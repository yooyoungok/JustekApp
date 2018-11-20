package justek.ide.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.NumberFormat;

import com.supinan.util.timer.SupinanTimer;

import justek.ide.model.listener.TriggerEventListener;

public class GraphTriggerData implements TriggerEventListener {

	static final String Tag = "GraphTriggerData";
	
	private static GraphTriggerData instance;
	private TriggerInfo triggerInfo;
	private int dirNo;
	private int fileCount=0;
	private String fileName;
	TriggerEventListener listener;
	double pre_value=0;
	double cur_value=0;
	public SupinanTimer timer;
	TriggerChartThread thread;
	long threadId;
	
	public static GraphTriggerData getInstance () {
		if(instance==null) {
			instance = new GraphTriggerData();
		}
		return instance;

	}

	public void setTriggerInfo(TriggerInfo info,int dirNo,TriggerEventListener listener) {
		this.triggerInfo = info;
		this.dirNo = dirNo-1;
		this.listener = listener;
	}
	
	public void checkFile() {
		
		if(CommandConst.DEBUG) return;
		
//		File folder = new File("./motionware/motion_server/pdata/");
		File folder = new File("./pdata/");
		for (File f : folder.listFiles()) {
		        f.delete();
		}
	}
	
	public void runLogProcess() {
		if(!CommandConst.DEBUG) {

			timer = new SupinanTimer();
			thread = TriggerChartThread.getInstance(5000,"Trigger",String.valueOf(this.dirNo));
			thread.fileCount=0;
			thread.addListener(this);
			thread.setTriggerInfo(this.triggerInfo);
			this.threadId = timer.addTimer(thread);
			
			this.execTrigger();
			
			Runtime runtime = Runtime.getRuntime();
			try {
				Process process = runtime.exec("/home/jscs1/justek_motionware/motionware/motion_server/log_proc2");
//				Process process = runtime.exec("ls -lt");
//				BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		else {
			System.out.println(Tag +" :: runLogProcess Debug Mode");
			timer = new SupinanTimer();
			thread = TriggerChartThread.getInstance(5000,"Trigger",String.valueOf(this.dirNo));
			thread.fileCount=0;
			thread.addListener(this);
			thread.setTriggerInfo(this.triggerInfo);
			this.threadId = timer.addTimer(thread);
		}
	}
	
	public void execTrigger() {
		try {
			Socket socketClient = new Socket(CommandConst.address, 12345);
			System.out.println("Client: " + " showInitialSpeed :: Connection Established");

			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			
			String serverMsg;
	        NumberFormat nf = NumberFormat.getInstance();
	        nf.setMaximumFractionDigits(2) ;
			double reTime = Double.valueOf(this.triggerInfo.getRecordTime());
			double resolution =  Double.valueOf(this.triggerInfo.getResolution());
			
//			String param = nf.format(reTime);
//			String sendSpeedGet = "i81="+param +".0\r\n";
//			
//			System.out.print(sendSpeedGet);
//			
//			writer.write(sendSpeedGet);
//			param = nf.format(resolution);
//			String sendSpeedGet2 = "i82="+param +".0\r\n";
//			writer.write(sendSpeedGet2);
			String sendSpeedGet3 = "i80=1.0\r\n";
			writer.write(sendSpeedGet3);
			
			writer.flush();
			socketClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void runLogProcess(String recordTime,String resolution) {
		if(!CommandConst.DEBUG) {

			timer = new SupinanTimer();
			thread = TriggerChartThread.getInstance(5000,"Trigger",String.valueOf(this.dirNo));
			thread.fileCount=0;
			thread.addListener(this);
			thread.setTriggerInfo(this.triggerInfo);
			this.threadId = timer.addTimer(thread);
			
			Runtime runtime = Runtime.getRuntime();
			try {
				Process process = runtime.exec("/home/jscs1/justek_motionware/motionware/motion_server/log_proc "+recordTime+" 2");
//				BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
	
	public boolean checkOutFileExists(String fileName) {
		this.fileCount = this.thread.fileCount-1;
		this.fileName = fileName;
		boolean result = false;
		File file = new File(fileName);
		if(file.exists()) {
			result = true;
		}
		return result;
	}
	
	public void checkChartFile() {
		
		File mfile = new File("./pdata/2.out");
		try {
			File file = new File("./home/log_data_all.out");
			BufferedReader br;
			FileReader fr = null; 
			BufferedWriter out;
			if(mfile.exists()) {
				
				if(file.exists()) {
					file.delete();
				}
				
				file = new File("./home/log_data_all.out");
				String line;
				out = new BufferedWriter(new FileWriter(file));
				
				mfile = new File("./pdata/0.out");
				if(mfile.exists()) {
					fr = new FileReader(mfile); 
					br = new BufferedReader(fr);
					while((line = br.readLine()) != null){
						out.write(line+"\n");
					}
				}
				
				mfile = new File("./pdata/1.out");
				if(mfile.exists()) {
					fr = new FileReader(mfile); 
					br = new BufferedReader(fr);
					while((line = br.readLine()) != null){
						out.write(line+"\n");
					}
				}

				mfile = new File("./pdata/2.out");
				if(mfile.exists()) {
					fr = new FileReader(mfile); 
					br = new BufferedReader(fr);
					while((line = br.readLine()) != null){
						out.write(line+"\n");
					}
				}
				
				mfile = new File("./pdata/3.out");
				if(mfile.exists()) {
					fr = new FileReader(mfile); 
					br = new BufferedReader(fr);
					while((line = br.readLine()) != null){
						out.write(line+"\n");
					}
				}
				
				mfile = new File("./pdata/4.out");
				if(mfile.exists()) {
					fr = new FileReader(mfile); 
					br = new BufferedReader(fr);
					while((line = br.readLine()) != null){
						out.write(line+"\n");
					}
				}
				
				mfile = new File("./pdata/5.out");
				if(mfile.exists()) {
					fr = new FileReader(mfile); 
					br = new BufferedReader(fr);
					while((line = br.readLine()) != null){
						out.write(line+"\n");
					}
				}
				
//				mfile = new File("./pdata/6.out");
//				fr = new FileReader(mfile); 
//				br = new BufferedReader(fr);
//
//				while((line = br.readLine()) != null){
//					out.write(line+"\n");
//				}
//				
//				mfile = new File("./pdata/7.out");
//				fr = new FileReader(mfile); 
//				br = new BufferedReader(fr);
//
//				while((line = br.readLine()) != null){
//					out.write(line+"\n");
//				}
//				
//				mfile = new File("./pdata/8.out");
//				fr = new FileReader(mfile); 
//				br = new BufferedReader(fr);
//
//				while((line = br.readLine()) != null){
//					out.write(line+"\n");
//				}
				
				fr.close();
				out.close();
			}
			//				Runtime runtime = Runtime.getRuntime();
			//				String command = "cat log_data_0.out log_data_2.out log_data_3.out log_data_4.out log_data_5.out log_data_6.out log_data_7.out log_data_8.out log_data_9.out > log_data_all.out ";
			//				command = "cp log_data_9.out  ./home/log_data_all.out";
			////				command = "cat log_data_0.out log_data_2.out log_data_3.out > log_data_all.out";
			//				Process catProcess = runtime.exec(command);
			this.checkTrigger();
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean checkTrigger() {
		boolean result = false;
		System.out.println("checkTrigger");

		String slope =this.triggerInfo.getSlope();
		double level = Double.parseDouble(this.triggerInfo.getLevel());
		
		int offset =2;
		if (this.triggerInfo.getSource().equals("ActualVelocity")) {
			offset =6;
		}
		else if (this.triggerInfo.getSource().equals("ActualPosition")) {
			offset =5;
		}
		else if (this.triggerInfo.getSource().equals("TargetPosition")) {
			offset =2;
		}
		else if (this.triggerInfo.getSource().equals("TargetVelocity")) {
			offset =3;
		}
		else if (this.triggerInfo.getSource().equals("TargetAcceleration")) {
			offset =4;
		}
	
		double a1=0;
		double a2=0;
		File mfile = new File(this.fileName);
//		File mfile = new File("./home/log_data_all.out");
		if(mfile.exists()) {
	
			System.out.println("checkTrigger file exsits");
			String line;
			String[] parsingList;
			
			try {
				
				FileReader fr = new FileReader(mfile);
				BufferedReader reader = new BufferedReader(fr);
				
				double target_pos = 0;
				double actual_pos = 0;
				double actual_vel = 0;
				double pre_target_pos=0 ;
				double pre_actual_pos =0;
				double pre_actual_vel =0;
				double pre_errPos=0;
				double cur_errPos=0;
				
				int record_count=0;
				while((line = reader.readLine()) != null){
					parsingList = line.split(" ");
					System.out.println("checkTrigger"+line);
					
					record_count++;
					if(Integer.parseInt(parsingList[0])==dirNo) {
//						pre_target_pos = Double.parseDouble(parsingList[2]);
//						pre_actual_pos =  Double.parseDouble(parsingList[3]);
//						pre_actual_vel =  Double.parseDouble(parsingList[4]);
						pre_value=Double.parseDouble(parsingList[offset]);
						pre_errPos = Double.parseDouble(parsingList[5])-Double.parseDouble(parsingList[2]);
						break;
					}
				}
					
				while((line = reader.readLine()) != null){
//					System.out.println("checkTrigger 2=="+line);
					parsingList = line.split(" ");
					record_count++;

					if(Integer.parseInt(parsingList[0])==dirNo) {
						//						target_pos =Double.parseDouble(parsingList[2]);
						//						actual_pos = Double.parseDouble(parsingList[3]);
						//						actual_vel =  Double.parseDouble(parsingList[4]);
						cur_errPos = Double.parseDouble(parsingList[5])-Double.parseDouble(parsingList[2]);
						cur_value = Double.parseDouble(parsingList[offset]);
//						System.out.println("Trigger Event Occured_1 pre_value =="+pre_value+" cur_value =="+cur_value);
						
//						if(true) break;
						
						if(this.triggerInfo.getSource().equals("PositionError")){
							if(slope.equals(CommandConst.POSITIVE_EDGE) && pre_errPos<level && cur_errPos>level || 
									slope.equals(CommandConst.NEGATIVE_EDGE) && pre_errPos>level && cur_errPos<level) {
								System.out.println("Trigger Event Occured_2 pre_errPos =="+pre_errPos+" cur_errPos =="+cur_errPos);
								this.readData(this.dirNo, record_count,fileName,offset);
								result = true;
								break;
							}
							else {
								pre_errPos=cur_errPos;
							}
						}
						else { 
							if(slope.equals(CommandConst.POSITIVE_EDGE) && pre_value<level && cur_value>level || 
									slope.equals(CommandConst.NEGATIVE_EDGE) && pre_value>level && cur_value<level) {
								System.out.println("Trigger Event Occured_2 pre_value =="+pre_value+" cur_value =="+cur_value);
								this.readData(this.dirNo, record_count,fileName,offset);
								result = true;
								break;
							}
							else {
								pre_value=cur_value;
							}
						}
					}
				}

				fr.close();
//				this.saveData(this.dirNo, record_count);
				
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		else {
			System.out.println("checkTrigger file not exsits");
		}
		
		return result;
	}
	
	public void readData(int dirNo, int record_count,String fileName,int offSet) {
		System.out.println("GraphTriggerData File Save");
		int bf_fileCount = this.fileCount-1;
		int af_fileCcount = this.fileCount+1;
		
		int record_cnt;;  // trigger start record count
		int resolution= Integer.parseInt(this.triggerInfo.getResolution());
		int duration = Integer.parseInt(this.triggerInfo.getRecordTime());
		int delay =Integer.parseInt(this.triggerInfo.getDelay());// delay
		int record_per_file = duration * 1000 / resolution * 3;
		
		int bf_count=0;
		int af_count=0;
		int start_count= record_count -  delay/resolution*3;
		int end_count = start_count + duration *1000 /resolution * 3  ;
		
		System.out.println("GraphTriggerData =start_count "+start_count);
		System.out.println("GraphTriggerData =resolution "+resolution);
		System.out.println("GraphTriggerData =duration "+duration);
		System.out.println("GraphTriggerData =delay "+delay);
		System.out.println("GraphTriggerData =record_per_file "+record_per_file);
		
		//
		if(start_count < 0) {
			bf_count=record_per_file+start_count;
			start_count=0;
		} 
		else {
			bf_count = 0;
		}
		
		if(end_count < record_per_file) {
			af_count = 0;
		}
		else {
			af_count = end_count - record_per_file;
			end_count = record_per_file;
		}

//		String bf_file = "./motionware/motion_server/pdata/"+bf_fileCount+".out";
//		String af_file = "./motionware/motion_server/pdata/"+af_fileCcount+".out";
		String bf_file = "./pdata/"+bf_fileCount+".out";
		String af_file = "./pdata/"+af_fileCcount+".out";
		
		BufferedReader br;
		FileReader fr = null; 
		String line =null;
		
//		Acceleration","PositionError","TargetPosition", "ActualPosition","TargetVelocity","ActualVelocity
		File actual_pos_file = new File("./home/"+CommandConst.chartList.get(3)+".txt");
		if(actual_pos_file.exists()) actual_pos_file.delete();
		
		File target_pos_file = new File("./home/"+CommandConst.chartList.get(2)+".txt");
		File actual_vel_file = new File("./home/"+CommandConst.chartList.get(5)+".txt");
		File err_pos_file = new File("./home/"+CommandConst.chartList.get(1)+".txt");
		File target_vel_file = new File("./home/"+CommandConst.chartList.get(4)+".txt");
		File target_acc_file = new File("./home/"+CommandConst.chartList.get(0)+".txt");
		
		double target_pos = 0;
		double actual_pos = 0;
		double actual_vel = 0;
		double target_vel = 0;
		double target_acc = 0;
		double err_pos=0;
		
		String[] parsingList;

		try {
		
		BufferedWriter target_pos_out  = new BufferedWriter(new FileWriter(target_pos_file));
		BufferedWriter actual_pos_out = new BufferedWriter(new FileWriter(actual_pos_file));
		BufferedWriter actual_vel_out = new BufferedWriter(new FileWriter(actual_vel_file));
		BufferedWriter err_pos_out = new BufferedWriter(new FileWriter(err_pos_file));
		BufferedWriter target_vel_out = new BufferedWriter(new FileWriter(target_vel_file));
		BufferedWriter target_acc_out = new BufferedWriter(new FileWriter(target_acc_file));
		
		if(bf_count > 0) {
			File file = new File(bf_file);
			if(file.exists()) {
				fr = new FileReader(file); 
				br = new BufferedReader(fr);

				for(int i=0; i<bf_count; i++) {
					if((line = br.readLine()) == null){
						break;
					}
				}

				for(int i=bf_count; i<=record_per_file;i++) {
					if((line = br.readLine()) != null){
						parsingList = line.split(" ");
						if(Integer.parseInt(parsingList[0])==dirNo) {
							target_pos =Double.parseDouble(parsingList[2]);
							actual_pos = Double.parseDouble(parsingList[5]);
							actual_vel =  Double.parseDouble(parsingList[6]);
							target_vel = Double.parseDouble(parsingList[3]);
							target_acc = Double.parseDouble(parsingList[4]);
							err_pos =actual_pos-target_pos;

							target_pos_out.write(Double.toString(target_pos)+"\n");
							actual_pos_out.write(Double.toString(actual_pos)+"\n");
							actual_vel_out.write(Double.toString(actual_vel)+"\n");
							target_vel_out.write(Double.toString(target_vel)+"\n");
							target_acc_out.write(Double.toString(target_acc)+"\n");
							err_pos_out.write(Double.toString(err_pos)+"\n");
						}
					}
				}
			}
		}

		if(start_count>0) {
			File file = new File(this.fileName);
			if(file.exists()) {
				fr = new FileReader(file); 
				br = new BufferedReader(fr);
				for(int i=0; i<start_count; i++) {
					if((line = br.readLine()) == null){
						break;
					}
				}
				for(int i=start_count; i<=end_count;i++) {
					if((line = br.readLine()) != null){
						parsingList = line.split(" ");
						if(Integer.parseInt(parsingList[0])==dirNo) {
							target_pos =Double.parseDouble(parsingList[2]);
							actual_pos = Double.parseDouble(parsingList[5]);
							actual_vel =  Double.parseDouble(parsingList[6]);
							target_vel = Double.parseDouble(parsingList[3]);
							target_acc = Double.parseDouble(parsingList[4]);
							err_pos =actual_pos-target_pos;

							target_pos_out.write(Double.toString(target_pos)+"\n");
							actual_pos_out.write(Double.toString(actual_pos)+"\n");
							actual_vel_out.write(Double.toString(actual_vel)+"\n");
							target_vel_out.write(Double.toString(target_vel)+"\n");
							target_acc_out.write(Double.toString(target_acc)+"\n");
							err_pos_out.write(Double.toString(err_pos)+"\n");
						}
					}
				}
			}
		}

		if(af_count>0) {
			File file = new File(af_file);
			if(file.exists()) {
				fr = new FileReader(file); 
				br = new BufferedReader(fr);
				
				for(int i=0; i<=af_count;i++) {
					if((line = br.readLine()) != null){
						parsingList = line.split(" ");
						
						if(Integer.parseInt(parsingList[0])==dirNo) {
							target_pos =Double.parseDouble(parsingList[2]);
							actual_pos = Double.parseDouble(parsingList[5]);
							actual_vel =  Double.parseDouble(parsingList[6]);
							target_vel = Double.parseDouble(parsingList[3]);
							target_acc = Double.parseDouble(parsingList[4]);
							err_pos =actual_pos-target_pos;

							target_pos_out.write(Double.toString(target_pos)+"\n");
							actual_pos_out.write(Double.toString(actual_pos)+"\n");
							actual_vel_out.write(Double.toString(actual_vel)+"\n");
							target_vel_out.write(Double.toString(target_vel)+"\n");
							target_acc_out.write(Double.toString(target_acc)+"\n");
							err_pos_out.write(Double.toString(err_pos)+"\n");
						}
					}
				}
			}
		}
		
		target_pos_out.close();
		actual_pos_out.close();
		actual_vel_out.close();
		target_acc_out.close();
		target_vel_out.close();
		err_pos_out.close();
		fr.close();
		
		if(this.listener!=null) {
			this.listener.SingleTriggerEvent(this.triggerInfo.getSource());
		}
		
		}catch(IOException e) {
			e.printStackTrace(); 
		}
	}
	
//	public void saveData(String fileName, String line) {
////		File mfile = new File("./home/log_data_all.out");
//		
//		File mfile = new File(fileName);
//		BufferedReader br;
//		FileReader fr = null; 
//		String line =null;
//
////		Acceleration","PositionError","TargetPosition", "ActualPosition","TargetVelocity","ActualVelocity
//		File actual_pos_file = new File("./home/"+CommandConst.chartList.get(3)+".txt");
//		File target_pos_file = new File("./home/"+CommandConst.chartList.get(2)+".txt");
//		File actual_vel_file = new File("./home/"+CommandConst.chartList.get(5)+".txt");
//		File err_pos_file = new File("./home/"+CommandConst.chartList.get(1)+".txt");
//
//		BufferedWriter target_pos_out;
//		BufferedWriter actual_pos_out;
//		BufferedWriter actual_vel_out;
//		BufferedWriter err_pos_out;
//		
//		if(mfile.exists()) {
//			System.out.println("File exists == "+fileName);
//			try{
//				fr = new FileReader(mfile); 
//				br = new BufferedReader(fr);
//
//				target_pos_out = new BufferedWriter(new FileWriter(target_pos_file));
//				actual_pos_out = new BufferedWriter(new FileWriter(actual_pos_file));
//				actual_vel_out = new BufferedWriter(new FileWriter(actual_vel_file));
//				err_pos_out = new BufferedWriter(new FileWriter(err_pos_file));
//
//				double target_pos = 0;
//				double actual_pos = 0;
//				double actual_vel = 0;
//				double err_pos=0;
//				String[] parsingList;
//
//				for(int i=0;i<start_count;i++) {
//					if((line = br.readLine()) == null){
//						break;
//					}
//				}
//
//				int j = 0;
//				while((line = br.readLine()) != null){
//					parsingList = line.split(" ");
//
//					if(Integer.parseInt(parsingList[0])==dirNo) {
//						target_pos =Double.parseDouble(parsingList[2]);
//						actual_pos = Double.parseDouble(parsingList[3]);
//						actual_vel =  Double.parseDouble(parsingList[4]);
//						err_pos =actual_pos-target_pos;
//						
//						target_pos_out.write(Double.toString(target_pos)+"\n");
//						actual_pos_out.write(Double.toString(actual_pos)+"\n");
//						actual_vel_out.write(Double.toString(actual_vel)+"\n");
//						err_pos_out.write(Double.toString(err_pos)+"\n");
//						
//						if(j==2500) break;
//						j++;
//					}
//				}
//				target_pos_out.close();
//				actual_pos_out.close();
//				actual_vel_out.close();
//				err_pos_out.close();
//				fr.close();
//				
//				if(this.listener!=null) {
//					this.listener.SingleTriggerEvent(this.triggerInfo.getSource());
//				}
//				
//			}catch(IOException e) {
//				e.printStackTrace(); 
//			}
//		}
//		else {
//			System.out.println("log_data_ Not exsits");
//		}
//	}
	

	@Override
	public void NormalTriggerEvent(String Source) {
		// TODO Auto-generated method stub
		boolean result = this.checkTrigger();
	}

	@Override
	public void SingleTriggerEvent(String Source) {
		// TODO Auto-generated method stub
		System.out.println("ChartTriggerData SingleTriggerEvent");
		this.timer.removeTimer(this.threadId);
		boolean result = this.checkTrigger();
		if(!result) {
			if(this.thread.fileCount<10) {
				System.out.println("ChartTriggerData SingleTriggerEvent FileCount ##"+this.thread.fileCount);
				this.threadId = this.timer.addTimer(this.thread);
			}
		}
	}
}
