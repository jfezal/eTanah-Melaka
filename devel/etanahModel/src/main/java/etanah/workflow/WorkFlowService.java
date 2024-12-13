/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.workflow;

/**
 *
 * @author solahuddin
 */
import etanah.util.WLUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.client.IWorkflowServiceClient;
import oracle.bpel.services.workflow.client.IWorkflowServiceClientConstants.CONNECTION_PROPERTY;
import oracle.bpel.services.workflow.client.WorkflowServiceClientFactory;
import oracle.bpel.services.workflow.query.ITaskQueryService;
import oracle.bpel.services.workflow.repos.Predicate;
import oracle.bpel.services.workflow.repos.TableConstants;
import oracle.bpel.services.workflow.task.ITaskAssignee;
import oracle.bpel.services.workflow.task.ITaskService;
import oracle.bpel.services.workflow.task.impl.TaskAssignee;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;

import org.apache.log4j.Logger;

public class WorkFlowService {
	
	
	private static final Properties bpelProps;
	
	
	private static final Map WF_SERVICE_PROP;
	
	private final static Logger logger = Logger.getLogger(WorkFlowService.class);
	private static final Logger syslog = Logger.getLogger("SYSLOG");
	private static final boolean DEBUG = logger.isDebugEnabled();
    private static boolean SOAP_CLIENT = true;
	

	static {
		// load properties for BPEL settings
		bpelProps = loadConfig("/etanah-bpel.properties");
		
		// create a map of properties
		WF_SERVICE_PROP = new HashMap();
        // check if we're running on weblogic
        if (WLUtil.isRunningOnWebLogic()) {
            String clientType = bpelProps.getProperty("client.type");
            if ("REMOTE".equals(clientType)) {
                SOAP_CLIENT = false;
                logger.info("Using EJB Remote client");
                WF_SERVICE_PROP.put(CONNECTION_PROPERTY.EJB_PROVIDER_URL,
                                    bpelProps.getProperty("server.t3"));
                WF_SERVICE_PROP.put(CONNECTION_PROPERTY.EJB_SECURITY_PRINCIPAL,
                                    WLUtil.decrypt(bpelProps.getProperty("admin.user")));
                WF_SERVICE_PROP.put(CONNECTION_PROPERTY.EJB_SECURITY_CREDENTIALS,
                                    WLUtil.decrypt(bpelProps.getProperty("admin.pass")));
            }
        }
        // fallback to SOAP webservice
        if (SOAP_CLIENT) {
            logger.info("Using SOAP client");
            WF_SERVICE_PROP.put(CONNECTION_PROPERTY.SOAP_END_POINT_ROOT,
                                bpelProps.getProperty("server.url"));
        }
	}

	private static Properties loadConfig(String propfile) {
		Properties prop = new Properties();
		try {
			logger.info("Loading " + propfile);
			prop.load(WorkFlowService.class.getResourceAsStream(propfile));
			logger.info("Loaded " + prop.size() + " values.");
		} catch (Exception e) {
			String msg = "Unable to load " + propfile + " configuration!";
			logger.fatal(msg, e);
			syslog.fatal(msg, e);
			// without this bpel will become unavailable
			throw new ExceptionInInitializerError(e);
		}
		return prop;
	}

	public static boolean initiateTask(String defID, String idPermohonan,
			String kodCaw, String pemilik, String urusan)
			throws Exception {

		logger.info("Initiating task.....");
                boolean retval = false;
                
                if (DEBUG) {
                        String msg = String.format("ID Workflow: %s\nID Mohon: %s\n"
                                        + "Kod Cawangan: %s\nPemilik: %s\nUrusan: %s", defID,
                                        idPermohonan, kodCaw, pemilik, urusan);
                        logger.debug(msg);
                }
                
                new JMSUtil().createTask(defID, idPermohonan, kodCaw, pemilik, urusan);
                retval = true;
                return retval;
	}

	public static Task getTask(String taskId, IWorkflowContext wfCtx)
			throws WorkflowException {

		// get the client
		IWorkflowServiceClient wfSvcClient = getWorkflowServiceClient();

		ITaskQueryService querySvc = wfSvcClient.getTaskQueryService();

		Task task = querySvc.getTaskDetailsById(wfCtx, taskId);
		// logger.info("Task is \n" + TaskUtil.getInstance().toString(task));
		task.getSystemMessageAttributes().getTextAttribute1();
		logger.info("Retrieved Task Successfully");

		return task;
	}

