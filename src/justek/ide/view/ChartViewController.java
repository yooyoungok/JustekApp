/**
 * ------------------------------------------------------------------------------
 * @Project       : JustekApp
 * @Source        : ChartViewController.java
 * @Description  : TargetAcceleration","PositionError","TargetPosition", "ActualPosition","TargetVelocity","ActualVelocity"
 * 						위의 5가지 정보를 그래프로 나타내어 모터의 기능을 분석한다. 
 * @Author        : YOUNGOK YOO
 * @Version       : v1.0
 * Copyright(c) 2018 JUSTEK All rights reserved
 *------------------------------------------------------------------------------
 *                  변         경         사         항                       
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                      DESCRIPTION                        
 * ----------  ------  --------------------------------------------------------- 
 * 2018.04.05   유영옥    신규생성                                     
 *------------------------------------------------------------------------------
 */

package justek.ide.view;

import java.text.NumberFormat;
import java.util.Calendar;

import org.controlsfx.control.CheckComboBox;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartViewer;

import com.supinan.util.timer.SupinanTimer;
import com.supinan.util.timer.Timer;
import com.supinan.util.timer.TimerStopType;

import justek.ide.chart.data.GraphData;
import justek.ide.chart.view.GraphPanel;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import justek.ide.model.CommandConst;
import justek.ide.utils.StringUtil;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ChartViewController  {

	@FXML
	private BorderPane borderPane;

	/** 좌축의 Y값 Reference Line을 이동하기위한 Slider */
	@FXML
	private Slider sliderY1;
	/** 우축의 Y값 Reference Line을 이동하기위한 Slider */
	@FXML
	private Slider sliderY2; 
	/** 좌축의 X값 Reference Line을 이동하기위한 Slider */
	@FXML
	private Slider sliderX1; 
	/** 우축의 X값 Reference Line을 이동하기위한 Slider */
	@FXML
	private Slider sliderX2; 

	/** Y값의 최소값 필드 */
	@FXML
	private TextField minYField;
	/** Y값의 최대값 필드 */
	@FXML
	private TextField maxYField;

	/** X값의 최소값 필드 */
	@FXML
	private TextField minXField; 
	/** X값의 최대값 필드 */
	@FXML
	private TextField maxXField; 
	/** 확대 버튼*/
	@FXML
	private Button zoomButton;
	/**좌축의 현재 Reference Y1값 */
	@FXML
	private TextField LeftY1Field;
	/**좌축의 현재 Reference Y2값 */
	@FXML
	private TextField LeftY2Field;
	/**우축의 현재 Reference Y1값 */
	@FXML
	private TextField RightY1Field;
	/**우축의 현재 Reference Y1값 */
	@FXML
	private TextField RightY2Field;
	/**X축의 현재 Reference X1값 */
	@FXML
	private TextField X1Field;
	/**X축의 현재 Reference X2값 */
	@FXML
	private TextField X2Field;
	/**변경할 Chart Name */
	@FXML
	private TextField chartNameField;
	/** 좌축의 Unit 변경 Field*/
	@FXML
	private TextField YRangeField;
	/** 좌축의 Unit 변경 Field*/
	@FXML
	private TextField Y2RangeField;
	/** X축의 범위 Field*/
	@FXML
	private TextField XRangeField;
	/** 좌축의 Unit 변경 Field*/
	@FXML
	private TextField fxLeftUnitTextField;
	/** 우축의 Unit 변경 Field*/
	@FXML
	private TextField fxRightUnitTextField;
	/** ChartName을 변경할 ComboBox */
	@FXML
	private ComboBox<String> chartNameComboBox;
	/** 확대할 그래프명을 선택하지 위한 ComboBox */
	@FXML
	private ComboBox<String> zoomComboBox;
	//	@FXML
	//	private ComboBox<String> seriesComboBox;
	//	@FXML
	//	private ComboBox<String> series2ComboBox;
	/** 좌축에 추가할 정보를 선택할 수 있는 CheckComboBox */
	@FXML
	private CheckComboBox<String> fxLeftCheckComboBox;
	/** 우축에 추가할 정보를 선택할 수 있는 CheckComboBox */
	@FXML
	private CheckComboBox<String> fxRightCheckComboBox;
	@FXML
	private HBox fxHBox; 

	/** Recording 소요시간 표시 */
	@FXML
	private Label fxTimeLabel;	
	/** Recording progressBar */
	@FXML
	private ProgressBar processBar;
	/** Recording중임을 표시하는 Info Label */
	@FXML
	private Label notiLabel;

	/** Timer Thread Object */
	private SupinanTimer timer;
	/** Recording Timer ID */
	private long timerId;
	/** Selected Driver Number */
	public int selectDriveNo = 1;
	/** Selected Driver Number : String value */
	public String numberDriveNo = new Integer(selectDriveNo).toString();
	
	public int recordingTime = 10;

	/** 그래프 생성을 위한 GraphPanel */
	private GraphPanel panel;
	/** JavaFX로 JFreeChart를 그리기 위한 Viewer */
	private ChartViewer viewer;
	/** 현재 Chart에 그려진 양축 Chart Name */
	private ObservableList<String> list;
	/**  좌축에 그려진 정보리스트 */
	private ObservableList<String> leftAddList; 	
	/** 우축에 추가된 정보리스트 */
	private ObservableList<String> rightAddList;	

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */

	@FXML
	private void initialize() {
		this.sliderListener();
		this.panel = new GraphPanel();
		JFreeChart chart = panel.defaultCreateChart();
		viewer = new ChartViewer(chart);
		this.borderPane.setCenter(viewer);
		this.panel.addCrossHair(viewer);
		this.leftAddList = FXCollections.observableArrayList();
		this.rightAddList = FXCollections.observableArrayList();
		this.setCheckComboBox();
	}

	/**
	 * 그래프를 그리기 위한 ComboBox Setting...
	 */
	public void setCheckComboBox() {
		this.fxLeftCheckComboBox = new CheckComboBox<String>(CommandConst.chartList);
		this.fxRightCheckComboBox =  new CheckComboBox<String>(CommandConst.chartList);
		this.fxHBox.getChildren().add(0, this.fxLeftCheckComboBox);
		this.fxHBox.getChildren().add(3, this.fxRightCheckComboBox);
		HBox.setMargin(this.fxLeftCheckComboBox,new Insets(0,0,0,60));
		//그래프를 그릴 정보를 선택하면 리스트에 추가한다.
		fxLeftCheckComboBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
			public void onChanged(ListChangeListener.Change<? extends String> c) {
				leftAddList = fxLeftCheckComboBox.getCheckModel().getCheckedItems();
				System.out.println(leftAddList);
			}
		});
		//그래프를 그릴 정보를 선택하면 리스트에 추가한다.
		fxRightCheckComboBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
			public void onChanged(ListChangeListener.Change<? extends String> c) {
				rightAddList = fxRightCheckComboBox.getCheckModel().getCheckedItems();
				System.out.println(rightAddList);
			}
		});

		this.fxLeftCheckComboBox.getCheckModel().check(0);
		this.fxRightCheckComboBox.getCheckModel().check(2);
	}

	/**
	 * 그래프 정보를 각 해당필드와 ComboBox에 입력하고, 초기화 한다.
	 */
	/**
	 * @author : YOO YOUNGOK 
	 * @method  setChartInfo
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
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

	/**
	 * Reference Line을 그리기 위한 Slider의 값을 설정하고 ChangeListener를 등록한다.
	 * Slider의 이동에 따른 Reference값을 해당필드에 입력한다.
	 * */
	/**
	 * @author : YOO YOUNGOK 
	 * @method  sliderListener
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public void sliderListener() {
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

	/**
	 *  사용자가 입력한 값을 Reference의 좌축의 Y값의 Delta값을 계산한다.
	 * @author : YOO YOUNGOK 
	 * @method  calculateReferenceY
	 * @return  String 계산결과
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public String calculateReferenceY() {
		String result = "0";	// d,d1의 계산결과
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(5) ;

		String x1 = StringUtil.removeCommna(this.LeftY1Field.getText());
		if(x1==null) x1 = "0";
		Double d = Double.parseDouble(x1);

		String x2 = StringUtil.removeCommna(this.LeftY2Field.getText());
		if(x2==null) x2 = "0";
		Double d1 = Double.parseDouble(x2);


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


	/**
	 * 사용자가 입력한 값을 Reference의 우축의 Y값의 Delta값을 계산한다.
	 * @author : YOO YOUNGOK 
	 * @method  calculateReferenceY2
	 * @return
	 * @return  String 계산결과
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public String calculateReferenceY2() {
		String result = "0"; // d,d1의 계산결과
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(5) ;

		String x1 = StringUtil.removeCommna(this.RightY1Field.getText());
		if(x1==null) x1 = "0";
		Double d = Double.parseDouble(x1);

		String x2 = StringUtil.removeCommna(this.RightY2Field.getText());
		if(x2==null) x2 = "0";
		Double d1 = Double.parseDouble(x2);

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


	/**
	 * 사용자가 입력한 값을 Reference X의 Delta값을 계산한다.	 * 
	 * @author : YOO YOUNGOK 
	 * @method  calculateReferenceX
	 * @return
	 * @return  String 계산결과
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public String calculateReferenceX() {
		String result = "0"; // d,d1의 계산결과
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(5) ;

		String x1 = StringUtil.removeCommna(this.X1Field.getText());
		if(x1==null) x1 = "0";
		Double d = Double.parseDouble(x1);

		String x2 = StringUtil.removeCommna(this.X2Field.getText());
		if(x2==null) x2 = "0";
		Double d1 = Double.parseDouble(x2);

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


	/**
	 * Recording시간측정 Start 및 해당정보 Label의 Visible을 true로 변경	 * 
	 * @author : YOO YOUNGOK 
	 * @method  setRecordingTimeView
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public void setRecordingTimeView(int time) {
		this.notiLabel.setVisible(true);
		this.processBar.setVisible(true);
		if(this.timer==null) {
			timer = new SupinanTimer();
		}
		
		this.recordingTime = time;
		this.timerId = timer.addTimer(new TimerCall(1000));
		this.fxTimeLabel.setText("Recording Time : 0 시간 0 분 0초");
	}

	/**
	 * Recording시간측정 Stop 및 해당정보 Label의 Visible을 false로 변경	 * 
	 * @author : YOO YOUNGOK 
	 * @method  stopRecordingTimeView
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public void stopRecordingTimeView() {
		if(this.timer!=null) {
			this.timer.removeTimer(this.timerId);
		}
		this.notiLabel.setVisible(false);
		this.processBar.setVisible(false);
	}


	/**
	 * KeyEvent에 해당하는 이벤트를 실행한다.	 * 
	 * @author : YOO YOUNGOK 
	 * @method  ChartKeyEvent
	 * @param event (KeyCode.ENTER)
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	@FXML
	public void ChartKeyEvent(KeyEvent event) {
		if(event.getCode()==KeyCode.ENTER) {
			//그래프 명 변경
			if(event.getSource() == this.chartNameField) {
				panel.setRangeName(this.chartNameField.getText(), this.chartNameComboBox.getSelectionModel().getSelectedIndex());
			}
			//좌축의 Y값의 Referencer값을 변경하고 해당 Slider의 위치를 변경한다.
			else if(event.getSource() == this.LeftY1Field) {
				Double value = Double.parseDouble(StringUtil.removeCommna(this.LeftY1Field.getText()));
				panel.setReferenceLineY(1, value);
				panel.setlostFocusX(1);
				panel.setlostFocusX(2);
				panel.setgetFocusY(1);
				panel.setlostFocusY(2);
			}
			//좌축의 Y값의 Referencer값을 변경하고 해당 Slider의 위치를 변경한다.
			else if(event.getSource() == this.LeftY2Field) {
				Double value = Double.parseDouble(StringUtil.removeCommna(this.LeftY2Field.getText()));
				panel.setReferenceLineY(2, value);
				panel.setlostFocusX(1);
				panel.setlostFocusX(2);
				panel.setlostFocusY(1);
				panel.setgetFocusY(2);
			}
			//우축의 Y값의 Referencer값을 변경하고 해당 Slider의 위치를 변경한다.
			else if(event.getSource() == this.RightY1Field) {
				Double value = Double.parseDouble(StringUtil.removeCommna(this.RightY1Field.getText()));
				panel.setReferenceLineY2(1, value);
				panel.setlostFocusX(1);
				panel.setlostFocusX(2);
				panel.setgetFocusY(1);
				panel.setlostFocusY(2);
			}
			//우축의 Y값의 Referencer값을 변경하고 해당 Slider의 위치를 변경한다.
			else if(event.getSource() == this.RightY2Field) {
				Double value = Double.parseDouble(StringUtil.removeCommna(this.RightY2Field.getText()));
				panel.setReferenceLineY2(2, value);
				panel.setlostFocusX(1);
				panel.setlostFocusX(2);
				panel.setlostFocusY(1);
				panel.setgetFocusY(2);
			}
			//X값의 Referencer값을 변경하고 해당 Slider의 위치를 변경한다.
			else if(event.getSource() == this.X1Field) {
				Double value = Double.parseDouble(StringUtil.removeCommna(this.X1Field.getText()));
				panel.setReferenceLineX(1, value);
				panel.setgetFocusX(1);
				panel.setlostFocusX(2);
				panel.setlostFocusY(1);
				panel.setlostFocusY(2);
			}
			//X값의 Referencer값을 변경하고 해당 Slider의 위치를 변경한다.
			else if(event.getSource() == this.X2Field) {
				Double value = Double.parseDouble(StringUtil.removeCommna(this.X2Field.getText()));
				panel.setReferenceLineX(2, value);
				panel.setlostFocusX(1);
				panel.setgetFocusX(2);
				panel.setlostFocusY(1);
				panel.setlostFocusY(2);
			}
			//좌축의 Unit를 변경한다.
			else if(event.getSource() == this.fxLeftUnitTextField) {
				if(this.fxLeftUnitTextField.getText()!=null) {
					Double value = Double.parseDouble(fxLeftUnitTextField.getText());
					if(value==0) {
						value=1.0;
					}
					this.panel.setTickUnit(0, value);
					this.chartReDraw();
				}
			}
			//우축의 Unit를 변경한다.
			else if(event.getSource() == this.fxRightUnitTextField) {
				if(this.fxRightUnitTextField.getText()!=null) {
					Double value = Double.parseDouble(fxRightUnitTextField.getText());
					if(value==0) {
						value=1.0;
					}
					this.panel.setTickUnit(1, value);
					this.chartReDraw();
				}
			}
		}
	}

	/**
	 * 사용자가 입력한 값으로 그래프를 확대한다.
	 * Chart Zoom Event 
	 * */
	@FXML
	public void zoomIn() {
		double y1 = Double.parseDouble(StringUtil.removeCommna(this.minYField.getText())); 
		double y2 =  Double.parseDouble(StringUtil.removeCommna(this.maxYField.getText()));

		double x1 = Double.parseDouble(StringUtil.removeCommna(this.minXField.getText()));
		double x2 =  Double.parseDouble(StringUtil.removeCommna(this.maxXField.getText()));
		this.panel.ZoomIn(this.zoomComboBox.getSelectionModel().getSelectedIndex(),x1,x2,y1, y2);
	}

	/**
	 * 그래프 확대시 Y축의 최대/최소값을 재설정한다.
	 * */
	@FXML
	public void changeZoomComboBox() {
		int index = this.zoomComboBox.getSelectionModel().getSelectedIndex();
		if(index==-1) index = 0;
		
		this.minYField.setText(panel.getMinValue(index));
		this.maxYField.setText(panel.getMaxValue(index));
	}

	/** leftAddList와 rightAddList에 저장된 값으로 그래프를 ReDraw한다. 
	 * @author : YOO YOUNGOK 
	 * @method  chartReDraw
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 19.
	 */
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



	/* ################### ############# Inner Class ############### ################### */	
	/**
	 * TimerCall
	 * Recording시간을 측정하기위한 Timer InnerClass	 * 
	 * @author : YOO YOUNGOK 
	 * @since 2018. 6. 19.
	 */
	public class TimerCall extends Timer{

		int hour=0;	
		int min=0;
		int sec=0;
		/**
		 * repeatPeriod 의 시간이 지날 때마다 execute() 메소드를 호출하도록 설정한다
		 * @param repeatPeriod(반복주기,ms)
		 * 예 : repeatPeriod = 1000 일 경우 1초
		 */
		public TimerCall(long repeatPeriod) {
			super(repeatPeriod);
		}

		/**
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
					sec++;	//1초마다 sec값을 증가시킨다.

					if(sec==60) {
						min++;
						sec=0;
						if(min==0) {
							hour++;
							min=0;
						}
					}		
					
					if(sec==recordingTime) {
						stopRecordingTimeView();
						GraphData.getInstance().saveData(CommandConst.DRIVER_NUMBEER);
						fxTimeLabel.setText("데이터 수집 완료!");
						
						System.out.println("onClickChartDraw Start");
						chartReDraw();
					}
					
					fxTimeLabel.setText(recordingTime+"후 완료예정입니다. => "+String.valueOf(hour)+" 시간 "+String.valueOf(min)+" 분 "+ String.valueOf(sec)+" 초");
				}
			});
		}

		/**
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
	/* ################### ############# Inner Class End ############### ################### */		
}

