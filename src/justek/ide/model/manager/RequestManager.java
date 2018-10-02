package justek.ide.model.manager;

import java.util.HashMap;
import java.util.List;

import com.supinan.util.timer.SupinanTimer;
import com.supinan.util.timer.TimerStopType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import justek.ide.model.CommandConst;
import justek.ide.model.RealTimeInfo;
import justek.ide.model.RealTimeThread;
import justek.ide.model.listener.RealTimeEventListener;

public class RequestManager implements RealTimeEventListener {

	private SupinanTimer mTimer;
	private ThreadQueue mQueue;
	private RealTimeEventListener realTimeEventListener;
	public long mThreadId;
	
	private static RequestManager instance;

	public static RequestManager getInstance () {
		if(instance==null) {
			instance = new RequestManager();
		}
		return instance;
	}
	
	public RequestManager() {
		this.mTimer = new SupinanTimer();
		mQueue = ThreadQueue.getInstance();
	}
	
	public boolean isRequestRealTimeData(String DriverNo) {
		return this.mQueue.containThread(CommandConst.REAL_TIME_THREAD_KEY+DriverNo);
	}
	
	public boolean isRequestRealTimeData() {
		if(this.mThreadId>0) {
			return true;
		}
		return false;
	}
	
	public void setRealTimeEventListener(RealTimeEventListener listener)
	{
		System.out.println("setRealTimeEventListener");
		
		if(listener == null) {
			System.out.println("setRealTimeEventListener null");
			this.realTimeEventListener = null;
		}
		else {
			realTimeEventListener = listener;
		}
	}
	
	/**
	 * @author : YOO YOUNGOK 
	 * @method  requestRealTimeData
	 * @param DriverNo
	 * @param listener
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 7. 5.
	 */
	public void requestRealTimeData(String DriverNo,RealTimeEventListener listener) {
		String key = CommandConst.REAL_TIME_THREAD_KEY+DriverNo;
		RealTimeThread thread = new RealTimeThread(CommandConst.INTERVAL_TIME,DriverNo);
		this.realTimeEventListener = listener;
		thread.mThreadId = mTimer.addTimer(thread);
		thread.addRealTimeListener(this);
		ThreadQueue.getInstance().putTimeThread(key,thread);
	}
	
	/**
	 * @author : YOO YOUNGOK 
	 * @method  requestAllRealTimeData
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 7. 5.
	 */
	public void requestAllRealTimeData() {
		System.out.println("requestAllRealTimeData");		
		if(!NetworkServerManager.checkSocket()) return;
		RealTimeThread thread = new RealTimeThread(CommandConst.INTERVAL_TIME);
		this.mThreadId = mTimer.addTimer(thread);
		thread.addRealTimeListener(this);
	}
	
	
	//추후 삭제할것...
	public void testRealTimeData() {
		System.out.println("testRealTimeData");		
		RealTimeThread thread = new RealTimeThread(CommandConst.INTERVAL_TIME);
		this.mThreadId = mTimer.addTimer(thread);
		thread.addRealTimeListener(this);
	}
	
	public boolean stopRealTimeThread(String DriverNo) {
		String key = CommandConst.REAL_TIME_THREAD_KEY+DriverNo;
		return ThreadQueue.getInstance().stopThread(key);
	}
	
	public void stopAllRealTimeThread() {
//		mTimer.removeTimer(this.mThreadId);
		mTimer.closeTimer();
		this.mThreadId = -1;
	}
	
	@Override
	public void realTimeDatarEvent(double Source) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void realTimeDataInfoEvent(RealTimeInfo Source) {
		// TODO Auto-generated method stub
//		System.out.println("RequestManager ==" +Source.toString());
		if(this.realTimeEventListener!=null) {
			this.realTimeEventListener.realTimeDataInfoEvent(Source);
		}
	}

	@Override
	public void autoScanEvent(ObservableList<TreeItem<String>> nodes) {
		// TODO Auto-generated method stub
		
	}
 
}
