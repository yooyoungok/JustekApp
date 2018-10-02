package justek.ide.model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import justek.ide.model.manager.NetworkServerManager;

public class ButtonCell extends TableCell<PLCInfo, String> { 

	private Button cellButton;
	private boolean isRun = false;

	Image icon = new Image (
			getClass().getResourceAsStream("/img/close_folder.png"));

	public ButtonCell(ListView<String> view){
		cellButton = new Button("RUN");
		cellButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent t) {
				if(!isRun) {
					isRun = true;
					PLCInfo info = getTableView().getItems().get(getIndex());
					NetworkServerManager.getInstance().runPlcFile(info.getName());
					setText("실행중");
					int dot = info.getName().indexOf('.');
					info.isRun = true;
					String runString2 = info.getName().substring(0, dot);
					view.getItems().add("enable plc "+runString2);
					cellButton.setStyle("-fx-background-color: #ee1010; -fx-text-fill: #FFFFFF;");
					cellButton.setText("STOP");
				}
				else {
					isRun = false;
					PLCInfo info = getTableView().getItems().get(getIndex());
					NetworkServerManager.stopPlcFile(info.getName());
					setText("대기중");
					info.isRun = false;
					int dot = info.getName().indexOf('.');
					String runString2 = info.getName().substring(0, dot);
					view.getItems().add("disable plc "+runString2);
					cellButton.setStyle("-fx-background-color: #058D17; -fx-text-fill: #FFFFFF;");
					cellButton.setText("RUN");
				}
			}
		});
	}

	//Display button if the row is not empty
	@Override 
	public void updateItem(String record, boolean empty) {
		super.updateItem(record, empty);
		if(!empty){
			PLCInfo info = getTableView().getItems().get(getIndex());
			if(info.isRun) {
				this.isRun = true;
				cellButton.setStyle("-fx-background-color: #ee1010; -fx-text-fill: #FFFFFF;");
				cellButton.setText("STOP");
				setText("실행중");
			}
			else {
				this.isRun = false;
				cellButton.setStyle("-fx-background-color: #058D17; -fx-text-fill: #FFFFFF;");
				setText("대기중");
				cellButton.setText("RUN");
			}
			
			this.setAlignment(Pos.CENTER);
			setGraphic(cellButton);
		} else {
			setGraphic(null);
		}
	}

	//    private String getString() { 
	//    	return getItem() == null ? "" : getItem(); 
	//    } 
	//         

} 

