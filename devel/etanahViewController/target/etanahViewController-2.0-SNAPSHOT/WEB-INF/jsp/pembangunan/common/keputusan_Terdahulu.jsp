<%--
    Document   : Keputusan_Terdahulu
    Created on : DEC 20, 2011, 12:44:34 PM
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



<s:form beanclass="etanah.view.stripes.pembangunan.KeputusanTerdahuluActionBean">
    <s:hidden name="kes:textrtasK.kertas.idKertas"/>
    <s:hidden name="btn"/>
    <s:errors/>
    <s:messages/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.kertasList}" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column property="tajuk" title="No Kertas Mesyuarat" style="vertical-align:baseline"/>
                    <display:column title="Tarikh Bersidang" style="vertical-align:baseline">
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhSidang}"/>
                    </display:column>
                    <%--<display:column  title="Tarikh Disahkan" style="vertical-align:baseline">
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhSahKeputusan}"/>
                    </display:column>--%>
                    <display:column property="noLaporan" title="Keputusan" style="vertical-align:baseline"/>
                       
                </display:table>
                <!--<p>
                        <label>Ulasan ::</label>
                        <//s:textarea name="ulasan" cols="50" rows="5"/>
                    </p>-->
            </div>
        </fieldset>
    </div>
</s:form>