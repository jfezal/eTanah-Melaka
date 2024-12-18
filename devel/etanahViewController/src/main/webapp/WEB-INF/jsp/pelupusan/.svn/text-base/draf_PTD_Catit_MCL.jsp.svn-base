<%-- 
    Document   : draf_PTD_MCL
    Created on : Jul 14, 2010, 11:11:38 AM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    function validate_required() {

        var jrkTanah = document.draf.jarak;
        if (jrkTanah.value == null || jrkTanah.value == "")
        {
            alert("Sila masukkan jarak tanah");
            jrkTanah.onFocus();
            return false;
        } else
        {
            return true;
        }

    }

    function simpan(f) {
        
        var q = $(f).formSerialize();
        var kand;
        kand = document.getElementById("huraianpptd3").value;
        $.post('${pageContext.request.contextPath}/pelupusan/draf_pertimbangan_ptd_catit_mcl?simpanKandungan2&kand=' + kand,q,
                function (data) {
                    $('#page_div').html(data);
                }, 'html');
    }

    function menyimpan(index, i, f)
    {
        var kand;
        if (index == 3)
            kand = document.getElementById("kandungan3" + i).value;
        else if (index == 1)
            kand = document.getElementById("kandungan1" + i).value;
        else if (index == 2)
            kand = document.getElementById("kandungan2" + i).value;
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pelupusan/draf_pertimbangan_ptd_catit_mcl?simpanKandungan&index=' + index + '&kandungan=' + kand, q,
                function (data) {
                    $('#page_div').html(data);
                }, 'html');



    }
    function addRow(index, f)
    {
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pelupusan/draf_pertimbangan_ptd_catit_mcl?tambahRow&index=' + index, q,
                function (data) {
                    $('#page_div').html(data);
                }, 'html');
    }
    function deleteRow(idKandungan, f)
    {
        if (confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/draf_pertimbangan_ptd_catit_mcl?deleteRow&idKandungan=' + idKandungan, q,
                    function (data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.pelupusan.DrafPertimbanganPTDCatitmclActionBean" name="draf">
    <s:messages/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend align="center">
                <u><b><h5>KERTAS UNTUK PERTIMBANGAN PENTADBIR TANAH ${actionBean.daerah} BAGI PERMOHONAN UNTUK MENJADIKAN TANAH ADAT MELAKA (MCL)</h5></b></u>
            </legend>
            <br>
            <table align="center">
                <tr>
                    <td><h5>i.</h5></td>
                    <td><h5>KEBENARAN UNTUK MENCATIT MCL</h5></td>
                </tr>
                <tr>
                    <td><h5>ii.</h5></td>
                    <td><h5>KEBENARAN UNTUK SERAH DAN MOHON SEMULA</h5></td>
                </tr>
            </table>
            <br>
            <!--            <center>
            <c:if test="${actionBean.viewOnly}">
                ${actionBean.tajuk}

                <%-- <s:textarea name="tajuk" rows="2" cols="160"> </s:textarea> --%>

            </c:if>
            <c:if test="${!actionBean.viewOnly}">
                ${actionBean.tajuk}

                <s:textarea name="tajuk" rows="2" cols="160"></s:textarea>

            </c:if>
            </center> -->

            <br>

            <hr>

            <br>

            <b>1.</b> &nbsp; &nbsp; &nbsp; &nbsp; <u><b>TUJUAN</b></u> <br/><br/>
                <%--<p>&nbsp; &nbsp; &nbsp; 1.1 &nbsp; &nbsp;
                <c:if test="${actionBean.viewOnly}">
                    ${actionBean.tujuan}
                </c:if>
                <c:if test="${!actionBean.viewOnly}">
                    <s:textarea name="tujuan" rows="3" cols="160" class="normal_text"></s:textarea>
                </c:if></p>--%>

            <p>
                <c:set var="i" value="1" />
                <c:set var="num" value="1"/>
                <c:forEach items="${actionBean.senaraiKandunganTujuan}" var="line">
                    &nbsp;<c:out value="1.${num}"/> &nbsp; &nbsp;
                    <c:if test="${actionBean.viewOnly}">
                        <%--<s:textarea  id="kandungan1${i}"name="senaraiKandunganTujuan[${i-1}].kandungan" cols="150"  rows="5" class="normal_text" />
                        --%>
                        ${line.kandungan}
                    </c:if>
                    <c:if test="${!actionBean.viewOnly}">
                        <s:textarea  id="kandungan1${i}"name="senaraiKandunganTujuan[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                        <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                    </c:if>
                    <br/>

                    <c:set var="i" value="${i+1}" />
                    <c:set var="num" value="${num+1}"/>
                </c:forEach>
                <c:if test="${!actionBean.viewOnly}">
                    &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('1',this.form)"></s:button>
                    <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('1',${i-1},this.form)"></s:button></c:if></p>
                    <br><br>

                    <b>2.</b> &nbsp; &nbsp; &nbsp; &nbsp; <u><b>LATAR BELAKANG</b></u> <br><br>
                    <p>&nbsp;2.1 &nbsp; &nbsp; <u><b>Perihal Permohonan</b></u> <br></p>
                    <%--&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                    <c:if test="${actionBean.viewOnly}">
                        ${actionBean.perihalPermohonan}
                    </c:if>
                    <c:if test="${!actionBean.viewOnly}">
                        <s:textarea name="perihalPermohonan" rows="3" cols="160" class="normal_text"></s:textarea>
                    </c:if></p>--%>
            <p>
                <c:set var="i" value="1" />
                <c:set var="num" value="1"/>
                <c:forEach items="${actionBean.senaraiKandunganPerihal}" var="line">
                <p><c:out value="2.1.${num}"/>
                    <c:if test="${actionBean.viewOnly}">
                        <%--
                        <s:textarea  id="kandungan2${i}"name="senaraiKandunganPerihal[${i-1}].kandungan" cols="150"  rows="5" class="normal_text" readonly="true"/>
                        --%>
                        ${line.kandungan}</p>
                    </c:if>
                    <c:if test="${!actionBean.viewOnly}">

                    <s:textarea  id="kandungan2${i}"name="senaraiKandunganPerihal[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                    <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                </c:if>
                <br/>

                <c:set var="i" value="${i+1}" />
                <c:set var="num" value="${num+1}"/>
            </c:forEach>
            <c:if test="${!actionBean.viewOnly}">
                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('2',this.form)"></s:button>
                <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('2',${i-1},this.form)"></s:button></p> </c:if>
                    <br><br>
                    <p>&nbsp; &nbsp; &nbsp; 2.2 &nbsp; &nbsp; <u><b>Perihal Tanah</b></u> <br><br></p>
                    <p style="font-size:15px;">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  2.2.1
                        Butir-butir tanah adalah seperti berikut :-

                    </p>
                    <table align="center" class="content">
                        <tr><td colspan="3"></td></tr>
                        <tr>
                            <td>Jenis dan No.Hakmilik </td>
                            <td>:</td>
                            <td><c:if test="${actionBean.hakmilik.noHakmilik ne null}">&nbsp;${actionBean.hakmilik.kodHakmilik.kod} &nbsp; ${actionBean.hakmilik.noHakmilik}</c:if>
                        <c:if test="${actionBean.hakmilik.noHakmilik eq null}">&nbsp;Tiada &nbsp;</c:if>
                        </td>
                    </tr>
                    <tr><td colspan="3"></td></tr>
                    <tr>
                        <td>No. Lot/PT </td>
                        <td>:</td>
                        <td><c:if test="${actionBean.hakmilik.noLot ne null}">&nbsp;${actionBean.hakmilik.lot.nama} &nbsp; ${actionBean.hakmilik.noLot}</c:if>
                        <c:if test="${actionBean.hakmilik.noLot eq null}">&nbsp;Tiada &nbsp;</c:if>
                        </td>
                    </tr>
                    <tr><td colspan="3"></td></tr>
                    <tr>
                        <td>Mukim/Bdr </td>
                        <td>:</td>
                        <td><c:if test="${actionBean.hakmilik.bandarPekanMukim.nama ne null}">&nbsp;${actionBean.hakmilik.bandarPekanMukim.nama}</c:if>
                        <c:if test="${actionBean.hakmilik.bandarPekanMukim.nama eq null}">&nbsp;Tiada &nbsp;</c:if>

                        </td>
                    </tr>
                    <tr><td colspan="3"></td></tr>
                    <tr>
                        <td>Daerah </td>
                        <td>:</td>
                        <td><c:if test="${actionBean.hakmilik.daerah.nama ne null}">&nbsp;${actionBean.hakmilik.daerah.nama}</c:if>
                        <c:if test="${actionBean.hakmilik.daerah.nama eq null}">&nbsp;Tiada &nbsp;</c:if>
                        </td>
                    </tr>
                    <tr><td colspan="3"></td></tr>
                    <tr>
                        <td>Hasil </td>
                        <td>:</td>
                        <td><c:if test="${actionBean.hakmilik.cukaiSebenar ne null}">&nbsp;RM <s:format formatPattern="###,###,###.00" value="${actionBean.hakmilik.cukaiSebenar}"/> </c:if>
                        <c:if test="${actionBean.hakmilik.cukaiSebenar eq null}">&nbsp;Tiada &nbsp;</c:if> </td>
                    </tr>
                    <tr><td colspan="3"></td></tr>
                    <tr>
                        <td>Tempoh Pajakan </td>
                        <td>:</td>
                        <td><c:if test="${actionBean.hakmilik.tempohPegangan ne null}">&nbsp;${actionBean.hakmilik.tempohPegangan} Tahun.</c:if>
                        <c:if test="${actionBean.hakmilik.tempohPegangan eq null}">&nbsp;Tiada &nbsp;</c:if> </td>
                    </tr>
                    <tr><td colspan="3"></td></tr>
                    <tr>
                        <td>Tarikh Tamat Tempoh </td>
                        <td>:</td>
                        <td><c:if test="${actionBean.hakmilik.tarikhLuput ne null}">&nbsp;<s:format formatPattern="dd-MM-yyyy" value="${actionBean.hakmilik.tarikhLuput}"/></c:if>
                        <c:if test="${actionBean.hakmilik.tarikhLuput eq null}">&nbsp;Tiada &nbsp;</c:if> </td>
                    </tr>
                    <tr><td colspan="3"></td></tr>
                    <tr>
                        <td>Keluasan </td>
                        <td>:</td>
                        <td><c:if test="${actionBean.hakmilik.luas ne null}">&nbsp;${actionBean.hakmilik.luas} ${actionBean.hakmilik.kodUnitLuas.nama}</c:if>
                        <c:if test="${actionBean.hakmilik.luas eq null}">&nbsp;Tiada &nbsp;</c:if> </td>
                    </tr>
                    <tr><td colspan="3"></td></tr>
                    <tr>
                        <td>Penjenisan </td>
                        <td>:</td>
                        <td><c:if test="${actionBean.hakmilik.kategoriTanah.nama ne null}">&nbsp;${actionBean.hakmilik.kategoriTanah.nama}</c:if>
                        <c:if test="${actionBean.hakmilik.kategoriTanah.nama eq null}">&nbsp;Tiada &nbsp;</c:if> </td>
                    </tr>
                    <tr><td colspan="3"></td></tr>
                    <tr>
                        <td valign="top">Syarat Nyata</td>
                        <td valign="top">:</td>
                        <td><%--<c:if test="${actionBean.hakmilik.syaratNyata.syarat ne null}">&nbsp;<s:textarea name="syarat" readonly="true" rows="3" cols="120" class="normal_text" value="${actionBean.hakmilik.syaratNyata.syarat}"/></c:if>--%>
                        <c:if test="${actionBean.hakmilik.syaratNyata.syarat ne null}">&nbsp;${actionBean.hakmilik.syaratNyata.syarat}</c:if>
                        <c:if test="${actionBean.hakmilik.syaratNyata.syarat eq null}">&nbsp;Tiada</c:if> </td>
                    </tr>
                    <tr><td colspan="3"></td></tr>
                    <tr>
                        <td valign="top">Sekatan Kepentingan</td>
                        <td valign="top">:</td>
                        <td><%--<c:if test="${actionBean.hakmilikPermohonan.sekatanKepentingan.sekatan ne null}">&nbsp;<s:textarea name="sekatan" rows="3" cols="120" class="normal_text" readonly="true" value="${actionBean.hakmilikPermohonan.sekatanKepentingan.sekatan}"/></c:if>
                            <c:if test="${actionBean.hakmilikPermohonan.sekatanKepentingan.sekatan eq null}">&nbsp;<s:textarea name="sekatan" rows="3" cols="120" class="normal_text" readonly="true" value="Tiada"/></c:if> </td>--%>

                        <c:if test="${actionBean.hakmilikPermohonan.sekatanKepentingan.sekatan ne null}">&nbsp; ${actionBean.hakmilik.sekatanKepentingan.sekatan} </c:if>
                        <%--Tanah yang diberimilik ini adalah tertakluk kepada Peruntukan Bahagian VIII Akta Kanun Tanah Negara (Hakmilik Pulau Pinang dan Melaka) 1963--%>

                        <c:if test="${actionBean.hakmilikPermohonan.sekatanKepentingan.sekatan eq null}">&nbsp;Tiada/></c:if>
                        </td>
                    </tr>
                    <tr><td colspan="3"></td></tr>
                </table>
                <br><br>

                <p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp 2.2.2 Tanah-tanah yang bersempadan dengan tanah ini ialah :-</p>
                <br><br>
                <div id ="lotsempadan">
                    <div class="content" align="center">
                        <table class="tablecloth">
                            <tr>
                                <th>&nbsp;</th><th>Id. Hakmilik / No. Lot </th><th>Kegunaan Tanah</th><th>Keadaan Tanah</th><th>Catatan</th><th>Milik Kerajaan</th>
                                <%--UTARA--%>
                                <c:if test="${!empty actionBean.disLaporanTanahSempadan.listLaporTanahSpdnU}">
                                    <c:set var="i" value="1" />
                            <tr>
                                <th rowspan="${actionBean.disLaporanTanahSempadan.uSize}">
                                    Utara
                                </th>
                                <c:forEach items="${actionBean.disLaporanTanahSempadan.listLaporTanahSpdnU}" var="line">

                                    <td>
                                        <c:if test="${line.laporanTanahSmpdn.hakmilik.noLot ne null}">
                                            ${line.laporanTanahSmpdn.noLot}
                                            <s:hidden  id="kandunganSpdnUHM${i}" name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.hakmilik.noLot" />
                                        </c:if>
                                        <c:if test="${line.laporanTanahSmpdn.noLot eq null}">
                                            ${line.laporanTanahSmpdn.hakmilik.idHakmilik}
                                            <s:hidden  id="kandunganSpdnUHM${i}" name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik" />
                                        </c:if>

                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.guna}
                                        <s:hidden   id="kandunganSpdnUKEG${i}" name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.guna" />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.keadaanTanah}
                                        <s:hidden   id="kandunganSpdnUKEA${i}" name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.keadaanTanah" />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.catatan}
                                        <s:hidden   id="kandunganSpdnUCAT${i}" name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.catatan" />
                                    </td>
                                    <td>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'T'}">Milik</c:if>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'N'}">--</c:if>
                                        <s:hidden  name="disLaporanTanahSempadan.listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.milikKerajaan" id="kandunganSpdnUMK${i}"/>

                                    </td>
                                </tr>

                                <c:set var="i" value="${i+1}" />        
                            </c:forEach>
                        </c:if>

                        <%--END OF UTARA--%>                             
                        <%--SELATAN--%>
                        <c:if test="${!empty actionBean.disLaporanTanahSempadan.listLaporTanahSpdnS}">
                            <c:set var="i" value="1" />
                            <tr>
                                <th rowspan="${actionBean.disLaporanTanahSempadan.sSize}">
                                    Selatan
                                </th>
                                <c:forEach items="${actionBean.disLaporanTanahSempadan.listLaporTanahSpdnS}" var="line">

                                    <td>
                                        <c:if test="${line.laporanTanahSmpdn.noLot ne null}">
                                            ${line.laporanTanahSmpdn.noLot}
                                            <s:hidden  id="kandunganSpdnSHM${i}" name="listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.hakmilik.noLot" />
                                        </c:if>
                                        <c:if test="${line.laporanTanahSmpdn.noLot eq null}">
                                            ${line.laporanTanahSmpdn.hakmilik.idHakmilik}
                                            <s:hidden  id="kandunganSpdnSHM${i}" name="listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik" />
                                        </c:if>
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.guna}
                                        <s:hidden  id="kandunganSpdnSKEG${i}" name="listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.guna" />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.keadaanTanah}
                                        <s:hidden  id="kandunganSpdnSKEA${i}" name="listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.keadaanTanah" />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.catatan}
                                        <s:hidden  id="kandunganSpdnSCAT${i}" name="listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.catatan" />
                                    </td>
                                    <td>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'T'}">Milik</c:if>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'N'}">--</c:if>
                                        <s:hidden name="disLaporanTanahSempadan.listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.milikKerajaan" id="kandunganSpdnSMK${i}"/>

                                    </td>
                                </tr>

                                <c:set var="i" value="${i+1}" />        
                            </c:forEach>
                        </c:if>

                        <%--END OF SELATAN--%>
                        <%--TIMUR--%>
                        <c:if test="${!empty actionBean.disLaporanTanahSempadan.listLaporTanahSpdnT}">
                            <c:set var="i" value="1" />
                            <tr>
                                <th rowspan="${actionBean.disLaporanTanahSempadan.tSize}">
                                    Timur
                                </th>
                                <c:forEach items="${actionBean.disLaporanTanahSempadan.listLaporTanahSpdnT}" var="line">

                                    <td>
                                        <c:if test="${line.laporanTanahSmpdn.noLot ne null}">
                                            ${line.laporanTanahSmpdn.noLot}
                                            <s:hidden  id="kandunganSpdnTHM${i}" name="listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.hakmilik.noLot" />
                                        </c:if>
                                        <c:if test="${line.laporanTanahSmpdn.noLot eq null}">
                                            ${line.laporanTanahSmpdn.hakmilik.idHakmilik}
                                            <s:hidden  id="kandunganSpdnTHM${i}" name="listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik" />
                                        </c:if>
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.guna}
                                        <s:hidden  id="kandunganSpdnTKEG${i}" name="listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.guna"  />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.keadaanTanah}
                                        <s:hidden  id="kandunganSpdnTKEA${i}" name="listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.keadaanTanah"  />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.catatan}
                                        <s:hidden  id="kandunganSpdnTCAT${i}" name="listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.catatan"  />
                                    </td>
                                    <td>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'T'}">Milik</c:if>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'N'}">--</c:if>
                                        <s:hidden name="disLaporanTanahSempadan.listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.milikKerajaan" id="kandunganSpdnTMK${i}" />

                                    </td>
                                </tr>

                                <c:set var="i" value="${i+1}" />        
                            </c:forEach>

                        </c:if>

                        <%--END OF TIMUR--%>
                        <%--BARAT--%>
                        <c:if test="${!empty actionBean.disLaporanTanahSempadan.listLaporTanahSpdnB}">
                            <c:set var="i" value="1" />
                            <tr>
                                <th rowspan="${actionBean.disLaporanTanahSempadan.bSize}">
                                    Barat
                                </th>
                                <c:forEach items="${actionBean.disLaporanTanahSempadan.listLaporTanahSpdnB}" var="line">

                                    <td>
                                        <c:if test="${line.laporanTanahSmpdn.noLot ne null}">
                                            ${line.laporanTanahSmpdn.noLot}
                                            <s:hidden  id="kandunganSpdnBHM${i}" name="listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.hakmilik.noLot" />
                                        </c:if>
                                        <c:if test="${line.laporanTanahSmpdn.noLot eq null}">
                                            ${line.laporanTanahSmpdn.hakmilik.idHakmilik}
                                            <s:hidden  id="kandunganSpdnBHM${i}" name="listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik" />
                                        </c:if>
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.guna}
                                        <s:hidden  id="kandunganSpdnBKEG${i}" name="listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.guna"  />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.keadaanTanah}
                                        <s:hidden  id="kandunganSpdnBKEA${i}" name="listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.keadaanTanah"  />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.catatan}
                                        <s:hidden  id="kandunganSpdnBCAT${i}" name="listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.catatan"  />
                                    </td>
                                    <td>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'T'}">Milik</c:if>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'N'}">--</c:if>
                                        <s:hidden name="disLaporanTanahSempadan.listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.milikKerajaan" id="kandunganSpdnBMK${i}" />

                                    </td>
                                </tr>

                                <c:set var="i" value="${i+1}" />        
                            </c:forEach>

                        </c:if>

                        <%--END OF BARAT--%>

                    </table>
                </div>
            </div>
            <br><br>

            <b>3.</b> &nbsp; &nbsp; &nbsp; &nbsp; <u><b>PERAKUAN PENOLONG PENTADBIR TANAH ${actionBean.daerah}</b></u> <br><br>
            <p>Pentadbiran ini telah meneliti permohonan ini dan memperakukan seperti berikut :- <br>
                <%--<br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; --%>
                <c:set var="i" value="1" />
                <c:set var="num" value="1"/>
                <c:forEach items="${actionBean.senaraiKandunganPenolongPTD}" var="line">
                    &nbsp; &nbsp; &nbsp; &nbsp; <p><c:out value="3.${num}"/> &nbsp; &nbsp;
                    <c:if test="${actionBean.viewOnly}">
                        <%--<s:textarea  id="kandungan3${i}"name="senaraiKandunganPenolongPTD[${i-1}].kandungan" cols="150"  rows="5" class="normal_text" readonly="true"/>--%>
                            ${line.kandungan}</p>
                    </c:if>
                    <c:if test="${!actionBean.viewOnly}">

                    <s:textarea  id="kandungan3${i}"name="senaraiKandunganPenolongPTD[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                    <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                </c:if>
                <br/>

                <c:set var="i" value="${i+1}" />
                <c:set var="num" value="${num+1}"/>
            </c:forEach>
            <c:if test="${!actionBean.viewOnly}">
                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('3',this.form)"></s:button>
                <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('3',${i-1},this.form)"></s:button><br/></c:if>
            <c:set var="test" value="${num}"/>
            <br/>

            <p>&nbsp;<c:out value="3.${test}"/> &nbsp; &nbsp;
                <c:if test="${!actionBean.viewOnly}">
                    <s:textarea name="huraianpptd3" id="huraianpptd3" rows="3" cols="150" class="normal_text"></s:textarea>
                    <s:button name="simpanKandungan2" value="Simpan" class="btn" onclick="simpan(this.form);"></s:button>
                </c:if>
                <c:if test="${actionBean.viewOnly}">
                    ${actionBean.huraianpptd3}
                    <%--<s:textarea name="huraianpptd3" rows="3" cols="150" class="normal_text" readonly="true"></s:textarea> --%>
                </c:if>
            </p>
            <br><br>
            <%--        <br><br>

                 <b>4.</b> &nbsp; &nbsp; &nbsp; &nbsp; <u><b>KEPUTUSAN</b></u> <br><br>
         <p>&nbsp; &nbsp; &nbsp; &nbsp; 4.1 &nbsp; &nbsp; Pentadbir Tanah ${actionBean.hakmilik.daerah.nama} adalah diminta membuat keputusan samada bersetuju atau sebaliknya dengan perakuan yang dibuat seperti di perenggan 3.1 di atas. :- <br><br>
         <br><br> --%>
            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Syarat - syarat Hakmilik :<br>
            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<p><table align="center">
                <tr>
                    <td> a. Jenis Hakmilik</td>
                    <td>:</td>
                    <td>&nbsp; Tanah Adat Melaka (MCL)</td>
                </tr>
                <tr><td colspan ="3"></td></tr>
                <tr>
                    <td> b. Tempoh</td>
                    <td>:</td>
                    <td>&nbsp; Kekal</td>
                </tr>
                <tr><td colspan ="3"></td></tr>
                <tr>
                    <td> c. Premium</td>
                    <td>:</td>
                    <td><s:select name="premium" value="${actionBean.hakmilikPermohonan.nilaianJpph}">
                            <s:option value="">Sila Pilih..</s:option>
                            <s:option value="1">Nominal - RM100</s:option>
                            <s:option value="3">Nominal Bangunan Kediaman - RM1000</s:option>
                            <s:option value="2">UnNominal - 1/2(1/4(NP - (NP x BTP / 100)))</s:option>
                        </s:select></td>
                        <%--<td>&nbsp; 1/2(1/4(NP - (NP x BTP / 100)))</td>--%>
                </tr>
                <tr><td colspan ="3"></td></tr>
                <tr >
                    <td><s:button name="simpanPremium" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/></td>
                </tr>
                <tr><td colspan ="3"></td></tr>
                <tr>
                    <td> d. Cukai</td>
                    <td>:</td>
                    <td>
                        <c:if test="${actionBean.cukaiSebenar ne null}">&nbsp; RM &nbsp;<s:format formatPattern="###,###,###.00" value="${actionBean.cukaiSebenar}"/></c:if>
                        <c:if test="${actionBean.cukaiSebenar eq null}">&nbsp; Tiada &nbsp;</c:if></td>
                    </tr>
                    <tr><td colspan ="3"></td></tr>
                    <tr>
                        <td> e. Bayaran Hakmilik</td>
                        <td>:</td>
                        <td><c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'YQ'}">&nbsp; Hakmilik Sementara : RM50.00</c:if></td>
                        <%--<c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'YT'}">&nbsp; Hakmilik Kekal : RM30.00</c:if>--%>
                    </tr>
                    <tr>
                        <td> </td>
                        <td> </td>
                        <td><c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'YQ'}">&nbsp; Hakmilik Kekal : RM50.00</c:if></td>
                        <%--<c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'YT'}">&nbsp; Hakmilik Kekal : RM30.00</c:if>--%>
                    </tr>
                    <tr><td colspan ="3"></td></tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td><c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'YT'}">&nbsp; Hakmilik Kekal : RM50.00</c:if>
                        </td>
                    </tr>
                    <tr><td colspan ="3"></td></tr>
                    <tr>
                        <td valign ="top"> f. Syarat Nyata</td>
                        <td valign ="top">:</td>
                        <td><%--<c:if test="${actionBean.hakmilik.syaratNyata.syarat ne null}">&nbsp; <s:textarea name="syarat" readonly="true" rows="3" cols="120" class="normal_text" value="${actionBean.hakmilik.syaratNyata.syarat}"/></c:if>
                            <c:if test="${actionBean.hakmilik.syaratNyata.syarat eq null}">&nbsp; <s:textarea name="syarat" readonly="true" rows="3" cols="120" class="normal_text" value="Tiada"/></c:if>--%>
                        <c:if test="${actionBean.hakmilik.syaratNyata.syarat ne null}">&nbsp; ${actionBean.hakmilik.syaratNyata.syarat}</c:if>
                        <c:if test="${actionBean.hakmilik.syaratNyata.syarat eq null}">&nbsp; Tiada</c:if>
                        </td>
                    </tr>
                    <tr><td colspan ="3"></td></tr>
                    <tr>
                        <td> g. Sekatan Kepentingan</td>
                        <td>:</td>
                        <td>&nbsp; ${actionBean.hakmilik.sekatanKepentingan.sekatan} <br> 
                        &nbsp; </td>
                        <!--&nbsp; Tanah yang diberimilik ini adalah tertakluk kepada Peruntukan Bahagian VIII Akta Kanun Tanah Negara (Hakmilik Pulau Pinang dan Melaka) 1963</td>-->
                        <%--
                        <td><s:textarea name="sekatan" readonly="true" rows="3" cols="120" class="normal_text" value="Tiada"/></td>
                        --%>
                </tr>
                <tr><td colspan ="3"></td></tr>
                <tr>
                    <td> h. Syarat Am</td>
                    <td>:</td>       
                    <td>&nbsp; Tanah Adat Melaka</td>
                </tr>
                <tr><td colspan ="3"></td></tr>
                <tr>
                    <td> i. Penjenisan</td>
                    <td>:</td>
                    <td><c:if test="${actionBean.hakmilik.kategoriTanah.nama ne null}">&nbsp; ${actionBean.hakmilik.kategoriTanah.nama}</c:if>
                        <c:if test="${actionBean.hakmilik.kategoriTanah.nama eq null}">&nbsp; Tiada &nbsp;</c:if></td>
                    </tr>
                </table>



                <br/><br/>
            <%--<hr>

         <br><br>

         <center><h4><b>KEPUTUSAN PENTADBIR TANAH MELAKA TENGAH</b></h4>
             --------------------------------------------------------- <br>
             <h5>(RAHSIA)</h5>

         <br><br>

         <p>Saya Pentadbir Tanah Melaka Tengah melalui Perwakilan Kuasa MMKN 21A/11/96 yang bersidang pada 10 April 1996 dan disahkan pada 17 April 1996 <br>
             <s:radio name="" value="TT" id="TT" title="Setuju"/><b>Setuju</b> &nbsp;
             <s:hidden name="kodSementara" value="TT" id="TT"/>
           <s:radio name="" value="TT" id="TT" title="Tidak Setuju"/><b>Tidak Setuju</b>
           <br>
            ke atas permohonan daripada ${actionBean.pemohon.pihak.nama} untuk menjadikan tanah milik sebagai Tanah Adat Melaka (MCL)ke atas <br>
            ${actionBean.hakmilik.noLot} , <b>Seluas</b> ${actionBean.hakmilik.luas} ${actionBean.hakmilik.kodUnitLuas.nama} ,${actionBean.hakmilik.bandarPekanMukim.nama}, Daerah Melaka Tengah, Melaka dengan syarat :-
            <br><br>
             a) Tanah ini dicatat (endorse) sebagai Tanah Adat Melaka (MCL) mengikut peruntukan Seksyen 109(A) Akta Kanun Tanah Negara (Hakmilik Pulau Pinang dan Melaka) 1963.

             <br><br>

              b) Setelah catatan sebagai Tanah Adat Melaka (MCL) diselesaikan, Pentadbir Tanah Melaka Tengah juga bersetuju mengeluarkan hakmilik dalam Daftar Hakmilik MCL Pejabat Tanah kepada pemilik tanah tersebut sekiranya pemilik
                 berkenaan membuat penyerahan dan memohon milik semula tanah tersebut.</p>

                 <br><br>

                <!s:button name="simpanMohonKertas"--%> 
            <%--&nbsp; &nbsp; &nbsp; &nbsp; <b>KEPUTUSAN PENTADBIR TANAH ${actionBean.daerah}</b>
            <p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Saya Pentadbir Tanah ${actionBean.hakmilik.daerah.nama} bersetuju/tidak bersetuju ke atas permohonan menjadikan Tanah Adat Melaka dan Pemohonan Penyerahan Balik Tanah dengan syarat-syarat seperti berikut :-
            --%>
            <b>4.</b> &nbsp; &nbsp; &nbsp; &nbsp; <u><b>KEPUTUSAN</b></u> <br><br>
            <p>&nbsp; &nbsp; &nbsp; &nbsp; 4.1 &nbsp; &nbsp; Pentadbir Tanah ${actionBean.hakmilik.daerah.nama} adalah diminta membuat keputusan samada bersetuju atau sebaliknya dengan perakuan yang dibuat seperti di perenggan 3 di atas. :- <br><br>
                <br><br>
                <%--
                <center>
                    <c:if test="${!actionBean.viewOnly}">
                        <s:button name="simpanMohonKertas" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </c:if>

                </center>
                --%>
        </fieldset>
    </div>

</s:form>