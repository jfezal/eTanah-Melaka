<%-- 
    Document   : papar_dokumen_ttgn
    Created on : Jun 24, 2011, 12:02:43 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>


<script type="text/javascript">
    function signFile(parameterToSign, trhT, ic) {
        alert("hi");
        alert(parameterToSign);
        alert("hi2");
        var signer = new ActiveXObject("SAPDFSigner.Form1");
        alert("hi3");
        //signer.SigningPDFFile(fileNme,fldrNme,txtJawatan,template,signature);
        signer.SigningPDFFile(parameterToSign, trhT, '');
        alert("hi4");
        if (signer.getValue() != "") {
            alert("hi5");
            document.eload.message.value = signer.getValue();
        }
    }

    function doSignFile(dateSvr) {
        {
            //alert("selectCheckBox1");
            var e = $('#sizeDokumen').val();
            var cnt = 0;
            var size = 1;
            var data = new Array();
            var borang = '';
            var DELIM = "|";
            var parameterToSign = '';
            var tempParameter = '';
            var date = new Date();
            var month = date.getMonth() + 1;
            var fullDate = date.getDate() + "/" + month + "/" + date.getFullYear();
            for (cnt = 0; cnt < e; cnt++)
            {
                if (e == '1') {
                    if (document.eload.namaFizikal.checked) {

                        data[cnt] = document.eload.namaFizikal.value;
                        tempParameter = document.eload.namaFizikal.value;
                        var a = tempParameter;
                        var arr = new Array();
                        var size = a.split('-');
                        //                    var last = size.length - 1 ;
                        for (var i = 0; i < size.length; i++) {
                            var test = a.split('-');
                            arr[i] = test[i];
                        }
                        //                    alert(borang);
                        var firstFile = arr[arr.length - 2];
                        //                    alert("Path bese:" + firstFile);
                        borang = arr[arr.length - 1]
                        if (borang == 'PRU') {
                            borang = "4D"
                        }
                        //                    alert("Borang :" + borang);
                        var fileTemp = "#" + arr[0];
                        for (var i = 1; i < size.length - 2; i++) {
                            fileTemp = fileTemp + "/" + arr[i]
                        }

                        parameterToSign = firstFile + fileTemp + "#" + borang + "#Y";

                    }

                }
                else {
                    if (document.eload.namaFizikal[cnt].checked) {

                        data[cnt] = document.eload.namaFizikal[cnt].value;
                        tempParameter = document.eload.namaFizikal[cnt].value;
                        //                    alert(tempParameter);
                        var a = tempParameter;
                        var arr = new Array();
                        var size = a.split('-');
                        //                    var last = size.length - 1 ;
                        for (var i = 0; i < size.length; i++) {
                            var test = a.split('-');
                            arr[i] = test[i];
                        }
                        //                    alert(borang);
                        var firstFile = arr[arr.length - 2];
                        //                    alert("Path bese:" + firstFile);
                        borang = arr[arr.length - 1]
                        if (borang == 'PRU') {
                            borang = "4D"
                        }
                        //                    alert("Borang :" + borang);
                        var fileTemp = "#" + arr[0];
                        for (var i = 1; i < size.length - 2; i++) {
                            fileTemp = fileTemp + "/" + arr[i]
                        }
                        if (parameterToSign != '') {
                            parameterToSign = parameterToSign + DELIM + firstFile + fileTemp + "#" + borang + "#Y";
                        } else {
                            parameterToSign = firstFile + fileTemp + "#" + borang + "#Y";
                        }
                    }
                }
            }
//            alert("parameterToSign" + parameterToSign);
//            alert("fullDate" + fullDate);
//            alert("borang" + borang);
//            alert("fileTemp" + fileTemp);
//            alert("tempParameter" + tempParameter);
//
            //alert("Signing Test1");
            //alert("parameterToSign" + parameterToSign);
            if (parameterToSign != '') {
                //alert("Signing Test2");
//                signer = "SAPDFSignerL1.Form1" ;
                //alert("ActiveXObject " + ActiveXObject);
                alert('xxx');
                var signer = new ActiveXObject("SAPDFSigner.Form1");
                alert("Signing Test3");
                signer.SigningPDFFile(parameterToSign, dateSvr);
                alert("Signing Test4");
                if (signer.getValue() != "") {
                    alert("Signing Test5");
                    document.eload.message.value = signer.getValue();
                }
            } else {
                alert('Sila Pilih Dokumen untuk ditandatangan.');
            }
        }
    }

    function makeFile(value) {
        alert(value);
        var a = value;
        var arr = new Array();
        var size = a.split('\\');
        //        alert(size.length);
        for (var i = 0; i < size.length; i++) {
            var test = a.split('\\');
            arr[i] = test[i];
        }



        //        alert("array  : " + arr);
        //        alert(a.split('\\').join('/'));
        //        alert("Last:" + arr[arr.length-1]);
        var firstFile = arr[arr.length - 1];
        var fileTemp = "#" + arr[0];
        for (var i = 1; i < size.length - 1; i++) {
            fileTemp = fileTemp + "/" + arr[i]
        }
        //        alert(fileTemp);
        //       var b = a ;
        var kodUrusan = document.getElementById('kodUrusan').value
        var borang = "";
        //        alert(kodUrusan)
        if (kodUrusan == 'PPBB' || kodUrusan == 'PBPTD' || kodUrusan == 'PBPTG') {
            borang = "4Ce";
        }
        else if (kodUrusan == 'PPRUS' || kodUrusan == 'PPRU') {
            borang = "4De";
        }
        else if (kodUrusan == 'PLPS') {
            borang = "4Ae";
        }
        else if (kodUrusan == 'LPSP') {
            borang = "4Be";
        }
        else if (kodUrusan == 'PPTR') {
            borang = "4E";
        }
        else if (kodUrusan == 'PTPBP') {
            borang = "PRMPH";
        }
        var date = new Date();
        var month = date.getMonth() + 1;
        //        var fullMonth = "" ;
        ////        alert("Length : " + month.length)
        //        if(month.length == 1){
        //            fullMonth = "0" + month ;
        //        }
        var fullDate = date.getDate() + "/" + month + "/" + date.getFullYear();

        document.getElementById('test1').value = firstFile + fileTemp + "#" + borang + "#Y";
        document.getElementById('tarikh1').value = fullDate;
        //        $('#test').val() = value;
    }


    function selectCheckBox()
    {
        alert("selectCheckBox");
        var e = $('#sizeDokumen').val();
        var cnt = 0;
        var size = 1;
        var data = new Array();
        var borang = '';
        var DELIM = "|";
        var parameterToSign = '';
        var tempParameter = '';
        var date = new Date();
        var month = date.getMonth() + 1;
        var fullDate = date.getDate() + "/" + month + "/" + date.getFullYear();
        for (cnt = 0; cnt < e; cnt++)
        {
            if (e == '1') {
                if (document.eload.namaFizikal.checked) {

                    data[cnt] = document.eload.namaFizikal.value;
                    tempParameter = document.eload.namaFizikal.value;
                    var a = tempParameter;
                    var arr = new Array();
                    var size = a.split('-');
                    //                    var last = size.length - 1 ;
                    for (var i = 0; i < size.length; i++) {
                        var test = a.split('-');
                        arr[i] = test[i];
                    }
                    //                    alert(borang);
                    var firstFile = arr[arr.length - 2];
                    //                    alert("Path bese:" + firstFile);
                    borang = arr[arr.length - 1]
                    if (borang == 'PRU') {
                        borang = "4D"
                    }
                    //                    alert("Borang :" + borang);
                    var fileTemp = "#" + arr[0];
                    for (var i = 1; i < size.length - 2; i++) {
                        fileTemp = fileTemp + "/" + arr[i]
                    }

                    parameterToSign = firstFile + fileTemp + "#" + borang + "#Y";

                }

            }
            else {
                if (document.eload.namaFizikal[cnt].checked) {

                    data[cnt] = document.eload.namaFizikal[cnt].value;
                    tempParameter = document.eload.namaFizikal[cnt].value;
                    //                    alert(tempParameter);
                    var a = tempParameter;
                    var arr = new Array();
                    var size = a.split('-');
                    //                    var last = size.length - 1 ;
                    for (var i = 0; i < size.length; i++) {
                        var test = a.split('-');
                        arr[i] = test[i];
                    }
                    //                    alert(borang);
                    var firstFile = arr[arr.length - 2];
                    //                    alert("Path bese:" + firstFile);
                    borang = arr[arr.length - 1]
                    if (borang == 'PRU') {
                        borang = "4D"
                    }
                    //                    alert("Borang :" + borang);
                    var fileTemp = "#" + arr[0];
                    for (var i = 1; i < size.length - 2; i++) {
                        fileTemp = fileTemp + "/" + arr[i]
                    }
                    if (parameterToSign != '') {
                        parameterToSign = parameterToSign + DELIM + firstFile + fileTemp + "#" + borang + "#Y";
                    } else {
                        parameterToSign = firstFile + fileTemp + "#" + borang + "#Y";
                    }
                }
            }
        }
        //        alert(parameterToSign);
        //        alert(fullDate);
        if (parameterToSign != '') {
            alert("hi");
            var signer = new ActiveXObject("SAPDFSignerL2.Form1");
            alert("hi");
            signer.SigningPDFFile(parameterToSign, fullDate, '');
            alert("hi");
            if (signer.getValue() != "") {
                alert("hi");
                document.eload.message.value = signer.getValue();
            }
        } else {
            alert('Sila Pilih Dokumen untuk ditandatangan.');
        }
    }
    //Cater for strata
    function selectCheckBoxKhas()
    {
        var e = $('#sizeDokumen').val();
        var cnt = 0;
        var size = 1;
        var data = new Array();
        var borang = '';
        var DELIM = "|";
        var parameterToSign = '';
        var tempParameter = '';
        var date = new Date();
        var month = date.getMonth() + 1;
        var fullDate = date.getDate() + "/" + month + "/" + date.getFullYear();
        for (cnt = 0; cnt < e; cnt++)
        {
            if (e == '1') {
                if (document.eload.namaFizikal.checked) {

                    data[cnt] = document.eload.namaFizikal.value;
                    tempParameter = document.eload.namaFizikal.value;
                    var a = tempParameter;
                    var arr = new Array();
                    var size = a.split('-');
                    //                    var last = size.length - 1 ;
                    for (var i = 0; i < size.length; i++) {
                        var test = a.split('-');
                        arr[i] = test[i];
                    }
                    //                    alert(borang);
                    var firstFile = arr[arr.length - 1];
                    //                    alert("Path bese:" + firstFile);
                    borang = arr[arr.length - 2]
                    if (borang == 'PRU') {
                        borang = "4D"
                    }
                    //                    alert("Borang :" + borang);
                    var fileTemp = "#" + $('#permit_path' + e).val() + "/"; //Temporary coding - Tgh pikir how to remake it
                    parameterToSign = firstFile + fileTemp + "#" + borang + "#Y";

                }

            }
            else {
                if (document.eload.namaFizikal[cnt].checked) {

                    data[cnt] = document.eload.namaFizikal[cnt].value;
                    tempParameter = document.eload.namaFizikal[cnt].value;
                    //                    alert(tempParameter);
                    var a = tempParameter;
                    var arr = new Array();
                    var size = a.split('-');
                    //                    var last = size.length - 1 ;
                    for (var i = 0; i < size.length; i++) {
                        var test = a.split('-');
                        arr[i] = test[i];
                    }
                    //                    alert(borang);
                    var firstFile = arr[arr.length - 1];
                    //                    alert("Path bese:" + firstFile);
                    borang = arr[arr.length - 2]
                    if (borang == 'PRU') {
                        borang = "4D"
                    }
                    //                    alert("Borang :" + borang);
                    var fileTemp = "#" + $('#permit_path' + e).val() + "/"; //Temporary coding - Tgh pikir how to remake it
                    if (parameterToSign != '') {
                        parameterToSign = parameterToSign + DELIM + firstFile + fileTemp + "#" + borang + "#Y";
                    } else {
                        parameterToSign = firstFile + fileTemp + "#" + borang + "#Y";
                    }
                }
            }
        }
//        alert(parameterToSign);
        //        alert(fullDate);
        if (parameterToSign != '') {

            var signer = new ActiveXObject("SAPDFSignerL2.Form1");

            signer.SigningPDFFile(parameterToSign, fullDate, '');

            if (signer.getValue() != "") {

                document.eload.message.value = signer.getValue();
            }
        } else {
            alert('Sila Pilih Dokumen untuk ditandatangan.');
        }
    }
