<%--
    Document   : tanah_rizab_add
    Created on : Jun 23, 2010, 5:14:55 PM
    Author     : Rajesh
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

     function tambahPengawalBaru(){
            window.open("${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?PengawalPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

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

//    if($("#bandarPekanMukim").val() == ""){
//        alert('Sila pilih " Bandar/Pekan/Mukim " terlebih dahulu.');
//        $("#bandarPekanMukim").focus();
//        return true;
//    }
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
    }
//    var unitTerlibat = parseInt($('#kodUnitLuas').val());
//    var unitDiambil = parseInt($('#luasDiambilUom').val());
//    if(unitTerlibat != unitDiambil){
//        alert("Unit ukuran tanah tidak sama");
//        $("#kodUnitLuas").focus();
//        return true;
//    }

    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    var address = $("#jagaEmail").val();
    if($("#jagaEmail").val() != ""){
        if(reg.test(address) == false) {
            alert('Invalid Email Address');
            $("#jagaEmail").focus();
            return true;
        }
    }

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
                    self.opener.refreshPageTanahRizab();
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
        var url = '${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?penyukatanBPM&kodDaerah='+kodDaerah;
        f.action = url;
        f.submit();
    }

function ValidateEmail(){
            var emailID= $("#email").val();

            if ((emailID==null)||(emailID=="")){
                return true;
            }
            if ((emailID!=null)||(emailID!="")){
                if(emailcheck(emailID)==false){
                    $("#email").val("");
                    $("#email").focus();
                    return false;
                }
            }
            return true;
        }

        function emailcheck(str) {

            var at="@";
            var dot=".";
            var lat=str.indexOf(at);
            var lstr=str.length;
            var ldot=str.indexOf(dot);
            if (str.indexOf(at)==-1){
                alert('"Alamat Email" salah');
                return false;
            }

            if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
                alert('"Alamat Email" salah');
                return false;
            }

            if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
                alert('"Alamat Email" salah');
                return false;
            }

            if (str.indexOf(at,(lat+1))!=-1){
                alert('"Alamat Email" salah');
                return false;
            }

            if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
                alert('"Alamat Email" salah');
                return false;
            }

            if (str.indexOf(dot,(lat+2))==-1){
                alert('"Alamat Email" salah');
                return false;
            }

            if (str.indexOf(" ")!=-1){
                alert('"Alamat Email" salah');
                return false;
            }

            return true;
        }

  </script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div class="subtitle">

         <s:form beanclass="etanah.view.stripes.pengambilan.MaklumatTambahanActionBean" >
             <s:hidden name="idTanahRizabPermohonan" id="idTanahRizabPermohonan"/>
             <fieldset class="aras1">
            <legend>
                Tanah Rizab
            </legend>

            <p>
                <label for="nolot">No. Lot :</label>
                <s:text name="rizab.noLot" size="20" id="nolot"/>
            </p>
            <p>
<!--                onchange="filterDaerah(this.value,this.form);" -->
                <label>Daerah :</label><%--${actionBean.hakmilik.daerah.nama}--%>
                <s:select name="rizab.daerah.kod" id="daerah" onchange="filterDaerah(this.value,this.form);">
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
                <s:select name="rizab.luasDiambilUom.kod" id="luasDiambilUom" ><font color="red">*</font>
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:option value="H">Hektar</s:option>
                                    <s:option value="M">Meter Persegi</s:option>
                        </s:select>
            </p>
            <p>
                <label for="nowarta">No. Warta :</label>
                <s:text name="rizab.noWarta" size="20" id="noWarta" onkeyup="validateNumber(this,this.value);"/>
            </p>
             <p>
                <label for="tarikhwarta">Tarikh Warta :</label>
                <s:text name="rizab.tarikhWarta" id="tarikhWarta" class="datepicker" size="12" />
            </p>
            <p>
                <label for="noPW">No. Pelan Warta :</label>
                <s:text name="rizab.noLitho" size="20" id="noLitho" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label for="status warta">Status Tanah :</label>
                <s:select name="rizab.rizab.kod" id="trizab" >
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodRizab}" label="nama" value="kod" sort="nama" />
                </s:select>
                <c:set value="1" var="count"/>
               <s:button class="btn" value="Add" name="add" id="add" onclick="addImage('${line.hakmilik.idHakmilik}','${line_rowNum}');"/>


            </p>


        </fieldset>

   <div class="subtitle displaytag">
     <fieldset class="aras1">
            <legend>
                 Maklumat Pegawai Pengawal/Agensi/Badan
            </legend>
            <p>
                <label for="nolot">Nama:</label>
                <s:text name="rizab.namaPenjaga" size="20" id="nolot"/>
            </p>
            <p>
                <label for="alamat">Alamat Surat-Menyurat:</label>
                <s:text name="rizab.jagaAlamat1" size="40"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="rizab.jagaAlamat2" size="40"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="rizab.jagaAlamat3" size="40"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="rizab.jagaAlamat4" size="40"/>
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="rizab.jagaPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
            </p>

            <p>
                <label for="Negeri">Negeri :</label>
                <s:select name="rizab.jagaNegeri.kod">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label>No.Telefon :</label>
                <s:text name="rizab.jagaTel" maxlength="11" id="jagaTel"  onkeyup="validateNumber(this,this.value);"/><font color="red">*</font>

            </p>
            <p>
                <label>No.Fax :</label>
                <s:text name="rizab.jagaFax" maxlength="11" id="jagaFax"  onkeyup="validateNumber(this,this.value);"/><font color="red">*</font>

            </p>
            <p>
                <label>Email :</label>
                <s:text name="rizab.jagaEmail" size="40" maxlength="100" id="jagaEmail"/>

            </p>
            <%--<s:button name="simpanPengawal" id="simpan" value="Simpan" class="btn" onclick="savePenjaga(this.name, this.form);"/>
            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>--%>

        </fieldset>
    </div>
            <div class="subtitle displaytag">
                <c:if test="${actionBean.showSimpan eq true}">
     <fieldset class="aras1">
            <legend>
            </legend>
         <br><br><br>
             <p>
                <label>&nbsp;</label>
                <s:button name="simpanTanahRizab" value="Simpan" class="btn" onclick="save1(this.name, this.form);" />
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>
            </c:if>
                <c:if test="${actionBean.showEdit eq true}">
     <fieldset class="aras1">
            <legend>
            </legend>
         <br><br><br>
             <p>
                <label>&nbsp;</label>
                <s:button name="editTanahRizab" value="Kemaskini" class="btn" onclick="save1(this.name, this.form);" />
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>
            </c:if>
    </div>
         </s:form>
</div>
