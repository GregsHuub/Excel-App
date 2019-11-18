public class ThreadTimer {
    public void timePause(int numberInSeconds) throws InterruptedException {
        Thread.sleep(numberInSeconds * 1000);
    }
}
