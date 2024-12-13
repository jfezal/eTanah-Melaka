<%-- 
    Document   : maklumat_pemohon
    Created on : Jul 1, 2010, 10:58:41 AM
    Author     : User
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.strata.maklumatPermohonActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend> Skrin Kemasukan Maklumat permohonan</legend>
            <div class="content" align="center">
                <table border="0" width="80%">                    
                    <tr><td><b>Nama</b></td>
                        <td><s:text name="Nama" value=""></s:text></td>
                    </tr>
                    <tr>
                        <td><b>Jenis Pengenalan</b></td>
                        <td><s:text name="Jenis Pengenalan" value=""></s:text></td>
                    </tr>
                    <tr>
                         <td><b>Nombor pengenalan</b></td>
                        <td><s:text name="Nombor pengenalan" value=""></s:text></td>
                    </tr>
                    <tr>
                        <td><b>Alamat</b></td>
                        <td><s:text name="Alamat" value=""></s:text></td>
                    </tr>
                    <tr>
                        <td><b>Poskod</b></td>
                        <td><s:text name="Poskod" value=""></s:text></td>
                    </tr>
                    <tr>
                        <td><b>Negeri</b></td>
                        <td><s:text name="Negeri" value=""></s:text></td>
                    </tr>
                </table>
            </div>
        </fieldset>
    </div>
  </s:form>

