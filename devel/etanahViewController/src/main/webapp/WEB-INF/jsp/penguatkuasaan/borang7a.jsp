<%-- 
    Document   : borang7a
    Created on : Nov 2, 2011, 6:24:12 PM
    Author     : sitifariza.hanim
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
    function limitText(limitField, limitNum) {
        if (limitField.value.length > limitNum) {
            limitField.value = limitField.value.substring(0, limitNum);
        }
    }
</script>
<style type="text/css">
    p{
        color:black;
        font-weight:normal;
        font-size:13px;
        padding-top:2px;
        padding-bottom:2px;
        line-height: 15pt ;
        text-align: justify;
    }


</style>


<s:form beanclass="etanah.view.penguatkuasaan.Borang7AKesActionBean">
    <s:messages/>
    <c:if test="${edit}">
        <div class="subtitle">
            <fieldset class="aras1">
                <s:hidden name="idPermohonan" id="idPermohonan"/>
                <s:hidden name="permohonanLaporanUlasan.idLaporUlas" id="idLaporUlas"/>
                <div>
                    <label>Tindakan Pemulihan Pelanggaran :</label>
                    <s:textarea name="ulasan" cols="50" rows="10" onkeydown="limitText(this,1000);" onkeyup="limitText(this,1000);" class="normal_text"/>
                </div>

            </fieldset>
            <br/>
            <label>&nbsp</label>
            <s:button name="simpan" class="btn" value="Simpan" onclick="doSubmit(this.form, this.name, 'page_div');"/>
        </div>
    </c:if>

    <c:if test="${view}">
        <div class="subtitle">
            <fieldset class="aras1">
                <%--<div>--%>
                <legend>
                    Tindakan Pemulihan Pelanggaran
                </legend>
                <br>

                <p>
                    ${actionBean.ulasan}&nbsp;
                </p>

            </fieldset>
        </div>
    </c:if>
</s:form>



