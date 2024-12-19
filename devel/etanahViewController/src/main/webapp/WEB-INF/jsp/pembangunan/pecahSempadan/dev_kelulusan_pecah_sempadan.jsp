<%-- 
    Document   : kelulusan_pecah_sempadan
    Created on : Dec 10, 2009, 2:33:33 PM
    Author     : nursyazwani
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>

<stripes:form beanclass="etanah.view.stripes.pembangunan.PecahSempadanActionBean">
   <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Kelulusan ${actionBean.permohonan.kodUrusan.nama}
            </legend>
            <p>
                <label>Keputusan:</label>
                ${actionBean.keputusan}&nbsp;
            </p>
            <p>
                <label>Arahan:</label>
                <s:textarea name="arahan" cols="100" rows="5"/>
            </p>
        </fieldset>
   </div>
   <p>
       <label>&nbsp;</label>
       <s:button name="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
   </p>
</stripes:form>
   