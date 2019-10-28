package com.francis.byteworkstest.serviceImpl;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.francis.byteworkstest.constant.ServerResponseStatus;
import com.francis.byteworkstest.dto.DeveloperDto;
import com.francis.byteworkstest.dto.ServerResponse;
import com.francis.byteworkstest.enumType.UserPrivilageType;
import com.francis.byteworkstest.model.Developer;
import com.francis.byteworkstest.model.Order;
import com.francis.byteworkstest.model.Privilege;
import com.francis.byteworkstest.model.User;
import com.francis.byteworkstest.repository.DeveloperRepository;
import com.francis.byteworkstest.repository.PrivilegeRepository;
import com.francis.byteworkstest.repository.UserRepository;
import com.francis.byteworkstest.service.DeveloperService;
import com.francis.byteworkstest.utility.Utility;

@Service
@Transactional
public class DeveloperServiceImpl implements DeveloperService{
	
	
	@Autowired
	DeveloperRepository developerRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PrivilegeRepository privilegeRepo;
	
	Utility utility = new Utility();

	@Override
	public Collection<Developer> findAll() {
		
		try {
			
			return	(Collection<Developer>) developerRepo.findAll();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Developer findById(long id) {

		try {
			
			return developerRepo.findById(id);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Developer findByDeveloperCode(String developerCode) {

		try {
			
			return developerRepo.findByDeveloperCode(developerCode);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ServerResponse createDeveloper(DeveloperDto request) {

		ServerResponse response = new ServerResponse();
		
		Developer developer = null;
		
		String userCode = request.getUserCode() != null ? request.getUserCode() : request.getUserCode();
		String phone = request.getPhone() != null ? request.getPhone() : request.getPhone();
		
       if (userCode == null || userCode.isEmpty()) {
			
			response.setData("");
            response.setMessage("Please enter user code");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
            return response;
		}
		
		if (phone == null || !Utility.isValidPhone(phone)) {
			response.setData("");
            response.setMessage("Please enter valid phone number");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);

            return response;
		}
		
		
		try {
			
			User confirmUser =  userRepo.findByUserCode(userCode);
			
			if (confirmUser == null) {
				response.setData("");
                response.setMessage("User does not exist");
                response.setSuccess(false);
                response.setStatus(ServerResponseStatus.FAILED);

                return response;
			}
			
			Privilege isDeveloper = privilegeRepo.findByName(UserPrivilageType.isDeveloper);
			
			Collection<Privilege> developerPrivileges = new HashSet<>();
			developerPrivileges.add(isDeveloper);
			
			developer = new Developer(); 
			developer.setUser(confirmUser);
			developer.setDeveloperCode("D" + System.currentTimeMillis());
			
			developerRepo.save(developer);
			
			response.setData(developer);
	        response.setMessage("Developer successfully created");
	        response.setSuccess(true);
	        response.setStatus(ServerResponseStatus.OK);

		
		}catch(Exception e) {
			 response.setData("");
	          response.setMessage("Failed to create Developer");
	          response.setSuccess(false);
	          response.setStatus(ServerResponseStatus.FAILED);
	      //    logger.error("An error occured while creating recipient User");
	          e.printStackTrace();	
		}
			
		return response;
	}

	@Override
	public ServerResponse getAllDevelopers() {

		ServerResponse response = new ServerResponse();
		try {
			
			Collection<Developer> developers = findAll();
			
			if (developers.size() < 1) {
				response.setData("");
		        response.setMessage("Developer's list is empty");
		        response.setSuccess(false);
		        response.setStatus(ServerResponseStatus.OK);
		        return response;
			}
			
			response.setData(developers);
	        response.setMessage("Get data successfully");
	        response.setSuccess(true);
	        response.setStatus(ServerResponseStatus.OK);
		} catch (Exception e) {
			response.setData("");
	        response.setMessage("Something went wrong");
	        response.setSuccess(false);
	        response.setStatus(ServerResponseStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
		
	}

}
