<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/scripts/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/styles/datepicker.css"></script>
<script language="javascript" type="text/javascript">
    $(document).ready(function() {
        $('#Step5').attr("disabled", "disabled");
        $('input[name="bilMohon"]').click(function() {
            $('input[name="Step5"]').removeAttr('disabled');
        });
        //        var htsps =  document.getElementById('htsps');
        //        var htspb = document.getElementById('htspb');
        //        if(htsps.checked){
        //            $('#Step5').removeAttr('disabled');
        //        }else if(htspb.checked){
        //            $('#Step5').removeAttr('disabled');
        //        }else{
        //            $('#Step5').attr("disabled", "disabled"); 
        //        }
        var m = document.getElementById('amtDummy');
        var label = document.getElementById('lbl');
        if (m.value == '') {
            $('#amaun1').val('0.00');
            //            $('#amaun1').show();
            //            $('#amaun2').hide();
        }
        if (label.value == 'Jumlah Petak' && m.value == '') {
            //            $('#amaun1').hide();
            //            $('#amaun2').show();           
            $('#amaun2').val('0');
            $('#amaun3').val('0');
            $('#amaun4').val('0');
        }
        var ursn = document.getElementById("ursn");
        if (ursn.value == 'Melepaskan Gadaian') {
            $("#m").hide();
        }

        //pbbd
        if (ursn.value == 'Permohonan dan Pendaftaran Pecah Bahagi Bangunan dan Tanah') {
            var pbbd = $('#pbbd:checked').val();
            if (pbbd == "Ya") {
                $('#1').show();
                $('#2').hide();
                $('#amaun3').val('0');

            } else if (pbbd == "tidak") {
                $('#2').show();
                $('#1').hide();
                $('#amaun2').val('0');
            } else {
                $('#amaun2').val('0');
                $('#amaun3').val('0');
                $('#1').hide();
                $('#2').hide();
            }
        }

        //pbbs
        if (ursn.value == 'Permohonan dan Pendaftaran Pecah Bahagi Bangunan') {
            var pbbd = $('#pbbd:checked').val();
            if (pbbd == "Ya") {
                $('#1').show();
                $('#2').hide();
                $('#amaun3').val('0');

            } else if (pbbd == "tidak") {
                $('#2').show();
                $('#1').hide();
                $('#amaun2').val('0');
            } else {
                $('#amaun2').val('0');
                $('#amaun3').val('0');
                $('#1').hide();
                $('#2').hide();
            }
        }

        if (ursn.value == 'Permohonan dan Pendaftaran Pecah Bahagi Bangunan Sementara') {
            var pbbd = $('#pbbd:checked').val();
            if (pbbd == "Ya") {
                $('#1').show();
                $('#2').hide();
                $('#amaun3').val('0');

            } else if (pbbd == "tidak") {
                $('#2').show();
                $('#1').hide();
                $('#amaun2').val('0');
            } else {
                $('#amaun2').val('0');
                $('#amaun3').val('0');
                $('#1').hide();
                $('#2').hide();
            }
        }
        
        //psbs
        if (ursn.value == 'Permohonan dan Pendaftaran Pecah Bahagi Bangunan Selepas Penyempurnaan Blok Sementara') {
            var pbbd = $('#pbbd:checked').val();
            if (pbbd == "Ya") {
                $('#1').show();
                $('#2').hide();
                $('#amaun3').val('0');

            } else if (pbbd == "tidak") {
                $('#2').show();
                $('#1').hide();
                $('#amaun2').val('0');
            } else {
                $('#amaun2').val('0');
                $('#amaun3').val('0');
                $('#1').hide();
                $('#2').hide();
            }
        }

        if (ursn.value == 'Permohanan Pendaftaran Sijil Perbadanan Pengurusan') {
            var pbbd = $('#pppp:checked').val();
            if (pbbd == "Ya") {
                $('#1').show();
                $('#2').hide();
                $('#amaun2').val('50');
                $('#amaun3').val('0');

            } else if (pbbd == "tidak") {
                $('#2').show();
                $('#1').hide();
                $('#amaun3').val('100');
                $('#amaun2').val('0');
            } else {
                $('#amaun2').val('0');
                $('#amaun3').val('0');
                $('#1').hide();
                $('#2').hide();
            }
        }
    <c:if test="${actionBean.disRadio eq true}">
        $('input[name="Step5"]').removeAttr('disabled');
    </c:if>

    });


