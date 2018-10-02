package justek.ide.model;

import javafx.beans.property.StringProperty;

public class DriverInfo {
	
	public StringProperty  driverNo;
	public StringProperty  name;
	public StringProperty  value;
	public StringProperty  singnal;
	
	public StringProperty getDriverNo() {
		return driverNo;
	}
	public void setDriverNo(StringProperty driverNo) {
		this.driverNo = driverNo;
	}
	public StringProperty getName() {
		return name;
	}
	public void setName(StringProperty name) {
		this.name = name;
	}
	public StringProperty getValue() {
		return value;
	}
	public void setValue(StringProperty value) {
		this.value = value;
	}
	public StringProperty getSingnal() {
		return singnal;
	}
	public void setSingnal(StringProperty singnal) {
		this.singnal = singnal;
	}
	@Override
	public String toString() {
		return "DriverInfo [driverNo=" + driverNo + ", name=" + name + ", value=" + value + ", singnal=" + singnal
				+ "]";
	}
}
