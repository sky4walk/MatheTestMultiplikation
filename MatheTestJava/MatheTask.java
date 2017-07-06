import java.io.Console;
import java.util.Random;
/**
 *
 * @author andre@andrebetz.de
 */
public class MatheTask {
	int[] numbersA; 
	int[] numbersB; 
	String[] operators = {"*",":"};
	
	Random randomGenerator = new Random();
	
	public MatheTask(int[] numbersA,int[] numbersB) {
		this.numbersA = numbersA;
		this.numbersB = numbersB;
	}

	public int getPossibilities() {
		return numbersA.length * numbersB.length * operators.length * 2 * 3;
	}

	public int genRandomTaskNr() {
		return randomGenerator.nextInt(getPossibilities());	
	}
	
	public String[] generateTask(int Nr) {
		int parA = Nr % numbersA.length;
		Nr = Nr / numbersA.length;

		int parB = Nr % numbersB.length;
		Nr = Nr / numbersB.length;
		
		int parC = Nr % operators.length;
		Nr = Nr / operators.length;

		int parD = Nr % 2;
		Nr = Nr / 2;

		int parE = Nr % 3;

		return generateTask(parA,parB,parC,parD,parE);
	}

	private String[] generateTask(int posA,int posB, int op, int change, int miss) {
		String[] taskStr = new String[7];
		
		if ( posA < numbersA.length &&
			 posB < numbersB.length &&
			 op < operators.length  &&
			 miss < 3               &&
			 change < 2) {
		
			int valA = numbersA[posA];
			int valB = numbersB[posB];
			int valC = valA * valB;
			
			taskStr[3] = "=";
			taskStr[5] = "|";
			if ( 0 == op ) {
				taskStr[1] = operators[op];
				taskStr[4] = Integer.toString(valC);
				if ( 0 == change ) {
					taskStr[0] = Integer.toString(valA);		
					taskStr[2] = Integer.toString(valB);		
				} else {
					taskStr[0] = Integer.toString(valB);		
					taskStr[2] = Integer.toString(valA);							
				}
			} else {
				taskStr[0] = Integer.toString(valC);
				taskStr[1] = operators[op];				
				if ( 0 == change ) {
					taskStr[2] = Integer.toString(valA);		
					taskStr[4] = Integer.toString(valB);		
				} else {
					taskStr[2] = Integer.toString(valB);		
					taskStr[4] = Integer.toString(valA);							
				}
			}
			
			if ( 0 == miss ) {
				taskStr[6] = taskStr[0];
				taskStr[0] = "_";
			} else if ( 1 == miss ) {
				taskStr[6] = taskStr[2];
				taskStr[2] = "_";
			} else if ( 2 == miss ) {
				taskStr[6] = taskStr[4];
				taskStr[4] = "_";
			} else {				
			}
		}
		
		return taskStr;
	}
	
	
}
