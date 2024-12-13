<html>
  <head>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
    <%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Kemasukan Terperinci Hakmilik</title>
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
    <title>Paparan Maklumat Pemberi Penerima</title>

  </head>
  <body>
    <script type="text/javascript">

      function viewHakmilik(id) {
        window.open("${pageContext.request.contextPath}/daftar/kesinambungan?viewhakmilikDetail&idHakmilik=" + id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
      }


    </script>
    <s:messages />
    <s:errors />

    <s:form action="/daftar/pertanyaanSBSWSA" >

      <fieldset class="aras1">

        <legend>Maklumat Permohonan SW/SB/SA</legend>
        <br>
        <p><label for="idPermohonan">Id Permohonan/ID Perserahan :</label>
          ${actionBean.permohonan.idPermohonan}&nbsp;
        </p>

        <p><label for="kodUrusan">Kod Urusan/Urusan :</label>
          ${actionBean.permohonan.kodUrusan.kod} -
          ${actionBean.permohonan.kodUrusan.nama}&nbsp;
        </p>

        <p><label for="tarikhDaftar">Tarikh Perserahan/Permohonan :</label>
          <fmt:formatDate pattern="dd/MM/yyyy hh:mm aa" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>&nbsp;
        </p>
        
        <p><label for="penyerah">Penyerah :</label>
          ${actionBean.permohonan.penyerahNama}&nbsp;
        </p>

        <p><label for="status">Status :</label>
          <c:if test="${actionBean.permohonan.status eq null}">
            Urusan ini sedang diproses
          </c:if>
          <c:if test="${actionBean.permohonan.status eq 'AK'}">
            Aktif
          </c:if>
          <c:if test="${actionBean.permohonan.status eq 'TS'}">
            Menunggu Permohonan/Perserahan sebelum untuk bermula
          </c:if>
          <c:if test="${actionBean.permohonan.status eq 'TA'}">
            Tugasan ini belum diambil oleh sesiapa
          </c:if>
          <c:if test="${actionBean.permohonan.status eq 'SL'}">
            <c:choose>
              <c:when test ="${actionBean.permohonan.kodUrusan.kodPerserahan.kod eq 'SB'}">
                Awalan
              </c:when>
              <c:otherwise>
                Urusan ini telah selesai diproses. Keputusan telah dikeluarkan
              </c:otherwise>
            </c:choose>
          </c:if>
          <c:if test="${actionBean.permohonan.status eq 'TK'}">
            Batal
          </c:if>
        </p>
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SB'}">
          <br>
          <p>
            <label for="status">Jenis Kebenaran :</label>
            ${actionBean.wkuasaSB.jenisSuratKuasa.nama}    
            &nbsp;
          </p> 
          <p>
            <c:if test="${actionBean.wkuasaSB.kuasaLain1 ne null}">
            <label for="status">Lain - lain :</label>
            ${actionBean.wkuasaSB.kuasaLain1}
            </c:if>
            &nbsp;
          </p>
        </c:if>
        <br>
        <c:if test="${fn:length(actionBean.permohonan.senaraiHakmilik) > 0}">
          <legend>Maklumat Hakmilik</legend>
          <p align="center">
            <display:table class="tablecloth" name="${actionBean.permohonan.senaraiHakmilik}"  cellpadding="0" cellspacing="0" id="line">
              <display:column title="No" sortable="true">${line_rowNum}</display:column>
              <display:column   title="ID Hakmilik" >
                <a href="#" onclick="viewHakmilik('${line.hakmilik.idHakmilik}');
        return false;">${line.hakmilik.idHakmilik}</a>
              </display:column>
              <display:column property="hakmilik.noLot" title="No Lot" />
              <display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
              <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
              <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
            </display:table><br>
          </p>
        </c:if>
       <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'SA'}">
        <c:if test="${fn:length(actionBean.listWakilKuasaPemberi) > 0}">
          <legend>Maklumat Pemberi</legend>
          <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.listWakilKuasaPemberi}" pagesize="10" cellpadding="0" cellspacing="0"
                           id="line">
              <display:column title="Bil" sortable="true">${line_rowNum}
              </display:column>
              <display:column property="wakilKuasa.permohonan.idPermohonan" title="ID Perserahan"  class="nama"/>
              <display:column title="Nama">
                  <c:if test="${empty line.nama}">
                    ${line.pihak.nama}
                </c:if>
                <c:if test="${!empty line.nama}">
                    ${line.nama}
                </c:if>
              </display:column>
              <display:column title="Jenis Pengenalan">
                   <c:if test="${empty line.pihak.jenisPengenalan.nama}">
                    ${line.kodPengenalan}
                </c:if>
                <c:if test="${!empty line.pihak.jenisPengenalan.nama}">
                    ${line.pihak.jenisPengenalan.nama}
                </c:if>
              </display:column>
              <display:column title="No Pengenalan">
                  <c:if test="${empty line.pihak.noPengenalan}">
                    ${line.noPengenalan}
                </c:if>
                <c:if test="${!empty line.pihak.noPengenalan}">
                    ${line.pihak.noPengenalan}
                </c:if>
              </display:column>
              <display:column title="Status">
                <c:choose>
                  <c:when test="${fn:contains(line.wakilKuasa.aktif,'Y') || fn:contains(line.wakilKuasa.aktif,'M')}"> 
                    Aktif
                  </c:when>
                  <c:otherwise>
                    Tidak Aktif
                  </c:otherwise>
                </c:choose>
              </display:column>
            </display:table><br>
          </div>
        </c:if> 
        <c:if test="${fn:length(actionBean.listWakilKuasaPenerima) > 0}">
          <legend>Maklumat Penerima</legend>  
          <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.listWakilKuasaPenerima}" pagesize="10" cellpadding="0" cellspacing="0"
                           requestURI="${pageContext.request.contextPath}/daftar/pertanyaanSBSWSA" id="line">
              <display:column title="Bil" sortable="true">${line_rowNum}
              </display:column>
              <display:column property="wakilKuasa.permohonan.idPermohonan" title="ID Perserahan"  class="nama"/>
              <display:column property="nama" title="Nama"/>
              <display:column property="jenisPengenalan.nama" title="Jenis Pengenalan"/>
              <display:column property="noPengenalan" title="No Pengenalan"/>
              <display:column title="Had Kuasa (RM)">
                <c:if test="${line.amaunMaksima != null}">
                  <fmt:formatNumber value="${line.amaunMaksima}" pattern="#,##0.00"/>
                </c:if>
                <c:if test="${line.amaunMaksima == null ||line.amaunMaksima == '0' }">
                  0.00
                </c:if>                                
              </display:column>
              <display:column title="Status">
                <c:choose>
                  <c:when test="${fn:contains(line.wakilKuasa.aktif,'Y') 
                                  || fn:contains(line.wakilKuasa.aktif,'M')}">  <!-- migrate dari SPTB-->
                    Aktif
                  </c:when>
                  <c:otherwise>
                    Tidak Aktif
                  </c:otherwise>
                </c:choose>
              </display:column>
              <%--<display:column property="ulasan" title="Ulasan"/>--%>
            </display:table><br>
          </div>
        </c:if>
      </c:if>
          <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SA'}">
              <c:if test="${fn:length(actionBean.listWakilKuasaPenerima) > 0}">
          <legend>Bagi Pihak</legend>  
          <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.listWakilKuasaPenerima}" pagesize="10" cellpadding="0" cellspacing="0"
                           requestURI="${pageContext.request.contextPath}/daftar/pertanyaanSBSWSA" id="line">
              <display:column title="Bil" sortable="true">${line_rowNum}
              </display:column>
              <display:column property="wakilKuasa.permohonan.idPermohonan" title="ID Perserahan"  class="nama"/>
              <display:column property="nama" title="Nama"/>
              <display:column property="jenisPengenalan.nama" title="Jenis Pengenalan"/>
              <display:column property="noPengenalan" title="No Pengenalan"/>
              <display:column title="Had Kuasa (RM)">
                <c:if test="${line.amaunMaksima != null}">
                  <fmt:formatNumber value="${line.amaunMaksima}" pattern="#,##0.00"/>
                </c:if>
                <c:if test="${line.amaunMaksima == null ||line.amaunMaksima == '0' }">
                  0.00
                </c:if>                                
              </display:column>
              <display:column title="Status">
                <c:choose>
                  <c:when test="${fn:contains(line.wakilKuasa.aktif,'Y') || fn:contains(line.wakilKuasa.aktif,'M')}"> 
                   Aktif
                  </c:when>
                  <c:otherwise>
                    Tidak Aktif
                  </c:otherwise>
                </c:choose>
              </display:column>
              <%--<display:column property="ulasan" title="Ulasan"/>--%>
            </display:table><br>
          </div>
        </c:if>
          <c:if test="${fn:length(actionBean.listWakilKuasaPemberi) > 0}">
          <legend>Pemegang Amanah</legend>
          <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.listWakilKuasaPemberi}" pagesize="10" cellpadding="0" cellspacing="0"
                           id="line">
              <display:column title="Bil" sortable="true">${line_rowNum}
              </display:column>
              <display:column property="wakilKuasa.permohonan.idPermohonan" title="ID Perserahan"  class="nama"/>
              <display:column title="Nama">
                  <c:if test="${empty line.nama}">
                    ${line.pihak.nama}
                </c:if>
                <c:if test="${!empty line.nama}">
                    ${line.nama}
                </c:if>
              </display:column>
              <display:column title="Jenis Pengenalan">
                   <c:if test="${empty line.pihak.jenisPengenalan.nama}">
                    ${line.kodPengenalan}
                </c:if>
                <c:if test="${!empty line.pihak.jenisPengenalan.nama}">
                    ${line.pihak.jenisPengenalan.nama}
                </c:if>
              </display:column>
              <display:column title="No Pengenalan">
                  <c:if test="${empty line.pihak.noPengenalan}">
                    ${line.noPengenalan}
                </c:if>
                <c:if test="${!empty line.pihak.noPengenalan}">
                    ${line.pihak.noPengenalan}
                </c:if>
              </display:column>
              <display:column title="Status">
                <c:choose>
                  <c:when test="${fn:contains(line.wakilKuasa.aktif,'Y') || fn:contains(line.wakilKuasa.aktif,'M')}"> 
                   Aktif
                  </c:when>
                  <c:otherwise>
                    Tidak Aktif
                  </c:otherwise>
                </c:choose>
              </display:column>
            </display:table><br>
          </div>
        </c:if> 
          </c:if>
        <p>
            <label>&nbsp;</label>  
            <s:button name="tutup" value="Tutup" class="btn" onclick="self.close();"/>&nbsp;
        </p>
      </s:form>
    </fieldset>
  </body>
</html>