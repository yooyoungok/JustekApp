package justek.ide.chart.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.text.NumberFormat;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.event.ChartProgressEvent;
import org.jfree.chart.event.ChartProgressListener;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.chart.fx.interaction.ChartMouseEventFX;
import org.jfree.chart.fx.interaction.ChartMouseListenerFX;
import org.jfree.chart.fx.overlay.CrosshairOverlayFX;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.Crosshair;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.Range;
import org.jfree.data.xy.XYDataset;

import javafx.scene.control.Slider;
import justek.ide.chart.data.DefaultData;
import justek.ide.chart.data.DefaultTriggerData;
import justek.ide.model.CommandConst;
import justek.ide.utils.StringUtil;

public class GraphTriggerPanel implements ChartProgressListener,ChartMouseListenerFX  {

	public JFreeChart chart;
	ChartPanel graphPanel;
	public DefaultTriggerData data;
	ChartViewer viewer;
	
	CrosshairOverlayFX crosshairOverlay;
	Crosshair xhair1;
	Crosshair xhair2;
	Crosshair yhair1;
	Crosshair yhair2;
	double yhair1_y2;
	double yhair2_y2;
	int data_index=1;
	
	private ArrayList<String> rangeList;
	private ArrayList<Integer> leftRangeList;
	private ArrayList<Integer> rightRangeList;
	
	//start ::  MyChartMouseListener
	 class MyChartMouseListener implements ChartMouseListener{

	        ChartPanel panel;

	        /**
	         * Creates a new mouse listener.
	         *
	         * @param panel  the panel.
	         */
	        public MyChartMouseListener(ChartPanel panel) {
	            this.panel = panel;
	        }
	        /**
	         * Callback method for receiving notification of a mouse click on a
	         * chart.
	         *
	         * @param event  information about the event.
	         */
	        public void chartMouseClicked(ChartMouseEvent event) {
	        	int x = event.getTrigger().getX();
	        	int y = event.getTrigger().getY();

	        	// the following translation takes account of the fact that the
	        	// chart image may have been scaled up or down to fit the panel...
	        	Point2D p = this.panel.translateScreenToJava2D(new Point(x, y));

	        	// now convert the Java2D coordinate to axis coordinates...
	        	XYPlot plot = (XYPlot) this.panel.getChart().getPlot();
	        	ChartRenderingInfo info = this.panel.getChartRenderingInfo();
	        	Rectangle2D dataArea = info.getPlotInfo().getDataArea();
	        	double xx = plot.getDomainAxis().java2DToValue(p.getX(), dataArea,
	        			plot.getDomainAxisEdge());
	        	double yy = plot.getRangeAxis().java2DToValue(p.getY(), dataArea,
	        			plot.getRangeAxisEdge());

	        	// just for fun, lets convert the axis coordinates back to component
	        	// coordinates...
	        	ValueAxis domainAxis = plot.getDomainAxis();
	        	ValueAxis rangeAxis = plot.getRangeAxis();
	        	double xxx = domainAxis.valueToJava2D(xx, dataArea,
	        			plot.getDomainAxisEdge());
	        	double yyy = rangeAxis.valueToJava2D(yy, dataArea,
	        			plot.getRangeAxisEdge());

	        	Point2D p2 = this.panel.translateJava2DToScreen(
	        			new Point2D.Double(xxx, yyy));
	        	System.out.println("Mouse coordinates are (" + x + ", " + y
	        			+ "), in data space = (" + xx + ", " + yy + ").");
	        	System.out.println("--> (" + p2.getX() + ", " + p2.getY() + ")");
	        	
	        	data.addSeries(100);
	  }

	        /**
	         * Callback method for receiving notification of a mouse movement on a
	         * chart.
	         *
	         * @param event  information about the event.
	         */
	        public void chartMouseMoved(ChartMouseEvent event) {
	            // ignore
	        }
	        
	    }
	 
	//end ::  MyChartMouseListener
	
	public void ZoomIn(int series,double x1,double x2,double y1,double y2) {
        updateZoom(series,x1,x2,y1,y2);
	}
	
