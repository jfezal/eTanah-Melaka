<%-- 
    Document   : permohonan_status_penguatkuasaan
    Created on : May 13, 2011, 03:49:04 PM
    Author     : latifah.iskak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<%-- <script type="text/javascript"
       src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>--%>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/styles/styles.css" />
        <title>Status Permohonan</title>

    </head>
    <body>
        <script type="text/javascript">

            function viewPermohonan(id){
                window.open("${pageContext.request.contextPath}/penguatkuasaan/status_permohonan?viewDetailPermohonan&idMohon="+id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=500");
            }

            function viewSejarah(id){
                window.open("${pageContext.request.contextPath}/lelong/status?viewSejarah&idPermohonan="+id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=500,height=700");
            }


        </script>
        <s:messages />
        <s:errors />

        <s:form action="/penguatkuasaan/status_permohonan" >
            <br>
            <fieldset class="aras1">
                <legend>
                    Keputusan Carian Berdasarkan Daerah
                </legend>
                <p>

                    <display:table class="tablecloth" name="${actionBean.daerahList}"  cellpadding="0" cellspacing="0" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column   title="ID Permohonan" >
                            <a href="#" onclick="viewPermohonan('${line.permohonan.idPermohonan}');return false;">${line.permohonan.idPermohonan}</a>
                        </display:column>
                        <display:column property="bandarPekanMukim.daerah.nama" title="Daerah" />
                        <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    </display:table>


                    &nbsp;
                </p>
            </fieldset>

        </s:form>

    </body>
</html>
