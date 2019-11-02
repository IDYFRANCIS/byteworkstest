package com.francis.byteworkstest.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.francis.byteworkstest.model.User;


/**
 * User's Repository
 * @author Francis
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	public User findById(long id);

	// find user account by email  	
	public User findByEmail(String email);
		
	// find user account by user code 	
	public User findByUserCode(String userCode);
	
	// find user account by phone Number  	
	public User findByPhone(String phone);
	
	// find user account by phone Number and email	
	public User findByPhoneOrEmail(String phone, String email);
	
	// find user account by phone activationCode  	
	public User findByActivationCode(String activationCode);
	
	@Query("SELECT A FROM User A WHERE A.phone=:phone OR A.phone=:email")
	public User getUserByEmailOrPhone(@Param("phone")String phone, @Param("email")String email);
	
}
