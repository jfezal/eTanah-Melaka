
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<title>Belakang Kaunter | Pilih Hakmilik</title>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<script type="text/javascript">
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

            //jika kod betst, N8AB, takyah check status batal
    <c:if test="${!empty batal}"> return; </c:if>
    
            $.get("${pageContext.request.contextPath}/daftar/check_idhakmilik?doCheckHakmilik&idHakmilik=" + val,
            function(data){
                if(data == '1'){
                    //$("#msg" + idxHakmilik).html("OK");
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
                    alert("Cukai untuk Hakmilik " + val + " masih belum dijelaskan!");
                } else{
                    alert(data);
                }
            });
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
                    alert("Terdapat cukai dalam Siri " + idx + " yang masih belum dilunaskan!");
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
</script>

<script type="text/javascript">
    $(document).ready(function() {

        $('form').submit(function(){
                $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            });

        $("#namaJenisHakmilik").change( function() {
            var valueJenisHakmilik = $("#namaJenisHakmilik").val();
            $("#kodJenisHakmilik").val(valueJenisHakmilik);
        });
        $("#kodJenisHakmilik").blur( function() {
            var valueJenisHakmilik = $("#kodJenisHakmilik").val();
            $("#namaJenisHakmilik").val(valueJenisHakmilik);
        });
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
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<p class=title>Langkah 2: Tentukan Hakmilik-Hakmilik Terlibat</p>

<span class=instr>Masukkan semua ID Hakmilik yang terlibat. Biarkan kosong mana-mana medan
    yang tidak berkaitan. Anda boleh mencampurkan diantara ID Hakmilik yang bersiri dan tidak bersiri .</span><br/>

<s:form action="/pelupusan/kaunter" >
    <c:if test="${actionBean.kodSerah eq 'MH' }">
        <fieldset class="aras1">
            <legend>Maklumat Hakmilik Baru</legend>

            <p>
                <label>Jenis Hakmilik Baru : </label>
                <s:text name="kodJenisHakmilik" size="4" id="kodJenisHakmilik"/> -
                <s:select name="hakmilik.kodHakmilik.kod" id="namaJenisHakmilik">
                    <s:option value="">-- Sila Pilih --</s:option>
                    <s:options-collection collection="${actionBean.senaraiKodHakmilik}" label="nama" value="kod" />
                </s:select>

            </p>

        </fieldset>
    </c:if>

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
                            <s:text class="hakmilik" name="hakmilikPermohonan[${i - 1}].hakmilik.idHakmilik" id="hakmilik${i - 1}" onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>&nbsp;
                        </td>
                        <c:if test="${i % 3 == 0}" >
                        </tr>
                    </c:if>

                </c:forEach>
            </table>
        </div>
    </fieldset>
    <br/>
    <fieldset class="aras1">
        <legend>ID Hakmilik bersiri</legend>

        <p>
            <label>Siri 1: ID Hakmilik dari</label>
            <s:text name="idHakmilikSiriDari[0]" id="idHakmilikSiriDari0" />
	           hingga <s:text name="idHakmilikSiriKe[0]" id="idHakmilikSiriKe0"/>
            <input type="button" value="Sahkan" onclick="validateHakmilikBersiri(0)" class="btn" />
		</p>
        <p>
            <label>Siri 2: ID Hakmilik dari</label>
            <s:text name="idHakmilikSiriDari[1]" id="idHakmilikSiriDari1" />
            hingga <s:text name="idHakmilikSiriKe[1]" id="idHakmilikSiriKe1" />
            <input type="button" value="Sahkan" onclick="validateHakmilikBersiri(1)" class="btn" />
        </p>
        <p>
            <label>Siri 3: ID Hakmilik dari</label>
            <s:text name="idHakmilikSiriDari[2]" id="idHakmilikSiriDari2" />
            hingga <s:text name="idHakmilikSiriKe[2]" id="idHakmilikSiriKe2" />
            <input type="button" value="Sahkan" onclick="validateHakmilikBersiri(2)" class="btn" />
        </p>
        <p>
            <label>Siri 4: ID Hakmilik dari</label>
            <s:text name="idHakmilikSiriDari[3]" id="idHakmilikSiriDari3" />
            hingga <s:text name="idHakmilikSiriKe[3]" id="idHakmilikSiriKe3" />
            <input type="button" value="Sahkan" onclick="validateHakmilikBersiri(3)" class="btn" />
        </p>
        <p>
            <label>Siri 5: ID Hakmilik dari</label>
            <s:text name="idHakmilikSiriDari[4]" id="idHakmilikSiriDari4"/>
            hingga <s:text name="idHakmilikSiriKe[4]" id="idHakmilikSiriKe4" />
            <input type="button" value="Sahkan" onclick="validateHakmilikBersiri(4)" class="btn" />
        </p>

        <p><label>&nbsp;</label>
            <s:submit name="Step2" value="Kembali" class="btn" />
            <s:submit name="Cancel" value="Batal" class="btn" />
            <s:submit name="Step4" value="Seterusnya" class="btn" id="Step4" />
        </p>
    </fieldset>

</s:form>