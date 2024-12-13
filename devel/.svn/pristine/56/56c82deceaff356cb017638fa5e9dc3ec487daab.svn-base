<%-- 
    Document   : penerimaanKeputusanMahkamah
    Created on : 03-April-2011, 11:55:43
    Author     : massita
    Edited by  : Rajesh
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
                var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_KeputusanMahkamah?hakmilikDetails&idHakmilik='
                +$(this).text();
            $.get(url,
            function(data){
                $('#page_div').html(data);
                },'html');
                window.open("${pageContext.request.contextPath}/pengambilan/penerimaan_KeputusanMahkamah?hakmilikDetails&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }
    });--%>

function refreshPagePenerimaanBorang(){
var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_KeputusanMahkamah?refreshpage';
$.get(url,
function(data){
$('#page_div').html(data);
},'html');
}

function updateDetails(h){
   var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_KeputusanMahkamah?editDetails&rowCount='+h;
$.get(url,
function(data){
$('#page_div').html(data);
},'html');
}
<%--var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_KeputusanMahkamah?showEditTanahRizab&idTanahRizabPermohonan='+h;
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
            var amaunDituntut = $("#amaunDituntut"+(i - 1)).val();

            if(tarikhBicara == ""){
                alert('Sila pilih " Tarikh Perbicaraan " terlebih dahulu.');
                $("#tarikhBicara"+(i - 1)).focus();
                return false;
            }
            
            if(amaunDituntut == ""){
                alert('Sila pilih " Amaun Pampasan " terlebih dahulu.');
                $("#amaunDituntut"+(i - 1)).focus();
                return false;
            }
        }
        return true;
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.PenerimaanKeputusanMahkamhActionBean">

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
                <b>HASIL KEPUTUSAN PERBICARAAN</b>
            </legend><br/>
            <p align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="6" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/penerimaan_KeputusanMahkamah" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                    <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                    <display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />

                    <display:column title="Tarikh Keputusan">
                        <s:text name="tarikhBicara[${line_rowNum - 1}]"  class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="tarikhBicara${line_rowNum - 1}"/>
                        <s:hidden name="count" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="count"/>
                    </display:column>
                    <display:column title="Amaun Pampasan"><s:text name="amaunDituntut[${line_rowNum - 1}]" id="amaunDituntut${line_rowNum - 1}" onkeyup="validateNumber(this,this.value);"/></display:column>


                   <%-- <display:column title="Kemaskini">
                    <div align="center">
                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                             id='rem_${line_rowNum}'   onmouseover="this.style.cursor='pointer';" onclick="updateDetails('${line_rowNum}')"/>
                    </div>
                    </display:column>--%>
                </display:table>
            <br/><br/><br/>

            <s:button name="simpanHakmilik" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>

            <br/>
            <br/>
            <br/>

        </fieldset>
    </div>

</s:form>