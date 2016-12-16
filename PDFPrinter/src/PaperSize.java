

public class PaperSize 
{
	private static final Double[] A0Size = {2384.0,3371.0};
	private static final Double[] A1Size = {1685.0,2384.0};
	private static final Double[] A2Size = {1190.0,1684.0};
	private static final Double[] A3Size = {842.0,1190.0};
	private static final Double[] A4Size = {595.0,842.0};
	
	
	/**
	 * @param iPaperSize - A number from 0-4. 4 = A4
	 * @return Paper Size Width/Length in 1/72nds of an inch (Points)
	 */
	public static Double[] getPaperSize(int iPaperSize)
	{
		Double[] returnSize = {0.0,0.0};
		switch(iPaperSize)
		{
		case 0: 	returnSize = A0Size;
					break;
		case 1: 	returnSize = A1Size;
					break;
		case 2: 	returnSize = A2Size;
					break;
		case 3: 	returnSize = A3Size;
					break;
		case 4: 	returnSize = A4Size;
					break;		
		default: 	returnSize = A4Size; //Our default setting will be the most commonly used A4.
        			break;
		}			
		return returnSize;
	}
	
	
}
