<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script>
    function validateHakmilik(idxHakmilik){
        var vals = $("#hakmilik" + idxHakmilik).val();
        var val = vals.toUpperCase();
        var type = 'hakmilik';
        frm = this.form;
        if (val == null || val == "") {
            $('#baki'+idxHakmilik).val("0");
            totalAmaun();
            return;
        }


        $.get("${pageContext.request.contextPath}/hasil/check_idhakmilik_accountNo?doCheckHakmilik&idHakmilik=" + val+"&type="+type,
        function(data){
            if(data =='0'){
                $("#hakmilik" + idxHakmilik).val("");
                $("#akaun" + idxHakmilik).val("");
                $("#baki" + idxHakmilik).val("0");
                alert("ID Hakmilik " + val + " tidak wujud!");
                totalAmaun();
            }
            else if(data =='2'){
                <%--$("#hakmilik" + idxHakmilik).val("");--%>
                alert("Terdapat Notis 6A bagi ID Hakmilik " + val + " yang masih belum dilunaskan! Sila jelaskan segera.");
                $.get("${pageContext.request.contextPath}/hasil/check_idhakmilik_accountNo?getBaki1&account=" + val,
                function(bal){
                    var m = parseFloat(bal);
                    $("#baki" + idxHakmilik).val(m.toFixed(2));
                    if(checkingId(idxHakmilik)){
                        checkingDuplicate(val, idxHakmilik);
                    }
                    totalAmaun();
                });
            }else if(data =='6'){
                $("#hakmilik"+ idxHakmilik).val("");
                $("#noAkaun"+ idxHakmilik).val("");
                if(type == "hakmilik"){
                    alert("ID Hakmilik " + val + " telah dibatalkan.");
                }if(type == "akaun"){
                    alert("Nombor Akaun " + val + " telah dibatalkan.");
                }
            }else if(data =='4'){
                $("#hakmilik" + idxHakmilik).val("");
                alert("ID Hakmilik " + val + " telah dikenakan Notis 8A.");
            }else if(data =='9'){
                $("#hakmilik" + idxHakmilik).val("");
                alert("Terdapat Permohonan Bayaran Ansuran bagi ID Hakmilik " + val + ".");
            }
            else{
                $("#msg" + idxHakmilik).html("OK");
                $("#akaun" + idxHakmilik).val(data);
                $.get("${pageContext.request.contextPath}/hasil/check_idhakmilik_accountNo?getBaki1&account=" + val,
                function(bal){
                    var m = parseFloat(bal);
                    $("#baki" + idxHakmilik).val(m.toFixed(2));
                    if(checkingId(idxHakmilik)){
                        checkingDuplicate(val, idxHakmilik);
                    }
                    totalAmaun();
                });
            }
        });
    }

    function checkingDuplicate(val, row){
        var bil = ${actionBean.bilHakmilik};
        for(var i=row; i >0;i--){
            var val1 = $("#hakmilik" + (i-1)).val();
            if(val == val1){
                alert("ID Hakmilik "+val+" telah dimasukkan.");
                $("#hakmilik" + row).val("");
                $("#akaun" + row).val("");
                $("#baki" + row).val("0");
                break;
            }
        }
    }

    function checkingId(row){
        for(var x=0;x<${actionBean.bilHakmilik};x++){
            var val = $("#hakmilik" + x).val();
            var val1 = "";
            if((x+1)<${actionBean.bilHakmilik}){
                val1 = $("#hakmilik" + (x+1)).val();
            }
            if((val == "")&&(val1!="")){
                alert("Sila Masukkan ID Hakmilik mengikut turutan yang betul.");
                $("#hakmilik" + x).focus();
                $("#hakmilik" + row).val("");
                $("#akaun" + row).val("");
                $("#baki" + row).val("0");
                return false;
            }
        }
        return true;
    }
