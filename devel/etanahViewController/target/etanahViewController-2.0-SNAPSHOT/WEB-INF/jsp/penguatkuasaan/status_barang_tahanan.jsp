<%-- 
    Document   : status_barang_tahanan
    Created on : Feb 24, 2010, 5:30:08 PM
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
                Status Barang Rampasan
            </legend>
            <div class="content" align="center">
                <table>
                    <tr>
                        <td class="rowlabel1">Status :</td>
                        <td class="input1">
                            <s:radio name="radio_" id="radio_" value=""/> Tiada Tuntutan
                            <s:radio name="radio_" id="radio_" value=""/> Ada Tuntutan
                        <td>
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