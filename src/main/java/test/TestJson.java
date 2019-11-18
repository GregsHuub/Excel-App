package test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestJson {

    public static void main(String[] args) throws JsonProcessingException {

            TestClass testClass = new TestClass("string,", 20, "nie ma");
            TestClass testClass3 = new TestClass("Nazwa stringa222", 240444, "I TU PIES POGRZEBANy");

        Map<Integer, TestClass> testMapWithObjects = new HashMap<>();
        testMapWithObjects.put(1,testClass);;
        testMapWithObjects.put(2,testClass3);

        List<TestClass> testList = new ArrayList<>();
        testList.add(testClass);

        ObjectMapper mapper = new ObjectMapper();

        String jsonMapString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testMapWithObjects);
        System.out.println(jsonMapString);

        System.out.println("TEST Z MAPA");

        String jsonString = mapper.writeValueAsString(testList);
        System.out.println(jsonString);

        String jsonInString2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testClass);
        System.out.println(jsonInString2);






    }

}