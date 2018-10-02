package justek.ide.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TreeItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import justek.ide.MainApp;
import justek.ide.model.CommandConst;
import justek.ide.model.RealTimeInfo;
import justek.ide.model.listener.RealTimeEventListener;
import justek.ide.model.listener.TreeEventListener;
import justek.ide.model.manager.DialogManager;
import justek.ide.model.manager.NetworkServerManager;
import justek.ide.model.manager.RequestManager;
import justek.ide.utils.StringUtil;

public class MotionSingleAxisController implements TreeEventListener,RealTimeEventListener{

	static final String Tag = "MotionSingleAxisController";

	ObservableList<String> driveModeComboList = FXCollections
			.observableArrayList("Position (UM = 5)","Velocity (UM = 2)","Current (UM = 1)","Stepper (UM = 3)");

	ObservableList<String> homingComboList = FXCollections
			.observableArrayList("Home on block positive direction + index(-4)","Home on block positive direction + index(-3)",
					"Homing block positive direction(-2)","Homing block positive direction(-1)","RLS falling edge + index(1)",
					"RLS falling edge + index(2)","Home immediate on current position(35)");


	@FXML
	private ComboBox<String> driveModeCombo;
	@FXML
	private ComboBox<String> homingCombo;
	@FXML
	private CheckBox fxRepAbsoluteCheckBox;
	@FXML
	private CheckBox fxRepRelativeCheckBox;
	@FXML
	private CheckBox runHeldCheckBox;
	@FXML
	private TextField PTPAbsolute;
	@FXML
	private TextField PTPAbsolute2;
	@FXML
	private TextField PTPRelative;
	@FXML
	private TextField motionSpeed;
	@FXML
	private Label fxSpeedLabel;
	@FXML
	private Label fxAccLabel;
	@FXML
	private Label fxDwellLabel;
	@FXML
	private Label driverNoField;
	@FXML
	private TextField sMotionAcc;
	@FXML
	private TextField sMotionPosition;
	@FXML
	private TextField sMotionPosErr;
	@FXML
	private TextField sMotionVelocity;
	@FXML
	private CategoryAxis sMotionXAxis;
	@FXML
	private NumberAxis sMotionYAxis;	
	@FXML
	private CategoryAxis sMotionXAxis2;
	@FXML
	private NumberAxis sMotionYAxis2;	
	@FXML
	private LineChart<String,Number> sMotionLineChart;	
	@FXML
	private LineChart<String, Number> sMotionLineChart2;	
	@FXML
	private ComboBox<String> fxComboIn_1;
	@FXML
	private ComboBox<String> fxComboIn_2;
	@FXML
	private ComboBox<String> fxComboIn_3;
	@FXML
	private ComboBox<String> fxComboIn_4;
	@FXML
	private ComboBox<String> fxComboIn_5;
	@FXML
	private ComboBox<String> fxComboIn_6;
	@FXML
	private ComboBox<String> fxComboOut_1;
	@FXML
	private ComboBox<String> fxComboOut_2;
	@FXML
	private ComboBox<String> fxComboOut_3;
	@FXML
	private ComboBox<String> fxComboOut_4;
	@FXML
	private Button fxJogLeftButton;
	@FXML
	private Button fxJogRightButton;
	

	public int selectDriveNo = 1;
	public String numberDriveNo = null;

	//Jog RunHeld ture/false
	private boolean isRunHeld = false;

