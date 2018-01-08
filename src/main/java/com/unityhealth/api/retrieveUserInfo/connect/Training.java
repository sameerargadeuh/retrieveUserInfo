package com.unityhealth.api.retrieveUserInfo.connect;

import java.text.ParseException;

public class Training {

  protected String name;
  protected String desc;
  protected String scoID;
  protected String launchURL;
  protected String trStatus;
  protected String dateTaken;

  public Training(  String name,
                    String desc,
                    String scoID,
                    String launchURL,
                    String trStatus,
                    String dateTaken) throws
      ParseException {

    this.name = name;
    this.desc = name;
    this.scoID = scoID;
    this.launchURL = launchURL;
    this.trStatus = trStatus;
    this.dateTaken = dateTaken;
  }

  public void setName(String name) { this.name = name; }
  public String getName() { return this.name; }

  public void setDesc(String desc) { this.desc = desc; }
  public String getDesc() { return this.desc; }
  
  public void setScoID(String scoID) { this.scoID = scoID; }
  public String getScoID() { return this.scoID; }

  public void setLaunchURL(String launchURL) { this.launchURL = launchURL; }
  public String getLaunchURL() { return this.launchURL; }

  public void setTrStatus(String trStatus) { this.trStatus = trStatus; }
  public String getTrStatus() { return this.trStatus; }

  public void setDateTaken(String closeDate) { this.dateTaken = closeDate; }
  public String getDateTaken() { return this.dateTaken; }

}
