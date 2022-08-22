package Model;

import java.util.*;

public abstract class CreditCalculator implements RoundingFractionalPart {
    private int paymentNumber = 1;
    private final double monthlyInterestAmount;
    private double balanceOwed;
    private final double monthlyPayment;
    private List<String[]> result;
    private GregorianCalendar calendar;

    public CreditCalculator(double loanAmount, double interestRate, int duration) {

        int numberOfMonthsInAYear = 12;
        monthlyInterestAmount = (interestRate / numberOfMonthsInAYear) / 100;
        balanceOwed = Double.parseDouble(changingTheFractionalPart(2).format(loanAmount));

        this.monthlyPayment = Double.parseDouble(
                changingTheFractionalPart(2).format(

                        loanAmount *
                                (monthlyInterestAmount *
                                        (Math.pow(1 + monthlyInterestAmount, duration))) /
                                (Math.pow(1 + monthlyInterestAmount, duration) - 1)).replaceAll(",", "."));

        result = new ArrayList<>();
        calendar = new GregorianCalendar();
    }

    public List<String[]> getLoanInformation() {


        double accruedInterest = Double.parseDouble(changingTheFractionalPart(2).
                format(balanceOwed * monthlyInterestAmount)
                .replaceAll(",", "."));

        double principalDebt = Double.parseDouble(changingTheFractionalPart(2).
                format(monthlyPayment - accruedInterest).
                replaceAll(",", "."));

        if (balanceOwed < monthlyPayment) {
            balanceOwed = 0;
            result.add(new String[]{
                    String.valueOf(paymentNumber++),
                    calendar.get(Calendar.DAY_OF_MONTH) + " " +

                            calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, new Locale("ru")) + " " + calendar.get(Calendar.YEAR),

                    String.valueOf(monthlyPayment),
                    String.valueOf(principalDebt),
                    String.valueOf(accruedInterest),
                    String.valueOf(balanceOwed)});

            return result;
        }
        balanceOwed = Double.parseDouble(changingTheFractionalPart(2).
                format(balanceOwed - principalDebt).replaceAll(",", "."));

        result.add(new String[]{
                String.valueOf(paymentNumber++),
                calendar.get(Calendar.DAY_OF_MONTH) + " " +

                        calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, new Locale("ru")) + " " + calendar.get(Calendar.YEAR),

                String.valueOf(monthlyPayment),
                String.valueOf(principalDebt),
                String.valueOf(accruedInterest),
                String.valueOf(balanceOwed)});

        calendar.add(Calendar.MONTH, +1);


        return getLoanInformation();
    }
}