	public static IWorkflowContext authenticate(String user)
			throws WorkflowException {

		logger.info("Authenticating User " + user + ".....");

		String adminUser = WLUtil.decrypt(bpelProps.getProperty("admin.user"));
		String pass = WLUtil.decrypt(bpelProps.getProperty("admin.pass"));

		// get the client
		IWorkflowServiceClient wfSvcClient = getWorkflowServiceClient();
//                logger.debug("Getting task query service...");
		ITaskQueryService querySvc = wfSvcClient.getTaskQueryService();
//                logger.debug("Authenticating...");
		IWorkflowContext ctxTemp = querySvc.authenticate(adminUser,
				pass.toCharArray(), null);
		IWorkflowContext ctx = querySvc.authenticateOnBehalfOf(ctxTemp, user);

		logger.info(user + " authenticated successfully");

		return ctx;
	}

	public static IWorkflowContext authenticateAdmin() throws WorkflowException {

		String adminUser = WLUtil.decrypt(bpelProps.getProperty("admin.user"));
		String pass = WLUtil.decrypt(bpelProps.getProperty("admin.pass"));

		logger.info("Authenticating User " + adminUser + ".....");

		// get the client
		IWorkflowServiceClient wfSvcClient = getWorkflowServiceClient();
		ITaskQueryService querySvc = wfSvcClient.getTaskQueryService();
		IWorkflowContext ctx = querySvc.authenticate(adminUser,
				pass.toCharArray(), null);

		logger.info("Authenticated successfully");

		return ctx;
	}

	public static List queryTasks(IWorkflowContext ctx, String branch)
			throws WorkflowException {

		// get the client
		IWorkflowServiceClient wfSvcClient = getWorkflowServiceClient();

		ITaskQueryService querySvc = wfSvcClient.getTaskQueryService();

		List queryColumns = getQueryColumns();

		// fetch the actions the user can perform as part of the task
		List optionalInfo = new ArrayList();
		optionalInfo.add(ITaskQueryService.OptionalInfo.ALL_ACTIONS);

		String taskState = "ASSIGNED";
		Predicate tasksState = new Predicate(
				TableConstants.WFTASK_STATE_COLUMN, Predicate.OP_EQ, taskState);

		Predicate statePredicate = new Predicate(
				TableConstants.WFTASK_TEXTATTRIBUTE2_COLUMN,
				Predicate.OP_CONTAINS, branch);

		Predicate finalPredicate = new Predicate(tasksState, Predicate.AND,
				statePredicate);

		List tasksList = null;
		tasksList = querySvc.queryTasks(ctx, queryColumns, optionalInfo,
				ITaskQueryService.AssignmentFilter.MY_AND_GROUP, null,
				finalPredicate, null, 0, 0); // Paging

		if (tasksList == null) {
			logger.info("TaskList is null!!");
		}
		String msg = String.format("Queried %d tasks successfully",
				tasksList.size());
		logger.info(msg);
		return tasksList;
	}
        public static List queryTasksPaging(IWorkflowContext ctx, String branch, int start, int end)
			throws WorkflowException {

		// get the client
		IWorkflowServiceClient wfSvcClient = getWorkflowServiceClient();

		ITaskQueryService querySvc = wfSvcClient.getTaskQueryService();

		List queryColumns = getQueryColumns();

		// fetch the actions the user can perform as part of the task
		List optionalInfo = new ArrayList();
		optionalInfo.add(ITaskQueryService.OptionalInfo.ALL_ACTIONS);

		String taskState = "ASSIGNED";
		Predicate tasksState = new Predicate(
				TableConstants.WFTASK_STATE_COLUMN, Predicate.OP_EQ, taskState);

		Predicate statePredicate = new Predicate(
				TableConstants.WFTASK_TEXTATTRIBUTE2_COLUMN,
				Predicate.OP_CONTAINS, branch);

		Predicate finalPredicate = new Predicate(tasksState, Predicate.AND,
				statePredicate);

		List tasksList = null;
		tasksList = querySvc.queryTasks(ctx, queryColumns, optionalInfo,
				ITaskQueryService.AssignmentFilter.MY_AND_GROUP, null,
				finalPredicate, null, start, end); // Paging

		if (tasksList == null) {
			logger.info("TaskList is null!!");
		}
		String msg = String.format("Queried %d tasks successfully",
				tasksList.size());
		logger.info(msg);
		return tasksList;
	}
        
