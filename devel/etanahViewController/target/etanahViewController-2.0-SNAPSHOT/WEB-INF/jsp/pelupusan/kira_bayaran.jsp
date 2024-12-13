<%--
    Document   : kira_bayaran.jsp
    Created on : Oct 07, 2013 10:42:28 AM
    Author     : 
    Edit       : Hayyan
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<%-- carian hakmilik start--%>


<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form id="form" name="form" beanclass="etanah.view.stripes.pelupusan.MaklumatPelelonganActionBean">
    <s:messages/>
    <s:errors/>
    
    <div class="subtitle" >
        <fieldset class="aras1">         
            <legend>Maklumat Bayaran Deposit</legend>
                <table cellspacing="5" cellpadding="5">
                    <tr>
                        <td>&nbsp;</td>
                        <td><label>Harga Rizab (RM) :</label></td>
                        <td><s:format formatPattern="#,##0.00" value="${actionBean.hargaRizab}"/></td>                     
                    </tr><tr>
                        <td>&nbsp;</td>
                        <td><label>Harga Bidaan (RM) :</label></td>
                        <td><s:format formatPattern="#,##0.00" value="${actionBean.hargaBidaan}"/></td>                     
                    </tr><tr>
                        <td>&nbsp;</td>
                        <td><label>Deposit Awal (RM) :</label></td>
                        <td><s:format formatPattern="#,##0.00" value="${actionBean.deposit}"/></td>                     
                    </tr><tr>
                        <td>&nbsp;</td>
                        <td><label>Bayaran Deposit 25% (RM) :</label></td>
                        <td><s:format formatPattern="#,##0.00" value="${actionBean.depositBayaran}"/></td>                     
                    </tr>
                    <c:if test="${actionBean.stageId eq '26PenyediaanSrtKelulusan'}">
                    <tr>
                        <td>&nbsp;</td>
                        <td><label>Bayaran Baki 75% (RM) :</label></td>
                        <td><s:format formatPattern="#,##0.00" value="${actionBean.bakiBayaran}"/></td>                     
                    </tr>
                    </c:if>
                    <tr>
                        <td>&nbsp;</td><td>&nbsp;</td>
                        <td><s:button name="saveCalc" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form,this.name,'page_div')"/></td>
                        <td>&nbsp;</td>
                    </tr>
                    
                </table>
        </fieldset>
    </div>
    
</s:form>
