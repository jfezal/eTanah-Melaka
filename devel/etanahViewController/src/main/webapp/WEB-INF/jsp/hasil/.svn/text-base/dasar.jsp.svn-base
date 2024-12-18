<%--
    Document   : dasar
    Created on : Jul 22, 2010, 3:18:55 PM
    Author     : nurfaizati
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/tablecloth.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    function popup(id) {
        window.open("${pageContext.request.contextPath}/hasil/papar_hakmilik_pihak?showForm&idHakmilik=" + id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    function popupNotis(id) {
        window.open("${pageContext.request.contextPath}/hasil/dasar?searchNotis&idHakmilik=" + id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    function doSubmit(f) {
        var url = '${pageContext.request.contextPath}/hasil/surat_peringatan_notis6A?find&idDasar=' + f;
        $.get(url,
                function(data) {
                    $('#dasar').html(data);
                }, 'html');
    }

    function hapusDasar(dasar) {
        if (confirm("Anda pasti untuk hapuskan ID Dasar :" + dasar + " ?")) {
            var f = document.dasar;
            var form = $(f).formSerialize();
            var url = '${pageContext.request.contextPath}/hasil/dasar?deleteDasar&' + form + '&idDasarHapus=' + dasar;
            f.action = url;
            f.submit();
        }
    }
</script>
<div class="subtitle" id="dasar">
    <s:form name="dasar" beanclass="etanah.view.stripes.hasil.DasarActionBean">        
        <s:hidden name= "kodNegeri"/>
        <table width="100%" bgcolor="green">
            <tr>
                <td width="100%" height="20" >
                    <div style="background-color: green;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL : Carian Dasar</font>
                    </div>
                </td>
            </tr>
        </table>
        <fieldset class="aras1">
            <legend>
                Carian
            </legend>
            <div class="instr-fieldset">
                <font color="red">PERINGATAN:</font>Sila Masukkan Salah Satu Maklumat Berikut
            </div>&nbsp;

            <div class="instr">
                <s:errors/>
                <s:messages/>
            </div>

            <p>
                <label for="noAkaun">ID Dasar :</label>
                <s:text name="idDasar" size="30" onblur="this.value = this.value.toUpperCase();"/>
            </p>
            <p>
                <label for="No_Resit">ID Hakmilik :</label>
                <s:text name="idHakmilik" size="30" onblur="this.value = this.value.toUpperCase();"/>
            </p>
            <p>
                <label for="No_Ruj">No Rujukan Notis :</label>
                <s:text name="noRujukanDasar" size="30" onblur="this.value = this.value.toUpperCase();"/>
            </p>
            <table border="0" width="100%">
                <tr>
                    <td align="center">
                        <s:submit   name="search" value="Cari" class="btn"/>
                        <s:button name=" " value="Isi Semula" class="btn" onclick="clearText('dasar');"/>
                    </td>
                </tr>
            </table>

        </fieldset>

        <c:if test="${actionBean.flag}">
            <br>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Maklumat
                    </legend>
                    <div class="content" align="center">
                        <div align="left">
                            <font size="2" color="black">Petunjuk :</font>
                            <p>
                                <img src="${pageContext.request.contextPath}/pub/images/edit.gif"/> - <font size="2" color="black">Klik untuk Kemasukan Hakmilik</font>
                                &nbsp;<b>|</b>&nbsp;
                                <img src="${pageContext.request.contextPath}/images/not_ok.gif"/> - <font size="2" color="black">Klik untuk Hapus Dasar</font>
                            </p>
                        </div>                        
                        <display:table class="tablecloth" name="${actionBean.senaraiDasar}" pagesize="10" cellpadding="0" cellspacing="0" id="line" requestURI="/hasil/dasar" excludedParams="deleteDasar">
                            <c:set var="noHM" value=""/>
                            <c:set var="T" value=""/>
                            <display:column title="Bil" sortable="true"><div align="center">${line_rowNum}</div></display:column>
                            <display:column property="idDasar" title="ID Dasar" />
                            <display:column property="cawangan.name" title="Daerah" />
                            <display:column title="ID Hakmilik" >
                                <c:if test="${fn:length(line.senaraiHakmilik) eq 0}"><em>Tiada</em></c:if>
                                <c:if test="${fn:length(line.senaraiHakmilik) ne 0}">
                                    <c:forEach items="${line.senaraiHakmilik}" var="senarai">
                                        <a href="#" onclick="popup('${senarai.hakmilik.idHakmilik}');
        return false;">${senarai.hakmilik.idHakmilik}</a><br>
                                    </c:forEach>
                                </c:if>
                            </display:column>
                            <display:column title="Status Semasa">
                                <c:set var="N6A" value=""/>
                                <c:set var="N8A" value=""/>
                                <c:set var="SP" value=""/>
                                <c:set var="NG" value=""/>
                                <c:set var="WR" value=""/>                                
                                <c:if test="${fn:length(line.senaraiHakmilik) eq 0}">
                                    <c:set var="noHM" value="ya"/>
                                    <em>Tiada</em>
                                </c:if>
                                <c:if test="${fn:length(line.senaraiHakmilik) ne 0}">
                                    <c:forEach items="${line.senaraiHakmilik}" var="senaraiHM">
                                        <c:if test="${fn:length(senaraiHM.senaraiNotis) eq 0}"><c:set var="T" value="Tiada"/></c:if>
                                        <c:if test="${fn:length(senaraiHM.senaraiNotis) ne 0}">
                                            <c:forEach items="${senaraiHM.senaraiNotis}" var="senaraiNotis">
                                                <c:if test="${senaraiNotis.notis.kod eq 'N6A'}"><c:set var="N6A" value="${senaraiNotis.notis.nama}"/></c:if>
                                                <c:if test="${senaraiNotis.notis.kod eq 'N8A'}"><c:set var="N8A" value="${senaraiNotis.notis.nama}"/></c:if>
                                                <c:if test="${senaraiNotis.notis.kod eq 'SP'}"><c:set var="SP" value="${senaraiNotis.notis.nama}"/></c:if>
                                                <c:if test="${senaraiNotis.notis.kod eq 'NG'}"><c:set var="NG" value="${senaraiNotis.notis.nama}"/></c:if>
                                                <c:if test="${senaraiNotis.notis.kod eq 'WR'}"><c:set var="WR" value="${senaraiNotis.notis.nama}"/></c:if>
                                            </c:forEach>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${SP ne ''}"><c:out value="${SP}"/><br></c:if>
                                <c:if test="${N6A ne ''}"><c:out value="${N6A}"/><br></c:if>
                                <c:if test="${NG ne ''}"><c:out value="${NG}"/><br></c:if>
                                <c:if test="${N8A ne ''}"><c:out value="${N8A}"/><br></c:if>
                                <c:if test="${WR ne ''}"><c:out value="${WR}"/><br></c:if>
                                <c:if test="${T ne ''}"><em><c:out value="${T}"/></em><br></c:if>
                            </display:column>
                            <display:column title="Tarikh Diwujudkan">
                                <div align="center"><fmt:formatDate value="${line.tarikhDasar}" pattern="dd/MM/yyyy"/></div>                                
                            </display:column>
                            <display:column title="Notis 6A" >
                                <c:if test="${fn:length(line.senaraiHakmilik) eq 0}"><em>Tiada</em></c:if>
                                <c:if test="${fn:length(line.senaraiHakmilik) ne 0}">
                                    <c:forEach items="${line.senaraiHakmilik}" var="senarai">
                                        ${senarai.perserahan6A.idPermohonan}<br>
                                    </c:forEach>
                                </c:if>
                            </display:column>
                            <display:column title="Pembatalan 6A" >
                                <c:if test="${fn:length(line.senaraiHakmilik) eq 0}"><em>Tiada</em></c:if>
                                <c:if test="${fn:length(line.senaraiHakmilik) ne 0}">
                                    <c:forEach items="${line.senaraiHakmilik}" var="senarai">
                                        ${senarai.perserahanBatal6A.idPermohonan}<br>
                                    </c:forEach>
                                </c:if>
                            </display:column>
                            <display:column title="Notis 8A" >
                                <c:if test="${fn:length(line.senaraiHakmilik) eq 0}"><em>Tiada</em></c:if>
                                <c:if test="${fn:length(line.senaraiHakmilik) ne 0}">
                                    <c:forEach items="${line.senaraiHakmilik}" var="senarai">
                                        ${senarai.perserahan8A.idPermohonan}<br>
                                    </c:forEach>
                                </c:if>
                            </display:column>
                            <display:column title="Pembatalan 8A" >
                                <c:if test="${fn:length(line.senaraiHakmilik) eq 0}"><em>Tiada</em></c:if>
                                <c:if test="${fn:length(line.senaraiHakmilik) ne 0}">
                                    <c:forEach items="${line.senaraiHakmilik}" var="senarai">
                                        ${senarai.perserahan8A.idPermohonan}<br>
                                    </c:forEach>
                                </c:if>
                            </display:column>
                            <display:column title="Tindakan">
                                <c:if test="${actionBean.pengguna.kodCawangan.kod eq line.cawangan.kod}">
                                    <div align="center">
                                        <c:if test="${noHM ne ''}">
                                            <img alt='Klik untuk Kemasukan Hakmilik' title="Kemasukan Hakmilik dalam dasar :${line.idDasar}" border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="doSubmit('${line.idDasar}');
        return false;" onmouseover="this.style.cursor = 'pointer';">
                                            &nbsp; | &nbsp;
                                            <img alt='Klik untuk Hapus Dasar' title="Hapus Dasar :${line.idDasar}" border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="hapusDasar('${line.idDasar}');
        return false;" onmouseover="this.style.cursor = 'pointer';">
                                        </c:if>
                                        <c:if test="${T  ne ''}">
                                            <img alt='Klik untuk Hapus Dasar' title="Hapus Dasar :${line.idDasar}" border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="hapusDasar('${line.idDasar}');
        return false;" onmouseover="this.style.cursor = 'pointer';">
                                        </c:if>
                                    </div>
                                </c:if>
                            </display:column>
                            <display:column title="No Warta">
                                <c:set var="N8A" value=""/>
                                <c:set var="NG" value=""/>
                                <c:if test="${fn:length(line.senaraiHakmilik) ne 0}">
                                    <c:forEach items="${line.senaraiHakmilik}" var="senaraiHM">
                                        <c:if test="${fn:length(senaraiHM.senaraiNotis) ne 0}">
                                            <c:forEach items="${senaraiHM.senaraiNotis}" var="senaraiNotis">
                                                <c:if test="${senaraiNotis.notis.kod eq 'N6A'}"><br></c:if>
                                                <c:if test="${senaraiNotis.notis.kod eq 'N8A'}"><c:set var="N8A" value="${senaraiNotis.noRujukan}"/></c:if>
                                                <c:if test="${senaraiNotis.notis.kod eq 'SP'}"><br></c:if>
                                                <c:if test="${senaraiNotis.notis.kod eq 'NG'}"><c:set var="NG" value="${senaraiNotis.noRujukan}"/></c:if>
                                                <c:if test="${senaraiNotis.notis.kod eq 'WR'}"><br></c:if>
                                            </c:forEach>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${NG ne ''}"><c:out value="${NG}"/><br></c:if>
                                <c:if test="${N8A ne ''}"><c:out value="${N8A}"/><br></c:if>
                                <c:if test="${T ne ''}"><em><c:out value="${T}"/></em><br></c:if>
                            </display:column>    
                        </display:table>
                    </div>
                </fieldset>
            </div>        
        </c:if>

    </s:form>
</div>