<%--
    Document   : ulasan_jabatan_teknikal
    Created on : Dec 14, 2009, 1:56:08 PM
    Author     : zadhirul.farihim
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<s:form beanclass="etanah.view.stripes.pelupusan.JabatanTeknikalActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Ulasan Jabatan Teknikal
            </legend>
            <div class="content" align="center">
                <%--<display:table name="${actionBean.listUlasan2}" id="line1" class="tablecloth">
                    <display:column title="No">
                        ${line1_rowNum}
                    </display:column>
                    <display:column title="Jabatan" property="agensi.nama"/>
                    <display:column title="Ulasan" property="ulasan"/>
                    <display:column title="Syor" property="ulasan"/>
                </display:table>--%>
                <table class="tablecloth">
                    <tr><th>Bil.</th><th>Jabatan Teknikal</th><th>Syor</th><th>Ulasan</th></tr>
                    <tr><td>1</td><td>Jabatan Kerja Raya</td><td>
                            <s:select name="laporanTanah.kecerunanTanah.kod">
                                <s:option value="0">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodKeputusan}" label="nama" value="kod" />
                            </s:select>
                        </td><td><textarea cols="40" rows="2"/></td></tr>
                       <tr><td>2</td><td>Jabatan Parit Dan Saliran</td><td>
                            <s:select name="laporanTanah.kecerunanTanah.kod">
                                <s:option value="0">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodKeputusan}" label="nama" value="kod" />
                            </s:select>
                        </td><td><textarea cols="40" rows="2"/></td></tr>
                </table>
            </div>
            <p>
                <label>&nbsp;</label>
                <s:button class="btn" value="Simpan" name="new" id="new" onclick=""/>&nbsp;
            </p>
            <br>
        </fieldset>
    </div>
</s:form>