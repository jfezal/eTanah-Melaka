<%--
    Document   : rundinganTawaranBayaranPampasan
    Created on : 23-Dec-2010, 11:55:43
    Author     : massita
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

    <%--$(document).ready( function() {

        var len = $(".daerah").length;
        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_borang?hakmilikDetails&idHakmilik='
                +$(this).text();
            $.get(url,
            function(data){
                $('#page_div').html(data);
                },'html');
                window.open("${pageContext.request.contextPath}/pengambilan/penerimaan_borang?hakmilikDetails&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }
    });--%>

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
<%--var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_borang?showEditTanahRizab&idTanahRizabPermohonan='+h;
window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");--%>



<%--function ajaxLink(link, update) {
$.get(link, function(data) {
$(update).html(data);
$(update).show();
});
return false;
}--%>
<%--function popupTanahRizab(){
var url = '${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?showEditTanahRizab&idTanahRizabPermohonan='+h;
window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
}--%>

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

<s:form beanclass="etanah.view.stripes.pengambilan.PenerimaanBorangActionBean">
    <s:messages/>
    <div class="subtitle">
            <s:errors field="amaun"/>
            <s:errors field="amaunt"/>
            <br />
            <fieldset class="aras1">
                <legend>Maklumat Tanah</legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" cellpadding="0" cellspacing="0"
                                   id="tbl1">
                        <display:column title="No" sortable="true">${tbl1_rowNum}</display:column>
                        <display:column title="No Hakmilik">
                            <s:link beanclass="etanah.view.stripes.pengambilan.perbicaraanKosGantiRugiNSActionBean"
                                    event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                <s:param name="idHakmilik" value="${tbl1.hakmilik.idHakmilik}"/>${tbl1.hakmilik.kodHakmilik.kod} ${tbl1.hakmilik.noHakmilik}/Id Hakmilik ${tbl1.hakmilik.idHakmilik}
                            </s:link>
                        </display:column>
                        <display:column title="Nombor Lot" style="vertical-align:baseline">
                            ${tbl1.hakmilik.noLot}
                        </display:column>
                        <display:column title="Daerah"  class="daerah" style="vertical-align:baseline">${tbl1.hakmilik.daerah.nama}</display:column>
                        <display:column  title="Bandar/Pekan/Mukim" style="vertical-align:baseline">${tbl1.hakmilik.bandarPekanMukim.nama}</display:column>
                        <display:column title="Luas yang diambil" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${tbl1.hakmilik.luas}"/>&nbsp;${tbl1.hakmilik.kodUnitLuas.nama}</display:column>
                        <display:column title="Luas sebenar" style="vertical-align:baseactionBean"><fmt:formatNumber pattern="#,##0.0000" value="${tbl1.hakmilik.luas}"/>&nbsp;${tbl1.hakmilik.kodUnitLuas.nama}</display:column>
                        <display:column title="Amaun Yang Dituntut (RM)" style="text-align:right">
                            <c:out value="${actionBean.hakmilikAmaunList[tbl1_rowNum-1]}"/>
                        </display:column>
                        <%--<c:if test="${Tptd}">--%>
                            <display:column title="Amaun Yang Diluluskan (RM)" style="text-align:right">
                                <c:out value="${actionBean.hakmilikSebenarList[tbl1_rowNum-1]}"/>
                            </display:column>
                        <%--</c:if>--%>
                    </display:table>
                </div>
                <br/>
            </fieldset>
    </div>


    <div  id="hakmilik_details">
        <fieldset class="aras1"><br/>
            <legend align="left">
                <b>Rundingan Tawaran Bayaran Pampasan</b>
            </legend><br/>
            <p align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="6" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/penerimaan_borang" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column title="No Hakmilik" />
                    <display:column title="Tuan Tanah" />
                    <display:column title="Syer yang Dimiliki" class="daerah"/>
                    <display:column title="Jenis Kerosakan beserta Amaun" style="width:35%;vertical-align:top;">
                    <table>
                        <s:checkbox id="${id}" name="${name}" class="${fieldClass}"/>Tanah&nbsp;&nbsp;<s:text name="lokasiBicara[${line_rowNum - 1}]" id="lokasiBicara${line_rowNum - 1}" value="Jenis Kerosakan beserta Amaun"/><br/>
                        <s:checkbox id="${id}" name="${name}" class="${fieldClass}"/>Projek&nbsp;&nbsp;<s:text name="lokasiBicara[${line_rowNum - 1}]" id="lokasiBicara${line_rowNum - 1}" value="Jenis Kerosakan beserta Amaun"/><br/>
                        <s:checkbox id="${id}" name="${name}" class="${fieldClass}"/>Bangunan&nbsp;&nbsp;
                        <s:checkbox id="${id}" name="${name}" class="${fieldClass}"/>stor&nbsp;&nbsp;<s:text name="lokasiBicara[${line_rowNum - 1}]" id="lokasiBicara${line_rowNum - 1}" value="Jenis Kerosakan beserta Amaun"/><br />
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:checkbox id="${id}" name="${name}" class="${fieldClass}"/>kuil&nbsp;&nbsp;&nbsp;<s:text name="lokasiBicara[${line_rowNum - 1}]" id="lokasiBicara${line_rowNum - 1}" value="Jenis Kerosakan beserta Amaun"/><br />
                       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:checkbox id="${id}" name="${name}" class="${fieldClass}"/>lain-lain&nbsp;&nbsp;<br />
                       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:text name="lokasiBicara[${line_rowNum - 1}]" id="lokasiBicara${line_rowNum - 1}" value="Jenis Kerosakan beserta Amaun"/>
                        <s:button class="btn" name="simpan" value="tambah" /><br /><br />
                        <s:checkbox id="${id}" name="${name}" class="${fieldClass}"/>Infrastruktur&nbsp;&nbsp;
                        <s:checkbox id="${id}" name="${name}" class="${fieldClass}"/>longkang&nbsp;&nbsp;<s:text name="lokasiBicara[${line_rowNum - 1}]" id="lokasiBicara${line_rowNum - 1}" value="Jenis Kerosakan beserta Amaun"/><br />
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:checkbox id="${id}" name="${name}" class="${fieldClass}"/>jalan tar&nbsp;&nbsp;&nbsp;&nbsp;<s:text name="lokasiBicara[${line_rowNum - 1}]" id="lokasiBicara${line_rowNum - 1}" value="Jenis Kerosakan beserta Amaun"/><br />
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:checkbox id="${id}" name="${name}" class="${fieldClass}"/>pagar&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:text name="lokasiBicara[${line_rowNum - 1}]" id="lokasiBicara${line_rowNum - 1}" value="Jenis Kerosakan beserta Amaun"/><br />
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:checkbox id="${id}" name="${name}" class="${fieldClass}"/>laluan paip&nbsp;&nbsp;<s:text name="lokasiBicara[${line_rowNum - 1}]" id="lokasiBicara${line_rowNum - 1}" value="Jenis Kerosakan beserta Amaun"/><br />
                       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:checkbox id="${id}" name="${name}" class="${fieldClass}"/>lain-lain&nbsp;&nbsp;<br />
                       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:text name="lokasiBicara[${line_rowNum - 1}]" id="lokasiBicara${line_rowNum - 1}" value="Jenis Kerosakan beserta Amaun"/>
                        <s:button class="btn" name="simpan" value="tambah" /><br /><br />
                        <s:checkbox id="${id}" name="${name}" class="${fieldClass}"/>lain-lain kos &nbsp;&nbsp;<s:text name="lokasiBicara[${line_rowNum - 1}]" id="lokasiBicara${line_rowNum - 1}" value="Jenis Kerosakan beserta Amaun"/>
                        <s:button class="btn" name="simpan" value="tambah" />
                    </table>
                    </display:column>
                    <display:footer>
                            <tr>
                                <td colspan="4"><div align="right"><b>Jumlah Keseluruhan(RM):</b></div></td>
                                <td><div align="left">
                                <s:text name="jumCaraBayar" style="text-align:right" formatPattern="#,##0.00" /></div></td>
                            </tr>
                    </display:footer>
                    <display:column title="Kemaskini">
                    <div align="center">
                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                             id='rem_${line_rowNum}'   onmouseover="this.style.cursor='pointer';" onclick="updateDetails('${line_rowNum}')"/>
                    </div>
                    </display:column>
                </display:table>
            <br/><br/><br/>

            <s:button name="simpanHakmilik" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>

            <br/>
            <br/>
            <br/>

        </fieldset>
    </div>
</s:form>
