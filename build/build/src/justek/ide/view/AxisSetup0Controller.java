package justek.ide.view;


import javafx.fxml.FXML;

import org.json.simple.JSONObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import justek.ide.MainApp;
import justek.ide.model.AxisSetupKey;
import justek.ide.model.CommandConst;
import justek.ide.model.listener.TreeEventListener;
import justek.ide.model.manager.FileSaveManager;
import justek.ide.model.manager.TreeViewManager;


public class AxisSetup0Controller implements TreeEventListener {
	
	static final String Tag = "AxisSetup0Controller";
	
	//axis0Combo1
	ObservableList<String> axis0Combo1List = CommandConst.AXIS_LILST;

	//axis0Combo2
	ObservableList<String> s1List = FXCollections
			.observableArrayList("Master","Slave");  // Gantry, Synchronized Current

	ObservableList<String> s2List = FXCollections
			.observableArrayList("X Master","X Slave","Y Master","Y Slave");  //Planar
	ObservableList<String> s3List = FXCollections
			.observableArrayList("Violin - G","Piccolo - G","Flute - G");  //Current Amplifier
	//axis0Combo3
	ObservableList<String> s4List = FXCollections
			.observableArrayList("Rotary Motor Rotary Load",
					"Rotary Motor Rotary Load + Gear",
					"Rotary Motor Linear Load + Gear + Lead Screw",
					"Rotary Motor Linear Load + Gear + Belt/Rack and Pinion",
					"Linear Motor Linear Load",
					"Linear Motor Linear Load + Gear + Wedge");  // Single Axis
	ObservableList<String> s5List = FXCollections
			.observableArrayList("Rotary Motor Rotary Load",
					"Linear Motor Linear Load");  // Simple Stepper Application(No Feedback),Simple Stepper Application(With Feedback) 
	ObservableList<String> s6List = FXCollections
			.observableArrayList("Rotary Motor Rotary Load",
					"Rotary Motor Rotary Load + Gear",
					"Rotary Motor Linear Load + Gear + Lead Screw",
					"Rotary Motor Linear Load + Gear + Belt/Rack and Pinion",
					"Linear Motor Linear Load",
					"Linear Motor Linear Load + Gear + Wedge");  // Gantry
	//axis0Combo4
	ObservableList<String> s7List = FXCollections
			.observableArrayList("Linear Feedback on Motor",
					"Linear Feedback on Load");  
	//axis0Combo5	
	ObservableList<String> s8List = FXCollections
			.observableArrayList("Position (UM = 5)",
					"Velocity (UM = 2)",
					"Current (UM = 1)");  
	ObservableList<String> s9List = FXCollections
			.observableArrayList("Position (UM = 5)");
	//					"Velocity (UM = 2)",
	//					"Current (UM = 1)",
	//					"Stepper (UM = 6)");  
	//axis0DriverCombo
	ObservableList<String> driverList = FXCollections
			.observableArrayList("Driver01",
					"Driver02",
					"Driver03");  	
	@FXML
	private ComboBox<String> axis0DriverCombo;
	@FXML
	private ComboBox<String> axis0Combo1;
	@FXML
	private ComboBox<String> axis0Combo2;	
	@FXML
	private ComboBox<String> axis0Combo3;	
	@FXML
	private ComboBox<String> axis0Combo4;	
	@FXML
	private ComboBox<String> axis0Combo5;	
	@FXML
	private Label driverLabel;


	@FXML
	private TextField DemoinatorField;

	@FXML
	private TextField TransmissionField;

	@FXML
	private TextField NumeratorField;

	@FXML
	private RadioButton singleFeedBackRaidoButton;

	@FXML
	private TreeView<String> treeView;

	//TreeView List
	private ObservableList<TreeItem<String>> nodeList;

	Image icon = new Image (
			getClass().getResourceAsStream("/img/folder-icon.png"));

	private TreeViewManager treeViewManager;

	// Reference to the main application
	private MainApp mainApp;

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
		axis0Combo1.setValue("Single Axis");
		axis0Combo1.setItems(CommandConst.AXIS_LILST);
		axis0Combo4.setValue("Linear Feedback on Motor");
		axis0Combo4.setItems(s7List);
		axis0Combo2.setValue("");
		axis0Combo2.setItems(null);
		axis0Combo3.setValue("Rotary Motor Rotary Load");
		axis0Combo3.setItems(s5List);
		axis0Combo5.setValue("Position (UM = 5)");
		axis0Combo5.setItems(s8List);

		this.singleFeedBackRaidoButton.setSelected(false);
		
