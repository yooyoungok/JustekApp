package justek.ide.model.manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import com.supinan.util.timer.Timer;
import com.supinan.util.timer.TimerStopType;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import justek.ide.MainApp;
import justek.ide.dao.MemberDAO;
import justek.ide.model.CommandConst;
import justek.ide.model.SDOInfo;
import justek.ide.utils.StringUtil;


public class NetworkServerManager {

	static final String Tag = "NetworkServerManager";

	private static NetworkServerManager instance;

	private static Socket realTimeSocket;

	/**
	 * @author : YOO YOUNGOK 
	 * @method  getInstance
	 * @return
	 * @return  NetworkServerManager
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public static NetworkServerManager getInstance () {
		if(instance==null) {
			instance = new NetworkServerManager();
		}

		return instance;
	}

	public static boolean openSocket() {
		boolean result = false;
		try {
			if(realTimeSocket!=null) result = true;
			else {
				realTimeSocket = new Socket(CommandConst.address, 12345);
				result = true;
			}
		} catch (UnknownHostException e) {
			//			e.printStackTrace();
			ErrorLogManager.getInstance().addErrorLog(Tag,"openSocket",e);
		} catch (IOException e) {
			//			e.printStackTrace();
			ErrorLogManager.getInstance().addErrorLog(Tag,"openSocket",e);
		}

		return result;
	}


	/**
	 * �ǽð� ������ ��û�ϴ� session�� �����Ѵ�.
	 * @author : YOO YOUNGOK 
	 * @method  closeSocket
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 28.
	 */
	public static void closeSocket() {
		try {
			if(realTimeSocket!=null) {
				realTimeSocket.close();
				realTimeSocket = null;

			}
			else {
				System.out.println("Socket is Null");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ErrorLogManager.getInstance().addErrorLog(Tag,"closeSocket",e);
		}
	}
	/**
	 * @author : YOO YOUNGOK 
	 * @method  firmwareDownload
	 * @param DriverNo
	 * @param fileName
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public static void firmwareDownload(String DriverNo,String fileName) {
		System.out.println("Client: NetworkServer :: firmwareDownload ==" +fileName);
		if(!checkSocket()) return;

		String command = "ethercat foe_write -p "+DriverNo+" "+fileName;
		try {
			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(command+"\r\n");
			writer.flush();

			//MemberDAO�� insertLog()�� ȣ���Ͽ� DB�� �����Ѵ�.
			MemberDAO.getInstance().insertLog(command);
			socketClient.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	/**
	 * @author : YOO YOUNGOK 
	 * @method  runPlcFile
	 * @param runString //������ PLC ���ϸ�
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public void runPlcFile(String runString) {
		int dot = runString.indexOf('.');
		String runString2 = runString.substring(0, dot);
		System.out.println("Client: NetworkServer :: runPlcFile ==" +runString2);
		if(!checkNetworkConnection())  { return ;}
		if(!checkSocket()) return;
		
		if(this.checkPlcFile(runString)) {
			DialogManager.getInstance().showServerErrorConfirmDialog("���� �ش� PLC�� �������Դϴ�.");
			return;
		}
		
		String command = "enable plc "+runString2;

		try {
			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(command+"\r\n");
			writer.flush();
			socketClient.close();
			//MemberDAO�� insertLog()�� ȣ���Ͽ� DB�� �����Ѵ�.
			MemberDAO.getInstance().insertLog(command);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author : YOO YOUNGOK 
	 * @method  stopPlcFile
	 * @param runString //stop�� ������ PLC ���ϸ�
	 * @return  void
	 * @exception SQLException / IOException
	 * @see
	 * @since 2018. 6. 21.
	 */
	public static void stopPlcFile(String runString) {
		int dot = runString.indexOf('.');
		String runString2 = runString.substring(0, dot);
		System.out.println("Client: NetworkServer :: stopPlcFile ==" +runString2);

		if(!checkNetworkConnection())  { return ;}
		if(!checkSocket()) return;

		String command = "disable plc "+runString2;
		try {
			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(command+"\r\n");
			writer.flush();
			//MemberDAO�� insertLog()�� ȣ���Ͽ� DB�� �����Ѵ�.
			MemberDAO.getInstance().insertLog(command);

			socketClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author : YOO YOUNGOK 
	 * @method  checkPlcFile
	 * @param plcName
	 * @return  boolean
	 * @exception 
	 * @desc  Ư�� plc ���� ���¸� Ȯ���Ѵ�.
	 * @since 2018. 8. 27.
	 */
	public boolean checkPlcFile(String plcName) {

		boolean result = false;

		if(CommandConst.DEBUG) return true;
		
		if(!checkNetworkConnection())  { return result;}
		if(!checkSocket()) return result;

		String serverMsg = null;
		String command = "ps";

		try {
			//MemberDAO�� insertLog()�� ȣ���Ͽ� DB�� �����Ѵ�.
			MemberDAO.getInstance().insertLog(command);

			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

			writer.write(command + "\r\n");
			writer.flush();

			serverMsg = reader.readLine();		
			serverMsg = reader.readLine(); // ù����� �ι���� Ȯ������ �ʾƵ� ��...		
			serverMsg = reader.readLine();
			System.out.println(Tag+" : reponse ="+serverMsg);		
			serverMsg = reader.readLine();
			
			while (!(serverMsg = reader.readLine()).isEmpty()) {
				System.out.println(Tag+" : reponse ="+serverMsg);
				if(serverMsg.contains(plcName)) {
					result = true;
					break;
				}
			}

			socketClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * @author : YOO YOUNGOK 
	 * @method  checkAllPlcFile
	 * @return  ObservableList<String>
	 * @exception 
	 * @desc ��� plc�� ���� ���θ� üũ�Ѵ�.
	 * @since 2018. 8. 27.
	 */
	public ObservableList<String> checkAllPlcFile() {
		
		ObservableList<String> resultList = FXCollections.observableArrayList();
		
//		if(CommandConst.DEBUG) {
////			resultList.add("/10.plc R");
////			resultList.add("/11.plc R");
////			resultList.add("/31.plc R");
//			return null;
//		}
		
		if(!checkNetworkConnection())  { return null ;}
		if(!checkSocket()) return null ;

		String serverMsg = null;
		String command = "ps";

		try {
			//MemberDAO�� insertLog()�� ȣ���Ͽ� DB�� �����Ѵ�.
			MemberDAO.getInstance().insertLog(command);

			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			writer.write(command+"\r\n");
			writer.flush();

			serverMsg = reader.readLine();		
			serverMsg = reader.readLine(); // ù����� �ι���� Ȯ������ �ʾƵ� ��...		
			serverMsg = reader.readLine();	
			System.out.println(Tag+" : reponse :: "+serverMsg);
			
			serverMsg = reader.readLine();	

			while (!(serverMsg = reader.readLine()).isEmpty()) {
				System.out.println(Tag+" : reponse ="+serverMsg);
				resultList.add(serverMsg);
			}

			socketClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;
	}


	/**
	 * @author : YOO YOUNGOK 
	 * @method  uploadData
	 * @param command  //Runtime�� ���� ������ ���α׷� ��ɾ�
	 * @return
	 * @return  String 
	 * @exception SQLException / IOException
	 * @see
	 * @since 2018. 6. 21.
	 */
	public static String uploadData(String command) {

		if(!checkNetworkConnection())  { return "error" ;}

		if(CommandConst.isWindow) {
			//MemberDAO�� insertLog()�� ȣ���Ͽ� DB�� �����Ѵ�.
			try {
				MemberDAO.getInstance().insertLog(command);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "error";
		}

		String result = "Fail";

		Runtime runtime = Runtime.getRuntime();
		try {

			//MemberDAO�� insertLog()�� ȣ���Ͽ� DB�� �����Ѵ�.
			MemberDAO.getInstance().insertLog(command);

			Process process = runtime.exec(command);
			String szLine; 
			String[] array;
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			if ((szLine = br.readLine()) != null) {
				System.out.println(szLine);
				array = szLine.split(" ");

				if(array.length!=2) {
					result = "No Data";
				}
				else {
					result = array[1];
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * @author : YOO YOUNGOK 
	 * @method  downloadData
	 * @param command
	 * @return  void
	 * @exception  IOException/SQLException
	 * @see
	 * @since 2018. 6. 21.
	 */
	public void downloadData(String command) {

		System.out.println("downloadData :: "+command);
		if(!checkNetworkConnection())  { return  ;}

		if(CommandConst.isWindow) {
			return ;
		}

		Runtime runtime = Runtime.getRuntime();
		try {
			//MemberDAO�� insertLog()�� ȣ���Ͽ� ���� Log�� DB�� �����Ѵ�.
			MemberDAO.getInstance().insertLog(command);
			runtime.exec(command);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getCommandData(String command) {
		System.out.println("getCommandData :: "+command);
		Runtime runtime = Runtime.getRuntime();
		String result = "";

		try {
			Process process = runtime.exec(command);
			String szLine; 
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));		
			while ((szLine = br.readLine()) != null) {
				System.out.println(szLine);
				result = szLine;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ErrorLogManager.getInstance().addErrorLog(Tag,"getCommandData",e);
			result = null;
		}

		return result;
	}

	
	public ObservableList<String> getEtherCATDAta() {
		System.out.println("getEtherCATDAta");
		Runtime runtime = Runtime.getRuntime();
		ObservableList<String> result = FXCollections.observableArrayList();

		try {
			Process process = runtime.exec("ethercat slaves");
			String szLine; 
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			while ((szLine = br.readLine()) != null) {
				System.out.println(szLine);
				if(szLine.contains("SM")) {
					result.add(szLine);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("getEtherCATDAta Error");
			ErrorLogManager.getInstance().addErrorLog(Tag,"getCommandData",e);
			return null;
		}

		return result;
	}
	
	public void changeEtherCATMode(String mode,String dirNum) {
		System.out.println("changeEtherCATMode");
		Runtime runtime = Runtime.getRuntime();

		try {
			Process process = runtime.exec("ethercat states -p "+dirNum+" "+mode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("changeEtherCATMode Error");
			ErrorLogManager.getInstance().addErrorLog(Tag,"getCommandData",e);
		}
	}
	
	/**
	 * numberDriveNo(����ڰ� ������ ����̹� Number)��  JogAccess���� �����´�.
	 * @author : YOO YOUNGOK 
	 * @method  getJogAccess
	 * @param numberDriveNo
	 * @return
	 * @return  String
	 * @exception IOException/SQLException
	 * @see
	 * @since 2018. 6. 21.
	 */
	public static String getJogAccess(String numberDriveNo) {
		System.out.println("Client: " + " getJogAccess :: Connection Established");

		if(!checkNetworkConnection())  { return "Error" ;}

		if(!checkSocket()) { return "Server Error";}

		String serverMsg = null;
		try {
			String command = "i" + numberDriveNo + CommandConst.PROFILER_JOG_ACC;
			//MemberDAO�� insertLog()�� ȣ���Ͽ� ���� Log�� DB�� �����Ѵ�.
			MemberDAO.getInstance().insertLog(command);

			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(command+"\r\n");
			writer.flush();

			reader.readLine();
			if(reader.readLine().equals(command)){
				serverMsg = reader.readLine();
				System.out.println("Client: " + serverMsg);				
			}

			socketClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serverMsg;
	}

	/**
	 * numberDriveNo(����ڰ� ������ ����̹� Number)�� Speed���� �����´�.
	 * @author : YOO YOUNGOK 
	 * @method  getInitialSpeed
	 * @param numberDriveNo
	 * @return
	 * @return  String
	 * @exception IOException/SQLException
	 * @see
	 * @since 2018. 6. 21.
	 */
	public static String getInitialSpeed(String numberDriveNo) {

		System.out.println(Tag+" :: getInitialSpeed :: " + "Connection Established");

		if(!checkNetworkConnection())  { return null;}

		if(!checkSocket()) { return "Server Error";}

		String serverMsg = null;
		try {
			String command = "i" + numberDriveNo + CommandConst.SPEED;
			//MemberDAO�� insertLog()�� ȣ���Ͽ� ���� Log�� DB�� �����Ѵ�.
			MemberDAO.getInstance().insertLog(command);
			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

			writer.write(command + "\r\n");
			writer.flush();
			if(reader.readLine().equals( command)) {
				System.out.println(Tag+" : reponse ="+command);
				serverMsg = reader.readLine();				
			}

			socketClient.close();
			System.out.println(Tag+" :: getInitialSpeed result: " + serverMsg);

		} catch (Exception e) {
			e.printStackTrace();
			DialogManager.getInstance().showServerErrorConfirmDialog(e.getMessage());
		}
		return serverMsg;
	}

	/**
	 * @author : YOO YOUNGOK 
	 * @method  setJogAcceleration
	 * @param numberDriverNo
	 * @param acceleration
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public static void setJogAcceleration(String numberDriverNo,String acceleration) {
		System.out.println(Tag+ " setJogAcceleration =  " +acceleration+":: Connection Established");

		if(!checkNetworkConnection())  { return ;}
		if(!checkSocket()) { return;}

		try {
			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write("i"+numberDriverNo+CommandConst.PROFILER_JOG_ACC+"=" + acceleration + "\r\n");
			writer.flush();
			writer.write("save"+ "\r\n");
			writer.flush();
			socketClient.close();
		} catch (Exception e) {
			DialogManager.getInstance().showServerErrorConfirmDialog(e.getMessage());
		}
	}


	/**
	 * @author : YOO YOUNGOK 
	 * @method  setSpeed
	 * @param numberDriverNo 
	 * @param speed
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public static void setSpeed(String numberDriverNo,String speed) {

		System.out.println(Tag+ " setSpeed =  " +speed+":: Connection Established");
		if(!checkNetworkConnection())  { return ;}
		if(!checkSocket()) { return;}

		try{
			Socket socketClient = new Socket(CommandConst.address, 12345);
			String command = "i"+numberDriverNo+CommandConst.SPEED+"=" + speed;
			//MemberDAO�� insertLog()�� ȣ���Ͽ� ���� Log�� DB�� �����Ѵ�.
			MemberDAO.getInstance().insertLog(command);
			BufferedWriter writer= 
					new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(command + "\r\n");
			writer.flush();
			writer.write("save"+ "\r\n");
			writer.flush();
			socketClient.close();
		}catch(Exception e){e.printStackTrace();}

	}

	/**
	 * numberDriveNo�� �ش��ϴ� ����̹��� ������ Speed���� �����´�.
	 * @author : YOO YOUNGOK 
	 * @method  getInitialAcceleration
	 * @param numberDriveNo
	 * @return
	 * @return  String //numberDriveNo�� speed value
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public static String getInitialAcceleration(String numberDriveNo) {

		System.out.println(Tag+" :: getInitialAcceleration :: " + "Connection Established");

		if(!checkNetworkConnection())  { return null;}

		if(!checkSocket()) { return "Server Error";}
		//		else if(CommandConst.DEBUG) {return "1000";}

		String serverMsg = null;
		try {

			String command = "i" + numberDriveNo + CommandConst.PROFILER_JOG_ACC;

			//MemberDAO�� insertLog()�� ȣ���Ͽ� ���� Log�� DB�� �����Ѵ�.
			MemberDAO.getInstance().insertLog(command);
			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(command+"\r\n");
			writer.flush();

			String reponse = reader.readLine();
			if(reponse.equals( command)) {
				System.out.println(Tag+" : reponse ="+reponse);
				serverMsg = reader.readLine();
				System.out.println(Tag+" :: getInitialAcceleration result: " + serverMsg);
			}

			socketClient.close();
		} catch (Exception e) {
			e.printStackTrace();
			DialogManager.getInstance().showServerErrorConfirmDialog(e.getMessage());
		}
		return serverMsg;
	}

	/**
	 * @author : YOO YOUNGOK 
	 * @method  handleRelativeRepetitive
	 * @param numberDriveNo
	 * @param ptp
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public static void handleRelativeRepetitive(String numberDriveNo,String ptp,String direction) {
		
		System.out.println(Tag+" :: handleRelativeRepetitive :: ptp == "+ptp + " Connection Established");

		try {
			File file = new File("/home/jscs1/linuxcnc/configs/pmac/programs/31.plc");

			if(!file.exists()) {
				file.createNewFile();
			}
			
			BufferedWriter out;
			
			if(file.exists()) {
				System.out.println("file exites");
				out = new BufferedWriter(new FileWriter(file));
				
				String value="";
				String value4 ="";
				if(direction.equals("+")) {
					value = "cmds \"#"+numberDriveNo+"J:"+ptp+"\"";
					value4 = "cmds \"#"+numberDriveNo+"J:-"+ptp+"\"";
				}
				else {
					value = "cmds \"#"+numberDriveNo+"J:-"+ptp+"\"";
					value4 = "cmds \"#"+numberDriveNo+"J:"+ptp+"\"";
				}
				out.write(value);
				out.newLine();
				String vaule1 = "while(h"+numberDriveNo+"10>0)";
				out.write(vaule1);
				out.newLine();
				String vaule2 = "sleep(1000000)";
				out.write(vaule2);
				out.newLine();
				String vaule3 = "endwhile";
				out.write(vaule3);
				out.newLine();
				out.write(value4);
				out.newLine();
				out.write(vaule1);
				out.newLine();
				out.write(vaule2);
				out.newLine();
				out.write(vaule3);
				out.newLine();
				
				out.close();
			}		
		} catch (IOException e1) {
			e1.printStackTrace(); 
		}

		if(CommandConst.DEBUG) return;
		if(!checkNetworkConnection())  { return ;}
		if(!checkSocket()) return;

		try {

			String speed = getInitialSpeed(numberDriveNo);
			if (speed != null) {  	
				setSpeed(numberDriveNo,speed); // RelativePTP�� �����ϱ� ���� speed�� ���� �����Ѵ�. 
			}	
			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write("enable plc 31\r\n");
			writer.flush();
			socketClient.close();

		} catch (Exception e) {
			DialogManager.getInstance().showServerErrorConfirmDialog(e.getMessage());
		}
	}



	/**
	 * @author : YOO YOUNGOK 
	 * @method  handleAbsoluteRepetitive
	 * @param numberDriveNo
	 * @param ptp
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public static void handleAbsoluteRepetitive(String numberDriveNo,String ptp,String ptp1,String speed) {

		System.out.println(Tag+" :: handleAbsoluteRepetitive :: ptp == "+ptp + " && "+ptp1 + " Connection Established");

		try {
			File file = new File("/home/jscs1/linuxcnc/configs/pmac/programs/31.plc");

			if(!file.exists()) {
				file.createNewFile();
			}
			
			BufferedWriter out;
			
			if(file.exists()) {
				System.out.println("file exites");
				out = new BufferedWriter(new FileWriter(file));

				
				String value = "cmds \"#"+numberDriveNo+"J="+ptp+"\"";
				out.write(value);
				out.newLine();
				String vaule1 = "while(h"+numberDriveNo+"10>0)";
				out.write(vaule1);
				out.newLine();
				String vaule2 = "sleep(1000000)";
				out.write(vaule2);
				out.newLine();
				String vaule3 = "endwhile";
				out.write(vaule3);
				out.newLine();
				
				String value4 = "cmds \"#"+numberDriveNo+"J="+ptp1+"\"";
				out.write(value4);
				out.newLine();
				out.write(vaule1);
				out.newLine();
				out.write(vaule2);
				out.newLine();
				out.write(vaule3);
				out.newLine();
				
				out.close();
			}		
		} catch (IOException e1) {
			e1.printStackTrace(); 
		}

		if(CommandConst.DEBUG) return;
		if(!checkNetworkConnection())  { return ;}
		if(!checkSocket()) return;
		
		try {
			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			if (speed != null) {  	
				writer.write("i"+numberDriveNo+CommandConst.SPEED+"=" + speed + "\r\n");
			}
			writer.flush();
			writer.write("enable plc 31\r\n");
			writer.flush();

			socketClient.close();
		} catch (Exception e) {
			DialogManager.getInstance().showServerErrorConfirmDialog(e.getMessage());
		}
	}

	/**
	 * @author : YOO YOUNGOK 
	 * @method  handleRelativeLeft
	 * @param numberDriveNo
	 * @param ptp
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public static void handleRelativeLeft(String numberDriveNo,String ptp,String speed) {

		System.out.println(Tag+" :: handleRelativeLeft :: ptp == "+ptp + " Connection Established");
		if(!checkNetworkConnection())  { return ;}
		if(!checkSocket()) return;

		if (speed != null) {  	
			setSpeed(numberDriveNo,speed); // RelativePTP�� �����ϱ� ���� speed�� ���� �����Ѵ�. 
		}

		try {
			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			String command = "#"+numberDriveNo+"j:-"+ ptp;
			System.out.println("===============handleRelativeLeft "+command+"======================");		
			//MemberDAO�� insertLog()�� ȣ���Ͽ� ���� Log�� DB�� �����Ѵ�.
			MemberDAO.getInstance().insertLog(command);
			writer.write(command+"\r\n");
			writer.flush();

			//MemberDAO�� insertLog()�� ȣ���Ͽ� ���� Log�� DB�� �����Ѵ�.
			MemberDAO.getInstance().insertLog(command);

			socketClient.close();
		} catch (Exception e) {
			e.printStackTrace();
//			DialogManager.getInstance().showServerErrorConfirmDialog(e.getMessage());
		}
	}

	/**
	 * @author : YOO YOUNGOK 
	 * @method  handleRelativeRight
	 * @param numberDriveNo
	 * @param ptp
	 * @param speed
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public static void handleRelativeRight(String numberDriveNo,String ptp,String speed) {
//
//		System.out.println(Tag+" :: handleRelativeLeft :: ptp == "+ptp + " Connection Established");
		if(!checkNetworkConnection())  { return ;}
		if(!checkSocket()) return;

		if (speed != null) {  	
			setSpeed(numberDriveNo,speed); // RelativeRight�� �����ϱ� ���� speed�� ���� �����Ѵ�. 
		}

		try {
			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			String command = "#"+numberDriveNo+"j:"+ ptp;
			//MemberDAO�� insertLog()�� ȣ���Ͽ� ���� Log�� DB�� �����Ѵ�.
			System.out.println("===============handleRelativeRight "+command+"======================");		
			MemberDAO.getInstance().insertLog(command);
			writer.write(command+"\r\n");
			writer.flush();

			socketClient.close();

		} catch (Exception e) {
			e.printStackTrace();
			DialogManager.getInstance().showServerErrorConfirmDialog(e.getMessage());
		}
	}

	/**
	 * @author : YOO YOUNGOK 
	 * @method  handleAbsoluteRight
	 * @param numberDriveNo
	 * @param ptp
	 * @param speed
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public static void handleAbsoluteRight(String numberDriveNo,String ptp,String speed) {

//		System.out.println(Tag+" :: handleRelativeRight :: ptp == "+ptp + " Connection Established");
		if(!checkNetworkConnection())  { return ;}
		if(!checkSocket()) return;

		if (speed != null) {  	
			setSpeed(numberDriveNo,speed); // AbsoluteRigh�� �����ϱ� ���� speed�� ���� �����Ѵ�. 
		}

		try {
			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			String command = "#"+numberDriveNo+"j="+ptp;
			//MemberDAO�� insertLog()�� ȣ���Ͽ� ���� Log�� DB�� �����Ѵ�.
			MemberDAO.getInstance().insertLog(command);
			System.out.println("===============handleAbsoluteRight "+command+"======================");
			writer.write(command+"\r\n");
			writer.flush();
			socketClient.close();

		} catch (Exception e) {
			DialogManager.getInstance().showServerErrorConfirmDialog(e.getMessage());
		}

	}

	/**
	 * @author : YOO YOUNGOK 
	 * @method  handleJoggingLeft
	 * @param numberDriveNo
	 * @param speed
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public static void handleJoggingLeft(String numberDriveNo,String speed) {

//		System.out.println(Tag+" :: handleJoggingLeft :: speed == " +numberDriveNo);
		if(!checkNetworkConnection())  { return ;}
		if(!checkSocket()) return;

		try {

			if (speed != null) {  	
				setSpeed(numberDriveNo,speed); // JoggingLeft�� �����ϱ� ���� speed�� ���� �����Ѵ�. 
			}			
			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			String command = "#"+numberDriveNo+"j-";
			//MemberDAO�� insertLog()�� ȣ���Ͽ� ���� Log�� DB�� �����Ѵ�.
			MemberDAO.getInstance().insertLog(command);	
			writer.write(command+"\r\n");
			writer.flush();
			socketClient.close();
		} catch (Exception e) {
			e.printStackTrace();
			DialogManager.getInstance().showServerErrorConfirmDialog(e.getMessage());
		}
	}

	/**
	 * @author : YOO YOUNGOK 
	 * @method  handleJoggingRight
	 * @param numberDriveNo
	 * @param speed
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public static void handleJoggingRight(String numberDriveNo,String speed) {

		System.out.println(Tag+" :: handleJoggingRight :: speed == " +speed);
		if(!checkNetworkConnection())  { return ;}
		if(!checkSocket()) return;

		try {
			if (speed != null) {  	
				setSpeed(numberDriveNo,speed); // JoggingRight�� �����ϱ� ���� speed�� ���� �����Ѵ�. 
			}
			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));			
			String command = "#"+numberDriveNo+"j+";
			//MemberDAO�� insertLog()�� ȣ���Ͽ� ���� Log�� DB�� �����Ѵ�.
			MemberDAO.getInstance().insertLog(command);
			writer.write(command+"\r\n");
			writer.flush();
			socketClient.close();
		} catch (Exception e) {
			e.printStackTrace();
			DialogManager.getInstance().showServerErrorConfirmDialog(e.getMessage());
		}
	}

	/**
	 * @author : YOO YOUNGOK 
	 * @method  serverOn
	 * @param numberDriveNo
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public static void serverOn(String numberDriveNo) {

		System.out.println(Tag+" :: serverOn :: numberDriveNo == " +numberDriveNo);
		if(!checkNetworkConnection())  { return ;}
		if(!checkSocket()) return;

		//MemberDAO�� insertLog()�� ȣ���Ͽ� ���� Log�� DB�� �����Ѵ�.
//		MemberDAO.getInstance().insertLog(command);
		
		try {
			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			//			String command ="#"+numberDriveNo+"$";
			String command = "ereset";
			writer.write(command+"\r\n");
			writer.flush();
			
			command ="$$";
			writer.write(command+"\r\n");
			writer.flush();
	
			command ="homeall";
			writer.write(command+"\r\n");
			writer.flush();
			
			socketClient.close();
		} catch (Exception e) {
			DialogManager.getInstance().showServerErrorConfirmDialog(e.getMessage());
		}
	}

	/**
	 * @author : YOO YOUNGOK 
	 * @method  serverOff
	 * @param numberDriveNo
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public static  void serverOff(String numberDriveNo) {
		System.out.println(Tag+" :: serverOff :: numberDriveNo == " +numberDriveNo);
		if(!checkNetworkConnection())  { return ;}
		if(!checkSocket()) return;
		try {
			System.out.println("Server Off");
			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			//			String command ="#"+numberDriveNo+"k";
			String command ="kk";
			//MemberDAO�� insertLog()�� ȣ���Ͽ� ���� Log�� DB�� �����Ѵ�.
			MemberDAO.getInstance().insertLog(command);
			writer.write(command+"\r\n");
			writer.flush();
			socketClient.close();
		} catch (Exception e) {
			DialogManager.getInstance().showServerErrorConfirmDialog(e.getMessage());
		}
	}

	/**
	 * @author : YOO YOUNGOK 
	 * @method  resetDriver
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public static void resetDriver() {
		System.out.println("Client: " + " resetDriver :: Connection Established");
		if(!checkNetworkConnection())  { return ;}
		if(!checkSocket()) return;
		try{
			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedWriter writer= 
					new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			//			String command ="$$$/";
			String command ="$$$";
			//MemberDAO�� insertLog()�� ȣ���Ͽ� ���� Log�� DB�� �����Ѵ�.
			MemberDAO.getInstance().insertLog(command);
			writer.write(command+"\r\n");
			writer.flush();
			socketClient.close();
		}catch(Exception e){
			DialogManager.getInstance().showServerErrorConfirmDialog(e.getMessage());
		}
	}

	/**
	 * @author : YOO YOUNGOK 
	 * @method  stop
	 * @param numberDriveNo
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public static void stop(String numberDriveNo) {

		System.out.println(Tag+" :: stop :: numberDriveNo == " +numberDriveNo);

		if(!checkSocket()) return;

		try {
			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			String command ="#"+numberDriveNo+"j/";
			//MemberDAO�� insertLog()�� ȣ���Ͽ� ���� Log�� DB�� �����Ѵ�.
			MemberDAO.getInstance().insertLog(command);
			writer.write(command+"\r\n");
			writer.flush();
			socketClient.close();
		} catch (Exception e) {
			DialogManager.getInstance().showServerErrorConfirmDialog(e.getMessage());
		}
	}

	public static boolean checkSocket() { 
		System.out.println(Tag+" :: checkSocket ");

		if(!checkNetworkConnection())  { return false ;}

		boolean connected = false;
		try {
			Socket socketClient = new Socket(CommandConst.address, 12345);
			if(!socketClient.isBound()) {
				System.out.println(Tag+" :: is Not Bound ");
				connected = false;
			}
			else {
				System.out.println(Tag+" :: is Bound ");
				connected = socketClient.isConnected() && ! socketClient.isClosed();
				if(connected) {
					System.out.println(Tag+" :: is connected ");
				}
			}
			socketClient.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			ErrorLogManager.getInstance().addErrorLog(Tag, "checkSocket", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ErrorLogManager.getInstance().addErrorLog(Tag, "checkSocket", e);
		}

		return connected;
	}

	/**
	 * @author : YOO YOUNGOK 
	 * @method  getPortInfo
	 * @param command
	 * @return
	 * @return  String
	 * @exception  IOException/SQLException
	 * @see
	 * @since 2018. 6. 21.
	 */
	public static String getPortInfo(String command) {
		System.out.println("Client: NetworkServer :: getPortInfo ==" +command);

		if(!checkNetworkConnection())  { return "CONNECTION ERROR";}

		String response = null;
		try {
			String sendMsg = command+"\r\n";
			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(sendMsg);
			writer.flush();

			//MemberDAO�� insertLog()�� ȣ���Ͽ� ���� Log�� DB�� �����Ѵ�.
			MemberDAO.getInstance().insertLog(command);

			String serverMsg = reader.readLine();
			System.out.println(Tag+" :: getPortInfo result: " + serverMsg);
			String serverMsg_1 = reader.readLine();
			serverMsg_1 = StringUtil.getNumberFommatWithMaxDigits(Double.parseDouble(serverMsg_1), 0);
			System.out.println(Tag+" :: getPortInfo result_1: " + serverMsg_1);

			response = serverMsg_1;
			socketClient.close();
		} catch (Exception e) {
			if(e.getClass()==ConnectException.class) {
				response= "CONNECTION ERROR";
			}
			else {
				response = "ERROR";
			}

			e.printStackTrace();
		}
		return response;
	}


	/**
	 * portName�� �ش��ϴ� ��Ʈ��  switch lamp�� value(ON/OFF ���� ������)�� �����Ѵ�. 
	 * @author : YOO YOUNGOK 
	 * @method  setSwitch
	 * @param portName //��Ʈ��
	 * @param value  // ��Ʈ�� 8�� ������ ON/OFF���� �������� ����� ��
	 * @return
	 * @return  boolean
	 * @exception IOException / SQLException
	 * @see
	 * @since 2018. 6. 21.
	 */
	public static boolean setSwitch(String portName,String value) {
		boolean result = false;

		if(!checkNetworkConnection())  { return false;}

		try {
			Socket socketClient = new Socket(CommandConst.address, 12345);
			String command = portName+"="+value;
			String sendMsg = command+"\r\n";
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(sendMsg);
			writer.flush();

			//MemberDAO�� insertLog()�� ȣ���Ͽ� ���� Log�� DB�� �����Ѵ�.
			MemberDAO.getInstance().insertLog(command);

			String serverMsg = reader.readLine();
			System.out.println(Tag+" :: setSwitch result: " + serverMsg);

			if(serverMsg.equals(command)) result = true; //�����κ��� ��ɾ�� ������ ���� ������ ����! , �ٸ��� ����!

			socketClient.close();
		} catch (Exception e) {
			//			if(e.getClass()==ConnectException.class) {
			//				DialogManager.getInstance().showServerErrorConfirmDialog("CONNECTION ERROR");
			//			}

			e.printStackTrace();
		}

		return result;
	}


	/**
	 * EtherCAT �� Port�� Switch�� controll�ϱ� ���� ������ mapping�Ѵ�.
	 * @author : YOO YOUNGOK 
	 * @method  reSetSwitchID
	 * @param command // ����mapping�� ���� ��ɾ�... ex) h1->lcec.0.0.input-1 (1�� ��Ʈ�� ������ h1����  �����ȴ�..)
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 26.
	 */
	public static void reSetSwitchID(String command) {

		if(!checkNetworkConnection())  { return ;}
		try {

			//MemberDAO�� insertLog()�� ȣ���Ͽ� ���� Log�� DB�� �����Ѵ�.
			MemberDAO.getInstance().insertLog(command);
			Socket socketClient = new Socket(CommandConst.address, 12345);
			String sendMsg = command+"\r\n";
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(sendMsg);
			writer.flush();
			socketClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setDriverIDSet(String command) {
		if(!checkNetworkConnection())  { return ;}
		try {

			//MemberDAO�� insertLog()�� ȣ���Ͽ� ���� Log�� DB�� �����Ѵ�.
			System.out.println(Tag+" :: "+command);

			MemberDAO.getInstance().insertLog(command);
			Socket socketClient = new Socket(CommandConst.address, 12345);
			String sendMsg = command+"\r\n";
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(sendMsg);
			writer.flush();

			socketClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getDriverStaus(String command) {

		String reponse = null;

		try {
			//MemberDAO�� insertLog()�� ȣ���Ͽ� ���� Log�� DB�� �����Ѵ�.
			MemberDAO.getInstance().insertLog(command);
			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(command+"\r\n");
			writer.flush();

			if(reader.readLine().equals(command)) {
				System.out.println(Tag+" : reponse ="+command);
				reponse = reader.readLine();				
			}
			socketClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reponse;
	}

	public static ObservableList<String> getDriverStaus(ObservableList<String> commandList) {	
		ObservableList<String> response = FXCollections.observableArrayList() ;
		try {
			if(realTimeSocket.isClosed()) {
				System.out.println(Tag+" : realTimeSocket isClosed");
				SocketAddress me = new InetSocketAddress(CommandConst.address,12345);
				realTimeSocket.bind(me);
			}

			BufferedReader reader = new BufferedReader(new InputStreamReader(realTimeSocket.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(realTimeSocket.getOutputStream()));

//						System.out.println(Tag+" commandList size == "+commandList.size());
			for(String value:commandList) {
//								System.out.println(Tag+" : commandList ="+value);
				writer.write(value+"\r\n");
				writer.flush();

				String szLine = reader.readLine();
				szLine = szLine.trim();
				if(szLine.equals(value)) {
//				System.out.println(Tag+" : reponse ="+szLine);
					String valueLine = reader.readLine();			
					response.add(valueLine);	
//					System.out.println(Tag+" : reponse ="+valueLine);
				}
				else {
					System.out.println(Tag+" : reponse_error ="+szLine);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	public static boolean checkNetworkConnection() {
		boolean isConnection = false;

		try {
			InetAddress tagetIp = InetAddress.getByName(CommandConst.address);
			boolean reachable =  tagetIp.isReachable(2000);

			if (reachable) {
				//				System.out.println("Connection!!!");
				isConnection = true;
			} 

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isConnection;
	}

	
	//�׷����� �׸��� ���� ���� ��ġ���� OFFSET���� �޾ƿ´�...
	public void getPositionOffSet(String dirNo) {
		String reponse = "78"; //11��20�� 3��10�� ��.. 
		if (!CommandConst.DEBUG) {
			try {
				Socket socketClient = new Socket(CommandConst.address, 12345);
				BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
				String command = CommandConst.GET_MOTOR_OFFSET.replace("Driver", String.valueOf(dirNo));
				writer.write(command+"\r\n");
				writer.flush();
				if(reader.readLine().equals(command)) {
					System.out.println(Tag+" : reponse ="+command);
					reponse = reader.readLine();				
				}
				socketClient.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		//�޾ƿ� �ɼ°��� �����Ѵ�.
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		prefs.put("OFFSET_"+dirNo, reponse);
	}

	/** ################################################################## */

	/**
	 * �����κ��� Response�� ���°�� while���� �����ϱ� ���� Timer Class
	 * @author YOUNGOK YOO
	 * @since 2018. 6. 19.
	 */
	public class TimerCall extends Timer{

		int sec=0;

		/**
		 * repeatPeriod �� �ð��� ���� ������ execute() �޼ҵ带 ȣ���ϵ��� �����Ѵ�
		 * @param repeatPeriod(�ݺ��ֱ�,ms)
		 * �� : repeatPeriod = 1000 �� ��� 1��
		 */
		public TimerCall(long repeatPeriod) {
			super(repeatPeriod);
		}

		/**
		 * ������ �ð��� ������ ������ ȣ��Ǵ� �޼ҵ�
		 */
		@Override
		public void execute() {
			Calendar cal = Calendar.getInstance();
			System.out.println("TimerCall ȣ��! " + cal.get(Calendar.HOUR_OF_DAY) + "�� " +
								cal.get(Calendar.MINUTE) + "�� " + cal.get(Calendar.SECOND) + "��");

			// Update the Label on the JavaFx Application Thread
			Platform.runLater(new Runnable()
			{
				@Override
				public void run()
				{
					sec++;	//1�ʸ��� sec���� ������Ų��.		
				}
			});
		}

		/**
		 * Ÿ�̸Ӱ� ����Ǿ��� ��� ȣ��ȴ�
		 * �ش� Ÿ�̸� ����ü�� �����ð����� ���� �ݺ��̱� ������ 
		 * SupinanTimer �� closeTimer() �� ȣ������ �ʴ��� �ش� �޼ҵ��
		 * ȣ������ �ʴ´�
		 */
		@Override
		public void stopTimer(TimerStopType type) {
			System.out.println("TimerCall ���� :: " + type.name());
		}
	}	
}
