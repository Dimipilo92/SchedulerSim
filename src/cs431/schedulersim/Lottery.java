package cs431.schedulersim;


public class Lottery extends RoundRobin {
	
	public Lottery(int rrTime) {
		super(rrTime);
	}

	protected int getNextIndex(int currentIndex) {
		if (processList.isEmpty()){
			return -1;
		}
		int totalPriority = 0;
		for (Process p: processList) {
			totalPriority+=p.getPriority();
		}
		int selection = (int) (Math.random()*totalPriority);
		
		int total = 0;
		int i = 0; 
		while (selection < total) {
			total+=processList.get(i).getPriority();
			i++;
		}
		
		return i;
	}
	
	protected String getSchedulerName() {
		return "Lottery"+rrTime;
	}
}
