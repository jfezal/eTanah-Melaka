<%-- 
    Document   : pembetul_perserahan_swsba
    Created on : Oct 4, 2012, 5:36:06 PM
    Author     : ei
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <title>Pembetulan Perserahan SB/SW/SA</title>
    <link type="text/css" rel="stylesheet"
          href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
    <link type="text/css" rel="stylesheet"
          href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
    <link type="text/css" rel="stylesheet"
          href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
    <script type="text/javascript"
    src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
    <script type="text/javascript"
    src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
    <link type="text/css" rel="stylesheet"
          href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
    <link type="text/css" rel="stylesheet"
          href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
    <script type="text/javascript"
    src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
    <link type="text/css" rel="stylesheet"
          href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
    <script src="<%= request.getContextPath()%>/js/ui.datepicker-ms.js" type="text/javascript"></script>
  </head>
  <body>
    <script type="text/javascript">
      $(document).ready(function() {
        var m = document.getElementById('bt');
        alert(m.value);
      });

      function kemaskiniHakmilik(id, id2) {
        window.open("${pageContext.request.contextPath}/daftar/pembetulanPertanyaanSBSWSA?kemaskiniHakmilik&idHakmilik=" + id + "&idPermohonan=" + id2, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=400");
      }

      function kemaskiniPemberi(id, id2, id3) {
        window.open("${pageContext.request.contextPath}/daftar/pembetulanPertanyaanSBSWSA?kemaskiniPemberi&idPemberi=" + id + "&idPermohonan=" + id2 + "&idPberi=" + id3, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=400");
      }

      function kemaskiniPenerima(id, id2, id3) {
        window.open("${pageContext.request.contextPath}/daftar/pembetulanPertanyaanSBSWSA?kemaskiniPenerima&idPenerima=" + id + "&idPermohonan=" + id2 + "&idPnerima=" + id3, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=400");
      }

      function reload(v) {
        var frm = document.form1;
        var url = "${pageContext.request.contextPath}/daftar/pembetulanPertanyaanSBSWSA?checkPermohonan&idPermohonan=" + v;
        frm.action = url;
        frm.submit();
      }

    </script>

    <s:messages />
    <s:errors />
    <s:useActionBean beanclass="etanah.view.daftar.UtilitiBetulPerserahanSWSBSA" var="betulSWSBSA" />
    <s:form action="/daftar/pembetulanPertanyaanSBSWSA" name="form1">

      <%--<s:form beanclass="etanah.view.daftar.pembetulanPertanyaanSBSWSA">--%>             
      <fieldset class="aras1">                
        <legend>Pembetulan Maklumat Permohonan SW/SB/SA (Revoke)</legend>
        <br>
        <p>
          <label for="idPermohonan">Id Permohonan/ID Perserahan :</label>
          ${actionBean.permohonan.idPermohonan}
          <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
        </p>                    
        <p>
          <label for="kodUrusan">Kod Urusan/Urusan :</label>
          ${actionBean.permohonan.kodUrusan.kod} / ${actionBean.permohonan.kodUrusan.nama}
        </p>                    
        <p>
          <label for="tarikhDaftar">Tarikh Perserahan :</label>
          <fmt:formatDate pattern="dd/MM/yyyy hh:mm aa" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>
        </p>
        <p>
          <label for="penyerah">Penyerah :</label>
          ${actionBean.permohonan.penyerahNama}
        </p>
        <c:if test="${actionBean.permohonan.keputusan.kod ne null}">
          <p>
            <label for="keputusan">Keputusan :</label>
            ${actionBean.permohonan.keputusan.nama}
          </p>
        </c:if>
        <br>
        <p>
          <label for="status">Status :</label>
          <c:if test="${actionBean.permohonan.status eq null}">
            Urusan ini sedang diproses
          </c:if>
          <c:if test="${actionBean.permohonan.status eq 'AK'}">
            Aktif (Urusan ini sedang diproses)
          </c:if>
          <c:if test="${actionBean.permohonan.status eq 'TS'}">
            Menunggu Permohonan/Perserahan sebelum untuk bermula
          </c:if>
          <c:if test="${actionBean.permohonan.status eq 'TA'}">
            Tugasan ini belum diambil oleh sesiapa
          </c:if>
          <c:if test="${actionBean.permohonan.status eq 'SL'}">
            Urusan ini telah selesai diproses. Keputusan telah dikeluarkan
          </c:if>
          <c:if test="${actionBean.permohonan.status eq 'TK'}">
            Urusan telah dibatalkan
          </c:if>
          <c:if test="${actionBean.permohonan.status eq 'SS'}">
            Aktif
          </c:if>            
          &nbsp; 
        </p>
        <c:if test="${actionBean.permohonan.status ne 'TK'}">
          <p>
            <label>&nbsp;</label>                                
            <s:submit name="batalStatus" value="Batal Status" class="btn" onclick="batalStatus(idPermohonan)"/>
          </p>
        </c:if>
        <br><br>
      </fieldset>
      <%--<c:if test="${actionBean.permohonan.keputusan.kod ne 'D'}"> --%>          
      <fieldset class="aras1">
        <c:if test="${fn:length(actionBean.permohonan.senaraiHakmilik) > 0}">                    
          <c:if test="${actionBean.permohonan.keputusan.kod ne 'D'}">
            <c:if test="${actionBean.permohonan.status ne 'TK'}">
              <font color="red">Sila tekan kemaskini untuk mengemaskini data.</font><br><br>
              </c:if>
            </c:if>
          <legend>Maklumat Hakmilik</legend>
          <p align="center">                        
            <display:table class="tablecloth" name="${actionBean.permohonan.senaraiHakmilik}"  
                           cellpadding="0" cellspacing="0" id="line">
              <display:column title="No" sortable="true">${line_rowNum}</display:column>
              <display:column title="ID Hakmilik">${line.hakmilik.idHakmilik}
<!--                                <a href="#" onclick="viewHakmilik('${line.hakmilik.idHakmilik}');return false;">${line.hakmilik.idHakmilik}</a>-->
              </display:column>
              <display:column property="hakmilik.noLot" title="No Lot" />
              <display:column title="Luas">
                <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}
              </display:column>
              <display:column title="Daerah" property="hakmilik.daerah.nama" class="daerah"/>
              <display:column title="Bandar/Pekan/Mukim" property="hakmilik.bandarPekanMukim.nama" />
              <%--<c:if test="${actionBean.permohonan.status ne 'TK'}">
                  <display:column title="Kemaskini"><p align="center">
                      <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                           onclick="kemaskiniHakmilik('${line.hakmilik.idHakmilik}','${actionBean.permohonan.idPermohonan}', this.form); return false;"
                           onmouseover="this.style.cursor='pointer';"></p>
                  </display:column>
              </c:if>--%>
            </display:table>
          </p>
          <br>
          <%--</c:if>--%>
          <c:if test="${fn:length(actionBean.listWakilKuasaPemberi) > 0}">   
            <legend> Maklumat Pemberi</legend>                
            <div class="content" align="center">
              <display:table class="tablecloth" name="${actionBean.listWakilKuasaPemberi}" 
                             pagesize="10" cellpadding="0" cellspacing="0" id="line2">
                <display:column title="Bil" sortable="true">${line2_rowNum}</display:column>
                <display:column property="wakilKuasa.permohonan.idPermohonan" title="ID Perserahan"  class="nama"/>
                <display:column property="pihak.nama" title="Nama"/>
                <display:column property="pihak.jenisPengenalan.nama" title="Jenis Pengenalan"/>
                <display:column property="pihak.noPengenalan" title="No Pengenalan"/>
                <c:if test="${actionBean.permohonan.keputusan.kod ne 'D'}">
                  <c:if test="${actionBean.permohonan.status ne 'TK'}">
                    <display:column title="Kemaskini">  
                      <p align="center">                                    
                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                             onclick="kemaskiniPemberi('${line2.wakilKuasa.permohonan.idPermohonan}', '${actionBean.permohonan.idPermohonan}', '${line2.idPemberi}', this.form);
        return false;" onmouseover="this.style.cursor = 'pointer';">
                      </p>                                
                    </display:column>
                  </c:if>
                </c:if>
              </display:table>
            </div><br>
          </c:if>
          <c:if test="${fn:length(actionBean.listWakilKuasaPenerima) > 0}">
            <legend>Maklumat Penerima</legend>
            <div class="content" align="center">
              <display:table class="tablecloth" name="${actionBean.listWakilKuasaPenerima}" 
                             pagesize="10" cellpadding="0" cellspacing="0" id="line3"
                             requestURI="${pageContext.request.contextPath}/daftar/pembetulan/SuratKuasaWakil?searchWakil" >
                <display:column title="Bil" sortable="true">${line3_rowNum}</display:column>
                <display:column property="wakilKuasa.permohonan.idPermohonan" title="ID Perserahan"  class="nama"/>
                <display:column property="nama" title="Nama"/>
                <display:column property="jenisPengenalan.nama" title="Jenis Pengenalan"/>
                <display:column property="noPengenalan" title="No Pengenalan"/>
                <%--                            <display:column title="Had Kuasa (RM)">
                                               <fmt:formatNumber value="${line.amaunMaksima}" pattern="0.00"/>
                                                </display:column>--%>
                <c:if test="${actionBean.permohonan.keputusan.kod ne 'D'}">
                  <c:if test="${actionBean.permohonan.status ne 'TK'}">
                    <display:column title="Kemaskini">
                      <p align="center">
                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                             onclick="kemaskiniPenerima('${line3.wakilKuasa.permohonan.idPermohonan}', '${actionBean.permohonan.idPermohonan}', '${line3.idPenerima}', this.form);
        return false;"
                             onmouseover="this.style.cursor = 'pointer';">
                      </p>                      
                    </display:column>
                  </c:if>
                </c:if>
                <%--<display:column property="ulasan" title="Ulasan"/>--%>
              </display:table>                            
            </div>                 
          </c:if>
          <c:if test="${actionBean.permohonan.keputusan.kod ne 'D'}">
            <c:if test="${actionBean.permohonan.status ne 'TK'}">
              <p align="center"><br>
                <s:hidden name="permohonan.idPermohonan" id="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                <s:submit name="checkPermohonan" value="Papar Semula" class="btn" />
              </p>
            </c:if>
          </c:if>
        </c:if><br><br>
      </fieldset>
      <%--</c:if>--%>
    </s:form>
  </body>
</html>
