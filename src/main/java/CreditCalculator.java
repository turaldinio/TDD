import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Month;

public class CreditCalculator {
    private final int numberOfMonthsInAYear = 12;
    private int monthCount = 1;
    private double interestRate;
    private double loanAmount;
    private int duration;
    private double monthlyInterestAmount;
    private DecimalFormat decimalFormat;

    public CreditCalculator(double loanAmount, double interestRate, int duration) {
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.duration = duration;
        decimalFormat = new DecimalFormat("#.####");
        monthlyInterestAmount = Double.parseDouble(decimalFormat.format((interestRate / numberOfMonthsInAYear) / 100).replaceAll(",", "."));
    }


    public void getLoanInformation() {
        if (loanAmount == 0) {
            return;
        }


        double monthlyPayment = loanAmount * Double.parseDouble(new DecimalFormat("#.######").format(
                (monthlyInterestAmount *
                        (Math.pow(1 + monthlyInterestAmount, duration))) /
                        (Math.pow(1 + monthlyInterestAmount, duration) - 1)).replaceAll(",", "."));

        saveTheResultToAFile(loanAmount, monthlyInterestAmount, interestRate, monthCount++);

        loanAmount = loanAmount - monthlyPayment;

        getLoanInformation();

    }


    public void saveTheResultToAFile(double loanAmount, double monthlyInterestAmount, double interestRate, int monthCount) {
        File resultFile = new File("result.csv");

        try (CSVWriter writer = new CSVWriter(new FileWriter(resultFile))) {
            if (resultFile.length() == 0) {
                writer.writeNext(new String[]{"№ платежа", "Дата платежа", " Сумма платежа", " Основной долг", " Начисленные проценты", " Остаток задолженности"});
            }
            writer.writeNext(new String[]{String.valueOf(monthlyInterestAmount),
                    Month.of(monthCount).name(),
                    String.valueOf(interestRate),
                    String.valueOf(loanAmount)});

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
