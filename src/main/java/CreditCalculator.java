import com.opencsv.CSVReader;

public class CreditCalculator {
    private final int numberOfMonthsInAYear = 12;

    public CSVReader getLoanInformation(double loanAmount, double interestRate, int duration) {
        if (loanAmount == 0) {
            return null;
        }
        double monthlyInterestAmount = interestRate / numberOfMonthsInAYear;
        double monthlyPayment = Math.round(monthlyInterestAmount * Math.pow(1 + monthlyInterestAmount, duration)) /
                (Math.pow(1 + monthlyInterestAmount, 6) - 1);

        getLoanInformation((loanAmount - monthlyPayment), interestRate, duration);

    }

    public void saveTheResultToAFile(double monthlyInterestAmount) {

    }
}
