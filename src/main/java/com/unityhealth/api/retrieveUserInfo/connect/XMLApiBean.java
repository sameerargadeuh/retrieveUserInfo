package com.unityhealth.api.retrieveUserInfo.connect;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Text;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;

/**
 * This class provides a basic adapter to the Breeze system. You create an
 * instance for each login session and the adapter will handle connecting to the
 * server and parsing the XML response.
 *
 */
public class XMLApiBean {

    /**
     * The URL of the server. Should be passed in to the constructor. A later
     * project might include reading this from a properties file.
     */
    protected String baseUrl;
    /**
     * The SCO-ID of the Courses Folder.
     */
    //protected String coursesFolder;
    /**
     * This is the user log in name, typically the user email address.
     */
    protected String login;
    /**
     * The password of the user.
     */
    protected String password;
    /**
     * The Java session ID that is generated upon successful login. All calls
     * except login must provide this ID for authentication.
     */
    protected String breezesession;

    /**
     * @param baseUrl the base URL of the Breeze server, including the trailing
     * slash http://www.breeze.example/ is a typical example. Most Breeze
     * installations will not need any path except that of the host.
     * @param login the login of the user as whom the adapter will act on the
     * Breeze system. This is often an administrator but Breeze will properly
     * apply permissions for any user.
     * @param password The password of the user who's logging in.
     * @param breezesession The Java session ID created by the Breeze server
     * upon successful login.
     */
    public XMLApiBean(String baseUrl,
            //String coursesFolder,
            String login,
            String password,
            String breezesession) {

        this.baseUrl = baseUrl;
        this.login = login;
        this.password = password;
        this.breezesession = breezesession;
        //this.coursesFolder = coursesFolder;
    }

    /**
     * @param baseUrl the base URL of the Breeze server, including the trailing
     * slash http://www.breeze.example/ is a typical example. Most Breeze
     * installations will not need any path except that of the host.
     * @param breezesession The Java session ID created by the Breeze server
     * upon successful login.
     */
    public XMLApiBean(String baseUrl, String breezesession) {
        this.baseUrl = baseUrl;
        this.breezesession = breezesession;
        //System.out.println("BREEZE XMLApiBean created = "+breezesession);
    }

    public XMLApiBean(String baseUrl,
            String login,
            String breezesession) {

        this.baseUrl = baseUrl;
        this.login = login;
        this.breezesession = breezesession;
    }

    public void getConcurrentUsers() throws Exception {

        Element doc = request("report-quotas", "session=" + this.breezesession);

        /*
         //DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
         //DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         //DocumentBuilder builder = factory.newDocumentBuilder();
         //Document doc = builder.parse("uri to xmlfile");

         XPathFactory xPathfactory = XPathFactory.newInstance();
         XPath xpath = (XPath) xPathfactory.newXPath();
        
         //XPathExpression expr = xpath.compile("//ep/source[@type]");
         //NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

         for (int i = 0; i < nl.getLength(); i++) {
         Node currentItem = nl.item(i);
         String key = currentItem.getAttributes().getNamedItem("type").getNodeValue();
         System.out.println(key);
         }
        
         //XPathFactory xPathfactory = XPathFactory.newInstance();
         //XPath xpath = xPathfactory.newXPath();
         //XPathExpression expr = xpath.compile("//Type[@type_id=\"4218\"]");
         //NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
         */
    }

    /**
     * Performs the action to log into Breeze. Stores the Java session ID.
     */
    public void loginAuth() throws Exception {

        if (breezesession != null) {
            logout();
        }

        URL loginUrl = breezeUrl("login", "external-auth=use");
        HttpURLConnection conn = (HttpURLConnection) loginUrl.openConnection();
        conn.setRequestProperty("x-user-id", login);

        conn.connect();

        InputStream resultStream = conn.getInputStream();
        Document doc = new SAXBuilder(false).build(resultStream);
        Element e = doc.getRootElement();
        XPath path = XPath.newInstance("./status/@code");

        if (path.valueOf(e).equals("ok")) {
            String breezesessionString = (String) (conn.getHeaderField("Set-Cookie"));
            StringTokenizer st = new StringTokenizer(breezesessionString, "=");
            String sessionName = null;
            if (st.countTokens() > 1) {
                sessionName = st.nextToken();
            }
            if (sessionName != null && (sessionName.equals("JSESSIONID") || sessionName.equals("BREEZESESSION"))) {
                String breezesessionNext = st.nextToken();
                int semiIndex = breezesessionNext.indexOf(';');
                breezesession = breezesessionNext.substring(0, semiIndex);
            }
            if (breezesession == null) {
                throw new RuntimeException("Could not log in - No Session.");
            }
        } else {
            throw new RuntimeException("Could not log in to Education Server.");
        }
    }

