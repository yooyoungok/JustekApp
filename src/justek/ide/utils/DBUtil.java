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
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");// ?��?��?���? ?��?��?��
		ds.setUrl(propReader.getConnUrl());
		ds.setUsername(propReader.getsUserId());
		ds.setPassword(propReader.getsPasswd());

		ds.setInitialSize(1);// 최초?�� ?��?��?�� �??��?��객체?�� ?�� 기본 :0
		ds.setMaxActive(10);// 최�? ?��?��?�� 커넥?�� 객체?�� ?�� 기본:8 ?��?��?��, 무한?�� ?��
		ds.setMaxIdle(2);// 커넥?�� ???�� �?리할 ?��?��?���? ?��?�� 코넥?��?�� 최�??�� 기본 :0
		ds.setMaxWait(2000);// ???��?�� 커넥?��?�� ?��?�� ?�� ?�� ?���??�� ?��?��?�� 경우 기달�? ?���?
		// long ???��?���? 미리초�?? ?��?��?��.

	}
	public static DBUtil getInstance() {
		return instance;
	}

	// DataSource�? �??�� Connection ?�� 리턴?��?�� 메소?��
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
