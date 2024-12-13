<%--
    Document   : penambahan_kosgantirugi
    Created on : Apr 27, 2010, 3:32:06 PM
    Author     : massita
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<script type="text/javascript">
<%--   $(document).ready(function() {
        $('#senaraiTuntutanKos0').val('T');
        $('#amaun0').val((${actionBean.jumCaraBayar}).toFixed(2));
        $('#amaunt0').val((${actionBean.jumCaraBayar}).toFixed(2));
        $('#jumCaraBayar').val((${actionBean.jumCaraBayar}).toFixed(2));
        var bil = parseInt(${actionBean.bil});
            var amnt = parseInt(${actionBean.jumCaraBayar});
            if(amnt <= 0){
                $('#next').attr("disabled", "true");
                $('#tble').attr("disabled", "true");
            }
        for (var i = 1; i < bil; i++){
            $('#amaunt'+i).attr("disabled", "true");
            $('#amaunt'+i).val("0.00");
            $('#amaun'+i).attr("disabled", "true");
            $('#amaun'+i).val("0");
            $("#field"+i).hide();
        }
    });--%>
</script>
<script type="text/javascript">

<%--function save(event, f){
            
                alert("else");                
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                alert("url:"+url);
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    alert("close");                    
                    self.close();
                },'html');
            
        }
        
          $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });--%>

function validation() {
        if($("#item").val() == ""){
            alert('Sila masukkan " Jenis Kerosakan " terlebih dahulu.');
            $("#item").focus();
            return true;
        }

        if($("#amaunTuntutan").val() == ""){
            alert('Sila masukkan " Amaun Yang Dituntut(RM) " terlebih dahulu.');
            $("#amaunTuntutan").focus();
            return true;
        }

        if($("#amaunSebenar").val() == ""){
            alert('Sila masukkan " Amaun Yang Diluluskan (RM) " terlebih dahulu.');
            $("#amaunSebenar").focus();
            return true;
        }

    }

        function save(event, f){
            if(validation()){

            }
            else{
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.close();
                },'html');
            }
        }
          $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });

function validateForm(){
    //self.opener.refreshPageganti();
    self.close();
}
function test(f) {
        $(f).clearForm();
    }
function updateTotal (inputTxt)
    {
        var total = 0;
        var bil = ${actionBean.bil};
        for (var i = 0; i <bil; i++){
    	var a = document.getElementById('form1' + i)
            if (a == null) break;
            if ((isNaN(a.value))||((a.value) ==""))
            {
                alert("Nombor tidak sah");
                inputTxt.value = RemoveNonNumeric(a);
                $('#form1').val("0.00");
                return;
            }
            total += parseFloat(a.value);
        }
        var t = document.getElementById('form1');
        t.value = total.toFixed(2);
        inputTxt.value = parseFloat(inputTxt.value).toFixed(2);
    }


<%--function RemoveNonNumeric( strString ){
        var strValidCharacters = "1234567890.";
        var strReturn = "0.00";
        var strBuffer = "0.00";
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
    }--%>
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

function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
    <s:form beanclass="etanah.view.stripes.pengambilan.perbicaraanKosGantiRugiActionBean" id="form1">
        <s:messages/>
        <div class="subtitle" id="page">
            <fieldset class="aras1">
                <legend>Tuntutan Kerosakan</legend>&nbsp;
                <div class="content">
                <p>
                    <label for="item"><font color="red">*</font>Jenis Kerosakan :</label>
                    <s:text name="permohonanTuntutanKos1.item" id="item"/> &nbsp;
                </p>
                <p>
                    <label for="amaunTuntutan"><font color="red">*</font>Amaun Yang Dituntut(RM) :</label>
                    <s:text name="permohonanTuntutanKos1.amaunTuntutan" size="20" id="amaunTuntutan"   />
                    <%--onkeyup="validateNumber(this,this.value);"--%>
                </p>
                <p>
                    <label for="amaunSebenar"><font color="red">*</font>Amaun Yang Diluluskan (RM) :</label>
                    <s:text name="permohonanTuntutanKos1.amaunSebenar" size="20" id="amaunSebenar" />
                </p>
                    
                 <p align="right">
                    <%--<s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>--%>
                   <s:hidden name="idPermohonanPihak" value="${actionBean.idPermohonanPihak}"/>
                    <s:hidden name="idPihak" value="${actionBean.pihak.idPihak}"/>
                    <s:hidden name="idKos" value="${actionBean.permohonanTuntutanKos.idKos}"/>
                    <%--<s:text name="idPermohonanPihak" value="${actionBean.permohonanTuntutanKos.pihak}"/>--%>
                    <s:reset name="" value="Isi Semula" class="btn"/>
                    <%--<s:submit name="simpanPopup" id="simpan" value="Simpan" class="btn" onclick="return saveTambah();"/>--%>
                    <s:button name="simpanPopup1" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                    
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
                </div>
                <br>
            </fieldset>
           </div>
        </s:form>