	private MainApp mainApp;

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		this.mainApp.listener =  this;

	} 
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		driveModeCombo.setValue("Position (UM = 5)");
		driveModeCombo.setItems(driveModeComboList);
		homingCombo.setValue("Home on block positive direction + index(-4)");
		homingCombo.setItems(homingComboList);

		fxComboIn_1.setItems(CommandConst.SINGLE_AXIS_FUNTION_LIST);
		fxComboIn_2.setItems(CommandConst.SINGLE_AXIS_FUNTION_LIST);
		fxComboIn_3.setItems(CommandConst.SINGLE_AXIS_FUNTION_LIST);
		fxComboIn_4.setItems(CommandConst.SINGLE_AXIS_FUNTION_LIST);
		fxComboIn_5.setItems(CommandConst.SINGLE_AXIS_FUNTION_LIST);
		fxComboIn_6.setItems(CommandConst.SINGLE_AXIS_FUNTION_LIST);
		fxComboOut_1.setItems(CommandConst.SINGLE_AXIS_FUNTION_OUT_LIST);
		fxComboOut_2.setItems(CommandConst.SINGLE_AXIS_FUNTION_OUT_LIST);
		fxComboOut_3.setItems(CommandConst.SINGLE_AXIS_FUNTION_OUT_LIST);
		fxComboOut_4.setItems(CommandConst.SINGLE_AXIS_FUNTION_OUT_LIST);

		this.selectDriveNo = CommandConst.DRIVER_NUMBEER;
		numberDriveNo = new Integer(selectDriveNo).toString();

		showInitialSpeed(); 
		RequestManager.getInstance().setRealTimeEventListener(this);
	}


	//ChartView Show
	@FXML
	void onClickChart(ActionEvent event) {
		DialogManager.getInstance().openChartView();

	}

	//Initialize Speed / Acceleration value
	public void showInitialSpeed() {
		//서버 Ip의 연결을 확인하여 인터넷에 연결되어 있지 않으면 통신을 하지 않도록 처리한다.
		if(!NetworkServerManager.checkNetworkConnection()) return;

		String speed = NetworkServerManager.getInitialSpeed(this.numberDriveNo);
		if(speed!=null)
			this.motionSpeed.setText(StringUtil.getNumnberFommat(speed));

		String acc = NetworkServerManager.getInitialAcceleration(this.numberDriveNo);
		if(acc!=null)
			this.sMotionAcc.setText(StringUtil.getNumnberFommat(acc));
	}

	public void handleDriveDisable() {
		NetworkServerManager.serverOff(this.numberDriveNo);
	}

	public void handleDriveEnable() {
		NetworkServerManager.serverOn(this.numberDriveNo);
	}

	public void handleDriveStop() {
		NetworkServerManager.stop(this.numberDriveNo);
	}

	@FXML
	void onClieckToggleRightJog(ActionEvent event){
		ToggleButton btnSelected = (ToggleButton) event.getSource();
		if(btnSelected.isSelected()) {
			System.out.println(Tag+" :: onClieckToggleRightJog");
			handleJogPlus();
		}
		else{
			this.handleDriveStop();
		}
	}

	@FXML
	void onClieckToggleLeftJog(ActionEvent event){
		ToggleButton btnSelected = (ToggleButton) event.getSource();
		if(btnSelected.isSelected()) {
			System.out.println(Tag+" :: onClieckToggleLeftJog");
			this.handleJogMinus();
		}
		else{
			this.handleDriveStop();
		}
	}


	@FXML
	void onClickStop(ActionEvent event) {
		NetworkServerManager.stop(this.numberDriveNo);
	}

	@FXML
	void handleRelativeLeft() {
//		this.PTPRelative.setText(StringUtil.addComma(this.PTPRelative.getText()));
		String ptp = StringUtil.removeCommna(this.PTPRelative.getText());
		String speed = StringUtil.removeCommna(this.motionSpeed.getText());
		if(ptp!=null && !ptp.isEmpty()) {
			if(this.fxRepRelativeCheckBox.isSelected()) {
				NetworkServerManager.handleRelativeRepetitive(numberDriveNo, ptp,"-");
			}
			else {
				NetworkServerManager.handleRelativeLeft(numberDriveNo, ptp,speed);
			}
		}
	}

	@FXML
	void handleRelativeRight() {
//		this.PTPRelative.setText(StringUtil.addComma(this.PTPRelative.getText()));
		String ptp = StringUtil.removeCommna(this.PTPRelative.getText());
		String speed = StringUtil.removeCommna(this.motionSpeed.getText());
		if(ptp!=null && !ptp.isEmpty()) {
			if(this.fxRepRelativeCheckBox.isSelected()) {
				NetworkServerManager.handleRelativeRepetitive(numberDriveNo, ptp,"+");
			}
			else {
				NetworkServerManager.handleRelativeRight(numberDriveNo, ptp,speed);
			}
		}
	}

	public void handleAbsolute1() {
//		this.PTPAbsolute.setText(StringUtil.addComma(this.PTPAbsolute.getText()));
//		this.PTPAbsolute2.setText(StringUtil.addComma(this.PTPAbsolute2.getText()));
		String ptp = StringUtil.removeCommna(this.PTPAbsolute.getText());
		String ptp1 = StringUtil.removeCommna(this.PTPAbsolute2.getText());
		String speed = StringUtil.removeCommna(this.motionSpeed.getText());
		if(ptp!=null && !ptp.isEmpty() && ptp1!=null && !ptp1.isEmpty()) {
			if(this.fxRepAbsoluteCheckBox.isSelected()) {
				NetworkServerManager.handleAbsoluteRepetitive(numberDriveNo, ptp,ptp1,speed);
			}
			else {
				NetworkServerManager.handleAbsoluteRight(this.numberDriveNo, ptp,speed);
			}
		}
	}

	public void handleAbsolute2() {
//		this.PTPAbsolute2.setText(StringUtil.addComma(this.PTPAbsolute2.getText()));
//		this.PTPAbsolute.setText(StringUtil.addComma(this.PTPAbsolute.getText()));
		String ptp = StringUtil.removeCommna(this.PTPAbsolute2.getText());
		String ptp1 = StringUtil.removeCommna(this.PTPAbsolute.getText());
		String speed = StringUtil.removeCommna(this.motionSpeed.getText());
		if(ptp!=null && !ptp.isEmpty() && ptp1!=null && !ptp1.isEmpty()) {
			if(this.fxRepAbsoluteCheckBox.isSelected()) {
				NetworkServerManager.handleAbsoluteRepetitive(numberDriveNo, ptp,ptp1,speed);
			}
			else {
				NetworkServerManager.handleAbsoluteRight(this.numberDriveNo, ptp,speed);
			}
		}
	}

	public void handleMotionSpeed() {
		NetworkServerManager.setSpeed(this.numberDriveNo, StringUtil.removeCommna(this.motionSpeed.getText()));
		this.motionSpeed.setText(StringUtil.getNumnberFommat(motionSpeed.getText()));

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String speed = NetworkServerManager.getInitialSpeed(this.numberDriveNo);
		if(speed!=null)
			this.fxSpeedLabel.setText(StringUtil.getNumnberFommat(speed));

	}

	public void handleJogAcceleration() {
		if ((this.sMotionAcc.getText() != null && !sMotionAcc.getText().isEmpty())) {
			NetworkServerManager.setJogAcceleration(this.numberDriveNo, StringUtil.removeCommna(sMotionAcc.getText()));
			sMotionAcc.setText(StringUtil.getNumnberFommat(sMotionAcc.getText()));
		}

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String acc = NetworkServerManager.getInitialAcceleration(this.numberDriveNo);
		if(acc!=null)
			this.fxAccLabel.setText(StringUtil.getNumnberFommat(acc));
	}

	public void handleJogPlus() {
		if(this.runHeldCheckBox.isSelected()) {
			if(this.isRunHeld) {
				NetworkServerManager.stop(this.numberDriveNo);
				this.fxJogRightButton.setStyle("-fx-background-color:\r\n" + 
						"#000000,linear-gradient(#7ebcea, #2f4b8f), "
						+ "linear-gradient(#426ab7, #263e75), "
						+ "linear-gradient(#395cab, #223768);");
				this.isRunHeld = false;
			}
			else {
				String speed = StringUtil.removeCommna(this.motionSpeed.getText());
				if(speed!=null)  NetworkServerManager.handleJoggingRight(numberDriveNo, speed);
				this.fxJogRightButton.setStyle("-fx-background-color: red;");
				this.isRunHeld = true;
			}
		}
		else {
			System.out.println("handleJogPlus isSelected NO");
			String speed = StringUtil.removeCommna(this.motionSpeed.getText());
			if(speed!=null)  NetworkServerManager.handleJoggingRight(numberDriveNo, speed);
		}
	}

	public void handleJogMinus() {
		if(this.runHeldCheckBox.isSelected()) {
			if(this.isRunHeld) {
				NetworkServerManager.stop(this.numberDriveNo);
				this.fxJogLeftButton.setStyle("-fx-background-color:\r\n" + 
						"#000000,linear-gradient(#7ebcea, #2f4b8f), "
						+ "linear-gradient(#426ab7, #263e75), "
						+ "linear-gradient(#395cab, #223768);");
	
				this.isRunHeld = false;
			}
			else {
				String speed = StringUtil.removeCommna(this.motionSpeed.getText());
				if(speed!=null)  NetworkServerManager.handleJoggingLeft(numberDriveNo, speed);
				this.fxJogLeftButton.setStyle("-fx-background-color: red;");
				this.isRunHeld = true;
			}
		}
		else {
			System.out.println("handleJogMinus isSelected NO");
			String speed = StringUtil.removeCommna(this.motionSpeed.getText());
			if(speed!=null)  {
				NetworkServerManager.handleJoggingLeft(numberDriveNo, speed);
			}
		}
	}

	@FXML
	void handleJogStop() {
		if(this.runHeldCheckBox.isSelected()) return;
		System.out.println("handleJogStop");
		NetworkServerManager.stop(numberDriveNo);
	}

	//Driver Reset
	public void handleDriveReset() {
		NetworkServerManager.resetDriver();
	}

	//	@SuppressWarnings("unused")
	//	private void addChartPane() {
	//		try {
	//			FXMLLoader loader = new FXMLLoader();
	//			loader.setLocation(MainApp.class.getResource("view/ChartView.fxml"));
	//			AnchorPane lChartPane = (AnchorPane) loader.load();
	//
	//			AnchorPane.setTopAnchor(lChartPane,0.0); // obviously provide your own constraints
	//			AnchorPane.setBottomAnchor(lChartPane,0.0);
	//			AnchorPane.setRightAnchor(lChartPane,0.0);
	//			AnchorPane.setLeftAnchor(lChartPane,0.0);
	//
	//			this.chartPane.getChildren().add(lChartPane);
	//
	//		} catch (IOException e) {
	//			e.printStackTrace();
	//		}
	//	}

	@Override
	public void clieckMenu() {
		this.selectDriveNo = CommandConst.DRIVER_NUMBEER;
		numberDriveNo = new Integer(selectDriveNo).toString();
		System.out.println(Tag+":: "+numberDriveNo);
		this.showInitialSpeed();
	}

	@Override
	public void realTimeDatarEvent(double Source) {
		// TODO Auto-generated method stub
	}

	@Override
	public void realTimeDataInfoEvent(RealTimeInfo Source) {
		// TODO Auto-generated method stub
		if(Source!=null) {
			//			System.out.println(Tag + " :: " + Source.toString());
			if(this.numberDriveNo.equals(Source.DriverNo)) {
				this.sMotionPosition.setText(StringUtil.getNumnberFommat(Source.ActualPosition));
				this.sMotionPosErr.setText(StringUtil.getNumnberFommat(Source.PosionError));
				this.sMotionVelocity.setText(StringUtil.getNumnberFommat(Source.ActualVelocity));
			}
		}
	}

	@Override
	public void autoScanEvent(ObservableList<TreeItem<String>> nodes) {
		// TODO Auto-generated method stub
	}
}