	public void updateZoom(int series,double x1, double x2, double y1,double y2) {
    	XYPlot plot = (XYPlot) this.chart.getPlot();
        double domainMin = plot.getDomainAxis().getRange().getLowerBound();
        double domainMax = plot.getDomainAxis().getRange().getUpperBound();
        System.out.println(domainMin + "," + domainMax);

        double rangeMin = plot.getRangeAxis(series).getRange().getLowerBound();
        double rangeMax = plot.getRangeAxis(series).getRange().getUpperBound();
        System.out.println(rangeMin + "," + rangeMax);                  

        plot.getRangeAxis(series).setRange(y1, y2);
        if(y1==0 && y2 ==0) {
        		plot.getRangeAxis(series).setRange(rangeMin, rangeMax);
        }
        else {
        		plot.getRangeAxis(series).setRange(y1, y2);
        }

        if(x1==0 && x2 ==0) {
        		plot.getDomainAxis().setRange(domainMin, domainMax);
        }
        else {
        		plot.getDomainAxis().setRange(x1, x2);
        }
    }
	
	public void updateZoom(double x1, double x2, double y1,double y2) {
    	XYPlot plot = (XYPlot) this.chart.getPlot();
        double domainMin = plot.getDomainAxis().getRange().getLowerBound();
        double domainMax = plot.getDomainAxis().getRange().getUpperBound();
        System.out.println(domainMin + "," + domainMax);

        double rangeMin = plot.getRangeAxis().getRange().getLowerBound();
        double rangeMax = plot.getRangeAxis().getRange().getUpperBound();
        System.out.println(rangeMin + "," + rangeMax);                  

        plot.getRangeAxis().setRange(y1, y2);
        if(y1==0 && y2 ==0) {
        		plot.getRangeAxis().setRange(rangeMin, rangeMax);
        }
        else {
        		plot.getRangeAxis().setRange(y1, y2);
        }

        if(x1==0 && x2 ==0) {
        		plot.getDomainAxis().setRange(domainMin, domainMax);
        }
        else {
        		plot.getDomainAxis().setRange(x1, x2);
        }
    }
    
	public void setSliderData(Slider slider, int index) {
		System.out.println("setSliderData index == "+index);
        XYPlot plot = (XYPlot) this.chart.getPlot();
//		if(plot.getDataset(index) ==null) {
//    		System.out.println("setSliderData index == null ::"+index);       
//			return;
//		}
		
        double value = slider.getValue();
        ValueAxis domainAxis = plot.getDomainAxis();
        ValueAxis rangeAxis = plot.getRangeAxis();
        ValueAxis rangeAxis2 = plot.getRangeAxis(1);
        Range range = domainAxis.getRange();
        Range range2 = rangeAxis.getRange();
        Range range2_2= rangeAxis2.getRange();
        
        double c = domainAxis.getLowerBound()
                   + (value / 100.0) * range.getLength();
        
        double d = rangeAxis.getLowerBound()
                + (value / 100.0) * range2.getLength();
        
        double d2 = rangeAxis2.getLowerBound()
                + (value / 100.0) * range2_2.getLength();
        
        if(index==1) {
        		this.xhair1.setValue(c);
        }
        else if(index==2) {
        		this.xhair2.setValue(c);
        }
        else if(index==3) {
        		this.yhair1.setValue(d);
        		 this.yhair1_y2 = d2;

        }
        else if(index==4) {
        		this.yhair2.setValue(d);
        		this.yhair2_y2 = d2;
        }
	}
	
	public void chartProgress(ChartProgressEvent event) {
		if (event.getType() != ChartProgressEvent.DRAWING_FINISHED) {
			return;
		}
		
		if (this.graphPanel != null) {
            JFreeChart c = this.chart;
            if (c != null) {
                XYPlot plot = (XYPlot) c.getPlot();
                XYDataset dataset = plot.getDataset();
                double xx = plot.getDomainCrosshairValue();
            }
        }
    }
	
	public void setReferenceLineY2(int i, double d) {
    	XYPlot plot = (XYPlot) this.chart.getPlot();
    	ChartRenderingInfo info = this.viewer.getRenderingInfo();
//    	ChartRenderingInfo info = this.graphPanel.getChartRenderingInfo();
    	Rectangle2D dataArea = info.getPlotInfo().getDataArea();

    	// just for fun, lets convert the axis coordinates back to component
    	// coordinates...
    	ValueAxis rangeAxis = plot.getRangeAxis(1);
    	double yyy = rangeAxis.valueToJava2D(d, dataArea,
    			plot.getRangeAxisEdge());
    	
    	double yy = plot.getRangeAxis().java2DToValue(yyy, dataArea,
    			plot.getRangeAxisEdge());
    	System.out.println("setReferenceLineY2 == "+yy);
    	
		if(i==1) {
			this.yhair1.setValue(yy);
		}
		else if(i==2) {
			this.yhair2.setValue(yy);
		}
    	
	}
	
