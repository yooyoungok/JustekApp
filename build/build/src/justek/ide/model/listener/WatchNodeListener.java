package justek.ide.model.listener;

import justek.ide.view.WatchNodeController;

public interface WatchNodeListener {
	void selectSignal(String key,WatchNodeController node);
	
	void deleteSignal(String key);
}
