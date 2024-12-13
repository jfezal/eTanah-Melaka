<%-- 
    Document   : dev_maklumat_permohonan
    Created on : Jan 12, 2010, 2:22:55 PM
    Author     : nursyazwani
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>

<s:form beanclass="etanah.view.stripes.pembangunan.MaklumatPermohonanActionBean">
    <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Permohonan</legend>
                <div class="content" align="center">
                    <p>
                        <label for="Permohonan">Permohonan :</label>
                        <%--${actionBean.permohonan.kodUrusan.nama}--%>
                    </p><br>
                    <p>
                        <label for="ID Permohonan">ID Permohonan :</label>
                        <%--${actionBean.permohonan.idPermohonan}--%>
                    </p>
                </div>
            </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Penyerah</legend>
            <p>
                <label>Nama/Syarikat :</label>
                ${actionBean.permohonan.penyerahNama}&nbsp;
            </p>
            <p>
                <label>Nombor Rujukan Penyerah :</label>
                ${actionBean.permohonan.penyerahNoRujukan}&nbsp;
            </p>

            <p>
                <label>Alamat :</label>
                ${actionBean.permohonan.penyerahAlamat1}&nbsp;
            </p>
            <p>
                <label> &nbsp;</label>
                ${actionBean.permohonan.penyerahAlamat2}&nbsp;
            </p>
            <p>
                <label> &nbsp;</label>
                ${actionBean.permohonan.penyerahAlamat3}&nbsp;
            </p>
            <p>
                <label> &nbsp;</label>
                ${actionBean.permohonan.penyerahAlamat4}&nbsp;
            </p>
            <p>
                <label>Poskod :</label>
                ${actionBean.permohonan.penyerahPoskod}&nbsp;
            </p>
            <p>
                <label>Negeri :</label>
                ${actionBean.permohonan.penyerahNegeri.nama}&nbsp;
            </p>
        </fieldset >
    </div>

</s:form>