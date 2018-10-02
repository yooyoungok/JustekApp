package justek.ide.view;


import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import justek.ide.MainApp;
import justek.ide.model.CommandConst;
import justek.ide.model.listener.TreeEventListener;
import justek.ide.model.manager.TreeViewManager;


public class AxisSetup11Controller implements TreeEventListener {

	static final String Tag = "AxisSetup11Controller";

	@FXML
	private TreeView<String> treeView;
	@FXML
	private Label fxDriverLabel;
	@FXML
	private TextField fxMinCurrentField;
	@FXML
	private TextField fxMaxCurrentField;
	@FXML
	private RadioButton expRb1;
	@FXML
	private RadioButton expRb2;
	@FXML
	private RadioButton excRb2;
	@FXML
	private RadioButton excRb1;
	@FXML
	private ComboBox<String> fxIntegralComboBox;
	@FXML
	private ComboBox<String> fxGainComboBox;
	@FXML
	private CheckBox testCheckBox3;
	@FXML
	private ToggleGroup expType;
	@FXML
	private ToggleGroup excType;
	@FXML
	private CheckBox textCheckBox1;
	@FXML
	private CheckBox textCheckBox2;
	@FXML
	private CheckBox textCheckBox3;
	@FXML
	private ComboBox<String> fxFrequencyComboBox;
	@FXML
	private CheckBox fxVoltageCheckBox;
	@FXML
	private TextField fxPwmField;
	@FXML
	private TextField fxPreField;
	@FXML
	private TextField fxSlopeField;

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
//		this.setTreeView();
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

	@FXML
	void onClickVerify(ActionEvent event) {
		System.out.println(Tag+" == onClickVerify ");
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
