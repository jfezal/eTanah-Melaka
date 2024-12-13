<%-- 
    Document   : Deraf_Perintah_BM_2
    Created on : Jun 20, 2011, 9:56:26 PM
    Author     : Rajesh
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html;charset=windows-1252" import="java.util.*" import="java.text.SimpleDateFormat"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">
$(document).ready( function(){
$(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
$(".datepicker1").datepicker({dateFormat: 'yy'});
});

function ajaxLink(link, update) {
$.get(link, function(data) {
$(update).html(data);
$(update).show();
});
return false;
}
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pengambilan.PerintahBM2ActionBean">
<div  id="hakmilik_details">
<s:messages/>
<s:errors/>
<%--<s:hidden name="show" value="${actionBean.show}"/>--%>

<fieldset class="aras1"><br/>
 <%--   <c:if test="${actionBean.hakmilik ne null && actionBean.permohonanPihak ne null}" > --%>
        <div class="content" align="center">
            <table class="tablecloth">
                <tr>
                    <th>Id Hakmilik</th><th>No Lot/No PT</th><th>Daerah</th><th>Bandar/Pekan/Mukim</th><th>Tuan Tanah</th>
                </tr>
                <tr>
                    <td>
                        ${actionBean.hakmilik.idHakmilik}
                    </td>
                    <td>
                        ${actionBean.hakmilik.lot.nama}${actionBean.hakmilik.noLot}
                    </td>
                    <td>
                        ${actionBean.hakmilik.daerah.nama}
                    </td>
                    <td>
                        ${actionBean.hakmilik.bandarPekanMukim.nama}
                    </td>
                     <td><c:forEach items="${actionBean.permohonanPihakPendepositList}" varStatus="loop" var="pendeposit">
                                <s:link beanclass="etanah.view.stripes.pengambilan.PerintahBM2ActionBean"
                                        event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                    <s:param name="idPihak" value="${pendeposit.pihak.idPihak}"/>
                                    <s:param name="idHakmilik" value="${actionBean.hakmilik.idHakmilik}"/>
                                    
                                    ${pendeposit.pihak.nama}
                                   
                                </s:link><BR>
                                 </c:forEach>
                            </td>
                </tr>
            </table>
        </div>
   <%-- </c:if>--%><br />
</fieldset>
      <c:if test="${showDetails}">
          <fieldset class="aras1">
              <div class="content" align="left">
                  Nama Tuan Tanah : ${actionBean.permohonanPihak.pihak.nama}
                  <table align="center"  width="70%" border="0">
                      <tr>
                          <td width="70" nowrap rowspan="0"><label >Nama Penolong Kanan Pendaftar Mahkamah Tinggi :</label></td>
                          <td >
                           <%--   <c:if test="${actionBean.show eq 'Edit'}">--%> 
                           <s:text name="namaPenolongKananPendaftar" size="40"/><%-- </c:if>--%>
                             <%-- <c:if test="${actionBean.show eq 'Details'}"> 
                             ${actionBean.namaPenolongKananPendaftar}
                             <s:hidden name="namaPenolongKananPendaftar"/> </c:if>--%>
                          </td>
                      </tr>
                  </table>

              </div>
          </fieldset><br/>

              <div align="center">
                  <s:hidden name="idPihak" value="${actionBean.idPihak}"/>
                  <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
                  <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
              </div>
      <br />
       </c:if>

</div>
</s:form>
