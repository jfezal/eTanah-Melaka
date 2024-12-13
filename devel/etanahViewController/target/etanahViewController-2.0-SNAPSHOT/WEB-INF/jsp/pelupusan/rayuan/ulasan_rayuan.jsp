<%-- 
    Document   : ulasan_rayuan
    Created on : Jul 5, 2011, 1:15:40 PM
    Author     : Zaid
--%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pelupusan.RayuanAnsuranActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Ulasan Rayuan</legend>
            <p>
                <label>Ulasan Rayuan :</label>
                <s:textarea name="permohonan.ulasan" rows="5" cols="50" class="normal_text" id="ulasanRayuan"/>
            </p>
           <br/>
            <p>
                <label>&nbsp;</label>
                <s:button name="simpanUlasan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>
            </p>
        </fieldset>
    </div>
</s:form>
