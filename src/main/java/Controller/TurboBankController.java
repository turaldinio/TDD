package Controller;

import Model.CreditCalculator;
import View.CreateAndSaveAPaymentSchedule;

import java.util.List;

public class TurboBankController {
    private CreditCalculator creditCalculator;
    private CreateAndSaveAPaymentSchedule paymentScheduler;

    public TurboBankController(CreditCalculator creditCalculator, CreateAndSaveAPaymentSchedule paymentScheduler) {
        this.creditCalculator = creditCalculator;
        this.paymentScheduler = paymentScheduler;
    }

    public List<String[]> getLoanInformation() {
        return creditCalculator.getLoanInformation();
    }

    public void saveTheResultToAFile(List<String[]> list, String filePath) {
        paymentScheduler.saveTheResultToAFile(list, filePath);
    }

    public void out(List<String[]> list) {
        paymentScheduler.out(list);
    }

    public int getDuration() {
        return creditCalculator.getDuration();
    }

    public double getBalanceOwed() {
        return creditCalculator.getBalanceOwed();
    }

}
