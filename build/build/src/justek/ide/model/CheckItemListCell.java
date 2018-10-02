package justek.ide.model;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.cell.CheckBoxListCell;

public class CheckItemListCell extends CheckBoxListCell<CheckItem> {
	private final CheckBox btn;

	public CheckItemListCell() {
		setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		btn = new CheckBox();
//		btn.setOnAction(new EventHandler<ActionEvent>(){
//
//			@Override
//			public void handle(ActionEvent t) {
//				CheckItem  item = getListView().getSelectionModel().getSelectedItem();
//				item.setChecked(btn.isSelected());
//				System.out.println("CheckItemListCell ::handle");
//			}
//		});
		
		btn.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                if (t) {
                	System.out.println("CheckItemListCell ::handle t1 "+t1.toString());
                } else {
                	System.out.println("CheckItemListCell ::handle t "+t.toString());
                }
            }
        });
	}

	@Override
	public void updateItem(CheckItem item, boolean empty) {
		super.updateItem(item, empty);

		if (item == null || empty) {
			setGraphic(null);
		} else {
			setText(item.getDisplayName());
			btn.setText(item.getDisplayName());
			btn.setSelected(item.isChecked());
			setGraphic(btn);
		}
	}

	@Override
	public void cancelEdit() {
		// TODO Auto-generated method stub
		super.cancelEdit();
	}

	@Override
	public void commitEdit(CheckItem newValue) {
		// TODO Auto-generated method stub
		super.commitEdit(newValue);

	}

	@Override
	public void startEdit() {
		// TODO Auto-generated method stub
		super.startEdit();
	}


}