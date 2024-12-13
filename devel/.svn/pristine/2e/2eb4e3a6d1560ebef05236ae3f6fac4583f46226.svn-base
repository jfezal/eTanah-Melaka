<%--
    Document   : edit_PihakBerkepentingan
    Created on : Nov 10, 2009, 4:23:38 PM
    Author     : w.fairul
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
        var len = $('.pemohon').length;
        var param = '';

        for (var i = 1; i <= len; i++) {
            var ckd = $('#chkbox_pemohon_' + i).is(":checked");
            if (ckd) {
                param = param + '&idHakmilikPihakBerkepentingan=' + $('#chkbox_pemohon_' + i).val();
            }
        }
        if (param == '') {
            alert('Tiada pihak.');
            return;
        }
        var url = '${pageContext.request.contextPath}/daftar/pembetulan/PihakBerkepentingan?simpanPemohonPihak' + param;

        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
            },
            success: function(data) {
                $('#page_div', opener.document).html(data);
                self.close();
            }
        });
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
        var url = '${pageContext.request.contextPath}/daftar/pembetulan/PihakBerkepentingan?simpanPemohon2Pihak' + param;

        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
            },
            success: function(data) {
                $('#page_div', opener.document).html(data);
                self.close();
            }
        });
    }
</script>

<s:form name="form1" beanclass="etanah.view.stripes.nota.PihakBerkepentinganActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                <c:set var="title" value="Pihak Berkepentingan"/>
                Senarai ${title}
            </legend>
            <div class="content" align="center">
                Sila Klik pada kotak dan tekan Pilih untuk memilih ${title}


                <display:table class="tablecloth" name="${actionBean.pihakKepentinganList2}" cellpadding="0" cellspacing="0" id="line">
                    <display:column>
                        <s:checkbox name="checkbox" id="chkbox_pemohon_${line_rowNum}" value="${line.idHakmilikPihakBerkepentingan}"/>
                    </display:column>
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column property="pihak.nama" title="Nama" class="pemohon"/>
                    <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                    <display:column property="jenis.nama" title="Jenis Pihak" />
                    <display:column property="hakmilik.idHakmilik" title="Id Hakmilik"/>
                </display:table>

            </div>
            <p>
                <label>&nbsp;</label>
                <s:button class="btn" value="Pilih" name="pilih" id="pilih" onclick="addPihak();" onmouseover="this.style.cursor='pointer';"/>&nbsp;
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


                    <display:table class="tablecloth" name="${actionBean.pemohonList2}" cellpadding="0" cellspacing="0" id="line">
                        <display:column>
                            <s:checkbox name="checkbox2" id="chkbox_pemohon2_${line_rowNum}" value="${line.idPemohon}"/>
                        </display:column>
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column property="nama" title="Nama" class="pemohon2"/>
                        <display:column property="noPengenalan" title="Nombor Pengenalan" />
                        <display:column property="jenis.nama" title="Jenis Pihak" />
                        <display:column property="hakmilik.idHakmilik" title="Id Hakmilik"/>
                    </display:table>

                </div>
                <p>
                    <label>&nbsp;</label>
                    <s:button class="btn" value="Pilih" name="pilih" id="pilih" onclick="addPihakPemohon();" onmouseover="this.style.cursor='pointer';"/>&nbsp;
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()" onmouseover="this.style.cursor='pointer';"/>
                </p>


            </fieldset >
        </div></c:if>
</s:form>