<%--
    Document   : kemasukan_senarai_tuan_tanah
    Created on : 15-Oct-2009, 03:41:03
    Author     : md.nurfikri
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
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
            });
        }

    <%--$('#copy').click( function(){
        $('#page_div').html('');
        var url = '${pageContext.request.contextPath}/common/mohon_pihak?copy';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    });--%>
        });

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
                    <display:column title="Bahagian yang dimiliki" >${line.syerPembilang}/${line.syerPenyebut}</display:column>
                    <display:column title="Tarikh Pemilikan Tanah"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/></display:column>
                </display:table>
            </div>            
        </fieldset>        
        <br>
        <div id="mohon_pihak">
            <fieldset class="aras1">
                <legend>Senarai <c:if test="${actionBean.p.kodUrusan.kod ne 'IS'}">Tuan Tanah Baru</c:if> <c:if test="${actionBean.p.kodUrusan.kod eq 'IS'}">Pemberi Ismen</c:if></legend>

                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.mohonPihakList}" cellpadding="0" cellspacing="0" id="lineMP">
                        <display:column title="Bil" sortable="true">${lineMP_rowNum}</display:column>
                        <display:column property="pihak.nama" title="Nama"/>
                        <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                        <c:if test="${edit}">
                        <display:column title="Bahagian yang diterima">
                            <div align="center">
                                <s:text name="syer1[${lineMP_rowNum-1}]" size="5" /> /
                                <s:text name="syer2[${lineMP_rowNum-1}]" size="5" />
                            </div>
                        </display:column>
                        </c:if>
                        <c:if test="${!edit}">
                            <display:column title="Bahagian yang diterima">${lineMP.syerPembilang}/${lineMP.syerPenyebut}</display:column>
                        </c:if>
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
                        <s:button class="btn" value="Sah Bahagian" name="semak" id="semak" onclick="semakSyer(this.form);"/>&nbsp;                       
                    </p>
                </c:if>
            </fieldset>
        </div>
    </s:form>

</div>
