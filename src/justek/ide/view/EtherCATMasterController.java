package justek.ide.view;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import justek.ide.MainApp;
import justek.ide.model.CommandConst;
import justek.ide.model.EditingCell;
import justek.ide.model.EtherCATMasterInfo;
import justek.ide.model.LabelCell;
import justek.ide.model.SDOInfo;
import justek.ide.model.listener.TreeEventListener;
import justek.ide.model.manager.NetworkServerManager;

public class EtherCATMasterController implements TreeEventListener {

    @FXML
    private Label transmittedLabel;

    @FXML
    private Label dropTransLabel;

    @FXML
    private TextField mailboxCycleTime;

    @FXML
    private Label wrongWCLabel;

    @FXML
    private Label slaveResponseLabel;

    @FXML
    private TextField destinationMAC;

    @FXML
    private TextField numberOfSlave2;

    @FXML
    private TextField cpuLoad;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private CheckBox enablePropDelayCheckbox;

    @FXML
    private TextField EthernetFrameMaxSize;

    @FXML
    private TableView<EtherCATMasterInfo> fxTableView;

    @FXML
    private Label receivedLabel;

    @FXML
    private TableColumn<?, ?> cyclicLengthColumn;

    @FXML
    private TextField cycleTime;

    @FXML
    private ComboBox<?> initMasterStateCombo;

    @FXML
    private Label receiveErrorLabel;

    @FXML
    private CheckBox sepIOUpdateCheckbox;

    @FXML
    private TextField numberOfSlave;

    @FXML
    private Label receiveBytesLabel;

    @FXML
    private RadioButton slaveClockRadio;

    @FXML
    private TableColumn<?, ?> typeColumn;

    @FXML
    private TextField networkState;

    @FXML
    private CheckBox writeMasterTimeCheckbox;

    @FXML
    private Label transBytesLabel;

    @FXML
    private CheckBox sepCyclicCmdCheckbox;

    @FXML
    private ToggleGroup clockGroup;

    @FXML
    private Label sendErrorLabel;

    @FXML
    private ComboBox<?> eoeIPStartAddressCombo;

    @FXML
    private RadioButton masterClockRadio;

    @FXML
    private Label maxMailboxLabel;

    @FXML
    private TableColumn<?, ?> cyclickLogicalColumn;

    @FXML
    private Label dropReceivedLabel;

    @FXML
    private ToggleGroup networkStateGroup;

    @FXML
    private TextField busLoad;

    @FXML
    private TextField requestState;

    @FXML
    private Label minCycleLabel;

    @FXML
    private Label averageMailboxLabel;

    @FXML
    private Label averageJitterMailboxLabel;

    @FXML
    private TextField portNo;

    @FXML
    private TextField hostIPAddress;

    @FXML
    private TableColumn<?, ?> varOffsetColumn;

    @FXML
    private Label wrongReceiveLabel;

    @FXML
    private ComboBox<?> maxCycleErrorCombo;


    @FXML
    private ToggleGroup useLRWGroup;

    @FXML
    private Label multicastLabel;

    @FXML
    private RadioButton lrwCheckbox;

    @FXML
    private RadioButton lrdCheckbox;

    @FXML
    private RadioButton fprdCheckbox;

    @FXML
    private Label averageJitterCycleLabel;

    @FXML
    private Label wrongTransLabel;

    @FXML
    private Label averageCycleLabel;

    @FXML
    private TextField sourceMAC;

    @FXML
    private Label parseErrorLabel;

    @FXML
    private CheckBox dcEnableCheckbox;

    @FXML
    private ComboBox<String> watchDogCombo;

    @FXML
    private ComboBox<String> slaveClockCombo;

    @FXML
    private TableColumn<?, ?> valueColumn;    

    @FXML
    private TableColumn<?, ?> bitSizeColumn;
    
    @FXML
    private TableColumn<?, ?> cyclicCountColumn;
    
