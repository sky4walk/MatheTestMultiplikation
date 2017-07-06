import java.io.Console;
import java.util.Random;
 
/**
 *
 * @author andre@andrebetz.de
 */
public class MatheTest {
	private int countTasks = 80;
	private int countCorr;
	private int[] taskList;
	private MatheTask task;
	private Random randomGenerator = new Random();
	private int b[] = {1,2,3,4,5,5,7,8,9,10};	
	
	public MatheTest(int a[], int taskCnt) {
		countTasks = taskCnt;
	    task = new MatheTask(a,b);
		taskList = generateTaskList(task.getPossibilities());
	}
	
	private int[] generateTaskList(int possibilities) {
		int[] list = new int[possibilities];
		for ( int i = 0; i < possibilities; i++ ) {
			list[i] = i;
		}
		for ( int i = 0; i < possibilities; i++ ) {
			int pos = randomGenerator.nextInt(possibilities);
			int tmp = list[i];
			list[i] = list[pos];
			list[pos] = tmp;
		}
		return list;
	}

	public void playList() {
		countCorr = 0;
		long startTime = System.currentTimeMillis();
		for ( int i = 0; i < taskList.length && i < countTasks; i++ ) {
			System.out.print("("+Integer.toString(i+1)+") ");
			String[] taskStr = task.generateTask(taskList[i]);
			
			if ( playOne(taskStr) ) countCorr++;
		}
		long endTime = System.currentTimeMillis();
		long diffTime = endTime-startTime;
		System.out.println("Erreicht " + countCorr + " von " + countTasks);
		System.out.println("Zeit verbraucht : " + diffTime/1000 + " Sek");
	}
	
	private boolean playOne(String[] taskStr) {
		String out = "";
		boolean ergebnis = false;
		for ( int i=0; i< taskStr.length-2; i++) {
			out += taskStr[i] +" ";
		}
		System.out.print(out);		
		String ret = waitForEnter("");
		
		if ( 0 < ret.length() ) {
			if ( ret.equals(taskStr[taskStr.length-1]) ) {
				System.out.println("Super");
				ergebnis = true;
			} else {
				System.out.println("Richtig ist:" + taskStr[taskStr.length-1]);		
			}
		} else {
			System.out.println("Richtig ist:" + taskStr[taskStr.length-1]);		
		}
		return ergebnis;
	}
	
	private String waitForEnter(String message, Object... args) {
		String retStr = "";
		Console c = System.console();
		if (c != null) {
			// printf-like arguments
			if (message != null)
				c.format(message, args);
			c.format("=> ");
			
			retStr = c.readLine();						
		}
		return retStr;
	}

    public static void main(String[] args) {
	
		int a[] = {2,4,5,8};
		
		MatheTest test;
		
		if ( args.length < 1) 
			test = new MatheTest(a,80);
		else
			test = new MatheTest(a,Integer.parseInt(args[0]));
			
		test.playList();
	}
}