package justek.ide.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import justek.ide.MainApp;
import justek.ide.model.ButtonCell;
import justek.ide.model.CommandConst;
import justek.ide.model.FileTreeItem;
import justek.ide.model.PLCInfo;
import justek.ide.model.manager.DialogManager;
import justek.ide.model.manager.NetworkServerManager;

public class EditController2 extends BaseController {

	static final String Tag = "EditController2";
	final FileChooser fileChooser = new FileChooser();
	private ObservableList<PLCInfo> plcData;
	private ObservableList<String> plcList = FXCollections.observableArrayList();    
	private HashMap<String,String> plcMap = new HashMap<String,String>();

	Image delete_image = new Image(getClass().getResourceAsStream("/img/delete.png"));

	@FXML
	private ListView<String> listView;
	@FXML
	private TextField fxInputTextField;
	@FXML
	private TextArea consoleTextArea;
	@FXML
	private TextArea errorTextArea;
	@FXML
	private TextArea editTextArea;

	@FXML
	private TabPane tabPane;
	@FXML
	private TabPane consolePane;
	@FXML
	private Tab errorTab;

	@FXML
	private Button clear_button;

	@FXML
	private TableView<PLCInfo> tableView;
	@FXML
	private TableColumn<PLCInfo, String> buttonColumn;
	@FXML
	private TableColumn<PLCInfo, String> nameColumn;

	private Callback<TableColumn<PLCInfo, String>, TableCell<PLCInfo, String>> cellFactory ;

	private MainApp mainApp;


	@FXML
	private void initialize() {
		
		this.plcData = FXCollections.observableArrayList();
		this.checkPlcStatus(); // plc 프로그램의 상태를 불러온다.

		//Table Setting!
		cellFactory  = (TableColumn<PLCInfo, String> param) -> new ButtonCell(this.listView);
		this.nameColumn.setCellValueFactory(cellData -> cellData.getValue().name);
		this.buttonColumn.setCellFactory(cellFactory);

		this.setView();
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	} 


	/**
	 * @author : YOO YOUNGOK 
	 * @method  checkPlcStatus
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 8. 27.
	 */
	private void checkPlcStatus() {
		this.plcList = NetworkServerManager.getInstance().checkAllPlcFile();
		if(this.plcList==null) return;
		
		for(String value : plcList) {
			String[] parsing = value.split(" "); // [.../10.plc R] 의 형태로..제공됨
			String[] plcName = parsing[0].split("/");
			String name = plcName[plcName.length-1];
			System.out.println(Tag+"======== checkPlcStatus = name");
			this.plcMap.put(name, parsing[1]);
		}
		
		this.setHomeTableView();
	}

	public void setView() {
		this.clear_button.setGraphic(new ImageView(this.delete_image));
	}

	File[] fileList;
	public void setHomeTableView() {
		
		File file = null;
		if(CommandConst.isWindow) {
			file = new File("./home/");
		}
		else {
			file = new File("/home/jscs1/linuxcnc/configs/pmac/programs/");
		}
		fileList = file.listFiles();
		if(fileList==null)return;
		// Loop through each file and directory in the fileList.


		this.sortByNumber();

		this.plcData.clear(); // plc파일 리스트를 초기화 한다...

		for (int i = 0; i < fileList.length; i++) {
			if(!fileList[i].isHidden()) {
				if(fileList[i].getName().endsWith(".plc")) {
					PLCInfo info = new PLCInfo();
					info.setName(fileList[i].getName());
					info.setPath(fileList[i].getAbsolutePath());
					if(this.plcMap.containsKey(fileList[i].getName())) {
						String value = this.plcMap.get(info.getName());
						if(value != null) {
							if(value.equals("R")) {
								info.isRun = true;
							}
							else {
								info.isRun = false;
							}
						}
					}
					this.plcData.add(info);
				}
			}
		}
		
		this.tableView.getItems().clear();
		this.tableView.getItems().addAll(plcData);
	}

