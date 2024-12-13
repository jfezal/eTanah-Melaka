<%-- 
    Document   : bayaran_balik_1
    Created on : Feb 2, 2011, 10:54:12 AM
    Author     : abdul.hakim
--%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    function decimalFormat(elmnt,inputTxt){
        var total = 0;
        var a = document.getElementById('amaun')
        if (isNaN(a.value)){
            elmnt.value = RemoveNonNumeric(a);
            return;
        }
        if((a.value)==""){
            alert("Sila masukkan jumlah bayaran balik");
            elmnt.value = RemoveNonNumeric(a);
            $('#amaun').focus();
            return;
        }
        inputTxt.value = parseFloat(inputTxt.value).toFixed(2);
        $('#amaun').val(parseFloat(a.value).toFixed(2));
    }

    function RemoveNonNumeric( strString ){
        var strValidCharacters = "1234567890.";
        var strReturn = "0";
        var strBuffer = "0";
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

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Bayaran Balik (Deposit/Amanah)</font>
                </div>
            </td>
        </tr>
    </table></div>
    <stripes:errors />
    <stripes:form beanclass="etanah.view.stripes.hasil.BayaranBalikDepositActionBean" id="bayar_balik">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Deposit</legend>

            <p>
                <label>Jenis Akaun :</label>
                ${actionBean.akaun.kodAkaun.nama}&nbsp;
            </p>

            <p>
                <label>Nombor Akaun :</label>
                ${actionBean.akaun.noAkaun}&nbsp;
            </p>

            <p>
                <label>Nama Pendeposit :</label>
                ${actionBean.akaun.pemegang.nama}&nbsp;
            </p>

            <p>
                <label>Batch No. :</label>
                <c:if test="${actionBean.akaun.kumpulan.idKumpulan eq null}">-</c:if>
                <c:if test="${actionBean.akaun.kumpulan.idKumpulan ne null}">${actionBean.akaun.kumpulan.idKumpulan}</c:if>&nbsp;
            </p>
            
        </fieldset>
    </div>

    <p></p>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>..:: Jurnal ::..</legend>

            <p>
                <label>Jumlah bayaran balik (RM) : </label>
                <stripes:text name="amaun"  onblur="javascript:decimalFormat(this, this.value);" id="amaun" style="text-align:right"/>
            </p>

            <p>
                <label>Nombor Rujukan :</label>
                <stripes:text name="noRujukan" id="noRuj"  onkeyup="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label>Tutup Akaun : </label>
                <stripes:radio name="active" value="Ya"/>Ya     &nbsp;&nbsp;&nbsp;&nbsp;
                <stripes:radio name="active" value="Tidak"/>Tidak
            </p>
        </fieldset>
    </div>

    <div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="right">
                    <stripes:submit name="Step5" value="Simpan" class="btn"/>
                    <stripes:submit name="Step4" value="Kembali" class="btn"/>
                </div>
            </td>
        </tr>
    </table></div>
</stripes:form>