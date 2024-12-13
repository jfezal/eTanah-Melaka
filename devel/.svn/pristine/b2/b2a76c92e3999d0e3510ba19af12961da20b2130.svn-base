<%-- 
    Document   : tambah_maklumat_anak
    Created on : May 20, 2010, 2:18:54 PM
    Author     : sitifariza.hanim
    Modified By: Rohan
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
        maximizeWindow();
    <c:if test="${!flag}">
                   opener.refreshMaklumatPemohon();
                   //alert("self");
                   self.close();
    </c:if>
          
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

</script>
<body onload="loading();">
    <%--<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>--%>
    <div class="subtitle" id="caw">
        <s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohon1ActionBean">
            <div align="center">
                <s:errors/>
                <s:messages/>
            </div>
            <fieldset class="aras1">
                <legend>Kemasukan Maklumat Anak Pemohon</legend>
                <br/>
                <p>
                    <label><font color="red">*</font>Nama Anak:</label>
                    <s:text name="pemohonHbgn.nama" size="40" id="nama" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label><font color="red">*</font>Nombor Kad Pengenalan:</label>
                    <s:text name="pemohonHbgn.noPengenalan" size="40" maxlength="12" id="kp" onchange="calAgeByNopeng(this.value);" onkeyup="dodacheck(this.value);"/>
                </p>
                <p>
                    <label><font color="red">*</font>Jantina :</label>
                    <s:select name="pemohonHbgn.kodJantina" id="jantina" value="kod">
                        <s:option value="0">Sila Pilih</s:option>
                        <s:option value="1">Lelaki</s:option>
                        <s:option value="2">Perempuan</s:option>
                    </s:select>
                </p>
<!--                <p>
                    <label><font color="red">*</font>Tarikh Lahir :</label>
                    <s:text name="pemohonHbgn.tarikhLahir" size="40" id="datepicker" class="datepicker" onchange="calAgeByDOB(this.value);" />
                </p>-->
                <p>
                    <label><font color="red">*</font>Umur :</label>
                    <s:text name="pemohonHbgn.umur"  size="10" maxlength="3"  id="umur" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                    <label>Nama Sekolah :</label>
                    <s:text name="pemohonHbgn.institusi"  id="sekolah"size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p><br/>
                    <label>&nbsp;</label>

                    <s:submit name="simpanPemohonAnak" id="simpan" value="Simpan" class="btn"/>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
                <br>
            </fieldset>
        </s:form>
    </div>
</body>
