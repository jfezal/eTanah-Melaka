<%-- 
    Document   : paparan_hakmilik_single
    Created on : Nov 1, 2009, 6:15:42 PM
    Author     : abu.mansur
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function edit(id){
        window.open("${pageContext.request.contextPath}/common/maklumat_hakmilik_single?popup&idSyarat="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }
</script>

<s:form beanclass="etanah.view.stripes.common.MaklumatHakmilikSingleActionBean" id="se">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Hakmilik
            </legend>
            <c:if test="${actionBean.negeri eq 'melaka'}">
                <p>
                    <label>No. Akaun :</label>
                    ${actionBean.hakmilikPermohonan.hakmilik.akaunCukai.noAkaun}&nbsp;
                </p>
            </c:if>            
            <p>
                <label>ID Hakmilik :</label>
                ${actionBean.hakmilikPermohonan.hakmilik.idHakmilik}&nbsp;
            </p>
            <p>
                <label>ID Kumpulan :</label>
                <c:if test="${actionBean.hakmilikPermohonan.hakmilik.akaunCukai.kumpulan ne null}">
                    ${actionBean.hakmilikPermohonan.hakmilik.akaunCukai.kumpulan.idKumpulan}&nbsp;
                </c:if>
                <c:if test="${actionBean.hakmilikPermohonan.hakmilik.akaunCukai.kumpulan eq null}">
                    Tiada.&nbsp;
                </c:if>
            </p>
            <p>
                <label>Daerah :</label>
                ${actionBean.hakmilikPermohonan.hakmilik.daerah.nama}&nbsp;
            </p>
            <p>
                <label>Bandar / Pekan / Mukim :</label>
                ${actionBean.hakmilikPermohonan.hakmilik.bandarPekanMukim.nama}&nbsp;
            </p>
            <p>
                <label>Nombor Lot :</label>
                ${actionBean.hakmilikPermohonan.hakmilik.noLot}&nbsp;
            </p>
            <p>
                <label>Luas :</label>
                <s:format formatPattern="#,##0.0000" value="${actionBean.hakmilikPermohonan.hakmilik.luas}" />&nbsp;${actionBean.hakmilikPermohonan.hakmilik.kodUnitLuas.nama}
            </p>
            <p>
                <label>Cukai Tanah (RM) :</label>
                <s:format formatPattern="#,##0.00" value="${actionBean.hakmilikPermohonan.hakmilik.cukaiSebenar}" />&nbsp;
            </p>
            <p>
                <label>Kategori Tanah :</label>
                ${actionBean.hakmilikPermohonan.hakmilik.kategoriTanah.nama}&nbsp;
            </p>
            <p>
                <label>Syarat Nyata :</label>
                <a href="#" onclick="edit('${actionBean.hakmilikPermohonan.hakmilik.syaratNyata.kod}');return false;">${actionBean.hakmilikPermohonan.hakmilik.syaratNyata.kod}</a>&nbsp;
            </p>
        </fieldset>
        <br>
    </div>
</s:form>