	public void setReferenceLineY(int i,double d) {
    	XYPlot plot = (XYPlot) this.chart.getPlot();
    	ChartRenderingInfo info = this.viewer.getRenderingInfo();
//    	ChartRenderingInfo info = this.graphPanel.getChartRenderingInfo();
    	Rectangle2D dataArea = info.getPlotInfo().getDataArea();
    	
    	ValueAxis rangeAxis = plot.getRangeAxis(0);
    	double yyy = rangeAxis.valueToJava2D(d, dataArea,
    			plot.getRangeAxisEdge());
    	
    	double yy = plot.getRangeAxis(1).java2DToValue(yyy, dataArea,
    			plot.getRangeAxisEdge());
    	System.out.println("setReferenceLineY == "+yy);
    	
		if(i==1) {
			this.yhair1.setValue(d);
			this.yhair1_y2 = yy;
		}
		else if(i==2) {
			this.yhair2.setValue(d);
			this.yhair2_y2 = yy;
		}
	}
	
	public void setReferenceLineX(int i,double d) {
		if(i==1) {
			this.xhair1.setValue(d);
		}
		else if(i==2) {
			this.xhair2.setValue(d);
		}
	}
	
	public void setgetFocusY(int i) {
		if(i==1) {
			this.yhair1.setPaint(Color.yellow);
		}
		else if(i==2) {
			this.yhair2.setPaint(Color.yellow);
		}
	}
	
	public void setgetFocusX(int i) {
		if(i==1) {
			this.xhair1.setPaint(Color.yellow);
		}
		else if(i==2) {
			this.xhair2.setPaint(Color.yellow);
		}
	}
	
	public void setlostFocusY(int i) {
		if(i==1) {
			this.yhair1.setPaint(Color.DARK_GRAY);
		}
		else if(i==2) {
			this.yhair2.setPaint(Color.DARK_GRAY);
		}
	}
	
	public void setlostFocusX(int i) {
		if(i==1) {
			this.xhair1.setPaint(Color.DARK_GRAY);
		}
		else if(i==2) {
			this.xhair2.setPaint(Color.DARK_GRAY);
		}
	}
	
	
	public void setRangeName(String name,int index) {
        XYPlot plot = (XYPlot) this.chart.getPlot();
        ValueAxis rangeAxis = plot.getRangeAxis(index);
        rangeAxis.setAttributedLabel(name);
	}
	
	public String getMaxValue(int series) {
		String value;
		
//		NumberFormat f = NumberFormat.getInstance();
//		f.setGroupingUsed(false);
		
        NumberFormat f = NumberFormat.getInstance();
        f.setMaximumFractionDigits(5) ;
        XYPlot plot = (XYPlot) this.chart.getPlot();
        ValueAxis rangeAxis = plot.getRangeAxis(series);
        double d = rangeAxis.getUpperBound();
//		value = Double.toString(d);
        value = f.format(d);
		return value;
	}
	
	public String getMaxValue() {
		String value;
		
        NumberFormat f = NumberFormat.getInstance();
        f.setMaximumFractionDigits(5) ;
        XYPlot plot = (XYPlot) this.chart.getPlot();
        ValueAxis rangeAxis = plot.getRangeAxis();
        double d = rangeAxis.getUpperBound();
//		value = Double.toString(d);
        value = f.format(d);
		return value;
	}
	
	public String getMinValue(int series) {
		System.out.println("getMinValue = " + series);
		String value;
        NumberFormat f = NumberFormat.getInstance();
        f.setMaximumFractionDigits(5) ;
        XYPlot plot = (XYPlot) this.chart.getPlot();
        ValueAxis rangeAxis = plot.getRangeAxis(series);
        double d = rangeAxis.getLowerBound();
//		value = Double.toString(d);
		value = f.format(d);
		return value;
	}
	
	public String getMinValue() {
		String value;
        NumberFormat f = NumberFormat.getInstance();
        f.setMaximumFractionDigits(5) ;
        XYPlot plot = (XYPlot) this.chart.getPlot();
        ValueAxis rangeAxis = plot.getRangeAxis();
        double d = rangeAxis.getLowerBound();
//		value = Double.toString(d);
		value = f.format(d);
		return value;
	}
	
