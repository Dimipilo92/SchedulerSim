package cs431.schedulersim;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public class Driver {
	
	public static void main(String[] args) throws Exception{
		Driver.run(args);
	}
	
	public static void run(String[] args) throws Exception{
		String[] ioDirectories = getDirectoriesFromArguments(args);
		String inputDirectory = ioDirectories[0];
		String outputDirectory = ioDirectories[1];
		
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
		PrintWriter pw = new PrintWriter(new File(outputDirectory+"\\averages.csv"));
		pw.write(sb.toString());
		pw.close();
	}
	
	private static File[] getTestFiles(String inputDirectory) {
		File directory = new File(inputDirectory);
		return directory.listFiles();
	}
	
	private static Scheduler[] getSchedulers() {
		return new Scheduler[]{
				new FCFS(),
				new SJF(),
				new RoundRobin(25),
				new RoundRobin(50),
				new Lottery(25)};
	}
	
	private static String[] getDirectoriesFromArguments(String[] args) {
		String dirs[] = {};
		try {
			String inputDirectory = absoluteDirectory(args[0]);
			String outputDirectory = absoluteDirectory(args[1]);
			validateDirectory(inputDirectory);
			validateDirectory(outputDirectory);
			dirs = new String[]{inputDirectory, outputDirectory};
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Requires two arguments: \n" +
					"\t1) input directory(absolute or relative) - where test files are stored\n" +
					"\t2) output directory(absolute or relative) - where results will be stored");
			System.exit(1);
		}
		return dirs;
	}
	
	private static void validateDirectory(String dir){
		File f = new File(dir);
		if (!f.isDirectory()) {
			System.out.println(f.getAbsolutePath()+" is not a valid directory!");
			System.exit(1);
		}
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
}
