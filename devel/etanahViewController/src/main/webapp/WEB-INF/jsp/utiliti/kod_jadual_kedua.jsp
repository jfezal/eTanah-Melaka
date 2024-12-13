<%-- 
    Document   : kod_jadual_kedua.jsp
    Created on : Apr 19, 2010
    Author     : hisham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
   
 </script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carian Jadual ${actionBean.kodNegeri}</title>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
        <script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>        
    </head>
    <body>
        <s:errors/>
        <s:messages/>        
        <div class="subtitle">
            <s:form action="/utiliti/kod_jadual_kedua">
                <%--  <s:hidden name="idHakmilik" />--%>
                <fieldset class="aras1">
                    
                    <c:if test="${actionBean.kodNegeri eq 'Kedua'}">
                        <legend> Carian Jadual Kedua </legend>                                            
                    </c:if>
                    <c:if test="${actionBean.kodNegeri eq 'Keenam'}">
                        <legend> Carian Jadual Keenam [Section 108] - "Persons To Whom Or Bodies To Which Land Subject To Part VIII May Be Charged" </legend>                                              
                    </c:if>
                    <br>
                    <p>
                        <label>Kod :</label>
                        <s:text name="kod" id="kod"/>
                    </p>
                    <p>
                        <label>Nama :</label>
                        <s:text name="nama" id="nama" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:submit name="cari" value="Cari" class="btn" />
                    </p>
                </fieldset>
            </div>
            <br>
            <div class="subtitle">
                <fieldset class="aras1">
                    <display:table style="width:100%" class="tablecloth" name="${actionBean.list}" pagesize="10" cellpadding="0" cellspacing="0" 
                            id="row" requestURI="/utiliti/kod_jadual_kedua">
                        <display:column title="Kod" property="kod" />
                        <display:column title="Nama" property="nama" />
                        <display:column title="No.Warta" property="noRujukan" />
                        <display:column title="Status" >
                            ${row.aktif == 'Y' ? 'Aktif' : 'Tidak Aktif' }
                        </display:column>
                        <display:column title="Tarikh Warta">
                             <fmt:formatDate pattern="dd/MM/yyyy" value="${row.infoAudit.tarikhKemaskini}"/>
                        </display:column>
                    </display:table>              
                </fieldset>
            </s:form>
        </div>
    </body>
</html>