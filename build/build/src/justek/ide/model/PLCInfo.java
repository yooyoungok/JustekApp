package justek.ide.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PLCInfo {
	public StringProperty  name;
	public StringProperty  path;
	public boolean isRun;
	
	public void setPath(String path) {
		this.path = new SimpleStringProperty(path);
	}
	
	public String  getPath() {
		return this.path.get();
	}
	
	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}
	
	public String  getName() {
		return this.name.get();
	}
	
	public String toString() {
		String st ="PLCInfo :: name =" + this.name +"\n"
				+"PLCInfo :: path =" + this.path +"\n";;
		
		return st;
	}
}
