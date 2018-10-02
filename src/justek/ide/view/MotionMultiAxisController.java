package justek.ide.view;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import justek.ide.MainApp;

public class MotionMultiAxisController {

	ObservableList<String> axisName1ComboList = FXCollections
			.observableArrayList("None","DriveNG02","G03 a01","G03.a02","DriveNG01","G03.a04");
	ObservableList<String> axisName2ComboList = FXCollections
			.observableArrayList("None","DriveNG02","G03 a01","G03.a02","DriveNG01","G03.a04");
	ObservableList<String> axisName3ComboList = FXCollections
			.observableArrayList("None","DriveNG02","G03 a01","G03.a02","DriveNG01","G03.a04");
	ObservableList<String> axisName4ComboList = FXCollections
			.observableArrayList("None","DriveNG02","G03 a01","G03.a02","DriveNG01","G03.a04");
	ObservableList<String> opMode1ComboList = FXCollections
			.observableArrayList("Position","Velocity","Current","Stepper");
	ObservableList<String> opMode2ComboList = FXCollections
			.observableArrayList("Position","Velocity","Current","Stepper");
	ObservableList<String> opMode3ComboList = FXCollections
			.observableArrayList("Position","Velocity","Current","Stepper");
	ObservableList<String> opMode4ComboList = FXCollections
			.observableArrayList("Position","Velocity","Current","Stepper");
	
	@FXML
	private ComboBox axisName1Combo;
	@FXML
	private ComboBox axisName2Combo;
	@FXML
	private ComboBox axisName3Combo;
	@FXML
	private ComboBox axisName4Combo;
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
	 axisName1Combo.setValue("None");
	 axisName1Combo.setItems(axisName1ComboList);
	 axisName2Combo.setValue("None");
	 axisName2Combo.setItems(axisName2ComboList);
	 axisName3Combo.setValue("None");
	 axisName3Combo.setItems(axisName3ComboList);
	 axisName4Combo.setValue("None");
	 axisName4Combo.setItems(axisName4ComboList);
	 opMode1Combo.setValue("Position");
	 opMode1Combo.setItems(opMode1ComboList);
	 opMode2Combo.setValue("Position");
	 opMode2Combo.setItems(opMode2ComboList);
	 opMode3Combo.setValue("Position");
	 opMode3Combo.setItems(opMode3ComboList);
	 opMode4Combo.setValue("Position");
	 opMode4Combo.setItems(opMode4ComboList);
	
  }
}
