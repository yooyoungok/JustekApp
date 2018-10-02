package justek.ide.view;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import justek.ide.MainApp;

public class EtherCATDiagMasterController {

	ObservableList<String> initMasterStateComboList = FXCollections
			.observableArrayList("Init","Pre-Operational","Safe Operational","Operational","Bootstrap");
	
	@FXML
	private ComboBox initMasterStateCombo;
	
	
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

		initMasterStateCombo.setItems(initMasterStateComboList);
	

	}
}
