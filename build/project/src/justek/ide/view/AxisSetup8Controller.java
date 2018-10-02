package justek.ide.view;


import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import justek.ide.MainApp;


public class AxisSetup8Controller {

	ObservableList<String> polarity1ComboList = FXCollections
			.observableArrayList("Low","High");
	ObservableList<String> polarity2ComboList = FXCollections
			.observableArrayList("Low","High");
	ObservableList<String> polarity3ComboList = FXCollections
			.observableArrayList("Low","High");
	ObservableList<String> polarity4ComboList = FXCollections
			.observableArrayList("Low","High");
	ObservableList<String> polarity5ComboList = FXCollections
			.observableArrayList("Low","High");
	ObservableList<String> polarity6ComboList = FXCollections
			.observableArrayList("Low","High");
	ObservableList<String> polarity7ComboList = FXCollections
			.observableArrayList("Low","High");
	ObservableList<String> polarity8ComboList = FXCollections
			.observableArrayList("Low","High");
	ObservableList<String> polarity9ComboList = FXCollections
			.observableArrayList("Low","High");
	ObservableList<String> polarity10ComboList = FXCollections
			.observableArrayList("Low","High");
	ObservableList<String> polarity11ComboList = FXCollections
			.observableArrayList("Low","High");
	ObservableList<String> polarity12ComboList = FXCollections
			.observableArrayList("Low","High");
	ObservableList<String> polarity13ComboList = FXCollections
			.observableArrayList("Low","High");

	ObservableList<String> opolarity1ComboList = FXCollections
			.observableArrayList("Low","High");
	ObservableList<String> opolarity2ComboList = FXCollections
			.observableArrayList("Low","High");
	ObservableList<String> opolarity3ComboList = FXCollections
			.observableArrayList("Low","High");
	ObservableList<String> opolarity4ComboList = FXCollections
			.observableArrayList("Low","High");
	ObservableList<String> opolarity5ComboList = FXCollections
			.observableArrayList("Low","High");
	ObservableList<String> opolarity6ComboList = FXCollections
			.observableArrayList("Low","High");
	ObservableList<String> opolarity7ComboList = FXCollections
			.observableArrayList("Low","High");
	ObservableList<String> opolarity8ComboList = FXCollections
			.observableArrayList("Low","High");
	
	ObservableList<String> function1ComboList = FXCollections
			.observableArrayList("Inhibit","Hard Stop","Ignore","General Purpose","Forward Direction","Reverse Direction","Begin","Soft Stop","Hard and Soft Stop","Abort");
	ObservableList<String> function2ComboList = FXCollections
			.observableArrayList("Inhibit","Hard Stop","Ignore","General Purpose","Forward Direction","Reverse Direction","Begin","Soft Stop","Hard and Soft Stop","Abort");
	ObservableList<String> function3ComboList = FXCollections
			.observableArrayList("Inhibit","Hard Stop","Ignore","General Purpose","Forward Direction","Reverse Direction","Begin","Soft Stop","Hard and Soft Stop","Abort");
	ObservableList<String> function4ComboList = FXCollections
			.observableArrayList("Inhibit","Hard Stop","Ignore","General Purpose","Forward Direction","Reverse Direction","Begin","Soft Stop","Hard and Soft Stop","Abort");
	ObservableList<String> function5ComboList = FXCollections
			.observableArrayList("Inhibit","Hard Stop","Ignore","General Purpose","Forward Direction","Reverse Direction","Begin","Soft Stop","Hard and Soft Stop","Abort");
	ObservableList<String> function6ComboList = FXCollections
			.observableArrayList("Inhibit","Hard Stop","Ignore","General Purpose","Forward Direction","Reverse Direction","Begin","Soft Stop","Hard and Soft Stop","Abort");
	ObservableList<String> function7ComboList = FXCollections
			.observableArrayList("Inhibit","Hard Stop","Ignore","General Purpose","Forward Direction","Reverse Direction","Begin","Soft Stop","Hard and Soft Stop","Abort");
	ObservableList<String> function8ComboList = FXCollections
			.observableArrayList("Inhibit","Hard Stop","Ignore","General Purpose","Forward Direction","Reverse Direction","Begin","Soft Stop","Hard and Soft Stop","Abort");
	ObservableList<String> function9ComboList = FXCollections
			.observableArrayList("Inhibit","Hard Stop","Ignore","General Purpose","Forward Direction","Reverse Direction","Begin","Soft Stop","Hard and Soft Stop","Abort");
	ObservableList<String> function10ComboList = FXCollections
			.observableArrayList("Inhibit","Hard Stop","Ignore","General Purpose","Forward Direction","Reverse Direction","Begin","Soft Stop","Hard and Soft Stop","Abort");
	ObservableList<String> function11ComboList = FXCollections
			.observableArrayList("Inhibit","Hard Stop","Ignore","General Purpose","Forward Direction","Reverse Direction","Begin","Soft Stop","Hard and Soft Stop","Abort");
	ObservableList<String> function12ComboList = FXCollections
			.observableArrayList("Inhibit","Hard Stop","Ignore","General Purpose","Forward Direction","Reverse Direction","Begin","Soft Stop","Hard and Soft Stop","Abort");
	ObservableList<String> function13ComboList = FXCollections
			.observableArrayList("Inhibit","Hard Stop","Ignore","General Purpose","Forward Direction","Reverse Direction","Begin","Soft Stop","Hard and Soft Stop","Abort");

