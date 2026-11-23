import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Watch implements Runnable {
    private volatile boolean running = true;

    @Override
    public void run()
    {
        while (running) {
            printCurrentTime();

            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            } catch (InterruptedException ignored) {
                //e.printStackTrace();
            }
            
            if(Thread.interrupted()) {
                System.out.println("Thread is interrupted");
                return;
            }
        }
    }

    public void printCurrentTime()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:s a");
        String currentTime = LocalDateTime.now().format(formatter);

        System.out.println(currentTime);
    }

    public void stop()
    {
        this.running = false;
        System.out.println("Thread is interrupted");
    }
}
