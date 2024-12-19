<%-- 
    Document   : maklumat_Bangunan
    Created on : Sep 21, 2010, 11:57:33 AM
    Author     : User
--%>
<%@ page import="java.io.*,etanah.Configuration" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    function doSetDokumenUtara(v){
        var idDokumen = document.getElementById("fotoutara").options[document.getElementById("fotoutara").selectedIndex].value;

        document.getElementById("idDokumenU").value = idDokumen;
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function doSetDokumenTimur(v){
        var idDokumen = document.getElementById("fototimur").options[document.getElementById("fototimur").selectedIndex].value;

        document.getElementById("idDokumenT").value = idDokumen;
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function doSetDokumenBarat(v){
        var idDokumen = document.getElementById("fotobarat").options[document.getElementById("fotobarat").selectedIndex].value;

        document.getElementById("idDokumenB").value = idDokumen;
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function doSetDokumenSelatan(v){
        var idDokumen = document.getElementById("fotoselatan").options[document.getElementById("fotoselatan").selectedIndex].value;

        document.getElementById("idDokumenS").value = idDokumen;
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }


    function doViewReportUtara(v) {

        var idDokumen = document.getElementById("fotoutara").options[document.getElementById("fotoutara").selectedIndex].value;
        alert(idDokumen);
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function doViewReportTimur(v) {

        var idDokumen = document.getElementById("fototimur").options[document.getElementById("fototimur").selectedIndex].value;
        alert(idDokumen);
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function doViewReportSelatan(v) {

        var idDokumen = document.getElementById("fotoselatan").options[document.getElementById("fotoselatan").selectedIndex].value;
        alert(idDokumen);
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function doViewReportBarat(v) {

        var idDokumen = document.getElementById("fotobarat").options[document.getElementById("fotobarat").selectedIndex].value;
        alert(idDokumen);
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function validateForm()
    {
        var tarikhSiasatan =document.getElementById("tarikhSiasatan").value;
        var jenisBangunan =document.getElementById("jenisBangunan").value;
        var adaBangunan = document.getElementById("adaBangunan").value;
            
        if(tarikhSiasatan == ""){
            alert('Sila masukan " Tarikh Siasatan " terlebih dahulu.');
            $("#tarikhSiasatan").focus();
            return false;
        }
        else if(jenisBangunan == ""){
            alert('Sila masukan " Jenis Bangunan " terlebih dahulu.');
            $("#jenisBangunan").focus();
            return false;
        }else if(adaBangunan ==""){
            alert('Sila pilih " Bangunan " terlebih dahulu.');
            $("#adaBangunan").focus();
            return false;
        }else{
            return true;
        }
            
    }

    function refresh(){
        var url = '${pageContext.request.contextPath}/pembangunan/muat_naik_imej';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
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

</script>
<%--Added for image thumbNail--%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/images/up/plusimageviewer.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/images/up/plusimageviewer.js"></script>

<%--End--%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form name="myform" beanclass="etanah.view.stripes.pembangunan.MuatNaikImejActionBean">
    <s:hidden name="idPermohonan" id="idmohon"/>
    <s:hidden name="imageFolderPath" id="lokasi"/>
    <s:messages/>
    <s:errors/>
    <div class="subtitle">      
        <fieldset class="aras1">
            <legend>Tarikh Siasatan</legend>
            <div class="content" align="center">
                <table border="0" width="80%">

                    <tr><td width="20%"><b>Tarikh Siasatan</b></td>
                        <td><b>:</b></td>
                        <td><s:text name="tarikhSiasatan" id="tarikhSiasatan" class="datepicker"/>
                        </td> 
                    </tr>
                </table>
            </div>
        </fieldset>

    </div>
    <div class="subtitle">      
        <fieldset class="aras1">
            <legend>Maklumat Sempadan</legend>

            <div class="content" align="left">
                <table class="tablecloth">
                    <tr>
                        <th>&nbsp;</th><th>Nombor Lot</th><th>Kegunaan</th>
                    </tr>
                    <tr>
                        <th>
                            Utara
                        </th>
                        <td>
                            <s:text name="sempadanUtaraNoLot"  id="sempadanUtaraNoLot" size="60" class="normal_text"/>
                        </td>
                        <td>
                            <s:text name="sempadanUtaraKegunaan" id="sempadanUtaraKegunaan" size="60" class="normal_text"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            Selatan
                        </th>
                        <td>
                            <s:text name="sempadanSelatanNoLot" id="sempadanSelatanNoLot" size="60" class="normal_text"/>
                        </td>
                        <td>
                            <s:text name="sempadanSelatanKegunaan" id="sempadanSelatanKegunaan" size="60" class="normal_text"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            Timur
                        </th>
                        <td>
                            <s:text name="sempadanTimurNoLot" id="sempadanTimurNoLot" size="60" class="normal_text"/>
                        </td>
                        <td>
                            <s:text name="sempadanTimurKegunaan" id="sempadanTimurKegunaan" size="60" class="normal_text"/>
                        </td>
                    </tr><tr>
                        <th>
                            Barat
                        </th>
                        <td>
                            <s:text name="sempadanBaratNoLot" id="sempadanBaratNoLot" size="60" class="normal_text"/>
                        </td>
                        <td>
                            <s:text name="sempadanBaratKegunaan" id="sempadanBaratKegunaan" size="60" class="normal_text"/>
                        </td>
                    </tr>
                </table>
            </div>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Keadaan Bangunan</legend><br>
            <div class="content" align="center">
                <table border="0" width="80%">

                    <tr><td width="20%"><b>Bangunan</b></td>
                        <td><b>:</b></td>
                        <td><s:select name="adaBangunan" id="adaBangunan" style="width:150px;">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="A"> Ada</s:option>
                                <s:option value="T"> Tiada </s:option>
                            </s:select>
                        </td> </tr>

                    <tr><td width="20%"><b>Jenis Bangunan</b></td>
                        <td><b>:</b></td>
                        <td>
                            <s:text name="jenisBangunan" value="" id="jenisBangunan"  size="30" class="normal_text"/> <font size="1.5" color="green"> (contoh: kedai,pejabat)</font>
                            </td> </tr>

                        <tr><td width="20%"><b>Keadaan Bangunan</b></td>
                            <td><b>:</b></td>
                            <td>
                            <s:text name="keadaanBangunan" readonly="readonly" value="Kekal" id="jbp" size="30" class="normal_text"></s:text>
                            </td> </tr>

                        <p></p>

                    </table></div>
            </fieldset>
        </div>

        <p align="center">
        <s:button name="simpanBangunan1" id="save" value="Simpan"  class="btn" onclick="if(validateForm()){doSubmit(this.form, this.name, 'page_div')}"/>
    </p>

</s:form>
