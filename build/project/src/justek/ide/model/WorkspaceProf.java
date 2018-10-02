package justek.ide.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class WorkspaceProf {
    private final StringProperty workspaceName;
    private final StringProperty workspacePath;
 	
    /**
     * Default constructor.
     */
    public WorkspaceProf() {
        this(null, null);
    }
    /**
     * Constructor with some initial data.
     * 
     * @param firstName
     * @param lastName
     */
    public WorkspaceProf(String workspaceName, String workspacePath) {
        this.workspaceName = new SimpleStringProperty(workspaceName);
        this.workspacePath = new SimpleStringProperty(workspacePath);
 
    }

    public String getWorkspaceName() {
        return workspaceName.get();
    }

    public void setWorkspaceName(String workspaceName) {
        this.workspaceName.set(workspaceName);
    }

    public StringProperty workspaceNameProperty() {
        return workspaceName;
    }

    public String getWorkspacePath() {
        return workspacePath.get();
    }

    public void setWorkspacePath(String workspacePath) {
        this.workspaceName.set(workspacePath);
    }

    public StringProperty workspacePathProperty() {
        return workspacePath;
    }

}
