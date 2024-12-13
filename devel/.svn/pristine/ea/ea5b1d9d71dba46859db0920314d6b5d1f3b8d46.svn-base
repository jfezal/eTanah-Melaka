<%-- 
    Document   : inactiveUser
    Created on : Oct 28, 2010, 3:57:18 PM
    Author     : Izam
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>

<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
    function editPengguna(frm, val) {
        var url = '${pageContext.request.contextPath}/uam/new_update?searchInactiveUser&idPengguna='+val;
        frm.action = url;
        frm.submit();
    }
</script>

<div id="all">
    <s:form beanclass="etanah.view.uam.InactiveUserActionBean" name="userForm">
        <s:messages/>
        <s:errors/>
        <div class="subtitle displaytag">
            <fieldset class="aras1">
                <legend>Carian Id Pengguna Tidak Aktif</legend>
                <p>
                    <font size="1" color="Red">
                        <strong>Sila masukkan Id Pengguna untuk membuat carian.</strong>
                    </font>
                </p>
                <p>
                    <label><font color="red">*</font>Id Pengguna :</label>
                    <s:text name="pguna.idPengguna"/>
                    <s:submit name="searchUser" id="search" value="Cari" class="btn"/>
                </p>
            </fieldset>
            <fieldset class="aras1">
                <legend>Senarai Pengguna Tidak Aktif</legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.userList}" pagesize="20"
                                   cellpadding="0" cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/uam/inactive_user">
                        <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                        <display:column property="idPengguna" title="ID Pengguna" style="vertical-align:baseline" href="new_update" paramId="searchInactiveUser&idPengguna"/>
                        <display:column property="jawatan" title="Jawatan" style="vertical-align:baseline"/>
                        <display:column property="infoAudit.tarikhKemaskini" title="Tarikh Batal" format="{0,date,dd/MM/yyyy}" style="vertical-align:baseline"/>
                        <display:column title="Kemaskini">
                            <div align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                     id='rem_${line_rowNum}' onclick="editPengguna(document.forms.userForm, '${line.idPengguna}')" onmouseover="this.style.cursor='pointer';">
                            </div>
                        </display:column>
                    </display:table>
                </div>
            </fieldset>
        </div>
    </s:form>
</div>
