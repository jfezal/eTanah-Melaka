<%-- 
    Document   : maklumat_asas
    Created on : Apr 9, 2010, 3:42:01 PM
    Author     : khairil
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<script type="text/javascript">
  $(document).ready(function() {
    var negeri = '${actionBean.kodNegeri}';
    var notis = '${actionBean.datun}';
    var peranan = '${actionBean.pengguna.perananUtama.kod}';

    if (negeri == 'negeri') {

      if (notis) {

        if ((peranan == '2')|| (peranan == '5')|| (peranan == '6')|| (peranan == '9') || (peranan == '10') || (peranan == '11') || (peranan == '12') || (peranan == '13') ||
                (peranan == '14') || (peranan == '15') || (peranan == '24') || (peranan == '27') || (peranan == '28') ||
                (peranan == '32') || (peranan == '35') || (peranan == '54') || (peranan == '60') || (peranan == '105') ||
                (peranan == 'ptd') || (peranan == 'tptd') || (peranan == 'ptg') || (peranan == 'tptg') || (peranan == '120') ||
                (peranan == '223') || (peranan == '224') || (peranan == '225') || (peranan == '233') || (peranan == '235') ||
                (peranan == 'pptg') || (peranan == '69') || (peranan == '70') || (peranan == '238') || (peranan == '16') ||
                (peranan == '232') || (peranan == '91') || (peranan == '71') || (peranan == '241')) {

        }
        else {

          $('#bil').attr('disabled', 'true');

        }
      }
    }
    if (negeri == 'melaka') {
      //
    }

  });
</script>

<script type="text/javascript">
  function edit1(f, id1) {
    var queryString = $(f).formSerialize()
    window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?cetak&" + queryString + "&idHakmilik=" + id1, "eTanah",
            "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
  }

  function cetakBil(f) {
    var form = $(f).formSerialize();
    var report = null;
    var negeri = '${actionBean.kodNegeri}';




    if (negeri == 'melaka') {
      report = 'HSLResitBilCukaiMLK001.rdf';
    } else {
      report = 'HSLResitBilCukaiNS003.rdf';
    }
    var param = document.getElementById('id_hakmilik');
    var param1 = document.getElementById('no_akaun');

    $.get("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?bilCetakPenyata&idHakmilik=" + param.value,
            function(data) {
              if (data == 'berjaya') {
  <%--window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+form+"&reportName="+report+"&report_p_id_hakmilik="+param.value, "eTanah",
  "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");--%>
                  var url = "reportName=" + report + "%26report_p_id_hakmilik=" + param.value;
                  if (negeri == 'melaka') {
                    url = url + "%26report_p_no_akaun=" + param1.value;
                  }
                  window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                          "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
                }
              });
    }

    function cetakInfoCukai(f, idHakmilik) {
      var form = $(f).formSerialize();
      var negeri = '${actionBean.kodNegeri}';
      var report = null;
      if (negeri == 'melaka') {
        report = "HSLMaklumatCukaiTanah_MLK.rdf";
      } else {
        report = "HSLMaklumatCukaiTanah001.rdf";
      }
  <%--window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+form+"&reportName="+report+"&report_p_id_hakmilik="+idHakmilik, "eTanah",
  "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");--%>

      var param1 = document.getElementById('no_akaun');
      var url = "reportName=" + report + "%26report_p_id_hakmilik=" + idHakmilik;
      if (negeri == 'melaka') {
        url = url + "%26report_p_no_akaun=" + param1.value;
      }
      window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
              "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    }

    function refreshPagesAddress123(f) {
      var queryString = $(f).formSerialize()
      var url = "${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?refreshpage&" + queryString;
      $.get(url,
              function(data) {
                $('#aa').html(data);
              }, 'html');
    }

    function refreshPagesAddress(f) {
      var queryString = $(f).formSerialize()
      var url = "${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?refreshpage&" + queryString;
      $.get(url,
              function(data) {
                $('#aa').html(data);
              }, 'html');
    }

    function refreshT123(f) {
      var queryString = $(f).formSerialize()
      var url = "${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?refreshTambah&" + queryString;
      $.get(url,
              function(data) {
                $('#aa').html(data);
              }, 'html');
    }

    function refreshB123(f) {
      var queryString = $(f).formSerialize()
      var url = "${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?refreshBaru&" + queryString;
      $.get(url,
              function(data) {
                $('#aa').html(data);
              }, 'html');
    }

    function bayar() {
      var url = "${pageContext.request.contextPath}/hasil/kutipan_hasil?search&hakmilik=" + hakmilik;
      $.post(url, q,
              function(data) {
                alert(data);
                $('#ak').html(data);
              }, 'html');
    }
    function tambahPembayar(f, id1, id2) {
      var queryString = $(f).formSerialize()
      window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?tambah&" + queryString + "&idHakmilik=" + id1 + "&noAkaun=" + id2, "eTanah",
              "status=0,toolbar=0,resizable=1,scrollbars=1,location=0,menubar=0,width=1035,height=470");
    }

    function pihakBaru(f, id1, id2) {
      var queryString = $(f).formSerialize()
      window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?baru&" + queryString + "&idHakmilik=" + id1 + "&noAkaun=" + id2, "eTanah",
              "status=0,toolbar=0,resizable=1,scrollbars=1,location=0,menubar=0,width=1035,height=470");
    }
