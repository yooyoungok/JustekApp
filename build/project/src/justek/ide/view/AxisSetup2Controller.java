package justek.ide.view;


import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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


public class AxisSetup2Controller implements TreeEventListener {

	static final String Tag = "AxisSetup2Controller";
	
	ObservableList<String> digitalHallsCombo1List = FXCollections
			.observableArrayList("Yes","No");
	ObservableList<String> digitalHallsCombo2List = FXCollections
			.observableArrayList("Yes","No");
	ObservableList<String> digitalHallsCombo3List = FXCollections
			.observableArrayList("Yes","No");
	ObservableList<String> directionCombo1List = FXCollections
			.observableArrayList("Non Invert","Invert");
	ObservableList<String> directionCombo2List = FXCollections
			.observableArrayList("Non Invert","Invert");
	ObservableList<String> directionCombo3List = FXCollections
			.observableArrayList("Non Invert","Invert");
/*	ObservableList<String> feedbackCombo1List = FXCollections
			.observableArrayList("Analog Input #1","Analog Sin/Cos, Port B","Encoder Quad, Port A","Encoder Quad, Port B",
					"Halls Only, Port A","Pulse and Direction, Port A","Pulse and Direction, Port B","Resolver, Port B",
					"Virtual Absolute - Gurley, Port B");
	ObservableList<String> feedbackCombo2List = FXCollections
			.observableArrayList("Analog Input #1","Analog Sin/Cos, Port B","Encoder Quad, Port A","Encoder Quad, Port B",
					"Halls Only, Port A","Pulse and Direction, Port A","Pulse and Direction, Port B","Resolver, Port B",
					"Virtual Absolute - Gurley, Port B");*/
	ObservableList<String> feedbackCombo1List = FXCollections
			.observableArrayList("Analog Sin/Cos, Port B","Encoder Quad, Port A");
	ObservableList<String> feedbackCombo2List = FXCollections
			.observableArrayList("Analog Sin/Cos, Port B","Encoder Quad, Port A");
	ObservableList<String> feedbackCombo3List = FXCollections
			.observableArrayList("Socket 1","Socket 2","Socket 3","Socket 4");
	ObservableList<String> homeSocketComboList = FXCollections
			.observableArrayList("Default","Socket 1","Socket 2","Socket 3","Socket 4");
	ObservableList<String> multiFactorCombo1List = FXCollections
			.observableArrayList("2","3","4","5","6","7","8","9","10","11","12","13","14");
	ObservableList<String> multiFactorCombo2List = FXCollections
			.observableArrayList("2","3","4","5","6","7","8","9","10","11","12","13","14");
	ObservableList<String> multiFactorCombo3List = FXCollections
			.observableArrayList("2","3","4","5","6","7","8","9","10","11","12","13","14");
	ObservableList<String> socketSourceComboList = FXCollections
			.observableArrayList("Socket 1","Socket 2","Socket 3","Socket 4");
	ObservableList<String> velFilterCombo1List = FXCollections
			.observableArrayList("Disable","2","3","4","5","6","7","8");
	ObservableList<String> velFilterCombo2List = FXCollections
			.observableArrayList("Disable","2","3","4","5","6","7","8");
	ObservableList<String> velFilterCombo3List = FXCollections
			.observableArrayList("Disable","2","3","4","5","6","7","8");



	@FXML
	private ComboBox<String> digitalHallsCombo1;
	@FXML
	private ComboBox<String> digitalHallsCombo2;
	@FXML
	private ComboBox<String> directionCombo1;
	@FXML
	private ComboBox<String> directionCombo2;
	@FXML
	private ComboBox<String> directionCombo3;
	@FXML
	private ComboBox<String> feedbackCombo1;
	@FXML
	private ComboBox<String> feedbackCombo2;
	@FXML
	private ComboBox<String> feedbackCombo3;
	
	@FXML
	private ComboBox<String> homeSocketCombo;
	@FXML
	private ComboBox<String> multiFactorCombo1;
	@FXML
	private ComboBox<String> multiFactorCombo2;
	@FXML
	private ComboBox<String> multiFactorCombo3;
	@FXML
	private ComboBox<String> socketSourceCombo;
	@FXML
	private ComboBox<String> digitalHallsCombo3;
	@FXML
	private ComboBox<String> velFilterCombo1;
	@FXML
	private ComboBox<String> velFilterCombo2;
	@FXML
	private ComboBox<String> velFilterCombo3;
    @FXML
    private Label fxDriverLabel;
    
    @FXML
    private TreeView<String> treeView;

    private TreeViewManager treeViewManager;
    
	//TreeView List
	private ObservableList<TreeItem<String>> nodeList;

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
    	digitalHallsCombo1.setValue("Yes");
    	digitalHallsCombo1.setItems(digitalHallsCombo1List);
    	digitalHallsCombo2.setValue("Yes");
    	digitalHallsCombo2.setItems(digitalHallsCombo2List);
    	directionCombo1.setValue("Non Invert");
    	directionCombo1.setItems(directionCombo1List);
    	directionCombo3.setValue("Non Invert");
    	directionCombo3.setItems(directionCombo3List);
    	feedbackCombo1.setValue("None");
    	feedbackCombo1.setItems(feedbackCombo1List);
    	feedbackCombo2.setValue("None");
    	feedbackCombo2.setItems(feedbackCombo2List);
    	feedbackCombo3.setValue("None");
    	feedbackCombo3.setItems(feedbackCombo3List);
    	homeSocketCombo.setValue("Default");
    	homeSocketCombo.setItems(homeSocketComboList);
    	multiFactorCombo1.setValue("2");
    	multiFactorCombo1.setItems(multiFactorCombo1List);
    	multiFactorCombo2.setValue("2");
    	multiFactorCombo2.setItems(multiFactorCombo2List);
    	multiFactorCombo3.setValue("2");
    	multiFactorCombo3.setItems(multiFactorCombo3List);
    	socketSourceCombo.setValue("Socket 1");
    	socketSourceCombo.setItems(socketSourceComboList);
    	digitalHallsCombo3.setValue("Yes");
    	digitalHallsCombo3.setItems(digitalHallsCombo3List);
    	velFilterCombo1.setValue("Disable");
    	velFilterCombo1.setItems(velFilterCombo1List);
    	velFilterCombo2.setValue("Disable");
    	velFilterCombo2.setItems(velFilterCombo2List);
    	velFilterCombo3.setValue("Disable");
    	velFilterCombo3.setItems(velFilterCombo3List);
    	directionCombo2.setValue("Non Invert");
    	directionCombo2.setItems(directionCombo2List);
    	
//    	this.setTreeView();
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
