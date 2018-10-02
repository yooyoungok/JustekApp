package justek.ide.view;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import org.json.simple.JSONObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import justek.ide.MainApp;
import justek.ide.model.AxisSetupKey;
import justek.ide.model.CommandConst;
import justek.ide.model.listener.TreeEventListener;
import justek.ide.model.manager.FileSaveManager;
import justek.ide.model.manager.TreeViewManager;


public class AxisSetup4Controller implements TreeEventListener {
	
	static final String Tag = "AxisSetup4Controller";

	ObservableList<String> axis0Combo1List = FXCollections
			.observableArrayList("Single Axis","Stepper Axis(No Feedback)","Stepper Axis(with Feedback)","Gantry","Planar","Synchronized Current","Current Amplifier");

	ObservableList<String> s1List = FXCollections
			.observableArrayList("Master","Slave");

	ObservableList<String> s2List = FXCollections
			.observableArrayList("X Master","X Slave","Y Master","Y Slave");

	@FXML
	private TextField fxConDriverField;

	@FXML
	private Label fxdriverLabel;

	@FXML
	private TextField fxMaxVoltageField;

	@FXML
	private TextField fxPWMField;

	@FXML
	private TextField fxPeakMotorField;

	@FXML
	private TextField fxMaxCurrentField;

	@FXML
	private TextField fxConMotorField;

	@FXML
	private TextField fxPeakDurationField;

	@FXML
	private TextField fxPeakDriverField;

	@FXML
	private TextField fxInterfalLimitField;

	@FXML
	private TreeView<String> treeview;	

	//TreeView List
	private ObservableList<TreeItem<String>> nodeList;

	private TreeViewManager treeViewManager;
	// Reference to the main application
	private MainApp mainApp;

	Image icon = new Image (
			getClass().getResourceAsStream("/img/folder-icon.png"));

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

//		setTreeView();
		
		getData();
	}

	//TreeView Node Setting!
	public void setTreeView() {
		treeViewManager = TreeViewManager.getInstance();
		treeViewManager.setMainApp(this.mainApp);
		this.nodeList = treeViewManager.getNodeInfo();
		treeViewManager.setTreeView(treeview);

		TreeItem<String> root = new TreeItem<>("Axis Configrations", new ImageView(icon));
		root.setExpanded(true);
		root.getChildren().addAll(nodeList);
		root.getChildren().get(0).setExpanded(true);
		root.getChildren().get(1).setExpanded(true);
		root.getChildren().get(2).setExpanded(true);
		root.getChildren().get(3).setExpanded(true);
		root.getChildren().get(4).setExpanded(true);
		treeview.setRoot(root);
	}
	
	public void saveData(KeyEvent event) {
		if(event.getCode()!=KeyCode.ENTER) return;
		System.out.println(Tag+"== saveData");
		this.saveData();
	}

	@SuppressWarnings("unchecked")
	public void saveData() {
		JSONObject dataInfo = new JSONObject();
		dataInfo.put(AxisSetupKey.ContinuousDriver, this.fxConDriverField.getText());
		dataInfo.put(AxisSetupKey.ContinuousMotor, this.fxConMotorField.getText());
		dataInfo.put(AxisSetupKey.MaxVoltage, this.fxMaxVoltageField.getText());
		dataInfo.put(AxisSetupKey.MaxCurrent, this.fxMaxCurrentField.getText());
		dataInfo.put(AxisSetupKey.PWMLimit, this.fxPWMField.getText());
		dataInfo.put(AxisSetupKey.PeakMotor, this.fxPeakMotorField.getText());
		dataInfo.put(AxisSetupKey.PeakDriver, this.fxPeakDriverField.getText());
		dataInfo.put(AxisSetupKey.PeakDuration, this.fxPeakDurationField.getText());
		dataInfo.put(AxisSetupKey.IntegralLimit, this.fxInterfalLimitField.getText());	
		FileSaveManager.getInstance().setAxisChangeData(Tag, dataInfo);
	}
	
	//저장된 화면 정보를 불러온다...
	public void getData() {
		JSONObject mainObject = FileSaveManager.getInstance().readAxisSetupFile();

		if(mainObject!=null) {
			JSONObject object = (JSONObject) mainObject.get(Tag);
			if(object==null) return;
			System.out.println(Tag+"== getData \n ==> "+object.toString());
			this.fxConDriverField.setText((String)object.get(AxisSetupKey.ContinuousDriver));
			this.fxConMotorField.setText((String)object.get(AxisSetupKey.ContinuousMotor));
			this.fxMaxVoltageField.setText((String)object.get(AxisSetupKey.MaxVoltage));
			this.fxMaxCurrentField.setText((String)object.get(AxisSetupKey.MaxCurrent));
			this.fxPWMField.setText((String)object.get(AxisSetupKey.PWMLimit));
			this.fxPeakMotorField.setText((String)object.get(AxisSetupKey.PeakMotor));
			this.fxPeakDriverField.setText((String)object.get(AxisSetupKey.PeakDriver));
			this.fxPeakDurationField.setText((String)object.get(AxisSetupKey.PeakDuration));
			this.fxInterfalLimitField.setText((String)object.get(AxisSetupKey.IntegralLimit));
		}
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
		if(mouseEvent.getSource() == this.treeview) {
			treeViewManager.setMainApp(this.mainApp);
			treeViewManager.mouseClick(mouseEvent);
		}
	}

	@Override
	public void clieckMenu() {

	}

}