        public static Integer countTask(IWorkflowContext ctx, String branch)
			throws WorkflowException {

		// get the client
		IWorkflowServiceClient wfSvcClient = getWorkflowServiceClient();

		ITaskQueryService querySvc = wfSvcClient.getTaskQueryService();

		List queryColumns = getQueryColumns();

		// fetch the actions the user can perform as part of the task
		List optionalInfo = new ArrayList();
		optionalInfo.add(ITaskQueryService.OptionalInfo.ALL_ACTIONS);

		String taskState = "ASSIGNED";
		Predicate tasksState = new Predicate(
				TableConstants.WFTASK_STATE_COLUMN, Predicate.OP_EQ, taskState);

		Predicate statePredicate = new Predicate(
				TableConstants.WFTASK_TEXTATTRIBUTE2_COLUMN,
				Predicate.OP_CONTAINS, branch);

		Predicate finalPredicate = new Predicate(tasksState, Predicate.AND,
				statePredicate);

		return querySvc.countTasks(ctx, ITaskQueryService.AssignmentFilter.MY_AND_GROUP, null, finalPredicate);
	}
        
      
        public static List queryTasksASSIGNED(IWorkflowContext ctx, String branch)
			throws WorkflowException {

		// get the client
		IWorkflowServiceClient wfSvcClient = getWorkflowServiceClient();

		ITaskQueryService querySvc = wfSvcClient.getTaskQueryService();

		List queryColumns = getQueryColumns();

		// fetch the actions the user can perform as part of the task
		List optionalInfo = new ArrayList();
		optionalInfo.add(ITaskQueryService.OptionalInfo.ALL_ACTIONS);

		String taskState = "ASSIGNED";
		Predicate tasksState = new Predicate(
				TableConstants.WFTASK_STATE_COLUMN, Predicate.OP_EQ, taskState);

		Predicate statePredicate = new Predicate(
				TableConstants.WFTASK_TEXTATTRIBUTE2_COLUMN,
				Predicate.OP_CONTAINS, branch);

		Predicate finalPredicate = new Predicate(tasksState, Predicate.AND,
				statePredicate);

		List tasksList = null;
		tasksList = querySvc.queryTasks(ctx, queryColumns, optionalInfo,
				ITaskQueryService.AssignmentFilter.MY, null,
				finalPredicate, null, 0, 0); // Paging

		if (tasksList == null) {
			logger.info("TaskList is null!!");
		}
		String msg = String.format("Queried %d tasks successfully",
				tasksList.size());
		logger.info(msg);
		return tasksList;
	}

	public static List queryTasksByIdMohon(IWorkflowContext ctx, String idM)
			throws WorkflowException {

		logger.debug("idPermohonan = " + idM);

		// get the client
		IWorkflowServiceClient wfSvcClient = getWorkflowServiceClient();

		ITaskQueryService querySvc = wfSvcClient.getTaskQueryService();

		List queryColumns = getQueryColumns();

		// fetch the actions the user can perform as part of the task
		List optionalInfo = new ArrayList();
		optionalInfo.add(ITaskQueryService.OptionalInfo.ALL_ACTIONS);

		String taskState = "ASSIGNED";
		Predicate tasksState = new Predicate(
				TableConstants.WFTASK_STATE_COLUMN, Predicate.OP_EQ, taskState);

		Predicate idPredicate = new Predicate(
				TableConstants.WFTASK_TEXTATTRIBUTE1_COLUMN,
				Predicate.OP_CONTAINS, idM);

		Predicate finalPredicate = new Predicate(tasksState, Predicate.AND,
				idPredicate);

		List tasksList = querySvc.queryTasks(ctx, queryColumns, optionalInfo,
				ITaskQueryService.AssignmentFilter.MY_AND_GROUP, null,
				finalPredicate, null, 0, 0); // Paging

		if (tasksList == null) {
			logger.info("TaskList is null!!");
		}
		String msg = String.format("Queried %d tasks successfully",
				tasksList.size());
		logger.info(msg);
		return tasksList;
	}

