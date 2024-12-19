<%-- 
    Document   : laporan_pelanV2StatusTanah
    Created on : Feb 19, 2013, 1:33:12 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PERIHAL TANAH</title>
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
        //            maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

    <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod eq '99'}">
            $('#jenistanahlainlain').show();
    </c:if>
    <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod ne '99'}">
            $('#jenistanahlainlain').hide();
    </c:if>
            /*
             * KOD MILIK
             */
    <c:choose>
        <c:when test="${actionBean.kodP eq 'K'}">
                $('#tanahkerajaan').show();
                $('#jikasebab').hide();
                $('#carianHakmilik').hide();
                $('#jenisRizab').hide();
                $('#jenisRizabno').hide();
        </c:when>
        <c:when test="${actionBean.kodP eq 'H'}">
                $('#carianHakmilik').show();
                $('#tanahkerajaan').hide();
                $('#jikasebab').hide();
                $('#jenisRizab').hide();
                $('#jenisRizabno').hide();
        </c:when>
        <c:when test="${actionBean.kodP eq 'R'}">
                $('#jenisRizab').show();
                $('#jenisRizabno').show();
                $('#carianHakmilik').hide();
                $('#tanahkerajaan').hide();
                $('#jikasebab').hide();
        </c:when>
        <c:otherwise>
                $('#jenisRizab').hide();
                $('#jenisRizabno').hide();
                $('#carianHakmilik').hide();
                $('#tanahkerajaan').hide();
                $('#jikasebab').hide();
        </c:otherwise>
    </c:choose>
            /*
             * END
             */
            /*
             * PARLIMEN DAN DUN
             */
    <c:choose>
        <c:when test="${actionBean.hakmilikPermohonan.kodKawasanParlimen.kod ne null}">
                filterKodDun();
        </c:when>       
    </c:choose>

        }); //END OF READY FUNCTION

        function showSebab() {
            $('#jikasebab').show();
        }

        function hideSebab() {
            $('#jikasebab').hide();
        }

        function showjenistanahlain(value) {
            // alert(value);
            if (value == '99') {
                $('#jenistanahlainlain').show();
            } else {
                $('#jenistanahlainlain').hide();
            }
        }

        function refreshpage(type) {
            //        alert(type);
            var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelanV2?refreshpage&type=' + type;
            window.open(url, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }

        function refreshpage() {
            //        alert('aa');
            NoPrompt();
    <c:choose>
        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
                var idHakmilik = $('#idHakmilik').val();
                opener.refreshV2ManyHakmilik('main', idHakmilik);
        </c:when>
        <c:otherwise>
                opener.refreshV2('main');
        </c:otherwise>
    </c:choose>

            self.close();
        }

        function openLain_lainPengawal(val) {
            if (val == '3') {
                $('#catatanPengawal').show();
            } else {
                $('#catatanPengawal').hide();
            }
        }

        //        function changeHideDun(value){
        //            // alert(value);
        //            $('#kodD').val("");
        //            if(value == 'P134'){
        //                $('#mT').show();
        //                $('#aG').hide();
        //                $('#tB').hide();
        //                $('#bK').hide();
        //                $('#kM').hide();
        //                $('#jS').hide();
        //            }else if(value == 'P135'){
        //                $('#aG').show();
        //                $('#mT').hide();
        //                $('#tB').hide();
        //                $('#bK').hide();
        //                $('#kM').hide();
        //                $('#jS').hide();
        //            }else if(value == 'P136'){
        //                $('#tB').show();
        //                $('#aG').hide();
        //                $('#mT').hide();
        //                $('#bK').hide();
        //                $('#kM').hide();
        //                $('#jS').hide();
        //            }else if(value == 'P137'){
        //                $('#bK').show();
        //                $('#tB').hide();
        //                $('#aG').hide();
        //                $('#mT').hide();
        //                $('#kM').hide();
        //                $('#jS').hide();
        //            }else if(value == 'P138'){
        //                $('#kM').show();
        //                $('#bK').hide();
        //                $('#tB').hide();
        //                $('#aG').hide();
        //                $('#mT').hide();
        //                $('#jS').hide();
        //            }else if(value == 'P139'){
        //                $('#jS').show();
        //                $('#kM').hide();
        //                $('#bK').hide();
        //                $('#tB').hide();
        //                $('#aG').hide();
        //                $('#mT').hide();
        //            }
        //        }

        function changeHide(value) {
                 // alert(value);
            if (value == 'K') {
                $('#tanahkerajaan').show();
                $('#jikasebab').hide();
            } else {
                $('#tanahkerajaan').hide();
            }

            if (value == 'R') {
                $('#jenisRizab').show();
                $('#jenisRizabno').show();
            } else {
                $('#jenisRizab').hide();
                $('#jenisRizabno').hide();
            }
            if (value == 'H') {
                $('#carianHakmilik').show();
                $('#jikasebab').hide();
            } else {
                $('#carianHakmilik').hide();
            }
        }

        function dun(value) {
            $('#kodD').val(value);
        }

        function filterKodDun() {
            var kodPar = $("#kodPar").val();
            //        alert("hi");
            NoPrompt();

            $.post('${pageContext.request.contextPath}/pelupusan/laporan_pelanV2?senaraiKodDunByKodPar&kodPar=' + kodPar,
            function(data) {
                if (data != '') {
                    $('#partialKodDun').html(data);
                    //                    $.unblockUI();
                }
            }, 'html');

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
    <s:form beanclass="etanah.view.stripes.pelupusan.LaporanPelanV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="kodD" id="kodD"/>
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <s:hidden name="noPtSementara" id="noPtSementara"/>
        <s:hidden name="idLapor" id="idLapor" value="${actionBean.permohonanLaporanPelan.idLaporan}"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <div id="perihaltanah">
                    <legend>Status Tanah</legend>
                    <div class="content" align="center">
                        <table class="tablecloth" border="0">



                            <tr>
                                <td>
                                    <font color="red" size="4">*</font>No. Lembaran Piawai :
                                </td>
                                <td>
                                    <s:text name="noLitho" size="20" />
                                </td>
                            </tr>
                            <%--<c:if test="${actionBean.hakmilikPermohonan.hakmilik ne null}">--%>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPRU' && actionBean.permohonan.kodUrusan.kod ne 'BMBT' && actionBean.permohonan.kodUrusan.kod ne 'PJBTR' && actionBean.permohonan.kodUrusan.kod ne 'PBMMK'}">
                                <tr>
                                    <td>
                                        <font color="red" size="4">*</font>Luas :
                                    </td>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS' || actionBean.permohonan.kodUrusan.kod eq 'PBPTD' || actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                                        <td>
                                            <s:text name="luas" formatPattern="0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);" id="Luas"/>
                                            <s:select name="kodLuas" value="${actionBean.kodLuas}" id="kULuas">
                                                <s:option value="">Sila Pilih</s:option>
                                                <s:option value="H">Hektar</s:option>
                                                <s:option value="M">Meter Persegi</s:option>
                                            </s:select>
                                        </td>
                                    </c:if>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PLPS' && actionBean.permohonan.kodUrusan.kod ne 'PBPTD' && actionBean.permohonan.kodUrusan.kod ne 'PBMT'}">
                                        <td>
                                            <s:text name="hakmilikPermohonan.luasDiluluskan" formatPattern="0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);" id="Luas"/>
                                            <s:select name="kodUnitLuas.kod" value="${actionBean.hakmilikPermohonan.luasLulusUom.kod}" id="kULuas">
                                                <s:option value="">Sila Pilih</s:option>
                                                <s:option value="H">Hektar</s:option>
                                                <s:option value="M">Meter Persegi</s:option>
                                            </s:select>
                                        </td>
                                    </c:if>
                                </tr>
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMMK'}">
                                <tr>
                                    <td>
                                        <font color="red" size="4">*</font>Luas / Panjang :
                                    </td>
                                    <td>
                                        <s:text name="hakmilikPermohonan.luasTerlibat" formatPattern="0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);" id="Luas"/>
                                        <s:select name="kodUnitLuas.kod" value="${actionBean.hakmilikPermohonan.kodUnitLuas.kod}" id="kULuas">
                                            <s:option value="">Sila Pilih</s:option>
                                            <s:option value="H">Hektar</s:option>
                                            <s:option value="M">Meter Persegi</s:option>
                                            <s:option value="KM">Kilometer</s:option>
                                            <s:option value="JM">Meter</s:option>
                                        </s:select>
                                    </td>
                                </tr>
                            </c:if>

                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">
                                <tr>
                                    <td>
                                        <font color="red" size="4">*</font>Keluasan Terlibat :
                                    </td>
                                    <td><%--hakmilikPermohonan.luasTerlibat--%>
                                        <s:text name="hakmilikPermohonan.luasDiluluskan" formatPattern="0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);" id="Luas"/>
                                        <s:select name="kodUnitLuas.kod" value="${actionBean.hakmilikPermohonan.luasLulusUom.kod}" id="kULuas">
                                            <s:option value="">Sila Pilih</s:option>
                                            <s:option value="M">Meter Persegi</s:option>
                                            <s:option value="MP">Meter Padu</s:option>
                                        </s:select>
                                    </td>
                                </tr>
                            </c:if>

                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT'}">
                                <tr>
                                    <td>
                                        <font color="red" size="4">*</font>Luas Atas Tanah :
                                    </td>
                                    <td>
                                        ${actionBean.hakmilikPermohonan.hakmilik.luas}
                                        ${actionBean.hakmilikPermohonan.hakmilik.kodUnitLuas.nama}
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <font color="red" size="4">*</font>Isipadu :
                                    </td>
                                    <td>
                                        <s:text name="hakmilikPermohonan.luasTerlibat" formatPattern="0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);" id="Luas"/>
                                        <s:select name="kodUnitLuas.kod" value="${actionBean.hakmilikPermohonan.kodUnitLuas.kod}" id="kULuas">
                                            <s:option value="">Sila Pilih</s:option>
                                            <s:option value="MP">Meter Padu</s:option>
                                        </s:select>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJBTR'}">
                                <tr>
                                    <td>
                                        <font color="red" size="4">*</font>Isipadu :
                                    </td>
                                    <td>
                                        <s:text name="hakmilikPermohonan.luasTerlibat" formatPattern="0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);" id="Luas"/>
                                        <s:select name="kodUnitLuas.kod" value="${actionBean.hakmilikPermohonan.kodUnitLuas.kod}" id="kULuas">
                                            <s:option value="">Sila Pilih</s:option>
                                            <s:option value="MP">Meter Padu</s:option>
                                        </s:select>
                                    </td>
                                </tr>
                            </c:if>
                            <tr>
                                <td>
                                    <font color="red" size="4">*</font>Status Tanah :
                                </td>
                                <td>
                                    <s:select name="kodP" style="width:150px" id="kodP" onchange="javaScript:changeHide(this.value)">
                                        <c:choose>
                                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                                                <s:option value="0">Sila Pilih</s:option>
                                                <s:options-collection collection="${listUtil.senaraiKodPemilikanLPSP}" label="nama" value="kod"/>
                                            </c:when>
                                            <c:otherwise>
                                                <s:option value="0">Sila Pilih</s:option>
                                                <s:options-collection collection="${listUtil.senaraiKodPemilikan}" label="nama" value="kod"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </s:select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <font color="red" size="4">*</font>Jenis Tanah :
                                </td>
                                <td>
                                    <s:select name="kodT" style="width:150px" value="" id="kodTanah" onchange="javaScript:showjenistanahlain(this.value)">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodTanah}" label="nama" value="kod"/>
                                    </s:select>
                                </td>
                            </tr>
                            <tr id="jenistanahlainlain">
                                <td>
                                    jika lain-lain :
                                </td>
                                <td>
                                    <s:textarea name="kand" rows="5" cols="50"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Jika ditanda untuk projek kerajaan, sila nyatakan :
                                </td>
                                <td>
                                    <s:text name="projekKerajaan" size="50" />
                                </td>
                            </tr>
                            <tr>
                                <td><c:if test="${actionBean.kodNegeri eq '04'}">
                                        <font color="red" size="4">*</font>
                                    </c:if>
                                    Kawasan Parlimen :
                                </td>
                                <td>
                                    <s:select name="kodPar" style="width:150px" value="" id="kodPar" onchange="filterKodDun();">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodKawasanparlimen}" label="nama" value="kod"/>
                                    </s:select>
                                </td>
                            </tr>
                            <tr>
                                <td><c:if test="${actionBean.kodNegeri eq '04'}">
                                        <font color="red" size="4">*</font>
                                    </c:if>
                                    DUN :
                                </td>
                                <td id="partialKodDun"></td>
                            </tr>
                            <!-- <c:if test="${actionBean.kodNegeri eq '04'}">
                                 <tr>
                                     <td>
                                         <font color="red" size="4">*</font>Kawasan Parlimen :
                                     </td>
                                     <td>
                                <s:select name="kodPar" style="width:150px" value="" id="kodPar" onchange="javaScript:changeHideDun(this.value)">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodKawasanparlimen}" label="nama" value="kod"/>
                                </s:select>
                            </td>
                        </tr>
                        <tr id="mT">
                            <td>
                                <font color="red" size="4">*</font>DUN :
                            </td>
                            <td>
                                <s:select name="kodDmT" style="width:150px" value="" id="kodDmT" onchange="dun(this.value);">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:option value="N01">Kuala Linggi</s:option>
                                    <s:option value="N02">Tanjung Bidara</s:option>
                                    <s:option value="N03">Ayer Limau</s:option>
                                    <s:option value="N04">Lendu</s:option>
                                    <s:option value="N05">Taboh Naning</s:option>
                                </s:select>
                            </td>
                        </tr>
                        <tr id="aG">
                            <td>
                                <font color="red" size="4">*</font>DUN :
                            </td>
                            <td>
                                <s:select name="kodDaG" style="width:150px" value="" id="kodDaG" onchange="dun(this.value);">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:option value="N06">Rembia</s:option>
                                    <s:option value="N07">Gadek</s:option>
                                    <s:option value="N08">Machap</s:option>
                                    <s:option value="N09">Durian Tunggal</s:option>
                                    <s:option value="N10">Asahan</s:option>
                                </s:select>
                            </td>
                        </tr>
                        <tr id="tB">
                            <td>
                                <font color="red" size="4">*</font>DUN :
                            </td>
                            <td>
                                <s:select name="kodDtB" style="width:150px" value="" id="kodDtB" onchange="dun(this.value);">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:option value="N11">Sungai Udang</s:option>
                                    <s:option value="N12">Pantai Kundor</s:option>
                                    <s:option value="N13">Paya Rumput</s:option>
                                    <s:option value="N14">Kelebang</s:option>
                                </s:select>
                            </td>
                        </tr>
                        <tr id="bK">
                            <td>
                                <font color="red" size="4">*</font>DUN :
                            </td>
                            <td>
                                <s:select name="kodDbK" style="width:150px" value="" id="kodDbK" onchange="dun(this.value);">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:option value="N15">Bachang</s:option>
                                    <s:option value="N16">Ayer Keroh</s:option>
                                    <s:option value="N17">Bukit Baru</s:option>
                                    <s:option value="N18">Ayer Molek</s:option>
                                </s:select>
                            </td>
                        </tr>
                        <tr id="kM">
                            <td>
                                <font color="red" size="4">*</font>DUN :
                            </td>
                            <td>
                                <s:select name="kodDkM" style="width:150px" value="" id="kodDkM" onchange="dun(this.value);">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:option value="N19">Kesidang</s:option>
                                    <s:option value="N20">Kota Laksamana</s:option>
                                    <s:option value="N21">Duyong</s:option>
                                    <s:option value="N22">Bandar Hilir</s:option>
                                    <s:option value="N23">Telok Mas</s:option>
                                </s:select>
                            </td>
                        </tr>
                        <tr id="jS">
                            <td>
                                <font color="red" size="4">*</font>DUN :
                            </td>
                            <td>
                                <s:select name="kodDjS" style="width:150px" value="" id="kodDjS" onchange="dun(this.value);">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:option value="N24">Bemban</s:option>
                                    <s:option value="N25">Rim</s:option>
                                    <s:option value="N26">Serkam</s:option>
                                    <s:option value="N27">Merlimau</s:option>
                                    <s:option value="N28">Sungai Rambai</s:option>
                                </s:select>
                            </td>
                        </tr>
                            </c:if> -->
                            <%--</c:if>--%>

                        </table>
                    </div>
                    <br/>

            </fieldset>
        </div>
        <fieldset class="aras1">
            <table  width="100%" border="0">
                <tr>
                    <td align="center"> 
                        <s:submit name="simpanPerihal" value="Simpan" class="btn" onclick="NoPrompt();"/>
                        <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                    </td>
                </tr>
            </table>   
        </fieldset>
    </s:form>
</body>
