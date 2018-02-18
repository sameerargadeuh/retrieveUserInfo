/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.api.retrieveUserInfo.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Where;

/**
 *
 * @author Sameer S Argade
 */
@Entity
@Table(name = "tblaccount")
@NamedQueries({
    @NamedQuery(name = "Tblaccount.findAll", query = "SELECT t FROM Tblaccount t")})
public class Tblaccount implements Serializable {

    /**
     * @return the apiUserPass
     */
    public String getApiUserPass() {
        return apiUserPass;
    }

    /**
     * @param apiUserPass the apiUserPass to set
     */
    public void setApiUserPass(String apiUserPass) {
        this.apiUserPass = apiUserPass;
    }

    /**
     * @return the apiTmpUserId
     */
    public String getApiTmpUserId() {
        return apiTmpUserId;
    }

    /**
     * @param apiTmpUserId the apiTmpUserId to set
     */
    public void setApiTmpUserId(String apiTmpUserId) {
        this.apiTmpUserId = apiTmpUserId;
    }

    /**
     * @return the apiEmailPersonal
     */
    public String getApiEmailPersonal() {
        return apiEmailPersonal;
    }

    /**
     * @param apiEmailPersonal the apiEmailPersonal to set
     */
    public void setApiEmailPersonal(String apiEmailPersonal) {
        this.apiEmailPersonal = apiEmailPersonal;
    }

    /**
     * @return the apiEmailBusiness
     */
    public String getApiEmailBusiness() {
        return apiEmailBusiness;
    }

    /**
     * @param apiEmailBusiness the apiEmailBusiness to set
     */
    public void setApiEmailBusiness(String apiEmailBusiness) {
        this.apiEmailBusiness = apiEmailBusiness;
    }

    /**
     * @return the apiUserIDActive
     */
    public Integer getApiUserIDActive() {
        return apiUserIDActive;
    }

    /**
     * @param apiUserIDActive the apiUserIDActive to set
     */
    public void setApiUserIDActive(Integer apiUserIDActive) {
        this.apiUserIDActive = apiUserIDActive;
    }

    /**
     * @return the apiUserIDPending
     */
    public Integer getApiUserIDPending() {
        return apiUserIDPending;
    }

    /**
     * @param apiUserIDPending the apiUserIDPending to set
     */
    public void setApiUserIDPending(Integer apiUserIDPending) {
        this.apiUserIDPending = apiUserIDPending;
    }

    /**
     * @return the apiLastUpdated
     */
    public Date getApiLastUpdated() {
        return apiLastUpdated;
    }

