<%-- 
    Document   : maklumat_pampasan_tidakBerdaftar
    Created on : 08-Dec-2011, 15:04:31
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

function validationTanah() {
    var tanahItem = $("#tanahItem").val();
    var tanahAmaun = $("#tanahAmaun").val();

    if(tanahItem == ""){
        alert('Sila pilih " Item : " terlebih dahulu.');
        $("#tanahItem").focus();
        return false;
    }

    if(tanahAmaun == ""){
        alert('Sila pilih " RM : " terlebih dahulu.');
        $("#tanahAmaun").focus();
        return false;
    }
    return true;
}

function validationBngn() {
    var bngnItem = $("#bngnItem").val();
    var bngnAmaun = $("#bngnAmaun").val();

    if(bngnItem == ""){
        alert('Sila pilih " Item : " terlebih dahulu.');
        $("#bngnItem").focus();
        return false;
    }

    if(bngnAmaun == ""){
        alert('Sila pilih " RM : " terlebih dahulu.');
        $("#bngnAmaun").focus();
        return false;
    }
    return true;
}

function validationLain() {
    var lainItem = $("#lainItem").val();
    var lainAmaun = $("#lainAmaun").val();

    if(lainItem == ""){
        alert('Sila pilih " Item : " terlebih dahulu.');
        $("#lainItem").focus();
        return false;
    }

    if(lainAmaun == ""){
        alert('Sila pilih " RM : " terlebih dahulu.');
        $("#lainAmaun").focus();
        return false;
    }
    return true;
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
    function removeNilai(idPenilaian)
{
    if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pengambilan/maklumatPampasan?deleteNilai&idPenilaian='
+idPenilaian;
            $.get(url,
            function(data){
            $('#page_div').html(data);
            <%--self.opener.refreshPageTanahRizab();--%>
            },'html');
        }
}
</script>
<s:form beanclass="etanah.view.stripes.pengambilan.MaklumatPampasanTidakberdaftarActionBean">
<div  id="hakmilik_details">
<s:messages/>
<s:errors/>

