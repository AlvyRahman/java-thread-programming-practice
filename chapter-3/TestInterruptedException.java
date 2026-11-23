public class TestInterruptedException {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(30);
                System.out.println("Thread t1 has completed running");
            } catch (InterruptedException e) {
                System.out.println("Thread t1 interrupted");
                Thread.currentThread().interrupt();
                throw new AssertionError(e);
            }
        });

        t1.start();
        t1.interrupt();
    }
}
