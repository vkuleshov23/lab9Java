package src;
import java.util.HashMap;

public class Deadlock {
	private HashMap<Integer, String> map;
	private int from;
	private int to;
	private String thread;

	public Deadlock(int from, int to, String thread_num) {
		this.map = new HashMap<>();
		this.from = from;
		this.to = to;
		this.thread = thread_num;
	}
	public void set(int key, String name) {
		map.put(key, name);
	}
	public void setAll(String name) {
		for(int i = this.from; i <= this.to; i++) {
			this.set(i, name + i);
		}
	}
	public int getFrom() {
		return this.from;
	}
	public int getTo() {
		return this.to;
	}
	public String get(int key) {
		try{
			return map.get(key);
		} catch(NullPointerException err) {
			return "Error";
		}
	}

	public synchronized String get(int key, Deadlock tmp) throws NullPointerException{
		String name = null;
		try{
			System.err.println(key + " " + this.map.get(key));
			name = this.map.get(key);
			if(name == null) {
				throw new NullPointerException("no key");
			}
		} catch(NullPointerException err) {
			System.err.println("other map using" + key + " " + tmp.map.get(key));
			name = tmp.get(key, this);
		} finally {
			return name;
		}
	}

	public HashMap<Integer, String> fillNewByFirstComplementBySecond(Deadlock tmpMap, HashMap<Integer, String> newMap){
		int start = (this.getFrom() < tmpMap.getFrom()) ? this.getFrom() : tmpMap.getFrom();
		int finish = (this.getTo() > tmpMap.getTo()) ? this.getTo() : tmpMap.getTo();

		for(; start <= finish; start++){
			System.err.println(this.thread + " " + start);
			try{
				String getName = this.get(start, tmpMap);
				newMap.put(start, getName);
			} catch(NullPointerException err) {
				throw err;
			}
		}		
		return newMap;
	}
}