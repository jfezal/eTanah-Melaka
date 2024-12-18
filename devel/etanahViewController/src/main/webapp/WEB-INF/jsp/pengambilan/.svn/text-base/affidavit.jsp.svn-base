<%--
    Document   : affidavit
    Created on : May 13, 2011, 9:59:55 AM
    Author     : massita
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html;charset=windows-1252" import="java.util.*" import="java.text.SimpleDateFormat"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">
$(document).ready( function(){
$(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
$(".datepicker1").datepicker({dateFormat: 'yy'});
});

    function addRow1(tableID) {
        document.getElementById('rowCount1').value = 1;
        var table = document.getElementById(tableID);

        var rowCount1 = table.rows.length;
        var row = table.insertRow(rowCount1);
        var cell2 = row.insertCell(0);
        eDIV = document.createElement("b");
        // add the text "hello world" to the div with createTextNode
        eDIV.appendChild(document.createTextNode("1." +(rowCount1+1)));
        // append your newly created DIV element to an already existing element.
        cell2.appendChild(eDIV);

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan1"+(rowCount1+1);
        element1.rows = 5;
        element1.cols = 150;
        element1.name ="kandungan1"+(rowCount1+1);
        element1.id ="kandungan1"+(rowCount1+1);
        cell1.appendChild(element1);
        document.getElementById('rowCount1').value=rowCount1 +1;
    }

    function deleteRow1(formPtg,form1)
    {
        var i = document.getElementById('rowCount1').value;
        var x= document.getElementById('dataTable1').rows[i-1].cells;
        var idKandungan = x[0].innerHTML;

        if (document.getElementById('idKandungan1'+(i)) != null) {
            var url = '${pageContext.request.contextPath}/pengambilan/affidavit?deleteSingle&idKandungan='
            +idKandungan+'&formPtg='+formPtg+'&form1='+form1;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        }

        document.getElementById('dataTable1').deleteRow(i-1);
        document.getElementById('rowCount1').value = i -1;
    }

    function validation() {
    var count1=$("#rowCount1").val();
    for(var i=1;i<=count1;i++){
        var kandungan1= $("#kandungan1"+i).val();
        if(kandungan1 == ""){
            alert('Sila pilih " 1. perakuan " terlebih dahulu.');
            $("#kandungan1"+i).focus();
            return false;
        }
    }

    var count5 = $("#count5").val();
    for(var i=1;i<=count5;i++){
        var recordCount5 = $("#count5"+i).val();
        for(var j=1;j<=recordCount5;j++){
            var syorPentadbir = $("#syorPentadbir"+i+j).val();
            if(syorPentadbir == ""){
                alert('Sila pilih " 5. SYOR PENTADBIR TANAH " terlebih dahulu.');
                $("#syorPentadbir"+i+j).focus();
                return false;
            }
        }

    }

    var count = $("#count").val();
    for(var i=1;i<=count;i++){
        var recordCount = $("#count"+i).val();
        for(var j=1;j<=recordCount;j++){
            var syor = $("#syor"+i+j).val();
            if(syor == ""){
                alert('Sila pilih " 7. SYOR PENGARAH TANAH DAN GALIAN(SYARAT-SYARAT KELULUSAN) " terlebih dahulu.');
                $("#syor"+i+j).focus();
                return false;
            }
        }

    }

    if($("#heading2").val() == ""){
            $("#heading2").val("Tiada Data");
    }

    if($("#perakuan").val() == ""){
            $("#perakuan").val("Tiada Data");
    }

    if($("#perakuan1").val() == ""){
            $("#perakuan1").val("Tiada Data");
    }

    if($("#tarikhIkrar").val() == ""){
           alert('Sila pilih " PADA " terlebih dahulu.');
                $("#tarikhIkrar").focus();
                return false;
    }
    if($("#diIkrarOleh").val() == ""){
            $("#diIkrarOleh").val("Tiada Data");
    }
    if($("#ulasanPengarah").val() == ""){
            $("#ulasanPengarah").val("Tiada Data");
    }

    if($("#samanPemulaBil").val() == ""){
            $("#samanPemulaBil").val("Tiada Data");
    }

    return true;
    }

function ajaxLink(link, update) {
$.get(link, function(data) {
$(update).html(data);
$(update).show();
});
return false;
}

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pengambilan.AffidavitActionBean" name="form2">
<div id="hakmilik_details">
    <s:messages/>
    <s:errors/>
    <fieldset class="aras1">
    <div align="center"><br /><br />
    <display:table class="tablecloth" name="${actionBean.permohonan.senaraiHakmilik}" pagesize="5" cellpadding="0" cellspacing="0"
                   requestURI="/pengambilan/affidavit" id="line">
        <display:column title="No" sortable="true">${line_rowNum}</display:column>
        <display:column property="hakmilik.idHakmilik" title="Id Hakmilik"/>
        <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
        <display:column title="Tuan Tanah" >
            <c:set value="1" var="count"/>
            <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                <table border="0">
                    <tr>
                        <td><c:out value="${count}"/>)</td>
                        <td>
                            <s:link beanclass="etanah.view.stripes.pengambilan.AffidavitActionBean"
                                    event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>
                                <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>
                                <c:out value="${senarai.pihak.nama}"/>
                            </s:link>
                        </td>
                        <td>(<c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/>)</td>
                    </tr>
                </table>
                        <c:set value="${count + 1}" var="count"/>
            </c:forEach>
        </display:column>
    </display:table>
    </div>
