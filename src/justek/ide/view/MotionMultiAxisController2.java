package justek.ide.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import justek.ide.MainApp;
import justek.ide.model.CommandConst;
import justek.ide.model.RealTimeInfo;
import justek.ide.model.listener.MultiAxisNodeListener;
import justek.ide.model.listener.NodeRealDataUpdateListener;
import justek.ide.model.listener.RealTimeEventListener;
import justek.ide.model.listener.TreeEventListener;
import justek.ide.model.manager.NetworkServerManager;
import justek.ide.model.manager.RequestManager;
import justek.ide.utils.StringUtil;

public class MotionMultiAxisController2 implements MultiAxisNodeListener,RealTimeEventListener {

	static final String Tag = "MotionMultiAxisController2";

	ObservableList<String> axisName1ComboList = FXCollections
			.observableArrayList("None","DriveNG02","G03 a01","G03.a02","DriveNG01","G03.a04");
	ObservableList<String> axisName2ComboList = FXCollections
			.observableArrayList("None","DriveNG02","G03 a01","G03.a02","DriveNG01","G03.a04");
	ObservableList<String> axisName3ComboList = FXCollections
			.observableArrayList("None","DriveNG02","G03 a01","G03.a02","DriveNG01","G03.a04");
	ObservableList<String> axisName4ComboList = FXCollections
			.observableArrayList("None","DriveNG02","G03 a01","G03.a02","DriveNG01","G03.a04");
	ObservableList<String> opMode1ComboList = FXCollections
			.observableArrayList("Position","Velocity","Current","Stepper");
	ObservableList<String> opMode2ComboList = FXCollections
			.observableArrayList("Position","Velocity","Current","Stepper");
	ObservableList<String> opMode3ComboList = FXCollections
			.observableArrayList("Position","Velocity","Current","Stepper");
	ObservableList<String> opMode4ComboList = FXCollections
			.observableArrayList("Position","Velocity","Current","Stepper");

	@FXML // fx:id="terminalPane"
	private AnchorPane terminalPane; // Value injected by FXMLLoader

	@FXML // fx:id="nodeHBox"
	private HBox nodeHBox; // Value injected by FXMLLoader

    @FXML
    private CheckBox fxAbsRepCheckBox;

    @FXML
    private CheckBox fxRelRepCheckBox;
    

