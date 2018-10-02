package justek.ide.model.manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import justek.ide.MainApp;

public class TreeViewManager {

	private TreeView<String> mtreeview;
	private MainApp mainApp;
	private static TreeViewManager instance;
	
	private boolean isSingle = true;;

	public static TreeViewManager getInstance () {
		if(instance==null) {
			instance = new TreeViewManager();
		}
		return instance;
	}
	
	public void setTreeView(TreeView<String> treeview) {
		this.mtreeview = treeview;
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public ObservableList<TreeItem<String>> getNodeInfo() {

		ObservableList<TreeItem<String>> nodes = FXCollections.observableArrayList();
		
			this.isSingle = true;
			
			TreeItem<String> nodeA = new TreeItem<>("Motor and Feedback");
			nodes.add(nodeA);
			TreeItem<String> nodeA1 = new TreeItem<>("Motor Settings");
//			TreeItem<String> nodeA2 = new TreeItem<>("Feedback Settings");
			TreeItem<String> nodeA3 = new TreeItem<>("User Units");
			nodeA.getChildren().add(nodeA1);
//			nodeA.getChildren().add(nodeA2);
			nodeA.getChildren().add(nodeA3);
			
			TreeItem<String> nodeB = new TreeItem<>("Limits and Protections");
			nodes.add(nodeB);
			TreeItem<String> nodeB1 = new TreeItem<>("Current Limits");
			TreeItem<String> nodeB2 = new TreeItem<>("Motion Limits");
			TreeItem<String> nodeB3 = new TreeItem<>("Protections");
			nodeB.getChildren().add(nodeB1);
			nodeB.getChildren().add(nodeB2);
			nodeB.getChildren().add(nodeB3);
			
			TreeItem<String> nodeC = new TreeItem<>("Application Settings");
			nodes.add(nodeC);
			TreeItem<String> nodeC1 = new TreeItem<>("Settling Window");
//			TreeItem<String> nodeC2 = new TreeItem<>("Inputs and Outputs");
			nodeC.getChildren().add(nodeC1);
//			nodeC.getChildren().add(nodeC2);
			
			TreeItem<String> nodeD = new TreeItem<>("Current");
			nodes.add(nodeD);
//			TreeItem<String> nodeD1 = new TreeItem<>("Identification");
//			TreeItem<String> nodeD2 = new TreeItem<>("Design");
			TreeItem<String> nodeD3 = new TreeItem<>("Verification-Time");
			TreeItem<String> nodeD4 = new TreeItem<>("Commutation");
//			nodeD.getChildren().add(nodeD1);
//			nodeD.getChildren().add(nodeD2);
			nodeD.getChildren().add(nodeD3);
			nodeD.getChildren().add(nodeD4);
			
			TreeItem<String> nodeE = new TreeItem<>("Velocity and Position");
			nodes.add(nodeE);
//			TreeItem<String> nodeE1 = new TreeItem<>("Identification");l
//			TreeItem<String> nodeE2 = new TreeItem<>("Design");
//			TreeItem<String> nodeE3 = new TreeItem<>("Scheduling");
			TreeItem<String> nodeE2 = new TreeItem<>("Verification-Time");
//			TreeItem<String> nodeE3 = new TreeItem<>("Summary");
//			nodeE.getChildren().add(nodeE1);
			nodeE.getChildren().add(nodeE2);
//			nodeE.getChildren().add(nodeE3);
//			nodeE.getChildren().add(nodeE4);
//			nodeE.getChildren().add(nodeE5);
			
		return nodes;
	}
	
	public ObservableList<TreeItem<String>> getGrantryNodeInfo() {

		this.isSingle = false;
		
		ObservableList<TreeItem<String>> nodes = FXCollections.observableArrayList();
		
			
			TreeItem<String> nodeA = new TreeItem<>("Motor and Feedback");
			nodes.add(nodeA);
			TreeItem<String> nodeA1 = new TreeItem<>("Motor Settings");
			TreeItem<String> nodeA2 = new TreeItem<>("Feedback Settings");
			TreeItem<String> nodeA3 = new TreeItem<>("User Units");
			nodeA.getChildren().add(nodeA1);
			nodeA.getChildren().add(nodeA2);
			nodeA.getChildren().add(nodeA3);
			
			TreeItem<String> nodeB = new TreeItem<>("Limits and Protections");
			nodes.add(nodeB);
			TreeItem<String> nodeB1 = new TreeItem<>("Current Limits");
			TreeItem<String> nodeB2 = new TreeItem<>("Protections");
			nodeB.getChildren().add(nodeB1);
			nodeB.getChildren().add(nodeB2);
			
			TreeItem<String> nodeC = new TreeItem<>("Application Settings");
			nodes.add(nodeC);
			TreeItem<String> nodeC1 = new TreeItem<>("Inputs and Outputs");
			nodeC.getChildren().add(nodeC1);
			
			TreeItem<String> nodeD = new TreeItem<>("Current");
			nodes.add(nodeD);
			TreeItem<String> nodeD1 = new TreeItem<>("Identification");
			TreeItem<String> nodeD2 = new TreeItem<>("Design");
			TreeItem<String> nodeD3 = new TreeItem<>("Verification-Time");
			nodeD.getChildren().add(nodeD1);
			nodeD.getChildren().add(nodeD2);
			nodeD.getChildren().add(nodeD3);
			
			TreeItem<String> nodeE = new TreeItem<>("Commutation");
			nodes.add(nodeE);
			
			TreeItem<String> nodeF = new TreeItem<>("Summary");
//			nodes.add(nodeF);
			
		return nodes;
	}
	
	  public void mouseClick(MouseEvent mouseEvent) {
		  System.out.println("TreeManager :: mouseClick"); 
	    	MouseButton button = mouseEvent.getButton();
	    	TreeItem<String> item = mtreeview.getSelectionModel().getSelectedItem();   	

	    	if(mouseEvent.getClickCount()==1) {
	    		
	    		 System.out.println("TreeManager :: getClickCount"); 
	    		if(item==null) {
	    			 System.out.println("TreeManager :: null"); 
	    			return;
	    		}
	    		else if(item.getValue().equals("Axis Configrations")) {
	    			mainApp.AxisSetup();
	    		}
	    		else if(item.getParent().getValue().equals("Axis Configrations")) {
	    			return;
	    		}
	    		else if(item.getParent().getValue().equals("Motor and Feedback")) {
	    			if(item.getValue().equals("Motor Settings")) {
	    				mainApp.AxisSetup1();
	    			} 
	    			else if(item.getValue().equals("Feedback Settings")) {
	    				mainApp.AxisSetup2();
	    			} 
	       			else if(item.getValue().equals("User Units")) {
	    				mainApp.AxisSetup3();
	    			} 
	    		}
	    		else if(item.getParent().getValue().equals("Limits and Protections")) {
	    			if(item.getValue().equals("Current Limits")) {
	    				mainApp.AxisSetup4();
	    			} 
	    			else if(item.getValue().equals("Motion Limits"))  {
	    				mainApp.AxisSetup5();
	    			}
	    			else if(item.getValue().equals("Protections"))  {
	    				mainApp.AxisSetup6();
	    			}
	    		}
	    		else if(item.getParent().getValue().equals("Velocity and Position")) {
	    			if(item.getValue().equals("Verification-Time")) {
	    				mainApp.AxisSetup16();
	    			} 
	    			else if(item.getValue().equals("Identification"))  {
	    				mainApp.AxisSetup13();
	    			}
	    			else if(item.getValue().equals("Design"))  {
	    				mainApp.AxisSetup14();
	    			}
	    			else if(item.getValue().equals("Scheduling"))  {
	    				mainApp.AxisSetup15();
	    			}	
	    			else if(item.getValue().equals("Summary"))  {
	    				mainApp.AxisSetup17();
	    			}	
	    		}
	    		else if(item.getParent().getValue().equals("Current")) {
	    			if(item.getValue().equals("Verification-Time")) {
	    				mainApp.AxisSetup11();
	    			} 
	    			else if(item.getValue().equals("Identification"))  {
	    				mainApp.AxisSetup9();
	    			}
	    			else if(item.getValue().equals("Design"))  {
	    				mainApp.AxisSetup10();
	    			}
	    			else if(item.getValue().equals("Commutation"))  {
	    				mainApp.AxisSetup12();
	    			}	
	    		}
	    		else if(item.getParent().getValue().equals("Application Settings")) {
	    			if(item.getValue().equals("Settling Window")) {
	    				mainApp.AxisSetup7();
	    			} 
	    			else if(item.getValue().equals("Inputs and Outputs"))  {
	    				mainApp.AxisSetup8();
	    			}
	
	    		}
	    	}
	  }

}