</script>
<script language="javascript" type="text/javascript">
    function changeFormat(row) {
        var amount = document.getElementById('amaun' + row);
        var x = parseFloat(amount.value);
        $('#amaun' + row).val(x.toFixed(2));
    }

    function changeFormat2(row) {
        var num = document.getElementById('amaun' + row).value;
        num = num.toString().replace(/\$|\,/g, '');
        if (isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100 + 0.50000000001);
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        if (cents < 10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3)) + ',' +
                    num.substring(num.length - (4 * i + 3));
        $('#amaun' + row).val((((sign) ? '' : '-') + num + '.' + cents));
    }
    function isNumberKey(evt)
    { //alert(evt);
        var charCode = (evt.which) ? evt.which : event.keyCode
        if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

        return true;
    }
    
    function isNumberKey1(txt,evt) {
      var charCode = (evt.which) ? evt.which : evt.keyCode;
      if (charCode == 46) {
        //Check if the text already contains the . character
        if (txt.value.indexOf('.') === -1) {
          return true;
        } else {
          return false;
        }
      } else {
        if (charCode > 31 &&
          (charCode < 48 || charCode > 57))
          return false;
      }
      return true;
    }

    function clearForm() {
        $('#amaun1').val('0');
        $('#amaun2').val('0');
        $('#amaun3').val('0');
        $('#amaun4').val('0');
        $("#htsps").value = false;
        $("#htspb").value = false;
    }
    function validate() {
        var htsps = document.getElementById("htsps");
        var htspb = document.getElementById("htspb");
        if (htsps.checked || htspb.checked)
        {
            return true;
        } else {
            alert('Sila Buat Pilihan Dahulu.');
            document.getElementById("htspb").focus();
            return false;

        }

    }

    function showBlock(value) {
        if (value == "Y")
        {
            $('#block').show();
            $('#provisional').hide();
            $('#ks').hide();
            $('#1').hide();
            $('#2').hide();
        } else if (value == "N")
        {
            $('#provisional').show();
            $('#block').hide();
            $('#ks').hide();
            $('#1').hide();
            $('#2').hide();
        } else if (value == 'ks')
        {
            $('#block').hide();
            $('#provisional').hide();
            $('#ks').show();
        }
    }
    function AdaKosRendah(value) {
        if (value == "Ya") {
            $('#1').show();
            $('#2').hide();
            $('#amaun3').val('0');
        } else {
            $('#2').show();
            $('#1').hide();
            $('#amaun2').val('0');
        }
    }
    function pppp1(value) {
        if (value == "Ya") {
            $('#1').show();
            $('#2').hide();
            $('#amaun2').val('50');
            $('#amaun3').val('0');
        } else {
            $('#2').show();
            $('#1').hide();
            $('#amaun3').val('100');
            $('#amaun2').val('0');
        }
    }
    function carisek8() {
        var q = $("#idMohonSek4").attr("value");
        //alert(q);
        var idMohon = q;
        //  alert(idMohon);
        var url = '${pageContext.request.contextPath}/spoc/sek8?findIdMohonSeksyen8&idPermohonan=' + idMohon;
        $.getJSON(url, function(data) {
            //alert(data.idPermohonan);
//            idMohon = data;
            if(!data.invalidId){
                $("#sek4idPermohonan").text(data.idPermohonan);
            $("#sek4norujukanfail").text(data.idPermohonan);
            $("#sek4urusan").text(data.urusan);
            $("#sek4tarikhwarta").text(data.tarikhWarta);
            $("#sek4kodLuasAmbil").text(data.kodLuasAmbil);
            $("#sek4jumLuasAmbil").text(data.jumLuasAmbil);
            $("#senaraiHakmilik").text(data.senaraiHakmilik);
            $("#sek4bilHakmilikTDK").text(data.bilHakmilikTDK);
            $("#sek4bilHakmilik").text(data.bilHakmilik);
            }else{
                alert("Id Perserahan yang dimasukkan tiada dalam Permohonan Pengambilan Tanah Seksyen 4")
            }
        }, 'html');
    }
    function carisek4() {
        var q = $("#idMohonSek4").attr("value");
        //alert(q);
        var idMohon = q;
        //  alert(idMohon);
        var url = '${pageContext.request.contextPath}/spoc/sek8?findIdMohon&idPermohonan=' + idMohon;
        $.getJSON(url, function(data) {
            //alert(data.idPermohonan);
//            idMohon = data;
            if(!data.invalidId){
                $("#sek4idPermohonan").text(data.idPermohonan);
            $("#sek4norujukanfail").text(data.idPermohonan);
            $("#sek4urusan").text(data.urusan);
            $("#sek4tarikhwarta").text(data.tarikhWarta);
            $("#sek4kodLuasAmbil").text(data.kodLuasAmbil);
            $("#sek4jumLuasAmbil").text(data.jumLuasAmbil);
            $("#senaraiHakmilik").text(data.senaraiHakmilik);
            $("#sek4bilHakmilikTDK").text(data.bilHakmilikTDK);
            $("#sek4bilHakmilik").text(data.bilHakmilik);
            }else{
                alert("Id Perserahan yang dimasukkan tiada dalam Permohonan Pengambilan Tanah Seksyen 4")
            }
        }, 'html');
    }
