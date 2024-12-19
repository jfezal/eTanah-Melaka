
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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

<script>
    $(document).ready(function() {
        $('#1').hide();
        $('#2').hide();
    });

function validatePerserahan(idxHakmilik){
    var val = $("#hakmilik" + idxHakmilik).val();
    frm = this.form;
    if (val == null || val == "") return;
    $.get("${pageContext.request.contextPath}/daftar/check_idpermohonan?doCheckHakmilik&idPermohonan=" + val,
        function(data){
            if(data == '1'){
                $("#msg" + idxHakmilik).html("OK");
            } else if(data =='0'){
                // disable if invalid Hakmilik
                // $("#collectPayment").attr("disabled", "true");
                $("#hakmilik" + idxHakmilik).val("");
                alert("ID Perserahan " + val + " tidak wujud!");
                // $("#msg" + idxHakmilik).html("<font color=red>ID Hakmilik " + val + " tidak wujud!</font>");
            } else{
                alert(data);
            }
        });
}

function validate(){
    var bil = document.getElementById('bilHakmilik');
    if(bil.value <=0){
        alert("Bilangan Dokumen mestilah tidak kurang daripada 0");
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

function RemoveNonNumeric( strString )
    {
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

    function AdaKosRendah(value) {
        if (value == "Ya") {
            $('#1').show();
            $('#2').hide();
            $('#bilHakmilik').val('0');
        } else {
            $('#2').show();
            $('#1').hide();
            $('#bilHakmilik').val('0');
        }
    }
</script>

<p class=title>
    <c:if test="${empty SSLSC}">Langkah 2</c:if>
    <c:if test="${!empty SSLSC}">Langkah 3</c:if>
    : Tentukan Bilangan Dokumen yang terlibat
</p>

<s:messages />
<s:errors />

<span class=instr>Masukkan bilangan Dokumen.  </span><br/>

<s:form action="/daftar/carian" id="pilih_hakmilik">
    <s:hidden name="nextStep" value="${SSLSC}"/>

	<fieldset class="aras1">
	       <legend>Dokumen</legend>

            <p>
                <c:if test="${actionBean.kodUrusan eq 'CSR'}">
                <p>
                    <label>Jenis Kos Rendah :&nbsp;  </label>
                    <s:radio id="kosRendah" name="kosRendah" value="Ya" onclick="javaScript:AdaKosRendah(this.value)"> </s:radio>&nbsp;Ya &nbsp;&nbsp;
                    <s:radio id="kosRendah" name="kosRendah" value="tidak" onclick="javaScript:AdaKosRendah(this.value)"> </s:radio>&nbsp;Tidak
                </p>
                <br>
                <label>Id Hakmilik Terlibat = </label>
                <br>
                <p>
                    <c:forEach items="${actionBean.senaraiHakmilikPetak}" var="i" >
                        <c:if test="${fn:contains(i,'GRN')|| fn:contains(i,'GMM')}">
                            <c:set var="idhakmilik" value="${fn:substring(i,0, 17)}" />
                            <c:set var="bilpetak" value="${fn:substring(i,17, 24)}" />
                        </c:if>
                        <c:if test="${fn:contains(i,'PN') || fn:contains(i,'PM') || fn:contains(i,'GM')}">
                            <c:set var="idhakmilik" value="${fn:substring(i,0, 16)}" />
                            <c:set var="bilpetak" value="${fn:substring(i,16, 23)}" />
                        </c:if>
                       
                        <label>&nbsp; </label>${idhakmilik} &nbsp;: ${bilpetak} petak
                        <br>
                    </c:forEach>
                </p>
                <div id="1"> <br />
                    <p><label for="bilHakmilik1" class="labelspoc"><em>*</em>Masukkan Bilangan Petak Terlibat:</label>
                        <s:text name="bilHakmilik1" id="bilHakmilik1" size="12" style="text-align:right" value="${actionBean.bilHakmilik}" onblur="javascript:nonNumber(this, this.value);"/>
                    </p>
                </div>
                <div id="2"> <br />
                    <p><label for="bilHakmilik2" class="labelspoc"><em>*</em>Masukkan Bilangan Petak Terlibat:</label>
                        <s:text name="bilHakmilik2" id="bilHakmilik2" size="12" style="text-align:right" value="${actionBean.bilHakmilik}" onblur="javascript:nonNumber(this, this.value);"/>
                    </p>
                </div>
            </c:if>


            <c:if test="${actionBean.kodUrusan ne 'CSR'}">
                <label for="bilHakmilik">Bil. Helaian Dokumen :</label>
                <s:text name="bilHakmilik" id="bilHakmilik" size="4" onblur="javascript:nonNumber(this, this.value);"/>
            </c:if>
                           
            </p>
            <br/>
            <p><label>&nbsp;</label>
                <c:if test="${actionBean.kodUrusan ne 'CSR'}">
                    <c:if test="${!empty SSLSC}">
                        <s:submit name="Step2b" value="Kembali" class="btn" />
                    </c:if>
                    <c:if test="${empty SSLSC}">
                        <s:submit name="Step1" value="Kembali" class="btn" />
                    </c:if>
                </c:if>
                <s:submit name="Cancel" value="Batal" class="btn" />
                <c:if test="${actionBean.kodUrusan ne 'CSR'}">
                    <s:button name="" value="Isi Semula" class="btn" onclick="clearText('pilih_hakmilik');"/>
                </c:if>
                <s:submit name="Step2a" value="Seterusnya" class="btn" id="Step4" />
            </p>
    </fieldset>
</s:form>
