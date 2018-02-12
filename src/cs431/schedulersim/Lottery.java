package cs431.schedulersim;


public class Lottery extends RoundRobin {
	
	public Lottery(int rrTime) {
		super(rrTime);
	}
	
	@Override
	protected int getNextIndex(int currentIndex) {
		
		if (processList.isEmpty()){
			return -1;
		}
		int totalPriority = 0;
		for (Process p: processList) {
			totalPriority+=p.getPriority();
		}
		int target = (int) (Math.random()*totalPriority);
		int total = 0;
		int i = 0; 
		while (total < target) {
			total+=processList.get(i).getPriority();
			i++;
		}
		
		return (target == 0)? 0 : i-1;
	}
	
	protected String getSchedulerName() {
		return "Lottery"+rrTime;
	}
}
