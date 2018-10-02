package justek.ide.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import justek.ide.model.CommandConst;
import justek.ide.model.RealTimeInfo;
import justek.ide.model.listener.MultiAxisNodeListener;
import justek.ide.model.listener.NodeRealDataUpdateListener;
import justek.ide.model.manager.NetworkServerManager;
import justek.ide.utils.StringUtil;

public class multiAxisComponent implements NodeRealDataUpdateListener {

	static final String Tag = "multiAxisComponent";

	@FXML
	private ComboBox<String> driverBox;
	@FXML
	private ComboBox<String> opModeBox;
	@FXML
	private TextField actualPosition;
	@FXML
	private TextField actualVelocity;
	@FXML
	private TextField activeCurrent;
	@FXML
	private TextField ptpRelative;

	@FXML
	private TextField ptpAbsolute;
	@FXML
	private TextField ptpAbsolute2;
	@FXML
	private CheckBox ptpAbsoluteCheck;
	@FXML
	private CheckBox ptpRelativeCheck;
	@FXML
	private TextField fxSpeedField;
	@FXML
	private RadioButton rlsButton;
	@FXML
	private RadioButton flsButton;

	@FXML
	private TextField fxRelativeField;

	@FXML
	private TextField fxAbsolute2Field;

	@FXML
	private TextField fxAbsolute1Field;

	@FXML
	private TextField fxAccField;

	@FXML
	private TextField fxDwellTimeField;

	@FXML
	private CheckBox runHeldCheck;

	//삭제를 위해서 Pane을 저장한다.
	private VBox nodePane;

	private String selectedDriver;

	private MultiAxisNodeListener mMultiAxisNodeListener;


	@FXML
	private void initialize() {
		this.setView();	
	}

	public MultiAxisNodeListener getMultiAxisNodeListener() {
		return mMultiAxisNodeListener;
	}


	public void setMultiAxisNodeListener(MultiAxisNodeListener multiAxisNodeListener) {
		this.mMultiAxisNodeListener = multiAxisNodeListener;
	}

	public void setView() {
		//TODO 초기는 임의 디폴트로 설정한다. -> 추후 삭제할것...
		this.driverBox.setItems(CommandConst.driverList);
	}


	public void onActionEnable(RealTimeInfo info) {


	}


	public VBox getNode() {
		return nodePane;
	}

	//현재 선택된 드라이버를 리턴한다.
	public String getSelectedDriver() {
		return this.selectedDriver;
	}

	public void setNode(VBox nodePane) {
		this.nodePane = nodePane;
	}


	//Node의 각 필드의 기본 정보를 셋팅한다.
	public void setData() {
		System.out.println(Tag+" :: setData");

		if(this.selectedDriver!=null && this.selectedDriver.equals(this.driverBox.getSelectionModel().getSelectedItem())) return;
		
		if(this.mMultiAxisNodeListener.checkSelectedDriver(this.selectedDriver,driverBox.getSelectionModel().getSelectedItem())) {
			System.out.println(Tag+" :: 이미 선택된 드라이버입니다.");
			
			// Update the Label on the JavaFx Application Thread
			Platform.runLater(new Runnable()
			{
				@Override
				public void run()
				{
					driverBox.getSelectionModel().select(-1);
					actualPosition.setText("");
					actualVelocity.setText("");
				}
			});
			
		}
		else {
			selectedDriver = driverBox.getSelectionModel().getSelectedItem();
			String driverNo = String.valueOf(driverBox.getSelectionModel().getSelectedIndex()+1);
			
			if(driverNo.equals("0")) {
				this.fxSpeedField.setText("");
				this.fxAccField.setText("");
				return;
			}
			
			//서버 Ip의 연결을 확인하여 인터넷에 연결되어 있지 않으면 통신을 하지 않도록 처리한다.
			if(!NetworkServerManager.checkNetworkConnection()) return;
			
			String speed = NetworkServerManager.getInitialSpeed(driverNo);
			if(speed!=null) {
				System.out.println("multiAxisComponent ::: speed =="+speed);
				this.fxSpeedField.setText(StringUtil.getNumnberFommat(speed));
			}

			String Acc = NetworkServerManager.getInitialAcceleration(driverNo);
			if(Acc!=null) {
				System.out.println("multiAxisComponent ::: Acc =="+Acc);
				this.fxAccField.setText(StringUtil.getNumnberFommat(Acc));
			}
		}
	}

	/*
	 * 현재 AxisNode를 화면에서 제거한다.
	 * */
	@FXML
	public void onClickRemove() {
		System.out.println("multiAxisComponent ::: onClickRmove");

		if(this.mMultiAxisNodeListener!=null) {
			this.mMultiAxisNodeListener.removeAixsNode(this.nodePane);
		}
	}

	@FXML
	void onClickEnable(ActionEvent event) {
		if(driverBox.getSelectionModel().getSelectedIndex()==-1) return;
		String driverNo = String.valueOf(driverBox.getSelectionModel().getSelectedIndex()+1);
		NetworkServerManager.serverOn(driverNo);
	}

	@FXML
	void onClickDisable(ActionEvent event) {
		if(driverBox.getSelectionModel().getSelectedIndex()==-1) return;
		String driverNo = String.valueOf(driverBox.getSelectionModel().getSelectedIndex()+1);
		NetworkServerManager.serverOff(driverNo);
	}

	@FXML
	void onClickHome(ActionEvent event) {
		if(driverBox.getSelectionModel().getSelectedIndex()==-1) return;
		
		String driverNo = String.valueOf(driverBox.getSelectionModel().getSelectedIndex()+1);
		String speed = StringUtil.removeCommna(this.fxSpeedField.getText());
		NetworkServerManager.handleAbsoluteRight(driverNo, "0",speed);
	}

