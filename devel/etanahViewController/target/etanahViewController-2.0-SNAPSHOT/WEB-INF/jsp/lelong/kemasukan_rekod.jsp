<%--
    Document   : kemasukan_rekod
    Created on : Apr 21, 2010, 3:23:34 AM
    Author     : mdizzat.mashrom
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">

    function showReport(){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        var url = '${pageContext.request.contextPath}/lelong/kemasukkan_rekod?genReport';
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function(xhr, ajaxOptions, thrownError) {
                alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                $.unblockUI();
            },
            success : function(data) {
                if(data.indexOf('Laporan telah dijana')==0){
                    alert(data);
                    $('#folder').click();
                }else {
                    alert(data);
                    $('#urusan').click();
                }
                $.unblockUI();
            }
        });
    }

    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);

            setTimeout(function(){
                self.close();
            }, 100);
        },'html');
    }

    $(document).ready(function() {
        var bil = $(".rowCount").length;

        for (var i = 0; i < bil; i++){
            $('#viewReport'+i).hide();
        }
    });

    function validateDate(i,date){
   
        var vsplit = date.split('/');
        var date = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var date2 = $('#tarikhDihantar'+i).val();
        var vsplit = date2.split('/');
        var date2 = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var hantar = new Date(date2);
        var terima = new Date(date);
        if(terima < hantar){
            alert("Tarikh Dimasukkan Salah");
            $('#tarikhTerima'+i).val("");
        }
    }

    function validateHantar12(i){
        var date = '${actionBean.permohonan.infoAudit.tarikhMasuk}';
        var dateSplit = date.split('-');
        var date1 = new Date(dateSplit[0], dateSplit[1]-1, dateSplit[2].substr(0, 2));

        var date2 = $('#tarikhDihantar'+i).val();
        var dateSplit2 = date2.split('/');
        var dateConv = new Date(dateSplit2[2], dateSplit2[1]-1, dateSplit2[0]);
        
        if(date1 > dateConv){
            alert("Tarikh Hantar Yang Dimasukkan Mestilah Melebihi Dari Tarikh Masuk Permohonan");
            $('#tarikhDihantar'+i).val("");
        }
    }


    function validate(){
        
        var bil = $(".rowCount").length;

        for (var i = 0; i < bil; i++){

            var nama = document.getElementById('namela'+i);
            var kod = document.getElementById('kodPenyampaian'+i);
            var cara = document.getElementById('kodPenghantaran' + i);
            var tarikh = document.getElementById('tarikhDihantar'+i);
            
            if(cara.value =="0"){
                alert("Sila Pilih Cara Penghantaran");
                return false;
            }else if(cara.value =="AR" || cara.value == "PO"){
                
                if(kod.value == "" ){
                    alert("Sila Pilih Status Penyampaian");
                    $('#kodPenyampaian'+i).focus();
                    return false;
                }
                if(tarikh.value == "" ){
                    alert("Sila Pilih Tarikh Hantar");
                    $('#tarikhDihantar'+i).focus();
                    return false;
                }
            }else{
                if(nama.value == ""){
                    alert("Sila Masukkan Nama Penghantar Notis");
                    $('#namela'+i).focus();
                    return false;
                }
                if(kod.value == "" ){
                    alert("Sila Pilih Status Penyampaian");
                    $('#kodPenyampaian'+i).focus();
                    return false;
                }
                if(tarikh.value == "" ){
                    alert("Sila Pilih Tarikh Hantar");
                    $('#tarikhDihantar'+i).focus();
                    return false;
                }
            }
                    
        }
        return true;
    }
    
    function validate2(){

        var bil = $(".rowCount").length;

        for (var i = 0; i < bil; i++){

            var nama = document.getElementById('namela'+i);
            var kod = document.getElementById('kodPenyampaian'+i);
            var cara = document.getElementById('kodPenghantaran' + i);
            var tarikh = document.getElementById('tarikhDihantar'+i);
            
            if(cara.value =="0"){
                alert("Sila Pilih Cara Penghantaran");
                return false;
            }else if(cara.value =="AR" || cara.value == "PO"){
                
                if(kod.value == "" ){
                    alert("Sila Pilih Status Penyampaian");
                    $('#kodPenyampaian'+i).focus();
                    return false;
                }
                if(tarikh.value == "" ){
                    alert("Sila Pilih Tarikh Hantar");
                    $('#tarikhDihantar'+i).focus();
                    return false;
                }
            }else{
                if(nama.value == ""){
                    alert("Sila Masukkan Nama Penghantar Notis");
                    $('#namela'+i).focus();
                    return false;
                }
                if(kod.value == "" ){
                    alert("Sila Pilih Status Penyampaian");
                    $('#kodPenyampaian'+i).focus();
                    return false;
                }
                if(tarikh.value == "" ){
                    alert("Sila Pilih Tarikh Hantar");
                    $('#tarikhDihantar'+i).focus();
                    return false;
                }
            }
                    
        }
        return true;
    }
    function muatNaikForm(notis,id) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/lelong/kemasukkan_rekod?popupUpload&idNotis='+notis+'&id='+id;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }

    function addPenghantarNotis(val){
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/lelong/kemasukkan_rekod?popupPenghantarNotis&id=' + val;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function doViewReport2(v) {
        var url = '${pageContext.request.contextPath}/lelong/kemasukkan_rekod?view&idNotis='+v;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
        
    function scan(notis,id) {
        var url = '${pageContext.request.contextPath}/lelong/kemasukkan_rekod?popupScan&idNotis='+notis+'&id='+id;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=800,height=700");
    }

    function findNoKadPengenalan(idPenghantarNotis,index){
        if(idPenghantarNotis != ""){
            $.get('${pageContext.request.contextPath}/lelong/kemasukkan_rekod?findNoPengenalan&idPenghantarNotis='+idPenghantarNotis+'&index='+index,
            function(data){
                $('#icLoad'+index).empty();
                $('#icLoad'+index).append("No.KP:("+data+")");
            }, 'html');

        }
    }

</script>

<s:form beanclass="etanah.view.stripes.lelong.KemasukkanRekodActionBean" id="folder_div">

    <s:messages/>
    <s:errors/>&nbsp;
    <c:if test="${actionBean.view ne true}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    <c:if test="${actionBean.gantian eq false}">
                        Maklumat Penghantaran Notis Siasatan
                    </c:if>
                    <c:if test="${actionBean.gantian eq true}">
                        Maklumat Penghantaran Pemberitahuan Penangguhan Siasatan
                    </c:if>
                </legend><br>

                <p class=instr>
                    *<em>Petunjuk :</em>
                </p>
                <p class=instr>
                    <em>H:</em> Tarikh Hantar
                    <em>T:</em> Tarikh Terima
                </p>
                <font size="2" color="black"></font>
                <p>
                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                         width="20" height="20" /> - <font size="1" color="black">Papar Dokumen</font>&nbsp;&nbsp;&nbsp;
                    <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                         width="20" height="20" /> - <font size="1" color="black">Muat Naik Dokumen</font>&nbsp;&nbsp;&nbsp;
                    <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png"
                         width="20" height="20" /> - <font size="1" color="black">Imbas Dokumen</font>
                </p><br>

                <div class="content" align="center">

                    <display:table class="tablecloth" name="${actionBean.listNotis}" cellpadding="0" cellspacing="0" requestURI="/lelong/kemasukkan_rekod" id="line">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Nama" class="rowCount" style="text-transform:uppercase;">
                            ${line.hakmilikPihakBerkepentingan.pihak.nama}<br>
                            (
                            <c:if test="${line.pihak ne null}">
                                <c:if test="${line.pihak.jenis.kod ne 'PG'}">
                                    Penggadai
                                </c:if>
                                <c:if test="${line.pihak.jenis.kod eq 'PG'}">
                                    ${line.hakmilikPihakBerkepentingan.jenis.nama}
                                </c:if>
                            </c:if>
                            <c:if test="${line.pihak eq null}">
                                ${line.hakmilikPihakBerkepentingan.jenis.nama}
                            </c:if>)
                        </display:column>
                        <display:column title="Nama Penghantar Notis" class="namaPG" style="text-transform:uppercase;">
                            <s:select name="namaPengahantar${line_rowNum-1}" id="namela${line_rowNum-1}" onchange="findNoKadPengenalan(this.value,'${line_rowNum-1}');" value="${line.penghantarNotis.idPenghantarNotis}" style="width:200px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${actionBean.senaraiPenghantarNotis}" label="nama" value="idPenghantarNotis"/>
                            </s:select>
                            &nbsp;
                            <%--<img src="${pageContext.request.contextPath}/pub/images/tambah.png" height="15" width="15" alt="Tambah Penghantar Notis"--%>
                            <%--onmouseover="this.style.cursor='pointer';" onclick="addPenghantarNotis('${line_rowNum-1}');return false;" title="Tambah Penghantar Notis"/>--%>
                            <br><div id="icLoad${line_rowNum-1}">No.K/P:(${line.penghantarNotis.noPengenalan})</div>
                        </display:column>
                        <display:column title="Status Penyampaian" class="${line_rowNum}" style="text-transform:uppercase;">
                            <s:select name="kodStatusTerima${line_rowNum-1}" id="kodPenyampaian${line_rowNum-1}" value="${line.kodStatusTerima.kod}">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:option value="TM">Diterima</s:option>
                                <s:option value="XT">Tidak Diterima</s:option>
                            </s:select>
                        </display:column>
                        <display:column title="Cara Penghantaran" class="${line_rowNum}" style="text-transform:uppercase;">
                            <s:select name="kodCaraPenghantaran${line_rowNum-1}" id="kodPenghantaran${line_rowNum-1}" value="${line.kodCaraPenghantaran.kod}">
                                <s:option value="0">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodCaraPenghantaran}" label="nama" value="kod"/>
                            </s:select>
                        </display:column>
                        <display:column title="Tarikh" class="${line_rowNum}">
                            <p>H : <s:text class="datepicker" onchange="validateHantar12('${line_rowNum-1}');" id="tarikhDihantar${line_rowNum-1}" name="listNotis[${line_rowNum-1}].tarikhHantar" formatPattern="dd/MM/yyyy" style="width:100px;"/>
                                <br>
                                &nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                            </p>
                            <p>T : <s:text class="datepicker" onchange="validateDate('${line_rowNum-1}',this.value);" id="tarikhTerima${line_rowNum-1}" name="listNotis[${line_rowNum-1}].tarikhTerima" formatPattern="dd/MM/yyyy" style="width:100px;"/>
                                <br>
                                &nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                            </p>
                        </display:column>
                        <display:column  title="Catatan" class="${line_rowNum}" style="text-transform:uppercase;" >
                            <s:textarea name="listNotis[${line_rowNum-1}].catatanPenerimaan" id="catatanTerima${line_rowNum-1}" rows="3" cols="20" onblur="this.value=this.value.toUpperCase();" />
                        </display:column>
                        <display:column title="Tindakan">
                            <div align="center">
                                <c:if test="${line.buktiPenerimaan.namaFizikal == null}">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                         onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.idNotis}','${line_rowNum-1}');return false;" title="Muat Naik Dokumen">
                                    /
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                         onmouseover="this.style.cursor='pointer';" onclick="scan('${line.idNotis}','${line_rowNum-1}');return false;" title="Imbas Dokumen">
                                    <div id="viewReport${line_rowNum-1}">
                                        /<img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                              onclick="doViewReport2('${line.idNotis}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"></div>
                                    </c:if>
                                    <c:if test="${line.buktiPenerimaan.namaFizikal != null}">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                         onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.idNotis}','${line_rowNum-1}');return false;" title="Muat Naik Dokumen">
                                    /
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                         onmouseover="this.style.cursor='pointer';" onclick="scan('${line.idNotis}','${line_rowNum-1}');return false;" title="Imbas Dokumen">
                                    /
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                         onclick="doViewReport('${line.buktiPenerimaan.idDokumen}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen">
                                </c:if>
                            </div>
                        </display:column>
                    </display:table>
                </div>
                <div>
                    <p align="right">
                        <%--test jappp, nnt bkk balik--%>
                        <%--<c:if test="${actionBean.showBtn eq true}">
                            <s:button class="btn" name="genReport" value="Warta" onclick="showReport();"/>
                        </c:if>--%>
                        <c:if test="${actionBean.negori eq true}">
                            <c:if test="${actionBean.button ne true}">
                                <s:button class="btn" name="simpan" value="Simpan" onclick="if(validate())doSubmit(this.form, this.name,'page_div')"/>
                            </c:if>
                        </c:if>
                        <c:if test="${actionBean.negori eq false}">
                            <c:if test="${actionBean.button ne true}">
                                <s:button class="btn" name="simpan" value="Simpan" onclick="if(validate2())doSubmit(this.form, this.name,'page_div')"/>
                            </c:if>
                        </c:if>

                    </p>
                </div>
                <br>
            </fieldset>
            <c:if test="${fn:length(actionBean.listNotisOld)>0}">
                <fieldset class="aras1">
                    <legend>
                        Sejarah Notis Penghantaran
                    </legend>
                    <div class="content" align="center">

                        <display:table class="tablecloth" name="${actionBean.listNotisOld}" cellpadding="0" cellspacing="0" requestURI="/lelong/kemasukkan_rekod" id="line">
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Nama" style="text-transform:uppercase;">
                                ${line.hakmilikPihakBerkepentingan.pihak.nama}<br>
                                (
                                <c:if test="${line.pihak ne null}">
                                    <c:if test="${line.pihak.jenis.kod ne 'PG'}">
                                        Penggadai
                                    </c:if>
                                    <c:if test="${line.pihak.jenis.kod eq 'PG'}">
                                        ${line.hakmilikPihakBerkepentingan.jenis.nama}
                                    </c:if>
                                </c:if>
                                <c:if test="${line.pihak eq null}">
                                    ${line.hakmilikPihakBerkepentingan.jenis.nama}
                                </c:if>)
                            </display:column>
                            <display:column title="Nama Penghantar Notis" style="text-transform:uppercase;">
                                ${line.penghantarNotis.nama}<br>
                                No.K/P:(${line.penghantarNotis.noPengenalan})
                            </display:column>
                            <display:column title="Status Penyampaian" property="kodStatusTerima.nama" style="text-transform:uppercase;"/>
                            <display:column title="Cara Penghantaran" property="kodCaraPenghantaran.nama" style="text-transform:uppercase;"/>
                            <display:column title="Tarikh">
                                <p>
                                    H : <fmt:formatDate value="${line.tarikhHantar}" pattern="dd/MM/yyyy"/>
                                </p>
                                <p>
                                    T : <fmt:formatDate value="${line.tarikhTerima}" pattern="dd/MM/yyyy"/>
                                </p>
                            </display:column>
                            <display:column  title="Catatan" property="catatanPenerimaan" style="text-transform:uppercase;"/>

                            <display:column title="Tindakan">
                                <c:if test="${line.buktiPenerimaan.idDokumen eq null}">
                                    -
                                </c:if>
                                <c:if test="${line.buktiPenerimaan.idDokumen ne null}">
                                    <p align="center">
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                             onclick="doViewReport('${line.buktiPenerimaan.idDokumen}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                    </p>
                                </c:if>
                            </display:column>
                        </display:table>
                    </div>
                </fieldset>
            </c:if>
        </div>
    </c:if>
</s:form>
