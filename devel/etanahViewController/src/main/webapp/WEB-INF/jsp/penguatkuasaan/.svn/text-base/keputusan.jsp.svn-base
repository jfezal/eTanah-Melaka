<%-- 
    Document   : keputusan_dan_view
    Created on : Feb 22, 2010, 9:55:50 AM
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
               Keputusan Terdahulu (jika ada)
            </legend>
            <div class="content" align="center">
                    <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0" id="lineMP">
                    <display:column title="Bil" sortable="true">{lineMP_rowNum}</display:column>
                    <display:column title="Keputusan/Pemantauan"/>
                    <display:column title="Syor" />
                    <display:column title="Ulasan" />
                    </display:table>

            </div>
        </fieldset>
    </div>
<br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Keputusan
            </legend>
            <div class="content" align="center">
                <table>
                    <tr>
                        <td class="rowlabel1"><font color="red">*</font>Keputusan :</td>
                        <td class="input1">
                            <s:radio name="radio_" id="radio_" value=""/> Setuju
                            <s:radio name="radio_" id="radio_" value=""/> Tidak Setuju
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1"><font color="red">*</font>Pemantauan :</td>
                        <td class="input1">
                            <s:radio name="radio_" id="radio_" value=""/> Patuh
                            <s:radio name="radio_" id="radio_" value=""/> Tidak Patuh
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Syor :</td>
                        <td class="input1"><s:select name="">
                                <s:option>Pilih...</s:option>
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Ulasan :</td>
                        <td class="input1"><s:textarea name="ulasan" rows="5" cols="50" /></td>
                    </tr>
                </table>
            </div>
        </fieldset>
    </div>
</s:form>
