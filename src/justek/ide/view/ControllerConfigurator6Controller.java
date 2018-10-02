package justek.ide.view;


import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import justek.ide.MainApp;

public class ControllerConfigurator6Controller {

	ObservableList<String> haltComboList = FXCollections
			.observableArrayList("Slow down on QS deceleration","Slow down on Profile Deceleration");

	@FXML
	private ComboBox haltCombo;

	ObservableList<String> abortComboList = FXCollections
			.observableArrayList("No Action","Fault Reaction","Quick Stop Reaction");

	@FXML
	private ComboBox abortCombo;
	ObservableList<String> driveFaultComboList = FXCollections
			.observableArrayList("Power Off","Decelerate on Profile Deceleration and power off","Decelerate on QS deceleration and power off");

	@FXML
	private ComboBox driveFaultCombo;
	ObservableList<String> driveQuickStopComboList = FXCollections
			.observableArrayList("Disable drive function","Decelerate on Profile Deceleration and transit to Switch On Disable",
					"Decelerate on QS Deceleration (0x6085) and transit to Switch On Disable",
					"Decelerate on Profile Deceleration and stay in Quick Stop Active",
					"Decelerate on QS Deceleration (0x6085) and stay in Quick Stop Active");

	@FXML
	private ComboBox driveQuickStopCombo;


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
		haltCombo.setItems(haltComboList);
		abortCombo.setItems(abortComboList);
		driveFaultCombo.setItems(driveFaultComboList);
		driveQuickStopCombo.setItems(driveQuickStopComboList);
	}	



}
