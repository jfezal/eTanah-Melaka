<%-- 
    Document   : senarai_keutamaan
    Created on : Mar 23, 2010, 8:39:42 AM
    Author     : fikri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
<script type="text/javascript">
    function reload(form) {
        form.action = '${pageContext.request.contextPath}/daftar/senarai_keutamaan?showForm&idPermohonan=${idPermohonan}';
        form.submit();
    }
</script>


<stripes:form beanclass="etanah.view.daftar.SenaraiKeutamaanActionBean">
    <div class="subtitile">
        <fieldset class="aras1">
            <legend>Senarai Hakmilik Terlibat</legend>
            <p>
                <label>Hakmilik :</label>
                <stripes:select name="idHakmilik" onchange="reload(this.form);" id="hakmilik">
                    <c:forEach items="${actionBean.senaraiHakmilikTerlibat}" var="item" varStatus="line">
                        <stripes:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                            ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                        </stripes:option>
                    </c:forEach>
                </stripes:select>
            </p>
            <br/>
        </fieldset>
        <br/>
        <fieldset class="aras1">
            <legend>Senarai Keutamaan</legend>
            <br/>
            <p align=""><font color="red" size="2">Sila klik pada ID Perserahan untuk memilih tugasan. Sila pilih berdasarkan keutamaan.</font></p>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend align="center">Urusan Kaveat</legend>
                    <p align="center"><label></label>
                        <display:table class="tablecloth" name="${actionBean.senaraiKaveat}" requestURI="/daftar/senarai_keutamaan"
                                       cellpadding="0" cellspacing="0" id="line1" style="width:90%" pagesize="10">
                            <display:column title="Bil">${line1_rowNum}</display:column>
                            <display:column property="kodUrusan" title="Kod Urusan"/>
                            <display:column property="urusan" title="Urusan"/>
                            <%--<display:column property="idHakmilik" title="Hakmilik"/> comment sebab column ni mengelirukan user--%>
                            <display:column title="ID Perserahan">
                                <c:choose>
                                    <c:when test="${line1.acquired eq 'true' && actionBean.permohonan.idPermohonan eq line1.idPermohonan}">
                                        <c:set var="url"
                                               value="${pageContext.request.contextPath}/daftar/senarai_keutamaan?acquired&idPermohonan=${line1.idPermohonan}&acquiredBy=${line1.acquiredBy}&tarikh=${line1.tarikh}"/>
                                        <a href="${url}">${line1.idPermohonan}</a>
                                    </c:when>
                                    <c:otherwise>${line1.idPermohonan}</c:otherwise>
                                </c:choose>
                            </display:column>
                            <display:column title="Peringkat">
                                ${line1.stage}
                            </display:column>
                        </display:table>
                    </p>
                    <br/>
                </fieldset>
                <br/>
                <fieldset class="aras1">
                    <legend align="center">Urusan Tarik Balik</legend>
                    <p align="center">
                        <label></label>
                        <display:table class="tablecloth" name="${actionBean.senaraiTarikBalik}" requestURI="/daftar/senarai_keutamaan"
                                       cellpadding="0" cellspacing="0" id="line2"  style="width:90%" pagesize="10">
                            <display:column title="Bil">${line2_rowNum}</display:column>
                            <display:column property="kodUrusan" title="Kod Urusan"/>
                            <display:column property="urusan" title="Urusan"/>
                            <%--<display:column property="idHakmilik" title="Hakmilik"/>  comment sebab column ni mengelirukan user--%>
                            <display:column title="ID Perserahan">
                                <c:choose>
                                    <c:when test="${line2.acquired eq 'true' && actionBean.permohonan.idPermohonan eq line2.idPermohonan}">
                                        <c:set var="url"
                                               value="${pageContext.request.contextPath}/daftar/senarai_keutamaan?acquired&idPermohonan=${line2.idPermohonan}&acquiredBy=${line2.acquiredBy}&tarikh=${line2.tarikh}"/>
                                        <a href="${url}">${line2.idPermohonan}</a>
                                    </c:when>
                                    <c:otherwise>${line2.idPermohonan}</c:otherwise>
                                </c:choose>
                            </display:column>                            
                        </display:table>
                    </p>
                    <br/>
                </fieldset>
                <br/>
                <fieldset class="aras1">
                    <legend align="center">Urusan Urusniaga/Bukan urusniaga/Nota</legend>
                    <p align="center">
                        <label></label>
                        <display:table class="tablecloth" name="${actionBean.senaraiUrusan}" requestURI="/daftar/senarai_keutamaan"
                                       cellpadding="0" cellspacing="0" id="line3" style="width:90%" pagesize="10">
                            <display:column title="Bil">${line3_rowNum}</display:column>
                            <display:column property="kodUrusan" title="Kod Urusan"/>
                            <display:column property="urusan" title="Urusan"/>
                            <%--<display:column property="idHakmilik" title="Hakmilik"/>  comment sebab column ni mengelirukan user --%>
                            <display:column title="ID Perserahan">
                                <c:choose>
                                    <c:when test="${line3.acquired eq 'true' && actionBean.permohonan.idPermohonan eq line3.idPermohonan}">
                                        <c:set var="url"
                                               value="${pageContext.request.contextPath}/daftar/senarai_keutamaan?acquired&idPermohonan=${line3.idPermohonan}&acquiredBy=${line3.acquiredBy}&tarikh=${line3.tarikh}"/>
                                        <a href="${url}">${line3.idPermohonan}</a>
                                    </c:when>
                                    <c:otherwise>${line3.idPermohonan}</c:otherwise>
                                </c:choose>
                            </display:column>
                            <display:column title="Peringkat">
                                ${line3.stage}
                            </display:column>
                        </display:table>
                    </p>
                    <br/>
                </fieldset>
            </div>
        </fieldset>
    </div>    
</stripes:form>