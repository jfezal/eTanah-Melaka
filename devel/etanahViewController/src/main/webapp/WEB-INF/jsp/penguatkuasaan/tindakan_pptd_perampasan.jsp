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
                        <td class="rowlabel1">Tindakan :</td>
                        <td class="input1">
                            <s:radio name="radio_" id="radio_" value=""/> Remedi
                            <s:radio name="radio_" id="radio_" value=""/> Perampasan
                            <s:radio name="radio_" id="radio_" value=""/> Tidak Remedi
                        </td>
                    </tr>                    
                </table>
            </div>
        </fieldset>
    </div>
</s:form>