</fieldset>
<c:if test="${showDetails}">
    <div class="subtitle">
        <fieldset class="aras1" id="locate">
            <div class="content" align="center">
                <table border="0" width="80%">
                    <tr align="left">
                        <td align="center">
                            <b><u>DALAM MAHKAMAH TINGGI MALAYA NEGERI SEMBILAN</u></b>
                        </td>
                    </tr>
                    <tr><td> &nbsp;</td></tr>
                    <tr align="left">
                        <td align="center"><b><u>SAMAN PEMULA NO: ${actionBean.samanPemulaBil}<s:hidden name="samanPemulaBil" id="samanPemulaBil"/></u></b><hr></td>
                    </tr>
                    <tr><td> &nbsp;</td></tr>
                    <div class="content" align="center">
                        <tr align="left"><td align="center">Dalam perkara <font style="text-transform: uppercase">${actionBean.heading}<s:hidden name="heading" /></font></td></tr><br />
                        <tr align="left"><td align="center">DAN</td></tr>
                        <tr align="left"><td align="center">Dalam Perkara Akta Pengambilan Tanah No. <font style="text-transform: uppercase"><s:text name="headingObj2.kandungan" size="10" id="heading2" /></font>1960
                        <tr align="left"><td align="center">DAN</td></tr>
                        <tr align="left"><td align="center">Dalam perkara Aturan 7 Kaedah 2(1) Kaedah-kaedah Mahkamah Tinggi Tahun 1980</td></tr>
                    </div>

                    <tr align="center"><td><br /><br />ANTARA</td></tr>
                    <tr><td> &nbsp;</td></tr>

                    <tr><td><b>Saya</b>&nbsp;<font style="text-transform: capitalize"><s:textarea name="perakuan.kandungan" rows="1" cols="140" id="perakuan" /></font></td></tr>
                    <tr><td><b>&nbsp;</b>telah bersumpah dan menyebut seperti berikut:-</td></tr>

                    <tr align="center"><td><br /><br /><b><u>AFFIDAVIT</u></b></td></tr>
                    <tr><td> &nbsp;</td></tr>

                    <tr><td><b>Saya</b>&nbsp;<font style="text-transform: capitalize"><s:textarea name="perakuan1.kandungan" rows="2" cols="140" id="perakuan1" /></font></td></tr>
                    <tr><td><b> &nbsp;</b>bersumpah dan menyatakan seperti berikut:-
                    </td></tr>
                    <tr><td> &nbsp;</td></tr>
                    <tr><td>
                            <table id ="dataTable1">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKertasKandungan1}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                        <td><font style="text-transform: uppercase"><s:textarea name="kandungan1${i}" id="kandungan1${i}" rows="3" cols="140" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font></td>
                                    </tr>
                                    <s:hidden name="idKandungan1${i}" id="idKandungan1${i}" value="${line.idKandungan}" />
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                                    <s:hidden name="rowCount1" value="${i-1}" id="rowCount1"/>
                            </table>
                    <tr><td align="right"><s:button name="ikrar" value="Tambah" class="btn" onclick="addRow1('dataTable1')" />
                            <s:button name="ikrar" value="Hapus" class="btn" onclick="deleteRow1('${formPtg}','${form1}')" />

                    <tr><td><b>Pada</b>&nbsp;&nbsp;<s:text class="datepicker" name="tarikhIkrar.kandungan" id="tarikhIkrar" formatPattern="dd/MM/yyyy" style="width:110px;" /></td</tr>
                </table>
            </div>
        </fieldset>
    </div>
                    <p align="center">
                        <s:hidden name="idPihak" value="${actionBean.idPihak}"/>
                        <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
                        <s:hidden name="idKertas" value="${permohonanKertas.idKertas}"/>
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
        </c:if>
    <%--</c:if>--%>
  </div>
</s:form>