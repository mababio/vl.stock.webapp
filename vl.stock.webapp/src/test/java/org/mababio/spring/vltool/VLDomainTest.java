/**
 * 
 */
package org.mababio.spring.vltool;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mababio.config.StockWebAppApplication;
import org.mababio.spring.vltool.domain.Stock;
import org.mababio.spring.vltool.extractor.StocksPdfExtractor;
import org.mababio.spring.vltool.repository.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StockWebAppApplication.class)
@WebAppConfiguration
public class VLDomainTest extends TestCase {
	
//	static final String LOG_PROPERTIES_FILE = "../ValueLineHibernate/properties/log4j.properties";
	
	
//	private static Logger iLOG = Logger.getLogger(VLDomainTest.class);
	
/*

	@Autowired
	private StockRepo service;
*/

	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
/*
		PropertyConfigurator.configure(LOG_PROPERTIES_FILE);
		iLOG.setLevel(Level.DEBUG);
		iLOG.info(" SetUP--  ");
*/
		System.out.println("<<<<Junit setUp ");
		System.out.println(System.getProperty("java.class.path"));
	}

/*
	@Test
	public void writeToFile(){

		PrintWriter out = null;
		try {
			out = new PrintWriter("/home/ea/Desktop/oneTwo.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		out.println("hello mike, it's working");

		out.close();


	}*/



	@Test
	public void  TestOutputRaw() throws Exception  {
		String folder = "/home/ea/IdeaProjects/vl.stock.webapp/vl.stock.webapp/src/main/resources/VL/VLpdf";

		StocksPdfExtractor stocksPdfExtractor = new StocksPdfExtractor(folder);
		try {
			stocksPdfExtractor.getStockFromPDF();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
/*

	@Test
	public  void testLoadData() throws FileNotFoundException {
		String folder = "/home/ea/IdeaProjects/vl.stock.webapp/vl.stock.webapp/src/main/resources/VL/VLpdf";
		service.deleteAll();
		iLOG.info(" STARTED testLoadValueLine --  ");
		StocksPdfExtractor pdfEx=new StocksPdfExtractor(folder);
		LinkedHashSet<Stock> vlStockSet=pdfEx.getStockFromPDF();
		service.save(vlStockSet);
	}



	@Test
	public void  testRepo(){

		List<Stock> list = service.findByStkName("Goodrich Petroleum");

		System.out.println("List:  ---> "+list);

	}
	
*/

	

		



		



}
