<%-- 
    Document   : perakuan
    Created on : Jul 22, 2010, 3:41:34 PM
    Author     : Rohan
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />


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

<s:form beanclass="etanah.view.stripes.pembangunan.PerakuanActionBean">

    <s:hidden name="kandunganK.kertas.idKertas"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <div class="content"  align="center">
                <table border="0" width=60%" align="center">

                    <tr><td><b>Perakuan</b></td>
                        <td>:</td>
                        <c:if test="${edit}">
                            <td><font style="text-transform: uppercase"><s:textarea rows="5" cols="120" name="perakuanPTGMelaka"/></font></td>
                        </c:if>
                        <c:if test="${!edit}">
                            <td><font style="text-transform:capitalize">1.1 &nbsp; ${actionBean.perakuanPTGMelaka}</font></td>
                        </c:if>
                    </tr><br><br>

                </table>
                <tr align="center">
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </tr>
            </div>
        </fieldset>
    </div>
</s:form>

