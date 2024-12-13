<%--
    Document   : laporan_tanah4
    Created on : June 15, 2011, 2:56:40 PM
    Author     : Murali
    Modified    : Shazwan, Srinivas
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<%-- carian hakmilik start--%>

<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<%-- carian hakmilik end--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
var checkplpulasan0 = '';
    $(document).ready(function() {
        // carian hakmilik start
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'}),dialogInit('Carian Hakmilik'),$('#catatanKegunaan').hide();
       <%--<c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >--%>
            $("#hakmilik${i - 1}").change(function(){validateHakmilik(${i - 1});});
            $("#hakmilik${i - 1}").keyup(function(){
                closeDialog();
            });
        <%--</c:forEach>--%>
        $('input:text').each(function(){
            $(this).focus(function() { $(this).addClass('focus')});
            $(this).blur(function() { $(this).removeClass('focus')});
        });
  //  });

               checkplpulasan0 =  document.getElementById("plpulasan0").value
               document.getElementById("flagElement").value = '0';


        //carian hakmilik end
        if('${actionBean.permohonan.kodUrusan.kod}' == "PPTR"){
             if('${actionBean.statBdnPngwl}' == "3"){
                $('#catatanPengawal').show();
             }else
                 $('#catatanPengawal').hide();

        }
        if('${actionBean.catatanKeg}'!=null)
            $('#catatanKegunaan').show();

        if('${actionBean.permohonan.kodUrusan.kod}' == 'PLPS'){

            if('${actionBean.ksn}'!=null&&'${actionBean.ksn}'!=''){

                 if('${actionBean.ksn}'=='SL'){
                 $('#plpssokong').show();
                 $('#plpstidaksokong').hide();
                }
                else if('${actionBean.ksn}'=='ST'){
                     $('#plpssokong').hide();
                     $('#plpstidaksokong').show();
                }
            }else{
                $('#plpssokong').hide();
                $('#plpstidaksokong').hide();
            }

            if('${actionBean.keg}'=='LN'){
                 $('#catatanKegunaan').show();
            }else{
                 $('#catatanKegunaan').hide();
            }

        }
        if('${actionBean.permohonan.kodUrusan.kod}' == 'PPRU'){

            if('${actionBean.ksn}'!=null&&'${actionBean.ksn}'!=''){

                 if('${actionBean.ksn}'=='SL'){
                 $('#pprusokong').show();
                 $('#pprutidaksokong').hide();
                }
                else if('${actionBean.ksn}'=='ST'){
                     $('#pprusokong').hide();
                     $('#pprutidaksokong').show();
                }
            }else{
                $('#pprusokong').hide();
                $('#pprutidaksokong').hide();
            }
        }
        if('${actionBean.permohonan.kodUrusan.kod}' == 'PPTR'){

            if('${actionBean.ksn}'!=null&&'${actionBean.ksn}'!=''){

                 if('${actionBean.ksn}'=='SL'){
                 $('#pptrsokong').show();
                 $('#pptrtidaksokong').hide();
                }
                else if('${actionBean.ksn}'=='ST'){
                     $('#pptrsokong').hide();
                     $('#pptrtidaksokong').show();
                }
            }else{
                $('#pptrsokong').hide();
                $('#pptrtidaksokong').hide();
            }
        }
        if('${actionBean.permohonan.kodUrusan.kod}' == 'PRMP'){

            if('${actionBean.ksn}'!=null&&'${actionBean.ksn}'!=''){

                 if('${actionBean.ksn}'=='SL'){
                 $('#prmpsokong').show();
                 $('#prmptidaksokong').hide();
                }
                else if('${actionBean.ksn}'=='ST'){
                     $('#prmpsokong').hide();
                     $('#prmptidaksokong').show();
                }
            }else{
                $('#prmpsokong').hide();
                $('#prmptidaksokong').hide();
            }

        }
        if('${actionBean.permohonan.kodUrusan.kod}' == 'LPSP'){

            if('${actionBean.ksn}'!=null&&'${actionBean.ksn}'!=''){

                 if('${actionBean.ksn}'=='SL'){
                 $('#lpspsokong').show();
                 $('#lpsptidaksokong').hide();
                }
                else if('${actionBean.ksn}'=='ST'){
                     $('#lpspsokong').hide();
                     $('#lpsptidaksokong').show();
                }
            }else{
                $('#lpspsokong').hide();
                $('#lpsptidaksokong').hide();
            }

        }

        if('${actionBean.permohonan.kodUrusan.kod}' == 'PPBB' || '${actionBean.permohonan.kodUrusan.kod}' == 'PBPTG' || '${actionBean.permohonan.kodUrusan.kod}' == 'PBPTD'){

            if('${actionBean.ksn}'!=null&&'${actionBean.ksn}'!=''){

                 if('${actionBean.ksn}'=='SL'){
                 $('#batuansokong').show();
                 $('#batuantidaksokong').hide();
                }
                else if('${actionBean.ksn}'=='ST'){
                     $('#batuansokong').hide();
                     $('#batuantidaksokong').show();
                }
            }else{
                $('#batuansokong').hide();
                $('#batuantidaksokong').hide();
            }

        }
        <c:if test="${actionBean.kodNegeri eq '05'}">

        if('${actionBean.permohonan.kodUrusan.kod}' == 'PBMT'){
            if('${actionBean.ksn}'!=null&&'${actionBean.ksn}'!=''){

                 if('${actionBean.ksn}'=='SL'){
                     document.form.ksn[0].checked=true;
                     document.form.ksn[1].checked=false;
                     document.form.ksn[2].checked=false;
                     showjikasokong();
                }
                else if('${actionBean.ksn}'=='ST'){
                    document.form.ksn[0].checked=false;
                     document.form.ksn[1].checked=false;
                     document.form.ksn[2].checked=true;
                   showtidaksokong();
                }
                else if('${actionBean.ksn}'=='SU'){
                    document.form.ksn[0].checked=false;
                     document.form.ksn[1].checked=true;
                     document.form.ksn[2].checked=false;
                   showpbmtsyorlulus();
                }
            }else{
                    document.form.ksn[0].checked=false;
                     document.form.ksn[1].checked=false;
                     document.form.ksn[2].checked=false;
            }

        }
        </c:if>




        $('#jenisRizab').hide();
        $('#jenisRizabno').hide();
        $('#carianHakmilik').hide();
        $('#tanahkerajaan').hide();
        $('#jikasebab').hide();
        $('#pengusuka').hide();
        $('#tarikh').hide();
        $('#laterbelaktable').hide();
        $('#Bangunantable').hide();
        $('#pbmtsyorlulus').hide();
//        $('#plpstidaksokong').hide();
//        $('#plpssokong').hide();
        $('#mT').hide();
        $('#aG').hide();
        $('#tB').hide();
        $('#bK').hide();
        $('#kM').hide();
        $('#jS').hide();
        $('#premiumTxt').hide();
        $('#jenistanahlainlain').hide();
        $('#keadaantanahlainlain').hide();

    var mh = '${actionBean.hakmilikPermohonan.kodMilik.kod}';
    var lt = '${actionBean.laporanTanah.bolehBerimilik}';

      if(mh == 'K'){
          $('#tanahkerajaan').show();
          $('#jikasebab').hide();
      }else{
        $('#tanahkerajaan').hide();
        $('#jikasebab').hide();
      }
      if((mh == 'K') && (lt == 'T')){
          $('#jikasebab').show();
      }else{
          $('#jikasebab').hide();
      }
      if(mh == 'H'){
        $('#carianHakmilik').show();
         $('#jikasebab').hide();
        }else {
            $('#carianHakmilik').hide();
        }

      if(mh == 'R'){
        $('#jenisRizab').show();
        $('#jenisRizabno').show();
        $('#jikasebab').hide();
      }else{
        $('#jenisRizab').hide();
        $('#jenisRizabno').hide();
      }

// Kawasan Parlimen && Dun
      var mhk = '${actionBean.hakmilikPermohonan.kodKawasanParlimen.kod}';
      var dun = '${actionBean.hakmilikPermohonan.kodDUN.kod}';
     // alert(mhk);
     // alert(dun);
        if(mhk == 'P134'){
        $('#kodDmT').val(dun);
        $('#mT').show();
        $('#aG').hide();
        $('#tB').hide();
        $('#bK').hide();
        $('#kM').hide();
        $('#jS').hide();
      }else if(mhk == 'P135'){
        $('#kodDaG').val(dun);
        $('#aG').show();
        $('#mT').hide();
        $('#tB').hide();
        $('#bK').hide();
        $('#kM').hide();
        $('#jS').hide();
      }else if(mhk == 'P136'){
        $('#kodDtB').val(dun);
        $('#tB').show();
        $('#aG').hide();
        $('#mT').hide();
        $('#bK').hide();
        $('#kM').hide();
        $('#jS').hide();
      }else if(mhk == 'P137'){
        $('#kodDbK').val(dun);
        $('#bK').show();
        $('#tB').hide();
        $('#aG').hide();
        $('#mT').hide();
        $('#kM').hide();
        $('#jS').hide();
      }else if(mhk == 'P138'){
        $('#kodDkM').val(dun);
        $('#kM').show();
        $('#bK').hide();
        $('#tB').hide();
        $('#aG').hide();
        $('#mT').hide();
        $('#jS').hide();
      }else if(mhk == 'P139'){
        $('#kodDjS').val(dun);
        $('#jS').show();
        $('#kM').hide();
        $('#bK').hide();
        $('#tB').hide();
        $('#aG').hide();
        $('#mT').hide();
      }else{
        $('#jS').hide();
        $('#kM').hide();
        $('#bK').hide();
        $('#tB').hide();
        $('#aG').hide();
        $('#mT').hide();
      }


  var ltu = '${actionBean.laporanTanah.usaha}';
  if(ltu == 'Y'){
      $('#pengusuka').show();
      $('#tarikh').show();
      $('#laterbelaktable').show();
  }else{
      $('#pengusuka').hide();
      $('#tarikh').hide();
      $('#laterbelaktable').hide();
  }
        var fm = '${actionBean.fasaPermohonan.keputusan.kod}';
        var kodurusan = '${actionBean.permohonan.kodUrusan.kod}';
        //alert(fm);
        if(kodurusan == 'PBMT'){
        //if(fm == 'DI'){
        if(fm == 'SL'){
        $('#jikasokong').show();
        $('#tidaksokong').hide();
        $('#pbmtsyorlulus').hide();
        }
        //else if(fm == 'TI'){
        else if(fm == 'ST'){
        $('#tidaksokong').show();
        $('#jikasokong').hide();
        $('#pbmtsyorlulus').hide();
        }
        //else if(fm == 'SL'){
        else if(fm == 'SU'){
        $('#pbmtsyorlulus').show();
        $('#tidaksokong').hide();
        $('#jikasokong').hide();

        }
        else {
        $('#tidaksokong').hide();
        $('#jikasokong').hide();
        $('#pbmtsyorlulus').hide();
        }
        }
//       if(kodurusan == 'PLPS'){
//        if(fm == 'DI'){
//        $('#plpssokong').show();
//        $('#plpstidaksokong').hide();
//        }
//        else if(fm == 'TI'){
//        $('#plpstidaksokong').show();
//        $('#plpssokong').hide();
//
//        }
//       }

       <%--var premium = '${actionBean.hakmilikPermohonan.keteranganKadarPremium}';
       if(premium == '25%(Nilai Pasaran - Nilai Pampasan)'){
           $('#premiumTxt').show();
       }else{
           $('#premiumTxt').hide();
       }--%>

      var jenistanah = '${actionBean.permohonanLaporanPelan.kodTanah.kod}';

      if(jenistanah == '99'){
         $('#jenistanahlainlain').show();
      }else{
          $('#jenistanahlainlain').hide();
      }

      var keadaantanah = '${actionBean.laporanTanah.kodKeadaanTanah.kod}';
      //alert(keadaantanah);

      if(keadaantanah == 'LL'){
         $('#keadaantanahlainlain').show();
      }else{
          $('#keadaantanahlainlain').hide();
      }


     var adbangunan = '${actionBean.laporanTanah.adaBangunan}';
     if(adbangunan == 'Y'){
        $('#Bangunantable').show();
     }else{
         $('#Bangunantable').hide();
     }

        if($('#adaBangunan').val() == 'Y')
            $('#bilanganBangunan').show();

        var v = '${actionBean.laporanTanah.kecerunanTanah.kod}';

        if(v == 'RT'){
            $('#tinggi').hide();
            $('#cerun').hide();
            $('#dalam').hide();
        }

        else if(v == 'BK'){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }

        else if(v == 'TG'){
            $('#tinggi').show();
            $('#cerun').hide();
            $('#dalam').hide();
        }

        else if(v == 'RD'){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }

        else if(v == 'CR'){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }

        else if(v == 'PY'){
            $('#tinggi').hide();
            $('#cerun').hide();
            $('#dalam').show();
        }
        $('#jenisRizab1').hide();
        $('#noWarta').hide();

        $('#nyataRancangan').hide();
        $('#tanahMilik').hide();
        $('#tujuan').hide();

        $('#catatanTanahMilik').hide();
        $('#catatanPBT').hide();

        var statusLuas = '${actionBean.hakmilikPermohonan.statusLuasDiluluskan}';

      if(statusLuas == 'S'){
         $('#luassbhgn').show();
      }else {
          $('#luassbhgn').hide();
      }

    });
    function removeLaporKawasan(idMohonlaporKws)
    {
        if(confirm('Adakah anda pasti?')) {
           <%-- var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?deletePermohonanTerdahulu&idPermohonan='
                +idPermohonan;--%>

             var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanah4?removeLaporKawasan&idMohonlaporKws='
                +idMohonlaporKws;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
    }
    function changeRancangan(value){

        if(value == "ada")
            $('#nyataRancangan').show();

        else
            $('#nyataRancangan').hide();
    }
    function tambahKawasan(){
        window.open("${pageContext.request.contextPath}/pelupusan/laporan_tanah4?kawasanPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }
    function changePBT(value){

        if(value == "dalam")
            $('#catatanPBT').show();

        else
            $('#catatanPBT').hide();
    }

    function changeTanahMilik(value){

        if(value == "ya"){
            $('#tanahMilik').show();
            $('#catatanTanahMilik').show();
        }

        else{
            $('#tanahMilik').hide();
            $('#catatanTanahMilik').hide();
        }
    }

    function changeKategoriTanah(value){

        if(value == "kerajaan"){
            $('#jenisRizab').hide();
            $('#noWarta').hide();
        }
        else if(value == "milik"){
            $('#jenisRizab').hide();
            $('#noWarta').hide();
        }
        else if(value == "rizab"){
            $('#jenisRizab').show();
            $('#noWarta').show();
        }
    }

    function changeTujuan(value){

        if(value == "Lain-lain")
            $('#tujuan').show();
        else
            $('#tujuan').hide();
    }

    function changeKeadaanTanah(text){

        if(text == "Rata"){
            $('#tinggi').hide();
            $('#cerun').hide();
            $('#dalam').hide();
        }

        else if(text == "Berbukit"){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }

        else if(text == "Tinggi"){
            $('#tinggi').show();
            $('#cerun').hide();
            $('#dalam').hide();
        }

        else if(text == "Rendah"){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }

        else if(text == "Curam"){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }

        else if(text == "Berpaya"){
            $('#tinggi').hide();
            $('#cerun').hide();
            $('#dalam').show();
        }
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
    function popup(i){

        var d = $(".x"+i).val();
        window.open("${pageContext.request.contextPath}/pelupusan/laporan_tanah?showeditTanahRizab&idTanahRizabPermohonan="+d, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
    }

        function tambahBaru(){
            var p = false ;
            var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanah?hakMilikPopup&p='+p;
            window.open(url, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }

        function showBilBangunan(f) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/laporan_tanah4?addBilBangunan&status=Y',q,
            function(data){
//                 $.unblockUI();
                $('#page_div').html(data);
//
            }, 'html');
//            var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanah4?addBilBangunan&status=Y&jenisJalan='+jenisJalan+'&adaJalanMasuk='
//                       +adaJalanMasuk+'&catatanJalanMasuk='+catatanJalanMasuk+'&namaSempadanJalanraya='+namaSempadanJalanraya+'&namaSempadanKeretapi='
//                       +namaSempadanKeretapi+'&namaSempadanLaut='+namaSempadanLaut+'&namaSempadanSungai='+namaSempadanSungai+'&namaSempadanlain='
//                       +namaSempadanlain+'&jarakSempadanJalanraya='+jarakSempadanJalanraya+'&jarakSempadanKeretapi='+jarakSempadanKeretapi+'&jarakSempadanLaut='
//                       +jarakSempadanLaut+'&jarakSempadanSungai='+jarakSempadanSungai+'&jarakSempadanLain='+jarakSempadanLain+'&jarak='+jarak+'&unitJarak='
//                       +unitJarak+'&jarakDari='+jarakDari+'&jarakDariKediaman='+jarakDariKediaman+'&jarakDariKediamanUom='+jarakDariKediamanUom+'&kecerunanT='
//                       +kecerunanT+'&ketinggianDariJalan='+ketinggianDariJalan+'&kecerunanBukit='+kecerunanBukit+'&parasAir='+parasAir+'&klasifikasiT='
//                       +klasifikasiT+'&strukturTanahLain='+strukturTanahLain+'&keadaanTanah='+keadaanTanah+'&tanahBertumpu='+tanahBertumpu+'&keteranganTanahBertumpu='
//                       +keteranganTanahBertumpu+'&dilintasTiangElektrik='+dilintasTiangElektrik+'&dilintasTiangTelefon='+dilintasTiangTelefon+'&dilintasLaluanGas='
//                       +dilintasLaluanGas+'&dilintasPaip='+dilintasPaip+'&dilintasTaliar='+dilintasTaliar+'&dilintasSungai='+dilintasSungai+'&dilintasParit='
//                       +dilintasParit+'&usaha='+usaha+'&diusaha='+diusaha+'&tarikhMulaUsaha2='+tarikhMulaUsaha2+'&usahaTanam='+usahaTanam+'&usahaLuas='
//                       +usahaLuas+'&usahaBilanganPokok='+usahaBilanganPokok+'&usahaHarga='+usahaHarga;
//            $.ajax({
//            url:url,
//            success:function(data){
//                $('#page_div').html(data);
//            }
//            });
//            $.post(url,
//            $.blockUI({ message: '<img src="${pageContext.request.contextPath}/pub/images/logo.gif" border="0"/>',css: { border: 'none', background: 'none'
//                        } }),
//            function(data){
//               $('#page_div').html(data);
//               $.unblockUI();
//            }, 'html');
        }
        function hideBilBangunan(f) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/laporan_tanah4?addBilBangunan&status=T',q,
            function(data){
                $('#page_div').html(data);
            }, 'html');

//            var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanah4?addBilBangunan&status=T&jenisJalan='+jenisJalan+'&adaJalanMasuk='
//                       +adaJalanMasuk+'&catatanJalanMasuk='+catatanJalanMasuk+'&namaSempadanJalanraya='+namaSempadanJalanraya+'&namaSempadanKeretapi='
//                       +namaSempadanKeretapi+'&namaSempadanLaut='+namaSempadanLaut+'&namaSempadanSungai='+namaSempadanSungai+'&namaSempadanlain='
//                       +namaSempadanlain+'&jarakSempadanJalanraya='+jarakSempadanJalanraya+'&jarakSempadanKeretapi='+jarakSempadanKeretapi+'&jarakSempadanLaut='
//                       +jarakSempadanLaut+'&jarakSempadanSungai='+jarakSempadanSungai+'&jarakSempadanLain='+jarakSempadanLain+'&jarak='+jarak+'&unitJarak='
//                       +unitJarak+'&jarakDari='+jarakDari+'&jarakDariKediaman='+jarakDariKediaman+'&jarakDariKediamanUom='+jarakDariKediamanUom+'&kecerunanT='
//                       +kecerunanT+'&ketinggianDariJalan='+ketinggianDariJalan+'&kecerunanBukit='+kecerunanBukit+'&parasAir='+parasAir+'&klasifikasiT='
//                       +klasifikasiT+'&strukturTanahLain='+strukturTanahLain+'&keadaanTanah='+keadaanTanah+'&tanahBertumpu='+tanahBertumpu+'&keteranganTanahBertumpu='
//                       +keteranganTanahBertumpu+'&dilintasTiangElektrik='+dilintasTiangElektrik+'&dilintasTiangTelefon='+dilintasTiangTelefon+'&dilintasLaluanGas='
//                       +dilintasLaluanGas+'&dilintasPaip='+dilintasPaip+'&dilintasTaliar='+dilintasTaliar+'&dilintasSungai='+dilintasSungai+'&dilintasParit='
//                       +dilintasParit+'&usaha='+usaha+'&diusaha='+diusaha+'&tarikhMulaUsaha2='+tarikhMulaUsaha2+'&usahaTanam='+usahaTanam+'&usahaLuas='
//                       +usahaLuas+'&usahaBilanganPokok='+usahaBilanganPokok+'&usahaHarga='+usahaHarga;
//            $.ajax({
//            url:url,
//            success:function(data){
//                $('#page_div').html(data);
//            }
//            });
//            $.post(url,
//            $.blockUI({ message: '<img src="${pageContext.request.contextPath}/pub/images/logo.gif" border="0"/>',css: { border: 'none', background: 'none'
//                        } }),
//            function(data){
//               $('#page_div').html(data);
//               $.unblockUI();
//            }, 'html');
            //$('#bilanganBangunan').hide();
            //$('#Bangunantable').hide();

        }

        function addImage(idHakmilik,rowNo) {
            var idDokumen = document.getElementById("imej_"+rowNo).options[document.getElementById("imej_"+rowNo).selectedIndex].value;
            $("#imej"+rowNo).val(idDokumen);

            $.post('${pageContext.request.contextPath}/pelupusan/laporan_tanah4?addHakmilikImage&idDokumen='+idDokumen+'&idHakmilik='+idHakmilik,
            function(data){
                $('#page_div').html(data);
            }, 'html');

        }

        function addImageUtara(){
            var idDokumen = document.getElementById("utaraImej").options[document.getElementById("utaraImej").selectedIndex].value;
            $.post('${pageContext.request.contextPath}/pelupusan/laporan_tanah4?addLaporanImage&pandanganImej=U&idDokumen='+idDokumen,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }

        function addImageSelatan(){
            var idDokumen = document.getElementById("selatanImej").options[document.getElementById("selatanImej").selectedIndex].value;
            $.post('${pageContext.request.contextPath}/pelupusan/laporan_tanah4?addLaporanImage&pandanganImej=S&idDokumen='+idDokumen,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }

        function addImageTimur(){
            var idDokumen = document.getElementById("timurImej").options[document.getElementById("timurImej").selectedIndex].value;
            $.post('${pageContext.request.contextPath}/pelupusan/laporan_tanah4?addLaporanImage&pandanganImej=T&idDokumen='+idDokumen,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }

        function addImageBarat(){
            var idDokumen = document.getElementById("baratImej").options[document.getElementById("baratImej").selectedIndex].value;
            $.post('${pageContext.request.contextPath}/pelupusan/laporan_tanah4?addLaporanImage&pandanganImej=B&idDokumen='+idDokumen,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }
        function openLain_lain(val){
              if(val == 'LL'||val == 'LN'){
                   $('#catatanKegunaan').show();
              }else{
                   $('#catatanKegunaan').hide();
              }
        }
        function doViewReport(rowNo) {
            var idDokumen = document.getElementById("imej_"+rowNo).options[document.getElementById("imej_"+rowNo).selectedIndex].value;
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        function doViewReportUtara() {
            var idDokumen = document.getElementById("utaraImej").options[document.getElementById("utaraImej").selectedIndex].value;
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        function doViewReportSelatan() {
            var idDokumen = document.getElementById("selatanImej").options[document.getElementById("selatanImej").selectedIndex].value;
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        function doViewReportTimur() {
            var idDokumen = document.getElementById("timurImej").options[document.getElementById("timurImej").selectedIndex].value;
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        function doViewReportBarat() {
            var idDokumen = document.getElementById("baratImej").options[document.getElementById("baratImej").selectedIndex].value;
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        function doView(idDokumen) {
            // alert(idDokumen);
            // var idDokumen = document.getElementById("imej_"+rowNo).options[document.getElementById("imej_"+rowNo).selectedIndex].value;
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }

        // GIS

        function ReplaceAll(Source,stringToFind,stringToReplace){
            var temp = Source;
            var index = temp.indexOf(stringToFind);
            while(index != -1){
                temp = temp.replace(stringToFind,stringToReplace);
                index = temp.indexOf(stringToFind);

            }
            return temp;
        }

        function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {

    <%--alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);--%>
            strNama = ReplaceAll(strNama," ","_");
            strJawatan = ReplaceAll(strJawatan," ","_");
            var stageId = "gis_lim";
    <%--        alert("nama:" + strNama);
            alert ("jawatan:" + strJawatan);
            alert ("stageid:" + stageId);--%>
                    var objShell = new ActiveXObject("WScript.Shell");
                    var sysVar = objShell.Environment("System");
                    //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
                    //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")
                    objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + stageId);
                }

                function uploadForm(pandanganImej) {
                    var left = (screen.width/2)-(1000/2);
                    var top = (screen.height/2)-(150/2);
                    var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanah4?uploadDoc&pandanganImej='+pandanganImej;
                    window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
                }
                function doSetDokumenUtara(){
                    var idDokumen = document.getElementById("utaraImej").options[document.getElementById("utaraImej").selectedIndex].value;
                    var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
                    window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
                }
                function doSetDokumenHakmilik(){
                    var idDokumen = document.getElementById("hmImej").options[document.getElementById("hmImej").selectedIndex].value;
                    var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
                    window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
                }

                function doSetDokumenTimur(){
                    var idDokumen = document.getElementById("timurImej").options[document.getElementById("timurImej").selectedIndex].value;
                    var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
                    window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
                }
                function doSetDokumenBarat(){
                    var idDokumen = document.getElementById("baratImej").options[document.getElementById("baratImej").selectedIndex].value;
                    var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
                    window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
                }
                function doSetDokumenSelatan(){
                    var idDokumen = document.getElementById("selatanImej").options[document.getElementById("selatanImej").selectedIndex].value;
                    var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
                    window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
                }

                // murali
                function changeHide(value){
                         // alert(value);
                    if(value == 'K'){
                        $('#tanahkerajaan').show();
                        $('#jikasebab').hide();
                    }else{
                        $('#tanahkerajaan').hide();
                    }

                    if(value == 'R'){
                        $('#jenisRizab').show();
                        $('#jenisRizabno').show();
                    }else{
                        $('#jenisRizab').hide();
                        $('#jenisRizabno').hide();
                    }
                    if(value == 'H'){
                        $('#carianHakmilik').show();
                        $('#jikasebab').hide();
                    }else{
                        $('#carianHakmilik').hide();
                    }
                    }
                function showSebab() {
                    $('#jikasebab').show();
                }
                function hideSebab() {
                    $('#jikasebab').hide();
                }

                function showpengusuka(){
                    $('#pengusuka').show();
                    $('#tarikh').show();
                    $('#laterbelaktable').show();
                    $('#tempoh').show();
                }
                function hidepengusuka() {
                    $('#pengusuka').hide();
                    $('#tarikh').hide();
                    $('#laterbelaktable').hide();
                    $('#Bangunantable').hide();
                    $('#tempoh').hide();
                }

                function showjikasokong(){
                    $('#jikasokong').show();
                    $('#tidaksokong').hide();
                    $('#pbmtsyorlulus').hide();
                }
                function showtidaksokong(){
                    $('#tidaksokong').show();
                    $('#jikasokong').hide();
                    $('#pbmtsyorlulus').hide();

                }
                function showpbmtsyorlulus(){
                    $('#pbmtsyorlulus').show();
                    $('#jikasokong').hide();
                    $('#tidaksokong').hide();
                }
                function showplpssokong(){
                      $('#plpssokong').show();
                      $('#plpstidaksokong').hide();
                }
                function showplpstidaksokong() {
                    $('#plpstidaksokong').show();
                    $('#plpssokong').hide();
                }
                function showpprusokong(){
                      $('#pprusokong').show();
                      $('#pprutidaksokong').hide();
                }
                function showpprutidaksokong() {
                    $('#pprutidaksokong').show();
                    $('#pprusokong').hide();
                }
                 function showpptrsokong(){
                      $('#pptrsokong').show();
                      $('#pptrtidaksokong').hide();
                }
                function showpptrtidaksokong() {
                    $('#pptrtidaksokong').show();
                    $('#pptrsokong').hide();
                }
                function showprmpsokong(){
                      $('#prmpsokong').show();
                      $('#prmptidaksokong').hide();
                }
                function showprmptidaksokong() {
                    $('#prmptidaksokong').show();
                    $('#prmpsokong').hide();
                }

                function showlpspsokong() {
                     $('#lpspsokong').show();
                      $('#lpsptidaksokong').hide();
                }
                function showlpsptidaksokong() {
                    $('#lpsptidaksokong').show();
                    $('#lpspsokong').hide();
                }
                function showbatuansokong() {
                     $('#batuansokong').show();
                      $('#batuantidaksokong').hide();
                }
                function showbatuantidaksokong() {
                    $('#batuantidaksokong').show();
                    $('#batuansokong').hide();
                }
                function showluaspenuh(){

                    $('#luassbhgn').hide();
                }
                function showluassbhgn(){
                     $('#luassbhgn').show();
                }

                 function changeHideDun(value){
                    // alert(value);
                     $('#kodD').val("");
                     if(value == 'P134'){
                        $('#mT').show();
                        $('#aG').hide();
                        $('#tB').hide();
                        $('#bK').hide();
                        $('#kM').hide();
                        $('#jS').hide();
                    }else if(value == 'P135'){
                        $('#aG').show();
                        $('#mT').hide();
                        $('#mT').attr("disabled", "disabled");
                        $('#tB').hide();
                        $('#tB').attr("disabled", "disabled");
                        $('#bK').hide();
                        $('#bK').attr("disabled", "disabled");
                        $('#kM').hide();
                        $('#kM').attr("disabled", "disabled");
                        $('#jS').hide();
                        $('#jS').attr("disabled", "disabled");
                    }else if(value == 'P136'){
                        $('#tB').show();
                        $('#aG').hide();
                        $('#mT').hide();
                        $('#bK').hide();
                        $('#kM').hide();
                        $('#jS').hide();
                    }else if(value == 'P137'){
                        $('#bK').show();
                        $('#tB').hide();
                        $('#aG').hide();
                        $('#mT').hide();
                        $('#kM').hide();
                        $('#jS').hide();
                    }else if(value == 'P138'){
                        $('#kM').show();
                        $('#bK').hide();
                        $('#tB').hide();
                        $('#aG').hide();
                        $('#mT').hide();
                        $('#jS').hide();
                    }else if(value == 'P139'){
                        $('#jS').show();
                        $('#kM').hide();
                        $('#bK').hide();
                        $('#tB').hide();
                        $('#aG').hide();
                        $('#mT').hide();
                    }
                 }

                 <%--function showPremimTxt(value){
                      if(value == '25%(Nilai Pasaran - Nilai Pampasan)'){
                        $('#premiumTxt').show();
                      }else{
                        $('#premiumTxt').hide();
                    }
                 }--%>

                   function showjenistanahlain(value){
                      // alert(value);
                       if(value == '99'){
                       $('#jenistanahlainlain').show();
                       }else{
                       $('#jenistanahlainlain').hide();
                       }
                   }

                   function showkeadaantanahlain(value){
                       //alert(value);
                       if(value == 'LL'){
                       $('#keadaantanahlainlain').show();
                       }else{
                       $('#keadaantanahlainlain').hide();
                       }
                   }

                function tambahPermohonanTerdahulu(){
                   // alert('test');
                    window.open("${pageContext.request.contextPath}/pelupusan/laporan_tanah4?permohonanTerdahuluPopup", "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
                }

                function removePermohonanTerdahulu(idMohonManual)
                {
                    if(confirm('Adakah anda pasti?')) {
                        var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanah4?deletePermohonanTerdahulu&idMohonManual='
                            +idMohonManual;
                        $.get(url,
                        function(data){
                            $('#page_div').html(data);
                        },'html');}
                }

                function cariPopup(){
                    // alert("search:"+index);
                   // var url = '${pageContext.request.contextPath}/pelupusan/draf_mmkn?search';
                    var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanah4?search';
                    window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
                }

                function cariPopup2(){
                    // alert("search:"+index);
                   // alert('search');
                   <%-- var url = '${pageContext.request.contextPath}/pelupusan/draf_mmkn?showFormCariKodSekatan';--%>
                     var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanah4?showFormCariKodSekatan';
                    //alert(url);
                    window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
                }

                //carian hakmilik start

               function carianHakmilikPopUp(){
                  // alert('popup');
                  var idHakmilik = $('#idHakmilik').val();
                   window.open("${pageContext.request.contextPath}/pelupusan/laporan_tanah4?carianHakmilikPopup="+idHakmilik, "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=980,height=800");
                }
                 //carian hakmilik end

        // Tambah Bangunan Tambah function

         function tambahBangunan(kategori){
        var idHakmilik = '${actionBean.hakmilik.idHakmilik}';
        window.open("${pageContext.request.contextPath}/pelupusan/laporan_tanah4?tambahBangunanPopup&kategori="+kategori, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }

    function editLaporanBangunan(idLaporBangunan){
        var idHakmilik = '${actionBean.hakmilik.idHakmilik}';
       // alert('idLaporBangunan-'+idLaporBangunan+'---idHakmilik='+idHakmilik);
        window.open("${pageContext.request.contextPath}/pelupusan/laporan_tanah4?editBangunanPopup&idHakmilik="+idHakmilik+'&idLaporBangunan='+idLaporBangunan, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }

    function refreshlptn(){
    var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanah4?refreshpage';
    $.get(url,
    function(data){
    $('#page_div').html(data);
    },'html');
   }

   function dun(value){
       $('#kodD').val(value);
   }


   function addRow2_new_new(){
        var count = 0;
       count = document.getElementById("countElement").value;
       var code_ref = count;
       var flagElement = document.getElementById("flagElement").value;
       if(checkplpulasan0 != '' ){
       if(count == 0 && flagElement == 0 && document.getElementById("pulasanTest") == undefined){
        count++;
        var count_ref = count++;
        var pulasan = document.getElementById("pulasan");
        var childNodes = pulasan.childNodes;
        var lab = document.createElement("label");
        lab.innerHTML = '&nbsp;'
        pulasan.appendChild(lab);
        var el = document.createElement('textarea');
        el.name = 'plpulasan' + count_ref;
        //alert("el.name"+el.name);
        el.id = 'plpulasan' + count_ref;
        //alert("el.id"+el.id);
        el.rows = 7;
        el.cols = 69;
        pulasan.appendChild(el);
        var buttonnode= document.createElement('input');
        buttonnode.setAttribute('type','button');
        buttonnode.setAttribute('name','delete'+ count_ref);
        buttonnode.setAttribute('value','Hapus');
        buttonnode.setAttribute('class','btn');
        pulasan.appendChild(buttonnode);
        var code = count_ref;
        document.getElementById("countElement").value = code;
        flag = 1;
        document.getElementById("flagElement").value = flag;
        document.getElementById("testElement").value = '1';
         }else if (((count != 0 && (flagElement == 1 || flagElement == 0))) || (document.getElementById("pulasanTest") != undefined && count == 0)){
             if(((document.getElementById('pulasanTest') == undefined && document.getElementById("pulasanTestParnet") == undefined) && document.getElementById("testElement").value != '1')  || (document.getElementById('pulasanTest') == undefined && '${actionBean.pasId}' == '1') ){
                count++;
        var count_ref = count++;
        var pulasanTest_ref = document.getElementById("pulasanTestParnet_ref");
        if('${actionBean.pasId}' == '1' ){
            var pdiv_ref = document.createElement("div");
            pdiv_ref.setAttribute('id', 'pulasanTestParnet');
            pulasanTest_ref.appendChild(pdiv_ref);
        }
        var pulasanTest = document.getElementById("pulasanTestParnet");
        var pdiv = document.createElement("p");
        pdiv.setAttribute('id', 'pulasanTest');
        pulasanTest.appendChild(pdiv);
        var pulasan = document.getElementById("pulasanTest");
        var childNodes = pulasan.childNodes;
        var lab = document.createElement("label");
        lab.innerHTML = '&nbsp;'
        pulasan.appendChild(lab);
        var el = document.createElement('textarea');
        el.name = 'plpulasan' + count_ref;
        el.id = 'plpulasan' + count_ref;
        el.rows = 7;
        el.cols = 69;
        pulasan.appendChild(el);
        var buttonnode= document.createElement('input');
        buttonnode.setAttribute('type','button');
        buttonnode.setAttribute('name','delete'+ count_ref);
        buttonnode.setAttribute('value','Hapus');
        buttonnode.setAttribute('class','btn');
        pulasan.appendChild(buttonnode);
        var code = count_ref;
        document.getElementById("countElement").value = code;
        flag = 1;
        document.getElementById("flagElement").value = flag;
        return false;
    }
             //alert("hai"+code_ref);
             if(document.getElementById('pulasanTest') != undefined){
                            //alert("checkid:"+'${actionBean.checkId}');
                            //alert("document.getElementById(flagElement).value:"+document.getElementById("flagElement").value);
                            //alert("testelement"+'${actionBean.testElement}');
                  if(('${actionBean.checkId}' != '0' &&  document.getElementById("flagElement").value != '1') || '${actionBean.testElement}' == '1' ){
                      document.getElementById("testElement").value = '0';
                       code_ref ++;
               }
                  code_ref =  code_ref ++;
             }
             //alert("hai#"+code_ref);
           var testElement =  document.getElementById("testElement").value;
           var textAreaData1 = document.getElementById("plpulasan"+code_ref).value;
           if(textAreaData1 == ''){
               alert("Sila Masukkan Data");
	         return false;
           }
           var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanah4?addSingle&textAreaData='+textAreaData1+"&rowCount2=0&testElement="+testElement;
                $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
         }

       }else if(checkplpulasan0 == ''){
       var plpulasan0 = document.getElementById("plpulasan0").value;
	   if(plpulasan0 != '' && count == 0){
          var textAreaData1 = document.getElementById("plpulasan0").value;
           var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanah4?addSingle&textAreaData='+textAreaData1+"&rowCount2=0";
                $.post(url,
            function(data){
                $('#page_div').html(data);
            },'html');

	   }else if(document.getElementById("plpulasan"+count).value != '' && count > 0){

		 var textAreaData1 = document.getElementById("plpulasan"+code_ref).value;
           var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanah4?addSingle&textAreaData='+textAreaData1+"&rowCount2=0";
                $.post(url,
            function(data){
                $('#page_div').html(data);
            },'html');

	   }else{

	    alert("Sila Masukkan Data");
	    return false;

	   }


       }

   }


   function deleteRow2_new_new(idValue){
   var count = 0;
   count = document.getElementById("countElement").value;
   count++;
   var code_ref = count++;
   var checkStatus = '0';
   var checkId='1';
   var pasId = '';

   if(document.getElementById("plpulasan"+code_ref) != undefined){
   var textAreaData1 = document.getElementById("plpulasan"+code_ref).value;
   }
   var idLaporUlas= document.getElementById('idLaporUlas'+idValue).value;

   if(textAreaData1 == '' ||document.getElementById("plpulasan"+code_ref) != undefined ){
       checkStatus = '1';
   }
   if( document.getElementById("pulasanTest") == undefined ){
       pasId = '1';
       checkId='0';
   }
   var conf = confirm("? Are You Sure About Deleting This Record");
   if(conf){
   var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanah4?deleteSingle&idLaporUlas='
                +idLaporUlas+'&checkStatus='+checkStatus+"&checkId="+checkId+"&pasId="+pasId;
               $.post(url,
           function(data){
              $('#page_div').html(data);
          },'html');
           //var form = document.form;
           //form.action = url;
           // form.submit();
    }else{
        return false;
    }

   }
   function deleteRow2_delete(){

   var pulasanTestParnet = document.getElementById("pulasanTestParnet");
   var pulasanTest = document.getElementById("pulasanTest");
   pulasanTestParnet.removeChild(pulasanTest);

   var count = 0;
       count = document.getElementById("countElement").value;
       document.getElementById("countElement").value = count--;
       //alert("count&"+document.getElementById("countElement").value);
}








    function calculateBayaranPRMP(){

        var kuponQty = document.getElementById('kadarBayarPRMP').value;
//       alert(kuponQty);
        var kuponAmaun = document.getElementById('luasLulus').value;
//        alert(kuponAmaun);
        var koduom = document.getElementById('koduom').value;
        var jumlah = 0;
        if(koduom == '0')
            alert('Sila Nyatakan Jenis Luas Diluluskan untuk pengiraan bayaran.');
        if(koduom == 'M')
            jumlah = kuponAmaun * kuponQty;
        if(koduom == 'H'){
            kuponQty = kuponQty * 10000;
            jumlah = kuponAmaun * kuponQty;
        }
        document.getElementById('amnt').value = CurrencyFormatted(jumlah);
     }
     function CurrencyFormatted(amount)
    {
        var i = parseFloat(amount);
        if(isNaN(i)) { i = 0.00; }
        var minus = '';
        if(i < 0) { minus = '-'; }
        i = Math.abs(i);
        i = parseInt((i + .005) * 100);
        i = i / 100;
        s = new String(i);
        if(s.indexOf('.') < 0) { s += '.00'; }
        if(s.indexOf('.') == (s.length - 2)) { s += '0'; }
        s = minus + s;
        return s;
    }
    function calculateBayarKupon(){

        var kuponQty = document.getElementById('kuponQty').value;

        var kuponAmaun = document.getElementById('kuponAmaun').value;
        var jumlah = (kuponAmaun * kuponQty);
        document.getElementById('kupon').value = CurrencyFormatted(jumlah);
        calculateSyarat();
    }
    function calculateSyarat(){
        var kuantiti = document.getElementById('kuantitiSyarat').value;
        //alert(kuantiti);
        var bayaran = document.getElementById('bayaranSyarat').value;
        //alert(bayaranSyarat);
        var jumlah = kuantiti * bayaran;
        //alert(jumlah);
        var cagaran = 20/100 * jumlah;
        var cagarJalan = document.getElementById('cagarJalan').value;

        var kuponQty = document.getElementById('kuponQty').value;

        var kuponAmaun = document.getElementById('kuponAmaun').value;
        var jumlahKpnQty = (kuponAmaun * kuponQty);
        cagarJalan = cagarJalan*1;
        var totalAll = (jumlah) + (cagaran) + (jumlahKpnQty) + (cagarJalan);

        document.getElementById('jumlahSyarat').value = CurrencyFormatted(jumlah);
        document.getElementById('cagaranSyarat').value = CurrencyFormatted(cagaran);
        document.getElementById('kuantitiJumlahSyarat').value = kuantiti;
        document.getElementById('cagarJalan').value = CurrencyFormatted(cagarJalan);
        document.getElementById('totalAll').value = CurrencyFormatted(totalAll);
    }
    function openLain_lainPengawal(val){
      if(val == '3'){
           $('#catatanPengawal').show();
      }else{
           $('#catatanPengawal').hide();
      }
    }
</script>

            <s:form beanclass="etanah.view.stripes.pelupusan.LaporanTanah4ActionBean" name="form">
    <s:errors/>
    <s:messages/>
    <s:hidden name="checkTanahExist" value="${actionBean.checkTanahExist}"/>
    <s:hidden name="permohonan.idPermohonan"/>
    <s:hidden name="display"/>

    <s:hidden name="kodD" id="kodD"/>
    <%--<s:text name="edit" id="edit"/>--%>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Perihal Tanah</legend>
            <c:if test="${actionBean.display eq 'false'}">
            <div class="content" align="center">
                <c:if test="${actionBean.kodP eq 'K'}">
                Tanah Kerajaan
                </c:if>
                <c:if test="${actionBean.kodP eq 'H'}">
                Tanah Hakmilik
                </c:if>
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                               requestURI="/pelupusan/laporan_tanah">
                    <c:if test="${line.hakmilik ne null}">
                        <display:column title="No" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.id}"/></display:column>
                        <display:column title="No.Lot/PT" property="hakmilik.noLot"/>
                        <display:column title="Bandar/Pekan/Mukim" property="hakmilik.bandarPekanMukim.nama"/>
                        <display:column title="Seksyen"><s:select name="kodSeksyen.nama" value="${actionBean.hakmilik.kodSeksyen.kod}">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:options-collection collection="${actionBean.kodSeksyenList}" label="nama" value="kod" /></s:select></display:column>
                        <display:column title="Daerah" property="hakmilik.bandarPekanMukim.daerah.nama"/>
                        <display:column title="Luas Dipohon"><s:format formatPattern="#,###,##0.0000" value="${line.hakmilik.luas}"/> ${line.hakmilik.kodUnitLuas.nama} </display:column>
                    </c:if>
                    <c:if test="${line.hakmilik eq null}">
                        <display:column title="No" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.id}"/></display:column>
                        <display:column title="No.Lot/PT" property="noLot"/>
                        <display:column title="Bandar/Pekan/Mukim" property="bandarPekanMukimBaru.nama"/>
                        <display:column title="Seksyen"><s:select name="kodSeksyen.nama" value="${actionBean.hakmilikPermohonan.kodSeksyen.kod}">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:options-collection collection="${actionBean.kodSeksyenList}" label="nama" value="kod" /></s:select></display:column>
                        <display:column title="Daerah" property="bandarPekanMukimBaru.daerah.nama"/>
                        <display:column title="Luas Dipohon"><s:format formatPattern="#,###,##0.0000" value="${line.luasTerlibat}"/> ${line.kodUnitLuas.nama} </display:column>
                    </c:if>
                </display:table>
            </div>
            </c:if>

           <c:if test="${actionBean.display eq 'true'}">
            <div class="content" align="center">
                <c:if test="${actionBean.kodP eq 'K'}">
                Tanah Kerajaan
                </c:if>
                <c:if test="${actionBean.kodP eq 'H'}">
                Tanah Hakmilik
                </c:if>
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                               requestURI="/pelupusan/laporan_tanah">
                     <c:if test="${line.hakmilik ne null}">
                        <display:column title="No" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.id}"/></display:column>
                        <display:column title="No.Lot/PT" property="hakmilik.noLot"/>
                        <display:column title="Bandar/Pekan/Mukim" property="hakmilik.bandarPekanMukim.nama"/>
                        <display:column title="Seksyen" property="hakmilik.seksyen.nama"></display:column>
                        <display:column title="Daerah" property="hakmilik.bandarPekanMukim.daerah.nama"/>
                        <display:column title="Luas Dipohon"><s:format formatPattern="#,###,##0.0000" value="${line.hakmilik.luas}"/> ${line.hakmilik.kodUnitLuas.nama} </display:column>
                    </c:if>
                    <c:if test="${line.hakmilik eq null}">
                        <display:column title="No" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.id}"/></display:column>
                        <display:column title="No.Lot/PT" property="noLot"/>
                        <display:column title="Bandar/Pekan/Mukim" property="bandarPekanMukimBaru.nama"/>
                        <display:column title="Seksyen" property="kodSeksyen.nama"></display:column>
                        <display:column title="Daerah" property="bandarPekanMukimBaru.daerah.nama"/>
                        <display:column title="Luas Dipohon"><s:format formatPattern="#,###,##0.0000" value="${line.luasTerlibat}"/> ${line.kodUnitLuas.nama} </display:column>
                    </c:if>
                </display:table>
            </div>
            </c:if>
            <p>
                <label>Status Tanah :</label>
                 <c:if test="${actionBean.display eq 'false'}">
	                <s:select name="kodP" style="width:150px" id="kodP" onchange="javaScript:changeHide(this.value)">
	                    <s:option value="0">Sila Pilih</s:option>
	                    <s:options-collection collection="${listUtil.senaraiKodPemilikan}" label="nama" value="kod"/>
	                </s:select>
                </c:if>
                <c:if test="${actionBean.display eq 'true'}">
                	${actionBean.hakmilikPermohonan.kodMilik.nama}&nbsp;
                </c:if>
            </p>
            <p id="tanahkerajaan">
                <label>Tanah Kerajaan Boleh diberimilik :</label>
                <c:if test="${actionBean.display eq 'false'}">
                <s:radio name="laporanTanah.bolehBerimilik" value="Y" onclick="hideSebab();" />&nbsp;Ya
                <s:radio name="laporanTanah.bolehBerimilik" value="T" onclick="showSebab();"/>&nbsp;Tidak
                </c:if>
                <c:if test="${actionBean.display eq 'true'}"><!--
                	${actionBean.laporanTanah.bolehBerimilik}
                	--><c:if test="${actionBean.laporanTanah.bolehBerimilik eq 'Y'}">
                		Ya &nbsp;
                	</c:if>
                	<c:if test="${actionBean.laporanTanah.bolehBerimilik eq 'T'}">
                		Tidak &nbsp;
                	</c:if>
                </c:if>
            </p>

            <p id="jikasebab">
                <label>Jika Tidak Boleh diberimilik, sebab :</label>
                 <c:if test="${actionBean.display eq 'false'}">
                <s:textarea name="laporanTanah.sebabTidakBolehBerimilik" rows="5" cols="50"/>
                 </c:if>
               <c:if test="${actionBean.display eq 'true'}">
                ${actionBean.laporanTanah.sebabTidakBolehBerimilik}&nbsp;
                 </c:if>
            </p>
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PHLP'}">
                <div class="content" align="center" id="carianHakmilik">
                <display:table  name="${actionBean.permohonan.senaraiHakmilik}" id="line" class="tablecloth">
                    <display:column title="ID Hakmilik">
                        <c:if test="${line.hakmilik.kodHakmilik.nama ne null}"> ${line.hakmilik.kodHakmilik.nama}&nbsp; </c:if>
                        <c:if test="${line.hakmilik.kodHakmilik.nama eq null}"> Tiada Data </c:if>
                    </display:column>

                    <display:column title="Jenis Hakmilik">
                        <c:if test="${line.hakmilik.idHakmilik ne null}"> ${line.hakmilik.idHakmilik}&nbsp; </c:if>
                        <c:if test="${line.hakmilik.idHakmilik eq null}"> Tiada Data </c:if>
                    </display:column>

                    <display:column title="No Lot" >
                        <c:if test="${line.hakmilik.noLot ne null}"> ${line.hakmilik.noLot}&nbsp; </c:if>
                        <c:if test="${line.hakmilik.noLot eq null}"> Tiada Data </c:if>

                    </display:column>

                    <display:column title="Luas">
                        <c:if test="${line.hakmilik.luas ne null}"> <fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</c:if>
                        <c:if test="${line.hakmilik.luas eq null}"> Tiada Data </c:if>
                    </display:column>

                    <display:column property="hakmilik.kategoriTanah.nama" title="Kegunaan" >
                        <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot ne null}"> ${actionBean.laporanTanah.sempadanUtaraNoLot}&nbsp; </c:if>
                        <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot eq null}"> Tiada Data </c:if>
                    </display:column>

                    <display:column title="Cukai (RM)">
                        <c:if test="${line.hakmilik.cukai ne null}"> <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukai}"/>&nbsp; </c:if>
                        <c:if test="${line.hakmilik.cukai eq null}"> Tiada Data </c:if>
                    </display:column>
                </display:table>

                         <c:if test="${actionBean.display eq 'false'}">
                             <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PRMP'}">
                                 <s:button class="longbtn" value="Carian Hakmilik" name="idHakmilik" id="idHakmilik" onclick="carianHakmilikPopUp();"/>&nbsp;
                             </c:if>

                         </c:if>

            </div>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PHLP'}">
                <div class="content" align="center" id="carianHakmilik">
                <display:table  name="${actionBean.hakmilikPermohonanList}" id="line" class="tablecloth">
                    <display:column title="ID Hakmilik">
                        <c:if test="${line.hakmilik.kodHakmilik.nama ne null}"> ${line.hakmilik.kodHakmilik.nama}&nbsp; </c:if>
                        <c:if test="${line.hakmilik.kodHakmilik.nama eq null}"> Tiada Data </c:if>
                    </display:column>

                    <display:column title="Jenis Hakmilik">
                        <c:if test="${line.hakmilik.idHakmilik ne null}"> ${line.hakmilik.idHakmilik}&nbsp; </c:if>
                        <c:if test="${line.hakmilik.idHakmilik eq null}"> Tiada Data </c:if>
                    </display:column>

                    <display:column title="No Lot" >
                        <c:if test="${line.hakmilik.noLot ne null}"> ${line.hakmilik.noLot}&nbsp; </c:if>
                        <c:if test="${line.hakmilik.noLot eq null}"> Tiada Data </c:if>

                    </display:column>

                    <display:column title="Luas">
                        <c:if test="${line.hakmilik.luas ne null}"> <fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</c:if>
                        <c:if test="${line.hakmilik.luas eq null}"> Tiada Data </c:if>
                    </display:column>

                    <display:column property="hakmilik.kategoriTanah.nama" title="Kegunaan" >
                        <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot ne null}"> ${actionBean.laporanTanah.sempadanUtaraNoLot}&nbsp; </c:if>
                        <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot eq null}"> Tiada Data </c:if>
                    </display:column>

                    <display:column title="Cukai (RM)">
                        <c:if test="${line.hakmilik.cukai ne null}"> <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukai}"/>&nbsp; </c:if>
                        <c:if test="${line.hakmilik.cukai eq null}"> Tiada Data </c:if>
                    </display:column>
                </display:table>

            </div>
            </c:if>
            <p id="jenisRizab">
                <label>Jenis Rizab :</label>
                <c:if test="${actionBean.display eq 'false'}">
                <s:select name="tanahR" style="width:250px" id="tanahR" >
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodRizab}" label="nama" value="kod"/>
                </s:select>
                </c:if>
              <c:if test="${actionBean.display eq 'true'}">
                  ${actionBean.tanahrizabpermohonan1.rizab.nama} &nbsp;
              </c:if>
            </p>
            <p id="jenisRizabno">
                <label>No. Warta Kerajaan :</label>
                <c:if test="${actionBean.display eq 'false'}">
                <s:text name="tanahrizabpermohonan1.noWarta" />
                </c:if>
                <c:if test="${actionBean.display eq 'true'}">
                           ${actionBean.tanahrizabpermohonan1.noWarta}&nbsp;
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR'}">
                    <p>
                    <label>Tarikh Warta:</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:text name="tanahrizabpermohonan1.tarikhWarta" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy"/>
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        <s:format formatPattern="dd/MM/yyyy" value="${actionBean.tanahrizabpermohonan1.tarikhWarta}"/> &nbsp;
                    </c:if>
                    </p>
                    <p>
                    <label>No Pelan Warta:</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:text name="tanahrizabpermohonan1.noPW" size="30"/>
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                         ${actionBean.tanahrizabpermohonan1.noPW}&nbsp;
                    </c:if>
                    </p>
                    <p>
                    <label>Pengawal:</label>
                    <c:if test="${actionBean.display eq 'false'}">
                        <s:select name="statBdnPngwl" style="width:350px" value="${actionBean.statBdnPngwl}" onchange="openLain_lainPengawal(this.value)">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:option value="1">SUK Negeri</s:option>
                            <s:option value="2">Pesuruhjaya Tanah Persekutuan</s:option>
                            <s:option value="3">Lain-lain</s:option>
                        </s:select>

                        <div  id="catatanPengawal">
                        <p>
                            <label><font color="red"></font>Jika Lain-lain (Sila Nyatakan) :</label>
                            <s:textarea name="tanahrizabpermohonan1.penjaga" id="catatan"  rows="5" cols="40"/>
                        </p>
                        </div>
                    </c:if>

                    <%--<c:if test="${actionBean.display eq 'false'}">
                         <s:text name="tanahrizabpermohonan1.penjaga" size="30"/>
                    </c:if>--%>
                    <c:if test="${actionBean.display eq 'true'}">
                         ${actionBean.tanahrizabpermohonan1.penjaga}&nbsp;
                    </c:if>
                    </p>
                </c:if>
            </p>
            <p>
                <label>Jenis Tanah :</label>
                <c:if test="${actionBean.display eq 'false'}">
                <s:select name="kodT" style="width:150px" value="" id="kodTanah" onchange="javaScript:showjenistanahlain(this.value)">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodTanah}" label="nama" value="kod"/>
                </s:select>
                </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.permohonanLaporanPelan.kodTanah.nama} &nbsp;
                    </c:if>
            </p>

             <p id="jenistanahlainlain">
                    <label>Lain-lain : </label>
                    <c:if test="${actionBean.display eq 'false'}">
                        <s:textarea name="kand" rows="5" cols="50"/>
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.kand}&nbsp;
                        </c:if>
                </p>



            <%--<c:if test="${actionBean.permohonan.penyerahNegeri.kod eq '04'}">--%>
            <c:if test="${actionBean.kodNegeri eq '04'}">
            <p>
                <label>Kawasan Parlimen :</label>
                <c:if test="${actionBean.display eq 'false'}">
                <s:select name="kodPar" style="width:150px" value="" id="kodPar" onchange="javaScript:changeHideDun(this.value)">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodKawasanparlimen}" label="nama" value="kod"/>
                </s:select>
                </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.kodKawasanParlimen.nama}&nbsp;
                    </c:if>
            </p>

            <p id="mT">
                <label>DUN :</label>
                <c:if test="${actionBean.display eq 'false'}">
                <s:select name="kodDmT" style="width:150px" value="" id="kodDmT" onchange="dun(this.value);">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:option value="N01">Kuala Linggi</s:option>
                    <s:option value="N02">Tanjung Bidara</s:option>
                    <s:option value="N03">Ayer Limau</s:option>
                    <s:option value="N04">Lendu</s:option>
                    <s:option value="N05">Taboh Naning</s:option>
                </s:select>
                </c:if>
                <c:if test="${actionBean.display eq 'true'}">
                    ${actionBean.hakmilikPermohonan.kodDUN.nama}&nbsp;
                </c:if>
            </p>

            <p id="aG">
                <label>DUN :</label>
                <c:if test="${actionBean.display eq 'false'}">
                <s:select name="kodDaG" style="width:150px" value="" id="kodDaG" onchange="dun(this.value);">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:option value="N06">Rembia</s:option>
                    <s:option value="N07">Gadek</s:option>
                    <s:option value="N08">Machap</s:option>
                    <s:option value="N09">Durian Tunggal</s:option>
                    <s:option value="N10">Asahan</s:option>
                </s:select>
                </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.kodDUN.nama}&nbsp;
                </c:if>
            </p>

            <p id="tB">
                <label>DUN :</label>
                <c:if test="${actionBean.display eq 'false'}">
                <s:select name="kodDtB" style="width:150px" value="" id="kodDtB" onchange="dun(this.value);">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:option value="N11">Sungai Udang</s:option>
                    <s:option value="N12">Pantai Kundor</s:option>
                    <s:option value="N13">Paya Rumput</s:option>
                    <s:option value="N14">Kelebang</s:option>
                </s:select>
                </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.kodDUN.nama}&nbsp;
                </c:if>
            </p>

            <p id="bK">
                <label>DUN :</label>
                <c:if test="${actionBean.display eq 'false'}">
                <s:select name="kodDbK" style="width:150px" value="" id="kodDbK" onchange="dun(this.value);">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:option value="N15">Bachang</s:option>
                    <s:option value="N16">Ayer Keroh</s:option>
                    <s:option value="N17">Bukit Baru</s:option>
                    <s:option value="N18">Ayer Molek</s:option>
                </s:select>
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.kodDUN.nama}&nbsp;
                </c:if>
            </p>

            <p id="kM">
                <label>DUN :</label>
                <c:if test="${actionBean.display eq 'false'}">
                <s:select name="kodDkM" style="width:150px" value="" id="kodDkM" onchange="dun(this.value);">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:option value="N19">Kesidang</s:option>
                    <s:option value="N20">Kota Laksamana</s:option>
                    <s:option value="N21">Duyong</s:option>
                    <s:option value="N22">Bandar Hilir</s:option>
                    <s:option value="N23">Telok Mas</s:option>
                </s:select>
                </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                    ${actionBean.hakmilikPermohonan.kodDUN.nama}&nbsp;
                </c:if>
            </p>

            <p id="jS">
                <label>DUN :</label>
                <c:if test="${actionBean.display eq 'false'}">
                <s:select name="kodDjS" style="width:150px" value="" id="kodDjS" onchange="dun(this.value);">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:option value="N24">Bemban</s:option>
                    <s:option value="N25">Rim</s:option>
                    <s:option value="N26">Serkam</s:option>
                    <s:option value="N27">Merlimau</s:option>
                    <s:option value="N28">Sungai Rambai</s:option>
                </s:select>
                </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.kodDUN.nama}&nbsp;
                </c:if>
            </p></c:if>

            <p>
                <label>Kedudukan Tanah :</label>
                 <c:if test="${actionBean.display eq 'false'}">
                <s:textarea name="hakmilikPermohonan.lokasi" rows="5" cols="70" class="normal_text"/>
                 </p> </c:if>
                 <c:if test="${actionBean.display eq 'true'}">
                          ${actionBean.hakmilikPermohonan.lokasi}&nbsp;
                 </c:if>
            <br/><%--<center>(Jika ada)</center>--%>
            <label>Muatnaik Gambar Tanah :</label>
              <s:select name="hmImej" style="width:300px;" id="hmImej" onchange="doSetDokumenHakmilik();">
                                <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                <s:options-collection collection="${actionBean.hakmilikImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                            </s:select>&nbsp;
                            <s:hidden name="idDokumen" id="idDokumenH" />${actionBean.hakmilik.idHakmilik}
                            <c:if test="${actionBean.display eq 'false'}">
                            <s:button name="uploadDoc" id="display" value="Tambah" class="btn" onclick="uploadForm('H');return false;"/>
                            </c:if>
            <div class="content" align="center">
                <div id="mohonTerdahulu">
                Permohonan Terdahulu
                <display:table class="tablecloth" name="${actionBean.permohonanManualList}" pagesize="20" cellpadding="0" cellspacing="0" id="line1">
                    <s:hidden name="" class="${line1_rowNum -1}" value="${line1.idMohonManual}"/>
                    <display:column title="No">${line1_rowNum}</display:column>
                    <display:column title="ID Permohanan/No Fail"  property="idPermohonan.idPermohonan"/>
                    <display:column title="Urusan" property="idPermohonan.kodUrusan.nama"/>
                    <display:column title="Status Keputusan" property="idPermohonan.keputusan.nama" />
                    <c:if test="${actionBean.display eq 'false'}">
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line1_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removePermohonanTerdahulu('${line1.idMohonManual}')"/>
                        </div>
                    </display:column></c:if>
                </display:table>
                <c:if test="${actionBean.display eq 'false'}">
                <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahPermohonanTerdahulu()"/>&nbsp;
                </c:if>
                </div>
            </div> <br/>
            <legend>Bersempadan</legend>
            <br>
            <div class="content" align="center">

                <table class="tablecloth">
                    <tr>
                        <th>Bersempadan</th><th>Nama</th><th>Jarak (KM)</th>
                    </tr>
                    <tr>
                        <td>
                            Jalan Raya
                        </td>
                        <td>
                            <c:if test="${actionBean.display eq 'false'}">
                            <s:text name="laporanTanah.namaSempadanJalanraya" id="namaSempadanJalanraya" />
                            </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.namaSempadanJalanraya}
                        </c:if>
                        </td>
                        <td>
                            <c:if test="${actionBean.display eq 'false'}">
                            <s:text name="laporanTanah.jarakSempadanJalanraya" id="jarakSempadanJalanraya" formatPattern="#,###,##0.0000" maxlength="4" onkeyup="validateNumber(this,this.value);"/>
                            </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.jarakSempadanJalanraya}
                        </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Landasan Keretapi
                        </td>
                        <td> <c:if test="${actionBean.display eq 'false'}">
                            <s:text name="laporanTanah.namaSempadanKeretapi" id="namaSempadanKeretapi" />
                            </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.namaSempadanKeretapi}
                        </c:if>
                        </td>
                        <td>
                            <c:if test="${actionBean.display eq 'false'}">
                            <s:text name="laporanTanah.jarakSempadanKeretapi" id="jarakSempadanKeretapi" formatPattern="#,###,##0.0000" maxlength="4" onkeyup="validateNumber(this,this.value);"/>
                            </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.jarakSempadanKeretapi}
                        </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Laut
                        </td>
                        <td>
                            <c:if test="${actionBean.display eq 'false'}">
                            <s:text name="laporanTanah.namaSempadanLaut" id="namaSempadanLaut" />
                            </c:if>
                             <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.namaSempadanLaut}
                        </c:if>
                        </td>
                        <td>
                            <c:if test="${actionBean.display eq 'false'}">
                            <s:text name="laporanTanah.jarakSempadanLaut" id="jarakSempadanLaut"  formatPattern="#,###,##0.0000" maxlength="4" onkeyup="validateNumber(this,this.value);"/>
                            </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.jarakSempadanLaut}
                        </c:if>
                        </td>
                    </tr><tr>
                        <td>
                            Sungai
                        </td>
                        <td>
                            <c:if test="${actionBean.display eq 'false'}">
                            <s:text name="laporanTanah.namaSempadanSungai" id="namaSempadanSungai" />
                            </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.namaSempadanSungai}
                        </c:if>
                        </td>
                        <td>
                             <c:if test="${actionBean.display eq 'false'}">
                            <s:text name="laporanTanah.jarakSempadanSungai" id="jarakSempadanSungai" formatPattern="#,###,##0.0000" maxlength="4" onkeyup="validateNumber(this,this.value);"/>
                             </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.jarakSempadanSungai}
                        </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Lain-lain
                        </td>
                        <td>
                            <c:if test="${actionBean.display eq 'false'}">
                            <s:text name="laporanTanah.namaSempadanlain" id="namaSempadanlain"/>
                            </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.namaSempadanlain}
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${actionBean.display eq 'false'}">
                            <s:text name="laporanTanah.jarakSempadanLain" id="jarakSempadanLain" formatPattern="#,###,##0.0000" maxlength="4" onkeyup="validateNumber(this,this.value);"/>
                            </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.jarakSempadanLain}
                        </c:if>
                        </td>
                    </tr>
                </table>
            </div>
            <p>
                <label>Jenis Jalan :</label>
                <c:if test="${actionBean.display eq 'false'}">
                <s:select name="laporanTanah.jenisJalan" style="width:230px" id="jenisJalan">
                    <s:option value="0"> Sila Pilih </s:option>
                    <s:option value="Jalan Berturap">Jalan Berturap</s:option>
                    <s:option value="Jalan Leterite">Jalan Laterite/Tanah Merah</s:option>
                    <%--<s:option value="Jalan Tanah Merah">Jalan Tanah Merah</s:option>--%>
                    <s:option value="Jalan Tanah">Jalan Tanah</s:option>
                    <s:option value="Jalan Kasaran (Batu)">Jalan Kasaran (Batu)</s:option>
                    <s:option value="Denai/Lorong">Denai/Lorong</s:option>
                </s:select>
                </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.jenisJalan}&nbsp;
                        </c:if>
            </p>
            <p>
                <label>Jalan Masuk :</label>
                <c:if test="${actionBean.display eq 'false'}">
                    <s:radio name="laporanTanah.adaJalanMasuk" value="Y" id="adaJalanMasuk"/>&nbsp;Ada
                <s:radio name="laporanTanah.adaJalanMasuk" value="T" id="adaJalanMasuk"/>&nbsp;Tiada
                </c:if>
                <c:if test="${actionBean.display eq 'true'}"><!--
                    ${actionBean.laporanTanah.adaJalanMasuk}&nbsp;
                    --><c:if test="${actionBean.laporanTanah.adaJalanMasuk eq 'Y'}">
                    	Ada &nbsp;
                    </c:if>
                    <c:if test="${actionBean.laporanTanah.adaJalanMasuk eq 'T'}">
                    	Tiada &nbsp;
                    </c:if>
               	</c:if>
            </p>
            <p>
                <label>Catatan :</label>
                <c:if test="${actionBean.display eq 'false'}">
                    <s:textarea name="laporanTanah.catatanJalanMasuk" id="catatanJalanMasuk" rows="5" cols="50"/>
                </c:if>
                <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.catatanJalanMasuk}&nbsp;
                        </c:if>
            </p>

        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <s:hidden name="laporanTanah.idLaporan"/>
            <s:hidden name="laporanTanah.permohonan.idPermohonan"/>
            <legend>Keadaan Tanah</legend>
                 <c:if test="${actionBean.kodNegeri eq '05'}">
                     <c:if test="${actionBean.display eq 'false'}">
                             <p>
                            <label>Jarak:</label>
                            <s:text name="hakmilikPermohonan.jarak" maxlength="6" onkeyup="validateNumber(this,this.value);" id="Jarak"/>
                            <s:select name="unitJarak.kod" value="${actionBean.hakmilikPermohonan.unitJarak.kod}" id="unitJarak">
                                <s:option value="">Sila Pilih</s:option>
                                <%--<s:option value="JK">Kaki</s:option>--%>
                                <s:option value="KM">Kilometer</s:option>
                                <s:option value="JM">Meter</s:option>
                            </s:select>
                            &nbsp;<font color="#003194"><b>dari</b></font>&nbsp;
                            <s:select name="hakmilikPermohonan.jarakDari" id="jarakDari">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="Jalan Raya">Jalan Raya</s:option>
                                <s:option value="Landasan Keretapi">Landasan Keretapi</s:option>
                                <s:option value="Pantai">Pantai</s:option>
                                <s:option value="Pekan">Pekan</s:option>
                                <s:option value="Sungai">Sungai</s:option>
                            </s:select>
                        </p>
                        <p>
                            <label>Jarak Dari Kediaman:</label>
                            <s:text name="hakmilikPermohonan.jarakDariKediaman" maxlength="6" onkeyup="validateNumber(this,this.value);" id="JarakKediaman"/>
                            <s:select name="jarakDariKediamanUom.kod" value="${actionBean.hakmilikPermohonan.jarakDariKediamanUom.kod}" id="jarakDariKediamanUom">
                                <s:option value="">Sila Pilih</s:option>
                                <%--<s:option value="JK">Kaki</s:option>--%>
                                <s:option value="KM">Kilometer</s:option>
                                <s:option value="JM">Meter</s:option>
                            </s:select>
                        </p>
                     </c:if>
                     <c:if test="${actionBean.display eq 'true'}">
                         <p>
                            <label>Jarak:</label>
                            ${actionBean.hakmilikPermohonan.jarak} <c:if test="${actionBean.hakmilikPermohonan.unitJarak.kod eq 'KM'}">Kilometer</c:if><c:if test="${actionBean.hakmilikPermohonan.unitJarak.kod eq 'JM'}">Meter</c:if>
                            &nbsp;<font color="#003194"><b>dari</b></font>&nbsp; ${actionBean.hakmilikPermohonan.jarakDari}
                        </p>
                        <p>
                            <label>Jarak Dari Kediaman:</label> ${actionBean.hakmilikPermohonan.jarakDariKediaman} <c:if test="${actionBean.hakmilikPermohonan.jarakDariKediamanUom.kod eq 'KM'}">Kilometer</c:if><c:if test="${actionBean.hakmilikPermohonan.jarakDariKediamanUom.kod eq 'JM'}">Meter</c:if>

                        </p>
                     </c:if>
                </c:if>
                 <c:if test="${actionBean.kodNegeri eq '04'}">
                      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR'}">
                          <c:if test="${actionBean.display eq 'false'}">
                             <p>
                            <label>Jarak:</label>
                            <s:text name="hakmilikPermohonan.jarak" maxlength="6" onkeyup="validateNumber(this,this.value);" id="Jarak"/>
                            <s:select name="unitJarak.kod" value="${actionBean.hakmilikPermohonan.unitJarak.kod}" id="uJarak">
                                <s:option value="">Sila Pilih</s:option>
                                <%--<s:option value="JK">Kaki</s:option>--%>
                                <s:option value="KM">Kilometer</s:option>
                                <s:option value="JM">Meter</s:option>
                            </s:select>
                            &nbsp;<font color="#003194"><b>dari</b></font>&nbsp;
                            <s:select name="hakmilikPermohonan.jarakDari" id="jarakDari" onchange="keluarNama();">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="Jalan Raya">Jalan Raya</s:option>
                                <s:option value="Landasan Keretapi">Landasan Keretapi</s:option>
                                <s:option value="Pantai">Pantai</s:option>
                                <s:option value="Pekan">Pekan</s:option>
                                <s:option value="Sungai">Sungai</s:option>
                            </s:select>
                        </p>
                        <p id="namaJarak">
                            <label>Nama Pantai/Pekan/Sungai :</label>
                            <s:text name="hakmilikPermohonan.jarakDariNama" id="namaJ" size="40"/>

                        </p>
                        <p>
                            <label>Jarak Dari Kediaman:</label>
                            <s:text name="hakmilikPermohonan.jarakDariKediaman" maxlength="6" onkeyup="validateNumber(this,this.value);" id="JarakKediaman"/>
                            <s:select name="jarakDariKediamanUom.kod" value="${actionBean.hakmilikPermohonan.jarakDariKediamanUom.kod}" id="uJarakKUOM">
                                <s:option value="">Sila Pilih</s:option>
                                <%--<s:option value="JK">Kaki</s:option>--%>
                                <s:option value="KM">Kilometer</s:option>
                                <s:option value="JM">Meter</s:option>
                            </s:select>
                        </p>
                     </c:if>
                     <c:if test="${actionBean.display eq 'true'}">
                         <p> <label>Jarak :</label>
                        <c:if test="${actionBean.hakmilikPermohonan.jarak ne null}">
                            ${actionBean.hakmilikPermohonan.jarak}
                            ${actionBean.hakmilikPermohonan.unitJarak.nama}&nbsp;dari
                            ${actionBean.hakmilikPermohonan.jarakDari}
                        </c:if>
                            <c:if test="${actionBean.hakmilikPermohonan.jarak eq null}">
                                &nbsp;
                            </c:if>
                        </p>
                         <p id="namaJarak">
                                <label>Nama Pantai/Pekan/Sungai :</label>
                                <c:if test="${actionBean.hakmilikPermohonan.catatan ne null}">
                                    ${actionBean.hakmilikPermohonan.catatan}
                                </c:if>
                                <c:if test="${actionBean.hakmilikPermohonan.catatan eq null}">
                                    &nbsp;
                                </c:if>

                            </p>
                         <p> <label>Jarak Dari Kediaman :</label>
                             <c:if test="${actionBean.hakmilikPermohonan.jarakDariKediaman ne null}">
                                 ${actionBean.hakmilikPermohonan.jarakDariKediaman}
                                 ${actionBean.hakmilikPermohonan.jarakDariKediamanUom.nama}
                             </c:if>
                            <c:if test="${actionBean.hakmilikPermohonan.jarakDariKediaman eq null}">
                                &nbsp;
                             </c:if>
                        </p>
                     </c:if>
                      </c:if>

                </c:if>
                <p>
                    <label>Kecerunan Tanah :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:select name="kecerunanT" id="kecerunanT" onchange="javaScript:changeKeadaanTanah(this.options[selectedIndex].text)">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodKecerunanTanah}" label="nama" value="kod" />
                    </s:select>
                    </c:if>
                        <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.kecerunanTanah.nama}&nbsp;
                        </c:if>
                </p>

                <p id="tinggi">
                    <label>Ketinggian Dari Paras Jalan (m) :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                        <s:text name="laporanTanah.ketinggianDariJalan" id="ketinggianDariJalan" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.ketinggianDariJalan}&nbsp;
                        </c:if>
                </p>&nbsp;
                <p id="cerun">
                    <label>Darjah Kecerunan :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                        <s:text name="laporanTanah.kecerunanBukit" id="kecerunanBukit" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.kecerunanBukit}&nbsp;
                        </c:if>
                </p>
                <p id="dalam">
                    <label>Dalam Paras Air (m) :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                        <s:text name="laporanTanah.parasAir" id="parasAir" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.parasAir}&nbsp;
                        </c:if>
                </p>
                <p>
                    <label>Klasifikasi Tanah :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:select name="klasifikasiT" id="klasifikasiT" value="${actionBean.laporanTanah.kodKeadaanTanah.kod}">
                        <s:option value="0">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodStrukturTanah}" label="nama" value="kod" />
                    </s:select>
                    </c:if>
                        <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.strukturTanah.nama}&nbsp;
                        </c:if>
                </p>
                <p>
                    <label>Jika Lain-lain :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                        <s:text name="laporanTanah.strukturTanahLain" id="strukturTanahLain" />
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.strukturTanahLain}&nbsp;
                        </c:if>
                </p>
                <p>
                    <label>Keadaan Tanah :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:select name="keadaanTanah" id="keadaanTanah" onchange="javaScript:showkeadaantanahlain(this.value)">
                        <s:option value="0">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodKeadaanTanah}" label="nama" value="kod" />
                    </s:select>
                    </c:if>
                        <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.kodKeadaanTanah.nama}&nbsp;
                        </c:if>
                </p>

                <p id="keadaantanahlainlain">
                    <label>Lain-lain : </label>
                    <c:if test="${actionBean.display eq 'false'}">
                        <s:textarea name="keadaankand" rows="5" cols="50"/>
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.keadaankand}&nbsp;
                        </c:if>
                </p>

                <p>
                    <label>Tanah Dipohon Bertumpu :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:select name="laporanTanah.tanahBertumpu" id="tanahBertumpu" >
                        <s:option value="0">--Sila Pilih--</s:option>
                        <s:option value="Y">Ya</s:option>
                        <s:option value="T">Tidak</s:option>
                    </s:select>
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}"><!--
                    	${actionBean.laporanTanah.tanahBertumpu}&nbsp;
	                    --><c:if test="${actionBean.laporanTanah.tanahBertumpu eq 'Y'}">
	                    	Ya &nbsp;
	                    </c:if>
	                    <c:if test="${actionBean.laporanTanah.tanahBertumpu eq 'T'}">
	                    	Tidak &nbsp;
	                    </c:if>
                    </c:if>
                    &nbsp; Pada :
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:select name="laporanTanah.keteranganTanahBertumpu" id="keteranganTanahBertumpu" >
                        <s:option value="0">--Sila Pilih--</s:option>
                        <s:option value="Jalanraya">Jalanraya</s:option>
                        <s:option value="Jalan Keretapi">Jalan Keretapi</s:option>
                        <s:option value="Sungai">Sungai</s:option>
                        <s:option value="Pantai">Pantai</s:option>
                        <s:option value="Tiada">Tiada</s:option>
                        <%--<s:options-collection collection="${listUtil.senaraiKodKecerunanTanah}" label="nama" value="kod" />--%>
                    </s:select>
                    </c:if>
                        <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.keteranganTanahBertumpu}&nbsp;
                        </c:if>
                </p>

                <%--<p>
                    <label>Lain-lain Keadaan :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:textarea name="laporanTanah.keadaanTanah" rows="5" cols="50"/>
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.keadaanTanah}&nbsp;
                        </c:if>
                </p>--%>

                <br>
                <p>
                    <label>Dilintasi Oleh :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                        <s:checkbox name="laporanTanah.dilintasTiangElektrik" value="Y" id="dilintasTiangElektrik"/>&nbsp; Talian Elektrik<br>
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq 'Y'}">
                              Talian Elektrik - Ya<br>
                        </c:if>
                         <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq null}">
                              Talian Elektrik - Tiada<br>
                        </c:if>
                        </c:if>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <c:if test="${actionBean.display eq 'false'}">
                        <s:checkbox name="laporanTanah.dilintasTiangTelefon" value="Y" id="dilintasTiangTelefon"/>&nbsp; Talian Telefon<br>
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                         <c:if test="${actionBean.laporanTanah.dilintasTiangTelefon eq 'Y'}">
                              Talian Telefon - Ya<br>
                        </c:if>
                         <c:if test="${actionBean.laporanTanah.dilintasTiangTelefon eq null}">
                              Talian Telefon - Tiada<br>
                        </c:if>
                    </c:if>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:checkbox name="laporanTanah.dilintasLaluanGas" value="Y" id="dilintasLaluanGas"/>&nbsp; Laluan Gas<br>
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        <c:if test="${actionBean.laporanTanah.dilintasLaluanGas eq 'Y'}">
                              Laluan Gas - Ya<br>
                        </c:if>
                         <c:if test="${actionBean.laporanTanah.dilintasLaluanGas eq null}">
                              Laluan Gas - Tiada<br>
                        </c:if>

                    </c:if>

                </p>
                <p>
                    <label>&nbsp;</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:checkbox name="laporanTanah.dilintasPaip" value="Y" id="dilintasPaip"/>&nbsp; Paip Air<br>
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        <c:if test="${actionBean.laporanTanah.dilintasPaip eq 'Y'}">
                              Paip Air - Ya<br>
                        </c:if>
                         <c:if test="${actionBean.laporanTanah.dilintasPaip eq null}">
                              Paip Air - Tiada<br>
                        </c:if>

                    </c:if>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:checkbox name="laporanTanah.dilintasTaliar" value="Y" id="dilintasTaliar"/>&nbsp; Tali Air<br>
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                         <c:if test="${actionBean.laporanTanah.dilintasTaliar eq 'Y'}">
                              Tali Air - Ya<br>
                        </c:if>
                         <c:if test="${actionBean.laporanTanah.dilintasTaliar eq null}">
                              Tali Air - Tiada<br>
                        </c:if>

                    </c:if>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:checkbox name="laporanTanah.dilintasSungai" value="Y" id="dilintasSungai"/>&nbsp; Sungai<br>
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                         	<c:if test="${actionBean.laporanTanah.dilintasSungai eq 'Y'}">
                              Sungai - Ya<br>
                        </c:if>
                         <c:if test="${actionBean.laporanTanah.dilintasSungai eq null}">
                              Sungai - Tiada<br>
                        </c:if>

                    </c:if>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:checkbox name="laporanTanah.dilintasParit" value="Y" id="dilintasParit"/>&nbsp; Parit<br>
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        <c:if test="${actionBean.laporanTanah.dilintasParit eq 'Y'}">
                              Parit- Ya<br>
                        </c:if>
                         <c:if test="${actionBean.laporanTanah.dilintasParit eq null}">
                              Parit- Tiada<br>
                        </c:if>

                    </c:if>
                </p>

      <%--  </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Latar belakang Tanah</legend>--%>
                <p>
                    <label>Tanah Diusahakan :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:radio name="laporanTanah.usaha" value="Y" id="usaha" onclick="showpengusuka();"/>&nbsp;Ya
                    <s:radio name="laporanTanah.usaha" value="T"  id="usaha" onclick="hidepengusuka();"/>&nbsp;Tidak
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}"><!--
                        ${actionBean.laporanTanah.usaha} &nbsp;
                        --><c:if test="${actionBean.laporanTanah.usaha eq 'Y'}">
                        	Ya &nbsp;
                        </c:if>
                        <c:if test="${actionBean.laporanTanah.usaha eq 'T'}">
                        	Tidak &nbsp;
                        </c:if>
                    </c:if>
                </p>

                <p id="pengusuka">
                    <label>Nama Pengusaha :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:text name="laporanTanah.diusaha" size="40" id="diusaha"/>
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.laporanTanah.diusaha} &nbsp;
                    </c:if>
                </p>
                <p id="tarikh">
                    <label>Tarikh Mula Usaha :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:text name="laporanTanah.tarikhMulaUsaha2" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy"/>
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        <fmt:formatDate value="${actionBean.laporanTanah.tarikhMulaUsaha2}" pattern="dd/MM/yyyy" />
                    </c:if>
                </p>
                <p id="tempoh">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                    <label>Tempoh Usaha Tanah :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                        <s:text name="laporanTanah.tarikhMulaUsaha" id="tempohUsaha"/>
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.laporanTanah.tarikhMulaUsaha}
                    </c:if>
                    </c:if>
                </p>
                <div class="content" align="center" id="laterbelaktable">
                    <table class="tablecloth">
                        <tr>
                            <th>Jenis Tanaman</th><th>Luas Yang Ditanam (meter persegi)</th><th>anggaran Bil.pokok</th><th>Nilaian Tanah Termasuk Tanaman(RM)</th>
                        </tr>
                        <tr align="center">
                            <td>
                                <c:if test="${actionBean.display eq 'false'}">
                                    <s:textarea name="laporanTanah.usahaTanam" id="usahaTanam" cols="15" rows="2" />
                                </c:if>
                                <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.laporanTanah.usahaTanam} &nbsp;
                    </c:if>
                            </td>
                            <td>
                                 <c:if test="${actionBean.display eq 'false'}">
                                <s:text name="usahaLuas" id="usahaLuas" size="35" onkeyup="validateNumber(this,this.value);"/>
                                 </c:if>
                                <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.usahaLuas} &nbsp;
                    </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.display eq 'false'}">
                                <s:text name="usahaBilanganPokok" id="usahaBilanganPokok" onkeyup="validateNumber(this,this.value);"/>
                                </c:if>
                                <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.usahaBilanganPokok} &nbsp;
                    </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.display eq 'false'}">
                                <s:text name="usahaHarga" id="usahaHarga" size="35" onkeyup="validateNumber(this,this.value);"/>
                                </c:if>
                                <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.usahaHarga} &nbsp;
                    </c:if>
                            </td>
                        </tr></table>
                </div>
                <p>
                    <label>Bangunan :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <%--<s:hidden name="adaBangunan" id="adaBangunan" value="${actionBean.laporanTanah.adaBangunan}"/>--%>
                    <s:radio name="laporanTanah.adaBangunan" value="Y" onclick="showBilBangunan(this.form);"/>&nbsp;Ada
                    <s:radio name="laporanTanah.adaBangunan" value="T" onclick="hideBilBangunan(this.form);"/>&nbsp;Tiada
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}"><!--
                        ${actionBean.laporanTanah.adaBangunan} &nbsp;
                        --><c:if test="${actionBean.laporanTanah.adaBangunan eq 'Y'}">
                        	Ada &nbsp;
                        </c:if>
                        <c:if test="${actionBean.laporanTanah.adaBangunan eq 'T'}">
                        	Tiada &nbsp;
                        </c:if>
                    </c:if>
                </p>
                <c:if test="${actionBean.laporanTanah.adaBangunan eq 'Y'}">
                    <div align="center">
                        <display:table class="tablecloth" name="${actionBean.permohonanLaporanBangunanList}" pagesize="5" cellpadding="0" cellspacing="0"
                                       requestURI="/pelupusan/laporan_tanah4"  id="line">
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Jenis Bangunan">
                                <c:if test="${line.jenisBangunan eq 'KK'}">
                                    kekal
                                </c:if>
                                <c:if test="${line.jenisBangunan eq 'SK'}">
                                    separuh kekal
                                </c:if>
                                <c:if test="${line.jenisBangunan eq 'SM'}">
                                    sementara
                                </c:if>
                            </display:column>
                            <display:column property="ukuran" title="Ukuran Bangunan" />
                            <display:column property="tahunDibina" title="Tahun Dibina" />
                            <display:column property="nilai" title="Nilai Bangunan" />
                            <display:column property="namaPemunya" title="Pemilik" />
                            <display:column property="namaKetua" title="Ketua Keluarga Yang Mendiami"/>
                            <display:column title="Status">
                                <c:if test="${line.jenisPendudukan.kod eq 'TT'}">
                                    Pemilik
                                </c:if>
                                <c:if test="${line.jenisPendudukan.kod eq 'TS'}">
                                    Pemilik dan Penyewa Bangunan
                                </c:if>
                                <c:if test="${line.jenisPendudukan.kod eq 'SS'}">
                                    Penyewa Tanah dan Bangunan
                                </c:if>
                            </display:column>
                             <c:if test="${actionBean.display eq 'false'}">
                            <display:column title="Kemaskini">
                                <div align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editLaporanBangunan('${line.idLaporBangunan}');"/>
                                </div>  <%--alert('edit'+${line.idLaporBangunan});--%>
                            </display:column></c:if>
                        </display:table>
                                    <c:if test="${actionBean.display eq 'false'}">
                            <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBangunan('B');"/>
                                    </c:if>
                    </div>

                </c:if>
