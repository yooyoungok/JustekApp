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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import justek.ide.MainApp;
import justek.ide.model.AxisSetupKey;
import justek.ide.model.CommandConst;
import justek.ide.model.listener.TreeEventListener;
import justek.ide.model.manager.FileSaveManager;
import justek.ide.model.manager.TreeViewManager;


public class AxisSetup5Controller implements TreeEventListener{

	static final String Tag = "AxisSetup5Controller";

	ObservableList<String> moduloComboList = FXCollections
			.observableArrayList("No Modulo - Using Position Limits",
					"No Modulo - SW and HW position limits are ignored by Drive (for use with Cyclic reference Master)",
					"Modulo - No Position Limits"
					);


	@FXML
	private TextField fxLowModuloField;

	@FXML
	private TextField fxStopField;

	@FXML
	private TextField fxLowPosField;

	@FXML
	private TextField fxMaxVelocityField;

	@FXML
	private TextField fxHighPosField;

	@FXML
	private TextField fxHighModuloField;

	@FXML
	private CheckBox fxAdvanceCheckBox02;

	@FXML
	private CheckBox fxAdvanceCheckBox01;

	@FXML
	private ComboBox<String> moduloCombo;
	@FXML
	private TreeView<String> treeView;
	@FXML
	private Label fxDriverLabel;

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
		moduloCombo.setValue(moduloComboList.get(0));
		moduloCombo.setItems(moduloComboList);

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
		dataInfo.put(AxisSetupKey.LowModulo, this.fxLowModuloField.getText());
		dataInfo.put(AxisSetupKey.StopDeceleration, this.fxStopField.getText());
		dataInfo.put(AxisSetupKey.LowPosition, this.fxLowPosField.getText());
		dataInfo.put(AxisSetupKey.MaxVelocity, this.fxMaxVelocityField.getText());
		dataInfo.put(AxisSetupKey.HighPosition, this.fxHighPosField.getText());
		dataInfo.put(AxisSetupKey.HighModulo, this.fxHighModuloField.getText());
		dataInfo.put(AxisSetupKey.AdvanceCheckBox02, this.fxAdvanceCheckBox02.isSelected());
		dataInfo.put(AxisSetupKey.AdvanceCheckBox01,this.fxAdvanceCheckBox01.isSelected() );
		dataInfo.put(AxisSetupKey.ModuloOptions, this.moduloCombo.getSelectionModel().getSelectedItem());

		FileSaveManager.getInstance().setAxisChangeData(Tag, dataInfo);
		
	}
	
	//저장된 화면 정보를 불러온다...
	public void getData() {
		JSONObject mainObject = FileSaveManager.getInstance().readAxisSetupFile();

		if(mainObject!=null) {
			JSONObject object = (JSONObject) mainObject.get(Tag);
			if(object==null) return;
			
			System.out.println(Tag+"== getData \n ==> "+object.toString());
			this.fxLowModuloField.setText((String)object.get(AxisSetupKey.LowModulo));
			this.fxStopField.setText((String)object.get(AxisSetupKey.StopDeceleration));
			this.fxLowPosField.setText((String)object.get(AxisSetupKey.LowPosition));
			this.fxMaxVelocityField.setText((String)object.get(AxisSetupKey.MaxVelocity));
			this.fxHighPosField.setText((String)object.get(AxisSetupKey.HighPosition));
			this.fxHighModuloField.setText((String)object.get(AxisSetupKey.HighModulo));
			this.fxHighModuloField.setText((String)object.get(AxisSetupKey.HighModulo));
			if( (boolean)object.get(AxisSetupKey.AdvanceCheckBox02)) {
				this.fxAdvanceCheckBox02.setSelected(true);
			}
			else {
				this.fxAdvanceCheckBox02.setSelected(false);
			}
			if( (boolean)object.get(AxisSetupKey.AdvanceCheckBox01)) {
				this.fxAdvanceCheckBox01.setSelected(true);
			}
			else {
				this.fxAdvanceCheckBox01.setSelected(false);
			}
			this.moduloCombo.setValue((String)object.get(AxisSetupKey.ModuloOptions));
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

	}
}
