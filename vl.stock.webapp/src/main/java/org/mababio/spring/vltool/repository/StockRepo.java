package org.mababio.spring.vltool.repository;

import org.mababio.spring.vltool.domain.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StockRepo extends MongoRepository<Stock, String> {

}
