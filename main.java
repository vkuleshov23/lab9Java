package prog.lab9;
import java.util.HashMap;
import java.lang.management.*;

public class main{
    public static void main(String[] args){
        
        Deadlock a = new Deadlock(50, 120, "Thread1");
        a.setAll("a");
        Deadlock b = new Deadlock(55, 125, "Thread2");
        b.setAll("b");
        HashMap<Integer, String> aMap = new HashMap<>();
        aMap.put(1, "init");
        HashMap<Integer, String> bMap = new HashMap<>();
        bMap.put(1, "init");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    a.fillNewByFirstComplementBySecond(b, aMap);
                } catch(NullPointerException err){
                    System.out.println("null pointer");
                    
                }
            }
        }).start();
    
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    b.fillNewByFirstComplementBySecond(a, bMap);
                } catch(NullPointerException err){
                    System.out.println("null pointer");
                }
            }
        }).start();
        try{Thread.sleep(2000);} catch(InterruptedException err){}

        System.out.println("finding deadlocked threads");
        ThreadMXBean tmx = ManagementFactory.getThreadMXBean();
        long[] ids = tmx.findDeadlockedThreads();
        if (ids != null) {
            ThreadInfo[] infos = tmx.getThreadInfo(ids, true, true);
            System.out.println("the following threads are deadlocked:");
            for (ThreadInfo ti : infos) {
                System.out.println(ti);
            }
        }
        System.out.println("finished finding deadlocked threads");

        // System.out.println("aMap:\n" + aMap);
        // System.out.println("bMap:\n" + bMap);
    }
}