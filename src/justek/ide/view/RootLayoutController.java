/**
 * ------------------------------------------------------------------------------
 * @Project       :  JustekApp
 * @Source        : RootLayoutController.java
 * @Description  : ��� �۾� ȭ���� Root View Controller
 * 						��� �޴��� �⺻ TreeView�� �����Ѵ�.
 * @Author        : YOUNGOK YOO / Ȯ���� : 
 * @Version       : v1.0
 * Copyright(c) 2018 JUSTEK All rights reserved
 *------------------------------------------------------------------------------
 *                  ��         ��         ��         ��                       
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                      DESCRIPTION                        
 * ----------  ------  --------------------------------------------------------- 
 * 2018.06.14  ������    �űԻ���                                     
 *------------------------------------------------------------------------------
 * 2018.09.04  ������    �ǿ� ���� �� �߰��� �ǽð������� ������� ȭ���� ��� �ǽð� listener�� nulló���Ѵ�.                    
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
import java.util.Optional;

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
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
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
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import justek.ide.MainApp;
import justek.ide.model.AutoScanThread;
import justek.ide.model.CommandConst;
import justek.ide.model.RealTimeInfo;
import justek.ide.model.listener.RealTimeEventListener;
import justek.ide.model.listener.TreeEventListener;
import justek.ide.model.manager.ControllerTreeViewManager;
import justek.ide.model.manager.DialogManager;
import justek.ide.model.manager.ErrorLogManager;
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
	MenuItem menuItemRemoveDriver = new MenuItem("Remove Driver");//menu1
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

	@FXML
	private RadioMenuItem fxOffLineRadioMenuItem;

	@FXML
	private RadioMenuItem fxOnLineRadioMenuItem;

	//�ǽð� ������ ��û�ϴ� View
	HashMap<String, Object> realTimeTabMap;

	//Tab�� �߰��Ǵ� View 
	HashMap<String, Object> tabMap;

	//�ý�������Tab�� �߰��Ǵ� View 
	HashMap<String, Object> tabSystemConfigMap;

	@FXML
	private VBox treepane;

	//Tunning�� ���� ȭ����� �߰��ȴ�.
	@FXML
	private TabPane mainTabPane;
	//System�������� ȭ����� �߰��ȴ�.
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
	private Tab systemTunningTab; //AxisSetup�� configrator�� ������ ��� ȭ��
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
	private ComboBox<String> fxIPComboBox; //����ڰ� ������ IP�� �����ϵ��� �Ѵ�.

	@FXML
	private Label fxSerboModeLabel; //�������¸� ǥ���Ѵ�.

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

		//�������� ǥ��
		handleOffLine();

		this.fxIPComboBox.setItems(CommandConst.SERVO_IP_LIST);

		//		contextMenu1.getItems().addAll(menuItemNew, menuItemOpen,menuItemSave, menuItemClear, menuItemAddDriver, menuItemAddController);	
		contextMenu1.getItems().addAll( menuItemAddDriver, menuItemRemoveDriver);
		contextMenu2.getItems().addAll(menuItemSaveWork, menuItemRemoveTarget,menuItemConnect);	
		contextMenu3.getItems().addAll(menuItemRemoveTarget1, menuItemConnect1, menuItemNewEtherCAT);// menuItemEditEtherCAT);	
		contextMenu4.getItems().addAll(menuItemAddDriver2);	
		contextMenu5.getItems().addAll(menuItemAddDriver3);	
		menuItemNew.setOnAction(e -> handleNewWorkspace());
		menuItemOpen.setOnAction(e -> handleOpenWorkspace());
		menuItemSave.setOnAction(e -> handleSaveWorkspace());
		menuItemClear.setOnAction(e -> handleClearWorkspace());
		menuItemAddDriver.setOnAction(e -> handleAddDriver());
		menuItemRemoveDriver.setOnAction(e -> handleRemoveDriver());
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

		//�ý��� ���� ���� ������ ������ ��� Ȯ���˾��� �����ش�.
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

		this.setAxisTree();
		this.setControllerTree();
		this.addTerminalPane();
		this.selectMainTabPane();
	}   


	public void setDriverID() {

		if(CommandConst.DEBUG) {
			//�ǽð� ������ ��û�Ѵ�.
			RequestManager.getInstance().testRealTimeData();
			System.out.println("Debug requestAllRealTimeData");
			return;
		}

		//Driver������� ����...-> ���� ����Ȯ�� �ʿ�...
		int size = CommandConst.driverList.size();
		
		for(int i=0;i<size;i++) {

			if(CommandConst.DRIVER_MAP.containsKey(CommandConst.driverList.get(i))) {
				String command = CommandConst.SET_DRIVER.replace("Driver", String.valueOf(CommandConst.DRIVER_MAP.get(CommandConst.driverList.get(i))));
				NetworkServerManager.setDriverIDSet(command);
			}
		}

		for(int i=0;i<size;i++) {
			if(CommandConst.DRIVER_MAP.containsKey(CommandConst.driverList.get(i))) {
				String command = CommandConst.SET_ACTUAL_POSITION.replace("Driver", String.valueOf(CommandConst.DRIVER_MAP.get(CommandConst.driverList.get(i))));
				NetworkServerManager.setDriverIDSet(command);
			}
		}

		for(int i=0;i<size;i++) {
			if(CommandConst.DRIVER_MAP.containsKey(CommandConst.driverList.get(i))) {
				String command = CommandConst.SET_TARGET_POSITION.replace("Driver", String.valueOf(CommandConst.DRIVER_MAP.get(CommandConst.driverList.get(i))));
				NetworkServerManager.setDriverIDSet(command);
			}
		}

		for(int i=0;i<size;i++) {
			if(CommandConst.DRIVER_MAP.containsKey(CommandConst.driverList.get(i))) {
				String command = CommandConst.SET_TARGET_VELOCITY.replace("Driver", String.valueOf(CommandConst.DRIVER_MAP.get(CommandConst.driverList.get(i))));
				NetworkServerManager.setDriverIDSet(command);
			}
		}

		boolean result = NetworkServerManager.openSocket();
		//�ǽð� ������ ��û�Ѵ�.
		if(result) {
			RequestManager.getInstance().requestAllRealTimeData();
			System.out.println("Success requestAllRealTimeData");
		}
		else
			DialogManager.getInstance().showServerErrorConfirmDialog("Server Connetcion Error");

	}

	@FXML
	private void initializeNew() {
		if(!CommandConst.DEBUG) {
			this.nodeList = this.getNodeInfo();
		}

		this.setTree();
		//		this.addTerminalPane();
		//����̹��� ������ �ǽð����� ��û�Ѵ�.(2018.04.26) ��û�������� �߰�
		//		this.getAutoScandata();
	}      


	public void addTabPane(String title,Pane pane,Object controller) {
		System.out.println(Tag+":: addTabPane :: Tab_ID :: "+pane.getId());
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

	//�ý��۱������� ���� Ȱ��ȭ�Ѵ�.
	public void selectSystemTab() {
		//		this.mainTabPane.setDisable(true);
		this.systemConfigTab.setDisable(false);
		this.rootTabPane.getSelectionModel().select(1);		
		if(this.systemConfigPane.getTabs().size()!=0) {
			this.systemConfigPane.getSelectionModel().select(0);
		}
	}

	//�ý��� ����͸� View�� �̹� �߰��Ǿ� ������ title�� �ش�Ǵ� tab�� �����ϵ��� �Ѵ�.
	public void selectTab(String title) {
		this.rootTabPane.getSelectionModel().select(0);
		ObservableList<Tab> tabList =this.mainTabPane.getTabs();
		for(Tab tab:tabList) {
			if(tab.getText().equals(title)) {
				this.mainTabPane.getSelectionModel().select(tab);
				/** ���õ� controller�� mainApp�� ����̹� ���� EventListener�� �޵��� �����Ѵ�.*/
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

	/**
	 * SystemConfigTab�� ������ ����ɶ� �ý��ۼ��� ��带 ������ ������ Ȯ���Ŀ� ���� ������ �����Ѵ�.
	 * @author : YOO YOUNGOK 
	 * @method  selectSystemMonitoringPane
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 8. 14.
	 */
	public void selectSystemMonitoringPane() {
		if(CommandConst.SYSTEM_CONFIG) {
			boolean result = DialogManager.getInstance().showSystemConfigOutConfirmDialog();
			if(result) {
				if(this.rootTabPane.getSelectionModel().getSelectedIndex()==0) {
					System.out.println("selectSystemMonitoringPane Selection changed");
					this.systemConfigTab.setDisable(true);
				}
			}
			else {
				this.rootTabPane.getSelectionModel().select(1);
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
	 * 		   
	 */
	public void selectMainTabPane() {
		this.mainTabPane.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Tab>() {
					@Override
					public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
						System.out.println("Tab Selection changed");
						if(t1!=null && realTimeTabMap.containsKey(t1.getId())){
							System.out.println("�ǽð������� �ʿ��ϴ� : Tab Listener select changed == tabId == "+t1.getId());
							RealTimeEventListener listener = (RealTimeEventListener)realTimeTabMap.get(t1.getId());
							RequestManager.getInstance().setRealTimeEventListener(listener);
						}
						//�ǽð������� ������� ������ listener�� nulló���Ѵ�. 2018.09.04
						else if(t!=null && t1!=null && !realTimeTabMap.containsKey(t1.getId())) {
							System.out.println("�ǽð������� �ʿ���� : Tab Listener select changed == tabId == "+t1.getId());
							RequestManager.getInstance().setRealTimeEventListener(null);
						}

						/** ���õ� controller�� mainApp�� ����̹� ���� EventListener�� �޵��� �����Ѵ�.*/
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
	 * @param mouseEvent //������� ȭ�鿡�� �����ϴ� mouseEvent
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

			if(!CommandConst.SYSTEM_CONFIG && item.getValue().contains("EL")) {
				mainApp.listener=null;
				mainApp.EtherCATPortInfo();
				return;
			}

			if(CommandConst.SYSTEM_CONFIG && item.getValue().contains("EL")) {
				return;
			}

			//�ý��� ȯ�漳�� ����ϰ��� �ƴѰ�츦 �����ؾ���..
			else if(CommandConst.SYSTEM_CONFIG) {
				if(item.getValue().equals("Workspace Justek")) {
					mainApp.Workspace();
				} else if(item.getValue().equals("Controller"))  {
					mainApp.SystemConfiguratorController();
					//Controller�� �ƴѰ��� Driver����(Slave ȭ��)�� ȣ���Ѵ�.
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
							CommandConst.DRIVER_NUMBEER = i-1;
							System.out.println(Tag+"Driver Select Number =="+ CommandConst.DRIVER_NUMBEER);
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
						//EtherCAT ����ȭ���� �ƴѰ�� ���� ����̹��� label�� �����Ѵ�.
						else {
							CommandConst.DRIVER_NUMBEER = i-1;
							CommandConst.DRIVER = item.getValue();
							fxDriverLabel.setText(CommandConst.DRIVER);

							if(this.mainApp.listener!=null) {
								System.out.println("Driver Select == Listener");
								System.out.println(Tag+"Driver Select Number =="+ CommandConst.DRIVER_NUMBEER);
								this.mainApp.listener.clieckMenu();
							}
						}
					}
				}	
			}
		} 
		else if(button==MouseButton.SECONDARY) {

			if(!CommandConst.SYSTEM_CONFIG) return;

			if(item.getValue().startsWith("Driver")) {
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


	/* #################### UI Event Method #########################*/


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
		String message = "Driver�� �߰��մϴ�."; 
		ButtonType foo = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
		ButtonType bar = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);
		Alert alert = new Alert(AlertType.CONFIRMATION,message,foo,bar); 
		alert.setTitle("AddDriver"); 
		alert.setContentText(message); 
		alert.setHeaderText("AddDriver");
		Optional<ButtonType> result = alert.showAndWait(); 		
		if ((result.isPresent()) && (result.get() == foo)) { 
			int count = CommandConst.driverList.size();
			String name = "Driver"+count;
			CommandConst.driverList.add(name);
			CommandConst.DRIVER_MAP.put(name, String.valueOf(count)); // Map���� �߰��Ѵ�.
			this.nodeList.clear();
			this.nodeList = this.getUpdateNodeInfo();
			this.setTree();	
			mainApp.AddDriver(name);
		}
	}

	@FXML
	private void handleRemoveDriver() {
		String message = "Driver�� �����մϴ�."; 
		ButtonType foo = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
		ButtonType bar = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);
		Alert alert = new Alert(AlertType.CONFIRMATION,message,foo,bar); 
		alert.setTitle("RemoveDriver"); 
		alert.setContentText(message); 
		alert.setHeaderText("RemoveDriver");
		Optional<ButtonType> result = alert.showAndWait(); 		
		if ((result.isPresent()) && (result.get() == foo)) { 
			String selectedName = this.treeView.getSelectionModel().getSelectedItem().getValue();
			CommandConst.driverList.remove(selectedName);
			CommandConst.DRIVER_MAP.remove(selectedName); //Map������ �����Ѵ�.
			this.nodeList.clear();
			this.nodeList = getDefaultNodeInfo();
			this.setTree();	
		}

		//		mainApp.AddDriver();
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


	//���Ϳ� ����� EtherCAT������ ������Ʈ�Ѵ�.
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
	 * �ý��� ���� ȭ����� ��ȯ�ؾ���...
	 * CommandConst�� SYSTEM_CONFIG=true..
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
			//			output.append("IOException : " + e.getMessage());
			ErrorLogManager.getInstance().addErrorLog(Tag,"handleMotionIDE",e);
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

		if(this.fxOffLineRadioMenuItem.isSelected()) {
			System.out.println(Tag +" :: handleOffLine"); 
			this.fxSerboModeLabel.setText("Servo Mode : OffLine");
			this.fxIPComboBox.setDisable(true);
		}
		else if(this.fxOnLineRadioMenuItem.isSelected()) {
			System.out.println(Tag +" :: handleONLine"); 
			this.fxSerboModeLabel.setText("Servo Mode : OnLine");
			this.fxIPComboBox.setDisable(false);
		}

		//DEBUG ��� �̸� ������ ���� �ʴ´�...
		if(CommandConst.DEBUG && this.fxOnLineRadioMenuItem.isSelected()) {
			this.setDriverID(); //Driver ID ����
			return;
		}
		else if(CommandConst.DEBUG && this.fxOffLineRadioMenuItem.isSelected()) {
			RequestManager.getInstance().stopAllRealTimeThread();
			return;
		}

		StringBuffer output = new StringBuffer();
		Runtime runtime = Runtime.getRuntime();

		try {
			if(this.fxOnLineRadioMenuItem.isSelected()) {
				runtime.exec("/bin/bash /usr/bin/linuxcnc /home/jscs1/linuxcnc/configs/pmac/pmac_justek_disp.ini");

				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				String command_1 ="ESTOP";
				NetworkServerManager.setDriverIDSet(command_1);

				String command_2 ="ERESET";
				NetworkServerManager.setDriverIDSet(command_2);

				String command_3 ="$$";
				NetworkServerManager.setDriverIDSet(command_3);

				String command_4 ="HOMEALL";
				NetworkServerManager.setDriverIDSet(command_4);
				
				this.setDriverID(); //Driver ID ����

			}
			else if(this.fxOffLineRadioMenuItem.isSelected()) {

				RequestManager.getInstance().stopAllRealTimeThread();
				//				NetworkServerManager.closeSocket();

				//				runtime.exec("/bin/bash /usr/bin/linuxcnc ../linuxcnc/configs/pmac.sim/pmac.ini");
				runtime.exec("/bin/bash /usr/bin/linuxcnc /home/jscs1/linuxcnc/configs/pmac.sim/pmac.ini");
			}
		}
		catch (IOException e) {
			e.printStackTrace();
//			output.append("IOException : " + e.getMessage());	
		}
	}


	/**
	 * @author : YOO YOUNGOK 
	 * @method  onClickServoIPListComboBox
	 * @param event
	 * @return  void
	 * @exception 
	 * @desc ����ڰ� ���ϴ� Servo IP�� �����ϵ��� �Ѵ�.
	 * @since 2018. 8. 14.
	 */
	@FXML
	void onClickServoIPListComboBox(ActionEvent event) {
		CommandConst.address = this.fxIPComboBox.getSelectionModel().getSelectedItem();
		System.out.println(Tag+" :: Selection IP Address => " + CommandConst.address); 

		//		boolean result = NetworkServerManager.openSocket();
		//		//�ǽð� ������ ��û�Ѵ�.
		//		if(result)
		//			RequestManager.getInstance().requestAllRealTimeData();
		//		else
		//			DialogManager.getInstance().showServerErrorConfirmDialog("Server Connetcion Error");

	}

	@FXML
	void onClickSystemMonitor(ActionEvent event) {
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
	void  handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Justek APP");
		alert.setHeaderText("About");
		alert.setContentText("Author: JUSTEK");

		alert.showAndWait();    	
	}

	/**
	 * Closes the application.
	 */
	@FXML
	private void handleExit() {
		System.exit(0);
	}

	/* #################### ��� Method #########################*/

	public void onClickAxisTreeView(MouseEvent mouseEvent) {
		System.out.println(Tag +" :: onClickAxisTreeView"); 
		treeViewManager.setTreeView(axisTreeView); //���� ȭ���� Ȯ���Ǹ� �����Ѵ�...
		treeViewManager.setMainApp(this.mainApp);
		treeViewManager.mouseClick(mouseEvent);
	}

	public void onClickControllerListView(MouseEvent mouseEvent) {
		System.out.println(Tag +" :: onClickControllerListView"); 
		this.controllerTreeViewManager.setMainApp(this.mainApp);
		controllerTreeViewManager.setListView(this.controllerTreeView);
		controllerTreeViewManager.mouseClick(mouseEvent);
	}

	//���佺ĵ�� �ǽ��Ѵ�.
	public void getAutoScandata() {
		if(this.mThread==null) {
			this.mThread = AutoScanThread.getInstance(300);
			this.mThread.addRealTimeEventListener(this);
			this.timer = new SupinanTimer();
		}
		this.mThreadId = timer.addTimer(mThread);
	}

	//���佺ĵ Ÿ�̸Ӹ� �����Ѵ�.
	public void stopAutoScandata() {
		if(this.mThread!=null && this.timer!=null) {
			this.timer.removeTimer(this.mThreadId);
			this.mThread = null;
			this.timer.closeTimer();
		}
	}

	/******************************
	 * �ʱ� system info tree�� ���� �����Ѵ�. -> ���� ����������..
	 * *******************************/
	public ObservableList<TreeItem<String>> getDefaultNodeInfo() {

		ObservableList<TreeItem<String>> nodes = FXCollections.observableArrayList();
		TreeItem<String> nodeA = new TreeItem<>("Controller");
		nodes.add(nodeA);

		int i=1;
		for(String value : CommandConst.driverList) {
			CommandConst.DRIVER_MAP.put(value, String.valueOf(i));
			TreeItem<String> node = new TreeItem<>(value);
			nodes.add(node);
			i++;
		}

		//		TreeItem<String> node3 = new TreeItem<>("EL9800");
		//		nodes.add(node3);
		return nodes;
	}

	public ObservableList<TreeItem<String>> getUpdateNodeInfo() {

		ObservableList<TreeItem<String>> nodes = FXCollections.observableArrayList();
		TreeItem<String> nodeA = new TreeItem<>("Controller");
		nodes.add(nodeA);

		for(String value :CommandConst.DRIVER_MAP.keySet()) {
			TreeItem<String> node = new TreeItem<>(value);
			nodes.add(node);

		}
		//		TreeItem<String> node3 = new TreeItem<>("EL9800");
		//		nodes.add(node3);
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

			CommandConst.DRIVER_MAP.clear(); // HashMap�������� ������... 

			//			br.readLine();
			while ((szLine = br.readLine()) != null) {
				String[] list = szLine.split(" ");

				String driverName = list[8];
				String driverNo = list[0];

				//				if(szLine.contains("EL")) {
				//					TreeItem<String> node = new TreeItem<>("EL9800");
				//					nodes.add(node);
				//					System.out.println(Tag+"EtherCAT Name :: "+szLine);
				//				}
				//				else {
				//					TreeItem<String> node = new TreeItem<>(driverName+"_"+driverNo);
				//					nodes.add(node);

				//������ ���Ͱ� �ƴѰ��� �켱 ����̹�����Ʈ���� �����Ѵ�. => ���� �������� Ȯ���Ұ�...
				if(driverName.contains("SM")) { 
					CommandConst.driverList.add("Driver"+driverNo);
					CommandConst.DRIVER_MAP.put("Driver"+driverNo, driverNo);
					TreeItem<String> node = new TreeItem<>("Driver"+driverNo);
					nodes.add(node);
					
				}
				else {
					CommandConst.DRIVER_MAP.put(driverName, driverNo);
					TreeItem<String> node = new TreeItem<>(driverName);
					nodes.add(node);
				}

				//�� ����̹��� ��ȣ�� key,value������ �����Ѵ�.
				System.out.println(Tag+"EtherCAT Name :: "+szLine);
				//				}
				count++;
			}

//			TreeItem<String> node3 = new TreeItem<>("EL9800");
//			nodes.add(node3);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return nodes;
	}

	/**
	 * @author : YOO YOUNGOK 
	 * @method  updateNodeList
	 * @return  void
	 * @exception 
	 * @desc ����̹� ��帮��Ʈ�� ������Ʈ�Ѵ�.
	 * @since 2018. 8. 14.
	 */
	public void updateNodeList() {
		this.nodeList = this.getUpdateNodeInfo();
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

			//			for(int i=1;i<=CommandConst.driverList.size();i++) {
			//				writer.write("#"+String.valueOf(i)+"k\r\n");
			//			}
			writer.write("estop\r\n");
			writer.flush();

			socketClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//�ǽð� AutoScan������ Ʈ���� �ݿ��Ѵ�.
	public  void setTree() {
		System.out.println("Device Tree ������ ������Ʈ�մϴ�..."); 
		TreeItem<String> root = new TreeItem<>("Workspace Justek",new ImageView(icon));
		root.setExpanded(true);
		root.getChildren().addAll(this.nodeList);
//		TreeItem<String> node3 = new TreeItem<>("EL9800");
//		this.nodeList.add(node3);
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
	 * �ǽð� AutoScan������ Ʈ���� �ݿ��Ѵ�.
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
			System.out.println("AutoScanData�� �޾ƿ��� ���߽��ϴ�...");
		}
	}
	/**
	 * @return the mainTabPane
	 */
	public TabPane getMainTabPane() {
		return mainTabPane;
	}
	/**
	 * @param mainTabPane the mainTabPane to set
	 */
	public void setMainTabPane(TabPane mainTabPane) {
		this.mainTabPane = mainTabPane;
	}
	/**
	 * @return the systemConfigPane
	 */
	public TabPane getSystemConfigPane() {
		return systemConfigPane;
	}
	/**
	 * @param systemConfigPane the systemConfigPane to set
	 */
	public void setSystemConfigPane(TabPane systemConfigPane) {
		this.systemConfigPane = systemConfigPane;
	}




}