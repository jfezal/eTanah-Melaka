
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">

<script>
  function validateHakmilik(idxHakmilik) {
        var val = $("#hakmilik" + idxHakmilik).val();
        //var kod = $("#kodUrusan").val();

        //alert("hakmilik:"+val);
        //frm = this.form;
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

      $.get("${pageContext.request.contextPath}/utiliti/validateHakmilik?validateIdHakmilik&idHakmilik=" + val,
              function(data) {
                 // alert("idHakmilik:"+val);
                  //alert("data:"+data);
                if (data == 'true') {
                  $('#next').removeAttr("disabled");
                }else{
                  $("#hakmilik" + idxHakmilik).val("");
                  
                  alert("ID Hakmilik " + val + " ini tidak sah untuk kemasukan maklumat Plan");
                   $("#hakmilik" + idxHakmilik).val('');
                  
                  //$('#next').attr("disabled", "true");
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

    function RemoveNonNumeric(strString) {
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

    function popupHakmilikDetails(idx) {
      //alert(id);
      var idH = $('#hakmilik' + idx).val();
      var url = "${pageContext.request.contextPath}/hakmilik?popupHakmilikDetail&idHakmilik=" + idH;
      window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
    }


</script>

<script type="text/javascript">
  $(document).ready(function() {
     
    var hakmilikArry = [];
    
    dialogInit('Carian Hakmilik');
  <c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >

        $("#hakmilik${i - 1}").change(function() {
          validateHakmilik(${i - 1});
           hakmilikArry += [$("#hakmilik${i - 1}").val()];
           hakmilikArry = hakmilikArry + ",";
            //alert("ID hakmilikArry in for:"+hakmilikArry);
        });
    
    $("#hakmilik${i - 1}").keyup(function() {
      closeDialog();
    });
    $('#next').attr("disabled", "true");
    
   
  </c:forEach>
      
   //for next btn
            
    $("#next").click(function(){
      //alert("ID hakmilikArry:"+hakmilikArry);
        $("#hakmilikArry").val(hakmilikArry);
        //$.get("$//{pageContext.request.contextPath}/utiliti/validateHakmilik?getHakmilik&hakmilikArry=" + hakmilikArry,
//              function(data) {
//                  alert("data"+data);
//
//            });
        
    });
    
      $('input:text').each(function() {
        $(this).focus(function() {
          $(this).addClass('focus')
        });
        $(this).blur(function() {
          $(this).removeClass('focus')
        });
      });
    });

</script>


<s:messages />
<s:errors />

<span class=instr>Masukkan semua ID Hakmilik yang terlibat. Biarkan kosong mana-mana medan
  yang tidak berkaitan.</span><br/>

<s:form action="/utiliti/carianHakmilik" id="pilih_hakmilik">

    <fieldset class="aras1">
    <legend>ID Hakmilik</legend>

    <p>
      <label>Bil. Hakmilik:</label>
      <s:text name="bilHakmilik" id="bilHakmilik" size="4" onblur="javascript:nonNumber(this, this.value);"/> atau kurang
      <s:submit name="Step3" value="Kemaskini" class="btn" onclick="return validate()"/>
    </p>
    <div align="center">
      <table border=0 class="tablecloth"><tr>
          <c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >

            <th align=right>
              ID Hakmilik ${i}:
            </th><td align=left>
              <s:text name="listIDHakmilik[${i - 1}]" id="hakmilik${i - 1}"/>&nbsp;
              <div id="msg${i - 1}"/>
            </td>
            <c:if test="${i % 3 == 0}" >
            </tr>
          </c:if>

        </c:forEach>
      </table>
    </div>
                      
    <p><label>&nbsp;</label>
      <s:button name="" value="Isi Semula" class="btn" onclick="clearText('pilih_hakmilik');"/>
      <s:submit name="next" value="Seterusnya" class="btn" id="next" />
      <s:hidden name="hakmilikArry" id="hakmilikArry" />
    </p>
  </fieldset>

</s:form>
