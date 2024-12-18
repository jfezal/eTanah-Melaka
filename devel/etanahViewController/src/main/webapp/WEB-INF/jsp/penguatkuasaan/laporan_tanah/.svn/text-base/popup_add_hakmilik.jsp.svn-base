<%-- 
    Document   : popup_tambah_hakisan
    Created on : Oct 31, 2012, 9:36:54 AM
    Author     : latifah.iskak
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>

<script type="text/javascript">
    
    $(document).ready(function() {
        self.opener.refreshPageAddHakmilik();
    });
    

    function validate(){
        if($("#statusCarian").val() != "" && $("#statusCarian").val() == "TW"){
            alert("Sila cari no hakmilik yang wujud");
            $("#hakmilik").focus(); 
            return false;
        }
        return true;
    }
    
    function save(event, f){

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $("#multipleHakmilikDiv",opener.document).replaceWith($('#multipleHakmilikDiv', $(data)));
        },'html');
        //        self.opener.refreshPageAddHakmilik();
        opener.refreshV2('main');
        self.close();

    }
    
    function carianHakmilik(){
        var idHakmilik= $("#hakmilik").val();
    
        //        alert(idHakmilik);
        if(idHakmilik != ""){
            $.get('${pageContext.request.contextPath}/penguatkuasaan/laporan_tanahV2?findHakmilikLTNH&idHakmilik='+idHakmilik,
            function(data){
                $("#resultHakmilikDiv").replaceWith($('#resultHakmilikDiv', $(data)));
                $("#msgInfoDiv").replaceWith($('#msgInfoDiv', $(data)));
                var status = $('#statusCarian').val();
                //                alert(status);
                //                if(status == "TW"){
                //                    alert("Id Hakmilik tidak wujud. Sila masukkan Id Hakmilik yang betul.");
                //                    return false;
                //                }
            }, 'html');

        }else{
            alert("Sila masukkan id hakmilik");
            $("#hakmilik").focus();
        }

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
    
    function refreshpage(){    
        NoPrompt();
        opener.refreshV2('main');
        self.close();
    }

</script>

<body>
    <script>
        // Allow the user to be warned by default.
        var allowPrompt = true;
                
        window.onbeforeunload = WarnUser;
        function WarnUser()
        {   
            if(allowPrompt)
                refreshpage();
            if(allowPrompt)
            {
                event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
            }
            else
            {
                // Reset the flag to its default value.
                allowPrompt = true;
            }
        }
        function NoPrompt()
        {
            allowPrompt = false;
        }

    </script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
    <s:form beanclass="etanah.view.penguatkuasaan.LaporanTanahV2ActionBean" name="form">
        <div id ="msgInfoDiv">
            <s:messages/>
            <s:errors/>
        </div>
        <div class="subtitle">
            <fieldset class="aras1">
                <s:hidden name="jenisLaporan" id="jenisLaporan"/>
                <div>
                    <legend>Maklumat Hakmilik</legend>
                    <div id="resultHakmilikDiv">
                        <p>
                            <label>Hakmilik :</label>
                            <s:text name="idHakmilik" id="hakmilik" onkeyup="this.value=this.value.toUpperCase();"/>
                            <font color="red" size="1">cth : 050501GM00000001</font>
                            <s:button class="btn" value="Cari" name="new" id="new" onclick="carianHakmilik();"/>
                        </p>

                        <s:hidden name="statusCarian" id="statusCarian"/>
                        <p>
                            <label>Kategori Tanah :</label>
                            <s:select name="hakmilik.kategoriTanah.kod" id="kodKategoriTanahCarian" disabled="true">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" sort="nama"/>
                            </s:select>&nbsp;
                        </p>
                        <p>
                            <label>No Lot :</label>
                            <s:text name="hakmilik.noLot" id="noLot" maxlength="15" size="10" onkeyup="validateNumber(this,this.value);" readonly="true"/> &nbsp;
                        </p>
                        <p>
                            <label>Kod Lot:</label>
                            <s:select name="hakmilik.lot.kod" id="kodLotCarian" disabled="true">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodLot}" label="nama" value="kod" sort="nama" />
                            </s:select>
                        </p>
                        <p>
                            <label>Luas :</label>
                            <s:text name="hakmilik.luas" id="luas" size="10" readonly="true"/> &nbsp;
                        </p>
                        <p>
                            <label>Kod Luas:</label>
                            <s:select name="hakmilik.kodUnitLuas.kod" id="kodLuasCarian" disabled="true">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodUOMByLuas}" label="nama" value="kod" sort="nama" />
                            </s:select>
                        </p>
                        <p>
                            <label>Daerah:</label>
                            <s:text name="hakmilik.daerah.nama" readonly="true"/> &nbsp;
                        </p>
                        <p>
                            <label>Bandar/Pekan/Mukim:</label>
                            <s:text name="hakmilik.bandarPekanMukim.nama" readonly="true"/> &nbsp;
                        </p>
                        <p>
                            <label>Jenis Penggunaan Tanah:</label>
                            <s:text name="hakmilik.maklumatAtasTanah" readonly="true"/> &nbsp;
                        </p>
                    </div>
                </div>
            </fieldset>
            <br/>
            <label>&nbsp</label>
            <s:hidden name="idMH" id="idMH"/>
            <s:submit name="simpanHakmilik" value="Simpan" class="btn" onclick="NoPrompt();"/>
            <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
        </div>
    </s:form>
