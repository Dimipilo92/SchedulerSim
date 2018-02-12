package cs431.schedulersim;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public abstract class Scheduler {
	protected final int SWAP_TIME_QUANTA = 3;
	
	protected List<Process> processList;
	protected List<Integer>	completionTimes;
	
	public Scheduler() {
		processList = new ArrayList<Process>();
		completionTimes = new ArrayList<Integer>();
	}
	
	public void simulate(List<Process> processList, File testFile, String outputDirectory) throws FileNotFoundException {
		String content = run(processList);
		writeToCSV(content,testFile,outputDirectory);
	}
	
	public double getAverageCompletionTime() {
		int total=0;
		for (Integer t : completionTimes) {
			total+=t;
		}
		return (double)total/(double)completionTimes.size();
	}
	
	protected abstract String run(List<Process> processList);

	protected abstract String getSchedulerName();
	
	protected void copyProcesses(List<Process> otherList) {
		processList = new ArrayList<Process>();
		for (Process p : otherList) {
			processList.add(new Process(p));
		}
	}
	
	private void writeToCSV(String content, File testFile, String outputDirectory) throws FileNotFoundException {
		String fullName = testFile.getName();
		String fileName = fullName.substring(0, fullName.lastIndexOf('.'));
		PrintWriter pw = new PrintWriter(new File(outputDirectory+"\\"+getSchedulerName()+"-"+fileName+".csv"));
		pw.write(content);
		pw.close();
	}
}