	public String getMaxDomain() {
		String value;
        NumberFormat f = NumberFormat.getInstance();
        f.setMaximumFractionDigits(5) ;
        XYPlot plot = (XYPlot) this.chart.getPlot();
        ValueAxis rangeAxis = plot.getDomainAxis();
        double d = rangeAxis.getUpperBound();
//		value = Double.toString(d);
		value = f.format(d);
		return value;
	}
	
	public String getMinDomain() {
		String value;
        NumberFormat f = NumberFormat.getInstance();
        f.setMaximumFractionDigits(5) ;
        XYPlot plot = (XYPlot) this.chart.getPlot();
        ValueAxis rangeAxis = plot.getDomainAxis();
        double d = rangeAxis.getLowerBound();
//		value = Double.toString(d);
        value = f.format(d);
		return value;
	}
	
	public double getDomainValue(int i) {
		double value = 0;
		if(i==1) {
			value=this.xhair1.getValue();	
		}
		else if(i==2) {
			value=this.xhair2.getValue();
		}
		return value;
	}
	
	public double getRangeValue(int i) {
		double value = 0;
		if(i==1) {
			value=this.yhair1.getValue();	
		}
		else if(i==2) {
			value=this.yhair2.getValue();
		}
		return value;
	}
	
	public double getRangeValue2(int i) {
		double value = 0;
		if(i==1) {
			value= this.yhair1_y2;
		}
		else if(i==2) {
			value= this.yhair2_y2;
		}
		return value;
	}
	
	
	public void setTickUnit(int index,double value) {
		if(index==0) {
			this.data.setLeftUnit(value);
		}
		else if(index==1) {
			this.data.setRightUnit(value);
		}
		
		this.data_index = 2;
//        XYPlot plot = (XYPlot) chart.getPlot();
//		NumberAxis range = (NumberAxis)plot.getRangeAxis(index);
//		range.setTickUnit(new NumberTickUnit(value));
	}
	
	public ArrayList<String> getRangeList(){
		return this.rangeList;
	}
	
	/**
	 * Create the panel.
	 */
	public GraphTriggerPanel() { }

	
    /**
     * @author : YOO YOUNGOK 
     * @method  createChart
     * @return
     * @return  JFreeChart
     * @exception 
     * @see
     * @since 2018. 6. 25.
     */
    public JFreeChart createChart() {
    	
    	this.rangeList = new ArrayList<>();
    	
    	if(this.data==null) {
    		this.data = new DefaultTriggerData();
    	}   	
    	
    	data.getLeftChart();
    	data.getRightChart();
    	
        this.chart = ChartFactory.createXYLineChart("Motion Chart",
                "Time(ms)",data.LeftChartName, this.data.createDataset1(), PlotOrientation.VERTICAL, true, true, false);
        
//        this.chart = ChartFactory.createXYLineChart("Motion Chart",
//                "Time(ms)",data.LeftChartName, this.data.createLeftDataSet(), PlotOrientation.VERTICAL, true, true, false);
        
        this.chart.getLegend(0).setVisible(true); //Legend를 보이도록 설정!
        
        XYPlot plot = (XYPlot) chart.getPlot();
//        plot.getDomainAxis().setAutoRange(true);
//        plot.getRangeAxis().setAutoRange(true);
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.setDomainCrosshairLockedOnData(false);

        NumberAxis axis2 = new NumberAxis(data.RightChartName);
        axis2.setAutoRange(true);
        plot.setRangeAxis(1, axis2);
        plot.setDataset(1,this.data.createDataset2()); 
//        plot.setDataset(1,this.data.createRightDataSet()); //add 2018.06.25
        plot.mapDatasetToRangeAxis(1, 1);
        
        plot.getRenderer(0).setSeriesPaint(0, Color.red);
  
        XYItemRenderer renderer2 = new StandardXYItemRenderer();
        renderer2.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        plot.setRenderer(1, renderer2);
        plot.getRenderer(1).setSeriesPaint(0, Color.blue);
        
        this.rangeList.add(plot.getRangeAxis(0).getLabel());
        this.rangeList.add(plot.getRangeAxis(1).getLabel());
        
        plot.getRangeAxis(0).setLabelPaint(Color.red);
        plot.getRangeAxis(1).setLabelPaint(Color.blue);
        
//      StandardChartTheme theme = (StandardChartTheme)StandardChartTheme.createDarknessTheme();
//      theme.apply(chart);
        return chart;
    }

    
 public JFreeChart createTriggerChart(String Source) {
    	
    	this.rangeList = new ArrayList<>();
    	
    	if(this.data==null) {
    		this.data = new DefaultTriggerData();
    	}   	
    	
    	data.getLeftChart();
    	
        this.chart = ChartFactory.createXYLineChart("Motion Chart",
                "Time(ms)",Source, this.data.createDataset1(), PlotOrientation.VERTICAL, true, true, false);
        
        this.chart.getLegend(0).setVisible(false);
        
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.getDomainAxis().setAutoRange(true);
        plot.getRangeAxis().setAutoRange(true);        
        plot.setDomainCrosshairLockedOnData(false);
        plot.getRenderer(0).setSeriesPaint(0, Color.red);
        plot.getRangeAxis(0).setLabelPaint(Color.red);
        
        this.rangeList.add(plot.getRangeAxis(0).getLabel());

        return chart;
    }
 
