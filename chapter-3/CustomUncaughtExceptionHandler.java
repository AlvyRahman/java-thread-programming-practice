import java.util.concurrent.TimeUnit;
 
class MyCustomExceptionHandler implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread td, Throwable thw)
    {
        System.out.println("Exception thrown from: " + td.getName());
        System.out.println("Exception thrown from: " + thw.getMessage());
    }
}

public class CustomUncaughtExceptionHandler {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                System.err.println("Thread inturupted: " + e.getMessage());
            }

            throw new RuntimeException("Runtime Exception inside t1");
        });

        t1.setUncaughtExceptionHandler(new MyCustomExceptionHandler());
        t1.start();
    }
}
