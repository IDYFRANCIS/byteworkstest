package com.francis.byteworkstest.developer;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.francis.byteworkstest.dto.DeveloperDto;
//import com.africaprudential.shareportal.ShareportalApplication;
//import com.africaprudential.shareportal.Dto.ClientDto;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
classes = ByteworkstestApplication.class)
@AutoConfigureMockMvc
public class DeveloperControllerTest {

	@Autowired
	MockMvc mockMvc;
		
	Gson gson;
	

	//Note: make sure you acquired authorization token from login for proper testing
	String authorization = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsidXNlci13ZWItc2VydmljZSJdLCJ1c2VyX25hbWUiOiJyZWdpbmFmcmFuY2lzODNAZ21haWwuY29tIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImV4cCI6MTU4MDQxMjc1OCwiYXV0aG9yaXRpZXMiOlsiaXNVc2VyIl0sImp0aSI6IjU5NTExYTk1LWZjOTItNDI0NS05NjgwLWZlYjVkMzA3MTAzNyIsImNsaWVudF9pZCI6InVzZXIifQ.eL411063lSpDDiGZ6bM5U33GruSrWYIrdp-67hbIv9Y";

	@Before
	  public void setup() {
		
		gson = new Gson();
	  }
	

	@Test
	public void create() throws Exception {
		String userCode = "U1580369858476";
		DeveloperDto request = new DeveloperDto();
		request.getClass().getSuperclass();
		request.setUserCode(userCode);
		String json = gson.toJson(request);
		
		ResultActions mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post("developer/"+userCode)
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", authorization)
				.content(json)
					.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk())
				.andExpect(content().json("{\"success\":true}"));
		
		System.out.println(gson.toJson(mvcResult.andReturn().getResponse().getContentAsString()));
		
	}
	
	@Test
	public void getAllDevelopers() throws Exception{
		
		ResultActions mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get("/developer")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", authorization)
					.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk())
				.andExpect(content().json("{\"success\":true}"));
		
		System.out.println(gson.toJson(mvcResult.andReturn().getResponse().getContentAsString()));
	}
	
	
}
