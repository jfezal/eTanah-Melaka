<%--
    Document   : StatusAward
    Created on : 20-Jul-2011, 18:56:04
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
<s:form beanclass="etanah.view.stripes.pengambilan.StatusAwardActionBean">
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
            <s:link beanclass="etanah.view.stripes.pengambilan.StatusAwardActionBean"
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
              <legend>Tuan Tanah</legend><br />
              <div align="center">
                  Sila masukkan nilaian untuk setiap Tuan Tanah.

                      <p align="center">
                        <display:table class="tablecloth" name="${actionBean.hakmilik.senaraiPihakBerkepentingan}" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/nota_siasatan" id="line" pagesize="5">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="pihak.nama" title="Pemilik Berdaftar" />
                    <display:column property="pihak.noPengenalan" title="No KP" />
                    <display:column title="Bahagian" >
                        ${line.syerPembilang}/${line.syerPenyebut}
                    </display:column>
                    <display:column title="Status Penerimaan Award">
                         <s:radio name="status[${line_rowNum-1}]" value="Terima"/>Terima
                         <s:radio name="status[${line_rowNum-1}]" value="Mahkamah"/>Mahkamah
                         <s:radio name="status[${line_rowNum-1}]" value="Amanah"/>Amanah Raya
                    </display:column>
                    <display:column title="Catatan" >
                        <%--<s:textarea size="5" name="catatan[${line_rowNum - 1}]" id="catatan${line_rowNum - 1}"/>&nbsp;--%>
                        <s:textarea  rows="5"  cols="30" name="catatan[${line_rowNum - 1}]" id="catatan${line_rowNum - 1}"/>
                        <s:hidden name="count" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="count"/>
                    </display:column>
                    <display:column title="Pampasan (RM)">
                       <c:set value="${line.syerPembilang}" var="a"/>
                        <c:set value="${line.syerPenyebut}" var="b"/>
                        <c:set value="${actionBean.hakmilikPermohonan.luasTerlibat}" var="c"/>
                        <c:set value="${(actionBean.hakmilikPerbicaraan.keputusanNilai)/(actionBean.hakmilikPermohonan.luasTerlibat)}" var="d"/>
                        <c:set value="${a/b*c}" var="e"/>
                        <c:set value="${e*d}" var="f"/>
                         <c:forEach items="${actionBean.senaraiPerbicaraanKehadiran}" var="list">
                                <c:if test="${line.pihak.idPihak == list.pihak.pihak.idPihak}">
                                    <c:set var="B" value="0"/>
                                    <c:forEach items="${list.senaraiPenilaian}" var="list1">
                                            <c:set value="${B + list1.amaun}" var="B"/>
                                    </c:forEach>
                                    <c:set value="${B}" var="g"/>
                                </c:if>
                        </c:forEach>
                        <fmt:formatNumber pattern="#,##0.00" value="${f+g}"/>
                    </display:column>
                </display:table>

            </p>
                        <s:button class="btn" value="Simpan" name="simpanAmanah" id="new" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
                        <%--<s:button class="btn" value="Hapus" name="remove" id="remove" onclick="deleteKehadiran('line1');"/>&nbsp;--%>
              </div>
                <br /><br />
          </fieldset><br />
      </c:if>

</div>
</s:form>