        public static List queryTasksByIdMohonBranch(IWorkflowContext ctx, String idM, String branch)
			throws WorkflowException {

		logger.debug("idPermohonan = " + idM);

		// get the client
		IWorkflowServiceClient wfSvcClient = getWorkflowServiceClient();

		ITaskQueryService querySvc = wfSvcClient.getTaskQueryService();

		List queryColumns = getQueryColumns();

		// fetch the actions the user can perform as part of the task
		List optionalInfo = new ArrayList();
		optionalInfo.add(ITaskQueryService.OptionalInfo.ALL_ACTIONS);

		String taskState = "ASSIGNED";
		Predicate tasksState = new Predicate(
				TableConstants.WFTASK_STATE_COLUMN, Predicate.OP_EQ, taskState);

		Predicate idPredicate = new Predicate(
				TableConstants.WFTASK_TEXTATTRIBUTE1_COLUMN,
				Predicate.OP_CONTAINS, idM);

                Predicate statePredicate = new Predicate(
				TableConstants.WFTASK_TEXTATTRIBUTE2_COLUMN,
				Predicate.OP_CONTAINS, branch);

		Predicate finalPredicate = new Predicate(tasksState, Predicate.AND,
				idPredicate);

                Predicate finalPredicate2 = new Predicate(finalPredicate, Predicate.AND,
				statePredicate);

		List tasksList = querySvc.queryTasks(ctx, queryColumns, optionalInfo,
				ITaskQueryService.AssignmentFilter.MY_AND_GROUP, null,
				finalPredicate2, null, 0, 0); // Paging

		if (tasksList == null) {
			logger.info("TaskList is null!!");
		}
		String msg = String.format("Queried %d tasks successfully",
				tasksList.size());
		logger.info(msg);
		return tasksList;
	}


	public static List queryTasksByAdmin(String idM) throws WorkflowException {

		logger.debug("idPermohonan = " + idM);

		// get the client
		IWorkflowServiceClient wfSvcClient = getWorkflowServiceClient();

		ITaskQueryService querySvc = wfSvcClient.getTaskQueryService();

		List queryColumns = getQueryColumns();
		IWorkflowContext ctx = authenticateAdmin();

		// fetch the actions the user can perform as part of the task
		List optionalInfo = new ArrayList();
		optionalInfo.add(ITaskQueryService.OptionalInfo.ALL_ACTIONS);

		String taskState = "ASSIGNED";
		Predicate tasksState = new Predicate(
				TableConstants.WFTASK_STATE_COLUMN, Predicate.OP_EQ, taskState);

		Predicate idPredicate = new Predicate(
				TableConstants.WFTASK_TEXTATTRIBUTE1_COLUMN,
				Predicate.OP_CONTAINS, idM);

		Predicate finalPredicate = new Predicate(tasksState, Predicate.AND,
				idPredicate);

		List tasksList = querySvc.queryTasks(ctx, queryColumns, optionalInfo,
				ITaskQueryService.AssignmentFilter.ADMIN, null, finalPredicate,
				null, 0, 0); // Paging

		if (tasksList == null) {
			logger.info("TaskList is null!!");
		}

		logger.info("Queried tasks successfully");
		return tasksList;
	}
        
        

	public static List queryAllTasks(IWorkflowContext ctx, String idM)
			throws WorkflowException {

		// get the client
		IWorkflowServiceClient wfSvcClient = getWorkflowServiceClient();

		ITaskQueryService querySvc = wfSvcClient.getTaskQueryService();

		List queryColumns = getQueryColumns();

		// fetch the actions the user can perform as part of the task
		List optionalInfo = new ArrayList();
		optionalInfo.add(ITaskQueryService.OptionalInfo.ALL_ACTIONS);

		String taskState = "ASSIGNED";
		Predicate tasksState = new Predicate(
				TableConstants.WFTASK_STATE_COLUMN, Predicate.OP_EQ, taskState);

		Predicate idPredicate = new Predicate(
				TableConstants.WFTASK_TEXTATTRIBUTE1_COLUMN,
				Predicate.OP_CONTAINS, idM);

		Predicate finalPredicate = new Predicate(tasksState, Predicate.AND,
				idPredicate);

		List tasksList = querySvc.queryTasks(ctx, queryColumns, optionalInfo,
				ITaskQueryService.AssignmentFilter.ALL, null, finalPredicate,
				null, 0, 0); // Paging

		if (tasksList == null) {
			logger.info("TaskList is null!!");
		}
		String msg = String.format("Queried %d tasks successfully",
				tasksList.size());
		logger.info(msg);
		return tasksList;
	}

