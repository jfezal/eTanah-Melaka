<%-- 
    Document   : Charting_Aduan
    Created on : 13-Apr-2011, 14:29:37
    Author     : nordiyana
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">
function removeTanahRizab(idTanahRizabPermohonan)
{
    if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?deleteTanahRizab&idTanahRizabPermohonan='
+idTanahRizabPermohonan;
            $.get(url,
            function(data){
            $('#page_div').html(data);
            self.opener.refreshPageTanahRizab();
            },'html');
        }
}
     function ReplaceAll(Source,stringToFind,stringToReplace){
    var temp = Source;
    var index = temp.indexOf(stringToFind);
        while(index != -1){
            temp = temp.replace(stringToFind,stringToReplace);
            index = temp.indexOf(stringToFind);

        }
       <%--alert(temp);--%>
        return temp;
    }

    function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {
        <%--var stageId = "g_charting_semak";--%>
         // replace " " with "_"

         strNama = ReplaceAll(strNama," ","_");
         strJawatan = ReplaceAll(strJawatan," ","_");
         strStageID = ReplaceAll(strStageID," ","_");
         alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strStageID);
        <%--
        alert ("stageid:" + stageId);--%>
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + strStageID);
    }


function removeTanahMilik(id)
{
if(confirm('Adakah anda pasti?')) {
var url = '${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?deleteTanahMilik&id='
+id;
$.get(url,
function(data){
$('#page_div').html(data);
},'html');}
}

function removePermohonanTerdahulu(idMohonManual)
{
if(confirm('Adakah anda pasti?')) {
var url = '${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?deletePermohonanTerdahulu&idMohonManual='
+idMohonManual;
$.get(url,
function(data){
$('#page_div').html(data);
},'html');}
}


