/**
 * ------------------------------------------------------------------------------
 * @Project       :  JustekApp
 * @Source        : RootLayoutController.java
 * @Description  : 모든 작업 화면의 Root View Controller
 * 						모든 메뉴와 기본 TreeView를 구성한다.
 * @Author        : YOUNGOK YOO / 확인자 : 
 * @Version       : v1.0
 * Copyright(c) 2018 JUSTEK All rights reserved
 *------------------------------------------------------------------------------
 *                  변         경         사         항                       
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                      DESCRIPTION                        
 * ----------  ------  --------------------------------------------------------- 
 * 2018.06.14  유영옥    신규생성                                     
 *------------------------------------------------------------------------------
 */

package justek.ide.view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import com.supinan.util.timer.SupinanTimer;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import justek.ide.MainApp;
import justek.ide.model.AutoScanThread;
import justek.ide.model.CommandConst;
import justek.ide.model.RealTimeInfo;
import justek.ide.model.listener.RealTimeEventListener;
import justek.ide.model.listener.TreeEventListener;
import justek.ide.model.manager.ControllerTreeViewManager;
import justek.ide.model.manager.NetworkServerManager;
import justek.ide.model.manager.RequestManager;
import justek.ide.model.manager.TreeViewManager;


public class RootLayoutController implements RealTimeEventListener {

	static final String Tag = "RootLayoutController";

	ObservableList<String> c1List = FXCollections
			.observableArrayList("Offline","TCP/IP"); //controller

	ObservableList<String> c2List = FXCollections
			.observableArrayList("Offline","Direct Access UDP","EtherCAT EoE","Direct Access USB");  //drive

	ObservableList<String> dFList = FXCollections
			.observableArrayList("Motion Device");  //device functionality
	ObservableList<String> dTList = FXCollections
			.observableArrayList("ds402");  //device type
	ObservableList<String> cTList = FXCollections
			.observableArrayList("Virtual Drive");  //control system type
	ObservableList<String> cTimeList = FXCollections
			.observableArrayList("0");  //cycle time divider

	ContextMenu contextMenu1 = new ContextMenu();   //Workspace
	ContextMenu contextMenu2 = new ContextMenu();   //Driver
	ContextMenu contextMenu3 = new ContextMenu();   //Controller
	ContextMenu contextMenu4 = new ContextMenu();   //Groups
	ContextMenu contextMenu5 = new ContextMenu();   //Virtual
	MenuItem menuItemNew = new MenuItem("New Workspace");  //menu1
	MenuItem menuItemOpen = new MenuItem("Open Workspace");//menu1
	MenuItem menuItemSave = new MenuItem("Save Workspace as");//menu1
	MenuItem menuItemClear = new MenuItem("Clear Workspace");//menu1
	MenuItem menuItemAddDriver = new MenuItem("Add Driver");//menu1
	MenuItem menuItemAddController = new MenuItem("Add Controller");//menu1
	MenuItem menuItemSaveWork = new MenuItem("Save Workspace");   //menu2
	MenuItem menuItemRemoveTarget = new MenuItem("Remove Target");   //menu2
	MenuItem menuItemConnect = new MenuItem("Connect");   //menu2
	MenuItem menuItemRemoveTarget1 = new MenuItem("Remove Target");   //menu3
	MenuItem menuItemConnect1 = new MenuItem("Connect");   //menu3
	MenuItem menuItemNewEtherCAT = new MenuItem("New EtherCAT Configuration"); //menu3  
	//    MenuItem menuItemEditEtherCAT = new MenuItem("Edit EtherCAT Configuration");   //menu3
	MenuItem menuItemAddDriver2 = new MenuItem("Add Driver"); //menu4
	MenuItem menuItemAddDriver3 = new MenuItem("Add Driver"); //menu5

	//실시간 정보를 요청하는 View
	HashMap<String, Object> realTimeTabMap;

	//Tab에 추가되는 View 
	HashMap<String, Object> tabMap;

	//시스템정보Tab에 추가되는 View 
	HashMap<String, Object> tabSystemConfigMap;

	@FXML
	private VBox treepane;

	//Tunning을 위한 화면들이 추가된다.
	@FXML
	private TabPane mainTabPane;
	//System구성정보 화면들이 추가된다.
	@FXML
	private TabPane systemConfigPane;

	@FXML
	private TabPane rootTabPane;

	//SystemConfig Tab
	@FXML
	private Tab systemConfigTab;

	@FXML
	private Tab systemMonitorTab; //AxisSetup
	@FXML
	private Tab systemConfigratorTab;
	@FXML
	private Tab systemTunningTab; //AxisSetup과 configrator를 제외한 모든 화면
	@FXML
	private AnchorPane rootAnchorPane;

