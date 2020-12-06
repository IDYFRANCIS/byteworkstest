package com.francis.byteworkstest.service;

import org.springframework.stereotype.Service;

import com.francis.byteworkstest.dto.ResolveBvnDto;
import com.francis.byteworkstest.dto.ServerResponse;

@Service
public interface BvnService {
	
	ServerResponse resolveBvn(String bvn);

}
