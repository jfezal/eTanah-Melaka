<%--
    Document   : edit_PihakBerkepentingan_tambah
    Author     : wazer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    function addPihak(v) {
        var idHakmilik = $('#hakmilik').val();
        var url = '${pageContext.request.contextPath}/daftar/pembetulan/TambahPihakBerkepentinganActionBean?addNewPihak&idUrusan=' + val1;
        window.open(url, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes");
    }

    function addPihakPemohon(k) {
        var len = $('.pemohon2').length;
        var param = '';

        for (var i = 1; i <= len; i++) {
            var ckd2 = $('#chkbox_pemohon2_' + i).is(":checked");
            if (ckd2) {
                param = param + '&idPemohon=' + $('#chkbox_pemohon2_' + i).val();
            }
        }
        if (param == '') {
            alert('Tiada pihak.');
            return;
        }
        var url = '${pageContext.request.contextPath}/daftar/pembetulan/PihakBerkepentingan?simpanPemohon' + param;

        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function (xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
            },
            success: function (data) {
                $('#page_div', opener.document).html(data);
                self.close();
            }
        });
    }
    function doOpen(val1, val2) {
        var idHakmilik = $('#hakmilik').val();
        alert(val1);
        alert(val2);
        var url = '${pageContext.request.contextPath}/daftar/pembetulan/TambahPihakBerkepentinganActionBean?addNewPihak&idUrusan=' + val1 + '&idMohonPihak=' + val2 ;
        window.open(url, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes");
    }
    function doOpen2(val1, val2) {
        var idHakmilik = $('#hakmilik').val();
        alert(val1);
        alert(val2);
        var url = '${pageContext.request.contextPath}/daftar/pembetulan/TambahPihakBerkepentinganActionBean?addNewPihak&idUrusan=' + val1 + '&idPemohon=' + val2 ;
        window.open(url, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes");
    }
</script>

<%--<s:form name="form1" beanclass="etanah.view.stripes.nota.PihakBerkepentinganActionBean">--%>
<s:form name="form1" beanclass="etanah.view.stripes.nota.TambahPihakBerkepentinganActionBean">

    <div class="subtitle">
        <fieldset class="aras1">

            <legend>
                <c:set var="title" value="Pihak Berkepentingan"/>
                Senarai ${title}
            </legend>
            <div class="content" align="center">
                Sila Klik pada kotak dan tekan Pilih untuk memilih ${title}
                <s:hidden id="idUrusan" name="idUrusan" value="${actionBean.urusan.idUrusan}"/> 
                <display:table class="tablecloth" name="${actionBean.listMohonPihak}" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column property="nama" title="Nama" class="pemohon"/>
                    <display:column property="noPengenalan" title="Nombor Pengenalan" />
                    <display:column property="jenis.nama" title="Jenis Pihak" />
                    <display:column property="hakmilik.idHakmilik" title="Id Hakmilik"/>
                    <display:column property="kodStatus" title="Status"/>
                </display:table>
            </div>
            <p>
                <label>&nbsp;</label>
                <s:button name="add" onclick="doOpen('${actionBean.urusan.idUrusan}','${line.idPermohonanPihak}');" value="tambah" class="btn"/>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()" onmouseover="this.style.cursor='pointer';"/>
            </p>


        </fieldset >
    </div>
    <c:if test="${actionBean.kodUrusan eq 'BETPB'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    <c:set var="title" value="Pihak Berkepentingan Telibat"/>
                    Senarai ${title}
                </legend>
                <div class="content" align="center">
                    Sila Klik pada kotak dan tekan Pilih untuk memilih ${title}


                    <display:table class="tablecloth" name="${actionBean.listPemohon}" cellpadding="0" cellspacing="0" id="line2">
                        <display:column>
                            <s:checkbox name="checkbox2" id="chkbox_pemohon2_${line2_rowNum}" value="${line2.idPemohon}"/>
                        </display:column>
                        <display:column title="Bil" sortable="true">${line2_rowNum}</display:column>
                        <display:column property="nama" title="Nama" class="pemohon2"/>
                        <display:column property="noPengenalan" title="Nombor Pengenalan" />
                        <display:column property="jenis.nama" title="Jenis Pihak" />
                        <display:column property="hakmilik.idHakmilik" title="Id Hakmilik"/>
                        <display:column property="kodStatus" title="Status"/>
                    </display:table>

                </div>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="add" onclick="doOpen2('${actionBean.urusan.idUrusan}','${line2.idPemohon}');" value="tambah" class="btn"/>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()" onmouseover="this.style.cursor='pointer';"/>
                </p>


            </fieldset >
        </div></c:if>
</s:form>