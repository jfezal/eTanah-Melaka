<%-- 
    Document   : borang2A_NotisMengenaiSiasatan
    Created on : 13-Sept-2011, 11:55:43
    Author     : shazwan (based on Pengambilan)
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">
$(document).ready( function(){
$(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
});

function refreshPagePenerimaanBorang(){
var url = '${pageContext.request.contextPath}/pelupusan/borang2A_NotisMengenaiSiasatan?refreshpage';
$.get(url,
function(data){
$('#page_div').html(data);
},'html');
}

function updateDetails(h){
   var url = '${pageContext.request.contextPath}/pelupusan/borang2A_NotisMengenaiSiasatan?editDetails&rowCount='+h;
$.get(url,
function(data){
$('#page_div').html(data);
},'html');
}
    function validation() {
        var count = $("#count").val();

        for(var i=1;i<=count;i++){
            var tarikhBicara = $("#tarikhBicara"+(i - 1)).val();
            var jam = $("#jam"+(i - 1)).val();
            var minit = $("#minit"+(i - 1)).val();
            var ampm = $("#ampm"+(i - 1)).val();
            var lokasiBicara = $("#lokasiBicara"+(i - 1)).val();

            if(tarikhBicara == ""){
                alert('Sila pilih " Tarikh Perbicaraan " terlebih dahulu.');
                $("#tarikhBicara"+(i - 1)).focus();
                return false;
            }
            if(jam == ""){
                alert('Sila pilih " JAM " terlebih dahulu.');
                $("#jam"+(i - 1)).focus();
                return false;
            }
            if(minit == ""){
                alert('Sila pilih " MINIT " terlebih dahulu.');
                $("#minit"+(i - 1)).focus();
                return false;
            }
            if(ampm == ""){
                alert('Sila pilih " AM/PM " terlebih dahulu.');
                $("#ampm"+(i - 1)).focus();
                return false;
            }
            if(lokasiBicara == ""){
                alert('Sila pilih " Lokasi Perbicaraan " terlebih dahulu.');
                $("#lokasiBicara"+(i - 1)).focus();
                return false;
            }
        }
        return true;
    }
</script>

<s:form beanclass="etanah.view.stripes.pelupusan.Borang2A_NotisMengenaiSiasatanActionBean">
    <s:messages/>
<div>
    <fieldset class="aras1">
        <legend align="center">
                <b>Sila masukkan maklumat berkaitan diruang yang disediakan.</b>
            </legend>
    </fieldset>
</div>
    <div  id="hakmilik_details">
        <fieldset class="aras1"><br/>
            <legend align="center">
                <b>Borang 2A</b><br />
                <b>(Seksyen 27 Kanun Tanah Negara)</b><br />
                <b>NOTIS MENGENAI SIASATAN</b>
            </legend><br/>
            <p align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="6" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/borang2A_NotisMengenaiSiasatan" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                    <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}&nbsp;${line.hakmilik.noLot}</display:column>
                    <display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />

                    <display:column title="Tarikh Perbicaraan">
                        <s:text name="tarikhBicara[${line_rowNum - 1}]"  class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="tarikhBicara${line_rowNum - 1}"/>
                        <s:hidden name="count" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="count"/>
                    </display:column>
                    <display:column title="Waktu Perbicaraan" style="width:200px">
                       <s:select name="jam[${line_rowNum - 1}]" style="width:50px" id="jam${line_rowNum - 1}">
                        <s:option value="">Jam</s:option>
                        <s:option value="00">00</s:option>
                        <s:option value="01">01</s:option>
                        <s:option value="02">02</s:option>
                        <s:option value="03">03</s:option>
                        <s:option value="04">04</s:option>
                        <s:option value="05">05</s:option>
                        <s:option value="06">06</s:option>
                        <s:option value="07">07</s:option>
                        <s:option value="08">08</s:option>
                        <s:option value="09">09</s:option>
                        <s:option value="10">10</s:option>
                        <s:option value="11">11</s:option>
                        <s:option value="12">12</s:option>
                    </s:select>
                    <s:select name="minit[${line_rowNum - 1}]" style="width:60px" id="minit${line_rowNum - 1}">
                        <s:option value="">Minit</s:option>
                        <s:option value="00">00</s:option>
                        <s:option value="05">05</s:option>
                        <s:option value="10">10</s:option>
                        <s:option value="15">15</s:option>
                        <s:option value="20">20</s:option>
                        <s:option value="25">25</s:option>
                        <s:option value="30">30</s:option>
                        <s:option value="35">35</s:option>
                        <s:option value="40">40</s:option>
                        <s:option value="45">45</s:option>
                        <s:option value="50">50</s:option>
                        <s:option value="55">55</s:option>
                    </s:select>
                    <s:select name="ampm[${line_rowNum - 1}]" style="width:60px" id="ampm${line_rowNum - 1}">
                        <s:option value="">Pilih</s:option>
                        <s:option value="AM">AM</s:option>
                        <s:option value="PM">PM</s:option>
                    </s:select>
                   </display:column>
                    <display:column title="Lokasi Perbicaraan"><s:text name="lokasiBicara[${line_rowNum - 1}]" id="lokasiBicara${line_rowNum - 1}" value="lokasiBicara" size='25'/></display:column>
                </display:table>
            <br/><br/><br/>

             <c:if test="${actionBean.stageId eq '14TtpMasa' || actionBean.stageId eq '12TtpTrkh' || actionBean.stageId eq '13SedNotis2A' || actionBean.stageId eq '15TtpknTrkh'}">
            <s:button name="simpanHakmilik" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
            </c:if>
            <br/>
            <br/>
            <br/>
        </fieldset>
    </div>
</s:form>
