
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<c:set var="nextStep" value="Step2"/>        
<c:set var="action" value="/daftar/carian_strata"/>
<c:set var="word" value="Langkah 1"/>

<c:if test="${ !empty tanpaBayaran}">
    <c:if test="${ !empty SSHMA}">
        <c:set var="nextStep" value="Step3a"/>
    </c:if>
    <c:if test="${empty SSHMA}">
        <c:set var="nextStep" value="Step2"/>
    </c:if>
    <%--<c:set var="action" value="/daftar/carian_tanpa_bayaran"/>--%>
    <c:set var="action" value="/daftar/carian_strata"/>
    <c:set var="word" value="Langkah 1"/>
</c:if>

<script>
    function validateHakmilik(idxHakmilik) {
        var val = $("#hakmilik" + idxHakmilik).val();
        frm = this.form;
        if (val == null || val == "")
            return;
        val = val.toUpperCase();
        $("#hakmilik" + idxHakmilik).val(val);
        <c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >
          if ($("#hakmilik${i - 1}").val() == val && (idxHakmilik !=${i-1})) {
              alert('Hakmilik telah dipilih.');
              $("#hakmilik" + idxHakmilik).val('');
              return;
          }
    </c:forEach>

          //utk kod urusan CRHMB, takyah check status hakmilik 'B'

          var url = "${pageContext.request.contextPath}/daftar/check_idhakmilik?doCheckHakmilikUtilitiStrata&idHakmilik=" + val;

 
          $.get(url,
          function(data) {
              if (data == '1') {
                  //               alert("Hakmilik tersebut mempunyai sekatan. Sila klik butang papar untuk melihat sekatan kepentingan terbabit.");
                  //            $("#msg" + idxHakmilik).html("Cukai Hakmilik telah dijelaskan. (" +
                  //                        "<a href=\"javascript:void(0);\" onclick=\"popupHakmilikDetails(" + idxHakmilik + ");\">Papar</a>)");
                  //$("#msg" + idxHakmilik).html("OK");                  
                  $('#Step4').removeAttr("disabled");
              } else if (data == '0') {
                  // disable if invalid Hakmilik 
                  // $("#collectPayment").attr("disabled", "true");
                  $("#hakmilik" + idxHakmilik).val("");
                  alert("ID Hakmilik " + val + " tidak wujud!");
                  // $("#msg" + idxHakmilik).html("<font color=red>ID Hakmilik " + val + " tidak wujud!</font>");
              } else if (data == '2') {
                  // disable if invalid Hakmilik 
                  // $("#collectPayment").attr("disabled", "true");
                  //$("#hakmilik" + idxHakmilik).val("");
                  //alert("Cukai untuk Hakmilik " + val + " masih belum dijelaskan!");
                  $('#Step4').removeAttr("disabled");
              } else if (data == '3') {
                  //$("#hakmilik" + idxHakmilik).val("");
                  //alert("Status hakmilik Provisional. Tiada Urusniaga / Kaveat Persendirian / Lien dibenarkan.");
              } else if (data.charAt(0) == '4') {
    <c:if test = "${salinan ne 'true'}">
                                $("#hakmilik" + idxHakmilik).val("");
                                var str = "Hakmilik " + val + " telah dibatalkan.";
                                if (data.substring(2).length > 0)
                                    str += " Hakmilik sambungan ialah " + data.substring(2) + ".";
                                alert(str);
                                $("#msg" + idxHakmilik).html(str);
    </c:if>
                                } else if (data == '5') {
                                    $("#hakmilik" + idxHakmilik).val("");
                                    alert("ID Hakmilik " + val + " masih berdaftar!");
                                    $("#hakmilik" + idxHakmilik).focus();
                                }else if (data == '11') {
                                    $("#hakmilik" + idxHakmilik).val("");
                                    alert("Hakmilik " + val + " telah ditukar ganti.");
                  
                                }
                                else {
                                    //alert("Unknown reply: " + data);

                                }
                            });
                        }

                        function validateHakmilikBersiri(idx) {
                            var dr = $("#idHakmilikSiriDari" + idx).val();
                            var ke = $("#idHakmilikSiriKe" + idx).val();
                            var kodSerah = '${actionBean.kodSerah}';
                            var kodUrusan = '${actionBean.urusan}';
                            frm = this.form;
                            if (dr == null || dr == "" || ke == null || ke == "")
                                return;
                            dr = dr.toUpperCase();
                            ke = ke.toUpperCase();
                            $.get("${pageContext.request.contextPath}/daftar/check_siri_idhakmilik?doCheckHakmilik&kodUrusan=${kodUrusan}&" +
                                "idHakmilikDari=" + dr + "&idHakmilikKe=" + ke + "&kodSerah=" + kodSerah + "&kodUrusan=" + kodUrusan,
                            function(data) {
                                if (data == '1') {
                                    alert("ID Hakmilik Siri " + (idx + 1) + " adalah sah!");
                                    $('#Step4').removeAttr("disabled");
                                }
                                else if (data == '0') {
                                    // disable if invalid Hakmilik
                                    //$("#Step5").attr("disabled", "true");
                                    //$("#idHakmilikSiriDari" + idx).val("");
                                    //$("#idHakmilikSiriKe" + idx).val("");
                                    alert("Terdapat ID Hakmilik yang tidak wujud dalam Siri " + (idx + 1) + "!");
                                }
                                else if (data == '2') {
                                    alert("ID Hakmilik Siri " + (idx + 1) + " adalah sah!");
                                    // disable if invalid Hakmilik
                                    //$("#collectPayment").attr("disabled", "true");
                                    //$("#idHakmilikSiriDari" + idx).val("");
                                    //$("#idHakmilikSiriKe" + idx).val("");
                                    //alert("Terdapat cukai dalam Siri " + idx + " yang masih belum dilunaskan!");
                                } else if (data == "3") {
                                    alert("ID Hakmilik Siri " + (idx + 1) + " tidak sah!");
                                } else if (data == "7") {
    <c:if test = "${salinan ne 'true'}">
                  alert("Terdapat ID Hakmilik yang batal dalam Siri " + (idx + 1) + "!");
    </c:if>
              }
          });
      }

      function validate() {
          var bil = document.getElementById('bilHakmilik');
          if (bil.value <= 0) {
              alert("Bilangan Hakmilik mestilah tidak kurang daripada 0");
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

      function appendAuto(val, id) {
          var bil = $('#bilHakmilikBersiri' + id).val();
          var len = val.length;
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

                  var h = val.substring(0, intIndex + 1);//temp
                  var val2 = parseInt(pre, 10) + parseInt(bil);
                  var len = (pre.length - String(val2).length);
                  if (String(val2).length < pre.length) {
                      for (var i = 0; i < len; i++) {
                          val2 = "0" + val2;
                      }
                  }

                  h = h + val2;
                  if (!isNaN(val2)) {
                      $('#idHakmilikSiriKe' + id).val(h);
                  }
              }
          } else {
              $('#idHakmilikSiriKe' + id).val('');
          }
      }

      function popupHakmilikDetails(idx) {
          //alert(id);
          var idH = $('#hakmilik' + idx).val();
          var url = "${pageContext.request.contextPath}/hakmilik?popupHakmilikDetail&idHakmilik=" + idH;
          window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
      }

      function appendAutoAll(intIndex) {
          var val = $('#idHakmilikSiriDari' + intIndex).val();
          if (val != '') {
              appendAuto(val, intIndex);
          }
      }
      function selectAll(a, clazz) {
          var len = $('.' + clazz).length;
          for (var i = 0; i < len; i++) {
              var id = clazz + i;
              var c = document.getElementById(id);
              c.checked = a.checked;
          }
      }
       
      function test(a,b){
     
          var i = a;
 
          var string1 = "hakmilik";
          var string2 = i.toString();
          var combine = string1.concat(string2);

          document.getElementById(combine).value = b;
       
      
      }
       
      function test2(a,b){
     
          var i = a;
 
          var string1 = "hakmilik";
          var string2 = i.toString();
          var combine = string1.concat(string2);

          document.getElementById(combine).value = "";
       
      
      }
       
