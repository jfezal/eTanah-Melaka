<%-- 
    Document   : maklumat_deposit
    Created on : Dec 19, 2012, 11:04:01 AM
    Author     : haqqiem
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN" http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" >
</script>
<s:form beanclass="etanah.view.stripes.hasil.MaklumatBayaranBalikDepositActionBean">
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Deposit</legend>
            <div align="center">
                <display:table name="${actionBean.senaraiAkaun}" id="row" class="tablecloth">
                    <display:column title="Bil."><div align="center">${row_rowNum}.</div></display:column>
                    <display:column title="Nama Pendeposit" property="pemegang.nama"/>
                    <display:column property="infoAudit.tarikhMasuk" style="text-align:center" title="Tarikh" format="{0,date,dd/MM/yyyy hh:mm aa}"/>
                    <display:column title="Cawangan" property="cawangan.name"/>
                    <display:column title="Nombor Akaun Deposit" property="noAkaun"/>
                    <display:column title="Amaun (RM)" class="number" property="baki" format="{0,number, 0.00}" style="text-align:right"/>
                    <c:choose>
                        <c:when test="${actionBean.flag}">
                            <display:column title="Status">
                                <s:select name="status[${row_rowNum - 1}]" id="status${row_rowNum - 1}">
                                    <s:option label="Aktif" value="A" />
                                    <s:option label="Tidak Aktif" value="B" />
                                </s:select>
                            </display:column>
                        </c:when>
                        <c:otherwise>
                            <display:column title="Status">
                                ${row.status.kod == 'A' ? 'Aktif' : 'Tidak Aktif'}
                            </display:column>
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${actionBean.flag ne true}">
                        <display:column title="Tarikh Warta" style="text-align:center">
                            <s:text name="trhWarta[${row_rowNum - 1}]" id="trhWarta${row_rowNum - 1}" class="datepicker" style="width:100px;" readonly="true"/>
                        </display:column>
                        <display:column title="No. Warta" style="text-align:center">
                            <s:text name="warta[${row_rowNum - 1}]" id="warta${row_rowNum - 1}" style="width:150px;"/>
                        </display:column>
                    </c:if>
                </display:table >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </div>
            <p align="right">
                <c:choose>
                    <c:when test="${actionBean.flag}">
                        <s:button class="btn" id="generate" onclick="doSubmit(this.form, this.name, 'page_div');" name="save" value="Simpan"/>
                    </c:when>
                    <c:otherwise>
                        <s:button class="btn" id="generate1" onclick="doSubmit(this.form, this.name, 'page_div');" name="saveWarta" value="Simpan"/>
                    </c:otherwise>
                </c:choose>
            </p>
        </fieldset>
    </div>
</s:form>