<%-- 
    Document   : tindakan
    Created on : Jan 20, 2010, 6:55:33 PM
    Author     : farah.shafilla
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
                Tindakan Pentadbir Tanah Daerah
            </legend>
            <div class="content" align="center">
                <table>
                    <tr>
                        <td class="rowlabel1">Keputusan :</td>
                        <td class="input1">
                            <s:radio name="radio_" id="radio_" value=""/> Notis Kosongkan Tanah
                            <s:radio name="radio_" id="radio_" value=""/> Dakwa
                            <s:radio name="radio_" id="radio_" value=""/> Denda
                            <s:radio name="radio_" id="radio_" value=""/> Operasi
                            <s:radio name="radio_" id="radio_" value=""/> Tiada Tindakan
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Ulasan :</td>
                        <td class="input1">
                            <s:textarea name="ulasan" rows="5" cols="50" />
                        </td>
                    </tr>
                </table>
            </div>
        </fieldset>
    </div>
</s:form>