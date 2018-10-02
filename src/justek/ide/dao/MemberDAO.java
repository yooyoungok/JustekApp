package justek.ide.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import justek.ide.model.cmdLog.LogDTO;
import justek.ide.model.member.MemberDTO;
import justek.ide.model.subMenu.SubMenuDTO;
import justek.ide.model.subMenu.TabMenuDTO;
import justek.ide.utils.DBUtil;

	public class MemberDAO {
		private static MemberDAO instance ;
														
	
		private DBUtil dbUtil;
		private MemberDAO() {
			dbUtil=DBUtil.getInstance();
		}
	
		public static MemberDAO getInstance() {
			if(instance==null) instance = new MemberDAO();
			
			return instance;
		}

	// insert cmd_log to DB
	public int insertLog(String cmd) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO CMD_LOG (LOG_NO,EXECUTE_TIME,MEMBER_ID,CMD) VALUES (LOG_NO_SEQ.NEXTVAL,TO_CHAR(SYSDATE,'yyyy/MM/DD HH24:MI:SS'),'admin01',?)";
		int cnt = 0;
		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cmd);			
			cnt = pstmt.executeUpdate();
		} finally {
			dbUtil.close(conn, pstmt);
		}
		return cnt;
	}

	public ObservableList<LogDTO> selectLog(String id, String startDate,String endDate) throws SQLException {
		ObservableList<LogDTO> list =  FXCollections.observableArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT LOG_NO,EXECUTE_TIME,MEMBER_ID,CMD FROM CMD_LOG WHERE MEMBER_ID=? AND SUBSTR(EXECUTE_TIME,1,10) BETWEEN ? AND ? ORDER BY EXECUTE_TIME DESC";
		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, startDate);
			pstmt.setString(3, endDate);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add (new LogDTO(Integer.parseInt(rs.getString("LOG_NO"))
						,rs.getString("EXECUTE_TIME")
						,rs.getString("MEMBER_ID")
						,rs.getString("CMD")
						));
			}

		} finally {
			dbUtil.close(conn, pstmt,rs);
		}
		return list;
	}

	

//		로그인
		public MemberDTO selectPasswd(String id) throws SQLException {
			MemberDTO memberDTO = new MemberDTO();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT MEMBER_ID, PASSWORD, MEMBER_AUTH, USE_YN FROM MEMBER WHERE 1=1 AND MEMBER_ID=?";
			try {
				conn = dbUtil.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					memberDTO.setMemberId(rs.getString("MEMBER_ID"));
					memberDTO.setPassword(rs.getString("PASSWORD"));
					memberDTO.setMemberAuth(rs.getString("MEMBER_AUTH"));
					memberDTO.setUseYn(rs.getString("USE_YN"));
				}
			} finally {
				dbUtil.close(conn, pstmt,rs);
			}
			return memberDTO;
		}
//메뉴 가져오기 
		public ArrayList<SubMenuDTO>  selectSubMenu(String auth) throws SQLException{
			// TODO Auto-generated method stub
			ArrayList<SubMenuDTO> list = new ArrayList<SubMenuDTO>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT SUB_MENU_ID,TOP_MENU_ID,SUB_MENU_DSCRTN,SUB_MENU_NM,SUB_MENU_AUTH,USE_YN,SUB_MENU_FX_ID FROM SUB_MENU WHERE 1=1 AND SUB_MENU_AUTH=?";
			try {
				conn = dbUtil.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, auth);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					list.add(new SubMenuDTO(rs.getString("SUB_MENU_ID")
							, rs.getString("TOP_MENU_ID")
							, rs.getString("SUB_MENU_DSCRTN")
							 , rs.getString("SUB_MENU_NM")
							 , rs.getString("SUB_MENU_AUTH")
							 , rs.getString("USE_YN")
							 , rs.getString("SUB_MENU_FX_ID")
							));
				}
			} finally {
				dbUtil.close(conn, pstmt,rs);
			}
			return list;
		}
		public ArrayList<TabMenuDTO> selectTabMenu(String auth) throws SQLException{
			// TODO Auto-generated method stub
						ArrayList<TabMenuDTO> list = new ArrayList<TabMenuDTO>();
						Connection conn = null;
						PreparedStatement pstmt = null;
						ResultSet rs = null;
						String sql = "SELECT TAB_MENU_ID,SUB_MENU_ID,TAB_MENU_DSCRTN,TAB_MENU_NM,TAB_MENU_AUTH,USE_YN,TAB_MENU_FX_ID FROM TAB_MENU WHERE 1=1 AND TAB_MENU_AUTH=?";
						try {
							conn = dbUtil.getConnection();
							pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, auth);
							rs = pstmt.executeQuery();
							while (rs.next()) {
								list.add(new TabMenuDTO(rs.getString("TAB_MENU_ID")
										 , rs.getString("SUB_MENU_ID")
										 , rs.getString("TAB_MENU_NM")
										 , rs.getString("TAB_MENU_DSCRTN")
										 , rs.getString("TAB_MENU_AUTH")
										 , rs.getString("USE_YN")
										 , rs.getString("TAB_MENU_FX_ID")
										));
							}
						} finally {
							dbUtil.close(conn, pstmt,rs);
						}
						return list;
		}
		

//로그인
//	public ArrayList<MemberDTO> selectAllperson() throws SQLException {
//		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String sql = "select person_no,firstName,lastName,street,city from person";
//		int cnt = 0;
//		try {
//			conn = dbUtil.getConnection();
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				list.add(new MemberDTO(rs.getString("firstName")
//						, rs.getString("lastName")
//						, rs.getString("street")
//						 , rs.getString("city")
//						));
//			}
//		} finally {
//			if(rs!=null){
//				rs.close();
//			}
//			if (pstmt != null) {
//				pstmt.close();
//			}
//			if (conn != null) {
//				conn.close();
//			}
//
//		}
//		return list;
//	}

//	
//	public int updateSchoolById(SchoolTO sto3) throws SQLException {
//		int cnt = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		String sql="update school set enrollment=?, school_address=? where school_id=?";
//		try {			
//			conn = dbUtil.getConnection();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1,sto3.getEnrollment() );
//			pstmt.setString(2, sto3.getSchoolAddress());
//			pstmt.setString(3, sto3.getSchoolId());
//			cnt=pstmt.executeUpdate();
//		} finally {
//			if (pstmt != null) {
//				pstmt.close();
//			}
//			if (conn != null) {
//				conn.close();//
//			}
//
//		}
//		return cnt;
//	}
//
//
//
//
//	public int deleteSchoolById(String sto) throws SQLException{
//		int cnt = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		String sql="delete from school where=?";
//		try {			
//			conn = dbUtil.getConnection();
//			pstmt.setString(1, sto);
//			cnt=pstmt.executeUpdate();
//		} finally {
//			if (pstmt != null) {
//				pstmt.close();
//			}
//			if (conn != null) {
//				conn.close();
//			}
//
//		}
//		return cnt;
//	}


}

