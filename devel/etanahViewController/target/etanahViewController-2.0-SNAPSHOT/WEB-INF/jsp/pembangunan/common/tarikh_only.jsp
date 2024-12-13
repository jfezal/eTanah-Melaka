<%-- 
    Document   : tarikh_only
    Created on : Oct 28, 2013, 4:01:29 PM
    Author     : khairul.hazwan
--%>


<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>

<s:form beanclass="etanah.view.stripes.pembangunan.TarikhTandatanganActionBean">
    <s:messages/>
    <s:errors/>       
    <p><font color="red">*Sila masukkan tarikh tandatangan terlebih dahulu sebelum menekan butang 'Selesai'. </font></p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Rekod Tarikh Tandatangan</legend>
                <p>
                    <label><font color="red">*</font>Tarikh Tandatangan :</label>
                        <s:text name="tarikh" size="12" id="datepicker" class="datepicker"/>
                </p>
            
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpanTarikhOnly" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
        </fieldset>
    </div>
   
</s:form>

