package src;
import java.util.*;
import java.io.*;


public class FileParser { 

	Scanner in;
	Statistics statMap;
	public FileParser(String filename, Statistics map) throws IOException{
		in = new Scanner(new File(filename));
		statMap = map;
	}

    public void reading(){ 
        String s;
        while(in.hasNextLine()){
            s = in.nextLine();
            String words[] = s.split(" ");
            for(int i = 0; i < words.length; i++){
				statMap.addWord(words[i]);
            }
        }
        in.close();
    }
}