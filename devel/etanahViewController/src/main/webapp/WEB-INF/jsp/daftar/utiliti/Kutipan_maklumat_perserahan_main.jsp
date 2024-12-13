<%-- 
    Document   : kutipan_data_main
    Created on : Sep 20, 2013, 11:48:18 AM
    Author     : ei
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf"%>
<!DOCTYPE html>
<title>e-Tanah Kutipan Data</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
  $(document).ready(function() {
    $("#kodBPM").val(${actionBean.bpm});

    $('#sahkan').click(function() {
      doBlockUI();
    });

    $('#seterusnya').click(function() {
      doBlockUI();
    });

    filterKodBPM($('#kodDaerah').val());

    $("#namaDaerah").val($("#kodDaerah").val());
    $("#namaDaerah").change(function() {
      var valueDaerah = $("#namaDaerah").val();
      $("#kodDaerah").val(valueDaerah);
    });
    $("#kodDaerah").blur(function() {
      var valueDaerah = $("#kodDaerah").val();
      $("#namaDaerah").val(valueDaerah);
    });
    $("#bpm").blur(function() {
      var valueBPM = $("#bpm").val();
      $("#namaBPM").val(valueBPM);
    });
    $("#namaBPM").change(function() {
      var valueBPM = $("#namaBPM").val();
      $("#kodBPM").val(valueBPM);
    });
    $("#namaJenisHakmilik").change(function() {
      var valueJenisHakmilik = $("#namaJenisHakmilik").val();
      $("#kodJenisHakmilik").val(valueJenisHakmilik);
    });
    $("#kodJenisHakmilik").blur(function() {
      var valueJenisHakmilik = $("#kodJenisHakmilik").val();
      $("#namaJenisHakmilik").val(valueJenisHakmilik);
    });
  });

  function filterKodBPM(f) {
    var kodDaerah = f
//    $.post('${pageContext.request.contextPath}/daftar/utiliti/kutipanData?senaraiKodBPMByDaerah&kodDaerah=' + kodDaerah + '&popup=true',
    $.post('${pageContext.request.contextPath}/daftar/utiliti/KemaskiniPerserahanHakmilik?cariBPM&kodDaerah=' + kodDaerah + '&popup=true&bpm=' + $('#bpm').val(),
            function(data) {
              if (data != '') {
                $('#partialKodBPM').html(data);
//                $('#namaBPM').html(data);
              }
            }, 'html');
  }
  
  

