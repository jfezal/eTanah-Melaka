<%-- 
    Document   : syor_cadangan
    Created on : 14-Oct-2009, 10:37:34
    Author     : md.nurfikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<div class="subtitle">
    <fieldset class="aras1">
        <legend>Syor Cadangan</legend>
        <div class="content">
            <p>
                <label for="syor">Syor Cadangan</label>
                <s:radio name="" value="D">Daftar</s:radio>
                <s:radio name="" value="T">Tolak</s:radio>
                <s:radio name="" value="G">Gantung</s:radio>
            </p>
            <p>
                <label for="ulasan">Ulasan</label>
                <s:textarea name="" cols="70" rows="5"/>
            </p>
        </div>
    </fieldset>
</div>
