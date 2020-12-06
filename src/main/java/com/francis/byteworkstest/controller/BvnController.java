package com.francis.byteworkstest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.francis.byteworkstest.constant.ServerResponseStatus;
import com.francis.byteworkstest.dto.ResolveBvnDto;
import com.francis.byteworkstest.dto.ServerResponse;
import com.francis.byteworkstest.service.BvnService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/bvn", produces = "application/json")
@Api(tags = "BVN Verification Management", description = "Endpoint")
public class BvnController {

	@Autowired
	BvnService service;

	private HttpHeaders responseHeaders = new HttpHeaders();

	@ApiOperation(value = "Resolve BVN Number number", response = ServerResponse.class)
	@RequestMapping(value = "/{bvn}", method = RequestMethod.GET)
	// @PreAuthorize("hasAuthority('isAdmin') OR hasAuthority('isShareholder')")
	@ResponseBody
	public ResponseEntity<?> resolveBvn(@RequestHeader("Authorization") String authorization,
			@PathVariable("bvn") String bvn) {

		ServerResponse response = new ServerResponse();

		try {
			response = service.resolveBvn(bvn);

		} catch (Exception e) {
			response.setData("An error occured" + e.getMessage());
			response.setMessage("An error occured");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);
			e.printStackTrace();
		}

		return new ResponseEntity<ServerResponse>(response, responseHeaders,
				ServerResponse.getStatus(response.getStatus()));
	}

}
