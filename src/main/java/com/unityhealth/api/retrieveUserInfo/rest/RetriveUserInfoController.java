/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.api.retrieveUserInfo.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unityhealth.api.retrieveUserInfo.connect.User;
import com.unityhealth.api.retrieveUserInfo.connect.XMLApiBean;
import com.unityhealth.api.retrieveUserInfo.domain.ICoursesRepository;
import com.unityhealth.api.retrieveUserInfo.domain.IUserReportRepository;
import com.unityhealth.api.retrieveUserInfo.domain.Tblaccount;
import com.unityhealth.api.retrieveUserInfo.domain.Tblcourses;
import com.unityhealth.api.retrieveUserInfo.service.ProcessDataFromSFService;
import com.unityhealth.api.retrieveUserInfo.sf.userModel.Dummy;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
//import com.fasterxml.jackson.core.json.


/**
 *
 * @author Sameer S Argade
 */
@Transactional
@RestController
@RequestMapping("/api/account")
public class RetriveUserInfoController {
     private Logger log = LoggerFactory.getLogger(RetriveUserInfoController.class);
     private RestTemplate restTemplate;
    @Autowired
    private IUserReportRepository userReportRepository;
    @Autowired
    private Environment env;
    @Autowired
    private ProcessDataFromSFService sfService;
    @Autowired
    
    private ICoursesRepository courseRpository;
    
