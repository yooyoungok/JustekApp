package justek.ide.model.subMenu;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class TabMenuDTO {

	private  String tabMenuId;
    private  String subMenuId;
    private  String tabMenuNm;
    private  String tabMenuDscrtn;
    private  String tabMenuAuth;
    private  String useYn;
    private  String tabMenuFxId;
	public String getTabMenuId() {
		return tabMenuId;
	}
	public String getSubMenuId() {
		return subMenuId;
	}
	public String getTabMenuNm() {
		return tabMenuNm;
	}
	public String getTabMenuDscrtn() {
		return tabMenuDscrtn;
	}
	public String getTabMenuAuth() {
		return tabMenuAuth;
	}
	public String getUseYn() {
		return useYn;
	}
	public String getTabMenuFxId() {
		return tabMenuFxId;
	}
	public void setTabMenuId(String tabMenuId) {
		this.tabMenuId = tabMenuId;
	}
	public void setSubMenuId(String subMenuId) {
		this.subMenuId = subMenuId;
	}
	public void setTabMenuNm(String tabMenuNm) {
		this.tabMenuNm = tabMenuNm;
	}
	public void setTabMenuDscrtn(String tabMenuDscrtn) {
		this.tabMenuDscrtn = tabMenuDscrtn;
	}
	public void setTabMenuAuth(String tabMenuAuth) {
		this.tabMenuAuth = tabMenuAuth;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public void setTabMenuFxId(String tabMenuFxId) {
		this.tabMenuFxId = tabMenuFxId;
	}
	public TabMenuDTO(String tabMenuId, String subMenuId, String tabMenuNm, String tabMenuDscrtn, String tabMenuAuth,
			String useYn, String tabMenuFxId) {
		super();
		this.tabMenuId = tabMenuId;
		this.subMenuId = subMenuId;
		this.tabMenuNm = tabMenuNm;
		this.tabMenuDscrtn = tabMenuDscrtn;
		this.tabMenuAuth = tabMenuAuth;
		this.useYn = useYn;
		this.tabMenuFxId = tabMenuFxId;
	}
	public TabMenuDTO() {
		super();
	}
	@Override
	public String toString() {
		return "TabMenuDTO [tabMenuId=" + tabMenuId + ", subMenuId=" + subMenuId + ", tabMenuNm=" + tabMenuNm
				+ ", tabMenuDscrtn=" + tabMenuDscrtn + ", tabMenuAuth=" + tabMenuAuth + ", useYn=" + useYn
				+ ", tabMenuFxId=" + tabMenuFxId + "]";
	}
    
}