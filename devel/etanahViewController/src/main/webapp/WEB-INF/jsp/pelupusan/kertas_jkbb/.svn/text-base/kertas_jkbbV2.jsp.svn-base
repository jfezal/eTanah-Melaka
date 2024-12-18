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
        window.open("${pageContext.request.contextPath}/pelupusan/kertas_JKBBV2?openFrame&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }
    
    function refreshV2KertasJKBB(type){
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_JKBBV2?refreshpage&type='+type;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        doUnBlockUI();
    }
        
</script>

<s:form beanclass="etanah.view.stripes.pelupusan.KertasJKBBV2ActionBean">
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
                    <td>2.2.${i}</td>
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
<!--                <tr>
                    <td style="text-align: right">2.3</td>
                    <td><b>Perihal Pemohon</b>
                    <%-- <c:if test="${fn:length(actionBean.listPemohon) eq 1}">
                            <span style="float:right">
                                <c:if test="${actionBean.disKertasMMKController.kLatarBelakang}">
                                    <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                                    <a onclick="openFrame('kPemohon');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                <%--</c:if>
                            </span>
                        </c:if> --%>

                    </td>
                </tr>-->
                <c:if test="${actionBean.kelompok eq false}">
                    <c:if test="${fn:length(actionBean.listPemohon) > 1}">
                        <%--if more than 1--%>
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

    <div class="subtitle">
        <fieldset class="aras1">
            <c:set var="string1" value="${actionBean.kawasanAdun}" />
            <c:set var="adunKawasan" value="${fn:toUpperCase(string1)}" />
            <legend>
                4. ULASAN Y.B ADUN KAWASAN ${adunKawasan}
            </legend>
            <table class="tablecloth" align="center">
                <tr>
                    <td style="text-align: right">4.1</td>
                    <td>${actionBean.defaultKandunganAdun}</td>
                </tr>
                <c:set value="1" var="i"/>
                <c:forEach items="${actionBean.senaraiUlasanAdun}" var="line">
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

<!--    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                5. ASAS - ASAS PERTIMBANGAN
                <span style="float:right">
                    <c:if test="${actionBean.disKertasMMKController.kLatarBelakang}">
                        <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                        <a onclick="openFrame('kAsas');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                        </c:if>
                </span>
            </legend>
            <table class="tablecloth" align="center">
                <c:if test="${!empty actionBean.senaraiKandunganAsas}">
                    <c:set value="1" var="i"/>
                    <c:forEach items="${actionBean.senaraiKandunganAsas}" var="line">
                        <tr>
                            <td style="text-align: right">5.${i}</td>
                            <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
                        </tr>
                        <c:set value="${i+1}" var="i"/>
                    </c:forEach>
                </c:if>    
            </table>
        </fieldset>
    </div> -->
               <%-- 
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                6. PERAKUAN PENOLONG PEGAWAI TANAH TERTINGGI
                <span style="float:right">
                    <c:if test="${actionBean.disKertasMMKController.kLatarBelakang}">
                        <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | 
                        <a onclick="openFrame('kPTertinggi');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                        </c:if>
                </span>
            </legend>
            <table class="tablecloth" align="center">
                <c:if test="${!empty actionBean.senaraiLaporanKandunganPerakuanPegawaiTertinggi}">
                    <c:set value="1" var="i"/>
                    <c:forEach items="${actionBean.senaraiLaporanKandunganPerakuanPegawaiTertinggi}" var="line">
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
                5. PERAKUAN PENTADBIR TANAH ${daerah}
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
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTD' }">
                            <c:import url="kertas_jkbbView/syarat/syaratBatuan.jsp"></c:import>
                        </c:when>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTG' }">
                            <c:import url="kertas_jkbbView/syarat/syaratBatuan.jsp"></c:import>
                        </c:when>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB' }">
                            <c:import url="kertas_jkbbView/syarat/syaratBatuan.jsp"></c:import>
                        </c:when>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMMK' }">
                            <c:import url="kertas_jkbbView/syarat/syaratBatuan.jsp"></c:import>
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
    </c:if>   
</s:form>
