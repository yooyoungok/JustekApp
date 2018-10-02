package justek.ide.model;

import javafx.scene.control.TableCell;

public class WatchLabelCell extends TableCell<DriverInfo, String> { 
 
	public boolean isBinary = false;

	public WatchLabelCell() { 
	} 

	@Override 
	public void updateItem(String item, boolean empty) { 
		super.updateItem(item, empty); 

		if (empty) { 
			setText(item); 
			setGraphic(null); 
		} else { 
			DriverInfo info = getTableView().getItems().get(getIndex());
			String value = info.getValue().get();
			this.setText(value);

		} 
	} 

	@Override
	protected boolean isItemChanged(String oldItem, String newItem) {
		// TODO Auto-generated method stub
		this.setText(newItem);
		return super.isItemChanged(oldItem, newItem);
		
	} 
	
	

} 

