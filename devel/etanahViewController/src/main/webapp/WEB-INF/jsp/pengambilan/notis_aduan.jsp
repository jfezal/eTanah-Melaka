<%-- 
    Document   : notis_aduan
    Created on : 15-Apr-2011, 18:42:45
    Author     : nordiyana
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">

    $(document).ready(function() {

        //            maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});


        }); //END OF READY FUNCTION



        function validateNumber(elmnt,content) {
            //if it is character, then remove it..
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }
  function validation() {
     var count1=$("#rowCount1").val();
    for(var i=1;i<=count1;i++){
        var kandungan1= $("#kandungan1"+i).val();
        if(kandungan1 == ""){
            alert('Sila pilih " 2.1 Latar Belakang " terlebih dahulu.');
            $("#kandungan1"+i).focus();
            return false;
        }
    }

    var count2=$("#rowCount2").val();
    for(var i=1;i<=count2;i++){
        var kandungan2= $("#kandungan2"+i).val();
        if(kandungan2 == ""){
            alert('Sila pilih " 3. Perihal Tanah " terlebih dahulu.');
            $("#kandungan2"+i).focus();
            return false;
        }
    }

    <%--var count3=$("#rowCount3").val();
    for(var i=1;i<=count3;i++){
        var kandungan3= $("#kandungan3"+i).val();
        if(kandungan3 == ""){
            alert('Sila pilih " 4. PANDANGAN PENTADBIR TANAH " terlebih dahulu.');
            $("#kandungan3"+i).focus();
            return false;
        }
    }--%>

    var count4=$("#rowCount4").val();
    for(var i=1;i<=count4;i++){
        var kandungan4= $("#kandungan4"+i).val();
        if(kandungan4 == ""){
            alert('Sila pilih " 4. PERAKUAN PENTADBIR TANAH " terlebih dahulu.');
            $("#kandungan4"+i).focus();
            return false;
        }
    }

    <%--var count5=$("#rowCount5").val();
    for(var i=1;i<=count5;i++){
        var kandungan5= $("#kandungan5"+i).val();
        if(kandungan5 == ""){
            alert('Sila pilih " 5. PERAKUAN PENGARAH TANAH DAN GALIAN " terlebih dahulu.');
            $("#kandungan5"+i).focus();
            return false;
        }
    }--%>

    if($("#kertasBil").val() == ""){
            $("#kertasBil").val("Tiada Data");
    }

    if($("#masa").val() == ""){
            $("#masa").val("Tiada Data");
    }

    if($("#tarikhmesyuarat").val() == ""){
            $("#tarikhmesyuarat").val("Tiada Data");
    }

    if($("#tempat").val() == ""){
            $("#tempat").val("Tiada Data");
    }

    if($("#syorPtg").val() == ""){
            $("#syorPtg").val("Tiada Data");
    }

    if($("#jam").val() == ""){
    alert('Sila pilih " JAM " terlebih dahulu.');
    $("#jam").focus();
    return false;
    }
    if($("#minit").val() == ""){
        alert('Sila pilih " MINIT " terlebih dahulu.');
        $("#minit").focus();
        return false;
    }
    if($("#pagiPetang").val() == ""){
        alert('Sila pilih " PAGI/PETANG " terlebih dahulu.');
        $("#pagiPetang").focus();
        return false;
    }
    return true;
}
function muatNaikForm(idNotis) {
        var idHakmilik = $('#idHakmilik').val();
        var showPP = $('#showPP').val();
        var showHP = $('#showHP').val();
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_notis_borang_aduan?popupUpload&idNotis='+idNotis;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }

    function scan(idNotis) {
        var idHakmilik = $('#idHakmilik').val();
        var showPP = $('#showPP').val();
        var showHP = $('#showHP').val();
        var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_notis_borang_aduan?popupScan&idNotis='+idNotis;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=800,height=700");
    }
       function refreshPagePBT(){
            $.get("${pageContext.request.contextPath}/pengambilan/carian_aduan?refreshpage",
            function(data){
                $('#page_div').html(data);
            },'html');
        }

</script>
    <%--<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />--%>
    <s:form beanclass="etanah.view.stripes.pengambilan.PenerimaanNotisBorangAduanActionBean" name="form">
        <s:errors/>
        <s:messages/>
       <div class="subtitle">
         <fieldset class="aras1">
            <%--<c:if test="${edit}">--%>
            <legend>Rekod Penyampaian Notis :Pengadu</legend>
                    <div class="subtitle" id="main">
                    <div class="content" align="center">
                        <s:hidden name="idNotis" id="idNotis" value="idNotis"/>
                        <%--${actionBean.pihak.idPihak}--%>
                        <display:table class="tablecloth" name="${actionBean.listNotisPengadu}"
                                   cellpadding="0" cellspacing="0" requestURI="/pengambilan/penerimaan_notis_borang_aduan" id="line">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column property="pihak.pihak.nama" title="Nama"/>
                        <display:column title="Nama Penghantar Notis " >
                            <s:select name="namaPengahantarA[${line_rowNum-1}]" id="namaPengahantarA${line_rowNum-1}" style="width:150px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiPenghantarNotis}" label="nama" value="idPenghantarNotis"/>
                            </s:select>
                        </display:column>
                        <display:column title="Status Penyampaian" >
                            <s:select name="kodStatusTerimaA[${line_rowNum-1}]" id="kodStatusTerimaA{line_rowNum-1}" >
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodStatusTerima}" label="nama" value="kod"/>
                            </s:select>
                        </display:column>
                        <display:column title="Cara Penghantaran" >
                            <s:select name="kodPenghantaranA[${line_rowNum-1}]" id="kodPenghantaranA${line_rowNum-1}" >
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodCaraPenghantaran}" label="nama" value="kod"/>
                            </s:select>
                        </display:column>
                        <display:column title="Tarikh" >
                            <p>H : <s:text class="datepicker" name="tarikhHantarA[${line_rowNum-1}]" id="tarikhDihantarA${line_rowNum-1}" formatPattern="dd/MM/yyyy" style="width:90px;" /><br><font size="1" color="red"> (cth : hh / bb / tttt)</font></p>
                            <p>T : <s:text class="datepicker" name="tarikhTerimaA[${line_rowNum-1}]" id="tarikhTerimaA${line_rowNum-1}" formatPattern="dd/MM/yyyy" style="width:90px;" /><br><font size="1" color="red"> (cth : hh / bb / tttt)</font></p>
                        </display:column>
                        <display:column  title="Catatan" >
                            <s:textarea name="catatanPenerimaanA[${line_rowNum-1}]" id="catatanPenerimaanA${line_rowNum-1}" rows="3" cols="20" />
                        </display:column>
                        <display:column title="Tindakan">
                        <p align="center">
                            <%--<c:if test="${actionBean.idDokumenListP[line_rowNum-1] eq ''}">--%>
                            <c:if test="${line.buktiPenerimaan == null}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                     onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.idNotis}');return false;" title="Muat Naik Dokumen"/>
                                /
                                <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                     onmouseover="this.style.cursor='pointer';" onclick="scan('${line.idNotis}');return false;" title="Imbas Dokumen"/>
                            </c:if>
                            <%--<c:if test="${actionBean.idDokumenListP[line_rowNum-1] ne ''}">--%>
                                <c:if test="${line.buktiPenerimaan != null}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                     onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.idNotis}');return false;" title="Muat Naik Dokumen"/>
                                /
                                <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                     onmouseover="this.style.cursor='pointer';" onclick="scan('${line.idNotis}');return false;" title="Imbas Dokumen"/>
                                /
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                     onclick="doViewReport('${actionBean.idDokumenListP[line_rowNum-1]}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                            </c:if>
                        </p>
                        </display:column>
                    </display:table>

                    <br/>
                </div>
               </div>
        <br/>
        </fieldset>
         <br>
          <fieldset class="aras1">
            <legend>Rekod Penyampaian Notis :Pemohon</legend>
                    <div class="subtitle" id="main">
                    <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.listNotisPemohon}"
                                   cellpadding="0" cellspacing="0" requestURI="/pengambilan/penerimaan_notis_borang_aduan" id="line">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column property="pihak.pihak.nama" title="Nama"/>
                        <display:column title="Nama Penghantar Notis">
                            <s:select name="namaPengahantarP[${line_rowNum-1}]" id="namaPengahantarP${line_rowNum-1}" style="width:150px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiPenghantarNotis}" label="nama" value="idPenghantarNotis"/>
                            </s:select>
                        </display:column>
                        <display:column title="Status Penyampaian" >
                            <s:select name="kodStatusTerimaP[${line_rowNum-1}]" id="kodStatusTerimaP{line_rowNum-1}" >
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodStatusTerima}" label="nama" value="kod"/>
                            </s:select>
                        </display:column>
                        <display:column title="Cara Penghantaran" >
                            <s:select name="kodPenghantaranP[${line_rowNum-1}]" id="kodPenghantaranP${line_rowNum-1}" >
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodCaraPenghantaran}" label="nama" value="kod"/>
                            </s:select>
                        </display:column>
                        <display:column title="Tarikh" >
                            <p>H : <s:text class="datepicker" name="tarikhHantarP[${line_rowNum-1}]" id="tarikhDihantarP${line_rowNum-1}" formatPattern="dd/MM/yyyy" style="width:90px;" /><br><font size="1" color="red"> (cth : hh / bb / tttt)</font></p>
                            <p>T : <s:text class="datepicker" name="tarikhTerimaP[${line_rowNum-1}]" id="tarikhTerimaP${line_rowNum-1}" formatPattern="dd/MM/yyyy" style="width:90px;" /><br><font size="1" color="red"> (cth : hh / bb / tttt)</font></p>
                        </display:column>
                        <display:column  title="Catatan" >
                            <s:textarea name="catatanPenerimaanP[${line_rowNum-1}]" id="catatanPenerimaanP${line_rowNum-1}" rows="3" cols="20" />
                        </display:column>
                        <display:column title="Tindakan">
                        <p align="center">
                            <%--<c:if test="${actionBean.idDokumenListP[line_rowNum-1] eq ''}">--%>
                                <c:if test="${line.buktiPenerimaan == null}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                     onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.idNotis}');return false;" title="Muat Naik Dokumen"/>
                                /
                                <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                     onmouseover="this.style.cursor='pointer';" onclick="scan('${line.idNotis}');return false;" title="Imbas Dokumen"/>
                            </c:if>
                            <%--<c:if test="${actionBean.idDokumenListP[line_rowNum-1] ne ''}">--%>
                                <c:if test="${line.buktiPenerimaan != null}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                     onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.idNotis}');return false;" title="Muat Naik Dokumen"/>
                                /
                                <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                     onmouseover="this.style.cursor='pointer';" onclick="scan('${line.idNotis}');return false;" title="Imbas Dokumen"/>
                                /
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                     onclick="doViewReport('${actionBean.idDokumenListP[line_rowNum-1]}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                            </c:if>
                        </p>
                        </display:column>
                    </display:table>
                    </div>

                    <br/>
                </div>
        <br/>
                 </fieldset>
       <br>
       <fieldset class="aras1">
            <legend>Rekod Penyampaian Notis :JPPH</legend>
                    <div class="subtitle" id="main">
                    <div class="content" align="center">
                        <%--<s:hidden name="idPihak" id="idPihak" value="pihak.idPihak"/>--%>
                        <%--${actionBean.pihak.idPihak}--%>
                        <display:table class="tablecloth" name="${actionBean.listNotisJPPH}"
                                   cellpadding="0" cellspacing="0" requestURI="/pengambilan/penerimaan_notis_borang_aduan" id="line">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column property="penerimaNotis" title="Nama"/>
                        <display:column title="Nama Penghantar Notis">
                            <s:select name="namaPengahantarJ[${line_rowNum-1}]" id="namaPengahantarJ${line_rowNum-1}" style="width:150px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiPenghantarNotis}" label="nama" value="idPenghantarNotis"/>
                            </s:select>
                        </display:column>
                        <display:column title="Status Penyampaian" >
                            <s:select name="kodStatusTerimaJ[${line_rowNum-1}]" id="kodStatusTerimaJ{line_rowNum-1}" >
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodStatusTerima}" label="nama" value="kod"/>
                            </s:select>
                        </display:column>
                        <display:column title="Cara Penghantaran" >
                            <s:select name="kodPenghantaranJ[${line_rowNum-1}]" id="kodPenghantaranJ${line_rowNum-1}" >
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodCaraPenghantaran}" label="nama" value="kod"/>
                            </s:select>
                        </display:column>
                        <display:column title="Tarikh" >
                            <p>H : <s:text class="datepicker" name="tarikhHantarJ[${line_rowNum-1}]" id="tarikhDihantarJ${line_rowNum-1}" formatPattern="dd/MM/yyyy" style="width:90px;" /><br><font size="1" color="red"> (cth : hh / bb / tttt)</font></p>
                            <p>T : <s:text class="datepicker" name="tarikhTerimaJ[${line_rowNum-1}]" id="tarikhTerimaJ${line_rowNum-1}" formatPattern="dd/MM/yyyy" style="width:90px;" /><br><font size="1" color="red"> (cth : hh / bb / tttt)</font></p>
                        </display:column>
                        <display:column  title="Catatan" >
                            <s:textarea name="catatanPenerimaanJ[${line_rowNum-1}]" id="catatanPenerimaanJ${line_rowNum-1}" rows="3" cols="20" />
                        </display:column>
                        <display:column title="Tindakan">
                        <p align="center">
                            <%--<c:if test="${actionBean.idDokumenListP[line_rowNum-1] eq ''}">--%>
                            <c:if test="${line.buktiPenerimaan == null}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                     onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.idNotis}');return false;" title="Muat Naik Dokumen"/>
                                /
                                <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                     onmouseover="this.style.cursor='pointer';" onclick="scan('${line.idNotis}');return false;" title="Imbas Dokumen"/>
                            </c:if>
                            <%--<c:if test="${actionBean.idDokumenListP[line_rowNum-1] ne ''}">--%>
                            <c:if test="${line.buktiPenerimaan != null}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                     onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.idNotis}');return false;" title="Muat Naik Dokumen"/>
                                /
                                <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                     onmouseover="this.style.cursor='pointer';" onclick="scan('${line.idNotis}');return false;" title="Imbas Dokumen"/>
                                /
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                     onclick="doViewReport('${actionBean.idDokumenListP[line_rowNum-1]}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                            </c:if>
                        </p>
                        </display:column>
                    </display:table>
                        <br>
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </div>
                    <br/>
                </div>
        <br/>
                 </fieldset>
                 </div>
 </s:form>

</body>


