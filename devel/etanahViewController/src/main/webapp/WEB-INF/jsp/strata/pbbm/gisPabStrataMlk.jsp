<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:form beanclass="etanah.view.strata.GisMLKStrataActionBean">
    <s:messages/>
    <s:errors/>
    <div>
        <fieldset class="aras1">
            <c:if test="${manual}"><p><font size="2" color="Red"> * Tab ini hanya berfungsi sekiranya menggunakan fail XML(Jadual Petak)</font></p></c:if>
            <c:if test="${!manual}">
                <legend>${actionBean.gisStage}</legend>
                <br/>
                <c:if test="${actionBean.isDokumenExist && actionBean.kaedah eq '1'}">
                    <font size="2" color="Red">${actionBean.arahan}</font>
                </c:if>
                <c:if test="${actionBean.isDokumenExist && actionBean.kaedah eq '2'}">
                    <font size="2" color="Red">Arahan: Fail dari JUPEM telah diterima. Sila klik butang Muat Turun</font>
                </c:if>
                <c:if test="${!actionBean.isDokumenExist}">
                    <font size="2" color="Red">Fail dari JUPEM masih belum di terima. </font>
                </c:if>

                <c:if test="${actionBean.isDokumenExist && actionBean.kaedah eq '2'}">
                    <br/>
                    <br/>
                    <s:button name="CheckJupem" id="btnClick" value="Muat Turun" class="btn" onclick="doSubmit(this.form,this.name,'page_div')" />
                </c:if>
            </c:if>
        </fieldset>
    </div>

</s:form>