    /**
     * Performs the action to log into Breeze. Stores the Java session ID.
     */
    protected void login() throws Exception {

        if (breezesession != null) {
            logout();
        }

        URL loginUrl = breezeUrl("login", "login=" + login + "&password=" + password);
        URLConnection conn = loginUrl.openConnection();
        conn.connect();

        InputStream resultStream = conn.getInputStream();
        Document doc = new SAXBuilder(false).build(resultStream);
        Element e = doc.getRootElement();
        XPath path = XPath.newInstance("./status/@code");

        if (path.valueOf(e).equals("ok")) {
            String breezesessionString = (String) (conn.getHeaderField("Set-Cookie"));
            StringTokenizer st = new StringTokenizer(breezesessionString, "=");
            String sessionName = null;
            if (st.countTokens() > 1) {
                sessionName = st.nextToken();
            }
            if (sessionName != null && (sessionName.equals("JSESSIONID") || sessionName.equals("BREEZESESSION"))) {
                String breezesessionNext = st.nextToken();
                int semiIndex = breezesessionNext.indexOf(';');
                breezesession = breezesessionNext.substring(0, semiIndex);
            }
            if (breezesession == null) {
                throw new RuntimeException("Could not log in - No Session.");
            }
        } else {
            throw new RuntimeException("Could not log in to Education Server.");
        }
    }

    /**
     * Logout of the LMS server, clearing the session as well.
     *
     * @throws java.lang.Exception
     */
    public void logout() throws Exception {
        if (this.breezesession == null) {
            request("logout", null);
        } else {
            request("logout", "session=" + this.breezesession);
        }
        this.breezesession = null;
    }

    /**
     * Given a sco, return the URL to access that sco.
     *
     * @param scoId the id of the Sco to return the URL of.
     *
     * @return the URL to access that sco.
     * @throws java.lang.Exception
     */
    public String scoUrl(String scoId) throws Exception {
        Element e = request("sco-info", "sco-id=" + scoId);
        XPath xpath = XPath.newInstance("//url-path/text()");
        String path = ((Text) xpath.selectSingleNode(e)).getText();

        e = request("sco-shortcuts", null);
        xpath = XPath.newInstance("//domain-name/text()");
        String url = ((Text) xpath.selectSingleNode(e)).getText();

        return url + "/" + path.substring(1) + "?session=" + breezesession;
    }

    /**
     * @param scoId
     * @return
     * @throws Exception
     */
    public String scoName(String scoId) throws Exception {
        Element e = request("sco-info", "sco-id=" + scoId);
        XPath xpath = XPath.newInstance("//name/text()");
        String path = ((Text) xpath.selectSingleNode(e)).getText();
        return path;
    }

    /**
     * @param scoId
     * @return
     * @throws Exception
     */
    public String scoUrl2(String scoId) throws Exception {
        Element e = request("sco-info", "sco-id=" + scoId);
        XPath xpath = XPath.newInstance("//url-path/text()");
        String path = ((Text) xpath.selectSingleNode(e)).getText();

        e = request("sco-shortcuts", null);
        xpath = XPath.newInstance("//domain-name/text()");
        String url = ((Text) xpath.selectSingleNode(e)).getText();

        return url + "/" + path.substring(1);
    }

    /**
     * Return general information about the given sco.
     *
     * @param scoId the id of the sco to check.
     * @return the XML representing general information about the sco.
     * @throws java.lang.Exception
     */
    public Element scoInfo(String scoId) throws Exception {
        return request("sco-info", "sco-id=" + scoId);
    }

