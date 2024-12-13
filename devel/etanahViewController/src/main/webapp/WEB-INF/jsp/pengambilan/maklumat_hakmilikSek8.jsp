<%-- 
    Document   : maklumat_hakmilikSek8
    Created on : 27-May-2011, 10:13:14
    Author     : nordiyana
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
function removeSingle(id)
{
if(confirm('Adakah anda pasti?')) {
var url = '${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikSek8?deleteSingle&id='
+id;
$.get(url,
function(data){
$('#page_div').html(data);
},'html');}
       <%-- alert(ialertd);--%>
}
     function tambahBaru(){
            window.open("${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikSek8?hakMilikPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

function refreshPageHakmilik(){
var url = '${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikSek8?refreshpage';
$.get(url,
function(data){
$('#page_div').html(data);
},'html');
}

  $(document).ready( function() {


        var len = $(".daerah").length;

        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                window.open("${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikSek8?popup&idHakmilik="+$(this).text(), "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }
    });

  function validateLuas(idVar,rowNo){

     var str = idVar.value;
     var luasTerlibat = parseInt(idVar.value);
     //alert('Luas Id:'+("luas"+rowNo));
     var luas = parseInt($('#luas'+rowNo).val());
     //alert(luas);
    // var luas = parseInt(document.getElementById("luas"+rowNo).value);
   //  var luas = parseInt(document.getElementById("luas"+rowNo).value);
   //  alert('Luas'+luas);

     if(luasTerlibat > luas){
         alert("Pastikan Luas Diambil tidak melebihi Luas");
         idVar.value = str.substring(0,str.length-1);
         idVar.focus();
         return true;
      }
  }

  function validateKodUnitLuas(idVar,rowNo){
      <%--alert(idVar.value);--%>
      if(idVar.value == 'M'){
          var unitLuasDiambil = "Meter Persegi";
          alert(unitLuasDiambil);
      }
      if(idVar.value == 'H'){
          var unitLuasDiambil = "Hektar";
          alert(unitLuasDiambil);
      }

      var unitLuas = document.getElementById("unitLuas"+rowNo).value;
      alert(unitLuas);

  }

  function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);

        },'html');

    }

    function tambahTanahKR(){
window.open("${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikSek8?tanahKRPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }
    function tambahTanahTDK(){
window.open("${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikSek8?tanahTDKPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

         function tambahTanahAA(){
window.open("${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikSek8?tanahAAPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

function popupTanahRizab(h){
var url = '${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikSek8?showEdittanahKR&idTanahRizabPermohonan='+h;
window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
}
function popupTanahAA(h){
var url = '${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikSek8?showEdittanahAA&idTanahRizabPermohonan='+h;
window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
}
function removeTanahRizab(idTanahRizabPermohonan)
{
    if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikSek8?deleteTanahRizab&idTanahRizabPermohonan='
+idTanahRizabPermohonan;
            $.get(url,
            function(data){
            $('#page_div').html(data);
            self.opener.refreshPageHakmilik();
            },'html');
        }
}


</script>

<s:form beanclass="etanah.view.stripes.pengambilan.maklumatHakmilikSek8ActionBean">
    <s:messages/>
    <div class="subtitle displaytag">

        <fieldset class="aras1" id="locate">
            <legend>
                Maklumat Tanah
            </legend>
            <c:if test="${edit}">
            <p align="center">
                    <label>Jumlah Keluasan Tanah</label>&nbsp;
                </p>
                <p>
                    <label>Meter Persegi :</label>${actionBean.amountMH}
                </p>
                <p>
                    <label>Hektar :</label><fmt:formatNumber maxFractionDigits="3" value="${actionBean.convH}"/>
                </p>
            <p align="center">

                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/maklumat_hakmilikSek8" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
                    <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                    <display:column title="Luas">
                        <s:hidden name="luas1" value="${line.hakmilik.luas}" id="luas${line_rowNum-1}" />
                        <s:hidden name="unitLuas" value="${line.hakmilik.kodUnitLuas.nama}" id="unitLuas${line_rowNum-1}" />
                        <fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>
                        &nbsp;${line.hakmilik.kodUnitLuas.nama}
                    </display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column title="Bandar/Pekan/Mukim" style="text-transform : capitalize">${line.hakmilik.bandarPekanMukim.nama} ${fn:toLowerCase(line.hakmilik.seksyen.nama)}</display:column>
                    <display:column title="Luas Diambil">
                         <c:if test="${line.luasTerlibat eq null}"><s:text name="luasTerlibat[${line_rowNum - 1}]" id="luasTerlibat${line_rowNum - 1}"  onkeyup="validateLuas(this,${line_rowNum - 1})" value="0"/>
                        &nbsp;${line.hakmilik.kodUnitLuas.nama}</c:if>
                         <c:if test="${line.luasTerlibat ne null}">
                        <s:text name="luasTerlibat[${line_rowNum - 1}]" id="luasTerlibat${line_rowNum - 1}"  onkeyup="validateLuas(this,${line_rowNum - 1})"/>
                        &nbsp;${line.hakmilik.kodUnitLuas.nama}
                       </c:if>
                    </display:column>
                    <display:column title="Baki Luas">
                        <c:if test="${line.luasTerlibat ne null}">
                              <c:set value="${line.luasTerlibat}" var="a"/>
                              <c:set value="${line.hakmilik.luas}" var="b"/>
                              <fmt:formatNumber pattern="#,##0.0000" value="${b-a}"/>
                        </c:if>
                        <c:if test="${line.luasTerlibat eq null}">0</c:if>
                    </display:column>
                        <%--hakmilik.kegunaanTanah.nama--%>
                    <display:column property="hakmilik.syaratNyata.syarat" title="Kegunaan Tanah" />
                    <c:if test="${!edit}">
                    <display:column title="Hapus">
                   <div align="center">
                   <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                    id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.hakmilikPermohonanList[line_rowNum-1].id}');" />
                   </div>
                   </display:column>
                    </c:if>
                </display:table>
                    <br>
                    <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBaru();"/>&nbsp;
                    <s:button name="simpanHakmilik" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>


            </c:if>
            <c:if test="${!edit}">
                <p align="center">
                    <label>Jumlah Keluasan Tanah</label>&nbsp;
                </p>
                <p>
                    <label>Meter Persegi :</label>${actionBean.amountMH}
                </p>
                <p>
                    <label>Hektar :</label>${actionBean.convH}
                </p>
                <br>
                <p align="center">
                 <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/maklumat_asas_pengambilan" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
                    <%--<display:column title="No. Hakmilik"  class="popup hakmilik${line_rowNum}">${line.hakmilik.kodHakmilik.kod}${line.hakmilik.noHakmilik}</display:column>--%>
                    <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                    <display:column title="Luas"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column title="Bandar/Pekan/Mukim" style="text-transform : capitalize">${line.hakmilik.bandarPekanMukim.nama} ${fn:toLowerCase(line.hakmilik.seksyen.nama)}</display:column>
                    <display:column title="Luas Diambil" property="luasTerlibat"><%--&nbsp;${line.hakmilik.kodUnitLuas.nama}--%></display:column>
                    <display:column title="Baki Luas">
                        <c:if test="${line.luasTerlibat ne null}">
                              <c:set value="${line.luasTerlibat}" var="a"/>
                              <c:set value="${line.hakmilik.luas}" var="b"/>
                              <fmt:formatNumber pattern="#,##0.00" value="${b-a}"/>
                        </c:if>
                        <c:if test="${line.luasTerlibat eq null}">0</c:if>
                    </display:column>
                    <display:column property="hakmilik.syaratNyata.syarat" title="Kegunaan Tanah" />
                </display:table>
                    </c:if>
                     </fieldset>
      <%--  <fieldset class="aras1" id="locate">
                <legend>
                    Maklumat Tanah Sebahagian
                </legend>
                <p align="center">
                <c:if test="${edit}">
                  <display:table class="tablecloth" name="${actionBean.sebahagianTanahList}" pagesize="20" style="width:100%" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/maklumat_tambahan">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
                    <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                    <display:column title="Luas">
                        <s:hidden name="luas1" value="${line.hakmilik.luas}" id="luas${line_rowNum-1}" />
                        <s:hidden name="unitLuas" value="${line.hakmilik.kodUnitLuas.nama}" id="unitLuas${line_rowNum-1}" />
                        <fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>
                        &nbsp;${line.hakmilik.kodUnitLuas.nama}
                    </display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column title="Luas Diambil" property="luasTerlibat"/>
                    <display:column title="Baki Luas">
                        <c:if test="${line.luasTerlibat ne null}">
                              <c:set value="${line.luasTerlibat}" var="a"/>
                              <c:set value="${line.hakmilik.luas}" var="b"/>
                              <fmt:formatNumber pattern="#,##0.00" value="${b-a}"/>
                        </c:if>
                        <c:if test="${line.luasTerlibat eq null}">0</c:if>
                    </display:column>
                    <display:column property="hakmilik.kegunaanTanah.nama" title="Kegunaan Tanah" />
                    <c:if test="${!edit}">
                    <display:column title="Hapus">
                   <div align="center">
                   <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                    id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.hakmilikPermohonanList[line_rowNum-1].id}');" />
                   </div>
                   </display:column>
                    </c:if>
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
        </fieldset>
        <fieldset class="aras1" id="locate">
                <legend>
                    Maklumat Tanah Tidak Dapat Dikesan (TDK)
                </legend>
                <p align="center">
                <c:if test="${edit}">
                <display:table class="tablecloth" name="${actionBean.tanahRizabList}" pagesize="20" style="width:100%" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/maklumat_tambahan">
                    <display:column title="No">${line_rowNum}</display:column>
                    <display:column property="rizab.nama" title="Jenis Rizab" />
                    <display:column title="No Lot" property="noLot" />
                    <display:column title="Luas Diambil" property="luasTerlibat" />
                    <display:column title="Luas Sebenar" />
                    <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column title="Cukai"><c:if test="${line.adaCukai eq 'A'}">Ada</c:if>
                    <c:if test="${line.adaCukai eq 'T'}">Tiada</c:if></display:column>
                    <display:column title="Nilai Cukai(RM)"><c:if test="${line.adaCukai ne 'T'}">${line.cukai}</c:if><c:if test="${line.adaCukai eq 'T'}">0</c:if></display:column>
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
                            <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahTanahTDK();"/>&nbsp;

            </c:if>
            <c:if test="${!edit}">
                  <display:table class="tablecloth" name="${actionBean.tanahRizabList}" pagesize="20" style="width:100%" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/maklumat_tambahan">
                    <display:column title="No">${line_rowNum}</display:column>
                     <display:column property="rizab.nama" title="Jenis Rizab" />
                    <display:column title="No Lot" property="noLot" />
                    <display:column title="Luas Diambil" property="luasTerlibat" />
                    <display:column title="Luas Sebenar" />
                    <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column title="Cukai" property="adaCukai"/>
                    <display:column title="Nilai Cukai(RM)" property="cukai"/>
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
            </c:if>
        </fieldset>
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
                    <display:column title="No. Hakmilik"  class="popup hakmilik${line_rowNum}">${line.hakmilik.kodHakmilik.kod}${line.hakmilik.noHakmilik}</display:column>
                    <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                    <display:column title="No. ROH" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                    <display:column title="Jenis ROH/GSA" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
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
                    <c:if test="${!edit}">
                    <display:column title="Hapus">
                   <div align="center">
                   <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                    id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.hakmilikPermohonanList[line_rowNum-1].id}');" />
                   </div>
                   </display:column>
                    </c:if>
                    &nbsp;
                </display:table>
                            <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahTanahRizab();"/>&nbsp;

            </c:if>
            <c:if test="${!edit}">
                 <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/maklumat_asas_pengambilan" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
                    <display:column title="No. Hakmilik"  class="popup hakmilik${line_rowNum}">${line.hakmilik.kodHakmilik.kod}${line.hakmilik.noHakmilik}</display:column>
                    <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                    <display:column title="No. ROH" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                    <display:column title="Jenis ROH/GSA" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
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
                    <c:if test="${!edit}">
                    <display:column title="Hapus">
                   <div align="center">
                   <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                    id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.hakmilikPermohonanList[line_rowNum-1].id}');" />
                   </div>
                   </display:column>
                    </c:if>
                    &nbsp;
                </display:table>
            </c:if>
                    
        </fieldset>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '831A'}">
             <fieldset class="aras1" id="locate">
                <legend>
                    Maklumat Tanah Kerajaan/Rizab
                </legend>
                <p align="center">
                <c:if test="${edit}">
                <display:table class="tablecloth" name="${actionBean.tanahRizabList}" pagesize="20" style="width:100%" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/maklumat_tambahan">
                    <display:column title="No">${line_rowNum}</display:column>
                    <display:column property="rizab.nama" title="Jenis Rizab" />
                    <display:column property="cawangan.name" title="Cawangan" />
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
                    <display:column property="noLot" title="No. PT/Lot"/>
                    <display:column property="noWarta" title="No. Warta"/>
                    <display:column property="tarikhWarta" title="Tarikh Warta" format="{0,date,dd/MM/yyyy}"/>
                    <display:column property="namaPenjaga" title="Pegawai Pengawal"/>
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
                            <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahTanahKR();"/>&nbsp;

            </c:if>
            <c:if test="${!edit}">
                 <display:table class="tablecloth" name="${actionBean.tanahRizabList}" pagesize="20" style="width:100%" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/maklumat_tambahan">
                    <display:column title="No">${line_rowNum}</display:column>
                    <display:column property="rizab.nama" title="Jenis Rizab" />
                    <display:column property="cawangan.name" title="Cawangan" />
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
                    <display:column property="noLot" title="No. PT/Lot"/>
                    <display:column property="noWarta" title="No. Warta"/>
                    <display:column property="tarikhWarta" title="Tarikh Warta" format="{0,date,dd/MM/yyyy}"/>
                    <display:column property="namaPenjaga" title="Pegawai Pengawal"/>
                </display:table>
            </c:if>
        </fieldset>
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
                    <display:column property="catatan" title="Catatan"/>

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
        </fieldset>
                 <fieldset class="aras1" id="locate">
                <legend>
                    Maklumat Tanah Tidak Dapat Dikesan (TDK)
                </legend>
                <p align="center">
                <c:if test="${edit}">
                <display:table class="tablecloth" name="${actionBean.tanahRizabList}" pagesize="20" style="width:100%" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/maklumat_tambahan">
                    <display:column title="No">${line_rowNum}</display:column>
                    <display:column property="rizab.nama" title="Jenis Rizab" />
                    <display:column title="No Lot" property="noLot" />
                    <display:column title="Luas Diambil" property="luasTerlibat" />
                    <display:column title="Luas Sebenar" />
                    <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column title="Cukai"><c:if test="${line.adaCukai eq 'A'}">Ada</c:if>
                    <c:if test="${line.adaCukai eq 'T'}">Tiada</c:if></display:column>
                    <display:column title="Nilai Cukai(RM)"><c:if test="${line.adaCukai ne 'T'}">${line.cukai}</c:if><c:if test="${line.adaCukai eq 'T'}">0</c:if></display:column>
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
                            <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahTanahTDK();"/>&nbsp;

            </c:if>
            <c:if test="${!edit}">
                  <display:table class="tablecloth" name="${actionBean.tanahRizabList}" pagesize="20" style="width:100%" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/maklumat_tambahan">
                    <display:column title="No">${line_rowNum}</display:column>
                     <display:column property="rizab.nama" title="Jenis Rizab" />
                    <display:column title="No Lot" property="noLot" />
                    <display:column title="Luas Diambil" property="luasTerlibat" />
                    <display:column title="Luas Sebenar" />
                    <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column title="Cukai" property="adaCukai"/>
                    <display:column title="Nilai Cukai(RM)" property="cukai"/>
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
            </c:if>
        </fieldset>

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
                    <display:column title="No. Hakmilik"  class="popup hakmilik${line_rowNum}">${line.hakmilik.kodHakmilik.kod}${line.hakmilik.noHakmilik}</display:column>
                    <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                    <display:column title="No. ROH" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                    <display:column title="Jenis ROH/GSA" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
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
                    <c:if test="${!edit}">
                    <display:column title="Hapus">
                   <div align="center">
                   <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                    id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.hakmilikPermohonanList[line_rowNum-1].id}');" />
                   </div>
                   </display:column>
                    </c:if>
                    &nbsp;
                </display:table>
                            <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahTanahRizab();"/>&nbsp;

            </c:if>
            <c:if test="${!edit}">
                 <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/maklumat_asas_pengambilan" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
                    <display:column title="No. Hakmilik"  class="popup hakmilik${line_rowNum}">${line.hakmilik.kodHakmilik.kod}${line.hakmilik.noHakmilik}</display:column>
                    <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                    <display:column title="No. ROH" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                    <display:column title="Jenis ROH/GSA" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
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
                    <c:if test="${!edit}">
                    <display:column title="Hapus">
                   <div align="center">
                   <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                    id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.hakmilikPermohonanList[line_rowNum-1].id}');" />
                   </div>
                   </display:column>
                    </c:if>
                    &nbsp;
                </display:table>
            </c:if>
                    </c:if>
        </fieldset>--%>
    </div>

</s:form>