</script>

<style type="text/css">
    .labelspoc1 {
        width: 20em;
        float: left;
        text-align: right;
        margin-right: 0px;
        display: block;
        /*color: #666666;*/
        color:#003194;
        font-weight: bold;
        font-family:Tahoma;
        /*font-family: Geneva, Arial, Helvetica, sans-serif;*/
        font-size: 13px;
        margin-left: -3px;
    }
</style>

<p class=title>URUSAN: ${actionBean.senaraiUrusanLabel}</p>
<p class=title>Langkah 4: Maklumat Tambahan Urusan</p>

<s:messages />
<s:errors />

<span class=instr>Masukkan maklumat yang diperlukan di bawah. Semua medan adalah mandatori</span><br/>

<s:form action="/kaunter/permohonan" name="form1" id="urusanInfoTamb">

    <fieldset class="aras1">
        <legend>Maklumat Tambahan Urusan</legend>

        <s:text name="senaraiUrusanLabel"  value="${actionBean.senaraiUrusanLabel}" id="ursn" style="display:none;" />
        <s:text name=" "  value="${actionBean.urusan.labelAmaun1}" id="lbl" style="display:none;"/>

        <c:if test="${actionBean.senaraiUrusanLabel ne 'Permohonan Pecah Bahagi Bangunan Sementara'
                      && actionBean.senaraiUrusanLabel ne 'Permohonan Pecah Bahagi Bangunan atau Bangunan dan Tanah bagi Bangunan Sementara'
                      && actionBean.senaraiUrusanLabel ne 'Permohonan dan Pendaftaran Pecah Bahagi Bangunan Sementara'
                      && actionBean.senaraiUrusanLabel ne 'Permohonan dan Pendaftaran Pecah Bahagi Bangunan dan Tanah'
                      && actionBean.senaraiUrusanLabel ne 'Permohonan dan Pendaftaran Pecah Bahagi Bangunan'
                      && actionBean.senaraiUrusanLabel ne 'Permohonan dan Pendaftaran Pecah Bahagi Bangunan Selepas Penyempurnaan Blok Sementara'
                      && actionBean.senaraiUrusanLabel ne 'Pembaharuan LPS' && actionBean.urusan.kodUrusan ne 'SEK4'&& actionBean.urusan.kodUrusan ne '831'}">
            <c:if test="${actionBean.urusan.labelAmaun1 != null}">
                <p><label for="amaun1" class="labelspoc"> 
                        <c:if test="${actionBean.senaraiUrusanLabel ne 'Melepaskan Gadaian'}"><em>*</em></c:if>   
                        ${actionBean.urusan.labelAmaun1}: 
                    </label>
                    <c:choose>
                        <c:when test="${actionBean.urusan.labelAmaun1 eq 'Jumlah Petak'}">
                            <s:text name="urusan.amaun1" id="amaun2" size="12" style="text-align:right" onkeypress="return isNumberKey(event)"/>
                        </c:when>
                        <c:when test="${actionBean.urusan.labelAmaun1 eq 'Luas (Hektar)'}">
                            <s:text name="urusan.amaun1" id="amaun1"
                                    size="12" style="text-align:right"
                                    onblur=""
                                    formatPattern="###,###,###.0000"/>
                        </c:when>
                        <c:otherwise>
                            <s:text name="urusan.amaun1" id="amaun1"
                                    size="12" style="text-align:right"
                                    onblur="changeFormat2('1')"
                                    formatPattern="###,###,###.00"/>
                        </c:otherwise>
                    </c:choose>                  
                </p>
            </c:if>
        </c:if>

        <%--urusan:PBS N9--%>
        <c:if test="${actionBean.senaraiUrusanLabel eq 'Permohonan Pecah Bahagi Bangunan Sementara'
                      && actionBean.kodNegeri eq 'n9'}">

              <%--<s:text name="urusan.amaun1" id="amaun2" size="12" style="text-align:right" onkeypress="return isNumberKey(event)"/>--%>


        </p>

        <p><label for="amaun1"  class="labelspoc1"><em>*</em>Jumlah Petak Blok Kekal:</label>
            <s:text name="urusan.amaun2" id="amaun3" size="12" style="text-align:right" onkeypress="return isNumberKey(event)"/>
        </p>
    </c:if> 

    <%--urusan:PBS Melaka--%>
    <c:if test="${actionBean.senaraiUrusanLabel eq 'Permohonan Pecah Bahagi Bangunan Sementara'
                  && actionBean.kodNegeri ne 'n9'}">
          <p><label for="amaun1"  class="labelspoc1"><em>*</em>Jumlah Blok Sementara:</label>
              <s:text name="urusan.amaun1" id="amaun2" size="12" style="text-align:right" onkeypress="return isNumberKey(event)"/>
          </p>

          <p><label for="amaun1"  class="labelspoc1"><em>*</em>Jumlah Petak Blok Kekal:</label>
              <s:text name="urusan.amaun2" id="amaun3" size="12" style="text-align:right" onkeypress="return isNumberKey(event)"/>
          </p>
    </c:if>      

    <%--added by Syafiq Az(STRATA) to differentiate with urusan:PHPC 29/05/2013: START--%>
    <c:if test="${actionBean.senaraiUrusanLabel eq 'Permohonan Pecah Bahagi Petak'}">
        <p><font color="Red" size="2">Sila pilih kaedah yang digunakan:</font></p>
            <%--<c:if test="${actionBean.disRadio eq true}">
              <p><s:radio name="bilMohon" id="htsps"  value="1" disabled="disabled"> </s:radio>&nbsp; Hakmilik Strata Sambungan - Pecah Sempadan
                </p>
            </c:if>--%>
            <c:if test="${actionBean.disRadio eq false}">
            <p><s:radio name="bilMohon" id="htsps"  value="1" > </s:radio>&nbsp; Hakmilik Strata Sambungan - Pecah Sempadan
                </p>
                <p><s:radio name="bilMohon" id="htspb"  value="2"> </s:radio>&nbsp; Hakmilik Strata Sambungan - Pecah Bahagian
                </p>
        </c:if>
        <c:if test="${actionBean.disRadio eq true}">
            <p><input type="radio" name="bilMohon" id="htspb" value="2" checked="checked"/>&nbsp; Hakmilik Strata Sambungan - Pecah Bahagian
            </p>
        </c:if>
    </c:if>
    <%--End--%>

    <%--urusan:PBTS N9--%>
    <c:if test="${actionBean.senaraiUrusanLabel eq 'Permohonan Pecah Bahagi Bangunan atau Bangunan dan Tanah bagi Bangunan Sementara'
                  && actionBean.kodNegeri eq 'n9'}">
          <!--<p><label for="amaun1"  class="labelspoc1"><em>*</em>Jumlah Petak Blok Sementara:</label>-->
          <%--s:text name="urusan.amaun1" id="amaun2" size="12" style="text-align:right" onkeypress="return isNumberKey(event)"/--%>
    </p>

    <p><label for="amaun1"  class="labelspoc1"><em>*</em>Jumlah Petak :</label>
        <s:text name="urusan.amaun2" id="amaun3" size="12" style="text-align:right" onkeypress="return isNumberKey(event)"/>
    </p>

    <!--<p><label for="amaun1"  class="labelspoc1"><em>*</em>Jumlah Petak Tanah:</label>-->
    <%--<s:text name="urusan.amaun3" id="amaun4" size="12" style="text-align:right" onkeypress="return isNumberKey(event)"/>--%>
