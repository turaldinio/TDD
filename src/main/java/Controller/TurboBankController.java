package Controller;

import Model.CreditCalculator;
import View.TurboBankView;

import java.util.List;

public class TurboBankController {
    private CreditCalculator creditCalculator;
    private TurboBankView paymentScheduler;

    public TurboBankController(CreditCalculator creditCalculator, TurboBankView paymentScheduler) {
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

}
