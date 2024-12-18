<%--
    Document   : maklumat_fail
    Created on : 21 Oktober 2009, 3:30:45 PM
    Author     : khairil
--%>
<%--<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>--%>
<%--<s:form beanclass="etanah.view.stripes.MaklumatFailActionBean">--%>
 <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'KVLK'}">
<div class="subtitle">
    <fieldset class="aras1">
        <legend>Permohonan Sebelum</legend>

       
            <p>
                <label>ID Permohonan :</label>
            <s:text name="permohonan.permohonanSebelum.idPermohonan" value="${actionBean.permohonan.permohonanSebelum}" />
            </p>
            <p>
                <label>Unit Serah :</label>
            <s:select name="permohonan.kodUrusan.jabatan.kod" value="${actionBean.permohonan.kodUrusan.jabatan.kod}">
                <s:option value="">-- Sila Pilih --</s:option>
                <s:options-collection collection="${list.senaraiKodJabatan}" label="nama" value="kod"/>
            </s:select>
            </p>
            <p>
                <label>Tarikh Terima :</label>
            <fmt:formatDate type="both"
                            pattern="dd/MM/yyyy h:mm"
                            value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>
            <fmt:formatDate type="time"
                            pattern="aaa"
                            value="${actionBean.permohonan.infoAudit.tarikhMasuk}" var="timeformat"/>
            <c:if test="${timeformat eq 'AM'}"> Pagi</c:if>
            <c:if test="${timeformat eq 'PM'}"> Petang</c:if>
      
        <%--${actionBean.permohonan.infoAudit.tarikhMasuk}--%>

        </p>
        <%-- <p>
             <label>Bil. Hakmilik :</label>
             < db >
         </p>--%>
        <%-- <p>
             <label>Bil. Siap :</label>
             < db >
         </p>
         <p>
             <label>Bil. Belum Siap :</label>
             < db >
         </p>--%>

        <p>
            <label>&nbsp;</label>
            <%--<s:button name="simpanPermohonan" value="Simpan" class="btn" onClick="doSubmit(this.form, this.name, 'page_div')" />--%>
        <s:button name="simpanPermohonan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>

        <%--<s:submit name="keluar" value="Keluar" class="btn" />--%>
        </p>

    </fieldset>
</div>
 </c:if>