	@FXML
	private FlowPane fxButtonPane;

	@FXML
	private Button button;

	private ObservableList<TreeItem<String>> nodeList;

	@FXML
	private ComboBox<String> controllerCombo1; //connection type of controller
	@FXML
	private TreeView<String> treeView;
	@FXML
	private TreeView<String> axisTreeView;
	@FXML
	private ListView<String> controllerTreeView;
	@FXML
	private AnchorPane consolePane;

	@FXML
	private Label fxDriverLabel;

	//TreeView List
	private ObservableList<TreeItem<String>>  axisNodeList;
	//TreeView List
	private ObservableList<String>  controllerNodeList;

	private TreeViewManager treeViewManager;
	private ControllerTreeViewManager controllerTreeViewManager;

	Image icon = new Image (
			getClass().getResourceAsStream("/img/folder-icon.png"));

	Image open_icon = new Image (
			getClass().getResourceAsStream("/img/open.png"));
	Image close_icon = new Image (
			getClass().getResourceAsStream("/img/close.png"));

	// Reference to the main application
	private MainApp mainApp;

	private AutoScanThread mThread;
	private long mThreadId; //Timer ID
	private SupinanTimer timer;

	//CommonButtonController
	CommonButtonController buttonController;

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
	
		
		this.nodeList = this.getDefaultNodeInfo();
		this.realTimeTabMap = new HashMap<>();
		this.tabMap = new HashMap<>();
		this.tabSystemConfigMap  = new HashMap<>();

		TreeItem<String> root = new TreeItem<>("Workspace Justek", new ImageView(icon));
		root.setExpanded(true);
		root.getChildren().addAll(this.nodeList);
		treeView.setRoot(root);

		CommandConst.DRIVER = this.nodeList.get(1).getValue();
		fxDriverLabel.setText(CommandConst.DRIVER);

		contextMenu1.getItems().addAll(menuItemNew, menuItemOpen,menuItemSave, menuItemClear, menuItemAddDriver, menuItemAddController);	
		contextMenu2.getItems().addAll(menuItemSaveWork, menuItemRemoveTarget,menuItemConnect);	
		contextMenu3.getItems().addAll(menuItemRemoveTarget1, menuItemConnect1, menuItemNewEtherCAT);// menuItemEditEtherCAT);	
		contextMenu4.getItems().addAll(menuItemAddDriver2);	
		contextMenu5.getItems().addAll(menuItemAddDriver3);	
		menuItemNew.setOnAction(e -> handleNewWorkspace());
		menuItemOpen.setOnAction(e -> handleOpenWorkspace());
		menuItemSave.setOnAction(e -> handleSaveWorkspace());
		menuItemClear.setOnAction(e -> handleClearWorkspace());
		menuItemAddDriver.setOnAction(e -> handleAddDriver());
		menuItemAddController.setOnAction(e -> handleAddController());
		menuItemSaveWork.setOnAction(e -> handleSaveWorkspace());
		menuItemRemoveTarget.setOnAction(e -> handleRemoveTarget());
		menuItemConnect.setOnAction(e -> handleConnectDevice());
		menuItemRemoveTarget1.setOnAction(e -> handleRemoveTarget());
		menuItemConnect1.setOnAction(e -> handleConnectDevice());
		//        menuItemNewEtherCAT.setOnAction(e -> handleEtherCATMaster());  
		menuItemNewEtherCAT.setOnAction(e -> handleAutoScan());  
		//       menuItemEditEtherCAT.setOnAction(e -> handleEtherCATSlave()); 
		//        menuItemEditEtherCAT.setOnAction(e -> handleEtherCATMaster()); 
		menuItemAddDriver2.setOnAction(e -> handleAddDriver());
		menuItemAddDriver3.setOnAction(e -> handleAddDriver());

