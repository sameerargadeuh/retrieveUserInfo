/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.api.retrieveUserInfo.sf.userModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Sameer S Argade
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    /**
     * @return the userId
     */
   @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", businessPhone=" + businessPhone + ", empId=" + empId + ", lastModifiedDateTime=" + lastModifiedDateTime + ", finalJobRole=" + finalJobRole + ", username=" + username + ", jobRole=" + jobRole + ", addressLine3=" + addressLine3 + ", addressLine2=" + addressLine2 + ", addressLine1=" + addressLine1 + ", jobCode=" + jobCode + ", dateOfBirth=" + dateOfBirth + ", division=" + division + ", jobTitle=" + jobTitle + ", lastModified=" + lastModified + ", custom02=" + custom02 + ", custom01=" + custom01 + ", custom04=" + custom04 + ", custom03=" + custom03 + ", custom06=" + custom06 + ", custom05=" + custom05 + ", custom07=" + custom07 + ", lastName=" + lastName + ", fax=" + fax + ", hireDate=" + hireDate + ", country=" + country + ", email=" + email + ", department=" + department + ", custom15=" + custom15 + ", custom14=" + custom14 + ", custom13=" + custom13 + ", custom12=" + custom12 + ", custom11=" + custom11 + ", custom10=" + custom10 + ", city=" + city + ", sciLastModified=" + sciLastModified + ", lastModifiedWithTZ=" + lastModifiedWithTZ + ", defaultFullName=" + defaultFullName + ", title=" + title + ", location=" + location + ", cellPhone=" + cellPhone + ", zipCode=" + zipCode + ", state=" + state + ", firstName=" + firstName + '}';
    }

    /**
     * @return the businessPhone
     */
    @JsonProperty("businessPhone")
    public String getBusinessPhone() {
        return businessPhone;
    }

    /**
     * @param businessPhone the businessPhone to set
     */
    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    /**
     * @return the empId
     */
    @JsonProperty("empId")
    public String getEmpId() {
        return empId;
    }

    /**
     * @param empId the empId to set
     */
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    /**
     * @return the lastModifiedDateTime
     */
    @JsonProperty("lastModifiedDateTime")
    public String getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    /**
     * @param lastModifiedDateTime the lastModifiedDateTime to set
     */
    
    public void setLastModifiedDateTime(String lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    /**
     * @return the finalJobRole
     */
    @JsonProperty("finalJobRole")
    public String getFinalJobRole() {
        return finalJobRole;
    }

    /**
     * @param finalJobRole the finalJobRole to set
     */
    public void setFinalJobRole(String finalJobRole) {
        this.finalJobRole = finalJobRole;
    }

    /**
     * @return the username
     */
    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the jobRole
     */
    @JsonProperty("jobRole")
    public String getJobRole() {
        return jobRole;
    }

    /**
     * @param jobRole the jobRole to set
     */
    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    /**
     * @return the addressLine3
     */
    @JsonProperty("addressLine3")
    public String getAddressLine3() {
        return addressLine3;
    }

    /**
     * @param addressLine3 the addressLine3 to set
     */
    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    /**
     * @return the addressLine2
     */
    @JsonProperty("addressLine2")
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * @param addressLine2 the addressLine2 to set
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * @return the addressLine1
     */
    @JsonProperty("addressLine1")
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * @param addressLine1 the addressLine1 to set
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * @return the jobCode
     */
    @JsonProperty("jobCode")
    public String getJobCode() {
        return jobCode;
    }

    /**
     * @param jobCode the jobCode to set
     */
    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    /**
     * @return the dateOfBirth
     */
    @JsonProperty("dateOfBirth")
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the division
     */
    @JsonProperty("division")
    public String getDivision() {
        return division;
    }

    /**
     * @param division the division to set
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * @return the jobTitle
     */
    @JsonProperty("jobTitle")
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * @param jobTitle the jobTitle to set
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * @return the lastModified
     */
    @JsonProperty("lastModified")
    public String getLastModified() {
        return lastModified;
    }

    /**
     * @param lastModified the lastModified to set
     */
    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * @return the custom02
     */
    @JsonProperty("custom02")
    public String getCustom02() {
        return custom02;
    }

    /**
     * @param custom02 the custom02 to set
     */
    
    public void setCustom02(String custom02) {
        this.custom02 = custom02;
    }

    /**
     * @return the custom01
     */
    @JsonProperty("custom01")
    public String getCustom01() {
        return custom01;
    }

    /**
     * @param custom01 the custom01 to set
     */
    public void setCustom01(String custom01) {
        this.custom01 = custom01;
    }

    /**
     * @return the custom04
     */
    @JsonProperty("custom04")
    public String getCustom04() {
        return custom04;
    }

    /**
     * @param custom04 the custom04 to set
     */
    public void setCustom04(String custom04) {
        this.custom04 = custom04;
    }

    /**
     * @return the custom03
     */
    @JsonProperty("custom03")
    public String getCustom03() {
        return custom03;
    }

    /**
     * @param custom03 the custom03 to set
     */
    public void setCustom03(String custom03) {
        this.custom03 = custom03;
    }

    /**
     * @return the custom06
     */
    @JsonProperty("custom06")
    public String getCustom06() {
        return custom06;
    }

    /**
     * @param custom06 the custom06 to set
     */
    public void setCustom06(String custom06) {
        this.custom06 = custom06;
    }

    /**
     * @return the custom05
     */
    @JsonProperty("custom05")
    public String getCustom05() {
        return custom05;
    }

    /**
     * @param custom05 the custom05 to set
     */
    public void setCustom05(String custom05) {
        this.custom05 = custom05;
    }

    /**
     * @return the custom07
     */
    @JsonProperty("custom07")
    public String getCustom07() {
        return custom07;
    }

    /**
     * @param custom07 the custom07 to set
     */

    public void setCustom07(String custom07) {
        this.custom07 = custom07;
    }

    /**
     * @return the lastName
     */
    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the fax
     */
    @JsonProperty("fax")
    public String getFax() {
        return fax;
    }

    /**
     * @param fax the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return the hireDate
     */
    @JsonProperty("hireDate")
    public String getHireDate() {
        return hireDate;
    }

    /**
     * @param hireDate the hireDate to set
     */
    
    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    /**
     * @return the country
     */
    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the email
    */
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the department
     */
     @JsonProperty("department")
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return the custom15
     */
     @JsonProperty("custom15")
    public String getCustom15() {
        return custom15;
    }

    /**
     * @param custom15 the custom15 to set
     */
    public void setCustom15(String custom15) {
        this.custom15 = custom15;
    }

    /**
     * @return the custom14
     */
     @JsonProperty("custom14")
    public String getCustom14() {
        return custom14;
    }

    /**
     * @param custom14 the custom14 to set
     */
    public void setCustom14(String custom14) {
        this.custom14 = custom14;
    }

    /**
     * @return the custom13
     */
     @JsonProperty("custom13")
    public String getCustom13() {
        return custom13;
    }

    /**
     * @param custom13 the custom13 to set
     */
    public void setCustom13(String custom13) {
        this.custom13 = custom13;
    }

    /**
     * @return the custom12
     */
     @JsonProperty("custom12")
    public String getCustom12() {
        return custom12;
    }

    /**
     * @param custom12 the custom12 to set
     */
    public void setCustom12(String custom12) {
        this.custom12 = custom12;
    }

    /**
     * @return the custom11
     */
     @JsonProperty("custom11")
    public String getCustom11() {
        return custom11;
    }

    /**
     * @param custom11 the custom11 to set
     */
    public void setCustom11(String custom11) {
        this.custom11 = custom11;
    }

    /**
     * @return the custom10
     */
     @JsonProperty("custom10")
    public String getCustom10() {
        return custom10;
    }

    /**
     * @param custom10 the custom10 to set
     */
    public void setCustom10(String custom10) {
        this.custom10 = custom10;
    }

    /**
     * @return the city
     */
     @JsonProperty("city")
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the sciLastModified
     */
     @JsonProperty("sciLastModified")
    public String getSciLastModified() {
        return sciLastModified;
    }

    /**
     * @param sciLastModified the sciLastModified to set
     */
     @JsonProperty("sciLastModified")
    public void setSciLastModified(String sciLastModified) {
        this.sciLastModified = sciLastModified;
    }

    /**
     * @return the lastModifiedWithTZ
     */
     @JsonProperty("lastModifiedWithTZ")
    public String getLastModifiedWithTZ() {
        return lastModifiedWithTZ;
    }

    /**
     * @param lastModifiedWithTZ the lastModifiedWithTZ to set
     */
    public void setLastModifiedWithTZ(String lastModifiedWithTZ) {
        this.lastModifiedWithTZ = lastModifiedWithTZ;
    }

    /**
     * @return the defaultFullName
     */
     @JsonProperty("defaultFullName")
    public String getDefaultFullName() {
        return defaultFullName;
    }

    /**
     * @param defaultFullName the defaultFullName to set
     */
    public void setDefaultFullName(String defaultFullName) {
        this.defaultFullName = defaultFullName;
    }

    /**
     * @return the title
     */
     @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the location
     */
     @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the cellPhone
     */
     @JsonProperty("cellPhone")
    public String getCellPhone() {
        return cellPhone;
    }

    /**
     * @param cellPhone the cellPhone to set
     */
    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    /**
     * @return the zipCode
     */
     @JsonProperty("zipCode")
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the state
     */
     @JsonProperty("state")
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the firstName
     */
     @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    private String userId;
   private String businessPhone;
   //private String salaryProrating;
   private String empId; 
   private String lastModifiedDateTime; 
   private String finalJobRole;
   private String username; //twcthomas2@hotmail.com_1",
  // private String married;
  // private String futureLeader;
 //  private String salary;
   private String jobRole;
 //  private String nickname; 
 //  private String salaryLocal;
 //  private String compensationEligible;
   private String addressLine3;
   private String addressLine2;
   private String addressLine1; //Shop MM1 Rhodes Shopping Centre, 1 Rider Boulevard",
   private String jobCode; //Pharmacist (111300)",
  // private String totalTeamSize;// 0",
   private String dateOfBirth;
 //  private String newToPosition;//;
//   private String impactOfLossComments;
 //  private String stockBudgetStockAmount;
   private String division; //Retail Operations (3330)",
  // private String salaryBudgetPromotionPercentage;
  // private String veteranSeparated;
 //  private String veteranProtected;
   private String jobTitle;
   private String lastModified;
  // private String bonusBudgetAmount;
   private String custom02;// Priceline Pharmacy (2200)",
   private String custom01; //TRex Group (1053)",
   private String custom04; //Franchise (#N/A)",
   private String custom03; //Store Management (5500)",
   private String custom06; //Full Time",
   private String custom05; //Franchise",
   //private String ssn;
   private String custom07;
   //private String reviewFreq;
   //private String riskOfLoss;
   //private String localCurrencyCode;
   //private String stockBudgetOptionAmount;
   //private String payGrade;
   private String lastName; //Tsang",
   private String fax;
   //private String compensationStockEligible;
   private String hireDate; ///Date(1502582400000)/",
   //private String salaryBudgetFinalSalaryPercentage;
   private String country; //Australia",
   private String email; //twcthomas2@hotmail.com_1",
   //private String criticalTalentComments;
   //private String compensationSalaryRateType;
   //private String defaultLocale;// en_GB",
   //private String minority;
   private String department; // Store Operations (4580)",
   //private String seatingChart;
   //private String salaryBudgetTotalRaisePercentage;
   //private String performance;// 0.0",
   //private String finalJobFamily;
   //private String origHireDate;
   //private String loginMethod; //PWD",
   //private String sysCostOfSource;
   private String custom15;
   private String custom14;
   private String custom13;
   //private String stockBudgetOther3Amount;
   private String custom12;
   private String custom11;
   //private String matrixManaged;
   private String custom10; //NSW Area 03 (723)",
   //private String reloWilling;
   //private String nationality;
  // private String matrix2Label;
   //private String compensationSalaryEligible;
   private String city; //Rhodes",
   //private String salaryBudgetExtraPercentage;
   //private String bonusTarget;
   //private String jobLevel;
   private String sciLastModified; // /Date(1515091132000+0000)/",
   //private String dateOfPosition;
   private String lastModifiedWithTZ; // /Date(1516864835000+0000)/",
   //private String reloComments;
   //private String keyPosition;
   //private String finalJobCode;
   private String defaultFullName; // Wing Tsang",
   //private String veteranDisabled;
   //private String benchStrength;
   private String title; //Pharmacist - Rhodes (824011300)",
   //private String businessSegment;
   //private String employeeClass; // Franchise",
   //private String lastReviewDate;
   //private String mi;
   //private String veteranMedal;
   //private String PayScaleLevel;
   //private String meritTarget;
   //private String salutation;
   //private String ethnicity;
   //private String serviceDate;
   private String location; //Priceline Pharmacy Rhodes (240)",
   private String cellPhone;
   //private String competency; //0.0",
   //private String homePhone;
   //private String level;
   private String zipCode; //2138",
   //private String salaryBudgetExtra2Percentage;
   //private String talentPool;
   //private String reasonForLeaving;
   //private String objective;// 0.0",
   //private String status; // t",
   //private String stockBudgetOther2Amount;
   //private String compensationBonusEligible;
   //private String jobFamily;
   //private String reloLocation;
   //private String dateOfCurrentPosition;
   //private String issueComments;
   //private String stockBudgetOther1Amount;
   //private String sysSource;
   //private String salaryBudgetLumpsumPercentage;
   //private String raiseProrating;
   private String state; //NSW",
   //private String citizenship;
   //private String timeZone; // US/Eastern",
   //private String onboardingId;
   //private String compensationReadOnly;
   //private String salaryBudgetMeritPercentage;
   //private String meritEffectiveDate;
   private String firstName; //Wing",
   //private String promotionAmount;
   //private String impactOfLoss;
   //private String lumpsum2Target;
   //private String potential; // 0.0",
   //private String lumpsumTarget;
   //private String matrix1Label;
   //private String suffix;
   //private String sysStartingSalary;
   //private String stockBudgetUnitAmount;
   //private String compensationSalaryRateUnits;
}
