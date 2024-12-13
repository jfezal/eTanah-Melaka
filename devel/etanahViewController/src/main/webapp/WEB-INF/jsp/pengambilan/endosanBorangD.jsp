<%--
    Document   : endosanBorangD
    Created on : Mac 1, 2011, 9:45:56 PM
    Author     : massita
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:form beanclass="etanah.view.stripes.pengambilan.EndosanBorangDActionBean">
    <div class="subtitle">
    <fieldset class="aras1">
          <legend>Borang D</legend><br />
          <font color="black">Maklumat Tanah</font>
        
    
       <p align="center">
           <s:button class="longbtn" style="font-size:10px" value="Hantar ke unit pendaftaran" name="simpan" />
       </p>
   </fieldset>
   </div>
</s:form>
