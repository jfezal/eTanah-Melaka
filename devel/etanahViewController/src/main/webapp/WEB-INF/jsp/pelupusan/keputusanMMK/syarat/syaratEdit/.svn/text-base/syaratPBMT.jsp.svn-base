<%-- 
    Document   : syaratPBMT
    Created on : Mar 13, 2013, 4:09:44 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SYARAT-SYARAT</title>
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
    <c:choose>
        <c:when test="${actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'GM' || actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'GRN'}">
        $('#jikaPajakan').hide();
        </c:when>
        <c:when test="${actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'PM' || actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'PN'}">
        $('#jikaPajakan').show();
        </c:when>
        <c:otherwise>
        $('#jikaPajakan').hide();
        </c:otherwise>
    </c:choose>
    <c:if test="${actionBean.hakmilikPermohonan.kategoriTanahBaru ne null}">
        filterKodGunaTanah();
    </c:if>
    }); //END OF READY FUNCTION

    function cariPopup(val) {
        var idHakmilik = $('#idHakmilik').val();
        NoPrompt();
        window.open("${pageContext.request.contextPath}/pelupusan/rekod_keputusanMMKV2?searchSyarat&jenisSyarat=" + val + "&idHakmilik=" + idHakmilik, "eTanah",
                "status=1,toolbar=0,location=1,menubar=0,width=910,height=800");
    }

    function getListKegunaanTanah()
    {
        NoPrompt();
        //         var q = $(f).formSerialize();
        var luas = $('#luasLulus').val();
        var idHakmilik = $('#idHakmilik').val();
        var kodKatTanah = $('#_kodKatTanah').val();
        self.editData(luas, idHakmilik, kodKatTanah, 'update');
    }

    function editData(luas, idMohonHakmilik, kodKat, type) {
        window.open("${pageContext.request.contextPath}/pelupusan/rekod_keputusanMMKV2?editDetailsData&idMH=" + idMohonHakmilik
                + "&type=" + type + "&luas=" + luas + "&kodKat=" + kodKat, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }

    function doSomething(val) {

        if (val == 'GRN' || val == 'GM') {
            //alert(val);
            $('#jikaPajakan').hide();
        } else if (val == 'PN' || val == 'PM') {
            //alert(val);
            $('#jikaPajakan').show();
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

    function filterKodGunaTanah() {
        var katTanah = $("#_kodKatTanah").val();
        //        alert("hi");
        NoPrompt();

        $.post('${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?senaraiKodGunaTanahByKatTanah&kodKatTanah=' + katTanah,
                function(data) {
                    if (data != '') {
                        $('#partialKodGunaTanah').html(data);
                        kiraCukaiKhas(this.form, $("#kodTanah").val());
                        //                    $.unblockUI();
                    }
                }, 'html');

    }

    function kiraCukai(f) {
        var kodTanah = $("#kodTanah").val();
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pelupusan/rekod_keputusanMMKV2?kiraCukai&kodTanah=' + kodTanah, q,
                function(data) {
                    if (data == '-1') {
                    } else {
                        $('#cukai').val("RM " + CurrencyFormatted(data) + " sehektar");
                    }
                }, 'html');
    }

    function kiraCukaiKhas(f, kod) {

        var kodTanah = kod;
//                        alert(kodTanah);
        var idHakmilik = $("#idHakmilik").val();
//            alert(idHakmilik);
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pelupusan/rekod_keputusanMMKV2?kiraCukai&kodTanah=' + kodTanah + '&idHakmilik=' + idHakmilik, q,
                function(data) {
                    if (data == '-1') {
                    } else {
                        $('#cukai').val("RM " + CurrencyFormatted(data) + " sehektar");
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
    <s:form beanclass="etanah.view.stripes.pelupusan.RekodKeputusanMMKV2ActionBean" name="form">
        <s:hidden name="idHakmilik" id="idHakmilik" value="${actionBean.hakmilikPermohonan.id}"/>
        <c:choose>
            <c:when test="${actionBean.kodNegeri eq '04'}">
                <table class="tablecloth" border="0">
                    <tr>
                        <td>
                            Luas Disyorkan :
                        </td>
                        <td>
                            <s:format value="${actionBean.hakmilikPermohonan.luasTerlibat}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Luas Diluluskan :
                        </td>
                        <td>
                            <s:text name="hakmilikPermohonan.luasDiluluskan" id="luasLulus" formatPattern="#,###,##0.0000"/> 
                            ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                            <s:hidden name="kodU" id="kodU" value="${actionBean.hakmilikPermohonan.kodUnitLuas.kod}"/>

                        </td>
                    </tr>
                    <tr>
                        <td><font color="red" size="4">*</font>Kategori</td>
                        <td> 
                            <s:select name="kategoriTanahBaru.kod" id="_kodKatTanah" value="${actionBean.hakmilikPermohonan.kategoriTanahBaru.kod}" onchange="filterKodGunaTanah();"> 
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" />
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td><font color="red" size="4">*</font>Kegunaan Tanah</td>
                        <td id="partialKodGunaTanah"></td>
                    </tr>
                    <tr>
                        <td><font color="red" size="4">*</font>Jenis Hakmilik :</td>
                        <td>
                            <s:select name="kodHakmilik" id="kodHakmilik" value="${actionBean.hakmilikPermohonan.kodHakmilik.kod}" onchange="doSomething(this.value);" >
                                <s:option value="0">--Sila Pilih--</s:option>
                                <s:option value="GRN">Geran (Pendaftar)</s:option>
                                <s:option value="PN">Pajakan Negeri (Pendaftar)</s:option> 
                                <s:option value="GM">Geran Mukim (Pejabat Tanah)</s:option>
                                <s:option value="PM">Pajakan Mukim (Pejabat Tanah)</s:option>
                            </s:select>
                        </td>
                    </tr>
                    <tr id="jikaPajakan">
                        <td><font color="red" size="4">*</font>Tempoh Pajakan:</td>
                        <td>
                            <s:select name="hakmilikPermohonan.tempohPajakan" id="tempohPajakan">
                                <s:option value="0">--Sila Pilih--</s:option>
                                <s:option value="30"> 30 </s:option>
                                <s:option value="60"> 60 </s:option>
                                <s:option value="99"> 99 </s:option>
                            </s:select>
                        </td>
                    </tr>                              
                    <tr>
                        <td><font color="red" size="4">*</font>Premium : </td>
                        <td>
                            <s:select name="keteranganKadarPremium" value="${actionBean.hakmilikPermohonan.keteranganKadarPremium}" id="nama" onchange="javaScript:showPremimTxt(this.value)">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${actionBean.senaraikodKadarPremium}" />
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td><font color="red" size="4">*</font>Hasil (RM) :</td>
                        <td>
                            <s:text name="hakmilikPermohonan.cukaiPerMeterPersegi" size="10"/>
                            <s:select name="hakmilikPermohonan.keteranganCukaiBaru" id="jenishasil">
                                <s:option value="0">--Sila Pilih--</s:option>
                                <s:option value="1"> Bagi setiap 100 meter persegi (Bangunan) </s:option>
                                <s:option value="2"> Kurang 5 Hektar (Pertanian) </s:option>
                                <s:option value="3"> Lebih 5 Hektar (Pertanian) </s:option>
                            </s:select> 
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <font color="red" size="4">*</font>Upah Ukur :
                        </td>
                        <td>
                            <s:radio name="hakmilikPermohonan.agensiUpahUkur" value="JUP" />&nbsp;Mengikut PU(A)438
                            <s:radio name="hakmilikPermohonan.agensiUpahUkur" value="JUB" />&nbsp;Juru Ukur Berlesen
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align:center;" colspan="2">      
                            <s:submit name="simpanSyarat" value="Simpan" class="btn" onclick="NoPrompt();"/>
                            <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                        </td>
                    </tr>
                </table>
            <legend>Syarat</legend>
            <table class="tablecloth" align="center">
                <tr>
                    <td>
                        <font color="red" size="4">*</font>Syarat Nyata :
                    </td>
                    <td>
                        <s:textarea name="hakmilikPermohonan.syaratNyataBaru.syarat" id="syaratNyata" rows="5" cols="80" readonly="yes" class="tooltips" value="${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}"></s:textarea>
                        <s:hidden name="disSyaratSekatan.kod" id="kod"/>                                    
                    </td>
                    <td style="vertical-align: middle;">
                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup('nyata')"/>  
                    </td>
                </tr>
                <tr>
                    <td>
                        <font color="red" size="4">*</font>Sekatan Kepentingan :
                    </td>
                    <td>
                        <s:textarea name="hakmilikPermohonan.sekatanKepentinganBaru.sekatan" id="syaratNyata1" rows="5" cols="80" readonly="yes" class="tooltips" value="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}"></s:textarea>
                        <s:hidden name="disSyaratSekatan.kodSktn" id="kodSktn"/>

                    </td>
                    <td style="vertical-align: middle;">
                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup('sekatan')"/>
                    </td>
                </tr>
            </table>            
        </c:when>
        <c:when test="${actionBean.kodNegeri eq '05'}">
            <table class="tablecloth" border="0">
                <c:if test="${actionBean.kelompok eq false}">
                    <tr>
                        <td>
                            Luas Disyorkan :
                        </td>
                        <td>
                            <s:format value="${actionBean.hakmilikPermohonan.luasTerlibat}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Luas Diluluskan :
                        </td>
                        <td>
                            <s:text name="hakmilikPermohonan.luasDiluluskan" formatPattern="#,###,##0.0000"/> 
                            ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                            <s:hidden name="kodU" id="kodU" value="${actionBean.hakmilikPermohonan.kodUnitLuas.kod}"/>

                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td><font color="red" size="4">*</font>Kategori</td>
                    <td> 
                        <s:select name="kategoriTanahBaru.kod" id="_kodKatTanah" value="${actionBean.hakmilikPermohonan.kategoriTanahBaru.kod}" onchange="filterKodGunaTanah();"> 
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" />
                        </s:select>
                    </td>
                </tr>
                <tr>
                    <td><font color="red" size="4">*</font>Kegunaan Tanah</td>
                    <td id="partialKodGunaTanah"></td>
                </tr>
                <tr>
                    <td><font color="red" size="4">*</font>Jenis Hakmilik :</td>
                    <td>
                        <s:select name="kodHakmilik" id="kodHakmilik" value="${actionBean.hakmilikPermohonan.kodHakmilik.kod}" onchange="doSomething(this.value);" >
                            <s:option value="0">--Sila Pilih--</s:option>
                            <s:option value="GRN">Geran (Pendaftar)</s:option>
                            <s:option value="PN">Pajakan Negeri (Pendaftar)</s:option> 
                            <s:option value="GM">Geran Mukim (Pejabat Tanah)</s:option>
                            <s:option value="PM">Pajakan Mukim (Pejabat Tanah)</s:option>
                        </s:select>
                    </td>
                </tr>
                <tr id="jikaPajakan">
                    <td><font color="red" size="4">*</font>Tempoh Pajakan:</td>
                    <td>
                        <s:select name="hakmilikPermohonan.tempohPajakan" id="tempohPajakan">
                            <s:option value="0">--Sila Pilih--</s:option>
                            <s:option value="30"> 30 </s:option>
                            <s:option value="60"> 60 </s:option>
                            <s:option value="99"> 99 </s:option>
                        </s:select>
                    </td>
                </tr>                              
                <tr>
                    <td><font color="red" size="4">*</font>Premium : </td>
                    <td>
                        <s:text name="amnt" size="20" formatPattern="#,###,##0.00" value="${actionBean.hakmilikPermohonan.kadarPremium}"/>
                    </td>
                </tr>
                <tr>
                    <td><font color="red" size="4">*</font>Hasil (RM) :</td>
                    <td>
                        <s:text name="hakmilikPermohonan.keteranganCukaiBaru" id="cukai" size="20" readonly="true"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <font color="red" size="4">*</font>Upah Ukur :
                    </td>
                    <td>
                        <s:radio name="hakmilikPermohonan.agensiUpahUkur" value="JUP" />&nbsp;Mengikut PU(A)438
                        <s:radio name="hakmilikPermohonan.agensiUpahUkur" value="JUB" />&nbsp;Juru Ukur Berlesen
                    </td>
                </tr>
                <tr>
                    <td style="text-align:center;" colspan="2">      
                        <s:submit name="simpanSyarat" value="Simpan" class="btn" onclick="NoPrompt();"/>
                        <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                    </td>
                </tr>
            </table>
            <legend>Syarat</legend>
            <table class="tablecloth" align="center">
                <tr>
                    <td>
                        <font color="red" size="4">*</font>Syarat Nyata :
                    </td>
                    <td>
                        <s:textarea name="hakmilikPermohonan.syaratNyataBaru.syarat" id="syaratNyata" rows="5" cols="80" readonly="yes" class="tooltips" value="${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}"></s:textarea>
                        <s:hidden name="disSyaratSekatan.kod" id="kod"/>                                    
                    </td>
                    <td style="vertical-align: middle;">
                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup('nyata')"/>  
                    </td>
                </tr>
                <tr>
                    <td>
                        <font color="red" size="4">*</font>Sekatan Kepentingan :
                    </td>
                    <td>
                        <s:textarea name="hakmilikPermohonan.sekatanKepentinganBaru.sekatan" id="syaratNyata1" rows="5" cols="80" readonly="yes" class="tooltips" value="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}"></s:textarea>
                        <s:hidden name="disSyaratSekatan.kodSktn" id="kodSktn"/>

                    </td>
                    <td style="vertical-align: middle;">
                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup('sekatan')"/>
                    </td>
                </tr>
            </table> 
        </c:when>
        <c:otherwise></c:otherwise>
    </c:choose>


</s:form>