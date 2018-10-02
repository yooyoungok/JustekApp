package justek.ide.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import justek.ide.MainApp;
import justek.ide.model.CommandConst;
import justek.ide.model.listener.TreeEventListener;
import justek.ide.model.manager.ControllerTreeViewManager;

public class ControllerConfigurator2Controller implements TreeEventListener {
	
	static final String Tag = "ControllerConfigurator2Controller";
	
    @FXML
    private ListView<String> fxListView;
    
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

	}
	
	/**
	* Is called by the main application to give a reference back to itself.
	* 
	* @param mainApp
	*/
	public void setMainApp(MainApp mainApp) {
	  this.mainApp = mainApp;
	  this.mainApp.listener = this;
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

	@Override
	public void clieckMenu() {
		// TODO Auto-generated method stub

	}
}
