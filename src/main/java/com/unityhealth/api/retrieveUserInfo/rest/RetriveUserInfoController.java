/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.api.retrieveUserInfo.rest;

import com.unityhealth.api.retrieveUserInfo.connect.XMLApiBean;
import com.unityhealth.api.retrieveUserInfo.domain.IUserReportRepository;
import com.unityhealth.api.retrieveUserInfo.domain.Tblaccount;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sameer S Argade
 */
@Transactional
@RestController
@RequestMapping("/api/account")
public class RetriveUserInfoController {
    @Autowired
    private IUserReportRepository userReportRepository;
    @Autowired
     RetriveUserInfoController(IUserReportRepository userReportRepository){
         userReportRepository = this.userReportRepository;
         
     }
      @RequestMapping(value= {"/getAccountbyUserID"}, method = RequestMethod.GET,
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Tblaccount getAccountbyUserID(String userID){
        System.out.println(userID);
        
        return userReportRepository.findByVUsername(userID);
    }
     @RequestMapping(value= {"/getAccountEmailbyUserID"}, method = RequestMethod.GET,
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String getEmailbyUserID(String userID){
        System.out.println(userID);
        Tblaccount account = userReportRepository.findByVUsername(userID);
        return account.getVEmail();
    }
     public String joinUser(@RequestBody Tblaccount in) {
        //EmailSenderResponseDto esrDto = new EmailSenderResponseDto();
        

           // CompanyUsers out = null; new CompanyUsers();
           // AccountDto in = joinPagedto.getAccount();
           
            String userId = null;
try {
            

         //   System.out.println(env.getProperty("adobeConnect") + " " + env.getProperty("adobeConnectAdmin") + " " + env.getProperty("adobeConnectAdminPassword"));
            XMLApiBean breezeAdmin = new XMLApiBean("", "","", null);
           

                String adminSession = breezeAdmin.getBreezesession();
                System.out.println(adminSession);
                userId = breezeAdmin.registerUser(in.getVFirstName(), in.getVLastName(), in.getVUsername(), encodeURL(""), in.getVUsername());
                System.out.println(in.getIBreezeUserID()+ "in.getBreezeID() ");
       in =   userReportRepository.save(in);
        } catch (Exception e) {
            e.printStackTrace();
             if (in.getIBreezeUserID() != null){
                 return "failure";
             }else{
                  return "failure";
             }
         
        }
        return in.getIBreezeUserID().toString();
    }

    /**
     *
     * @return
     */
    public String getRandomPassword() {

        String password = "";
        Random generator = new Random();

        String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "v", "t", "u", "v", "w", "x", "y", "z"};

        for (int i = 0; i < 6; i++) {
            password += chars[generator.nextInt(26)];
        }
        return password;
    }

    public String get_SHA_SecurePassword(String passwordToHash, String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    //Add salt
    public String getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
    }

    public String encodeURL(String url) {
        String encoded = null;
        try {
            encoded = URLEncoder.encode(url.trim(), "UTF-8");
            int last = encoded.length();
            if (encoded.charAt(last - 1) == '/') {
                encoded = encoded.substring(0, last - 1);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encoded;
    }
}
