package com.francis.byteworkstest.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.francis.byteworkstest.constant.ServerResponseStatus;
import com.francis.byteworkstest.dto.ActivateUserRequest;
import com.francis.byteworkstest.dto.PasswordRestDto;
import com.francis.byteworkstest.dto.ResendUserActivationCodeDto;
import com.francis.byteworkstest.dto.ServerResponse;
import com.francis.byteworkstest.dto.SignInRequest;
import com.francis.byteworkstest.dto.SignUpRequest;
import com.francis.byteworkstest.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/*
 * User's account endpoint manager 
 */
@Controller
@RequestMapping(value = "/user", produces = "application/json")
@Api(tags = "User Account Management", description = "Endpoint")
//@PreAuthorize("hasAuthourity('admin') OR hasAuthority('super_admin')")
public class UserController {

	@Autowired
	UserService userService;

	@Resource(name = "tokenServices")
	ConsumerTokenServices tokenServices;

	private HttpHeaders responseHeaders = new HttpHeaders();

	private static Logger logger = LogManager.getLogger(UserController.class);

	/*********************************************************************************************************************
	 * 
	 * ACCOUNT CREATION SERVICE
	 * 
	 **********************************************************************************************************************/

	@ApiOperation(value = "Register a user account", response = ServerResponse.class)
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	// @PreAuthorize("hasAuthourity('admin') OR hasAuthority('super_admin')")
	public ResponseEntity<?> create(@RequestBody SignUpRequest request) {

		logger.info("Starting to create user account at controller");

		ServerResponse response = new ServerResponse();

		logger.info(request.getEmail() + " role " + request.getPhone());
		try {
			response = userService.create(request);

		} catch (Exception e) {
			response.setData("An error occured" + e.getMessage());
			response.setMessage("An error occured");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);

		}

