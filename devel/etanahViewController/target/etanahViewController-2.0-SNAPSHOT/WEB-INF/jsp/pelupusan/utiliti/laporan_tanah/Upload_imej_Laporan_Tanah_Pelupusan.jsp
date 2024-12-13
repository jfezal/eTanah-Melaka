<%--
    Document   : Upload_imej_Laporan_Tanah_Pelupusan
    Created on : June 26, 2011, 5:47:08 PM
    Author     : Murali
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MUATNAIK GAMBAR</title>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript">

    function doSave(e, f) {
        //var q = $(f).formSerialize();
        var url = f.action + '?' + e;
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });
        $.ajax({
            type: "GET",
            url: url,
            //data : q,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                //alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                $('#error').html('<div class=errors>Terdapat Masalah Teknikal. Sila Hubungi Pentadbir Sistem.</div><div id=t><a href="#">[ Lihat Terperinci ]</a></div><br/><div id=error class=b>' + xhr.responseText + '</div>');
                $.unblockUI();
            },
            success: function(data) {
                $('#folder_div').html(data);
                $("#folder_div input:text").each(function(el) {
                    $(this).blur(function() {
                        $(this).val($(this).val().toUpperCase());
                    });
                });
                self.close();
            }
        });
    }
    $(document).ready(function() {
    <%--self.opener.refresh('<c:out value="${actionBean.idHakmilik}"/>');--%>
        //            alert($('#pandanganImej').val());
        //            var url = '${pageContext.request.contextPath}/upload?reload&idPermohonan=${idPermohonan}';
        //            $.ajax({
        //                type:"GET",
        //                url : url,
        //                success : function(data) {
        //                    $('#folder_div',opener.document).html(data);
        //                }
        //            });
    });

