<%-- 
    Document   : session
    Created on : Jul 22, 2013, 12:43:34 PM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.: Session Manager :.</title>
        <script type="text/javascript">
            function confirmation(userID) {
                var confirmResult = confirm('Are you sure want to kill session ' + userID + '?');
                return confirmResult;
            }
        </script>
    </head>
    <body>
        <s:messages/>
        <s:errors/>
        <s:form beanclass="com.theta.portal.stripes.SessionActionBean">
            <s:hidden name="counterId" />
            <s:hidden name="currentYear" />
            <div>
                <br>
                <div class="panel panel-default">
                    <!-- Toggle Heading -->
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse-1">
                                <i class="fa fa-angle-up control-icon"></i>
                                Session Manager
                            </a>
                        </h4>
                    </div>
                    <div id="collapse-1" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <div class="form-horizontal">

                                <s:submit name="viewList" value="Reload" class="btn" /><br />
                                <br>
                                <span class="sub-title">List All Active Session</span>    
                                <br>
                                <div class="table-responsive">
                                    <display:table class="table table-striped table-bordered" cellspacing="0" name="${actionBean.sessionActive}" requestURI="/sessionManager?viewList" id="line" >
                                        <display:column title="No."><center>${line_rowNum}.</center></display:column>
                                            <display:column title="User ID" property="userId" />                            
                                            <display:column title="Session ID" property="sessionId"/>
                                            <display:column title="Date Created" ><fmt:formatDate value="${line.dateCreated}" pattern="dd/MM/yyyy hh:mm:ss a"/></display:column>
                                            <display:column title="IP Address" property="computerName"/>
                                            <display:column title="Action" >
                                            <center>
                                                <a class="glyphicon glyphicon-trash"href="sessionManager?killSession&sessionKill=${line.sessionId}" onclick="javascript:return confirmation('${line.userId}');">
                                                </a>
                                            </center>
                                        </display:column>
                                    </display:table>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </s:form>
    </body>
</html>

