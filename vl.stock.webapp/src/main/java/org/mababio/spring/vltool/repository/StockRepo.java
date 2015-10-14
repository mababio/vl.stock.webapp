package org.mababio.spring.vltool.repository;

import org.mababio.spring.vltool.domain.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StockRepo extends MongoRepository<Stock, String> {



      List<Stock> findByStkName(String name);
      List<Stock> findByTicker(String ticker);
      List<Stock> findByVlinePriceBetween(Integer min, Integer max);

}
