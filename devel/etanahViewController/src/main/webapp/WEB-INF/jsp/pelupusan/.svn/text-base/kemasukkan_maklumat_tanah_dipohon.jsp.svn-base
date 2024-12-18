<%-- 
    Document   : kemasukkan_maklumat_tanah_dipohon
    Created on : 05-Oct-2009, 16:13:30
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
        <script type="text/javascript">
            $(function(){
                $("#add").click( function(){
                    frm = this.form;                    
                    //TODO :
                    self.opener.addRows(frm.tujuan.value);
                    self.close();
                });
            });

        </script>
    </head>
    <body>
        <form:form beanclass="etanah.view.stripes.PermohonanPelupusanActionBean">
            <fieldset class="aras1">
                <legend>
                    Maklumat Tanah Dipohon
                </legend>
                <table width="100%">
                    <tr>
                        <td class="rowlabel1">Tujuan</td>
                        <td class="input1"><s:text name="" id="tujuan"/></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Daerah</td>
                        <td class="input1"><s:text name=""/></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Bandar/Pekan/Mukim:</td>
                        <td class="input1">
                            <s:select name="">
                                <s:option>Mukim 1</s:option>
                                <s:option>Pekan 1</s:option>
                                <s:option>Bandar 1</s:option>
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Kategori Tanah:</td>
                        <td class="input1">
                            <s:select name="">
                                <s:option>kategori 1</s:option>
                                <s:option>kategori 2</s:option>
                                <s:option>kategori 3</s:option>
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Status Pemilikan Tanah:</td>
                        <td class="input1">
                            <s:select name="">
                                <s:option>kategori 1</s:option>
                                <s:option>kategori 2</s:option>
                                <s:option>kategori 3</s:option>
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Lokasi</td>
                        <td class="input1"><s:text name=""/></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">No Lot (Jika Ada)</td>
                        <td class="input1">
                            <s:select name="">
                                <s:option>1</s:option>
                                <s:option>2</s:option>
                                <s:option>3</s:option>
                            </s:select>
                            <s:text name=""/>
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Keluasan Tanah Dipohon</td>
                        <td class="input1">                            
                            <s:text name=""/>
                        </td>
                    </tr>
                </table>
            </fieldset>
            <table width="100%">
                <tr>
                    <td><s:button name="save" id="add" value="Simpan"/><s:reset name="reset" value="Isi Semula"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>
