<%-- 
    Document   : notis_7G_NS
    Created on : Apr 6, 2011, 1:47:53 PM
    Author     : GATES
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>
<script type="text/javascript">
      function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
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

    function removeCommas(val){
        return val.replace(/\,/g,'');
    }


    function calc(elmnt,content){
        // validations
        validateNumber(elmnt,content);

        var premium = $("#premium").val();
        var sumbangan = $("#sumbangan").val();
        var penulisan = $("#penulisan").val();
        var sewaBaru = $("#sewaBaru").val();
        var bayaranUpahUkur = $("#bayaranUpahUkur").val();
        var tandaSempadan = $("#tandaSempadan").val();
        var penyediaan = $("#penyediaan").val();
        var pendaftaran = $("#pendaftaran").val();
        var semantara = $("#semantara").val();
        var notis = $("#notis").val();
        var borang7c = $("#borang7c").val();

        var jumlah =  parseFloat(Number(removeCommas(premium))) + parseFloat(Number(removeCommas(sumbangan)))  + parseFloat(Number(removeCommas(penulisan)))
            + parseFloat(Number(removeCommas(sewaBaru))) + parseFloat(Number(removeCommas(bayaranUpahUkur))) + parseFloat(Number(removeCommas(tandaSempadan)))
            + parseFloat(Number(removeCommas(penyediaan))) + parseFloat(Number(removeCommas(pendaftaran))) + parseFloat(Number(removeCommas(semantara)))
            + parseFloat(Number(removeCommas(notis))) + parseFloat(Number(removeCommas(borang7c))) + parseFloat(Number(removeCommas(dendap)));
        <%--alert("jumlah:"+jumlah);--%>
        $("#jumlah").val(jumlah);
    }

</script>
        <script type="text/javascript">
            
            function convert(val, id){
            var amaun = CurrencyFormatted(val);
            amaun = CommaFormatted(amaun);
            $('#'+id).val(amaun);
        }

        function CurrencyFormatted(amount){
            var q = amount.indexOf(",");

            if(q > 0){
                amount = amount.replace (/,/g, "");
            }

            var i = parseFloat(amount);

            if(isNaN(i)) { i = 0.00; }
            var minus = '';
            if(i < 0) { minus = '-'; }
            i = Math.abs(i);
            i = parseInt((i + .005) * 100);
            i = i / 100;
            s = new String(i);
            if(s.indexOf('.') < 0) { s += '.00'; }
            if(s.indexOf('.') == (s.length - 2)) { s += '0'; }
            s = minus + s;
            return s;
        }

        function CommaFormatted(amount){
            var delimiter = ","; // replace comma if desired
            var a = amount.split('.',2)
            var d = a[1];
            var i = parseInt(a[0]);
            if(isNaN(i)) { return ''; }
            var minus = '';
            if(i < 0) { minus = '-'; }
            i = Math.abs(i);
            var n = new String(i);
            var a = [];
            while(n.length > 3)
            {
                var nn = n.substr(n.length-3);
                a.unshift(nn);
                n = n.substr(0,n.length-3);
            }
            if(n.length > 0) { a.unshift(n); }
            n = a.join(delimiter);
            if(d.length < 1) { amount = n; }
            else { amount = n + '.' + d; }
            amount = minus + amount;
            return amount;
        }
      function kiraCukaiKelompok() {
      $("#kira").show();
      $("#cals").hide();
      var kodUOM = $("#kodluas").val();
      var luas = $("#luas").val();
      var idH = $("#idHakmilik").val();
      
      $.post('${pageContext.request.contextPath}/daftar/nota/nota_daftar?kiraCukaiKelompok&idHakmilik=' + idH + '&luas=' + luas + '&kodUOM=' + kodUOM,
              function(data) {
                $('#sewaBaru').val(convert(data,'sewaBaru'));
                 $("#kira").hide();
                  $("#cals").show();
              }, 'html');
    }
        </script>

<stripes:form beanclass="etanah.view.stripes.pembangunan.Notis7GNSActionBean">
<s:messages/>
<s:hidden name="cukai" value="1"></s:hidden>
<s:hidden name="idHakmilik" id="idHakmilik"/>
<s:hidden name="luas" id="luas"/>
<s:hidden name="kodluas" id="kodluas"/>

<div class="subtitle">
    <fieldset class="aras1">
        <legend>
           Sewa Tahunan Baru
        </legend>
        <div class="content" align="center">
            <table border="0" width="35%">                   
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Sewa Tahunan/Cukai Baru</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="sewaBaru" id="sewaBaru" onkeyup="calc(this,this.value);" value="0.00"  formatPattern="#,##0.00" style="width:130px;"/></td>
                        <td>        <span id="kira" style="display:none">Jana Cukai...</span>
                                    <img alt="Mesin Kira" onmouseover="this.style.cursor = 'pointer';"  src='${pageContext.request.contextPath}/pub/images/icons/calc.png'  onclick="kiraCukaiKelompok();" id="cals"/>
                        </td>
                    </tr> 
                </table>                    
                <p>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
                </p>
            </div>
        </fieldset>
    </div>

</stripes:form>




