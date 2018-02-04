package cs431.schedulersim;

import java.util.Comparator;

// greatest priority first
public class PriorityComparator implements Comparator<Process>{
	@Override
	public int compare(Process p1, Process p2) {
		return p2.getPriority()-p1.getPriority();
	}
}
