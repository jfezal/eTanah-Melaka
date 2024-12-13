<%--
    Document   : maklumat_pemohon
    Created on : Jul 1, 2010, 10:58:41 AM
    Author     : User
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.strata.SampleActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend> Skrin Kemasukan Maklumat permohonan</legend>
            <div class="content" align="center">
                <table border="0" width="80%">
                    <tr><td colspan="3"><b>1) Maklumat Permohon</b></td> </tr><br>
                    
                    <tr><td width="20%"><b>Nama</b></td>
                        <td width="1%"><b>:</b></td>
                        <td><s:text name="nama" value=""></s:text></td>
                    </tr>
                    <tr>
                        <td><b>Jenis Pengenalan</b></td>
                        <td><b>:</b></td>
                        <td><s:text name="jenisPengenalan" value=""></s:text></td>
                    </tr>
                    <tr>
                         <td><b>Nombor pengenalan</b></td>
                         <td><b>:</b></td>
                        <td><s:text name="nomborPengenalan" value=""></s:text></td>
                    </tr>
                    <tr>
                        <td><b>Alamat</b></td>
                        <td><b>:</b></td>
                        <td><s:text name="alamat" value=""></s:text></td>
                    </tr>
                    <tr>
                        <td><b>Poskod</b></td>
                        <td><b>:</b></td>
                        <td><s:text name="poskod" value=""></s:text></td>
                    </tr>
                    <tr>
                        <td><b>Negeri</b></td>
                        <td><b>:</b></td>
                        <td><s:text name="negeri" value=""></s:text></td>
                    </tr>
                </table>
            </div>
        </fieldset>
    </div>
  </s:form>

