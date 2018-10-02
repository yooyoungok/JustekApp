package justek.ide.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropReader {
	
	private static PropReader instance = new PropReader();
	private String sUserId = null;
	private String sPasswd = null;
	private String sServerIp = null;
	private String sSid = null;
	private String sDBPort = null;
	private String connUrl = null;
	public PropReader() {
		// TODO Auto-generated constructor stub
		try{
            // ?��로퍼?�� ?��?�� ?���?
            String propFile = "resources/config/config.properties";
            // ?��로퍼?�� 객체 ?��?��
            Properties props = new Properties();
            // ?��로퍼?�� ?��?�� ?��?��림에 ?���?
            FileInputStream fis = new FileInputStream(propFile);
            // ?��로퍼?�� ?��?�� 로딩
            props.load(new java.io.BufferedInputStream(fis));
            // ?���? ?���?
            sUserId = props.getProperty("db.user") ;
            sPasswd = props.getProperty("db.pass") ;
            sServerIp = props.getProperty("db.host") ;
            sSid = props.getProperty("db.sid") ;
            sDBPort = props.getProperty("db.port") ;
            connUrl = "jdbc:oracle:thin:@"+sServerIp+":"+sDBPort+":"+sSid;
            // 콘솔 출력
//            System.out.println(msg) ;
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	public static PropReader getInstance() {
		return instance;
	}
	public String getsUserId() {
		return sUserId;
	}
	public String getsPasswd() {
		return sPasswd;
	}
	public String getConnUrl() {
		return connUrl;
	}
	
	
	
}
