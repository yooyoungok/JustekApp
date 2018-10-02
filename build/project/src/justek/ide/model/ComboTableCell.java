package justek.ide.model;

import java.util.HashMap;

import com.supinan.util.timer.SupinanTimer;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.ComboBoxTableCell;
import justek.ide.model.listener.RealTimeEventListener;


public class ComboTableCell extends ComboBoxTableCell<DriverInfo, String>  { 

	
	private HashMap<String,RealTimeThread> mThreadList ; //실시간정보 쓰레드를 드라이버 넘버를 키값으로 저장한다..
    private SupinanTimer timer;
    private DriverInfo info;
    private RealTimeEventListener listener;
    
    
         public ComboTableCell() { 
     		this.mThreadList =new HashMap<String,RealTimeThread>();
         } 
         
         public ComboTableCell( ObservableList<String> list) { 
        	 super(list);
     		this.mThreadList =new HashMap<String,RealTimeThread>();
         }
 
         public ComboTableCell( ObservableList<String> list,RealTimeEventListener listener) { 
        	 super(list);
     		this.mThreadList =new HashMap<String,RealTimeThread>();
     		this.listener = listener;
         }
         
         @Override 
         public void startEdit() { 
             if (!isEmpty()) { 
                 super.startEdit(); 
             } 
         } 
 
 
         @Override 
         public void cancelEdit() { 
             super.cancelEdit(); 
             setGraphic(null); 
         } 
 
         @Override 
         public void updateItem(String item, boolean empty) { 
             super.updateItem(item, empty); 
             if(empty) {
            	 setGraphic(null); 
             }
             
             if(this.getTableColumn().getText().contains("Signal")) {
            	 if(this.timer==null) {
            		 this.timer = new SupinanTimer();
            	 }
            	 if(!empty) {
            		 if(item!=null && !item.equals("")) {
            			 info = getTableView().getItems().get(getIndex());
    
            			 if(info!=null) {
            				 this.getRealTimeData(info.getDriverNo().get());
            			 }
            		 }
            	 }
             }
             
 
         }

		@Override
		public void commitEdit(String newValue) {
			// TODO Auto-generated method stub
			super.commitEdit(newValue);
		} 
         
		//드라이버에 따라 실시간데이터 쓰레드를 생성한다.
		public void getRealTimeData(String DriverNo) {
			
			//해당 드라이버에 대한 쓰레드가 있는경우는 다시 생성하지 않는다...
			if(this.mThreadList.containsKey(DriverNo)) { 
				System.out.println("이미 선택된 드라이버의 실시간정보가 요청되었습니다.");
				return;
				}
			
			
			RealTimeThread thread = new RealTimeThread(5000,DriverNo);
			thread.addRealTimeListener(this.listener);
			thread.mThreadId = this.timer.addTimer(thread);
			this.mThreadList.put(DriverNo, thread);
		}
		

     } 

