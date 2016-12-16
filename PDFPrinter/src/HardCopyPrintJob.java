


import java.io.*;
import org.apache.pdfbox.*;
import org.apache.pdfbox.pdmodel.*;
import javax.print.PrintService;
import java.awt.print.*;
import java.util.Date;

/**
 * @author cohealy
 * @since  June 2014
 * @version 1.00.0 
 * @category pdf printing
 */


public class HardCopyPrintJob 
{
	 
	private String 		logfile = "C:/temp/javaPrint.txt";
	private String 		printerName;
	private String 		hardCopyFile;
	private File 		PrintFile = null;
	private PrinterJob 	hardcopyPrint;
	private PrintService printer; 
	private Double[] 	paperDimensions;
		
		
	public HardCopyPrintJob(){
	}
	public HardCopyPrintJob(String printer, String filename)
	{
		setPrinter(printer);
		setHardcopyFile(filename);		
		log("Init print: " + hardCopyFile + " on " + printerName);
	}
	
	/**
	 * @param ipsPrinter The Printer name for this print job,
	 * 					 It must be a local printer	
	 */
	public void setPrinter(String ipsPrinter)
	{
		if(ipsPrinter != null)
		{
			printerName = ipsPrinter;
		}
	}
	

	public String getPrinter()
	{
		return printerName; 
	}
	public void setLogFile(String ipsLog)
	{
		if(ipsLog != null)
		{
			logfile = ipsLog;
		}
	}
	
	public String getLogFile()
	{
		return logfile; 
	}
	
	
	/**
	 * @param ipsHardcopyFile The name of the file we want to print,
	 * 						  The full file path is required.
	 */
	public void setHardcopyFile(String ipsHardcopyFile)
	{
		if(ipsHardcopyFile != null)
		{
			hardCopyFile = ipsHardcopyFile;
		}
	}
	
	/**
	 * @return The name of the file being printed
	 */
	public String getHardCopyFile()
	{
		return hardCopyFile;
	}
	
	public PageFormat setPrintingSize()
	{
		Paper hcPaper = new Paper();
		paperDimensions = PaperSize.getPaperSize(4);
		hcPaper.setSize(paperDimensions[0],paperDimensions[1]);
		hcPaper.setImageableArea(10, 10, 190, 277);
		
		PageFormat hcPageForm = new PageFormat();
		hcPageForm.setPaper(hcPaper);
				
		return hcPageForm;		
	}
	
	
	public String printHardcopy() 
	{
		
		if(printerName.isEmpty())
		{
			return "No printer!";
		}
		if(hardCopyFile.isEmpty())
		{
			return "No filename!";
		}
		
		try
		{
			//get the file we were passed into memory
			PrintFile = new File(hardCopyFile);
			if(!PrintFile.exists())
			{ 
				return "File not found";
			}
			
			//get a local printer for this print job
			printer = LocalPrinter.getPrinter(printerName);			
			if(printer == null)
			{
				return "Printer " + printerName + " not installed locally";
			}
			//set up a print job to pass to pdfbox so we can have the printer and paper sizes etc ready
			PrinterJob hardcopyPrint = PrinterJob.getPrinterJob();
			hardcopyPrint.setPrintable(null, setPrintingSize());
			hardcopyPrint.setPrintService(printer); //and set the print job up with the printer we need
		 		
			log("Loading the document now");
			
			PDDocument HardcopyDoc = PDDocument.load(PrintFile);
			HardcopyDoc.silentPrint(hardcopyPrint);
			
		}
		catch(Exception printingException)
		{
			printingException.printStackTrace();
			return "Print Exception: " + printingException.getMessage();
		}		
		log("Print job submitted: " + hardCopyFile + " to " + printerName);
		log("*****************************************************************");	
		return "Print job submitted: " + hardCopyFile + " to " + printerName;
	}	
	
	//need to implement a proper logging solution
	private void log(String ipsLogLine) 
	{
		Date date = new Date();
		try 
		{
			BufferedWriter bw = new BufferedWriter(new FileWriter(getLogFile(), true));
			bw.write(date.toString() + " - " + ipsLogLine);
			bw.newLine();
			bw.flush();
		} 
			catch(IOException eLogException) 
			{
	            System.out.println(eLogException); 
	            eLogException.printStackTrace();
			} 
    }
}
