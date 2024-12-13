<%-- 
    Document   : surat_kelulusan_RAYT
    Created on : Aug 26, 2010, 4:51:13 PM
    Author     : sitifariza.hanim
--%>

<%@include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pelupusan.SuratKelulusanRAYTActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Syarat - Syarat Hakmilik 
            </legend>
            <p>
                <label>Jenis Hakmilik :</label>
                <s:select name="kodHakmilik.kod" value="${actionBean.hakmilikPermohonan.kodHakmilik.kod}">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${actionBean.senaraiKodHakmilik}" label="nama" value="kod"/>
                </s:select>
            </p> 
            <p>
                <label>Tempoh :</label>
                <s:text name="hakmilikPermohonan.tempohHakmilik" id="tempoh" size="10"/> &nbsp; Tahun
            </p>
            <p>
                <label>Hasil (RM):</label>
                <s:text name="hakmilikPermohonan.cukaiPerMeterPersegi" size="10"/>&nbsp; 100 meter persegi atau sebahagian daripadanya.
            </p>
            <p>
                <label>Bayaran Ukur :</label> &nbsp;Mengikut P.U.(A)483
            </p>
            <p>
                <label>Jenis Penggunaan Tanah :</label>
                <s:select name="kegunaanTanah.kod" value="${actionBean.hakmilikPermohonan.kegunaanTanah.kod}" style="width:500px" >
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${actionBean.senaraiKegunaanTanah}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label>Syarat Nyata :</label>
                <s:select name="syaratNyata.kod" value="${actionBean.hakmilikPermohonan.syaratNyata.kod}" style="width:500px">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${actionBean.senaraiKodSyaratNyata}" label="syarat" value="kod"/>
                </s:select>
            </p>
            <p>
                <label>Sekatan Kepentingan :</label>
                <s:select name="sekatanKepentingan.kod" value="${actionBean.hakmilikPermohonan.sekatanKepentingan.kod}" style="width:500px">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${actionBean.senaraiKodSekatan}" label="sekatan" value="kod"/>
                </s:select>
            </p>

        </fieldset>
    </div>
    <div>
        <p>
            <label>&nbsp;</label>

            <s:button name="simpan" id="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>


        </p>
    </div>
</s:form>

