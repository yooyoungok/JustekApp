package justek.ide.view;


import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import org.json.simple.JSONObject;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import justek.ide.MainApp;
import justek.ide.model.AxisSetupKey;
import justek.ide.model.CommandConst;
import justek.ide.model.listener.TreeEventListener;
import justek.ide.model.manager.FileSaveManager;
import justek.ide.model.manager.TreeViewManager;


public class AxisSetup12Controller implements TreeEventListener {

	static final String Tag = "AxisSetup12Controller";
	
    @FXML
    private TreeView<String> treeView;
    @FXML
    private Label fxDriverLabel;
    @FXML
    private TextField fxVelocityField;
    @FXML
    private TextField fxDeplaceField;
    @FXML
    private TextField fxCurrentLevelField;
    @FXML
    private TextField fxVelolcityFeedBackField;
    @FXML
    private TextField fxPosicionFeedBackField;
    @FXML
    private TextField fxCommutFeedBackField;
    @FXML
    private TextField fxActiveCurrentField;
    @FXML
    private CheckBox fxVerticalCheckBox;
    @FXML
    private ComboBox<String> fxMethodComboBox;
    
    
    
	// Reference to the main application
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
//		this.setTreeView();
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
		dataInfo.put(AxisSetupKey.Velocity, this.fxVelocityField.getText());
		dataInfo.put(AxisSetupKey.Deplace, this.fxDeplaceField.getText());
		dataInfo.put(AxisSetupKey.CurrentLevel, this.fxCurrentLevelField.getText());
		dataInfo.put(AxisSetupKey.VelolcityFeedBack, this.fxVelolcityFeedBackField.getText());
		dataInfo.put(AxisSetupKey.PosicionFeedBack, this.fxPosicionFeedBackField.getText());
		dataInfo.put(AxisSetupKey.CommutFeedBack, this.fxCommutFeedBackField.getText());
		dataInfo.put(AxisSetupKey.ActiveCurrent, this.fxActiveCurrentField.getText());
		dataInfo.put(AxisSetupKey.VerticalValue,this.fxVerticalCheckBox.isSelected() );
		dataInfo.put(AxisSetupKey.AutoMethodValue, this.fxMethodComboBox.getSelectionModel().getSelectedItem());

		FileSaveManager.getInstance().setAxisChangeData(Tag, dataInfo);
		
	}
	
	//저장된 화면 정보를 불러온다...
	public void getData() {
		JSONObject mainObject = FileSaveManager.getInstance().readAxisSetupFile();

		if(mainObject!=null) {
			JSONObject object = (JSONObject) mainObject.get(Tag);
			if(object==null) return;
			
			System.out.println(Tag+"== getData \n ==> "+object.toString());
			this.fxVelocityField.setText((String)object.get(AxisSetupKey.Velocity));
			this.fxDeplaceField.setText((String)object.get(AxisSetupKey.Deplace));
			this.fxCurrentLevelField.setText((String)object.get(AxisSetupKey.CurrentLevel));
			this.fxVelolcityFeedBackField.setText((String)object.get(AxisSetupKey.VelolcityFeedBack));
			this.fxPosicionFeedBackField.setText((String)object.get(AxisSetupKey.PosicionFeedBack));
			this.fxCommutFeedBackField.setText((String)object.get(AxisSetupKey.CommutFeedBack));
			this.fxActiveCurrentField.setText((String)object.get(AxisSetupKey.ActiveCurrent));
			if( (boolean)object.get(AxisSetupKey.VerticalValue)) {
				this.fxVerticalCheckBox.setSelected(true);
			}
			else {
				this.fxVerticalCheckBox.setSelected(false);
			}
			this.fxMethodComboBox.setValue((String)object.get(AxisSetupKey.AutoMethodValue));
		}
	}
	
	
    @FXML
    void onClickHold(ActionEvent event) {
    	System.out.println(Tag+" == onClickHold ");
    }
    
    @FXML
    void onClickLeft(ActionEvent event) {
    	System.out.println(Tag+" == onClickLeft ");
    }
    
    @FXML
    void onClickRight(ActionEvent event) {
    	System.out.println(Tag+" == onClickRight ");
    }
    @FXML
    void onClickRun(ActionEvent event) {
    	System.out.println(Tag+" == onClickRun ");
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
