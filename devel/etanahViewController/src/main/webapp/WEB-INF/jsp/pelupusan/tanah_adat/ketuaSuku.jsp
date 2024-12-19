<%-- 
    Document   : ketuaSuku
    Created on : Jul 12, 2011, 11:30:35 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:form beanclass="etanah.view.stripes.pelupusan.TanahAdatActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle1">
        <fieldset class="aras1">
    <legend>Maklumat Suku Pemohon</legend><br><br>
    <table>
        <tr>
            <td><label>Nama Suku</label></td>
            <td>:</td>
            <td>${actionBean.namaSuku}</td>
        </tr>
        <tr>
            <td><label>Ketua Suku</label></td>
            <td>:</td>
            <td>${actionBean.dipimpinOleh}</td>
        </tr>
        <tr>
            <td><label>Alamat</label></td>
            <td>:</td>
            <td>${actionBean.alamat1}</td>
        </tr>
        <tr>
            <td><label></label></td>
            <td></td>
            <td>${actionBean.alamat2}</td>
        </tr>
        <tr>
            <td><label></label></td>
            <td></td>
            <td>${actionBean.alamat3}</td>
        </tr>
        <tr>
            <td><label></label></td>
            <td></td>
            <td>${actionBean.alamat4}</td>
        </tr>
        <tr>
            <td><label></label></td>
            <td></td>
            <td>${actionBean.poskod} ${actionBean.negeri.nama}</td>
        </tr>        
    </table>
            </fieldset>
            </div>
</s:form>