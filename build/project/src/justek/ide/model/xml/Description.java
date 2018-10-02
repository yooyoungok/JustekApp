package justek.ide.model.xml;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
        "Devices",
})

public class Description {
	 @JsonProperty("Devices")
	 private Devices devices;

	@JsonProperty("Devices")
	public Devices getDevices() {
		return devices;
	}
	
	@JsonProperty("Devices")
	public void setDevices(Devices device) {
		this.devices = device;
	}

	@Override
	public String toString() {
		return "Description [device=" + devices + "]";
	}
	
}
