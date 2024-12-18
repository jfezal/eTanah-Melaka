<%--
    Document   : utiliti_ansuran_remedi
    Created on : Oct 22, 2012, 12:23:22 PM
    Author     : latifah.iskak
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>



<script language="javascript" type="text/javascript">
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


    function validateForm(){

        if(document.getElementById("idPermohonanCarian").value == ""){
            alert("Sila cari dahulu rekod dengan memasukkan Id Permohonan yang dikehendaki");
            $('#idMohon').focus();
            return false;
        }

        return true;
    }

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function resetForm() {
        if(document.getElementById("idMohon").value !=""){
            document.getElementById("idMohon").value ="";
        }

        var bil =  ${fn:length(actionBean.senaraiKompaun)};
        for (var i = 0; i < bil; i++){
            var amaunBaru = document.getElementById('amaunBaru'+i).value;
            var tempohHari = document.getElementById('tempohHari'+i).value;

            if(amaunBaru != ""){
                document.getElementById('amaunBaru'+i).value ="";
            }
            if(tempohHari != ""){
                document.getElementById('tempohHari'+i).value ="";
            }

        }

    }

    function muatNaikForm(folderId, idPermohonan, dokumenKod) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/upload_action?popupUpload&folderId=' + folderId+ '&idPermohonan=' + idPermohonan + '&dokumenKod=' + dokumenKod;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }

    function removeImej(idImej) {
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/utiliti_pengurangan_kompaun?deleteSelected&idImej='+idImej;
            $.get(url,
            function(data){
                $("#lampiranDiv").replaceWith($('#lampiranDiv', $(data)));
            },'html');
        }
    }

    function refreshPageKompaun1(idPermohonan){
        alert("yuhu");
        var url = '${pageContext.request.contextPath}/penguatkuasaan/utiliti_pengurangan_kompaun?reload&idPermohonan='+idPermohonan;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function refreshPageKompaun(){
        //alert("yuhu1");
        var url = '${pageContext.request.contextPath}/penguatkuasaan/utiliti_pengurangan_kompaun?refreshpage';
        $.get(url,
        function(data){
            $("#lampiranDiv").replaceWith($('#lampiranDiv', $(data)));
            //$('#page_div').html(data);
        },'html');
    }


 
</script>

<s:form beanclass="etanah.view.penguatkuasaan.UtilitiAnsuranRemediActionBean" name="form1">
    <s:messages/>
    <s:errors/>
    <s:hidden name="permohonan.idPermohonan" />

    <div class="subtitle">
        <fieldset class="aras1">

            <legend>Carian kompaun</legend>
            <br>
            <p><label for="idPermohonan">Id Permohonan/ID Perserahan :</label>
                <input type="text" name="idPermohonan" id="idMohon" onkeyup="this.value=this.value.toUpperCase();"/>
                <s:submit name="searchKompaun" value="Cari" class="btn"/>
            </p>
            <p>
                <label>Id Permohonan :</label>
                ${actionBean.idPermohonan} &nbsp;
                <s:hidden name="idPermohonan" id="idPermohonanCarian"/>
            </p>


            <div class="content" align="center">

                <display:table class="tablecloth" name="${actionBean.senaraiKompaun}" cellpadding="0" cellspacing="0" id="line" >
                    <display:column title="Bil" sortable="true">
                        ${line_rowNum}
                    </display:column>
                    <display:column title="No.Siri">
                        <a class="popup" onclick="viewSyorKompaun('${line.idKompaun}','${line.orangKenaSyak.idOrangKenaSyak}')">${line.noRujukan}</a>
                        <input name="idKompaun${line_rowNum-1}" id="idKompaun${line_rowNum-1}" value="${line.idKompaun}" type="hidden"/>
                    </display:column>
                    <display:column title="Diserahkan Kepada">${line.isuKepada}</display:column>
                    <display:column title="No.KP">
                        <c:if test="${line.noPengenalan ne null}">
                            ${line.noPengenalan}
                        </c:if>
                        <c:if test="${line.noPengenalan eq null}">
                            Tiada data
                        </c:if>
                    </display:column>
                    <display:column title="Amaun(RM)">
                        ${line.amaun}
                    </display:column>
                    <display:column title="Tempoh">
                        <c:if test="${actionBean.statusAnsuran eq true}">
                            6 bulan
                        </c:if>
                        <c:if test="${actionBean.statusAnsuran eq false}">
                            ${line.tempohHari} hari
                        </c:if>
                    </display:column>
                </display:table>

                <br/>
                <c:if test="${actionBean.statusAnsuran eq true}">
                    <table  width="50%" id="tbl" class="tablecloth" align="center">
                        <tr>
                            <th  width="1%" align="center"><b>Bil</b></th>
                            <th  width="5%" align="center"><b>Tarikh Bayaran</b></th>
                            <th  width="5%" align="center"><b>Amaun (RM)</b></th>
                        </tr>
                        <tr style="font-weight:bold">
                            <td>1.</td>
                            <td>${actionBean.tarikhAkhirBayaran1}</td>
                            <td>${actionBean.amaunBulan1}</td>
                        </tr>
                        <tr style="font-weight:bold">
                            <td>2.</td>
                            <td>${actionBean.tarikhAkhirBayaran2}</td>
                            <td>${actionBean.amaunBulan2}</td>
                        </tr>
                        <tr style="font-weight:bold">
                            <td>3.</td>
                            <td>${actionBean.tarikhAkhirBayaran3}</td>
                            <td>${actionBean.amaunBulan3}</td>
                        </tr>
                        <tr style="font-weight:bold">
                            <td>4.</td>
                            <td>${actionBean.tarikhAkhirBayaran4}</td>
                            <td>${actionBean.amaunBulan4}</td>
                        </tr>
                        <tr style="font-weight:bold">
                            <td>5.</td>
                            <td>${actionBean.tarikhAkhirBayaran5}</td>
                            <td>${actionBean.amaunBulan5}</td>
                        </tr>
                        <tr style="font-weight:bold">
                            <td>6.</td>
                            <td>${actionBean.tarikhAkhirBayaran6}</td>
                            <td>${actionBean.amaunBulan6}</td>
                        </tr>
                        <tr>
                            <td colspan="2" align="right">Jumlah Bayaran(RM):</td>
                            <td><input name="jumKompaun" value="${actionBean.totalPayment}" id="jumKompaun${line_rowNum-1}" size="12" class="number" readonly="readonly" style="background:transparent;border:0px;" /></td>
                        </tr>

                    </table>
                </c:if>

                <c:if test="${actionBean.statusAnsuran eq false}">
                    <table width="70%">
                        <tr><td align="center">
                                <br/>
                                <s:submit name="simpan" id="save" value="Ansuran" class="longbtn" onclick="return validateForm()"/>

                    </table>
                </c:if>


            </div>
        </fieldset>
    </div>

</s:form>

