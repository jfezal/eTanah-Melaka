<%-- 
    Document   : getNewAduan
    Created on : Oct 28, 2013, 9:10:07 AM
    Author     : nurashidah
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    function editTindakan(frm, val) {
        var url = '${pageContext.request.contextPath}/uam/aduan?viewAduan&idAduan=' + val;
        frm.action = url;
        frm.submit();
    }


</script>

<s:form beanclass="etanah.view.uam.SenaraiAduanPortalActionBean" name="senaraiAduanPortalForm">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">

        <fieldset class="aras1">
            <legend>
                Senarai Aduan
            </legend>
            <div class="content" align="center">
                <display:table name="${actionBean.senaraiAduanPortal}" id="line" class="tablecloth" pagesize="20" cellpadding="0" cellspacing="0"
                               requestURI="${pageContext.request.contextPath}/uam/senarai_aduan_portal">
                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                    <display:column property="nama" title="Nama Pengadu" style="vertical-align:baseline"/>
                    <display:column property="noPengenalan" title="No. Kad Pengenalan" style="vertical-align:baseline"/>
                    <display:column property="kodAduanPortal.jenisAduan" title="Jenis Aduan" style="vertical-align:baseline"/>
                    <display:column property="tarikhAduan" title="Tarikh Aduan" style="vertical-align:baseline"/>
                    <display:column title="Penerangan Aduan" style="vertical-align:baseline"><textarea readonly="true">${line.penerangan}</textarea></display:column>>
                    <display:column title="Tindakan">

                        <div align="center">
                            <img alt='Klik Untuk Tindakan' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';" onclick="editTindakan(document.forms.senaraiAduanPortalForm, '${line.idAduan}')"/>
                        </div>
                    </display:column>                                  
                </display:table>
            </div>
        </fieldset>

    </div>
</s:form>
