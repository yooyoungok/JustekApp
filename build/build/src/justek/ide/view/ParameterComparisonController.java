package justek.ide.view;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import justek.ide.MainApp;
import justek.ide.model.CommandConst;
import justek.ide.model.DummyData;
import justek.ide.model.EditingCell;
import justek.ide.model.LabelCell;
import justek.ide.model.SDOInfo;
import justek.ide.model.listener.TreeEventListener;
import justek.ide.utils.StringUtil;

public class ParameterComparisonController implements TreeEventListener {

	ObservableList<String> viewModeComboList = FXCollections
			.observableArrayList("All","Difference Only","Identical Only");
	
	@FXML
	private ComboBox<String> viewModeCombo;

	@FXML
	private ComboBox<String> motorCombo1;
	@FXML
	private ComboBox<String> motorCombo2;
	
	@FXML
	private TableView<SDOInfo> ampTable;
	@FXML
	private TableView<SDOInfo> currentTable;
	@FXML
	private TableView<SDOInfo> posTable;
	@FXML
	private TableView<SDOInfo> motorTable;

	@FXML
	private TableView<SDOInfo> ampTable2;
	@FXML
	private TableView<SDOInfo> currentTable2;
	@FXML
	private TableView<SDOInfo> posTable2;
	@FXML
	private TableView<SDOInfo> motorTable2;
	
	//MoterData Column
	@FXML
	private TableColumn<SDOInfo, String> motorIndex;
	@FXML
	private TableColumn<SDOInfo, String> motorSubIndex;
	@FXML
	private TableColumn<SDOInfo, String> motorDescriptionCol;
	@FXML
	private TableColumn<SDOInfo, String> motorNameCol;
	@FXML
	private TableColumn<SDOInfo, String> motorValueCol;
	@FXML
	private TableColumn<SDOInfo, String> motorDefaultCol;
	@FXML
	private TableColumn<SDOInfo, String> motorPermissionCol;
	
	
	//Position Column
	@FXML
	private TableColumn<SDOInfo, String> posIndex;
	@FXML
	private TableColumn<SDOInfo, String> posSubIndex;
	@FXML
	private TableColumn<SDOInfo, String> posDescriptionCol;
	@FXML
	private TableColumn<SDOInfo, String> posNameCol;
	@FXML
	private TableColumn<SDOInfo, String> posValueCol;
	@FXML
	private TableColumn<SDOInfo, String> posDefaultCol;
	@FXML
	private TableColumn<SDOInfo, String> posPermissionCol;
	
	//Current Column
	@FXML
	private TableColumn<SDOInfo, String> curIndex;
	@FXML
	private TableColumn<SDOInfo, String> curSubIndex;
	@FXML
	private TableColumn<SDOInfo, String> curDescriptionCol;
	@FXML
	private TableColumn<SDOInfo, String> curNameCol;
	@FXML
	private TableColumn<SDOInfo, String> curValueCol;
	@FXML
	private TableColumn<SDOInfo, String> curDefaultCol;
	@FXML
	private TableColumn<SDOInfo, String> curPermissionCol;
	
	//Amp Column
	@FXML
	private TableColumn<SDOInfo, String> ampIndex;
	@FXML
	private TableColumn<SDOInfo, String> ampSubIndex;
	
	@FXML
	private TableColumn<SDOInfo, String> ampDescriptionCol;
	@FXML
	private TableColumn<SDOInfo, String> ampNameCol;
	@FXML
	private TableColumn<SDOInfo, String> ampValueCol;
	@FXML
	private TableColumn<SDOInfo, String> ampDefaultCol;
	@FXML
	private TableColumn<SDOInfo, String> ampPermissionCol;

