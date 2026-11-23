import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FibonacciTask implements Runnable {
    long n;
    String id;

    public FibonacciTask(long n, String id)
    {
        this.n = n;
        this.id = id;
    }

    private BigDecimal fib(long n)
    {
        if(n == 0) return BigDecimal.ZERO;
        if(n == 1) return BigDecimal.ONE;
        return fib(n-1).add(fib(n-2));
    }

    @Override
    public void run()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;

        System.out.println(this.id+" started at "+formatter.format(LocalDateTime.now()));
        
        BigDecimal fib = fib(n);

        System.out.println(this.id+" ended at "+formatter.format(LocalDateTime.now())+" || Result: "+fib);
    }
}
