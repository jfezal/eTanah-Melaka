<%--
    Document   : laporan_tanahV2
    Created on : Feb 20, 2012, 11:44 AM
    Author     : Shazwan
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<%-- carian hakmilik start--%>

<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<%-- carian hakmilik end--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    $(document).ready(function() {
        // carian hakmilik start
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'}), dialogInit('Carian Hakmilik'), $('#catatanKegunaan').hide();
        //carian hakmilik end

    <c:if test="${actionBean.catatanKeg ne null}">
        $('#catatanKegunaan').show();
    </c:if>
            /*
             * CUSTOM BY URUSAN   
             *  
             */
    <c:choose>
        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS'}">
            <c:if test="${actionBean.keg eq 'LN'}">
            $('#catatanKegunaan').show();
            </c:if>
            <c:if test="${actionBean.keg ne 'LN'}">
                    $('#catatanKegunaan').hide();
            </c:if>
        </c:when>
        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
            <c:if test="${actionBean.keg eq 'LN'}">
                $('#catatanKegunaan').show();
            </c:if>
            <c:if test="${actionBean.keg ne 'LN'}">
                    $('#catatanKegunaan').hide();
            </c:if>
        </c:when>
        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR'}">
            <c:if test="${actionBean.statBdnPngwl eq '3'}">$('#catatanPengawal').show();</c:if>
            <c:if test="${actionBean.statBdnPngwl ne '3'}">$('#catatanPengawal').hide();</c:if>
        </c:when>
        <c:otherwise>
                $('#catatanPengawal').hide();
        </c:otherwise>
    </c:choose>

            /*
             * actionBean.ksn
             */
    <c:if test="${actionBean.ksn ne null and !empty actionBean.ksn}">
        <c:choose>
            <c:when test="${actionBean.ksn eq 'SL'}">
                <c:choose>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS'}">
                        $('#plpssokong').show();
                        $('#plpstidaksokong').hide();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">
                            $('#pprusokong').show();
                            $('#pprutidaksokong').hide();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTMTA'}">
                            $('#pprusokong').show();
                            $('#pprutidaksokong').hide();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR'}">
                            $('#pptrsokong').show();
                            $('#pptrtidaksokong').hide();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PRMP'}">
                            $('#prmpsokong').show();
                            $('#prmptidaksokong').hide();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                            $('#lpspsokong').show();
                            $('#lpsptidaksokong').hide();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB'}">
                            $('#batuansokong').show();
                            $('#batuantidaksokong').hide();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTG'}">
                            $('#batuansokong').show();
                            $('#batuantidaksokong').hide();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTD'}">
                            $('#batuansokong').show();
                            $('#batuantidaksokong').hide();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTBTS'}">
                            $('#batuansokong').show();
                            $('#batuantidaksokong').hide();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTBTC'}">
                            $('#batuansokong').show();
                            $('#batuantidaksokong').hide();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT'}">
                            $('#batuansokong').show();
                            $('#batuantidaksokong').hide();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                        <c:if test="${actionBean.kodNegeri eq '05'}">
                            document.form.ksn[0].checked = true;
                            document.form.ksn[1].checked = false;
                            document.form.ksn[2].checked = false;
                            showjikasokong();
                        </c:if>
                    </c:when>
                    <c:otherwise>
                            $('#plpssokong').hide();
                            $('#plpstidaksokong').hide();
                            $('#pprusokong').hide();
                            $('#pprutidaksokong').hide();
                            $('#pptrsokong').hide();
                            $('#pptrtidaksokong').hide();
                            $('#prmpsokong').hide();
                            $('#prmptidaksokong').hide();
                            $('#lpspsokong').hide();
                            $('#lpsptidaksokong').hide();
                            $('#batuansokong').hide();
                            $('#batuantidaksokong').hide();
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:when test="${actionBean.ksn eq 'ST'}">
                <c:choose>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS'}">
                        $('#plpssokong').hide();
                        $('#plpstidaksokong').show();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">
                            $('#pprusokong').hide();
                            $('#pprutidaksokong').show();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTMTA'}">
                            $('#pprusokong').hide();
                            $('#pprutidaksokong').show();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR'}">
                            $('#pptrsokong').hide();
                            $('#pptrtidaksokong').show();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PRMP'}">
                            $('#prmpsokong').hide();
                            $('#prmptidaksokong').show();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                            $('#lpspsokong').hide();
                            $('#lpsptidaksokong').show();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB'}">
                            $('#batuansokong').hide();
                            $('#batuantidaksokong').show();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTG'}">
                            $('#batuansokong').hide();
                            $('#batuantidaksokong').show();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTD'}">
                            $('#batuansokong').hide();
                            $('#batuantidaksokong').show();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTBTC'}">
                            $('#batuansokong').hide();
                            $('#batuantidaksokong').show();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTBTS'}">
                            $('#batuansokong').hide();
                            $('#batuantidaksokong').show();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT'}">
                            $('#batuansokong').hide();
                            $('#batuantidaksokong').show();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                        <c:if test="${actionBean.kodNegeri eq '05'}">
                            document.form.ksn[0].checked = false;
                            document.form.ksn[1].checked = false;
                            document.form.ksn[2].checked = true;
                            showtidaksokong();
                        </c:if>
                    </c:when>
                    <c:otherwise>
                            $('#plpssokong').hide();
                            $('#plpstidaksokong').hide();
                            $('#pprusokong').hide();
                            $('#pprutidaksokong').hide();
                            $('#pptrsokong').hide();
                            $('#pptrtidaksokong').hide();
                            $('#prmpsokong').hide();
                            $('#prmptidaksokong').hide();
                            $('#lpspsokong').hide();
                            $('#lpsptidaksokong').hide();
                            $('#batuansokong').hide();
                            $('#batuantidaksokong').hide();
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:when test="${actionBean.ksn eq 'SU'}">
                <c:choose>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                        <c:if test="${actionBean.kodNegeri eq '05'}">
                        document.form.ksn[0].checked = false;
                        document.form.ksn[1].checked = true;
                        document.form.ksn[2].checked = false;
                        showpbmtsyorlulus();
                        </c:if>
                    </c:when>
                    <c:otherwise>

                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS'}">
                        $('#plpssokong').hide();
                        $('#plpstidaksokong').hide();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">
                            $('#pprusokong').hide();
                            $('#pprutidaksokong').hide();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR'}">
                            $('#pptrsokong').hide();
                            $('#pptrtidaksokong').hide();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PRMP'}">
                            $('#prmpsokong').hide();
                            $('#prmptidaksokong').hide();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                            $('#lpspsokong').hide();
                            $('#lpsptidaksokong').hide();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB'}">
                            $('#batuansokong').hide();
                            $('#batuantidaksokong').hide();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTG'}">
                            $('#batuansokong').hide();
                            $('#batuantidaksokong').hide();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTD'}">
                            $('#batuansokong').hide();
                            $('#batuantidaksokong').hide();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTBTS'}">
                            $('#batuansokong').hide();
                            $('#batuantidaksokong').hide();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTBTC'}">
                            $('#batuansokong').hide();
                            $('#batuantidaksokong').hide();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT'}">
                            $('#batuansokong').hide();
                            $('#batuantidaksokong').hide();
                    </c:when>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                        <c:if test="${actionBean.kodNegeri eq '05'}">
                            document.form.ksn[0].checked = false;
                            document.form.ksn[1].checked = false;
                            document.form.ksn[2].checked = false;
                        </c:if>
                    </c:when>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </c:if>
            /*
             * HAKMILIK PERMOHONAN
             */
    <c:if test="${actionBean.hakmilikPermohonan ne null}">
            /*
             * KOD MILIK
             */
        <c:if test="${actionBean.hakmilikPermohonan.kodMilik ne null}">
            <c:if test="${actionBean.hakmilikPermohonan.kodMilik eq 'K' }">
            $('#tanahkerajaan').show();
            $('#jikasebab').hide();
                <c:if test="${actionBean.laporanTanah ne null}">
                    <c:if test="${!empty actionBean.laporanTanah.bolehBerimilik}">
                        <c:if test="${actionBean.laporanTanah.bolehBerimilik eq 'T'}">
            $('#jikasebab').show();
                        </c:if>
                        <c:if test="${actionBean.laporanTanah.bolehBerimilik ne 'T'}">
                                $('#jikasebab').hide();
                        </c:if>
                    </c:if>
                </c:if>
            </c:if>
            <c:if test="${actionBean.hakmilikPermohonan.kodMilik ne 'K' }">
                    $('#tanahkerajaan').hide();
                    $('#jikasebab').hide();
            </c:if>
            <c:if test="${actionBean.hakmilikPermohonan.kodMilik eq 'H' }">
                    $('#carianHakmilik').show();
                    $('#jikasebab').hide();
            </c:if>
            <c:if test="${actionBean.hakmilikPermohonan.kodMilik ne 'H' }">
                    $('#carianHakmilik').hide();
            </c:if>
            <c:if test="${actionBean.hakmilikPermohonan.kodMilik eq 'R' }">
                    $('#jenisRizab').show();
                    $('#jenisRizabno').show();
                    $('#jikasebab').hide();
            </c:if>
            <c:if test="${actionBean.hakmilikPermohonan.kodMilik ne 'R' }">
                    $('#jenisRizab').hide();
                    $('#jenisRizabno').hide();
            </c:if>
        </c:if>
                /*
                 * END 
                 */
                /*
                 * PARLIMEN DAN DUN
                 */
        <c:if test="${actionBean.hakmilikPermohonan.kodKawasanParlimen ne null}">
            <c:if test="${actionBean.hakmilikPermohonan.kodKawasanParlimen.kod eq 'P134'}">
                $('#mT').show();
                $('#aG').hide();
                $('#tB').hide();
                $('#bK').hide();
                $('#kM').hide();
                $('#jS').hide();
            </c:if>
            <c:if test="${actionBean.hakmilikPermohonan.kodKawasanParlimen.kod eq 'P135'}">
                    $('#aG').show();
                    $('#mT').hide();
                    $('#tB').hide();
                    $('#bK').hide();
                    $('#kM').hide();
                    $('#jS').hide();
            </c:if>
            <c:if test="${actionBean.hakmilikPermohonan.kodKawasanParlimen.kod eq 'P136'}">
                    $('#tB').show();
                    $('#aG').hide();
                    $('#mT').hide();
                    $('#bK').hide();
                    $('#kM').hide();
                    $('#jS').hide();
            </c:if>
            <c:if test="${actionBean.hakmilikPermohonan.kodKawasanParlimen.kod eq 'P137'}">
                    $('#bK').show();
                    $('#tB').hide();
                    $('#aG').hide();
                    $('#mT').hide();
                    $('#kM').hide();
                    $('#jS').hide();
            </c:if>
            <c:if test="${actionBean.hakmilikPermohonan.kodKawasanParlimen.kod eq 'P138'}">
                    $('#kM').show();
                    $('#bK').hide();
                    $('#tB').hide();
                    $('#aG').hide();
                    $('#mT').hide();
                    $('#jS').hide();
            </c:if>
            <c:if test="${actionBean.hakmilikPermohonan.kodKawasanParlimen.kod eq 'P139'}">
                    $('#jS').show();
                    $('#kM').hide();
                    $('#bK').hide();
                    $('#tB').hide();
                    $('#aG').hide();
                    $('#mT').hide();
            </c:if>

            <c:if test="${actionBean.hakmilikPermohonan.kodDUN ne null}">
                <c:if test="${actionBean.hakmilikPermohonan.kodKawasanParlimen.kod eq 'P134'}">
                    $('#kodDmT').val('${actionBean.hakmilikPermohonan.kodDUN.kod}');
                </c:if>
                <c:if test="${actionBean.hakmilikPermohonan.kodKawasanParlimen.kod eq 'P135'}">
                        $('#kodDaG').val('${actionBean.hakmilikPermohonan.kodDUN.kod}');
                </c:if>
                <c:if test="${actionBean.hakmilikPermohonan.kodKawasanParlimen.kod eq 'P136'}">
                        $('#kodDtB').val('${actionBean.hakmilikPermohonan.kodDUN.kod}');
                </c:if>
                <c:if test="${actionBean.hakmilikPermohonan.kodKawasanParlimen.kod eq 'P137'}">
                        $('#kodDbK').val('${actionBean.hakmilikPermohonan.kodDUN.kod}');
                </c:if>
                <c:if test="${actionBean.hakmilikPermohonan.kodKawasanParlimen.kod eq 'P138'}">
                        $('#kodDkM').val('${actionBean.hakmilikPermohonan.kodDUN.kod}');
                </c:if>
                <c:if test="${actionBean.hakmilikPermohonan.kodKawasanParlimen.kod eq 'P139'}">
                        $('#kodDjS').val('${actionBean.hakmilikPermohonan.kodDUN.kod}');
                </c:if>
            </c:if>
            <c:if test="${actionBean.hakmilikPermohonan.kodKawasanParlimen.kod ne 'P134' 
                          and actionBean.hakmilikPermohonan.kodKawasanParlimen.kod ne 'P135'
                          and actionBean.hakmilikPermohonan.kodKawasanParlimen.kod ne 'P136'
                          and actionBean.hakmilikPermohonan.kodKawasanParlimen.kod ne 'P137'
                          and actionBean.hakmilikPermohonan.kodKawasanParlimen.kod ne 'P138'
                          and actionBean.hakmilikPermohonan.kodKawasanParlimen.kod ne 'P139'}">
                    $('#jS').hide();
                    $('#kM').hide();
                    $('#bK').hide();
                    $('#tB').hide();
                    $('#aG').hide();
                    $('#mT').hide();
            </c:if>
        </c:if>
                /*
                 * END
                 */
    </c:if>
    <c:if test="${actionBean.hakmilikPermohonan.kodKawasanParlimen eq null}">
            $('#jS').hide();
            $('#kM').hide();
            $('#bK').hide();
            $('#tB').hide();
            $('#aG').hide();
            $('#mT').hide();
    </c:if>

            /*
             * LAPORAN_TANAH
             */
    <c:if test="${actionBean.laporanTanah ne null}">
        <c:if test="${!empty actionBean.laporanTanah.usaha}">
            <c:if test="${actionBean.laporanTanah.usaha eq 'Y'}">
            $('#pengusuka').show();
            $('#tarikh').show();
            $('#laterbelaktable').show();
            </c:if>
            <c:if test="${actionBean.laporanTanah.usaha ne 'Y'}">
                    $('#pengusuka').hide();
                    $('#tarikh').hide();
                    $('#laterbelaktable').hide();
            </c:if>
        </c:if>
        <c:if test="${actionBean.laporanTanah.kodKeadaanTanah ne null}">
            <c:if test="${actionBean.laporanTanah.kodKeadaanTanah.kod eq 'LL'}">
                $('#keadaantanahlainlain').show();
            </c:if>
            <c:if test="${actionBean.laporanTanah.kodKeadaanTanah.kod ne 'LL'}">
                    $('#keadaantanahlainlain').hide();
            </c:if>
        </c:if>
        <c:if test="${actionBean.laporanTanah.kodKeadaanTanah eq null}">
                $('#keadaantanahlainlain').hide();
        </c:if>
        <c:if test="${!empty actionBean.laporanTanah.adaBangunan}">
            <c:if test="${actionBean.laporanTanah.adaBangunan eq 'Y'}">
                $('#Bangunantable').show();
            </c:if>
            <c:if test="${actionBean.laporanTanah.adaBangunan ne 'Y'}">
                    $('#Bangunantable').hide();
            </c:if>
        </c:if>
        <c:if test="${actionBean.laporanTanah.kecerunanTanah ne null}">
            <c:choose>
                <c:when test="${actionBean.laporanTanah.kecerunanTanah.kod eq 'RT'}">
                    $('#tinggi').hide();
                    $('#cerun').hide();
                    $('#dalam').hide();
                </c:when>
                <c:when test="${actionBean.laporanTanah.kecerunanTanah.kod eq 'BK'}">
                        $('#tinggi').hide();
                        $('#cerun').show();
                        $('#dalam').hide();
                </c:when>
                <c:when test="${actionBean.laporanTanah.kecerunanTanah.kod eq 'TG'}">
                        $('#tinggi').show();
                        $('#cerun').hide();
                        $('#dalam').hide();
                </c:when>
                <c:when test="${actionBean.laporanTanah.kecerunanTanah.kod eq 'RD'}">
                        $('#tinggi').hide();
                        $('#cerun').show();
                        $('#dalam').hide();
                </c:when>
                <c:when test="${actionBean.laporanTanah.kecerunanTanah.kod eq 'CR'}">
                        $('#tinggi').hide();
                        $('#cerun').show();
                        $('#dalam').hide();
                </c:when>
                <c:when test="${actionBean.laporanTanah.kecerunanTanah.kod eq 'PY'}">
                        $('#tinggi').hide();
                        $('#cerun').hide();
                        $('#dalam').show();
                </c:when>
            </c:choose>
        </c:if>
    </c:if>
    <c:if test="${actionBean.laporanTanah eq null}">
            $('#Bangunantable').hide();
            $('#keadaantanahlainlain').hide();
            $('#pengusuka').hide();
            $('#tarikh').hide();
            $('#laterbelaktable').hide();
    </c:if>

            if ($('#adaBangunan').val() == 'Y')
                $('#bilanganBangunan').show();

            /*
             * FASA PERMOHONAN
             */
    <c:if test="${actionBean.fasaPermohonan ne null}">
        <c:if test="${actionBean.fasaPermohonan.keputusan ne null}">
            <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL'}">
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
            $('#jikasokong').show();
            $('#tidaksokong').hide();
            $('#pbmtsyorlulus').hide();
                </c:if>
            </c:if>
            <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'ST'}">
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                    $('#tidaksokong').show();
                    $('#jikasokong').hide();
                    $('#pbmtsyorlulus').hide();
                </c:if>
            </c:if>
            <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'SU'}">
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                    $('#pbmtsyorlulus').show();
                    $('#tidaksokong').hide();
                    $('#jikasokong').hide();
                </c:if>
            </c:if>
            <c:if test="${actionBean.fasaPermohonan.keputusan.kod ne 'SL'
                          and actionBean.fasaPermohonan.keputusan.kod ne 'ST'
                          and actionBean.fasaPermohonan.keputusan.kod ne 'SU'}">
                    $('#tidaksokong').hide();
                    $('#jikasokong').hide();
                    $('#pbmtsyorlulus').hide();
            </c:if>
        </c:if>
        <c:if test="${actionBean.fasaPermohonan.keputusan eq null}">
                $('#pbmtsyorlulus').hide();
                $('#jikasokong').hide();
                $('#pbmtsyorlulus').hide();
        </c:if>
    </c:if>
            /*
             * MOHON LAPOR PELAN
             */
    <c:if test="${actionBean.permohonanLaporanPelan ne null}">
        <c:if test="${actionBean.permohonanLaporanPelan.kodTanah ne null}">
            <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod eq '99'}">
            $('#jenistanahlainlain').show();
            </c:if>
            <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod ne '99'}">
                    $('#jenistanahlainlain').hide();
            </c:if>
        </c:if>
    </c:if>
    <c:if test="${actionBean.permohonanLaporanPelan eq null}">
            $('#jenistanahlainlain').hide();
    </c:if>

            $('#premiumTxt').hide();
            $('#jenisRizab1').hide();
            $('#noWarta').hide();
            $('#nyataRancangan').hide();
            $('#tanahMilik').hide();
            $('#tujuan').hide();
            $('#catatanTanahMilik').hide();
            $('#catatanPBT').hide();

    <c:if test="${actionBean.hakmilikPermohonan ne null}">
        <c:if test="${!empty actionBean.hakmilikPermohonan.statusLuasDiluluskan}">
            <c:if test="${actionBean.hakmilikPermohonan.statusLuasDiluluskan eq 'S'}">
            $('#luassbhgn').show();
            </c:if>
            <c:if test="${actionBean.hakmilikPermohonan.statusLuasDiluluskan ne 'S'}">
                    $('#luassbhgn').hide();
            </c:if>
        </c:if>
        <c:if test="${empty actionBean.hakmilikPermohonan.statusLuasDiluluskan}">
                $('#luassbhgn').hide();
        </c:if>
    </c:if>
        });

        function reload(val) {
            doBlockUI();
            var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?reload&idHakmilik=' + val;
            $.ajax({
                type: "GET",
                url: url,
                dataType: 'html',
                error: function(xhr, ajaxOptions, thrownError) {
                    alert("error=" + xhr.responseText);
                    doUnBlockUI();
                },
                success: function(data) {
                    $('#page_div').html(data);
                    doUnBlockUI();
                }
            });
        }

        function reloadPT(val) {
            doBlockUI();
            var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?reload&noPtSementara=' + val;
            $.ajax({
                type: "GET",
                url: url,
                dataType: 'html',
                error: function(xhr, ajaxOptions, thrownError) {
                    alert("error=" + xhr.responseText);
                    doUnBlockUI();
                },
                success: function(data) {
                    $('#page_div').html(data);
                    doUnBlockUI();
                }
            });
        }

        function doSetDokumenHakmilik() {
            var idDokumen = document.getElementById("hmImej").options[document.getElementById("hmImej").selectedIndex].value;
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }

        function openImage(type, i, imageU) {
            var idDokumen;
            if (type == 'U')
                idDokumen = document.getElementById("imejU" + i).options[document.getElementById("imejU" + i).selectedIndex].value;
            else if (type == 'S')
                idDokumen = document.getElementById("imejS" + i).options[document.getElementById("imejS" + i).selectedIndex].value;
            else if (type == 'T')
                idDokumen = document.getElementById("imejT" + i).options[document.getElementById("imejT" + i).selectedIndex].value;
            else if (type == 'B')
                idDokumen = document.getElementById("imejB" + i).options[document.getElementById("imejB" + i).selectedIndex].value;
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }

        function openFrame(type) {
//            doBlockUI();
            var idHakmilik = $('#idHakmilik').val();
            var noPtSementara = $('#noPtSementara').val();
            //        alert(idHakmilik);
            window.open("${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?openFrame&idHakmilik="
                    + idHakmilik + "&noPtSementara=" + noPtSementara + "&type=" + type, "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
            //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
        }

        function refreshV2(type) {
            var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?refreshpage&type=' + type;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
            doUnBlockUI();
        }

        function refreshV2ManyHakmilik(type, hakmilik) {
            var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?refreshpage&type=' + type + '&idHakmilik=' + hakmilik;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
            doUnBlockUI();
        }

        //Add for charting function
        function ReplaceAll(Source, stringToFind, stringToReplace) {
            var temp = Source;
            var index = temp.indexOf(stringToFind);
            while (index != -1) {
                temp = temp.replace(stringToFind, stringToReplace);
                index = temp.indexOf(stringToFind);

            }
            return temp;
        }

        function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {

            var strNama2 = ReplaceAll(strNama, " ", "_");
            var strJawatan2 = ReplaceAll(strJawatan, " ", "_");
    <%--var strStageID2 = "gis_lim";--%>
            var strStageID2 = "g_laporan_tanah";
            var objShell = new ActiveXObject("WScript.Shell");
            var sysVar = objShell.Environment("System");

            objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " + strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
        }

        function RunProgramSemak(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {

            var strNama2 = ReplaceAll(strNama, " ", "_");
            var strJawatan2 = ReplaceAll(strJawatan, " ", "_");
    <%--var strStageID2 = "gis_lim";--%>
            var strStageID2 = "g_semak_permohonan";
            var objShell = new ActiveXObject("WScript.Shell");
            var sysVar = objShell.Environment("System");

            objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " + strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
        }
</script>
<s:form beanclass="etanah.view.stripes.pelupusan.LaporanTanahV2PelupusanActionBean" name="form">
    <s:errors/>
    <s:messages/>
    <s:hidden name="idHakmilik" id="idHakmilik"/>
    <s:hidden name="noPtSementara" id="noPtSementara"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Senarai Hakmilik Terlibat</legend>
            <div align="center">
                <c:if test="${hmListCount > 1}">
                    <p>
                        <font size="2" color="red">
                        <b>Permohonan Melibatkan Banyak Hakmilik</b>
                        </font>
                    </p>
                </c:if>
            </div>
            <div align="center">
                <c:if test="${actionBean.hakmilikPermohonan.hakmilik ne null}">
                    <font color="#003194" style="font-weight: bold; font-family:Tahoma; font-size: 13px;">
                    Hakmilik :
                    </font>
                </c:if>
                <c:if test="${actionBean.hakmilikPermohonan.hakmilik eq null}">
                    <font color="#003194" style="font-weight: bold; font-family:Tahoma; font-size: 13px;">
                    &nbsp;
                    </font>
                </c:if>
                <c:if test="${edit}">
                    <c:if test="${actionBean.hakmilikPermohonan.hakmilik ne null}">
                        <s:select name="idHakmilik" onchange="reload(this.value);" id="hakmilik">
                            <c:forEach items="${actionBean.senaraiHakmilikPermohonan}" var="item" varStatus="line">
                                <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                                    ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                                </s:option>
                            </c:forEach>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.hakmilikPermohonan.hakmilik eq null}">
                        <c:set var="i" value="1" />
                        <s:select name="noPtSementara" onchange="reloadPT(this.value);" id="hakmilik">
                            <c:forEach items="${actionBean.senaraiNoPt}" var="item" varStatus="line">
                                <s:option value="${item.noPtSementara}" style="width:400px">
                                    Laporan Tanah${i}
                                </s:option>
                                <c:set var="i" value="${i+1}" />
                            </c:forEach>
                        </s:select>
                    </c:if>
                </c:if>
                <c:if test="${!edit}">
                    <c:if test="${actionBean.hakmilikPermohonan.hakmilik ne null}">
                        <s:select name="idHakmilik" onchange="reload(this.value);" id="hakmilik">
                            <c:forEach items="${actionBean.senaraiHakmilikPermohonan}" var="item" varStatus="line">
                                <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                                    ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                                </s:option>
                            </c:forEach>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.hakmilikPermohonan.hakmilik eq null}">
                        <c:if test="${actionBean.kodNegeri eq '05'}">
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTBTC' && actionBean.permohonan.kodUrusan.kod ne 'PTBTS'}">
                                <c:set var="i" value="1" />
                                <s:select name="noPtSementara" onchange="reloadPT(this.value);" id="hakmilik">
                                    <c:forEach items="${actionBean.senaraiNoPt}" var="item" varStatus="line">
                                        <s:option value="${item.noPtSementara}" style="width:400px">
                                            Laporan Tanah ${i}
                                        </s:option>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </s:select>
                            </c:if>
                        </c:if>
                        <c:if test="${actionBean.kodNegeri eq '04'}">
                            <c:if test="${actionBean.hakmilikPermohonan.hakmilik eq null}">
                                <s:select name="noPtSementara" onchange="reloadPT(this.value);" id="noPtSementara">
                                    <c:forEach items="${actionBean.senaraiHakmilikPermohonan}" var="item" varStatus="line">
                                        <s:option value="${item.id}" style="width:400px">
                                            Lot( ${item.noLot})
                                        </s:option>
                                    </c:forEach>
                                </s:select>
                            </c:if>
                        </c:if>

                       
                        <c:if test="${actionBean.kodNegeri eq '05'}">
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTBTC' || actionBean.permohonan.kodUrusan.kod eq 'PTBTS'}">
                                <c:set var="i" value="1" />
                                <s:select name="noPtSementara" onchange="reloadPT(this.value);" id="hakmilik">
                                    <s:option value="" style="width:400px">
                                        Laporan Tanah
                                    </s:option>
                                </s:select>
                            </c:if>
                        </c:if>

                    </c:if>                
                </c:if>
            </div>
        </fieldset>        
        <br/>
        <c:if test="${actionBean.flag}">
            <div class="subtitle">
                <fieldset class="aras1">
                    <c:choose>
                        <c:when test="${actionBean.stageId eq 'laporan_tanah' || actionBean.stageId eq 'g_laporan_tanah'}">
                            <legend>LIM</legend>
                            <center>
                                <br/>
                                <p>
                                    <s:button name="lim" id="chart" value="LIM" class="btn" onclick="RunProgram('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                                </p>
                            </center>
                        </c:when>
                        <c:when test="${actionBean.stageId eq '04SediaLaporanTanah'}">
                            <legend>LIM</legend>
                            <center>
                                <br/>
                                <p>
                                    <s:button name="lim" id="chart" value="LIM" class="btn" onclick="RunProgram('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                                </p>
                            </center>
                        </c:when>
                        <c:when test="${actionBean.stageId eq '04SediaLaporan'}">
                            <legend>LIM</legend>
                            <center>
                                <br/>
                                <p>
                                    <s:button name="lim" id="chart" value="LIM" class="btn" onclick="RunProgram('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                                </p>
                            </center>
                        </c:when>
                        <c:when test="${actionBean.stageId eq 'sedia_laporan_tanahptg'}">
                            <legend>LIM</legend>
                            <center>
                                <br/>
                                <p>
                                    <s:button name="lim" id="chart" value="LIM" class="btn" onclick="RunProgram('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                                </p>
                            </center>
                        </c:when>
                        <c:when test="${actionBean.stageId eq 'sedia_draf_jkbb'}">
                            <legend>LIM</legend>
                            <center>
                                <br/>
                                <p>
                                    <s:button name="lim" id="chart" value="LIM" class="btn" onclick="RunProgram('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                                </p>
                            </center>
                        </c:when>
                        <c:when test="${actionBean.stageId eq '06LaporanTanah'}">
                            <legend>LIM</legend>
                            <center>
                                <br/>
                                <p>
                                    <s:button name="lim" id="chart" value="LIM" class="btn" onclick="RunProgram('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                                </p>
                            </center>
                        </c:when>
                        <c:otherwise>
                            <legend>Semak LIM</legend>
                            <center>
                                <br/>
                                <p>
                                    <s:button name="lim" id="chart" value="Semak LIM" class="btn" onclick="RunProgramSemak('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                                </p>
                            </center>
                        </c:otherwise>
                    </c:choose>
                </fieldset>
            </div>
        </c:if>
        <br/>
        <fieldset class="aras1">
            <legend>Perihal Tanah
                <span style="float:right">
                    <%--<a onclick="viewPerihalTanah();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%> 
                    <c:if test="${actionBean.disLaporanTanahController.pTanah}">
                        <a onclick="openFrame('pTanah');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                        </c:if>                    
                </span>
            </legend>
            <div id="perihaltanah">
                <div class="content" align="center">
                    <c:if test="${actionBean.kodP eq 'K'}">
                        Tanah Kerajaan
                    </c:if>
                    <c:if test="${actionBean.kodP eq 'H'}">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT'}">
                            Tanah Hakmilik Permukaan
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'BMBT'}">
                            Tanah Hakmilik 
                        </c:if>                        
                    </c:if>
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="/pelupusan/laporan_tanahV2">
                        <c:if test="${line.hakmilikPermohonan.hakmilik ne null}">
                            <display:column title="No" sortable="true">${line_rowNum}
                                <s:hidden name="x" class="x${line_rowNum -1}" value="${line.hakmilikPermohonan.id}"/></display:column>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTBTC' && actionBean.permohonan.kodUrusan.kod ne 'PTBTS'}">
                                <display:column title="ID Hakmilik" >${line.hakmilikPermohonan.hakmilik.idHakmilik}</display:column>
                            </c:if>
                            <display:column title="No.Lot/PT">
                                <c:if test="${line.hakmilikPermohonan.noLot eq null}">
                                    ${line.hakmilikPermohonan.hakmilik.noLot}
                                </c:if>
                                <c:if test="${line.hakmilikPermohonan.noLot ne null}">
                                    ${line.hakmilikPermohonan.noLot}
                                </c:if>
                            </display:column>
                            <display:column title="Bandar/Pekan/Mukim">
                                <c:if test="${line.hakmilikPermohonan.bandarPekanMukimBaru.nama eq null}">
                                    ${line.hakmilikPermohonan.hakmilik.bandarPekanMukim.nama}
                                </c:if>
                                <c:if test="${line.hakmilikPermohonan.noLot ne null}">
                                    ${line.hakmilikPermohonan.bandarPekanMukimBaru.nama}
                                </c:if>                                
                            </display:column>
                            <display:column title="Seksyen">
                                <c:if test="${line.hakmilikPermohonan.kodSeksyen.nama eq null}">
                                    ${line.hakmilikPermohonan.hakmilik.seksyen.nama}
                                </c:if>
                                <c:if test="${line.hakmilikPermohonan.kodSeksyen.nama ne null}">
                                    ${line.hakmilikPermohonan.kodSeksyen.nama}
                                </c:if>
                            </display:column>
                            <display:column title="Daerah">
                                <c:if test="${line.hakmilikPermohonan.bandarPekanMukimBaru.cawangan.daerah.nama eq null}">
                                    ${line.hakmilikPermohonan.hakmilik.bandarPekanMukim.daerah.nama}
                                </c:if>
                                <c:if test="${line.hakmilikPermohonan.bandarPekanMukimBaru.cawangan.daerah.nama ne null}">
                                    ${line.hakmilikPermohonan.bandarPekanMukimBaru.daerah.nama}
                                </c:if>
                            </display:column>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT' || actionBean.permohonan.kodUrusan.kod eq 'PTBTC' || actionBean.permohonan.kodUrusan.kod eq 'PTBTS'}">
                                <display:column title="Luas Tanah Permukaan"><s:format formatPattern="#,###,##0.0000" value="${line.hakmilikPermohonan.hakmilik.luas}"/> ${line.hakmilikPermohonan.hakmilik.kodUnitLuas.nama} </display:column>
                                <display:column title="Luas Tanah Bawah Dipohon"><s:format formatPattern="#,###,##0.0000" value="${line.hakmilikPermohonan.luasTerlibat}"/> ${line.hakmilikPermohonan.kodUnitLuas.nama} </display:column>
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'BMBT' && actionBean.permohonan.kodUrusan.kod ne 'PTBTC' && actionBean.permohonan.kodUrusan.kod ne 'PTBTS'}">
                                <display:column title="Luas Dipohon"><s:format formatPattern="#,###,##0.0000" value="${line.hakmilikPermohonan.luasTerlibat}"/> ${line.hakmilikPermohonan.kodUnitLuas.nama} </display:column>
                            </c:if>
                        </c:if>

                        <c:if test="${line.hakmilikPermohonan.hakmilik eq null}">
                            <display:column title="No" sortable="true">${line_rowNum}
                                <s:hidden name="x" class="x${line_rowNum -1}" value="${line.hakmilikPermohonan.id}"/></display:column>
                            <display:column title="No.Lot/PT" >${line.hakmilikPermohonan.noLot}</display:column>
                            <display:column title="Bandar/Pekan/Mukim" >${line.hakmilikPermohonan.bandarPekanMukimBaru.nama}</display:column>
                            <display:column title="Seksyen" >${line.hakmilikPermohonan.kodSeksyen.nama}</display:column>
                            <display:column title="Daerah" >${line.hakmilikPermohonan.bandarPekanMukimBaru.daerah.nama}</display:column>
                            <display:column title="Luas Dipohon"><s:format formatPattern="#,###,##0.0000" value="${line.hakmilikPermohonan.luasTerlibat}"/> ${line.hakmilikPermohonan.kodUnitLuas.nama}  </display:column>
                        </c:if>
                    </display:table>        
                    <br>
                    <c:if test="${actionBean.hakmilikPermohonanList[0].hakmilikPermohonan.hakmilik ne null}">
                        <table class="tablecloth" border="0">
                            <tr>
                                <td>
                                    Status Tanah :
                                </td>
                                <td>
                                    ${actionBean.hakmilikPermohonan.kodMilik.nama}&nbsp;
                                </td>
                            </tr>
                            <c:if test="${actionBean.hakmilikPermohonan.kodMilik ne null}">
                                <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'K'}">
                                    <tr>
                                        <td>
                                            Tanah Kerajaan Boleh diberimilik :
                                        </td>
                                        <td>
                                            <c:if test="${actionBean.laporanTanah.bolehBerimilik eq 'Y'}">
                                                Ya &nbsp;
                                            </c:if>
                                            <c:if test="${actionBean.laporanTanah.bolehBerimilik eq 'T'}">
                                                Tidak &nbsp;
                                            </c:if>
                                        </td>
                                    </tr>
                                    <c:if test="${actionBean.laporanTanah.bolehBerimilik eq 'T'}">
                                        <tr>
                                            <td>
                                                Jika Tidak Boleh diberimilik, sebab :
                                            </td>
                                            <td>
                                                ${actionBean.laporanTanah.sebabTidakBolehBerimilik}&nbsp;
                                            </td>
                                        </tr>
                                    </c:if>                            
                                </c:if>
                            </c:if>
                            <!--            
                            <c:if test="${actionBean.hakmilikPermohonan.kodMilik ne null}">
                                <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'H'}">
                                    <tr>
                                        <td colspan="2">
                                    <display:table  name="${actionBean.senaraiHakmilikPerihalTanah}" id="line" class="tablecloth">
                                        <display:column title="ID Hakmilik">
                                            <c:if test="${line.hakmilik.kodHakmilik.nama ne null}"> ${line.hakmilik.kodHakmilik.nama}&nbsp; </c:if>
                                            <c:if test="${line.hakmilik.kodHakmilik.nama eq null}"> Tiada Data </c:if>
                                        </display:column>

                                        <display:column title="Jenis Hakmilik">
                                            <c:if test="${line.hakmilik.idHakmilik ne null}"> ${line.hakmilik.idHakmilik}&nbsp; </c:if>
                                            <c:if test="${line.hakmilik.idHakmilik eq null}"> Tiada Data </c:if>
                                        </display:column>

                                        <display:column title="No Lot" >
                                            <c:if test="${line.hakmilik.noLot ne null}"> ${line.hakmilik.noLot}&nbsp; </c:if>
                                            <c:if test="${line.hakmilik.noLot eq null}"> Tiada Data </c:if>

                                        </display:column>

                                        <display:column title="Luas">
                                            <c:if test="${line.hakmilik.luas ne null}"> <fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</c:if>
                                            <c:if test="${line.hakmilik.luas eq null}"> Tiada Data </c:if>
                                        </display:column>

                                        <display:column property="hakmilik.kategoriTanah.nama" title="Kegunaan" >
                                            <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot ne null}"> ${actionBean.laporanTanah.sempadanUtaraNoLot}&nbsp; </c:if>
                                            <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot eq null}"> Tiada Data </c:if>
                                        </display:column>

                                        <display:column title="Cukai (RM)">
                                            <c:if test="${line.hakmilik.cukai ne null}"> <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukai}"/>&nbsp; </c:if>
                                            <c:if test="${line.hakmilik.cukai eq null}"> Tiada Data </c:if>
                                        </display:column>
                                    </display:table>      
                                </td>
                            </tr>
                                </c:if>
                            </c:if>-->
                            <c:if test="${actionBean.hakmilikPermohonan.kodMilik ne null}">
                                <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'R'}">
                                    <tr>
                                        <td>
                                            Jenis Rizab :
                                        </td>
                                        <td>
                                            ${actionBean.tanahrizabpermohonan1.rizab.nama} &nbsp;
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            No. Warta Kerajaan :
                                        </td>
                                        <td>
                                            ${actionBean.tanahrizabpermohonan1.noWarta}&nbsp;
                                        </td>
                                    </tr>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR'}">
                                        <tr>
                                            <td>
                                                Tarikh Warta:
                                            </td>
                                            <td>
                                                <s:format formatPattern="dd/MM/yyyy" value="${actionBean.tanahrizabpermohonan1.tarikhWarta}"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                No Pelan Warta:
                                            </td>
                                            <td>
                                                ${actionBean.tanahrizabpermohonan1.noPW}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                Pengawal:
                                            </td>
                                            <td>
                                                ${actionBean.tanahrizabpermohonan1.penjaga}
                                            </td>
                                        </tr>
                                    </c:if>

                                </c:if>                            
                            </c:if>
                            <tr>
                                <td>
                                    Jenis Tanah :
                                </td>
                                <td>
                                    ${actionBean.permohonanLaporanPelan.kodTanah.nama} &nbsp;
                                </td>
                            </tr> 
                            <c:if test="${actionBean.permohonanLaporanPelan.kodTanah ne null}">
                                <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod eq '99'}">
                                    <tr>
                                        <td>Lain-lain :
                                        </td>
                                        <td>${actionBean.kand}&nbsp;
                                        </td>
                                    </tr>
                                </c:if>
                            </c:if>
                            <tr>
                                <td>
                                    Kawasan Parlimen :
                                </td>
                                <td>
                                    ${actionBean.hakmilikPermohonan.kodKawasanParlimen.nama}&nbsp;

                                </td>
                            </tr>
                            <tr>
                                <td>
                                    DUN :
                                </td>
                                <td>
                                    <%---   ${actionBean.hakmilikPermohonan.kodDUN}&nbsp; --%>
                                    ${actionBean.hakmilikPermohonan.kodDUN.nama}&nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Kedudukan Tanah :
                                </td>
                                <td>
                                    ${actionBean.hakmilikPermohonan.lokasi}&nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Muatnaik Gambar Tanah :
                                </td>
                                <td>
                                    <s:select name="hmImej" style="width:300px;" id="hmImej" onchange="doSetDokumenHakmilik();">
                                        <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                        <s:options-collection collection="${actionBean.hakmilikImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                                    </s:select>
                                </td>
                            </tr>
                        </table>
                    </c:if>
                    <c:if test="${actionBean.hakmilikPermohonanList[0].hakmilikPermohonan.hakmilik eq null}">
                        <table class="tablecloth" border="0">
                            <tr>
                                <td>
                                    Status Tanah :
                                </td>
                                <td>
                                    ${actionBean.hakmilikPermohonanList[0].hakmilikPermohonan.kodMilik.nama}&nbsp;
                                </td>
                            </tr>
                            <c:if test="${actionBean.hakmilikPermohonanList[0].hakmilikPermohonan.kodMilik ne null}">
                                <c:if test="${actionBean.hakmilikPermohonanList[0].hakmilikPermohonan.kodMilik.kod eq 'K'}">
                                    <tr>
                                        <td>
                                            Tanah Kerajaan Boleh diberimilik :
                                        </td>
                                        <td>
                                            <c:if test="${actionBean.laporanTanah.bolehBerimilik eq 'Y'}">
                                                Ya &nbsp;
                                            </c:if>
                                            <c:if test="${actionBean.laporanTanah.bolehBerimilik eq 'T'}">
                                                Tidak &nbsp;
                                            </c:if>
                                        </td>
                                    </tr>
                                    <c:if test="${actionBean.laporanTanah.bolehBerimilik eq 'T'}">
                                        <tr>
                                            <td>
                                                Jika Tidak Boleh diberimilik, sebab :
                                            </td>
                                            <td>
                                                ${actionBean.laporanTanah.sebabTidakBolehBerimilik}&nbsp;
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:if>
                            </c:if>
                            <!--
                            <c:if test="${actionBean.hakmilikPermohonanList[0].hakmilikPermohonan.kodMilik ne null}">
                                <c:if test="${actionBean.hakmilikPermohonanList[0].hakmilikPermohonan.kodMilik.kod eq 'H'}">
                                    <tr>
                                        <td colspan="2">
                                    <display:table  name="${actionBean.senaraiHakmilikPerihalTanah}" id="line" class="tablecloth">
                                        <display:column title="ID Hakmilik">
                                            <c:if test="${line.hakmilik.kodHakmilik.nama ne null}"> ${line.hakmilik.kodHakmilik.nama}&nbsp; </c:if>
                                            <c:if test="${line.hakmilik.kodHakmilik.nama eq null}"> Tiada Data </c:if>
                                        </display:column>

                                        <display:column title="Jenis Hakmilik">
                                            <c:if test="${line.hakmilik.idHakmilik ne null}"> ${line.hakmilik.idHakmilik}&nbsp; </c:if>
                                            <c:if test="${line.hakmilik.idHakmilik eq null}"> Tiada Data </c:if>
                                        </display:column>

                                        <display:column title="No Lot" >
                                            <c:if test="${line.hakmilik.noLot ne null}"> ${line.hakmilik.noLot}&nbsp; </c:if>
                                            <c:if test="${line.hakmilik.noLot eq null}"> Tiada Data </c:if>

                                        </display:column>

                                        <display:column title="Luas">
                                            <c:if test="${line.hakmilik.luas ne null}"> <fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</c:if>
                                            <c:if test="${line.hakmilik.luas eq null}"> Tiada Data </c:if>
                                        </display:column>

                                        <display:column property="hakmilik.kategoriTanah.nama" title="Kegunaan" >
                                            <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot ne null}"> ${actionBean.laporanTanah.sempadanUtaraNoLot}&nbsp; </c:if>
                                            <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot eq null}"> Tiada Data </c:if>
                                        </display:column>

                                        <display:column title="Cukai (RM)">
                                            <c:if test="${line.hakmilik.cukai ne null}"> <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukai}"/>&nbsp; </c:if>
                                            <c:if test="${line.hakmilik.cukai eq null}"> Tiada Data </c:if>
                                        </display:column>
                                    </display:table>
                                </td>
                            </tr>
                                </c:if>
                            </c:if>-->
                            <c:if test="${actionBean.hakmilikPermohonanList[0].hakmilikPermohonan.kodMilik ne null}">
                                <c:if test="${actionBean.hakmilikPermohonanList[0].hakmilikPermohonan.kodMilik.kod eq 'R'}">
                                    <tr>
                                        <td>
                                            Jenis Rizab :
                                        </td>
                                        <td>
                                            ${actionBean.tanahrizabpermohonan1.rizab.nama} &nbsp;
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            No. Warta Kerajaan :
                                        </td>
                                        <td>
                                            ${actionBean.tanahrizabpermohonan1.noWarta}&nbsp;
                                        </td>
                                    </tr>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR'}">
                                        <tr>
                                            <td>
                                                Tarikh Warta:
                                            </td>
                                            <td>
                                                <s:format formatPattern="dd/MM/yyyy" value="${actionBean.tanahrizabpermohonan1.tarikhWarta}"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                No Pelan Warta:
                                            </td>
                                            <td>
                                                ${actionBean.tanahrizabpermohonan1.noPW}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                Pengawal:
                                            </td>
                                            <td>
                                                ${actionBean.tanahrizabpermohonan1.penjaga}
                                            </td>
                                        </tr>
                                    </c:if>

                                </c:if>
                            </c:if>
                            <tr>
                                <td>
                                    Jenis Tanah :
                                </td>
                                <td>
                                    ${actionBean.permohonanLaporanPelan.kodTanah.nama} &nbsp;
                                </td>
                            </tr>
                            <c:if test="${actionBean.permohonanLaporanPelan.kodTanah ne null}">
                                <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod eq '99'}">
                                    <tr>
                                        <td>Lain-lain :
                                        </td>
                                        <td>${actionBean.kand}&nbsp;
                                        </td>
                                    </tr>
                                </c:if>
                            </c:if>
                            <tr>
                                <td>
                                    Kawasan Parlimen :
                                </td>
                                <td>
                                    ${actionBean.hakmilikPermohonanList[0].hakmilikPermohonan.kodKawasanParlimen.nama}&nbsp;

                                </td>
                            </tr>
                            <tr>
                                <td>
                                    DUN :
                                </td>
                                <td>
                                    <%---   ${actionBean.hakmilikPermohonan.kodDUN}&nbsp; --%>
                                    ${actionBean.hakmilikPermohonanList[0].hakmilikPermohonan.kodDUN.nama}&nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Kedudukan Tanah :
                                </td>
                                <td>
                                    ${actionBean.hakmilikPermohonanList[0].hakmilikPermohonan.lokasi}&nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Muatnaik Gambar Tanah :
                                </td>
                                <td>
                                    <s:select name="hmImej" style="width:300px;" id="hmImej" onchange="doSetDokumenHakmilik();">
                                        <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                        <s:options-collection collection="${actionBean.hakmilikImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                                    </s:select>
                                </td>
                            </tr>
                        </table>
                    </c:if>
                    <br>
                    <legend>
                        Permohonan Terdahulu
                    </legend>
                    <display:table class="tablecloth" name="${actionBean.listpermohonanTanahTerdahulu}" pagesize="10" cellpadding="0" cellspacing="0" id="line1">
                        <s:hidden name="" class="${line1_rowNum -1}" value="${line1.permohonanManualSemasa.idMohonManual}"/>
                        <display:column title="No">${line1_rowNum}</display:column>
                        <display:column title="No Fail">
                            <c:if test="${line1.permohonanTerdahulu ne null}">
                                ${line1.permohonanTerdahulu.idPermohonan}
                            </c:if>
                            <c:if test="${line1.permohonanTerdahulu eq null}">
                                ${line1.noFail}
                            </c:if>
                        </display:column>
                        <display:column title="Urusan" property="permohonanTerdahulu.kodUrusan.nama"/>
                        <display:column title="Status Keputusan" property="statusKeputusan" />
                        <c:if test="${line1.permohonanTerdahulu.sebab ne null}">
                            <display:column title="Sebab" property="sebab" />
                        </c:if>
                        <c:if test="${line1.permohonanTerdahulu.sebab eq null}">
                            <display:column title="Sebab"> - </display:column>
                        </c:if>
                        <display:column title="Keputusan Oleh" property="keputusanOleh" />
                        <display:column title="Tarikh Keputusan" >
                            <s:format value="${line1.tarikhKeputusan}" formatPattern="dd/MM/yyyy"/>
                        </display:column>
                        <display:column title="Nama Pemohon" property="namaPemohon" />
                    </display:table>
                </div>
                <br/>
            </div>
        </fieldset>
    </div>
    <br/>
    <div class="subtitle" align="center">
        <fieldset class="aras1">
            <legend>
                Latar Belakang Tanah
                <span style="float:right">
                    <c:if test="${actionBean.disLaporanTanahController.lotSempadan}">
                        <%-- <a onclick="viewDalamKawasan();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                        <a onclick="openFrame('lBelakangTanah');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                        </c:if>                    
                </span>
            </legend>
            <br>
            <div id="latar_blkg_tanah" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiPermohonanLaporanPohon}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pelupusan/laporan_tanahV2">                              
                    <display:column title ="Bil">${line_rowNum}
                        <s:hidden name="" class="${line_rowNum -1}" value="${line.idLaporanPohon}"/>
                    </display:column>
                    <display:column  title ="Jenis Dipohon">
                        <c:if test="${line.jenisDipohon eq 'H'}">
                            Pemberimilikan
                        </c:if>
                        <c:if test="${line.jenisDipohon eq 'LP'}">
                            LPS
                        </c:if>
                        <c:if test="${line.jenisDipohon eq 'P'}">
                            Permit
                        </c:if> 
                        <c:if test="${line.jenisDipohon eq 'M'}">
                            Manual
                        </c:if>     
                    </display:column>
                    <display:column  title ="No Rujukan / No Fail">
                        ${line.noRujukan}
                    </display:column>
                    <display:column  title ="Kegunaan">
                        ${line.kegunaan}
                    </display:column>

                </display:table>
                <br/>
            </div>
        </fieldset> 
    </div>   
    <br/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Bersempadan
                <span style="float:right">
                    <c:if test="${actionBean.disLaporanTanahController.bSempadan}">
                        <%-- <a onclick="viewSempadan();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%> 
                        <a onclick="openFrame('sempadan');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                        </c:if>                    
                </span>
            </legend>
            <div id="sempadan">
                <br>
                <div class="content" align="center">

                    <table class="tablecloth">
                        <tr>
                            <th>Bersempadan</th><th>Nama</th><th>Jarak (KM)</th>
                        </tr>
                        <tr>
                            <td>
                                Jalan Raya
                            </td>                        
                            <td>
                                ${actionBean.laporanTanah.namaSempadanJalanraya}
                            </td>
                            <td>
                                ${actionBean.laporanTanah.jarakSempadanJalanraya}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Landasan Keretapi
                            </td>
                            <td> 
                                ${actionBean.laporanTanah.namaSempadanKeretapi}
                            </td>
                            <td>
                                ${actionBean.laporanTanah.jarakSempadanKeretapi}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Laut
                            </td>
                            <td>
                                ${actionBean.laporanTanah.namaSempadanLaut}
                            </td>
                            <td>
                                ${actionBean.laporanTanah.jarakSempadanLaut}
                            </td>
                        </tr><tr>
                            <td>
                                Sungai
                            </td>
                            <td>
                                ${actionBean.laporanTanah.namaSempadanSungai}
                            </td>
                            <td>
                                ${actionBean.laporanTanah.jarakSempadanSungai}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Lain-lain
                            </td>
                            <td>
                                ${actionBean.laporanTanah.namaSempadanlain}
                            </td>
                            <td>
                                ${actionBean.laporanTanah.jarakSempadanLain}
                            </td>
                        </tr>
                    </table>
                    <br>
                    <table class="tablecloth">
                        <tr>
                            <td>
                                Jenis Jalan :
                            </td>
                            <td>
                                ${actionBean.laporanTanah.jenisJalan}&nbsp;
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Jalan Masuk :
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.adaJalanMasuk eq 'Y'}">
                                    Ada &nbsp;
                                </c:if>
                                <c:if test="${actionBean.laporanTanah.adaJalanMasuk eq 'T'}">
                                    Tiada &nbsp;
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Catatan :
                            </td>
                            <td>
                                ${actionBean.laporanTanah.catatanJalanMasuk}&nbsp;
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </fieldset>
    </div>
    <br/>
    <div class="subtitle">
        <fieldset class="aras1">
            <s:hidden name="laporanTanah.idLaporan"/>
            <s:hidden name="laporanTanah.permohonan.idPermohonan"/>
            <legend>Keadaan Tanah
                <span style="float:right">
                    <c:if test="${actionBean.disLaporanTanahController.kTanah}">
                        <%-- <a onclick="viewKeadaanTanah();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                        <a onclick="openFrame('kTanah');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                        </c:if>                    
                </span>
            </legend>
            <div id="keadaantanah" align="center">
                <table class="tablecloth" align="center">
                    <c:if test="${actionBean.kodNegeri eq '05'}">
                        <tr>
                            <td>
                                Jarak:
                            </td>
                            <td>
                                ${actionBean.hakmilikPermohonan.jarak} <c:if test="${actionBean.hakmilikPermohonan.unitJarak.kod eq 'KM'}">Kilometer</c:if><c:if test="${actionBean.hakmilikPermohonan.unitJarak.kod eq 'JM'}">Meter</c:if>
                                &nbsp;<font color="#003194"><b>dari</b></font>&nbsp; ${actionBean.hakmilikPermohonan.jarakDari} 
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Jarak Dari Kediaman:
                            </td>
                            <td>
                                ${actionBean.hakmilikPermohonan.jarakDariKediaman} <c:if test="${actionBean.hakmilikPermohonan.jarakDariKediamanUom.kod eq 'KM'}">Kilometer</c:if><c:if test="${actionBean.hakmilikPermohonan.jarakDariKediamanUom.kod eq 'JM'}">Meter</c:if>
                                </td>
                            </tr>
                    </c:if>
                    <c:if test="${actionBean.kodNegeri eq '04'}">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR'}">
                            <tr>
                                <td>
                                    Jarak :
                                </td>
                                <td>
                                    <c:if test="${actionBean.hakmilikPermohonan.jarak ne null}">
                                        ${actionBean.hakmilikPermohonan.jarak}
                                        ${actionBean.hakmilikPermohonan.unitJarak.nama}&nbsp;dari
                                        ${actionBean.hakmilikPermohonan.jarakDari}
                                    </c:if>
                                    <c:if test="${actionBean.hakmilikPermohonan.jarak eq null}">
                                        &nbsp;
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Nama Pantai/Pekan/Sungai :
                                </td>
                                <td>
                                    <c:if test="${actionBean.hakmilikPermohonan.jarakDariNama ne null}">
                                        ${actionBean.hakmilikPermohonan.jarakDariNama}
                                    </c:if>
                                    <c:if test="${actionBean.hakmilikPermohonan.jarakDariNama eq null}">
                                        &nbsp;
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Jarak Dari Kediaman :
                                </td>
                                <td>
                                    <c:if test="${actionBean.hakmilikPermohonan.jarakDariKediaman ne null}">
                                        ${actionBean.hakmilikPermohonan.jarakDariKediaman}
                                        ${actionBean.hakmilikPermohonan.jarakDariKediamanUom.nama}
                                    </c:if>
                                    <c:if test="${actionBean.hakmilikPermohonan.jarakDariKediaman eq null}">
                                        &nbsp;
                                    </c:if>
                                </td>
                            </tr>
                        </c:if>
                    </c:if>
                    <tr>
                        <td>
                            Kecerunan Tanah :
                        </td>
                        <td>
                            ${actionBean.laporanTanah.kecerunanTanah.nama}&nbsp;
                        </td>
                    </tr>
                    <c:if test="${actionBean.laporanTanah.kecerunanTanah ne null}">
                        <c:if test="${actionBean.laporanTanah.kecerunanTanah.kod eq 'TG'}">
                            <tr>
                                <td>Ketinggian Dari Paras Jalan (m) :
                                </td>
                                <td>${actionBean.laporanTanah.ketinggianDariJalan}&nbsp;
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${actionBean.laporanTanah.kecerunanTanah.kod eq 'RD'}">
                            <tr>
                                <td>Darjah Kecerunan :
                                </td>
                                <td>${actionBean.laporanTanah.kecerunanBukit}&nbsp;
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${actionBean.laporanTanah.kecerunanTanah.kod eq 'CR'}">
                            <tr>
                                <td>Darjah Kecerunan :
                                </td>
                                <td>${actionBean.laporanTanah.kecerunanBukit}&nbsp;
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${actionBean.laporanTanah.kecerunanTanah.kod eq 'BK'}">
                            <tr>
                                <td>Darjah Kecerunan :
                                </td>
                                <td>${actionBean.laporanTanah.kecerunanBukit}&nbsp;
                                </td>
                            </tr>
                        </c:if>    
                        <c:if test="${actionBean.laporanTanah.kecerunanTanah.kod eq 'PY'}">
                            <tr>
                                <td>Dalam Paras Air (m) :
                                </td>
                                <td>${actionBean.laporanTanah.parasAir}&nbsp;
                                </td>
                            </tr>
                        </c:if>
                    </c:if>
                    <tr>
                        <td>Klasifikasi Tanah :
                        </td>
                        <td>${actionBean.laporanTanah.strukturTanah.nama}&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td>Jika Lain-lain :
                        </td>
                        <td>${actionBean.laporanTanah.strukturTanahLain}&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td>Keadaan Tanah :
                        </td>
                        <td>${actionBean.laporanTanah.kodKeadaanTanah.nama}&nbsp;
                        </td>
                    </tr>
                    <c:if test="${actionBean.laporanTanah.kodKeadaanTanah ne null}">
                        <c:if test="${actionBean.laporanTanah.kodKeadaanTanah.kod eq 'LL'}">
                            <tr>
                                <td>
                                    Lain-lain : 
                                </td>
                                <td>
                                    ${actionBean.keadaankand}&nbsp;
                                </td>
                            </tr>
                        </c:if>
                    </c:if>
                    <tr>
                        <td>
                            Tanah Dipohon Bertumpu :
                        </td>
                        <td>
                            <c:if test="${actionBean.laporanTanah.tanahBertumpu eq 'Y'}">
                                Ya &nbsp;
                            </c:if>
                            <c:if test="${actionBean.laporanTanah.tanahBertumpu eq 'T'}">
                                Tidak &nbsp;
                            </c:if>
                            &nbsp; Pada :
                            ${actionBean.laporanTanah.keteranganTanahBertumpu}&nbsp;
                        </td>
                    </tr>
                </table>
                <br>
                <legend>Dilintasi Oleh</legend>
                <table class="tablecloth" align="center">
                    <tr>
                        <th>Talian Elektrik</th><th>Talian Telefon</th><th>Laluan Gas</th><th>Paip Air</th><th>Tali Air</th><th>Sungai</th><th>Parit</th><th>Lain-lain</th>
                    </tr>
                    <tr>
                        <td>
                            <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq 'Y'}">Ya</c:if>
                            <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq null}">Tiada</c:if>
                            </td>
                            <td>
                            <c:if test="${actionBean.laporanTanah.dilintasTiangTelefon eq 'Y'}">Ya</c:if>
                            <c:if test="${actionBean.laporanTanah.dilintasTiangTelefon eq null}">Tiada</c:if>
                            </td>
                            <td>
                            <c:if test="${actionBean.laporanTanah.dilintasLaluanGas eq 'Y'}">Ya</c:if>
                            <c:if test="${actionBean.laporanTanah.dilintasLaluanGas eq null}">Tiada</c:if>
                            </td>
                            <td>
                            <c:if test="${actionBean.laporanTanah.dilintasPaip eq 'Y'}">Ya</c:if>
                            <c:if test="${actionBean.laporanTanah.dilintasPaip eq null}">Tiada</c:if>
                            </td>
                            <td>
                            <c:if test="${actionBean.laporanTanah.dilintasTaliar eq 'Y'}">Ya</c:if>
                            <c:if test="${actionBean.laporanTanah.dilintasTaliar eq null}">Tiada</c:if>
                            </td>
                            <td>
                            <c:if test="${actionBean.laporanTanah.dilintasSungai eq 'Y'}">Ya</c:if>
                            <c:if test="${actionBean.laporanTanah.dilintasSungai eq null}">Tiada</c:if>
                            </td>
                            <td>
                            <c:if test="${actionBean.laporanTanah.dilintasParit eq 'Y'}">Ya</c:if>
                            <c:if test="${actionBean.laporanTanah.dilintasParit eq null}">Tiada</c:if>
                            </td>
                            <td>
                            <c:if test="${actionBean.laporanTanah.syor eq 'Y'}">Ya - ${actionBean.laporanTanah.ulasan}</c:if>
                            <c:if test="${actionBean.laporanTanah.syor eq null}">Tiada</c:if>
                            </td>
                        </tr>
                    </table>
                    <br>
                    <table class="tablecloth" align="center">
                        <tr>
                            <td>Tanah Diusahakan :</td>
                            <td> <c:if test="${actionBean.laporanTanah.usaha eq 'Y'}">Ya</c:if>
                            <c:if test="${actionBean.laporanTanah.usaha eq 'T'}">Tidak</c:if>
                            </td>
                        </tr>
                    <c:if test="${actionBean.laporanTanah.usaha eq 'Y'}">
                        <tr>
                            <td>
                                Diusahakan Oleh :
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.diusaha eq 'P'}">
                                    Pemohon
                                </c:if>
                                <c:if test="${actionBean.laporanTanah.diusaha ne null && actionBean.permohonanLaporanUsaha.diUsahaOleh ne null}">
                                    ,
                                </c:if> 
                                <c:if test="${actionBean.permohonanLaporanUsaha.diUsahaOleh eq 'OL'}">
                                    Orang Lain
                                </c:if>    
                            </td>
                        </tr>
                        <c:if test="${actionBean.permohonanLaporanUsaha.diUsahaOleh eq 'OL'}">
                            <tr>
                                <td colspan="2">
                                    <display:table class="tablecloth" name="${actionBean.listPermohonanLaporanUsaha}" cellpadding="0" cellspacing="0"
                                                   requestURI="/pelupusan/laporan_tanahV2"  id="line">
                                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                        <display:column title="Pengusaha Tanah Selain Pemohon">
                                            ${line.diUsaha}
                                        </display:column>
                                    </display:table>
                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <td>Tarikh Mula Usaha :</td>
                            <td> <fmt:formatDate value="${actionBean.laporanTanah.tarikhMulaUsaha2}" pattern="dd/MM/yyyy" /></td>
                        </tr>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                            <tr>
                                <td>Tempoh Usaha Tanah :</td>
                                <td>${actionBean.laporanTanah.tarikhMulaUsaha}
                                    &nbsp; Tahun
                                </td>
                            </tr>
                        </c:if> 
                    </c:if>
                </table>
                <c:if test="${actionBean.laporanTanah.usaha eq 'Y'}">
                    <table class="tablecloth">
                        <tr>
                            <th>Jenis Tanaman</th><th>Luas Yang Ditanam</th><th>anggaran Bil.pokok</th><th>Nilaian Tanah Termasuk Tanaman(RM)</th>
                        </tr>
                        <tr align="center">
                            <td>
                                ${actionBean.laporanTanah.usahaTanam} &nbsp;
                            </td>
                            <td>
                                ${actionBean.usahaLuas} ${actionBean.laporanTanah.usahaLuasUom.nama} &nbsp;
                            </td>
                            <td>
                                ${actionBean.usahaBilanganPokok} &nbsp;
                            </td>
                            <td>
                                ${actionBean.usahaHarga} &nbsp;
                            </td>
                        </tr>
                    </table>
                </c:if>
                <table class="tablecloth" align="center">
                    <tr>
                        <td>Bangunan :</td>
                        <td>
                            <c:if test="${actionBean.laporanTanah.adaBangunan eq 'Y'}">Ada</c:if>
                            <c:if test="${actionBean.laporanTanah.adaBangunan eq 'T'}">Tiada</c:if>
                            </td>
                        </tr>
                    </table>

                <c:if test="${actionBean.laporanTanah.adaBangunan eq 'Y'}">
                    <br>
                    <display:table class="tablecloth" name="${actionBean.permohonanLaporanBangunanList}" pagesize="5" cellpadding="0" cellspacing="0"
                                   requestURI="/pelupusan/laporan_tanahV2"  id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Jenis Bangunan">
                            <c:if test="${line.jenisBangunan eq 'KK'}">
                                Kekal
                            </c:if>
                            <c:if test="${line.jenisBangunan eq 'SK'}">
                                Separuh kekal
                            </c:if>
                            <c:if test="${line.jenisBangunan eq 'SM'}">
                                Sementara
                            </c:if> 
                            <c:if test="${line.jenisBangunan eq 'LL'}">
                                lain-lain
                            </c:if>
                        </display:column>
                        <display:column title="Keterangan Jenis Bangunan" >
                            <c:choose>
                                <c:when test="${line.jenisBangunan eq 'LL'}">
                                    ${line.keteranganJenisBngn}
                                </c:when>     
                                <c:otherwise>
                                    -
                                </c:otherwise>
                            </c:choose> 
                        </display:column>
                        <display:column property="ukuran" title="Ukuran Bangunan" />
                        <display:column property="tahunDibina" title="Tahun Dibina" />
                        <display:column property="keadaanBangunan" title="Keadaan Bangunan" />
                        <display:column property="nilai" title="Nilai Bangunan" />
                        <display:column property="namaPemunya" title="Pemilik" />
                        <display:column property="namaKetua" title="Ketua Keluarga Yang Mendiami"/>
                        <display:column title="Status">
                            <c:if test="${line.jenisPendudukan.kod eq 'TT'}">
                                Pemilik
                            </c:if>
                            <c:if test="${line.jenisPendudukan.kod eq 'TS'}">
                                Pemilik dan Penyewa Bangunan
                            </c:if>
                            <c:if test="${line.jenisPendudukan.kod eq 'SS'}">
                                Penyewa Tanah dan Bangunan
                            </c:if>
                        </display:column>
                    </display:table>

                    <br>
                </c:if>
                <table class="tablecloth" align="center">
                    <tr>
                        <td>Tanah sudah Diperenggan :</td>
                        <td>
                            <c:if test="${actionBean.laporanTanah.perenggan eq 'Y'}">Sudah</c:if>
                            <c:if test="${actionBean.laporanTanah.perenggan eq 'T'}">Belum</c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>Rancangan Kerajaan Atas Tanah (Jika Ada) :</td>
                            <td>${actionBean.laporanTanah.rancanganKerajaan}</td>
                    </tr>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PHLP' }">
                        <tr>
                            <td>Nilai Anggaran :</td>
                            <td>
                                RM ${actionBean.laporanTanah.nilaiTanah}
                            </td>
                        </tr>
                    </c:if>
                </table>
            </div>
        </fieldset>             
    </div>
    <br/>
    <div class="subtitle" id="updiv">
        <fieldset class="aras1">
            <legend>Perihal Lot-Lot Bersempadan
                <span style="float:right">
                    <c:if test="${actionBean.disLaporanTanahController.lotSempadan}">
                        <%-- <a onclick="viewLotSempadan();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                        <a onclick="openFrame('lSempadan');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                        </c:if>                    
                </span>
            </legend>
            <div id ="lotsempadan">
                <div class="content" align="center">
                    <table class="tablecloth">
                        <tr>
                            <th>&nbsp;</th><th>ID. Hakmilik</th><th>No. Lot / No. PT</th><th>Kegunaan Tanah</th><th>Keadaan Tanah</th><th>Jarak Dari Tanah Dipohon</th><th>Catatan</th><th>Milik Kerajaan</th><th>Imej Tanah</th>
                                <%--UTARA--%>
                                <c:if test="${!empty actionBean.disLaporanTanahSempadan.listLaporTanahSpdnU}">
                                    <c:set var="i" value="1" />
                            <tr>
                                <th rowspan="${actionBean.disLaporanTanahSempadan.uSize}">
                                    Utara
                                </th>
                                <c:forEach items="${actionBean.disLaporanTanahSempadan.listLaporTanahSpdnU}" var="line">

                                    <td>
                                        ${line.laporanTanahSmpdn.hakmilik.idHakmilik}
                                        <s:hidden  id="kandunganSpdnUHM${i}" name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik" />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.kodLot.nama} ${line.laporanTanahSmpdn.noLot}
                                        <s:hidden  id="kandunganSpdnUNLot${i}" name="disLaporanTanahSempadan.listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.noLot" />
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
                                        <s:format formatPattern="#,###,##0.0000" value="${line.laporanTanahSmpdn.jarak}"/>  ${line.laporanTanahSmpdn.jarakUom.nama}
                                        <s:hidden   id="kandunganSpdnUJ${i}" name="disLaporanTanahSempadan.listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.jarak" />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.catatan}
                                        <s:hidden   id="kandunganSpdnUCAT${i}" name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.catatan" />
                                    </td>
                                    <td>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'T'}">Milik</c:if>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'R'}">Rizab</c:if>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'N'}">--</c:if>
                                        <s:hidden  name="disLaporanTanahSempadan.listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.milikKerajaan" id="kandunganSpdnUMK${i}"/>

                                    </td>
                                    <td>
                                        <s:select name="imejU${i}" style="width:300px;" id="imejU${i}" onchange="openImage('U',${i},'imejU${i}');">
                                            <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                            <s:options-collection collection="${line.listImejLaporan}" label="catatan" value="dokumen.idDokumen"/>
                                        </s:select>
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
                                        ${line.laporanTanahSmpdn.hakmilik.idHakmilik}
                                        <s:hidden  id="kandunganSpdnSHM${i}" name="listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik" />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.kodLot.nama} ${line.laporanTanahSmpdn.noLot}
                                        <s:hidden  id="kandunganSpdnSNLot${i}" name="disLaporanTanahSempadan.listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.noLot" />
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
                                        <s:format formatPattern="#,###,##0.0000" value="${line.laporanTanahSmpdn.jarak}"/>  ${line.laporanTanahSmpdn.jarakUom.nama}
                                        <s:hidden   id="kandunganSpdnSJ${i}" name="disLaporanTanahSempadan.listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.jarak" />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.catatan}
                                        <s:hidden  id="kandunganSpdnSCAT${i}" name="listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.catatan" />
                                    </td>
                                    <td>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'T'}">Milik</c:if>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'R'}">Rizab</c:if>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'N'}">--</c:if>
                                        <s:hidden name="disLaporanTanahSempadan.listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.milikKerajaan" id="kandunganSpdnSMK${i}"/>

                                    </td>
                                    <td>
                                        <s:select name="imejS${i}" style="width:300px;" id="imejS${i}" onchange="openImage('S',${i},'imejS${i}');">
                                            <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                            <s:options-collection collection="${line.listImejLaporan}" label="catatan" value="dokumen.idDokumen"/>
                                        </s:select>
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
                                        ${line.laporanTanahSmpdn.hakmilik.idHakmilik}
                                        <s:hidden  id="kandunganSpdnTHM${i}" name="listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik"  />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.kodLot.nama} ${line.laporanTanahSmpdn.noLot}
                                        <s:hidden  id="kandunganSpdnTNLot${i}" name="disLaporanTanahSempadan.listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.noLot" />
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
                                        <s:format formatPattern="#,###,##0.0000" value="${line.laporanTanahSmpdn.jarak}"/>  ${line.laporanTanahSmpdn.jarakUom.nama}
                                        <s:hidden   id="kandunganSpdnTJ${i}" name="disLaporanTanahSempadan.listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.jarak" />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.catatan}
                                        <s:hidden  id="kandunganSpdnTCAT${i}" name="listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.catatan"  />
                                    </td>
                                    <td>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'T'}">Milik</c:if>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'R'}">Rizab</c:if>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'N'}">--</c:if>
                                        <s:hidden name="disLaporanTanahSempadan.listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.milikKerajaan" id="kandunganSpdnTMK${i}" />

                                    </td>
                                    <td>
                                        <s:select name="imejT${i}" style="width:300px;" id="imejT${i}" onchange="openImage('T',${i},'imejT${i}');">
                                            <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                            <s:options-collection collection="${line.listImejLaporan}" label="catatan" value="dokumen.idDokumen"/>
                                        </s:select>
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
                                        ${line.laporanTanahSmpdn.hakmilik.idHakmilik}
                                        <s:hidden  id="kandunganSpdnBHM${i}" name="listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik"  />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.kodLot.nama} ${line.laporanTanahSmpdn.noLot}
                                        <s:hidden  id="kandunganSpdnBNLot${i}" name="disLaporanTanahSempadan.listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.noLot" />
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
                                        <s:format formatPattern="#,###,##0.0000" value="${line.laporanTanahSmpdn.jarak}"/>  ${line.laporanTanahSmpdn.jarakUom.nama}
                                        <s:hidden   id="kandunganSpdnBJ${i}" name="disLaporanTanahSempadan.listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.jarak" />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.catatan}
                                        <s:hidden  id="kandunganSpdnBCAT${i}" name="listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.catatan"  />
                                    </td>
                                    <td>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'T'}">Milik</c:if>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'R'}">Rizab</c:if>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'N'}">--</c:if>
                                        <s:hidden name="disLaporanTanahSempadan.listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.milikKerajaan" id="kandunganSpdnBMK${i}" />

                                    </td>
                                    <td>
                                        <s:select name="imejB${i}" style="width:300px;" id="imejB${i}" onchange="openImage('B',${i},'imejB${i}');">
                                            <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                            <s:options-collection collection="${line.listImejLaporan}" label="catatan" value="dokumen.idDokumen"/>
                                        </s:select>
                                    </td>
                                </tr>

                                <c:set var="i" value="${i+1}" />        
                            </c:forEach>

                        </c:if>

                        <%--END OF BARAT--%>

                    </table>
                </div>
            </div>
        </fieldset>
    </div>
    <br/>
    <div class="subtitle" align="center">
        <fieldset class="aras1">
            <legend>
                Dalam Kawasan
                <span style="float:right">
                    <c:if test="${actionBean.disLaporanTanahController.dKawasan}">
                        <%-- <a onclick="viewDalamKawasan();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                        <a onclick="openFrame('dKawasan');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                        </c:if>                    
                </span>
            </legend>
            <br>
            <div id="dalamkawasan" align="center">
                <display:table  name="${actionBean.senaraiLaporanKawasan}" id="line9" class="tablecloth">
                    <s:hidden name="" class="${line9_rowNum -1}" value="${line9.idMohonlaporKws}"/>
                    <display:column title="No">${line9_rowNum}</display:column>
                    <display:column title="Jenis Rizab"  property="kodRizab.nama"/>
                    <display:column title="Catatan">
                        <c:if test="${line9.catatan ne null}">
                            ${line9.catatan}
                        </c:if>
                        <c:if test="${line9.catatan eq null}">
                            -
                        </c:if>
                    </display:column>
                    <display:column title="No Warta" property="noWarta"/>
                    <display:column title="Tarikh Warta" property="tarikhWarta" format="{0,date,dd-MM-yyyy}"/>
                    <display:column title="No Pelan Warta" property="noPelanWarta" />                    
                </display:table>
                <br/>
            </div>
        </fieldset> 
    </div>
    <c:if test="${actionBean.kodNegeri eq '05'}">
        <div class="subtitle" align="center">
            <fieldset class="aras1">
                <legend>
                    Jabatan - Jabatan Teknikal
                    <span style="float:right">
                        <c:if test="${actionBean.disLaporanTanahController.lotSempadan}">
                            <%-- <a onclick="viewDalamKawasan();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                            <a onclick="openFrame('jTeknikal');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                            </c:if>                    
                    </span>
                </legend>
                <br>
                <div id="jabatan_teknikal" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiUlasanJabatanTeknikal}" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/pelupusan/laporan_tanahV2">                              
                        <display:column title ="Bil">${line_rowNum}
                            <s:hidden name="" class="${line_rowNum -1}" value="${line.idRujukan}"/>
                        </display:column>
                        <display:column  title ="Nama Jabatan Teknikal">
                            ${line.agensi.nama}
                        </display:column>

                    </display:table>
                    <br/>
                </div>
            </fieldset> 
        </div>
    </c:if>
    <br/>

    <div class="subtitle">
        <fieldset class="aras1">
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
                <c:if test="${actionBean.kodNegeri eq '05'}">
                    <c:choose>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBGSA'}">
                            Syor Penolong Pegawai Tanah 
                        </c:when>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                            Syor Penolong Pegawai Tanah
                        </c:when>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS'}">
                            Syor Penolong Pegawai Tanah
                        </c:when>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                            Syor Penolong Pegawai Tanah
                        </c:when>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'WMRE'}">
                            Syor Penolong Pegawai Tanah
                        </c:when>
                        <c:otherwise>
                            Syor Penolong Pegawai Tanah
                        </c:otherwise>
                    </c:choose>

                </c:if>
                <span style="float:right">
                    <c:if test="${actionBean.disLaporanTanahController.syorPPT}">
                        <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                        <a onclick="openFrame('syorPPT');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                        </c:if>                   
                </span>
            </legend>
            <c:choose>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBGSA' }">
                    <c:import url="laporan_tanahView/syor/syorPBMT.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT' }">
                    <c:import url="laporan_tanahView/syor/syorPBMT.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS' }">
                    <c:import url="laporan_tanahView/syor/syorPLPS.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'RLPS' }">
                    <c:import url="laporan_tanahView/syor/syorRLPS.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA' }">
                    <c:import url="laporan_tanahView/syor/syorPTGSA.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU' }">
                    <c:import url="laporan_tanahView/syor/syorPPRU.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR' }">
                    <c:import url="laporan_tanahView/syor/syorPPTR.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP' }">
                    <c:import url="laporan_tanahView/syor/syorLPSP.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB' }">
                    <c:import url="laporan_tanahView/syor/syorPPBB.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTD' }">
                    <c:import url="laporan_tanahView/syor/syorPBPTD.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTG' }">
                    <c:import url="laporan_tanahView/syor/syorPBPTG.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG' || actionBean.permohonan.kodUrusan.kod eq 'MLCRG' }">
                    <c:import url="laporan_tanahView/syor/syorLMCRG.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PRMP' }">
                    <c:import url="laporan_tanahView/syor/syorPRMP.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'MCMCL' }">
                    <c:import url="laporan_tanahView/syor/syorMCMCL.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'MMMCL' }">
                    <c:import url="laporan_tanahView/syor/syorMMMCL.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PRIZ'}">
                    <c:import url="laporan_tanahView/syor/syorPRIZ.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PHLA' }">
                    <c:import url="laporan_tanahView/syor/syorPHLA.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBRZ' }">
                    <c:import url="laporan_tanahView/syor/syorPBRZ.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'WMRE' }">
                    <c:import url="laporan_tanahView/syor/syorPBRZ.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'MMRE' }">
                    <c:import url="laporan_tanahView/syor/syorPBRZ.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'BMRE' }">
                    <c:import url="laporan_tanahView/syor/syorPBRZ.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBHL' }">
                    <c:import url="laporan_tanahView/syor/syorPBHL.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT' }">
                    <c:import url="laporan_tanahView/syor/syorBMBT.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTBTC' }">
                    <c:import url="laporan_tanahView/syor/syorPTBTC.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTBTS' }">
                    <c:import url="laporan_tanahView/syor/syorPTBTS.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PJBTR' }">
                    <c:import url="laporan_tanahView/syor/syorPJBTR.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PLPTD' }">
                    <c:import url="laporan_tanahView/syor/syorPLPTD.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMMK' }">
                    <c:import url="laporan_tanahView/syor/syorPBMMK.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTPBP' }">
                    <c:import url="laporan_tanahView/syor/syorPRMP.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTBTC' }">
                    <c:import url="laporan_tanahView/syor/syorPTBTC.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTBTS' }">
                    <c:import url="laporan_tanahView/syor/syorPTBTS.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PJTK' }">
                    <c:import url="laporan_tanahView/syor/syorPBMT.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTMTA' }">
                    <c:import url="laporan_tanahView/syor/syorPTMTA.jsp"></c:import>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PCRG'}">
                    <c:import url="laporan_tanahView/syor/syorPBPTG.jsp"></c:import>
                    <%--<c:import url="laporan_tanahView/syor/syorPBMT.jsp"></c:import>--%>
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LPJH'}">
                    <c:import url="laporan_tanahView/syor/syorPRIZ.jsp"></c:import>
                    <%--<c:import url="laporan_tanahView/syor/syorPBMT.jsp"></c:import>--%>
                </c:when>
                <c:when test="${actionBean.kodNegeri eq '05'}">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJLB' || actionBean.permohonan.kodUrusan.kod eq 'LSTP' }">
                        <%--<c:import url="laporan_tanahView/syor/syorPJLB.jsp"></c:import>--%>
                        <c:import url="laporan_tanahView/syor/syorPBPTG.jsp"></c:import>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMMK' }">
                        <%--<c:import url="laporan_tanahView/syor/syorPJLB.jsp"></c:import>--%>
                        <c:import url="laporan_tanahView/syor/syorPBMMK.jsp"></c:import>
                    </c:if>
                </c:when>
                <c:when test="${actionBean.kodNegeri eq '04'}">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJLB' || actionBean.permohonan.kodUrusan.kod eq 'MPJLB' }">
                        <c:import url="laporan_tanahView/syor/syorPJLB.jsp"></c:import>
                    </c:if>
                </c:when>
                <c:otherwise>

                </c:otherwise>
            </c:choose>
            <br/>
            <legend> Ulasan :</legend>
            <table class="tablecloth" align="center">
                <c:if test="${!empty actionBean.senaraiLaporanKandungan1}">
                    <c:set var="i" value="1" />
                    <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line">
                        <tr><td style="text-align: right">${i}</td>
                            <td><s:textarea value="${line.ulasan}" cols="80" readonly="true" name="syorUlasan" rows="5"/></td>
                        </tr>
                        <c:set var="i" value="${i+1}" />
                    </c:forEach> 
                </c:if>
                <c:if test="${empty actionBean.senaraiLaporanKandungan1}">
                    <tr>
                        <td><s:textarea value="TIADA ULASAN PENOLONG PEGAWAI TANAH TELAH DIBUAT." cols="80" readonly="true" name="syorUlasan" rows="5"/></td>
                    </tr>
                </c:if>
            </table>
        </fieldset>
    </div>
    <br/>

</s:form>