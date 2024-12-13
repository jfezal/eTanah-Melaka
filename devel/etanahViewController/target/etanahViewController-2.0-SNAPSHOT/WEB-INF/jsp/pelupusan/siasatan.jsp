<%-- 
    Document   : siasatan
    Created on : Apr 24, 2011, 7:06:55 PM
    Author     : Murali
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:form beanclass="etanah.view.stripes.pelupusan.SiasatanActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <div class="content" align="center">

                <legend>Siastan</legend>

                <table align="center" width="80%">
                          <tr><td valign="top"><b>Keputusan Siasatan</b></td>
                              <td valign="top">: </td>

                    <td><font style="text-transform: uppercase"><s:textarea rows="7" cols="100" name="keputusansiasatan"></s:textarea></font></td></tr><br>

                   </table><br>
                    
                   <%-- <label>&nbsp;</label>--%>
                    <tr><td>
                            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </td></tr>
                
            </div>

        </fieldset>
    </div>

    </s:form>