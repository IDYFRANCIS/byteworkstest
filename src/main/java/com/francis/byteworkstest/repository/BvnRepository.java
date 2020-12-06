package com.francis.byteworkstest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.francis.byteworkstest.model.Bvn;

@Repository
public interface BvnRepository extends CrudRepository<Bvn, String>{
	
	Bvn findByBvn(String bvn);

}
