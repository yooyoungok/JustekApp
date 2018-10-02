package justek.ide.view;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import justek.ide.MainApp;
import justek.ide.model.CommandConst;
import justek.ide.model.listener.TreeEventListener;

public class EtherCATMasterController implements TreeEventListener {
	 
	private MainApp mainApp;
	
	/**
	* Is called by the main application to give a reference back to itself.
	* 
	* @param mainApp
	*/
	public void setMainApp(MainApp mainApp) {
	  this.mainApp = mainApp;
	  this.mainApp.listener = this;
	}

	@Override
	public void clieckMenu() {
		// TODO Auto-generated method stub
		
		if(!CommandConst.isController) {
			this.mainApp.EtherCATSlave();
		}
	} 
}