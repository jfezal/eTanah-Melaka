<%-- 
    Document   : laporan_tanah_popup
    Created on : JUNE 30, 2011,
    Author     : Syaiful
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
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
        if($("#namaKetua").val() == ""){
            alert('Sila masukkan " Nama Ketua Kewarga : " terlebih dahulu.');
            $("#namaKetua").focus();
            return true;
        }
        return false;
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
    $(document).ready(function(){
        $('#page_effect').fadeIn(2000);
    });
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:messages/>
<div class="subtitle" id="page_effect" style="display:none;">
    <s:form beanclass="etanah.view.stripes.pembangunan.LaporanTanahNSActionBean" >
        <s:hidden name="idPermohonan" value="${actionBean.idPermohonan}"/>
        <s:hidden name="TB" value="${actionBean.TB}"/>
        <s:hidden name="idLaporBangunan" value="${actionBean.permohonanLaporanBangunan.idLaporBangunan}"/>
        <fieldset class="aras1">
            <legend>
                <c:if test="${actionBean.TB eq 'T'}">Tanaman </c:if> <c:if test="${actionBean.TB eq 'B'}">Bangunan </c:if>
            </legend>
            <p>
                <label >Nama Pemilik :</label>
                <s:text name="permohonanLaporanBangunan.namaPemunya" size="20" id="namaPemunya" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label >Nama Penyewa :</label>
                <s:text name="permohonanLaporanBangunan.namaPenyewa" size="20" id="namaPenyewa" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>
                    <c:if test="${actionBean.TB eq 'T'}">Tanaman kekal/sementara :</c:if> <c:if test="${actionBean.TB eq 'B'}">Keadaan Bangunan :</c:if>
                </label>
                <s:radio name="permohonanLaporanBangunan.jenisBangunan" value="KK" id="jenisBangunan"/>&nbsp;kekal
            </p>
            <c:if test="${actionBean.TB eq 'B'}">
                <p>
                    <label>&nbsp;</label>
                    <s:radio name="permohonanLaporanBangunan.jenisBangunan" value="SK" id="jenisBangunan"/>&nbsp;separuh kekal
                </p>
            </c:if>
            <p>
                <label>&nbsp;</label>
                <s:radio name="permohonanLaporanBangunan.jenisBangunan" value="SM" id="jenisBangunan"/>&nbsp;sementara
            </p>
            <p>
                <label>
                    <c:if test="${actionBean.TB eq 'T'}">Tahun Mula diusahakan :</c:if> <c:if test="${actionBean.TB eq 'B'}">Tahun dibina :</c:if>
                </label>
                <s:textarea name="permohonanLaporanBangunan.keteranganTahunBinaan" id="tahunDibina" cols="20" />
            </p>
            <p>
                <label>
                    <c:if test="${actionBean.TB eq 'T'}">Jenis Tanaman :</c:if> <c:if test="${actionBean.TB eq 'B'}">Kegunaan Bangunan :</c:if>
                </label>
                <s:textarea name="permohonanLaporanBangunan.namaKetua" cols="49" rows="2" id="namaKetua" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label >Kadar Sewa :</label>
                <s:text name="permohonanLaporanBangunan.nilai" size="12" id="kadarSewa" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <c:if test="${edit}">
                    <s:button name="editBangunan" value="Kemaskini" class="btn" onclick="save1(this.name, this.form);" />
                </c:if>
                <c:if test="${!edit}">
                    <s:button name="simpanBangunan" value="Simpan" class="btn" onclick="save1(this.name, this.form);" />
                </c:if>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
            <br>
        </fieldset>
    </s:form>
</div>