	//MoterData Column
	@FXML
	private TableColumn<SDOInfo, String> motorDescriptionCol2;
	@FXML
	private TableColumn<SDOInfo, String> motorNameCol2;
	@FXML
	private TableColumn<SDOInfo, String> motorValueCol2;
	@FXML
	private TableColumn<SDOInfo, String> motorDefaultCol2;
	@FXML
	private TableColumn<SDOInfo, String> motorPermissionCol2;
	
	
	//Position Column
	@FXML
	private TableColumn<SDOInfo, String> posDescriptionCol2;
	@FXML
	private TableColumn<SDOInfo, String> posNameCol2;
	@FXML
	private TableColumn<SDOInfo, String> posValueCol2;
	@FXML
	private TableColumn<SDOInfo, String> posDefaultCol2;
	@FXML
	private TableColumn<SDOInfo, String> posPermissionCol2;
	
	//Current Column
	@FXML
	private TableColumn<SDOInfo, String> curDescriptionCol2;
	@FXML
	private TableColumn<SDOInfo, String> curNameCol2;
	@FXML
	private TableColumn<SDOInfo, String> curValueCol2;
	@FXML
	private TableColumn<SDOInfo, String> curDefaultCol2;
	@FXML
	private TableColumn<SDOInfo, String> curPermissionCol2;
	
	//Amp Column
	@FXML
	private TableColumn<SDOInfo, String> ampDescriptionCol2;
	@FXML
	private TableColumn<SDOInfo, String> ampNameCol2;
	@FXML
	private TableColumn<SDOInfo, String> ampValueCol2;
	@FXML
	private TableColumn<SDOInfo, String> ampDefaultCol2;
	@FXML
	private TableColumn<SDOInfo, String> ampPermissionCol2;

    private ObservableList<SDOInfo> ampData;
    private ObservableList<SDOInfo> curData;
    private ObservableList<SDOInfo> motorData;
    private ObservableList<SDOInfo> positionData;
    
    private ObservableList<SDOInfo> ampData2;
    private ObservableList<SDOInfo> curData2;
    private ObservableList<SDOInfo> motorData2;
    private ObservableList<SDOInfo> positionData2;
    
    private ObservableList<SDOInfo> totalTableData;
    private ObservableList<SDOInfo> totalTableData2;

    public ObservableList<String> motorList ;
    
	public int selectDriveNo = 1;
	public String numberDriveNo = new Integer(selectDriveNo).toString();
	private MainApp mainApp;
	
	private Callback<TableColumn<SDOInfo, String>, TableCell<SDOInfo, String>> cellFactory ;
	
	DummyData data;
	
	/**
	* Is called by the main application to give a reference back to itself.
	* 
	* @param mainApp
	*/
	public void setMainApp(MainApp mainApp) {
	  this.mainApp = mainApp;
	  this.mainApp.listener = this;
	} 
	
	
	   /**
	    * Initializes the controller class. This method is automatically called
	    * after the fxml file has been loaded.
	    */
	@FXML
	private void initialize() {
		this.ampData = FXCollections.observableArrayList();
		this.motorData = FXCollections.observableArrayList();
		this.positionData = FXCollections.observableArrayList();
		this.curData = FXCollections.observableArrayList();
		
		this.ampData2 = FXCollections.observableArrayList();
		this.motorData2 = FXCollections.observableArrayList();
		this.positionData2 = FXCollections.observableArrayList();
		this.curData2 = FXCollections.observableArrayList();
		
		cellFactory = (TableColumn<SDOInfo, String> param) -> new LabelCell();
		
//		this.setRightTableView();
		this.setLeftTableView();
		this.setMotorList();
		
		this.selectDriveNo = CommandConst.DRIVER_NUMBEER+1;
		numberDriveNo = new Integer(selectDriveNo).toString();
	}
	
