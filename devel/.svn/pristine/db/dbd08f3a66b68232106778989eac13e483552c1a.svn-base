<%-- 
    Document   : maklumat_trizab
    Created on : 23-Mar-2010, 09:46:54
    Author     : nordiyana
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript">
function removeSingle(idTanahRizabPermohonan)
{
if(confirm('Adakah anda pasti?')) {
var url = '${pageContext.request.contextPath}/pengambilan/maklumat_trizab?deleteSingle&idTanahRizabPermohonan='
+idTanahRizabPermohonan;
$.get(url,
function(data){
$('#page_div').html(data);
},'html');}
}


function tambahBaru(){
window.open("${pageContext.request.contextPath}/pengambilan/maklumat_trizab?hakMilikPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

function refreshPageTanahRizab(){
var url = '${pageContext.request.contextPath}/pengambilan/maklumat_trizab?refreshpage';
$.get(url,
function(data){
$('#page_div').html(data);
},'html');
}

function popup(h){

var url = '${pageContext.request.contextPath}/pengambilan/maklumat_trizab?editTanahRizab&idTanahRizabPermohonan='+h;
window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");


}
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.maklumattrizabActionBean">
    <s:messages/>
    <div class="subtitle displaytag">

        <fieldset class="aras1" id="locate">
            <legend>
                Maklumat Warta(Tanah Rizab/GSA)
            </legend>
            <p align="center"><label></label>

                <display:table class="tablecloth" name="${actionBean.tanahRizabList}" pagesize="20" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                   <%-- <display:column property="tanahrizab.noLitho" title="No Lembaran Piawai"/>--%>
                   <%-- <display:column property="idTanahRizabPermohonan" title="Id"/>--%>--%>
                    <display:column property="noLitho" title="No Lembaran Piawai" />
                    <display:column property="rizab.nama" title="Jenis Rizab" />
                    <display:column property="noLot" title="No Lot" />
                    <display:column property="lokasi" title="Lokasi" />
                    <display:column property="noWarta" title="No. Warta"/>
                    <display:column property="tarikhWarta" title="Tarikh Warta" format="{0,date,dd/MM/yyyy}"/>
                    

                    <display:column title="Kemaskini">
                    <div align="center">
                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                    id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${actionBean.tanahRizabList[line_rowNum-1].idTanahRizabPermohonan}');"/>
                    </div>
                    </display:column>
                    <display:column title="Hapus">
                   <div align="center">
                   <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                    id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.tanahRizabList[line_rowNum-1].idTanahRizabPermohonan}');" />
                   </div>
                    </display:column>
                </display:table>
                        <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBaru();"/>&nbsp;
                <br>
                 <%--<s:button name="tambahHakmilik" id="save" value="Tambah" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>--%>
                




        </fieldset>
    </div>

</s:form>