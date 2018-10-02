package justek.ide.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SDOInfo {
	
	public StringProperty  index;
	public StringProperty subIndex;
	public StringProperty  name;
	public StringProperty  value;
	public StringProperty  name2;
	public StringProperty  value2;
	public StringProperty  permission;
	public StringProperty  defaultValue;
	public StringProperty  description;
	public StringProperty unitType;
	
	public void setIndex(String index) {
		this.index = new SimpleStringProperty(index);
	}
	
	public void setSubIndex(String index) {
		this.subIndex = new SimpleStringProperty(index);
	}
	
	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}
	
	public void setValue(String value) {
		this.value =  new SimpleStringProperty(value);
	}
	
	public void setUnitType(String type) {
		this.unitType = new SimpleStringProperty(type);
	}
	
	public void setName2(String name) {
		this.name2 = new SimpleStringProperty(name);
	}
	
	public void setValue2(String value) {
		this.value2 =  new SimpleStringProperty(value);
	}
	
	public void setPermission(String  permission) {
		this.permission = new SimpleStringProperty(permission);
	}
	
	public void setDefaultValue(String  defaultValue) {
		this.defaultValue =  new SimpleStringProperty(defaultValue);
	}
	
	public void setDescription(String  description) {
		this.description =  new SimpleStringProperty(description);
	}
	
	
	public String  getIndex() {
		return this.index.get();
	}
	
	public String  getName() {
		return this.name.get();
	}
	
	public String  getValue() {
		return this.value.get();
	}
	
	public String  getValue2() {
		if(this.value2!=null) {
			return this.value2.get();
		}
		else {
			return null;
		}
	}
	
	public String  getUnitType() {
		return this.unitType.get();
	}
	
	public String  getDescription() {
		return this.description.get();
	}
	
	public String  getPermssion() {
		return this.permission.get();
	}
	
	public String  getDefaultValue() {
		return this.defaultValue.get();
	}
	
	public String toString() {
		String st = "SDOInfo :: index =" + this.index +"\n"
				   + "SDOInfo :: name =" + this.name +"\n"
				   + "SDOInfo :: unitType =" + this.unitType +"\n"
				   + "SDOInfo :: vaule =" + this.value +"\n"
				   + "SDOInfo :: vaule2 =" + this.value2 +"\n";
		
		return st;
	}

}
