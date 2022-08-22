package View;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TurboBankView implements CreateAndSaveAPaymentSchedule {


    @Override
    public void saveTheResultToAFile(List<String[]> list, String filePath) {
        File file = overWriteFile(filePath);
        try (CSVWriter writer = new CSVWriter(new FileWriter(file, true))) {

            if (file.length() == 0) {
                writer.writeNext(new String[]{"№ платежа", "Дата платежа", " Сумма платежа", " Основной долг", " Начисленные проценты", " Остаток задолженности"});
            }
            writer.writeAll(list);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void out(List<String[]> list) {
        System.out.println(" № платежа " + " Дата платежа " + " Сумма платежа " + " Основной долг " + " Начисленные проценты " + " Остаток задолженности ");
        for (String[] line : list) {
            print(line);
        }
    }

    private void print(String[] array) {
        StringBuilder builder = new StringBuilder();
        builder.append("     ").
                append(array[0]).append("       ").
                append(array[1]).append("     ").
                append(array[2]).append("      ").
                append(array[3]).append("          ").
                append(array[4]).append("             ").
                append(array[5]).append("\n");

        System.out.println(builder.toString());
    }

    private File overWriteFile(String path) {
        File file = new File(path);
        if (file.length() != 0) {
            file.delete();
        }
        return new File(path);

    }
}
