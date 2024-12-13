<%--
    Document   : popup_tambah_laporan_operasi
    Created on : Nov 11, 2011, 11:24:21 AM
    Author     : latifah.iskak
--%>


<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
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
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript">
    $(document).ready(function() {
        self.opener.refreshLaporanOperasiPolis();
    });

    function save(event, f){

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $("#senaraiPasukanDiv",opener.document).replaceWith($('#senaraiPasukanDiv', $(data)));
            self.opener.refreshLaporanOperasiPolis();
            self.close();
        },'html');
    }

    function test(f) {
        $(f).clearForm();
    }

    function convertText(r,t){
        var i = r.value;
        document.getElementById(t).value=i.toUpperCase();
    }


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

     

    function limitText(limitField, limitNum) {
        if (limitField.value.length > limitNum) {
            limitField.value = limitField.value.substring(0, limitNum);
        }
    }

    function findPenggunaPasukan(id){
        var idPenggunaKetua = window.opener.document.getElementById("namaKetua").value;//id pengguna
        var namaKetua = window.opener.document.getElementById("namaKetuaPasukan").value;
        var icKetua = window.opener.document.getElementById("noPengenalanKetua").value;
        
        if(idPenggunaKetua == id){
            alert("Pengguna ini telah dipilih sebagai ketua. Sila pilih pengguna yang lain.");
            resetInfo();
            return false;
        }
        
        $.get('${pageContext.request.contextPath}/penguatkuasaan/laporan_operasi_polis?findPenggunaPasukan&id='+id,
        function(html){
            $("#penggunaInfoDiv").replaceWith($('#penggunaInfoDiv', $(html)));
            var noPengenalan =  $('#noPengenalanCarian').val();
            var jabatan = $('#jawatanCarian').val();
            var namaCarian = $('#namaCarian').val();
            var kadKuasaCarian = $('#kadKuasaCarian').val();

            var bil =  document.getElementById("recordCount").value;
            for (var i = 0; i < bil; i++){
                var namaList = document.getElementById('nama'+i).value;
                var noKpList = document.getElementById('noKp'+i).value;
                if(namaCarian == namaList){
                    alert("Pengguna ini telah dipilih. Sila pilih pengguna yang lain.");
                    resetInfo();
                    return false;
                }

                if(noPengenalan == noKpList){
                    alert("Pengguna ini telah dipilih. Sila pilih pengguna yang lain.");
                    resetInfo();
                    return false;
                }
            }

            if(namaCarian == namaKetua){
                alert("Pengguna ini telah dipilih sebagai ketua. Sila pilih pengguna yang lain.");
                resetInfo();
                return false;
            }else if(noPengenalan == icKetua){
                resetInfo();
                return false;
            }

            document.getElementById("icPasukan").value = noPengenalan;
            document.getElementById("jawatanPasukan").value = jabatan;
            document.getElementById("namaPasukan").value = namaCarian;
            document.getElementById("kadKuasa").value = kadKuasaCarian;


        }, 'html');
    }

    function resetInfo(){
        document.getElementById("icPasukan").value = '';
        document.getElementById("jawatanPasukan").value = '';
        document.getElementById("namaPasukan").value = '';
        document.getElementById("pilih").value = '';
        document.getElementById("kadKuasa").value = '';
    }

    function validateForm(){
        if($('#namaPasukan').val()=="")
        {
            alert('Sila pilih pengguna terlebih dahulu');
            $('#pilih').focus();
            return false;
        }
        if($('#peranan').val()=="")
        {
            alert('Sila pilih peranan terlebih dahulu');
            $('#peranan').focus();
            return false;
        }
        return true;
    }



</script>
<s:form beanclass="etanah.view.penguatkuasaan.LaporanOperasiPolisActionBean">
    <s:messages />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Senarai Pasukan
            </legend>
            <div class="content">
                <p>
                    <label>Sila Pilih :</label>
                    <s:select name="pilih" id="pilih" onchange="findPenggunaPasukan(this.value);">
                        <s:option value="">Sila Pilih</s:option>
                        <c:choose>
                            <c:when test="${actionBean.kodNegeri eq '05' && (actionBean.permohonan.kodUrusan.kod eq '429' || actionBean.permohonan.kodUrusan.kod eq '425')}">
                                <c:forEach items="${actionBean.pasukan}" var="line">
                                    <s:option value="${line.idPengguna}">${line.nama} (${line.jawatan})</s:option>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${actionBean.pengguna}" var="line">
                                    <s:option value="${line.idPengguna}">${line.nama} (${line.jawatan})</s:option>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>

                    </s:select>
                </p>
                <p>
                    <label><em>*</em>Nama :</label>
                    <s:text name="namaPasukan" id="namaPasukan" size="30" onkeyup="this.value=this.value.toUpperCase();" readonly="true"/>&nbsp;
                </p>
                <p>
                    <label>No Pengenalan :</label>
                    <s:text name="icPasukan" id="icPasukan" size="30" onkeyup="this.value=this.value.toUpperCase();" readonly="true"/>&nbsp;
                </p>
                <p>
                    <label>Jawatan :</label>
                    <s:text name="jawatanPasukan" size="40" id="jawatanPasukan" onkeyup="this.value=this.value.toUpperCase();" readonly="true"/>&nbsp;
                </p>

                <div id="penggunaInfoDiv" style="visibility: hidden; display:none"">
                    <s:text name="namaCarian" id="namaCarian"/>
                    <s:text name="noPengenalanCarian" id="noPengenalanCarian"/>
                    <s:text name="jawatanCarian" id="jawatanCarian"/>
                    <s:text name="kadKuasaCarian" id="kadKuasaCarian"/>
                </div>

                <p>
                    <label>No.Kad Kuasa :</label>
                    <s:text name="kadKuasa" id="kadKuasa" maxlength="10" onkeyup="this.value=this.value.toUpperCase();" readonly="true"/>&nbsp;
                </p>
                <p>
                    <label><em>*</em>Peranan :</label>
                    <s:select name="peranan" id="peranan">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodPerananOperasi}" label="nama" value="kod" sort="nama" />
                    </s:select>
                </p>

                <div id="senaraiPasukanDivHidden" style="visibility: hidden; display: none">
                    <%--<div id="senaraiPasukanDivHidden">--%>
                    <s:hidden name="recordCount" id="recordCount"/>
                    <c:set value="0" var="i"/>
                    <c:forEach items="${actionBean.senaraiPasukanWithoutKetua}" var="senarai">
                        <input name="nama${i}" id="nama${i}" value="${senarai.nama}">
                        <input name="noKp${i}" id="noKp${i}" value="${senarai.noKp}">
                        <c:set var="i" value="${i+1}" />
                    </c:forEach>
                </div>

                <br/>
                <p align="center">
                    <s:button class="btn" onclick="if(validateForm())save(this.name,this.form);" name="simpanPasukanOperasi" value="Simpan"/>
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    <s:hidden name="idPasukan" id="idPasukan"/>
                </p>

            </div>
        </fieldset>
    </div>
</s:form>

