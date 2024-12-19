<%--
    Document   : kemasukan_pihak_main
    Created on : Nov 5, 2010, 11:54:31 AM
    Author     : fikri
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    var DELIM = "__^$__";

    function doEdit(d, j, d1,kod){
        var idHakmilik = $('#hakmilik').val();
        window.open("${pageContext.request.contextPath}/daftar/pihak_kepentingan?showEditPemohonPenerimaForm&jenis_pihak="
            +j+"&id="+d + "&idPihak=" +d1 + '&idHakmilik=' + idHakmilik + '&kodUrusan=' +kod, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes");
    }

    function addNew(action){
        var idHakmilik = $('#hakmilik').val();
        var len = $('.nama').length;
        var param = '';
        doBlockUI();

        for(var i=0; i<len; i++){

            var ckd = $('#cb_'+i).is(":checked");
            if(ckd){
                param = param + '&uids=' + $('#cb_'+i).val();
            }
        }

        if(param == ''){
            alert('Tiada pemohon.');
            doUnBlockUI();
            return;
        }

        var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?'
            +action+param+'&jenisPihak=pemohon&idHakmilik=' + idHakmilik;

        if ($('#copyToAll').is(':checked')) {
            url = url + '&copyToAll=true';
        }

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


    function remove2(action, cn, jenis, id){       
        var idHakmilik = $('#hakmilik').val();
        var len = $('.' + cn).length;
        var param = '';
        doBlockUI();

        for(var i=0; i<len; i++){

            var ckd = $('#' + id +'_'+i).is(":checked");
            if(ckd){
                param = param + '&uids=' + $('#' + id +'_'+i).val();
            }
        }

        if(param == ''){
            alert('Tiada ' + jenis);
            doUnBlockUI();
            return;
        }
        var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?' +action+param + '&jenisPihak=' + jenis + '&idHakmilik=' + idHakmilik;

        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
                // doPopupMsg('icons/alertIcon.png',"Kemaskini gagal!");
            },
            success : function(data){
                $('#page_div').html(data);
                //doPopupMsg('icons/KnobValidGreen.png','Kemaskini Data Berjaya');
                doUnBlockUI();
            }
        });
    }

    function reload (val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?reload&idHakmilik=' + val;
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

    function doOpen() {
        var idHakmilik = $('#hakmilik').val();
        var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?showSearchForm&idHakmilik=' + idHakmilik;
        window.open(url,  "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes");
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
        var url = '${pageContext.request.contextPath}/pihak_berkepentingan?updateSyerMohonPihak&idpihak='+idpihak + '&idHakmilik=' +  $('#hakmilik').val()
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

    function samaRata(f){
        var q = $(f).formSerialize();
        $.get('${pageContext.request.contextPath}/daftar/pihak_kepentingan?agihSamaRata&hakmilik='+$('#hakmilik').val(),q,
        function(data){
    <%-- if( data == null || data.length == 0) {
         alert('Terdapat Masalah');
         return;
     }
     var p = data.split(DELIM);
     $('.pembilang').each(function(){
         $(this).val(p[0]);
     });
     $('.penyebut').each(function(){
         $(this).val(p[1]);
     });--%>
                 $('#page_div').html(data);
             });
         }

         function selectAll(a, clazz){
             var len = $('.' + clazz).length;
             for (var i=0; i<len; i++) {
                 var id = clazz + i;
                 var c = document.getElementById(id);
                 c.checked = a.checked;
             }
         }

</script>


<div class="subtitle displaytag">
    <s:messages/>
    <s:errors/>
    <s:form beanclass="etanah.view.daftar.PihakKepentinganAction" name="form1">
        <fieldset class="aras1">
            <legend>Senarai Hakmilik Terlibat</legend>
            <c:if test="${!empty moreThanOneHakmilik}">
                <p>
                    <label></label>&nbsp;
                    <font color="red" size="2">
                        <input type="checkbox" name="copyToAll" value="1" id="copyToAll"/>
                        <b><em>Sila klik jika sama untuk semua hakmilik.</em></b>
                    </font>
                </p>
            </c:if>
            <p>
                <label>Hakmilik :</label>
                <s:select name="idHakmilik" onchange="reload(this.value);" id="hakmilik">
                    <c:forEach items="${actionBean.senaraiHakmilikTerlibat}" var="item" varStatus="line">
                        <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                            ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                        </s:option>
                    </c:forEach>
                </s:select>
            </p>
            <br/>
        </fieldset>
        <br/>
        <fieldset class="aras1">
            <legend>
                <c:choose>
                    <c:when test="${bukan_tuan_tanah}">
                        Senarai Pihak Berkepentingan
                    </c:when>
                    <c:otherwise>
                        Senarai Pemilik
                    </c:otherwise>
                </c:choose>

            </legend>
            <p align="center">
                <c:choose>
                    <c:when test="${isBerangkai}">
                        <display:table style="width:90%;" class="tablecloth" name="${actionBean.senaraiPermohonanPihakBerangkai}"
                                       cellpadding="0" cellspacing="0" id="line">
                                <c:if test="${edit && actionBean.permohonan.kodUrusan.kod ne 'KVST'
                          && actionBean.permohonan.kodUrusan.kod ne 'KVLT'
                          && actionBean.permohonan.kodUrusan.kod ne 'KVPT'}">
                                    <display:column title="<input type='checkbox' id='semua' name='semua' onclick='javascript:selectAll(this,\"cb_\");'/>">
                                        <s:checkbox name="checkbox" id="cb_${line_rowNum-1}"
                                                    value="${line.pihak.idPihak}-${line.jenis.kod}-${line.syerPembilang}-${line.syerPenyebut}-${line.noRujukan}" class="cb_"/>
                                    </display:column>
                                </c:if>
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column property="pihak.nama" title="Nama" class="nama"/>
                            <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                            <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                            <display:column property="permohonan.idPermohonan" title="ID Perserahan" />
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'JMGPJ' && no_syer ne 'true'}">
                                <display:column title="Bahagian yang dimiliki" >
                                <div align="center">
                                    ${line.syerPembilang}/${line.syerPenyebut}
                                </div>
                            </display:column>
                        </c:if>
                        <display:column property="jenis.nama" title="Jenis Pihak"/>
                    </display:table>
                </c:when>
                <c:otherwise>
                    <display:table style="width:90%;" class="tablecloth" name="${actionBean.senaraiKeempunyaan}"
                                   cellpadding="0" cellspacing="0" id="line">

                        <c:if test="${edit}">
                            <display:column title="<input type='checkbox' id='semua' name='semua' onclick='javascript:selectAll(this,\"cb_\");'/>">
                                <s:checkbox name="checkbox" id="cb_${line_rowNum-1}" value="${line.pihak.idPihak}-${line.jenis.kod}-${line.syerPembilang}-${line.syerPenyebut}- " class="cb_"/>
                            </display:column>
                        </c:if>
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column  title="Nama" property="pihak.nama" class="nama"/>
                        <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                        <display:column property="perserahan.idPerserahan" title="ID Perserahan" />
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'JMGPJ' && no_syer ne 'true'}">
                            <display:column title="Bahagian yang dimiliki" >
                                <div align="center">
                                    ${line.syerPembilang}/${line.syerPenyebut}
                                </div>
                            </display:column>
                        </c:if>
                        <display:column property="jenis.nama" title="Jenis Pihak"/>
                    </display:table>
                </c:otherwise>
            </c:choose>
            </p>
            <br/>
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'KVAT'
                          && actionBean.permohonan.kodUrusan.kod ne 'KVST'
                          && actionBean.permohonan.kodUrusan.kod ne 'KVLT'
                          && actionBean.permohonan.kodUrusan.kod ne 'KVPT'}">
                <c:if test="${edit && ( fn:length(actionBean.senaraiKeempunyaan) > 0
                              || fn:length(actionBean.senaraiPermohonanPihakBerangkai) > 0 ) }">
                    <p align="center">
                        <label></label>
                        <s:button name="add" onclick="addNew(this.name);" value="Simpan" class="btn"/>
                    </p>
                </c:if>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'KVAT'}">
                <c:if test="${edit && ( fn:length(actionBean.senaraiKeempunyaan) > 0
                              || fn:length(actionBean.senaraiPermohonanPihakBerangkai) > 0 ) }">
                    <p align="center">
                        <label></label>
                        <s:button name="addPP" onclick="addNew(this.name);" value="Simpan" class="btn"/>
                    </p>
                </c:if>
            </c:if>
        </fieldset>
        <br/>
        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'KVAT'
                 && actionBean.permohonan.kodUrusan.kod ne 'KVST'
                 && actionBean.permohonan.kodUrusan.kod ne 'KVLT'
                 && actionBean.permohonan.kodUrusan.kod ne 'KVPT'
                 && actionBean.permohonan.kodUrusan.kod ne 'PHMMT'}">
            <fieldset class="aras1">
                <legend>Senarai Pihak Terlibat</legend>
                <div class="content" align="center">

                    <c:choose>
                        <c:when test="${fn:length(actionBean.senaraiPermohonanAtasPihak) > 0}">
                            <display:table style="width:80%;" class="tablecloth" name="${actionBean.senaraiPermohonanAtasPihak}"
                                           cellpadding="0" cellspacing="0" id="line1">
                                <c:if test="${edit}">
                                    <display:column title="<input type='checkbox' id='semua_rm' name='semua_rm' onclick='javascript:selectAll(this,\"rm_\");'/>">
                                        <s:checkbox name="checkbox" id="rm_${line1_rowNum-1}" value="${line1.idAtasPihak}" class="rm_"/>
                                    </display:column>
                                </c:if>
                                <display:column title="Bil">${line1_rowNum}</display:column>
                                <display:column title="Nama Pihak" property="pihakBerkepentingan.pihak.nama" class="remove"/>
                                <display:column title="Alamat Surat Menyurat">
                                    ${line1.pihakBerkepentingan.pihak.suratAlamat1}
                                    <c:if test="${line1.pihakBerkepentingan.pihak.suratAlamat1 ne null && line1.pihakBerkepentingan.pihak.suratAlamat2 ne null}"> , </c:if>
                                    ${line1.pihakBerkepentingan.pihak.suratAlamat2}
                                    <c:if test="${line1.pihakBerkepentingan.pihak.suratAlamat2 ne null && line1.pihakBerkepentingan.pihak.suratAlamat3 ne null}"> , </c:if>
                                    ${line1.pihakBerkepentingan.pihak.suratAlamat3}
                                    <c:if test="${line1.pihakBerkepentingan.pihak.suratAlamat3 ne null && line1.pihakBerkepentingan.pihak.suratAlamat4 ne null}"> , </c:if>
                                    ${line1.pihakBerkepentingan.pihak.suratAlamat4}
                                    <c:if test="${line1.pihakBerkepentingan.pihak.suratAlamat4 ne null && line1.pihakBerkepentingan.pihak.suratPoskod ne null}">,</c:if>
                                    ${line1.pihakBerkepentingan.pihak.suratPoskod}
                                    <c:if test="${line1.pihakBerkepentingan.pihak.suratPoskod ne null && line1.pihakBerkepentingan.pihak.suratNegeri.kod ne null}">,</c:if>
                                    ${line1.pihakBerkepentingan.pihak.suratNegeri.nama}
                                </display:column>
                                <c:if test="${edit}">
                                    <display:column title="Kemaskini">
                                        <p align="center">
                                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                 onclick="doEdit('${line1.idAtasPihak}', 'pemohon','${line1.pihakBerkepentingan.pihak.idPihak}','${actionBean.permohonan.kodUrusan.kod}');return false;" onmouseover="this.style.cursor='pointer';">
                                        </p>
                                    </display:column>
                                </c:if>

                            </display:table>
                        </c:when>
                        <c:otherwise>
                            <display:table style="width:90%;" class="tablecloth" name="${actionBean.senaraiPemohon}"
                                           cellpadding="0" cellspacing="0" id="line1">
                                <c:if test="${edit}">
                                    <display:column title="<input type='checkbox' id='semua_rm' name='semua_rm' onclick='javascript:selectAll(this,\"rm_\");'/>">
                                        <div align="center">
                                            <s:checkbox name="checkbox" id="rm_${line1_rowNum-1}" value="${line1.idPemohon}" class="rm_"/>
                                        </div>
                                    </display:column>
                                </c:if>
                                <display:column title="Bil">${line1_rowNum}</display:column>
                                <display:column title="Nama Pihak" property="pihak.nama" class="remove"/>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'JMGPJ' && no_syer ne 'true'}">
                                    <display:column title="Bahagian Terlibat">
                                        <div align="center">
                                            ${line1.syerPembilang}/${line1.syerPenyebut}
                                        </div>
                                    </display:column>
                                    <display:column property="jenis.nama" title="Jenis Pihak"/>
                                </c:if>
                                <c:if test="${edit}">
                                    <display:column title="Kemaskini">
                                        <p align="center">
                                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                 onclick="doEdit('${line1.idPemohon}', 'pemohon','${line1.pihak.idPihak}','${actionBean.permohonan.kodUrusan.kod}');return false;" onmouseover="this.style.cursor='pointer';">
                                        </p>
                                    </display:column>
                                </c:if>
                            </display:table>

                        </c:otherwise>
                    </c:choose>
                </div>
                <c:if test="${edit && (fn:length(actionBean.senaraiPemohon) > 0 || fn:length(actionBean.senaraiPermohonanAtasPihak) > 0)}">
                    <p align="center">
                        <label></label>
                        <s:button name="delete" onclick="remove2(this.name, 'remove', 'pemohon', 'rm');" value="Hapus" class="btn"/>
                    </p>
                </c:if>
            </fieldset>
        </c:if>
        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'GDCE'}">
        <br/>
        <fieldset class="aras1">
            <c:if test="${fn:startsWith(actionBean.permohonan.kodUrusan.kod,'KV')}">
                <legend>Senarai Kaveator</legend>
            </c:if>
            <c:if test="${!fn:startsWith(actionBean.permohonan.kodUrusan.kod,'KV')}">
                <legend>Senarai Pihak Baru Terlibat</legend>
            </c:if>
            <div class="content" align="center">
                <display:table style="width:90%;" class="tablecloth" name="${actionBean.senaraiPermohonanPihak}"
                               cellpadding="0" cellspacing="0" id="line2">
                    <c:if test="${edit}">
                        <display:column title="<input type='checkbox' id='semua_rm_mp' name='semua_rm_mp' onclick='javascript:selectAll(this,\"rm_mp_\");'/>">
                            <div align="center">
                                <s:checkbox name="checkbox" id="rm_mp_${line2_rowNum-1}" value="${line2.idPermohonanPihak}" class="rm_mp_"/>
                            </div>
                        </display:column>
                    </c:if>
                    <display:column title="Bil">${line2_rowNum}</display:column>
                    <display:column title="Nama Pihak" property="pihak.nama" class="remove"/>
                    <display:column title="No Pengenalan" property="pihak.noPengenalan"/>
                    <display:column title="Jenis Pihak" property="jenis.nama"/>
                    <display:column title="Alamat Surat Menyurat">
                        ${line2.pihak.suratAlamat1}

                        ${line2.pihak.suratAlamat2}

                        ${line2.pihak.suratAlamat3}

                        ${line2.pihak.suratPoskod}

                        ${line2.pihak.suratAlamat4}

                        ${line2.pihak.suratNegeri.nama}
                    </display:column>
                    <c:set value="Bahagian Terlibat" var="title"/>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBGAA'}">
                        <c:set value="Bahagian Bersama" var="title"/>
                    </c:if>
                    <%--|| actionBean.permohonan.kodUrusan.kod eq 'KVAT'-- tutup JKPTG Melaka FAT sessi 7
                    || actionBean.permohonan.kodUrusan.kod eq 'KVLT' || actionBean.permohonan.kodUrusan.kod eq 'KVPT'
                    || actionBean.permohonan.kodUrusan.kod eq 'KVST' || actionBean.permohonan.kodUrusan.kod eq 'PHMMT'
                    --%>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMT'
                                  || actionBean.permohonan.kodUrusan.kod eq 'PMP'
                                  || actionBean.permohonan.kodUrusan.kod eq 'PMTM'
                                  || actionBean.permohonan.kodUrusan.kod eq 'PNPA'
                                  || actionBean.permohonan.kodUrusan.kod eq 'PNPAB'
                                  || actionBean.permohonan.kodUrusan.kod eq 'PJT'
                                  || actionBean.permohonan.kodUrusan.kod eq 'PJTM'
                                  || actionBean.permohonan.kodUrusan.kod eq 'TA'
                                  || actionBean.permohonan.kodUrusan.kod eq 'TENBT'
                                  || actionBean.permohonan.kodUrusan.kod eq 'IS'
                                  || actionBean.permohonan.kodUrusan.kod eq 'ISBLS'
                                  || actionBean.permohonan.kodUrusan.kod eq 'KVAB'
                                  || actionBean.permohonan.kodUrusan.kod eq 'KVAS'
                                  || actionBean.permohonan.kodUrusan.kod eq 'KVLS'
                                  || actionBean.permohonan.kodUrusan.kod eq 'KVLTB'
                                  || actionBean.permohonan.kodUrusan.kod eq 'KVPB'
                                  || actionBean.permohonan.kodUrusan.kod eq 'KVPK'
                                  || actionBean.permohonan.kodUrusan.kod eq 'KVPPT'
                                  || actionBean.permohonan.kodUrusan.kod eq 'KVPS'
                                  || actionBean.permohonan.kodUrusan.kod eq 'JAGAB'
                                  || actionBean.permohonan.kodUrusan.kod eq 'JDGD'
                                  || actionBean.permohonan.kodUrusan.kod eq 'JDS'
                                  || actionBean.permohonan.kodUrusan.kod eq 'JMGD'
                                  || actionBean.permohonan.kodUrusan.kod eq 'JPGD'
                                  || actionBean.permohonan.kodUrusan.kod eq 'JPGPJ'
                                  || actionBean.permohonan.kodUrusan.kod eq 'JML'
                                  || actionBean.permohonan.kodUrusan.kod eq 'PMHUK'
                                  || actionBean.permohonan.kodUrusan.kod eq 'PMHUN'
                                  || actionBean.permohonan.kodUrusan.kod eq 'PMKMH'
                                  || actionBean.permohonan.kodUrusan.kod eq 'GD'
                                  || actionBean.permohonan.kodUrusan.kod eq 'GDCE'
                                  || actionBean.permohonan.kodUrusan.kod eq 'PPBM'
                                  || actionBean.permohonan.kodUrusan.kod eq 'PPUH'
                                  || actionBean.permohonan.kodUrusan.kod eq 'PPUHB'
                                  || actionBean.permohonan.kodUrusan.kod eq 'PJBT'
                                  || actionBean.permohonan.kodUrusan.kod eq 'PJKBT'
                                  || actionBean.permohonan.kodUrusan.kod eq 'TMAMD'
                                  || actionBean.permohonan.kodUrusan.kod eq 'TMAME'
                                  || actionBean.permohonan.kodUrusan.kod eq 'TMAMF'
                                  || actionBean.permohonan.kodUrusan.kod eq 'TMAMG'
                                  || actionBean.permohonan.kodUrusan.kod eq 'TMAML'
                                  || actionBean.permohonan.kodUrusan.kod eq 'TMAMT'
                                  || actionBean.permohonan.kodUrusan.kod eq 'TMAMW'
                                  || actionBean.permohonan.kodUrusan.kod eq 'TN'
                                  || actionBean.permohonan.kodUrusan.kod eq 'TRPA'
                                  || actionBean.permohonan.kodUrusan.kod eq 'PNPHB'
                          }">
                        <display:column title="${title}" style="width:150px">
                            <div align="center">
                                <s:text name="senaraiPermohonanPihak[${line2_rowNum-1}].syerPembilang" id="syer1${line2_rowNum-1}"
                                        onblur="updateSyer('${line2.idPermohonanPihak}', '${line2_rowNum-1}')" class="pembilang"
                                        onchange="updateSyer('${line2.idPermohonanPihak}', '${line2_rowNum-1}')" size="5"/> /
                                <s:text name="senaraiPermohonanPihak[${line2_rowNum-1}].syerPenyebut"
                                        id="syer2${line2_rowNum-1}"
                                        onblur="updateSyer('${line2.idPermohonanPihak}', '${line2_rowNum-1}')"
                                        onchange="updateSyer('${line2.idPermohonanPihak}', '${line2_rowNum-1}')" class="penyebut"
                                        size="5"/>
                            </div>
                        </display:column>
                    </c:if>
                    <c:if test="${edit}">
                        <display:column title="Kemaskini">
                            <p align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                     onclick="doEdit('${line2.idPermohonanPihak}', 'penerima','${line2.pihak.idPihak}','${actionBean.permohonan.kodUrusan.kod}');return false;"
                                     onmouseover="this.style.cursor='pointer';">
                            </p>
                        </display:column>
                    </c:if>
                </display:table>
            </div>

            <c:if test="${edit}">

                <p align="center">
                    <label></label>
                    <c:if test="${fn:length(actionBean.senaraiPermohonanPihak) > 0}">

                        <%--<s:button class="btn" value="Semak Syer" name="semak" id="semak" onclick="semakSyer(this.form);"/>&nbsp;--%>
                        <%--<s:button class="longbtn" value="Agih Sama Rata" name="agihSamaRata" onclick="samaRata(this.form);"/>&nbsp;--%>

                        <s:button name="delete" onclick="remove2(this.name, 'remove', 'penerima', 'rm_mp');" value="Hapus" class="btn"/>&nbsp;
                    </c:if>
                    <s:button name="add" onclick="doOpen();" value="Tambah" class="btn"/>
                </p>

            </c:if>

        </fieldset>
        </c:if>
        <%-- Data migration tiada pihak pemugut duti harta pesaka negeri2 tanah melayu--%>
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'GDCE'}">
            <fieldset class="aras1">
                <legend>Senarai Pihak Baru Terlibat</legend>
                <br/>
                <br/>
                PEMUNGUT DUTI HARTA PESAKA NEGERI-NEGERI TANAH MELAYU
            </fieldset>
        </c:if>
    </s:form>
</div>
