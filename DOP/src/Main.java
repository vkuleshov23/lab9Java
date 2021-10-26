package src;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{

    	Statistics stat = new Statistics();
    	Thread[] files = new Thread[args.length];
    	try{
			for(int i = 0; i < args.length; i++){
				files[i] = new ThreadFileParser(args[i], stat);
				files[i].start();
			}
    		for(Thread file : files){
    			file.join();
    		}
    	} catch(InterruptedException ie) {
    		ie.printStackTrace();
		} catch (IOException e) {
    		e.printStackTrace();
		}
		System.out.println(stat.getMap());
    }
}