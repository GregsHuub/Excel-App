import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


class ApacheCsvReader implements CsvReaderInterface {

    public List<String> readCsvFiles(List<String> directories) throws IOException {
        List<List<String>> records = new ArrayList<>();


        for (int i = 0; i < directories.size(); i++) {
            try (CSVReader csvReader = new CSVReader(new FileReader(directories.get(i)))) {
                records.add(Collections.singletonList(directories.get(i)));
                String[] values;

                while ((values = csvReader.readNext()) != null) {
                    records.add(Arrays.asList(values));

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


            return records.stream().flatMap(List::stream).collect(Collectors.toList());



//        public List<String> readCsvFilesSINGLELIST (List <String> directories) throws IOException {
//            List<String> records = new ArrayList<>();
//
//            for (int i = 0; i < directories.size(); i++) {
//                try (Reader reader = Files.newBufferedReader(Paths.get(directories.get(i)));
//                     CSVReader csvReader = new CSVReader(reader);
//                ) {
//
//                    records.add(directories.get(i));
//                    String[] values;
//                    while ((values = csvReader.readNext()) != null) {
//                        records.add(String.valueOf(values));
//                        System.out.println("name: " + values[0]);
////                    System.out.println("Surname: " + values[1]);
////                    System.out.println("age: " + values[2]);
////                    System.out.println("Country: " + values[3]);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            return records;
//        }

    }
}