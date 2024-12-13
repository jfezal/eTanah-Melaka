<%-- 
    Document   : permohanan_terdahulu
    Created on : Jul 16, 2010, 10:37:03 AM
    Author     : User
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript"></script>

<%--<s:form beanclass="etanah.view.strata.PermohananTerdahuluActionBean">--%>
    <s:form beanclass="etanah.view.strata.PermohonanStrataActionBean">
    <s:messages/>
    <s:errors/>
    <c:if test="${edit}">
    <div class="subtitle">
        <fieldset class="aras1">
             
            <legend>Kemasukan maklumat permohonan terdahulu </legend>
            <p>
                <label>Id permohonan terdahulu :</label>
                <s:text name="idPermohonanTerdahulu" value="" size="40"/>&nbsp;
            </p>
        </fieldset>&nbsp;
    </div>
            <p>
              <label>&nbsp;</label>
             <%-- <a href="urusan?showForm4"<s:submit name="kembali" value="kembali" class="btn"/></a>
              <a href="urusan?showForm6"<s:submit name="Terus" value="Terus" class="btn" /></a>--%>
               <s:button name="kembali" id="back" value="Kembali" class="btn"/>
               <s:button name="terus" id="go" value="Terus" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
   </c:if>
</s:form>