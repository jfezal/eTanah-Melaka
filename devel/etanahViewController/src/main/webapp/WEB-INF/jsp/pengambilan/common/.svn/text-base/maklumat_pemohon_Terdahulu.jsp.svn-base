<%-- 
    Document   : maklumat_pemohon_Terdahulu
    Created on : 14-April-2011, 11:01:49
    Author     : massita
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
                window.open("${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?showEditPemohon&idPihak="+d, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=750");
            });
        }

    });

    function viewPihak(id, jenis){
        window.open("${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?viewPihakDetail&idPihak="+id+"&jenis="+jenis, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=670,scrollbars=yes");
    }

    function popupExisting(){
        window.open("${pageContext.request.contextPath}/common/pihak", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }

    function addNewPB(){
        window.open("${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?pihakKepentinganPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=700,scrollbars=yes");
    }

    function addNewPemohon(){
        window.open("${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?pemohonPopup", "eTanah",
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
            var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?deletePemohon&idPemohon='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }

    function removeChanges(val){
        var answer = confirm("adakah anda pasti untuk Hapus?");
        if (answer){
            var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?deleteChanges&idKkini='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            });
        }
    }

    function removeMultipleMohonPihak(value) {
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


            var url = '${pageContext.request.contextPath}/common/mohon_pihak?deleteMultiplePihakConsent'+param;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }
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
            var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?deleteSelectedPemohon'+param;
            $.ajax({
                type:"GET",
                url : url,
                dataType : 'html',
                error : function (xhr, ajaxOptions, thrownError){
                    alert("error=" + xhr.responseText);
                },
                success : function(data){
                    $('#page_div').html(data);
                }
            });

        }
    }

  <%--  function addPemohon(){
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
        var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?simpanPemohon'+param;

        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
            },
            success : function(data){
                $('#page_div').html(data);
            }
        });
    }
--%>
    function semakSyer(f,value){
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?semakSyer&jenis='+value,q,
        function(data){
            if(data != ''){
                alert(data);
            }
        }, 'html');
    }

    function samaRata(f, value){
        var q = $(f).formSerialize();
        $.get('${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?agihSamaRata&jenis='+value,q,
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

    function dopopup(i,kod){
        if(kod == "TN" || kod == "PNKP"){
            var url ="showEditNamaPemohon";
        }
        else if(kod == "TA"){
            var url = "showEditAlamatPemohon";
        }else
        {
            var url = "showEditPemohon";
        }
        var d = $('.x'+i).val();
        window.open("${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?"+url+"&idPihak="+d, "eTanah",
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
        window.open("${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?showEditPenerima&idPihak="+d, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=700,scrollbars=yes");
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
        var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?updateSyerMohonPihak&idpihak='+idpihak
            + '&syer1=' + s1 + '&syer2=' + s2;
        $.post( url,
        function(data){
        }, 'html');

    }

function refreshPagePemohon(){
var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?refreshpage';
$.get(url,
function(data){
$('#page_div').html(data);
},'html');
}

</script>
<div class="subtitle displaytag">
    <s:form beanclass="etanah.view.stripes.pengambilan.pemohonPermohonanTerdahuluActionBean">
    <s:messages/>
    <s:errors/>
    <c:if test="${form}">
        <fieldset class="aras1">
            <legend>
                Senarai Pemohon
            </legend>
            <c:if test="${edit}">
                <p align="center">
                    <font size="2" color="red"><b>
                            SILA TAMBAH BARU JIKA PEMOHON BUKAN PENYERAH
                        </b>
                    </font>
                </p>
            </c:if>
            <%--second layer start--%>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.pemohonList}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

                   <%-- <c:if test="${edit}">--%>
                        <%--<display:column>
                            <s:checkbox name="rm" id="rm_${line_rowNum-1}" value="${line.idPemohon}" class="remove"/>
                        </display:column>--%>
                    <%--</c:if>--%>

                    <display:column title="Bil" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                    </display:column>
                    <display:column  title="Nama" >
                        <%--<a href="#" onclick="viewPihak('${line.pihak.idPihak}', 'pemohon');return false;">--%>${line.pihak.nama}<%--</a>--%>
                    </display:column>
                    <display:column property="pihak.noPengenalan" title="No. Pengenalan/No.Syarikat" />
                    <display:column title="Alamat">${line.pihak.alamat1}
                        <c:if test="${line.pihak.suratAlamat2 ne null}">,</c:if>
                        ${line.pihak.alamat2}
                        <c:if test="${line.pihak.suratAlamat3 ne null}"> </c:if>
                        ${line.pihak.alamat3}
                        <c:if test="${line.pihak.alamat4 ne null}">,</c:if>
                        ${line.pihak.suratAlamat4}
                        <c:if test="${line.pihak.poskod ne null}">,</c:if>
                        ${line.pihak.suratPoskod}
                        <c:if test="${line.pihak.negeri.kod ne null}">,</c:if>
                        <font style="text-transform:uppercase;">${line.pihak.negeri.nama}</font>
                    </display:column>
                    <display:column property="pihak.noTelefon1" title="No.Telefon" />
                    <display:column property="pihak.noTelefon2" title="No.Fax" />
                    <display:column property="pihak.email" title="Email" />
                    <%--<c:if test="${edit}">--%>
                         <%--<display:column title="Kemaskini">
                            <p align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                     onclick="dopopup('${line_rowNum -1}','${actionBean.p.kodUrusan.kod}');return false;" onmouseover="this.style.cursor='pointer';">
                            </p>
                        </display:column>--%>
                    <%--</c:if>--%>
                </display:table>
            </div>
<%--            <p align="center">
                <c:if test="${edit}">
                    <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addNewPemohon();"/>&nbsp;
                    <label>&nbsp;</label>
                </c:if>
                <c:if test="${edit && fn:length(actionBean.pemohonList) > 0}" >
                    <s:button class="btn" value="Hapus" name="new" id="new" onclick="removeMultiplePemohon();"/>&nbsp;
                </c:if>
            </p>--%>
        </fieldset>
        <br/>
    </c:if>
    </s:form>
</div>