	private HashMap<VBox, multiAxisComponent> mNodeMap;
	private HashMap<String, Boolean> mSelectedDriverMap;
	private ObservableList<NodeRealDataUpdateListener> mListenerList;
	private ObservableList<String> mDriveList;
	private MainApp mainApp;

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

	} 

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		mNodeMap = new HashMap<>();
		mSelectedDriverMap = new HashMap<>();
		mListenerList =  FXCollections.observableArrayList();
		mDriveList =  FXCollections.observableArrayList();
		
		RequestManager.getInstance().setRealTimeEventListener(this);

	}

	private void addTerminalPane() {
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ConsoleView.fxml"));
			AnchorPane lTerminalPane = (AnchorPane) loader.load();
			ConsoleViewController controller = loader.getController();

			AnchorPane.setTopAnchor(lTerminalPane,0.0); // obviously provide your own constraints
			AnchorPane.setBottomAnchor(lTerminalPane,0.0);
			AnchorPane.setRightAnchor(lTerminalPane,0.0);
			AnchorPane.setLeftAnchor(lTerminalPane,0.0);

			this.terminalPane.getChildren().add(lTerminalPane);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@FXML
	public void addPane() {
		
		int count = this.nodeHBox.getChildren().size();
		if(count-1==CommandConst.driverList.size()) return;
		
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/multiAxisComponent.fxml"));
			VBox nodePane = (VBox) loader.load();
			multiAxisComponent controller = loader.getController();
			controller.setMultiAxisNodeListener(this);
			controller.setNode(nodePane);
			this.nodeHBox.getChildren().add(nodePane);
			this.mListenerList.add(controller);
			this.mNodeMap.put(nodePane, controller);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** 추가된 모든 드라이버의 PTP Absolute를 실행한다. */
	@FXML
	public void onClickAllPTPAbsoluteOne() {
		for(NodeRealDataUpdateListener listener :mListenerList) {
			multiAxisComponent component = (multiAxisComponent)listener;
			component.handleAbsolute1(null);
		}
	}
	
	@FXML
	public void onClickAllPTPAbsoluteTwo() {
		for(NodeRealDataUpdateListener listener :mListenerList) {
			multiAxisComponent component = (multiAxisComponent)listener;
			component.handleAbsolute2(null);
		}
	}
	
	/** 추가된 모든 드라이버의 PTP Relative Left를 실행한다. */
	@FXML
	public void onClickAllPTPRelativeLeft() {
		for(NodeRealDataUpdateListener listener :mListenerList) {
			multiAxisComponent component = (multiAxisComponent)listener;
			component.handleRelativeLeft(null);
		}
	}
	
	/** 추가된 모든 드라이버의 PTP Relative Left를 실행한다. */
	@FXML
	public void onClickAllPTPRelativeRight() {
		for(NodeRealDataUpdateListener listener :mListenerList) {
			multiAxisComponent component = (multiAxisComponent)listener;
			component.handleRelativeRight(null);
		}
	}
	
	@FXML
	public void onClickAllJogRight() {
		for(NodeRealDataUpdateListener listener :mListenerList) {
			multiAxisComponent component = (multiAxisComponent)listener;
			component.handleJogRight();
		}
	}
	
	@FXML
	public void onClickAllJogLeft() {
		for(NodeRealDataUpdateListener listener :mListenerList) {
			multiAxisComponent component = (multiAxisComponent)listener;
			component.handleJogLeft();
		}
	}
	
	
	@FXML
	void onClickEnable(ActionEvent event) {
		NetworkServerManager.serverOn("0");
//		for(NodeRealDataUpdateListener listener :mListenerList) {
//			multiAxisComponent component = (multiAxisComponent)listener;
//			component.onClickEnable(event);
//		}
	}

	@FXML
	void onClickDisable(ActionEvent event) {
		NetworkServerManager.serverOff("0");
//		for(NodeRealDataUpdateListener listener :mListenerList) {
//			multiAxisComponent component = (multiAxisComponent)listener;
//			component.onClickDisable(event);
//		}
	}

	@FXML
	void onClickHome(ActionEvent event) { 
		for(NodeRealDataUpdateListener listener :mListenerList) {
			multiAxisComponent component = (multiAxisComponent)listener;
			component.onClickHome(event);
		}
	}

	@FXML
	void onClickStop(ActionEvent event) {
		for(NodeRealDataUpdateListener listener :mListenerList) {
			multiAxisComponent component = (multiAxisComponent)listener;
			component.onClickStop(event);
		}
	}
	
	/************  MultiAxisNodeListener   ************ */
	@Override
	public void removeAixsNode(VBox node) {
		// TODO Auto-generated method stub
		if(node!=null) {
			if(this.nodeHBox.getChildren().contains(node)){
				System.out.println("MotionMultiAxisController2 ::: removeAixsNode ");
				this.nodeHBox.getChildren().remove(node);
				multiAxisComponent controller = this.mNodeMap.get(node);
				this.mNodeMap.remove(node);				
				this.mListenerList.remove(controller);
				this.mDriveList.remove(controller.getSelectedDriver());
				controller=null;
			}
		}
	}
	
	@Override
	public boolean checkSelectedDriver(String oldNode,String node) {
		boolean result = false;
		if(this.mDriveList.contains(node)) {result = true;}
		else { 
			this.mDriveList.add(node);
			this.mDriveList.remove(oldNode);
		}
		
		return result;
	}

	/************************
	 * 
	 * 실시간 드라이버 정보를 받아 해당 노드에 실시간 정보를 전달한다.
	 * 
	 *********************** */
	@Override
	public void realTimeDatarEvent(double Source) {
		// TODO Auto-generated method stub

	}

	@Override
	public void realTimeDataInfoEvent(RealTimeInfo Source) {
		try {
			for(NodeRealDataUpdateListener listener :mListenerList) {
				listener.updataData(Source);
			}
		}catch(Exception e) {
			System.out.println(Tag+" :: realTimeDataInfoEvent Error");
		}
	}

	@Override
	public void autoScanEvent(ObservableList<TreeItem<String>> nodes) {
		// TODO Auto-generated method stub

	}
}
