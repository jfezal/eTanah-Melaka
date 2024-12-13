<%-- 
    Document   : nota_siasatan_new
    Created on : 08-Dec-2010, 10:56:00
    Author     : nordiyana
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
var url = '${pageContext.request.contextPath}/pengambilan/siasataletrik?showEditTuanTanah&idPihak='+h;
window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
}

function tambah(){
window.open("${pageContext.request.contextPath}/pengambilan/siasataletrik?showTuanTanahPopup", "eTanah",
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
    
    function addRow3(tableID) {
        document.form2.rowCount3.value = 1;
        var table = document.getElementById(tableID);

        var rowCount3 = table.rows.length;
        var row = table.insertRow(rowCount3);

        var cell2 = row.insertCell(0);
//        cell2.innerHTML = "<b>"+"2." +(rowCount3+1)+"</b>";
        cell2.innerHTML = "<b>" +(rowCount3+1)+"</b>";

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan3"+(rowCount3+1);
        element1.rows = 5;
        element1.cols = 150;
        element1.name ="kandungan3"+(rowCount3+1);
        element1.id ="kandungan3"+(rowCount3+1);
        cell1.appendChild(element1);
        document.form2.rowCount3.value=rowCount3 +1;
        var cell2 = row.insertCell(2);
        var e1 = document.createElement("INPUT");
        e1.t = "button"+(rowCount3+1);
        e1.setAttribute("type","button");
        e1.className="btn";
        e1.value="Hapus";
        e1.id =(rowCount3+1);
        e1.onclick=function(){deleteRow3('','','',(e1.id));};
        cell2.appendChild(e1);
    }

    function deleteRow3(idKandungan,index)
    {
            document.getElementById('dataTable3').deleteRow(index-1);
            if(idKandungan == ''){
                var rowCount = document.getElementById("rowCount3").value;
                document.getElementById("rowCount3").value = rowCount-1;
                regenerateBill3('dataTable3');
            }else{
                var url = '${pageContext.request.contextPath}/pengambilan/siasataletrik?deleteSingle&idKandungan='
                    +idKandungan+'&formPtg='+formPtg+'&form1='+form1;

                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
        }

</script>

<s:form name="notaForm" beanclass="etanah.view.stripes.pengambilan.NotaSiasatanLektrikActionBean">
    <s:messages/>
    <div  id="hakmilik_details">
        <s:hidden name="hakmilikPerbicaraan.idPerbicaraan"/>
        <fieldset class="aras1"><br/>
            <legend >
                <b>Maklumat Hakmilik Permohonan</b>
            </legend><br/>
            <p align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/nota_siasatan" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column title="ID Hakmilik">
                        <s:link beanclass="etanah.view.stripes.pengambilan.NotaSiasatanLektrikActionBean"
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
                    <td width="50%"><b>&nbsp;&nbsp;&nbsp;&nbsp;Bil Kes</b></td>
                    <td><b>:</b></td>
                    <td><b>${actionBean.hakmilikPerbicaraan.idPerbicaraan}</b></td>

                </tr>
                <tr>
                    <td width="50%"><b>&nbsp;&nbsp;&nbsp;&nbsp;Perbicaraan Bil</b></td>
                    <td><b>:</b></td>
                    <td><b><s:text name="hakmilikPerbicaraan.noRujukan" size="20"/></b></td>

                </tr>
                <tr>
                    <td width="50%"><b>&nbsp;&nbsp;&nbsp;&nbsp;Penolong Pentadbir Tanah</b></td>
                    <td><b>:</b></td>
                    <td>
                        <s:select name="hakmilikPerbicaraan.dibicaraOleh.idPengguna" id="dibicaraOleh">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${actionBean.senaraiPengguna}" label="nama" value="idPengguna" sort="nama" />
                        </s:select>
                    </td>
                </tr>
               <%-- <tr>
                    <td width="50%"><b>&nbsp;&nbsp;&nbsp;&nbsp;Keputusan</b></td>
                    <td><b>:</b></td>
                    <td>
                        <s:radio name="hakmilikPerbicaraan.catatan" value="Lulus" />&nbsp;Lulus
                    </td>
                </tr>
                <tr>
                    <td width="50%"><b>&nbsp;&nbsp;&nbsp;&nbsp;</b></td>
                    <td><b></b></td>
                    <td>
                        <s:radio name="hakmilikPerbicaraan.catatan" value="Tangguh" />&nbsp;Tangguh
                    </td>
                </tr>
                <tr>
                    <td width="50%"><b>&nbsp;&nbsp;&nbsp;&nbsp;</b></td>
                    <td><b></b></td>
                    <td>
                        <s:radio name="hakmilikPerbicaraan.catatan" value="Bantahan" />&nbsp;Bantahan
                    </td>
                </tr>--%>
            </table>
           <br/><br/>
           Kehadiran
           <br/><br/>
           
            <p align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiPerbicaraanKehadiran}"  cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/nota_siasatan" id="line1">
                    <display:column title="No" sortable="true">${line1_rowNum}</display:column>
                    <display:column title="Nama" >
                        <c:if test="${line1.pihak ne null}"> ${line1.pihak.pihak.nama} </c:if>
                        <c:if test="${line1.permohonanPihakTidakBerkepentingan ne null}"> ${line1.permohonanPihakTidakBerkepentingan.nama} </c:if>
                        <c:if test="${line1.pihak eq null}"> ${line1.nama} </c:if>
                    </display:column>
                    <display:column title="No KP" >
                        <c:if test="${line1.pihak ne null}"> ${line1.pihak.pihak.noPengenalan} </c:if>
                        <c:if test="${line1.permohonanPihakTidakBerkepentingan ne null}"> ${line1.permohonanPihakTidakBerkepentingan.nomborPengenalan} </c:if>
                        <c:if test="${line1.pihak eq null}"> ${line1.noPengenalan} </c:if>
                    </display:column>
                    <display:column title="Alamat" style="vertical-align:baseline">
                        <c:if test="${line1.pihak ne null}">
                            ${line1.pihak.pihak.alamat1}
                            <c:if test="${line1.pihak.pihak.alamat2 ne null}"> , ${line1.pihak.pihak.alamat2} </c:if>
                            <c:if test="${line1.pihak.pihak.alamat3 ne null}"> , ${line1.pihak.pihak.alamat3} </c:if>
                            <c:if test="${line1.pihak.pihak.alamat4 ne null}"> , ${line1.pihak.pihak.alamat4} </c:if>
                        </c:if>
                            <c:if test="${line1.permohonanPihakTidakBerkepentingan ne null}">
                            ${line1.permohonanPihakTidakBerkepentingan.alamat1}
                            <c:if test="${line1.permohonanPihakTidakBerkepentingan.alamat2 ne null}"> , ${line1.permohonanPihakTidakBerkepentingan.alamat2} </c:if>
                            <c:if test="${line1.permohonanPihakTidakBerkepentingan.alamat3 ne null}"> , ${line1.permohonanPihakTidakBerkepentingan.alamat3} </c:if>
                            
                        </c:if>
                        <c:if test="${line1.pihak eq null && line1.permohonanPihakTidakBerkepentingan.alamat1 eq null}">
                            -
                        </c:if>
                    </display:column>
                    <display:column title="Poskod">
                        <c:if test="${line1.pihak ne null}"> 
                            ${line1.pihak.pihak.poskod}
                        </c:if>
                        <c:if test="${line1.permohonanPihakTidakBerkepentingan ne null}"> 
                            ${line1.permohonanPihakTidakBerkepentingan.poskod}
                        </c:if>
                        <c:if test="${line1.pihak eq null && line1.permohonanPihakTidakBerkepentingan.poskod eq null}">
                            -
                        </c:if>
                    </display:column>
                    <display:column title="Negeri">
                        <c:if test="${line1.pihak ne null}"> 
                            ${line1.pihak.pihak.negeri.nama}
                        </c:if>
                        <c:if test="${line1.pihak eq null}">
                            -
                        </c:if>
                    </display:column>
                    <display:column title="No Tel" style="vertical-align:baseline">
                        <c:if test="${line1.pihak ne null}">
                            ${line1.pihak.pihak.noTelefon1}
                            <c:if test="${line1.pihak.pihak.noTelefon2 ne null}">, ${line1.pihak.pihak.noTelefon2} </c:if>
                        </c:if>
                            <c:if test="${line1.permohonanPihakTidakBerkepentingan ne null}">
                            ${line1.permohonanPihakTidakBerkepentingan.nomborTelefon1}
                            <c:if test="${line1.permohonanPihakTidakBerkepentingan.nomborTelefon2 ne null}">, ${line1.permohonanPihakTidakBerkepentingan.nomborTelefon2} </c:if>
                        </c:if>
                            <c:if test="${line1.pihak eq null && line1.permohonanPihakTidakBerkepentingan.nomborTelefon1 eq null}">
                            -
                        </c:if>
                    </display:column>
                    <display:column title="Jawatan">
                        <c:if test="${line1.pihak eq null}"> ${line1.jawatan} </c:if>
                    </display:column>
                    <display:column title="Status Kehadiran">
                         <s:radio name="hadir[${line1_rowNum-1}]" value="1"/>Hadir
                         <s:radio name="hadir[${line1_rowNum-1}]" value="0"/>Tidak Hadir
                    </display:column>
                    <display:column title="Bantahan">
                         <s:radio name="hadirBantahList[${line1_rowNum-1}]" value="Y"/>Ya
                         <s:radio name="hadirBantahList[${line1_rowNum-1}]" value="T"/>Tidak
                    </display:column>
                </display:table>
                        <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambah();"/>&nbsp;
                        <s:button class="btn" value="Hapus" name="remove" id="remove" onclick="deleteKehadiran('line1');"/>&nbsp;
            </p>
            <br/><br/>

                <table>

                    <tr>
                        <td width="30%"><b>1) Tarikh permilikan tanah</b></td>
                        <td><b>:</b></td>
                        <td><b><s:text name="hakmilikPerbicaraan.tarikhPemilikan" id="trkDate" class="datepicker" formatPattern="dd/MM/yyyy"/>
                                <s:hidden name="hakmilikPerbicaraan.tarikhPemilikan" /></b></td>
                    </tr>
                    <tr><td><b>TUAN TANAH</b></td></tr>
                        <tr><td>
                                <table id ="dataTable3">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKandungan1}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idPerbicaraan}</td>
                                            <td><c:out value="${line.subtajuk}"/></td>
                                            <td><font><s:textarea name="kandungan3${i}" id="kandungan3${i}" rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></font></td>
                                            <td><s:button class="btn" value="Hapus" name="delete" id="${i}" onclick="deleteRow3('${formPtg}','${form1}',${line.idKandungan},${i})"/></td>
                                        </tr>
                                        <s:hidden name="idKandungan3${i}" id="idKandungan3${i}" value="${line.idKandungan}" />
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                    <s:hidden name="rowCount3" value="${i-1}" id="rowCount3"/>
                                </table>
                        <tr><td align="right"><s:button name="perihalPermohonan" value="Tambah" class="btn" onclick="addRow3('dataTable3')" />
                                <%--<s:button name="perihalPermohonan" value="Hapus" class="btn" onclick="deleteRow3()" />--%>
                            </td>
                        </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Harga pembelian tanah(RM)</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="hakmilikPerbicaraan.hargaPembelian" size="20" onkeyup="validateNumber(this,this.value);"/></b></td>
                    </tr>
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
                        <td ><b>3) Permohonan tukar syarat/pecah sempadan.Jika ada,<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;bila dan bagaimana kedudukan sekarang.</b></td>
                         <td><b>:</b></td>
                         <td><s:radio name="hakmilikPerbicaraan.pecahSyarat" value="Y"/>&nbsp;Ada
                             <s:radio name="hakmilikPerbicaraan.pecahSyarat" value="T"/>&nbsp;Tiada</td>

                    </tr>
                    <tr>

                        <td ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tarikh Permohonan Tukar syarat/pecah sempadan</b></td>
                        <td><b>:</b></td>
                        <td><b><s:text name="hakmilikPerbicaraan.tarikhPecahSyarat" id="datepicker2" class="datepicker" formatPattern="dd/MM/yyyy"/></b></td>
                    </tr>
                    <tr>
                        <td ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Kedudukan terkini</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="hakmilikPerbicaraan.lokasiTerkini" size="20"/></b><br/><br/></td>
                    </tr>
                    <tr><td width="45%"><b>4) Gadaian/Pajakan/Perjanjian Jualbeli</b></td>
                        <td><b>:</b></td>
                         <td><s:radio name="hakmilikPerbicaraan.ujudGPPJ" value="Y"/>&nbsp;Ada
                               <s:radio name="hakmilikPerbicaraan.ujudGPPJ" value="T"/>&nbsp;Tiada</td>
                    </tr>
                    <tr>
                        <td width=30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Komen</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.komenGPPJ" rows="3" cols="50" id="catatan"/></b></td>
                    </tr>
                    <tr><td width="45%"><b>5) Keterangan dan tuntutan orang yang berkepentingan</b></td></tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;i) Tuanpunya tanah</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.keteranganTuanpunya" rows="3" cols="50" /></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ii) Penduduk, Penyewa, Pemajak</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.keteranganPPP" rows="3" cols="50" /></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;iii) Pemegang Gadaian, Pemegang Kaveat, Pemegang Lien</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.keteranganGKL" rows="3" cols="50" /></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;iv) Lain - lain(Nyatakan Kepentingan)</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.keteranganLain" rows="3" cols="50" /></b></td>
                    </tr>
                    <tr><td width="45%"><b>6) Keterangan Jabatan Penilaian dan Perkhidmatan Harta</b></td></tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;i) Bilangan Surat JPPH</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="hakmilikPerbicaraan.penilaiNoRujukan" size="20" onkeyup="validateNumber(this,this.value);"/></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ii) Tarikh Surat JPPH</b></td>
                        <td><b>:</b></td>
                        <td><b><s:text name="hakmilikPerbicaraan.tarikhSuratPenilai" id="datepicker4" class="datepicker" formatPattern="dd/MM/yyyy"/></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;iii) Nilaian JPPH</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.pemohonUlasan" rows="3" cols="50" /></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;iv) Bantahan(Jika Ada)</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.alasanTuntutan" rows="3" cols="50" /></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;v) Akujanji Agensi Pemohon(Jika berkaitan)</b></td>
                        <td><b>:</b></td>
                        <td><b><s:textarea name="hakmilikPerbicaraan.akujanjiPenilai" rows="3" cols="50" /></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>7) Hal - hal lain</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.penilaiNama" rows="3" cols="50" /></b></td>
                    </tr>
                     <tr>
                        <td width="30%"><b>8) Syarat Penggunaan Hak Lalu Lalang</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.ulasanPenilai" rows="3" cols="50" /></b></td>
                    </tr>
                    <%--<tr>
                        <td width="30%"><b>8.) Penangguhan perbicaraan (jika berkaitan) Sebab - sebab penangguhan</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.sebabTangguh" rows="3" cols="50" /></b></td>
                    </tr>
                    <tr><td width="30%"><b>9.) Ulang Bicara</b></td></tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;i) Tarikh</b></td>
                        <td><b>:</b></td>
                        <td><b><s:text name="tarikhBicara" id="trkDate" class="datepicker5" formatPattern="dd/MM/yyyy"/></b></td>
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
                        <td><b> <s:text name="hakmilikPerbicaraan.keadaanTanah" size="20"/></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;iv) Keterangan semasa ulang bicara</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.bantahanPenilai" rows="3" cols="50"/></b></td>
                    </tr>--%>
                     <tr><td width="45%"><b>8) Nilaian tanah yang ditentukan oleh Pentadbir Tanah</b></td></tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;i) Nilai tanah</b></td>
                        <td><b>:</b></td>
                        <td><s:text name="hakmilikPerbicaraan.keputusanNilai"/>
                            <s:select name="hakmilikPerbicaraan.kodUOM.kod" ><font color="red">*</font>
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:option value="H">Hektar</s:option>
                                    <s:option value="M">Meter Persegi</s:option>
                                    <s:option value="E">Ekar</s:option>
                        </s:select></td>
                         <td>
                        </td>
                    </tr>
                </table>
                            <br>
                            <c:if test="${actionBean.kodUrusan ne 'PHLLA'}">
                            <div class="subtitle displaytag">
     <fieldset class="aras1" id="locate">
            <legend>
                Keputusan Perbicaraan
            </legend>
            <p align="center"><label>
                    <table align="center">
                <tr>
                    <td width="50%"><b>&nbsp;&nbsp;&nbsp;&nbsp;Keputusan</b></td>
                    <td><b>:</b></td>
                    <td>
                        <s:radio name="hakmilikPerbicaraan.catatan" value="Lulus" />&nbsp;Lulus
                    </td>
                </tr>
                <tr>
                    <td width="50%"><b>&nbsp;&nbsp;&nbsp;&nbsp;</b></td>
                    <td><b></b></td>
                    <td>
                        <s:radio name="hakmilikPerbicaraan.catatan" value="Tangguh" />&nbsp;Tangguh
                    </td>
                </tr>
                <tr>
                    <td width="50%"><b>&nbsp;&nbsp;&nbsp;&nbsp;</b></td>
                    <td><b></b></td>
                    <td>
                        <s:radio name="hakmilikPerbicaraan.catatan" value="Bantahan" />&nbsp;Bantahan
                    </td>
                </tr>
            </table></label>

            <br>

        </fieldset>
    </div>
                    </c:if>
                              <c:if test="${actionBean.kodUrusan ne 'PHLLP'}">
                            <div class="subtitle displaytag">
     <fieldset class="aras1" id="locate">
            <legend>
                Keputusan Perbicaraan
            </legend>
            <p align="center"><label>
                    <table align="center">
                <tr>
                    <td width="50%"><b>&nbsp;&nbsp;&nbsp;&nbsp;Keputusan</b></td>
                    <td><b>:</b></td>
                    <td>
                        <s:radio name="hakmilikPerbicaraan.catatan" value="Lulus" />&nbsp;Lulus
                    </td>
                </tr>
                <tr>
                    <td width="50%"><b>&nbsp;&nbsp;&nbsp;&nbsp;</b></td>
                    <td><b></b></td>
                    <td>
                        <s:radio name="hakmilikPerbicaraan.catatan" value="Tangguh" />&nbsp;Tangguh
                    </td>
                </tr>
                <tr>
                    <td width="50%"><b>&nbsp;&nbsp;&nbsp;&nbsp;</b></td>
                    <td><b></b></td>
                    <td>
                        <s:radio name="hakmilikPerbicaraan.catatan" value="Bantahan" />&nbsp;Bantahan
                    </td>
                </tr>
            </table></label>

            <br>

        </fieldset>
    </div>
                    </c:if>
             <%--  --------- --%><%--${showPerintah}--%>
                    <br/>
                    <p align="center">
                        <s:hidden name="idHakmilik"/>
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        <c:if test="${actionBean.kodUrusan ne 'PHLLA'}">
                        <c:if test="${showPerintah}">
                            <s:button name="perintah" value="Perintah" class="btn" onclick="popupPerintah('${actionBean.idHakmilik}');"/>
                        </c:if>
                        </c:if>
                        <c:if test="${actionBean.kodUrusan ne 'PHLLP'}">
                        <c:if test="${showPerintah}">
                            <s:button name="perintah" value="Perintah" class="btn" onclick="popupPerintah('${actionBean.idHakmilik}');"/>
                        </c:if>
                        </c:if>
                    </p>
                    </c:if>

                     </fieldset>
    </div>

</s:form>
