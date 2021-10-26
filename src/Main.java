package src;
import java.util.HashMap;
import java.lang.management.*;

public class Main {
    public static void main(String[] args) {
        
        Deadlock a = new Deadlock(40, 55, "Thread1");
        a.setAll("a");
        Deadlock b = new Deadlock(35, 50, "Thread2");
        b.setAll("b");
        HashMap<Integer, String> aMap = new HashMap<>();
        aMap.put(1, "init");
        HashMap<Integer, String> bMap = new HashMap<>();
        bMap.put(1, "init");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
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
        try {
            Thread.sleep(200);
        } catch(InterruptedException err) {}

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
        System.err.println("aMap:\n" + aMap);
        System.err.println("bMap:\n" + bMap);
    }
}