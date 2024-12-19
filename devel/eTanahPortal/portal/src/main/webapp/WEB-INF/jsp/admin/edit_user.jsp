<%-- 
    Document   : edit_user
    Created on : Apr 28, 2013, 7:11:47 PM
    Author     : wan.fairul
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">       
        <script type="text/javascript">
            $(document).ready(function() {
                $('.uppercase').each(function() {
                    $(this).blur(function() {
                        $(this).val($(this).val().toUpperCase());
                    });
                });
            });



            function isNumberKey(evt)
            {
                var charCode = (evt.which) ? evt.which : event.keyCode
                if (charCode > 31 && (charCode < 45 || charCode > 57 || charCode == 47))
                    return false;

                return true;
            }

        </script>
        <style>
            div#dialog-form p{
                margin: 0px; padding: 0 0 10px 0; 
            }
            div#dialog-form label {
                width: 10em;
                float: left;
                text-align: right;
                margin-right: 10px;
                display: block;
                color: #000;
                font-family:Tahoma;
                font-size: 12px;
                margin-left: -0.5px;
            }
            div#dialog-form font, textArea {
                font-size: 12px;
            }
            div#dialog-form select { font-size: 12px; font-family: 'Averia Libre', cursive; }
            div#dialog-form input { font-size: 12px;}
            .ui-dialog .ui-state-error { padding: .3em; }
            .validateTips { border: 1px solid transparent; padding: 0.3em; }
        </style>

    </head>
    <body>
        <div id="dialog-form" title="Tasks">
            <p class="validateTips">All form fields are required.</p>
            <s:useActionBean beanclass="sms.stripes.util.ListUtil" var="list"/>            
            <s:form beanclass="sms.stripes.action.UserAction" name="popupForm"> 
                <s:hidden name="staff.userId"/> 
                <fieldset>
                    <p>
                        <label>User ID :</label>
                        ${actionBean.staff.userId}&nbsp;
                    </p>
                    <p>
                        <label>Password :</label>
                        <s:password name="staff.password" style="width:250px"/>
                    </p>

                    <p>
                        <label>Email :</label>
                        <s:text name="staff.email" style="width:250px"/>
                    </p>
                    <p>
                        <label>Active :</label>                         
                        <s:radio name="staff.activate" value="Y"/>Yes                                       
                        <s:radio name="staff.activate" value="N"/> No                                           
                    </p>
                </fieldset>
            </div>
        </body>
    </s:form>
</html>
