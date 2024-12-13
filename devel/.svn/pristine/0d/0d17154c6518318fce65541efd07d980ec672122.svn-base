<%-- 
    Document   : pembetulan_wakil
    Created on : Sep 23, 2020, 1:46:04 PM
    Author     : zipzap
--%>

<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%--<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>--%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/timepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.jtimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

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

    function editPenerima(idPermohonan) {
//        alert("idBorangPerPb" + idBorangPerPb);
        doBlockUI();
//        var url = '${pageContext.request.contextPath}/pengambilan/sek8/sediaBorangN?simpanPB&idBorangPerPb=' + idBorangPerPb;
        var url = '${pageContext.request.contextPath}/daftar/utiliti/PembetulanWakilActionBean?editPenerima&idPermohonan=' + idPermohonan;
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
//    function cari(idPermohonan) {
////        alert("idBorangPerPb" + idBorangPerPb);
//        doBlockUI();
////        var url = '${pageContext.request.contextPath}/pengambilan/sek8/sediaBorangN?simpanPB&idBorangPerPb=' + idBorangPerPb;
//        var url = '${pageContext.request.contextPath}/daftar/utiliti/PembetulanWakilActionBean?cari&idPermohonan=' + idPermohonan;
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
    function editPemberi(idPermohonan) {
//        alert("idBorangPerPb" + idBorangPerPb);
        doBlockUI();
//        var url = '${pageContext.request.contextPath}/pengambilan/sek8/sediaBorangN?simpanPB&idBorangPerPb=' + idBorangPerPb;
        var url = '${pageContext.request.contextPath}/daftar/utiliti/PembetulanWakilActionBean?editPemberi&idPermohonan=' + idPermohonan;
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

    function penerima(idPenerima, idPermohonan) {
//        alert("idPenerima =" + idPenerima);
//        alert("idPermohonan =" + idPermohonan);
//        alert("idMohonHakmilik = " + idMohonHakmilik);
//        doBlockUI();
        var url = "${pageContext.request.contextPath}/daftar/utiliti/PembetulanWakilActionBean?pilihPenerima&idPenerima=" + idPenerima + '&idPermohonan=' + idPermohonan;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
//                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
//                doUnBlockUI();
            }
        });
    }

//       function tambahPenerima1(idPermohonan) {
//        alert("idPermohonan = " + idPermohonan);
////        doBlockUI();
//        var url = "${pageContext.request.contextPath}/daftar/utiliti/PembetulanWakilActionBean?tambahPemberi&idPermohonan=" + idPermohonan;
//        $.ajax({
//     
//        type: "GET",
//            url: url,
//            dataType: 'html',
//            error: function(xhr, ajaxOptions, thrownError) {
//                alert("error=" + xhr.responseText);
////                doUnBlockUI();
//            },
//            success: function(data) {
//                $('#page_div').html(data);
////                doUnBlockUI();
//            }
//        });
//    }
//     function tambahPenerima1(idPermohonan,) {
//           alert("idPermohonan = " + idPermohonan);
//          var url = "${pageContext.request.contextPath}/daftar/utiliti/PembetulanWakilActionBean?tambahPemberi&idPermohonan=" + idPermohonan;
//        $.get(url,
//                function(data) {alert("idPermohonan1 = " + idPermohonan);
//                    $('#page_div').html(data);
//                }, 'html');
//                 
//    }

    function tambahPenerima1(idPermohonan) {
        alert("idPermohonan = " + idPermohonan);
        var url = '${pageContext.request.contextPath}/daftar/utiliti/PembetulanWakilActionBean?tambahPemberi&idPermohonan=' + idPermohonan;
        //var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?carianIndependent&katTanahPilihan=' + katTanahPilihan;
        $.ajax({
            url: url,
            success: function(data) {
                $('#page_div').html(data);
            }
        });
    }

