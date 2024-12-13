<%-- 
    Document   : surat_ulasan
    Created on : Jun 25, 2013, 3:29:40 PM
    Author     : muhammad.amin
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<style type="text/css">

    #uLabel {
        width: 15em;
        float: left;
        text-align: left;
        margin-right: 0px;
        display: block;
        color:#003194;
        font-weight: bold;
        font-family:Tahoma;
        font-size: 13px;
        margin-left: -3px;
    }
</style>

<s:form beanclass="etanah.view.stripes.consent.SuratUlasanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <c:if test="${actionBean.complete}">
                <legend>Maklumat Surat Ulasan Teknikal</legend>
                <table width="100%">
                    <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                    <tr><td></td><td>
                            <c:if test="${actionBean.tajuk ne ' '}">
                                <s:textarea name="tajuk"  readonly="true" class="normal_text"
                                            style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>
                            </c:if>
                        </td></tr>
                    <tr><td>&nbsp;</td><td>&nbsp;</td></tr>    
                    <tr><td></td><td>
                            Dengan hormatnya saya merujuk kepada perkara tersebut di atas.
                        </td></tr>
                    <tr><td>&nbsp;</td><td>&nbsp;</td></tr>    
                    <tr><td></td><td>
                            <s:textarea name="text1" rows="5" style="width:97%;" class="normal_text"/>
                        </td></tr>
                    <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                    <tr><td></td><td>
                            <s:textarea name="text2" rows="5" style="width:97%;" class="normal_text"/>
                        </td></tr>
                    <tr><td></td><td>
                            <s:textarea name="text3" rows="5" style="width:97%;" class="normal_text"/>
                        </td></tr>
                    <tr><td>&nbsp;</td><td>&nbsp;</td></tr>

                </table>
                <p align="center">

                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>

                </p>
                <br/>
            </c:if>
        </fieldset>
    </div>
</s:form>



