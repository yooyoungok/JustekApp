package justek.ide.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GraphData {

	private static GraphData instance;

	public static GraphData getInstance () {
		if(instance==null) {
			instance = new GraphData();
		}
		return instance;
	}
	
	public void saveData(int dirNo) {
		System.out.println("Chart File Save");
		
		File mfile = new File("./home/3.out");
		BufferedReader br;
		FileReader fr = null; 
		String line =null;

//		Acceleration","PositionError","TargetPosition", "ActualPosition","TargetVelocity","ActualVelocity
		File posError_file = new File("./home/"+CommandConst.chartList.get(1)+".txt") ;// posion_error;
		File acc_file = new File("./home/"+CommandConst.chartList.get(0)+".txt");
		File actual_pos_file = new File("./home/"+CommandConst.chartList.get(3)+".txt");
		File target_pos_file = new File("./home/"+CommandConst.chartList.get(2)+".txt");
		File target_vel_file = new File("./home/"+CommandConst.chartList.get(4)+".txt");
		File actual_vel_file = new File("./home/"+CommandConst.chartList.get(5)+".txt");

		
		if(!CommandConst.DEBUG) {

			Runtime runtime = Runtime.getRuntime();
			try {

				Process process = runtime.exec("./home/loff.sh");
				Thread.sleep(1000);
				Process process1 = runtime.exec("./home/ldump3.sh");
				Thread.sleep(2000);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(InterruptedException e ) {
				e.printStackTrace();
			}

			mfile = new File("3.out");
		}

		BufferedWriter acc_out;
		BufferedWriter pos_out;
		BufferedWriter target_pos_out;
		BufferedWriter actual_pos_out;
		BufferedWriter target_vel_out;
		BufferedWriter actual_vel_out;

		if(mfile.exists()) {
			System.out.println("3.out exists");
			try{
				fr = new FileReader(mfile); 
				br = new BufferedReader(fr);

				pos_out = new BufferedWriter(new FileWriter(posError_file));
				acc_out = new BufferedWriter(new FileWriter(acc_file));
				target_pos_out = new BufferedWriter(new FileWriter(target_pos_file));
				actual_pos_out = new BufferedWriter(new FileWriter(actual_pos_file));
				target_vel_out = new BufferedWriter(new FileWriter(target_vel_file));
				actual_vel_out = new BufferedWriter(new FileWriter(actual_vel_file));

				int ta_pos1 = 0;
				int ta_pos2 = 0;
				int ta_pos3 = 0;
				int target_pos = 0;
				int actual_pos = 0;
				int err_pos = 0;
				int accel = 0;

				int actual_vel = 0;
				
				String[] parsingList;
				
				int j = 0;
				while((line = br.readLine()) != null){
					parsingList = line.split(" ");

					if(Integer.parseInt(parsingList[0])==dirNo) {
						target_pos = Integer.parseInt(parsingList[2]);
						actual_pos = Integer.parseInt(parsingList[3]);
						actual_vel =  Integer.parseInt(parsingList[4]);
						
						ta_pos1 = ta_pos2;
						ta_pos2 = ta_pos3;
						ta_pos3 = target_pos;
						err_pos = actual_pos-target_pos; //error_position
						accel = (ta_pos3-ta_pos2)-(ta_pos2-ta_pos1);

						if(j<2) {
							System.out.println("Accel Data parsingList[2]= "+parsingList[2]);
							System.out.println("Accel Data ta_pos1= "+ ta_pos1);
							System.out.println("Accel Data ta_pos2= "+ ta_pos2);
							System.out.println("Accel Data ta_pos3= "+ ta_pos3);
							System.out.println("Accel Data actual_vel = "+ actual_vel);
						}
						else {
							target_pos_out.write(Integer.toString(target_pos)+"\n");
							actual_pos_out.write(Integer.toString(actual_pos)+"\n");
							actual_vel_out.write(Integer.toString(actual_vel)+"\n");
							target_vel_out.write(Integer.toString(ta_pos2-ta_pos1)+"\n"); // Target Velocity
							acc_out.write(Integer.toString(accel)+"\n");
							pos_out.write(Double.toString(err_pos)+"\n");
						}

						j++;

					}
				}

				pos_out.close();
				acc_out.close();
				target_pos_out.close();
				actual_pos_out.close();
				actual_vel_out.close();
				target_vel_out.close();
				
				fr.close();

			}catch(IOException e) {
				e.printStackTrace(); 
			}
		}
		else {
			System.out.println("3.out Not exsits");
		}
	}
	
}
