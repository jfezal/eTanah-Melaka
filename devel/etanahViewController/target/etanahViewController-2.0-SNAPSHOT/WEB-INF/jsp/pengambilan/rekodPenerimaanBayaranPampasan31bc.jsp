<%--
    Document   : rekodPenerimaanBayaranPampasan31bc
    Created on : Aug 4, 2010, 10:02:48 AM
    Author     : massita
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
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
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript">
function refreshPagePenerimaanBorang(){
var url = '${pageContext.request.contextPath}/pengambilan/BayaranPampasan?refreshpage';
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
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.RekodPenerimaanBayaranPampasanActionBean">
<div  id="hakmilik_details">
    <div align="center">
        <table style="width:99.2%" bgcolor="purple">
            <tr>
                <td width="100%" height="20" >
                    <div style="background-color: purple;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;" color="white">PENGAMBILAN: REKOD PENERIMAAN BAYARAN PAMPASAN (3(b)(c))</font>
                    </div>
                </td>
            </tr>
        </table>
    </div>
    <br/><br/>

    <s:messages/>
    <s:errors/>

    <%--<div class="subtitle">--%>
        <fieldset class="aras1">
            <%--<c:if test="${actionBean.hakmilik ne null}">--%>
            <legend align="left">
                <b>Maklumat Hakmilik Permohonan</b>
            </legend>
            <div align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/BayaranPampasan" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column title="ID Hakmilik">
                        <s:link beanclass="etanah.view.stripes.pengambilan.RekodPenerimaanBayaranPampasanActionBean"
                            event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                        <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}
                        </s:link>
                    </display:column>
                    <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                </display:table>
            <%--</c:if>--%>
            </div>
        </fieldset>
            <br/><br />

    <%--<div  align="center">--%>
        <c:if test="${actionBean.hakmilik ne null}">
            <%--<c:if test="${showPenyediaanBayaranPampasan}">--%>
            <fieldset class="aras1">
                <legend>Penyediaan bayaran pampasan</legend><br />

                    <display:table class="tablecloth" name="${actionBean.hakmilik}" cellpadding="0" cellspacing="0" id="tbl1">
                        <display:column title="No" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                        <display:column property="idHakmilik" title="ID Hakmilik" />
                        <display:column property="noLot" title="Nombor Lot/PT" />
                        <display:column title="Daerah" property="daerah.nama" class="daerah" />
                        <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                        <display:column title="Orang Berkepentingan" style="vertical-align:baseline">
                            <c:forEach items="${actionBean.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                <%--<c:if test="${senarai.jenis.kod eq 'PM'}">--%>
                                    <s:link beanclass="etanah.view.stripes.pengambilan.RekodPenerimaanBayaranPampasanActionBean"
                                            event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                        <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>${senarai.pihak.nama}
                                    </s:link>
                                    <br/>
                                    No KP ${senarai.pihak.noPengenalan}
                                <%--</c:if>--%>
                                    <br/>
                            </c:forEach>
                        </display:column>
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

        <br /><br />
        </fieldset>
        </c:if>
        <%--<c:if test="${actionBean.permohonanPihak ne null}">--%>
        <c:if test="${showDetails}">
            <table width="100%">
                <tr>
                    <td width="30%"><label>Tajuk Permohonan :</label></td>
                    <td>${actionBean.permohonanSblm.sebab }</td>
                </tr>                
                <tr>
                    <td><label>Jumlah pampasan (RM) :</label></td>
                    <td><%-- <tr><td class="number" align="right"> <fmt:formatNumber value="${actionBean.jumlahCb}" pattern="0.00"/></td></tr>&nbsp;--%>
                <%--<s:text name="a" size="50"/>--%>${actionBean.jumCaraBayar1}<br /></td>
                </tr>
                <tr>
                  <td><label >Jumlah pampasan yang diterima (RM) :</label></td>
                  <td><s:text name="jumTerimaPampasan" size="50" onkeyup="validateNumber(this,this.value);"/><br /></td>
              </tr>
              <tr>
                  <td><label >Jumlah pampasan yang diterima (RM) :</label></td>
                  <td><s:text name="jumTerimaPampasan" size="50" onkeyup="validateNumber(this,this.value);"/><br /></td>
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
                  <td><s:text name="tarikhDok" class="datepicker" id="datepicker"/></td>
              </tr>
              <tr>
                  <td><label >Bank :</label></td>
                  <td><s:select name="kodBank.kod" style="width:300px;" id="kodBank" >
                            <s:option value="">-- Sila Pilih --</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodBank}" value="kod" label="nama"/>
                        </s:select>
                  </td>
              </tr>
            </table>
              <br/><br/>

              <div align="center">
                  <s:hidden name="idPihak" value="${actionBean.idPihak}"/>
                  <%--<s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>--%>
                  <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
              </div>
    <%--</div>--%>
      <%--</div>--%>
      <br />

     <%-- <div align="center">
          <table style="width:99.2%" bgcolor="purple">
              <tr><td width="100%" height="20" >
                      <div style="background-color: purple;" align="center"><font style="font-family: Tahoma; font-size: 16px; font-weight: bold;"></font></div><s:button name="janaSurat" value="Jana Surat" class="longbtn"/>
                  </td>
              </tr>
          </table>
      </div>--%>
          </c:if>

      <%--</div>--%>
      </div>
    </s:form>
