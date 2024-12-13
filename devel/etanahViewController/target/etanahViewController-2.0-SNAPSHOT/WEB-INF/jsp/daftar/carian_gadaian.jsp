<%-- 
    Document   : carian_gadaian
    Created on : Dec 3, 2009, 2:30:17 PM
    Author     : fikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
  $(document).ready(function() {
    var len = $(".hakmilik").length;

    for (var i = 0; i <= len; i++) {
      $('.perserahan' + i).click(function() {
        window.open("${pageContext.request.contextPath}/common/maklumat_permohonan?viewPermohonan&idPermohonan=" + $(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
      });
    }
  });
  function doPopup(val) {
    var url = '${pageContext.request.contextPath}/common/maklumat_permohonan?viewPermohonan&idPermohonan=' + val;
    window.open(url, "eTanah", strWindowFeatures);
  }
  function remove(val) {
    if (confirm('Adakah Anda Pasti?')) {
      $.blockUI({
        message: $('#displayBox'),
        css: {
          top: ($(window).height() - 50) / 2 + 'px',
          left: ($(window).width() - 50) / 2 + 'px',
          width: '50px'
        }
      });
      var url = '${pageContext.request.contextPath}/daftar/gadaian?delete&id=' + val;
      $.get(url,
              function(data) {
                $('#page_div').html(data);
                $.unblockUI();
              }, 'html');
    }
  }

  function removeSelect() {

    if (confirm('adakah anda pasti?')) {
      var param = '';
      var len = $('.remove').length;
      doBlockUI();
      for (var i = 1; i <= len; i++) {

        var ckd = $('#rm_' + i).is(":checked");
        if (ckd) {
          param = param + '&uids=' + $('#rm_' + i).val();
        }
      }

      if (param == '') {
        alert('Tiada urusan dipilih.');
        doUnBlockUI();
        return;
      }

      var url = '${pageContext.request.contextPath}/daftar/gadaian?deleteSelectedItem' + param;

      $.ajax({
        type: "GET",
        url: url,
        dataType: 'html',
        error: function(xhr, ajaxOptions, thrownError) {
          alert("error=" + xhr.responseText);
          doUnBlockUI();
        },
        success: function(data) {
          $('#page_div').html(data);
          //doPopupMsg("Kemaskini berjaya!");
          doUnBlockUI();
        }
      });
    }
  }
  
  function removeSelectTN() {

    if (confirm('adakah anda pasti?')) {
      var param = '';
      var len = $('.remove').length;
      doBlockUI();
      for (var i = 1; i <= len; i++) {

        var ckd = $('#rm_' + i).is(":checked");
        if (ckd) {
          param = param + '&uids=' + $('#rm_' + i).val();
        }
      }

      if (param == '') {
        alert('Tiada urusan dipilih.');
        doUnBlockUI();
        return;
      }

      var url = '${pageContext.request.contextPath}/daftar/gadaian?deleteSelectedItemTN' + param;

      $.ajax({
        type: "GET",
        url: url,
        dataType: 'html',
        error: function(xhr, ajaxOptions, thrownError) {
          alert("error=" + xhr.responseText);
          doUnBlockUI();
        },
        success: function(data) {
          $('#page_div').html(data);
          //doPopupMsg("Kemaskini berjaya!");
          doUnBlockUI();
        }
      });
    }
  }

  function selectAll(a) {
    for (i = 1; i < 100; i++) {
      if (a.id == 'choose') {
        var c = document.getElementById("chkbox" + i);
        if (c == null)
          break;
        c.checked = a.checked;
      } else if (a.id == 'delete') {
        var c = document.getElementById("rm_" + i);
        if (c == null)
          break;
        c.checked = a.checked;
      }
    }
  }

  function reload(val) {
    doBlockUI();
    var url = '${pageContext.request.contextPath}/daftar/gadaian?searchGadaian&idHakmilik=' + val;
    $.ajax({
      type: "GET",
      url: url,
      dataType: 'html',
      error: function(xhr, ajaxOptions, thrownError) {
        alert("error=" + xhr.responseText);
        doUnBlockUI();
      },
      success: function(data) {
        $('#page_div').html(data);
        $('.popup').each(function() {
          $(this).html('<u>' + $(this).text() + '</u>');
          $(this).mouseover(function() {
            $(this).addClass("cursor_pointer");
          });
        });
        var len = $(".hakmilik").length;

        for (var i = 0; i <= len; i++) {
          $('.perserahan' + i).click(function() {
            window.open("${pageContext.request.contextPath}/common/maklumat_permohonan?viewPermohonan&idPermohonan=" + $(this).text(), "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
          });
        }
        doUnBlockUI();
      }
    });
  }
</script>

<s:form beanclass="etanah.view.daftar.Gadaian">
  <%--<div class="subtitle">
      <fieldset class="aras1">
          <legend>
              Carian Gadaian
          </legend>
          <p>
              <label for="nama pihak">No. Berdaftar Gadaian :</label>
              <s:text name=""/>
          </p>
          <p>
              <label>&nbsp;</label>
              <s:submit name="searchGadaian" value="Cari" class="btn"/>
          </p>
      </fieldset> 
  </div>--%>
  <div class="subtitle displaytag">
    <fieldset class="aras1">
      <legend>Senarai Hakmilik Terlibat</legend>
      <c:if test="${!empty moreThanOneHakmilik}">
        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'HKTHK'
                      && actionBean.permohonan.kodUrusan.kod ne 'HSTHK'
                      && actionBean.permohonan.kodUrusan.kod ne 'HMSC'}">
              <p>
                <label></label>&nbsp;
                <font color="red" size="2">
                  <input type="checkbox" name="copyToAll" value="1" id="copyToAll"/>
                  <b><em>Sila klik jika sama untuk semua hakmilik.</em></b>
                </font>
              </p>
        </c:if>
      </c:if>
      <p><font color="red">Sila Pilih Hakmilik Yang Terlibat.</font></p>
      <p>
        <label>Hakmilik :</label>
        <s:select name="idHakmilik" onchange="reload(this.value);" id="hakmilik">
          <c:forEach items="${actionBean.senaraiHakmilikTerlibat}" var="item" varStatus="line">
            <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
              ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
            </s:option>
          </c:forEach>
        </s:select>
      </p>
      <br/>
    </fieldset>
    <br/>
    <fieldset class="aras1">
      <legend>
        Senarai Urusan 
        <%--<c:choose>
            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'KVPK' || actionBean.permohonan.kodUrusan.kod eq 'KVAK'
                            || actionBean.permohonan.kodUrusan.kod eq 'KVLK' || actionBean.permohonan.kodUrusan.kod eq 'KVSK'
                    || actionBean.permohonan.kodUrusan.kod eq 'JML'}">
                    Kaveat
            </c:when>
            <c:when test="${fn:startsWith(actionBean.permohonan.kodUrusan.kod,'GD')
                            || actionBean.permohonan.kodUrusan.kod eq 'JPGD'
                            || actionBean.permohonan.kodUrusan.kod eq 'JMGD'
                             }">
                    Gadaian / Pajakan
            </c:when>
            <c:when test="${fn:startsWith(actionBean.permohonan.kodUrusan.kod,'KV')}">
                Kaveat
            </c:when>
            <c:when test="${fn:startsWith(actionBean.permohonan.kodUrusan.kod,'PJ')
                            || actionBean.permohonan.kodUrusan.kod eq 'JPGD'
                            || actionBean.permohonan.kodUrusan.kod eq 'JMGD'
                            }">

                            Pajakan
                    </c:when>
                    <c:when test="${fn:startsWith(actionBean.permohonan.kodUrusan.kod,'IS')}">
                        Ismen
                    </c:when>
                </c:choose>--%>
      </legend>
      <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'HKTHK'
                    && actionBean.permohonan.kodUrusan.kod ne 'HSTHK'
                    && actionBean.permohonan.kodUrusan.kod ne 'HMSC'}">
            <br/>
            <p>
              <font color="red">Sila Pilih Pada Rekod Yang Berkenaan dan Tekan Butang Simpan.</font>
            </p>
      </c:if>      
      <%--<display:table class="tablecloth" name="${actionBean.hakmilikUrusanList}" pagesize="10" cellpadding="0" cellspacing="0"
                     requestURI="${pageContext.request.contextPath}/common/pihak" id="line">
          <display:column title="<input type='checkbox' name='semua' id='choose' onclick='javascript:selectAll(this);' />">
              <s:checkbox name="chkbox" id="chkbox${line_rowNum}" value="${line.idUrusan}" class="checkbox"/>
          </display:column>
          <display:column title="No" sortable="true">${line_rowNum}</display:column>
          <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="hakmilik"/>
          <display:column property="idPerserahan" title="ID Perserahan" class="popup perserahan${line_rowNum}"/>
          <display:column title="Tarikh Daftar"><fmt:formatDate value="${line.tarikhDaftar}" pattern="dd/MM/yyyy hh:mm:ss"/></display:column>
          <display:column property="permohonan.idPermohonan" title="ID Perserahan"/>
          <display:column property="kodUrusan.nama" title="Urusan"/>
      </display:table>--%>

      <c:choose>
        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'HKTHK'
                        or actionBean.permohonan.kodUrusan.kod eq 'HSTHK'
                        or actionBean.permohonan.kodUrusan.kod eq 'HMSC'}">
                <div class="content" align="center">
                  <display:table class="tablecloth" name="${actionBean.senaraiList}" pagesize="100" cellpadding="0" cellspacing="0"
                                 requestURI="/daftar/gadaian" id="line3" style="width:90%;">
                    <display:column title="Bil" style="width:1%;"><div align="right">${line3_rowNum}</div></display:column>
                    <display:column property="idPermohonan" title="ID Perserahan" style="width:20%;"/> 
                    <display:column property="kodUrusan.kod" title="Kod Urusan" style="width:13%;"/>
                    <display:column property="kodUrusan.nama" title="Nama Urusan"/>
                    <display:column property="infoAudit.tarikhMasuk" title="Tarikh Daftar" style="width:17%;" format="{0,date,dd/MM/yyyy hh:mm aa}"/>
                  </display:table>
                </div>
        </c:when>
        <c:otherwise>
          <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.senaraiList}" pagesize="100" cellpadding="0" cellspacing="0"
                           requestURI="/daftar/gadaian" id="line3">
              <display:column title="<input type='checkbox' name='semua' id='choose' onclick='javascript:selectAll(this);' />">                        
                <s:checkbox name="chkbox" id="chkbox${line3_rowNum}" value="${line3.idPermohonan}" class="checkbox"/>
              </display:column>
              <display:column title="No" sortable="true">${line3_rowNum}</display:column>
              <display:column property="idPermohonan" title="ID Perserahan" class="popup perserahan${line3_rowNum}"/>
              <%--<display:column title="Hakmilik Terlibat" class="hakmilik">
                  <c:forEach items="${line3.senaraiHakmilik}" var="item">
                      <c:forEach items="${actionBean.permohonan.senaraiHakmilik}" var="item2">
                          <c:if test="${item.hakmilik.idHakmilik eq item2.hakmilik.idHakmilik}">
                              ${item.hakmilik.idHakmilik}
                              <s:hidden name="hakmilikTerlibat" value="${item.hakmilik.idHakmilik}"/>
                              <br/>
                          </c:if>
                      </c:forEach>                             
                  </c:forEach>
              </display:column>--%>
              <display:column property="kodUrusan.nama" title="Urusan"/>
            </display:table>        
          </div>
          <c:if test="${fn:length(actionBean.senaraiList) > 0}">
              <p>
              <label>&nbsp;</label>
              <c:choose>
                  <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'TN'||actionBean.permohonan.kodUrusan.kod eq 'TA'}">
                      <s:button name="saveUrusanTukarNama" id="add" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                  </c:when>
                  <c:otherwise>                      
                        <s:button name="save" id="add" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                  </c:otherwise>
                  
              </c:choose>
            </p>
          </c:if>
        </c:otherwise>
      </c:choose>
    </fieldset>
    <br/>
    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'HKTHK'
                  && actionBean.permohonan.kodUrusan.kod ne 'HSTHK'
                  && actionBean.permohonan.kodUrusan.kod ne 'HMSC'}">
          <fieldset class="aras1">
            <legend>
              Senarai Urusan Terlibat
              <%-- <c:choose>
                   <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'KVPK' || actionBean.permohonan.kodUrusan.kod eq 'KVAK'
                                   || actionBean.permohonan.kodUrusan.kod eq 'KVLK' || actionBean.permohonan.kodUrusan.kod eq 'KVSK'}">
                           Atas Kepentingan
                   </c:when>
                   <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'GD'}">
                       Melepaskan Gadaian
                   </c:when>
                   <c:when test="${fn:startsWith(actionBean.permohonan.kodUrusan.kod,'KV')}">
                       Melepaskan / Pembatalan / Menarik Balik Kaveat
                   </c:when>
                   <c:when test="${fn:startsWith(actionBean.permohonan.kodUrusan.kod,'PJ') || 
                                   fn:startsWith(actionBean.permohonan.kodUrusan.kod,'GD')}">
                       Gadaian / Pajakan Terlibat
                   </c:when>
                   <c:when test="${fn:startsWith(actionBean.permohonan.kodUrusan.kod,'IS')}">
                       Melepaskan Ismen
                   </c:when>
               </c:choose>--%>
            </legend>
            <div class="content" align="center">
                <c:choose>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'TN'||actionBean.permohonan.kodUrusan.kod eq 'TA'}">
                        <display:table class="tablecloth" name="${actionBean.senaraiMau}" pagesize="100" 
                                       cellpadding="0" cellspacing="0"
                                       requestURI="${pageContext.request.contextPath}/daftar/gadaian" 
                                       id="line2">
                            <display:column title="<input type='checkbox' name='semua' id='delete' onclick='javascript:selectAll(this);' />">
                                <s:checkbox name="checkbox" id="rm_${line2_rowNum}" value="${line2.idAtasUrusan}" class="remove"/>
                            </display:column>
                            <display:column title="No" sortable="true">${line2_rowNum}</display:column>
                            <display:column property="urusan.kodUrusan.nama" title="Urusan"/>
                            <display:column property="permohonanBaru.idPermohonan" title="ID Perserahan"/>
                            <display:column property="urusan.hakmilik.idHakmilik" title="ID Hakmilik"/>
                        </display:table>
                        <br/>
                        <c:if test="${fn:length(actionBean.senaraiMau) > 0}">
                            <s:button name="delete" onclick="removeSelectTN();" value="Hapus" class="btn"/>&nbsp;
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <display:table class="tablecloth" name="${actionBean.senaraiPermohonanHubungan}" pagesize="100" cellpadding="0" cellspacing="0"
                                       requestURI="${pageContext.request.contextPath}/daftar/gadaian" id="line2">
                            <display:column title="<input type='checkbox' name='semua' id='delete' onclick='javascript:selectAll(this);' />">
                                <s:checkbox name="checkbox" id="rm_${line2_rowNum}" value="${line2.idPermohonanHubungan}" class="remove"/>
                            </display:column>
                            <display:column title="No" sortable="true">${line2_rowNum}</display:column>
                            <display:column property="permohonanSasaran.kodUrusan.nama" title="Urusan"/>
                            <display:column property="permohonanSasaran.idPermohonan" title="ID Perserahan"/>
                        </display:table>
                        <br/>
                        <c:if test="${fn:length(actionBean.senaraiPermohonanHubungan) > 0}">
                            <s:button name="delete" onclick="removeSelect();" value="Hapus" class="btn"/>&nbsp;
                        </c:if>
                    </c:otherwise>
                </c:choose>
              
            
              <%--<display:table class="tablecloth" name="${actionBean.senaraiMau}" pagesize="10" cellpadding="0" cellspacing="0"
                             requestURI="${pageContext.request.contextPath}/common/pihak" id="line">
                  <display:column title="<input type='checkbox' name='semua' id='delete' onclick='javascript:selectAll(this);' />">
                      <s:checkbox name="checkbox" id="rm_${line_rowNum}" value="${line.idAtasUrusan}" class="remove"/>
                  </display:column>
                  <display:column title="No" sortable="true">${line_rowNum}</display:column>
                  <display:column property="urusan.kodUrusan.nama" title="Urusan"/>
                  <display:column property="urusan.hakmilik.idHakmilik" title="ID Hakmilik"/>
                  <display:column property="urusan.idPerserahan" title="ID Permohonan"/>
                  <display:column title="Hapus">
                      <div align="center">
                          <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                               id='rem_${line_rowNum}' onclick="remove('${line.idAtasUrusan}')">
                      </div>
                  </display:column>
              </display:table>--%>
              
            </div>            
          </fieldset>
    </c:if>
  </div>
</s:form>
