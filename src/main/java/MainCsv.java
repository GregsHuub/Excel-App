import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeoutException;

public class MainCsv {

    private static final ThreadTimer threadTimer = new ThreadTimer();


    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {

        ApacheCsvReader apacheCsvReader = new ApacheCsvReader();

//        PROPERTIES

        final Properties appProperties = new Properties();

        appProperties.load(new FileInputStream("src/main/resources/csv.properties"));
        final String direction1 = appProperties.getProperty("folder1");
        final String direction2 = appProperties.getProperty("folder2");
        final String direction3 = appProperties.getProperty("folder3");
        final String direction4 = appProperties.getProperty("folder4");
        final String direction5 = appProperties.getProperty("folder5");

        final int interval = Integer.parseInt(appProperties.getProperty("interval"));

        String fileFormat = appProperties.getProperty("fileFormat");
//                PROPERTIES

        List<String> directions = new ArrayList<>();
        directions.add(direction1);
        directions.add(direction2);
        directions.add(direction3);
        directions.add(direction4);
        directions.add(direction5);


        System.out.println("PLIKI W KATALOGACH");

        for (int i = 0; i <= 4; i++) {

            if (apacheCsvReader.findFilesInFolder(directions.get(i), fileFormat).isEmpty()) {
                System.out.println("Folder numer: " + (i + 1) + " jest pusty");
                threadTimer.timePause(1);
            } else {
                System.out.println(apacheCsvReader.findFilesInFolder(directions.get(i), fileFormat));
                threadTimer.timePause(interval);
            }
        }

        Thread.sleep(interval);
        System.out.println("Read file in progress..");

//        List<String> listToPrint = apacheCsvReader.readCsvFiles(apacheCsvReader.findFilesInFolder(direction1, fileFormat));
//        int size = listToPrint.size();
//        System.out.println(size);
//        System.out.println(listToPrint.get(1));
//        System.out.println("brake");
//        System.out.println(listToPrint.get(2));
//
//        System.out.println("ODcZYT");
        System.out.println("Rabbit MQ Connection...");
        String QUEUE_NAME = "test1";

//        TEST PARSOWANIA LISTY DO JSONA i wydruk

//        System.out.println("TEST jSONA");
//        List<List<String>> listsTEMPTEMP = apacheCsvReader.readCsvFiles(apacheCsvReader.findFilesInFolder(direction1, fileFormat));
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        for (List<String> strings : listsTEMPTEMP) {
//
//                String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(strings);
//                System.out.println(jsonString);
//
//        }
//
//        System.out.println("KONIEC TESTU JSONA");
//        Thread.sleep(2000);

//        System.out.println("TEST WYDRUKU LISTY Z SINGLE LIST");
//        List<String> stringsListTEST = apacheCsvReader.readCsvFilesSINGLELIST(apacheCsvReader.findFilesInFolder(direction1, fileFormat));
//        stringsListTEST.forEach(System.out::println);
//        System.out.println(" KONIEC TEST WYDRUKU LISTY Z SINGLE LIST");
//        Thread.sleep(2000);


//        ZAPIS DO RABBITMQ

        System.out.println("START SENDING MASSAGES TO RABBITMQ");


        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            List<List<String>> lists = Collections.singletonList(apacheCsvReader.readCsvFiles(apacheCsvReader.findFilesInFolder(direction1, fileFormat)));
            for (List<String> list : lists) {
                for (String listTemp : list) {
                    System.out.print(listTemp + " ,");
                    Thread.sleep(100);

                    channel.basicPublish("", QUEUE_NAME, null, listTemp.getBytes("UTF-8"));
                }
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("Message sent. Connection Close");
    }
}
