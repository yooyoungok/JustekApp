package justek.ide.model.xml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
        "RevisionNo",
        "ProductCode",
        "content",
})

public class Type {
	
	@JsonProperty("RevisionNo")
	private String productCode;
	@JsonProperty("ProductCode")
	private String revisionNo;
	@JsonProperty("content")
	private String content;
	
	@JsonProperty("RevisionNo")
	public String getProductCode() {
		return productCode;
	}
	
	@JsonProperty("RevisionNo")
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	@JsonProperty("ProductCode")
	public String getRevisionNo() {
		return revisionNo;
	}
	
	@JsonProperty("ProductCode")
	public void setRevisionNo(String revisionNo) {
		this.revisionNo = revisionNo;
	}
	
	@JsonProperty("content")
	public String getContent() {
		return content;
	}
	
	@JsonProperty("content")
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "Type [productCode=" + productCode + ", revisionNo=" + revisionNo + ", content=" + content + "]";
	}
	
}
