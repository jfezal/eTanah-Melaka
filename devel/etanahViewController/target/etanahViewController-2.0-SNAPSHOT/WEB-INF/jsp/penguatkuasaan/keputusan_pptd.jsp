<%-- 
    Document   : keputusan_pptd
    Created on : Feb 24, 2010, 12:08:06 PM
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
               Keputusan PPTD
            </legend>
            <br>
            <table>
                <tr>
                    <td class="rowlabel1">Keputusan :</td>
                    <td class="input1"><s:radio name="radio_" id="radio_" value=""/> Terus Langgar Syarat
                        <s:radio name="radio_" id="radio_" value=""/> Berhenti Langgar Syarat

                    </td>
                </tr>
                <tr>
                    <td class="rowlabel1">Ulasan :</td>
                    <td class="input1"><s:textarea name="ulasan" rows="5" cols="50"/></td>
               </tr>
            </table>
        </fieldset>
  </div>
</s:form>