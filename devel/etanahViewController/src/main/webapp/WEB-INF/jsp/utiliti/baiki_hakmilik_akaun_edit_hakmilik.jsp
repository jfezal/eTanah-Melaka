<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<style type="text/css">
    td.s{
        font-weight:normal;
        color:black;
        text-align: left;
    }
    input , select {
        width: 95%;
    }
    td{
        font-size: 100% !important;
    }
    .arrow { background:transparent url(${pageContext.request.contextPath}/pub/images/arrows.png) no-repeat scroll 0px -16px; width:100%; height:16px; display:block;}
    .up { background-position:0px 0px;}

</style>
<script type="text/javascript">
    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

    function onlyNos(e, t) {
            try {
                if (window.event) {
                    var charCode = window.event.keyCode;
                }
                else if (e) {
                    var charCode = e.which;
                }
                else { return true; }
                if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                    return false;
                }
                return true;
            }
            catch (err) {
                alert(err.Description);
            }
    }
    
    function validateNumber(elmnt, content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric(strString)
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for (intIndex = 0; intIndex < strString.length; intIndex++)
        {
            strBuffer = strString.substr(intIndex, 1);
            // Is this a number
            if (strValidCharacters.indexOf(strBuffer) > -1)
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }
</script>

<s:form beanclass="etanah.view.utility.KemaskiniHakmilikAkaun" name="form1">
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <div id="page_div">
        <s:messages />
        <s:errors />
        <div class="subtitle">            
            <fieldset class="aras1">
                <legend>
                    Baiki Maklumat Hakmilik
                </legend>
                <s:hidden name="idHakmilik"/>
                <div align="center">
                    <table class="tablecloth" width="70%">
                        <tr><th colspan="3">ID Hakmilik - ${actionBean.hakmilik.idHakmilik}</th></tr>
                        <tr><th colspan="3">Butiran Hakmilik</th></tr>
                        <tr><td colspan="3"><table class="tablecloth">
                                    <tr><th>Perkara</th><th>Maklumat Semasa</th><th>Maklumat Baru</th></tr>
                                    <tr><td>Status :</td><td class="s">${actionBean.statusHakmilik}</td>
                                        <td>
                                            <s:select style="text-transform:uppercase" name="kodStatusHakmilik" id="kodStatusHakmilik" value="">
                                                <s:option value="">Sila Pilih</s:option>
                                                <s:options-collection collection="${actionBean.listKodStatusHakmilik}" label="nama" value="kod" />
                                            </s:select>
                                        </td></tr>
                        </tr>
                        <tr><td>Tarikh Batal :</td><td class="s"><c:if test="${actionBean.hakmilik.tarikhBatal eq null}">Tiada</c:if><fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${actionBean.hakmilik.tarikhBatal}"/>&nbsp;</td><td class="s"><s:text name="tarikhBatal" id="tarikhBatal" class="datepicker" onfocus=""/>
                                <br><center><font color="black" size="1">ATAU</font><br>
                            <s:submit name="saveTarikhNull" value="NULLkan Tarikh" class="btn" onclick=""/></td></tr>
                        <tr><td>Tarikh Daftar Asal :</td><td class="s"><c:if test="${actionBean.hakmilik.tarikhDaftarAsal eq null}">Tiada</c:if><fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${actionBean.hakmilik.tarikhDaftarAsal}"/>&nbsp;</td><td class="s"><s:text name="tarikhDaftarAsal" id="tarikhDaftarAsal" class="datepicker" onfocus=""/>
                                <br><center><font color="black" size="1">ATAU</font><br>
                            <s:submit name="saveTarikhDaftarAsalNull" value="NULLkan TrhDftrAsal" class="btn" onclick=""/></td></tr>
                        <tr><td>Cukai Sebenar :</td><td class="s">${actionBean.hakmilik.cukaiSebenar}</td><td><s:text name="cukaiSebenar" onkeyup="validateNumber(this,this.value);"/></td></tr>
                        <tr><td>No versi DHDE :</td><td class="s">${actionBean.hakmilik.noVersiDhde}</td>
                            <td><s:text name="noVersiDhde" onkeypress="return onlyNos(event,this);"/></td></tr>
                        <tr><td>No versi DHKE :</td><td class="s">${actionBean.hakmilik.noVersiDhke}</td>
                            <td><s:text name="noVersiDhke" onkeypress="return onlyNos(event,this);"/>
                            <br><center><font color="black" size="1">ATAU</font><br>
                            <s:submit name="saveVersiNull" value="NULLkan Versi" class="btn" onclick=""/></td></tr>
                        <tr><td>ID Dokumen DHDE :</td><td class="s">${actionBean.hakmilik.dhde.idDokumen}</td>
                            <td><s:text name="iddokumenDhde" onkeypress="return onlyNos(event,this);"/></td></tr>
                        <tr><td>ID Dokumen DHKE :</td><td class="s">${actionBean.hakmilik.dhke.idDokumen}</td>
                            <td><s:text name="iddokumenDhke" onkeypress="return onlyNos(event,this);"/></td></tr>
                        <tr><td colspan="3">
                                <div align="center">                                    
                                    <s:submit name="saveHakmilik" value="Simpan" class="btn" onclick=""/>
                                    <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:self.close()"/>
                                </div>
                            </td></tr>
                    </table>
                </div>
            </fieldset>
        </div>
    </div>
</s:form>
