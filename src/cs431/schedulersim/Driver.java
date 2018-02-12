package cs431.schedulersim;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public class Driver {
	
	public static void main(String[] args) throws Exception{
		Driver.run(args);
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
		return directory.listFiles();
	}
	
	public static Scheduler[] getSchedulers() {
		return new Scheduler[]{
				new FCFS(),
				new SJF(),
				new RoundRobin(25),
				new RoundRobin(50),
				new Lottery(25)};
	}
	
	public static void run(String[] args) throws Exception{
		String inputDirectory = absoluteDirectory(args[0]);
		String outputDirectory = absoluteDirectory(args[1]);
		File[] testFiles = getTestFiles(inputDirectory);
		Scheduler[] schedulerList = getSchedulers();

		StringBuilder sb = new StringBuilder();
		sb.append(",FCFS,SJF,RoundRobin25,RoundRobin50,Lottery\n");
		
		for (File file : testFiles) {
			sb.append(file.getName()+",");
			for(Scheduler scheduler : schedulerList) {
				List<Process> processList = ProcessListCreator.readFile(file);
				scheduler.simulate(processList, file, outputDirectory);
				double c = scheduler.getAverageCompletionTime();
				sb.append(c+",");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		PrintWriter pw = new PrintWriter(new File(outputDirectory+"\\averages.csv"));
		pw.write(sb.toString());
		pw.close();
	}
}
