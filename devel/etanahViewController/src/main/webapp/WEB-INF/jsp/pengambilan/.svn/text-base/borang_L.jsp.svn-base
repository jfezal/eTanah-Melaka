<%--
    Document   : borang_L
    Created on : July 22, 2011, 3:26:12 PM
    Author     : massita
--%>

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

    function validation() {
        var count = $("#count").val();
        for(var i=1;i<=count;i++){
            var lokasiBicara = $("#tempohPenyerahan"+(i - 1)).val();
            if(lokasiBicara == ""){
                alert('Sila pilih "tempoh penyerahan" terlebih dahulu.');
                $("#tempohPenyerahan"+(i - 1)).focus();
                return false;
            }
        }
        return true;
    }
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.BorangLActionBean">

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
                <b>Borang L</b>
            </legend><br/>
            <p align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="6" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/borang_L" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                    <display:column title="No Lot/No PT">${line.hakmilik.lot.nama}&nbsp;${line.hakmilik.noLot}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column title="Perbicaraan Pengambilan No.">
                        <c:if test="${actionBean.hakmilikPerbicaraanList[line_rowNum - 1].idPerbicaraan ne null}">
                                 ${actionBean.hakmilikPerbicaraanList[line_rowNum - 1].idPerbicaraan}
                        </c:if>
                         <c:if test="${actionBean.hakmilikPerbicaraanList[line_rowNum - 1].idPerbicaraan eq null}">
                             <c:out value="Tiada Data"/>
                         </c:if>
                    </display:column>
                    <display:column title="No.Warta">
                        <c:if test="${actionBean.noWarta ne null}">
                                 ${actionBean.noWarta}
                        </c:if>
                         <c:if test="${actionBean.noWarta eq null}">
                             <c:out value="Tiada Data" />
                         </c:if>
                    </display:column>
                    <display:column title="Tarikh Warta">
                        <c:if test="${actionBean.tarikhWarta ne null}">
                                 ${actionBean.tarikhWarta}
                        </c:if>
                         <c:if test="${actionBean.tarikhWarta eq null}">
                             <c:out value="Tiada Data"/>
                         </c:if>
                    </display:column>
                    <display:column title="Tempoh Penyerahan">
                                <s:text size="5" name="tempohPenyerahan[${line_rowNum - 1}]" id="tempohPenyerahan{line_rowNum - 1}" onkeyup="validateNumber(this,this.value);"/> Hari
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
