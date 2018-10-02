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

public class ControllerConfigurator5Controller {

	static final String Tag = "ControllerConfigurator5Controller";

	ObservableList<String> fastModeComboList = FXCollections
			.observableArrayList("Position and Velocity","User Value");

	@FXML
	private ComboBox<String> fastModeCombo;

	ObservableList<String> fastSourceComboList = FXCollections
			.observableArrayList("a01","a02","a03","a04","v01");

	@FXML
	private ComboBox<String> fastSourceCombo;

	ObservableList<String> fastParameterComboList = FXCollections
			.observableArrayList("Not Set","Target Position","Actual Position","Velocity");

	@FXML
	private ComboBox<String> fastParameterCombo;
	@FXML
	private ListView<String> fxListView;

	private ControllerTreeViewManager mControllerTreeViewManager;

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
		fastModeCombo.setItems(fastModeComboList);
		fastSourceCombo.setItems(fastSourceComboList);
		fastParameterCombo.setItems(fastParameterComboList);
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
