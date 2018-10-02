package justek.ide.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import justek.ide.MainApp;
import justek.ide.model.CommandConst;
import justek.ide.model.manager.DialogManager;

public class AddDriverController {


	@FXML
	private TextField fxNameTextField;
	
	// Reference to the main application
	private MainApp mainApp;
	
	private String preName;

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

	}   


	/**
	 * @author : YOO YOUNGOK 
	 * @method  setInitialName
	 * @param name
	 * @return  void
	 * @exception 
	 * Desc ���ο� ����̹� ���� ���Ƿ� �����Ͽ� ���޹޾� ȭ�鿡 �����Ѵ�.
	 * @since 2018. 8. 14.
	 */
	public void setInitialName(String name) {
		this.preName = name;
		fxNameTextField.setText(name);
	}
	
    /**
     * @author : YOO YOUNGOK 
     * @method  onCllickSaveDriver
     * @param event
     * @return  void
     * @exception 
     * @Desc ����̹� �߰� �Ϸ� ��ư(�˾����� �ٽ� �ѹ� Ȯ�� �� ���ο� ����̹��� �߰��Ѵ�..) 
     * @since 2018. 8. 14.
     */
    @FXML
    void onCllickSaveDriver(ActionEvent event) {
    	boolean result = DialogManager.getInstance().showConfirmDialogWithResult("���ο� Driver�� �߰��Ͻðڽ��ϱ�?");
    	if(result) {
    		
    		 for(String name:CommandConst.DRIVER_MAP.keySet()) {
    			 if(name.equals(this.preName)){
    				 CommandConst.driverList.remove(name);
    				 CommandConst.DRIVER_MAP.remove(preName);
    				 CommandConst.driverList.add(this.fxNameTextField.getText());
    				 String num = String.valueOf( CommandConst.driverList.indexOf(this.fxNameTextField.getText()));
    				 System.out.println("������ ����̹��� ��ȣ�� "+ num+"�Դϴ�.");
    				 CommandConst.DRIVER_MAP.put(this.fxNameTextField.getText(),num);
    			 }
    		 }
    		 
    		 this.mainApp.SaveDriver(this.fxNameTextField.getText());
    	}
    }
}