		//시스템 정보 탭의 선택을 해제할 경우 확인팝업을 보여준다.
		//		this.rootTabPane.getSelectionModel().selectedItemProperty().addListener(
		//				new ChangeListener<Tab>() {
		//					@Override
		//					public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
		//						System.out.println("rootTabPane Selection changed");
		//						if(t.equals(systemConfigTab)) {
		//							boolean result = DialogManager.getInstance().showSystemConfigOutConfirmDialog();
		//							if(!result) {
		//								rootTabPane.getSelectionModel().select(1);
		//							}
		//							else {
		//								systemConfigTab.setDisable(true);
		//							}
		//						}
		//					}
		//				}
		//				);
		
		
//		this.setDriverID(); //Driver ID 설정
		this.setAxisTree();
		this.setControllerTree();
		this.addTerminalPane();
		this.selectMainTabPane();
	}   

	
	public void setDriverID() {
		
		//controller와 EL9800갯수 차람
		int size = this.nodeList.size()-2;
		
		for(int i=1;i<=size;i++) {
			String command = CommandConst.SET_DRIVER.replace("Driver", String.valueOf(i));
			NetworkServerManager.setDriverIDSet(command);
		}
		
		for(int i=1;i<=size;i++) {
			String command = CommandConst.SET_ACTUAL_POSITION.replace("Driver", String.valueOf(i));
			NetworkServerManager.setDriverIDSet(command);
		}
		
		for(int i=1;i<=size;i++) {
			String command = CommandConst.SET_TARGET_POSITION.replace("Driver", String.valueOf(i));
			NetworkServerManager.setDriverIDSet(command);
		}
		
		for(int i=1;i<=size;i++) {
			String command = CommandConst.SET_TARGET_VELOCITY.replace("Driver", String.valueOf(i));
			NetworkServerManager.setDriverIDSet(command);
		}
	}

	@FXML
	private void initializeNew() {
		if(!CommandConst.DEBUG) {
			this.nodeList = this.getNodeInfo();
		}

		this.setTree();
		//		this.addTerminalPane();
		//드라이버의 정보를 실시간으로 요청한다.(2018.04.26) 요청사항으로 추가
		//		this.getAutoScandata();
	}      


	public void addTabPane(String title,Pane pane,Object controller) {
		System.out.println(Tag+"addTabPane :: Tab_ID :: "+pane.getId());
		this.rootTabPane.getSelectionModel().select(0);
		Tab tab = new Tab();
		tab.setContent(pane);
		tab.setText(title);
		tab.setId(pane.getId());
		this.mainTabPane.getTabs().add(tab);
		this.tabMap.put(tab.getId(), controller);
		SingleSelectionModel<Tab> selectionModel = mainTabPane.getSelectionModel();
		selectionModel.select(tab);

		tab.setOnClosed(new EventHandler<Event>() {
			@Override
			public void handle(Event t) {
				System.out.println(tab.getId()+" :: Tab Closed!");
				if(tabMap.containsKey(tab.getId())) {
					System.out.println(tab.getId()+" :: Contains");
					//		        	Object object = tabMap.get(tab.getId());
					Object object =tabMap.get(tab.getId());
					if(object.equals(mainApp.listener)) {
						mainApp.listener = null;
					}
					tabMap.remove(tab.getId());
				}

				if(realTimeTabMap.containsKey(tab.getId())){
					realTimeTabMap.remove(tab.getId());
				}
			}
		});
	}

	public void addSystemConfigTab(String title,Pane pane,Object controller) {
		Tab tab = new Tab();
		tab.setContent(pane);
		tab.setText(title);
		tab.setId(pane.getId());
		this.systemConfigPane.getTabs().add(tab);
		this.tabSystemConfigMap.put(title, controller);
		SingleSelectionModel<Tab> selectionModel = systemConfigPane.getSelectionModel();
		selectionModel.select(tab);

		tab.setOnClosed(new EventHandler<Event>() {
			@Override
			public void handle(Event t) {
				System.out.println("System Tab Closed!");
				if(tabSystemConfigMap.containsKey(tab.getId())){
					tabSystemConfigMap.remove(tab.getId());
				}
			}
		});
	}

	public void selectTunningTab() {
		this.systemConfigTab.setDisable(true);
		this.rootTabPane.getSelectionModel().select(0);		
	}

	//시스템구성정보 탭을 활성화한다.
	public void selectSystemTab() {
		//		this.mainTabPane.setDisable(true);
		this.systemConfigTab.setDisable(false);
		this.rootTabPane.getSelectionModel().select(1);		
		if(this.systemConfigPane.getTabs().size()!=0) {
			this.systemConfigPane.getSelectionModel().select(0);
		}
	}

	//시스템 모니터링 View가 이미 추가되어 있으면 title에 해당되는 tab를 선태하도록 한다.
	public void selectTab(String title) {
		this.rootTabPane.getSelectionModel().select(0);
		ObservableList<Tab> tabList =this.mainTabPane.getTabs();
		for(Tab tab:tabList) {
			if(tab.getText().equals(title)) {
				this.mainTabPane.getSelectionModel().select(tab);
				/** 선택된 controller를 mainApp의 드라이버 선택 EventListener를 받도록 수정한다.*/
				Object object = tabMap.get(tab.getId());
				if(object!=null) {	
					if(object instanceof TreeEventListener) {
						System.out.println(Tag+" :: tab changed "+object.getClass());
						object =(TreeEventListener)tabMap.get(tab.getId());
						mainApp.listener = (TreeEventListener)object;
					}
				}
				break;
			}
		}	

		if(CommandConst.isEtherCAT) {
			if(this.mainTabPane.getSelectionModel().getSelectedItem().getText().equals("EtherCATSlave")) {
				EtherCATSlaveController2 controller = (EtherCATSlaveController2)this.tabMap.get("EtherCATSlave");
				//				controller.getSlaveData();
				controller.readXMLFile();
				this.mainApp.listener  = controller;
			}
			else {
				if(this.mainTabPane.getSelectionModel().getSelectedItem().getText().equals("EtherCATMaster")) {
					EtherCATMasterController controller = (EtherCATMasterController)this.tabMap.get("EtherCATMaster");
					this.mainApp.listener  = controller;
				}
			}
		}
	}

	public void selectSystemConfigTab(String title) {
		ObservableList<Tab> tabList =this.systemConfigPane.getTabs();
		for(Tab tab:tabList) {
			if(tab.getText().equals(title)) {
				this.systemConfigPane.getSelectionModel().select(tab);
				break;
			}
		}	
	}

	public void selectSystemMonitoringPane() {
		if(CommandConst.SYSTEM_CONFIG) {
			CommandConst.SYSTEM_CONFIG = false;
			if(this.rootTabPane.getSelectionModel().getSelectedIndex()==0) {
				System.out.println("selectSystemMonitoringPane Selection changed");
				this.systemConfigTab.setDisable(true);
			}
		}
	}

	public void putControllerMap(String key, Object controller) {
		this.realTimeTabMap.put(key, controller);
	}


	public void addButtonPane() {
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CommonButtonView.fxml"));
			FlowPane buttonPane = (FlowPane) loader.load();
			this.buttonController = loader.getController();
			this.fxButtonPane.getChildren().add(buttonPane);
			this.fxButtonPane.setVisible(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setButtonPaneVisible(boolean value) {
		this.fxButtonPane.setVisible(value);
	}

	/**
	 * @author : YOO YOUNGOK 
	 * @method  addTerminalPane
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	private void addTerminalPane() {
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ConsoleView.fxml"));
			AnchorPane lTerminalPane = (AnchorPane) loader.load();
			AnchorPane.setTopAnchor(lTerminalPane,0.0); // obviously provide your own constraints
			AnchorPane.setBottomAnchor(lTerminalPane,0.0);
			AnchorPane.setRightAnchor(lTerminalPane,0.0);
			AnchorPane.setLeftAnchor(lTerminalPane,0.0);

			this.consolePane.getChildren().add(lTerminalPane);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public AnchorPane getMainPane() {
		return this.rootAnchorPane;
	}

	/**
	 * @author : YOO YOUNGOK 
	 * @method  isContainedTab
	 * @param key
	 * @return
	 * @return  boolean
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public boolean isContainedTab(String key) {
		boolean result = false;

		if(this.tabMap.containsKey(key)) {
			result = true;
		}

		return result;
	}

	/**
	 * @author : YOO YOUNGOK 
	 * @method  isSystemConfigContainedTab
	 * @param key
	 * @return
	 * @return  boolean
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public boolean isSystemConfigContainedTab(String key) {
		boolean result = false;

		if(this.tabSystemConfigMap.containsKey(key)) {
			result = true;
		}

		return result;
	}

	/**
	 * @author : YOO YOUNGOK 
	 * @method  selectMainTabPane
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public void selectMainTabPane() {
		this.mainTabPane.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Tab>() {
					@Override
					public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
						System.out.println("Tab Selection changed");
						if(t1!=null && realTimeTabMap.containsKey(t1.getId())){
							System.out.println(t1.getText() + " :: Tab Listener select changed");
							RealTimeEventListener listener = (RealTimeEventListener)realTimeTabMap.get(t1.getId());
							RequestManager.getInstance().setRealTimeEventListener(listener);
						}

						/** 선택된 controller를 mainApp의 드라이버 선택 EventListener를 받도록 수정한다.*/
						if(t1==null) return;
						Object object = tabMap.get(t1.getId());
						if(object!=null) {	
							if(object instanceof TreeEventListener) {
								System.out.println(Tag+" :: tab changed "+object.getClass());
								object =(TreeEventListener)tabMap.get(t1.getId());
								mainApp.listener = (TreeEventListener)object;
							}
						}
					}
				}
				);
	}

	/**
	 * @author : YOO YOUNGOK 
	 * @method  mouseClick
	 * @param mouseEvent //사용자의 화면에서 실행하는 mouseEvent
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public void mouseClick(MouseEvent mouseEvent) {
		MouseButton button = mouseEvent.getButton();

		TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();   	
		if(item == null) return;

		if(button==MouseButton.PRIMARY) {

			if(item.getValue().equals("EL9800")) {
				mainApp.listener=null;
				mainApp.EtherCATPortInfo();
			}
			//시스템 환경설정 모드일경우와 아닌경우를 구분해야함..
			else if(CommandConst.SYSTEM_CONFIG) {
				if(item.getValue().equals("Workspace Justek")) {
					mainApp.Workspace();
				} else if(item.getValue().equals("Controller"))  {
					mainApp.SystemConfiguratorController();
					//Controller가 아닌경우는 Driver정보(Slave 화면)를 호출한다.
				}else {
					mainApp.SystemConfiguratorDrive(item.getValue());
				}
			}
			else {
				System.out.println("Driver Select Change");
				if(this.nodeList.contains(item)) {
					int i = this.nodeList.indexOf(item);

					if(CommandConst.isEtherCAT) {
						System.out.println("Is EtherCAT");
						if(i==0) {
							CommandConst.isController = true;
							if(this.mainApp.listener!=null) {
								System.out.println("Driver Select Controller== Listener");
								this.mainApp.listener.clieckMenu();
							}
						}
						else {
							CommandConst.isController = false;
							CommandConst.DRIVER_NUMBEER = i;
							CommandConst.DRIVER = item.getValue();
							fxDriverLabel.setText(CommandConst.DRIVER);
							if(this.mainApp.listener!=null) {
								System.out.println("Driver Select == Listener");
								this.mainApp.listener.clieckMenu();
							}
						}
					}
					else {
						System.out.println("Not EtherCAT");
						CommandConst.isController = false;
						if(i==0) {
							System.out.println("Not EtherCAT Controller");
							//							CommandConst.DRIVER_NUMBEER = 1;
							return;
						}
						//EtherCAT 정보화면이 아닌경우 선택 드라이버로 label를 변경한다.
						else {
							CommandConst.DRIVER_NUMBEER = i;
							CommandConst.DRIVER = item.getValue();
							fxDriverLabel.setText(CommandConst.DRIVER);

							if(this.mainApp.listener!=null) {
								System.out.println("Driver Select == Listener");
								this.mainApp.listener.clieckMenu();
							}
						}
					}
				}	
			}
		} 
		else if(button==MouseButton.SECONDARY) {

			if(!CommandConst.SYSTEM_CONFIG) return;

			if(item.getValue().equals("Workspace Justek")) {
				// workspace right click
				contextMenu1.show(mainApp.getPrimaryStage(), mouseEvent.getScreenX(), mouseEvent.getScreenY());
			} else if(item.getValue().equals("Controller"))  {
				contextMenu3.show(mainApp.getPrimaryStage(), mouseEvent.getScreenX(), mouseEvent.getScreenY());
			} else if(item.getValue().equals("Groups"))  {
				contextMenu4.show(mainApp.getPrimaryStage(), mouseEvent.getScreenX(), mouseEvent.getScreenY());
			} else if(item.getValue().equals("Device Network(EtherCAT)"))  {
				contextMenu5.show(mainApp.getPrimaryStage(), mouseEvent.getScreenX(), mouseEvent.getScreenY());

			}else if(item.getValue().equals("Driver01"))  {
				contextMenu2.show(mainApp.getPrimaryStage(), mouseEvent.getScreenX(), mouseEvent.getScreenY());
			}else if(item.getValue().equals("Driver02"))  {
				contextMenu2.show(mainApp.getPrimaryStage(), mouseEvent.getScreenX(), mouseEvent.getScreenY());
			}else if(item.getValue().equals("Driver03"))  {
				contextMenu2.show(mainApp.getPrimaryStage(), mouseEvent.getScreenX(), mouseEvent.getScreenY());
			}else if(item.getValue().equals("v01"))  {
				contextMenu2.show(mainApp.getPrimaryStage(), mouseEvent.getScreenX(), mouseEvent.getScreenY());
			}else if(item.getValue().equals("a01(Virtual)"))  {
				contextMenu2.show(mainApp.getPrimaryStage(), mouseEvent.getScreenX(), mouseEvent.getScreenY());
			}
		}
	}


	@FXML
	private void handleOpenWorkspace() {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
		if (file != null) {
			mainApp.loadWorkspaceFromFile(file);
		}
	}


	@FXML
	private void handleNewWorkspace() {
		mainApp.NewWorkspace();
	}


	@FXML
	private void handleSaveWorkspace() {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

		if (file != null) {
			// Make sure it has the correct extension
			if (!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
			mainApp.saveWorkspaceToFile(file);
		}
	}


	@FXML
	private void handleClearWorkspace() {
		mainApp.ClearWorkspace();
	}

	@FXML
	private void handleAddDriver() {
		mainApp.AddDriver();
	}

	@FXML
	private void handleAddController() {
		mainApp.AddController();
	}

	@FXML
	private void handleRemoveTarget() {
		mainApp.RemoveTarget();
	}

	@FXML
	private void handleConnectDevice() {
		mainApp.ConnectDevice();
	}

	@FXML
	private void handleConnectController() {
		mainApp.ConnectController();
	}

	@FXML
	private void handleEtherCATMaster() {
		mainApp.EtherCATMaster();
	}


	//모터에 연결된 EtherCAT정보를 업데이트한다.
	private void handleAutoScan() {
		this.initializeNew();
		mainApp.EtherCATMaster();
	}

	@FXML
	private void handleEtherCATSlave() {
		CommandConst.SYSTEM_CONFIG = false;
		CommandConst.isEtherCAT = true;
		if(this.treeView.getSelectionModel().getSelectedItem()==null) {
			mainApp.EtherCATMaster();
		}
		else if(this.treeView.getSelectionModel().getSelectedItem().getValue().equals("Controller")) {
			mainApp.EtherCATMaster();
		}
		else {
			mainApp.EtherCATSlave();
		}
	}

	@FXML
	private void handleAddGroup() {
		mainApp.AddGroup();
	}


	@FXML
	private void handleAddVirtualDevice() {
		mainApp.AddVirtualDevice();
	}

	/*************************
	 * 시스템 정보 화면모드로 전환해야함...
	 * CommandConst의 SYSTEM_CONFIG=true..
	 * 
	 * ************************/
	@FXML
	private void handleWorkspace() {
		mainApp.Workspace();

	}

	@FXML
	private void handleSystemConfiguratorController() {
		mainApp.SystemConfiguratorController();
	}

	@FXML
	private void handleSystemConfiguratorDrive() {
		//		mainApp.SystemConfiguratorDrive();
	}


	@FXML
	private void handleAxisSetup() {
		mainApp.AxisSetup();
	}


	@FXML
	private void handleMotionSingleAxis() {
		mainApp.MotionSingleAxis();
	}

	@FXML
	private void handleMotionMultiAxis() {
		mainApp.MotionMultiAxis();
	}

	@FXML
	private void handleParameters() {
		mainApp.Parameters();
	}

	@FXML
	private void handleParameterComparison() {
		this.mainApp.thread_flag= false;
		mainApp.ParameterComparison();
	}

	@FXML
	private void handleCompensationTable() {
		mainApp.CompensationTable();
	}

	@FXML
	private void handleControllerConfigurator() {
		mainApp.ControllerConfigurator();
	}


	@FXML
	private void handleControllerConfigurator1() {
		mainApp.ControllerConfigurator1();
	}

	@FXML
	private void handleControllerConfigurator2() {
		mainApp.ControllerConfigurator2();
	}

	@FXML
	private void handleControllerConfigurator3() {
		mainApp.ControllerConfigurator3();
	}

	@FXML
	private void handleControllerConfigurator4() {
		mainApp.ControllerConfigurator4();
	}

	@FXML
	private void handleControllerConfigurator5() {
		mainApp.ControllerConfigurator5();
	}

	@FXML
	private void handleControllerConfigurator6() {
		mainApp.ControllerConfigurator6();
	}

	@FXML
	private void handleControllerConfiguratorG1() {
		mainApp.ControllerConfiguratorG1();
	}

	@FXML
	private void handleControllerConfiguratorG2() {
		mainApp.ControllerConfiguratorG2();
	}

	@FXML
	private void handleControllerConfiguratorG3() {
		mainApp.ControllerConfiguratorG3();
	}

	@FXML
	private void handleControllerConfiguratorG4() {
		mainApp.ControllerConfiguratorG4();
	}


	@FXML
	private void handleControllerMotion() {
		mainApp.ControllerMotion();

	}

	@FXML
	private void handleEtherCATDiagMaster() {
		mainApp.EtherCATDiagMaster();

	}

	@FXML
	private void handleEtherCATDiagSlave() {
		mainApp.EtherCATDiagSlave();


	}

	@FXML
	private void handleParametersExplorer() {
		mainApp.ParametersExplorer();
	}

	@FXML
	private void handleControllerCompensatoinTable() {
		mainApp.ControllerCompensationTable();
	}

	@FXML
	private void handleJog() {
		mainApp.Jog();
	}

	@FXML
	private void handleStatusView() {
		mainApp.StatusView();
	}

	@FXML
	private void  handleMotionProgramEdit() {
		mainApp.Edit();
	}   
	@FXML
	private void handleMotionIDE() {  
		StringBuffer output = new StringBuffer();
		Process process = null;
		BufferedReader bufferReader = null;
		Runtime runtime = Runtime.getRuntime();

		try {
			process = runtime.exec("./run_beremiz.sh");

		} catch (IOException e) {
			output.append("IOException : " + e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	private void     handleCommandMacro() {
	}
	@FXML
	private void     handleScriptManager() {
	}
	@FXML
	private void     handleMotionProgramRun() {
	}
	@FXML
	private void     handleUploadController() {
	}
	@FXML
	private void   handleDownloadController() {
	}
	@FXML
	private void    handleUploadDriver() {
		this.mainApp.handleUploadDriver();

	}
	@FXML
	private void  handleDownloadDriver() {
	}
	@FXML
	private void  handleDownloadControllerFirmware() {
	}
	@FXML
	private void  handleDownloadDriverFirmware() {
		this.mainApp.handleDownloadFirmWare();
	}

	@FXML
	private void  handleRecorder() {
		this.mainApp.handleRecorder();
	}

	@FXML
	private void handleShowStatistics() {
		this.mainApp.handleShowStatistics();
	}

	@FXML
	private void handleLogHistory() {
		this.mainApp.handleLogHistory();
	}

	@FXML
	private void handleOffLine() {
		System.out.println(Tag +" :: handleOffLine"); 

		if(CommandConst.DEBUG) return;

		StringBuffer output = new StringBuffer();
		Runtime runtime = Runtime.getRuntime();

		try {
			runtime.exec("/bin/bash /usr/bin/linuxcnc ../linuxcnc/configs/pmac.sim/pmac.ini");

		} catch (IOException e) {
			output.append("IOException : " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void onClickAxisTreeView(MouseEvent mouseEvent) {
		System.out.println(Tag +" :: onClickAxisTreeView"); 
		treeViewManager.setTreeView(axisTreeView); //추후 화면이 확정되면 삭제한다...
		treeViewManager.setMainApp(this.mainApp);
		treeViewManager.mouseClick(mouseEvent);
	}

	public void onClickControllerListView(MouseEvent mouseEvent) {
		System.out.println(Tag +" :: onClickControllerListView"); 
		this.controllerTreeViewManager.setMainApp(this.mainApp);
		controllerTreeViewManager.setListView(this.controllerTreeView);
		controllerTreeViewManager.mouseClick(mouseEvent);
	}

	@FXML
	public void onClickSystemMonitor(ActionEvent event) {
		System.out.println(Tag +" :: onClickSystemMonitor"); 

		if(CommandConst.DEBUG) return;

		StringBuffer output = new StringBuffer();
		Runtime runtime = Runtime.getRuntime();

		try {
			runtime.exec("/usr/bin/gnome-system-monitor");

		} catch (IOException e) {
			output.append("IOException : " + e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	private void  handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Justek APP");
		alert.setHeaderText("About");
		alert.setContentText("Author: JUSTEK");

		alert.showAndWait();    	
	}


	//오토스캔을 실시한다.
	public void getAutoScandata() {
		if(this.mThread==null) {
			this.mThread = AutoScanThread.getInstance(300);
			this.mThread.addRealTimeEventListener(this);
			this.timer = new SupinanTimer();
		}
		this.mThreadId = timer.addTimer(mThread);
	}

	//오토스캔 타이머를 종료한다.
	public void stopAutoScandata() {
		if(this.mThread!=null && this.timer!=null) {
			this.timer.removeTimer(this.mThreadId);
			this.mThread = null;
			this.timer.closeTimer();
		}
	}

	public ObservableList<TreeItem<String>> getDefaultNodeInfo() {

		ObservableList<TreeItem<String>> nodes = FXCollections.observableArrayList();

		TreeItem<String> nodeA = new TreeItem<>("Controller");
		nodes.add(nodeA);
		TreeItem<String> node = new TreeItem<>("Driver01");
		nodes.add(node);
		TreeItem<String> node1 = new TreeItem<>("Driver02");
		nodes.add(node1);
		TreeItem<String> node2 = new TreeItem<>("Driver03");
		nodes.add(node2);
		TreeItem<String> node3 = new TreeItem<>("EL9800");
		nodes.add(node3);
		return nodes;
	}

	//Driver Scan
	public ObservableList<TreeItem<String>> getNodeInfo() {

		ObservableList<TreeItem<String>> nodes = FXCollections.observableArrayList();

		Runtime runtime = Runtime.getRuntime();
		try {
			int count=1;
			Process process = runtime.exec("ethercat slaves");
			String szLine; 
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			TreeItem<String> nodeA = new TreeItem<>("Controller");
			nodes.add(nodeA);
			CommandConst.driverList.clear();
			br.readLine();
			while ((szLine = br.readLine()) != null) {
				TreeItem<String> node = new TreeItem<>("Driver"+count);
				nodes.add(node);
				CommandConst.driverList.add("Driver"+count);
				System.out.println(Tag+"EtherCAT Name :: "+szLine);
				count++;
			}
			
			TreeItem<String> node = new TreeItem<>("EL9800");
			nodes.add(node);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return nodes;
	}

	@FXML
	public void serverOff() {
		if(CommandConst.DEBUG) {
			System.out.println("DEBUG Server Off");
			return;
		}

		this.stopAutoScandata();
		try {
			System.out.println("Server Off");
			Socket socketClient = new Socket(CommandConst.address, 12345);

			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

			for(int i=1;i<=CommandConst.driverList.size();i++) {
				writer.write("#"+String.valueOf(i)+"k\r\n");
			}
			writer.flush();

			socketClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Closes the application.
	 */
	@FXML
	private void handleExit() {
		System.exit(0);
	}

	//실시간 AutoScan정보를 트리에 반영한다.
	public  void setTree() {
		System.out.println("Device Tree 정보를 업데이트합니다..."); 
		TreeItem<String> root = new TreeItem<>("Workspace Justek",new ImageView(icon));
		root.setExpanded(true);
		root.getChildren().addAll(this.nodeList);
		TreeItem<String> node3 = new TreeItem<>("EL9800");
		this.nodeList.add(node3);
		treeView.setRoot(root);

		CommandConst.DRIVER = this.nodeList.get(1).getValue();
	}

	public  void setAxisTree() {
		treeViewManager = TreeViewManager.getInstance();
		this.axisNodeList = treeViewManager.getNodeInfo();
		treeViewManager.setTreeView(axisTreeView);

		TreeItem<String> root = new TreeItem<>("Axis Configrations");
		root.setExpanded(true);
		root.getChildren().addAll(this.axisNodeList);
		root.getChildren().get(0).setExpanded(true);
		root.getChildren().get(1).setExpanded(true);
		root.getChildren().get(2).setExpanded(true);
		root.getChildren().get(3).setExpanded(true);
		root.getChildren().get(4).setExpanded(true);
		this.axisTreeView.setRoot(root);
	}

	/**
	 * 실시간 AutoScan정보를 트리에 반영한다.
	 * @author : YOO YOUNGOK 
	 * @method  setControllerTree
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public  void setControllerTree() {
		controllerTreeViewManager = ControllerTreeViewManager.getInstance();
		controllerNodeList = controllerTreeViewManager.getNodeInfo();
		controllerTreeView.setItems(controllerNodeList);
		controllerTreeViewManager.setListView(this.controllerTreeView);
	}


	/** (non-Javadoc)
	 * @see justek.ide.model.listener.RealTimeEventListener#realTimeDataInfoEvent(justek.ide.model.RealTimeInfo)
	 */
	@Override
	public void realTimeDataInfoEvent(RealTimeInfo Source) {
		// TODO Auto-generated method stub
	}


	/** (non-Javadoc)
	 * @see justek.ide.model.listener.RealTimeEventListener#realTimeDatarEvent(double)
	 */
	@Override
	public void realTimeDatarEvent(double Source) {
		// TODO Auto-generated method stub
	}

	/** (non-Javadoc)
	 * @see justek.ide.model.listener.RealTimeEventListener#autoScanEvent(javafx.collections.ObservableList)
	 */
	@Override
	public void autoScanEvent(ObservableList<TreeItem<String>> nodes) {

		if(nodes!=null) {
			this.nodeList =nodes;
			this.treeView.getRoot().getChildren().clear();

			// Update the Label on the JavaFx Application Thread
			Platform.runLater(new Runnable()
			{
				@Override
				public void run()
				{
					setTree();
				}
			});
		}
		else {
			System.out.println("AutoScanData를 받아오지 못했습니다...");
		}
	}
}