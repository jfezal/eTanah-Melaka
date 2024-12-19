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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $('.empty').remove();

        var len2 = $(".alamat").length;
         
        for ( var i=0; i<len2; i++){
            var d = $('.x'+i).val();
    
            $('.nama'+i).bind('click',d, function(){
                window.open("${pageContext.request.contextPath}/pihak_berkepentingan?showEditPemohon&idPihak="+d, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=750");
            });
        }
         });

        function popupExisting(){
            window.open("${pageContext.request.contextPath}/common/pihak", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }

        function addNewPB(){
            window.open("${pageContext.request.contextPath}/pihak_berkepentingan?pihakKepentinganPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=700,scrollbars=yes");
        }

        function addNewPemohon(){
            window.open("${pageContext.request.contextPath}/pihak_berkepentingan?pemohonPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=700,scrollbars=yes");
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
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/common/mohon_pihak?delete&idPermohonanPihak='+val;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
        }
///
        function removePemohon(val){
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/strata/waran?deletePemohon&idPemohon='+val;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }       
        }

        function removeChanges(val){
            var answer = confirm("adakah anda pasti untuk Hapus?");
            if (answer){
                var url = '${pageContext.request.contextPath}/pihak_berkepentingan?deleteChanges&idKkini='+val;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                });
            }
        }
///

 function addPemohon(){
        var len = $('.nama').length;
        var param = '';

        for(var i=1; i<=len; i++){
            var ckd = $('#chkbox'+i).is(":checked");
            if(ckd){
                param = param + '&idPihak=' + $('#chkbox'+i).val();
            }
        }
        if(param == ''){
            alert('Tiada pemohon.');
            return;
        }

        var url = '${pageContext.request.contextPath}/strata/waran?simpanPemohon'+param;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                }, 'html');
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
            if(kod == "TN" || kod == "PNKP"){
                var url ="showEditNamaPemohon";
            }
            else if(kod == "TA"){
                var url = "showEditAlamatPemohon";
            }else{
                var url = "showEditPemohon";
            }
            var d = $('.x'+i).val();
            window.open("${pageContext.request.contextPath}/pihak_berkepentingan?"+url+"&idPihak="+d, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
        }

        function editPenerima(i,kod){
            if(kod == "TN" || kod == "PNKP"){
                var url ="showEditNamaPemohon";
            }
            else if(kod == "TA"){
                var url = "showEditAlamatPemohon";
            }else{
                var url = "showEditPenerima";
            }
            var d = $('.a'+i).val();
            window.open("${pageContext.request.contextPath}/pihak_berkepentingan?"+url+"&idPihak="+d, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
        }

        //fikri : automatic insert into table for syer
        function updateSyer(idpihak, id) {
            var s1 = $('#syer1' + id).val();
            var s2 = $('#syer2' + id).val();

            if(s1 == '' || s2 == ''){
                return;
            }

            if(isNaN(s1) && isNaN(s2)){
                return;
            }
            var url = '${pageContext.request.contextPath}/pihak_berkepentingan?updateSyerMohonPihak&idpihak='+idpihak
                + '&syer1=' + s1 + '&syer2=' + s2;
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });
            $.post(url,
                    function(data) {
                       $.unblockUI(); 
                    }, 'html');
        }

</script>
<div class="subtitle displaytag">
    <s:messages/>
    <s:errors/>
    <s:form beanclass="etanah.view.strata.WaranPenahananActionBean">
       
            <fieldset class="aras1">
                <legend>Senarai Pemilik</legend>
                <div class="content" align="center">
                    <c:if test="${edit}">
                        Sila Klik pada kotak dan tekan Pilih untuk memilih Pemilik
                    </c:if>                        

                    <c:choose>
                        <c:when test="${berangkai}">
                            <display:table class="tablecloth" name="${actionBean.senaraiPemohonBerangkai}" cellpadding="0" cellspacing="0" id="line"
                                           requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                                <c:if test="${edit}">
                                    <display:column>
                                        <s:checkbox name="checkbox" id="chkbox${line_rowNum}" value="${line.pihak.idPihak}"/>
                                    </display:column>
                                </c:if>
                                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                <display:column property="pihak.nama" title="Nama" class="nama"/>
                                <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                                <display:column title="Bahagian yang dimiliki" >${line.syerPembilang}/${line.syerPenyebut}</display:column>
                            </display:table>
                        </c:when>
                        <c:otherwise>
                            <display:table class="tablecloth" name="${actionBean.pihakKepentinganList}" cellpadding="0" cellspacing="0" id="line"
                                           requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                                <c:if test="${edit}">
                                    <display:column>
                                        <s:checkbox name="checkbox" id="chkbox${line_rowNum}" value="${line.pihak.idPihak}"/>
                                    </display:column>
                                </c:if>

                                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                <display:column property="pihak.nama" title="Nama" class="nama"/>
                                <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                                <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                                <display:column title="Bahagian yang dimiliki" >${line.syerPembilang}/${line.syerPenyebut}</display:column>
                                <display:column title="Tarikh Pemilikan Tanah">
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>
                                </display:column>
                                <display:column property="jenis.nama" title="Jenis Pihak"/>
                            </display:table>
                        </c:otherwise>
                    </c:choose>
                </div>
                <c:if test="${fn:length(actionBean.senaraiPemohonBerangkai) > 0 || fn:length(actionBean.pihakKepentinganList) > 0}">
                    <c:if test="${edit}">
                        <p>
                            <label>&nbsp;</label>
                            <s:button class="btn" value="Pilih" name="pilih" id="pilih" onclick="addPemohon();"/>&nbsp;
                        </p>
                    </c:if>
                </c:if>
            </fieldset>
            <br/>
     
       
            <fieldset class="aras1">

                <legend>
                    <c:set var="title" value="Pemohon"/>
                   Senarai ${title}
                </legend>


                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.pemohonList}" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

                        <display:column title="Bil" sortable="true">${line_rowNum}
                            <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                        </display:column>
                        <display:column property="pihak.nama" title="Nama"/>
                        <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                        <display:column title="Alamat" >${line.pihak.suratAlamat1}
                            <c:if test="${line.pihak.suratAlamat2 ne null}"> , </c:if>
                            ${line.pihak.suratAlamat2}
                            <c:if test="${line.pihak.suratAlamat3 ne null}"> , </c:if>
                            ${line.pihak.suratAlamat3}
                            <c:if test="${line.pihak.suratAlamat4 ne null}"> , </c:if>
                            ${line.pihak.suratAlamat4}</display:column>
                        <display:column property="pihak.suratPoskod" title="Poskod" />
                        <display:column property="pihak.suratNegeri.nama" title="Negeri" />

                        <c:if test="${edit}"><
                            <display:column title="Kemaskini">
                                <p align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                         onclick="dopopup('${line_rowNum -1}','${actionBean.p.kodUrusan.kod}');return false;" onmouseover="this.style.cursor='pointer';">
                                </p>
                              
                            </display:column>
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onclick="removePemohon('${line.idPemohon}')" onmouseover="this.style.cursor='pointer';">
                                </div>
                            </display:column>
                        </c:if>
                    </display:table>
                </div>
                   
                 <p>
                        <label>&nbsp;</label>
                        <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addNewPemohon();"/>&nbsp;
                    </p>
              
            </fieldset>
            <br/>
     
    </s:form>

</div>
