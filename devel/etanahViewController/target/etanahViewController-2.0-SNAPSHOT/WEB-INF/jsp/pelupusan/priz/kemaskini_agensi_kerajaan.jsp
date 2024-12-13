<%-- 
    Document   : kemaskini_agensi_kerajaan
    Created on : Sep 21, 2012, 6:05:06 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kemaskini Pemohon</title>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">
    
    $(document).ready(function() {
        maximizeWindow();
        <c:if test="${!flag}">
            opener.refreshMaklumatPemohon();
            self.close();
        </c:if>
    })
    
     function validateNumber(elmnt,content) {
            //if it is character, then remove it..
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }
        
        function removeNonNumeric( strString )
        {
            var strValidCharacters = "1234567890";
            var strReturn = "";
            var strBuffer = "";
            var intIndex = 0;
            // Loop through the string
            for( intIndex = 0; intIndex < strString.length; intIndex++ )
            {
                strBuffer = strString.substr( intIndex, 1 );
                // Is this a number
                if( strValidCharacters.indexOf( strBuffer ) > -1 )
                {
                    strReturn += strBuffer;
                }
            }
            return strReturn;
        }
    </script>
<div class="subtitle" id="caw">
    <s:form beanclass="etanah.view.stripes.pelupusan.MaklumatAgensiKerajaanActionBean">
        <s:useActionBean beanclass="etanah.view.ListUtil" var="listUtil"/>
        <div align="center">
            <s:errors/>
            <s:messages/>
        </div>
        <fieldset class="aras1">
            <legend>Kemaskini Pemohon</legend>
            <s:hidden name="pemohon.idPemohon"/>
            <p>
                <label>Nama :</label>
                ${actionBean.pihak.nama}
                &nbsp;<s:hidden name="pihak.idPihak"/>
                 <s:hidden name="idPermohonan" id="idPermohonan"/>
            </p>
            <p>
                <label>Kod Agensi :</label>
                <s:hidden name="pihak.noPengenalan"/>
                ${actionBean.pihak.noPengenalan}&nbsp;
            </p>
            <p>
                <label>Alamat : </label>
                <s:text name="pihak.alamat1" value="${actionBean.pihak.alamat1}"size="40" id="alamat2"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.alamat2" value="${actionBean.pihak.alamat2}"size="40" id="alamat2"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.alamat3" value="${actionBean.pihak.alamat3}"size="40"  id="alamat3"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.alamat4" value="${actionBean.pihak.alamat4}"size="40"  id="alamat4"/>

            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="pihak.poskod" value="${actionBean.pihak.poskod}" size="40" id="poskod" maxlength="5" onkeyup="validateNumber(this,this.value);"/>              </p>
            <p>
                <label>Negeri :</label>
                <s:select name="negeri"  id="negeri" >
                    <s:option value="0">Pilih </s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
            </p>
             <p><label>&nbsp;</label>
                <s:submit name="kemaskiniAgensi" value="Kemaskini" class="btn"/>
                <s:button name="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>
    </s:form>
</div>
