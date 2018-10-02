/**
 * ------------------------------------------------------------------------------
 * @Project       : JustekApp
 * @Source        : ChartViewController.java
 * @Description  : TargetAcceleration","PositionError","TargetPosition", "ActualPosition","TargetVelocity","ActualVelocity"
 * 						���� 5���� ������ �׷����� ��Ÿ���� ������ ����� �м��Ѵ�. 
 * @Author        : YOUNGOK YOO
 * @Version       : v1.0
 * Copyright(c) 2018 JUSTEK All rights reserved
 *------------------------------------------------------------------------------
 *                  ��         ��         ��         ��                       
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                      DESCRIPTION                        
 * ----------  ------  --------------------------------------------------------- 
 * 2018.04.05   ������    �űԻ���                                     
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

	/** ������ Y�� Reference Line�� �̵��ϱ����� Slider */
	@FXML
	private Slider sliderY1;
	/** ������ Y�� Reference Line�� �̵��ϱ����� Slider */
	@FXML
	private Slider sliderY2; 
	/** ������ X�� Reference Line�� �̵��ϱ����� Slider */
	@FXML
	private Slider sliderX1; 
	/** ������ X�� Reference Line�� �̵��ϱ����� Slider */
	@FXML
	private Slider sliderX2; 

	/** Y���� �ּҰ� �ʵ� */
	@FXML
	private TextField minYField;
	/** Y���� �ִ밪 �ʵ� */
	@FXML
	private TextField maxYField;

	/** X���� �ּҰ� �ʵ� */
	@FXML
	private TextField minXField; 
	/** X���� �ִ밪 �ʵ� */
	@FXML
	private TextField maxXField; 
	/** Ȯ�� ��ư*/
	@FXML
	private Button zoomButton;
	/**������ ���� Reference Y1�� */
	@FXML
	private TextField LeftY1Field;
	/**������ ���� Reference Y2�� */
	@FXML
	private TextField LeftY2Field;
	/**������ ���� Reference Y1�� */
	@FXML
	private TextField RightY1Field;
	/**������ ���� Reference Y1�� */
	@FXML
	private TextField RightY2Field;
	/**X���� ���� Reference X1�� */
	@FXML
	private TextField X1Field;
	/**X���� ���� Reference X2�� */
	@FXML
	private TextField X2Field;
	/**������ Chart Name */
	@FXML
	private TextField chartNameField;
	/** ������ Unit ���� Field*/
	@FXML
	private TextField YRangeField;
	/** ������ Unit ���� Field*/
	@FXML
	private TextField Y2RangeField;
	/** X���� ���� Field*/
	@FXML
	private TextField XRangeField;
	/** ������ Unit ���� Field*/
	@FXML
	private TextField fxLeftUnitTextField;
	/** ������ Unit ���� Field*/
	@FXML
	private TextField fxRightUnitTextField;
	/** ChartName�� ������ ComboBox */
	@FXML
	private ComboBox<String> chartNameComboBox;
	/** Ȯ���� �׷������� �������� ���� ComboBox */
	@FXML
	private ComboBox<String> zoomComboBox;
	//	@FXML
	//	private ComboBox<String> seriesComboBox;
	//	@FXML
	//	private ComboBox<String> series2ComboBox;
	/** ���࿡ �߰��� ������ ������ �� �ִ� CheckComboBox */
	@FXML
	private CheckComboBox<String> fxLeftCheckComboBox;
	/** ���࿡ �߰��� ������ ������ �� �ִ� CheckComboBox */
	@FXML
	private CheckComboBox<String> fxRightCheckComboBox;
	@FXML
	private HBox fxHBox; 

	/** Recording �ҿ�ð� ǥ�� */
	@FXML
	private Label fxTimeLabel;	
	/** Recording progressBar */
	@FXML
	private ProgressBar processBar;
	/** Recording������ ǥ���ϴ� Info Label */
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

	/** �׷��� ������ ���� GraphPanel */
	private GraphPanel panel;
	/** JavaFX�� JFreeChart�� �׸��� ���� Viewer */
	private ChartViewer viewer;
	/** ���� Chart�� �׷��� ���� Chart Name */
	private ObservableList<String> list;
	/**  ���࿡ �׷��� ��������Ʈ */
	private ObservableList<String> leftAddList; 	
	/** ���࿡ �߰��� ��������Ʈ */
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
	 * �׷����� �׸��� ���� ComboBox Setting...
	 */
	public void setCheckComboBox() {
		this.fxLeftCheckComboBox = new CheckComboBox<String>(CommandConst.chartList);
		this.fxRightCheckComboBox =  new CheckComboBox<String>(CommandConst.chartList);
		this.fxHBox.getChildren().add(0, this.fxLeftCheckComboBox);
		this.fxHBox.getChildren().add(3, this.fxRightCheckComboBox);
		HBox.setMargin(this.fxLeftCheckComboBox,new Insets(0,0,0,60));
		//�׷����� �׸� ������ �����ϸ� ����Ʈ�� �߰��Ѵ�.
		fxLeftCheckComboBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
			public void onChanged(ListChangeListener.Change<? extends String> c) {
				leftAddList = fxLeftCheckComboBox.getCheckModel().getCheckedItems();
				System.out.println(leftAddList);
			}
		});
		//�׷����� �׸� ������ �����ϸ� ����Ʈ�� �߰��Ѵ�.
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
	 * �׷��� ������ �� �ش��ʵ�� ComboBox�� �Է��ϰ�, �ʱ�ȭ �Ѵ�.
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
	 * Reference Line�� �׸��� ���� Slider�� ���� �����ϰ� ChangeListener�� ����Ѵ�.
	 * Slider�� �̵��� ���� Reference���� �ش��ʵ忡 �Է��Ѵ�.
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
	 *  ����ڰ� �Է��� ���� Reference�� ������ Y���� Delta���� ����Ѵ�.
	 * @author : YOO YOUNGOK 
	 * @method  calculateReferenceY
	 * @return  String �����
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public String calculateReferenceY() {
		String result = "0";	// d,d1�� �����
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
	 * ����ڰ� �Է��� ���� Reference�� ������ Y���� Delta���� ����Ѵ�.
	 * @author : YOO YOUNGOK 
	 * @method  calculateReferenceY2
	 * @return
	 * @return  String �����
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public String calculateReferenceY2() {
		String result = "0"; // d,d1�� �����
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
	 * ����ڰ� �Է��� ���� Reference X�� Delta���� ����Ѵ�.	 * 
	 * @author : YOO YOUNGOK 
	 * @method  calculateReferenceX
	 * @return
	 * @return  String �����
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public String calculateReferenceX() {
		String result = "0"; // d,d1�� �����
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
	 * Recording�ð����� Start �� �ش����� Label�� Visible�� true�� ����	 * 
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
		this.fxTimeLabel.setText("Recording Time : 0 �ð� 0 �� 0��");
	}

	/**
	 * Recording�ð����� Stop �� �ش����� Label�� Visible�� false�� ����	 * 
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
	 * KeyEvent�� �ش��ϴ� �̺�Ʈ�� �����Ѵ�.	 * 
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
			//�׷��� �� ����
			if(event.getSource() == this.chartNameField) {
				panel.setRangeName(this.chartNameField.getText(), this.chartNameComboBox.getSelectionModel().getSelectedIndex());
			}
			//������ Y���� Referencer���� �����ϰ� �ش� Slider�� ��ġ�� �����Ѵ�.
			else if(event.getSource() == this.LeftY1Field) {
				Double value = Double.parseDouble(StringUtil.removeCommna(this.LeftY1Field.getText()));
				panel.setReferenceLineY(1, value);
				panel.setlostFocusX(1);
				panel.setlostFocusX(2);
				panel.setgetFocusY(1);
				panel.setlostFocusY(2);
			}
			//������ Y���� Referencer���� �����ϰ� �ش� Slider�� ��ġ�� �����Ѵ�.
			else if(event.getSource() == this.LeftY2Field) {
				Double value = Double.parseDouble(StringUtil.removeCommna(this.LeftY2Field.getText()));
				panel.setReferenceLineY(2, value);
				panel.setlostFocusX(1);
				panel.setlostFocusX(2);
				panel.setlostFocusY(1);
				panel.setgetFocusY(2);
			}
			//������ Y���� Referencer���� �����ϰ� �ش� Slider�� ��ġ�� �����Ѵ�.
			else if(event.getSource() == this.RightY1Field) {
				Double value = Double.parseDouble(StringUtil.removeCommna(this.RightY1Field.getText()));
				panel.setReferenceLineY2(1, value);
				panel.setlostFocusX(1);
				panel.setlostFocusX(2);
				panel.setgetFocusY(1);
				panel.setlostFocusY(2);
			}
			//������ Y���� Referencer���� �����ϰ� �ش� Slider�� ��ġ�� �����Ѵ�.
			else if(event.getSource() == this.RightY2Field) {
				Double value = Double.parseDouble(StringUtil.removeCommna(this.RightY2Field.getText()));
				panel.setReferenceLineY2(2, value);
				panel.setlostFocusX(1);
				panel.setlostFocusX(2);
				panel.setlostFocusY(1);
				panel.setgetFocusY(2);
			}
			//X���� Referencer���� �����ϰ� �ش� Slider�� ��ġ�� �����Ѵ�.
			else if(event.getSource() == this.X1Field) {
				Double value = Double.parseDouble(StringUtil.removeCommna(this.X1Field.getText()));
				panel.setReferenceLineX(1, value);
				panel.setgetFocusX(1);
				panel.setlostFocusX(2);
				panel.setlostFocusY(1);
				panel.setlostFocusY(2);
			}
			//X���� Referencer���� �����ϰ� �ش� Slider�� ��ġ�� �����Ѵ�.
			else if(event.getSource() == this.X2Field) {
				Double value = Double.parseDouble(StringUtil.removeCommna(this.X2Field.getText()));
				panel.setReferenceLineX(2, value);
				panel.setlostFocusX(1);
				panel.setgetFocusX(2);
				panel.setlostFocusY(1);
				panel.setlostFocusY(2);
			}
			//������ Unit�� �����Ѵ�.
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
			//������ Unit�� �����Ѵ�.
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
	 * ����ڰ� �Է��� ������ �׷����� Ȯ���Ѵ�.
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
	 * �׷��� Ȯ��� Y���� �ִ�/�ּҰ��� �缳���Ѵ�.
	 * */
	@FXML
	public void changeZoomComboBox() {
		int index = this.zoomComboBox.getSelectionModel().getSelectedIndex();
		if(index==-1) index = 0;
		
		this.minYField.setText(panel.getMinValue(index));
		this.maxYField.setText(panel.getMaxValue(index));
	}

	/** leftAddList�� rightAddList�� ����� ������ �׷����� ReDraw�Ѵ�. 
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
	 * Recording�ð��� �����ϱ����� Timer InnerClass	 * 
	 * @author : YOO YOUNGOK 
	 * @since 2018. 6. 19.
	 */
	public class TimerCall extends Timer{

		int hour=0;	
		int min=0;
		int sec=0;
		/**
		 * repeatPeriod �� �ð��� ���� ������ execute() �޼ҵ带 ȣ���ϵ��� �����Ѵ�
		 * @param repeatPeriod(�ݺ��ֱ�,ms)
		 * �� : repeatPeriod = 1000 �� ��� 1��
		 */
		public TimerCall(long repeatPeriod) {
			super(repeatPeriod);
		}

		/**
		 * ������ �ð��� ������ ������ ȣ��Ǵ� �޼ҵ�
		 */
		@Override
		public void execute() {
			Calendar cal = Calendar.getInstance();
			System.out.println("TimerCall ȣ��! " + cal.get(Calendar.HOUR_OF_DAY) + "�� " +
					cal.get(Calendar.MINUTE) + "�� " + cal.get(Calendar.SECOND) + "��");

			// Update the Label on the JavaFx Application Thread
			Platform.runLater(new Runnable()
			{
				@Override
				public void run()
				{
					sec++;	//1�ʸ��� sec���� ������Ų��.

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
						fxTimeLabel.setText("������ ���� �Ϸ�!");
						
						System.out.println("onClickChartDraw Start");
						chartReDraw();
					}
					
					fxTimeLabel.setText(recordingTime+"�� �ϷΌ���Դϴ�. => "+String.valueOf(hour)+" �ð� "+String.valueOf(min)+" �� "+ String.valueOf(sec)+" ��");
				}
			});
		}

		/**
		 * Ÿ�̸Ӱ� ����Ǿ��� ��� ȣ��ȴ�
		 * �ش� Ÿ�̸� ����ü�� �����ð����� ���� �ݺ��̱� ������ 
		 * SupinanTimer �� closeTimer() �� ȣ������ �ʴ��� �ش� �޼ҵ��
		 * ȣ������ �ʴ´�
		 */
		@Override
		public void stopTimer(TimerStopType type) {
			System.out.println("TimerCall ����:" + type.name());
		}
	}
	/* ################### ############# Inner Class End ############### ################### */		
}

