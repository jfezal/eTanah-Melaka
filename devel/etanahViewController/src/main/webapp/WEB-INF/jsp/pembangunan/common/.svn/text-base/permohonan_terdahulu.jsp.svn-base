<%-- 
    Document   : permohonan_terdahulu
    Created on : Oct 7, 2011, 11:23:41 AM
    Author     : nursyazwani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>

<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:150px;
        margin-right:0.5em;
        text-align:right;
        width:15em;
        vertical-align:top;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:40em;
    }
</style>
<script type="text/javascript">
    function cariPermohonan(){
        <%--if(confirm('Adakah anda pasti?')) {--%>
                var val = $('#idMohonAsal').val();
                <%--alert("val = " + val);--%>
                var url = '${pageContext.request.contextPath}/pembangunan/permohonanSebelum?cari&idMohonAsal='+val;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            <%--}--%>
    }
    function cariBayar(){
        <%--if(confirm('Adakah anda pasti?')) {--%>
                var val = $('#idMohonAsal').val();
                <%--alert("val = " + val);--%>
                var url = '${pageContext.request.contextPath}/pembangunan/permohonanSebelum?cariBayar&idMohonAsal='+val;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            <%--}--%>
    }
</script>


<s:form beanclass="etanah.view.stripes.pembangunan.PermohonanSebelumActionBean">
    <s:messages/>
    <s:errors/>
  <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Permohonan Sebelum Rayuan
            </legend>
            <div class="content" align="center">
                <table width="80%" border="0" cellspacing="5">
                    <tr><td id="tdLabel">ID Permohonan Terdahulu : </td>
                        <td><s:text name="idMohonAsal" maxlength="20" size="30" id="idMohonAsal"/> &nbsp;
                            <c:if test="${actionBean.permohonanCurr.kodUrusan.kod ne 'RBPA'}"><s:button name="cari" id="carian" class="btn" value="Cari" onclick="cariPermohonan();"/></c:if>
                        <c:if test="${actionBean.permohonanCurr.kodUrusan.kod eq 'RBPA'}"><s:button name="cariBayar" id="carian" class="btn" value="Cari" onclick="cariBayar();"/></c:if></td>
                    </tr>
                    <tr><td id="tdLabel">Jenis Permohonan :</td>
                        <td id="tdDisplay">${actionBean.mohonAsal.idPermohonanAsal.kodUrusan.nama}</td>
                    </tr>
                    <tr><td id="tdLabel">Tarikh Notis :</td>
                        <td id="tdDisplay">${actionBean.tarikhNotis}</td>
                    </tr>
                </table>

            </div>
        </fieldset>
  </div>
</s:form>
