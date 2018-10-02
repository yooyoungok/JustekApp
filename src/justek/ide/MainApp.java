package justek.ide;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import justek.ide.view.RootLayoutController;
import justek.ide.view.SDODataViewController;
import justek.ide.view.StatusViewController;
import justek.ide.view.SystemConfiguratorControllerController;
import justek.ide.view.SystemConfiguratorDriveController;
import justek.ide.view.WatchWindowViewController;
import justek.ide.view.WorkspaceController;
import justek.ide.model.CommandConst;
import justek.ide.model.listener.TreeEventListener;
import justek.ide.model.manager.DialogManager;
import justek.ide.model.manager.ErrorLogManager;
import justek.ide.model.manager.NetworkServerManager;
import justek.ide.model.manager.RequestManager;
import justek.ide.utils.OsUtils;
import justek.ide.view.AddControllerController;
import justek.ide.view.AddDriverController;
import justek.ide.view.AddGroupController;
import justek.ide.view.AddVirtualDeviceController;
import justek.ide.view.AxisSetup0Controller;
import justek.ide.view.AxisSetup10Controller;
import justek.ide.view.AxisSetup11Controller;
import justek.ide.view.AxisSetup12Controller;
import justek.ide.view.AxisSetup13Controller;
import justek.ide.view.AxisSetup14Controller;
import justek.ide.view.AxisSetup15Controller;
import justek.ide.view.AxisSetup16Controller;
import justek.ide.view.AxisSetup17Controller;
import justek.ide.view.AxisSetup1Controller;
import justek.ide.view.AxisSetup2Controller;
import justek.ide.view.AxisSetup3Controller;
import justek.ide.view.AxisSetup4Controller;
import justek.ide.view.AxisSetup5Controller;
import justek.ide.view.AxisSetup6Controller;
import justek.ide.view.AxisSetup7Controller;
import justek.ide.view.AxisSetup8Controller;
import justek.ide.view.AxisSetup9Controller;
import justek.ide.view.ClearWorkspaceController;
import justek.ide.view.CompensationTableController;
import justek.ide.view.ControllerConfigurator1Controller;
import justek.ide.view.ControllerConfigurator2Controller;
import justek.ide.view.ControllerConfigurator3Controller;
import justek.ide.view.ControllerConfigurator4Controller;
import justek.ide.view.ControllerConfigurator5Controller;
import justek.ide.view.ControllerConfigurator6Controller;
import justek.ide.view.ControllerConfiguratorController;
import justek.ide.view.ControllerConfiguratorG1Controller;
import justek.ide.view.ControllerConfiguratorG2Controller;
import justek.ide.view.ControllerConfiguratorG3Controller;
import justek.ide.view.ControllerConfiguratorG4Controller;
import justek.ide.view.ControllerMotionController;
import justek.ide.view.EtherCATDiagMasterController;
import justek.ide.view.EtherCATDiagSlaveController;
import justek.ide.view.EtherCATMasterController;
import justek.ide.view.EtherCATSlaveController;
import justek.ide.view.EtherCATSlaveController2;
import justek.ide.view.FirmWareDownloadViewController;
import justek.ide.view.JogController;
import justek.ide.view.LogViewController;
import justek.ide.view.MotionMultiAxisController;
import justek.ide.view.MotionMultiAxisController2;
import justek.ide.view.MotionSingleAxisController;
import justek.ide.view.ParameterComparisonController;
import justek.ide.view.ParametersController;
import justek.ide.view.ParametersExplorerController;
import justek.ide.view.RecorderController;
import justek.ide.view.EditController;
import justek.ide.view.EditController2;
import justek.ide.view.EtherCATBoardViewController;

public class MainApp extends Application {

	static final String Tag = "MainApp";
	
	private Stage primaryStage;
	private AnchorPane rootLayout;

	private AnchorPane mainPane;

	public boolean thread_flag = false;
	public TreeEventListener listener;
	Process p = null;

	final FileChooser fileChooser = new FileChooser();

	RootLayoutController rootLayoutController;

