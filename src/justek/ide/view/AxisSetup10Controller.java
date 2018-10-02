package justek.ide.view;


import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import justek.ide.MainApp;


public class AxisSetup10Controller {

	ObservableList<String> axis0Combo1List = FXCollections
			.observableArrayList("Single Axis","Stepper Axis(No Feedback)","Stepper Axis(with Feedback)","Gantry","Planar","Synchronized Current","Current Amplifier");

	ObservableList<String> s1List = FXCollections
			.observableArrayList("Master","Slave");
	
	ObservableList<String> s2List = FXCollections
			.observableArrayList("X Master","X Slave","Y Master","Y Slave");

/*	@FXML
	private ComboBox axis0Combo1;
	@FXML
	private ComboBox axis0Combo2;	
	*/
	
	   // Reference to the main application
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
//    	axis0Combo1.setValue("Single Axis");
 //   	axis0Combo1.setItems(axis0Combo1List);

     }
/*    
    @FXML
    private void combo1Choice() {
    	if(axis0Combo1.getValue().equals("Gantry")) {
    		axis0Combo2.setValue("Master");
    		axis0Combo2.setItems(s1List);
    	} 
    	else if(axis0Combo1.getValue().equals("Planar")) {
        	axis0Combo2.setValue("X Master");
        	axis0Combo2.setItems(s2List);
    	}
    	else if(axis0Combo1.getValue().equals("Synchronized Current")) {
    		axis0Combo2.setValue("Master");
    		axis0Combo2.setItems(s1List);
    	} else {
       		axis0Combo2.setValue("");
    		axis0Combo2.setItems(null);
  		
    	}
    	
    }
*/
    @FXML
    private void handleAxisSetup() {
    	 mainApp.AxisSetup();
    }

    @FXML
    private void handleAxisSetup1() {
   	    mainApp.AxisSetup1();	
    }

    @FXML
    private void handleAxisSetup2() {
   	    mainApp.AxisSetup2();	
    }


    @FXML
    private void handleAxisSetup3() {
   	    mainApp.AxisSetup3();	
    }


    @FXML
    private void handleAxisSetup4() {
   	    mainApp.AxisSetup4();	
    }



    @FXML
    private void handleAxisSetup5() {
   	    mainApp.AxisSetup5();	
    }



    @FXML
    private void handleAxisSetup6() {
   	    mainApp.AxisSetup6();	
    }


    @FXML
    private void handleAxisSetup7() {
   	    mainApp.AxisSetup7();	
    }


    @FXML
    private void handleAxisSetup8() {
   	    mainApp.AxisSetup8();	
    }


    @FXML
    private void handleAxisSetup9() {
   	    mainApp.AxisSetup9();	
    }



    @FXML
    private void handleAxisSetup10() {
   	    mainApp.AxisSetup10();	
    }


    @FXML
    private void handleAxisSetup11() {
   	    mainApp.AxisSetup11();	
    }


    @FXML
    private void handleAxisSetup12() {
   	    mainApp.AxisSetup12();	
    }



    @FXML
    private void handleAxisSetup13() {
   	    mainApp.AxisSetup13();	
    }



    @FXML
    private void handleAxisSetup14() {
   	    mainApp.AxisSetup14();	
    }



    @FXML
    private void handleAxisSetup15() {
   	    mainApp.AxisSetup15();	
    }



    @FXML
    private void handleAxisSetup16() {
   	    mainApp.AxisSetup16();	
    }



    @FXML
    private void handleAxisSetup17() {
   	    mainApp.AxisSetup17();	
    }
 
}
