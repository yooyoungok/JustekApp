package justek.ide.model;

import java.util.HashMap;

import com.supinan.util.timer.SupinanTimer;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.ComboBoxTableCell;
import justek.ide.model.listener.RealTimeEventListener;


public class ComboTableCell extends ComboBoxTableCell<DriverInfo, String>  { 

	
	private HashMap<String,RealTimeThread> mThreadList ; //�ǽð����� �����带 ����̹� �ѹ��� Ű������ �����Ѵ�..
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
         
		//����̹��� ���� �ǽð������� �����带 �����Ѵ�.
		public void getRealTimeData(String DriverNo) {
			
			//�ش� ����̹��� ���� �����尡 �ִ°��� �ٽ� �������� �ʴ´�...
			if(this.mThreadList.containsKey(DriverNo)) { 
				System.out.println("�̹� ���õ� ����̹��� �ǽð������� ��û�Ǿ����ϴ�.");
				return;
				}
			
			
			RealTimeThread thread = new RealTimeThread(5000,DriverNo);
			thread.addRealTimeListener(this.listener);
			thread.mThreadId = this.timer.addTimer(thread);
			this.mThreadList.put(DriverNo, thread);
		}
		

     } 

