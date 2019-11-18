package test;

public class TestClass {

    String string;
    Integer number;
    String testowo;

    public TestClass(String string, Integer number, String testowo) {
        this.string = string;
        this.number = number;
        this.testowo = testowo;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTestowo() {
        return testowo;
    }

    public void setTestowo(String testowo) {
        this.testowo = testowo;
    }
}
