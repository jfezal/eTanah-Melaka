<%-- 
    Document   : UtilitiSenaraiPermohonanLelongan
    Created on : Apr 19, 2012, 3:34:54 PM
    Author     : mazurahayati.yusop, nur.aqilah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    function pilih(v) {
        window.open("${pageContext.request.contextPath}/lelong/senaraipembida?getPembida&idLelong= " + v, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=1200");
    }

    function tambah(v) {
        window.open("${pageContext.request.contextPath}/lelong/senaraipembida?tambahPembida&idPermohonan= " + v, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=1200");
    }
    function tambahWakilPembida(v, v2, wakilPembida) {

        window.open("${pageContext.request.contextPath}/lelong/senaraipembida?tambahWakil&idPembida= " + v + "&idPermohonan=" + v2 + "&wakilPembida=" + wakilPembida, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=1200");
    }

    function refreshingPagingFolder(idPermohonan) {
        var url = "${pageContext.request.contextPath}/lelong/senaraipembida?refresh&idPermohonan=" + idPermohonan;
        window.location = url;
    }

    function editStatus(val, val2, val3) {
        window.open("${pageContext.request.contextPath}/lelong/senaraipembida?tambahStatus&nama=" + val + "&idPembida=" + val2 + "&idLelong=" + val3, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=" + screen.width + ",height=" + screen.height + ",scrollbars=yes,left=0,top=0");
    }

    function updateStatus(idPermohonan, idPembida, idLelong, idHakmilik, kodStatus) {

        $.ajax({
            url: 'senaraipembida?simpanEditPembida&idPermohonan=' + idPermohonan + '&idPembida=' + idPembida + '&idLelong=' + idLelong + '&idHakmilik=' + idHakmilik + '&kodStatus=' + kodStatus,
            success: function() {
            },
            error: function() {
            }
        });
    }

    function updateStatusBersama(idPermohonan, idPihak, idLelong, kodStatus) {

        $.ajax({
            url: 'senaraipembida?simpanEditPembida&idPermohonan=' + idPermohonan + '&idPihak=' + idPihak + '&idLelong=' + idLelong + '&kodStatus=' + kodStatus,
            success: function() {
                alert(success);
            },
            error: function() {
                alert(error);
            }
        });
    }

    function popupAlamat(val, val2) {
        window.open("${pageContext.request.contextPath}/lelong/senaraipembida?tambahTarikhSenaraiHitam&idPembida=" + val + '&idPermohonan=' + val2, "eTanah",
                "status=0,toolbar=0,scrollbars=1,location=0,menubar=0,width=1000,height=1200");
    }
    function muatNaikForm(pembida, id, caraLelong) {
        var left = (screen.width / 2) - (1000 / 2);
        var top = (screen.height / 2) - (150 / 2);
        var url = '${pageContext.request.contextPath}/lelong/senaraipembida?popupUpload&idPembida=' + pembida + '&id=' + id + '&caraLelong=' + caraLelong;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }

    function scan(pembida, id) {
        var url = '${pageContext.request.contextPath}/lelong/senaraipembida?popupScan&idPembida=' + pembida + '&id=' + id;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=800,height=700");
    }

    //    function doViewReport(idDokumen) {
    //        var url= '${pageContext.request.contextPath}/lelong/dokumen/view/' + idDokumen;
    //            
    //        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    //    }
    function doViewReport(d) {
        window.open("${pageContext.request.contextPath}/lelong/dokumen/view?view&idDokumen=" + d, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }


    //    function doViewReport(idDokumen) {
    //        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
    //        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    //    }

    function doViewReport2(v) {
        var url = '${pageContext.request.contextPath}/lelong/senaraipembida?view&idPembida=' + v;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function clearForm() {

        $("#idPermohonan").val('');

    }
    function doSearch2(idPermohonan) {
        alert(idPermohonan);
        var url = '${pageContext.request.contextPath}/lelong/senaraiPembida?search&idPermohonan=' + idPermohonan;
        alert("dsd2");
        f = document.form1;
        alert(url);
        f.action = url;
        alert("dsd4");
        f.submit();
        alert("dsd5");
    }
    function doSearch(e, f) {
        var a = $('#idPermohonan').val();

        if (a == '') {
            alert('Sila masukan id Permohonan');
            return;
        }
        f.action = f.action + '?' + e;
        f.submit();
    }

    $(document).ready(function() {
        var bil = $(".rowCount").length;

        for (var i = 0; i < bil; i++) {
            $('#viewReport' + i).hide();
        }
    });

    function kemaskiniPembida(val1) {


        var idHakmilik = $('#hakmilik').val();
        var url = '${pageContext.request.contextPath}/lelong/senaraipembida?kemaskiniPembida&idPembida=' + val1;
        window.open(url, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes");
    }
    function hapusPembida(val) {
        if (confirm('Adakah pasti untuk hapus')) {
            var url = '${pageContext.request.contextPath}/lelong/senaraipembida?hapusPembida&idPembida=' + val;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }

</script>
<s:form  beanclass="etanah.view.stripes.lelong.UtilitiTukarNamaActionBean" name="form1">
    <s:messages/>
    <s:errors/>

    <s:hidden id="idPembida" name="idPembida" value="${actionBean.idPembida}"/>
    <div class="subtitle" id ="aa">
        <fieldset class="aras1">
            <legend>
                Carian
            </legend>
            <p>
                <label> ID Permohonan :</label>
                <s:text name="idPermohonan" id = "idPermohonan" maxlength="20" size="31" onblur="this.value=this.value.toUpperCase();"/>
            </p>

            <p align="right">
                <s:submit name="checkPermohonan" value="Cari" class="btn" onclick="doSearch(this.name, this.form);"/>
                <s:button name="reset" value="Isi Semula" class="btn" onclick="clearForm();"/>
                <%--<s:button name=" " value="Isi Semula" class="btn" onclick="clearText();" />--%>
            </p>
        </fieldset>

        <c:if test="${actionBean.fasaPermohonan ne null}">
            <p>
                <label><font color="red">* </font>Pegawai Yang Menjatuhkan Perintah :</label>
                        <s:select name="idPengguna" style="width:250px"  id="pengguna">
                            <s:option id="pengguna" value="">-Sila Pilih-</s:option>
                    <s:options-collection collection="${actionBean.senaraiPT}" label="nama" id="pengguna" value="idPengguna" />
                </s:select>
                &nbsp;
            </p>
            <br>
            <center><s:submit name="search" value="Simpan" class="btn" onclick="doSearch(this.name, this.form);"/></center>
        </c:if>
            
            
            
    </div>









</s:form>

