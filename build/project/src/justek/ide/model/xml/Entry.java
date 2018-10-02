package justek.ide.model.xml;

public class Entry {
	private String index;
	private String subIndex;
	private String bitLen;
	private String name;
	private String dataType;
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getSubIndex() {
		return subIndex;
	}
	public void setSubIndex(String subIndex) {
		this.subIndex = subIndex;
	}
	public String getBitLen() {
		return bitLen;
	}
	public void setBitLen(String bitLen) {
		this.bitLen = bitLen;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	@Override
	public String toString() {
		return "Entry [index=" + index + ", subIndex=" + subIndex + ", bitLen=" + bitLen + ", name=" + name
				+ ", dataType=" + dataType + "]";
	}

}
