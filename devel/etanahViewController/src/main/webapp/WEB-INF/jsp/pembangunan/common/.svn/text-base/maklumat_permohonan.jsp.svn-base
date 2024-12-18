<%--
    Document   : maklumat_Permohonan
    Created on : Oct 22, 2009, 5:13:38 PM
    Author     : muhammad.amin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
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

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form  beanclass="etanah.view.stripes.pembangunan.MaklumatPermohonanActionBean">
    <s:errors/>
    <s:messages/>
    <s:hidden name="kodKeputusan.kod" id="keputusanId" />
    <s:hidden name="kodKeputusan.nama" id="keputusanName" />
    <s:hidden name="kodNegeri" id="kodNegeri"/>
    <s:hidden name="stageId" id="stageId"/>
    <s:hidden name="permohonan.kodUrusan.kod" id="kodUrusan"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Permohonan</legend>
            <span style="color:red;font-size: 8pt">Arahan: Sila Pastikan Dokumen Surat Kuasa Wakil dan Surat Semakan Pelan telah dimuatnaik pada Tab Dokumen untuk urusan SBMS sekiranya berkaitan</span>
            <p></p>
            <p></p>
            <p>
                <label for="Permohonan">Permohonan :</label>
                <font style="text-transform:uppercase;"> ${actionBean.permohonan.kodUrusan.nama}</font>&nbsp;
            </p>
            <p>
                <label for="ID Permohonan">ID Permohonan :</label>
                ${actionBean.permohonan.idPermohonan}&nbsp;
            </p>
            <c:if test="${actionBean.idDokumenSW !=0}">
                <p>
                    <label for="No Surat Kuasa Wakil">No Surat Kuasa Wakil :</label>
                <s:text name="noSuratKuasaWakil" id="noSuratKuasaWakil" />
            </p></c:if>
            <c:if test="${actionBean.idDokumenSPSP !=0}">

                <p>
                    <label for="No Surat Semak Pelan">No Surat Semak Pelan :</label>
                <s:text name="noSuratSemakPelan" id="noSuratSemakPelan" />
            </p></c:if>
            <!--            <p>
                            <label for="Surat Kuasa Wakil">Permohonan berserta lanjutan tempoh (90A) :</label>
            <s:select name="lanjutTempoh" style="width:100px" id="lanjutTempoh">
                <s:option value="T">Sila Pilih</s:option>
                <s:option value="Y">Ya</s:option>
                <s:option value="T">Tidak</s:option>
            </s:select>            </p>-->
            <c:if test="${actionBean.idDokumenSW !=0 || actionBean.idDokumenSPSP !=0}">
                <p><label>&nbsp;</label>            <s:button name="simpan" onclick="doSubmit(this.form, this.name, 'page_div');" id="simpan" value="Simpan" class="longbtn"/>
                </c:if>
            </p>      </fieldset>
    </div>
    <br/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Penyerah</legend>
            <p>
                <label>Nama :</label>
                <font style="text-transform:uppercase;"> ${actionBean.permohonan.penyerahNama}</font>&nbsp;
            </p>
            <p>
                <label>Jenis Penyerah :</label>
                <font style="text-transform:uppercase;">
                    <c:if test="${actionBean.permohonan.kodPenyerah.nama ne null}"> ${actionBean.permohonan.kodPenyerah.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonan.kodPenyerah eq null && actionBean.permohonan.penyerahJenisPengenalan ne null}">
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'B'}"> INDIVIDU </c:if><%--K/P Baru --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'D'}"> SYARIKAT </c:if><%-- Pendaftaran--%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'F'}">  INDIVIDU</c:if><%-- Paksa--%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'I'}">  INDIVIDU</c:if><%--Polis --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'L'}"> INDIVIDU </c:if><%-- K/P Lama--%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'N'}"> SYARIKAT </c:if><%--Bank --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'P'}"> INDIVIDU </c:if><%--Pasport --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'S'}"> SYARIKAT </c:if><%--Syarikat --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'U'}"> PERTUBUHAN </c:if><%--Pertubuhan --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'X'}"> TIADA </c:if><%-- Tiada --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'Z'}"> Badan Kerajaan </c:if><%--Badan Kerajaan --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'O'}"> Tidak Berkenaan </c:if><%--Tidak Berkenaan --%>
                    </c:if>
                </font>
            </p>
            <c:if test="${actionBean.permohonan.penyerahAlamat1 ne null}">
                <p>
                    <label>Alamat :</label>
                    <font style="text-transform:uppercase;"> ${actionBean.permohonan.penyerahAlamat1}</font>&nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.penyerahAlamat2 ne null}">
                <p>
                    <label> &nbsp;</label>
                    <font style="text-transform:uppercase;">${actionBean.permohonan.penyerahAlamat2}</font> &nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.penyerahAlamat3 ne null}">
                <p>
                    <label> &nbsp;</label>
                    <font style="text-transform:uppercase;"> ${actionBean.permohonan.penyerahAlamat3} </font> &nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.penyerahAlamat4 ne null}">
                <p>
                    <label>Bandar :</label>
                    <font style="text-transform:uppercase;">${actionBean.permohonan.penyerahAlamat4}</font>&nbsp;
                </p>
            </c:if>
            <p>
                <label>Poskod :</label>
                ${actionBean.permohonan.penyerahPoskod}&nbsp;
            </p>
            <p>
                <label>Negeri :</label>
                <font style="text-transform:uppercase;">${actionBean.permohonan.penyerahNegeri.nama} </font>&nbsp;
            </p>
        </fieldset>
    </div>
</s:form>
