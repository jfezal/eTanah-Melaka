<%--
    Document   : rencana_Pertimbangan_Menteri
    Created on : July 06, 2010, 9:47:53 AM
    Author     : NageswaraRao
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:right;
        width:32em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:20em;
    }
</style>

<script type="text/javascript">

    function addRow1(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 2.3." +(rowcount+1)+"</b>";
        var cell1 = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'ahliLembaga' + (rowcount+1);
        el.rows = 5;
        el.cols = 145;
        cell1.appendChild(el);
        document.getElementById("rowCount1").value=rowcount+1;
    }


    function addRow2(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 3." +(rowcount+1)+"</b>";
        var leftcell = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'ulasan' + (rowcount+1);
        el.rows = 5;
        el.cols = 145;
        leftcell.appendChild(el);
        document.getElementById("rowCount2").value=rowcount+1;
    }

    function addRow3(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 5." +(rowcount+1)+"</b>";
        var leftcell = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'perakuanPengarah' + (rowcount+1);
        el.rows = 5;
        el.cols = 145;
        leftcell.appendChild(el);
        document.getElementById("rowCount3").value=rowcount+1;
    }

    function addRow4(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 7." +(rowcount+1)+"</b>";
        var leftcell = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'syarat' + (rowcount+1);
        el.rows = 5;
        el.cols = 145;
        leftcell.appendChild(el);
        document.getElementById("rowCount4").value=rowcount+1;
    }



    function deleteRow1()
    {
        var j = document.getElementById('rowCount1').value;
        if(j == 1){
            alert("Tiada Boleh Dihapuskan");
            return false;
        }
        var x= document.getElementById('dataTable1').rows[j-1].cells;
        var idKandungan = x[0].innerHTML;
        document.getElementById('dataTable1').deleteRow(j-1);
        var rc1 = $('#rowCount1').val();
        var c1 = $('#count1').val();
        if(rc1 == c1){
            var url = '${pageContext.request.contextPath}/pembangunan/melaka/rencanaPertimbanganMenteri?deleteSingle&idKandungan='
                +idKandungan;
    <%--alert("url:"+url);--%>
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
            document.getElementById('rowCount1').value= j -1;
        }

        function deleteRow2()
        {
            var j = document.getElementById('rowCount2').value;
            if(j == 1){
                alert("Tiada Boleh Dihapuskan");
                return false;
            }
            var x= document.getElementById('dataTable2').rows[j-1].cells;
            var idKandungan = x[0].innerHTML;
            document.getElementById('dataTable2').deleteRow(j-1);
            var rc2 = $('#rowCount2').val();
            var c2 = $('#count2').val();
    <%--alert("rc1:"+rc2);
    alert("c1:"+c2);--%>
            if(rc2 == c2){
                var url = '${pageContext.request.contextPath}/pembangunan/melaka/rencanaPertimbanganMenteri?deleteSingle&idKandungan='+idKandungan;
    <%--alert(url);--%>
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
            document.getElementById('rowCount2').value= j -1;
        }

        function deleteRow3()
        {
            var j = document.getElementById('rowCount3').value;
            if(j == 1){
                alert("Tiada Boleh Dihapuskan");
                return false;
            }
            var x= document.getElementById('dataTable3').rows[j-1].cells;
            var idKandungan = x[0].innerHTML;
            document.getElementById('dataTable3').deleteRow(j-1);
            var rc2 = $('#rowCount3').val();
            var c2 = $('#count3').val();
    <%--alert("rc1:"+rc2);
    alert("c1:"+c2);--%>
            if(rc2 == c2){
                var url = '${pageContext.request.contextPath}/pembangunan/melaka/rencanaPertimbanganMenteri?deleteSingle&idKandungan='+idKandungan;
    <%--alert(url);--%>
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
            document.getElementById('rowCount3').value= j -1;
        }


        function deleteRow4(valRow)
        {
           // alert("valRow:"+valRow);
            var j = document.getElementById('rowCount4').value;
           // alert("total row"+j);
            if(j == 1){
                alert("Tiada Boleh Dihapuskan");
                return false;
            }
            //var x= document.getElementById('dataTable4').rows[j-1].cells;
            var x= document.getElementById('dataTable4').rows[valRow-1].cells;
            var idKandungan = x[0].innerHTML;
            //alert("x"+x);
            //alert("idKandungan:"+idKandungan);
            //document.getElementById('dataTable4').deleteRow(j-1);
            document.getElementById('dataTable4').deleteRow(valRow-1);
            var rc2 = $('#rowCount4').val();
            var c2 = $('#count4').val();
    <%--alert("rc1:"+rc2);
    alert("c1:"+c2);--%>
           // if(rc2 == c2){
                var url = '${pageContext.request.contextPath}/pembangunan/melaka/rencanaPertimbanganMenteri?deleteSingle&idKandungan='+idKandungan;
    <%--alert(url);--%>
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            //}
            document.getElementById('rowCount4').value= j -1;
        }


        $(document).ready( function() {


            var len = $(".daerah").length;

            for (var i=0; i<=len; i++){
                $('.hakmilik'+i).click( function() {
                    window.open("${pageContext.request.contextPath}/pembangunan/melaka/rencanaPertimbanganMenteri?hakmilikDetail&idHakmilik="+$(this).text(), "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
                });
            }
        });
    
        function TrimZeros(instring) {
            return instring.replace(/^0+/g,'');
        }
  
</script>
<s:form beanclass="etanah.view.stripes.pembangunan.RencanaPertimbanganMenteriActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kertasK.kertas.idKertas"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>          	
            <div class="content" align="center">
                <table border="0" width="80%" cellspacing="10">
                    <tr><td align="center"><b>RENCANA UNTUK PERTIMBANGAN YAB KETUA MENTERI MELAKA</b></td></tr>

                    <c:if test="${edit and actionBean.pengguna.kodCawangan.kod ne '00'}">
                        <tr><td><b><s:textarea name="tajuk" rows="5" cols="150" class="normal_text"/></b></td></tr>
                    </c:if>
                    <c:if test="${!edit or actionBean.pengguna.kodCawangan.kod eq '00'}">
                        <tr><td><b><font style="text-transform:uppercase">${actionBean.tajuk}</font></b></td></tr>
                                </c:if>

                    <tr><td><b>1. TUJUAN</b></td></tr>
                    <c:if test="${edit and actionBean.pengguna.kodCawangan.kod ne '00'}">
                        <tr><td><b><s:textarea name="tujuan" rows="5" cols="150" class="normal_text"/></b></td></tr>
                    </c:if>
                    <c:if test="${!edit or actionBean.pengguna.kodCawangan.kod eq '00'}">
                        <tr><td><b>${actionBean.tujuan}</b></td></tr>
                    </c:if>

                    <tr><td><b>2. LATAR BELAKANG</b></td></tr>
                    <tr><td><b>2.1  Perihal Permohonan</b></td></tr>
                    <c:if test="${edit and actionBean.pengguna.kodCawangan.kod ne '00'}">
                        <tr><td><s:textarea rows="5" cols="150" name="perihalPermohonan" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${!edit or actionBean.pengguna.kodCawangan.kod eq '00'}">
                        <tr><td>${actionBean.perihalPermohonan}</td></tr>
                    </c:if>

                    <tr><td><b>2.2  Perihal Pemohon</b></td></tr>
                    <c:if test="${edit and actionBean.pengguna.kodCawangan.kod ne '00'}">
                        <tr><td><s:textarea rows="5" cols="150" name="perihalPemohon" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${!edit or actionBean.pengguna.kodCawangan.kod eq '00'}">
                        <tr><td>${actionBean.perihalPemohon}</td></tr>
                    </c:if>

                    <tr><td><b>2.3 Senarai Ahli Lembaga Pengarah Syarikat seperti berikut:</b></td></tr>
                    <c:if test="${edit and actionBean.pengguna.kodCawangan.kod ne '00'}">
                        <tr><td>
                                <table id ="dataTable1">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKandungan1}" var="line">
                                        <tr>
                                            <td style="display:none">${line.idKandungan}</td>
                                            <td><b><c:out value="2.3.${i}"/></b></td>
                                            <td><s:textarea name="ahliLembaga${i}" id="ahliLembaga${i}"  rows="5" cols="145" class="normal_text">${line.kandungan}</s:textarea> </td>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>                    
                        <tr><td><s:hidden name="rowCount1" id="rowCount1"/>
                                <s:hidden name="count1" id="count1" value="${fn:length(actionBean.senaraiKandungan1)}"/>
                            </td></tr>
                        <tr>
                            <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <s:button class="btn" value="Tambah" name="add" onclick="addRow1('dataTable1')"/>&nbsp;
                                <s:button name="hapus" value="Hapus" class="btn" onclick="deleteRow1()" />
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!edit or actionBean.pengguna.kodCawangan.kod eq '00' }">
                        <tr><td>
                                <table>
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKandungan1}" var="line">
                                        <tr><td>&nbsp;&nbsp; <b><c:out value="2.3.${i}"/></b>&nbsp;&nbsp;</td>
                                            <td>${line.kandungan}</td>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                    </c:if>

                    <tr><td><b>2.4 Perihal Tanah</b></td></tr>
                    <c:if test="${edit and actionBean.pengguna.kodCawangan.kod ne '00'}">
                        <tr><td><s:textarea rows="5" cols="150" name="perihalTanah" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${!edit or actionBean.pengguna.kodCawangan.kod eq '00'}">
                        <tr><td>${actionBean.perihalTanah}</td></tr>
                    </c:if>

                    <tr><td><b>2.5  Ulasan ADUN Kawasan</b></td></tr>
                    <tr><td>
                            <c:if test="${edit and actionBean.pengguna.kodCawangan.kod ne '00'}">
                                <c:if test="${actionBean.ulasanAdun eq null}">
                                    TIADA DATA.
                                </c:if>
                                <c:if test="${actionBean.ulasanAdun ne null}">
                                    <s:textarea rows="5" cols="160" name="ulasan" class="normal_text" />
                                </c:if>
                            </c:if>
                            <c:if test="${!edit or actionBean.pengguna.kodCawangan.kod eq '00'}">
                                <c:if test="${actionBean.ulasanAdun eq null}">
                                    TIADA DATA.
                                </c:if>
                                <c:if test="${actionBean.ulasanAdun ne null}">
                                    ${actionBean.ulasan}
                                </c:if>
                            </c:if>
                        </td></tr>

                    <tr><td><b>2.6  Butir-Butir Hakmilik </b></td></tr>
                    <tr><td>
                            <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanListHM}" cellpadding="0" cellspacing="0"
                                           requestURI="/common/maklumat_hakmilik_permohonan" id="line">
                                <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                                <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}" style="vertical-align:baseline"/>
                                <display:column title="Nombor Lot/PT" style="vertical-align:baseline">
                                    ${line.hakmilik.lot.nama} ${(actionBean.noLotList[line_rowNum-1])} 
                                </display:column>
                                <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                                <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah" style="vertical-align:baseline;text-transform:capitalize"/>
                                <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="vertical-align:baseline;text-transform:capitalize"/>
                            </display:table>
                            <br></br>
                            <%--
                            <table border="0" width="60%">
                                <c:if test="${actionBean.saizList eq 1}">
                                    <tr>
                                        <td id="tdLabel"><font color="black">No.Lot/PT :</font></td>
                                        <td id="tdDisplay">${actionBean.hakmilikSingle.lot.nama}&nbsp; <fmt:formatNumber  pattern="00" value="${actionBean.hakmilikSingle.noLot}"/> &nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Jenis dan No. Hakmilik :</font></td>
                                        <td id="tdDisplay">${actionBean.hakmilikSingle.kodHakmilik.kod}&nbsp; <fmt:formatNumber  pattern="00" value="${actionBean.hakmilikSingle.noHakmilik}"/> &nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Tempoh Pegangan :</font></td>
                                        <td id="tdDisplay">Pajakan ${actionBean.hakmilikSingle.tempohPegangan} tahun&nbsp;</td>
                                    </tr>
                                    <c:if test="${actionBean.hakmilikSingle.pegangan eq 'P'}">
                                        <tr>
                                            <td id="tdLabel"><font color="black">Tarikh Luput Pajakan :</font></td>
                                            <td id="tdDisplay">${actionBean.hakmilikSingle.tarikhLuput}</td>
                                        </tr>
                                    </c:if>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Bandar/Pekan/Mukim :</font></td>
                                        <td id="tdDisplay">${actionBean.hakmilikSingle.bandarPekanMukim.nama} &nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Luas :</font></td>
                                        <td id="tdDisplay"><fmt:formatNumber pattern="#,##0.0000" value="${actionBean.hakmilikSingle.luas}"/>&nbsp; ${actionBean.hakmilikSingle.kodUnitLuas.nama}</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Tuan Tanah :</font></td>
                                        <td>
                                            <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList[0].hakmilik.senaraiPihakBerkepentingan}" cellpadding="0" cellspacing="0" id="tbl2">
                                                <display:column title="Nama" style="vertical-align:baseline">
                                                    <c:if test="${tbl2.jenis.kod eq 'PM' && tbl2.aktif eq 'Y'}">                                                     
                                                        ${tbl2.pihak.nama}                                                   
                                                    </c:if>
                                                </display:column>
                                                <display:column title="Bahagian Dimiliki" style="vertical-align:baseline">
                                                    <c:if test="${tbl2.jenis.kod eq 'PM' && tbl2.aktif eq 'Y'}">     
                                                        ${tbl2.syerPembilang}/${tbl2.syerPenyebut}
                                                    </c:if>
                                                </display:column>
                                            </display:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Jenis Kegunaan Tanah :</font></td>
                                        <td id="tdDisplay">${actionBean.hakmilikSingle.kegunaanTanah.nama}</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Syarat Nyata :</font></td>
                                        <td id="tdDisplay">${actionBean.hakmilikSingle.syaratNyata.syarat}</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Sekatan Kepentingan :</font></td>
                                        <td id="tdDisplay">${actionBean.hakmilikSingle.sekatanKepentingan.sekatan}</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Bebanan Berdaftar :</font></td>
                                        <td id="tdDisplay">-</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Surat Persetujuan Daripada :</font></td>
                                        <td id="tdDisplay">-</td>
                                    </tr>
                                </c:if>
                            --%>

                            <%--<c:if test="${actionBean.saizList > 1}">--%>
                            <!--<div align ="center">-->

                            <!--</div>-->
                            <%--</c:if>--%>


                            <%--
                            <tr>
                                <td id="tdLabel"><font color="black">No.Lot/PT :</font></td>
                                <td id="tdDisplay">Sila Rujuk Lampiran</td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><font color="black">Jenis dan No. Hakmilik :</font></td>
                                <td id="tdDisplay">Sila Rujuk Lampiran</td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><font color="black">Tempoh Pegangan :</font></td>
                                <td id="tdDisplay">Sila Rujuk Lampiran</td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><font color="black">Bandar/Pekan/Mukim :</font></td>
                                <td id="tdDisplay">Sila Rujuk Lampiran</td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><font color="black">Luas :</font></td>
                                <td id="tdDisplay">Sila Rujuk Lampiran</td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><font color="black">Tuan Tanah :</font></td>
                                <td id="tdDisplay">Sila Rujuk Lampiran</td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><font color="black">Jenis Kegunaan Tanah :</font></td>
                                <td id="tdDisplay">Sila Rujuk Lampiran</td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><font color="black">Syarat Nyata :</font></td>
                                <td id="tdDisplay">Sila Rujuk Lampiran</td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><font color="black">Sekatan Kepentingan :</font></td>
                                <td id="tdDisplay">Sila Rujuk Lampiran</td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><font color="black">Bebanan Berdaftar :</font></td>
                                <td id="tdDisplay">Sila Rujuk Lampiran</td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><font color="black">Surat Persetujuan Daripada :</font></td>
                                <td id="tdDisplay">Sila Rujuk Lampiran</td>
                            </tr>
                            --%>
                            <%--</c:if>--%>
                            <!--                            </table>
                                                    </td></tr>-->

                    <tr><td><b>2.7 Butir-butir Pembangunan </b></td></tr>
                    <!--<HEKTAR>-->
                    <tr><td>
                            <table class="tablecloth" border="1" width="85%" id="tbl">
                                <tr>
                                    <th width="5%" align="center"><b>Bil.</b></th>
                                    <th width="17%" align="center"><b>Jenis Kategori</b></th>                                   
                                    <th width="10%" align="center"><b>Bilangan Unit</b></th>
                                    <th width="16%" align="center"><b>Keluasan</b></th>
                                </tr>
                                <c:set var="recordCount" value="0"/>
                                <c:set var="billNo" value="0"/>
                                <c:set var="k" value="1"/>
                                <c:forEach items="${actionBean.senaraiPermohonanPlotPelan}" var="plotPelan">
                                    <tr>
                                        <c:if test ="${plotPelan.pemilikan.kod eq 'H'}">
                                            <td>${k}.</td>
                                            <td>${plotPelan.kategoriTanah.nama}</td>
                                            <td>${plotPelan.bilanganPlot} </td>
                                            <td>${plotPelan.luas} &nbsp; ${actionBean.hakmilik.kodUnitLuas.nama}</td>
                                        </c:if>
                                    </tr>
                                    <c:set var="k" value="${k+1}" />
                                </c:forEach>
                            </table>
                        </td>
                    </tr>


                    <tr><td><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Infrastruktur</b></td></tr>
                    <tr><td>
                            <table class="tablecloth" border="1" width="85%" id="tb2">
                                <tr>
                                    <th width="5%" align="center"><b>Bil.</b></th>
                                    <th width="17%" align="center"><b>Jenis Kategori</b></th>                                  
                                    <th width="10%" align="center"><b>Bilangan Unit</b></th>
                                    <th width="16%" align="center"><b>Keluasan</b></th>
                                </tr>
                                <c:set var="recordCount" value="0"/>
                                <c:set var="billNo" value="0"/>
                                <c:set var="i" value="1"/>
                                <c:forEach items="${actionBean.senaraiPermohonanPlotPelanForK}" var="plotPelanForK">
                                    <tr>
                                        <c:if test ="${plotPelanForK.pemilikan.kod eq 'K'}">
                                            <td>${i}.</td>
                                            <td>${plotPelanForK.kegunaanTanahLain}</td>
                                            <td>${plotPelanForK.bilanganPlot} </td>
                                            <td>${plotPelanForK.luas} &nbsp; ${actionBean.hakmilik.kodUnitLuas.nama}</td>
                                        </c:if>                                        
                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table>
                        </td>
                    </tr>


                    <tr><td><b>2.8 Senarai Harga </b></td></tr>
                    <c:if test="${edit and actionBean.pengguna.kodCawangan.kod ne '00'}">
                        <tr><td><s:textarea name="harga" rows="5" cols="150" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${!edit or actionBean.pengguna.kodCawangan.kod eq '00'}">
                        <tr><td>${actionBean.harga}&nbsp;</td></tr>
                    </c:if>

                    <tr><td><b>3. KEPUTUSAN JAWATANKUASA BELAH BAHAGI NEGERI MELAKA </b></td></tr>
                    <c:if test="${edit and actionBean.pengguna.kodCawangan.kod ne '00'}">
                        <tr><td>
                                <table id ="dataTable2">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKandungan2}" var="line">
                                        <tr>
                                            <td style="display:none">${line.idKandungan}</td>
                                            <td><b>3.${i}</b></td>
                                            <td><s:textarea name="ulasan${i}" id="ulasan${i}"  rows="5" cols="145" class="normal_text">${line.kandungan}</s:textarea> </td>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td><s:hidden name="rowCount2" id="rowCount2"/>
                                <s:hidden name="count2" id="count2" value="${fn:length(actionBean.senaraiKandungan2)}"/> </td>
                        </tr>
                        <tr>
                            <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <s:button class="btn" value="Tambah" name="add" onclick="addRow2('dataTable2')"/>&nbsp;
                                <s:button name="hapus" value="Hapus" class="btn" onclick="deleteRow2()" /></td>
                        </tr>
                    </c:if>
                    <c:if test="${!edit or actionBean.pengguna.kodCawangan.kod eq '00'}">
                        <tr><td>
                                <table cellspacing="10">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKandungan2}" var="line">
                                        <tr><td valign="top"><b> 3.${i} </b></td>
                                            <td>${line.kandungan} </td>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                    </c:if>

                    <tr><td><b><font style="text-transform:uppercase">4. PERAKUAN PENTADBIR TANAH ${actionBean.pejTanah}</font></b></td></tr>
                                <c:if test="${edit and actionBean.pengguna.kodCawangan.kod ne '00'}">
                        <tr><td><s:textarea name="perakuan" rows="5" cols="150" id="perakuan" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${!edit or actionBean.pengguna.kodCawangan.kod eq '00'}">
                        <tr><td>${actionBean.perakuan}&nbsp;</td></tr>
                    </c:if>

                    <c:if test="${edit}">
                        <c:if test="${actionBean.pengguna.kodCawangan.kod eq '00'}">
                            <tr><td><b>5. PERAKUAN PENGARAH TANAH DAN GALIAN MELAKA</b></td></tr>
                            <tr><td>    
                            <table id ="dataTable3">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKandungan3}" var="line">
                                    <tr>
                                        <td style="display:none">${line.idKandungan}</td>
                                        <td><b>5.${i}</b></td>
                                        <td><s:textarea name="perakuanPengarah${i}" id="perakuanPengarah${i}"  rows="5" cols="145" class="normal_text">${line.kandungan}</s:textarea> </td>
                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table>

                            <tr>
                                <td>
                                    <s:hidden name="rowCount3" id="rowCount3"/>
                                    <s:hidden name="count3" id="count3" value="${fn:length(actionBean.senaraiKandungan3)}"/> 
                                </td>
                            </tr>
                            <tr>
                                <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <s:button class="btn" value="Tambah" name="add" onclick="addRow3('dataTable3')"/>&nbsp;
                                    <s:button name="hapus" value="Hapus" class="btn" onclick="deleteRow3()" />
                                </td>
                            </tr>

                            <%--
                            <tr>
                                <td>
                                    <s:textarea name="perakuanptgmelaka" rows="7" cols="150" id="perakuanptgmelaka" class="normal_text"/>
                                </td>
                            </tr>
                            --%>
                        </c:if>
                    </c:if>

                    <c:if test="${!edit}">
                        <c:if test="${actionBean.pengguna.kodCawangan.kod eq '00'}">
                            <tr><td><b>5. PERAKUAN PENGARAH TANAH DAN GALIAN MELAKA</b></td></tr>
                            <tr>
                                <td>
                                    <table cellspacing="10">
                                        <c:set var="i" value="1" />
                                        <c:forEach items="${actionBean.senaraiKandungan3}" var="line">
                                            <tr><td valign="top"><b> 5.${i} </b></td>
                                                <td>${line.kandungan} </td>
                                            </tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                            <%--<tr><td>${actionBean.perakuanptgmelaka}&nbsp;</td></tr>--%>
                        </c:if>
                    </c:if>
                    <%--
                            <table id ="dataTable3">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKandungan3}" var="line">
                                    <tr>
                                        <td style="display:none">${line.idKandungan}</td>
                                        <td><b>5.${i}</b></td>
                                        <td><s:textarea name="perakuanPengarah${i}" id="perakuanPengarah${i}"  rows="5" cols="145" class="normal_text">${line.kandungan}</s:textarea> </td>
                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table>
                                                      
                    <tr>
                        <td><s:hidden name="rowCount3" id="rowCount3"/>
                            <s:hidden name="count3" id="count3" value="${fn:length(actionBean.senaraiKandungan3)}"/> </td>
                    </tr>
                    <tr>
                        <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <s:button class="btn" value="Tambah" name="add" onclick="addRow3('dataTable3')"/>&nbsp;
                            <s:button name="hapus" value="Hapus" class="btn" onclick="deleteRow3()" /></td>
                    </tr>
                    --%>
                    <%--<tr><td><s:textarea name="perakuanptg" rows="5" cols="150" id="perakuanptg" class="normal_text"/></td></tr>--%>



                    <%--
                    <tr><td>
                            <table cellspacing="10">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKandungan3}" var="line">
                                    <tr><td valign="top"><b> 5.${i} </b></td>
                                        <td>${line.kandungan} </td>
                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                    --%>

                    <br/>
                    <c:if test="${actionBean.pengguna.kodCawangan.kod eq '00'}">
                        <c:if test="${edit}">
                            <tr><td>
                                    <table>
                                        <tr>
                                            <td><b>6.</b></td>
                                            <td><s:textarea name="sementara" rows="5" cols="150" class="normal_text"/> </td>
                                        </tr>
                                    </table>
                                </td></tr>
                            </c:if>
                            <c:if test="${!edit}">
                            <tr><td>
                                    <table>
                                        <tr>
                                            <td><b>6.</b></td>
                                            <td${actionBean.sementara} </td>
                                        </tr>
                                    </table>
                                </td></tr>
                            </c:if>
                        </c:if>
                        <c:if test="${actionBean.pengguna.kodCawangan.kod eq '00'}">
                            <c:if test="${edit}">                                
                            <tr><td><b>7. Syarat-syarat am seperti berikut :-</b></td></tr>
                            <tr><td>
                                    <table id ="dataTable4">
                                        <c:set var="i" value="1" />
                                        <c:forEach items="${actionBean.senaraiKandungan4}" var="line">
                                            <tr>
                                                <td style="display:none">${line.idKandungan}</td>
                                                <td><b>7.${i}</b></td>
                                                <td>
                                                    <s:textarea name="syarat${i}" id="syarat${i}"  rows="5" cols="145" class="normal_text">${line.kandungan}</s:textarea>
                                                </td>
                                                <td>
                                                    <div align="center">
                                                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                             id='rem_${line_rowNum}' onclick="deleteRow4(${i})" onmouseover="this.style.cursor='pointer';">
                                                    </div>
                                                    <%--<s:button name="hapus" value="Hapus" class="btn" onclick="deleteRow4(${i})" />--%>
                                                </td>
                                            </tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td><s:hidden name="rowCount4" id="rowCount4"/>
                                    <s:hidden name="count4" id="count4" value="${fn:length(actionBean.senaraiKandungan4)}"/> </td>
                            </tr>
                            <tr>
                                <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <s:button class="btn" value="Tambah" name="add" onclick="addRow4('dataTable4')"/>&nbsp;
                                    <%--<s:button name="hapus" value="Hapus" class="btn" onclick="deleteRow4()" />--%>
                                </td>
                            </tr>                                    
                        </c:if>                            
                        <c:if test="${!edit}">                                
                            <tr><td><b>7. Syarat-syarat am seperti berikut :-</b></td></tr>
                            <tr><td>
                                    <table cellspacing="10">
                                        <c:set var="i" value="1" />
                                        <c:forEach items="${actionBean.senaraiKandungan4}" var="line">
                                            <tr><td valign="top"><b> 7.${i} </b></td>
                                                <td>${line.kandungan} </td>
                                            </tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>                                    
                        </c:if>
                    </c:if>
                    <c:if test="${actionBean.pengguna.kodCawangan.kod eq '00'}">
                        <c:if test="${edit}">
                            <tr><td><b>8. KEPUTUSAN DIPOHON</b></td></tr>
                            <tr><td><s:textarea name="keputusanDipohon" rows="5" cols="150" id="perakuan" class="normal_text"/></td></tr>
                        </c:if>
                        <c:if test="${!edit}">
                            <tr><td><b>8. KEPUTUSAN DIPOHON</b></td></tr>
                            <tr><td>${actionBean.keputusanDipohon}&nbsp;</td></tr>
                        </c:if>
                    </c:if>


                </table>
                <c:if test="${edit}">
                    <br>
                    <p align="center">
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </c:if>
            </div>
        </fieldset>
    </div>
</s:form>