package justek.ide.view;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import justek.ide.MainApp;

public class WorkspaceController {

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
     
}
