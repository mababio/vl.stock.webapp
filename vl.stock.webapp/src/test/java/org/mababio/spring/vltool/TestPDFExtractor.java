package org.mababio.spring.vltool;

import junit.framework.TestCase;
import org.junit.Test;
import org.mababio.spring.vltool.extractor.StocksPdfExtractor;

import java.io.FileNotFoundException;

/**
 * Created by ea on 10/11/15.
 */
public class TestPDFExtractor extends TestCase {

private  String folder;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
         folder = "/home/ea/IdeaProjects/vl.stock.webapp/vl.stock.webapp/src/main/resources/VL/VLpdf";
        System.out.println("this is the setup method for junit");
    }



    @Test
    public void  TestOutputRaw()  {

        StocksPdfExtractor stocksPdfExtractor = new StocksPdfExtractor(folder);
        try {
            stocksPdfExtractor.getStockFromPDF();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
