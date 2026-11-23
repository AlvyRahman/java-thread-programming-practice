import java.lang.Thread;

public class InnerTest {
    public void run()
    {
        String name = Thread.currentThread().getName();
        System.out.println("Class: InnerTest Thread: "+name);
    }
}