<%-- 
    Document   : kertas_jktdV2
    Created on : Apr 25, 2013, 3:25:04 PM
    Author     : afham
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<script type="text/javascript">

    function openFrame(type) {
        doBlockUI();
        window.open("${pageContext.request.contextPath}/pelupusan/kertas_JKTDV2?openFrame&type=" + type, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }

    function refreshV2KertasJKTD(type) {
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_JKTDV2?refreshpage&type=' + type;
        $.get(url,
                function(data) {
                    $('#page_div').html(data);
                }, 'html');
        doUnBlockUI();
    }

    function refreshRekodKeputusanJKTDV2(type) {
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_JKTDV2?refreshpage&type=' + type;
        $.get(url,
                function(data) {
                    $('#page_div').html(data);
                }, 'html');
        doUnBlockUI();
    }
    
    function editMohonHakmilik(idMohonHakmilik,type){
        doBlockUI();
        //        alert(idHakmilik);
        window.open("${pageContext.request.contextPath}/pelupusan/kertas_JKTDV2?editDetails&idMH="+idMohonHakmilik
            +"&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }

</script>

<s:form beanclass="etanah.view.stripes.pelupusan.KertasJKTDV2ActionBean">
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                <span style="float:right">
                    <c:if test="${actionBean.disKertasMMKController.kTajuk}">
                        <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                        <a onclick="openFrame('kTajuk');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                        </c:if>
                </span>
            </legend>
            <table class="tablecloth" align="center">
                <tr>
                    <td><s:textarea value="${actionBean.kandunganTajuk}" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
                </tr>    
            </table>

        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                1. TUJUAN
                <span style="float:right">
                    <c:if test="${actionBean.disKertasMMKController.kTujuan}">
                        <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                        <a onclick="openFrame('kTujuan');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                        </c:if>
                </span>
            </legend>
            <table class="tablecloth" align="center">
                <c:if test="${!empty actionBean.senaraiKandunganTujuan}">
                    <c:set value="1" var="i"/>
                    <c:forEach items="${actionBean.senaraiKandunganTujuan}" var="line">
                        <tr>
                            <td style="text-align: right">1.${i}</td>
                            <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
                        </tr>
                        <c:set value="${i+1}" var="i"/>
                    </c:forEach>
                </c:if>    
            </table>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                2. LATAR BELAKANG
            </legend>
            <table class="tablecloth" align="center">
                <tr>
                    <td style="text-align: right">2.1</td>
                    <td><b>Perihal Permohonan</b>
                        <span style="float:right">
                            <c:if test="${actionBean.disKertasMMKController.kLatarBelakang}">
                                <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                                <a onclick="openFrame('kPermohonan');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                </c:if>
                        </span>
                    </td>
                </tr>
                <c:if test="${!empty actionBean.senaraiKandunganPerihalPermohonan}">
                    <c:set value="1" var="i"/>
                    <c:forEach items="${actionBean.senaraiKandunganPerihalPermohonan}" var="line">
                        <tr>
                            <td style="text-align: right">2.1.${i}</td>
                            <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
                        </tr>
                        <c:set value="${i+1}" var="i"/>
                    </c:forEach>
                </c:if>      
                <tr>
                    <td style="text-align: right">2.2</td>
                    <td><b>Perihal Tanah</b>
                        <span style="float:right">
                            <c:if test="${actionBean.disKertasMMKController.kLatarBelakang}">
                                <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                                <a onclick="openFrame('kTanah');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                </c:if>
                        </span>
                    </td>
                </tr>
                <c:if test="${!empty actionBean.senaraiKandunganPerihalTanah}">
                    <c:set value="1" var="a"/>
                    <c:forEach items="${actionBean.senaraiKandunganPerihalTanah}" var="line">
                        <tr>
                            <td style="text-align: right">2.2.${a}</td>
                            <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
                        </tr>
                        <c:set value="${a+1}" var="a"/>
                    </c:forEach>
                </c:if>
                <tr>
                    <td>2.2.${a}</td>
                    <td>${actionBean.defaultPerihalSempadan}
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><display:table class="tablecloth" name="${actionBean.laporTanahSempadanList}" cellpadding="0" cellspacing="0"
                                   requestURI="/pelupusan/draf_mmkn_pjbtr" id="line">
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
                        </display:table></td>
                </tr>

            </table>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                3. BUTIR-BUTIR PEMOHON
            </legend>
            <table class="tablecloth" align="center">

                <tr>
                    <td style="text-align: right">3.1</td>
                    <td><b>Perihal Pemohon</b></td>
                </tr>
                <c:if test="${actionBean.kelompok eq false}">
                    <c:if test="${fn:length(actionBean.listPemohon) > 1}">
                        <%--if more than 1--%>
                        <tr>
                            <td style="text-align: right">3.1.1</td>
                            <td>${actionBean.defaultPerihalPemohon}</td>
                        </tr>
                    </c:if>

                    <c:if test="${fn:length(actionBean.listPemohon) eq 1}">
                        <tr>
                            <td>&nbsp;</td>
                            <td>
                                <table>
                                    <tr>
                                        <th>Perkara</th><th>Pemohon</th><th>Suami/Isteri</th>
                                    </tr>
                                    <tr>
                                        <th>
                                            Nama
                                        </th>
                                        <td>
                                            <%--${actionBean.pemohon.pihak.nama}--%>
                                            ${actionBean.listPemohon[0].pihak.nama}
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${fn:length(actionBean.listPemohonHubungan) eq 1}">
                                                    ${actionBean.pemohonHubungan.nama} 
                                                </c:when>
                                                <c:otherwise>
                                                    <%--more than 1--%>
                                                    <c:forEach items="${actionBean.listPemohonHubungan}" var="ph">
                                                        ${ph.nama}<br>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>

                                        </td>
                                    </tr>
                                    <tr>
                                        <th>
                                            No Kad Pengenalan
                                        </th>
                                        <td>
                                            ${actionBean.pemohon.pihak.noPengenalan}
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${fn:length(actionBean.listPemohonHubungan) eq 1}">
                                                    ${actionBean.pemohonHubungan.noPengenalan}
                                                </c:when>
                                                <c:otherwise>
                                                    <c:forEach items="${actionBean.listPemohonHubungan}" var="ph">
                                                        ${ph.noPengenalan}<br>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>
                                            Alamat
                                        </th>
                                        <td>

                                            ${actionBean.pemohon.pihak.alamat1}
                                            <c:if test="${actionBean.pemohon.pihak.alamat2 ne null}"> , </c:if>
                                            ${actionBean.pemohon.pihak.alamat2}
                                            <c:if test="${actionBean.pemohon.pihak.alamat3 ne null}"> , </c:if>
                                            ${actionBean.pemohon.pihak.alamat3}
                                            <c:if test="${actionBean.pemohon.pihak.alamat4 ne null}"> , </c:if>
                                            ${actionBean.pemohon.pihak.alamat4}
                                            ${actionBean.pemohon.pihak.poskod}
                                            ${actionBean.pemohon.pihak.negeri.nama}
                                        </td>
                                        <td>
                                            <%--isteri/suami--%>
                                            <c:choose>
                                                <c:when test="${fn:length(actionBean.listPemohonHubungan) eq 1}">
                                                    ${actionBean.pemohonHubungan.alamat1}
                                                    <c:if test="${actionBean.pemohonHubungan.alamat2 ne null}"> , </c:if>
                                                    ${actionBean.pemohonHubungan.alamat2}
                                                    <c:if test="${actionBean.pemohonHubungan.alamat3 ne null}"> , </c:if>
                                                    ${actionBean.pemohonHubungan.alamat3}
                                                    <c:if test="${actionBean.pemohonHubungan.alamat4 ne null}"> , </c:if>
                                                    ${actionBean.pemohonHubungan.alamat4}
                                                    ${actionBean.pemohonHubungan.poskod}
                                                    ${actionBean.pemohonHubungan.negeri.nama}
                                                </c:when>
                                                <c:otherwise>
                                                    <c:forEach items="${actionBean.listPemohonHubungan}" var="ph">
                                                        ${ph.alamat1}
                                                        <c:if test="${ph.alamat2 ne null}"> , </c:if>
                                                        ${ph.alamat2}
                                                        <c:if test="${ph.alamat3 ne null}"> , </c:if>
                                                        ${ph.alamat3}
                                                        <c:if test="${ph.alamat4 ne null}"> , </c:if>
                                                        ${ph.alamat4}
                                                        ${ph.poskod}
                                                        ${ph.negeri.nama}
                                                        <br>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>
                                            Umur/ Tarikh Lahir
                                        </th>
                                        <td>
                                            ${actionBean.pemohon.umur}Tahun / <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.pemohon.pihak.tarikhLahir}"/>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${fn:length(actionBean.listPemohonHubungan) eq 1}">
                                                    ${actionBean.pemohonHubungan.umur}Tahun/  z<fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.pemohonHubungan.tarikhLahir}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:forEach items="${actionBean.listPemohonHubungan}" var="ph">
                                                        ${ph.umur}Tahun/ <fmt:formatDate pattern="dd/MM/yyyy" value="${ph.tarikhLahir}"/><br>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>
                                            Tempat Lahir
                                        </th>
                                        <td>
                                            ${actionBean.pemohon.pihak.tempatLahir}
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${fn:length(actionBean.listPemohonHubungan) eq 1}">
                                                    ${actionBean.pemohonHubungan.tempatLahir}
                                                </c:when>
                                                <c:otherwise>
                                                    <c:forEach items="${actionBean.listPemohonHubungan}" var="ph">
                                                        ${ph.tempatLahir}<br>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>

                                        </td>
                                    </tr>
                                    <tr>
                                        <th>
                                            Pekerjaan
                                        </th>
                                        <td>
                                            ${actionBean.pemohon.pekerjaan}
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${fn:length(actionBean.listPemohonHubungan) eq 1}">
                                                    ${actionBean.pemohonHubungan.pekerjaan}
                                                </c:when>
                                                <c:otherwise>
                                                    <c:forEach items="${actionBean.listPemohonHubungan}" var="ph">
                                                        ${ph.pekerjaan}<br>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>
                                            Pendapatan
                                        </th>
                                        <td>
                                            RM ${actionBean.pemohon.pendapatan}
                                        </td>
                                        <td>

                                            <c:choose>
                                                <c:when test="${fn:length(actionBean.listPemohonHubungan) eq 1}">
                                                    <c:if test="${actionBean.pemohonHubungan.gaji ne null}">
                                                        RM ${actionBean.pemohonHubungan.gaji}
                                                    </c:if>
                                                    <c:if test="${actionBean.pemohonHubungan.gaji eq null}">
                                                        Tiada
                                                    </c:if>

                                                </c:when>
                                                <c:otherwise>
                                                    <c:forEach items="${actionBean.listPemohonHubungan}" var="ph">
                                                        <c:if test="${ph.gaji ne null}">
                                                            RM
                                                        </c:if>
                                                        ${ph.gaji}<br>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>
                                            Tanggungan
                                        </th>
                                        <td colspan="2">
                                            <p align="center">
                                                <c:if test="${actionBean.pemohon.tanggungan ne null && actionBean.pemohon.tanggungan > 0}">
                                                    ${actionBean.pemohon.tanggungan} orang
                                                </c:if>
                                                <c:if test="${actionBean.pemohon.tanggungan ne null && actionBean.pemohon.tanggungan eq 0}">
                                                    Tiada Tanggungan
                                                </c:if>
                                                <c:if test="${actionBean.pemohon.tanggungan eq null}">
                                                    Tiada Tanggungan
                                                </c:if>
                                            </p>
                                        </td>

                                    </tr>
                                    <tr>
                                        <th>
                                            Tarikh Mohon
                                        </th>
                                        <td colspan="2">
                                            <p align="center"> <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.pemohon.infoAudit.tarikhMasuk}"/> </p>
                                        </td>

                                    </tr>
                                </table>

                            </td>
                        </tr>
                    </c:if>
                </c:if>
                <c:if test="${actionBean.kelompok eq true}">
                    <tr>
                        <td style="text-align: right">3.1.1</td>
                        <td>${actionBean.defaultPerihalPemohon}</td>
                    </tr>
                </c:if>

            </table>
        </fieldset>
    </div>



    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                4. ULASAN - ULASAN JABATAN TEKNIKAL
            </legend>
            <table class="tablecloth" align="center">
                <tr>
                    <td style="text-align: right">4.1</td>
                    <td>${actionBean.defaultKandunganJbtn}</td>
                </tr>
                <c:set value="1" var="i"/>
                <c:forEach items="${actionBean.senaraiUlasanJabatanTeknikal}" var="line">
                    <tr>
                        <td style="text-align: right">${i}</td>
                        <td><font style="font-weight:bold;">${line.namaAgensi}</font></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>( Ruj. Surat ${line.noRujukan} bertarikh <s:format value="${line.tarikhRujukan}" formatPattern="dd/MM/yyyy"/> )</td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>${line.ulasan}
                            <s:hidden name="idRujukanLuar${i}" value="${line.idRujukan}" id="idRujukanLuar$${i}"/>
                        </td>
                    </tr>
                    <c:set var="i" value="${i+1}" />
                </c:forEach>
            </table>
        </fieldset>
    </div>
    <c:if test="${actionBean.kelompok eq false}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    5. MAKLUMAT PEMILIKAN TANAH PEMOHON
                </legend>
                <table class="tablecloth" align="center">

                    <tr>
                        <td style="text-align: right">5.1</td>
                        <td>Maklumat pemilikan tanah pemohon dan suami/isteri adalah seperti berikut:-</td>
                    </tr>

                    <tr>
                        <td>&nbsp;</td>
                        <td>
                            <table class="tablecloth" align="center">
                                <tr>
                                    <th>Pemohon</th><th>Hakmilik</th><th>Lot</th><th>Mukim/Pekan</th><th>Luas</th><th>Kegunaan</th>
                                </tr>
                                <c:if test="${!empty actionBean.listDisPemilikanTanah}">

                                    <c:forEach items="${actionBean.listDisPemilikanTanah}" var="line">
                                        <tr>
                                            <td>
                                                <c:if test = "${line.pemohon.pihak.nama ne null}">
                                                    ${line.pemohon.pihak.nama}
                                                </c:if>
                                                <c:if test="${line.pemohon.pihak.nama eq null}">
                                                    -
                                                </c:if>
                                            </td>

                                            <td>
                                                <c:if test = "${line.hakmilik.noLot ne null}">
                                                    ${line.hakmilik.idHakmilik}
                                                </c:if>
                                                <c:if test="${line.hakmilik.noLot eq null}">
                                                    -
                                                </c:if>
                                            </td>

                                            <td>
                                                <c:if test = "${line.hakmilik.bandarPekanMukim.nama ne null}">
                                                    ${line.hakmilik.bandarPekanMukim.nama}
                                                </c:if>
                                                <c:if test="${line.hakmilik.bandarPekanMukim.nama eq null}">
                                                    -
                                                </c:if>
                                            </td>

                                            <td>
                                                <c:if test = "${line.hakmilik.luas ne null}">
                                                    ${line.hakmilik.luas}
                                                </c:if>
                                                <c:if test="${line.hakmilik.luas eq null}">
                                                    -
                                                </c:if>
                                            </td>

                                            <td>
                                                <c:if test = "${line.hakmilik.kegunaanTanah.nama ne null}">
                                                    ${line.hakmilik.kegunaanTanah.nama}
                                                </c:if>
                                                <c:if test="${line.hakmilik.kegunaanTanah.nama eq null}">
                                                    -
                                                </c:if>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <c:if test = "${line.pemohonHubungan.nama ne null}">
                                                    ${line.pemohonHubungan.nama}
                                                </c:if>
                                                <c:if test="${line.pemohonHubungan.nama eq null}">
                                                    -
                                                </c:if>
                                            </td>

                                            <td>
                                                <c:if test = "${line.hakmilik.idHakmilik ne null}">
                                                    ${line.hakmilik.idHakmilik}
                                                </c:if>
                                                <c:if test="${line.hakmilik.idHakmilik eq null}">
                                                    -
                                                </c:if>
                                            </td>

                                            <td>
                                                <c:if test = "${line.hakmilik.noLot ne null}">
                                                    ${line.hakmilik.noLot}
                                                </c:if>
                                                <c:if test="${line.hakmilik.noLot eq null}">
                                                    -
                                                </c:if>
                                            </td>

                                            <td>
                                                <c:if test = "${line.hakmilik.bandarPekanMukim.nama ne null}">
                                                    ${line.hakmilik.bandarPekanMukim.nama}
                                                </c:if>
                                                <c:if test="${line.hakmilik.bandarPekanMukim.nama eq null}">
                                                    -
                                                </c:if>
                                            </td>

                                            <td>
                                                <c:if test = "${line.hakmilik.luas ne null}">
                                                    ${line.hakmilik.luas}
                                                </c:if>
                                                <c:if test="${line.hakmilik.luas eq null}">
                                                    -
                                                </c:if>
                                            </td>

                                            <td>
                                                <c:if test = "${line.hakmilik.kegunaanTanah.nama ne null}">
                                                    ${line.hakmilik.kegunaanTanah.nama}
                                                </c:if>
                                                <c:if test="${line.hakmilik.kegunaanTanah.nama eq null}">
                                                    -
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                </c:if>



                            </table>

                        </td>
                    </tr>

                </table>
            </fieldset>
        </div>


        <%--
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    6. HURAIAN <c:choose><c:when test="${actionBean.permohonan.cawangan.kod eq '08'}">TIMBALAN PENTADBIR TANAH GEMAS</c:when><c:otherwise>PENTADBIR TANAH ${actionBean.permohonan.cawangan.daerah.nama}</c:otherwise></c:choose>
                    <span style="float:right">
                        <c:if test="${actionBean.disKertasMMKController.kPerakuanPTD}">
                           <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | 
                            <a onclick="openFrame('kHuraianPtd');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                            </c:if>
                    </span>
                </legend>
                <table class="tablecloth" align="center">
                    <c:if test="${!empty actionBean.senaraiKandunganHuraianPtd}">
                        <c:set value="1" var="i"/>
                        <c:forEach items="${actionBean.senaraiKandunganHuraianPtd}" var="line">
                            <tr>
                                <td style="text-align: right">6.${i}</td>
                                <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
                            </tr>
                            <c:set value="${i+1}" var="i"/>
                        </c:forEach>
                    </c:if>
                </table>
            </fieldset>
        </div>
        --%>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    6. HURAIAN <c:choose><c:when test="${actionBean.permohonan.cawangan.kod eq '08'}">TIMBALAN PENTADBIR TANAH GEMAS</c:when><c:otherwise>PENTADBIR TANAH ${actionBean.permohonan.cawangan.daerah.nama}</c:otherwise></c:choose>
                            <span style="float:right">
                        <c:if test="${actionBean.disKertasMMKController.kPerakuanPTD}">
                            <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                            <a onclick="openFrame('kPerakuanPtd');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                            </c:if>
                    </span>
                </legend>
                <table class="tablecloth" align="center">
                    <tr>
                        <td>Keputusan :</td>
                        <td>
                            <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL'}">
                                Syor Lulus
                            </c:if>
                            <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'ST'}">
                                Syor Tolak
                            </c:if>
                        </td>
                    </tr>
                    <c:if test="${!empty actionBean.senaraiKandunganPerakuanPtd}">
                        <c:set value="1" var="i"/>
                        <c:forEach items="${actionBean.senaraiKandunganPerakuanPtd}" var="line">
                            <tr>
                                <td style="text-align: right">6.${i}</td>
                                <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
                            </tr>
                            <c:set value="${i+1}" var="i"/>
                        </c:forEach>
                    </c:if>

                </table>
                <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL'}">
                    <center>
                        <c:choose>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT' }">
                                <c:import url="kertas_jktdView/syarat/syaratPBMT.jsp"></c:import>
                            </c:when>
                        </c:choose>
                    </center>
                </c:if>
            </fieldset>
        </div>

        <c:if test="${actionBean.disKertasMMKController.vPTG}">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        8. PERAKUAN PENGARAH TANAH DAN GALIAN
                        <span style="float:right">
                            <c:if test="${actionBean.disKertasMMKController.kPerakuanPTG}">
                                <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                                <a onclick="openFrame('kPerakuanPtg');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                </c:if>
                        </span>
                    </legend>
                    <table class="tablecloth" align="center">
                        <c:if test="${!empty actionBean.senaraiKandunganPerakuanPtg}">
                            <c:set value="1" var="i"/>
                            <c:forEach items="${actionBean.senaraiKandunganPerakuanPtg}" var="line">
                                <tr>
                                    <td style="text-align: right">8.${i}</td>
                                    <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5"/></td>
                                </tr>
                                <c:set value="${i+1}" var="i"/>
                            </c:forEach>
                        </c:if>
                    </table>
                </fieldset>
            </div>

            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        9. KEPUTUSAN
                        <span style="float:right">
                            <c:if test="${actionBean.disKertasMMKController.kPerakuanPTG}">
                                <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                                <a onclick="openFrame('kKeputusan');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                </c:if>
                        </span>
                    </legend>
                    <table class="tablecloth" align="center">
                        <c:if test="${!empty actionBean.senaraiKandunganKeputusan}">
                            <c:set value="1" var="i"/>
                            <c:forEach items="${actionBean.senaraiKandunganKeputusan}" var="line">
                                <tr>
                                    <td style="text-align: right">9.${i}</td>
                                    <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5"/></td>
                                </tr>
                                <c:set value="${i+1}" var="i"/>
                            </c:forEach>
                        </c:if>
                    </table>
                </fieldset>
            </div>
        </c:if>
    </c:if>

    <c:if test="${actionBean.kelompok eq true}">
        <%--
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    5. HURAIAN <c:choose><c:when test="${actionBean.permohonan.cawangan.kod eq '08'}">TIMBALAN PENTADBIR TANAH GEMAS</c:when><c:otherwise>PENTADBIR TANAH ${actionBean.permohonan.cawangan.daerah.nama}</c:otherwise></c:choose>
                    <span style="float:right">
                        <c:if test="${actionBean.disKertasMMKController.kPerakuanPTD}">
                            <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | 
                            <a onclick="openFrame('kHuraianPtd');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                            </c:if>
                    </span>
                </legend>
                <table class="tablecloth" align="center">
                    <c:if test="${!empty actionBean.senaraiKandunganHuraianPtd}">
                        <c:set value="1" var="i"/>
                        <c:forEach items="${actionBean.senaraiKandunganHuraianPtd}" var="line">
                            <tr>
                                <td style="text-align: right">5.${i}</td>
                                <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
                            </tr>
                            <c:set value="${i+1}" var="i"/>
                        </c:forEach>
                    </c:if>
                </table>
            </fieldset>
        </div>--%>

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    5. HURAIAN <c:choose><c:when test="${actionBean.permohonan.cawangan.kod eq '08'}">TIMBALAN PENTADBIR TANAH GEMAS</c:when><c:otherwise>PENTADBIR TANAH ${actionBean.permohonan.cawangan.daerah.nama}</c:otherwise></c:choose>
                            <span style="float:right">
                        <c:if test="${actionBean.disKertasMMKController.kPerakuanPTD}">
                            <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                            <a onclick="openFrame('kPerakuanPtd');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                            </c:if>
                    </span>
                </legend>
                <table class="tablecloth" align="center">
                    <tr>
                        <td>Keputusan :</td>
                        <td>
                            <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL'}">
                                Syor Lulus
                            </c:if>
                            <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'ST'}">
                                Syor Tolak
                            </c:if>
                        </td>
                    </tr>
                    <c:if test="${!empty actionBean.senaraiKandunganPerakuanPtd}">
                        <c:set value="1" var="i"/>
                        <c:forEach items="${actionBean.senaraiKandunganPerakuanPtd}" var="line">
                            <tr>
                                <td style="text-align: right">5.${i}</td>
                                <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
                            </tr>
                            <c:set value="${i+1}" var="i"/>
                        </c:forEach>
                    </c:if>

                </table>
                <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL'}">
                    <center>
                        <c:choose>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT' }">
                                <c:import url="kertas_jktdView/syarat/syaratPBMT.jsp"></c:import>
                            </c:when>
                        </c:choose>
                    </center>
                    <table class="tablecloth" align="center">
                        <tr>
                            <td colspan="2">
                                <b>Senarai Hakmilik Permohonan Tiada Keputusan</b>
                                <a onclick="openFrame('sHakmilik');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                    <display:table class="tablecloth" name="${actionBean.senaraiHakmilikTiadaKeputusan}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                                                   requestURI="/pelupusan/rekod_keputusanJKTDV2">
                                        <display:column title="No">${line_rowNum}</display:column>
                                        <c:if test="${actionBean.kelompok eq true}">
                                            <display:column title="ID Permohonan" >${line.permohonan.idPermohonan}</display:column> 
                                        </c:if>    
                                        <c:if test="${line.hakmilik ne null}">
                                            <display:column title="ID Hakmilik" >${line.hakmilik.idHakmilik}</display:column> 
                                            <display:column title="No.Lot/PT">
                                                <c:if test="${line.noLot eq null}">
                                                    ${line.hakmilik.noLot}
                                                </c:if>
                                                <c:if test="${line.noLot ne null}">
                                                    ${line.noLot}
                                                </c:if>
                                            </display:column>
                                            <display:column title="Bandar/Pekan/Mukim">
                                                <c:if test="${line.bandarPekanMukimBaru.nama eq null}">
                                                    ${line.hakmilik.bandarPekanMukim.nama}
                                                </c:if>
                                                <c:if test="${line.noLot ne null}">
                                                    ${line.bandarPekanMukimBaru.nama}
                                                </c:if>                                
                                            </display:column>
                                        </c:if>
                                        <c:if test="${line.hakmilik eq null}">
                                            <display:column title="No.Lot/PT" >${line.noLot}</display:column>
                                            <display:column title="Bandar/Pekan/Mukim" >${line.bandarPekanMukimBaru.nama}</display:column>
                                        </c:if>
                                    </display:table>   
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <b>Senarai Syor Hakmilik Permohonan Lulus</b>
                                <display:table class="tablecloth" name="${actionBean.senaraiHakmilikLulus}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                                               requestURI="/pelupusan/rekod_keputusanJKTDV2">
                                    <display:column title="No">${line_rowNum}</display:column>
                                    <c:if test="${actionBean.kelompok eq true}">
                                        <display:column title="ID Permohonan" >${line.permohonan.idPermohonan}</display:column> 
                                    </c:if>
                                    <c:if test="${line.hakmilik ne null}">
                                        <display:column title="ID Hakmilik" >${line.hakmilik.idHakmilik}</display:column> 
                                        <display:column title="No.Lot/PT">
                                            <c:if test="${line.noLot eq null}">
                                                ${line.hakmilik.noLot}
                                            </c:if>
                                            <c:if test="${line.noLot ne null}">
                                                ${line.noLot}
                                            </c:if>
                                        </display:column>
                                        <display:column title="Bandar/Pekan/Mukim">
                                            <c:if test="${line.bandarPekanMukimBaru.nama eq null}">
                                                ${line.hakmilik.bandarPekanMukim.nama}
                                            </c:if>
                                            <c:if test="${line.noLot ne null}">
                                                ${line.bandarPekanMukimBaru.nama}
                                            </c:if>                                
                                        </display:column>
                                    </c:if>
                                    <c:if test="${line.hakmilik eq null}">
                                        <display:column title="No.Lot/PT" >${line.noLot}</display:column>
                                        <display:column title="Bandar/Pekan/Mukim" >${line.bandarPekanMukimBaru.nama}</display:column>
                                    </c:if>
                                    <display:column title="Tindakan">
                                        <c:if test="${actionBean.disKertasMMKController.kPerakuanPTD}">
                                            <a onclick="editMohonHakmilik(${line.id}, 'update')" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                                            </c:if>
                                            <c:if test="${!actionBean.disKertasMMKController.kPerakuanPTD}">
                                            <a onclick="editMohonHakmilik(${line.id}, 'view')" onmouseover="this.style.cursor = 'pointer';"><img alt="Papar"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                                            </c:if>
                                        </display:column>
                                    </display:table>   
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <b>Senarai Syor Hakmilik Permohonan Tolak</b>
                                <display:table class="tablecloth" name="${actionBean.senaraiHakmilikTolak}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                                               requestURI="/pelupusan/rekod_keputusanJKTDV2">
                                    <display:column title="No">${line_rowNum}</display:column>
                                    <c:if test="${actionBean.kelompok eq true}">
                                        <display:column title="ID Permohonan" >${line.permohonan.idPermohonan}</display:column> 
                                    </c:if>
                                    <c:if test="${line.hakmilik ne null}">
                                        <display:column title="ID Hakmilik" >${line.hakmilik.idHakmilik}</display:column> 
                                        <display:column title="No.Lot/PT">
                                            <c:if test="${line.noLot eq null}">
                                                ${line.hakmilik.noLot}
                                            </c:if>
                                            <c:if test="${line.noLot ne null}">
                                                ${line.noLot}
                                            </c:if>
                                        </display:column>
                                        <display:column title="Bandar/Pekan/Mukim">
                                            <c:if test="${line.bandarPekanMukimBaru.nama eq null}">
                                                ${line.hakmilik.bandarPekanMukim.nama}
                                            </c:if>
                                            <c:if test="${line.noLot ne null}">
                                                ${line.bandarPekanMukimBaru.nama}
                                            </c:if>                                
                                        </display:column>
                                    </c:if>
                                    <c:if test="${line.hakmilik eq null}">
                                        <display:column title="No.Lot/PT" >${line.noLot}</display:column>
                                        <display:column title="Bandar/Pekan/Mukim" >${line.bandarPekanMukimBaru.nama}</display:column>
                                    </c:if>
                                </display:table>  
                            </td>
                        </tr>
                    </table>
                </c:if>
            </fieldset>
        </div>

        <c:if test="${actionBean.disKertasMMKController.vPTG}">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        7. PERAKUAN PENGARAH TANAH DAN GALIAN
                        <span style="float:right">
                            <c:if test="${actionBean.disKertasMMKController.kPerakuanPTG}">
                                <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                                <a onclick="openFrame('kPerakuanPtg');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                </c:if>
                        </span>
                    </legend>
                    <table class="tablecloth" align="center">
                        <c:if test="${!empty actionBean.senaraiKandunganPerakuanPtg}">
                            <c:set value="1" var="i"/>
                            <c:forEach items="${actionBean.senaraiKandunganPerakuanPtg}" var="line">
                                <tr>
                                    <td style="text-align: right">7.${i}</td>
                                    <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5"/></td>
                                </tr>
                                <c:set value="${i+1}" var="i"/>
                            </c:forEach>
                        </c:if>
                    </table>
                </fieldset>
            </div>

            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        8. KEPUTUSAN
                        <span style="float:right">
                            <c:if test="${actionBean.disKertasMMKController.kPerakuanPTG}">
                                <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                                <a onclick="openFrame('kKeputusan');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                </c:if>
                        </span>
                    </legend>
                    <table class="tablecloth" align="center">
                        <c:if test="${!empty actionBean.senaraiKandunganKeputusan}">
                            <c:set value="1" var="i"/>
                            <c:forEach items="${actionBean.senaraiKandunganKeputusan}" var="line">
                                <tr>
                                    <td style="text-align: right">8.${i}</td>
                                    <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5"/></td>
                                </tr>
                                <c:set value="${i+1}" var="i"/>
                            </c:forEach>
                        </c:if>
                    </table>
                </fieldset>
            </div>
        </c:if>
    </c:if>            
</s:form>
