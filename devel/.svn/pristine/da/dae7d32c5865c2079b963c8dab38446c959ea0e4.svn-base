
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<%--<script type="text/javascript">
// only for demo purposes
$.validator.setDefaults({
      submitHandler: function() {
            alert("submitted!");
      }
});

$.metadata.setType("attr", "validate");

$(document).ready(function() {
      $("#form1").validate();
      $("#fail").validate();
});
</script>--%>
<c:set var="nextStep" value="Step2a"/>        
<c:set var="action" value="/daftar/carian"/>
<c:set var="word" value="Langkah 3"/>

<c:if test="${ !empty tanpaBayaran}">
  <c:if test="${!empty CSBB}">
    <c:set var="nextStep" value="Step3a"/>
  </c:if>
  <c:if test="${empty CSBB}">
    <c:set var="nextStep" value="Step3"/>
  </c:if>
  <c:set var="action" value="/daftar/carian_tanpa_bayaran"/>
  <c:set var="word" value="Langkah 2"/>
</c:if>


<script>
  function validatePerserahan(idxHakmilik) {
    var val = $("#hakmilik" + idxHakmilik).val();
    frm = this.form;
    if (val == null || val == "")
      return;
    val = val.toUpperCase();
    $("#hakmilik" + idxHakmilik).val(val);

  <c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >
    if ($("#hakmilik${i - 1}").val() == val && (idxHakmilik !=${i-1})) {
      alert('Id Perserahan telah dipilih.');
      $("#hakmilik" + idxHakmilik).val('');
      return;
    }
  </c:forEach>

      $.get("${pageContext.request.contextPath}/daftar/check_idpermohonan?doCheckPermohonan&idPermohonan=" + val + '&kodUrusan=${kodUrusan}',
              function(data) {
                if (data == '1') {
                  $("#msg" + idxHakmilik).html("OK");
                } else if (data == '0') {
                  // disable if invalid Hakmilik
                  // $("#collectPayment").attr("disabled", "true");
                  $("#hakmilik" + idxHakmilik).val("");
                  alert("ID Perserahan " + val + " tidak wujud!");
                  // $("#msg" + idxHakmilik).html("<font color=red>ID Hakmilik " + val + " tidak wujud!</font>");
                } else {
                  $("#hakmilik" + idxHakmilik).val("");
                  alert(data);
                }
              });
    }

    function validate() {
      var bil = document.getElementById('bilHakmilik');
      if (bil.value <= 0) {
        alert("Bilangan Perserahan mestilah tidak kurang daripada 0");
        return false;
      }
    }

    function nonNumber(elmnt, inputTxt) {
      var a = document.getElementById('bilHakmilik')

      if (isNaN(a.value)) {
        alert("Nombor tidak sah.Sila masukkan Semula");
        $("#bilHakmilik").focus();
        elmnt.value = RemoveNonNumeric(inputTxt);
        return;
      }
    }

    function RemoveNonNumeric(strString)
    {
      var strValidCharacters = "123456789.";
      var strReturn = "0";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for (intIndex = 0; intIndex < strString.length; intIndex++)
      {
        strBuffer = strString.substr(intIndex, 1);
        // Is this a number
        if (strValidCharacters.indexOf(strBuffer) > -1)
        {
          strReturn += strBuffer;
        }
      }
      return strReturn;
    }
</script>

<script type="text/javascript">
  $(document).ready(function() {
  <c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >
    $("#hakmilik${i - 1}").blur(function() {
      validatePerserahan(${i - 1});
    });
  </c:forEach>
    });

</script>

<p class=title>Langkah 2: Tentukan Perserahan-perserahan Terlibat</p>

<s:messages />
<s:errors />

<span class=instr>Masukkan semua ID Perserahan yang terlibat. Biarkan kosong mana-mana medan
  yang tidak berkaitan. </span><br/>

<%--<s:form action="/daftar/carian" id="pilih_hakmilik">--%>

<s:form action="${action}" id="pilih_hakmilik">

  <fieldset class="aras1">
    <legend>ID Perserahan</legend>

    <p>
      <label for="bilHakmilik">Bil. Perserahan</label>
      <s:text name="bilHakmilik" id="bilHakmilik" size="4" onblur="javascript:nonNumber(this, this.value);"/> atau kurang
      <s:submit name="Step2" value="Kemaskini" class="btn" onclick="return validate()"/>
    </p>
    <div align="center">
      <table border=0 class="tablecloth"><tr>
          <c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >

            <th align=right>
              ID Perserahan ${i}:
            </th><td align=left>
              <s:text name="hakmilikPermohonan[${i - 1}].idPerserahan" id="hakmilik${i - 1}"/>&nbsp;

            </td>
            <c:if test="${i % 3 == 0}" >
            </tr>
          </c:if>

        </c:forEach>
      </table>
    </div>
    <br/>
    <p><label>&nbsp;</label>
      <s:submit name="Step1" value="Kembali" class="btn" />
      <s:submit name="Cancel" value="Batal" class="btn" />
      <s:button name="" value="Isi Semula" class="btn" onclick="clearText('pilih_hakmilik');"/>
      <c:if test="${kodUrusan eq 'SSLSC'}">
        <s:submit name="Step2b" value="Seterusnya" class="btn" id="Step4" />
      </c:if>
      <c:if test="${kodUrusan ne 'SSLSC'}">
        <%--<s:submit name="Step2a" value="Seterusnya" class="btn" id="Step4" />--%>
        <s:submit name="${nextStep}" value="Seterusnya" class="btn" id="Step4" />
      </c:if>

    </p>
  </fieldset>
</s:form>
