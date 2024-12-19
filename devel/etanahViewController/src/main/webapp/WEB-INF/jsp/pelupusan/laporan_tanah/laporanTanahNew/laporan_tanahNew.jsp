<%--
    Document   : laporan_tanahV2
    Created on : Feb 20, 2012, 11:44 AM
    Author     : Shazwan
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<%-- carian hakmilik start--%>

<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<%-- carian hakmilik end--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function reloadPT(val) {
//        alert("val" + val);
        doBlockUI();
        var url = '${pageContext.request.contextPath}/common/laporan/tanah/laporantanahNewActionBean?reloadMain&idMohonHakmilik=' + val;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }
    function latarBelakangTanah(idMohonHakmilik, idLaporTanah) {
        window.open("${pageContext.request.contextPath}/common/laporan/tanah/laporantanahNewActionBean?latarBelakang&idMohonHakmilik=" + idMohonHakmilik + '&idLaporTanah=' + idLaporTanah, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
    }

    function keadaanTanahNew(idMohonHakmilik, idLaporTanah) {
        window.open("${pageContext.request.contextPath}/common/laporan/tanah/laporantanahNewActionBean?keadaanTanah&idMohonHakmilik=" + idMohonHakmilik + '&idLaporTanah=' + idLaporTanah, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
    }
    function lotSempadan2(idMohonHakmilik, idLaporTanah) {
        window.open("${pageContext.request.contextPath}/common/laporan/tanah/laporantanahNewActionBean?lotSempadan&idMohonHakmilik=" + idMohonHakmilik + '&idLaporTanah=' + idLaporTanah, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
    }

    function sempadanTanah(idMohonHakmilik, idLaporTanah) {
        window.open("${pageContext.request.contextPath}/common/laporan/tanah/laporantanahNewActionBean?sempadan&idMohonHakmilik=" + idMohonHakmilik + '&idLaporTanah=' + idLaporTanah, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
    }
    function kawasan(idMohonHakmilik, idLaporTanah) {
        window.open("${pageContext.request.contextPath}/common/laporan/tanah/laporantanahNewActionBean?kawasanPopup&idMohonHakmilik=" + idMohonHakmilik + '&idLaporTanah=' + idLaporTanah, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
    }
    function syor(idMohonHakmilik, idLaporTanah) {
        window.open("${pageContext.request.contextPath}/common/laporan/tanah/laporantanahNewActionBean?syorPopap&idMohonHakmilik=" + idMohonHakmilik + '&idLaporTanah=' + idLaporTanah, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
    }
    function kemaskiniUlasan(idMohonHakmilik, idLaporTanah, idLaporUlasan) {
        window.open("${pageContext.request.contextPath}/common/laporan/tanah/laporantanahNewActionBean?kemaskiniUlasanPopup&idMohonHakmilik=" + idMohonHakmilik + '&idLaporTanah=' + idLaporTanah + '&idLaporUlasan=' + idLaporUlasan, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
    }
    function tambahUlasan(idMohonHakmilik, idLaporTanah) {
        window.open("${pageContext.request.contextPath}/common/laporan/tanah/laporantanahNewActionBean?ulasanPopap&idMohonHakmilik=" + idMohonHakmilik + '&idLaporTanah=' + idLaporTanah, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
    }

    function perihal(idMohonHakmilik, idLaporTanah) {
//        alert(idLaporTanah);
        window.open("${pageContext.request.contextPath}/common/laporan/tanah/laporantanahNewActionBean?perihalTanah&idMohonHakmilik=" + idMohonHakmilik + '&idLaporTanah=' + idLaporTanah, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
    }

    function deleteUlas(idMohonHakmilik, idLaporTanah, idLaporUlasan)
    {
        if (confirm('Adakah anda pasti??')) {
            var url = '${pageContext.request.contextPath}/common/laporan/tanah/laporantanahNewActionBean?deleteUlasan&idMohonHakmilik=' + idMohonHakmilik + '&idLaporTanah=' + idLaporTanah + '&idLaporUlasan=' + idLaporUlasan;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                        self.opener.refreshPageTanahRizab();
                    }, 'html');
        }
    }

    function removeKemaskiniFail(idPermohonan, idFail)
    {
        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/common/laporan/tanah/laporantanahNewActionBean?deleteNoFail&idPermohonan='
                    + idPermohonan + '&idMohonManual=' + idFail;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                        self.opener.refreshPageTanahRizab();
                    }, 'html');
        }
    }
    function doSetDokumenHakmilik() {
        var idDokumen = document.getElementById("hmImej").options[document.getElementById("hmImej").selectedIndex].value;
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function removeKemaskiniFailUrusan(idFail)
    {
        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/common/laporan/tanah/laporantanahNewActionBean?deleteNoFailUrusan&idMohonManual='
                    + idFail;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                        self.opener.refreshPageTanahRizab();
                    }, 'html');
        }
    }

</script>
<s:form beanclass="etanah.view.stripes.common.laporan.tanah.laporantanahNewActionBean" name="form">

    <s:errors/>
    <s:messages/>
    <s:hidden name="idHakmilik" id="idHakmilik"/>
    <s:hidden name="noPtSementara" id="noPtSementara"/>
    <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                <tfoot>
                    <tr>    
                        <td colspan="4"><center><b>Senarai Hakmilik Terlibat</b></center></td>  
                </tr>
                </tfoot>
            </table>
            <br>
            <!--<legend>Senarai Hakmilik Terlibat</legend>-->
            <div align="center">
                <s:select name="idMohonHakmilik" onchange="reloadPT(this.value);" id="idMohonHakmilik">
                    <s:option value="">-- Sila Pilih --</s:option>
                    <c:forEach items="${actionBean.hakmilikPermohonanList2}" var="item" varStatus="line">                         
                        <c:if test="${item.hakmilik.idHakmilik ne null}">                              
                            <s:option value="${item.id}" style="width:400px">
                                ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                            </s:option>
                        </c:if>
                        <c:if test="${item.hakmilik.idHakmilik eq null}">
                            <s:option value="${item.id}" style="width:400px">
                                ${item.lot.nama} - ${item.noLot}
                            </s:option>
                        </c:if>
                    </c:forEach>
                </s:select>


            </div>
            <div class="content" align="center">
                <%--<p align="center"><label></label>--%>
                <c:if test="${item.hakmilik.idHakmilik ne null}">   
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList1}" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/maklumat_tambahan">
                        <display:column title="No">${line_rowNum}</display:column>
                        <display:column property="hakmilik.kodHakmilik.nama" title="Jenis Milik" />
                        <display:column property="hakmilik.cawangan.name" title="Cawangan" />
                        <display:column property="hakmilik.daerah.nama" title="Daerah" />
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                        <display:column property="hakmilik.lot.nama" title="Kod Lot"/>
                        <display:column title="No. PT/Lot" value = "${(actionBean.noLotList[line_rowNum-1])}"/>                  
                        <%--<display:column title="No. PT/Lot" value = "${actionBean.noLot}"/>--%>
                        <display:column property="hakmilik.idHakmilik" title="No. H/M"/>
                        <display:column title="Kawasan PBT">
                            <c:if test="${line.hakmilik.pbt ne null}">${line.hakmilik.pbt.nama}</c:if>
                            <c:if test="${line.hakmilik.pbt eq null}">Luar Kawasan PBT</c:if>
                        </display:column>

                    </display:table>
                </c:if>
                <c:if test="${item.hakmilik.idHakmilik eq null}">
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList1}" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/maklumat_tambahan">
                        <display:column title="No">${line_rowNum}</display:column>
                        <display:column property="kodHakmilik.nama" title="Jenis Milik" />
                        <display:column property="cawangan.name" title="Cawangan" />
                        <display:column property="lot.nama" title="Kod Lot"/>
                        <display:column title="No. PT/Lot" value = "${(actionBean.noLotList[line_rowNum-1])}"/>                  
                        <%--<display:column title="No. PT/Lot" value = "${actionBean.noLot}"/>--%>
                        <display:column property="hakmilik.idHakmilik" title="No. H/M"/>
                        <display:column title="Kawasan PBT">
                            <c:if test="${line.hakmilik.pbt ne null}">${line.hakmilik.pbt.nama}</c:if>
                            <c:if test="${line.hakmilik.pbt eq null}">Luar Kawasan PBT</c:if>
                        </display:column>
                    </display:table>
                </c:if>
                <br>
            </div>
        </fieldset> 
        <c:if test="${actionBean.hakmilikPermohonan ne null}">
            <fieldset class="aras1">
                <div class="content" align="center">
                    <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                        <tfoot>
                            <tr>    
                                <td colspan="4"><center><b>Perihal Tanah</b></center></td>  
                        </tr>
                        </tfoot>
                    </table>
                    <table  style="width:100%;" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                        <br>

                        <tr>
                            <th colspan="100" width ="100">Status Tanah </th>
                            <td width="1000">${actionBean.hakmilikPermohonan.kodMilik.nama}</td>                 
                        </tr>
                        <tr>
                            <th colspan="100" width ="100">Jenis Rizab</th>
                            <td width="1000">${actionBean.tanahrizabpermohonan1.rizab.nama}</td>   

                        </tr>
                        <tr>
                            <th colspan="100" width ="100">No. Warta </th>
                            <td width="1000">${actionBean.tanahrizabpermohonan1.noWarta}</td>   

                        </tr>
                        <tr>
                            <th colspan="100" width ="100">Jenis tanah </th>
                            <td width="1000">${actionBean.permohonanLaporanPelan.kodTanah.nama}</td>   

                        </tr>
                        <tr>
                            <th colspan="100" width ="100">Kawasan Parlimen </th>
                            <td width="1000">${actionBean.hakmilikPermohonan.kodKawasanParlimen.nama}</td>
                        </tr>
                        <tr>
                            <th colspan="100" width ="100">DUN </th>
                            <td width="1000">${actionBean.hakmilikPermohonan.kodDUN.nama}</td>
                        </tr>
                        <tr>
                            <th colspan="100" width ="150">Kedudukan Tanah </th>
                            <td width="1000">${actionBean.hakmilikPermohonan.lokasi}</td>
                        </tr>
                    </table>
                    <s:button name="perihalTanah" value="Perihal Tanah" class="longbtn"  onclick="perihal('${actionBean.hakmilikPermohonan.id}','${actionBean.laporanTanah.idLaporan}');return false;"/>
                </div>
        </div>
    </fieldset>


    <fieldset class="aras1" id="locate">

        <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
            <tfoot>
                <tr>    
                    <td colspan="4"><center><b>Senarai Permohonan Terdahulu</b></center></td>  
            </tr>
            </tfoot>
        </table>
        <div class="content" align="center">                                        
            <display:table class="tablecloth" name="${actionBean.permohonanLamaListUrusan}" cellpadding="0" cellspacing="0" id="line"
                           requestURI="${pageContext.request.contextPath}/laporan_pelukisPelan">
                <display:column title="No">${line_rowNum}</display:column>
                <display:column property="idPermohonan" title="Id Permohonan" />   
                <display:column property="kodUrusan.nama" title="Urusan" />   
                <display:column property="status" title="Status" />   
                <display:column property="sebab" title="sebab" />   
                <display:column property="keputusanOleh.nama" title="Keputusan Oleh" />  
                <display:column property="tarikhKeputusan" title="Tarikh Keputusan" /> 
                <display:column title="Hapus">
                    <div align="center">
                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                             id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';" onclick="removeKemaskiniFailUrusan('${line.idPermohonan}');" />
                    </div>
                </display:column>                  
            </display:table>
            <br/>
            <display:table class="tablecloth" name="${actionBean.mohonManualList}" cellpadding="0" cellspacing="0" id="line"
                           requestURI="${pageContext.request.contextPath}/laporan_pelukisPelan">
                <display:column title="No">${line_rowNum}</display:column>
                <display:column title="ID Fail">
                    ${line.noFail}
                </display:column>     
                <display:column title="Hapus">
                    <div align="center">
                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                             id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';" onclick="removeKemaskiniFail('${line.idPermohonan.idPermohonan}', '${line.idMohonManual}');" />
                    </div>
                </display:column>                 
            </display:table>
            <br>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Tambah Permohonan Terdahulu</legend>
                    <div class="content" align="center">
                        <table class="tablecloth" border="0">
                            <tr>
                                <td>No. Fail :</td>
                                <td><s:text name="noFailBaru" id="noFail"/></td>
                            </tr>
                            <tr>
                                <td align="right" colspan="2">
                                    <s:button name="simpanFailPermohonan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>

                                    <s:reset name="IsiSemula" value="Isi Semula" class="btn" onclick=""/>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <br/>

                </fieldset>
            </div>                
            <%--</c:if>--%>
            <br/>

            <br>
        </div>
    </fieldset>

    <fieldset class="aras1" id="locate">
        <div class="content" align="center">
            <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                <tfoot>
                    <tr>    
                        <td colspan="4"><center><b>Latar Belakang Tanah</b></center></td>  
                </tr>
                </tfoot>
            </table>
            <br>
            <display:table class="tablecloth" name="${actionBean.senaraiPermohonanLaporanPohon}" cellpadding="0" cellspacing="0" id="line"
                           requestURI="${pageContext.request.contextPath}/laporan_pelukisPelan">
                <display:column title="No">${line_rowNum}</display:column>
                <display:column property="jenisDipohon" title="Jenis Tanah" />   
                <display:column property="noRujukan" title="No Rujukan" />   
                <display:column property="kegunaan" title="Kegunaan" />                     
            </display:table>
        </div>
        <center>
            <s:button name="latarBelakang" value="Latar Belakang" class="longbtn"  onclick="latarBelakangTanah('${actionBean.hakmilikPermohonan.id}', '${actionBean.laporanTanah1.idLaporan}');return false;"/>
        </center>
    </fieldset>

    <br>


    <fieldset class="aras1"> 
        <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
            <tfoot>
                <tr>    
                    <td colspan="4"><center><b>Bersempadan</b></center></td>  
            </tr>
            </tfoot>
        </table>
        <div class="content" align="center" id="vertical-2">
            <table  style="width:100%;" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">

                <td width="1000" height ="2">
                <center>
                    <display:table style="width:100%;" class="tablecloth" name="${actionBean.laporanTanah1}"
                                   cellpadding="0" cellspacing="0" id="line">
                        <display:column title="Jalan Raya">
                            <center> Nama  : ${line.namaSempadanJalanraya} <br>
                                Jarak : ${line.jarakSempadanJalanraya} KM</center>
                            </display:column>
                            <display:column title="Keretapi">
                            <center> Nama  : ${line.namaSempadanKeretapi} <br>
                                Jarak : ${line.jarakSempadanKeretapi} KM </center>
                            </display:column>
                            <display:column title="Laut">
                            <center>  Nama  : ${line.namaSempadanLaut} <br>
                                Jarak : ${line.jarakSempadanLaut} KM </center>
                            </display:column>
                            <display:column title="Sungai">
                            <center> Nama  : ${line.namaSempadanSungai} <br>
                                Jarak : ${line.jarakSempadanSungai} KM </center>
                            </display:column>
                            <display:column title="Lain-lain">
                            <center> Nama  : ${line.namaSempadanlain} <br>
                                Jarak : ${line.jarakSempadanLain} KM </center>
                            </display:column>

                    </display:table>
                </center>

                <div class="content" align="center" id="vertical-2">
                    Jalan Masuk :<c:if test="${line.adaJalanMasuk eq 'T'}">
                        Tiada
                    </c:if>
                    <c:if test="${line.adaJalanMasuk eq 'Y'}">
                        Ada
                    </c:if>
                    <br>
                    Jenis Jalan : ${line.jenisJalan} <br>
                    Catatan : ${line.catatanJalanMasuk}
                </div>

                </td>
            </table>
        </div>
        <center>
            <s:button name="sempadan" value="Sempadan" class="longbtn"  onclick="sempadanTanah('${actionBean.hakmilikPermohonan.id}', '${actionBean.laporanTanah1.idLaporan}');return false;"/>
        </center>
    </fieldset>

    <fieldset class="aras1">
        <div class="content" align="center">
            <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                <tfoot>
                    <tr>    
                        <td colspan="4"><center><b>Keadaan tanah</b></center></td>  
                </tr>
                </tfoot>
            </table>
            <table  style="width:100%;" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                <br>

                <tr>
                    <th colspan="100" width ="100">Kecerunan Tanah </th>
                    <td width="1000">${actionBean.laporanTanah1.kecerunanTanah.nama}</td>                 
                </tr>
                <tr>
                    <th colspan="100" width ="100">Klasifikasi Tanah</th>
                    <td width="1000">${actionBean.laporanTanah1.strukturTanah.nama}</td>   

                </tr>
                <tr>
                    <th colspan="100" width ="100">Jika Lain - lain </th>
                    <td width="1000">${actionBean.laporanTanah1.strukturTanahLain}</td>   

                </tr>
                <tr>
                    <th colspan="100" width ="100">Keadaan Tanah</th>
                    <td width="1000">${actionBean.laporanTanah1.kodKeadaanTanah.nama}</td>   

                </tr>
                <tr>
                    <th colspan="100" width ="100"> Tanah Dipohon Bertumpu</th>
                    <td width="1000">${actionBean.laporanTanah1.tanahBertumpu}</td>
                    <td width="1000">Pada : ${actionBean.laporanTanah1.keteranganTanahBertumpu}</td>
                </tr>

            </table>

        </div>

        <div class="content" align="center" id="vertical-2">
            Dilintasi Oleh
            <br>
            <table  style="width:100%;" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">

                <td width="1000" height ="2">
                    <display:table style="width:100%;" class="tablecloth" name="${actionBean.laporanTanah1}"
                                   cellpadding="0" cellspacing="0" id="line">
                        <display:column title="Talian elektrik">
                        <center><c:if test="${line.dilintasTiangElektrik eq null}">Tiada</c:if>
                            <c:if test="${line.dilintasTiangElektrik eq 'Y'}">Ya</c:if></center>
                        </display:column>

                    <display:column title="Talian Telefon">
                        <center><c:if test="${line.dilintasTiangTelefon eq null}">Tiada</c:if>
                            <c:if test="${line.dilintasTiangTelefon eq 'Y'}">Ya</c:if></center>
                        </display:column>

                    <display:column title="Laluan Gas" >
                        <center><c:if test="${line.dilintasLaluanGas eq null}">Tiada</c:if>
                            <c:if test="${line.dilintasLaluanGas eq 'Y'}">Ya</c:if></center>
                        </display:column>

                    <display:column title="Paip Air">
                        <center><c:if test="${line.dilintasPaip eq null}">Tiada</c:if>
                            <c:if test="${line.dilintasPaip eq 'Y'}">Ya</c:if></center>
                        </display:column>

                    <display:column title="Tali air"> 
                        <center><c:if test="${line.dilintasTaliar eq null}">Tiada</c:if>
                            <c:if test="${line.dilintasTaliar eq 'Y'}">Ya</c:if></center>
                        </display:column>

                    <display:column title="Sungai">
                        <center><c:if test="${line.dilintasSungai eq null}">Tiada</c:if>
                            <c:if test="${line.dilintasSungai eq 'Y'}">Ya</c:if></center>
                        </display:column>

                    <display:column title="Parit" >
                        <center><c:if test="${line.dilintasParit eq null}">Tiada</c:if>
                            <c:if test="${line.dilintasParit eq 'Y'}">Ya</c:if></center>
                        </display:column>

                    <display:column title="Lain-lain">
                        <center><c:if test="${actionBean.laporanTanah.ulasan  eq null}">Tiada</c:if>
                            <c:if test="${actionBean.laporanTanah.ulasan eq 'Y'}">Ya</c:if></center>
                        </display:column>

                </display:table>
                <div class="content" align="center" id="vertical-2">

                </div>

                </td>
            </table>
        </div>
        <center>
            <s:button name="keadaanTanah" value="Keadaan Tanah" class="longbtn"  onclick="keadaanTanahNew('${actionBean.hakmilikPermohonan.id}', '${actionBean.laporanTanah1.idLaporan}');return false;"/>
        </center>
    </fieldset> 

    <fieldset class="aras1" id="locate">
        <div class="content" align="center">
            <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                <tfoot>
                    <tr>    
                        <td colspan="4"><center><b>Perihal Lot-lot Bersempadan</b></center></td>  
                </tr>
                </tfoot>
            </table>
            <br>
            <display:table class="tablecloth" name="${actionBean.laporanTanahSempadanList}" cellpadding="0" cellspacing="0" id="line"
                           requestURI="${pageContext.request.contextPath}/laporan_pelukisPelan">
                <center>
                    <display:column title="">
                        <center>
                            <c:if test="${line.jenisSempadan eq 'U'}">
                                Utara
                            </c:if>
                            <c:if test="${line.jenisSempadan eq 'T'}">
                                Timur
                            </c:if>
                            <c:if test="${line.jenisSempadan eq 'B'}">
                                Barat
                            </c:if>
                            <c:if test="${line.jenisSempadan eq 'S'}">
                                Selatan
                            </c:if>
                        </center>
                    </display:column>
                    <display:column property="hakmilik.idHakmilik" title="Id Hakmilik" class="guna" />
                    <display:column property="hakmilik.noLot" title="No Lot/No Pt" class="guna" />
                    <display:column property="guna" title="Kegunaan Tanah" class="guna" />
                    <display:column property="keadaanTanah" title="Kegunaan Tanah" class="KegunaanTanah" />
                    <display:column title="jarak Dari tanah Dipohon">
                        ${line.jarak} - ${line.jarakUom.nama}
                    </display:column> 
                    <display:column property="catatan" title="catatan" class="catatan" />
                    <display:column property="milikKerajaan" title="Milik Kerajaan" class="milikKerajaan" />
                    <display:column title="Imej">
                        <s:select name="hmImej" style="width:300px;" id="hmImej" onchange="doSetDokumenHakmilik();">
                            <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                            <c:if test="${line.jenisSempadan eq 'U'}">
                                utara <s:options-collection collection="${actionBean.hakmilikImejLaporanListU}" label="catatan" value="dokumen.idDokumen"/> 
                            </c:if>
                            <c:if test="${line.jenisSempadan eq 'B'}">
                                barat <s:options-collection collection="${actionBean.hakmilikImejLaporanListB}" label="catatan" value="dokumen.idDokumen"/> 
                            </c:if>
                            <c:if test="${line.jenisSempadan eq 'S'}">
                                selatan <s:options-collection collection="${actionBean.hakmilikImejLaporanListS}" label="catatan" value="dokumen.idDokumen"/> 
                            </c:if>
                            <c:if test="${line.jenisSempadan eq 'T'}">
                                timur <s:options-collection collection="${actionBean.hakmilikImejLaporanListT}" label="catatan" value="dokumen.idDokumen"/>
                            </c:if>

                        </s:select>
                    </display:column> 
                </center>
            </display:table>
        </div>
        <center>
            <s:button name="lotSempadan" value="Lot Bersempadan" class="longbtn"  onclick="lotSempadan2('${actionBean.hakmilikPermohonan.id}', '${actionBean.laporanTanah1.idLaporan}');return false;"/>
        </center>
    </fieldset>

    <fieldset class="aras1" id="locate">
        <div class="content" align="center">
            <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                <tfoot>
                    <tr>    
                        <td colspan="4"><center><b>Dalam Kawasan</b></center></td>  
                </tr>
                </tfoot>
            </table>
            <br>

            <c:if test="${!empty actionBean.senaraiLaporanKawasan}">
                <display:table class="tablecloth" name="${actionBean.senaraiLaporanKawasan}" cellpadding="0" cellspacing="0" id="line">
                    <center>
                        <display:column property="kodRizab.nama" title="Jenis Rizab" class="rizab" />
                        <display:column property="noWarta" title="No Warta" class="noWarta" />
                        <display:column property="tarikhWarta" title="Tarikh Warta" class="tarikhWarta" />
                        <display:column property="noPelanWarta" title="No Pelan Warta" class="noPelanWarta" /> 
                        <c:if test="${line.catatan ne null}">
                            <display:column property="catatan" title="catatan" class="catatan" />
                        </c:if>

                    </center>
                </display:table>
            </c:if>
        </div>
        <center>
            <s:button name="dalamKwsn" value="Dalam Sempadan" class="longbtn"  onclick="kawasan('${actionBean.hakmilikPermohonan.id}', '${actionBean.laporanTanah1.idLaporan}');return false;"/>
        </center>
    </fieldset>

    <fieldset class="aras1">
        <div class="content" align="center">
            <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                <tfoot>
                    <tr>    
                        <td colspan="4"><center><b>Syor / Ulasan</b></center></td>  
                </tr>
                </tfoot>
            </table>
            <table  style="width:100%;" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                <br>

                <tr>
                    <th colspan="100" width ="100">Syor Oleh</th>
                    <td width="1000">${actionBean.mohonFasa.infoAudit.dikemaskiniOleh.nama}</td>                 
                </tr>
                <tr>
                    <th colspan="100" width ="100">Waktu Syor</th>
                    <td width="1000">${actionBean.mohonFasa.infoAudit.tarikhKemaskini}</td>   

                </tr>
                <tr>
                    <th colspan="100" width ="100">Syor </th>
                    <td width="1000">${actionBean.mohonFasa.keputusan.nama}</td>   

                </tr>
                <c:if test="${actionBean.mohonFasa.ulasan ne null}">
                    <tr>
                        <th colspan="100" width ="100">Sebab </th>
                        <td width="1000">${actionBean.mohonFasa.ulasan}</td>   
                    </tr>
                </c:if>
            </table>
            <center>
                <s:button name="syotPpt" value="Syor" class="longbtn"  onclick="syor('${actionBean.hakmilikPermohonan.id}', '${actionBean.laporanTanah1.idLaporan}');return false;"/>
            </center>
            <br>
            Ulasan
            <display:table class="tablecloth" name="${actionBean.senaraiLaporanKandungan1}" cellpadding="0" cellspacing="0" id="line">
                <center>
                    <display:column property="ulasan" title="Ulasan" class="ulasan" />
                    <display:column title="kemaskini">
                        <div align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';" onclick="kemaskiniUlasan('${actionBean.hakmilikPermohonan.id}', '${actionBean.laporanTanah1.idLaporan}', '${line.idLaporUlas}');" />
                        </div>
                    </display:column>
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';" onclick="deleteUlas('${actionBean.hakmilikPermohonan.id}', '${actionBean.laporanTanah1.idLaporan}', '${line.idLaporUlas}');" />
                        </div>
                    </display:column>
                </center>
            </display:table>
        </div>
        <center>
            <s:button name="ulasan" value="Tambah Syor" class="longbtn"  onclick="tambahUlasan('${actionBean.hakmilikPermohonan.id}', '${actionBean.laporanTanah1.idLaporan}');return false;"/>
        </center>
    </fieldset>
</c:if>


</s:form>