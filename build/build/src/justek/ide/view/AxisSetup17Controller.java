package justek.ide.view;


import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import justek.ide.MainApp;
import justek.ide.model.CommandConst;
import justek.ide.model.listener.TreeEventListener;
import justek.ide.model.manager.TreeViewManager;


public class AxisSetup17Controller implements TreeEventListener {

	static final String Tag = "AxisSetup17Controller";
	
	@FXML
	private TreeView<String> treeView;
	@FXML
	private Label fxDriverLabel;
	
    @FXML
    private TextField fxDesignFileField;

    @FXML
    private TextField fxUploadFileField;

    @FXML
    private CheckBox fxUploadCheckBox;

    @FXML
    private CheckBox fxSaveCheckBox;

    @FXML
    private CheckBox fxSaveDesignCheckBox;

    @FXML
    private TextArea fxSummaryTextArea;
	

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
		this.setTreeView();
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
	void onClickSave(ActionEvent event) {
		System.out.println(Tag+" == onClickSave");
	}

	@FXML
	void onClickCancel(ActionEvent event) {
		System.out.println(Tag+" == onClickCancel ");
	}
	
	@FXML
	void onClickFile(ActionEvent event) {
		System.out.println(Tag+" == onClickFile ");
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
