<%-- 
    Document   : popup_notis
    Created on : Mar 22, 2011, 4:00:21 PM
    Author     : latifah.iskak
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
        if($('#kodNegeri').val() == '04' && $('#kodUrusan').val() == '49'){
            if($('#caraPenghantaran').val() == '' || $('#caraPenghantaran').val() != 'WA'){
                document.getElementById("maklumatTampalDiv").style.visibility = 'hidden';
                document.getElementById("maklumatTampalDiv").style.display = 'none';
            }
            
            checkWarta($('#caraPenghantaran').val());
        }
    });

    function save(event, f){

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshPageNotis();
    <%--alert("Maklumat telah berjaya disimpan.");--%>
                self.close();
            },'html');

        }

        function validateForm(){

            if($('#jenisNotis').val() == ''){
                alert("Sila pilih jenis notis");
                $('#jenisNotis').focus();
                return false;
            }
            if($('#penghantarNotis').val() == ''){
                alert("Sila pilih penghantar notis");
                $('#penghantarNotis').focus();
                return false;
            }
            if($('#statusPenyampaian').val() == ''){
                alert("Sila pilih status penyampaian");
                $('#statusPenyampaian').focus();
                return false;
            }
            if($('#caraPenghantaran').val() == ''){
                alert("Sila pilih cara penghantaran");
                $('#caraPenghantaran').focus();
                return false;
            }
            if($('#jenisNotis').val() == 'PDT' 
                || $('#jenisNotis').val() == '7A' 
                || $('#jenisNotis').val() == '7B' 
                || $('#jenisNotis').val() == '7E' 
                || $('#jenisNotis').val() == '7F'){
                if($('#tarikhTerima').val() == ''){
                    alert("Sila masukkan tarikh terima");
                    $('#tarikhTerima').focus();
                    return false;
                }
            }

    <%--            if($('#tarikhHantar').val() == ''){
                    alert("Sila masukkan tarikh hantar");
                    $('#tarikhHantar').focus();
                    return false;
                }--%>

                     

    <%--            if($('#tarikhTerima').val() == ''){
                    alert("Sila masukkan tarikh terima");
                    $('#tarikhTerima').focus();
                    return false;
                }--%>
   
       
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

                    function convertDay(value){
                        var vsplit = value.split('/');
                        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
                        var date = new Date(fulldate);
                        var today =  new Date();
                        if (date > today){
                            alert("Sila masukkan tarikh hari ini atau tarikh hari sebelumnya.");
                            $("#tarikhTerima").val("");
                        }
                    }
        
                    function checkWarta(i){
                        //alert(i);
                        if($('#kodNegeri').val() == '04' && $('#kodUrusan').val() == '49'){
                            if(i == 'WA'){
                                document.getElementById("maklumatTampalDiv").style.visibility = 'visible';
                                document.getElementById("maklumatTampalDiv").style.display = '';
                            }else{
                                document.getElementById("maklumatTampalDiv").style.visibility = 'hidden';
                                document.getElementById("maklumatTampalDiv").style.display = 'none';
                            }
                        }
                    }
                    
                    function chooseJenisNotis(val){
                        if(val == 'PDT' || val == '7A' || val == '7B' || val == '7E' || val == '7F'){
                            document.getElementById("mandatoryTarikhTerimaDiv").style.visibility = 'visible';
                            document.getElementById("mandatoryTarikhTerimaDiv").style.display = '';
                            
                            document.getElementById("notMandatoryTarikhTerimaDiv").style.visibility = 'hidden';
                            document.getElementById("notMandatoryTarikhTerimaDiv").style.display = 'none';
                        }else{
                            document.getElementById("notMandatoryTarikhTerimaDiv").style.visibility = 'visible';
                            document.getElementById("notMandatoryTarikhTerimaDiv").style.display = '';
                            
                            document.getElementById("mandatoryTarikhTerimaDiv").style.visibility = 'hidden';
                            document.getElementById("mandatoryTarikhTerimaDiv").style.display = 'none';
                        }
                    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>

<s:form  beanclass="etanah.view.penguatkuasaan.NotisBuktiPenyampaianActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Notis Penyampaian
            </legend>
            <s:hidden name="kodNegeri" id="kodNegeri"/>
            <s:hidden name="permohonan.kodUrusan.kod" id="kodUrusan"/>

            <div class="content">

                <p>
                    <label><em>*</em>Jenis Notis :</label>
                    <s:select name="jenisNotis" id="jenisNotis" onchange="chooseJenisNotis(this.value);">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodNotis}" label="nama" value="kod" sort="nama"/>
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
                        <s:options-collection collection="${listUtil.senaraiKodStatusPenyampaian}" label="nama" value="kod" />
                    </s:select>&nbsp;
                </p>

                <p>
                    <label><em>*</em>Cara Penghantaran :</label>
                    <s:select name="caraPenghantaran" id="caraPenghantaran" onchange="checkWarta(this.value);">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodCaraPenghantaran}" label="nama" value="kod" />
                    </s:select>&nbsp;
                </p>

                <p>
                    <label>Tarikh Hantar:</label>
                    <s:text name="tarikhHantar"  class="datepicker" formatPattern="dd/MM/yyyy" id="tarikhHantar"/>&nbsp;
                    <font color="red" size="1">cth : hh / bb / tttt</font>
                </p>
                <div id="mandatoryTarikhTerimaDiv"  style="visibility: hidden; display: none">
                    <p>
                        <label><em>*</em>Tarikh Terima:</label>
                        <s:text name="tarikhTerima"  class="datepicker" formatPattern="dd/MM/yyyy" id="tarikhTerima" onchange="convertDay(this.value);"/>&nbsp;
                        <font color="red" size="1">cth : hh / bb / tttt</font>
                    </p>
                </div>
                <div id="notMandatoryTarikhTerimaDiv">
                    <p>
                        <label>Tarikh Terima:</label>
                        <s:text name="tarikhTerima"  class="datepicker" formatPattern="dd/MM/yyyy" id="tarikhTerimaNotMandatory"/>&nbsp;
                        <font color="red" size="1">cth : hh / bb / tttt</font>
                    </p>
                </div>

                <div id="maklumatTampalDiv">
                    <p>
                        <label>Tarikh Tampal:</label>
                        <s:text name="tarikhTampal"  class="datepicker" formatPattern="dd/MM/yyyy" id="tarikhTampal"/>&nbsp;
                        <font color="red" size="1">cth : hh / bb / tttt</font>
                    </p>
                    <p>
                        <label>Nama Penampal :</label>
                        <s:text name="namaTampal" id="namaTampal" size="50" onkeyup="this.value=this.value.toUpperCase();" />&nbsp;
                    </p>

                    <p>
                        <label>Lokasi Ditampal :</label>
                        <s:text name="tempatTampal"  id="tempatTampal" size="50" onkeyup="this.value=this.value.toUpperCase();" />&nbsp;
                    </p>
                </div>

                <p>
                    <label>Catatan :</label>
                    <s:textarea name="catatan" id="catatan" rows="5" cols="50" onkeyup="this.value=this.value.toUpperCase();" />&nbsp;
                </p>


                <p><label>&nbsp;</label>
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                    <s:button name="simpan" id="simpan" class="btn" value="Simpan" onclick="if(validateForm())save(this.name,this.form);"/>
                    <%-- <s:button name="simpanPopup" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>--%>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>

                <br>
            </div>
        </fieldset>
    </div>
</s:form>
