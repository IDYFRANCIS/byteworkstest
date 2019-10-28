package com.francis.byteworkstest.security;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.francis.byteworkstest.service.UserService;


@Configuration
public class AuthorizationTokenEnhancer implements TokenEnhancer {

    @Autowired
    private UserService accountService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        com.francis.byteworkstest.model.User account = accountService.findByPhoneOrEmail(user.getUsername());
                
        final Map<String, Object> additionalInfo = new HashMap<String, Object>();
        additionalInfo.put("user", account);

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

        return accessToken;
    }

}
