/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.ldap;

import etanah.util.WLUtil;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Properties;
import java.util.List;
import javax.naming.Context;
import javax.naming.NameClassPair;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.AttributeInUseException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.NoSuchAttributeException;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

/**
 *
 * @author solahuddin
 */
public class LDAPManager {

    /** The connection, through a <code>DirContext</code>, to LDAP */
    private DirContext context;
    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LDAPManager.class);
    private static final Properties conf;
    private static final String LDAP_SERVER;
    private static final boolean USE_LDAP;

    public LDAPManager() throws NamingException {
        context = getInitialContext();
    }

    static {
        try {
            conf = new Properties();
            logger.info("Loading LDAP config");
            java.io.InputStream in = LDAPManager.class.getResourceAsStream("/ldap.properties");
            conf.load(in);
            if (logger.isDebugEnabled()) {
                logger.debug(conf);
            }
                        
            // Using cluster address, should be the configured virtual IP of the application
            String url = conf.getProperty("ldap.url");
            
            // minin Jun 2013 - try using own managed server address
            // the magic keyword
            if ("try-lookup-mbean-server-address".equals(url)) {
                InetSocketAddress address = WLUtil.getServerAddress();
                if (address != null && !address.isUnresolved()) {
                    url = "ldap://" + address.getHostName() + ":" + address.getPort();
                    logger.info("Using WebLogic server address " + url + ", for authentication.");
                }
                else {
                    // remove the magic keyword
                    url = null;
                }
            }
            
            if (url != null && url.startsWith("ldap://")) {
                LDAP_SERVER = url;
            }
            else {
                LDAP_SERVER = new StringBuffer("ldap://")
                                .append(conf.getProperty("ldap.server")).append(":")
                                .append(conf.getProperty("ldap.port")).toString();
            }
            if ("true".equals(conf.getProperty("ldap.login"))) {
                USE_LDAP = true;
            } else {
                USE_LDAP = false;
            }
            
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public void addUser(String username, String firstName,
            String lastName, String password, String description)
            throws NamingException {

        // Create a container set of attributes
        Attributes container = new BasicAttributes();

        // Create the objectclass to add
        Attribute objClasses = new BasicAttribute("objectclass");
        objClasses.add("top");
        objClasses.add("person");
        objClasses.add("organizationalPerson");
        objClasses.add("inetOrgPerson");
        objClasses.add("wlsUser");

        // Assign the username, first name, and last name
        String cnValue = "";
        if(firstName.equals(lastName)) cnValue = firstName;
        else cnValue = new StringBuffer(firstName).append(" ").append(lastName).toString();

        Attribute cn = new BasicAttribute("cn", cnValue);
        Attribute givenName = new BasicAttribute("givenName", firstName);
        Attribute sn = new BasicAttribute("sn", lastName);
        Attribute uid = new BasicAttribute("uid", username);
        Attribute desc = new BasicAttribute("description", description);
        // Add password
        Attribute userPassword =
                new BasicAttribute("userpassword", password);

        // Add these to the container
        container.put(objClasses);
        container.put(cn);
        container.put(sn);
        container.put(givenName);
        container.put(uid);
        container.put(userPassword);
        container.put(desc);

        // Create the entry
        try{
        	context.createSubcontext(getUserDN(username), container);
        } catch (javax.naming.NameAlreadyBoundException e){
        	logger.error(e.getMessage(), e);
        	throw e;
        }
    }

    public void deleteUser(String username) throws NamingException {
        try {
            context.destroySubcontext(getUserDN(username));
        } catch (NameNotFoundException e) {
            // If the user is not found, ignore the error
        }
    }
    
    public static boolean isValidUser(String username, String password) {
        DirContext ctx = null;
        boolean auth = false;
        try {
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            props.put(Context.PROVIDER_URL, LDAP_SERVER);
            props.put(Context.SECURITY_AUTHENTICATION, "simple");          
            props.put("com.sun.jndi.ldap.connect.pool", "true");
            props.put("com.sun.jndi.ldap.connect.pool.maxsize","125");
            props.put("com.sun.jndi.ldap.connect.pool.timeout","300000");

            if ((username != null) && (!username.equals(""))) {
                props.put(Context.SECURITY_PRINCIPAL, "uid="+username+","+conf.getProperty("user.ou"));
                props.put(Context.SECURITY_CREDENTIALS,
                          ((password == null) ? "" : password));
            }

            // Create the initial context
            ctx = new InitialDirContext(props);
            auth = true;
        } catch (javax.naming.NameNotFoundException e) {
            logger.error("Pengguna tidak wujud - " + username, e);
//            throw e;
        } catch (NamingException e) {
            // Any other error indicates couldn't log user in
            logger.error("Unable to authenticate", e);
//            throw e;
        } finally {
            if (ctx != null) {
                try {
                    ctx.close();
                } catch (Exception e) {
                    //ignore
                }
            }
        }
        return auth;
    }

    public void addGroup(String name, String description)
            throws NamingException {

        // Create a container set of attributes
        Attributes container = new BasicAttributes();

        // Create the objectclass to add
        Attribute objClasses = new BasicAttribute("objectclass");
        objClasses.add("top");
        objClasses.add("groupOfURLs");
        objClasses.add("groupOfUniqueNames");

        // Assign the name and description to the group
        Attribute cn = new BasicAttribute("cn", name);
        Attribute desc = new BasicAttribute("description", description);

        // Add these to the container
        container.put(objClasses);
        container.put(cn);
        container.put(desc);

        // Create the entry
        context.createSubcontext(getGroupDN(name), container);
    }

    public void deleteGroup(String name) throws NamingException {
        try {
            context.destroySubcontext(getGroupDN(name));
        } catch (NameNotFoundException e) {
            // If the group is not found, ignore the error
        } 
    }

    public void assignGroupToUser(String username, String groupName)
            throws NamingException,NameNotFoundException {

        try {
            ModificationItem[] mods = new ModificationItem[1];

            Attribute mod =
                    new BasicAttribute("wlsMemberOf",
                    getGroupDN(groupName));
            mods[0] = new ModificationItem(DirContext.ADD_ATTRIBUTE, mod);
            context.modifyAttributes(getUserDN(username), mods);
            
        } catch (AttributeInUseException e) {
            // If user is already added, ignore exception
        } catch (NameNotFoundException e) {
            throw e;
        } 
    }

    public void removeGroupFromUser(String username, String groupName)
            throws NamingException {

        try {
            ModificationItem[] mods = new ModificationItem[1];

            Attribute mod =
                    new BasicAttribute("wlsMemberOf",
                    getGroupDN(groupName));
            mods[0] =
                    new ModificationItem(DirContext.REMOVE_ATTRIBUTE, mod);
            context.modifyAttributes(getUserDN(username), mods);
        } catch (NoSuchAttributeException e) {
            // If user is not assigned, ignore the error
        } 
    }

    public void modifyUser(String username, String firstName, String lastName, String description)
            throws NamingException {

        try {
            ModificationItem[] mods = new ModificationItem[5];

            String cnValue = "";
            if(firstName.equals(lastName)) cnValue = firstName;
            else cnValue = new StringBuffer(firstName).append(" ").append(lastName).toString();

            Attribute cn = new BasicAttribute("cn", cnValue);
            Attribute givenName = new BasicAttribute("givenName", firstName);
            Attribute sn = new BasicAttribute("sn", lastName);
            Attribute uid = new BasicAttribute("uid", username);
            Attribute desc = new BasicAttribute("description", description);

            mods[0] =
                    new ModificationItem(DirContext.REPLACE_ATTRIBUTE, cn);
            mods[1] =
                    new ModificationItem(DirContext.REPLACE_ATTRIBUTE, givenName);
            mods[2] =
                    new ModificationItem(DirContext.REPLACE_ATTRIBUTE, uid);
            mods[3] =
                    new ModificationItem(DirContext.REPLACE_ATTRIBUTE, desc);
            mods[4] =
                    new ModificationItem(DirContext.REPLACE_ATTRIBUTE, sn);

            context.modifyAttributes(getUserDN(username), mods);
        } catch (NoSuchAttributeException e) {
            // If user is not assigned, ignore the error
        } 
    }

    public void changePassword(String username, String password)
            throws NamingException {
        try {
            ModificationItem[] mods = new ModificationItem[1];
            // new password
            Attribute userPassword =
                    new BasicAttribute("userpassword", password);
            mods[0] =
                    new ModificationItem(DirContext.REPLACE_ATTRIBUTE, userPassword);

            context.modifyAttributes(getUserDN(username), mods);
        } catch (NoSuchAttributeException e) {
            // If user is not assigned, ignore the error
        } 
    }
    public boolean findUserByUsername(String username) throws NamingException {
        String filter = "uid=" + username;

        // Set up search constraints
        SearchControls cons = new SearchControls();
        cons.setSearchScope(SearchControls.ONELEVEL_SCOPE);

        NamingEnumeration results =
                context.search(conf.getProperty("user.ou"), filter, cons);

        return results.hasMore() ? true : false;
    }

    public List getGroupsByUsername(String username) throws NamingException {
        List groups = new ArrayList();

        // Set up criteria to search on
        String filter = "uid=" + username;

        // Set up search constraints
        SearchControls cons = new SearchControls();
        cons.setSearchScope(SearchControls.ONELEVEL_SCOPE);

        NamingEnumeration results =
                context.search(conf.getProperty("user.ou"), filter, cons);
        while (results.hasMore()) {
            SearchResult result = (SearchResult) results.next();
            Attribute at = result.getAttributes().get("wlsMemberOf");
            if(at != null) {
                for (int i = 0; i < at.size(); i++) {
                    groups.add(getGroupCN(at.get(i).toString()));
                }
            }
        }
        return groups;
    }

    public List getAllUser() throws NamingException {
        List usersList = new ArrayList();

        NamingEnumeration results =
                context.list(conf.getProperty("user.ou"));
        while (results.hasMore()) {
            NameClassPair result = (NameClassPair) results.next();
            String user = getUserUID(result.getName());
            if (!user.equalsIgnoreCase("weblogic") && !user.equalsIgnoreCase("OracleSystemUser")) {
                usersList.add(user);
            }
        }

        return usersList;
    }
    public List getAllGroup() throws NamingException {
        List usersList = new ArrayList();

        NamingEnumeration results =
                context.list(conf.getProperty("group.ou"));
        while (results.hasMore()) {
            NameClassPair result = (NameClassPair) results.next();
            String user = getGroupCN(result.getName());
            usersList.add(user);
            logger.info("Peranan: "+user);
        }

        return usersList;
    }

    //TODO:x berfungsi lagi
    public String getPassword(String username) throws NamingException {
        // Set up criteria to search on
        String filter = "uid=" + username;

        // Set up search constraints
        SearchControls cons = new SearchControls();
        cons.setSearchScope(SearchControls.SUBTREE_SCOPE);
        String pass = "";
        NamingEnumeration results =
                context.search(conf.getProperty("user.ou"), filter, cons);
        while (results.hasMore()) {
            SearchResult result = (SearchResult) results.next();

            Attribute userPassword = result.getAttributes().get("userpassword");
            pass = (String) userPassword.get(0).toString();
        }
        return pass;
    }

    private String getUserDN(String username) {
        return new StringBuffer().append("uid=").append(username).append(",").append(conf.getProperty("user.ou")).toString();
    }

    private String getUserUID(String userDN) {
        int start = userDN.indexOf("=");
        int end = userDN.indexOf(",");

        if (end == -1) {
            end = userDN.length();
        }

        return userDN.substring(start + 1, end);
    }

    private String getGroupDN(String name) {
        return new StringBuffer().append("cn=").append(name).append(",").append(conf.getProperty("group.ou")).toString();
    }

    private String getGroupCN(String groupDN) {
        int start = groupDN.indexOf("=");
        int end = groupDN.indexOf(",");

        if (end == -1) {
            end = groupDN.length();
        }

        return groupDN.substring(start + 1, end);
    }

    private DirContext getInitialContext()
            throws NamingException {

        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory");
        props.put(Context.PROVIDER_URL, LDAP_SERVER);
        props.put("com.sun.jndi.ldap.connect.pool", "true");
        props.put("com.sun.jndi.ldap.connect.pool.maxsize","125");
        props.put("com.sun.jndi.ldap.connect.pool.timeout","300000");

        String username = WLUtil.decrypt(conf.getProperty("ldap.admin"));
        String password = WLUtil.decrypt(conf.getProperty("ldap.pass"));
        if ((username != null) && (!username.equals(""))) {
            props.put(Context.SECURITY_AUTHENTICATION, "simple");
            props.put(Context.SECURITY_PRINCIPAL, username);
            props.put(Context.SECURITY_CREDENTIALS,
                    ((password == null) ? "" : password));
        }

        return new InitialDirContext(props);
    }
    
    public static boolean useLdap() {
        return USE_LDAP;
    }
    
    public void close() {
        if (context != null) {
                try {
                    context.close();
                } catch (NamingException e) {
                    //ignore
                }
            }
    }
}
