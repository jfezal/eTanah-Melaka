<%-- 
    Document   : tanahAAppl
    Created on : 16-Mar-2011, 21:23:54
    Author     : nordiyana
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
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
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>

<script type="text/javascript">


$(document).ready( function(){
$(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
});
function save(){
        self.opener.refreshPageTanahRizab();
        self.close();}



function validation() {
    if($("#nolot").val() == ""){
        alert('Sila pilih " No. PT/Lot " terlebih dahulu.');
        $("#nolot").focus();
        return true;
    }

    if($("#daerah").val() == ""){
        alert('Sila pilih " Daerah " terlebih dahulu.');
        $("#daerah").focus();
        return true;
    }

    if($("#bandarPekanMukim").val() == ""){
        alert('Sila pilih " Bandar/Pekan/Mukim " terlebih dahulu.');
        $("#bandarPekanMukim").focus();
        return true;
    }
    if($("#rizab").val() == ""){
        alert('Sila pilih " Jenis Rizab " terlebih dahulu.');
        $("#rizab").focus();
        return true;
    }

    if($("#nowarta").val() == ""){
        alert('Sila masukkan " No Warta " terlebih dahulu.');
        $("#nowarta").focus();
        return true;
    }

    var luasTerlibat = parseInt($('#luasTerlibat').val());
    var luasDiambil = parseInt($('#luasDiambil').val());
    if(luasTerlibat < luasDiambil){
        alert("Luas Sebenar must be more than Luas Diambil");
        $("#luasTerlibat").focus();
        return true;

    var unitTerlibat = parseInt($('#kodUnitLuas').val());
    var unitDiambil = parseInt($('#luasDiambilUom').val());
    if(unitTerlibat != unitDiambil){
        alert("Unit ukuran tanah tidak sama");
        $("#kodUnitLuas").focus();
        return true;
    }
    }

   <%-- var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    var address = $("#jagaEmail").val();
    if($("#jagaEmail").val() != ""){
        if(reg.test(address) == false) {
            alert('Invalid Email Address');
            $("#jagaEmail").focus();
            return true;
        }
    }--%>

    }

        function save1(event, f){
            if(validation()){

            }
            else{
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.opener.refreshPageHakmilik();
                    self.close();
                },'html');
            }
        }
          $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
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

    function filterDaerah(kodDaerah,f){
        var url = '${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?penyukatanBPMAA&kodDaerah='+kodDaerah;
        f.action = url;
        f.submit();
    }
     function validateLuas(idVar,rowNo){

     var str = idVar.value;
     var luasdiAmbil = parseInt(idVar.value);
     //alert('Luas Id:'+("luas"+rowNo));
     var luas = parseInt($('#luasTerlibat'+rowNo).val());
     //alert(luas);
    // var luas = parseInt(document.getElementById("luas"+rowNo).value);
   //  var luas = parseInt(document.getElementById("luas"+rowNo).value);
   //  alert('Luas'+luas);

     if(luasdiAmbil > luas){
         alert("Luas Diambil must less than Luas");
         idVar.value = str.substring(0,str.length-1);
         idVar.focus();
         return true;
      }
  }




  </script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div class="subtitle">

         <s:form beanclass="etanah.view.stripes.pengambilan.MaklumatTambahanActionBean" >
             <fieldset class="aras1">
            <legend>
                Tanah AA
            </legend>
            <p>
                <label for="noHakmilik">No AA :</label>
                <s:text name="rizab.noLot" size="20" id="noLot"/>
                 <%--onkeyup="validateNumber(this,this.value);"--%>

            </p>
            <p>
                <label>Daerah :</label><%--${actionBean.hakmilik.daerah.nama}--%>
                <s:select name="rizab.daerah.kod" id="daerah" onchange="filterDaerah(this.value,this.form);" >
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama" />
                </s:select>
            </p>
            <p>
                <label>Bandar/Pekan/Mukim :</label>
                <s:select name="rizab.bandarPekanMukim.kod" id="bandarPekanMukim" >
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${actionBean.senaraiBPM}" label="nama" value="kod" sort="nama" />
                </s:select>
            </p>
            <p>
                <label for="Luas">Luas Sebenar :</label>
                <s:text name="rizab.luasTerlibat" size="20" id="luasTerlibat" />
                   <s:select name="rizab.kodUnitLuas.kod" id="kodUnitLuas" ><font color="red">*</font>
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:option value="H">Hektar</s:option>
                                    <s:option value="M">Meter Persegi</s:option>
                        </s:select>
            </p>
            <p>
                <label for="Luas diambil">Luas Diambil :</label>
                <s:text name="rizab.luasDiambil" size="20" id="luasDiambil"/>
               <s:select name="rizab.kodUnitLuas.kod" id="kodUnitLuas" ><font color="red">*</font>
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:option value="H">Hektar</s:option>
                                    <s:option value="M">Meter Persegi</s:option>
                        </s:select>
            </p>
            <p>
                <label for="nowarta">Lokasi :</label>
                <s:text name="rizab.noWarta" size="20" id="noWarta" onkeyup="validateNumber(this,this.value);"/>
            </p>
             <p>
                <label for="tarikhwarta">Pemilik :</label>
                <s:text name="rizab.namaPenjaga" size="20" id="nolot"/>
            </p>
            <p>
                <label for="noPW">Syarat Nyata :</label>
                <s:select name="rizab.kodSyaratNyata.kod" id="rizab" >
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodSyaratNyata}" label="syarat" value="kod" sort="syarat" />
                </s:select>
            </p>


        </fieldset>


    </div>
            <div class="subtitle displaytag">
     <fieldset class="aras1">
            <legend>
            </legend>
         <br><br><br>
             <p>
                <label>&nbsp;</label>
                <s:button name="simpanTanahAA" value="Simpan" class="btn" onclick="save1(this.name, this.form);" />
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>
    </div>
         </s:form>
</div>
