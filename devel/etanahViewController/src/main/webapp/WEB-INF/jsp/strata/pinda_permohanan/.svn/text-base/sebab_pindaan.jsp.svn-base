<%-- 
    Document   : sebab_pindaan
    Created on : Jul 29, 2010, 5:13:32 PM
    Author     : User
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript"></script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.strata.SebabSebabActionBean" name="form1">
    <s:messages/>
    <s:errors/>
<%--<c:if test="${edit}">--%>
    <div class="subtitle">
<!--            <p>
                <label>Id permohonan terdahulu :</label>
                ${idPermohonanTerdahulu}

            </p>&nbsp;-->
            
           <fieldset class="aras1">
                <legend> Sebab Sebab Pindaan</legend>

                <p>
                    <label>Sebab-sebab Pindaan :</label>
                    <s:textarea readonly="${!edit}"  name="sebab" cols="100" rows="5" class="normal_text" id="catatan"/>&nbsp;
                </p>&nbsp;
                <c:if test="${edit}">
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
                </c:if>
           </fieldset>
       </div>
<%--</c:if>--%>
</s:form>
