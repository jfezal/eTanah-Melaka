<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.tools.min.js?select=full&debug=true"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript">

    $(document).ready(function(){
        dialogInit('Carian Hakmilik');
        $('#idHakmilik').keyup(function(){
            closeDialog();
        });
    });

    function doSearch(e,f) {
        var a = $('#idPermohonan').val();
        var b = $('#idHakmilik').val();
        var c = $('#idResit').val();
        if(a == '' && b == '' && c == ''){
            alert('Sila masukan id Perserahan atau Hakmilik atau No Resit');            
            return;
        }
        f.action = f.action + '?' + e;
        f.submit();
    }

    function doSearch2 (idPermohonan) {
        var url = '${pageContext.request.contextPath}/daftar/cetakSemulaResit_SijilCarian?search&idPermohonan=' + idPermohonan;
        f = document.form1;
        f.action = url;
        f.submit();
    }

    function carianSemula () {
        $('#idPermohonan').val('');
        $('#idHakmilik').val('');
        $('#idResit').val('');
        var url = '${pageContext.request.contextPath}/daftar/cetakSemulaResit_SijilCarian';
        f = document.form1;
        f.action = url;
        f.submit();
    }
    
    function doPrintViaApplet (docId) {
        //alert('tsttt');
        var a = document.getElementById('applet');
        a.doPrint(docId.toString());
        //a.printDocument(docId.toString());
    }

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function doSaveCapaian(v){        
        var sbb = $('#sbb_cetakan_semula').val();
        if(sbb == '') {
            alert('Sila masukan Sebab');
        return;
        }
        var url = '${pageContext.request.contextPath}/daftar/cetakSemulaResit_SijilCarian?cetakSemula&sbb_cetakan_semula=' + sbb + '&id_dokumen=' + v;
        $.ajax({
            type:"GET",
            url : url,
            success : function(data) {
                if(data == '1'){
                    doPrintViaApplet(v);
                }
            }
        });        
    }

    function toUppercase(id){
        $('#' + id).val($('#' + id).val().toUpperCase());
    }
</script>
<style type="text/css">
    .tooltip {
        display:none;
        background:transparent url(${pageContext.request.contextPath}/pub/images/black_arrow.png);
        font-size:12px;
        height:70px;
        width:160px;
        padding:25px;
        color:#fff;
    }

    <%--.tooltip {
        background-color:#000;
        border:1px solid #fff;
        padding:10px 15px;
        width:200px;
        display:none;
        color:#fff;
        text-align:left;
        font-size:12px;

               /* outline radius for mozilla/firefox only */
               -moz-box-shadow:0 0 10px #000;
               -webkit-box-shadow:0 0 10px #000;
           }--%>
</style>
<s:messages/>
<s:errors/>
<s:form beanclass="etanah.view.daftar.utiliti.CetakSemulaSijil" name="form1">
    <div class="subtitle">
<!--        Menu: Utility Pendaftaran > Cetakan Semula Resit/Sijil Carian-->
        <fieldset class="aras1">
            <legend>
                Cetakan Semula Resit / Sijil Carian
            </legend>
            <p>
                <label>ID Perserahan :</label>
                <s:text name="idPermohonan" id="idPermohonan" onblur="toUppercase(this.id);"/>
            </p>
            <p>
                <label>Hakmilik :</label>
                <s:text name="idHakmilik" id="idHakmilik" onblur="toUppercase(this.id);"
                        onkeyup="this.value = this.value.toUpperCase();"
                        onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>
            </p>
            <p>
                <label>No Resit :</label>
                <s:text name="idResit" id="idResit" onblur="toUppercase(this.id);"/>
            </p>
            <br/>
            <p>
                <label>&nbsp;</label>
                <s:button name="searchPartial" value="Cari" class="btn" onclick="doSearch(this.name, this.form);"/>
                <s:button name="" value="Carian Semula" class="btn" onclick="carianSemula();"/>
            </p>
        </fieldset>
    </div>
    <br/>
    <c:if test="${fn:length(actionBean.senaraiPermohonan) > 0}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Senarai Perserahan
                </legend>
                <p align="center">
                    <label></label>
                    <c:set var="row_num" value="${actionBean.__pg_start}"/>
                    <display:table class="tablecloth" name="${actionBean.senaraiPermohonan}"
                                   cellpadding="0" cellspacing="0" id="line"
                                   requestURI="/daftar/cetakSemulaResit_SijilCarian"
                                   pagesize="10"                                   
                                   sort="external" size="${actionBean.__pg_total_records}" partialList="true">
                        <c:set var="row_num" value="${row_num+1}"/>
                        <display:column title="Bil">${row_num}</display:column>
                        <display:column title="ID Perserahan">
                            <a href="#" onclick="doSearch2('${line.idPermohonan}');">${line.idPermohonan}</a>
                        </display:column>
                        <display:column property="kodUrusan.kod" title="Kod Urusan"/>
                        <display:column property="kodUrusan.nama" title="Urusan"/>
                        <display:column title="Tarikh Perserahan">
                            <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="d/MM/yyyy hh:mm:ss"/>
                        </display:column>
                    </display:table>
                </p>
                <br/>                
            </fieldset>
        </div>
    </c:if>
    <c:if test="${fn:length(actionBean.senaraiPermohonanCarian) > 0}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Senarai Carian
                </legend>
                <p align="center">
                    <label></label>
                    <c:set var="row_num" value="${actionBean.__pg_start}"/>
                    <display:table class="tablecloth" name="${actionBean.senaraiPermohonanCarian}"
                                   cellpadding="0" cellspacing="0" id="line"
                                   requestURI="/daftar/cetakSemulaResit_SijilCarian"
                                   pagesize="10"
                                   sort="external" size="${actionBean.__pg_total_records}" partialList="true">
                        <c:set var="row_num" value="${row_num+1}"/>
                        <display:column title="Bil">${row_num}</display:column>
                        <display:column title="ID Perserahan">
                            <a href="#" onclick="doSearch2('${line.idCarian}');">${line.idCarian}</a>
                        </display:column>
                        <display:column property="urusan.kod" title="Kod Urusan"/>
                        <display:column property="urusan.nama" title="Urusan"/>
                        <display:column title="Tarikh Perserahan">
                            <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="d/MM/yyyy hh:mm:ss"/>
                        </display:column>
                    </display:table>
                </p>
                <br/>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${fn:length(actionBean.senaraiDokumen) > 0}">        
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Keputusan Carian Dokumen
                </legend>
                <p class="instr">
