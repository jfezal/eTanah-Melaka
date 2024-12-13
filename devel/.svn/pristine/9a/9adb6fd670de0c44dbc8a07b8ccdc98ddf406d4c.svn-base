<%-- 
    Document   : Borang_7
    Created on : 17 October 2011 6:07 PM
    Author     : Shazwan
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function test(f) {
        $(f).clearForm();
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.stripes.pelupusan.Borang7ActionBean" id="clear">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend align="justify">Permit Tanah Pertanian</legend>
            <br>
            <c:if test="${actionBean.permit.noPermit ne null}">
                <p>
                    <label>No.Permit :</label>
                        ${actionBean.permit.noPermit}
                    <%--<s:text name="permit.noPermit" size="20" maxlength="10" />
                    <s:hidden name="lupusPermit.id"/>--%>
                </p>
            </c:if>
            <p>
                <label>Tarikh Dikeluarkan :</label>
                <c:if test="${!actionBean.viewOnly}">
                    <s:text formatPattern="dd/MM/yyyy" name="permit.tarikhPermit" class="datepicker" id="tarikh"/>
                </c:if>
                 <c:if test="${actionBean.viewOnly}">
                     <s:format formatPattern="dd/MM/yyyy" value="${actionBean.permit.tarikhPermit}"/>                    
                </c:if>
            </p>
            <p>
                <label>Bayaran :</label>
                RM ${actionBean.mohonTuntutKos.amaunTuntutan}
            </p>

        </fieldset>
        <fieldset class="aras1">
            <legend> Jadual</legend>
            <p>
                <label>Tarikh mula :</label>
                <c:if test="${!actionBean.viewOnly}">
                   <s:text formatPattern="dd/MM/yyyy" name="permit.tarikhPermitMula" class="datepicker" id="tarikhMula" />
                </c:if>
                 <c:if test="${actionBean.viewOnly}">
                     <s:format formatPattern="dd/MM/yyyy" value="${actionBean.permit.tarikhPermitMula}"/>                    
                </c:if>
                
            </p>
             <p>
                 <label>Tarikh tamat :</label>
                  <c:if test="${!actionBean.viewOnly}">
                   <s:text formatPattern="dd/MM/yyyy" name="permit.tarikhpermitAkhir" class="datepicker" id="tarikhTamat"/>
                </c:if>
                 <c:if test="${actionBean.viewOnly}">
                     <s:format formatPattern="dd/MM/yyyy" value="${actionBean.permit.tarikhpermitAkhir}"/>                    
                </c:if>
                
            </p>
            <p>
                <label>Tujuan Permit :</label>
                <c:if test="${actionBean.mohonPermitItem ne null}">
                    <c:if test="${actionBean.mohonPermitItem.kodItemPermit ne null}">
                     ${actionBean.mohonPermitItem.kodItemPermit.nama}
                    </c:if>
                    <c:if test="${actionBean.mohonPermitItem.kodItemPermit eq null}">
                         &nbsp;
                    </c:if>
                </c:if>
                <c:if test="${actionBean.mohonPermitItem eq null}">
                    &nbsp;
                </c:if>
                <%--<s:text name="lupusPermit.strukturBolehBina" size="50" maxlength="30" />--%>
            </p>
             <p>
                <label>Peruntukan tambahan :</label>
                <c:if test="${!actionBean.viewOnly}">
                   <s:textarea name="permit.peruntukanTambahan" cols="47" />
                </c:if>
                 <c:if test="${actionBean.viewOnly}">
                    ${actionBean.permit.peruntukanTambahan}                    
                </c:if>
                
            </p>
            <label>&nbsp;</label>
            <p>
                <c:if test="${!actionBean.viewOnly}">
                   <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    <s:button name="reset" value="Isi Semula"   class="btn" onclick="return test();"/>
                </c:if>
            </p>
        </fieldset>
    </div>
</s:form>
