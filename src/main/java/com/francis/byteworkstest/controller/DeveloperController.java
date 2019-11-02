package com.francis.byteworkstest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.francis.byteworkstest.constant.ServerResponseStatus;
import com.francis.byteworkstest.dto.DeveloperDto;
import com.francis.byteworkstest.dto.ServerResponse;
import com.francis.byteworkstest.service.DeveloperService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/*
 * Developer's  account endpoint manager 
 */
@Controller
@RequestMapping(value = "/developer", produces = "application/json")
@Api(tags = "Developer Management", description = "Endpoint")
public class DeveloperController {
	
	
	@Autowired
	DeveloperService developerService;
	
	private HttpHeaders responseHeaders = new HttpHeaders();
	
	
	@ApiOperation(value = "Developers account can be created", response = ServerResponse.class)
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createDeveloper(@RequestHeader("Authorization")  String authorization, @RequestBody DeveloperDto request){
		
		
		ServerResponse response = new ServerResponse();
		
		try {
			
			response = developerService.createDeveloper(request);
		 
		} catch (Exception e) {
			response.setData("An error occured" + e.getMessage());
            response.setMessage("An error occured");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
            
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}
	
	
	
	@ApiOperation(value = "Get all developers", response = ServerResponse.class)
    @RequestMapping( method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getAllOrders(@RequestHeader("Authorization")  String authorization){
		
		ServerResponse response = new ServerResponse();
		
		try {
			
			response = developerService.getAllDevelopers();
		
		} catch (Exception e) {
			response.setData("An error occured in user account verification" + e.getMessage());
			response.setMessage("An error occured in user account verification");
	        response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

	}

}
