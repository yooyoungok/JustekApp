package justek.ide.model.listener;

import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import justek.ide.model.RealTimeInfo;

public interface RealTimeEventListener {
	void realTimeDatarEvent(double Source);
	void realTimeDataInfoEvent(RealTimeInfo Source);
	void autoScanEvent(ObservableList<TreeItem<String>> nodes);
}