    /**
     * Processes all Action Requests except login. The Java session must already
     * be set by the login method. Login is called if there is no existing
     * session.
     *
     * @param action the action to perform.
     * @param queryString the action parameters
     *
     * @return the XML document returned from the action.
     * @throws java.lang.Exception
     */
    protected Element request(String action, String queryString) throws Exception {

        if (breezesession == null) {
            login();
        }

        URL url = breezeUrl(action, queryString);

        URLConnection conn = url.openConnection();
        conn.setRequestProperty("Cookie", "BREEZESESSION=" + breezesession);
        conn.connect();

        InputStream resultStream = conn.getInputStream();
        
//        BufferedReader in = new BufferedReader(new InputStreamReader(
//                                    resultStream));
//        String inputLine;
//        while ((inputLine = in.readLine()) != null) 
//            System.out.println(inputLine);
//       // in.close();
//        System.out.println(resultStream);
        Document doc = new SAXBuilder(false).build(resultStream);
        return doc.getRootElement();
    }

    /**
     * Retrieves the appropriate Breeze URL.
     *
     * @param action the action to perform.
     * @param queryString the parameters for the action.
     *
     * @return the URL that will perform the action request.
     * @throws java.lang.Exception
     */
    protected URL breezeUrl(String action, String queryString) throws Exception {

        if (queryString != null) {
            queryString = queryString.replace(" ", "%20");
        }

        return new URL(baseUrl + "/api/xml?" + "action=" + action
                + (queryString != null ? ('&' + queryString) : ""));
    }

    public String getBreezesession() throws Exception {
        if (breezesession == null) {
            login();
        }
        return breezesession;
    }

    public String getBreezesessionAuth() throws Exception {
        if (breezesession == null) {
            loginAuth();
        }
        return breezesession;
    }

    // --- Meetings -------------------------------------------
    
    /**
     * Retrieve the meetings for the currently logged in user using the action
     * report-my-meetings
     *
     * @return List of XML elements of meetings
     * @throws java.lang.Exception
     */
    public List getMyMeetings() throws Exception {
        Element meetingDoc = request("report-my-meetings", null);
        return XPath.selectNodes(meetingDoc, "//meeting");
    }

    /**
     * Parse through the XML document of meeting elements and extract the most
     * important attributes and data including the meeting name and the URL
     * users click to participate in the meeting;
     *
     * @param e An XML element that contains the meeting information
     *
     * @return Meeting object with meeting information.
     * @throws java.lang.Exception
     */
    public Meeting getNextMeeting(Element e) throws Exception {

        XPath namePath = XPath.newInstance("./name/text()");
        XPath expiredPath = XPath.newInstance("./expired/text()");
        XPath idPath = XPath.newInstance("./@sco-id");
        XPath datePath = XPath.newInstance("./date-begin/text()");
        String expired = expiredPath.valueOf(e);
        Meeting newMeeting = new Meeting(namePath.valueOf(e),
                scoUrl(idPath.valueOf(e)),
                datePath.valueOf(e));

        newMeeting.setActive((String) (expired.equals("false") ? "true" : "false"));
        return newMeeting;
    }

    /**
     * The action to request the XML document that includes all meetings.
     *
     * @return the XML document of meetings
     * @throws java.lang.Exception
     */
    public Element myMeetings() throws Exception {
        return request("report-my-meetings", null);
    }

    // --- My Courses -----------------------------------------
    /**
     * @return @throws Exception
     */
    public List getMyCourses() throws Exception {
        Element courseDoc = request("report-my-courses", null);
        return XPath.selectNodes(courseDoc, "//course");
    }

    /**
     * @param status
     * @return
     * @throws Exception
     */
    public List getMyCourses(String status) throws Exception {
        String params = "filter-tr-status=" + status;
        Element courseDoc = request("report-my-courses", params);
        return XPath.selectNodes(courseDoc, "//course");
    }

