package justek.ide.model.manager;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class AxisSetupDataManager {

	static final String Tag = "DialogManager";
	
	private static AxisSetupDataManager instance;

	public static AxisSetupDataManager getInstance () {
		if(instance==null) {
			instance = new AxisSetupDataManager();
		}
		return instance;
	}
	
	public void showServerErrorConfirmDialog(String error) {
		Alert alert = new Alert(AlertType.ERROR); 
		alert.setTitle("Error"); 
		String s = "Server Connection Error"; 
		alert.setContentText(error); 
		alert.setHeaderText("ERROR");
		alert.show();
	}
}
