<%-- 
    Document   : maklumat_aduan_view
    Created on : 19-Jan-2010, 15:34:24
    Author     : nurshahida.radzi
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<s:form beanclass="etanah.view.penguatkuasaan.PenguatkuasaanActionBean">
<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Maklumat Aduan
        </legend>
        <div class="content" align="center">

                <table>
                    <tr>
                        <td class="rowlabel1">ID Aduan :</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Tarikh :</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Daerah :</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1"><font color="red">*</font>Cara Aduan :</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Peruntukan Undang-undang :</td>
                        <td></td>
                    </tr>
                   <tr>
                        <td class="rowlabel1"><font color="red">*</font>Ringkasan Aduan :</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1"><font color="red">*</font>Jenis Haiwan :</td>
                        <td></td>
                    </tr>
                </table>

                <br>
            </div>
        </fieldset>
    </div>
</s:form>
