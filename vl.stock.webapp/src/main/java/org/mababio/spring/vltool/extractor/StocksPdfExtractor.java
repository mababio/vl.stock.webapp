/**
 * 
 */
package org.mababio.spring.vltool.extractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.mababio.spring.vltool.domain.Stock;
import org.mababio.spring.vltool.domain.ValueLine;
//import org.mababio.spring.vltool.pdf.extractor.ValueLinePdfManager;
import org.mababio.spring.vltool.utils.DatabaseUtility;
import org.mababio.spring.vltool.utils.DateToken;
import org.mababio.spring.vltool.utils.VLineUtils;

import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;


public class StocksPdfExtractor {
	//where the pdf lives// make sure you change it!!!!!
	public static final String FOLDER = "C:/VL/VLpdf";
	//private static org.mababio.spring.vltool.pdf.extractor.ValueLinePdfManager pdfMngr;
	static final String LOG_PROPERTIES_FILE = "src/log4j/log4j.properties";
	private static Logger iLOG = Logger.getLogger(StocksPdfExtractor.class);


	public LinkedHashSet<Stock> getPdfValueLines(){
		LinkedHashSet<Stock> vlStockSet=new LinkedHashSet<Stock>();
		LinkedHashSet<String> filesSet=new LinkedHashSet<String>();
		File folder = new File(FOLDER);
		filesSet = this.loadValueLinePDFs(folder,filesSet);
		 System.out.println(" CONTENTS ["+filesSet.size()+"]");
		 Iterator<String> itx=filesSet.iterator();
		 while(itx.hasNext()){
			 String fileName=itx.next();
			 iLOG.info(" fileName ["+fileName+"]");
			 LinkedHashMap<Integer,String> loadedPageSet= processVLPDF(fileName);
			 iLOG.info(" loadedPageSet size ["+loadedPageSet.size()+"]");
			 Set<Stock> wkvline= processValueLinePayLoad(loadedPageSet);
			 if(null!=wkvline){
				 vlStockSet.addAll(wkvline);
			 }
		 }
		return vlStockSet;
	}
	private static LinkedHashMap<Integer,String>  processVLPDF(String pdf){
		com.itextpdf.text.pdf.PdfReader reader;
		BufferedReader breader=null;
		LinkedHashMap<Integer,String> result=new LinkedHashMap<Integer,String>();
		
		try{
			reader = new com.itextpdf.text.pdf.PdfReader(pdf);
			PdfReaderContentParser parser = new PdfReaderContentParser(reader);
		    TextExtractionStrategy strategy;  
		    strategy = parser.processContent(47, new SimpleTextExtractionStrategy());  
		    breader = new BufferedReader(new StringReader(strategy.getResultantText()));
		    String aLine = null;
		    int counter=0;
		    
		    while ((aLine = breader.readLine()) != null){
		    	counter++;
		    	result.put(counter, aLine);
		    }
		        
		}catch (Throwable e) {
			e.printStackTrace();
		}finally{
			if(null!=breader){
				try {
					breader.close();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	private LinkedHashSet<String> loadValueLinePDFs(File InputFolder,LinkedHashSet<String> pdfFiles){
		 String temp = ""; 
		 for (final File fileEntry : InputFolder.listFiles()) {
		      if (fileEntry.isDirectory()) {
		         System.out.println("Reading files under the folder "+InputFolder.getAbsolutePath());
		         pdfFiles=loadValueLinePDFs(fileEntry,pdfFiles);
		      } else {
		        if (fileEntry.isFile()) {
		          temp = fileEntry.getName();
		          if ((temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("pdf")){
		            System.out.println("File= " + InputFolder.getAbsolutePath()+ "\\" + fileEntry.getName());
		            String fPath=InputFolder.getAbsolutePath()+ "\\" + fileEntry.getName();
		            fPath=StringUtils.replace(fPath, "\\","/");
		            pdfFiles.add(fPath);
		          }
		        }
	}
		 }
		 System.out.println("Total File Count ["+pdfFiles.size()+"]");
    	 
		return pdfFiles;
	}
	
	private  Set<Stock> processValueLinePayLoad(LinkedHashMap<Integer,String> VLMap){
		iLOG.debug(">>>>> processValueLinePayLoad Starting the Stock Load Process....");
		
		Set<Stock> stockSet = new HashSet<>();
		ValueLine vaLine=new ValueLine();
		DateToken dtk=VLineUtils.getDateToken(VLMap.get(2));//Generate DateToken from Line
		
		Calendar cal=VLineUtils.getVLCalendar(dtk);
		vaLine.setVlineDate(DatabaseUtility.convertCalendarToSQLDate(cal));
		
		  
		 Collection<String> allStks= VLMap.values();
		 Iterator<String> stxr=allStks.iterator();
		
		 while(stxr.hasNext()){
			 String stckLine=stxr.next();
			 stckLine=stckLine.trim();
		
			 if(Character.isDigit(stckLine.charAt(0))){
				 Stock stck=VLineUtils.getValueLineStock(stckLine,dtk);
				 stck.setValueLine(vaLine);
				 stockSet.add(stck);
			}
		 
		 }
		 iLOG.debug(">>>>> processValueLinePayLoad COMPLETED STOCK LOAD ");
		return stockSet;
		
	}


}
