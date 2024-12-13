<%-- 
    Document   : tindakan_operasi
    Created on : Feb 22, 2010, 4:33:56 PM
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
                Tindakan Operasi
            </legend>
            <div class="content" align="center">
                <table>
                    <tr>
                        <td class="rowlabel1">Keputusan :</td>
                        <td class="input1">
                            <s:radio name="radio_" id="radio_" value=""/> Tindakan Operasi Diteruskan
                            <s:radio name="radio_" id="radio_" value=""/> Tindakan Operasi Tidak Diteruskan
                        </td>
                    </tr>
                     
                </table>
            </div>
        </fieldset>
    </div>
</s:form>