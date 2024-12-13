<%-- 
    Document   : cetak_surat_tolakPermohonan
    Created on : Aug 5, 2010, 1:01:36 PM
    Author     : mazurahayati.yusop
--%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.stripes.lelong.MaklumatEnkuiriActionBean">
    <p>
        <s:messages />
        <s:errors />&nbsp;
    </p>

    <fieldset class="aras1">
        <legend>
            Cetak Surat Tolak Permohonan
        </legend><br><br>
        <div class="content">
<%--            <p align="right">
                 <s:button name="saveNotisGantian" onclick="edit1(this.form, this.name, 'page_div');" id="btn1" disabled="${actionBean.disabled}" value="Cetak Notis Gantian" class="longbtn" style=""/>
            </p>--%>
        </div>
    </fieldset>
</s:form>
