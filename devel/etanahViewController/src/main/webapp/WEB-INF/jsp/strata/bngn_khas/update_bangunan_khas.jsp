<%--
    Document   : update_bangunan_khas
    Created on : Dec 22, 2010, 09:35:51 AM
    Author     : zadhirul.farihim
    Edited by : nns Oct 05, 2021
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
        document.form1.bilPetak.value="";
        document.form1.bilTgkt.value="";
        document.form1.bilBilik.value="";
    
    });

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

    function isInteger(s){
        var i;
        for (i = 0; i < s.length; i++){
            // Check that current character is number.
            var c = s.charAt(i);
            if (((c < "0") || (c > "9"))) return false;
        }
        // All characters are numbers.
        return true;
    }

    function stripCharsInBag(s, bag){
        var i;
        var returnString = "";
        // Search through string's characters one by one.
        // If character is not in bag, append to returnString.
        for (i = 0; i < s.length; i++){
            var c = s.charAt(i);
            if (bag.indexOf(c) == -1) returnString += c;
        }
        return returnString;
    }

    function kemaskini(event, f){
        var blok = document.getElementById("updatenamaLain").value;
        var bil_petak = document.getElementById("updatebilPetak").value;
        var bil_tingkat = document.getElementById("updatebilTgkt").value;
        
        if ((blok == ""))
        {
            alert('Sila masukkan blok');
            document.form1.updatenamaLain.focus();
            return false;
        }
        if ((bil_tingkat == ""))
        {
            alert('Sila masukkan bilangan tingkat ');
            document.form1.updatebilTgkt.focus();
            return false;
        }
        if ((bil_petak == ""))
        {
            alert('Sila masukkan bilangan petak ');
            document.form1.updatebilPetak.focus();
            return false;
        }else{
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
    <%--self.opener.refreshPageHakmilik();--%>
                    self.close();
                },'html');
                return true;
            }
        }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div class="subtitle" >
    <s:form  name="form1" beanclass="etanah.view.strata.BangunanKhasActionBean">
        <s:messages/>
        <s:errors/>
        <s:hidden name="idBangunan"/>

        <fieldset class="aras1">
            <legend>Kemaskini Bangunan</legend>
            <p>
                Yang bertanda(<em>*</em>) adalah wajib diisi.
            </p>

            <p>
                <label><em>*</em>Blok (M) :</label>
                <s:text readonly="true" name="updatenamaLain" size="30" id="updatenamaLain" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label><em>*</em>Bilangan Tingkat :</label>
                <s:text name="updatebilTgkt"  size="30" id="updatebilTgkt" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label><em>*</em>Bilangan Petak :</label>
                <s:text name="updatebilPetak" size="30" id="updatebilPetak" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label><em>*</em>Jenis Kengunaan Bangunan :</label> 
                <s:select name="gunaBngn" value="${actionBean.gunaBngn}">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodKegunaanBangunan}" label="nama" value="kod"/>
                </s:select>
            </p>


            <br>
            <label>&nbsp;</label>
            <s:button name="updateBngn" id="simpan" value="Kemaskini" class="btn" onclick="kemaskini(this.name,this.form)"/>
            <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
            <br>

            <br>
        </fieldset>


    </s:form>
</div>