package com.francis.byteworkstest.user;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.francis.byteworkstest.ByteworkstestApplication;
import com.francis.byteworkstest.dto.ActivateUserRequest;
import com.francis.byteworkstest.dto.PasswordRestDto;
import com.francis.byteworkstest.dto.ResendUserActivationCodeDto;
import com.francis.byteworkstest.dto.ServerResponse;
import com.francis.byteworkstest.dto.SignInRequest;
import com.francis.byteworkstest.model.User;
import com.francis.byteworkstest.service.UserService;




@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
classes = ByteworkstestApplication.class)
public class UserServiceTest {

	@Autowired
	UserService userService;
	
	@Test
	public void findByPhoneOrEmail() throws Exception {
		User user = userService.findByEmail("reginafrancis83@gmail.com");
		assertEquals(user.getEmail(), "reginafrancis83@gmail.com");
	}
	
	
	@Test
	public void Login() throws Exception {
		
		SignInRequest signInRequest = new SignInRequest();
		signInRequest.setPassword("regina");
		signInRequest.setUsername("reginafrancis83@gmail.com");
		
		ServerResponse response = userService.login(signInRequest);
		assertEquals(response.isSuccess(), true);
	}
	
	@Test
	public void ActivateUser() throws Exception {
		
		ActivateUserRequest request = new ActivateUserRequest();
		request.setActivationCode("n2iwViwR8OLcNL1IFdCRgckcysWBBKLFFWOLUom8"); //Note activation code generate every time you run this test class
		request.setPassword("password");

		ServerResponse response = userService.userActivation(request);
		System.out.println(response.getMessage());
		assertEquals(response.isSuccess(), true);
	}
	
	@Test
	public void PasswordRestRequest() throws Exception {
		
		ResendUserActivationCodeDto request = new ResendUserActivationCodeDto();
		request.setEmail("reginafrancis83@gmail.com");
		
		ServerResponse response = userService.reSendUserPassword(request);
		System.out.println(response.getMessage());
		assertEquals(response.isSuccess(), true);
	}
	
	@Test
	public void ResendUserActivation() throws Exception {
		
		ResendUserActivationCodeDto request = new ResendUserActivationCodeDto();
		request.setEmail("reginafrancis832@gmail.com");
		
		ServerResponse response = userService.reSendUserActivation(request);
		System.out.println(response.getMessage());
		assertEquals(response.isSuccess(), true);
	}
	
	@Test
	public void PasswordReset() throws Exception {
		
		PasswordRestDto request = new PasswordRestDto();
		request.setResetCode("FPChfUHTSmUusLsTSqpuGkRlOiRmlU85jC44EbiN"); //Note password reset code generate every time you run this test class
		request.setPassword("regina");
		
		ServerResponse response = userService.passwordReset(request);
		System.out.println(response.getMessage());
		assertEquals(response.isSuccess(), true);
	}
	
}