</p>
</c:if>  

<%--PBTS/04--%>
<c:if test="${actionBean.senaraiUrusanLabel eq 'Permohonan Pecah Bahagi Bangunan atau Bangunan dan Tanah bagi Bangunan Sementara'
              && actionBean.kodNegeri ne 'n9'}">
      <p><label for="amaun1"  class="labelspoc1"><em>*</em>Jumlah Blok Sementara:</label>
          <s:text name="urusan.amaun1" id="amaun2" size="12" style="text-align:right" onkeypress="return isNumberKey(event)"/>
      </p>

      <p><label for="amaun1"  class="labelspoc1"><em>*</em>Jumlah Petak Blok Kekal:</label>
          <s:text name="urusan.amaun2" id="amaun3" size="12" style="text-align:right" onkeypress="return isNumberKey(event)"/>
      </p>

      <p><label for="amaun1"  class="labelspoc1"><em>*</em>Jumlah Petak Tanah:</label>
          <s:text name="urusan.amaun3" id="amaun4" size="12" style="text-align:right" onkeypress="return isNumberKey(event)"/>
      </p>
</c:if>  

<%--PBBD/04--%>
<c:if test="${actionBean.kodNegeri ne 'n9' && actionBean.senaraiUrusanLabel eq 'Permohonan dan Pendaftaran Pecah Bahagi Bangunan dan Tanah'}">
    <br />           
    <p>
        <label class="labelspoc">Jenis Kos Rendah :&nbsp;  </label>
        <s:radio id="pbbd" name="pbbd" value="Ya" onclick="javaScript:AdaKosRendah(this.value)"> </s:radio>&nbsp;Ya &nbsp;&nbsp;
        <s:radio id="pbbd" name="pbbd" value="tidak" onclick="javaScript:AdaKosRendah(this.value)"> </s:radio>&nbsp;Tidak 
        </p>
        <div id="1"> <br />
            <p><label for="amaun1" class="labelspoc"><em>*</em>Masukkan ${actionBean.urusan.labelAmaun1}:</label>
            <s:text name="urusan.amaun1" id="amaun2" size="12" style="text-align:right" onkeypress="return isNumberKey(event)"/>
        </p>
    </div>
    <div id="2"> <br />
        <p><label class="labelspoc">Kod Kategori Tanah : </label>
            <s:select name="urusan.katgTanah" id="kTanah" style="width:250px;">
                <s:option label="1 - PERNIAGAAN"  value="1" />
                <s:option label="2 - KEDIAMAN"  value="2" />
                <s:option label="3 - PERINDUSTRIAN"  value="3" />
                <s:option label="4 - PEMBANGUNAN BERCAMPUR"  value="4" />
            </s:select>
        </p>    
        <p><label for="amaun1" class="labelspoc"><em>*</em>Masukkan ${actionBean.urusan.labelAmaun1}:</label>
            <s:text name="urusan.amaun2" id="amaun3" size="12" style="text-align:right" onkeypress="return isNumberKey(event)"/>
        </p>
    </div>