<fieldset class="aras1"><br/>
    <legend align="left"><b>Maklumat Hakmilik Permohonan</b></legend>
    <div align="center">
    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                   requestURI="/pengambilan/maklumatPampasan" id="line">
        <display:column title="No" sortable="true">${line_rowNum}</display:column>
        <display:column title="ID Hakmilik">
            <s:link beanclass="etanah.view.stripes.pengambilan.MaklumatPampasanTidakberdaftarActionBean"
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
              <legend>Pihak Berkepentingan Tidak Berdaftar</legend><br />
              <div align="center">
                  Sila masukkan nilaian untuk setiap Pihak Berkepentingan Tidak Berdaftar.
                  
                    <br>
              <display:table class="tablecloth" name="${actionBean.hakmilik}" cellpadding="0" cellspacing="0" id="tbl1">
                    <display:column title="No" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                    <display:column property="idHakmilik" title="ID Hakmilik" />
                    <display:column property="noLot" title="Nombor Lot/PT" />
                    <display:column title="Daerah" property="daerah.nama" class="daerah" />
                    <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column title="Orang Berkepentingan" style="vertical-align:baseline">
                        <c:forEach items="${actionBean.senaraiHadir}" var="senarai">
                                <s:link beanclass="etanah.view.stripes.pengambilan.MaklumatPampasanTidakberdaftarActionBean"
                                        event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                    <s:param name="idKehadiran" value="${senarai.idKehadiran}"/>
                                    <c:if test="${senarai.pihak ne null}">${senarai.pihak.pihak.nama}</c:if>
                                    <c:if test="${senarai.permohonanPihakTidakBerkepentingan ne null}">${senarai.nama}</c:if>
                                    <c:if test="${senarai.nama ne null}">${senarai.nama}</c:if>
                                    ${senarai.permohonanPihakTidakBerkepentingan.nama}
                                </s:link>
                                <br/>
                                No KP 
                                 <c:if test="${senarai.pihak ne null}">${senarai.pihak.pihak.noPengenalan}</c:if>
                                 <c:if test="${senarai.permohonanPihakTidakBerkepentingan ne null}"></c:if>
                                 <c:if test="${senarai.noPengenalan ne null}">${senarai.noPengenalan}</c:if>
                            <%--</c:if>--%>
                            <br/>
                        </c:forEach>
                    </display:column>
                    <display:column title="Luas Diambil" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp;${actionBean.hakmilik.kodUnitLuas.nama}</display:column>
              </display:table>
              </div>
                <br /><br />
          </fieldset><br />
      </c:if>
      <%--<c:if test="${actionBean.permohonanPihak ne null}">--%>
      <c:if test="${showDetails}">
          <table >
              <tr>
                  <td width="20%"><label  >Perbicaraan Pengambilan No :</label></td>
                  <td>${actionBean.hakmilikPerbicaraan.idPerbicaraan}<br /></td>
              </tr>
              <tr>
                  <td width="20%"><label  >Tarikh Perbicaraan :</label></td>
                  <td><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.hakmilikPerbicaraan.tarikhBicara }"/><br /></td>
              </tr>
          </table>
      <br/>
      <br/><br/>

      <table >
          <tr><td align="right"><font color="#003194" style="Tahoma" size="2px"><b>Nilaian Bangunan :</b></font></td></tr>
          <tr>
               <s:hidden name="idKehadiran" value ="${actionBean.idKehadiran}" />
               <s:hidden name="idHakmilik" value ="${actionBean.idHakmilik}" />
              <td><label for="bngnItem" >Item :</label></td>
              <td width="19%"><s:text name="bngnItem" size="20" id="bngnItem"/><br/></td>
              <td ><font color="#003194" style="Tahoma" size="2px"><b>RM :</b></font></td>
              <td width="18%"><s:text name="bngnAmaun" size="20" id="bngnAmaun" onkeyup="validateNumber(this,this.value);"/></td>
              <td><s:button name="simpanBngn" id="save" value="Simpan" class="btn" onclick="if(validationBngn())doSubmit(this.form, this.name, 'page_div')"/></td>

          </tr>
      </table>

       <br/><br/>

      <div align="center">
          <display:table class="tablecloth" name="${actionBean.penilaianBngnList}" cellpadding="0" cellspacing="0" id="tbl1">
              <display:column title="No" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
              <display:column property="item" title="Item" />
              <display:column property="amaun" title="Amaun (RM)" />
              <display:column title="Hapus">
                    <div align="center">
                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeNilai('${tbl1.idPenilaian}');" />
                    </div>
                    </display:column>
          </display:table>
      </div>
          <br /><br/>

      <table >
          <tr><td align="right"><font color="#003194" style="Tahoma" size="2px"><b>Nilaian Lain-Lain :</b></font></td></tr>
          <tr>
               <s:hidden name="idKehadiran" value ="${actionBean.idKehadiran}" />
               <s:hidden name="idHakmilik" value ="${actionBean.idHakmilik}" />
              <td><label for="lainItem">Item :</label></td>
              <td width="19%"><s:text name="lainItem" size="20" id="lainItem"/><br/></td>
              <td ><font color="#003194" style="Tahoma" size="2px"><b>RM :</b></font></td>
              <td width="18%"><s:text name="lainAmaun" size="20" id="lainAmaun" onkeyup="validateNumber(this,this.value);"/></td>
              <td><s:button name="simpanLain" id="save" value="Simpan" class="btn" onclick="if(validationLain())doSubmit(this.form, this.name, 'page_div')"/></td>
          </tr>
      </table>

       <br/><br/>

      <div align="center">
          <display:table class="tablecloth" name="${actionBean.penilaianLainList}" cellpadding="0" cellspacing="0" id="tbl1">
              <display:column title="No" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
              <display:column property="item" title="Item" />
              <display:column property="amaun" title="Amaun (RM)" />
             <display:column title="Hapus">
                    <div align="center">
                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeNilai('${tbl1.idPenilaian}');" />
                    </div>
                    </display:column>
          </display:table>
      </div>

          <br/><br/>

      <div align="center">
          <display:table class="tablecloth" name="${actionBean.senaraiHadirIndividu}" cellpadding="0" cellspacing="0" id="tbl1">
              <display:column title="No" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
              <display:column title="Pihak Berkepentingan Tidak Berdaftar">${tbl1.permohonanPihakTidakBerkepentingan.nama}<c:if test="${(tbl1.idKehadiran == actionBean.idKehadiran) && (tbl1.permohonanPihakTidakBerkepentingan ne null)}">${tbl1.nama}</c:if>
              <c:if test="${(tbl1.idKehadiran == actionBean.idKehadiran) && (tbl1.pihak.idPermohonanPihak ne null)}">${tbl1.pihak.pihak.nama}</c:if></display:column>
              <display:column  title="Item Nilaian (RM)" >
                  <c:if test="${tbl1.idKehadiran == actionBean.idKehadiran}">
                  <%--<c:set value="${tbl1.pihak.pihak.syerPembilang}" var="a"/>
                  <c:set value="${tbl1.pihak.pihak.syerPenyebut}" var="b"/>--%>
                  <%--<c:set value="${actionBean.hakmilikPermohonan.luasTerlibat}" var="c"/>
                  <c:set value="${actionBean.hakmilikPerbicaraan.keputusanNilai}" var="d"/>
                  <c:set value="${d/c}" var="e"/>
                  <c:set value="${(a/b)*c}" var="f"/>
                  <c:set value="${e*f}" var="g"/>
                  <b>Tanah : RM <fmt:formatNumber pattern="#,##0.00" value="${g}"/></b><br/>--%>
                  <b>Bangunan : RM ${actionBean.itemNilaianBngn}</b><br/>
                  <b>Lain - lain : RM ${actionBean.itemNilaianLain}</b>
                  </c:if>
              </display:column><%--
             --%> <display:column  title="Jumlah Keseluruhan (RM)" >
                  <c:set value="${actionBean.itemNilaianBngn}" var="a"/>
                  <c:set value="${actionBean.itemNilaianLain}" var="b"/>
                  <%--<c:set value="${a+b}" var="g"/>
                  <c:set value="${actionBean.jumlah}" var="g"/>
                  <b>RM ${actionBean.jumlah}</b>--%>
                  <b>RM <fmt:formatNumber pattern="#,##0.00" value="${a+b}"/></b>
              </display:column>
          </display:table>
      </div>
       </c:if>
      <%--</c:if>--%>

</div>
</s:form>