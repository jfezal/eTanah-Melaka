<%-- 
    Document   : statusPenerimaanPampasan
    Created on : 28-Sep-2010, 18:37:39
    Author     : nordiyana
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript">
function refreshPagePenerimaanBorang(){
var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_notis?refreshpage';
$.get(url,
function(data){
$('#page_div').html(data);
},'html');
}

function ajaxLink(link, update) {
$.get(link, function(data) {
$(update).html(data);
$(update).show();
});
return false;
}
</script>
<s:form beanclass="etanah.view.stripes.pengambilan.StatusPenerimaanPampasanActionBean">
<div  id="hakmilik_details">
    <div align="center">
        <table style="width:99.2%" bgcolor="purple">
            <tr><td width="100%" height="20" ><div style="background-color: purple;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;"><font color="white">PENGAMBILAN: STATUS PENERIMAAN BAYARAN PAMPASAN </font></font>
            </div></td></tr>
        </table>
    </div><br /><br />

<s:messages/>
<s:errors/>

<fieldset class="aras1"><br/>
    <legend align="left"><b>Status Penerimaan Pampasan</b></legend>
    <div align="center">
    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                   requestURI="/pengambilan/bayaranPampasan31a" id="line">
        <display:column title="No" sortable="true">${line_rowNum}</display:column>
        <display:column title="ID Hakmilik">
            <s:link beanclass="etanah.view.stripes.pengambilan.StatusPenerimaanPampasanActionBean"
                    event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}</s:link>
        </display:column>
        <display:column property="hakmilik.noLot" title="No Lot/No PT" />
        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
        <display:column property="hakmilik.bandarPekanMukim.nama" title="Orang Berkepentingan" />
        <display:column property="hakmilik.bandarPekanMukim.nama" title="Status" />
    </display:table>
    </div>
</fieldset>
      <br /><br />

      <c:if test="${actionBean.hakmilik ne null}">
          <fieldset class="aras1">
              <legend>Status Penerimaan</legend><br />
              <div align="center">
              <display:table class="tablecloth" name="${actionBean.hakmilik}" cellpadding="0" cellspacing="0" id="tbl1">
                    <display:column title="No" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                    <display:column property="idHakmilik" title="ID Hakmilik" />
                    <display:column property="noLot" title="Nombor Lot/PT" />
                    <display:column title="Daerah" property="daerah.nama" class="daerah" />
                    <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column title="Orang Berkepentingan" style="vertical-align:baseline">
                        <c:forEach items="${actionBean.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                            <%--<c:if test="${senarai.jenis.kod eq 'PM'}">--%>
                                <s:link beanclass="etanah.view.stripes.pengambilan.StatusPenerimaanPampasanActionBean"
                                        event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                    <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>${senarai.pihak.nama}
                                </s:link>
                                <br/>
                                No KP ${senarai.pihak.noPengenalan}
                            <%--</c:if>--%>
                            <br/>
                        </c:forEach>
                    </display:column>
                    <display:column title="Status" style="vertical-align:baseline">
                         <c:forEach items="${actionBean.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                               <s:select name="kodStatus[${line_rowNum-1}]" id="kodStatus${line_rowNum-1}" onchange="validateKodStatus(this,${line_rowNum - 1})"><font color="red">*</font>
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:option value="H">Terima</s:option>
                                    <s:option value="M">Amanah Raya</s:option>
                                    <s:option value="Y">Mahkamah</s:option>
                                </s:select>
                            </c:forEach>
                   
                    </display:column>
                    <display:column title="Perbicaraan Pengambilan No" style="vertical-align:baseline">
                        ${actionBean.hakmilikPerbicaraan.idPerbicaraan}
                    </display:column>
                   <display:column title="No Warta" style="vertical-align:baseline">
                        ${actionBean.permohonanPengambilan.noWarta}
                    </display:column>
                    <display:column title="Tarikh Warta" style="vertical-align:baseline">
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonanPengambilan.tarikhWarta}"/>
                    </display:column>
              </display:table>
              </div>
                <br /><br />
          </fieldset><br />
      </c:if>
      <br />
      

</div>
</s:form>