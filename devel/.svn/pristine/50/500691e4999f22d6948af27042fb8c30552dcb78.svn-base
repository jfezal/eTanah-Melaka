<%--
    Document   : Pu
    Created on : Jul 26, 2010, 3:23:20 PM
    Author     : Rohan
    Modify by  : Siti Fariza Hanim (12042011)
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />


<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:134px;
        margin-right:0.5em;
        padding-top: 2px;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:20em;
    }

    .pLabel {
        color:#003194;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:100px;
    }

    .pInfo {
        color:#003194;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:-7em;

    }

    .labelItem {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
    }
</style>

<script type="text/javascript">

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
        var strReturn = "0.00";
        var strBuffer = "0";
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

    <%--  function updateTotal(){
          document.getElementById("jumlahPengecualian").value = parseFloat(document.getElementById("jumlahPengecualian").value).toFixed(2);

        document.getElementById("jumlahBayaranUkur").value = parseFloat(document.getElementById("jumlahBayaranUkur").value).toFixed(2);
    }--%>

        function validateForm(){

            if(document.form.noRujukanPejabatTanah.value=="")
            {
                alert("Sila masukkan No. Rujukan Pejabat Tanah");
                $('#noRujukanPejabatTanah').focus();
                return false;
            }
            if(document.form.tujuan.value=="")
            {
                alert("Sila masukkan tujuan");
                $('#tujuan').focus();
                return false;
            }
                        
            if(document.form.tarikhPerakuan.value=="")
            {
                alert("Sila masukkan tarikh perakuan");
                $('#tarikhPerakuan').focus();
                return false;
            }
            return true;
        }

    <%--     function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {

        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
        var nama = strNama.replace(" ", "_");
        var jawatan =  strJawatan.replace(" ", "_");
        var stageId = "g_penyediaan_pu";
        alert("nama:" + nama);
        alert ("jawatan:" + jawatan);
        alert ("stageid:" + stageId);
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + nama + " " +  jawatan + " " + strIDPermohonan + " " + stageId);
    }

    function RunProgram2(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {

        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
        var nama = strNama.replace(" ", "_");
        var jawatan =  strJawatan.replace(" ", "_");
        var stageId = "g_hantar_pu";
        alert("nama:" + nama);
        alert ("jawatan:" + jawatan);
        alert ("stageid:" + stageId);
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        objShell.Run(sysVar("eTanahGISPU") + " " + strUserID + " " + nama + " " +  jawatan + " " + strIDPermohonan + " " + stageId);
    }

  // testing
  function sendFile(idPermohonan,idAliran){
      idAliran="g_hantar_pu";
      alert("idPermohonan:"+idPermohonan);
      alert("idAliran:"+idAliran);
    var url = '${pageContext.request.contextPath}/utility/inboundIntegration?executeCMD&idPermohonan='+idPermohonan+'&idAliran='+idAliran;
              $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
  }--%>

      function validatePoskodLength(value){
          var plength = value.length;
          if(plength != 5){
              alert('Poskod yang dimasukkan salah.');
              $('#poskod').val("");
              $('#poskod').focus();
          }
      }
</script>

<s:form beanclass="etanah.view.penguatkuasaan.Pu1ActionBean" name="form" id="form">
    <s:messages/>
    <s:errors/>
    <c:if test="${actionBean.kodNegeri eq '04'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>PERMINTAAN UKUR </legend>

                <br>
                <c:if test="${edit}">
                    <p>
                        <label>Bil. Pejabat Tanah :</label>
                        <s:hidden name="permohonanUkur.idMohonUkur"/> 
                        <s:text name="permohonanUkur.noRujukanPejabatTanah" id="noRujukanPejabatTanah" size="32" maxlength="20" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Bil. Pejabat Ukur :</label>
                        <s:text name="permohonanUkur.noRujukanPejabatUkur" size="32" maxlength="12" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Kerajaan Negeri :</label>
                        <c:if test="${actionBean.kodNegeri eq '04'}">
                            Melaka&nbsp;
                        </c:if>
                    </p>
                    <p>
                        <label>No. Permintaan Ukur :</label>
                        ${actionBean.permohonanUkur.noPermohonanUkur} &nbsp;
                    </p>
                    <br/>
                    <p align ="left" class="pLabel"> 
                        Sila ukurkan tanah yang bertanda merah dalam pelan*di sebelah/berkembar untuk :
                    </p>
                    <table>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(a) Berimilik Tanah Kerajaan
                                <s:text name="permohonanUkur.bermilikTanahKerajaan" id="bermilikTanahKerajaan" size="32" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(b) Pecahan *Lot/lot/Bangunan-Bangunan
                                <s:text name="permohonanUkur.pecahanLot" id="pecahanLot" size="32" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(c) *Cantuman Lot-Lot/Bahagian
                                <s:text name="permohonanUkur.camtumanLot" id="camtumanLot" size="32" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                                <font style="color:#003194;font-weight:bold;">*dan terus dipecah</font>
                                <s:text name="permohonanUkur.camtumanLotDipecah" id="camtumanLotDipecah" size="32" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(d) Penyerahan sebahagian dari Lot-Lot
                                <s:text name="permohonanUkur.serahSebahagianLot" id="serahSebahagianLot" size="32" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(e) Penyerahan Lot-Lot
                                <s:text name="permohonanUkur.lot" id="lot" size="32" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                                <font style="color:#003194;font-weight:bold;">dan berimilik semula bahagian-bahagian</font>
                                <s:text name="permohonanUkur.berimilikSemulaBahagian" id="berimilikSemulaBahagian" size="32" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(f) Pengambilan sebahagian dari Lot-Lot
                                <s:text name="permohonanUkur.ambilSebahagianLot" id="ambilSebahagianLot" size="32" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(g) Ukuran semula Lot-Lot
                                <s:text name="ukurSemulaLot" id="ukurSemulaLot" size="32" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(h) *Menggantikan tanda sempada yang hilang/membetulkan tempat tanda-tanda sempadan :
                            </td>
                        </tr>
                    </table>

                    <br/>
                    <p class="pLabel"> 
                        2. Suratan-suratan Hakmilik yang dikehendaki untuk dikeluarkan ialah :
                    </p>
                    <table>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(a) Geran (Borang 5B) bagi
                                <s:text name="permohonanUkur.perincianBorang5b" id="perincianBorang5b" size="50" maxlength="45" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(b) Lease Negeri (Borang 5C)
                                <s:text name="permohonanUkur.perincianBorang5c" size="50" maxlength="45" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(c) *Geran Mukim (Borang 5D)/Lease Mukim(Borang 5E)
                                <s:text name="permohonanUkur.perincianBorang5d" size="50" maxlength="45" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(d) Pajakan Lombong didahului oleh Perakuan Lombong bagi
                                <s:text name="permohonanUkur.perincianPajakanLombong" size="50" maxlength="45" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(e) Hakmilik Pecahan (Borang 10C) bagi
                                <s:text name="permohonanUkur.borang10c" size="50" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                    </table>

                    <br>
                    <p class="pLabel"> 
                        3. Suratan-suratan Hakmilik bagi Lot-Lot &nbsp;<s:radio name="permohonanUkur.statusSuratanHakmilik" value="A"/>*akan/<s:radio name="permohonanUkur.statusSuratanHakmilik" value="T"/>telah dimansuhkan
                    </p>
                    <br>
                    <p class="pLabel"> 
                        4. Bayaran-bayaran ukur : 
                    </p>
                    <table>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(a) Dibebaskan Penuh oleh
                                <s:text name="permohonanUkur.pemberiPengecualian" size="50" maxlength="45" onkeyup="this.value=this.value.toUpperCase();"/>
                                <font style="color:#003194;font-weight:bold;">lihat</font>
                                <s:text name="permohonanUkur.pengecualianOlehLihat" id="pengecualianOlehLihat" size="32" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(b) Dibebaskan Setakat
                                <s:text name="permohonanUkur.pengecualianSetakat" size="50" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                                <font style="color:#003194;font-weight:bold;">lihat</font>
                                <s:text name="permohonanUkur.pengecualianSetakatLihat" id="pengecualianSetakatLihat" size="32" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(c) Mengikut Peraturan
                                <s:text name="permohonanUkur.noAturan" size="50" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                                <font style="color:#003194;font-weight:bold;">Jadual W.K. 486/85</font>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(d) Bayaran Ukur sebanyak RM
                                <s:text name="permohonanUkur.jumlahBayaranUkur" id="jumlahBayaranUkur" size="15" class="number" formatPattern="0.00"  maxlength="14" onkeyup="validateNumber(this,this.value);"/>
                                <font style="color:#003194;font-weight:bold;">telah dibayar. No.Resit</font>
                                <s:text name="permohonanUkur.noResit" size="20" maxlength="10" onkeyup="this.value=this.value.toUpperCase();" /><br>

                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                &nbsp;&nbsp;&nbsp;&nbsp;<font style="color:#003194;font-weight:bold;">haribulan</font>
                                <s:text name="permohonanUkur.tarikhResit" class="datepicker" formatPattern="dd/MM/yyyy" size="12"/>
                                <font style="color:#003194;font-weight:bold;">No.Akaun</font>
                                <s:text name="permohonanUkur.noAkaun" size="20" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" />
                            </td>
                        </tr>
                    </table>
                    <br>
                    <p class="pLabel"> 
                        5. Nama <s:text name="nama" size="30" maxlength="100" onkeyup="this.value=this.value.toUpperCase();" /> <br>

                    </p>
                    <p class="pLabel"> 
                        &nbsp;&nbsp;&nbsp;dan Alamat pemohon&nbsp;&nbsp;<s:text name="alamat1" size="50" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" />
                    </p>
                    <table>
                        <tr>
                            <td id="tdLabel">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <s:text name="alamat2" size="50" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" />
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <s:text name="alamat3" size="50" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" />
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <s:text name="alamat4" size="50" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" />  
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <s:text name="poskod" size="10" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);" />                        </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <s:select name="kodNegeriPemohon"  style="width:139px;">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" sort="nama" />
                                </s:select>
                            </td>
                        </tr>
                    </table>
                    <br>
                    <p class="pLabel"> 
                        6. Daerah  ${actionBean.permohonan.cawangan.daerah.nama} *Mukim/Bandar/Pekan ${actionBean.hakmilik.bandarPekanMukim.nama}

                    </p>
                    <br>
                    <p class="pLabel">
                        7. Anggaran Luas
                        <s:text name="anggaranLuas" size="10" onkeyup="validateNumber(this,this.value);"/>
                        <s:select name="anggaranLuasUOM" id="anggaranLuasUOM">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodUOMByLuas}" label="nama" value="kod" sort="nama" />
                        </s:select>
                    </p>
                    <br>
                    <p class="pLabel">
                        8. Jenis-Jenis Kegunaan Tanah : *
                        <s:select name="kodKategoriTanah" id="kodKategoriTanah">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" sort="nama"/>
                        </s:select>&nbsp;
                    </p>
                    <br>
                    <p class="pLabel">
                        9. Suratan-suratan Hakmilik Sementara <s:radio name="permohonanUkur.statusSuratanHakmilikSementara" value="T"/>* telah / <s:radio name="permohonanUkur.statusSuratanHakmilikSementara" value="B"/>belum dikeluarkan

                    </p>
                    <br>
                    <p class="pLabel">
                        10. <em>*</em>Tarikh Perakuan 
                        <s:text name="tarikhPerakuan" class="datepicker" id="tarikhPerakuan" formatPattern="dd/MM/yyyy" size="12"/>
                    </p>
                    <br>
                    <p class="pLabel">
                        11. Tujuan
                    </p>
                    <p class="pLabel">
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <s:textarea name="permohonanUkur.tujuan" id="tujuan" lang="100" cols="70" rows="3" onkeyup="this.value=this.value.toUpperCase();" />
                    </p>
                    <br><br>
                    <p align="center">
                        <s:button name="simpan" id="save" value="Simpan & Jana No PU" class="longbtn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div')"/>
                        <%--<s:button name="lakarPelan" id="lakarPelan" value="Penyedian PU GIS" class="longbtn"  onclick="RunProgram('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;--%>
                        <%--<s:button name="lakarPelan" id="lakarPelan" value="Penghantaran PU" class="longbtn"  onclick="RunProgram2('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;--%>
                    </p>
                </c:if>
                <c:if test="${!edit}">
                    <p>
                        <label>Bil. Pejabat Tanah :</label>
                        <s:hidden name="permohonanUkur.idMohonUkur"/> 
                        <s:text name="permohonanUkur.noRujukanPejabatTanah" id="noRujukanPejabatTanah" size="32" maxlength="20" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                    </p>
                    <p>
                        <label>Bil. Pejabat Ukur :</label>
                        <s:text name="permohonanUkur.noRujukanPejabatUkur" size="32" maxlength="12" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                    </p>
                    <p>
                        <label>Kerajaan Negeri :</label>
                        <c:if test="${actionBean.kodNegeri eq '04'}">
                            Melaka&nbsp;
                        </c:if>
                    </p>
                    <p>
                        <label>No. Permintaan Ukur :</label>
                        ${actionBean.permohonanUkur.noPermohonanUkur} &nbsp;
                    </p>
                    <br/>
                    <p align ="left" class="pLabel"> 
                        Sila ukurkan tanah yang bertanda merah dalam pelan*di sebelah/berkembar untuk :
                    </p>
                    <table>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(a) Berimilik Tanah Kerajaan
                                <s:text name="permohonanUkur.bermilikTanahKerajaan" id="bermilikTanahKerajaan" size="32" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(b) Pecahan *Lot/lot/Bangunan-Bangunan
                                <s:text name="permohonanUkur.pecahanLot" id="pecahanLot" size="32" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(c) *Cantuman Lot-Lot/Bahagian
                                <s:text name="permohonanUkur.camtumanLot" id="camtumanLot" size="32" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                                <font style="color:#003194;font-weight:bold;">*dan terus dipecah</font>
                                <s:text name="permohonanUkur.camtumanLotDipecah" id="camtumanLotDipecah" size="32" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(d) Penyerahan sebahagian dari Lot-Lot
                                <s:text name="permohonanUkur.serahSebahagianLot" id="serahSebahagianLot" size="32" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(e) Penyerahan Lot-Lot
                                <s:text name="permohonanUkur.lot" id="lot" size="32" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                                <font style="color:#003194;font-weight:bold;">dan berimilik semula bahagian-bahagian</font> 
                                <s:text name="permohonanUkur.berimilikSemulaBahagian" id="berimilikSemulaBahagian" size="32" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(f) Pengambilan sebahagian dari Lot-Lot
                                <s:text name="permohonanUkur.ambilSebahagianLot" id="ambilSebahagianLot" size="32" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(g) Ukuran semula Lot-Lot
                                <s:text name="ukurSemulaLot" id="ukurSemulaLot" size="32" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(h) *Menggantikan tanda sempada yang hilang/membetulkan tempat tanda-tanda sempadan :
                            </td>
                        </tr>
                    </table>

                    <br/>
                    <p class="pLabel"> 
                        2. Suratan-suratan Hakmilik yang dikehendaki untuk dikeluarkan ialah :
                    </p>
                    <table>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(a) Geran (Borang 5B) bagi
                                <s:text name="permohonanUkur.perincianBorang5b" id="perincianBorang5b" size="50" maxlength="45" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(b) Lease Negeri (Borang 5C)
                                <s:text name="permohonanUkur.perincianBorang5c" size="50" maxlength="45" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(c) *Geran Mukim (Borang 5D)/Lease Mukim(Borang 5E)
                                <s:text name="permohonanUkur.perincianBorang5d" size="50" maxlength="45" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(d) Pajakan Lombong didahului oleh Perakuan Lombong bagi
                                <s:text name="permohonanUkur.perincianPajakanLombong" size="50" maxlength="45" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(e) Hakmilik Pecahan (Borang 10C) bagi
                                <s:text name="permohonanUkur.borang10c" size="50" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                            </td>
                        </tr>
                    </table>

                    <br>
                    <p class="pLabel"> 
                        3. Suratan-suratan Hakmilik bagi Lot-Lot &nbsp;<s:radio name="permohonanUkur.statusSuratanHakmilik" value="A" disabled="true"/>*akan/<s:radio name="permohonanUkur.statusSuratanHakmilik" value="T" disabled="true"/>telah dimansuhkan
                    </p>
                    <br>
                    <p class="pLabel"> 
                        4. Bayaran-bayaran ukur : 
                    </p>
                    <table>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(a) Dibebaskan Penuh oleh
                                <s:text name="permohonanUkur.pemberiPengecualian" size="50" maxlength="45" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                                <font style="color:#003194;font-weight:bold;">lihat</font>
                                <s:text name="permohonanUkur.pengecualianOlehLihat" id="pengecualianOlehLihat" size="32" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(b) Dibebaskan Setakat
                                <s:text name="permohonanUkur.pengecualianSetakat" size="50" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                                <font style="color:#003194;font-weight:bold;">lihat</font>
                                <s:text name="permohonanUkur.pengecualianSetakatLihat" id="pengecualianSetakatLihat" size="32" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(c) Mengikut Peraturan
                                <s:text name="permohonanUkur.noAturan" size="50" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                                <font style="color:#003194;font-weight:bold;">Jadual W.K. 486/85</font>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                <em>*</em>(d) Bayaran Ukur sebanyak RM
                                <s:text name="permohonanUkur.jumlahBayaranUkur" id="jumlahBayaranUkur" size="15" class="number" formatPattern="0.00"  maxlength="14" onkeyup="validateNumber(this,this.value);" disabled="true"/>
                                <font style="color:#003194;font-weight:bold;">telah dibayar. No.Resit</font>
                                <s:text name="permohonanUkur.noResit" size="20" maxlength="10" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/><br>

                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                &nbsp;&nbsp;&nbsp;&nbsp;<font style="color:#003194;font-weight:bold;">haribulan</font>
                                <s:text name="permohonanUkur.tarikhResit" class="datepicker" formatPattern="dd/MM/yyyy" size="12" disabled="true"/>
                                <font style="color:#003194;font-weight:bold;">No.Akaun</font>
                                <s:text name="permohonanUkur.noAkaun" size="20" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                            </td>
                        </tr>
                    </table>
                    <br>
                    <p class="pLabel"> 
                        5. Nama <s:text name="nama" size="30" maxlength="100" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/> <br>

                    </p>
                    <p class="pLabel"> 
                        &nbsp;&nbsp;&nbsp;dan Alamat pemohon&nbsp;&nbsp;<s:text name="alamat1" size="50" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                    </p>
                    <table>
                        <tr>
                            <td id="tdLabel">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <s:text name="alamat2" size="50" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <s:text name="alamat3" size="50" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <s:text name="alamat4" size="50" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>  
                            </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <s:text name="poskod" size="10" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);" disabled="true"/>                        </td>
                        </tr>
                        <tr>
                            <td id="tdLabel">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <s:select name="kodNegeriPemohon"  style="width:139px;" disabled="true">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" sort="nama" />
                                </s:select>
                            </td>
                        </tr>
                    </table>
                    <br>
                    <p class="pLabel"> 
                        6. Daerah  ${actionBean.permohonan.cawangan.daerah.nama} *Mukim/Bandar/Pekan ${actionBean.hakmilik.bandarPekanMukim.nama}

                    </p>
                    <br>
                    <p class="pLabel">
                        7. Anggaran Luas
                        <s:text name="anggaranLuas" size="10" onkeyup="validateNumber(this,this.value);" disabled="true"/>
                        <s:select name="anggaranLuasUOM" id="anggaranLuasUOM" disabled="true">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodUOMByLuas}" label="nama" value="kod" sort="nama" />
                        </s:select>
                    </p>
                    <br>
                    <p class="pLabel">
                        8. Jenis-Jenis Kegunaan Tanah : *
                        <s:select name="kodKategoriTanah" id="kodKategoriTanah" disabled="true">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" sort="nama"/>
                        </s:select>&nbsp;
                    </p>
                    <br>
                    <p class="pLabel">
                        9. Suratan-suratan Hakmilik Sementara <s:radio name="permohonanUkur.statusSuratanHakmilikSementara" value="T" disabled="true"/>* telah / <s:radio name="permohonanUkur.statusSuratanHakmilikSementara" value="B" disabled="true"/>belum dikeluarkan

                    </p>
                    <br>
                    <p class="pLabel">
                        10. <em>*</em>Tarikh Perakuan 
                        <s:text name="tarikhPerakuan" class="datepicker" id="tarikhPerakuan" formatPattern="dd/MM/yyyy" size="12" disabled="true"/>
                    </p>
                    <br>
                    <p class="pLabel">
                        11. Tujuan
                    </p>
                    <p class="pLabel">
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <s:textarea name="permohonanUkur.tujuan" id="tujuan" lang="100" cols="70" rows="3" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                    </p>
                    <br><br>
                </c:if>





            </fieldset>
        </div>
    </c:if>


</s:form>
<br>
<div align="center">
