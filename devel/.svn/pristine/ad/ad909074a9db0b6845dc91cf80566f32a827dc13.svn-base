<%-- 
    Document   : lokasi_pencerobohan_table
    Created on : Jan 19, 2010, 12:22:44 PM
    Author     : farah.shafilla
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
    
    function tambahBaru(){
        window.open("${pageContext.request.contextPath}/maklumat_lokasi?popup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        self.opener.refreshPageCeroboh();
    }
    function removeSingle(idAduanLokasi)
    {
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/maklumat_lokasi?deleteSingle&idAduanLokasi='
                +idAduanLokasi;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
        self.opener.refreshPageCeroboh();
    }
    function refreshPageCeroboh(){
        var url = '${pageContext.request.contextPath}/maklumat_lokasi?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
    function popup(h){
        var url = '${pageContext.request.contextPath}/maklumat_lokasi?popupedit&idAduanLokasi='+h;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");


    }

    
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatLokasiActionBean">
    <div class="subtitle">
        <fieldset class="aras1" id="locate">
            <legend>
                Lokasi Pencerobohan
            </legend>
            <c:if test="${edit}">
            <div class="instr-fieldset">
                Klik butang tambah untuk masukkan maklumat lokasi pencerobohan
            </div>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiAduanLokasi}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column property="cawangan.name" title="Cawangan"></display:column>
                    <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim"></display:column>
                    <display:column property="pemilikan.nama" title="Jenis Tanah"></display:column>
                    <display:column property="noLot" title="No.Lot"></display:column>
                    <display:column property="lokasi" title="Lokasi"></display:column>
                    <display:column title="Kemaskini">
                        <div align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${line.idAduanLokasi}')"/>
                        </div>
                    </display:column>
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.senaraiAduanLokasi[line_rowNum-1].idAduanLokasi}');" />
                        </div>
                    </display:column>

                </display:table>
                <br>
                <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBaru();"/>

            </div>
            </c:if>
            <c:if test="${view}">
<div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiAduanLokasi}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column property="cawangan.name" title="Cawangan"></display:column>
                    <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim"></display:column>
                    <display:column property="pemilikan.nama" title="Jenis Tanah"></display:column>
                    <display:column property="noLot" title="No.Lot"></display:column>
                    <display:column property="lokasi" title="Lokasi"></display:column>
                </display:table>
                <br>
            </div>
            </c:if>
        </fieldset>
    </div>
</s:form>
