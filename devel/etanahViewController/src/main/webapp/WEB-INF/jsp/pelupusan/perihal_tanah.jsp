<%-- 
    Document   : perihal_tanah
    Created on : Jun 15, 2011, 4:43:35 PM
    Author     : Rohans
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<script type="text/javascript">
function showLesen(i){
        window.open("${pageContext.request.contextPath}/pelupusan/perihal_tanah?showForPerihal&idMohon="+i, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600,scrollbars=yes");
    }


</script>

<s:form beanclass="etanah.view.stripes.pelupusan.PerihalTanahActionBean">
    <s:messages />
    <s:errors />
    <div class="subtitle">

        <fieldset class="aras1">
            <legend>Perihal Tanah</legend><br/>

            <tr align="left"><td><font style="color:black;">No Lot:</font></td>
                <td><font style="color:black;">${actionBean.hakmilikPermohonan.noLot}</font></td></tr><br/><br/>

            <div class="content" align="center">

                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList1}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pelupusan/perihal_tanah">
                    <display:column title="No">${line_rowNum}</display:column>
                    <display:column property="permohonan.idPermohonan" title="ID Permohonan" />
                    <display:column property="permohonan.kodUrusan.nama" title="Urusan" />
                    <display:column title="Pemohon">
                        <c:forEach items="${line.permohonan.senaraiPemohon}" var="line1" > ${line1.pihak.nama}</c:forEach>
                    </display:column>
                    <display:column title="Alamat" >
                          <c:forEach items="${line.permohonan.senaraiPemohon}" var="line1" >
                            ${line1.pihak.alamat1}
                            <c:if test="${line1.pihak.alamat2 ne null}"> , </c:if>
                            ${line1.pihak.alamat2}
                            <c:if test="${line1.pihak.alamat3 ne null}"> , </c:if>
                            ${line1.pihak.alamat3}
                            <c:if test="${line1.pihak.alamat4 ne null}"> , </c:if>
                            ${line1.pihak.alamat4}</c:forEach></display:column>
                    <display:column title="Status">  ${actionBean.kodList[line_rowNum-1]}
                        <c:if test="${line.permohonan.status eq 'SL'&& line.permohonan.keputusan ne null}">(${line.permohonan.keputusan.nama})</c:if>
                    </display:column>
                    <display:column title="Maklumat Lesen / Permit">
                        <%--<c:if test="${actionBean.p.kodUrusan.kod eq 'LPSP' || actionBean.p.kodUrusan.kod eq 'PBPTD' || actionBean.p.kodUrusan.kod eq 'PPBB'||actionBean.p.kodUrusan.kod eq 'PLPS'||actionBean.p.kodUrusan.kod eq 'PBPTG'}">--%>
                        <c:if test="${line.permohonan.kodUrusan.kod eq 'LPSP' || line.permohonan.kodUrusan.kod eq 'PBPTD' || line.permohonan.kodUrusan.kod eq 'PPBB'||line.permohonan.kodUrusan.kod eq 'PLPS'||line.permohonan.kodUrusan.kod eq 'PBPTG'}">
                            <s:button name="papar" id="papar" value="Papar" class="btn" onclick="javascript:showLesen('${line.permohonan.idPermohonan}');" />
                        </c:if>
                    </display:column>
                </display:table>
            </div>
        </fieldset>
    </div>
</s:form>


