<%-- 
    Document   : tambahsukudalamPihak_view
    Created on : Jun 7, 2011, 3:09:25 PM
    Author     : mazurahayati.yusop
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript">
</script>

<s:form beanclass="etanah.view.stripes.lelong.PihakPentingActionBean">
    <s:messages/>
    <s:errors/>&nbsp;

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Suku 
            </legend>
            <s:hidden name="namaPihak" value="${actionBean.pihak.idPihak}"/>
            <br>
            <label>Nama :  </label> 
            <font style="text-transform:uppercase;">${actionBean.pihak.nama}</font>

            <p>
                <label>Nama Suku : </label>
                <font style="text-transform:uppercase;">${actionBean.pihak.suku.nama} </font>
            </p>
            <p>
                <label>Pecahan Suku / Lengkongan : </label>
                <font style="text-transform:uppercase;">${actionBean.pihak.subSuku}</font>
            </p>
            <p>
                <label>Perut : </label>
                <font style="text-transform:uppercase;">${actionBean.pihak.keturunan}</font>
            </p>
            <p>
                <label>Luak : </label>
                <font style="text-transform:uppercase;">${actionBean.pihak.tempatSuku}</font>
            </p>
            <p align="center"><label></label>
                <br>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
    </div>
    <br>
</fieldset>
</s:form>

