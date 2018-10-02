package justek.ide.view;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import justek.ide.MainApp;

public class CompensationTableController {
	
	ObservableList<String> socketComboList = FXCollections
			.observableArrayList("Socket 1","Socket 2","Socket 3","Socket 4");
	ObservableList<String> mappedComboList = FXCollections
			.observableArrayList("ET (1 to 2048)","NT (1 to 255)","UI (1 to 24)");
	ObservableList<String> measurementComboList = FXCollections
			.observableArrayList("Socket 1","Socket 2","Socket 3","Socket 4","Advanced");
	ObservableList<String> connectionComboList = FXCollections
			.observableArrayList("G01","G02");
	ObservableList<String> networkComboList = FXCollections
			.observableArrayList("a01","a02","a03","a04");
	ObservableList<String> numberRunsComboList = FXCollections
			.observableArrayList("1","2");
	ObservableList<String> vmeasurementComboList = FXCollections
			.observableArrayList("Socket 1","Socket 2","Socket 3","Socket 4","Advanced");
	ObservableList<String> vconnectionComboList = FXCollections
			.observableArrayList("G01","G02");
	ObservableList<String> vnetworkComboList = FXCollections
			.observableArrayList("a01","a02","a03","a04");
	ObservableList<String> vnumberRunsComboList = FXCollections
			.observableArrayList("1","2");
	
	
	@FXML
	private ComboBox socketCombo;
	@FXML
	private ComboBox mappedCombo;
	@FXML
	private ComboBox measurementCombo;
	@FXML
	private ComboBox connectionCombo;
	@FXML
	private ComboBox networkCombo;
	@FXML
	private ComboBox numberRunsCombo;
	@FXML
	private ComboBox vmeasurementCombo;
	@FXML
	private ComboBox vconnectionCombo;
	@FXML
	private ComboBox vnetworkCombo;
	@FXML
	private ComboBox vnumberRunsCombo;

	   // Reference to the main application
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

		socketCombo.setItems(socketComboList);
		mappedCombo.setItems(mappedComboList);
		measurementCombo.setItems(measurementComboList);
		connectionCombo.setItems(connectionComboList);
		networkCombo.setItems(networkComboList);
		numberRunsCombo.setItems(numberRunsComboList);
		vmeasurementCombo.setItems(vmeasurementComboList);
		vconnectionCombo.setItems(vconnectionComboList);
		vnetworkCombo.setItems(vnetworkComboList);
		vnumberRunsCombo.setItems(vnumberRunsComboList);

	
	}
}
