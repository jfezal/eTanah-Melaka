<%-- 
    Document   : permohonan_supaya_bantahan
    Created on : June 03, 2011, 12:01:23 PM
    Author     : massita
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
<s:form beanclass="etanah.view.stripes.pengambilan.PermohonanSupayaBantahan_caterOneTTOnlyActionBean">
<div  id="hakmilik_details">
<s:messages/>
<s:errors/>
    <div align="center">
        <table style="width:99.2%" bgcolor="purple">
            <tr><td width="100%" height="20" ><div style="background-color: purple;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;"><font color="white">PERMOHONAN SUPAYA BANTAHAN DIRUJUKKAN KEPADA MAHKAMAH</font></font>
            </div></td></tr>
        </table>
    </div><br /><br />
<s:hidden name="idPermohonanPihak" value="${actionBean.permohonanPihak.idPermohonanPihak}"/>
<fieldset class="aras1"><br/>
     <c:if test="${actionBean.hakmilik ne null && actionBean.permohonanPihak ne null}" >
        <legend align="left"><b>Maklumat Hakmilik Permohonan</b></legend>
    <div align="center">
    <table class="tablecloth">
                <tr>
                    <th>No Hakmilik</th><th>No Lot/No PT</th><th>Daerah</th><th>Bandar/Pekan/Mukim</th><th>Tuan Tanah</th>
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
                    <td>
                        <s:link beanclass="etanah.view.stripes.pengambilan.PermohonanSupayaBantahan_caterOneTTOnlyActionBean"
                                event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                            <s:param name="idPihak" value="${actionBean.permohonanPihak.pihak.idPihak}"/>
                            <s:param name="idHakmilik" value="${actionBean.hakmilik.idHakmilik}"/>
                            ${actionBean.permohonanPihak.pihak.nama}
                        </s:link>
                    </td>
                </tr>
            </table>
        </div>
     </c:if>
</fieldset>
      <c:if test="${showDetails}">
          <fieldset class="aras1">
                  <%--<legend align="left"><b>Maklumat Hakmilik Permohonan</b></legend>--%>
              <div class="content" align="left">
                  <table align="left"  width="100%">
                      <tr>
                          <td align="left" width="30%"><b>1. Kepentingan pada tanah tersebut:</b></td>
                      </tr>
                      <tr><td></td></tr>
                      <tr>
                          <td align="left" class="input1" width="70%"><s:textarea rows="5" cols="150" name="kepentinganTanah"/></td>
                      </tr>
                      <tr><td></td></tr>
                      <tr>
                          <td align="left" width="30%"><b>2. Bantahan terhadap:</b></td>
                      </tr>
                      <tr><td></td></tr>
                      <tr>
                          <td align="left" width="70%">
                              <s:checkbox name="sebabUkurTanah" value="Y" />&nbsp;&nbsp;&nbsp;&nbsp;Ukuran Tanah itu<br>
                              <s:checkbox name="sebabAmnPampasan" value="Y" />&nbsp;&nbsp;&nbsp;&nbsp;Amaun Pampasan<br>
                              <s:checkbox name="sebabPihakPampasan" value="Y"/>&nbsp;&nbsp;&nbsp;&nbsp;Orang yang kepadanya pampasan itu kena bayar<br>
                              <s:checkbox name="sebabUmpukanPampasan" value="Y" />&nbsp;&nbsp;&nbsp;&nbsp;Umpukan Pampasan<br>
                          </td>
                      </tr>
                      <tr><td></td></tr>
                      <tr>
                          <td align="left" width="30%" rowspan="1"><b>3. Alasan-alasan bantahan adalah seperti berikut:</b></td>
                      </tr>
                      <tr>
                          <td align="left" width="70%"><s:textarea name="alasanBantah" rows="5" cols="150"/></td>
                      </tr>
                  </table>
              </div>
          </fieldset>
              <br/>
              <div align="center">
                  <s:hidden name="idPihak" value="${actionBean.idPihak}"/>
                  <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
                  <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
              </div>
       </c:if>
</div>
</s:form>
