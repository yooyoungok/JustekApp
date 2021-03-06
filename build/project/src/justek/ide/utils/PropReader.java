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
            // ?λ‘νΌ?° ??Ό ?μΉ?
            String propFile = "resources/config/config.properties";
            // ?λ‘νΌ?° κ°μ²΄ ??±
            Properties props = new Properties();
            // ?λ‘νΌ?° ??Ό ?€?Έλ¦Όμ ?΄κΈ?
            FileInputStream fis = new FileInputStream(propFile);
            // ?λ‘νΌ?° ??Ό λ‘λ©
            props.load(new java.io.BufferedInputStream(fis));
            // ?­λͺ? ?½κΈ?
            sUserId = props.getProperty("db.user") ;
            sPasswd = props.getProperty("db.pass") ;
            sServerIp = props.getProperty("db.host") ;
            sSid = props.getProperty("db.sid") ;
            sDBPort = props.getProperty("db.port") ;
            connUrl = "jdbc:oracle:thin:@"+sServerIp+":"+sDBPort+":"+sSid;
            // μ½μ μΆλ ₯
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
