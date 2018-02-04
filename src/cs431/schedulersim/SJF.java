package cs431.schedulersim;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;

public class SJF extends FCFS{
	
	@Override
	public void run(List<Process> input, File testFile) throws FileNotFoundException {
		copyProcesses(input);
		processList.sort(new Comparator<Process>() {
			@Override
			public int compare(Process p1, Process p2) {
				return p1.getBurstTime() - p2.getBurstTime();
			}
		});
		
		String results = runProcesses();
		writeToCSV(results.toString(),testFile);
	}
	
	@Override
	protected String getSchedulerName(){
		return "SJF";
	}
}
