<%-- 
    Document   : nota_daftar_luasdancukai
    Created on : Dec 31, 2009, 12:24:25 PM
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
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

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
                <label for="noFail">Nombor Fail / ID Permohonan :</label>
                <s:text name="permohonanRujLuar.noFail" size="24"></s:text>
            </p>
            <p>
                <label>Nombor Kertas:</label>
                <s:select name="permohonanRujLuar.namaSidang" id="kodMesy" style="width:6%;" >
                    <s:option value="" >Sila Pilih</s:option>
                    <s:option value="PTD">PTD</s:option>
                    <s:option value="MMK">MMK</s:option>
                    <s:option value="MB">MB</s:option>
                </s:select>

                <s:text name="permohonanRujLuar.noSidang" size="10"></s:text>

            </p>

            <p>
                <label>Tarikh Mesyuarat :</label>
                <s:text name="tarikhSidang" class="datepicker"/>
            </p>
            <p>
                <label>Nombor Warta :</label>
                <s:text name="permohonanRujLuar.noRujukan" size="24"></s:text>
            </p>
            <p>
                <label>Tarikh Warta :</label>
                <s:text name="tarikhRujukan" id="datepicker" class="datepicker"/>
            </p>

            <p id="HLTE">
                <label>Keluasan :</label>
                <s:text name="luas" size="10"></s:text>
                <s:select name="kodUOM" id="kodMesy" style="width:6%;" >
                    <s:option value="" >Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodUOM}" label="nama" value="kod" />
                </s:select>
            </p>
            <p>
                <label>Cukai Dipanda :</label>
                <s:text name="cukaiBaru" size="24"></s:text>
            </p>
            <br/>


        </fieldset>

    </div>
    <br>
    <table style="margin-left: auto; margin-right: auto;">
        <tr>
            <td>&nbsp;</td>
            <td><div >

                    <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="simpanMaklumatluasdancukai" value="Simpan"/>


                </div>
            </td>
        </tr>
    </table>

    <br>

</form:form>