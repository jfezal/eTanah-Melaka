<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">
    function edit(id, idNotis){
        window.open("${pageContext.request.contextPath}/hasil/notis_gantian?popup&idHakmilik="+id+"&idNotis="+idNotis, "eTanah",
                 "status=0,toolbar=0,location=0,menubar=0,width=1100,height=680,scrollbars=yes,resizable=1");
    }
</script>
<s:form beanclass="etanah.view.stripes.hasil.NotisGantianActionBean">
    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Senarai Hakmilik</legend>
            <div class="content" align="center">

                <display:table class="tablecloth" name="${actionBean.senaraiNotis}" id="line">

                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column  title="ID Hakmilik" >
                        <a href="#" onclick="edit('${line.hakmilik.hakmilik.idHakmilik}','${line.idNotis}');return false;">${line.hakmilik.hakmilik.idHakmilik}</a>
                    </display:column>
                    <display:column property="pihak.nama" title="Nama"/>
                    <display:column property="hakmilik.hakmilik.akaunCukai.baki" title="Jumlah Tunggakan (RM)" style="text-align:right" format="{0,number, 0.00}"/>
                    <display:column title="Tahun Tunggakan" style="text-align:center">
                        <c:set value="${line.hakmilik.hakmilik.akaunCukai.senaraiTransaksiDebit[0].untukTahun}" var="minTahun"/>
                        <c:forEach items="${line.hakmilik.hakmilik.akaunCukai.senaraiTransaksiDebit}" var="transaksi">
                            <c:if test="${transaksi.untukTahun eq minTahun}">
                                <c:set value="${minTahun}" var="maxTahun"/>
                            </c:if>
                            <c:if test="${transaksi.untukTahun > minTahun}">
                                <c:set value="${transaksi.untukTahun}" var="maxTahun"/>
                            </c:if>
                        </c:forEach>
                        <c:out value="${minTahun}"/> - <c:out value="${maxTahun}"/>
                    </display:column>
                    <display:column property="tarikhNotis" title="Tarikh Notis 6A" format="{0,date,dd/MM/yyyy}"/>
                    <display:column title="Hakmilik PTD/PTG">
                        <c:choose >
                            <c:when test="${line.hakmilik.hakmilik.kodHakmilik.kod eq 'GRN'
                                            or line.hakmilik.hakmilik.kodHakmilik.kod eq 'PN'
                                            or line.hakmilik.hakmilik.kodHakmilik.kod eq 'HSD'}">PTG</c:when>
                            <c:otherwise>PTD</c:otherwise>
                        </c:choose>
                    </display:column>
                </display:table>
            </div>

            <p>
                <label>
                    <div class="instr" align="center">
                    <s:errors/></div>
                </label>
            </p>
        </fieldset>
    </div>
</s:form>


