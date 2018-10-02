package justek.ide.chart.data;


import java.io.BufferedReader;
import java.io.FileReader;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.Arrangement;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.block.BlockContainer;
import org.jfree.chart.block.BorderArrangement;
import org.jfree.chart.block.EmptyBlock;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.CompositeTitle;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DefaultData {

	String[] acc = null;
	String[] pos = null;

	int speedLength;
	int speedLength2;
	public int resolution = 1;
	public XYSeries series1;
	public XYSeries series2;

	public String LeftChartName = "";
	public String RightChartName = "";

	public ObservableList<String> leftChartNames;
	public ObservableList<String> rightChartNames;
	public ObservableList<XYSeries> leftSeries;
	public ObservableList<XYSeries> rightSeries;
	public ObservableList<String[]> leftDataList;
	public ObservableList<String[]> rightDataList;

	private XYSeriesCollection leftDataSet;
	private XYSeriesCollection rightDataSet;

	private double leftUnit=1;
	private double rightUnit =1;

	//현재 사용하지 않음... 
	public XYDataset defaultData() {

		// create dataset 1...
		series1 = new XYSeries("ActualPosition");

		for (int i = 0; i < this.speedLength-1 ; i++) {
			series1.add(i, Double.parseDouble(acc[i])/this.leftUnit);
		}

		XYSeriesCollection collection = new XYSeriesCollection();
		collection.addSeries(series1);
		return collection;
	}


	//시리즈를 여러개 생성할 수 있도록 한다.
	public XYDataset createLeftDataSet() {
		// create dataset 1...
		if(this.leftChartNames==null) {
			this.leftChartNames = FXCollections.observableArrayList();
		}

		if(this.leftSeries==null) {
			this.leftSeries = FXCollections.observableArrayList();
		}

		this.leftChartNames.add(this.LeftChartName);

		XYSeries series = new XYSeries(LeftChartName);

		for (int i = 0; i < this.speedLength-1 ; i++) {
			series.add(i*resolution, Double.parseDouble(acc[i])/this.leftUnit);
		}

		XYSeriesCollection collection = new XYSeriesCollection();
		collection.addSeries(series);
		this.leftSeries.add(series);

		this.leftDataSet = collection;

		return leftDataSet;
	}

	//시리즈를 여러개 생성할 수 있도록 한다.
	public XYDataset createRightDataSet() {
		// create dataset 1...

		if(this.rightChartNames==null) {
			this.rightChartNames = FXCollections.observableArrayList();
		}

		if(this.rightSeries==null) {
			this.rightSeries = FXCollections.observableArrayList();
		}

		this.rightChartNames.add(this.RightChartName);

		XYSeries series = new XYSeries(RightChartName);

		for (int i = 0; i < this.speedLength-1 ; i++) {
			series.add(i*resolution, Double.parseDouble(pos[i])/this.leftUnit);
		}

		XYSeriesCollection collection = new XYSeriesCollection();
		collection.addSeries(series);
		this.rightSeries.add(series);

		this.rightDataSet = collection;

		return rightDataSet;
	}

	public XYDataset createDataset1() {

		// create dataset 1...
		series1 = new XYSeries(LeftChartName);

		for (int i = 0; i < this.speedLength-1 ; i++) {
			series1.add(i*resolution, Double.parseDouble(acc[i])/this.leftUnit);
		}

		XYSeriesCollection collection = new XYSeriesCollection();
		collection.addSeries(series1);
		return collection;
	}

	public XYDataset createDataset2() {

		// create dataset 2...
		series2 = new XYSeries(RightChartName);

		for (int i = 0; i < this.speedLength2-1 ; i++) {
			series2.add(i*resolution,Double.parseDouble(pos[i])/this.rightUnit);
		}

		XYSeriesCollection collection = new XYSeriesCollection();
		collection.addSeries(series2);
		return collection;
	}

	public void getPositionData() {
		try {
			BufferedReader uc = new BufferedReader(new FileReader("graph.csv"));
			String s ;
			String[] array;
			acc = new String[10000];
			pos = new String[10000];
			int i = 0;

			while((s=uc.readLine()) != null) {	
				array = s.split(",");
				acc[i] = array[0];
				pos[i] = array[1];
				i++;
			}
			speedLength = i;
			uc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getPosData() {
		try {

			BufferedReader uc = new BufferedReader(new FileReader("./home/pos.txt"));

			String s ;
			pos = new String[10000];
			int i = 0;

			while((s=uc.readLine()) != null) {	
				pos[i] = s;
				i++;
			}
			speedLength = i;
			uc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getAccData() {
		try {
			BufferedReader uc = new BufferedReader(new FileReader("./home/acc.txt"));

			String s ;
			acc = new String[10000];
			int i = 0;

			while((s=uc.readLine()) != null) {	
				acc[i] = s;
				i++;
			}
			speedLength = i;
			uc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cleaerSeries() {
		if(this.series1!=null) {
			this.series1.clear();
		}
		if(this.series2!=null) {
			this.series2.clear();
		}
	}

	//추가된 모든 시리즈를 삭제한다.
	public void cleaerAllSeries() {

		if(this.leftSeries!=null){
			for(XYSeries series:this.leftSeries) {
				series.clear();
			}
			
			this.leftSeries.clear();
		}
		if(this.rightSeries!=null){
			for(XYSeries series:this.rightSeries) {
				series.clear();
			}
			
			this.rightSeries.clear();
		}
	
	}

	public void addSeries(double value) {
		if(this.series1.getMaxX()>5000) {
			this.series1.remove(0);
		}
		this.series1.add(this.series1.getMaxX()+1,value);
	}

	public void getLeftChart() {
		try {
			BufferedReader uc = new BufferedReader(new FileReader("./home/"+this.LeftChartName+".txt"));

			String s ;
			acc = new String[100000];
			int i = 0;

			while((s=uc.readLine()) != null) {	
				acc[i] = s;
				i++;
			}
			speedLength = i;
			uc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getRightChart() {
		try {
			BufferedReader uc = new BufferedReader(new FileReader("./home/"+this.RightChartName+".txt"));

			String s ;
			pos = new String[100000];
			int i = 0;

			while((s=uc.readLine()) != null) {	
				pos[i] = s;
				i++;
			}
			speedLength2 = i;
			uc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getLeftChart(String chartName) {

		try {
			BufferedReader uc = new BufferedReader(new FileReader("./home/"+chartName+".txt"));

			String s ;
			acc = new String[100000];
			int i = 0;

			while((s=uc.readLine()) != null) {	
				acc[i] = s;
				i++;
			}
			speedLength = i;
			uc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getRightChart(String chartName) {

		try {
			BufferedReader uc = new BufferedReader(new FileReader("./home/"+chartName+".txt"));

			String s ;
			pos = new String[100000];
			int i = 0;

			while((s=uc.readLine()) != null) {	
				pos[i] = s;
				i++;
			}
			speedLength2 = i;
			uc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	//왼쪽 축의 시리즈 추가
	public XYDataset addLeftSeries(String chartName) {

		this.getLeftChart(chartName);
		
		XYSeries series = new XYSeries(chartName);
		XYSeriesCollection collection = new XYSeriesCollection();
		for (int i = 0; i < this.speedLength-1 ; i++) {
			series.add(i*resolution, Double.parseDouble(acc[i])/this.leftUnit);
		}
		
		System.out.println("addLeftSeries Unit "+ this.leftUnit);
		collection.addSeries(series);
		
		if(this.leftChartNames==null) {
			this.leftChartNames = FXCollections.observableArrayList();
		}
		
		this.leftChartNames.add(chartName);

		return collection;
	}

	//오른쪽 축의 시리즈 추가
	public XYDataset addRightSeries(String chartName) {
		
		this.getRightChart(chartName);

		XYSeries series = new XYSeries(chartName);
		XYSeriesCollection collection = new XYSeriesCollection();
		for (int i = 0; i < this.speedLength2-1 ; i++) {
			series.add(i*resolution, Double.parseDouble(pos[i])/this.rightUnit);
		}
		
		System.out.println("addRightSeries Unit "+ this.rightUnit);

		collection.addSeries(series);
		
		if(this.rightChartNames==null) {
			this.rightChartNames = FXCollections.observableArrayList();
		}
		this.rightChartNames.add(chartName);

		return collection;
	}

	public void setRightUnit(double value) {
		this.rightUnit = value;
	}

	public void setLeftUnit(double value) {
		this.leftUnit = value;
	}

	public XYDataset removeRightSeries(String value) {
		int index = this.rightChartNames.indexOf(value);
		this.rightChartNames.remove(value);
		this.rightSeries.remove(index);
		this.rightDataSet.removeAllSeries();
		for(XYSeries series:this.rightSeries) {
			this.rightDataSet.addSeries(series);
		}
		return this.rightDataSet;
	}

	public XYDataset removeLeftSeries(String value) {
		int index = this.leftChartNames.indexOf(value);
		this.leftChartNames.remove(value);
		this.leftSeries.remove(index);
		this.leftDataSet.removeAllSeries();
		for(XYSeries series:this.leftSeries) {
			this.leftDataSet.addSeries(series);
		}
		return this.leftDataSet;
	}
}

