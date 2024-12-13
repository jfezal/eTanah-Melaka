<%-- 
    Document   : undo_serah_tugasan
    Created on : Sep 20, 2013, 4:42:51 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<html>
  <body>
    <script type="text/javascript">
      $(document).ready(function() {
        $('form').submit(function() {
          doBlockUI();
        });
      });

      function doQueryTask(val) {
        $('#tugasan').html('');
        $('#loading-img').show();
        var nama = $('#id_pguna option:selected').text();

        var url = '${pageContext.request.contextPath}/agihTugasan?query&idPengguna=' + val;
        $.get(url,
                function(data) {
                  $('#loading-img').hide();
                  $('#tugasan').html(nama + ' mempunyai ' + data + ' tugasan.');
                }
        );
      }

      function doAgih(f) {

        var sebab = document.form1.sebab.value;
        var i = $('#id_pguna').val();
        if (i == '') {
          alert('Sila pilih pengguna terlebih dahulu.');
          $('#id_pguna').focus();
          return false;
        }
        else if ((sebab == "")) {
          alert('Sila Masukkan Sebab ');
          document.form1.sebab.focus();
          return false;
        }
        if (confirm('Adakah anda pasti?')) {
          f.action = f.action + '?doAgihToPT';
          f.submit();
        }
      }

      function doBlockUI() {
        $.blockUI({
          message: $('#displayBox'),
          css: {
            top: ($(window).height() - 50) / 2 + 'px',
            left: ($(window).width() - 50) / 2 + 'px',
            width: '50px'
          }
        });
      }
    </script>
    <s:messages />
    <s:errors />
    <s:useActionBean beanclass="etanah.view.stripes.pelupusan.utility.UtilitiUndoSerahTugasan" var="penggunaperanan"/>
    <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none" alt=""/>
    <s:form beanclass="etanah.view.stripes.pelupusan.utility.UtilitiUndoSerahTugasan" name="form1">
      <s:hidden name="permohonan.idPermohonan"></s:hidden>
        <fieldset class="aras1">

          <legend>Agihan Semula</legend>
          <br>
          <p><label for="idPermohonan">ID Permohonan/ID Perserahan :</label>
          <s:text name="idPermohonan" id="idPermohonan" onkeyup="this.value=this.value.toUpperCase();" />
        </p>

        <p>
          <label>&nbsp;</label>
          <s:submit name="checkPermohonan" value="Seterusnya" class="btn" />
        </p>
        <br>
      </fieldset>
      <br>
      <c:if test="${actionBean.form}">
        <fieldset class="aras1">
          <s:hidden name="permohonan.idPermohonan"></s:hidden>
            <legend>Maklumat Perserahan</legend>
            <br>
            <p><label for="idPermohonan">Id Permohonan/ID Perserahan :</label>
            ${actionBean.permohonan.idPermohonan}
          </p>

          <p><label for="kodUrusan">Kod Urusan/Urusan :</label>
            ${actionBean.permohonan.kodUrusan.kod} -
            ${actionBean.permohonan.kodUrusan.nama}
          </p>

          <p><label for="tarikhDaftar">Tarikh Perserahan/Permohonan :</label>
            <fmt:formatDate pattern="dd/MM/yyyy hh:mm aa" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>
          </p>
          &nbsp;
          <p><label for="penyerah">Penyerah :</label>
            ${actionBean.permohonan.penyerahNama}
          </p>
            <p><label>Peringkat :</label>
              ${actionBean.stage == null ? "-" : actionBean.stage}
            </p>
          <p><label>Tugasan Diambil Oleh :</label>
            ${actionBean.nama == null ? "-" : actionBean.nama}
          </p>
          <br>
        </fieldset>
        <br>
        <div class="subtitle">
          <fieldset class="aras1">
            <legend>
              Agihan Tugas
            </legend>
            <p><label>Hantar Kepada :</label>
              <%--
              <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna" onchange="doQueryTask(this.value);">
                <s:option value="">-- Sila Pilih --</s:option>
                <s:options-collection collection="${penggunaperanan.listPp}" value="idPengguna" label="nama" sort="nama" />
              </s:select> 
                <img src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/>
              --%>

              <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna" onchange="doQueryTask(this.value);">
                <s:option value="">-- Sila Pilih --</s:option>
                <c:forEach items="${penggunaperanan.listPp}" var="i">                  
                  <s:option value="${i.pengguna.idPengguna}" >${i.pengguna.nama} - (${i.pengguna.idPengguna})</s:option>
                </c:forEach>
              </s:select> 
              <img src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/>
              <em id="tugasan"></em>
            </p>
            <p>
              <label><em>*</em>Sebab Agihan :</label>
              <s:textarea name="sebab" cols="50" rows="10"/>
            </p>
            <p>
              <label>&nbsp;</label>
              <s:button  name="Simpan" value="Agih" class="btn" onclick="doAgih(this.form);"/>
            </p>
          </fieldset>
        </div>
      </body>
    </c:if>
  </s:form>
</html>