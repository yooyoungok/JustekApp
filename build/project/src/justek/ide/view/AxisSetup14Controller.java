package justek.ide.view;


import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import justek.ide.MainApp;


public class AxisSetup14Controller {

	ObservableList<String> objectiveComboList = FXCollections
			.observableArrayList("PI Controller","PI Controller + LPF","Refine Tuning","Advanced Controller");
	ObservableList<String> schedulingCombo1List = FXCollections
			.observableArrayList("Off","Manual","By Speed","By Position","Best Settling","By object 0x2E00 lowbits","By object 0x2E00 high bits");
	ObservableList<String> schedulingCombo2List1 = FXCollections
			.observableArrayList("GS Index 1","GS Index 2","GS Index 3","GS Index 4","GS Index 5","GS Index 6");
	ObservableList<String> schedulingCombo2List2 = FXCollections
			.observableArrayList("Automatic","GS Index 1","GS Index 2","GS Index 3","GS Index 4","GS Index 5","GS Index 6");
	ObservableList<String> schedulingCombo2List3 = FXCollections
			.observableArrayList("Automatic","GS Index 1","GS Index 2","GS Index 3","GS Index 4","GS Index 5","GS Index 6");
	ObservableList<String> schedulingCombo2List4 = FXCollections
			.observableArrayList("Automatic","Stand Still - 61","Settling - 62","In Motion - 63");
	ObservableList<String> schedulingCombo2List5 = FXCollections
			.observableArrayList("Automatic","GS Index 1","GS Index 2","GS Index 3","GS Index 4","GS Index 5","GS Index 6");
	ObservableList<String> schedulingCombo2List6 = FXCollections
			.observableArrayList("Automatic","GS Index 1","GS Index 2","GS Index 3","GS Index 4","GS Index 5","GS Index 6");
	ObservableList<String> velFilter1ComboList = FXCollections
			.observableArrayList("Disable","Low-Pass","Lead-Lag","Double Lead-Lag","Notch","Anti-Notch","General Biquad");
	ObservableList<String> velFilter2ComboList = FXCollections
			.observableArrayList("Disable","Low-Pass","Lead-Lag","Double Lead-Lag","Notch","Anti-Notch","General Biquad");
	ObservableList<String> velFilter3ComboList = FXCollections
			.observableArrayList("Disable","Low-Pass","Lead-Lag","Double Lead-Lag","Notch","Anti-Notch","General Biquad");
	ObservableList<String> velFilter4ComboList = FXCollections
			.observableArrayList("Disable","Low-Pass","Lead-Lag","Double Lead-Lag","Notch","Anti-Notch","General Biquad");
	ObservableList<String> velFilter5ComboList = FXCollections
			.observableArrayList("Disable","Low-Pass","Lead-Lag","Double Lead-Lag","Notch","Anti-Notch","General Biquad");
	ObservableList<String> velFilter6ComboList = FXCollections
			.observableArrayList("Disable","Low-Pass","Lead-Lag","Double Lead-Lag","Notch","Anti-Notch","General Biquad");
	ObservableList<String> positionFilter1ComboList = FXCollections
			.observableArrayList("Disable","Low-Pass","Lead-Lag","Double Lead-Lag","Notch","Anti-Notch","General Biquad");
	ObservableList<String> positionFilter2ComboList = FXCollections
			.observableArrayList("Disable","Low-Pass","Lead-Lag","Double Lead-Lag","Notch","Anti-Notch","General Biquad");
	ObservableList<String> schedulingFilter1ComboList = FXCollections
			.observableArrayList("Disable","Low-Pass","Lead-Lag","Double Lead-Lag","Notch","Anti-Notch","General Biquad");
	ObservableList<String> schedulingFilter2ComboList = FXCollections
			.observableArrayList("Disable","Low-Pass","Lead-Lag","Double Lead-Lag","Notch","Anti-Notch","General Biquad");
	ObservableList<String> schedulingFilter3ComboList = FXCollections
			.observableArrayList("Disable","Low-Pass","Lead-Lag","Double Lead-Lag","Notch","Anti-Notch","General Biquad");

