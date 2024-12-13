<%-- 
    Document   : report_text_file
    Created on : Mar 10, 2015, 3:18:24 PM
    Author     : haqqiem
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript">
    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        var daerah = document.getElementById('d').value;
        var rep = document.getElementById('namaReport').value;
        report(rep);
        if((daerah != 0)||(rep != 0))
            $('#param').show();
        else 
            $('#param').hide();
    });
    
    function report(val){
        if(val==0){
            $('#param').hide();}
        else{
            $('#param').show();
            if((val == 1)||(val == 2)){
                $('#amount').show();
                $('#katg').show();
                $('#jenis').show();
                $('#trh').show();
            }else if(val == 3){
                $('#jenis').show();
                $('#trh').show();
                $('#amount').hide();
                $('#katg').hide();
            }else {
                $('#jenis').hide();
                $('#trh').hide();
                $('#amount').hide();
                $('#katg').hide();
            }
        }
    }
    
    function filterBPM(kodDaerah){
        var rpt = document.getElementById('namaReport').value;
        var url = '${pageContext.request.contextPath}/hasil/report_txt?filterByDaerah&kodDaerah='+kodDaerah+'&reportId='+rpt;
        $.get(url,
        function(data){
            $('#daerah').html(data);
            report(rpt);
        },'html');
    }
    
    function dateValidation(value){
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var today = new Date();
        var sdate = new Date(fulldate);
        if (sdate > today){
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#trkh').val("");
        }
    }
    
    function changeFormat2() {
        var num = document.getElementById('amt').value;
        num = num.toString().replace(/\$|\,/g, '');
        if (isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100 + 0.50000000001);
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        if (cents < 10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3)) + ',' +
            num.substring(num.length - (4 * i + 3));
        $('#amt').val((((sign) ? '' : '-') + num + '.' + cents));
    }
</script>


<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.hasil.TextFileReportActionBean" id="report" name="form">
    <div id="daerah">
        <div align="center">
    <table style="width:99.2%" bgcolor="green">
        <tr><td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Laporan Text File</font>
                </div>
            </td>
        </tr>
    </table>
</div>
    <s:messages/>
    <s:errors/>
    <s:hidden name="reportNo" />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Laporan</legend>
            <p><label>Nama Laporan :</label>
                <s:select name="reportNo" onchange="report(this.value);" value="${actionBean.reportNo}" id='namaReport'>
                    <s:option label="Pilih Laporan..." value="0" />
                    <s:option label="1. Ringkasan Tunggakan Cukai Ikut Kategori (Summary)" value="1" />
                    <s:option label="2. Senarai Tunggakan Cukai Ikut Kategori" value="2" />
                    <s:option label="3. Laporan Tunggakan Sehingga.... " value="3" />
                    <s:option label="4. Laporan Induk Cukai Tanah " value="4" />
                    <%--<c:forEach items="${actionBean.listReport}" var="i" >
                        <c:set var="kodTransaksi" value="${i}" />
                        <s:option value="${i}" >${i}</s:option>
                    </c:forEach>--%>
                </s:select>
            </p><br>
        </fieldset>
    </div><br>
    <div class="subtitle" id="param">
        <fieldset class="aras1">
            <p>
                <label><em>*</em>Daerah :</label>
                <s:select name="daerah" onchange="filterBPM(this.value);" id="d">
                    <s:option label="Sila Pilih..."  value="0" />
                    <c:forEach items="${actionBean.senaraiDaerah}" var="i" >
                        <c:set var="kodDaerah" value="${i}" />
                        <s:option value="${i.kod}" >${i.kod} - ${i.nama}</s:option>
                    </c:forEach>
                </s:select>
            </p>
            <p>
                <label><em>*</em>Bandar/Pekan/Mukim :</label>
                <s:select name="kodBpm">
                    <s:option label="Sila Pilih..."  value="0" />
                    <c:forEach items="${actionBean.senaraiBPM}" var="i" >
                        <c:set var="kodDaerah" value="${i}" />
                        <s:option value="${i.kod}" >${i.bandarPekanMukim} - ${i.nama}</s:option>
                    </c:forEach>
                </s:select>
            </p>
            <p id="jenis">
                <label><em>*</em>Jenis Hakmilik :</label>
                <s:select name="jenisHakmilik">
                    <s:option label="Sila Pilih..."  value="0" />
                    <c:forEach items="${actionBean.senaraiJenisHakmilik}" var="i" >
                        <c:set var="jenisHakmilik" value="${i}" />
                        <s:option value="${i.kod}" >${i.kod} - ${i.nama}</s:option>
                    </c:forEach>
                </s:select>
            </p>
            <p id="trh">
                <label><em>*</em>Tarikh Tamat :</label>
                <s:text name="tarikh" id="trkh" size="15" onchange="dateValidation(this.value)" maxlength="10" class="datepicker"/>
            </p>
            <p id="amount">
                <label><em>*</em>Amaun Min :</label>
                <s:text name="amaun" id="amt" size="15" style="width:100px;text-align:right;" onblur="changeFormat2()" formatPattern="###,###,##0.00"/>
            </p>
            <p id="katg">
                <label><em>*</em>Kategori Pemilik :</label>
                <s:select name="bangsa">
                    <s:option label="Sila Pilih..."  value="0" />
                    <c:forEach items="${actionBean.senaraiBangsa}" var="i" >
                        <c:set var="jenisHakmilik" value="${i}" />
                        <s:option value="${i.kod}" >${i.kod} - ${i.nama}</s:option>
                    </c:forEach>
                </s:select>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:submit name="Step2" value="Jana" class="btn"/>
                <c:if test="${actionBean.flagJana}">
                    <s:submit name="Step3" value="Muat Turun" class="btn"/>
                </c:if>
            </p>
        </fieldset>
    </div>
</s:form>
</div>
