package com.unityhealth.api.retrieveUserInfo.connect;

/**
 * (C) Copyright 2004 Macromedia, Inc. All Rights Reserved
 *
 * THIS CODE AND INFORMATION IS PROVIDED "AS-IS" WITHOUT WARRANTY OF ANY KIND,
 * EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A PARTICULAR PURPOSE.
 *
 * THIS CODE IS NOT SUPPORTED BY MACROMEDIA, Inc.
 *
 */
/**
 * <p>
 * Title: MyMeetings example for Breeze</p>
 * <p>
 * Description: Example application to retireve a user's meetings</p>
 * <p>
 * Copyright: Copyright (c) 2004</p>
 * <p>
 * Company: Macromedia Inc.</p>
 *
 * @author Bob Benedict
 * @version 1.0
 */
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * This class is an encapsulation of the meeting data. Using this class allows
 * us to hide XML details from the JSP pages that need this information.
 *
 */
public class Meeting {

    /**
     * The meeting name.
     */
    protected String name;

    /**
     * The launch URL users click to participate in the meeting.
     */
    protected String launchURL;

    /**
     * True if the meeting is active. False if the meeting is not.
     */
    protected String active;

    /**
     * Meeting start date and time. The incoming XML is ISO 8601 compliant. The
     * individual date values are parsed into a date object.
     */
    protected Date starting;

    /**
     * Instantiate the meeting with the name, launch URL and start date as an
     * ISO 8601 compliant date.
     *
     * @param name Meeting name
     * @param launchURL URL users click
     * @param startdate Meeting start date and time in ISO 8601
     */
    public Meeting(String name, String launchURL, String startdate) throws
            ParseException {

        this.name = name;
        this.launchURL = launchURL;

        // Separate date from time, and start time from the finish
        StringTokenizer tokens = new StringTokenizer(startdate, "T");
        StringTokenizer dates = new StringTokenizer(tokens.nextToken(), "-");
        StringTokenizer times = new StringTokenizer(tokens.nextToken(), "-");
        StringTokenizer timesdetails = new StringTokenizer(times.nextToken(), ":");

        // Build the date string
        String year = dates.nextToken();
        String month = dates.nextToken();
        String day = dates.nextToken();
        String hours = timesdetails.nextToken();
        String minutes = timesdetails.nextToken();

        // Use a SimpleDateFormat object to create a Date object
        String dateStr = month + " " + day + " " + year + " " + hours + " " + minutes;
        SimpleDateFormat formatter = new SimpleDateFormat("MM dd yyyy HH mm");
        this.starting = formatter.parse(dateStr);
    }

    /**
     * Set the name property.
     *
     * @param name Meeting name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the meeting name
     *
     * @return The meeting name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the launch URL users click to participate in the meeting.
     *
     * @param launchURL URL users click to participate in meeting
     */
    public void setLaunchURL(String launchURL) {
        this.launchURL = launchURL;
    }

    /**
     * Getter for the launch URL
     *
     * @return the launch URL
     */
    public String getLaunchURL() {
        return this.launchURL;
    }

    /**
     * Set the active string. Could use a Boolean here as well.
     *
     * @param active True or False if the meeting is active or not
     */
    public void setActive(String active) {
        this.active = active;
    }

    /**
     * Getter for the active string
     *
     * @return The active string
     */
    public String getActive() {
        return this.active;
    }

    /**
     * Get the date formatted the with a SimpleDateFormta.format method.
     *
     * @return A properly formatted date string
     */
    public String getStartdate() throws Exception {
        SimpleDateFormat displayDate = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        return displayDate.format(this.starting);
    }
}
