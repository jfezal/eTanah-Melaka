<%-- 
    Document   : warta_GSA
    Created on : 14 September 2011, 1:09:29 PM
    Author     : Akmal
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:form beanclass="etanah.view.stripes.pelupusan.WartaGSA">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <div class="content">
 
                <p>
                    <label>No. Warta Terdahulu :</label>
                    <s:text name="noWarta" size="30" id="noWarta"/>
                </p>
                <p><label>Tarikh Warta Terdahulu :</label>
                    <s:text name="tarikhWarta" size="30" id="tarikhWarta" class="datepicker" formatPattern="dd/MM/yyyy"/>
                </p>
                <p>
                    <label>No. Pelan :</label>
                    <s:text name="noPelan" size="30" id="noPelan"/>
                </p>
             
                <p align="center"><s:button  name="simpanWarta" id="simpanWarta" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
                
                
            </div>
        </fieldset>
 </s:form>