		this.getData();
		
//		this.setTreeView();
	}

	@FXML
	private void combo2Choice() {
		if(axis0Combo2.getValue()==null) return;

		if(axis0Combo2.getValue().equals(CommandConst.AXIS_IDENTITY_LILST.get(0))) {
			this.setGrantryTreeView();
		}
		else if(axis0Combo2.getValue().equals(CommandConst.AXIS_IDENTITY_LILST.get(1))) {
			this.setTreeView();
		}
		
		this.saveData();
	}
	@FXML
	private void combo1Choice() {

		if(axis0Combo1.getValue().equals(CommandConst.AXIS_LILST.get(1))) {
			axis0Combo2.setItems(CommandConst.AXIS_IDENTITY_LILST);
			axis0Combo2.setValue(CommandConst.AXIS_IDENTITY_LILST.get(0));
		}
		else if(axis0Combo1.getValue().equals(CommandConst.AXIS_LILST.get(0))) {
			axis0Combo2.setItems(null);
			axis0Combo2.setValue("");
			this.setTreeView();
		}
		
		this.saveData();
		// 		axis0Combo2.setValue("");
		//		axis0Combo2.setItems(null);
		// 		axis0Combo3.setValue("Rotary Motor Rotary Load");
		//		axis0Combo3.setItems(s5List);
		// 		axis0Combo5.setValue("Position (UM = 5)");
		//		axis0Combo5.setItems(s8List);

		/*
    	if(axis0Combo1.getValue().equals("Single Axis")) {
      		axis0Combo2.setValue("");
    		axis0Combo2.setItems(null);
     		axis0Combo3.setValue("Rotary Motor Rotary Load");
    		axis0Combo3.setItems(s5List);
     		axis0Combo5.setValue("Position (UM = 5)");
    		axis0Combo5.setItems(s8List);

    	} else if(axis0Combo1.getValue().equals("Stepper Axis(No Feedback)")) {
      		axis0Combo2.setValue("");
    		axis0Combo2.setItems(null);
     		axis0Combo3.setValue("Rotary Motor Rotary Load");
    		axis0Combo3.setItems(s5List);
     		axis0Combo5.setValue("Position (UM = 5)");
    		axis0Combo5.setItems(s9List);
    	} else if(axis0Combo1.getValue().equals("Stepper Axis(with Feedback)")) {
      		axis0Combo2.setValue("");
    		axis0Combo2.setItems(null);
     		axis0Combo3.setValue("Rotary Motor Rotary Load");
    		axis0Combo3.setItems(s5List);
     		axis0Combo5.setValue("Position (UM = 5)");
    		axis0Combo5.setItems(s9List);
    	} else if(axis0Combo1.getValue().equals("Gantry")) {
    		axis0Combo2.setValue("Master");
    		axis0Combo2.setItems(s1List);
     		axis0Combo3.setValue("Rotary Motor Rotary Load");
    		axis0Combo3.setItems(s6List);
     		axis0Combo5.setValue("Position (UM = 5)");
    		axis0Combo5.setItems(s9List);
    	} else if(axis0Combo1.getValue().equals("Planar")) {
        	axis0Combo2.setValue("X Master");
        	axis0Combo2.setItems(s2List);
     		axis0Combo3.setValue("Rotary Motor Rotary Load");
    		axis0Combo3.setItems(s4List);
     		axis0Combo5.setValue("Position (UM = 5)");
    		axis0Combo5.setItems(s9List);
    	} else if(axis0Combo1.getValue().equals("Synchronized Current")) {
    		axis0Combo2.setValue("Master");
    		axis0Combo2.setItems(s1List);
     		axis0Combo3.setValue("Rotary Motor Rotary Load");
    		axis0Combo3.setItems(s4List);
     		axis0Combo5.setValue("Position (UM = 5)");
    		axis0Combo5.setItems(s9List);
    	} else if(axis0Combo1.getValue().equals("Current Amplifier")) {
    		axis0Combo2.setValue("Violin - G");
    		axis0Combo2.setItems(s3List);
     		axis0Combo3.setValue("Rotary Motor Rotary Load");
    		axis0Combo3.setItems(s4List);
     		axis0Combo5.setValue("Position (UM = 5)");
    		axis0Combo5.setItems(s9List);
    	} else {
       		axis0Combo2.setValue("");
    		axis0Combo2.setItems(null);
     		axis0Combo3.setValue("");
    		axis0Combo3.setItems(null);
     		axis0Combo5.setValue("");
    		axis0Combo5.setItems(null);
    	}
		 */
	}

	//TreeView Node Setting!
	public void setTreeView() {
		treeViewManager = TreeViewManager.getInstance();
		this.nodeList = treeViewManager.getNodeInfo();
		treeViewManager.setTreeView(treeView);

		TreeItem<String> root = new TreeItem<>("Axis Configrations", new ImageView(icon));
		root.setExpanded(true);
		root.getChildren().addAll(this.nodeList);
		root.getChildren().get(0).setExpanded(true);
		root.getChildren().get(1).setExpanded(true);
		root.getChildren().get(2).setExpanded(true);
		root.getChildren().get(3).setExpanded(true);
		root.getChildren().get(4).setExpanded(true);
		treeView.setRoot(root);
	}

	public void setGrantryTreeView() {
		treeViewManager = TreeViewManager.getInstance();
		this.nodeList = treeViewManager.getGrantryNodeInfo();
		treeViewManager.setTreeView(treeView);

		TreeItem<String> root = new TreeItem<>("Axis Configrations", new ImageView(icon));
		root.setExpanded(true);
		root.getChildren().addAll(this.nodeList);
		for(TreeItem<String> item : root.getChildren()) {
			item.setExpanded(true);
		}
		treeView.setRoot(root);
	}

	
	//
	/**
	 * 현재 저장된 Config 정보를 불러온다...
	 * @author : YOO YOUNGOK 
	 * @method  getData
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 25.
	 */
	public void getData() {
		JSONObject mainObject = FileSaveManager.getInstance().readAxisSetupFile();
		if(mainObject!=null) {
			JSONObject object = (JSONObject) mainObject.get(Tag);
			if(object == null) return;
			System.out.println(Tag+"== getData \n ==> "+object.toString());
			this.axis0Combo1.setValue((String)object.get(AxisSetupKey.AxisControConfiguration));
			this.axis0Combo2.setValue((String)object.get(AxisSetupKey.AxisIdentity));
			this.axis0Combo3.setValue((String)object.get(AxisSetupKey.ElectroMechanicalConfiguration));
			this.axis0Combo4.setValue((String)object.get(AxisSetupKey.LoopFeedbackConfiguration));
			this.axis0Combo5.setValue((String)object.get(AxisSetupKey.ModeOperation));
			this.NumeratorField.setText((String)object.get(AxisSetupKey.Numerator));
			this.DemoinatorField.setText((String)object.get(AxisSetupKey.Denominator));
			this.TransmissionField.setText((String)object.get(AxisSetupKey.Transmission));
			this.singleFeedBackRaidoButton.setSelected((Boolean)object.get(AxisSetupKey.SingleFeedback));
		}
	}
	
	@SuppressWarnings("unchecked")
	public void saveData() {
		JSONObject dataInfo = new JSONObject();
		
		dataInfo.put(AxisSetupKey.AxisControConfiguration,this.axis0Combo1.getSelectionModel().getSelectedItem());
		dataInfo.put(AxisSetupKey.AxisIdentity,this.axis0Combo2.getSelectionModel().getSelectedItem());
		dataInfo.put(AxisSetupKey.ElectroMechanicalConfiguration,this.axis0Combo3.getSelectionModel().getSelectedItem());
		dataInfo.put(AxisSetupKey.LoopFeedbackConfiguration,this.axis0Combo4.getSelectionModel().getSelectedItem());
		dataInfo.put(AxisSetupKey.ModeOperation,this.axis0Combo5.getSelectionModel().getSelectedItem());
		
		dataInfo.put(AxisSetupKey.Numerator, this.NumeratorField.getText());
		dataInfo.put(AxisSetupKey.Denominator, this.DemoinatorField.getText());
		dataInfo.put(AxisSetupKey.Transmission, this.TransmissionField.getText());
		dataInfo.put(AxisSetupKey.SingleFeedback, this.singleFeedBackRaidoButton.isSelected());
		FileSaveManager.getInstance().setAxisChangeData(Tag, dataInfo);
	}

	@FXML
	public void saveData(KeyEvent event) {
		if(event.getCode()!=KeyCode.ENTER) return;
		System.out.println(Tag+"== saveData");
		this.saveData();
	}
	
	@FXML
	public void onSelectComboBox() {
		this.saveData();
	}
	
    @FXML
    void onClickRevert(ActionEvent event) {
    	System.out.println(Tag+" == onClickRevert ");
    }

    @FXML
    void onClickApply(ActionEvent event) {
    	System.out.println(Tag+" == onClickApply ");
    }

    @FXML
    void onClickErrors(ActionEvent event) {
    	System.out.println(Tag+" == onClickErrors ");
    }

	public void mouseClick(MouseEvent mouseEvent) {
		treeViewManager.setMainApp(this.mainApp);
		treeViewManager.mouseClick(mouseEvent);
	}

	@Override
	public void clieckMenu() {

	}
}
