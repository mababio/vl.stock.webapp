package org.mababio.config;


import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.mababio.spring.vltool.repository.PersonRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;



//@SpringBootApplication
@EnableMongoRepositories(basePackages = "org.mababio.spring.vltool.repository")
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages="org.mababio")
public class StockWebAppApplication extends AbstractMongoConfiguration implements CommandLineRunner   {

	@Autowired
	PersonRepo repo;


	/*MongoDB settings*/

	@Override
	protected String getDatabaseName() {
		return "mababio";
	}

	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient("192.168.1.9");
	}


    public static void main(String[] args) {
        SpringApplication.run(StockWebAppApplication.class, args);
    }




	@Override
	public void run(String... arg0) throws Exception {

		//repo.save(new Person("Michael", "Jordan").setAddr(new Address("Brookyln","New York")));
		//repo.save(new Person("john", "adam").setAddr(new Address("Manhattan","New York")));
	}

}
