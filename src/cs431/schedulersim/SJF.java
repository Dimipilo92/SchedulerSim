package cs431.schedulersim;

import java.util.Comparator;
import java.util.List;

public class SJF extends FCFS{
	
	@Override
	public String run(List<Process> input){
		copyProcesses(input);
		processList.sort(new Comparator<Process>() {
			@Override
			public int compare(Process p1, Process p2) {
				return p1.getBurstTime() - p2.getBurstTime();
			}
		});
		
		return runProcesses();
	}
	
	@Override
	protected String getSchedulerName(){
		return "SJF";
	}
}