<!--                <div class="content" align="center" id="Bangunantable" style="display:none">
                    <display:table class="tablecloth" name="${actionBean.permohonanLaporanBangunanList}" pagesize="5" cellpadding="0" cellspacing="0"
                                       requestURI="/pelupusan/laporan_tanah4"  id="line">
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Jenis Bangunan">
                                <c:if test="${line.jenisBangunan eq 'KK'}">
                                    kekal
                                </c:if>
                                <c:if test="${line.jenisBangunan eq 'SK'}">
                                    separuh kekal
                                </c:if>
                                <c:if test="${line.jenisBangunan eq 'SM'}">
                                    sementara
                                </c:if>
                            </display:column>
                            <display:column property="ukuran" title="Ukuran Bangunan" />
                            <display:column property="tahunDibina" title="Tahun Dibina" />
                            <display:column property="nilai" title="Nilai Bangunan" />
                            <display:column property="namaPemunya" title="Pemilik" />
                            <display:column property="namaKetua" title="Ketua Keluarga Yang Mendiami"/>
                            <display:column title="Status">
                                <c:if test="${line.jenisPendudukan.kod eq 'TT'}">
                                    Pemilik
                                </c:if>
                                <c:if test="${line.jenisPendudukan.kod eq 'TS'}">
                                    Pemilik dan Penyewa Bangunan
                                </c:if>
                                <c:if test="${line.jenisPendudukan.kod eq 'SS'}">
                                    Penyewa Tanah dan Bangunan
                                </c:if>
                            </display:column>
                             <c:if test="${actionBean.display eq 'false'}">
                            <display:column title="Kemaskini">
                                <div align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editLaporanBangunan('${line.idLaporBangunan}');"/>
                                </div>  <%--alert('edit'+${line.idLaporBangunan});--%>
                            </display:column></c:if>
                        </display:table>
                                    <c:if test="${actionBean.display eq 'false'}">
                            <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBangunan('B');"/>
                                    </c:if>
               </div>-->
                <p>
                    <label>Tanah sudah Diperenggan :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <%--<s:radio name="laporanTanah.perenggan" value="Y" />&nbsp;Ada
                    <s:radio name="laporanTanah.perenggan" value="T" />&nbsp;Tiada--%>
                    <s:radio name="laporanTanah.perenggan" value="Y" />&nbsp;Sudah
                    <s:radio name="laporanTanah.perenggan" value="T" />&nbsp;Belum
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}"><!--
                        ${actionBean.laporanTanah.perenggan} &nbsp;
                        --><c:if test="${actionBean.laporanTanah.perenggan eq 'Y'}">
                        	Sudah &nbsp;
                        </c:if>
                        <c:if test="${actionBean.laporanTanah.perenggan eq 'T'}">
                        	Belum &nbsp;
                        </c:if>
                    </c:if>
                </p>

                <p>
                    <label>Rancangan Kerajaan Atas Tanah (Jika Ada) :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:text name="laporanTanah.rancanganKerajaan" />
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.laporanTanah.rancanganKerajaan} &nbsp;
                    </c:if>
                </p>
                <p>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PHLP' }">
                    <label>Nilai Anggaran :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                        RM <s:text name="laporanTanah.nilaiTanah" formatPattern="###,###,###.00"></s:text>
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                     RM ${actionBean.laporanTanah.nilaiTanah}
                    </c:if>
                 </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PHLP' }">
                    &nbsp;
                </c:if>
            </p>
           </fieldset>
    </div>

    <br /><br />
    <div class="subtitle" id="updiv">
        <fieldset class="aras1">
            <%--<legend>Tanah Sekeliling</legend>--%>
            <legend><font color="red">*</font> Perihal Lot-Lot Bersempadan</legend>
            <div class="content" align="center">
                <table class="tablecloth">
                    <tr>
                        <th>&nbsp;</th><%--<th>Taraf Tanah</th>--%><th>Nombor Lot</th><th>Kegunaan</th><th>Catatan</th><th>Gambar</th>
                    </tr>
                    <tr>
                        <th>
                            Utara
                        </th>
                        <%--<td>
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>--%>
                        <td>
                            <c:if test="${actionBean.display eq 'false'}">
                            <s:text name="laporanTanah.sempadanUtaraNoLot" maxlength="10"/>
                            </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.laporanTanah.sempadanUtaraNoLot} &nbsp;
                    </c:if>
                        </td>
                        <td>
                            <c:if test="${actionBean.display eq 'false'}">
                            <s:textarea name="laporanTanah.sempadanUtaraKegunaan" rows="3" cols="20"/>
                            </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.laporanTanah.sempadanUtaraKegunaan} &nbsp;
                    </c:if>
                        </td>
                          <td>
                            <c:if test="${actionBean.display eq 'false'}">
                            <s:textarea name="laporanTanah.catatanSempadanUtara" rows="3" cols="20"/>
                            </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.catatanSempadanUtara} &nbsp;
                            </c:if>
                        </td>
                        <td>
                            <s:select name="utaraImej" style="width:300px;" id="utaraImej" onchange="doSetDokumenUtara();">
                                <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                <s:options-collection collection="${actionBean.utaraImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                            </s:select>&nbsp;
                            <s:hidden name="idDokumen" id="idDokumenU" />${actionBean.hakmilik.idHakmilik}
                            <c:if test="${actionBean.display eq 'false'}">
                            <s:button name="uploadDoc" id="display" value="Tambah" class="btn" onclick="uploadForm('U');return false;"/>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            Selatan
                        </th>
                        <%--<td>
                            <s:radio name="laporanTanah.sempadanSelatanMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                            <s:radio name="laporanTanah.sempadanSelatanMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>--%>
                        <td>
                             <c:if test="${actionBean.display eq 'false'}">
                            <s:text name="laporanTanah.sempadanSelatanNoLot" maxlength="10"/>
                             </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.laporanTanah.sempadanSelatanNoLot} &nbsp;
                    </c:if>
                        </td>
                        <td>
                            <c:if test="${actionBean.display eq 'false'}">
                            <s:textarea name="laporanTanah.sempadanSelatanKegunaan" rows="3" cols="20"/>
                            </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.laporanTanah.sempadanSelatanKegunaan} &nbsp;
                    </c:if>
                        </td>
                         <td>
                            <c:if test="${actionBean.display eq 'false'}">
                            <s:textarea name="laporanTanah.catatanSempadanSelatan" rows="3" cols="20"/>
                            </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.catatanSempadanSelatan} &nbsp;
                            </c:if>
                        </td>
                        <td>
                            <s:select name="selatanImej" style="width:300px;" id="selatanImej" onchange="doSetDokumenSelatan();">
                                <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                <s:options-collection collection="${actionBean.selatanImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                            </s:select>&nbsp;
                            <s:hidden name="idDokumen" id="idDokumenS" />
                            <c:if test="${actionBean.display eq 'false'}">
                            <s:button name="uploadDoc" id="display" value="Tambah" class="btn" onclick="uploadForm('S');return false;"/>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            Timur
                        </th>
                        <%--<td>
                            <s:radio name="laporanTanah.sempadanTimurMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                            <s:radio name="laporanTanah.sempadanTimurMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>--%>
                        <td>
                            <c:if test="${actionBean.display eq 'false'}">
                            <s:text name="laporanTanah.sempadanTimurNoLot" maxlength="10"/>
                            </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.laporanTanah.sempadanTimurNoLot} &nbsp;
                    </c:if>
                        </td>
                        <td>
                            <c:if test="${actionBean.display eq 'false'}">
                            <s:textarea name="laporanTanah.sempadanTimurKegunaan" rows="3" cols="20"/>
                            </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.laporanTanah.sempadanTimurKegunaan} &nbsp;
                    </c:if>
                        </td>
                         <td>
                            <c:if test="${actionBean.display eq 'false'}">
                            <s:textarea name="laporanTanah.catatanSempadanTimur" rows="3" cols="20"/>
                            </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.catatanSempadanTimur} &nbsp;
                            </c:if>
                        </td>
                        <td>
                            <s:select name="timurImej" style="width:300px;" id="timurImej" onchange="doSetDokumenTimur();">
                                <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                <s:options-collection collection="${actionBean.timurImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                            </s:select>&nbsp;
                            <s:hidden name="idDokumen" id="idDokumenT" />
                            <c:if test="${actionBean.display eq 'false'}">
                            <s:button name="uploadDoc" id="display" value="Tambah" class="btn" onclick="uploadForm('T');return false;"/>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            Barat
                        </th>
                        <%--<td>
                            <s:radio name="laporanTanah.sempadanBaratMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                            <s:radio name="laporanTanah.sempadanBaratMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>--%>
                        <td>
                            <c:if test="${actionBean.display eq 'false'}">
                            <s:text name="laporanTanah.sempadanBaratNoLot" maxlength="10"/>
                            </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.laporanTanah.sempadanBaratNoLot} &nbsp;
                    </c:if>
                        </td>
                        <td>
                            <c:if test="${actionBean.display eq 'false'}">
                            <s:textarea name="laporanTanah.sempadanBaratKegunaan" rows="3" cols="20"/>
                            </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.laporanTanah.sempadanBaratKegunaan} &nbsp;
                    </c:if>
                        </td>
                         <td>
                            <c:if test="${actionBean.display eq 'false'}">
                            <s:textarea name="laporanTanah.catatanSempadanBarat" rows="3" cols="20"/>
                            </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.laporanTanah.catatanSempadanBarat} &nbsp;
                            </c:if>
                        </td>
                        <td>
                            <s:select name="baratImej" style="width:300px;" id="baratImej" onchange="doSetDokumenBarat();">
                                <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                <s:options-collection collection="${actionBean.baratImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                            </s:select>&nbsp;
                            <s:hidden name="idDokumen" id="idDokumenB" />
                             <c:if test="${actionBean.display eq 'false'}">
                            <s:button name="uploadDoc" id="display" value="Tambah" class="btn" onclick="uploadForm('B');return false;"/>
                             </c:if>
                        </td>
                    </tr>
                </table>
            </div>
        </fieldset>
    </div>

        <div class="subtitle" align="center">
            <fieldset class="aras1" id="locate">
        <legend>
            Dalam Kawasan
        </legend>
        <br>
        <p>
                <%--<label>Status Tanah :</label>--%>
                Tanah Dipohon Berada Dalam Kawasan :
                <%--<s:radio name="pbt" value="Y" />Ya--%>
                <%--<s:checkbox name="pbt" value="1"/> Di bawah Kawalan Pihak Berkuasa Tempatan--%>
                <display:table  name="${actionBean.senaraiLaporanKawasan}" id="line9" class="tablecloth">
                        <s:hidden name="" class="${line9_rowNum -1}" value="${line9.idMohonlaporKws}"/>
                        <display:column title="No">${line9_rowNum}</display:column>
                        <display:column title="Jenis Rizab"  property="kodRizab.nama"/>
                        <display:column title="Catatan">
                            <c:if test="${line9.catatan ne null}">
                                ${line9.catatan}
                            </c:if>
                             <c:if test="${line9.catatan eq null}">
                                -
                            </c:if>
                        </display:column>
                        <display:column title="No Warta" property="noWarta"/>
                        <display:column title="Tarikh Warta" property="tarikhWarta" format="{0,date,dd-MM-yyyy}"/>
                        <display:column title="No Pelan Warta" property="noPelanWarta" />
                        <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line9_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeLaporKawasan('${line9.idMohonlaporKws}')"/>
                        </div>
                        </display:column>
                    </display:table><br/>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahKawasan()"/>&nbsp;
                    </c:if>
            </p>



