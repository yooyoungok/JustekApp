package justek.ide.view;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import justek.ide.MainApp;

public class ControllerConfigurator4Controller {
	private MainApp mainApp;
	
	/**
	* Is called by the main application to give a reference back to itself.
	* 
	* @param mainApp
	*/
	public void setMainApp(MainApp mainApp) {
	  this.mainApp = mainApp;
	
	}
    @FXML
    private void handleControllerConfigurator() {
    	 mainApp.ControllerConfigurator();
   }


    @FXML
    private void handleControllerConfigurator1() {
   	 mainApp.ControllerConfigurator1();
    }

    @FXML
    private void handleControllerConfigurator2() {
   	 mainApp.ControllerConfigurator2();
    }

    @FXML
    private void handleControllerConfigurator3() {
   	 mainApp.ControllerConfigurator3();
    }

    @FXML
    private void handleControllerConfigurator4() {
   	 mainApp.ControllerConfigurator4();
    }

    @FXML
    private void handleControllerConfigurator5() {
   	 mainApp.ControllerConfigurator5();
    }

    @FXML
    private void handleControllerConfigurator6() {
   	 mainApp.ControllerConfigurator6();
    }

    @FXML
    private void handleControllerConfiguratorG1() {
   	 mainApp.ControllerConfiguratorG1();
    }

    @FXML
    private void handleControllerConfiguratorG2() {
   	 mainApp.ControllerConfiguratorG2();
    }

    @FXML
    private void handleControllerConfiguratorG3() {
   	 mainApp.ControllerConfiguratorG3();
    }

    @FXML
    private void handleControllerConfiguratorG4() {
   	 mainApp.ControllerConfiguratorG4();
    }
 
}