    public JFreeChart defaultCreateChart() {
    	
    	this.rangeList = new ArrayList<>();
    	
    	this.data = new DefaultTriggerData();
    	
        this.chart = ChartFactory.createXYLineChart("Motion Chart",
                "Time(ms)", "ActualPosition",null, PlotOrientation.VERTICAL, true, true, false);
        
        this.chart.getLegend(0).setVisible(false);
        
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.getDomainAxis().setAutoRange(true);
        plot.getRangeAxis().setAutoRange(true);
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.setDomainCrosshairLockedOnData(false);

        NumberAxis axis2 = new NumberAxis("Position Error");
        axis2.setAutoRange(true);
        plot.setRangeAxis(1, axis2);
        plot.setDataset(1,this.data.createDataset2());
        plot.mapDatasetToRangeAxis(1, 1);
        
//        plot.getRenderer(0).setSeriesPaint(0, Color.red);
  
        XYItemRenderer renderer2 = new StandardXYItemRenderer();
//        renderer2.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
        renderer2.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        plot.setRenderer(1, renderer2);
//        plot.getRenderer(1).setSeriesPaint(0, Color.blue);
        
        this.rangeList.add(plot.getRangeAxis(0).getLabel());
        this.rangeList.add(plot.getRangeAxis(1).getLabel());
        
//        plot.getRangeAxis(0).setLabelPaint(Color.red);
//        plot.getRangeAxis(1).setLabelPaint(Color.blue);

        return chart;
    }
   
    
    
