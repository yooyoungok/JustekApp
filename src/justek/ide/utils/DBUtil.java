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
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");// ??Ό?΄λ²? ?΄??€
		ds.setUrl(propReader.getConnUrl());
		ds.setUsername(propReader.getsUserId());
		ds.setPassword(propReader.getsPasswd());

		ds.setInitialSize(1);// μ΅μ΄? ??±?  μΌ??₯?κ°μ²΄? ? κΈ°λ³Έ :0
		ds.setMaxActive(10);// μ΅λ? ??±?  μ»€λ₯? κ°μ²΄? ? κΈ°λ³Έ:8 ???, λ¬΄ν?· ?
		ds.setMaxIdle(2);// μ»€λ₯? ???΄ κ΄?λ¦¬ν  ?¬?©?μ§? ?? μ½λ₯?? μ΅λ?? κΈ°λ³Έ :0
		ds.setMaxWait(2000);// ???¬?  μ»€λ₯??΄ ?? ?Έ ? ?μ²??΄ ?€?΄?¬ κ²½μ° κΈ°λ¬λ¦? ?κ°?
		// long ????Όλ‘? λ―Έλ¦¬μ΄λ?? ?£??€.

	}
	public static DBUtil getInstance() {
		return instance;
	}

	// DataSourceλ‘? λΆ??° Connection ? λ¦¬ν΄?? λ©μ?
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
