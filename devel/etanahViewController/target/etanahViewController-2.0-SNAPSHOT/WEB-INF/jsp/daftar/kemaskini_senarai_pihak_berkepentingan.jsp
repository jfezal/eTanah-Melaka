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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/fraction.js" language="Javascript"></script>
<script type="text/javascript">

    var DELIM = "__^$__";

    $(document).ready( function(){
        $('.empty').remove();       
        
        var len2 = $(".alamat").length;
         
        for ( var i=0; i<len2; i++ ){
            var d = $('.x'+i).val();
    
            $('.nama'+i).bind('click',d, function(){
                window.open("${pageContext.request.contextPath}/pihak_berkepentingan?showEditPemohon&idPihak="+d, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=750");
            });
        }

    });

    function viewPihak(id, jenis){
        window.open("${pageContext.request.contextPath}/pihak_berkepentingan?viewPihakDetail&idPihak="+id+"&jenis="+jenis, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=670,scrollbars=yes");
    }

    function popupExisting(){
        window.open("${pageContext.request.contextPath}/common/pihak", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }

    function addNewPB(){
        window.open("${pageContext.request.contextPath}/pihak_berkepentingan?pihakKepentinganPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=960,height=700,scrollbars=yes");
    }

    function addNewPemohon(){
        window.open("${pageContext.request.contextPath}/pihak_berkepentingan?pemohonPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=700,scrollbars=yes");
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

    function removePemohon(val){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pihak_berkepentingan?deletePemohon&idPemohon='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }

    function removeChanges(val){
        var answer = confirm("adakah anda pasti untuk Hapus?");
        if (answer){
            doBlockUI();
            var url = '${pageContext.request.contextPath}/pihak_berkepentingan?deleteChanges&idKkini='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                doUnBlockUI();
            });
        }
    }

    function removeMultipleMohonPihak () {
        var param = '';
        if(confirm('Adakah anda pasti?')) {
            doBlockUI();
            $('.remove2').each(function(index){
                var a = $('#rm_mp'+index).is(":checked");
                if(a) {
                    param = param + '&idPermohonanPihak=' + $('#rm_mp'+index).val();
                }
            });
            if(param == ''){
                doUnBlockUI();
                alert('Sila Pilih Pemohon terlebih dahulu.');
                return;
            }
            var url = '${pageContext.request.contextPath}/common/mohon_pihak?deleteMultiplePihak'+param;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                doUnBlockUI();
            }, 'html');
        }
    }

    function removeMultiplePemohon() {
        var param = '';
        if(confirm('Adakah anda pasti?')) {
            doBlockUI();
            $('.remove').each(function(index){
                var a = $('#rm_'+index).is(":checked");
                if(a) {
                    param = param + '&idPihak=' + $('#rm_'+index).val();
                }
            });
            if(param == ''){
                doUnBlockUI();
                alert('Sila pilih pihak yang terlibat terlebih dahulu.');
                return;
            }
            var url = '${pageContext.request.contextPath}/pihak_berkepentingan?deleteSelectedPemohon'+param;
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
    }

    function removeMultipleMohonAtasPihak() {
        var param = '';
        if(confirm('Adakah anda pasti?')) {
            doBlockUI();
            $('.remove').each(function(index){
                var a = $('#rm_'+index).is(":checked");
                if(a) {
                    param = param + '&idMohonAtas=' + $('#rm_'+index).val();
                }
            });
            if(param == ''){
                alert('Sila pilih pihak yang terlibat terlebih dahulu.');
                doUnBlockUI();
                return;
            }
            var url = '${pageContext.request.contextPath}/pihak_berkepentingan?deleteSelectedMohonPihak'+param;
            $.ajax({
                type:"GET",
                url : url,
                dataType : 'html',
                error : function (xhr, ajaxOptions, thrownError){
                    doUnBlockUI();
                    alert("error=" + xhr.responseText);
                },
                success : function(data){
                    $('#page_div').html(data);
                    doUnBlockUI();
                }
            });

        }
    }

    function addPemohon(){
        var len = $('.nama').length;
        var param = '';
        doBlockUI();
        
        for(var i=1; i<=len; i++){
            
            var ckd = $('#chkbox'+i).is(":checked");
            if(ckd){
                param = param + '&idPihak=' + $('#chkbox'+i).val();
            }
        }
        if(param == ''){
            alert('Tiada pemohon.');
            doUnBlockUI();
            return;
        }
        var url = '${pageContext.request.contextPath}/pihak_berkepentingan?simpanPemohon'+param;

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

    function addAtasPihak(){
        doBlockUI();
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
            doUnBlockUI();
            return;
        }
        var url = '${pageContext.request.contextPath}/pihak_berkepentingan?simpanMohonAtasPihak'+param;

        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success : function(data){
                doUnBlockUI();
                $('#page_div').html(data);
            }
        });
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

    function samaRata(f){
        var q = $(f).formSerialize();
        $.get('${pageContext.request.contextPath}/pihak_berkepentingan?agihSamaRata',q,
        function(data){
            if( data == null || data.length == 0) {
                alert('Terdapat Masalah');
                return;
            }
            var p = data.split(DELIM);
            $('.pembilang').each(function(){
                $(this).val(p[0]);
            });
            $('.penyebut').each(function(){
                $(this).val(p[1]);
            });
        });
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
    <s:form beanclass="etanah.view.stripes.common.PihakBerkepentinganActionBean">
        <%--and actionBean.p.kodUrusan.kod ne 'KVSS'--%>
        <%--and actionBean.p.kodUrusan.kod ne 'KVLS'--%>
        <c:if test="${actionBean.p.kodUrusan.kod ne 'KVPT'
                      and actionBean.p.kodUrusan.kod ne 'KVST'

              }">
            <fieldset class="aras1">
                <c:choose>
                    <c:when test="${actionBean.p.kodUrusan.kod eq 'PH30B' 
                                    || actionBean.p.kodUrusan.kod eq 'PH30A' || actionBean.p.kodUrusan.kod eq 'TN'
                                    || actionBean.p.kodUrusan.kod eq 'JPGPJ' || actionBean.p.kodUrusan.kod eq 'JMGPJ'
                                    || actionBean.p.kodUrusan.kod eq 'JMLK'
                                    || actionBean.p.kodUrusan.kod eq 'KVSPC' || actionBean.p.kodUrusan.kod eq 'KVSK'}">
                            
                        <legend>Senarai Pihak Berkepentingan</legend>
                    </c:when>
                    <c:otherwise>
                        <legend>Senarai Pemilik</legend>
                    </c:otherwise>                    
                </c:choose>
                <c:if test="${edit}">
                    <p align="left">
                        <font size="2" color="red">
                                Sila Klik pada kotak dan tekan Pilih untuk memilih
                                <c:if test="${actionBean.p.kodUrusan.kod eq 'PNPA'}">Pemegang Amanah </c:if>
                                <c:if test="${actionBean.p.kodUrusan.kod ne 'PNPA'}">Pihak Berkepentingan Yang Terlibat</c:if>.
                            
                        </font>
                    </p>
                </c:if>

                <div class="content" align="center">
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
                                    <display:column title="Bahagian yang dimiliki" >
                                        <c:if test="${actionBean.p.kodUrusan.kod ne 'TN'}">
                                            ${line.syerPembilang}/${line.syerPenyebut}
                                        </c:if>
                                    </display:column>
                                <display:column property="jenis.nama" title="Jenis Pihak"/>
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
                                <display:column  title="Nama" class="nama">
                                    <a href="#" onclick="viewPihak('${line.pihak.idPihak}','tuanTanah');return false;">${line.pihak.nama}</a>
                                </display:column>
                                <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                                <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                                <display:column title="Bahagian yang dimiliki" >
                                    <c:if test="${actionBean.p.kodUrusan.kod ne 'PNPAB'}">
                                            ${line.syerPembilang}/${line.syerPenyebut}
                                    </c:if>
                                    <c:if test="${actionBean.p.kodUrusan.kod eq 'PNPAB'}">
                                            -
                                    </c:if>
                                </display:column>
                                <%--display:column title="Tarikh Pemilikan Tanah">
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>
                                </display:column--%>
                                <display:column property="jenis.nama" title="Jenis Pihak"/>
                            </display:table>
                        </c:otherwise>
                    </c:choose>
                </div>
                <c:if test="${fn:length(actionBean.senaraiPemohonBerangkai) > 0 || fn:length(actionBean.pihakKepentinganList) > 0}">
                    <c:if test="${edit}">
                        <p>
                            <label>&nbsp;</label>
                            <c:if test="${actionBean.p.kodUrusan.kod eq 'TRPA'}">
                                    <s:button class="btn" value="Pilih" name="pilih" id="pilih" onclick="addAtasPihak();"/>&nbsp;
                            </c:if>
                            <c:if test="${actionBean.p.kodUrusan.kod ne 'TRPA'}">
                                    <s:button class="btn" value="Pilih" name="pilih" id="pilih" onclick="addPemohon();"/>&nbsp;
                            </c:if>
                        </p>
                    </c:if>
                </c:if>
            </fieldset>
            <br/>
        </c:if>
         <%--and actionBean.p.kodUrusan.kod ne 'KVSS'--%>
         <%--and actionBean.p.kodUrusan.kod ne 'KVLS'--%>
        <c:if test="${actionBean.p.kodUrusan.kod ne 'KVPT'
                      and actionBean.p.kodUrusan.kod ne 'KVST'
                      and actionBean.p.kodUrusan.kod ne 'KVPPT'
              }">
            <fieldset class="aras1">

                <legend>
                    <c:set var="title" value="Pihak Yang Terlibat"/>
                    <c:if test="${actionBean.p.kodUrusan.kod eq 'TMAMG'}">
                        <c:set var="title" value="Si Mati"/>
                    </c:if>
                    <c:if test="${actionBean.p.kodUrusan.kod eq 'IS' || actionBean.p.kodUrusan.kod eq 'ISBLS' }">
                        <c:set var="title" value="Pemohon Ismen"/>
                    </c:if>
                    <c:if test="${actionBean.p.kodUrusan.kod eq 'PNPA'}">
                        <c:set var="title" value="Pemegang Amanah"/>
                    </c:if>
                    <c:if test="${actionBean.p.kodUrusan.kod eq 'JPGD' || actionBean.p.kodUrusan.kod eq 'JMGD'
                                  || actionBean.p.kodUrusan.kod eq 'JML'}">
                        <c:set var="title" value="Tuan Tanah yang terlibat"/>
                    </c:if>
                    Senarai ${title}
                </legend>

                <c:if test="${actionBean.p.kodUrusan.kod eq 'PNKP' || actionBean.p.kodUrusan.kod eq 'TN'}">
                    <p align="left">
                        <font size="2" color="red">
                            Sila klik pada kemaskini untuk membuat sebarang pertukaran.
                        </font>
                    </p>
                </c:if>

                <%--second layer start--%>

                <c:if test="${actionBean.p.kodUrusan.kod eq 'TRPA'}">
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.mapList}" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                            <c:if test="${edit}">
                                <display:column>
                                    <s:checkbox name="rm" id="rm_${line_rowNum-1}" value="${line.idAtasPihak}" class="remove"/>
                                </display:column>
                            </c:if>
                             <display:column title="Bil" sortable="true">${line_rowNum}
                                <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihakBerkepentingan.pihak.idPihak}"/>
                            </display:column>
                            <display:column  title="Nama" >
                                <a href="#" onclick="viewPihak('${line.pihakBerkepentingan.pihak.idPihak}', 'pemohon');return false;">${line.pihakBerkepentingan.pihak.nama}</a>
                            </display:column>
                            <display:column property="pihakBerkepentingan.pihak.noPengenalan" title="Nombor Pengenalan" />
                            <display:column title="Alamat">${line.pihakBerkepentingan.pihak.suratAlamat1}
                                <c:if test="${line.pihakBerkepentingan.pihak.suratAlamat2 ne null}"> , </c:if>
                                ${line.pihakBerkepentingan.pihak.suratAlamat2}
                                <c:if test="${line.pihakBerkepentingan.pihak.suratAlamat3 ne null}"> , </c:if>
                                ${line.pihakBerkepentingan.pihak.suratAlamat3}
                                <c:if test="${line.pihakBerkepentingan.pihak.suratAlamat4 ne null}"> , </c:if>
                                ${line.pihakBerkepentingan.pihak.suratAlamat4}
                                <c:if test="${line.pihakBerkepentingan.pihak.suratPoskod ne null}">,</c:if>
                                ${line.pihakBerkepentingan.pihak.suratPoskod}
                                <c:if test="${line.pihakBerkepentingan.pihak.suratNegeri.kod ne null}">,</c:if>
                                ${line.pihakBerkepentingan.pihak.suratNegeri.nama}
                            </display:column>
                            <c:if test="${edit}">
                                <display:column title="Kemaskini">
                                    <p align="center">
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="dopopup('${line_rowNum -1}','${actionBean.p.kodUrusan.kod}');return false;" onmouseover="this.style.cursor='pointer';">
                                    </p>                                    
                                </display:column>
                            </c:if>
                        </display:table>
                    </div>
                     <p>
                        <label>&nbsp;</label>
                        <c:if test="${edit && fn:length(actionBean.mapList) > 0}" >
                            <s:button class="btn" value="Hapus" name="new" id="new" onclick="removeMultipleMohonAtasPihak();"/>&nbsp;
                        </c:if>
                    </p>
                </c:if>
                <c:if test="${actionBean.p.kodUrusan.kod ne 'TRPA'}">
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.pemohonList}" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                            <c:if test="${edit}">
                                <display:column>
                                    <s:checkbox name="rm" id="rm_${line_rowNum-1}" value="${line.idPemohon}" class="remove"/>
                                </display:column>
                            </c:if>
                            <display:column title="Bil" sortable="true">${line_rowNum}
                                <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                            </display:column>
                            <display:column  title="Nama" >
                                <a href="#" onclick="viewPihak('${line.pihak.idPihak}', 'pemohon');return false;">${line.pihak.nama}</a>
                            </display:column>
                            <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                            <display:column title="Alamat">${line.pihak.suratAlamat1}
                                <c:if test="${line.pihak.suratAlamat2 ne null}"> , </c:if>
                                ${line.pihak.suratAlamat2}
                                <c:if test="${line.pihak.suratAlamat3 ne null}"> , </c:if>
                                ${line.pihak.suratAlamat3}
                                <c:if test="${line.pihak.suratAlamat4 ne null}"> , </c:if>
                                ${line.pihak.suratAlamat4}
                                <c:if test="${line.pihak.suratPoskod ne null}">,</c:if>
                                ${line.pihak.suratPoskod}
                                <c:if test="${line.pihak.suratNegeri.kod ne null}">,</c:if>
                                ${line.pihak.suratNegeri.nama}
                            </display:column>
                            <c:if test="${edit}">
                                <display:column title="Kemaskini">
                                    <p align="center">
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="dopopup('${line_rowNum -1}','${actionBean.p.kodUrusan.kod}');return false;" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                    <%--<a href="#" onclick="dopopup('${line_rowNum -1}','${actionBean.p.kodUrusan.kod}');return false;">Kemaskini</a></display:column>--%>
                                </display:column>
                                <%--<display:column title="Hapus">
                                    <div align="center">
                                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                             id='rem_${line_rowNum}' onclick="removePemohon('${line.idPemohon}')" onmouseover="this.style.cursor='pointer';">
                                    </div>
                                </display:column>--%>
                            </c:if>
                        </display:table>
                    </div>
                    <%--<c:if test="${edit and actionBean.p.kodUrusan.kod ne 'IS' and actionBean.p.kodUrusan.kod ne 'TN' and actionBean.p.kodUrusan.kod ne 'TA' and actionBean.p.kodUrusan.kod ne 'PMT'}">--%>
                    <p>
                        <label>&nbsp;</label>
                        <c:if test="${edit && actionBean.p.kodUrusan.jabatanNama ne 'Pendaftaran' && actionBean.p.kodUrusan.jabatanNama ne 'Consent'}">
                            <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addNewPemohon();"/>&nbsp;
                            <label>&nbsp;</label>
                        </c:if>
                        <c:if test="${edit && fn:length(actionBean.pemohonList) > 0}" >
                            <s:button class="btn" value="Hapus" name="new" id="new" onclick="removeMultiplePemohon();"/>&nbsp;
                        </c:if>
                    </p>
                </c:if>
            </fieldset>
            <br/>
        </c:if>        
        <c:if test="${actionBean.p.kodUrusan.kod eq 'TA' or actionBean.p.kodUrusan.kod eq 'TN' or actionBean.p.kodUrusan.kod eq 'PNKP'}">
            <div id="pertukaran">
                <fieldset class="aras1"><legend>Senarai Pertukaran</legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.mohonPihakKemaskiniList}" cellpadding="0" cellspacing="0" id="linePertukaran">
                            <display:column title="Bil" sortable="true">${linePertukaran_rowNum}</display:column>

                            <display:column  title="Maklumat Lama" style="text-transform:uppercase">
                                <c:if test="${linePertukaran.namaMedan eq 'rumahNegeri.kod'}">
                                    ${actionBean.namaNegeriLama}
                                </c:if>
                                <c:if test="${linePertukaran.namaMedan ne 'rumahNegeri.kod' && linePertukaran.namaMedan ne 'jeniskp'}">
                                    ${linePertukaran.nilaiLama}
                                </c:if>
                                <c:if test="${linePertukaran.namaMedan eq 'jeniskp'}">
                                    <c:if test="${linePertukaran.nilaiLama eq 'L'}">No K/P Lama</c:if>
                                    <c:if test="${linePertukaran.nilaiLama eq 'B'}">No K/P</c:if>
                                    <c:if test="${linePertukaran.nilaiLama eq 'S'}">No Syarikat</c:if>
                                    <c:if test="${linePertukaran.nilaiLama eq 'P'}">No Pasport</c:if>
                                    <c:if test="${linePertukaran.nilaiLama eq 'T'}">No Tentera</c:if>
                                    <c:if test="${linePertukaran.nilaiLama eq 'I'}">No Polis</c:if>
                                    <c:if test="${linePertukaran.nilaiLama eq 'O'}">Tidak Berkenaan</c:if>
                                    <c:if test="${linePertukaran.nilaiLama eq 'N'}">No Bank</c:if>
                                    <c:if test="${linePertukaran.nilaiLama eq 'F'}">No Paksa</c:if>
                                    <c:if test="${linePertukaran.nilaiLama eq 'U'}">No Pertubuhan</c:if>
                                    <c:if test="${linePertukaran.nilaiLama eq 'D'}">No Pendaftaran</c:if>
                                </c:if>
                            </display:column>
                            <display:column title="Maklumat Baru" style="text-transform:uppercase">
                                <c:if test="${linePertukaran.namaMedan eq 'rumahNegeri.kod'}">
                                    ${actionBean.namaNegeriBaru}
                                </c:if>
                                <c:if test="${linePertukaran.namaMedan ne 'rumahNegeri.kod' && linePertukaran.namaMedan ne 'jeniskp'}">
                                    ${linePertukaran.nilaiBaru}
                                </c:if>
                                <c:if test="${linePertukaran.namaMedan eq 'jeniskp'}">
                                    <c:if test="${linePertukaran.nilaiBaru eq 'L'}">No K/P Lama</c:if>
                                    <c:if test="${linePertukaran.nilaiBaru eq 'B'}">No K/P</c:if>
                                    <c:if test="${linePertukaran.nilaiBaru eq 'S'}">No Syarikat</c:if>
                                    <c:if test="${linePertukaran.nilaiBaru eq 'P'}">No Pasport</c:if>
                                    <c:if test="${linePertukaran.nilaiBaru eq 'T'}">No Tentera</c:if>
                                    <c:if test="${linePertukaran.nilaiBaru eq 'I'}">No Polis</c:if>
                                    <c:if test="${linePertukaran.nilaiBaru eq 'O'}">Tidak Berkenaan</c:if>
                                    <c:if test="${linePertukaran.nilaiBaru eq 'N'}">No Bank</c:if>
                                    <c:if test="${linePertukaran.nilaiBaru eq 'F'}">No Paksa</c:if>
                                    <c:if test="${linePertukaran.nilaiBaru eq 'U'}">No Pertubuhan</c:if>
                                    <c:if test="${linePertukaran.nilaiBaru eq 'D'}">No Pendaftaran</c:if>
                                </c:if>

                            </display:column>      
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem${linePertukaran_rowNum}' onclick="removeChanges('${linePertukaran.idKemaskini}')" >
                                </div>
                            </display:column>
                        </display:table>
                    </div>
                </fieldset>

            </div>

        </c:if>
        <c:if test="${actionBean.p.kodUrusan.kod ne 'TA' and actionBean.p.kodUrusan.kod ne 'TN' 
                      and actionBean.p.kodUrusan.kod ne 'PNKP' and actionBean.p.kodUrusan.kod ne 'PLS'
                      and actionBean.p.kodUrusan.kod ne 'KVSPC' and actionBean.p.kodUrusan.kod ne 'KVPPT'}">
            <div id="mohon_pihak">
                <fieldset class="aras1">
                    <legend>Senarai
                        <c:if test="${fn:startsWith(actionBean.p.kodUrusan.kod, 'KV')}">
                                        Pemasuk Kaveat</c:if>
                        <c:if test="${actionBean.p.kodUrusan.kod eq 'PNPAB'}">Pemilik Baru</c:if>
                        <c:if test="${actionBean.p.kodUrusan.kod ne 'GD'
                                      && actionBean.p.kodUrusan.kod ne 'JPGD' 
                                      && actionBean.p.kodUrusan.kod ne 'JMGD' && actionBean.p.kodUrusan.kod ne 'PNPAB'
                                      && actionBean.p.kodUrusan.kod ne 'MGGS'
                                      && actionBean.p.kodUrusan.kod ne 'MGG' && actionBean.p.kodUrusan.kod ne 'GDPJK'
                                      && actionBean.p.kodUrusan.kod ne 'JML' &&
                                        !fn:startsWith(actionBean.p.kodUrusan.kod,'KV')}">Penerima</c:if>
                        <c:if test="${actionBean.p.kodUrusan.kod eq 'GD' || actionBean.p.kodUrusan.kod eq 'GDPJK'}">Pemegang Gadaian</c:if>
                        <c:if test="${actionBean.p.kodUrusan.kod eq 'MGGS'
                                      || actionBean.p.kodUrusan.kod eq 'MGG'}">Pemberi Mortgage</c:if>
                        <c:if test="${actionBean.p.kodUrusan.kod eq 'JPGD' || actionBean.p.kodUrusan.kod eq 'JMGD'
                                      || actionBean.p.kodUrusan.kod eq 'JML'}">Tuan Tanah Baru</c:if>
                        <c:if test="${actionBean.p.kodUrusan.kod eq 'IS' || actionBean.p.kodUrusan.kod eq 'ISBLS'}">Ismen</c:if>
                        <%--actionBean.p.kodUrusan.kod eq 'KVLK' or actionBean.p.kodUrusan.kod eq 'KVPB' or actionBean.p.kodUrusan.kod eq 'KVPT'
                                      || actionBean.p.kodUrusan.kod eq 'KVPS' or actionBean.p.kodUrusan.kod eq 'KVAT'
                                      or actionBean.p.kodUrusan.kod eq 'KVST' or actionBean.p.kodUrusan.kod eq 'KVSS'
                                      or actionBean.p.kodUrusan.kod eq 'KVLS' or actionBean.p.kodUrusan.kod eq 'KVAS'}"--%>                        
                    </legend>
                        <c:if test="${actionBean.p.kodUrusan.kod eq 'KVST'}">
                            <font size="2"  color="red"> Sila klik Tuan Tanah jika pemasuk kaveat adalah Tuan Tanah sendiri.</font>
                        </c:if>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.mohonPihakList}" cellpadding="0" cellspacing="0" id="lineMP">
                            <c:if test="${edit}">
                                <display:column>
                                    <s:checkbox name="rm_mp" id="rm_mp${lineMP_rowNum-1}" value="${lineMP.idPermohonanPihak}" class="remove2"/>
                                </display:column>
                            </c:if>
                            <display:column title="Bil" sortable="true">${lineMP_rowNum}
                                <s:hidden name="a" class="a${lineMP_rowNum-1}" value="${lineMP.pihak.idPihak}"/>
                            </display:column>
                            <display:column  title="Nama" >
                                <a href="#" onclick="viewPihak('${lineMP.pihak.idPihak}','penerima');return false;">${lineMP.pihak.nama}</a>
                            </display:column>
                            <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" class="penerimaKP"/>
                            <c:if test="${edit and actionBean.p.kodUrusan.kod ne 'KVLK'
                                          and actionBean.p.kodUrusan.kod ne 'KVPB' and actionBean.p.kodUrusan.kod ne 'KVPT'
                                          and actionBean.p.kodUrusan.kod ne 'KVST' and actionBean.p.kodUrusan.kod ne 'KVSS' 
                                          and actionBean.p.kodUrusan.kod ne 'KVLS' and actionBean.p.kodUrusan.kod ne 'IS' 
                                          and actionBean.p.kodUrusan.kod ne 'ISBLS' 
                                          and actionBean.p.kodUrusan.kod ne 'GDPJK'
                                          and actionBean.p.kodUrusan.kod ne 'MGG'
                                          and actionBean.p.kodUrusan.kod ne 'MGGS'
                                  }">
                                <display:column title="Bahagian yang terlibat">
                                    <div align="center">
                                        <s:text name="syer1[${lineMP_rowNum-1}]" size="5" id="syer1${lineMP_rowNum-1}"
                                                onblur="updateSyer('${lineMP.idPermohonanPihak}', '${lineMP_rowNum-1}')"
                                                onchange="updateSyer('${lineMP.idPermohonanPihak}', '${lineMP_rowNum-1}')" class="pembilang"/> /
                                        <s:text name="syer2[${lineMP_rowNum-1}]" size="5" id="syer2${lineMP_rowNum-1}"
                                                onblur="updateSyer('${lineMP.idPermohonanPihak}', '${lineMP_rowNum-1}')"
                                                onchange="updateSyer('${lineMP.idPermohonanPihak}', '${lineMP_rowNum-1}')" class="penyebut"/>
                                    </div>
                                </display:column>
                            </c:if>
                            <c:if test="${actionBean.p.kodUrusan.kod eq 'KVLK' and actionBean.p.kodUrusan.kod eq 'KVPB' and actionBean.p.kodUrusan.kod eq 'KVPT'
                                          and actionBean.p.kodUrusan.kod eq 'KVST' and actionBean.p.kodUrusan.kod eq 'KVSS' and actionBean.p.kodUrusan.kod eq 'KVLS'
                                  }">
                                <display:column property="jenis.nama" title="Jenis PB"/>
                            </c:if>

                            <c:if test="${!edit and actionBean.p.kodUrusan.kod ne 'KVLK' and actionBean.p.kodUrusan.kod ne 'KVPB' and actionBean.p.kodUrusan.kod ne 'KVPT'
                                          and actionBean.p.kodUrusan.kod ne 'KVST' and actionBean.p.kodUrusan.kod ne 'KVSS' and actionBean.p.kodUrusan.kod ne 'KVLS'
                                  }">
                                <display:column title="Bahagian yang terlibat">${lineMP.syerPembilang}/${lineMP.syerPenyebut}</display:column>
                            </c:if>

                            <c:if test="${edit}">
                                <display:column title="Kemaskini">
                                    <p align="center">
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="editPenerima('${lineMP_rowNum-1}','${actionBean.p.kodUrusan.kod}');return false;" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </display:column>
                                <%--<display:column title="Hapus">
                                    <div align="center">
                                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                             id='rem${lineMP_rowNum}' onclick="remove('${lineMP.idPermohonanPihak}')" onmouseover="this.style.cursor='pointer';">
                                    </div>
                                </display:column>--%>
                            </c:if>
                        </display:table>
                    </div>

                    <c:if test="${edit}">
                        <p>
                            <label>&nbsp;</label>
                            <%--<s:button class="btn" value="Carian Pihak" name="searchExisting" id="searchExisting" onclick="popupExisting();"/>&nbsp;--%>
                            <c:if test="${actionBean.p.kodUrusan.kod ne 'PNPAB'}">
                                <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addNewPB();"/>&nbsp;
                            </c:if>
                            <c:if test="${actionBean.p.kodUrusan.kod ne 'KVLK' and actionBean.p.kodUrusan.kod ne 'KVPB' and actionBean.p.kodUrusan.kod ne 'KVPT'
                                          and actionBean.p.kodUrusan.kod ne 'KVST' and actionBean.p.kodUrusan.kod ne 'KVSS' and actionBean.p.kodUrusan.kod ne 'KVLS'
                                          and actionBean.p.kodUrusan.kod ne 'IS' and actionBean.p.kodUrusan.kod ne 'ISBLS' and actionBean.p.kodUrusan.kod ne 'GDPJK'
                                          and actionBean.p.kodUrusan.kod ne 'MGG' and actionBean.p.kodUrusan.kod ne 'MGGS' and actionBean.p.kodUrusan.kod ne 'PNPAB'
                                  }">
                                <c:if test="${fn:length(actionBean.mohonPihakList) > 0}">
                                    <s:button class="btn" value="Semak Syer" name="semak" id="semak" onclick="semakSyer(this.form);"/>&nbsp;
                                    <s:button class="longbtn" value="Agih Sama Rata" name="" id="_agihSamaRata" onclick="samaRata(this.form);"/>&nbsp;                                    
                                </c:if>
                            </c:if>
                            <c:if test="${actionBean.p.kodUrusan.kod eq 'KVST'}">
                                <s:button class="btn" value="Tuan Tanah" name="semak" id="semak" onclick="doPopupTuanTanah();"/>&nbsp;
                            </c:if>
                            <c:if test="${actionBean.p.kodUrusan.kod eq 'PNPAB' && fn:length(actionBean.pihakKepentinganList) > 0}">
                                <s:button class="btn" value="Pemilik Baru" name="semak" id="semak" onclick="doPopupTuanTanah();"/>&nbsp;
                            </c:if>
                            <c:if test="${fn:length(actionBean.mohonPihakList) > 0}">
                                <s:button class="btn" value="Hapus" name="" onclick="removeMultipleMohonPihak();"/>&nbsp;
                            </c:if>
                            <%--<c:if test="${copy}">
                                <s:button class="btn" value="Salin Lama" name="copy" id="copy" onclick="copy();"/>
                            </c:if>--%>
                        </p>
                    </c:if>
                </fieldset>
            </div>
        </c:if>
    </s:form>

</div>

    <script type="text/javascript">
        function doPopupTuanTanah() {
            window.open("${pageContext.request.contextPath}/common/pemohon?showFormPopup", "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=910,height=700,scrollbars=yes");
        }
    </script>
