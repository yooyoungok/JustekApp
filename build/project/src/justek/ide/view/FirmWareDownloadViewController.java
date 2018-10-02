package justek.ide.view;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import justek.ide.MainApp;
import justek.ide.model.CommandConst;
import justek.ide.model.listener.TreeEventListener;
import justek.ide.model.manager.NetworkServerManager;

public class FirmWareDownloadViewController implements TreeEventListener{
	
	static final String Tag = "FirmWareDownloadViewController";
	
    @FXML
    private Label fxDescriptionLabel;

    @FXML
    private TextField fxFileNameField;

    @FXML
    private Label fxDriverNoLabel;
	
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

	@FXML
	private void initialize() {
		this.fxDriverNoLabel.setText(CommandConst.DRIVER);
		this.fxFileNameField.setText(System.getProperty("user.dir").toString());
	}
	
    @FXML
    void onClickDownload(ActionEvent event) {
    	System.out.println(Tag+" == onClickDownload ");
    	
    	NetworkServerManager.firmwareDownload(this.fxDriverNoLabel.getText(), this.fxFileNameField.getText());
    }

    @FXML
    void onClickCancel(ActionEvent event) {
    	System.out.println(Tag+" == onClickCancel ");
    }
    
    @FXML
    void onClickFileDialog(ActionEvent event) {
    	System.out.println(Tag+" == onClickFileDialog ");
    	String file = this.openFileChoolser();
    	if(file!=null) {
    		this.fxDescriptionLabel.setText("Selected File -> "+file);
    		this.fxFileNameField.setText(file);
    	}
    	else {
    		this.fxDescriptionLabel.setText("No Selected File");
    	}
    }

	public String openFileChoolser() {
		String result = null;
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")+"/home/firmware"));
		fileChooser.getExtensionFilters().clear();
		// Add Extension Filters
		fileChooser.getExtensionFilters().addAll(//
				new FileChooser.ExtensionFilter("hex", "*.hex")); //

		File file = fileChooser.showOpenDialog(this.mainApp.getPrimaryStage());

		if (file != null) {
//			result = file.getName();
			result = file.getAbsolutePath(); //-> 현재는 절대 경로를 전달한다...
		}
		return result;
	}
	
	@Override
	public void clieckMenu() {
		this.fxDriverNoLabel.setText(CommandConst.DRIVER);
	}
	
	
}
