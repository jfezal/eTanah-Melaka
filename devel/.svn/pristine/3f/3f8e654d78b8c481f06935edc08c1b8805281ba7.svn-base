<%--
    Document   : draf_jkbb_pjbtr
    Created on : Jan 6, 2013, 4:00:26 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
    function save1(event, f){

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);

        },'html');

    }
    function refreshDrafJKBB(){
        var edit = $('#edit').val();
        var url = '${pageContext.request.contextPath}/pelupusan/draf_jkbb_pjbtr?refreshDrafJKBB&edit='+edit;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function CurrencyFormatted(amount)
    {
        var i = parseFloat(amount);
        if(isNaN(i)) { i = 0.00; }
        var minus = '';
        if(i < 0) { minus = '-'; }
        i = Math.abs(i);
        i = parseInt((i + .005) * 100);
        i = i / 100;
        s = new String(i);
        if(s.indexOf('.') < 0) { s += '.00'; }
        if(s.indexOf('.') == (s.length - 2)) { s += '0'; }
        s = minus + s;
        return s;
    }
    function menyimpan(index,i,f)
    {
        /*
         * LEGEND : 22 -> Perihal Tanah 2.3.*
         */
        var kand;
        if(index == 2)
            kand = document.getElementById("kandungan2"+i).value;
        if(index == 22)
            kand = document.getElementById("kandungan22"+i).value;
        if(index == 24)
            kand = document.getElementById("kandungan24"+i).value;
        if(index == 25)
            kand = document.getElementById("kandungan25"+i).value;
        if(index == 3)
            kand = document.getElementById("kandungan3"+i).value;
        if(index==4){
            kand = document.getElementById("kandungan4"+i).value;
        }
        if(index==5){
            kand = document.getElementById("kandungan5"+i).value;
        }
        if(index==6){
            kand = document.getElementById("kandungan6"+i).value;
        }
        if(index==7){
            kand = document.getElementById("kandungan7"+i).value;
        }
        if(index==8){
            kand = document.getElementById("kandungan8"+i).value;
        }
        if(index==9){
            kand = document.getElementById("kandungan9"+i).value;
        }
        if(index==10){
            kand = document.getElementById("kandungan10"+i).value;
        }
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pelupusan/draf_jkbb_pjbtr?simpanKandungan&index='+index+'&kandungan='+kand,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');



    }
    function addRow(index,f)
    {
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pelupusan/draf_jkbb_pjbtr?tambahRow&index='+index,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function deleteRow(idKandungan,f)
    {
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/draf_jkbb_pjbtr?deleteRow&idKandungan='+idKandungan,q,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }
    }
</script>
<s:form beanclass="etanah.view.stripes.pelupusan.DrafJKBB_PJBTRActionBean" name="form" id="form">
    <s:messages/>
    <s:errors/>
    <s:hidden name="edit" id="edit"/>
    <s:hidden name="kertasK.idKertas"/>
    <s:hidden name="edit" id="edit"/>
    <s:hidden name="editPTD" id="editPTD"/>
    <s:hidden name="editPTG" id="editPTG"/>
    <s:hidden name="openPTG" id="openPTG"/>
    <s:hidden name="firstTimeOpen" id="firstTimeOpen"/>
    <%--taknak edit--%>

    <c:if test="${actionBean.edit}">
        <table width="90%" border="0" >
            <tr>
                <td colspan="4">
                    <div class="subtitle" style="text-transform: capitalize">
                        <fieldset class="aras1">
                            <legend>
                                <c:if test="${actionBean.urusanStatus eq 'PJBTR'}">
                                    DRAF RENCANA JAWATANKUASA BELAH BAHAGI
                                </c:if>
                            </legend>
                            <p align="center">
                                <b> </b>
                            </p>
                            <div class="content" align="center">

                                <table border="0" width="80%" cellspacing="10%" align="center">
                                    <tr><td id="tdLabel" ><b><font style="text-transform: capitalize">
                                                    <tr><td>
                                                            <p><b><span style="text-transform:uppercase"> ${actionBean.tajukMainDraf}</span></b></p>

                                                        </td></tr></font></b></td></tr>

                                    <tr><td>&nbsp;</td></tr>
                                </table>
                            </div>
                        </fieldset>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="1%"><b>1.</b></td>
                <td colspan="3"><div align="left"><b>TUJUAN</b></div></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>1.1</td>
                <td colspan="2">${actionBean.tajukTujuanDraf}</td>
            </tr>
            <tr>
                <td colspan="4">
                    &nbsp;
                </td>
            </tr>
            <tr>
                <td width="1%">2.</td>
                <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">LATAR BELAKANG</font></b></div></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>2.1</td>
                <td colspan="2"><b> Perihal Permohonan</b></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td width="1%">2.1.1</td>
                <td>${actionBean.tajukPerihalPermohonan}</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>2.2</td>
                <td colspan="2"><b> Perihal Pemohon</b></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td width="1%">2.2.1</td>
                <td>${actionBean.tajukPerihalPemohon}</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>2.3</td>
                <td colspan="2"><b> Perihal Tanah</b></td>
            </tr>
            <c:set var="i" value="1" />
            <c:set var="num" value="1"/>
            <c:forEach items="${actionBean.senaraiLaporanPerihalTanah}" var="line">
                <tr>
                    <td>&nbsp;</td>
                    <td valign="top">&nbsp;</td>
                    <td><c:out value="2.3.${num}"/></td>
                    <td>
                        ${line.kandungan}
                    </td>
                </tr>
                <c:set var="i" value="${i+1}" />
                <c:set var="num" value="${num+1}"/>
            </c:forEach>

            <tr>
                <td>&nbsp;</td>
                <td>2.4</td>
                <td colspan="2"><b> Butir-butir Tanah</b></td>
            </tr>

            <c:set var="i" value="1" />
            <c:set var="num" value="1"/>
            <c:forEach items="${actionBean.senaraiLaporanKandunganButirTanah}" var="line">
                <tr>
                    <td>&nbsp;</td>
                    <td valign="top">&nbsp;</td>
                    <td><c:out value="2.4.${num}"/></td>
                    <td>
                        ${line.kandungan}
                    </td>
                </tr>
                <c:set var="i" value="${i+1}" />
                <c:set var="num" value="${num+1}"/>
            </c:forEach>

            <tr>
                <td>&nbsp;</td>
                <td>2.5</td>
                <td colspan="2"><b> Lokasi Tanah</b></td>
            </tr>

            <c:set var="i" value="1" />
            <c:set var="num" value="1"/>
            <c:forEach items="${actionBean.senaraiLaporanKandunganLokasiTanah}" var="line">
                <tr>
                    <td>&nbsp;</td>
                    <td valign="top">&nbsp;</td>
                    <td><c:out value="2.5.${num}"/></td>
                    <td>
                        ${line.kandungan}
                    </td>
                </tr>
                <c:set var="i" value="${i+1}" />
                <c:set var="num" value="${num+1}"/>
            </c:forEach>
            <tr>
                <td colspan="4">
                    &nbsp;
                </td>
            </tr>
            <tr>
                <td width="1%">3.</td>
                <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">ULASAN JABATAN-JABATAN TEKNIKAL</font></b></div></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>3.1</td>
                <td colspan="2">${actionBean.tajukUlasanJabatan}</td>
            </tr>
            <c:set var="i" value="0" />
            <c:forEach items="${actionBean.senaraiUlasanJabatanTeknikal}" var="line">
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td width="1%"><font style="font-weight:bold;"><div align="right">${i+1}.</div></font></td>
                    <td><font style="font-weight:bold;">${line.namaAgensi}</font></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2">( Ruj. Surat ${line.noRujukan} bertarikh <s:format value="${line.tarikhRujukan}" formatPattern="dd/MM/yyyy"/> )</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>${line.ulasan}
                        <s:hidden name="idRujukanLuar${i}" value="${line.idRujukan}" id="idRujukanLuar$${i}"/>
                    </td>
                </tr>
                <c:set var="i" value="${i+1}" />
            </c:forEach>
            <tr>
                <td colspan="4">
                    &nbsp;
                </td>
            </tr>
            <tr>
                <td width="1%">4.</td>
                <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">ULASAN YB ADUN</font></b></div></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>4.1</td>
                <td colspan="2">${actionBean.tajukUlasanJKBB}</td>
            </tr>
            <c:set var="i" value="0" />
            <c:forEach items="${actionBean.senaraiUlasanJKBB}" var="ulasanJKBB">
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td width="1%"><font style="font-weight:bold;"><div align="right">${i+1}.</div></font></td>
                    <td><font style="font-weight:bold;">${ulasanJKBB.namaAgensi}</font></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td width="1%">&nbsp;</td>
                    <td${ulasanJKBB.ulasan}</td>
                </tr>
                <c:set var="i" value="${i+1}" />
            </c:forEach>
            <tr>
                <td colspan="4">
                    &nbsp;
                </td>
            </tr>
            <tr>
                <td width="1%">5.</td>
                <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">ASAS-ASAS PERTIMBANGAN</font></b></div></td>
            </tr>
            <c:set var="i" value="1" />
            <c:set var="num" value="1"/>
            <c:forEach items="${actionBean.senaraiLaporanAsasPertimbangan}" var="line">
                <tr>
                    <td>&nbsp;</td>
                    <td valign="top">&nbsp;</td>
                    <td><c:out value="5.${num}"/></td>
                    <td>
                        ${line.kandungan}
                    </td>
                </tr>
                <c:set var="i" value="${i+1}" />
                <c:set var="num" value="${num+1}"/>
            </c:forEach>


            <c:if test="${actionBean.openPTD}">
                <c:if test="${actionBean.viewOnlyPTD}">
                    <tr>
                        <td colspan="4">
                            &nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td width="1%">6.</td>
                        <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">PERAKUAN PENTADBIR TANAH DAERAH <span style="text-transform: uppercase">${actionBean.permohonan.cawangan.daerah.nama}</span></font></b></div></td>
                    </tr>

                    <c:if test="${!actionBean.viewOnlyPTDOnly}">
                        <%--
                      TEST PERAKUAN PTD
                        --%>
                        <c:set var="i" value="1" />
                        <c:set var="num" value="1"/>
                        <c:forEach items="${actionBean.senaraiLaporanKandunganPerakuanPTD}" var="line">
                            <tr>
                                <td>&nbsp;</td>
                                <td valign="top">&nbsp;</td>
                                <td><c:out value="6.${num}"/></td>
                                <td>
                                    <c:if test="${!actionBean.viewOnlyPTDOnly}">
                                        <s:textarea  id="kandungan6${i}"name="senaraiLaporanKandunganPerakuanPTD[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                                        <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                                    </c:if>
                                    <c:if test="${actionBean.viewOnlyPTDOnly}">
                                        ${line.kandungan}
                                    </c:if>
                                </td>
                            </tr>
                            <c:set var="i" value="${i+1}" />
                            <c:set var="num" value="${num+1}"/>
                        </c:forEach>
                        <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>
                                <c:if test="${!actionBean.viewOnlyPTDOnly}">
                                    <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('6',this.form)"></s:button>
                                    <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('6',${i-1},this.form)"></s:button>
                                </c:if>
                                <c:if test="${actionBean.viewOnlyPTDOnly}">
                                    &nbsp;
                                </c:if>
                            </td>
                        </tr>
                        <%--
                        END OF PERAKUAN PTD
                        --%>
                    </c:if>
                    <c:if test="${actionBean.viewOnlyPTDOnly}">
                        <c:set var="i" value="1" />
                        <c:set var="num" value="1"/>
                        <c:forEach items="${actionBean.senaraiLaporanKandunganPerakuanPTD}" var="line">
                            <tr>
                                <td>&nbsp;</td>
                                <td valign="top">&nbsp;</td>
                                <td><c:out value="6.${num}"/></td>
                                <td>
                                    ${line.kandungan}
                                </td>
                            </tr>
                            <c:set var="i" value="${i+1}" />
                            <c:set var="num" value="${num+1}"/>
                        </c:forEach>
                    </c:if>
                </c:if>
                <c:if test="${!actionBean.viewOnlyPTD}">
                    <tr>
                        <td colspan="4">
                            &nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td width="1%">6.</td>
                        <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">PERAKUAN PENTADBIR TANAH DAERAH ${actionBean.drafDaerah}</font></b></div></td>
                    </tr>

                    <c:set var="i" value="1" />
                    <c:set var="num" value="1"/>
                    <c:forEach items="${actionBean.senaraiLaporanKandunganPerakuanPTD}" var="line">
                        <tr>
                            <td>&nbsp;</td>
                            <td valign="top">&nbsp;</td>
                            <td><c:out value="6.${num}"/></td>
                            <td>
                                ${line.kandungan}
                            </td>
                        </tr>
                        <c:set var="i" value="${i+1}" />
                        <c:set var="num" value="${num+1}"/>
                    </c:forEach>


                </c:if>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2">Syarat - syarat adalah seperti yang berikut :<br><br>
                        <table width="90%" border="0">
                            <tr>
                                <td align="right" id="tdDisplay">&nbsp</td>
                                <td align="center">&nbsp;</td>
                                <td align="left" colspan="2">Jika permohonan diluluskan PT hendaklah menyampaikan terma kelulusan seperti yang berikut:</td>
                            </tr>
                            <tr>
                                <td align="right" id="tdDisplay">&nbsp</td>
                                <td align="center">&nbsp;</td>
                                <td align="left" colspan="2">a) Isipadu Disyorkan : ${actionBean.hakmilikPermohonan.luasDiluluskan}&nbsp; ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}&nbsp;</td>
                            </tr>
                            <tr>
                                <td align="right" id="tdDisplay">&nbsp</td>
                                <td align="center">&nbsp;</td>
                                <td align="left" colspan="2">b) Bayaran tahunan : RM ${actionBean.mohonTuntutKos.amaunTuntutan} &nbsp;.
                                </td>
                            </tr>
                            <tr>
                                <td align="right" id="tdDisplay">&nbsp</td>
                                <td align="center">&nbsp;</td>
                                <td align="left" colspan="2">c) Tempoh Pajakan : ${actionBean.hakmilikPermohonan.tempohPajakan}&nbsp;Tahun.
                                </td>
                            </tr>
                            <tr>
                                <td align="right" id="tdDisplay">&nbsp</td>
                                <td align="center">&nbsp;</td>
                                <td align="left" colspan="2">d) Kegunaan Tanah : ${actionBean.hakmilikPermohonan.hakmilik.maklumatAtasTanah}&nbsp;.
                                </td>
                            </tr>
                            <tr>
                                <td align="right" id="tdDisplay">&nbsp</td>
                                <td align="center">&nbsp;</td>
                                <td align="left" colspan="2">e) Syarat-syarat pajakan :
                                </td>
                            </tr>
                            <tr>
                                <td align="right" id="tdDisplay">&nbsp</td>
                                <td align="center">&nbsp;</td>
                                <td align="left" colspan="2">i) Tanah ini tidak boleh digunakan bagi apa-apa maksud selain daripada maksud yang dinyatakan di dalamnya.</td>
                            </tr>
                            <tr>
                                <td align="right" id="tdDisplay">&nbsp</td>
                                <td align="center">&nbsp;</td>
                                <td align="left" colspan="2">ii) Permit ini tidak boleh dipajak, dijual atau disewakan kepada sesiapa jua pun.</td>
                            </tr>
                            <tr>
                                <td align="right" id="tdDisplay">&nbsp</td>
                                <td align="center">&nbsp;</td>
                                <td align="left" colspan="2">iii) Bangunan kekal tidak dibenarkan didirikan di atas tanah ini.
                                </td>
                            </tr>
                            <tr>
                                <td align="right" id="tdDisplay">&nbsp</td>
                                <td align="center">&nbsp;</td>
                                <td align="left" colspan="2">iv)Permit ini akan dibatalkan dan tanah ini akan diambil balik tanpa apa-apa gantirugi apabila Kerajaan hendak menggunakan
                                    tanah ini kelak atau jika berlaku apa-apa perlanggaran syarat terhadap tanah ini.
                                </td>
                            </tr>
                        </table></td>
                </tr>
            </c:if>
            <c:if test="${actionBean.openPTG}">
                <c:if test="${actionBean.viewOnlyPTG}">
                    <tr>
                        <td colspan="4">
                            &nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td width="1%">7.</td>
                        <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">PERAKUAN PENGARAH TANAH DAN GALIAN</font></b></div></td>
                    </tr>

                    <c:if test="${actionBean.viewOnlyPTGOnly}">
                        <c:set var="i" value="1" />
                        <c:set var="num" value="1"/>
                        <c:forEach items="${actionBean.senaraiLaporanKandunganPerakuanPTG}" var="line">
                            <tr>
                                <td>&nbsp;</td>
                                <td valign="top">&nbsp;</td>
                                <td><c:out value="7.${num}"/></td>
                                <td>
                                    ${line.kandungan}
                                </td>
                            </tr>
                            <c:set var="i" value="${i+1}" />
                            <c:set var="num" value="${num+1}"/>
                        </c:forEach>
                    </c:if>
                    <c:if test="${!actionBean.viewOnlyPTGOnly}">
                        <%--
                         TEST PERAKUAN PTG
                        --%>
                        <c:set var="i" value="1" />
                        <c:set var="num" value="1"/>
                        <c:forEach items="${actionBean.senaraiLaporanKandunganPerakuanPTG}" var="line">
                            <tr>
                                <td>&nbsp;</td>
                                <td valign="top">&nbsp;</td>
                                <td><c:out value="7.${num}"/></td>
                                <td>
                                    <c:if test="${!actionBean.viewOnlyPTGOnly}">
                                        <s:textarea  id="kandungan7${i}"name="senaraiLaporanKandunganPerakuanPTG[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                                        <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                                    </c:if>
                                    <c:if test="${actionBean.viewOnlyPTGOnly}">
                                        ${line.kandungan}
                                    </c:if>
                                </td>
                            </tr>
                            <c:set var="i" value="${i+1}" />
                            <c:set var="num" value="${num+1}"/>
                        </c:forEach>
                        <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>
                                <c:if test="${!actionBean.viewOnlyPTGOnly}">
                                    <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('7',this.form)"></s:button>
                                    <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('7',${i-1},this.form)"></s:button>
                                </c:if>
                                <c:if test="${actionBean.viewOnlyPTGOnly}">
                                    &nbsp;
                                </c:if>
                            </td>
                        </tr>
                        <%--
                        END OF PERAKUAN PTG
                        --%>
                    </c:if>

                </c:if>
                <c:if test="${!actionBean.viewOnlyPTG}">
                    <tr>
                        <td colspan="4">
                            &nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td width="1%">7.</td>
                        <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">PERAKUAN PENGARAH TANAH DAN GALIAN</font></b></div></td>
                    </tr>

                    <c:set var="i" value="1" />
                    <c:set var="num" value="1"/>
                    <c:forEach items="${actionBean.senaraiLaporanKandunganPerakuanPTG}" var="line">
                        <tr>
                            <td>&nbsp;</td>
                            <td valign="top">&nbsp;</td>
                            <td><c:out value="7.${num}"/></td>
                            <td>
                                ${line.kandungan}
                            </td>
                        </tr>
                        <c:set var="i" value="${i+1}" />
                        <c:set var="num" value="${num+1}"/>
                    </c:forEach>
                </c:if>

            </c:if>

        </table>
    </c:if>

    <%--EDITABLE--%>
    <c:if test="${!actionBean.edit}">
        <table width="90%" border="0" >
            <tr>
                <td colspan="4">
                    <div class="subtitle" style="text-transform: capitalize">
                        <fieldset class="aras1">
                            <legend> </legend>
                            <c:if test="${actionBean.urusanStatus eq 'PJBTR'}">
                                DRAF RENCANA JAWATANKUASA BELAH BAHAGI
                            </c:if>
                            <p align="center">
                                <b> </b>
                            </p>
                            <div class="content" align="center">

                                <table border="0" width="80%" cellspacing="10%" align="center">
                                    <tr><td id="tdLabel" ><b><font style="text-transform: capitalize">
                                                    <tr><td>
                                                            <p><b><span style="text-transform:uppercase"> ${actionBean.tajukMainDraf}</span></b></p>

                                                        </td></tr></font></b></td></tr>

                                    <tr><td>&nbsp;</td></tr>
                                </table>
                            </div>
                        </fieldset>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="1%"><b>1.</b></td>
                <td colspan="3"><div align="left"><b>TUJUAN</b></div></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td width="1%"><b>1.1</b></td>
                <td colspan="2">
                    <div align="left">
                        <%--<s:textarea name="tajukTujuanDraf" class="normal_text" rows="5" cols="150"/>--%>
                        ${actionBean.tajukTujuanDraf}
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>
            <tr>
                <td><b>2.</b></td>
                <td colspan="3"><b>LATAR BELAKANG</b></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td width="1%"><b>2.1</b></td>
                <td colspan="2"><div align="left"><b> Perihal Permohonan</b></div></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td width="1%">2.1.1</td>
                <td>
                    <div align="left">
                        ${actionBean.tajukPerihalPermohonan}
                        <%--<s:textarea name="tajukPerihalPermohonan" class="normal_text" rows="5" cols="150"/>--%>
                    </div>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td width="1%"><b>2.2</b></td>
                <td colspan="2"><div align="left"><b>Perihal Pemohon</b></div></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>2.2.1</td>
                <td>
                    <div align="left">
                        <%--<s:textarea name="tajukPerihalPemohon" class="normal_text" rows="5" cols="150"/>--%>
                        ${actionBean.tajukPerihalPemohon}
                    </div>
                </td>
            </tr>

            <tr>
                <td>&nbsp;</td>
                <td width="1%"><b>2.3</b></td>
                <td colspan="2"><div align="left"><b> Perihal Tanah</b></div></td>
            </tr>
            <c:set var="i" value="1" />
            <c:set var="num" value="1"/>
            <c:forEach items="${actionBean.senaraiLaporanPerihalTanah}" var="line">
                <tr>
                    <td>&nbsp;</td>
                    <td valign="top">&nbsp;</td>
                    <td><c:out value="2.3.${num}"/></td>
                    <td>
                        <c:if test="${!actionBean.edit}">
                            <s:textarea  id="kandungan22${i}"name="senaraiLaporanPerihalTanah[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                            <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                        </c:if>
                        <c:if test="${actionBean.edit}">
                            ${line.kandungan}
                        </c:if>
                    </td>
                </tr>
                <c:set var="i" value="${i+1}" />
                <c:set var="num" value="${num+1}"/>
            </c:forEach>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>
                    <c:if test="${!actionBean.edit}">
                        <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('22',this.form)"></s:button>
                        <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('22',${i-1},this.form)"></s:button>
                    </c:if>
                    <c:if test="${actionBean.edit}">
                        &nbsp;
                    </c:if>
                </td>
            </tr>
            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>

            <tr>
                <td>&nbsp;</td>
                <td width="1%"><b>2.4</b></td>
                <td colspan="2"><div align="left"><b> Butir-butir Tanah </b></div></td>
            </tr>
            <c:set var="i" value="1" />
            <c:set var="num" value="1"/>
            <c:forEach items="${actionBean.senaraiLaporanKandunganButirTanah}" var="line">
                <tr>
                    <td>&nbsp;</td>
                    <td valign="top">&nbsp;</td>
                    <td><c:out value="2.4.${num}"/></td>
                    <td>
                        <c:if test="${!actionBean.edit}">
                            <s:textarea  id="kandungan24${i}"name="senaraiLaporanKandunganButirTanah[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                            <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                        </c:if>
                        <c:if test="${actionBean.edit}">
                            ${line.kandungan}
                        </c:if>
                    </td>
                </tr>
                <c:set var="i" value="${i+1}" />
                <c:set var="num" value="${num+1}"/>
            </c:forEach>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>
                    <c:if test="${!actionBean.edit}">
                        <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('24',this.form)"></s:button>
                        <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('24',${i-1},this.form)"></s:button>
                    </c:if>
                    <c:if test="${actionBean.edit}">
                        &nbsp;
                    </c:if>
                </td>
            </tr>
            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>

            <tr>
                <td>&nbsp;</td>
                <td width="1%"><b>2.5</b></td>
                <td colspan="2"><div align="left"><b> Lokasi Tanah</b></div></td>
            </tr>
            <c:set var="i" value="1" />
            <c:set var="num" value="1"/>
            <c:forEach items="${actionBean.senaraiLaporanKandunganLokasiTanah}" var="line">
                <tr>
                    <td>&nbsp;</td>
                    <td valign="top">&nbsp;</td>
                    <td><c:out value="2.5.${num}"/></td>
                    <td>
                        <c:if test="${!actionBean.edit}">
                            <s:textarea  id="kandungan25${i}"name="senaraiLaporanKandunganLokasiTanah[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                            <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                        </c:if>
                        <c:if test="${actionBean.edit}">
                            ${line.kandungan}
                        </c:if>
                    </td>
                </tr>
                <c:set var="i" value="${i+1}" />
                <c:set var="num" value="${num+1}"/>
            </c:forEach>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>
                    <c:if test="${!actionBean.edit}">
                        <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('25',this.form)"></s:button>
                        <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('25',${i-1},this.form)"></s:button>
                    </c:if>
                    <c:if test="${actionBean.edit}">
                        &nbsp;
                    </c:if>
                </td>
            </tr>
            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>
            <%--
            END TEST TAMBAH
            --%>
            <tr>
                <td><b>3.</b></td>
                <td colspan="3"><b>ULASAN JABATAN-JABATAN TEKNIKAL</b></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td width="1%"><b>3.1</b></td>
                <td colspan="2"><div align="left">${actionBean.tajukUlasanJabatan}</div></td>
            </tr>
            <c:set var="i" value="0" />
            <c:forEach items="${actionBean.senaraiUlasanJabatanTeknikal}" var="line">
                <c:if test="${!actionBean.edit}">
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td><div align="left">${i+1}.</div></td>
                        <td><font style="font-weight:bold;">${line.namaAgensi}</font></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td colspan="2">( Ruj. Surat ${line.noRujukan} bertarikh <s:format value="${line.tarikhRujukan}" formatPattern="dd/MM/yyyy"/> )</td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>${line.ulasan}
                            <s:hidden name="idRujukanLuar${i}" value="${line.idRujukan}" id="idRujukanLuar$${i}"/>
                        </td>
                    </tr>
                </c:if>
                <c:set var="i" value="${i+1}" />
            </c:forEach>
            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>
            <tr>
                <td><b>4.</b></td>
                <td colspan="3"><b>ULASAN YB ADUN</b></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td width="1%"><b>4.1</b></td>
                <td colspan="2"><div align="left">${actionBean.tajukUlasanJKBB}</div></td>
            </tr>
            <c:set var="i" value="0" />
            <c:forEach items="${actionBean.senaraiUlasanJKBB}" var="ulasanJKBB">
                <c:if test="${!actionBean.edit}">
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td><div align="left">${i+1}.</div></td>
                        <td><font style="font-weight:bold;">${ulasanJKBB.namaAgensi}</font></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td colspan="2">( Ruj. Surat ${ulasanJKBB.noRujukan} bertarikh <s:format value="${ulasanJKBB.tarikhRujukan}" formatPattern="dd/MM/yyyy"/> )</td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>${ulasanJKBB.ulasan}
                            <s:hidden name="idulasanJKBBRujukanLuar${i}" value="${ulasanJKBB.idRujukan}" id="idulasanJKBBRujukanLuar${i}"/>
                        </td>
                    </tr>
                </c:if>

                <c:set var="i" value="${i+1}" />
            </c:forEach>
            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>
            <tr>
                <td><b>5.</b></td>
                <td colspan="3"><b>ASAS-ASAS PERTIMBANGAN</b></td>
            </tr>
            <c:set var="i" value="1" />
            <c:set var="num" value="1"/>
            <c:forEach items="${actionBean.senaraiLaporanAsasPertimbangan}" var="line">
                <tr>
                    <td>&nbsp;</td>
                    <td valign="top">&nbsp;</td>
                    <td><c:out value="5.${num}"/></td>
                    <td>
                        <c:if test="${!actionBean.edit}">
                            <s:textarea  id="kandungan5${i}"name="senaraiLaporanAsasPertimbangan[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                            <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                        </c:if>
                        <c:if test="${actionBean.edit}">
                            ${line.kandungan}
                        </c:if>
                    </td>
                </tr>
                <c:set var="i" value="${i+1}" />
                <c:set var="num" value="${num+1}"/>
            </c:forEach>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>
                    <c:if test="${!actionBean.edit}">
                        <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('5',this.form)"></s:button>
                        <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('5',${i-1},this.form)"></s:button>
                    </c:if>
                    <c:if test="${actionBean.edit}">
                        &nbsp;
                    </c:if>
                </td>
            </tr>
            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>
            <%--
              END OF ASAS-ASAS PERTIMBANGAN
            --%>

            <c:if test="${actionBean.openPTD}">
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                    <td><b>6.</b></td>
                    <td colspan="3"><font style="text-transform: capitalize"><b>PERAKUAN PENTADBIR TANAH DAERAH <span style="text-transform: uppercase">${actionBean.permohonan.cawangan.daerah.nama}</span></b></font></td>
                </tr>
                <%--
                TEST PERAKUAN PTD
                --%>
                <c:set var="i" value="1" />
                <c:set var="num" value="1"/>
                <c:forEach items="${actionBean.senaraiLaporanKandunganPerakuanPTD}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                        <td valign="top">&nbsp;</td>
                        <td><c:out value="6.${num}"/></td>
                        <td>
                            <c:if test="${!actionBean.edit}">
                                <s:textarea  id="kandungan6${i}"name="senaraiLaporanKandunganPerakuanPTD[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                                <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                            </c:if>
                            <c:if test="${actionBean.edit}">
                                ${line.kandungan}
                            </c:if>
                        </td>
                    </tr>
                    <c:set var="i" value="${i+1}" />
                    <c:set var="num" value="${num+1}"/>
                </c:forEach>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>
                        <c:if test="${!actionBean.edit}">
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('6',this.form)"></s:button>
                            <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('6',${i-1},this.form)"></s:button>
                        </c:if>
                        <c:if test="${actionBean.edit}">
                            &nbsp;
                        </c:if>
                    </td>
                </tr> 
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2">Syarat - syarat adalah seperti yang berikut :<br><br>
                        <table width="90%" border="0">
                            <tr>
                                <td align="right" id="tdDisplay">&nbsp</td>
                                <td align="center">&nbsp;</td>
                                <td align="left" colspan="2">Jika permohonan diluluskan PT hendaklah menyampaikan terma kelulusan seperti yang berikut:</td>
                            </tr>
                            <tr>
                                <td align="right" id="tdDisplay">&nbsp</td>
                                <td align="center">&nbsp;</td>
                                <td align="left" colspan="2">a) Keluasan yang diluluskan : ${actionBean.hakmilikPermohonan.luasDiluluskan}&nbsp; ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}&nbsp;</td>
                            </tr>
                            <tr>
                                <td align="right" id="tdDisplay">&nbsp</td>
                                <td align="center">&nbsp;</td>
                                <td align="left" colspan="2">b) Bayaran tahunan : RM ${actionBean.mohonTuntutKos.amaunTuntutan} &nbsp;.
                                </td>
                            </tr>
                            <tr>
                                <td align="right" id="tdDisplay">&nbsp</td>
                                <td align="center">&nbsp;</td>
                                <td align="left" colspan="2">c) Tempoh Pajakan : ${actionBean.hakmilikPermohonan.tempohPajakan}&nbsp;Tahun.
                                </td>
                            </tr>
                            <tr>
                                <td align="right" id="tdDisplay">&nbsp</td>
                                <td align="center">&nbsp;</td>
                                <td align="left" colspan="2">d) Kegunaan Tanah : ${actionBean.hakmilikPermohonan.hakmilik.maklumatAtasTanah}&nbsp;.
                                </td>
                            </tr>
                            <tr>
                                <td align="right" id="tdDisplay">&nbsp</td>
                                <td align="center">&nbsp;</td>
                                <td align="left" colspan="2">e) Syarat-syarat pajakan :
                                </td>
                            </tr>
                            <tr>
                                <td align="right" id="tdDisplay">&nbsp</td>
                                <td align="center">&nbsp;</td>
                                <td align="left" colspan="2">i) Tanah ini tidak boleh digunakan bagi apa-apa maksud selain daripada maksud yang dinyatakan di dalamnya.</td>
                            </tr>
                            <tr>
                                <td align="right" id="tdDisplay">&nbsp</td>
                                <td align="center">&nbsp;</td>
                                <td align="left" colspan="2">ii) Permit ini tidak boleh dipajak, dijual atau disewakan kepada sesiapa jua pun.</td>
                            </tr>
                            <tr>
                                <td align="right" id="tdDisplay">&nbsp</td>
                                <td align="center">&nbsp;</td>
                                <td align="left" colspan="2">iii) Bangunan kekal tidak dibenarkan didirikan di atas tanah ini.
                                </td>
                            </tr>
                            <tr>
                                <td align="right" id="tdDisplay">&nbsp</td>
                                <td align="center">&nbsp;</td>
                                <td align="left" colspan="2">iv)Permit ini akan dibatalkan dan tanah ini akan diambil balik tanpa apa-apa gantirugi apabila Kerajaan hendak menggunakan
                                    tanah ini kelak atau jika berlaku apa-apa perlanggaran syarat terhadap tanah ini.
                                </td>
                            </tr>
                        </table></td>
                </tr>
                <%--
                END OF PERAKUAN PTD
                --%>

            </c:if>
            <c:if test="${actionBean.openPTG}">
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                    <td><b>7.</b></td>
                    <td colspan="3"><font style="text-transform: capitalize"><b>PERAKUAN PENGARAH TANAH DAN GALIAN</b></font></td>

                    <%--
                    TEST PERAKUAN PTG
                    --%>
                    <c:set var="i" value="1" />
                    <c:set var="num" value="1"/>
                    <c:forEach items="${actionBean.senaraiLaporanKandunganPerakuanPTG}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                        <td valign="top">&nbsp;</td>
                        <td><c:out value="7.${num}"/></td>
                        <td>
                            <c:if test="${!actionBean.edit}">
                                <s:textarea  id="kandungan7${i}"name="senaraiLaporanKandunganPerakuanPTG[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                                <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                            </c:if>
                            <c:if test="${actionBean.edit}">
                                ${line.kandungan}
                            </c:if>
                        </td>
                    </tr>
                    <c:set var="i" value="${i+1}" />
                    <c:set var="num" value="${num+1}"/>
                </c:forEach>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>
                        <c:if test="${!actionBean.edit}">
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('7',this.form)"></s:button>
                            <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('7',${i-1},this.form)"></s:button>
                        </c:if>
                        <c:if test="${actionBean.edit}">
                            &nbsp;
                        </c:if>
                    </td>
                </tr>
                <%--
                END OF PERAKUAN PTG
                --%>

            </c:if>
        </table>

    </c:if>

</s:form>
