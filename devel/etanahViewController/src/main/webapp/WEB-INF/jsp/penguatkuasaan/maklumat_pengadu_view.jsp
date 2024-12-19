<%-- 
    Document   : maklumat_pengadu_view
    Created on : 19-Jan-2010, 15:40:30
    Author     : nurshahida.radzi
    Modify by  : sitifariza.hanim 28022011
                 latifah.iskak 06102011 - hide/show certain tab
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%--<s:form  beanclass="etanah.view.penguatkuasaan.MaklumatPengaduActionBean">--%>
<script type="text/javascript">
    $(document).ready( function(){
        //$('#hantar').attr('onClick', 'agihTugasanPP();');
        //$('#hantar').attr("disabled", false);//to enable selesai button
        var kodNegeri = $('#kodNegeri').val();
        var idStage = $('#stageId').val();
        var kodUrusan = $('#kodUrusan').val();
        var resultCase = $('#keputusanKes').val();
        var kodUrusanTemp = $('#kodUrusanTemp').val();

        //TODO : Hafifi
        //idStage = 'sedia_kertas_pertuduhan';
        //resultCase = 'C';
        
        // ** START -- properties for NS ** //
        if(kodNegeri == '05'){
            if(kodUrusan == '422'){
                if(idStage == 'keputusan1'){
                    if(resultCase == 'C' || resultCase == 'DK'){
                        $("#page_id_7").show();
                        $("#hantar").hide();
                    }else{
                        $("#page_id_7").hide();
                        $("#hantar").show();
                    }
                } else if (idStage == 'rujuk_kepada_tpr'){
                    if(resultCase == 'C'){
                        $("#page_id_6").show();
                    }else{
                        $("#page_id_6").hide();
                    }
                } else if (idStage == 'keputusan2'){
                    if(resultCase == 'C'){
                        $("#page_id_6").show();
                    }else{
                        $("#page_id_6").hide();
                    }
                }
            }else if(kodUrusan == '423'){
                if(idStage == 'keputusan1'){
                    if(resultCase == 'C' || resultCase == 'DK'){
                        $("#page_id_7").show();
                        $("#hantar").hide();
                    }else{
                        $("#page_id_7").hide();
                        $("#hantar").show();
                    }
                } else if (idStage == 'rujuk_kepada_tpr'){
                    if(resultCase == 'C'){
                        $("#page_id_6").show();
                    }else{
                        $("#page_id_6").hide();
                    }
                } else if (idStage == 'keputusan2'){
                    if(resultCase == 'C'){
                        $("#page_id_6").show();
                    }else{
                        $("#page_id_6").hide();
                    }
                }
            }else if(kodUrusan == '424'){
                if(idStage == 'keputusan1'){
                    if(resultCase == 'C' || resultCase == 'DK'){
                        $("#page_id_8").show();
                        $("#hantar").hide();
                    }else{
                        $("#page_id_8").hide();
                        $("#hantar").show();
                    }
                } else if (idStage == 'rujuk_kepada_tpr'){
                    if(resultCase == 'C'){
                        $("#page_id_7").show();
                    }else{
                        $("#page_id_7").hide();
                    }
                } else if (idStage == 'keputusan2'){
                    if(resultCase == 'C'){
                        $("#page_id_7").show();
                    }else{
                        $("#page_id_7").hide();
                    }
                }
            }else if(kodUrusan == '427'){
                if(idStage == 'keputusan1'){
                    if(resultCase == 'C' || resultCase == 'DK'){
                        $("#page_id_10").show();
                        $("#hantar").hide();
                    }else{
                        $("#page_id_10").hide();
                        $("#hantar").show();
                    }
                } else if (idStage == 'rujuk_kepada_tpr'){
                    if(resultCase == 'C'){
                        $("#page_id_9").show();
                    }else{
                        $("#page_id_9").hide();
                    }
                } else if (idStage == 'keputusan2'){
                    if(resultCase == 'C'){
                        $("#page_id_9").show();
                    }else{
                        $("#page_id_9").hide();
                    }
                }
            }else if(kodUrusan == '428'){
                if(idStage == 'keputusan1'){
                    if(resultCase == 'C' || resultCase == 'DK'){
                        $("#page_id_10").show();
                        $("#hantar").hide();
                    }else{
                        $("#page_id_10").hide();
                        $("#hantar").show();
                    }
                } else if (idStage == 'rujuk_kepada_tpr'){
                    if(resultCase == 'C'){
                        $("#page_id_9").show();
                    }else{
                        $("#page_id_9").hide();
                    }
                } else if (idStage == 'keputusan2'){
                    if(resultCase == 'C'){
                        $("#page_id_9").show();
                    }else{
                        $("#page_id_9").hide();
                    }
                }
            } else if(kodUrusan == '425' || kodUrusan == '425A' || kodUrusan == '426'){
                if(idStage == 'arah_simpan_barang' 
                    || idStage == 'imbas_surat_tuntutan'
                    || idStage == 'maklum_jual_barang'
                    || idStage == 'kpsn3'
                    || idStage == 'maklum_bayaran_kompaun'
                    || idStage == 'maklum_rayuan_kompaun'
                    || idStage == 'peraku_surat_lepas_brg'){
                    $("#hantar").hide();
                }else if(idStage == 'kemaskini_inventori' || idStage == 'tindakan_simpan_barang'){
                    if(resultCase == 'NX'){
                        $("#page_id_9").show();
                        $("#hantar").hide();
                    }else{
                        $("#page_id_9").hide();
                        $("#hantar").show();
                    }
                }else if(idStage == 'kpsn4'){
                    if(resultCase == 'C' || resultCase == 'PF'){
                        if(kodUrusan == '426'){
                            $("#page_id_12").show();
                            $("#hantar").hide();
                        }else{
                            $("#page_id_4").show();
                            $("#page_id_5").show();
                            $("#page_id_6").show();
                            $("#page_id_7").show();
                            $("#page_id_8").show();
                            $("#page_id_9").show();
                            $("#page_id_11").show();
                            $("#hantar").hide();
                        }                        
                    }else{
                        if(kodUrusan == '426'){
                            $("#page_id_12").hide();
                            $("#hantar").show();
                        }else{
                            $("#page_id_4").hide();
                            $("#page_id_5").hide();
                            $("#page_id_6").hide();
                            $("#page_id_7").hide();
                            $("#page_id_8").hide();
                            $("#page_id_9").hide();
                            $("#page_id_11").hide();
                            $("#hantar").show();
                        }
                    }
                }else if(idStage == "lantik_io"){
                    if(resultCase == 'TK'){
                        $("#page_id_11").hide();
                        $("#hantar").show();
                    }else{
                        $("#page_id_11").show();
                        $("#hantar").hide();
                    }
                }else if(idStage == 'sedia_kertas_pertuduhan'){
                    if(resultCase == "C"){          
                        $("#page_id_9").show();                   
                    } else {              
                        $("#page_id_9").hide();               
                    }
                }else{
                    $("#hantar").show();
                }
            } else if(kodUrusan == '127'){
                if(idStage == 'kpsn_pemantauan1'){
                    if(resultCase == "BE"){
                        $('#report').hide();
                    }else if(resultCase == "TE"){
                        $('#report').show();
                    }else{
                        $('#report').show();
                    }
                } else if(idStage == 'kpsn_enkuiri_b7e'){
                    if(resultCase == "TD"){
                        $('#report').hide();
                    }else{
                        $('#report').show();
                    }
                }
            } else if(kodUrusan == '351' || kodUrusan == '352'){
                if(idStage == 'kemaskini_mukim_indek' || idStage == 'kemaskini_hasil'){
                    $("#page_id_5").hide();
                }
            } else if(kodUrusan == '429'){
                if(idStage == 'kaji_ks'){
                    if(resultCase == "" || resultCase != "C"){ // C = kompaun
                        $('#page_id_7').hide();//hide tab syor kompaun
                    }else if (resultCase == "C"){
                        $('#page_id_7').show();//show tab syor kompaun
                    }
                }
                
                if($("#specSec").val() == "429(a)"){
                    document.getElementById("a").checked = true;
                }else if($("#specSec").val() == "429(b)"){
                    document.getElementById("b").checked = true;
                }else if($("#specSec").val() == "429(c)"){
                    document.getElementById("c").checked = true;
                }else if($("#specSec").val() == "429(d)"){
                    document.getElementById("d").checked = true;
                }
            }
        }        
        // ** END -- properties for NS ** //
        if(kodNegeri == '04'){
            //            if(kodUrusan == '425'){
            //                if(idStage == 'semak1_laporan_awal1'){
            //                    if(resultCase == "" || resultCase != "KR"){ //KR = kes ringan
            //                        $('#report').hide();//hide button Jana Dokumen
            //                        $('#hantar').removeClass('disablebtn').addClass('btn'); //to change class name
            //                        $('#hantar').attr("disabled", false);//to enable selesai button
            //                        $('#page_id_4').hide();//hide tab keputusan aduan
            //                    }else{
            //                        $('#report').show();//show button Jana Dokumen
            //                        $('#page_id_4').show();//show tab keputusan aduan
            //                    } 
            //                }
            //            }
            
            if(kodUrusan == '426'){
                if(idStage == 'keputusan_op'){
                    if(resultCase == ""){ //KD = kes kompaun/dakwa
                        $('#hantar').removeClass('disablebtn').addClass('btn'); //to change class name
                        $('#hantar').attr("disabled", false);//to enable selesai button
                        $('#page_id_14').hide();//hide tab Senarai Kertas Siasatan
                        $('#page_id_15').hide();//hide tab Pelantikan Penyiasat
                        $('#hantar').show();
                    }else if(resultCase != "KD" && resultCase != "PE"){
                        $('#hantar').removeClass('disablebtn').addClass('btn'); //to change class name
                        $('#hantar').attr("disabled", false);//to enable selesai button
                        $('#page_id_14').hide();//hide tab Senarai Kertas Siasatan
                        $('#page_id_15').hide();//hide tab Pelantikan Penyiasat
                        $('#hantar').show();
                    }else{
                        $('#page_id_14').show();//show tab Senarai Kertas Siasatan
                        $('#page_id_15').show();//show tab Pelantikan Penyiasat
                        $('#hantar').hide();
                    } 
                }
                
                if(idStage == 'sedia_laporan2'){
                    $('#hantar').attr("disabled", true);//to disable selesai button
                    if($('#statusJanaDokumen').val() == "T"){
                        $('#hantar').removeClass('disablebtn').addClass('btn'); //to change class name
                        $('#hantar').attr("disabled", false);//to enable selesai button
                    }
                }
                
                if(idStage == 'keputusan_kompaundakwa'){
                    if(resultCase == "" || resultCase != "HD"){ 
                        $('#hantar').removeClass('disablebtn').addClass('btn'); //to change class name
                        $('#hantar').attr("disabled", false);//to enable selesai button
                        $('#page_id_16').hide();//hide tab Keputusan kompaun/dakwa
                    }else{
                        $('#page_id_16').show();//show tab Keputusan kompaun/dakwa
                        $('#hantar').hide();
                    }
                }
            }
            
            if(kodUrusan == '999'){
                if(idStage == 'g_sedia_pelan'){
                    if(kodUrusanTemp != ''){
                        if(kodUrusanTemp == '127'){
                            $('#page_id_7').hide();//hide tab Laporan Operasi
                            $('#page_id_8').hide();//hide tab Orang Yang Disyaki
                            $('#page_id_9').hide();//hide tab Laporan Polis
                            $('#page_id_10').hide();//hide tab Barang Rampasan
                            $('#page_id_11').hide();//hide tab Diari Siasatan
                            $('#page_id_12').hide();//hide tab Borang Bongkar
                            $('#page_id_13').hide();//hide tab Notis Sita
                            $('#page_id_14').hide();//hide tab Laporan Siasatan Awalan
                            $('#page_id_15').hide();//hide tab Serahan Barang Rampasan
                            $('#page_id_16').hide();//hide tab Barang Operasi
                            $('#page_id_17').hide();//hide tab Charting
                            $('#page_id_18').hide();//hide tab Nota/Minit
                            $('#page_id_19').hide();//hide tab Pertukaran seksyen
                        }
                    
                        if(kodUrusanTemp == '426'){
                            $('#page_id_3').hide();//hide tab Laporan Tanah
                            $('#page_id_4').hide();//hide tab Laporan Pemantauan
                            $('#page_id_5').hide();//hide tab Nota/Minit
                            $('#page_id_6').hide();//hide tab Pertukaran seksyen
                            $('#page_id_17').hide();//hide tab charting
                        }
                    
                        if(kodUrusanTemp == '425' || kodUrusanTemp == '425A'){
                            //                            $('#page_id_3').hide();//hide tab Laporan Tanah
                            $('#page_id_4').hide();//hide tab Laporan Pemantauan
                            $('#page_id_5').hide();//hide tab Nota/Minit
                            $('#page_id_6').hide();//hide tab Pertukaran seksyen
                            $('#page_id_7').hide();//hide tab Laporan Siasatan Awalan
                        } 
                    }
                    
                    $('#hantar').attr("disabled", true);//to disable selesai button
                    if($('#statusJanaDokumenRAP').val() == ""){
                        $('#hantar').removeClass('disablebtn').addClass('btn'); //to change class name
                        $('#hantar').attr("disabled", false);//to enable selesai button
                    }
                      
                }
                 
            }
            
            if(kodUrusan == '425' || kodUrusan == '425A'){
                if(idStage == 'keputusan_laporan1'){
                    if(resultCase == "" || resultCase != "BS"){ //KD = Siasatan Untuk Pendakwaan
                        $('#hantar').removeClass('disablebtn').addClass('btn'); //to change class name
                        $('#hantar').attr("disabled", false);//to enable selesai button
                        $('#page_id_14').hide();//hide tab Senarai Kertas Siasatan
                        $('#page_id_15').hide();//hide tab Pelantikan Penyiasat
                        $('#hantar').show();
                    }else{
                        $('#page_id_14').show();//show tab Senarai Kertas Siasatan
                        $('#page_id_15').show();//show tab Pelantikan Penyiasat
                        $('#hantar').hide();
                    } 
                }
                
                if(idStage == 'terima_kpsn_ks'){
                    if(resultCase == "" || resultCase != "HD"){ 
                        $('#hantar').removeClass('disablebtn').addClass('btn'); //to change class name
                        $('#hantar').attr("disabled", false);//to enable selesai button
                        $('#page_id_15').hide();//hide tab Keputusan kompaun/dakwa
                    }else{
                        $('#page_id_15').show();//show tab Keputusan kompaun/dakwa
                        $('#hantar').hide();
                    }
                }
                
                if(idStage == 'keputusan_op'){
                    if(resultCase == ""){ //KD = kes kompaun/dakwa
                        $('#hantar').removeClass('disablebtn').addClass('btn'); //to change class name
                        $('#hantar').attr("disabled", false);//to enable selesai button
                        $('#page_id_15').hide();//hide tab Senarai Kertas Siasatan
                        $('#page_id_16').hide();//hide tab Pelantikan Penyiasat
                        $('#hantar').show();
                    }else if(resultCase != "KD" && resultCase != "BR"){
                        $('#hantar').removeClass('disablebtn').addClass('btn'); //to change class name
                        $('#hantar').attr("disabled", false);//to enable selesai button
                        $('#page_id_15').hide();//hide tab Senarai Kertas Siasatan
                        $('#page_id_16').hide();//hide tab Pelantikan Penyiasat
                        $('#hantar').show();
                    }else{
                        $('#page_id_15').show();//show tab Senarai Kertas Siasatan
                        $('#page_id_16').show();//show tab Pelantikan Penyiasat
                        $('#hantar').hide();
                    } 
                }
                    
                if(idStage == 'semak1_laporan_awal1'){
                    //                        alert("resultCase :"+resultCase);
                    if(resultCase == ""){ 
                        $('#page_id_13').hide();//hide tab Sedia Jawapan Kes
                        $('#report').hide();//hide button Jana Dokumen
                        $('#hantar').removeClass('disablebtn').addClass('btn'); //to change class name
                        $('#hantar').attr("disabled", false);//to enable selesai button
                    }else if(resultCase == "KH"){
                        // KH = Kes Berat
                        $('#page_id_13').hide();//hide tab Sedia Jawapan Kes
                        $('#report').show();//show button Jana Dokumen
                        $('#hantar').attr("disabled", true);//to disable selesai button 
                    }else{
                        // KR = Kes Ringan
                        $('#page_id_13').show();//show tab Sedia Jawapan Kes
                        $('#report').show();//show button Jana Dokumen
                        $('#hantar').attr("disabled", true);//to disable selesai button 
                    }
                        
                }
            }
            
            if(kodUrusan == '49'){
                if(idStage == 'semak_laporan2'){
                    if(resultCase == "" || resultCase != "AH"){ //KD = Siasatan Untuk Pendakwaan
                        $('#hantar').removeClass('disablebtn').addClass('btn'); //to change class name
                        $('#hantar').attr("disabled", false);//to enable selesai button
                        $('#page_id_6').hide();//hide tab Jenis Hakisan
                        $('#hantar').show();
                    }else{
                        $('#page_id_6').show();//hide tab Jenis Hakisan
                        $('#hantar').hide();
                    }   
                }
                 
            }
            
            if(kodUrusan == '422' || kodUrusan == '423'){
                if(idStage == 'keputusan_kes'){
                    if(resultCase == "" || resultCase != "OP"){ 
                        $('#page_id_7').hide();//hide tab Keputusan kompaun/dakwa
                    }else{
                        $('#page_id_7').show();//show tab Keputusan kompaun/dakwa
                    }
                }
                
            }
            
            if(kodUrusan == '351' || kodUrusan == '352'){
                if(idStage == 'bantahan_1tahun'){
                    if(resultCase == "" || resultCase != "XT"){ 
                        $('#page_id_7').hide();//hide tab Maklumat pihak berkepentingan
                    }else{
                        $('#page_id_7').show();//show tab Maklumat pihak berkepentingan
                    }
                }
                
            }
            
            if(kodUrusan == '127'){
                if(idStage == 'pilih_tindakan'){
                    if(resultCase == "" || resultCase != "LT"){ 
                        $('#page_id_5').hide();//hide tab Maklumat Tempoh Lanjut Remedi
                    }else{
                        $('#page_id_5').show();//show tab Maklumat Tempoh Lanjut Remedi
                    }
                }
            }
        }
        // ** END -- properties for MLK ** //

    });
    
    
    function limitText(limitField, limitNum) {
        if (limitField.value.length > limitNum) {
            limitField.value = limitField.value.substring(0, limitNum);
        }
    }


