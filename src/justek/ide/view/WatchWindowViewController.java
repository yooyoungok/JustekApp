package justek.ide.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


import java.io.IOException;
import java.util.HashMap;

import justek.ide.MainApp;
import justek.ide.model.CommandConst;
import justek.ide.model.RealTimeInfo;
import justek.ide.model.listener.RealTimeEventListener;
import justek.ide.model.listener.WatchNodeListener;
import justek.ide.model.manager.RequestManager;


public class WatchWindowViewController implements WatchNodeListener, RealTimeEventListener{
	
	static final String Tag = "WatchWindowViewController";
	
	private HashMap<String, WatchNodeController> paneMap;
	private MainApp mainApp;
 
	@FXML
	private VBox nameBox;

    
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
		this.initData();
		this.paneMap = new HashMap<>();
		
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("finalize");
		
	}

	private void initData() {
	    for(int i=0; i<20;i++) {
	    	 this.addPane();
	    }
	    System.out.println(Tag+":; initData");
	    RequestManager.getInstance().setRealTimeEventListener(this);
	}
	
	@FXML
	public void addPane() {
        try {
            // Load the fxml file and create a new stage for the popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/WatchNode.fxml"));
            AnchorPane nodePane = (AnchorPane) loader.load();
            WatchNodeController controller = loader.getController();
            controller.addListener(this);
//            VBox.setVgrow(nodePane, Priority.ALWAYS);

           this.nameBox.getChildren().add(nodePane);

        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	/*****
	 * WatchNodeListener
	 * 
	 ******/
	@Override
	public void selectSignal(String key, WatchNodeController node) {
		// TODO Auto-generated method stub
		if(this.paneMap.containsKey(key)) {
			System.out.println("WatchWindowViewController selectSignal alreay selected === "+key);
			return;
		}
		this.paneMap.put(key, node);
		System.out.println("WatchWindowViewController selectSignal ==="+key);
		
	}

	@Override
	public void deleteSignal(String key) {
		System.out.println(Tag +" :: deleteSignal == " +key);
		if(this.paneMap.containsKey(key)) {
			this.paneMap.remove(key);
		}
	}
	
	/*****
	 * RealTimeEventListener
	 * 
	 ******/	
	@Override
	public void realTimeDatarEvent(double Source) { 	}

	@Override
	public void realTimeDataInfoEvent(RealTimeInfo Source) {
//		System.out.println(Tag +" :: realTimeDataInfoEvent :: paneMap.size()=" + this.paneMap.size());
		
		System.out.println(Tag +" :: realTimeDataInfoEvent :: RealTimeInfo=" + Source.toString());
		
		String key = Source.DriverNo+"_"+CommandConst.AP;
		if(this.paneMap.containsKey(key)) {
			this.paneMap.get(key).setValue(Source.ActualPosition);
		}
		
		key = Source.DriverNo+"_"+CommandConst.PE;
		if(this.paneMap.containsKey(key)) {
			this.paneMap.get(key).setValue(Source.PosionError);
		}
		
		key = Source.DriverNo+"_"+CommandConst.TP;
		if(this.paneMap.containsKey(key)) {
			this.paneMap.get(key).setValue(Source.TargetPosition);
		}
		
		key = Source.DriverNo+"_"+CommandConst.AV;
		if(this.paneMap.containsKey(key)) {
			this.paneMap.get(key).setValue(Source.ActualVelocity);
		}
	}

	@Override
	public void autoScanEvent(ObservableList<TreeItem<String>> nodes) {	}

}
