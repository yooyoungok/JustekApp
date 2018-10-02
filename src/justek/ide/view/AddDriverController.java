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
	 * Desc 새로운 드라이버 명을 임의로 설정하여 전달받아 화면에 노출한다.
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
     * @Desc 드라이버 추가 완료 버튼(팝업으로 다시 한번 확인 후 새로운 드라이버를 추가한다..) 
     * @since 2018. 8. 14.
     */
    @FXML
    void onCllickSaveDriver(ActionEvent event) {
    	boolean result = DialogManager.getInstance().showConfirmDialogWithResult("새로운 Driver를 추가하시겠습니까?");
    	if(result) {
    		
    		 for(String name:CommandConst.DRIVER_MAP.keySet()) {
    			 if(name.equals(this.preName)){
    				 CommandConst.driverList.remove(name);
    				 CommandConst.DRIVER_MAP.remove(preName);
    				 CommandConst.driverList.add(this.fxNameTextField.getText());
    				 String num = String.valueOf( CommandConst.driverList.indexOf(this.fxNameTextField.getText()));
    				 System.out.println("수정된 드라이버의 번호는 "+ num+"입니다.");
    				 CommandConst.DRIVER_MAP.put(this.fxNameTextField.getText(),num);
    			 }
    		 }
    		 
    		 this.mainApp.SaveDriver(this.fxNameTextField.getText());
    	}
    }
}
