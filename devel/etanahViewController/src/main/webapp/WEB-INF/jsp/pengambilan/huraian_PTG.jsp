<%-- 
    Document   : huraian_PTG
    Created on : 18-Jan-2010, 23:39:09
    Author     : nordiyana
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.stripes.pengambilan.PenyediaanKertasActionBean">

            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Huraian Pengarah Tanah Dan Galian
                    </legend>
                    <div class="content" align="left">
                        <s:textarea name="huraian" cols="87" rows="8"/>
                        <p>
                        <label for="Maksud_Pengambilan">&nbsp;</label>
                        <s:submit name="searchAllPermohonan" value="Jana Dokumen" class="btn"/>
                            </p>
                    </div>
                </fieldset>
            </div>

    </s:form>