	@FXML
	private ComboBox objectiveCombo;
	@FXML
	private ComboBox schedulingCombo1;
	@FXML
	private ComboBox schedulingCombo2;
	@FXML
	private ComboBox velFilter1Combo;
	@FXML
	private ComboBox velFilter2Combo;
	@FXML
	private ComboBox velFilter3Combo;
	@FXML
	private ComboBox velFilter4Combo;
	@FXML
	private ComboBox velFilter5Combo;
	@FXML
	private ComboBox velFilter6Combo;
	@FXML
	private ComboBox positionFilter1Combo;
	@FXML
	private ComboBox positionFilter2Combo;
	@FXML
	private ComboBox schedulingFilter1Combo;
	@FXML
	private ComboBox schedulingFilter2Combo;
	@FXML
	private ComboBox schedulingFilter3Combo;

	
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
    	objectiveCombo.setValue("PI Controller");
    	objectiveCombo.setItems(objectiveComboList);
    	schedulingCombo1.setValue("Manual");
    	schedulingCombo1.setItems(schedulingCombo1List);
    	velFilter1Combo.setValue("Disable");
    	velFilter1Combo.setItems(velFilter1ComboList);
    	velFilter2Combo.setValue("Disable");
    	velFilter2Combo.setItems(velFilter2ComboList);
    	velFilter3Combo.setValue("Disable");
    	velFilter3Combo.setItems(velFilter3ComboList);
    	velFilter4Combo.setValue("Disable");
    	velFilter4Combo.setItems(velFilter4ComboList);
    	velFilter5Combo.setValue("Disable");
    	velFilter5Combo.setItems(velFilter5ComboList);
    	velFilter6Combo.setValue("Disable");
    	velFilter6Combo.setItems(velFilter6ComboList);
    	positionFilter1Combo.setValue("Disable");
    	positionFilter1Combo.setItems(positionFilter1ComboList);
    	positionFilter2Combo.setValue("Disable");
    	positionFilter2Combo.setItems(positionFilter2ComboList);
    	schedulingFilter1Combo.setValue("Disable");
    	schedulingFilter1Combo.setItems(schedulingFilter1ComboList);
    	schedulingFilter2Combo.setValue("Disable");
    	schedulingFilter2Combo.setItems(schedulingFilter2ComboList);
    	schedulingFilter3Combo.setValue("Disable");
    	schedulingFilter3Combo.setItems(schedulingFilter3ComboList);


     }
   
    @FXML
    private void schedulingCombo1Choice() {
    	if(schedulingCombo1.getValue().equals("Off")) {
    		schedulingCombo2.setValue("");
    		schedulingCombo2.setItems(null);
    	} else if(schedulingCombo1.getValue().equals("Manual")) {
    		schedulingCombo2.setValue("GS Index 1");
    		schedulingCombo2.setItems(schedulingCombo2List1);
    	} else if(schedulingCombo1.getValue().equals("By Speed")) {
    		schedulingCombo2.setValue("Automatic");
    		schedulingCombo2.setItems(schedulingCombo2List1);
    	} else if(schedulingCombo1.getValue().equals("By Position")) {
    		schedulingCombo2.setValue("Automatic");
    		schedulingCombo2.setItems(schedulingCombo2List1);
    	} else if(schedulingCombo1.getValue().equals("Best Settling")) {
    		schedulingCombo2.setValue("Automatic");
    		schedulingCombo2.setItems(schedulingCombo2List1);
    	} else if(schedulingCombo1.getValue().equals("By object 0x2E00 lowbits")) {
    		schedulingCombo2.setValue("Automatic");
    		schedulingCombo2.setItems(schedulingCombo2List1);
    	} else if(schedulingCombo1.getValue().equals("By object 0x2E00 high bits")) {
    		schedulingCombo2.setValue("Automatic");
    		schedulingCombo2.setItems(schedulingCombo2List1);
    	} else {
    		schedulingCombo2.setValue("");
    		schedulingCombo2.setItems(null);
    	}
    }

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
