package justek.ide.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import justek.ide.model.CommandConst;
import justek.ide.model.ParamConstant;
import justek.ide.model.listener.WatchNodeListener;
import justek.ide.model.manager.NetworkServerManager;
import justek.ide.utils.StringUtil;

public class WatchNodeController {

	static final String Tag = "WatchNodeController";

	@FXML
	private ComboBox<String> nameBox;
	@FXML
	private ComboBox<String> signalbox;
    @FXML
    private ComboBox<String> fxParamComboBox;
    
	@FXML
	private TextField valueLabel;
	@FXML
	private Button fxDeleteButton;

	private WatchNodeListener listener;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		nameBox.setItems(CommandConst.driverList);
		if (fxParamComboBox!=null) {
			fxParamComboBox.setItems(CommandConst.PARAMETER_LIST);
		}
		this.setSignalBoxData();
		this.valueLabel.setText(" ");
	}

	public void setSignalBoxData() {
		ObservableList<String> sdoValue =  FXCollections.observableArrayList();
		for(String string:CommandConst.STATUS_LIST ) {
			sdoValue.add(string);
		}
		
		for(ParamConstant.SDOVALUE env:ParamConstant.SDOVALUE.values()) {
			System.out.println(env.name());
			sdoValue.add(env.getValue());
		}
		this.signalbox.setItems(sdoValue);
	}	

	//WatchNode2-> 파라미터 선택에 따른 Signal정보 리스트를 업데이트한다.
	public void setSignalBoxData(String parameterValue) {
		
		//업데이트전 현재의 리스트를 삭제한다.
		this.signalbox.getItems().clear();
		ObservableList<String> sdoValue =  FXCollections.observableArrayList();
		sdoValue.clear();
		if(parameterValue.equals("SDO")) {
			for(ParamConstant.SDOVALUE env:ParamConstant.SDOVALUE.values() ) {
//				System.out.println(env.name());
				sdoValue.add(env.getValue());
			}
		}
		else if(parameterValue.equals("PDO")) {
			for(String string:CommandConst.STATUS_LIST ) {
				sdoValue.add(string);
			}
		}
		else if(parameterValue.equals("HALL")) {
			for(String string:CommandConst.STATUS_LIST ) {
				sdoValue.add(string);
			}
		}
		this.signalbox.setItems(sdoValue);
	}
	
	@FXML
	void onClickCancel(ActionEvent event) {
		if(this.listener!=null) {
			this.listener.deleteSignal(this.getSignal());
		}
		this.fxDeleteButton.setVisible(false);
		this.nameBox.getSelectionModel().clearSelection();
		this.signalbox.getSelectionModel().clearSelection();
		this.fxParamComboBox.getSelectionModel().clearSelection();
		this.valueLabel.setText("");
	}

    @FXML
    void onClickAddSignal(ActionEvent event) {
    	String parm = this.fxParamComboBox.getSelectionModel().getSelectedItem();
    	this.setSignalBoxData(parm);
    }
    
	public void addListener(WatchNodeListener listener) {
		this.listener = listener;
	}

	public String getSignal() {
		System.out.println(this.nameBox.getSelectionModel().getSelectedIndex()+"_"+this.signalbox.getSelectionModel().getSelectedItem());
		String num = CommandConst.DRIVER_MAP.get(this.nameBox.getSelectionModel().getSelectedItem());
		if(num != null) {
			return num+"_"+this.signalbox.getSelectionModel().getSelectedItem();
		}
		
		return null;
	}

	public void reSet() {
		nameBox.getSelectionModel().clearSelection();
		signalbox.getSelectionModel().clearSelection();
		this.valueLabel.setText("");
	}


	/** 선택된 노드를 저장한다... ** */
	public void addPane() {
		if(nameBox.getSelectionModel().getSelectedItem()!=null && signalbox.getSelectionModel().getSelectedItem()!=null ) {
			this.fxDeleteButton.setVisible(true);
			listener.selectSignal(getSignal(), this);

			/** 선택된 value가 SDO값인 경우 서버에 정보를 요청한다.  */
			String value = this.signalbox.getSelectionModel().getSelectedItem();
			String getString = this.signalbox.getSelectionModel().getSelectedItem();
			
			
			/** StatusWord */
			if(getString.equals(CommandConst.ST)) {
				int index = this.nameBox.getSelectionModel().getSelectedIndex()+1;
				String num = String.valueOf(index);
				String command = CommandConst.GET_DRIVER_STATUS.replace("Driver",num);
				String status = NetworkServerManager.getDriverStaus(command);
				if(status!=null) {
					this.valueLabel.setText(status);
				}
				return;
			}
			
			for(ParamConstant.SDOVALUE env:ParamConstant.SDOVALUE.values() ) {
				if(value.equals(env.getValue())) {
					getString = env.name();
					int Driver_Num = this.nameBox.getSelectionModel().getSelectedIndex()+1;
					String DriveNo = new Integer(Driver_Num).toString();
					String output = null;
					ParamConstant.GETSDOVAULE getValue = ParamConstant.GETSDOVAULE.valueOf(getString);
					output = getValue.getValue().replace("driver", DriveNo);
					String propotionalgain = NetworkServerManager.uploadData(output);
					this.valueLabel.setText(StringUtil.removeDot(propotionalgain));
					break;
				}
			}
		}
	}

	public void setValue(String value) {
		if(!this.nameBox.getSelectionModel().isEmpty())
			this.valueLabel.setText(value);
	}
}