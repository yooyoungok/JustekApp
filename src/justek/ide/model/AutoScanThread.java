package justek.ide.model;

import justek.ide.model.listener.RealTimeEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.supinan.util.timer.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class AutoScanThread extends Timer{


	private RealTimeEventListener listener;
	
	
	private static AutoScanThread instance;

	public static AutoScanThread getInstance (long repeatPeriod) {
		if(instance==null) {
			instance = new AutoScanThread(repeatPeriod);
		}
		return instance;
	}
	
	public AutoScanThread(long repeatPeriod) {
		super(repeatPeriod);
	}

	public void addRealTimeEventListener(RealTimeEventListener listener) {
		this.listener = listener;
	}
	
	//Driver Scan
	public void getNodeInfo() {

		ObservableList<TreeItem<String>> nodes = FXCollections.observableArrayList();
		
		Runtime runtime = Runtime.getRuntime();
		try {
			int count=1;
			Process process = runtime.exec("ethercat slaves");
			String szLine=""; 
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			TreeItem<String> nodeA = new TreeItem<>("Controller");
			nodes.add(nodeA);
			CommandConst.driverList.clear();
			
			while ((szLine = br.readLine()) != null) {
				TreeItem<String> node = new TreeItem<>("Driver"+count);
				nodes.add(node);
				CommandConst.driverList.add("Driver"+count);
				count++;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(this.listener!=null){
				this.listener.autoScanEvent(nodes);
			}
		}
		
	}

	int i=0;
	//디버그 모드인경우 
	public void getDefaultNodeInfo() {

		ObservableList<TreeItem<String>> nodes = FXCollections.observableArrayList();
			i++;
			TreeItem<String> nodeA = new TreeItem<>("Controller_New_"+i);
			nodes.add(nodeA);
				TreeItem<String> node = new TreeItem<>("Driver_01");
				nodes.add(node);
				TreeItem<String> node1 = new TreeItem<>("Driver_02");
				nodes.add(node1);
				TreeItem<String> node2 = new TreeItem<>("Driver_03");
				nodes.add(node2);
				
				if(this.listener!=null){
					this.listener.autoScanEvent(nodes);
				}
	}
	
	@Override
	public void execute() {
		// 타이머 실행
//		Calendar cal = Calendar.getInstance();
//		System.out.println("AutoScanThread" + " 실행 [" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + "]");
		if(!CommandConst.isWindow) {
			this.getNodeInfo();
		}
		else {
			this.getDefaultNodeInfo();
		}
	}

	@Override
	public void stopTimer(TimerStopType type) {
		// 타이머 종료
		System.out.println(" AutoScanThread 종료 : " + type);
	}
}
