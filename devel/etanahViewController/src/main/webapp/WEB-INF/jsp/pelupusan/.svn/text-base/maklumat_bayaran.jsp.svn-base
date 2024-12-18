<%-- 
    Document   : maklumat_bayaran
    Created on : Apr 4, 2011, 12:21:29 PM
    Author     : Rohans
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">



<s:form beanclass="etanah.view.stripes.pelupusan.MaklumatBayaranActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Bayaran
            </legend>
            <table>
                <tr>
                    <td><label>Bayaran :</label>
                    <td>${actionBean.transaksi.amaun}</td>
                </tr>
               
                <tr>
                    <td> <label>No Resit:</label> </td>
                    <td>${actionBean.transaksi.dokumenKewangan}</td>
                   
                </tr>

                 <tr>
                    <td><label>Tarikh  :</label></td>
                    <td><fmt:formatDate value="${actionBean.transaksi.tarikhMasuk}" pattern="dd/MM/yyyy"/>
                      
                </tr>
                
            </table>
       </fieldset>
    </div>
   

</s:form>