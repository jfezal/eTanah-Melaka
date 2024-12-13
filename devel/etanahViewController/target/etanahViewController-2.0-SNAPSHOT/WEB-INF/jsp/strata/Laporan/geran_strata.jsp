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
        var sbb = $('#sbb_cetakan_semula').val();
        if(sbb == '') {
            alert('Sila masukan Sebab');
            return;
        }
        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+"&reportName="+nama+"&report_p_id_hakmilik="+idhakmilik, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    }

    function search(frm){
        var idHakmilikInduk = $('#idHakmilikInduk').val();
        var idHakmilikStrata = $('#idHakmilikStrata').val();

        var url = '${pageContext.request.contextPath}/strata/Geranstrata?cari&idHakmilikInduk='+ idHakmilikInduk + '&idHakmilikStrata='+idHakmilikStrata;
        frm.action = url;
        frm.submit();
    }

    function nonNumber(elmnt, inputTxt) {
        var a = document.getElementById('bilHakmilik')

        if (isNaN(a.value)) {
            alert("Nombor tidak sah.Sila masukkan Semula");
            $("#bilHakmilik").focus();
            elmnt.value = RemoveNonNumeric(inputTxt);
            return;
        }
    }
    function validate() {
        var bil = document.getElementById('bilHakmilik');
        if (bil.value <= 0) {
            alert("Bilangan Hakmilik mestilah tidak kurang daripada 0");
            return false;
        }
    }


    function selectAll1(a){
        var len = $('.cetakSemua1').length;
        for (var i=0; i<len; i++) {
            var c = document.getElementById("cetaksemua1" + i);
            c.checked = a.checked;
        }
    }

    function printSelectedDocuments1 () {
        var sbb = $('#sbb_cetakan_semula').val();
        if(sbb == '') {
            alert('Sila masukan Sebab');
            return;
        }

        var documentsList = '';
        var len = $('.cetakSemua1').length;
        for (var i=0; i<len; i++) {
            var c = document.getElementById("cetaksemua1" + i);
            var x = document.getElementById("idhakmilik1" + i);
            if (c.checked) {
                documentsList = documentsList +',' + c.value;
                var url = '${pageContext.request.contextPath}/strata/Geranstrata?cetakSemula&sbb_cetakan_semula=' + sbb + '&id_dokumen=' + c.value + '&idHakmilik=' + x.value;
                $.ajax({
                    type:"GET",
                    url : url,
                    success : function(data) {
                        <%--if(data == '1'){
                            doPrintViaApplet(v);
                        }
                        else{--%>
                         if(data == '0'){
                            alert("Sila Masukkan Sebab Yang Berlainan");
                            return;
                         }
                        <%--}--%>
                    }
                });
            }
        }
        if (documentsList == '') {
            alert('Sila Pilih Dokumen untuk dicetak.');
            return;
        } else {
            doPrintViaApplet(documentsList) ;
        }
    }

    function selectAll2(a){
        var len = $('.cetakSemua2').length;
        for (var i=0; i<len; i++) {
            var c = document.getElementById("cetaksemua2" + i);
            c.checked = a.checked;
        }
    }

    function printSelectedDocuments2 () {
        var sbb = $('#sbb_cetakan_semula').val();
        if(sbb == '') {
            alert('Sila masukan Sebab');
            return;
        }

        var documentsList = '';
        var len = $('.cetakSemua2').length;
        for (var i=0; i<len; i++) {
            var c = document.getElementById("cetaksemua2" + i);
            var x = document.getElementById("idhakmilik2" + i);
            if (c.checked) {
                documentsList = documentsList +',' + c.value;
                var url = '${pageContext.request.contextPath}/strata/Geranstrata?cetakSemula&sbb_cetakan_semula=' + sbb + '&id_dokumen=' + c.value + '&idHakmilik=' + x.value;
                $.ajax({
                    type:"GET",
                    url : url,
                    success : function(data) {
                        <%--if(data == '1'){
                            doPrintViaApplet(v);
                        }
                        else{--%>
                         if(data == '0'){
                            alert("Sila Masukkan Sebab Yang Berlainan");
                            return;
                         }
                        <%--}--%>
                    }
                });
            }
        }
        if (documentsList == '') {
            alert('Sila Pilih Dokumen untuk dicetak.');
            return;
        } else {
            doPrintViaApplet(documentsList) ;
        }
    }

    function selectAll3(a){
        var len = $('.cetakSemua3').length;
        for (var i=0; i<len; i++) {
            var c = document.getElementById("cetaksemua3" + i);
            c.checked = a.checked;
        }
    }

    function printSelectedDocuments3 () {
        var sbb = $('#sbb_cetakan_semula').val();
        if(sbb == '') {
            alert('Sila masukan Sebab');
            return;
        }

        var documentsList = '';
        var len = $('.cetakSemua3').length;
        for (var i=0; i<len; i++) {
            var c = document.getElementById("cetaksemua3" + i);
            var x = document.getElementById("idhakmilik3" + i);
            if (c.checked) {
                documentsList = documentsList +',' + c.value;
                var url = '${pageContext.request.contextPath}/strata/Geranstrata?cetakSemula&sbb_cetakan_semula=' + sbb + '&id_dokumen=' + c.value + '&idHakmilik=' + x.value;
                $.ajax({
                    type:"GET",
                    url : url,
                    success : function(data) {
                        <%--if(data == '1'){
                            doPrintViaApplet(v);
                        }
                        else{--%>
                         if(data == '0'){
                            alert("Sila Masukkan Sebab Yang Berlainan");
                            return;
                         }
                        <%--}--%>
                    }
                });
            }
        }
        if (documentsList == '') {
            alert('Sila Pilih Dokumen untuk dicetak.');
            return;
        } else {
            doPrintViaApplet(documentsList) ;
        }
    }
    function selectAll4(a){
        var len = $('.cetakSemua4').length;
        for (var i=0; i<len; i++) {
            var c = document.getElementById("cetaksemua4" + i);
            c.checked = a.checked;
        }
    }

    function printSelectedDocuments4 () {
        var sbb = $('#sbb_cetakan_semula').val();
        if(sbb == '') {
            alert('Sila masukan Sebab');
            return;
        }

        var documentsList = '';
        var len = $('.cetakSemua4').length;
        for (var i=0; i<len; i++) {
            var c = document.getElementById("cetaksemua4" + i);
            var x = document.getElementById("idhakmilik4" + i);
            if (c.checked) {
                documentsList = documentsList +',' + c.value;
                var url = '${pageContext.request.contextPath}/strata/Geranstrata?cetakSemula&sbb_cetakan_semula=' + sbb + '&id_dokumen=' + c.value + '&idHakmilik=' + x.value;
                $.ajax({
                    type:"GET",
                    url : url,
                    success : function(data) {
                        <%--if(data == '1'){
                            doPrintViaApplet(v);
                        }
                        else{--%>
                         if(data == '0'){
                            alert("Sila Masukkan Sebab Yang Berlainan");
                            return;
                         }
                        <%--}--%>
                    }
                });
            }
        }
        if (documentsList == '') {
            alert('Sila Pilih Dokumen untuk dicetak.');
            return;
        } else {
            doPrintViaApplet(documentsList) ;
        }
    }
    function selectAll4k(a){
        var len = $('.cetakSemua4k').length;
        for (var i=0; i<len; i++) {
            var c = document.getElementById("cetaksemua4k" + i);
            c.checked = a.checked;
        }
    }

    function printSelectedDocuments4k () {
        var sbb = $('#sbb_cetakan_semula').val();
        if(sbb == '') {
            alert('Sila masukan Sebab');
            return;
        }

        var documentsList = '';
        var len = $('.cetakSemua4k').length;
        for (var i=0; i<len; i++) {
            var c = document.getElementById("cetaksemua4k" + i);
            var x = document.getElementById("idhakmilik4k" + i);
            if (c.checked) {
                documentsList = documentsList +',' + c.value;
                var url = '${pageContext.request.contextPath}/strata/Geranstrata?cetakSemula&sbb_cetakan_semula=' + sbb + '&id_dokumen=' + c.value + '&idHakmilik=' + x.value;
                $.ajax({
                    type:"GET",
                    url : url,
                    success : function(data) {
                         if(data == '0'){
                            alert("Sila Masukkan Sebab Yang Berlainan");
                            return;
                         }
                    }
                });
            }
        }
        if (documentsList == '') {
            alert('Sila Pilih Dokumen untuk dicetak.');
            return;
        } else {
            doPrintViaApplet(documentsList) ;
        }
    }
    function selectAll3k(a){
        var len = $('.cetakSemua3k').length;
        for (var i=0; i<len; i++) {
            var c = document.getElementById("cetaksemua3k" + i);
            c.checked = a.checked;
        }
    }

    function printSelectedDocuments3k () {
        var sbb = $('#sbb_cetakan_semula').val();
        if(sbb == '') {
            alert('Sila masukan Sebab');
            return;
        }

        var documentsList = '';
        var len = $('.cetakSemua3k').length;
        for (var i=0; i<len; i++) {
            var c = document.getElementById("cetaksemua3k" + i);
            var x = document.getElementById("idhakmilik3k" + i);
            if (c.checked) {
                documentsList = documentsList +',' + c.value;
                var url = '${pageContext.request.contextPath}/strata/Geranstrata?cetakSemula&sbb_cetakan_semula=' + sbb + '&id_dokumen=' + c.value + '&idHakmilik=' + x.value;
                $.ajax({
                    type:"GET",
                    url : url,
                    success : function(data) {
                         if(data == '0'){
                            alert("Sila Masukkan Sebab Yang Berlainan");
                            return;
                         }
                    }
                });
            }
        }
        if (documentsList == '') {
            alert('Sila Pilih Dokumen untuk dicetak.');
            return;
        } else {
            doPrintViaApplet(documentsList) ;
        }
    }
    function selectAll5(a){
        var len = $('.cetakSemua5').length;
        for (var i=0; i<len; i++) {
            var c = document.getElementById("cetaksemua5" + i);
            c.checked = a.checked;
        }
    }

    function printSelectedDocuments5 () {
        var sbb = $('#sbb_cetakan_semula').val();
        if(sbb == '') {
            alert('Sila masukan Sebab');
            return;
        }

        var documentsList = '';
        var len = $('.cetakSemua5').length;
        for (var i=0; i<len; i++) {
            var c = document.getElementById("cetaksemua5" + i);
            var x = document.getElementById("idhakmilik5" + i);
            if (c.checked) {
                documentsList = documentsList +',' + c.value;
                var url = '${pageContext.request.contextPath}/strata/Geranstrata?cetakSemula&sbb_cetakan_semula=' + sbb + '&id_dokumen=' + c.value + '&idHakmilik=' + x.value;
                $.ajax({
                    type:"GET",
                    url : url,
                    success : function(data) {
                        <%--if(data == '1'){
                            doPrintViaApplet(v);
                        }
                        else{--%>
                         if(data == '0'){
                            alert("Sila Masukkan Sebab Yang Berlainan");
                            return;
                         }
                        <%--}--%>
                    }
                });
            }
        }
        if (documentsList == '') {
            alert('Sila Pilih Dokumen untuk dicetak.');
            return;
        } else {
            doPrintViaApplet(documentsList) ;
        }
    }

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function doSaveCapaian(v,b){
        var sbb = $('#sbb_cetakan_semula').val();
        var idHakmilik = b;
        if(sbb == '') {
            alert('Sila masukan Sebab');
            return;
        }
        var url = '${pageContext.request.contextPath}/strata/Geranstrata?cetakSemula&sbb_cetakan_semula=' + sbb + '&id_dokumen=' + v + '&idHakmilik=' + idHakmilik;
        $.ajax({
            type: "GET",
            url: url,
            success: function(data) {
                if(data == '1'){
                    doPrintViaApplet(v);
                }
                else{
                    alert("Sila Masukkan Sebab Yang Berlainan.");
                }
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
                <legend>ID Hakmilik</legend>
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
                    </table> atau
                </div>
                <br>
                <%--<table border=0 class="tablecloth">
                    <tr>
                        <th align=right>
                            ID Hakmilik Strata:
                        </th>
                        <td align=left>

                                <s:text name="idHakmilikStrata" id="idHakmilikStrata"  size="37"
                                        onkeyup="this.value = this.value.toUpperCase();"  onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikStrata');"/>&nbsp;

                            </td>
                        </tr>
                    </table>--%>
            </fieldset>
            <fieldset class="aras1">
                <legend>ID Hakmilik Strata</legend>
                <p>
                    <label for="bilHakmilik">Bil. Hakmilik:</label>
                    <s:text name="bilHakmilik" id="bilHakmilik" size="4" onblur="javascript:nonNumber(this, this.value);"/> atau kurang
                    <s:submit name="showForm" value="Kemaskini" class="btn" onclick="return validate()"/>
                </p>
                <div align="center">
                    <table border=0 class="tablecloth"><tr>
                            <c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >
                                <th align=right>
                                    ID Hakmilik Strata ${i}:
                                </th>
                                <td align=left>

                                    <s:text name="idHakmilikStrata${i - 1}" id="idHakmilikStrata${i - 1}"  size="37"
                                            onkeyup="this.value = this.value.toUpperCase();"  onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikStrata');"/>&nbsp;

                                </td>
                                <c:if test="${i % 3 == 0}" >
                                </tr>
                            </c:if>
                        </c:forEach>
                    </table>
                </div>
            </fieldset>
            <br>
            <div align="center">
                <p>
                    <s:button name="" value="Isi Semula" class="btn" onclick="clearText('cetak_hakmilik');"/>
                    <s:button name="cari" value="Cari" class="btn" id="cari" onclick="search(this.form);"/>

                </p>

            </div>
        </div>


        <br/>

        <c:choose>
            <c:when test="${fn:length(actionBean.senaraiTkrganti) == 0 && (actionBean.idHakmilikStrata ne null || actionBean.idHakmilikInduk ne null)}">
                <fieldset class="aras1">
                    <font style="color:red"><B>Id hakmilik ini dijana melalui sistem eTanah. Tidak boleh menggunakan utiliti ini untuk mencetak Borang Strata</B></font>
                </fieldset>
            </c:when> 
            <c:otherwise>

                <c:if test="${fn:length(actionBean.senaraiTkrganti) > 0}">
                    <p>
                        <label>Sebab Cetakan Semula :</label>
                        <s:textarea name="sbb_cetakan_semula" id="sbb_cetakan_semula" cols="60" rows="10" onblur="toUppercase(this.id);"/>
                    </p>
                    <br>
                    <br>
                    <legend>
                        Senarai Borang Strata
                    </legend>
                    <p style="color:red">
                        *Sila klik pada (<img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk memaparkan borang<br/>
                    </p>
                    <br>
                </c:if>
            </c:otherwise>
        </c:choose >
            
        <fieldset class="aras1">
            
            <c:if test="${fn:length(actionBean.senaraiTkrganti2k3k) >0}">
            <c:if test="${fn:length(actionBean.senaraiDokumen2K3K) >0}">
                <c:set var="rowNum" value="0"/>
                <display:table style="width:90%" class="tablecloth" name="${actionBean.senaraiDokumen2K3K}"  pagesize="20" cellpadding="0" cellspacing="0" id="induk">
                    <display:column title="Tajuk">
                        ${induk.tajuk}
                    </display:column>
                    <display:column title="Tarikh Tukarganti">
                        ${actionBean.senaraiTkrganti2k3k[0].tarikhTukarganti2k}
                    </display:column>
                    <display:column title="Tukarganti Oleh">
                        ${actionBean.senaraiTkrganti2k3k[0].infoAudit.dimasukOleh.nama}
                    </display:column>
                    <display:column title="Tarikh Dokumen Dijana">
                        ${induk.infoAudit.tarikhMasuk}
                    </display:column>
                    <display:column title="Dokumen Dijana Oleh">
                        ${induk.infoAudit.dimasukOleh.nama}
                    </display:column>
                    <display:column title="Paparan">
                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" title="Papar ${induk.tajuk}"
                             onclick="doViewReport('${induk.idDokumen}');" height="20" width="20" alt="papar" onmouseover="this.style.cursor='pointer';"/>
                    </display:column>
                    <display:column title="Sejarah Paparan">
                        <c:set var="count" value="0"/>
                        <c:forEach items="${induk.senaraiCapaian}" var="item">
                            <c:if test="${item.aktiviti.kod eq 'PD'}" >
                                <c:set var="count" value="${count+1}"/>
                            </c:if>
                        </c:forEach>
                        <a href="#" onclick="sejarahPaparan('${induk.idDokumen}');return false;">Papar :<c:out value="${count}"/> rekod</a>
                    </display:column>
                    <display:column title="Sejarah Cetakan">
                        <c:set var="count" value="0"/>
                        <c:forEach items="${induk.senaraiCapaian}" var="item">
                            <c:if test="${item.aktiviti.kod eq 'CD'}" >
                                <c:set var="count" value="${count+1}"/>
                            </c:if>
                        </c:forEach>
                        <a href="#" onclick="sejarahCetakan('${induk.idDokumen}');return false;">Cetak :<c:out value="${count}"/> rekod </a>
                    </display:column>
                    <%--<display:column title="Cetak">
                        <c:if test="${induk.namaFizikal != null}">
                            <s:button name="cetak" value="Cetak" onclick="doPrintViaApplet('${line.idDokumen}');" class="btn"/>
                            <p align="center"><img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png" title="Cetak ${induk.tajuk}"
                                                   onclick="doSaveCapaian('${induk.idDokumen}','${induk.hakmilik}');" height="20" width="20" alt="cetak" onmouseover="this.style.cursor='pointer';"/></p>
                            </c:if>
                        </display:column>--%>
                    <display:column title="<input type='checkbox' id='semua1' name='semua1' onclick='javascript:selectAll1(this);' /> Cetak">
                        <p align="center">
                            <input type="checkbox" name="cetaksemua1" id="cetaksemua1${rowNum}" value="${induk.idDokumen}" class="cetakSemua1"/>
                            <s:hidden name="idhakmilik1" id="idhakmilik1${rowNum}" value="${induk.hakmilik}"/>
                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                 onclick="doSaveCapaian('${induk.idDokumen}','${induk.hakmilik}');" height="30" width="30" alt="cetak"
                                 onmouseover="this.style.cursor='pointer';" title="Cetak Dokumen ${induk.tajuk}"/>
                        </p>
                        <c:set var="rowNum" value="${rowNum +1}"/>
                    </display:column>
                </display:table>
                <center><s:button name="cetakSemua" value="Cetak Semua B2K & B3K" class="longbtn" onclick="printSelectedDocuments1();"/></center>
            </c:if>
            </c:if>

            <c:if test="${fn:length(actionBean.senaraiDokumenProv) >0}">
            <c:if test="${fn:length(actionBean.senaraiTkrgantiProv) >0}">
                <br><br>
                Bangunan Sementara &nbsp;&nbsp;
                <c:set var="rowNum" value="0"/>
                <display:table style="width:90%" class="tablecloth" name="${actionBean.senaraiDokumenProv}" requestURI="/strata/Geranstrata" pagesize="20" cellpadding="0" cellspacing="0" id="induk">
                    <c:forEach items="${actionBean.senaraiTkrgantiProv}" var="line">
                        <c:if test = "${induk.hakmilik eq line.hakmilikInduk.idHakmilik}">
                            <display:column title="Tajuk">
                                ${induk.tajuk}
                            </display:column>
                            <display:column title="Tarikh Tukarganti">
                                ${line.tarikhTukarganti4k}
                            </display:column>
                            <display:column title="Tukarganti Oleh">
                                ${line.infoAudit.dimasukOleh.nama}
                            </display:column>
                            <display:column title="Tarikh Dokumen Dijana">
                                ${induk.infoAudit.tarikhMasuk}
                            </display:column>
                            <display:column title="Dokumen Dijana Oleh">
                                ${induk.infoAudit.dimasukOleh.nama}
                            </display:column>
                            <display:column title="Paparan">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" title="Papar ${induk.tajuk}"
                                     onclick="doViewReport('${induk.idDokumen}');" height="20" width="20" alt="papar" onmouseover="this.style.cursor='pointer';"/>
                            </display:column>
                            <display:column title="Sejarah Paparan">
                                <c:set var="count" value="0"/>
                                <c:forEach items="${induk.senaraiCapaian}" var="item">
                                    <c:if test="${item.aktiviti.kod eq 'PD'}" >
                                        <c:set var="count" value="${count+1}"/>
                                    </c:if>
                                </c:forEach>
                                <a href="#" onclick="sejarahPaparan('${induk.idDokumen}');return false;">Papar :<c:out value="${count}"/> rekod</a>
                            </display:column>
                            <display:column title="Sejarah Cetakan">
                                <c:set var="count" value="0"/>
                                <c:forEach items="${induk.senaraiCapaian}" var="item">
                                    <c:if test="${item.aktiviti.kod eq 'CD'}" >
                                        <c:set var="count" value="${count+1}"/>
                                    </c:if>
                                </c:forEach>
                                <a href="#" onclick="sejarahCetakan('${induk.idDokumen}');return false;">Cetak :<c:out value="${count}"/> rekod </a>
                            </display:column>
                            <%--<display:column title="Cetak">
                                <c:if test="${induk.namaFizikal != null}">
                                    <s:button name="cetak" value="Cetak" onclick="doPrintViaApplet('${line.idDokumen}');" class="btn"/>
                                    <p align="center"><img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png" title="Cetak ${induk.tajuk}"
                                                           onclick="doSaveCapaian('${induk.idDokumen}','${induk.hakmilik}');" height="20" width="20" alt="cetak" onmouseover="this.style.cursor='pointer';"/></p>
                                    </c:if>
                                </display:column>--%>
                            <display:column title="<input type='checkbox' id='semua2' name='semua2' onclick='javascript:selectAll2(this);' /> Cetak">
                                <p align="center">
                                    <input type="checkbox" name="cetaksemua2" id="cetaksemua2${rowNum}" value="${induk.idDokumen}" class="cetakSemua2"/>
                                    <s:hidden name="idhakmilik2" id="idhakmilik2${rowNum}" value="${induk.hakmilik}"/>
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                         onclick="doSaveCapaian('${induk.idDokumen}','${induk.hakmilik}');" height="30" width="30" alt="cetak"
                                         onmouseover="this.style.cursor='pointer';" title="Cetak Dokumen ${induk.tajuk}"/>
                                </p>
                                <c:set var="rowNum" value="${rowNum +1}"/>
                            </display:column>
                        </c:if>
                    </c:forEach>
                </display:table>
                <center><s:button name="cetakSemua" value="Cetak Semua Bangunan Sementara" class="longbtn" onclick="printSelectedDocuments2();"/></center>
            </c:if>
            </c:if>
            <br>

            <c:if test="${fn:length(actionBean.senaraiTkrganti) >0}">
            <c:if test="${fn:length(actionBean.senaraiDokumen) >0}">
                <c:set var="rowNum" value="0"/>
                <%--<display:table style="width:90%" class="tablecloth" name="${actionBean.senaraiDokumen}" requestURI="/strata/Geranstrata" pagesize="20" cellpadding="0" cellspacing="0" id="line1">--%>
                <display:table style="width:90%" class="tablecloth" name="${actionBean.senaraiDokumen}" cellpadding="0" cellspacing="0" id="line1">
                    <c:forEach items="${actionBean.senaraiTkrganti}" var="line">
                        <c:if test = "${line1.hakmilik eq line.hakmilikStrata}">
                            <display:column title="Tajuk">
                                ${line1.tajuk}
                            </display:column>
                            <display:column title="Tarikh Tukarganti">
                                ${line.tarikhTukarganti4k}
                            </display:column>
                            <display:column title="Tukarganti Oleh">
                                ${line.infoAudit.dimasukOleh.nama}
                            </display:column>
                            <display:column title="Tarikh Dokumen Dijana">
                                ${line1.infoAudit.tarikhMasuk}
                            </display:column>
                            <display:column title="Dokumen Dijana Oleh">
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
                            <display:column title="<input type='checkbox' id='semua3' name='semua3' onclick='javascript:selectAll3(this);' /> Cetak">
                                <p align="center">
                                    <input type="checkbox" name="cetaksemua3" id="cetaksemua3${rowNum}" value="${line1.idDokumen}" class="cetakSemua3"/>
                                    <s:hidden name="idhakmilik3" id="idhakmilik3${rowNum}" value="${line1.hakmilik}"/>
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                         onclick="doSaveCapaian('${line1.idDokumen}','${line1.hakmilik}');" height="30" width="30" alt="cetak"
                                         onmouseover="this.style.cursor='pointer';" title="Cetak Dokumen ${line1.tajuk}"/>
                                </p>
                                <c:set var="rowNum" value="${rowNum +1}"/>
                            </display:column>
                        </c:if>
                    </c:forEach>
                </display:table>
                <center><s:button name="cetakSemua" value="Cetak Semua B4K DHDK" class="longbtn" onclick="printSelectedDocuments3();"/></center>
            </c:if>
            </c:if>
            <c:if test="${fn:length(actionBean.senaraiTkrganti) >0}">
            <c:if test="${fn:length(actionBean.senaraiDokumenKK) >0}">
                <c:set var="rowNum" value="0"/>
                <%--<display:table style="width:90%" class="tablecloth" name="${actionBean.senaraiDokumen}" requestURI="/strata/Geranstrata" pagesize="20" cellpadding="0" cellspacing="0" id="line1">--%>
                <display:table style="width:90%" class="tablecloth" name="${actionBean.senaraiDokumenKK}" cellpadding="0" cellspacing="0" id="line1">
                    <c:forEach items="${actionBean.senaraiTkrganti}" var="line">
                        <c:if test = "${line1.hakmilik eq line.hakmilikStrata}">
                            <display:column title="Tajuk">
                                ${line1.tajuk}
                            </display:column>
                            <display:column title="Tarikh Tukarganti">
                                ${line.tarikhTukarganti4k}
                            </display:column>
                            <display:column title="Tukarganti Oleh">
                                ${line.infoAudit.dimasukOleh.nama}
                            </display:column>
                            <display:column title="Tarikh Dokumen Dijana">
                                ${line1.infoAudit.tarikhMasuk}
                            </display:column>
                            <display:column title="Dokumen Dijana Oleh">
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
                            <display:column title="<input type='checkbox' id='semua3k' name='semua3k' onclick='javascript:selectAll3k(this);' /> Cetak">
                                <p align="center">
                                    <input type="checkbox" name="cetaksemua3k" id="cetaksemua3k${rowNum}" value="${line1.idDokumen}" class="cetakSemua3k"/>
                                    <s:hidden name="idhakmilik3k" id="idhakmilik3k${rowNum}" value="${line1.hakmilik}"/>
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                         onclick="doSaveCapaian('${line1.idDokumen}','${line1.hakmilik}');" height="30" width="30" alt="cetak"
                                         onmouseover="this.style.cursor='pointer';" title="Cetak Dokumen ${line1.tajuk}"/>
                                </p>
                                <c:set var="rowNum" value="${rowNum +1}"/>
                            </display:column>
                        </c:if>
                    </c:forEach>
                </display:table>
                <center><s:button name="cetakSemua" value="Cetak Semua B4K DHKK" class="longbtn" onclick="printSelectedDocuments3k();"/></center>
            </c:if>
            </c:if>
            <c:if test="${fn:length(actionBean.senaraiTkrganti) >0}">
            <c:if test="${fn:length(actionBean.senaraiDokumenBSK) >0}">
                <c:set var="rowNum" value="0"/>
                <%--<display:table style="width:90%" class="tablecloth" name="${actionBean.senaraiDokumen}" requestURI="/strata/Geranstrata" pagesize="20" cellpadding="0" cellspacing="0" id="line1">--%>
                <display:table style="width:90%" class="tablecloth" name="${actionBean.senaraiDokumenBSK}" cellpadding="0" cellspacing="0" id="line1">
                    <c:forEach items="${actionBean.senaraiTkrganti}" var="line">
                        <c:if test = "${line1.hakmilik eq line.hakmilikStrata}">
                            <display:column title="Tajuk">
                                ${line1.tajuk}
                            </display:column>
                            <display:column title="Tarikh Tukarganti">
                                ${line.tarikhTukarganti4k}
                            </display:column>
                            <display:column title="Tukarganti Oleh">
                                ${line.infoAudit.dimasukOleh.nama}
                            </display:column>
                            <display:column title="Tarikh Dokumen Dijana">
                                ${line1.infoAudit.tarikhMasuk}
                            </display:column>
                            <display:column title="Dokumen Dijana Oleh">
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
                            <display:column title="<input type='checkbox' id='semua4' name='semua4' onclick='javascript:selectAll4(this);' /> Cetak">
                                <p align="center">
                                    <input type="checkbox" name="cetaksemua4" id="cetaksemua4${rowNum}" value="${line1.idDokumen}" class="cetakSemua4"/>
                                    <s:hidden name="idhakmilik4" id="idhakmilik4${rowNum}" value="${line1.hakmilik}"/>
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                         onclick="doSaveCapaian('${line1.idDokumen}','${line1.hakmilik}');" height="30" width="30" alt="cetak"
                                         onmouseover="this.style.cursor='pointer';" title="Cetak Dokumen ${line1.tajuk}"/>
                                </p>
                                <c:set var="rowNum" value="${rowNum +1}"/>
                            </display:column>
                        </c:if>
                    </c:forEach>
                </display:table>
                <center><s:button name="cetakSemua" value="Cetak Semua BSK DHDK" class="longbtn" onclick="printSelectedDocuments4();"/></center>
            </c:if>
            </c:if>
            <c:if test="${fn:length(actionBean.senaraiTkrganti) >0}">
            <c:if test="${fn:length(actionBean.senaraiDokumenBSKK) >0}">
                <c:set var="rowNum" value="0"/>
                <%--<display:table style="width:90%" class="tablecloth" name="${actionBean.senaraiDokumen}" requestURI="/strata/Geranstrata" pagesize="20" cellpadding="0" cellspacing="0" id="line1">--%>
                <display:table style="width:90%" class="tablecloth" name="${actionBean.senaraiDokumenBSKK}" cellpadding="0" cellspacing="0" id="line1">
                    <c:forEach items="${actionBean.senaraiTkrganti}" var="line">
                        <c:if test = "${line1.hakmilik eq line.hakmilikStrata}">
                            <display:column title="Tajuk">
                                ${line1.tajuk}
                            </display:column>
                            <display:column title="Tarikh Tukarganti">
                                ${line.tarikhTukarganti4k}
                            </display:column>
                            <display:column title="Tukarganti Oleh">
                                ${line.infoAudit.dimasukOleh.nama}
                            </display:column>
                            <display:column title="Tarikh Dokumen Dijana">
                                ${line1.infoAudit.tarikhMasuk}
                            </display:column>
                            <display:column title="Dokumen Dijana Oleh">
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
                            <display:column title="<input type='checkbox' id='semua4k' name='semua4k' onclick='javascript:selectAll4k(this);' /> Cetak">
                                <p align="center">
                                    <input type="checkbox" name="cetaksemua4k" id="cetaksemua4k${rowNum}" value="${line1.idDokumen}" class="cetakSemua4k"/>
                                    <s:hidden name="idhakmilik4k" id="idhakmilik4k${rowNum}" value="${line1.hakmilik}"/>
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                         onclick="doSaveCapaian('${line1.idDokumen}','${line1.hakmilik}');" height="30" width="30" alt="cetak"
                                         onmouseover="this.style.cursor='pointer';" title="Cetak Dokumen ${line1.tajuk}"/>
                                </p>
                                <c:set var="rowNum" value="${rowNum +1}"/>
                            </display:column>
                        </c:if>
                    </c:forEach>
                </display:table>
                <center><s:button name="cetakSemua" value="Cetak Semua BSK DHDK" class="longbtn" onclick="printSelectedDocuments4k();"/></center>
            </c:if>
            </c:if>
            <c:if test="${fn:length(actionBean.senaraiTkrganti) >0}">
            <c:if test="${fn:length(actionBean.senaraiDokumenPSK) >0}">
                <c:set var="rowNum" value="0"/>
                <%--<display:table style="width:90%" class="tablecloth" name="${actionBean.senaraiDokumen}" requestURI="/strata/Geranstrata" pagesize="20" cellpadding="0" cellspacing="0" id="line1">--%>
                <display:table style="width:90%" class="tablecloth" name="${actionBean.senaraiDokumenPSK}" cellpadding="0" cellspacing="0" id="line1">
                    <c:forEach items="${actionBean.senaraiTkrganti}" var="line">
                        <c:if test = "${line1.hakmilik eq line.hakmilikStrata}">
                            <display:column title="Tajuk">
                                ${line1.tajuk}
                            </display:column>
                            <display:column title="Tarikh Tukarganti">
                                ${line.tarikhTukarganti4k}
                            </display:column>
                            <display:column title="Tukarganti Oleh">
                                ${line.infoAudit.dimasukOleh.nama}
                            </display:column>
                            <display:column title="Tarikh Dokumen Dijana">
                                ${line1.infoAudit.tarikhMasuk}
                            </display:column>
                            <display:column title="Dokumen Dijana Oleh">
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
                            <display:column title="<input type='checkbox' id='semua5' name='semua5' onclick='javascript:selectAll5(this);' /> Cetak">
                                <p align="center">
                                    <input type="checkbox" name="cetaksemua5" id="cetaksemua5${rowNum}" value="${line1.idDokumen}" class="cetakSemua5"/>
                                    <s:hidden name="idhakmilik5" id="idhakmilik5${rowNum}" value="${line1.hakmilik}"/>
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                         onclick="doSaveCapaian('${line1.idDokumen}','${line1.hakmilik}');" height="30" width="30" alt="cetak"
                                         onmouseover="this.style.cursor='pointer';" title="Cetak Dokumen ${line1.tajuk}"/>
                                </p>
                                <c:set var="rowNum" value="${rowNum +1}"/>
                            </display:column>
                        </c:if>
                    </c:forEach>
                </display:table>
                <center><s:button name="cetakSemua" value="Cetak Semua Pelan" class="longbtn" onclick="printSelectedDocuments5();"/></center>
            </c:if>
            </c:if>


            <br>
        </fieldset>

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
    </s:form>
</div>
