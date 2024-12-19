<%-- 
    Document   : kemasukan_pihak_ten
    Created on : May 21, 2010, 10:31:58 AM
    Author     : khairil
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $('.empty').remove();

        var len2 = $(".alamat").length;

        for ( var i=0; i<len2; i++){
            var d = $('.x'+i).val();

            $('.nama'+i).bind('click',d, function(){
                window.open("${pageContext.request.contextPath}/pihak_berkepentingan?showEditPemohon&idPihak="+d, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=1000,scrollbars={yes|1}");
            });
        }

    });

    function popupExisting(){
        window.open("${pageContext.request.contextPath}/common/pihak", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }

    function addNew(){
        window.open("${pageContext.request.contextPath}/pihak_berkepentingan?pihakKepentinganPopup&urusan=pajakan", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=600,scrollbars=yes");
    }



    function remove(val){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/common/mohon_pihak?delete&idPermohonanPihak='+val+'&urusan=pajakan';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');

        }
    }

    function removePemohon(val){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pihak_berkepentingan?deletePemohon&idPemohon='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
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

    function dopopup(i){
        var d = $('.x'+i).val();
        window.open("${pageContext.request.contextPath}/pihak_berkepentingan?showEditPemohon&idPihak="+d, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
    }

</script>
<div class="subtitle displaytag">
    <s:errors/>
    <s:form beanclass="etanah.view.stripes.common.PihakBerkepentinganActionBean">
        <fieldset class="aras1">
            <legend>Senarai Tuan Tanah</legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.pihakKepentinganList}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column property="pihak.nama" title="Nama" class="nama"/>
                    <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                    <%--<display:column title="Bahagian yang dimiliki" >${line.syerPembilang}/${line.syerPenyebut}</display:column>--%>
                    <display:column title="Tarikh Pemilikan Tanah"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/></display:column>
                </display:table>
            </div>
        </fieldset>
       
        <br>
        <div id="mohon_pihak">
            <fieldset class="aras1">
                <legend>Senarai <c:if test="${actionBean.p.kodUrusan.kod eq 'PNPA'}">Pemegang Amanah</c:if><c:if test="${actionBean.p.kodUrusan.kod ne 'PNPA'}">Penerima </c:if><c:if test="${actionBean.p.kodUrusan.kod ne 'TEN' and actionBean.p.kodUrusan.kod ne 'TENBT' and actionBean.p.kodUrusan.kod ne 'PNPA'}">Pajakan</c:if><c:if test="${actionBean.p.kodUrusan.kod eq 'TEN' || actionBean.p.kodUrusan.kod eq 'TENBT' }">Tenansi</c:if></legend>

                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.mohonPihakList}" cellpadding="0" cellspacing="0" id="lineMP">
                        <display:column title="Bil" sortable="true">${lineMP_rowNum}</display:column>
                        <display:column property="pihak.nama" title="Nama"/>
                        <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                        <c:if test="${edit}">
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem${lineMP_rowNum}' onclick="remove('${lineMP.idPermohonanPihak}')">
                                </div>
                            </display:column>
                        </c:if>
                    </display:table>
                </div>

                <c:if test="${edit}">
                    <p>
                        <label>&nbsp;</label>
                        <%--<s:button class="btn" value="Carian Pihak" name="searchExisting" id="searchExisting" onclick="popupExisting();"/>&nbsp;--%>
                        <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addNew();"/>&nbsp;
                    </p>
                </c:if>
            </fieldset>
        </div>
          
        
    </s:form>

</div>