<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
    Modified By: Murali Aug 25, 2011
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
    $(document).ready(function() {
        $('#chk2').change(function() {
            $("#chk2").val(($(this).is(':checked')) ? "Y" : "");
        });
    });
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
        var val = $("#idMh").val();
        if((confirm("Anda pasti ingin menghapuskan imej ini?"))){
            var url = '${pageContext.request.contextPath}/strata/permohonanStrata_PHPP?hapusImej&idDokumen='+idDokumen+'&idMh='+val;
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

        //        $('#divcat').hide();
        //            
        //        var lK1 = '${actionBean.pemilik.laporanKemudahan1}';
        //        if(lK1 != null && lK1 != ""){
        //            document.getElementById("aduan" + '${actionBean.pemilik.laporanKemudahan1}').checked = true;
        //        }
        //         
        //        var lK2 = '${actionBean.pemilik.laporanKemudahan2}';
        //        if(lK2 != null && lK2 != ""){
        //            document.getElementById("aduan"+ '${actionBean.pemilik.laporanKemudahan2}').checked = true;
        //        }
        //
        //        var lK3 = '${actionBean.pemilik.laporanKemudahan3}';
        //        if(lK3 != null && lK3 != ""){
        //            document.getElementById("aduan"+ '${actionBean.pemilik.laporanKemudahan3}').checked = true;
        //        }
        //
        //        var lK4 = '${actionBean.pemilik.laporanKemudahan4}';
        //        if(lK4 != null && lK4 != ""){
        //            document.getElementById("aduan"+ '${actionBean.pemilik.laporanKemudahan4}').checked = true;
        //        }
        //         
        //        var lK5 = '${actionBean.pemilik.laporanKemudahan5}';
        //        if(lK5 != null && lK5 != ""){
        //            document.getElementById("aduan"+ '${actionBean.pemilik.laporanKemudahan5}').checked = true;
        //        }
        //        
        //        var lK6 = '${actionBean.pemilik.laporanKemudahan6}';
        //        if(lK6 != null && lK6 != ""){
        //            document.getElementById("aduan"+ '${actionBean.pemilik.laporanKemudahan6}').checked = true;
        //        }
        //
        //        var lK7 = '${actionBean.pemilik.laporanKemudahan7}';
        //        if(lK7 != null && lK7 != ""){
        //            document.getElementById("aduan"+ '${actionBean.pemilik.laporanKemudahan7}').checked = true;
        //        }
        //         
        //        var lK8 = '${actionBean.pemilik.laporanKemudahan8}';
        //        if(lK8 != null && lK8 != ""){
        //            document.getElementById("aduan"+ '${actionBean.pemilik.laporanKemudahan8}').checked = true;
        //        }
        //         
        //        var lK9 = '${actionBean.pemilik.laporanKemudahan9}';
        //        if(lK9 != null && lK9 != ""){
        //            document.getElementById("aduan"+ '${actionBean.pemilik.laporanKemudahan9}').checked = true;
        //        }
        //        
        //        var lK10 = '${actionBean.pemilik.laporanKemudahan10}';
        //        if(lK10 != null && lK10 != ""){
        //            document.getElementById("aduan"+ '${actionBean.pemilik.laporanKemudahan10}').checked = true;
        //            $('#divcat').show();
        //        }else{
        //            $('#divcat').hide();
        //        }
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
        var lK10 = '${actionBean.pemilik.laporanKemudahan10}';
        if(lK10 != null && lK10 != ""){
            document.getElementById("aduan"+ '${actionBean.pemilik.laporanKemudahan10}').checked = true;            
        }
            
        var lK11 = '${actionBean.pemilik.laporanKemudahan11}';
        if(lK11 != null && lK11 != ""){
            document.getElementById("aduan"+ '${actionBean.pemilik.laporanKemudahan11}').checked = true;
        }
        
        var lK12 = '${actionBean.pemilik.laporanKemudahan12}';
        if(lK12 != null && lK12 != ""){
            document.getElementById("aduan"+ '${actionBean.lainLainOnly}').checked = true;
            $('#divcat').show();
        }else{
            $('#divcat').hide();
        } 
        function changeHide(value){
            if(value == true){
                $('#divcat').show();
            }else{
                $('#divcat').hide();
            }
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
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            $.post(url,
            function(data){
                $('#page_div').html(data);
                $.unblockUI();
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

        function hakdetails (val) {
    <%--alert(val);--%>
            doBlockUI();
            var url = '${pageContext.request.contextPath}/strata/permohonanStrata_PHPP?hakdetails&idMh=' + val;
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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/images/up/plusimageviewer.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/images/up/plusimageviewer.js"></script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.strata.PermohonanStrataPHPPActionBean">
    <s:messages/>
    <s:errors/>    
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Senarai Hakmilik Terlibat</legend>
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
            <br />
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Laporan Kedudukan Tanah</legend>
            <p>
                <label>Tarikh Siasatan :</label>
                <s:text name="tarikhSiasatan" id="tarikhSiasatan" formatPattern="dd/MM/yyyy" class="datepicker" readonly="true"/>
            </p>
            <p>
                <label>Lokasi :</label>
                <s:textarea name="laporanLokasi" cols="50" rows="5" class="normal_text"/>
            </p>
            <p>
                <label>Mukim/Pekan/Bandar :</label>
                <s:select name="laporanBandarTerdekat">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="nama"/>
                </s:select>
            </p>
            <p>
                <label>Kegunaan :</label>
                <s:checkbox name="laporanUntukKediaman" value="Y" /> Kediaman
            </p>
            <p>                
                <label>&nbsp;</label>
                <s:checkbox name="laporanUntukPerniagaan" value="Y" /> Perniagaan
            </p>
            <p>                
                <label>&nbsp;</label>
                <s:checkbox name="laporanUntukPejabat" value="Y" /> Pejabat
            </p>
            <table border="0" width="72%">
                <tr>
                    <td valign="top">
                        <label>&nbsp;</label>
                        <c:if test="${actionBean.chk2 eq null}">
                            <input type="checkbox" name="chk" id="chk2"> Lain-lain (Sila Nyatakan)
                        </c:if>
                        <c:if test="${actionBean.chk2 ne null}">
                            <input type="checkbox" name="chk" id="chk2" checked> Lain-lain (Sila Nyatakan)
                        </c:if>
                        <p><p/> 
                        <label>&nbsp;</label>
                        <s:textarea name="laporanKegunaanLain" cols="30" rows="3" class="normal_text"/>
                    </td>
                </tr>
            </table>
            <p>
                <label>Jenis Kos Rendah :</label>                
                <input type="radio" name="laporanKosRendah" value="Y" id="lpr1" onclick ="javaScript:changebkosrendah(this.value)"/> Ya
                <input type="radio" name="laporanKosRendah" value="N" id="lpr2" onclick ="javaScript:changebkosrendah(this.value)"/> Tidak
            </p>
            <div id="kos_rendah" class="subtitle">
                <p>                    
                    <label>No. Sijil Kos Rendah :</label>
                    <s:text name="cfNoSijil"/>
                </p>
            </div>
            <br>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Laporan Keadaan Tanah</legend>
            <p>
                <label>Keadaan Tanah :</label>
                <s:textarea  name="laporankeadaanTanah" cols="50" rows="4" class="normal_text"/>
            </p>
            <br>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Laporan Maklumat Bangunan</legend>
            <p>
                <label>Bilangan Blok :</label>
                <s:text name="laporanBilBangunan" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label>Bilangan Petak :</label>
                <s:text name="laporanBilPetak" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <!--            <p>
                            <label>Bilangan Blok Sementara :</label>
            <%--<s:text name="laporanBilBangunanProvisional" id="laporanBilBangunanProvisional" onkeyup="validateNumber1(this,this.value);"/>--%>
        </p>-->
            <div id="pbs">
                <p>
                    <label>Bilangan Petak di Blok Sementara :</label>
                    <s:text name="laporanBilPetakProvisional" onkeyup="validateNumber(this,this.value);"/>
                </p>
            </div>
            <!--added by murali @NS PAT 13-08-2012: temporarly saving in to this coloumn -->
            <p>
                <label>Bilangan Petak Tanah :</label>
                <s:text name="laporanBilPetakTanah" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <br>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Laporan Harta Bersama</legend>
            <%--<label>Kemudahan - Kemudahan : </label>--%>
            <label>Senarai Harta Bersama :</label>

            <table>
                <tr>
                    <td>
                        <c:forEach items="${actionBean.senaraikodHartaBersama}" var="line">
                            <c:if test="${line.nama ne 'Lain-lain'}">
                                <s:checkbox name="${line.nama}" value="${line.nama}" id="aduan${line.nama}"/> ${line.nama}<br/>
                            </c:if>
                            <c:if test="${line.nama eq 'Lain-lain'}">
                                <s:checkbox name="${line.nama}" value="${line.nama}" id="aduan${line.nama}" onclick="javaScript:changeHide(this.checked)"/> ${line.nama}<br/>
                                <div id="divcat">
                                    <s:textarea name="catatan1" rows="5" cols="36" id="catatan1" class="normal_text"/>
                                </div>
                            </c:if>
                        </c:forEach>
                    </td>
                </tr>

            </table>
            <br/>
        </fieldset>
    </div>
    <div class="content">
        <fieldset class="aras1">
            <legend>Laporan Sempadan</legend>

            <div class="content" align="center">
                <table class="tablecloth">
                    <tr>
                        <th>&nbsp;Sempadan</th>
                        <th>Nombor Lot</th>
                        <th>Jenis Kegunaan Tanah</th>
                    </tr>
                    <tr>
                        <th>Utara</th>                   
                        <td>
                            <s:textarea name="lotUtara" class="normal_text" cols="37" rows="3" />
                        </td>
                        <td>
                            <s:textarea name="gunaUtara" id="gunaUtara" cols="50" rows="3" class="normal_text"/>
                        </td>                  
                    </tr>
                    <tr>
                        <th>Selatan</th>                   
                        <td>
                            <s:textarea name="lotSelatan" class="normal_text" cols="37" rows="3" />
                        </td>
                        <td>
                            <s:textarea name="gunaSelatan" id="gunaSelatan" cols="50" rows="3" class="normal_text"/>
                        </td>                  
                    </tr>
                    <tr>
                        <th>Timur</th>                   
                        <td>
                            <s:textarea name="lotTimur" class="normal_text" cols="37" rows="3"/>
                        </td>
                        <td>
                            <s:textarea name="gunaTimur" id="gunaTimur" cols="50" rows="3" class="normal_text"/>
                        </td>                  
                    </tr>
                    <tr>
                        <th>Barat</th>                   
                        <td>
                            <s:textarea name="lotBarat" class="normal_text" cols="37" rows="3"/>
                        </td>
                        <td>
                            <s:textarea name="gunaBarat" id="gunaBarat" cols="50" rows="3" class="normal_text"/>
                        </td>                  
                    </tr>


                </table>
            </div>
            <br />
            <div align="center">
                <input type="checkbox" id="checkLaporan" name="checkLaporan" value="Yes" />
                <font color="red">Maklumat Laporan Tanah bagi kedua-dua hakmilik adalah sama.</font>
            </div>
            <br />
            <div align="left">
                <label>&nbsp;</label>               
                <s:button name="simpanLaporanTanah" value="Simpan" class="btn"  onclick="doSubmit(this.form, this.name, 'page_div')"/>
                <s:button class="btn" value="Isi Semula" name="resetForm" onclick="clearForm()"/>
            </div>
        </fieldset>
    </div>

    <%--imej--%>
    <s:hidden name="idPermohonan" id="idmohon"/>
    <s:hidden name="imageFolderPath" id="lokasi"/>
    <div class="subtitle">
        <c:set var="filePath"  value="${actionBean.imageFolderPath}" />
        <fieldset class="aras1">
            <legend>Muat naik Imej Laporan</legend>
            <%--<c:if test="${edit}">--%>
            <p>
                <font color="red">*</font> Sila klik 'Muatnaik imej' untuk muatnaik imej <br>
                <font color="red">*</font> klik 'Hapus Imej' untuk hapus imej
            </p><br>
            <p>
                <s:button name="muatnaikimej" value="Muatnaik imej" class="longbtn"  onclick="muatNaikimej();" /></p>
                <%--</c:if>--%>
            <br>
            <%
                String file = "C:/" + (String) pageContext.getAttribute("filePath");
                File f = new File(file);
                String[] fileNames = f.list();
                File[] fileObjects = f.listFiles();
            %>
            <table cellspacing="20" align="center">
                <c:choose>
                    <c:when test="${fn:length(actionBean.senaraiImejLaporan) > 0}">
                        <c:forEach items="${actionBean.senaraiImejLaporan}" var="item" varStatus="loop">
                            <%--<font color="black" size="3">${loop.count} . ${item.dokumen.kodDokumen.nama} </font><br/>--%>
                            <c:if test="${loop.count mod 8 eq 0}">
                                <tr>
                                </c:if>
                                <td>
                                    <%--<c:if test="${edit}">--%>
                                    <s:button name="hapusImej" value="Hapus Imej" class="btn"onclick="hapusimej('${item.dokumen.idDokumen}');"/><br/>
                                    <%--</c:if>--%>
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
</s:form>