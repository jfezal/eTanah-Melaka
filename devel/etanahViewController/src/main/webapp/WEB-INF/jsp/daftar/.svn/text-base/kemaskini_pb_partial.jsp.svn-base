
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    var DELIM = "__^$__";


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
        var url = '${pageContext.request.contextPath}/pihak_berkepentingan?simpanMohonAtasPihak'+param + '&hakmilik='+ $('#hakmilik').val();

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
                $('#div_content').html(data);
            }
        });
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
            var url = '${pageContext.request.contextPath}/pihak_berkepentingan?deleteSelectedMohonPihak'+param+ '&hakmilik='+ $('#hakmilik').val();
            $.ajax({
                type:"GET",
                url : url,
                dataType : 'html',
                error : function (xhr, ajaxOptions, thrownError){
                    doUnBlockUI();
                    alert("error=" + xhr.responseText);
                },
                success : function(data){
                    $('#div_content').html(data);
                    doUnBlockUI();
                }
            });

        }
    }
    
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
        var url = '${pageContext.request.contextPath}/pihak_berkepentingan?simpanPemohonMultipleHakmilik'+param+'&hakmilik='+ $('#hakmilik').val();

        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
            },
            success : function(data){
                $('#div_content').html(data);
            }
        });
    }

    function removeMultiplePemohon() {
        var param = '';
        if(confirm('Adakah anda pasti pemohon?')) {
            $('.remove').each(function(index){
                var a = $('#rm_'+index).is(":checked");
                if(a) {
                    param = param + '&idPihak=' + $('#rm_'+index).val();
                }
            });
            if(param == ''){
                alert('Sila Pilih Pemohon terlebih dahulu.');
                return;
            }
            var url = '${pageContext.request.contextPath}/pihak_berkepentingan?deleteSelectedPemohon'+param+'&hakmilik='+ $('#hakmilik').val();
            $.ajax({
                type:"GET",
                url : url,
                dataType : 'html',
                error : function (xhr, ajaxOptions, thrownError){
                    alert("error=" + xhr.responseText);
                },
                success : function(data){
                    $('#div_content').html(data);
                }
            });
        }
    }
    
    function addNewPB(){
        window.open("${pageContext.request.contextPath}/pihak_berkepentingan?pihakKepentinganPopup&hakmilik=" + $('#hakmilik').val(), "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=700,scrollbars=yes");
    }

    function samaRata(f){
        var q = $(f).formSerialize();
        $.get('${pageContext.request.contextPath}/pihak_berkepentingan?agihSamaRata&hakmilik='+ $('#hakmilik').val(),q,
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
        window.open("${pageContext.request.contextPath}/pihak_berkepentingan?"+url+"&idPihak="+d+"&hakmilik=" + $('#hakmilik').val(), "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
    }

    function editPemohon(i,kod){
        if(kod == "TN" || kod == "PNKP"){
            var url ="showEditNamaPemohon";
        }
        else if(kod == "TA"){
            var url = "showEditAlamatPemohon";
        }else{
            var url = "showEditPemohon";
        }
        var d = $('.x'+i).val();
        window.open("${pageContext.request.contextPath}/pihak_berkepentingan?"+url+"&idPihak="+d+"&hakmilik=" + $('#hakmilik').val(), "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
    }

    function removeMultipleMohonPihak () {
        var param = '';
        if(confirm('Adakah anda pasti?')) {
            $('.remove2').each(function(index){
                var a = $('#rm_mp'+index).is(":checked");
                if(a) {
                    param = param + '&idPermohonanPihak=' + $('#rm_mp'+index).val();
                }
            });
            if(param == ''){
                alert('Sila Pilih Pemohon terlebih dahulu.');
                return;
            }
            var url = '${pageContext.request.contextPath}/common/mohon_pihak?deleteMultiplePihak'+param+'&hakmilik='+ $('#hakmilik').val();;
            $.get(url,
            function(data){
                $('#div_content').html(data);
            }, 'html');
        }
    }

    function semakSyer(f){
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pihak_berkepentingan?semakSyerByIdHakmilik&hakmilik=' + $('#hakmilik').val(),q,
        function(data){
            if(data != ''){
                alert(data);
            }
        }, 'html');
    }

   function doPopupTuanTanah() {
            window.open("${pageContext.request.contextPath}/common/pemohon?showFormPopup&idHakmilik=" + $('#hakmilik').val(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=910,height=700,scrollbars=yes");
        }
</script>

<s:form beanclass="etanah.view.stripes.common.PihakBerkepentinganActionBean">
    <fieldset class="aras1">
        <legend>Senarai Tuan Tanah</legend>
        <br/>
        <%--display:table class="tablecloth" name="${actionBean.pihakKepentinganList}" cellpadding="0" cellspacing="0" id="line"
                       requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
            <c:if test="${edit}">
                <display:column>
                    <s:checkbox name="checkbox" id="chkbox${line_rowNum}" value="${line.pihak.idPihak}"/>
                </display:column>
            </c:if>
            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
            <display:column property="pihak.nama" title="Nama" class="nama"/>
            <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
            <display:column title="ID Hakmilik" >
                <s:hidden name="hakmilik" id="hakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}
            </display:column>
            <display:column title="Bahagian yang dimiliki" >${line.syerPembilang}/${line.syerPenyebut}</display:column>
            <display:column title="Tarikh Pemilikan Tanah">
                <fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>
            </display:column>
            <display:column property="jenis.nama" title="Jenis Pihak"/>
        </display:table--%>

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
                    <display:column title="ID Hakmilik" >
                        <s:hidden name="hakmilik" id="hakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}
                    </display:column>
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
                    <display:column property="pihak.nama" title="Nama" class="nama"/>
                    <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                    <display:column title="ID Hakmilik" >
                        <s:hidden name="hakmilik" id="hakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}
                    </display:column>
                    <display:column title="Bahagian yang dimiliki" >${line.syerPembilang}/${line.syerPenyebut}</display:column>
                    <%--display:column title="Tarikh Pemilikan Tanah">
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>
                    </display:column--%>
                    <display:column property="jenis.nama" title="Jenis Pihak"/>
                </display:table>
            </c:otherwise>
        </c:choose>

        <br/>
        <c:if test="${fn:length(actionBean.senaraiPemohonBerangkai) > 0 || fn:length(actionBean.pihakKepentinganList) > 0}">
            <c:if test="${edit}">
                <p>
                    <label></label>
                    <c:if test="${actionBean.p.kodUrusan.kod eq 'TRPA'}">
                        <s:button class="btn" value="Pilih" name="pilih" id="pilih" onclick="addAtasPihak();"/>&nbsp;
                    </c:if>
                    <c:if test="${actionBean.p.kodUrusan.kod ne 'TRPA'}">
                        <s:button class="btn" value="Pilih" name="pilih" id="pilih" onclick="addPemohon();"/>&nbsp;
                    </c:if>
                </p>
            </c:if>
        </c:if>
        <br/>
    </fieldset>

    <br/>
    <fieldset class="aras1">
        <legend>Senarai Pihak Yang Terlibat</legend>
        <br/>

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
                <label></label>
                <c:if test="${edit && fn:length(actionBean.mapList) > 0}" >
                    <s:button class="btn" value="Hapus" name="new" id="new" onclick="removeMultipleMohonAtasPihak();"/>&nbsp;
                </c:if>
            </p>
        </c:if>
        <c:if test="${actionBean.p.kodUrusan.kod ne 'TRPA'}">

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

                <c:if test="${edit}">
                    <display:column title="Kemaskini">
                        <p align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                 onclick="editPemohon('${line_rowNum -1}','${actionBean.p.kodUrusan.kod}');return false;" onmouseover="this.style.cursor='pointer';">
                        </p>
                    </display:column>
                </c:if>
            </display:table>
        </c:if>
        <br/>
        <c:if test="${edit && fn:length(actionBean.pemohonList) > 0}" >
            <p>
                <label></label>
                <s:button class="btn" value="Hapus" name="new" id="new" onclick="removeMultiplePemohon();"/>&nbsp;
            </p>
        </c:if>
        <br/>
    </fieldset>
    <br/>
    <c:if test="${actionBean.p.kodUrusan.kod ne 'TA' and actionBean.p.kodUrusan.kod ne 'TN' and actionBean.p.kodUrusan.kod ne 'PNKP'}">
        <div id="mohon_pihak">
            <fieldset class="aras1">
                <legend>Senarai
                    <%--<c:if test="${actionBean.p.kodUrusan.kod eq 'KVLK' or actionBean.p.kodUrusan.kod eq 'KVPB' or actionBean.p.kodUrusan.kod eq 'KVPT'
                                  or actionBean.p.kodUrusan.kod eq 'KVST' or actionBean.p.kodUrusan.kod eq 'KVSS' or actionBean.p.kodUrusan.kod eq 'KVLS'}">Kaveat</c:if>--%>
                    <c:if test="${fn:startsWith(actionBean.p.kodUrusan.kod,'KV')}">Kaveator</c:if>
                    <c:if test="${actionBean.p.kodUrusan.kod ne 'KVLK' && actionBean.p.kodUrusan.kod ne 'KVPT'
                                  && actionBean.p.kodUrusan.kod ne 'KVST' && actionBean.p.kodUrusan.kod ne 'KVSS'
                                  && actionBean.p.kodUrusan.kod ne 'KVLS' && actionBean.p.kodUrusan.kod ne 'GD'
                                  && actionBean.p.kodUrusan.kod ne 'JPGD'
                                  && actionBean.p.kodUrusan.kod ne 'JMGD'
                                  && actionBean.p.kodUrusan.kod ne 'MGGS'
                                  && actionBean.p.kodUrusan.kod ne 'MGG'
                                  && actionBean.p.kodUrusan.kod ne 'JML' && !fn:startsWith(actionBean.p.kodUrusan.kod,'KV')}">Penerima</c:if>
                    <c:if test="${actionBean.p.kodUrusan.kod eq 'GD'}">Pemegang Gadaian</c:if>
                    <c:if test="${actionBean.p.kodUrusan.kod eq 'MGGS'
                                  || actionBean.p.kodUrusan.kod eq 'MGG'}">Pemberi Mortgage</c:if>
                    <c:if test="${actionBean.p.kodUrusan.kod eq 'JPGD' || actionBean.p.kodUrusan.kod eq 'JMGD'
                                  || actionBean.p.kodUrusan.kod eq 'JML'}">Tuan Tanah Baru</c:if>
                    <c:if test="${actionBean.p.kodUrusan.kod eq 'IS' || actionBean.p.kodUrusan.kod eq 'ISBLS'}">Ismen</c:if>                    
                </legend>

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
                        <display:column property="pihak.nama" title="Nama"/>
                        <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                        <display:column property="hakmilik.idHakmilik" title="Hakmilik Terlibat" />
                        <display:column property="jenis.nama" title="Jenis Pihak" />
                        <c:if test="${edit and actionBean.p.kodUrusan.kod ne 'KVLK'
                                      and actionBean.p.kodUrusan.kod ne 'KVPB' and actionBean.p.kodUrusan.kod ne 'KVPT'
                                      and actionBean.p.kodUrusan.kod ne 'KVST' and actionBean.p.kodUrusan.kod ne 'KVSS'
                                      and actionBean.p.kodUrusan.kod ne 'KVLS' and actionBean.p.kodUrusan.kod ne 'IS'
                                      and actionBean.p.kodUrusan.kod ne 'ISBLS'
                                      and actionBean.p.kodUrusan.kod ne 'GDPJK'
                                      and actionBean.p.kodUrusan.kod ne 'MGG'
                                      and actionBean.p.kodUrusan.kod ne 'MGGS' and actionBean.p.kodUrusan.kod ne 'TMAMG'
                              }">
                            <display:column title="Bahagian yang diterima">
                                <div align="center">
                                    <s:text name="syer1[${lineMP_rowNum-1}]" size="5" id="syer1${lineMP_rowNum-1}"
                                            onblur="updateSyer('${lineMP.idPermohonanPihak}', '${lineMP_rowNum-1}')"
                                            onchange="updateSyer('${lineMP.idPermohonanPihak}', '${lineMP_rowNum-1}" class="pembilang"/> /
                                    <s:text name="syer2[${lineMP_rowNum-1}]" size="5" id="syer2${lineMP_rowNum-1}"
                                            onblur="updateSyer('${lineMP.idPermohonanPihak}', '${lineMP_rowNum-1}')"
                                            onchange="updateSyer('${lineMP.idPermohonanPihak}', '${lineMP_rowNum-1}" class="penyebut"/>
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
                            <display:column title="Bahagian yang diterima">${lineMP.syerPembilang}/${lineMP.syerPenyebut}</display:column>
                        </c:if>

                        <c:if test="${edit}">
                            <display:column title="Kemaskini">
                                <p align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                         onclick="editPenerima('${lineMP_rowNum-1}','${actionBean.p.kodUrusan.kod}');return false;" onmouseover="this.style.cursor='pointer';">
                                </p>
                            </display:column>
                        </c:if>
                    </display:table>
                </div>

                <c:if test="${edit}">
                    <p>
                        <label></label>
                        <c:if test="${actionBean.p.kodUrusan.kod ne 'PNPAB'}">
                                <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addNewPB();"/>&nbsp;
                            </c:if>&nbsp;
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
                        <c:if test="${actionBean.p.kodUrusan.kod eq 'PNPAB' && fn:length(actionBean.pihakKepentinganList) > 0}">
                                <s:button class="btn" value="Pemilik Baru" name="semak" id="semak" onclick="doPopupTuanTanah();"/>&nbsp;
                            </c:if>
                        <c:if test="${fn:length(actionBean.mohonPihakList) > 0}">
                            <s:button class="btn" value="Hapus" name="" onclick="removeMultipleMohonPihak();"/>&nbsp;
                        </c:if>
                    </p>
                </c:if>
            </fieldset>
        </div>
    </c:if>
</s:form>