</c:if>

<c:if test="${actionBean.senaraiUrusanLabel eq 'Permohanan Pendaftaran Sijil Perbadanan Pengurusan'}">
    <br />           
    <p>
        <label class="labelspoc">Bayaran :&nbsp;  </label>
        <s:radio id="pppp" name="pbbd" value="Ya" onclick="javaScript:pppp1(this.value)"> </s:radio>&nbsp;Sebelum Pindaan 1 JUN 2015 &nbsp;&nbsp;
        <s:radio id="pppp" name="pbbd" value="tidak" onclick="javaScript:pppp1(this.value)"> </s:radio>&nbsp;Selepas Pindaan 1 JUN 2015
        </p>
        <div id="1"> <br />
            <p><label for="amaun1" class="labelspoc"><em>*</em>Masukkan Amaun:</label>
            <s:text name="urusan.amaun1" id="amaun2" size="12" style="text-align:right" onkeypress="return isNumberKey(event)"/>
        </p>
    </div>
    <div id="2"> <br />  
        <p><label for="amaun1" class="labelspoc"><em>*</em>Masukkan Amaun :</label>
            <s:text name="urusan.amaun2" id="amaun3" size="12" style="text-align:right" onkeypress="return isNumberKey(event)"/>
        </p>
    </div>
</c:if>
<c:if test="${actionBean.urusan.kodUrusan eq 'SEK4'}">
    <br />           

    <div id="1"> <br />
        <p><label for="amaun1" class="labelspoc"><em>*</em>Kategori:</label>
            <s:select name="urusan.kategori" id="kTanah" style="width:250px;">
                <s:option label="1 - PENGAMBILAN SEKSYEN 3 (1) (a)"  value="1" />
                <s:option label="2 - PENGAMBILAN SEKSYEN 3 (1) (b)"  value="2" />
                <s:option label="3 - PENGAMBILAN SEKSYEN 3 (1) (c)"  value="3" />
            </s:select></p>
    </div>
    <div id="1"> <br />
        <p>
            <label class="labelspoc">Nilaian Tanah :&nbsp;  </label>
            <s:text name="urusan.nilaiJPPH" id="amaun2" size="12" style="text-align:right" onkeypress="return isNumberKey1(this,event)"/>
        </p>
    </div>
    <div id="2"> <br />  
        <p><label for="amaun1" class="labelspoc"><em>*</em>Jumlah Hakmilik TDK 2:</label>
            <s:text name="urusan.jumlahTDK" id="amaun3" size="12" style="text-align:right" onkeypress="return isNumberKey(event)"/>
        </p>
    </div>
