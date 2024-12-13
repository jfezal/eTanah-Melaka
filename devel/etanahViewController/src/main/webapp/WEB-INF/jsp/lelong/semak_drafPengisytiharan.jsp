<%-- 
    Document   : semak_drafPengisytiharan
    Created on : Mar 17, 2010, 5:43:27 PM
    Author     : mazurahayati.yusop
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:form action="">
        <div class="subtitle">
        <fieldset class="aras1">
            <legend>
               Sokongan PPTD
            </legend>
            <div class="content">
                <p>
                    <label>Sokongan :</label>
                        <s:radio name="radio_" id="radio_" value=""/> Sokong
                        <s:radio name="radio_" id="radio_" value=""/> Tidak Disokong
                </p>
                    <p>
                        <label>Ulasan :</label>
                        <s:textarea name="" rows="5" cols="50"/>
                    </p>
            </div>
        </fieldset>
    </div>

    <div class="content" align="right">
            <p>
                    <s:button name="" value="Papar Draf Peristiharan" class="btn" />
                    <s:button name="" value="Isi Semula" class="btn" />
                    <s:button name="" value="Simpan" class="btn" />
                    <%--<s:button name="" value="Hantar" class="btn" />
                    <s:button name="" value="Keluar" class="btn" />--%>
            </p>
    </div><br>
</s:form>
