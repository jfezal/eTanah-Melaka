<%-- 
    Document   : popup_tambah_hakmilik
    Created on : Jan 3, 2012, 11:24:21 AM
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
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript">
    $(document).ready( function(){
        self.opener.refreshTanahMilik(); 
    });

    function save(event, f){

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(html){
            $("#tanahMilikDiv",opener.document).replaceWith($('#tanahMilikDiv', $(html)));
        },'html');
        self.opener.refreshTanahMilik();
        self.close();

    }

    function simpan1(event, f){
        alert("Anda pasti untuk simpan?");
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(html){
            $("#tanahMilikDiv",opener.document).replaceWith($('#tanahMilikDiv', $(html)));
        },'html');
        self.opener.refreshTanahMilik();
        self.close();
    }

    function validateForm(){
        var bil = 0;
        if($('#noLot').val() != "" || $('#kodLot').val() != "" || $('#kategoriTanah').val() != "" 
            || $('#luas').val() != "" || $('#kodLuas').val() != "" || $('#kodDun').val() != "" || $('#badanPengawal').val() != "" || $('#catatan').val() != ""){
            bil++;
        }
        
        //alert(bil);
        if(bil == 0){
            alert("Sila isikan maklumat");
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
<s:form beanclass="etanah.view.penguatkuasaan.LaporanTanahActionBean">
    <s:messages />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tanah Kerajaan/Rizab
            </legend>
            <div class="content">
                <p>
                    <label>No. Lot :</label>
                    <s:text name="noLot" id="noLot" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;
                </p>
                <p>
                    <label>Kod Lot :</label>
                    <s:select name="kodLot" id="kodLot">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodLot}" label="nama" value="kod" sort="nama" />
                    </s:select>
                </p>
                <p>
                    <label>Kategori Tanah :</label>
                    <s:select name="kategoriTanah" id="kategoriTanah">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" sort="nama" />
                    </s:select>
                </p>
                <p>
                    <label>Luas :</label>
                    <s:text name="luas" id="luas" onkeyup="validateNumber(this,this.value);" maxlength="7"/>
                    <s:select name="kodLuas" id="kodLuas">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodUOMByLuas}" label="nama" value="kod" sort="nama" />
                    </s:select>
                </p>
                  <p>
                    <label>Kod PBT :</label>
                    <s:select name="kodPBT" id="kodPBT">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodPBT}" label="nama" value="kod" sort="nama" />
                    </s:select>
                </p>
                <c:if test = "${actionBean.kodNegeri eq '04'}">
                    <p>
                        <label>Kod Dun :</label>
                        <s:select name="kodDun" id="kodDun">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodDUN}" label="nama" value="kod" sort="nama" />
                        </s:select>
                    </p>
                </c:if>
                <p>
                    <label>Badan Pengawal Tanah Kerajaan/Rezab :</label>
                    <s:text name="badanPengawal" id="badanPengawal" size="50" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;
                </p>
                <p>
                    <label>Catatan :</label>
                    <s:textarea name="catatan" id="catatan" cols="50" rows="5" onchange="this.value=this.value.toUpperCase();"/>
                </p>

                <br/><br/>
                <p><label>&nbsp;</label>
                    <s:button class="btn"  name="simpanTanahMilik" onclick="if(validateForm())save(this.name,this.form);" value="Simpan"/>
                    <s:button name="tutup" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    <s:hidden name="id"/>


                </p>


            </div>

        </fieldset>
    </div>

</s:form>

