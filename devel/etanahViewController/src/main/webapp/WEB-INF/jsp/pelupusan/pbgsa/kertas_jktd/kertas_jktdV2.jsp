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
        
    function openFrame(type){
        doBlockUI();
        window.open("${pageContext.request.contextPath}/pelupusan/kertas_JKTDPBGSA?openFrame&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }
    
    function refreshV2KertasJKTD(type){
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_JKTDPBGSA?refreshpage&type='+type;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        doUnBlockUI();
    }
        
</script>

<s:form beanclass="etanah.view.stripes.pelupusan.KertasJKTDPBGSAActionBean">
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                <span style="float:right">
                    <c:if test="${actionBean.disKertasMMKController.kTajuk}">
                        <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                        <a onclick="openFrame('kTajuk');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
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
                        <a onclick="openFrame('kTujuan');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
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
                                <a onclick="openFrame('kPermohonan');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
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
                                <a onclick="openFrame('kTanah');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
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
                3. BUTIR-BUTIR PESERTA
            </legend>
            <table class="tablecloth" align="center">

                <tr>
                    <td style="text-align: right">3.1</td>
                    <td><b>Perihal Peserta</b></td>
                </tr>
                <tr>
                    <td colspan="2">
                <display:table class="tablecloth" name="${actionBean.penempatanPesertaList}" cellpadding="0" cellspacing="0" id="line"
                       requestURI="${pageContext.request.contextPath}/pelupusan/penempatan_peserta">

                    <display:column title="Bil">${line_rowNum}
                        <s:hidden name="" class="${line_rowNum -1}" value="${line.idPenempatanPeserta}"/>
                    </display:column>
                    <display:column property="nama" title="Nama"/>
                    <display:column property="noPengenalan" title="Nombor Pengenalan" />

                    <display:column title="Alamat" >${line.alamat1}
                        <c:if test="${line.alamat2 ne null}"> , </c:if>
                        ${line.alamat2}
                        <c:if test="${line.alamat3 ne null}"> , </c:if>
                        ${line.alamat3}
                        <c:if test="${line.alamat4 ne null}"> , </c:if>
                        ${line.alamat4}</display:column>
                    <display:column property="poskod" title="Poskod" />
                    <display:column property="negeri.nama" title="Negeri" />
                    <display:column property="markahTemuduga" title="Markah Temuduga" />               

                </display:table>
                    </td>
                </tr>
                <br>
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



        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    6. HURAIAN PENTADBIR TANAH ${actionBean.permohonan.cawangan.daerah.nama}
                    <span style="float:right">
                        <c:if test="${actionBean.disKertasMMKController.kPerakuanPTD}">
                            <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
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

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    7. SYOR PENTADBIR TANAH ${actionBean.permohonan.cawangan.daerah.nama}
                    <span style="float:right">
                        <c:if test="${actionBean.disKertasMMKController.kPerakuanPTD}">
                            <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                            <a onclick="openFrame('kPerakuanPtd');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
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
                                <td style="text-align: right">7.${i}</td>
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
                                <a onclick="openFrame('kPerakuanPtg');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
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
                                <a onclick="openFrame('kKeputusan');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
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
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    5. HURAIAN PENTADBIR TANAH ${actionBean.permohonan.cawangan.daerah.nama}
                    <span style="float:right">
                        <c:if test="${actionBean.disKertasMMKController.kPerakuanPTD}">
                            <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
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
        </div>

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    6. SYOR PENTADBIR TANAH ${actionBean.permohonan.cawangan.daerah.nama}
                    <span style="float:right">
                        <c:if test="${actionBean.disKertasMMKController.kPerakuanPTD}">
                            <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                            <a onclick="openFrame('kPerakuanPtd');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
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
                        7. PERAKUAN PENGARAH TANAH DAN GALIAN
                        <span style="float:right">
                            <c:if test="${actionBean.disKertasMMKController.kPerakuanPTG}">
                                <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                                <a onclick="openFrame('kPerakuanPtg');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
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
                                <a onclick="openFrame('kKeputusan');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
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
