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

        function tambah(){
            window.open("${pageContext.request.contextPath}/pengambilan/nota_siasatan1?showTuanTanahPopup", "eTanah",
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

        function limitText(limitField, limitNum) {
            if (limitField.value.length > limitNum) {
                limitField.value = limitField.value.substring(0, limitNum);
            }
        }


</script>

<s:form name="notaForm" beanclass="etanah.view.stripes.pengambilan.NotaSiasatan1ActionBean">
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
                        <s:link beanclass="etanah.view.stripes.pengambilan.NotaSiasatan1ActionBean"
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
                </table>
                <br/><br/>
                <p align="center">
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
                <br/><br/>

                Kehadiran
                <br/><br/>

                <p align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiPihakPentingBerdaftar}"  cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/nota_siasatan" id="line2">
                        <display:column title="No" sortable="true">${line2_rowNum}</display:column>
                        <display:column title="Nama" >
                            <c:if test="${line2.pihak ne null}"> ${line2.pihak.pihak.nama} </c:if>
                            <c:if test="${line2.pihak eq null}"> - </c:if>
                            <s:hidden name="nama[${line2_rowNum-1}]" value="${line2.pihak.pihak.nama}" />
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
                        <display:column title="Jawatan">
                            <c:if test="${line2.jawatan eq null}"> ${line2.jawatan} </c:if>
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
                    <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambah();"/>&nbsp;
                    <s:button class="btn" value="Hapus" name="remove" id="remove" onclick="deleteKehadiran('line1');"/>&nbsp;
                    <%--<s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahPBTDaftar();"/>&nbsp;
                    <s:button class="btn" value="Hapus" name="remove" id="remove" onclick="deleteKehadiran('line2');"/>&nbsp;--%>
                </p>
                <br/><br/>


                <%--Kehadiran
                <br/><br/>

                <p align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiPerbicaraanKehadiran}"  cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/nota_siasatan1" id="line1">
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
                            <s:radio name="bantah[${line1_rowNum-1}]" value="Y"/>Ya
                            <s:radio name="bantah[${line1_rowNum-1}]" value="T"/>Tidak
                        </display:column>
                    </display:table>
                    <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambah();"/>&nbsp;
                    <s:button class="btn" value="Hapus" name="remove" id="remove" onclick="deleteKehadiran('line1');"/>&nbsp;
                </p>--%>
                <br/><br/>
                <table>

                    <tr><td width="45%"><b>KETERANGAN TUAN TANAH</b></td></tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.pemohonUlasan" rows="3" cols="50" /></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.alasanTuntutan" rows="3" cols="50" /></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></td>
                        <td><b>:</b></td>
                        <td><b><s:textarea name="hakmilikPerbicaraan.akujanjiPenilai" rows="3" cols="50" /></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>TUNTUTAN</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.penilaiNama" rows="3" cols="50" /></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>MAKLUM BALAS TENAGA NASIONAL BERHAD</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.ulasanPenilai" rows="3" cols="50" /></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>MAKLUM BALAS TUAN TANAH</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.keteranganTuanpunya" rows="3" cols="50" /></b></td>
                    </tr>
                    <br>
                    <tr><td width="30%"><b>KEPUTUSAN/PERINTAH PENTADBIR TANAH</b></td></tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.perintahPtd" rows="3" cols="50" class="normal_text" style="border-bottom-style: none;resize: none;" /></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></td>
                        <td><b></b></td>
                        <td><b> <s:textarea name="makluman" id ="makluman" rows="2" cols="50" onkeydown="limitText(this,1000);" onkeyup="limitText(this,1000);" class="normal_text" style="border-top-style: none;" /></b></td>
                    </tr>
                    <%--<td>
                        <b> <s:textarea name="hakmilikPerbicaraan.keteranganTuanpunya" rows="3" cols="50" /></b>
                        <s:textarea
                            <s:textarea name="hakmilikPerbicaraan.perintahPtd" rows="3" cols="50" />
                            <s:textarea name="makluman" id ="makluman" rows="5" cols="50" onkeydown="limitText(this,1000);" onkeyup="limitText(this,1000);" class="normal_text" />
                        />
                    &nbsp;
                    </td>--%>
                    <%--</tr>--%>

                </table>
                <br>
                <div class="subtitle displaytag">

                </div>
                <c:if test="${actionBean.kodUrusan ne 'PHLLA' and 'PHLLP' and 'PILDA' and 'PTNB'}">
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
                    <c:if test="${actionBean.kodUrusan ne 'PHLLA' and 'PHLLP' and 'PILDA' and 'PTNB'}">
                        <c:if test="${showPerintah}">
                            <s:button name="perintah" value="Perintah" class="btn" onclick="popupPerintah('${actionBean.idHakmilik}');"/>
                        </c:if>
                    </c:if>

                </p>
            </c:if>

        </fieldset>
    </div>

</s:form>
