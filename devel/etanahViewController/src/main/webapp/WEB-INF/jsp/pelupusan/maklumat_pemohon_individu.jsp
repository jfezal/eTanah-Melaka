<%-- 
    Document   : maklumat_pemohon_individu
    Created on : 05-Oct-2009, 15:36:51
    Author     : md.nurfikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body>
        <form:form beanclass="etanah.view.stripes.PermohonanPelupusanActionBean">
            <fieldset class="aras1">
                <legend>
                    Maklumat Pemohon Individu
                </legend>
                <table width="100%">
                    <tr>
                        <td class="rowlabel1">Nama:</td>
                        <td class="input1"><s:text name=""/></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Jenis Pengenalan:</td>
                        <td class="input1">
                            <s:select name="">
                                <s:option>Kad Pengenalan Baru</s:option>
                                <s:option>Kad Pengenalan Lama</s:option>
                                <s:option>Tentera</s:option>
                                <s:option>Surat Beranak</s:option>
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">No Pengenalan:</td>
                        <td class="input1"><s:text name=""/></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Warna Kad Pengenalan:</td>
                        <td class="input1"><s:text name=""/></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Umur:</td>
                        <td class="input1"><s:text name=""/> Tahun</td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Bangsa:</td>
                        <td class="input1">
                            <s:select name="">
                                <s:option>Melayu</s:option>
                                <s:option>Cina</s:option>
                                <s:option>India</s:option>
                                <s:option>Lain-lain</s:option>
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Warganegara:</td>
                        <td class="input1">
                            <s:select name="">
                                <s:option>Malaysia</s:option>
                                <s:option>Lain-lain</s:option>
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Alamat:</td>
                        <td class="input1"><s:text name=""/></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1"></td>
                        <td class="input1"><s:text name=""/></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1"></td>
                        <td class="input1"><s:text name=""/></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1"></td>
                        <td class="input1"><s:text name=""/></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Poskod:</td>
                        <td class="input1"><s:text name=""/></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Negeri:</td>
                        <td class="input1"><s:text name=""/></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">No Tel Rumah:</td>
                        <td class="input1"><s:text name=""/></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">No Tel Pejabat:</td>
                        <td class="input1"><s:text name=""/></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">No Tel Bimbit:</td>
                        <td class="input1"><s:text name=""/></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Pekerjaan:</td>
                        <td class="input1"><s:text name=""/></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Pendapatan:</td>
                        <td class="input1"><s:text name=""/></td>
                    </tr>
                </table>
            </fieldset>
        </form:form>
    </body>
</html>
