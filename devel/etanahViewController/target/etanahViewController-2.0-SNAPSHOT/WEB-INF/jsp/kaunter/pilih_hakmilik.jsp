
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

<script>
function validateHakmilik(idxHakmilik){
    var val = $("#hakmilik" + idxHakmilik).val();
    frm = this.form;
    if (val == null || val == "") return;    
    val = val.toUpperCase();
    $("#hakmilik" + idxHakmilik).val(val);

    <c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >
            if( $("#hakmilik${i - 1}").val() == val && (idxHakmilik!=${i-1})){
                alert('Hakmilik telah dipilih.');
                $("#hakmilik" + idxHakmilik).val('');
                return;
            }
    </c:forEach>

    $.get("${pageContext.request.contextPath}/daftar/check_idhakmilik?doCheckHakmilik&idHakmilik=" + val,
        function(data){
            if(data == '1'){
                $("#msg" + idxHakmilik).html("Cukai Hakmilik telah dijelaskan. (" +
                        "<a href=\"javascript:void(0);\" onclick=\"popupHakmilikDetails(" + idxHakmilik + ");\">Papar</a>)");
            } else if(data =='0'){
                // disable if invalid Hakmilik 
                // $("#collectPayment").attr("disabled", "true");
                $("#hakmilik" + idxHakmilik).val("");
                alert("ID Hakmilik " + val + " tidak wujud!");
                // $("#msg" + idxHakmilik).html("<font color=red>ID Hakmilik " + val + " tidak wujud!</font>");
            } else if(data =='2'){
                // disable if invalid Hakmilik 
                // $("#collectPayment").attr("disabled", "true");
                //$("#hakmilik" + idxHakmilik).val("");
                $("#msg" + idxHakmilik).html("Cukai untuk Hakmilik " + val + " masih belum dijelaskan!");         
                alert("Cukai untuk Hakmilik " + val + " masih belum dijelaskan!");                
            } else if (data == '3'){
                $("#hakmilik" + idxHakmilik).val("");
                alert("Status hakmilik Provisional. Tiada Urusniaga / Kaveat Persendirian / Lien dibenarkan.");
            } else if (data.charAt(0) == '4'){
                <%--alert(data);--%>
            	$("#hakmilik" + idxHakmilik).val("");
                var str = "Hakmilik " + val + " telah dibatalkan.";
                if (data.substring(2).length > 0) str += " Hakmilik sambungan ialah " + data.substring(2) + ".";
                alert(str);
            	$("#msg" + idxHakmilik).html(str);
            } else{
                alert("Unknown reply: " + data);
            }
        });

    // check for kaveat
    $.get("${pageContext.request.contextPath}/daftar/check_kaveat?doCheckKaveat&idHakmilik=" + val,
            function(data){
                if(data == '0'){
                    // nothing to do
                } else if(data =='1'){
                    alert("Hakmilik " + val + " mempunyai Kaveat!");
                }
            });

    //alert(msg);
        
}

function validateHakmilikBersiri(idx){
    var dr = $("#idHakmilikSiriDari" + idx).val();
    var ke = $("#idHakmilikSiriKe" + idx).val();
    frm = this.form;
    if (dr == null || dr == "" || ke == null || ke == "") return;
    $.get("${pageContext.request.contextPath}/daftar/check_siri_idhakmilik?doCheckHakmilik&" +
    	    "idHakmilikDari=" + dr + "&idHakmilikKe=" + ke,
        function(data){
            if(data == '1'){
                alert("ID Hakmilik Siri " + (idx + 1) + " adalah sah!");
            }
            else if(data =='0'){
                // disable if invalid Hakmilik 
                //$("#Step5").attr("disabled", "true");
                //$("#idHakmilikSiriDari" + idx).val("");
                //$("#idHakmilikSiriKe" + idx).val("");
                alert("Terdapat ID Hakmilik yang tidak wujud dalam Siri " + (idx + 1) + "!");
            }
            else if(data =='2'){
                // disable if invalid Hakmilik 
                //$("#collectPayment").attr("disabled", "true");
                //$("#idHakmilikSiriDari" + idx).val("");
                //$("#idHakmilikSiriKe" + idx).val("");                
                alert("Terdapat cukai dalam Siri " + (idx + 1) + " yang masih belum dijelaskan!");
            } else if (data == "3"){
                alert("ID Hakmilik Siri " + (idx + 1) + " tidak sah!");
            }
        });
}

function validate(){
    var bil = document.getElementById('bilHakmilik');
    if(bil.value <=0){
        alert("Bilangan Hakmilik mestilah tidak kurang daripada 0");
        return false;
    }
}

function nonNumber(elmnt,inputTxt){
    var a = document.getElementById('bilHakmilik')

        if (isNaN(a.value)){
            alert("Nombor tidak sah.Sila masukkan Semula");
            $("#bilHakmilik").focus();
            elmnt.value = RemoveNonNumeric(inputTxt);
            return;
        }
}

