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
        window.open("${pageContext.request.contextPath}/hasil/kertas_risalat?openFrame&type=" + type, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }

    function refreshV2KertasMMKHasil(type) {
        var url = '${pageContext.request.contextPath}/hasil/kertas_risalat?refreshpage&type=' + type;
        $.get(url,
                function(data) {
                    $('#page_div').html(data);
                }, 'html');
        doUnBlockUI();
    }

</script>

<s:form beanclass="etanah.view.stripes.hasil.KertasRisalatMMKNActionBean">
    <s:errors/>
    <s:messages/>
    <s:hidden name="hakmilikPermohonan.hakmilik.idHakmilik"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                <span style="float:right">
                    <%--<c:if test="${actionBean.disKertasMMKController.kTajuk}">--%>
                        <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                        <a onclick="openFrame('kTajuk');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                        <%--</c:if>--%>
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
                    <%--<c:if test="${actionBean.disKertasMMKController.kTujuan}">--%>
                        <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                        <a onclick="openFrame('kTujuan');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                        <%--</c:if>--%>
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
                            <%--<c:if test="${actionBean.disKertasMMKController.kLatarBelakang}">--%>
                                <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                                <a onclick="openFrame('kPermohonan');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                <%--</c:if>--%>
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
                            <%--<c:if test="${actionBean.disKertasMMKController.kLatarBelakang}">--%>
                                <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                                <a onclick="openFrame('kTanah');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                <%--</c:if>--%>
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
                    <td style="text-align: right">2.3</td>
                    <td><b>Perihal Pemohon</b>
                        <span style="float:right">
                            <%--<c:if test="${actionBean.disKertasMMKController.kLatarBelakang}">--%>
                                <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                                <a onclick="openFrame('kPerihal');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                <%--</c:if>--%>
                        </span>
                    </td>
                </tr>
                <c:if test="${!empty actionBean.senaraiKandunganPerihalPemohon}">
                    <c:set value="1" var="i"/>
                    <c:forEach items="${actionBean.senaraiKandunganPerihalPemohon}" var="line">
                        <tr>
                            <td style="text-align: right">2.3.${i}</td>
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
                3.ULASAN JABATAN KEWANGAN DAN PERBENDAHARAAN NEGERI MELAKA
                <span style="float:right">
                    <%--<c:if test="${actionBean.disKertasMMKController.kUlasan}">--%>
                        <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                        <a onclick="openFrame('kUlasan');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                        <%--</c:if>--%>
                </span>
            </legend>
            <table class="tablecloth" align="center">
                <c:if test="${!empty actionBean.senaraiUlasanJab}">
                    <c:set value="1" var="i"/>
                    <c:forEach items="${actionBean.senaraiUlasanJab}" var="line">
                        <tr>
                            <td style="text-align: right">3.${i}</td>
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
                4. MAKLUMAT TANAH DAN STATUS CUKAI TANAH 
            </legend>
            <table class="tablecloth" align="center">
                <tr>
                    <td style="text-align: right">4.1</td>
                    <td style="text-align: right">Lokasi:</td>
                    <td>${actionBean.hakmilikPermohonan.hakmilik.bandarPekanMukim.nama}</td>
                </tr>
                <tr>
                    <td style="text-align: right">4.2</td>
                    <td style="text-align: right">No. Hakmilik:</td>
                    <td>${actionBean.hakmilikPermohonan.hakmilik.kodHakmilik.kod} ${actionBean.hakmilikPermohonan.hakmilik.noHakmilik}</td>
                </tr>
                <tr>
                    <td style="text-align: right">4.3</td>
                    <td style="text-align: right">No. Lot:  </td>
                    <td>${actionBean.hakmilikPermohonan.hakmilik.lot.nama} ${actionBean.hakmilikPermohonan.hakmilik.noLot}</td>
                </tr>
                <tr>
                    <td style="text-align: right">4.4</td>
                    <td style="text-align: right">Cukai Setahun:</td>
                    <td> RM ${actionBean.cukaiSetahun} </td>
                </tr>
                <tr>
                    <td style="text-align: right">4.5</td>
                    <td style="text-align: right">Tunggakan:</td>
                    <td>RM <fmt:formatNumber value="${actionBean.tunggakan}" pattern="#,###,###,###.00"/></td>
                    
                </tr>
                <tr>
                    <td style="text-align: right">4.6</td>
                    <td style="text-align: right">Tahun Tunggakan:</td>
                    <td style="text-align: right"><c:if test = "${actionBean.tunggakan eq 0}">-</c:if>
                        <c:if test = "${actionBean.tunggakan ne 0}" > 
                            ( dari tahun ${actionBean.min} hingga ${actionBean.max} )</c:if></td>

                    </tr>
                <tr>
                    <td style="text-align: right">4.7</td>
                    <td style="text-align: right">Denda Lewat:</td>
                    <td>RM <fmt:formatNumber value="${actionBean.dendaLewat}" pattern="#,###,###,###.00"/></td>
                    </tr>
                <tr>
                    <td style="text-align: right">4.8</td>
                    <td style="text-align: right">Jumlah Semasa:</td>
                    <td>RM <fmt:formatNumber value="${actionBean.baki}" pattern="#,###,###,###.00"/></td>
                     </tr>
                <tr>
                    <td style="text-align: right">4.9</td>
                    <td style="text-align: right">Denda Semasa:</td>
                    <td>RM <fmt:formatNumber value="${actionBean.dendaSemasa}" pattern="#,###,###,###.00"/></td>
                    
                </tr>
                <tr>
                    <td style="text-align: right">4.10</td>
                    <td style="text-align: right">Jumlah Lewat Bayar:</td>
                    <td>RM <fmt:formatNumber value="${actionBean.jumlahLewatBayar}" pattern="#,###,###,###.00"/></td>
                </tr>

            </table>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                5. ASAS-ASAS PERTIMBANGAN
                <span style="float:right">
                    <%--<c:if test="${actionBean.disKertasMMKController.kPerakuanPTD}">--%>
                    <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                    <a onclick="openFrame('kHuraianPtd');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                        <%--</c:if>--%>
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
                6. PERAKUAN PENTADBIR TANAH DAERAH
                <span style="float:right">
                    <%--<c:if test="${actionBean.disKertasMMKController.kPerakuanPTD}">--%>
                        <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                        <a onclick="openFrame('kPerakuanPtd');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                        <%--</c:if>--%>
                </span>
            </legend>
            <table class="tablecloth" align="center">
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

        </fieldset>
    </div>

   <c:if test="${actionBean.disKertasMMKController.vPTG}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    7. PERAKUAN PENGARAH TANAH DAN GALIAN
                    <span style="float:right">
                        <%--<c:if test="${actionBean.disKertasMMKController.kPerakuanPTG}">--%>
                            <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                            <a onclick="openFrame('kPerakuanPtg');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                            <%--</c:if>--%>
                    </span>
                </legend>
                <table class="tablecloth" align="center">
                    <c:if test="${!empty actionBean.senaraiKandunganPerakuanPtg}">
                        <c:set value="1" var="i"/>
                        <c:forEach items="${actionBean.senaraiKandunganPerakuanPtg}" var="line">
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
                    8. KEPUTUSAN
                    <span style="float:right">
                        <%--<c:if test="${actionBean.disKertasMMKController.kPerakuanPTG}">--%>
                            <!--<a onclick="viewSyor();" onmouseover="this.style.cursor = 'pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> |--> 
                            <a onclick="openFrame('kKeputusan');" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                            <%--</c:if>--%>
                    </span>
                </legend>
                <table class="tablecloth" align="center">
                    <c:if test="${!empty actionBean.senaraiKandunganKeputusan}">
                        <c:set value="1" var="i"/>
                        <c:forEach items="${actionBean.senaraiKandunganKeputusan}" var="line">
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
    </c:if>
</s:form>