    /**
     * @param e
     * @return
     * @throws Exception
     */
    public MyCourse getNextMyCourse(Element e) throws Exception {

        XPath namePath = XPath.newInstance("./name/text()");
        XPath idPath = XPath.newInstance("./@sco-id");
        XPath compPath = XPath.newInstance("./completed/text()");
        XPath trStatusPath = XPath.newInstance("./tr-status/text()");
        XPath closeDate = XPath.newInstance("./date-end/text()");

        return new MyCourse(namePath.valueOf(e),
                idPath.valueOf(e),
                scoUrl(idPath.valueOf(e)),
                compPath.valueOf(e),
                trStatusPath.valueOf(e),
                closeDate.valueOf(e));
    }
    
    // --- Courses --------------------------------------------

    /**
     * @param folderSco
     * @return
     * @throws Exception
     */
    public List getAllCourses(String folderSco) throws Exception {

        String params = "sco-id=" + folderSco + "&filter-type=content";
        Element courseDoc = request("sco-contents", params);
        return XPath.selectNodes(courseDoc, "//sco");
    }

    /**
     * @param search
     * @param folderScoID
     * @return
     * @throws Exception
     */
    public List getCoursesSearch(String search, String folderScoID) throws Exception {
        String params = "sco-id=" + folderScoID + "&filter-type=content";
        if (search != null) {
            params = params + "&filter-like-name=" + search;
        }
        Element courseDoc = request("sco-contents", params);
        return XPath.selectNodes(courseDoc, "//sco");
    }

    /**
     * @param e
     * @return
     * @throws Exception
     */
    public Course getNextCourse(Element e) throws Exception {

        XPath namePath = XPath.newInstance("./name/text()");
        XPath idPath = XPath.newInstance("./@sco-id");
        XPath descPath = XPath.newInstance("./description/text()");
        XPath closeDate = XPath.newInstance("./date-end/text()");

        return new Course(namePath.valueOf(e), idPath.valueOf(e), scoUrl(idPath.valueOf(e)), descPath.valueOf(e), closeDate.valueOf(e));
    }

    /**
     * @param scoID
     * @param folderScoID
     * @return
     * @throws Exception
     */
    public Course getCourse(String scoID, String folderScoID) throws Exception {
        Element e = request("sco-info", "sco-id=" + scoID);
        XPath namePath = XPath.newInstance("//name/text()");
        String nPath = (namePath.selectSingleNode(e) != null) ? ((Text) namePath.selectSingleNode(e)).getText() : "";
        XPath descPath = XPath.newInstance("//description/text()");
        String dPath = (descPath.selectSingleNode(e) != null) ? ((Text) descPath.selectSingleNode(e)).getText() : "";
        XPath closeDate = XPath.newInstance("//date-end/text()");
        String cDate = (closeDate.selectSingleNode(e) != null) ? ((Text) closeDate.selectSingleNode(e)).getText() : "";
        return new Course(nPath, scoID, scoUrl2(scoID), dPath, cDate);
    }

    // --- User -----------------------------------------
    /**
     * @param principalID
     * @param status
     * @return
     * @throws Exception
     */
    public List getUserTrainingTaken(String principalID, String status) throws Exception {

        String params = "filter-status=" + status + "&principal-id=" + principalID;
        Element courseDoc = request("report-user-trainings-taken", params);
        return XPath.selectNodes(courseDoc, "//row");
    }

    /**
     * @param e
     * @return
     * @throws Exception
     */
    public Training getNextUserTraining(Element e) throws Exception {

        XPath namePath = XPath.newInstance("./name/text()");
        XPath descPath = XPath.newInstance("./description/text()");
        XPath scoPath = XPath.newInstance("./@sco-id");
        XPath statusPath = XPath.newInstance("./@status");
        XPath dateTakenPath = XPath.newInstance("./date-taken/text()");

        return new Training(namePath.valueOf(e),
                descPath.valueOf(e),
                scoPath.valueOf(e),
                scoUrl(scoPath.valueOf(e)),
                statusPath.valueOf(e),
                dateTakenPath.valueOf(e));
    }