</script>           
<div class="subtitile">

    <s:errors/>
    <s:messages/>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.daftar.utiliti.PembetulanWakilActionBean">


        <fieldset class="aras1">
            <legend>Carian Id Wakil</legend>
            <p>
                <label><em>*</em>ID Wakil :</label>
                <s:text name="idPermohonan" id="idPermohonan"  onkeyup="this.value=this.value.toUpperCase();"/>
                <s:submit name="cari" id="save" value="Cari" class="btn"/>
            </p>

            <br/>
            <c:if test="${actionBean.wakilPemberi ne null}">
                <legend>Pemberi</legend>
                <div class="content" align="center">
                    <table class="tablecloth" cellpadding="0" cellspacing="0">
                        <thead>
                            <tr>
                                <th>Bil</th>
                                <th>Jenis Medan</th>
                                <th>Maklumat Lama</th>
                                <th>Maklumat Baru</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="odd">
                                <td>1.</td>
                                <td>Nama</td><s:hidden name="idHakmilik"/><s:hidden name="namaLama" value="${actionBean.wakilPemberi.nama}"/>
                                <td>${actionBean.wakilPemberi.nama}&nbsp;<s:hidden name="idPihak" /></td>
                                <td>
                                    <%--<s:text name="nama" onkeyup="this.value=this.value.toUpperCase();"/>--%>
                                    <s:textarea name="namaPemberi" id="namaPemberi" onkeyup="this.value=this.value.toUpperCase()" rows="5" cols="50" value="${nama}"/>
                                </td>
                            </tr>
                            <tr class="even">
                                <td>2.</td>
                                <td>Jenis Pengenalan</td>
                                <td>${actionBean.wakilPemberi.kodPengenalan}&nbsp;</td><s:hidden name="jeniskpLama" value="${actionBean.wakilPemberi.kodPengenalan}"/>
                                <td>
                                    <s:select name="jenisPengenalanPemberi" id="jenisPengenalanPemberi" value="${kod}">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                    </s:select>
                                </td>
                            </tr>
                            <tr class="odd">
                                <td>3.</td>
                                <td>No Pengenalan</td>
                                <td>${actionBean.wakilPemberi.noPengenalan}&nbsp;</td><s:hidden name="nokpLama" value="${actionBean.wakilPemberi.noPengenalan}"/>
                                <td><s:text name="noPengenalanPemberi" id="noPengenalanPemberi" onkeyup="this.value=this.value.toUpperCase();" value="${noPengenalanPemberi}"/></td>
                            </tr>


                        </tbody>

                    </table>
                </div>
                <br>

            </c:if>
            <c:if test="${fn:length(actionBean.listWakilKuasaPenerima) > 1}">
                <c:if test="${actionBean.wakilPenerima ne null}">
                    <legend>Penerima</legend>
                    <div class="content" align="center">
                        <table class="tablecloth" cellpadding="0" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>Bil</th>
                                    <th>Jenis Medan</th>
                                    <th>Maklumat Lama</th>
                                    <th>Maklumat Baru</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="odd">
                                    <td>1.</td>
                                    <td>Nama</td><s:hidden name="idHakmilik"/><s:hidden name="namaLama" value="${actionBean.wakilPenerima.nama}"/>
                                    <td>${actionBean.wakilPenerima.nama}&nbsp;<s:hidden name="idPihak" /></td>
                                    <td>
                                        <%--<s:text name="nama" onkeyup="this.value=this.value.toUpperCase();"/>--%>
                                        <s:textarea name="namaPenerima" id="namaPenerima" onkeyup="this.value=this.value.toUpperCase()" rows="5" cols="50" value="${namaPenerima}"/>
                                    </td>
                                </tr>
                                <tr class="even">
                                    <td>2.</td>
                                    <td>Jenis Pengenalan</td>
                                    <td>${actionBean.wakilPenerima.jenisPengenalan.nama}&nbsp;</td><s:hidden name="jeniskpLama" value="${actionBean.wakilPenerima.jenisPengenalan.nama}"/>
                                    <td>
                                        <s:select name="jenisPengenalanPenerima" id="jenisPengenalanPenerima" value="${jenisPengenalanPenerima}">
                                            <s:option value="">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                        </s:select>
                                    </td>
                                </tr>
                                <tr class="odd">
                                    <td>3.</td>
                                    <td>No Pengenalan</td>
                                    <td>${actionBean.wakilPenerima.noPengenalan}&nbsp;</td><s:hidden name="nokpLama" value="${actionBean.wakilPenerima.noPengenalan}"/>
                                    <td><s:text name="noPengenalanPenerima" id="noPengenalanPenerima" onkeyup="this.value=this.value.toUpperCase();" value="${noPengenalanPenerima}"/></td>
                                </tr>


                            </tbody>

                        </table>
                        <br>
                        <s:submit name="simpan" id="save" value="Simpan" class="btn"/>
                    </div>
                </fieldset>
            </c:if>
        </c:if>
        <center>
            <br>     
            
            <c:if test="${fn:length(actionBean.listWakilKuasaPemberi) > 0}">
                <legend>Pemberi</legend>
                <display:table class="tablecloth" name="${actionBean.listWakilKuasaPemberi}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/maklumat_tambahan">
                    <display:column property="nama" title="Nama" />
                    <display:column title="Jenis Pengenalan">
                        <c:if test="${empty line.pihak.jenisPengenalan.nama}">
                         ${line.kodPengenalan}
                        </c:if>
                        <c:if test="${!empty line.pihak.jenisPengenalan.nama}">
                            ${line.pihak.jenisPengenalan.nama}
                        </c:if>
                   </display:column>
                    <display:column property="noPengenalan" title="No Pengenalan" />
                    <display:column title="Alamat Pemberi">
                        ${line.pihak.alamat1}
                        ${line.pihak.alamat2}
                        ${line.pihak.alamat3}
                        ${line.pihak.alamat4}
                    </display:column>
                    <display:column property="pihak.poskod" title="Poskod" />
                    <display:column property="pihak.negeri.nama" title="Negeri" />
                    <display:column title="Pilihan Pemberi">
                        <s:hidden id="idPermberi" name="idPermberi" value="${line.idPemberi}" />
                        <s:submit name="pilihPemberi" id="pilihPemberi" value="kemaskini" class="btn"/>
                    </display:column>
                </display:table>
            </c:if>
            <c:if test="${actionBean.wakilK ne null}">
                <c:if test="${fn:length(actionBean.listWakilKuasaPemberi) <= 0}">
                    <legend>Pemberi</legend>
                    <br>
                    <s:hidden id="idPermohonan" name="idPermohonan" value="${actionBean.idPermohonan}" />
                    <s:submit name="tambahPemberi" value="tambah" class="btn"/>
                </c:if>
            </c:if>
            <br>
            <br>        
            <c:if test="${fn:length(actionBean.listWakilKuasaPenerima) > 0}">
                <legend>Penerima</legend>
                <display:table class="tablecloth" name="${actionBean.listWakilKuasaPenerima}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/maklumat_tambahan">
                    <display:column property="nama" title="Nama" />
                    <display:column property="jenisPengenalan.nama" title="Jenis Pengenalan" />
                    <display:column property="noPengenalan" title="No Pengenalan" />
                    <display:column title="Alamat Penerima">
                        ${line.alamat1}
                        ${line.alamat2}
                        ${line.alamat3}
                        ${line.alamat4}
                    </display:column>
                    <display:column property="poskod" title="Poskod" />
                    <display:column property="negeri.nama" title="Negeri" />
                    <display:column title="Pilihan Penerima">
                        <s:hidden id="idPenerima" name="idPenerima" value="${line.idPenerima}" />
                        <s:submit name="pilihPenerima" id="pilihPenerima" value="kemaskini" class="btn"/>
                    </display:column>
                </display:table>
            </c:if>
            <c:if test="${actionBean.wakilK ne null}">
                 <c:if test="${fn:length(actionBean.listWakilKuasaPenerima) <= 0}">
                     <legend>Penerima</legend>
                     <br>
                     <s:hidden id="idPermohonan" name="idPermohonan" value="${actionBean.idPermohonan}" />
                     <s:submit name="tambahPenerima" value="tambah" class="btn"/>
                 </c:if>
             </c:if>
            
            <br>
            <br><br>

        </center>
    </s:form>
</div>
