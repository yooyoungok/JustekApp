package justek.ide.model.xml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
        "Vendor",
        "Descriptions",
})

public class EtherCATInfo {
	@JsonProperty("Vendor")
	private Vendor vendor;
	@JsonProperty("Descriptions")
	private Description descriptions;
	    
	@JsonProperty("Vendor")
	public Vendor getVender() {
		return vendor;
	}
	@JsonProperty("Vendor")
	public void setVender(Vendor vender) {
		this.vendor = vender;
	}
	@JsonProperty("Descriptions")
	public Description getDescription() {
		return descriptions;
	}
	@JsonProperty("Descriptions")
	public void setDescription(Description description) {
		this.descriptions = description;
	}
		
	@Override
	public String toString() {
		return "EtherCATInfo [vender=" + vendor + ", description=" + descriptions + "]";
	}
	    
}
