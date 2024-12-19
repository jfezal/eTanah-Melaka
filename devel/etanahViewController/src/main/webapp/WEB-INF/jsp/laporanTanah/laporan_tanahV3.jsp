<%-- 
    Document   : laporan_tanahV3
    Created on : Oct 14, 2020, 1:38:23 PM
    Author     : zipzap
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

    $(document).ready(function() {


        var len = $(".daerah").length;

        for (var i = 0; i <= len; i++) {
            $('.hakmilik' + i).click(function() {
                window.open("${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikpengambilan?popup&idHakmilik=" + $(this).text(), "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600").focus();
            });
        }
    });


    function reloadPT(val) {
//        alert("val" + val);
        doBlockUI();
        var url = '${pageContext.request.contextPath}/laporanTanah?reloadMain&idMohonHakmilik=' + val;
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
    function simpanKedudukanTanah(idMohonHakmilik, idLaporTanah) {
        window.open("${pageContext.request.contextPath}/laporanTanah?simpanKedudukan&idMohonHakmilik=" + idMohonHakmilik + '&idLaporTanah=' + idLaporTanah, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
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
    
       function stsTanah(idMohonHakmilik) {
//        alert("idMohonHakmilik = " + idMohonHakmilik);
        doBlockUI();
        var url = "${pageContext.request.contextPath}/laporanTanah?statusPerihalTanah&idMohonHakmilik=" + idMohonHakmilik;
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
<s:form beanclass="etanah.view.laporanTanah.laporanTanahV3" name="form">

    <s:errors/>
    <s:messages/>
    <s:hidden name="idHakmilik" id="idHakmilik"/>
    <s:hidden name="noPtSementara" id="noPtSementara"/>
    <s:hidden name="idMohonHakmilik" id="idMohonHakmilik"/>
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
        </fieldset>
        <br>

        <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
            <tfoot>
                <tr>    
                    <td colspan="4"><center><b>Perihal Tanah</b></center></td>  
            </tr>
            </tfoot>

            <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                <tr>
                    <th>Perkara:</th>
                    <td>${actionBean.permohonanPengambilan.tujuanPermohonan}</td>
                </tr>
                <tr>
                    <th>No Ruj Fail:</th>
                    <td>${actionBean.permohonan.idPermohonan}</td>
                </tr>
                <tr>
                    <th>Tarikh Permohonan:</th>
                    <td>${actionBean.permohonan.infoAudit.tarikhMasuk}</td>
                </tr>
                <tr>
                    <th>Tarikh Terima Fail:</th>
                    <td>${actionBean.fasaPermohonan.tarikhHantar}</td>
                </tr>
            </table>
        </table>
        <br>
        <c:if test="${fn:length(actionBean.listMohonPerihalTanah) > 0}">
            <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                <tfoot>
                    <tr>    
                        <td colspan="4"><center><b>Perihal Permohonan</b></center></td>  
                </tr>
                </tfoot>

                <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2"> 
                    <c:forEach items="${actionBean.listMohonPerihalTanah}" var="item" varStatus="line">
                        <tr>

                            <c:if test="${item.kodPerihalTanah.kod eq 'T01'}">
                                <th>${item.kodPerihalTanah.nama}</th>
                                <td>  <s:textarea name="jalan1" value="${item.keterangan}" id="jalan" rows="3" cols="100"/></td>
                            </c:if>   
                        </tr>
                        <tr>
                            <c:if test="${item.kodPerihalTanah.kod eq 'T02'}">
                                <th>${item.kodPerihalTanah.nama}</th>
                                <td>  <s:textarea name="kampung1" value="${item.keterangan}" id="kampung" rows="3" cols="100"/></td>
                            </c:if>   
                        </tr>
                        <tr>
                            <c:if test="${item.kodPerihalTanah.kod eq 'T03'}">
                                <th>${item.kodPerihalTanah.nama}</th>
                                <td>  <s:textarea name="landMark1" value="${item.keterangan}" id="landMark" rows="3" cols="100"/></td>
                            </c:if>   
                        </tr>
                        <tr>
                            <c:if test="${item.kodPerihalTanah.kod eq 'T04'}">
                                <th>${item.kodPerihalTanah.nama}</th>
                                <td>  <s:textarea name="jarak1" value="${item.keterangan}" id="jarak" rows="3" cols="100"/></td>
                            </c:if>   
                        </tr>
                        <tr>
                            <c:if test="${item.kodPerihalTanah.kod eq 'T05'}">
                                <th>${item.kodPerihalTanah.nama}</th>
                                <td>  <s:textarea name="dun1" value="${item.keterangan}" id="dun" rows="3" cols="100"/></td>
                            </c:if>   
                        </tr>
                        <tr>
                            <c:if test="${item.kodPerihalTanah.kod eq 'T06'}">
                                <th>${item.kodPerihalTanah.nama}</th>
                                <td>  <s:textarea name="parlimen1" value="${item.keterangan}" id="parlimen" rows="3" cols="100"/></td>
                            </c:if>   
                        </tr>
                        <tr>
                            <c:if test="${item.kodPerihalTanah.kod eq 'T07'}">
                                <th>${item.kodPerihalTanah.nama}</th>
                                <td>  <s:textarea name="PBT1" value="${item.keterangan}" id="PBT" rows="3" cols="100"/></td>
                            </c:if>   
                        </tr>
                        <tr>
                            <c:if test="${item.kodPerihalTanah.kod eq 'T08'}">
                                <th>${item.kodPerihalTanah.nama}</th>
                                <td>  <s:textarea name="zone1" value="${item.keterangan}" id="zone" rows="3" cols="100"/></td>
                            </c:if>   
                        </tr>
                        <tr>
                            <c:if test="${item.kodPerihalTanah.kod eq 'T09'}">
                                <th>${item.kodPerihalTanah.nama}</th>
                                <td>  <s:textarea name="tnb1" value="${item.keterangan}" id="tnb" rows="3" cols="100"/></td>
                            </c:if>   
                        </tr>
                        <tr>
                            <c:if test="${item.kodPerihalTanah.kod eq 'T10'}">
                                <th>${item.kodPerihalTanah.nama}</th>
                                <td>  <s:textarea name="koordinat1" value="${item.keterangan}" id="koordinat" rows="3" cols="100"/></td>
                            </c:if>   
                        </tr>
                    </c:forEach>
                </table>
                <br>
            </table>
            <br>
            <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                <tfoot>
                    <tr>    
                        <td colspan="4"><center><b>Butir - Butir Tanah</b></center></td>  
                </tr>
                </tfoot>

                <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                    <tr>
                        <th>No. Lot/PT</th>
                        <td>${actionBean.hakmilikPermohonan.hakmilik.noLot}</td>
                    </tr>
                    <tr>
                        <th>Luas</th>
                        <td>${actionBean.hakmilikPermohonan.hakmilik.luas}</td>
                    </tr>
                    <tr>
                        <th>Mukim/Pekan/Bandar</th>
                        <td>${actionBean.hakmilikPermohonan.hakmilik.bandarPekanMukim.nama}</td>
                    </tr>
                    <tr>
                        <th>Daerah</th>
                        <td>${actionBean.hakmilikPermohonan.hakmilik.daerah.nama}</td>
                    </tr>
                    <tr>
                        <th>Lembar Piawai</th>
                        <td>${actionBean.noLitho}</td>
                    </tr>
                    <tr>
                        <th>No Pelan Akui</th>
                        <td>${actionBean.hakmilikPermohonan.hakmilik.bandarPekanMukim.nama}</td>
                    </tr>
                </table>
                <br>
                
                <center>
                    <%--<s:button name="simpanKedudukanTanah" id="" value="simpan" class="btn" onclick="simpanKedudukanTanah('${line4.id}','${actionBean.idPermohonan}')"/>--%> 
                    <s:button name="simpanKedudukan" id="" value="simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/> &nbsp; &nbsp;
                    <s:button name="statusPerihalTanah" id="" value="Seterusnya" class="btn" onclick="stsTanah('${actionBean.hakmilikPermohonan.id}')"/>
                </center>
            </table>
        </c:if>

    </div>
</s:form>
