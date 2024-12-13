<%-- 
    Document   : tggh_kpsn_lantik_jurulelong
    Created on : Mar 24, 2011, 7:40:48 PM
    Author     : mdizzat.mashrom
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">

    function validate(){

        if($('#y').is(':checked') == false && $('#n').is(':checked') == false && $('#k').is(':checked') == false){
            alert("Sila Pilih Keputusan");
            return false;
        }
        return true;
    }




</script>
<s:form beanclass="etanah.view.stripes.lelong.MemasukkanMaklumatJurulelongBerlesenActionBean">

    <s:messages/>
    <s:errors/>&nbsp;

    <fieldset class="aras1">
        <legend>
            Maklumat Keputusan
        </legend>

        <p>
            <label><font color="red">*</font> Jurulelong : </label>
            <s:radio id="y" name="kpsn" value="PH"/> Pentadbir Tanah &nbsp;
            <s:radio id="n" name="kpsn" value="JL"/> Jurulelong Berlesen &nbsp;
        </p>

        <p>
            <label>Ulasan : </label>
            <s:textarea name="ulasan" rows="8" cols="60"/>
        </p><br>

        <p align="center">
            <s:button name="simpanKpsnPPTL" value="Simpan" class="btn"  onclick="if(validate())doSubmit(this.form, this.name, 'page_div')"/>
        </p>
    </fieldset>
</s:form>