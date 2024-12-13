<%-- 
    Document   : kertas_rencana_ptd
    Created on : Jul 3, 2013, 10:38:45 AM
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
        window.open("${pageContext.request.contextPath}/pelupusan/kertas_pindaanPTDV2?openFrame&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }
    
    function refreshV2KertasPindaaanPTDV2(type){
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_pindaanPTDV2?refreshpage&type='+type;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        doUnBlockUI();
    }
        
</script>

<s:form beanclass="etanah.view.stripes.pelupusan.KertasRencanaPindaanPTDV2ActionBean">
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
                    <c:if test="${actionBean.disKertasMMKController.kTujuan}">
                        <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                        <a onclick="openFrame('kLatarBelakang');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                        </c:if>
                </span>
            </legend>
            <table class="tablecloth" align="center">
                <c:if test="${!empty actionBean.senaraiKandunganLatarBelakang}">
                    <c:set value="1" var="i"/>
                    <c:forEach items="${actionBean.senaraiKandunganLatarBelakang}" var="line">
                        <tr>
                            <td style="text-align: right">2.${i}</td>
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
                3. HURAIAN <c:choose><c:when test="${actionBean.permohonan.cawangan.kod eq '08'}">PENOLONG PENTADBIR TANAH GEMAS</c:when><c:otherwise>PENOLONG PENTADBIR TANAH ${actionBean.permohonan.cawangan.daerah.nama}</c:otherwise></c:choose>
                <span style="float:right">
                    <c:if test="${actionBean.disKertasMMKController.kTujuan}">
                        <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                        <a onclick="openFrame('kHuraianPPtd');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                        </c:if>
                </span>
            </legend>
            <table class="tablecloth" align="center">
                <c:if test="${!empty actionBean.senaraiKandunganHuraianPPtd}">
                    <c:set value="1" var="i"/>
                    <c:forEach items="${actionBean.senaraiKandunganHuraianPPtd}" var="line">
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
                4. SYOR <c:choose><c:when test="${actionBean.permohonan.cawangan.kod eq '08'}">PENOLONG PENTADBIR TANAH GEMAS</c:when><c:otherwise>PENOLONG PENTADBIR TANAH ${actionBean.permohonan.cawangan.daerah.nama}</c:otherwise></c:choose>
                <span style="float:right">
                    <c:if test="${actionBean.disKertasMMKController.kTujuan}">
                        <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                        <a onclick="openFrame('kSyorPPtd');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                        </c:if>
                </span>
            </legend>
            <table class="tablecloth" align="center">

                <c:if test="${!empty actionBean.senaraiKandunganSyorPPtd}">
                    <c:set value="1" var="i"/>
                    <c:forEach items="${actionBean.senaraiKandunganSyorPPtd}" var="line">
                        <tr>
                            <td style="text-align: right">4.${i}</td>
                            <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5" class="normal_text"/></td>
                        </tr>
                        <c:set value="${i+1}" var="i"/>
                    </c:forEach>
                </c:if>

            </table>
            <div class="content" align="center">
                <p><b>BAYARAN TAMBAHAN :</b></p>
                <span style="float:center"> 
                    <c:if test="${actionBean.disKertasMMKController.kTujuan}">
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

    <c:if test="${actionBean.disKertasMMKController.kPerakuanPTD}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    5. KEPUTUSAN 
                    <span style="float:right">
                        <c:if test="${actionBean.disKertasMMKController.kPerakuanPTD}">
                            <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                            <a onclick="openFrame('kKeputusan');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
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
                    <c:if test="${!empty actionBean.senaraiKandunganKeputusan}">
                        <c:set value="1" var="i"/>
                        <c:forEach items="${actionBean.senaraiKandunganKeputusan}" var="line">
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
</s:form>

