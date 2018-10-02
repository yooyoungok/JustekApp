package justek.ide.model.manager;

import java.util.HashMap;
import java.util.List;

import com.supinan.util.timer.SupinanTimer;
import com.supinan.util.timer.Timer;
import com.supinan.util.timer.TimerStopType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ThreadQueue {

	private HashMap<String,Timer> mQueue;
	private HashMap<String, List<Object>> mListenerQueue;
	
	private static ThreadQueue instance;

	public static ThreadQueue getInstance () {
		if(instance==null) {
			instance = new ThreadQueue();
		}
		return instance;
	}
	
	public ThreadQueue() {
		mQueue = new HashMap<>();
		mListenerQueue = new HashMap<>();
	}
	
	private boolean removeThread(String key) {
		boolean result = false;
		if(this.mQueue.containsKey(key)) {
			result = this.mQueue.remove(key, mQueue.get(key));
		}
		return result;
	}
	
	/**************************
	 * 
	 * 요청된 쓰레드를 정지시키고 큐에서 삭제한다.
	 * 	return true/false;
	 * 
	 ************************ */
	public boolean stopThread(String key) {
		Timer timer  = null;
		if((timer = this.getTimeThread(key)) != null) {
			timer.stopTimer(TimerStopType.STOP_USER);
			return this.removeThread(key);
		}
		return false;
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
	
	public boolean removeThreadListener(String key,Object value) {
		return this.mListenerQueue.remove(key, value);
	}

	public HashMap<String, List<Object>> getmListenerQueue() {
		return mListenerQueue;
	}	
}
