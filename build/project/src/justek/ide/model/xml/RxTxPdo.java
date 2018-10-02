package justek.ide.model.xml;

import java.util.ArrayList;
import java.util.List;

public class RxTxPdo {
	private String index;
	private String name;
	private List<Entry> entryList = new ArrayList<Entry>();
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Entry> getEntryList() {
		return entryList;
	}
	public void setEntryList(List<Entry> entryList) {
		this.entryList = entryList;
	}
	@Override
	public String toString() {
		return "RxTxPdo [index=" + index + ", name=" + name + ", entryList=" + entryList + "]";
	} 
	
}
