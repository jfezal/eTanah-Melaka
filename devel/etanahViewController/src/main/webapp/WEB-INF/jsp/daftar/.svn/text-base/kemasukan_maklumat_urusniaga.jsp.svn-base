<%-- 
    Document   : kemasukan_maklumat_urusniaga
    Created on : 16-Oct-2009, 22:44:27
    Author     : md.nurfikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
  $(document).ready(function() {


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

    var val = parseFloat($('#pjanjianAmaun').val());
//    var amaun = cf(val);
//      amaun = cf2(amaun);
//      $('#pjanjianAmaun').val(amaun);
//    if (val != '' && val != '0') {
//      convert(val, 'pjanjianAmaun');
//      val = $('#pjanjianAmaun').val();
//      doCalculateDutiStem('pjanjianDutiSetam', val);
//    } else {
////            val = parseFloat(val);
//      var amaun = cf(val);
//      amaun = cf2(amaun);
//      $('#pjanjianAmaun').val(amaun);
//    }
  });

  function doCalculateDutiStem(id, amt) {
    var _q = amt.indexOf(",");
    if (_q > 0) {
      amt = amt.replace(/,/g, "");
    }
    //alert(amt);
    var tmp = parseFloat(amt);
    if (tmp > 100000) {
      tmp = tmp * 0.02;
    } else {
      tmp = tmp * 0.01;
    }
    //convert(tmp, id);
    //$('#'+id).val(tmp);
//        var amaun = cf(tmp);
//        amaun = cf2(amaun);
    $('#' + id).val(tmp.toFixed(2));
  }

  function traslateToCurrency(val, id) {
    val = cf(val);
    alert(val);
    val = cf2(val);
    //alert(val);
    $('#' + id).val(val);
  }

  function cf(amount)
  {
    var t = amount;
    //alert("q" + t.indexOf(","));
    //var q = t.indexOf(",");
    //if(q > 0){
    //    amount = t.replace (/,/g, "");
    //}
    var i = parseFloat(amount);
    if (isNaN(i)) {
      i = 0.00;
    }
    var minus = '';
    if (i < 0) {
      minus = '-';
    }
    i = Math.abs(i);
    i = parseInt((i + .0005) * 100);
    i = i / 100;
    s = new String(i);
    if (s.indexOf('.') < 0) {
      s += '.00';
    }
    if (s.indexOf('.') == (s.length - 2)) {
      s += '0';
    }
    s = minus + s;
    return s;
  }

  function cf2(amount)
  {
    var delimiter = ","; // replace comma if desired
    var a = amount.split('.', 2)
    var d = a[1];
    var i = parseInt(a[0]);
    if (isNaN(i)) {
      return '';
    }
    var minus = '';
    if (i < 0) {
      minus = '-';
    }
    i = Math.abs(i);
    var n = new String(i);
    var a = [];
    while (n.length > 3)
    {
      var nn = n.substr(n.length - 3);
      a.unshift(nn);
      n = n.substr(0, n.length - 3);
    }
    if (n.length > 0) {
      a.unshift(n);
    }
    n = a.join(delimiter);
    if (d.length < 1) {
      amount = n;
    }
    else {
      amount = n + '.' + d;
    }
    amount = minus + amount;
    return amount;
  }
</script>
<s:form beanclass="etanah.view.daftar.MaklumatUrusniagaActionBean">
  <s:messages/>
  <s:errors/>
  <s:hidden name="urusan.idUrusan"/>
  <c:set var="disabled" value="false"/>
  <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMT' || actionBean.permohonan.kodUrusan.kod eq 'GD'}">
    <c:set var="disabled" value="false"/>
  </c:if>
  <div class="subtitle">
    <fieldset class="aras1">
      <legend>Maklumat Urusniaga</legend>
      <br>
      <p>
        <label for="Amaun">Amaun (RM) :</label>
        <s:text name="urusan.perjanjianAmaun" id="pjanjianAmaun" 
                onblur="convert(this.value, this.id);doCalculateDutiStem('pjanjianDutiSetam', this.value);"
                formatPattern="#,###.00" disabled="${disabled}" size="35"/>
      </p>
      <p>
        <label for="Duti">Duti Setem (RM) :</label>
        <s:text name="urusan.perjanjianDutiSetem" id="pjanjianDutiSetam" 
                onblur="convert(this.value, this.id); " formatPattern="#,##0.00" size="35"/>
      </p>
      <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'JDGD'}">
        <p>
          <label for="Mahkamah">No Mahkamah :</label>
          <s:text name="urusan.perjanjianNoRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
        </p>
      </c:if>
      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJTM'}">
          <p>
              <label for="Mahkamah">Nama Mahkamah :</label>
              <s:text name="namaSidang" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
          </p>
      </c:if>
      <p>
        <label for="Mahkamah">No Fail/ID Permohonan :</label>
        <s:text name="noFail" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
      </p>
      <p>
        <label for="Mahkamah">Tarikh Penyaksian :</label>
        <s:text name="trhSaksi" class="datepicker2" disabled="${disabled}" readonly="true"
                formatPattern="dd/MM/yyyy"
                onblur="editDateBlur(this, 'DD/MM/YYYY')"
                onkeypress="return editDatePre(this, 'DD/MM/YYYY', event)"
                onkeyup="return editDate(this, 'DD/MM/YYYY', event);" size="35"/>
      </p>
      <br>
      <p>
        <label>&nbsp;</label>
        <s:button name="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
      </p>
      <br>
      <br>
    </fieldset>
  </div>

</s:form>
