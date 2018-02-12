package cs431.schedulersim;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProcessListCreator {
	public static List<Process> readFile(File file) throws FileNotFoundException{
		Scanner fileReader = new Scanner(file);
		List<Process> processList = new ArrayList<Process>();
		
		while (fileReader.hasNext()) {
			int pid = fileReader.nextInt();
			int burstTime = fileReader.nextInt();
			int priority = fileReader.nextInt();
			processList.add(new Process(pid,burstTime,priority));
		}
		
		fileReader.close();
		return processList;
	}
}
