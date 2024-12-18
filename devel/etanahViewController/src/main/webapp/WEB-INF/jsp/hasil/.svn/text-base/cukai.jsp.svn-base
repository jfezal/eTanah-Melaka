<%-- 
    Document   : cukai
    Created on : Jan 18, 2013, 11:58:46 AM
    Author     : abdul.hakim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloths.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<div align="center"><table style="width:100%" bgcolor="green">
        <tr><td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Kutipan Cukai</font>
                </div>
            </td>
        </tr>
    </table></div>
    <s:form beanclass="etanah.view.stripes.hasil.CukaiActionBean" id="hasil">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Kutipan Cukai Tanah</legend>
            <p>
                <label>Bayaran Cukai : </label>
                <s:select name="typeOfPayment" id="jenisBayaran" onchange="viewField(this.value)">
                    <s:option label="Bayaran Tunggal" value="0" />
                    <s:option label="Bayaran Pukal" value="1" />
                </s:select>
            </p>
                
            <p id="noAkaunSingle"><label for="noAkaun"><em>*</em>Nombor Akaun</label>
                <s:text name="akaun.noAkaun" id="noAkaun" size="30" onblur="javascript:checkingHakmilik(this.value, 'akaun');" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <br>
        </fieldset>
    </div><br>    
    
    <div class="subtitle" id="pukal">
        <fieldset class="aras1">
        <legend>ID Hakmilik Yang Terlibat</legend>
        <p>
            <label for="bilHakmilik">Bil. Hakmilik:</label>
            <s:text name="bilHakmilik" id="bilHakmilik" size="4" onkeyup="return checkBilAkaun(this);"/> atau kurang<br>
        </p>
        <p>
            <label>&nbsp;</label>
            <s:submit name="update" value="Kemaskini" class="btn"/>
        </p>
        <div align="center">
            <table border=0 align="center" class="tablecloth">
                <tr>
                    <th>Bil.</th>
                    <th class="akaun">Nombor Akaun</th>
                    <th class="baki">Jumlah</th>
                    <th>Bil</th>
                    <th class="akaun">Nombor Akaun</th>
                    <th class="baki">Jumlah</th>
                    <th>Bil</th>
                    <th class="akaun">Nombor Akaun</th>
                    <th class="baki">Jumlah</th>
                </tr>

                <tr>
                    <c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >
                        <td align="center" style="text-align:center;">${i}. </td>
                        <td  class="akaun">
                            <s:text name="accList[${i - 1}].noAkaun" id="akaunList${i - 1}" style="width:15em"/>
                        </td>
                        <td class="baki">
                            <s:text name="accList[${i - 1}].baki" id="baki${i - 1}" size="6" readonly="true" style="text-align:right"/>
                        </td>
                        <c:if test="${i % 3 == 0}" >
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </div>
        <p></p>
        <p>
            <label>Jumlah Perlu Dibayar (RM):</label>
            <s:text name="" id="tot" readonly="true" style="background:transparent;border:0 px;text-align:right;" size="10"/>
        </p>
    <br>
    </div>

    <div align="center"><table style="width:100%" bgcolor="green">
        <tr>
            <td align="right">
                <s:button name=" " value="Isi Semula" class="btn" onclick="clearText('hasil');" id="clear"/>&nbsp;
                <s:submit name="Step2" value="Seterusnya" class="btn" id="nextBtn" disabled="true"/>&nbsp;
            </td>
        </tr>
    </table></div>
</s:form>
<script language="javascript" >
    $(document).ready(function() {
        var type = ${actionBean.typeOfPayment};
        if(type=='0'){
            $('#noAkaunSingle').show();
            $('#pukal').hide();
        }
        if(type=='1'){
            $('#noAkaunSingle').hide();
            $('#pukal').show();
        }
        
        <c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >
            $("#akaunList${i - 1}").blur(function(){
                validateAkaun(${i - 1});
                
            });
        </c:forEach>
        $('#tot').val('0.00');
    });
</script>

