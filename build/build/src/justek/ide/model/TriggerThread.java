package justek.ide.model;

import justek.ide.model.listener.RealTimeEventListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import com.supinan.util.timer.*;

public class TriggerThread extends Timer{

	private TriggerInfo triggerInfo;
	private final String name;
	private ArrayList<String> resultList;
	public String numberDriveNo;
	private RealTimeEventListener listener;
	
	double preValue=0;
	double curValue = 0;

	
	private static TriggerThread instance;

	public static TriggerThread getInstance (long repeatPeriod, String name,String numberDriveNo) {
		if(instance==null) {
			instance = new TriggerThread(repeatPeriod,name,numberDriveNo);
		}
		return instance;
	}

	public TriggerThread(long repeatPeriod, String name,String numberDriveNo) {
		super(repeatPeriod);
		this.numberDriveNo = numberDriveNo;
		this.name = name;
	}
	
	public void addTriggerEventListener(RealTimeEventListener listener) {
		this.listener = listener;
	}
	
	public void getRealTimedata() {
		Runtime runtime = Runtime.getRuntime();
		try {
			Process process = runtime.exec("./shm_helper1 s"+this.numberDriveNo);
			String szLine; 
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));		
			resultList = new ArrayList<>();
			while ((szLine = br.readLine()) != null) {
				System.out.println(szLine);
				this.parsingData(szLine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void getInfo() {

		File file = new File("data.txt");
		BufferedReader br;
		FileReader fr = null; 
		String line =null;
		
		resultList = new ArrayList<>();
		try{
			fr = new FileReader(file); 
			br = new BufferedReader(fr);
			while((line = br.readLine()) != null){
				parsingData(line);
			}
			
			
		}catch(IOException e) {
			System.out.println("Thread Exception Stop getInfo :: Thread#1");
			e.printStackTrace(); 
		}
	}
	
	public void parsingData(String readData) {
		String[] parsingList;
		
		if(readData!=null) {
			System.out.println(readData);
			parsingList = readData.split(":");
			if(parsingList.length==2) {
				this.resultList.add(parsingList[1]);
				if(this.resultList.size()==3) {
					if(this.listener!=null) {
						this.listener.realTimeDatarEvent((this.getPositionValue()));
					}
				}
			}
		}

	}
	
	public double getPositionValue() {
		double value = Double.parseDouble(this.resultList.get(2));
		return value;
	}
	public double getValue() {
		double value = 0;
		if(this.triggerInfo.getSource().equals("PositionError")) {
			double accPos = Double.parseDouble(this.resultList.get(2));
			double tarPos = Double.parseDouble(this.resultList.get(1));
			value  = accPos-tarPos;
		}
		else if (this.triggerInfo.getSource().equals("ActualVelocity")) {
			value = Double.parseDouble(this.resultList.get(3));
		}
		else if (this.triggerInfo.getSource().equals("ActualPosition")) {
			value = Double.parseDouble(this.resultList.get(2));
		}
		else if (this.triggerInfo.getSource().equals("TargetPosition")) {
			value = Double.parseDouble(this.resultList.get(1));
	}
		return value;
	}
	
	@Override
	public void execute() {
		// 타이머 실행
		Calendar cal = Calendar.getInstance();
		System.out.println(this.name + " 실행 [" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + "]");
		if(CommandConst.DEBUG) {
			this.getInfo();
		}
		else {
			this.getRealTimedata();
		}
	}

	@Override
	public void stopTimer(TimerStopType type) {
		// 타이머 종료
		System.out.println(this.name + " 종료 : " + type);
	}
}
