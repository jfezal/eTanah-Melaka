<%-- 
    Document   : kertas_pindaan_mmkV2
    Created on : Jul 4, 2013, 4:42:32 PM
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
        window.open("${pageContext.request.contextPath}/pelupusan/kertas_pindaan_MMKV2?openFrame&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }
    
    function refreshV2KertasPindaanMMK(type){
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_pindaan_MMKV2?refreshpage&type='+type;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        doUnBlockUI();
    }
        
</script>

<s:form beanclass="etanah.view.stripes.pelupusan.KertasPindaanMMKV2ActionBean">
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
                <span style="float:right">
                    <c:if test="${actionBean.disKertasMMKController.kLatarBelakang}">
                        <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                        <a onclick="openFrame('kTanah');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                        </c:if>
                </span>
            </legend>
            <table class="tablecloth" align="center">
                <c:if test="${!empty actionBean.senaraiKandunganPerihalTanah}">
                    <c:set value="1" var="a"/>
                    <c:forEach items="${actionBean.senaraiKandunganPerihalTanah}" var="line">
                        <tr>
                            <td style="text-align: right">2.${a}</td>
                            <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
                        </tr>
                        <c:set value="${a+1}" var="a"/>
                    </c:forEach>
                </c:if>
            </table>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                3. HURAIAN PENTADBIR TANAH ${actionBean.permohonan.cawangan.daerah.nama}
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
                            <td style="text-align: right">3.${i}</td>
                            <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
                        </tr>
                        <c:set value="${i+1}" var="i"/>
                    </c:forEach>
                </c:if>    
            </table>
            <div class="content" align="center">
                <p><b>BAYARAN TAMBAHAN :</b></p>
                <span style="float:center"> 
                    <c:if test="${actionBean.disKertasMMKController.kPerakuanPTD}">
                        <a onclick="openFrame('tBorang');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                        </c:if>                    
                </span>
                <table class="tablecloth">
                    <tr>
                        <th>Item</th><th>Amaun Bayaran</th>
                        <c:if test="${!empty actionBean.listPermohonanTuntutanKos}">
                            <c:set var="i" value="1" />
                        </tr>       
                        <tr>
                            <c:forEach items="${actionBean.listPermohonanTuntutanKos}" var="line">
                                <td>
                                    ${line.item}
                                </td>
                                <td>
                                    RM <s:format value="${line.amaunTuntutan}" formatPattern="#,###,##0.00"/>
                                </td>
                            </tr>

                            <c:set var="i" value="${i+1}" />        
                        </c:forEach>
                        <tr>
                            <td>Jumlah </td>
                            <td>RM <s:format value="${actionBean.total}" formatPattern="#,###,##0.00"/></td>
                        </tr>
                    </c:if>
                </table>
            </div>
        </fieldset>
    </div>   

    <c:if test="${actionBean.disKertasMMKController.vPTG}">

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    4. HURAIAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN
                    <span style="float:right">
                        <c:if test="${actionBean.disKertasMMKController.kPerakuanPTG}">
                            <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                            <a onclick="openFrame('kHuraianPtg');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                            </c:if>
                    </span>
                </legend>
                <table class="tablecloth" align="center">
                    <c:if test="${!empty actionBean.senaraiKandunganHuraianPtg}">
                        <c:set value="1" var="i"/>
                        <c:forEach items="${actionBean.senaraiKandunganHuraianPtg}" var="line">
                            <tr>
                                <td style="text-align: right">4.${i}</td>
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
                    5. SYOR PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN
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
                    6. KEPUTUSAN
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

