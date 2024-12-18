<%--
    Document   : maklumat_hakmilik_permohonan
    Created on : 08 Oktober 2009, 3:41:04 PM
    Author     : khairil
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript">
  $(document).ready(function() {


    var len = $(".daerah").length;

    for (var i = 0; i <= len; i++) {
      $('.hakmilik' + i).click(function() {
        window.open("${pageContext.request.contextPath}/hakmilik?hakmilikDetail&idHakmilik=" + $(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
      });
    }
  });

  function doEdit() {
    window.open('${pageContext.request.contextPath}/common/maklumat_hakmilik_permohonan?editHakmilikPermohonan', '.:eTanah:.',
            'status=0,toolbar=0,location=0,menubar=0,width=900,height=600');
  }
</script>
<div class="subtitle displaytag">
  <s:form beanclass="etanah.view.stripes.common.MaklumatHakmilikPermohonanActionBean">
    <div class="subtitle ">

      <fieldset class="aras1">
        <legend>
          Maklumat Hakmilik Permohonan
        </legend>
        <div class="content" align="center">
          <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}"  cellpadding="0" cellspacing="0"
                         requestURI="/common/maklumat_hakmilik_permohonan" id="line">
            <display:column title="Bil">${line_rowNum}</display:column>
            <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
            <display:column property="hakmilik.noLot" title="No Lot" />
            <%--<display:column property="hakmilik.luas" title="Luas" />--%>
            <display:column title="Luas"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
            <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
            <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
            <%--display:column title="Kemaskini">
                        <p align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                 onclick="doEdit('${line.hakmilik.idHakmilik}');return false;"
                                 onmouseover="this.style.cursor='pointer';">
                        </p>
            </display:column--%>
          </display:table>  
          <br>
        </div>
        <%--p>
       <label>&nbsp;</label>
      <s:button name="" value="K/kini Hakmilik" onclick="doEdit();" class="longbtn"/>
    </p--%>
      </fieldset>
    </div>
    <c:if test="${fn:length(actionBean.listPemilik) > 0}">
      <br>
      <div class="subtitle ">
        <fieldset class="aras1">
          <legend>Maklumat Pemilik</legend>
          <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.listPemilik}" id="line4" 
                           cellpadding="0" cellspacing="0" pagesize="10" requestURI="/common/maklumat_hakmilik_permohonan">
              <display:column title="Bil" style="text-align: center">${line4_rowNum}</display:column>
              <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" style="text-align: center"/>            
              <display:column title="Nama"> ${line4.nama} </display:column> <%--<display:column property="pihak.nama" title="Nama" />    //yus edit 19/07/2018         --%>
              <display:column title="Syer" style="width:40;text-align: center">
                ${line4.syerPembilang}/${line4.syerPenyebut}
              </display:column>
              <display:column property="jenis.nama" title="Jenis Pihak" style="text-align: center"/>
            </display:table>
            <br>
          </div>
        </fieldset>
      </div>
    </c:if>
    <c:if test="${fn:length(actionBean.listHakmilikPihak) > 0}">
      <br>
      <div class="subtitle ">
        <fieldset class="aras1">
          <legend>Maklumat Pihak Terlibat</legend>
          <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.listHakmilikPihak}" 
                           id="line3" cellpadding="0" cellspacing="0" pagesize="10"
                           requestURI="/common/maklumat_hakmilik_permohonan">
              <display:column title="Bil" style="text-align: center">${line3_rowNum}</display:column>
              <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" style="text-align: center"/>            
              <display:column title="Nama"> ${line3.nama} </display:column>    <%-- <display:column property="pihak.nama" title="Nama"/> //yus edit 04/07/2018 --%>       
              <display:column title="Syer" style="width:40;text-align: center">
                ${line3.syerPembilang}/${line3.syerPenyebut}
              </display:column>
              <display:column property="jenis.nama" title="Jenis Pihak" style="text-align: center"/>
            </display:table>
            <br>
          </div>
        </fieldset>
      </div>
    </c:if>
    <br/>
    <div class="subtitle">
      <fieldset class="aras1">
        <legend>
          Maklumat Urusan Hakmilik
        </legend>
        <div align="center" class="content">
          <display:table class="tablecloth" name="${actionBean.urusanList}"  cellpadding="0" cellspacing="0"
                         id="line2" pagesize="10" requestURI="/common/maklumat_hakmilik_permohonan">             
            <display:column title="Bil">${line2_rowNum}</display:column>
            <display:column property="kodUrusan.nama" title="Urusan"/>
            <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
            <display:column property="idPerserahan" title="ID Perserahan"/>
            <display:column title="Status">
              <c:if test="${line2.aktif eq 'Y'}">Daftar</c:if>
              <c:if test="${line2.aktif eq 'T'}">Batal</c:if>
            </display:column>
            <display:column title="ID Batal">
              <c:if test="${line2.aktif eq 'Y'}">-</c:if>
              <c:if test="${line2.aktif eq 'T'}">${line2.idPermohonanBatal}</c:if>
            </display:column>
            <display:column title="Tarikh Transaksi">
              <c:if test="${line2.aktif eq 'Y'}">
                <fmt:formatDate value="${line2.tarikhDaftar}" pattern="dd/MM/yyyy hh:mm:ss aa"/>
              </c:if>
              <c:if test="${line2.aktif eq 'T'}">
                <fmt:formatDate value="${line2.tarikhBatal}" pattern="dd/MM/yyyy hh:mm:ss aa"/>
              </c:if>
            </display:column>
          </display:table>
          <br>
        </div>
      </fieldset>
    </div>
    <div id="list_pb"/>
  </s:form>
</div>
