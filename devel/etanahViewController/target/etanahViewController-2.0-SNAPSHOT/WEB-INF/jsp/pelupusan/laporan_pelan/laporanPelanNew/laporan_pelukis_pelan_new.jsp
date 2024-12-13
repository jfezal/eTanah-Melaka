<%--
    Document   : dev_laporan_pelukis_pelan
    Created on : Nov 9, 2010, 11:45:34 AM
    Author     : nursyazwani
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:150px;
        margin-right:0.5em;
        text-align:left;
        width:15em;
        vertical-align:top;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:40em;
    }
</style>
<script type="text/javascript">
    function removeTanahRizab(idMohonlaporKws)
    {
        if (confirm('Adakah anda pasti??')) {
            var url = '${pageContext.request.contextPath}/common/laporan/pelan/java/LaporanPelukisPelanNewActionBean?deleteTanahRizab&idMohonlaporKws='
                    + idMohonlaporKws;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                        self.opener.refreshPageTanahRizab();
                    }, 'html');
        }
    }

    function ReplaceAll(Source, stringToFind, stringToReplace) {
        var temp = Source;
        var index = temp.indexOf(stringToFind);
        while (index != -1) {
            temp = temp.replace(stringToFind, stringToReplace);
            index = temp.indexOf(stringToFind);

        }
        return temp;
    }

    function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {
        alert(" user id:" + strUserID + " nama: " + strNama + " jawatan:" + strJawatan + " pmohonan id: " + strIDPermohonan + " stage id: " + strIDStage);
        strNama = ReplaceAll(strNama, " ", "_");
        strJawatan = ReplaceAll(strJawatan, " ", "_");
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " + strJawatan + " " + strIDPermohonan + " " + strIDStage);
    }


    function removeTanahMilik(id)
    {
        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pembangunan/laporan_pelukisPelan?deleteTanahMilik&id='
                    + id;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }

    function removePermohonanTerdahulu(idPermohonan)
    {
        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pembangunan/laporan_pelukisPelan?deletePermohonanTerdahulu&idPermohonan='
                    + idPermohonan;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }

    function removeKemaskiniFail(idPermohonan, idFail)
    {
        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/common/laporan/pelan/java/LaporanPelukisPelanNewActionBean?deleteNoFail&idPermohonan='
                    + idPermohonan + '&idMohonManual=' + idFail;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                        self.opener.refreshPageTanahRizab();
                    }, 'html');
        }
    }

    function removeKemaskiniFailPermohonan(idPermohonan)
    {
        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/common/laporan/pelan/java/LaporanPelukisPelanNewActionBean?deleteNoFailPermohonan&idPermohonan='
                    + idPermohonan;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                        self.opener.refreshPageTanahRizab();
                    }, 'html');
        }
    }
    function popupKemaskiniFail(idPermohonan, idFail) {
        var url = '${pageContext.request.contextPath}/pembangunan/laporan_pelukisPelan?kemaskiniNoFail&idPermohonan='
                + idPermohonan + '&idMohonManual=' + idFail;
        window.open(url, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function tambahTanahRizab(idMohonHakmilik) {
//        alert("idMohonHakmilik " + idMohonHakmilik)
        window.open("${pageContext.request.contextPath}/common/laporan/pelan/java/LaporanPelukisPelanNewActionBean?tanahRizabPopupNew&idMohonHakmilik=" + idMohonHakmilik, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }

    function tambahTanahMilik() {
        window.open("${pageContext.request.contextPath}/pembangunan/laporan_pelukisPelan?tanahMilikPopup", "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }

    function tambahPermohonanTerdahulu() {
        window.open("${pageContext.request.contextPath}/pembangunan/laporan_pelukisPelan?permohonanTerdahuluPopup", "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }

    function refreshPageTanahRizab() {
        var url = '${pageContext.request.contextPath}/pembangunan/laporan_pelukisPelan?refreshpage';
        $.get(url,
                function(data) {
                    $('#page_div').html(data);
                }, 'html');
    }

    function popupTanahRizab(h) {
        var url = '${pageContext.request.contextPath}/common/laporan/pelan/java/LaporanPelukisPelanNewActionBean?showEditTanahRizab&idMohonlaporKws=' + h;
        window.open(url, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function popupTambahPermohonanSebelum(h) {
        var url = '${pageContext.request.contextPath}/common/laporan/pelan/java/LaporanPelukisPelanNewActionBean?showTambahPermohonanTerdahuluNew&idPermohonan=' + h;
        window.open(url, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    $(document).ready(function() {

        $('#mT').hide();
        $('#aG').hide();
        $('#tB').hide();
        $('#bK').hide();
        $('#kM').hide();
        $('#jS').hide();

        // Kawasan Parlimen && Dun
        var mhk = '${actionBean.hakmilikPermohonan.kodKawasanParlimen.kod}';
        var dun = '${actionBean.hakmilikPermohonan.kodDUN.kod}';
        //         alert(mhk);
        //         alert(dun);
        if (mhk == 'P134') {
            $('#kodDmT').val(dun);
            $('#mT').show();
            $('#aG').hide();
            $('#tB').hide();
            $('#bK').hide();
            $('#kM').hide();
            $('#jS').hide();
        } else if (mhk == 'P135') {
            $('#kodDaG').val(dun);
            $('#aG').show();
            $('#mT').hide();
            $('#tB').hide();
            $('#bK').hide();
            $('#kM').hide();
            $('#jS').hide();
        } else if (mhk == 'P136') {
            $('#kodDtB').val(dun);
            $('#tB').show();
            $('#aG').hide();
            $('#mT').hide();
            $('#bK').hide();
            $('#kM').hide();
            $('#jS').hide();
        } else if (mhk == 'P137') {
            $('#kodDbK').val(dun);
            $('#bK').show();
            $('#tB').hide();
            $('#aG').hide();
            $('#mT').hide();
            $('#kM').hide();
            $('#jS').hide();
        } else if (mhk == 'P138') {
            $('#kodDkM').val(dun);
            $('#kM').show();
            $('#bK').hide();
            $('#tB').hide();
            $('#aG').hide();
            $('#mT').hide();
            $('#jS').hide();
        } else if (mhk == 'P139') {
            $('#kodDjS').val(dun);
            $('#jS').show();
            $('#kM').hide();
            $('#bK').hide();
            $('#tB').hide();
            $('#aG').hide();
            $('#mT').hide();
        } else {
            $('#jS').hide();
            $('#kM').hide();
            $('#bK').hide();
            $('#tB').hide();
            $('#aG').hide();
            $('#mT').hide();
        }

        $('.hakmilikSebelum').click(function() {
            //                alert($(this).text());
            window.open("${pageContext.request.contextPath}/pembangunan/laporan_pelukisPelan?permohonanSebelumDetail", "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
        });
    });

    function changeHideDun(value) {
        // alert(value);
        $('#kodD').val("");
        if (value == 'P134') {
            $('#mT').show();
            $('#aG').hide();
            $('#tB').hide();
            $('#bK').hide();
            $('#kM').hide();
            $('#jS').hide();
        } else if (value == 'P135') {
            $('#aG').show();
            $('#mT').hide();
            $('#mT').attr("disabled", "disabled");
            $('#tB').hide();
            $('#tB').attr("disabled", "disabled");
            $('#bK').hide();
            $('#bK').attr("disabled", "disabled");
            $('#kM').hide();
            $('#kM').attr("disabled", "disabled");
            $('#jS').hide();
            $('#jS').attr("disabled", "disabled");
        } else if (value == 'P136') {
            $('#tB').show();
            $('#aG').hide();
            $('#mT').hide();
            $('#bK').hide();
            $('#kM').hide();
            $('#jS').hide();
        } else if (value == 'P137') {
            $('#bK').show();
            $('#tB').hide();
            $('#aG').hide();
            $('#mT').hide();
            $('#kM').hide();
            $('#jS').hide();
        } else if (value == 'P138') {
            $('#kM').show();
            $('#bK').hide();
            $('#tB').hide();
            $('#aG').hide();
            $('#mT').hide();
            $('#jS').hide();
        } else if (value == 'P139') {
            $('#jS').show();
            $('#kM').hide();
            $('#bK').hide();
            $('#tB').hide();
            $('#aG').hide();
            $('#mT').hide();
        }
    }
    function dun(value) {
        $('#kodD').val(value);
    }

    function reloadPT(val) {
//        alert("val" + val);
        doBlockUI();
        var url = '${pageContext.request.contextPath}/common/laporan/pelan/java/LaporanPelukisPelanNewActionBean?reload&idMohonHakmilik=' + val;
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
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.common.laporan.pelan.java.LaporanPelukisPelanNewActionBean">

    <s:errors/>
    <s:messages/>
    <s:hidden name="kodD" id="kodD"/>
    <div class="subtitle displaytag">
        <fieldset class="aras1">
            <center>

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
            </center>
        </fieldset>

        <fieldset class="aras1" id="locate">
            <legend>
                Tanah Milik 
            </legend>
            <div class="content" align="center">
                <%--<p align="center"><label></label>--%>
                <c:if test="${item.hakmilik.idHakmilik ne null}">   
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="line"
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
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/maklumat_tambahan">
                        <display:column title="No">${line_rowNum}</display:column>
                        <display:column property="kodHakmilik.nama" title="Jenis Milik" />
                        <display:column property="cawangan.name" title="Cawangan" />
                        <display:column property="lot.nama" title="Kod Lot"/>
                        <display:column title="No. PT/Lot" value = "${(actionBean.noLotList[line_rowNum-1])}"/>                  
                        <%--<display:column title="No. PT/Lot" value = "${actionBean.noLot}"/>--%>
                        <display:column title="No. Hakmilik" >
                            <c:if test="${line.hakmilik.idHakmilik ne null}">
                                ${line.hakmilik.idHakmilik}
                            </c:if>
                            <c:if test="${line.hakmilik.idHakmilik eq null}">
                                Hakmilik Tidak Dapat Dikesan ( TDK )
                            </c:if>

                        </display:column>
                        <display:column title="Kawasan PBT">
                            <c:if test="${line.hakmilik.pbt ne null}">${line.hakmilik.pbt.nama}</c:if>
                            <c:if test="${line.hakmilik.pbt eq null}">Luar Kawasan PBT</c:if>
                        </display:column>
                    </display:table>
                </c:if>
                <br>
            </div>
        </fieldset>
    </div>
    <br/>
    <br/>
    <div class="subtitle displaytag">
        <c:if test="${actionBean.bilHakmilik eq true}">
            <fieldset class="aras1" id="locate">
                <legend>
                    Dalam Kawasan
                </legend>
                <%--<p align="center"><label></label>--%>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.permohonanLaporanKawasanList}" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/laporan_pelukisPelan">
                        <display:column title="No">${line_rowNum}</display:column>
                        <display:column property="permohonan.idPermohonan" title="Id Permohonan" />
                        <display:column property="kodRizab.nama" title="Jenis Rizab" />
                        <display:column property="catatan" title="catatan" />
                        <display:column property="noWarta" title="no Warta" />
                        <display:column property="noPelanWarta" title="no Pelan Warta"/>
                        <display:column property="hakmilikPermohonan.hakmilik.idHakmilik" title="Id Hakmilik"/>
                        <display:column property="tarikhWarta" title="Tarikh Warta" format="{0,date,dd/MM/yyyy}"/>
                        <%-- <display:column property="catatan" title="Catatan"/>--%>
                        <c:if test="${edit}">
                            <display:column title="Kemaskini">
                                <div align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';" onclick="popupTanahRizab('${line.idMohonlaporKws}');"/>
                                </div>
                            </display:column>
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';" onclick="removeTanahRizab('${line.idMohonlaporKws}');" />
                                </div>
                            </display:column>
                        </c:if>
                    </display:table>
                    <c:if test="${edit}">
                        <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahTanahRizab(${actionBean.idMohonHakmilik});"/>&nbsp;
                    </c:if>
                    <br>
                </div>
            </fieldset>
        </c:if>
    </div>

    <br/>
    <br/>
    <div class="subtitle displaytag">
        <c:if test="${actionBean.bilHakmilik eq true}">
            <fieldset class="aras1" id="locate">
                <legend>
                    Permohonan Terdahulu ( File )
                </legend>

                <div class="content" align="center">                                        
                    <display:table class="tablecloth" name="${actionBean.mohonManualList}" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/laporan_pelukisPelan">
                        <display:column title="No">${line_rowNum}</display:column>
                        <display:column title="ID Fail">
                            ${line.noFail}
                        </display:column>     

                        <c:if test="${edit}">
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';" onclick="removeKemaskiniFail('${line.idPermohonan.idPermohonan}', '${line.idMohonManual}');" />
                                </div>
                            </display:column>
                        </c:if>                   
                    </display:table>
                    <br/>

                    <div class="subtitle">
                        <fieldset class="aras1">
                            <legend>Permohonan Terdahulu</legend>
                            <div class="content" align="center">
                                <table class="tablecloth" border="0">
                                    <tr>
                                        <td>No. Fail :</td>
                                        <td><s:text name="noFailBaru" id="noFail"/></td>
                                    </tr>
                                    <tr>
                                        <td align="right" colspan="2">
                                            <s:button name="simpanFail" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>

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
        </c:if>
        <%--<c:if test="${actionBean.bilHakmilik eq true}">
            <fieldset class="aras1" id="locate">
                <legend>
                    Permohonan Terdahulu ( Permohonan Etanah )
                </legend>

                <div class="content" align="center">                                        
                    <display:table class="tablecloth" name="${actionBean.senaraiMohonManual}" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/laporan_pelukisPelan">
                        <display:column title="No">${line_rowNum}</display:column>
                        <display:column property="permohonan.idPermohonan" title="Id Permohonan" />    
                        <display:column property="permohonan.kodUrusan.nama" title="Nama urusan" />    
                        <display:column property="permohonan.keputusan.nama" title="Keputusan" />    
                        <display:column property="permohonan.tarikhKeputusan" title="Tarikh Keputusan" />    

                        <c:if test="${edit}">
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';" onclick="removeKemaskiniFailPermohonan('${line.idPermohonan}');" />
                                </div>
                            </display:column>
                        </c:if>                   
                    </display:table>
                    <br/>
                    <c:if test="${edit}">
                        <s:button class="btn" value="Tambah" name="new" id="new" onclick="popupTambahPermohonanSebelum('${actionBean.idPermohonan}');"/>&nbsp;
                    </c:if>
                    </c:if>
                    <br/>

                    <br>
                </div>
            </fieldset>
        </c:if>--%>
    </div>
    <br/>
    <br/>
    <div class="content" align="center">
        <c:if test="${actionBean.bilHakmilik eq true}">
            <c:if test="${edit}">
                <table width="48%" border="0">
                    <tr>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">No Lembaran Piawai </font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font></td>
                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;"><s:text name="noLitho" size="20" id="noLitho" class="normal_text"/></font></td>
                    </tr>
                    <tr>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Luas </font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font></td>
                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                <s:text name="luas" size="20" id="luas" class="normal_text"/>
                                <s:select name="kodUnitLuas" value="${actionBean.kodUOM}" id="kodUnitLuas">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodUOMByLuas}" label="nama" value="kod"/>
                                </s:select>
                            </font></td>
                    </tr>
                    <tr>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Status tanah </font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font></td>
                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                <s:select name="kodP" style="width:150px" id="kodP" onchange="javaScript:changeHide(this.value)">
                                    <c:choose>
                                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                                            <s:option value="0">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodPemilikanLPSP}" label="nama" value="kod"/>
                                        </c:when>
                                        <c:otherwise>
                                            <s:option value="0">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodPemilikan}" label="nama" value="kod"/>
                                        </c:otherwise>
                                    </c:choose>
                                </s:select>
                            </font></td>
                    </tr>
                    <tr>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Jenis Tanah </font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font></td>
                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                <s:select name="kodTanah" style="width:150px" value="" id="kodTanah" onchange="javaScript:showjenistanahlain(this.value)">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodTanah}" label="nama" value="kod"/>
                                </s:select>
                            </font></td>
                    </tr>
                    <tr>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Di tanda untuk projek kerajaan</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font></td>
                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;"><s:text name="projekKerajaan" size="20" id="projekkerajaan" class="normal_text"/></font></td>
                    </tr>

                    <c:if test="${actionBean.negeri eq '04'}">
                        <tr>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Kawasan Parlimen</font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font></td>
                            <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                    <s:select name="kodPar" style="width:150px" value="" id="kodPar" onchange="changeHideDun(this.value)">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodKawasanparlimen}" label="nama" value="kod"/>
                                    </s:select>
                                </font></td>
                        </tr>

                        <tr id="mT">
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">DUN</font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font></td>
                            <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                    <s:select name="kodDmT" style="width:150px" value="" id="kodDmT" onchange="dun(this.value);">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiP134}" label="nama" value="kod"/>
                                        <%--
                                        <s:option value="N01">Kuala Linggi</s:option>
                                        <s:option value="N02">Tanjung Bidara</s:option>
                                        <s:option value="N03">Ayer Limau</s:option>
                                        <s:option value="N04">Lendu</s:option>
                                        <s:option value="N05">Taboh Naning</s:option>
                                        --%>
                                    </s:select>
                                </font></td>
                        </tr>
                        <tr id="aG">
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">DUN</font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font></td>
                            <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                    <s:select name="kodDaG" style="width:150px" value="" id="kodDaG" onchange="dun(this.value);">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiP135}" label="nama" value="kod"/>

                                        <%--
                                        <s:option value="N06">Rembia</s:option>
                                        <s:option value="N07">Gadek</s:option>
                                        <s:option value="N08">Machap</s:option>
                                        <s:option value="N09">Durian Tunggal</s:option>
                                        --%>
                                    </s:select>
                                </font></td>
                        </tr>
                        <tr id="tB">
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">DUN</font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font></td>
                            <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                    <s:select name="kodDtB" style="width:150px" value="" id="kodDtB" onchange="dun(this.value);">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiP136}" label="nama" value="kod"/>
                                        <%--
                                        <s:option value="N11">Sungai Udang</s:option>
                                        <s:option value="N12">Pantai Kundor</s:option>
                                        <s:option value="N13">Paya Rumput</s:option>
                                        <s:option value="N14">Kelebang</s:option>
                                        --%>
                                    </s:select>
                                </font></td>
                        </tr>
                        <tr id="bK">
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">DUN</font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font></td>
                            <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                    <s:select name="kodDbK" style="width:150px" value="" id="kodDbK" onchange="dun(this.value);">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiP137}" label="nama" value="kod"/>
                                        <%--
                                        <s:option value="N15">Bachang</s:option>
                                        <s:option value="N16">Ayer Keroh</s:option>
                                        <s:option value="N17">Bukit Baru</s:option>
                                        <s:option value="N18">Ayer Molek</s:option>
                                        --%>
                                    </s:select>
                                </font></td>
                        </tr>
                        <tr id="kM">
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">DUN</font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font></td>
                            <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                    <s:select name="kodDkM" style="width:150px" value="" id="kodDkM" onchange="dun(this.value);">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiP138}" label="nama" value="kod"/>
                                        <%--
                                        <s:option value="N19">Kesidang</s:option>
                                        <s:option value="N20">Kota Laksamana</s:option>
                                        <s:option value="N21">Duyong</s:option>
                                        <s:option value="N22">Bandar Hilir</s:option>
                                        <s:option value="N23">Telok Mas</s:option>
                                        --%>
                                    </s:select>
                                </font></td>
                        </tr>
                        <tr id="jS">
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">DUN</font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font></td>
                            <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                    <s:select name="kodDjS" style="width:150px" value="" id="kodDjS" onchange="dun(this.value);">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiP139}" label="nama" value="kod"/>

                                        <%--
                                        <s:option value="N24">Bemban</s:option>
                                        <s:option value="N25">Rim</s:option>
                                        <s:option value="N26">Serkam</s:option>
                                        <s:option value="N27">Merlimau</s:option>
                                        <s:option value="N28">Sungai Rambai</s:option>
                                        <s:option value="N10">Asahan</s:option>
                                        --%>
                                    </s:select>
                                </font></td>
                        </tr>
                    </c:if>
                    <c:if test="${actionBean.negeri eq '04'}">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPCS' || actionBean.permohonan.kodUrusan.kod eq 'PPCB' || actionBean.permohonan.kodUrusan.kod eq 'SBMS' ||
                                      actionBean.permohonan.kodUrusan.kod eq 'PYTN'}">
                              <tr>
                                  <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Muatnaik Fail ASCII <br> dari Juruukur berlesen </font></td>
                                  <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font></td>
                                  <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                          <s:radio name="noPTSementara" value="1000"/> Ya &nbsp;&nbsp;&nbsp;&nbsp;
                                          <s:radio name="noPTSementara" value="0"/> Tidak
                                      </font></td>
                              </tr>
                        </c:if>
                    </c:if>
                    <tr>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Lain-lain hal</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font></td>
                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                <s:textarea name="catatan" rows="5" cols="50" id="catatan" class="normal_text"/>
                            </font></td>
                    </tr>
                </table>           

                <p align="center">
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    <%-- <s:button name="Charting" id="btnClick" value="Charting" class="btn" onclick="RunProgram('ppelan1','etanah123')"/>--%>
                    <s:button name="lakarPelan" id="lakarPelan" value="Charting" class="btn"  onclick="RunProgram('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                </p>

            </c:if>
        </c:if>

    </div>
</s:form>