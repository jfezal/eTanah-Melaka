<%-- 
    Document   : pihak_berkepentingan_partial
    Created on : Jul 19, 2010, 1:42:50 PM
    Author     : muhammad.amin
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    var DELIM = "__^$__";

    $(document).ready( function(){

    <%--     if($('input[name=bukanPemohon]').is(':checked')){
             $('#ttBukanPemohon').hide();
         }else{
             $('#ttBukanPemohon').show();
         }--%>
             });

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
                 var url = '${pageContext.request.contextPath}/consent/pihak_consent?simpanPemohonMultipleHakmilik'+param+'&hakmilik='+ $('#hakmilik').val();

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
                 if(confirm('Adakah anda pasti?')) {
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
                     var url = '${pageContext.request.contextPath}/consent/pihak_consent?deleteSelectedPemohon'+param+'&hakmilik='+ $('#hakmilik').val();
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
                 window.open("${pageContext.request.contextPath}/consent/pihak_consent?pihakKepentinganPopup&hakmilik=" + $('#hakmilik').val(), "eTanah",
                 "status=0,toolbar=0,location=0,menubar=0,width=910,height=700,scrollbars=yes");
             }

             function addNewPemohon(){
                 window.open("${pageContext.request.contextPath}/consent/pihak_consent?pemohonPopup&hakmilik=" + $('#hakmilik').val(), "eTanah",
                 "status=0,toolbar=0,location=0,menubar=0,width=910,height=700,scrollbars=yes");
             }

             function viewPihak(id, jenis){
                 window.open("${pageContext.request.contextPath}/consent/pihak_consent?viewPihakDetail&idPihak="+id+"&jenis="+jenis+"&idHakmilik=" + $('#hakmilik').val(), "eTanah",
                 "status=0,toolbar=0,location=0,menubar=0,width=900,height=670,scrollbars=yes");
             }

             function samaRata(f, value){
                 var q = $(f).formSerialize();
                 $.get('${pageContext.request.contextPath}/consent/pihak_consent?agihSamaRata&jenis='+value+'&hakmilik='+ $('#hakmilik').val(),q,
                 function(data){
                     if( data == null || data.length == 0) {
                         alert('Terdapat Masalah');
                         return;
                     }
                     var p = data.split(DELIM);

                     if(value == "penerima"){
                         $('.pembilang').each(function(){
                             $(this).val(p[0]);
                         });
                         $('.penyebut').each(function(){
                             $(this).val(p[1]);
                         });
                     }

                     else if(value == "gadaian"){
                         $('.pembilang2').each(function(){
                             $(this).val(p[0]);
                         });
                         $('.penyebut2').each(function(){
                             $(this).val(p[1]);
                         });
                     }

                 });
             }

             function editSimati(i, value){
                 id = $('.yy'+i).val();
                 window.open("${pageContext.request.contextPath}/consent/pihak_consent?showEditSimati&idPihak="+id, "eTanah",
                 "status=0,toolbar=0,location=0,menubar=0,width=890,height=700,scrollbars=yes");
             }


             function editPenerima(i,value){
                 var d;
                 if(value == 'penerima'){
                     d = $('.a'+i).val();
                 }
                 else if(value == 'gadaian'){
                     d = $('.a2'+i).val();
                 }
                 window.open("${pageContext.request.contextPath}/consent/pihak_consent?showEditPenerima&idPihak="+d+"&hakmilik=" + $('#hakmilik').val(), "eTanah",
                 "status=0,toolbar=0,location=0,menubar=0,width=890,height=700,scrollbars=yes");
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
                 window.open("${pageContext.request.contextPath}/consent/pihak_consent?"+url+"&idPihak="+d+"&hakmilik=" + $('#hakmilik').val(), "eTanah",
                 "status=0,toolbar=0,location=0,menubar=0,width=890,height=700,scrollbars=yes");
             }

             function removeMultipleMohonPihak (value) {
                 var param = '';
                 if(confirm('Adakah anda pasti?')) {
                     if(value == 'penerima'){
                         $('.remove2').each(function(index){
                             var a = $('#rm_mp'+index).is(":checked");
                             if(a) {
                                 param = param + '&idPermohonanPihak=' + $('#rm_mp'+index).val();
                             }
                         });
                     }

                     else if(value == 'gadaian'){
                         $('.remove3').each(function(index){
                             var a = $('#rm_mp2'+index).is(":checked");
                             if(a) {
                                 param = param + '&idPermohonanPihak=' + $('#rm_mp2'+index).val();
                             }
                         });
                     }
                     if(param == ''){
                         alert('Sila Pilih Penerima terlebih dahulu.');
                         return;
                     }
                     var url = '${pageContext.request.contextPath}/common/mohon_pihak?deleteMultiplePihakConsent'+param+'&hakmilik='+ $('#hakmilik').val();
                     $.get(url,
                     function(data){
                         $('#div_content').html(data);
                     }, 'html');
                 }
             }


             function semakSyer(f,value ){

                 notComplete = false;

                 if(value == "penerima"){
                     var rowNo = $('table#lineMP tr').length;

                     for (var i = 0; i < rowNo-1; i++){
                         var s1 = $('#syer1' + i).val();
                         var s2 = $('#syer2' + i).val();
                         if(s1 == 0 || s1 == '' || s2 == 0 || s2 == ''){
                             notComplete = true;
                             break;
                         }
                     }
                 }
                 else  if(value == "gadaian"){
                     var rowNo = $('table#lineMP2 tr').length;

                     for (var i = 0; i < rowNo-1; i++){
                         var s1 = $('#syer1B' + i).val();
                         var s2 = $('#syer2B' + i).val();
                         if(s1 == 0 || s1 == '' || s2 == 0 || s2 == ''){
                             notComplete = true;
                             break;
                         }
                     }
                 }

                 if(notComplete){
                     alert("Sila Masukkan Maklumat Syer Dengan Betul");
                 }

                 else{
                     var q = $(f).formSerialize();
                     $.post('${pageContext.request.contextPath}/consent/pihak_consent?semakSyerByIdHakmilik&jenis='+value+'&hakmilik='+ $('#hakmilik').val(),q,
                     function(data){
                         if(data != ''){
                             alert(data);
                         }
                     }, 'html');
                 }
             }

             function hideTerlibat(){
                 if($('input[name=bukanPemohon]').is(':checked')){
                     $('#ttBukanPemohon').hide();
                     var url = '${pageContext.request.contextPath}/consent/pihak_consent?simpanPihakTerlibatMultipleHakmilik&hakmilik='+ $('#hakmilik').val();

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

                 else{
                     $('#ttBukanPemohon').show();
                     var url = '${pageContext.request.contextPath}/consent/pihak_consent?deletePihakTerlibatMultipleHakmilik&hakmilik='+ $('#hakmilik').val();

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

</script>

<s:form beanclass="etanah.view.stripes.consent.PihakConsentActionBean">
    <fieldset class="aras1">
        <legend>Senarai Tuan Tanah</legend>
        <br/>
        <c:if test="${edit && actionBean.p.kodUrusan.kod ne 'TMADT'}">
            <%--            <p align="center">
                            <font size="2" color="red">
                                <s:checkbox name="bukanPemohon" id="bukanPemohon" value="checked" onclick="hideTerlibat();"/>
                                <b>TUAN TANAH BUKAN PEMOHON</b>
                                <br/>
                                <br/>
                            </font>
                        </p>--%>
            <p align="center">
                <font size="2" color="red">
                    <b>Sila Pilih Jika Tuan Tanah Adalah Pemohon</b>
                </font>
            </p>
        </c:if>
        <c:if test="${actionBean.p.kodUrusan.kod eq 'TMADT'}">
            <c:if test="${edit}">
                <font size="2" color="red">
                    <b>Sila masukkan maklumat kematian pada bahagian kemaskini.</b>
                </font>
            </c:if>
        </c:if>
        <display:table class="tablecloth" name="${actionBean.pihakKepentinganList}" cellpadding="0" cellspacing="0" id="line"
                       requestURI="${pageContext.request.contextPath}/consent/pihak_consent">
            <c:if test="${edit}">
                <display:column>
                    <s:checkbox name="checkbox" id="chkbox${line_rowNum}" value="${line.pihak.idPihak}"/>
                </display:column>
            </c:if>
            <display:column title="Bil" sortable="true">${line_rowNum}
                <s:hidden name="yy" class="yy${line_rowNum -1}" value="${line.pihak.idPihak}"/>
            </display:column>
            <display:column  title="Nama" class="nama">
                <a href="#" onclick="viewPihak('${line.pihak.idPihak}','tuanTanah');return false;">${line.pihak.nama}</a>
            </display:column>
            <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
            <display:column title="ID Hakmilik" >
                <s:hidden name="hakmilik" id="hakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}
            </display:column>
            <display:column title="Bahagian yang dimiliki" >${line.syerPembilang}/${line.syerPenyebut}</display:column>
            <display:column title="Tarikh Pemilikan Tanah">
                <fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>
            </display:column>
            <display:column  title="Jenis Pihak">
                <font style="text-transform:uppercase;">${line.jenis.nama}</font>
            </display:column>
            <c:if test="${actionBean.p.kodUrusan.idWorkflow eq 'http://xmlns.oracle.com/Consent_Negeri/Consent/ADAT' || actionBean.p.kodUrusan.kod eq 'BTADT'}">
                <c:if test="${line.pihak.jenisPengenalan.kod eq 'B' || line.pihak.jenisPengenalan.kod eq 'L' || line.pihak.jenisPengenalan.kod eq 'P' || line.pihak.jenisPengenalan.kod eq 'T' || line.pihak.jenisPengenalan.kod eq 'I' || line.pihak.jenisPengenalan.kod eq 'F'}">

                    <c:if test="${edit}">
                        <display:column title="Kemaskini">
                            <p align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                     onclick="editSimati('${line_rowNum -1}', 'terlibat');return false;" onmouseover="this.style.cursor='pointer';">
                            </p>
                        </display:column>
                    </c:if>
                </c:if>
            </c:if>
        </display:table>
        <br/>
        <c:if test="${fn:length(actionBean.senaraiPemohonBerangkai) > 0 || fn:length(actionBean.pihakKepentinganList) > 0}">
            <c:if test="${edit}">
                <div align="center">
                    <p>
                        <s:button class="btn" value="Pilih" name="pilih" id="pilih" onclick="addPemohon();"/>&nbsp;
                    </p>
                </div>
            </c:if>
        </c:if>
        <br/>
    </fieldset>

    <br/>
    <fieldset class="aras1">
        <legend>Senarai Pemohon</legend>
        <br/>
        <c:if test="${edit}">
            <p align="center">
                <font size="2" color="red"><b>
                        Sila Tambah Baru Jika Tuan Tanah Bukan Pemohon
                    </b>
                </font>
            </p>
        </c:if>
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
                <font style="text-transform:uppercase;">${line.pihak.suratNegeri.nama}</font>
            </display:column>
            <c:if test="${edit}">
                <display:column title="Kemaskini">
                    <p align="center">
                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                             onclick="editPemohon('${line_rowNum -1}','${actionBean.p.kodUrusan.kod}');return false;" onmouseover="this.style.cursor='pointer';">
                    </p>
                </display:column>
            </c:if>
        </display:table>
        <br/>
        <div align="center">
            <p>
                <c:if test="${edit}">
                    <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addNewPemohon();"/>&nbsp;
                </c:if>
                <c:if test="${edit && fn:length(actionBean.pemohonList) > 0}" >
                    <s:button class="btn" value="Hapus" name="new" id="new" onclick="removeMultiplePemohon();"/>&nbsp;
                </c:if>
            </p>
        </div>
        <br/>
    </fieldset>
    <br/>
    <div id="mohon_pihak">
        <fieldset class="aras1">
            <legend>Senarai
                <c:choose>
                    <c:when test="${actionBean.p.kodUrusan.kod eq 'PCPTD' || actionBean.p.kodUrusan.kod eq 'PGDMB' || actionBean.p.kodUrusan.kod eq 'PCMMK' || actionBean.p.kodUrusan.kod eq 'PMMAT' || actionBean.p.kodUrusan.kod eq 'PMPTD' || actionBean.p.kodUrusan.kod eq 'PJKTL' || actionBean.p.kodUrusan.kod eq 'PMJTL'}">
                        Penerima Pindah Milik
                    </c:when>
                    <c:when test="${actionBean.p.kodUrusan.kod eq 'PJPTD' || actionBean.p.kodUrusan.kod eq 'PJMMK'}">
                        Penerima Pajakan
                    </c:when>
                    <c:when  test="${actionBean.p.kodUrusan.kod eq 'MCPTD' || actionBean.p.kodUrusan.kod eq 'MCGDMB' || actionBean.p.kodUrusan.kod eq 'MCMMK'}">
                        Penerima Gadaian
                    </c:when>
                    <c:when test="${actionBean.p.kodUrusan.kod eq 'PJPTD' || actionBean.p.kodUrusan.kod eq 'PJMMK'}">
                        Penerima Pajakan
                    </c:when>
                    <c:when test="${actionBean.p.kodUrusan.kod eq 'BTADT'}">
                        Pihak Yang Menuntut
                    </c:when>
                    <c:when test="${actionBean.p.kodUrusan.kod eq 'PAADT'}">
                        Pemegang Amanah
                    </c:when>
                    <c:otherwise>
                        Penerima
                    </c:otherwise>
                </c:choose>
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
                    <display:column  title="Nama" >
                        <a href="#" onclick="viewPihak('${lineMP.pihak.idPihak}','penerima');return false;">${lineMP.pihak.nama}</a>
                    </display:column>
                    <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" class="penerimaKP"/>
                    <c:if test="${display}">
                        <display:column title="Bahagian yang diterima">${lineMP.syerPembilang}/${lineMP.syerPenyebut}</display:column>
                    </c:if>
                    <c:if test="${edit}">
                        <display:column title="Bahagian yang diterima">
                            <div align="center">
                                <s:text name="syer1[${lineMP_rowNum-1}]" size="5" id="syer1${lineMP_rowNum-1}"
                                        onblur="updateSyer('${lineMP.idPermohonanPihak}', '${lineMP_rowNum-1}')"
                                        onchange="updateSyer('${lineMP.idPermohonanPihak}', '${lineMP_rowNum-1}'" class="pembilang"/> /
                                <s:text name="syer2[${lineMP_rowNum-1}]" size="5" id="syer2${lineMP_rowNum-1}"
                                        onblur="updateSyer('${lineMP.idPermohonanPihak}', '${lineMP_rowNum-1}')"
                                        onchange="updateSyer('${lineMP.idPermohonanPihak}', '${lineMP_rowNum-1}'" class="penyebut"/>
                            </div>
                        </display:column>
                        <display:column title="Kemaskini">
                            <p align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                     onclick="editPenerima('${lineMP_rowNum-1}','penerima');return false;" onmouseover="this.style.cursor='pointer';">
                            </p>
                        </display:column>
                    </c:if>
                </display:table>
            </div>

            <c:if test="${edit}">
                <div align="center">
                    <p>             
                        <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addNewPB();"/>&nbsp;

                        <c:if test="${fn:length(actionBean.mohonPihakList) > 0}">
                            <s:button class="btn" value="Semak Syer" name="semak" id="semak" onclick="semakSyer(this.form,'penerima');"/>&nbsp;
                            <s:button class="longbtn" value="Agih Sama Rata" name="" id="_agihSamaRata" onclick="samaRata(this.form,'penerima');"/>&nbsp;
                        </c:if>

                        <c:if test="${fn:length(actionBean.mohonPihakList) > 0}">
                            <s:button class="btn" value="Hapus" name="" onclick="removeMultipleMohonPihak('penerima');"/>&nbsp;
                        </c:if>
                    </p>
                </div>
            </c:if>
        </fieldset>
    </div>
    <c:if test="${actionBean.p.kodUrusan.kod eq 'PCPTD' || actionBean.p.kodUrusan.kod eq 'PGDMB' || actionBean.p.kodUrusan.kod eq 'PCMMK'}">
        <br/>
        <div id="mohon_gadaian">
            <fieldset class="aras1">
                <legend>Senarai Penerima Gadaian </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.penerimaGadaianList}" cellpadding="0" cellspacing="0" id="lineMP2">
                        <c:if test="${edit}">
                            <display:column>
                                <s:checkbox name="rm_mp2" id="rm_mp2${lineMP2_rowNum-1}" value="${lineMP2.idPermohonanPihak}" class="remove3"/>
                            </display:column>
                        </c:if>
                        <display:column title="Bil" sortable="true">${lineMP2_rowNum}
                            <s:hidden name="a2" class="a2${lineMP2_rowNum-1}" value="${lineMP2.pihak.idPihak}"/>
                        </display:column>
                        <display:column  title="Nama" >
                            <a href="#" onclick="viewPihak('${lineMP2.pihak.idPihak}','penerima');return false;">${lineMP2.pihak.nama}</a>
                        </display:column>
                        <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" class="penerimaKP"/>

                        <c:if test="${display}">
                            <display:column title="Bahagian yang diterima">${lineMP2.syerPembilang}/${lineMP2.syerPenyebut}</display:column>
                        </c:if>

                        <c:if test="${edit}">
                            <display:column title="Bahagian yang diterima">
                                <div align="center">
                                    <s:text name="syer1B[${lineMP2_rowNum-1}]" size="5" id="syer1B${lineMP2_rowNum-1}"
                                            onblur="updateSyer('${lineMP2.idPermohonanPihak}', '${lineMP2_rowNum-1}')"
                                            onchange="updateSyer('${lineMP2.idPermohonanPihak}', '${lineMP2_rowNum-1}'" class="pembilang2"/> /
                                    <s:text name="syer2B[${lineMP2_rowNum-1}]" size="5" id="syer2B${lineMP2_rowNum-1}"
                                            onblur="updateSyer('${lineMP2.idPermohonanPihak}', '${lineMP2_rowNum-1}')"
                                            onchange="updateSyer('${lineMP2.idPermohonanPihak}', '${lineMP2_rowNum-1}'" class="penyebut2"/>
                                </div>
                            </display:column>
                            <display:column title="Kemaskini">
                                <p align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                         onclick="editPenerima('${lineMP2_rowNum-1}','gadaian');return false;" onmouseover="this.style.cursor='pointer';">
                                </p>
                            </display:column>

                        </c:if>
                    </display:table>
                </div>

                <c:if test="${edit}">
                    <div align="center">
                        <p>
                            <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addNewPB();"/>&nbsp;

                            <c:if test="${fn:length(actionBean.penerimaGadaianList) > 0}">
                                <s:button class="btn" value="Semak Syer" name="semak" id="semak" onclick="semakSyer(this.form,'gadaian');"/>&nbsp;
                                <s:button class="longbtn" value="Agih Sama Rata" name="" id="_agihSamaRata" onclick="samaRata(this.form,'gadaian');"/>&nbsp;
                            </c:if>

                            <c:if test="${fn:length(actionBean.penerimaGadaianList) > 0}">
                                <s:button class="btn" value="Hapus" name="" onclick="removeMultipleMohonPihak('gadaian');"/>&nbsp;
                            </c:if>
                        </p>
                    </div>
                </c:if>
            </fieldset>
        </div>
    </c:if>
</s:form>