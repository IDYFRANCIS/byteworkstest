package com.francis.byteworkstest;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.francis.byteworkstest.constant.AppConstants;
import com.francis.byteworkstest.enumType.UserPrivilageType;
import com.francis.byteworkstest.enumType.UserRoleType;
import com.francis.byteworkstest.model.Privilege;
import com.francis.byteworkstest.model.User;
import com.francis.byteworkstest.repository.PrivilegeRepository;
import com.francis.byteworkstest.repository.UserRepository;


/*
 * Auto create seed data
 */

@Component
@Transactional
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private AppConstants appConstants;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	private boolean hasBeenSetup;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	private static Logger logger = LogManager.getLogger(InitialDataLoader.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (hasBeenSetup) {
			return;
		}

		createPrivilagesIfNotFound();
		createVendorAccountIfNotFound();
		
		
		hasBeenSetup = true;
	}

	//Create user privileges 
	private Privilege createPrivilagesIfNotFound() {

		Privilege privileges;
		
		//find privilege if not exists
		//if not exist save
		Privilege isUser = privilegeRepository.findByName(UserPrivilageType.isUser);
		Privilege isDeveloper = privilegeRepository.findByName(UserPrivilageType.isDeveloper);
		Privilege isVendor = privilegeRepository.findByName(UserPrivilageType.isVendor);
		
		
		if (isUser == null) {
			privileges = new Privilege();
			privileges.setName(UserPrivilageType.isUser);
			privilegeRepository.save(privileges);
		}

		if (isDeveloper == null) {
			privileges = new Privilege();
			privileges.setName(UserPrivilageType.isDeveloper);
			privilegeRepository.save(privileges);
		}
		if (isVendor == null) {
			privileges = new Privilege();
			privileges.setName(UserPrivilageType.isVendor);
			privilegeRepository.save(privileges);
		}

		return null;
	}

	
	/*
	 * Create  Food Vendor who is also a supper admin user
	 * Find food vendor if not not exists 
	 * save if not exists
	 */
	
	private User createVendorAccountIfNotFound() {

		User userAccount = userRepository.findByEmail(appConstants.APP_ADMIN_EMAIL);
		Privilege isVendor = privilegeRepository.findByName(UserPrivilageType.isVendor);
		
		
		Collection<Privilege> vendorPrivileges = new HashSet<>();
		vendorPrivileges.add(isVendor);
		
		
		logger.info("Starting to create vendor account ");

		if (userAccount != null) {
			return null;
		}

		User user = new User();
		user.setActive(true);
		user.setEmail(appConstants.APP_ADMIN_EMAIL);
		user.setPhone(appConstants.APP_DEFAULT_ADMIN_PHONE);
		user.setFirstName(appConstants.APP_DEFAULT_ADMIN_NAME);
		user.setLastName(appConstants.APP_DEFAULT_ADMIN_NAME);
		user.setPassword(passwordEncoder.encode(appConstants.APP_ADMIN_PASSWORD));
		user.setRole(UserRoleType.VENDOR);
		user.setPrivileges(vendorPrivileges);

		logger.info("Vendor Account " + user.getEmail());
		
		User findEmail = userRepository.findByEmail(user.getEmail());

		if (findEmail != null) {
			return null;
		}

		userRepository.save(user);

		return null;
	}

}
