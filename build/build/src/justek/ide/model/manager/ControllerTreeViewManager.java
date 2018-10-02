package justek.ide.model.manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import justek.ide.MainApp;

public class ControllerTreeViewManager {

	private ListView<String> mListView;
	private MainApp mainApp;
	private static ControllerTreeViewManager instance;
	

	public static ControllerTreeViewManager getInstance () {
		if(instance==null) {
			instance = new ControllerTreeViewManager();
		}
		return instance;
	}
	
	public void setListView(ListView<String> listView) {
		this.mListView = listView;
	}
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public ObservableList<String> getNodeInfo() {

		ObservableList<String> nodes = FXCollections.observableArrayList();
		nodes.add("User Units");
		nodes.add("Limits Protections");
		nodes.add("Motion Limits");
		nodes.add("Settling Window");
		nodes.add("Fast Reference");
		nodes.add("Error Policies");
//		nodes.add("User Units");
		nodes.add("Protections");
		nodes.add("Settling Window");
		nodes.add("Motion Limits");
		nodes.add("Global");
		return nodes;
	}
	
	  public void mouseClick(MouseEvent mouseEvent) {
	    	MouseButton button = mouseEvent.getButton();
	    	String item = this.mListView.getSelectionModel().getSelectedItem();
	    	
	    	if(mouseEvent.getClickCount()==2) {
	    		if(item==null) {
	    			return;
	    		}
	    	}
	    	else if(mouseEvent.getClickCount()==1) {
	    		if(this.mListView.getSelectionModel().getSelectedIndex()==0) {
	    			mainApp.ControllerConfigurator1();
	    		}
	    		else if(this.mListView.getSelectionModel().getSelectedIndex()==1) {
	    			mainApp.ControllerConfigurator2();
	    		}
	    		else if(this.mListView.getSelectionModel().getSelectedIndex()==2) {
	    			mainApp.ControllerConfigurator3();
	    		}
	    		else if(this.mListView.getSelectionModel().getSelectedIndex()==3) {
	    			mainApp.ControllerConfigurator4();
	    		}
	    		else if(this.mListView.getSelectionModel().getSelectedIndex()==4) {
	    			mainApp.ControllerConfigurator5();
	    		}
	    		else if(this.mListView.getSelectionModel().getSelectedIndex()==5) {
	    			mainApp.ControllerConfigurator6();
	    		}
//	    		else if(this.mListView.getSelectionModel().getSelectedIndex()==6) {
//	    			mainApp.ControllerConfiguratorG1();
//	    		}
	    		else if(this.mListView.getSelectionModel().getSelectedIndex()==6) {
	    			mainApp.ControllerConfiguratorG2();
	    		}
	    		else if(this.mListView.getSelectionModel().getSelectedIndex()==7) {
	    			mainApp.ControllerConfiguratorG3();
	    		}
	    		else if(this.mListView.getSelectionModel().getSelectedIndex()==8) {
	    			mainApp.ControllerConfiguratorG4();
	    		}
	    		else if(this.mListView.getSelectionModel().getSelectedIndex()==9) {
	    			mainApp.ControllerConfigurator();
	    		}
	    	}
	  }
}
