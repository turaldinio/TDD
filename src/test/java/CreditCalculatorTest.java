import Model.TurboBank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

public class CreditCalculatorTest {
    private TurboBank creditCalculator;

    @BeforeEach
    public void init() {
        creditCalculator = new TurboBank(100000, 10, 6);
    }

    @Test
    public void getLoanInformation(double loanAmount, double interestRate, int duration) {
        File csvFile = new File("src/test/java/testResources/present.csv");

    }
}