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
	
	public abstract void run(List<Process> processList, File testFile) throws FileNotFoundException ;

	protected abstract String getSchedulerName();
	
	protected void copyProcesses(List<Process> otherList) {
		processList = new ArrayList<Process>();
		for (Process p : otherList) {
			processList.add(new Process(p));
		}
	}
	
	protected int getAverageCompletionTime() {
		int total=0;
		for (Integer t : completionTimes) {
			total+=t;
		}
		return total/completionTimes.size();
	}
	
	protected void writeToCSV(String content, File testFile) throws FileNotFoundException {
		String parent = testFile.getParent();
		String fullName = testFile.getName();
		String fileName = fullName.substring(0, fullName.lastIndexOf('.'));
		PrintWriter pw = new PrintWriter(new File(parent+"\\"+getSchedulerName()+"-"+fileName+".csv"));
		pw.write(content);
		pw.close();
	}
}
