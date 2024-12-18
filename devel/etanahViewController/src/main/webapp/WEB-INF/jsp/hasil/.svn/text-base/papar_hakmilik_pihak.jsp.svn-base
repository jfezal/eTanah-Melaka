<%-- 
    Document   : papar_hakmilik_pihak
    Created on : Mar 12, 2010, 6:12:13 PM
    Author     : abu.mansur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>

<script type="text/javascript">
    function popup(id){
        window.open("${pageContext.request.contextPath}/common/maklumat_hakmilik_single?popup&idSyarat="+id, "",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=300,scrollbars=1");
    }

    $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });
</script>


<div class="subtitle">
    <s:form beanclass="etanah.view.stripes.hasil.PaparHakmilikPihakActionBean">
        <s:hidden name="hakmilik.idHakmilik"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Hakmilik </legend>
                <c:if test="${actionBean.negeri eq 'melaka'}">
                    <p>
                        <label>No. Akaun :</label>
                        ${actionBean.hakmilik.akaunCukai.noAkaun}&nbsp;
                    </p>
                </c:if>
                <p>
                    <label>ID Hakmilik :</label>
                    ${actionBean.hakmilik.idHakmilik}&nbsp;
                </p>
            </fieldset>
            <br>
        </div>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Hakmilik
                </legend>

                <p>
                    <label>Daerah :</label>
                    ${actionBean.hakmilik.daerah.nama}&nbsp;
                </p>
                <p>
                    <label>Bandar/Pekan/Mukim :</label>
                    ${actionBean.hakmilik.bandarPekanMukim.nama}&nbsp;
                </p>
                <p>
                    <label>Jenis Hakmilik :</label>
                    ${actionBean.hakmilik.kodHakmilik.nama}&nbsp;
                </p>
                <p>
                    <label>Jenis Penggunaan Tanah :</label>
                    ${actionBean.hakmilik.kategoriTanah.nama}&nbsp;
                </p>
                <p>
                    <label>Kod Lot :</label>
                    ${actionBean.hakmilik.lot.nama}&nbsp;

                </p>
                <p>
                    <label>No Lot/PT :</label>
                    ${actionBean.hakmilik.noLot}&nbsp;

                </p>
                <p>
                    <label >Syarat Nyata :</label>
                    <c:if test="${actionBean.hakmilik.syaratNyata ne null}">
                        <a href="#" onclick="popup('${actionBean.hakmilik.syaratNyata.kod}');return false;">${actionBean.hakmilik.syaratNyata.kod}&nbsp;</a>
                    </c:if>
                    <c:if test="${actionBean.hakmilik.syaratNyata eq null}">
                        Tiada.
                    </c:if>
                </p>
                <p>
                    <label>No Pelan Piawai :</label>
                    <c:if test="${actionBean.hakmilik.noLitho ne null}">
                        ${actionBean.hakmilik.noLitho}&nbsp;
                    </c:if>
                    <c:if test="${actionBean.hakmilik.noLitho eq null}">
                        Tiada.
                    </c:if>
                </p>
                <p>
                    <label>No Pelan Akui :</label>
                    <c:if test="${actionBean.hakmilik.noPelan ne null}">
                        ${actionBean.hakmilik.noPelan}&nbsp;
                    </c:if>
                    <c:if test="${actionBean.hakmilik.noPelan eq null}">
                        Tiada.
                    </c:if>
                </p>
                <p>
                    <label >Keluasan  :</label>
                    <s:format formatPattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp; ${actionBean.hakmilik.kodUnitLuas.nama}
                </p>
                <p>
                    <label >Cukai Tanah Tahunan (RM)  :</label>
                    <td class="number" align="right"> <fmt:formatNumber value="${actionBean.hakmilik.cukaiSebenar}" pattern="0.00"/></td>
                </p>
            </fieldset>
        </div>
        <br>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Tuan Tanah
                </legend>

                <div class="content" align="center">

                    <display:table class="tablecloth" name="${actionBean.pihakList}" pagesize="5" cellpadding="0" cellspacing="0" requestURI="/hasil/papar_hakmilik_pihak" id="line">
                        <display:column title="Bil" sortable="true" > <div align="center">${line_rowNum}</display:column>
                            <display:column property="pihak.nama" title="Nama" class="cawangan${line_rowNum}" />
                            <display:column property="pihak.noPengenalan" title="No Pengenalan" class="cawangan${line_rowNum}" />
                            <display:column property="pihak.bangsa.nama" title="Bangsa" class="cawangan${line_rowNum}" />
                            <display:column title="Alamat" class="alamat">
                                ${line.pihak.suratAlamat1}<c:if test="${line.pihak.suratAlamat2 ne null}">,</c:if>
                                ${line.pihak.suratAlamat2}<c:if test="${line.pihak.suratAlamat3 ne null}">,</c:if>
                                ${line.pihak.suratAlamat3}<c:if test="${line.pihak.suratAlamat4 ne null}">,</c:if>
                                ${line.pihak.suratAlamat4}<c:if test="${line.pihak.suratPoskod ne null}">,</c:if>
                                ${line.pihak.suratPoskod}<c:if test="${line.pihak.suratNegeri.nama ne null}">,</c:if>
                                ${line.pihak.suratNegeri.nama}
                            </display:column>
                        </display:table>
                    </div>
            </fieldset>
        </div>
        <br>
        <table width="100%">
            <tr>
                <%--<s:hidden name="permohonanUlasan.tarikhUlasan" value="<%=new java.util.Date()%>" />--%>
                <td align="right"><s:button name="" id="close" value="Tutup" class="btn"/></td>
            </tr>
        </table>
    </s:form>
</div>