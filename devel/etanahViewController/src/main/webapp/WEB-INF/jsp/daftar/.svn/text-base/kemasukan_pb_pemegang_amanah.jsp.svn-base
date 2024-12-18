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
    function addNewPemohon(j,a){
        window.open("${pageContext.request.contextPath}/pihak_berkepentingan?pihakKepentinganPopup&jenisPB=" +j +'&urusan=' + a, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800,scrollbars=yes");
    }   

    function remove(val){
        if(confirm('Adakah anda pasti?')){
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });

            //var url = '${pageContext.request.contextPath}/common/permohonan_atas_pihak?deleteMohonAtasPihakKaveat&id='+val;
            var url = '${pageContext.request.contextPath}/common/mohon_pihak?delete&idPermohonanPihak='+val+'&urusan=pnpa'; 
            $.ajax({
                type:"POST",
                url : url,
                dataType : 'html',
                error : function(xhr, ajaxOptions, thrownError) {
                    //alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                    $("#page_div").html('<div class=errors>Terdapat Masalah Teknikal. Sila Hubungi Pentadbir Sistem.</div><div id=t></div><br/><div id=error class=b>' + xhr.responseText + '</div>');
                    $.unblockUI();
                },
                success : function(data) {
                    $('#page_div').html(data);
                    $.unblockUI();
                }
            });
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
        $.post( url,
        function(data){
        }, 'html');

    }
    function removePemohon(id) {
        if(confirm('Adakah anda pasti?')){
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });

            //var url = '${pageContext.request.contextPath}/common/permohonan_atas_pihak?deleteMohonAtasPihakKaveat&id='+val;
            var url = '${pageContext.request.contextPath}/common/permohonan_atas_pihak?deletePemohon&idPemohon='+id;
            $.ajax({
                type:"POST",
                url : url,
                dataType : 'html',
                error : function(xhr, ajaxOptions, thrownError) {
                    //alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                    $("#page_div").html('<div class=errors>Terdapat Masalah Teknikal. Sila Hubungi Pentadbir Sistem.</div><div id=t></div><br/><div id=error class=b>' + xhr.responseText + '</div>');
                    $.unblockUI();
                },
                success : function(data) {
                    $('#page_div').html(data);
                    $.unblockUI();
                }
            });
        }
    }

    function add() {
        var pemegang = '';
        var pemberi = '';
        $('.pemegang').each(function(){
            var valid = $(this).is(':checked');
            if (valid) {
                pemegang = pemegang + '&idpemegang=' + $(this).val();
            }
        });

        $('.pemberi').each(function(){
            var valid = $(this).is(':checked');
            if (valid) {
                pemberi = pemberi + '&idpemberi=' + $(this).val();
            }
        });

        if (pemegang == '' && pemberi == '') {
            alert('Sila pilih pemegang atau pemberi');
            return;
        }

        doBlockUI();

        var url = '${pageContext.request.contextPath}/common/permohonan_atas_pihak?save'+pemegang+pemberi;        
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success : function(data){
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });

    }

    function doCheckPemberiPemegang(val, id, id2){
        if (id2 == 'pemberi') {
            $('.pemegang').each(function(){
                var valid = $(this).is(':checked');
                if (valid) {
                    if ($(this).val() == val) {
                        alert('Tuan Tanah telah diplih sebagai Pemegang Amanah.');
                        $('#'+id).attr('checked','');
                        return;
                    }
                }
            });
        } else if (id2 == 'pemegang') {
            $('.pemberi').each(function(){
                var valid = $(this).is(':checked');
                if (valid) {
                    if ($(this).val() == val) {
                        alert('Tuan Tanah telah diplih sebagai Pemberi Amanah.');
                        $('#'+id).attr('checked','');
                        return;
                    }
                }
            });
        }
    }

