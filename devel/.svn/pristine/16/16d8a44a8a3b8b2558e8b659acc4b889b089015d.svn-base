<%-- 
    Document   : RujukanPadaMahkamah
    Created on : Sep 29, 2010, 12:01:23 PM
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

function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;

    }
function ajaxLink(link, update) {
$.get(link, function(data) {
$(update).html(data);
$(update).show();
});
return false;
}
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pengambilan.RujukanPadaMahkamahActionBean">
<s:messages/>
<s:errors/>
<div id="hakmilik_details">
    <div align="center">
        <table style="width:99.2%" bgcolor="purple">
            <tr><td width="100%" height="20" ><div style="background-color: purple;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;"><font color="white">RUJUKAN PADA MAHKAMAH </font></font>
            </div></td></tr>
        </table>
    </div><br /><br />



<fieldset class="aras1">
    <legend align="left"><b>Maklumat Hakmilik Permohonan</b></legend>
    <div align="center">
    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                   requestURI="/pengambilan/rujukanPadaMahkamah" id="line">
        <display:column title="No" sortable="true">${line_rowNum}</display:column>
        <display:column property="hakmilik.noHakmilik" title="No Hakmilik"/>
        <display:column property="hakmilik.noLot" title="No Lot/No PT" />
        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
        <display:column title="Tuan Tanah" >
            <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                <s:link beanclass="etanah.view.stripes.pengambilan.RujukanPadaMahkamahActionBean"
                        event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
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
      <c:if test="${showDetails}">
          <fieldset class="aras1">
              <legend align="left"><b>Maklumat Hakmilik Permohonan</b></legend>
              <div class="content" align="left">
          <table align="left"  width="100%">
                      <tr>
                          <td align="left" width="30%"><b>Ringkasan bantahan:</b></td>
                      </tr>
                      <tr>
                          <td align="left" class="input1" width="70%"><s:textarea rows="5" cols="150" name="ringkasanBantah"/></td>
                      </tr>
                      <br>
                      <tr>
                          <td align="left" width="30%"><b>Kedudukan dan luasnya tanah, dan butir butir mengenai apa-apa pokok, bangunan atau tanaman-tanaman yang sedia ada diatasnya :</b></td>
                      </tr>
                      <tr>
                          <td align="left" class="input1" width="70%"><s:textarea rows="5" cols="150" name="butiranTanah"/></td>
                      </tr>
                      <br>
                      <tr>
                          <td align="left" width="30%"><b>Notis-notis yang berikut telah disampaikan  kepada pihak-pihak yang berkepentingan:</b></td>
                      </tr>
                      <tr>
                          <td align="left" class="input1" width="70%"><s:textarea rows="5" cols="150" name="notis"/></td>
                      </tr>
                      <br>
                      <tr>
                          <td align="left" width="30%"><b>Pernyataan-pernyataan secara bertulis yang berikut telah dibuat atau diserahkan oleh ppihak-pihak yang berrikut yang berkepentingan:</b></td>
                      </tr>
                      <tr>
                          <td align="left" class="input1" width="70%"><s:textarea rows="5" cols="150" name="pernyataan"/></td>
                      </tr>
                      <br>
                      <tr>
                          <td align="left" width="30%" rowspan="1"><b>Amaun yang ditawarkan kerana kerosakan di bawah seksyen 6 Akta tersebut adalah sebanyak RM :</b></td>
                      </tr>
                      <tr>
                          <td align="left" ><s:text name="amnTawarRosak" size="20" onkeyup="validateNumber(this,this.value);"/></td>
                      </tr>
                      <br>
                      <c:if test="${actionBean.kodUrusan eq 'SEK4'}">
                       <tr>
                          <td align="left" width="30%" rowspan="1"><b>Amaun yang ditawarkan kerana kerosakan di bawah seksyen 6 Akta tersebut adalah sebanyak RM :</b></td>
                      </tr>
                      <tr>
                          <td align="left" ><s:text name="amnTawarRosak" size="20"onkeyup="validateNumber(this,this.value);"/></td>
                      </tr>
                      <tr>
                          <td align="left" width="30%" rowspan="1"><b>Amaun pampasan yang diawarkan di bawah seksyen 14 adalah sebanyak RM :</b></td>
                      </tr>
                      <tr>
                          <td align="left" ><s:text name="amnTawarPampasan"  disabled="true"  size="20" onkeyup="validateNumber(this,this.value);"/></td>
                      </tr>
                      </c:if>
                       <c:if test="${actionBean.kodUrusan ne 'SEK4'}">
                       <tr>
                          <td align="left" width="30%" rowspan="1"><b>Amaun yang ditawarkan kerana kerosakan di bawah seksyen 6 Akta tersebut adalah sebanyak RM :</b></td>
                      </tr>
                      <tr>
                          <td align="left" ><s:text name="amnTawarRosak" size="20" disabled="true" onkeyup="validateNumber(this,this.value);"/></td>
                      </tr>
                      <tr>
                          <td align="left" width="30%" rowspan="1"><b>Amaun pampasan yang diawarkan di bawah seksyen 14 adalah sebanyak RM :</b></td>
                      </tr>
                      <tr>
                          <td align="left" ><s:text name="amnTawarPampasan" size="20" onkeyup="validateNumber(this,this.value);"/></td>
                      </tr>
                      </c:if>
                      <tr>
                          <td align="left" width="30%" rowspan="1"><b>Amaun pampasan yang diawarkan di bawah seksyen 14 adalah sebanyak RM :</b></td>
                      </tr>
                      <tr>
                          <td align="left" ><s:text name="amnTawarPampasan" size="20" onkeyup="validateNumber(this,this.value);"/></td>
                      </tr>
                      <br>
                      <tr>
                          <td align="left" width="30%" rowspan="1"><b>Berikut adalah alasan-alasan atas mana mana amaun pampasan itu telah ditentukan :</b></td>
                      </tr>
                      <tr>
                          <td align="left" width="70%"><s:textarea name="alasanAmnPampasan" rows="5" cols="150"/></td>
                      </tr>
                      <br>
                      <tr>
                          <td align="left" width="30%" rowspan="1"><b>Saya lampirkan bersama sama ini dokumen-dokumen yang berikut :</b></td>
                      </tr>
                      <tr>
                          <td align="left" width="70%"><s:textarea name="lampiran" rows="5" cols="150"/></td>
                      </tr>
                  </table>
                      <br>
                      <br>
              </div>
          </fieldset>
           <br>


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



