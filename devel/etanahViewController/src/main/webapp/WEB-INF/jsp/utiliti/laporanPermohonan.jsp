<%-- 
    Document   : laporanPermohonan
    Created on : Sep 2, 2015, 12:47:30 PM
    Author     : hamizah
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<s:form beanclass="etanah.view.utility.CarianPermohonanActionBean" name="utilityMohonForm">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <p> <label for="kodJabatan"><font color="red">*</font>Modul :</label>
                 <s:select name="jabatan" id="jabatan" onchange="selectJabatan">
                        <s:option value=""> Sila Pilih </s:option>
                    <c:forEach items="${actionBean.senaraiKodJabatan}" var="line">
                        <s:option value="${line.kod}">${line.nama}</s:option>
                    </c:forEach>
                </s:select>
                </p>
                <p><label for="kodUrusan"><font color="red">*</font>Urusan :</label>
                    <s:select name="urusan" id="urusan">
                        <s:option value=""> Sila Pilih </s:option>
                    <c:forEach items="${actionBean.senaraiKodUrusan}" var="line">
                        <s:option value="${line.kod}">${line.nama}</s:option>
                    </c:forEach>
                </s:select>
                <s:submit name="searchPermohonan" id="searchPermohonan" value="Papar" class="btn"/>
            </p>
        </fieldset>
    </div>
</s:form>
