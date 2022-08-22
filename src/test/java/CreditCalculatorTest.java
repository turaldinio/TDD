import Controller.TurboBankController;
import Model.TurboBank;
import View.TurboBankView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CreditCalculatorTest {
    private TurboBankController controller;
    private List<String[]> list;

    @BeforeEach
    public void init() {

        controller = new TurboBankController(new TurboBank(1_000_000, 23.5, 72),
                new TurboBankView());
        list = controller.getLoanInformation();


    }


    /**
     * the number of payments is equal to the number of months of the loan
     */
    @Test
    public void numberOFMonthsAndNumberOfPayments() {
        Assertions.assertEquals(list.size(), controller.getDuration());
    }

    /**
     * we check that the method creates a file and writes data to it
     */
    @Test
    public void theLoanIsRepaid() {
        Assertions.assertEquals(0, controller.getBalanceOwed());
    }

    /**
     * we check that the method creates a file and writes data to it
     */
    @Test
    public void isFileExists() {
        controller.saveTheResultToAFile(list, "result.csv");
        Assertions.assertTrue(Files.exists(Path.of("result.csv")));
    }


}