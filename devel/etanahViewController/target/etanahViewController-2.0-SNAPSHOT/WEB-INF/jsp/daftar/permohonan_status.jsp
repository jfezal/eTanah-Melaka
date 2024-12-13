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

            function viewHakmilik(id,idPermohonan){
                window.open("${pageContext.request.contextPath}/daftar/kesinambungan?viewhakmilikDetail&idHakmilik="+id+"&idPermohonan="+idPermohonan, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
            }
            function viewSyarikatMCL(id){
                window.open("${pageContext.request.contextPath}/daftar/kesinambungan?viewSyarikatMCLDetail&idMohonPihak="+id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
            }
            
            


        </script>
        <script language="javascript" >
            $(document).ready(function() {
                var l = ${fn:length(actionBean.fasaMohon)};
                var m = document.getElementById('kod');
                var k = document.getElementById('kpsn');
                if((m.value == "HKTHK")||(m.value == "HSTHK")){
                    if((l < 2)&&(k.value == "D")){
                        $('#note').removeAttr("style");
                    }
                }
            });
        </script>
        <s:messages />
        <s:errors />

        <s:form action="/daftar/kesinambungan" >
            <s:text name="permohonan.kodUrusan.kod" value="${actionBean.permohonan.kodUrusan.kod}" id="kod" style="display:none;"/>
            <s:text name="permohonan.keputusan.kod" value="${actionBean.permohonan.keputusan.kod}" id="kpsn" style="display:none;"/>
            <br>
            <fieldset class="aras1">
                <legend>Maklumat Perserahan Belum Daftar/Telah Daftar</legend>
                <br>
                <p><label for="idPermohonan">ID Permohonan/ID Perserahan :</label>
                    ${actionBean.permohonan.idPermohonan}
                </p>

                <p><label for="kodUrusan">Kod Urusan/Urusan :</label>
                    ${actionBean.permohonan.kodUrusan.kod} -
                    ${actionBean.permohonan.kodUrusan.nama}
                </p>
                <c:if test="${actionBean.permohonan.kodUrusan.kodPerserahan.kod eq 'CP'}">
                <%--<p><label>No. Sijil :</label>
                   ${actionBean.pr.noSidang == null ? "-" : actionBean.pr.noSidang }
                </p>--%>
                </c:if>

                <p><label for="tarikhDaftar">Tarikh Permohonan/Perserahan :</label>
                    <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>     <fmt:formatDate pattern="hh:mm aa" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>
                </p>
                
                <p><label for="penyerah">Penyerah :</label>
                    ${actionBean.permohonan.penyerahNama}
                </p>
                
                
                <c:if test="${actionBean.status eq 'SL'}">
                    <p><label for="keputusan">Keputusan :</label>
                        ${actionBean.permohonan.keputusan.kod == null ? "-" : actionBean.permohonan.keputusan.nama}
                    </p>
                </c:if>
                <c:if test="${actionBean.permohonan.keputusan.kod eq  'G'}">
                    <p><label for="keputusan">Keputusan :</label>
                        Gantung - Kemasukan
                    </p>
                </c:if>
                <c:if test="${actionBean.permohonan.keputusan.kod eq  'TK'}">
                    <p><label>Sebab Batal :</label>
                        ${actionBean.permohonan.sebab == null ? "-" : actionBean.permohonan.sebab}
                    </p>
                    <p><label>Pembatalan Oleh :</label>
                        ${actionBean.permohonan.infoAudit.dikemaskiniOleh.nama}
                    </p>
                </c:if>
                <c:if test="${actionBean.permohonan.keputusan.kod eq  'W'}">
                    <p><label>Sebab Tarik Balik :</label>
                        ${actionBean.permohonan.sebab == null ? "-" : actionBean.permohonan.sebab}
                    </p>
                    <%--<p><label>Pembatalan Oleh :</label>
                        ${actionBean.permohonan.infoAudit.dikemaskiniOleh.nama}
                    </p>--%>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.jabatan.kod ne '22'}">
                    <p><label>Peringkat :</label>
                        ${actionBean.stage == null ? "-" : actionBean.stage}
                    </p>
                </c:if>
                <c:if test="${actionBean.flag}">    
                <p><label>Kerani Spoc :</label>                
                    ${actionBean.spoc} 
                </p>
                </c:if>
                <p><label>Kerani Kemasukan :</label>
                    ${actionBean.permohonan.infoAudit.dimasukOleh.nama == null ? "-" : actionBean.permohonan.infoAudit.dimasukOleh.nama}
                </p>                
                <c:if test="${actionBean.pendaftar ne null && actionBean.status eq 'SL'}">
                <p><label>Pendaftar :</label>
                    ${actionBean.pendaftar == null ? "-" : actionBean.pendaftar}
                </p>
                </c:if>
                <p style="display:none;" id="note"><label>&nbsp;</label>
                    <em>Urusan ini telah diselesaikan menggunakan Utiliti Tukarganti.</em>
                </p>
                <br>
            </fieldset>
            <c:if test="${actionBean.permohonan.kodUrusan.kodPerserahan.kod eq 'MH'}">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Hakmilik Sebelum
                    </legend>
                    <c:if test="${actionBean.status eq 'SL'}">
                        <p align="center">
                            <display:table class="tablecloth" name="${actionBean.hmSebelumList}"  cellpadding="0" cellspacing="0" id="line">
                                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                <display:column   title="ID Hakmilik" >
                                    <a href="#" onclick="viewHakmilik('${line.hakmilik.idHakmilik}','${actionBean.idPermohonan}');return false;">${line.hakmilik.idHakmilik}</a>
                                </display:column>
                                <display:column property="hakmilik.noLot" title="No Lot" />
                                <display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                                <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                                <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                            </display:table>
                            &nbsp;
                        </p>
                    </c:if>
                        <c:if test="${actionBean.status ne 'SL'}">
                        <p align="center">
                            <display:table class="tablecloth" name="${actionBean.hmpSebelumList}"  cellpadding="0" cellspacing="0" id="line">
                                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                <display:column   title="ID Hakmilik" >
                                    <a href="#" onclick="viewHakmilik('${line.hakmilikSejarah}','${actionBean.idPermohonan}');return false;">${line.hakmilikSejarah}</a>
                                </display:column>
                                <display:column property="hakmilik.noLot" title="No Lot" />
                                <display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                                <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                                <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                            </display:table>
                            &nbsp;
                        </p>
                        </c:if>
                    </c:if>
                </fieldset>
            <br>
            
            <%--Start Azmi  Cara CPB 1--%>
            <%--Serahan Certificated Person--%>
            <%--<c:if test="${actionBean.permohonan.kodUrusan.kodPerserahan.kod eq 'CP'}">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Certificated Person
                    </legend>
                    
                        <p align="center">
                            <display:table class="tablecloth" name="${actionBean.listHakmilikPihakCP}"  cellpadding="0" cellspacing="0" id="line">
                              
                                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                                <display:column property="pihak.nama" title="Nama" />
                                <display:column property="pihak.noPengenalan" title="No. K/P" />
                                <display:column property="" title="No. Sijil" />
                                
                                
                            </display:table>
                            &nbsp;
                        </p>
                </fieldset>
            </c:if>--%>
            <c:if test="${actionBean.permohonan.kodUrusan.kodPerserahan.kod eq 'CP'}">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Certificated Person
                    </legend>
                    
                        <p align="center">
                            <display:table class="tablecloth" name="${actionBean.senaraiHakmilikCPB}"  cellpadding="0" cellspacing="0" id="line">
                              
                                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                <display:column property="idHakmilik" title="ID Hakmilik" />
                                <display:column property="nama" title="Nama" />
                                <display:column property="ic" title="No. K/P" />
                                <display:column property="noSijil" title="No. Sijil"/>
                                <display:column property="status" title="Status" />
                              
                            </display:table>
                            &nbsp;
                        </p>
                </fieldset>
            </c:if>
            <br>
            <%--Tamat Serahan Certificated Person--%>
            <%--Serahan MCL--%>
            <%--<c:if test="${actionBean.permohonan.kodUrusan.kodPerserahan.kod eq 'SM'}">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Syarikat MCL
                    </legend>
                    
                        <p align="center">
                            <display:table class="tablecloth" name="${actionBean.listHakmilikPihakCP}"  cellpadding="0" cellspacing="0" id="line">
                              
                                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                                <display:column property="pihak.nama" title="Nama" />
                                <display:column property="pihak.noPengenalan" title="No. K/P" />
                            </display:table>
                            &nbsp;
                        </p>
                </fieldset>
            </c:if>--%>
            <c:if test="${actionBean.permohonan.kodUrusan.kodPerserahan.kod eq 'SM'}">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Syarikat MCL
                    </legend>
                        <p align="center">
                            <display:table class="tablecloth" name="${actionBean.senaraiHakmilikCPB}"  cellpadding="0" cellspacing="0" id="line">
                              
                                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                <display:column title="Nama" >
                                <a href="#" onclick="viewSyarikatMCL('${line.idmohonpihak}');return false;">${line.nama}</a>
                                </display:column>
                                <display:column property="ic" title="No. Syarikat" />
                                <display:column property="status" title="Status" />
                            </display:table>
                            &nbsp;
                        </p>
                </fieldset>
            </c:if>
            <br>
            <%--Tamat Serahan MCL--%>
            <%-- Tamat Azmi--%>
            
            <fieldset class="aras1">
                <legend>
                    Maklumat Hakmilik<c:if test="${actionBean.permohonan.kodUrusan.kodPerserahan.kod eq 'MH'}"> Baru</c:if>
                </legend>
                <p align="center">
                    <display:table class="tablecloth" name="${actionBean.permohonan.senaraiHakmilik}"  cellpadding="0" cellspacing="0" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column   title="ID Hakmilik" >
                            <a href="#" onclick="viewHakmilik('${line.hakmilik.idHakmilik}','${actionBean.idPermohonan}');return false;">${line.hakmilik.idHakmilik}</a>
                        </display:column>
                        <display:column property="hakmilik.noLot" title="No Lot" />
                        <display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    
                    </display:table>
                    &nbsp;
                </p><br>
            </fieldset>
            <br>
            <c:if test="${actionBean.permohonan.kodUrusan.kodPerserahan.kod ne 'CP' and actionBean.permohonan.kodUrusan.kodPerserahan.kod ne 'SM' }">
            <fieldset class="aras1">
                <legend>
                    Proses permohonan :
                </legend>
                <p align="center"> 
                <display:table name="${actionBean.fasaMohon}" class="tablecloth" id="line">
                    <display:column property="infoAudit.tarikhMasuk" title="Tarikh" sortable="true" format="{0,date,dd/MM/yyyy hh:mm aa}"/>
                    <display:column title="Peringkat">
                        <c:if test="${line.idAliran eq 'agih_tugas'}">
                            Agih Tugasan
                        </c:if>
                        <c:if test="${line.idAliran eq 'kemasukan'}">
                            Kemasukan
                        </c:if>
                        <c:if test="${line.idAliran eq 'keputusan'}">
                            Keputusan
                        </c:if>
                    </display:column>
                    <display:column property="idPengguna" title="Diproses Oleh"/>
                    <display:column title="Syor / Keputusan">
                        <c:if test="${line.keputusan.kod eq 'SD'}">
                            Syor Daftar - Kemasukan
                        </c:if>
                        <c:if test="${line.keputusan.kod eq 'ST'}">
                            Syor Tolak - Kemasukan
                        </c:if>
                        <c:if test="${line.keputusan.kod eq 'SG'}">
                            Syor Gantung - Kemasukan
                        </c:if>
                        <c:if test="${line.keputusan.kod eq 'D'}">
                            Daftar Kemasukan
                        </c:if>
                        <c:if test="${line.keputusan.kod eq 'T'}">
                            Tolak Kemasukan
                        </c:if>
                        <c:if test="${line.keputusan.kod eq 'G'}">
                            Gantung Kemasukan
                        </c:if>
                    </display:column>
                    <display:column property="tarikhKeputusan" title="Tarikh Keputusan" sortable="true" format="{0,date,dd/MM/yyyy hh:mm aa}"/>
                    <display:column title="Ulasan">
                        ${line.ulasan == null ? "-" : line.ulasan}
                    </display:column>
                </display:table></p><br><br>
            </fieldset>
            </c:if>
        </s:form>

    </body>
</html>