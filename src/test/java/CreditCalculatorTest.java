import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.Csv;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParser;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParserSettings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CreditCalculatorTest {
    private CreditCalculator creditCalculator;

    @BeforeEach
    public void init() {
        creditCalculator = new CreditCalculator();
    }

    public void getLoanInformation(int loanAmount, double interestRate, int duration) {
        File csvFile = new File("src/test/java/testResources/present.csv");
        try {
            CSVReader csvReader = new CSVReader(new FileReader(csvFile));
            CSVReader csvResult = creditCalculator.getLoanInformation(loanAmount, interestRate, duration);
            Assertions.assertEquals(csvResult.readAll(), csvReader.readAll());
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }


    }

}
