<%-- 
    Document   : popup_edit_sempadan
    Created on : Dis 22, 2011, 4:09:37 PM
    Author     : sitifariza.hanim
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
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

        var idHakmilik = $('#hakmilik').val();
        //alert("id hakmilik : "+idHakmilik);
        if(idHakmilik == ''){
            //tiada hakmilik
            document.getElementById("tiadaHakmilikDiv").style.visibility = 'visible';
            document.getElementById("tiadaHakmilikDiv").style.display = '';

            document.getElementById("resultHakmilikDiv").style.visibility = 'hidden';
            document.getElementById("resultHakmilikDiv").style.display = 'none';
            document.getElementById('kodKategoriTanah').disabled = true;
            document.getElementById('kodLot').disabled = true;
            document.getElementById('noLot').disabled = true;
        }else{
            //ada hakmilik
            document.getElementById("tiadaHakmilikDiv").style.visibility = 'hidden';
            document.getElementById("tiadaHakmilikDiv").style.display = 'none';

            document.getElementById("resultHakmilikDiv").style.visibility = 'visible';
            document.getElementById("resultHakmilikDiv").style.display = '';

            var kodLot = $('#kodLot').val();
            document.getElementById("kodLotCarian").value = kodLot;
            document.getElementById('kodLotCarian').disabled = true;

            var kodKategoriTanah = $('#kodKategoriTanah').val();
            document.getElementById("kodKategoriTanahCarian").value = kodKategoriTanah;
            document.getElementById('kodKategoriTanahCarian').disabled = true;
        }

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

    function simpan2(event, f){
        alert("Anda pasti untuk simpan?");
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(html){
            $("#senaraiSempadan",opener.document).replaceWith($('#senaraiSempadan', $(html)));

        },'html');
        self.opener.refreshListSempadan();
        self.close();
    }
    function semakIdHakmilik(){
        var idHakmilik= $("#hakmilik").val();
        alert("hai : " +idHakmilik);
        if(idHakmilik != ""){
            alert("tak null");
            $.get('${pageContext.request.contextPath}/penguatkuasaan/jenis_sempadan?semakIdHakmilik&idHakmilik='+idHakmilik,
            function(data){
                alert(data);
                if(data == "exist"){
                    alert("Id Hakmilik wujud");
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
<s:form name="form3" id="form" beanclass="etanah.view.penguatkuasaan.JenisSempadanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
        <s:hidden name="idLaporan" value="${actionBean.laporanTanahSempadan.laporanTanah.idLaporan}"/>
        <s:hidden name="idLaporTanahSpdn" value="${actionBean.laporanTanahSempadan.idLaporTanahSpdn}"/>
        <fieldset class="aras1">
            <legend>
                Maklumat Sempadan
            </legend>
            <div class="content">
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
                    <s:text name="idHakmilik" id="hakmilik" onblur="carianHakmilik()"/>
                </p>

                <div id="resultHakmilikDiv">
                    <p>
                        <label>Kegunaan :</label>

                        <s:select name="kodKategoriTanahCarian" id="kodKategoriTanahCarian" disabled="true">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" sort="nama"/>
                        </s:select>&nbsp;
                    </p>
                    <p>
                        <label>No Lot :</label>
                        <s:text name="noLot" id="noLot" onkeyup="validateNumber(this,this.value);" readonly="true"/> &nbsp;
                        <s:hidden name="statusCarian" id="statusCarian"/>
                        <s:hidden name="kodLot" id="kodLot"/>
                        <s:hidden name="kodKategoriTanah" id="kodKategoriTanah"/>
                    </p>
                    <p>
                        <label>Kod Lot:</label>
                        <s:select name="kodLotCarian" id="kodLotCarian">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodLot}" label="nama" value="kod" sort="nama" />
                        </s:select>
                    </p>
                </div>
                <%--<s:text name="kodLot" id="kodLot"/>--%>
                <div id="tiadaHakmilikDiv">
                    <p>
                        <label>Kegunaan :</label>
                        <s:select name="kodKategoriTanah" id="kodKategoriTanah">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" />
                        </s:select>&nbsp;
                    </p>
                    <p>
                        <label>No Lot :</label>
                        <s:text name="noLot" id="noLotTanpaHakmilik" size="10" onkeyup="validateNumber(this,this.value);"/> &nbsp;
                    </p>
                    <p>
                        <label>Kod Lot:</label>
                        <s:select name="kodLot" id="kodLotTanpaHakmilik">
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
                    <s:button name="edit" class="btn" value="Simpan" onclick="simpan2(this.name,this.form);"/>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
                <br>
            </div>
        </fieldset>
    </div>
</s:form>
