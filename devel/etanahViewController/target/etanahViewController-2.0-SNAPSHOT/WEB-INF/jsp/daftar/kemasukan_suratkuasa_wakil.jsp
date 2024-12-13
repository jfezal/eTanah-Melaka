<%-- 
    Document   : kemasukan_suratkuasa_wakil
    Created on : Dec 14, 2009, 1:36:42 PM
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


<form:form beanclass="etanah.view.stripes.kemasukanSuratkuasawakilActionBean">

    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Suratkuasa Wakil
            </legend>
            <p>
                <label for="noRujukan">ID Suratkuasa Wakil :</label>
                <s:text name="noRujukan"></s:text>
            </p>
            <p>
                <label for="noSidang">Tarikh Fail :</label>
                <s:text name="noMesy" class="datepicker"></s:text>

            </p>

            <p>
                <label for="trhSidang">Nombor Mahkamah :</label>
                <s:text name="noMahkamah"/>
            </p>
             <p>
                <label for="trhSidang">Tarikh Mahkamah :</label>
                <s:text name="trhSidang" class="datepicker"/>
            </p>

            <p>
                <label for="noRujukan">Nama Pemberi :</label>
                <s:text name="noRujukan"></s:text>
            </p>
            <p>
                <label for="noRujukan">No. K/P Pemberi :</label>
                <s:text name="noRujukan"></s:text> *Contoh : 760911025564
            </p>
            <p>
                <label for="trhRujukan">Nama Penerima :</label>
                <s:text name="trhRujukan"/>
            </p>
            <p>
                <label for="noRujukan">No. K/P Penerima :</label>
                <s:text name="noRujukan"></s:text> *Contoh : 760911025564
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