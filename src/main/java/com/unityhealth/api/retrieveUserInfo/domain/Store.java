package com.unityhealth.api.retrieveUserInfo.domain;


import java.util.Date;

import javax.persistence.*;

/**
 * Created by shanefox on 24/10/2016.
 */
@Entity
@Table(name = "tblstores")
public class Store {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "iID", columnDefinition = "UNSIGNED INT(10)")
    private Integer id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="iGroupID")
    private Group group;
    
    @Column(name = "vCodeIMSBrick")
    private String vCodeIMSBrick;
    @Column(name = "vCodeIMS")
    private String vCodeIMS;
    @Column(name = "vCodeDendrite")
    private String vCodeDendrite;
    @Column(name = "vCodeDendriteBrick")
    private String vCodeDendriteBrick;
   @Column(name="vLocName")
    private String storeName;
    @Column(name = "vLocAddress1")
    private String vLocAddress1;
    @Column(name = "vLocAddress2")
    private String vLocAddress2;
    @Column(name = "vLocLocality1")
    private String vLocLocality1;
    @Column(name="vLocRegion")
    private String storeRegion;
    @Column(name="vLocPostCode")
    private String postCode;
    @Column(name = "vPhoneNumber")
    private String vPhoneNumber;
    @Column(name = "vFaxNumber")
    private String vFaxNumber;
    @Column(name = "vPhoneNumberClean")
    private String vPhoneNumberClean;
    @Column(name = "vLocLocality2")
    private String vLocLocality2;
    @Column(name = "vLocCountry")
    private String vLocCountry;
    @Column(name = "vEmail")
    private String vEmail;
    @Column(name = "vABN")
    private String vABN;
    @Column(name = "vEntityType")
    private String vEntityType;
    @Column(name = "vEntityName")
    private String vEntityName;
    @Column(name = "vTradingName")
    private String vTradingName;
    @Column(name = "vBusName")
    private String vBusName;
    @Column(name = "vACN")
    private String vACN;
    @Column(name = "vOwners")
    private String vOwners;
    @Column(name = "vManagers")
    private String vManagers;
    @Column(name = "vProprietors")
    private String vProprietors;
    @Column(name = "vQCPP")
    private String vQCPP;
    @Column(name = "vHeadPharmacist")
    private String vHeadPharmacist;
    @Column(name = "iHeadPharmacistID")
    private Integer iHeadPharmacistID;
    @Column(name = "vAccountCode")
    private String vAccountCode;
    @Column(name = "iType")
    private Integer iType;
    @Column(name = "iNumStaff")
    private Integer iNumStaff;
    @Column(name = "dtCheck")
    @Temporal(TemporalType.DATE)
    private Date dtCheck;
    @Column(name = "vStatus")
    private String vStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * @return the vCodeIMSBrick
     */
    public String getvCodeIMSBrick() {
        return vCodeIMSBrick;
    }

    /**
     * @param vCodeIMSBrick the vCodeIMSBrick to set
     */
    public void setvCodeIMSBrick(String vCodeIMSBrick) {
        this.vCodeIMSBrick = vCodeIMSBrick;
    }

    /**
     * @return the vCodeIMS
     */
    public String getvCodeIMS() {
        return vCodeIMS;
    }

    /**
     * @param vCodeIMS the vCodeIMS to set
     */
    public void setvCodeIMS(String vCodeIMS) {
        this.vCodeIMS = vCodeIMS;
    }

    /**
     * @return the vCodeDendrite
     */
    public String getvCodeDendrite() {
        return vCodeDendrite;
    }

    /**
     * @param vCodeDendrite the vCodeDendrite to set
     */
    public void setvCodeDendrite(String vCodeDendrite) {
        this.vCodeDendrite = vCodeDendrite;
    }

    /**
     * @return the vCodeDendriteBrick
     */
    public String getvCodeDendriteBrick() {
        return vCodeDendriteBrick;
    }

    /**
     * @param vCodeDendriteBrick the vCodeDendriteBrick to set
     */
    public void setvCodeDendriteBrick(String vCodeDendriteBrick) {
        this.vCodeDendriteBrick = vCodeDendriteBrick;
    }

     public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreRegion() {
        return storeRegion;
    }

    public void setStoreRegion(String storeRegion) {
        this.storeRegion = storeRegion;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * @return the vLocAddress1
     */
    public String getvLocAddress1() {
        return vLocAddress1;
    }

    /**
     * @param vLocAddress1 the vLocAddress1 to set
     */
    public void setvLocAddress1(String vLocAddress1) {
        this.vLocAddress1 = vLocAddress1;
    }

    /**
     * @return the vLocAddress2
     */
    public String getvLocAddress2() {
        return vLocAddress2;
    }

    /**
     * @param vLocAddress2 the vLocAddress2 to set
     */
    public void setvLocAddress2(String vLocAddress2) {
        this.vLocAddress2 = vLocAddress2;
    }

    /**
     * @return the vLocLocality1
     */
    public String getvLocLocality1() {
        return vLocLocality1;
    }

    /**
     * @param vLocLocality1 the vLocLocality1 to set
     */
    public void setvLocLocality1(String vLocLocality1) {
        this.vLocLocality1 = vLocLocality1;
    }

   

    
    /**
     * @return the vPhoneNumber
     */
    public String getvPhoneNumber() {
        return vPhoneNumber;
    }

    /**
     * @param vPhoneNumber the vPhoneNumber to set
     */
    public void setvPhoneNumber(String vPhoneNumber) {
        this.vPhoneNumber = vPhoneNumber;
    }

    /**
     * @return the vFaxNumber
     */
    public String getvFaxNumber() {
        return vFaxNumber;
    }

    /**
     * @param vFaxNumber the vFaxNumber to set
     */
    public void setvFaxNumber(String vFaxNumber) {
        this.vFaxNumber = vFaxNumber;
    }

    /**
     * @return the vPhoneNumberClean
     */
    public String getvPhoneNumberClean() {
        return vPhoneNumberClean;
    }

    /**
     * @param vPhoneNumberClean the vPhoneNumberClean to set
     */
    public void setvPhoneNumberClean(String vPhoneNumberClean) {
        this.vPhoneNumberClean = vPhoneNumberClean;
    }

    /**
     * @return the vLocLocality2
     */
    public String getvLocLocality2() {
        return vLocLocality2;
    }

    /**
     * @param vLocLocality2 the vLocLocality2 to set
     */
    public void setvLocLocality2(String vLocLocality2) {
        this.vLocLocality2 = vLocLocality2;
    }

    /**
     * @return the vLocCountry
     */
    public String getvLocCountry() {
        return vLocCountry;
    }

    /**
     * @param vLocCountry the vLocCountry to set
     */
    public void setvLocCountry(String vLocCountry) {
        this.vLocCountry = vLocCountry;
    }

    /**
     * @return the vEmail
     */
    public String getvEmail() {
        return vEmail;
    }

    /**
     * @param vEmail the vEmail to set
     */
    public void setvEmail(String vEmail) {
        this.vEmail = vEmail;
    }

    /**
     * @return the vABN
     */
    public String getvABN() {
        return vABN;
    }

    /**
     * @param vABN the vABN to set
     */
    public void setvABN(String vABN) {
        this.vABN = vABN;
    }

    /**
     * @return the vEntityType
     */
    public String getvEntityType() {
        return vEntityType;
    }

    /**
     * @param vEntityType the vEntityType to set
     */
    public void setvEntityType(String vEntityType) {
        this.vEntityType = vEntityType;
    }

    /**
     * @return the vEntityName
     */
    public String getvEntityName() {
        return vEntityName;
    }

    /**
     * @param vEntityName the vEntityName to set
     */
    public void setvEntityName(String vEntityName) {
        this.vEntityName = vEntityName;
    }

    /**
     * @return the vTradingName
     */
    public String getvTradingName() {
        return vTradingName;
    }

    /**
     * @param vTradingName the vTradingName to set
     */
    public void setvTradingName(String vTradingName) {
        this.vTradingName = vTradingName;
    }

    /**
     * @return the vBusName
     */
    public String getvBusName() {
        return vBusName;
    }

    /**
     * @param vBusName the vBusName to set
     */
    public void setvBusName(String vBusName) {
        this.vBusName = vBusName;
    }

    /**
     * @return the vACN
     */
    public String getvACN() {
        return vACN;
    }

    /**
     * @param vACN the vACN to set
     */
    public void setvACN(String vACN) {
        this.vACN = vACN;
    }

    /**
     * @return the vOwners
     */
    public String getvOwners() {
        return vOwners;
    }

    /**
     * @param vOwners the vOwners to set
     */
    public void setvOwners(String vOwners) {
        this.vOwners = vOwners;
    }

    /**
     * @return the vManagers
     */
    public String getvManagers() {
        return vManagers;
    }

    /**
     * @param vManagers the vManagers to set
     */
    public void setvManagers(String vManagers) {
        this.vManagers = vManagers;
    }

    /**
     * @return the vProprietors
     */
    public String getvProprietors() {
        return vProprietors;
    }

    /**
     * @param vProprietors the vProprietors to set
     */
    public void setvProprietors(String vProprietors) {
        this.vProprietors = vProprietors;
    }

    /**
     * @return the vQCPP
     */
    public String getvQCPP() {
        return vQCPP;
    }

    /**
     * @param vQCPP the vQCPP to set
     */
    public void setvQCPP(String vQCPP) {
        this.vQCPP = vQCPP;
    }

    /**
     * @return the vHeadPharmacist
     */
    public String getvHeadPharmacist() {
        return vHeadPharmacist;
    }

    /**
     * @param vHeadPharmacist the vHeadPharmacist to set
     */
    public void setvHeadPharmacist(String vHeadPharmacist) {
        this.vHeadPharmacist = vHeadPharmacist;
    }

    /**
     * @return the iHeadPharmacistID
     */
    public Integer getiHeadPharmacistID() {
        return iHeadPharmacistID;
    }

    /**
     * @param iHeadPharmacistID the iHeadPharmacistID to set
     */
    public void setiHeadPharmacistID(Integer iHeadPharmacistID) {
        this.iHeadPharmacistID = iHeadPharmacistID;
    }

    /**
     * @return the vAccountCode
     */
    public String getvAccountCode() {
        return vAccountCode;
    }

    /**
     * @param vAccountCode the vAccountCode to set
     */
    public void setvAccountCode(String vAccountCode) {
        this.vAccountCode = vAccountCode;
    }

    /**
     * @return the iType
     */
    public Integer getiType() {
        return iType;
    }

    /**
     * @param iType the iType to set
     */
    public void setiType(Integer iType) {
        this.iType = iType;
    }

    /**
     * @return the iNumStaff
     */
    public Integer getiNumStaff() {
        return iNumStaff;
    }

    /**
     * @param iNumStaff the iNumStaff to set
     */
    public void setiNumStaff(Integer iNumStaff) {
        this.iNumStaff = iNumStaff;
    }

    /**
     * @return the dtCheck
     */
    public Date getDtCheck() {
        return dtCheck;
    }

    /**
     * @param dtCheck the dtCheck to set
     */
    public void setDtCheck(Date dtCheck) {
        this.dtCheck = dtCheck;
    }

    /**
     * @return the vStatus
     */
    public String getvStatus() {
        return vStatus;
    }

    /**
     * @param vStatus the vStatus to set
     */
    public void setvStatus(String vStatus) {
        this.vStatus = vStatus;
    }
}
