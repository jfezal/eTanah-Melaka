<%--
    Document   : makluman_Arahan
    Created on : Jul 15, 2010, 3:59:20 PM
    Author     : NageswaraRao
--%>


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>


<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:right;
        width:32em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:20em;
    }
</style>


<s:form beanclass="etanah.view.stripes.pembangunan.MaklumanArahanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">
                <table width="60%" border="0">
                    <c:if test="${(edit)}">
                        <tr>
                            <td id="tdLabel">Makluman/Arahan :</td>
                            <td><s:textarea rows="5" cols="130" name="ulasan" class="normal_text"/></td>
                        </tr>
                    </c:if>
                    <c:if test="${!(edit)}">
                        <tr><td id="tdLabel">Makluman/Arahan :</td>
                            <td id="tdDisplay">${actionBean.fp.ulasan} &nbsp;</td>
                        </tr>
                    </c:if>
                </table>
                <c:if test="${edit}">
                    <p align="center">
                        <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan"/>
                    </p>
                </c:if>
            </div>
        </fieldset>
    </div><br><br>
    <hr><br><br>
</s:form>
