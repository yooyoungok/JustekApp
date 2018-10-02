package justek.ide.model;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import justek.ide.utils.StringUtil;

public class EditingCell extends TableCell<SDOInfo, String> { 

	public boolean isBinary = false;
	public String preValue;
	private TextField textField; 


	public EditingCell() { 
		
	} 
	
	@Override 
	public void startEdit() { 
		SDOInfo info = getTableView().getItems().get(getIndex());
		preValue = info.getValue();

		if (!isEmpty()) { 
			super.startEdit(); 
			createTextField(); 
			setText(null); 
			setGraphic(textField); 
			textField.selectAll(); 

		} 
	} 


	@Override
	public void commitEdit(String newValue) {
		// TODO Auto-generated method stub
		super.commitEdit(newValue);
		if(!newValue.equals(this.preValue)) {
			setTextFill(Color.RED); 
			setStyle("-fx-background-color: yellow");
		}
	}


	@Override 
	public void cancelEdit() { 
		super.cancelEdit(); 
		setText(StringUtil.getNumnberFommat((String) getItem())); 
		setGraphic(null); 
	} 

	@Override 
	public void updateItem(String item, boolean empty) { 
		super.updateItem(item, empty); 

		this.setOnMouseReleased((MouseEvent e) -> {
			if (e.getButton() == MouseButton.SECONDARY) {
				if(this.getTableColumn().getText().contains("Value")) {
					System.out.println("Click Right Mouse == "+this.getTableColumn().getText());
					this.setContextMenu(this.showPopupMenu());
				}
			}
		});

		if (empty) { 
			setText(item); 
			setGraphic(null); 
		} else { 
			if (isEditing()) { 
				if (textField != null) { 
					textField.setText(StringUtil.getNumnberFommat(getString())); 
				} 
				setText(null); 
				setGraphic(textField); 
			} else { 
				
				//초기화시는 셀의 색상을 복원한다...
				setTextFill(Color.BLACK); 
				setStyle("-fx-background-color: transparent");
				
				setText(StringUtil.getNumnberFommat(getString())); 

				if(this.getTableColumn().getText().contains("Value")) {
					setAlignment(Pos.CENTER_RIGHT);
				}
				else {
					setAlignment(Pos.CENTER_LEFT);
				}
				setGraphic(null); 
			} 
		} 
	} 


	private void createTextField() { 
		textField = new TextField(getString()); 
		textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2); 
		textField.setOnAction((e) -> commitEdit(textField.getText())); 
		textField.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {

			if (!newValue) { 
				System.out.println("Commiting " + textField.getText()); 
				commitEdit(textField.getText()); 
			} 
		}); 

		textField.setAlignment(Pos.CENTER_RIGHT);
	} 

	private String getString() { 
		return getItem() == null ? "" : getItem(); 
	} 

	private ContextMenu showPopupMenu() {
		ContextMenu contextMenu = new ContextMenu();
		MenuItem item1 = new MenuItem("Binary Value");
		item1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if(!isBinary) {
					isBinary = true;
					String result = StringUtil.getToBiniary(getText().replaceAll(",", ""));
					setText(result);
					System.out.println("Binary Value == " + result);
				}
			}
		});

		MenuItem item2 = new MenuItem("Hexa Value");
		item2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String result="";
				if(isBinary) {
					result = StringUtil.binaryToHex(getText());
					setText(result);
				}
				else {
					result = StringUtil.getDecimalToHexValue(getText().replaceAll(",", ""));
					setText(result);
				}

				System.out.println("Hexa Value == " + result);
				isBinary = false;
			}
		});

		MenuItem item3 = new MenuItem("Decimal Value");
		item3.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				String result = "";
				if(isBinary) {
					result = StringUtil.binaryToDec(getText());
					setText(StringUtil.getNumnberFommat(result));
				}
				else {
					result = StringUtil.getHexToDecimalValue(getText().replaceAll(",", ""));
					setText(StringUtil.getNumnberFommat(result));
				}

				System.out.println("Decimal Value  == " +result);
				isBinary = false;
			}
		});

		contextMenu.getItems().addAll(item1, item2,item3);

		return contextMenu;
	}
} 

