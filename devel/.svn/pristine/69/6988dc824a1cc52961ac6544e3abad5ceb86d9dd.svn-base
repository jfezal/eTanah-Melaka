<%-- 
    Document   : carian_tutupe_kertas_siasatan
    Created on : Apr 18, 2014, 9:55:46 AM
    Author     : MohammadHafifi
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script language="javascript" type="text/javascript">
    function validateForm2(){

        if(document.getElementById("idPermohonanCarian").value == ""){
            alert("Sila masukkan Id Permohonan yang dikehendaki");
            $('#idPermohonanCarian').focus();
            return false;
        }

        return true;
    }
</script>
<s:form beanclass="etanah.view.penguatkuasaan.utiliti.UtilitiTutupKertasSiasatan">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian Tutup Kertas Siasatan</legend>
            <p><label for="idPermohonan"><em>*</em>Id Permohonan:</label>
                <input type="text" name="idPermohonan" id="idPermohonanCarian" onkeyup="this.value=this.value.toUpperCase();"/>
                <s:submit name="searchNoSerahan" value="Cari" class="btn" onclick="return validateForm2()"/>
            </p>
        </fieldset>
    </div>

    <c:if test="${actionBean.permohonan ne null}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Permohonan</legend>
                <p>
                    <label for="ID Permohonan">ID Permohonan :</label>
                    ${actionBean.permohonan.idPermohonan}&nbsp;
                    <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                </p>
                <p>
                    <label>Tempat :</label>
                    ${actionBean.permohonan.cawangan.name}&nbsp;
                </p>
                <p>
                    <label>Tarikh :</label>
                    <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>&nbsp;
                </p>
                <p>
                    <label>Daerah :</label>
                    ${actionBean.permohonan.cawangan.kod} - ${actionBean.permohonan.cawangan.daerah.nama}&nbsp;

                </p>
                <p>
                    <label>Alamat :</label>
                    ${actionBean.permohonan.cawangan.alamat.alamat1}&nbsp;
                </p>
                <p>
                    <label> &nbsp;</label>
                    ${actionBean.permohonan.cawangan.alamat.alamat2}&nbsp;
                </p>
                <p>
                    <label> &nbsp;</label>
                    ${actionBean.permohonan.cawangan.alamat.alamat3}&nbsp;
                </p>
                <p>
                    <label> &nbsp;</label>
                    ${actionBean.permohonan.cawangan.alamat.alamat4}&nbsp;
                </p>
                <p>
                    <label>Poskod :</label>
                    ${actionBean.permohonan.cawangan.alamat.poskod}&nbsp;
                </p>
                <p>
                    <label>Negeri :</label>
                    ${actionBean.permohonan.cawangan.alamat.negeri.nama}&nbsp;
                </p>
                <p>
                    <label>Cara Aduan :</label>
                    ${actionBean.permohonan.caraPermohonan.nama}&nbsp;
                </p>
                <p>
                    <label>Seksyen KTN :</label>
                    ${actionBean.permohonan.kodUrusan.nama}&nbsp;
                </p>
                <c:if test="${actionBean.permohonan.keputusan ne null}">
                    <p>
                        <label>Keputusan :</label>
                        <strong>${actionBean.permohonan.keputusan.nama}&nbsp;</strong>
                    </p>
                    <p>
                        <label>Keputusan Oleh :</label>
                        <strong>${actionBean.permohonan.keputusanOleh.nama}&nbsp;</strong>
                    </p>
                    <p>
                        <label>Tarikh Keputusan :</label>
                        ${actionBean.permohonan.tarikhKeputusan}&nbsp;
                    </p>
                </c:if>
                <p>
                    <label>Status Permohonan :</label>
                    <c:choose>
                        <c:when test="${actionBean.permohonan.status eq 'AK'}">
                            Aktif
                        </c:when>
                        <c:when test="${actionBean.permohonan.status eq 'SL'}">
                            Selesai
                        </c:when>
                        <c:when test="${actionBean.permohonan.status eq 'TA'}">
                            Belum Diambil Tugasan
                        </c:when>
                    </c:choose>
                </p>
                <c:if test="${actionBean.permohonan.status ne 'SL'}">
                    <p>
                        <label>Ulasan Tutup Kes :</label>
                        <s:textarea name="ulasan" rows="5" cols="50" class="normal_text"/>
                    </p>
                    <p align="center">
                        <s:submit name="simpanTutupKes" id="save"  value="Tutup Kes" class="btn"/>
                    </p>
                </c:if>
            </fieldset>
        </div>
    </c:if>
</s:form>
