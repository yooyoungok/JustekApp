package justek.ide.model.xml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
        "Id",
})

public class Vendor {
	@JsonProperty("Id")
	String id;
	
	@JsonProperty("Id")
	public String getId() {
		return id;
	}
	@JsonProperty("Id")
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Vender [id=" + id + "]";
	}
	
}
