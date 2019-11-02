package com.francis.byteworkstest.serviceImpl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.francis.byteworkstest.constant.ServerResponseStatus;
import com.francis.byteworkstest.dto.DeveloperDto;
import com.francis.byteworkstest.dto.DeveloperResponseDto;
import com.francis.byteworkstest.dto.ServerResponse;
import com.francis.byteworkstest.enumType.UserPrivilegeType;
import com.francis.byteworkstest.enumType.UserRoleType;
import com.francis.byteworkstest.mail.EmailService;
import com.francis.byteworkstest.mail.Mail;
import com.francis.byteworkstest.model.Developer;
import com.francis.byteworkstest.model.Privilege;
import com.francis.byteworkstest.model.User;
import com.francis.byteworkstest.repository.DeveloperRepository;
import com.francis.byteworkstest.repository.PrivilegeRepository;
import com.francis.byteworkstest.repository.UserRepository;
import com.francis.byteworkstest.service.DeveloperService;
import com.francis.byteworkstest.utility.Utility;



/**
 * Developer account implementation
 * @author Francis
 *
 */
@Service
@Transactional
public class DeveloperServiceImpl implements DeveloperService{
	
	
	@Autowired
	DeveloperRepository developerRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PrivilegeRepository privilegeRepo;
	
	@Autowired
	private EmailService emailService;
	
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

	//Creating developer's account
	@Override
	public ServerResponse createDeveloper(DeveloperDto request) {

		ServerResponse response = new ServerResponse();
		
		Developer developer = null;
		
		String userCode = request.getUserCode() != null ? request.getUserCode() : request.getUserCode();
		
		//Validating inputs from request
       if (userCode == null || userCode.isEmpty()) {
			
			response.setData("");
            response.setMessage("Please enter user code");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
            return response;
		}
		
		
		try {
			// Checking to ensure that the user exist in the system before creating user as a developer
			User confirmUser =  userRepo.findByUserCode(userCode);
			
			if (confirmUser == null) {
				response.setData("");
                response.setMessage("User does not exist");
                response.setSuccess(false);
                response.setStatus(ServerResponseStatus.FAILED);

                return response;
			}
			
			//Assigning user the privileges of a developer
			Privilege isDeveloper = privilegeRepo.findByName(UserPrivilegeType.isDeveloper);
		
			
			Collection<Privilege> developerPrivileges = new HashSet<>();
			developerPrivileges.add(isDeveloper);
			
			//Developer created
			developer = new Developer(); 
			developer.setUser(confirmUser);
			
			String developerCode = "DEV" + System.currentTimeMillis();

			
			//Developer code generated for developer
			developer.setDeveloperCode(developerCode);
			
			
			//send mail notification on account creation
			Mail mail = new Mail();
            mail.setTo(developer.getUser().getEmail());
            mail.setFrom("foodvendor@byteworks.com");
            mail.setSubject("Developer Account Creation");

            Map<String, Object> model = new HashMap<String, Object>();
             {
	            model.put("salutation", "Dear " + developer.getUser().getFirstName());

			}
            model.put("message", "Welcome to ByteWorks Food Ordering App and thank you for creating a Developer account, you can now place your orders for food on the system using the developer code assigned to you. <b>" + developerCode + "</b>");
            mail.setModel(model);
            mail.setTemplate("verify.ftl");
            
            emailService.sendSimpleMessage(mail);
			
			//Developer saved to database
			developerRepo.save(developer);
			
			DeveloperResponseDto dto = new DeveloperResponseDto();
			dto.setDeveloperCode(developerCode);
			dto.setDeveloperFristName(developer.getUser().getFirstName());
			dto.setDeveloperLastName(developer.getUser().getLastName());
			dto.setDeveloperPhone(developer.getUser().getPhone());
			dto.setDeveloperEmail(developer.getUser().getEmail());
			dto.setDeveloperGender(developer.getUser().getGender());
			dto.setAddress(developer.getUser().getAddress());
			dto.setUserRoleType(UserRoleType.DEVELOPER);
			
			response.setData(dto);
	        response.setMessage("Developer successfully created");
	        response.setSuccess(true);
	        response.setStatus(ServerResponseStatus.OK);

		
		}catch(Exception e) {
			 response.setData("");
	          response.setMessage("Failed to create Developer");
	          response.setSuccess(false);
	          response.setStatus(ServerResponseStatus.FAILED);
	          e.printStackTrace();	
		}
			
		return response;
	}

	
	/**
	 * Fetching all developers existing on the system
	 */
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
