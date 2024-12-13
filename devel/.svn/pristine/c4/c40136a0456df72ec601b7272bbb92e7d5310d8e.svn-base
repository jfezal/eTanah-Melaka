<%-- 
    Document   : maklumat_tawaran_kompaun
    Created on : Feb 23, 2010, 4:48:13 PM
    Author     : farah.shafilla
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
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
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<script src="<%= request.getContextPath()%>/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript">

    function test(f) {
        $(f).clearForm();
    }
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

   function validateForm(){

        if(document.form1.rujukan.value=="")
        {
            alert("Sila Isi No.Siri Kompaun");
            return false;
        }
        if(document.form1.amaun.value=="")
        {
            alert("Sila Isi Jumlah Kompaun");
            return false;
        }
        if(document.form1.tempoh.value=="")
        {
            alert("Sila Isi Tempoh");
            return false;
        }        

    self.opener.refreshPage2();
    self.close();
    }
    function test(f) {
        $(f).clearForm();
    }
     function updateTotal(){
     document.form1.amaun.value = parseFloat(document.form1.amaun.value).toFixed(2);
}
</script>
<s:form beanclass="etanah.view.penguatkuasaan.TawaranKompaunActionBean" name="form1">
        <s:messages />
<div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tawaran Kompaun
            </legend>
            <div class="content">
            <p>
                    <label>No.Siri Kompaun :</label>
                    <s:text name="kompaun.noRujukan" id="rujukan"/>&nbsp;
                </p>
                <p>
                    <label>Jumlah Kompaun (RM) :</label>
                    <s:text id="amaun" name="kompaun.amaun" formatPattern="0.00" onkeyup="validateNumber(this,this.value);"/>&nbsp;
                </p>
                <p>
                    <label>Tempoh (hari) :</label>
                    <s:text name="kompaun.tempohHari" onkeyup="validateNumber(this,this.value);" maxlength="2" id="tempoh"/>&nbsp;
                </p>                
                <p>
                    <label>Diserahkan Kepada :</label>
                    ${actionBean.kompaun.isuKepada},${actionBean.kompaun.noPengenalan}&nbsp;
                  
                </p>
                
           <p align="right">
           <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                    <s:hidden name="idKompaun" value="${actionBean.kompaun.idKompaun}"/>
                   <s:submit name="simpanSingle" id="simpan" value="Simpan" class="btn" onclick="return validateForm()"/>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
           </p>
            </div>
        </fieldset>
    </div>
</s:form>
