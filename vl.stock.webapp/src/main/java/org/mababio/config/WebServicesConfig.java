package org.mababio.config;


import org.glassfish.jersey.server.ResourceConfig;
import org.mababio.webservices.StockCrud;
import org.springframework.context.annotation.Configuration;

/*
*
 * Created by ea on 8/22/15.

*/

@Configuration
public class WebServicesConfig extends ResourceConfig {

public WebServicesConfig(){

    register(StockCrud.class);

}

}
