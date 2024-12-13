<%-- 
    Document   : utiliti_popup_edit_notis
    Created on : Sept 8, 2011, 4:00:21 PM
    Author     : ctzainal
--%>


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

    function save(event, f){

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        var idPermohonan = $('#idPermohonan').val();
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
        },'html');
        alert("Maklumat telah berjaya disimpan.");
        self.opener.refreshPageNotis(idPermohonan);
        self.close();
    }

    function validateForm(){

        if($('#jenisNotis').val() == ''){
            alert("Sili pilih jenis notis");
            $('#jenisNotis').focus();
            return false;
        }
        if($('#penghantarNotis').val() == ''){
            alert("Sili pilih penghantar notis");
            $('#penghantarNotis').focus();
            return false;
        }
        if($('#statusPenyampaian').val() == ''){
            alert("Sili pilih status penyampaian");
            $('#statusPenyampaian').focus();
            return false;
        }
        if($('#caraPenghantaran').val() == ''){
            alert("Sili pilih cara penghantaran");
            $('#caraPenghantaran').focus();
            return false;
        }

        return true;
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
    function test(f) {
        $(f).clearForm();
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>

<s:form  beanclass="etanah.view.penguatkuasaan.UtilitiNotisPenyampaianActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Notis Bukti Penyampaian
            </legend>

            <div class="content">

                <p>
                    <label><em>*</em>Jenis Notis :</label>
                    <s:select name="jenisNotis" id="jenisNotis">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodNotis}" label="nama" value="kod" />
                    </s:select>&nbsp;
                </p>

                <p>
                    <label><em>*</em>Nama Penghantar Notis :</label>
                    <s:select name="penghantarNotis" id="penghantarNotis">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiPenghantarNotis}" label="nama" value="idPenghantarNotis" />
                    </s:select>&nbsp;
                </p>

                <p>
                    <label>Nama Penerima Notis :</label>
                    <s:text name="penerimaNotis" id="penerimaNotis" size="50" onkeyup="this.value=this.value.toUpperCase();" />&nbsp;
                </p>

                <p>
                    <label><em>*</em>Status Penyampaian :</label>
                    <s:select name="statusPenyampaian" id="statusPenyampaian">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodStatusTerima}" label="nama" value="kod" />
                    </s:select>&nbsp;
                </p>

                <p>
                    <label><em>*</em>Cara Penghantaran :</label>
                    <s:select name="caraPenghantaran" id="caraPenghantaran">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodCaraPenghantaran}" label="nama" value="kod" />
                    </s:select>&nbsp;
                </p>

                <p>
                    <label><em>*</em>Tarikh Hantar:</label>
                    <s:text name="tarikhHantar"  class="datepicker" formatPattern="dd/MM/yyyy" id="tarikhHantar"/>&nbsp;
                    <font color="red" size="1">cth : hh / bb / tttt</font>
                </p>

                <p>
                    <label><em>*</em>Tarikh Terima:</label>
                    <s:text name="tarikhTerima"  class="datepicker" formatPattern="dd/MM/yyyy" id="tarikhTerima"/>&nbsp;
                    <font color="red" size="1">cth : hh / bb / tttt</font>
                </p>

                <p>
                    <label><em>*</em>Tarikh Tampal:</label>
                    <s:text name="tarikhTampal"  class="datepicker" formatPattern="dd/MM/yyyy" id="tarikhTampal"/>&nbsp;
                    <font color="red" size="1">cth : hh / bb / tttt</font>
                </p>
                <p>
                    <label>Nama Penampal :</label>
                    <s:text name="namaTampal" id="namaTampal" size="50" onkeyup="this.value=this.value.toUpperCase();" />&nbsp;
                </p>

                <p>
                    <label>Lokasi Penampal :</label>
                    <s:text name="tempatTampal"  id="tempatTampal" size="50" onkeyup="this.value=this.value.toUpperCase();" />&nbsp;
                </p>

                <p>
                    <label>Catatan :</label>
                    <s:textarea name="catatan" id="catatan" rows="5" cols="50" onkeyup="this.value=this.value.toUpperCase();" />&nbsp;
                </p>


                <p><label>&nbsp;</label>
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                    <s:button name="editNotis" id="simpan" class="btn" value="Simpan" onclick="if(validateForm())save(this.name,this.form);"/>
                    <%--<s:submit name="editNotis" id="simpan" class="btn" value="Simpan" onclick="return validateForm();"/>--%>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.notis.permohonan.idPermohonan}"/>
                    <s:hidden name="idNotis" value="${actionBean.notis.idNotis}"/>
                </p>

                <br>
            </div>
        </fieldset>
    </div>
</s:form>
