
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.tools.min.js?select=full&debug=true"></script>
<script type="text/javascript">

  $(document).ready(function() {
  <%-- $("img[title]").tooltip({
       // tweak the position
       offset: [10, 2],

            // use the "slide" effect
            effect: 'slide'
        }).dynamic({ bottom: { direction: 'down', bounce: true } });--%>
          });

          function doSearch(e, f) {
            var a = $('#idPermohonan').val();
            if (a == '') {
              alert('Sila masukan id Perserahan.');
              $('#idPermohonan').focus();
              return;
            }

            f.action = f.action + '?' + e;
            f.submit();
          }

          function doSearch2(idPermohonan) {
            var url = '${pageContext.request.contextPath}/daftar/cetak_semula_dokumen?search&idPermohonan=' + idPermohonan;
            f = document.form1;
            f.action = url;
            f.submit();
          }

          function doPrintViaApplet(docId) {
            //alert('tsttt');
            var a = document.getElementById('applet');
            a.doPrint(docId.toString());
          }

          function doViewReport(v) {
            var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
          }

          function doSaveCapaian(v) {
            var sbb = $('#sbb_cetakan_semula').val();
            if (sbb == '') {
              alert('Sila masukan Sebab');
              return;
            }
            var url = '${pageContext.request.contextPath}/daftar/cetak_semula_dokumen?cetakSemula&sbb_cetakan_semula=' + sbb.trim() + '&id_dokumen=' + v;
            $.ajax({
              type: "GET",
              url: url,
              success: function(data) {
                if (data == '1') {
                  doPrintViaApplet(v);
                }
              }
            });
          }

          function doSignFile(today) {

            var DELIM = "|";
            var dhde_file = '';
            var parameterToSign = '';

            $('.signs').each(function(index) {
              index = index + 1;
              if ($('#dhde' + index).is(':checked')) {
                dhde_file = $('#dhde' + index).val() + '#' + $('#dhde_path' + index).val() + '#dhde#N';
                if (parameterToSign != '') {
                  parameterToSign = parameterToSign + DELIM + dhde_file;
                } else {
                  parameterToSign = dhde_file;
                }
              }
            });
            //alert(parameterToSign);
            sign(parameterToSign, today);
          }

          function doPrintViaApplet2() {
            var FILE_TO_PRINT = '';
            var DELIM = ',';

            $('.cetaks').each(function(index) {
              index = index + 1;
              if ($('#cetak' + index).is(':checked')) {
                if (FILE_TO_PRINT == '') {
                  FILE_TO_PRINT = $('#cetak' + index).val();
                } else {
                  FILE_TO_PRINT = FILE_TO_PRINT + DELIM + $('#cetak' + index).val();
                }
              }

            });

            if (FILE_TO_PRINT != '') {
              var a = document.getElementById('applet');
              a.doPrint(FILE_TO_PRINT);
              //a.printDocument(FILE_TO_PRINT);
            }
          }


          function sign(parameterToSign, today) {
            if (parameterToSign != '') {
              var signer = new ActiveXObject("SAPDFSigner.Form1");
              signer.SigningPDFFile(parameterToSign, today);
              if (signer.getValue() != "") {
                document.eload.message.value = signer.getValue();
              }
            } else {
              alert('Sila Pilih Dokumen untuk ditandatangan.');
            }
          }

          function toUppercase(id) {
            $('#' + id).val($('#' + id).val().toUpperCase());
          }
          function selectAll(a) {
            var id = a.id;
            var len = $('.signs').length;

            for (i = 1; i <= len; i++) {
              if (id == 'semuaDhde') {
                var c = document.getElementById("dhde" + i);
                if (c == null)
                  continue;
                c.checked = a.checked;
              } else if (id == 'cetakSemua') {
                var c = document.getElementById("cetak" + i);
                if (c == null)
                  continue;
                c.checked = a.checked;
              }
            }
          }

</script>
<style type="text/css">
  .tooltip {
    display:none;
    background:transparent url(${pageContext.request.contextPath}/pub/images/black_arrow.png);
    font-size:12px;
    height:70px;
    width:160px;
    padding:25px;
    color:#fff;
  }