	@Override
	public void start(Stage primaryStage) {
	
		if(OsUtils.isWindow()) {
			CommandConst.isWindow=true;
		}
		else {
			CommandConst.DEBUG = false;
		}
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("JustekApp");
		
		this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				NetworkServerManager.closeSocket();				
				Platform.exit();
				System.exit(0);
			}
		});

		initRootLayout();
	}

	/**
	 * 
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {

			Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();

			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (AnchorPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout,visualBounds.getWidth()-100, visualBounds.getHeight()-100);
			primaryStage.setScene(scene);
			this.rootLayoutController = loader.getController();
			rootLayoutController.setMainApp(this);
			rootLayoutController.addButtonPane();
			rootLayoutController.setButtonPaneVisible(false);
			this.mainPane = rootLayoutController.getMainPane();
			primaryStage.show();

		} catch (IOException e) {			
			e.printStackTrace();
//			ErrorLogManager.getInstance().addErrorLog(Tag,"initRootLayout",e);
		}
	}

	/**
	 * Loads person data from the specified file. The current person data will
	 * be replaced.
	 * 
	 * @param file
	 */
	public void loadWorkspaceFromFile(File file) {

	}

	public void setCenterPane(Pane pane) {
		System.out.println("추가합니다... ");
		this.mainPane.getChildren().clear();
		AnchorPane.setTopAnchor(pane,0.0); // obviously provide your own constraints
		AnchorPane.setBottomAnchor(pane,0.0);
		AnchorPane.setRightAnchor(pane,0.0);
		AnchorPane.setLeftAnchor(pane,0.0);
		this.mainPane.getChildren().add(pane);
	}

	
	public void addTabPane(String title, Pane pane,Object controller) {
		if(this.rootLayoutController.isContainedTab(pane.getId())){
			System.out.println(Tag+"=="+title+" addTabPane 이미 있습니다... ");
			controller = null;
			pane = null;
			this.rootLayoutController.selectTab(title);
		}
		else {
			System.out.println(Tag+" addTabPane 추가합니다... ");
			this.rootLayoutController.addTabPane(title,pane,controller);
		}
	}
	
	public void addSystemConfigTabPane(String title, Pane pane,Object controller) {
		if(this.rootLayoutController.isSystemConfigContainedTab(pane.getId())){
			System.out.println(Tag+" addSystemConfigTabPane 이미 있습니다... ");
			controller = null;
			pane = null;
			this.rootLayoutController.selectSystemTab();
			this.rootLayoutController.selectSystemConfigTab(title);
		}
		else {
			System.out.println(Tag+" addSystemConfigTabPane 추가합니다... ");
			this.rootLayoutController.addSystemConfigTab(pane.getId(),pane,controller);
		}
	}

	public boolean NewWorkspace() {
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/NewWorkspace.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("NewWorkspace");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			//           NewWorkspaceController controller = loader.getController();
			dialogStage.showAndWait();
			//          return controller.isOkClicked();
			return false;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}   
	}

	public void saveWorkspaceToFile(File file) {

	}

	public void ClearWorkspace() {
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ClearWorkspace.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("NewWorkspace");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			dialogStage.showAndWait();
			ClearWorkspaceController controller = loader.getController();

			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void AddDriver(String name) {
		
		if(!CommandConst.SYSTEM_CONFIG) return;

		CommandConst.SYSTEM_CONFIG = true;
		CommandConst.isEtherCAT = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AddDriver.fxml"));
			BorderPane tt = (BorderPane) loader.load();
			AddDriverController controller = loader.getController();
			controller.setMainApp(this);
			controller.setInitialName(name);
			tt.setId("AddDriver");
			this.addSystemConfigTabPane(tt.getId(), tt,controller);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void SaveDriver(String newName) {
		this.rootLayoutController.updateNodeList();
		this.rootLayoutController.setTree();
		DialogManager.getInstance().showConfirmDialogWithResult("Driver 추가 완료하였습니다.");
	}
	
	public void AddController() {
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AddController.fxml"));
			BorderPane bP = (BorderPane) loader.load();
			//			rootLayout.setCenter(bP);
			this.setCenterPane(bP);
			AddControllerController controller = loader.getController();

			controller.setMainApp(this);


		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void RemoveTarget() {
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RemoveTarget.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("NewWorkspace");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			dialogStage.showAndWait();


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ConnectDevice() {
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ConnectDevice.fxml"));
			BorderPane bP = (BorderPane) loader.load();
			//			rootLayout.setCenter(bP);
			this.setCenterPane(bP);


		} catch (IOException e) {
			e.printStackTrace();
		}

	}    

	public void ConnectController() {
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ConnectController.fxml"));
			BorderPane bP = (BorderPane) loader.load();
			//			rootLayout.setCenter(bP);
			this.setCenterPane(bP);


		} catch (IOException e) {
			e.printStackTrace();
		}

	}    

	public void EtherCATMaster() {
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/EtherCATMaster.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
//			this.setCenterPane(bP);
			EtherCATMasterController controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("EtherCATMaster");
			this.addTabPane(tt.getId(), tt,controller);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void EtherCATSlave() {
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/EtherCATSlave.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			//			rootLayout.setCenter(bP);
			EtherCATSlaveController2 controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("EtherCATSlave");
			this.addTabPane(tt.getId(), tt,controller);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void AddGroup() {
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AddGroup.fxml"));
			BorderPane bP = (BorderPane) loader.load();
			//			rootLayout.setCenter(bP);
			this.setCenterPane(bP);
			AddGroupController controller = loader.getController();
			controller.setMainApp(this);


		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void SystemConfiguratorGroup() {
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AddGroup.fxml"));
			BorderPane bP = (BorderPane) loader.load();
			//			rootLayout.setCenter(bP);
			this.setCenterPane(bP);


		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void AddVirtualDevice() {
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AddVirtualDevice.fxml"));
			BorderPane bP = (BorderPane) loader.load();
			//			rootLayout.setCenter(bP);
			this.setCenterPane(bP);
			AddVirtualDeviceController controller = loader.getController();
			controller.setMainApp(this);


		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void SystemConfiguratorVirtualDevice() {
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AddVirtualDevice.fxml"));
			BorderPane bP = (BorderPane) loader.load();
			//			rootLayout.setCenter(bP);
			this.setCenterPane(bP);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*************************
	 * 시스템 정보 화면모드
	 * System Config Tab을 활성화 한다.
	 * 
	 * ************************/
	public void Workspace() {
		this.rootLayoutController.selectSystemTab();
		CommandConst.SYSTEM_CONFIG = true;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Workspace.fxml"));
			BorderPane tt = (BorderPane) loader.load();
			WorkspaceController controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("Workspace");
			this.addSystemConfigTabPane(tt.getId(), tt,controller);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void SystemConfiguratorController() {
		
		if(!CommandConst.SYSTEM_CONFIG) return;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/SystemConfiguratorController.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			SystemConfiguratorControllerController controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("SystemConfiguratorController");
			this.addSystemConfigTabPane(tt.getId(), tt,controller);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void SystemConfiguratorDrive(String driver) {
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/SystemConfiguratorDrive.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			//			rootLayout.setCenter(bP);
			SystemConfiguratorDriveController controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("SystemConfiguratorDrive_"+driver);
			this.addSystemConfigTabPane(tt.getId(), tt,controller);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void EtherCATPortInfo() {
		CommandConst.SYSTEM_CONFIG = false;
		CommandConst.isEtherCAT = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/EtherCATBoardView.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			EtherCATBoardViewController controller = loader.getController();
			tt.setId("T-EtherCATPortInfo");
			this.addTabPane("EtherCATPortInfo", tt,controller);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void AxisSetup() {
		CommandConst.SYSTEM_CONFIG = false;
		CommandConst.isEtherCAT = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AxisSetup0.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			AxisSetup0Controller controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("T-AxisConfigurations");
			this.addTabPane("AxisConfigurations", tt,controller);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void AxisSetup1() {
		CommandConst.SYSTEM_CONFIG = false;
		CommandConst.isEtherCAT = false;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AxisSetup-1.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			AxisSetup1Controller controller = loader.getController();
			controller.setMainApp(this);
//			rootLayoutController.setButtonPaneVisible(true);
			tt.setId("T-MotorSetting");
			this.addTabPane("MotorSetting", tt,controller);


		} catch (IOException e) {
			e.printStackTrace();
		}

	} 


	public void AxisSetup2() {
		CommandConst.SYSTEM_CONFIG = false;
		CommandConst.isEtherCAT = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AxisSetup-2.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			AxisSetup2Controller controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("T-FeedbackSettings");
//			this.setCenterPane(tt);
			this.addTabPane("FeedbackSettings", tt,controller);
		} catch (IOException e) {
			e.printStackTrace();
		}

	} 


	public void AxisSetup3() {
		CommandConst.SYSTEM_CONFIG = false;
		CommandConst.isEtherCAT = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AxisSetup-3.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			AxisSetup3Controller controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("T-UserUnits");
//			this.setCenterPane(tt);
			this.addTabPane("UserUnits", tt,controller);
		} catch (IOException e) {
			e.printStackTrace();
		}

	} 


	public void AxisSetup4() {
		CommandConst.SYSTEM_CONFIG = false;
		CommandConst.isEtherCAT = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AxisSetup-4.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			AxisSetup4Controller controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("T-CurrentLimits");
			this.addTabPane("CurrentLimits", tt,controller);
		} catch (IOException e) {
			e.printStackTrace();
		}

	} 


	public void AxisSetup5() {
		CommandConst.SYSTEM_CONFIG = false;
		CommandConst.isEtherCAT = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AxisSetup-5.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			AxisSetup5Controller controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("T-MotionLimits");
			this.addTabPane("MotionLimits", tt,controller);

			
		} catch (IOException e) {
			e.printStackTrace();
		}

	} 


	public void AxisSetup6() {
		CommandConst.SYSTEM_CONFIG = false;
		CommandConst.isEtherCAT = false;		
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AxisSetup-6.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			AxisSetup6Controller controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("T-Protections");
			this.addTabPane("Protections", tt,controller);
		} catch (IOException e) {
			e.printStackTrace();
		}

	} 


	public void AxisSetup7() {
		CommandConst.SYSTEM_CONFIG = false;
		CommandConst.isEtherCAT = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AxisSetup-7.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			AxisSetup7Controller controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("T-SettingWindow");
			this.addTabPane("SettingWindow", tt,controller);

		} catch (IOException e) {
			e.printStackTrace();
		}

	} 


	public void AxisSetup8() {
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AxisSetup-8.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			this.setCenterPane(tt);
			AxisSetup8Controller controller = loader.getController();
			controller.setMainApp(this);


		} catch (IOException e) {
			e.printStackTrace();
		}

	} 


	public void AxisSetup9() {
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AxisSetup-9.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			this.setCenterPane(tt);
			AxisSetup9Controller controller = loader.getController();
			controller.setMainApp(this);


		} catch (IOException e) {
			e.printStackTrace();
		}

	} 


	public void AxisSetup10() {
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AxisSetup-10.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			this.setCenterPane(tt);
			AxisSetup10Controller controller = loader.getController();
			controller.setMainApp(this);


		} catch (IOException e) {
			e.printStackTrace();
		}

	} 


	public void AxisSetup11() {
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AxisSetup-11.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			AxisSetup11Controller controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("T-Current-Verification-Time");
			this.addTabPane("Current-Verification-Time", tt,controller);


		} catch (IOException e) {
			e.printStackTrace();
		}

	} 


	public void AxisSetup12() {
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AxisSetup-12.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			AxisSetup12Controller controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("T-Commutation");
			this.addTabPane("Commutation", tt,controller);

		} catch (IOException e) {
			e.printStackTrace();
		}

	} 


	public void AxisSetup13() {
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AxisSetup-13.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			this.setCenterPane(tt);
			AxisSetup13Controller controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	} 


	public void AxisSetup14() {
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AxisSetup-14.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			this.setCenterPane(tt);
			AxisSetup14Controller controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	} 


	public void AxisSetup15() {
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AxisSetup-15.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			this.setCenterPane(tt);
			AxisSetup15Controller controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	} 


	public void AxisSetup16() {        
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AxisSetup-16-2.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			AxisSetup16Controller controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("T-Verification-Time");
			this.rootLayoutController.putControllerMap(tt.getId(), controller);
			this.addTabPane("Verification-Time", tt,controller);

		} catch (IOException e) {
//			e.printStackTrace();
			
			ErrorLogManager.getInstance().addErrorLog(Tag,"AxisSetup16",e);
		}

	} 


	public void AxisSetup17() {
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AxisSetup17.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			this.setCenterPane(tt);
			AxisSetup17Controller controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	} 


	public void Edit() {
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Edit2.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			EditController2 controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("Edit");
			this.addTabPane("Edit", tt,controller);
		} catch (IOException e) {
			e.printStackTrace();
		}

	} 

	public void MotionSingleAxis() {
		CommandConst.SYSTEM_CONFIG = false;
		CommandConst.isController = false;
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/MotionSingleAxis.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			MotionSingleAxisController controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("SingleAxis");
			this.rootLayoutController.putControllerMap(tt.getId(), controller);
			this.addTabPane("SingleAxis", tt,controller);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void MotionMultiAxis() {
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/MotionMultiAxis_2.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			MotionMultiAxisController2 controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("MultiAxis");			
			this.rootLayoutController.putControllerMap(tt.getId(), controller);			
			this.addTabPane("MultiAxis", tt,controller);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void Parameters() {
		CommandConst.SYSTEM_CONFIG = false;

		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			//            loader.setLocation(MainApp.class.getResource("view/Parameters.fxml"));
			//          BorderPane bP = (BorderPane) loader.load();
			loader.setLocation(MainApp.class.getResource("view/SDODataView.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			SDODataViewController controller = loader.getController();
			controller.setMainApp(this);
			this.rootLayoutController.setButtonPaneVisible(false);
			tt.setId("Parameter");
			this.addTabPane("Parameter", tt,controller);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void ParameterComparison() {
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ParameterComparison2.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			ParameterComparisonController controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("ParameterComparison");
			this.addTabPane("ParameterComparison", tt,controller);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void CompensationTable() {
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CompensationTable.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			this.setCenterPane(tt);
			CompensationTableController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void ControllerConfigurator() {
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ControllerConfigurator.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			ControllerConfiguratorController controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("C-ControllerConfigurator");
			this.addTabPane("ControllerConfigurator", tt,controller);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void ControllerConfigurator1() {
		this.thread_flag = false;
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ControllerConfigurator-1.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
//			this.setCenterPane(tt);
			ControllerConfigurator1Controller controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("C-UserUnits");
			this.addTabPane("UserUnits", tt,controller);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ControllerConfigurator2() {
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ControllerConfigurator-2.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
//			this.setCenterPane(tt);
			ControllerConfigurator2Controller controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("C-LimitsProtections");
			this.addTabPane("Limits_Protections", tt,controller);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void ControllerConfigurator3() {
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ControllerConfigurator-3.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			ControllerConfigurator3Controller controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("C-MotionLimits");
			this.addTabPane("MotionLimits", tt,controller);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void ControllerConfigurator4() {
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ControllerConfigurator-4.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			ControllerConfigurator4Controller controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("C-SettlingWindow");
			this.addTabPane("SettlingWindow", tt,controller);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void ControllerConfigurator5() {
		this.thread_flag = false;
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ControllerConfigurator-5.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			ControllerConfigurator5Controller controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("C-FastReference");
			this.addTabPane("FastReference", tt,controller);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void ControllerConfigurator6() {
		this.thread_flag = false;
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ControllerConfigurator-6.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			ControllerConfigurator6Controller controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("C-ErrorPolicies");
			this.addTabPane("ErrorPolicies", tt,controller);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void ControllerConfiguratorG1() {
		this.thread_flag = false;
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ControllerConfiguratorG-1.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			ControllerConfiguratorG1Controller controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("G-UserUnits");
			this.addTabPane("UserUnits", tt,controller);


		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void ControllerConfiguratorG2() {
		this.thread_flag = false;
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ControllerConfiguratorG-2.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			ControllerConfiguratorG2Controller controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("G-Protections");
			this.addTabPane("Protections", tt,controller);


		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void ControllerConfiguratorG4() {
		this.thread_flag = false;
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ControllerConfiguratorG-4.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			ControllerConfiguratorG4Controller controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("G-SettlingWindow");
			this.addTabPane("SettlingWindow", tt,controller);


		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void ControllerConfiguratorG3() {
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ControllerConfiguratorG-3.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			ControllerConfiguratorG3Controller controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("G-MotionLimits");
			this.addTabPane("MotionLimits", tt,controller);


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ControllerMotion() {
		this.thread_flag = false;
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ControllerMotion.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			this.setCenterPane(tt);
			ControllerMotionController controller = loader.getController();
			controller.setMainApp(this);


		} catch (IOException e) {
			e.printStackTrace();
		}    
	}


	public void EtherCATDiagMaster() {
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/EtherCATDiagMaster.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			EtherCATDiagMasterController controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("EtherCATDiagMaster");
			this.addTabPane("EtherCATDiagMaster", tt,controller);


		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void EtherCATDiagSlave() {
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/EtherCATDiagSlave.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			EtherCATDiagSlaveController controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("EtherCATDiagSlave");
			this.addTabPane("EtherCATDiagSlave", tt,controller);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ParametersExplorer() {
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Parameters.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			this.setCenterPane(tt);
			ParametersController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void ControllerCompensationTable() {
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CompensationTable.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			this.setCenterPane(tt);
			CompensationTableController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void Jog() {
		this.thread_flag = false;
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Jog.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			JogController controller = loader.getController();
			tt.setId("Jog");
			controller.setMainApp(this);
			this.addTabPane("Jog", tt,controller);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void StatusView() {
		this.thread_flag = false;
		CommandConst.SYSTEM_CONFIG = false;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/WatchWindowView.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			WatchWindowViewController controller = loader.getController();
			tt.setId("T-WatchWindowView");
			controller.setMainApp(this);
			this.rootLayoutController.putControllerMap(tt.getId(), controller);			
			this.addTabPane("WatchWindowView", tt,controller);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handleDownloadFirmWare() {
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/FirmWareDownloadView.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			FirmWareDownloadViewController controller = loader.getController();
			tt.setId("T-FirmWareDownload");
			controller.setMainApp(this);
			this.addTabPane("FirmWareDownload", tt,controller);

		} catch (IOException e) {
			ErrorLogManager.getInstance().addErrorLog(Tag,"handleDownloadFirmWare",e);
		}
	}

	public void handleRecorder() {
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Recorder.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			RecorderController controller = loader.getController();
			tt.setId("T-Recorder");
			controller.setMainApp(this);
			this.addTabPane("Recorder", tt,controller);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handleUploadDriver() {
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/SDODataView.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			SDODataViewController controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("UploadDriver");
			this.addTabPane("UploadDriver", tt,controller);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handleLogHistory() {
		CommandConst.SYSTEM_CONFIG = false;
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/LogView.fxml"));
			AnchorPane tt = (AnchorPane) loader.load();
			LogViewController controller = loader.getController();
			controller.setMainApp(this);
			tt.setId("LogHistory");
			this.addTabPane("LogHistory", tt,controller);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	public void handleShowStatistics() {


		//    	ProcessBuilder pb = new ProcessBuilder("C:/Octave/Octave-4.2.1/bin/octave.exe","/octave/fact.m");
		Runtime run = Runtime.getRuntime();

		try {
			//			Process p = run.exec("cmd /c start  C:/Octave/Octave-4.2.1/bin/octave-cli.exe ");
			//    		Process p = run.exec(" NotePad.exe ");
			//    		Process p = run.exec("octave.bat");
			String filName = this.openFileChoolser();
			if(filName==null) return;

			if(CommandConst.DEBUG) {
				p = run.exec("cmd /c start  C:/Octave/Octave-4.2.1/bin/octave-cli.exe --persist ./octave/"+filName);

			}
			else {
				System.out.println("handleShow Octave");
				p = run.exec("xterm -hold -e octave --persist ./octave/"+filName);
//				p = run.exec("octave --persist ./octave/"+filName);
			}


			//    		pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public String openFileChoolser() {
		String result = null;
		//		 fileChooser.setInitialDirectory(new File("C:\\Users\\cty\\eclipse-workspace\\JustekApp\\octave"));

		if(CommandConst.isWindow) {
			fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")+"/octave"));
		}

		fileChooser.getExtensionFilters().clear();
		// Add Extension Filters
		fileChooser.getExtensionFilters().addAll(//
				new FileChooser.ExtensionFilter("m", "*.m")); //

		File file = fileChooser.showOpenDialog(this.getPrimaryStage());

		if (file != null) {
			result = file.getName();
		}	

		return result;
	}


	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}


	public static void main(String[] args) {
		launch(args);
	}
}
