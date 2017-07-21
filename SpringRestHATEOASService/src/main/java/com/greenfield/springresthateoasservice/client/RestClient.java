package com.greenfield.springresthateoasservice.client;


import static org.junit.Assert.assertNotNull;

import java.net.URI;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.greenfield.springresthateoasservice.domain.AuthTokenInfo;
import com.greenfield.springresthateoasservice.domain.Bookmark;
import com.greenfield.springresthateoasservice.hateoas.BookmarkResource;

public class RestClient {
	
	private static final String REST_SERVICE_URI = "https://localhost:8443/SpringRestHATEOASService/";
	
	private static final String AUTH_SERVER_URI = "https://localhost:8443/SpringRestHATEOASService/oauth/token";
    
	private static final String OAUTH2_PASSWORD_GRANT = "?grant_type=password&username=jhoeller&password=password";
    
    private static final String OAUTH2_ACCESS_TOKEN = "?access_token=";
    
    private static final RestTemplate restTemplate = new RestTemplate();
    
    private static final Logger logger = LoggerFactory.getLogger(RestClient.class);
    
     // Prepare HTTP Headers.
    private static HttpHeaders getHeaders() {
    	
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        
        return headers;
    }
    
    // Add HTTP Authorization header, using Basic-Authentication to send client-credentials.
    private static HttpHeaders getHeadersWithClientCredentials(){
    	
    	// clientId:secret as client credential with basic authorization
        String plainClientCredentials="android-bookmarks:123456";
        String base64ClientCredentials = new String(Base64.encodeBase64(plainClientCredentials.getBytes()));
         
        HttpHeaders headers = getHeaders();
        headers.add("Authorization", "Basic " + base64ClientCredentials);
        return headers;
    }    
     
    //Send a POST request [on /oauth/token] to get an access-token, which will then be send with each request.
    @SuppressWarnings({ "unchecked"})
    private static AuthTokenInfo sendTokenRequest() {
        
        HttpEntity<String> request = new HttpEntity<>(getHeadersWithClientCredentials());
         
        ResponseEntity<Object> response = restTemplate.exchange(AUTH_SERVER_URI + OAUTH2_PASSWORD_GRANT, HttpMethod.POST, request, Object.class);
        
        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) response.getBody();
        AuthTokenInfo tokenInfo = null;
         
        if ( map != null ) {
        	
            tokenInfo = new AuthTokenInfo();
            
            tokenInfo.setAccess_token((String)map.get("access_token"));
            tokenInfo.setToken_type((String)map.get("token_type"));
            tokenInfo.setRefresh_token((String)map.get("refresh_token"));
            tokenInfo.setExpires_in((int)map.get("expires_in"));
            tokenInfo.setScope((String)map.get("scope"));
            
            logger.info("The access token with OAuth2 returned: {}", tokenInfo);
        }
        else{
        	logger.info("The access token with OAuth2 didn't return");         
        }
        
        return tokenInfo;
    }
    
    // Send a GET request to get a specific username
    private static void getBookmark(AuthTokenInfo tokenInfo) {
    	
        assertNotNull("The OAuth2 access token doesn't exist", tokenInfo);
        
        logger.info("Start testing getBookmark method for Bookmark Rest services");
        
        HttpEntity<String> request = new HttpEntity<>(getHeaders());
        
        ResponseEntity<BookmarkResource> response = restTemplate.exchange(REST_SERVICE_URI + "/mfisher/bookmarks/11" + OAUTH2_ACCESS_TOKEN + tokenInfo.getAccess_token(),
        		HttpMethod.GET, request, BookmarkResource.class);
        
        BookmarkResource bookmarkResource = response.getBody();
        logger.info("The bookmark result is: {}", bookmarkResource.getBookmark());
    }
    

    // Send a POST request to create a new user.
    private static void addBookmark(AuthTokenInfo tokenInfo) {
    	
    	assertNotNull("The OAuth2 access token doesn't exist", tokenInfo);
    	
    	logger.info("Start testing getBookmark method for Bookmark Rest services");
    	
        Bookmark bookmark = new Bookmark("http://bookmark.com/18/", "Third bookmark description");
        
        HttpEntity<Object> request = new HttpEntity<>(bookmark, getHeaders());
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/jlong/bookmarks/" + OAUTH2_ACCESS_TOKEN + tokenInfo.getAccess_token(),
                request, BookmarkResource.class);
        
       logger.info("Location of new bookmark: {}", uri.toASCIIString());
    }
    
    public static void main(String[] args) {
    	
        AuthTokenInfo tokenInfo = sendTokenRequest();
        
        getBookmark(tokenInfo);
        
        addBookmark(tokenInfo);
    }

}