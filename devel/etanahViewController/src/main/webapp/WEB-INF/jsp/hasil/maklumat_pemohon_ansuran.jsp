<%--
    Document   : maklumat_pemohon_ansuran
    Created on : Feb 12, 2010, 11:12:26 AM
    Author     : abdul.hakim
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN" http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script  language="javascript" >

function updateTotal(elmnt,inputTxt){
    var total = 0;
    	var a = document.getElementById('amaun')
    	var b = document.getElementById('amaun1')
    	if ((isNaN(a.value))||(isNaN(b.value))){
    	    <%--alert("Nombor "+a.value+" tidak sah");--%>
            elmnt.value = RemoveNonNumeric(b);
            elmnt.value = RemoveNonNumeric(a);
    	    return;
        }
        if((a.value)==""){
            alert("Sila masukkan pendapatan bulanan");
            elmnt.value = RemoveNonNumeric(a);
            $('#amaun').focus();
            return;
        }
        if((b.value)==""){
            alert("Sila masukkan pendapatan bulanan suami/ isteri");
            elmnt.value = RemoveNonNumeric(b);
            $('#amaun1').focus();
            return;
        }
        total = parseFloat(a.value)+parseFloat(b.value);
    var t = document.getElementById('jumCaraBayar');
    t.value = total.toFixed(2);
    inputTxt.value = parseFloat(inputTxt.value).toFixed(2);
    $('#amaun').val(parseFloat(a.value).toFixed(2));
    $('#amaun1').val(parseFloat(b.value).toFixed(2));
}

function RemoveNonNumeric( strString )
{
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

function validate(x,y,z){
    var kerja = document.getElementById('kerja');
    var amt = document.getElementById('amaun');
    if(kerja.value == ""){
        alert("Sila masukkan pekerjaan pemohon.");
        $('#kerja').focus();
        return false;
    }else if(amt.value == "0.00"){
        alert("Sila masukkan pendapatan bulanan pemohon.");
        $('#amaun').focus();
        return false;
    }else{
        doSubmit(x,y,z);
        return true;
    }
}
</script>
<s:form beanclass="etanah.view.stripes.hasil.MaklumatPemohonAnsuranActionBean">
<s:messages/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pemohon</legend>
            <p>
                <label>Pekerjaan :</label>
                <s:text name="permohonanPihak.pekerjaan" size="35" id="kerja"/>
            </p>
            <p>
                <label>Pendapatan Sebulan (RM) :</label>
                <s:text name="permohonanPihak.pendapatan" onblur="javascript:updateTotal(this, this.value);" id="amaun" style="text-align:right"/>
            </p>
            <p>
                <label>Pendapatan Suami/Isteri</label>
                &nbsp;<br>
                <label>Sebulan (RM) :</label>
                <s:text name="permohonanPihak.pendapatanLain" onblur="javascript:updateTotal(this, this.value);" id="amaun1" style="text-align:right"/>
            </p>
            <p>
                <label>Jumlah (RM) :</label>
                <s:text name="total" id="jumCaraBayar" readonly="true" style="text-align:right"/>
            </p>
            <p align="right">
                <s:button class="btn" onclick="return validate(this.form, this.name, 'page_div');" disabled="${actionBean.simpanBtn}" name="save" value="Simpan"/>
            </p>
        </fieldset>
    </div>
</s:form>
<script language="javascript" >
	$(document).ready(function() {
	    // focus on the first payment
            var a = document.getElementById('amaun');
            var b = document.getElementById('amaun1');

            var total = parseFloat(a.value)+parseFloat(b.value);

            $('#amaun').val((parseFloat(a.value)).toFixed(2));
            $('#amaun1').val((parseFloat(b.value)).toFixed(2));
            $('#jumCaraBayar').val(total.toFixed(2));
            function stopRKey(evt) {
               var evt = (evt) ? evt : ((event) ? event : null);
               var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
               if ((evt.keyCode == 13) && (node.type=="text")) {return false;}
            }

            document.onkeypress = stopRKey;
    });
</script>