	private void setLeftTableView() {
		this.ampIndex.setCellValueFactory(cellData -> cellData.getValue().index);
		this.ampSubIndex.setCellValueFactory(cellData -> cellData.getValue().subIndex);
	    this.ampNameCol.setCellValueFactory(cellData -> cellData.getValue().name);
	    this.ampValueCol.setCellValueFactory(cellData -> cellData.getValue().value);
	    this.ampValueCol2.setCellValueFactory(cellData -> cellData.getValue().value2);

		this.posIndex.setCellValueFactory(cellData -> cellData.getValue().index);
		this.posSubIndex.setCellValueFactory(cellData -> cellData.getValue().subIndex);
	    this.posNameCol.setCellValueFactory(cellData -> cellData.getValue().name);
	    this.posValueCol.setCellValueFactory(cellData -> cellData.getValue().value);
	    this.posValueCol2.setCellValueFactory(cellData -> cellData.getValue().value2);

		this.curIndex.setCellValueFactory(cellData -> cellData.getValue().index);
		this.curSubIndex.setCellValueFactory(cellData -> cellData.getValue().subIndex);	    
	    this.curNameCol.setCellValueFactory(cellData -> cellData.getValue().name);
	    this.curValueCol.setCellValueFactory(cellData -> cellData.getValue().value);
	    this.curValueCol2.setCellValueFactory(cellData -> cellData.getValue().value2);
	    
	    this.motorIndex.setCellValueFactory(cellData -> cellData.getValue().index);
	    this.motorSubIndex.setCellValueFactory(cellData -> cellData.getValue().subIndex);
	    this.motorNameCol.setCellValueFactory(cellData -> cellData.getValue().name);
	    this.motorValueCol.setCellValueFactory(cellData -> cellData.getValue().value);
	    this.motorValueCol2.setCellValueFactory(cellData -> cellData.getValue().value2);
	    
	    
	    this.ampValueCol.setCellFactory(this.cellFactory);
	    this.ampIndex.setCellFactory(this.cellFactory);
	    this.ampSubIndex.setCellFactory(this.cellFactory);
	    this.ampValueCol2.setCellFactory(this.cellFactory);
	    this.ampNameCol.setCellFactory(this.cellFactory);
	    
	    this.posValueCol.setCellFactory(this.cellFactory);
	    this.posIndex.setCellFactory(this.cellFactory);
	    this.posSubIndex.setCellFactory(this.cellFactory);
	    this.posValueCol2.setCellFactory(this.cellFactory);
	    this.posNameCol.setCellFactory(this.cellFactory);
	    
	    this.curValueCol.setCellFactory(this.cellFactory);
	    this.curIndex.setCellFactory(this.cellFactory);
	    this.curSubIndex.setCellFactory(this.cellFactory);
	    this.curValueCol2.setCellFactory(this.cellFactory);
	    this.curNameCol.setCellFactory(this.cellFactory);
	    
	    this.motorValueCol.setCellFactory(this.cellFactory);
	    this.motorValueCol2.setCellFactory(this.cellFactory);
	    this.motorSubIndex.setCellFactory(this.cellFactory);
	    this.motorIndex.setCellFactory(this.cellFactory);
	    this.motorNameCol.setCellFactory(this.cellFactory);
	    
	}
	
	public void getTableData(boolean file,int index,String motorName) {
		data = DummyData.getInstance();
		
		if(file && index==1) {
			this.totalTableData = data.getTableSDOInfo(motorName);

			if(this.totalTableData!=null) {
				for(SDOInfo info : this.totalTableData){
					if(info.getIndex().equals(CommandConst.AMP_DATA)){
						this.ampData.add(info);
					}
					else 	if(info.getIndex().equals(CommandConst.CURRENT_DATA)){
						this.curData.add(info);
					}
					else 	if(info.getIndex().equals(CommandConst.MOTOR_DATA)){
						this.motorData.add(info);
					}
					else 	if(info.getIndex().equals(CommandConst.POSITION_DATA)){
						this.positionData.add(info);
					}
				}
			}
		}
		else if(file && index==2) {

			this.totalTableData2 = data.getTableSDOInfo(motorName);

			if(this.totalTableData2!=null) {
				
				for(SDOInfo info : this.totalTableData2){
					if(info.getIndex().equals(CommandConst.AMP_DATA)){
						this.ampData2.add(info);
					}
					else 	if(info.getIndex().equals(CommandConst.CURRENT_DATA)){
						this.curData2.add(info);
					}
					else 	if(info.getIndex().equals(CommandConst.MOTOR_DATA)){
						this.motorData2.add(info);
					}
					else 	if(info.getIndex().equals(CommandConst.POSITION_DATA)){
						this.positionData2.add(info);
					}
				}
			}
		}
		else if(!file && index ==1) {
			this.ampData = this.setAmp();
			this.curData = this.setCurrent();
			this.motorData = this.setMotor();
			this.positionData = this.setPosition();
		}
		else if(!file && index ==2) {
			
			this.ampData2 = this.setAmp();
			this.curData2 = this.setCurrent();
			this.motorData2 = this.setMotor();
			this.positionData2 = this.setPosition();
		}
	}
	
