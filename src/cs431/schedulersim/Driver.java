package cs431.schedulersim;

import java.io.File;
import java.util.List;

public class Driver {
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Driver.run();
	}
	
	public static String[] getTestFiles() {
		return new String[] {
				"C:\\Users\\LENOVO USER\\Desktop\\projectdata\\testdata1.txt",
				"C:\\Users\\LENOVO USER\\Desktop\\projectdata\\testdata2.txt",
				"C:\\Users\\LENOVO USER\\Desktop\\projectdata\\testdata3.txt",
				"C:\\Users\\LENOVO USER\\Desktop\\projectdata\\testdata4.txt"
		};
	}
	
	public static void run() throws Exception{
		String[] testFiles = getTestFiles();
		Scheduler[] schedulerList = {
				new FCFS(),
				new SJF(),
				new RoundRobin(25),
				new RoundRobin(50),
				new Lottery(25)
		};
		
		for (String fileName : testFiles) {
			for(Scheduler scheduler : schedulerList) {
				List<Process> processList = ProcessListCreator.readFile(fileName);
				scheduler.run(processList, new File(fileName));
			}
		}
	}
}