    /**
     * @return @throws Exception
     */
    public User getUser() throws Exception {
        Element e = request("principal-list", "filter-login=" + login);
        XPath pathUserID = XPath.newInstance("./principal-list/principal/@principal-id");
        XPath pathLogin = XPath.newInstance("./principal-list/principal/login/text()");
        XPath pathName = XPath.newInstance("./principal-list/principal/name/text()");
        XPath pathEmail = XPath.newInstance("./principal-list/principal/email/text()");
        return new User(pathUserID.valueOf(e), pathLogin.valueOf(e), pathName.valueOf(e), pathEmail.valueOf(e));
    }

    // --- Admin Functions -----------------------------
    /**
     * @param loginEmail
     * @return
     * @throws Exception
     */
    public User getUser(String loginEmail) throws Exception {

        Element e = request("principal-list", "filter-login=" + loginEmail);
        XPath pathUserID = XPath.newInstance("./principal-list/principal/@principal-id");
        XPath pathLogin = XPath.newInstance("./principal-list/principal/login/text()");
        XPath pathName = XPath.newInstance("./principal-list/principal/name/text()");
        XPath pathEmail = XPath.newInstance("./principal-list/principal/email/text()");

        if (loginEmail != null && !loginEmail.equals("")) {
            if (pathLogin.valueOf(e).equalsIgnoreCase(loginEmail)) {
                return new User(pathUserID.valueOf(e), pathLogin.valueOf(e), pathName.valueOf(e), pathEmail.valueOf(e));
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * @param userID
     * @throws Exception
     */
    public void deleteUser(String userID) throws Exception {

        Element e = request("principals-delete", "&principal-id=" + userID);
        XPath pathStatus = XPath.newInstance("./status/@code");
        if (!pathStatus.valueOf(e).equals("ok")) {
            throw new RuntimeException("Could not Delete User '" + userID + "'");
        }
    }

    /**
     * @param userID
     * @param fname
     * @param sname
     * @param login
     * @param email
     * @throws Exception
     */
    public void updateUser(String userID, String fname, String sname, String login, String email) throws Exception {

        Element e = request("principal-update",
                "principal-id=" + userID
                + "&first-name=" + fname
                + "&last-name=" + sname
                + "&login-name=" + login
                + "&email=" + email);

        XPath pathStatus = XPath.newInstance("./status/@code");
        if (!pathStatus.valueOf(e).equals("ok")) {
            throw new RuntimeException("Could not Update User.");
        }
    }

    /**
     * @param userID
     * @param password
     * @throws Exception
     */
    public void updateUserPassword(String userID, String password) throws Exception {

        Element e = request("user-update-pwd",
                "user-id=" + userID
                + "&password=" + password
                + "&password-verify=" + password);

        XPath pathStatus = XPath.newInstance("./status/@code");
        if (!pathStatus.valueOf(e).equals("ok")) {
            throw new RuntimeException("Could not Update Password.");
        }
    }

    /**
     * @param fname
     * @param sname
     * @param login
     * @param password
     * @param email
     * @return
     * @throws Exception
     */
    public String registerUser(String fname, String sname, String login, String password, String email) throws Exception {
        String emailStr = URLEncoder.encode(email);
        Element e = request("principal-update",
                "type=user&send-email=false&has-children=0"
                + "&first-name=" + fname + "&last-name=" + sname
                + "&login=" + login
                + "&password=" + password
                + "&email=" + emailStr);

        XPath pathUserID = XPath.newInstance("./principal/@principal-id");
        return pathUserID.valueOf(e);
    }

    /**
     * @param userID
     * @param groupID
     * @throws Exception
     */
    public void addUserToGroup(String userID, String groupID) throws Exception {

        Element e = request("group-membership-update", "&group-id=" + groupID + "&principal-id=" + userID + "&is-member=true");

        XPath pathStatus = XPath.newInstance("./status/@code");

        if (!pathStatus.valueOf(e).equals("ok")) {
            throw new RuntimeException("Could not add user to group '" + groupID + "'.");
        }
    }

    /**
     * @param userID
     * @param scoID
     * @throws Exception
     */
    public void enroll(String userID, String scoID) throws Exception {
        Element e = request("permissions-update", "&acl-id=" + scoID + "&principal-id=" + userID + "&permission-id=view");

        String responseStatus = getStatus(e);

        XPath codePath = XPath.newInstance("./status/@code");
        XPath subcodePath = XPath.newInstance("./status/@subcode");

        if (null != codePath && codePath.valueOf(e).equalsIgnoreCase("ok")) {

            if (null != subcodePath && subcodePath.valueOf(e).equalsIgnoreCase("no-quota")) {
                throw new QuotaException("Quota Reached.");
            }

        }
        if (codePath == null || (!codePath.valueOf(e).equals("ok"))) {
            throw new RuntimeException("Could not enrol.");
        }
    }

    /**
     * @param el
     * @return
     * @throws JDOMException
     */
    private String getStatus(Element el) throws JDOMException {

        XPath codePath = XPath.newInstance("//status/@code");
        XPath subcodePath = XPath.newInstance("//status/@subcode");

        String code = codePath.valueOf(el);
        String subcode = subcodePath.valueOf(el);

        StringBuffer status = new StringBuffer();

        if (null != code && code.length() > 0) {
            status.append(code);
        }
        if (null != subcode && subcode.length() > 0) {
            status.append(" - " + subcode);
        }

        XMLOutputter outp = new XMLOutputter();
        String s = outp.outputString(el);

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

        String dateStamp = sdf.format(date);
        String statusStr = String.format("TStamp[%s] - Status[%s] - XML[%s]", dateStamp, status.toString(), s);

        return statusStr;
    }

    /**
     * Determines if the user session is still alive on Adobe Connect returns
     * code = true if the user is still logged in otherwise returns code = false
     *
     * @param account_id - Principle-Id
     * @param session - BreezeSession-Id
     * @return
     * @throws Exception
     */
    public boolean isUserSessionAlive(String account_id, String session) throws Exception {

        URL queryUserUrl = breezeUrl("common-info", "account-id=" + account_id + "&session=" + session);
        URLConnection conn = queryUserUrl.openConnection();
        conn.connect();

        InputStream resultStream = conn.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len;
        while ((len = resultStream.read(buffer)) > -1) {
            baos.write(buffer, 0, len);
        }
        baos.flush();

        // Open new InputStreams using the recorded bytes
        // Can be repeated as many times as you wish
        InputStream is1 = new ByteArrayInputStream(baos.toByteArray());

        Document doc = new SAXBuilder(false).build(is1);
        Element e = doc.getRootElement();

        XPath pathUserID = XPath.newInstance("./common/user/@user-id");

        boolean userSessionAlive = (pathUserID.valueOf(e) != null && !pathUserID.valueOf(e).equals(""));

        return userSessionAlive;
    }

    /**
     * @return @throws Exception
     */
    public Quota getQuotas() throws Exception {

        Quota quota = null;
        Element e = request("report-quotas", "");
        Element childE = e.getChild("report-quotas");
        List quotas = childE.getChildren();
        Iterator i = quotas.iterator();

        while (i.hasNext()) {
            Element eItem = (Element) i.next();
            if (eItem.getAttribute("quota-id").getValue().equals("training-user")) {
                int used = Integer.parseInt(eItem.getAttribute("used").getValue());
                int limit = Integer.parseInt(eItem.getAttribute("limit").getValue());
                quota = new Quota(used, limit);
                break;
            }
        }
        return quota;
    }

    /**
     * @return @throws Exception
     */
    public boolean isUserQuotaExceeded() throws Exception {

        Quota quota = getQuotas();

        int used = quota.getUsed();
        int limit = quota.getLimit();

        boolean isExceeded = (used >= limit);
        return isExceeded;
    }

    /**
     * @return @throws Exception
     */
    public Quota getUserQuota() throws Exception {
        Quota quota = getQuotas();
        return quota;
    }

    /**
     * @param is
     * @param ecoding
     * @return
     * @throws IOException
     */
    public static String convertStreamToString(InputStream is, String ecoding) throws IOException {
        StringBuilder sb = new StringBuilder(Math.max(16, is.available()));
        char[] tmp = new char[4096];

        try {
            InputStreamReader reader = new InputStreamReader(is, ecoding);
            for (int cnt; (cnt = reader.read(tmp)) > 0;) {
                sb.append(tmp, 0, cnt);
                sb.append("\n");
            }
        } finally {
            is.close();
        }
        return sb.toString();
 }
}
