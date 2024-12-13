<%-- 
    Document   : popup_tambah_barang_operasi
    Created on : Sept 18, 2012, 11:24:21 AM
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
    $(document).ready( function(){
        var status = $('#kategoriBarang').val();
        //        alert("status :"+status);
        if(status == "T"){
            document.getElementById("bukanBarangElektronik").checked = true;
        }else if(status == "E"){
            document.getElementById("barangElektronik").checked = true;
        }
    });

    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshPageBarangOperasi();
            self.close();
        },'html');

    }

    function validateForm(){
        if($('#namaBarangan').val()=="")
        {
            alert('Sila masukkan nama barang terlebih dahulu');
            $('#namaBarangan').focus();
            return false;
        }
        if($('#jenisBarangan').val()=="")
        {
            alert('Sila masukkan jenis barang terlebih dahulu');
            $('#jenisBarangan').focus();
            return false;
        }
        if($('#modelBarangan').val()=="")
        {
            alert('Sila masukkan model barang terlebih dahulu');
            $('#modelBarangan').focus();
            return false;
        }
        if($('#penggunaSerahan').val()=="")
        {
            alert('Sila pilih penerima barang terlebih dahulu');
            $('#penggunaSerahan').focus();
            return false;
        }
        
        if(document.getElementById("bukanBarangElektronik").checked == false && document.getElementById("barangElektronik").checked == false)
        {
            alert('Sila pilih kategori barang terlebih dahulu');
            return false;
        }
        
        return true;
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

 

 
</script>
<s:form beanclass="etanah.view.penguatkuasaan.SerahanBarangRampasanActionBean">
    <s:messages />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Barangan Semasa Operasi
            </legend>
            <c:if test="${edit}">
                <div class="content">
                    <p>
                        <label><em>*</em>Nama Barang :</label>
                        <s:text name="namaBarangan" id="namaBarangan" size="40" maxlength="50" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                    </p>
                    <p>
                        <label><em>*</em>Jenis Barang :</label>
                        <s:text name="jenisBarangan" id="jenisBarangan" size="40" maxlength="20" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                    </p>
                    <p>
                        <label><em>*</em>Model Barang :</label>
                        <s:text name="modelBarangan" id="modelBarangan" size="40" maxlength="20" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                    </p>
                    <p>
                        <label>Kuantiti Barang :</label>
                        <s:text name="kuantitiBarangan" id="kuantitiBarangan" size="5" maxlength="5" onkeyup="validateNumber(this,this.value);"/>&nbsp;
                    </p>
                    <p>
                        <label><em>*</em>Penerima Barang:</label>
                        <s:select name="penggunaSerahan" id="penggunaSerahan">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${actionBean.senaraiPengguna}" label="nama" value="idPengguna" sort="nama" />
                        </s:select>
                    </p>
                    <p>
                        <label>Kategori Barang : </label>
                        <input type="radio" name="jenisBarang" id="barangElektronik" value="E" />Elektronik
                        <input type="radio" name="jenisBarang" id="bukanBarangElektronik" value="T" />Bukan Elektronik
                        <input type="hidden" value="${actionBean.jenisBarang}" id="kategoriBarang">
                    </p>

                </div>
                <p align="center">
                    <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                    <s:hidden name="idOperasi"/>
                    <s:hidden name="idBarangOperasi"/>
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                    <s:button class="btn" onclick="if(validateForm())save(this.name,this.form);" name="simpanBarangOperasi" value="Simpan"/>
                    <s:submit class="btn"  value="Tutup" name="closePopup" onclick="self.close();" />
                </p>
                <br/><br/>
            </c:if>

            <c:if test="${view}">
                <div class="content">
                    <p>
                        <label>Nama Barang :</label>
                        ${actionBean.operasiBarangPenguatkuasaan.nama}&nbsp;
                    </p>
                    <p>
                        <label>Jenis Barang :</label>
                        ${actionBean.operasiBarangPenguatkuasaan.jenis}&nbsp;
                    </p>
                    <p>
                        <label>Model Barang :</label>
                        ${actionBean.operasiBarangPenguatkuasaan.model}&nbsp;
                    </p>
                    <p>
                        <label>Kuantiti Barang :</label>
                        ${actionBean.operasiBarangPenguatkuasaan.kntt}&nbsp;
                    </p>
                    <p>
                        <label>Penerima Barang:</label>
                        ${actionBean.operasiBarangPenguatkuasaan.pengguna.nama}&nbsp;

                    </p>
                    <p>
                        <label>Kategori Barang:</label>
                        <c:if test="${actionBean.operasiBarangPenguatkuasaan.kategoriBarangOperasi eq 'T'}">
                            Bukan Elektronik&nbsp;
                        </c:if>
                        <c:if test="${actionBean.operasiBarangPenguatkuasaan.kategoriBarangOperasi eq 'E'}">
                            Elektronik&nbsp;
                        </c:if>


                    </p>
                    <br><br>
                    <p align="center">
                        <s:submit class="btn"  value="Tutup" name="closePopup" onclick="self.close();" />
                    </p>
                </div>
            </c:if>

        </fieldset>
    </div>


</s:form>

