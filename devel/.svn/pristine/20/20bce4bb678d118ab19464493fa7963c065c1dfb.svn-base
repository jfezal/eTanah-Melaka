<%-- 
    Document   : maklumat_pemohon
    Created on : 12-Jan-2010, 18:06:12
    Author     : nordiyana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function validation() {
        if($("#nama").val() == ""){
            alert('Sila masukkan " Nama Pemohon " terlebih dahulu.');
            $("#nolitho").focus();
            return true;
        }
        if($("#noic").val() == ""){
            alert('Sila pilih " No.Pengenalan/No.Syarikat " terlebih dahulu.');
            $("#daerah").focus();
            return true;
        }
        if($("#pengenalan").val() == ""){
            alert('Sila pilih " Jenis Pengenalan " terlebih dahulu.');
            $("#bpm").focus();
            return true;
        }
        if($("#Poskod").val() == ""){
            alert('Sila masukkan " Poskod " terlebih dahulu.');
            $("#cawangan").focus();
            return true;
        }
        if($("#negeri").val() == ""){
            alert('Sila pilih " Negeri " terlebih dahulu.');
            $("#rizab").focus();
            return true;
        }
         if($("#alamat1").val() == ""){
            alert('Sila masukkan " Alamat " terlebih dahulu.');
            $("#nolot").focus();
            return true;
        }
         if($("#notelefon").val() == ""){
            alert('Sila masukkan " No. Telefon " terlebih dahulu.');
            $("#lokasi").focus();
            return true;
        }
         if($("#nowarta").val() == ""){
            alert('Sila masukkan " No Warta " terlebih dahulu.');
            $("#nowarta").focus();
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
                    $('#page_div',opener.document).html(data);
                    self.opener.refreshPagePemohon();
                    self.close();
                },'html');
            }
        }

</script>

<s:form beanclass="etanah.view.stripes.pengambilan.pbtActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pemohon
            </legend>
            <p>
                <label>Nama :</label>
                <s:text name="pihak.nama" size="40" id="nama"/>&nbsp;<s:hidden name="pihak.idPihak"/>
            </p>
            <p>
                <label>Jenis Pengenalan :</label>
                <s:select name="pihak.jenisPengenalan.kod"  value=""  style="width:139px;" id="pengenalan">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" sort="nama" />
                </s:select>
              &nbsp;
            </p>
            <p>
                <label>No.Pengenalan/No.Syarikat :</label>
                <s:text name="pihak.noPengenalan" size="40" id="noic"/>&nbsp;<font color="red">*</font>
            </p>

            <p>
                <label>Alamat :</label>
                <s:text name="pihak.suratAlamat1" size="40" id="alamat1"/><font color="red">*</font>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.suratAlamat2" size="40" id="alamat2"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.suratAlamat3" size="40" id="alamat3"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.suratAlamat4" size="40" id="alamat4"/>
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="pihak.suratPoskod" size="40" maxlength="5" id="Poskod"/><font color="red">*</font>
            </p>

            <p>
                <label>Negeri :</label>
                <s:select name="pihak.negeri.kod"  style="width:139px;" id="negeri"><font color="red">*</font>
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" sort="nama" />
                </s:select>
            </p>
            <%--<p>
                <label>Negeri :</label>
                <s:select name="pihak.suratNegeri.kod" id="negeri" >
                    <s:option>Pilih ...</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
            </p>--%>
            <p>
                <label>No.Telefon :</label>
                <s:text name="pihak.noTelefon1" maxlength="10" id="notelefon"  /><font color="red">*</font>

            </p>
            <p>
                <label>Email :</label>
                <s:text name="pihak.email" size="40" maxlength="5" />

            </p>
            <p>
                <label>&nbsp;</label>
                <s:button name="simpanPemohonPopup" id="simpan" value="Simpan" class="btn" onclick="save1(this.name, this.form);"/>
                <label>&nbsp;</label>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>

        </fieldset >
    </div>

    </s:form>
