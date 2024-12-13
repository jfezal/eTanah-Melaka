<%-- 
    Document   : L4tanah
    Created on : 09-Jun-2011, 04:56:31
    Author     : nordiyana
--%>

<%--
    Document   : EndorsanHakmilik
    Created on : 31-May-2011, 17:14:26
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




  function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);

        },'html');

    }


</script>

<s:form beanclass="etanah.view.stripes.pengambilan.EndorsanHakmilikActionBean">
    <s:messages/>

    <div class="subtitle displaytag">
<c:if test="${pampasanTambahan}">
        <fieldset class="aras1" id="locate">
            <legend>
                Maklumat Tanah : Pengambilan Sebahagian
            </legend>
            <%--<c:if test="${edit}">--%>
            <%--<p align="center">
                    <label>Jumlah Keluasan Tanah</label>&nbsp;
                </p>
                <p>
                    <label>Meter Persegi :</label>${actionBean.amountMH}
                </p>
                <p>
                    <label>Hektar :</label><fmt:formatNumber maxFractionDigits="3" value="${actionBean.convH}"/>
                </p>--%>
            <p align="center">

                 <display:table class="tablecloth" name="${actionBean.l4TanahList}" pagesize="20" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/maklumat_asas_pengambilan" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
                    <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                     <display:column title="Luas Sebenar">
                        <s:hidden name="luas1" value="${line.hakmilik.luas}" id="luas${line_rowNum-1}" />
                        <s:hidden name="unitLuas" value="${line.hakmilik.kodUnitLuas.nama}" id="unitLuas${line_rowNum-1}" />
                        <fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>
                        &nbsp;${line.hakmilik.kodUnitLuas.nama}
                    </display:column>
                    <display:column title="Luas Diambil">
                        <fmt:formatNumber pattern="#,##0.0000" value="${line.luasTerlibat}"/>
                        &nbsp;${line.hakmilik.kodUnitLuas.nama}
                    </display:column>
                    <display:column title="Baki Luas" >
                              <c:set value="${line.luasTerlibat}" var="a"/>
                              <c:set value="${line.hakmilik.luas}" var="c"/>
                              <fmt:formatNumber pattern="#,##0.0000" value="${c-a}"/>
                    </display:column>
                    <display:column title="Luas Pelan B1">
                        <fmt:formatNumber pattern="#,##0.0000" value="${line.luasPelanB1}"/>
                        &nbsp;${line.hakmilik.kodUnitLuas.nama}
                    </display:column>
                    <display:column title="Ukur Halus" >
                              <c:set value="${line.luasTerlibat}" var="a"/>
                              <c:set value="${line.luasPelanB1}" var="b"/>
                              <c:set value="${line.hakmilik.luas}" var="c"/>
                              <fmt:formatNumber pattern="#,##0.0000" value="${(c-a)-b}"/>
                    </display:column>
                    <display:column title="Nilai Tanah">
                        <fmt:formatNumber pattern="#,##0.0000" value="${line.nilaianJpph}"/>
                        &nbsp;${line.hakmilik.kodUnitLuas.nama}
                    </display:column>
                    <display:column title="Kategori Pampasan">
                         <c:if test="${(line.hakmilik.luas - line.luasTerlibat) < line.luasPelanB1}">-</c:if>
                         <c:if test="${(line.hakmilik.luas - line.luasTerlibat) > line.luasPelanB1}">Pampasan Tambahan</c:if>
                         <c:if test="${(line.hakmilik.luas - line.luasTerlibat) eq line.luasPelanB1}">Pampasan Kekal</c:if>
                    </display:column>
                </display:table>
                    </fieldset>

         </c:if>
                 <c:if test="${view}">

        <fieldset class="aras1" id="locate">
                <legend>
                    Maklumat Tanah : Pengambilan Keseluruhan
                </legend>
                <p align="center">
              <%--  <c:if test="${edit}">--%>
                  <display:table class="tablecloth" name="${actionBean.l4TanahList}" pagesize="20" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/maklumat_asas_pengambilan" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
                    <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                     <display:column title="Luas Sebenar">
                        <s:hidden name="luas1" value="${line.hakmilik.luas}" id="luas${line_rowNum-1}" />
                        <s:hidden name="unitLuas" value="${line.hakmilik.kodUnitLuas.nama}" id="unitLuas${line_rowNum-1}" />
                        <fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>
                        &nbsp;${line.hakmilik.kodUnitLuas.nama}
                    </display:column>
                    <display:column title="Luas Diambil">
                        <s:text name="luasTerlibat[${line_rowNum - 1}]" readonly="true" id="luasTerlibat${line_rowNum - 1}"  onkeyup="validateLuas(this,${line_rowNum - 1})"/>
                        &nbsp;${line.hakmilik.kodUnitLuas.nama}
                    </display:column>
                    <display:column title="Baki Luas" >
                              <c:set value="${line.luasTerlibat}" var="a"/>
                              <c:set value="${line.hakmilik.luas}" var="c"/>
                              <fmt:formatNumber pattern="#,##0.0000" value="${c-a}"/>
                    </display:column>
                    <display:column title="Luas Pelan B1">
                        <s:text name="luasPelanB1[${line_rowNum - 1}]" readonly="true" id="luasPelanB1${line_rowNum - 1}"/>
                        &nbsp;${line.hakmilik.kodUnitLuas.nama}
                    </display:column>
                    <display:column title="Ukur Halus" >
                              <c:set value="${line.luasTerlibat}" var="a"/>
                              <c:set value="${line.luasPelanB1}" var="b"/>
                              <c:set value="${line.hakmilik.luas}" var="c"/>
                              <fmt:formatNumber pattern="#,##0.0000" value="${(c-a)-b}"/>
                    </display:column>
                    <display:column title="Nilai Tanah">
                        <s:text name="nilaiTanah[${line_rowNum - 1}]" readonly="true" id="nilaiTanah${line_rowNum - 1}"/>
                        &nbsp;${line.hakmilik.kodUnitLuas.nama}
                    </display:column>
                    <display:column title="Kategori Pampasan">
                         <c:if test="${(line.hakmilik.luas - line.luasTerlibat) < line.luasPelanB1}">-</c:if>
                         <c:if test="${(line.hakmilik.luas - line.luasTerlibat) > line.luasPelanB1}">Pampasan Tambahan</c:if>
                         <c:if test="${(line.hakmilik.luas - line.luasTerlibat) eq line.luasPelanB1}">Pampasan Kekal</c:if>
                    </display:column>
                </display:table>
        </fieldset>
                    </c:if>
    </div>
    <br>
