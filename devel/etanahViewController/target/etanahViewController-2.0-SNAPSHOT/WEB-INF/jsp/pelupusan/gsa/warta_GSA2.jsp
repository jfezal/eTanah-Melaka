<%-- 
    Document   : warta_GSA2
    Created on : 23 September 2011, 3:51:15 PM
    Author     : Akmal
--%>


<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:form beanclass="etanah.view.stripes.pelupusan.WartaGSAActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <div class="content">
 
                <p>
                    <label>No. Warta :</label>
                    <s:text name="nWarta" size="30" id="noWarta"/>
                </p>
                <p><label>Tarikh Warta :</label>
                    <s:text name="tWarta" size="30" id="tarikhWarta" class="datepicker" formatPattern="dd/MM/yyyy"/>
                </p>
                <p>
                    <label>Tarikh Terima Warta :</label>
                    <s:text name="terimaWarta" size="30" id="tarikhWarta" class="datepicker" formatPattern="dd/MM/yyyy"/>
                </p>
             
                <p align="center"><s:button  name="simpan" id="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
                
                
            </div>
        </fieldset>
 </s:form>