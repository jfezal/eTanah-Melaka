<%-- 
    Document   : kertasMMKNS
    Created on : Apr 11, 2013, 11:49:10 AM
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
        window.open("${pageContext.request.contextPath}/pelupusan/kertas_MMKV2?openFrame&type=" + type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }

    function refreshV2KertasMMK(type) {
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_MMKV2?refreshpage&type=' + type;
        $.get(url,
        function(data) {
            $('#page_div').html(data);
        }, 'html');
        doUnBlockUI();
    }
    
    function editMohonHakmilik(idMohonHakmilik,type){
        doBlockUI();
        //        alert(idHakmilik);
        window.open("${pageContext.request.contextPath}/pelupusan/kertas_MMKV2?editDetails&idMH="+idMohonHakmilik
            +"&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }

</script>

<s:form beanclass="etanah.view.stripes.pelupusan.KertasMMKV2ActionBean">
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
            <br>
            <legend>
                2. LATAR BELAKANG
            </legend>
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'JMRE'}">
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
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'JMRE'}">
                <span style="float:right">
                    <c:if test="${actionBean.disKertasMMKController.kLatarBelakang}">
                        <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                        <a onclick="openFrame('kPermohonan');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                        </c:if>
                </span>
                <br>
                <table class="tablecloth" align="center">
                    <c:if test="${empty actionBean.senaraiKandunganPerihalPermohonan}">
                        <tr>
                            <td style="text-align: right">2.1</td>
                            <td><s:textarea value="Sila Tambah Ayat Di Sini" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
                        </tr>
                    </c:if> 
                    <c:if test="${!empty actionBean.senaraiKandunganPerihalPermohonan}">
                        <c:set value="1" var="i"/>
                        <c:forEach items="${actionBean.senaraiKandunganPerihalPermohonan}" var="line">
                            <tr>
                                <td style="text-align: right">2.${i}</td>
                                <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
                            </tr>
                            <c:set value="${i+1}" var="i"/>
                        </c:forEach>
                    </c:if>      
                </table>
            </c:if>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <!--utk kod urusan JMRE sahaja-->
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'JMRE'}">
                <br>
                <legend>
                    3. ULASAN PENASIHAT UNDANG-UNDANG NEGERI
                    <span style="float:right">
                        <c:if test="${actionBean.disKertasMMKController.kUlasanPenasihat}">
                            <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                            <a onclick="openFrame('kUlasanPenasihat');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                            </c:if>
                    </span>
                </legend>
                <table class="tablecloth" align="center">
                    <c:if test="${empty actionBean.senaraiUlasanPenasihat}">
                        <tr>
                            <td style="text-align: right">3.1</td>
                            <td><s:textarea value="Sila Tambah Ayat Di Sini" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
                        </tr>
                    </c:if> 
                    <c:if test="${!empty actionBean.senaraiUlasanPenasihat}">
                        <c:set value="1" var="i"/>
                        <c:forEach items="${actionBean.senaraiUlasanPenasihat}" var="line">
                            <tr>
                                <td style="text-align: right">3.${i}</td>
                                <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
                            </tr>
                            <c:set value="${i+1}" var="i"/>
                        </c:forEach>
                    </c:if>    
                </table>
            </c:if>

            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'JMRE'}">
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
            </c:if>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'JMRE'}">
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
            </c:if>
        </fieldset>
    </div>
    <c:if test="${actionBean.kelompok eq false}">
        <div class="subtitle">
            <fieldset class="aras1">
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'JMRE'}">
                    <c:choose>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LPJH'}">
                            <legend>
                                5. MAKLUMAT KAWASAN YANG DIPOHON
                            </legend>
                        </c:when>
                        <c:otherwise>
                            <legend>
                                5. MAKLUMAT PEMILIKAN TANAH PEMOHON
                            </legend>
                        </c:otherwise>
                    </c:choose>

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

                                        <%--length ${fn:length(actionBean.listDisPemilikanTanah)}--%>
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
                </c:if>
            </fieldset>
        </div>

        <div class="subtitle">
            <fieldset class="aras1">
                <c:choose>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PCRG' || actionBean.permohonan.kodUrusan.kod eq 'LPJH'}">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'JMRE' && actionBean.permohonan.kodUrusan.kod ne 'PBMT' }">
                            <legend>
                                6. HURAIAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN

                                <span style="float:right">
                                    <c:if test="${actionBean.disKertasMMKController.kPerakuanPTD}">
                                        <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                                        <a onclick="openFrame('kHuraianPtd');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
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
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'JMRE' && actionBean.permohonan.kodUrusan.kod ne 'PBMT' }">
                            <legend>
                                6. HURAIAN <c:choose><c:when test="${actionBean.permohonan.cawangan.kod eq '08'}">TIMBALAN PENTADBIR TANAH GEMAS</c:when><c:otherwise>PENTADBIR TANAH ${actionBean.permohonan.cawangan.daerah.nama}</c:otherwise></c:choose>

                                <span style="float:right">
                                    <c:if test="${actionBean.disKertasMMKController.kPerakuanPTD}">
                                        <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                                        <a onclick="openFrame('kHuraianPtd');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
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
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </fieldset>
        </div>   

        <div class="subtitle">
            <fieldset class="aras1">
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'JMRE' && actionBean.permohonan.kodUrusan.kod ne 'BMRE'}">
                    <legend>
                        <c:choose>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                                6. HURAIAN
                            </c:when>
                            <c:otherwise>
                                7. PERAKUAN
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PCRG'}">
                                PERAKUAN JAWATANKUASA JKSMN
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LPJH'}">
                                PERAKUAN MESYUARAT JAWATANKUASA SUMBER MINERAL NEGERI
                            </c:when>
                            <c:otherwise>
                                <c:choose><c:when test="${actionBean.permohonan.cawangan.kod eq '08'}">TIMBALAN PENTADBIR TANAH GEMAS</c:when><c:otherwise>PENTADBIR TANAH ${actionBean.permohonan.cawangan.daerah.nama}</c:otherwise></c:choose>
                            </c:otherwise>
                        </c:choose>


                        <span style="float:right">
                            <c:if test="${actionBean.disKertasMMKController.kPerakuanPTD}">
                                <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                                <a onclick="openFrame('kPerakuanPtd');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                </c:if>
                        </span>
                    </legend>
                    <table class="tablecloth" align="center">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'BMRE' and actionBean.permohonan.kodUrusan.kod ne 'MMRE' and actionBean.permohonan.kodUrusan.kod ne 'WMRE' and actionBean.permohonan.kodUrusan.kod ne 'JMRE'}">
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
                        </c:if>
                        <c:if test="${!empty actionBean.senaraiKandunganPerakuanPtd}">
                            <c:set value="1" var="i"/>
                            <c:forEach items="${actionBean.senaraiKandunganPerakuanPtd}" var="line">
                                <tr>
                                    <td style="text-align: right"><c:choose>
                                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                                                6.${i}
                                            </c:when>
                                            <c:otherwise>
                                                7.${i}
                                            </c:otherwise>
                                        </c:choose></td>
                                    <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
                                </tr>
                                <c:set value="${i+1}" var="i"/>
                            </c:forEach>
                        </c:if>
                    </table>
                    <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL'}">
                        <center>
                            <c:choose>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                                    <c:import url="kertasMMKView/syarat/syaratPBMT.jsp"></c:import>
                                </c:when>
                            </c:choose>
                        </center>
                    </c:if>
                </c:if>
            </fieldset>
        </div>   

        <div class="subtitle">
            <fieldset class="aras1">
                <!--EDIT BMRE,MMRE,WMRE,PBRZ,JMRE BY SYAZWAN -->
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'LPSP' && actionBean.permohonan.kodUrusan.kod ne 'PTPBP' && actionBean.permohonan.kodUrusan.kod ne 'PRMMK' && actionBean.permohonan.kodUrusan.kod ne 'BMRE' && actionBean.permohonan.kodUrusan.kod ne 'MMRE' && actionBean.permohonan.kodUrusan.kod ne 'WMRE' && actionBean.permohonan.kodUrusan.kod ne 'PBRZ' && actionBean.permohonan.kodUrusan.kod ne 'JMRE'}">
                    <legend>
                        <c:choose>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                                7. KEPUTUSAN MESYUARAT JAWATANKUASA TANAH DAERAH 
                            </c:when>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PCRG' || actionBean.permohonan.kodUrusan.kod eq 'LPJH'}">
                                8. SYOR PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN
                            </c:when>
                            <c:otherwise>
                                8. KEPUTUSAN MESYUARAT JAWATANKUASA TANAH DAERAH 
                            </c:otherwise>
                        </c:choose>

                        <span style="float:right">
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPRU'}">
                                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTGSA'}">
                                    <c:if test="${actionBean.disKertasMMKController.kPerakuanPTD}">
                                        <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                                        <a onclick="openFrame('kKeputusanJktd');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                        </c:if>
                                    </c:if>
                                </c:if>
                        </span>
                    </legend> 
                </c:if>
                <table class="tablecloth" align="center">
                    <c:if test="${!empty actionBean.senaraiKandunganKeputusanJktd}">
                        <c:set value="1" var="i"/>
                        <c:forEach items="${actionBean.senaraiKandunganKeputusanJktd}" var="line">
                            <tr>
                                <td style="text-align: right"><c:choose>
                                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                                            7.${i}
                                        </c:when>
                                        <c:otherwise>
                                            8.${i}
                                        </c:otherwise>
                                    </c:choose></td>
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
                <c:choose>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PCRG' || actionBean.permohonan.kodUrusan.kod eq 'LPJH'}">
                        <legend>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PCRG'}">
                                9. PENUTUP
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LPJH'}">
                                9. KEPUTUSAN
                            </c:if>

                            <span style="float:right">
                                <c:if test="${actionBean.disKertasMMKController.penutup}">
                                    <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                                    <a onclick="openFrame('kPenutup');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                    </c:if>
                            </span>
                        </legend>
                        <table class="tablecloth" align="center">
                            <c:if test="${!empty actionBean.senaraiKandunganPenutup}">
                                <c:set value="1" var="i"/> 
                                <c:forEach items="${actionBean.senaraiKandunganPenutup}" var="line">
                                    <tr>
                                        <td style="text-align: right">9.${i}</td>
                                        <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
                                    </tr>
                                    <c:set value="${i+1}" var="i"/>
                                </c:forEach>
                            </c:if>
                        </table>
                    </c:when>

                </c:choose>
            </fieldset>
        </div>

        <c:if test="${actionBean.disKertasMMKController.vPTG}">

            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'LPSP' && actionBean.permohonan.kodUrusan.kod ne 'PTPBP' && actionBean.permohonan.kodUrusan.kod ne 'PRMMK' && actionBean.permohonan.kodUrusan.kod ne 'BMRE' && actionBean.permohonan.kodUrusan.kod ne 'MMRE' && actionBean.permohonan.kodUrusan.kod ne 'WMRE' && actionBean.permohonan.kodUrusan.kod ne 'PBRZ' && actionBean.permohonan.kodUrusan.kod ne 'JMRE' && actionBean.permohonan.kodUrusan.kod ne 'PBMT' && actionBean.permohonan.kodUrusan.kod ne 'LPJH'}">
                            9. HURAIAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP' || actionBean.permohonan.kodUrusan.kod eq 'PTPBP' || actionBean.permohonan.kodUrusan.kod eq 'PRMMK' || actionBean.permohonan.kodUrusan.kod eq 'BMRE' || actionBean.permohonan.kodUrusan.kod eq 'MMRE' || actionBean.permohonan.kodUrusan.kod eq 'WMRE' || actionBean.permohonan.kodUrusan.kod eq 'PBRZ' || actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                            8. HURAIAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'JMRE'}">
                            4. HURAIAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN
                        </c:if>
                        <span style="float:right">
                            <c:if test="${actionBean.disKertasMMKController.kPerakuanPTG}">
                                <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                                <a onclick="openFrame('kHuraianPtg');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                </c:if>
                        </span>
                    </legend>
                    <table class="tablecloth" align="center">
                        <c:if test="${empty actionBean.senaraiKandunganHuraianPtg}">
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'JMRE'}">
                                <tr>
                                    <td style="text-align: right">4.1</td>
                                    <td><s:textarea value="Sila Tambah Ayat Di Sini" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
                                </tr>
                            </c:if>
                        </c:if>
                        <c:if test="${!empty actionBean.senaraiKandunganHuraianPtg}">
                            <c:set value="1" var="i"/>
                            <c:forEach items="${actionBean.senaraiKandunganHuraianPtg}" var="line">
                                <tr>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'LPSP' && actionBean.permohonan.kodUrusan.kod ne 'PTPBP' && actionBean.permohonan.kodUrusan.kod ne 'PRMMK' && actionBean.permohonan.kodUrusan.kod ne 'BMRE' && actionBean.permohonan.kodUrusan.kod ne 'MMRE' && actionBean.permohonan.kodUrusan.kod ne 'WMRE' && actionBean.permohonan.kodUrusan.kod ne 'PBRZ' && actionBean.permohonan.kodUrusan.kod ne 'JMRE' && actionBean.permohonan.kodUrusan.kod ne 'PBMT'}">
                                        <td style="text-align: right">9.${i}</td>
                                    </c:if>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP' || actionBean.permohonan.kodUrusan.kod eq 'PTPBP' || actionBean.permohonan.kodUrusan.kod eq 'PRMMK' || actionBean.permohonan.kodUrusan.kod eq 'BMRE' || actionBean.permohonan.kodUrusan.kod eq 'MMRE' || actionBean.permohonan.kodUrusan.kod eq 'WMRE' || actionBean.permohonan.kodUrusan.kod eq 'PBRZ' || actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                                        <td style="text-align: right">8.${i}</td>
                                    </c:if>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'JMRE'}">
                                        <td style="text-align: right">4.${i}</td>
                                    </c:if>
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
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'JMRE'}">
                            <br>
                            5. SYOR PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP' || actionBean.permohonan.kodUrusan.kod eq 'PTPBP' || actionBean.permohonan.kodUrusan.kod eq 'PRMMK' || actionBean.permohonan.kodUrusan.kod eq 'BMRE' || actionBean.permohonan.kodUrusan.kod eq 'MMRE' || actionBean.permohonan.kodUrusan.kod eq 'WMRE' || actionBean.permohonan.kodUrusan.kod eq 'PBRZ' || actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                            9. SYOR PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'LPSP' && actionBean.permohonan.kodUrusan.kod ne 'PTPBP' && actionBean.permohonan.kodUrusan.kod ne 'PRMMK' && actionBean.permohonan.kodUrusan.kod ne 'BMRE' && actionBean.permohonan.kodUrusan.kod ne 'MMRE' && actionBean.permohonan.kodUrusan.kod ne 'WMRE' && actionBean.permohonan.kodUrusan.kod ne 'PBRZ' && actionBean.permohonan.kodUrusan.kod ne 'JMRE' && actionBean.permohonan.kodUrusan.kod ne 'PBMT' && actionBean.permohonan.kodUrusan.kod ne 'LPJH'}">
                            10. SYOR PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN
                        </c:if>
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
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP' || actionBean.permohonan.kodUrusan.kod eq 'PTPBP' || actionBean.permohonan.kodUrusan.kod eq 'PRMMK' || actionBean.permohonan.kodUrusan.kod eq 'BMRE' || actionBean.permohonan.kodUrusan.kod eq 'MMRE' || actionBean.permohonan.kodUrusan.kod eq 'WMRE' || actionBean.permohonan.kodUrusan.kod eq 'PBRZ' || actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                                        <td style="text-align: right">9.${i}</td>
                                    </c:if>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'LPSP' && actionBean.permohonan.kodUrusan.kod ne 'PTPBP' && actionBean.permohonan.kodUrusan.kod ne 'PRMMK' && actionBean.permohonan.kodUrusan.kod ne 'BMRE' && actionBean.permohonan.kodUrusan.kod ne 'MMRE' && actionBean.permohonan.kodUrusan.kod ne 'WMRE' && actionBean.permohonan.kodUrusan.kod ne 'PBRZ' && actionBean.permohonan.kodUrusan.kod ne 'JMRE' && actionBean.permohonan.kodUrusan.kod ne 'PBMT'}">
                                        <td style="text-align: right">10.${i}</td>
                                    </c:if>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'JMRE'}">
                                        <td style="text-align: right">5.${i}</td>
                                    </c:if>
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
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'JMRE'}">
                            6. KEPUTUSAN
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP' || actionBean.permohonan.kodUrusan.kod eq 'PTPBP' || actionBean.permohonan.kodUrusan.kod eq 'PRMMK' || actionBean.permohonan.kodUrusan.kod eq 'BMRE' || actionBean.permohonan.kodUrusan.kod eq 'MMRE' || actionBean.permohonan.kodUrusan.kod eq 'WMRE' || actionBean.permohonan.kodUrusan.kod eq 'PBRZ' || actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                            10. KEPUTUSAN
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'LPSP' && actionBean.permohonan.kodUrusan.kod ne 'PTPBP' && actionBean.permohonan.kodUrusan.kod ne 'PRMMK' && actionBean.permohonan.kodUrusan.kod ne 'BMRE' && actionBean.permohonan.kodUrusan.kod ne 'MMRE' && actionBean.permohonan.kodUrusan.kod ne 'WMRE' && actionBean.permohonan.kodUrusan.kod ne 'PBRZ' && actionBean.permohonan.kodUrusan.kod ne 'JMRE'  && actionBean.permohonan.kodUrusan.kod ne 'PBMT'  && actionBean.permohonan.kodUrusan.kod ne 'LPJH'}">
                            11. KEPUTUSAN
                        </c:if>
                        <span style="float:right">
                            <c:if test="${actionBean.disKertasMMKController.kPerakuanPTG}">
                                <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                                <a onclick="openFrame('kKeputusan');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                </c:if>
                        </span>
                    </legend>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'LPJH'}">
                        <table class="tablecloth" align="center">
                            <c:if test="${!empty actionBean.senaraiKandunganKeputusan}">
                                <c:set value="1" var="i"/>
                                <c:forEach items="${actionBean.senaraiKandunganKeputusan}" var="line">
                                    <tr>
                                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP' || actionBean.permohonan.kodUrusan.kod eq 'PTPBP' || actionBean.permohonan.kodUrusan.kod eq 'PRMMK' || actionBean.permohonan.kodUrusan.kod eq 'BMRE' || actionBean.permohonan.kodUrusan.kod eq 'MMRE' || actionBean.permohonan.kodUrusan.kod eq 'WMRE' || actionBean.permohonan.kodUrusan.kod eq 'PBRZ' || actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                                            <td style="text-align: right">10.${i}</td>
                                        </c:if>
                                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'LPSP' && actionBean.permohonan.kodUrusan.kod ne 'PTPBP' && actionBean.permohonan.kodUrusan.kod ne 'PRMMK' && actionBean.permohonan.kodUrusan.kod ne 'BMRE' && actionBean.permohonan.kodUrusan.kod ne 'MMRE' && actionBean.permohonan.kodUrusan.kod ne 'WMRE' && actionBean.permohonan.kodUrusan.kod ne 'PBRZ' && actionBean.permohonan.kodUrusan.kod ne 'JMRE' && actionBean.permohonan.kodUrusan.kod ne 'PBMT'}">
                                            <td style="text-align: right">11.${i}</td>
                                        </c:if>
                                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'JMRE'}">
                                            <td style="text-align: right">6.${i}</td>
                                        </c:if>
                                        <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
                                    </tr>
                                    <c:set value="${i+1}" var="i"/>
                                </c:forEach>
                            </c:if>    
                        </table>
                    </c:if>

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
        <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> |
        <a onclick="openFrame('kHuraianPtd');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
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
</div>   --%>

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
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT' || actionBean.permohonan.kodUrusan.kod eq 'LPSP' }">
                                <c:import url="kertasMMKView/syarat/syaratPBMT.jsp"></c:import>
                            </c:when>
                        </c:choose>
                    </center>
                </c:if>
            </fieldset>
        </div>   

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    6. KEPUTUSAN MESYUARAT JAWATANKUASA TANAH DAERAH
                    <span style="float:right">
                        <c:if test="${actionBean.disKertasMMKController.kPerakuanPTD}">
                            <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                            <a onclick="openFrame('kKeputusanJktd');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                            </c:if>
                    </span>
                </legend>
                <table class="tablecloth" align="center">
                    <c:if test="${!empty actionBean.senaraiKandunganKeputusanJktd}">
                        <c:set value="1" var="i"/>
                        <c:forEach items="${actionBean.senaraiKandunganKeputusanJktd}" var="line">
                            <tr>
                                <td style="text-align: right">6.${i}</td>
                                <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
                            </tr>
                            <c:set value="${i+1}" var="i"/>
                        </c:forEach>
                        <tr>
                            <td colspan="2">
                                <b>Senarai Syor Hakmilik Permohonan Lulus</b>
                                <display:table class="tablecloth" name="${actionBean.senaraiHakmilikLulus}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                                               requestURI="/pelupusan/rekod_keputusanV2">
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
                                               requestURI="/pelupusan/rekod_keputusanV2">
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
                    </c:if>    
                </table>
            </fieldset>
        </div>             
        <c:if test="${actionBean.disKertasMMKController.vPTG}">

            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        7. HURAIAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN
                        <span style="float:right">
                            <c:if test="${actionBean.disKertasMMKController.kPerakuanPTG}">
                                <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                                <a onclick="openFrame('kHuraianPtg');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                </c:if>
                        </span>
                    </legend>
                    <table class="tablecloth" align="center">
                        <c:if test="${!empty actionBean.senaraiKandunganHuraianPtg}">
                            <c:set value="1" var="i"/>
                            <c:forEach items="${actionBean.senaraiKandunganHuraianPtg}" var="line">
                                <tr>
                                    <td style="text-align: right">7.${i}</td>
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
                        8. SYOR PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN
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
                                    <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
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