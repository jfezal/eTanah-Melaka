<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel=" stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel=" stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel=" stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pelupusan.NotaSiasatanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Nota Siasatan:Bantahan Pembatalan Rizab</legend>
            <p>
                <label><font color="red">*</font>Tarikh Siasatan :</label>
                    <s:text name="tarikhSidang" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy" />
            </p>
            <p>
                <label>Tajuk : </label>
                <s:textarea rows="6" cols="100" name="kand0" class="normal_text" />
            </p>

            <p>
                <label>1. Perihal Permohonan :</label>
                <s:textarea rows="6" cols="100" name="kand1" class="normal_text" />
            </p>
            <p>
			<table>
			<tr>
				<td><label>2. Kedudukan Rizab :</label></td>
                <c:set var="i" value="1" />
                <c:forEach items="${actionBean.latarBelakangList}" var="line">
                    <tr><td></td><td><s:textarea  id="kandungan2${i}"  name="latarBelakangList[${i-1}].kandungan" cols="100"  rows="5" class="normal_text"/>      
                    <c:set var="i" value="${i+1}" /></td></tr>
                </c:forEach>
			</tr>
            </table>
			</p>
            <p>
                <label>3. Asas Bantahan :</label>
                <s:textarea rows="6" cols="100" name="kand3" class="normal_text"/>
            </p>
            
            <!--c:if test="${editPeraku}"-->
                 <p>
                <label>4. Syor / perakuan :</label>
                <s:textarea rows="6" cols="100" name="kand4" class="normal_text"/>
                </p>  
            <!--/c:if-->
            <table align="center">
                <tr><td>
                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </td></tr>
            </table>
        </fieldset>
    </div>
</s:form>
