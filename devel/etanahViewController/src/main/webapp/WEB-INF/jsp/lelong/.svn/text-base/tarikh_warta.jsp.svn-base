<%--
    Document   : tarikh_warta
    Created on : Mar 24, 2011, 7:40:48 PM
    Author     : mr.Bean
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
    function validate(){

        if($('#tarikhWarta').val() == null){
            alert("Sila Masukkan No.Rujukan PUNM");
            return false;
        }
        return true;
    }
</script>
<s:form beanclass="etanah.view.stripes.lelong.Borang16HViewActionBean">

    <s:messages/>
    <s:errors/>&nbsp;

    <fieldset class="aras1">
        <legend>
            No Rujukan PUNM
        </legend>

        <p>
            <label><font color="red">*</font> No.Rujukan PUNM : </label>
            <s:text id="tarikhWarta" name="tarikhWarta" style="width:240px;"/>
        </p>
        <br>
        <p align="center">
            <s:button name="simpanTarikh" value="Simpan" class="btn"  onclick="if(validate())doSubmit(this.form, this.name, 'page_div')"/>
        </p>
    </fieldset>
</s:form>