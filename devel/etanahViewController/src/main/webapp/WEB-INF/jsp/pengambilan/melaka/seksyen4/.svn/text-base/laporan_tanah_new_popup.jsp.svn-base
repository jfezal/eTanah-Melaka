<%--
    Document   : laporan_tanah_new_popup
    Created on : 21-Mac-2011, 10:32:27
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

function validation() {
        if($("#jenisBangunan").val() == ""){
            alert('Sila pilih " Jenis Bangunan : " terlebih dahulu.');
            $("#jenisBangunan").focus();
            return true;
        }
        if($("#ukuran").val() == ""){
            alert('Sila pilih " Ukuran Bangunan : " terlebih dahulu.');
            $("#ukuran").focus();
            return true;
        }
        if($("#nilai").val() == ""){
            alert('Sila pilih " Nilai Bangunan : " terlebih dahulu.');
            $("#nilai").focus();
            return true;
        }
        if($("#tahunDibina").val() == ""){
            alert('Sila pilih " Tarikh dibina : " terlebih dahulu.');
            $("#tahunDibina").focus();
            return true;
        }
        if($("#namaPemunya").val() == ""){
            alert('Sila pilih " Nama Pemilik : " terlebih dahulu.');
            $("#namaPemunya").focus();
            return true;
        }
//        if($("#namaKetua").val() == ""){
//            alert('Sila masukkan " Nama Ketua Keluarga : " terlebih dahulu.');
//            $("#namaKetua").focus();
//            return true;
//        }
        <%--if($("#catatan").val() == ""){
            alert('Sila masukkan " Catatan " terlebih dahulu.');
            $("#catatan").focus();
            return true;
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
                    self.opener.jenisKegunaan();
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

  </script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div class="subtitle">
    <s:form beanclass="etanah.view.stripes.pengambilan.LaporanTanahPengambilanActionBean" >
        <s:hidden name="idHakmilik" value="${actionBean.hakmilik.idHakmilik}"/>
        <s:hidden name="kategori" value="${actionBean.kategori}"/>
        <s:hidden name="idLaporBangunan" value="${actionBean.permohonanLaporanBangunan.idLaporBangunan}"/>
        <fieldset class="aras1">
            <legend>
                Bangunan
            </legend>
            <p>
                <label>Jenis Bangunan :</label>
                <s:radio name="permohonanLaporanBangunan.jenisBangunan" value="KK" id="jenisBangunan"/>&nbsp;kekal
            </p>
            <p>
                <label>&nbsp;</label>
                <s:radio name="permohonanLaporanBangunan.jenisBangunan" value="SK" id="jenisBangunan"/>&nbsp;separuh kekal
            </p>
            <p>
                <label>&nbsp;</label>
                <s:radio name="permohonanLaporanBangunan.jenisBangunan" value="SM" id="jenisBangunan"/>&nbsp;sementara
            </p>
            <p>
                <label>Ukuran Bangunan :</label>
                <s:text name="permohonanLaporanBangunan.ukuran" id="ukuran" size="12" onkeyup="validateNumber(this,this.value);"/>&nbsp;meter persegi
            </p>
            <p>
                <label>Nilai Bangunan :</label>&nbsp;RM
                <s:text name="permohonanLaporanBangunan.nilai" id="nilai" size="12" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label>Tahun dibina :</label>
                <s:text name="permohonanLaporanBangunan.tahunDibina" id="tahunDibina" size="12" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label >Nama Pemilik :</label>
                <s:text name="permohonanLaporanBangunan.namaPemunya" size="20" id="namaPemunya"/>
            </p>
            <p>
                <label >Nama Ketua Keluarga :</label>
                <s:text name="permohonanLaporanBangunan.namaKetua" size="20" id="namaKetua"/>
            </p>
             <p>
                <label>Status :</label>
                <s:checkbox name="permohonanLaporanBangunan.jenisPendudukan.kod" value="TT"/>&nbsp; Pemilik<br>

                <label>&nbsp;</label>
                <s:checkbox name="permohonanLaporanBangunan.jenisPendudukan.kod" value="TS"/>&nbsp; Pemilik dan Penyewa Bangunan<br>

                <label>&nbsp;</label>
                <s:checkbox name="permohonanLaporanBangunan.jenisPendudukan.kod" value="SS"/>&nbsp; Penyewa Tanah dan Bangunan<br>

            </p>
            <p>
                <label>&nbsp;</label>
                <c:if test="${edit}">
                    <s:button name="editBangunan" value="Simpan" class="btn" onclick="save1(this.name, this.form);" />
                </c:if>
                <c:if test="${!edit}">
                    <s:button name="simpanBangunan" value="Simpan" class="btn" onclick="save1(this.name, this.form);" />
                </c:if>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>


         </s:form>
</div>
