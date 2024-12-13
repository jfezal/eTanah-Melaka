<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
    $(document).ready(function () {

        var kodNegeri = $('#kodNegeri').val();
        var stageId = $('#stageId').val();
        var kodUrusan = $('#kodUrusan').val();
        var resultCase = $('#keputusanKes').val();

        if (kodNegeri == '04' && (stageId == '17ArahanMaklumanKpsn')) {
            if (kodUrusan == 'PTPT') {
                if ($("#keputusanId").val() == 'PV') {
                    $(".warning").html("Arahan : Terdapat " + $("#keputusanName").val() + ". Sila rujuk Tab Laporan Kerosakan Tanah");
                } else {
                    $(".warning").html("Arahan : " + $("#keputusanName").val() + ". Tiada perubahan");
                }
            }
        } else if (resultCase == "T") {
            if (kodUrusan == 'JMRE') {
                $('#page_id_006').hide();//hide tab draf MMK
            }
        }

    });
</script>
<s:form beanclass="etanah.view.stripes.pembangunan.MaklumatPermohonanActionBean">
    <div class="subtitle">

        <fieldset class="aras1">
            <legend>Maklumat Perserahan</legend>
            <p>
                <label for="Permohonan">Perserahan :</label>
                ${actionBean.permohonan.kodUrusan.nama}&nbsp;
            </p>
            <p>
                <label for="ID Permohonan">ID Perserahan :</label>
                ${actionBean.permohonan.idPermohonan}&nbsp;
            </p>
            <p>
                <label for="ID Permohonan">Sebab-Sebab :</label>
                ${actionBean.permohonan.sebab}&nbsp;
            </p>
            <p>
                <label for="ID Permohonan">Keterangan-Keterangan Lain :</label>
                ${actionBean.permohonan.catatan}&nbsp;
            </p>
            <c:if test="${actionBean.idDokumenSW !=0}">
                <p>
                    <label for="No Surat Kuasa Wakil">No Surat Kuasa Wakil :</label>
               ${actionBean.noSuratKuasaWakil}&nbsp; 
            </p></c:if>
            <c:if test="${actionBean.idDokumenSPSP !=0}">

                <p>
                    <label for="No Surat Semak Pelan">No Surat Semak Pelan :</label>
                ${actionBean.noSuratSemakPelan}&nbsp;
            </p></c:if>
        </fieldset >
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Penyerah</legend>
            <p>
                <label for="Nama">Nama :</label>
                ${actionBean.permohonan.penyerahNama}&nbsp;
            </p>
            <p>
                <label for="Nama">Nombor Rujukan Penyerah :</label>
                ${actionBean.permohonan.penyerahNoRujukan}&nbsp;
            </p>
            <%--            <p>
                            <label for="Jenis Pengenalan">Jenis Pengenalan :</label>
                            &nbsp;
                        </p>
                        <p>
                            <label for="Nombor Pengenalan">Nombor Pengenalan :</label>
                            &nbsp;
                        </p>
                        <p>
                            <label for="Bangsa">Bangsa :</label>
                            &nbsp;
                        </p>--%>
            <p>
                <label for="Alamat">Alamat :</label>
                ${actionBean.permohonan.penyerahAlamat1}&nbsp;
            </p>
            <p>
                <label>&nbsp;</label>
                ${actionBean.permohonan.penyerahAlamat2}&nbsp;
            </p>
            <p>
                <label>&nbsp;</label>
                ${actionBean.permohonan.penyerahAlamat3}&nbsp;
            </p>
            <p>
                <label>Bandar :</label>
                ${actionBean.permohonan.penyerahAlamat4}&nbsp;
            </p>
            <p>
                <label for="Poskod">Poskod :</label>
                ${actionBean.permohonan.penyerahPoskod}&nbsp;
            </p>
            <p>
                <label for="Poskod">Negeri :</label>
                ${actionBean.permohonan.penyerahNegeri.nama}&nbsp;
            </p>           
        </fieldset>
    </div>
    <%--<c:if test="${pendaftar}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Pembetulan</legend>
                <p>
                    <label>Nota Pembetulan :</label>
                    ${actionBean.mohon.catatan} &nbsp;${actionBean.mohon.sebab}
                </p>
            </fieldset>
        </div>
    </c:if>--%>
</s:form>
