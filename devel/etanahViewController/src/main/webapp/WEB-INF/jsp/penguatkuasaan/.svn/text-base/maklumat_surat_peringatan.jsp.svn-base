<%-- 
    Document   : maklumat_surat_peringatan
    Created on : Mar 18, 2010, 4:36:56 PM
    Author     : farah.shafilla
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
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

        if($('#tempoh').val() == "")
        {
            alert("Sila Isi Tempoh Mengosongkan Tanah");
            $('#tempoh').focus();
            return false;
        }
        return true;
    }
</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatNotisActionBean" name="form1">
  <s:messages />
  <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Notis Kosongkan Tanah Terdahulu
            </legend>
            <div class="content">
             <p>
                    <label>Tarikh Notis :</label>
                    <fmt:formatDate value="${actionBean.notisPenguatkuasaan.tarikhNotis}" pattern="dd/MM/yyyy" />&nbsp;

                </p>
                <p>
                    <label>Tempoh Mengosongkan Tanah (Hari) :</label>
                   ${actionBean.notisPenguatkuasaan.tempohHari} hari&nbsp;

            </p>
            </div>
            </fieldset>
                    <br>
                    <c:if test="${edit}">
                   <fieldset class="aras1" id="simpanSurat">
            <legend>
                Maklumat Surat Peringatan Notis Kosongkan Tanah
            </legend>
        <div class="content" id="simpanSurat" >

                <p>
                    <label>Tempoh Mengosongkan Tanah (Hari) :</label>
                    <s:text name="suratPeringatan.tempohHari" value="${actionBean.suratPeringatan.tempohHari}" onkeyup="validateNumber(this,this.value);" maxlength="2" id="tempoh"/>
                   
                </p>                     
                 <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                 <s:hidden name="idNotis" value="${actionBean.suratPeringatan.idNotis}"/>
        <p align="right">
            <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
            <s:button class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');" name="simpanSurat" value="Simpan"/>
           <%-- <s:button  name="cetak" value="Cetak" class="btn"/>--%>
        </p>
         </div>
        </fieldset>
  </c:if>
            <c:if test="${view}">
                         <fieldset class="aras1">
            <legend>
                Maklumat Surat Peringatan Notis Kosongkan Tanah
            </legend>

                <p>
                    <label>Tempoh Mengosongkan Tanah :</label>
                   ${actionBean.suratPeringatan.tempohHari} hari

                </p>
        </fieldset>
            </c:if>
    </div>
</s:form>