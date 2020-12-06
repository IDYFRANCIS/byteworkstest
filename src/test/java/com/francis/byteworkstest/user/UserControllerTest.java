package com.francis.byteworkstest.user;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.francis.byteworkstest.ByteworkstestApplication;
import com.francis.byteworkstest.dto.ActivateUserRequest;
import com.francis.byteworkstest.dto.PasswordRestDto;
import com.francis.byteworkstest.dto.ResendUserActivationCodeDto;
import com.francis.byteworkstest.dto.SignInRequest;
import com.google.gson.Gson;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
classes = ByteworkstestApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	SignInRequest signInRequest;
	
	Gson gson;
	
		//Note: make sure you acquired authorization token from login for proper testing
		String authorization = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsidXNlci13ZWItc2VydmljZSJdLCJ1c2VyX25hbWUiOiJyZWdpbmFmcmFuY2lzODNAZ21haWwuY29tIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImV4cCI6MTU3NTMyMjcxMCwiYXV0aG9yaXRpZXMiOlsiaXNVc2VyIl0sImp0aSI6ImZjODZiNzU4LTg3MTktNGUwOS04ZGExLWY3ZjNlZDUxYjRhYiIsImNsaWVudF9pZCI6InVzZXIifQ.ML6mY6oXqlQ0GDfIKFkR2OmPAYmCGXc7dgOSEFzzr50";

		@Before
		  public void setup() {
			
			signInRequest = new SignInRequest();
			signInRequest.setPassword("regina");
			signInRequest.setUsername("reginafrancis83@gmail.com");
			gson = new Gson();
		  }
		
		@Test
		public void Login() throws Exception {
			
			SignInRequest signInRequest = new SignInRequest();
			signInRequest.setPassword("regina");
			signInRequest.setUsername("reginafrancis83@gmail.com");
			
			String json = gson.toJson(signInRequest);
			
			ResultActions mvcResult = mockMvc.perform(
					MockMvcRequestBuilders.post("/user/login")
					.contentType(MediaType.APPLICATION_JSON)
					.content(json)
						.accept(MediaType.APPLICATION_JSON)
					).andExpect(status().isOk())
					.andExpect(content().json("{\"success\":true,\"message\":\"Login Successful\"}"));
			
			
			System.out.println(gson.toJson(mvcResult.andReturn().getResponse().getContentAsString()));
			
		}
		
		@Test
		public void ActivateUser() throws Exception {
			
			ActivateUserRequest request = new ActivateUserRequest();
			request.setActivationCode("gOCUBlMxDjIDWUHvJ4KXBo4BQeSGT6TtpPr3Ko4i"); //Note activation code generate every time you run this test class
			request.setPassword("password");
			
			String json = gson.toJson(request);
			
			ResultActions mvcResult = mockMvc.perform(
					MockMvcRequestBuilders.post("/user/verification")
					.contentType(MediaType.APPLICATION_JSON)
					.content(json)
						.accept(MediaType.APPLICATION_JSON)
					).andExpect(status().isOk())
					.andExpect(content().json("{\"success\":true}"));
			
			
			System.out.println(gson.toJson(mvcResult.andReturn().getResponse().getContentAsString()));
			
		}
		
		@Test
		public void PasswordRestRequest() throws Exception {
			
			ResendUserActivationCodeDto request = new ResendUserActivationCodeDto();
			request.setEmail("reginafrancis83@gmail.com");
			
			String json = gson.toJson(request);
			
			ResultActions mvcResult = mockMvc.perform(
					MockMvcRequestBuilders.post("/user/passwordrest")
					.contentType(MediaType.APPLICATION_JSON)
					.content(json)
						.accept(MediaType.APPLICATION_JSON)
					).andExpect(status().isOk())
					.andExpect(content().json("{\"success\":true}"));
			
			
			System.out.println(gson.toJson(mvcResult.andReturn().getResponse().getContentAsString()));
			
		}
		
		@Test
		public void ResendUserActivation() throws Exception {
			
			ResendUserActivationCodeDto request = new ResendUserActivationCodeDto();
			request.setEmail("reginafrancis83@gmail.com");
			
			String json = gson.toJson(request);
			
			ResultActions mvcResult = mockMvc.perform(
					MockMvcRequestBuilders.post("/user/getactivation")
					.contentType(MediaType.APPLICATION_JSON)
					.content(json)
						.accept(MediaType.APPLICATION_JSON)
					).andExpect(status().isOk())
					.andExpect(content().json("{\"success\":true}"));
			
			
			System.out.println(gson.toJson(mvcResult.andReturn().getResponse().getContentAsString()));
			
		}
		
		@Test
		public void PasswordReset() throws Exception {
			
			PasswordRestDto request = new PasswordRestDto();
			request.setResetCode("VLHMx1noPbOMTwCrrchtfITIxv0KxcOWCgUsPqnP"); //Note password reset code generate every time you run this test class
			request.setPassword("password");
			
			String json = gson.toJson(request);
			
			ResultActions mvcResult = mockMvc.perform(
					MockMvcRequestBuilders.post("/user/password/change")
					.contentType(MediaType.APPLICATION_JSON)
					.content(json)
						.accept(MediaType.APPLICATION_JSON)
					).andExpect(status().isOk())
					.andExpect(content().json("{\"success\":true}"));
			
			
			System.out.println(gson.toJson(mvcResult.andReturn().getResponse().getContentAsString()));
			
		}
		
}
