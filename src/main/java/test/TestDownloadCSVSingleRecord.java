package test;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestDownloadCSVSingleRecord {

    private static final String SAMPLE_CSV = "C:\\Users\\a769324\\OneDrive - Atos\\Desktop\\Do testu\\copy1\\plik1.csv";

    public static void main(String[] args) throws IOException {


        Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV));
        CSVReader csvReader = new CSVReader(reader);

        String[] nextRecord;
        while ((nextRecord = csvReader.readNext()) != null) {
            System.out.println("Name : " + nextRecord[0]);
//            System.out.println("Email : " + nextRecord[1]);
//            System.out.println("Phone : " + nextRecord[2]);
//            System.out.println("Country : " + nextRecord[3]);
//            System.out.println("==========================");
        }

    }
}