<%--                    Sila klik pada <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar" />
                    untuk semak dokumen.
                    <b>|</b>--%>
                    Sila klik pada <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png" height="30" width="30" alt="papar" />
                    untuk cetak dokumen.
                </p>
                <p>
                <div align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiDokumen}"
                                   cellpadding="0" cellspacing="0" id="line1">
                        <display:column title="Bil">${line1_rowNum}</display:column>
                        <display:column property="kodDokumen.kod" title="Kod Dokumen"/>
                        <display:column property="kodDokumen.nama" title="Nama Dokumen"/>
                        <display:column title="Tajuk">
                            ${line1.tajuk}
<%--                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" title="Papar ${line1.tajuk}"
                                 onclick="doViewReport('${line1.idDokumen}');" height="20" width="20" alt="papar" onmouseover="this.style.cursor='pointer';"/>--%>
                        </display:column>
                        <display:column title="Cetak">                            
                            <c:if test="${line1.namaFizikal != null}">
                                <%--<s:button name="cetak" value="Cetak" onclick="doPrintViaApplet('${line.idDokumen}');" class="btn"/>--%>
                                <p align="center"><img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png" title="Cetak ${line1.tajuk}"
                                                       onclick="doSaveCapaian('${line1.idDokumen}');" height="20" width="20" alt="cetak" onmouseover="this.style.cursor='pointer';"/></p>
                                </c:if>
                            </display:column>
                        </display:table>
                </div>
                </p>
                <br/>
                <p>
                    <label>Sebab cetakan semula :</label>
                    <s:textarea name="sbb_cetakan_semula" id="sbb_cetakan_semula" cols="60" rows="10" onblur="toUppercase(this.id);"/>
                </p>
                <br/>
            </fieldset>
        </div>
        <%-- <applet code="etanah.dokumen.print.DocumentPrinter" ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
            ${pageContext.request.contextPath}/commons-httpclient-2.0.2.jar,
            ${pageContext.request.contextPath}/commons-logging.jar,
            ${pageContext.request.contextPath}/swingx-1.6.jar,
            ${pageContext.request.contextPath}/log4j-1.2.12.jar,
            ${pageContext.request.contextPath}/jpedal_trial.jar,
            ${pageContext.request.contextPath}/PDFRenderer.jar"
                codebase = "${pageContext.request.contextPath}/"
                name     = "applet2"
                id       = "applet2"
                width    = "1"
                height   = "1"
                align    = "middle">
            <param name="uploadAction" value="<%=request.getContextPath()%>/PrintServlet"/>
            <param name ="method" value="pdfp">
             <%
            Cookie[] cookies2 = request.getCookies();
            StringBuffer sb2 = new StringBuffer();
            for (int i =0; i < cookies2.length; i++) {
               sb2.setLength(0);
               sb2.append(cookies2[i].getName());
               sb2.append("=");
               sb2.append(cookies2[i].getValue());
              %>
              <param name="Cookie<%= i %>" value="<%= sb2.toString() %>"><%
            }
            %>
    </applet> --%>
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
</s:form>
