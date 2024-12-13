<%-- 
    Document   : kemasukan_maklumat_kaveat
    Created on : Aug 2, 2010, 3:37:52 PM
    Author     : fikri
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.daftar.Kaveat">
     <s:messages />
     <s:errors />
     <div class="subtitle">
         <fieldset class="aras1">
             <s:hidden name="ulasanOnly" value="true"/>
            <legend>
                Maklumat Kaveat
            </legend>
             <p>
                 <label>Sebab Kaveat :</label>
                 <s:textarea name="ulasan" rows="10" cols="50" value="${ulasan}"/>
             </p>
              <p>
                <label>&nbsp;</label>
                <s:button name="saveKaveatInfo" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>
            </p>
         </fieldset>
     </div>
</s:form>