	//파일명순으로 정렬...
	private void sortByNumber() {
		System.out.println("sortByNumber");

		Arrays.sort(fileList, new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				int n1 = extractNumber(o1.getName());
				int n2 = extractNumber(o2.getName());
				return n1 - n2;
			}

			private int extractNumber(String name) {
				int i = 0;
				try {
					int e = name.lastIndexOf('.');
					String number = name.substring(0, e);
					i = Integer.parseInt(number);
				} catch(Exception e) {
					i = 100; // if filename does not match the format
				}
				return i;
			}
		});
	}

	public void addTextAreaChangeEvent(Tab tab) {
		TextArea textArea = (TextArea)tab.getContent();
		textArea.textProperty().addListener((obs,old,niu)->{
		});
	}

	@FXML
	public void saveAsFile() {
		Tab tab = this.tabPane.getSelectionModel().getSelectedItem();
		if(tab==null) return;
		this.saveFileChoolser();
		this.plcData.clear();
		this.tableView.getItems().clear();
		this.setHomeTableView();
	}

	@FXML
	public void saveFile() {
		Tab tab = this.tabPane.getSelectionModel().getSelectedItem();
		if(tab==null) return;

		if(tab.getId()!=null) {
			TextArea content2 = (TextArea)tab.getContent();
			System.out.println(tab.getId());
			File file = new File(tab.getId());
			this.saveTextToFile(content2.getText().toString(), file);
		}
		else {
			this.saveFileChoolser();
		}
		this.plcData.clear();
		this.tableView.getItems().clear();
		this.setHomeTableView();
	}

	@FXML
	public void closeTab() {
		Tab tab = this.tabPane.getSelectionModel().getSelectedItem();
		if(tab==null) return;

		if(tab.getId()==null) {
			TextArea content2 = (TextArea)tab.getContent();
			if(content2.getText()==null || content2.getText().trim().equals("")) {
				tabPane.getTabs().remove(tabPane.getSelectionModel().getSelectedIndex());
			}
			else {
				this.saveFileChoolser();
				tabPane.getTabs().remove(tabPane.getSelectionModel().getSelectedIndex());
				this.plcData.clear();
				this.tableView.getItems().clear();
				this.setHomeTableView();
			}
		}
		else if(tab.getId()!=null) {
			TextArea content2 = (TextArea)tab.getContent();
			System.out.println(tab.getId());
			File file = new File(tab.getId());
			this.saveTextToFile(content2.getText().toString(), file);
			tabPane.getTabs().remove(tabPane.getSelectionModel().getSelectedIndex());
		}
	}

	@FXML
	public void addNewFile() {
		Tab tab = new Tab();
		TextArea textarea = new TextArea();
		tab.setContent(textarea);
		tab.setText("New File "+tabPane.getTabs().size());
		tabPane.getTabs().add(tab);
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(tab);
		this.addTextAreaChangeEvent(tab);
	}

	@FXML
	public void clearConsole() {
		Tab tab = this.consolePane.getSelectionModel().getSelectedItem();
		TextArea content2 = (TextArea)tab.getContent();
		content2.clear();
	}

	@FXML
	public void mouseEvent(MouseEvent event) {

		if (event.getClickCount() == 2) {
			if(event.getSource().equals(this.tableView)) {
				this.openFileAtTableView(); 
			}
			else if(event.getSource().equals(this.listView)){
				//Use ListView's getSelected Item
				String selString = listView.getSelectionModel().getSelectedItem().toString();
				System.out.println("Select Item=="+selString);
				this.consoleTextArea.appendText("\n Justek> "+ selString);
				this.runCommand(selString);
			}
		}
	}


	int line_count=0; //TextArea count
	@FXML
	public void keyEvent(KeyEvent event) {
		
		if (event.getCode()== KeyCode.ENTER) {
			if(event.getSource().equals(this.fxInputTextField)) {
				String textData = this.fxInputTextField.getText();
				this.consoleTextArea.appendText("\n Justek> "+textData);
				this.fxInputTextField.clear();
				this.runCommand(textData);
			}
			return;
		}

		if (event.getCode()== KeyCode.ENTER) {
			if(event.getSource().equals(this.consoleTextArea)) {
				//Use ListView's getSelected Item

				String[] s = this.consoleTextArea.getText().split("\n");
				String selString ="";
				if(s.length==-1) return;

				if(s.length>line_count) { 
					selString = s[s.length-1];
					if(selString.equals("") || selString.contains("Response")) {
						return;
					}
				}

				line_count = s.length;

				String[] s2 = selString.split("\n");
				if(s2.length==-1) return;
				if(selString.equals("")) return;

				System.out.println("Input Test=="+selString);	             
				this.runCommand(selString);
			}
			//use this to do whatever you want to. Open Link etc.
		}
	}

	@FXML
	public void deleteAtTreeView() {
		PLCInfo info = (PLCInfo)this.tableView.getItems().get(this.tableView.getSelectionModel().getSelectedIndex());
		File file = new File(info.getPath());

		ButtonType foo = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
		ButtonType bar = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);

		Alert alert = new Alert(AlertType.CONFIRMATION,
				"Delete Selected File! Are you sure? ",
				foo,
				bar);

		alert.setTitle("Confirmation");
		alert.setHeaderText("Delete File");
		Optional<ButtonType> result = alert.showAndWait();
		if ((result.isPresent()) && (result.get() == foo)) {
			boolean result1 = file.delete();
			if(result1) {
				ObservableList<Tab> tabList =this.tabPane.getTabs();
				for(Tab tab:tabList) {
					if(tab.getId()!=null&&  tab.getId().contains(file.getName())) {
						this.tabPane.getTabs().remove(tab);
						break;
					}
				}
			}
		}
		this.plcData.clear();
		this.tableView.getItems().clear();
		this.setHomeTableView();
	}

	@FXML
	public void openFileChoolser() {

		//		 fileChooser.setInitialDirectory(new File(System.getProperty("./home/")));

		fileChooser.setInitialDirectory(new File("./home/"));

		fileChooser.getExtensionFilters().clear();
		// Add Extension Filters
		fileChooser.getExtensionFilters().addAll(//
				new FileChooser.ExtensionFilter("PLC", "*.plc")); //

		File file = fileChooser.showOpenDialog(this.mainApp.getPrimaryStage());

		if (file != null) {
			openFile(file);
		}
	}

	public void openFileAtTableView() {
		PLCInfo info = (PLCInfo)this.tableView.getItems().get(this.tableView.getSelectionModel().getSelectedIndex());
		File file = new File(info.getPath());
		if(file.getName().endsWith(".plc" )) {
			this.openFile(file);
		}
	}

	@FXML
	public void saveFileChoolser() {

		fileChooser.getExtensionFilters().clear();
		// Add Extension Filters
		fileChooser.getExtensionFilters().addAll(//
				new FileChooser.ExtensionFilter("PLC", "*.plc")); //

		Tab tab = this.tabPane.getSelectionModel().getSelectedItem();
		TextArea content = (TextArea)tab.getContent();
//		fileChooser.setInitialDirectory(new File("./home/"));
		fileChooser.setInitialDirectory(new File("/home/jscs1/linuxcnc/configs/pmac/programs/"));
		File file = fileChooser.showSaveDialog(this.mainApp.getPrimaryStage());

		System.out.println("addNode saveFileChoolser Item=="+file.getAbsolutePath());
		if (file != null) {
			if(tab.getId()!=null) {
				saveTextToFile(content.getText().toString(),file);
				this.openFile(file);
			}
			else {
				saveTextToFile(content.getText().toString(),file);
				tab.setText(file.getName());
				tab.setId(file.getAbsolutePath());
			}
		}
	}

	public void addNode(FileTreeItem node) {
		// Get a list of files.
		File fileInputDirectoryLocation = new File(node.getFullPath());
		File fileList[] = fileInputDirectoryLocation.listFiles();
		System.out.println("addNode Select Item=="+fileInputDirectoryLocation.getName());

		if(fileList==null)return;
		// Loop through each file and directory in the fileList.
		for (int i = 0; i < fileList.length; i++) {
			Path path = fileList[i].toPath();
			FileTreeItem treeNode=new FileTreeItem(path);
			if(!fileList[i].isHidden()) {
				if(fileList[i].getName().endsWith(".plc"))
					node.getChildren().add(treeNode);
			}
		}
	}

	private void openFile(File file) {
		Tab tab = new Tab();
		TextArea textArea = new TextArea();
		tab.setContent(textArea);
		tab.setText(file.getName());
		tab.setId(file.getAbsolutePath());
		tabPane.getTabs().add(tab);
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(tab);
		this.addTextAreaChangeEvent(tab);
		try {
			BufferedReader uc =  new BufferedReader(new FileReader(file));
			String s = null;
			while((s=uc.readLine()) != null) {	
				textArea.appendText(s+"\n");
			}
			uc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveTextToFile(String content, File file) {
		try {
			PrintWriter writer;
			writer = new PrintWriter(file);
			writer.println(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void runCommand(String runString) {
		System.out.println("Client: " + " runCommand :: Connection Established == " +runString);
		this.listView.getItems().add(runString);
		
		String[] parsingValue = runString.split(" ");
		String plcName = parsingValue[parsingValue.length-1];
		
		if(runString.contains("enable")) {
//			해당 PLC가 실행되는지 먼저 확인을 한다..
			if(NetworkServerManager.getInstance().checkPlcFile(plcName)) {
				DialogManager.getInstance().showServerErrorConfirmDialog("현재 해당 PLC가 실행중입니다.");
				this.checkPlcStatus();
				return;
			}
		}
		
		try {
			Socket socketClient = new Socket(CommandConst.address, 12345);
			String sendMsg = runString+"\r\n";
			String serverMsg;
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(sendMsg);
			writer.flush();

			if(!runString.contains("plc")) {
				serverMsg = reader.readLine();
				if(serverMsg!=null) {
					System.out.println(serverMsg);
					serverMsg = reader.readLine();
					System.out.println(serverMsg);
					this.consoleTextArea.appendText("\n Justek> "+serverMsg);
				}
			}

			socketClient.close();
			System.out.println("Client: " + " runCommand :: Finish == " +sendMsg);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setData() {
		this.setHomeTableView();
	}

}
