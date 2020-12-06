package com.francis.byteworkstest.developer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.francis.byteworkstest.ByteworkstestApplication;
import com.francis.byteworkstest.dto.DeveloperDto;
import com.francis.byteworkstest.dto.ServerResponse;
import com.francis.byteworkstest.service.DeveloperService;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
classes = ByteworkstestApplication.class)
public class DeveloperServiceTest {

	@Autowired
	DeveloperService service;
	
	@Test
	public void create(){
		String userCode = "U1580369858476";
		DeveloperDto request = new DeveloperDto();
		request.setUserCode(userCode);
		ServerResponse response = service.createDeveloper(request);
		System.out.println(response.getMessage());
		assertEquals(response.isSuccess(), true);
	}
	
	@Test
	public void getAllDevelopers(){
		ServerResponse response = service.getAllDevelopers();
		System.out.println(response.getMessage());
		assertEquals(response.isSuccess(), true);
	}
	
	
}
