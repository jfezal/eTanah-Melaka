<%-- 
    Document   : permohonan_status_lelong
    Created on : Apr 13, 2011, 11:03:04 AM
    Author     : mazurahayati.yusop
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<%-- <script type="text/javascript"
       src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>--%>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/styles/styles.css" />
        <title>Status Permohonan</title>

    </head>
    <body>
        <script type="text/javascript">

            function viewHakmilik(id){
                window.open("${pageContext.request.contextPath}/lelong/status?viewhakmilikDetail&idHakmilik="+id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=500");
            }
            
            function viewDetail(id){
                window.open("${pageContext.request.contextPath}/lelong/status?viewDetail&idLelong="+id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=500");
            }

            <%--  function viewSejarah(id){
                  window.open("${pageContext.request.contextPath}/lelong/status?viewSejarah&idPermohonan="+id, "eTanah",
                  "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
              }--%>


        </script>
        <s:messages />
        <s:errors />

        <s:form action="/lelong/status" >
            <br>
            <fieldset class="aras1">

                <legend>Maklumat Permohonan</legend>
                <br>
                <p><label for="idPermohonan">ID Permohonan/ID Perserahan :</label>
                    ${actionBean.permohonan.idPermohonan}
                    <%--<a href="#" onclick="viewSejarah('${actionBean.permohonan.idPermohonan}');return false;"> ${actionBean.permohonan.idPermohonan} </a>--%>
                </p>
                <c:if test="${actionBean.idSebelum ne null}">
                    <p>
                        <label>ID Permohonan Terdahulu: </label>
                        <font style="text-transform:uppercase;">${actionBean.idSebelum}</font>&nbsp;
                    </p>
                </c:if>



                <p><label for="kodUrusan">Kod Urusan/Urusan :</label>
                    ${actionBean.permohonan.kodUrusan.kod} -
                    ${actionBean.permohonan.kodUrusan.nama}
                </p>

                <p><label for="tarikhDaftar">Tarikh Perserahan/Permohonan :</label>
                    <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>     <fmt:formatDate pattern="hh:mm aa" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>
                </p>

                <p><label for="penyerah">Penyerah :</label>
                    ${actionBean.permohonan.penyerahNama}
                </p>

                <p><label for="keputusan">Keputusan :</label>
                    ${actionBean.permohonan.keputusan.kod == null ? "-" : actionBean.permohonan.keputusan.nama}
                </p>
                <c:if test="${actionBean.permohonan.kodUrusan.jabatan.kod ne '22'}">
                    <p><label for="pguna">Peringkat :</label>
                        ${actionBean.stage == null ? "-" : actionBean.stage}
                    </p>
                </c:if>
            </fieldset>

            <fieldset class="aras1">
                <legend>
                    Maklumat Hakmilik
                </legend>
                <p>

                    <display:table class="tablecloth" name="${actionBean.permohonan.senaraiHakmilik}"  cellpadding="0" cellspacing="0" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column   title="ID Hakmilik" >
                            <a href="#" onclick="viewHakmilik('${line.hakmilik.idHakmilik}');return false;">${line.hakmilik.idHakmilik}</a>
                        </display:column>
                        <display:column property="hakmilik.noLot" title="No Lot" />
                        <display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                        <%--                        <display:column title="Details">
                                                    <a href="#" onclick="viewSejarah('${line.permohonan.idPermohonan}');return false;">${line.permohonan.idPermohonan}</a>
                                                </display:column>--%>
                    </display:table>


                    &nbsp;
                </p>
            </fieldset>

            <fieldset class="aras1">
                <legend>
                    Proses permohonan:
                </legend>


                <display:table name="${actionBean.permohonan.senaraiFasa}" class="tablecloth">

                    <display:column property="infoAudit.tarikhMasuk" title="Tarikh" sortable="true" format="{0,date,dd/MM/yyyy hh:mm aa}"/>

                    <display:column property="infoAudit.dimasukOleh.nama" title="Diproses Oleh"/>

                    <display:column property="idAliran" title="ID Aliran"/>

                    <display:column property="keputusan.nama" title="Status"/>

                    <display:column property="ulasan" title="Ulasan"/>

                </display:table>
            </fieldset>
            <br>
            <br>

            <fieldset class="aras1">
                <legend>
                    Sejarah Siasatan
                </legend>
                <display:table class="tablecloth" name="${actionBean.senaraiEnkuiri2}" id="line">
                    <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                    <display:column title="Tarikh"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhEnkuiri}"/></display:column>
                    <display:column title="Masa"><fmt:formatDate pattern="hh:mm aaa" value="${line.tarikhEnkuiri}"/></display:column>
                    <display:column property="tempat" title="Tempat" class="nama${line_rowNum}"/>
                    <display:column property="perkara" title="Perkara" class="nama${line_rowNum}"/>
                    <display:column property="status.nama" title="Status" class=""/>
                    <display:column property="ulasanPegawai" title="Ulasan" class="nama${line_rowNum}"/>
                </display:table>
            </fieldset>

            <br>
            <fieldset class="aras1">
                <legend>
                    Sejarah Lelongan
                </legend>
                <display:table class="tablecloth" name="${actionBean.senaraiLelongan}" id="line" >
                    <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                    <display:column title="IDHakmilik" property="hakmilikPermohonan.hakmilik.idHakmilik"/>
                    <display:column title="Tarikh Lelongan Awam"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhLelong}"/></display:column>
                    <display:column title="Masa"><fmt:formatDate pattern="hh:mm aaa" value="${line.tarikhLelong}"/></display:column>
                    <display:column property="tempat" title="Tempat" class="nama${line_rowNum}"/>
                    <display:column title="Harga Rizab (RM)"><div align="right">
                            <fmt:formatNumber pattern="#,##0.00" value="${line.hargaRizab}"/>
                        </div></display:column>
                    <display:column title="Deposit (RM)"><div align="right">
                            <fmt:formatNumber pattern="#,##0.00" value="${line.deposit}"/>
                        </div></display:column>
                    <display:column title="Tarikh Akhir Bayaran"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhAkhirBayaran}"/></display:column>
                    <c:if test="${actionBean.showPopupPembidaBJ eq true}">
                        <c:forEach items="${actionBean.checkListStatusPembida2}" var="line2" >

                            <c:if test="${fn:length(actionBean.checkListStatusPembida2)==1}">
                                <c:if test="${line.idLelong eq line2.lelong.idLelong}">
                                    <display:column title="Status" ><a href="#" onclick="viewDetail('${line.idLelong}');return false;">${line.kodStatusLelongan.nama}</a></display:column>
                                </c:if>
                                <c:if test="${line.idLelong ne line2.lelong.idLelong}">
                                    <display:column title="Status" >${line.kodStatusLelongan.nama}</display:column>
                                </c:if> 
                            </c:if>
                            <c:if test="${fn:length(actionBean.checkListStatusPembida2)>=2}">
                                <c:if test="${line.idLelong eq line2.lelong.idLelong}">
                                    <display:column title="Status" ><a href="#" onclick="viewDetail('${line.idLelong}');return false;">${line.kodStatusLelongan.nama}</a></display:column>
                                </c:if>
                            </c:if>
                        </c:forEach>
                    </c:if>
                    <c:if test="${actionBean.showPopupPembidaBJ eq false}">
                        <display:column title="Status" >${line.kodStatusLelongan.nama}</display:column>
                    </c:if>
                    <c:forEach items="${actionBean.pembidaList}" var="line2" >
                        <c:if test="${fn:length(actionBean.pembidaList)==1}">
                            <c:if test="${line.idLelong eq line2.lelong.idLelong}">
                                <display:column title="Pembida  ">${line2.pihak.nama}</display:column>
                            </c:if>
                            <c:if test="${line.idLelong ne line2.lelong.idLelong}">
                                <display:column title="Pembida  "></display:column>
                            </c:if>

                        </c:if>
                        <c:if test="${fn:length(actionBean.pembidaList)>=2}">
                            <c:if test="${line.idLelong eq line2.lelong.idLelong}">
                                <display:column title="Pembida ">${line2.pihak.nama}</display:column>
                            </c:if>                            
                        </c:if>                       
                    </c:forEach>
                    <display:column title="Jurulelong">
                        <c:if test="${line.jurulelong.idJlb eq null}">   
                            -
                        </c:if>
                        <c:if test="${line.jurulelong.idJlb ne null}">   
                            ${line.jurulelong.nama}
                        </c:if>

                    </display:column>
                    <%--<display:column title="Ulasan" class=""/>--%>
                </display:table>

            </fieldset>
        </s:form>

    </body>
</html>
