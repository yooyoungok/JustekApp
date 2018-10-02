package justek.ide.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class CommonButtonController implements Initializable {

	static final String Tag = "CommonButtonController";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@FXML
	void onClickRevert(ActionEvent event) {
		System.out.println(Tag+" == onClickRevert ");
	}		

	@FXML
	void onClickApply(ActionEvent event) {
		System.out.println(Tag+" == onClickApply ");
	}

	@FXML
	void onClickError(ActionEvent event) {
		System.out.println(Tag+" == onClickErrors ");
	}

}
