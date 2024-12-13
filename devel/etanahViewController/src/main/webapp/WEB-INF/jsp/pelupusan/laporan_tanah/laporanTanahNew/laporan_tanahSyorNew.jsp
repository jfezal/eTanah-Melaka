<%--
    Document   :  syorPPBB(Editable).jsp
    Created on :  Jan 17, 2012, 10:50:13 AM
    Author     :  Shazwan
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SYOR PENOLONG PEGAWAI TANAH</title>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">
    var size = 0;
    function save(event, f) {
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url, q,
                function(data) {
                    $('#page_div', opener.document).html(data);
                    self.close();
                }, 'html');
    }

    $(document).ready(function() {
        //maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    <c:choose>
        <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL' }">
            $('#batuansokong').show();
            $('#batuantidaksokong').hide();
        </c:when>
        <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'ST' }">
                $('#batuantidaksokong').show();
                $('#batuansokong').hide();
        </c:when>
        <c:otherwise>
                $('#batuantidaksokong').hide();
                $('#batuansokong').hide();
        </c:otherwise>
    </c:choose>
    <c:if test="${actionBean.ksn ne null}">
            $('#syorKpsn').val('${actionBean.ksn}');
    </c:if>
        }); //END OF READY FUNCTION

        function refreshpage2(type) {
            //        alert(type);
            var url = '${pageContext.request.contextPath}/common/laporan/tanah/laporantanahNewActionBean?refreshpage&type=' + type;
            window.open(url, "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
        function showbatuansokong(val) {
            $('#batuansokong').show();
            $('#batuantidaksokong').hide();
            if (val == 'ST') {
                val = 'SL';
            }
            setKpsn2(val)
        }

        function showbatuantidaksokong(val) {
            $('#batuantidaksokong').show();
            $('#batuansokong').hide();
            if (val == 'SL') {
                val == 'ST';
            }
            setKpsn2(val);
        }

        function addRow(index, f)
        {
            NoPrompt();
            var kodksn = document.getElementById("ksn").value;
            var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?tambahRow&index=' + index + '&kodksn=' + kodksn;
            window.open(url, "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
        function deleteRow(idKandungan, f, tName)
        {
            NoPrompt();
            if (confirm('Adakah anda pasti untuk menghapus data ini?')) {
                var q = $(f).formSerialize();
                $.post('${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?deleteRow&idKandungan=' + idKandungan + '&tName=' + tName + '&typeName=PPT', q,
                        function(data) {
                            $('#page_div').html(data);

                        }, 'html');
                self.refreshpage2('syorPPT');
            }
        }



        function CurrencyFormatted(amount)
        {
            var i = parseFloat(amount);
            if (isNaN(i)) {
                i = 0.00;
            }
            var minus = '';
            if (i < 0) {
                minus = '-';
            }
            i = Math.abs(i);
            i = parseInt((i + .005) * 100);
            i = i / 100;
            s = new String(i);
            if (s.indexOf('.') < 0) {
                s += '.00';
            }
            if (s.indexOf('.') == (s.length - 2)) {
                s += '0';
            }
            s = minus + s;
            return s;
        }
        function calculateBayarKupon() {

            var kuponQty = document.getElementById('kuponQty').value;
//            alert("kuponQty = " + kuponQty);
            var kuponAmaun = 50;
//            alert("kuponAmaun = " + kuponAmaun);
            var jumlahKupon = (kuponAmaun * kuponQty);
            document.getElementById('kupon').value = CurrencyFormatted(jumlahKupon);
//             alert("jumlahKupon = " + jumlahKupon);
            calculateSyarat();
        }
        function calculateSyarat() {

            var kuantiti = document.getElementById('isiPaduBatu').value;
//           alert("kuantiti = " + kuantiti);
            var bayaran = document.getElementById('bayaranIsiPadu').value;
//            alert("bayaran = " + bayaran);
            var jumlah = kuantiti * bayaran;
//            alert("jumlah = " + jumlah);
            //var cagaran = jumlah * 3;
            var cagaran = jumlah * 0.2; //Calculation From User PDT Jasin - 6/2/2015
            var cagarJalan = document.getElementById('cagarJalan').value;
//            alert("cagarJalan = " + cagarJalan);
            var kuponQty = document.getElementById('kuponQty').value;
//            alert("kuponQty = " + kuponQty);
            var kuponAmaun = 50;
//            kuponAmaun = 50;
//            alert("kuponAmaun = " + kuponAmaun);
            var jumlahKpnQty = (kuponAmaun * kuponQty);
//            alert("jumlahKpnQty = " + jumlahKpnQty);
            cagarJalan = cagarJalan * 1;
//            alert("cagarJalan = " + cagarJalan);

            //var totalAll = (jumlah) + (cagaran) + (jumlahKpnQty) + (cagarJalan);
            var totalAll = jumlah + cagaran + jumlahKpnQty + cagarJalan; //Calculation From User PDT Jasin - 6/2/2015

            document.getElementById('jumlahSyarat').value = CurrencyFormatted(jumlah);
            document.getElementById('cagaranSyarat').value = CurrencyFormatted(cagaran);
            document.getElementById('kuantitiJumlahSyarat').value = kuantiti;
            document.getElementById('cagarJalan').value = CurrencyFormatted(cagarJalan);
            document.getElementById('totalAll').value = CurrencyFormatted(totalAll);
        }
        function setKandungan(i, idLaporUlas) {
            var index = i;
            var kandungan = $('#kandungan5' + index).val();
            $('#' + idLaporUlas + 'kandunganUlas').val(kandungan);
        }
        function setKpsn() {
            NoPrompt();
//            var kpsn = document.getElementById('ksn').value;
//            if(kpsn != null)
//                $('#syorKpsn').val(kpsn);
        }
        function setKpsn2(val) {
            $('#ksn').val(val);
            $('#syorKpsn').val(val);
        }
</script>
<body>
    <script>
        // Allow the user to be warned by default.
        var allowPrompt = true;

        window.onbeforeunload = WarnUser;
        function WarnUser()
        {
            if (allowPrompt)
                refreshpage();
            if (allowPrompt)
            {
                event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
            }
            else
            {
                // Reset the flag to its default value.
                allowPrompt = true;
            }
        }
        function NoPrompt()
        {
            allowPrompt = false;
        }

    </script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.common.laporan.tanah.laporantanahNewActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <c:if test="${actionBean.hakmilikPermohonan.permohonan.kodUrusan.kod ne 'PRIZ'}">
            <div class="subtitle">
                <fieldset class="aras1">
                    <div id="perihaltanah">
                        <legend>
                            <c:if test="${actionBean.kodNegeri eq '04'}">
                                <c:choose>
                                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                                        Syor Penolong Pegawai Tanah
                                    </c:when>
                                    <c:otherwise>
                                        Syor Penolong Pegawai Tanah
                                    </c:otherwise>
                                </c:choose>                       
                            </c:if>                  
                        </legend>
                    </div>


                    <table class="tablecloth" align="center">
                        <tr>
                            <td>
                                <font color="red" size="4">*</font>Syor : 
                            </td>
                            <td>
                                <s:radio name="ksn" id="ksn" value="SL" onclick="showbatuansokong(this.value);" />&nbsp;Sokong
                                <s:radio name="ksn" id="ksn" value="ST" onclick="showbatuantidaksokong(this.value);" />&nbsp;Tidak Sokong                                             
                                <s:hidden name="syorKpsn" id="syorKpsn"/>
                            </td>

                        </tr>
                    </table>
                    <br/>
                    <div id="batuansokong">
                        <table class="tablecloth" align="center">
                            <tr>
                                <td>Jumlah Isipadu Dipohon :</td>
                                <td>
                                    ${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohon}
                                    <c:if test="${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.kod eq 'MP'}">Meterpadu</c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Tempoh Dipohon :</td>
                                    <td>
                                    ${actionBean.permohonanBahanBatuan.tempohKeluar} ${actionBean.permohonanBahanBatuan.unitTempohKeluar.nama}
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Kuantiti yang disyorkan :</td>
                                <td>
                                    <s:text name="isiPaduBatu" id="isiPaduBatu" value="${actionBean.permohonanBahanBatuan.jumlahIsipadu}"/> 
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Tempoh :</td>
                                <td>
                                    <s:text name="tempohDisyor" value="${actionBean.permohonanBahanBatuan.tempohDisyor}" id="tempohDisyor" />                                    
                                    <s:select name="unitTempohUOM" id="unitTempohUOM" value="${actionBean.permohonanBahanBatuan.tempohSyorUom.kod}">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:option value="HR">Hari</s:option>
                                        <s:option value="B">Bulan</s:option>
                                        <s:option value="T">Tahun</s:option>
                                    </s:select>
                                </td>
                            </tr>
                            <tr>
                                <td>Kadar Bayaran (RM): </td>
                                <td><c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'H'}">
                                        <s:format value="${actionBean.permohonanBahanBatuan.jenisBahanBatu.royaltiTanahMilik}" formatPattern="##0.00"/> <span style="text-transform:lowercase">se${actionBean.permohonanBahanBatuan.jenisBahanBatu.royaltiTanahMilikUom.nama}</span>
                                        <s:hidden name="bayaranIsiPadu" id="bayaranIsiPadu" value="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.royaltiTanahMilik}"/>
                                    </c:if>
                                    <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'S'}">
                                        <s:format value="${actionBean.permohonanBahanBatuan.jenisBahanBatu.royaltiTanahMilik}" formatPattern="##0.00"/> <span style="text-transform:lowercase">se${actionBean.permohonanBahanBatuan.jenisBahanBatu.royaltiTanahMilikUom.nama}</span>
                                        <s:hidden name="bayaranIsiPadu" id="bayaranIsiPadu" value="${actionBean.permohonanBahanBatuan.jenisBahanBatu.royaltiTanahMilik}"/>
                                    </c:if>
                                    <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'K' or actionBean.hakmilikPermohonan.kodMilik.kod eq 'R' or actionBean.hakmilikPermohonan.kodMilik.kod eq 'L'}">
                                        <s:format value="${actionBean.permohonanBahanBatuan.jenisBahanBatu.royaltiTanahKerajaan}" formatPattern="##0.00"/> <span style="text-transform:lowercase">se${actionBean.permohonanBahanBatuan.jenisBahanBatu.royaltiTanahMilikUom.nama}</span>
                                        <s:hidden name="bayaranIsiPadu" id="bayaranIsiPadu" value="${actionBean.permohonanBahanBatuan.jenisBahanBatu.royaltiTanahKerajaan}"/>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td>Jumlah bayaran yang dikenakan (RM) :</td>
                                <td><c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'H'}">
                                        <s:format value="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.royaltiTanahMilik}" formatPattern="##0.00"/>
                                    </c:if>
                                    <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'K' or actionBean.hakmilikPermohonan.kodMilik.kod eq 'R' or actionBean.hakmilikPermohonan.kodMilik.kod eq 'L'}">
                                        <s:format value="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.royaltiTanahKerajaan}" formatPattern="##0.00"/>
                                    </c:if>
                                    x <s:text name="disPermohonanBahanBatu.syaratBahanBatu.jumlahIsipadu" id="kuantitiJumlahSyarat" readonly="true"/> = RM <s:text name="disPermohonanBahanBatu.jumlahKeneBayar" id="jumlahSyarat" formatPattern="###,###,##0.00" readonly="true"/>

                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <font color="red" size="4">*</font>Wang Cagaran yang dikenakan (RM) : 
                                </td>
                                <td><s:text name="disPermohonanBahanBatu.cagarKeneBayar" id="cagaranSyarat" formatPattern="###,###,##0.00" readonly="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>Cagaran Jalan (RM) :</td>
                                <td>
                                    <s:text name="cagarJalan" value="${actionBean.cagaranJalan.amaunTuntutan}" id="cagarJalan" formatPattern="###,###,##0.00"/> 
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Bayaran Kupon (RM) : </td>
                                <td>
                                    <s:format formatPattern="#,##.00" value="${actionBean.disPermohonanBahanBatu.kuponAmaun}"/>  * 
                                    <s:text name="kuponQty" value="${actionBean.bayarankupon.kuantiti}" id="kuponQty" /> = RM 
                                    <s:text name="kupon" id="kupon" readonly="true"/>
                                </td> 
                            </tr>
                            <tr>
                                <td>Jumlah Keseluruhan Bayaran (RM) :</td>
                                <td><s:text name="disPermohonanBahanBatu.totalAll" id="totalAll" readonly="true"/>
                                </td>    
                            </tr>
                        </table>
                    </div>
                    <div id="batuantidaksokong">
                        <table class="tablecloth" align="center">
                            <tr>
                                <td><font color="red" size="4">*</font>Sebab :</td>
                                <td><s:textarea name="sebab" rows="5" cols="80" id="ulasan" value="${actionBean.fasaPermohonan.ulasan}" /></td>
                            </tr>
                        </table>
                    </div>



                    <legend>Ulasan</legend>
                    <table class="tablecloth" border="0" align="center">
                        <c:set var="i" value="1" />
                        <c:set var="num" value="1"/>
                        <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line">
                            <tr>
                                <td style="text-align: right"><c:out value="${num})"/></td>
                                <td>
                                    <s:textarea  id="kandungan5${i}" name="senaraiLaporanKandungan1[${i-1}].ulasan" cols="80"  rows="5" onblur="setKandungan(${i},${line.idLaporUlas})" class="normal_text"/>
                                    <s:hidden id="${line.idLaporUlas}kandunganUlas" name="${line.idLaporUlas}kandunganUlas"/>
                                    <s:hidden name="${line.idLaporUlas}"/>
                                </td>
                                <td style="vertical-align: middle;">
                                    <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idLaporUlas},this.form,'mohonLaporUlas')"></s:button> 
                                    </td>
                                </tr>

                            <c:set var="i" value="${i+1}" />
                            <c:set var="num" value="${num+1}"/>
                        </c:forEach>
                        <tr>
                            <s:hidden name="idLaporTanah" id="idLaporTanah" value="${actionBean.laporanTanah.idLaporan}"/>
                            <s:hidden name="idMohonHakmilik" id="idMohonHakmilik" value="${actionBean.hakmilikPermohonan.id}"/>
                            <td style="text-align:center;" colspan="3">      
                                <s:submit name="simpanKandungan" value="Simpan" class="btn"/>
                                <s:button name="Kira" value="Kira" class="btn" onclick="calculateBayarKupon();"/>

                                <s:hidden name="index" id="index" value="5"/>
                                <%--<s:button name="simpanSyor" value="Simpan" class="btn" onclick="menyimpan('5',${i-1},this.form)"/>--%>
                                <s:button name="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                            </td>
                        </tr>
                    </table>
                </fieldset>
            </div>
        </c:if>




    </s:form>
</body>

