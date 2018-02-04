package cs431.schedulersim;

public class Process{
	private int pid;
	private int burstTime;
	private int priority;
	
	public Process(int pid, int burstTime, int priority) {
		this.pid = pid;
		this.burstTime = burstTime;
		this.priority=priority;
	}
	
	public Process(Process p) {
		this.pid = p.getPid();
		this.burstTime = p.getBurstTime();
		this.priority = p.getPriority();
	}
	
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getBurstTime() {
		return burstTime;
	}

	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

}
