package org.mababio.webservices;

import org.mababio.spring.vltool.domain.Stock;
import org.mababio.spring.vltool.repository.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

//import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


/**
 * Created by ea on 8/22/15.
 * Author: Michael Ababio
 */

@Component
@Path("/stock")
public class StockCrud{

    @Autowired
    StockRepo repo;




    @GET
    @Path("/find/byname/{var}")
    @Produces(MediaType.APPLICATION_JSON)
    public  Response findByNameStock(@PathParam("var") String name){

        if(null ==name ||name.isEmpty()) {
        return Response.status(200).entity("The stock name was not provided").build();

        }else {
            List<Stock> result  = repo.findByStkName(name);
            return Response.ok(result).build();
    }

    }

    @GET
    @Path("/find/byticker/{var}")
    @Produces(MediaType.APPLICATION_JSON)
    public  Response findByTickerStock(@PathParam("var") String ticker){

        if(null ==ticker ||ticker.isEmpty()) {
            return Response.status(200).entity("The stock name was not provided").build();

        }else {
            List<Stock> result  = repo.findByTicker(ticker);
            return Response.ok(result).build();
        }
    }

/*

    @GET
    @Path("/find/bybtwprice/{min}/{max}")
    @Produces(MediaType.APPLICATION_JSON)
    public  Response findByBtwPriceStock(@PathParam("min") Integer min, @PathParam("max") Integer max){

        if(null ==min || null==max) {
            return Response.status(200).entity("The stock name was not provided").build();

        }else {
            List<Stock> result  = repo.findByVlinePriceBetween(min, max);
            return Response.ok(result).build();
        }
    }


    @GET
    @Path("/find/bybtwprice/{min}/{max}")
    @Produces(MediaType.APPLICATION_JSON)
    public  Response findByBtwPriceStock(@PathParam("min") Integer min, @PathParam("max") Integer max){

        if(null ==min || null==max) {
            return Response.status(200).entity("The stock name was not provided").build();

        }else {
            List<Stock> result  = repo.findByVlinePriceBetween(min,max);
            return Response.ok(result).build();
        }
    }
*/







}
