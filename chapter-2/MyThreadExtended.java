import java.lang.Thread;
import java.util.concurrent.TimeUnit;

public class MyThreadExtended extends Thread
{
    @Override
    public void run()
    {
        String name = Thread.currentThread().getName();

        for(int i = 1; i <= 5; i++) {
            System.out.println(i+": "+name);
            
            try{
                Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}