</c:if>
<c:if test="${actionBean.urusan.kodUrusan eq '831'}">
    <br />           

    <div id="1"> <br />
        <p><label for="amaun1" class="labelspoc"><em>*</em>Id Permohonan Seksyen 4:</label>
            <s:text name="urusan.idPermohonanSebelum" id="idMohonSek4" size="20" style="text-align:right"/>
            <s:button name="cari 1" id="cariSek4"  class="btn" value="Cari" onclick="carisek4()"/>
        </p>
    </div>
    <%--<c:if test="${sek4idPermohonan ne null}">--%>
    <div id="1"> <br />
        <legend>Maklumat Pengambilan Seksyen 4</legend>
        <br>
        <br>
        <p><label for="amaun1" class="labelspoc">Id Permohonan: </label>
            <span id="sek4idPermohonan">&nbsp;</span>
        </p>
        <p><label for="amaun2" class="labelspoc">No Rujukan Fail : </label>
            <span id="sek4norujukanfail">&nbsp;</span>
        </p>
        <p><label for="amaun3" class="labelspoc">Urusan : </label>
            <span id="sek4urusan">&nbsp;</span>
        </p>
        <p><label for="amaun4" class="labelspoc">Tarikh Warta : </label>
            <span id="sek4tarikhwarta">&nbsp;</span>
        </p>
        <p><label for="amaun5" class="labelspoc">Hakmilik Total : </label>
            <span id="sek4bilHakmilik">&nbsp;</span>
        </p>
        <p><label for="amaun1" class="labelspoc">Jumlah Hakmilik TDK : </label>
            <span id="sek4bilHakmilikTDK">&nbsp;</span>
        </p>
        <!--        <p><label for="amaun1" class="labelspoc">Senarai Hakmilik : </label>
                    <span id="senaraiHakmilik">&nbsp;</span>
                </p>-->
        <p><label for="amaun1" class="labelspoc">Luas Ambil : </label>
            <span id="sek4jumLuasAmbil">&nbsp;</span>&nbsp;<span id="sek4kodLuasAmbil">&nbsp;</span>
        </p>

    </div>
    <%--</c:if>--%>


</c:if>
    <c:if test="${actionBean.urusan.kodUrusan eq 'BMAHK'}">
    <br />           

    <div id="1"> <br />
        <p><label for="amaun1" class="labelspoc"><em>*</em>Id Permohonan Seksyen 8:</label>
            <s:text name="urusan.idPermohonanSebelum" id="idMohonSek4" size="20" style="text-align:right"/>
            <s:button name="cari" id="cariSek8"  class="btn" value="Cari" onclick="carisek8()"/>
        </p>
    </div>
    <%--<c:if test="${sek4idPermohonan ne null}">--%>
    <div id="1"> <br />
        <legend>Maklumat Pengambilan Seksyen 8</legend>
        <br>
        <br>
        <p><label for="amaun1" class="labelspoc">Id Permohonan: </label>
            <span id="sek4idPermohonan">&nbsp;</span>
        </p>
        <p><label for="amaun2" class="labelspoc">No Rujukan Fail : </label>
            <span id="sek4norujukanfail">&nbsp;</span>
        </p>
        <p><label for="amaun3" class="labelspoc">Urusan : </label>
            <span id="sek4urusan">&nbsp;</span>
        </p>
        <p><label for="amaun4" class="labelspoc">Tarikh Warta : </label>
            <span id="sek4tarikhwarta">&nbsp;</span>
        </p>
        <p><label for="amaun5" class="labelspoc">Jumlah Hakmilik : </label>
            <span id="sek4bilHakmilik">&nbsp;</span>
        </p>
        <p><label for="amaun1" class="labelspoc">Jumlah Hakmilik TDK : </label>
            <span id="sek4bilHakmilikTDK">&nbsp;</span>
        </p>
        <!--        <p><label for="amaun1" class="labelspoc">Senarai Hakmilik : </label>
                    <span id="senaraiHakmilik">&nbsp;</span>
                </p>-->
        <p><label for="amaun1" class="labelspoc">Luas Ambil : </label>
            <span id="sek4jumLuasAmbil">&nbsp;</span>&nbsp;<span id="sek4kodLuasAmbil">&nbsp;</span>
        </p>

    </div>
    <%--</c:if>--%>


