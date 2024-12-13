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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
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

        function addNew(){
            window.open("${pageContext.request.contextPath}/pihak_berkepentingan?pihakKepentinganPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

        function addNewPemohon(){
            window.open("${pageContext.request.contextPath}/pihak_berkepentingan?pemohonPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
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
                var url = '${pageContext.request.contextPath}/common/mohon_pihak?delete2&idPermohonanPihak='+val;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
        }

        function removePemohon(val){
            if(confirm('Adakah anda pasti?')) {
                doBlockUI();
                var url = '${pageContext.request.contextPath}/pihak_berkepentingan?deletePemohon2&idPemohon='+val;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
                doUnBlockUI();
            }
        }

        function removeChanges(val){
            var answer = confirm("adakah anda pasti untuk hapus?");
            if (answer){
                var url = '${pageContext.request.contextPath}/pihak_berkepentingan?deleteChanges&idKkini='+val;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                });
            }
        }

        function addPemohon(){
            doBlockUI();
            var len = $('.nama').length;
            for(var i=1; i<=len; i++){
                var ckd = $('#chkbox'+i).is(":checked");
                if(ckd){
                    var url = '${pageContext.request.contextPath}/pihak_berkepentingan?simpanPemohon2&idPihak='+$('#chkbox'+i).val();
                    $.get(url,
                    function(data){                        
                        $('#page_div').html(data);
                    },'html');                    
                }
            }
            doUnBlockUI();
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
            if(kod == "TN"){
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

</script>
<div class="subtitle displaytag">
    <s:messages/>
    <s:errors/>
    <s:form beanclass="etanah.view.stripes.common.PihakBerkepentinganActionBean">     
        <fieldset class="aras1">
            <legend>Senarai Pemilik</legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.pihakKepentinganList}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column property="pihak.nama" title="Nama" class="nama"/>
                    <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                    <display:column title="Syer yang dimiliki" >${line.syerPembilang}/${line.syerPenyebut}</display:column>
                    <display:column title="Tarikh Pemilikan Tanah">
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>
                    </display:column>
                    <display:column property="jenis.nama" title="Jenis Pihak"/>
                </display:table>
            </div>
        </fieldset>
        <br>
        <fieldset class="aras1">
            <legend>Senarai Pihak Berkepentingan</legend>
            <font color="red" size="2">&nbsp;&nbsp;Sila pilih pihak yang terlibat sahaja.</font>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.pihakKepentinganList2}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                    <display:column><s:checkbox name="checkbox" id="chkbox${line_rowNum}" value="${line.pihak.idPihak}"/></display:column>
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column property="pihak.nama" title="Nama" class="nama"/>
                    <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                    <%--<display:column title="Syer yang dipegang" >${line.syerPembilang}/${line.syerPenyebut}</display:column>--%>
                    <display:column property="jenis.nama" title="Jenis Pihak"/>
                </display:table>
            </div>
            <c:if test="${edit && fn:length(actionBean.pihakKepentinganList2) > 0}">
                <p>
                    <label>&nbsp;</label>
                    <s:button class="btn" value="Pilih" name="pilih" id="pilih" onclick="addPemohon();"/>&nbsp;
                </p>
            </c:if>
        </fieldset>
        <br>
        <fieldset class="aras1">
            <legend>
                Senarai Pemohon
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
                        <display:column title="Kemaskini"><a href="#" onclick="dopopup('${line_rowNum -1}','${actionBean.p.kodUrusan.kod}');return false;">Kemaskini</a></display:column>

                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onclick="removePemohon('${line.idPemohon}')">
                            </div>
                        </display:column>
                    </c:if>
                </display:table>
            </div>       
        </fieldset>

        <br>             
        <div id="mohon_pihak">
            <fieldset class="aras1">
                <legend>Senarai Penerima Baru</legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.mohonPihakList}" cellpadding="0" cellspacing="0" id="lineMP">
                        <display:column title="Bil" sortable="true">${lineMP_rowNum}</display:column>
                        <display:column property="pihak.nama" title="Nama"/>
                        <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                        <c:if test="${actionBean.p.kodUrusan.kod ne 'PMP'}">
                        <display:column title="Syer yang diterima">
                            <div align="center">
                                <s:text name="syer1[${lineMP_rowNum-1}]" size="5" /> /
                                <s:text name="syer2[${lineMP_rowNum-1}]" size="5" />
                            </div>
                        </display:column>
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
                    <c:if test="${fn:length(actionBean.pemohonList)>0}">
                        <p>
                            <label>&nbsp;</label>
                            <%--<s:button class="btn" value="Carian Pihak" name="searchExisting" id="searchExisting" onclick="popupExisting();"/>&nbsp;--%>
                            <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addNew();"/>&nbsp;
                            <%--<s:button class="btn" value="Semak Syer" name="semak" id="semak" onclick="semakSyer(this.form);"/>&nbsp;--%>

                        </p>
                    </c:if>
                </c:if>
            </fieldset>
        </div>
    </s:form>

</div>
