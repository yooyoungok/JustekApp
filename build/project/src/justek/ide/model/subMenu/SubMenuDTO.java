package justek.ide.model.subMenu;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class SubMenuDTO {

    private  String subMenuId;
    private  String topMenuId;
    private  String subMenuDscrtn;
    private  String subMenuNm;
    private  String subMenuAuth;
    private  String useYn;
    private  String subMenuFxId;
    
	public String getSubMenuId() {
		return subMenuId;
	}
	public String getTopMenuId() {
		return topMenuId;
	}
	public String getSubMenuDscrtn() {
		return subMenuDscrtn;
	}
	public String getSubMenuNm() {
		return subMenuNm;
	}
	public String getSubMenuAuth() {
		return subMenuAuth;
	}
	public String getUseYn() {
		return useYn;
	}
	public String getSubMenuFxId() {
		return subMenuFxId;
	}
	public void setSubMenuId(String subMenuId) {
		this.subMenuId = subMenuId;
	}
	public void setTopMenuId(String topMenuId) {
		this.topMenuId = topMenuId;
	}
	public void setSubMenuDscrtn(String subMenuDscrtn) {
		this.subMenuDscrtn = subMenuDscrtn;
	}
	public void setSubMenuNm(String subMenuNm) {
		this.subMenuNm = subMenuNm;
	}
	public void setSubMenuAuth(String subMenuAuth) {
		this.subMenuAuth = subMenuAuth;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public void setSubMenuFxId(String subMenuFxId) {
		this.subMenuFxId = subMenuFxId;
	}
	public SubMenuDTO(String subMenuId, String topMenuId, String subMenuDscrtn, String subMenuNm, String subMenuAuth,
			String useYn, String subMenuFxId) {
		super();
		this.subMenuId = subMenuId;
		this.topMenuId = topMenuId;
		this.subMenuDscrtn = subMenuDscrtn;
		this.subMenuNm = subMenuNm;
		this.subMenuAuth = subMenuAuth;
		this.useYn = useYn;
		this.subMenuFxId = subMenuFxId;
	}
	public SubMenuDTO() {
		super();
	}
	@Override
	public String toString() {
		return "SubMenuDTO [subMenuId=" + subMenuId + ", topMenuId=" + topMenuId + ", subMenuDscrtn=" + subMenuDscrtn
				+ ", subMenuNm=" + subMenuNm + ", subMenuAuth=" + subMenuAuth + ", useYn=" + useYn + ", subMenuFxId="
				+ subMenuFxId + "]";
	}
	
    
}