<%--
    Document   : AkuanTerimaBayaran
    Created on : Sep 28, 2010, 6:31:48 PM
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

    function popupList(idPihak,idHakmilik){
        var url = '${pageContext.request.contextPath}/pengambilan/keputusanPerundingan?showAkuanBayaranList&idPihak='+idPihak+'&idHakmilik='+idHakmilik;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
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
<s:form beanclass="etanah.view.stripes.pengambilan.KeputusanRundinganBayaranPampasanActionBean">
<div  id="hakmilik_details">
    <div align="center">
        <table style="width:80%" bgcolor="purple">
            <tr><td width="100%" height="20" ><div style="background-color: purple;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;"><font color="white">KEPUTUSAN PERUNDINGAN</font></font>
            </div></td></tr>
        </table>
    </div><br /><br />

<s:messages/>
<s:errors/>

<fieldset class="aras1"><br/>
    <div align="center">
    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                   requestURI="/pengambilan/keputusanPerundingan" id="line">
        <display:column title="No" sortable="true">${line_rowNum}</display:column>
        <display:column property="hakmilik.noHakmilik" title="No Hakmilik"/>
        <display:column property="hakmilik.noLot" title="No Lot" />
        <display:column property="hakmilik.daerah.nama" title="Daerah" class="Daerah"/>
        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
        <display:column property="hakmilik.bandarPekanMukim.nama" title="Nilaian Tanah" />
        <display:column title="Tuan Tanah" >
            <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                <s:link beanclass="etanah.view.stripes.pengambilan.KeputusanRundinganBayaranPampasanActionBean"
                        event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                    <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>
                    <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>
                    ${senarai.pihak.nama}
                </s:link>
                    <br/>
            </c:forEach>
        </display:column>
         <display:column title="Syer">
            <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                
                <c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/><br />
                <%--&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
            </c:forEach>
        </display:column>
        <display:column title="Kemaskini">
            <c:forEach items="" var="senarai">
            <div align="center">
                <br/>
                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>
                     <%--onclick="editTuntutanKos('${senarai.idKos}','${senarai.pihak.idPermohonanPihak}');"/>--%>
            </div>
            </c:forEach>
       </display:column>
    </display:table>
    </div><br />
</fieldset><br /><br />
      <c:if test="${showDetails}">
      <div align="left">
      <fieldset class="aras1">
            <div class="content" align="center">
                    <table class="tablecloth">
                        <tr>
                            <th width="20%">Jenis Nilaian</th><th width="60%">Keterangan</th><th width="20%">Nilaian (RM)</th>
                        </tr>
                        <tr>
                            <td>
                                <b>1. Nilaian Tanah</b>
                            </td>
                            <td>
                                <s:textarea name="keteranganNilaiTanah" id="keteranganNilaiTanah"  rows="3" cols="90"/>
                            </td>
                            <td>
                                <s:text name="nilaiTanah" id="nilaiTanah"  formatPattern="#,##0.00"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <b>2. Nilaian Bangunan</b>
                            </td>
                            <td>
                                <s:textarea name="keteranganNilaiBangunan" id="keteranganNilaiBangunan"  rows="3" cols="90"/>
                            </td>
                            <td>
                                <s:text name="nilaiBangunan" id="nilaiBangunan"  formatPattern="#,##0.00"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <b>3. Nilaian Pokok</b>
                            </td>
                            <td>
                                <s:textarea name="keteranganNilaiPokok" id="keteranganNilaiPokok"  rows="3" cols="90"/>
                            </td>
                            <td>
                                <s:text name="nilaiPokok" id="nilaiPokok"  formatPattern="#,##0.00"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <b>4. Nilaian Infrastruktur</b>
                            </td>
                            <td>
                                <s:textarea name="keteranganNilaiInfra" id="keteranganNilaiInfra"  rows="3" cols="90"/>
                            </td>
                            <td>
                                <s:text name="nilaiInfra" id="nilaiInfra"  formatPattern="#,##0.00"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <b>5. Nilaian Lain-Lain</b>
                            </td>
                            <td>
                                <s:textarea name="keteranganNilaiLain" id="keteranganNilaiLain"  rows="3" cols="90"/>
                            </td>
                            <td>
                                <s:text name="nilaiLain" id="nilaiLain"  formatPattern="#,##0.00"/>
                            </td>
                        </tr>
                        <tr>
                            <td ></td>
                            <td>
                                <div align="right">
                                    <b>Jumlah Keseluruhan (RM) : </b>                                    
                                </div>
                            </td>
                            <td>
                                <s:text name="a" value="${(actionBean.nilaiTanah)+(actionBean.nilaiBangunan)+(actionBean.nilaiPokok)+(actionBean.nilaiInfra)+(actionBean.nilaiLain)}" formatPattern="#,##0.00" readonly="true"/>
                            </td>
                        </tr>
                    </table>
                </div>
              <br/><br/>
      
      
              <div align="center">
                  <s:hidden name="idPihak" value="${actionBean.idPihak}"/>
                  <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
                  <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
              </div>

      <br />
       
       </fieldset>
</div>
              </c:if>
</div>
</s:form>

