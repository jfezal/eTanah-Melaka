<%-- 
    Document   : kemasukan_pihak_mati
    Created on : Apr 1, 2010, 12:39:39 PM
    Author     : muhammad.amin
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
        window.open("${pageContext.request.contextPath}/consent/pihak_turun_milik?pihakKepentinganPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=700,scrollbars=yes");
    }

    function addNewPemohon(){
        window.open("${pageContext.request.contextPath}/consent/pihak_turun_milik?pemohonPopup", "eTanah",
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

   

    function removePemohon(val){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/consent/pihak_turun_milik?deletePemohon&idPemohon='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');

        }
    }

    function removeChanges(val){
        var answer = confirm("adakah anda pasti untuk Hapus?");
        if (answer){
            var url = '${pageContext.request.contextPath}/consent/pihak_turun_milik?deleteChanges&idKkini='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            });
        }
    }

    function addSiMati(){
        var len = $('.nama').length;
        for(var i=1; i<=len; i++){
            var ckd = $('#chkbox'+i).is(":checked");
            if(ckd){
                var url = '${pageContext.request.contextPath}/consent/pihak_turun_milik?simpanSiMati&idPihak='+$('#chkbox'+i).val();
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                });
            }
        }
    }

    function remove(val){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/common/mohon_pihak?deletePenerimaTurunMilik&idPermohonanPihak='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
            refreshPageSiMati();
        }
    }

    function removeSiMati(val){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/common/mohon_pihak?deleteSiMati&idPihak='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);

            },'html');
            refreshPageSiMati();
        }
    }

    function editSiMati(i){
        var d = $('.mati'+i).val();
        window.open("${pageContext.request.contextPath}/consent/pihak_turun_milik?showEditSiMati&idPihak="+d, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
    }

    function refreshPageSiMati(){
        var url = '${pageContext.request.contextPath}/consent/pihak_turun_milik?getSenaraiHakmilikKepentingan';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function semakSyer(f){
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/consent/pihak_turun_milik?semakSyer',q,
        function(data){
            if(data != ''){
                alert(data);
            }
        }, 'html');
    }

    function dopopup(i,kod){
     
        var d = $('.x'+i).val();
        window.open("${pageContext.request.contextPath}/consent/pihak_turun_milik?showEditPemohon&idPihak="+d, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
    }

    function editPenerima(i,kod){
        var d = $('.a'+i).val();
        window.open("${pageContext.request.contextPath}/consent/pihak_turun_milik?showEditPenerima&idPihak="+d, "eTanah",
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
        var url = '${pageContext.request.contextPath}/consent/pihak_turun_milik?updateSyerMohonPihak&idpihak='+idpihak
            + '&syer1=' + s1 + '&syer2=' + s2;
        $.post( url,
        function(data){
        }, 'html');

    }

</script>
<div class="subtitle displaytag">
    <s:messages/>
    <s:errors/>
    <s:form beanclass="etanah.view.stripes.consent.PihakTurunMilikActionBean">

        <fieldset class="aras1">
            <legend>Senarai Tuan Tanah</legend>
            <div class="content" align="center">
                <c:if test="${edit}">
                    <font color="red" size="2"> Sila Klik pada kotak dan tekan Pilih untuk memilih Si Mati</font><br><br>
                </c:if>
                <display:table class="tablecloth" name="${actionBean.pihakKepentinganList}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                    <c:if test="${edit}">
                        <display:column title="Pilih">
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

            </div>
            <c:if test="${fn:length(actionBean.pihakKepentinganList) > 0}">
                <c:if test="${edit}">
                    <p>
                        <label>&nbsp;</label>
                        <s:button class="btn" value="Pilih" name="pilih" id="pilih" onclick="addSiMati();"/>&nbsp;
                    </p>
                </c:if>
            </c:if>
        </fieldset>
        <br/>

        <fieldset class="aras1">
            <legend>
                Senarai Si Mati
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.matiList}" cellpadding="0" cellspacing="0" id="lineMati">
                    <display:column title="Bil" sortable="true">${lineMati_rowNum}
                        <s:hidden name="mati" class="mati${lineMati_rowNum-1}" value="${lineMati.idPihak}"/>
                    </display:column>
                    <display:column property="nama" title="Nama"/>
                    <display:column property="noPengenalan" title="Nombor Pengenalan" />
                    <display:column title="Tarikh Meninggal">
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${lineMati.tarikhMati}"/>
                    </display:column>
                    <display:column property="noSijilMati" title="Nombor Sijil Mati" />
                    <c:if test="${edit}">
                        <display:column title="Kemaskini">
                            <p align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                     onclick="editSiMati('${lineMati_rowNum-1}');return false;" onmouseover="this.style.cursor='pointer';">
                            </p>
                        </display:column>
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem${lineMati_rowNum}' onclick="removeSiMati('${lineMati.idPihak}')" onmouseover="this.style.cursor='pointer';">
                            </div>
                        </display:column>
                    </c:if>
                </display:table>
            </div>
        </fieldset>
        <br/>
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
                        <display:column title="Kemaskini">
                            <p align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                     onclick="dopopup('${line_rowNum -1}');return false;" onmouseover="this.style.cursor='pointer';">
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

        <div id="mohon_pihak">
            <fieldset class="aras1">
                <legend>
                    Senarai Penerima
                </legend>

                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.mohonPihakList}" cellpadding="0" cellspacing="0" id="lineMP">
                        <display:column title="Bil" sortable="true">${lineMP_rowNum}
                            <s:hidden name="a" class="a${lineMP_rowNum-1}" value="${lineMP.pihak.idPihak}"/>
                        </display:column>
                        <display:column property="pihak.nama" title="Nama"/>
                        <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />

                        <display:column title="Bahagian yang diterima">
                            <div align="center">
                                <s:text name="syer1[${lineMP_rowNum-1}]" size="5" id="syer1${lineMP_rowNum-1}" onblur="updateSyer('${lineMP.idPermohonanPihak}', '${lineMP_rowNum-1}')"/> /
                                <s:text name="syer2[${lineMP_rowNum-1}]" size="5" id="syer2${lineMP_rowNum-1}" onblur="updateSyer('${lineMP.idPermohonanPihak}', '${lineMP_rowNum-1}')"/>
                            </div>
                        </display:column>

                        <c:if test="${edit}">
                            <display:column title="Kemaskini">
                                <p align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                         onclick="editPenerima('${lineMP_rowNum-1}');return false;" onmouseover="this.style.cursor='pointer';">
                                </p>
                            </display:column>
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem${lineMP_rowNum}' onclick="remove('${lineMP.idPermohonanPihak}')" onmouseover="this.style.cursor='pointer';">
                                </div>
                            </display:column>
                        </c:if>
                    </display:table>
                </div>

                <c:if test="${edit}">
                    <p>
                        <label>&nbsp;</label>
                        <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addNewPB();"/>&nbsp;
                        <c:if test="${fn:length(actionBean.mohonPihakList) > 0}">
                            <s:button class="btn" value="Semak Syer" name="semak" id="semak" onclick="semakSyer(this.form);"/>&nbsp;
                        </c:if>

                    </p>
                </c:if>
            </fieldset>
        </div>
    </s:form>
</div>