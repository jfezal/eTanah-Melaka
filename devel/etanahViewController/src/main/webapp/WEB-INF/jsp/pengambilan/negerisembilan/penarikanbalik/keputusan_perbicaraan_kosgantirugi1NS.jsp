<%--
    Document   : keputusan_perbicaraan_kosgantirugi1
    Created on : Apr 27, 2010, 3:32:06 PM
    Author     : massita
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN" "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloths.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<script type="text/javascript">
    $(document).ready(function() {
        $('#senaraiTuntutanKos0').val('T');
        $('#amaun0').val((${actionBean.jumCaraBayar}).toFixed(2));
        $('#amaunt0').val((${actionBean.jumCaraBayar1}).toFixed(2));

        $('#tuanTanahAmaunTotal').val((${actionBean.tuanTanahAmaunTotal}).toFixed(2));

        $('#jumCaraBayar').val((${actionBean.jumCaraBayar}).toFixed(2));
        $('#jumCaraBayar1').val((${actionBean.jumCaraBayar1}).toFixed(2));
        var bil = parseInt(${actionBean.bil});
        var amnt = parseInt(${actionBean.jumCaraBayar});
        var amnt1 = parseInt(${actionBean.jumCaraBayar1});
        if(amnt <= 0){
            $('#next').attr("disabled", "true");
            $('#tble').attr("disabled", "true");
        }
        if(amnt1 <= 0){
            $('#next').attr("disabled", "true");
            $('#tble').attr("disabled", "true");
        }
        for (var i = 1; i < bil; i++){
            $('#amaunt'+i).attr("disabled", "true");
            $('#amaunt'+i).val("0.00");
            $('#amaun'+i).attr("disabled", "true");
            $('#amaun'+i).val("0");
            $("#field"+i).hide();
        }

        var len = $(".daerah").length;
        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                alert($(this).text());
                window.open("${pageContext.request.contextPath}/pengambilan/gantiRugiNS?hakmilikDetails&idHakmilik="+$(this).text(),"eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }
    });

</script>
<script type="text/javascript">
    function addNew(idPermohonanPihak){
        window.open("${pageContext.request.contextPath}/pengambilan/gantiRugiNS?popup&idPermohonanPihak="+idPermohonanPihak,"eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=400");
    }

    function addNew1(){
        window.open("${pageContext.request.contextPath}/pengambilan/gantiRugiNS?popupTptd","eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=400");
    }


    function removeSingle(idKos)
    {
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pengambilan/gantiRugiNS?deleteSingle&idKos='
                +idKos;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
    }

    function updateSingle(idKos)
    {
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pengambilan/gantiRugiNS?simpanSingle&idKos='
                +idKos;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
    }

    function editTuntutanKos(h,i) {
        var url = '${pageContext.request.contextPath}/pengambilan/gantiRugiNS?editTuntutanKos&idKos='+h+'&idPermohonanPihak='+i;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");

    }

    function popup(h){
        var url = '${pageContext.request.contextPath}/pengambilan/gantiRugiNS?popupedit&idKos='+h;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function popup1(h){
        var url = '${pageContext.request.contextPath}/pengambilan/gantiRugiNS?popupedit1&idKos='+h;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }



    function updateTotal (inputTxt,row){
        var total = 0;
        var a = document.getElementById('amaun' + row)
        if ((isNaN(a.value))||((a.value) =="")){
            alert("Nombor tidak sah");
            inputTxt.value = RemoveNonNumeric(a);
            $('#jumCaraBayar').val("0.00");
            return;
        }
        total += parseFloat(a.value);

        var t = document.getElementById('jumCaraBayar');
        t.value = total.toFixed(2);
        inputTxt.value = parseFloat(inputTxt.value).toFixed(2);
        updtTot();
        var yy = row - 1;
        if(yy>=0){
            var t = document.getElementById('jumCaraBayar');
            var bal = parseInt(${actionBean.jumlahCaj}) + parseInt(t.value);
            if(bal > 0)
                $('#amaun' + (row+1)).val(bal);
        }else{
            var t = document.getElementById('jumCaraBayar');
            var bal = parseInt(${actionBean.jumlahCaj}) + parseInt(a.value);
            if(bal > 0)
                $('#amaun' + (row+1)).val(bal);
        }
    }

    function updtTot(){
        var total = 0;
        for (var i=0; i<total.length(); i++){
            var a = document.getElementById('amaun' + i)
            total += parseFloat(a.value);
        }
        var t = document.getElementById('jumCaraBayar');
        t.value = total.toFixed(2);
    }

    function updateTotal1 (inputTxt,row){
        var total = 0;

        var a = document.getElementById('amaunt' + row)
        if ((isNaN(a.value))||((a.value) =="")){
            alert("Nombor tidak sah");
            inputTxt.value = RemoveNonNumeric(a);
            $('#jumCaraBayar1').val(a);
            return;
        }
        total += parseFloat(a.value);

        var t = document.getElementById('jumCaraBayar1');
        t.value = total.toFixed(2);
        inputTxt.value = parseFloat(inputTxt.value).toFixed(2);
        updtTot1();
        var yy = row - 1;
        if(yy>=0){
            var t = document.getElementById('jumCaraBayar1');
            var bal = parseInt(${actionBean.jumlahCaj}) + parseInt(t.value);
            if(bal > 0)
                $('#amaunt' + (row+1)).val(bal);
        }else{
            var t = document.getElementById('jumCaraBayar1');
            var bal = parseInt(${actionBean.jumlahCaj}) + parseInt(a.value);
            if(bal > 0)
                $('#amaunt' + (row+1)).val(bal);
        }
    }

    function updtTot1(){
        var total = 0;
        for (var i=0; i<total.length(); i++){
            var a = document.getElementById('amaunt' + i)
            total += parseFloat(a.value);
        }
        var t = document.getElementById('jumCaraBayar1');
        t.value = total.toFixed(2);
    }

    function ajaxLink(link, update) {
        $.get(link, function(data) {
            $(update).html(data);
            $(update).show();
        });
        return false;
    }

    function refreshPageganti(){
        alert("---refreshPageganti--");
        var url = '${pageContext.request.contextPath}/pengambilan/gantiRugiNS?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }


</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pengambilan.perbicaraanKosGantiRugiNSActionBean" name="form2">
    <s:errors/>
    <s:messages/>
    <div  id="hakmilik_details">
        <div class="subtitle">
            <s:errors field="amaun"/>
            <s:errors field="amaunt"/>
            <br />
            <fieldset class="aras1">
                <legend>Maklumat Tanah</legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiHakmilikPermohonan}" cellpadding="0" cellspacing="0"
                                   id="tbl1">
                        <display:column title="No" sortable="true">${tbl1_rowNum}</display:column>
                        <display:column title="No Hakmilik">
                            <s:link beanclass="etanah.view.stripes.pengambilan.perbicaraanKosGantiRugiNSActionBean"
                                    event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                <s:param name="idHakmilik" value="${tbl1.hakmilik.idHakmilik}"/>${tbl1.hakmilik.kodHakmilik.kod} ${tbl1.hakmilik.noHakmilik}
                            </s:link>
                        </display:column>
                        <%--<display:column title="Nombor Lot" style="vertical-align:baseline">
                            ${tbl1.hakmilik.noLot}
                        </display:column>--%>
                        <display:column title="No Lot/No PT" >${tbl1.hakmilik.lot.nama}${tbl1.hakmilik.noLot}</display:column>
                        <display:column title="Daerah"  class="daerah" style="vertical-align:baseline">${tbl1.hakmilik.daerah.nama}</display:column>
                        <display:column  title="Bandar/Pekan/Mukim" style="vertical-align:baseline">${tbl1.hakmilik.bandarPekanMukim.nama}</display:column>
                        <display:column title="Luas yang diambil" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${tbl1.luasTerlibat}"/>&nbsp;${tbl1.hakmilik.kodUnitLuas.nama}</display:column>
                        <display:column title="Luas sebenar" style="vertical-align:baseactionBean"><fmt:formatNumber pattern="#,##0.0000" value="${tbl1.hakmilik.luas}"/>&nbsp;${tbl1.hakmilik.kodUnitLuas.nama}</display:column>
                        <display:column title="Amaun Yang Dituntut (RM)" style="text-align:right">
                            <fmt:formatNumber pattern="#,##0.00" value="${actionBean.hakmilikAmaunList[tbl1_rowNum-1]}"/>
                        </display:column>
                        <%--<c:if test="${Tptd}">--%>
                        <display:column title="Amaun Yang Diluluskan (RM)" style="text-align:right" >
                            <fmt:formatNumber pattern="#,##0.00" value="${actionBean.hakmilikSebenarList[tbl1_rowNum-1]}"/>
                        </display:column>
                        <%--</c:if>--%>
                        <display:footer>
                            <tr>
                                <td colspan="7"><div align="right"><b>Jumlah Perlu Dibayar(RM):</b></div></td>
                                <td><div align="right">
                                        <c:set var="A" value="0"/>
                                        <c:forEach items="${actionBean.hakmilikAmaunList}" var="amt">
                                            <c:set value="${A + amt}" var="A"/>
                                        </c:forEach>

                                        <s:text name="jum" value="${A}" formatPattern="#,##0.00" style="text-align:right" readonly="true"/></div></td>

                                <td><div align="right">
                                        <c:set var="B" value="0"/>
                                        <c:forEach items="${actionBean.hakmilikSebenarList}" var="amtSeb">
                                            <c:set value="${B + amtSeb}" var="B"/>
                                        </c:forEach>

                                        <s:text name="amaunSeb" value="${B}" formatPattern="#,##0.00" style="text-align:right" readonly="true"/>
                                    </div></td>

                            </tr>
                        </display:footer>
                    </display:table>
                </div>
                <br/>
            </fieldset>
        </div>
        <br/><br/>
        <c:if test="${showTuanTanah}">
            <div>
                <fieldset class="aras1" id="locate">
                    <br/>
                    <legend>Maklumat Tuan Tanah</legend>
                    <p align="center">
                        <display:table class="tablecloth" name="${actionBean.permohonanPihak1List}" cellpadding="0" cellspacing="0"
                                       requestURI="/pengambilan/gantiRugiNS" id="line"><br />
                            <display:column title="No" sortable="true" style="vertical-align:center"><br />${line_rowNum}</display:column>
                         <%--   <display:column title="No Hakmilik" style="vertical-align:center"><br />
                                ${line.hakmilik.idHakmilik} - ${line.hakmilik.noLot}
                            </display:column>--%>
                            <display:column title="Tuan Tanah">
                                <%--<c:forEach items="${actionBean.hakmilik.senaraiPihakBerkepentingan}" var="senarai">--%>
                                    <br>
                                    <s:link beanclass="etanah.view.stripes.pengambilan.perbicaraanKosGantiRugiNSActionBean"
                                            event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                        <s:param name="idPihak" value="${line.pihak.idPihak}"/>${line.pihak.nama}
                                    </s:link>
                                    &nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <%--</c:forEach>--%>
                            </display:column>
                            <display:column title="Syer yang dimiliki" style="text-align:center">
                                ${line.syerPembilang}/${line.syerPenyebut}
                                <%--<c:forEach items="${actionBean.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                    <br />
                                    <c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/>
                                    &nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </c:forEach>--%>
                            </display:column>
                            <display:column title="Amaun Yang Dituntut (RM)" style="text-align:right">
                                <c:forEach items="${actionBean.permohonanTuntutanKosList}" var="senarai">
                                    <br />
                                    <c:if test="${line.idPermohonanPihak == senarai.pihak.idPermohonanPihak}">
                                        <fmt:formatNumber pattern="#,##0.00" value="${senarai.amaunTuntutan}"/>
                                        &nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </c:if>
                                </c:forEach>
                            </display:column>
                            <%--<c:if test="${Tptd}">--%>
                            <display:column title="Amaun Yang Diluluskan (RM)" style="text-align:right">
                                <c:forEach items="${actionBean.permohonanTuntutanKosList}" var="senarai">
                                    <br />
                                    <c:if test="${line.idPermohonanPihak == senarai.pihak.idPermohonanPihak}">
                                        <fmt:formatNumber pattern="#,##0.00" value="${senarai.amaunSebenar}"/>
                                        &nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </c:if>
                                </c:forEach>
                            </display:column>
                            <%--</c:if>--%>
                            <display:footer>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td><div align="left"><b>Jumlah Perlu Dibayar(RM):</b></div></td>
                                <td><div align="right">
                                        <s:text name="jumCaraBayar1" value="jumCaraBayar1" formatPattern="#,##0.00" style="text-align:right" readonly="true"/></div></td>
                                        <c:if test="${Tptd}">
                                    <td><div align="right">
                                            <s:text name="amaunSebenarTuanTotal" value="amaunSebenarTuanTotal" formatPattern="#,##0.00" style="text-align:right" readonly="true"/></div></td>
                                        </c:if>
                            </tr>
                        </display:footer>
                    </display:table>
                    </p>
                </fieldset>
            </div>
        </c:if>
        <br/><br/>
        <c:if test="${showGantirugi}">
            <%--<c:if test="${actionBean.permohonanPihak ne null}">--%>
            <div>
                <fieldset class="aras1">
                    <br/>
                    <legend>Maklumat Tuntutan Kos Gantirugi</legend>
                    <p align="center">
                        ${actionBean.permohonanPihak1List[0].idPermohonanPihak}
                        <%--<display:table class="tablecloth" name="${actionBean.hakmilik}" cellpadding="0" cellspacing="0"--%>
                        <display:table class="tablecloth" name="${actionBean.permohonanTuntutanKosList}" cellpadding="0" cellspacing="0"
                                       requestURI="/pengambilan/gantiRugiNS" id="line"><br />
                            <display:column title="No" sortable="true"><br />${line_rowNum}</display:column>
                           <%-- <display:column title="No Hakmilik" style="vertical-align:center"><br />
                                <%--${line.kodHakmilik.kod} ${line.noHakmilik}
                            </display:column> --%>
                            <display:column title="Tuan Tanah">
                                <br>
                                ${actionBean.permohonanPihak1List[0].pihak.nama}
                            </display:column>
                            <display:column title="Syer yang dimiliki" style="text-align:center"><br />
                                ${actionBean.permohonanPihak1List[0].syerPembilang}/${actionBean.permohonanPihak1List[0].syerPenyebut}
                            </display:column>
                            <display:column title="Jenis Kerosakan" style="text-align:center">
                                <c:forEach items="${actionBean.permohonanTuntutanKosList}" var="senarai">
                                    <br/>
                                    <c:out value="${senarai.item}"/>
                                    <br/>
                                </c:forEach>
                            </display:column>
                            <display:column title="Amaun Yang Dituntut (RM)" style="text-align:right">
                                <c:forEach items="${actionBean.permohonanTuntutanKosList}" var="senarai">
                                    <br/>
                                    <fmt:formatNumber pattern="#,##0.00" value="${senarai.amaunTuntutan}"/>
                                    <br/>
                                </c:forEach>
                            </display:column>

                            <display:column title="Amaun Yang Diluluskan (RM)" style="text-align:right">
                                <c:forEach items="${actionBean.permohonanTuntutanKosList}" var="senarai">
                                    <br/>
                                    <fmt:formatNumber pattern="#,##0.00" value="${senarai.amaunSebenar}"/>
                                    <br/>
                                </c:forEach>
                            </display:column>
                            <c:if test="${Tptd}">
                                <display:column title="Kemaskini">
                                    <c:forEach items="${actionBean.permohonanTuntutanKosList}" var="senarai">
                                    <div align="center">
                                        <br/>
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editTuntutanKos('${senarai.idKos}','${senarai.pihak.idPermohonanPihak}');"/>
                                    </div>
                                </c:forEach>
                            </display:column>
                        </c:if>
                        <display:column title="Hapus">
                            <div align="center">
                                <c:forEach items="${actionBean.permohonanTuntutanKosList}" var="senarai">
                                    <br/>
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${senarai.idKos}');" />
                                    <br/>
                                </c:forEach>
                            </div>
                        </display:column>
                        <display:footer>
                            <tr>
                                <td colspan="5"><div align="right"><b>Jumlah Perlu Dibayar(RM):</b></div></td>
                                <td><div align="right">
                                        <s:text name="jumCaraBayar" style="text-align:right" readonly="true" formatPattern="#,##0.00" /></div></td>
                                        <c:if test="${Tptd}">
                                    <td><div align="right">
                                            <s:text name="amaunSebenarTotal" style="text-align:right" readonly="true" formatPattern="#,##0.00" />
                                        </div></td>
                                    </c:if>
                            </tr>
                        </display:footer>
                    </display:table>
                    </p>

                    <br />
                    <s:hidden name="idPihak" value="${actionBean.pihak.idPihak}"/>
                    <div align="center">
                        <s:button name="new" value="Tambah" id="new" class="btn" onclick="addNew(${actionBean.permohonanPihak1List[0].idPermohonanPihak});" />
                    </div>
                </fieldset>
            </div>
            <%--</c:if>--%>
        </c:if>
    </div>
</s:form>