<!--        <p>
            <label>Tanah Dipohon Berada Dalam Kawasan :</label>
            <c:if test="${actionBean.display eq 'false'}">
            <s:checkbox name="pbt1" value="1"/> Di bawah Kawalan Pihak Berkuasa Tempatan
            </c:if>
            <c:if test="${actionBean.display eq 'true'}">
                ${actionBean.pbt1} Di bawah Kawalan Pihak Berkuasa Tempatan &nbsp;
            </c:if>
        </p>
        <p>
            <label>&nbsp;</label>
            <c:if test="${actionBean.display eq 'false'}">
            <s:checkbox name="rizab_melayu" value="3" /> Tanah Rizab Melayu
            </c:if>
            <c:if test="${actionBean.display eq 'true'}">
            ${actionBean.rizab_melayu} Tanah Rizab Melayu &nbsp;
            </c:if>
        </p>
        <p>
            <label>&nbsp;</label>
            <c:if test="${actionBean.display eq 'false'}">
            <s:checkbox name="gsa" value="2" /> Rizab GSA
            </c:if>
            <c:if test="${actionBean.display eq 'true'}">
            ${actionBean.gsa} Rizab GSA &nbsp;
            </c:if>
        </p>
        <p>
            <label>&nbsp;</label>
            <c:if test="${actionBean.display eq 'false'}">
            <s:checkbox name="hutan" value="4" /> Rizab Hutan Simpan Kekal
            </c:if>
            <c:if test="${actionBean.display eq 'true'}">
            ${actionBean.hutan} Rizab Hutan Simpan Kekal &nbsp;
            </c:if>
        </p>
        <p>
            <label>&nbsp; </label>
            <c:if test="${actionBean.display eq 'false'}">
            <s:checkbox name="lain" value="99" /> Lain-lain
            </c:if>
            <c:if test="${actionBean.display eq 'true'}">
            ${actionBean.lain} Lain-lain &nbsp;
            </c:if>
        </p>
        <p>
            <label for="catatanLain">Catatan :</label>
            <c:if test="${actionBean.display eq 'false'}">
            <s:textarea name="catatanLain1" rows="5" cols="50" />
            </c:if>
            <c:if test="${actionBean.display eq 'true'}">
            ${actionBean.catatanLain1} &nbsp;
            </c:if>
        </p>-->
    </fieldset> </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <c:if test="${actionBean.kodNegeri eq '04'}">
                 <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT' }"><legend>Syor Penolong Pegawai Tanah</legend></c:if>
            </c:if>
            <c:if test="${actionBean.kodNegeri eq '05'}">
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT' }"><legend>Ulasan Penolong Pegawai Tanah</legend></c:if>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS' || actionBean.permohonan.kodUrusan.kod eq 'LPSP'}"><legend>Syor Penolong Pegawai Tanah</legend></c:if>
            <%--If PBMT--%>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT' }">
                <%--<p>
                    <label>Syor :</label>
                    <s:radio name="ksn" value="DI" onclick="showjikasokong();" />&nbsp;Sokong
                    <s:radio name="ksn" value="TI" onclick="showtidaksokong();" />&nbsp;Tidak Sokong
                </p>--%>
                <c:if test="${actionBean.kodNegeri eq '04'}">
                    <p>
                    <label>Syor :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:radio name="ksn" value="SL" onclick="showjikasokong();" />&nbsp;Sokong Permohonan
                    <s:radio name="ksn" value="SU" onclick="showpbmtsyorlulus();" />&nbsp;Syor Lulus LPS
                    <s:radio name="ksn" value="ST" onclick="showtidaksokong();" />&nbsp;Tidak Sokong
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.fasaPermohonan.keputusan.nama}&nbsp;
                    </c:if>
                </p>
                </c:if>
                <c:if test="${actionBean.kodNegeri eq '05'}">
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PBMT'}">
                        <p>
                        <label>Syor :</label>
                        <c:if test="${actionBean.display eq 'false'}">
                        <s:radio name="ksn" value="SL" onclick="showjikasokong();" />&nbsp;Sokong Permohonan
                        <s:radio name="ksn" value="SU" onclick="showpbmtsyorlulus();" />&nbsp;Syor Lulus LPS
                        <s:radio name="ksn" value="ST" onclick="showtidaksokong();" />&nbsp;Tidak Sokong
                        </c:if>
                        <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.fasaPermohonan.keputusan.nama}&nbsp;
                        </c:if>
                    </p>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                        <p>
                        <label>Pertimbangan :</label>
                        <c:if test="${actionBean.display eq 'false'}">
                        <s:radio name="ksn" value="SL" onclick="showjikasokong();" />&nbsp;Dipertimbangkan
                        <s:radio name="ksn" value="SU" onclick="showpbmtsyorlulus();" />&nbsp;Dipertimbangkan Untuk LPS
                        <s:radio name="ksn" value="ST" onclick="showtidaksokong();" />&nbsp;Tidak Disokong
                        </c:if>
                        <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.fasaPermohonan.keputusan.nama}&nbsp;
                        </c:if>
                    </p>
                </c:if>
                </c:if>

                <c:if test="${actionBean.kodNegeri eq '04'}">
                    <div id="jikasokong">
                    <p>
                        <label>Diluluskan :</label>
                        <c:if test="${actionBean.display eq 'false'}">
                        <s:radio name="hakmilikPermohonan.statusLuasDiluluskan" value="P" />&nbsp;Semua Kawasan dipohon
                        <s:radio name="hakmilikPermohonan.statusLuasDiluluskan" value="S" />&nbsp;Sebahagian Sahaja
                        </c:if>
                        <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.statusLuasDiluluskan}&nbsp;
                    </c:if>
                    </p>
                    <p>
                        <label>Luas Diluluskan:</label>
                        <c:if test="${actionBean.display eq 'false'}">
                        <s:text name="noPT.luasDilulus" size="20"/>
                        </c:if>
                        <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.noPT.luasDilulus}&nbsp;
                    </c:if>
                        <c:if test="${actionBean.display eq 'false'}">
                        <s:select name="kodU" style="width:150px" value="" id="koduom">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodUOM}" label="nama" value="kod"/>
                        </s:select>
                        </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.noPT.kodUOM.nama}&nbsp;
                    </c:if>
                    </p>
                   <%-- <p>
                        <label>Penjenisan :</label>
                        <s:select name="hakmilikPermohonan.penjenisan" id="penjenis" >
                            <s:option value="0">--Sila Pilih--</s:option>
                            <s:option value="Bangunan">Bangunan</s:option>
                            <s:option value="Pertanian">Pertanian</s:option>
                            <s:option value="Perusahaan">Perusahaan</s:option>
                        </s:select>
                    </p>--%>
                    <p>
                        <label>Jenis Hakmilik :</label>
                        <c:if test="${actionBean.display eq 'false'}">
                        <s:select name="kodHmlk" id="kodHmlk" >
                            <s:option value="0">--Sila Pilih--</s:option>
                            <s:option value="HSM">Hakmilik Sementara (Mukim)</s:option>
                            <s:option value="HSD">Hakmilik Sementara Daftar</s:option>
                            <%--<s:options-collection collection="${listUtil.senaraiKodKecerunanTanah}" label="nama" value="kod" />--%>
                        </s:select>
                        </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.kodHakmilik.nama}&nbsp;
                    </c:if>
                    </p>
                    <p>
                        <label>Tempoh Pajakan :</label>
                        <%--<s:text name="hakmilikPermohonan.tempohPajakan" size="20" onkeyup="validateNumber(this,this.value);"/>--%>
                        <c:if test="${actionBean.display eq 'false'}">
                        <s:select name="hakmilikPermohonan.tempohPajakan" id="tempohPajakan">
                            <s:option value="0">--Sila Pilih--</s:option>
                            <s:option value="30"> 30 </s:option>
                            <s:option value="60"> 60 </s:option>
                            <s:option value="99"> 99 </s:option>
                        </s:select>
                        </c:if>
                             <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.tempohPajakan}&nbsp;
                    </c:if>
                    </p>
                     <p>
                        <label>Premium :</label>
                        <c:if test="${actionBean.display eq 'false'}">
                        <s:select name="keteranganKadarPremium" id="nama" onchange="javaScript:showPremimTxt(this.value)">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${actionBean.senaraikodKadarPremium}" />
                        </s:select>
                           </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.keteranganKadarPremium}&nbsp;
                    </c:if>
                     <%--<p id="premiumTxt">
                         <label>Nilai :</label>
                         <c:if test="${actionBean.display eq 'false'}">
                         <s:text name="hakmilikPermohonan.kadarPremium" size="20" onkeyup="validateNumber(this,this.value);" />
                         </c:if>
                         <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.kadarPremium}&nbsp;
                    </c:if>--%>
                    </p>
                    <p>
                        <label>Hasil :</label>
                        <c:if test="${actionBean.display eq 'false'}">
                        <s:text name="hakmilikPermohonan.keteranganCukaiBaru" size="50"/>
                        </c:if>
                        <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.hakmilikPermohonan.keteranganCukaiBaru} &nbsp;
                    </c:if>
                    </p>
                    <p>
                        <label>Upah Ukur :</label>
                        <%--Mengikut PU(A)438 Juru ukur Berlesen--%>
                        <c:if test="${actionBean.display eq 'false'}">
                        <s:radio name="hakmilikPermohonan.agensiUpahUkur" value="JUP" />&nbsp;Mengikut PU(A)438
                        <s:radio name="hakmilikPermohonan.agensiUpahUkur" value="JUB" />&nbsp;JuruUkur Berlesen
                        </c:if>
                        <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.agensiUpahUkur}&nbsp;
                    </c:if>
                    </p>

                    <p>
                        <label>Syarat Nyata :</label>
                        <c:if test="${actionBean.display eq 'false'}">
                        <s:textarea name="syaratNyata" id="syaratNyata" rows="5" cols="80" readonly="yes" class="tooltips" value=""></s:textarea>
                        <s:hidden name="kod" id="kod"/>
                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup()"/>
                        </c:if>
                        <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}&nbsp;
                    </c:if>
                    </p>
                    <p>
                        <label>Sekatan Kepentingan :</label>
                        <c:if test="${actionBean.display eq 'false'}">
                        <s:textarea name="syaratNyata1" id="syaratNyata1" rows="5" cols="80" readonly="yes" class="tooltips" value=""></s:textarea>
                        <s:hidden name="kodSktn" id="kodSktn"/>
                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup2()"/>
                        </c:if>
                        <c:if test="${actionBean.display eq 'true'}">
                        <table border="0"> <tr><td>
                        ${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}&nbsp; <br/>
                                </td></tr></table>
                    </c:if>
                    </p>
                </div>
                </c:if>
                <c:if test="${actionBean.kodNegeri eq '05'}">
                    <div id="jikasokong">
                    <p>
                        <label>Diluluskan :</label>
                        <c:if test="${actionBean.display eq 'false'}">
                        <s:radio name="hakmilikPermohonan.statusLuasDiluluskan" value="P" />&nbsp;Semua Kawasan dipohon
                        <s:radio name="hakmilikPermohonan.statusLuasDiluluskan" value="S" />&nbsp;Sebahagian Sahaja
                        </c:if>
                        <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.statusLuasDiluluskan}&nbsp;
                    </c:if>
                    </p>
                    <p>
                        <label>Luas Diluluskan:</label>
                        <c:if test="${actionBean.display eq 'false'}">
                        <s:text name="luasDilulus" size="30"/>
                        </c:if>
                        <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.luasDilulus}&nbsp;
                    </c:if>
                        <c:if test="${actionBean.display eq 'false'}">
                        <s:select name="kodU" style="width:150px" value="" id="koduom">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodUOMByLuasLupus}" label="nama" value="kod"/>
                        </s:select>
                        </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.noPT.kodUOM.nama}&nbsp;
                    </c:if>
                    </p>
                   <%-- <p>
                        <label>Penjenisan :</label>
                        <s:select name="hakmilikPermohonan.penjenisan" id="penjenis" >
                            <s:option value="0">--Sila Pilih--</s:option>
                            <s:option value="Bangunan">Bangunan</s:option>
                            <s:option value="Pertanian">Pertanian</s:option>
                            <s:option value="Perusahaan">Perusahaan</s:option>
                        </s:select>
                    </p>--%>
                    <p>
                        <label>Jenis Hakmilik Sementara:</label>
                        <c:if test="${actionBean.display eq 'false'}">
                         <s:select name="kodHmlk" style="width:300px" id="kodHmlk">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodHakmilikSementara}" label="nama" value="kod"/>
                        </s:select>

                        </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.kodHakmilikSementara.nama}&nbsp;
                    </c:if>
                    </p>
                    <p>
                        <label>Jenis Hakmilik Tetap :</label>
                        <c:if test="${actionBean.display eq 'false'}">
                            <s:select name="kodHmlkTetap" style="width:150px" id="kodHmlkTetap">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodHakmilikTetap}" label="nama" value="kod"/>
                        </s:select>
                        </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.kodHakmilikTetap.nama}&nbsp;
                    </c:if>
                    </p>
                    <p>
                        <label>Tempoh Pajakan :</label>
                        <%--<s:text name="hakmilikPermohonan.tempohPajakan" size="20" onkeyup="validateNumber(this,this.value);"/>--%>
                        <c:if test="${actionBean.display eq 'false'}">
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PBMT'}">
                            <s:text name="hakmilikPermohonan.tempohPajakan" size="3"/>
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                                <s:select name="hakmilikPermohonan.tempohPajakan" id="tempohPajakan">
                                    <s:option value="0">--Sila Pilih--</s:option>
                                    <s:option value="30"> 30 </s:option>
                                    <s:option value="60"> 60 </s:option>
                                    <s:option value="99"> 90 </s:option>
                                </s:select>
                            </c:if>
                        </c:if>
                             <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.tempohPajakan}&nbsp;
                    </c:if>
                    </p>
                     <p>
                        <label>Kadar Premium :</label>Mengikut NSPU 25/2002 dan Jabatan Penilaian dan Perkhidmatan Harta
                        <%--
                        <c:if test="${actionBean.display eq 'false'}">
                            <s:text name="keteranganKadarPremium" maxlength="300" size="50"/>

                           </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.keteranganKadarPremium}&nbsp;
                        </c:if>--%>
                     <%--<p id="premiumTxt">
                         <label>Nilai :</label>
                         <c:if test="${actionBean.display eq 'false'}">
                         <s:text name="hakmilikPermohonan.kadarPremium" size="20" onkeyup="validateNumber(this,this.value);" />
                         </c:if>
                         <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.kadarPremium}&nbsp;
                    </c:if>--%>
                    </p>
                    <p>
                        <label>Kadar Cukai :</label>Mengikut NSPU 7/2005
                        <%--<c:if test="${actionBean.display eq 'false'}">
                        <s:text name="hakmilikPermohonan.keteranganCukaiBaru" size="50"/>
                        </c:if>
                        <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.hakmilikPermohonan.keteranganCukaiBaru}&nbsp;
                        </c:if>--%>
                    </p>
                    <p>
                        <label>Upah Ukur :</label>
                        <%--Mengikut PU(A)438 Juru ukur Berlesen--%>
                        <c:if test="${actionBean.display eq 'false'}">
                        <s:radio name="hakmilikPermohonan.agensiUpahUkur" value="JUP" />&nbsp;Mengikut PU(A)438
                        <s:radio name="hakmilikPermohonan.agensiUpahUkur" value="JUB" />&nbsp;JuruUkur Berlesen
                        </c:if>
                        <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.agensiUpahUkur}&nbsp;
                    </c:if>
                    </p>
                    <p>
                        <label>Kegunaan :</label>
                        <%--Mengikut PU(A)438 Juru ukur Berlesen--%>
                        <c:if test="${actionBean.display eq 'false'}">
                        <s:select name="hakmilikPermohonan.penjenisan" id="kegunaan">
                            <s:option value="0">--Sila Pilih--</s:option>
                            <s:option value="01"> Bangunan </s:option>
                            <s:option value="02"> Pertanian </s:option>
                            <s:option value="03"> Perusahaan </s:option>
                        </s:select>
                        </c:if>
                        <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.penjenisan}&nbsp;
                    </c:if>
                    </p>
                    <p>
                        <label>Syarat Nyata :</label>
                        <c:if test="${actionBean.display eq 'false'}">
                        <s:textarea name="syaratNyata" id="syaratNyata" rows="5" cols="80" readonly="yes" class="tooltips" value=""></s:textarea>
                        <s:hidden name="kod" id="kod"/>
                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup()"/>
                        </c:if>
                        <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}&nbsp;
                    </c:if>
                    </p>
                    <p>
                        <label>Sekatan Kepentingan :</label>
                        <c:if test="${actionBean.display eq 'false'}">
                        <s:textarea name="syaratNyata1" id="syaratNyata1" rows="5" cols="80" readonly="yes" class="tooltips" value=""></s:textarea>
                        <s:hidden name="kodSktn" id="kodSktn"/>
                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup2()"/>
                        </c:if>
                        <c:if test="${actionBean.display eq 'true'}">
                        <table border="0"> <tr><td>
                        ${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}&nbsp; <br/>
                                </td></tr></table>
                    </c:if>
                    </p>
                </div>
                </c:if>

                <p id="tidaksokong">
                    <label>Sebab :</label>
                     <c:if test="${actionBean.display eq 'false'}">
                    <s:textarea name="ulasan" rows="5" cols="80" id="ulasan" value="${actionBean.fasaPermohonan.ulasan}" />
                     </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.fasaPermohonan.ulasan}&nbsp; <br/>
                    </c:if>
                </p>

                <div id="pbmtsyorlulus">
                <p>
                    <label>Kegunaan :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:select name="keg" style="width:300px" id="keg">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodItemPermit}" label="nama" value="kod"/>
                        </s:select>
                    </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                                ${actionBean.permohonanPermitItem.kodItemPermit.nama}&nbsp;
                    </c:if>
                </p>


                <p>
                    <label>Bayaran (RM) :</label>
                     <c:if test="${actionBean.display eq 'false'}">
                    <s:text name="amnt" size="20" onkeyup="validateNumber(this,this.value);"/>
                     </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.permohonanTuntutanKos.amaunTuntutan}&nbsp;
                    </c:if>
                     Setahun
                </p>

                <p>

                    <label>Luas :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                        <s:text name="hakmilikPermohonan.luasTerlibat" size="20"/>
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.luasTerlibat}&nbsp;
                    </c:if>
                    <c:if test="${actionBean.display eq 'false'}">
                        <s:select name="kodU" style="width:150px" value="" id="koduom">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodUOMByLuasLupus}" label="nama" value="kod"/>
                        </s:select>
                    </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}&nbsp;
                    </c:if>

                    </p>


                <%--<p>
                    <label>Syarat Tambahan :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:textarea name="plpulasan2" rows="5" cols="80" />
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.plpulasan2}&nbsp;
                    </c:if>
                </p>--%>
                <p>
                    <label>Syarat Tambahan :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:textarea name="ulsn" rows="5" cols="80" />
                    </c:if>
                     <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.ulsn}&nbsp;
                    </c:if>
                </p>
                </div>
            </c:if>
            <%--if LPS--%>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS'}">

                <p>
                    <label>Syor :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:radio name="ksn" value="SL" onclick="showplpssokong();" />&nbsp;Sokong
                    <s:radio name="ksn" value="ST" onclick="showplpstidaksokong();" />&nbsp;Tidak Sokong
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.fasaPermohonan.keputusan.nama}&nbsp;
                    </c:if>
                </p>

                <div id="plpssokong">
                <p>
                    <label>Kegunaan :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                        <s:select name="keg" style="width:250px" id="keg" onchange="openLain_lain(this.value)">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodItemLPS}" label="nama" value="kod"/>
                        </s:select>
                    </c:if>
                             <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.permohonanPermitItem.kodItemPermit.nama}&nbsp;
                    </c:if>
                </p>
                <div id="catatanKegunaan">
                    <p>
                    <label>Catatan :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                        <s:textarea name="catatanKeg" rows="5" cols="80"/>
                    </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                                ${actionBean.catatanKeg}&nbsp;
                    </c:if>
                    </p>
                </div>
                <p>
                    <label>Bayaran (RM) :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:text name="amnt" size="20" onkeyup="validateNumber(this,this.value);"/>
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.permohonanTuntutanKos.amaunTuntutan}&nbsp;
                    </c:if>
                     Setahun
                </p>
                 <p>
                    <label></label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:radio name="hakmilikPermohonan.statusLuasDiluluskan" value="P" onclick="showluaspenuh();" />&nbsp;Keluasan Penuh
                    <s:radio name="hakmilikPermohonan.statusLuasDiluluskan" value="S" onclick="showluassbhgn();" />&nbsp;Keluasan Sebahagian
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        <c:if test="${actionBean.hakmilikPermohonan.statusLuasDiluluskan eq 'P'}">
                            Keluasan Penuh
                        </c:if>
                         <c:if test="${actionBean.hakmilikPermohonan.statusLuasDiluluskan eq 'S'}">
                            Keluasan Sebahagian
                        </c:if>
                            &nbsp;
                    </c:if>
                </p>
                <div id="luassbhgn">
                    <p>
                    <label>Luas Diluluskan:</label>
                     <c:if test="${actionBean.display eq 'false'}">
                         <s:text name="hakmilikPermohonan.luasDiluluskan" formatPattern="#,###,##0.0000" size="20"/>
                     </c:if>
                        <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.luasDiluluskan}&nbsp;
                    </c:if>
                    <c:if test="${actionBean.display eq 'false'}">
                        <s:select name="kodU" style="width:150px" value="" id="koduom">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodUOM}" label="nama" value="kod"/>
                        </s:select>
                    </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.luasLulusUom.nama}&nbsp;
                    </c:if>
                    </p>
                </div>

                <%--<p>
                    <label>Syarat Tambahan :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:textarea name="plpulasan2" rows="5" cols="80" />
                    </c:if>
                     <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.plpulasan2}&nbsp;
                    </c:if>
                </p>--%>
                <p>
                    <label>Syarat Tambahan :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:textarea name="ulsn" rows="5" cols="80" />
                    </c:if>
                     <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.ulsn}&nbsp;
                    </c:if>
                </p>
                </div>

               <p id="plpstidaksokong">
                    <label>Sebab :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:textarea name="ulasan" rows="5" cols="80" id="ulasan" value="${actionBean.fasaPermohonan.ulasan}" />
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">

                        <c:if test="${actionBean.fasaPermohonan.ulasan eq null}">
                        	- &nbsp;
                        </c:if>
                        <c:if test="${actionBean.fasaPermohonan.ulasan ne null}">
                        	${actionBean.fasaPermohonan.ulasan}&nbsp;
                        </c:if>
                    </c:if>
                </p>
            </c:if>
            <%--if PPRU--%>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">

                <p>
                    <label>Syor :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:radio name="ksn" value="SL" onclick="showpprusokong();" />&nbsp;Sokong
                    <s:radio name="ksn" value="ST" onclick="showpprutidaksokong();" />&nbsp;Tidak Sokong
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.fasaPermohonan.keputusan.nama}&nbsp;
                    </c:if>
                </p>

                <div id="pprusokong">
                <p>
                    <label>Bayaran (RM) :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:text name="amnt" size="20" onkeyup="validateNumber(this,this.value);"/>
                    </c:if>

                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.permohonanTuntutanKos.amaunTuntutan}&nbsp;
                    </c:if>

                </p>
                <p>
                <label>Keluasan Diluluskan (Isipadu) :</label>
                <c:if test="${actionBean.display eq 'false'}">
                     <s:text name="hakmilikPermohonan.luasDiluluskan" formatPattern="#,###,##0.0000" size="20"/>
                </c:if>
                <c:if test="${actionBean.display eq 'true'}">
                    ${actionBean.hakmilikPermohonan.luasDiluluskan}&nbsp;
                </c:if>
                <c:if test="${actionBean.display eq 'false'}">
                    <s:select name="kodU" id="koduom" style="width:100px">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="MP">Meter Padu</s:option>
                    </s:select>
                </c:if>
                <c:if test="${actionBean.display eq 'true'}">
                    ${actionBean.hakmilikPermohonan.luasLulusUom.nama}&nbsp;
                </c:if>
                </p>
                <p>
                    <label>Tempoh :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:text name="hakmilikPermohonan.tempohPajakan" size="3" onkeyup="validateNumber(this,this.value);"/>
                    </c:if>

                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.tempohPajakan}&nbsp;
                    </c:if>
                    Tahun.
                </p>
                </div>

               <p id="pprutidaksokong">
                    <label>Sebab :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:textarea name="ulasan" rows="5" cols="80" id="ulasan" value="${actionBean.fasaPermohonan.ulasan}" />
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        <c:if test="${actionBean.fasaPermohonan.ulasan eq null}">
                        	- &nbsp;
                        </c:if>
                        <c:if test="${actionBean.fasaPermohonan.ulasan ne null}">
                        	${actionBean.fasaPermohonan.ulasan}&nbsp;
                        </c:if>
                    </c:if>
                </p>
            </c:if>
            <%--if PPRU--%>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR'}">

                <p>
                    <label>Syor :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:radio name="ksn" value="SL" onclick="showpptrsokong();" />&nbsp;Sokong
                    <s:radio name="ksn" value="ST" onclick="showpptrtidaksokong();" />&nbsp;Tidak Sokong
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.fasaPermohonan.keputusan.nama}&nbsp;
                    </c:if>
                </p>

                <div id="pptrsokong">
                <p>
                    <label>Bayaran (RM) :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:text name="amnt" size="20" onkeyup="validateNumber(this,this.value);"/> &nbsp; Setahun
                    </c:if>

                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.permohonanTuntutanKos.amaunTuntutan}&nbsp; Setahun
                    </c:if>

                </p>

                <p>
                    <label>Tempoh :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:text name="hakmilikPermohonan.tempohPajakan" size="3" onkeyup="validateNumber(this,this.value);"/>
                    </c:if>

                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.tempohPajakan}&nbsp;
                    </c:if>
                    Tahun.
                </p>
                <p>
                <label>Luas Diluluskan:</label>
                <c:if test="${actionBean.display eq 'false'}">
                     <s:text name="hakmilikPermohonan.luasDiluluskan" size="15"/>
                </c:if>
                <c:if test="${actionBean.display eq 'true'}">
                    ${actionBean.hakmilikPermohonan.luasDiluluskan}&nbsp;
                </c:if>
                <c:if test="${actionBean.display eq 'false'}">
                    <s:select name="kodU" id="koduom">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="M">Meter Persegi</s:option>
                        <s:option value="H">Hektar</s:option>
                    </s:select>
                </c:if>
                <c:if test="${actionBean.display eq 'true'}">
                    ${actionBean.hakmilikPermohonan.luasLulusUom.nama}&nbsp;
                </c:if>
                </p>
                </div>

               <p id="pptrtidaksokong">
                    <label>Sebab :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:textarea name="ulasan" rows="5" cols="80" id="ulasan" value="${actionBean.fasaPermohonan.ulasan}" />
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        <c:if test="${actionBean.fasaPermohonan.ulasan eq null}">
                        	- &nbsp;
                        </c:if>
                        <c:if test="${actionBean.fasaPermohonan.ulasan ne null}">
                        	${actionBean.fasaPermohonan.ulasan}&nbsp;
                        </c:if>
                    </c:if>
                </p>
            </c:if>
            <%--if PRMP--%>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRMP'}">

                <p>
                    <label>Syor Penolong Pegawai:</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:radio name="ksn" value="SL" onclick="showprmpsokong();" />&nbsp;Sokong
                    <s:radio name="ksn" value="ST" onclick="showprmptidaksokong();" />&nbsp;Tidak Sokong
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.fasaPermohonan.keputusan.nama}&nbsp;
                    </c:if>
                </p>

                <%--<div id="plpssokong">
                <p>
                    <label>Kegunaan :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                        <s:select name="keg" style="width:250px" id="keg" onchange="openLain_lain(this.value)">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodItemPermit}" label="nama" value="kod"/>
                        </s:select>
                    </c:if>
                             <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.permohonanPermitItem.kodItemPermit.nama}&nbsp;
                    </c:if>
                </p>
                <div id="catatanKegunaan">
                    <p>
                    <label>Catatan :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                        <s:text name="catatanKeg" size="30" />
                    </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                                ${actionBean.catatanKeg}&nbsp;
                    </c:if>
                    </p>
                </div>
                --%>
                <div id="prmpsokong">
                <p>
                    <label>Kadar Bayaran (RM):</label>

                        ${actionBean.pmi.kodItemPermit.royaltiTanahKerajaan}&nbsp;se ${actionBean.pmi.kodItemPermit.royaltiTanahKerajaanUom.nama}
                          <s:hidden value="${actionBean.pmi.kodItemPermit.royaltiTanahKerajaan}" name="kadarBayarPRMP" id="kadarBayarPRMP"/>
                </p>
                <p>
                    <label>Luas Disyorkan</label>
                     <c:if test="${actionBean.display eq 'false'}">
                         <s:text name="hakmilikPermohonan.luasDiluluskan" id="luasLulus" formatPattern="#,###,##0.0000" size="20" onkeyup="calculateBayaranPRMP()"/>
                     </c:if>
                        <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.luasDiluluskan}&nbsp;
                    </c:if>
                    <c:if test="${actionBean.display eq 'false'}">
                        <s:select name="kodU" style="width:150px" value="" id="koduom" onchange="calculateBayaranPRMP()">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:option value="M">Meter Persegi</s:option>
                             <s:option value="H">Hektar</s:option>
                        </s:select>
                    </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.luasLulusUom.nama}&nbsp;
                    </c:if>
                </p>
                <p>
                    <label>Bayaran (RM) :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                        <s:text name="amnt" size="20" formatPattern="###,###,###,##0.0000" onkeyup="validateNumber(this,this.value);" id="amnt" readonly="true"/>
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.permohonanTuntutanKos.amaunTuntutan}&nbsp;
                    </c:if>
                     Setahun
                </p>
                <%--<p>
                    <label>Syarat Tambahan :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:textarea name="plpulasan2" rows="5" cols="80" />
                    </c:if>
                     <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.plpulasan2}&nbsp;
                    </c:if>
                </p>
                <p>
                    <label>Syarat Tambahan :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:textarea name="ulsn" rows="5" cols="80" />
                    </c:if>
                     <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.ulsn}&nbsp;
                    </c:if>
                </p>--%>
                </div>

               <p id="prmptidaksokong">
                    <label>Sebab :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:textarea name="ulasan" rows="5" cols="80" id="ulasan" value="${actionBean.fasaPermohonan.ulasan}" />
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">

                        <c:if test="${actionBean.fasaPermohonan.ulasan eq null}">
                        	- &nbsp;
                        </c:if>
                        <c:if test="${actionBean.fasaPermohonan.ulasan ne null}">
                        	${actionBean.fasaPermohonan.ulasan}&nbsp;
                        </c:if>
                    </c:if>
                </p>
            </c:if>
