import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CreditCalculator {
    private int monthCount = 1;
    private final double monthlyInterestAmount;
    private final GregorianCalendar calendar;
    private double balanceOwed;
    private final double monthlyPayment;

    public CreditCalculator(double loanAmount, double interestRate, int duration) {

        int numberOfMonthsInAYear = 12;
        monthlyInterestAmount = (interestRate / numberOfMonthsInAYear) / 100;
        calendar = new GregorianCalendar();
        balanceOwed = Double.parseDouble(changingTheFractionalPart(2).format(loanAmount));

        this.monthlyPayment = Double.parseDouble(
                changingTheFractionalPart(2).format(

                        loanAmount *
                                (monthlyInterestAmount *
                                        (Math.pow(1 + monthlyInterestAmount, duration))) /
                                (Math.pow(1 + monthlyInterestAmount, duration) - 1)).replaceAll(",", "."));

        File paymentSchedule = new File("result.csv");
        if (paymentSchedule.length() != 0) {
            paymentSchedule.delete();

        }

    }


    public void getLoanInformation() {


        double accruedInterest = Double.parseDouble(changingTheFractionalPart(2).
                format(balanceOwed * monthlyInterestAmount)
                .replaceAll(",", "."));

        double principalDebt = Double.parseDouble(changingTheFractionalPart(2).
                format(monthlyPayment - accruedInterest).
                replaceAll(",", "."));

        if (balanceOwed < monthlyPayment) {
            balanceOwed = 0;
            saveTheResultToAFile(monthlyPayment, accruedInterest, principalDebt, balanceOwed, monthCount++);

            return;
        }
        balanceOwed = Double.parseDouble(changingTheFractionalPart(2).
                format(balanceOwed - principalDebt).replaceAll(",", "."));

        saveTheResultToAFile(monthlyPayment, accruedInterest, principalDebt, balanceOwed, monthCount++);
        calendar.add(Calendar.MONTH, +1);

        getLoanInformation();

    }


    public void saveTheResultToAFile(double monthlyPayment, double accruedInterest, double principalDebt, double balanceOwed, int monthCount) {

        File resultFile = new File("result.csv");

        try (CSVWriter writer = new CSVWriter(new FileWriter(resultFile, true))) {
            if (resultFile.length() == 0) {
                writer.writeNext(new String[]{"№ платежа", "Дата платежа", " Сумма платежа", " Основной долг", " Начисленные проценты", " Остаток задолженности"});
            }
            writer.writeNext(new String[]{
                    String.valueOf(monthCount),
                    calendar.getDisplayName(Calendar.MONTH,
                            Calendar.LONG, new Locale("ru")),
                    String.valueOf(monthlyPayment),
                    String.valueOf(principalDebt),
                    String.valueOf(accruedInterest),
                    String.valueOf(balanceOwed)});

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DecimalFormat changingTheFractionalPart(int numberOfDecimalPlaces) {
        StringBuilder pattern = new StringBuilder("#.");
        for (int a = 0; a < numberOfDecimalPlaces; a++) {
            pattern.append("#");
        }

        return new DecimalFormat(pattern.
                toString());
    }
}
