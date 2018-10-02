package justek.ide.view;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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


public class AxisSetup1Controller  implements TreeEventListener{

	static final String Tag = "AxisSetup1Controller";
	
	ObservableList<String> motorTypeCombo1List = FXCollections
			.observableArrayList("Rotary Brush","Rotary Brushless","Linear DC","Linear Brushless");

    @FXML
    private TreeView<String> treeView;
    
	@FXML
	private ComboBox<String> motorTypeCombo;

    @FXML
    private Label driverLabel;
    
    @FXML
    private TextField fxConStallCurrentField;

    @FXML
    private TextField fxPolePairsField;

    @FXML
    private TextField fxPeakCurrentField;

    @FXML
    private TextField fxMaxMotorSpeedField;

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
    	System.out.println(Tag+" initialize");
    	motorTypeCombo.setValue("Rotary Brush");
    	motorTypeCombo.setItems(motorTypeCombo1List);    

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
		dataInfo.put(AxisSetupKey.MotorType,this.motorTypeCombo.getSelectionModel().getSelectedItem());
		dataInfo.put(AxisSetupKey.PeakCurrent, this.fxPeakCurrentField.getText());
		dataInfo.put(AxisSetupKey.ContinuousStallCurrent, this.fxConStallCurrentField.getText());
		dataInfo.put(AxisSetupKey.MaximumMotorSpeed, this.fxMaxMotorSpeedField.getText());
		dataInfo.put(AxisSetupKey.PolePairsperRevolution, this.fxPolePairsField.getText());
		FileSaveManager.getInstance().setAxisChangeData(Tag, dataInfo);
	}
	
	//저장된 화면 정보를 불러온다...
	public void getData() {
		JSONObject mainObject = FileSaveManager.getInstance().readAxisSetupFile();

		if(mainObject!=null) {
			JSONObject object = (JSONObject) mainObject.get(Tag);
			if(object == null) return;
			System.out.println(Tag+"== getData \n ==> "+object.toString());
			this.motorTypeCombo.setValue((String)object.get(AxisSetupKey.MotorType));
			this.fxConStallCurrentField.setText((String)object.get(AxisSetupKey.ContinuousStallCurrent));
			this.fxMaxMotorSpeedField.setText((String)object.get(AxisSetupKey.MaximumMotorSpeed));
			this.fxPeakCurrentField.setText((String)object.get(AxisSetupKey.PeakCurrent));
			this.fxPolePairsField.setText((String)object.get(AxisSetupKey.PolePairsperRevolution));
		}
	}
	
	public void mouseClick(MouseEvent mouseEvent) {
		treeViewManager.setMainApp(this.mainApp);
		treeViewManager.mouseClick(mouseEvent);
	}

	@Override
	public void clieckMenu() {
		
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

}
