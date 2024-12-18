<%-- 
    Document   : tugasan.jsp
    Created on : Jul 28, 2017, 11:11:04 PM
    Author     : mohd.faidzal
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
   
    function refreshDiv(){
        var id = '${actionBean.idPermohonan}';
        var url = '${pageContext.request.contextPath}/utiliti/utilitiPemohonUtama?check&idPermohonan='+id ;
        $.get(url,
        function(data){
            $("#refresh").replaceWith($('#refresh', $(data)));
        },'html');
    }
    
    
</script>
<s:messages/>
<s:errors/>
<s:form beanclass="etanah.view.stripes.pembangunan.tugasan.TugasanActionBean">
    <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.idPermohonan}"/>

    
<!--        <div class="subtitle">
            <fieldset class="aras1">
                <br>
                <legend>Sila Masukkan ID Permohonan Untuk Carian</legend>

                <label for="idPermohonan"><em>*</em>ID Permohonan :</label>                   
                <input type="text" name="idPermohonan" onkeyup="this.value=this.value.toUpperCase();"/>

                    <s:submit name="checkPermohonan" value="Cari" class="btn"/>             
               
            </fieldset>
        </div>-->
   
        <fieldset class="aras1">
            <legend>
                Senarai Permohonan 
            </legend>
            <div class="content" align="center" id="refresh">
                <display:table class="tablecloth" name="${actionBean.listPermohonan}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/utiliti/tugasan">
                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">
                       ${line_rowNum} </display:column>
                    <display:column title="Id Permohonan" style="vertical-align:baseline"><a href="${pageContext.request.contextPath}${line.kodPeringkat.url}&idPermohonan=${line.mohon.idPermohonan}">${line.mohon.idPermohonan}</a> </display:column>
                    <display:column property="mohon.kodUrusan.nama" title="Urusan" style="vertical-align:baseline">${line.mohon.kodUrusan.nama}</display:column>
                    <display:column property="kodPeringkat.nama" title="Peringkat" style="vertical-align:baseline">${line.kodPeringkat.nama}</display:column>
                    
                </display:table>
            </div>
        </fieldset>
</s:form>