	public static List queryTasksByDate(IWorkflowContext ctx, Date date, String branch)
			throws WorkflowException {

		// get the client
		IWorkflowServiceClient wfSvcClient = getWorkflowServiceClient();

		ITaskQueryService querySvc = wfSvcClient.getTaskQueryService();

		List queryColumns = getQueryColumns();

		// fetch the actions the user can perform as part of the task
		List optionalInfo = new ArrayList();
		optionalInfo.add(ITaskQueryService.OptionalInfo.ALL_ACTIONS);

		String taskState = "ASSIGNED";

                 Predicate statePredicate = new Predicate(
				TableConstants.WFTASK_TEXTATTRIBUTE2_COLUMN,
				Predicate.OP_CONTAINS, branch);

		Predicate tasksState = new Predicate(
				TableConstants.WFTASK_STATE_COLUMN, Predicate.OP_EQ, taskState);

		Predicate datePredicate = new Predicate(
				TableConstants.WFTASK_ASSIGNEDDATE_COLUMN, Predicate.OP_AFTER,
				date);

		Predicate finalPredicate = new Predicate(tasksState, Predicate.AND,
				datePredicate);

                Predicate finalPredicate2 = new Predicate(finalPredicate, Predicate.AND, statePredicate);

		List tasksList = querySvc.queryTasks(ctx, queryColumns, optionalInfo,
				ITaskQueryService.AssignmentFilter.MY_AND_GROUP, null,
				finalPredicate2, null, 0, 0); // Paging

		if (tasksList == null) {
			logger.info("TaskList is null!!");
		}
		String msg = String.format("Queried %d tasks successfully",
				tasksList.size());
		logger.info(msg);
		return tasksList;
	}

	public static List queryTasksByDate3(IWorkflowContext ctx, Date date, String branch)
			throws WorkflowException {

		// get the client
		IWorkflowServiceClient wfSvcClient = getWorkflowServiceClient();

		ITaskQueryService querySvc = wfSvcClient.getTaskQueryService();

		List queryColumns = getQueryColumns();

		// fetch the actions the user can perform as part of the task
		List optionalInfo = new ArrayList();
		optionalInfo.add(ITaskQueryService.OptionalInfo.ALL_ACTIONS);

		String taskState = "ASSIGNED";
                
                Predicate statePredicate = new Predicate(
				TableConstants.WFTASK_TEXTATTRIBUTE2_COLUMN,
				Predicate.OP_CONTAINS, branch);

		Predicate tasksState = new Predicate(
				TableConstants.WFTASK_STATE_COLUMN, Predicate.OP_EQ, taskState);

		Predicate datePredicate = new Predicate(
				TableConstants.WFTASK_ASSIGNEDDATE_COLUMN, Predicate.OP_BEFORE,
				date);

		Predicate finalPredicate = new Predicate(tasksState, Predicate.AND,
				datePredicate);

                Predicate finalPredicate2 = new Predicate(finalPredicate, Predicate.AND, statePredicate);

		List tasksList = querySvc.queryTasks(ctx, queryColumns, optionalInfo,
				ITaskQueryService.AssignmentFilter.MY_AND_GROUP, null,
				finalPredicate2, null, 0, 0); // Paging

		if (tasksList == null) {
			logger.info("TaskList is null!!");
		}
		String msg = String.format("Queried %d tasks successfully",
				tasksList.size());
		logger.info(msg);
		return tasksList;
	}