</style>
<s:messages/>
<s:errors/>
<s:messages/>
<s:form beanclass="etanah.view.daftar.utiliti.CetakSalinanSahAction" name="form1">
  <div class="subtitle">
    <fieldset class="aras1">
      <legend>
        Carian Salinan Sah
      </legend>
      <p>
        <label>ID Carian :</label>
        <s:text name="idCarian" id="idCarian" onblur="toUppercase(this.id);"/>
      </p>
      <p>
        <label>&nbsp;</label>
        <s:button name="searchCarian" value="Cari" class="btn" onclick="doSearch(this.name, this.form);"/>
      </p>
    </fieldset>
  </div>
  <br/>
  <c:if test="${!empty actionBean.permohonanCarian}">
    <div class="subtitle">
      <fieldset class="aras1">
        <legend>
          Permohonan Carian
        </legend>
        <p>
          <label>ID Carian :</label> &nbsp; ${actionBean.permohonanCarian.idCarian} &nbsp;
        </p>
        <p>
          <label>Urusan :</label> &nbsp; ${actionBean.permohonanCarian.urusan.kod} - ${actionBean.permohonanCarian.urusan.nama}&nbsp;
        </p>
        <p align="center">
          <label>&nbsp;</label>&nbsp;
          <display:table class="tablecloth" name="${actionBean.senaraiDokumen}"
                         cellpadding="0" cellspacing="0" id="line1">
            <display:column title="Bil" class="signs">
              ${line1_rowNum}
              <!-- comment out by azri: KIV: because taktau a dokumen mane nak kasik tandatangan.. 
              buka dulu sume pastu pandai2 la user mintak mane nak t/tangan.. mane aku tau keje dieorang.. -->
              <%--<c:if test="${line1.kodDokumen.kod eq 'SDH'}">                           
              <input type="checkbox" value="${line1.idDokumen}" id="dhde${line1_rowNum}"/>
              <c:set var="path"/>
              <c:forTokens delims="/" items="${line1.namaFizikal}" var="items" begin="0" end="3">
                <c:set var="path" value="${path}/${items}"/>
              </c:forTokens>
              <input type="hidden" id="dhde_path${line1_rowNum}" value="${path}"/>
              </c:if>--%>
            </display:column>
            <display:column property="kodDokumen.kod" title="Kod Dokumen"/>
            <display:column property="kodDokumen.nama" title="Nama Dokumen"/>
            <display:column title="Tajuk">
              ${line1.tajuk}
              <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" title="Papar ${line1.tajuk}"
                   onclick="doViewReport('${line1.idDokumen}');" height="20" width="20" alt="papar" onmouseover="this.style.cursor = 'pointer';"/>
            </display:column>
            <display:column title="<input type='checkbox' id='cetakSemua' name='semua' onclick='javascript:selectAll(this);' &nbsp;/> Cetak " class="cetaks">
              <c:if test="${line1.namaFizikal != null}">
                <input type="checkbox" value="${line1.idDokumen}" id="cetak${line1_rowNum}"/>
                
                <%--<s:button name="cetak" value="Cetak" onclick="doPrintViaApplet('${line.idDokumen}');" class="btn"/>--%>
              <p align="center"><img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png" title="Cetak ${line1.tajuk}"
                                     onclick="doPrintViaApplet('${line1.idDokumen}');" height="20" width="20" alt="cetak" onmouseover="this.style.cursor = 'pointer';"/></p>
              </c:if>
            </display:column>
          </display:table>
        </p>
        <c:if test="${fn:length(actionBean.senaraiDokumen) > 0}">
          <p>
            <label>&nbsp;</label>
             <!-- comment out by ameer.. request by k.safina .. fat 13/6/2013 -->
            <%--- <s:button name="" id="" value="T/tangan" class="btn" onclick="doSignFile('${today}');"/> ---%>
            <s:button name="" value="Cetak" class="btn" onclick="doPrintViaApplet2();"/>
          </p>
        </c:if>
      </fieldset>
    </div>
    <br/>
    <applet code="PrintApplet.class" ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
            ${pageContext.request.contextPath}/commons-httpclient-2.0.2.jar,
            ${pageContext.request.contextPath}/commons-logging.jar,
            ${pageContext.request.contextPath}/swingx-1.6.jar,
            ${pageContext.request.contextPath}/log4j-1.2.12.jar,
            ${pageContext.request.contextPath}/jpedal_trial.jar"
            codebase = "."
            name     = "applet"
            id       = "applet"
            width    = "1"
            height   = "1"
            align    = "middle">
      <param name="uploadAction" value="<%=request.getContextPath()%>/PrintServlet"/>
      <param name ="disabledWatermark" value="true"/>
      <param name ="method" value="pdfp">
      <%
                  Cookie[] cookies = request.getCookies();
                  StringBuffer sb = new StringBuffer();
                  for (int i = 0; i < cookies.length; i++) {
                      sb.setLength(0);
                      sb.append(cookies[i].getName());
                      sb.append("=");
                      sb.append(cookies[i].getValue());
      %>
      <param name="Cookie<%= i%>" value="<%= sb.toString()%>"><%
                  }
      %>
    </applet>
  </c:if>

</s:form>