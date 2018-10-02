package justek.ide.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import justek.ide.MainApp;
import justek.ide.model.CommandConst;
import justek.ide.model.listener.TreeEventListener;
import justek.ide.model.xml.Description;
import justek.ide.model.xml.Device;
import justek.ide.model.xml.Vendor;
import justek.ide.model.xml.XMLUtils;
import justek.ide.model.xml.EtherCATInfo;

public class EtherCATSlaveController implements TreeEventListener{
	
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
	
	XMLUtils mXmlUtils;
	
	private Vendor mVendor;
	private Description mDesrcription;
	private Device mDevice;
	
	private List<EtherCATInfo> mEtherCATList;
	
	/**
	* Is called by the main application to give a reference back to itself.
	* 
	* @param mainApp
	*/
	public void setMainApp(MainApp mainApp) {
	  this.mainApp = mainApp;
	  this.mainApp.listener = this;
	} 
	
    @FXML
    private void initialize() {
    	mXmlUtils = XMLUtils.getInstance();
 
    	try {
    		this.mXmlUtils.getXmlToJasonArray();
    		this.mEtherCATList = this.mXmlUtils.getEtherCATList();
	    	this.getSlaveData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    } 
    
    
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

	@Override
	public void clieckMenu() {
		// TODO Auto-generated method stub
		System.out.println("EtherCATSlaveController == clieckMenu ");
		
		if(CommandConst.isController) {
			System.out.println("EtherCATSlaveController ==isController 상태입니다. ");
			this.mainApp.EtherCATMaster();
			return;
		}
		
		if(this.mEtherCATList.size()>=CommandConst.DRIVER_NUMBEER) {
			System.out.println("EtherCATSlaveController ==isController가 아닌 상태입니다. ");
			this.getSlaveData();
		}
    	else {
    		this.vendorId.setText("");
			this.deviceName.setText("");
			this.productCode.setText("");
			this.deviceType.setText("");
			this.revisionNumber.setText("");
    	}
	}
    
    
}
