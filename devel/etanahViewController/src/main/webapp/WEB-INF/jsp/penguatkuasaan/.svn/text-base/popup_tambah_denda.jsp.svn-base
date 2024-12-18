<%--
    Document   : popup_tambah_denda
    Created on : Oct 18, 2011, 11:38:22 AM
    Author     : latifah.iskak
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
    $(document).ready(function() {
        
    });

    function save(event, f){

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshPage();
            self.close();
        },'html');

    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function test(f) {
        $(f).clearForm();
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

        if(document.form1.tempohDenda.value=="")
        {
            alert("Sila masukkan tempoh (hari)");
            $('#tempohDenda').focus();
            return false;
        }
     
        if(document.form1.amaun.value=="")
        {
            alert("Sila masukkan amaun denda");
            $('#amaun').focus();
            return false;
        }

        if(document.form1.amaun.value=="0.00")
        {
            alert("Sila masukkan amaun denda");
            $('#amaun').focus();
            return false;
        }

        var bil =  ${fn:length(actionBean.hakMilikPihakBerkepentinganList)};
        var j = 0;
        for (var i = 0; i < bil; i++){

            var pilih = document.getElementById('pilihPihak'+i).checked;

            if( pilih == true){
                j++;
            }
        }
            
        if(j == 0 ){
            alert("Sila pilih pihak yang hendak dikenakan denda ");
            return false;
        }
        return true;
    }

    function test(f) {
        $(f).clearForm();
    }

    function updateTotal(){
        document.form1.amaun.value = parseFloat(document.form1.amaun.value).toFixed(2);
        if( document.form1.amaun.value <500){
            alert("Sila masukkan jumlah denda melebihi atau sama dengan RM 500");
            document.form1.amaun.value = "0.00";
        }
    }

</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatPerintahDendaActionBean" name="form1">
    <s:messages />

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Perintah Denda
            </legend>
            <div class="content">

                <p>
                    <label><em>*</em>Tempoh (Hari) :</label>
                    <s:text name="tempohDenda" onkeyup="validateNumber(this,this.value);" maxlength="2" id="tempohDenda"/>&nbsp;
                </p>
                <p>
                    <label><em>*</em>Jumlah Denda (RM) :</label>
                    <s:text id="amaun" name="amaun" size="15" class="number" formatPattern="0.00" maxlength="10" onkeyup="validateNumber(this,this.value);" onblur="javascript:updateTotal(this, this.value);"/>&nbsp;
                </p>

                <br>
                <fieldset class="aras2">
                    <legend>
                        Senarai Pihak
                    </legend>
                    <br>
                    <div class="instr-fieldset">
                        <font color="red" size="1"> ** Sila pilih pihak yang akan dikenakan denda</font>
                    </div>
                    <br>
                    <div align="center" >

                        <display:table name="${actionBean.hakMilikPihakBerkepentinganList}" id="line1" class="tablecloth">
                            <display:column title="Bil">
                                ${line1_rowNum}
                            </display:column>
                            <display:column title="Pilih">
                                <s:checkbox name="pilihPihak${line1_rowNum-1}" id="pilihPihak${line1_rowNum-1}" value="${line1.pihak.idPihak}"/>
                            </display:column>
                            <display:column property="pihak.nama" title="Nama" />
                            <display:column property="pihak.noPengenalan" title="No. Pengenalan" />
                            <display:column title="Alamat" class="alamat">
                                ${line1.pihak.alamat1}<c:if test="${line1.pihak.alamat2 ne null}">,</c:if>
                                ${line1.pihak.alamat2}<c:if test="${line1.pihak.alamat3 ne null}">,</c:if>
                                ${line1.pihak.alamat3}<c:if test="${line1.pihak.alamat4 ne null}">,</c:if>
                                ${line1.pihak.alamat4}<c:if test="${line1.pihak.poskod ne null}">,</c:if>
                                ${line1.pihak.poskod}<c:if test="${line1.pihak.negeri.kod ne null}">,</c:if>
                                ${line1.pihak.negeri.nama}
                            </display:column>
                            <display:column property="pihak.noTelefon1" title="No. Telefon" />
                            <display:column property="pihak.email" title="Alamat Email" />
                            <display:column property="jenis.nama" title="Status" class="${line1_rowNum}"/>
                        </display:table>

                    </div>
                    <p>&nbsp;</p>
                </fieldset>
                <br>
                <p align="center">
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                    <s:button class="btn" name="simpan" value="Simpan" onclick="if(validateForm())save(this.name,this.form);"/>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>

            </div>
        </fieldset>
    </div>
</s:form>