	public void setMotorList() {
		this.motorList = FXCollections.observableArrayList();
		File directory = new File("./home/motor/");
		
		 // get just files, not directories
		 File[] files = directory.listFiles();
		 for (File file : files) {
			 int Idx =  file.getName() .lastIndexOf(".");
			 String _fileName =  file.getName().substring(0, Idx );
			 motorList.add(_fileName);	 
		 }
		 
		 motorList.add(CommandConst.ACTUAL_DATA);
		 
		 this.motorCombo1.setItems(this.motorList);
		 this.motorCombo2.setItems(this.motorList);
		 
		 this.motorCombo1.setValue(this.motorList.get(0));
		 this.motorCombo2.setValue(this.motorList.get(0));
	}
	
	public void getLeftTableData() {
		String motor1 = this.motorCombo1.getValue().toString();
		
		this.motorData.clear();
		this.ampData.clear();
		this.curData.clear();
		this.positionData.clear();
		
		if(!motor1.equals(CommandConst.ACTUAL_DATA)) {			
			this.getTableData(true, 1, motor1);
		}
		else {
			//Debug Mode
			if(CommandConst.DEBUG) {
				Alert alert = new Alert(AlertType.ERROR); 
				alert.setTitle("ERROR"); 
				String s = "Connection Error"; 
				alert.setContentText(s); 
				alert.setHeaderText("ERROR");
				alert.show();
				
				return;
			}
			
			this.getTableData(false, 1, motor1);
		}
	}
	
	public void getRightTableData() {
		String motor2 = this.motorCombo2.getValue().toString();
		
		this.motorData2.clear();
		this.ampData2.clear();
		this.curData2.clear();
		this.positionData2.clear();
		
		if(!motor2.equals(CommandConst.ACTUAL_DATA)) {
			this.getTableData(true, 2, motor2);
		}
		else {
			//Debug Mode
			if(CommandConst.DEBUG) {
				Alert alert = new Alert(AlertType.ERROR); 
				alert.setTitle("ERROR"); 
				String s = "Connection Error"; 
				alert.setContentText(s); 
				alert.setHeaderText("ERROR");
				alert.show();
				
				return;
			}
			this.getTableData(false, 2, motor2);
		}
		
		
		for(SDOInfo info:this.motorData) {
			info.setSubIndex(Integer.toString(this.motorData.indexOf(info)+1));
			
			String value = this.motorData2.get(this.motorData.indexOf(info)).getValue();
			if(info.getValue().contains("0x") &&! value.contains("0x") ) {
				value = StringUtil.getDecimalToHexValue(value);
			}
			else if(!info.getValue().contains("0x") &&value.contains("0x")) {
				value = StringUtil.getHexToDecimalValue(value);
			}
			
			info.setValue2(value);
		}
		
		for(SDOInfo info:this.ampData) {
			info.setSubIndex(Integer.toString(this.ampData.indexOf(info)+1));
			info.setValue2(this.ampData2.get(this.ampData.indexOf(info)).getValue());
		}
		
		for(SDOInfo info:this.curData) {
			info.setSubIndex(Integer.toString(this.curData.indexOf(info)+1));
			info.setValue2(this.curData2.get(this.curData.indexOf(info)).getValue());
		}
		
		for(SDOInfo info:this.positionData) {
			info.setSubIndex(Integer.toString(this.positionData.indexOf(info)+1));
			info.setValue2(this.positionData2.get(this.positionData.indexOf(info)).getValue());
		}
		
		this.motorTable.setItems(this.motorData);
		this.currentTable.setItems(this.curData);
		this.posTable.setItems(this.positionData);
		this.ampTable.setItems(this.ampData);
		
//		this.ampTable2.setItems(this.ampData2);
//		this.currentTable2.setItems(this.curData2);
//		this.posTable2.setItems(this.positionData2);
	}
	
