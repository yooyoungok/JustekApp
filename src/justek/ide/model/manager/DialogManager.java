package justek.ide.model.manager;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.controlsfx.dialog.ExceptionDialog;

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
import javafx.stage.StageStyle;
import justek.ide.MainApp;
import justek.ide.model.CommandConst;
import justek.ide.view.LoadingDialogController;

public class DialogManager {

	static final String Tag = "DialogManager";

	private static DialogManager instance;
	
	Stage newWindow ;

	public static DialogManager getInstance () {
		if(instance==null) {
			instance = new DialogManager();
		}
		return instance;
	}

	public void showConfirmDialog(String  message) {
		ButtonType foo = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
		Alert alert = new Alert(AlertType.CONFIRMATION,message,foo); 
		alert.setTitle("CONFIRMATION"); 
		alert.setHeaderText("CONFIRMATION");
		alert.show();
	}
	
	public boolean showConfirmDialogWithResult(String  message) {
		ButtonType foo = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
		Alert alert = new Alert(AlertType.CONFIRMATION,message,foo); 
		alert.setTitle("CONFIRMATION"); 
		alert.setHeaderText("CONFIRMATION");
		Optional<ButtonType> result = alert.showAndWait(); 		
		if ((result.isPresent()) && (result.get() ==foo)) { 
			return true;		
		}
		else {
			return false;
		}
	}
	
	public boolean showConfirmDialogWithCancle(String  message) {
		ButtonType foo = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
		Alert alert = new Alert(AlertType.CONFIRMATION,message,foo); 
		alert.setTitle("CONFIRMATION"); 
		alert.setHeaderText("CONFIRMATION");
		Optional<ButtonType> result = alert.showAndWait(); 		
		if ((result.isPresent()) && (result.get() ==foo)) { 
			return true;		
		}
		
		return false;
	}
	
	public void showServerErrorConfirmDialog(String  error) {
		Alert alert = new Alert(AlertType.ERROR); 
		alert.setTitle("Error"); 
		alert.setContentText(error); 
		alert.setHeaderText("ERROR");
		alert.show();
	}

	public void showErrorConfirmDialog(Exception error) {
		ExceptionDialog exceptionDialog = new ExceptionDialog(error);
		exceptionDialog.setTitle("Error");
		exceptionDialog.setHeaderText("Error Message");
		exceptionDialog.show();

	}

	//시스템 정보 화면을 종료 확인팝업
	public boolean showSystemConfigOutConfirmDialog() {
		ButtonType foo = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
		ButtonType bar = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);
		String content = "시스템 정보 화면 모드를 종료합니다.";
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

	        // 다이얼로그 스테이지를 만든다.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Chart View");
	        dialogStage.initModality(Modality.NONE);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        dialogStage.show();
//	        // person을 컨트롤러에 설정한다.
//	        PersonEditDialogController controller = loader.getController();
//	        controller.setDialogStage(dialogStage);
//	        controller.setPerson(person);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void openLoadingView() {
		try {
			newWindow = new Stage();
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/LoadingDialog.fxml"));
			AnchorPane layout = (AnchorPane) loader.load();
			// Show the scene containing the root layout.
			Scene scene = new Scene(layout,150,75);
			@SuppressWarnings("unused")
			LoadingDialogController controller = loader.getController();
			newWindow.centerOnScreen();
			newWindow.initStyle(StageStyle.TRANSPARENT);
			newWindow.initModality(Modality.APPLICATION_MODAL);
			newWindow.setScene(scene);
			newWindow.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void hideLoadingView() {
		if(this.newWindow!=null) {
			this.newWindow.close();
			this.newWindow = null;
		}
	}

}
