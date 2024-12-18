<%-- 
    Document   : laporan_ulasan_popup
    Created on : JUNE 30, 2011,
    Author     : Syaiful
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
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
    function NoPrompt()
    {           
        allowPrompt = true;          
    }
           
    function saveUlasan(event, f){
        //alert("data");
        if(validation()){
        }
        else{
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
                
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                //                $("#ulasanDiv",opener.document).replaceWith($('#ulasanDiv', $(data)));
                self.opener.refreshUlasan();
                //                    $.unblockUI();
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
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>         
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:messages/>
<div class="subtitle" id="page_effect" style="display:none;">
    <s:form beanclass="etanah.view.stripes.pembangunan.utiliti.UtilitiLtActionBean" >        
        <s:hidden name="idPermohonan" value="${actionBean.idPermohonan}"/>
        <s:hidden name="notaPermohonan.idMohonNota" />
        <div class="subtitle" align="center">
            <fieldset class="aras1">
                <legend>
                    Ulasan
                </legend>
                <p>
                    <s:textarea name="notaPermohonan.nota" cols="100" rows="5" id="nota" class="normal_text" />
                </p>
                <c:if test="${!edit}">
                    <s:button name="simpanRekodUlasan" value="Simpan" class="btn" onclick="saveUlasan(this.name, this.form);" />
                </c:if>
                <c:if test="${edit}">
                    <s:button name="simpanEditRekodUlasan" value="Simpan" class="btn" onclick="saveUlasan(this.name, this.form);" />
                </c:if>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close().fadeOut(2000)"/>
            </fieldset>
        </div>
    </s:form>
</div>

