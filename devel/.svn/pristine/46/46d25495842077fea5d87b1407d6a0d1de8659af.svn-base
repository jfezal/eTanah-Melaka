<%-- 
    Document   : no_pumm_2A
    Created on : May 16, 2011, 11:24:24 AM
    Author     : mdizzat.mashrom
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
    function validate(){

        if($('#noRuj').val() == null){
            alert("Sila Masukkan No.Rujukan PUNS");
            $('#noRuj').focus();
            return false;
        }
        return true;
    }
</script>
<s:form beanclass="etanah.view.stripes.lelong.NoRujPunmActionBean">

    <s:messages/>
    <s:errors/>&nbsp;

    <fieldset class="aras1">
        <legend>
            No Rujukan PUNS
        </legend>

        <p>
            <label><font color="red">*</font> No.Rujukan PUNS : </label>
            <s:text id="noRuj" name="noRuj" style="width:240px;"/>
        </p>
        <br>
        <p align="center">
            <s:button name="simpanNoRuj" value="Simpan" class="btn"  onclick="if(validate())doSubmit(this.form, this.name, 'page_div')"/>
        </p>
    </fieldset>
</s:form>