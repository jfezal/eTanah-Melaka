<%--
    Document   : draf_mmkn_bmbt_mlk
    Created on : Jun 8, 2011, 11:20:39 AM
    Author     : Administrator
    Modified by : Shazwan
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>



<script type="text/javascript">
    $(document).ready(function() {
    <c:choose>
        <c:when test="${actionBean.kodHakmilik eq 'GM'}">
                $('#tPajakan').hide();
        </c:when>
        <c:when test="${actionBean.kodHakmilik eq 'PM'}">
                $('#tPajakan').show();
        </c:when>
        <c:otherwise>
                $('#tPajakan').hide();
        </c:otherwise>
    </c:choose>  

        });
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
            var url = '${pageContext.request.contextPath}/pelupusan/draf_mmkn_bmbt?refreshDrafJKBB&edit='+edit;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
        function addPerihalTanah(){
            window.open("${pageContext.request.contextPath}/pelupusan/draf_mmkn_bmbt?showTambahPerihalTanah", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=710,heighbayaranSyaratt=500,scrollbars=yes");
        }
        function addPerakuanPTD(){
            var edit = $('#edit').val();
            window.open("${pageContext.request.contextPath}/pelupusan/draf_mmkn_bmbt?showTambahPerakuanPTD&edit=" + edit, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=710,height=500,scrollbars=yes");
        }
        function addPerakuanPTG(){
            var edit = $('#edit').val();
            window.open("${pageContext.request.contextPath}/pelupusan/draf_mmkn_bmbt?showTambahPerakuanPTG&edit=" + edit, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=710,height=500,scrollbars=yes");
        }
        function addAsasTimbang(){
            window.open("${pageContext.request.contextPath}/pelupusan/draf_mmkn_bmbt?showTambahAsasPertimbangan", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=710,height=500,scrollbars=yes");
        }
        function calculateSyarat(){
            var kuantiti = document.getElementById('kuantitiSyarat').value;
            //alert(kuantiti);
            var bayaran = document.getElementById('bayaranSyarat').value;
            //alert(bayaranSyarat);
            var jumlah = kuantiti * bayaran;
            //alert(jumlah);
            var cagaran = 20/100 * jumlah;
            var cagarJalan = document.getElementById('cagarJalan').value;

            var kuponQty = document.getElementById('kuponQty').value;

            var kuponAmaun = document.getElementById('kuponAmaun').value;
            var jumlahKpnQty = (kuponAmaun * kuponQty);
            cagarJalan = cagarJalan*1;
            var totalAll = (jumlah) + (cagaran) + (jumlahKpnQty) + (cagarJalan);

            document.getElementById('jumlahSyarat').value = CurrencyFormatted(jumlah);
            document.getElementById('cagaranSyarat').value = CurrencyFormatted(cagaran);
            document.getElementById('kuantitiJumlahSyarat').value = kuantiti;
            document.getElementById('cagarJalan').value = CurrencyFormatted(cagarJalan);
            document.getElementById('totalAll').value = CurrencyFormatted(totalAll);
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
            $.post('${pageContext.request.contextPath}/pelupusan/draf_mmkn_bmbt?simpanKandungan&index='+index+'&kandungan='+kand,q,
            function(data){
                $('#page_div').html(data);
            }, 'html');



        }
        function addRow(index,f)
        {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/draf_mmkn_bmbt?tambahRow&index='+index,q,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }
        function deleteRow(idKandungan,f)
        {
            if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
                var q = $(f).formSerialize();
                $.post('${pageContext.request.contextPath}/pelupusan/draf_mmkn_bmbt?deleteRow&idKandungan='+idKandungan,q,
                function(data){
                    $('#page_div').html(data);
                }, 'html');
            }
        }
        function calculateBayarKupon(){

            var kuponQty = document.getElementById('kuponQty').value;

            var kuponAmaun = document.getElementById('kuponAmaun').value;
            var jumlah = (kuponAmaun * kuponQty);
            document.getElementById('kupon').value = CurrencyFormatted(jumlah);
            calculateSyarat();
        }
        function showTempoh(val){
            if(val == 'PN')
                $('#tPajakan').show();
            else 
                $('#tPajakan').hide();
        }
        function semakSyor(f,v){
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/draf_mmkn_bmbt?showsyortolaklulus&kpsn='+v,q,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }
        function cariPopup(){
            var url = '${pageContext.request.contextPath}/pelupusan/draf_mmkn_bmbt?search';
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
        }
        function cariPopup2(){
            var url = '${pageContext.request.contextPath}/pelupusan/draf_mmkn_bmbt?showFormCariKodSekatan';
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
        }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pelupusan.DrafMMKN_BMBTActionBean" name="form" id="form">
    <s:messages/>
    <s:errors/>
    <s:hidden name="edit" id="edit"/>
    <s:hidden name="kertasK.idKertas"/>
    <%--<s:hidden name="edit" id="edit"/>--%>
    <s:hidden name="editPTD" id="editPTD"/>
    <s:hidden name="editPTG" id="editPTG"/>
    <s:hidden name="openPTG" id="openPTG"/>
    <s:hidden name="firstTimeOpen" id="firstTimeOpen"/>
    <%--taknak edit--%>

    <%--
    Purpose For Testing Program
    status ====== : <br>
    edit :: ${actionBean.edit}<br>
    openPTD :: ${actionBean.openPTD}<br>
    viewOnlyPTD :: ${actionBean.viewOnlyPTD}<br>
    viewOnlyPTG :: ${actionBean.viewOnlyPTG}<br>
    openPTG :: ${actionBean.openPTG}<br>
    --%>
    <c:if test="${actionBean.edit}">
        <table width="90%" border="0" >
            <tr>
                <td colspan="4">
                    <div class="subtitle" style="text-transform: capitalize">
                        <fieldset class="aras1">
                            <legend>
                                <c:if test="${actionBean.urusanStatus eq 'PPBB'}">
                                    DRAF RENCANA JAWATANKUASA BELAH BAHAGI
                                </c:if>
                                <c:if test="${actionBean.urusanStatus eq 'PBPTD'}">
                                    RENCANA UNTUK PERTIMBANGAN PENTADBIR TANAH DAERAH <span style="text-transform: uppercase">${actionBean.permohonan.cawangan.daerah.nama}</span>
                                </c:if>
                                <c:if test="${actionBean.urusanStatus eq 'PBPTG'}">
                                    RENCANA UNTUK PERTIMBANGAN PENGARAH TANAH DAN GALIAN
                                </c:if>
                                <c:if test="${actionBean.urusanStatus eq 'BMBT'}">
                                    <%--KERTAS MMK--%>
                                </c:if>
                            </legend>
                            <p align="center">
                                <b> </b>
                            </p>
                            <div class="content" align="center">

                                <table border="0" width="80%" cellspacing="10%" align="center">
                                    <tr><td id="tdLabel" >
                                            <b><font style="text-transform: capitalize">
                                                    <tr>
                                                        <td>
                                                            <p><b>
                                                                    <c:if test="${!actionBean.edit}">
                                                                        <s:textarea rows="6" cols="150" name="tajukMainDraf" class="normal_text" style="text-transform: uppercase"/>
                                                                    </c:if>
                                                                    <c:if test="${actionBean.edit}">
                                                                        <span style="text-transform: uppercase">${actionBean.tajukMainDraf}</span>
                                                                    </c:if>
                                                                    <%--<span style="text-transform:uppercase">${actionBean.tajukMainDraf}</span></b></p>--%>
                                                                </b></p>
                                                        </td>
                                                    </tr>
                                                </font></b>
                                        </td>
                                    </tr>

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
                <td>&nbsp;</td>
                <td width="1%">2.1.2</td>
                <td>
                    <div align="left">Tanah yang dipohon ditunjukkan dengan tepi warna merah dalam pelan yang dilampirkan.
                    </div>
                </td>
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
            <%--   <c:set value="0" var="i"/>
               <c:forEach items="${actionBean.senaraiLaporanKandunganPerihalTanah}" var="perihalTanah">
                   <s:hidden name="perihalTanah.kandungan${i}" value="${perihalTanah.kandungan}" id="${perihalTanah.kandungan}"/>
                   <tr>
                       <td>&nbsp;</td>
                       <td>&nbsp;</td>
                       <td width="1%">${perihalTanah.subtajuk}</td>
                       <td>${perihalTanah.kandungan}</td>
                   </tr>
                   <c:set value="${i+1}" var="i"/>
               </c:forEach> --%>

            <c:set var="i" value="1" />
            <c:set var="num" value="1"/>
            <c:forEach items="${actionBean.senaraiLaporanKandunganPerihalTanah}" var="line">
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
                <td valign="top">&nbsp;</td>
                <td colspan="3"><c:out value="2.3.${num}"/>
                    Tanah-tanah yang bersempadan dengan tanah yang dipohon adalah seperti berikut:-</td>
            </tr>

            <tr><td colspan="6">
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.laporTanahSempadanList}" cellpadding="0" cellspacing="0"
                                       requestURI="/pelupusan/draf_mmkn_bmbt" id="line">
                            <display:column title="Kedudukan">
                                <c:if test="${line.jenisSempadan eq 'U'}">
                                    Utara
                                </c:if>
                                <c:if test="${line.jenisSempadan eq 'S'}">
                                    Selatan
                                </c:if>
                                <c:if test="${line.jenisSempadan eq 'B'}">
                                    Barat
                                </c:if>
                                <c:if test="${line.jenisSempadan eq 'T'}">
                                    Timur
                                </c:if>
                            </display:column>
                            <display:column title="Tanah Kerajaan/Rizab/Lot/PT" >
                                <c:if test="${line.milikKerajaan eq 'T' || line.milikKerajaan eq 'N'}">
                                    <c:if test="${line.noLot ne null && line.kodLot ne null}">
                                        ${line.kodLot.nama} ${line.noLot}
                                    </c:if>
                                </c:if>
                                <c:if test="${line.milikKerajaan eq 'Y'}">
                                    Tanah Kerajaan
                                </c:if>
                                <c:if test="${line.milikKerajaan eq 'R'}">
                                    Rizab
                                </c:if>

                            </display:column>
                            <display:column title="Apa yang terdapat di atas tanah">Tanah ini digunakan untuk ${line.guna} dan keadaannya adalah ${line.keadaanTanah}</display:column>



                        </display:table>
                    </div>
                </td></tr>

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
                <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">ULASAN YB ADUN KAWASAN </font><span style="text-transform: uppercase">${actionBean.kawasanAdun}</span></b></div></td>
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
                    <td>&nbsp;</td>
                    <td colspan="2">( Ruj. Surat ${ulasanJKBB.noRujukan} bertarikh <s:format value="${ulasanJKBB.tarikhRujukan}" formatPattern="dd/MM/yyyy"/> )</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td width="1%">&nbsp;</td>
                    <td>${ulasanJKBB.ulasan}</td>
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
            <!--          <tr>
                        <td>&nbsp;</td>
                        <td>5.1</td>
                        <td colspan="2">${actionBean.tajukAsasTimbang}</td>
                      </tr>
            <c:set value="0" var="i"/>
            <c:forEach items="${actionBean.senaraiLaporanAsasPertimbangan}" var="asasTimbang">
                <s:hidden name="asasTimbang${i}" value="${asasTimbang.kandungan}" id="${asasTimbang.kandungan}"/>
           <tr>
             <td>&nbsp;</td>
             <td>&nbsp;</td>
             <td width="1%"><div align="right">${asasTimbang.subtajuk}</div></td>
             <td>${asasTimbang.kandungan}</td>
           </tr>
                <c:set value="${i+1}" var="i"/>
            </c:forEach>
            -->
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
                        <tr>
                            <td width="1%">&nbsp;</td>
                            <td>&nbsp;
                            </td>
                            <td colspan="2"> Perakuan :
                                <s:radio name="kpsn" value="SL" id="kpsn" onclick="semakSyor(this.form,this.value);"/> Syor Lulus
                                <s:radio name="kpsn" value="ST" id="kpsn" onclick="semakSyor(this.form,this.value);"/> Syor Tolak
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4">&nbsp;</td>
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
                        <tr>
                            <td width="1%">&nbsp;</td>
                            <td>&nbsp;
                            </td>
                            <td colspan="2"> Perakuan :
                                <c:if test="${actionBean.kpsn eq 'SL'}">Syor Lulus</c:if>
                                <c:if test="${actionBean.kpsn eq 'ST'}">Syor Tolak</c:if>
                            </td>
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

                </c:if>
                <c:if test="${!actionBean.viewOnlyPTD}">
                    <tr>
                        <td colspan="4">
                            &nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td width="1%">6.</td>
                        <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">PERAKUAN PENTADBIR TANAH DAERAH <span style="text-transform: uppercase">${actionBean.permohonan.cawangan.daerah.nama}</span></font></b></div></td>
                    </tr>
                    <tr>
                        <td width="1%">&nbsp;</td>
                        <td>&nbsp;
                        </td>
                        <td colspan="2"> Perakuan :
                            <c:if test="${actionBean.kpsn eq 'SL'}">Syor Lulus</c:if>
                            <c:if test="${actionBean.kpsn eq 'ST'}">Syor Tolak</c:if>
                        </td>
                    </tr>
                    <!--                <tr>
                                        <td>&nbsp;</td>
                                        <td>6.1</td>
                                        <td colspan="2"><s:textarea name="tajukPerakuanPTD" id="tajukPerakuanPTD"   class="normal_text" rows="5" cols="150"></s:textarea></td>
                                    </tr>
                    <c:set value="0" var="i"/>
                    <c:forEach items="${actionBean.senaraiLaporanKandunganPerakuanPTD}" var="perakuanPTD">
                    <tr>
                        <td>&nbsp;</td>
                        <td><div align="right">${perakuanPTD.subtajuk}</div></td>
                        <td colspan="2"><s:textarea name="perakuanPTD${i}" value="${perakuanPTD.kandungan}" id="${perakuanPTD.kandungan}" rows="5" cols="150" class="normal_text"/></td>
                      </tr>
                        <c:set value="${i+1}" var="i"/>
                    </c:forEach>
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td colspan="2"><s:button class="btn" value="Tambah" name="showTambahPerakuanPTD" id="newPerakuanPentadbir" onclick="addPerakuanPTD();"/>
                    <s:button class="btn" value="Simpan" name="simpanDataPTD" id="simpanDataPTD" onclick="doSubmit(this.form, this.name, 'page_div')"/></td>
                </tr>-->
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
            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td width="1%">&nbsp;</td>
                <td colspan="2">${actionBean.perakuPTD}</td>
            </tr>
            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>
            <c:if test="${actionBean.kpsn eq 'SL'}">
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2"><b><font style="text-transform: capitalize">SYARAT-SYARAT PEMBERIMILIKAN STRATUM</font></b></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>i.</td>
                    <td colspan="2">
                        Jenis Hakmilik Disyorkan :
                        ${actionBean.hakmilikPermohonan.kodHakmilik.nama}
                    </td>
                </tr>
                <tr id="tPajakan">
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2">
                        Tempoh Pajakan
                        ${actionBean.hakmilikPermohonan.tempohPajakan}
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>ii.</td>
                    <td colspan="2">
                        Isipadu Disyorkan  :
                        ${actionBean.hakmilikPermohonan.luasTerlibat} Meter Padu
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>iii.</td>
                    <td colspan="2">
                        Luas  :
                        ${actionBean.hakmilikPermohonan.hakmilik.luas} Meter Persegi
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>iv.</td>
                    <td colspan="2">
                        No Lot  :
                        ${actionBean.hakmilikPermohonan.hakmilik.noLot} 
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>v.</td>
                    <td colspan="2">
                        Syarat Nyata Disyorkan :
                        ${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>vi.</td>
                    <td colspan="2">
                        Sekatan Kepentingan Disyorkan :
                        ${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>vii.</td>
                    <td colspan="2">
                        Kadar Premium Disyorkan :
                        <s:format formatPattern="#,##0.00" value="${actionBean.hakmilikPermohonan.kadarPremium}"/>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>viii.</td>
                    <td colspan="2">
                        Kadar Cukai Tahunan :
                        <s:format formatPattern="#,##0.00" value="${actionBean.hakmilikPermohonan.cukaiBaru}"/>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>ix.</td>
                    <td colspan="2">
                        Kategori Kegunaan Tanah :
                        ${actionBean.hakmilikPermohonan.kodKegunaanTanah.nama}
                    </td>
                </tr>
            </c:if>

            <c:if test="${!actionBean.viewOnlyPTDOnly}">
                <tr>
                    <td colspan="4">
                        <center>
                            <s:button name="SimpandrafJKBB" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </center>
                    </td>
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
                    <!--                  <tr>
                                        <td>&nbsp;</td>
                                        <td>7.1</td>
                                        <td colspan="2">${actionBean.tajukPerakuanPTG}<s:hidden name="tajukPerakuanPTG" id="tajukPerakuanPTG"/></td>
                                      </tr>
                    <c:set value="0" var="i"/>
                    <c:forEach items="${actionBean.senaraiLaporanKandunganPerakuanPTG}" var="perakuanPTG">
                        <tr>
                          <td>&nbsp;</td>
                          <td><div align="right">${perakuanPTG.subtajuk}</div></td>
                          <td colspan="2">${perakuanPTG.kandungan}<s:hidden name="perakuanPTG${i}" value="${perakuanPTG.kandungan}" id="${perakuanPTG.kandungan}"/></td>
                        </tr>
                        <c:set value="${i+1}" var="i"/>
                    </c:forEach>
                    -->
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
                    <!--                  <tr>
                                        <td>&nbsp;</td>
                                        <td>7.1</td>
                                        <td colspan="2"><s:textarea name="tajukPerakuanPTG" id="tajukPerakuanPTG"   class="normal_text" rows="5" cols="150"></s:textarea></td>
                                      </tr>
                    <c:set value="0" var="i"/>
                    <c:forEach items="${actionBean.senaraiLaporanKandunganPerakuanPTG}" var="perakuanPTG">
                        <tr>
                          <td>&nbsp;</td>
                          <td><div align="right">${perakuanPTG.subtajuk}</div></td>
                          <td colspan="2"><s:textarea name="perakuanPTG${i}" value="${perakuanPTG.kandungan}" id="${perakuanPTG.kandungan}" rows="5" cols="150" class="normal_text"/></td>
                        </tr>
                        <c:set value="${i+1}" var="i"/>
                    </c:forEach>
                    <tr>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                      <td colspan="2"><s:button class="btn" value="Tambah" name="SimpandrafJKBB" id="newPerakuanPTG" onclick="addPerakuanPTG();"/>
                    <s:button class="btn" value="Simpan" name="simpanDataPTG" id="simpanDataPTG" onclick="doSubmit(this.form, this.name, 'page_div')"/></td>
                   </tr>-->
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
            <tr>
                <td colspan="4">
                    &nbsp;
                </td>
            </tr>


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
                            <c:if test="${actionBean.urusanStatus eq 'PPBB'}">
                                DRAF RENCANA JAWATANKUASA BELAH BAHAGI
                            </c:if>
                            <c:if test="${actionBean.urusanStatus eq 'PBPTD'}">
                                RENCANA UNTUK PERTIMBANGAN PENTADBIR TANAH DAERAH <span style="text-transform: uppercase">${actionBean.permohonan.cawangan.daerah.nama}</span>
                            </c:if>
                            <c:if test="${actionBean.urusanStatus eq 'PBPTG'}">
                                RENCANA UNTUK PERTIMBANGAN PENGARAH TANAH DAN GALIAN
                            </c:if>
                            <c:if test="${actionBean.urusanStatus eq 'BMBT'}">
                                <%--KERTAS MMK--%>
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
                <td>&nbsp;</td>
                <td width="1%">2.1.2</td>
                <td>
                    <div align="left">Tanah yang dipohon ditunjukkan dengan tepi warna merah dalam pelan yang dilampirkan.
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
                <td colspan="2"><div align="left"><b> Perihal Tanah </b></div></td>
            </tr>
            <c:set var="i" value="1" />
            <c:set var="num" value="1"/>
            <c:forEach items="${actionBean.senaraiLaporanKandunganPerihalTanah}" var="line">
                <tr>
                    <td>&nbsp;</td>
                    <td valign="top">&nbsp;</td>
                    <td><c:out value="2.3.${num}"/></td>
                    <td>
                        <c:if test="${!actionBean.edit}">
                            <s:textarea  id="kandungan22${i}"name="senaraiLaporanKandunganPerihalTanah[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
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
                <td>&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td colspan="3"><c:out value="2.3.${num}"/>
                    Tanah-tanah yang bersempadan dengan tanah yang dipohon adalah seperti berikut:-</td>
            </tr>

            <tr><td colspan="6">
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.laporTanahSempadanList}" cellpadding="0" cellspacing="0"
                                       requestURI="/pelupusan/draf_mmkn_bmbt" id="line">
                            <display:column title="Kedudukan">
                                <c:if test="${line.jenisSempadan eq 'U'}">
                                    Utara
                                </c:if>
                                <c:if test="${line.jenisSempadan eq 'S'}">
                                    Selatan
                                </c:if>
                                <c:if test="${line.jenisSempadan eq 'B'}">
                                    Barat
                                </c:if>
                                <c:if test="${line.jenisSempadan eq 'T'}">
                                    Timur
                                </c:if>
                            </display:column>
                            <display:column title="Tanah Kerajaan/Rizab/Lot/PT" >
                                <c:if test="${line.milikKerajaan eq 'T' || line.milikKerajaan eq 'N'}">
                                    <c:if test="${line.noLot ne null && line.kodLot ne null}">
                                        ${line.kodLot.nama} ${line.noLot}
                                    </c:if>
                                </c:if>
                                <c:if test="${line.milikKerajaan eq 'Y'}">
                                    Tanah Kerajaan
                                </c:if>
                                <c:if test="${line.milikKerajaan eq 'R'}">
                                    Rizab
                                </c:if>

                            </display:column>
                            <display:column title="Apa yang terdapat di atas tanah">Tanah ini digunakan untuk ${line.guna} dan keadaannya adalah ${line.keadaanTanah}</display:column>



                        </display:table>

                    </div>
                </td></tr>



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
                <td colspan="3"><b>ULASAN YB ADUN KAWASAN <span style="text-transform: uppercase">${actionBean.kawasanAdun}</span></b></td>
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

            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>
            <c:if test="${actionBean.openPTD}">
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                    <td><b>6.</b></td>
                    <td colspan="3"><font style="text-transform: capitalize"><b> PERAKUAN PENTADBIR TANAH DAERAH <span style="text-transform: uppercase">${actionBean.permohonan.cawangan.daerah.nama}</span></b></font></td>
                </tr>
                <tr>
                    <td width="1%">&nbsp;</td>
                    <td>&nbsp;
                    </td>
                    <td colspan="2"> Perakuan :
                        <s:radio name="kpsn" value="SL" id="kpsn" onclick="semakSyor(this.form,this.value);"/> Syor Lulus
                        <s:radio name="kpsn" value="ST" id="kpsn" onclick="semakSyor(this.form,this.value);"/> Syor Tolak
                    </td>
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
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
                            <s:button value="Tambah" class="btn" name="simpan"  onclick="addRow('6',this.form)"></s:button>
                            <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('6',${i-1},this.form)"></s:button>
                        </c:if>
                        <c:if test="${actionBean.edit}">
                            &nbsp;
                        </c:if>
                    </td>
                </tr>
                <%--
                END OF PERAKUAN PTD
                --%>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>

            </c:if>

            <tr>
                <td>&nbsp;</td>
                <td width="1%">&nbsp;</td>
                <td colspan="2">${actionBean.perakuPTD}</td>
            </tr>
            <c:if test="${actionBean.kpsn eq 'SL'}">
                <tr>
                    <td>&nbsp;</td>
                    <td width="1%">&nbsp;</td>
                    <td colspan="2"><div align="left"><b><font style="text-transform: capitalize">SYARAT-SYARAT PEMBERIMILIKAN STRATUM</font></b></div></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td width="1%">i.</td>
                    <td colspan="2">
                        <font color="red" size="4">*</font>Jenis Hakmilik Disyorkan :
                        <s:select name="kodHakmilik" value="${actionBean.hakmilikPermohonan.kodHakmilik.kod}" onclick="showTempoh(this.value);"  style="width:150px" id="kodHakmilik">
                            <s:option value="">Sila Pilih</s:option>
                            <%--<s:option value="GM">Geran Mukim</s:option>
                            <s:option value="PM">Pajakan Mukim</s:option>--%>
                             <s:option value="GRN">Geran</s:option>
                                            <s:option value="PN">Pajakan Negeri</s:option>
                        </s:select>
                    </td>
                </tr>
                <tr id="tPajakan">
                    <td>&nbsp;</td>
                    <td width="1%">&nbsp;</td>
                    <td colspan="2">
                        <font color="red" size="4">*</font>Tempoh Pajakan :
                        <s:text name="hakmilikPermohonan.tempohPajakan"  size="20"/> Tahun
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td width="1%">ii.</td>
                    <td colspan="2">
                        <font color="red" size="4">*</font>Isipada Disyorkan :
                        <s:text name="hakmilikPermohonan.luasTerlibat" formatPattern="#,###,##0.0000" size="20"/> Meter Padu
                        <s:hidden name="hakmilikPermohonan.kodUnitLuas.kod" value="MP"/>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td width="1%">iii.</td>
                    <td colspan="2">
                        <font color="red" size="4">*</font>Luas :
                        <s:text name="hakmilikPermohonan.hakmilik.luas" formatPattern="#,###,##0.0000" size="20"/> Meter Persegi
                        <s:hidden name="hakmilikPermohonan.kodUnitLuas.kod" value="MP"/>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td width="1%">iv.</td>
                    <td colspan="2">
                        <font color="red" size="4">*</font>No Lot :
                        <s:text name="hakmilikPermohonan.hakmilik.noLot" formatPattern="#,###,##0.0000" size="20"/>
                        <s:hidden name="hakmilikPermohonan.kodUnitLuas.kod" value="MP"/>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td width="1%">v.</td>
                    <td colspan="2">
                        <font color="red" size="4">*</font>Syarat Nyata :
                        <s:textarea name="syaratNyata" id="syaratNyata" rows="5" cols="80" readonly="yes" class="tooltips" value=""></s:textarea>
                        <s:hidden name="kod" id="kod"/>
                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup()"/>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td width="1%">vi.</td>
                    <td colspan="2">
                        <font color="red" size="4">*</font>Sekatan Kepentingan :
                        <s:textarea name="syaratNyata1" id="syaratNyata1" rows="5" cols="80" readonly="yes" class="tooltips" value=""></s:textarea>
                        <s:hidden name="kodSktn" id="kodSktn"/>
                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup2()"/>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td width="1%">vii.</td>
                    <td colspan="2">
                        <font color="red" size="4">*</font>Kadar Premium (RM) :
                        <s:text name="hakmilikPermohonan.kadarPremium" size="20" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);"  readonly="true"/>  Setahun
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td width="1%">viii.</td>
                    <td colspan="2">
                        <font color="red" size="4">*</font>Kadar Cukai Tahunan(RM) :
                        <s:text name="hakmilikPermohonan.cukaiBaru" size="20" onkeyup="validateNumber(this,this.value);" />  Setahun
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td width="1%">ix.</td>
                    <td colspan="2">
                        <font color="red" size="4">*</font>Kategori Kegunaan Tanah :
                        <s:select name="kodGunaTanah"  value="${actionBean.hakmilikPermohonan.kodKegunaanTanah.kod}"style="width:210px;" id="kodKegunaTanah">
                            <s:option value="">-- Sila Pilih --</s:option>
                            <s:options-collection collection="${listUtil.senaraiKegunaanTanah}" value="kod" label="nama" />

                        </s:select>
                    </td>
                </tr>
            </c:if>

            <tr>
                <td colspan="4">
                    <center>
                        <s:button name="SimpandrafJKBB" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </center>
                </td>
            </tr>
        </table>

    </c:if>

</s:form>