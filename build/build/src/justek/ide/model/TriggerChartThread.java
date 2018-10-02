package justek.ide.model;

import justek.ide.chart.data.GraphTriggerData;
import justek.ide.model.listener.TriggerEventListener;

import com.supinan.util.timer.*;

public class TriggerChartThread extends Timer{

	private final String name;
	public String numberDriveNo;
	public int fileCount=0;
	TriggerInfo info;
	TriggerEventListener listener;
	
	private static TriggerChartThread instance;
	
	public static TriggerChartThread getInstance (long repeatPeriod, String numberDriveNo, String name) {
		if(instance==null) {
			instance = new TriggerChartThread(repeatPeriod,numberDriveNo,name);
		}
		return instance;
	}

	
	// 지정한 시간(repeatPeriod)만큼 주기적으로 실행
	public TriggerChartThread(long repeatPeriod, String name,String numberDriveNo) {
		super(repeatPeriod);
		this.numberDriveNo = numberDriveNo;
		this.name = name;
	}
	
	// 지성한 시간(repeatPeriod)만큼 주기적으로 총 지정한 횟수(repeatCount)만큼 실행
	public TriggerChartThread(long repeatPeriod, int repeatCount,String numberDriveNo, String name) {
		super(repeatPeriod, repeatCount);
		this.name = name;
		this.numberDriveNo = numberDriveNo;
		System.out.println(this.name + " 실행");
	}
	
	public void addListener(TriggerEventListener listener) {
		this.listener = listener;
	}
	
	public void setTriggerInfo(TriggerInfo info) {
		this.info = info;
	}

	@Override
	public void execute() {
		// 타이머 실행
//		Calendar cal = Calendar.getInstance();
//		System.out.println(this.name + " 실행 [" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + "]");
//		String fileName = "./motionware/motion_server/pdata/"+this.fileCount+".out";
		
//		String fileName = "./pdata/"+this.fileCount+".out"; -> 2018.06.27 수정후 주석처리
		String fileName = "./pdata/triggerData.out";
		boolean result = GraphTriggerData.getInstance().checkOutFileExists(fileName);
		if(result) {
			System.out.println(this.name + " 파일이름= [" + fileName+ "] 이 있습니다.");
//			this.fileCount++; -> 2018.06.27 수정후 주석처리
			if(this.info.getMode().equals("Single")) {
				System.out.println("Single");
				listener.SingleTriggerEvent("");
			}
			else {
				System.out.println("Normal");
				listener.NormalTriggerEvent("");
			}
		}
		else {
			System.out.println(this.name + " 파일이름= [" + fileName+ "] 이 없습니다.");
		}
	}

	@Override
	public void stopTimer(TimerStopType type) {
		// 타이머 종료
		System.out.println(this.name + " 종료 : " + type);
	}
}
