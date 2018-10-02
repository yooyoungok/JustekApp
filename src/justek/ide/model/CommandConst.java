/**
 * ------------------------------------------------------------------------------
 * @Project       :  JustekApp
 * @Source        : CommandConst.java
 * @Description  : 명령어 및 기본값 저장
 * @Author        : YOUNGOK YOO / 확인자 : 
 * @Version       : v1.0
 * Copyright(c) 2018 JUSTEK All rights reserved
 *------------------------------------------------------------------------------
 *                  변         경         사         항                       
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                      DESCRIPTION                        
 * ----------  ------  --------------------------------------------------------- 
 * 2018.06.14  유영옥    신규생성                                     
 *------------------------------------------------------------------------------
 */

package justek.ide.model;

import java.awt.Color;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CommandConst {

	public static String address = "192.168.1.207";
//	public final static String address = "192.168.100.51";
	public static boolean DEBUG = true;
	public static boolean isWindow = false;
	
	public static boolean SYSTEM_CONFIG = false;
	public static boolean isEtherCAT = false;
	public static boolean isController = false;
	
	public static int INTERVAL_TIME=100;
	
	public final static String REAL_TIME_THREAD_KEY="REALTIME_THREAD";
	public final static String TRIGGER_THREAD_KEY="TRIGGER_THREAD";
	public final static String AUTO_SCAN_THREAD_KEY="AUTO_SCAN_THREAD";

	public final static String LEFT = "LEFT";
	public final static String RIGHT = "RIGHT";
	
	// PosionLoop Info Field
	public final static String PROPOTION_GAIN = "30";
	public final static String VELOCITY_FF_GAIN = "32";
	public final static String ACCELERATION_FF_GAIN = "35";
	public final static String GAIN_DIVIDE = "31";
	public final static String SCALE = "09";
	public final static String FEEDBACK_DERIVATIVE_GAIN = "34";
	public final static String INTEGRAL_GAIN = "33";
	
	public  static String  PROPOTION_GAIN_UPLOAD = "ethercat upload -p driver -t int32 0x2400 1";
	public  static String  INTEGRAL_GAIN_UPLOAD = "ethercat upload -p driver -t int32 0x2400 2";
	public  static String  GAIN_DIVIDE_UPLOAD = "ethercat upload -p driver -t int32 0x2400 3";
	public  static String  SCALE_UPLOAD = "ethercat upload -p driver -t int16  0x2400 4";
	public  static String  FEEDBACK_DERIVATIVE_GAIN_UPLOAD = "ethercat upload -p driver -t int32 0x2400 5";
	public  static String  VELOCITY_FF_GAIN_UPLOAD = "ethercat upload -p driver -t int32 0x2400 6";
	public  static String  ACCELERATION_FF_GAIN_UPLOAD = "ethercat upload -p driver -t int32 0x2400 7";
	
	public  static String  PROPOTION_GAIN_DOWNLOAD = "ethercat download -p driver -t int32 0x2400 1 V";
	public  static String  INTEGRAL_GAIN_DOWNLOAD  = "ethercat download -p driver -t int32 0x2400 2 V";
	public  static String  GAIN_DIVIDE_DOWNLOAD  = "ethercat download -p driver -t int32 0x2400 3 V";
	public  static String  SCALE_DOWNLOAD = "ethercat download -p driver -t int16  0x2400 4 V";
	public  static String  FEEDBACK_DERIVATIVE_GAIN_DOWNLOAD  = "ethercat download -p driver -t int32 0x2400 5 V";
	public  static String  VELOCITY_FF_GAIN_DOWNLOAD  = "ethercat download -p driver -t int32 0x2400 6 V";
	public  static String  ACCELERATION_FF_GAIN_DOWNLOAD  = "ethercat download -p driver -t int32 0x2400 7 V";
	
	//enable set
	public final static String POSITON = "22";
	public final static String VELOCITY = "22";
	public final static String CURRENT = "22";
	
	//profiler field
	public final static String SPEED = "22"; //16
	public final static String PROFILER_PTP = "22";
	public final static String PROFILER_ABSOULTE = "22";
	public final static String PROFILER_RELATIVE = "22";
	public final static String PROFILER_JOG_ACC = "17"; //19에서 변경...
	public final static String PROFILER_DEC = "22";
	public final static String PROFILER_STOP_DEC = "22";
	public final static String PROFILER_SMOOTH = "22";
	public final static String PROFILER_DWELL = "22";
	
	
	//DATA DEFINE
	public final static String TP = "TargetPosition";
	public final static String AP = "ActualPosition";
	public final static String TV = "TargetVelocity";
	public final static String AV = "ActualVelocity";
	public final static String TA = "TargetAcceleration";
	public final static String PE = "PositionError";
	public final static String ST = "StatusWord";
	
	public final static ObservableList<String> SINGLE_AXIS_FUNTION_LIST = FXCollections.observableArrayList("GP","PLIM","MLIM","HALL_U","HALL_V","HALL_W","FAULT CLEAR");
	public final static ObservableList<String> SINGLE_AXIS_FUNTION_OUT_LIST = FXCollections.observableArrayList("GP","PLIM","MLIM","HALL_U","HALL_V","HALL_W","FAULT");
	
	public static ObservableList<String> STATUS_LIST = FXCollections.observableArrayList(TP,AP,AV,PE,ST);
	
	public final static ObservableList<String> TRIGGER_RLIST = FXCollections.observableArrayList("TargetPosition","ActualPosition","TargetVelocity","ActualVelocity","TargetAcceleration","PositionError","StatusParameter","UserParameter");
	public static ObservableList<String> driverList = FXCollections.observableArrayList("Driver1", "Driver2");
	public final static ObservableList<String> chartList = FXCollections.observableArrayList("TargetAcceleration","PositionError","TargetPosition", "ActualPosition","TargetVelocity","ActualVelocity");
	
	public final static ObservableList<String> RECORD_TIME_LIST = FXCollections.observableArrayList("10","20","300");
	public final static ObservableList<String> RESOULTION_LIST = FXCollections.observableArrayList("1","2","4","5","10");
	
	
	public final static ObservableList<Color> COLOR_LIST = FXCollections.observableArrayList(Color.cyan,Color.green,Color.MAGENTA,Color.DARK_GRAY);
	//parameter field
	public final static String AMP_DATA = "0x2200";
	public final static String MOTOR_DATA = "0x2300";
	public final static String CURRENT_DATA = "0x2600";
	public final static String POSITION_DATA = "0x2400";
	public final static String ACTUAL_DATA = "Actual Motor";
	
	public static String DRIVER = "Driver1";
	public static int DRIVER_NUMBEER = 1;
	public static int MAXDIGITS = 0;
	public static HashMap<String,String> DRIVER_MAP = new HashMap<String,String>(); //드라이버 이름과 번호를 key/value로 저장한다...
	
	public final static String POSITIVE_EDGE = "Positive Edge";
	public final static String NEGATIVE_EDGE = "Negative Edge";
	
	//Axis Configuration...
	public final static ObservableList<String> AXIS_LILST = FXCollections.observableArrayList("SingleAxis");
	public final static ObservableList<String> AXIS_IDENTITY_LILST = FXCollections.observableArrayList("Master","Slave");
	
	//EtherCAT PORT NAME -> 포트명이 곧 명령어의 기능을 하므로 소문자로 저장한다.
	public final static String PORT_1 = "h1";
	public final static String PORT_2 = "h2";
	public final static String PORT_3 = "h3";
	public final static String PORT_4 = "h4";
	
	public final static String SET_PORT_1 = PORT_1+"->lcec.0.0.input-1";
	public final static String SET_PORT_2 = PORT_2+"->lcec.0.0.input-2";
	public final static String SET_PORT_3 = PORT_3+"->lcec.0.0.output-1";
	public final static String SET_PORT_4 = PORT_4+"->lcec.0.0.output-2";
	
	/** Driver는 선택된 Driver 번호로 replace된다.*/
	public final static String SET_DRIVER = "hDriver00->lcec.0.Driver.srv-status-word";
	public final static String SET_ACTUAL_POSITION = "hDriver30->lcec.0.Driver.srv-pos-extenc";
	public final static String SET_TARGET_POSITION = "hDriver20->lcec.0.Driver.srv-pos-cmd";
	public final static String SET_TARGET_VELOCITY = "hDriver10->lcec.0.Driver.srv-vel-fb";
	
	/** Driver는 선택된 Driver 번호로 replace된다.*/
	public final static String GET_DRIVER_STATUS = "hDriver00";
	public final static String GET_DRIVER_TARGET_POSITION = "hDriver20";
	public final static String GET_DRIVER_ACTUAL_POSITION = "hDriver30";
	public final static String GET_DRIVER_ACTUAL_VELOCITY = "hDriver10";

	/** ServerIP Array */
	public final static ObservableList<String> SERVO_IP_LIST = FXCollections.observableArrayList("192.168.1.207","192.168.1.248");
}

	