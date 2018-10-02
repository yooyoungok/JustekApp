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
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import justek.ide.MainApp;
import justek.ide.model.CommandConst;
import justek.ide.model.FileTreeItem;

public class EditController {
	
	private ObservableList<String> logList = FXCollections.observableArrayList();
	final FileChooser fileChooser = new FileChooser();
	private Desktop desktop = Desktop.getDesktop();
	
	Image delete_image = new Image(getClass().getResourceAsStream("/img/delete.png"));
	
	@FXML
	private ListView<String> listView;
	@FXML
	private ListView<String> consoleListView;
	@FXML
	private TextArea consoleTextArea;
	@FXML
	private TextArea errorTextArea;
	@FXML
	private TextArea editTextArea;
	@FXML
	private TreeView<String> treeView;
	@FXML
	private TabPane tabPane;
	@FXML
	private TabPane consolePane;
	@FXML
	private Tab errorTab;
	
	@FXML
	private Button clear_button;
	
	private MainApp mainApp;
	
	
	 @FXML
	    private void initialize() {
		 System.out.println("initialize");
		 logList.add("test1");
		 logList.add("test2");
		 listView.setItems(logList);
		 
//		 this.setConsoleListView();
		 
		 this.setHomeTreeView();
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
	
	public void setView() {
		this.clear_button.setGraphic(new ImageView(this.delete_image));
	}
	
	public void setHomeTreeView() {
		 String hostName="Project";
		 File file = new File("./home/");
		 String home = System.getProperty("user.home");
		 System.out.println(home);
		 FileTreeItem rootNode=new FileTreeItem(hostName);
		 FileTreeItem treeNode=new FileTreeItem(file.toPath());
		 rootNode.getChildren().add(treeNode);
		 this.treeView.setRoot(rootNode);
		 this.addNode(treeNode);
		    
		 rootNode.setExpanded(true);
		 treeNode.setExpanded(true);
	}
	public void setDefaultTreeView() {
		   //setup the file browser root
	    String hostName="computer";
	    try{hostName=InetAddress.getLocalHost().getHostName();}catch(UnknownHostException x){}
	    TreeItem<String> rootNode=new TreeItem<>(hostName);
	    
	    Iterable<Path> rootDirectories=FileSystems.getDefault().getRootDirectories();
	    for(Path name:rootDirectories){
	      FileTreeItem treeNode=new FileTreeItem(name);
	      rootNode.getChildren().add(treeNode);
	    }
	    
	    rootNode.setExpanded(true);
	    this.treeView.setRoot(rootNode);
	    
	}
	
	
	public void refreshTree() {
		this.setHomeTreeView();
	}
	
	public void setConsoleListView() {
		
		consoleListView.setEditable(true);
		consoleListView.setCellFactory(TextFieldListCell.forListView());
		consoleListView.getItems().add("");

		consoleListView.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>() { 
			@Override 
			public void handle(ListView.EditEvent<String> t) { 
				consoleListView.getItems().set(t.getIndex(), t.getNewValue()); 
				System.out.println("setOnEditCommit"); 
			} 
		}); 

		consoleListView.setOnEditCancel(new EventHandler<ListView.EditEvent<String>>() { 
			@Override 
			public void handle(ListView.EditEvent<String> t) { 
				System.out.println("setOnEditCancel"); 
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
	}
	
	@FXML
	public void closeTab() {
		Tab tab = this.tabPane.getSelectionModel().getSelectedItem();
		if(tab==null) return;
		
		if(tab.getId()!=null) {
			TextArea content2 = (TextArea)tab.getContent();
			System.out.println(tab.getId());
			File file = new File(tab.getId());
			this.saveTextToFile(content2.getText().toString(), file);
			tabPane.getTabs().remove(tabPane.getSelectionModel().getSelectedIndex());
		}
		else {
			this.saveFileChoolser();
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
		
		if(treeView.getSelectionModel().getSelectedIndex()==0){return;}
		
		 if (event.getClickCount() == 2) {
			 if(event.getSource().equals(this.treeView)) {
			        FileTreeItem item = (FileTreeItem)treeView.getSelectionModel().getSelectedItem();
			        if(item.isDirectory()) {
			        	if(item.isLeaf()){
			        		this.addNode(item);
			        		 item.setExpanded(true);
			        	}
			        }
			        else {
			        	this.openFileAtTreeView();
			        }
			       
			        System.out.println("Selected File Path =="+item.getFullPath());
			 }
			 else {
	           //Use ListView's getSelected Item
	           String selString = listView.getSelectionModel().getSelectedItem().toString();
	           System.out.println("Select Item=="+selString);
	           this.consoleTextArea.appendText(selString+"\n");
//	           this.consoleTextArea.requestFocus();
//	           this.consoleListView.getItems().add(selString);
//	           this.listView.getItems().add(selString);
	        }
		 }
		 else if(event.getButton()==MouseButton.SECONDARY) {
			 if(event.getSource().equals(this.treeView)) {
				 FileTreeItem sel_item = (FileTreeItem)this.treeView.getSelectionModel().getSelectedItem();
				 if(sel_item.getFullPath().endsWith(".plc")) {
					 this.treeView.getContextMenu().getItems().get(2).setVisible(true);
				 }
				 else {
					 this.treeView.getContextMenu().getItems().get(2).setVisible(false);
				 }
			 }
		 }
	}
	
	
	int line_count=0; //TextArea count
	@FXML
	public void keyEvent(KeyEvent event) {
//		if(event.getSource().equals(consoleListView)) {
//			if(event.getCode()==KeyCode.ENTER){
//				int count = this.consoleListView.getItems().size();
//				String commandString = this.consoleListView.getItems().get(count-1);
//				consoleListView.getItems().add("");
//				this.runCommand(commandString);
//			}
//		}
		
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
		FileTreeItem node = (FileTreeItem)this.treeView.getSelectionModel().getSelectedItem();
		File file = new File(node.getFullPath());

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
					System.out.println("Delete Tab=="+tab.getId());
					if(tab.getId().equals(file.getAbsolutePath())) {
						this.tabPane.getTabs().remove(tab);
						break;
					}
				}
			}
		}
		this.refreshTree();
	}
	
	@FXML
	public void openFileChoolser() {
		 fileChooser.setInitialDirectory(new File(System.getProperty("./home/")));
		 
		 fileChooser.getExtensionFilters().clear();
	     // Add Extension Filters
	      fileChooser.getExtensionFilters().addAll(//
	              new FileChooser.ExtensionFilter("All Files", "*.*"), //
	              new FileChooser.ExtensionFilter("PROG", "*.prog"), //
	              new FileChooser.ExtensionFilter("PLC", "*.plc")); //
	      
		 File file = fileChooser.showOpenDialog(this.mainApp.getPrimaryStage());
		 
	       if (file != null) {
               openFile(file);
           }
	}
	
	@FXML
	public void openFileAtTreeView() {
		FileTreeItem node = (FileTreeItem)this.treeView.getSelectionModel().getSelectedItem();
		File file = new File(node.getFullPath());
		if(file.getName().endsWith(".plc") || file.getName().endsWith(".txt") || file.getName().endsWith(".prog") ) {
			this.openFile(file);
		}
	}
	
	@FXML
	public void saveFileChoolser() {
		
		fileChooser.getExtensionFilters().clear();
	     // Add Extension Filters
	      fileChooser.getExtensionFilters().addAll(//
	              new FileChooser.ExtensionFilter("All Files", "*.*"), //
	              new FileChooser.ExtensionFilter("PROG", "*.prog"), //
	              new FileChooser.ExtensionFilter("PLC", "*.plc")); //
	      
		Tab tab = this.tabPane.getSelectionModel().getSelectedItem();
		TextArea content = (TextArea)tab.getContent();
		 fileChooser.setInitialDirectory(new File("./home/"));
		 File file = fileChooser.showSaveDialog(this.mainApp.getPrimaryStage());
		 
	       if (file != null) {
	    	   saveTextToFile(content.getText().toString(),file);
	   			tab.setText(file.getName());
	   			tab.setId(file.getAbsolutePath());
	   			this.refreshTree();
           }
	}
	
	@FXML
	public void runFileAtTree() {
		FileTreeItem node = (FileTreeItem)this.treeView.getSelectionModel().getSelectedItem();
		File file = new File(node.getFullPath());
		if(CommandConst.DEBUG) {
			try {
				this.desktop.open(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			if(file.getName().endsWith(".plc" )) {
				this.runFile(file.getName());
			}
		}
	}
	
	//Not Use
	@FXML
	public void runCommandAtConsole(KeyEvent event) {
		if(event.getSource().equals(consoleListView)) {
			if(event.getCode()==KeyCode.ENTER){
				int count = this.consoleListView.getItems().size();
				String commandString = this.consoleListView.getItems().get(count-1);
				this.runCommand(commandString);
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
	
	private void runFile(String fullPath) {
		this.runPlcFile(fullPath);
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
    
    public void runPlcFile(String runString) {
    	try {
    		Socket socketClient = new Socket(CommandConst.address, 12345);
    		int dot = runString.indexOf('.');
    		String runString2 = runString.substring(0, dot);
    		System.out.println("Client: runPlcFile ==" +runString2);

//    		BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
    		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

    		String serverMsg = null;
    		writer.write("enable plc "+runString+"\r\n");
    		writer.flush();
//
//    		serverMsg = reader.readLine();
//    		if (serverMsg != null) {
//    			System.out.println(serverMsg);
//    		}

    		socketClient.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
	public void runCommand(String runString) {
		System.out.println("Client: " + " runCommand :: Connection Established == " +runString);
		 this.listView.getItems().add(runString);
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
					
					if(serverMsg.contains("ERR")) {
						this.consolePane.getSelectionModel().select(this.errorTab);
						this.errorTextArea.appendText("Request :"+runString+"\n");
						this.errorTextArea.appendText("Response :"+serverMsg+"\n");
					}
					else {
						this.consoleTextArea.appendText("\nResponse : "+serverMsg);
					}
				}
			}
			socketClient.close();
			System.out.println("Client: " + " runCommand :: Finish == " +sendMsg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		try {
//			Thread.sleep(2000);
//		}
//		catch(InterruptedException   e)
//		{
//			e.printStackTrace();
//		}
	}
   
}