	@FXML
	public void CompareData() {
		if(this.motorCombo1.getValue().equals(this.motorCombo2.getValue())) {
			String s = "선택된 모터가 동일합니다. \n계속 진행하시겠습니까?";
			ButtonType foo = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
			ButtonType bar = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);
			Alert alert = new Alert(AlertType.CONFIRMATION,s,foo,bar); 
			alert.setTitle("CONFIRMATION"); 

			alert.setContentText(s); 
			alert.setHeaderText("CONFIRMATION");
			Optional<ButtonType> result = alert.showAndWait(); 		
			if ((result.isPresent()) && (result.get() == foo)) { 
				this.getLeftTableData();
				this.getRightTableData();
			}
		}
		else {
			this.getLeftTableData();
			this.getRightTableData();
		}
	}
	
public ObservableList<SDOInfo> setCurrent() {
		
		ObservableList<SDOInfo> currentData = FXCollections.observableArrayList();

		System.out.println("uploadData_Current");
		int Driver_Num = this.selectDriveNo-1;
		String DriveNo = new Integer(Driver_Num).toString();
	
//		/usr/bin/ethercat upload -p 0 -t int16 0x2200 1
//		("/usr/bin/ethercat download -p 0 -t int32 0x2400 1 " + ix30
		String param = null;
		param = DriveNo+" -t uint32 0x2600 1";
		String result= null;
		boolean flag= true;
		SDOInfo info_current = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_current.setName("Proportional gain");
		info_current.setValue(result);
		currentData.add(info_current);
		
		param = DriveNo+" -t uint32 0x2600 2";
		SDOInfo info_current2 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_current2.setName("Integral gain");
		info_current2.setValue(result);
		currentData.add(info_current2);
		
		param = DriveNo+" -t uint32 0x2600 3";
		SDOInfo info_current3 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_current3.setName("Gain divide");
		info_current3.setValue(result);
		
		currentData.add(info_current3);
		
		return currentData;
	}
	
	public ObservableList<SDOInfo> setMotor() {
		
		ObservableList<SDOInfo> motorData = FXCollections.observableArrayList();

		System.out.println("uploadData_Motor");
		int Driver_Num = this.selectDriveNo-1;
		String DriveNo = new Integer(Driver_Num).toString();
	
//		/usr/bin/ethercat upload -p 0 -t int16 0x2200 1
//		("/usr/bin/ethercat download -p 0 -t int32 0x2400 1 " + ix30
		String param = null;
		param = DriveNo+" -t uint16 0x2300 1";
		String result= null;
		boolean flag= true;
		SDOInfo info_motor = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		flag = true;
		info_motor.setName("Motor Type");
		info_motor.setValue(result);
		motorData.add(info_motor);
		

		param = DriveNo+" -t uint16 0x2300 2";
		SDOInfo info_motor2 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_motor2.setName("Pole Pairs");
		info_motor2.setValue(result);
		motorData.add(info_motor2);
		
		param = DriveNo+" -t uint16 0x2300 3";
		SDOInfo info_motor3 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_motor3.setName("Hall Sensor Type");
		info_motor3.setValue(result);
		motorData.add(info_motor3);
		
		param = DriveNo+" -t uint16 0x2300 4";
		SDOInfo info_motor4 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_motor4.setName("Hall Offset");
		info_motor4.setValue(result);
		motorData.add(info_motor4);
		
		param = DriveNo+" -t uint16 0x2300 5";
		SDOInfo info_motor5 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_motor5.setName("Resistance");
		info_motor5.setValue(result);
		motorData.add(info_motor5);
		
		param = DriveNo+" -t uint16 0x2300 6";
		SDOInfo info_motor6 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_motor6.setName("Inductance");
		info_motor6.setValue(result);
		motorData.add(info_motor6);
		
		param = DriveNo+" -t uint16 0x2300 7";
		SDOInfo info_motor7 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_motor7.setName("Back EMF");
		info_motor7.setValue(result);
		motorData.add(info_motor7);
		
		param = DriveNo+" -t uint32 0x2300 8";
		SDOInfo info_motor8 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_motor8.setName("Maximum Velocity");
		info_motor8.setValue(result);
		motorData.add(info_motor8);
		
		param = DriveNo+" -t uint32 0x2300 9";
		SDOInfo info_motor9 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_motor9.setName("Torque Constant");
		info_motor9.setValue(result);
		motorData.add(info_motor9);
		
		param = DriveNo+" -t uint16 0x2300 10";
		SDOInfo info_motor10 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_motor10.setName("Peak Current");
		info_motor10.setValue(result);
		motorData.add(info_motor10);
		
		param = DriveNo+" -t uint16 0x2300 11";
		SDOInfo info_motor11 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_motor11.setName("Continuous Current");
		info_motor11.setValue(result);
		motorData.add(info_motor11);
		
		param = DriveNo+" -t uint16 0x2300 12";
		SDOInfo info_motor12 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_motor12.setName("Temperature Sensor");
		info_motor12.setValue(result);
		motorData.add(info_motor12);
		
		param = DriveNo+" -t uint16 0x2300 13";
		SDOInfo info_motor13 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_motor13.setName("Encoder Type codo");
		info_motor13.setValue(result);
		motorData.add(info_motor13);
		
		param = DriveNo+" -t uint16 0x2300 14";
		SDOInfo info_motor14 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_motor14.setName("Encoder Unit");
		info_motor14.setValue(result);
		motorData.add(info_motor14);
		
		param = DriveNo+" -t uint16 0x2300 15";
		SDOInfo info_motor15 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_motor15.setName("Encoder Direction");
		info_motor15.setValue(result);
		motorData.add(info_motor15);
		
		param = DriveNo+" -t uint32 0x2300 16";
		SDOInfo info_motor16 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_motor16.setName("Counts per Revolution");
		info_motor16.setValue(result);
		motorData.add(info_motor16);
		
		param = DriveNo+" -t uint16 0x2300 17";
		SDOInfo info_motor17 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_motor17.setName("Encoder Resolution");
		info_motor17.setValue(result);
		motorData.add(info_motor17);
		
		param = DriveNo+" -t uint32 0x2300 18";
		SDOInfo info_motor18 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_motor18.setName("Electrical distance");
		info_motor18.setValue(result);
		motorData.add(info_motor18);
		
		param = DriveNo+" -t uint16 0x2300 19";
		SDOInfo info_motor19 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_motor19.setName("Auxiliary encoder type code");
		info_motor19.setValue(result);
		motorData.add(info_motor19);
		
		param = DriveNo+" -t uint16 0x2300 20";
		SDOInfo info_motor20 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_motor20.setName("Auxiliary encoder direction");
		info_motor20.setValue(result);
		motorData.add(info_motor20);
		
		param = DriveNo+" -t uint16 0x2300 21";
		SDOInfo info_motor21 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_motor21.setName("Auxiliary encoder resolution");
		info_motor21.setValue(result);
		motorData.add(info_motor21);
		
		param = DriveNo+" -t uint8 0x2300 22";
		SDOInfo info_motor22 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_motor22.setName("Limit sensor Swad");
		info_motor22.setValue(result);
		motorData.add(info_motor22);
		
		return motorData;
	}
	
	public ObservableList<SDOInfo> setAmp() {
		
		ObservableList<SDOInfo> ampData = FXCollections.observableArrayList();

		System.out.println("uploadData_Amp");
		int Driver_Num = this.selectDriveNo-1;
		String DriveNo = new Integer(Driver_Num).toString();
	
//		/usr/bin/ethercat upload -p 0 -t int16 0x2200 1
//		("/usr/bin/ethercat download -p 0 -t int32 0x2400 1 " + ix30
		String param = null;
		param = DriveNo+" -t int16 0x2200 1";
		String result= null;
		boolean flag= true;
		SDOInfo info_amp = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_amp.setName("Amplifier Peak Current");
		info_amp.setValue(result);
		ampData.add(info_amp);
		
		param = DriveNo+" -t uint16 0x2200 2";
		SDOInfo info_amp_1 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_amp_1.setName("Amplifier Continous Current");
		info_amp_1.setValue(result);
		ampData.add(info_amp_1);
		
		param = DriveNo+" -t int16 0x2200 3";
		SDOInfo info_amp_2 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_amp_2.setName("Amplifier Peak Current Time");
		info_amp_2.setValue(result);
		ampData.add(info_amp_2);
		
		param = DriveNo+" -t uint16 0x2200 4";
		SDOInfo info_amp_3 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_amp_3.setName("Amplifier Maximum Voltage");
		info_amp_3.setValue(result);
		ampData.add(info_amp_3);
		
		param = DriveNo+" -t uint16 0x2200 5";
		SDOInfo info_amp_4 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		
		flag = true;
		info_amp_4.setName("Amplifier Minimum Voltage");
		info_amp_4.setValue(result);
		ampData.add(info_amp_4);
		
		return ampData;
	}
	
