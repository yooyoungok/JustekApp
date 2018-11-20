
package justek.ide.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import justek.ide.MainApp;
import justek.ide.model.CommandConst;
import justek.ide.model.listener.TreeEventListener;
import justek.ide.model.manager.DialogManager;
import justek.ide.model.manager.NetworkServerManager;
import justek.ide.model.xml.Description;
import justek.ide.model.xml.Device;
import justek.ide.model.xml.Vendor;
import justek.ide.model.xml.XMLUtils;
import justek.ide.model.xmlv2.EtherCATInfoV2;
import justek.ide.model.xml.EtherCATInfo;


public class EtherCATSlaveController2 implements TreeEventListener{
	
	static final String Tag = "EtherCATSlaveController2";
	
	private MainApp mainApp;
	
	@FXML
	private TextField deviceType;
	
	@FXML
	private TextField productCode;
	
	@FXML
	private TextField revisionNumber;
	
	@FXML
	private TextField physicalAddress;
	
	@FXML
	private TextField vendorId;
	//ksg-s
	@FXML
	private TextField vendorName;
	
	@FXML
	private TextField name;
	@FXML
	private Label etherCATxml;
	//ksg-e
	@FXML
	private TextField deviceName;
	
	
	@FXML
	private TextField fxCurrentStateField;
	
	@FXML
	private TextField fxReqestStateField;
	
	XMLUtils mXmlUtils;
	
	private Vendor mVendor;
	private Description mDesrcription;
	private Device mDevice;
	
	private List<EtherCATInfo> mEtherCATList;
	
	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @author : YOO YOUNGOK 
	 * @method  setMainApp
	 * @param mainApp
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 19.
	 */
	public void setMainApp(MainApp mainApp) {
	  this.mainApp = mainApp;
	  this.mainApp.listener = this;
	} 
	
    @FXML
    private void initialize() {
//ksg-s
    	//삽입-파일 읽어오기
    	File file = getXmlFilePath();
        if (file != null) {
        	loadEtherCATdataFromFile(file);
        }
    	//삽입-파일 읽어오기-e
    	
//    	mXmlUtils = XMLUtils.getInstance();
//    	try {
//    		this.mXmlUtils.getXmlToJasonArray();
//    		this.mEtherCATList = this.mXmlUtils.getEtherCATList();
//	    	this.getSlaveData();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
        this.getCurrentFieldData();
    }
//ksg-e
    
//ksg-s

    public void readXMLFile() {
    	File file = getXmlFilePath();
        if (file != null) {
        	loadEtherCATdataFromFile(file);
        }
    }
    
    //xml파일패쓰 메모리에서  읽어오기
    public File getXmlFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }
  //xml파일패쓰 메모리에 저장
    public void setXmlFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // 파일 이름 라벨에  표시하기
            this.etherCATxml.setText("EtherCAT Configuration - "+file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            this.etherCATxml.setText("EtherCAT Configuration");
        }
    }
    /**
     * Creates an empty address book.
     */
