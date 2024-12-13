<%-- 
    Document   : notis_kaveat
    Created on : Jul 26, 2010, 12:55:05 PM
    Author     : fikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/date.format.js"></script>
<script type="text/javascript">
  $(document).ready(function() {
    var bilSalinan = $("#bilSalinan").val();
    var caraHantar = $("#caraHantar").val()
    if (bilSalinan === "") {
      // by defult Bilangan Salinan is '1'
      $("#bilSalinan").val("1");
    }
    if (caraHantar !== "") {
      if (caraHantar === 'PN') { // Penghantar Notis
        $("#tkhWarta").val("");
        $("#tkhTampal").val(""); 
      } else if (caraHantar === 'PO') { // POS
        $("#penerimaNotis").val("");
        $("#tkhTampal").val("");
        $("#noWarta").val("");
        $("#tkhWarta").val("");               
      }
    }
  });

  function doReload(event, form, value) {
    doBlockUI();
    var q = $(form).formSerialize();
    var url = form.action + '?' + event + '&idHakmilik=' + value + '&isNotis=true';
    $.ajax({
      type: "POST",
      url: url,
      dataType: 'html',
      data: q,
      error: function(xhr) {
      },
      success: function(data) {
        $('#page_div').html(data);
        $("#page_div textarea").each(function(el) {
          $(this).focus(function() {
            $(this).addClass('focus');
          });
          $(this).blur(function() {
            $(this).removeClass('focus');
            $(this).val($(this).val().toUpperCase());
          });
        });
        $("#page_div input:text").each(function(el) {
          if (!$(this).hasClass('normal_text')) {
            $(this).focus(function() {
              $(this).addClass('focus');
            });
            $(this).blur(function() {
              $(this).removeClass('focus');
              $(this).val($(this).val().toUpperCase());
            });
          }
        });

        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        doUnBlockUI()
      }
    });

  }

  function doCalcEndDate(id) {

    var hari = 0;
    var bln = 2;
    var thn = 0;

    if ($('#' + id).val() == '') {
      return;
    }
    var startDate = $('#' + id).val();
    //manual process :: should find conclusion on this
    var y = parseInt(startDate.substring(6, 10), 10);
    var m = parseInt(startDate.substring(3, 5), 10);
    var d = parseInt(startDate.substring(0, 2), 10);

    if (!isNaN(hari)) {
      d = d + hari;
      d = d - 1;
      if (d == 0) {
        m = m - 1;
      }
    }


    if (!isNaN(bln)) {
      m = m + bln;
      if (m > 12) {
        y = y + 1;
        m = m - 12;
        if (m == 2) {
          var isleap = (y % 4 == 0 && (y % 100 != 0 || y % 400 == 0));
          if (d >= 30) {
            if (isleap) {
              d = 28;
            } else {
              d = 27;
            }
          } else if (d == 0) {
            if (isleap) {
              d = 29;
            } else {
              d = 28;
            }
          }
        }
      }
    }

    if (!isNaN(thn)) {
      y = y + thn;
    }

    if (d == 0 && m == 0) {
      y = y - 1;
    }
    //y = y + thn;
    var endDate = new Date();
    endDate.setDate(d);
    endDate.setMonth(m - 1);
    endDate.setFullYear(y);
    $('#tkhTamat').val(endDate.format("dd/mm/yyyy"));
  }
