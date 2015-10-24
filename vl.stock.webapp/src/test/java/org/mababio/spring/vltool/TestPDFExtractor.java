package org.mababio.spring.vltool;

import junit.framework.TestCase;

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

import java.io.FileNotFoundException;
import java.util.LinkedHashSet;

/**
 * Created by ea on 10/11/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StockWebAppApplication.class)
@WebAppConfiguration
public class TestPDFExtractor extends TestCase {




@Autowired
private StockRepo service;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.out.println("this is the setup method for junit");
    }


    
    
    @Test
	public  void testLoadData() throws Exception{	
		
		service.deleteAll();
		StocksPdfExtractor stockExt = new StocksPdfExtractor("C:\\Users\\Workstation\\Desktop\\gitClone\\vl.stock.webapp\\vl.stock.webapp\\src\\main\\resources\\VL\\VLpdf");
		 LinkedHashSet<Stock> vlStockSet;
		 
		 try{
			 vlStockSet = (LinkedHashSet<Stock>) stockExt.getStockFromPDF(); 
			service.save(vlStockSet);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
					
	}
    
    
    

}
