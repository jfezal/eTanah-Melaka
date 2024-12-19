<%-- 

    Author     : wazer
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<%--<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>--%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/fraction.js" language="Javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript">

    var DELIM = "__^$__";

    function doUpperCase(id) {
        var val = $('#' + id).val().toUpperCase();
        $('#' + id).val(val);
    }





    function deletePengadu(val, idPermohonan) {
        alert(idPermohonan);
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pengambilan/aduan_kerosakan/semak/pemohon_aduan?deletePengadu&idPemohon=' + val + '&idPermohonan=' + idPermohonan;
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

    function editPenerima(i, value) {

        var d;
        if (value == 'penerima') {
            d = $('.a' + i).val();
        }
        else if (value == 'gadaian') {
            d = $('.a2' + i).val();
        }
        window.open("${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?showEditPenerima&idPihak=" + d, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=700,scrollbars=yes");
    }

    //fikri : automatic insert into table for syer


    function refreshPagePemohon() {
        var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?refreshpage';
        $.get(url,
                function(data) {
                    $('#page_div').html(data);
                }, 'html');
    }

    function reloadTuanTanah(val) {
//        alert("val" + val);
//        alert("idMohonHakmilik" + idMohonHakmilik);
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pengambilan/aduan_kerosakan/semak/pemohon_aduan?reloadTuanTanah&idMohonHakmilik=' + val;
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

    function simpanTuanTanah(val, idPermohonan) {
//        alert("idHakmilikPihak " + val);
//        alert("idPermohonan " + idPermohonan);
//        alert("idMohonHakmilik" + idMohonHakmilik);
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pengambilan/aduan_kerosakan/semak/pemohon_aduan?simpanTuanTanah&idHakmilikPihak=' + val + '&idPermohonan=' + idPermohonan;
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
    function simpanPemaju(val, idPermohonan) {
//        alert("idHakmilikPihak " + val);
//        alert("idPermohonan " + idPermohonan);
//        alert("idMohonHakmilik" + idMohonHakmilik);
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pengambilan/aduan_kerosakan/semak/pemohon_aduan?simpanPemaju&idHakmilikPihak=' + val + '&idPermohonan=' + idPermohonan;
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
    function search(val, idPermohonan) {
//        alert("idHakmilikPihak " + val);
//        alert("idPermohonan " + idPermohonan);
//        alert("idMohonHakmilik" + idMohonHakmilik);
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pengambilan/aduan_kerosakan/semak/pemohon_aduan?search&idMohonHakmilik=' + val + '&idPermohonan=' + idPermohonan;
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
    function searchPemohonPemaju(val, idmhnLama) {
//        alert("idHakmilikPihak " + val);
//        alert("idPermohonan " + idmhnLama);
//        alert("idMohonHakmilik" + idMohonHakmilik);
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pengambilan/aduan_kerosakan/semak/pemohon_aduan?searchPemohonPemaju&idMohonHakmilik=' + val + '&idmhnLama=' + idmhnLama;
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

//    function deletePengadu(val, idPermohonan) {
//        doBlockUI();
//        var url = '${pageContext.request.contextPath}/pengambilan/aduan_kerosakan/semak/pemohon_aduan?deletePengadu&idPemohon=' + val + '&idPermohonan=' + idPermohonan;
//        $.ajax({
//            type: "GET",
//            url: url,
//            dataType: 'html',
//            error: function(xhr, ajaxOptions, thrownError) {
//                alert("error=" + xhr.responseText);
//                doUnBlockUI();
//            },
//            success: function(data) {
//                $('#page_div').html(data);
//                doUnBlockUI();
//            }
//        });
//    }

    function selectHakmilik(val) {
//        alert("val" + val);
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pengambilan/aduan_kerosakan/semak/pemohon_aduan?reloadMain&idMohonHakmilik=' + val;
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

<s:form beanclass="etanah.view.stripes.pengambilan.aduan.semakan.pemohonAduanActionBean">
    <div class="subtitle displaytag">
        <s:messages/>
        <s:errors/>
        <s:hidden name="kod" id="kod"/>
        <s:hidden name="idMohonHakmilik" id="idMohonHakmilik" value="${actionBean.hakmilikPermohonan.id}"/>
        <s:hidden name="idmhnLama" id="idmhnLama" value="${actionBean.permohonanLama.idPermohonan}"/>
        <fieldset class="aras1" align="center">
            <legend>
            </legend>
            <br>
            <div align="center">
                <s:text name="idmhnLama" id="idmhnLama" size="30"/> &nbsp&nbsp
                <s:button name="cariPemaju" id="save" value="cari" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>  

                <c:if test="${(fn:length(actionBean.senaraiHakmilikPermohonan) > 0 ) && (actionBean.infoWarta ne null)}">
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.senaraiHakmilikPermohonan}" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

                            <display:column  title="Id Hakmilik" >
                                <c:if test="${line.hakmilik ne null}">
                                    ${line.hakmilik.idHakmilik}
                                </c:if>
                                <c:if test="${line.hakmilik eq null}">
                                    Tidak Dapat Dikesan
                                </c:if>
                            </display:column>
                            <display:column property="kodHakmilik.nama" title="Bangsa" />
                            <display:column property="noLot" title="No Lot" />
                            <display:column  title="Luas" >
                                ${line.luasTerlibat} ${line.kodUnitLuas.kod}
                            </display:column>
                            <display:column  title="No Warta" >
                                ${actionBean.infoWarta.noWarta} 
                            </display:column>
                            <display:column  title="Tarikh Warta" >
                                ${actionBean.infoWarta.trhWarta} 
                            </display:column>
                            <display:column  title="Tarikh Warta" >
                                ${actionBean.infoWarta.keteranganWarta} 
                            </display:column>
                            <display:column title="Pilih">
                                <p align="center">
                                    <s:radio name="maklumatPenyerah" id="maklumatPenyerah" checked="checked" value="Y" onclick="searchPemohonPemaju('${line.id}','${line.permohonan.idPermohonan}');"/> Ya &nbsp
                                </p>
                            </display:column>
                            <%--</c:if>--%>
                        </display:table>
                    </div>
                </c:if>
            </div>

            <fieldset class="aras1">
                <c:if test="${fn:length(actionBean.pemohonListPemaju) > 0 }">

                    <legend>
                        Senarai Pengadu 
                    </legend>

                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.pemohonListPemaju}" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

                            <display:column title="Bil" sortable="true">${line_rowNum}
                                <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                            </display:column>
                            <display:column  title="Nama" >
                                <%--<a href="#" onclick="viewPihak('${line.pihak.idPihak}', 'pemohon');return false;">--%>${line.pihak.nama}
                            </display:column>
                            <display:column  title="hakmilik" >
                                <%--<a href="#" onclick="viewPihak('${line.pihak.idPihak}', 'pemohon');return false;">--%>${line.hakmilik.idHakmilik}
                            </display:column>
                            <display:column property="pihak.noPengenalan" title="No. Pengenalan/No.Syarikat" />${line.pihak.noPengenalan}
                            <display:column title="Alamat ">${line.pihak.alamat1}
                                <c:if test="${line.pihak.alamat2 ne null}">,</c:if>
                                ${line.pihak.alamat2}
                                <c:if test="${line.pihak.alamat3 ne null}"> </c:if>
                                ${line.pihak.alamat3}
                                <c:if test="${line.pihak.alamat4 ne null}">,</c:if>
                                ${line.pihak.alamat4}
                                <c:if test="${line.pihak.poskod ne null}">,</c:if>
                                ${line.pihak.poskod}
                                <c:if test="${line.pihak.negeri.kod ne null}">,
                                    <c:if test="${line.pihak.negeri.kod eq '01'}">JOHOR</c:if>
                                    <c:if test="${line.pihak.negeri.kod eq '02'}">KEDAH</c:if>
                                    <c:if test="${line.pihak.negeri.kod eq '03'}">KELANTAN</c:if>
                                    <c:if test="${line.pihak.negeri.kod eq '04'}">MELAKA</c:if>
                                    <c:if test="${line.pihak.negeri.kod eq '05'}">NEGERI SEMBILAN</c:if>
                                    <c:if test="${line.pihak.negeri.kod eq '06'}">PAHANG</c:if>
                                    <c:if test="${line.pihak.negeri.kod eq '07'}">PULAU PINANG</c:if>
                                    <c:if test="${line.pihak.negeri.kod eq '08'}">PERAK</c:if>
                                    <c:if test="${line.pihak.negeri.kod eq '09'}">PERLIS</c:if>
                                    <c:if test="${line.pihak.negeri.kod eq '10'}">SELANGOR</c:if>
                                    <c:if test="${line.pihak.negeri.kod eq '11'}">TERENGGANU</c:if>
                                    <c:if test="${line.pihak.negeri.kod eq '12'}">SABAH</c:if>
                                    <c:if test="${line.pihak.negeri.kod eq '13'}">SARAWAK</c:if>
                                    <c:if test="${line.pihak.negeri.kod eq '14'}">WP KUALA LUMPUR</c:if>
                                    <c:if test="${line.pihak.negeri.kod eq '15'}">WP LABUAN</c:if>
                                    <c:if test="${line.pihak.negeri.kod eq '16'}">WP PUTRAJAYA</c:if>
                                </c:if>
                                <%--<font style="text-transform:uppercase;">${line.pihak.negeri.nama}</font>--%>
                            </display:column>
                            <%--<c:if test="${edit}">--%>
                            <display:column title="Hapus ${actioanBean.permohonan.idPermohonan}">
                                <p align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif'
                                         onclick="deletePengadu('${line.idPemohon}','${actioanBean.permohonanLama.idPermohonan}');
                                                 return false;" onmouseover="this.style.cursor = 'pointer';">
                                </p>
                            </display:column>
                            <%--</c:if>--%>
                        </display:table>
                    </div>
                    <%--</c:if>--%>


                    <br/>

                </c:if>

            </fieldset>


    </div>

</fieldset>

<br>

</s:form>



