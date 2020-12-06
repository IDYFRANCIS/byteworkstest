package com.francis.byteworkstest.serviceImpl;

import java.net.HttpURLConnection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.francis.byteworkstest.constant.AppConstants;
import com.francis.byteworkstest.constant.ServerResponseStatus;
import com.francis.byteworkstest.dto.ApiResponseDto;
import com.francis.byteworkstest.dto.BvnVerificationResponse;
import com.francis.byteworkstest.dto.BvnVerificationResponseData;
import com.francis.byteworkstest.dto.ErrorResponseDto;
import com.francis.byteworkstest.dto.ResolveBvnDto;
import com.francis.byteworkstest.dto.ServerResponse;
import com.francis.byteworkstest.model.Bvn;
import com.francis.byteworkstest.model.User;
import com.francis.byteworkstest.repository.BvnRepository;
import com.francis.byteworkstest.repository.UserRepository;
import com.francis.byteworkstest.service.BvnService;
import com.francis.byteworkstest.utility.Utility;
import com.google.gson.Gson;



@Transactional
@Service
public class BvnServiceImpl implements BvnService{
	
	
	@Autowired
	AppConstants appConstants;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	BvnRepository bvnRepo;
	

	@Override
	public ServerResponse resolveBvn(String bvn) {
		
		
		 ServerResponse response = new ServerResponse();
			
			//Validate inputs
//			if (userCode == null || userCode.isEmpty()) {
//				response.setData("");
//	            response.setMessage("Please provide userCode");
//	            response.setSuccess(false);
//	            response.setStatus(ServerResponseStatus.FAILED);
//
//	            return response;
//			}
			
			
			//Validate inputs
			if (bvn == null || bvn.isEmpty()) {
				response.setData("");
	            response.setMessage("Please provide BVN Number");
	            response.setSuccess(false);
	            response.setStatus(ServerResponseStatus.FAILED);

	            return response;
			}
			
			
			try {
				
				//Validate user
//				User user = userRepo.findByUserCode(userCode);
//				if (user == null) {
//					response.setData("");
//		            response.setMessage("User not found");
//		            response.setSuccess(false);
//		            response.setStatus(ServerResponseStatus.FAILED);
//
//		            return response;
//				}
				
				
				
				
				ApiResponseDto api = new ApiResponseDto();
				Gson gson = new Gson();
	        
				//Pay using paystack gateway
	            api = Utility.httpGetRequest(appConstants.PAYSTACK_BVN_URL + "/bank/resolve_bvn/" + bvn, appConstants.PAYSTACK_AUTH_KEY);
	            
	            if (api.getStatus() == HttpURLConnection.HTTP_OK) {
	            	
	            BvnVerificationResponse	bvnResponse = gson.fromJson(api.getResponse(), BvnVerificationResponse.class);
	            
	            if(bvnResponse != null && bvnResponse.getData().getBvn().equals(bvn));
	            
	            Bvn bvnRep = new Bvn();
	            
	            bvnRep.setBvn(bvnResponse.getData().getBvn());
	            bvnRep.setFirst_name(bvnResponse.getData().getFirst_name());
	            bvnRep.setLast_name(bvnResponse.getData().getLast_name());
	            bvnRep.setDob(bvnResponse.getData().getDob());
	            bvnRep.setMobile(bvnResponse.getData().getMobile());
	            
//	            BvnVerificationResponseData respondBvn = new BvnVerificationResponseData();
//	            respondBvn.setBvn(bvnResponse.getData().getBvn());
//	            respondBvn.setFirst_name(bvnResponse.getData().getFirst_name());
//	            respondBvn.setLast_name(bvnResponse.getData().getLast_name());
//	            respondBvn.setDob(bvnResponse.getData().getDob());
//	            respondBvn.setMobile(bvnResponse.getData().getMobile());
//	            
	            bvnRepo.save(bvnRep);
	            
	            response.setSuccess(bvnResponse.isStatus());
    			response.setMessage("BVN Matches");
    			response.setData(bvnRep);
    			response.setStatus(ServerResponseStatus.OK);
	            	
	            }
	            
	            
	            else {
	    			
	                ErrorResponseDto errorResponseDto = gson.fromJson(api.getResponse(), ErrorResponseDto.class);
	    			
	    			response.setSuccess(errorResponseDto.isStatus());
	    			response.setMessage(errorResponseDto.getData().getMessage());
	    			response.setData(errorResponseDto.getData());
	    			response.setStatus(ServerResponseStatus.FAILED);
	            	
	            }

				
				
			} catch (Exception e) {
				response.setData("");
		        response.setMessage("Something went wrong");
		        response.setSuccess(false);
		        response.setStatus(ServerResponseStatus.INTERNAL_SERVER_ERROR);
		        e.printStackTrace();
			}
			return response;
	
	}
	
}
