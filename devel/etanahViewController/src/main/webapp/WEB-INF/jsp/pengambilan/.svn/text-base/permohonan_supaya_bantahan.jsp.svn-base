<%-- 
    Document   : permohonan_supaya_bantahan
    Created on : Sep 28, 2010, 2:30:15 PM
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

function ajaxLink(link, update) {
$.get(link, function(data) {
$(update).html(data);
$(update).show();
});
return false;
}
</script>
<s:form beanclass="etanah.view.stripes.pengambilan.PermohonanSupayaBantahanActionBean">
<div  id="pihak_details">
    <div align="center">
        <table style="width:99.2%" bgcolor="purple">
            <tr><td width="100%" height="20" ><div style="background-color: purple;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;"><font color="white">PERMOHONAN SUPAYA BANTAHAN DIRUJUKKAN KEPADA MAHKAMAH</font></font>
            </div></td></tr>
        </table>
    </div><br /><br />

<s:messages/>
<s:errors/>
<fieldset class="aras1"><br/>
        <legend align="left"><b>Maklumat Hakmilik Permohonan</b></legend>
    <div align="center">
    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                   requestURI="/pengambilan/permohonan_supaya_bantahan" id="line">
        <display:column title="No" sortable="true">${line_rowNum}</display:column>
        <display:column property="hakmilik.noHakmilik" title="No Hakmilik"/>
        <display:column property="hakmilik.noLot" title="No Lot/No PT" />
        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
        <display:column title="Tuan Tanah" >
            <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                <s:link beanclass="etanah.view.stripes.pengambilan.PermohonanSupayaBantahanActionBean"
                        event="pihakDetails" onclick="return ajaxLink(this, '#pihak_details');" >
                    <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>
                    <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>
                    ${senarai.pihak.nama}
                </s:link>
                    <br/>
            </c:forEach>
        </display:column>

    </display:table>
    </div>
</fieldset>
<br /><br />
      <c:if test="${showDetails}">
          <fieldset class="aras1">
                  <legend align="left"><b>Maklumat Hakmilik Permohonan</b></legend>

              <div class="content" align="left">
                  <table align="left"  width="100%">
                      <tr>
                          <td align="left" width="30%"><b>Kepentingan pada tanah tersebut:</b></td>
                      </tr>
                      <br>
                      <tr>
                          <td align="left" class="input1" width="70%"><s:textarea rows="5" cols="150" name="kepentinganTanah"/></td>
                      </tr>
                      <br>
                      <tr>
                          <td align="left" width="30%"><b>Bantahan terhadap:</b></td>
                      </tr>
                      <br>
                      <tr>
                          <td align="left" width="70%">
                              <s:checkbox name="sebabUkurTanah" value="Y" />&nbsp;&nbsp;&nbsp;&nbsp;Ukuran Tanah itu<br>
                              <s:checkbox name="sebabAmnPampasan" value="Y" />&nbsp;&nbsp;&nbsp;&nbsp;Amaun Pampasan<br>
                              <s:checkbox name="sebabPihakPampasan" value="Y"/>&nbsp;&nbsp;&nbsp;&nbsp;Orang yang kepadanya pampasan itu kena bayar<br>
                              <s:checkbox name="sebabUmpukanPampasan" value="Y" />&nbsp;&nbsp;&nbsp;&nbsp;Umpukan Pampasan<br>
                          </td>
                      </tr>
                      <br>
                      <tr>
                          <td align="left" width="30%" rowspan="1"><b>Alasan-alasan bantahan adalah seperti beriku:</b></td>
                      </tr>
                      <br>
                      <tr>
                          <td align="left" width="70%"><s:textarea name="alasanBantah" rows="5" cols="150"/></td>
                      </tr>
                  </table>
                      <br>
                      <br>
              </div>
          </fieldset><br>
                
         
              <br/><br/>

              <div align="center">
                  <s:hidden name="idPihak" value="${actionBean.idPihak}"/>
                  <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
                  <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
              </div>

      
      <br />
       </c:if>

</div>
</s:form>