</script>
<s:form beanclass="etanah.view.stripes.pelupusan.TandatanganDokumenActionBean" name="eload">
    <div class="content" align="center">
        <fieldset class="aras1">
            <legend>Tandatangan Borang</legend>
            <s:hidden name="${actionBean.permohonan.kodUrusan.kod}" value="${actionBean.permohonan.kodUrusan.kod}" id="kodUrusan"/>
            <s:hidden name="test" id="test1"/>
            <s:hidden name="test2" id="test2"/>
            <s:hidden name="tarikh" id="tarikh1"/>
            <s:hidden name="sizeDokumen" id="sizeDokumen"/>
            <display:table class="tablecloth" name="${actionBean.kandunganPermit}" cellpadding="0" cellspacing="0"
                           requestURI="${pageContext.request.contextPath}/pelupusan/dokumen_tandatangan" id="line">
                <display:column>
                    <c:choose>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPRUS'}">
                            <s:checkbox name="namaFizikal" id="namaFizikal" value="${line.dokumen.namaFizikal}-${line.dokumen.kodDokumen.kod}-${line.dokumen.idDokumen}"/>
                            <c:set var="path"/>
                            <c:forTokens delims="/" items="${line.dokumen.namaFizikal}" var="items" begin="0" end="3">
                                <c:set var="path" value="${path}/${items}"/>
                            </c:forTokens>
                            <input type="hidden" id="permit_path${line_rowNum}" value="${path}"/>
                        </c:when>
                        <c:otherwise>
                            <s:checkbox name="namaFizikal" id="namaFizikal" value="${line.dokumen.namaFizikal}-${line.dokumen.kodDokumen.kod}"></s:checkbox>
                        </c:otherwise>
                    </c:choose>
                </display:column>
                <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                <%--<display:column property="dokumen.idDokumen" title="Kod Dokumen"/> --%>
                <display:column property="dokumen.kodDokumen.kod" title="Kod Dokumen"/>
                <display:column property="dokumen.kodDokumen.nama" title="Nama Dokumen"/>
                <%--<display:column property="dokumen.namaFizikal" title="Nama Fizikal"/> --%>

            </display:table>
            <c:choose>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPRUS'}">
                    <s:button name="tandantangan" value="Tandatangan" class="longbtn" onclick="javascript:selectCheckBoxKhas();"/>
                </c:when>
                <c:otherwise>
                    <s:button name="tandantangan" value="Tandatangan" class="longbtn" onclick="doSignFile('${today}')"/>
                </c:otherwise>
            </c:choose>
            <%--<s:button name="" id="" value="T/tangan" class="btn" onclick="doSignFile('${today}', '${prm}');"/>--%>
            <%--<s:button name="tandantangan" value="Tandatangan3" class="longbtn" onclick="makeFile(document.getElementById('test1').value,document.getElementById('test2').value,document.getElementById('tarikh1').value,'');"/>--%>
        </fieldset>
    </div>


</s:form>