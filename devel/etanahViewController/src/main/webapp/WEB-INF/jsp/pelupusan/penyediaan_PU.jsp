<%-- 
    Document   : penyediaan_PU
    Created on : Dec 15, 2009, 10:05:32 AM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.pelupusan.PelupusanActionBean">
    <div class="subtitle">
        <p>PENGESAHAN PERMOHONAN UKUR PELAN</p>

        <fieldset class="aras1">
            <legend>
                Permohonan
            </legend>
            <p>
                <label>ID Permohonan :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Tujuan :</label>
                <s:text name=""/>
            </p>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pemohon
            </legend>
            <p>
                <label>Nama :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Jenis Pengenalan :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>No. Kad Pengenalan :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Alamat :</label>
                <s:textarea name=""rows="5" cols="70"/>
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Bandar :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Negeri :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Nombor Telefon (Rumah) :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Nombor Telefon (Pejabat) :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Nombor Telefon (Bimbit) :</label>
                <s:text name=""/>
            </p>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Permohonan Pelan
            </legend>
            <p>
                <label>Nombor Permintaan Ukur :</label>
                <s:text name=""/>
            </p>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pelan B2
            </legend>
            <p>
                <label>&nbsp;</label>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="No."/>
                    <display:column title="No. Lot/PT"/>
                    <display:column title="Keluasan"/>
                    <display:column title="ID Dokumen"/>
                </display:table>
            </p>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pelan JUPEM
            </legend>
            <p>
                <label>Bilangan Lot :</label>
                &nbsp;
            </p>
            <p>
                <label>&nbsp;</label>
                <display:table name="" id="line" class="tablecloth">
                    <display:column title="No."/>
                    <display:column title="No. Lot/PT"/>
                    <display:column title="Keluasan"/>
                    <display:column title="No. Pelan Akui"/>
                    <display:column title="Imbas Lot Pelan B1"/>
                    <display:column title="ID Dokumen"/>
                </display:table>
            </p>
            <p>
                <label>Nombor Rujukan JUPEM :</label>
                &nbsp;
            </p>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Catatan JUPEM
            </legend>
            <p>
                <label>Huraian :</label>
                <s:textarea name=""rows="5" cols="70"/>
            </p>
        </fieldset>
        <p>
            <label>&nbsp;</label>
            <s:submit name="" value="Simpan" class="btn"/>
            <s:submit name="" value="Hantar" class="btn"/>
            <s:reset name="" value="Isi Semula" class="btn"/>
            <s:submit name="" value="Keluar" class="btn"/>
        </p>
    </div>
</s:form>