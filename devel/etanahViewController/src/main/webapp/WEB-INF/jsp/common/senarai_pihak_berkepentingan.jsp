<%-- 
    Document   : senarai_pihak_berkepentingan
    Created on : 15-Oct-2009, 03:41:03
    Author     : md.nurfikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    function popupExisting(){
        window.open("${pageContext.request.contextPath}/common/pihak", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }

    function addNew(){
        window.open("${pageContext.request.contextPath}/pihak_berkepentingan?pihakKepentinganPopup&tuanTanah=true", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=600");
    }

    function copy(){
        $('#page_div').html('');
        var url = '${pageContext.request.contextPath}/common/mohon_pihak?copy';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }


    function addRowMohonPihak(nama, kp ,syer){
        //TODO: to be complete
        var rowNo = $('table#lineMP tr').length;
        $('table#lineMP > tbody').append("<tr id='x"+rowNo+"' class='x'><td class='rowNo'>"+rowNo
            +"</td><td>"+nama+"</td><td>"+kp+"</td><td>"+syer+"</td><td>"+
            "<img alt='Click to Delete' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem' id='rem"+
            rowNo+"' onclick=\"remove(this.id,'line')\"></td></tr>");
    }

    function remove(val){
        var url = '${pageContext.request.contextPath}/common/mohon_pihak?delete&idPermohonanPihak='+val+'&tuanTanah=true';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function removePemohon(val){
        var url = '${pageContext.request.contextPath}/pihak_berkepentingan?deletePemohon&idPemohon='+val;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function addPemohon(){
        var len = $('.nama').length;
        for(var i=1; i<=len; i++){
            var ckd = $('#chkbox'+i).is(":checked");
            if(ckd){
                var url = '${pageContext.request.contextPath}/pihak_berkepentingan?simpanPemohon&idPihak='+$('#chkbox'+i).val();
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                });
            }
        }
    }

    function semakSyer(f){
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pihak_berkepentingan?semakSyer',q,
        function(data){
            if(data != ''){
                alert(data);
            }
        }, 'html');
    }
    function dopopup(i,kod){
        var d = $('.x'+i).val();
        if(kod == "TN"){
            var url ="showEditNamaPemohon";
        }
        if(kod == "TA"){
            var url = "showEditAlamatPemohon";
        }
        window.open("${pageContext.request.contextPath}/pihak_berkepentingan?"+url+"&idPihak="+d, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
    }
</script>
<div class="subtitle">
    <form:form beanclass="etanah.view.stripes.common.PihakBerkepentinganActionBean">
        <fieldset class="aras1">
            <legend>Senarai Tuan Tanah</legend>

            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.pihakKepentinganList}" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="Bil" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum-1}" value="${line.pihak.idPihak}"/>
                    </display:column>
                    <display:column property="pihak.nama" title="Nama"/>
                    <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                    <display:column title="Bahagian yang dimiliki" >${line.syerPembilang}/${line.syerPenyebut}</display:column>
                    <display:column title="Tarikh Pemilikan Tanah"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/></display:column>
                    <%--<display:column title=""><a href="#" onclick="dopopup('${line_rowNum -1}','${actionBean.p.kodUrusan.kod}');return false;">Kemaskini</a></display:column>--%>
                </display:table>
            </div>
        </fieldset>
    </form:form>
</div>
