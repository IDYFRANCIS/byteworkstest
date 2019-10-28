package com.francis.byteworkstest.serviceImpl;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.francis.byteworkstest.constant.AppConstants;
import com.francis.byteworkstest.constant.ServerResponseStatus;
import com.francis.byteworkstest.dto.ActivateUserRequest;
import com.francis.byteworkstest.dto.PasswordRestDto;
import com.francis.byteworkstest.dto.ResendUserActivationCodeDto;
import com.francis.byteworkstest.dto.ServerResponse;
import com.francis.byteworkstest.dto.SignInRequest;
import com.francis.byteworkstest.dto.SignUpRequest;
import com.francis.byteworkstest.enumType.UserPrivilageType;
import com.francis.byteworkstest.enumType.UserRoleType;
import com.francis.byteworkstest.model.Privilege;
import com.francis.byteworkstest.model.User;
import com.francis.byteworkstest.repository.PrivilegeRepository;
import com.francis.byteworkstest.repository.UserRepository;
import com.francis.byteworkstest.service.UserService;
import com.francis.byteworkstest.utility.Utility;


@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	AppConstants appConstants;
	
	@Autowired
	PrivilegeRepository privilegeRepository;
	
	
	//@Autowired
	//private EmailService emailService;

    Utility utility = new Utility();
	
	 private static Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
	@Override
	public Collection<User> findAll(){
		try {
			return (Collection<User>) userRepository.findAll();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public User findById(long id){
		
		try {
			return userRepository.findById(id);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
		
	}
	
	@Override
	public User findByEmail(String email){
		try {
			return userRepository.findByEmail(email);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return null;
		
	}
	
	@Override
	public User findByPhone(String phone){
		try {
			return userRepository.findByPhone(phone);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return null;
		
	}
	
	@Override
	public User findByPhoneOrEmail(String emailOrphone){
		try {
			return userRepository.findByPhoneOrEmail(emailOrphone, emailOrphone);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public ServerResponse create(SignUpRequest request){
		ServerResponse response = new ServerResponse();
		
		User User = null;
		
		String email = request.getEmail() != null ? request.getEmail() : request.getEmail();
		String phone = request.getPhone() != null ? request.getPhone() : request.getPhone();;
		String firstname = request.getFirstName() != null ? request.getFirstName() : request.getFirstName();
		String lastname = request.getLastName() != null ? request.getLastName() : request.getLastName();
		String middlename = request.getMiddleName() != null ? request.getMiddleName() : request.getMiddleName();
		String dob = request.getDateOfBirth() != null ? request.getDateOfBirth() : request.getDateOfBirth();
		String gender = request.getGender() != null ? request.getGender() : request.getGender();
		String address = request.getAddress() != null ? request.getAddress() : request.getAddress();

		if (email == null || !Utility.isValidEmail(email)) {
			
			response.setData("");
            response.setMessage("Please enter valid email address");
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
		
		
		if (firstname == null || firstname.isEmpty()) {
			response.setData("");
            response.setMessage("Please enter firstname");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);

            return response;
		}
		
		if (lastname == null || lastname.isEmpty()) {
			response.setData("");
            response.setMessage("Please enter lastname");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);

            return response;
		}
		
		
		if (middlename == null || middlename.isEmpty()) {
			response.setData("");
            response.setMessage("Please enter middlename");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);

            return response;
		}
		
		if (gender == null || gender.isEmpty()) {
			response.setData("");
            response.setMessage("Please enter gender");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);

            return response;
		}
		
		if (dob == null || dob.isEmpty()) {
			response.setData("");
            response.setMessage("Please enter date of birth");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);

            return response;
		}
		
		if (address == null || address.isEmpty()) {
			response.setData("");
            response.setMessage("Please enter address");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);

            return response;
		}
		
		
		try {
			
			User requestUser = userRepository.findByPhoneOrEmail(phone, email);
			
			if (requestUser != null) {
				response.setData("");
                response.setMessage("User email or number already exist");
                response.setSuccess(false);
                response.setStatus(ServerResponseStatus.FAILED);

                return response;
			}
			
			
			Privilege isUser = privilegeRepository.findByName(UserPrivilageType.isUser);
			
			Collection<Privilege> userPrivileges = new HashSet<>();
			userPrivileges.add(isUser);
			
			
			User = new User();
			User.setPrivileges(userPrivileges);
			String activationCode =  Utility.generateRandomString(40);

			User.setRole(UserRoleType.USER);
			User.setEmail(email);
			User.setFirstName(firstname);
			User.setLastName(lastname);
			User.setAddress(address);
			User.setDateOfBirth(dob);
			User.setMiddleName(middlename);
			User.setGender(gender);
			User.setPhone(phone);
			User.setDateCreated(new Date());
			User.setActive(false);
			User.setActivationCode(activationCode);
			User.setUserCode("U" + System.currentTimeMillis());
		
			entityManager.persist(User);
			
			response.setData(User);
	        response.setMessage("User successfully created");
	        response.setSuccess(true);
	        response.setStatus(ServerResponseStatus.OK);

		} catch (Exception e) {
		  response.setData("");
          response.setMessage("Failed to create User");
          response.setSuccess(false);
          response.setStatus(ServerResponseStatus.FAILED);

          logger.error("An error occured while creating recipient User");
          e.printStackTrace();
		}
		return response;
		
	}
	
	@Override
	public ServerResponse userActivation(ActivateUserRequest request){
		ServerResponse response = new ServerResponse();
		
		try {
			
			String activationCode = request.getActivationCode() != null ? request.getActivationCode() : request.getActivationCode();
			String password = request.getPassword() != null ? request.getPassword() : request.getPassword();
			
			User UserCode = userRepository.findByActivationCode(activationCode);

			if (UserCode == null) {
				
				response.setData("");
		        response.setMessage("Invalid activation Code");
		        response.setSuccess(false);
		        response.setStatus(ServerResponseStatus.FAILED);
				
				return response;
			}

			User User = entityManager.find(User.class, UserCode.getId());
			User.setPassword(passwordEncoder.encode(password));
			User.setActive(true);
			User.setActivationCode(null);
						
			
	        response.setData(User);
            response.setMessage("User successfully created");
            response.setSuccess(true);
            response.setStatus(ServerResponseStatus.OK);
            
			
			response.setData("User successfully activated");
	        response.setMessage("User successfully activated");
	        response.setSuccess(true);
	        response.setStatus(ServerResponseStatus.OK);
			
		} catch (Exception e) {
			
			response.setData("");
	        response.setMessage("Failed to create user User");
	        response.setSuccess(false);
	        response.setStatus(ServerResponseStatus.FAILED);
	          
			e.printStackTrace();
		}
		return response;
	}
	
	@Override
	public ServerResponse reSendUserActivation(ResendUserActivationCodeDto email){
		ServerResponse response = new ServerResponse();
		
		try {
			
			User UserCode = findByPhoneOrEmail(email.getEmail());
			
			if (UserCode == null) {
				
				response.setData("");
		        response.setMessage("User not found");
		        response.setSuccess(false);
		        response.setStatus(ServerResponseStatus.FAILED);
				
				return response;
			}
		
			String activationCode = Utility.generateRandomString(40);

			User User = entityManager.find(User.class, UserCode.getId());
			User.setActivationCode(activationCode);
			
			response.setData("");
	        response.setMessage("Activation code sent successfully");
	        response.setSuccess(true);
	        response.setStatus(ServerResponseStatus.OK);
			
		} catch (Exception e) {
			
			response.setData("");
	        response.setMessage("Failed to create user User");
	        response.setSuccess(false);
	        response.setStatus(ServerResponseStatus.FAILED);
	          
			e.printStackTrace();
		}
		return response;
	}
	
	@Override
	public ServerResponse reSendUserPassword(ResendUserActivationCodeDto email){
		ServerResponse response = new ServerResponse();
		
		try {

			User UserCode = findByPhoneOrEmail(email.getEmail());
			
			if (UserCode == null) {
				
				response.setData("");
		        response.setMessage("User not found");
		        response.setSuccess(false);
		        response.setStatus(ServerResponseStatus.FAILED);
				
				return response;
			}
	        
			String passwordResetCode =  Utility.generateRandomString(40);

			User User = entityManager.find(User.class, UserCode.getId());
			User.setActivationCode(passwordResetCode);
			
			response.setData("password reset code sent successfully");
	        response.setMessage("password reset code sent successfully");
	        response.setSuccess(true);
	        response.setStatus(ServerResponseStatus.OK);
			
		} catch (Exception e) {
			
			response.setData("");
	        response.setMessage("Failed to resend User Password");
	        response.setSuccess(false);
	        response.setStatus(ServerResponseStatus.FAILED);
	          
			e.printStackTrace();
		}
		return response;
	}
	
	@Override
	public ServerResponse passwordReset(PasswordRestDto request){
		ServerResponse response = new ServerResponse();
		
		try {
			String passwordResetCode = request.getResetCode() != null ? request.getResetCode() : request.getResetCode();
			String password = request.getPassword() != null ? request.getPassword() : request.getPassword();

			User UserCode = userRepository.findByActivationCode(passwordResetCode);

			if (UserCode == null) {
				
				response.setData("");
		        response.setMessage("Invalid activation Code");
		        response.setSuccess(false);
		        response.setStatus(ServerResponseStatus.FAILED);
				
				return response;
			}
			
			User User = entityManager.find(User.class, UserCode.getId());
			User.setPassword(passwordEncoder.encode(password));
			User.setActive(true);
			User.setActivationCode(null);
			
			response.setData("");
	        response.setMessage("User password successfully changed");
	        response.setSuccess(true);
	        response.setStatus(ServerResponseStatus.OK);
			
		} catch (Exception e) {
			
			response.setData("");
	        response.setMessage("Failed to reset User Password");
	        response.setSuccess(false);
	        response.setStatus(ServerResponseStatus.FAILED);
	          
			e.printStackTrace();
		}
		return response;
	}
	
	@Override
	public ServerResponse login(SignInRequest request){
		
		ServerResponse response = new ServerResponse();
		try {
			
			logger.info(request.getUsername());
			
			//convert client id and client secret to base64 token 
			String authorization = Utility.getCredentials(appConstants.CLIENT_ID, appConstants.CLIENT_SECRET);
			logger.info(authorization);
			request.setGrant_type(appConstants.GRANT_TYPE);
			
			System.out.println("this is to check for request" + request);

			
			//send login request
			response = Utility.loginHttpRequest2( appConstants.APP_LOGIN_URL, request, authorization);
			
		} catch (Exception e) {
			response.setData("Something went wrong !!!");
 			response.setMessage("User authentication failed");
 			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.INTERNAL_SERVER_ERROR);
			
			return response;
		}
		
		return response;
	}


	@Override
	public ServerResponse getAllUsers() {
		ServerResponse response = new ServerResponse();
		try {
			
			Collection<User> Users = findAll();
			
			if (Users.size() < 1) {
				response.setData(Users);
		        response.setMessage("User list is empty");
		        response.setSuccess(false);
		        response.setStatus(ServerResponseStatus.OK);
		        return response;
			}
			
			response.setData(Users);
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


	@Override
	public ServerResponse getUserByUsername(String userCode) {
		ServerResponse response = new ServerResponse();
		
		if (userCode == null || userCode.isEmpty()) {
			response.setData("");
	        response.setMessage("User list is empty");
	        response.setSuccess(false);
	        response.setStatus(ServerResponseStatus.FAILED);
	        return response;
		}
		
		try {
			
			User User = userRepository.findByUserCode(userCode);
			
			if (User == null) {
				response.setData("");
		        response.setMessage("User not found");
		        response.setSuccess(false);
		        response.setStatus(ServerResponseStatus.FAILED);
		        return response;
			}
			
			response.setData(User);
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


	
//	@Override
//	public ServerResponse sendMail(MailDto request) {
//		ServerResponse response = new ServerResponse();
//		
//		if (request.getEmailAddress() == null || request.getEmailAddress().isEmpty()) {
//			response.setData("");
//	        response.setMessage("Please enter email address");
//	        response.setSuccess(false);
//	        response.setStatus(ServerResponseStatus.FAILED);
//	        return response;
//		}
//		
//		if (request.getSubject() == null || request.getSubject().isEmpty()) {
//			response.setData("");
//	        response.setMessage("Please enter mail subject");
//	        response.setSuccess(false);
//	        response.setStatus(ServerResponseStatus.FAILED);
//	        return response;
//		}
//		
//		if (request.getSalutation() == null || request.getSalutation().isEmpty()) {
//			response.setData("");
//	        response.setMessage("Please enter mail salutation");
//	        response.setSuccess(false);
//	        response.setStatus(ServerResponseStatus.FAILED);
//	        return response;
//		}
//		
//		if (request.getMessageBody() == null || request.getMessageBody().isEmpty()) {
//			response.setData("");
//	        response.setMessage("Please enter mail body");
//	        response.setSuccess(false);
//	        response.setStatus(ServerResponseStatus.FAILED);
//	        return response;
//		}
//		
//		try {
//			
//			Mail mail = new Mail();
//            mail.setTo(request.getEmailAddress());
//            mail.setFrom("no-reply@optimumedu.com");
//            mail.setSubject(request.getSubject());
//
//            Map<String, Object> model = new HashMap<String, Object>();
//            model.put("salutation", request.getSalutation());
//            model.put("messageBody", request.getMessageBody());
//            model.put("link", request.getLink());
//            mail.setModel(model);
//            mail.setTemplate("email_template.ftl");
//            emailService.sendSimpleMessage(mail);
//            
//            response.setData("Mail sent");
//	        response.setMessage("Mail sent");
//	        response.setSuccess(true);
//	        response.setStatus(ServerResponseStatus.OK);
//	        
//		} catch (Exception e) {
//			response.setData("");
//	        response.setMessage("Something went wrong");
//	        response.setSuccess(false);
//	        response.setStatus(ServerResponseStatus.INTERNAL_SERVER_ERROR);
//	        e.printStackTrace();
//	    	return response;
//		}
//		return response;
//	}
	
	
	
}
