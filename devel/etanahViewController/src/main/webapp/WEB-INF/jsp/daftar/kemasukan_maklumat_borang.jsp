<%-- 
    Document   : kemasukan_maklumat_kaviet
    Created on : Dec 3, 2009, 2:43:10 PM
    Author     : fikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/date.format.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
  $(document).ready(function() {
    var val = '${actionBean.permohonanRujukanLuar.kodPerintah.kod}';
    //if(val == 'PD')
    //    $('.mahkamah').hide();
    var val2 = '${actionBean.permohonan.kodUrusan.kod}';
    //if(val2 == 'TMAME')
    //    $('.mahkamah').hide();


    $(".datepicker2").datepicker(
            {
              dateFormat: 'dd/mm/yy',
              changeMonth: true,
              changeYear: true,
              maxDate: new Date(),
              beforeShow: function(input, inst)
              {
                inst.dpDiv.css({
                  marginTop: -input.offsetHeight + 'px', marginLeft: input.offsetWidth + 'px'
                });
              }
            });
  });

  function doReload_hakmilik(val, f) {
    f.action = '${pageContext.request.contextPath}/daftar/kaveat?reloadHakmilik&idHakmilik=' + val;
    f.submit();
  }

  function doselectOrder() {
    var v = $('#order').val();
    if (v == 'MK' || v == '') {

      //$('.mahkamah').show();
    } else {
      //$('.mahkamah').hide();
    }
  }

  function doReload(event, form, value) {
    doBlockUI();
    var q = $(form).formSerialize();
    var url = form.action + '?' + event + '&idHakmilik=' + value;
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

  function doValid()
  {
      if(${fn:startsWith(actionBean.permohonan.kodUrusan.kod,'PLB')}){  
          if (($('#noMahkamah').val() == '')||($('#namaMahkamah').val() == '')||($('#tkhPerintah').val() == '')||($('#ulasan').val() == '')){
               alert("Pastikan anda masukkan No Mahkamah, Mahkamah, Tarikh Perintah dan juga Sebab Perintah"); 
               return false;
          }
          
          else
              return true;
              
      }
      
      else if(${fn:startsWith(actionBean.permohonan.kodUrusan.kod,'KV')}){       
          if (($('#ulasan').val() == '')){
              alert("Sila Masukkan Sebab Kaveat");
              return false;
          }
          
          else 
              return true;
      }  
      
      else
           return true;
       
  }


  function doCalcEndDate(id) {
    var hari = parseInt($('.hari').val(), 10);
    var bln = parseInt($('.bulan').val(), 10);
    var thn = parseInt($('.tahun').val(), 10);
    var kodUrusan = $('#kodUrusan').val();

    if ($('#' + id).val() == '') {
      return;
    }

    //Added by Aizuddin to change PHMB tempoh checking
    if (isNaN(hari) && isNaN(bln) && isNaN(thn) && kodUrusan != 'PHMB') {
      alert('Sila Masukan Tempoh.');
      $('#' + id).val('');
      return;
    }
    if (hari == '0' && bln == '0' && thn == '0')
      return;
    var startDate = $('#' + id).val();
    //manual process :: should find conclusion on this
    var y = parseInt(startDate.substring(6, 10), 10);
    var m = parseInt(startDate.substring(3, 5), 10);
    var d = parseInt(startDate.substring(0, 2), 10);

    if (!isNaN(hari))
    {
      d = d + hari;
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

    if (!isNaN(thn))
    {
      y = y + thn;
      if (d == 0) {
        m = m - 1;
      }
    }

    if (d == 0 && m == 0) {
      y = y - 1;

    }

    //y = y + thn;
    var endDate = new Date();
    var s = 1;
    endDate.setDate(d);
    endDate.setMonth(m - 1);
    endDate.setFullYear(y);
    endDate.setDate(endDate.getDate() - s);

    $('#tkhTamat').val(endDate.format("dd/mm/yyyy"));
  }


  function calculateDates(id) {
    //        var duration = parseInt(document.getElementById('duration').value);
    //        if(isNaN(duration)){
    //            alert('Invalid duration');
    //            return false;
    //        }

    var hari = parseInt($('.hari').val(), 10);
    var bln = parseInt($('.bulan').val(), 10);
    var thn = parseInt($('.tahun').val(), 10);
    if (isNaN(hari)) {
      alert('Tempoh Hari tidak betul.');
      $('.hari').focus();
      return false;
    }
    if (isNaN(bln)) {
      alert('Tempoh bulan tidak betul.');
      $('.bulan').focus();
      return false;
    }
    if (isNaN(thn)) {
      alert('Tempoh tahun tidak betul.');
      $('.tahun').focus();
      return false;
    }

    var startDate = $('#' + id).val();

    var endDt = startDate.split("/");
    var date = endDt[0];
    var month = endDt[1];
    var year = endDt[2];
    //alert(year);
    //alert(date);
    //alert(month);

    if (parseInt(month) == 2) {//feb month
      var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));

      if (parseInt(date) > 29 || (parseInt(date) == 29 && !isleap)) {
        //alert("February " + year + " doesn't have " + date + " days!");
        //return false;
      }
    }


    var myDate = new Date(year, month - 1, date - 1);
    myDate.setMonth(myDate.getMonth() + bln);
    myDate.setDate(myDate.getDate() + hari);
    myDate.setYear(myDate.getYear() + thn);
    var dd = new Date(myDate.getYear(), myDate.getMonth(), myDate.getDate());
    newDate = append(dd.getDate()) + "/" + append((dd.getMonth() + 1)) + "/" + dd.getFullYear();
    //alert(newDate);
    return newDate;
  }

  function append(x) {
    return(x < 0 || x > 9 ? "" : "0") + x
  }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="listUtil" />
