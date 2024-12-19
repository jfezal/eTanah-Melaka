<%-- 
    Document   : strata_laporan
    Created on : 20 SEPT 2013, 12:43:28 AM
    Author     : abu.mansur
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<script type="text/javascript">
    function popupParam(nama,idhakmilik){
        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+"&reportName="+nama+"&report_p_id_hakmilik="+idhakmilik, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    }

    function jana2k3k(frm){
        var idHakmilikInduk = $('#idHakmilikInduk').val();

        var url = '${pageContext.request.contextPath}/strata/Geranstrata2K3K?jana&idHakmilikInduk='+ idHakmilikInduk;
        frm.action = url;
        frm.submit();
    }
    function search(frm){
        var idHakmilikInduk = $('#idHakmilikInduk').val();

        var url = '${pageContext.request.contextPath}/strata/Geranstrata2K3K?cari&idHakmilikInduk='+ idHakmilikInduk;
        frm.action = url;
        frm.submit();
    }

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function doSaveCapaian(v,b){
        var idHakmilik = b;
        var url = '${pageContext.request.contextPath}/strata/Geranstrata2K3K?cetakSemula&id_dokumen=' + v + '&idHakmilik=' + idHakmilik;
        $.ajax({
            type: "GET",
            url: url,
            success: function(data) {
                doPrintViaApplet(v);
            }
        });

    }
    
    function doPrintViaApplet(docId) {
    <%--alert('tsttt');--%>
            var a = document.getElementById('applet');
            a.doPrint(docId.toString());
        }

        function sejarahCetakan(id){
            window.open("${pageContext.request.contextPath}/daftar/cetak_semula_dokumen?viewSejarahCetakan&idDokumen="+id, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
        }
        function sejarahPaparan(id){
            window.open("${pageContext.request.contextPath}/strata/Geranstrata?viewSejarahPaparan&idDokumen="+id, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
        }

    
        function popup(url)
        {
            params  = 'width='+screen.width;
            params += ', height='+screen.height;
            params += ', top=0, left=0'
            params += ', fullscreen=no';
            params += ', directories=no';
            params += ', location=no';
            params += ', menubar=no';
            params += ', resizable=no';
            params += ', scrollbars=yes';
            params += ', status=no';
            params += ', toolbar=no';
            newwin=window.open(url,'PopUp', params);
            if (window.focus) {newwin.focus()}
            return false;
        }
</script>

<div id="laporan">
    <s:messages/>
    <s:errors/>
    <s:form beanclass="etanah.view.strata.laporan.GeranStrataActionBean" id="cetak_hakmilik">
        <div class="subtitle">

            <fieldset class="aras1">
                <legend>ID Hakmilik </legend>
                <%--<span class=instr>* Sila gunakan carian ini untuk cetakan semula dokumen Borang 2(K),Borang 3(K),Borang 4K(DHDK),Borang 4K(DHKK).</span><br/>--%>

                <div align="center">
                    <table border=0 class="tablecloth"><tr>
                            <th align=right>
                                ID Hakmilik Induk (Master Title):
                            </th>
                            <td align=left>

                                <s:text name="idHakmilikInduk" id="idHakmilikInduk"  size="37"
                                        onkeyup="this.value = this.value.toUpperCase();"  onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>&nbsp;

                            </td>
                    </table>
                    <br>
                    <p>
                        <s:button name="" value="Isi Semula" class="btn" onclick="clearText('cetak_hakmilik');"/>
                        <s:button name="cari" value="Cari" class="btn" id="cari" onclick="search(this.form);"/>

                    </p>
                </div>
            </fieldset>

            <br/>
            <c:if test="${actionBean.idHakmilikInduk ne null && actionBean.kodnegeri eq '05'}">
                <c:if test="${versi}">
                    <table class="tablecloth" align="center">
                        <tr style="width: 100%">
                            <th>Senarai Borang</th>
                            <th>Id Hakmilik</th>
                            <th>Papar</th>
                        </tr>
                        <tr>
                            <td>Borang 3(K)</td>
                            <td>${actionBean.idHakmilikInduk}</td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popupParam('UTILITIB3K_NS','${actionBean.idHakmilikInduk}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Borang 2(K)</td>
                            <td>${actionBean.idHakmilikInduk}</td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popupParam('UTILITIB2K_NS','${actionBean.idHakmilikInduk}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                    </table>

                    <center>
                        <p>
                            <s:button name="" value="Daftar Versi 1" class="longbtn" onclick="jana2k3k(this.form);"/>

                        </p>
                    </center>
                </c:if>
                <br>
            </c:if>
            <c:if test="${actionBean.idHakmilikInduk ne null && actionBean.kodnegeri eq '04'}">
                <c:if test="${versi}">
                    <table class="tablecloth" align="center">
                        <tr style="width: 100%">
                            <th>Senarai Borang</th>
                            <th>Id Hakmilik</th>
                            <th>Papar</th>
                        </tr>
                        <tr>
                            <td>Borang 3(K)</td>
                            <td>${actionBean.idHakmilikInduk}</td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popupParam('UTILITIB3K_MLK','${actionBean.idHakmilikInduk}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Borang 2(K)</td>
                            <td>${actionBean.idHakmilikInduk}</td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popupParam('UTILITIB2K_MLK','${actionBean.idHakmilikInduk}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                    </table>

                    <center>
                        <p>
                            <s:button name="" value="Daftar Versi 1" class="longbtn" onclick="jana2k3k(this.form);"/>

                        </p>
                    </center>
                </c:if>
                <br>
            </c:if>
            <c:if test="${!versi}">
                <display:table style="width:90%" class="tablecloth" name="${actionBean.senaraiDokumen}" pagesize="20" cellpadding="0" cellspacing="0" id="line1">
                    <display:column title="Tajuk">
                        ${line1.tajuk}

                    </display:column>
                    <display:column title="Tarikh Tukarganti">
                        ${line1.infoAudit.tarikhMasuk}
                    </display:column>
                    <display:column title="Tukarganti Oleh">
                        ${line1.infoAudit.dimasukOleh.nama}
                    </display:column>
                    <display:column title="Paparan">
                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" title="Papar ${line1.tajuk}"
                             onclick="doViewReport('${line1.idDokumen}');" height="20" width="20" alt="papar" onmouseover="this.style.cursor='pointer';"/>
                    </display:column>
                    <display:column title="Sejarah Paparan">
                        <c:set var="count" value="0"/>
                        <c:forEach items="${line1.senaraiCapaian}" var="item">
                            <c:if test="${item.aktiviti.kod eq 'PD'}" >
                                <c:set var="count" value="${count+1}"/>
                            </c:if>
                        </c:forEach>
                        <a href="#" onclick="sejarahPaparan('${line1.idDokumen}');return false;">Papar :<c:out value="${count}"/> rekod</a>
                    </display:column>
                    <display:column title="Sejarah Cetakan">
                        <c:set var="count" value="0"/>
                        <c:forEach items="${line1.senaraiCapaian}" var="item">
                            <c:if test="${item.aktiviti.kod eq 'CD'}" >
                                <c:set var="count" value="${count+1}"/>
                            </c:if>
                        </c:forEach>
                        <a href="#" onclick="sejarahCetakan('${line1.idDokumen}');return false;">Cetak :<c:out value="${count}"/> rekod </a>
                    </display:column>
                    <display:column title="Cetak">
                        <c:if test="${line1.namaFizikal != null}">
                            <%--<s:button name="cetak" value="Cetak" onclick="doPrintViaApplet('${line.idDokumen}');" class="btn"/>--%>
                            <p align="center"><img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png" title="Cetak ${line1.tajuk}"
                                                   onclick="doSaveCapaian('${line1.idDokumen}','${line1.hakmilik}');" height="20" width="20" alt="cetak" onmouseover="this.style.cursor='pointer';"/></p>
                            </c:if>
                        </display:column>



                </display:table>
                <applet code="PrintApplet.class" ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
                        ${pageContext.request.contextPath}/commons-httpclient-2.0.2.jar,
                        ${pageContext.request.contextPath}/commons-logging.jar,
                        ${pageContext.request.contextPath}/swingx-1.6.jar,
                        ${pageContext.request.contextPath}/log4j-1.2.12.jar,
                        ${pageContext.request.contextPath}/jpedal_demo.jar"
                        codebase = "."
                        name     = "applet"
                        id       = "applet"
                        width    = "1"
                        height   = "1"
                        align    = "middle">
                    <param name ="uploadAction" value="<%=request.getContextPath()%>/PrintServlet"/>
                    <param name ="disabledWatermark" value="true"/>
                    <param name ="withoutSignature" value="true"/>
                    <param name ="method" value="pdfp">
                    <param name ="idPengguna" value="${idPengguna}"/>
                    <%
                                Cookie[] cookies = request.getCookies();
                                StringBuffer sb = new StringBuffer();
                                for (int i = 0; i < cookies.length; i++) {
                                    sb.setLength(0);
                                    sb.append(cookies[i].getName());
                                    sb.append("=");
                                    sb.append(cookies[i].getValue());
                    %>
                    <param name="Cookie<%= i%>" value="<%= sb.toString()%>"><%
                                }
                    %>
                </applet>
            </c:if>
        </div>
    </s:form>
</div>
