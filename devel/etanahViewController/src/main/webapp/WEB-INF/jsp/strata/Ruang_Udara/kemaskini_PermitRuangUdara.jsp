<%-- 
    Document   : kemaskini_PermitRuangUdara
    Created on : Jun 7, 2011, 7:04:14 PM
    Author     : zadhirul.farihim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>

<script type="text/javascript">

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
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

    //global variable :
    var dtCh= "/";
    var minYear=1900;
    var maxYear=2100;


    function isInteger(s){
        var i;
        for (i = 0; i < s.length; i++){
            // Check that current character is number.
            var c = s.charAt(i);
            if (((c < "0") || (c > "9")))
                return false;
        }
        // All characters are numbers.
        return true;
    }

    function kemaskini(event, f){

        var noBlok = document.getElementById("noBlok").value;
        var noTingkat = document.getElementById("noTingkat").value;
        var noPetak = document.getElementById("noPetak").value;
        var jenisStrukTambah = document.getElementById("jenisStrukTambah").value;        
        var bilStruktur = document.getElementById("bilStrukTambah").value;

        if ((jenisStrukTambah == 0))
        {
            alert('Sila masukkan jenis struktur tambah ');
            document.getElementById("jenisStrukTambah").focus();
            return false;
        }

        else if ((noBlok == ""))
        {
            alert('Sila Isikan Nombor Blok ');
            document.getElementById("noBlok").focus();
            return false;
        }
        else if ((noTingkat == ""))
        {
            alert('Sila masukkan nombor tingkat');
            document.getElementById("noTingkat").focus();
            return false;
        }
        <%--if (jenisStrukTambah != "Bumbung") {
            if ((noPetak == ""))
            {
                alert('Sila masukkan nombor petak ');
                document.getElementById("noPetak").focus();
                return false;
            }
        }--%>
        if ((bilStruktur == ""))
        {
            alert('Sila masukkan bil struktur tambah ');
            document.getElementById("bilStrukTambah").focus();
            return false;
        }
        else
        {
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });

            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url, q,
                    function(data) {
                        $('#page_div', opener.document).html(data);
                         $.unblockUI();
                        self.close();
                        //   refreshPage();
                    }, 'html');
            return true
        }
    }
    function getbs()
    {
        var str = document.getElementById('bilStrukTambah').value;
        //bilPermit
        document.getElementById('bilPermit').value = str;
    }
    function getbumbung() {
        var bumbung = document.getElementById("jenisStrukTambah").value;
        if (bumbung == 'Bumbung')
        {

            $('#petak').hide();
        } else {
            document.getElementById("noPetak").value = "";
            document.getElementById("noPetak").focus();
            $('#petak').show();
        }
    }
    $(document).ready(function() {
        var bumbung = document.getElementById("jenisStrukTambah").value;
        if (bumbung == 'Bumbung')
        {
            $('#petak').hide();
        }
    });

</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form  name="form1" beanclass="etanah.view.strata.PermitRuangUdaraActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="idPermit"/>
    <div class="subtitle" >
        <fieldset class="aras1">
            <br>
            <legend>Kemaskini Permit Ruang Udara</legend>
            <p>
                Yang bertanda(<em>*</em>) adalah wajib diisi.
            </p>
            <p><label>Jenis Struktur Tambahan :</label>
                <s:select name="jenisStrukTambah"  id="jenisStrukTambah" style="width:150px;" onchange="getbumbung();" >
                    <s:option  value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${actionBean.senaraikodGunaRuangUdara}" label="nama" value="nama"/>
                </s:select>
            </p>

            <p>
                <label><em>*</em>No. Blok :</label>
                <s:text name="noBlok" id="noBlok" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label><em>*</em>No. Tingkat :</label>
                <s:text name="noTingkat" id="noTingkat"class="normal_text" onkeyup="validateNumber(this,this.value);"/>
            </p><p id="petak">

                <label>No. Petak :</label>
                <s:text name="noPetak" id="noPetak" class="normal_text" onkeyup="validateNumber(this,this.value);"/>

            </p>
            <%--<p>
                <label><em>*</em>Di Bina Oleh :</label>
                <s:text name="dibinaOleh" id="dibinaOleh" class="normal_text"/>
            </p>--%>
            <p>
                <label><em>*</em>Bil. Struktur Tambahan :</label>
                <s:text name="bilStrukTambah" id="bilStrukTambah" value="" class="normal_text" onchange="getbs()" onkeyup="validateNumber(this,this.value);"></s:text>
                </p>
                <p>
                    <label>Bil. Permit :</label>
                <s:text name="bilPermit" id="bilPermit" value="" class="normal_text" readonly="readonly" ></s:text>
                </p>

                <br>
                <label>&nbsp;</label>
            <s:button name="updatePermit" id="simpan" value="Kemaskini" class="btn" onclick="kemaskini(this.name,this.form)"/>
            <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
            <%--<s:button name="tambahBangunan" value="Simpan" class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');"/>--%>
            <br>

            <br>

        </fieldset>
    </div>


</s:form>

