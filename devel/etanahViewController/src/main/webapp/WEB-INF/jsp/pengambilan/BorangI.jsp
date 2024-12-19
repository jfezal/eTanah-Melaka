<%-- 
    Document   : BorangI
    Created on : 23-Feb-2010, 17:09:31
    Author     : nordiyana
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<s:form beanclass="etanah.view.stripes.pengambilan.BorangIActionBean">
<s:messages/>
<s:errors/>
    <s:hidden name="permohonanRujukanLuar.idRujukan"/>
    <div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Borang I
        </legend>
                 <p>
                 <label for="status permohonan">Jenis Permohonan :</label>
                 <s:radio name="statusPermohonan" value="Segera"/>&nbsp;Segera
                 <s:radio name="statusPermohonan" value="Tidak Segera"/>&nbsp;Tidak Segera
                 </p>
    </fieldset>
</div>


    </s:form>
