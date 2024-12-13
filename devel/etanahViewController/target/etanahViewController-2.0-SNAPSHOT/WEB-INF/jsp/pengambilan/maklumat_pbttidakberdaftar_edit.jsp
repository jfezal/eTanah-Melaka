<%-- 
    Document   : maklumat_pbttidakberdaftar_edit
    Created on : 15-Dec-2011, 11:43:11
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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<script type="text/javascript">


    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });
    function save(){
        self.opener.refreshPageTanahRizab();
        self.close();}

    function validation() {
        if($("#idhakmilik").val() == ""){
            alert('Sila masukkan " No H/M " terlebih dahulu.');
            $("#idhakmilik").focus();
            return true;
        }
        if($("#nama").val() == ""){
            alert('Sila masukkan " Nama " terlebih dahulu.');
            $("#nama").focus();
            return true;
        }

        if($("#kp").val() == ""){
            alert('Sila masukkan " No. Pengenalan " terlebih dahulu.');
            $("#kp").focus();
            return true;
        }
        if($("#alamat1").val() == ""){
            alert('Sila masukkan " No. Pengenalan " terlebih dahulu.');
            $("#alamat1").focus();
            return true;
        }

        if($("#bandar").val() == ""){
            alert('Sila masukkan " Bandar " terlebih dahulu.');
            $("#bandar").focus();
            return true;
        }
        if($("#poskod").val() == ""){
            alert('Sila masukkan " Poskod " terlebih dahulu.');
            $("#poskod").focus();
            return true;
        }
        if($("#negeri").val() == ""){
            alert('Sila masukkan " Negeri " terlebih dahulu.');
            $("#negeri").focus();
            return true;
        }
        if($("#noTelefon").val() == ""){
            alert('Sila masukkan " No. Telefon " terlebih dahulu.');
            $("#noTelefon").focus();
            return true;
        }
    }

    function savePenjaga(event, f){
        if(validation()){

        }else{
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.opener.refreshPagePBT();
    <%--self.opener.refreshPageHakmilik();--%>
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

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div class="subtitle">

    <s:form beanclass="etanah.view.stripes.pengambilan.maklumatpbttidakberdaftarActionBean">
        <s:hidden name="idPermohonanPhkTdkBerptg" value="${actionBean.idPermohonanPhkTdkBerptg}"/>
        <s:hidden name="typesender" value="phtbedit"/>
        <fieldset class="aras1">
            <legend>
                Maklumat Pihak Berkepentingan Tidak Berdaftar 
            </legend>
            <br/>
            <table>
                <tr>
                    <td><label>Nama :</label></td>
                    <td><s:text name="pptb.nama" size="40" id="nama" onkeyup="this.value=this.value.toUpperCase();"/><font color="red">*</font></td>
                </tr>
                <tr>
                    <td><label>No. Pengenalan :</label></td>
                    <td><s:text name="pptb.nomborPengenalan" id="kp" size="40" maxlength="12" onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase();"/>&nbsp;<em style="font-style:normal">(cth : 800620125177)</em><font color="red">*</font></td>
                </tr>
                <tr>
                    <td><label>Alamat Berdaftar :</label></td>
                    <td><s:text name="pptb.alamat1" id="alamat1" size="40" maxlength="40"/><font color="red">*</font></td>
                </tr>
                <tr>
                    <td><b>&nbsp;</b></td>
                    <td><s:text name="pptb.alamat2" id="alamat2" size="40" maxlength="40"/><font color="red">*</font></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><s:text name="pptb.alamat3" id="alamat3" size="40" maxlength="40"/><font color="red">*</font></td>

                </tr>
                <tr>
                    <td><label>Bandar :</label></td>
                    <td><s:text name="pptb.alamat4" id="bandar" size="40" maxlength="25"/><font color="red">*</font></td>

                </tr>
                <tr>
                    <td><label>Poskod :</label></td>
                    <td><s:text name="pptb.poskod" id="poskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/><font color="red">*</font></td>

                </tr>
                <tr>
                    <td><label>Negeri :</label></td>
                    <td><s:select name="pptb.kodNegeri.kod" id="negeri" value="kod">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
                        </s:select><font color="red">*</font></td>

                </tr>
                <tr>
                    <td><label>No. Telefon :</label></td>
                    <td><s:text name="pptb.nomborTelefon1" id="noTelefon" size="40" maxlength="11"/><font color="red">*</font></td>

                </tr>
                <tr>
                    <td><label>Bank :</label></td>
                    <td><s:select name="pptb.kodBank.kod" style="width:300px;" id="kodbank" value="kod">
                            <s:option value="">-- Sila Pilih --</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodBank}" value="kod" label="nama"/>
                        </s:select>
                    </td>
                </tr>
                <tr>
                    <td><label >No. Akaun:</label></td>
                    <td><s:text name="pptb.nomborAkaunBank" id="noAkaun" size="50"/></td>
                </tr>
            </table>
            <br/>
            <center>
            <table>
                <tr>
                    <td>&nbsp;</td>
                    <td><s:button name="simpanKehadiran" id="simpan" value="Simpan" class="btn" onclick="savePenjaga(this.name, this.form);"/></td>
                    <td><s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/></td>

                </tr>
            </table>
            </center>
        </fieldset>
    </s:form>
</div>