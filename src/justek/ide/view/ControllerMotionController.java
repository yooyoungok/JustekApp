package justek.ide.view;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import justek.ide.MainApp;

public class ControllerMotionController {

	ObservableList<String> opMode1ComboList = FXCollections
			.observableArrayList("Profile Position","Profile Velocity","Homing","Cyclic Position","Cyclic Velocity","Cyclic Torque");
	ObservableList<String> opMode2ComboList = FXCollections
			.observableArrayList("Profile Position","Profile Velocity","Homing","Cyclic Position","Cyclic Velocity","Cyclic Torque");
	ObservableList<String> opMode3ComboList = FXCollections
			.observableArrayList("Profile Position","Profile Velocity","Homing","Cyclic Position","Cyclic Velocity","Cyclic Torque");
	ObservableList<String> opMode4ComboList = FXCollections
			.observableArrayList("Profile Position","Profile Velocity","Homing","Cyclic Position","Cyclic Velocity","Cyclic Torque");
	
	@FXML
	private ComboBox opMode1Combo;
	@FXML
	private ComboBox opMode2Combo;
	@FXML
	private ComboBox opMode3Combo;
	@FXML
	private ComboBox opMode4Combo;
	
	private MainApp mainApp;
	
	/**
	* Is called by the main application to give a reference back to itself.
	* 
	* @param mainApp
	*/
	public void setMainApp(MainApp mainApp) {
	  this.mainApp = mainApp;
	
	} 
	
	
	   /**
	    * Initializes the controller class. This method is automatically called
	    * after the fxml file has been loaded.
	    */
	@FXML
	private void initialize() {

		opMode1Combo.setItems(opMode1ComboList);
		opMode2Combo.setItems(opMode2ComboList);
		opMode3Combo.setItems(opMode3ComboList);
		opMode4Combo.setItems(opMode4ComboList);

	}

	
	
}