//  function validateNoHakmilik(idx) {
//    //  DO CHECK NO HAKMILIk
//    var dr = $("#noHakmilikSiriDari" + idx).val();
//    var ke = $("#noHakmilikSiriHingga" + idx).val();
//    var kn = $("#kodNegeri").val();
//    var kd = $("#kodDaerah").val();
//    var bpm = $("#noHakmilikSiriHingga" + idx).val();
//    var jhm = $("#kodJenisHakmilik").val();
//    alert("dari : " + dr);
//    alert("hingga : " + ke);
//    frm = this.form;
//    if (dr === null || dr === "" || ke === null || ke === "")
//      return;
//    dr = dr.toUpperCase();
//    ke = ke.toUpperCase();
//    $.get("${pageContext.request.contextPath}/daftar/utiliti/kutipanData?doCheckNoHakmilik&noHmDr=" + dr +
//            "&noHmKe=" + ke + "&kodNegeri=" + kn + "&kodDaerah=" + kd + "&bpm=" + bpm + "&kodJenisHakmilik=" + jhm,
//            function(data) {
//              alert(data);
//              if (data === '1') {
//                alert("Hakmilik Siri adalah sah!");
//              } else if (data === '2') {
//                alert("Terdapat hakmilik yang mempunyai versi bukan 0!");
//              } else if (data === '3') {
//                alert("Terdapat hakmilik yang telah dibatalkan!");
//              } else if (data === "4") {
//                alert("ID Hakmilik Siri " + (idx + 1) + " tidak sah!");
//              } else if (data === "5") {
//                alert("Terdapat ID Hakmilik yang batal dalam Siri " + (idx + 1) + "!");
//              }
//            });
//  }

  function checkNoHakmilik(f) {
    // auto insert 8 no in no hakmilik
    var noHm = zeroPad(f, 8);
    $('#noHakmilikSiriDari0').val(noHm);
    $('#idHakmilikSiriDari0').val('');
    $('#idHakmilikSiriHingga0').val('');
  }

  function checkNoHakmilik1(f) {
    // auto insert 8 no in no hakmilik
    var noHm = zeroPad(f, 8);
    $('#noHakmilikSiriHingga0').val(noHm);
  }

  function checkNoHakmilik2(f) {
    // auto insert 8 no in no hakmilik
    $('#idHakmilik0').val("");
//    var noHm = zeroPad(RemoveAlphabet(f), 8); // comment out sbb user boleh masukkan abjad dan simbol
    var noHm = zeroPad(f, 8);
    $('#noHakmilik').val(noHm);
  }

  function clearhakmilik(f) {
    $('#noHakmilik').val("");
  }


  function checkIdHakmilik() {
    // auto clear text 
    $('#noHakmilikSiriDari0').val("");
    $('#noHakmilikSiriHingga0').val("");
    $('#kodBPM').val("");
    $('#bpm').val("");
    $('#namaBPM').val("0");
    $('#kodJenisHakmilik').val("");
    $('#namaJenisHakmilik').val("");
  }

  function zeroPad(num, count) {
    // auto insert '0000' in no hakmilik
    var numZeropad = num + '';
    while (numZeropad.length < count) {
      numZeropad = "0" + numZeropad;
    }
    return numZeropad;
  }

  function appendAuto(val, id) {
    // AUTO INSERT NO HAKMILIK HINGGA
    val = RemoveAlphabet(val);
    var bil = $('#bilHakmilik' + id).val();
    var len = val.length;
    var intIndex = 0;
    var pre = "";
    if (val != '') {
      val = val.toUpperCase();
      $('#noHakmilikSiriDari' + id).val(val);
      bil = bil - 1;
      if (parseInt(bil, 10) > 0) {
        for (intIndex = len - 1; intIndex >= 0; intIndex--) {
          var c = val.charAt(intIndex);
          if (c >= '0' && c <= '9') {
            pre = c + pre;
          } else {
            break;
          }
        }
        var h = val.substring(0, intIndex + 1); //temp  
        var val2 = parseInt(pre, 10) + parseInt(bil);
        var len = (pre.length - String(val2).length);
        if (String(val2).length < pre.length) {
          for (var i = 0; i < len; i++) {
            val2 = "0" + val2;
          }
        }
        h = zeroPad(h + val2, 8);
        if (!isNaN(val2)) {
          $('#noHakmilikSiriHingga' + id).val(h);
        }
      }
    } else {
      $('#noHakmilikSiriHingga' + id).val('');
    }
  }

  function appendAuto2(val, id) {
    // AUTO INSERT ID HAKMILIK HINGGA
    var bil = $('#bilHakmilik' + id).val();
    var len = val.length;
    if (len != 16 && len != 17) {
      alert("Sila masukkan id hakmilik yang betul.");
      $('#idHakmilikSiriDari' + id).val('');
      $('#idHakmilikSiriHingga' + id).val('');
    } else {
      var intIndex = 0;
      var pre = "";
      if (val != '') {
        val = val.toUpperCase();
        $('#idHakmilikSiriDari' + id).val(val);
        bil = bil - 1;
        if (parseInt(bil, 10) > 0) {
          for (intIndex = len - 1; intIndex >= 0; intIndex--) {
            var c = val.charAt(intIndex);
            if (c >= '0' && c <= '9') {
              pre = c + pre;
            } else {
              break;
            }
          }
          var h = val.substring(0, intIndex + 1); //temp  
          var val2 = parseInt(pre, 10) + parseInt(bil);
          var len = (pre.length - String(val2).length);
          if (String(val2).length < pre.length) {
            for (var i = 0; i < len; i++) {
              val2 = "0" + val2;
            }
          }
          h = h + val2;
          if (!isNaN(val2)) {
            $('#idHakmilikSiriHingga' + id).val(h);
          }
        }
      } else {
        $('#idHakmilikSiriHingga' + id).val('');
      }
    }
  }

  function appendAutoAll(intIndex) {
    // AUTO INSERT NO HAKMILIK IF BIL HAKMILIK IS CHANGED
    var val = $('#noHakmilikSiriDari' + intIndex).val();
    var val2 = $('#idHakmilikSiriDari' + intIndex).val();
    if (val != '') {
      appendAuto(val, intIndex);
    }
    if (val2 != '') {
      appendAuto2(val2, intIndex);
    }
  }

  function nonNumber(elmnt, inputTxt) {
    var a = document.getElementById('bilHakmilik0');
    if (isNaN(a.value)) {
//      alert("Nombor tidak sah.Sila masukkan Semula");
//      $("#bilHakmilik0").focus();
      elmnt.value = RemoveNonNumeric(inputTxt);
      return;
    }
  }

  function RemoveNonNumeric(strString) {
    var strValidCharacters = "123456789";
    var strReturn = "";
    var strBuffer = "";
    var intIndex = 0;
    // Loop through the string
    for (intIndex = 0; intIndex < strString.length; intIndex++) {
      strBuffer = strString.substr(intIndex, 1);
      // Is this a number
      if (strValidCharacters.indexOf(strBuffer) > -1) {
        strReturn += strBuffer;
      }
    }
    return strReturn;
  }

  function RemoveAlphabet(strString) {
    // REMOVE OTHER THEN NUMBER
//    alert("masuk");
    var strValidCharacters = "0123456789";
    var strReturn = "";
    var strBuffer = "";
    var intIndex = 0;
    // Loop through the string
    for (intIndex = 0; intIndex < strString.length; intIndex++) {
      strBuffer = strString.substr(intIndex, 1);
      // Is this a number      
      if (strValidCharacters.indexOf(strBuffer) > -1) {
        strReturn += strBuffer;
      }
    }
    return strReturn;
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
<script>
  function clearForm(f) {
    var frm = document.forms.form1;
  <c:choose>
    <c:when test="${actionBean.kelompok eq 'true'}">
      var v = "kutipanBerkelompok";
    </c:when>
    <c:otherwise>
        var v = "kutipanSingle";
    </c:otherwise>
  </c:choose> 
        var url = '${pageContext.request.contextPath}/daftar/utiliti/KemaskiniPerserahanHakmilik?' + v;
        frm.action = url;
        frm.submit();
      }
</script>

<div class="kutipdata">
  <s:messages />
  <s:errors />
  <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
  <s:form beanclass="etanah.view.daftar.utiliti.KemaskiniPerserahanHakmilikActionBean" name="form1">    
    <fieldset class="aras1">
      <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
      <s:hidden name="kelompok" id="kelompok" />
      <legend>Utiliti Kemaskini Permohonan Hakmilik </legend>
      <br>
      <c:choose>
        <c:when test="${actionBean.kelompok eq 'true'}"> 

          <p>
            <label>ID Hakmilik :</label>
            Mula -&nbsp;
            <s:text name="idHakmilikSiriDari[0]" id="idHakmilikSiriDari0" 
                    onblur="checkIdHakmilik();this.value=this.value.toUpperCase();" size="22" 
                    onchange="appendAuto2(this.value, '0');"/>
            &nbsp;Hingga -
            <s:text name="idHakmilikSiriHingga[0]" id="idHakmilikSiriHingga0" 
                    onblur="checkIdHakmilik();this.value=this.value.toUpperCase();" size="22"
                    onchange="appendAuto2(this.value, '0');"/> 
          </p>
        </c:when>
        <c:otherwise>
          <p>
            <label>ID Hakmilik :</label>
            <s:text name="idHakmilik" id="idHakmilik0" size="20" onblur="javascript:nonNumber(this, this.value);" onchange="clearhakmilik(this.value);"/>
          </p>
        </c:otherwise>          
      </c:choose>      
      <p>
        <label>&nbsp;</label>
            
        <c:choose>
          <c:when test="${actionBean.kelompok eq 'true'}">
            <s:submit name="seterusnyaBerkelompok" value="Seterusnya" class="btn" id="seterusnya" />&nbsp; 
            <s:submit name="doCheckNoHakmilik" value="Sahkan" class="btn" id="sahkan" />&nbsp; 
            <!--<input type="button" value="Sahkan" onclick="validateNoHakmilik(0)" class="btn" />&nbsp;-->
          </c:when>
          <c:otherwise>
            <s:submit name="seterusnya" value="Seterusnya" class="btn" id="seterusnya" />&nbsp; 
          </c:otherwise>
        </c:choose>     
        <s:button name="reset" value="Isi Semula" class="btn" onclick="clearForm(this.form);"/>
      </p>
      <br>
    </fieldset>
  </s:form>
  
</div>