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


public class AxisSetup7Controller implements TreeEventListener {

	static final String Tag = "AxisSetup7Controller";
	
    @FXML
    private TreeView<String> treeView;

    @FXML
    private Label fxDriverLabel;
    
    @FXML
    private TextField fxVelocityField;

    @FXML
    private TextField fxPositionTimeField;

    @FXML
    private TextField fxVeloticyTimeField;

    @FXML
    private TextField fxPositionField;
    
	ObservableList<String> axis0Combo1List = FXCollections
			.observableArrayList("Single Axis","Stepper Axis(No Feedback)","Stepper Axis(with Feedback)","Gantry","Planar","Synchronized Current","Current Amplifier");

	ObservableList<String> s1List = FXCollections
			.observableArrayList("Master","Slave");
	
	ObservableList<String> s2List = FXCollections
			.observableArrayList("X Master","X Slave","Y Master","Y Slave");

	//TreeView List
	private ObservableList<TreeItem<String>> nodeList;

	private TreeViewManager treeViewManager;

	Image icon = new Image (
			getClass().getResourceAsStream("/img/folder-icon.png"));
	
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
		dataInfo.put(AxisSetupKey.VelocityWindow, this.fxVelocityField.getText());
		dataInfo.put(AxisSetupKey.VeloticyTimeWindow, this.fxVeloticyTimeField.getText());
		dataInfo.put(AxisSetupKey.PositionTimeWindow, this.fxPositionTimeField.getText());
		dataInfo.put(AxisSetupKey.PositionWindow, this.fxPositionField.getText());

		FileSaveManager.getInstance().setAxisChangeData(Tag, dataInfo);
	}
	
	//저장된 화면 정보를 불러온다...
	public void getData() {
		JSONObject mainObject = FileSaveManager.getInstance().readAxisSetupFile();

		if(mainObject!=null) {
			JSONObject object = (JSONObject) mainObject.get(Tag);
			if(object==null) return;
			System.out.println(Tag+"== getData \n ==> "+object.toString());
			this.fxVelocityField.setText((String)object.get(AxisSetupKey.VelocityWindow));
			this.fxVeloticyTimeField.setText((String)object.get(AxisSetupKey.VeloticyTimeWindow));
			this.fxPositionTimeField.setText((String)object.get(AxisSetupKey.PositionTimeWindow));
			this.fxPositionField.setText((String)object.get(AxisSetupKey.PositionWindow));
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
