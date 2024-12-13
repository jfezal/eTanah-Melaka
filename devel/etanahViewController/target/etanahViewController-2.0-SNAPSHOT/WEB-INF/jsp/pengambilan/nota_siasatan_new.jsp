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
            var url = '${pageContext.request.contextPath}/pengambilan/nota_siasatan?showEditTuanTanah&idPihak='+h;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }

        function tambahPBTDaftar(){
            window.open("${pageContext.request.contextPath}/pengambilan/nota_siasatan?showPBTDaftarPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }
        
        function tambahPBTXDaftar(){
            window.open("${pageContext.request.contextPath}/pengambilan/nota_siasatan?showPBTXDaftarPopup", "eTanah",
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

        function showReport(idHakmilik){
            window.open("${pageContext.request.contextPath}/pengambilan/nota_siasatan?genReport&idHakmilik="+idHakmilik, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
        }


</script>

<s:form name="notaForm" beanclass="etanah.view.stripes.pengambilan.NotaSiasatanActionBean">
    <s:hidden name="pengguna" value="ppview"/>
    <s:hidden name="pengguna" value="ppadd"/>
    <s:messages/>

    <div  id="hakmilik_details">
        <s:hidden name="hakmilikPerbicaraan.idPerbicaraan" />
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
                ID Hakmilik : ${actionBean.hakmilik.idHakmilik}
                <table>
                    <tr>
                        <td width="50%"><b>&nbsp;&nbsp;&nbsp;&nbsp;Bil Kes</b></td>
                        <td><b>:</b></td>
                        <td><b>${actionBean.hakmilikPerbicaraan.idPerbicaraan}</b></td>

                    </tr>
                    <tr>
                        <td width="50%"><b>&nbsp;&nbsp;&nbsp;&nbsp;Perbicaraan Bil</b></td>
                        <td><b>:</b></td>
                        <td><b><s:text name="hakmilikPerbicaraan.noRujukan" value="${actionBean.hakmilikPerbicaraan.noRujukan}" size="20"/></b></td>

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
                Senarai Tuan Tanah
                <br/><br/>

                <p align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiTuanTanah}"  cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/nota_siasatan" id="line1">
                        <display:column title="No" sortable="true">${line1_rowNum}</display:column>
                        <display:column title="Nama" >
                            <c:if test="${line1.nama ne null}"> ${line1.nama} </c:if>
                            <c:if test="${line1.nama eq null}"> - </c:if>
                        </display:column>
                        <display:column title="No KP" >
                            <c:if test="${line1.noPengenalan ne null}"> ${line1.noPengenalan} </c:if>
                            <c:if test="${line1.noPengenalan eq null}"> - </c:if>
                        </display:column>
                        <display:column title="Alamat" style="vertical-align:baseline">
                            <c:if test="${line1.alamat1 ne null}">
                                ${line1.alamat1}
                                <c:if test="${line1.alamat2 ne null}"> , ${line1.alamat2} </c:if>
                                <c:if test="${line1.alamat3 ne null}"> , ${line1.alamat3} </c:if>
                                <c:if test="${line1.alamat4 ne null}"> , ${line1.alamat4} </c:if>
                            </c:if>
                            <c:if test="${line1.alamat1 eq null}">
                                -
                            </c:if>
                        </display:column>
                        <display:column title="Poskod">
                            <c:if test="${line1.poskod ne null}"> 
                                ${line1.poskod}
                            </c:if>
                            <c:if test="${line1.poskod eq null}">
                                -
                            </c:if>
                        </display:column>
                        <display:column title="Negeri">
                            <c:if test="${line1.negeri ne null}"> 
                                ${line1.negeri.nama}
                            </c:if>
                            <c:if test="${line1.negeri eq null}">
                                -
                            </c:if>
                        </display:column>
                        <display:column title="No Tel" style="vertical-align:baseline">
                            <c:if test="${line1.noTelefon1 ne null}">
                                ${line1.noTelefon1}
                                <c:if test="${line1.noTelefon2 ne null}">, ${line1.noTelefon2} </c:if>
                            </c:if>
                            <c:if test="${line1.noTelefon1 eq null}">
                                -
                            </c:if>
                        </display:column>
                        <%--  <display:column title="Jawatan">
                              <c:if test="${line1.idpihak eq null}"> ${line1.jawatan} </c:if>
                          </display:column>--%>
                        <%--  <display:column title="Status Kehadiran">
                              <s:radio name="hadir1[${line1_rowNum-1}]" value="1"/>Hadir<br/>
                              <s:radio name="hadir1[${line1_rowNum-1}]" value="0"/>Tidak Hadir<br/>
                          </display:column>--%>
                        <%--  <display:column title="Keputusan">
                              <s:radio name="keputusan1[${line1_rowNum-1}]" value="0"/>Setuju<br/>
                              <s:radio name="keputusan1[${line1_rowNum-1}]" value="1"/>Bantah<br/>
                          </display:column>--%>
                    </display:table>
                    <%--<s:button class="btn" value="Tambah" name="new" id="new" onclick="tambah();"/>&nbsp;
                    <s:button class="btn" value="Hapus" name="remove" id="remove" onclick="deleteKehadiran('line1');"/>&nbsp;--%>
                </p>
                <br/><br/>
                PBT Daftar
                <br/><br/>

                <p align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiPihakPentingBerdaftar}"  cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/nota_siasatan" id="line2">
                        <display:column title="No" sortable="true">${line2_rowNum}</display:column>
                        <display:column title="Nama" >
                            <c:if test="${line2.pihak ne null}"> ${line2.pihak.pihak.nama} </c:if>
                            <c:if test="${line2.pihak eq null}"> - </c:if>
                        </display:column>
                        <display:column title="No KP" >
                            <c:if test="${line2.pihak ne null}"> ${line2.pihak.pihak.noPengenalan} </c:if>
                            <c:if test="${line2.pihak eq null}"> - </c:if>
                        </display:column>
                        <display:column title="Alamat" style="vertical-align:baseline">
                            <c:if test="${line2.pihak ne null}">
                                ${line2.pihak.pihak.alamat1}
                                <c:if test="${line2.pihak.pihak.alamat2 ne null}"> , ${line2.pihak.pihak.alamat2} </c:if>
                                <c:if test="${line2.pihak.pihak.alamat3 ne null}"> , ${line2.pihak.pihak.alamat3} </c:if>
                                <c:if test="${line2.pihak.pihak.alamat4 ne null}"> , ${line2.pihak.pihak.alamat4} </c:if>
                            </c:if>
                            <c:if test="${line2.pihak eq null}">
                                -
                            </c:if>
                        </display:column>
                        <display:column title="Poskod">
                            <c:if test="${line2.pihak ne null}"> 
                                ${line2.pihak.pihak.poskod}
                            </c:if>
                            <c:if test="${line2.pihak eq null}">
                                -
                            </c:if>
                        </display:column>
                        <display:column title="Negeri">
                            <c:if test="${line2.pihak ne null}"> 
                                ${line2.pihak.pihak.negeri.nama}
                            </c:if>
                            <c:if test="${line2.pihak eq null}">
                                -
                            </c:if>
                        </display:column>
                        <display:column title="No Tel" style="vertical-align:baseline">
                            <c:if test="${line2.pihak ne null}">
                                ${line2.pihak.pihak.noTelefon1}
                                <c:if test="${line2.pihak ne null}">, ${line2.pihak.pihak.noTelefon2} </c:if>
                            </c:if>
                            <c:if test="${line2.pihak eq null}">
                                -
                            </c:if>
                        </display:column>
                       <display:column title="Tarikh Pemilikan Tanah">
                            
                            <s:text name="tarikhMilik[${line2_rowNum-1}]" id="tarikhMilik[${line2_rowNum-1}]" class="datepicker" formatPattern="dd/MM/yyyy"/>
                        </display:column>
                        <display:column title="Cara pemilikan tanah">
                            <s:text name="caraMilik[${line2_rowNum-1}]" size="20"/>
                        </display:column>
                        <display:column title="Harga Pembelian Tanah">
                            <s:text name="hargaMilik[${line2_rowNum-1}]" size="20" onkeyup="validateNumber(this,this.value);"/>
                        </display:column>        
                        <display:column title="Status Kehadiran">
                            <s:radio name="hadir[${line2_rowNum-1}]" value="1"/>Hadir<br/>
                            <s:radio name="hadir[${line2_rowNum-1}]" value="0"  checked="checked"/>Tidak Hadir<br/>
                            <s:hidden name="idHadir[${line2_rowNum-1}]" value="${line2.idKehadiran}" />
                        </display:column>
                        <display:column title="Keputusan">
                            <s:radio name="keputusan[${line2_rowNum-1}]" value="0"/>Setuju<br/>
                            <s:radio name="keputusan[${line2_rowNum-1}]" value="1"/>Bantah<br/>
                        </display:column>
                    </display:table>
                    <%--  <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahPBTDaftar();"/>&nbsp;
                      <s:button class="btn" value="Hapus" name="remove" id="remove" onclick="deleteKehadiran('line2');"/>&nbsp;--%>
                </p>
                <br/><br/>
                PBT Tidak Berdaftar
                <br/><br/>

                <p align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiPermohonanPihakTdkBKepentingan}"  cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/nota_siasatan" id="line3">
                        <display:column title="No" sortable="true">${line3_rowNum}</display:column>
                        <display:column title="Nama" >
                            <c:if test="${actionBean.perbicaraanKehadiran ne null}"> ${actionBean.perbicaraanKehadiran.nama} </c:if>
                            <c:if test="${actionBean.perbicaraanKehadiran eq null}"> - </c:if>
                        </display:column>
                        <display:column title="No KP" >
                            <c:if test="${actionBean.perbicaraanKehadiran ne null}"> ${actionBean.perbicaraanKehadiran.noPengenalan} </c:if>
                            <c:if test="${actionBean.perbicaraanKehadiran eq null}"> - </c:if>
                        </display:column>
                        <display:column title="Alamat" style="vertical-align:baseline">
                            <c:if test="${line3.permohonanPihakTidakBerkepentingan ne null}">
                                ${line3.permohonanPihakTidakBerkepentingan.alamat1}
                                <c:if test="${line3.permohonanPihakTidakBerkepentingan ne null}"> , ${line3.permohonanPihakTidakBerkepentingan.alamat2} </c:if>
                                <c:if test="${line3.permohonanPihakTidakBerkepentingan ne null}"> , ${line3.permohonanPihakTidakBerkepentingan.alamat3} </c:if>
                                <c:if test="${line3.permohonanPihakTidakBerkepentingan ne null}"> , ${line3.permohonanPihakTidakBerkepentingan.alamat4} </c:if>
                            </c:if>
                            <c:if test="${line3.permohonanPihakTidakBerkepentingan eq null}">
                                -
                            </c:if>
                        </display:column>
                        <display:column title="Poskod">
                            <c:if test="${line3.permohonanPihakTidakBerkepentingan ne null}"> 
                                ${line3.permohonanPihakTidakBerkepentingan.poskod}
                            </c:if>
                            <c:if test="${line3.permohonanPihakTidakBerkepentingan eq null}">
                                -
                            </c:if>
                        </display:column>
                        <display:column title="Negeri">
                            <c:if test="${line3.permohonanPihakTidakBerkepentingan ne null}">
                                ${line3.permohonanPihakTidakBerkepentingan.kodNegeri.nama}
                            </c:if>
                            <c:if test="${line3.permohonanPihakTidakBerkepentingan eq null}">
                                -
                            </c:if>
                        </display:column>
                        <display:column title="No Tel" style="vertical-align:baseline">
                            <c:if test="${line3.permohonanPihakTidakBerkepentingan ne null}">
                                ${line3.permohonanPihakTidakBerkepentingan.nomborTelefon1}
                                <c:if test="${line3.permohonanPihakTidakBerkepentingan ne null}">, ${line3.permohonanPihakTidakBerkepentingan.nomborTelefon2} </c:if>
                            </c:if>
                            <c:if test="${line3.permohonanPihakTidakBerkepentingan eq null}">
                                -
                            </c:if>
                        </display:column>
                        <display:column title="Jawatan">
                            <c:if test="${actionBean.perbicaraanKehadiran.jawatan eq null}"> ${actionBean.perbicaraanKehadiran.jawatan} </c:if>
                        </display:column>
                        <display:column title="Status Kehadiran">
                            <s:radio name="hadir2[${line3_rowNum-1}]" value="1"/>Hadir<br/>
                            <s:radio name="hadir2[${line3_rowNum-1}]" value="0"  checked="checked"/>Tidak Hadir<br/>
                            <s:hidden name="idHadir[${line3_rowNum-1}]" value="${actionBean.perbicaraanKehadiran.idKehadiran}" />
                        </display:column>
                        <display:column title="Keputusan">
                            <s:radio name="keputusan2[${line3_rowNum-1}]" value="0"/>Setuju<br/>
                            <s:radio name="keputusan2[${line3_rowNum-1}]" value="1"/>Bantah<br/>
                        </display:column>
                    </display:table>
                    <%--  <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahPBTXDaftar();"/>&nbsp;
                      <s:button class="btn" value="Hapus" name="remove" id="remove" onclick="deleteKehadiran('line3');"/>&nbsp;--%>
                </p>
                <br/><br/>
                <table>

                 <%--   <tr>
                        <td width="30%"><b>1) Tarikh permilikan tanah</b></td>
                        <td><b>:</b></td>
                        <td><b><s:text name="hakmilikPerbicaraan.tarikhPemilikan" id="trkDate" class="datepicker" formatPattern="dd/MM/yyyy"/>
                                <s:hidden name="hakmilikPerbicaraan.tarikhPemilikan" /></b></td>
                    </tr>--%>
                    <%--<tr>
                        <td width="30%"><b>1) Tarikh permilikan tanah atau</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:radio name="tujuan" id="tujuan1" value="F" onclick="javascript:test();"/>&nbsp;<s:text name="hakmilikPerbicaraan.tarikhPemilikan" id="trkDate" class="datepicker" disabled="true" formatPattern="dd/MM/yyyy"/>
                                <s:hidden name="hakmilikPerbicaraan.tarikhPemilikan" /></b></td>
                    </tr>--%>
                    <%--   <tr>
                           <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tahun pemilikan tanah</b></td>
                           <td><b>:</b></td>
                           <td><b><s:radio name="tujuan" id="tujuan2" value="S" onclick="javascript:test();"/>&nbsp;
                                   <s:text name="hakmilikPerbicaraan.tarikhPemilikan" id="trkYear" class="datepicker1" disabled="true" formatPattern="yyyy"/>
                               </b>
                           </td>
                       </tr>--%>

                 <%--   <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cara pemilikan tanah</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="hakmilikPerbicaraan.caraPemilikan" size="20"/></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Harga pembelian tanah(RM)</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="hakmilikPerbicaraan.hargaPembelian" size="20" onkeyup="validateNumber(this,this.value);"/></b></td>
                    </tr>--%>
                    <tr>
                        <td width="30%"><b>1) Jenis Tanaman</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.tanaman" rows="3" cols="50" /></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Bangunan</b></td>
                        <td><b>:</b></td>
                        <td><b><s:textarea name="hakmilikPerbicaraan.bangunan" rows="3" cols="50" /></b></td>
                    </tr>
                    <tr>
                        <td ><b>2) Permohonan tukar syarat/pecah sempadan.Jika ada,<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;bila dan bagaimana kedudukan sekarang.</b></td>
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
                    <tr><td width="45%"><b>3) Gadaian/Pajakan/Perjanjian Jualbeli</b></td>
                        <td><b>:</b></td>
                        <td><s:radio name="hakmilikPerbicaraan.ujudGPPJ" value="Y"/>&nbsp;Ada
                            <s:radio name="hakmilikPerbicaraan.ujudGPPJ" value="T"/>&nbsp;Tiada</td>
                    </tr>
                    <tr>
                        <td width=30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Komen</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.komenGPPJ" rows="3" cols="50" id="catatan"/></b></td>
                    </tr>
                    <tr><td width="45%"><b>4) Keterangan dan tuntutan orang yang berkepentingan</b></td></tr>
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
                    <tr><td width="45%"><b>5) Keterangan Jabatan Penilaian dan Perkhidmatan Harta</b></td></tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;i) Bilangan Surat JPPH</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="hakmilikPerbicaraan.penilaiNoRujukan" size="20"/></b></td>
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
                        <td width="30%"><b>6) Hal - hal lain</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.penilaiNama" rows="3" cols="50" /></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>7) Syarat Penggunaan Hak Lalu Lalang</b></td>
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
                    <tr><td width="45%"><b>8) Nilaian tanah yang ditentukan oleh Pentadbir Tanah </b></td></tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;i) Nilai tanah</b></td>
                        <td><b>:</b></td>
                        <td>&nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="hakmilikPerbicaraan.keputusanNilai" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00"/>
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

                <%--<c:if test="${actionBean.kodUrusan ne 'PHLLA' and 'PHLLP'}">--%>
                <div class="subtitle displaytag">
                    <fieldset class="aras1" id="locate">
                        <legend>
                            Keputusan Perbicaraan
                        </legend>
                        ${actionBean.kodUrusan}
                        <p align="center"><label>
                                <table align="center">
                                    <tr>
                                        <td width="50%"><b>&nbsp;&nbsp;&nbsp;&nbsp;Keputusan</b></td>
                                        <td><b>:</b></td>
                                        <td>
                                            <s:radio name="hakmilikPerbicaraan.catatan" value="Lulus" />&nbsp;Lulus- Menerima Tawaran
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="50%"><b>&nbsp;&nbsp;&nbsp;&nbsp;</b></td>
                                        <td><b></b></td>
                                        <td>
                                            <s:radio name="hakmilikPerbicaraan.catatan" value="Tangguh" />&nbsp;Tangguh- Tidak Menerima Tawaran
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="50%"><b>&nbsp;&nbsp;&nbsp;&nbsp;</b></td>
                                        <td><b></b></td>
                                        <td>
                                            <s:radio name="hakmilikPerbicaraan.catatan" value="Bantahan" />&nbsp;Bantahan- Menerima Bantahan Dengan Tawaran
                                        </td>
                                    </tr>
                                </table></label>

                            <br>

                    </fieldset>
                </div>
                <%--</c:if>--%>
                <%--  --------- --%><%--${showPerintah}--%>
                <br/>
                <p align="center">
                    <s:hidden name="idHakmilik"/>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    <c:if test="${showReport}">
                        <s:button name="genReport" id="report" value="Jana Borang" class="longbtn" onclick="showReport('${actionBean.idHakmilik}');"/>&nbsp;
                    </c:if>
                    <%--<c:if test="${actionBean.kodUrusan ne 'PHLLA' and 'PHLLP'}">--%>
                    <c:if test="${showPerintah}">
                        <s:button name="perintah" value="Perintah" class="btn" onclick="popupPerintah('${actionBean.idHakmilik}');"/>
                    </c:if>
                    <%--</c:if>--%>

                </p>
            </c:if>

        </fieldset>
    </div>

</s:form>
