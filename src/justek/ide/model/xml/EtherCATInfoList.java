package justek.ide.model.xml;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
	"EtherCATInfo",
})

public class EtherCATInfoList {
	
	private List<EtherCATInfo> etherCATInfo = new ArrayList<EtherCATInfo>();

	@JsonProperty("EtherCATInfo")
	public List<EtherCATInfo> getEtherCATInfo() {
		return etherCATInfo;
	}

	public void setEtherCATInfo(List<EtherCATInfo> etherCATInfo) {
		this.etherCATInfo = etherCATInfo;
	}

	@Override
	public String toString() {
		return "EtherCATInfoList [etherCATInfo=" + etherCATInfo + "]";
	}
}
