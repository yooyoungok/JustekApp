package justek.ide.view;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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


public class AxisSetup6Controller implements TreeEventListener{
	
	static final String Tag = "AxisSetup6Controller";

    @FXML
    private TreeView<String> treeView;

    @FXML
    private Label fxDriverLabel;
    
    @FXML
    private TextField fxUnderVoltageField;

    @FXML
    private TextField fxMaxTrackingField;

    @FXML
    private TextField fxCurrentLimitField;

    @FXML
    private TextField fxVelocityErrorField;

    @FXML
    private TextField fxMotorStuckField;

    @FXML
    private TextField fxPositionErrorField;

    @FXML
    private TextField fxYawPositionErrorField;

    @FXML
    private TextField fxOverVoltageField;

    @FXML
    private TextField fxVelocityLimitField;

    private MainApp mainApp;
    
	//TreeView List
	private ObservableList<TreeItem<String>> nodeList;

	private TreeViewManager treeViewManager;

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
//    	this.setTreeView();
    	this.getData();
     }
    
	//TreeView Node Setting!
	public void setTreeView() {
		treeViewManager = TreeViewManager.getInstance();
		treeViewManager.setMainApp(this.mainApp);
		this.nodeList = treeViewManager.getNodeInfo();
		treeViewManager.setTreeView(treeView);

		TreeItem<String> root = new TreeItem<>("Axis Configrations", new ImageView(icon));
		root.setExpanded(true);
		root.getChildren().addAll(nodeList);
		root.getChildren().get(0).setExpanded(true);
		root.getChildren().get(1).setExpanded(true);
		root.getChildren().get(2).setExpanded(true);
		root.getChildren().get(3).setExpanded(true);
		root.getChildren().get(4).setExpanded(true);
		treeView.setRoot(root);
	}
 
	public void saveData(ActionEvent event) {
		System.out.println(Tag+"== saveData");
		this.saveData();
	}

	@SuppressWarnings("unchecked")
	public void saveData() {
		JSONObject dataInfo = new JSONObject();
		dataInfo.put(AxisSetupKey.VelocityLimit, this.fxVelocityLimitField.getText());
		dataInfo.put(AxisSetupKey.OverVoltage, this.fxOverVoltageField.getText());
		dataInfo.put(AxisSetupKey.YawPositionError, this.fxYawPositionErrorField.getText());
		dataInfo.put(AxisSetupKey.PositionError, this.fxPositionErrorField.getText());
		dataInfo.put(AxisSetupKey.MotorStuck, this.fxMotorStuckField.getText());
		dataInfo.put(AxisSetupKey.VelocityError, this.fxVelocityErrorField.getText());
		dataInfo.put(AxisSetupKey.MaxTracking, this.fxMaxTrackingField.getText());
		dataInfo.put(AxisSetupKey.CurrentLimit, this.fxCurrentLimitField.getText());
		dataInfo.put(AxisSetupKey.UnderVoltage, this.fxUnderVoltageField.getText());

		FileSaveManager.getInstance().setAxisChangeData(Tag, dataInfo);
	}
	
	//저장된 화면 정보를 불러온다...
	public void getData() {
		JSONObject mainObject = FileSaveManager.getInstance().readAxisSetupFile();

		if(mainObject!=null) {
			JSONObject object = (JSONObject) mainObject.get(Tag);
			if(object==null) return;
			
			System.out.println(Tag+"== getData \n ==> "+object.toString());
			this.fxVelocityLimitField.setText((String)object.get(AxisSetupKey.VelocityLimit));
			this.fxOverVoltageField.setText((String)object.get(AxisSetupKey.OverVoltage));
			this.fxYawPositionErrorField.setText((String)object.get(AxisSetupKey.YawPositionError));
			this.fxPositionErrorField.setText((String)object.get(AxisSetupKey.PositionError));
			this.fxMotorStuckField.setText((String)object.get(AxisSetupKey.MotorStuck));
			this.fxVelocityErrorField.setText((String)object.get(AxisSetupKey.VelocityError));
			this.fxCurrentLimitField.setText((String)object.get(AxisSetupKey.CurrentLimit));
			this.fxMaxTrackingField.setText((String)object.get(AxisSetupKey.MaxTracking));
			this.fxUnderVoltageField.setText((String)object.get(AxisSetupKey.UnderVoltage));
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
		if(mouseEvent.getSource() == this.treeView) {
			treeViewManager.setMainApp(this.mainApp);
			treeViewManager.mouseClick(mouseEvent);
		}
	}

	@Override
	public void clieckMenu() {
		// TODO Auto-generated method stub

	}
 
}
