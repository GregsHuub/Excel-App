package gsonTest;



import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;


public class GsonTest {
    public static void main(String[] args) throws IOException {

        File input = new File("C:\\Users\\a769324\\Desktop\\csv_Files\\Java EXcel reader\\csvDoTestu.csv");
        File output = new File("C:\\Users\\a769324\\Desktop\\csv_Files\\Java EXcel reader\\output.json");

        CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
        CsvMapper csvMapper = new CsvMapper();

        List<Object> readAll = csvMapper.readerFor(Map.class).with(csvSchema).readValues(input).readAll();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(output, readAll);

        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(readAll));

    }

}