</script>
<script>
    function validateAkaun(account){
        var val = $("#akaun" + account).val();
        frm = this.form;
        if (val == null || val == "") {
            return;
        }
        $.get("${pageContext.request.contextPath}/hasil/check_idhakmilik_accountNo?doCheckAkaun&account=" + val,
        function(data){
            if(data =='0'){
                $("#hakmilik" + account).val("");
                $("#akaun" + account).val("");
                $("#baki" + account).val("0");
                alert("Nombor Akaun " + val + " tidak wujud!");
            }else if(data =='3'){
                $("#hakmilik" + account).val("");
                $("#akaun" + account).val("");
                $("#baki" + account).val("0");
                alert("Nombor Akaun " + val + " telah dibatalkan!");
            }
            else{
                $("#hakmilik" + account).val(data);
                $.get("${pageContext.request.contextPath}/hasil/check_idhakmilik_accountNo?getBaki&account=" + val,
                function(bal){
                    var m = parseFloat(bal);
                    $("#baki" + account).val(m.toFixed(2));
                    if(checkingId(account)){
                        checkingDuplicate(data, account);
                    }
                    totalAmaun();
                });
            }
        });
    }

    function validate(){
        var a = document.getElementById('bilHakmilik');
        var bil = parseInt(a.value);
        if(bil < 1){
            alert("Bilangan Hakmilik hendaklah tidak kurang daripada 1.");
            $("#bilHakmilik").focus();
            return false;
        }else if(bil == null){
            alert("Sila masukkan Bilangan Hakmilik.");
            $("#bilHakmilik").focus();
            return false;
        }else{
            return true;
        }

    }

    function totalAmaun(){
        var t = 0;
        for(var x=0;x<${actionBean.bilHakmilik};x++){
            var m = document.getElementById('baki'+x);
            if(parseFloat(m.value) > 0){
                t += parseFloat(m.value);
            }
        }
        $('#tot').val(t.toFixed(2));
    }

    function updateTotal(elmnt,inputTxt){
        var a = document.getElementById('bilHakmilik')

        if (isNaN(a.value)){
            alert("Nombor tidak sah.Sila masukkan Semula");
            $("#bilHakmilik").focus();
            elmnt.value = RemoveNonNumeric(inputTxt);
            return;
        }
        if(((a.value)=="")||((a.value)==0)){
            alert("Sila masukkan Bilangan Hakmilik");
            $("#bilHakmilik").val("6")
            return;
        }
        if(parseInt(a.value) > 500){
            alert("Bilangan Hakmilik hendaklah kurang daripada 500 Hakmilik");
            $("#bilHakmilik").val("6")
            return;
        }
    }

    function RemoveNonNumeric( strString )
    {
          var strValidCharacters = "123456789.";
          var strReturn = "6";
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

    function popupKumpulan(){
        window.open("${pageContext.request.contextPath}/hasil/carian_kumpulan?popupCarianKumpulan",
                             "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=1");
    }

    function refreshKump(kump){
        $("#kumpulan").val(kump);
    }
</script>

<script type="text/javascript">
    $(document).ready(function() {
    for(var x=0;x<${actionBean.bilHakmilik};x++){
        var val = $("#hakmilik" + x).val();
        var b = document.getElementById('baki'+x);
        if(val == ""){
            $("#baki" + x).val("0");
        }else{
            var sd = parseFloat(b.value);
            $("#baki" + x).val(sd.toFixed(2));
        }
    }
    totalAmaun();
    <c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >
                $("#popup${i - 1}").click( function(){
                    frm = this.form;
                    window.open(frm.action + "/popup?hakmilik.idHakmilik="+$("#hakmilik${i - 1}").val(), "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1024,height=300");
                });
                $("#hakmilik${i - 1}").blur(function(){
                    validateHakmilik(${i - 1});

                });
                $("#akaun${i - 1}").blur(function(){
                    validateAkaun(${i - 1});

                });
    </c:forEach>
            });
</script>
<div align="center"><table style="width:99.2%" bgcolor="green">
    <tr>
        <td width="100%" height="20" >
            <div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Kutipan Cukai</font>
            </div>
        </td>
    </tr>
</table></div>
<%--<p class=title>Langkah 3: Tentukan Hakmilik-Hakmilik Terlibat</p>--%>

<div><font color="black"><b></b></font></div><br>

<s:form beanclass="etanah.view.stripes.hasil.KutipanHasilActionBean" id="batching">
<%--<s:hidden name="${actionBean.hakmilikList}" />--%>

<s:hidden name="${actionBean.hList}" />
<s:messages />
<s:errors />
    <fieldset class="aras1">
        <legend>ID Hakmilik Yang Terlibat</legend>
        <p class=instr>
            <em><font color="black">Masukkan ID Hakmilik bagi Hakmilik-Hakmilik yang terlibat.</font></em>
        </p>

        <p>
            <label for="bilHakmilik">Bil. Hakmilik:</label>
            <s:text name="bilHakmilik" id="bilHakmilik" size="4" onblur="javascript:updateTotal(this, this.value);"/> atau kurang<br>
        <div align="center"><s:submit name="updates" value="Kemaskini" class="btn" onclick="return validate();"/></div>
    </p>

    <div align="center">
        <%--<s:errors field="list"/>--%>
        <table border=0 align="center" class="tablecloth">
            <tr>
                <th>Bil.</th>
                <th class="hakmilik">ID Hakmilik</th>
                <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                    <th class="akaun">Nombor Akaun</th>
                </c:if>
                <th class="baki">Jumlah</th>
                <th>Bil</th>
                <th class="hakmilik">ID Hakmilik</th>
                <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                    <th class="akaun">Nombor Akaun</th>
                </c:if>
                <th class="baki">Jumlah</th>
            </tr>

            <tr>
                <c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >
                    <td align="center" style="text-align:center;">${i}. </td>
                    <td class="hakmilik">
                        <s:text name="hList[${i - 1}].idHakmilik" id="hakmilik${i - 1}" onblur="this.value=this.value.toUpperCase();"/>
                    </td>
                    <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                        <td  class="akaun">
                            <s:text name="accList[${i - 1}].noAkaun" id="akaun${i - 1}"/>
                        </td>
                    </c:if>
                    <td class="baki">
                        <s:text name="accList[${i - 1}].baki" id="baki${i - 1}" size="6" readonly="true" style="text-align:right"/>
                    </td>
                    <c:if test="${i % 2 == 0}" >
                    </tr>
                </c:if>
            </c:forEach>
        </table>
        <p></p>
    </div>
        <p>
            <label>Jumlah Perlu Dibayar (RM):</label>
            <s:text name="" id="tot" readonly="true" style="background:transparent;border:0 px;text-align:right;" size="10"/>
        </p>
    <br>
</fieldset>
<p></p>
<fieldset class="aras1">
    <legend>ID Hakmilik Bersiri</legend>
    <p class=instr>
        <em><font color="black">Masukkan ID Hakmilik bagi Hakmilik-Hakmilik yang terlibat.</font></em>
    </p>

    <p>
        <label>ID Hakmilik dari :</label>
        <s:text name="idHakmilikSiriDari" onblur="this.value=this.value.toUpperCase();"/> hingga
        <s:text name="idHakmilikSiriKe"  onblur="this.value=this.value.toUpperCase();"/>
    </p>
    <c:if test="${actionBean.kodNegeri eq 'melaka'}">
        <p>
            <label>Nombor Akaun dari :</label>
            <s:text name="noAkaunSiriDari"/> hingga
            <s:text name="noAkaunSiriKe"  />
        </p>
    </c:if>
<br>
</fieldset>
<p></p>
<fieldset class="aras1">
    <legend>ID Kumpulan</legend>
    <p class=instr>
        <em><font color="black">Masukkan ID Kumpulan yang telah didaftarkan. Sila klik
                <img alt='Klik Untuk Cari Dasar' border='0' height="16" width="16" src='${pageContext.request.contextPath}/pub/images/search-icon.png' class='rem' title="Carian Kumpulan.">
            untuk membuat carian Kumpulan.</font></em>
    </p>

    <p>
        <label>ID Kumpulan :</label>
        <s:text name="kumpAkaun" onkeyup="this.value=this.value.toUpperCase();" id="kumpulan"/>
        <img alt='Klik Untuk Cari Dasar' border='0' height="16" width="16" src='${pageContext.request.contextPath}/pub/images/search-icon.png' class='rem'
                     id='carianDasar' onclick='popupKumpulan();return false;' onmouseover="this.style.cursor='pointer';" title="Carian Kumpulan.">
    </p>
<br>
</fieldset>

<div align="center"><table style="width:99.2%" bgcolor="green">
    <tr>
        <td align="right">
            <s:submit name="main" value="Kembali" class="btn" />&nbsp;
            <s:submit name="details" value="Seterusnya" class="btn" id="collectPayment" onclick="return checkingId()"/>
        </td>
    </tr>
</table></div>


</s:form>