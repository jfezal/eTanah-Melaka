<%-- 
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : ${user}
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    function save1(f) {
        if (validationAddress()) {
            return false;
        } else {
            self.opener.refreshT123(f);
            self.opener.refreshPage123(f);
            self.close();
        }
    }

    function tambah(f) {

        self.opener.refreshT123(f);
        self.opener.refreshPage123(f);
        self.close();

    }

</script>

<script type="text/javascript">
    function validateNumber(elmnt, content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric(strString)
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
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

    function validationAddress() {
        if ($("#alamat2").val() == "") {
    <%--alert('Sila masukkan " Alamat " terlebih dahulu.');--%>
            $("#alamat2").focus();
            return true;
        }

        if ($("#alamat1").val() == "") {
            alert('Sila masukkan " Alamat " terlebih dahulu.');
            $("#alamat1").focus();
            return true;
        }
        if ($("#poskod").val() == "") {
            alert('Sila masukkan " Poskod " terlebih dahulu.');
            $("#poskod").focus();
            return true;
        }
        if ($("#negeri").val() == "") {
            alert('Sila pilih " Negeri " terlebih dahulu.');
            $("#negeri").focus();
            return true;
        }
        return false;
    }

    <%--$(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });--%>

    function ValidateEmail() {
        var emailID = $("#email").val();

        if ((emailID == null) || (emailID == "")) {
            return true;
        }
        if ((emailID != null) || (emailID != "")) {
            if (emailcheck(emailID) == false) {
                $("#email").val("");
                $("#email").focus();
                return false;
            }
        }
        return true;
    }

    function emailcheck(str) {

        var at = "@";
        var dot = ".";
        var lat = str.indexOf(at);
        var lstr = str.length;
        var ldot = str.indexOf(dot);
        if (str.indexOf(at) == -1) {
            alert('"Alamat Email" salah');
            return false;
        }

        if (str.indexOf(at) == -1 || str.indexOf(at) == 0 || str.indexOf(at) == lstr) {
            alert('"Alamat Email" salah');
            return false;
        }

        if (str.indexOf(dot) == -1 || str.indexOf(dot) == 0 || str.indexOf(dot) == lstr) {
            alert('"Alamat Email" salah');
            return false;
        }

        if (str.indexOf(at, (lat + 1)) != -1) {
            alert('"Alamat Email" salah');
            return false;
        }

        if (str.substring(lat - 1, lat) == dot || str.substring(lat + 1, lat + 2) == dot) {
            alert('"Alamat Email" salah');
            return false;
        }

        if (str.indexOf(dot, (lat + 2)) == -1) {
            alert('"Alamat Email" salah');
            return false;
        }

        if (str.indexOf(" ") != -1) {
            alert('"Alamat Email" salah');
            return false;
        }

        return true;
    }

</script>
<script type="text/javascript">
    function bal1(f, x, pihak) {
        var queryString = $(f).formSerialize();
        $.get("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?saveTambah&" + queryString + "&idHakmilik=" + x + "&idPihak=" + pihak,
                setTimeout(function () {
                    self.close();
                }, 100));

    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.hasil.PertanyaanHakmilikActionBean" id="pembayar">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Pihak Berkepentingan
            </legend>
            <div class="content" align="center">


                <display:table class="tablecloth" name="${actionBean.listPihakP}"  cellpadding="0" cellspacing="0" id="line" >

                    <display:column title="Pilih" style="text-align:center">

                        <div align="center"><s:radio name="idPihak" value="${line.pihak.idPihak}" onclick="bal1(this.form, '${line.hakmilik.idHakmilik}','${line.pihak.idPihak}')"/></div>
                    </display:column>
                    <display:column  title="Nama" property="pihak.nama" />
                    <display:column  title="Jenis Pihak" property="jenis.nama" />
                </display:table>
            </div>
        </fieldset>
    </div>

    <div align="center">
        <s:submit name="saveTambah" value="Simpan" class="btn" onclick="return tambah(this.form);" />
        <%--<s:submit name="saveTambah" value="Simpan" class="btn"  />--%>
        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close();"/>
        <%--<s:button name="" value="Isi Semula" class="btn" onclick="clearText('pembayar');"/>--%>
    </div>


    <%--</div>--%>

</s:form>
