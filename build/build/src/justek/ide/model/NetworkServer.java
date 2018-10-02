package justek.ide.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class NetworkServer {
	
	private static NetworkServer instance;

	public static NetworkServer getInstance () {
		if(instance==null) {
			instance = new NetworkServer();
		}
		return instance;
	}
	
    public static void runPlcFile(String runString) {
		int dot = runString.indexOf('.');
		String runString2 = runString.substring(0, dot);
		System.out.println("Client: NetworkServer :: runPlcFile ==" +runString2);
    	try {
    		Socket socketClient = new Socket(CommandConst.address, 12345);
    		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
    		writer.write("enable plc "+runString2+"\r\n");
    		writer.flush();
    		
    		socketClient.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public static void stopPlcFile(String runString) {
		int dot = runString.indexOf('.');
		String runString2 = runString.substring(0, dot);
		System.out.println("Client: NetworkServer :: stopPlcFile ==" +runString2);
    	try {
    		Socket socketClient = new Socket(CommandConst.address, 12345);

    		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
    		writer.write("disable plc "+runString2+"\r\n");
    		writer.flush();
    		
    		socketClient.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
	public String uploadData(String command) {
		
		if(CommandConst.DEBUG) {
			return "error";
		}
		
		String result = "Fail";
		
		Runtime runtime = Runtime.getRuntime();
		try {
			Process process = runtime.exec(command);
			String szLine; 
			String[] array;
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			if ((szLine = br.readLine()) != null) {
				System.out.println(szLine);
						array = szLine.split(" ");
						
						if(array.length!=2) {
							result = "Fail";
						}
						else {
						result = array[1];
						}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void downloadData(String command) {
		
		if(CommandConst.DEBUG){
			return;
		}
		
		System.out.println("downloadData :: "+command);
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec(command);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
