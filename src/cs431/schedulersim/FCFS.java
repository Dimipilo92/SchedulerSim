package cs431.schedulersim;

import java.util.List;

public class FCFS extends Scheduler {
	
	public String run(List<Process> input) {
		copyProcesses(input);
		return runProcesses();
	}
	
	protected String runProcesses() {
		StringBuilder sb = new StringBuilder();
		sb.append("CpuTime,PID,StartingBurstTime,"
				+ "EndingBurstTime,CompletionTime\n");
		
		int cpuTime = 0;
		for(int i = 0; i < processList.size(); i++) {
			Process p = processList.get(i);
			sb.append(cpuTime+",");
			sb.append(p.getPid()+",");
			sb.append(p.getBurstTime()+",");
			sb.append("0,");
			cpuTime+= p.getBurstTime();
			sb.append(cpuTime+"\n");
			completionTimes.add(cpuTime);
			cpuTime+=SWAP_TIME_QUANTA;
		}
		sb.append("\nAverage Completion Time: " + getAverageCompletionTime());
		return sb.toString();
	}

	@Override
	protected String getSchedulerName() {
		return "FCFS";
	}
}