	public static List queryTasksByDate2(IWorkflowContext ctx, Date date1,
			Date date2, String branch) throws WorkflowException {

		// get the client
		IWorkflowServiceClient wfSvcClient = getWorkflowServiceClient();

		ITaskQueryService querySvc = wfSvcClient.getTaskQueryService();

		List queryColumns = getQueryColumns();

		// fetch the actions the user can perform as part of the task
		List optionalInfo = new ArrayList();
		optionalInfo.add(ITaskQueryService.OptionalInfo.ALL_ACTIONS);

		String taskState = "ASSIGNED";
                Predicate statePredicate = new Predicate(
				TableConstants.WFTASK_TEXTATTRIBUTE2_COLUMN,
				Predicate.OP_CONTAINS, branch);
                
		Predicate tasksState = new Predicate(
				TableConstants.WFTASK_STATE_COLUMN, Predicate.OP_EQ, taskState);

		Predicate date1Predicate = new Predicate(
				TableConstants.WFTASK_ASSIGNEDDATE_COLUMN, Predicate.OP_AFTER,
				date1);

		Predicate date2Predicate = new Predicate(
				TableConstants.WFTASK_ASSIGNEDDATE_COLUMN, Predicate.OP_BEFORE,
				date2);

		Predicate prePredicate = new Predicate(tasksState, Predicate.AND,
				date1Predicate);

		Predicate finalPredicate = new Predicate(prePredicate, Predicate.AND,
				date2Predicate);

                Predicate finalPredicate2 = new Predicate(finalPredicate, Predicate.AND, statePredicate);

		List tasksList = querySvc.queryTasks(ctx, queryColumns, optionalInfo,
				ITaskQueryService.AssignmentFilter.MY_AND_GROUP, null,
				finalPredicate2, null, 0, 0); // Paging

		if (tasksList == null) {
			logger.info("TaskList is null!!");
		}
		String msg = String.format("Queried %d tasks successfully",
				tasksList.size());
		logger.info(msg);
		return tasksList;
	}

	private static List getQueryColumns() {
		List arr = new ArrayList();
		arr.add("TASKID");
		arr.add("TASKNUMBER");
		arr.add("TITLE");
		arr.add("PRIORITY");
		arr.add("ASSIGNEES");
		arr.add("STATE");
		arr.add("STAGE");
		arr.add("VERSION");
		arr.add("CREATEDDATE");
		arr.add("ASSIGNEDDATE");
		arr.add("DUEDATE");
		arr.add("ENDDATE");
		arr.add("CATEGORY");
		arr.add("TEXTATTRIBUTE1");
		arr.add("TEXTATTRIBUTE2");
		return arr;
	}

	public static Task reassignTask(IWorkflowContext ctx, String taskId,
			String toUser, String type) throws Exception {

		// get Human Workflow client
		IWorkflowServiceClient wfSvcClient = getWorkflowServiceClient();

		ITaskQueryService querySvc = wfSvcClient.getTaskQueryService();

		ITaskAssignee toUserAssignee = new TaskAssignee(toUser, type);
		List l = new ArrayList();
		l.add(toUserAssignee);

		updateTaskOutcome(taskId, ctx, "APPROVE");
//		IWorkflowContext ctxAdmin = authenticateAdmin();
//		ITaskService taskSvc = wfSvcClient.getTaskService();
		IWorkflowContext iwork = authenticate(toUser);
		return acquireTask(taskId, iwork);

		// Task reassignedTask = taskSvc.reassignTask(ctxAdmin, taskId, l);
		// reassignedTask.getSystemAttributes().getAssignedDate();
	}

        public static Task reassignTask(IWorkflowContext ctx, String taskId,
			String toUser, String type, String outcome) throws Exception {

		// get Human Workflow client
		IWorkflowServiceClient wfSvcClient = getWorkflowServiceClient();

		ITaskQueryService querySvc = wfSvcClient.getTaskQueryService();

		ITaskAssignee toUserAssignee = new TaskAssignee(toUser, type);
		List l = new ArrayList();
		l.add(toUserAssignee);

		updateTaskOutcome(taskId, ctx, outcome);
//		IWorkflowContext ctxAdmin = authenticateAdmin();
//		ITaskService taskSvc = wfSvcClient.getTaskService();
		IWorkflowContext iwork = authenticate(toUser);
		return acquireTask(taskId, iwork);

		// Task reassignedTask = taskSvc.reassignTask(ctxAdmin, taskId, l);
		// reassignedTask.getSystemAttributes().getAssignedDate();
	}

