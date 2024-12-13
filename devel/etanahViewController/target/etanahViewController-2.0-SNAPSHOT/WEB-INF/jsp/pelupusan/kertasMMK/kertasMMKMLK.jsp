<%-- 
    Document   : kertasMMK
    Created on : Apr 8, 2013, 11:02:26 AM
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
        window.open("${pageContext.request.contextPath}/pelupusan/kertas_MMKV2?openFrame&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }

    function openFramejtek(type,index){
        doBlockUI();
        window.open("${pageContext.request.contextPath}/pelupusan/kertas_MMKV2?openFramejtek&type="+type+'&index='+index, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }
    
    function refreshV2KertasMMK(type){
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_MMKV2?refreshpage&type='+type;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        doUnBlockUI();
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
                    <c:set value="1" var="i"/>
                    <c:forEach items="${actionBean.senaraiKandunganPerihalTanah}" var="line">
                        <tr>
                            <td style="text-align: right">2.2.${i}</td>
                            <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
                        </tr>
                        <c:set value="${i+1}" var="i"/>
                    </c:forEach>
                </c:if>
                <tr>
                    <td>2.2.${i+1}</td>
                    <td><s:textarea value="${actionBean.defaultPerihalSempadan}" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
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
                <tr>
                    <td style="text-align: right">2.3</td>
                    <td><b>Perihal Pemohon</b>
                        <c:if test="${fn:length(actionBean.listPemohon) eq 1}">
                            <span style="float:right">
                                <c:if test="${actionBean.disKertasMMKController.kLatarBelakang}">
                                    <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                                    <a onclick="openFrame('kPemohon');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                    </c:if>
                            </span>
                        </c:if>

                    </td>
                </tr>
                <%--<c:if test="${actionBean.kelompok eq false}">
                    <c:if test="${fn:length(actionBean.listPemohon) > 1}">
                        
                        <tr>
                            <td style="text-align: right">2.3.1</td>
                            <td>${actionBean.defaultPerihalPemohon}</td>
                        </tr>
                    </c:if>

                    <c:if test="${fn:length(actionBean.listPemohon) eq 1}">
                        <c:if test="${!empty actionBean.senaraiKandunganPemohon}">
                            <c:set value="1" var="i"/>
                            <c:forEach items="${actionBean.senaraiKandunganPemohon}" var="line">
                                <tr>
                                    <td style="text-align: right">2.3.${i}</td>
                                    <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
                                </tr>
                                <c:set value="${i+1}" var="i"/>
                            </c:forEach>
                        </c:if>
                    </c:if>
                </c:if>--%>
                <c:if test="${actionBean.kelompok eq false}">
                    <c:if test="${!empty actionBean.senaraiKandunganPemohon}">
                            <c:set value="1" var="i"/>
                            <c:forEach items="${actionBean.senaraiKandunganPemohon}" var="line">
                                <tr>
                                    <td style="text-align: right">2.3.${i}</td>
                                    <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
                                </tr>
                                <c:set value="${i+1}" var="i"/>
                            </c:forEach>
                      </c:if>                    
                </c:if>
                <c:if test="${actionBean.kelompok eq true}">
                    <tr>
                        <td style="text-align: right">2.3.1</td>
                        <td>${actionBean.defaultPerihalPemohon}</td>
                    </tr>
                </c:if>        
            </table>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                3. ULASAN - ULASAN JABATAN TEKNIKAL
            </legend>
            <table class="tablecloth" align="center">
                <tr>
                    <td style="text-align: right">3.1</td>
                    <td>${actionBean.defaultKandunganJbtn}</td>
                </tr>
                <c:set value="1" var="i"/>
                <c:forEach items="${actionBean.senaraiUlasanJabatanTeknikalNamaAgensi}" var="line">
                    <c:if test="${line.namaAgensi ne null}">
                        <tr>
                            <td style="text-align: right">${i}</td>
                            <td><font style="font-weight:bold;">${line.namaAgensi}</font>
                                <span style="float:right">
                                    <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                                    <a onclick="openFramejtek('jtek',${line.agensi.kod});" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                </span>
                            </td>
                        </tr>
                    </c:if>
                    <c:forEach items="${actionBean.senaraiUlasanJabatanTeknikal}" var="list">
                        <c:if test="${line.agensi.kod eq list.agensi.kod}">
                            <c:if test="${list.noRujukan ne null}">
                                <tr>
                                    <td>&nbsp;</td>
                                    <td>( Ruj. Surat ${line.noRujukan} yang diterima pada <s:format value="${line.tarikhKuatkuasa}" formatPattern="dd/MM/yyyy"/> )</td>
                                </tr>
                            </c:if>
                            <tr>
                                <td>&nbsp;</td>
                                <td>${list.ulasan}
                                    <s:hidden name="idRujukanLuar${i}" value="${line.idRujukan}" id="idRujukanLuar$${i}"/>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    <c:set var="i" value="${i+1}" />
                </c:forEach> 
            </table>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <c:forEach items="${actionBean.senaraiUlasanAdun}" var="line">
                <c:set var="string1" value="${actionBean.kawasanAdun}" />
                <c:set var="adunKawasan" value="${fn:toUpperCase(string1)}" />
                <legend>
                    4. ULASAN Y.B ADUN KAWASAN ${adunKawasan}, ${line.namaAgensi}
                </legend>
                <table class="tablecloth" align="center">
                    <tr>
                        <td style="text-align: right">4.1</td>
                        <td>${actionBean.defaultKandunganAdun}</td>
                    </tr>
                    <c:set value="1" var="i"/>
                    <%--<tr>
                        <td style="text-align: right">${i}</td>
                        <td><font style="font-weight:bold;">${line.namaAgensi}</font></td>
                    </tr>--%>
                    <tr>
                        <td>&nbsp;</td>
                        <td> Ulasan diterima pada <s:format value="${line.tarikhTerima}" formatPattern="dd/MM/yyyy"/> </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>${line.ulasan}
                            <s:hidden name="idRujukanLuar${i}" value="${line.idRujukan}" id="idRujukanLuar$${i}"/>
                        </td>
                    </tr>
                    <c:set var="i" value="${i+1}" />
                </table>
            </c:forEach>
        </fieldset>
    </div>
    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR'}">

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    ULASAN BADAN PENGAWAL
                </legend>
                <table class="tablecloth" align="center">
                    <tr>
                        <td>Keputusan : 
                        </td>
                        <td><c:choose>
                                <c:when test="${actionBean.senaraiUlasanPengawal.diSokong eq '1'}">
                                    Disokong 
                                </c:when>
                                <c:when test="${actionBean.senaraiUlasanPengawal.diSokong eq '2'}">
                                    Ditolak
                                </c:when>
                                <c:when test="${actionBean.senaraiUlasanPengawal.diSokong eq '3'}">
                                    Tiada Syor
                                </c:when>
                                <c:otherwise></c:otherwise>
                            </c:choose></td>
                    </tr>
                    <tr>
                        <td>Ulasan :</td>
                        <td><s:textarea value="${actionBean.senaraiUlasanPengawal.ulasan}" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/>
                        </td>
                    </tr>
                    <c:set var="i" value="${i+1}" />
                </table>
            </fieldset>
        </div>            

    </c:if>

    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG' or actionBean.permohonan.kodUrusan.kod eq 'MLCRG'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    5. KEPUTUSAN JAWATANKUASA SUMBER MINERAL
                </legend>
                <table class="tablecloth" align="center">
                    <tr>
                        <td>&nbsp;</td>
                        <td colspan="3">
                            <table border="0">
                                <tr>
                                    <td><div align="right">Syor : </div></td>
                                    <td colspan="3">
                                        <c:choose>
                                            <c:when test="${actionBean.mohonRujLuarJKM.diSokong eq 'L'}">
                                                Diluluskan
                                            </c:when>
                                            <c:when test="${actionBean.mohonRujLuarJKM.diSokong eq 'T'}">
                                                Ditolak
                                            </c:when>
                                        </c:choose>
                                    </td>
                                </tr>
                                <tr>
                                    <td><div align="right">Ulasan : </div></td>
                                    <td colspan="3">${actionBean.mohonRujLuarJKM.ulasan}</td>
                                </tr>
                                <c:set var="i" value="1" />
                                <c:set var="k" value="1" />
                                <c:forEach items="${actionBean.senaraiLaporanUlas}" var="line">

                                    <tr>
                                        <td>
                                            <c:choose>
                                                <c:when test="${i eq 1}">
                                                    Terma Dan Syarat :
                                                </c:when>
                                                <c:otherwise>
                                                    &nbsp;
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <%--<td>&nbsp;</td>--%>
                                        <td colspan="2">${i}) ${line.ulasan}</td>

                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                    <c:set var="k" value="${k+1}" />
                                </c:forEach>
                            </table>

                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>
    </c:if>

    <%--Cater komen NRE untuk numbering LMCRG dan MCLRG--%>

    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG' or actionBean.permohonan.kodUrusan.kod eq 'MLCRG'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    <c:set var="daerah" value="${actionBean.permohonan.cawangan.daerah.nama}"/>
                    6. PERAKUAN PENTADBIR TANAH ${fn:toUpperCase(daerah)}
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
                                <td style="text-align: right">5.${i}</td>
                                <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
                            </tr>
                            <c:set value="${i+1}" var="i"/>
                        </c:forEach>
                    </c:if>

                </table>
            </fieldset>
        </div>
    </c:if>

    <c:if test="${actionBean.disKertasMMKController.vPTG and actionBean.permohonan.kodUrusan.kod eq 'LMCRG' or actionBean.permohonan.kodUrusan.kod eq 'MLCRG'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    7. PERAKUAN PENGARAH TANAH DAN GALIAN MELAKA
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
                                <td style="text-align: right">6.${i}</td>
                                <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
                            </tr>
                            <c:set value="${i+1}" var="i"/>
                        </c:forEach>
                    </c:if>    
                </table>
            </fieldset>
        </div>
    </c:if> 
    <%--tamat komen NRE--%>

    <c:choose>
        <c:when test="${actionBean.stageId eq 'sedia_draf_rencana_mmkn' and actionBean.permohonan.kodUrusan.kod eq 'PRMP'}">
        </c:when>
        <c:when test="${actionBean.stageId eq 'semak_draf_rencana_mmkn1' and actionBean.permohonan.kodUrusan.kod eq 'PRMP'}">
        </c:when>
        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG'or actionBean.permohonan.kodUrusan.kod eq 'MLCRG' }">
        </c:when>
        <c:otherwise>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        <c:set var="daerah" value="${actionBean.permohonan.cawangan.daerah.nama}"/>
                        5. PERAKUAN PENTADBIR TANAH ${fn:toUpperCase(daerah)}
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
                                    <c:import url="kertasMMKView/syarat/syaratPBMT.jsp"></c:import>
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR' }">
                                    <c:import url="kertasMMKView/syarat/syaratPPTR.jsp"></c:import>
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU' }">
                                    <c:import url="kertasMMKView/syarat/syaratPPRU.jsp"></c:import>
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PRMP' }">
                                    <c:import url="kertasMMKView/syarat/syaratPRMP.jsp"></c:import>
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP' }">
                                    <c:import url="kertasMMKView/syarat/syaratLPSP.jsp"></c:import>
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS' }">
                                    <c:import url="kertasMMKView/syarat/syaratPLPS.jsp"></c:import>
                                </c:when>
                            </c:choose>
                        </center>
                    </c:if>
                </fieldset>
            </div>
        </c:otherwise>
    </c:choose>

    <c:if test="${actionBean.disKertasMMKController.vPTG}">
        <c:choose>
            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG'or actionBean.permohonan.kodUrusan.kod eq 'MLCRG' }">
            </c:when>
            <c:otherwise>
                <div class="subtitle">
                    <fieldset class="aras1">
                        <legend>
                            6. PERAKUAN PENGARAH TANAH DAN GALIAN MELAKA
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
                                        <td style="text-align: right">6.${i}</td>
                                        <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
                                    </tr>
                                    <c:set value="${i+1}" var="i"/>
                                </c:forEach>
                            </c:if>
                        </table>
                    </fieldset>
                </div>
            </c:otherwise>
        </c:choose>
    </c:if>   
</s:form>