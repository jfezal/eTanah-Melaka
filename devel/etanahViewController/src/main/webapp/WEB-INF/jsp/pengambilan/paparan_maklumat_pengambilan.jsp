<%-- 
    Document   : paparan_maklumat_pengambilan
    Created on : 13-Jan-2010, 14:04:56
    Author     : nordiyana
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.stripes.pengambilan.MaklumatPengambilanActionBean">
    <div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Jana Permohonan
        </legend>

    <%--<div class="content" align="center">--%>
        <p>
            <label for="Maksud_Pengambilan">Tajuk Permohonan:</label>
           <s:textarea name="tajuk_pengambilan" rows="8" cols="100">  </s:textarea>
        </p>
        <p>
            <label for="Maksud_Pengambilan">&nbsp;</label>
        <s:submit name="searchAllPermohonan" value="Jana Tajuk" class="btn"/>
        </p>

            <br/>
            <%--</div>--%>

    </fieldset>
    </div>
    <div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Maklumat Pengambilan
        </legend>
             <p>
                <label>No Rujukan Surat :</label>
                ${actionBean.permohonanRujukanLuar.noRujukan}&nbsp;
            </p>
            <p>
                <label>Tarikh Surat Memohon :</label>
                 <s:format formatPattern="dd/MM/yyyy" value="${actionBean.permohonanRujukanLuar.tarikhRujukan}" />&nbsp;
            </p>
            <p>
                <label>Maksud Pengambilan :</label>
                ${actionBean.permohonanRujukanLuar.permohonan.sebab}&nbsp;
            </p>
                       <p>
                <label>Setelah Pengambilan Semula :</label>
                ${actionBean.permohonanPengambilan.selepasPengambilan}&nbsp;
            </p>
            <br/>

    </fieldset>
</div>


    </s:form>
