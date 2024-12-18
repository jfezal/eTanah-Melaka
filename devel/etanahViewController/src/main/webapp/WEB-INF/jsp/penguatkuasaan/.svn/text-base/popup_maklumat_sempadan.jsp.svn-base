<%-- 
    Document   : popup_maklumat_sempadan
    Created on : Dis 23, 2011, 11:01:15 AM
    Author     : sitifariza.hanim
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

        //refresh parent window
        self.opener.refreshListSempadan();
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


    function simpan1(event, f){
        alert("Anda pasti untuk simpan?");
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(html){
            $("#senaraiSempadan",opener.document).replaceWith($('#senaraiSempadan', $(html)));
//            $('#page_div',opener.document).html(html);
        },'html');
        self.opener.refreshListSempadan();
        self.close();
    }

    function semakIdHakmilik(){
        var idHakmilik= $("#hakmilik").val();
        if(idHakmilik != ""){
            $.get('${pageContext.request.contextPath}/penguatkuasaan/jenis_sempadan?semakIdHakmilik&idHakmilik='+idHakmilik,
            function(data){

                if(data == "exist"){
                }else{
                    alert("Id Hakmilik tidak wujud. Sila masukkan Id Hakmilik yang betul.");
                }
            }, 'html');

        }

    }

    function carianHakmilik(){
        var idHakmilik= $("#hakmilik").val();
        if(idHakmilik != ""){
            $.get('${pageContext.request.contextPath}/penguatkuasaan/jenis_sempadan?findHakmilik&idHakmilik='+idHakmilik,
            function(data){
                $("#resultHakmilikDiv").replaceWith($('#resultHakmilikDiv', $(data)));
                var status = $('#statusCarian').val();
                if(status == "TW"){
                    alert("Id Hakmilik tidak wujud. Sila masukkan Id Hakmilik yang betul.");
                    document.getElementById("hakmilik").value = '';
                    document.getElementById("kodKategoriTanah").value = '';
                    document.getElementById("noLot").value = '';
                    document.getElementById("kodLot").value = '';
                }else{

                    var kodLotCarian = $('#kodLotCarian').val();
                    document.getElementById("kodLot").value = kodLotCarian;
                    
                    var kodKategoriTanahCarian = $('#kodKategoriTanahCarian').val();
                    document.getElementById("kodKategoriTanah").value = kodKategoriTanahCarian;

                    document.getElementById("tiadaHakmilikDiv").style.visibility = 'hidden';
                    document.getElementById("tiadaHakmilikDiv").style.display = 'none';

                    document.getElementById("resultHakmilikDiv").style.visibility = 'visible';
                    document.getElementById("resultHakmilikDiv").style.display = '';
                }
            }, 'html');

        }

    }
    
    function limitText(limitField, limitNum) {
        if (limitField.value.length > limitNum) {
            limitField.value = limitField.value.substring(0, limitNum);
        }
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<s:messages/>
<s:errors/>
<s:form  name="form2" beanclass="etanah.view.penguatkuasaan.JenisSempadanActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Sempadan
            </legend>

            <div class="content">
                <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                <s:hidden name="idLapor" id="idLapor"/>
                <s:hidden name="idLaporTanahSpdn"/>
                <s:hidden name="jenisLaporan" id="jenisLaporan"/>
                <p>
                    <label>Jenis Sempadan :</label>
                    <s:select name="jenisSempadan" id="jenisSempadan">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="U">Utara</s:option>
                        <s:option value="B">Barat</s:option>
                        <s:option value="T">Timur</s:option>
                        <s:option value="S">Selatan</s:option>

                    </s:select>
                    &nbsp;
                </p>


                <p>
                    <label>Hakmilik :</label>
                    <s:text name="idHakmilik" id="hakmilik" onkeyup="this.value=this.value.toUpperCase();" onblur="carianHakmilik();" />
                </p>


                <div id="resultHakmilikDiv" style="visibility: hidden; display: none">
                    <p>
                        <label>Kegunaan :</label>

                        <s:select name="kodKategoriTanahCarian" id="kodKategoriTanahCarian" disabled="true">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" sort="nama"/>
                        </s:select>&nbsp;
                    </p>
                    <p>
                        <label>No Lot :</label>
                        <s:text name="noLot" id="noLot" size="10" onkeyup="validateNumber(this,this.value);" readonly="true"/> &nbsp;
                        <s:hidden name="statusCarian" id="statusCarian"/>
                        <s:hidden name="kodLotCarian" id="kodLotCarian"/>
                        <s:hidden name="kodLot" id="kodLot"/>
                        <s:hidden name="kodKategoriTanah" id="kodKategoriTanah"/>
                    </p>
                    <p>
                        <label>Kod Lot:</label>
                        <s:select name="kodLotCarian" id="kodLotCarian" disabled="true">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodLot}" label="nama" value="kod" sort="nama" />
                        </s:select>
                    </p>
                </div>
                <div id="tiadaHakmilikDiv">
                    <p>
                        <label>Kegunaan :</label>
                        <s:select name="kodKategoriTanah" id="kodKategoriTanah">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" sort="nama" />
                        </s:select>&nbsp;
                    </p>
                    <p>
                        <label>No Lot :</label>
                        <s:text name="noLot" id="noLot" size="10" onkeyup="validateNumber(this,this.value);"/> &nbsp;
                    </p>
                    <p>
                        <label>Kod Lot:</label>
                        <s:select name="kodLot" id="kodLot">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodLot}" label="nama" value="kod" sort="nama" />
                        </s:select>
                    </p>
                </div>

                <p>
                    <label>Catatan :</label>
                    <s:textarea name="catatan" id="catatan" cols="50" rows="6" onkeydown="limitText(this,150);" onkeyup="limitText(this,150);"/>
                </p>
                <p><label>&nbsp;</label>
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                    <s:button name="simpan" id="simpan" class="btn" value="Simpan" onclick="simpan1(this.name,this.form);"/>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
                <br>
            </div>
        </fieldset>
    </div>
</s:form>
