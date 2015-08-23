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
import java.util.ArrayList;
import java.util.List;


/**
 * Created by ea on 8/22/15.
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

        if(name.isEmpty() || null ==name) {
        return Response.status(200).entity("The stock name was not provided").build();

        }else {
            List<Stock> result  = repo.findByStkName(name);
            return Response.ok(result).build();
    }








    }




}
