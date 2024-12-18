<%-- 
    Document   : dev_senarai_pemohon
    Created on : Jun 28, 2010, 11:28:51 AM
    Author     : nursyazwani
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
    <%--var x= $('#checkmate').val();
    alert(x);
    if(x == ${line.pihak.idPihak}){
       document.frm.checkmate.checked = true;
    }--%>
            var len2 = $(".alamat").length;

            for ( var i=0; i<len2; i++){
                var d = $('.x'+i).val();

                $('.nama'+i).bind('click',d, function(){
                    window.open("${pageContext.request.contextPath}/pembangunan/pihak_berkepentinganPPCB?showEditPemohon&idPihak="+d, "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=900,height=750");
                });
            }
            $('#page_effect').fadeIn(5000);
        });

        function addNewPemohon(){
            window.open("${pageContext.request.contextPath}/pembangunan/pihak_berkepentinganPPCB?pemohonPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
        }

        function remove(val){
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/common/mohon_pihak?delete&idPermohonanPihak='+val;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
        }

        function removePemohon(val){
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pembangunan/pihak_berkepentinganPPCB?deletePemohon&idPemohon='+val;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
        }

        function removeChanges(val){
            var answer = confirm("adakah anda pasti untuk Hapus?");
            if (answer){
                var url = '${pageContext.request.contextPath}/pembangunan/pihak_berkepentinganPPCB?deleteChanges&idKkini='+val;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                    self.opener.refreshPageUlasanJabatanTeknikal();
                });
            }
        }

        function addPemohon(){
            var len = $('.nama').length;
            for(var i=1; i<=len; i++){
                var ckd = $('#chkbox'+i).is(":checked");
                if(ckd){
                    var url = '${pageContext.request.contextPath}/pembangunan/pihak_berkepentinganPPCB?simpanPemohon&idPihak='+$('#chkbox'+i).val();
                    $.get(url,
                    function(data){
                        $('#page_div').html(data);
                    });
                }
            }
        }


        function refreshPagePemohon(){
    <%--alert("refreshPagePemohon");--%>
            var url = '${pageContext.request.contextPath}/pembangunan/pihak_berkepentinganPPCB?getSenaraiPemohon';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');

            alert("Rekod telah berjaya disimpan") ;
            $.unblockUI();
        }


        function selectCheckBox()
        {   
            var e= $('#sizelistphk').val();
    <%--//alert(e);--%>
            var cnt=0;
            var data = new Array() ;
            for(cnt=0;cnt<e;cnt++)
            {
                if(e=='1'){
                    if(document.frm.checkmate.checked) {
                        //                     alert(document.frm.checkmate[cnt].value) ;
                        data[cnt] = document.frm.checkmate.value ;
                    }

                }
                else {
                    if(document.frm.checkmate[cnt].checked) {
                        //                     alert(document.frm.checkmate[cnt].value) ;
                        data[cnt] = document.frm.checkmate[cnt].value ;
                    }
                    else{
                        data[cnt] = "T" ;
                    }
                }
            }
            //          alert(data) ;
            if(confirm("Adakah anda pasti?")) {
               
                var url = '${pageContext.request.contextPath}/pembangunan/pihak_berkepentinganPPCB?SimpanNewPemohon&item='+data ;
                $.blockUI({
                    message: $('#displayBox'),
                    css: {
                        top:  ($(window).height() - 50) /2 + 'px',
                        left: ($(window).width() - 50) /2 + 'px',
                        width: '50px'
                    }
                });
                $.get(url,
                function(data){
    <%--alert("Rekod telah berjaya disimpan") ;--%>
                                    <%--$('#page_div').html(data);--%>
                                    refreshPagePemohon();
                                    
                                },'html');
                            }
                        }
    
                        function semakSyer(f){
                            var q = $(f).formSerialize();
                            $.post('${pageContext.request.contextPath}/pembangunan/pihak_berkepentinganPPCB?semakSyer',q,
                            function(data){
                                if(data != ''){
                                    alert(data);
                                }
                            }, 'html');
                        }

                        function dopopup(i,kod,idPihak){
                            if(kod == "TN"){
                                var url ="showEditNamaPemohon";
                            }
                            else if(kod == "TA"){
                                var url = "showEditAlamatPemohon";
                            }else{
                                var url = "showEditPemohon";
                            }
    <%--var d = $('.x'+i).val();--%>
            window.open("${pageContext.request.contextPath}/pembangunan/pihak_berkepentinganPPCB?"+url+"&idPihak="+idPihak, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=1100px,height=600px");
        }

</script>
<div class="subtitle displaytag" id="page_effect" style="display:none;">
    <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
    <s:messages/>
    <s:errors/>
    <s:form beanclass="etanah.view.stripes.pembangunan.PihakBerkepentinganPPCBActionBean" name="frm">

        <fieldset class="aras1" style="display: none">
            <legend>Makluman :</legend>
            <div class="content" align="center">
                <fmt:message key="converter.number.invalidNumber"/>
            </div>
        </fieldset>
        <font color="red" size="2"><b>* Arahan : Sila Pilih Pemohon yang Menandatangani Borang Permohonan dan Klik Butang 'Simpan'</b></font><br /><br />

        <s:hidden name="${actionBean.sizelistphk}" id="sizelistphk" value="${actionBean.sizelistphk}"/>
        <display:table class="tablecloth" name="${actionBean.listPihak}" cellpadding="0" cellspacing="0" id="line"
                       requestURI="${pageContext.request.contextPath}/pihak_berkepentinganPPCB">
            <display:column  title="Pilih"> <s:checkbox name="checkmate" id="checkmate${line.pihak.idPihak}"  value="${line.pihak.idPihak}"/></display:column>
            <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}
                <%--<s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>--%>
            </display:column>
            <%--<display:column property="pihak.idPihak" title="Nama" style="vertical-align:baseline"/>getJenisPengenalan().getNama()--%>
            <display:column property="pihak.nama" title="Nama" style="vertical-align:baseline"/>getJenisPengenalan().getNama()
            <display:column property="pihak.jenisPengenalan.nama" title="Jenis Pengenalan" style="vertical-align:baseline"/>
            <display:column property="pihak.noPengenalan" title="No. KP/Syarikat" style="vertical-align:baseline"/>
            <display:column property="pihak.bangsa.nama" title="Bangsa" style="vertical-align:baseline"/>
            <display:column title="Alamat" style="vertical-align:baseline">${line.pihak.alamat1}
                <c:if test="${line.pihak.alamat2 ne null}"> , </c:if>
                ${line.pihak.alamat2}
                <c:if test="${line.pihak.alamat3 ne null}"> , </c:if>
                ${line.pihak.alamat3}
                <c:if test="${line.pihak.alamat4 ne null}"> , </c:if>
                ${line.pihak.alamat4}</display:column>
            <display:column property="pihak.poskod" title="Poskod" style="vertical-align:baseline"/>
            <display:column property="pihak.negeri.nama" title="Negeri" style="vertical-align:baseline"/>
            <c:if test="${edit}">
                <display:column title="Kemaskini" style="vertical-align:baseline">
                    <div align="center">
                        <%--<a href="#" src='${pageContext.request.contextPath}/images/edit.gif' onclick="dopopup('${line_rowNum -1}','${actionBean.p.kodUrusan.kod}');return false;">Kemaskini</a>--%>
                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                             id='rem_${line_rowNum}' onclick="dopopup('${line_rowNum -1}','${actionBean.p.kodUrusan.kod}','${line.pihak.idPihak}');return false;">
                    </div>
                </display:column>
            </c:if>
        </display:table>
        <br /><br /><br /><br /><br /><br />
        <c:if test="${fn:length(actionBean.pemohonList) > 0}">

            <font color="black" size="2"><b>Senarai Nama Pemohon Yang Telah Disimpan</b></font><br /><br />
            <display:table class="tablecloth" name="${actionBean.pemohonList}" cellpadding="0" cellspacing="0" id="line"
                           requestURI="${pageContext.request.contextPath}/pihak_berkepentinganPPCB">
                <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}
                </display:column>
                <display:column property="pihak.nama" title="Nama" style="vertical-align:baseline"/>getJenisPengenalan().getNama()
                <display:column property="pihak.jenisPengenalan.nama" title="Jenis Pengenalan" style="vertical-align:baseline"/>
                <display:column property="pihak.noPengenalan" title="No. KP/Syarikat" style="vertical-align:baseline"/>
            </display:table>
        </c:if>
        <br/><br>
        <%--<c:if test="${edit}">--%>
        <p>
            <label>&nbsp;</label>
            <%--<s:button class="btn" value="Simpan" name="new" onclick="SimpanNewPemohon();"/>&nbsp;--%>
            <s:button name="SimpanNewPemohon" value="Simpan" class="btn" onclick="javascript:selectCheckBox();"/>&nbsp;
        </p>
        <%--</c:if>--%>
    </s:form>
</div>