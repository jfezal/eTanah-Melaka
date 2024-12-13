<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:40em;
    }
</style>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
    function p(v, w) {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });
        $.get("${pageContext.request.contextPath}/strata/maklumat_tanah?showForm&idHakmilikPihakBerkepentingan=" + v + "&idHakmilik=" + w,
                function (data) {
                    $("#perincianPihak").show();
                    $("#perincianPihak").html(data);
                    $.unblockUI();
                });
    }

    $(document).ready(function () {


        var len = $(".daerah").length;

        for (var i = 0; i <= len; i++) {
            $('.hakmilik' + i).click(function () {
                window.open("${pageContext.request.contextPath}/strata/maklumat_tanah?hakmilikDetail&idHakmilik=" + $(this).text(), "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }
    });
</script>
<s:form beanclass="etanah.view.strata.MaklumatTanahActionBean">
    <s:messages/>
    <s:errors/>
    <%--<div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tanah
            </legend>
            <br>

            <display:table   class="tablecloth" name="${actionBean.listHakmilikP}" cellpadding="0" cellspacing="0" id="line" >
                <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}<s:hidden id="hidden3" name="hidden3" value="${line_rowNum-1}"/></display:column>
                <display:column title="No Hakmilik"><a href="#" title="Sila klik untuk papar detail" onclick="p('61200304','${line.hakmilik.idHakmilik}');return false;">${line.hakmilik.idHakmilik}</a></display:column>
                <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}" style="vertical-align:baseline"/>
                <display:column title="No Lot"> ${line.hakmilik.lot.nama} ${line.hakmilik.noLot}</display:column>
                <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah" style="vertical-align:baseline;text-transform:capitalize"/>
                <display:column property="hakmilik.tarikhDaftar" title="Tarikh Daftar" />
                <display:column property="hakmilik.kodHakmilik.nama" title="Jenis Hakmilik" />
                <display:column property="hakmilik.kategoriTanah.nama" title="Kategori Tanah" />
                <display:column title="Luas" >${line.hakmilik.luas} ${line.hakmilik.kodUnitLuas.nama}</display:column>

            </display:table>
            <br>

        </fieldset>
    </div>--%>
    <%--   <div id="perincianPihak">
           <a href="#" title="Sila klik untuk perincian maklumat" onclick="p('61200304','${line.hakmilik.idHakmilik}');return false;">Papar Pemilik</a>

        <p></p>
    </div>--%>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pemilik</legend>
            <br>
            <display:table class="tablecloth" name="${actionBean.listHakmilikPihak}" cellpadding="0" cellspacing="0" id="line"
                           requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                <display:column  title="Nama" class="nama">
                    ${line.nama}
                </display:column>
                <display:column property="noPengenalan" title="Nombor Pengenalan" />
                <%-- <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                 <display:column property="jenis.nama" title="Jenis Pihak"/>--%>

                <%-- <display:column title="Alamat" >${line.pihak.alamat1}
                    <c:if test="${line.pihak.alamat2 ne null}"> , </c:if>
                    ${line.pihak.alamat2}
                    <c:if test="${line.pihak.alamat3 ne null}"> , </c:if>
                    ${line.pihak.alamat3}
                    <c:if test="${line.pihak.alamat4 ne null}"> , </c:if>
                    ${line.pihak.alamat4}</display:column>
                <display:column property="pihak.poskod" title="Poskod" />
                <display:column property="pihak.negeri.nama" title="Negeri" />  --%>

                <display:column title="Alamat" >${line.alamat1}
                    <c:if test="${line.alamat2 ne null}"> , </c:if>
                        ${line.alamat2}
                    <c:if test="${line.alamat3 ne null}"> , </c:if>
                        ${line.alamat3}
                    <c:if test="${line.alamat4 ne null}"> , </c:if>
                    ${line.alamat4}</display:column>
                <display:column property="poskod" title="Poskod" />
                <display:column property="negeri.nama" title="Negeri" /> 

            </display:table>
            <br>
            <legend>Maklumat Urusan Berkuatkuasa</legend>
            <br>
            <display:table class="tablecloth" name="${actionBean.listHakmilikUrusan}" cellpadding="0" cellspacing="0" id="line">

                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                <display:column  title="Kod Urusan">
                    ${line.permohonan.kodUrusan.kod}
                </display:column>
                <display:column  title="Nama Urusan">
                    ${line.permohonan.kodUrusan.nama}
                </display:column>
                <display:column  title="Nama Urusan">
                    ${line.permohonan.idPermohonan}
                </display:column>
            </display:table>
            <br>
            <legend>Maklumat Urusan Dalam Proses</legend>
            <br>
            <display:table class="tablecloth" name="${actionBean.listHakmilikPermohonan}" cellpadding="0" cellspacing="0" id="line">

                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                <display:column  title="Kod Urusan">
                    ${line.permohonan.kodUrusan.kod}
                </display:column>
                <display:column  title="Nama Urusan">
                    ${line.permohonan.kodUrusan.nama}
                </display:column>
                <display:column  title="Nama Urusan">
                    ${line.permohonan.idPermohonan}
                </display:column>
                <display:column  title="Status">
                    <c:if test="${line.permohonan.status eq 'TA'}">
                        Tunggu Ambil
                    </c:if>
                    <c:if test="${line.permohonan.status eq 'SL'}">
                        Selesai
                    </c:if>
                    <c:if test="${line.permohonan.status eq 'AK'}">
                        Aktif
                    </c:if>
                </display:column>

            </display:table>
        </fieldset>
    </div>
</s:form>