</script>
<s:form  name="form1" id="form1"  beanclass="etanah.view.penguatkuasaan.SenaraiAduanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Aduan</legend>
            <s:hidden name="keputusan" id="keputusanKes"/>
            <s:hidden name="kodNegeri" id="kodNegeri"/>
            <s:hidden name="stageId" id="stageId"/>
            <s:hidden name="permohonan.kodUrusan.kod" id="kodUrusan"/>
            <s:hidden name="permohonanNota.idMohonNota" id="permohonanNota"/>
            <s:hidden name="idPengguna" id="idPengguna"/>
            <s:hidden name="statusJanaDokumen" id="statusJanaDokumen"/>
            <s:hidden name="statusJanaDokumenRAP" id="statusJanaDokumenRAP"/>
            <s:hidden name="permohonan.rujukanUndang2" id="kodUrusanTemp"/>
            <s:hidden name="specificSection" id="specSec"/>
            <p>
                <label for="ID Permohonan">ID Aduan :</label>
                ${actionBean.permohonan.idPermohonan}&nbsp;
            </p>
            <p>
                <label>Tempat :</label>
                ${actionBean.permohonan.cawangan.name}&nbsp;
            </p>
            <p>
                <label>Tarikh :</label>
                <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>&nbsp;
            </p>
            <p>
                <label>Daerah :</label>
                ${actionBean.permohonan.cawangan.kod} - ${actionBean.permohonan.cawangan.daerah.nama}&nbsp;

            </p>
            <p>
                <label>Alamat :</label>
                ${actionBean.permohonan.cawangan.alamat.alamat1}&nbsp;
            </p>
            <p>
                <label> &nbsp;</label>
                ${actionBean.permohonan.cawangan.alamat.alamat2}&nbsp;
            </p>
            <p>
                <label> &nbsp;</label>
                ${actionBean.permohonan.cawangan.alamat.alamat3}&nbsp;
            </p>
            <p>
                <label> &nbsp;</label>
                ${actionBean.permohonan.cawangan.alamat.alamat4}&nbsp;
            </p>
            <p>
                <label>Poskod :</label>
                ${actionBean.permohonan.cawangan.alamat.poskod}&nbsp;
            </p>
            <p>
                <label>Negeri :</label>
                ${actionBean.permohonan.cawangan.alamat.negeri.nama}&nbsp;
            </p>
            <p>
                <label>Cara Aduan :</label>
                ${actionBean.permohonan.caraPermohonan.nama}&nbsp;
            </p>
            <c:choose>
                <c:when test="${actionBean.kodNegeri eq '05'}">
                    <c:if test="${actionBean.editPerihalSeksyen eq false}">
                        <c:if test="${actionBean.stageId eq 'maklum_aduan' || actionBean.stageId eq 'maklum_arahan'}">
                            <table>
                                <tr>
                                    <td valign="top" style="margin-left: 30px;"><p><label>Peruntukan Undang-undang :</label></p></td>
                                    <td valign="top" style="float-left: 23px;"><font size="2px;">${actionBean.permohonan.kodUrusan.nama}&nbsp;</font></td>
                                </tr>
                            </table>
                        </c:if>
                        <c:if test="${actionBean.stageId ne 'maklum_aduan' && actionBean.stageId ne 'maklum_arahan'}">
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq '429'}">
                                <table>
                                    <tr>
                                        <td valign="top" style="margin-left: 30px;"><p><label>Peruntukan Undang-undang :</label></p></td>
                                        <td valign="top" style="float-left: 23px;">
                                            <font size="2px;">
                                                <c:choose>
                                                    <c:when test="${actionBean.permohonan.rujukanUndang2 eq '429(a)'}">
                                                        Seksyen 429 (a) KTN&nbsp;
                                                    </c:when>
                                                    <c:when test="${actionBean.permohonan.rujukanUndang2 eq '429(b)'}">
                                                        Seksyen 429 (b) KTN&nbsp;
                                                    </c:when>
                                                    <c:when test="${actionBean.permohonan.rujukanUndang2 eq '429(c)'}">
                                                        SSeksyen 429 (c) KTN&nbsp;
                                                    </c:when>
                                                    <c:when test="${actionBean.permohonan.rujukanUndang2 eq '429(d)'}">
                                                        Seksyen 429 (d) KTN&nbsp;
                                                    </c:when>
                                                    <c:otherwise>
                                                        ${actionBean.permohonan.kodUrusan.nama}&nbsp;
                                                    </c:otherwise>
                                                </c:choose>

                                            </font>
                                        </td>
                                    </tr>
                                </table>
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne '429'}">
                                <table>
                                    <tr>
                                        <td valign="top" style="margin-left: 30px;"><p><label>Peruntukan Undang-undang :</label></p></td>
                                        <td valign="top" style="float-left: 23px;"><font size="2px;">${actionBean.permohonan.kodUrusan.nama}</font></td>
                                    </tr>
                                </table>
                            </c:if>
                            <table>
                                <tr>
                                    <td valign="top" style="margin-left: 30px;"><p><label>Keterangan Seksyen :</label></p></td>
                                    <td valign="top" style="float-left: 23px;"><font size="2px;">${actionBean.permohonan.catatan}&nbsp;</font></td>
                                </tr>
                            </table>
                        </c:if>
                    </c:if>
                    <c:if test="${actionBean.editPerihalSeksyen eq true}">
                        <p>
                            <label>Peruntukan Undang-undang :</label>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne '429'}">
                                <s:textarea name="editSeksyen" id="editSeksyen" value="${actionBean.editSeksyen}"  rows="3" cols="70" class="normal_text"/>
                                <font color="red" size="1">cth: S.351 KTN</font>
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq '429'}">
                                <input type="radio" name="specificSection" value="429(a)" id="a"/> S.429 (a) KTN &nbsp;&nbsp;
                                <input type="radio" name="specificSection" value="429(b)" id="b"/> S.429 (b) KTN&nbsp;&nbsp;
                                <input type="radio" name="specificSection" value="429(c)" id="c"/> S.429 (c) KTN&nbsp;&nbsp;
                                <input type="radio" name="specificSection" value="429(d)" id="d"/> S.429 (d) KTN&nbsp;&nbsp;
                            </c:if>
                        </p>
                        <p>
                            <label>Keterangan Seksyen :</label>
                            <s:textarea name="catatanSeksyen" id="catatanSeksyen" value="${actionBean.catatanSeksyen}"  rows="7" cols="70" class="normal_text"/>
                            <font color="red" size="1">cth: S.351 (a) KTN</font>
                        </p>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <p>
                        <label>Peruntukan Undang-undang :</label>
                        ${actionBean.permohonan.kodUrusan.nama}&nbsp;
                    </p>
                </c:otherwise>
            </c:choose>

            <table>
                <tr>
                    <td valign="top" style="margin-left: 30px;"><p><label>Ringkasan Aduan :</label></p></td>
                    <td valign="top" style="float-left: 23px;">
                        <c:choose>
                            <c:when test="${actionBean.kodNegeri eq '05'}">
                                <c:choose>
                                    <c:when test="${actionBean.editRingkasanAduan eq true}">
                                        <s:textarea name="sebab" id="sebab" value="${actionBean.sebab}"  rows="5" cols="50" onkeydown="limitText(this,2500);" onkeyup="limitText(this,2500);" class="normal_text"/>
                                    </c:when>
                                    <c:otherwise>
                                        <font size="2px;">${actionBean.permohonan.sebab}&nbsp;</font>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <font size="2px;">${actionBean.permohonan.sebab}&nbsp;</font>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </table>
        </fieldset >
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pengadu</legend>
            <p>
                <label>Nama :</label>
                ${actionBean.permohonan.penyerahNama}&nbsp;
            </p>
            <p>
                <label>Jenis Pengenalan :</label>
                ${actionBean.permohonan.penyerahJenisPengenalan.nama}&nbsp;
            </p>
            <p>
                <label>No.Pengenalan :</label>
                <font style="text-transform: uppercase">${actionBean.permohonan.penyerahNoPengenalan}</font>&nbsp;
            </p>

            <p>
                <label>Alamat :</label>
                ${actionBean.permohonan.penyerahAlamat1}&nbsp;
            </p>
            <c:if test="${actionBean.permohonan.penyerahAlamat2 ne null}">
                <p>
                    <label> &nbsp;</label>
                    ${actionBean.permohonan.penyerahAlamat2}&nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.penyerahAlamat3 ne null}">
                <p>
                    <label> &nbsp;</label>
                    ${actionBean.permohonan.penyerahAlamat3}&nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.penyerahAlamat4 ne null}">
                <p>
                    <label> &nbsp;</label>
                    ${actionBean.permohonan.penyerahAlamat4}&nbsp;
                </p>
            </c:if>
            <p>
                <label>Poskod :</label>
                ${actionBean.permohonan.penyerahPoskod}&nbsp;
            </p>
            <p>
                <label>Negeri :</label>
                ${actionBean.permohonan.penyerahNegeri.nama}&nbsp;
            </p>
            <p>
                <label>No.Telefon :</label>
                ${actionBean.permohonan.penyerahNoTelefon1}&nbsp;
            </p>
            <p>
                <label>Email :</label>
                ${actionBean.permohonan.penyerahEmail}&nbsp;
            </p>
            <br>
            <p align="center">
                <c:if test="${actionBean.editPerihalSeksyen eq true || actionBean.editRingkasanAduan eq true}">
                    <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="updatePerihalSeksyen" value="Simpan"/>
                </c:if>
            </p>


        </fieldset>
    </div>
</s:form>

