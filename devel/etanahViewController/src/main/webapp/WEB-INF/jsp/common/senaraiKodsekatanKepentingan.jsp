<%-- 
    Document   : senaraiKodsekatanKepentingan
    Created on : Dec 30, 2009, 6:11:57 PM
    Author     : mohd.fairul
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<s:errors/>
<s:messages/>
<br>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Carian
        </legend>
        <div class="content" align="center">

            <s:form action="/senaraiTugasan" id="se">
                <table>
                    <tr>
                        <td class="rowlabel1">ID Permohonan / ID Perserahan :</td>
                        <td class="input1"><s:text name="idInsert"/> </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Tarikh Dari :</td>
                        <td class="input1">
                            <s:text name="fromDate" id="datepicker" class="datepicker"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Tarikh Hingga :</td>
                        <td class="input1">
                            <s:text name="untilDate" id="datepicker2" class="datepicker"/>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <s:submit name="searchAllPermohonan" value="Cari" class="btn"/>

                            <s:button name="reset" value="Isi Semula" class="btn" onclick="clearText('se');"/>
                        </td>
                    </tr>
                </table>
            </s:form>
            <br>

        </div>
    </fieldset>
</div>
<br>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Senarai Tugasan
        </legend>
        <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.kodsekatanKepentinganList}"  id="line" pagesize="10"
                           requestURI="${pageContext.request.contextPath}/senarai/kodsyaratnyata">
                <display:column title="Bil" class="bil${line_rowNum}">${line_rowNum}</display:column>
                <display:column title="Kod" class="kod${line_rowNum}">${actionBean.kodsekatanKepentingan.kod}</display:column>
                 <display:column property="sekatan" title="Sekatan" class="link${line_rowNum}"/>
                <%--<display:column property="tajuk" title="Urusan" href="${line.link}" class="link${line_rowNum}"/>--%>
                <%--<display:column title="Urusan">
                    <a href="${line.link}">${line.tajuk}</a>
                </display:column>--%>
                <%--<display:column property="kepentingan" title="Keutamaan"/>
                <display:column property="tarikhMula" title="Tarikh Terima"/>
                <display:column property="tarikhTamat" title="Sasaran (Tarikh)"/>--%>
            </display:table>
        </div>
    </fieldset>

</div>
<br>


