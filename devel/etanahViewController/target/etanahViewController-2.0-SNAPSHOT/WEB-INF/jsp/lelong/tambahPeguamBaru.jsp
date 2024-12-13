<%-- 
    Document   : tambahPeguamBaru
    Created on : May 12, 2011, 4:59:09 PM
    Author     : Administrator
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>


<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<script type="text/javascript">
    
    function validatePoskodLength(value){
        var plength = value.length;
        if(plength != 5){
            alert('Poskod yang dimasukkan salah.');
            $('#poskod').val("");
            $('#poskod').focus();
        }
    }

    function validation() {
        if($("#nama").val() == ""){
            alert('Sila masukkan " Nama " terlebih dahulu.');
            $("#nama").focus();
            return true;
        }
        if($("#jenis").val() == ""){
            alert('Sila pilih " Jenis Kad Pengenalan " terlebih dahulu.');
            $("#jenis").focus();
            return true;
        }
        if($("#pengenalan").val() == ""){
            alert('Sila masukkan " No. Kad Pengenalan " terlebih dahulu.');
            $("#pengenalan").focus();
            return true;
        }
        if($("#alamat1").val() == ""){
            alert('Sila masukkan " Alamat " terlebih dahulu.');
            $("#alamat1").focus();
            return true;
        }
        if($("#poskod").val() == ""){
            alert('Sila masukkan " Poskod " terlebih dahulu.');
            $("#poskod").focus();
            return true;
        }
        if($("#negeri").val() == ""){
            alert('Sila pilih " Negeri " terlebih dahulu.');
            $("#negeri").focus();
            return true;
        }

        return false;
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }
    function validateIC(icno){

        $.get("${pageContext.request.contextPath}/lelong/peguam?doCheckIC&icno=" + icno,
        function(data){
            if(data =='1'){
                alert("Kad Pengenalan No " + icno + " telah wujud");
                $("#nokp1").val("");
                $("#nokp1").focus();
                return false;
            }
        });
        return true;
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
</script>

<s:form beanclass="etanah.view.stripes.lelong.UtilitiPilihPeguamActionBean">
    <s:messages/>
    <s:errors/>&nbsp;
    <s:hidden name="idPermohonan" value="${actionBean.idPermohonan}"/>
    <s:hidden name="idPermohonan" value="${actionBean.idPenyerah}"/>
    <c:set value="${actionBean.idPermohonan}" var="idPermohonan"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Peguam Yang Ditambah
            </legend>
            <div class="content">
                <p>
                    <label><font color="red">*</font> Nama Peguam : </label>
                    <s:text id="" name="peguam.nama" onchange="this.value=this.value.toUpperCase();" style="width:260px;"/>
                </p>
                <p>
                    <label><font color="red">*</font>Jenis Pengenalan : </label>
                    <s:select id="jenis" name="kodJenis" style="width:150px;" onchange="changeNOKP(this.value);">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p>
                    <label> <font color="red">*</font>Nombor Pengenalan : </label>
                    <s:text id="nokp1" name="peguam.noPengenalan" onkeyup="validateNumber(this,this.value);" onchange="validateIC(this.value);" style="width:150px;"/>
                    <s:text id="nokp2" name="peguam.noPengenalan"  onchange="validateIC(this.value);" style="width:150px;"/>
                    <font color="red" size="1">(cth : 550201045678)</font>
                </p>
                <p>
                    <label><font color="red">*</font>Alamat : </label>
                    <s:text id="alamat1" name="peguam.alamat1" size="40" maxlength="40" onchange="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:text name="peguam.alamat2" size="40" maxlength="40" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:text name="peguam.alamat3" size="40" maxlength="40" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:text name="peguam.alamat4" size="40" maxlength="40" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                </p>
                <p>
                    <label><font color="red">*</font>Poskod : </label>
                    <s:text id="poskod" name="peguam.poskod" size="19" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);" style="width:150px;"/>&nbsp;
                </p>

                <p>
                    <label> <font color="red">*</font>Negeri : </label>
                    <s:select id="negeri" name="kodNegeri" style="width:150px;">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p>
                    <label> Nombor Telefon Bimbit : </label>
                    <s:text name="peguam.noTelefon1" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);" style="width:150px;"/>&nbsp;
                </p>
                <p>
                    <label> Nombor Telefon Pejabat : </label>
                    <s:text name="peguam.noTelefon2" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);" style="width:150px;"/>&nbsp;
                </p>
                <br>
                <div class="content" align="center">
                    <p>
                        <s:submit name="simpanTambahPeguam" id="save" value="Simpan" class="btn" onclick=""/>
                        <s:button name="" value="Tutup" class="btn" onclick="self.close();"/>
                    </p>
                </div>
            </div>
        </fieldset>
    </div>
</s:form>