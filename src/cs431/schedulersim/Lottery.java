package cs431.schedulersim;


public class Lottery extends RoundRobin {
	
	public Lottery(int rrTime) {
		super(rrTime);
	}

	protected int getNextIndex(int currentIndex) {
		if (processList.isEmpty()){
			return -1;
		}
		int totalBurst = 0;
		for (Process p: processList) {
			totalBurst+=p.getBurstTime();
		}
		int selection = (int) (Math.random()*totalBurst);
		
		int total = 0;
		int i = 0; 
		while (selection < total) {
			total+=processList.get(i).getBurstTime();
			i++;
		}
		
		return i;
	}
	
	protected String getSchedulerName() {
		return "Lottery";
	}
}
