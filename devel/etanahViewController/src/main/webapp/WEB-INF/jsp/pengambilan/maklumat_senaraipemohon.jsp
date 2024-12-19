<%-- 
    Document   : maklumat_senaraipemohon
    Created on : 13-May-2010, 10:13:22
    Author     : nordiyana
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
                window.open("${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?showEditPemohon&idPihak="+d, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=750");
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
            window.open("${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?pihakKepentinganPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

       <%-- function addNewPemohon(){
            window.open("${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?pemohonPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }--%>

        function copy(){
            $('#page_div').html('');
            var url = '${pageContext.request.contextPath}/common/mohon_pihak?copy';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

        function refreshPagePihakBerkepentingan(){
        var url = '${pageContext.request.contextPath}/pengambilan/pihak_berkepentingan?refreshpage';
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
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/common/mohon_pihak?delete&idPemohon='+val;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
        }


        function removeSingle(idPemohon)
        {
        if(confirm('Adakah anda pasti?')) {
        var url = '${pageContext.request.contextPath}/pengambilan/pihak_berkepentingan?deleteSingle&idPemohon='
        +idPemohon;
        $.get(url,
        function(data){
        $('#page_div').html(data);
        },'html');}
        }

        function removeChanges(val){
            var answer = confirm("adakah anda pasti untuk Hapus?");
            if (answer){
                var url = '${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?deleteChanges&idKkini='+val;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                });
            }
        }

        function tambahBaru(){
            window.open("${pageContext.request.contextPath}/pengambilan/pihak_berkepentingan?pemohonPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

        function addPemohon(){
            var len = $('.nama').length;
            for(var i=1; i<=len; i++){
                var ckd = $('#chkbox'+i).is(":checked");
                if(ckd){
                    var url = '${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?simpanPemohon&idPihak='+$('#chkbox'+i).val();
                    $.get(url,
                    function(data){
                        $('#page_div').html(data);
                    });
                }
            }
        }

        function semakSyer(f){
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?semakSyer',q,
            function(data){
                if(data != ''){
                    alert(data);
                }
            }, 'html');
        }



         function dopopup(i){

            var d = $('.x'+i).val();
            window.open("${pageContext.request.contextPath}/pengambilan/pihak_berkepentingan?showEditPemohon&idPihak="+d, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
        }

</script>
<div class="subtitle displaytag">
    <s:messages/>
    <s:errors/>
    <s:form beanclass="etanah.view.stripes.pengambilan.PihakBerkepentinganActionBean">
            <fieldset class="aras1">
                <legend>Senarai Pemohon</legend>
                <div class="content" align="center">

                            <display:table class="tablecloth" name="${actionBean.pemohonList}" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

                        <display:column title="Bil" sortable="true">${line_rowNum}
                            <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                        </display:column>
                        <display:column property="pihak.nama" title="Nama"/>
                        <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                        <display:column title="Alamat" >
                            ${line.pihak.suratAlamat1}
                            <c:if test="${line.pihak.suratAlamat2 ne null}"> , </c:if>
                            ${line.pihak.suratAlamat2}
                            <c:if test="${line.pihak.suratAlamat3 ne null}"> , </c:if>
                            ${line.pihak.suratAlamat3}
                            <c:if test="${line.pihak.suratAlamat4 ne null}"> , </c:if>
                            ${line.pihak.suratAlamat4}</display:column>
                        <display:column property="pihak.suratPoskod" title="Poskod" />
                        <display:column property="pihak.suratNegeri.nama" title="Negeri" />
                                <display:column title="Kemaskini">
                                <div align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="dopopup('${line_rowNum -1}');"/>
                                </div>
                                </display:column>
                                <display:column title="Hapus">
                                <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.pemohonList[line_rowNum-1].idPemohon}');" />
                                </div>
                                </display:column>
                            </display:table>

                                <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="tambahBaru();"/>&nbsp;

                </div>

            </fieldset>

    </s:form>
</div>
