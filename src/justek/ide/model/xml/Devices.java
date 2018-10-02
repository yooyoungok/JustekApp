package justek.ide.model.xml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
	"Device",
})

public class Devices {
		 @JsonProperty("Device")
		 private Device device;
//		private List<Device> deviceList = new ArrayList<Device>();

		@JsonProperty("Devices")
		public Device getDevice() {
			return device;
		}
		
		@JsonProperty("Devices")
		public void setDevice(Device device) {
			this.device = device;
		}

		@Override
		public String toString() {
			return "Description [device=" + device + "]";
		}
}
