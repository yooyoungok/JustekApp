package justek.ide.view;



import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import javafx.stage.Stage;


public class NewWorkspaceController {
	
	@FXML
    private TextField workspaceNameField;


  
    private Stage dialogStage;

    private boolean okClicked = false;
    
    @FXML
    private void initialize() {
    }
    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        
            okClicked = true;
            dialogStage.close();

    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
