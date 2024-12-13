<%-- 
    Document   : syarat_kelulusan_bbrumput
    Created on : May 15, 2010, 4:48:55 PM
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
                <label>Surat Akuan :</label>
                <s:text name="suratAkuan" size="110"/>
            </p>
            <p>
                <label>Tempoh Permit :</label>
                <s:text name="tempohPermit" size="50"/>
            </p>
            <p>
                <label>Kuantiti :</label>
                <s:text name="kuantiti" size="50"/>
            </p>
            <p>
                <label>Royalti (RM):</label>
                <s:text name="royalti" size="50" />
            </p>
            <label>&nbsp;</label>
            <p>
                <s:reset name="" value="Isi Semula" class="btn"/>
                <s:button name="syaratBBRumput" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </fieldset>
    </div>
</s:form>
