<%-- 
    Document   : tandatangan_permit
    Created on : Mar 9, 2018, 12:02:04 PM
    Author     : mohd.faidzal
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<script type="text/javascript">
    function callProc(str)
    {
        var signer = new ActiveXObject("SAPDFSigner.Form1");
        signer.callingProc(str);

        //signer.SigningPDFFile(str,"2017-12-22");
    }

    function callPDF(str, date)
    {
        var signer = new ActiveXObject("SAPDFSigner.Form1");

        signer.SigningPDFFile(str, date);
    }

    function callPDFMulti(str, str2)
    {
        var signer = new ActiveXObject("SAPDFSigner.Form1");
        signer.SigningMultiPDFFile(str, str2);
    }


</script>
<s:form beanclass="etanah.view.stripes.pelupusan.TandatanganDokumenActionBean" name="eload">
    <div class="content" align="center">
        <fieldset class="aras1">
            Peringatan : sila Jana no Permit dan Permit sebelum tanda tangan.
            <legend>Tandatangan Borang</legend>
            <s:hidden name="${actionBean.permohonan.kodUrusan.kod}" value="${actionBean.permohonan.kodUrusan.kod}" id="kodUrusan"/>
            <s:hidden name="test" id="test1"/>
            <s:hidden name="test2" id="test2"/>
            <s:hidden name="tarikh" id="tarikh1"/>
            <s:hidden name="sizeDokumen" id="sizeDokumen"/>
            <display:table class="tablecloth" name="${actionBean.senaraitandatangan}" cellpadding="0" cellspacing="0"
                           requestURI="${pageContext.request.contextPath}/pelupusan/dokumen_tandatangan" id="line">
                <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                <%--<display:column property="dokumen.idDokumen" title="Kod Dokumen"/> --%>
                <display:column property="dokumen.kodDokumen.kod" title="Kod Dokumen"/>
                <display:column property="dokumen.kodDokumen.nama" title="Nama Dokumen"/>
                <br>
                <display:column title="Tandatangan">  
                    <c:if test="${actionBean.permit.noPermitBaru ne null}"> 
                        <s:button name="tandantangan" value="Tandatangan" class="longbtn" onclick="callPDF('${line.signPath}','${line.date}')"/>
                    </c:if>
                </display:column>
            </display:table>
            <c:if test="${actionBean.permit.noPermitBaru eq null}"> 
                <s:button name="generatePermit" class="longbtn" value="Jana No Permit" onclick="doSubmit(this.form, this.name, 'page_div');"/>
            </c:if>
        </fieldset>
    </div>


</s:form>
