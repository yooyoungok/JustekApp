package justek.ide.view;

import justek.ide.MainApp;
import justek.ide.model.CommandConst;
import justek.ide.model.DummyData;
import justek.ide.model.SDOInfo;
import justek.ide.model.listener.TreeEventListener;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import justek.ide.model.EditingCell;
import justek.ide.model.LabelCell;

public class SDODataViewController implements TreeEventListener{

	private MainApp mainApp;
	
	@FXML
	private ComboBox<String> axis0DriverCombo;
	@FXML
	private ComboBox<String> motorCombo;
	
	public int selectDriveNo = 1;
	public String numberDriveNo = new Integer(selectDriveNo).toString();
	
	public String mororName;

	@FXML
	private TableView<SDOInfo> ampTable;
	@FXML
	private TableView<SDOInfo> currentTable;
	@FXML
	private TableView<SDOInfo> posTable;
	@FXML
	private TableView<SDOInfo> motorTable;
	@FXML
	private Accordion accordin;
	
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
	
	@FXML
	private TitledPane ampPane;
	@FXML
	private TitledPane currentPane;
	@FXML
	private TitledPane motorPane;
	@FXML
	private TitledPane positionPane;
	
	@FXML
	private Label driverLabel;
	
	private Callback<TableColumn<SDOInfo, String>, TableCell<SDOInfo, String>> cellFactory ;
	private Callback<TableColumn<SDOInfo, String>, TableCell<SDOInfo, String>> labelCellFactory ;
	

    private ObservableList<SDOInfo> ampData;
    private ObservableList<SDOInfo> curData;
    private ObservableList<SDOInfo> motorData;
    private ObservableList<SDOInfo> positionData;
    
    private ObservableList<SDOInfo> totalTableData;
    
    public ObservableList<String> motorList ;

	@FXML
	private void initialize() {
		cellFactory = (TableColumn<SDOInfo, String> param) -> new EditingCell();
		labelCellFactory  = (TableColumn<SDOInfo, String> param) -> new LabelCell();

		this.setAmpTableView();
		this.setCurrentTableView();
		this.setMoterTableView();
		this.setPosTableView();
		
		this.setMotorList();
		
		this.selectDriveNo = CommandConst.DRIVER_NUMBEER+1;
		numberDriveNo = new Integer(selectDriveNo).toString();
		this.driverLabel.setText(CommandConst.DRIVER);
		this.uploadData();
	}

	public void setMotorList() {
		this.motorList = FXCollections.observableArrayList();
		File directory = new File("./home/motor/");

		
		 // get just files, not directories
		 File[] files = directory.listFiles();
		 String[] parsing= null;
		 for (File file : files) {
			 int Idx =  file.getName() .lastIndexOf(".");
			 String _fileName =  file.getName().substring(0, Idx );
			 motorList.add(_fileName);	 
		 }
		 
		 motorList.add("Actual Motor");
		 
		 this.motorCombo.setItems(this.motorList);
		 this.motorCombo.setValue(this.motorList.get(0));
		 this.mororName = this.motorList.get(0);
	}
	
	private void setAmpTableView() {
	    this.ampIndex.setCellValueFactory(cellData -> cellData.getValue().index);
	    this.ampSubIndex.setCellValueFactory(cellData -> cellData.getValue().subIndex);
	    this.ampNameCol.setCellValueFactory(cellData -> cellData.getValue().name);
	    this.ampValueCol.setCellValueFactory(cellData -> cellData.getValue().value);
	    
	    this.ampValueCol.setCellFactory(cellFactory);
	    this.ampIndex.setCellFactory(labelCellFactory);
	    this.ampSubIndex.setCellFactory(labelCellFactory);
	    this.ampNameCol.setCellFactory(labelCellFactory);
	    
	    ampValueCol.setOnEditCommit( 
	    		(TableColumn.CellEditEvent<SDOInfo, String> t) -> { 
	    			((SDOInfo) t.getTableView().getItems() 
	    					.get(t.getTablePosition().getRow())) 
	    			.setValue(t.getNewValue()); 
	    		}); 
	}
	
