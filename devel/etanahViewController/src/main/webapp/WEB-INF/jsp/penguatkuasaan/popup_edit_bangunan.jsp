<%-- 
    Document   : popup_edit_bangunan
    Created on : Dis 22, 2011, 4:09:37 PM
    Author     : sitifariza.hanim
    Modifify by: ctzainal
--%>
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
        self.opener.refreshListBangunan(); 
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

    function test(f) {
        $(f).clearForm();
    }



    function simpan2(event, f){
        alert("Anda pasti untuk simpan?");
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(html){
            $("#senaraiBangunan",opener.document).replaceWith($('#senaraiBangunan', $(html)));

        },'html');
        self.opener.refreshListBangunan();
        self.close();
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<s:form name="form3" id="form" beanclass="etanah.view.penguatkuasaan.JenisBangunanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
        <fieldset class="aras1">
            <legend>
                Maklumat Bangunan
            </legend>
            <div class="content">
                <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                <s:hidden name="idLaporBangunan" id="idLaporBangunan" />
                <p>
                    <label>Jenis Bangunan :</label>
                    <s:select name="jenisBangunan" id="jenisBangunan">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="SM">Sementara</s:option>
                        <s:option value="SK">Separuh Kekal</s:option>
                        <s:option value="KK">Kekal</s:option>
                        <s:option value="LL">Lain-lain</s:option>

                    </s:select>
                    &nbsp;
                </p>


                <p>
                    <label>Ukuran :</label>
                    <s:text name="ukuran" id="ukuran" onkeyup="validateNumber(this,this.value);" size="5"/> x <s:text name="ukuranTemp" id="ukuran" onkeyup="validateNumber(this,this.value);" size="5"/>
                    <font color="red" size="1">(Panjang) x (Lebar)</font>
                </p>

                <p>
                    <label>Unit Ukuran :</label>
                    <s:select name="uomUkuran" id="uomUkuran">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodUOM}" label="nama" value="kod" />
                    </s:select>&nbsp;
                </p>
                <p>
                    <label>Nilai (RM) :</label>
                    <s:text name="nilai" id="nilai" formatPattern="0.00" onkeyup="validateNumber(this,this.value);" />
                </p>
                <p>
                    <label>Nama Pemunya :</label>
                    <s:text name="namaPemunya" id="namaPemunya" onkeyup="this.value=this.value.toUpperCase();" size="30" maxlength="20" />
                </p>
                <p>
                    <label>Nama Penyewa :</label>
                    <s:text name="namaPenyewa" id="namaPenyewa" onkeyup="this.value=this.value.toUpperCase();" size="30" maxlength="20" />
                </p>

                <p><label>&nbsp;</label>
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                    <s:button name="edit" class="btn" value="Simpan" onclick="simpan2(this.name,this.form);"/>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
                <br>
            </div>
        </fieldset>
    </div>
</s:form>
