<%-- 
    Document   : syarat_kelulusan_pasir_sungai
    Created on : May 15, 2010, 4:47:47 PM
    Author     : sitifariza.hanim
--%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.stripes.pelupusan.SyaratSuratKelulusanTanahActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend align="justify"><br>SYARAT-SYARAT PENGELUARAN PERMIT BAHAN BATUAN</legend>

            <p>
                <label>Tempoh Permit :</label>
                <s:text name="tempohPermit" size="20"/>
            </p>
            <p>
                <label>Kuantitii :</label>
                <s:text name="kuantiti" size="20"/>
            </p>
            <p>
                <label>Royalti (RM):</label>
                <s:text name="royalti" size="20" />
            </p>
             <p>
                 <label>Deposit Pejabat Tanah (RM) :</label>
                <s:text name="deposit" size="20"/>
            </p>
            <p>
                <label>Syarat kuatkuasa Permit :</label>
                <s:text name="syaratkuatkuasa" size="100"/>
            </p>
            <p>
                <label>Surat Akuan :</label>
                <s:text name="suratAkuan" size="100"/>
            </p>
             <p>
                <label>Syarat tambahan :</label>
                <s:text name="syaratTambahan" size="100"/>
            </p>
            <label>&nbsp;</label>
            <p>
                <s:reset name="" value="Isi Semula" class="btn"/>
                <s:button name="syaratBBPasirSungai" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </fieldset>
    </div>
</s:form>