</script>
<s:form beanclass="etanah.view.stripes.hasil.PertanyaanHakmilikActionBean">
  <div id="bayar">
    <div class="subtitle">
      <fieldset class="aras1">
        <s:hidden name="tukarGanti" value="${actionBean.tukarGanti}"/>
        <legend>Maklumat Asas</legend>
        <%--${actionBean.pengguna.perananUtama.kod}--%>
        <c:if test="${actionBean.kodNegeri eq 'melaka'}">
          <p>
            <label>No Akaun :</label>
            ${actionBean.akaun.noAkaun}&nbsp;
            <s:text name="report_p_no_akaun" value="${actionBean.akaun.noAkaun}" id="no_akaun" style="display:none"/>
          </p>
          <p>
            <label>Status Akaun:</label>
            ${actionBean.akaun.status.nama}&nbsp;
          </p>
        </c:if>
        <p><label>Daerah :</label>
          ${actionBean.hakmilik.daerah.nama}&nbsp;
          <s:text name="report_p_id_hakmilik" value="${actionBean.hakmilik.idHakmilik}" id="id_hakmilik" style="display:none"/>
        </p>
        <p>
          <label>Bandar/Pekan/Mukim :</label>
          ${actionBean.hakmilik.bandarPekanMukim.nama}&nbsp;
        </p>
        <p>
          <label>Seksyen :</label>
          <c:if test="${actionBean.hakmilik.seksyen.kod ne null}">
            ${actionBean.hakmilik.seksyen.nama}&nbsp;
          </c:if>
          <c:if test="${actionBean.hakmilik.seksyen.kod eq null}">
            - &nbsp;
          </c:if>
        </p>
        <p>
          <label>Tempat :</label>
          <c:if test="${actionBean.hakmilik.lokasi ne null}">
            ${actionBean.hakmilik.lokasi}&nbsp;
          </c:if>
          <c:if test="${actionBean.hakmilik.lokasi eq null}">
            - &nbsp;
          </c:if>
        </p>
        <p>
          <label>Jenis Hakmilik :</label>
          ${actionBean.hakmilik.kodHakmilik.nama}&nbsp;
        </p>
        <p>
          <label>Jenis Kategori Tanah :</label>
          ${actionBean.hakmilik.kategoriTanah.nama}&nbsp;
        </p>
        <p>
          <label> Kegunaan Tanah :</label>
          ${actionBean.hakmilik.kegunaanTanah.nama}&nbsp;
        </p>
        <p>
          <label>Kod Lot :</label>
          ${actionBean.hakmilik.lot.nama}&nbsp;
        </p>
        <p>
          <label>
              <c:choose>
                  <c:when test="${actionBean.hakmilik.kodHakmilik.kod eq 'GRS'
                          || actionBean.hakmilik.kodHakmilik.kod eq 'PNS'}">No Lot Stratum</c:when>
                  <c:otherwise>No Lot/PT </c:otherwise>
              </c:choose>
              :
          </label>
          ${actionBean.hakmilik.noLot}&nbsp;
        </p>
        <p>
          <label>Tarikh Daftar :</label>
          <fmt:formatDate type="date" pattern="dd/MM/yyyy"
                          value="${actionBean.hakmilik.tarikhDaftar}"/>&nbsp;
        </p>
        <p>
          <label>Tanah Rezab :</label>
          <c:if test="${actionBean.hakmilik.rizab ne null}">
            ${actionBean.hakmilik.rizab.nama}&nbsp;
          </c:if>
          <c:if test="${actionBean.hakmilik.rizab eq null}">
            - &nbsp;
          </c:if>
        </p>
        <p>
          <label>Pihak Berkuasa Tempatan :</label>
          <c:if test="${actionBean.hakmilik.pbt ne null}">
            ${actionBean.hakmilik.pbt.nama}&nbsp;
          </c:if>
          <c:if test="${actionBean.hakmilik.pbt eq null}">
            - &nbsp;
          </c:if>
        </p>
        <p>
          <label>Taraf Pegangan :</label>
          <c:if test="${actionBean.hakmilik.pegangan eq 'P'}">
            Pajakan
          </c:if>
          <c:if test="${actionBean.hakmilik.pegangan eq 'S'}">
            Selama - lama
          </c:if>
          &nbsp;
        </p>
        <p>
          <label>Tempoh :</label>
          <c:if test="${actionBean.hakmilik.tempohPegangan ne null && actionBean.hakmilik.tempohPegangan ne 0 }">
            ${actionBean.hakmilik.tempohPegangan} Tahun&nbsp;
          </c:if>
          <c:if test="${actionBean.hakmilik.tempohPegangan eq null}">
            -
          </c:if>
          <c:if test="${actionBean.hakmilik.tempohPegangan eq 0}">
            -
          </c:if>
        </p>
        <p>
          <label>Tarikh Luput :</label>
          <c:if test="${actionBean.hakmilik.tarikhLuput eq null}">
            Tiada
          </c:if>
          <fmt:formatDate type="date" pattern="dd/MM/yyyy"
                          value="${actionBean.hakmilik.tarikhLuput}"/>&nbsp;
        </p>
        <p>
          <label for="noPu">No Permohonan Ukur :</label>
          <c:if test="${actionBean.hakmilik.noPu ne null}">
            ${actionBean.hakmilik.noPu}&nbsp;
          </c:if>
          <c:if test="${actionBean.hakmilik.noPu eq null}">
            - &nbsp;
          </c:if>
        </p>
        <p>
          <label >Syarat Nyata :</label>
          ${actionBean.hakmilik.syaratNyata.kod}&nbsp;
        </p>
        <p>
          <label>&nbsp;</label>
          <s:textarea name="syaratNyata" rows="5" cols="60" id="syarat" readonly="true">${actionBean.hakmilik.syaratNyata.syarat}</s:textarea>
          </p>
          <p>
            <label >Sekatan :</label>
          ${actionBean.hakmilik.sekatanKepentingan.kod}&nbsp;
        </p>
        <p>
          <label>&nbsp;</label>
          <s:textarea name="sekatan" rows="5" cols="60" id="sekatan" readonly="true">${actionBean.hakmilik.sekatanKepentingan.sekatan}</s:textarea>
          </p>
          <p>
            <label>No Pelan Piawai :</label>
          <c:if test="${actionBean.hakmilik.noLitho ne null}">
            ${actionBean.hakmilik.noLitho}&nbsp;
          </c:if>
          <c:if test="${actionBean.hakmilik.noLitho eq null}">
            - &nbsp;
          </c:if>
        </p>
        <p>
          <label>
                <c:choose>
                    <c:when test="${actionBean.hakmilik.kodHakmilik.kod eq 'GRS'
                                    || actionBean.hakmilik.kodHakmilik.kod eq 'PNS'}">No Pelan Stratum Diperakui</c:when>
                    <c:otherwise>No Pelan Diperakui</c:otherwise>
                </c:choose>
               :
          </label>
          <c:if test="${actionBean.hakmilik.noPelan ne null}">
            ${actionBean.hakmilik.noPelan}&nbsp;
          </c:if>
          <c:if test="${actionBean.hakmilik.noPelan eq null}">
            - &nbsp;
          </c:if>
        </p>
        <p>
          <label >
              <c:choose>
                    <c:when test="${actionBean.hakmilik.kodHakmilik.kod eq 'GRS'
                                    || actionBean.hakmilik.kodHakmilik.kod eq 'PNS'}">Isipadu Lot Stratum</c:when>
                  <c:otherwise>Keluasan</c:otherwise>
              </c:choose>                
              :</label>
          <s:format formatPattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp; ${actionBean.hakmilik.kodUnitLuas.nama}
        </p>
        <c:if test="${actionBean.hakmilik.kodHakmilik.kod eq 'GRS'
                      || actionBean.hakmilik.kodHakmilik.kod eq 'PNS'}">
            <p>
                <label>Kedalaman :</label>
                <s:format formatPattern="#,##0.0000" value="${actionBean.hakmilik.kedalamanTanah}"/>&nbsp;
                <c:if test="${actionBean.hakmilik.kedalamanTanahUOM ne null}">
                    ${actionBean.hakmilik.kedalamanTanahUOM.nama}
                </c:if>
            </p>
        </c:if>
        <p>
          <label >Cukai Asal (RM)  :</label>

          <fmt:formatNumber value="${actionBean.hakmilik.cukai}" pattern="0.00"/>&nbsp;
        </p>
        <p>
          <label >Cukai Tanah Tahunan (RM)  :</label>
          <fmt:formatNumber value="${actionBean.hakmilik.cukaiSebenar}" pattern="0.00"/>&nbsp;
        </p>
        <p>
          <label>No Versi DHKE :</label>
          ${actionBean.hakmilik.noVersiDhke}&nbsp;
        </p>
        <p>
          <label>No Versi DHDE :</label>
          ${actionBean.hakmilik.noVersiDhde}&nbsp;
        </p>
        <c:if test="${actionBean.hakmilik.noVersiDhde eq '0' || actionBean.hakmilik.noVersiDhde eq '1'}">
          <p>
            <label>Tarikh Tukar Ganti :</label>
            <fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${actionBean.hakmilikLama.tarikhJilid16}"/>&nbsp;            
          </p>
          <p>
            <label>Pegawai Tukar Ganti :</label>
            ${actionBean.hakmilikLama.pegawaiJilid16}&nbsp;
          </p>
        </c:if>
        <p>
          <label>Tarikh Daftar Asal :</label>
          <c:if test="${actionBean.hakmilik.tarikhDaftarAsal ne ''}">
            <fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${actionBean.hakmilik.tarikhDaftarAsal}"/>&nbsp;
          </c:if>
          <c:if test="${actionBean.hakmilik.tarikhDaftarAsal eq ''}">
            - &nbsp;
          </c:if>
        </p>
        <c:if test="${actionBean.hakmilik.kodStatusHakmilik.kod eq 'B'}" >
          <p>
            <label>Tarikh Batal :</label>
            <%--${actionBean.hakmilik.tarikhBatal}--%>
            <fmt:formatDate type="date" pattern="dd/MM/yyyy"
                            value="${actionBean.hakmilik.tarikhBatal}"/>&nbsp;
          </p>
        </c:if>
        <p>
          <label>No Rujukan Fail :</label>
          ${actionBean.hakmilik.noFail}&nbsp;
        </p><p>
          <label>Agensi:</label>
          ${actionBean.agensiHakmilik.namaAgensi}&nbsp;
        </p>
        <p>
          <label>Kegunaan Tanah(Projek Persekutuan):</label>
          ${actionBean.agensiHakmilik.kegunaanTanah}&nbsp;
        </p><br>
      </fieldset>

    </div>
    <br>
    <div class="subtitle">
      <fieldset class="aras1">
        <legend>
          Maklumat Pembayar
        </legend>
        <p><label>Nama :</label>
          ${actionBean.pihak.nama}&nbsp;
          <%--                    <a href="#" onclick="editMaklumat(this.form, '${actionBean.pihak.idPihak}','${actionBean.hakmilik.idHakmilik}','${actionBean.akaun.noAkaun}');">${actionBean.pihak.nama}&nbsp;</a>&nbsp;
          --%>
          <c:if test="${actionBean.pengguna.perananUtama.kod eq '2' or actionBean.pengguna.perananUtama.kod eq '5' or actionBean.pengguna.perananUtama.kod eq '116' or actionBean.pengguna.perananUtama.kod eq '58'}">
            <c:if test="${actionBean.pengguna.kodCawangan.kod eq '00' or actionBean.pengguna.kodCawangan.kod eq '04' or actionBean.pengguna.kodCawangan.kod eq actionBean.hakmilik.daerah.kod}">
              <a onmouseover="this.style.cursor = 'pointer';" onclick="tambahPembayar(this.form, '${actionBean.hakmilik.idHakmilik}', '${actionBean.akaun.noAkaun}');">
                <img alt="Sila Klik Untuk Pilih Nama Pembayar Yang Baru" src='${pageContext.request.contextPath}/pub/images/edit.gif' />&nbsp;
              </a>&nbsp;&nbsp;
              <a onmouseover="this.style.cursor = 'pointer';" onclick="pihakBaru(this.form, '${actionBean.hakmilik.idHakmilik}', '${actionBean.akaun.noAkaun}');">
                <img alt="Sila Klik Untuk Tambah Pembayar Yang Baru" src='${pageContext.request.contextPath}/pub/images/tambah.png' />&nbsp;
              </a>&nbsp;
            </c:if>
          </c:if>
        </p>
        <p>
          <label>No Pengenalan :</label>
          ${actionBean.pihak.noPengenalan}&nbsp;
        </p>
        <p>
          <label>Alamat Tetap :</label>
          ${actionBean.pihak.alamat1}&nbsp;
        </p>
        <c:if test="${actionBean.pihak.alamat2 ne null}">
          <p>
            <label> &nbsp;</label>
            ${actionBean.pihak.alamat2}&nbsp;
          </p>
        </c:if>
        <c:if test="${actionBean.pihak.alamat3 ne null}">
          <p>
            <label> &nbsp;</label>
            ${actionBean.pihak.alamat3}&nbsp;
          </p>
        </c:if>
        <p>
          <label>Bandar :</label>
          <c:if test="${actionBean.pihak.alamat4 ne null}">
            ${actionBean.pihak.alamat4}&nbsp;
          </c:if>
          <c:if test="${actionBean.pihak.alamat4 eq null}">
            -
          </c:if>
        </p>
        <p>
          <label>Poskod :</label>
          <c:if test="${actionBean.pihak.poskod ne null}">
            ${actionBean.pihak.poskod}&nbsp;
          </c:if>
          <c:if test="${actionBean.pihak.poskod eq null}">
            -
          </c:if>
        </p>
        <p>
          <label>Negeri :</label>
          <c:if test="${actionBean.pihak.negeri.nama ne null}">
            ${actionBean.pihak.negeri.nama}&nbsp;
          </c:if>
          <c:if test="${actionBean.pihak.negeri.nama eq null}">
            -
          </c:if>
        </p>
        <br>
        <p>
          <label>Alamat Surat Menyurat :</label>
          <c:if test="${actionBean.pihak.suratAlamat1 ne null}">
            ${actionBean.pihak.suratAlamat1}&nbsp;
          </c:if>
          <c:if test="${actionBean.pihak.suratAlamat1 eq null}">
            -
          </c:if>


          <c:if test="${actionBean.pengguna.perananUtama.kod eq '2' or actionBean.pengguna.perananUtama.kod eq '5'  or actionBean.pengguna.perananUtama.kod eq '116'}">
            <%--<c:if test="${actionBean.pengguna.kodCawangan.kod eq '00' or actionBean.pengguna.kodCawangan.kod eq actionBean.hakmilik.daerah.kod}">--%>
              <a id="" onclick="editMaklumat(this.form, '${actionBean.pihak.idPihak}', '${actionBean.hakmilik.idHakmilik}', '${actionBean.akaun.noAkaun}');" onmouseover="this.style.cursor = 'pointer';">
                <img alt="Sila Klik Untuk Kemaskini Alamat Pembayar" src='${pageContext.request.contextPath}/pub/images/edit.gif' />
              </a>&nbsp;
            <%--</c:if>--%>
          </c:if>
        </p>
        <c:if test="${actionBean.pihak.suratAlamat2 ne null}">
          <p>
            <label> &nbsp;</label>
            ${actionBean.pihak.suratAlamat2}&nbsp;
          </p>
        </c:if>
        <c:if test="${actionBean.pihak.suratAlamat3 ne null}">
          <p>
            <label> &nbsp;</label>
            ${actionBean.pihak.suratAlamat3}&nbsp;
          </p>
        </c:if>
        <p>
          <label>Bandar :</label>
          <c:if test="${actionBean.pihak.suratAlamat4 ne null}">
            ${actionBean.pihak.suratAlamat4}&nbsp;
          </c:if>
          <c:if test="${actionBean.pihak.suratAlamat4 eq null}">
            -
          </c:if>
        </p>
        <p>
          <label>Poskod :</label>
          <c:if test="${actionBean.pihak.suratPoskod ne null}">
            ${actionBean.pihak.suratPoskod}&nbsp;
          </c:if>
          <c:if test="${actionBean.pihak.suratPoskod eq null}">
            -
          </c:if>
        </p>
        <p>
          <label>Negeri :</label>
          <c:if test="${actionBean.pihak.suratNegeri.nama ne null}">
            ${actionBean.pihak.suratNegeri.nama}&nbsp;
          </c:if>
          <c:if test="${actionBean.pihak.suratNegeri.nama eq null}">
            -
          </c:if>
        </p>
        <br>
        <p>
          <label>Nombor Telefon :</label>
          <c:if test="${actionBean.pihak.noTelefon1 ne null}">
            ${actionBean.pihak.noTelefon1}&nbsp;
          </c:if>
          <c:if test="${actionBean.pihak.noTelefon1 eq null}">
            - &nbsp;
          </c:if>
        </p>
        <p>
          <label>Nombor Telefon Bimbit :</label>
          <c:if test="${actionBean.pihak.noTelefonBimbit ne null}">
            ${actionBean.pihak.noTelefonBimbit}&nbsp;
          </c:if>
          <c:if test="${actionBean.pihak.noTelefonBimbit eq null}">
            - &nbsp;
          </c:if>
        </p>
        <p>
          <label>Email :</label>
          <c:if test="${actionBean.pihak.email ne null}">
            ${actionBean.pihak.email}&nbsp;
          </c:if>
          <c:if test="${actionBean.pihak.email eq null}">
            - &nbsp;
          </c:if>
        </p>
        <br>
      </fieldset>
    </div>
    <br>

    <div class="subtitle">
      <fieldset class="aras1">
        <legend>
          Perihal Cukai Semasa
        </legend>
        <c:if test="${actionBean.kodNegeri ne 'melaka'}">
          <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.transList}"
                           pagesize="10" cellpadding="0" cellspacing="0"
                           requestURI="/common/carian_hakmilik" id="line3">
              <%--<display:table class="tablecloth"  pagesize="10" cellpadding="0" cellspacing="0" requestURI="/common/carian_hakmilik" id="line2">--%>
              <display:column title="Tarikh Transaksi" >
                <c:if test="${line3.dokumenKewangan.noRujukanManual ne null}">
                  <fmt:formatDate value="${line3.dokumenKewangan.tarikhTransaksi}" pattern="dd/MM/yyyy hh:mm aa"/>
                </c:if>
                <c:if test="${line3.dokumenKewangan.noRujukanManual eq null}">
                  <fmt:formatDate value="${line3.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm aa"/>
                </c:if>
              </display:column>
              <display:column  title="Transaksi Penyata">${line3.kodTransaksi.nama}</display:column>
              <display:column  title="No Resit"  >
                <c:if test="${line3.dokumenKewangan.noRujukanManual ne null}"><%-- or line2.dokumenKewangan.noRujukanManual ne ''}">--%>
                  <a href="#" onclick="edit3(this.form, '${line3.dokumenKewangan.idDokumenKewangan}');">
                    ${line3.dokumenKewangan.noRujukanManual}&nbsp;
                  </a>
                </c:if>
                <c:if test="${line3.dokumenKewangan.noRujukanManual eq null}"><%-- or line2.dokumenKewangan.noRujukanManual eq ''}">--%>
                  <c:choose>
                    <c:when test="${line3.dokumenKewangan.noRujukan ne null}">
                      <a href="#" onclick="edit3(this.form, '${line3.dokumenKewangan.idDokumenKewangan}');">
                        ${line3.dokumenKewangan.noRujukan}&nbsp;
                      </a>
                    </c:when>
                    <c:otherwise>
                      <a href="#" onclick="edit3(this.form, '${line3.dokumenKewangan.idDokumenKewangan}');">
                        ${line3.dokumenKewangan.idDokumenKewangan}&nbsp;
                      </a>
                    </c:otherwise>
                  </c:choose>
                </c:if>
              </display:column>
              <display:column property="untukTahun" title="Tahun" />
              <display:column title="Akaun Debit (RM)" style="text-align:right">
                <c:if test="${line3.status.kod eq 'A' and line3.kodTransaksi.kod ne '99000'
                              and line3.kodTransaksi.kod ne '99001'
                              and line3.kodTransaksi.kod ne '99002'
                              and line3.kodTransaksi.kod ne '99003'
                              and line3.kodTransaksi.kod ne '99030' }">
                  <fmt:formatNumber value="${line3.amaun}" pattern="#,##0.00"/>
                </c:if>
                <%--<c:if test="${line2.status.kod eq 'B'}">
                    <fmt:formatNumber value="${line2.amaun}" pattern="#,##0.00"/>
                </c:if>--%>
              </display:column>
              <display:column title="Akaun Kredit (RM)" style="text-align:right">
                <c:if test="${line3.status.kod ne 'A'}"><%-- and line2.status.kod ne 'B'}">--%>
                  <fmt:formatNumber value="${line3.amaun}" pattern="#,##0.00"/>
                </c:if>
                <c:if test="${line3.status.kod eq 'A'
                              and line3.kodTransaksi.kod eq '99002'
                              or line3.kodTransaksi.kod eq '99000'
                              or line3.kodTransaksi.kod eq '99001'
                              or line3.kodTransaksi.kod eq '99003'
                              or line3.kodTransaksi.kod eq '99030'}">
                  <fmt:formatNumber value="${line3.amaun}" pattern="#,##0.00"/>
                </c:if>
              </display:column>
              <display:column property="status.nama" title="Status"/>
              <display:column title="Tarikh Batal" >
                <c:if test="${line3.status.kod eq 'B'}">
                  <fmt:formatDate value="${line3.dokumenKewangan.tarikhBatal}" pattern="dd/MM/yyyy hh:mm aa"/>
                </c:if>
              </display:column>
              <%-- <display:column title="Dimasuk Oleh" >
                   <c:out value="${line2.infoAudit.dimasukOleh.nama}"/>
               </display:column>--%>
            </display:table>
          </div>
        </c:if>

        <c:if test="${actionBean.kodNegeri eq 'melaka'}">
          <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.transList}" pagesize="10" cellpadding="0"
                           cellspacing="0" requestURI="/common/carian_hakmilik" id="line3">
              <display:column title="Bil" sortable="true"> <div align="center">${line3_rowNum}</div></display:column>
              <display:column title="Tarikh Transaksi" >
                <c:if test="${line3.dokumenKewangan.noRujukanManual ne null}">
                  <fmt:formatDate value="${line3.dokumenKewangan.tarikhTransaksi}" pattern="dd/MM/yyyy hh:mm aa"/>
                </c:if>
                <c:if test="${line3.dokumenKewangan.noRujukanManual eq null}">
                  <fmt:formatDate value="${line3.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm aa"/>
                </c:if>
              </display:column>
              <display:column  title="Jenis Transaksi" >
                ${line3.kodTransaksi.kod} - ${line3.kodTransaksi.nama}</display:column>
              <c:if test="${line3.dokumenKewangan.noRujukanManual ne null}"><%-- or line2.dokumenKewangan.noRujukanManual ne ''}">--%>
                <display:column  title="No Resit"  >
                  <a href="#" onclick="edit3(this.form, '${line3.dokumenKewangan.idDokumenKewangan}');">
                    ${line3.dokumenKewangan.noRujukanManual}&nbsp;
                  </a>
                </display:column>
              </c:if>
              <c:if test="${line3.dokumenKewangan.noRujukanManual eq null}"><%-- or line2.dokumenKewangan.noRujukanManual eq ''}">--%>
                <display:column  title="No Resit"  >
                  <a href="#" onclick="edit3(this.form, '${line3.dokumenKewangan.idDokumenKewangan}');">
                    ${line3.dokumenKewangan.idDokumenKewangan}&nbsp;
                  </a>
                </display:column>
              </c:if>
              <display:column title="Cara Bayaran" >
                <c:forEach items="${line3.dokumenKewangan.senaraiBayaran}" var="senarai" >
                  <c:out value="${senarai.caraBayaran.kodCaraBayaran.nama}" /><br>
                </c:forEach>
              </display:column>
              <display:column title="Bank / Agensi Pembayaran">
                <c:forEach items="${line3.dokumenKewangan.senaraiBayaran}" var="senarai">
                  <c:out value="${senarai.caraBayaran.bank.nama}" /><br>
                </c:forEach>
              </display:column>
              <display:column title="No Rujukan">
                <c:forEach items="${line3.dokumenKewangan.senaraiBayaran}" var="senarai" >
                  <c:out value="${senarai.caraBayaran.noRujukan}" /><br>
                </c:forEach>
              </display:column>
              <%--<display:column property="infoAudit.tarikhMasuk" title="Tarikh Transaksi" class="cawangan${line_rowNum}" format="{0,date,dd/MM/yyyy,hh:mm aa}"/>--%>
              <display:column property="untukTahun" title="Tahun" />
              <display:column property="status.nama" title="Status"/>
              <display:column title="Akaun Debit (RM)" style="text-align:right">
                <c:if test="${line3.status.kod eq 'A' and line3.kodTransaksi.kod ne '99000'
                              and line3.kodTransaksi.kod ne '99001'and line3.kodTransaksi.kod ne '99002'
                              and line3.kodTransaksi.kod ne '99051'and line3.kodTransaksi.kod ne '99050'
                              and line3.kodTransaksi.kod ne '99003'and line3.kodTransaksi.kod ne '99030' 
                              and line3.kodTransaksi.kod ne '99004' }">
                  <fmt:formatNumber value="${line3.amaun}" pattern="#,##0.00"/>
                </c:if>
                <c:if test="${line3.status.kod ne 'A'}">
                  -
                </c:if>
              </display:column>
              <display:column title="Akaun Kredit (RM)" style="text-align:right">
                <c:if test="${line3.status.kod ne 'A'}">
                  <fmt:formatNumber value="${line3.amaun}" pattern="#,##0.00"/>
                </c:if>
                <c:if test="${line3.status.kod eq 'A' and line3.kodTransaksi.kod eq '99002'
                              or line3.kodTransaksi.kod eq '99000'or line3.kodTransaksi.kod eq '99001'
                              or line3.kodTransaksi.kod eq '99051'or line3.kodTransaksi.kod eq '99050'
                              or line3.kodTransaksi.kod eq '99003'or line3.kodTransaksi.kod eq '99030'
                              or line3.kodTransaksi.kod eq '99004'}">
                  <fmt:formatNumber value="${line3.amaun}" pattern="#,##0.00"/>
                </c:if>
              </display:column>
              <display:column title="Dimasuk Oleh" >
                <c:out value="${line3.infoAudit.dimasukOleh.nama}"/>
              </display:column>
            </display:table>
          </div>
        </c:if>
      </fieldset>
    </div>
    <br>
    <%--start sementara sahaja 03062011
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Jumlah Perlu Dibayar</legend>
        <table align="center">
            <tr>
                <td width="70">&nbsp;</td>
                <td width="2">&nbsp;</td>
                <td><font size="2">&nbsp;</font></td>
                <td width="60">&nbsp;</td>
                <td width="170"><font color="#003194" size="2px" style="Tahoma"><b>Jumlah Keseluruhan </b></font></td>
                <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                <td><font size="2">RM<fmt:formatNumber value="${actionBean.hakmilik.akaunCukai.baki}" pattern="#,##0.00"/></font></td>
            </tr>
        </table>
        </fieldset>
    </div>
            <br>
     end sementara sahaja 03062011--%>
    <c:if test="${actionBean.jum gt '0'}">
      <%--<c:if test="${actionBean.hakmilik.akaunCukai.baki gt '0'}">--%>
      <div class="subtitle">
        <fieldset class="aras1">
          <legend>
            Maklumat Denda & Notis
          </legend>
          <div align="center">
            <table align="center">
              <tr>
                <td width="70"><font color="#003194" size="2px" style="Tahoma"><b>Jumlah </b></font></td>
                <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                <td><font size="2">RM<fmt:formatNumber value="${actionBean.jum}" pattern="0.00"/></font></td>
                <td width="60">&nbsp;</td>
                <td width="170"><font color="#003194" size="2px" style="Tahoma"><b>Sebelum 1 Jun </b></font></td>
                <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                <td><font size="2">RM<fmt:formatNumber value="${actionBean.jum}" pattern="0.00"/></font></td>
              </tr>
              <tr>
                <td width="70"><font color="#003194" size="2px" style="Tahoma"><b>Denda</b></font></td>
                <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                <td><font size="2">RM<fmt:formatNumber value="${actionBean.denda}" pattern="0.00"/></font></td>
                <td width="60">&nbsp;</td>
                <td width="170"><font color="#003194" size="2px" style="Tahoma"><b>Selepas 31 Mei </b></font></td>
                <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                <td><font size="2">RM<fmt:formatNumber value="${actionBean.jumDenda}" pattern="0.00"/></font></td>
              </tr>
              <tr>
                <td width="70"><font color="#003194" size="2px" style="Tahoma"><b>Notis 6A </b></font></td>
                <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                <td><font size="2">RM<fmt:formatNumber value="${actionBean.z}" pattern="0.00"/></font></td>
                <td width="60">&nbsp;</td>
                <td width="170"><font color="#003194" size="2px" style="Tahoma"><b>Jika Notis 6A disampaikan </b></font></td>
                <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                <td><font size="2">RM<fmt:formatNumber value="${actionBean.notis}" pattern="0.00"/></font></td>
              </tr>
            </table>
          </div>
        </fieldset>
      </div>
    </c:if>
  </div>
