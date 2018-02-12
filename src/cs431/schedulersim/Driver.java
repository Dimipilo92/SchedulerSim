package cs431.schedulersim;

import java.io.File;
import java.util.List;

public class Driver {
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		String inputDirectory = absoluteDirectory(args[0]);
		String outputDirectory = absoluteDirectory(args[1]);
		System.out.println(inputDirectory);
		System.out.println(outputDirectory);
		
		File[] testFiles = getTestFiles(inputDirectory);
		
		Driver.run(testFiles, outputDirectory);
		
		//Driver.run()
	}
	
	private static String absoluteDirectory(String relativeDir) {
		String absoluteDirectory;
		if (relativeDir.charAt(0) == 'C') {
			absoluteDirectory = relativeDir;
		}
		else {
			String callingPath = System.getProperty("user.dir");
			absoluteDirectory = callingPath+"\\"+relativeDir;
		}
		return absoluteDirectory;
	}
	
	public static File[] getTestFiles(String inputDirectory) {
		File directory = new File(inputDirectory);
		File[] listOfFiles = directory.listFiles();
		return directory.listFiles();
	}
	
	public static void run(File[] testFiles, String outputPath) throws Exception{
		//String[] testFiles = getTestFiles();
		Scheduler[] schedulerList = {
				new FCFS(),
				new SJF(),
				new RoundRobin(25),
				new RoundRobin(50),
				new Lottery(25)
		};
		
		for (File file : testFiles) {
			for(Scheduler scheduler : schedulerList) {
				List<Process> processList = ProcessListCreator.readFile(file);
				scheduler.run(processList, file);
			}
		}
	}
}