    public void addCrossHair(ChartViewer viewer) {
    	this.viewer = viewer;
        crosshairOverlay = new CrosshairOverlayFX();
        viewer.addChartMouseListener(this);
        this.xhair1 = new Crosshair();
        xhair1.setPaint(Color.DARK_GRAY);
        this.xhair1.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1, new float[]{2.0f, 2.0f}, 0));
        this.xhair1.setLabelVisible(true);
        this.xhair1.setValue(0);
        
        this.xhair2 = new Crosshair();
        xhair2.setPaint(Color.DARK_GRAY);
        this.xhair2.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1, new float[]{2.0f, 2.0f}, 0));
        this.xhair2.setLabelVisible(true);
        this.xhair2.setValue(0);
        
        this.yhair1 = new Crosshair();
        yhair1.setPaint(Color.YELLOW);
        this.yhair1.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1, new float[] {2.0f, 2.0f}, 0));
        this.yhair1.setLabelVisible(true);
        String minValue = StringUtil.removeCommna(this.getMinValue());
        if(minValue==null) System.out.println("minValue is Null");
        else this.yhair1.setValue(Double.parseDouble(minValue));
        
        this.yhair2 = new Crosshair();
        yhair2.setPaint(Color.YELLOW);
        this.yhair2.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1, new float[] {2.0f, 2.0f}, 0));
        this.yhair2.setLabelVisible(true);
        String minValue1 = StringUtil.removeCommna(this.getMinValue());
        if(minValue1==null) System.out.println("minValue is Null");
        else this.yhair2.setValue(Double.parseDouble(minValue1));
        this.yhair2.setValue(Double.parseDouble(StringUtil.removeCommna(this.getMinValue())));
        
        crosshairOverlay.addDomainCrosshair(xhair1);
        crosshairOverlay.addRangeCrosshair(yhair1);
        crosshairOverlay.addDomainCrosshair(xhair2);
        crosshairOverlay.addRangeCrosshair(yhair2);
        viewer.getCanvas().addOverlay(crosshairOverlay);
    }
    
    public void removeCrossHair(ChartViewer viewer) {
    	if(this.crosshairOverlay!=null) {
    		viewer.getCanvas().removeOverlay(this.crosshairOverlay);
    	}
    }
    
    public void setTimeUnit(String resolution) {
    	this.data.resolution = Integer.parseInt(resolution);
    }
    
    public void clearSeries() {
    	this.data.cleaerSeries();
    }
    
    /**
     * @author : YOO YOUNGOK 
     * @method  addRightDataSet
     * @param name
     * @return  void
     * @exception 
     * @see
     * @since 2018. 6. 27.
     */
    public void addRightDataSet(String name) {
    	this.data.getRightChart(name);
    	XYDataset dataSet = this.data.addRightSeries(name);
        XYPlot plot = (XYPlot) chart.getPlot();
        this.data_index++;
        plot.setDataset(this.data_index, dataSet);
        plot.setRangeAxis(data_index, new NumberAxis(name));
        plot.mapDatasetToRangeAxis(data_index,data_index);  
        this.rangeList.add(plot.getRangeAxis(data_index).getLabel());
        plot.setRangeAxisLocation(data_index, AxisLocation.BOTTOM_OR_RIGHT);
        
        plot.getRangeAxis(data_index).setAutoRange(true);
        
        XYItemRenderer renderer2 = new StandardXYItemRenderer();
        renderer2.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        plot.setRenderer(data_index, renderer2);
        
        plot.getRenderer(data_index).setSeriesPaint(0,CommandConst.COLOR_LIST.get(1));
        if(this.rightRangeList==null) {
        	this.rightRangeList = new ArrayList<>();
        }
        int index = this.data_index;
        this.rightRangeList.add(index);
    }
    
    /**
     * @author : YOO YOUNGOK 
     * @method  addLeftDataSet
     * @param name
     * @return  void
     * @exception 
     * @see
     * @since 2018. 6. 27.
     */
    public void addLeftDataSet(String name) {
    	this.data.getLeftChart(name);
    	XYDataset dataSet = this.data.addLeftSeries(name);
        XYPlot plot = (XYPlot) chart.getPlot();
        this.data_index++;
        plot.setDataset(this.data_index, dataSet);
        plot.setRangeAxis(data_index, new NumberAxis(name));
        plot.mapDatasetToRangeAxis(data_index,data_index);  
        this.rangeList.add(plot.getRangeAxis(data_index).getLabel());
        plot.setRangeAxisLocation(data_index, AxisLocation.BOTTOM_OR_LEFT);
        
        plot.getRangeAxis(data_index).setAutoRange(true);
        
        XYItemRenderer renderer2 = new StandardXYItemRenderer();
        renderer2.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        plot.setRenderer(data_index, renderer2);
        plot.getRenderer(data_index).setSeriesPaint(0,CommandConst.COLOR_LIST.get(0));
        if(this.leftRangeList==null) {
        	this.leftRangeList = new ArrayList<>();
        }
        int index = this.data_index;
        this.leftRangeList.add(index);
    }    
    
    public void removeRightSeries(String value) {
    	XYDataset dataSet = this.data.removeRightSeries(value);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDataset(1, dataSet);
    }

    /**
     *  A mouse listener that can receive event notifications from a (JavaFX) 
     *  @param event  event information (never {@code null}).    
     */
    
	@Override
	public void chartMouseClicked(ChartMouseEventFX event) {
//		double x = event.getTrigger().getX();
//    	double y = event.getTrigger().getY();
//
//    	// the following translation takes account of the fact that the
//    	// chart image may have been scaled up or down to fit the panel...
////    	Point2D p = this.chart.translateScreenToJava2D(new Point(x, y));
////    	Point2D p = this.viewer.tr
//    	// now convert the Java2D coordinate to axis coordinates...
//    	XYPlot plot = (XYPlot) this.chart.getPlot();
//    	ChartRenderingInfo info = this.viewer.getRenderingInfo();
//    	Rectangle2D dataArea = info.getPlotInfo().getDataArea();
//    	double xx = plot.getDomainAxis().java2DToValue(x, dataArea,
//    			plot.getDomainAxisEdge());
//    	double yy = plot.getRangeAxis().java2DToValue(y, dataArea,
//    			plot.getRangeAxisEdge());
//
//    	System.out.println("Mouse coordinates are (" + x + ", " + y
//    			+ "), in data space = (" + xx + ", " + yy + ").");
//    	this.yhair1.setValue(yy);
	}

	@Override
	public void chartMouseMoved(ChartMouseEventFX event) {

    	
	}
}

