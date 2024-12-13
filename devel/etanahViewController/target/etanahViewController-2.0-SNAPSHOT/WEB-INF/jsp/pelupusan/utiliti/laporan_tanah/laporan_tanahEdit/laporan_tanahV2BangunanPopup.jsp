<%-- 
    Document   : laporan_tanahGSA_popup
    Created on : January 18, 2012, 04:26:00 PM
    Author     : Shazwan
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
    <c:choose>
        <c:when test="${actionBean.permohonanLaporanBangunan.jenisBangunan eq 'LL'}">
                $('#lain2').show(); 
        </c:when>     
        <c:otherwise>
                $('#lain2').hide();
        </c:otherwise>
    </c:choose>
        
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
            //            alert('Sila masukkan " Nama Ketua Kewarga : " terlebih dahulu.');
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
                    // self.opener.jenisKegunaan();
                    opener.refreshlptn();
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
    
        function openFrame(type){     
            
            NoPrompt();
            var idHakmilik = $('#idHakmilik').val();
            var noPtSementara = $('#noPtSementara').val();
            var idPermohonan = $('#idPermohonan').val() ;
            //        alert(idHakmilik);
            //    alert(idHakmilik);
            window.open("${pageContext.request.contextPath}/pelupusan/utiliti/laporanTanah?openFrame&idHakmilik="+idHakmilik+"&type="+type+"&noPtSementara="+noPtSementara+"&idPermohonan="+idPermohonan, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
            //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
        }
        
        function refreshpage(){
            //        alert('aa');
            NoPrompt();
    <c:choose>
        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
                var idHakmilik = $('#idHakmilik').val();
                opener.refreshV2ManyHakmilik('main',idHakmilik);
        </c:when>
        <c:otherwise>
                opener.refreshV2('main');
        </c:otherwise>
    </c:choose>
            self.close();
        }
        
        function changeJikaLainLain(val){
            if(val == 'LL'){
                $('#lain2').show();
            }else{
                $('#lain2').hide();
            }
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
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <div class="subtitle">
        <s:form beanclass="etanah.view.stripes.pelupusan.utility.UtilitiLaporanTanahActionBean" >
            <s:hidden name="kategori" value="${actionBean.kategori}"/>
            <s:hidden name="idLaporTanah" value="${actionBean.idLaporTanah}"/>        
            <s:hidden name="idLaporBangunan" value="${actionBean.permohonanLaporanBangunan.idLaporBangunan}"/>
            <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.idPermohonan}"/> 
            <s:hidden name="idHakmilik" id="idHakmilik"/>
            <s:hidden name="noPtSementara" id="noPtSementara"/>
            <fieldset class="aras1">
                <legend>
                    Bangunan
                </legend>
                <div class="content" align="center">
                    <table class="tablecloth" border="0">
                        <tr>
                            <td>
                                Jenis Bangunan :
                            </td>
                            <td>
                                <s:radio name="permohonanLaporanBangunan.jenisBangunan" value="KK" id="jenisBangunan" onclick="changeJikaLainLain(this.value)"/>&nbsp;kekal&nbsp;
                                <s:radio name="permohonanLaporanBangunan.jenisBangunan" value="SK" id="jenisBangunan" onclick="changeJikaLainLain(this.value)"/>&nbsp;separuh kekal&nbsp;
                                <s:radio name="permohonanLaporanBangunan.jenisBangunan" value="SM" id="jenisBangunan" onclick="changeJikaLainLain(this.value)"/>&nbsp;sementara&nbsp;
                                <s:radio name="permohonanLaporanBangunan.jenisBangunan" value="LL" id="jenisBangunan" onclick="changeJikaLainLain(this.value)"/>&nbsp;lain-lain&nbsp;
                            </td>
                        </tr>
                        <tr id="lain2">
                            <td>
                                Sila Nyatakan (Jika Lain-lain) :
                            </td>
                            <td>
                                <s:text name="permohonanLaporanBangunan.keteranganJenisBngn" id="keteranganJenisBngn" size="20"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Keluasan Bangunan :
                            </td>
                            <td>
                                <s:text name="permohonanLaporanBangunan.ukuran" id="ukuran" size="12" onkeyup="validateNumber(this,this.value);"/>&nbsp;
                                <s:select name="permohonanLaporanBangunan.uomUkuran.kod" value="${actionBean.permohonanLaporanBangunan.uomUkuran.kod}" id="ukuranUOM">
                                    <s:option value="">Sila Pilih</s:option>
                                    <%--<s:option value="JK">Kaki</s:option>--%>
                                    <s:option value="K">Kaki Persegi</s:option>
                                    <s:option value="M">Meter Persegi</s:option>
                                </s:select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Nilai Bangunan : &nbsp;RM
                            </td>
                            <td>
                                <s:text name="permohonanLaporanBangunan.nilai" id="nilai" size="12" onkeyup="validateNumber(this,this.value);"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Keadaan Bangunan :
                            </td>
                            <td>
                                <s:textarea name="permohonanLaporanBangunan.keadaanBangunan" id="keadaanBangunan" rows="5" cols="30" class="normal_text"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Tahun dibina :
                            </td>
                            <td>
                                <s:text name="permohonanLaporanBangunan.tahunDibina" id="tahunDibina" size="12" onkeyup="validateNumber(this,this.value);"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Nama Pemilik :
                            </td>
                            <td>
                                <s:text name="permohonanLaporanBangunan.namaPemunya" size="20" id="namaPemunya"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Nama Ketua Keluarga :
                            </td>
                            <td>
                                <s:text name="permohonanLaporanBangunan.namaKetua" size="20" id="namaKetua"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Status :
                            </td>
                            <td>
                                <s:checkbox name="permohonanLaporanBangunan.jenisPendudukan.kod" value="TT"/>&nbsp; Pemilik<br>
                                <s:checkbox name="permohonanLaporanBangunan.jenisPendudukan.kod" value="TS"/>&nbsp; Pemilik dan Penyewa Bangunan<br>
                                <s:checkbox name="permohonanLaporanBangunan.jenisPendudukan.kod" value="SS"/>&nbsp; Penyewa Tanah dan Bangunan<br>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                &nbsp;
                            </td>
                            <td>
                                <c:if test="${!edit}">
                                    <s:submit name="simpanBangunan" value="Simpan" class="btn" onclick="NoPrompt();"/>
                                </c:if>                                
                                <c:if test="${edit}">
                                    <s:submit name="simpanBangunan" value="Kemaskini" class="btn" onclick="NoPrompt();"/>    
                                </c:if>                                
                                <s:button name="" id="tutup" value="Kembali" class="btn" onclick="openFrame('kTanah')"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </fieldset>
        </s:form>
    </div>

</body>

