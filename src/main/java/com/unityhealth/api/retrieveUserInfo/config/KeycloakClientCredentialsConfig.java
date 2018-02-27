/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.api.retrieveUserInfo.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

/**
 *
 * @author Sameer S Argade
 */
@Configuration
@EnableOAuth2Client
@EnableWebSecurity
public class KeycloakClientCredentialsConfig  extends WebSecurityConfigurerAdapter { 
    @Value("${security.oauth2.client.clientId}")
    private String clientId;
 
    @Value("${security.oauth2.client.clientSecret}")
    private String clientSecret;
 
    @Value("${security.oauth2.client.accessTokenUri}")
    private String accessTokenUri;
 
   
    @Override
    public void configure( final WebSecurity web ) throws Exception {
        web.ignoring().antMatchers( "/**");
    }
 
    @Override
    protected void configure( final HttpSecurity http ) throws Exception {
        super.configure( http );
    }
 
    @Bean
    public OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        List<String>scope = new ArrayList<>();
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        details.setAccessTokenUri(accessTokenUri);
        
        //details.setScope(scope);
        //details.setGrantType("client_credentials");
        
        return details;
    }
 
    @Bean
    public OAuth2RestTemplate createRestTemplate(OAuth2ClientContext clientContext) {
        return new OAuth2RestTemplate(oAuth2ProtectedResourceDetails(), clientContext);
    }
    
}
