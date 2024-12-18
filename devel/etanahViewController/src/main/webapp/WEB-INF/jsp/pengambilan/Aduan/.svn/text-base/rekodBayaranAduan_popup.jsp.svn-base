<%-- 
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : ${user}
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>

<script type="text/javascript">


$(document).ready( function(){
$(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
});


$(document).ready(function() {
    $("#close").click( function(){
        setTimeout(function(){
            self.close();
        }, 100);
    });
    });

  </script>
<STYLE>
.red {background:red}
.green {background:green}
.yellow {background:yellow}
.white {background:white}
</STYLE>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div class="subtitle">
    <s:form beanclass="etanah.view.stripes.pengambilan.rekodBayaranAduan" >
        <s:messages/>
        <fieldset class="aras1">
            <legend> </legend>
            <display:table class="tablecloth" name="${actionBean.senaraiAmbilPampasan}" pagesize="5" cellpadding="0" cellspacing="0"
                           requestURI="/pengambilan/RekodAmbilCekSatuTuanTanah" id="line">
                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                <display:column title="Jumlah Nilai Cek(RM)">
                    RM <fmt:formatNumber pattern="#,##0.00" value="${line.jumTerimaPampasan}"/>
                </display:column>
                    hoi
                <%--<c:if test="${permohonan.kodUrusan.kod eq 'SEK4A'}">--%>
                <display:column title="Cara Bayaran" value="${line.kodCaraBayaran.nama}"/>
                <display:column title="Tarikh" style="vertical-align:top;">
                            <fmt:formatDate value="${line.tarikhDok}" pattern="dd/MM/yyyy"/>
                </display:column>
                <display:column title="Bank" style="vertical-align:top;">
                <c:if test="${line.kodBank ne null}">
                    ${line.kodBank.nama}
                </c:if>
                <c:if test="${line.kodBank eq null}">
                    -
                </c:if>
                </display:column>

                <%--<display:column title="Bank" value="${line.kodBank.nama}"/>--%>
                <%--<c:if test="${line.tarikhDok ne null}">--%>
                    <%-- RM <fmt:formatDate value="${line.tarikhDok}" pattern="dd/MM/yyyy"/>--%>
                <%--</c:if>--%>
                <%--<c:if test="${line.tarikhDok eq null}">
                   -
                </c:if>--%>

               <%-- <c:if test="${line.kodBank eq null}">
                   -
                </c:if>--%>
               <%-- <c:if test="${line.noDok ne null}">
                    <display:column title="No. Dokumen" value="${line.noDok}"/>
                </c:if>
                <c:if test="${line.noDok eq null}">
                   -
                </c:if>--%>

                <%--</c:if>--%>
                <%--<c:if test="${permohonan.kodUrusan.kod ne 'SEK4A'}">
                <display:column  title="Rekod Bayaran">
                    <s:hidden name="idPihak" value="${line.perbicaraanKehadiran.pihak.pihak.idPihak}"/>
                    <div align="center">
                        <c:if test="${line.jumTerimaPampasan > 0}">
                            <s:button name="a" class="green" disabled="true" value="    " />
                        </c:if>
                        <c:if test="${line.jumTerimaPampasan == 0}">
                            <s:button name="a" class="red" disabled="true" value="    " />
                        </c:if>
                    </div>
                </display:column>
                <display:column  title="Cek Diambil Tuan Tanah" >
                    <div align="center">
                        <s:checkbox name="dokDiambil[${line_rowNum-1}]" value="Y" id="dokDiambil[${line_rowNum-1}]"/>
                    </div>
                </display:column>
                <display:column  title="Tarikh Cek Diambil" >
                    <s:text name="tarikhDokDiambil[${line_rowNum-1}]" id="datepicker[${line_rowNum-1}]" class="datepicker" size="12" formatPattern="dd/MM/yyyy"/>
                </display:column>
                </c:if>--%>
            </display:table>
            <br/><br/>
            <div align="center">
                <s:hidden name="idHakmilik" value="${actionBean.hakmilik.idHakmilik}"/>
                <%--<c:if test="${permohonan.kodUrusan.kod ne 'SEK4A'}">
                <s:submit name="simpan" value="Simpan" class="btn" />
                </c:if>--%>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </div>
        </fieldset>
    </s:form>
</div>