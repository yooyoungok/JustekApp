package justek.ide.view;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import justek.ide.MainApp;
import justek.ide.model.CommandConst;
import justek.ide.model.TriggerInfo;

public class TriggerController {
	
    @FXML
    private ComboBox triggerComboBox;
    @FXML
    private TextField levelTextField;
    @FXML
    private TextField delayTextField;
    @FXML
    private RadioButton singleRadioButton;
    @FXML
    private RadioButton normalRadioButton;
    @FXML
    private RadioButton positiveRadioButton;
    @FXML
    private RadioButton negativeRadioButton;
	
	private Stage dialogStage;
    private boolean okClicked = false;
    private ObservableList<String> list;
    private TriggerInfo info;
    
	private MainApp mainApp;
	
	//Trigger Setting Info
	private double level;
	private double delayTime;
	private String mode;
	private String edge;
	private String Source;
	
	
	
	@FXML
	private void initialize() {
		list =  FXCollections.observableArrayList(CommandConst.TRIGGER_RLIST);
		this.triggerComboBox.setItems(list);
	}
	/**
	* Is called by the main application to give a reference back to itself.
	* 
	* @param mainApp
	*/
	public void setMainApp(MainApp mainApp) {
	  this.mainApp = mainApp;
	} 
	
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setTriggerInfo(TriggerInfo info) {
    	this.info = info;
    }
    public boolean isOkClicked() {
        return okClicked;
    }
    
    
    @FXML
    public void startTrigger() {
    	
    }
    
    @FXML
    public void exit() {
    	dialogStage.close();
    }
    
    @FXML
    public void clickOK() {
    	okClicked = true;
    	
    	info.setSource(this.triggerComboBox.getSelectionModel().getSelectedItem().toString());
    	info.setLevel(this.levelTextField.getText());
    	
    	String mode = null;
    	if(this.normalRadioButton.isSelected()) {
    		mode = this.normalRadioButton.getText();
    	}
    	else if(this.singleRadioButton.isSelected()) {
    		mode = this.singleRadioButton.getText();
    	}
    	info.setMode(mode);
    	
    	String slope = null;
    	if(this.positiveRadioButton.isSelected()) {
    		slope = this.positiveRadioButton.getText();
    	}
    	else if(this.negativeRadioButton.isSelected()) {
    		slope = this.negativeRadioButton.getText();
    	}
    	info.setSlope(slope);
    	
    	info.setDelay(this.delayTextField.getText());
    	
    	dialogStage.close();
    }
}
