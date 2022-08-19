import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

public class CreditCalculatorTest {
    private CreditCalculator creditCalculator;

    @BeforeEach
    public void init() {
        creditCalculator = new CreditCalculator(100000, 10, 6);
    }

    @Test
    public void getLoanInformation(double loanAmount, double interestRate, int duration) {
        File csvFile = new File("src/test/java/testResources/present.csv");
        //       try {
        // CSVReader csvReader = new CSVReader(new FileReader(csvFile));
        // creditCalculator.getLoanInformation(loanAmount, interestRate, duration);

        //Assertions.assertEquals(csvResult.readAll(), csvReader.readAll());
//        } catch (IOException | CsvException e) {
//            e.printStackTrace();
//        }

        //     }

    }
}