<%--
    Document   : tetap_enkuiri
    Created on : Mar 4, 2010, 6:43:00 PM
    Author     : mazurahayati
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.stripes.lelong.notisEnkuiriActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Notis
            </legend>
            <div class="content">

                <p>
                    <label>Tarikh Notis:</label>
                    <s:text name="tarikhNotis" id="datepicker" class="datepicker" onchange=""/>&nbsp;
                    <font color="red" size="1">(cth : hh / bb / tttt)</font>
                    &nbsp;
                </p>

                <p>
                    <label> Jenis Notis :</label>
                    <s:select id="" name="note">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodNotis}" label="nama" value="kod"/>
                    </s:select>
                </p>
            </div>
        </fieldset>
    </div>

    <div class="content" align="right">
        <p>
            <s:button name="simpanNotis" id="" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
        </p>
    </div>
</s:form>
