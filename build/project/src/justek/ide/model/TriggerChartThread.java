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

	
	// ������ �ð�(repeatPeriod)��ŭ �ֱ������� ����
	public TriggerChartThread(long repeatPeriod, String name,String numberDriveNo) {
		super(repeatPeriod);
		this.numberDriveNo = numberDriveNo;
		this.name = name;
	}
	
	// ������ �ð�(repeatPeriod)��ŭ �ֱ������� �� ������ Ƚ��(repeatCount)��ŭ ����
	public TriggerChartThread(long repeatPeriod, int repeatCount,String numberDriveNo, String name) {
		super(repeatPeriod, repeatCount);
		this.name = name;
		this.numberDriveNo = numberDriveNo;
		System.out.println(this.name + " ����");
	}
	
	public void addListener(TriggerEventListener listener) {
		this.listener = listener;
	}
	
	public void setTriggerInfo(TriggerInfo info) {
		this.info = info;
	}

	@Override
	public void execute() {
		// Ÿ�̸� ����
//		Calendar cal = Calendar.getInstance();
//		System.out.println(this.name + " ���� [" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + "]");
//		String fileName = "./motionware/motion_server/pdata/"+this.fileCount+".out";
		
//		String fileName = "./pdata/"+this.fileCount+".out"; -> 2018.06.27 ������ �ּ�ó��
		String fileName = "./pdata/triggerData.out";
		boolean result = GraphTriggerData.getInstance().checkOutFileExists(fileName);
		if(result) {
			System.out.println(this.name + " �����̸�= [" + fileName+ "] �� �ֽ��ϴ�.");
//			this.fileCount++; -> 2018.06.27 ������ �ּ�ó��
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
			System.out.println(this.name + " �����̸�= [" + fileName+ "] �� �����ϴ�.");
		}
	}

	@Override
	public void stopTimer(TimerStopType type) {
		// Ÿ�̸� ����
		System.out.println(this.name + " ���� : " + type);
	}
}