		return new ResponseEntity<ServerResponse>(response, responseHeaders,
				ServerResponse.getStatus(response.getStatus()));
	}

	@ApiOperation(value = "Verify and Activate user account", response = ServerResponse.class)
	@RequestMapping(value = "verification/{activationCode}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> ActivateUser(@RequestBody ActivateUserRequest request) {

		ServerResponse response = new ServerResponse();

		try {

			response = userService.userActivation(request);

		} catch (Exception e) {
			response.setData("An error occured" + e.getMessage());
			response.setMessage("An error occured");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);

		}

		return new ResponseEntity<ServerResponse>(response, responseHeaders,
				ServerResponse.getStatus(response.getStatus()));
	}

	@ApiOperation(value = "Resend user Activation code", response = ServerResponse.class)
	@RequestMapping(value = "getactivation", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> ResendUserActivation(@RequestBody ResendUserActivationCodeDto email) {

		ServerResponse response = new ServerResponse();

		try {

			response = userService.reSendUserActivation(email);

		} catch (Exception e) {
			response.setData("An error occured" + e.getMessage());
			response.setMessage("An error occured");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);

		}

		return new ResponseEntity<ServerResponse>(response, responseHeaders,
				ServerResponse.getStatus(response.getStatus()));
	}

	@ApiOperation(value = "Send password recovery code to the user", response = ServerResponse.class)
	@RequestMapping(value = "passwordrest", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> PasswordRestRequest(@RequestBody ResendUserActivationCodeDto email) {

		ServerResponse response = new ServerResponse();

		try {

			response = userService.reSendUserPassword(email);

		} catch (Exception e) {
			response.setData("An error occured" + e.getMessage());
			response.setMessage("An error occured");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);

		}

		return new ResponseEntity<ServerResponse>(response, responseHeaders,
				ServerResponse.getStatus(response.getStatus()));
	}

	@ApiOperation(value = "Change user password", response = ServerResponse.class)
	@RequestMapping(value = "password/change", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> PasswordReset(@RequestBody PasswordRestDto request) {

		ServerResponse response = new ServerResponse();

		try {

			response = userService.passwordReset(request);

		} catch (Exception e) {
			response.setData("");
			response.setMessage("An error occured");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);

		}

		return new ResponseEntity<ServerResponse>(response, responseHeaders,
				ServerResponse.getStatus(response.getStatus()));
	}

	@ApiOperation(value = "Login user account", response = ServerResponse.class)
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> Login(@RequestBody SignInRequest request) {

		ServerResponse response = new ServerResponse();

		try {

			response = userService.login(request);
			System.out.println("this is to confirm request" + request);

		} catch (Exception e) {
			response.setData("An error occured in user account verification" + e.getMessage());
			response.setMessage("An error occured in user account verification");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);
		}

		return new ResponseEntity<ServerResponse>(response, responseHeaders,
				ServerResponse.getStatus(response.getStatus()));

	}

	@ApiOperation(value = "Get all users", response = ServerResponse.class)
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getAllUsers(@RequestHeader("Authorization") String authorization) {

		ServerResponse response = new ServerResponse();

		try {

			response = userService.getAllUsers();

		} catch (Exception e) {
			response.setData("An error occured in user account verification" + e.getMessage());
			response.setMessage("An error occured in user account verification");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);
		}

		return new ResponseEntity<ServerResponse>(response, responseHeaders,
				ServerResponse.getStatus(response.getStatus()));

	}

	// @Autowired
	// TokenStore tokenStore;
	//
	// @Autowired
	// JwtTokenStore jwtTokenStore;
	//
	// @RequestMapping(method = RequestMethod.POST, value = "/tokens")
	// @ResponseBody
	// public List<String> getTokens() {
	// List<String> tokenValues = new ArrayList<String>();
	// Collection<OAuth2AccessToken> tokens =
	// jwtTokenStore.findTokensByClientId("user");
	// if (tokens!=null){
	// for (OAuth2AccessToken token:tokens){
	// tokenValues.add(token.getValue());
	// }
	// }
	// return tokenValues;
	// }
	//
	//
	// @RequestMapping(method = RequestMethod.POST, value = "/logout/{tokenId:.*}")
	// @ResponseBody
	// public String revokeToken(@PathVariable String tokenId) {
	// System.out.println(tokenId);
	// OAuth2Authentication authentication =
	// jwtTokenStore.readAuthentication(tokenId);
	//
	// if (authentication == null) {
	// System.out.println("is null");
	// throw new InvalidTokenException("Invalid access token: " + tokenId);
	// }
	// OAuth2Request clientAuth = authentication.getOAuth2Request();
	// if (clientAuth == null) {
	// System.out.println("Invalid access token");
	// throw new InvalidTokenException("Invalid access token (no client id): " +
	// tokenId);
	// }
	// Collection<OAuth2AccessToken> accessTokens =
	// jwtTokenStore.findTokensByClientIdAndUserName("user",
	// "admin@bizzdeskgroup.com");
	//
	// if (accessTokens == null) {
	// System.out.println("Invalid accessToken");
	// throw new InvalidTokenException("accessToken (no client id): " + tokenId);
	// }
	//
	// for(OAuth2AccessToken accessToken : accessTokens){
	// System.out.println("Invalid accessToken" + accessToken.getTokenType());
	// jwtTokenStore.removeAccessToken(accessToken);
	// }
	//
	//
	// System.out.println("not null " + clientAuth.getClientId());
	// return clientAuth.getClientId();
	// }
	//
	// @Autowired
	// private DefaultTokenServices tokenServicess;
	// @ResponseStatus(HttpStatus.OK)
	// @RequestMapping(method = RequestMethod.DELETE, value = "/revoke")
	// public void revokeToken2(@RequestHeader("Authorization") String
	// authorization) {
	// String tokenId = authorization.replace("Bearer", "").trim();
	// OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenId);
	// tokenStore.removeAccessToken(accessToken);
	// boolean r = tokenServices.revokeToken(tokenId);
	// System.out.println(r);
	//
	// }
}
