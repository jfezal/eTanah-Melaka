<%--
    Document   : setup_denda_tahunan1
    Created on : Aug 28, 2012, 5:31:54 PM
    Author     : haqqiem
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    function generateReportArchive(f,caw){
        var kodNegeri = document.getElementById('negeri');
        var form = $(f).formSerialize();
        var report =null;

        if(kodNegeri.value == 'melaka'){
            report = 'HSL_R_60_MLK.rdf';
        }else{
            report = 'HSL_R_60_NS.rdf';
        }
        var url = "reportName="+report+"%26report_p_kod_caw="+caw;

        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    }
    
    function generateReportArchiveStrata(f,caw){
        var kodNegeri = document.getElementById('negeri');
        var form = $(f).formSerialize();
        var report =null;

        if(kodNegeri.value == 'melaka'){
            report = 'HSL_R_60S_MLK.rdf';
        }else{
            report = 'HSL_R_60_NS.rdf';
        }
        var url = "reportName="+report+"%26report_p_kod_caw="+caw;

        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    }
</script>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Set-up Denda Lewat</font>
                </div>
            </td>
        </tr>
    </table></div>
    <stripes:errors />
    <stripes:messages/>
<p></p>
<stripes:form beanclass="etanah.view.stripes.hasil.DendaTahunanActionBean" id="denda_tahunan" >
    <stripes:text name="kodNegeri" value="${actionBean.kodNegeri}" id="negeri" style="display:none;"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Pembayaran</legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.transList}" requestURI="/hasil/denda_tahunan" id="line">
                        <display:column title="Bil." ><div align="center">${line_rowNum}.</div></display:column>
                        <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                            <display:column property="akaunDebit.noAkaun" title="No Akaun"/>
                            <!--display:column property="akaunDebit.hakmilik.idHakmilik" title="ID Hakmilik"/-->
                        </c:if>
                        <c:if test="${actionBean.kodNegeri ne 'melaka'}">
                            <display:column property="akaunDebit.noAkaun" title="ID Hakmilik"/>
                        </c:if>
                        <display:column property="amaun" title="Amaun Denda (RM)" style="text-align:right" format="{0,number, #,###,###.00}"/>
                        <display:column property="akaunDebit.baki" title="Amaun Perlu Dibayar (RM)" style="text-align:right" format="{0,number, #,###,###.00}"/>
                        <display:footer>
                            <tr>
                                <td colspan="2"><div align="right"><b>Jumlah (RM):</b></div></td>
                                <td><div align="right"><fmt:formatNumber value="${actionBean.total}" pattern="#,###,###.00" /></div></td>
                            </tr>
                        </display:footer>
                    </display:table>
                </div>
            </fieldset>
        </div>
    <div align="center"><table style="width:99.2%" bgcolor="green">
            <tr>
                <td width="100%" height="20" >
                    <div style="background-color: green;" align="right">&nbsp;
                        <stripes:button name=" " id="cetak" onclick="generateReportArchive(this.form,'${actionBean.cawanganDenda}');" value="Cetak" class="btn"/>&nbsp;
                        <stripes:button name=" " id="cetakStrata" onclick="generateReportArchiveStrata(this.form,'${actionBean.cawanganDenda}');" value="Cetak Strata" class="btn"/>&nbsp;
                    </div>
                </td>
            </tr>
        </table></div>
</stripes:form>