<%--
    Document   : rekodPenerimaanBayaranPampasan31a
    Created on : Aug 4, 2010, 10:02:48 AM
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
        var str = elmnt.value;
        if(str > ${actionBean.jumCaraBayar1}){
            elmnt.value = str.substring(0,str.length-1);
            elmnt.focus();
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
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript">

<%--$(document).ready( function(){
$(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
});--%>

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

function validation() {
    if($("#jumTerimaPampasan").val() == ""){
        alert('Sila pilih " Jumlah pampasan yang diterima (RM) : " terlebih dahulu.');
        $("#jumTerimaPampasan").focus();
        return false;
    }
    if ($("input[name='kodCaraBayaran.kod']:checked").val() != 'CT' &&
        $("input[name='kodCaraBayaran.kod']:checked").val() != 'CL' &&
        $("input[name='kodCaraBayaran.kod']:checked").val() != 'CB' &&
        $("input[name='kodCaraBayaran.kod']:checked").val() != 'DB' &&
        $("input[name='kodCaraBayaran.kod']:checked").val() != 'T' &&
        $("input[name='kodCaraBayaran.kod']:checked").val() != 'XT') {
        alert('Sila pilih " Cara Pembayaran : " terlebih dahulu.');
        $("#kodCaraBayaran1").focus();
        return false;
    }
   
    if($("#datepicker").val() == ""){
        alert('Sila pilih " Tarikh : " terlebih dahulu.');
        $("#datepicker").focus();
        return false;
    }
    if($("#kodBank").val() == ""){
        alert('Sila pilih " Bank : " terlebih dahulu.');
        $("#kodBank").focus();
        return false;
    }
    return true;

}

function popupList(idPihak,idHakmilik){
        var url = '${pageContext.request.contextPath}/pengambilan/bayaranPampasan31a?showAmbilPampasanList&idPihak='+idPihak+'&idHakmilik='+idHakmilik;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pengambilan.RekodPenerimaanBayaranPampasan31aActionBean" id="form">
<div  id="hakmilik_details">
    <div align="center">
        <table style="width:99.2%" bgcolor="purple">
            <tr><td width="100%" height="20" ><div style="background-color: purple;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;"><font color="white">PENGAMBILAN: REKOD PENERIMAAN BAYARAN PAMPASAN (3(1)(a))</font></font>
            </div></td></tr>
        </table>
    </div><br /><br />

<s:messages/>
<s:errors/>
      <%--  <s:hidden name ="hakmilik.idHakmilik"/>--%>

<fieldset class="aras1"><br/>
    <legend align="left"><b>Maklumat Hakmilik Permohonan</b></legend>
    <div align="center">
    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                   requestURI="/pengambilan/bayaranPampasan31a" id="line">
        <display:column title="No" sortable="true">${line_rowNum}</display:column>
        <display:column title="ID Hakmilik">
            <s:link beanclass="etanah.view.stripes.pengambilan.RekodPenerimaanBayaranPampasan31aActionBean"
                    event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}</s:link>
        </display:column>
        <display:column property="hakmilik.noLot" title="No Lot/No PT" />
        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
    </display:table>
    </div>
</fieldset>
      <br /><br />

      <c:if test="${actionBean.hakmilik ne null}">
          <fieldset class="aras1">
              <legend>Penyediaan bayaran pampasan</legend><br />
              <div align="center">
              <display:table class="tablecloth" name="${actionBean.hakmilik}" cellpadding="0" cellspacing="0" id="tbl1">
                    <display:column title="No" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                    <display:column property="idHakmilik" title="ID Hakmilik" />
                    <display:column property="noLot" title="Nombor Lot/PT" />
                    <display:column title="Daerah" property="daerah.nama" class="daerah" />
                    <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column title="Orang Berkepentingan" style="vertical-align:baseline">
                        <display:table name="${tbl1.senaraiPihakBerkepentingan}" id="line5" class="tablecloth">
                            <display:column title="Pihak">
                                <s:link beanclass="etanah.view.stripes.pengambilan.RekodPenerimaanBayaranPampasan31aActionBean"
                                        event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                    <s:param name="idPihak" value="${line5.pihak.idPihak}"/>${line5.pihak.nama}
                                </s:link>
                            </display:column>
                            <display:column title="No KP">
                                ${line5.pihak.noPengenalan}
                            </display:column>
                            <display:column title="Bil Bayaran">
                                <c:set var="count" value="0"/>
                                <c:forEach items="${actionBean.ambilPampasanList}" var="list">
                                    <c:if test="${line5.pihak.idPihak == list.perbicaraanKehadiran.pihak.pihak.idPihak}">
                                        <c:set value="${count + 1}" var="count"/>
                                    </c:if>
                                </c:forEach>
                                <a href="javascript:popupList('${line5.pihak.idPihak}','${tbl1.idHakmilik}');">
                                    <c:out value="${count}" />
                                </a>
                            </display:column>
                        </display:table>
                        <%--<c:forEach items="${tbl1.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                            <c:if test="${senarai.jenis.kod eq 'PM'}">
                                <s:link beanclass="etanah.view.stripes.pengambilan.RekodPenerimaanBayaranPampasan31aActionBean"
                                        event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                    <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>${senarai.pihak.nama}
                                </s:link>
                                <br/>
                                No KP ${senarai.pihak.noPengenalan}
                            </c:if>
                            <br/>
                        </c:forEach>--%>
                    </display:column>
                    <%--<display:column title="Bil Bayaran">
                        <c:forEach items="${tbl1.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                            <c:set var="count" value="0"/>
                            <c:forEach items="${actionBean.ambilPampasanList}" var="list">
                                <c:if test="${senarai.pihak.idPihak == list.permohonanPihak.pihak.idPihak}">
                                    <c:set value="${count + 1}" var="count"/>
                                </c:if>
                            </c:forEach>
                            <a href="javascript:popupList('${senarai.pihak.idPihak}','${tbl1.hakmilik.idHakmilik}');">
                                <c:out value="${count}" />
                            </a><br/>
                        </c:forEach>
                    </display:column>--%>
                    <display:column title="Luas Diambil" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp;${actionBean.hakmilik.kodUnitLuas.nama}</display:column>
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
      <%--<c:if test="${actionBean.permohonanPihak ne null}">--%>
      <c:if test="${showDetails}">
          <table width="100%">
              <tr>
                  <td width="30%"><label  >Tajuk Permohonan :</label></td>
                  <td>${actionBean.permohonan.sebab }<br /></td>
              </tr>
              <tr>
                  <td><label >Jumlah pampasan (RM) :</label></td>
                  <td><%-- <tr><td class="number" align="right"> <fmt:formatNumber value="${actionBean.jumlahCb}" pattern="0.00"/></td></tr>&nbsp;--%>
                ${actionBean.jumCaraBayar1}<br /></td>
              </tr>
              <tr>
                  <td><label >Jumlah pampasan yang diterima (RM) :</label></td>
                  <td><s:text name="jumTerimaPampasan" size="50" id="jumTerimaPampasan" onkeyup="validateNumber(this,this.value);"/><br /></td>
              </tr>
              <tr>
                  <td><label >Cara Pembayaran : </label></td>
                  <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran1" value="CT" /> Cek Tempatan <br /></td>
              </tr>
              <tr>
                  <td><label >&nbsp;&nbsp;</label></td>
                  <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran2" value="CL"/> Cek Luar <br /></td>
              </tr>
              <tr>
                  <td><label >&nbsp;&nbsp;</label></td>
                  <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran3" value="CB"/> Cek Bank Negara <br /></td>
              </tr>
              <tr>
                  <td><label >&nbsp;&nbsp;</label></td>
                  <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran4" value="DB"/> Bank Draf <br /></td>
              </tr>
              <tr>
                  <td><label >&nbsp;&nbsp;</label></td>
                  <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran5" value="T"/> Tunai <br /></td>
              </tr>
              <tr>
                  <td><label >&nbsp;&nbsp;</label></td>
                  <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran6" value="XT"/> Tiada Pembayaran</td>
              </tr>
              <tr>
                  <td><label >No. :</label></td>
                  <td><s:text name="noDok" size="50"/></td>
              </tr>
              <tr>
                  <td><label >Tarikh :</label></td>
                  <td><s:text name="tarikhDok" class="datepicker" id="datepicker" formatPattern="dd/MM/yyyy"/></td>
              </tr>
              <tr>
                  <td><label >Bank :</label></td>
                  <td><s:select name="kodBank.kod" style="width:300px;" id="kodBank" >
                            <s:option value="">-- Sila Pilih --</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodBank}" value="kod" label="nama"/>
                        </s:select>
                  </td>
              </tr>
              <tr>
                  <td><label >No. Voucher:</label></td>
                  <td><s:text name="noVou" size="50"/></td>
              </tr>
              <tr>
                  <td><label >Tarikh Voucher:</label></td>
                  <td><s:text name="tarikhDok" class="datepicker" id="datepicker" formatPattern="dd/MM/yyyy"/></td>
              </tr>
            </table>
              <br/><br/>

              <div align="center">
                  <s:hidden name="idPihak" value="${actionBean.idPihak}"/>
                  <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
              </div>
      <br />
       </c:if>

</div>
</s:form>