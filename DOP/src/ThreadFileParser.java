package src;
import java.io.*;

public class ThreadFileParser extends Thread{
	FileParser fp;
	public ThreadFileParser(String filename, Statistics map) throws IOException{
		fp = new FileParser(filename, map);
	}
	@Override
	public void run(){
		fp.reading();
	}
}