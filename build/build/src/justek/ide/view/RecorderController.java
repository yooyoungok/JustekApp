package justek.ide.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TreeItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Calendar;

import org.controlsfx.control.CheckComboBox;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartViewer;

import com.supinan.util.timer.SupinanTimer;
import com.supinan.util.timer.Timer;
import com.supinan.util.timer.TimerStopType;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import justek.ide.MainApp;
import justek.ide.chart.data.GraphData;
import justek.ide.chart.data.GraphTriggerData;
import justek.ide.chart.view.GraphPanel;
import justek.ide.model.CommandConst;
import justek.ide.model.RealTimeInfo;
import justek.ide.model.TriggerInfo;
import justek.ide.model.TriggerThread;
import justek.ide.model.listener.RealTimeEventListener;
import justek.ide.model.listener.TreeEventListener;
import justek.ide.model.listener.TriggerEventListener;
import justek.ide.utils.StringUtil;

public class RecorderController implements TreeEventListener,TriggerEventListener,RealTimeEventListener{
	
	static final String Tag = "RecorderController";
	
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
	@FXML
	private Label fxTimeLabel;
    @FXML
    private ToggleButton fxLeftToggleButton;
    @FXML
    private ToggleButton fxRightToggleButton;
    
	private GraphPanel panel;
	private ChartViewer viewer;
	private ObservableList<String> list;
	
    private ObservableList<String> sourceList;
    
	ObservableList<String> leftAddList;
	ObservableList<String> rightAddList;
	
	//Trigger Info
    @FXML
    private ComboBox<String> triggerComboBox;
	@FXML
	private ComboBox<String> targetComoboBox;
	@FXML
	private ComboBox<String> resolutionComoboBox;
	@FXML
	private ComboBox<String> recorderTimeComoboBox;
    @FXML
    private TextField fxRecordTimeTextField;
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
    @FXML
    private TextField fxRightUnitTextField;
    @FXML
    private TextField fxLeftUnitTextField;
    @FXML
    private ProgressBar processBar;
    @FXML
    private Label notiLabel;
    
	@FXML
	private CheckComboBox<String> fxLeftCheckComboBox;
	@FXML
	private CheckComboBox<String> fxRightCheckComboBox;
	@FXML
	private HBox fxHBox;

	
	//End Trigger Info
    
	private MainApp mainApp;

	private SupinanTimer timer;
	private long threadId; //Timer ID
	private long realThreadId; //Timer ID
	private long timerId; //Timer ID
	private TriggerThread triggerThread;
	private TriggerThread realTimeThread;
	private TriggerInfo info;
	public int selectDriveNo = 1;
	public String numberDriveNo = new Integer(selectDriveNo).toString();
	private boolean ignoreStart=false;
	private boolean triggerStart=false;
	
	
	/**
	* Is called by the main application to give a reference back to itself.
	* 
	* @param mainApp
	*/
	
