package etanah.workflow;

/**
 * @author solahuddin
 */
import com.google.inject.Singleton;
import etanah.util.WLUtil;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.log4j.Logger;

class JMSUtil {

    private final static Logger logger = Logger.getLogger(WorkFlowService.class);
    private static final Logger syslog = Logger.getLogger("SYSLOG");
    private static final boolean DEBUG = logger.isDebugEnabled();
    //
    private final static String DEFAULT_TASK_QUEUE_NAME = "jms/eTanahQueue";
    private final static String DEFAULT_TASK_QUEUE_CONNECTION = "jms/eTanahCF";
    private static final String TASK_PAYLOAD = "<ns1:WorkflowInfoReq xmlns:ns1=\"http://www.etanahjv.com\">\n"
            + "  <ns1:title>%s</ns1:title>\n"
            + "  <ns1:creator>%s</ns1:creator>\n"
            + "  <ns1:appID>%s</ns1:appID>\n"
            + "  <ns1:branch>%s</ns1:branch>\n"
            + "</ns1:WorkflowInfoReq>";
    //
    private static final Properties namingProps;
    //
    private static final AtomicReference<Context> context = new AtomicReference<Context>();
    private static final AtomicReference<QueueConnectionFactory> connFactory = new AtomicReference<QueueConnectionFactory>();
    
    /**
     *
     * @param defID
     * @param idPermohonan
     * @param kodCaw
     * @param pemilik
     * @param urusan
     * @throws Exception
     */
    protected void createTask(String defID, String idPermohonan,
            String kodCaw, String pemilik, String urusan) throws Exception {

        logger.info("Looking up for JMS connection");
        QueueConnection connection = getConnectionFactory().createQueueConnection();
//        logger.info("connection is : " + connection);
        connection.start();
        logger.info("connection started");

        QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
//        logger.info("session : " + session);

        Queue queue = (Queue) getContext().lookup(DEFAULT_TASK_QUEUE_NAME);


        QueueSender sender = session.createSender(queue);
//        logger.info("sender : " + sender);
        QueueReceiver receiver = session.createReceiver(queue);
//        logger.info("receiver : " + receiver);

        // create and set payload
        String taskXmlString = String.format(TASK_PAYLOAD,
                                             urusan, pemilik, idPermohonan, kodCaw);
        sendMessage(session, sender, taskXmlString, defID);

        close(connection, session, sender, receiver, queue);
        logger.info("Task initiated successfully");
    }

    private void sendMessage(QueueSession session, QueueSender sender, String xmlData, String flowType) {
        try {
            TextMessage message = session.createTextMessage(xmlData);
            message.setJMSType(flowType);
            sender.send(message);
        } catch (JMSException jmse) {
            logger.error("Error while sending message", jmse);
        }
    }

    /**
     * Old code emphasize on synchronized initialization, just mimic it.
     * @return
     */
    private QueueConnectionFactory getConnectionFactory() {
        QueueConnectionFactory conn = connFactory.get();
        if (conn != null) return conn;
        try {
            conn = (QueueConnectionFactory) getContext().lookup(DEFAULT_TASK_QUEUE_CONNECTION);
            connFactory.set(conn);
        } catch (NamingException e) {
            logger.fatal("Error while getting QueueConnectionFactory", e);
        }
        return conn;
    }

    /**
     * Force closing of Queue resources
     * @param connection
     * @param session
     * @param sender
     * @param receiver
     * @param queue
     */
    private void close(QueueConnection connection, QueueSession session,
            QueueSender sender, QueueReceiver receiver, Queue queue) {
        if (sender != null) {
            try {
                sender.close();
            } catch (Exception e) {
                logger.error("Error when closing", e);
            }
        }
        if (receiver != null) {
            try {
                receiver.close();
            } catch (Exception e) {
                logger.error("Error when closing", e);
            }
        }
        if (session != null) {
            try {
                session.close();
            } catch (Exception e) {
                logger.error("Error when closing", e);
            }
        }
        if (queue != null) {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error("Error when closing", e);
            }
        }
    }

    static {
        namingProps = loadConfig("/etanah-jms-naming.properties");
    }

    private static Properties loadConfig(String propfile) {
        Properties prop = new Properties();
        try {
            if (DEBUG) {
                logger.debug("Loading " + propfile);
            }
            prop.load(WorkFlowService.class.getResourceAsStream(propfile));
            // decrypt values,
            prop.setProperty("java.naming.security.principal", WLUtil.decrypt(prop.getProperty("java.naming.security.principal")));
            prop.setProperty("java.naming.security.credentials", WLUtil.decrypt(prop.getProperty("java.naming.security.credentials")));            
        } catch (Exception e) {
            String msg = "Unable to load " + propfile + " configuration!";
            logger.fatal(msg, e);
            syslog.fatal(msg, e);
            // without this bpel will become unavailable
            throw new ExceptionInInitializerError(e);
        }
        return prop;
    }

    /**
     * Old code emphasize on synchronized initialization, just mimic it.
     * @return
     */
    private Context getContext() {
        Context ctx = context.get();
        if (ctx != null) {
            return ctx;
        }
        try {
            ctx = new InitialContext(namingProps);   
            context.set(ctx);
        } catch (NamingException e) {
            logger.fatal(e.getMessage(), e);
        }
        return ctx;
    }


    @Override
    /**
     * If we ever need to close this
     */
    protected void finalize() throws Throwable {
        connFactory.set(null);
        Context ctx = context.get();
        if (ctx != null) {
            if (DEBUG) logger.debug("Closing JNDI InitialContext");
            try {
                ctx.close();
            } catch (Exception e) {
                logger.info("Error while closing JNDI context", e);
            } finally {
                context.set(null);
                super.finalize();
            }
        }
    }
}