	private void setPosTableView() {
	    this.posIndex.setCellValueFactory(cellData -> cellData.getValue().index);
	    this.posSubIndex.setCellValueFactory(cellData -> cellData.getValue().subIndex);
	    this.posNameCol.setCellValueFactory(cellData -> cellData.getValue().name);
	    this.posValueCol.setCellValueFactory(cellData -> cellData.getValue().value);
	    
	    this.posValueCol.setCellFactory(cellFactory);
	    this.posIndex.setCellFactory(labelCellFactory);
	    this.posSubIndex.setCellFactory(labelCellFactory);
	    this.posNameCol.setCellFactory(labelCellFactory);
	    
	    posValueCol.setOnEditCommit( 
	    		(TableColumn.CellEditEvent<SDOInfo, String> t) -> { 
	    			((SDOInfo) t.getTableView().getItems() 
	    					.get(t.getTablePosition().getRow())) 
	    			.setValue(t.getNewValue()); 
	    		}); 
	}
	
	private void setCurrentTableView() {
	    this.curIndex.setCellValueFactory(cellData -> cellData.getValue().index);
	    this.curSubIndex.setCellValueFactory(cellData -> cellData.getValue().subIndex);
	    this.curNameCol.setCellValueFactory(cellData -> cellData.getValue().name);
	    this.curValueCol.setCellValueFactory(cellData -> cellData.getValue().value);
	    
	    this.curValueCol.setCellFactory(cellFactory);
	    this.curIndex.setCellFactory(labelCellFactory);
	    this.curSubIndex.setCellFactory(labelCellFactory);
	    this.curNameCol.setCellFactory(labelCellFactory);
	    
	    curValueCol.setOnEditCommit( 
	    		(TableColumn.CellEditEvent<SDOInfo, String> t) -> { 
	    			((SDOInfo) t.getTableView().getItems() 
	    					.get(t.getTablePosition().getRow())) 
	    			.setValue(t.getNewValue()); 
	    		}); 
	     
	}
	
	private void setMoterTableView() {
	    this.motorIndex.setCellValueFactory(cellData -> cellData.getValue().index);
	    this.motorSubIndex.setCellValueFactory(cellData -> cellData.getValue().subIndex);
	    this.motorNameCol.setCellValueFactory(cellData -> cellData.getValue().name);
	    this.motorValueCol.setCellValueFactory(cellData -> cellData.getValue().value);
	    
	    this.motorValueCol.setCellFactory(cellFactory);
	    this.motorIndex.setCellFactory(labelCellFactory);
	    this.motorSubIndex.setCellFactory(labelCellFactory);
	    this.motorNameCol.setCellFactory(labelCellFactory);
	    
	    motorValueCol.setOnEditCommit( 
	    		(TableColumn.CellEditEvent<SDOInfo, String> t) -> { 
	    			((SDOInfo) t.getTableView().getItems() 
	    					.get(t.getTablePosition().getRow())) 
	    			.setValue(t.getNewValue()); 
	    		}); 
	    
	}
	
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
	private void driverChoice() {

//		if (axis0DriverCombo.getValue().equals("Driver01")) {
//			selectDriveNo = 1;
//			numberDriveNo = new Integer(selectDriveNo).toString();
//		} else if (axis0DriverCombo.getValue().equals("Driver02")) {
//			selectDriveNo = 2;
//			numberDriveNo = new Integer(selectDriveNo).toString();
//		} else if (axis0DriverCombo.getValue().equals("Driver03")) {
//			selectDriveNo = 3;
//			numberDriveNo = new Integer(selectDriveNo).toString();
//		}
		
//		this.loadData();
	}
	
	@FXML
	private void motorChoice() {
	       // Create a file object
		this.mororName = this.motorCombo.getValue();
		this.uploadData();
	}
	
