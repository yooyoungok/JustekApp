package justek.ide.model.xml;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * @author cty
 *
 */
/**
 * @author cty
 *
 */
public class XMLUtils {
	
	public static int PRETTY_PRINT_INDENT_FACTOR = 4;
	
	private static XMLUtils instance;

	public static XMLUtils getInstance () {
		if(instance==null) {
			instance = new XMLUtils();
		}
		return instance;
	}
	
	
	private Vendor mVendor;
	private Description mDescription;
	private List<EtherCATInfo> etherCATList;

	
	/*******************
	 * 
	 * XML파일을 JSON으로 생성한다.
	 * 
	 ****************** */
	public void getXmlToJason() throws IOException {
		//2.XML to Json
		String jsonPrettyPrintString = "";
		try {
			
			//add(2018.04.05)
			
			JSONObject xmlJSONObj = XML.toJSONObject(this.readLineByLineJava8("./home/xml/sm01.xml"));
//			System.out.println(xmlJSONObj.toString());
			jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
//			System.out.println(jsonPrettyPrintString);
			
			//json을 xml로 변환
//			String xml_data = XML.toString(xmlJSONObj);
//			String xmlPrintString = xml_data.toString();
//			System.out.println(xmlPrintString);
		} catch (JSONException je) {
			System.out.println(je.toString());
		}

		//create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		//convert json string to object
//		EtherCATInfo emp = objectMapper.readValue(jsonPrettyPrintString.getBytes(), EtherCATInfo.class);
		JsonNode node = objectMapper.readTree(jsonPrettyPrintString.getBytes());
		EtherCATInfo info = objectMapper.readValue(node.get("EtherCATInfo").toString(), EtherCATInfo.class);
		
		
		/*
		 * EtherCATInfo정보에서 Vendor정보를 가져온다.
		 * */
		this.mVendor = info.getVender();
		this.mDescription = info.getDescription();
		
//		System.out.println(info.toString());
		
		this.getXmlToJasonArray();
		
		//read JSON like DOM Parser
//		JsonNode rootNode = objectMapper.readTree(jsonData);
//		JsonNode idNode = rootNode.path("id");
//		System.out.println("id = "+idNode.asInt());

//		JsonNode phoneNosNode = rootNode.path("phoneNumbers");
//		Iterator<JsonNode> elements = phoneNosNode.elements();
//		while(elements.hasNext()){
//			JsonNode phone = elements.next();
//			System.out.println("Phone No = "+phone.asLong());
//		}
	}

	public void getXmlToJasonArray() throws IOException {
		//2.XML to Json
		String jsonPrettyPrintString = "";
		try {
			
			//add(2018.04.05)
			JSONObject xmlJSONObj = XML.toJSONObject(this.readLineByLineJava8("./home/xml/ec_xml.xml"));
//			System.out.println(xmlJSONObj.toString());
			jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
//			System.out.println("getXmlToJasonArray === "+jsonPrettyPrintString);
			
		} catch (JSONException je) {
			System.out.println(je.toString());
		}

		//create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = objectMapper.readTree(jsonPrettyPrintString.getBytes());
		EtherCATInfoList info = objectMapper.readValue(node.get("EtherCATInfoList").toString(), EtherCATInfoList.class);
		
		if(info!=null) {
			this.etherCATList =info.getEtherCATInfo();
			for(EtherCATInfo info1 : this.etherCATList) {
				System.out.println("  EtherCATInfo ==  "+info1.toString());
			}
		}
		
		//json을 xml로 변환
//		String xml_data = XML.toString(xmlJSONObj);
//		String xmlPrintString = xml_data.toString();
//		System.out.println(xmlPrintString);
	}
			
	
    private String readLineByLineJava8(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();
 
        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
 
        return contentBuilder.toString();
    }

    
	public Vendor getVendor() {
		return mVendor;
	}

	public void setVendor(Vendor mVendor) {
		this.mVendor = mVendor;
	}

	public Description getDescription() {
		return mDescription;
	}

	public void setDescription(Description mDescription) {
		this.mDescription = mDescription;
	}

	public List<EtherCATInfo> getEtherCATList() {
		return etherCATList;
	}

	public void setEtherCATList(List<EtherCATInfo> etherCATList) {
		this.etherCATList = etherCATList;
	}
	
	
	

}
