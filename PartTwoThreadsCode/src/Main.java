import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1000);

        List<Future<Long>> futures = new ArrayList<>();
        for(int i=0;i<1000;i++)
        {
           Callable<Long> task = new Callable<Long>() {
               @Override
               public Long call() throws Exception {
                   long sum = 0;
                   for(int j=1;j<=1000000;j++)
                   {
                       sum++;
                   }
                   return sum;
               }
           };

            futures.add(fixedThreadPool.submit(task));
        }

        long totalSum =0;
        for(Future<Long> f: futures)
        {
            totalSum+=f.get();
        }

        System.out.println("Sum is: "+totalSum);

    }
}