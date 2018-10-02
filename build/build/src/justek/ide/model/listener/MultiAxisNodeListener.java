package justek.ide.model.listener;

import javafx.scene.layout.VBox;
import justek.ide.view.multiAxisComponent;

public interface MultiAxisNodeListener {
	public void removeAixsNode(VBox node);
	public boolean checkSelectedDriver(String oldNode,String node);
}
