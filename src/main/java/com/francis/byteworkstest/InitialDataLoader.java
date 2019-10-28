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
		createAdminAccountIfNotFound();
		
		
		hasBeenSetup = true;
	}

	private Privilege createPrivilagesIfNotFound() {

		Privilege privileges;
		
		Privilege isAdmin = privilegeRepository.findByName(UserPrivilageType.isAdmin);
		Privilege isDeveloper = privilegeRepository.findByName(UserPrivilageType.isDeveloper);
		Privilege isVendor = privilegeRepository.findByName(UserPrivilageType.isVendor);
		
		
		if (isAdmin == null) {
			privileges = new Privilege();
			privileges.setName(UserPrivilageType.isAdmin);
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

	private User createAdminAccountIfNotFound() {

		User userAccount = userRepository.findByEmail(appConstants.APP_ADMIN_EMAIL);
		Privilege isAdmin = privilegeRepository.findByName(UserPrivilageType.isAdmin);
		
		
		Collection<Privilege> adminPrivileges = new HashSet<>();
		adminPrivileges.add(isAdmin);
		
		
		logger.info("Starting to create admin account ");

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
		user.setRole(UserRoleType.SYSTEM_ADMIN);
		user.setPrivileges(adminPrivileges);

		logger.info("Admin Account " + user.getEmail());
		
		User findEmail = userRepository.findByEmail(user.getEmail());

		if (findEmail != null) {
			return null;
		}

		userRepository.save(user);

		return null;
	}

}
