/**
 * ------------------------------------------------------------------------------
 * @Project       : JustekApp
 * @Source        : LogInfo.java
 * @Description   : 
 * @Author        : YOUNGOK YOO
 * @Version       : v1.0
 * Copyright(c) 2018 JUSTEK All rights reserved
 *------------------------------------------------------------------------------
 *                  변         경         사         항                       
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                      DESCRIPTION                        
 * ----------  ------  --------------------------------------------------------- 
 * 2018. 6. 19.   유영옥    신규생성                                     
 *------------------------------------------------------------------------------
 */

package justek.ide.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LogInfo {

	public StringProperty  date;
	public StringProperty  value;
	
	public String getDate() {
		return date.get();
	}
	public void setDate(String date) {
		this.date = new SimpleStringProperty(date);
	}
	public String getValue() {
		return value.get();
	}
	public void setValue(String value) {
		this.value = new SimpleStringProperty(value);
	}
	@Override
	public String toString() {
		return "LogInfo [date=" + date + ", value=" + value + "]";
	}
}
