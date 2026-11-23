import java.util.concurrent.TimeUnit;

public class Test
{
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            int c = 0;
            while (c < 11) {
                System.out.println("Thread #1");
                c++;
            }
        });

        t1.setName("A-T #1");
        t1.start();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("!!!Hello from MAIN!!!");
    }
}