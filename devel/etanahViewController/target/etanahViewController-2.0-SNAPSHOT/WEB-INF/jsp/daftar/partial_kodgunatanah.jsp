<%-- 
    Document   : partial_kodgunatanah
    Created on : Jan 15, 2010, 3:17:33 PM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    $(document).ready( function(){
        $('#kodTanah').val($('#kodGunaTanah').val());
        //$('#kodTanah').val();
    });
</script>

<s:form partial="true" beanclass="etanah.view.stripes.KemasukanPerincianHakmilikActionBean">
    <%--<c:if test="${actionBean.p.kodUrusan.kod eq 'HKABS'}">
        <c:set var="disabledbtn" value="disabled"/>
    </c:if>--%>

    <%--<p><label>Kegunaan Tanah : </label>
        <s:select name="hakmilik.kegunaanTanah.kod" id="kodTanah">
            <s:option value="">Sila Pilih</s:option>
            <s:options-collection collection="${actionBean.listKodGunaTanahByKatTanah}" label="nama" value="kod" />
        </s:select>
    </p>--%>
    <p><label>Kegunaan Tanah <c:if test="${actionBean.p.kodUrusan.kod eq 'BMSTM'}"> Bawah Tanah</c:if>: </label>

        <%--<c:if test="${disabledbtn eq 'disabled'}">
            <s:hidden name="kodGunaTanah" value="${actionBean.kodGunaTanah}"/>
        </c:if>--%>
        <%--<s:select name="kodGunaTanah" id="kodTanah" onchange="kiraCukai(this.form,'${actionBean.hakmilik.idHakmilik}');" disabled="${disabledbtn}">--%>
        <s:select name="kodGunaTanah" id="kodTanah" onchange="kiraCukai(this.form,'${actionBean.hakmilik.idHakmilik}');" style="width:200pt">
            <s:option value="">Sila Pilih</s:option>
            <s:options-collection collection="${actionBean.listKodGunaTanahByKatTanah}" label="nama" value="kod" />
        </s:select>

    </p>

</s:form>