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
    $(document).ready(function() {

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
        }
        else if (resultCase == "T") {
            if (kodUrusan == 'JMRE') {
                $('#page_id_006').hide();//hide tab draf MMK
            }
        }

    });
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form  beanclass="etanah.view.stripes.common.MaklumatPermohonanActionBean">
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
            <p>
                <label for="Permohonan">Permohonan :</label>
                <font style="text-transform:uppercase;"> ${actionBean.permohonan.kodUrusan.nama}</font>&nbsp;
            </p>
            <p>
                <label for="ID Permohonan">ID Permohonan :</label>
                ${actionBean.permohonan.idPermohonan}&nbsp;
            </p>
        </fieldset>
    </div>

    <%---------------FOR URUSAN PMMAT CONSENT------------------%>
    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMMAT' && edit}">
        <br/>
        <div class="subtitle">
            <fieldset class="aras1">
                <s:hidden name="permohonanRujukanLuar.idRujukan"/>
                <legend>Maklumat Perintah</legend>
                <p>
                    <label> <font color="red">*</font>Jenis Perintah :</label>
                            <s:select name="permohonanRujukanLuar.catatan">
                                <s:option value="">Sila Pilih</s:option>
                        <s:option>PERINTAH PENTADBIR TANAH</s:option>
                        <s:option>PERINTAH MAHKAMAH</s:option>
                    </s:select>
                </p>
                <p>
                    <label><font color="red">*</font>Tarikh Perintah :</label>
                            <s:text name="tarikhPerintah" id="datepicker" class="datepicker"/>
                </p>
                <p>
                    <label><font color="red">*</font>Nombor Perintah :</label>
                            <s:text name="permohonanRujukanLuar.noRujukan" size="20"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpanPermohonanRujukanLuar" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </fieldset>
        </div>
    </c:if>

    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMMAT' && !edit}">
        <br/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Perintah</legend>
                <p>
                    <label>Jenis Perintah :</label>
                    ${actionBean.permohonanRujukanLuar.catatan}
                </p>
                <p>
                    <label>Tarikh Perintah :</label>
                    ${actionBean.tarikhPerintah}
                </p>
                <p>
                    <label>Nombor Perintah :</label>
                    ${actionBean.permohonanRujukanLuar.noRujukan}
                </p>

            </fieldset>
        </div>
    </c:if>

    <%---------------FOR URUSAN BANTAHAN TANAH ADAT------------------%>
    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BTADT' && edit}">
        <br/>
        <div class="subtitle">
            <fieldset class="aras1">
                <s:hidden name="permohonanUrusan.idUrusan"/>
                <legend>Maklumat Permohonan Terdahulu</legend>
                <p>
                    <label> <font color="red">*</font>Nama Permohonan :</label>
                            <s:select name="permohonanUrusan.catatan">
                                <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodUrusanBantahanTanahAdat}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpanPermohonanUrusan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BTADT' && !edit}">
        <br/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Permohonan Terdahulu</legend>
                <p>
                    <label>Nama Permohonan :</label>
                    <font style="text-transform:uppercase;"> ${actionBean.kodUrusan.nama}</font>&nbsp;
                </p>
            </fieldset>
        </div>
    </c:if>
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
