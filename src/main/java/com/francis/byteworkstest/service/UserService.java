package com.francis.byteworkstest.service;

import java.util.Collection;

import javax.mail.Address;

import org.springframework.stereotype.Service;

import com.francis.byteworkstest.dto.ActivateUserRequest;
import com.francis.byteworkstest.dto.PasswordRestDto;
import com.francis.byteworkstest.dto.ResendUserActivationCodeDto;
import com.francis.byteworkstest.dto.ServerResponse;
import com.francis.byteworkstest.dto.SignInRequest;
import com.francis.byteworkstest.dto.SignUpRequest;
import com.francis.byteworkstest.model.User;


@Service
public interface UserService {

	public Collection<User> findAll();
		
	public User findById(long id);
	
	public User findByEmail(String email);
	
	public User findByPhone(String phone);
	
	public User findByPhoneOrEmail(String emailOrphone);
	
	public ServerResponse create(SignUpRequest request);
	
	public ServerResponse userActivation(ActivateUserRequest request);
	
	public ServerResponse reSendUserActivation(ResendUserActivationCodeDto email);
	
	public ServerResponse reSendUserPassword(ResendUserActivationCodeDto email);
	
	public ServerResponse passwordReset(PasswordRestDto request);
	
	public ServerResponse login(SignInRequest request);
	
	public ServerResponse getAllUsers();
	
	public ServerResponse getUserByUsername(String username);
	
	//public ServerResponse updateAddress(Address address);
	
	//public ServerResponse deleteAddress(long id);
	
	//ServerResponse sendMail(MailDto request);

	
}
