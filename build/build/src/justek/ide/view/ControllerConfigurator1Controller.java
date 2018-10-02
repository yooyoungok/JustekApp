package justek.ide.view;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import justek.ide.MainApp;
import justek.ide.model.manager.ControllerTreeViewManager;

public class ControllerConfigurator1Controller {

	static final String Tag = "ControllerConfigurator1Controller";
	
	ObservableList<String> positionComboList = FXCollections
			.observableArrayList("No conversion","Micrometers (um)","Millimeters (mm)","Inches (inch)","Degrees (deg)","Radians (rad)","Other");
	ObservableList<String> velocityComboList = FXCollections
			.observableArrayList("No Conversion","Micrometers/sec (um/sec)","Millimeters/sec (mm/sec)","Inches/sec (in/sec)",
					"Degrees/sec (deg/sec)","Radians/sec (rad/sec)","Rounds Per Minute (RPM)","Rounds Per Second (RPS)","Other");
	
	@FXML
	private ComboBox<String> positionCombo;
	@FXML
	private ComboBox<String> velocityCombo;
    @FXML
    private ListView<String> fxListView;
    @FXML
    private TextField fxPositionUnitField;
    @FXML
    private Label fxDriverLabel;
    
	private MainApp mainApp;
	
	private ControllerTreeViewManager mControllerTreeViewManager;
	
	   /**
	    * Initializes the controller class. This method is automatically called
	    * after the fxml file has been loaded.
	    */
	@FXML
	private void initialize() {
		positionCombo.setItems(positionComboList);
		velocityCombo.setItems(velocityComboList);
	}

	
	/**
	* Is called by the main application to give a reference back to itself.
	* 
	* @param mainApp
	*/
	public void setMainApp(MainApp mainApp) {
	  this.mainApp = mainApp;
	
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
