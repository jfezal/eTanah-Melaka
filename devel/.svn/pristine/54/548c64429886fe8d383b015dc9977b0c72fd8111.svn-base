<%-- 
    Document   : tambah_pemohonV2Anak
    Created on : Jul 17, 2013, 1:03:48 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ageCalculator.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">

    $(document).ready( function(){
          
    });

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
    function dodacheck(val) {
        //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;

        var v = $('#jenisPengenalan').val();

        var strPass = val;
        var strLength = strPass.length;
        //$('#kp').attr("maxlength","12");
        if(strLength > 12) {
            var tst = val.substring(0, (strLength) - 1);
            $('#kp').val(tst);
        }
        var lchar = val.charAt((strLength) - 1);
        if(isNaN(lchar)) {
            //return false;
            var tst = val.substring(0, (strLength) - 1);
            $('#kp').val(tst);
        }
          
    }

    function doCheckUmur(v,id,type){
        var va = $('#jenisPengenalan').val();
        if(va == 'B'){
            return doValidateAge(v, id, 'jenisPengenalan',type);
        }else {
            return true;
        }
    }
    function calAgeByNopeng(value){
        // var v = $('#jenisPengenalan').val();
    <%-- var v = '${actionBean.pemohonHbgn.jenisPengenalan.kod}';

        if(v == 'B') {--%>

                var icNo = value;
                $('#datepicker').val(icNo.substring(4,6)+"/"+icNo.substring(2,4)+"/"+19 + icNo.substring(0,2));
                var age = calculateAge(icNo.substring(4,6), icNo.substring(2,4), 19 + icNo.substring(0,2));
                if(age >100){
                    age = age-100;
                }
                $('#umur').val(age);
            }<%--}--%>
         
        

            function calculateUmar(){
                if($("#tarikhLahir").val() != ""){
                    var value = $("#tarikhLahir").val();
                    var yearStartVal = value.substring(6,8);
                    if(yearStartVal == "19"){
                        var age = calculateAge(value.substring(0,2), value.substring(3,5), 19 + value.substring(8,10));
                        if(age > 99){
                            age = age-100;
                        }   
                
                    }else{
                        var age = calculateAge(value.substring(0,2), value.substring(3,5), 20 + value.substring(8,10));
                    }
                    $('#umur').val(age);
                }
            }
            
             function editKeluarga()
        {
            NoPrompt();
            var idPemohon = $('#idPemohon').val();
//            alert(idPemohon);
            window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohonV2?showFormMaklumatKeluarga&idPemohon="+idPemohon, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }   

</script>
<body>
    <script>
        // Allow the user to be warned by default.
        var allowPrompt = true;
                
        window.onbeforeunload = WarnUser;
        function WarnUser()
        {   
            if(allowPrompt)
                refreshpage();
            if(allowPrompt)
            {
                event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
            }
            else
            {
                // Reset the flag to its default value.
                allowPrompt = true;
            }
        }
        function NoPrompt()
        {
            allowPrompt = false;
        }
        
    </script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="list" />
    <s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohonV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="permohonan.kodUrusan.kod"/>
        <s:hidden name="idPemohon" id="idPemohon"/>
        <s:hidden name="idPemohonHubungan" value="${actionBean.pemohonHubungan.idHubungan}"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Tambah Anak</legend>
                <div class="content" align="center">
                    <%--<font color="red">Sila Masukkan Maklumat Yang Bertanda *</font>--%>
                    <table class="tablecloth" border="0">
                        <tr>
                            <td>
                                Nama :
                            </td>
                            <td>
                                <s:text name="pemohonHubungan.nama" id="nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Nombor Kad Pengenalan :
                            </td>
                            <td>
                               <%-- <s:text name="pemohonHubungan.noPengenalan" size="40" maxlength="12" id="kp" onchange="calAgeByNopeng(this.value);" onkeyup="dodacheck(this.value);"/> --%>
							   <s:text name="pemohonHubungan.noPengenalan" size="40" maxlength="12" id="kp" onkeyup="dodacheck(this.value);"/> 
                            </td>
                        </tr>
                        <tr>

                            <td>
                                Jantina :
                            </td>
                            <td>
                                <s:select name="pemohonHubungan.kodJantina" id="jantina" value="kod">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:option value="1">Lelaki</s:option>
                                    <s:option value="2">Perempuan</s:option>
                                </s:select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Umur :
                            </td>
                            <td>
                                <s:text name="pemohonHubungan.umur" size="10" maxlength="3"  id="umur" onkeyup="validateNumber(this,this.value);"/>
                            </td>
                        </tr>


                        <tr>
                            <td>Nama Sekolah :</td>
                            <td>
                                <s:text name="pemohonHubungan.institusi" size="40"onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>

                    </table>
                    <br/>
                    <table class="tablecloth" border="0">
                        <tr>
                            <td colspan="2"><s:submit name="simpanPemohonHubungan" value="Simpan" class="btn" onclick="NoPrompt();"/>
                                <s:hidden name="type" id="type" value="tAnak"/>
                                <s:hidden name="status" id="status" value="${status}"/>
                                <s:button name="Tambah" id="save" value="Kembali" class="btn" onclick="editKeluarga()"/></td>
                        </tr>
                    </table>
                </div>
                <br/>

            </fieldset>
        </div>
    </s:form>
</body>