</s:form>

<div align="center">
  <table border="0" width="97%">
    <tr>
      <td align="right" width="64%">
        <c:if test="${actionBean.pengguna.perananUtama.kod eq '2' or actionBean.pengguna.perananUtama.kod eq '5'}">
          <s:form beanclass="etanah.view.stripes.hasil.KutipanHasilActionBean">
            <s:text name="hakmilik.idHakmilik" value="${actionBean.hakmilik.idHakmilik}" style="display:none;"/>
            <c:if test="${!datun}">
              <%--<s:submit name="search" value="Bayar" class="btn"/>  08/04/2014 --%>
            </c:if>
          </s:form>
        </c:if>
      </td>
      <td align="left" width="33%">
        <%--<s:form beanclass="etanah.view.stripes.hasil.PertanyaanHakmilikActionBean">--%>
        <s:form beanclass="etanah.view.stripes.common.CarianHakmilik">
           <c:if test="${actionBean.hakmilik.kodStatusHakmilik.kod ne 'B'}">
           <c:if test="${actionBean.akaun.status.kod ne 'B'}">
              <s:button name="" onclick="cetakBil(this.form)" value="Cetak Bil" id="bil" class="btn" style="display:${actionBean.btn2}"/>
            </c:if> 
               </c:if>
         
          <%--<c:if test="${actionBean.kodNegeri ne 'melaka'}">--%>
          <s:button name="" onclick="cetakInfoCukai(this.form,'${actionBean.hakmilik.idHakmilik}');" value="Cetak Info Cukai" class="longbtn" style="display:${actionBean.btn2}"/>
          <%--</c:if>--%>
          <s:submit name="back" value="Kembali" class="btn" id="btn4"/>
          
        </s:form>
      </td>
    </tr>
  </table>
</div>
