package justek.ide.model.member;

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
public class MemberDTO {

    private  String memberId;
    private  String password;
    private  String memberAuth;
    private  String useYn;
	
    
	public MemberDTO(String memberId, String password, String memberAuth, String useYn) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.memberAuth = memberAuth;
		this.useYn = useYn;
	}
	public MemberDTO() {
		super();
	}
	public String getMemberId() {
		return memberId;
	}
	public String getPassword() {
		return password;
	}
	public String getMemberAuth() {
		return memberAuth;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setMemberAuth(String memberAuth) {
		this.memberAuth = memberAuth;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	@Override
	public String toString() {
		return "Person [memberId=" + memberId + ", password=" + password + ", memberAuth=" + memberAuth + ", useYn="
				+ useYn + "]";
	}

    
}