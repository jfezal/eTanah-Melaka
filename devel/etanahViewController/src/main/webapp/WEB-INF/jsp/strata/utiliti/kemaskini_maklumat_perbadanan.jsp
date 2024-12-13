<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<!DOCTYPE html>

<script type="text/javascript">
    function validateNumber(elmnt,content){
        // If it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric(strString){
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for(intIndex = 0; intIndex < strString.length; intIndex++)
        {
            strBuffer = strString.substr(intIndex, 1);
            // Is this a number
            if(strValidCharacters.indexOf( strBuffer ) > -1){
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }
    
    function save(event, f, g){      
        var pengurusanNama = document.getElementById("pengurusanNama").value;
        var pengurusanAlamat1 = document.getElementById("pengurusanAlamat1").value;
        var pengurusanPoskod = document.getElementById("pengurusanPoskod").value;
        var pengurusanKodNegeri = document.getElementById("pengurusanKodNegeri").value;
        if(pengurusanNama == ""){
            alert('Sila masukkan Nama Perbadanan Pengurusan');
            document.formstr.pengurusanNama.focus();
            return false;
        } else if ((pengurusanAlamat1 == "")) {
            alert('Sila masukkan Alamat');
            document.formstr.pengurusanAlamat1.focus();
            return false;
        } else if ((pengurusanPoskod == "")) {
            alert('Sila masukkan Poskod');
            document.formstr.pengurusanPoskod.focus();
        } else if ((pengurusanKodNegeri == "")) {
            alert('Sila Pilih Negeri');
            document.formstr.pengurusanKodNegeri.focus();
        } else {
            var q = $(f).formSerialize();
            f.action = f.action + '?' + event + '&idPermohonan=' + g + '&popup&' + q;
            f.submit();
        }
    }

    //    function clearForm(){               
    //        var url = "${pageContext.request.contextPath}/strata/kemaskini_maklumat_perbadanan?resetForm";                   
    //        $.post(url,
    //        function(data){
    //            $('#page_div').html(data);
    //        },'html');
    //    }
    
    function clearForm() {
        $('#pengurusanNama').val('');
        $('#pengurusanAlamat1').val('');
        $('#pengurusanAlamat2').val('');
        $('#pengurusanAlamat3').val('');
        $('#pengurusanAlamat4').val('');
        $('#pengurusanPoskod').val('');
        $('#pengurusanKodNegeri').val('');
    }
</script>


<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none" alt=""/>

<s:form id="formstr" beanclass="etanah.view.strata.utiliti.KemaskiniDataPermohonanStrataActionBean">

    <s:errors/>
    <s:messages/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Perbadanan Pengurusan</legend>

            <p>
                <label><em>*</em>Nama :</label>
                <s:text size="45" name="pengurusanNama" id="pengurusanNama"/>
            </p>
            <p>
                <label><em>*</em>Alamat :</label>
                <s:text name="pengurusanAlamat1" size="45" id="pengurusanAlamat1"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="pengurusanAlamat2" size="45" id="pengurusanAlamat2" />
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="pengurusanAlamat3" size="45" id="pengurusanAlamat3" />
            </p>
            <p>
                <label>Bandar :</label>
                <s:text name="pengurusanAlamat4" size="45" id="pengurusanAlamat4" />
            </p>
            <p>
                <label><em>*</em>Poskod :</label>
                <s:text name="pengurusanPoskod" size="45" maxlength="5" id="pengurusanPoskod" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label><em>*</em>Negeri :</label> 
                <s:select name="pengurusanKodNegeri" style="width:286px;" id="pengurusanKodNegeri">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p></p>

            <p>
                <label>&nbsp;</label>
                <c:if test="${actionBean.mc eq null}">
                    <s:button name="simpanMaklumatBadan" id="simpan" value="Kemaskini" class="btn" onclick="return save('simpanMaklumatBadan', this.form, '${actionBean.permohonan.idPermohonan}');"/>&nbsp;
                </c:if>
                <c:if test="${actionBean.mc ne null}">
                    <s:button name="updateMaklumatBadan" id="simpan" value="Kemaskini" class="btn" onclick="save('updateMaklumatBadan', this.form,'${actionBean.permohonan.idPermohonan}');"/>&nbsp;
                </c:if>
                <s:button class="btn" value="Isi Semula" name="resetForm" onclick="clearForm()"/>
            </p>

        </fieldset>
    </div>

</s:form>