</c:if>

<%--PBBS/04--%>
<c:if test="${actionBean.kodNegeri ne 'n9' && actionBean.senaraiUrusanLabel eq 'Permohonan dan Pendaftaran Pecah Bahagi Bangunan' 
              || actionBean.senaraiUrusanLabel eq 'Permohonan dan Pendaftaran Pecah Bahagi Bangunan Sementara'}">
      <br />            
      <p><label class="labelspoc">Jenis Kos Rendah :&nbsp; </label>
          <s:radio id="pbbd" name="pbbd" value="Ya" onclick="javaScript:AdaKosRendah(this.value)"> </s:radio>&nbsp;Ya &nbsp;&nbsp;
          <s:radio id="pbbd" name="pbbd" value="tidak" onclick="javaScript:AdaKosRendah(this.value)"> </s:radio>&nbsp;Tidak </p>
          <div id="1"> <br />
              <p><label for="amaun1" class="labelspoc"><em>*</em>Masukkan ${actionBean.urusan.labelAmaun1}:</label>
              <s:text name="urusan.amaun1" id="amaun2" size="12" style="text-align:right" onkeypress="return isNumberKey(event)"/>
          </p>
      </div>
      <div id="2"> <br />
          <p><label class="labelspoc">Kod Kategori Tanah : </label>
              <s:select name="urusan.katgTanah" id="kTanah" style="width:250px;">
                  <s:option label="1 - PERNIAGAAN"  value="1" />
                  <s:option label="2 - KEDIAMAN"  value="2" />
                  <s:option label="3 - PERINDUSTRIAN"  value="3" />
                  <s:option label="4 - PEMBANGUNAN BERCAMPUR"  value="4" />
              </s:select>
          </p>    
          <p><label for="amaun1" class="labelspoc"><em>*</em>Masukkan ${actionBean.urusan.labelAmaun1}:</label>
              <s:text name="urusan.amaun2" id="amaun3" size="12" style="text-align:right" onkeypress="return isNumberKey(event)"/>
          </p>
      </div>
</c:if>
      
<%--PSBS/04--%>
<c:if test="${actionBean.kodNegeri ne 'n9' && actionBean.senaraiUrusanLabel eq 'Permohonan dan Pendaftaran Pecah Bahagi Bangunan Selepas Penyempurnaan Blok Sementara'}">
      <br />            
      <p><label class="labelspoc">Jenis Kos Rendah :&nbsp; </label>
          <s:radio id="pbbd" name="pbbd" value="Ya" onclick="javaScript:AdaKosRendah(this.value)"> </s:radio>&nbsp;Ya &nbsp;&nbsp;
          <s:radio id="pbbd" name="pbbd" value="tidak" onclick="javaScript:AdaKosRendah(this.value)"> </s:radio>&nbsp;Tidak </p>
          <div id="1"> <br />
              <p><label for="amaun1" class="labelspoc"><em>*</em>Masukkan ${actionBean.urusan.labelAmaun1}:</label>
              <s:text name="urusan.amaun1" id="amaun2" size="12" style="text-align:right" onkeypress="return isNumberKey(event)"/>
          </p>
      </div>
      <div id="2"> <br />
          <p><label class="labelspoc">Kod Kategori Tanah : </label>
              <s:select name="urusan.katgTanah" id="kTanah" style="width:250px;">
                  <s:option label="1 - PERNIAGAAN"  value="1" />
                  <s:option label="2 - KEDIAMAN"  value="2" />
                  <s:option label="3 - PERINDUSTRIAN"  value="3" />
                  <s:option label="4 - PEMBANGUNAN BERCAMPUR"  value="4" />
              </s:select>
          </p>    
          <p><label for="amaun1" class="labelspoc"><em>*</em>Masukkan ${actionBean.urusan.labelAmaun1}:</label>
              <s:text name="urusan.amaun2" id="amaun3" size="12" style="text-align:right" onkeypress="return isNumberKey(event)"/>
          </p>
      </div>
</c:if>