    @Autowired
     RetriveUserInfoController(IUserReportRepository userReportRepository,RestTemplateBuilder restTemplateBuilder,ProcessDataFromSFService sfService){
         userReportRepository = this.userReportRepository;
        this.sfService = sfService;
        // restTemplate =  restTemplateBuilder.basicAuthorization("seargade@australianT1", "sam123").build();
         restTemplate =  restTemplateBuilder.basicAuthorization("iTherapeutic@API", "Welcome@123").build();
         
     }
      @RequestMapping(value= {"/getAccountbyUserID"}, method = RequestMethod.GET,
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Tblaccount getAccountbyUserID(String userID){
        //System.out.println(userID);
        log.debug("userID recieved from the webservice" + userID);
        log.debug("before Find By " + userID);
        Calendar calendar = Calendar.getInstance();
        long yourmilliseconds = 1503802789000l+0000l;
        //calendar.setTimeInMillis(yourmilliseconds);
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        log.debug("-------------------------------------yo mate the date --------------------------------------------------" + dt1.format(calendar.getTime()));
        log.debug("-------------------------------------yo mate the date --------------------------------------------------" + dt1.format(new Date()));
        return userReportRepository.findByVUsername(userID);
    }
    @Transactional 
    @RequestMapping(value= {"/getAccountEmailbyUserID"}, method = RequestMethod.GET,
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String getEmailbyUserID(String userID,String name,String vUrl){
        System.out.println(userID);
        vUrl = vUrl.replace("https://connect.itherapeutics.com.au/", ""); //vUrl.substring(vUrl.indexOf("https://connect.itherapeutics.com.au"), vUrl.length());
        Tblcourses course = null;
        if(vUrl != null){
        course = courseRpository.findByVUrlContaining(vUrl);
        }
        if(userID !=null && !userID.equals("")){
        //Tblaccount account = userReportRepository.findByVUsername(userID);
        Tblaccount account = userReportRepository.findByApiUserId(userID);
        //System.out.println(Argade, Sameer S)
        System.out.println(name);
        System.out.println("vUrl recieved from the webservice ...... " + vUrl );
          log.debug("---------------------------------vUrl recieved from the webservice ------------------------------------ " + vUrl);
         log.debug("---------------------------------userID recieved from the webservice ------------------------------------ " + userID);
        log.debug("----------------------------------before Find By---------------------------------------------------------- " + userID);
        //System.out.println(nameArr[2]);
        if(account == null){
            account = new Tblaccount();
            account.setApiUserId(userID);
            if(name != null && !name.equals("")){
                
            String[] nameArr = name.split(",");
        System.out.println(nameArr[0]);
        System.out.println(nameArr[1]);
            account.setVFirstName(nameArr[0]);
            account.setVLastName(nameArr[1]);
            }else{
                 account.setVFirstName(userID);
            account.setVLastName(userID);
            }
            account.setDtJoinDate(new Date());
            
            String email = userID+"@api.net.au";
            account.setVEmail(email);
            account.setVUsername(email);
            account.setVStatus("active");
            account.setApiSyncStatus("OC");
            account = userReportRepository.save(account);
            
            String breezID = joinUser(account,course);
             log.debug("----------------------------------After Join User---------------------------------------------------------- " + breezID);
            if(breezID !="failure"){
                Integer iBreezeID = Integer.parseInt(breezID);
                         account.setIBreezeUserID(iBreezeID); 
                         userReportRepository.save(account);
                        }
            
        }else{
             XMLApiBean breezeAdmin =  new XMLApiBean(env.getProperty("adobeConnect"), env.getProperty("adobeConnectAdmin"), env.getProperty("adobeConnectAdminPassword"), null);

                String adminSession;
            try {
                adminSession = breezeAdmin.getBreezesession();
                 System.out.println(adminSession);
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(RetriveUserInfoController.class.getName()).log(Level.SEVERE, null, ex);
            }
               
            enrolUser(account.getIBreezeUserID().toString(), course.getICourseID().toString(),breezeAdmin);
        }
           // enrolUser(userID, userID, breezeAdmin);
        //course.getICourseID();
        
        return account.getVEmail();
        }
        return null;
    }
     public String joinUser( Tblaccount in, Tblcourses course) {
        log.debug("----------------------------------Inside Join User---------------------------------------------------------- " );
           
            String userId = null;
try {
            String ptPass = getRandomPassword();

           System.out.println(env.getProperty("adobeConnect") + " " + env.getProperty("adobeConnectAdmin") + " " + env.getProperty("adobeConnectAdminPassword"));
      
           XMLApiBean breezeAdmin =  new XMLApiBean(env.getProperty("adobeConnect"), env.getProperty("adobeConnectAdmin"), env.getProperty("adobeConnectAdminPassword"), null);

                String adminSession = breezeAdmin.getBreezesession();
                System.out.println(adminSession);
               User breezUser = breezeAdmin.getUser(in.getVUsername());
               if(breezUser != null){
                   userId = breezUser.getPrincipalID();
                 //  System.out.println(userId + " userId " + in.getVUsername());
               }else{
                userId = breezeAdmin.registerUser(in.getVFirstName(), in.getVLastName(), in.getVUsername(), encodeURL(ptPass), in.getVUsername());
                 breezeAdmin.addUserToGroup(userId, env.getProperty("breezeLearnersGroupSCO"));
               }
                System.out.println(userId + " userId " + in.getVUsername());
               enrolUser(userId, course.getICourseID().toString(), breezeAdmin);
        log.debug("----------------------------------After enroll in join User---------------------------------------------------------- " );
        } catch (Exception e) {
            e.printStackTrace();
            
                  return "failure";
            
         
        }
        return userId.toString();
    }
     
// public String joinUser( Tblaccount in) {
//        log.debug("----------------------------------Inside Join User---------------------------------------------------------- " );
//           
//            String userId = null;
//try {
//            String ptPass = getRandomPassword();
//
//           System.out.println(env.getProperty("adobeConnect") + " " + env.getProperty("adobeConnectAdmin") + " " + env.getProperty("adobeConnectAdminPassword"));
//      
//           XMLApiBean breezeAdmin =  new XMLApiBean(env.getProperty("adobeConnect"), env.getProperty("adobeConnectAdmin"), env.getProperty("adobeConnectAdminPassword"), null);
//
//                String adminSession = breezeAdmin.getBreezesession();
//                System.out.println(adminSession);
//               User breezUser = breezeAdmin.getUser(in.getVUsername());
//               if(breezUser != null){
//                   userId = breezUser.getPrincipalID();
//                 //  System.out.println(userId + " userId " + in.getVUsername());
//               }else{
//                userId = breezeAdmin.registerUser(in.getVFirstName(), in.getVLastName(), in.getVUsername(), encodeURL(ptPass), in.getVUsername());
//                 breezeAdmin.addUserToGroup(userId, env.getProperty("breezeLearnersGroupSCO"));
//               }
//                System.out.println(userId + " userId " + in.getVUsername());
//               
//        log.debug("----------------------------------After enroll in join User---------------------------------------------------------- " );
//        } catch (Exception e) {
//            e.printStackTrace();
//            
//                  return "failure";
//            
//         
//        }
//        return userId.toString();
//    }
     
     
     public String enrolUser(String iUserId, String iModuleId,XMLApiBean breezeAdmin){
       //   System.out.println(env.getProperty("adobeConnect") + " " + env.getProperty("adobeConnectAdmin") + " " + env.getProperty("adobeConnectAdminPassword"));
       //     XMLApiBean breezeAdmin = new XMLApiBean("https://connect.itherapeutics.com.au", "info@itherapeutics.com.au","qwasz2004!", null);
         //  XMLApiBean breezeAdmin =  new XMLApiBean(env.getProperty("adobeConnect"), env.getProperty("adobeConnectAdmin"), env.getProperty("adobeConnectAdminPassword"), null);
 
         try {
               log.debug("----------------------------------Inside Enrol User---------------------------------------------------------- " );
             String adminSession = breezeAdmin.getBreezesession();
              breezeAdmin.enroll(iUserId, iModuleId);
              log.debug("----------------------------------After Enroll ------------" + iUserId + "---------------------------------------------- " + iModuleId);
         } catch (Exception ex) {
             java.util.logging.Logger.getLogger(RetriveUserInfoController.class.getName()).log(Level.SEVERE, null, ex);
         }
           return "success";
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
     @RequestMapping(value= {"/getSFbyUserID"}, method = RequestMethod.GET,
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
   List<Dummy>  restClientMethod(){
     //String str =   restTemplate.getForObject("https://api10preview.sapsf.com/odata/v2/User('SEARGADE')?$format=json",String.class);
   //  String recordCount = restTemplate.getForObject("https://api10preview.sapsf.com/odata/v2/User/$count?$format=json",String.class);
   String prodUserUrl = "https://api10.successfactors.com/odata/v2/User";
   String jsonFormat = "?$format=json";
   String recordCountUrl =  prodUserUrl + "/$count" + jsonFormat;
  
   String prodUserUrlJsonFormat = prodUserUrl + jsonFormat;
    String recordCount = restTemplate.getForObject(recordCountUrl,String.class);
     Integer count = Integer.parseInt(recordCount);
     Integer noOfPages = count/1000;
     Integer remainingRecords = count %(noOfPages * 1000);
     if (remainingRecords > 0){
         noOfPages++;
     }
         log.debug(" Total Number of pages ........................................" + noOfPages);
         Dummy dummy =  null;
         List<Dummy> dummyList = new ArrayList<Dummy>();
     for(int i = 1; i <= noOfPages; i++ ){
          log.debug(" Inside the for loop stay tuned ........................................ " + i);
         if(i == 1){
          // dummy =    restTemplate.getForObject("https://api10preview.sapsf.com/odata/v2/User?$format=json",Dummy.class);
          dummy =    restTemplate.getForObject(prodUserUrlJsonFormat,Dummy.class);
          log.debug("i is one ........................................" + dummy.getD());
          log.debug("The next page is at ........................................" + dummy.getD().getNext());
         }else if(dummy != null && dummy.getD() != null  && dummy.getD().getNext()!= null){
             dummy =    restTemplate.getForObject(dummy.getD().getNext(),Dummy.class);
         log.debug("D is ........................................" + dummy.getD());
          log.debug("The next page is at ........................................" + dummy.getD().getNext());
            }
       
        dummyList.add(dummy);
          Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        
          ObjectMapper objectMapper = new ObjectMapper();
           RandomAccessFile aFile;
           
                try {
                     log.debug("Prepare to write to file number .............................." + i);
                    aFile = new RandomAccessFile("E:\\retrieveUserInfo\\FileFromSFProd" + i + dt1.format(calendar.getTime()) +  ".json", "rw");
                     FileChannel inChannel = aFile.getChannel();
                     String jsonInString = objectMapper.writeValueAsString(dummy);
                     ByteBuffer buf = ByteBuffer.allocate(jsonInString.length());
                    buf.clear();
                    if(jsonInString != null ){
                    buf.put(jsonInString.getBytes());
                    }
                    else{
                          log.debug("Shouldn't come here at all ........................................" + jsonInString);
                          
                    }

                    buf.flip();
                       log.debug(" about to write to file  ........................................"  + i);
                    while (buf.hasRemaining()) {

                            inChannel.write(buf);

                    }
                    inChannel.close();
                 log.debug(" finished write to file  ........................................"  + i);

                } catch (FileNotFoundException ex) {
                    java.util.logging.Logger.getLogger(RetriveUserInfoController.class.getName()).log(Level.SEVERE, null, ex);
                    log.debug("FileNotFoundException ........................................" + ex);
                } catch (JsonProcessingException ex) {
                    java.util.logging.Logger.getLogger(RetriveUserInfoController.class.getName()).log(Level.SEVERE, null, ex);
                    log.debug("JsonProcessingException ........................................" + ex);
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(RetriveUserInfoController.class.getName()).log(Level.SEVERE, null, ex);
                    log.debug("IOException ........................................" + ex);
                }
            
           
     }
           return dummyList;
    }
    @RequestMapping(value= {"/readAndValidate"}, method = RequestMethod.GET,
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
   void  readAndValidate(String jsonFileName,String excelFileName){
       sfService.validateUsersLit(jsonFileName,excelFileName);
   }
   
   
   @RequestMapping(value= {"/downloadBioceuticalsProducts"}, method = RequestMethod.GET,
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
   void  download(String jsonFileName,String excelFileName){
       restTemplate = new RestTemplate();
//       MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();  
//       body.add("X-API-KEY", "H03js2hbsv20fjj2hd034s034jb2vdl2sd04435s");
//       
//        HttpHeaders headers = new HttpHeaders();
//    //headers.setContentType(MediaType.APPLICATION_JSON);
//   HttpEntity<MultiValueMap> entity = new HttpEntity<MultiValueMap>(body, headers);
//   // headers.add("X-API-KEY", "H03js2hbsv20fjj2hd034s034jb2vdl2sd04435s");
//      //  restTemplate = new RestTemplate(); //restTemplateBuilder.basicAuthorization("iTherapeutic@API", "Welcome@123").build();
//       // HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
//        try{
//            ResponseEntity<String> response = restTemplate.exchange("https://webservice.fit.net.au/imgateway/products/get.json?name=ZBM", HttpMethod.GET, entity, String.class);
//        System.out.println("Result - status ("+ response.getStatusCode() + ") has body: " + response.hasBody());
//        }catch(Exception e){
//            e.printStackTrace();
//        }
         Map<String,Object> headersMap = Collections.unmodifiableMap(
            Stream.of(
                    new SimpleEntry<>("X-API-KEY", "H03js2hbsv20fjj2hd034s034jb2vdl2sd04435s"),
                    new SimpleEntry<>("Accept", "application/json")
            )
            .collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())));
    HttpEntity<?> httpEntity = buildHttpEntity(headersMap);

    //log.info("API Key: {}, Call sign: {}", apikey, userId);

    RestTemplate restTemplate = new RestTemplate();
    
    List<String> productList = new ArrayList<String>();
    productList.add("UltraDefense-Energy-Blend");
productList.add("UltraDefense-Maitake-D");
productList.add("UltraDefense-Trametes");
productList.add("UD-Mushroom-Complex");
productList.add("UltraDefense-Mushroom-7");
productList.add("HepatoClear");
productList.add("Histammune-Clear");
productList.add("NeoFlora-For-Juniors");
productList.add("Paracea-Forte");
productList.add("Bio-Chromium-Plus");
productList.add("Bio-Disc");
productList.add("Bio-Mineral-Formula");
productList.add("CF43");
productList.add("CP57");
productList.add("CPIP");
productList.add("CPMP");
productList.add("CS36");
productList.add("IP82");
productList.add("MP65");
productList.add("Magcal-Plus");
productList.add("ML20");
productList.add("PC73");
productList.add("PCCP");
productList.add("PCIP");
productList.add("PCMP");
productList.add("PP85");
productList.add("PPIP");
productList.add("PPMP");
productList.add("PS29");
productList.add("PSMP");
productList.add("PSPC");
productList.add("S79");
productList.add("SCF");
productList.add("SP96");
productList.add("SPCF");
productList.add("SPCP");
productList.add("SPIP");
productList.add("SPMP");
productList.add("SPPC");
productList.add("SPPS");
productList.add("SPS");
productList.add("SPSS");
productList.add("SS-69");
productList.add("SSMP");
productList.add("SSPC");
productList.add("SSS");
productList.add("Sodical-Plus");
productList.add("ZBM");

    
    for(String productName:productList){
       ResponseEntity<String> response = restTemplate.exchange("https://webservice.fit.net.au/imgateway/products/get.json?name="+ productName, HttpMethod.GET, httpEntity, String.class);
        System.out.println("Result - status ("+ response.getStatusCode() + ") has body: " + response.hasBody() + response.getBody() );
     RandomAccessFile aFile;
           
                try {
                     log.debug("Prepare to write to file number .............................." + productName );
                    aFile = new RandomAccessFile("E:\\retrieveUserInfo\\" + productName +  ".json", "rw");
                     FileChannel inChannel = aFile.getChannel();
                    // ObjectMapper objectMapper = new ObjectMapper();
                    // String jsonInString = objectMapper.writeValueAsString(response.getBody());
                     ByteBuffer buf = ByteBuffer.allocate(response.getBody().length());
                    buf.clear();
                    if(response.getBody() != null ){
                    buf.put(response.getBody().getBytes());
                    }
                    else{
                          log.debug("Shouldn't come here at all ........................................" + response.getBody());
                          
                    }

                    buf.flip();
                   //    log.debug(" about to write to file  ........................................"  + i);
                    while (buf.hasRemaining()) {

                            inChannel.write(buf);

                    }
                    inChannel.close();
               //  log.debug(" finished write to file  ........................................"  + i);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
   }
      
//        System.out.println("Result - status ("+ response.getStatusCode() + ") has body: " + response.hasBody());
    //return restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class, userId);
   
   }
   // Returns HttpEntity object with the specified headers set
private HttpEntity<String> buildHttpEntity(Map<String,Object> headerParams) {

    HttpHeaders headers = new HttpHeaders();
    headerParams.forEach((k,v)->headers.set(k, v.toString()));
    return new HttpEntity<String>(headers);
    
    
}
  @RequestMapping(value= {"/createAPiUsers"}, method = RequestMethod.GET,
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
   void  createAPiUsers(){
       sfService.addNewUSersToBreeze();
   }
}