	ObservableList<String> ofunction1ComboList = FXCollections
			.observableArrayList("General Purpose","AOK","Brake","Motor Status","Motor Fault","Target Reached");
	ObservableList<String> ofunction2ComboList = FXCollections
			.observableArrayList("General Purpose","AOK","Brake","Motor Status","Motor Fault","Target Reached");
	ObservableList<String> ofunction3ComboList = FXCollections
			.observableArrayList("General Purpose","AOK","Brake","Motor Status","Motor Fault","Target Reached");
	ObservableList<String> ofunction4ComboList = FXCollections
			.observableArrayList("General Purpose","AOK","Brake","Motor Status","Motor Fault","Target Reached");
	ObservableList<String> ofunction5ComboList = FXCollections
			.observableArrayList("General Purpose","AOK","Brake","Motor Status","Motor Fault","Target Reached");
	ObservableList<String> ofunction6ComboList = FXCollections
			.observableArrayList("General Purpose","AOK","Brake","Motor Status","Motor Fault","Target Reached");
	ObservableList<String> ofunction7ComboList = FXCollections
			.observableArrayList("General Purpose","AOK","Brake","Motor Status","Motor Fault","Target Reached");
	ObservableList<String> ofunction8ComboList = FXCollections
			.observableArrayList("General Purpose","AOK","Brake","Motor Status","Motor Fault","Target Reached");


