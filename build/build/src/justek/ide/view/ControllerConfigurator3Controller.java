package justek.ide.view;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import justek.ide.MainApp;
import justek.ide.model.manager.ControllerTreeViewManager;

public class ControllerConfigurator3Controller {
	
	ObservableList<String> moduloComboList = FXCollections
			.observableArrayList("No Modulo - Using Position Limits","Modulo (Maestro only)","Modulo (Maestro and Drive)");
	
	static final String Tag = "ControllerConfigurator3Controller";

	@FXML
	private ComboBox<String> moduloCombo;
	
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
		moduloCombo.setItems(moduloComboList);
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
