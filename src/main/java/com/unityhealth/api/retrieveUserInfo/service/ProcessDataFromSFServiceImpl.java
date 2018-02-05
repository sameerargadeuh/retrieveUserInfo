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
import java.io.FileNotFoundException;
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
        FileOutputStream outputStream = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            //file = new File("E:\\biocueticalsload\\product.json");
            File file = new File("E:\\retrieveUserInfo\\5tfeb200.json");
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
            Integer foundInPriceLinseStores = 0;
            Integer foundByNameAlone = 0;
            Integer blankUsers = 0;
            Integer processedCount = 0;
            Integer totalCount = 0;
            Integer notFountCount = 0;
            Integer rowCount = 1;
            User[] userList = null;
            List<User> foundByEmailUsers = new ArrayList<User>();
            List<User> foundByBusinessEmailUsers = new ArrayList<User>();
            List<User> foundByPricelineStoresUsers = new ArrayList<User>();
            List<User> foundInNonPriceLinseStoresUsers = new ArrayList<User>();
            List<User> foundInPriceLinseStoresList = new ArrayList<User>();
            List<User> foundByAddressUsers = new ArrayList<User>();
            List<User> foundByNameAloneUsers = new ArrayList<User>();
            List<User> notFoundUsers = new ArrayList<User>();
            List<User> blankUsersList = new ArrayList<User>();
            List<Tblaccount> foundByEmailItheraUsers = new ArrayList<Tblaccount>();
            List<Tblaccount> foundByBusinessEmailItheraUsers = new ArrayList<Tblaccount>();
            List<Tblaccount> foundByPricelineStoresItheraUsers = new ArrayList<Tblaccount>();
            List<Tblaccount> foundInNonPriceLinseStoresItheraUsers = new ArrayList<Tblaccount>();
            List<Tblaccount> foundByNameAloneItheraUsers = new ArrayList<Tblaccount>();
            Workbook workbook = new XSSFWorkbook();
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
            header.getCell(1).setCellStyle(style);
            header.createCell(2).setCellValue("Ithera_Personal_Email");
            header.getCell(2).setCellStyle(style);
            header.createCell(3).setCellValue("SF_Business_Email");
            header.getCell(3).setCellStyle(style);