	@FXML
	private ComboBox polarity1Combo;
	@FXML
	private ComboBox polarity2Combo;
	@FXML
	private ComboBox polarity3Combo;
	@FXML
	private ComboBox polarity4Combo;
	@FXML
	private ComboBox polarity5Combo;
	@FXML
	private ComboBox polarity6Combo;
	@FXML
	private ComboBox polarity7Combo;
	@FXML
	private ComboBox polarity8Combo;
	@FXML
	private ComboBox polarity9Combo;
	@FXML
	private ComboBox polarity10Combo;
	@FXML
	private ComboBox polarity11Combo;
	@FXML
	private ComboBox polarity12Combo;
	@FXML
	private ComboBox polarity13Combo;
	@FXML
	private ComboBox function1Combo;
	@FXML
	private ComboBox function2Combo;
	@FXML
	private ComboBox function3Combo;
	@FXML
	private ComboBox function4Combo;
	@FXML
	private ComboBox function5Combo;
	@FXML
	private ComboBox function6Combo;
	@FXML
	private ComboBox function7Combo;
	@FXML
	private ComboBox function8Combo;
	@FXML
	private ComboBox function9Combo;
	@FXML
	private ComboBox function10Combo;
	@FXML
	private ComboBox function11Combo;
	@FXML
	private ComboBox function12Combo;
	@FXML
	private ComboBox function13Combo;
	@FXML
	private ComboBox ofunction1Combo;
	@FXML
	private ComboBox ofunction2Combo;
	@FXML
	private ComboBox ofunction3Combo;
	@FXML
	private ComboBox ofunction4Combo;
	@FXML
	private ComboBox ofunction5Combo;
	@FXML
	private ComboBox ofunction6Combo;
	@FXML
	private ComboBox ofunction7Combo;
	@FXML
	private ComboBox ofunction8Combo;
	@FXML
	private ComboBox opolarity1Combo;
	@FXML
	private ComboBox opolarity2Combo;
	@FXML
	private ComboBox opolarity3Combo;
	@FXML
	private ComboBox opolarity4Combo;
	@FXML
	private ComboBox opolarity5Combo;
	@FXML
	private ComboBox opolarity6Combo;
	@FXML
	private ComboBox opolarity7Combo;
	@FXML
	private ComboBox opolarity8Combo;

	
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
    	polarity1Combo.setValue("Low");
    	polarity1Combo.setItems(polarity1ComboList);
    	polarity2Combo.setValue("Low");
    	polarity2Combo.setItems(polarity2ComboList);
    	polarity3Combo.setValue("Low");
    	polarity3Combo.setItems(polarity3ComboList);
    	polarity4Combo.setValue("Low");
    	polarity4Combo.setItems(polarity4ComboList);
    	polarity5Combo.setValue("Low");
    	polarity5Combo.setItems(polarity5ComboList);
    	polarity6Combo.setValue("Low");
    	polarity6Combo.setItems(polarity6ComboList);
    	polarity7Combo.setValue("Low");
    	polarity7Combo.setItems(polarity7ComboList);
    	polarity8Combo.setValue("Low");
    	polarity8Combo.setItems(polarity8ComboList);
    	polarity9Combo.setValue("Low");
    	polarity9Combo.setItems(polarity9ComboList);
    	polarity10Combo.setValue("Low");
    	polarity10Combo.setItems(polarity10ComboList);
    	polarity11Combo.setValue("Low");
    	polarity11Combo.setItems(polarity11ComboList);
    	polarity12Combo.setValue("Low");
    	polarity12Combo.setItems(polarity12ComboList);
    	polarity13Combo.setValue("Low");
    	polarity13Combo.setItems(polarity13ComboList);
    	opolarity1Combo.setValue("Low");
    	opolarity1Combo.setItems(opolarity1ComboList);
    	opolarity2Combo.setValue("Low");
    	opolarity2Combo.setItems(opolarity2ComboList);
    	opolarity3Combo.setValue("Low");
    	opolarity3Combo.setItems(opolarity3ComboList);
    	opolarity4Combo.setValue("Low");
    	opolarity4Combo.setItems(opolarity4ComboList);
    	opolarity5Combo.setValue("Low");
    	opolarity5Combo.setItems(opolarity5ComboList);
    	opolarity6Combo.setValue("Low");
    	opolarity6Combo.setItems(opolarity6ComboList);
    	opolarity7Combo.setValue("Low");
    	opolarity7Combo.setItems(opolarity7ComboList);
    	opolarity8Combo.setValue("Low");
    	opolarity8Combo.setItems(opolarity8ComboList);
    	function1Combo.setValue("Inhibit");
    	function1Combo.setItems(function1ComboList);
    	function2Combo.setValue("Inhibit");
    	function2Combo.setItems(function2ComboList);
    	function3Combo.setValue("Inhibit");
    	function3Combo.setItems(function3ComboList);
    	function4Combo.setValue("Inhibit");
    	function4Combo.setItems(function4ComboList);
    	function5Combo.setValue("Inhibit");
    	function5Combo.setItems(function5ComboList);
    	function6Combo.setValue("Inhibit");
    	function6Combo.setItems(function6ComboList);
    	function7Combo.setValue("Inhibit");
    	function7Combo.setItems(function7ComboList);
    	function8Combo.setValue("Inhibit");
    	function8Combo.setItems(function8ComboList);
    	function9Combo.setValue("Inhibit");
    	function9Combo.setItems(function9ComboList);
    	function10Combo.setValue("Inhibit");
    	function10Combo.setItems(function10ComboList);
    	function11Combo.setValue("Inhibit");
    	function11Combo.setItems(function11ComboList);
    	function12Combo.setValue("Inhibit");
    	function12Combo.setItems(function12ComboList);
    	function13Combo.setValue("Inhibit");
    	function13Combo.setItems(function13ComboList);
    	ofunction1Combo.setValue("General Purpose");
    	ofunction1Combo.setItems(ofunction1ComboList);
    	ofunction2Combo.setValue("General Purpose");
    	ofunction2Combo.setItems(ofunction2ComboList);
    	ofunction3Combo.setValue("General Purpose");
    	ofunction3Combo.setItems(ofunction3ComboList);
    	ofunction4Combo.setValue("General Purpose");
    	ofunction4Combo.setItems(ofunction4ComboList);
    	ofunction5Combo.setValue("General Purpose");
    	ofunction5Combo.setItems(ofunction5ComboList);
    	ofunction6Combo.setValue("General Purpose");
    	ofunction6Combo.setItems(ofunction6ComboList);
    	ofunction7Combo.setValue("General Purpose");
    	ofunction7Combo.setItems(ofunction7ComboList);
    	ofunction8Combo.setValue("General Purpose");
    	ofunction8Combo.setItems(ofunction8ComboList);
  

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
