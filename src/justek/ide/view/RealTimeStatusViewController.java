package justek.ide.view;

import justek.ide.MainApp;
import justek.ide.model.CommandConst;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class RealTimeStatusViewController {

	private MainApp mainApp;
	
	ObservableList<String> driverList = FXCollections.observableArrayList("Driver01", "Driver02", "Driver03");
	
	@FXML
	private TextField statusField;
	
	@FXML
	private TextField targetPositionField;
	@FXML
	private TextField actualPositionField;
	@FXML
	private TextField actualVelocityField;
	
	@FXML
	private ComboBox<String> driverComboBox;
	
	
	public int selectDriveNo = 1;
	public String numberDriveNo = new Integer(selectDriveNo).toString();
	
	/**
	* Is called by the main application to give a reference back to itself.
	* 
	* @param mainApp
	*/
	public void setMainApp(MainApp mainApp) {
	  this.mainApp = mainApp;
	} 
	
    @FXML
    private void initialize() {
    	driverComboBox.setValue("Driver01");
    	driverComboBox.setItems(driverList);
    }
    
    
	@FXML
	// ����̹� ���ÿ� ���� ����� ������ ȣ���ϵ��� �Ѵ�.
	private void driverChoice() {

		// ���õ� ����̹��� ��Ʈ������ ��ȯ�Ͽ� �����Ѵ�.
		if (driverComboBox.getValue().equals("Driver01")) {
			selectDriveNo = 1;
			numberDriveNo = new Integer(selectDriveNo).toString();
		} else if (driverComboBox.getValue().equals("Driver02")) {
			selectDriveNo = 2;
			numberDriveNo = new Integer(selectDriveNo).toString();
		} else if (driverComboBox.getValue().equals("Driver03")) {
			selectDriveNo = 3;
			numberDriveNo = new Integer(selectDriveNo).toString();
		}
	}
	
}
