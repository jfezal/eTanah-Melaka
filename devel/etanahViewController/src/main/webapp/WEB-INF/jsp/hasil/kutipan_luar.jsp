<%-- 
    Document   : kutipan_luar.jsp
    Created on : 23-Mar-2012 11:38:16 AM
    Author     : haqqiem
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="list" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
        <title>Kutipan Luar</title>

    </head>
    <body>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-plugin-validate.js"></script>
    <style type="text/css">
        <!--
        .style1 {font-size: 21px; color: #003366; font-family: "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;}
        .errFld { border: 1px solid red; }
        -->
    </style>
    <script language="javascript">

        $(document).ready(function() {
            $('input:text').each(function(){
                $(this).focus(function() { $(this).addClass('focus')});
                $(this).blur(function() { $(this).removeClass('focus')});
            });
            $('select').each(function(){
                $(this).focus(function() { $(this).addClass('focus')});
                $(this).blur(function() { $(this).removeClass('focus')});
            });


            $('#txtFile').change( function() {
                $('#filename').text($('#txtFile').val());
            });

            $('form').submit( function () {
                return formValidation();
            });

        });


        function reload(val, form) {
            form.action = form.action + '?reloadAgensi&kodAgensi=' + val;
            form.submit();
        }

        function doSubmit(frm, event) {
            frm.action = frm.action + '?' + event;
            frm.submit();
        }

    </script>


    <stripes:messages />
    <stripes:errors />

    <stripes:form action="/hasil/kutipan_luar">

        <fieldset class="aras1">

            <legend>Muat Naik Kutipan Luar</legend>


            <p>
                <label for="kodUrusankod"><em>*</em>Fail</label>
                <stripes:file name="txtFile" id="txtFile" class="required"/>
                <span id="filename"/>
                <stripes:hidden name="fileName" value="${actionBean.txtFile.fileName}"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <stripes:submit name="check" value="Semak" class="btn" />
            </p>

        </fieldset>
        <br/>
        <c:if test="${!empty actionBean.senaraiTrans}">
            <fieldset class="aras1">

                <legend>Senarai Akaun Cukai Terlibat</legend>
                <p align="center">


                    <display:table name="${actionBean.senaraiTrans}" class="tablecloth" id="row2" style="width:50% ">
                        <display:column title="Bil">${row2_rowNum}</display:column>
                        <display:column title="Tarikh" >
                            <fmt:formatDate value="${row2.tarikh}" pattern="dd/MM/yyyy hh:mm a"/>
                        </display:column>
                        <display:column title="Nombor Akaun">${row2.noAkaun}</display:column>
                        <display:column title="Nombor Resit">${row2.noResit}</display:column>
                        <display:column title="Amaun (RM)" style="text-align:right">
                            <fmt:formatNumber value="${row2.bayaran}" pattern="#,###,###.00"/>
                        </display:column>                        
                    </display:table>

                </p>
                <p>
                    <label>&nbsp;</label>
                    <stripes:button name="uploadFile" value="Muat Naik" class="btn" onclick="doSubmit(this.form, this.name);"/>
                </p>
            </fieldset>
        </c:if>
    </stripes:form>
    <br/>
</div>
</body>
</html>