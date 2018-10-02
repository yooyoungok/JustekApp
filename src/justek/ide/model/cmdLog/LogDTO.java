/**
 * ------------------------------------------------------------------------------
 * @Project       : JustekApp
 * @Source        : LogDTO.java
 * @Description   : 
 * @Author        : Marco Jakob
 * @Version       : v1.0
 * Copyright(c) 2018 JUSTEK All rights reserved
 *------------------------------------------------------------------------------
 *                  변         경         사         항                       
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                      DESCRIPTION                        
 * ----------  ------  --------------------------------------------------------- 
 * 2018. 6. 19.   권순구    신규생성                                     
 *------------------------------------------------------------------------------
 */

package justek.ide.model.cmdLog;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class LogDTO {

    private  int logNo;
    private  StringProperty executeTime;
    private  StringProperty memberId;
    private  StringProperty cmd;
	public int getLogNo() {
		return logNo;
	}
	public StringProperty getExecuteTime() {
		return executeTime;
	}
	public StringProperty getMemberId() {
		return memberId;
	}
	public StringProperty getCmd() {
		return cmd;
	}
	public void setLogNo(int logNo) {
		this.logNo = logNo;
	}
	public void setExecuteTime(String executeTime) {
		this.executeTime = new SimpleStringProperty(executeTime);
	}
	public void setMemberId(String memberId) {
		this.memberId = new SimpleStringProperty(memberId);
	}
	public void setCmd(String cmd) {
		this.cmd = new SimpleStringProperty(cmd);
	}
	
	public LogDTO() {
		super();
	}
	
	
	public LogDTO(int logNo, String executeTime, String memberId, String cmd) {
		super();
		this.logNo = logNo;
		this.executeTime = new SimpleStringProperty(executeTime);
		this.memberId = new SimpleStringProperty(memberId);
		this.cmd = new SimpleStringProperty(cmd);
	}
	@Override
	public String toString() {
		return "MemberDTO [logNo=" + logNo + ", executeTime=" + executeTime + ", memberId=" + memberId + ", cmd=" + cmd
				+ "]";
	}
    
}