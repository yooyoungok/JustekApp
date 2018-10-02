package justek.ide.view;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import justek.ide.MainApp;
import justek.ide.model.CommandConst;
import justek.ide.model.manager.ControllerTreeViewManager;
import justek.ide.model.manager.TreeViewManager;

public class ControllerConfiguratorController {
	
	static final String Tag = "ControllerConfiguratorController";

    @FXML
    private ListView<String> fxListView;
    
	private MainApp mainApp;
	
	private ControllerTreeViewManager mControllerTreeViewManager;
	
	@FXML
	private void initialize() {

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
