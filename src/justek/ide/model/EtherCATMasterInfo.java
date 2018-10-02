/**
 * ------------------------------------------------------------------------------
 * @Project       : JustekApp
 * @Source        : EtherCATInfo.java
 * @Description   : 
 * @Author        : YOUNGOK YOO /  확인자 : 
 * @Version       : v1.0
 * Copyright(c) 2018 JUSTEK All rights reserved
 *------------------------------------------------------------------------------
 *                  변         경         사         항                       
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                      DESCRIPTION                        
 * ----------  ------  --------------------------------------------------------- 
 * 2018. 8. 27.   유영옥    신규생성                                     
 *------------------------------------------------------------------------------
 */
package justek.ide.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author cty
 *
 */
public class EtherCATMasterInfo {
	/** EtherCATInfo*/
	
	private StringProperty  name;
	private StringProperty position;
	private StringProperty  state;
	private StringProperty  RxErrorA;
	private StringProperty  LostLinksA;
	private StringProperty  RxErrorB;
	private StringProperty  LostLinksb;
	
	/**
	 * @return the name
	 */
	public StringProperty getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}
	/**
	 * @return the position
	 */
	public StringProperty getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = new SimpleStringProperty(position);
	}
	/**
	 * @return the state
	 */
	public StringProperty getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = new SimpleStringProperty(state);
	}
	/**
	 * @return the rxErrorA
	 */
	public StringProperty getRxErrorA() {
		return RxErrorA;
	}
	/**
	 * @param rxErrorA the rxErrorA to set
	 */
	public void setRxErrorA(String rxErrorA) {
		RxErrorA = new SimpleStringProperty(rxErrorA);
	}
	/**
	 * @return the lostLinksA
	 */
	public StringProperty getLostLinksA() {
		return LostLinksA;
	}
	/**
	 * @param lostLinksA the lostLinksA to set
	 */
	public void setLostLinksA(String lostLinksA) {
		LostLinksA = new SimpleStringProperty(lostLinksA);
	}
	/**
	 * @return the rxErrorB
	 */
	public StringProperty getRxErrorB() {
		return RxErrorB;
	}
	/**
	 * @param rxErrorB the rxErrorB to set
	 */
	public void setRxErrorB(String rxErrorB) {
		RxErrorB = new SimpleStringProperty(rxErrorB);
	}
	/**
	 * @return the lostLinksb
	 */
	public StringProperty getLostLinksb() {
		return LostLinksb;
	}
	/**
	 * @param lostLinksb the lostLinksb to set
	 */
	public void setLostLinksb(String lostLinksb) {
		LostLinksb = new SimpleStringProperty(lostLinksb);
	}
	
	/** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EtherCATMasterInfo [name=" + name + ", position=" + position + ", state=" + state + ", RxErrorA="
				+ RxErrorA + ", LostLinksA=" + LostLinksA + ", RxErrorB=" + RxErrorB + ", LostLinksb=" + LostLinksb
				+ "]";
	}
	
}
