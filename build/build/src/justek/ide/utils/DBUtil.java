package justek.ide.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;


public class DBUtil {
	private static DBUtil instance = new DBUtil();
	private BasicDataSource ds;
	private PropReader propReader = null;
	
	private DBUtil() {
		propReader = PropReader.getInstance();
		
		ds = new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");// ?“œ?¼?´ë²? ?´?˜?Š¤
		ds.setUrl(propReader.getConnUrl());
		ds.setUsername(propReader.getsUserId());
		ds.setPassword(propReader.getsPasswd());

		ds.setInitialSize(1);// ìµœì´ˆ?˜ ?ƒ?„±?•  ì¼??„¥?…˜ê°ì²´?˜ ?ˆ˜ ê¸°ë³¸ :0
		ds.setMaxActive(10);// ìµœë? ?ƒ?„±?•  ì»¤ë„¥?…˜ ê°ì²´?˜ ?ˆ˜ ê¸°ë³¸:8 ?Œ?ˆ˜?Š”, ë¬´í•œ?„· ?…
		ds.setMaxIdle(2);// ì»¤ë„¥?…˜ ???´ ê´?ë¦¬í•  ?‚¬?š©?˜ì§? ?•Š?Š ì½”ë„¥?…˜?˜ ìµœë??ˆ˜ ê¸°ë³¸ :0
		ds.setMaxWait(2000);// ???—¬?•  ì»¤ë„¥?…˜?´ ?—†?„ ?„¸ ?… ?š”ì²??´ ?“¤?–´?˜¬ ê²½ìš° ê¸°ë‹¬ë¦? ?‹œê°?
		// long ???…?œ¼ë¡? ë¯¸ë¦¬ì´ˆë?? ?„£?Š”?‹¤.

	}
	public static DBUtil getInstance() {
		return instance;
	}

	// DataSourceë¡? ë¶??„° Connection ?„ ë¦¬í„´?•˜?Š ë©”ì†Œ?“œ
	public Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	//close 
	public void close(Connection conn,PreparedStatement pstmt) throws SQLException{
		if(pstmt!=null){
			pstmt.close();
		}
		if(conn!=null){
			conn.close();
			
		}
	}
	public void close(Connection conn,PreparedStatement pstmt, ResultSet rs) throws SQLException{
		if(pstmt!=null){
			pstmt.close();
		}
		if(conn!=null){
			conn.close();
			
		}
		if(rs!=null){
			rs.close();
			
		}
	}
}
