<%-- 
    Document   : laporan_tanah3
    Created on : Jan 14, 2010, 11:41:54 AM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.stripes.pelupusan.LaporanTanahActionBean">
    <fieldset class="aras1">
        <legend>LAPORAN TANAH</legend>
        <br><br>
        <table border="0">
            <tr>
                <td>Id Permohonan</td>
                <td>444</td>
                <td>Mukim</td>
                <td><s:text name=""/></td>
            </tr>
            <tr>
                <td>Lot/PT.No</td>
                <td><s:text name=""/></td>
                <td>Jenis Hakmilik</td>
                <td><s:text name=""/></td>
            </tr>
        <tr>
            <td>Sekiranya Pajakan :</td>
            <td>Tempoh </td>
            <td><s:text name=""/></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>Tarikh Luput</td>
            <td><s:text name=""/></td>
        </tr>
        <tr>
            <td>Luas </td>
            <td><s:text name=""/></td>
            <td>DUN</td>
            <td><s:text name=""/></td>
        </tr>
        <tr>
            <td>Keadaan Tanah</td>
            <td><s:textarea name=""/></td>
        </tr>
        <tr>
            <td>Pemilik Berdaftar</td>
            <td><s:textarea name=""/></td>
        </tr>
        <tr>
            <td>Penjenisan</td>
            <td><s:text name=""/></td>
        </tr>
        <tr>
            <td>Syarat Nyata</td>
            <td><s:text name=""/></td>
        </tr>
        <tr>
            <td>Sekatan Kepentingan</td>
            <td><s:text name=""/></td>
        </tr>
    </table>

    </fieldset>
    
</s:form>