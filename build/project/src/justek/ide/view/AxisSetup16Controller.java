package justek.ide.view;

import java.io.*;
import java.net.*;
import java.text.NumberFormat;
import java.util.*;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartViewer;

import com.sun.javafx.scene.control.skin.Utils;

import justek.ide.chart.data.*;
import justek.ide.chart.view.GraphPanel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import justek.ide.MainApp;
import justek.ide.model.CommandConst;
import justek.ide.model.RealTimeInfo;
import justek.ide.model.listener.RealTimeEventListener;
import justek.ide.model.listener.TreeEventListener;
import justek.ide.model.manager.NetworkServerManager;
import justek.ide.model.manager.RequestManager;
import justek.ide.model.manager.TreeViewManager;
import justek.ide.utils.StringUtil;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class AxisSetup16Controller implements EventHandler<KeyEvent>,TreeEventListener,RealTimeEventListener {

	static final String Tag = "AxisSetup16Controller";

	ObservableList<String> velFilter1ComboList = FXCollections.observableArrayList("Disable", "Low-Pass", "Lead-Lag",
			"Double Lead-Lag", "Notch", "Anti-Notch", "General Biquad");
	ObservableList<String> velFilter2ComboList = FXCollections.observableArrayList("Disable", "Low-Pass", "Lead-Lag",
			"Double Lead-Lag", "Notch", "Anti-Notch", "General Biquad");
	ObservableList<String> velFilter3ComboList = FXCollections.observableArrayList("Disable", "Low-Pass", "Lead-Lag",
			"Double Lead-Lag", "Notch", "Anti-Notch", "General Biquad");
	ObservableList<String> velFilter4ComboList = FXCollections.observableArrayList("Disable", "Low-Pass", "Lead-Lag",
			"Double Lead-Lag", "Notch", "Anti-Notch", "General Biquad");
	ObservableList<String> velFilter5ComboList = FXCollections.observableArrayList("Disable", "Low-Pass", "Lead-Lag",
			"Double Lead-Lag", "Notch", "Anti-Notch", "General Biquad");
	ObservableList<String> velFilter6ComboList = FXCollections.observableArrayList("Disable", "Low-Pass", "Lead-Lag",
			"Double Lead-Lag", "Notch", "Anti-Notch", "General Biquad");
	ObservableList<String> positionFilter1ComboList = FXCollections.observableArrayList("Disable", "Low-Pass",
			"Lead-Lag", "Double Lead-Lag", "Notch", "Anti-Notch", "General Biquad");
	ObservableList<String> positionFilter2ComboList = FXCollections.observableArrayList("Disable", "Low-Pass",
			"Lead-Lag", "Double Lead-Lag", "Notch", "Anti-Notch", "General Biquad");
	ObservableList<String> schedulingFilter1ComboList = FXCollections.observableArrayList("Disable", "Low-Pass",
			"Lead-Lag", "Double Lead-Lag", "Notch", "Anti-Notch", "General Biquad");
	ObservableList<String> schedulingFilter2ComboList = FXCollections.observableArrayList("Disable", "Low-Pass",
			"Lead-Lag", "Double Lead-Lag", "Notch", "Anti-Notch", "General Biquad");
	ObservableList<String> schedulingFilter3ComboList = FXCollections.observableArrayList("Disable", "Low-Pass",
			"Lead-Lag", "Double Lead-Lag", "Notch", "Anti-Notch", "General Biquad");

	@FXML
	private Label stLabel;

	@FXML
	private ComboBox<String>  velFilter1Combo;
	@FXML
	private ComboBox<String>  velFilter2Combo;
	@FXML
	private ComboBox<String>  velFilter3Combo;
	@FXML
	private ComboBox<String>  velFilter4Combo;
	@FXML
	private ComboBox<String>  velFilter5Combo;
	@FXML
	private ComboBox<String>  velFilter6Combo;
	@FXML
	private ComboBox<String>  positionFilter1Combo;
	@FXML
	private ComboBox<String>  positionFilter2Combo;
	@FXML
	private ComboBox<String>  schedulingFilter1Combo;
	@FXML
	private ComboBox<String>  schedulingFilter2Combo;
	@FXML
	private ComboBox<String> schedulingFilter3Combo;

	@FXML
	private TextField ptpAbsolute1;
	@FXML
	private TextField ptpAbsolute2;
	@FXML
	private TextField fxDec;
	@FXML
	private TextField fxAmp;
	@FXML
	private TextField ptpRelative;
	@FXML
	private TextField fxSpeed;
	@FXML
	private TextField fxStopDec;
	@FXML
	private TextField fxSmooth;
	@FXML
	private TextField fxAcc;
	@FXML
	private TextField fxInjectionPoint;


	//RealTime Field
	@FXML
	private TextField fxPosition;
	@FXML
	private TextField fxVelocity;
	@FXML
	private TextField fxActualPosition;
	@FXML
	private TextField fxCurrent;


	@FXML
	private TextField fxDwell;
	@FXML
	private TextField fxFrequency;

	// PosionLoop Info Field
	@FXML
	private TextField propotionalGain;
	@FXML
	private TextField velocityFFGain;
	@FXML
	private TextField accelerationFFGain;
	@FXML
	private TextField gainDivide;
	@FXML
	private TextField scale;
	@FXML
	private TextField fbDerivativeGain;
	@FXML
	private TextField integralGain;

	@FXML
	private Label proportioalLabel;
	@FXML
	private Label velocityLabel;
	@FXML
	private Label gainDivideLabel;
	@FXML
	private Label scaleLabel;
	@FXML
	private Label accelerationFFGainLabel;
	@FXML
	private Label fbDerivativeGainLabel;
	@FXML
	private Label integralGainLabel;

	@FXML
	private Button btnStop;
	@FXML
	private Button btnStart;

	@FXML
	private Label driverLabel;

	@FXML
	private TextField tfTimeInterval;

	@FXML
	private TreeView<String> treeView;

	@FXML
	private Circle warningCirclle;
	@FXML
	private Circle statusCircle;
	@FXML
	private Circle voltageCircle;

	@FXML
	private Tab chartTab;
	@FXML
	private TabPane  tabPane;
	@FXML
	private Tab positionTab;
	@FXML
	private Tab velocityTab;
	@FXML
	private BorderPane borderPane;

	@FXML
	private Slider sliderY1;
	@FXML
	private Slider sliderY2;
	@FXML
	private Slider sliderX1;
	@FXML
	private Slider sliderX2;

	@FXML
	private TextField minYField;
	@FXML
	private TextField maxYField;
	@FXML
	private TextField minXField;
	@FXML
	private TextField maxXField;
	@FXML
	private Button zoomButton;

	@FXML
	private TextField LeftY1Field;
	@FXML
	private TextField LeftY2Field;
	@FXML
	private TextField RightY1Field;
	@FXML
	private TextField RightY2Field;
	@FXML
	private TextField X1Field;
	@FXML
	private TextField X2Field;
	@FXML
	private TextField chartNameField;

	@FXML
	private TextField YRangeField;
	@FXML
	private TextField Y2RangeField;
	@FXML
	private TextField XRangeField;

	@FXML
	private ComboBox<String> chartNameComboBox;
	@FXML
	private ComboBox<String> zoomComboBox;
	@FXML
	private ComboBox<String> seriesComboBox;
	@FXML
	private ComboBox<String> series2ComboBox;

	public int selectDriveNo = 1;
	public String numberDriveNo = new Integer(selectDriveNo).toString();

	int speedLength;
	byte[] speedData = null;

	private ArrayList<String> resultList;
	private RealTimeInfo info;

	//TreeView List
	private ObservableList<TreeItem<String>> nodeList;

	private Thread thread;

	private int unit = 1;

	Image icon = new Image (
			getClass().getResourceAsStream("/img/folder-icon.png"));

	// Reference to the main application
	private MainApp mainApp;

	private TreeViewManager treeViewManager;

	//chart
	private Process chartProcess;
	private GraphPanel panel;
	private ChartViewer viewer;
	private ObservableList<String> list;

	ChartViewController chartController;
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
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */

	@FXML
	private void initialize() {

		velFilter1Combo.setValue("Disable");
		velFilter1Combo.setItems(velFilter1ComboList);
		velFilter2Combo.setValue("Disable");
		velFilter2Combo.setItems(velFilter2ComboList);
		velFilter3Combo.setValue("Disable");
		velFilter3Combo.setItems(velFilter3ComboList);
		velFilter4Combo.setValue("Disable");
		velFilter4Combo.setItems(velFilter4ComboList);
		velFilter5Combo.setValue("Disable");
		velFilter5Combo.setItems(velFilter5ComboList);
		velFilter6Combo.setValue("Disable");
		velFilter6Combo.setItems(velFilter6ComboList);
		positionFilter1Combo.setValue("Disable");
		positionFilter1Combo.setItems(positionFilter1ComboList);
		positionFilter2Combo.setValue("Disable");
		positionFilter2Combo.setItems(positionFilter2ComboList);
		schedulingFilter1Combo.setValue("Disable");
		schedulingFilter1Combo.setItems(schedulingFilter1ComboList);
		schedulingFilter2Combo.setValue("Disable");
		schedulingFilter2Combo.setItems(schedulingFilter2ComboList);
		schedulingFilter3Combo.setValue("Disable");
		schedulingFilter3Combo.setItems(schedulingFilter3ComboList);

		this.propotionalGain.setOnKeyPressed(this);
		this.integralGain.setOnKeyPressed(this);
		this.fbDerivativeGain.setOnKeyPressed(this);
		this.scale.setOnKeyPressed(this);
		this.velocityFFGain.setOnKeyPressed(this);
		this.accelerationFFGain.setOnKeyPressed(this);
		this.gainDivide.setOnKeyPressed(this);
		this.fxAcc.setOnKeyPressed(this);
		this.fxSpeed.setOnKeyPressed(this);
		this.tfTimeInterval.setOnKeyPressed(this);
		this.tfTimeInterval.setText(String.valueOf(CommandConst.INTERVAL_TIME));

		this.selectDriveNo = CommandConst.DRIVER_NUMBEER;
		numberDriveNo = new Integer(selectDriveNo).toString();

		this.warningCirclle.setFill(Color.GRAY);
		this.voltageCircle.setFill(Color.GRAY);
		this.statusCircle.setFill(Color.GRAY);

		if(NetworkServerManager.checkNetworkConnection()) {
			this.getDriverPositionInfo(this.numberDriveNo);
			this.showInitialSpeed();
		}
		
		this.showPopupMenu();

		/** -> 2018.06.12 그래프화면을 공통으로 이용하도록 수정하여 사용하지 않음.
		this.sliderListener();
		this.seriesComboBox.setItems(CommandConst.chartList);
		this.seriesComboBox.setValue(CommandConst.chartList.get(0));
		this.series2ComboBox.setItems(CommandConst.chartList);
		this.series2ComboBox.setValue(CommandConst.chartList.get(1));
		this.panel = new GraphPanel();
		JFreeChart chart = panel.defaultCreateChart();
		viewer = new ChartViewer(chart);
		this.borderPane.setCenter(viewer);
		 */
		RequestManager.getInstance().setRealTimeEventListener(this);
		this.setChartTab();
	}

	public void setChartTab() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/ChartView.fxml"));
		try {
			AnchorPane chartPane = (AnchorPane) loader.load();
			this.chartController = loader.getController();
			AnchorPane.setTopAnchor(chartPane,0.0); // obviously provide your own constraints
			AnchorPane.setBottomAnchor(chartPane,0.0);
			AnchorPane.setRightAnchor(chartPane,0.0);
			AnchorPane.setLeftAnchor(chartPane,0.0);
			Tab tab = new Tab();
			tab.setContent(chartPane);
			tab.setText("Recording Chart");
			this.tabPane.getTabs().add(1, tab);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** 2018.06.12부터 사용하지 않음.. */
	//chart  Default Value Setting
	public void setChartInfo() {
		this.minXField.setText(panel.getMinDomain());
		this.maxXField.setText(panel.getMaxDomain());
		this.minYField.setText(panel.getMinValue());
		this.maxYField.setText(panel.getMaxValue());

		list =  FXCollections.observableArrayList(this.panel.getRangeList());
		this.zoomComboBox.setItems(list);
		this.zoomComboBox.setValue(list.get(0));

		this.chartNameComboBox.setItems(this.list);
		this.chartNameComboBox.setValue(list.get(0));

		this.LeftY1Field.setText("0");
		this.LeftY2Field.setText("0");
		this.RightY1Field.setText("0");
		this.RightY2Field.setText("0");
		this.X1Field.setText("0");
		this.X2Field.setText("0");

		this.YRangeField.setText("0");
		this.Y2RangeField.setText("0");
		this.XRangeField.setText("0");
	}

	/** 2018.06.12부터 사용하지 않음.. */
	//chart Slider addListener
	public void sliderListener() {
		// Listen for Slider value changes
		sliderX1.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				panel.setSliderData(sliderX1,1);
				panel.setgetFocusX(1);
				panel.setlostFocusX(2);
				panel.setlostFocusY(1);
				panel.setlostFocusY(2);

				NumberFormat f = NumberFormat.getInstance();
				f.setMaximumFractionDigits(5) ;
				String value =f.format(panel.getDomainValue(1));
				X1Field.setText(value);

				XRangeField.setText(calculateReferenceX());
			}
		});
		sliderX2.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				panel.setSliderData(sliderX2,2);
				panel.setlostFocusX(1);
				panel.setgetFocusX(2);
				panel.setlostFocusY(1);
				panel.setlostFocusY(2);

				NumberFormat f = NumberFormat.getInstance();
				f.setMaximumFractionDigits(5) ;
				String value =f.format(panel.getDomainValue(2));
				X2Field.setText(value);
				XRangeField.setText(calculateReferenceX());
			}
		});
		sliderY1.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				panel.setSliderData(sliderY1,3);
				panel.setlostFocusX(1);
				panel.setlostFocusX(2);
				panel.setgetFocusY(1);
				panel.setlostFocusY(2);

				NumberFormat f = NumberFormat.getInstance();
				f.setMaximumFractionDigits(5) ;
				String value =f.format(panel.getRangeValue(1));
				LeftY1Field.setText(value);
				String value2 =f.format(panel.getRangeValue2(1));
				RightY1Field.setText(value2);
				YRangeField.setText(calculateReferenceY());
				Y2RangeField.setText(calculateReferenceY2());
			}
		});
		sliderY2.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				panel.setSliderData(sliderY2, 4);
				panel.setlostFocusX(1);
				panel.setlostFocusX(2);
				panel.setlostFocusY(1);
				panel.setgetFocusY(2);

				NumberFormat f = NumberFormat.getInstance();
				f.setMaximumFractionDigits(5) ;
				String value =f.format(panel.getRangeValue(2));
				LeftY2Field.setText(value);
				String value2 =f.format(panel.getRangeValue2(2));
				RightY2Field.setText(value2);
				YRangeField.setText(calculateReferenceY());
				Y2RangeField.setText(calculateReferenceY2());
			}
		});
	}

	public void showInitialSpeed() {
		//서버 Ip의 연결을 확인하여 인터넷에 연결되어 있지 않으면 통신을 하지 않도록 처리한다.
		if(!NetworkServerManager.checkNetworkConnection()) return;
		
		String speed = NetworkServerManager.getInitialSpeed(this.numberDriveNo);
		if(speed!=null)
			this.fxSpeed.setText(StringUtil.getNumnberFommat(speed));
		
		String acc = NetworkServerManager.getInitialAcceleration(this.numberDriveNo);
		if(acc!=null)
			this.fxAcc.setText(StringUtil.getNumnberFommat(acc));
	}


	public void showTuningParameter() {
		try {
			Socket socketClient = new Socket(CommandConst.address, 12345);
			System.out.println("Client: " + "Connection Established");

			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

			String serverMsg;
			String sendSpeedGet = "i" + numberDriveNo + CommandConst.SPEED+"\r\n";
			String sendTuningParameterGet = "i" + numberDriveNo + "??\r\n";
			writer.write(sendSpeedGet);
			System.out.println(sendSpeedGet);
			writer.flush();
			serverMsg = reader.readLine();
			int dot = serverMsg.indexOf('.');
			String serverMsg2 = serverMsg.substring(0, dot);
			System.out.println("Client: " + serverMsg2);
			fxSpeed.setText(serverMsg2);
			// Tuning Paramter get
			writer.write(sendTuningParameterGet);
			System.out.println(sendTuningParameterGet);
			writer.flush();
			serverMsg = reader.readLine();
			System.out.println("Tuning Parameters: " + serverMsg);
			propotionalGain.setText(serverMsg);
			integralGain.setText(serverMsg);
			gainDivide.setText(serverMsg);
			scale.setText(serverMsg);
			fbDerivativeGain.setText(serverMsg);
			velocityFFGain.setText(serverMsg);
			accelerationFFGain.setText(serverMsg);
			socketClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleVerificationEnable() {

		this.serverOn();
	}

	@FXML
	private void handleVerificationDisable() {
		this.mainApp.thread_flag = false;
		//		this.serverOff();
	}

	/**
	 * @author : YOO YOUNGOK 
	 * @method  handleAbsolute1
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	@FXML
	private void handleAbsolute1() {
		this.ptpAbsolute1.setText(StringUtil.addComma(this.ptpAbsolute1.getText()));
		String ptp = StringUtil.removeCommna(this.ptpAbsolute1.getText());
		if(ptp!=null && !ptp.isEmpty()) {
			String speed = StringUtil.removeCommna(this.fxSpeed.getText());
			if(speed!=null) NetworkServerManager.handleAbsoluteRight(speed, ptp, speed);
		}
	}

	/**
	 * @author : YOO YOUNGOK 
	 * @method  handleAbsolute2
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	@FXML
	private void handleAbsolute2() {
		this.ptpAbsolute2.setText(StringUtil.addComma(this.ptpAbsolute2.getText()));
		String ptp = StringUtil.removeCommna(this.ptpAbsolute2.getText());
		if(ptp!=null && !ptp.isEmpty()) {
			String speed = StringUtil.removeCommna(this.fxSpeed.getText());
			if(speed!=null) NetworkServerManager.handleAbsoluteRight(speed, ptp, speed);
		}
	}

	/**
	 * @author : YOO YOUNGOK 
	 * @method  handleRelativeLeft
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	@FXML
	private void handleRelativeLeft() {
		this.ptpRelative.setText(StringUtil.addComma(this.ptpRelative.getText()));
		String ptp =  StringUtil.removeCommna(this.ptpRelative.getText());
		if(ptp!=null && !ptp.isEmpty()) {
			String speed = StringUtil.removeCommna(this.fxSpeed.getText());
			if(speed!=null)  NetworkServerManager.handleRelativeLeft(numberDriveNo, ptp, speed);
		}
	}

	@FXML
	private void handleRelativeRight() {
		this.ptpRelative.setText(StringUtil.addComma(this.ptpRelative.getText()));
		String ptp =  StringUtil.removeCommna(this.ptpRelative.getText());
		if(ptp!=null && !ptp.isEmpty()) {
			String speed = StringUtil.removeCommna(this.fxSpeed.getText());
			if(speed!=null)  NetworkServerManager.handleRelativeRight(numberDriveNo, ptp, speed);
		}
	}

	@FXML
	private void handleJoggingLeft() {
		String speed = StringUtil.removeCommna(this.fxSpeed.getText());
		if(speed!=null)  NetworkServerManager.handleJoggingLeft(numberDriveNo, speed);

	}

	@FXML
	private void handleJoggingRight() {
		String speed = StringUtil.removeCommna(this.fxSpeed.getText());
		if(speed!=null)  NetworkServerManager.handleJoggingRight(numberDriveNo, speed);
	}

	@FXML
	public void handleMotionSpeed() {
		if ((fxSpeed.getText() != null && !fxSpeed.getText().isEmpty())) {
			NetworkServerManager.setSpeed(this.numberDriveNo, StringUtil.removeCommna(fxSpeed.getText()));
			fxSpeed.setText(StringUtil.getNumnberFommat(fxSpeed.getText()));
		}
	}

	public void handleJogAcceleration() {
		if ((fxAcc.getText() != null && !fxAcc.getText().isEmpty())) {
			NetworkServerManager.setJogAcceleration(this.numberDriveNo, StringUtil.removeCommna(fxAcc.getText()));
			fxAcc.setText(StringUtil.getNumnberFommat(fxAcc.getText()));
		}
	}

	@FXML
	private void handleProfilerStop() {
		NetworkServerManager.stop(this.numberDriveNo);
	}

	@FXML
	private void handleSineStart() {
		System.out.println("Client: handleSineStart :: " + "Connection Established");
	}

	@FXML
	private void handleSineStop() {
		System.out.println("Client: handleSineStop :: " + "Connection Established");
	}

	@FXML
	private void handleVerificationApply() {

		int Driver_Num = this.selectDriveNo-1;
		String DriveNo = new Integer(Driver_Num).toString();
		String output = null;

		output =  CommandConst.PROPOTION_GAIN_DOWNLOAD.replace("driver", DriveNo);
		output = output.replace("V", this.propotionalGain.getText());
		System.out.println(output);
		NetworkServerManager.getInstance().downloadData(output);

		output = CommandConst.INTEGRAL_GAIN_DOWNLOAD.replace("driver", DriveNo);
		output = output.replace("V", this.propotionalGain.getText());
		System.out.println(output);
		NetworkServerManager.getInstance().downloadData(output);

		output = CommandConst.SCALE_DOWNLOAD.replace("driver", DriveNo);
		output = output.replace("V", this.propotionalGain.getText());
		System.out.println(output);
		NetworkServerManager.getInstance().downloadData(output);

		output = CommandConst.FEEDBACK_DERIVATIVE_GAIN_DOWNLOAD.replace("driver", DriveNo);
		output = output.replace("V", this.propotionalGain.getText());
		System.out.println(output);
		NetworkServerManager.getInstance().downloadData(output);

		output = CommandConst.GAIN_DIVIDE_DOWNLOAD.replace("driver", DriveNo);
		output = output.replace("V", this.propotionalGain.getText());
		System.out.println(output);
		NetworkServerManager.getInstance().downloadData(output);

		output = CommandConst.VELOCITY_FF_GAIN_DOWNLOAD.replace("driver", DriveNo);
		output = output.replace("V", this.propotionalGain.getText());
		System.out.println(output);
		NetworkServerManager.getInstance().downloadData(output);

		output = CommandConst.ACCELERATION_FF_GAIN_DOWNLOAD.replace("driver", DriveNo);
		output = output.replace("V", this.propotionalGain.getText());
		System.out.println(output);
		NetworkServerManager.getInstance().downloadData(output);
	}

	@FXML
	private void driverChoice() {	
		String sendSpeedGet = "i" + numberDriveNo + CommandConst.SPEED+"\r\n";
		System.out.println(sendSpeedGet);
		this.recordingStop2();

	}

	@FXML
	private void decreaseDot() {
		if(CommandConst.MAXDIGITS==1) {
			return;
		}

		CommandConst.MAXDIGITS--;
		System.out.println("Client: reduceDot == "+CommandConst.MAXDIGITS);

		if(CommandConst.DEBUG) {
			this.fxActualPosition.setText(StringUtil.getNumnberFommat("123456789.82678945"));
			double ap =1234597.126264568;
			double tp = 1234587.148123488;

			double error_pos = ap-tp;
			String result = Double.toString(error_pos);
			this.fxCurrent.setText(StringUtil.getNumnberFommat(result));
		}
	}

	@FXML
	private void increaseDot() {
		if(CommandConst.MAXDIGITS==5) {
			return;
		}

		CommandConst.MAXDIGITS++;

		System.out.println("Client: increaseDot== "+CommandConst.MAXDIGITS);

		if(CommandConst.DEBUG) {
			this.fxActualPosition.setText(StringUtil.getNumnberFommat("123456789.82678945"));
			double ap =1234597.126264568;
			double tp = 1234587.148123488;
			double error_pos = ap-tp;
			String result = Double.toString(error_pos);
			this.fxCurrent.setText(StringUtil.getNumnberFommat(result));
		}
	}

	/** 현재 사용하지 않음..  2018.06.12-> onClickChartDraw()로 대체 */
	@FXML
	private void recordingStart() {
		System.out.println("Chart Draw Start");
		this.tabPane.getSelectionModel().select(this.chartTab);

		//add 2018.04.26(차트그리지 못하는 문제 수정)
		panel.data.LeftChartName  = this.seriesComboBox.getSelectionModel().getSelectedItem();
		panel.data.RightChartName  = this.series2ComboBox.getSelectionModel().getSelectedItem();

		if(this.borderPane.getCenter()==null) {
			this.panel = new GraphPanel();
			JFreeChart chart =  panel.createChart();
			viewer = new ChartViewer(chart);
			this.borderPane.setCenter(viewer);
			this.panel.addCrossHair(viewer);
		}
		else {
			this.panel.removeCrossHair(viewer);
			this.panel.clearSeries();
			viewer.getChart().getXYPlot().setDataset(0, null);
			viewer.getChart().getXYPlot().setDataset(1, null);
			viewer.setChart(panel.createChart());
			this.panel.addCrossHair(viewer);
			System.out.println("Already Draw Chart");
		}

		this.setChartInfo();
	}

	public void onClickChartDraw() {
		System.out.println(Tag+" :: onClickChartDraw Start");
		this.chartController.chartReDraw();
	}

	/**
	 * Log 정보를 저장하기위한 프로그림을 실행한다.
	 * */
	@FXML
	public void logStart() {
		System.out.println("logStart");
		this.tabPane.getSelectionModel().select(1);
		this.chartController.setRecordingTimeView();

		if(CommandConst.DEBUG) return;

		Runtime runtime = Runtime.getRuntime();
		try {
			File file = new File("3.out");
			File mfile = new File("3.dat");
			if(file.exists()) {
				file.delete();
			}
			if(mfile.exists()) {
				mfile.delete();
			}

//			Process process = runtime.exec("/home/jscs1/justek_motionware/bin/lon.sh");
			Process process = runtime.exec("/home/jscs1/justek_motionware/etherCAT_data/getData.sh");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void longTermStart() {
		System.out.println("longTermStart");

		Runtime runtime = Runtime.getRuntime();
		try {
			Process process = runtime.exec("./bin/shm_helper1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Not Use!
	//	@FXML
	//	private void recordingStop() {
	//			System.out.println("Chart File Save");
	//			
	//			File mfile = new File("log_pos.dat");
	//			BufferedReader br;
	//			FileReader fr = null; 
	//			String line =null;
	//			
	//			File pos_file = new File("pos.txt");
	//			File acc_file = new File("acc.txt");
	//			
	//			BufferedWriter pos_out;
	//			BufferedWriter acc_out;
	//			
	//			if(mfile.exists()) {
	//			
	//			try{
	//				fr = new FileReader(mfile); 
	//				br = new BufferedReader(fr);
	//
	//				pos_out = new BufferedWriter(new FileWriter(pos_file));
	//				acc_out = new BufferedWriter(new FileWriter(acc_file));
	//				
	//				double ta_pos1 = 0;
	//				double ta_pos2 = 0;
	//				double ta_pos3 = 0;
	//				double target_pos;
	//				double actual_pos;
	//				double err_pos;
	//				double accel;
	//				
	//				String[] parsingList;
	//				while((line = br.readLine()) != null){
	//					parsingList = line.split(",");
	//					target_pos = Double.parseDouble(parsingList[0]);
	//					actual_pos = Double.parseDouble(parsingList[1]);
	//					ta_pos1 = ta_pos2;
	//					ta_pos2 = ta_pos3;
	//					ta_pos3 = target_pos;
	//					err_pos = target_pos-actual_pos; //error_position
	//					
	//					accel = (ta_pos3-ta_pos2)-(ta_pos2-ta_pos1);
	//					pos_out.write(Double.toString(err_pos)+"\n");
	//					acc_out.write(Double.toString(accel)+"\n");
	//				}
	//				
	//				
	//				pos_out.close();
	//				acc_out.close();
	//				
	//			}catch(IOException e) {
	//				e.printStackTrace(); 
	//			}
	//			}
	//	}

	@FXML
	private void recordingStop2() {
		this.chartController.stopRecordingTimeView();		
		GraphData.getInstance().saveData(this.selectDriveNo);
	}

	@FXML
	public void tuningRefresh() {
		getDriverPositionInfo(this.numberDriveNo);
	}

	/**
	 * Server Connect & Data Set/Get Method
	 * @param NumDriver
	 */

	public void getDriverPositionInfo(String NumDriver) {

		if(!NetworkServerManager.checkNetworkConnection()) return;
		
//		int Driver_Num = this.selectDriveNo-1;
		int Driver_Num = this.selectDriveNo;
		String DriveNo = new Integer(Driver_Num).toString();

		String output = null;
		output = CommandConst.PROPOTION_GAIN_UPLOAD.replace("driver", DriveNo);
		System.out.println(output);
		String propotionalgain = NetworkServerManager.uploadData(output);
		this.proportioalLabel.setText(this.removeDot(propotionalgain));

		output = CommandConst.INTEGRAL_GAIN_UPLOAD.replace("driver", DriveNo);
		System.out.println(output);
		String interalgain =  NetworkServerManager.uploadData(output);
		this.integralGainLabel.setText(this.removeDot(interalgain));

		output = CommandConst.SCALE_UPLOAD.replace("driver", DriveNo);
		System.out.println(output);
		String scale1 = NetworkServerManager.uploadData(output);
		this.scaleLabel.setText(this.removeDot(scale1));

		output = CommandConst.FEEDBACK_DERIVATIVE_GAIN_UPLOAD.replace("driver", DriveNo);
		System.out.println(output);
		String feedbackderivativegain = NetworkServerManager.uploadData(output);
		this.fbDerivativeGainLabel.setText(this.removeDot(feedbackderivativegain));

		output = CommandConst.GAIN_DIVIDE_UPLOAD.replace("driver", DriveNo);
		System.out.println(output);
		String gaindivide =  NetworkServerManager.uploadData(output);
		this.gainDivideLabel.setText(this.removeDot(gaindivide));

		output = CommandConst.VELOCITY_FF_GAIN_UPLOAD.replace("driver", DriveNo);
		System.out.println(output);
		String velocity_gain =  NetworkServerManager.uploadData(output);
		this.velocityLabel.setText(this.removeDot(velocity_gain));

		output = CommandConst.ACCELERATION_FF_GAIN_UPLOAD.replace("driver", DriveNo);
		System.out.println(output);
		String accelerationgain =  NetworkServerManager.uploadData(output);
		this.accelerationFFGainLabel.setText(this.removeDot(accelerationgain));

	}

	/** 
	 * setRealTimeData()
	 * 서버로 부터 받은 실시간 정보를 화면의 해당 필드에 표현한다.
	 * */
	public void setRealTimeData() {

		if(this.info!=null) {


			double ap1 = Double.parseDouble(info.ActualPosition);
			ap1 = ap1/this.unit;

			double av = Double.parseDouble(info.ActualVelocity);
			av = av/this.unit;

			this.fxVelocity.setText(StringUtil.getNumnberFommat(String.valueOf(av)));
			this.fxActualPosition.setText(StringUtil.getNumnberFommat(String.valueOf(ap1)));
			this.fxPosition.setText(StringUtil.getNumnberFommat(info.TargetPosition));


			double ap = Double.parseDouble(info.ActualPosition);
			double tp = Double.parseDouble(info.TargetPosition);		
			double error_pos = ap-tp;

//			if(CommandConst.DEBUG) {
//				error_pos =CommandConst.INTERVAL_TIME;
//			}

			this.fxCurrent.setText(StringUtil.getNumberFommat(error_pos));

		}
		else {
			this.fxVelocity.setText("0");
			this.fxActualPosition.setText("0");
			this.fxPosition.setText("0");
		}
	}

	public String removeDot(String readData) {
		String result = readData;
		if(readData!=null) {
			int dot = readData.indexOf('.');
			if(dot==-1) {
				result = readData;
			}else {
				result = readData.substring(0, dot);
			}
		}
		return result;
	}


	public void serverOn() {
		NetworkServerManager.serverOn(this.numberDriveNo);
	}

	public void serverOff() {
		NetworkServerManager.serverOff(this.numberDriveNo);
	}

	public void setPLC() {
		try {
			Socket socketClient = new Socket(CommandConst.address, 12345);
			System.out.println("Client: " + " setPLC :: Connection Established");

			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write("enable plc 3\r\n");
			writer.flush();

			socketClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Thread.sleep(2000);
		}catch(InterruptedException   e)
		{
			e.printStackTrace();
		}
	}

	public void setSatus(String value) {
		String binary = StringUtil.hexToBinary(value);

		//		System.out.println("Now Status == "+binary);

		binary = binary.replaceAll("0b", "");
		char[] by = binary.toCharArray();

		if(by.length>=6) {
			char vol = by[by.length-6];
			if(vol=='1') {
				this.voltageCircle.setFill(Color.RED);
			}
			else {
				this.voltageCircle.setFill(Color.GRAY);
			}
		}

		if(by.length>=8) {
			char warning = by[by.length-8];
			if(warning=='1') {
				this.warningCirclle.setFill(Color.RED);
			}
			else {
				this.warningCirclle.setFill(Color.GRAY);
			}
		}

		String status = String.valueOf( by[by.length-4]);
		StringBuilder sb = new StringBuilder(status);
		status = sb.append( by[by.length-3]).toString();
		status = sb.append( by[by.length-2]).toString();
		status = sb.append( by[by.length-1]).toString();

		if(status.equals("0111")) {
			if(by.length>=6) {
				char stop = by[by.length-6];
				if(stop=='1') {
					String s = "Operation enabled";
					this.stLabel.setText(s);
					this.statusCircle.setFill(Color.GREEN);
				}
				else {
					this.statusCircle.setFill(Color.RED);
					this.stLabel.setText("Quick stop active");
				}
			}
		}
		else if(status.equals("1111")) {
			this.statusCircle.setFill(Color.RED);
			this.stLabel.setText("Fault reaction active");
		}
		else if(status.equals("1000")) {
			this.statusCircle.setFill(Color.RED);
			this.stLabel.setText("Fault");
		}
		else if(status.equals("0000")) {
			char 	isReady = by[by.length-7];
			if(isReady=='1') {
				this.statusCircle.setFill(Color.GRAY);
				this.stLabel.setText("Switch on disabled");
			}
			else {
				this.statusCircle.setFill(Color.GRAY);
				this.stLabel.setText("Not Ready");
			}
		}
		else if(status.equals("0011")) {
			this.statusCircle.setFill(Color.GREEN);
			this.stLabel.setText("Switch On");
		}
		else if(status.equals("0001")) {
			this.statusCircle.setFill(Color.YELLOW);
			this.stLabel.setText("Ready to Switch on");
		}	
	}

	@Override
	public void handle(KeyEvent event) {
		int Driver_Num = this.selectDriveNo-1;
		String DriveNo = new Integer(Driver_Num).toString();
		String output = null;

		//Set realTime interval
		if(event.getCode()==KeyCode.ENTER && event.getSource().equals(this.tfTimeInterval)) {
			if(this.tfTimeInterval.getText()==null)
				return;

			int time = Integer.parseInt(this.tfTimeInterval.getText());

			//minimum time =100ms
			if(time<100) {
				return;
			}

			CommandConst.INTERVAL_TIME = time;
			System.out.println("Change request Time == "+CommandConst.INTERVAL_TIME);
		}
		else if(event.getCode()==KeyCode.ENTER) {
			if(event.getSource().equals(this.propotionalGain)) {
				output =  CommandConst.PROPOTION_GAIN_DOWNLOAD.replace("driver", DriveNo);
				String value = StringUtil.removeCommna( this.propotionalGain.getText());
				output = output.replace("V",value);
				NetworkServerManager.getInstance().downloadData(output);
				System.out.println(output);
			}
			else if(event.getSource().equals(this.integralGain)) {
				output = CommandConst.INTEGRAL_GAIN_DOWNLOAD.replace("driver", DriveNo);
				String value = StringUtil.removeCommna( this.integralGain.getText());
				output = output.replace("V",value);
				System.out.println(output);
				NetworkServerManager.getInstance().downloadData(output);
			}
			else if(event.getSource().equals(this.scale)) {
				output = CommandConst.SCALE_DOWNLOAD.replace("driver", DriveNo);
				String value = StringUtil.removeCommna( this.scale.getText());
				output = output.replace("V",value);
				System.out.println(output);
				NetworkServerManager.getInstance().downloadData(output);
			}
			else if(event.getSource().equals(this.velocityFFGain)) {
				output = CommandConst.VELOCITY_FF_GAIN_DOWNLOAD.replace("driver", DriveNo);
				String value = StringUtil.removeCommna( this.velocityFFGain.getText());
				output = output.replace("V",value);
				System.out.println(output);
				NetworkServerManager.getInstance().downloadData(output);
			}
			else if(event.getSource().equals(this.accelerationFFGain)) {
				output = CommandConst.ACCELERATION_FF_GAIN_DOWNLOAD.replace("driver", DriveNo);
				String value = StringUtil.removeCommna( this.accelerationFFGain.getText());
				output = output.replace("V",value);
				System.out.println(output);
				NetworkServerManager.getInstance().downloadData(output);
			}
			else if(event.getSource().equals(this.fbDerivativeGain)) {
				output = CommandConst.FEEDBACK_DERIVATIVE_GAIN_DOWNLOAD.replace("driver", DriveNo);
				String value = StringUtil.removeCommna( this.fbDerivativeGain.getText());
				output = output.replace("V",value);
				System.out.println(output);
				NetworkServerManager.getInstance().downloadData(output);
			}
			else if(event.getSource().equals(this.gainDivide)) {
				output = CommandConst.GAIN_DIVIDE_DOWNLOAD.replace("driver", DriveNo);
				String value = StringUtil.removeCommna( this.gainDivide.getText());
				output = output.replace("V",value);
				System.out.println(output);
				NetworkServerManager.getInstance().downloadData(output);
			}
			else if(event.getSource().equals(this.fxAcc)) {
				handleJogAcceleration();
			}
			else if(event.getSource().equals(this.fxSpeed)) {
				this.handleMotionSpeed();
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(!event.getSource().equals(this.fxSpeed) && !event.getSource().equals(this.fxAcc) ) this.getDriverPositionInfo(this.numberDriveNo);

		}
	}

	//ActualPosition & ActualVelocity set UserUnit
	public void modifyUnit() {
		TextInputDialog dialog = new TextInputDialog(String.valueOf(this.unit));
		dialog.setHeaderText("Set User Unit");
		dialog.setContentText("Current Unit : "+String.valueOf(this.unit)+" cnt");
		dialog.setTitle("Set UserUnit");
		ButtonType buttonTypeOk = new ButtonType("OK",ButtonData.OK_DONE);
		ButtonType buttonTypeCancel = new ButtonType("CANCEL", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().clear();
		dialog.getDialogPane().getButtonTypes().setAll(buttonTypeOk, buttonTypeCancel);
		Optional<String> result =  dialog.showAndWait();
		if (result.isPresent()) {
			this.unit = Integer.parseInt(result.get());
			System.out.println("modifyUnit == "+result.get());
		}
	}

	public void showPopupMenu() {
		this.fxActualPosition.setOnMouseReleased((MouseEvent e) -> {
			if (e.getButton() == MouseButton.SECONDARY) {
				this.fxActualPosition.setContextMenu(createContextMenu());
			}
		});
		this.fxVelocity.setOnMouseReleased((MouseEvent e) -> {
			if (e.getButton() == MouseButton.SECONDARY) {
				this.fxVelocity.setContextMenu(createContextMenu());
			}
		});
	}

	/** 2018.06.12부터 사용하지 않음.. */
	//TreeView Node Setting!
	public void setTreeView() {
		treeViewManager = TreeViewManager.getInstance();
		this.nodeList = treeViewManager.getNodeInfo();
		treeViewManager.setTreeView(treeView);

		TreeItem<String> root = new TreeItem<>("Axis Configrations", new ImageView(icon));
		root.setExpanded(true);
		root.getChildren().addAll(this.nodeList);
		root.getChildren().get(0).setExpanded(true);
		root.getChildren().get(1).setExpanded(true);
		root.getChildren().get(2).setExpanded(true);
		root.getChildren().get(3).setExpanded(true);
		root.getChildren().get(4).setExpanded(true);
		treeView.setRoot(root);
	}

	private ContextMenu createContextMenu() {
		ContextMenu contextMenu = new ContextMenu();
		MenuItem item1 = new MenuItem("Modify Unit");
		item1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				modifyUnit();
			}
		});

		MenuItem item2 = new MenuItem("Reset");
		item2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				unit = 1;
			}
		});

		contextMenu.getItems().addAll(item1, item2);

		return contextMenu;
	}

	public void mouseClick(MouseEvent mouseEvent) {
		if(mouseEvent.getSource() == this.treeView) {
			treeViewManager.setMainApp(this.mainApp);
			treeViewManager.mouseClick(mouseEvent);
		}
		else if(mouseEvent.getSource() == this.viewer) {

		}
	}

	/*
	 * Chart Action Method..
	 * */
	/** 2018.06.12부터 사용하지 않음.. */
	public String calculateReferenceY() {
		String result = "0";
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(5) ;

		Double d = Double.parseDouble(StringUtil.removeCommna(this.LeftY1Field.getText()));
		Double d1 = Double.parseDouble(StringUtil.removeCommna(this.LeftY2Field.getText()));

		if(d>d1) {
			result = nf.format(d-d1);
		}
		else if(d<d1) {
			result = nf.format(d1-d);
		}
		else if(d==d1) {
			result = "0";
		}
		return result;
	}
	/** 2018.06.12부터 사용하지 않음.. */
	public String calculateReferenceY2() {
		String result = "0";
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(5) ;
		Double d = Double.parseDouble(StringUtil.removeCommna(this.RightY1Field.getText()));
		Double d1 = Double.parseDouble(StringUtil.removeCommna(this.RightY2Field.getText()));
		if(d>d1) {
			result = nf.format(d-d1);
		}
		else if(d<d1) {
			result = nf.format(d1-d);
		}
		else if(d==d1) {
			result = "0";
		}
		return result;
	}
	/** 2018.06.12부터 사용하지 않음.. */
	public String calculateReferenceX() {
		String result = "0";
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(5) ;
		Double d = Double.parseDouble(StringUtil.removeCommna(this.X1Field.getText()));
		Double d1 = Double.parseDouble(StringUtil.removeCommna(this.X2Field.getText()));

		if(d>d1) {
			result = nf.format(d-d1);
		}
		else if(d<d1) {
			result = nf.format(d1-d);
		}
		else if(d==d1) {
			result = "0";
		}

		return result;
	}

	/** 2018.06.12부터 사용하지 않음.. */
	@FXML
	public void ChartKeyEvent(KeyEvent event) {
		if(event.getCode()==KeyCode.ENTER) {
			if(event.getSource() == this.chartNameField) {
				panel.setRangeName(this.chartNameField.getText(), this.chartNameComboBox.getSelectionModel().getSelectedIndex());
			}
			else if(event.getSource() == this.LeftY1Field) {
				Double value = Double.parseDouble(StringUtil.removeCommna(this.LeftY1Field.getText()));
				panel.setReferenceLineY(1, value);
				panel.setlostFocusX(1);
				panel.setlostFocusX(2);
				panel.setgetFocusY(1);
				panel.setlostFocusY(2);
			}
			else if(event.getSource() == this.LeftY2Field) {
				Double value = Double.parseDouble(StringUtil.removeCommna(this.LeftY2Field.getText()));
				panel.setReferenceLineY(2, value);
				panel.setlostFocusX(1);
				panel.setlostFocusX(2);
				panel.setlostFocusY(1);
				panel.setgetFocusY(2);
			}
			else if(event.getSource() == this.RightY1Field) {
				Double value = Double.parseDouble(StringUtil.removeCommna(this.RightY1Field.getText()));
				panel.setReferenceLineY2(1, value);
				panel.setlostFocusX(1);
				panel.setlostFocusX(2);
				panel.setgetFocusY(1);
				panel.setlostFocusY(2);
			}
			else if(event.getSource() == this.RightY2Field) {
				Double value = Double.parseDouble(StringUtil.removeCommna(this.RightY2Field.getText()));
				panel.setReferenceLineY2(2, value);
				panel.setlostFocusX(1);
				panel.setlostFocusX(2);
				panel.setlostFocusY(1);
				panel.setgetFocusY(2);
			}
			else if(event.getSource() == this.X1Field) {
				Double value = Double.parseDouble(StringUtil.removeCommna(this.X1Field.getText()));
				panel.setReferenceLineX(1, value);
				panel.setgetFocusX(1);
				panel.setlostFocusX(2);
				panel.setlostFocusY(1);
				panel.setlostFocusY(2);
			}
			else if(event.getSource() == this.X2Field) {
				Double value = Double.parseDouble(StringUtil.removeCommna(this.X2Field.getText()));
				panel.setReferenceLineX(2, value);
				panel.setlostFocusX(1);
				panel.setgetFocusX(2);
				panel.setlostFocusY(1);
				panel.setlostFocusY(2);
			}
		}
	}

	/** 2018.06.12부터 사용하지 않음.. */
	@FXML
	public void zoomIn() {
		double y1 = Double.parseDouble(StringUtil.removeCommna(this.minYField.getText()));
		double y2 =  Double.parseDouble(StringUtil.removeCommna(this.maxYField.getText()));

		double x1 = Double.parseDouble(StringUtil.removeCommna(this.minXField.getText()));
		double x2 =  Double.parseDouble(StringUtil.removeCommna(this.maxXField.getText()));
		this.panel.ZoomIn(this.zoomComboBox.getSelectionModel().getSelectedIndex(),x1,x2,y1, y2);
	}

	/** 2018.06.12부터 사용하지 않음.. */
	@FXML
	public void changeZoomComboBox() {
		this.minYField.setText(panel.getMinValue());
		this.maxYField.setText(panel.getMaxValue());
	}

	/** 2018.06.12부터 사용하지 않음.. */
	@FXML
	public void setChangeLeftChart() {
		panel.data.LeftChartName  = this.seriesComboBox.getSelectionModel().getSelectedItem();
	}
	/** 2018.06.12부터 사용하지 않음.. */
	@FXML
	public void setChangeRightChart() {
		panel.data.RightChartName  = this.series2ComboBox.getSelectionModel().getSelectedItem();
	}

	/** 2018.06.12부터 사용하지 않음.. */
	@FXML
	public void chartReDraw() {
		this.panel.removeCrossHair(viewer);
		this.panel.clearSeries();
		viewer.getChart().getXYPlot().setDataset(0, null);
		viewer.getChart().getXYPlot().setDataset(1, null);
		panel.data.LeftChartName  = this.seriesComboBox.getSelectionModel().getSelectedItem();
		panel.data.RightChartName  = this.series2ComboBox.getSelectionModel().getSelectedItem();
		viewer.setChart(panel.createChart());
		this.panel.addCrossHair(viewer);
		this.setChartInfo();
		System.out.println("ReDraw Chart");
	}

	@Override
	public void realTimeDatarEvent(double Source) { }

	@Override
	public void realTimeDataInfoEvent(RealTimeInfo Source) {
		// TODO Auto-generated method stub

				System.out.println(Tag + " :: " + Source.toString());

		// Update the Label on the JavaFx Application Thread
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				if(Source!=null) {
					if(numberDriveNo.equals(Source.DriverNo)) {
						info = Source;
						
						String value = StringUtil.removeDot(info.StausWords);
						value = StringUtil.getDecimalToHexValue(value);
						System.out.println(Tag +" getDecimalToHexValue = "+value);
						info.StausWords = value;
						setSatus(info.StausWords);
						setRealTimeData();
					}
				}
			}
		});
	}

	@Override
	public void autoScanEvent(ObservableList<TreeItem<String>> nodes) { }
	
	/** (non-Javadoc)
	 * @see justek.ide.model.listener.TreeEventListener#clieckMenu()
	 */
	@Override
	public void clieckMenu() {
		// TODO Auto-generated method stub
		this.selectDriveNo = CommandConst.DRIVER_NUMBEER;
		numberDriveNo = new Integer(selectDriveNo).toString();

		this.getDriverPositionInfo(this.numberDriveNo);
		this.showInitialSpeed();

	}

}

