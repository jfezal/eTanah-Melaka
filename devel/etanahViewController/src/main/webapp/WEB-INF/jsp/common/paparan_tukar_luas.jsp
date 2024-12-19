<%-- 
    Document   : paparan_tukar_luas
    Created on : Oct 8, 2010, 3:56:56 PM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">
    function kiraLuas(f)
    {
        var luas = $('#luas').val();
        if(luas != ''){
            var url = '${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?kira';
            var q = $(f).formSerialize();
            $.post(url,q,
            function(data){
                $('.subtitle').html(data);
            },'html');
    <%-- var unit = $('#kodUOMkepada :selected').text();
     $('.unitLuas').html(unit);--%>
             }else{
                 alert('Sila Masukan Luas');
             }
         }
         $(document).ready(function() {
             maximizeWindow();
        
             //alert(unit);
         });
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.KemasukanPerincianHakmilikActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Tukar Unit Luas
            </legend>
            <p>
                <label>Luas :</label>
                <s:text name="luasDari" id= "luas"/>                              
            </p>
            <p>
                <label>Dari : </label>
                <s:select  style="text-transform:uppercase" id="kodUOMdari" name="kodUnitLuasDari" onchange="kiraLuas(this.form);">
                    <%--<s:options-collection collection="${list.senaraiKodUOMByLuas}" label="nama" value="kod"/>--%>
                    <s:option value="H">Hektar</s:option>
                    <s:option value="M">Meter Persegi</s:option>
                    <s:option value="K">Kaki Persegi</s:option>
                    <s:option value="E">Ekar</s:option>
                </s:select>
            </p>
            <p>
                <label>Kepada : </label>
                <s:select  style="text-transform:uppercase" id="kodUOMkepada" name="kodUnitLuasKepada" onchange="kiraLuas(this.form);">
                    <%--<s:options-collection collection="${list.senaraiKodUOMByLuas}" label="nama" value="kod"/>--%>
                    <s:option value="H">Hektar</s:option>
                    <s:option value="M">Meter Persegi</s:option>
                    <s:option value="K">Kaki Persegi</s:option>
                    <s:option value="E">Ekar</s:option>
                </s:select>
            </p>
            <p>
                <label>&nbsp;</label>
                <fmt:formatNumber pattern="0.####" value="${actionBean.totalLuas}"/>
                <c:if test="${actionBean.kodUnitLuasKepada eq 'H'}"> Hektar </c:if>
                <c:if test="${actionBean.kodUnitLuasKepada eq 'M'}"> Meter Persegi </c:if>
                <c:if test="${actionBean.kodUnitLuasKepada eq 'K'}"> Kaki Persegi </c:if>
                <c:if test="${actionBean.kodUnitLuasKepada eq 'E'}"> Ekar </c:if>
                &nbsp;
            </p>
            <p>
                <label>&nbsp;</label>
                <%--<s:button name="Kira" id="kira" value="Kira" class="btn" onclick="kiraLuas(this.form);"/>&nbsp;--%>
                <s:button name="" value="Tutup" class="btn" onclick="self.close();"/>
            </p>
        </fieldset>
    </div>

</s:form>
