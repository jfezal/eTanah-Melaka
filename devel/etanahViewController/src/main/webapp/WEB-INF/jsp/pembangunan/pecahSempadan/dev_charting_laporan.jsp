<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:form beanclass="etanah.view.stripes.pembangunan.LaporanPelukisPelanActionBean">
<s:messages/>
<s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Laporan Pelukis Pelan
            </legend>
            <p>
                <label>Jenis Pemilikan Tanah :</label>
                <s:select name="ff">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodPemilikan}" label="nama" value="kod" />
                </s:select>
            </p>
            <p>
                <label>Jenis Tanah :</label>
                <s:select name="ff">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodTanah}" label="nama" value="kod" />
                </s:select>
            </p>
            <p>
                <label>Tanah Simpanan Melayu :</label>
                <s:radio name="melayu" value="Y"/>Ya&nbsp;
                <s:radio name="melayu" value="Tidak"/>Tidak
            </p>
            <p>
                <label>Rizab Hutan Kekal :</label>
                <s:radio name="hutan" value="Y"/>Ya&nbsp;
                <s:radio name="hutan" value="T"/>Tidak
            </p>
            <p>
                <label>Tanah GSA :</label>
                <s:radio name="gsa" value="Y"/>Ya&nbsp;
                <s:radio name="gsa" value="T"/>Tidak
            </p>
            <p>
                <label>Permohonan Terdahulu  :</label>
                <s:radio name="terdahulu" value="Y"/>Ada&nbsp;
                <s:radio name="terdahulu" value="T"/>Tiada
            </p>
            <p>
                <label>Lain-lain Hal :</label>
                <s:textarea name ="" rows="5" cols="70" />
            </p>
            <p>
                <label>&nbsp;</label>
                <s:button name="simpan" value="Simpan" class="btn"/>
                <s:button name="charting" value="Charting" class="btn"/>
            </p>
        </fieldset>
    </div>
</s:form>