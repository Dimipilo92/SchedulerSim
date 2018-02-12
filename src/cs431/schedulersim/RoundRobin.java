package cs431.schedulersim;

import java.util.List;

public class RoundRobin extends Scheduler {
	private int rrTime;
	public RoundRobin(int rrTime){
		super();
		this.rrTime = rrTime;
	}
	@Override
	public String run(List<Process> input){
		copyProcesses(input);
		return runProcesses();
	}
	
	protected String runProcesses() {
		StringBuilder sb = new StringBuilder();
		sb.append("CpuTime,PID,StartingBurstTime,"
				+ "EndingBurstTime,CompletionTime\n");
		
		int cpuTime = 0;
		
		int currentIndex = 0;
		while(currentIndex != -1) {
			Process currentProcess = processList.get(currentIndex);
			sb.append(cpuTime+",");
			sb.append(currentProcess.getPid()+",");
			sb.append(currentProcess.getBurstTime()+","); // starting
			
			int run = Math.min(currentProcess.getBurstTime(), rrTime);
			currentProcess.setBurstTime(currentProcess.getBurstTime()-run);
			sb.append(currentProcess.getBurstTime()+","); // ending
			cpuTime+= run;
			if (currentProcess.getBurstTime() == 0) {
				sb.append(cpuTime+"\n");
				completionTimes.add(cpuTime);
				processList.remove(currentIndex);
				currentIndex--;
			}
			else {
				sb.append("0\n");
			}
			
			int nextIndex = getNextIndex(currentIndex);
			if (nextIndex != currentIndex) {
				cpuTime+=SWAP_TIME_QUANTA;
			}
			currentIndex = nextIndex;
		}
		sb.append("\nAverage Completion Time: " + getAverageCompletionTime());
		return sb.toString();
	}

	protected int getNextIndex(int currentIndex) {
		if (processList.isEmpty()){
			return -1;
		}
		for (int i = 1; i < processList.size()+1; i++) {
			int j = (i+currentIndex) % processList.size();
			if(processList.get(j).getBurstTime() != 0) {
				return j;
			}
		}
		return -1;
	}
	
	@Override
	protected String getSchedulerName() {
		return "RoundRobin"+rrTime;
	}
}
