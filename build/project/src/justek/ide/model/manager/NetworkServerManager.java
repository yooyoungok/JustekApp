package justek.ide.model.manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
import java.util.Calendar;

import com.supinan.util.timer.Timer;
import com.supinan.util.timer.TimerStopType;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import justek.ide.dao.MemberDAO;
import justek.ide.model.CommandConst;
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

	public static void openSocket() {
		try {
			realTimeSocket = new Socket(CommandConst.address, 12345);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 실시간 정보를 요청하는 session을 종료한다.
	 * @author : YOO YOUNGOK 
	 * @method  closeSocket
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 28.
	 */
	public static void closeSocket() {
		try {
			if(realTimeSocket!=null) {realTimeSocket.close();}
			else {
				System.out.println("Socket is Null");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

			//MemberDAO의 insertLog()를 호출하여 DB에 저장한다.
			MemberDAO.getInstance().insertLog(command);
			socketClient.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	/**
	 * @author : YOO YOUNGOK 
	 * @method  runPlcFile
	 * @param runString //실행할 PLC 파일명
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public static void runPlcFile(String runString) {
		int dot = runString.indexOf('.');
		String runString2 = runString.substring(0, dot);
		System.out.println("Client: NetworkServer :: runPlcFile ==" +runString2);
		if(!checkNetworkConnection())  { return ;}
		if(!checkSocket()) return;

		String command = "enable plc "+runString2;

		try {
			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(command+"\r\n");
			writer.flush();
			socketClient.close();
			//MemberDAO의 insertLog()를 호출하여 DB에 저장한다.
			MemberDAO.getInstance().insertLog(command);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author : YOO YOUNGOK 
	 * @method  stopPlcFile
	 * @param runString //stop을 실행할 PLC 파일명
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
			//MemberDAO의 insertLog()를 호출하여 DB에 저장한다.
			MemberDAO.getInstance().insertLog(command);
			
			socketClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author : YOO YOUNGOK 
	 * @method  uploadData
	 * @param command  //Runtime을 통해 실행할 프로그램 명령어
	 * @return
	 * @return  String 
	 * @exception SQLException / IOException
	 * @see
	 * @since 2018. 6. 21.
	 */
	public static String uploadData(String command) {

		if(!checkNetworkConnection())  { return "error" ;}
		
		if(CommandConst.DEBUG) {
			//MemberDAO의 insertLog()를 호출하여 DB에 저장한다.
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

			//MemberDAO의 insertLog()를 호출하여 DB에 저장한다.
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
		
		if(CommandConst.DEBUG) {
			return ;
		}

		Runtime runtime = Runtime.getRuntime();
		try {
			//MemberDAO의 insertLog()를 호출하여 실행 Log를 DB에 저장한다.
			MemberDAO.getInstance().insertLog(command);
			runtime.exec(command);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * numberDriveNo(사용자가 선택한 드라이버 Number)의  JogAccess값을 가져온다.
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
			//MemberDAO의 insertLog()를 호출하여 실행 Log를 DB에 저장한다.
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
	 * numberDriveNo(사용자가 선택한 드라이버 Number)의 Speed값을 가져온다.
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
			//MemberDAO의 insertLog()를 호출하여 실행 Log를 DB에 저장한다.
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
			//MemberDAO의 insertLog()를 호출하여 실행 Log를 DB에 저장한다.
			MemberDAO.getInstance().insertLog(command);
			BufferedWriter writer= 
					new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(command + "\r\n");
			writer.flush();
			socketClient.close();
		}catch(Exception e){e.printStackTrace();}

	}

	/**
	 * numberDriveNo에 해당하는 드라이버에 설정된 Speed값을 가져온다.
	 * @author : YOO YOUNGOK 
	 * @method  getInitialAcceleration
	 * @param numberDriveNo
	 * @return
	 * @return  String //numberDriveNo의 speed value
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

			//MemberDAO의 insertLog()를 호출하여 실행 Log를 DB에 저장한다.
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
	public static void handleRelativeRepetitive(String numberDriveNo,String ptp) {

		System.out.println(Tag+" :: handleRelativeRepetitive :: ptp == "+ptp + " Connection Established");
		if(!checkNetworkConnection())  { return ;}
		if(!checkSocket()) return;

		try {

			String speed = getInitialSpeed(numberDriveNo);
			if (speed != null) {  	
				setSpeed(numberDriveNo,speed); // RelativePTP를 실행하기 전에 speed를 먼저 설정한다. 
			}
			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write("enable plc 10\r\n");
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
	public static void handleAbsoluteRepetitive(String numberDriveNo,String ptp,String speed) {

		System.out.println(Tag+" :: handleAbsoluteRepetitive :: ptp == "+ptp + " Connection Established");
		if(!checkNetworkConnection())  { return ;}
		if(!checkSocket()) return;

		try {
			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			if (speed != null) {  	
				writer.write("i"+numberDriveNo+CommandConst.SPEED+"=" + speed + "\r\n");
			}
			writer.flush();
			writer.write("enable plc 10\r\n");
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
			setSpeed(numberDriveNo,speed); // RelativePTP를 실행하기 전에 speed를 먼저 설정한다. 
		}

		try {
			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			String command = "#"+numberDriveNo+"j:-"+ ptp;
			//MemberDAO의 insertLog()를 호출하여 실행 Log를 DB에 저장한다.
			MemberDAO.getInstance().insertLog(command);
			writer.write(command+"\r\n");
			writer.flush();

			//MemberDAO의 insertLog()를 호출하여 실행 Log를 DB에 저장한다.
			MemberDAO.getInstance().insertLog(command);

			socketClient.close();
		} catch (Exception e) {
			DialogManager.getInstance().showServerErrorConfirmDialog(e.getMessage());
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

		System.out.println(Tag+" :: handleRelativeLeft :: ptp == "+ptp + " Connection Established");
		if(!checkNetworkConnection())  { return ;}
		if(!checkSocket()) return;

		if (speed != null) {  	
			setSpeed(numberDriveNo,speed); // RelativeRight를 실행하기 전에 speed를 먼저 설정한다. 
		}

		try {
			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			String command = "#"+numberDriveNo+"j:"+ ptp;
			//MemberDAO의 insertLog()를 호출하여 실행 Log를 DB에 저장한다.
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

		System.out.println(Tag+" :: handleRelativeRight :: ptp == "+ptp + " Connection Established");
		if(!checkNetworkConnection())  { return ;}
		if(!checkSocket()) return;

		if (speed != null) {  	
			setSpeed(numberDriveNo,speed); // AbsoluteRigh를 실행하기 전에 speed를 먼저 설정한다. 
		}

		try {
			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			String command = "#"+numberDriveNo+"j="+ptp;
			//MemberDAO의 insertLog()를 호출하여 실행 Log를 DB에 저장한다.
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
	 * @method  handleJoggingLeft
	 * @param numberDriveNo
	 * @param speed
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public static void handleJoggingLeft(String numberDriveNo,String speed) {

		System.out.println(Tag+" :: handleJoggingLeft :: speed == " +numberDriveNo);
		if(!checkNetworkConnection())  { return ;}
		if(!checkSocket()) return;

		try {

			if (speed != null) {  	
				setSpeed(numberDriveNo,speed); // JoggingLeft를 실행하기 전에 speed를 먼저 설정한다. 
			}			
			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			String command = "#"+numberDriveNo+"j-";
			//MemberDAO의 insertLog()를 호출하여 실행 Log를 DB에 저장한다.
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
				setSpeed(numberDriveNo,speed); // JoggingRight를 실행하기 전에 speed를 먼저 설정한다. 
			}
			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));			
			String command = "#"+numberDriveNo+"j+";
			//MemberDAO의 insertLog()를 호출하여 실행 Log를 DB에 저장한다.
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

		try {
			Socket socketClient = new Socket(CommandConst.address, 12345);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			String command ="#"+numberDriveNo+"$";
			//MemberDAO의 insertLog()를 호출하여 실행 Log를 DB에 저장한다.
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
			String command ="#"+numberDriveNo+"k";
			//MemberDAO의 insertLog()를 호출하여 실행 Log를 DB에 저장한다.
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
			String command ="$$$/";
			//MemberDAO의 insertLog()를 호출하여 실행 Log를 DB에 저장한다.
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
			//MemberDAO의 insertLog()를 호출하여 실행 Log를 DB에 저장한다.
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
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

			//MemberDAO의 insertLog()를 호출하여 실행 Log를 DB에 저장한다.
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
	 * portName에 해당하는 포트의  switch lamp의 value(ON/OFF 값을 십진수)로 전달한다. 
	 * @author : YOO YOUNGOK 
	 * @method  setSwitch
	 * @param portName //포트명
	 * @param value  // 포트당 8개 램프의 ON/OFF값을 십진수로 계산한 값
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

			//MemberDAO의 insertLog()를 호출하여 실행 Log를 DB에 저장한다.
			MemberDAO.getInstance().insertLog(command);

			String serverMsg = reader.readLine();
			System.out.println(Tag+" :: setSwitch result: " + serverMsg);

			if(serverMsg.equals(command)) result = true; //서버로부터 명령어와 동일한 값을 받으면 성공! , 다르면 실패!

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
	 * EtherCAT 각 Port의 Switch를 controll하기 위한 변수를 mapping한다.
	 * @author : YOO YOUNGOK 
	 * @method  reSetSwitchID
	 * @param command // 변수mapping을 위한 명령어... ex) h1->lcec.0.0.input-1 (1번 포트의 변수는 h1으로  설정된다..)
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 26.
	 */
	public static void reSetSwitchID(String command) {
		
		if(!checkNetworkConnection())  { return ;}
		try {

			//MemberDAO의 insertLog()를 호출하여 실행 Log를 DB에 저장한다.
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

			//MemberDAO의 insertLog()를 호출하여 실행 Log를 DB에 저장한다.
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
			//MemberDAO의 insertLog()를 호출하여 실행 Log를 DB에 저장한다.
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
			
//			System.out.println(Tag+" commandList size == "+commandList.size());
			for(String value:commandList) {
//				System.out.println(Tag+" : commandList ="+value);
				writer.write(value+"\r\n");
				writer.flush();
				
				String szLine = reader.readLine();
				szLine = szLine.trim();
				if(szLine.equals(value)) {
//					System.out.println(Tag+" : reponse ="+szLine);
					response.add(reader.readLine());				
				}
				else {
//					System.out.println(Tag+" : reponse_error ="+szLine);
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

	/** ################################################################## */

	/**
	 * 서버로부터 Response가 없는경우 while문을 제어하기 위한 Timer Class
	 * @author YOUNGOK YOO
	 * @since 2018. 6. 19.
	 */
	public class TimerCall extends Timer{

		int sec=0;
		/**
		 * repeatPeriod 의 시간이 지날 때마다 execute() 메소드를 호출하도록 설정한다
		 * @param repeatPeriod(반복주기,ms)
		 * 예 : repeatPeriod = 1000 일 경우 1초
		 */
		public TimerCall(long repeatPeriod) {
			super(repeatPeriod);
		}

		/**
		 * 지정한 시간이 지났을 때마다 호출되는 메소드
		 */
		@Override
		public void execute() {
			Calendar cal = Calendar.getInstance();
			System.out.println("TimerCall 호출! " + cal.get(Calendar.HOUR_OF_DAY) + "시 " +
					cal.get(Calendar.MINUTE) + "분 " + cal.get(Calendar.SECOND) + "초");

			// Update the Label on the JavaFx Application Thread
			Platform.runLater(new Runnable()
			{
				@Override
				public void run()
				{
					sec++;	//1초마다 sec값을 증가시킨다.		
				}
			});
		}

		/**
		 * 타이머가 종료되었을 경우 호출된다
		 * 해당 타이머 구현체는 일정시간마다 무한 반복이기 때문에 
		 * SupinanTimer 의 closeTimer() 를 호출하지 않는한 해당 메소드는
		 * 호출하지 않는다
		 */
		@Override
		public void stopTimer(TimerStopType type) {
			System.out.println("TimerCall 종료:" + type.name());
		}
	}	
}
