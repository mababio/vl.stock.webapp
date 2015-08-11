package org.mababio.spring.vltool;


import org.mababio.spring.vltool.domain.Address;
import org.mababio.spring.vltool.domain.Person;
import org.mababio.spring.vltool.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;



//@SpringBootApplication
@EnableMongoRepositories
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages="org.mababio.spring")
public class StockWebAppApplication implements CommandLineRunner  {

	
	
	@Autowired
	PersonRepo repo;

	
	
    public static void main(String[] args) {
        SpringApplication.run(StockWebAppApplication.class, args);
    
   
    
    }

	@Override
	public void run(String... arg0) throws Exception {
		
		
		repo.save(new Person("john", "sam").setAddr(new Address("Manhattan","New York")));
		repo.save(new Person("john", "adam").setAddr(new Address("Manhattan","New York")));
		
		
		
	}
}
