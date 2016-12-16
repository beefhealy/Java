
import javax.swing.JOptionPane;

//TODO implement logging

public class PrintPDF 
{
	private static String printMessage;
			
	/**
	 * @param args printer and filename must be passed to main method
	 */
	public static void main(String[] args) 
	{	
		System.out.println(PrintPDF.class.getName());
		try 
		{ 			
		if(args.length < 2)
		{
			System.out.println("Usage: a <printer> and a <filename> must be passed - aborting.");
			return;
		} 		
			System.out.println("Running print job now");
			//HardCopyPrintJob HCPrintJob = new HardCopyPrintJob("Send To OneNote 2013", "C:\\temp\\file.PDF");
			HardCopyPrintJob HCPrintJob = new HardCopyPrintJob(args[0], args[1]);
			HCPrintJob.setLogFile("C:/temp/javaPrint.txt");
			
			printMessage = HCPrintJob.printHardcopy();
			
			if(args.length > 2)
			{ 
				if(args[3] == "verbose")
				{
					JOptionPane.showMessageDialog(null, printMessage);
				}
			}			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}	
}
