<%-- 
    Document   : kertas_ringkasan_full
    Created on : Mar 4, 2010, 3:21:24 PM
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

<s:form beanclass="etanah.view.stripes.pembangunan.KertasRingkasanActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">
                <table border="0" width="80%">
                    <tr><td align="center"><b>KERTAS RINGKASAN PENTADBIR TANAH </b></td></tr><br>

                    <tr><td><b>KERTAS RINGKASAN PENTADBIR TANAH BAGI PERMOHONAN UNTUK <%--${actionBean.permohonan.kodUrusan.nama}--%> BAGI <%--${actionBean.hakmilik.bandarPekanMukim.nama}, ${actionBean.hakmilik.daerah.nama}--%>.</b></td></tr><br>

                    <tr><td><b>1. TUJUAN</b></td></tr>
                    <tr><td width="100%">1.1 Tujuan kertas ini adalah untuk rayuan pertimbangan Pentadbir Tanah bagi permohonan <%--${actionBean.permohonan.kodUrusan.nama}--%>.</td></tr><br>
                    <tr><td><b>2. LATAR BELAKANG</b></td></tr>

                    <c:if test="${edit}">
                        <tr><td><s:textarea name="latarBelakang" rows="5" cols="150"/></td></tr><br>
                    </c:if>

                    <c:if test="${!edit}">
                        <tr><td><%--${actionBean.latarBelakang}--%>&nbsp;</td></tr><br>
                    </c:if>
                    <c:if test="${edit}">
                        <tr><td><b>3. HURAIAN PENTADBIR TANAH  <%--${actionBean.hakmilik.daerah.nama}--%>&nbsp;</b></td></tr>
                        <tr><td><s:textarea name="huraianPentadbir" rows="5" cols="150"/></td></tr><br>
                        <tr><td><b>5. SYOR PENTADBIR TANAH  <%--${actionBean.hakmilik.daerah.nama}--%>&nbsp;</b></td></tr>
                        <tr><td><s:textarea name="syorPentadbir" rows="5" cols="150"/></td></tr><br>
                        <%--<tr><td><b>8. HURAIAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN</b></td></tr>
                        <tr><td><s:textarea name="huraianPtg" rows="5" cols="150"/></td></tr><br>
                        <tr><td><b>9. SYOR PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN</b></td></tr>
                        <tr><td><s:textarea name="syorPtg" rows="5" cols="150"/></td></tr><br>--%>
                    </c:if>

                    <c:if test="${!edit}">
                        <tr><td><b>3. HURAIAN PENTADBIR TANAH  <%--${actionBean.hakmilik.daerah.nama}--%>&nbsp;</b></td></tr>
                        <tr><td><%--${actionBean.huraianPentadbir}--%>&nbsp;</td></tr><br>
                        <tr><td><b>4. SYOR PENTADBIR TANAH  <%--${actionBean.hakmilik.daerah.nama}--%>&nbsp;</b></td></tr>
                        <tr><td><%--${actionBean.syorPentadbir}--%>&nbsp;</td></tr>
                        <%--<tr><td><b>8. HURAIAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN</b></td></tr>
                        <tr><td>${actionBean.huraianPtg}&nbsp;</td></tr><br>
                        <tr><td><b>9. SYOR PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN</b></td></tr>
                        <tr><td>${actionBean.syorPtg}&nbsp;</td></tr><br>--%>
                    </c:if>
                    <%--<tr><td><b>10. KEPUTUSAN</b></td></tr>
                    <tr><td width="100%">10.1 Dibentangkan untuk pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri Sembilan Darul Khusus.</td></tr><br>--%>
                </table>
            </div>
            <c:if test="${edit}">
                <br>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </c:if>
        </fieldset>
    </div>
</s:form>