//            header.createCell(4).setCellValue("Ithera_Business_Email");
//            header.getCell(4).setCellStyle(style);
            header.createCell(4).setCellValue("SF_UserID");
            header.getCell(4).setCellStyle(style);
            header.createCell(5).setCellValue("SF_Firstname");
            header.getCell(5).setCellStyle(style);
            header.createCell(6).setCellValue("Ithera_Firstname");
            header.getCell(6).setCellStyle(style);
            header.createCell(7).setCellValue("SF_LastName");
            header.getCell(7).setCellStyle(style);
            header.createCell(8).setCellValue("Ithera_LastName");
            header.getCell(8).setCellStyle(style);
            header.createCell(9).setCellValue("SF_StoreName");
            header.getCell(9).setCellStyle(style);
            header.createCell(10).setCellValue("Ithera_StoreName");
            header.getCell(10).setCellStyle(style);
            header.createCell(11).setCellValue("Sf_Address");
            header.getCell(11).setCellStyle(style);
            header.createCell(12).setCellValue("Ithera_Address");
            header.getCell(12).setCellStyle(style);
            header.createCell(13).setCellValue("SF_City");
            header.getCell(13).setCellStyle(style);
            header.createCell(14).setCellValue("Ithera_City");
            header.getCell(14).setCellStyle(style);
            header.createCell(15).setCellValue("SF_State");
            header.getCell(15).setCellStyle(style);
            header.createCell(16).setCellValue("Ithera_State");
            header.getCell(16).setCellStyle(style);
            header.createCell(17).setCellValue("SF_PostCode");
            header.getCell(17).setCellStyle(style);
            header.createCell(18).setCellValue("Ithera_PostCode");
            header.getCell(18).setCellStyle(style);
            header.createCell(19).setCellValue("SF Banner Group");
            header.getCell(19).setCellStyle(style);
             header.createCell(19).setCellValue("Ithera Banner Group");
            header.getCell(19).setCellStyle(style);
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
                    Tblaccount account = null;
                   // if(rowCount == 970)
                     account = userReportRepository.findByApiUserId(user.getUserId());
                    
                    if (account == null && user.getFirstName() != null && user.getLastName() != null ) {
                        if (email != null) {
                            account = userReportRepository.findByVUsernameAndVStatus(email, "active");
                        }

                        if (account != null) { // if user is found in ithera db the user is already present in the db
                            log.debug(".....................user found by email ................................. " + email);
                            foundByEmailUsers.add(user);
                            account.setApiUserId(user.getUserId());
                            buildExcelDocument(workbook, sheet, user, account, "PersonalEmailActive", rowCount);
                            foundByEmail++;
                        } else {
                            //  log.debug(".....................user not found by email ................................. " + email );

                            account = userReportRepository.findByVUsernameAndVStatus(businessEmail, "active");

                            if (account != null) {
                                log.debug(".....................user found by businessEmail ................................. " + businessEmail);
                                account.setApiUserId(user.getUserId());
                                foundByBusinessEmail++;
                                foundByBusinessEmailUsers.add(user);
                                buildExcelDocument(workbook, sheet, user, account, "BusinessEmailActive", rowCount);
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

                                    log.debug("PriceLine Store Exact Match " + user.getFirstName() + "......" + user.getLastName() + "......" + user.getEmail() + "......" + user.getLocation() + "......" + account.getVEmail());
                                    foundByPricelineStores++;
                                    foundByPricelineStoresUsers.add(user);
                                    account.setApiUserId(user.getUserId());
                                    buildExcelDocument(workbook, sheet, user, account, "NamesStoresMatchActive", rowCount);
                                } else {
                                    log.debug("Before NSPG check ");
                                    account = userReportRepository.findByVFirstNameAndVLastNameAndStore_Group_IdInAndVStatus(user.getFirstName(), user.getLastName(), groupIDs, "active");
                                    log.debug("After NSPG check ");
                                    if(account != null){
                                        log.debug("PriceLine Store but stores don't match  " + user.getFirstName() + "......" + user.getLastName() + "......" + user.getEmail() + "......" + user.getLocation() + "......" + account.getVEmail());
                                    //foundByPricelineStores++;
                                    foundInPriceLinseStores++;
                                    foundInPriceLinseStoresList.add(user);
                                    if(account.getApiUserId() != null){
                                    account.setApiUserId(user.getUserId());
                                    buildExcelDocument(workbook, sheet, user, account, "NamesStoresPriceLineGroup", rowCount);
                                    }else{
                                        buildExcelDocument(workbook, sheet, user, account, "NamesStoresPriceLineGroupPending", rowCount);
                                    }
                                    
                                    }else {
                                        if (storeName != null) {
                                        account = userReportRepository.findByVFirstNameAndVLastNameAndStore_storeNameAndStore_Group_IdNotInAndVStatus(user.getFirstName(), user.getLastName(), storeName, groupIDs, "active");
                                    }

                                    if (account != null) {
                                        account.setApiUserId(user.getUserId());
                                        foundInNonPriceLinseStoresUsers.add(user);
                                        log.debug(" Not a PriceLine Store " + user.getFirstName() + "......" + user.getLastName() + "......" + user.getEmail() + "......" + user.getLocation() + "......" + account.getVEmail());
                                        buildExcelDocument(workbook, sheet, user, account, "NameStorePricelineGroup", rowCount);
                                    } else {
                                        account = userReportRepository.findByAddress(user.getFirstName(), user.getLastName(), user.getAddressLine1(), user.getCity(), user.getState(), user.getZipCode());
                                        if (account != null) {
                                            foundInNonPriceLinseStores++;
                                            account.setApiUserId(user.getUserId());
                                            foundByAddressUsers.add(user);
                                            log.debug(" Not a PriceLine Store matched by address  " + user.getFirstName() + "......" + user.getLastName() + "......" + user.getEmail() + "......" + user.getLocation() + "......" + account.getVEmail());
                                            buildExcelDocument(workbook, sheet, user, account, "NamesMatchedNotPriceLineStore", rowCount);
                                        } else {
                                            account = userReportRepository.findByVFirstNameAndVLastNameAndVStatus(user.getFirstName(), user.getLastName(), "active");
                                            if (account != null) {
                                                account.setApiUserId(user.getUserId());
                                                foundByNameAloneUsers.add(user);
                                                foundByNameAlone++;
                                                log.debug("found by firstnmae last name only Not in the Store they suggest so a " + account.getVEmail() + " ..... account store" + account.getStore().getStoreName());
                                                buildExcelDocument(workbook, sheet, user, account, "NameMatchOther", rowCount);
                                            } else {
                                                notFountCount++;
                                                notFoundUsers.add(user);
                                                buildExcelDocument(workbook, sheet, user, account, "NewSuccessFactorsActive", rowCount);
                                            }

                                        }

                                    }
                                    }
                                }
                            }
                        }
                    } else {
                        blankUsers++;
                        blankUsersList.add(user);
                        log.debug("Either Email is ..... " + email + "or firstname is ...... " + user.getFirstName() + "or lastName is .... " + user.getLastName() + "............userId....." + user.getUserId());
                         buildExcelDocument(workbook, sheet, user, account, "Nulled First Name or Last Name", rowCount);
                    }
                    long looptime2 = System.currentTimeMillis();
                    long loopTimeTaken = (looptime2 - looptime1) / 1000;
                    long loopTimeTakenSfar = (looptime2 - time1) / 1000;
                    log.debug("Total Time Taken by this iteration is ........................" + loopTimeTaken);
                    log.debug("Total Time Taken so far is ........................" + loopTimeTakenSfar);
                    log.debug(".....................users processedCount ................................. " + processedCount);
                    log.debug("The Stats for the iteration ..... foundByEmail=" + foundByEmail + "........ foundByBusinessEmail=" + foundByBusinessEmail + ".......foundByPricelineStores=" + foundByPricelineStores + "..........foundInNonPriceLinseStores" + foundInNonPriceLinseStores + "............foundByNameAlone=" + foundByNameAlone + ".......blankUsers" + blankUsers + "..................notFountCount" + notFountCount);
                    rowCount++;
                }
                
            }
            File aFile;
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
            aFile = new File("E:\\retrieveUserInfo\\FileFromSF" + "log" + dt1.format(calendar.getTime()) + ".xlsx");
            log.debug("afile is ..................." + aFile);
            outputStream = new FileOutputStream(aFile);
            workbook.write(outputStream);
            workbook.close();
            log.debug("The Stats for the day ..... foundByEmail=" + foundByEmail + "........ foundByBusinessEmail=" + foundByBusinessEmail + ".......foundByPricelineStores=" + foundByPricelineStores + "..........foundInNonPriceLinseStores" + foundInNonPriceLinseStores + "............foundByNameAlone=" + foundByNameAlone + ".......blankUsers" + blankUsers + "..................notFountCount" + notFountCount);
            long time2 = System.currentTimeMillis();
            long timeTaken = (time2 - time1) / 1000;
            log.debug("Total Time Taken by this prgram is " + timeTaken);
