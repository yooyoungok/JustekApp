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
            // ?îÑÎ°úÌçº?ã∞ ?åå?ùº ?úÑÏπ?
            String propFile = "resources/config/config.properties";
            // ?îÑÎ°úÌçº?ã∞ Í∞ùÏ≤¥ ?Éù?Ñ±
            Properties props = new Properties();
            // ?îÑÎ°úÌçº?ã∞ ?åå?ùº ?ä§?ä∏Î¶ºÏóê ?ã¥Í∏?
            FileInputStream fis = new FileInputStream(propFile);
            // ?îÑÎ°úÌçº?ã∞ ?åå?ùº Î°úÎî©
            props.load(new java.io.BufferedInputStream(fis));
            // ?ï≠Î™? ?ùΩÍ∏?
            sUserId = props.getProperty("db.user") ;
            sPasswd = props.getProperty("db.pass") ;
            sServerIp = props.getProperty("db.host") ;
            sSid = props.getProperty("db.sid") ;
            sDBPort = props.getProperty("db.port") ;
            connUrl = "jdbc:oracle:thin:@"+sServerIp+":"+sDBPort+":"+sSid;
            // ÏΩòÏÜî Ï∂úÎ†•
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
