<%-- 
    Document   : pembetulan_wakil
    Created on : Sep 23, 2020, 1:46:04 PM
    Author     : zipzap
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
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
    function cari(idPermohonan) {
//        alert("idBorangPerPb" + idBorangPerPb);
        doBlockUI();
//        var url = '${pageContext.request.contextPath}/pengambilan/sek8/sediaBorangN?simpanPB&idBorangPerPb=' + idBorangPerPb;
        var url = '${pageContext.request.contextPath}/daftar/utiliti/PembetulanWakilActionBean?cari&idPermohonan=' + idPermohonan;
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
                <s:hidden name="idPemberi" id="idPemberi" value="${actionBean.idPemberi}"/>
                <legend>Pemberi ${actionBean.idPemberi}</legend>
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
                                <!--<td>${actionBean.kodJenisPengenalan.nama}&nbsp;</td>-->
                                <td>${actionBean.wakilPemberi.kodPengenalan}&nbsp;</td>
                                <s:hidden name="jeniskpLama" value="${actionBean.wakilPemberi.kodPengenalan}"/>
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

                <center> <s:submit name="simpanPemberi" id="${actionBean.wakilPemberi.idPemberi}" value="simpan" class="btn"/> </center>
                </c:if>
                <c:if test="${actionBean.wakilPenerima ne null}">
                    <c:if test="${actionBean.wakilPemberi eq null}">
                    <legend>Penerima ${actionBean.idPenerima}</legend>
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

                                <s:hidden name="idPenerima" id="idPenerima" value="${actionBean.wakilPenerima.idPenerima}"/>
                            </tbody>

                        </table>
                        <br>

                    </div>
                    <center><s:submit name="simpanPenerima" id="${actionBean.wakilPemberi.idPenerima}" value="simpan" class="btn"/></center>
                    </c:if>
                </c:if>
        </fieldset>
    </s:form>
</div>