function tambahTanahRizab(){
window.open("${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?tanahRizabPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

function tambahTanahMilik(){
window.open("${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?tanahMilikPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

function tambahPermohonanTerdahulu(){
window.open("${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?permohonanTerdahuluPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

function refreshPageTanahRizab(){
var url = '${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?refreshpage';
$.get(url,
function(data){
$('#page_div').html(data);
},'html');
}

function popupTanahRizab(h){
var url = '${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?showEditTanahRizab&idTanahRizabPermohonan='+h;
window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
}

function popupTanahAA(h){
var url = '${pageContext.request.contextPath}/pengambilan/maklumat_tambahan??showEdittanahAA&idTanahRizabPermohonan='+h;
window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
}
function popupPermohonanTerdahulu(h){
var url = '${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?showEditPermohonanTerdahulu&idMohonManual='+h;
window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
}
function showUlasan() {
     $('#noWartaPihakBerkuasa').show();
     $('#namaWartaPihakBerkuasa').show();
     $('#kawkuasa').show();
    $('#namaPBT').show();
    $('#namaPihakBerkuasa').show();

}
function hideUlasan() {

    $('#noWartaPihakBerkuasa').hide();
    $('#namaWartaPihakBerkuasa').hide();
    $('#kawkuasa').hide();
    $('#namaPBT').hide();
    $('#namaPihakBerkuasa').hide();


}
function showJenisTanahBandar() {
     $('#noWartaBandar').show();
     $('#noWartaPekan').hide();
     $('#noWartaSeksyen').hide();
     $('#bandar').show();
     $('#pekan').hide();
     $('#seksyen').hide();
}
function showJenisTanahPekan() {
     $('#noWartaBandar').hide();
     $('#noWartaPekan').show();
     $('#pekan').show();
     $('#noWartaSeksyen').hide();
     $('#bandar').hide();
     $('#seksyen').hide();
}
function showJenisTanahSeksyen() {
     $('#noWartaBandar').hide();
     $('#noWartaPekan').hide();
     $('#noWartaSeksyen').show();
     $('#seksyen').show();
     $('#bandar').hide();
     $('#pekan').hide();
}
<%--function popup(i){

     var d = $('.x'+i).val();
            window.open("${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?showeditTanahRizab&idTanahRizabPermohonan="+d, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");


}--%>
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.ChartingAduanActionBean">

    <s:messages/>
    <c:if test="${formPP}">
    <div class="subtitle displaytag">
        <fieldset class="aras1" id="locate">
            <legend>
                Tanah Milik
            </legend>
            <p align="center"><label></label>
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" style="width:100%" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/maklumat_tambahan">
                    <display:column title="No">${line_rowNum}</display:column>
                    <display:column property="hakmilik.kodHakmilik.nama" title="Jenis Milik" />
                    <display:column property="hakmilik.daerah.nama" title="Daerah" />
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column title="Luas" >${line.hakmilik.luas}&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column title="Luas Diambil" >${line.luasTerlibat}&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column property="hakmilik.noLot" title="No. PT/Lot"/>
                    <display:column property="hakmilik.idHakmilik" title="No. H/M"/>
                    <display:column title="Diambil">
                         <s:radio name="diambilHakmilikPermohonan[${line_rowNum-1}]" value="1"/>Ya
                         <s:radio name="diambilHakmilikPermohonan[${line_rowNum-1}]" value="0"/>Tidak
                    </display:column>
                    <display:column title="Ulasan">
                        <s:text name="catatanHakmilikPermohonan[${line_rowNum - 1}]" />
                    </display:column>
                </display:table>
            <%--<s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahTanahMilik();"/>&nbsp;--%>
            <br>

        </fieldset>
    </div>
            </c:if>
     <c:if test="${viewPP}">
    <div class="subtitle displaytag">
        <fieldset class="aras1" id="locate">
            <legend>
                Tanah Milik
            </legend>
            <p align="center"><label></label>
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" style="width:100%" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/maklumat_tambahan">
                    <display:column title="No">${line_rowNum}</display:column>
                    <display:column property="hakmilik.kodHakmilik.nama" title="Jenis Milik" />
                    <display:column property="hakmilik.daerah.nama" title="Daerah" />
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column title="Luas" >${line.hakmilik.luas}&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column title="Luas Diambil" >${line.luasTerlibat}&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column property="hakmilik.noLot" title="No. PT/Lot"/>
                    <display:column property="hakmilik.idHakmilik" title="No. H/M"/>
                    <display:column title="Diambil">
                        <s:radio  disabled="true" name="diambilHakmilikPermohonan[${line_rowNum-1}]" value="1"/>Ya
                         <s:radio disabled="true" name="diambilHakmilikPermohonan[${line_rowNum-1}]" value="0"/>Tidak
                    </display:column>
                    <display:column title="Ulasan">
                        <s:text readonly="true" name="catatanHakmilikPermohonan[${line_rowNum - 1}]" />
                    </display:column>
                </display:table>
            <%--<s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahTanahMilik();"/>&nbsp;--%>
            <br>

        </fieldset>
    </div>
            </c:if>
  <br/>
  <br/>
       <c:if test="${actionBean.permohonan.kodUrusan.kod eq '831A' && actionBean.kodNegeri eq '05'}">
    <div class="subtitle displaytag">
     <fieldset class="aras1" id="locate">
            <legend>
                Tanah Kerajaan/Tanah Rizab
            </legend>
            <p align="center"><label></label>
                <display:table class="tablecloth" name="${actionBean.tanahRizabList}" pagesize="20" style="width:100%" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/maklumat_tambahan">
                    <display:column title="No">${line_rowNum}</display:column>
                    <display:column property="rizab.nama" title="Jenis Rizab" />
                    <display:column property="cawangan.name" title="Cawangan" />
                    <display:column property="daerah.nama" title="Daerah" />
                    <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column property="noLot" title="No. PT/Lot"/>
                    <display:column property="noWarta" title="No. Warta"/>
                    <display:column property="tarikhWarta" title="Tarikh Warta" format="{0,date,dd/MM/yyyy}"/>
                    <display:column title="Diambil">
                         <s:radio name="diambilTanahRizab[${line_rowNum-1}]" value="1"/>Ya
                         <s:radio name="diambilTanahRizab[${line_rowNum-1}]" value="0"/>Tidak
                    </display:column>
                    <display:column title="Ulasan">
                        <s:text name="catatanTanahRizab[${line_rowNum - 1}]" />
                    </display:column>
                    <display:column title="Kemaskini">
                    <div align="center">
                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popupTanahRizab('${line.idTanahRizabPermohonan}');"/>
                    </div>
                    </display:column>
                    <display:column title="Hapus">
                    <div align="center">
                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeTanahRizab('${line.idTanahRizabPermohonan}');" />
                    </div>
                    </display:column>
                </display:table>
            <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahTanahRizab();"/>&nbsp;
            <br>

        </fieldset>
    </div>
             <br/>
  <br/>
             <div class="subtitle displaytag">
     <fieldset class="aras1" id="locate">
                <legend>
                    Maklumat Tanah Approve Application (AA)
                </legend>
                <p align="center">
                <c:if test="${edit}">
                  <display:table class="tablecloth" name="${actionBean.senaraiTanahAA}" pagesize="20" style="width:100%" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/maklumat_tambahan">
                    <display:column title="No">${line_rowNum}</display:column>
                    <display:column property="noLot" title="No. AA" />
                    <display:column property="rizab.nama" title="Jenis Tanah" />
                    <display:column property="daerah.nama" title="Daerah" />
                    <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line.luasTerlibat}"/>&nbsp;${line.kodUnitLuas.nama}</display:column>
                    <display:column title="Luas Diambil"><fmt:formatNumber pattern="#,##0.00" value="${line.luasDiambil}"/>&nbsp;${line.kodUnitLuas.nama}</display:column>
                    <display:column title="Baki Luas">
                        <c:if test="${line.luasTerlibat ne null}">
                              <c:set value="${line.luasDiambil}" var="a"/>
                              <c:set value="${line.luasTerlibat}" var="b"/>
                              <fmt:formatNumber pattern="#,##0.00" value="${b-a}"/>
                        </c:if>
                        <c:if test="${line.luasDiambil eq null}">0</c:if>
                    </display:column>
                    <display:column property="namaPenjaga" title="Pemilik"/>
                    <display:column property="kodSyaratNyata.syarat" title="Syarat Nyata"/>
                     <display:column title="Diambil">
                         <s:radio name="diambilTanahAA[${line_rowNum-1}]" value="1"/>Ya
                         <s:radio name="diambilTanahAA[${line_rowNum-1}]" value="0"/>Tidak
                    </display:column>
                    <display:column title="Ulasan">
                        <s:text name="catatanTanahAA[${line_rowNum - 1}]" />
                    </display:column>
                    <display:column title="Kemaskini">
                    <div align="center">
                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popupTanahAA('${line.idTanahRizabPermohonan}');"/>
                    </div>
                    </display:column>
                    <display:column title="Hapus">
                    <div align="center">
                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeTanahRizab('${line.idTanahRizabPermohonan}');" />
                    </div>
                    </display:column>
                </display:table>
                            <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahTanahAA();"/>&nbsp;

            </c:if>
            <c:if test="${!edit}">
                  <display:table class="tablecloth" name="${actionBean.senaraiTanahAA}" pagesize="20" style="width:100%" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/maklumat_tambahan">
                    <display:column title="No">${line_rowNum}</display:column>
                    <display:column property="noLot" title="No. AA" />
                    <display:column property="rizab.nama" title="Jenis Tanah" />
                    <display:column property="daerah.nama" title="Daerah" />
                    <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line.luasTerlibat}"/>&nbsp;${line.kodUnitLuas.nama}</display:column>
                    <display:column title="Luas Diambil"><fmt:formatNumber pattern="#,##0.00" value="${line.luasDiambil}"/>&nbsp;${line.kodUnitLuas.nama}</display:column>
                    <display:column title="Baki Luas">
                        <c:if test="${line.luasTerlibat ne null}">
                              <c:set value="${line.luasDiambil}" var="a"/>
                              <c:set value="${line.luasTerlibat}" var="b"/>
                              <fmt:formatNumber pattern="#,##0.00" value="${b-a}"/>
                        </c:if>
                        <c:if test="${line.luasDiambil eq null}">0</c:if>
                    </display:column>
                    <display:column property="namaPenjaga" title="Pemilik"/>
                    <display:column property="kodSyaratNyata.syarat" title="Syarat Nyata"/>
                </display:table>
            </c:if>
        </fieldset>    </div>
   <br/>
  <br/>
     <div class="subtitle displaytag">
     <fieldset class="aras1" id="locate">
        <legend>
            Maklumat Tanah Registration of Holding (ROH)/(GSA)
        </legend>
        <p align="center">
        <c:if test="${edit}">
        <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" cellpadding="0" cellspacing="0"
                       requestURI="/pengambilan/maklumat_asas_pengambilan" id="line">
            <display:column title="No" sortable="true">${line_rowNum}</display:column>
            <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
            <%--<display:column title="No. Hakmilik"  class="popup hakmilik${line_rowNum}">${line.hakmilik.kodHakmilik.kod}${line.hakmilik.noHakmilik}</display:column>--%>
            <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
            <display:column title="No. ROH" ><%--${line.hakmilik.lot.nama}${line.hakmilik.noLot}--%></display:column>
            <display:column title="Jenis ROH/GSA" ><%--${line.hakmilik.lot.nama}${line.hakmilik.noLot}--%></display:column>
            <display:column title="Luas">
                <s:hidden name="luas1" value="${line.hakmilik.luas}" id="luas${line_rowNum-1}" />
                <s:hidden name="unitLuas" value="${line.hakmilik.kodUnitLuas.nama}" id="unitLuas${line_rowNum-1}" />
                <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>
                &nbsp;${line.hakmilik.kodUnitLuas.nama}
            </display:column>
            <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
            <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
            <display:column title="Luas Diambil">
                <s:text name="luasTerlibat[${line_rowNum - 1}]" id="luasTerlibat${line_rowNum - 1}"  onkeyup="validateLuas(this,${line_rowNum - 1})"/>
                &nbsp;${line.hakmilik.kodUnitLuas.nama}
            </display:column>
            <display:column title="Baki Luas">
                <c:if test="${line.luasTerlibat ne null}">
                      <c:set value="${line.luasTerlibat}" var="a"/>
                      <c:set value="${line.hakmilik.luas}" var="b"/>
                      <fmt:formatNumber pattern="#,##0.00" value="${b-a}"/>
                </c:if>
                <c:if test="${line.luasTerlibat eq null}">0</c:if>
            </display:column>

            &nbsp;
        </display:table>
                    <%--<s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahTanahRizab();"/>&nbsp;--%>

    </c:if>
    <c:if test="${!edit}">
         <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" cellpadding="0" cellspacing="0"
                       requestURI="/pengambilan/maklumat_asas_pengambilan" id="line">
            <display:column title="No" sortable="true">${line_rowNum}</display:column>
            <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
            <%--<display:column title="No. Hakmilik"  class="popup hakmilik${line_rowNum}">${line.hakmilik.kodHakmilik.kod}${line.hakmilik.noHakmilik}</display:column>--%>
            <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
            <display:column title="No. ROH" ><%--${line.hakmilik.lot.nama}${line.hakmilik.noLot}--%></display:column>
            <display:column title="Jenis ROH/GSA" ><%--${line.hakmilik.lot.nama}${line.hakmilik.noLot}--%></display:column>
            <display:column title="Luas">
                <s:hidden name="luas1" value="${line.hakmilik.luas}" id="luas${line_rowNum-1}" />
                <s:hidden name="unitLuas" value="${line.hakmilik.kodUnitLuas.nama}" id="unitLuas${line_rowNum-1}" />
                <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>
                &nbsp;${line.hakmilik.kodUnitLuas.nama}
            </display:column>
            <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
            <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
            <display:column title="Luas Diambil">
                <s:text name="luasTerlibat[${line_rowNum - 1}]" id="luasTerlibat${line_rowNum - 1}"  onkeyup="validateLuas(this,${line_rowNum - 1})"/>
                &nbsp;${line.hakmilik.kodUnitLuas.nama}
            </display:column>
            <display:column title="Baki Luas">
                <c:if test="${line.luasTerlibat ne null}">
                      <c:set value="${line.luasTerlibat}" var="a"/>
                      <c:set value="${line.hakmilik.luas}" var="b"/>
                      <fmt:formatNumber pattern="#,##0.00" value="${b-a}"/>
                </c:if>
                <c:if test="${line.luasTerlibat eq null}">0</c:if>
            </display:column>
        </display:table>
            </c:if>
    </fieldset>
     </div>
   <br/>
  <br/>
     <div class="subtitle displaytag">
     <fieldset class="aras1" id="locate">
        <legend>
            Maklumat Tanah Tidak Dapat Dikesan (TDK)
        </legend>
        <p align="center">
        <c:if test="${edit}">
        <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" style="width:100%" cellpadding="0" cellspacing="0" id="line"
                       requestURI="${pageContext.request.contextPath}/maklumat_tambahan">
            <display:column title="No">${line_rowNum}</display:column>
            <display:column title="No Lot" />
            <display:column title="Luas Diambil" />
            <display:column title="Luas Sebenar" />
            <%--<display:column property="" title="Bandar/Pekan/Mukim" />--%>
            <display:column title="Cukai"/>
            <display:column title="Nilai Cukai(RM)"/>
            <display:column title="Kemaskini"/>
            <display:column title="Hapus">
            <%--<div align="center">
                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeTanahRizab('${line.idTanahRizabPermohonan}');" />
            </div>--%>
            </display:column>
        </display:table>
                    <%--<s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahTanahTDK();"/>&nbsp;--%>

    </c:if>
    <c:if test="${!edit}">
         <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" cellpadding="0" cellspacing="0"
                       requestURI="/pengambilan/maklumat_asas_pengambilan" id="line">
            <display:column title="No" sortable="true">${line_rowNum}</display:column>
            <display:column title="No Lot" />
            <display:column title="Luas Diambil" />
            <display:column title="Luas Sebenar" />
            <%--<display:column property="" title="Bandar/Pekan/Mukim" />--%>
            <display:column title="Cukai"/>
            <display:column title="Nilai Cukai(RM)"/>
        </display:table>
    </c:if>
    </fieldset>
    </div>
    </c:if>
     <br/>
     <br/>

            <br/>
            <br/>
            <c:if test="${formPP}">
                 <table>
                <tr>
                    <td><label for="nama">Jenis Tanah :</label></td>


                    <td><s:radio name="kodtanah" value="01" onclick="showJenisTanahBandar();"/>&nbsp; Tanah Bandar<br></td>
                    <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod eq null}">
                    <td><label id="bandar" for="nama">No. Warta :</label></td>
                    <td><s:text name="noWartaPP" size="20" id="noWartaBandar"/></td>
                    <td>&nbsp;</td>
                    </c:if>
                     <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod eq '01'}">
                    <td><label id="bandar" for="nama">No. Warta :</label></td>
                    <td><s:text name="noWartaPP" size="20" id="noWartaBandar"/></td>
                    <td>&nbsp;</td>
                    </c:if>
                </tr>
                 <tr>
                    <td>&nbsp;</td>
                    <td><s:radio name="kodtanah" value="02" onclick="showJenisTanahPekan();" />&nbsp; Tanah Pekan<br></td>
                      <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod eq null}">
                    <td><label id="pekan" for="nama">No. Warta :</label></td>
                    <td><s:text name="noWartaPP" size="20" id="noWartaPekan"/></td>
                    <td>&nbsp;</td>
                    </c:if>
                    <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod eq '02'}">
                    <td><label id="pekan" for="nama">No. Warta :</label></td>
                    <td><s:text name="noWartaPP" size="20" id="noWartaPekan"/></td>
                    <td>&nbsp;</td>
                    </c:if>
                </tr>
                 <tr>
                    <td>&nbsp;</td>
                    <td><s:radio name="kodtanah" value="03"/>&nbsp; Tanah Desa<br></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                 <tr>
                    <td>&nbsp;</td>
                    <td><s:radio name="kodtanah" value="07" onclick="showJenisTanahSeksyen();"/>&nbsp; Tanah Seksyen</td>
                    <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod eq null}">
                    <td><label id="seksyen" for="nama">No. Warta :</label></td>
                    <td><s:text name="noWartaPP" size="20" id="noWartaSeksyen"/></td>
                    <td>&nbsp;</td>
                    </c:if>
                    <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod eq '07'}">
                    <td><label id="seksyen" for="nama">No. Warta :</label></td>
                    <td><s:text name="noWartaPP" size="20" id="noWartaSeksyen"/></td>
                    <td>&nbsp;</td>
                    </c:if>
                </tr>
                <tr>
                    <td><label for="nama">Dalam Kawasan PBT :</label></td>
                    <td><s:radio name="permohonanLaporanPelan.kawasanPihakBerkuasa" value="Y" onclick="showUlasan();"/>&nbsp; Ya
                        <s:radio name="permohonanLaporanPelan.kawasanPihakBerkuasa" value="T" onclick="hideUlasan();"/>&nbsp; Tidak</td>
                    <td><label id="kawkuasa" for="nama">No. Warta :</label></td>
                    <td><s:text name="noWartaPihakBerkuasa" size="20" id="noWartaPihakBerkuasa"/></td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td><label id="namaPBT" for="nama">Nama PBT :</label></td>
                    <td><s:text name="namaPihakBerkuasa" size="20" id="namaPihakBerkuasa"/></td>
                </tr>
                </table>
                <br>
            <p>
                <label for="nolitho">No. Lembaran Piawai :</label>
                <s:text name="noLitho" size="20" id="nolitho"/>
            </p>
            <p>
                <label for="projekkerajaan">Di tanda untuk projek kerajaan :</label>
               <s:radio name="projekKerajaan" value="Y"/>&nbsp; Ya
               <s:radio name="projekKerajaan" value="T"/>&nbsp; Tidak
            </p>
            <p>
                <label for="catatan">Lain lain hal :</label>
                <s:textarea name="catatan" rows="5" cols="50" id="catatan"/>
            </p>
            <p align="center">
                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                <s:button name="Charting" id="lakarPelan" value="Charting" class="btn"  onclick="RunProgram('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.permohonan.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
            </p>
            <br>
              <div class="subtitle displaytag">
     <fieldset class="aras1" id="locate">
            <legend>
                Permohonan Terdahulu
            </legend>
            <p align="center"><label></label>

                <%--<display:table class="tablecloth" name="${actionBean.permohonanTerdahuluList}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/maklumat_tambahan">--%>
                <display:table class="tablecloth" name="${actionBean.permohonanManualList}" pagesize="20" style="width:100%" cellpadding="0" cellspacing="0" id="line">

                    <display:column title="No">${line_rowNum}</display:column>
                    <display:column property="noFail" title="No Fail" />

                    <display:column title="Kemaskini">
                    <div align="center">
                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popupPermohonanTerdahulu('${line.idMohonManual}');"/>
                    </div>
                    </display:column>
                    <display:column title="Hapus">
                    <div align="center">
                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removePermohonanTerdahulu('${line.idMohonManual}');"/>
                    </div>
                    </display:column>
                </display:table>
            <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahPermohonanTerdahulu()"/>&nbsp;
            <br>

        </fieldset>
    </div>
            </c:if>
            <c:if test="${viewPP}">
                 <table>
                <tr>
                    <td><label for="nama">Jenis Tanah :</label></td>


                    <td><s:radio name="kodtanah" value="01" onclick="showJenisTanahBandar();" disabled="true"/>&nbsp; Tanah Bandar<br></td>
                    <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod eq null}">
                    <td><label id="bandar" for="nama">No. Warta :</label></td>
                    <td><s:text name="noWartaPP" size="20" id="noWartaBandar" readonly="true"/></td>
                    <td>&nbsp;</td>
                    </c:if>
                     <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod eq '01'}">
                    <td><label id="bandar" for="nama">No. Warta :</label></td>
                    <td><s:text name="noWartaPP" size="20" id="noWartaBandar" readonly="true"/></td>
                    <td>&nbsp;</td>
                    </c:if>
                </tr>
                 <tr>
                    <td>&nbsp;</td>
                    <td><s:radio name="kodtanah" value="02" onclick="showJenisTanahPekan();" disabled="true"/>&nbsp; Tanah Pekan<br></td>
                      <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod eq null}">
                    <td><label id="pekan" for="nama">No. Warta :</label></td>
                    <td><s:text name="noWartaPP" size="20" id="noWartaPekan" readonly="true"/></td>
                    <td>&nbsp;</td>
                    </c:if>
                    <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod eq '02'}">
                    <td><label id="pekan" for="nama">No. Warta :</label></td>
                    <td><s:text name="noWartaPP" size="20" id="noWartaPekan" readonly="true"/></td>
                    <td>&nbsp;</td>
                    </c:if>
                </tr>
                 <tr>
                    <td>&nbsp;</td>
                    <td><s:radio name="kodtanah" value="03"/>&nbsp; Tanah Desa<br></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                 <tr>
                    <td>&nbsp;</td>
                    <td><s:radio name="kodtanah" value="07" onclick="showJenisTanahSeksyen();" disabled="true"/>&nbsp; Tanah Seksyen</td>
                    <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod eq null}">
                    <td><label id="seksyen" for="nama">No. Warta :</label></td>
                    <td><s:text name="noWartaPP" size="20" id="noWartaSeksyen" readonly="true"/></td>
                    <td>&nbsp;</td>
                    </c:if>
                    <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod eq '07'}">
                    <td><label id="seksyen" for="nama">No. Warta :</label></td>
                    <td><s:text name="noWartaPP" size="20" id="noWartaSeksyen" readonly="true"/></td>
                    <td>&nbsp;</td>
                    </c:if>
                </tr>
                <tr>
                    <td><label for="nama">Dalam Kawasan PBT :</label></td>
                    <td><s:radio name="permohonanLaporanPelan.kawasanPihakBerkuasa" value="Y" onclick="showUlasan();" disabled="true"/>&nbsp; Ya
                        <s:radio name="permohonanLaporanPelan.kawasanPihakBerkuasa" value="T" onclick="hideUlasan();" disabled="true"/>&nbsp; Tidak</td>
                    <td><label id="kawkuasa" for="nama">No. Warta :</label></td>
                    <td><s:text name="noWartaPihakBerkuasa" size="20" id="noWartaPihakBerkuasa" readonly="true"/></td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td><label id="namaPBT" for="nama">Nama PBT :</label></td>
                    <td><s:text name="namaPihakBerkuasa" size="20" id="namaPihakBerkuasa" readonly="true"/></td>
                </tr>
                </table>
                <br>
            <p>
                <label for="nolitho">No. Lembaran Piawai :</label>
                <s:text name="noLitho" size="20" id="nolitho" readonly="true"/>
            </p>
            <p>
                <label for="projekkerajaan">Di tanda untuk projek kerajaan :</label>
               <s:radio name="projekKerajaan" value="Y" disabled="true" />&nbsp; Ya
               <s:radio name="projekKerajaan" value="T" disabled="true"/>&nbsp; Tidak
            </p>
            <p>
                <label for="catatan">Lain lain hal :</label>
                <s:textarea name="catatan" rows="5" cols="50" id="catatan" readonly="true"/>
            </p>
            <p align="center">
                <s:button name="simpan" id="save" value="Simpan" class="btn" disabled="true" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                <s:button name="Charting" id="lakarPelan" value="Charting" disabled="true" class="btn"  onclick="RunProgram('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.permohonan.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
            </p>
            <br>
              <div class="subtitle displaytag">
     <fieldset class="aras1" id="locate">
            <legend>
                Permohonan Terdahulu
            </legend>
            <p align="center"><label></label>

                <%--<display:table class="tablecloth" name="${actionBean.permohonanTerdahuluList}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/maklumat_tambahan">--%>
                <display:table class="tablecloth" name="${actionBean.permohonanManualList}" pagesize="20" style="width:100%" cellpadding="0" cellspacing="0" id="line">

                    <display:column title="No">${line_rowNum}</display:column>
                    <display:column property="noFail" title="No Fail" />

                    <%--<display:column title="Kemaskini">
                    <div align="center">
                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popupPermohonanTerdahulu('${line.idMohonManual}');"/>
                    </div>
                    </display:column>
                    <display:column title="Hapus">
                    <div align="center">
                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removePermohonanTerdahulu('${line.idMohonManual}');"/>
                    </div>
                    </display:column>--%>
                </display:table>
                <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahPermohonanTerdahulu()" disabled="true"/>&nbsp;
            <br>

        </fieldset>
    </div>
            </c:if>


 </s:form>