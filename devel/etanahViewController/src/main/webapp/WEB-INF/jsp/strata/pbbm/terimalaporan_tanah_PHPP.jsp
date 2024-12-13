<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>
<%@ page import="java.io.*,etanah.Configuration" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">
    textarea.bolded {
        font-weight:bold;
    }
    textarea.italics {
        font-style:italic;
    }
</style>
<script type="text/javascript">

    function deleteRow4(index)
    {
        alert("recordNo:"+index);
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var url = '${pageContext.request.contextPath}/strata/permohonanStrata?updateListKemudahan&index='
                +index;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }
    function addKemudahan()
    {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);

        if(confirm('Adakah anda pasti untuk menambah data ini?')) {
            var url = '${pageContext.request.contextPath}/strata/permohonanStrata?addKemudahan';
            window.open(url,'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
        }
    }

    function changebkosrendah(value){

        if(value == "Y")
        {
            $('#kos_rendah').show();
        }
        else if(value == "N")
        {
            $('#kos_rendah').hide();
        }
    }

    function validateNumber1(elmnt,content) {
        //if it is character, then remove it..
        pbs();
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
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
    function bold(txt) {
        document.getElementById(txt).style.fontWeight = 'bold';
    }
    function italic(txt) {
        document.getElementById(txt).style.fontStyle = 'italic';
    }
    function uline(txt) {
        document.getElementById(txt).style.textDecoration='underline' ;
    }

    function doViewReport(v) {

        var idDokumen = document.getElementById("foto").options[document.getElementById("foto").selectedIndex].value;
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function muatNaikimej() {
        var val = $("#idMh").val();
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/strata/permohonanStrata_PHPP?uploadDoc&idMh='+ val;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=300, left=" + left + ",top=" + top);
    }

    function hapusimej(idDokumen){
        if((confirm("Anda pasti ingin menghapuskan imej ini?"))){
            var url = '${pageContext.request.contextPath}/strata/permohonanStrata_PHPP?hapusImej&idDokumen='+idDokumen;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }

    function hapusimej1(idDokumen){
        $('#dialog-confirm').dialog('open')
        $(function(){
            $( "#dialog" ).dialog( "destroy" );

            $( "#dialog-confirm" ).dialog({
                resizable: false,
                height:140,
                modal: true,
                buttons: {
                    "Tidak": function() {
                        alert('tidak selected');

                        $( this ).dialog( "close" );
                    },
                    "Ya": function() {
                        alert('ya selected');

                        var url = '${pageContext.request.contextPath}/strata/permohonanStrata?hapusImej&idDokumen='+idDokumen;
                        $.get(url,
                        function(data){
                            $('#page_div').html(data);
                            refreshPage();
                        },'html');

                        $( this ).dialog( "close" );
                    }
                }
            });
        });
    }
    $(document).ready(function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });

        $('#pbs').hide();
        var pem = '${actionBean.pemilik}';
        if(pem !=null){
            var pbs = '${actionBean.pemilik.laporanBilPetakProvisional}';
            if(pbs != "0" && pbs !='') {
                $('#pbs').show();
            }else{
                $('#pbs').hide();
            }
        }

        $('#divcat').hide();

        var lK1 = '${actionBean.pemilik.laporanKemudahan1}';
        if(lK1 != null && lK1 != ""){
            document.getElementById("aduan" + '${actionBean.pemilik.laporanKemudahan1}').checked = true;
        }

        var lK2 = '${actionBean.pemilik.laporanKemudahan2}';
        if(lK2 != null && lK2 != ""){
            document.getElementById("aduan"+ '${actionBean.pemilik.laporanKemudahan2}').checked = true;
        }

        var lK3 = '${actionBean.pemilik.laporanKemudahan3}';
        if(lK3 != null && lK3 != ""){
            document.getElementById("aduan"+ '${actionBean.pemilik.laporanKemudahan3}').checked = true;
        }

        var lK4 = '${actionBean.pemilik.laporanKemudahan4}';
        if(lK4 != null && lK4 != ""){
            document.getElementById("aduan"+ '${actionBean.pemilik.laporanKemudahan4}').checked = true;
        }

        var lK5 = '${actionBean.pemilik.laporanKemudahan5}';
        if(lK5 != null && lK5 != ""){
            document.getElementById("aduan"+ '${actionBean.pemilik.laporanKemudahan5}').checked = true;
        }

        var lK6 = '${actionBean.pemilik.laporanKemudahan6}';
        if(lK6 != null && lK6 != ""){
            document.getElementById("aduan"+ '${actionBean.pemilik.laporanKemudahan6}').checked = true;
        }

        var lK7 = '${actionBean.pemilik.laporanKemudahan7}';
        if(lK7 != null && lK7 != ""){
            document.getElementById("aduan"+ '${actionBean.pemilik.laporanKemudahan7}').checked = true;
        }

        var lK8 = '${actionBean.pemilik.laporanKemudahan8}';
        if(lK8 != null && lK8 != ""){
            document.getElementById("aduan"+ '${actionBean.pemilik.laporanKemudahan8}').checked = true;
        }

        var lK9 = '${actionBean.pemilik.laporanKemudahan9}';
        if(lK9 != null && lK9 != ""){
            document.getElementById("aduan"+ '${actionBean.pemilik.laporanKemudahan9}').checked = true;
        }
        
        var lK12 = '${actionBean.pemilik.laporanKemudahan12}';
        if(lK12 != null && lK12 != ""){
            document.getElementById("aduan"+ '${actionBean.pemilik.laporanKemudahan12}').checked = true;
        }

        var lK11 = '${actionBean.pemilik.laporanKemudahan13}';
        if(lK11 != null && lK11 != ""){
            document.getElementById("aduan"+ '${actionBean.pemilik.laporanKemudahan13}').checked = true;
            $('#divcat').show();
        }else{
            $('#divcat').hide();
        } 

        var lK10 = '${actionBean.pemilik.laporanKemudahan10}';
        if(lK10 != null && lK10 != ""){
            document.getElementById("aduan"+ '${actionBean.pemilik.laporanKemudahan10}').checked = true;            
        }

        $('#kos_rendah').hide();
        var jksr = '${actionBean.pemilik.laporanKosRendah}';
        if(jksr == ""){
            document.getElementById("lpr2").checked = true;
        }
        if(jksr == "Y"){
            document.getElementById("lpr1").checked = true;
            $('#kos_rendah').show();
        }else{
            document.getElementById("lpr2").checked = true;
        }
    });
    function changeHide(value){
        if(value == true){
            $('#divcat').show();
        }else{
            $('#divcat').hide();
        }
    }

    function clearForm(){
        var val = $("#idMh").val();
    <%--alert(val);--%>
            var url = '${pageContext.request.contextPath}/strata/permohonanStrata_PHPP?resetForm&idMh=' + val;
            $.post(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

        function pbs(){
            var pbs = $('#laporanBilBangunanProvisional').val();
            if(pbs != '0'){
                $('#pbs').show();
            }else{
                $('#pbs').hide();
            }
        }

        function hakdetails(val) {
    <%--alert(val);--%>
            doBlockUI();
            var url = '${pageContext.request.contextPath}/strata/permohonanStrata_PHPPRedonly?hakdetails&idMh=' + val;
    <%--alert(url);--%>
            $.ajax({
                type:"GET",
                url : url,
                dataType : 'html',
                error : function (xhr, ajaxOptions, thrownError){
                    alert("error=" + xhr.responseText);
                    doUnBlockUI();
                },
                success : function(data){
                    $('#page_div').html(data);
                    doUnBlockUI();
                }
            });
        }

</script>
<s:form beanclass="etanah.view.strata.PermohonanStrataPHPPReadOnlyActionBean">

    <div class="subtitle">
        <fieldset class="aras1" >
            <legend >Senerai Hakmilik Terlibat</legend> <br />
            <div align="center">
                <font color="#003194" style="font-weight: bold; font-family:Tahoma; font-size: 13px;">
                    Hakmilik :
                </font>
                <s:select name="idMh" onchange="hakdetails(this.value);" id="idMh">
                    <c:forEach items="${actionBean.senaraiHakmilikPermohonan}" var="item" varStatus="line">
                        <s:option value="${item.id}" style="width:400px">
                            ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                        </s:option>
                    </c:forEach>
                </s:select>  
            </div>
        </fieldset>
    </div> <br /><br />   
    <c:if test="${!edit}">
        <div class="subtitle">
            <fieldset class="aras1">
                <p>
                    <label>Tarikh Siasatan : </label>
                    <fmt:formatDate value="${actionBean.laporanTanah.tarikhSiasat}" pattern="dd/MM/yyyy" /> &nbsp;
                </p>

                <legend>Laporan Kedudukan Tanah</legend>
                <p>
                    <label>Lokasi : </label>
                    ${actionBean.pemilik.laporanLokasi}&nbsp;
                </p>
                <p>
                    <label>Mukim/Pekan/Bandar :</label>
                    ${actionBean.pemilik.laporanBandarTerdekat}&nbsp;
                </p>
                <c:if test="${((actionBean.pemilik.laporanUntukKediaman ne null)||(actionBean.pemilik.laporanUntukPejabat ne null) ||(actionBean.pemilik.laporanUntukPerniagaan ne null))}">
                    <p> <label>Kegunaan :</label>
                    </c:if>
                <table><tr><td>
                            <c:if test="${actionBean.pemilik.laporanUntukKediaman eq 'Y'}">
                                Kediaman &nbsp; <br />
                            </c:if>

                            <c:if test="${actionBean.pemilik.laporanUntukPejabat eq 'Y'}">
                                Pejabat &nbsp; <br>
                            </c:if>
                            <c:if test="${actionBean.pemilik.laporanUntukPerniagaan eq 'Y'}">
                                Perniagaan &nbsp;
                            </c:if> </td></tr></table>
                </p>

                <%--<c:if test="${actionBean.pemilik.laporanUntukPejabat eq 'Y'}">
                    <p>
                        <label>&nbsp;</label> Pejabat &nbsp;
                    </p>
                </c:if>

            <c:if test="${actionBean.pemilik.laporanUntukPerniagaan eq 'Y'}">
                <p>                   
                    <label>&nbsp;</label> Perniagaan &nbsp;
                </p>
            </c:if>--%>

                <c:if test="${actionBean.pemilik.laporanKegunaanLain ne null}">
                    <p>
                        <label>Jika lain kegunaan</label>
                        ${actionBean.pemilik.laporanKegunaanLain} &nbsp;
                    </p>
                </c:if>
                <p>
                    <label>Jenis Kos Rendah :</label>
                    <c:if test="${actionBean.pemilik.laporanKosRendah eq 'Y'}">
                        Ya <br />
                        <label>No. Sijil Kos Rendah :</label>
                        ${actionBean.pemilik.cfNoSijil} &nbsp;
                    </c:if>
                    <c:if test="${actionBean.pemilik.laporanKosRendah eq 'N'}">
                        Tidak
                    </c:if>
                </p>
                <br>
            </fieldset>

        </div>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Laporan Keadaan Tanah
                </legend>

                <p>
                    <label>Keadaan Tanah :  </label>
                    ${actionBean.pemilik.laporankeadaanTanah}&nbsp;
                </p>
                <br>
            </fieldset>
        </div>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Laporan Maklumat Bangunan
                </legend>

                <p>
                    <label>Bilangan Blok :  </label>
                    ${actionBean.pemilik.laporanBilBangunan}&nbsp;
                </p>
                <p>
                    <label>Bilangan Petak :  </label>
                    ${actionBean.pemilik.laporanBilPetak}&nbsp;
                </p>
                <p>
                    <label>Bilangan Blok Sementara:  </label>
                    ${actionBean.pemilik.laporanBilBangunanProvisional}&nbsp;
                </p>
                <p>
                    <label>Bilangan Petak di Blok Sementara:  </label>
                    ${actionBean.pemilik.laporanBilPetakProvisional}&nbsp;
                </p>            
                <p>
                    <label>Bilangan Petak Tanah:  </label>
                    ${actionBean.pemilik.laporanBilPetakTanah}&nbsp;
                </p>
                <br>
            </fieldset>
        </div>

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    <%--Kemudahan - Kemudahan--%>
                    Senarai Harta Bersama
                </legend>
                <c:set var="i" value="1"/>
                <c:if test="${actionBean.pemilik.laporanKemudahan1 ne null}"><p>
                        <label>Kemudahan - Kemudahan :</label> <%--1)--%>${i}) <c:set var="i" value="${i+1}"/>
                        ${actionBean.pemilik.laporanKemudahan1}&nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.pemilik.laporanKemudahan2 ne null}"><p>
                        <label>&nbsp;</label> <%--2)--%>${i}) <c:set var="i" value="${i+1}"/>
                        ${actionBean.pemilik.laporanKemudahan2}&nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.pemilik.laporanKemudahan3 ne null}"><p>
                        <label>&nbsp;</label> <%--3)--%>${i}) <c:set var="i" value="${i+1}"/>
                        ${actionBean.pemilik.laporanKemudahan3}&nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.pemilik.laporanKemudahan4 ne null}"> <p>
                        <label>&nbsp;</label> <%--4)--%>${i}) <c:set var="i" value="${i+1}"/>
                        ${actionBean.pemilik.laporanKemudahan4}&nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.pemilik.laporanKemudahan5 ne null}"> <p>
                        <label>&nbsp;</label> <%--5)--%>${i}) <c:set var="i" value="${i+1}"/>
                        ${actionBean.pemilik.laporanKemudahan5}&nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.pemilik.laporanKemudahan6 ne null}"><p>
                        <label>&nbsp;</label> <%--6)--%>${i}) <c:set var="i" value="${i+1}"/>
                        ${actionBean.pemilik.laporanKemudahan6}&nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.pemilik.laporanKemudahan7 ne null}"> <p>
                        <label>&nbsp;</label> <%--7)--%> ${i}) <c:set var="i" value="${i+1}"/>
                        ${actionBean.pemilik.laporanKemudahan7}&nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.pemilik.laporanKemudahan8 ne null}"> <p>
                        <label>&nbsp;</label> <%--8)--%>${i}) <c:set var="i" value="${i+1}"/>
                        ${actionBean.pemilik.laporanKemudahan8}&nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.pemilik.laporanKemudahan9 ne null}"> <p>
                        <label>&nbsp;</label> <%--9)--%>${i}) <c:set var="i" value="${i+1}"/>
                        ${actionBean.pemilik.laporanKemudahan9}&nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.pemilik.laporanKemudahan10 ne null}"><p>
                        <label>&nbsp;</label> <%--10)--%> ${i}) <c:set var="i" value="${i+1}"/>
                        ${actionBean.pemilik.laporanKemudahan10} - ${actionBean.pemilik.laporanKemudahan11}&nbsp;
                    </p>
                </c:if> 
                <c:if test="${actionBean.pemilik.laporanKemudahan11 ne null}"><p>
                        <label>&nbsp;</label> <%--11)--%> ${i}) <c:set var="i" value="${i+1}"/>
                        ${actionBean.pemilik.laporanKemudahan11}&nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.pemilik.laporanKemudahan12 ne null}"><p>
                        <label>&nbsp;</label> <%--12)--%> ${i}) <c:set var="i" value="${i+1}"/>
                        ${actionBean.pemilik.laporanKemudahan12}&nbsp;
                    </p>
                </c:if>
                <br>
            </fieldset>
        </div>
        <div class="content" align="center">
            <table class="tablecloth">
                <tr>
                    <th>&nbsp;Sempadan</th><th>Nombor Lot</th><th>Jenis Kegunaan Tanah</th>
                </tr>
                <tr>
                    <th>
                        Utara
                    </th>
                    <td>
                        <s:textarea name="lotUtara" class="normal_text" cols="30" rows="3" readonly="true" />
                    </td>
                    <td>
                        <s:textarea name="gunaUtara" id="gunaUtara" cols="50" rows="3" class="normal_text" readonly="true"/>
                    </td>
                </tr>
                <tr>
                    <th>
                        Selatan
                    </th>
                    <td>
                        <s:textarea name="lotSelatan" class="normal_text" cols="30" rows="3" readonly="true" />
                    </td>
                    <td>
                        <s:textarea name="gunaSelatan" id="gunaSelatan" cols="50" rows="3" class="normal_text" readonly="true"/>
                    </td>
                </tr>
                <tr>
                    <th>
                        Timur
                    </th>
                    <td>
                        <s:textarea name="lotTimur" class="normal_text" cols="30" rows="3" readonly="true"/>
                    </td>
                    <td>
                        <s:textarea name="gunaTimur" id="gunaTimur" cols="50" rows="3" class="normal_text" readonly="true"/>
                    </td>
                </tr><tr>
                    <th>
                        Barat
                    </th>
                    <td>
                        <s:textarea name="lotBarat" class="normal_text" cols="30" rows="3" readonly="true"/>
                    </td>
                    <td>
                        <s:textarea name="gunaBarat" id="gunaBarat" cols="50" rows="3" class="normal_text" readonly="true"/>
                    </td>
                </tr>
            </table>
        </div>
        <%--imej--%>
        <s:hidden name="idPermohonan" id="idmohon"/>
        <s:hidden name="imageFolderPath" id="lokasi"/>
        <div class="subtitle">
            <c:set var="filePath"  value="${actionBean.imageFolderPath}" />
            <fieldset class="aras1">
                <legend>Muat naik Imej Laporan</legend>
                <br>
                <table cellspacing="20" align="center">
                    <c:choose>
                        <c:when test="${fn:length(actionBean.senaraiImejLaporan) > 0}">
                            <c:forEach items="${actionBean.senaraiImejLaporan}" var="item" varStatus="loop">
                                <c:if test="${loop.count mod 8 eq 0}">
                                    <tr >
                                    </c:if>
                                    <td>
                                        <img alt="Imej" src="${pageContext.request.contextPath}/dokumen/view/${item.dokumen.idDokumen}" height="110" width="109" data-plusimage="${pageContext.request.contextPath}/dokumen/view/${item.dokumen.idDokumen}" />
                                        <br>
                                        ${item.catatan}
                                    </td>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                            <tr>
                                <td><font color="red">Tiada Imej Imbasan.</font></td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </table>
            </fieldset>
        </div>
    </c:if>
</s:form>