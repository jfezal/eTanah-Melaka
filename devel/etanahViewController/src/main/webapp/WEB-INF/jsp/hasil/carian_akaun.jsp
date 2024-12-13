<%-- 
    Document   : status_permohonan
    Created on : Jul 15, 2010, 9:10:59 PM
    Author     : muhammad.amin
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    $(document).ready(function () {
        $('form').submit(function () {
            doBlockUI();
        });
    });

    function validateNumber(elmnt, content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric(strString)
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for (intIndex = 0; intIndex < strString.length; intIndex++)
        {
            strBuffer = strString.substr(intIndex, 1);
            // Is this a number
            if (strValidCharacters.indexOf(strBuffer) > -1)
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

    function dodacheck(val) {
        //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;
        var v = $('#jenisKp').val();

        if (v == 'B') {
            var strPass = val;
            var strLength = strPass.length;
            //$('#kp').attr("maxlength","12");
            if (strLength > 12) {
                var tst = val.substring(0, (strLength) - 1);
                $('#noKp').val(tst);
            }
            var lchar = val.charAt((strLength) - 1);
            if (isNaN(lchar)) {
                //return false;
                var tst = val.substring(0, (strLength) - 1);
                $('#noKp').val(tst);
            }
        }
    }

    function dodacheck2(val) {
        //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;
        var v = $('#jenisKp2').val();

        if (v == 'B') {
            var strPass = val;
            var strLength = strPass.length;
            //$('#kp').attr("maxlength","12");
            if (strLength > 12) {
                var tst = val.substring(0, (strLength) - 1);
                $('#noKp2').val(tst);
            }
            var lchar = val.charAt((strLength) - 1);
            if (isNaN(lchar)) {
                //return false;
                var tst = val.substring(0, (strLength) - 1);
                $('#noKp2').val(tst);
            }
        }
    }

    function dateValidation(id, value) {
        var vsplit = value.split('/');
        var fulldate = vsplit[1] + '/' + vsplit[0] + '/' + vsplit[2];
        var today = new Date();
        var sdate = new Date(fulldate);
        if (sdate > today) {
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#' + id).val("");
        }
    }

    function viewStatus(id) {
        window.open("${pageContext.request.contextPath}/hasil/carianAkaun?search&idHakmilik=" + id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=" + screen.width + ",height=" + screen.height + ",scrollbars=yes,left=0,top=0");
    }

    function doBlockUI() {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });
    }


</script>

<img alt="" id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.hasil.CarianAkaunActionBean" id="cetak_semula">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">

        <fieldset class="aras1">
            <legend>Semakan Status Akaun</legend>




            <p>
                <label><em>*</em>ID Hakmilik :</label>
                <s:text name="idHakmilik" id="idHakmilik" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
           
            <p align="center">
                <s:submit name="search" value="Cari" class="btn" />
                <s:submit name="reset" value="Carian Semula" class="btn" onclick=""/>
            </p>


        </fieldset>
    </div>
</s:form>