/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.api.retrieveUserInfo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unityhealth.api.retrieveUserInfo.connect.XMLApiBean;
import com.unityhealth.api.retrieveUserInfo.domain.IUserReportRepository;
import com.unityhealth.api.retrieveUserInfo.domain.Tblaccount;
import com.unityhealth.api.retrieveUserInfo.sf.userModel.Dummy;
import com.unityhealth.api.retrieveUserInfo.sf.userModel.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Sameer S Argade
 */
@Service

public class ProcessDataFromSFServiceImpl implements ProcessDataFromSFService {

    private Logger log = LoggerFactory.getLogger(ProcessDataFromSFServiceImpl.class);

    @Autowired
    private IUserReportRepository userReportRepository;
    @Autowired
    private Environment env;
    ProcessDataFromSFServiceImpl(IUserReportRepository userReportRepository) {
        this.userReportRepository = userReportRepository;
    }
    
    @Override
    @Transactional
    public void validateUsersLit(String jsonFileName, String excelFileName) {
        FileOutputStream outputStream = null;
        List<Dummy> dataList = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            //file = new File("E:\\biocueticalsload\\product.json");
           String jsonFilePath =  env.getProperty("jsonFilePath");
            File file = new File(jsonFilePath + jsonFileName);
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
            header.createCell(20).setCellValue("Ithera Banner Group");
            header.getCell(20).setCellStyle(style);
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

                    if (account == null && user.getFirstName() != null && user.getLastName() != null) {
                        if (email != null) {
                            account = userReportRepository.findByVUsernameAndVStatus(email, "active");
                        }

                        if (account != null) { // if user is found in ithera db the user is already present in the db
                            log.debug(".....................user found by email ................................. " + email);
                            foundByEmailUsers.add(user);
                            account.setApiUserId(user.getUserId());
                            // account.setVFirstName(user.getFirstName());
                            // account.setVLastName(user.getLastName());
                            //account.setVEmail(email);
                            account.setApiEmailPersonal(email);
                            //account.setApiEmailBusiness(email);
                            account.setApiSyncStatus("EMA");
                            account.setVStatus("Active");
                            account.setApiUserId(user.getUserId());
                            account.setApiUserIDActive(1);
                            account.setApiUserIDPending(0);
                            //account.setDtJoinDate(new Date());
                            account.setApiLastUpdated(new Date());
                            //account.setVUsername(email);
                            buildExcelDocument(workbook, sheet, user, account, "PersonalEmailActive", rowCount);
                            foundByEmail++;
                        } else {
                            //  log.debug(".....................user not found by email ................................. " + email );

                            account = userReportRepository.findByVUsernameAndVStatus(businessEmail, "active");

                            if (account != null) {
                                log.debug(".....................user found by businessEmail ................................. " + businessEmail);
                                account.setApiUserId(user.getUserId());
                                // account.setVFirstName(user.getFirstName());
                                // account.setVLastName(user.getLastName());
                                //account.setVEmail(email);
                                //account.setApiEmailPersonal(email);
                                account.setApiEmailBusiness(email);
                                account.setApiSyncStatus("EMB");
                                account.setVStatus("Active");
                                account.setApiUserId(user.getUserId());
                                account.setApiUserIDActive(1);
                                account.setApiUserIDPending(0);
                                //account.setDtJoinDate(new Date());
                                account.setApiLastUpdated(new Date());
                                //account.setVUsername(email);
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
                                List <Tblaccount> accountsByFNameLNameStore = null;
                                if (storeName != null) {
                                    accountsByFNameLNameStore = userReportRepository.findByVFirstNameAndVLastNameAndStore_storeNameAndStore_Group_IdInAndVStatus(user.getFirstName(), user.getLastName(), storeName, groupIDs, "active");
                                }
                                if (accountsByFNameLNameStore != null && accountsByFNameLNameStore.size()>0) {
                                    boolean singleStoreAccount = false;
                                    if(accountsByFNameLNameStore.size()> 1){
                                        singleStoreAccount = false;
                                    }else{
                                        singleStoreAccount = true;
                                    }
                                    for(Tblaccount storeAccount:accountsByFNameLNameStore){
                                    log.debug("PriceLine Store Exact Match " + user.getFirstName() + "......" + user.getLastName() + "......" + user.getEmail() + "......" + user.getLocation() + "......" + storeAccount.getVEmail());
                                    foundByPricelineStores++;
                                    foundByPricelineStoresUsers.add(user);
                                    storeAccount.setApiUserId(user.getUserId());
                                    // account.setVFirstName(user.getFirstName());
                                    // account.setVLastName(user.getLastName());
                                    //account.setVEmail(email);
                                    storeAccount.setApiEmailPersonal(email);
                                    //account.setApiEmailBusiness(email);
                                    storeAccount.setApiSyncStatus("NSMS");
                                   // account.setVStatus("Active");
                                   if(singleStoreAccount){
                                    storeAccount.setApiTmpUserId(user.getUserId());
                                    storeAccount.setApiUserIDActive(0);
                                    storeAccount.setApiUserIDPending(1);
                                   }else{
                                    storeAccount.setApiUserId(user.getUserId());
                                    storeAccount.setApiUserIDActive(1);
                                    storeAccount.setApiUserIDPending(0);
                                   }
                                    //account.setDtJoinDate(new Date());
                                    storeAccount.setApiLastUpdated(new Date());
                                    //account.setVUsername(email);
                                     userReportRepository.save(storeAccount);
                                    buildExcelDocument(workbook, sheet, user, storeAccount, "NamesStoresMatchActive", rowCount++);
                                    }
                                } else {
                                    log.debug("Before NSPG check ");
                                    account = userReportRepository.findByVFirstNameAndVLastNameAndStore_Group_IdInAndVStatus(user.getFirstName(), user.getLastName(), groupIDs, "active");
                                    log.debug("After NSPG check ");
                                    if (account != null) {
                                        log.debug("PriceLine Store but stores don't match  " + user.getFirstName() + "......" + user.getLastName() + "......" + user.getEmail() + "......" + user.getLocation() + "......" + account.getVEmail());
                                        //foundByPricelineStores++;
                                        foundInPriceLinseStores++;
                                        foundInPriceLinseStoresList.add(user);

                                        account.setApiUserId(user.getUserId());
                                        // account.setVFirstName(user.getFirstName());
                                        // account.setVLastName(user.getLastName());
                                        //account.setVEmail(email);
                                        account.setApiEmailPersonal(email);
                                        //account.setApiEmailBusiness(email);
                                        account.setApiSyncStatus("NSPG");
                                        account.setVStatus("Active");
                                        account.setApiUserId(user.getUserId());
                                        account.setApiUserIDActive(0);
                                        account.setApiUserIDPending(1);
                                        //account.setDtJoinDate(new Date());
                                        account.setApiLastUpdated(new Date());
                                        //account.setVUsername(email);

                                        if (account.getApiUserId() != null) {
                                            account.setApiUserId(user.getUserId());
                                            buildExcelDocument(workbook, sheet, user, account, "NamesStoresPriceLineGroupPending", rowCount);
                                        } else {
                                            buildExcelDocument(workbook, sheet, user, account, "NamesStoresPriceLineGroupPending", rowCount);
                                        }

                                    } else {
                                        log.debug("Before Store Check ");
                                        if (storeName != null) {
                                            account = userReportRepository.findByVFirstNameAndVLastNameAndStore_storeNameAndStore_Group_IdNotInAndVStatus(user.getFirstName(), user.getLastName(), storeName, groupIDs, "active");
                                        }
                                        log.debug("After Store Check ");
                                        if (account != null) {
                                            log.debug("In not a proceline Store Check ");
                                            account.setApiUserId(user.getUserId());
                                            // account.setVFirstName(user.getFirstName());
                                            // account.setVLastName(user.getLastName());
                                            //account.setVEmail(email);
                                            account.setApiEmailPersonal(email);
                                            //account.setApiEmailBusiness(email);
                                            account.setApiSyncStatus("NSNPG");
                                            account.setVStatus("Pending");
                                            account.setApiUserId(user.getUserId());
                                            account.setApiUserIDActive(1);
                                            account.setApiUserIDPending(0);
                                            //account.setDtJoinDate(new Date());
                                            account.setApiLastUpdated(new Date());
                                            //account.setVUsername(email);
                                            foundInNonPriceLinseStoresUsers.add(user);
                                            log.debug(" Not a PriceLine Store " + user.getFirstName() + "......" + user.getLastName() + "......" + user.getEmail() + "......" + user.getLocation() + "......" + account.getVEmail());
                                            buildExcelDocument(workbook, sheet, user, account, "NameStorePricelineGroupPending", rowCount);
                                        } else {
                                            log.debug("get by address Check ");
                                            account = userReportRepository.findByAddress(user.getFirstName(), user.getLastName(), user.getAddressLine1(), user.getCity(), user.getState(), user.getZipCode());

                                            if (account != null) {
                                                foundInNonPriceLinseStores++;
                                                account.setApiUserId(user.getUserId());
                                                // account.setVFirstName(user.getFirstName());
                                                // account.setVLastName(user.getLastName());
                                                //account.setVEmail(email);
                                                account.setApiEmailPersonal(email);
                                                //account.setApiEmailBusiness(email);
                                                account.setApiSyncStatus("NMNPG");
                                                account.setVStatus("PENDING");
                                                account.setApiUserId(user.getUserId());
                                                account.setApiUserIDActive(1);
                                                account.setApiUserIDPending(0);
                                                //account.setDtJoinDate(new Date());
                                                account.setApiLastUpdated(new Date());
                                                //account.setVUsername(email);
                                                foundByAddressUsers.add(user);
                                                log.debug(" Not a PriceLine Store matched by address  " + user.getFirstName() + "......" + user.getLastName() + "......" + user.getEmail() + "......" + user.getLocation() + "......" + account.getVEmail());
                                                buildExcelDocument(workbook, sheet, user, account, "NamesMatchedNotPriceLineStorePending", rowCount);
                                            } else {
                                                //  log.debug("only firstname lastt name check " + user.toString());
                                                log.debug(" only firstname lastt name check  " + user.getFirstName() + "......" + user.getLastName() + "......" + user.getEmail() + "......" + user.getLocation() + "......");
                                                List<Tblaccount> accounts = userReportRepository.findByVFirstNameAndVLastNameAndVStatus(user.getFirstName(), user.getLastName(), "active");
                                                log.debug("After only firstname lastt name check " + accounts);

                                                if (accounts != null && accounts.size() > 0) {
                                                    log.debug("acounts not null " + accounts.size());
                                                    for (Tblaccount singleaccount : accounts) {
                                                        log.debug("how about this acount  " + singleaccount);
                                                        singleaccount.setApiUserId(user.getUserId());
                                                        // account.setVFirstName(user.getFirstName());
                                                        // account.setVLastName(user.getLastName());
                                                        //account.setVEmail(email);
                                                        singleaccount.setApiEmailPersonal(email);
                                                        //account.setApiEmailBusiness(email);
                                                        singleaccount.setApiSyncStatus("NMO");
                                                        //singleaccount.setVStatus("Active");
                                                        if(accounts.size()==1){
                                                        singleaccount.setApiUserId(user.getUserId());
                                                        }else{
                                                            singleaccount.setApiTmpUserId(user.getUserId());
                                                        }
                                                        singleaccount.setApiUserIDActive(0);
                                                        singleaccount.setApiUserIDPending(1);
                                                        //account.setDtJoinDate(new Date());
                                                        singleaccount.setApiLastUpdated(new Date());
                                                        //account.setVUsername(email);
                                                        log.debug("so is the userid uopdated?..  " + singleaccount.getApiUserId());
                                                        log.debug("Is the list null?..  " + foundByNameAloneUsers + "...... foundByNameAlone value " + foundByNameAlone);
                                                        foundByNameAloneUsers.add(user);
                                                        log.debug("Added to the list as well..  ");
                                                        foundByNameAlone++;
                                                      //  log.debug("found by firstnmae last name only Not in the Store they suggest so a " + singleaccount.getVEmail() + " ..... account store" + singleaccount.getStore().getStoreName());
                                                        buildExcelDocument(workbook, sheet, user, singleaccount, "NameMatchOtherPending", rowCount++);
                                                        userReportRepository.save(singleaccount);
                                                    }
                                                } else {
                                                    notFountCount++;
                                                    notFoundUsers.add(user);
                                                    buildExcelDocument(workbook, sheet, user, account, "NewSuccessFactorsActive", rowCount);
                                                    if (account == null) {
                                                        account = new Tblaccount();
                                                        account.setVFirstName(user.getFirstName());
                                                        account.setVLastName(user.getLastName());
                                                        if (email != null) {
                                                            account.setVEmail(email);
                                                            account.setApiEmailPersonal(email);
                                                            account.setApiEmailBusiness(email);
                                                            account.setVUsername(email);
                                                        } else {
                                                            account.setVEmail("");
                                                            account.setApiEmailPersonal("");
                                                            account.setApiEmailBusiness("");
                                                            account.setVUsername("");
                                                        }
                                                        account.setApiSyncStatus("NSFA");
                                                        account.setVStatus("ACTIVE");
                                                        account.setApiUserId(user.getUserId());
                                                        account.setApiUserIDActive(1);
                                                        account.setApiUserIDPending(0);
                                                        account.setDtJoinDate(new Date());
                                                        account.setApiLastUpdated(new Date());
                                                        
                                                    }
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
                        if (account == null && email != null) {
                            account = new Tblaccount();
                            //if(user.getFirstName())
                            account.setVFirstName(user.getFirstName());
                            account.setVLastName(user.getLastName());
                            if (email != null) {
                                account.setVEmail(email);
                                account.setApiEmailPersonal(email);
                                account.setApiEmailBusiness(email);
                                account.setVUsername(email);
                            } else {
                                account.setVEmail("");
                                account.setApiEmailPersonal("");
                                account.setApiEmailBusiness("");
                                account.setVUsername("");
                            }
                            account.setApiSyncStatus("NFNOLN");
                            account.setVStatus("");
                            account.setApiUserId(user.getUserId());
                            account.setApiUserIDActive(0);
                            account.setApiUserIDPending(1);
                            account.setDtJoinDate(new Date());
                            account.setApiLastUpdated(new Date());

                        }

                    }
                    long looptime2 = System.currentTimeMillis();
                    long loopTimeTaken = (looptime2 - looptime1) / 1000;
                    long loopTimeTakenSfar = (looptime2 - time1) / 1000;
                    log.debug("Total Time Taken by this iteration is ........................" + loopTimeTaken);
                    log.debug("Total Time Taken so far is ........................" + loopTimeTakenSfar);
                    log.debug(".....................users processedCount ................................. " + processedCount);
                    log.debug("The Stats for the iteration ..... foundByEmail=" + foundByEmail + "........ foundByBusinessEmail=" + foundByBusinessEmail + ".......foundByPricelineStores=" + foundByPricelineStores + "..........foundInNonPriceLinseStores" + foundInNonPriceLinseStores + "............foundByNameAlone=" + foundByNameAlone + ".......blankUsers" + blankUsers + "..................notFountCount" + notFountCount);
                    rowCount++;
                    if (account != null) {
                        userReportRepository.save(account);
                    }
                }

            }
            File aFile;
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
            //String jsonFilePath =  env.getProperty("jsonFilePath");
            aFile = new File(jsonFilePath + excelFileName + "log" + dt1.format(calendar.getTime()) + ".xlsx");
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(ProcessDataFromSFServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void buildExcelDocument(Workbook workbook, Sheet sheet, User user, Tblaccount account, String syncStatus, Integer rowCount) {
        log.debug(" What we have on excel sheet herer ............ " + sheet + "   ...............Syncstatus" + syncStatus + "row count ....." + rowCount);
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
        try {
            log.debug(" What we have on excel sheet herer userrow ............ " + userRow);
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
            log.debug(" after sync status ............ ");
            userRow.createCell(1).setCellValue(user.getEmail());

            if (account != null) {
                userRow.createCell(2).setCellValue(account.getVUsername());
            } else {
                userRow.createCell(2).setCellValue("");
            }
            log.debug(" after email  ............ ");
            userRow.createCell(3).setCellValue(user.getCustom12());//business email

//        if(account!=null){
//        userRow.createCell(4).setCellValue(account.getVUsername());
//        }else{
//            userRow.createCell(4).setCellValue("");
//        }
            userRow.createCell(4).setCellValue(user.getUserId());
            log.debug(" after buisenss emil  ............ ");
            userRow.createCell(5).setCellValue(user.getFirstName());
            if (account != null) {
                userRow.createCell(6).setCellValue(account.getVFirstName());
            } else {
                userRow.createCell(6).setCellValue("");
            }
            log.debug(" after firstname  ............ ");
            userRow.createCell(7).setCellValue(user.getLastName());
            if (account != null) {
                userRow.createCell(8).setCellValue(account.getVLastName());
            } else {
                userRow.createCell(8).setCellValue("");
            }
            log.debug(" after last name  ............ ");
            userRow.createCell(9).setCellValue(user.getLocation());
            if (account != null && account.getStore() != null) {
                userRow.createCell(10).setCellValue(account.getStore().getStoreName());
            } else {
                userRow.createCell(10).setCellValue("");
            }
            log.debug(" after storename  ............ ");
            userRow.createCell(11).setCellValue(user.getAddressLine1());
            if (account != null && account.getStore() != null) {
                String loc2 = account.getStore().getvLocAddress2();
                if (loc2 == null) {
                    loc2 = "";
                }

                userRow.createCell(12).setCellValue(account.getStore().getvLocAddress1() + loc2);
            } else {
                userRow.createCell(12).setCellValue("");
            }
            log.debug(" after address  ............ ");
            userRow.createCell(13).setCellValue(user.getCity());
            if (account != null && account.getStore() != null) {
                userRow.createCell(14).setCellValue(account.getStore().getvLocLocality1());
            } else {
                userRow.createCell(14).setCellValue("");
            }
            log.debug(" after City  ............ ");
            userRow.createCell(15).setCellValue(user.getState());
            if (account != null && account.getStore() != null) {
                userRow.createCell(16).setCellValue(account.getStore().getStoreRegion());
            } else {
                userRow.createCell(16).setCellValue("");
            }
            log.debug(" after region   ............ ");
            userRow.createCell(17).setCellValue(user.getZipCode());
            if (account != null && account.getStore() != null) {
                userRow.createCell(18).setCellValue(account.getStore().getPostCode());
            } else {
                userRow.createCell(18).setCellValue("");
            }
            userRow.createCell(19).setCellValue(user.getCustom02());
            if (account != null && account.getStore() != null && account.getStore().getGroup() != null) {
                userRow.createCell(20).setCellValue(account.getStore().getGroup().getvName());
            } else {
                userRow.createCell(20).setCellValue("");
            }
            log.debug(" after postcode  ............ ");
        } catch (Exception e) {
            log.debug("some error occurred whle adding row to the sheet" + e);
            e.printStackTrace();
        }

        //   }
    }

    public static void main(String args[]) throws Exception {

        //ProcessDataFromSFServiceImpl processDataFromSFServiceImpl = new ProcessDataFromSFServiceImpl(null);
        // processDataFromSFServiceImpl.buildExcelDocument();
        String vurl = "https://connect.itherapeutics.com.au/p644jwda2o6/";
        // vurl =  vurl.substring(http://connect.itherapeutics.com.au/, 0);
        vurl = vurl.replace("https://connect.itherapeutics.com.au/", "");
//           log.debug(vurl);

        System.out.println(vurl);
    }
    
    public String joinUser( Tblaccount in) {
        log.debug("----------------------------------Inside Join User---------------------------------------------------------- " );
           
            String userId = null;
try {
            String ptPass = getRandomPassword();

          // System.out.println(env.getProperty("adobeConnect") + " " + env.getProperty("adobeConnectAdmin") + " " + env.getProperty("adobeConnectAdminPassword"));
      
           XMLApiBean breezeAdmin =  new XMLApiBean(env.getProperty("adobeConnect"), env.getProperty("adobeConnectAdmin"), env.getProperty("adobeConnectAdminPassword"), null);

                String adminSession = breezeAdmin.getBreezesession();
                System.out.println(adminSession);
                com.unityhealth.api.retrieveUserInfo.connect.User breezUser = null;
                if(in.getVUsername() != null && !in.getVUsername().equals(""))
               breezUser = breezeAdmin.getUser(in.getVUsername());
               if(breezUser != null){
                   userId = breezUser.getPrincipalID();
                 //  System.out.println(userId + " userId " + in.getVUsername());
               }else{
                    if(in.getVUsername() != null && !in.getVUsername().equals("")){
                userId = breezeAdmin.registerUser(in.getVFirstName(), in.getVLastName(), in.getVUsername(), encodeURL(ptPass), in.getVUsername());
                 breezeAdmin.addUserToGroup(userId, env.getProperty("breezeLearnersGroupSCO"));
                    }
               }
                if(in.getVUsername() != null && !in.getVUsername().equals(""))
                if( in.getIBreezeUserID() == null){
         in.setIBreezeUserID(Integer.parseInt(userId));
         //in.set
         in.setApiUserPass(ptPass);
         String salt = getSalt();
                    String newPassword = get_SHA_SecurePassword(ptPass, salt);
                    in.setVPassword(newPassword);
         userReportRepository.save(in);
         }
                //System.out.println(userId + " userId " + in.getVUsername());
               
        log.debug("---------------------------------- " + userId + " userId " + in.getVUsername() + " ---------------------------------------------------------- " );
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("some error occurred while creatig api users..." +  e);
            
                  return "failure";
            
         
        }
        if(userId != null)
        return userId.toString();
        log.debug("UserID is null for userId........." + in.getApiUserId()  );
        return null;
    }
     
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

    @Override
    public void addNewUSersToBreeze() {
        log.debug("process to add new api users about to start");
        
       List<Tblaccount> newUsers = userReportRepository.findByApiSyncStatus("NSFA");
       log.debug("Number of new users to be added...." + newUsers.size());
       String userID="";
       Integer userCount = 0;
       for(Tblaccount newUser:newUsers){
          log.debug(" before userID newUser.getApiUserId() ..... " + "userName ...." + newUser.getVUsername());
         userID =  joinUser(newUser);
         log.debug(" after userID newUser.getApiUserId() ..... " + "userName ...." + newUser.getVUsername());
         log.debug(" breezeUser ID ..... " + userID);
        log.debug("user number processed " + ++userCount);
    }
       log.debug("total number processed " + ++userCount);
    }
}
