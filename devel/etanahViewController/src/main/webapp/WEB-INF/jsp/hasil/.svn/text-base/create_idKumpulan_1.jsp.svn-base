<%--
    Document   : create_idKumpulan_1
    Created on : Sep 2, 2010, 1:27:04 PM
    Author     : abdul.hakim
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
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
    function checkNumber(elmnt,inputTxt){
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

    function validateHakmilik(idxHakmilik){
        var val = $("#hakmilik" + idxHakmilik).val();
        var type = 'hakmilik';
        frm = this.form;
        if (val == null || val == "") {
            return;
        }
        $.get("${pageContext.request.contextPath}/hasil/check_idhakmilik_accountNo?doCheckHakmilik1&idHakmilik=" + val+"&type="+type,
        function(data){
            if(data =='0'){
                $("#hakmilik" + idxHakmilik).val("");
                alert("ID Hakmilik " + val + " tidak wujud!");
            }
            else{
                $("#msg" + idxHakmilik).html("OK");
                $("#akaun" + idxHakmilik).val(data);
                if(checkingId(idxHakmilik)){
                    checkingDuplicate(data,idxHakmilik);
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
        $.get("${pageContext.request.contextPath}/hasil/check_idhakmilik_accountNo?doCheckAkaun&account=" + val,
        function(data){
            if(data =='0'){
                $("#hakmilik" + account).val("");
                $("#akaun" + account).val("");
                $("#baki" + account).val("0");
                alert("Nombor Akaun " + val + " tidak wujud!");
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
        $.get("${pageContext.request.contextPath}/hasil/carian_idKumpulan?doCheckKumpulan&idHakmilik=" + val,
        function(data){
            if(data !='0'){
                alert("ID Hakmilik " + val + " telah wujud dalam kumpulan "+data);
                $("#hakmilik" + row).val("");
                $("#akaun" + row).val("");
            }
        });
    }
</script>
<script type="text/javascript">
    $(document).ready(function() {
        $('#idTag').val('${actionBean.kumpulanAkaun.idKumpulan}');
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

<div id="daerah">
    <s:form beanclass="etanah.view.stripes.hasil.CreateIdKumpulanActionBean" id="create_idKump">
        <s:messages/>
        <s:errors/>&nbsp;
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Kumpulan</legend>
                <s:hidden name="kumpulanAkaun.idKumpulan" id="idKump"/>
                <p>
                    <label>ID Kumpulan :</label>
                    ${actionBean.kumpulanAkaun.idKumpulan}&nbsp;
                </p>
                <p>
                    <label>Nama Kumpulan :</label>
                    ${actionBean.kumpulanAkaun.catatan}&nbsp;
                </p>
                <p>
                    <label>Catatan :</label>
                    ${actionBean.kumpulanAkaun.cawangan.name}&nbsp;
                </p>
            </fieldset>
        </div>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>ID Hakmilik Terlibat</legend>
                <p class=instr>
                    <em><font color="black">Masukkan ID Hakmilik bagi Hakmilik-Hakmilik yang terlibat.</font></em>
                </p>

                <div align="center">
                    <table align="center" border="0">
                        <tr>
                            <td id="tdLabel">Bil. Hakmilik :</td>
                            <td id="tdDisplay"><s:text name="bilHakmilik" id="bilHakmilik" size="4" onkeyup="javascript:checkNumber(this, this.value);"/> atau kurang</label></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center"><s:submit name="updates" value="Kemaskini" class="btn" onclick="return validate();"/></td>
                        </tr>
                    </table>
                </div>

                <div align="center">
                    <%--<s:errors field="list"/>--%>
                    <table border=0 align="center" class="tablecloth">
                        <tr>
                            <th>Bil.</th>
                            <th class="hakmilik">ID Hakmilik</th>
                            <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                                <th class="akaun">Nombor Akaun</th>
                            </c:if>
                            <th>Bil</th>
                            <th class="hakmilik">ID Hakmilik</th>
                            <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                                <th class="akaun">Nombor Akaun</th>
                            </c:if>
                        </tr>

                        <tr>
                            <c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >
                                <td align="center" style="text-align:center;">${i}. </td>
                                <td class="hakmilik">
                                    <s:text name="senaraiHakmilik[${i - 1}].idHakmilik" id="hakmilik${i - 1}" onkeyup="this.value=this.value.toUpperCase();"/>
                                </td>
                                <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                                    <td  class="akaun">
                                        <s:text name="senaraiAkaun[${i - 1}].noAkaun" id="akaun${i - 1}" onkeyup="this.value=this.value.toUpperCase();"/>
                                    </td>
                                </c:if>
                                <c:if test="${i % 2 == 0}" >
                                </tr>
                            </c:if>
                        </c:forEach>
                    </table>
                </div><br>
            </fieldset>
                    <p></p>
            <fieldset class="aras1">
                <legend>ID Hakmilik Bersiri</legend>
                <p class=instr>
                    <em><font color="black">Masukkan ID Hakmilik bagi Hakmilik-Hakmilik yang terlibat.</font></em>
                </p>

                <p>
                    <label>ID Hakmilik dari :</label>
                    <s:text name="idHakmilikSiriDari"/> hingga
                    <s:text name="idHakmilikSiriKe"  />
                </p>
            <br>
            </fieldset>
        </div><br>

        <div align="center"><table style="width:99.2%">
            <tr>
                <td align="center">
                    <s:submit name="saveHakmilik" value="Simpan" class="btn" />&nbsp;
                    <s:button name=" " value="Isi Semula" class="btn" onclick="clearText('create_idKump');"/>
                </td>
            </tr>
        </table></div>
    </s:form>
    <s:form action="kumpulan_akaun" id="carian_hakmilik">
        <div class="content" id="carian_hm">
            <fieldset class="aras1">
                <legend>Carian Hakmilik</legend>
                <p class=instr align="center">
                    <font color="black">KliK Butang Carian untuk membuat carian.</font><br>
                        <em>Peringatan :</em> Ditetapkan 500 hakmilik sahaja untuk setiap kumpulan.
                </p>
                <br>
                <div align="center">
                    <s:submit name="" value="Carian Hakmilik" class="btn"/>
                    <s:text name="idTagKumpulan" id='idTag' style="display:none;"/>
                    <s:text name="flagKumpulan" id='idTag' value="kumpulan" style="display:none;"/>
                </div>
            <br>
            </fieldset>
        </div>
    </s:form>
</div>