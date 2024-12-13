<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<s:form beanclass="etanah.view.stripes.consent.KemasukanUlasanActionBean">
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Permohonan</legend>
            <p>
                <label>Urusan :</label>
                ${actionBean.permohonan.kodUrusan.nama}&nbsp;
            </p>
            <p>
                <label>ID Permohonan :</label>
                ${actionBean.permohonan.idPermohonan}&nbsp;
            </p>
            <p>
                <label>ID Hakmilik :</label>
                ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.idHakmilik}&nbsp;
            </p>
        </fieldset>
    </div>
    <br/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                <c:if test="${actionBean.pengguna.perananUtama.kod eq '56'}">Jabatan Pertanian Negeri Melaka</c:if>
                <c:if test="${actionBean.pengguna.perananUtama.kod eq '57'}">Jabatan Tenaga Kerja Negeri Melaka</c:if>
            </legend>
            <p>
                <label>Ulasan :</label>
                <s:textarea name="ulasan"  rows="8" cols="90" class="normal_text"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </fieldset>
    </div>

</s:form>

