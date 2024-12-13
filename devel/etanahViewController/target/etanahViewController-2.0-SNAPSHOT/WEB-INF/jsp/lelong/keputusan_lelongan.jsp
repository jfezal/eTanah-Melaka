<%-- 
    Document   : keputusan_lelongan
    Created on : Mar 17, 2011, 9:31:23 PM
    Author     : mdizzat.mashrom
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">

    function validate(){

        if(($("#Y").is(':checked') == false) && ($("#N").is(':checked') == false)){
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
            <c:if test="${actionBean.disable eq true}">
                <p>
                    <label><font color="red">*</font> Keputusan : </label>
                    <input type="radio" id="Y" name="kpsn" value="AP" disabled/> Ada Pembida &nbsp;
                    <input type="radio" id="N" name="kpsn" value="TB" disabled/> Tiada Pembida
                </p>     
            </c:if>
            <c:if test="${actionBean.disable eq false}">
                <p>
                    <label><font color="red">*</font> Keputusan : </label>
                    <input type="radio" id="Y" name="kpsn" value="AP" /> Ada Pembida &nbsp;
                    <input type="radio" id="N" name="kpsn" value="TB"/> Tiada Pembida
                </p>     
            </c:if>

        </c:if>

        <c:if test="${actionBean.show eq true}">
            <c:if test="${actionBean.disable eq true}">
                <p>
                    <label><font color="red">*</font> Keputusan : </label>
                    <input type="radio" id="Y" name="kpsn" value="AP" disabled/> Ada Pembida/Tarik Permohonan Untuk Setiap Hakmilik &nbsp;
                    <input type="radio" id="N" name="kpsn" value="TB" disabled/> Ada Dan Tiada Pembida Untuk Setiap Hakmilik
                </p>   
            </c:if>
            <c:if test="${actionBean.disable eq false}">
                <p>
                    <label><font color="red">*</font> Keputusan : </label>
                    <input type="radio" id="Y" name="kpsn" value="AP" /> Ada Pembida/Tarik Permohonan Untuk Setiap Hakmilik &nbsp;
                    <input type="radio" id="N" name="kpsn" value="TB"/> Ada Dan Tiada Pembida Untuk Setiap Hakmilik
                </p>
            </c:if>
        </c:if>

        <p>
            <label>Ulasan : </label>
            <s:textarea name="ulasan" rows="8" cols="60"/>
        </p><br>

        <p align="center">
            <s:button name="simpanKpsn" value="Simpan" class="btn"  onclick="if(validate())doSubmit(this.form, this.name, 'page_div')"/>
        </p>
    </fieldset>
</s:form>
