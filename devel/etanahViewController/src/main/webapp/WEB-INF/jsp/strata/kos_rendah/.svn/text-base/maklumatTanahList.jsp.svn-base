<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:40em;
    }
</style>
<script type="text/javascript">
    function p(v,w){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        $.get("${pageContext.request.contextPath}/strata/PKKR?paparPemilik&idHakmilikPihakBerkepentingan="+v+"&idHakmilik="+w,
        function(data){
            $("#perincianPihak").show();
            $("#perincianPihak").html(data);
            $.unblockUI();
        });
    }
</script>
<s:form beanclass="etanah.view.strata.PermohonanStrataPKKRActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tanah
            </legend>
            <br>
          
                <display:table   class="tablecloth" name="${actionBean.listHakmilikP}" cellpadding="0" cellspacing="0" id="line" >
                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}<s:hidden id="hidden3" name="hidden3" value="${line_rowNum-1}"/></display:column>
                    <display:column title="No Hakmilik"><a href="#" title="Sila klik untuk papar detail" onclick="p('61200304','${line.hakmilik.idHakmilik}');return false;">${line.hakmilik.idHakmilik}</a></display:column>
                    <display:column title="No Lot"> ${line.hakmilik.lot.nama} ${line.hakmilik.noLot}</display:column>
                    <display:column property="hakmilik.tarikhDaftar" title="Tarikh Daftar" />
                    <display:column property="hakmilik.kodHakmilik.nama" title="Jenis Hakmilik" />
                    <display:column property="hakmilik.kategoriTanah.nama" title="Kategori Tanah" />
                    <display:column title="Luas" >${line.hakmilik.luas} ${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column property="hakmilik.kodHakmilik.nama" title="Tarikh Daftar" />

            </display:table>
           <br>
        </fieldset>
        
    </div>
<div id="perincianPihak">
    <p></p>
</div>
</s:form>