	@FXML
	void onClickStop(ActionEvent event) {
		if(driverBox.getSelectionModel().getSelectedIndex()==-1) return;
		
		String driverNo = String.valueOf(driverBox.getSelectionModel().getSelectedIndex()+1);
		NetworkServerManager.stop(driverNo);
	}

	@FXML
	void handleJogLeft(ActionEvent event) {
		System.out.println(Tag+" :: handleJogLeft");
		if(driverBox.getSelectionModel().getSelectedIndex()==-1) return;
		
		String driverNo = String.valueOf(driverBox.getSelectionModel().getSelectedIndex()+1);
		String speed = StringUtil.removeCommna(this.fxSpeedField.getText());
		NetworkServerManager.handleJoggingLeft(driverNo,speed);
	}

	@FXML
	void handleJogRight(ActionEvent event) {
		System.out.println(Tag+" :: handleJogRight");
		if(driverBox.getSelectionModel().getSelectedIndex()==-1) return;
		
		String driverNo = String.valueOf(driverBox.getSelectionModel().getSelectedIndex()+1);
		String speed = StringUtil.removeCommna(this.fxSpeedField.getText());
		NetworkServerManager.handleJoggingRight(driverNo,speed);
	}

	@FXML
	void handleRelativeLeft(ActionEvent event) {
		System.out.println(Tag+" :: handleRelativeLeft");
		if(driverBox.getSelectionModel().getSelectedIndex()==-1) return;
		
		String driverNo = String.valueOf(driverBox.getSelectionModel().getSelectedIndex()+1);
		String ptp = StringUtil.removeCommna(this.fxRelativeField.getText());
		String speed = StringUtil.removeCommna(this.fxSpeedField.getText());
		if(ptp!=null && !ptp.isEmpty())
			NetworkServerManager.handleRelativeLeft(driverNo, ptp,speed);
	}

	@FXML
	void handleRelativeRight(ActionEvent event) {
		System.out.println(Tag+" :: handleRelativeRight");
		if(driverBox.getSelectionModel().getSelectedIndex()==-1) return;
		
		String driverNo = String.valueOf(driverBox.getSelectionModel().getSelectedIndex()+1);
		String ptp = StringUtil.removeCommna(this.fxRelativeField.getText());
		String speed = StringUtil.removeCommna(this.fxSpeedField.getText());
		if(ptp!=null && !ptp.isEmpty())
			NetworkServerManager.handleRelativeRight(driverNo, ptp,speed);
	}


	@FXML
	void handleAbsolute1(ActionEvent event) {
		System.out.println(Tag+" :: handleAbsolute1");
		if(driverBox.getSelectionModel().getSelectedIndex()==-1) return;
		
		String driverNo = String.valueOf(driverBox.getSelectionModel().getSelectedIndex()+1);
		String ptp = StringUtil.removeCommna(this.fxAbsolute1Field.getText());
		String speed = StringUtil.removeCommna(this.fxSpeedField.getText());
		if(ptp!=null && !ptp.isEmpty())
			NetworkServerManager.handleAbsoluteRight(driverNo, ptp,speed);
	}

	@FXML
	void handleAbsolute2(ActionEvent event) {
		System.out.println(Tag+" :: handleAbsolute2");
		if(driverBox.getSelectionModel().getSelectedIndex()==-1) return;
		
		String driverNo = String.valueOf(driverBox.getSelectionModel().getSelectedIndex()+1);
		String ptp = StringUtil.removeCommna(this.fxAbsolute2Field.getText());
		String speed = StringUtil.removeCommna(this.fxSpeedField.getText());
		if(ptp!=null && !ptp.isEmpty())
			NetworkServerManager.handleAbsoluteRight(driverNo, ptp,speed);
	}

	@Override
	public void updataData(RealTimeInfo info) {
		// TODO Auto-generated method stub

		if(this.selectedDriver==null) return;

		if(this.driverBox.getSelectionModel().getSelectedIndex()+1==Integer.valueOf(info.DriverNo)) {
			//			if(this.driverBox.getSelectionModel().getSelectedIndex()+1==2) {
			this.actualVelocity.setText(info.ActualVelocity);
			//			}

			this.actualPosition.setText(info.ActualPosition);
			//			System.out.println(Tag+"=="+info.ActualPosition);

		}

	}	

	public void handleMotionSpeed() {
		int Driver_Num = this.driverBox.getSelectionModel().getSelectedIndex()+1;
		String DriveNo = new Integer(Driver_Num).toString();		
		if ((fxSpeedField.getText() != null && !fxSpeedField.getText().isEmpty())) {
			fxSpeedField.setText(StringUtil.getNumnberFommat(fxSpeedField.getText()));
			NetworkServerManager.setSpeed(DriveNo, StringUtil.removeCommna(fxSpeedField.getText()));
		}
	}

	public void handleJogAcceleration() {
		int Driver_Num = this.driverBox.getSelectionModel().getSelectedIndex()+1;
		String DriveNo = new Integer(Driver_Num).toString();
		if ((fxAccField.getText() != null && !fxAccField.getText().isEmpty())) {
			fxAccField.setText(StringUtil.getNumnberFommat(fxAccField.getText()));
			NetworkServerManager.setJogAcceleration(DriveNo, StringUtil.removeCommna(fxAccField.getText()));
		}
	}
	

	public void handle(KeyEvent event) {
		if(event.getCode()==KeyCode.ENTER) {
			if(event.getSource().equals(fxSpeedField)) {
				this.handleMotionSpeed();
			}
			else if(event.getSource().equals(this.fxAccField)) {
				this.handleJogAcceleration();
			}
		}
	}

}