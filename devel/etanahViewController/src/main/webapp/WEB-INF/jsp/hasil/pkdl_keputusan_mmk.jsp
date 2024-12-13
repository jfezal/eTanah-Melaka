<%-- 
    Document   : pkdl_keputusan_mmk
    Created on : Jan 5, 2010, 7:13:13 PM
    Author     : abu.mansur
--%>


<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<script language="javascript" >
        $(document).ready(function() {
            var flag = ${actionBean.flag}
            if(flag){
                $('#meeting').attr("disabled", "true");
                $('#datepicker').attr("disabled", "true");
                $('#nilai').attr("disabled", "true");
                $('#save').attr("style", "display:none");
        }
    });
</script>
<script type="text/javascript">
    function validateNumber(elmnt,content) {
    //if it is character, then remove it..
    if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function changeFormat2(row) {
        var num = document.getElementById('nilai').value;
        num = num.toString().replace(/\$|\,/g, '');
        if (isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100 + 0.50000000001);
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        if (cents < 10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3)) + ',' +
            num.substring(num.length - (4 * i + 3));
        $('#nilai').val((((sign) ? '' : '-') + num + '.' + cents));
    }
    
    function RemoveNonNumeric( strString )
    {
          var strValidCharacters = "1234567890.";
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

    function validationSimpan(){
        var kodUrusan = '${actionBean.permohonan.kodUrusan.kod}';
        if($("#meeting").val() == ""){
            alert('Sila masukkan " Bilangan Mesyuarat " terlebih dahulu.');
            $("#meeting").focus();
            return true;
        }
        if($("#datepicker").val() == ""){
            alert('Sila masukkan " Tarikh Disahkan " terlebih dahulu.');
            $("#datepicker").focus();
            return true;
        }
        if($("#nilai").val() == ""){
            if(kodUrusan == 'PKDL')
                alert('Sila masukkan " Jumlah Pengurangan Denda " terlebih dahulu.');
//            if(kodUrusan == 'PRPP' || kodUrusan == 'REMRI')
            if(kodUrusan == 'PRPP')
                alert('Sila masukkan " Jumlah yang perlu dibayar " terlebih dahulu.');
            $("#nilai").focus();
            return true;
        }
        return false;
    }

    function simpan(event, f){
            if(validationSimpan()){

            }
            else{
                doSubmit(f, event, 'page_div');
            }
        }

</script>

<s:form beanclass="etanah.view.stripes.hasil.PKDLKeputusanMMKActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Keputusan</legend>
            <p>
                <s:messages />
                <s:errors />&nbsp;
            </p>
            <p>
                <label><em>*</em>Bilangan Mesyuarat :</label>
                <s:text id="meeting" name="bilMesyuarat" maxlength="30"/>
                <s:hidden name="permohonanRujukanLuar.idRujukan"/>
            </p>
            <p>
                <label><em>*</em>Tarikh Disahkan :</label>
                <s:text name="tarikhMesyuarat" id="datepicker" class="datepicker"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
            </p>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PKDL'}">
                <p>
                    <label><em>*</em>Jumlah Pengurangan Denda RM :</label>
                    <s:text id="nilai" name="permohonan.nilaiKeputusan" value="0.00" formatPattern="#,###,##0.00" onkeyup="validateNumber(this,this.value);" maxlength="13"/>
                </p>
            </c:if>
            <%--<c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRPP' or actionBean.permohonan.kodUrusan.kod eq 'REMRI'}">--%>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRPP'}">
                <p>
                    <label><em>*</em>Jumlah yang perlu dibayar RM :</label>
                    <s:text id="nilai" name="permohonan.nilaiKeputusan" value="0.00" formatPattern="#,###,##0.00" onkeyup="validateNumber(this,this.value);" maxlength="13" onblur="changeFormat2('1')"/>
                </p>
            </c:if>
            <p>
                <label>&nbsp;</label>
                 <s:button name="simpanMesyuarat" id="save" value="Simpan" class="btn" onclick="simpan(this.name, this.form);"/>&nbsp;
                <%--<s:reset name="" value="Isi Semula" class="btn"/>--%>
            </p>
        </fieldset >
    </div>
</s:form>
