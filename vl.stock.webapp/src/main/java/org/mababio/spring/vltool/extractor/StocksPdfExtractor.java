/**
 * author : Michael Ababio
 * Purpose: This class is the visible system that transforms pdf data into java objects
 * input: String representation of directory containing pdf
 * output: Java collection of Stock objects
 */
package org.mababio.spring.vltool.extractor;

import java.io.*;
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
import org.mababio.spring.vltool.utils.DateToken;
import org.mababio.spring.vltool.utils.VLineUtils;

import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;





public class StocksPdfExtractor {
	
	
	/*Field Deceleration */
	private String  folder;
	static final String LOG_PROPERTIES_FILE = "src/log4j/log4j.properties";
	private static Logger iLOG = Logger.getLogger(StocksPdfExtractor.class);
	/*Field Deceleration */



	public StocksPdfExtractor(final String folder){
		this.folder = folder;
	}


	public String getFolder() {
		return folder;
	}


	/*
	* Purpose:Main method that coordinates subsystem to produce the objects from pdf
	* input: nothing
	* output: collection of Stock object
	* */
	public Collection<Stock> getStockFromPDF() throws Exception {
		LinkedHashSet<Stock> vlStockSet=new LinkedHashSet<>();
		LinkedHashSet<String> filesSet=new LinkedHashSet<>();
		File folder = new File(getFolder());
		filesSet = this.getPDFFilePath(folder,filesSet);
		/*logging*/ System.out.println(" CONTENTS ["+filesSet.size()+"]");/*logging*/
		 Iterator<String> itx=filesSet.iterator();
		 while(itx.hasNext()){
			 String fileName=itx.next();
			 /*logging*/iLOG.info(" fileName ["+fileName+"]");/*logging*/
			 LinkedHashMap<Integer,String> loadedPageSet= processVLPDF(fileName);
			 /*logging*/iLOG.info(" loadedPageSet size ["+loadedPageSet.size()+"]");/*logging*/
			 Set<Stock> wkvline= processValueLinePayLoad(loadedPageSet);
			 if(null!=wkvline){
				 vlStockSet.addAll(wkvline);
			 }
		 }
		return vlStockSet;
	}



	/*
	* Puspose: Intermediate step in getStockFromPDF(a.k.a main method)
	* input: file path of pdf
	* output : intermediate result used in getStockFromPDF(a.k.a main method)
	*
	*/
	private static LinkedHashMap<Integer,String>  processVLPDF(String pdf){
		com.itextpdf.text.pdf.PdfReader reader;
		BufferedReader breader=null;
		LinkedHashMap<Integer,String> result=new LinkedHashMap<>();
		
		try{
			reader = new com.itextpdf.text.pdf.PdfReader(pdf);
			PdfReaderContentParser parser = new PdfReaderContentParser(reader);
		    TextExtractionStrategy strategy;  
		    strategy = parser.processContent(47, new SimpleTextExtractionStrategy());  
		    breader = new BufferedReader(new StringReader(strategy.getResultantText()));
		    String aLine ;//= null;
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
	


	private LinkedHashSet<String> getPDFFilePath(final File InputFolder, LinkedHashSet<String> pdfFiles){
		 String temp;
		 for (final File fileEntry : InputFolder.listFiles()) {
		      if (fileEntry.isDirectory()) {
		         /*logging*/System.out.println("Reading files under the folder "+InputFolder.getAbsolutePath());/*logging*/
		         pdfFiles=getPDFFilePath(fileEntry, pdfFiles);
			  } else {
		        if (fileEntry.isFile()) {
		          temp = fileEntry.getName();
		          if ((temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("pdf")){
		            /*logging*/System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>File= " + InputFolder.getAbsolutePath()+ "\\" + fileEntry.getName());/*logging*/
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


	/*for testing purposes*/
	private  void dumpStringCollectionToFile(Collection dumpStrings) throws FileNotFoundException {

		PrintWriter out = new PrintWriter("C:/Users/Workstation/Desktop/fileDump.txt");

		dumpStrings.stream().forEach(dump -> out.println(dump.toString()));

		out.close();
	}


	private  Set<Stock> processValueLinePayLoad(LinkedHashMap<Integer,String> VLMap) throws Exception {
		/*logging*/iLOG.debug(">>>>> processValueLinePayLoad Starting the Stock Load Process....");/*logging*/
		
		Set<Stock> stockSet = new HashSet<>();
		ValueLine vaLine=new ValueLine();
		DateToken dtk=VLineUtils.getDateToken(VLMap.get(2));//Generate DateToken from Line
		Calendar cal=VLineUtils.getVLCalendar(dtk);
		vaLine.setVlineDate(cal.getTime());
		
		  
		 Collection<String> allStks= VLMap.values();

		/*this is for testing purposes*/

		dumpStringCollectionToFile(allStks);


		 //Iterator<String> stxr=allStks.iterator();

		for(String str : allStks){

			String stckLine=str.trim();
			if(Character.isDigit(stckLine.charAt(0))){
				Stock stck=VLineUtils.getValueLineStock(stckLine,vaLine);
				stockSet.add(stck);
			}
		}
		 iLOG.debug(">>>>> processValueLinePayLoad COMPLETED STOCK LOAD ");
		return stockSet;
		
	}


}