//            for (User fbe : foundByEmailUsers) {
//                log.debug(" Found by Email Users ............ " + fbe);
//            }
//            for (User fbbe : foundByBusinessEmailUsers) {
//                log.debug(" Found by business Email Users ............ " + fbbe);
//            }
//            for (User fbps : foundByPricelineStoresUsers) {
//                log.debug(" Found by Pricline Stores Users ............ " + fbps);
//            }
//            for (User fbnps : foundInNonPriceLinseStoresUsers) {
//                log.debug(" Found by NonPriceLise Stores Users ............ " + fbnps);
//            }
//            for (User fba : foundByAddressUsers) {
//                log.debug(" Found by Address Users ............ " + fba);
//            }
//            for (User fbn : foundByNameAloneUsers) {
//                log.debug(" Found by Name Alone Users ............ " + fbn);
//            }
//            for (User nf : notFoundUsers) {
//                log.debug(" Not Found  Users ............ " + nf);
//            }
//            for (User blank : blankUsersList) {
//                log.debug(" Blank first name or last name  Users ............ " + blank);
//            }
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProcessDataFromSFServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ProcessDataFromSFServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                outputStream.close();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(ProcessDataFromSFServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void buildExcelDocument(Workbook workbook, Sheet sheet, User user, Tblaccount account, String syncStatus, Integer rowCount) {
        log.debug(" What we have on excel sheet herer ............ " + sheet + "   ...............Syncstatus" + syncStatus + "row count ....." +  rowCount);
        // change the file name
        //response.setHeader("Content-Disposition", "attachment; filename=\"my-xls-file.xls\"");
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
//    header.createCell(8).setCellValue("Phone Number");
//    header.getCell(8).setCellStyle(style);
        //  int rowCount = 1;

        // for(User user : users){
        Row userRow = sheet.createRow(rowCount);
        try{
         log.debug(" What we have on excel sheet herer userrow ............ " + userRow );
//        userRow.createCell(0).setCellValue(user.getFirstName());
//        userRow.createCell(1).setCellValue(user.getLastName());
//        userRow.createCell(2).setCellValue(user.getEmail());
//        userRow.createCell(3).setCellValue(user.getJobTitle());
//        userRow.createCell(4).setCellValue(user.getCustom02());
//        userRow.createCell(5).setCellValue(user.getAddressLine1());
//        userRow.createCell(6).setCellValue(user.getCity());
//        userRow.createCell(7).setCellValue(user.getCountry());
//        userRow.createCell(8).setCellValue(user.getLocation());
//syncStatus = null;
        userRow.createCell(0).setCellValue(syncStatus);
        log.debug(" after sync status ............ " );
        userRow.createCell(1).setCellValue(user.getEmail());
        
        if(account!=null){
        userRow.createCell(2).setCellValue(account.getVUsername());
        }else{
            userRow.createCell(2).setCellValue("");
        }
        log.debug(" after email  ............ " );
        userRow.createCell(3).setCellValue(user.getCustom12());//business email
        
//        if(account!=null){
//        userRow.createCell(4).setCellValue(account.getVUsername());
//        }else{
//            userRow.createCell(4).setCellValue("");
//        }
        userRow.createCell(4).setCellValue(user.getUserId());
        log.debug(" after buisenss emil  ............ " );
        userRow.createCell(5).setCellValue(user.getFirstName());
        if(account!=null){
        userRow.createCell(6).setCellValue(account.getVFirstName());
        }else{
            userRow.createCell(6).setCellValue("");
        }
        log.debug(" after firstname  ............ " );
        userRow.createCell(7).setCellValue(user.getLastName());
        if(account!=null){
        userRow.createCell(8).setCellValue(account.getVLastName());
        }else{
            userRow.createCell(8).setCellValue("");
        }
        log.debug(" after last name  ............ " );
        userRow.createCell(9).setCellValue(user.getLocation());
        if(account!=null && account.getStore() != null ){
        userRow.createCell(10).setCellValue(account.getStore().getStoreName());
        }else{
            userRow.createCell(10).setCellValue("");
        }
        log.debug(" after storename  ............ " );
        userRow.createCell(11).setCellValue(user.getAddressLine1());
        if(account!=null && account.getStore()!= null){
        String loc2 = account.getStore().getvLocAddress2();
        if (loc2 == null) {
            loc2 = "";
        }
        
        userRow.createCell(12).setCellValue(account.getStore().getvLocAddress1() + loc2);
        }else{
            userRow.createCell(12).setCellValue("");
        }
        log.debug(" after address  ............ " );
        userRow.createCell(13).setCellValue(user.getCity());
        if(account!=null && account.getStore()!= null){
        userRow.createCell(14).setCellValue(account.getStore().getvLocLocality1());
        }else{
            userRow.createCell(14).setCellValue("");
        }
        log.debug(" after City  ............ " );
        userRow.createCell(15).setCellValue(user.getState());
        if(account!=null && account.getStore()!= null){
        userRow.createCell(16).setCellValue(account.getStore().getStoreRegion());
        }else{
            userRow.createCell(16).setCellValue("");
        }
        log.debug(" after region   ............ " );
        userRow.createCell(17).setCellValue(user.getZipCode());
        if(account!=null && account.getStore()!= null ){
        userRow.createCell(18).setCellValue(account.getStore().getPostCode());
        }else{
            userRow.createCell(18).setCellValue("");
        }
         userRow.createCell(19).setCellValue(user.getCustom02());
         if(account!=null && account.getStore()!= null && account.getStore().getGroup()!= null  ){
          userRow.createCell(19).setCellValue(account.getStore().getGroup().getvName());
         }
          userRow.createCell(19).setCellValue("");
        log.debug(" after postcode  ............ " );
        }catch(Exception e){
            log.debug("some error occurred whle adding row to the sheet" +  e);
            e.printStackTrace();
        }

        //   }
    }

    public static void main(String args[]) throws Exception {

        ProcessDataFromSFServiceImpl processDataFromSFServiceImpl = new ProcessDataFromSFServiceImpl(null);
        // processDataFromSFServiceImpl.buildExcelDocument();

    }

}