<s:messages/>
<s:errors/>
<s:form beanclass="etanah.view.daftar.Kaveat">
  <%--<s:text name="permohonanRujukanLuar.idRujukan"/>--%>
  <s:hidden name="kodUrusan" id="kodUrusan" value="${actionBean.permohonan.kodUrusan.kod}"/>
  <div class="subtitle">
    <fieldset class="aras1">
      <legend>
        Kemasukan Borang
      </legend>
      <br/>
      <p><font color="black" size="2"><b>*Sila isi yang diperlukan sahaja.</b></font></p>

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
        <s:select name="idHakmilik" onchange="doReload('reloadHakmilik', this.form,this.value);">
          <c:forEach items="${actionBean.senaraiHakmilik}" var="item">
            <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
              ${item.hakmilik.idHakmilik} (${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama})
            </s:option>
          </c:forEach>
        </s:select>
        <font color="red" size="2">*Sila Pilih Hakmilik</font>
      </p>
      <p>
        <label for="nama pihak">Jenis Perintah :</label>
        <s:select name="kodPerintah" id="order" onchange="doselectOrder();" style="width:200px" value="${kodPerintah}">
          <s:option value="">Sila Pilih</s:option>         
          <s:options-collection collection="${listUtil.senaraiKodPerintah}" value="kod" label="nama"/>
        </s:select>
      </p>
      <p>
        <label for="nama pihak">No Fail :</label>
        <s:text name="permohonanRujukanLuar.noFail" style="width:300px"/>
      </p>
      <p>
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PNPA'}">
          <label for="nama pihak">No Surat Amanah :</label>
        </c:if>
        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PNPA'}">
          <label for="nama pihak">No Perintah/Saman Pemula :</label>
        </c:if>
        <s:text name="permohonanRujukanLuar.noRujukan" style="width:300px"/>
      </p>      
      <p class="mahkamah">
        <label for="nama pihak" >No Mahkamah/No Sidang :</label>
        <s:text id="noMahkamah" name="permohonanRujukanLuar.noSidang" style="width:300px"/>
      <c:if test="${fn:startsWith(actionBean.permohonan.kodUrusan.kod,'PLB')}">
            <font color="red" size="2">*Mandatori</font>
      </c:if> 
      </p>
      <p class="mahkamah">
        <label for="nama pihak">Mahkamah/Pej. Tanah Daerah :</label>
        <s:text id="namaMahkamah" name="permohonanRujukanLuar.namaSidang" style="width:420px"/>&nbsp;
      <c:if test="${fn:startsWith(actionBean.permohonan.kodUrusan.kod,'PLB')}">
            <font color="red" size="2">*Mandatori</font>
      </c:if> 
      </p>
      <p>
        <label for="nama pihak">Tarikh Perintah :</label>
        <s:text id="tkhPerintah" name="tkhSidang" class="datepicker2" onblur="editDateBlur(this, 'DD/MM/YYYY')"
                onkeypress="return editDatePre(this, 'DD/MM/YYYY', event)"
                onkeyup="return editDate(this, 'DD/MM/YYYY', event);"/>
      <c:if test="${fn:startsWith(actionBean.permohonan.kodUrusan.kod,'PLB')}">
            <font color="red" size="2">*Mandatori</font>
      </c:if> 
      </p>
      <p>
        <c:if test="${fn:startsWith(actionBean.permohonan.kodUrusan.kod,'KV')}">
          <label>
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'KVSMP'}">
              Sebab Kaveat Dimasukkan :
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'KVSMP'}">
              Sebab Perintah :
            </c:if>          
          </label>          
        </c:if>
        <c:if test="${!fn:startsWith(actionBean.permohonan.kodUrusan.kod,'KV')}">
          <label>
            Sebab Perintah :
          </label>
        </c:if> 
        <s:textarea id="ulasan" name="permohonanRujukanLuar.ulasan" rows="10" cols="50"/>
        <c:if test="${fn:startsWith(actionBean.permohonan.kodUrusan.kod,'KV')}">
            <font color="red" size="2">*Mandatori</font>
        </c:if>
      </p>
      <p>
        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PHMB' || actionBean.permohonan.kodUrusan.kod ne 'PMHUN'}">
          <label for="nama pihak">Tempoh :</label>
          <s:text name="tahun" class="tahun" value="${thn}" onchange="doCalcEndDate('tkhKuatKuasa');"/> tahun &nbsp;
          <s:text name="bulan" class="bulan" value="${bln}" onchange="doCalcEndDate('tkhKuatKuasa');"/> bulan &nbsp;
          <s:hidden name="hari" class="hari" value="${hari}" onchange="doCalcEndDate('tkhKuatKuasa');"/>
        </c:if>
      </p>
      <p class="mahkamah">
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PNPA'}">
          <label for="nama pihak">Tarikh Rujukan :</label>
          <s:text name="tkhRujukan" id="tkhKuatKuasa" class="datepicker" onchange="doCalcEndDate(this.id);"
                  onblur="editDateBlur(this, 'DD/MM/YYYY')"
                  onkeypress="return editDatePre(this, 'DD/MM/YYYY', event)"
                  onkeyup="return editDate(this, 'DD/MM/YYYY', event);"/>
        </c:if>
        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PNPA'}">
          <label for="nama pihak">Tarikh Kuatkuasa :</label>
          <s:text name="tkhKuatKuasa" id="tkhKuatKuasa" class="datepicker" onchange="doCalcEndDate(this.id);" 
                  onblur="editDateBlur(this, 'DD/MM/YYYY')"
                  onkeypress="return editDatePre(this, 'DD/MM/YYYY', event)"
                  onkeyup="return editDate(this, 'DD/MM/YYYY', event);"/>
        </c:if>
      </p>

      <p class="mahkamah">
        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PHMB' || actionBean.permohonan.kodUrusan.kod ne 'PMHUN'}">
          <label for="nama pihak">Tarikh Tamat :</label>
          <s:text name="tkhTamat" id="tkhTamat" class="datepicker"
                  onblur="editDateBlur(this, 'DD/MM/YYYY')"
                  onkeypress="return editDatePre(this, 'DD/MM/YYYY', event)"
                  onkeyup="return editDate(this, 'DD/MM/YYYY', event);"/>
        </c:if>
      </p>
      <p>
        <label>&nbsp;</label>
        <s:button name="savePerintah" value="Simpan" class="btn" onclick="if (doValid()) doSubmit(this.form, this.name, 'page_div');"/>
      </p>
    </fieldset>
  </div>
</s:form>