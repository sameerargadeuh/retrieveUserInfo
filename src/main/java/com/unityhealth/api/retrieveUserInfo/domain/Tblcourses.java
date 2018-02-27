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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Sameer S Argade
 */
@Entity
@Table(name = "tblcourses")
@NamedQueries({
    @NamedQuery(name = "Tblcourses.findAll", query = "SELECT t FROM Tblcourses t")})
public class Tblcourses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iID")
    private Integer iID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bActive")
    private int bActive;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iSite")
    private int iSite;
    @Column(name = "iCourseID")
    private Integer iCourseID;
    @Size(max = 512)
    @Column(name = "vName")
    private String vName;
    @Size(max = 255)
    @Column(name = "vDesc")
    private String vDesc;
    @Size(max = 512)
    @Column(name = "vSummary")
    private String vSummary;
    @Size(max = 255)
    @Column(name = "vAbrv")
    private String vAbrv;
    @Column(name = "iType")
    private Integer iType;
    @Column(name = "iCompanyID")
    private Integer iCompanyID;
    @Column(name = "iBrandID")
    private Integer iBrandID;
    @Column(name = "iReportID")
    private Integer iReportID;
    @Size(max = 255)
    @Column(name = "vImage")
    private String vImage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iRewardPoints")
    private int iRewardPoints;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bComingSoon")
    private int bComingSoon;
    @Column(name = "dtDatePublished")
    @Temporal(TemporalType.DATE)
    private Date dtDatePublished;
    @Column(name = "dtClosed")
    @Temporal(TemporalType.DATE)
    private Date dtClosed;
    @Column(name = "iReplacedBy")
    private Integer iReplacedBy;
    @Column(name = "bRestricted")
    private Character bRestricted;
    @Column(name = "bRestrictedHide")
    private Integer bRestrictedHide;
    @Size(max = 1024)
    @Column(name = "vRestrictedMessage")
    private String vRestrictedMessage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bRestrictedByCode")
    private int bRestrictedByCode;
    @Size(max = 255)
    @Column(name = "vCode")
    private String vCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bRestrictedByGroup")
    private int bRestrictedByGroup;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bRestrictedByStore")
    private int bRestrictedByStore;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bRestrictedByType")
    private int bRestrictedByType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bRestrictedByProfession")
    private int bRestrictedByProfession;
    @Size(max = 255)
    @Column(name = "vProfession")
    private String vProfession;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bDisclaimer")
    private int bDisclaimer;
    @Size(max = 1024)
    @Column(name = "vDisclaimer")
    private String vDisclaimer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bCertificate")
    private int bCertificate;
    @Column(name = "bAccred")
    private Integer bAccred;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iAccredTypeID")
    private int iAccredTypeID;
    @Column(name = "bAccredPending")
    private Integer bAccredPending;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "iAccredPoints")
    private Float iAccredPoints;
    @Size(max = 255)
    @Column(name = "vAccredDesc")
    private String vAccredDesc;
    @Size(max = 512)
    @Column(name = "vAccredDetails")
    private String vAccredDetails;
    @Size(max = 512)
    @Column(name = "vAccredCompetency")
    private String vAccredCompetency;
    @Size(max = 512)
    @Column(name = "vAccredObjectives")
    private String vAccredObjectives;
    @Size(max = 512)
    @Column(name = "vAccredCongratulatory")
    private String vAccredCongratulatory;
    @Size(max = 32)
    @Column(name = "vAccredNum")
    private String vAccredNum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bRewards")
    private int bRewards;
    @Size(max = 1024)
    @Column(name = "vRewardsInfo")
    private String vRewardsInfo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bNew")
    private int bNew;
    @Size(max = 255)
    @Column(name = "vSearchText")
    private String vSearchText;
    @Size(max = 1024)
    @Column(name = "vAudienceMsg")
    private String vAudienceMsg;
    @Column(name = "iFormatType")
    private Integer iFormatType;
    @Size(max = 255)
    @Column(name = "vUrl")
    private String vUrl;
    @Lob
    @Size(max = 65535)
    @Column(name = "vNotes")
    private String vNotes;
    @Column(name = "iOwnerGroupID")
    private Integer iOwnerGroupID;
    @Column(name = "iRequired")
    private Integer iRequired;
    @Column(name = "iRestrictedDisplayType")
    private Integer iRestrictedDisplayType;

    public Tblcourses() {
    }

    public Tblcourses(Integer iID) {
        this.iID = iID;
    }

    public Tblcourses(Integer iID, int bActive, int iSite, int iRewardPoints, int bComingSoon, int bRestrictedByCode, int bRestrictedByGroup, int bRestrictedByStore, int bRestrictedByType, int bRestrictedByProfession, int bDisclaimer, int bCertificate, int iAccredTypeID, int bRewards, int bNew) {
        this.iID = iID;
        this.bActive = bActive;
        this.iSite = iSite;
        this.iRewardPoints = iRewardPoints;
        this.bComingSoon = bComingSoon;
        this.bRestrictedByCode = bRestrictedByCode;
        this.bRestrictedByGroup = bRestrictedByGroup;
        this.bRestrictedByStore = bRestrictedByStore;
        this.bRestrictedByType = bRestrictedByType;
        this.bRestrictedByProfession = bRestrictedByProfession;
        this.bDisclaimer = bDisclaimer;
        this.bCertificate = bCertificate;
        this.iAccredTypeID = iAccredTypeID;
        this.bRewards = bRewards;
        this.bNew = bNew;
    }

    public Integer getIID() {
        return iID;
    }

    public void setIID(Integer iID) {
        this.iID = iID;
    }

    public int getBActive() {
        return bActive;
    }

    public void setBActive(int bActive) {
        this.bActive = bActive;
    }

    public int getISite() {
        return iSite;
    }

    public void setISite(int iSite) {
        this.iSite = iSite;
    }

    public Integer getICourseID() {
        return iCourseID;
    }

    public void setICourseID(Integer iCourseID) {
        this.iCourseID = iCourseID;
    }

    public String getVName() {
        return vName;
    }

    public void setVName(String vName) {
        this.vName = vName;
    }

    public String getVDesc() {
        return vDesc;
    }

    public void setVDesc(String vDesc) {
        this.vDesc = vDesc;
    }

    public String getVSummary() {
        return vSummary;
    }

    public void setVSummary(String vSummary) {
        this.vSummary = vSummary;
    }

    public String getVAbrv() {
        return vAbrv;
    }

    public void setVAbrv(String vAbrv) {
        this.vAbrv = vAbrv;
    }

    public Integer getIType() {
        return iType;
    }

    public void setIType(Integer iType) {
        this.iType = iType;
    }

    public Integer getICompanyID() {
        return iCompanyID;
    }

    public void setICompanyID(Integer iCompanyID) {
        this.iCompanyID = iCompanyID;
    }

    public Integer getIBrandID() {
        return iBrandID;
    }

    public void setIBrandID(Integer iBrandID) {
        this.iBrandID = iBrandID;
    }

    public Integer getIReportID() {
        return iReportID;
    }

    public void setIReportID(Integer iReportID) {
        this.iReportID = iReportID;
    }

    public String getVImage() {
        return vImage;
    }

    public void setVImage(String vImage) {
        this.vImage = vImage;
    }

    public int getIRewardPoints() {
        return iRewardPoints;
    }

    public void setIRewardPoints(int iRewardPoints) {
        this.iRewardPoints = iRewardPoints;
    }

    public int getBComingSoon() {
        return bComingSoon;
    }

    public void setBComingSoon(int bComingSoon) {
        this.bComingSoon = bComingSoon;
    }

    public Date getDtDatePublished() {
        return dtDatePublished;
    }

    public void setDtDatePublished(Date dtDatePublished) {
        this.dtDatePublished = dtDatePublished;
    }

    public Date getDtClosed() {
        return dtClosed;
    }

    public void setDtClosed(Date dtClosed) {
        this.dtClosed = dtClosed;
    }

    public Integer getIReplacedBy() {
        return iReplacedBy;
    }

    public void setIReplacedBy(Integer iReplacedBy) {
        this.iReplacedBy = iReplacedBy;
    }

    public Character getBRestricted() {
        return bRestricted;
    }

    public void setBRestricted(Character bRestricted) {
        this.bRestricted = bRestricted;
    }

    public Integer getBRestrictedHide() {
        return bRestrictedHide;
    }

    public void setBRestrictedHide(Integer bRestrictedHide) {
        this.bRestrictedHide = bRestrictedHide;
    }

    public String getVRestrictedMessage() {
        return vRestrictedMessage;
    }

    public void setVRestrictedMessage(String vRestrictedMessage) {
        this.vRestrictedMessage = vRestrictedMessage;
    }

    public int getBRestrictedByCode() {
        return bRestrictedByCode;
    }

    public void setBRestrictedByCode(int bRestrictedByCode) {
        this.bRestrictedByCode = bRestrictedByCode;
    }

    public String getVCode() {
        return vCode;
    }

    public void setVCode(String vCode) {
        this.vCode = vCode;
    }

    public int getBRestrictedByGroup() {
        return bRestrictedByGroup;
    }

    public void setBRestrictedByGroup(int bRestrictedByGroup) {
        this.bRestrictedByGroup = bRestrictedByGroup;
    }

    public int getBRestrictedByStore() {
        return bRestrictedByStore;
    }

    public void setBRestrictedByStore(int bRestrictedByStore) {
        this.bRestrictedByStore = bRestrictedByStore;
    }

    public int getBRestrictedByType() {
        return bRestrictedByType;
    }

    public void setBRestrictedByType(int bRestrictedByType) {
        this.bRestrictedByType = bRestrictedByType;
    }

    public int getBRestrictedByProfession() {
        return bRestrictedByProfession;
    }

    public void setBRestrictedByProfession(int bRestrictedByProfession) {
        this.bRestrictedByProfession = bRestrictedByProfession;
    }

    public String getVProfession() {
        return vProfession;
    }

    public void setVProfession(String vProfession) {
        this.vProfession = vProfession;
    }

    public int getBDisclaimer() {
        return bDisclaimer;
    }

    public void setBDisclaimer(int bDisclaimer) {
        this.bDisclaimer = bDisclaimer;
    }

    public String getVDisclaimer() {
        return vDisclaimer;
    }

    public void setVDisclaimer(String vDisclaimer) {
        this.vDisclaimer = vDisclaimer;
    }

    public int getBCertificate() {
        return bCertificate;
    }

    public void setBCertificate(int bCertificate) {
        this.bCertificate = bCertificate;
    }

    public Integer getBAccred() {
        return bAccred;
    }

    public void setBAccred(Integer bAccred) {
        this.bAccred = bAccred;
    }

    public int getIAccredTypeID() {
        return iAccredTypeID;
    }

    public void setIAccredTypeID(int iAccredTypeID) {
        this.iAccredTypeID = iAccredTypeID;
    }

    public Integer getBAccredPending() {
        return bAccredPending;
    }

    public void setBAccredPending(Integer bAccredPending) {
        this.bAccredPending = bAccredPending;
    }

    public Float getIAccredPoints() {
        return iAccredPoints;
    }

    public void setIAccredPoints(Float iAccredPoints) {
        this.iAccredPoints = iAccredPoints;
    }

    public String getVAccredDesc() {
        return vAccredDesc;
    }

    public void setVAccredDesc(String vAccredDesc) {
        this.vAccredDesc = vAccredDesc;
    }

    public String getVAccredDetails() {
        return vAccredDetails;
    }

    public void setVAccredDetails(String vAccredDetails) {
        this.vAccredDetails = vAccredDetails;
    }

    public String getVAccredCompetency() {
        return vAccredCompetency;
    }

    public void setVAccredCompetency(String vAccredCompetency) {
        this.vAccredCompetency = vAccredCompetency;
    }

    public String getVAccredObjectives() {
        return vAccredObjectives;
    }

    public void setVAccredObjectives(String vAccredObjectives) {
        this.vAccredObjectives = vAccredObjectives;
    }

    public String getVAccredCongratulatory() {
        return vAccredCongratulatory;
    }

    public void setVAccredCongratulatory(String vAccredCongratulatory) {
        this.vAccredCongratulatory = vAccredCongratulatory;
    }

    public String getVAccredNum() {
        return vAccredNum;
    }

    public void setVAccredNum(String vAccredNum) {
        this.vAccredNum = vAccredNum;
    }

    public int getBRewards() {
        return bRewards;
    }

    public void setBRewards(int bRewards) {
        this.bRewards = bRewards;
    }

    public String getVRewardsInfo() {
        return vRewardsInfo;
    }

    public void setVRewardsInfo(String vRewardsInfo) {
        this.vRewardsInfo = vRewardsInfo;
    }

    public int getBNew() {
        return bNew;
    }

    public void setBNew(int bNew) {
        this.bNew = bNew;
    }

    public String getVSearchText() {
        return vSearchText;
    }

    public void setVSearchText(String vSearchText) {
        this.vSearchText = vSearchText;
    }

    public String getVAudienceMsg() {
        return vAudienceMsg;
    }

    public void setVAudienceMsg(String vAudienceMsg) {
        this.vAudienceMsg = vAudienceMsg;
    }

    public Integer getIFormatType() {
        return iFormatType;
    }

    public void setIFormatType(Integer iFormatType) {
        this.iFormatType = iFormatType;
    }

    public String getVUrl() {
        return vUrl;
    }

    public void setVUrl(String vUrl) {
        this.vUrl = vUrl;
    }

    public String getVNotes() {
        return vNotes;
    }

    public void setVNotes(String vNotes) {
        this.vNotes = vNotes;
    }

    public Integer getIOwnerGroupID() {
        return iOwnerGroupID;
    }

    public void setIOwnerGroupID(Integer iOwnerGroupID) {
        this.iOwnerGroupID = iOwnerGroupID;
    }

    public Integer getIRequired() {
        return iRequired;
    }

    public void setIRequired(Integer iRequired) {
        this.iRequired = iRequired;
    }

    public Integer getIRestrictedDisplayType() {
        return iRestrictedDisplayType;
    }

    public void setIRestrictedDisplayType(Integer iRestrictedDisplayType) {
        this.iRestrictedDisplayType = iRestrictedDisplayType;
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
        if (!(object instanceof Tblcourses)) {
            return false;
        }
        Tblcourses other = (Tblcourses) object;
        if ((this.iID == null && other.iID != null) || (this.iID != null && !this.iID.equals(other.iID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unityhealth.api.retrieveUserInfo.domain.Tblcourses[ iID=" + iID + " ]";
    }
    
}