</script>
<script type="text/javascript">

    function refreshlptn() {
        var url = '${pageContext.request.contextPath}/pelupusan/utiliti/laporanTanah?refreshpage';
        $.get(url,
                function(data) {
                    $('#page_div').html(data);
                    self.opener.refreshlptn();
                    self.close();
                }, 'html');
    }
    function openFrame(type) {
        var idHakmilik = $('#idHakmilik').val();
        var noPtSementara = $('#noPtSementara').val();
        var idPermohonan = $('#idPermohonan').val();
        //        alert(idHakmilik);
        NoPrompt();
        //    alert(idHakmilik);
        window.open("${pageContext.request.contextPath}/pelupusan/utiliti/laporanTanah?openFrame&idHakmilik=" + idHakmilik + "&type=" + type + "&noPtSementara=" + noPtSementara + "&idPermohonan=" + idPermohonan, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }
    function refreshpage() {
        //        alert('aa');
        NoPrompt();
    <c:choose>
        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
        var idHakmilik = $('#idHakmilik').val();
        opener.refreshV2ManyHakmilik('main', idHakmilik);
        </c:when>
        <c:otherwise>
        opener.refreshV2('main');
        </c:otherwise>
    </c:choose>
        self.close();
    }
    function refreshpage2(type, pandanganImej, idLapor, idHakmilik, noPtSementara, idPermohonan) {
//        alert(pandanganImej);
//        alert(type);
//        alert(idLapor);
        var url = '${pageContext.request.contextPath}/pelupusan/utiliti/laporanTanah?refreshpage&type=' + type + '&pandanganImej=' + pandanganImej + '&idLapor=' + idLapor + "&idHakmilik=" + idHakmilik + "&noPtSementara=" + noPtSementara + "&idPermohonan=" + idPermohonan;
        window.open(url, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        
    }
    function deleteImage(idImej, idDokumen, type, f) {
        NoPrompt();
        var imageType = $('#pandanganImej').val();
        //        alert(imageType);
        var idLapor = $('#idLapor').val();

        var idHakmilik = $('#idHakmilik').val();
        var noPtSementara = $('#noPtSementara').val();
        var idPermohonan = $('#idPermohonan').val();
        //        alert(idLapor);

        if (confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/utiliti/laporanTanah?deleteImage&idImej=' + idImej + '&idDokumen=' + idDokumen + '&type=' + type + '&imageType=' + imageType + '&idLapor=' + idLapor, q,
                    function(data) {
                        //    alert(data);
                        $('#page_div').html(data);
                       // alert(sss);
                        self.refreshpage2('imgPopup', imageType, idLapor, idHakmilik, noPtSementara, idPermohonan);
                        //alert(sss);
                    }, 'html');
         
           alert("Fail imej telah berjaya dihapuskan");
        }
    }

</script>

<s:errors/>
<s:messages/>
<body>
    <script>
        // Allow the user to be warned by default.
        var allowPrompt = true;
        window.onbeforeunload = WarnUser;
        function WarnUser()
        {
            if (allowPrompt)
                refreshpage();
            if (allowPrompt)
            {
                event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
            }
            else
            {
                // Reset the flag to its default value.
                allowPrompt = true;
            }
        }
        function NoPrompt()
        {
            allowPrompt = false;
        }

    </script>
    <div id="error"/>
    <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
    <s:form beanclass="etanah.view.stripes.pelupusan.utility.UtilitiLaporanTanahActionBean">
        <s:hidden name="idLapor" id="idLapor" value="${actionBean.laporanTanah.idLaporan}"/>
        <s:hidden name="pandanganImej" id="pandanganImej" value="${actionBean.pandanganImej}"/>
        <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.idPermohonan}"/> 
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <s:hidden name="noPtSementara" id="noPtSementara"/>
        <c:choose>
            <c:when test="${actionBean.pandanganImej eq 'U'}">
                <s:hidden name="idlaporTnhSmpdn" id="idlaporTnhSmpdn" value="${actionBean.disLaporanTanahSempadan.laporanTanahSempadan.idLaporTanahSpdn}"/>
            </c:when>
            <c:when test="${actionBean.pandanganImej eq 'S'}">
                <s:hidden name="idlaporTnhSmpdn" id="idlaporTnhSmpdn" value="${actionBean.disLaporanTanahSempadan.laporanTanahSempadan.idLaporTanahSpdn}"/>
            </c:when>
            <c:when test="${actionBean.pandanganImej eq 'T'}">
                <s:hidden name="idlaporTnhSmpdn" id="idlaporTnhSmpdn" value="${actionBean.disLaporanTanahSempadan.laporanTanahSempadan.idLaporTanahSpdn}"/>
            </c:when>
            <c:when test="${actionBean.pandanganImej eq 'B'}">
                <s:hidden name="idlaporTnhSmpdn" id="idlaporTnhSmpdn" value="${actionBean.disLaporanTanahSempadan.laporanTanahSempadan.idLaporTanahSpdn}"/>
            </c:when>
            <c:otherwise>
            </c:otherwise>
        </c:choose>

        <fieldset class="aras1">         
            <legend>Senarai Gambar</legend>
            <div class="content" align="center">
                <%--s:button name="" value="TEST" onclick="history.go(0);"  id="delImage"/--%>
                <display:table class="tablecloth" name="${actionBean.hakmilikImejLaporanListEdit}" pagesize="5" cellpadding="0" cellspacing="0" id="line"
                               requestURI="/pelupusan/utiliti/laporanTanah">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column title="Gambar">
                        <img id="img${line.dokumen.idDokumen}" alt="${line.dokumen.namaFizikal}" align="center" src="${pageContext.request.contextPath}/dokumen/view/${line.dokumen.idDokumen}" height="100" width="100" data-plusimage="${pageContext.request.contextPath}/dokumen/view/${line.dokumen.idDokumen}" data-plussize="400,300" onclick="popImej('${line.dokumen.idDokumen}')" onmouseover="this.style.cursor = 'pointer';"/>
                    </display:column>
                    <display:column title="Catatan">
                        ${line.catatan}
                    </display:column>
                    <display:column title="Kemaskini">
                        <div align="center">
                            <a onclick="deleteImage('${line.idImej}', '${line.dokumen.idDokumen}', 'imgPopup', this.form);" onmouseover="this.style.cursor = 'pointer';" ><img alt="Hapus" height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/not_ok.gif'/></a>
                        </div>  <%--alert('edit'+${line.idLaporBangunan});--%>
                    </display:column>
                </display:table>
            </div>
        </fieldset>
        <br/> 
        <fieldset class="aras1">
            <%--<s:text name="idHakmilik" value="${idHakmilik}"/>--%>
            <s:hidden name="pandanganImej" value="${pandanganImej}"/>
            <s:hidden name="idHakmilik" id="idHakmilik" value="${actionBean.hakmilikPermohonan.hakmilik.idHakmilik}"/>
            <legend>Muat Naik</legend>
            <p> <p>
                <label><font color="red">*</font>Catatan :</label><s:textarea name="catatan" id="catatan" />
                &nbsp;<p></p>
            <label>&nbsp;</label>&nbsp;
            <s:file name="fileToBeUpload"/>
            <%--<c:if test="${actionBean.fileToBeUpload ne null}">
                ${actionBean.fileToBeUpload.fileName}
            </c:if>--%>
            <br/>
            <p>
                <label>&nbsp;</label>&nbsp;            
                <%--<s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>--%>
                <%--<s:submit name="simpanImejLaporan" value="Simpan" class="btn" onclick="save1(this.name, this.form);"/>--%>
                <%--<s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>--%>
                <%--<s:submit name="simpanImejLaporan" value="Simpan" class="btn" onclick="save1(this.name, this.form);"/>--%>
                <s:submit name="simpanImejLaporan" value="Simpan" class="btn" onclick="NoPrompt();"/>
                <%--<s:button name="close" value="Tutup" onclick="test();" class="btn"/>--%>
                <c:choose>
                    <c:when test="${actionBean.pandanganImej eq 'H'}"> 
                        <s:button name="close" value="Kembali" class="btn" onclick="openFrame('pTanah')"/>
                    </c:when>
                    <c:when test="${actionBean.pandanganImej eq 'U'}">
                        <s:button name="close" value="Kembali" class="btn" onclick="openFrame('lSempadan')"/>
                    </c:when>
                    <c:when test="${actionBean.pandanganImej eq 'S'}">
                        <s:button name="close" value="Kembali" class="btn" onclick="openFrame('lSempadan')"/>
                    </c:when>
                    <c:when test="${actionBean.pandanganImej eq 'T'}">
                        <s:button name="close" value="Kembali" class="btn" onclick="openFrame('lSempadan')"/>
                    </c:when>
                    <c:when test="${actionBean.pandanganImej eq 'B'}">
                        <s:button name="close" value="Kembali" class="btn" onclick="openFrame('lSempadan')"/>
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>

            </p>
        </fieldset>

    </s:form>
</body>