    /**
     * @param apiLastUpdated the apiLastUpdated to set
     */
    public void setApiLastUpdated(Date apiLastUpdated) {
        this.apiLastUpdated = apiLastUpdated;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iID")
    private Integer iID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iSite")
    private int iSite;
    
//    @Column(name = "iStoreID")
//    private Integer iStoreID;
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="iStoreID", columnDefinition = "INT(11) UNSIGNED")
    @Where(clause = "iStoreID != -1")
    private Store store;
    @Size(max = 16)
    @Column(name = "vAccountType")
    private String vAccountType;
    @Size(max = 16)
    @Column(name = "vUserType")
    private String vUserType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "vStatus")
    private String vStatus;
    @Column(name = "iBreezeUserID")
    private Integer iBreezeUserID;
    @Lob
    @Size(max = 65535)
    @Column(name = "vComments")
    private String vComments;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bUpdated")
    private int bUpdated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bReport")
    private int bReport;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dtJoinDate")
    @Temporal(TemporalType.DATE)
    private Date dtJoinDate;
    @Column(name = "dtValidTo")
    @Temporal(TemporalType.DATE)
    private Date dtValidTo;
    @Column(name = "dtConfirmDate")
    @Temporal(TemporalType.DATE)
    private Date dtConfirmDate;
    @Column(name = "dtAdminConfirm")
    @Temporal(TemporalType.DATE)
    private Date dtAdminConfirm;
    @Column(name = "iAdmin")
    private Integer iAdmin;
    @Size(max = 255)
    @Column(name = "vStoreManager")
    private String vStoreManager;
    @Size(max = 255)
    @Column(name = "vLogin")
    private String vLogin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "vEmail")
    private String vEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "vUsername")
    private String vUsername;
    @Size(max = 255)
    @Column(name = "vPassword")
    private String vPassword;
    @Size(max = 255)
    @Column(name = "vPasswordSalt")
    private String vPasswordSalt;
    
    @Column(name = "apiUserPass")
    private String apiUserPass;
    @Column(name = "dtPasswordUpdated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtPasswordUpdated;
    @Column(name = "dtUpdated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUpdated;
    @Size(max = 8)
    @Column(name = "vOptIn")
    private String vOptIn;
    @Column(name = "bOptIn")
    private Integer bOptIn;
    @Size(max = 8)
    @Column(name = "vAge")
    private String vAge;
    @Size(max = 32)
    @Column(name = "vTitle")
    private String vTitle;
    @Size(max = 64)
    @Column(name = "vFirstNameProper")
    private String vFirstNameProper;
    @Size(max = 64)
    @Column(name = "vFirstName")
    private String vFirstName;
    @Size(max = 64)
    @Column(name = "vMiddleName")
    private String vMiddleName;
    @Size(max = 255)
    @Column(name = "vPosition")
    private String vPosition;
    @Size(max = 64)
    @Column(name = "vLastName")
    private String vLastName;
    @Size(max = 16)
    @Column(name = "vPostnominals")
    private String vPostnominals;
    @Size(max = 64)
    @Column(name = "vPostition")
    private String vPostition;
    @Size(max = 32)
    @Column(name = "vProfession")
    private String vProfession;
    @Size(max = 128)
    @Column(name = "vProfessionOther")
    private String vProfessionOther;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bMedNumVerified")
    private int bMedNumVerified;
    @Size(max = 64)
    @Column(name = "vMedNumber")
    private String vMedNumber;
    @Size(max = 64)
    @Column(name = "vMedNumber2")
    private String vMedNumber2;
    @Column(name = "iGroupID")
    private Integer iGroupID;
    @Size(max = 255)
    @Column(name = "vGroup")
    private String vGroup;
    @Size(max = 255)
    @Column(name = "vLocName")
    private String vLocName;
    @Size(max = 255)
    @Column(name = "vLocAddress1")
    private String vLocAddress1;
    @Size(max = 255)
    @Column(name = "vLocAddress2")
    private String vLocAddress2;
    @Size(max = 64)
    @Column(name = "vLocLocality1")
    private String vLocLocality1;
    @Size(max = 64)
    @Column(name = "vLocLocality2")
    private String vLocLocality2;
    @Size(max = 64)
    @Column(name = "vLocRegion")
    private String vLocRegion;
    @Size(max = 16)
    @Column(name = "vLocPostCode")
    private String vLocPostCode;
    @Size(max = 64)
    @Column(name = "vLocCountry")
    private String vLocCountry;
    @Size(max = 64)
    @Column(name = "vPhoneNumber")
    private String vPhoneNumber;
    @Size(max = 64)
    @Column(name = "vMobileNumber")
    private String vMobileNumber;
    @Size(max = 64)
    @Column(name = "vFaxNumber")
    private String vFaxNumber;
    @Size(max = 64)
    @Column(name = "vReferSource")
    private String vReferSource;
    @Size(max = 64)
    @Column(name = "vReferSourceRep")
    private String vReferSourceRep;
    @Column(name = "bInterests")
    private Integer bInterests;
    @Column(name = "bBuyer")
    private Integer bBuyer;
    @Column(name = "bIsHeadPharmacist")
    private Integer bIsHeadPharmacist;
    @Column(name = "bIsProprietor")
    private Integer bIsProprietor;
    @Column(name = "iRewardPoints")
    private Integer iRewardPoints;
    @Column(name = "iRewardPointsTotal")
    private Integer iRewardPointsTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iRewardPointsUsed")
    private int iRewardPointsUsed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iRewardPointsBonus")
    private int iRewardPointsBonus;
    @Column(name = "dtReachedGold")
    @Temporal(TemporalType.DATE)
    private Date dtReachedGold;
    @Size(max = 64)
    @Column(name = "vExternalID")
    private String vExternalID;
    @Size(max = 64)
    @Column(name = "vExternalID2")
    private String vExternalID2;
    @Column(name = "bAgreeOptIn")
    private Integer bAgreeOptIn;
    @Column(name = "bSherpaOptIn")
    private Integer bSherpaOptIn;
    @Size(max = 255)
    @Column(name = "vEmailEDM")
    private String vEmailEDM;
    @Column(name = "bSherpaSurveyTaken")
    private Integer bSherpaSurveyTaken;
    @Column(name = "bSmsOptin")
    private Integer bSmsOptin;
     @Column(name = "apiUserId")
    private String apiUserId;
     @Column(name = "apiSyncStatus")
    private String apiSyncStatus;
      @Column(name = "apiEmailPersonal")
     private String apiEmailPersonal;
       @Column(name = "apiEmailBusiness")
     private String apiEmailBusiness;
        @Column(name = "apiUserIDActive")
     private Integer apiUserIDActive;
         @Column(name = "apiUserIDPending")
     private Integer apiUserIDPending;
          @Column(name = "apiLastUpdated")
     private Date apiLastUpdated;
          @Column(name="apiTmpUserId")
private String apiTmpUserId;

    public Tblaccount() {
    }

    public Tblaccount(Integer iID) {
        this.iID = iID;
    }

    public Tblaccount(Integer iID, int iSite, String vStatus, int bUpdated, int bReport, Date dtJoinDate, String vEmail, String vUsername, int bMedNumVerified, int iRewardPointsUsed, int iRewardPointsBonus) {
        this.iID = iID;
        this.iSite = iSite;
        this.vStatus = vStatus;
        this.bUpdated = bUpdated;
        this.bReport = bReport;
        this.dtJoinDate = dtJoinDate;
        this.vEmail = vEmail;
        this.vUsername = vUsername;
        this.bMedNumVerified = bMedNumVerified;
        this.iRewardPointsUsed = iRewardPointsUsed;
        this.iRewardPointsBonus = iRewardPointsBonus;
    }

    public Integer getIID() {
        return iID;
    }

    public void setIID(Integer iID) {
        this.iID = iID;
    }

    public int getISite() {
        return iSite;
    }

    public void setISite(int iSite) {
        this.iSite = iSite;
    }

//    public Integer getIStoreID() {
//        return iStoreID;
//    }
//
//    public void setIStoreID(Integer iStoreID) {
//        this.iStoreID = iStoreID;
//    }

    public String getVAccountType() {
        return vAccountType;
    }

    public void setVAccountType(String vAccountType) {
        this.vAccountType = vAccountType;
    }

    public String getVUserType() {
        return vUserType;
    }

    public void setVUserType(String vUserType) {
        this.vUserType = vUserType;
    }

    public String getVStatus() {
        return vStatus;
    }

    public void setVStatus(String vStatus) {
        this.vStatus = vStatus;
    }

    public Integer getIBreezeUserID() {
        return iBreezeUserID;
    }

    public void setIBreezeUserID(Integer iBreezeUserID) {
        this.iBreezeUserID = iBreezeUserID;
    }

    public String getVComments() {
        return vComments;
    }

    public void setVComments(String vComments) {
        this.vComments = vComments;
    }

    public int getBUpdated() {
        return bUpdated;
    }

    public void setBUpdated(int bUpdated) {
        this.bUpdated = bUpdated;
    }

    public int getBReport() {
        return bReport;
    }

    public void setBReport(int bReport) {
        this.bReport = bReport;
    }

    public Date getDtJoinDate() {
        return dtJoinDate;
    }

    public void setDtJoinDate(Date dtJoinDate) {
        this.dtJoinDate = dtJoinDate;
    }

    public Date getDtValidTo() {
        return dtValidTo;
    }

    public void setDtValidTo(Date dtValidTo) {
        this.dtValidTo = dtValidTo;
    }

    public Date getDtConfirmDate() {
        return dtConfirmDate;
    }

    public void setDtConfirmDate(Date dtConfirmDate) {
        this.dtConfirmDate = dtConfirmDate;
    }

    public Date getDtAdminConfirm() {
        return dtAdminConfirm;
    }

    public void setDtAdminConfirm(Date dtAdminConfirm) {
        this.dtAdminConfirm = dtAdminConfirm;
    }

    public Integer getIAdmin() {
        return iAdmin;
    }

    public void setIAdmin(Integer iAdmin) {
        this.iAdmin = iAdmin;
    }

    public String getVStoreManager() {
        return vStoreManager;
    }

    public void setVStoreManager(String vStoreManager) {
        this.vStoreManager = vStoreManager;
    }

    public String getVLogin() {
        return vLogin;
    }

    public void setVLogin(String vLogin) {
        this.vLogin = vLogin;
    }

    public String getVEmail() {
        return vEmail;
    }

    public void setVEmail(String vEmail) {
        this.vEmail = vEmail;
    }

    public String getVUsername() {
        return vUsername;
    }

    public void setVUsername(String vUsername) {
        this.vUsername = vUsername;
    }

    public String getVPassword() {
        return vPassword;
    }

    public void setVPassword(String vPassword) {
        this.vPassword = vPassword;
    }

    public String getVPasswordSalt() {
        return vPasswordSalt;
    }

    public void setVPasswordSalt(String vPasswordSalt) {
        this.vPasswordSalt = vPasswordSalt;
    }

    public Date getDtPasswordUpdated() {
        return dtPasswordUpdated;
    }

    public void setDtPasswordUpdated(Date dtPasswordUpdated) {
        this.dtPasswordUpdated = dtPasswordUpdated;
    }

    public Date getDtUpdated() {
        return dtUpdated;
    }

    public void setDtUpdated(Date dtUpdated) {
        this.dtUpdated = dtUpdated;
    }

    public String getVOptIn() {
        return vOptIn;
    }

    public void setVOptIn(String vOptIn) {
        this.vOptIn = vOptIn;
    }

    public Integer getBOptIn() {
        return bOptIn;
    }

    public void setBOptIn(Integer bOptIn) {
        this.bOptIn = bOptIn;
    }

    public String getVAge() {
        return vAge;
    }

    public void setVAge(String vAge) {
        this.vAge = vAge;
    }

    public String getVTitle() {
        return vTitle;
    }

    public void setVTitle(String vTitle) {
        this.vTitle = vTitle;
    }

    public String getVFirstNameProper() {
        return vFirstNameProper;
    }

    public void setVFirstNameProper(String vFirstNameProper) {
        this.vFirstNameProper = vFirstNameProper;
    }

    public String getVFirstName() {
        return vFirstName;
    }

    public void setVFirstName(String vFirstName) {
        this.vFirstName = vFirstName;
    }

    public String getVMiddleName() {
        return vMiddleName;
    }

    public void setVMiddleName(String vMiddleName) {
        this.vMiddleName = vMiddleName;
    }

    public String getVPosition() {
        return vPosition;
    }

    public void setVPosition(String vPosition) {
        this.vPosition = vPosition;
    }

    public String getVLastName() {
        return vLastName;
    }

    public void setVLastName(String vLastName) {
        this.vLastName = vLastName;
    }

    public String getVPostnominals() {
        return vPostnominals;
    }

    public void setVPostnominals(String vPostnominals) {
        this.vPostnominals = vPostnominals;
    }

    public String getVPostition() {
        return vPostition;
    }

    public void setVPostition(String vPostition) {
        this.vPostition = vPostition;
    }

    public String getVProfession() {
        return vProfession;
    }

    public void setVProfession(String vProfession) {
        this.vProfession = vProfession;
    }

    public String getVProfessionOther() {
        return vProfessionOther;
    }

    public void setVProfessionOther(String vProfessionOther) {
        this.vProfessionOther = vProfessionOther;
    }

    public int getBMedNumVerified() {
        return bMedNumVerified;
    }

    public void setBMedNumVerified(int bMedNumVerified) {
        this.bMedNumVerified = bMedNumVerified;
    }

    public String getVMedNumber() {
        return vMedNumber;
    }

    public void setVMedNumber(String vMedNumber) {
        this.vMedNumber = vMedNumber;
    }

    public String getVMedNumber2() {
        return vMedNumber2;
    }

    public void setVMedNumber2(String vMedNumber2) {
        this.vMedNumber2 = vMedNumber2;
    }

    public Integer getIGroupID() {
        return iGroupID;
    }

    public void setIGroupID(Integer iGroupID) {
        this.iGroupID = iGroupID;
    }

    public String getVGroup() {
        return vGroup;
    }

    public void setVGroup(String vGroup) {
        this.vGroup = vGroup;
    }

    public String getVLocName() {
        return vLocName;
    }

    public void setVLocName(String vLocName) {
        this.vLocName = vLocName;
    }

    public String getVLocAddress1() {
        return vLocAddress1;
    }

    public void setVLocAddress1(String vLocAddress1) {
        this.vLocAddress1 = vLocAddress1;
    }

    public String getVLocAddress2() {
        return vLocAddress2;
    }

    public void setVLocAddress2(String vLocAddress2) {
        this.vLocAddress2 = vLocAddress2;
    }

    public String getVLocLocality1() {
        return vLocLocality1;
    }

    public void setVLocLocality1(String vLocLocality1) {
        this.vLocLocality1 = vLocLocality1;
    }

    public String getVLocLocality2() {
        return vLocLocality2;
    }

    public void setVLocLocality2(String vLocLocality2) {
        this.vLocLocality2 = vLocLocality2;
    }

    public String getVLocRegion() {
        return vLocRegion;
    }

    public void setVLocRegion(String vLocRegion) {
        this.vLocRegion = vLocRegion;
    }

    public String getVLocPostCode() {
        return vLocPostCode;
    }

    public void setVLocPostCode(String vLocPostCode) {
        this.vLocPostCode = vLocPostCode;
    }

    public String getVLocCountry() {
        return vLocCountry;
    }

    public void setVLocCountry(String vLocCountry) {
        this.vLocCountry = vLocCountry;
    }

    public String getVPhoneNumber() {
        return vPhoneNumber;
    }

    public void setVPhoneNumber(String vPhoneNumber) {
        this.vPhoneNumber = vPhoneNumber;
    }

    public String getVMobileNumber() {
        return vMobileNumber;
    }

    public void setVMobileNumber(String vMobileNumber) {
        this.vMobileNumber = vMobileNumber;
    }

    public String getVFaxNumber() {
        return vFaxNumber;
    }

    public void setVFaxNumber(String vFaxNumber) {
        this.vFaxNumber = vFaxNumber;
    }

    public String getVReferSource() {
        return vReferSource;
    }

    public void setVReferSource(String vReferSource) {
        this.vReferSource = vReferSource;
    }

    public String getVReferSourceRep() {
        return vReferSourceRep;
    }

    public void setVReferSourceRep(String vReferSourceRep) {
        this.vReferSourceRep = vReferSourceRep;
    }

    public Integer getBInterests() {
        return bInterests;
    }

    public void setBInterests(Integer bInterests) {
        this.bInterests = bInterests;
    }

    public Integer getBBuyer() {
        return bBuyer;
    }

    public void setBBuyer(Integer bBuyer) {
        this.bBuyer = bBuyer;
    }

    public Integer getBIsHeadPharmacist() {
        return bIsHeadPharmacist;
    }

    public void setBIsHeadPharmacist(Integer bIsHeadPharmacist) {
        this.bIsHeadPharmacist = bIsHeadPharmacist;
    }

    public Integer getBIsProprietor() {
        return bIsProprietor;
    }

    public void setBIsProprietor(Integer bIsProprietor) {
        this.bIsProprietor = bIsProprietor;
    }

    public Integer getIRewardPoints() {
        return iRewardPoints;
    }

    public void setIRewardPoints(Integer iRewardPoints) {
        this.iRewardPoints = iRewardPoints;
    }

    public Integer getIRewardPointsTotal() {
        return iRewardPointsTotal;
    }

    public void setIRewardPointsTotal(Integer iRewardPointsTotal) {
        this.iRewardPointsTotal = iRewardPointsTotal;
    }

    public int getIRewardPointsUsed() {
        return iRewardPointsUsed;
    }

    public void setIRewardPointsUsed(int iRewardPointsUsed) {
        this.iRewardPointsUsed = iRewardPointsUsed;
    }

    public int getIRewardPointsBonus() {
        return iRewardPointsBonus;
    }

    public void setIRewardPointsBonus(int iRewardPointsBonus) {
        this.iRewardPointsBonus = iRewardPointsBonus;
    }

    public Date getDtReachedGold() {
        return dtReachedGold;
    }

    public void setDtReachedGold(Date dtReachedGold) {
        this.dtReachedGold = dtReachedGold;
    }

    public String getVExternalID() {
        return vExternalID;
    }

    public void setVExternalID(String vExternalID) {
        this.vExternalID = vExternalID;
    }

    public String getVExternalID2() {
        return vExternalID2;
    }

    public void setVExternalID2(String vExternalID2) {
        this.vExternalID2 = vExternalID2;
    }

    public Integer getBAgreeOptIn() {
        return bAgreeOptIn;
    }

    public void setBAgreeOptIn(Integer bAgreeOptIn) {
        this.bAgreeOptIn = bAgreeOptIn;
    }

    public Integer getBSherpaOptIn() {
        return bSherpaOptIn;
    }

    public void setBSherpaOptIn(Integer bSherpaOptIn) {
        this.bSherpaOptIn = bSherpaOptIn;
    }

    public String getVEmailEDM() {
        return vEmailEDM;
    }

    public void setVEmailEDM(String vEmailEDM) {
        this.vEmailEDM = vEmailEDM;
    }

    public Integer getBSherpaSurveyTaken() {
        return bSherpaSurveyTaken;
    }

    public void setBSherpaSurveyTaken(Integer bSherpaSurveyTaken) {
        this.bSherpaSurveyTaken = bSherpaSurveyTaken;
    }

    public Integer getBSmsOptin() {
        return bSmsOptin;
    }

    public void setBSmsOptin(Integer bSmsOptin) {
        this.bSmsOptin = bSmsOptin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iID != null ? iID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblaccount)) {
            return false;
        }
        Tblaccount other = (Tblaccount) object;
        if ((this.iID == null && other.iID != null) || (this.iID != null && !this.iID.equals(other.iID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unityhealth.api.retrieveUserInfo.domain.Tblaccount[ iID=" + iID + " ]";
    }

    /**
     * @return the apiUserId
     */
    public String getApiUserId() {
        return apiUserId;
    }

    /**
     * @param apiUserId the apiUserId to set
     */
    public void setApiUserId(String apiUserId) {
        this.apiUserId = apiUserId;
    }

    /**
     * @return the apiSyncStatus
     */
    public String getApiSyncStatus() {
        return apiSyncStatus;
    }

    /**
     * @param apiSyncStatus the apiSyncStatus to set
     */
    public void setApiSyncStatus(String apiSyncStatus) {
        this.apiSyncStatus = apiSyncStatus;
    }

    /**
     * @return the store
     */
    public Store getStore() {
        return store;
    }

    /**
     * @param store the store to set
     */
    public void setStore(Store store) {
        this.store = store;
    }
    
}
