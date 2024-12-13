<%-- 
    Document   : pelan_sp
    Created on : Aug 26, 2012, 6:42:37 PM
    Author     : Navin
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


        <s:form beanclass="etanah.view.stripes.pelupusan.PelanSPActionBean">
    <s:messages/>
    <s:errors/>
    <fieldset class="aras1">
        <legend>Maklumat Pelan SP</legend>
            <div align="center">
                <table width="50%" border="0">
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>Tarikh Terima Pelan SP :</td>
                        <c:if test="${edit}">
                        <td colspan="2">
                            <s:text name="permohonanLaporanKawasan.tarikhTerimaWarta" class="datepicker" formatPattern="dd/MM/yyyy"/>
                        </td>
                        </c:if>
                        <c:if test="${!edit}">
                         <td colspan="2">
                            <s:text name="permohonanLaporanKawasan.tarikhTerimaWarta" formatPattern="dd/MM/yyyy"/>
                         </td>   
                        </c:if>
                    </tr>
                     <tr>
                        <td>&nbsp;</td>
                        <td>No Pelan SP :</td>
                        <td colspan="2">
                            <s:text name="permohonanLaporanKawasan.noPelanWarta" size="20"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <tr>
                        <c:if test="${edit}">
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td colspan="2">
                            <s:button name="simpanPelanSP" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </td>
                        </c:if>
                    </tr>
                    
                </table>
            </div>
    </fieldset>
  </s:form>


