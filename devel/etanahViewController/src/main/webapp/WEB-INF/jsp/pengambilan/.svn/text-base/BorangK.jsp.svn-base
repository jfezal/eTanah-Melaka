<%-- 
    Document   : Borang K
    Created on : 23-Nov-2009, 13:17:22
    Author     : nordiyana
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">


    function showReportS(){
        window.open("${pageContext.request.contextPath}/pengambilan/borangK?genReportS", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
    }
      function showReportK(){
        window.open("${pageContext.request.contextPath}/pengambilan/borangK?genReportK", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
    }
</script>
<s:form beanclass="etanah.view.stripes.pengambilan.BorangKActionBean">
    <s:messages/>
    <s:errors/>
    <c:if test="${actionBean.mesej eq null}"/>
    <c:if test="${edit}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Notis Bahawa Tanah Telah Diambil Milik
                </legend>

                <div class="content" align="center">
                    <%--
                                   <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" cellpadding="0" cellspacing="0"
                                                   requestURI="/borangpengambilanmodule" id="line">
                                       <s:hidden name="hakmilikPermohonan.id"></s:hidden>
                                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
                                        <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                                        <display:column title="Orang yang Berkepentingan">
                                                         <c:set value="1" var="count"/>
                                                     <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                                     <c:if test="${senarai.jenis.kod eq 'PM'}">
                                                         <br>
                                                         <c:out value="${count}"/>)&nbsp;
                                                     <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;<br>
                                                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.KP/Co :&nbsp;<c:out value="${senarai.pihak.noPengenalan}"/>
                                                         <c:set value="${count + 1}" var="count"/>
                                                     </c:if>
                                                     </c:forEach>
                                                     </display:column>
                                        <display:column title="Luas Sebenar"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                                        <display:column title="Luas Diambil"><fmt:formatNumber pattern="#,##0.0000" value="${line.luasTerlibat}"/> &nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                                        <display:column title="Perbicaraan Pengambilan No" style="vertical-align:baseline">
                                            <s:text size="5" name="idBicara[${line_rowNum - 1}]" id="idBicara${line_rowNum - 1}"/>&nbsp;
                                            <s:hidden name="count" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="count"/>${actionBean.idBicara[line_rowNum - 1]}
                                        </display:column>
                                       <display:column title="No Warta" style="vertical-align:baseline">
                                            ${actionBean.item}
                                        </display:column>
                                        <display:column title="Tarikh Warta" style="vertical-align:baseline">
                                           ${actionBean.tarikhLulus}
                                        </display:column>&nbsp;
                                    </display:table>
                    --%>

                    <br>

                    <c:if test="${actionBean.sebahagian eq 'true' && actionBean.keseluruhan eq 'false'}">
                        Pengambilan Sebahagian Tanah ${actionBean.hakmilik.id}
                        <br>
                        <display:table class="tablecloth" name="${actionBean.sebahagianList}" pagesize="20" cellpadding="0" cellspacing="0"
                                       requestURI="/borangpengambilanmodule" id="line">
                            <s:hidden name="hakmilikPermohonan.id"></s:hidden>
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                            <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                            <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                            <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                            <%--<display:column title="Orang yang Berkepentingan">
                                             <c:set value="1" var="count"/>
                                         <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                         <c:if test="${senarai.jenis.kod eq 'PM'}">
                                             <br>
                                             <c:out value="${count}"/>)&nbsp;
                                         <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;<br>
                                         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.KP/Co :&nbsp;<c:out value="${senarai.pihak.noPengenalan}"/>
                                             <c:set value="${count + 1}" var="count"/>
                                         </c:if>
                                         </c:forEach>
                                         </display:column>--%>
                            <display:column title="Luas Sebenar"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                            <display:column title="Luas Diambil"><fmt:formatNumber pattern="#,##0.0000" value="${line.luasTerlibat}"/> &nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                            <display:column title="Perbicaraan Pengambilan No" style="vertical-align:baseline">
                                <%-- <s:text size="5" name="idBicara[${line_rowNum - 1}]" id="idBicara${line_rowNum - 1}"/>&nbsp;
                                 <s:hidden name="count" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="count"/>--%>${actionBean.idBicara[line_rowNum - 1]}
                            </display:column>
                            <display:column title="No Warta" style="vertical-align:baseline">
                                ${actionBean.item}
                            </display:column>
                            <display:column title="Tarikh Warta" style="vertical-align:baseline">
                                ${actionBean.tarikhLulus}
                            </display:column>&nbsp;
                        </display:table>
                        <br>
                      <%--  <c:if test="${actionBean.stageId ne '41_2MaklumanEndorsan'}">
                            <p align="center">
                                <s:button name="genReport" id="report" value="Jana Borang K" class="longbtn" onclick="showReport();"/>&nbsp;
                            </p>
                        </c:if>--%>
                        <%--<c:if test="${actionBean.stageId ne '41_2MaklumanEndorsan'}">--%>
                            <p align="center">
                                <s:button name="genReportS" id="report" value="Jana Borang K(S)" class="longbtn" onclick="showReportS();"/>&nbsp;
                            </p>
                        <%--</c:if>--%>
                    </c:if>

                    <c:if test="${actionBean.keseluruhan eq 'true' && actionBean.sebahagian eq 'false'}">
                        <br>
                        Pengambilan Keseluruhan Tanah<br>
                        <display:table class="tablecloth" name="${actionBean.keseluruhanList}" pagesize="20" cellpadding="0" cellspacing="0"
                                       requestURI="/borangpengambilanmodule" id="line">
                            <s:hidden name="hakmilikPermohonan.id"></s:hidden>
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                            <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                            <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                            <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                            <%-- <display:column title="Orang yang Berkepentingan">
                                              <c:set value="1" var="count"/>
                                          <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                          <c:if test="${senarai.jenis.kod eq 'PM'}">
                                              <br>
                                              <c:out value="${count}"/>)&nbsp;
                                          <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;<br>
                                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.KP/Co :&nbsp;<c:out value="${senarai.pihak.noPengenalan}"/>
                                              <c:set value="${count + 1}" var="count"/>
                                          </c:if>
                                          </c:forEach>
                                          </display:column>--%>
                            <display:column title="Luas Sebenar"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                            <display:column title="Luas Diambil"><fmt:formatNumber pattern="#,##0.0000" value="${line.luasTerlibat}"/> &nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                            <display:column title="Perbicaraan Pengambilan No" style="vertical-align:baseline">
                                <%-- <s:text size="5" name="idBicara[${line_rowNum - 1}]" id="idBicara${line_rowNum - 1}"/>&nbsp;
                                 <s:hidden name="count" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="count"/>--%>${actionBean.idBicara[line_rowNum - 1]}
                            </display:column>
                            <display:column title="No Warta" style="vertical-align:baseline">
                                ${actionBean.item}
                            </display:column>
                            <display:column title="Tarikh Warta" style="vertical-align:baseline">
                                ${actionBean.tarikhLulus}
                            </display:column>&nbsp;
                        </display:table>
                    </div>
                    <br>
                    <br>
                   <%-- <c:if test="${actionBean.stageId ne '41_2MaklumanEndorsan'}">
                        <p align="center">
                            <s:button name="genReport" id="report" value="Jana Borang K" class="longbtn" onclick="showReport();"/>&nbsp;
                        </p>
                    </c:if>--%>
                            <p align="center">
                                <s:button name="genReportK" id="report" value="Jana Borang K(K)" class="longbtn" onclick="showReportK();"/>&nbsp;
                            </p>
                </fieldset>

            </div>
        </c:if>
        <c:if test="${actionBean.keseluruhan eq 'true' && actionBean.sebahagian eq 'true'}">
            <br>
            Pengambilan Keseluruhan Tanah<br>
            <display:table class="tablecloth" name="${actionBean.keseluruhanList}" pagesize="20" cellpadding="0" cellspacing="0"
                           requestURI="/borangpengambilanmodule" id="line">
                <s:hidden name="hakmilikPermohonan.id"></s:hidden>
                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                <%-- <display:column title="Orang yang Berkepentingan">
                                  <c:set value="1" var="count"/>
                              <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                              <c:if test="${senarai.jenis.kod eq 'PM'}">
                                  <br>
                                  <c:out value="${count}"/>)&nbsp;
                              <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;<br>
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.KP/Co :&nbsp;<c:out value="${senarai.pihak.noPengenalan}"/>
                                  <c:set value="${count + 1}" var="count"/>
                              </c:if>
                              </c:forEach>
                              </display:column>--%>
                <display:column title="Luas Sebenar"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                <display:column title="Luas Diambil"><fmt:formatNumber pattern="#,##0.0000" value="${line.luasTerlibat}"/> &nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                <display:column title="Perbicaraan Pengambilan No" style="vertical-align:baseline">
                    <%-- <s:text size="5" name="idBicara[${line_rowNum - 1}]" id="idBicara${line_rowNum - 1}"/>&nbsp;
                     <s:hidden name="count" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="count"/>--%>${actionBean.idBicara[line_rowNum - 1]}
                </display:column>
                <display:column title="No Warta" style="vertical-align:baseline">
                    ${actionBean.item}
                </display:column>
                <display:column title="Tarikh Warta" style="vertical-align:baseline">
                    ${actionBean.tarikhLulus}
                </display:column>&nbsp;
            </display:table>
            <%--</div>--%>
            <br>
            <%--<c:if test="${actionBean.stageId ne '41_2MaklumanEndorsan'}">--%>
                <p align="center">
                    <s:button name="genReportK" id="report" value="Jana Borang K(K)" class="longbtn" onclick="showReportK();"/>&nbsp;
                </p>
            <%--</c:if>--%>
            <p align="center">

                Pengambilan Sebahagian Tanah
                <br>
                <display:table class="tablecloth" name="${actionBean.sebahagianList}" pagesize="20" cellpadding="0" cellspacing="0"
                               requestURI="/borangpengambilanmodule" id="line">
                    <s:hidden name="hakmilikPermohonan.id"></s:hidden>
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
                    <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <%--<display:column title="Orang yang Berkepentingan">
                                     <c:set value="1" var="count"/>
                                 <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                 <c:if test="${senarai.jenis.kod eq 'PM'}">
                                     <br>
                                     <c:out value="${count}"/>)&nbsp;
                                 <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;<br>
                                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.KP/Co :&nbsp;<c:out value="${senarai.pihak.noPengenalan}"/>
                                     <c:set value="${count + 1}" var="count"/>
                                 </c:if>
                                 </c:forEach>
                                 </display:column>--%>
                    <display:column title="Luas Sebenar"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column title="Luas Diambil"><fmt:formatNumber pattern="#,##0.0000" value="${line.luasTerlibat}"/> &nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column title="Perbicaraan Pengambilan No" style="vertical-align:baseline">
                        <%-- <s:text size="5" name="idBicara[${line_rowNum - 1}]" id="idBicara${line_rowNum - 1}"/>&nbsp;
                         <s:hidden name="count" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="count"/>--%>${actionBean.idBicara[line_rowNum - 1]}
                    </display:column>
                    <display:column title="No Warta" style="vertical-align:baseline">
                        ${actionBean.item}
                    </display:column>
                    <display:column title="Tarikh Warta" style="vertical-align:baseline">
                        ${actionBean.tarikhLulus}
                    </display:column>&nbsp;
                </display:table>
                <%--<c:if test="${actionBean.stageId ne '41_2MaklumanEndorsan'}">--%>
                <p align="center">
                    <s:button name="genReportS" id="report" value="Jana Borang K(S)" class="longbtn" onclick="showReportS();"/>&nbsp;
                </p>
            <%--</c:if>--%>

        </c:if>

        <%--</fieldset>--%>

        <%--</div>--%>

    </c:if>

</s:form>

