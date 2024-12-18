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
        //$('.empty').remove();

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

    <%--function addNew(){
        window.open("${pageContext.request.contextPath}/pihak_berkepentingan?pihakKepentinganPopup&urusan=pajakan&hakmilik="+$('#idHakmilik').val(), "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=600,scrollbars=yes");
    }--%>

     function addNew() {
        var idHakmilik = $('#idHakmilik').val();
        var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?showSearchForm&idHakmilik=' + idHakmilik;
        window.open(url,  "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes");
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

    function removeMultiple() {
        if(confirm('Adakah anda pasti?')) {
            $('.remove').each(function(index){
                var a = $('#chck'+index).is(":checked");
                if(a) {
                    
                }
            });
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

    function removeMultipleMohonPihak () {
        var param = '';
        if(confirm('Adakah anda pasti?')) {
            doBlockUI();
            $('.remove2').each(function(index){
                index = index+1;
                var a = $('#rm_mp'+index).is(":checked");                
                if(a) {
                    param = param + '&idPermohonanPihak=' + $('#rm_mp'+index).val();
                }
            });
            if(param == ''){
                alert('Sila Pilih Pemohon terlebih dahulu.');
                doUnBlockUI();
                return;
            }
            var url = '${pageContext.request.contextPath}/common/mohon_pihak?deleteMultiplePihak'+param+'&pajakan=true&hakmilik=' +$('#idHakmilik').val();
            $.get(url,
            function(data){
                $('#page_div').html(data);
                doUnBlockUI();
            }, 'html');
        }
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
        window.open("${pageContext.request.contextPath}/pihak_berkepentingan?"+url+"&pajakan=true&idPihak="+d, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
    }

    function reload (id) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pihak_berkepentingan?reloadPajakanForm&idHakmilik=' + id;
        $.get(url,
            function(data){
                $('#page_div').html(data);
                doUnBlockUI();
            }, 'html');        
    }

</script>
<div class="subtitle displaytag">
    <s:errors/>
    <s:form beanclass="etanah.view.stripes.common.PihakBerkepentinganActionBean">
        <fieldset class="aras1">
            <legend>Senarai Tuan Tanah</legend>
            <p>
                <label>Hakmilik :</label>
                <s:select name="idHakmilik" style="width:300px" onchange="reload(this.value);" id="idHakmilik">
                    <s:options-collection collection="${actionBean.p.senaraiHakmilik}"
                                          label="hakmilik.idHakmilik" value="hakmilik.idHakmilik"/>
                </s:select>
            </p>

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
                <legend>Senarai 
                    <c:if test="${actionBean.p.kodUrusan.kod eq 'PNPA'}">Pemegang Amanah</c:if>
                    <c:if test="${actionBean.p.kodUrusan.kod ne 'TEN' and actionBean.p.kodUrusan.kod ne 'TENBT' and actionBean.p.kodUrusan.kod ne 'PNPA' and actionBean.p.kodUrusan.kod ne 'TENPT'}">Pemegang Pajakan</c:if>
                    <c:if test="${actionBean.p.kodUrusan.kod eq 'TEN' || actionBean.p.kodUrusan.kod eq 'TENBT'|| actionBean.p.kodUrusan.kod eq 'TENPT' }">Penerima Tenansi</c:if></legend>

                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.mohonPihakList}" cellpadding="0" cellspacing="0" id="lineMP">
                        <c:if test="${fn:length(actionBean.mohonPihakList) > 0}">
                            <display:column>
                                <input type="checkbox" id="rm_mp${lineMP_rowNum}" value="${lineMP.idPermohonanPihak}" class="remove2"/>
                            </display:column>
                        </c:if>
                        <display:column title="Bil" sortable="true">${lineMP_rowNum}<s:hidden name="a" class="a${lineMP_rowNum-1}" value="${lineMP.pihak.idPihak}"/></display:column>
                        <display:column property="pihak.nama" title="Nama"/>
                        <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                        <display:column property="jenis.nama" title="Jenis Pihak" />
                        <c:if test="${edit}">
                                <display:column title="Kemaskini">
                                    <p align="center">
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="editPenerima('${lineMP_rowNum-1}','${actionBean.p.kodUrusan.kod}');return false;" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </display:column>                                
                            </c:if>
                        <%--c:if test="${edit}">
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem${lineMP_rowNum}' onclick="remove('${lineMP.idPermohonanPihak}')">
                                </div>
                            </display:column>
                        </c:if--%>
                    </display:table>
                </div>

                <c:if test="${edit}">
                    <p>
                        <label>&nbsp;</label>
                        <%--<s:button class="btn" value="Carian Pihak" name="searchExisting" id="searchExisting" onclick="popupExisting();"/>&nbsp;--%>
                        <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addNew();"/>&nbsp;
                        <c:if test="${fn:length(actionBean.mohonPihakList) > 0}">
                                <s:button class="btn" value="Hapus" name="" onclick="removeMultipleMohonPihak();"/>&nbsp;
                        </c:if>
                    </p>
                </c:if>
            </fieldset>
        </div>
        <br>
        <%--<c:if test="${actionBean.p.kodUrusan.kod ne 'TEN' and actionBean.p.kodUrusan.kod ne 'TENBT' }">
            <c:if test="${fn:length(actionBean.othersPihakList) > 0}">
                <div id="mohon_pihak">
                    <fieldset class="aras1">
                        <legend>Senarai Pihak Berkepentingan</legend>

                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.othersPihakList}" cellpadding="0"
                                           cellspacing="0" id="otherPihak">
                                <display:column title="Bil" sortable="true">${otherPihak_rowNum}</display:column>
                                <display:column property="pihak.nama" title="Nama"/>
                                <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                            </display:table>
                        </div>
                    </fieldset>
                </div>
            </c:if>
        </c:if>--%>
    </s:form>

</div>
