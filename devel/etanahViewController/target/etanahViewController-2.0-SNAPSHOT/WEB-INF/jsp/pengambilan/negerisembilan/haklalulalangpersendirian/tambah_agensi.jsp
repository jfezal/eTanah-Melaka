<%-- 
    Document   : tambah_agensi
    Created on : Oct 12, 2011, 3:06:42 PM
    Author     : Murali
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
        self.close();
    }

    function validation() {
        if($("#kod").val() == ""){
            alert('Sila masukan " Kod " terlebih dahulu.');
            $("#kod").focus();
            return true;
        }
        if(document.getElementById("kod").value.length > 4){
            alert('" kod " paling maksimum adalah 4 digit.');
            $("#kod").focus();
            return true;
        }
        if($("#kodKementerian").val() == "0"){
            alert('Sila masukan " Kod Kementerian " terlebih dahulu.');
            $("#kodKementerian").focus();
            return true;
        }
        if($("#nama").val() == ""){
            alert('Sila masukan " Nama Jabatan " terlebih dahulu.');
            $("#nama").focus();
            return true;
        }
        if($("#kodNegeri").val() == ""){
            alert('Sila pilih " Negeri " terlebih dahulu.');
            $("#bandarPekanMukim").focus();
            return true;
        }
        if($("#alamat1").val() == ""){
            alert('Sila masukan " alamat1 " terlebih dahulu.');
            $("#alamat1").focus();
            return true;
        }    
        if($("#poskod").val() == ""){
            alert('Sila masukkan " poskod " terlebih dahulu.');
            $("#poskod").focus();
            return true;
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
                $('#page_div',self.document).html(data);
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

</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="list" />
<div class="subtitle">

    <s:form beanclass="etanah.view.stripes.pengambilan.JabatanTeknikalPengambilanActionBean">
        <%--<s:text name="permohonan.idPermohonan"/>--%>
        <fieldset class="aras1">
            <legend>
                <s:messages/>
                <s:errors/>
                Kod Agensi
            </legend>
            <p>
                <label for="kod">Kod :</label>
                <s:text name="kodAgensi.kod" size="15" id="kod" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <%--    <p>
                    <label for="kodKementerian">Kod Kementerian :</label>
                    <s:select name="kodAgensi.kodKementerian" id="kodKementerian" >
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="1">Test</s:option>
                        <s:options-collection collection="${list.senaraiKodKementerian}" label="nama" value="kod" sort="nama" />
                    </s:select>
                    <s:text name="kodAgensi.kodKementerian" size="20" id="kodKementerian" onkeyup="this.value=this.value.toUpperCase();"/>
                 </p>--%>

            <p>
                <label>Kod Kementerian  :</label>
                <s:select name="kodAgensi.kodKementerian" id="kodKementerian" >
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodKementerian}" label="nama" value="kod" sort="nama" />
                </s:select>

            </p>

            <p>
                <label for="kodKategoriAgensi:">KodKategoriAgensi:</label>
                <s:select name="kodKatgAgensi" id="KodKategoriAgensi" value="">
                    <s:option value="JTK">Jabatan Teknikal</s:option>
                    <s:option value="ADN">Adun</s:option>
                </s:select>
            </p>

            <p>
                <label for="nama">Nama Jabatan:</label>
                <s:text name="kodAgensi.nama" size="50" id="nama" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label for="alamat1">Alamat:</label>
                <s:text name="kodAgensi.alamat1" id="alamat1"  size="50" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label for="alamat2">&nbsp;</label>
                <s:text name="kodAgensi.alamat2" id="alamat2"  size="50" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label for="alamat3">&nbsp;</label>
                <s:text name="kodAgensi.alamat3" id="alamat3"  size="50" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label for="alamat4">&nbsp;</label>
                <s:text name="kodAgensi.alamat4" id="alamat4"  size="50" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label for="poskod">Poskod:</label>
                <s:text name="kodAgensi.poskod" id="poskod"  size="8" maxlength="5" />
            </p>
            <p>
                <label>Negeri :</label>
                <s:select name="kodAgensi.kodNegeri.kod" id="kodNegeri" >
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" sort="nama" />
                </s:select>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:button name="simpanKodAgensi" value="Simpan" class="btn" onclick="save1(this.name, this.form);" />
                <s:button name="tutup" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>
    </s:form>
</div>