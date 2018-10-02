package justek.ide.view;


import javafx.fxml.FXML;
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


public class AxisSetup3Controller implements TreeEventListener {

	static final String Tag = "AxisSetup3Controller";
	
	ObservableList<String> positionUnitsComboList = FXCollections
			.observableArrayList("No Conversion");

	ObservableList<String> velocityUnitsComboList = FXCollections
			.observableArrayList("No conversion");
	
	@FXML
	private ComboBox<String> positionUnitsCombo;
	@FXML
	private ComboBox<String> velocityUnitsCombo;

    @FXML
    private Label fxdriverLabel;

    @FXML
    private TextField fxPosionUnitField;

    @FXML
    private TreeView<String> treeView;

    @FXML
    private TextField fxVelocityUnitField;

    @FXML
    private RadioButton fxLoadRadioButton;

    @FXML
    private RadioButton fxMotorRadioButton;

	   // Reference to the main application
    private MainApp mainApp;

    private TreeViewManager treeViewManager;
    
	//TreeView List
	private ObservableList<TreeItem<String>> nodeList;

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
    	positionUnitsCombo.setItems(positionUnitsComboList);
    	positionUnitsCombo.setValue(this.positionUnitsComboList.get(0));
    	velocityUnitsCombo.setItems(velocityUnitsComboList);
    	velocityUnitsCombo.setValue(this.velocityUnitsComboList.get(0));

//    	this.setTreeView();
    	
    	this.getData();
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
	
	public void onSelectComboBox() {
		this.saveData();
	}
	
	public void saveData(KeyEvent event) {
		if(event.getCode()!=KeyCode.ENTER) return;
		System.out.println(Tag+"== saveData");
		this.saveData();
	}
	
	@SuppressWarnings("unchecked")
	public void saveData() {
		JSONObject dataInfo = new JSONObject();
		dataInfo.put(AxisSetupKey.PositionDisplayUnits, this.positionUnitsCombo.getSelectionModel().getSelectedItem());
		dataInfo.put(AxisSetupKey.VelocityDisplayUnits, this.velocityUnitsCombo.getSelectionModel().getSelectedItem());
		
		if(this.fxLoadRadioButton.isSelected()) {
			dataInfo.put(AxisSetupKey.UnitCalculation, this.fxLoadRadioButton.getText());
		}
		else {
			dataInfo.put(AxisSetupKey.UnitCalculation, this.fxMotorRadioButton.getText());
		}
		
		dataInfo.put(AxisSetupKey.PosionUnitFactor, this.fxPosionUnitField.getText());
		dataInfo.put(AxisSetupKey.VelocityDisplayFactor, this.fxVelocityUnitField.getText());
		FileSaveManager.getInstance().setAxisChangeData(Tag, dataInfo);
	}
	
	//저장된 화면 정보를 불러온다...
	public void getData() {
		JSONObject mainObject = FileSaveManager.getInstance().readAxisSetupFile();

		if(mainObject!=null) {
			JSONObject object = (JSONObject) mainObject.get(Tag);
			if(object==null) return;
			System.out.println(Tag+"== getData \n ==> "+object.toString());
			this.positionUnitsCombo.setValue((String)object.get(AxisSetupKey.PositionDisplayUnits));
			this.velocityUnitsCombo.setValue((String)object.get(AxisSetupKey.VelocityDisplayUnits));
			this.fxPosionUnitField.setText((String)object.get(AxisSetupKey.PosionUnitFactor));
			this.fxVelocityUnitField.setText((String)object.get(AxisSetupKey.VelocityDisplayFactor));
			if(((String)object.get(AxisSetupKey.UnitCalculation)).equals(this.fxLoadRadioButton.getText())) {
				this.fxLoadRadioButton.setSelected(true);
			}
			else {
				this.fxMotorRadioButton.setSelected(true);
			}
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
		treeViewManager.setMainApp(this.mainApp);
		treeViewManager.mouseClick(mouseEvent);
	}

	@Override
	public void clieckMenu() {

	}
	
}
