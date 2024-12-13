<%-- 
    Document   : Aduan_bicara
    Created on : 16-Apr-2011, 10:34:10
    Author     : nordiyana
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
$(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
});

function refreshPagePenerimaanBorang(){
var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_borang?refreshpage';
$.get(url,
function(data){
$('#page_div').html(data);
},'html');
}

function updateDetails(h){
   var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_borang?editDetails&rowCount='+h;
$.get(url,
function(data){
$('#page_div').html(data);
},'html');
}


    function validation() {
        var count = $("#count").val();

        <%--for(var i=1;i<=count;i++){--%>
            var tarikhBicara = $("#tarikhBicara").val();
            var jam = $("#jam").val();
            var minit = $("#minit").val();
            var ampm = $("#ampm").val();
            var lokasiBicara = $("#lokasiBicara").val();

            if(tarikhBicara == ""){
                alert('Sila pilih " Tarikh Perbicaraan " terlebih dahulu.');
                $("#tarikhBicara").focus();
                return false;
            }
            if(jam == ""){
                alert('Sila pilih " JAM " terlebih dahulu.');
                $("#jam").focus();
                return false;
            }
            if(minit == ""){
                alert('Sila pilih " MINIT " terlebih dahulu.');
                $("#minit").focus();
                return false;
            }
            if(ampm == ""){
                alert('Sila pilih " AM/PM " terlebih dahulu.');
                $("#ampm").focus();
                return false;
            }
            if(lokasiBicara == ""){
                alert('Sila pilih " Lokasi Perbicaraan " terlebih dahulu.');
                $("#lokasiBicara").focus();
                return false;
            }
        <%--}--%>
        return true;
    }
    function removeTarikh(idPerbicaraan)
{
    if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pengambilan/aduan_bicara?deleteTarikh&idPerbicaraan='
+idPerbicaraan;
            $.get(url,
            function(data){
            $('#page_div').html(data);
            <%--self.opener.refreshPageTanahRizab();--%>
            },'html');
        }
}
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.AduanBicaraActionBean">

    <s:messages/>
     <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Rundingan</legend>
            <br/>
            <table>
                <tr>
                    <td><label for="nama">Tarikh Rundingan :</label></td>
                    <td><s:text name="tarikhBicara"  class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="tarikhBicara"/></td>
                </tr>
                <tr>
                    <td><label for="nama">Waktu Rundingan :</label></td>
                    <td><s:select name="jam" style="width:56px" id="jam">
                        <s:option value="">Jam</s:option>
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
                    <s:select name="minit" style="width:56px" id="minit">
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
                    <s:select name="ampm" style="width:80px" id="ampm">
                        <s:option value="">Pilih</s:option>
                        <s:option value="AM">AM</s:option>
                        <s:option value="PM">PM</s:option>
                    </s:select></td>
                </tr>
                <tr>
                    <td><label for="nama">Lokasi Rundingan :</label></td>
                    <td><s:text name="lokasiBicara" id="lokasiBicara"/></td>
                </tr>

            </table>
                <br>

           <p align="center">
           <s:button name="simpanHakmilik" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>

            </p>
            </fieldset>
        </div>
           <div class="subtitle displaytag">
        <fieldset class="aras1" id="locate">
            <legend>
                Maklumat Rundingan
            </legend>
            <p align="center"><label></label>
                <display:table class="tablecloth" name="${actionBean.hakBicaraList}" pagesize="20" style="width:100%" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="No">${line_rowNum}</display:column>
                    <display:column property="lokasiBicara" title="Lokasi" />
                    <display:column title="Tarikh" style="vertical-align:top;">
                            <fmt:formatDate value="${line.tarikhBicara}" pattern="dd/MM/yyyy"/>
                        </display:column>
                    <%--<display:column title="Masa" style="vertical-align:top;">
                            <fmt:formatDate value="${line.tarikhBicara}" pattern="dd/MM/yyyy"/>
                            <fmt:formatDate type="time" value="${actionBean.waktuPerbicaraan}"/>

                        </display:column>--%>
                    <display:column title="Masa">
                        <s:text name="waktu[${line_rowNum - 1}]" disabled="true" id="waktu${line_rowNum - 1}" value="${actionBean.waktu}"/>
                       
                        <%--<s:hidden name="luas1" value="${line.hakmilik.luas}" id="luas${line_rowNum-1}" />
                        <s:hidden name="unitLuas" value="${line.hakmilik.kodUnitLuas.nama}" id="unitLuas${line_rowNum-1}" />
                        <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>--%>
                        <%--&nbsp;${actionBean.waktu}--%>
                    </display:column>
                    <display:column title="Hapus">
                    <div align="center">
                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeTarikh('${line.idPerbicaraan}');" />
                    </div>
                    </display:column>
                     </display:table>

            <br>

        </fieldset>
    </div>
                <br/>

</s:form>
