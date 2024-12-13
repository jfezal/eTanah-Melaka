<%-- 
    Document   : rthsmaklumat_rayuan
    Created on : Nov 1, 2010, 2:28:51 PM
    Author     : Murali
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

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
<%--function save(event, f){


        var tempohHari=$("#tempohHari").val();
        var sebab = $("#sebab").val();

        if(tempohHari== ""){
            alert('Sila masukkan Tempoh Hari');
            document.form1.tempoLanjutan.focus();
        }else if(tempohHari <=0 || tempohHari>90){
            alert('Lanjutan tempoh mesti tidak kurang dari 90 hari');
            document.form1.tempoLanjutan.focus();
        }else if(sebab ==  ""){
            alert('Sila masukkan sebab-sebab lanjutan tempoh');
            document.form1.sebabLanjutanTempoh.focus();
        }else{

            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div').html(data);

            },'html');
        }
    }--%>


    function editPermohonan(val){
        window.open("${pageContext.request.contextPath}/strata/maklumat_rayuan?editPermohonanPopup&idPermohonan="+val, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=800,height=300");
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.strata.RTHSMaklumatRayuanActionBean" name="form1">
    <s:messages/>
    <s:errors/>
<c:if test="${edit1}">
    <div class="subtitle">
           <fieldset class="aras1">
                <legend>Kemasukan Maklumat rayuan</legend>
                <p>
                    <label>Tempoh Lanjutan Yang Dipohon :</label><s:text name="tempohHari" size="30" id="tempohHari" value="${actionBean.tempohHari}" maxlength="5" onkeyup="validateNumber(this,this.value);"/>&nbsp;<b>Hari</b>&nbsp;<em><%--(Lanjutan tempoh tidak kurang dari 90 hari)*--%></em>
                </p>&nbsp;

                <p>
                   <label>Sebab-sebab Lanjutan Tempoh :</label>
                    <s:textarea  name="sebab" cols="100" rows="5" class="normal_text" id="sebab"/>&nbsp;
                </p>&nbsp;
                  <p>

                    <label>&nbsp;</label>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                  </p>

           </fieldset>
    </div>
</c:if>
    <c:if test="${edit}">
       <div class="subtitle">
           <fieldset class="aras1">
                <legend>Kemasukan Maklumat rayuan</legend>
                <p>
                    <label>Tempoh Lanjutan Yang Dipohon :</label>
                    ${actionBean.permohonan.tempohHari}
                </p>&nbsp;

                <p>
                    <label>Sebab-sebab Lanjutan Tempoh :</label>
                    ${actionBean.permohonan.sebab}
                </p>&nbsp;

           </fieldset>
    </div>
  </c:if>
</s:form>

