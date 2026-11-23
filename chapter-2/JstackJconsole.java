import java.util.concurrent.TimeUnit;

public class JstackJconsole {
    public static void main(String[] args) {
        Thread t1 = new Thread(()-> {
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(()-> {
            while (!Thread.interrupted()) {
                System.out.println("T2 is running");
            }
        });

        t1.setName("Thread #1");
        t2.setName("Thread #2");
        t1.start();
        t2.start();

        try {
            TimeUnit.MINUTES.sleep(5);
        } catch (InterruptedException e) {
                e.printStackTrace();
        }

        t2.interrupt();
    }    
}
