<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.stripes.consent.MaklumatPindahMilikActionBean">
    <s:messages/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Draf Pertimbangan JKTD</legend>
            <p>
                <label>Huraian Pentadbir Tanah :</label>
                <s:textarea name="permohonan.sebab" rows="5" cols="70"/>
            </p>
            <p>
                <label>Syor Pentadbir Tanah :</label>
                <s:textarea name="permohonan.catatan" rows="5" cols="70"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:button name="simpanPindahMilik" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </fieldset >
    </div>

</s:form>

