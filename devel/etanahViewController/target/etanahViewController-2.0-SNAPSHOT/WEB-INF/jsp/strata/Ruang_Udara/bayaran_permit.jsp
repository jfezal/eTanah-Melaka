<%--
    Document   : bangaran_permit
    Created on : Sep 21, 2010, 12:52:03 AM
    Author     : User
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.strata.BayaranPermitActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Bayaran Permit</legend><br>
            <div class="content" align="center">
                <table border="0" width="80%">
                    <tr><td width="20%"><b>Jumlah Bill Permit</b></td>
                        <td><b>:</b></td>
                        <td><s:text name="jumlahBillPermit" value="" id="jbp" class="normal_text" readonly="readonly"></s:text></td>
                    </tr>
                    <tr><td width="20%"><b>Kadar Bayaran</b></td>
                        <td><b>:</b></td>
                        <td><s:text name="kadarBayaran" id="kb" class="normal_text" readonly="readonly" value="RM 10xtempoh(21)" /> </td>
                    </tr>
                    <tr><td width="20%"><b>Jumlah Bayaran</b></td>
                        <td><b>:</b></td>
                        <td><s:text name="jumlahBayaran" value="" id="jb" class="normal_text" readonly="readonly"></s:text></td>
                    </tr>
                </table></div>
             </fieldset></div>
                     <p align="center">
                        <s:button name="calculate" id="calculate" value="Kira" class="btn" onclick="clear1();"/>
                </p>
  </s:form>