<!--                For LPSP-->
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">

                <p>
                    <label>Syor :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:radio name="ksn" value="SL" onclick="showlpspsokong();" />&nbsp;Sokong
                    <s:radio name="ksn" value="ST" onclick="showlpsptidaksokong();" />&nbsp;Tidak Sokong
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.fasaPermohonan.keputusan.nama}&nbsp;
                    </c:if>
                </p>
                 <div id="lpspsokong">
                     <p>
                    <label> Jumlah meter padu : </label>
                    <s:text name="permohonanBahanBatuan.jumlahIsipadu" size="10"/>
                    <s:select name="jumlahIsipaduUom.kod" id="isipaduUOM" value="${actionBean.permohonanBahanBatuan.jumlahIsipaduUom.kod}">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="MP">Meterpadu</s:option>
                    <s:option value="KB">Ketul Batu</s:option>
                    </s:select>
                     </p>
                     <p>
                    <label>Tempoh: </label><s:text name="permohonanBahanBatuan.tempohDisyor" size="20" id="tempoh" maxlength="3" onkeyup="validateNumber(this,this.value);" value="${actionBean.permohonanBahanBatuan.tempohDisyor}"/>
                <s:select name="tempohSyorUOM" id="tempohUOM">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="HR">Hari</s:option>
                    <s:option value="B">Bulan</s:option>
                    <s:option value="T">Tahun</s:option>
                </s:select>
                    </p>

                <p>
                    <label>Bayaran :</label>(RM)
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:text name="amnt" size="20" onkeyup="validateNumber(this,this.value);"/>
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.permohonanTuntutanKos.amaunTuntutan}&nbsp;
                    </c:if>
                     Setahun
                </p>
                <p>
                    <label>Luas Diluluskan:</label>
                     <c:if test="${actionBean.display eq 'false'}">
                         <s:text name="hakmilikPermohonan.luasDiluluskan" formatPattern="#,###,##0.0000" size="20"/>
                     </c:if>
                        <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.luasDiluluskan}&nbsp;
                    </c:if>
                    <c:if test="${actionBean.display eq 'false'}">
                        <s:select name="kodU" style="width:150px" value="" id="koduom">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodUOM}" label="nama" value="kod"/>
                        </s:select>
                    </c:if>
                            <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.luasLulusUom.nama}&nbsp;
                    </c:if>
                    </p>
                </div>

               <p id="lpsptidaksokong">
                    <label>Sebab :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:textarea name="ulasan" rows="5" cols="80" id="ulasan" value="${actionBean.fasaPermohonan.ulasan}" />
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">

                        <c:if test="${actionBean.fasaPermohonan.ulasan eq null}">
                        	- &nbsp;
                        </c:if>
                        <c:if test="${actionBean.fasaPermohonan.ulasan ne null}">
                        	${actionBean.fasaPermohonan.ulasan}&nbsp;
                        </c:if>
                    </c:if>
                </p>
            </c:if>
                <%--Batuan--%>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB' || actionBean.permohonan.kodUrusan.kod eq 'PBPTD' || actionBean.permohonan.kodUrusan.kod eq 'PBPTG'}">
                       <p>
                    <label>Syor :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:radio name="ksn" value="SL" onclick="showbatuansokong();" />&nbsp;Sokong
                    <s:radio name="ksn" value="ST" onclick="showbatuantidaksokong();" />&nbsp;Tidak Sokong
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.fasaPermohonan.keputusan.nama}&nbsp;
                    </c:if>
                </p>
                <div id="batuansokong">
                    <c:if test="${actionBean.display eq 'false'}">
                <p>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB' or actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                        <label>Jumlah Isipadu Dipohon :</label>
                            ${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohon}
                               <c:if test="${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.kod eq 'MP'}">Meterpadu</c:if>
                               <c:if test="${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.kod eq 'KB'}">Ketul Batu</c:if>
                   </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTG'}">
                    <label>Jumlah Isipadu Dipohon :</label>
                        ${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohon}
                        <c:if test="${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.kod eq 'MP'}">Meterpadu</c:if>

                        </c:if>
                         <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTD'}">
                    <label><font color="red">*</font>Jumlah isipadu Dipohon :</label>
                    ${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohon}
                        <c:if test="${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.kod eq 'MP'}">Meterpadu</c:if>
                        </c:if>
                 </p>
                     <p>
                    <label>Tempoh Dipohon :</label>
                        ${actionBean.permohonanBahanBatuan.tempohKeluar} ${actionBean.permohonanBahanBatuan.unitTempohKeluar.nama}
                          <c:if test="${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.kod eq 'MP'}">Meterpadu</c:if>
                               <c:if test="${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.kod eq 'KB'}">Ketul Batu</c:if>
                         <s:hidden name="jumlahIsipaduUom.kod" value="${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.kod}"/>
                         
                        </p>
                        <p>
                            <label>${actionBean.tempoh}${actionBean.noktahbertindih}</label>
                            <s:text name="syaratBahanBatu.tempohDisyor" id="syaratBahanBatu.tempohDisyor"/> ${actionBean.permohonanBahanBatuan.unitTempohKeluar.nama}
                            <s:hidden name="tempohSyorUOM" value="${actionBean.permohonanBahanBatuan.unitTempohKeluar.kod}"/>
                        </p>
                        <p>
                            <label>${actionBean.kadarBayar}
                                  ${actionBean.noktahbertindih} </label>RM
                            <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'H'}">
                                    <c:if test="${actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'TM' or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'BG'
                                                  or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'PS' or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'TL' or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'RP'
                                                  or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'BT'}">
                                        <s:format value="${actionBean.syaratBahanBatu.jenisBahanBatu.royaltiTanahMilik}" formatPattern="##0.00"/> <span style="text-transform:lowercase">se${actionBean.syaratBahanBatu.jenisBahanBatu.royaltiTanahMilikUom.nama}</span>
                                        <s:hidden name="bayaranSyarat" id="bayaranSyarat" value="${actionBean.syaratBahanBatu.jenisBahanBatu.royaltiTanahMilik}"/>
                                    </c:if>
                                </c:if>
                                <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'K'}">
                                    <c:if test="${actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'TM' or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'BG'
                                                  or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'PS' or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'TL' or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'RP'
                                                  or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'BT'}">
                                        <s:format value="${actionBean.syaratBahanBatu.jenisBahanBatu.royaltiTanahKerajaan}" formatPattern="##0.00"/> <span style="text-transform:lowercase">se${actionBean.syaratBahanBatu.jenisBahanBatu.royaltiTanahKerajaanUom.nama}</span>
                                        <s:hidden name="bayaranSyarat" id="bayaranSyarat" value="${actionBean.syaratBahanBatu.jenisBahanBatu.royaltiTanahKerajaan}"/>
                                    </c:if>
                                </c:if>
                        </p>
                        <p>
                            <label> ${actionBean.jumlahBayar}
                                  ${actionBean.noktahbertindih} </label>RM
                            <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'H'}">
                                                <c:if test="${actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'TM' or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'BG'
                                                              or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'PS' or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'TL' or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'RP'
                                                              or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'BT'}">
                                                    <s:format value="${actionBean.syaratBahanBatu.jenisBahanBatu.royaltiTanahMilik}" formatPattern="##0.00"/>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'K'}">
                                                <c:if test="${actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'TM' or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'BG'
                                                              or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'PS' or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'TL' or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'RP'
                                                              or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'BT'}">
                                                    <s:format value="${actionBean.syaratBahanBatu.jenisBahanBatu.royaltiTanahKerajaan}" formatPattern="##0.00"/>
                                                </c:if>
                                            </c:if>
                                            x <s:text name="syaratBahanBatu.jumlahIsipadu" id="kuantitiJumlahSyarat" readonly="true"/>${actionBean.jumlahBayar3}<s:text name="jumlahKeneBayar" id="jumlahSyarat" formatPattern="###,###,##0.00" readonly="true"/>
                        </p>
                        <p>
                            <label>
                                ${actionBean.wangCagar}${actionBean.noktahbertindih}
                            </label>${actionBean.wangCagar2}
                            <s:text name="cagarKeneBayar" id="cagaranSyarat" formatPattern="###,###,##0.00" readonly="true"/>
                        </p>
                        <p>
                            <label>
                                Cagaran Jalan :
                            </label>RM
                            <s:text name="cagarJalan" id="cagarJalan" formatPattern="###,###,##0.00" onblur="calculateBayarKupon()"/>
                        </p>
                        <p>
                            <label>
                                Bayaran Kupon ${actionBean.noktahbertindih}
                            </label>RM
                            <s:format formatPattern="#,##.00" value="${actionBean.kuponAmaun}"/>  * <s:text name="kuponQty" id="kuponQty" onkeyup="calculateBayarKupon()"/> = RM <s:text name="kupon" id="kupon" readonly="true"/>
                                <s:hidden name="kuponAmaun" id="kuponAmaun" value="${actionBean.kuponAmaun}"/>
                        </p>
                        <p>
                            <label>Jumlah Keseluruhan Bayaran : </label>RM
                            <s:text name="totalAll" id="totalAll" readonly="true"/>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">
                        <p>
                            <label> Jumlah Isipadu : </label>
                            ${actionBean.permohonanBahanBatuan.jumlahIsipadu}
                            ${actionBean.permohonanBahanBatuan.jumlahIsipaduUom.nama}

                        </p>
                        <p>
                            <label>Tempoh: </label>
                            ${actionBean.permohonanBahanBatuan.tempohDisyor}
                            ${actionBean.permohonanBahanBatuan.tempohSyorUom.nama}
                        </p>
                        <p>
                         <label>${actionBean.kuantiti}${actionBean.noktahbertindih}</label>${actionBean.syaratBahanBatu.jumlahIsipadu}${actionBean.syaratBahanBatu.jumlahIsipaduUom.nama}
                        </p>
                        <p>
                            <label>${actionBean.tempoh}${actionBean.noktahbertindih}</label>${actionBean.syaratBahanBatu.tempohDisyor}
                                    <c:if test="${actionBean.syaratBahanBatu.tempohSyorUom.kod eq 'T'}">
                                            Tahun
                                    </c:if>
                                    <c:if test="${actionBean.syaratBahanBatu.tempohSyorUom.kod eq 'B'}">
                                            Bulan
                                    </c:if>
                                    <c:if test="${actionBean.syaratBahanBatu.tempohSyorUom.kod eq 'HR'}">
                                            Hari
                                    </c:if>
                        </p>
                        <p>
                            <label>${actionBean.kadarBayar}
                                  ${actionBean.noktahbertindih} </label>RM
                            <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'H'}">
                                    <c:if test="${actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'TM' or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'BG'
                                                  or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'PS' or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'TL' or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'RP'
                                                  or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'BT'}">
                                        <s:format value="${actionBean.syaratBahanBatu.jenisBahanBatu.royaltiTanahMilik}" formatPattern="##0.00"/> <span style="text-transform:lowercase">se${actionBean.syaratBahanBatu.jenisBahanBatu.royaltiTanahMilikUom.nama}</span>
                                        <%--<s:hidden name="bayaranSyarat" id="bayaranSyarat" value="${actionBean.syaratBahanBatu.jenisBahanBatu.royaltiTanahMilik}"/>--%>
                                    </c:if>
                                </c:if>
                                <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'K'}">
                                    <c:if test="${actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'TM' or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'BG'
                                                  or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'PS' or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'TL' or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'RP'
                                                  or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'BT'}">
                                        <s:format value="${actionBean.syaratBahanBatu.jenisBahanBatu.royaltiTanahKerajaan}" formatPattern="##0.00"/> <span style="text-transform:lowercase">se${actionBean.syaratBahanBatu.jenisBahanBatu.royaltiTanahKerajaanUom.nama}</span>
                                        <%--<s:hidden name="bayaranSyarat" id="bayaranSyarat" value="${actionBean.syaratBahanBatu.jenisBahanBatu.royaltiTanahKerajaan}"/>--%>
                                    </c:if>
                                </c:if>
                        </p>
                        <p>
                            <label> ${actionBean.jumlahBayar}
                                  ${actionBean.noktahbertindih} </label>RM
                            <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'H'}">
                                                <c:if test="${actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'TM' or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'BG'
                                                              or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'PS' or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'TL' or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'RP'
                                                              or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'BT'}">
                                                    <s:format value="${actionBean.syaratBahanBatu.jenisBahanBatu.royaltiTanahMilik}" formatPattern="##0.00"/>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'K'}">
                                                <c:if test="${actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'TM' or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'BG'
                                                              or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'PS' or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'TL' or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'RP'
                                                              or actionBean.syaratBahanBatu.jenisBahanBatu.kod eq 'BT'}">
                                                    <s:format value="${actionBean.syaratBahanBatu.jenisBahanBatu.royaltiTanahKerajaan}" formatPattern="##0.00"/>
                                                </c:if>
                                            </c:if>
                                            x ${actionBean.syaratBahanBatu.jumlahIsipadu} ${actionBean.jumlahBayar3} ${actionBean.jumlahKeneBayar}
                        </p>
                        <p>
                            <label>
                                ${actionBean.wangCagar}${actionBean.noktahbertindih}
                            </label>${actionBean.wangCagar2}${actionBean.cagarKeneBayar}
                        </p>
                        <p>
                            <label>
                                Cagaran Jalan :
                            </label>RM ${actionBean.cagarJalan}
                        </p>
                        <p>
                            <label>
                                Bayaran Kupon ${actionBean.noktahbertindih}
                            </label>RM
                            <s:format formatPattern="#,##.00" value="${actionBean.kuponAmaun}"/>  * ${actionBean.kuponQty} = RM ${actionBean.kupon}
                                <%--<s:hidden name="kuponAmaun" id="kuponAmaun" value="${actionBean.kuponAmaun}"/>--%>
                        </p>
                        <p>
                            <label>Jumlah Keseluruhan Bayaran : </label>RM ${actionBean.totalAll}
                        </p>
                    </c:if>
                    </div>
                  <p id="batuantidaksokong">
                    <label>Sebab :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:textarea name="ulasan" rows="5" cols="80" id="ulasan" value="${actionBean.fasaPermohonan.ulasan}" />
                    </c:if>
                    <c:if test="${actionBean.display eq 'true'}">

                        <c:if test="${actionBean.fasaPermohonan.ulasan eq null}">
                        	- &nbsp;
                        </c:if>
                        <c:if test="${actionBean.fasaPermohonan.ulasan ne null}">
                        	${actionBean.fasaPermohonan.ulasan}&nbsp;
                        </c:if>
                    </c:if>
                </p>
                </c:if>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">

                <%--<p>
                    <label><font color="red">*</font>Ulasan :</label>
                    <c:if test="${actionBean.display eq 'false'}">
                    <s:textarea name="plpulasan1" rows="5" cols="80"/>
                    </c:if>
                     <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.plpulasan1}&nbsp;
                    </c:if>
                </p>--%>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG' or actionBean.permohonan.kodUrusan.kod eq 'PJLB'}">
                    <p>
                        <label>Bayaran (RM) :</label>
                        <c:if test="${actionBean.display eq 'false'}">
                        <s:text name="amnt" size="20" onkeyup="validateNumber(this,this.value);"/> &nbsp; Setahun
                        </c:if>

                        <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.permohonanTuntutanKos.amaunTuntutan}&nbsp; Setahun
                        </c:if>

                    </p>
                    <p>
                        <label>Tempoh :</label>
                        <c:if test="${actionBean.display eq 'false'}">
                        <s:text name="hakmilikPermohonan.tempohPajakan" size="3" onkeyup="validateNumber(this,this.value);"/>
                        </c:if>

                        <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.hakmilikPermohonan.tempohPajakan}&nbsp;
                        </c:if>
                        Tahun.
                    </p>
                    <p>
                        <label>Luas Disyorkan</label>
                        <c:if test="${actionBean.display eq 'false'}">
                             <s:text name="hakmilikPermohonan.luasDiluluskan" size="15"/>
                        </c:if>
                        <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.hakmilikPermohonan.luasDiluluskan}&nbsp;
                        </c:if>
                        <c:if test="${actionBean.display eq 'false'}">
                            <s:select name="kodU" id="koduom">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="M">Meter Persegi</s:option>
                                <s:option value="H">Hektar</s:option>
                            </s:select>
                        </c:if>
                        <c:if test="${actionBean.display eq 'true'}">
                            ${actionBean.hakmilikPermohonan.luasLulusUom.nama}&nbsp;
                        </c:if>
                    </p>
                    <p>
                        <label>Syarat Nyata :</label>
                        <c:if test="${actionBean.display eq 'false'}">
                        <s:textarea name="syaratNyata" id="syaratNyata" rows="5" cols="80" readonly="yes" class="tooltips" value=""></s:textarea>
                        <s:hidden name="kod" id="kod"/>
                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup()"/>
                        </c:if>
                        <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}&nbsp;
                    </c:if>
                    </p>
                    <p>
                        <label>Sekatan Kepentingan :</label>
                        <c:if test="${actionBean.display eq 'false'}">
                        <s:textarea name="syaratNyata1" id="syaratNyata1" rows="5" cols="80" readonly="yes" class="tooltips" value=""></s:textarea>
                        <s:hidden name="kodSktn" id="kodSktn"/>
                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup2()"/>
                        </c:if>
                        <c:if test="${actionBean.display eq 'true'}">
                        ${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}&nbsp;
                    </c:if>
                    </p>
                </c:if>
                     <p>
                         <label><font color="red">*</font>Ulasan :</label>
                         <s:textarea name="plpulasan0" id="plpulasan0"  rows="7"  cols="69" class="normal_text" value="${actionBean.plpulasan0}"></s:textarea>
                    </p>
                    <p id="pulasan">
                       <c:set var="i" value="1" />
                       <c:set var="j" value="0" />
                       <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line" begin="0">
                       <s:hidden  name="idLaporUlas${i}" id="idLaporUlas${i}" value="${line.idLaporUlas}"/>
                       <label>&nbsp;</label>
                       <s:textarea name="plpulasan${i}" id="plpulasan${i}"  rows="7" cols="69" class="normal_text" value="${line.ulasan}"></s:textarea>
                       <s:button class="btn" value="Hapus" name="delete${i}" onclick="deleteRow2_new_new(${i})" style=" " />
                       <c:set var="j" value="${i}" />
                       <c:set var="i" value="${i+1}" />
                       </c:forEach>
                       <s:hidden  name="countElement" id="countElement" value="${j}"/>
                       <s:hidden  name="flagElement" id="flagElement" value=""/>
                       <s:hidden  name="testElement" id="testElement" value=""/>
                       </p>
                       <div id="pulasanTestParnet_ref">
                            <c:if test="${addBox}">
                            <div id="pulasanTestParnet">
					   <p id="pulasanTest">
                                               <label id="empty">&nbsp;</label>
                                      <s:textarea name="plpulasan${j+1}" id="plpulasan${j+1}"  rows="7" cols="69" class="normal_text"></s:textarea>
                                      <s:button class="btn" id="delete@" value="Hapus" name="delete@" onclick="deleteRow2_delete()"/>

									</p>
                              </div>
                              </c:if>
                       </div>

                       <p>
                             <label>&nbsp;</label>
                             <s:button class="btn" value="Tambah" name="add" onclick="addRow2_new_new()"/>
                             <%--<s:button class="btn" value="Hapus" name="delete${i}" onclick="deleteRow2()"/>--%>

                       </p>


        </fieldset></div>
        <br/><br/><br/><br/>
                <div class="subtitle">

    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <c:if test="${actionBean.kodNegeri eq '05'}">
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT' or actionBean.permohonan.kodUrusan.kod eq 'PPBB' or actionBean.permohonan.kodUrusan.kod eq 'PBPTD' or actionBean.permohonan.kodUrusan.kod eq 'PBPTG'}">
                <c:if test="${actionBean.stageId ne 'laporan_tanah'}">

                    <legend>Ulasan Penolong Pegawai Tanah (Kanan)</legend> </br>
                    <c:if test="${actionBean.stageId eq 'semak_laporan_tanah'}">
                    <p align="center"><s:textarea  id="ulasanKanan" name="ulasanKanan" cols="150"  rows="5" class="normal_text" value="${actionBean.ulasanKanan}"/></p>
                    <p align="center"><s:button  name="ulasanPPTKanan" id="ulasanPPTKanan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
                    <s:button  name="kemaskiniUlasan" id="kemaskiniUlasan" value="Kemaskini" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/></p>
                    </c:if>
                    <c:if test="${actionBean.stageId eq 'kenalpasti_jabatan_teknikal'||actionBean.stageId eq 'terima_ulasan_teknikal'||actionBean.stageId eq 'draf_jktd' ||actionBean.stageId eq 'semak_draf_jktd1' ||actionBean.stageId eq 'semak_draf_jktd2'||actionBean.stageId eq 'persetujuan_draf_jktd'}">
                        <label>Ulasan :</label>${actionBean.ulasanKanan}&nbsp;
                    </c:if>

                </c:if>
            </c:if>
           </c:if>
           <c:if test="${actionBean.kodNegeri eq '04'}">
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB' or actionBean.permohonan.kodUrusan.kod eq 'PBPTD' or actionBean.permohonan.kodUrusan.kod eq 'PBPTG'}">
                <c:if test="${actionBean.stageId ne 'laporan_tanah'}">


                    <c:if test="${actionBean.stageId eq 'semak_laporan_tanah'}">
                      <legend>Ulasan Penolong Pegawai Tanah (Kanan)</legend> </br>
                    <p align="center"><s:textarea  id="ulasanKanan" name="ulasanKanan" cols="150"  rows="5" class="normal_text" value="${actionBean.ulasanKanan}"/></p>
                    <p align="center"><s:button  name="ulasanPPTKanan" id="ulasanPPTKanan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
                    </p>
                    </c:if>
                    <c:if test="${actionBean.stageId eq 'kenalpasti_jabatan_teknikal'||actionBean.stageId eq 'terima_ulasan_teknikal'||actionBean.stageId eq 'draf_jktd' ||actionBean.stageId eq 'semak_draf_jktd1' ||actionBean.stageId eq 'semak_draf_jktd2'||actionBean.stageId eq 'persetujuan_draf_jktd'}">
                    <legend>Ulasan Penolong Pegawai Tanah (Kanan)</legend> </br>
                        <label>Ulasan :</label>${actionBean.ulasanKanan}&nbsp;
                    </c:if>

                </c:if>
            </c:if>
           </c:if>
        </fieldset>
    </div>

        <fieldset class="aras1">
                <table  width="100%" border="0">
                    <tr>
                        <td align="center">
                            <c:if test="${actionBean.display eq 'false'}">
                            <s:button name="simpanLaporanTanah" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                            </c:if>
                        </td>
                        
                        <%--<td>&nbsp;&nbsp;&nbsp;&nbsp; <s:button name="gislim" id="save" value="GIS LIM" class="btn"   onclick="RunProgram('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/></td>--%>
                    </tr>
                </table>

        </fieldset>
</s:form>