<%--PHPC/04--%>
<c:if test="${actionBean.kodNegeri ne 'n9' && actionBean.senaraiUrusanLabel eq 'Permohonan dan Pendaftaran Pecah Petak'}">
    <p><font color="Red" size="2">Sila pilih kaedah yang digunakan:</font></p>               
        <c:if test="${actionBean.disRadio eq false}">
        <p><s:radio name="bilMohon" id="htsps"  value="1" > </s:radio>&nbsp; Hakmilik Strata Sambungan - Pecah Sempadan
            </p>
            <p><s:radio name="bilMohon" id="htspb"  value="2"> </s:radio>&nbsp; Hakmilik Strata Sambungan - Pecah Bahagian
            </p>
    </c:if>
    <c:if test="${actionBean.disRadio eq true}">
        <p><input type="radio" name="bilMohon" id="htspb" value="2" checked="checked"/>&nbsp; Hakmilik Strata Sambungan - Pecah Bahagian
        </p>
    </c:if>
</c:if>


<s:text name="urusan.amaun1" id="amtDummy" style="display:none;"/>

<c:if test="${actionBean.urusan.labelTarikh1 != null}">
    <p><label for="tarikh1" class="labelspoc">
            <em id="m">*</em>${actionBean.urusan.labelTarikh1} :</label>
            <s:text name="urusan.tarikh1"
                    formatPattern="dd/MM/yyyy" class="datepicker2" id="tarikh1" readonly="true"
                    onblur="editDateBlur(this, 'DD/MM/YYYY')"
                    onkeypress="return editDatePre(this, 'DD/MM/YYYY', event)"
                    onkeyup="return editDate(this, 'DD/MM/YYYY', event);"/>
    </p>
</c:if>

<c:if test="${actionBean.urusan.labelNilai1 != null}">
    <p><label for="nilai1" class="labelspoc">
            <em>*</em>${actionBean.urusan.labelNilai1} :</label>
            <s:select name="urusan.nilai1" id="nilai1">
                <%--<s:options-collection collection="${actionBean.urusan.nilai1Selection}"/>--%>
                <c:forEach items="${actionBean.urusan.nilai1Selection}" var="line">
                    <c:choose>
                        <c:when test="${line eq 'TSSKB'}">
                            <s:option value="${line}">${line} - Tukar Syarat Sama Kategori Bangunan </s:option>
                    </c:when>
                    <c:when test="${line eq 'TSSKP'}">
                        <s:option value="${line}">${line} - Tukar Syarat Sama Kategori Pertanian </s:option>
                    </c:when>
                    <c:when test="${line eq 'TSSKI'}">
                        <s:option value="${line}">${line} - Tukar Syarat Sama Kategori Industri </s:option>
                    </c:when>
                    <c:when test="${line eq 'TSPSN'}">
                        <s:option value="${line}">${line} - Tukar Syarat Pindaan Syarat Nyata </s:option>
                    </c:when>
                    <c:when test="${line eq 'TSLK'}">
                        <s:option value="${line}">${line} - Tukar Syarat Lain Kategori </s:option>
                    </c:when>
                    <c:otherwise>
                        <s:option value="${line}">${line} </s:option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </s:select>
    </p>
</c:if>

<c:if test="${actionBean.urusan.labelTeks1 != null}">
    <p><label for="nilai1" class="labelspoc">
            <em>*</em>${actionBean.urusan.labelTeks1} :</label>
            <s:text name="urusan.teks1" id="teks1" size="50"/>
    </p>
</c:if>
   

<br/>
<p align="center">
    <s:submit name="Step3" value="Kembali" class="btn" />
    <s:submit name="CancelAll" value="Batal" class="btn" />
    <s:button  name="reset" value="Isi Semula" class="btn" onclick="clearForm(this.form);"/>
    <c:if test="${actionBean.senaraiUrusanLabel ne 'Permohonan Pecah Bahagi Petak'}">
        <s:submit name="Step5" value="Seterusnya" class="btn" />
    </c:if>
    <c:if test="${actionBean.senaraiUrusanLabel eq 'Permohonan Pecah Bahagi Petak'}">
        <s:submit name="Step5" id="Step5"  value="Seterusnya" class="btn" onclick="return validate()" />
    </c:if>

</p>

</fieldset>

</s:form>

<script type="text/javascript">
    $(document).ready(function() {
        $(".datepicker2").datepicker(
                {
                    dateFormat: 'dd/mm/yy',
                    changeMonth: true,
                    changeYear: true,
                    maxDate: new Date()
                });

    });
</script>