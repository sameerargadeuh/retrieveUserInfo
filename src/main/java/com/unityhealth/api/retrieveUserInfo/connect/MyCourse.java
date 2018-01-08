package com.unityhealth.api.retrieveUserInfo.connect;

import java.text.ParseException;

/**
 * This class is an encapsulation of the MyCourse data.  Using this class
 * allows us to hide XML details from the JSP pages that need this information.
 *
 */
public class MyCourse {

  protected String name;
  protected String scoID;
  protected String launchURL;
  protected String completed;
  protected String trStatus;
  protected String closeDate;
  
  public MyCourse(String name, String scoID, String launchURL, String completed,String trStatus, String closeDate) throws
      ParseException {

    this.name = name;
    this.scoID = scoID;
    this.launchURL = launchURL;
    this.completed = completed;
    this.trStatus = trStatus;
    this.closeDate = closeDate;
  }

  public void setName(String name) { this.name = name; }
  public String getName() { return this.name; }
  
  public void setScoID(String scoID) { this.scoID = scoID; }
  public String getScoID() { return this.scoID; }
  
  public void setLaunchURL(String launchURL) { this.launchURL = launchURL; }
  public String getLaunchURL() { return this.launchURL; }
    
  public void setCompleted(String completed) { this.completed = completed; }
  public String getCompleted() { return this.completed; }
  
  public void setTrStatus(String trStatus) { this.trStatus = trStatus; }
  public String getTrStatus() { return this.trStatus; }

  public void setCloseDate(String closeDate) { this.closeDate = closeDate; }
  public String getCloseDate() { return this.closeDate; }
  
}