	public static Task acquireTask(String taskId, IWorkflowContext ctx)
			throws WorkflowException, StaleObjectException {

		// get the client
		IWorkflowServiceClient wfSvcClient = getWorkflowServiceClient();

		ITaskService taskSvc = wfSvcClient.getTaskService();                
		Task task = taskSvc.acquireTask(ctx, taskId);
		return task;

	}

        public static Task releaseTask (IWorkflowContext ctx, String taskId)
                throws StaleObjectException, WorkflowException {
            IWorkflowServiceClient wfSvcClient = getWorkflowServiceClient();

		ITaskService taskSvc = wfSvcClient.getTaskService();
                return taskSvc.releaseTask(ctx, taskId);
        }
        
        public static Task releaseTask (IWorkflowContext ctx, Task taskId)
                throws StaleObjectException, WorkflowException {
            IWorkflowServiceClient wfSvcClient = getWorkflowServiceClient();

		ITaskService taskSvc = wfSvcClient.getTaskService();
                return taskSvc.releaseTask(ctx, taskId);
        }

	public static String updateTaskOutcome(String taskId, IWorkflowContext ctx,
			String status) throws WorkflowException, StaleObjectException {
            
		// get the client
		IWorkflowServiceClient wfSvcClient = getWorkflowServiceClient();

		ITaskService taskSvc = wfSvcClient.getTaskService();
		// Task t = getTask(taskId, ctx);
		// taskSvc.acquireTask(ctx, t);
		Task task = taskSvc.updateTaskOutcome(ctx, taskId, status);
		return task.getSystemAttributes().getStage();
	}

	public static String updateBackOutcome(String taskId, IWorkflowContext ctx,
			String toUser) throws WorkflowException, StaleObjectException {

		// get the client
		IWorkflowServiceClient wfSvcClient = getWorkflowServiceClient();

		ITaskService taskSvc = wfSvcClient.getTaskService();
		Task task = taskSvc.updateTaskOutcome(ctx, taskId, "back");
		IWorkflowContext iwork = authenticate(toUser);
		acquireTask(taskId, iwork);
		return task.getSystemAttributes().getStage();
	}

	public static void withdrawTask(String taskId) throws WorkflowException,
			StaleObjectException {

		// get the client
		IWorkflowServiceClient wfSvcClient = getWorkflowServiceClient();
		IWorkflowContext ctx = authenticateAdmin();
		ITaskService taskSvc = wfSvcClient.getTaskService();
		Task task = taskSvc.withdrawTask(ctx, taskId);
	}

	public static String getCurrentState(String taskId)
			throws WorkflowException, StaleObjectException {

		// get the client
		IWorkflowContext ctxAdmin = authenticateAdmin();
//		IWorkflowServiceClient wfSvcClient = getWorkflowServiceClient();
		Task task = getTask(taskId, ctxAdmin);

		return task.getSystemAttributes().getState();
	}
        
        public static void updateTaskBranch(String idPermohonan, String newBranch, IWorkflowContext ctx) 
                throws StaleObjectException, WorkflowException {
            
            IWorkflowServiceClient wfSvcClient = getWorkflowServiceClient();           
            ITaskService taskSvc = wfSvcClient.getTaskService();
            ITaskQueryService querySvc = wfSvcClient.getTaskQueryService();            
            
            List tasks = queryTasksByAdmin(idPermohonan);
            if (!tasks.isEmpty()) {
                Task task = (Task) tasks.get(0);
                String taskId = task.getSystemAttributes().getTaskId();                  
                task = querySvc.getTaskDetailsById(ctx, taskId);
                task.getSystemMessageAttributes().setTextAttribute2(newBranch);
                taskSvc.updateTask(ctx, task);
            }            
        }


	private static IWorkflowServiceClient getWorkflowServiceClient() {
		// get the client
        logger.info("Retrieving workflow client");
        String type = "SOAP";
        if (!SOAP_CLIENT) type = "REMOTE";
		IWorkflowServiceClient wfSvcClient = WorkflowServiceClientFactory
				.getWorkflowServiceClient(type, WF_SERVICE_PROP, null);

		return wfSvcClient;
	}
        	

}