<%-- 
    Document   : nota_daftar_pendaftaran
    Created on : Dec 22, 2009, 10:47:28 AM
    Author     : mohd.fairul
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<form:form beanclass="etanah.view.stripes.nota.NotaDaftarActionBean">

    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Nota
            </legend>
            <p style="color:red">
                *Isi Yang Berkenaan Sahaja.
            </p>
            <p>
                <label for="noRujukan">Nombor Fail / ID Permohonan :</label>
                <s:text name="noRujukan" size="24"></s:text>
            </p>
            <p>
                <label for="noSidang">Nombor Kertas:</label>
                <s:select name="kodMesy" id="kodMesy" style="width:6%;" >
                    <s:option value="" >Sila Pilih</s:option>
                    <s:option value="PTD">PTD</s:option>
                    <s:option value="MMK">MMK</s:option>
                    <s:option value="MB">MB</s:option>
                </s:select>

                <s:text name="noMesy" size="10"></s:text>

            </p>

            <p>
                <label for="trhSidang">Tarikh Mesyuarat :</label>
                <s:text name="trhSidang" class="datepicker" size="24"/>
            </p>
                           
            <br/>


        </fieldset>

    </div>
    <br>
    <table style="margin-left: auto; margin-right: auto;">
        <tr>
            <td>&nbsp;</td>
            <td><div >

                    <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="save" value="Simpan"/>


                </div>
            </td>
        </tr>
    </table>

    <br>

</form:form>
