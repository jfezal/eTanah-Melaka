<%-- 
    Document   : pru_perakuan_MMK
    Created on : Dec 15, 2009, 1:56:33 PM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.pelupusan.PelupusanActionBean">
    <div class="subtitle">
        <p>PERAKUAN KERTAS KERJA MAJLIS MESYUARAT KERAJAAN</p>
        <fieldset class="aras1">
            <legend>
                Maklumat Pemohon
            </legend>
            <p>
            <div align="right">
                <tr:selectRangeChoiceBar first="5" rows="3" inlineStyle="float: right;"/>
            </div>
            <p>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="Bil."/>
                    <display:column title="Nama"/>
                    <display:column title="Jenis Pengenalan"/>
                    <display:column title="Nombor"/>
                    <display:column title="Alamat"/>
                    <display:column title="Nombor Telefon Rumah"/>
                    <display:column title="Nombor Telefon Pejabat"/>
                    <display:column title="Samb."/>
                    <display:column title="Nombor Telefon Bimbit"/>
                    <display:column title="Emel"/>
                </display:table>

            </p>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tanah Dimiliki
            </legend>
            <p>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="Bil."/>
                    <display:column title="ID Hakmilik"/>
                    <display:column title="Bandar/Pekan/Mukim"/>
                    <display:column title="Hakmilik"/>
                    <display:column title="Nombor Lot"/>
                    <display:column title="Keluasan"/>
                    <display:column title="Kategori Tanah"/>
                    <display:column title="Kod Syarat Nyata"/>
                    <display:column title="Kod Sekatan Kepentingan"/>
                    <display:column title="Emel"/>
                </display:table>

            </p>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Ringkasan Kertas Kerja Majlis Mesyuarat Kerajaan
            </legend>
            <h:panelGrid columns="2" styleClass="input" columnClasses="rowlabel2,aras0">
                <p>
                    <label>ID Permohonan :</label>
                    <s:text name=""/>
                </p>
            </h:panelGrid>
            <h:panelGrid columns="2" styleClass="input" columnClasses="rowlabel2,aras0">
                <p>
                    <label>Peruntukan Undang - Undang :</label>
                    <s:text name=""/>
                </p>
            </h:panelGrid>
            <h:panelGrid columns="2" styleClass="input" columnClasses="rowlabel2,aras0">
                <p>
                    <label>Perihal Permohonan :</label>
                    <s:text name=""/>
                </p>
            </h:panelGrid>
            <p>
                <label>Pemohon :</label>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="Nama"/>
                    <display:column title="Alamat"/>
                </display:table>
            </p>
            <%-- <p>
                 Tarikh-tarikh permohonan diproses :</p>
             <p>Permohonan oleh Pentadbir Tanah :</p>
             <p>Rujuk kepada Jabatan Teknikal :</p>
             <p>Surat-surat ulasan yang diterima :</p>--%>
            <p>
                <label>&nbsp;</label>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="Bil."/>
                    <display:column title="Proses"/>
                    <display:column title="Tarikh"/>
                </display:table>
            </p>
        </fieldset>
    </div>

    <div class="subtitle">
        <%-- <p>Perihal Tanah :</p>
         <p>Tanah ini terletak di ... . Keluasan yang terlibat ialah seluas 1.00 Meter Persegi untuk Lot 1
             Kegunaan lot-lot yang bersempadan dengannya adalah seperti berikut :
         </p>--%>
        <p>
            <label>Pemohon :</label>
        </p>
        <p>
            <display:table name="" id="line" class="tablecloth">
                <display:column title="Arah"/>
                <display:column title="Status Tanah Sekeliling"/>
                <display:column title="No. Lot/PT"/>
                <display:column title="Kategori"/>
                <display:column title="Kegunaan Semasa"/>
            </display:table>
        </p>
    </div>
    <div class="subtitle">
        <%--<p>Asas-asas Pertimbangan :</p>
        <p>Permohonan ini telah dirujuk kepada Jabatan-jabatan Teknikal. Ulasan jabatan-jabatan berkenaan
                adalah seperti berikut :
        </p>--%>
        <p>
            <label>Pemohon :</label>
            <display:table name="" id="line" class="tablecloth">
                <display:column title="Jabatan"/>
                <display:column title="Alamat"/>
            </display:table>
        </p>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Kertas Kerja Majlis Mesyuarat Kerajaan
            </legend>
            <p>
                <label>ID Permohonan :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Peruntukan Undang - Undang :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Tujuan :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Latarbelakang Permohonan :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Perihal Tanah :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>&nbsp;</label>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="Arah"/>
                    <display:column title="Status Tanah Sekeliling"/>
                    <display:column title="No. Lot/PT"/>
                    <display:column title="Kategori"/>
                    <display:column title="Kegunaan Semasa"/>
                </display:table>
            </p>
        </fieldset>
    </div>
    <p>
        <label>&nbsp;</label>
        <s:submit name="" value="Keluar" class="btn"/>
        <s:submit name="" value="Simpan" class="btn"/>
        <s:submit name="" value="Hantar" class="btn"/>
        <s:submit name="" value="Papar Kertas Kerja" class="btn"/>
        <s:submit name="" value="Papar Kertas Ringkasan" class="btn"/>
    </p>


</s:form>
