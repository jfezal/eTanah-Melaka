<%-- 
    Document   : borang_pengadu
    Created on : Jan 18, 2010, 4:10:13 PM
    Author     : farah.shafilla
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form  beanclass="etanah.view.penguatkuasaan.MaklumatPengaduActionBean">
<s:messages/>
    <div class="subtitle">

        <fieldset class="aras1">
            <legend>Tempat Aduan</legend>

           <p>
                <label for="ID Permohonan">ID Permohonan :</label>
                ${actionBean.permohonan.idPermohonan}&nbsp;
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
                <label>Cara Aduan :</label>
                &nbsp;
            
                   <s:select name="caraPermohonan.kod" value="${actionBean.permohonan.caraPermohonan.kod}">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodCaraPermohonan}" label="nama" value="kod" />
                </s:select>&nbsp;

            </p>
            <p>
                <label>Peruntukan Undang-undang :</label>
                ${actionBean.permohonan.kodUrusan.nama}&nbsp;
            </p>
            <p>
                <label>Ringkasan Aduan :</label>
            <s:textarea name="permohonan.sebab" rows="5" cols="50" />&nbsp;
            </p>
        </fieldset >
    <br>
        <fieldset class="aras1">
            <legend>Maklumat Pengadu</legend>
            <p>
                <label>Nama :</label>
                ${actionBean.permohonan.penyerahNama}&nbsp;
            </p>
            <p>
                <label>Jenis Pengenalan :</label>
              ${actionBean.permohonan.penyerahJenisPengenalan.nama}&nbsp;
            </p>
            <p>
                <label>No.Pengenalan :</label>
                ${actionBean.permohonan.penyerahNoPengenalan}&nbsp;
            </p>

            <p>
                <label>Alamat :</label>
                ${actionBean.permohonan.penyerahAlamat1}&nbsp;
            </p>
            <p>
                <label> &nbsp;</label>
                ${actionBean.permohonan.penyerahAlamat2}&nbsp;
            </p>
            <p>
                <label> &nbsp;</label>
                ${actionBean.permohonan.penyerahAlamat3}&nbsp;
            </p>
            <p>
                <label> &nbsp;</label>
                ${actionBean.permohonan.penyerahAlamat4}&nbsp;
            </p>
            <p>
                <label>Poskod :</label>
                ${actionBean.permohonan.penyerahPoskod}&nbsp;
            </p>
            <p>
                <label>Negeri :</label>
                ${actionBean.permohonan.penyerahNegeri.nama}&nbsp;
            </p>
            <p>
                <label>No.Telefon :</label>
                ${actionBean.permohonan.penyerahNoTelefon1}&nbsp;
            </p>
            <p>
                <label>Email :</label>
                ${actionBean.permohonan.penyerahEmail}&nbsp;
            </p>
        </fieldset>

            <p align="right">
                <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan"/>
            </p>
    </div>
</s:form>

