

import java.awt.print.PrinterJob;

import javax.print.PrintService;

public class LocalPrinter 
{
	/**
	 * Retrieve the specified Print Service; will return null if not found.
	 * @return 
	 */
	public static PrintService getPrinter(String printerName) 
	{

	    PrintService prnService = null;

	    // Get array of all print services
	    PrintService[] services = PrinterJob.lookupPrintServices();

	    // Retrieve specified print service from the array
	    for (int index = 0; prnService == null && index < services.length; index++) 
	    {

	        if (services[index].getName().equalsIgnoreCase(printerName)) 
	        {
	            prnService = services[index];
	        }
	    }

	    // Return the print service
	    return prnService;
	}
}
