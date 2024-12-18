<%-- 
    Document   : kemasukan_pihak_mcl
    Created on : Dec 12, 2012, 04:57:31 AM
    Author     : Aizuddin
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function dummy(){
        $('#idMohonSebelum').val('');
    }
    
    // Clear a single form
    function reset (form){
        var elements = form.getElementsByTagName('*idMohonSebelum');
        for (var i = 0, len = elements.length; i < len; i++){
            if ((elements[i].tagName == 'input' && elements[i].type && elements[i].type == 'text') || elements[i].tagName == 'textarea'){
                elements[i].value = '';
            }
        }  
    }
    
    function reset2 (form){
        var elements = form.getElementsByTagName('*idBatal');
        for (var i = 0, len = elements.length; i < len; i++){
            if ((elements[i].tagName == 'input' && elements[i].type && elements[i].type == 'text') || elements[i].tagName == 'textarea'){
                elements[i].value = '';
            }
        }  
        
        var elements = form.getElementsByTagName('*sebabPembatalan');
        for (var i = 0, len = elements.length; i < len; i++){
            if ((elements[i].tagName == 'input' && elements[i].type && elements[i].type == 'text') || elements[i].tagName == 'textarea'){
                elements[i].value = '';
            }
        }  
    }
    
    function reload()
    { url = "${pageContext.request.contextPath}/daftar/pihak_kepentingan?showFormMCL";
        $.post(url,
        function(data){
            $('#page_div').html(data);
        },'html');

    }
    
    var DELIM = "__^$__";

    function doEdit(d, j, d1, kU){        
        var idHakmilik = $('#hakmilik').val();
        window.open("${pageContext.request.contextPath}/daftar/pihak_kepentingan?showEditPemohonPenerimaForm&jenis_pihak="
            +j+"&id="+d + "&idPihak=" +d1 + '&idHakmilik=' + idHakmilik + '&kodUrusan=' +kU, "eTanah",
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
            alert('Tiada pemohon.');
            doUnBlockUI();
            return;
        }
        var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?' +action+param + '&jenisPihak=' + jenis + '&idHakmilik=' + idHakmilik;
        
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
        var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?showSearchFormMCL&idHakmilik=' + idHakmilik;
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
        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'SMB' && actionBean.permohonan.kodUrusan.kod ne 'SMK' && actionBean.permohonan.kodUrusan.kod ne 'SMBT'}">
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
                                <display:column property="jenis.nama" title="Jenis Pihak"/>
                            </display:table>
                        </c:when>
                        <c:otherwise>
                            <display:table style="width:90%;" class="tablecloth" name="${actionBean.senaraiKeempunyaan}"
                                           cellpadding="0" cellspacing="0" id="line">

                                <c:if test="${edit && actionBean.permohonan.kodUrusan.kod ne 'KVST'
                                              && actionBean.permohonan.kodUrusan.kod ne 'KVLT'
                                              && actionBean.permohonan.kodUrusan.kod ne 'KVPT'}">
                                    <display:column title="<input type='checkbox' id='semua' name='semua' onclick='javascript:selectAll(this,\"cb_\");'/>">
                                        <s:checkbox name="checkbox" id="cb_${line_rowNum-1}" value="${line.pihak.idPihak}-${line.jenis.kod}-${line.syerPembilang}-${line.syerPenyebut}" class="cb_"/>
                                    </display:column>
                                </c:if>
                                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                <display:column  title="Nama" property="pihak.nama" class="nama"/>
                                <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                                <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                                <display:column property="perserahan.idPerserahan" title="ID Perserahan" />
                                <display:column property="jenis.nama" title="Jenis Pihak"/>
                                <display:column property="pihak.nama" title="Status MCL"/>
                            </display:table>
                        </c:otherwise>
                    </c:choose>
                </p>
                <br/>
                <c:if test="${edit && ( fn:length(actionBean.senaraiKeempunyaan) > 0
                              || fn:length(actionBean.senaraiPermohonanPihakBerangkai) > 0 ) }">
                      <p align="center">
                          <label></label>
                          <s:button name="add" onclick="addNew(this.name);" value="Simpan" class="btn"/>
                      </p>
                </c:if>
            </fieldset>
        </c:if>
        <%--        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SMK' || actionBean.permohonan.kodUrusan.kod eq 'SMBT'}">
                    <p>
                        <label><font color="red" class="idMohonSebelum">*</font>No Syarikat MCL :</label>
                        <s:text name="idMohonSebelum" id="idMohonSebelum"/>
                    </p>
                    <br/>
                    <p>
                        <label>
                            <s:button name="reload" value="Isi Semula" class="btn" onclick="reset();"/>&nbsp;
                        </label>
                        <s:button name="cariPihakMCL" value="Kemaskini" class="btn" onclick="doSubmit(this.form,this.name,'page_div');"/>&nbsp;
                           <s:button name="cariPihakMCL" class="btn" value="Kemaskini" onclick="cari(this.name, this.form);"/>&nbsp;

            </p>
            <br/>
        </c:if>--%>
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SMBT'}">
            <%-- <p>
                 <label><font color="red" class="idBatal">*</font>No Syarikat MCL Terlibat:</label>
                 <s:text name="idBatal" id="idBatal"/>
             </p>--%>
            <p>
                <label>Sebab Pembatalan :</label> 
                <s:textarea name="sebabPembatalan" id="sebabPembatalan" rows="4" cols="40"/>
            </p>   
            <br/>
            <p align="center">
                <s:button name="batalPihakMCL" value="Simpan" class="btn" onclick="doSubmit(this.form,this.name,'page_div');"/>&nbsp;
                <s:button name="reload" value="Isi Semula" class="btn" onclick="reset2();"/>&nbsp;
                <%--   <s:button name="cariPihakMCL" class="btn" value="Kemaskini" onclick="cari(this.name, this.form);"/>&nbsp; --%>

            </p>
            <br/>
        </c:if>
        <br/>
        <br/>        
        <fieldset class="aras1">
            <legend>Senarai Syarikat MCL</legend>
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
                    <display:column title="Nama Syarikat" property="nama" class="remove"/>
                    <display:column title="No Syarikat" property="noPengenalan"/>
                    <display:column title="Alamat Surat Menyurat">
                        ${line2.alamatSurat.alamatSurat1}

                        ${line2.alamatSurat.alamatSurat2}

                        ${line2.alamatSurat.alamatSurat3}

                        ${line2.alamatSurat.alamatSurat4}

                        ${line2.alamatSurat.poskodSurat}                        

                        ${line2.alamatSurat.negeriSurat.nama}
                    </display:column>
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
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SMB'}">
                <c:if test="${edit}">

                    <p align="center">
                        <label></label>
                        <c:if test="${fn:length(actionBean.senaraiPermohonanPihak) > 0}">                       
                            <s:button name="delete" onclick="remove2(this.name, 'remove', 'penerima', 'rm_mp');" value="Hapus" class="btn"/>&nbsp;
                        </c:if>
                        <s:button name="add" onclick="doOpen();" value="Tambah" class="btn"/>
                    </p>
                </c:if>
            </c:if>
        </fieldset>
    </s:form>    
</div>