public ObservableList<SDOInfo> setPosition() {
		
		ObservableList<SDOInfo> posData = FXCollections.observableArrayList();

		System.out.println("uploadData_Position Loop Gain");
		int Driver_Num = this.selectDriveNo-1;
		String DriveNo = new Integer(Driver_Num).toString();
	
//		/usr/bin/ethercat upload -p 0 -t int16 0x2200 1
//		("/usr/bin/ethercat download -p 0 -t int32 0x2400 1 " + ix30
		String param = null;
		param = DriveNo+" -t int32 0x2400 1";
		String result= null;
		boolean flag= true;
		SDOInfo info_pos = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		flag = true;
		info_pos.setName("Position Loop Proportional Pain");
		info_pos.setValue(result);
		posData.add(info_pos);
		
		param = DriveNo+" -t uint32 0x2400 2";
		SDOInfo info_pos_1 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		flag = true;
		info_pos_1.setName("Position Loop Integral Gain");
		info_pos_1.setValue(result);
		posData.add(info_pos_1);
		
		param = DriveNo+" -t int32 0x2400 3";
		SDOInfo info_pos_2 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		flag = true;
		info_pos_2.setName("Gain Divider");
		info_pos_2.setValue(result);
		posData.add(info_pos_2);
		
		param = DriveNo+" -t uint16 0x2400 4";
		SDOInfo info_pos_3 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		flag = true;
		info_pos_3.setName("Position Loop Scale");
		info_pos_3.setValue(result);
		posData.add(info_pos_3);
		
		param = DriveNo+" -t uint32 0x2400 5";
		SDOInfo info_pos_4 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		flag = true;
		info_pos_4.setName("Velocity FeedBack Derivative Gain");
		info_pos_4.setValue(result);
		posData.add(info_pos_4);
		
		param = DriveNo+" -t uint32 0x2400 6";
		SDOInfo info_pos_5 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		flag = true;
		info_pos_5.setName("Velocity FeedForward Gain");
		info_pos_5.setValue(result);
		posData.add(info_pos_5);
		
		param = DriveNo+" -t uint32 0x2400 7";
		SDOInfo info_pos_6 = new SDOInfo();
		while(flag) {
			result = this.getSDOString(param);
			if(!result.equals("Fail"))
				flag = false;
		}
		flag = true;
		info_pos_6.setName("Acceleration Feedforward Gain");
		info_pos_6.setValue(result);
		posData.add(info_pos_6);
		
		return posData;
	}

	public String getSDOString(String param) {
		String result = "Fail";
		
		Runtime runtime = Runtime.getRuntime();
		try {
			Process process = runtime.exec("/usr/bin/ethercat upload -p "+param);
			String szLine; 
			String[] array;
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			if ((szLine = br.readLine()) != null) {
				System.out.println(szLine);
						array = szLine.split(" ");
						
						if(array.length!=2) {
							result = "Fail";
						}
						else {
						result = array[1];
						}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}


	@Override
	public void clieckMenu() {
		// TODO Auto-generated method stub

	}
}
