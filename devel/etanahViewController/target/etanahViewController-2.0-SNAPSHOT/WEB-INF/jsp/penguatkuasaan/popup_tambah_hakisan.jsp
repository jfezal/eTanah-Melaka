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
        
    <c:if test="${actionBean.statusView eq false}">
            var status = $('#statusTanah').val();
            var statusEdit = $('#statusEdit').val();
            var statusHakmilik = $('#statusHakmilik').val();
            var statusHakisan = $('#statusHakisan').val();
            //        alert("statusHakmilik :"+statusHakmilik);
        
            if(statusEdit == 'false'){
                if(status == "H"){
                    document.getElementById("tanahHakmilik").checked = true;
                }else if(status == "K"){
                    document.getElementById("tanahKerajaan").checked = true;
                }else{
                    status = "H";
                    document.getElementById("tanahHakmilik").checked = true;
                }
                changeStatusTanah(status);
            }
        
            if(statusEdit == 'true'){
                document.getElementById("tanahHakmilik").disabled = true;
                document.getElementById("tanahKerajaan").disabled = true;
            
                if(statusHakmilik == "H"){
                    document.getElementById("tanahHakmilik").checked = true;
                
                    $('#kodKategoriTanahCarian').val($('#kodKategoriTanahHakmilik').val());
                    $('#kodLotCarian').val($('#kodLotHakmilik').val());
                    $('#kodLuasCarian').val($('#kodLuasHakmilik').val());
                
                }else if(statusHakmilik == "K"){
                    document.getElementById("tanahKerajaan").checked = true;
                
                    $('#kodKategoriTanahHakmilik').attr("disabled", true);
                    $('#kodLotHakmilik').attr("disabled", true);
                    $('#kodLuasHakmilik').attr("disabled", true);
                    $('#noLot').attr("disabled", true);
                    $('#luas').attr("disabled", true);
                }
            
                if(statusHakisan == 'S'){
                    document.getElementById("hakisanSebahagian").checked = true;
                }else{
                    document.getElementById("hakisanPenuh").checked = true;
                }
                changeStatusTanah(statusHakmilik);
            }
    </c:if>

        });
    

        function validate(){
            //        alert(document.getElementById("tanahKerajaan").checked);
            if(document.getElementById("tanahKerajaan").checked == true){
                if($('#noLotKerajaan').val() == ''){
                    alert("Sila masukkan no lot.");
                    $('#noLotKerajaan').focus();
                    return false;
                }
                if($('#kodLotKerajaan').val() == ''){
                    alert("Sila pilih kod lot.");
                    $('#kodLotKerajaan').focus();
                    return false;
                }
                if($('#luasKerajaan').val() == ''){
                    alert("Sila masukkan luas.");
                    $('#luasKerajaan').focus();
                    return false;
                }
                if($('#kodLuasKerajaan').val() == ''){
                    alert("Sila pilih kod luas.");
                    $('#kodLuasKerajaan').focus();
                    return false;
                }
            }

            return true;
        }

        function save(event, f){
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.opener.refreshPageHakisan();
                self.close();
            },'html');

        }
    
        function carianHakmilik(){
            var idHakmilik= $("#hakmilik").val();
            if(idHakmilik != ""){
                $.get('${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah?findHakmilik&idHakmilik='+idHakmilik,
                function(data){
                    $("#resultHakmilikDiv").replaceWith($('#resultHakmilikDiv', $(data)));
                    $("#msgInfoDiv").replaceWith($('#msgInfoDiv', $(data)));
                    var status = $('#statusCarian').val();
                    //                    alert(status);
                    if(status == "TW"){
                        alert("Id Hakmilik tidak wujud. Sila masukkan Id Hakmilik yang betul.");
                        document.getElementById("hakmilik").value = '';
                        document.getElementById("kodKategoriTanah").value = '';
                        document.getElementById("noLot").value = '';
                        document.getElementById("kodLot").value = '';
                    }else{
                        $('#kodKategoriTanahHakmilik').val($('#kodKategoriTanahCarian').val());
                        $('#kodLotHakmilik').val($('#kodLotCarian').val());
                        $('#kodLuasHakmilik').val($('#kodLuasCarian').val());
                    }
                }, 'html');

            }else{
                alert("Sila masukkan id hakmilik");
                $("#hakmilik").focus();
            }

        }
    
        function changeStatusTanah(value){
            //alert("val :"+value);
            if(value == "H"){
                //if have id hakmilik
                $('#tanahMilikDiv').hide();
                $('#resultHakmilikDiv').show();
            }else if(value == "K"){
                //if dont have id hakmilik
                $('#tanahMilikDiv').show();
                $('#resultHakmilikDiv').hide();
            }else{
                $('#resultHakmilikDiv').show();
                $('#tanahMilikDiv').hide();
                document.getElementById("tanahHakmilik").checked = true;
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

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<s:form beanclass="etanah.view.penguatkuasaan.LaporanTanahActionBean">
    <div id ="msgInfoDiv">
        <s:messages/>
        <s:errors/>
    </div>
    <div class="subtitle">
        <c:if test="${actionBean.statusView eq false}">
            <fieldset class="aras1">
                <div>
                    <legend>Maklumat Hakisan Tanah</legend>
                    <p>
                        <label>Status Tanah : </label>
                        <input type="radio" name="statusTanah" id="tanahKerajaan" value="K" onclick="changeStatusTanah('K');" />Tanah Kerajaan/Rezab
                        <input type="radio" name="statusTanah" id="tanahHakmilik" value="H" onclick="changeStatusTanah('H');" />Tanah Milik
                    </p>
                    <div id="resultHakmilikDiv">
                        <p>
                            <label>Hakmilik :</label>
                            <s:text name="idHakmilik" id="hakmilik" onkeyup="this.value=this.value.toUpperCase();"/>
                            <s:button class="btn" value="Cari" name="new" id="new" onclick="carianHakmilik();"/>
                        </p>

                        <s:hidden name="statusCarian" id="statusCarian"/>
                        <s:hidden name="kodLot" id="kodLotHakmilik"/>
                        <s:hidden name="kodLuas" id="kodLuasHakmilik"/>
                        <s:hidden name="kodKategoriTanah" id="kodKategoriTanahHakmilik"/>
                        <p>
                            <label>Kategori Tanah :</label>
                            <s:select name="kodKategoriTanahCarian" id="kodKategoriTanahCarian" disabled="true">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" sort="nama"/>
                            </s:select>&nbsp;
                        </p>
                        <p>
                            <label>No Lot :</label>
                            <s:text name="noLot" id="noLot" maxlength="15" size="10" onkeyup="validateNumber(this,this.value);" readonly="true"/> &nbsp;
                        </p>
                        <p>
                            <label>Kod Lot:</label>
                            <s:select name="kodLotCarian" id="kodLotCarian" disabled="true">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodLot}" label="nama" value="kod" sort="nama" />
                            </s:select>
                        </p>
                        <p>
                            <label>Luas :</label>
                            <s:text name="luas" id="luas" size="10" onkeyup="validateNumber(this,this.value);"/> &nbsp;
                        </p>
                        <p>
                            <label>Kod Luas</label>
                            <s:select name="kodLuasCarian" id="kodLuasCarian" disabled="true">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodUOMByLuas}" label="nama" value="kod" sort="nama" />
                            </s:select>
                        </p>
                    </div>

                    <div id="tanahMilikDiv">
                        <p>
                            <label>Kategori Tanah :</label>

                            <s:select name="kodKategoriTanah" id="kodKategoriTanah">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" sort="nama"/>
                            </s:select>&nbsp;
                        </p>
                        <p>
                            <label><em>*</em>No Lot :</label>
                            <s:text name="noLot" id="noLotKerajaan" size="10" maxlength="15" onkeyup="validateNumber(this,this.value);"/> &nbsp;
                        </p>
                        <p>
                            <label><em>*</em>Kod Lot:</label>
                            <s:select name="kodLot" id="kodLotKerajaan">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodLot}" label="nama" value="kod" sort="nama" />
                            </s:select>
                        </p>
                        <p>
                            <label><em>*</em>Luas :</label>
                            <s:text name="luas" id="luasKerajaan" size="10" maxlength="7" onkeyup="validateNumber(this,this.value);"/> &nbsp;
                        </p>
                        <p>
                            <label><em>*</em>Kod Luas</label>
                            <s:select name="kodLuas" id="kodLuasKerajaan">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodUOMByLuas}" label="nama" value="kod" sort="nama" />
                            </s:select>
                        </p>
                        <p>
                            <label>Badan Pengawal Tanah Kerajaan/Rezab :</label>
                            <s:text name="badanPengawal" id="badanPengawal" size="50" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;
                        </p>
                    </div>
                    <c:if test = "${actionBean.kodNegeri eq '04' && actionBean.permohonan.kodUrusan.kod ne '49'}">
                        <p>
                            <label>Kod Dun</label>
                            <s:select name="kodDunHakisan" id="kodDunHakisan">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodDUN}" label="nama" value="kod" sort="nama" />
                            </s:select>
                        </p>
                    </c:if>

                    <p>
                        <label>Catatan :</label>
                        <s:textarea name="catatan" id="catatan" cols="50" rows="5" onchange="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Luas Hakisan:</label>
                        <s:text name="luasHakisan" id="luasHakisan" size="10" maxlength="7" onkeyup="validateNumber(this,this.value);"/> &nbsp;
                        <s:select name="kodLuasHakisan" id="kodLuasHakisan">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodUOMByLuas}" label="nama" value="kod" sort="nama" />
                        </s:select>
                    </p>

                    <p>
                        <label>Jenis Hakisan :</label>
                        <input type="radio" name="jenisHakisan" id="hakisanPenuh" value="P" />Penuh
                        <input type="radio" name="jenisHakisan" id="hakisanSebahagian" value="S"/>Sebahagian

                    </p>
                </div>
            </fieldset>
            <br/>
            <label>&nbsp</label>

            <s:button class="btn" name="simpan" onclick="if(validate())save(this.name,this.form);" value="Simpan"/>
        </c:if>

        <c:if test="${actionBean.statusView eq true}">
            <fieldset class="aras1">
                <div>
                    <legend>Maklumat Hakisan Tanah</legend>
                    <p>
                        <label>Status Tanah : </label>
                        <c:if test="${actionBean.hakmilikPermohonanHakisan.hakmilik eq null}">
                            Tanah Kerajaan/Rezab 
                        </c:if>
                        <c:if test="${actionBean.hakmilikPermohonanHakisan.hakmilik ne null}">
                            Tanah Milik 
                        </c:if>
                    </p>
                    <c:if test="${actionBean.hakmilikPermohonanHakisan.hakmilik ne null}">
                        <p>
                            <label>ID Hakmilik :</label>
                            ${actionBean.hakmilikPermohonanHakisan.hakmilik.idHakmilik}&nbsp;
                        </p>
                    </c:if>
                    <p>
                        <label>Kategori Tanah :</label>
                        ${actionBean.hakmilikPermohonanHakisan.jenisPenggunaanTanah.nama}&nbsp;
                    </p>
                    <p>
                        <label>No Lot :</label>
                        ${actionBean.hakmilikPermohonanHakisan.noLot}&nbsp;
                    </p>
                    <p>
                        <label>Kod Lot:</label>
                        ${actionBean.hakmilikPermohonanHakisan.lot.nama}&nbsp;
                    </p>
                    <p>
                        <label>Luas :</label>
                        ${actionBean.hakmilikPermohonanHakisan.luasTerlibat}&nbsp;
                    </p>
                    <p>
                        <label>Kod Luas :</label>
                        ${actionBean.hakmilikPermohonanHakisan.kodUnitLuas.nama}&nbsp;
                    </p>
                    <!--                    <p>
                                            <label>Kod Dun</label>
                    ${actionBean.hakmilikPermohonanHakisan.kodDUN.nama}&nbsp;
                </p>-->

                    <c:if test="${actionBean.hakmilikPermohonanHakisan.hakmilik eq null}">
                        <p>
                            <label>Badan Pengawal Tanah Kerajaan/Rezab :</label>
                            ${actionBean.hakmilikPermohonanHakisan.lokasi}&nbsp;
                        </p>
                    </c:if>

                    <p>
                        <label>Catatan :</label>
                        ${actionBean.hakmilikPermohonanHakisan.catatan}&nbsp;
                    </p>
                    <p>
                        <label>Luas Hakisan:</label>
                        ${actionBean.hakmilikPermohonanHakisan.luasUkurHalus}  ${actionBean.hakmilikPermohonanHakisan.luasUkurHalusUom.nama}&nbsp;
                    </p>
                    <p>
                        <label>Jenis Hakisan :</label>
                        <c:if test="${actionBean.hakmilikPermohonanHakisan.jenisHakisan eq 'P'}">
                            Hakisan Penuh&nbsp;
                        </c:if>
                        <c:if test="${actionBean.hakmilikPermohonanHakisan.jenisHakisan eq 'S'}">
                            Hakisan Sebahagian&nbsp;
                        </c:if>

                    </p>
                </div>
            </fieldset>
            <br/>
            <label>&nbsp</label>

        </c:if>
        <s:hidden name="idMH" id="idMH"/>
        <input type="hidden" name="statusHakmilik" id="statusHakmilik" value="${actionBean.statusTanah}"/>
        <input type="hidden" name="statusHakisan" id="statusHakisan" value="${actionBean.jenisHakisan}"/>
        <input type="hidden" name="statusEdit" id="statusEdit" value="${actionBean.statusEdit}"/>
        <s:button name="tutup" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>



    </div>
</s:form>
