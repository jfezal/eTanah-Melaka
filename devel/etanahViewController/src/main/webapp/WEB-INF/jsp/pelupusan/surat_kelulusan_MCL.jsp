<%-- 
    Document   : surat_kelulusan_MCL
    Created on : Aug 23, 2010, 12:24:58 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.stripes.pelupusan.suratKelulusanMCLActionBean">
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
           
            <p>I. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <b><u>Syarat-syarat Hakmilik:-</u></b></p><br>
            <table border="0">
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>Jenis Hakmilik</td>
                    <td>:</td>
                    <td>Tanah Adat Melaka(MCL)</td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>Tempoh</td>
                    <td>:</td>
                    <td>Kekal</td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>Penjenisan</td>
                    <td>:</td>
                    <td>${actionBean.hakmilik.kategoriTanah.nama}</td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>Syarat Nyata</td>
                    <td>:</td>
                    <td>${actionBean.hakmilik.syaratNyata.syarat}</td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>Sekatan Kepentingan</td>
                    <td>:</td>
                    <td>${actionBean.hakmilikPermohonan.sekatanKepentingan.sekatan}</td>
                </tr>
            </table><br>
             <p>II &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <b><u>Syarat Am:-</u></b></p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Tanah Adat Melaka.</p>
            <br>
            <br>
            <p>III &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <b><u>Bayaran Catitera:-</u></b></p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; RM 30.00</p>
            <br>
            <br>
            <p>IV &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <b><u>Bayaran Proses Permohonan Tanah:-</u></b></p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; RM 50.00</p>
            <br>
            <center>
<!--                
                <s:button name="simpan" id="save" class="btn" value="Simpan" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                -->
            </center>


        </fieldset>


    </div>

</s:form>
