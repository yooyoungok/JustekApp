package justek.ide.model;

import javafx.beans.property.SimpleBooleanProperty;

public class CheckItem {
   SimpleBooleanProperty selected; 
    String displayName;
    String internalName;    

    public boolean isChecked() {
    	return selected.get();
    }

    public void setChecked(boolean checked) {
    	this.selected.set(checked);
    }

    public void setselectedProperty(boolean value){
    	this.selected = new SimpleBooleanProperty(value);
    	
    }
    
    public SimpleBooleanProperty selectedProperty() {
		return selected;
	}
    
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getInternalName() {
        return internalName;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }
    
	@Override 
	public String toString() { 
			return getDisplayName();
		} 

}