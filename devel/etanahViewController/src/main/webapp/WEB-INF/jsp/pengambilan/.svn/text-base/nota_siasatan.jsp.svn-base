<%--
    Document   : nota_siasatan
    Created on : July 9, 2010, 4:00:12 PM
    Author     : Rajesh
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252" import="java.util.*" import="java.text.SimpleDateFormat"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">
$(document).ready( function(){


$(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
$(".datepicker1").datepicker({dateFormat: 'yy'});
});
function ajaxLink(link, update) {
$.get(link, function(data) {
$(update).html(data);
$(update).show();
});
return false;
}

function tambahKehadiran(tableID) {
    var table = document.getElementById(tableID);
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);

    var cell1 = row.insertCell(0);
    cell1.innerHTML = rowCount;
    <%--var element1 = document.createElement("input");
    element1.type = "checkbox";
    cell1.appendChild(element1);--%>

    var cell2 = row.insertCell(1);
    var element1 = document.createElement("input");
    element1.type = "text";
    cell2.appendChild(element1);

    var cell3 = row.insertCell(2);
    var element2 = document.createElement("input");
    element2.type = "text";
    cell3.appendChild(element2);
}

function popup(h){
var url = '${pageContext.request.contextPath}/pengambilan/nota_siasatan?showEditTuanTanah&idPihak='+h;
window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
}