<script language="javascript" >
    function viewField(f){
        var b = ${actionBean.bilHakmilik};
        if(f==0){
            for(var m=0; m<b;m++){
                $('#akaunList'+m).val('');
                $('#baki'+m).val('');}
            $('#biHakmilik').val(b);
            $('#noAkaunSingle').show();
            $('#pukal').hide();
            $('#nextBtn').attr('disabled', true);
        }
        if(f==1){
            $('#tot').val('0.00');
            $('#noAkaun').val('');
            $('#biHakmilik').val(b);
            $('#noAkaunSingle').hide();
            $('#pukal').show();
        }
    }
    
    function checkBilAkaun(inputTxt){
        var v = RemoveNonNumeric1(inputTxt.value);
        $('#bilHakmilik').val(parseFloat(v));
    }
    
    function RemoveNonNumeric1( strString ){
        var strValidCharacters = "1234567890.";
        var strReturn = "0";
        var strBuffer = "0";
        var intIndex = 0.00;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ ){
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 ){
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }
    
    function checkingHakmilik(id, type){
        if(id != ""){
            $.get("${pageContext.request.contextPath}/hasil/check_idhakmilik_accountNo?doCheckAccount&idHakmilik=" + id+"&type="+type,
            function(data){
                if (data == '0'){
                    alert("Nombor Akaun yang dimasukkan tidak sah. Sila masukkan Nombor Akaun semula.");
                    $('#noAkaun').val('');
                }
                else if(data == '2'){
                    alert("Nombor Akaun yang dimasukkan telah dibatalkan.");
                    $('#noAkaun').val('');                    
                }else{$('#nextBtn').removeAttr('disabled');}
            });
        }else{$('#nextBtn').attr('disabled', true);}
    }
    
    function validateAkaun(account){
        var val = $("#akaunList" + account).val();
        var type = "akaun";
        if(val != ""){
            $.get("${pageContext.request.contextPath}/hasil/check_idhakmilik_accountNo?doCheckAccount&idHakmilik=" + val+"&type="+type,
            function(data){
                if (data == '0'){
                    alert("Nombor Akaun yang dimasukkan tidak sah. Sila masukkan Nombor Akaun semula.");
                    $("#akaunList" + account).val('');
                }else if(data == '2'){
                    alert("Nombor Akaun yang dimasukkan telah dibatalkan.");
                    $("#akaunList" + account).val('');
                }else{
                    $.get("${pageContext.request.contextPath}/hasil/check_idhakmilik_accountNo?getBaki&account=" + val,
                    function(bal){
                        var m = parseFloat(bal);
                        $("#baki" + account).val(m.toFixed(2));
                        if(checkingId(account)){
                            checkingDuplicate(val, account);
                        }
                        totalAmaun();
                    });
                    $('#nextBtn').removeAttr('disabled');
                }
            });
        }
    }
    
    function totalAmaun(){
        var t = 0;
        for(var x=0;x<${actionBean.bilHakmilik};x++){
            var m = document.getElementById('baki'+x);
            if(parseFloat(m.value) > 0){
                t += parseFloat(m.value);
            }
        }
        $('#tot').val(t.toFixed(2));
    }
    
    function checkingDuplicate(val, row){
        var bil = ${actionBean.bilHakmilik};
        for(var i=row; i >0;i--){
            var val1 = $("#akaunList" + (i-1)).val();
            if(val == val1){
                alert("ID Hakmilik "+val+" telah dimasukkan.");
                $("#akaunList" + row).val("");
                $("#baki" + row).val("");
                break;
            }
        }
    }

    function checkingId(row){
        for(var x=0;x<${actionBean.bilHakmilik};x++){
            var val = $("#akaunList" + x).val();
            var val1 = "";
            if((x+1)<${actionBean.bilHakmilik}){
                val1 = $("#akaunList" + (x+1)).val();
            }
            if((val == "")&&(val1!="")){
                alert("Sila Masukkan ID Hakmilik mengikut turutan yang betul.");
                $("#akaunList" + row).val("");
                $("#baki" + row).val("");
                return false;
            }
        }
        return true;
    }
</script>