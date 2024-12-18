
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
        window.open("${pageContext.request.contextPath}/pelupusan/kertas_makluman_mmkV2?openFrame&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }
    
    function refreshV2KertasMaklumanMMK(type){
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_makluman_mmkV2?refreshpage&type='+type;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        doUnBlockUI();
    }
        
</script>

<s:form beanclass="etanah.view.stripes.pelupusan.KertasMaklumanMMKActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <table class="tablecloth" align="center">
                <tr>
                    <td><s:textarea value="${actionBean.kandunganTajuk}" cols="80" readonly="true" name="syorUlasan" rows="5"/></td>
                </tr>    
            </table>

        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                1. TUJUAN
            </legend>
            <table class="tablecloth" align="center">
                <c:if test="${!empty actionBean.senaraiKandunganTujuan}">
                    <c:set value="1" var="i"/>
                    <c:forEach items="${actionBean.senaraiKandunganTujuan}" var="line">
                        <tr>
                            <td style="text-align: right">1.${i}</td>
                            <td>
                                <s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5"/>
                            </td>
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
                3. SIASATAN PENGARAH TANAH DAN GALIAN 
                <span style="float:right">
                    <c:if test="${actionBean.disKertasMMKController.kTujuan}">
                        <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                        <a onclick="openFrame('kSiasatan');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                        </c:if>
                </span>
            </legend>
            <table class="tablecloth" align="center">
                <c:if test="${!empty actionBean.senaraiKandunganSiasatan}">
                    <c:set value="1" var="i"/>
                    <c:forEach items="${actionBean.senaraiKandunganSiasatan}" var="line">
                        <tr>
                            <td style="text-align: right">3.${i}</td>
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
                4. ULASAN PENGARAH TANAH DAN GALIAN 
                <span style="float:right">
                    <c:if test="${actionBean.disKertasMMKController.kTujuan}">
                        <%-- <a onclick="viewSyor();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                        <a onclick="openFrame('kMakluman');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                        </c:if>
                </span>
            </legend>
            <table class="tablecloth" align="center">
                <c:if test="${!empty actionBean.senaraiKandunganMakluman}">
                    <c:set value="1" var="i"/>
                    <c:forEach items="${actionBean.senaraiKandunganMakluman}" var="line">
                        <tr>
                            <td style="text-align: right">4.${i}</td>
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
                5. KEPUTUSAN
                <span style="float:right">
                    <c:if test="${actionBean.disKertasMMKController.kTujuan}">
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
                            <td style="text-align: right">5.${i}</td>
                            <td><s:textarea value="${line.kandungan}" cols="80" readonly="true" name="syorUlasan" rows="5"/></td>
                        </tr>
                        <c:set value="${i+1}" var="i"/>
                    </c:forEach>
                </c:if>    
            </table>
        </fieldset>
    </div>   
</s:form>

