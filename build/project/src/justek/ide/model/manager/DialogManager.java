package justek.ide.model.manager;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import justek.ide.MainApp;
import justek.ide.model.CommandConst;

public class DialogManager {

	static final String Tag = "DialogManager";

	private static DialogManager instance;

	public static DialogManager getInstance () {
		if(instance==null) {
			instance = new DialogManager();
		}
		return instance;
	}

	public void showServerErrorConfirmDialog(String error) {
		Alert alert = new Alert(AlertType.ERROR); 
		alert.setTitle("Error"); 
		alert.setContentText(error); 
		alert.setHeaderText("ERROR");
		alert.show();
	}


	//�ý��� ���� ȭ���� ���� Ȯ���˾�
	public boolean showSystemConfigOutConfirmDialog() {
		ButtonType foo = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
		ButtonType bar = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);
		String content = "�ý��� ���� ȭ�� ��带 �����մϴ�.";
		Alert alert = new Alert(AlertType.CONFIRMATION,content,foo,bar); 
		alert.setTitle("CONFIRMATION"); 
		alert.setHeaderText("CONFIRMATION");

		Optional<ButtonType> result = alert.showAndWait(); 		
		if ((result.isPresent()) && (result.get() ==foo)) { 
			CommandConst.SYSTEM_CONFIG = false;
			return true;		
		}
		else {
			CommandConst.SYSTEM_CONFIG = true;
			return false;
		}
	}
	
	public void openChartView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ChartView.fxml"));

			AnchorPane page = (AnchorPane) loader.load();

	        // ���̾�α� ���������� �����.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Chart View");
	        dialogStage.initModality(Modality.NONE);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        dialogStage.show();
//	        // person�� ��Ʈ�ѷ��� �����Ѵ�.
//	        PersonEditDialogController controller = loader.getController();
//	        controller.setDialogStage(dialogStage);
//	        controller.setPerson(person);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
