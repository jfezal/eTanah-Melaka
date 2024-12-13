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

    function doEdit(d, j, d1){        
        var idHakmilik = $('#hakmilik').val();
        window.open("${pageContext.request.contextPath}/pelupusan/pihak_kepentingan_lalulalang?showEditPemohonPenerimaForm&jenis_pihak="
            +j+"&id="+d + "&idPihak=" +d1 + '&idHakmilik=' + idHakmilik, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }

    function selectPemohon(){
        var len = $('.nama').length;
        var param = '';
        doBlockUI();
        for(var i=1; i<=len; i++){
            var ckd = $('#chkbox'+i).is(":checked");
            if(ckd){
                param = param + '&idPihakBerkepentingan=' + $('#chkbox'+i).val();
            }
        }
        if(param == ''){
            alert('Sila pilih maklumat terlebih dahulu.');
            doUnBlockUI();
            return;
        }
        var url = '${pageContext.request.contextPath}/pelupusan/pihak_kepentingan_lalulalang?simpanPemohon'+param + '&idHakmilik=' +  $('#hakmilik').val();

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

    function selectTerlibat1(){
        var len = $('.nama').length;
        var param = '';
        //        alert('A.');
        doBlockUI();
        for(var i=1; i<=len; i++){
            //            alert('B.');
            //            var ckd = $('#chkbox'+i).is(":checked");
            //            if(ckd){
            param = param + '&idPihakBerkepentingan=' + $('#chkbox'+i).val();
            //                alert(param);
            //            }
        }
        if(param == ''){
            alert('Sila pilih maklumat terlebih dahulu.');
            doUnBlockUI();
            return;
        }
        var url = '${pageContext.request.contextPath}/pelupusan/pihak_kepentingan_lalulalang?simpanPihakTerlibat'+param + '&idHakmilik=' +  $('#hakmilik').val();

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

    function selectTerlibat() {
        doBlockUI();
        var param = '';
        $('.pemilikTerlibat').each(function(index) {
            var a = $('#checkboxpihak' + index).is(":checked");
            if (a) {
                param = param + '&idPihakBerkepentingan=' + $('#checkboxpihak' + index).val();
                //          alert(param);
            }
        });

        var url = '${pageContext.request.contextPath}/pelupusan/pihak_kepentingan_lalulalang?simpanPihakTerlibat' + param + '&idHakmilik=' +  $('#hakmilik').val();
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

    function addNew(action){
        var idHakmilik = $('#hakmilik').val();
        var len = $('.nama').length;
        var param = '';        
        doBlockUI();

        for(var i=1; i<=len; i++){

            var ckd = $('#chkbox'+i).is(":checked");
            if(ckd){
                param = param + '&uids=' + $('#chkbox'+i).val();
            }
        }

        if(param == ''){
            alert('Sila pilih maklumat terlebih dahulu.');
            doUnBlockUI();
            return;
        }
        
        var url = '${pageContext.request.contextPath}/pelupusan/pihak_kepentingan_lalulalang?' +action+param+'&jenisPihak=pemohon&idHakmilik=' + idHakmilik;

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


    function remove(action, cn, jenis, id){
        var idHakmilik = $('#hakmilik').val();
        var len = $('.' + cn).length;
        var param = '';
        doBlockUI();

        for(var i=1; i<=len; i++){

            var ckd = $('#' + id +'_'+i).is(":checked");
            if(ckd){
                param = param + '&uids=' + $('#' + id +'_'+i).val();
            }
        }

        if(param == ''){
            alert('Sila pilih maklumat terlebih dahulu.');
            doUnBlockUI();
            return;
        }
        else{
            var url = '${pageContext.request.contextPath}/pelupusan/pihak_kepentingan_lalulalang?' +action+param + '&jenisPihak=' + jenis + '&idHakmilik=' + idHakmilik;

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
                    //doPopupMsg("Kemaskini berjaya!");
                    doUnBlockUI();
                }
            });
        }
    }

    function removePemohon() {
        var param = '';
        doBlockUI();

        $('.removePemohon').each(function(index){
            var a = $('#rm_pemohon_'+index).is(":checked");
            if(a) {
                param = param + '&idPemohon=' + $('#rm_pemohon_'+index).val();
            }
        });

        if(param == ''){
            alert('Sila Pilih Pemohon terlebih dahulu.');
            doUnBlockUI();
            return;
        }
        else{
            if(confirm('Adakah anda pasti?')) {
              
                var url = '${pageContext.request.contextPath}/pelupusan/pihak_kepentingan_lalulalang?deletePemohon'+param;
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
                        //doPopupMsg("Kemaskini berjaya!");
                        doUnBlockUI();
                    }
                });
            }
        }
    }

    function hapusHakmilik() {
        doBlockUI();
        var x = $('#hakmilik').val();
        if(confirm('Adakah anda pasti?')) {

            var url = '${pageContext.request.contextPath}/pelupusan/pihak_kepentingan_lalulalang?deleteIdHakmilik='+x;
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
                    //doPopupMsg("Kemaskini berjaya!");
                    doUnBlockUI();
                }
            });
        }
    }

    function reloadEdit (val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pelupusan/pihak_kepentingan_lalulalang?reloadEdit&idHakmilik=' + val;
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
    
    function reload (val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pelupusan/pihak_kepentingan_lalulalang?reload&idHakmilik=' + val;
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

    function doOpen(type) {
        var idHakmilik = $('#hakmilik').val();
        var url = '${pageContext.request.contextPath}/pelupusan/pihak_kepentingan_lalulalang?showSearchForm&type='+type+'&idHakmilik=' + idHakmilik;
        window.open(url,  "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }
    <%--
        function semakSyer(f){
            var idHakmilik = $('#hakmilik').val();
            alert(idHakmilik);
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/consent/pihak_berkepentingan?semakSyer',q,
            function(data){
                if(data != ''){
                    alert(data);
                }
            }, 'html');
        }--%>

            function semakSyer(f,value){
                notComplete = false;
                if(value == "penerima"){
                    var rowNo = $('table#linePenerima tr').length;
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
                    var rowNo = $('table#lineGadaian tr').length;

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
                    alert("Pembahagian Syer Berjaya");
                }
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
                var url = '${pageContext.request.contextPath}/pihak_kepentingan_lalulalang?updateSyerMohonPihak&idpihak='+idpihak + '&idHakmilik=' +  $('#hakmilik').val()
                    + '&syer1=' + s1 + '&syer2=' + s2;
                $.post( url,
                function(data){
                }, 'html');

            }

    <%--    function samaRata(f){
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
        }--%>

            function samaRata(f, value){
                var idHakmilik = $('#hakmilik').val();
                var q = $(f).formSerialize();
                $.get('${pageContext.request.contextPath}/pelupusan/pihak_kepentingan_lalulalang?agihSamaRata&jenis='+value+'&hakmilik='+idHakmilik,q,
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

            function validateNumber(elmnt,content) {
                //if it is character, then remove it..
                if (isNaN(content)) {
                    elmnt.value = removeNonNumeric(content);
                    return;
                }
            }

            function removeNonNumeric( strString )
            {
                var strValidCharacters = "1234567890";
                var strReturn = "";
                var strBuffer = "";
                var intIndex = 0;
                // Loop through the string
                for( intIndex = 0; intIndex < strString.length; intIndex++ )
                {
                    strBuffer = strString.substr( intIndex, 1 );
                    // Is this a number
                    if( strValidCharacters.indexOf( strBuffer ) > -1 )
                    {
                        strReturn += strBuffer;
                    }
                }
                return strReturn;
            }

            function viewPihak(id, jenis){
                window.open("${pageContext.request.contextPath}/pelupusan/pihak_kepentingan_lalulalang?viewPihakDetail&idPihak="+id+"&jenis="+jenis+ '&idHakmilik=' +  $('#hakmilik').val(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
            }

            function editTuanTanah(idPihak){

                window.open("${pageContext.request.contextPath}/pelupusan/pihak_kepentingan_lalulalang?editTuanTanah&idPihak="+idPihak+"&hakmilik="+$('#hakmilik').val(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
            }

            function editPemohon(idPemohon){
                window.open("${pageContext.request.contextPath}/pelupusan/pihak_kepentingan_lalulalang?editPemohon&idPemohon="+idPemohon+ '&hakmilik=' +  $('#hakmilik').val(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
            }

            function editPenerima(idMohonPihak){
                window.open("${pageContext.request.contextPath}/pelupusan/pihak_kepentingan_lalulalang?editPenerima&idMohonPihak="+idMohonPihak+ '&hakmilik=' +  $('#hakmilik').val(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
            }
            
            function carianHakmilikPopUp(){
                // alert('popup');
                //                  var idHakmilik = $('#idHakmilik').val();
                var statusPage = $('#statusPage').val();
                //                   alert(statusPage);
                window.open("${pageContext.request.contextPath}/pelupusan/pihak_kepentingan_lalulalang?carianHakmilikPopup&statusPage="+statusPage, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=980,height=800,scrollbars=yes");
            }
            function refreshlptn(){
                var url = '${pageContext.request.contextPath}/pelupusan/pihak_kepentingan_lalulalang?refreshpage';
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
</script>        


<div class="subtitle displaytag">
    <s:messages/>
    <s:errors/>
    <s:form beanclass="etanah.view.stripes.pelupusan.PihakKepentinganLalulalangActionBean" name="form1">

        <fieldset class="aras1">
            <legend>Senarai Hakmilik Terlibat</legend>
            <div align="center">
                <c:if test="${fn:length(actionBean.senaraiHakmilikTerlibat) > 1}">
                    <p>
                        <font size="2" color="red">
                            <b>Permohonan Melibatkan Banyak Hakmilik</b>
                        </font>
                    </p>
                </c:if>
            </div>
            <div align="center">          
                <font color="#003194" style="font-weight: bold; font-family:Tahoma; font-size: 13px;">
                    <c:if test="${!empty actionBean.senaraiHakmilikTerlibat}">
                        Hakmilik :
                    </c:if>
                </font>
                <c:if test="${edit}">
                    <c:if test="${empty actionBean.senaraiHakmilikTerlibat}"><font color="red">Sila Masukkan Hakmilik Terlibat</font></c:if>
                    <c:if test="${!empty actionBean.senaraiHakmilikTerlibat}">

                        <s:select name="idHakmilik" onchange="reloadEdit(this.value);" id="hakmilik">
                            <c:forEach items="${actionBean.senaraiHakmilikTerlibat}" var="item" varStatus="line">
                                <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                                    ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                                </s:option>
                            </c:forEach>
                        </s:select>
                    </c:if>
                </c:if>
                <c:if test="${!edit}">
                    <s:select name="idHakmilik" onchange="reload(this.value);" id="hakmilik">
                        <c:forEach items="${actionBean.senaraiHakmilikTerlibat}" var="item" varStatus="line">
                            <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                                ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                            </s:option>
                        </c:forEach>
                    </s:select>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PRMP'}">
                    <p>
                        <c:if test="${actionBean.statusBtn eq 'OPEN'}">
                            <s:button class="longbtn" value="Carian Hakmilik" name="idHakmilik" id="idHakmilik" onclick="carianHakmilikPopUp();"/>&nbsp;
                        </c:if>
                        <c:if test="${!empty actionBean.senaraiHakmilikTerlibat}">
                            <s:button class="longbtn" value="Hapus Hakmilik" name="hapusidHakmilik" id="hapusidHakmilik" onclick="hapusHakmilik();"/>&nbsp;
                        </c:if>
                        <c:if test="${actionBean.statusBtn eq 'HIDE'}">
                            &nbsp;
                        </c:if>
                    </p>
                </c:if>
            </div>
            <br/>
        </fieldset>
        <fieldset class="aras1">
            <legend> Maklumat Tanah</legend>
            <p>
                <label>Daerah :</label>
                <c:if test="${actionBean.hakmilikTanah.daerah.nama eq null}">Tiada</c:if>
                <c:if test="${actionBean.hakmilikTanah.daerah.nama ne null}">${actionBean.hakmilikTanah.daerah.nama}</c:if>&nbsp;
            </p>
            <p>
                <label>Mukim/Pekan/Bandar :</label>
                <c:if test="${actionBean.hakmilikTanah.bandarPekanMukim.nama eq null}">Tiada</c:if>
                <c:if test="${actionBean.hakmilikTanah.bandarPekanMukim.nama ne null}">${actionBean.hakmilikTanah.bandarPekanMukim.nama}</c:if>&nbsp;
            </p>
            <p>
                <label>Seksyen :</label>
                <c:if test="${actionBean.hakmilikTanah.seksyen eq null}">Tiada</c:if>
                <c:if test="${actionBean.hakmilikTanah.seksyen ne null}">${actionBean.hakmilikTanah.seksyen.nama}</c:if>
            </p>
            <p>
                <label>Tempat :</label>
                <c:if test="${actionBean.hakmilikTanah.lokasi eq null}">Tiada</c:if>
                <c:if test="${actionBean.hakmilikTanah.lokasi ne null}">${actionBean.hakmilikTanah.lokasi}</c:if>
            </p>
            <p>
                <label>Jenis Hakmilik :</label>
                <c:if test="${actionBean.hakmilikTanah.kodHakmilik.nama eq null}">Tiada</c:if>
                <c:if test="${actionBean.hakmilikTanah.kodHakmilik.nama ne null}">${actionBean.hakmilikTanah.kodHakmilik.nama}</c:if>
            </p>
            <p>
                <label>Kategori Tanah :</label>
                <c:if test="${actionBean.hakmilikTanah.kategoriTanah.nama eq null}">Tiada</c:if>
                <c:if test="${actionBean.hakmilikTanah.kategoriTanah.nama ne null}">${actionBean.hakmilikTanah.kategoriTanah.nama}</c:if>
            </p>
            <p>
                <label>Kod Lot/PT :</label>
                <c:if test="${actionBean.hakmilikTanah.lot.nama eq null}">Tiada</c:if>
                <c:if test="${actionBean.hakmilikTanah.lot.nama ne null}">${actionBean.hakmilikTanah.lot.nama}</c:if>
            </p>
            <p>
                <label>Nombor Lot/PT :</label>
                <c:if test="${actionBean.hakmilikTanah.noLot eq null}">Tiada</c:if>
                <c:if test="${actionBean.hakmilikTanah.noLot ne null}">${actionBean.hakmilikTanah.noLot}</c:if>
            </p>
            <p>
                <label>Keluasan :</label>
                <c:if test="${actionBean.hakmilikTanah.luas eq null}">Tiada</c:if>
                <c:if test="${actionBean.hakmilikTanah.luas ne null}">${actionBean.hakmilikTanah.luas}&nbsp;${actionBean.hakmilikTanah.kodUnitLuas.nama}</c:if>
            </p>
            <p>
                <label>Syarat Nyata :</label>
                <c:if test="${actionBean.hakmilikTanah.syaratNyata.syarat eq null}">Tiada</c:if>
                <c:if test="${actionBean.hakmilikTanah.syaratNyata.syarat ne null}">${actionBean.hakmilikTanah.syaratNyata.syarat}</c:if>
            </p>
            <p>
                <label>Sekatan Kepentingan :</label>
                <c:if test="${actionBean.hakmilikTanah.sekatanKepentingan.sekatan eq null}">Tiada</c:if>
                <c:if test="${actionBean.hakmilikTanah.sekatanKepentingan.sekatan ne null}">${actionBean.hakmilikTanah.sekatanKepentingan.sekatan}</c:if>
            </p>
            <p>
                <label>Nombor Pelan Piawai :</label>
                <c:if test="${actionBean.mohonLaporPelan.noLitho ne null}">
                    ${actionBean.mohonLaporPelan.noLitho}
                </c:if>
                <c:if test="${actionBean.mohonLaporPelan.noLitho eq null}">
                    Tiada
                </c:if>
            </p>
            <p>
                <label>Nombor Pelan :</label>
                <c:if test="${actionBean.hakmilikTanah.noPelan eq null}">Tiada</c:if>
                <c:if test="${actionBean.hakmilikTanah.noPelan ne null}">${actionBean.hakmilikTanah.noPelan}</c:if>
            </p>

            <p>
                <label>Jenis Rizab :</label>
                <c:if test="${actionBean.hakmilikTanah.rizab.nama ne null}">
                    ${actionBean.hakmilikTanah.rizab.nama}
                </c:if>
                <c:if test="${actionBean.hakmilikTanah.rizab.nama eq null}">
                    Tiada
                </c:if>
            </p>
            <p>
                <label>Cukai Tahunan :</label>
                <c:if test="${actionBean.hakmilikTanah.cukai eq null}">Tiada</c:if>
                <c:if test="${actionBean.hakmilikTanah.cukai ne null}">RM <s:format value="${actionBean.hakmilikTanah.cukai}" formatPattern="##0.00"/></c:if>
            </p>
            <p>
                <label>Lokasi :</label>
                <c:if test="${actionBean.hakmilikTanah.lokasi eq null}">Tiada</c:if>
                <c:if test="${actionBean.hakmilikTanah.lokasi ne null}">${actionBean.hakmilikTanah.lokasi}</c:if>
            </p>

        </fieldset>
        <br/>

        <fieldset class="aras1">
            <legend> Senarai Pemilik</legend>
            <c:if test="${edit}">
                <c:if test="${fn:length(actionBean.senaraiKeempunyaan) == 1}">
                    <p align="center">
                        <font size="2" color="red">
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'TMADT'}">
                                <b>Sila Pilih Butang 'Pilih Pemohon' Jika Pemilik Adalah Pemohon</b>
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TMADT'}">
                                <b>Sila masukkan maklumat kematian pada bahagian kemaskini.</b>
                            </c:if>
                        </font>
                    </p>
                </c:if>
                <c:if test="${fn:length(actionBean.senaraiKeempunyaan) > 1}">
                    <p align="center">
                        <font size="2" color="red">

                            <b>Sila Klik Butang 'Pilih' Jika Pemilik Adalah Pihak Yang Terlibat</b>
                        </font>
                    </p>
                </c:if>
            </c:if>
            <div class="content" align="center">
                <display:table style="width:90%;" class="tablecloth" name="${actionBean.senaraiKeempunyaan}"
                               cellpadding="0" cellspacing="0" id="linePemilik">

                    <c:if test="${edit}">

                        <display:column title="Pilih" style="width:40;">
                            <s:checkbox name="checkboxpihak" id="checkboxpihak${linePemilik_rowNum - 1}" value="${linePemilik.idHakmilikPihakBerkepentingan}" class="pemilikTerlibat"/>
                        </display:column>
                    </c:if>
                    <display:column title="Bil" sortable="true" style="width:40;">
                        ${linePemilik_rowNum}
                        <s:hidden id="chkbox${linePemilik_rowNum - 1}" name="chkbox${linePemilik_rowNum - 1}" value="${linePemilik.idHakmilikPihakBerkepentingan}"/>
                    </display:column>

                    <%--<display:column  title="Nama" property="pihak.nama" class="nama"/>--%>
                    <display:column  title="Nama" class="nama">
                        <a href="#" onclick="viewPihak('${linePemilik.pihak.idPihak}', 'tuanTanah');return false;"><font style="text-transform:uppercase;">${linePemilik.pihak.nama} / ${linePemilik.idHakmilikPihakBerkepentingan}</font></a>
                    </display:column>
                    <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                    <%--<display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />--%>
                    <display:column title="Syer Dimiliki" >
                        <div align="center">
                            ${linePemilik.syerPembilang}/${linePemilik.syerPenyebut}
                        </div>
                    </display:column>
                    <display:column title="Jenis Pihak"><font style="text-transform:uppercase;">${linePemilik.jenis.nama} </font></display:column>
                    <c:if test="${actionBean.permohonan.kodUrusan.idWorkflow eq 'http://xmlns.oracle.com/Consent_Negeri/Consent/ADAT' || actionBean.permohonan.kodUrusan.kod eq 'BTADT'}">
                        <c:if test="${edit}">
                            <display:column title="Kemaskini" style="width:70;">
                                <c:if test="${linePemilik.pihak.jenisPengenalan.kod eq 'B' || linePemilik.pihak.jenisPengenalan.kod eq 'L' || linePemilik.pihak.jenisPengenalan.kod eq 'P' || linePemilik.pihak.jenisPengenalan.kod eq 'T' || linePemilik.pihak.jenisPengenalan.kod eq 'I' || linePemilik.pihak.jenisPengenalan.kod eq 'F'}">
                                    <p align="center">
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="editTuanTanah('${linePemilik.pihak.idPihak}', 'tuanTanah');return false;" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </c:if>
                            </display:column>
                        </c:if>
                    </c:if>
                </display:table>
            </div>
            <c:if test="${fn:length(actionBean.senaraiKeempunyaan) > 0}">
                <c:if test="${edit}">
                    <p align="center">

                        <!--                        <c:if test="${fn:length(actionBean.senaraiKeempunyaan) > 1}">
                            <s:button class="longbtn" value="Pilih" name="pilih" id="pilih" onclick="selectTerlibat();"/>&nbsp;
                        </c:if>
                        -->
                        <s:button class="longbtn" value="Pilih" name="pilih" id="pilih" onclick="selectTerlibat();"/>
                        <!--                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'TMADT'}">
                            <s:button class="longbtn" value="Pilih" name="pilih" id="pilih" onclick="selectPemohon();"/>&nbsp;
                        </c:if>
                        -->
                    </p>
                </c:if>
            </c:if>
        </fieldset>

        <c:if test="${fn:length(actionBean.senaraiTuanTanahTerlibat) > 0 && fn:length(actionBean.senaraiKeempunyaan) > 1}">
            <br/>
            <fieldset class="aras1">
                <legend>Senarai Pemilik Terlibat</legend>
                <div class="content" align="center">
                    <display:table style="width:90%;" class="tablecloth" name="${actionBean.senaraiTuanTanahTerlibat}"
                                   cellpadding="0" cellspacing="0" id="lineTerlibat">
                        <c:if test="${edit}">
                            <display:column title="Pilih" style="width:40;">
                                <s:checkbox name="checkbox" id="rm_ter_${lineTerlibat_rowNum}" value="${lineTerlibat.idPermohonanPihak}"/>
                            </display:column>
                        </c:if>
                        <display:column title="Bil" style="width:40;">${lineTerlibat_rowNum}</display:column>
                        <%-- <display:column title="Nama Pihak" property="pihak.nama" class="remove"/>--%>
                        <display:column  title="Nama" class="remove">
                            <a href="#" onclick="viewPihak('${lineTerlibat.pihak.idPihak}', 'terlibat');return false;"><font style="text-transform:uppercase;">${lineTerlibat.pihak.nama}</font></a>
                        </display:column>
                        <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                        <display:column title="Syer Terlibat">
                            <div align="center">
                                ${lineTerlibat.syerPembilang}/${lineTerlibat.syerPenyebut}
                            </div>
                        </display:column>
                        <c:if test="${edit}">
                            <display:column title="Kemaskini" style="width:70;">
                                <p align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                         onclick="editTuanTanah('${lineTerlibat.pihak.idPihak}', 'tuanTanahTerlibat');return false;" onmouseover="this.style.cursor='pointer';">
                                </p>
                            </display:column>
                        </c:if>
                    </display:table>
                </div>

                <c:if test="${edit}">
                    <p align="center">
                        <c:if test="${fn:length(actionBean.senaraiTuanTanahTerlibat) > 0}">
                            <s:button name="delete" onclick="remove(this.name, 'remove', 'pihakTerlibat', 'rm_ter');" value="Hapus" class="btn"/>&nbsp;
                        </c:if>
                    </p>
                </c:if>
            </fieldset>
        </c:if>
        <!--        <br/>
                <fieldset class="aras1">
                    <legend>Senarai Pemohon</legend>

                    <div class="content" align="center">
        <display:table style="width:90%;" class="tablecloth" name="${actionBean.senaraiPemohon}"
                       cellpadding="0" cellspacing="0" id="linePemohon">
            <c:if test="${edit}">
                <display:column title="Pilih" style="width:40;">
                    <s:checkbox name="checkbox" id="rm_pemohon_${linePemohon_rowNum-1}" value="${linePemohon.idPemohon}" class="removePemohon"/>
                </display:column>
            </c:if>
            <display:column title="Bil" style="width:40;">${linePemohon_rowNum}</display:column>
            <%--<display:column title="Nama Pihak" property="pihak.nama" class="remove"/>--%>
            <display:column  title="Nama">
                <a href="#" onclick="viewPihak('${linePemohon.pihak.idPihak}', 'pemohon');return false;"><font style="text-transform:uppercase;">${linePemohon.pihak.nama}</font></a>
            </display:column>
            <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
            <display:column title="Alamat Surat Menyurat">
                ${linePemohon.pihak.suratAlamat1}
                <c:if test="${linePemohon.pihak.suratAlamat1 ne null && linePemohon.pihak.suratAlamat2 ne null}"> , </c:if>
                ${linePemohon.pihak.suratAlamat2}
                <c:if test="${linePemohon.pihak.suratAlamat2 ne null && linePemohon.pihak.suratAlamat3 ne null}"> , </c:if>
                ${linePemohon.pihak.suratAlamat3}
                <c:if test="${linePemohon.pihak.suratAlamat3 ne null && linePemohon.pihak.suratAlamat4 ne null}"> , </c:if>
                ${linePemohon.pihak.suratAlamat4}
                <c:if test="${linePemohon.pihak.suratAlamat4 ne null && linePemohon.pihak.suratPoskod ne null}">,</c:if>
                ${linePemohon.pihak.suratPoskod}
                <c:if test="${linePemohon.pihak.suratPoskod ne null && linePemohon.pihak.suratNegeri.kod ne null}">,</c:if>
                <font style="text-transform:uppercase;">${linePemohon.pihak.suratNegeri.nama}</font>
            </display:column>

            <c:if test="${edit}">
                <%--
                    <display:column title="Kemaskini" style="width:70;">
                        <p align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                 onclick="editPemohon('${linePemohon.idPemohon}');return false;" onmouseover="this.style.cursor='pointer';">
                        </p>
                    </display:column>
                --%>
            </c:if>
        </display:table>
    </div>
        <c:if test="${edit}">
            <p align="center">
            <s:button name="add" onclick="doOpen('pemohon');" value="Tambah" class="btn"/>
            <c:if test="${(fn:length(actionBean.senaraiPemohon) > 0)}">
                <s:button name="delete" onclick="removePemohon();" value="Hapus" class="btn"/>
            </c:if>
        </p>
        </c:if>
    </fieldset>-->
        <!--
        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'BTADT' && actionBean.permohonan.kodUrusan.kod ne 'TMADT'}">
            <br/>
            <fieldset class="aras1">
                <legend>Senarai
            <c:choose>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PCPTD' || actionBean.permohonan.kodUrusan.kod eq 'PGDMB' || actionBean.permohonan.kodUrusan.kod eq 'PCMMK' || actionBean.permohonan.kodUrusan.kod eq 'PMMAT' || actionBean.permohonan.kodUrusan.kod eq 'PMPTD' || actionBean.permohonan.kodUrusan.kod eq 'PJKTL' || actionBean.permohonan.kodUrusan.kod eq 'PMJTL'}">
                    Penerima Pindah Milik
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PJPTD' || actionBean.permohonan.kodUrusan.kod eq 'PJMMK'}">
                    Penerima Pajakan
                </c:when>
                <c:when  test="${actionBean.permohonan.kodUrusan.kod eq 'MCPTD' || actionBean.permohonan.kodUrusan.kod eq 'MCGDMB' || actionBean.permohonan.kodUrusan.kod eq 'MCMMK'}">
                    Penerima Gadaian
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PJPTD' || actionBean.permohonan.kodUrusan.kod eq 'PJMMK'}">
                    Penerima Pajakan
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'BTADT'}">
                    Pihak Yang Menuntut
                </c:when>
                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PAADT'}">
                    Pemegang Amanah
                </c:when>
                <c:otherwise>
                    Penerima
                </c:otherwise>
            </c:choose>
        </legend>

        <div class="content" align="center">
            <display:table style="width:90%;" class="tablecloth" name="${actionBean.senaraiPermohonanPihak}"
                           cellpadding="0" cellspacing="0" id="linePenerima">
                <c:if test="${edit}">
                    <display:column title="Pilih" style="width:40;">
                        <s:checkbox name="checkbox" id="rm_penerima_${linePenerima_rowNum}" value="${linePenerima.idPermohonanPihak}"/>
                    </display:column>
                </c:if>
                <display:column title="Bil" style="width:40;">${linePenerima_rowNum}</display:column>
                <%--<display:column title="Nama Pihak" property="pihak.nama" class="remove"/>--%>
                <display:column  title="Nama" class="remove">
                    <a href="#" onclick="viewPihak('${linePenerima.pihak.idPihak}', 'penerima');return false;"><font style="text-transform:uppercase;">${linePenerima.pihak.nama}</font></a>
                </display:column>
                <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                <display:column title="Alamat Surat Menyurat">
                    <c:if test="${linePenerima.pihakCawangan ne null}">
                        ${linePenerima.pihakCawangan.suratAlamat1}
                        <c:if test="${linePenerima.pihakCawangan.suratAlamat1 ne null && linePenerima.pihakCawangan.suratAlamat2 ne null}">, </c:if>
                        ${linePenerima.pihakCawangan.suratAlamat2}
                        <c:if test="${linePenerima.pihakCawangan.suratAlamat2 ne null && linePenerima.pihakCawangan.suratAlamat3 ne null}">, </c:if>
                        ${linePenerima.pihakCawangan.suratAlamat3}
                        <c:if test="${linePenerima.pihakCawangan.suratAlamat3 ne null && linePenerima.pihakCawangan.suratAlamat4 ne null}">, </c:if>
                        ${linePenerima.pihakCawangan.suratAlamat4}
                        <c:if test="${linePenerima.pihakCawangan.suratAlamat4 ne null && linePenerima.pihakCawangan.suratPoskod ne null}">,</c:if>
                        ${linePenerima.pihakCawangan.suratPoskod}
                        <c:if test="${linePenerima.pihakCawangan.suratPoskod ne null && linePenerima.pihakCawangan.suratNegeri ne null}">,</c:if>
                        <font style="text-transform:uppercase;">${linePenerima.pihakCawangan.suratNegeri.nama}</font>
                    </c:if>
                    <c:if test="${linePenerima.pihakCawangan eq null}">
                        ${linePenerima.pihak.suratAlamat1}
                        <c:if test="${linePenerima.pihak.suratAlamat1 ne null && linePenerima.pihak.suratAlamat2 ne null}">,</c:if>
                        ${linePenerima.pihak.suratAlamat2}
                        <c:if test="${linePenerima.pihak.suratAlamat2 ne null && linePenerima.pihak.suratAlamat3 ne null}">,</c:if>
                        ${linePenerima.pihak.suratAlamat3}
                        <c:if test="${linePenerima.pihak.suratAlamat3 ne null && linePenerima.pihak.suratAlamat4 ne null}">,</c:if>
                        ${linePenerima.pihak.suratAlamat4}
                        <c:if test="${linePenerima.pihak.suratAlamat4 ne null && linePenerima.pihak.suratPoskod ne null}">,</c:if>
                        ${linePenerima.pihak.suratPoskod}
                        <c:if test="${linePenerima.pihak.suratPoskod ne null && linePenerima.pihak.suratNegeri ne null}">,</c:if>
                        <font style="text-transform:uppercase;">${linePenerima.pihak.suratNegeri.nama}</font>
                    </c:if>
                </display:column>
                <display:column property="jenis.nama" title="Jenis" style="text-transform:uppercase;"/>
                <c:if test="${edit}">
                    <display:column title="Syer Terlibat" style="width:165;">
                        <div align="center">
                        <c:if test="${linePenerima.jenis.kod eq 'PA'}">
                            -
                        </c:if>
                        <c:if test="${linePenerima.jenis.kod ne 'PA'}">
                            <s:text name="senaraiPermohonanPihak[${linePenerima_rowNum-1}].syerPembilang" id="syer1${linePenerima_rowNum-1}"
                                    onblur="updateSyer('${linePenerima.idPermohonanPihak}', '${linePenerima_rowNum-1}')" class="pembilang"
                                    onchange="updateSyer('${linePenerima.idPermohonanPihak}', '${linePenerima_rowNum-1}')" size="6" maxlength="13"
                                    onkeyup="validateNumber(this,this.value);"/> /
                            <s:text name="senaraiPermohonanPihak[${linePenerima_rowNum-1}].syerPenyebut"
                                    id="syer2${linePenerima_rowNum-1}"
                                    onblur="updateSyer('${linePenerima.idPermohonanPihak}', '${linePenerima_rowNum-1}')"
                                    onchange="updateSyer('${linePenerima.idPermohonanPihak}', '${linePenerima_rowNum-1}')"
                                    onkeyup="validateNumber(this,this.value);" class="penyebut" size="6" maxlength="13"/>
                        </c:if>
                    </div>
                    </display:column>
                </c:if>

                <c:if test="${!edit}">
                    <display:column title="Syer Terlibat" style="width:165;">
                        <div align="center">
                        <c:if test="${linePenerima.jenis.kod eq 'PA'}">
                            -
                        </c:if>
                        <c:if test="${linePenerima.jenis.kod ne 'PA'}">
                            ${linePenerima.syerPembilang}/${linePenerima.syerPenyebut}
                        </c:if>
                    </div>
                    </display:column>
                </c:if>
                <c:if test="${edit}">
                    <display:column title="Kemaskini" style="width:70;">
                        <p align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                 onclick="editPenerima('${linePenerima.idPermohonanPihak}', 'penerima');return false;"
                                 onmouseover="this.style.cursor='pointer';">
                        </p>
                    </display:column>
                </c:if>
            </display:table>
        </div>
            <c:if test="${edit}">
                <p align="center">
                <s:button name="add" onclick="doOpen('penerima');" value="Tambah" class="btn"/>
                <c:if test="${fn:length(actionBean.senaraiPermohonanPihak) > 0}">
                    <%--<s:button class="btn" value="Semak Syer" name="semak" id="semak" onclick="semakSyer(this.form);"/>&nbsp;--%>
                    <s:button class="btn" value="Semak Syer" name="" id="semak" onclick="semakSyer(this.form,'penerima');"/>&nbsp;
                    <s:button class="longbtn" value="Agih Sama Rata" name="" onclick="samaRata(this.form,'penerima');"/>&nbsp;
                    <s:button name="delete" onclick="remove(this.name, 'remove', 'penerima', 'rm_penerima');" value="Hapus" class="btn"/>&nbsp;
                </c:if>
            </p>
            </c:if>
        </fieldset>
        </c:if>
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PCPTD' || actionBean.permohonan.kodUrusan.kod eq 'PGDMB' || actionBean.permohonan.kodUrusan.kod eq 'PCMMK'}">
            <br/>
            <fieldset class="aras1">
                <legend>Senarai Penerima Gadaian</legend>

                <div class="content" align="center">
            <display:table style="width:90%;" class="tablecloth" name="${actionBean.senaraiPenerimaGadaian}" cellpadding="0" cellspacing="0" id="lineGadaian">
                <c:if test="${edit}">
                    <display:column title="Pilih" style="width:40;">
                        <s:checkbox name="checkbox" id="rm_gadaian_${lineGadaian_rowNum}" value="${lineGadaian.idPermohonanPihak}"/>
                    </display:column>
                </c:if>
                <display:column title="Bil" style="width:40;">${lineGadaian_rowNum}</display:column>
                <%--<display:column title="Nama Pihak" property="pihak.nama" class="remove"/>--%>
                <display:column  title="Nama" >
                    <a href="#" onclick="viewPihak('${lineGadaian.pihak.idPihak}', 'penerima');return false;"><font style="text-transform:uppercase;">${lineGadaian.pihak.nama}</font></a>
                </display:column>
                <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                <display:column title="Alamat Surat Menyurat">
                    <c:if test="${lineGadaian.pihakCawangan ne null}">
                        ${lineGadaian.pihakCawangan.suratAlamat1}
                        <c:if test="${lineGadaian.pihakCawangan.suratAlamat1 ne null && lineGadaian.pihakCawangan.suratAlamat2 ne null}"> , </c:if>
                        ${lineGadaian.pihakCawangan.suratAlamat2}
                        <c:if test="${lineGadaian.pihakCawangan.suratAlamat2 ne null && lineGadaian.pihakCawangan.suratAlamat3 ne null}"> , </c:if>
                        ${lineGadaian.pihakCawangan.suratAlamat3}
                        <c:if test="${lineGadaian.pihakCawangan.suratAlamat3 ne null && lineGadaian.pihakCawangan.suratAlamat4 ne null}"> , </c:if>
                        ${lineGadaian.pihakCawangan.suratAlamat4}
                        <c:if test="${lineGadaian.pihakCawangan.suratAlamat4 ne null && lineGadaian.pihakCawangan.suratPoskod ne null}">,</c:if>
                        ${lineGadaian.pihakCawangan.suratPoskod}
                        <c:if test="${lineGadaian.pihakCawangan.suratPoskod ne null && lineGadaian.pihakCawangan.suratNegeri.kod ne null}">,</c:if>
                        <font style="text-transform:uppercase;">${lineGadaian.pihakCawangan.suratNegeri.nama}</font>
                    </c:if>
                    <c:if test="${lineGadaian.pihakCawangan eq null}">
                        ${lineGadaian.pihak.suratAlamat1}
                        <c:if test="${lineGadaian.pihak.suratAlamat1 ne null && lineGadaian.pihak.suratAlamat2 ne null}"> , </c:if>
                        ${lineGadaian.pihak.suratAlamat2}
                        <c:if test="${lineGadaian.pihak.suratAlamat2 ne null && lineGadaian.pihak.suratAlamat3 ne null}"> , </c:if>
                        ${lineGadaian.pihak.suratAlamat3}
                        <c:if test="${lineGadaian.pihak.suratAlamat3 ne null && lineGadaian.pihak.suratAlamat4 ne null}"> , </c:if>
                        ${lineGadaian.pihak.suratAlamat4}
                        <c:if test="${lineGadaian.pihak.suratAlamat4 ne null && lineGadaian.pihak.suratPoskod ne null}">,</c:if>
                        ${lineGadaian.pihak.suratPoskod}
                        <c:if test="${lineGadaian.pihak.suratPoskod ne null && lineGadaian.pihak.suratNegeri.kod ne null}">,</c:if>
                        <font style="text-transform:uppercase;">${lineGadaian.pihak.suratNegeri.nama}</font>
                    </c:if>
                </display:column>
                <display:column property="jenis.nama" title="Jenis" style="text-transform:uppercase;"/>
                <c:if test="${edit}">
                    <display:column title="Syer Terlibat" style="width:165;">
                        <div align="center">
                        <c:if test="${lineGadaian.jenis.kod eq 'PA'}">
                            -
                        </c:if>
                        <c:if test="${lineGadaian.jenis.kod ne 'PA'}">
                            <s:text name="senaraiPenerimaGadaian[${lineGadaian_rowNum-1}].syerPembilang" id="syer1B${lineGadaian_rowNum-1}"
                                    onblur="updateSyer('${lineGadaian.idPermohonanPihak}', '${lineGadaian_rowNum-1}')" class="pembilang2"
                                    onchange="updateSyer('${lineGadaian.idPermohonanPihak}', '${lineGadaian_rowNum-1}')" size="6" maxlength="13"
                                    onkeyup="validateNumber(this,this.value);"/> /
                            <s:text name="senaraiPenerimaGadaian[${lineGadaian_rowNum-1}].syerPenyebut"
                                    id="syer2B${lineGadaian_rowNum-1}"
                                    onblur="updateSyer('${lineGadaian.idPermohonanPihak}', '${lineGadaian_rowNum-1}')"
                                    onchange="updateSyer('${lineGadaian.idPermohonanPihak}', '${lineGadaian_rowNum-1}')" class="penyebut2"
                                    onkeyup="validateNumber(this,this.value);" size="6" maxlength="13"/>
                        </c:if>
                    </div>
                    </display:column>
                </c:if>
                <c:if test="${!edit}">
                    <display:column title="Syer Terlibat" style="width:165;">
                        <div align="center">
                        <c:if test="${lineGadaian.jenis.kod eq 'PA'}">
                            -
                        </c:if>
                        <c:if test="${lineGadaian.jenis.kod ne 'PA'}">
                            ${lineGadaian.syerPembilang}/${lineGadaian.syerPenyebut}
                        </c:if>
                    </div>
                    </display:column>
                </c:if>
                <c:if test="${edit}">
                    <display:column title="Kemaskini" style="width:70;">
                        <p align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                 onclick="editPenerima('${lineGadaian.idPermohonanPihak}', 'penerimaGadaian');return false;"
                                 onmouseover="this.style.cursor='pointer';">
                        </p>
                    </display:column>
                </c:if>
            </display:table>
        </div>

            <c:if test="${edit }">
                <p align="center">
                <s:button name="add" onclick="doOpen('penerima');" value="Tambah" class="btn"/>
                <c:if test="${fn:length(actionBean.senaraiPenerimaGadaian) > 0}">
                    <%--<s:button class="btn" value="Semak Syer" name="semak" id="semak" onclick="semakSyer(this.form);"/>&nbsp;--%>
                    <s:button class="btn" value="Semak Syer" name="semak" id="semak" onclick="semakSyer(this.form,'gadaian');"/>&nbsp;
                    <s:button class="longbtn" value="Agih Sama Rata" name="" onclick="samaRata(this.form,'gadaian');"/>&nbsp
                    <s:button name="delete" onclick="remove(this.name, 'remove', 'penerima', 'rm_gadaian');" value="Hapus" class="btn"/>&nbsp;
                </c:if>
            </p>
            </c:if>
        </fieldset>
        </c:if>-->
    </s:form>    
</div>