<c:if test="${combine}">
    <div class="subtitle displaytag">

        <fieldset class="aras1" id="locate">
            <legend>
                Maklumat Tanah : Pengambilan Sebahagian
            </legend>
            <%--<c:if test="${edit}">--%>
            <%--<p align="center">
                    <label>Jumlah Keluasan Tanah</label>&nbsp;
                </p>
                <p>
                    <label>Meter Persegi :</label>${actionBean.amountMH}
                </p>
                <p>
                    <label>Hektar :</label><fmt:formatNumber maxFractionDigits="3" value="${actionBean.convH}"/>
                </p>--%>
            <p align="center">

                 <display:table class="tablecloth" name="${actionBean.sebahagianTanahList}" pagesize="20" style="width:100%" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/maklumat_tambahan">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
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
                   <%-- <c:if test="${!edit}">
                    <display:column title="Hapus">
                   <div align="center">
                   <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                    id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.hakmilikPermohonanList[line_rowNum-1].id}');" />
                   </div>
                   </display:column>
                    </c:if>--%>
                </display:table>
            <%--</c:if>--%>
                    </fieldset>


        <fieldset class="aras1" id="locate">
                <legend>
                    Maklumat Tanah : Pengambilan Keseluruhan
                </legend>
                <p align="center">
              <%--  <c:if test="${edit}">--%>
                  <display:table class="tablecloth" name="${actionBean.keseluruhanTanahList}" pagesize="20" style="width:100%" cellpadding="0" cellspacing="0" id="line"
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
                   <%-- <c:if test="${!edit}">
                    <display:column title="Hapus">
                   <div align="center">
                   <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                    id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.hakmilikPermohonanList[line_rowNum-1].id}');" />
                   </div>
                   </display:column>
                    </c:if>--%>
                </display:table>


         </c:if>


        </fieldset>
    </div>











</s:form>

