package justek.ide.model;

import java.util.HashMap;
import java.util.List;

import com.supinan.util.timer.SupinanTimer;
import com.supinan.util.timer.Timer;
import com.supinan.util.timer.TimerStopType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ThreadQueue {

	private HashMap<String,Timer> mQueue;
	
	
	private static ThreadQueue instance;

	public static ThreadQueue getInstance () {
		if(instance==null) {
			instance = new ThreadQueue();
		}
		return instance;
	}
	
	public ThreadQueue() {
		this.mQueue = new HashMap<>();
		
	}
	
	public boolean removeThread(String key) {
		boolean result = false;
		if(this.mQueue.containsKey(key)) {
			result = this.mQueue.remove(key, mQueue.get(key));
		}
		return result;
	}
	
	public boolean containThread(String key) {
		return this.mQueue.containsKey(key);
	}
	
	public Timer getTimeThread(String key) {
		if(this.containThread(key)) {
			return this.mQueue.get(key);
		}
		return null;
	}
	
	public void putTimeThread(String key,Timer thread) {
		this.mQueue.put(key,thread);
	}
}
