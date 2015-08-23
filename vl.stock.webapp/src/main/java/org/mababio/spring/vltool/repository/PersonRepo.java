package org.mababio.spring.vltool.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.mababio.spring.vltool.domain.*;

public interface PersonRepo extends MongoRepository<Person, Integer> {




}