function RemoveNonNumeric( strString ){
      var strValidCharacters = "123456789.";
      var strReturn = "0";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}

function popupHakmilikDetails(idx){
    //alert(id);
    var idH = $('#hakmilik' + idx).val();
    var url ="${pageContext.request.contextPath}/hakmilik?popupHakmilikDetail&idHakmilik=" + idH;
    window.open(url,"eTanah","status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
}

</script>

<script type="text/javascript">
    $(document).ready(function() {
        dialogInit('Carian Hakmilik');
        <c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >
            $("#hakmilik${i - 1}").change(function(){validateHakmilik(${i - 1});});
            $("#hakmilik${i - 1}").keyup(function(){                
                closeDialog();
            });
        </c:forEach>
        $('input:text').each(function(){
            $(this).focus(function() { $(this).addClass('focus')});
            $(this).blur(function() { $(this).removeClass('focus')});
        });
    });

</script>

<p class=title>URUSAN: ${actionBean.senaraiUrusanLabel}</p>
<p class=title>Langkah 3: Tentukan Hakmilik-Hakmilik Terlibat</p>

<s:messages />
<s:errors />

<span class=instr>Masukkan semua ID Hakmilik yang terlibat. Biarkan kosong mana-mana medan
yang tidak berkaitan. Anda boleh mencampurkan diantara ID Hakmilik yang bersiri dan tidak bersiri.</span><br/>

<s:form action="/kaunter/permohonan" id="pilih_hakmilik">

	<fieldset class="aras1">
	       <legend>ID Hakmilik tidak bersiri</legend>

            <p>
                <label for="bilHakmilik">Bil. Hakmilik:</label>
                <s:text name="bilHakmilik" id="bilHakmilik" size="4" onblur="javascript:nonNumber(this, this.value);"/> atau kurang
                <s:submit name="Step3" value="Kemaskini" class="btn" onclick="return validate()"/>
            </p>
            <div align="center">
                <table border=0 class="tablecloth"><tr>
                <c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >

                    <th align=right>
                        ID Hakmilik ${i}:
                    </th><td align=left>
                        <s:text name="hakmilikPermohonan[${i - 1}].hakmilik.idHakmilik" id="hakmilik${i - 1}"
                                onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>&nbsp;
                        <div id="msg${i - 1}"/>
                    </td>
                    <c:if test="${i % 3 == 0}" >
                        </tr>
                    </c:if>

                </c:forEach>
                </table>
            </div>
    </fieldset>

    <fieldset class="aras1">
           <legend>ID Hakmilik bersiri</legend>
	    
	        <p>
	        <label>Siri 1: ID Hakmilik dari</label>
	           <s:text name="idHakmilikSiriDari[0]" id="idHakmilikSiriDari0" onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>
	           hingga <s:text name="idHakmilikSiriKe[0]" id="idHakmilikSiriKe0" onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>
	           <input type="button" value="Sahkan" onclick="validateHakmilikBersiri(0)" class="btn" />

            <p>
            <label>Siri 2: ID Hakmilik dari</label>
                <s:text name="idHakmilikSiriDari[1]" id="idHakmilikSiriDari1" onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>
                hingga <s:text name="idHakmilikSiriKe[1]" id="idHakmilikSiriKe1" onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>
               <input type="button" value="Sahkan" onclick="validateHakmilikBersiri(1)" class="btn" />
            </p>
            <p>
            <label>Siri 3: ID Hakmilik dari</label>
                <s:text name="idHakmilikSiriDari[2]" id="idHakmilikSiriDari2" onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>
                hingga <s:text name="idHakmilikSiriKe[2]" id="idHakmilikSiriKe2" onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>
               <input type="button" value="Sahkan" onclick="validateHakmilikBersiri(2)" class="btn" />
            </p>
            <p>
            <label>Siri 4: ID Hakmilik dari</label>
                <s:text name="idHakmilikSiriDari[3]" id="idHakmilikSiriDari3" onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>
                hingga <s:text name="idHakmilikSiriKe[3]" id="idHakmilikSiriKe3" onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>
               <input type="button" value="Sahkan" onclick="validateHakmilikBersiri(3)" class="btn" />
            </p>
            <p>
            <label>Siri 5: ID Hakmilik dari</label>
                <s:text name="idHakmilikSiriDari[4]" id="idHakmilikSiriDari4" onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>
                hingga <s:text name="idHakmilikSiriKe[4]" id="idHakmilikSiriKe4" onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>
               <input type="button" value="Sahkan" onclick="validateHakmilikBersiri(4)" class="btn" />
	        </p>
	  
            <p><label>&nbsp;</label>
	            <s:submit name="Step2" value="Kembali" class="btn" />
	            <s:submit name="Cancel" value="Batal" class="btn" />
	            <s:button name="" value="Isi Semula" class="btn" onclick="clearText('pilih_hakmilik');"/>
	            <s:submit name="Step4" value="Seterusnya" class="btn" id="Step4" />
	        </p>
	
	</fieldset>
	
</s:form>
