/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.api.retrieveUserInfo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unityhealth.api.retrieveUserInfo.domain.IUserReportRepository;
import com.unityhealth.api.retrieveUserInfo.domain.Tblaccount;
import com.unityhealth.api.retrieveUserInfo.sf.userModel.Dummy;
import com.unityhealth.api.retrieveUserInfo.sf.userModel.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sameer S Argade
 */
@Service
public class ProcessDataFromSFServiceImpl implements ProcessDataFromSFService {

    private Logger log = LoggerFactory.getLogger(ProcessDataFromSFServiceImpl.class);

   // @Autowired
    private IUserReportRepository userReportRepository;

    ProcessDataFromSFServiceImpl(IUserReportRepository userReportRepository) {
        this.userReportRepository = userReportRepository;
    }

    @Override
    public void validateUsersLit(List<Dummy> dataList) {
        ObjectMapper objectMapper = new ObjectMapper();
        //file = new File("E:\\biocueticalsload\\product.json");
        File file = new File("E:\\retrieveUserInfo\\FileFromSF130-14-2018.json");
        long time1 = System.currentTimeMillis();

        try {
            Dummy singlePage = new Dummy();
            singlePage = objectMapper.readValue(file, Dummy.class);
            dataList = new ArrayList<Dummy>();
            dataList.add(singlePage);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ProcessDataFromSFServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        Integer foundByEmail = 0;
        Integer foundByBusinessEmail = 0;
        Integer foundByPricelineStores = 0;
        Integer foundInNonPriceLinseStores = 0;
        Integer foundByNameAlone = 0;
        Integer blankUsers = 0;
        Integer processedCount = 0;
        Integer totalCount = 0;
        Integer notFountCount=0;
        User[] userList = null;
        List<User> foundByEmailUsers= new ArrayList<User>();
        List<User> foundByBusinessEmailUsers= new ArrayList<User>();
        List<User> foundByPricelineStoresUsers= new ArrayList<User>();
        List<User> foundInNonPriceLinseStoresUsers= new ArrayList<User>();
        List<User> foundByAddressUsers= new ArrayList<User>();
        List<User> foundByNameAloneUsers= new ArrayList<User>();
        List<User> notFoundUsers= new ArrayList<User>();
         List<User> blankUsersList = new ArrayList<User>();
        
        List<Tblaccount> foundByEmailItheraUsers= new ArrayList<Tblaccount>();
        List<Tblaccount> foundByBusinessEmailItheraUsers= new ArrayList<Tblaccount>();
        List<Tblaccount> foundByPricelineStoresItheraUsers= new ArrayList<Tblaccount>();
        List<Tblaccount> foundInNonPriceLinseStoresItheraUsers= new ArrayList<Tblaccount>();
        List<Tblaccount> foundByNameAloneItheraUsers= new ArrayList<Tblaccount>();
        
        
        for (Dummy data : dataList) {
            userList = data.getD().getResults();
            for (User user : userList) {
                //find user by email id 
                totalCount = userList.length;
                log.debug(".....................users totalCount ................................. " + totalCount);
                processedCount++;
                long looptime1 = System.currentTimeMillis();
                String email = null;
                String storeName = null;
                String businessEmail = null;
                List<Integer> groupIDs = new ArrayList<Integer>();
                groupIDs.add(79);
                groupIDs.add(199);
                if (user.getEmail() != null && user.getEmail().lastIndexOf("_1") != -1) {
                    email = user.getEmail().substring(0, user.getEmail().lastIndexOf("_1")).trim();
                } else if (user.getEmail() != null) {
                    email = user.getEmail().trim();
                }

                log.debug(".....................user ................................. " + user.getFirstName() + "......" + user.getLastName() + "......" + user.getEmail() + "......." + user.getUserId());
                if (user.getLocation() != null && user.getLocation().lastIndexOf("(") != -1) {
                    storeName = user.getLocation().substring(0, user.getLocation().lastIndexOf("(")).trim();
                } else if (user.getLocation() != null && !user.getLocation().equals("N\\A")) {
                    storeName = user.getLocation().trim();
                }
                if (user.getCustom12() != null && user.getCustom12().lastIndexOf("_1") != -1) {
                    businessEmail = user.getCustom12().substring(0, user.getCustom12().lastIndexOf("_1"));
                } else {
                    businessEmail = user.getCustom12();
                }

                log.debug(".....................storeName................................. " + storeName);
                Tblaccount account = userReportRepository.findByApiUserId(user.getUserId());
                if (account == null && user.getFirstName() != null && user.getLastName() != null) {
                    if (email != null){
                     account = userReportRepository.findByVUsernameAndVStatus(email, "active");
                    }
                            
                    
                    if (account != null) { // if user is found in ithera db the user is already present in the db
                        log.debug(".....................user found by email ................................. " + email);
                        foundByEmailUsers.add(user);
                        account.setApiUserId(user.getUserId());
                        
                        foundByEmail++;
                    } else {
                        //  log.debug(".....................user not found by email ................................. " + email );

                        account = userReportRepository.findByVUsernameAndVStatus(businessEmail, "active");

                        if (account != null) {
                            log.debug(".....................user found by businessEmail ................................. " + businessEmail);
                            account.setApiUserId(user.getUserId());
                            foundByBusinessEmail++;
                            foundByBusinessEmailUsers.add(user);
                        } else {
                            //  log.debug(".....................user not found by businessEmail ................................. " + businessEmail );
//                            account = userReportRepository.findByVEmailAndVStatus(email, "active");
//                            if (account != null) {
//                                log.debug(".....................user found by businessEmail ................................. " + businessEmail);
//                                account.setApiUserId(user.getUserId());
//                                foundByBusinessEmail++;
//                            } else {
//                                if (user.getCustom12() != null && user.getCustom12().lastIndexOf("_1") != -1) {
//                                    businessEmail = user.getCustom12().substring(0, user.getCustom12().lastIndexOf("_1"));
//                                } else {
//                                    businessEmail = user.getCustom12();
//                                }
//                                account = userReportRepository.findByVEmailAndVStatus(businessEmail, "active");
//                                if (account != null) {
//                                    log.debug(".....................user found by businessEmail ................................. " + businessEmail);
//                                    account.setApiUserId(user.getUserId());
//                                    foundByBusinessEmail++;
//                                } else {
                            //log.debug(".....................use not  found by businessEmail ................................. " + businessEmail );

                            if (storeName != null) {
                                account = userReportRepository.findByVFirstNameAndVLastNameAndStore_storeNameAndStore_Group_IdInAndVStatus(user.getFirstName(), user.getLastName(), storeName, groupIDs, "active");
                            }
                            if (account != null) {

                                log.debug("PriceLine Store " + user.getFirstName() + "......" + user.getLastName() + "......" + user.getEmail() + "......" + user.getLocation() + "......" + account.getVEmail());
                                foundByPricelineStores++;
                                foundByPricelineStoresUsers.add(user);
                                 account.setApiUserId(user.getUserId());
                            } else {
                                if (storeName != null) {
                                    account = userReportRepository.findByVFirstNameAndVLastNameAndStore_storeNameAndStore_Group_IdNotInAndVStatus(user.getFirstName(), user.getLastName(), storeName, groupIDs, "active");
                                }

                                if (account != null) {
                                    account.setApiUserId(user.getUserId());
                                    foundInNonPriceLinseStoresUsers.add(user);
                                    log.debug(" Not a PriceLine Store " + user.getFirstName() + "......" + user.getLastName() + "......" + user.getEmail() + "......" + user.getLocation() + "......" + account.getVEmail());
                                } else {
                                    account = userReportRepository.findByAddress(user.getFirstName(), user.getLastName(), user.getAddressLine1(), user.getCity(), user.getState(), user.getZipCode());
                                    if (account != null) {
                                        foundInNonPriceLinseStores++;
                                         account.setApiUserId(user.getUserId());
                                         foundByAddressUsers.add(user);
                                        log.debug(" Not a PriceLine Store matched by address  " + user.getFirstName() + "......" + user.getLastName() + "......" + user.getEmail() + "......" + user.getLocation() + "......" + account.getVEmail());
                                    } else {
                                        account = userReportRepository.findByVFirstNameAndVLastNameAndVStatus(user.getFirstName(), user.getLastName(), "active");
                                        if (account != null) {
                                            account.setApiUserId(user.getUserId());
                                            foundByNameAloneUsers.add(user);
                                            foundByNameAlone++;
                                            log.debug("found by firstnmae last name only Not in the Store they suggest so a " + account.getVEmail() + " ..... account store" + account.getStore().getStoreName());
                                        }else{
                                            notFountCount++;
                                            notFoundUsers.add(user);
                                        }

                                    }

                                }
                         
                    }
                }
            }
        }else {
                    blankUsers++;
                    blankUsersList.add(user);
                    log.debug("Either Email is ..... " + email + "or firstname is ...... " + user.getFirstName() + "or lastName is .... " + user.getLastName() + "............userId....." +  user.getUserId());
                }
        long looptime2 = System.currentTimeMillis();
        long loopTimeTaken = (looptime2 - looptime1) / 1000;
        long loopTimeTakenSfar = (looptime2 - time1) / 1000;
        log.debug("Total Time Taken by this iteration is ........................" + loopTimeTaken);
        log.debug("Total Time Taken so far is ........................" + loopTimeTakenSfar);
        log.debug(".....................users processedCount ................................. " + processedCount);
        log.debug("The Stats for the iteration ..... foundByEmail=" + foundByEmail + "........ foundByBusinessEmail=" + foundByBusinessEmail + ".......foundByPricelineStores=" + foundByPricelineStores + "..........foundInNonPriceLinseStores" + foundInNonPriceLinseStores + "............foundByNameAlone=" + foundByNameAlone + ".......blankUsers" + blankUsers + "..................notFountCount" + notFountCount);
    }

}
log.debug("The Stats for the day ..... foundByEmail=" + foundByEmail + "........ foundByBusinessEmail=" + foundByBusinessEmail + ".......foundByPricelineStores=" + foundByPricelineStores + "..........foundInNonPriceLinseStores" + foundInNonPriceLinseStores + "............foundByNameAlone=" + foundByNameAlone + ".......blankUsers" + blankUsers  + "..................notFountCount" + notFountCount);

        long time2 = System.currentTimeMillis();
        long timeTaken = (time2 - time1)/1000;
        log.debug("Total Time Taken by this prgram is " + timeTaken);
        
        for(User fbe:foundByEmailUsers){
            log.debug(" Found by Email Users ............ " + fbe);
        }
        for(User fbbe:foundByBusinessEmailUsers){
             log.debug(" Found by business Email Users ............ " + fbbe);
        }
        for(User fbps:foundByPricelineStoresUsers){
             log.debug(" Found by Pricline Stores Users ............ " + fbps);
        }
        for(User fbnps:foundInNonPriceLinseStoresUsers){
             log.debug(" Found by NonPriceLise Stores Users ............ " + fbnps);
        }
        for(User fba:foundByAddressUsers){
             log.debug(" Found by Address Users ............ " + fba);
        }
        for(User fbn:foundByNameAloneUsers){
             log.debug(" Found by Name Alone Users ............ " + fbn);
        }
        for(User nf:notFoundUsers){
             log.debug(" Not Found  Users ............ " + nf);
        }
         for(User blank: blankUsersList){
              log.debug(" Blank first name or last name  Users ............ " + blank);
         }
    }

    
    void buildExcelDocument(User user, Tblaccount account,String syncStatus) throws Exception {

    // change the file name
    //response.setHeader("Content-Disposition", "attachment; filename=\"my-xls-file.xls\"");
 Workbook workbook = new XSSFWorkbook();
//    @SuppressWarnings("unchecked")
//              ObjectMapper objectMapper = new ObjectMapper();
//        //file = new File("E:\\biocueticalsload\\product.json");
//        File file = new File("E:\\retrieveUserInfo\\FileFromSF130-14-2018.json");
//        long time1 = System.currentTimeMillis();
// List<Dummy> dataList = null;
//       
//            Dummy singlePage = new Dummy();
//            singlePage = objectMapper.readValue(file, Dummy.class);
//           dataList = new ArrayList<Dummy>();
//            dataList.add(singlePage);
       
    //User[] users = singlePage.getD().getResults();

    // create excel xls sheet
    Sheet sheet = workbook.createSheet("User Detail");
    sheet.setDefaultColumnWidth(30);

    // create style for header cells
    CellStyle style = workbook.createCellStyle();
    Font font = workbook.createFont();
    font.setFontName("Arial");
    style.setFillForegroundColor(HSSFColor.BLUE.index);
    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    font.setBold(true);
    font.setColor(HSSFColor.WHITE.index);
    style.setFont(font);


    // create header row
    Row header = sheet.createRow(0);
    header.createCell(0).setCellValue("SyncStatus");
    header.getCell(0).setCellStyle(style);
    header.createCell(1).setCellValue("SF_Personal_Email");
    header.getCell(0).setCellStyle(style);
    header.createCell(1).setCellValue("Ithera_Personal_Email");
    header.getCell(0).setCellStyle(style);
     header.createCell(1).setCellValue("SF_Business_Email");
    header.getCell(0).setCellStyle(style);
     header.createCell(1).setCellValue("Ithera_Business_Email");
      header.getCell(0).setCellStyle(style);
     header.createCell(1).setCellValue("SF_UserID");
    header.getCell(0).setCellStyle(style);
    header.createCell(2).setCellValue("Firstname");
    
    header.getCell(0).setCellStyle(style);
    
    header.createCell(2).setCellValue("Firstname");
    header.getCell(0).setCellStyle(style);
    header.createCell(1).setCellValue("LastName");
    header.getCell(1).setCellStyle(style);
    header.createCell(2).setCellValue("Age");
    header.getCell(2).setCellStyle(style);
    header.createCell(3).setCellValue("Job Title");
    header.getCell(3).setCellStyle(style);
    header.createCell(4).setCellValue("Company");
    header.getCell(4).setCellStyle(style);
    header.createCell(5).setCellValue("Address");
    header.getCell(5).setCellStyle(style);
    header.createCell(6).setCellValue("City");
    header.getCell(6).setCellStyle(style);
    header.createCell(7).setCellValue("Country");
    header.getCell(7).setCellStyle(style);
    header.createCell(8).setCellValue("Phone Number");
    header.getCell(8).setCellStyle(style);
    



    int rowCount = 1;

   // for(User user : users){
        Row userRow =  sheet.createRow(rowCount++);
        userRow.createCell(0).setCellValue(user.getFirstName());
        userRow.createCell(1).setCellValue(user.getLastName());
        userRow.createCell(2).setCellValue(user.getEmail());
        userRow.createCell(3).setCellValue(user.getJobTitle());
        userRow.createCell(4).setCellValue(user.getCustom02());
        userRow.createCell(5).setCellValue(user.getAddressLine1());
        userRow.createCell(6).setCellValue(user.getCity());
        userRow.createCell(7).setCellValue(user.getCountry());
        userRow.createCell(8).setCellValue(user.getLocation());

     //   }
     File aFile;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
     aFile = new File("E:\\retrieveUserInfo\\FileFromSF" + "log" + dt1.format(calendar.getTime())  +  ".xlsx");
                    
                    // String jsonInString = objectMapper.writeValueAsString(dummy);
                     
                    
                    FileOutputStream outputStream = new FileOutputStream(aFile);
            workbook.write(outputStream);
            workbook.close();

}
    
    public static void main(String args[]) throws Exception {

        ProcessDataFromSFServiceImpl processDataFromSFServiceImpl = new ProcessDataFromSFServiceImpl(null);
       // processDataFromSFServiceImpl.buildExcelDocument();
        
    }

}
