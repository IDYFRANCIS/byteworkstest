package com.francis.byteworkstest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.francis.byteworkstest.enumType.UserPrivilageType;
import com.francis.byteworkstest.model.Privilege;



@Repository
public interface PrivilegeRepository extends CrudRepository<Privilege, Long>{

	public Privilege findById(long id);

	public Privilege findByName(UserPrivilageType name);
	
}
