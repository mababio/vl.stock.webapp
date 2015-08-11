/**
 * 
 */
package org.mababio.spring.vltool;

import java.util.LinkedHashSet;

import junit.framework.TestCase;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.junit.runner.RunWith;
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
	
	static final String LOG_PROPERTIES_FILE = "../ValueLineHibernate/properties/log4j.properties";
	
	
	private static Logger iLOG = Logger.getLogger(VLDomainTest.class);
	
	
	@Autowired
	private StockRepo service;
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		PropertyConfigurator.configure(LOG_PROPERTIES_FILE);
		iLOG.setLevel(Level.DEBUG);
		iLOG.info(" SetUP--  ");
		System.out.println("<<<<Junit setUp ");
		System.out.println(System.getProperty("java.class.path"));
	}
	
	
	

	@Test
	public  void testLoadData(){	
		
		service.deleteAll();
		iLOG.info(" STARTED testLoadValueLine --  ");
		StocksPdfExtractor pdfEx=new StocksPdfExtractor();
		LinkedHashSet<Stock> vlStockSet=pdfEx.getPdfValueLines(); 
		service.save(vlStockSet);
	}
	

	

		



		



}
