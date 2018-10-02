package justek.ide.model.xml;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
	"Type",
    "Name",
    "Sm",
    "RxPdo",
    "TxPdo",
})

@JsonIgnoreProperties({ "Sm", "RxPdo", "TxPdo" })

public class Device {
	@JsonProperty("Type")
	private Type type;
	@JsonProperty("Name")
	private String name;
	@JsonProperty("Sm")
	private List<Sm> SmList = new ArrayList<Sm>();
	@JsonProperty("Rxpdo")
	private RxTxPdo rxpdo;
	@JsonProperty("Txpdo")
	private RxTxPdo txpdo;
	
	@JsonProperty("Type")
	public Type getType() {
		return type;
	}
	
	@JsonProperty("Type")
	public void setType(Type type) {
		this.type = type;
	}
	
	@JsonProperty("Name")
	public String getName() {
		return name;
	}
	
	@JsonProperty("Name")
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("Sm")
	public List<Sm> getSmList() {
		return SmList;
	}
	
	@JsonProperty("Sm")
	public void setSmList(List<Sm> smList) {
		SmList = smList;
	}
	
	@JsonProperty("Rxpdo")
	public RxTxPdo getRxpdo() {
		return rxpdo;
	}
	
	@JsonProperty("Rxpdo")
	public void setRxpdo(RxTxPdo rxpdo) {
		this.rxpdo = rxpdo;
	}
	
	@JsonProperty("Txpdo")
	public RxTxPdo getTxpdo() {
		return txpdo;
	}
	
	@JsonProperty("Txpdo")
	public void setTxpdo(RxTxPdo txpdo) {
		this.txpdo = txpdo;
	}
	
	@Override
	public String toString() {
		return "Device [type=" + type + ", name=" + name + ", SmList=" + SmList + ", rxpdo=" + rxpdo + ", txpdo="
				+ txpdo + "]";
	}
}