</script>

<script type="text/javascript">
    $(document).ready(function() {
        dialogInit('Carian Hakmilik');
        <c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >
          $("#hakmilik${i - 1}").change(function() {
              $(".hmBersiri").val(""); // clear text for hakmilik bersiri
              validateHakmilik(${i - 1});
          });
          $("#hakmilik${i - 1}").keyup(function() {
              closeDialog();
          });
    </c:forEach>
      });
</script>

<p class=title>${word}: Tentukan Hakmilik-Hakmilik Terlibat</p>
<s:messages />
<s:errors />

<s:form action="${action}" id="pilih_hakmilik">
    <span class=instr>* Sila gunakan carian ini dengan menggunakan ID hakmilik induk untuk memaparkan semua ID hakmilik strata yang terlibat.</span><br/>
    <p>
        <label for="bilHakmilik">ID Hakmilik Induk:</label>
        <s:text name="idHakmilikInduk" id="idHakmilikInduk" size="20" onblur="this.value = this.value.toUpperCase();"/>
        <s:submit name="cariHakmilikStrata" value="Cari" class="btn"/>
    </p>
    <div class="content" align="center">
        <legend>ID Hakmilik Strata Yang Belum Ditukarganti</legend>
        <display:table class="tablecloth" name="${actionBean.listHakmilikStrata}"
                       cellpadding="0" cellspacing="0" id="line" pagesize="10"  requestURI="/daftar/carian_strata" >
            <display:column title="Bil" sortable="true"><div align="right">${line_rowNum}</div></display:column>
            <display:column property="idHakmilik" title="ID Hakmilik Strata"/>
        </display:table>


        <c:if test="${fn:length(actionBean.listHakmilikStrataversi1) > 0}">
            <br><br>
            <legend>ID Hakmilik Strata Yang Sudah Ditukarganti</legend>
            <display:table class="tablecloth" name="${actionBean.listHakmilikStrataversi1}"
                           cellpadding="0" cellspacing="0" id="line" pagesize="10"  requestURI="/daftar/carian_strata" >
                <display:column title="Bil" sortable="true"><div align="right">${line_rowNum}</div></display:column>
                <display:column property="idHakmilik" title="ID Hakmilik Strata"/>
            </display:table>
        </c:if>

        <c:if test="${fn:length(actionBean.listHakmilikStrataProv) > 0}">
            <br><br>
            <display:table class="tablecloth" name="${actionBean.listHakmilikStrataProv}"
                           cellpadding="0" cellspacing="0" id="line" pagesize="10"  requestURI="/daftar/carian_strata" >
                <display:column title="Bil" sortable="true"><div align="right">${line_rowNum}</div></display:column>
                <display:column title="Bangunan Sementara">${line.idHakmilikInduk} - ${line.noBangunan}</display:column>
                <c:if test="${line.noVersiDhde == 0}">
                <display:column title="Status Tukarganti">Belum</display:column>
                </c:if>
                <c:if test="${line.noVersiDhde != 0}">
                <display:column title="Status Tukarganti">Selesai</display:column>
                </c:if>
            </display:table>
        </c:if>
    </div>
    <fieldset class="aras1">
        <legend>ID Hakmilik Strata</legend>
        <span class=instr>* Sila gunakan carian ini dengan menggunakan ID hakmilik strata.</span><br/>
        <p>
            <label for="bilHakmilik">Bil. Hakmilik:</label>
            <s:text name="bilHakmilik" id="bilHakmilik" size="4" onblur="javascript:nonNumber(this, this.value);"/> atau kurang
            <s:submit name="Stepkemaskini" value="Kemaskini" class="btn" onclick="return validate()"/>
        </p>
        <div align="center">
            <table border=0 class="tablecloth"><tr>
                    <c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >
                        <th align=right>
                            ID Hakmilik Strata ${i}:
                        </th>
                        <td align=left>

                            <s:text name="hakmilikPermohonan[${i - 1}].idHakmilik" id="hakmilik${i - 1}"  size="37"
                                    onkeyup="this.value = this.value.toUpperCase();"  onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikStrata');"/>&nbsp;

                        </td>
                        <c:if test="${i % 3 == 0}" >
                        </tr>
                    </c:if>
                </c:forEach>
            </table>

            <c:if test="${fn:length(actionBean.listHakmilikStrataProv) > 0}">
                <table border=0 class="tablecloth"><tr>
                        <c:set var="i" value="1" />
                        <c:forEach items="${actionBean.listHakmilikStrataProv}" var="line1">
                            <c:if test="${line1.noVersiDhde eq '0'}">
                                <th align=right>
                                    ID Hakmilik Bangunan Sementara ${line1.noBangunan}:
                                </th>
                                <td align=left>

                                    <s:text name="idHakmilikProv${i}" value="${line1.idHakmilikInduk}0${line1.noBangunan}" size="25"/>&nbsp;

                                </td>
                                <c:if test="${i % 3 == 0}" >
                                </tr>
                            </c:if>
                            <c:set var="i" value="${i+1}" />
                        </c:if>
                    </c:forEach>
                </table>
            </c:if>

        </div>
    </fieldset>
    <br/>
    <fieldset class="aras1">
        <p>
            <label>&nbsp;</label>
            <s:submit name="Step1" value="Batal" id="Step1" class="btn"/>
            <s:button name="" value="Isi Semula" class="btn" onclick="clearText('pilih_hakmilik');"/>
            <c:if test="${fn:length(actionBean.listHakmilikStrata) > 0}">
                <s:submit name="Step2" value="Seterusnya" class="btn" id="Step4"/>
            </c:if>
            <c:if test="${fn:length(actionBean.listHakmilikStrata) == 0}">
                <s:submit name="Step2" value="Seterusnya" class="btn" id="Step4"/>
            </c:if>

        </p>
    </fieldset>
</s:form>
