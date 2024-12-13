<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.io.*,java.util.*" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    
    $(document).ready(function(){
        $('form').submit(function() {
            doBlockUI();
        });
    });
    function simpanPihak(event, f){
        if(validation()){

        }
        else{
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
            },'html');
        }
    }

    function validation() {
        if($("#nama").val() == ""){
            alert('Sila masukkan " Nama " terlebih dahulu.');
            $("#nama").focus();
            return false;
        }
        if($("#jenis").val() == ""){
            alert('Sila pilih " Jenis Kad Pengenalan " terlebih dahulu.');
            $("#jenis").focus();
            return false;
        }
        return true;
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
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

    function changeNOKP( val ){
        var no = val;
        if(no == 'B'){
            $("#nokp2").hide();
            $("#nokp1").show();
        }else{
            $("#nokp2").show();
            $("#nokp1").hide();
        }
    }

    function test(f) {
        $(f).clearForm();
    }

    function clearForm(f) {

        $("#id").val('');
        $("#nama").val('');
        $("#jenis").val('');
        $("#nokp1").val('');
        $("#nokp2").val('');
    }

    $(document).ready(function() {
        $("#nokp2").hide();
    });
    

    function validateIC(icno){
        $.get("${pageContext.request.contextPath}/lelong/pendaftaran_jurulelong?doCheckJuruLelong&icno=" + icno,
        function(data){
            if(data =='1'){
                alert("Kad Pengenalan No " + icno + " telah wujud!");
                $("#nokp1").val("");
                $("#nokp1").focus();
                return false;
            }
        });
        return true;
    }
    function hapus(idPenghantarNotis){
        if(confirm('Adakah pasti untuk hapus?')){
            
            $.get("${pageContext.request.contextPath}/penghantarnotis?delete&idPenghantarNotis=" + idPenghantarNotis,
            function(data){
                if(data=='1'){
                    alert("Hapus Data Berjaya");
                    
                    return false;
                }else{
                    alert("Hapus Data Gagal. Sila Hubungi Pentadbir Sistem.")
                }
            });
            return true;
            
        }
    }
    function updateStatus(idPenghantarNotis,status){
      
        $.ajax({
            url: 'penghantarnotis?simpanEditStatus&idPenghantarNotis='+idPenghantarNotis+'&status='+status,
            success: function(){
            },
            error: function(){
            }
        });
    }

    
</script>
<%
    // added by nur.aqilah
    // Set refresh, autoload time as 20 seconds
    response.setIntHeader("Refresh", 20);
%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.daftar.utiliti.PenghantarNotisActionBean">
    <div class="subtitle displaytag"  id="page_div">
        <fieldset class="aras1" id="">
            <s:messages />
            <s:errors />
            <s:hidden id="idPenghantarNotis" name="idPenghantarNotis" value="${actionBean.idPenghantarNotis}"/>

            <legend>
                Maklumat Penghantar Notis
            </legend>
            <p>
                <label><font color="red">*</font> Nama : </label>
                <s:text id="nama" name="penghantarNotis.nama" onchange="this.value=this.value.toUpperCase();" style="width:260px;"/>
            </p>
            <p>
                <label><font color="red">*</font>Jenis Pengenalan : </label>
                <s:select id="jenis" name="penghantarNotis.kodJenisPengenalan.kod" style="width:150px;" onchange="changeNOKP(this.value);">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label> <font color="red">*</font>Nombor Pengenalan : </label>
                <s:text id="nokp1" name="penghantarNotis.noPengenalan" onkeyup="validateNumber(this,this.value);" style="width:150px;"/>
                <s:text id="nokp2" name="penghantarNotis.noPengenalan" style="width:150px;"/>
                <font color="red" size="1">(cth : 550201045678)</font>
            </p>
            <div class="content" align="center">
                <p>
                    <s:submit name="simpanPenghantarNotis" id="save" value="Simpan" class="btn" onclick="return validation();"/>
                    <s:submit name="reset" value="Isi Semula" class="btn" onclick="clearForm(this.form);"/>
                    <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
                    <%--<s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>--%>
                </p>
            </div>
        </fieldset>

        <fieldset class="aras1">
            <legend>
                Maklumat Penghantar Notis
            </legend>
            <div class="content" align="center">
                <p>

                    <display:table class="tablecloth" name="${actionBean.listPenghantar}"  cellpadding="0" cellspacing="0" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}
                            <s:hidden name="" class="x${line_rowNum -1}" value="${line.idPenghantarNotis}"/>
                        </display:column>
                        <display:column property="nama" title="Nama" />
                        <display:column property="kodJenisPengenalan.nama" title="Jenis Pengenalan"/>
                        <display:column property="noPengenalan" title="Nombor Pengenalan" />
                        <display:column title="Status" class="${line_rowNum}" style="text-transform:uppercase;">
                            <s:hidden name="idPenghantarNotis" value="${line.idPenghantarNotis}"/>
                            <s:select name="status{line_rowNum -1}" id="" value="${line.aktif}" style="width:200px;" onchange="updateStatus('${line.idPenghantarNotis}',this.value)">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:option value="Y">Aktif</s:option>
                                <s:option value="T">Tidak Aktif </s:option>
                            </s:select>
                        </display:column>
                    <display:column title="Hapus">
                        <p align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif'
                                 onclick="hapus('${line.idPenghantarNotis}');return false;" onmouseover="this.style.cursor='pointer';">
                        </p>
                    </display:column>
                </display:table>


                &nbsp;
            </div>
        </fieldset>
    </div>
</s:form>