	@FXML 
	private void modifyApply() {

		String s = "해당정보 변경은 모터의 심각한 손상을 초대할 수 있습니다. "
				+ "\n다시한번 확인하시기 바랍니다."
				+ "\n변경된 정보를 저장하시겠습니까?"; 
		ButtonType foo = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
		ButtonType bar = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);
		Alert alert = new Alert(AlertType.WARNING,s,foo,bar); 
		alert.setTitle("WARNING"); 
		alert.setContentText(s); 
		alert.setHeaderText("WARNING");

		Optional<ButtonType> result = alert.showAndWait(); 		
		if ((result.isPresent()) && (result.get() ==foo)) { 
			
			this.totalTableData.clear();
			this.totalTableData.addAll(this.ampTable.getItems());
			this.totalTableData.addAll(this.currentTable.getItems());
			this.totalTableData.addAll(this.posTable.getItems());
			this.totalTableData.addAll(this.motorTable.getItems());
			
			this.saveModifyData("0", this.totalTableData);
		}
		else {
			this.reloadData();
		}
	}
	
	@FXML 
	private void modifyDownload() {
		String s = "해당정보 변경은 모터의 심각한 손상을 초대할 수 있습니다. "
				+ "\n다시한번 확인하시기 바랍니다."
				+ "\n변경된 정보를 저장하시겠습니까?"; 
		ButtonType foo = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
		ButtonType bar = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);
		Alert alert = new Alert(AlertType.WARNING,s,foo,bar); 
		alert.setTitle("WARNING"); 
		alert.setContentText(s); 
		alert.setHeaderText("WARNING");
		Optional<ButtonType> result = alert.showAndWait(); 		
		if ((result.isPresent()) && (result.get() == foo)) { 
			
			ObservableList<SDOInfo> items = this.ampTable.getItems();
			this.saveDownloadAmp(items);
			
			ObservableList<SDOInfo> items_current = this.currentTable.getItems();
			this.saveDownloadCurrent(items_current);
			
			ObservableList<SDOInfo> items_motor = this.motorTable.getItems();
			this.saveDownloadMotor(items_motor);
			
			ObservableList<SDOInfo> items_position = this.posTable.getItems();
			this.saveDownloadPosition(items_position);
		}
		else {
			this.reloadData();
		}
	}
	
	@FXML
	public void reloadData() {
		System.out.println("reloadData");		
		this.uploadData();
	}
	
	@FXML
	public void uploadData() {
		System.out.println("uploadData");		
		
		if(this.mororName.equals(CommandConst.ACTUAL_DATA)) {
			
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
			
			this.ampData = setAmp();
			this.motorData = setMotor();
			this.curData = setCurrent();
			this.positionData = setPosition();
			
		}
		else {
			DummyData data = DummyData.getInstance();

			if(this.totalTableData!=null) {
				this.totalTableData.clear();
			}
			
			this.totalTableData = data.getTableSDOInfo(this.mororName);
			this.ampData = FXCollections.observableArrayList();
			this.motorData = FXCollections.observableArrayList();
			this.positionData = FXCollections.observableArrayList();
			this.curData = FXCollections.observableArrayList();

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
		
		for(SDOInfo info:this.motorData) {
			info.setSubIndex(Integer.toString(this.motorData.indexOf(info)+1));
		}
		
		for(SDOInfo info:this.ampData) {
			info.setSubIndex(Integer.toString(this.ampData.indexOf(info)+1));
		}
		
		for(SDOInfo info:this.curData) {
			info.setSubIndex(Integer.toString(this.curData.indexOf(info)+1));
		}
		
		for(SDOInfo info:this.positionData) {
			info.setSubIndex(Integer.toString(this.positionData.indexOf(info)+1));
		}
		
		this.ampTable.setItems(ampData);
		this.motorTable.setItems(motorData);
		this.currentTable.setItems(curData);
		this.posTable.setItems(positionData);
		
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
	
	public void setSDOString(String param) {
		
		System.out.println("setSDOString"+param);
		Runtime runtime = Runtime.getRuntime();
		try {
			
			Process process = runtime.exec("/usr/bin/ethercat download -p "+param);
			System.out.println("/usr/bin/ethercat download -p "+param);
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void downloadData() {
		System.out.println("downloadData");
		
		if(this.motorCombo.getValue().equals(CommandConst.ACTUAL_DATA)) {
			if(CommandConst.DEBUG)
				return;
			
			this.modifyDownload();
		}
		else {
			this.modifyApply();
		}
	}
	
	
	public void saveDownloadAmp(ObservableList<SDOInfo> items) {

		System.out.println("saveDownloadAmp");
		int Driver_Num = this.selectDriveNo-1;
		String DriveNo = new Integer(Driver_Num).toString();

			SDOInfo info = items.get(0);
			String value = info.getValue();
			String param = DriveNo+" -t int16 0x2200 1 "+value;
			this.setSDOString(param);
			
			info = items.get(1);
			value = info.getValue();
			param = DriveNo+" -t uint16 0x2200 2 "+value;
			this.setSDOString(param);
			
			info = items.get(2);
			value = info.getValue();
			param = DriveNo+" -t int16 0x2200 3 "+value;
			this.setSDOString(param);
			
			info = items.get(3);
			value = info.getValue();
			param = DriveNo+" -t uint16 0x2200 4 "+value;
			this.setSDOString(param);
			
			info = items.get(4);
			value = info.getValue();
			param = DriveNo+" -t uint16 0x2200 5 "+value;
			this.setSDOString(param);
	}
	
	public void saveDownloadCurrent(ObservableList<SDOInfo> items) {
		
		System.out.println("saveDownloadCurrent");
		int Driver_Num = this.selectDriveNo-1;
		String DriveNo = new Integer(Driver_Num).toString();

			SDOInfo info = items.get(0);
			String value = info.getValue();
			String param = DriveNo+" -t uint32 0x2600 1 "+value;
			this.setSDOString(param);
			
			info = items.get(1);
			value = info.getValue();
			param = DriveNo+" -t uint32 0x2600 2 "+value;
			this.setSDOString(param);
			
			info = items.get(2);
			value = info.getValue();
			param = DriveNo+" -t uint32 0x2600 3 "+value;
			this.setSDOString(param);
	}
	
	public void saveDownloadPosition(ObservableList<SDOInfo> items) {
		
		System.out.println("saveDownloadPosition");
		int Driver_Num = this.selectDriveNo-1;
		String DriveNo = new Integer(Driver_Num).toString();

			SDOInfo info = items.get(0);
			String value = info.getValue();
			String param = DriveNo+" -t uint32 0x2400 1 "+value;
			this.setSDOString(param);
			
			info = items.get(1);
			value = info.getValue();
			param = DriveNo+" -t uint32 0x2400 2 "+value;
			this.setSDOString(param);
			
			info = items.get(2);
			value = info.getValue();
			param = DriveNo+" -t uint32 0x2400 3 "+value;
			this.setSDOString(param);
			
			info = items.get(3);
			value = info.getValue();
			param = DriveNo+" -t uint16 0x2400 4 "+value;
			this.setSDOString(param);
			
			info = items.get(4);
			value = info.getValue();
			param = DriveNo+" -t uint32 0x2400 5 "+value;
			this.setSDOString(param);
			
			info = items.get(5);
			value = info.getValue();
			param = DriveNo+" -t uint32 0x2400 6 "+value;
			this.setSDOString(param);
			
			info = items.get(6);
			value = info.getValue();
			param = DriveNo+" -t uint32 0x2400 7 "+value;
			this.setSDOString(param);
	}
	
	public void saveDownloadMotor(ObservableList<SDOInfo> items) {
		
		System.out.println("saveDownloadMotor");
		int Driver_Num = this.selectDriveNo-1;
		String DriveNo = new Integer(Driver_Num).toString();
		
		SDOInfo info = items.get(0);
		String value = info.getValue();
		String param = DriveNo+" -t uint16 0x2300 1 "+value;
		this.setSDOString(param);
		
		info = items.get(1);
		value = info.getValue();
		param = DriveNo+" -t uint16 0x2300 2 "+value;
		this.setSDOString(param);
		
		info = items.get(2);
		value = info.getValue();
		param = DriveNo+" -t uint16 0x2300 3 "+value;
		this.setSDOString(param);
		
		info = items.get(3);
		value = info.getValue();
		param = DriveNo+" -t uint16 0x2300 4 "+value;
		this.setSDOString(param);
		
		info = items.get(4);
		value = info.getValue();
		param = DriveNo+" -t uint16 0x2300 5 "+value;
		this.setSDOString(param);
		
		info = items.get(5);
		value = info.getValue();
		param = DriveNo+" -t uint16 0x2300 6 "+value;
		this.setSDOString(param);
		
		info = items.get(6);
		value = info.getValue();
		param = DriveNo+" -t uint16 0x2300 7 "+value;
		this.setSDOString(param);
		
		info = items.get(7);
		value = info.getValue();
		param = DriveNo+" -t uint32 0x2300 8 "+value;
		this.setSDOString(param);
		
		info = items.get(8);
		value = info.getValue();
		param = DriveNo+" -t uint32 0x2300 9 "+value;
		this.setSDOString(param);
		
		info = items.get(9);
		value = info.getValue();
		param = DriveNo+" -t uint16 0x2300 10 "+value;
		this.setSDOString(param);
		
		info = items.get(10);
		value = info.getValue();
		param = DriveNo+" -t uint16 0x2300 11 "+value;
		this.setSDOString(param);
		
		info = items.get(11);
		value = info.getValue();
		param = DriveNo+" -t uint16 0x2300 12 "+value;
		this.setSDOString(param);
		
		info = items.get(12);
		value = info.getValue();
		param = DriveNo+" -t uint16 0x2300 13 "+value;
		this.setSDOString(param);
		
		info = items.get(13);
		value = info.getValue();
		param = DriveNo+" -t uint16 0x2300 14 "+value;
		this.setSDOString(param);
		
		info = items.get(14);
		value = info.getValue();
		param = DriveNo+" -t uint16 0x2300 15 "+value;
		this.setSDOString(param);
		
		info = items.get(15);
		value = info.getValue();
		param = DriveNo+" -t uint32 0x2300 16 "+value;
		this.setSDOString(param);
		
		info = items.get(16);
		value = info.getValue();
		param = DriveNo+" -t uint16 0x2300 17 "+value;
		this.setSDOString(param);
		
		info = items.get(17);
		value = info.getValue();
		param = DriveNo+" -t uint32 0x2300 18 "+value;
		this.setSDOString(param);
		
		info = items.get(18);
		value = info.getValue();
		param = DriveNo+" -t uint16 0x2300 19 "+value;
		this.setSDOString(param);
		
		info = items.get(19);
		value = info.getValue();
		param = DriveNo+" -t uint16 0x2300 20 "+value;
		this.setSDOString(param);
		
		info = items.get(20);
		value = info.getValue();
		param = DriveNo+" -t uint16 0x2300 21 "+value;
		this.setSDOString(param);
		
		info = items.get(21);
		value = info.getValue();
		param = DriveNo+" -t uint8 0x2300 22 "+value;
		this.setSDOString(param);
	
	}
	
	public void saveModifyData(String tapId, ObservableList<SDOInfo> items) {
		try {

			File file = new File("./home/motor/"+this.mororName+".txt");
			
			if(CommandConst.DEBUG) {
				 file = new File(".\\home\\motor\\"+this.mororName+".txt");
			}
			
			BufferedWriter out;
			if(file.exists()) {
				out = new BufferedWriter(new FileWriter(file));

				for(int i=0;i<items.size();i++) {
					SDOInfo info = items.get(i);
					String value = info.getIndex();
					value +=","+ info.getName();
					value +=","+ info.getValue();
					value +=","+ info.getPermssion();
					value +=","+ info.getDefaultValue();
					value +=","+ info.getDescription();	
					out.write(value);
					out.newLine();
				}
				out.close();
			}
			
//			
//			if(tapId.equals(CommandConst.AMP_DATA)) {
//				File mfile = new File("amp_"+this.numberDriveNo+".txt");
//				BufferedWriter out;
//				if(mfile.exists()) {
//					out = new BufferedWriter(new FileWriter(mfile));
//
//					for(int i=0;i<items.size();i++) {
//						SDOInfo info = items.get(i);
//						String value = info.getName();
//						value +=","+ info.getValue();
//						value +=","+ info.getPermssion();
//						value +=","+ info.getDefaultValue();
//						value +=","+ info.getDescription();	
//						out.write(value);
//						out.newLine();
//					}
//					out.close();
//				}
//			}
//			else if(tapId.equals(CommandConst.CURRENT_DATA)) {
//				File mfile = new File("current_"+this.numberDriveNo+".txt");
//				BufferedWriter out;
//				if(mfile.exists()) {
//					out = new BufferedWriter(new FileWriter(mfile));
//
//					for(int i=0;i<items.size();i++) {
//						SDOInfo info = items.get(i);
//						String value = info.getName();
//						value +=","+ info.getValue();
//						value +=","+ info.getPermssion();
//						value +=","+ info.getDefaultValue();
//						value +=","+ info.getDescription();	
//						out.write(value);
//						out.newLine();
//					}
//					out.close();
//				}
//			}
//			else if(tapId.equals(CommandConst.MOTOR_DATA)) {
//				File mfile = new File("motor_"+this.numberDriveNo+".txt");
//				BufferedWriter out;
//				if(mfile.exists()) {
//					out = new BufferedWriter(new FileWriter(mfile));
//
//					for(int i=0;i<items.size();i++) {
//						SDOInfo info = items.get(i);
//						String value = info.getName();
//						value +=","+ info.getValue();
//						value +=","+ info.getPermssion();
//						value +=","+ info.getDefaultValue();
//						value +=","+ info.getDescription();	
//						out.write(value);
//						out.newLine();
//					}
//					out.close();
//				}
//			}
		} catch (IOException e) {
			e.printStackTrace(); 
		}
	}
	
	public void loadData() {
		this.addDummyAmpTable();
		this.addDummyMotorTable();
		this.addDummyCurrentTable();
		this.addDummyPosTable();
	}
	
	public void addDummyAmpTable() {
		this.ampData = new DummyData().dummySDOInfo(this.numberDriveNo);
		if(this.ampData!=null) {
			this.ampTable.setItems(this.ampData);
		}
	}
	
	public void addDummyMotorTable() {
		this.motorData = new DummyData().dummyMotorSDOInfo(this.numberDriveNo);
		if(this.motorData!=null) {
			this.motorTable.setItems(this.motorData);
		}
	}
	
	public void addDummyCurrentTable() {
		this.curData = new DummyData().dummyCurrentSDOInfo(this.numberDriveNo);
		if(this.curData!=null) {
			this.currentTable.setItems(this.curData);
		}
	}
	
	public void addDummyPosTable() {
		this.curData = new DummyData().dummyCurrentSDOInfo(this.numberDriveNo);
		if(this.curData!=null) {
			this.posTable.setItems(this.positionData);
		}
	}

	@Override
	public void clieckMenu() {
		// TODO Auto-generated method stub
		this.selectDriveNo = CommandConst.DRIVER_NUMBEER+1;
		numberDriveNo = new Integer(selectDriveNo).toString();
		this.driverLabel.setText(CommandConst.DRIVER);
	}
}
