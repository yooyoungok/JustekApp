package justek.ide.model;

import justek.ide.model.listener.RealTimeEventListener;
import justek.ide.model.manager.ErrorLogManager;
import justek.ide.model.manager.NetworkServerManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import com.supinan.util.timer.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RealTimeThread extends Timer{

	static final String Tag = "RealTimeThread";
	
	private ArrayList<String> resultList;
	public String numberDriveNo;
	private RealTimeEventListener listener;
	public long mThreadId;

	double preValue=0;
	double curValue = 0;


	private  static RealTimeThread instance;

	/**
	 * @author : YOO YOUNGOK 
	 * @method  getInstance
	 * @param repeatPeriod
	 * @param numberDriveNo
	 * @return  RealTimeThread
	 * @exception 
	 * @see
	 * @since 2018. 7. 5.
	 */
	public static RealTimeThread getInstance (long repeatPeriod,String numberDriveNo) {
		if(instance==null) {
			instance = new RealTimeThread(repeatPeriod,numberDriveNo);
		}
		return instance;
	}

	/**
	 * @author : YOO YOUNGOK 
	 * @method  getInstance
	 * @param repeatPeriod
	 * @return  RealTimeThread
	 * @exception 
	 * @see
	 * @since 2018. 7. 5.
	 */
	public static RealTimeThread getInstance (long repeatPeriod) {
		if(instance==null) {
			instance = new RealTimeThread(repeatPeriod);
		}
		return instance;
	}
	
	public RealTimeThread(long repeatPeriod,String numberDriveNo) {
		super(repeatPeriod);
		this.numberDriveNo = numberDriveNo;
	}

	public RealTimeThread(long repeatPeriod) {
		super(repeatPeriod);
	}

	public void addRealTimeListener(RealTimeEventListener listener) {
		this.listener = listener;
	}

	/**
	 * 개발자가 local로 테스트하기 위한 method -> 추후 삭제예정임...
	 * @author : YOO YOUNGOK 
	 * @method  getInfo
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 7. 5.
	 */
	public void getInfo() {
		RealTimeInfo info = new RealTimeInfo();

		File file = new File("data.txt");
		BufferedReader br;
		FileReader fr = null; 
		String line =null;

		resultList = new ArrayList<>();
		info = new RealTimeInfo();

		try{
			fr = new FileReader(file); 
			br = new BufferedReader(fr);
			while((line = br.readLine()) != null){
				parsingData(line);
			}

			
			info.DriverNo = String.valueOf(CommandConst.DRIVER_NUMBEER);
			this.setRealTimeInfo(info);

		}catch(IOException e) {
//			System.out.println("Thread Exception Stop getInfo :: Thread#1");
			ErrorLogManager.getInstance().addErrorLog(Tag,"getInfo",e);
		}
	}



	/**
	 * @author : YOO YOUNGOK 
	 * @method  getRealTimedata
	 * @param driverNo
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 7. 5.
	 */
	public void getRealTimedata(String driverNo) {
		RealTimeInfo info = new RealTimeInfo();

		Runtime runtime = Runtime.getRuntime();
		try {
			//			System.out.println("getRealTimedata == "+"./shm_helper1 s"+driverNo);

			Process process = runtime.exec("./shm_helper1 s"+driverNo);
			String szLine; 
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));		
			resultList = new ArrayList<>();
			while ((szLine = br.readLine()) != null) {
				//				System.out.println(szLine);
				this.parsingData(szLine);
			}

			info.DriverNo = driverNo;
			setRealTimeInfo(info);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ErrorLogManager.getInstance().addErrorLog(Tag,"getRealTimedata",e);
		}	
	}

	//현재 사용중... 
	public void getRealdata(String num) throws InterruptedException {

		RealTimeInfo info = new RealTimeInfo();
//		System.out.println(Tag+" :: "+num);
		
		ObservableList<String> commandList = FXCollections.observableArrayList();
		int newNum = Integer.valueOf(num)-1; // 0부터 처리하므로 -1을 해야함... 
		String command0 = CommandConst.GET_DRIVER_STATUS.replace("Driver", String.valueOf(num));
		String command1 = CommandConst.GET_DRIVER_ACTUAL_POSITION.replace("Driver", String.valueOf(newNum));
		String command2 = CommandConst.GET_DRIVER_TARGET_POSITION.replace("Driver", String.valueOf(newNum));
		String command3 = CommandConst.GET_DRIVER_ACTUAL_VELOCITY.replace("Driver", String.valueOf(num));
		commandList.add(command0);
		commandList.add(command1);
		commandList.add(command2);
		commandList.add(command3);

		ObservableList<String> resultList =NetworkServerManager.getDriverStaus(commandList);

		//모든 정보가 입력되어 있으면 실시간 정보를 보내준다.
		if(resultList.size()==4) {
			double value = 0;
			double accPos = Double.parseDouble(resultList.get(1));
			double tarPos = Double.parseDouble(resultList.get(2));
			value  = accPos-tarPos;

			info.StausWords = resultList.get(0);
			info.ActualPosition = resultList.get(1);
			info.TargetPosition = resultList.get(2);
			info.ActualVelocity = resultList.get(3);
			info.PosionError= String.valueOf(value);
			
			info.DriverNo = num;

			if(this.listener!=null) {
//				System.out.println("========================"+info.DriverNo+"========================");
				this.listener.realTimeDataInfoEvent(info);
			}			
			else {
				System.out.println("======================== listener null "+info.DriverNo+"========================");
				
			}
		}
		else {
			System.out.println(Tag+"======================Error Size "+resultList.size()+"========================");
		}
	}

	//현재 사용중... 
	public void getAllRealTimeData() {

		if(!NetworkServerManager.checkNetworkConnection())  { return  ;}
		
//		System.out.println("getAllRealTimeData"+CommandConst.driverList.size());
		
		for(int i=1;i <= CommandConst.driverList.size();i++) {
//			System.out.println(" ##### getAllRealTimeData :: "+String.valueOf(i)+"#####");
			this.numberDriveNo = String.valueOf(i);
			
			try {
				this.getRealdata( String.valueOf(i));
			} catch (InterruptedException e) {
				ErrorLogManager.getInstance().addErrorLog(Tag,"getAllRealTimeData",e);
			}
		}
	}

	public void parsingData(String readData) {
		String[] parsingList;

		if(readData!=null) {
			//			System.out.println(readData);
			parsingList = readData.split(":");
			if(parsingList.length==2) {
				this.resultList.add(parsingList[1]);
			}
		}
	}

	public void setRealTimeInfo(RealTimeInfo info) {
		double value = 0;
		double accPos = Double.parseDouble(this.resultList.get(2));
		double tarPos = Double.parseDouble(this.resultList.get(1));
		value  = accPos-tarPos;

		if(info!=null) {
			info.StausWords = this.resultList.get(0);
			info.PosionError = String.valueOf(value);
			info.ActualVelocity = this.resultList.get(3);
			info.ActualPosition = this.resultList.get(2);
			info.TargetPosition = this.resultList.get(1);
		}

		if(this.listener!=null) {
//			System.out.println("Thread Exception Stop getInfo ::this.listener!=null");
			this.listener.realTimeDataInfoEvent(info);
		}
	}

	@Override
	public void execute() {
//		
//		if(CommandConst.DEBUG) {
//			this.getInfo();
//		}
//		else {
		// 타이머 실행
		//		Calendar cal = Calendar.getInstance();
		//		System.out.println( "RealTimeThread 실행 [" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + "]");
			this.getAllRealTimeData(); // 드라이버 전체의 실시간 상태정보를 가져온다.
//		}
	}

	@Override
	public void stopTimer(TimerStopType type) {
		// 타이머 종료
		System.out.println( "RealTimeThread 종료 : " + type);
	}
}
