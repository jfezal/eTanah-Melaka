<%--
    Document   : senarai_aduan
    Created on : Apr 7, 2010, 11:36:04 AM
    Author     : aminah.abdmutalib
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript">

    function refreshPagesenarai(){
       
        var url = '${pageContext.request.contextPath}/penguatkuasaan/senarai_aduan?refreshpage';
        window.location = url;
        
    }
  function textCounter(field, countfield, maxlimit) {
if (field.value.length > maxlimit) // if too long...trim it!
field.value = field.value.substring(0, maxlimit);
// otherwise, update 'characters left' counter
else
countfield.value = maxlimit - field.value.length;
}

</script>
<s:errors/>
<s:messages/>
<br>
<div class="subtitle">
    <fieldset class="aras1" id="locate">
        <legend>Carian</legend>
        <div class="content" align="center">
            <s:form action="/penguatkuasaan/senarai_aduan" id="senarai_aduan">
                <table>
                    <tr>
                        <td class="rowlabel1">ID Aduan :</td>
                        <td class="input1"><s:text name="idInsert" onkeyup="this.value=this.value.toUpperCase();"/> </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">&nbsp;</td>
                        <td ><font color="red">ATAU</font></td>
                    </tr>
                    <%--<tr>
                        <td class="rowlabel1">Tarikh Dari :</td>
                        <td class="input1">
                            <s:text name="fromDate17" id="datepicker" class="datepicker"/>
                        </td>
                    </tr>--%>
                    <tr>
                        <td class="rowlabel1">Tarikh Dari :</td>
                        <td class="input1">
                            <s:text name="fromDate"  formatType="date" formatPattern="dd/MM/yyyy" id="datepicker" class="datepicker"/>
                            <font size="1" color="red">[hh/bb/tttt]</font>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <s:submit name="searchAllPermohonan" value="Cari" class="btn"/>
                           <s:button  name="" value="Isi Semula" class="btn" onclick="refreshPagesenarai();"/>
                          
                        </td>
                    </tr>
                </table>
            </s:form>
            <br>
        </div>
    </fieldset>
</div>
<br>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>Senarai Aduan</legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiAduan}"  id="line" pagesize="10" style="width:95%" requestURI="${pageContext.request.contextPath}/penguatkuasaan/senarai_aduan">
                <display:column title="Bil" class="bil${line_rowNum}" style="${bcolor}">${line_rowNum}</display:column>
                <display:column title="ID Aduan" style="${bcolor}">
                    <a href="${pageContext.request.contextPath}/penguatkuasaan/senarai_aduan?peruntukanSeksyen&idPermohonan=${line.idPermohonan}" style="${bcolor}">${line.idPermohonan}</a>
                </display:column>
                
                <%--<display:column property="idPermohonan" title="ID Permohonan" class="idPermohonan${line_rowNum}" style="${bcolor}"/>--%>
                <display:column title="Tarikh Terima" style="${bcolor}"><fmt:formatDate pattern="dd/MM/yyyy hh:mm:ss" value="${line.infoAudit.tarikhMasuk}"/></display:column>
                </display:table>
            </div>
    </fieldset>
</div>

