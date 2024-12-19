<%-- 
    Document   : terima_ulasan_jbtn_teknikal
    Created on : Jul 14, 2010, 2:33:59 PM
    Author     : sitifariza.hanim
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<s:form beanclass="etanah.view.stripes.pelupusan.JabatanTeknikalActionBean">
   <div class="content" align="center">
                <display:table class="tablecloth" name="x" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pelupusan/jabatan_teknikal">

                    <display:column title="Bil" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                    </display:column>
                    <display:column property="jabatan" title="Nama Jabatan"/>
                    <display:column property="tarikhHantar" title="Tarikh Hantar" />
                    <display:column property="tarikhJangkaTerima" title="Tarikh Jangka Terima" />
                    <display:column property="tarikhTerima" title="Tarikh Terima" />
                    <display:column property="syor" title="Syor" />
                    <display:column property="status" title="Status" />
                </display:table>
            </div>

</s:form>


