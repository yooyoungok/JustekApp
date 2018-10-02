package justek.ide.model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import justek.ide.utils.StringUtil;

public class LabelCell extends TableCell<SDOInfo, String> { 
 
	public boolean isBinary = false;
	
         public LabelCell() { 
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
            	 SDOInfo info = getTableView().getItems().get(getIndex());
            	 
            	 String value = info.getValue();
            	 
            	 if(value.contains("0x")) {
            		 value = StringUtil.getHexToDecimalValue(value);
            	 }
            	 
            	 String value2= info.getValue2();
            	 if(value2!=null &&value2.contains("0x")) {
            		 value2 = StringUtil.getHexToDecimalValue(value2);
            	 }
            	 
            	 if(value2!=null) {
            	 try {
            		 if(!value.equals(value2)) {
            			 setTextFill(Color.RED); 
            			 setStyle("-fx-background-color: yellow");

            		 }
            		 else {
            			 setTextFill(Color.BLACK); 
            			 setStyle("-fx-background-color: transparent");
            		 }
            	 }
            	 catch(NullPointerException e){
            		 
            	 }
            	 }
            	 
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

