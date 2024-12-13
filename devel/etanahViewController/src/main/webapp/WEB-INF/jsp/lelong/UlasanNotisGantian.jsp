<%-- 
    Document   : UlasanNotisGantian
    Created on : Jun 3, 2010, 11:33:47 AM
    Author     : mazurahayati.yusop
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:form beanclass="etanah.view.stripes.lelong.NotisGantianActionBean">
    <p>
        <s:messages />
        <s:errors />&nbsp;
    </p>

    <fieldset class="aras1">
        <legend>
            Arahan Penyampaian Notis Gantian
        </legend><br><br>
        <div class="subtitle displaytag" >
            <p>
                <label> Ulasan :</label>
                <s:textarea name="ulasan" cols="50" rows="5" />                
            </p>
            <p>
                <label>&nbsp;</label>
                <s:hidden name="fasaPermohonan.idFasa"/>
            </p>

            <p align="right">
                <s:button name="simpanNotis" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
            </p>
        </div>
    </fieldset>
</s:form>
