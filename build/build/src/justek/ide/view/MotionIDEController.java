package justek.ide.view;

import java.io.BufferedReader;
import java.io.IOException;

import justek.ide.MainApp;

public class MotionIDEController {
	
	private MainApp mainApp;
	/**
	* Is called by the main application to give a reference back to itself.
	* 
	* @param mainApp
	*/
	public void setMainApp(MainApp mainApp) {
	  this.mainApp = mainApp;
	
	} 
	public void handleShell() {
    StringBuffer output = new StringBuffer();
    Process process = null;
    BufferedReader bufferReader = null;
    Runtime runtime = Runtime.getRuntime();
 
    try {
        process = runtime.exec("cmd.exe /c dir");

//        // shell 실행이 정상 동작
//        bufferReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//        String msg = null;
//        while((msg=bufferReader.readLine()) != null) {
//            output.append(msg + System.getProperty("line.separator"));
//        }
//        bufferReader.close();
//
//        // shell 실행시 에러가 발생
//        bufferReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
//        while((msg=bufferReader.readLine()) != null) {
//            output.append(msg + System.getProperty("line.separator"));
//        }
    } catch (IOException e) {
        output.append("IOException : " + e.getMessage());
        e.printStackTrace();
    } finally {
        try {
            process.destroy();
            if(bufferReader != null) bufferReader.close();
        } catch(IOException e1) {
            e1.printStackTrace();
        }
    }

  //  return output.toString();
	}

}