    @FXML
    private TableColumn<?, ?> piOffsetColumn;

    @FXML
    private TableColumn<EtherCATMasterInfo,String> diagLostLinkAColumn;
    
    @FXML
    private TableColumn<EtherCATMasterInfo,String> diagRxErrorBColumn;

    @FXML
    private TableColumn<EtherCATMasterInfo,String> diagLostLinkBColumn;
    
    @FXML
    private TableColumn<EtherCATMasterInfo,String> diagRxErrorAColumn;
    
    @FXML
    private TableColumn<EtherCATMasterInfo,String> diagNameColumn;    
    
    @FXML
    private TableColumn<EtherCATMasterInfo,String> diagPositionColumn;    
    
    @FXML
    private TableColumn<EtherCATMasterInfo,String> diagStateColumn;
    

    @FXML
    private TableColumn<?, ?> cyclicCommandColumn;

    @FXML
    private TableColumn<?, ?> aliasColumn;
    
    @FXML
    private Label framePerSecLabel;

    @FXML
    private Label maxCycleLabel;

    @FXML
    private TextField autoRecoveryTimeout;

    @FXML
    private TextField currentState;

    @FXML
    private Label minMailboxLabel;

    @FXML
    private Label collisionLabel;
    
	
	private MainApp mainApp;
	
	private ObservableList<EtherCATMasterInfo> diagTableData;
	
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
		this.setDiagTableView();
	}
	
	
	private void setDiagTableView() {
		this.diagNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
		this.diagPositionColumn.setCellValueFactory(cellData -> cellData.getValue().getPosition());
		this.diagRxErrorAColumn.setCellValueFactory(cellData -> cellData.getValue().getRxErrorA());
		this.diagLostLinkAColumn.setCellValueFactory(cellData -> cellData.getValue().getLostLinksA());
		this.diagRxErrorBColumn.setCellValueFactory(cellData -> cellData.getValue().getRxErrorB());
		this.diagLostLinkBColumn.setCellValueFactory(cellData -> cellData.getValue().getLostLinksb());
		this.diagStateColumn.setCellValueFactory(cellData -> cellData.getValue().getState());
		
//		this.setDummyData();
		this.getCurrentFieldData();
		this.fxTableView.setItems(this.diagTableData);
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
		this.diagTableData  = FXCollections.observableArrayList();
		
    	ObservableList<String>  result = NetworkServerManager.getInstance().getEtherCATDAta();
    	if(result!=null) {
    		for(int i=1;i<result.size();i++) {
    			System.out.println(result.get(i));
    			EtherCATMasterInfo info = new EtherCATMasterInfo();
    			String[] list = result.get(i).split(" ");
    			info.setName(CommandConst.driverList.get(i-1));
    			info.setState(list[4]);
    			this.diagTableData.add(info);
    		}
    	}
    	else {
    		return;
    	}
    }

    
	@Override
	public void clieckMenu() {
		// TODO Auto-generated method stub
		
		if(!CommandConst.isController) {
			this.mainApp.EtherCATSlave();
		}
	} 
	
	
	//test data
	private void setDummyData() {
		EtherCATMasterInfo info = new EtherCATMasterInfo();
		this.diagTableData  = FXCollections.observableArrayList();
		
		info.setLostLinksA("-");
		info.setLostLinksb("-");
		info.setName("Driver1");
		info.setPosition("-");
		info.setRxErrorA("-");
		info.setRxErrorB("-");
		info.setState("-");
		
		this.diagTableData.add(info);
		
		EtherCATMasterInfo info2 = new EtherCATMasterInfo();		
		info2.setLostLinksA("-");
		info2.setLostLinksb("-");
		info2.setName("Driver2");
		info2.setPosition("-");
		info2.setRxErrorA("-");
		info2.setRxErrorB("-");
		info2.setState("-");
		
		this.diagTableData.add(info2);
		
		
	}
}