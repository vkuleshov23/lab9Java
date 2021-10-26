package src;
import java.util.concurrent.ConcurrentHashMap;

public class Statistics {
	private ConcurrentHashMap<String, Integer> statMap;

	public Statistics(){
		statMap = new ConcurrentHashMap<String, Integer>();
	}
	public void addWord(String word){
		if(statMap.containsKey(word)){
			statMap.replace(word, (statMap.get(word))+1);
		} else {
			statMap.put(word, 1);
		}
	}
	public ConcurrentHashMap<String, Integer> getMap(){
		return statMap;
	}
}