</script>
<div class="subtitle displaytag">
    <s:errors/>
    <s:form beanclass="etanah.view.stripes.common.PermohonanAtasPihakBerkepentinganActionBean">

        <fieldset class="aras1">
            <legend>Senarai Tuan Tanah</legend>
            <div class="content" >
                <p align="center"><label></label>&nbsp;
                    <%-- <display:table class="tablecloth" name="${actionBean.pihakKepentinganList}" cellpadding="0" cellspacing="0" id="line"
                                        requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

                            <display:column title="Bil" sortable="true">
                                    ${line_rowNum}</display:column>
                                <display:column title="Pemberi">
                                    <s:radio id="pemberi${line_rowNum}" name="rb"
                                             onclick="doCheckPemberiPemegang(this.value, this.id, 'pemberi');"
                                             value="${line.idHakmilikPihakBerkepentingan}" class="pemberi"/>
                                </display:column>
                                <display:column title="Pemberi">
                                    <s:checkbox name="checkbox" id="pemberi${line_rowNum}" value="${line.idHakmilikPihakBerkepentingan}" class="pemberi"/>
                                </display:column>
                                <display:column title="Pemegang">
                                    <s:radio id="pemegang" name="rb_pemegang"
                                             onclick="doCheckPemberiPemegang(this.value, this.id, 'pemegang');"
                                             value="${line.idHakmilikPihakBerkepentingan}" class="pemegang"/>
                                </display:column>
                                <display:column title="Pemegang">
                                    <s:checkbox name="checkbox" id="pemegang${line_rowNum}" value="${line.idHakmilikPihakBerkepentingan}" class="pemegang"/>
                                </display:column>
                                <display:column property="pihak.nama" title="Nama" class="nama"/>
                                <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                                <display:column title="Bahagian yang dimiliki" >${line.syerPembilang}/${line.syerPenyebut}</display:column>
                                <display:column title="Tarikh Pemilikan Tanah"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/></display:column>
                                <display:column property="jenis.nama" title="Jenis Pihak"/>
                        </display:table>--%>                    

                    <%--<c:forEach items="${actionBean.p.senaraiHakmilik}" var="item" varStatus="i">
                    <p>
                        <label>ID Hakmilik :</label>
                        ${item.hakmilik.idHakmilik}
                    </p>     --%>
                    <p align="center">
                        <display:table class="tablecloth" name="${actionBean.pihakKepentinganList}"
                                       cellpadding="0" cellspacing="0" id="line"
                                       requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

                            <%--<display:column title="Bil" sortable="true">
                                ${line_rowNum}</display:column>--%>
                            <%--<display:column title="Pemberi">
                                <s:radio id="pemberi${line_rowNum}" name="rb"
                                         onclick="doCheckPemberiPemegang(this.value, this.id, 'pemberi');"
                                         value="${line.idHakmilikPihakBerkepentingan}" class="pemberi"/>
                            </display:column>--%>
                            <display:column property="hakmilik.idHakmilik" title="Hakmilik" group="1"/>
                            <display:column title="Pemberi">
                                <s:checkbox name="checkbox" id="pemberi${line_rowNum}" value="${line.idHakmilikPihakBerkepentingan}" class="pemberi"/>
                            </display:column>
                            <%-- <display:column title="Pemegang">
                                 <s:radio id="pemegang" name="rb_pemegang"
                                          onclick="doCheckPemberiPemegang(this.value, this.id, 'pemegang');"
                                          value="${line.idHakmilikPihakBerkepentingan}" class="pemegang"/>
                             </display:column>--%>
                            <display:column title="Pemegang">
                                <s:checkbox name="checkbox" id="pemegang${line_rowNum}" value="${line.idHakmilikPihakBerkepentingan}" class="pemegang"/>
                            </display:column>
                            <display:column property="pihak.nama" title="Nama" class="nama"/>
                            <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                            
                            <display:column title="Bahagian yang dimiliki" >${line.syerPembilang}/${line.syerPenyebut}</display:column>
                            <display:column title="Tarikh Pemilikan Tanah"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/></display:column>
                            <display:column property="jenis.nama" title="Jenis Pihak"/>

                        </display:table>


                    </p>

                    <br/>
                <%--</c:forEach>--%>
                </p>
                <br/>
                <p>
                    <label>&nbsp;</label>&nbsp;
                    <%--<s:button class="btn" name="savePNPA" value="Simpan" onclick="doSubmit(this.form,this.name,'page_div');"/>--%>
                    <s:button class="btn" name="save" value="Simpan" onclick="add();"/>
                </p>
            </div>

        </fieldset>
        <br/>
        <fieldset class="aras1">
            <legend>Senarai Pemberi Amanah</legend>
            <br/>
            <font size="2" color="black">&nbsp;Sila pilih <font size="3" color="red">PEMBERI AMANAH</font> daripada tuan tanah</font>
            <div class="content">
                <p align="center"><label></label>&nbsp;
                    <display:table class="tablecloth" name="${actionBean.pemohonList}" cellpadding="0" cellspacing="0" id="lineMP">
                        <display:column title="Bil" sortable="true">${lineMP_rowNum}</display:column>
                        <display:column property="pihak.nama" title="Nama"/>
                        <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />                       
                        <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem${lineMP_rowNum}' onclick="removePemohon('${lineMP.idPemohon}')" onmouseover="this.style.cursor='pointer';">
                        </div>
                    </display:column>
                </display:table>
                </p>
                <br/>               
            </div>
        </fieldset>
        <br/>
        <fieldset class="aras1">
            <legend>Senarai Pemegang Amanah</legend>
            <br/>
            <font size="2" color="black">&nbsp;Sila pilih <font size="3" color="red">PEMEGANG AMANAH</font> daripada tuan tanah atau pilih pihak yang lain.</font>
            <div class="content" >
                <p align="center"><label></label>&nbsp;
                    <display:table class="tablecloth" name="${actionBean.mohonPihakPemegangAmanah}" cellpadding="0" cellspacing="0" id="lineMP">
                        <display:column title="Bil" sortable="true">${lineMP_rowNum}</display:column>
                        <display:column property="pihak.nama" title="Nama"/>
                        <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                        <display:column property="jenis.nama" title="Jenis Pihak"/>
                        <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem${lineMP_rowNum}' onclick="remove('${lineMP.idPermohonanPihak}')" onmouseover="this.style.cursor='pointer';">
                        </div>
                    </display:column>
                </display:table>
                </p>
                <br/>
                <%--<c:if test="${fn:length(actionBean.mohonPihakList)==0}">

                </c:if>--%>
                <p>
                    <label>&nbsp;</label>&nbsp;
                    <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addNewPemohon('PA', '');"/>&nbsp;
                </p>
            </div>
        </fieldset>
        <br/>
        <fieldset class="aras1">
            <legend>Senarai Penerima Amanah</legend>
            <div class="content">
                <p align="center"><label></label>&nbsp;
                    <display:table class="tablecloth" name="${actionBean.mohonPihakList}" cellpadding="0" cellspacing="0" id="line2">
                        <display:column title="Bil" sortable="true">${line2_rowNum}</display:column>
                        <display:column property="pihak.nama" title="Nama"/>
                        <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                        <display:column property="jenis.nama" title="Jenis Pihak"/>
                        <%-- <display:column title="Bahagian">
                                <div align="center">
                                    <s:text name="syer1[${line2_rowNum-1}]" value="${actionBean.syer1[line2_rowNum-1]}" id="syer1${line2_rowNum-1}" size="5" onblur="updateSyer('${line2.idPermohonanPihak}', '${line2_rowNum-1}')"/> /
                                    <s:text name="syer2[${line2_rowNum-1}]" value="${actionBean.syer2[line2_rowNum-1]}" id="syer2${line2_rowNum-1}" size="5" onblur="updateSyer('${line2.idPermohonanPihak}', '${line2_rowNum-1}')"/>
                                </div>
                            </display:column>--%>
                        <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem${line2_rowNum}' onclick="remove('${line2.idPermohonanPihak}')" onmouseover="this.style.cursor='pointer';">
                        </div>
                    </display:column>

                </display:table>
                </p>
                <br/>
                <c:if test="${fn:length(actionBean.pemohonList) > 0}">
                    <p>
                        <label>&nbsp;</label>&nbsp;
                        <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addNewPemohon('WAR', 'PNPA');"/>&nbsp;
                    </p>
                </c:if>
            </div>
        </fieldset>
    </s:form>
</div>