	@FXML
	private void initialize() {
		this.panel = new GraphPanel();
		JFreeChart chart = panel.defaultCreateChart();
		viewer = new ChartViewer(chart);
		this.borderPane.setCenter(viewer);
		list =  FXCollections.observableArrayList(this.panel.getRangeList());
		this.chartNameComboBox.setItems(this.list);
		this.chartNameComboBox.setValue(list.get(0));
		
		this.leftAddList = FXCollections.observableArrayList();
		this.rightAddList = FXCollections.observableArrayList();
		
		sourceList =  FXCollections.observableArrayList(CommandConst.TRIGGER_RLIST);
		
		this.triggerComboBox.setItems(sourceList);
		this.triggerComboBox.setValue(sourceList.get(1));
		
		this.recorderTimeComoboBox.setItems(CommandConst.RECORD_TIME_LIST);
		this.recorderTimeComoboBox.setValue(CommandConst.RECORD_TIME_LIST.get(0));
		this.resolutionComoboBox.setItems(CommandConst.RESOULTION_LIST);
		this.resolutionComoboBox.setValue(CommandConst.RESOULTION_LIST.get(0));
		this.sliderListener();
		
		this.setCheckComboBox();
		
		this.sliderX1.setDisable(true);
		this.sliderX2.setDisable(true);
		this.sliderY1.setDisable(true);
		this.sliderY2.setDisable(true);
		
//		this.seriesComboBox.setItems(sourceList);
//		this.seriesComboBox.setValue(sourceList.get(0));
//		this.series2ComboBox.setItems(sourceList);
//		this.series2ComboBox.setValue(sourceList.get(5));
	}
	

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		this.mainApp.listener =  this;
	} 
	
	private void setCheckComboBox() {
		this.fxLeftCheckComboBox = new CheckComboBox<String>(CommandConst.TRIGGER_RLIST);
		this.fxRightCheckComboBox =  new CheckComboBox<String>(CommandConst.TRIGGER_RLIST);
		this.fxHBox.getChildren().add(0, this.fxLeftCheckComboBox);
		this.fxHBox.getChildren().add(3, this.fxRightCheckComboBox);
		HBox.setMargin(this.fxLeftCheckComboBox,new Insets(0,0,0,60));
		fxLeftCheckComboBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
		     public void onChanged(ListChangeListener.Change<? extends String> c) {
		    	 leftAddList = fxLeftCheckComboBox.getCheckModel().getCheckedItems();
		         System.out.println(leftAddList);
		     }
		 });
		fxRightCheckComboBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
		     public void onChanged(ListChangeListener.Change<? extends String> c) {
		    	 rightAddList = fxRightCheckComboBox.getCheckModel().getCheckedItems();
		         System.out.println(rightAddList);
		     }
		 });
		
		this.fxLeftCheckComboBox.getCheckModel().check(0);
		this.fxRightCheckComboBox.getCheckModel().check(2);
	}
	
	
	//Not Use!
	@FXML
	public void clickTrigger() {
		
		this.info = new TriggerInfo();
		boolean ok = this.showTriggerDialog(info);
		
		if(ok) {
			System.out.println("clickTrigger return == OK");
			System.out.println(info.toString());
			try {

				File file;
				
				if(CommandConst.DEBUG) {
					 file = new File("C:/home/trigger.cnf");
				}
				else {
					file = new File("./home/trigger.cnf");
				}
				
				BufferedWriter out = new BufferedWriter(new FileWriter(file));

				out.write(info.getSource()+"\n");
				out.write(info.getLevel()+"\n");
				out.write(info.getMode()+"\n");
				out.write(info.getSlope()+"\n");
				out.write(info.getDelay());
				out.close();
				
			} catch (IOException e) {
				e.printStackTrace(); 
			}
		}
		else {
			System.out.println("clickTrigger return == Cancel");
		}
	}
	
	
	//Now Not Use!
	public boolean showTriggerDialog(TriggerInfo info) {
	    try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/ChartChooser.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Trigger");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(this.mainApp.getPrimaryStage());
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        TriggerController controller = loader.getController();
	        controller.setTriggerInfo(info);
	        controller.setDialogStage(dialogStage);
	        dialogStage.showAndWait();

	        return controller.isOkClicked();
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	//현재 사용하지 않음... 
	public void startTrigger() {
		if(this.realTimeThread==null) {
			this.realTimeThread = TriggerThread.getInstance(10, "RealTime Log", this.numberDriveNo);
			this.realTimeThread.addTriggerEventListener(this);
			timer = new SupinanTimer();
		}
		this.realThreadId = timer.addTimer(this.realTimeThread);
	}
	
	
	//LongTerm LogProcess(기본5초마다 파일을 체크하여 데이터를 확인한다.)
	public void startLongTermTrigger() {
		this.triggerStart=true;
		this.ignoreStart=false;
		
		if(this.timer==null) {
			timer = new SupinanTimer();
		}
		
		this.timerId = timer.addTimer(new TimerCall(1000));
		this.fxTimeLabel.setText("Trigger 소요시간 : 0 시간 0 분 0초");
	
		GraphTriggerData chartData = GraphTriggerData.getInstance();
		chartData.setTriggerInfo(this.info, this.selectDriveNo,this);
		chartData.checkFile();
		chartData.runLogProcess();
	}
	
	
	public void setTriggerInfo() {
		this.info = new TriggerInfo();
	
    	info.setSource(this.triggerComboBox.getSelectionModel().getSelectedItem().toString());
    	
//    	info.setRecordTime(this.recorderTimeComoboBox.getSelectionModel().getSelectedItem().toString());
    	info.setRecordTime(fxRecordTimeTextField.getText()); //-> 사용자가 직접 입력하도록 수정(2018.06.05)
    	info.setResolution(this.resolutionComoboBox.getSelectionModel().getSelectedItem().toString());
    	
    	this.fxLeftCheckComboBox.getCheckModel().clearChecks();
		this.fxLeftCheckComboBox.getCheckModel().check(info.getSource());
		
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
    	
    	this.panel.setTimeUnit(info.getResolution());
    	
    	System.out.println(Tag+" :: setTriggerInfo :: " + info.toString());
	}
	
	@FXML
	public void clickStart() {
		System.out.println(Tag+" :; clickStart :: " + this.numberDriveNo);
		
		this.processBar.setVisible(true);
		this.notiLabel.setVisible(true);
		
//		this.startTrigger();
		this.setTriggerInfo();

		startLongTermTrigger();
		
	}
	
	public void triggerChartDraw() {
		this.panel.removeCrossHair(viewer);
		this.panel.clearSeries();
		viewer.getChart().getXYPlot().setDataset(0, null);
		viewer.getChart().getXYPlot().setDataset(1, null);
		panel.data.LeftChartName  =info.getSource();
		viewer.setChart(panel.createTriggerChart(info.getSource()));
		this.panel.addCrossHair(viewer);
		this.setChartInfo();
		System.out.println(Tag+" :: triggerChartDraw");
	}
	
	@FXML
	public void IgnoreTriggerRun() {
		System.out.println("IgnoreTriggerRun");
		if(CommandConst.DEBUG) return;
		
		this.triggerStart=false;
		this.ignoreStart=true;
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
			Process process = runtime.exec("/home/jscs1/justek_motionware/bin/lon.sh");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void recordingStop2() {
		this.processBar.setVisible(false);
		this.notiLabel.setVisible(false);
		
		if(this.ignoreStart) {
			System.out.println("recordingStop2 _ DriverNo. =="+this.selectDriveNo);
			GraphData.getInstance().saveData(this.selectDriveNo);
		}
		else if(this.triggerStart) {
			GraphTriggerData.getInstance().timer.closeTimer();
			this.timer.removeTimer(this.timerId);
		}
	}
	
	/*
	 * Chart Action Method..
	 * */
	
	//chart  Default Value Setting
	public void setChartInfo() {
		this.minXField.setText(panel.getMinDomain());
		this.maxXField.setText(panel.getMaxDomain());
		this.minYField.setText(panel.getMinValue());
		this.maxYField.setText(panel.getMaxValue());
		
		list =  FXCollections.observableArrayList(this.panel.getRangeList());

		
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				zoomComboBox.setItems(list);
				zoomComboBox.setValue(list.get(0));
				
				chartNameComboBox.setItems(list);
				chartNameComboBox.setValue(list.get(0));
			}
		});

		
		this.LeftY1Field.setText("0");
		this.LeftY2Field.setText("0");
		this.RightY1Field.setText("0");
		this.RightY2Field.setText("0");
		this.X1Field.setText("0");
		this.X2Field.setText("0");
		
		this.YRangeField.setText("0");
		this.Y2RangeField.setText("0");
		this.XRangeField.setText("0");
		
		this.sliderX1.setDisable(false);
		this.sliderX2.setDisable(false);
		this.sliderY1.setDisable(false);
		this.sliderY2.setDisable(false);
	}
	
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
			else if(event.getSource() == this.fxLeftUnitTextField) {
				Double value = Double.parseDouble(fxLeftUnitTextField.getText());
				if(value==0) {
					value=1.0;
				}
				this.panel.setTickUnit(0, value);
				this.chartReDraw();
			}
			else if(event.getSource() == this.fxRightUnitTextField) {
				Double value = Double.parseDouble(fxRightUnitTextField.getText());
				if(value==0) {
					value=1.0;
				}
				this.panel.setTickUnit(1, value);
				this.chartReDraw();
			}
		}
	}
	
	//chart Zoom Event (TextField Input)
	@FXML
	public void zoomIn() {
		 double y1 = Double.parseDouble(StringUtil.removeCommna(this.minYField.getText()));
		 double y2 =  Double.parseDouble(StringUtil.removeCommna(this.maxYField.getText()));
		 
		 double x1 = Double.parseDouble(StringUtil.removeCommna(this.minXField.getText()));
		 double x2 =  Double.parseDouble(StringUtil.removeCommna(this.maxXField.getText()));
		 this.panel.ZoomIn(this.zoomComboBox.getSelectionModel().getSelectedIndex(),x1,x2,y1, y2);
	}
	
	
	@FXML
	public void changeZoomComboBox() {
		int index = this.zoomComboBox.getSelectionModel().getSelectedIndex();
		if(index==-1) index = 0;
		
		this.minYField.setText(panel.getMinValue(index));
		this.maxYField.setText(panel.getMaxValue(index));
	}
	
	@FXML
	public void setChangeLeftChart() {
		panel.data.LeftChartName  = this.seriesComboBox.getSelectionModel().getSelectedItem();
	}
	
	@FXML
	public void setChangeRightChart() {
		panel.data.RightChartName  = this.series2ComboBox.getSelectionModel().getSelectedItem();
	}
	
	@FXML
	public void chartReDraw() {
		System.out.println("ReDraw Chart");
		this.panel.removeCrossHair(viewer);
		this.panel.clearSeries();		
		viewer.getChart().getXYPlot().setDataset(0, null);
		viewer.getChart().getXYPlot().setDataset(1, null);

		panel.data.LeftChartName  = this.leftAddList.get(0);
		panel.data.RightChartName  = this.rightAddList.get(0);
		viewer.setChart(panel.createChart());

		if(this.leftAddList.size()>1) {
			for(String name : this.leftAddList) {
				if(!name.equals(panel.data.LeftChartName)){
					panel.addLeftDataSet(name);
				}
			}
		}

		if(this.rightAddList.size()>1) {
			for(String name : this.rightAddList) {
				if(!name.equals(panel.data.RightChartName)){
					panel.addRightDataSet(name);
				}
			}
		}

		this.panel.addCrossHair(viewer);
		this.setChartInfo();
	}

	
	@Override
	public void clieckMenu() {
		// TODO Auto-generated method stub
		this.selectDriveNo = CommandConst.DRIVER_NUMBEER;
		numberDriveNo = new Integer(selectDriveNo).toString();
	}
	
	/*
	 * TriggerEventListener
	 * */
	@Override
	public void NormalTriggerEvent(String Source) {
		// TODO Auto-generated method stub
		System.out.println("NormalTriggerEvent");
	}

	@Override
	public void SingleTriggerEvent(String Source) {
		// TODO Auto-generated method stub
		System.out.println("SingleTriggerEvent");
		
		this.timer.removeTimer(this.timerId);
		this.timer.closeTimer();
		
		this.processBar.setVisible(false);
		this.notiLabel.setVisible(false);
		
		this.panel.removeCrossHair(viewer);
		this.panel.clearSeries();
		viewer.getChart().getXYPlot().setDataset(0, null);
		viewer.getChart().getXYPlot().setDataset(1, null);
		panel.data.LeftChartName  = info.getSource();
		panel.data.RightChartName = this.rightAddList.get(0);
		viewer.setChart(panel.createChart());
		this.panel.addCrossHair(viewer);
		
		this.setChartInfo();
		System.out.println("SingleTriggerEvent ReDraw Chart ="+ info.getSource());

	}
	
	@Override
	public void realTimeDatarEvent(double Source) {
		// TODO Auto-generated method stub
		System.out.println("realTimeDatarEvent");
		this.panel.data.addSeries(Source);
		
	}

	@Override
	public void autoScanEvent(ObservableList<TreeItem<String>> nodes) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void realTimeDataInfoEvent(RealTimeInfo Source) {
		// TODO Auto-generated method stub
		
	}
	
	
	public class TimerCall extends Timer{

		int hour=0;
		int min=0;
		int sec=0;
		/*
		 * repeatPeriod 의 시간이 지날 때마다 execute() 메소드를 호출하도록 설정한다
		 * 단위 : ms  
		 * 예 : repeatPeriod = 1000 일 경우 1초
		 */
		public TimerCall(long repeatPeriod) {
			super(repeatPeriod);
		}

		/*
		 * 지정한 시간이 지났을 때마다 호출되는 메소드
		 */
		@Override
		public void execute() {
			Calendar cal = Calendar.getInstance();
			System.out.println("TimerCall 호출! " + cal.get(Calendar.HOUR_OF_DAY) + "시 " +
							cal.get(Calendar.MINUTE) + "분 " + cal.get(Calendar.SECOND) + "초");
			
			// Update the Label on the JavaFx Application Thread
			Platform.runLater(new Runnable()
			{
				@Override
				public void run()
				{
					sec++;
					
					if(sec==60) {
						min++;
						sec=0;
						if(min==0) {
							hour++;
							min=0;
						}
					}

					fxTimeLabel.setText("Trigger 소요시간 : "+String.valueOf(hour)+" 시간 "+String.valueOf(min)+" 분 "+ String.valueOf(sec)+" 초");
				}
			});
		}

		/*
		 * 타이머가 종료되었을 경우 호출된다
		 * 해당 타이머 구현체는 일정시간마다 무한 반복이기 때문에 
		 * SupinanTimer 의 closeTimer() 를 호출하지 않는한 해당 메소드는
		 * 호출하지 않는다
		 */
		@Override
		public void stopTimer(TimerStopType type) {
			System.out.println("TimerCall 종료:" + type.name());
		}

	}

}
