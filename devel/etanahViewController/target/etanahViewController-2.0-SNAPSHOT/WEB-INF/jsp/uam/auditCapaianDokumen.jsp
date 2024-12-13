<%-- 
    Document   : auditCapaianDokumen
    Created on : Jul 21, 2011, 12:18:30 PM
    Author     : zahidaziz
--%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>



<script type="text/javascript">
     
    //     $(document).ready(function(){
    //        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    //    });

        
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
    
    function viewDetails(frm, value) {
        var url = '${pageContext.request.contextPath}/uam/AuditDokumenDetails?getAuditDokDetails&idCapaian='+value;
        frm.action = url;
        frm.submit();
    }
    
  
    function checkFields() {
   

        var id = document.getElementById("idAuditData.idAudit").value;
        var jad = document.getElementById("jadual").value;
        var namaMed = document.getElementById("idAuditData.namaMedan").value;
        var akt = document.getElementById("aktiviti").value;
        var tDr = document.getElementById("tarikhDari").value;
        var tHg = document.getElementById("tarikhHingga").value;
    
        if(id=='' && jad=='' && namaMed=='' && akt=='' && tDr=='' && tHg==''){
            alert("Sila masukkan data pada mana-mana ruangan sebelum carian dilakukan!");
            return false;
        }
    
        return true;
    }

    function popup(){
        window.open("${pageContext.request.contextPath}/uam/carianKodDokumen?showForm","eTanah",
        "status=yes,toolbar=1,left=50,menubar=0,width=1000,height=800,scrollbars=yes");
        return;
    }
        function detailPengguna(pguna){
//        alert(pguna);
         var url = '${pageContext.request.contextPath}/uam/pengguna_Details?viewPenggunaDetails&idPengguna=' + pguna;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=850,height=600,scrollbars=yes");
    }
    function checkId(f) {

        if(f.elements['idPermohonan'].value == '') {
            alert('Sila masukkan Id Permohonan.');
            f.elements['idPermohonan'].focus();
            return false;
            
        }
        if(f.elements['hingga'].value != '') {
            if(f.elements['dari'].value == '') {
                alert('Sila Masukkan Tarikh Masuk Dokumen.');
                f.elements['dari'].focus();
                return false;
            }
        }
        else return true;
    }
</script>
<s:form  beanclass="etanah.view.uam.AuditCapaiDokumenActionBean" name="auditCapaianDokumenForm" id ="auditCapaianDokumen" onsubmit="return checkFields();"  >
    <s:messages/>
    <s:errors/>
    <div class ="subtitle">
        <fieldset class="aras1">
            <legend>Audit Dokumen</legend>
            <p>
                <font size="2" color="red">Perhatian :</font> Sila masukkan data-data berikut.<br>
                Bertanda <font color="red">*</font> adalah mandatori.
            </p>
            <p><label><font color="red">*</font>ID Permohonan :</label><s:text id="idP" name ="idPermohonan" /></p>
            <%--<p><label>ID Dokumen   :</label><s:text id="idDok" name ="dok.idDokumen" value ="${actionBean.dok.idDokumen}" onblur = "this.value=this.value.toUpperCase();"/></p> --%>
            <p><label>Pengguna   :</label><s:text name ="pengguna" value ="${actionBean.pengguna}"/></p>
            <p>
                <label>Kod Dokumen   :</label>
                <s:text id="idDok" name ="kodDokumen" value ="${actionBean.kodDokumen}" onblur ="this.value=this.value.toUpperCase();"/>
                <s:button name="" value="Kod Dokumen" class="btn" onclick="popup();return false;"/>
            </p>
            <p><label>Tarikh Masuk Dokumen  :</label>
                Tarikh Dari&nbsp; <s:text class="datepicker" id="dari"  formatPattern="dd/MM/yyyy" name ="tarikhMsDari" value =""/>
                &nbsp;&nbsp;Hingga&nbsp;&nbsp;
                <s:text class="datepicker" id="hingga"  formatPattern="dd/MM/yyyy" name ="tarikhMsHingga" value =""/>
            </p>
            <p><label>Jumlah Paparan :</label>
                <s:select name = "paparan" value = "${actionBean.paparan}">
                    <%--<s:option></s:option>--%>
                    <s:option value="5">5</s:option>
                    <s:option value="10">10</s:option>
                    <s:option value="15">15</s:option>
                    <s:option value="20">20</s:option>
                    <s:option value="25">25</s:option>
                    <s:option value="30">30</s:option>
                    <s:option value="35">35</s:option>
                    <s:option value="40">40</s:option>
                    <s:option value="45">45</s:option>
                    <s:option value="50">50</s:option>
                    <s:option value="100">100</s:option>
                </s:select>
            </p>
            <div align="center">
                <p>
                    <s:submit class ="btn" name ="viewAuditCapaiDok" value ="Cari" onclick="return checkId(this.form);"/>&nbsp;
                    <s:button name = "reset" class="btn" onclick= "clearText('auditCapaianDokumen')" value ="Isi Semula" /> 
                </p>
                <br>
            </div>
        </fieldset>

        <c:if test = "${actionBean.flag}">
            <div class ="content" align="center">
                <fieldset class ="aras1">
                    <legend>Senarai Audit Dokumen</legend>
                    <display:table name="${actionBean.auditDok}" id="line"  class="tablecloth" pagesize="${actionBean.paparan}" requestURI="${pageContext.request.contextPath}/uam/auditCapaianDokumen">
                        <display:column title="Bil" sortable = "true" style  = "verticle-align:baseline" >${line_rowNum}</display:column>

                        <display:column property="infoAudit.tarikhMasuk" title="Tarikh/Masa" sortable="true" style="verticle-align:baseline" format="{0,date,dd/MM/yyyy hh:mm:ss}"></display:column>
                        <%--<display:column property="infoAudit.dimasukOleh.idPengguna" title="Pengguna" sortable="true" href="pengguna_Details" paramId="viewPenggunaDetails&idPengguna" style="vertical-align:baseline"></display:column>--%>
                        <display:column  title="Pengguna" sortable="true" style="vertical-align:baseline"><a style="cursor: pointer" onclick="detailPengguna('${line.infoAudit.dimasukOleh.idPengguna}')">  ${line.infoAudit.dimasukOleh.idPengguna}</a></display:column>
                       

                        <display:column title="Dokumen" value="${line.dokumen.kodDokumen.kod}-${line.dokumen.kodDokumen.nama}"sortable="true" style="verticle-align:baseline"></display:column>
                        <display:column title="Jenis Capaian" value="${line.alasan}"sortable="true" style="verticle-align:baseline"></display:column>
                    </display:table>
                </fieldset>
            </div>
        </c:if>
    </div>
</s:form> 