</script>
<s:errors/>
<s:messages/>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<s:form beanclass="etanah.view.daftar.Kaveat">
  <div class="subtitle">
    <fieldset class="aras1">
      <legend>
        Kemasukan Notis
      </legend>


      <c:if test="${fn:length(actionBean.senaraiHakmilik) > 1}">
        <p>
          <label>&nbsp;</label>
          <font color="red" size="2">
            <c:set var="checked" value=""/>
            <c:if test="${copy_all eq 'true'}">
              <c:set var="checked" value="checked"/>
            </c:if>
            <input type="checkbox" name="copyToAllHakmilik" value="1" ${checked}/>
            Sila klik jika maklumat perintah sama untuk semua hakmilik.
          </font>
        </p>
      </c:if>

      <p>
        <label>ID Hakmilik : </label>
        <s:select name="idHakmilik" onchange="doReload('showFormNotis', this.form,this.value);">
          <c:forEach items="${actionBean.senaraiHakmilik}" var="item">
            <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
              ${item.hakmilik.idHakmilik} (${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama})
            </s:option>
          </c:forEach>
        </s:select><font color="red" size="2">&nbsp;* Sila Pilih Hakmilik</font>
      </p>
      <p>
        <label>Borang :</label>&nbsp;
        ${actionBean.notis.dokumenNotis.kodDokumen.kod} - ${actionBean.notis.dokumenNotis.kodDokumen.nama}&nbsp;
      </p>
      <p>
        <label>ID Perserahan :</label>&nbsp;
        ${actionBean.permohonan.idPermohonan}&nbsp;
      </p>
      <p>
        <label>Urusan :</label>&nbsp;
        ${actionBean.permohonan.kodUrusan.kod} - ${actionBean.permohonan.kodUrusan.nama}&nbsp;
      </p>
      <br>
      <p>
        <label>Status Penyampaian :</label>&nbsp;
        <%-- comment out by azri: 
        <s:select name="statusPenyampaian" id="statusPenyampaian" style="width:180px;">
          <s:option value="">Sila Pilih</s:option>
          <s:options-collection collection="${listUtil.senaraiKodStatusTerima}" label="nama" value="kod" />
        </s:select>&nbsp;--%>
        <%-- N9 USE THIS --%>
        <s:select name="kodStatusTerima" id="statusPenyampaian" style="width:200px;">
          <s:option value="">Sila Pilih</s:option>
          <s:options-collection collection="${listUtil.senaraiKodStatusTerimaN9}" label="nama" value="kod" />
        </s:select>&nbsp;
      </p>
      <p>
        <label>Cara Penghantaran :</label>&nbsp;
        <%--<s:hidden name="caraHantar" id="caraHantar" value="${actionBean.notis.kodCaraPenghantaran.kod}"/>--%> 
        <s:select name="kodCaraPenghantaran" id="caraPenghantaran" style="width:200px;" 
                  onchange="javaScript:changeCara(this.value)">
          <s:option value="">Sila Pilih</s:option>
          <s:options-collection collection="${listUtil.senaraiKodCaraPenghantaran}" label="nama" value="kod" />
        </s:select>&nbsp;
      </p>
      <p>
        <label>Bilangan Salinan :</label>&nbsp;
        <s:text name="notis.bilangan" readonly="${noEdit}" id="bilSalinan"/>&nbsp;
      </p>
      <p>
        <label>Tarikh Notis :</label>&nbsp;
        <s:text name="tkhNotis" class="datepicker" readonly="${noEdit}" id="tkhNotis" onchange="doCalcEndDate(this.id);"/>&nbsp;
      </p>
      <p>
        <label>Tarikh Notis Diserah :</label>&nbsp;
        <s:text name="tkhHantar" class="datepicker" readonly="${noEdit}" onchange="doCalcEndDate(this.id);"/>&nbsp;
      </p>
      <p>
        <label>Tarikh Luput Notis :</label>&nbsp;
        <s:text name="tkhTamat" class="datepicker" readonly="${noEdit}" id="tkhTamat"/>&nbsp;
      </p>      
      <!--            <p>
                      <label>Tarikh Diambil :</label>&nbsp;
      <%--<s:text name="notis.bilangan" readonly="${noEdit}"/>&nbsp;--%>
  </p>-->      
      <p>
        <label>Penerima Notis :</label>&nbsp;
        <s:text name="notis.penerimaNotis" style="width:300px" readonly="${noEdit}" id="penerimaNotis"/>&nbsp;
      </p>
      <p>
        <label>Tarikh Tampal Dinotis Awam :</label>&nbsp;
        <s:text name="tkhTampal" class="datepicker" readonly="${noEdit}" id="tkhTampal"/>&nbsp;
      </p>
      <%--<p>
          <label>Tarikh Hantar Warta :</label>&nbsp;
      </p>--%>
      <p>
        <label>No Warta :</label>&nbsp;
        <s:text name="notis.warta.noRujukan" readonly="${noEdit}" id="noWarta"/>&nbsp;
      </p>           
      <p>
        <label>Tarikh Warta :</label>&nbsp;
        <s:text name="tkhWarta" class="datepicker" id="tkhWarta" readonly="${noEdit}" onchange="doCalcEndDate(this.id);"/>&nbsp;        
      </p>
      <br/>
      <c:if test="${empty noEdit}">
        <p>
          <label>&nbsp;</label>
          <s:button name="saveNotisInfo" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>                    
        </p>
      </c:if>
    </fieldset>
  </div>
</s:form>
