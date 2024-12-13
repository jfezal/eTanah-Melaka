<%-- 
    Document   : keputusan_bayarBaki
    Created on : Mar 17, 2011, 10:28:55 PM
    Author     : mdizzat.mashrom
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">

    function validate(){

        if($('#y').is(':checked') == false && $('#n').is(':checked') == false){
            alert("Sila Pilih Keputusan");
            return false;
        }
        return true;
    }




</script>
<s:form beanclass="etanah.view.stripes.lelong.KeputusanBidaanActionBean">

    <s:messages/>
    <s:errors/>&nbsp;

    <fieldset class="aras1">
        <legend>
            Maklumat Keputusan
        </legend>

        <c:if test="${actionBean.show eq false}">
            <p>
                <label><font color="red">*</font> Keputusan : </label>
                <s:radio id="y" name="kpsn" value="DB"/> Telah Jelas Baki Bidaan &nbsp;
                <s:radio id="n" name="kpsn" value="LB"/> Belum Jelas Baki Bidaan
            </p>
        </c:if>

        <c:if test="${actionBean.show eq true}">
            <p>
                <label><font color="red">*</font> Keputusan : </label>
                <s:radio id="y" name="kpsn" value="DB"/> Telah Jelas Baki Semua Hakmilik &nbsp;
                <s:radio id="n" name="kpsn" value="LB"/> Belum Dan Telah Jelas Baki Setiap Hakmilik
            </p>
        </c:if>

        <p>
            <label>Ulasan : </label>
            <s:textarea name="ulasan" rows="8" cols="60"/>
        </p><br>

        <p align="center">
            <s:button name="simpanKpsn2" value="Simpan" class="btn"  onclick="if(validate())doSubmit(this.form, this.name, 'page_div')"/>
        </p>
    </fieldset>
</s:form>
