<%-- 
    Document   : dev_laporan_salinanKepada
    Created on : Jan 16, 2014, 12:32:16 PM
    Author     : khairul.hazwan
--%>

<%-- 
    Document   : dev_laporan_MLK
    Created on : Sep 22, 2010, 10:57:44 PM
    Author     : nursyazwani
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    function popupParam(nama){
        var id = '${actionBean.idPermohonan}';
        window.open("${pageContext.request.contextPath}/utiliti/utilitiSalinan/requestParam?namaReport="+nama+"&idPermohonan="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    
//    function openFrame(){     
//            
//      //  NoPrompt();
//        var idPermohonan = $('#idPermohonan').val();  
//        window.open("${pageContext.request.contextPath}/utiliti/utilitiSalinanDev?findKandungan&idPermohonan="+idPermohonan, "eTanah",
//        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");     
//    }
</script>

<div id="laporan">
    <s:form beanclass="etanah.view.stripes.pelupusan.utility.UtilitiSalinanActionBean">
        <s:hidden name="idPermohonan" id="idPermohonan"/>
        <%--<s:hidden name="kodDokumen" id="kodDokumen"/>--%>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Senarai Surat
                </legend>
                <br>
                <c:set value="1" var="count"/>
                <c:forEach items="${actionBean.senaraiReport}" var="report">
                    <c:out value="${count}"/>) <a href="#" onclick="popupParam('${actionBean.senaraiReportName[count-1]}');"><c:out value="${report}"/></a>
                    <c:set value="${count +1}" var="count"/>
                    <br>
                </c:forEach>
                    <br>
            </fieldset>
                 <td align="center" colspan="2">                                  
                      <%--<s:button name="tutup" value="Kembali" class="btn" onclick="openFrame()"/>--%>
                      <s:hidden id="id_mohon" name="idPermohonan" value="${actionBean.idPermohonan}"/>
                      <s:submit name="findKandungan" value="Kembali" class="btn"  onclick=""/>                     
                 </td>
        </div>
    </s:form>
</div>