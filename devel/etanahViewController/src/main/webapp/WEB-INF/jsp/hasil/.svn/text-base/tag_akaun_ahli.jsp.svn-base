<%-- 
    Document   : tag_akaun_ahli
    Created on : Mar 30, 2011, 5:47:25 PM
    Author     : abu.mansur
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
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
    var id = document.getElementById('tagId');
    $("#idTag").val(id.value);
    });

    function checkNumber(elmnt,inputTxt){
        var a = document.getElementById('bilHakmilik');

        if (isNaN(a.value)){
            alert("Nombor tidak sah.Sila masukkan Semula");
            $("#bilHakmilik").focus();
            elmnt.value = RemoveNonNumeric(inputTxt);
            return;
        }
        if(((a.value)=="")||((a.value)==0)){
            alert("Sila masukkan Bilangan Hakmilik");
            $("#bilHakmilik").val("6");
            return;
        }
        if((a.value)>500){
            alert("Bilangan Hakmilik melebihi had ditetapkan.");
            $("#bilHakmilik").val("6");
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


    function validateHakmilikSiri(idxHakmilik,test){
        
        var val = idxHakmilik;
        var type = 'hakmilik';
        frm = this.form;
        if (val == null || val == "") {
            return;
        }
        $.get("${pageContext.request.contextPath}/hasil/check_idhakmilik_accountNo?doCheckHakmilikKumpulan&idHakmilik=" + val+"&type="+type,
        function(data){
            if(data =='0'){
                $("#" + test).val("");
                alert("ID Hakmilik " + val + " tidak wujud!");
                $("#" + test).focus();
            }
            else if(data =='3'){
                $("#" + test).val("");
                alert("ID Hakmilik " + val + " bukan status DAFTAR!");
                $("#" + test).focus();
            }
            else{
                if(checkingIdSequence(idxHakmilik)){
                    if(checkingDuplicateHakmilik(val,idxHakmilik)){
                        $("#akaun" + idxHakmilik).val(data);
                    }                    
                }
            }
        });
    }
    
    function validateHakmilik(idxHakmilik){
        var val = $("#hakmilik" + idxHakmilik).val();
        var type = 'hakmilik';
        frm = this.form;
        if (val == null || val == "") {
            return;
        }
        $.get("${pageContext.request.contextPath}/hasil/check_idhakmilik_accountNo?doCheckHakmilikKumpulan&idHakmilik=" + val+"&type="+type,
        function(data){
            if(data =='0'){
                $("#hakmilik" + idxHakmilik).val("");
                alert("ID Hakmilik " + val + " tidak wujud!");
                $("#hakmilik" + idxHakmilik).focus();
            }
            else if(data =='3'){
                $("#hakmilik" + idxHakmilik).val("");
                alert("ID Hakmilik " + val + " bukan status DAFTAR!");
                $("#hakmilik" + idxHakmilik).focus();
            }
            else{
                if(checkingIdSequence(idxHakmilik)){
                    if(checkingDuplicateHakmilik(val,idxHakmilik)){
                        $("#akaun" + idxHakmilik).val(data);
                    }                    
                }
            }
        });
    }

    function validateAkaun(account){
        var val = $("#akaun" + account).val();
        frm = this.form;
        if (val == null || val == "") {
            return;
        }
        $.get("${pageContext.request.contextPath}/hasil/check_idhakmilik_accountNo?doCheckAkaunKumpulan&account=" + val,
        function(data){
            if(data =='0'){
                $("#hakmilik" + account).val("");
                $("#akaun" + account).val("");
                alert("Nombor Akaun " + val + " tidak wujud!");
            }
            else if(data =='3'){
                $("#hakmilik" + account).val("");
                $("#akaun" + account).val("");
                alert("ID Hakmilik " + val + " bukan status DAFTAR!");
            }
            else{                
                if(checkingIdSequence(account)){
                    if(checkingDuplicateAkaun(val,account)){
                        $("#hakmilik" + account).val(data);
                    }                   
                }
            }
        });
    }

    function checkingDuplicateHakmilik(val, row){
        for(var i=row; i >0;i--){
            var val1 = $("#hakmilik" + (i-1)).val();
            <%--alert(val1+", "+val);--%>
            if(val == val1){
                alert("ID Hakmilik "+val+" telah dimasukkan.");
                $("#hakmilik" + row).val("");
                $("#akaun" + row).val("");
                $("#hakmilik" + row).focus();
                return false;
                break;
            }
        }
        return true;
    }

    function checkingDuplicateAkaun(val, row){
        for(var i=row; i >0;i--){
            var val1 = $("#akaun" + (i-1)).val();
            <%--alert(val1+", "+val);--%>
            if(val == val1){
                alert("No. Akaun "+val+" telah dimasukkan.");
                $("#hakmilik" + row).val("");
                $("#akaun" + row).val("");
                $("#hakmilik" + row).focus();
                return false;
                break;
            }
        }
        return true;
    }

    function checkingIdSequence(row){
        for(var x=0;x<${actionBean.bilHakmilik};x++){
            var val = $("#hakmilik" + x).val();
            var val1 = "";
            if((x+1)<${actionBean.bilHakmilik}){
                val1 = $("#hakmilik" + (x+1)).val();
            }
            if((val == "")&&(val1!="")){
                alert("Sila masukkan maklumat mengikut turutan yang betul.");
                $("#hakmilik" + x).focus();
                $("#hakmilik" + row).val("");
                $("#akaun" + row).val("");
                return false;
            }
        }
        return true;
    }

    function validateSiri(){
        if($("#hakmilikSiriDari").val() == "" && $("#hakmilikSiriKe").val() == ""){
            alert("Sila isi ID Hakmilik bersiri.");
            $("#hakmilikSiriDari").focus();
            $("#hakmilikSiriKe").val("");
            return false;
        }

        if(($("#hakmilikSiriKe").val() == "" && $("#hakmilikSiriDari").val() != "")){
            alert("Sila masukkan maklumat HINGGA terlebih dahulu.");
            $("#hakmilikSiriKe").focus();
            return false;
        }

        if(($("#hakmilikSiriKe").val() != "" && $("#hakmilikSiriDari").val() == "")){
            alert("Sila masukkan maklumat DARI terlebih dahulu.");
            $("#hakmilikSiriDari").focus();
            return false;
        }
        return true;
    }
</script>
<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:right;
        width:25em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:30em;
    }
</style>
<s:form beanclass="etanah.view.stripes.hasil.KumpulanAkaunTAGBaruActionBean" id="kump_ahli">
    <s:messages/>
    <s:errors/>&nbsp;
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Kumpulan</legend>
            <s:hidden name="tagAkaun.idTag" id="tagId"/>
            <p>
                <label>ID Kumpulan :</label>
                ${actionBean.tagAkaun.idTag}&nbsp;
            </p>
            <p>
                <label>Nama Kumpulan :</label>
                ${actionBean.tagAkaun.nama}&nbsp;
            </p>
            <p>
                <label>Catatan :</label>
                ${actionBean.tagAkaun.catatan}&nbsp;
            </p>
        </fieldset>
    </div>
    <div class="content" align="center">
        <fieldset class="aras1">
            <legend>1. Hakmilik<c:if test="${actionBean.negeri eq 'melaka'}">/Akaun</c:if></legend>
            <p class=instr>
                <font color="black">Masukkan ID Hakmilik<c:if test="${actionBean.negeri eq 'melaka'}">/No. Akaun</c:if> bagi hakmilik-hakmilik yang terlibat.</font><br>
                <em>Peringatan :</em> Ditetapkan 500 hakmilik<c:if test="${actionBean.negeri eq 'melaka'}">/akaun</c:if> sahaja untuk setiap kumpulan.
            </p>
            <table align="center" border="0">
                <tr>
                    <td id="tdLabel">Bil. Hakmilik :</td>
                    <td id="tdDisplay"><s:text name="bilHakmilik" id="bilHakmilik" size="4" onkeyup="javascript:checkNumber(this, this.value);"/> atau kurang</td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><s:submit name="updates" value="Kemaskini" class="btn"/>&nbsp;<s:submit name="kembaliAkaunBaru" value="Kembali" class="btn"/></td>
                </tr>
            </table>
            <div align="center">
                <table border=0 align="center" class="tablecloth">
                    <tr>
                        <th>Bil.</th>
                        <th class="hakmilik">ID Hakmilik</th>
                        <c:if test="${actionBean.negeri eq 'melaka'}">
                            <th class="akaun">Nombor Akaun</th>
                        </c:if>
                        <th>Bil</th>
                        <th class="hakmilik">ID Hakmilik</th>
                        <c:if test="${actionBean.negeri eq 'melaka'}">
                            <th class="akaun">Nombor Akaun</th>
                        </c:if>
                    </tr>

                    <tr>
                        <c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >
                            <td align="center" style="text-align:center;">${i}. </td>
                            <td class="hakmilik">
                                <s:text name="senaraiHakmilik[${i - 1}].idHakmilik" id="hakmilik${i - 1}" onkeyup="this.value = this.value.toUpperCase()"/>
                            </td>
                            <c:if test="${actionBean.negeri eq 'melaka'}">
                                <td  class="akaun">
                                    <s:text name="senaraiAkaun[${i - 1}].noAkaun" id="akaun${i - 1}"/>
                                </td>
                            </c:if>
                            <c:if test="${i % 2 == 0}" >
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>
                <br>
                <div align="center">
                    <s:submit name="simpanAhli" value="Simpan" class="btn" />
                    <s:button name=" " value="Isi Semula" class="btn" onclick="clearText('kump_ahli');"/>                    
                </div>
            </div>
        </fieldset>
    </div>
    <%-- to be determine later --%>
    <%--<div class="content">
        <fieldset class="aras1">
            <legend>Hakmilik<c:if test="${actionBean.negeri eq 'melaka'}">/Akaun</c:if> Bersiri</legend>
            <p class=instr align="center">
                <em><font color="black">Masukkan ID Hakmilik<c:if test="${actionBean.negeri eq 'melaka'}">/No. Akaun</c:if> bagi Hakmilik-Hakmilik yang terlibat.</font></em>
            </p>
            <p>
                <label>ID Hakmilik :</label>
                dari <s:text id="hakmilikSiriDari" name="hakmilikSiriDari"/> hingga
                <s:text id="hakmilikSiriKe" name="hakmilikSiriKe" onblur="validateHakmilikSiri();"/>
            </p>
            <c:if test="${actionBean.negeri eq 'melaka'}">
                <p>
                    <label><em>atau</em></label>
                    &nbsp;
                </p>
                <p>
                    <label>Nombor Akaun :</label>
                    dari <s:text id="akaunSiriDari" name="akaunSiriDari"/> hingga
                    <s:text id="akaunSiriKe" name="akaunSiriKe" onblur="validateAkaunSiri();"/>
                </p>
            </c:if>
                <br>
            <div align="center">
                <s:submit name="simpanSiri" value="Simpan" class="btn" onclick="return validateSiri();"/>
            </div>
        <br>
        </fieldset>
    </div>--%>

    <div class="content">
        <fieldset class="aras1">
            <legend>2. Hakmilik Bersiri</legend>
            <p class=instr align="center">
                <font color="black">Masukkan ID Hakmilik bagi hakmilik-hakmilik yang terlibat.</font><br>
                    <em>Peringatan :</em> Ditetapkan 500 hakmilik sahaja untuk setiap kumpulan.
            </p>
            <p>
                <label>ID Hakmilik :</label>
                dari <s:text id="hakmilikSiriDari" name="hakmilikSiriDari" onblur="validateHakmilikSiri(this.value,'hakmilikSiriDari');" onkeyup="this.value = this.value.toUpperCase()"/> hingga
                <s:text id="hakmilikSiriKe" name="hakmilikSiriKe" onblur="validateHakmilikSiri(this.value,'hakmilikSiriKe');" onkeyup="this.value = this.value.toUpperCase()"/>
            </p>
            <br>
            <div align="center">
                <s:submit name="simpanSiri" value="Simpan" class="btn" onclick="return validateSiri();"/>
            </div>
        <br>
        </fieldset>
    </div>
    
</s:form>
<s:form action="kumpulan_akaun" id="carian_hakmilik">
    <div class="content">
        <fieldset class="aras1">
            <legend>3. Carian Hakmilik</legend>
            <p class=instr align="center">
                <font color="black">KliK Butang Carian untuk membuat carian.</font><br>
                    <em>Peringatan :</em> Ditetapkan 500 hakmilik sahaja untuk setiap kumpulan.
            </p>
            <br>
            <div align="center">
                <s:submit name="" value="Carian Hakmilik" class="btn"/>
                <s:text name="idTagKumpulan" id='idTag' style="display:none;"/>
                <s:text name="flagKumpulan" id='idTag' value="tag" style="display:none;"/>
            </div>
        <br>
        </fieldset>
    </div>
</s:form>