//    @FXML
//    private void handleNew() {
//        mainApp.getEtherCATdata().clear();
//        mainApp.setEtherCATfilePath(null);
//    }

    /**
     * Opens a FileChooser to let the user select an address book to load.
     */
    
    //Xml 파일 열기 
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            loadEtherCATdataFromFile(file);
        }
    }
    //xml 데이타 읽기
  	public void loadEtherCATdataFromFile(File file) {
        	  System.out.println("loadEtherCATdataFromFile!");
        	  
        	  int deviceNum = CommandConst.DRIVER_NUMBEER-1;
          	//삽입-s
        	  try {
        	         //역마샬링-s
        	         JAXBContext context = JAXBContext
        	        		 .newInstance(EtherCATInfoV2.class);
        	         Unmarshaller um = context.createUnmarshaller();
        	         EtherCATInfoV2 etherCATInfoV2 = (EtherCATInfoV2) um.unmarshal(file);
        	         //역마샬링-e
        	         
        	         //변수 선언 예시
        	         justek.ide.model.xmlv2.EtherCATInfoV2.Vendor vendor;
        	         justek.ide.model.xmlv2.EtherCATInfoV2.Descriptions descriptions;
//        	         List<justek.ide.model.xmlv2.EtherCATInfoV2.Descriptions.Devices.Device.Name> Name;
//        	         Name = etherCATInfoV2.getDescriptions().getDevices().getDevice().get(0).getName();        	         
        	         vendor = etherCATInfoV2.getVendor();
        	         descriptions = etherCATInfoV2.getDescriptions();

        	         if(descriptions.getDevices().getDevice().size()<CommandConst.DRIVER_NUMBEER-1) {
        	        	 DialogManager.getInstance().showServerErrorConfirmDialog("No Device Data");
        	        	 resetData();
        	        	 return;
        	         }

        	         ///예시
        	         System.out.println("vendor.Name():"+vendor.getName());
        	         System.out.println("vendor.id():"+etherCATInfoV2.getVendor().getId());
        	         System.out.println("device.Name():"+descriptions.getDevices().getDevice().get(deviceNum).getName().get(0).getValue());
        	         System.out.println("device.type():"+descriptions.getDevices().getDevice().get(deviceNum).getType().getValue());
        	         System.out.println("product.Code():"+descriptions.getDevices().getDevice().get(deviceNum).getType().getProductCode());
        	         System.out.println("Rivision.No():"+descriptions.getDevices().getDevice().get(deviceNum).getType().getRevisionNo());
        	         System.out.println("device.profileNo():"+Integer.toString((descriptions.getDevices().getDevice().get(deviceNum).getProfile().getChannelInfo().getProfileNo())));
        	        
        	        this.vendorName.setText(vendor.getName());
        	        this.name.setText(vendor.getName());
        	        this.vendorId.setText(vendor.getId());
        	    	this.deviceName.setText(descriptions.getDevices().getDevice().get(deviceNum).getName().get(0).getValue());
        	    	this.productCode.setText(descriptions.getDevices().getDevice().get(deviceNum).getType().getProductCode());
        	    	this.deviceType.setText(descriptions.getDevices().getDevice().get(deviceNum).getType().getValue());
        	    	this.revisionNumber.setText(descriptions.getDevices().getDevice().get(0).getType().getRevisionNo());
        	    	//xml파일 패쓰 메모리에 리셋
        	    	setXmlFilePath(file);
        	    	///
        	    	
        	    	} catch (Exception e) { // catches ANY exception
        	        	e.printStackTrace();
//        	        	Alert alert = new Alert(AlertType.ERROR);
//        	        	alert.setTitle("Error");
//        	        	alert.setHeaderText("Could not load data");
//        	        	alert.setContentText("Could not load data from file:\n" );
//        	        	alert.showAndWait();
        	        }

      }

    /**
     * Saves the file to the person file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
//    @FXML
//    private void handleSave() {
//        File etherCATfile = mainApp.getEtherCATfilePath();
//        if (etherCATfile != null) {
//            mainApp.saveEtherCATdataToFile(etherCATfile);
//        } else {
//            handleSaveAs();
//        }
//    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
//    @FXML
//    private void handleSaveAs() {
//        FileChooser fileChooser = new FileChooser();
//
//        // Set extension filter
//        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
//                "XML files (*.xml)", "*.xml");
//        fileChooser.getExtensionFilters().add(extFilter);
//
//        // Show save file dialog
//        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
//
//        if (file != null) {
//            // Make sure it has the correct extension
//            if (!file.getPath().endsWith(".xml")) {
//                file = new File(file.getPath() + ".xml");
//            }
//            mainApp.saveEtherCATdataToFile(file);
//        }
//    }
//ksg-e 
  	
    public void getSlaveData() {
    	if(this.mEtherCATList!=null && this.mEtherCATList.size()>=CommandConst.DRIVER_NUMBEER) {
    		EtherCATInfo info = this.mEtherCATList.get(CommandConst.DRIVER_NUMBEER-1);
    		if(info!=null) {
    			this.mVendor = info.getVender();
    			this.mDesrcription = info.getDescription();
    			if(mVendor!=null) {
    				this.vendorId.setText(mVendor.getId());
    			}

    			if(this.mDesrcription!=null) {
    				this.mDevice = this.mDesrcription.getDevices().getDevice();
    				System.out.println("EtherCATSlaveController=="+this.mDevice.toString());
    				this.deviceName.setText(this.mDevice.getName());
    				this.productCode.setText(this.mDevice.getType().getProductCode());
    				this.deviceType.setText(this.mDevice.getType().getContent());
    				this.revisionNumber.setText(this.mDevice.getType().getRevisionNo());
    			}
    			else {
    				this.deviceName.setText("");
    				this.productCode.setText("");
    				this.deviceType.setText("");
    				this.revisionNumber.setText("");
    			}
    		}
    	}
		else {
			this.vendorId.setText("");
			this.deviceName.setText("");
			this.productCode.setText("");
			this.deviceType.setText("");
			this.revisionNumber.setText("");
		}
    }

    /**
     * EtherCAT Slave의 모든 값을 초기화 한다.
     * @author : YOO YOUNGOK 
     * @method  resetData
     * @return  void
     * @exception 
     * @see
     * @since 2018. 6. 26.
     */
    private void resetData() {
		this.name.setText("");
		this.vendorName.setText("");
		this.vendorId.setText("");
		this.deviceName.setText("");
		this.productCode.setText("");
		this.deviceType.setText("");
		this.revisionNumber.setText("");
    }
    
    
    /**
     * @author : YOO YOUNGOK 
     * @method  getCurrentFieldData
     * @return  void
     * @exception 
     * @desc CurrentStateField 정보를 가져온다.. 
     * @since 2018. 8. 27.
     */
    private void getCurrentFieldData() {
    	System.out.println("EtherCATSlaveController == getCurrentFieldData ");
    	
    	String result = NetworkServerManager.getInstance().getCommandData("ethercat slaves -p"+String.valueOf(CommandConst.DRIVER_NUMBEER));
    	
    	if(result==null) return;
    	
    	String[] list = result.split(" ");
    	String status = list[4];
    	for(int i=0 ;i<list.length;i++) {
    		System.out.println(list[i]);
    	}
    	this.fxCurrentStateField.setText(status);
    	
    	
    }

	@Override
	public void clieckMenu() {
		// TODO Auto-generated method stub
		System.out.println("EtherCATSlaveController == clieckMenu ");
		
		if(CommandConst.isController) {
			System.out.println("EtherCATSlaveController ==isController 상태입니다. ");
			this.mainApp.EtherCATMaster();
			return;
		}
		
		if(this.mEtherCATList == null) {
			this.readXMLFile();
	        return;
		}
		
		if(this.mEtherCATList.size()>=CommandConst.DRIVER_NUMBEER) {
			System.out.println("EtherCATSlaveController ==isController가 아닌 상태입니다. ");
			this.getSlaveData();
			this.getCurrentFieldData();
		}
    	else {
    		resetData();
    		this.getCurrentFieldData();
    	}
	}
    
    @FXML
    void onClickProOp(ActionEvent event) {
    	System.out.println("EtherCATSlaveController == onClickProOp ");
    	this.fxReqestStateField.setText("PREOP");
    	NetworkServerManager.getInstance().changeEtherCATMode("PREOP",String.valueOf(CommandConst.DRIVER_NUMBEER));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	this.getCurrentFieldData();
    }

    @FXML
    void onClickSafeOp(ActionEvent event) {
    	System.out.println("EtherCATSlaveController == onClickSafeOp ");
    	this.fxReqestStateField.setText("SAFEOP");
    	NetworkServerManager.getInstance().changeEtherCATMode("SAFEOP",String.valueOf(CommandConst.DRIVER_NUMBEER));
    	try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
    	this.getCurrentFieldData();
    }
    
    @FXML
    void onClickOperational(ActionEvent event) {
    	System.out.println("EtherCATSlaveController == onClickOperational ");
    	this.fxReqestStateField.setText("OP");
    	NetworkServerManager.getInstance().changeEtherCATMode("OP",String.valueOf(CommandConst.DRIVER_NUMBEER));
    	try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
    	this.getCurrentFieldData();
    }
}