function tambah(){
window.open("${pageContext.request.contextPath}/pengambilan/nota_siasatan?showTuanTanahPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

function  test(){
        var tujuan1=document.getElementById("tujuan1");
        var trkDate=document.getElementById("trkDate");
        var trkYear=document.getElementById("trkYear");
        if(tujuan1.checked){
           trkYear.value="";
           trkDate.disabled=false;
           trkYear.disabled=true;
        }else{
           trkDate.value="";
           trkDate.disabled=true;
           trkYear.disabled=false;
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

    function popupPerintah(idHakmilik){
        var url = '${pageContext.request.contextPath}/pengambilan/nota_siasatan?showPerintah&idHakmilik='+idHakmilik;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }


</script>

<s:form name="notaForm" beanclass="etanah.view.stripes.pengambilan.NotaSiasatanActionBean">
    <s:messages/>

    <div  id="hakmilik_details">
        <s:hidden name="hakmilikPerbicaraan.idPerbicaraan" />
        <s:hidden name="hakmilikPerbicaraanTangguh.idTangguh" />
        <fieldset class="aras1"><br/>
            <legend >
                <b>Maklumat Hakmilik Permohonan</b>
            </legend><br/>
            <p align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/nota_siasatan" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column title="ID Hakmilik">
                        <s:link beanclass="etanah.view.stripes.pengambilan.NotaSiasatanActionBean"
                            event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                        <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}
                        </s:link>
                    </display:column>
                    <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column title="Luas Sebenar"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column title="Luas Diambil"><fmt:formatNumber pattern="#,##0.00" value="${line.luasTerlibat}"/>&nbsp;${line.kodUnitLuas.nama}</display:column>
                    <display:column property="hakmilik.syaratNyata.syarat" title="Syarat Nyata" />
                </display:table>
            </p>
            <br/><br/><br/>
             <c:if test="${actionBean.hakmilik ne null}">
            <table>
                <tr>
                    <td width="50%"><b>&nbsp;&nbsp;&nbsp;&nbsp;Bil Kes : Perbicaraan Bil</b></td>
                    <td><b>:</b></td>
                    <td><b>${actionBean.hakmilikPerbicaraan.idPerbicaraan}</b></td>
                    
                </tr>
                <tr>
                    <td width="50%"><b>&nbsp;&nbsp;&nbsp;&nbsp;Penolong Pentadbir Tanah</b></td>
                    <td><b>:</b></td>
                    <td>
                        <s:select name="hakmilikPerbicaraan.dibicaraOleh.idPengguna" id="dibicaraOleh" >
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${actionBean.senaraiPengguna}" label="nama" value="idPengguna" sort="nama" />
                        </s:select>
                    </td>
                </tr>            
            </table>
           <br/><br/>
           Kehadiran
           <br/><br/>
            <p align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilik.senaraiPihakBerkepentingan}"  cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/nota_siasatan" id="line1">
                    <display:column title="No" sortable="true">${line1_rowNum}</display:column>
                    <display:column property="pihak.nama" title="Nama" />
                    <display:column property="pihak.noPengenalan" title="No KP" />
                    <display:column title="Alamat" style="vertical-align:baseline">
                        ${line1.pihak.alamat1}
                        <c:if test="${line1.pihak.alamat2 ne null}"> , ${line1.pihak.alamat2} </c:if>
                        <c:if test="${line1.pihak.alamat3 ne null}"> , ${line1.pihak.alamat3} </c:if>
                        <c:if test="${line1.pihak.alamat4 ne null}"> , ${line1.pihak.alamat4} </c:if>
                    </display:column>
                    <display:column property="pihak.poskod" title="Poskod"/>
                    <display:column property="pihak.negeri.nama" title="Negeri"/>
                    <display:column title="No Tel" style="vertical-align:baseline">
                        ${line1.pihak.noTelefon1}
                        <c:if test="${line1.pihak.noTelefon2 ne null}">, ${line1.pihak.noTelefon2} </c:if>
                    </display:column>
                    <%--<display:column title="Jawatan"/>--%>
                    <display:column title="Status Kehadiran">
                         <s:radio name="hadir[${line1_rowNum-1}]" value="1"/>Hadir
                         <s:radio name="hadir[${line1_rowNum-1}]" value="0"/>Tidak Hadir
                    </display:column>>
                </display:table>
                        <%--<s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahKehadiran('line1');"/>&nbsp;
                        <s:button class="btn" value="Hapus" name="remove" id="remove" onclick="deleteKehadiran('line1');"/>&nbsp;--%>
            </p>
            <br/><br/>
           
                <table >

                    <tr>
                        <td width="30%"><b>1) Tarikh permilikan tanah</b></td>
                        <td><b>:</b></td>
                        <td><b><s:text name="hakmilikPerbicaraan.tarikhPemilikan" id="trkDate" class="datepicker" formatPattern="dd/MM/yyyy"/>
                                <s:hidden name="hakmilikPerbicaraan.tarikhPemilikan" /></b></td>
                    </tr>
                    <%--<tr>
                        <td width="30%"><b>1) Tarikh permilikan tanah atau</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:radio name="tujuan" id="tujuan1" value="F" onclick="javascript:test();"/>&nbsp;<s:text name="hakmilikPerbicaraan.tarikhPemilikan" id="trkDate" class="datepicker" disabled="true" formatPattern="dd/MM/yyyy"/>
                                <s:hidden name="hakmilikPerbicaraan.tarikhPemilikan" /></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tahun pemilikan tanah</b></td>
                        <td><b>:</b></td>
                        <td><b><s:radio name="tujuan" id="tujuan2" value="S" onclick="javascript:test();"/>&nbsp;
                                <s:text name="hakmilikPerbicaraan.tarikhPemilikan" id="trkYear" class="datepicker1" disabled="true" formatPattern="yyyy"/>
                            </b>
                        </td>
                    </tr>--%>
                    
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cara pemilikan tanah</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="hakmilikPerbicaraan.caraPemilikan" size="20"/></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Harga pambelian tanah(RM)</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="hakmilikPerbicaraan.hargaPembelian" size="20" onkeyup="validateNumber(this,this.value);"/></b></td>
                    </tr>
                    <br/>
                    <tr>
                        <td width="30%"><b>2) Jenis Tanaman</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.tanaman" rows="3" cols="50" /></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Bangunan</b></td>
                        <td><b>:</b></td>
                        <td><b><s:textarea name="hakmilikPerbicaraan.bangunan" rows="3" cols="50" /></b></td>
                    </tr>

                    <tr>
                        <td ><b>3) Permohonan tukar syarat/pecah sempadan.Jika ada,bila dan bagaimana kedudukan sekarang.</b></td>
                         <td><b>:</b></td>
                         <td><s:radio name="hakmilikPerbicaraan.pecahSyarat" value="A"/>&nbsp;Ada
                             <s:radio name="hakmilikPerbicaraan.pecahSyarat" value="T"/>&nbsp;Tiada</td>
                    </tr>
                    <tr>
                        <td ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tarikh Permohonan Tukar syarat/pecah sempadan</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="hakmilikPerbicaraan.tarikhPecahSyarat" class="datepicker" formatPattern="dd/MM/yyyy"/></b></td>
                    </tr>
                    <tr>
                        <td ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Kedudukan terkini</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="hakmilikPerbicaraan.lokasiTerkini" size="20"/></b></td>
                    </tr>
                    <tr><td width="45%"><b>4) Gadaian/Pajakan/Perjanjian Jualbeli</b></td>
                        <td><b>:</b></td>
                        <td><s:radio name="hakmilikPerbicaraan.ujudGPPJ" value="Ada"/>&nbsp;Ada
                            <s:radio name="hakmilikPerbicaraan.ujudGPPJ" value="Tiada"/>&nbsp;Tiada</td>
                    </tr>
                    <tr>
                        <td width=30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Komen</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.komenGPPJ" rows="3" cols="50" id="komenGPPJ"/></b></td>
                    </tr>
                    <tr><td width="45%"><b>5.) Keterangan dan tuntutan orang yang berkepentingan</b></td></tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;i) Tuanpunya tanah</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="xy" rows="3" cols="50" /></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ii) Penduduk, Penyewa, Pemajak</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="xy" rows="3" cols="50" /></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;iii) Pemegang Gadaian, Pemegang Kaveat, Pemegang Lien</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="xy" rows="3" cols="50" /></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;iv) Lain - lain(Nyatakan Kepentingan)</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="xy" rows="3" cols="50" /></b></td>
                    </tr>
                    <tr><td width="45%"><b>6.) Keterangan Jabatan Penilaian dan Perkhidmatan Harta</b></td></tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;i) Bilangan Surat JPPH</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="hakmilikPerbicaraan.penilaiNoRujukan" size="20" onkeyup="validateNumber(this,this.value);"/></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ii) Tarikh Surat JPPH</b></td>
                        <td><b>:</b></td>
                        <td><b><s:text name="hakmilikPerbicaraan.tarikhSuratPenilai" id="datepicker2" class="datepicker" formatPattern="dd/MM/yyyy"/></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;iii) Nilaian JPPH</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="hakmilikPerbicaraan.nilaianPenilai" size="20" onkeyup="validateNumber(this,this.value);" /></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;iv) Bantahan(Jika Ada)</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.bantahanPenilai" rows="3" cols="50" /></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;v) Akujanji Agensi Pemohon(Jika berkaitan)</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.akujanjiPenilai" rows="3" cols="50" /></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>7.) Hal - hal lain</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="xy" rows="3" cols="50" /></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>8.) Penangguhan perbicaraan (jika berkaitan) Sebab - sebab penangguhan</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.sebabTangguh" rows="3" cols="50" /></b></td>
                    </tr>
                    <tr><td width="30%"><b>9.) Ulang Bicara</b></td></tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;i) Tarikh</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="tarikhBicara" class="datepicker" formatPattern="dd/MM/yyyy"/></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ii) Masa</b></td>
                        <td><b>:</b></td>
                        <td><b><s:select name="jam" style="width:56px" id="jam">
                        <s:option value="">Jam</s:option>
                        <s:option value="01">01</s:option>
                        <s:option value="02">02</s:option>
                        <s:option value="03">03</s:option>
                        <s:option value="04">04</s:option>
                        <s:option value="05">05</s:option>
                        <s:option value="06">06</s:option>
                        <s:option value="07">07</s:option>
                        <s:option value="08">08</s:option>
                        <s:option value="09">09</s:option>
                        <s:option value="10">10</s:option>
                        <s:option value="11">11</s:option>
                        <s:option value="12">12</s:option>
                    </s:select>
                            <s:select name="minit" style="width:65px" id="minit">
                        <s:option value="">Minit</s:option>
                        <s:option value="00">00</s:option>
                        <s:option value="05">05</s:option>
                        <s:option value="10">10</s:option>
                        <s:option value="15">15</s:option>
                        <s:option value="20">20</s:option>
                        <s:option value="25">25</s:option>
                        <s:option value="30">30</s:option>
                        <s:option value="35">35</s:option>
                        <s:option value="40">40</s:option>
                        <s:option value="45">45</s:option>
                        <s:option value="50">50</s:option>
                        <s:option value="55">55</s:option>
                    </s:select>
                    <s:select name="ampm" style="width:80px" id="ampm">
                        <s:option value="">Pilih</s:option>
                        <s:option value="AM">AM</s:option>
                        <s:option value="PM">PM</s:option>
                    </s:select></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;iii) Tempat</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="hakmilikPerbicaraanTangguh.lokasiBicara"/></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;iv) Keterangan semasa ulang bicara</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraanTangguh.catatan" rows="3" cols="50"/></b></td>
                    </tr>
                     <tr><td width="45%"><b>10.) Nilaian tanah yang ditentukan oleh Pentadbir Tanah</b></td></tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;i) Nilai tanah</b></td>
                        <td><b>:</b></td>
                        <td><s:text name="hakmilikPerbicaraan.keputusanNilai" size="20" onkeyup="validateNumber(this,this.value);"/>
                            <s:select name="hakmilikPerbicaraan.kodUOM.kod">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:option value="H">Hektar</s:option>
                                <s:option value="M">Meter Persegi</s:option>
                                <s:option value="E">Ekar</s:option>
                            </s:select></td>
                    </tr>
                    <%--<tr>
                        <td width="30%"><b>2) Lokasi tanah</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="hakmilikPerbicaraan.lokasi" size="20"/></b></td>
                    </tr>
                    <tr>
                        <td width="45%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Jauh dengan jalan dan pekan/bandar</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="hakmilikPerbicaraan.jarakKeBandar" size="20" onkeyup="validateNumber(this,this.value);"/></b>&nbsp;&nbsp;km</td>
                    </tr>
                    <br/>
                    <tr>
                        <td width="30%"><b>3) Keadaan tanah</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.keadaanTanah" rows="3" cols="50" /></b></td>
                    </tr>
                    <br/>
                                      
                </table>                    
                <table >
                    <tr>
                        <td ><b>5) Permohonan tukar syarat/pecah sempadan.Jika ada,bila dan bagaimana kedudukan sekarang.</b></td>

                    </tr>
                </table>
                <table border="0" width="50%">
                    <tr>
                        <td ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tarikh Permohonan Tukar syarat/pecah sempadan</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="permohonanRujukanLuar.noSidang" size="20"/></b></td>
                    </tr>
                    <tr>
                        <td ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Kedudukan terkini</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="permohonanRujukanLuar.noSidang" size="20"/></b><br/><br/></td>
                    </tr>
                </table>   
                <table>
                    <tr><td width="45%"><b>6) Gadaian/Pajakan/Perjanjian Jualbeli</b></td>
                    </tr>
                    <tr>
                        <td width=30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Komen</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.catatan" rows="3" cols="50" id="catatan"/></b></td>
                    </tr>
                    <br/>
                    <tr>
                        <td width="30%"><b>7) Tuntutan jumlah pampasan (RM)</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="hakmilikPerbicaraan.amaunDituntut" size="20" onkeyup="validateNumber(this,this.value);"/></b></td>
                    </tr>
                    <tr>
                        <td width=30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Alasan</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.alasanTuntutan" rows="3" cols="50" /></b></td>
                    </tr>                    
                    <tr>
                        <td width=30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. Keterangan Agensi Pemohon</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.pemohonUlasan" rows="3" cols="50" /></b></td>
                    </tr>                    
                    <tr>
                        <td width=30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;C. Keterangan Pegawai Penilaian</b></td>
                    </tr>
                    <tr>
                        <td width=40%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nilai Tanah</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="hakmilikPerbicaraan.keputusanNilai" size="20" onkeyup="validateNumber(this,this.value);"/></b>
                        <s:select name="hakmilikPerbicaraan.kodUOM.kod">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:option value="M"> 1000 Meter Persegi</s:option>
                        </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td width=40%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Bilangan Surat</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="hakmilikPerbicaraan.penilaiNoRujukan" size="20" onkeyup="validateNumber(this,this.value);"/></b></td>
                    </tr>
                    <tr>
                        <td width=40%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tarikh Surat</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="hakmilikPerbicaraan.tarikhSuratPenilai" id="datepicker2" class="datepicker" formatPattern="dd/MM/yyyy"/></b></td>
                    </tr>
                    
                    <tr>
                        <td width=40%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(Jika berubah dari laporan)</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="penilaiUlasan" rows="3" cols="50" /></b></td>
                    </tr>--%>
                    <%--<tr>
                        <td width=40%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. Lain--lain Hal</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="keterangan" rows="3" cols="50" id="catatan"/></b></td>
                    </tr>--%>
                </table>
                    <br/>
                    <p align="center">
                        <s:hidden name="idHakmilik"/>
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        <c:if test="${showPerintah}">
                            <s:button name="perintah" value="Perintah" class="btn" onclick="popupPerintah('${actionBean.idHakmilik}');"/>
                        </c:if>
                    </p>
                    </c:if>

                     </fieldset>
    </div>
         
</s:form>
