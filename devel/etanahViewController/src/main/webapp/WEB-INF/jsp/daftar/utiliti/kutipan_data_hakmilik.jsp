<%-- 
    Document   : kutipan_data_maklumat_hakmilik
    Created on : Sep 24, 2013, 5:11:48 PM
    Author     : ei
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<c:set var="disabled" value="disabled"/>
<script type="text/javascript">
    function filterKodBPM(f) {
        // GET BANDAR PEKAN MUKIN FROM PARTIAL JSP 
        var kodDaerah = f;
        $.post('${pageContext.request.contextPath}/daftar/utiliti/kutipanData?cariBPM&kodDaerah=' + kodDaerah + '&popup=true',
                function(data) {
                    if (data != '') {
                        $('#partialKodBPM').html(data);
                    }
                }, 'html');
    }

    function filterKodGunaTanah(val, val2) {
        var url = '${pageContext.request.contextPath}/daftar/utiliti/kutipanData?senaraiKodGunaTanahByKatTanah&kt=' + val + '&hm=' + val2;
        frm = document.kutipanUrusan;
        frm.action = url;
        frm.submit();
    }
</script>
<script type="text/javascript">
    $(document).ready(function() {
        $(".datepicker1").datepicker({changeYear: true});
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        filterKodBPM($('#kodDaerah').val());
        filterKodGunaTanah($('#katTanah').val());
//    alert($('#pegangan').val());

    <c:if test="${actionBean.idHakmilik ne null}">
        showHm1($('#idHa kmilik').val());
    </c:if>

    <c:if test="${actionBean.kodKatTanah ne null}">
        alert(${actionBean.kodKatTanah});
    </c:if>

        $('#simpanPerinci').click(function() {
            doBlockUI();
        });
    });
    function showHm1(v) {
        // SHOW ID HAKMILIK DETAILS   
        doBlockUI();
        var url = '${pageContext.request.contextPath}/daftar/utiliti/kutipanData?maklumatDetail&idHakmilik=' + v;
        frm = document.kutipanUrusan;
        frm.action = url;
        frm.submit();
    }

    function removeMultipleMohonHakmilik() {
        // DELETE CHECKED HAKMILIK
        doBlockUI();
        if (confirm('Adakah anda pasti untuk hapus hakmilik ini')) {
            var param = '';
            $('.remove2').each(function(index) {
                var a = $('#checkbox' + index).is(":checked");
                if (a) {
                    param = param + '&idHapus=' + $('#checkbox' + index).val();
                }
            });
            if (param === '') {
                alert('Sila Pilih Hakmilik terlebih dahulu.');
                $.unblockUI();
                return;
            }

            var url = '${pageContext.request.contextPath}/daftar/utiliti/kutipanData?hapusHakmilik' + param;
            frm = document.kutipanUrusan;
            frm.action = url;
            frm.submit();
        }
    }

    function selectAll(a) {
        // TICK ALL HAKMILIK
        var size = '${fn:length(actionBean.listSenaraiHakmilik)}';
        for (i = 0; i < size; i++) {
            var c = document.getElementById("checkbox" + i);
            if (c === null)
                break;
            c.checked = a.checked;
        }
    }

    function popupFormSyaratNyata() {
        // GET SYARAT NYATA POPUP
        var url = "${pageContext.request.contextPath}/daftar/utiliti/kutipanData?paparPopupSyaratNyata&idHakmilik=" + $("#idHakmilik").val() + "&kumpHm=" + $("#kumpHm").val();
        window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
    }

    function popupFormKodSekatan() {
        // GET SEKATAN POPUP
        var url = "${pageContext.request.contextPath}/daftar/utiliti/kutipanData?paparPopupSekatan&idHakmilik=" + $("#idHakmilik").val() + "&kumpHm=" + $("#kumpHm").val();
        window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
    }

    function popupHakmilikSblm(id) {
        // GET HAKMILIK SEBELUM POP-UP
        var url = "${pageContext.request.contextPath}/daftar/utiliti/kutipanData?paparPopupHakmilikSebelum&idHakmilik=" + id + "&kumpHm=" + $("#kumpHm").val();
        window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=500");
    }

    function popupHakmilikAsal(id) {
        // GET HAKMILIK ASAL POP-UP
        var url = "${pageContext.request.contextPath}/daftar/utiliti/kutipanData?paparPopupHakmilikAsal&idHakmilik=" + id + "&kumpHm=" + $("#kumpHm").val();
        window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=500");
    }

    function popupPihak(id) {
        // GET PIHAK POP
        var url = "${pageContext.request.contextPath}/daftar/utiliti/kutipanData?paparPopupPihak&idHakmilik=" + id + "&kumpHm=" + $("#kumpHm").val();
        window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=800");
    }

    function popupKemaskiniPihak(val, val1, val2) {
        // GET POPUP KEMASKINI PIHAK
        var url = "${pageContext.request.contextPath}/daftar/utiliti/kutipanData?kemaskiniPihak&phk=" + val + "&hm=" + val1 + "&hp=" + val2;
        window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=800");
    }

    function selectAllPihak(a) {
        // TICK ALL HAKMILIK
        var size = '${fn:length(actionBean.listHakmilikPihak)}';
        for (i = 0; i < size; i++) {
            var c = document.getElementById("checkboxpihak" + i);
            if (c === null)
                break;
            c.checked = a.checked;
        }
    }

    function removePihak() {
        // DELETE CHECKED PIHAK
        if (confirm('Adakah anda pasti untuk hapus pihak ini')) {
            doBlockUI();
            var param = '';
            $('.remove2').each(function(index) {
                var a = $('#checkboxpihak' + index).is(":checked");
                if (a) {
                    param = param + '&idHapus=' + $('#checkboxpihak' + index).val();
//          alert(param);
                }
            });
            if (param === '') {
                alert('Sila Pilih Pihak terlebih dahulu.');
                return;
            }

            var url = '${pageContext.request.contextPath}/daftar/utiliti/kutipanData?hapusPihak' + param;
            //        frm = document.maklumatDetail;
            frm = document.kutipanUrusan;
            frm.action = url;
            frm.submit();
        }
    }

    function samaRata(f) {       // MAKE ALL SHARE EQUAL FOR EACH PIHAK
        var q = $(f).formSerialize();
        doBlockUI();
        var url = '${pageContext.request.contextPath}/daftar/utiliti/kutipanData?agihSamaRata';
        //      frm = document.maklumatDetail;
        frm = document.kutipanUrusan;
        frm.action = url;
        frm.submit();
    }

    function zeroPad(num, count) {
        var numZeropad = num + '';
        while (numZeropad.length < count) {
            numZeropad = "0" + numZeropad;
        }
        return numZeropad;
    }

    function checkPelan(f) {
        // GET GIS PLAN
        var noLot = zeroPad(f, 7);
        var kodDaerah = $('#kodDaerah').val();
        var kodBPM2 = $('#kodBPM').val();
        var kodBPM = zeroPad(kodBPM2, 2);
        var kodNegeri = $('#kodNegeri').val();
        var namaLot = $('#jenisLot :selected').text();
        var kodSeksyen = '${actionBean.hakmilik.seksyen.seksyen}';
        if (kodSeksyen === "") {
            kodSeksyen = "000";
        }

        var kodPelan;
        var jenisPelan;
        var jenisHm = $('#pegangan').val();

        if (jenisHm === 'S') {
            jenisPelan = "B1";
            kodPelan = "1";
        } else {
            jenisPelan = "B2";
            kodPelan = "3";
        }
        //      alert("noLot " + noLot);
        //      alert("kodDaerah " + kodDaerah);
        //      alert("kodNegeri " + kodNegeri);
        //      alert("kodBPM " + kodBPM);
        doBlockUI();

        $.post('${pageContext.request.contextPath}/daftar/utiliti/kutipanData?checkPelan&noLot='
                + noLot + '&kodDaerah=' + kodDaerah + '&kodNegeri=' + kodNegeri + '&kodBPM=' + kodBPM + '&kodSeksyen=' + kodSeksyen + '&jenisPelan=' + jenisPelan,
                function(data) {
//                        alert("data pelan : " + data);
                    if (data !== '1') {
                        $('#checkPelanMessages').show();
                        $('#checkPelanMessages').attr('class', 'errors');
                        $('#checkPelanMessages').html('Pelan untuk No ' + namaLot + ' ' + noLot + ' tiada');
                        $.unblockUI();
                    } else {
                        $('#checkPelanMessages').show();
                        $('#checkPelanMessages').attr('class', 'messages');
                        $('#checkPelanMessages').html('Pelan untuk No ' + namaLot + ' ' + noLot + ' wujud. '
                                + '<a href=${pageContext.request.contextPath}/pelan/view/kutipanData' + kodNegeri + kodDaerah + kodBPM + kodSeksyen + kodPelan + noLot + ' target=parent>' + ' Klik Untuk Semak Pelan </a>');
                        $.unblockUI();
                    }
                }, 'html');
    }

    function checkNoLot(f) {
        // CHECK NO LOT IN TABLE HAKMILIK
        var noLot = zeroPad(f, 7);
        var kodDaerah = $('#kodDaerah').val();
        var kodBPM = $('#kodBPM').val();
        var kodNegeri = $('#kodNegeri').val();
        var jenisLot = $('#jenisLot').val();
        var kodSeksyen = $('#kodSeksyen').val();
        alert("kodSeksyen =" + kodSeksyen);

        if (kodSeksyen == null) {
            kodSeksyen = '';
        } 
        alert("kodSeksyen1 =" + kodSeksyen);
        $('#noLot').val(noLot);
//      alert("no lot : " + noLot);
//      alert("kodDaerah : " + kodDaerah);
//      alert("kodBPM : " + kodBPM);
//      alert("kodNegeri : " + kodNegeri);
        $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?checkNoLot&noLot='
                + noLot + '&kodDaerah=' + kodDaerah + '&kodNegeri=' + kodNegeri + '&kodBPM=' + kodBPM
                + '&jenisLot=' + jenisLot + '&kodSeksyen=' + kodSeksyen,
                function(data) {
                    //                alert("data LOT : " + data);
                    var namaLot = $('#jenisLot :selected').text();
                    if (data != '1') {
                        $('#checkLotMessages').attr('class', 'errors');
                        $('#checkLotMessages').html('No ' + namaLot + ' ' + noLot + ' telah digunakan');
                        $('#checkLotMessages').show();
                    } else {
                        $('#checkLotMessages').attr('class', 'messages');
                        $('#checkLotMessages').html('No ' + namaLot + ' ' + noLot + ' belum pernah digunakan');
                        $('#checkLotMessages').show();
                    }
                }, 'html');
    }

    function checkLuas(f) {
        // CHECK LUAS 
//      var noLot = zeroPad(f, 7);
        var kodUOM = $('#kodUOM').val();
        var luas = $('#luas').val();
//      alert(luas);
        if (kodUOM === 'E' && luas !== "") {
            // CONVERT ekar rood pool  
            $.post('${pageContext.request.contextPath}/daftar/utiliti/kutipanData?checkLuas&luas=' + luas + '&kodUOM=' + kodUOM,
                    function(data) {
                        var ekar = parseFloat(data);
                        var m = ekar * 4046.8564;
                        m = parseFloat(Math.round(m * 10000) / 10000).toFixed(4); // round up to 0.0000
                        var h = ekar * 0.404686;
                        h = parseFloat(Math.round(h * 10000) / 10000).toFixed(4); // round up to 0.0000

                        if (!isNaN(m) && !isNaN(h)) {
                            $('#luasMeter').val(m); // pass to hidden text input
                            $('#luasHektar').val(h);// pass to hidden text input
                            // show massage
                            $('#checkLuasMessages').attr('class', 'messages');
                            $('#checkLuasMessages').html('Luas Metrik = ' + m + ' Meter Persegi; ' + h + ' Hektar.');
                            $('#checkLuasMessages').show();
                        } else {
                            $('#luasMeter').val("");
                            $('#luasHektar').val("");
                        }
                    }, 'html');
        } else if (kodUOM === 'P' && luas !== "") {
            // CONVERT ekar perpuluhan
            var ekar = parseFloat(luas);
            var m = ekar * 4046.8564;
            m = parseFloat(Math.round(m * 10000) / 10000).toFixed(4); // round up to 0.0000
            var h = ekar * 0.404686;
            h = parseFloat(Math.round(h * 10000) / 10000).toFixed(4); // round up to 0.0000

            if (!isNaN(m) && !isNaN(h)) {
                $('#luasMeter').val(m); // pass to hidden text input
                $('#luasHektar').val(h);// pass to hidden text input
                // show massege
                $('#checkLuasMessages').attr('class', 'messages');
                $('#checkLuasMessages').html('Luas Metrik = ' + m + ' Meter Persegi; ' + h + ' Hektar.');
                $('#checkLuasMessages').show();
            } else {
                $('#luasMeter').val("");
                $('#luasHektar').val("");
            }
        } else {
            $('#luasMeter').val("");
            $('#luasHektar').val("");
        }
    }

    function filterPegangan() {
        var pegangan = $('#pegangan').val();
        if (pegangan === 'P') {
            $("#p").show();
        } else if (pegangan === 'S') {
            $("#p").hide();
            $("#tempohPegangan").val("");
            $("#tkhTamat").val("");
        }
    }

    function doCalcEndDate(id) {
        var thn = parseInt($('#tempohPegangan').val(), 10);
        if ($('#' + id).val() === '') {
            return;
        }
        if (isNaN(thn)) {
            alert('Sila Masukan Tempoh.');
            $('#tkhTamat').val('');
            return;
        }
        if (thn === '0')
            return;
        var startDate = $('#' + id).val();
        //manual process :: should find conclusion on this
        var y = parseInt(startDate.substring(6, 10), 10);
        var m = parseInt(startDate.substring(3, 5), 10);
        var d = parseInt(startDate.substring(0, 2), 10);
        if (!isNaN(thn)) {
            y = y + thn;
        }

        var endDate = new Date();
        var s = 1;
        endDate.setDate(d);
        endDate.setMonth(m - 1);
        endDate.setFullYear(y);
        endDate.setDate(endDate.getDate() - s);
        $('#tkhTamat').val(endDate.format("dd/mm/yyyy"));
    }

    function doBlockUI() {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });
    }

    function removeChangesAsal(val) {
        var answer = confirm("adakah anda pasti untuk Hapus?");
        if (answer) {
            var url = '${pageContext.request.contextPath}/daftar/utiliti/kutipanData?deleteChangesAsal&idHakmilikAsal=' + val;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    });
        }
    }

    function removeChangesSblm(val) {
        var answer = confirm("adakah anda pasti untuk Hapus?");
        if (answer) {
            var url = '${pageContext.request.contextPath}/daftar/utiliti/kutipanData?deleteChangesSblm&idHakmilikSblm=' + val;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    });
        }
    }

    function cariWaris(id) {
        var url = "${pageContext.request.contextPath}/daftar/pembetulan_pihak?cariWaris&idHakmilikPihakBerkepentingan=" + id;
        window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
    }
</script>
<div class="a">
    <s:errors/>
    <s:messages/>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.daftar.utiliti.KutipanDataActionBean" name="maklumatHakmilik" id="maklumatHakmilik">
        <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <s:hidden name="idHakmilik" id="idHakmilik" value="${actionBean.idHakmilik}"/> 
                <s:hidden name="kumpHm" id="kumpHm" value="${actionBean.kumpHm}"/> 
                <lagend>Maklumat Hakmilik</lagend>
                <p style="color:black">
                    *Sila klik pada ID hakmilik untuk mengemaskini hakmilik.
                </p>
                <br>
                <p align="center">
                    <display:table class="tablecloth" style="width:95%;" id="line" cellpadding="0" cellspacing="0"
                                   requestURI="/daftar/utiliti/kutipanData" name="${actionBean.listSenaraiHakmilik}" >
                        <display:column title="<input type='checkbox' name='semua' onclick='javascript:selectAll(this);'>" style="width:1%;">
                            <s:checkbox name="checkbox" id="checkbox${line_rowNum-1}" value="${line.idHakmilik}" class="remove2"/>
                        </display:column>
                        <display:column title="Bil" style="width:1%;"><p align="right">${line_rowNum}</p></display:column>
                    <display:column title="ID Hakmilik">
                        <div align="center">   
                            <a href="#" onclick="showHm1('${line.idHakmilik}');
                                    return false;">
                                ${line.idHakmilik}
                            </a>
                            <s:hidden name="hiddenIdHakmilik" id="hiddenIdHakmilik${line_rowNum-1}" value="${line.idHakmilik}"/>
                        </div> 
                    </display:column>  
                    <display:column title="Luas / Unit">
                        <div align="center">
                            <c:if test="${line.luas ne null && line.luas ne 0}">
                                ${line.luas} / ${line.kodUnitLuas.nama}  
                            </c:if>&nbsp;  
                        </div>
                    </display:column>
                    <display:column title="No Lot / No PT">
                        <div align="center">
                            <c:if test="${line.noLot ne null}">
                                <!--<//fmt:formatNumber pattern="###" value="$//{line.noLot}"/>-->
                                ${line.noLot}
                            </c:if>&nbsp;  
                        </div>
                    </display:column>         
                    <display:column title="Kod Syarat Nyata">
                        <div align="center">
                            <c:if test="${line.syaratNyata.kod ne null}">
                                ${line.syaratNyata.kod}
                            </c:if>&nbsp; 
                        </div>
                    </display:column>
                    <display:column title="Kod Sekatan">
                        <div align="center">
                            <c:if test="${line.sekatanKepentingan.kod ne null}">
                                ${line.sekatanKepentingan.kod}
                            </c:if>&nbsp; 
                        </div>
                    </display:column>       
                    <display:column title="Cukai (RM)">
                        <div align="center">
                            <c:if test="${line.cukai ne null}">
                                <s:format formatPattern="#,##0.00" value="${line.cukai}"/>
                            </c:if>&nbsp; 
                        </div>
                    </display:column>
                </display:table>
                </p>
                <br>
                <p align="center">          
                    <s:button class="btn" value="Hapus" name="" onclick="removeMultipleMohonHakmilik();"/>&nbsp;         
                </p>
                <br>
            </fieldset>
            <!--</div>-->
            <br>
            <!--    <div id="maklumatDetail">      
                </div>  -->
            <c:if test="${actionBean.idHakmilik ne null}">
                <!--<div class="subtitle">-->
                <fieldset class="aras1">
                    <s:hidden name="kumpHm" id="kumpHm" value="${actionBean.kumpHm}"/> 
                    <legend>Maklumat Perincian Hakmilik</legend>
                    <br>
                    <p>
                        <label>ID Hakmilik :</label>
                        ${actionBean.hakmilik.idHakmilik}
                        <s:hidden name="kodNegeri" id="kodNegeri"/>
                        &nbsp
                    </p>
                    <p>
                        <label>Cawangan :</label>
                        <s:hidden name="hakmilik.cawangan.kod2" id="kodCawangan" value="${actionBean.hakmilik.cawangan.kod}"/>
                        <s:select name="hakmilik.cawangan.kod" id="namaCawangan" style="width:200pt">            
                            <s:options-collection collection="${actionBean.senaraiKodCawangan}" label="name" value="kod"/>
                        </s:select>
                    </p>          
                    <p>
                        <label>Daerah :</label>
                        <s:hidden name="hakmilik.daerah.kod" id="kodDaerah" value="${actionBean.hakmilik.daerah.kod}"/>
                        <s:select disabled="${disabled}" name="hakmilik.daerah.kod" id="namaDaerah" onchange="filterKodBPM(this.value);" style="width:200pt">            
                            <s:options-collection collection="${actionBean.senaraiKodDaerah}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <p>
                        <label>Bandar / Pekan / Mukim :</label>
                        <s:hidden name="hakmilik.bandarPekanMukim.kod" id="kodBPM"/>
                        <s:select name="hakmilik.bandarPekanMukim.kod" id="namaBPM" style="width:200pt">            
                            <s:options-collection collection="${actionBean.listBandarPekanMukim}" label="nama" value="kod"/>
                        </s:select>          
                    </p>        
                    <c:if test="${fn:length(actionBean.listKodSeksyen) > 0 }">
                        <p>
                            <label>Seksyen :</label>  
                            <s:hidden name="hakmilik.seksyen.kod" id="kodSeksyen"/>
                            <s:select name="hakmilik.seksyen.kod" style="text-transform:uppercase;width:200pt" 
                                      id="selectKodSeksyen"  >
                                <s:option value="000">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.listKodSeksyen}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                    </c:if>
                    <p>
                        <label>No Fail Hakmilik :</label>
                        <s:text name="hakmilik.noFail" value="${actionBean.hakmilik.noFail}" class="uppercase" size="40" onblur="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Lokasi :</label>
                        <c:if test="${disabledbtn eq 'disabled'}">
                            <s:hidden name="hakmilik.lokasi" />
                        </c:if>
                        <s:text name="hakmilik.lokasi" class="uppercase" disabled="${disabledbtn}" size="40" onblur="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Kategori Tanah : </label>
                        <s:select style="text-transform:uppercase;width:200pt" 
                                  name="kodKatTanah" id="katTanah" 
                                  onchange="filterKodGunaTanah(this.value,'${actionBean.hakmilik.idHakmilik}');filterKodUOM();kiraCukai(this.form,'');" >
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${actionBean.senaraiKodKategoriTanah}" label="nama" value="kod" />
                        </s:select>
                    </p>
                    <p>
                        <label>Kegunaan Tanah : </label>
                        <s:select style="text-transform:uppercase;width:200pt"  
                                  name="hakmilik.kegunaanTanah.kod" id="kodGunaTanah" value="${actionBean.hakmilik.kegunaanTanah.kod}"
                                  onchange="kiraCukai(this.form,'${actionBean.hakmilik.idHakmilik}');" >
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${actionBean.listKodGunaTanahByKatTanah}" label="nama" value="kod" />
                        </s:select>
                    </p>
                    <p>
                        <label>Keluasan / Unit :</label>
                        <s:text name="hakmilik.luas" id="luas" onblur="checkLuas(this.value);" size="18"/>
                        <s:select  style="text-transform:uppercase" id="kodUOM" name="hakmilik.kodUnitLuas.kod" 
                                   onchange="checkLuas(this.value);">
                            <s:options-collection collection="${actionBean.senaraiKodUOMByLuas2}" label="nama" value="kod"/>
                        </s:select>
                        <s:hidden name="luasMeter" id="luasMeter" formatPattern="###0.0000" />
                        <s:hidden name="luasHektar" id="luasHektar" formatPattern="###0.0000" />
                    </p>
                    <>
                    </p>
                    <center>
                        <div id="checkLuasMessages" style="font-size: 12px;display: none;margin-top: 10px;margin-bottom: 10px;width:50%;"></div>
                    </center>   
                    <p>
                    <p>
                        <label>Kod Lot / No Lot : </label>
                        <s:select  style="text-transform:uppercase;width:105pt" name="hakmilik.lot.kod" id="jenisLot" value="${actionBean.hakmilik.lot.kod}">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${actionBean.senaraiKodLot}" label="nama" value="kod" />
                        </s:select> /
                        <s:text name="hakmilik.noLot" id="noLot" size="16" maxlength="7" 
                                value="${actionBean.hakmilik.noLot}"
                                onblur="checkNoLot(this.value);checkPelan(this.value);"/>
                    </p>
                    <center>
                        <div id="checkLotMessages" style="font-size: 12px;display: none;margin-top: 10px;margin-bottom: 10px;width:50%;"></div>
                        <div id="checkPelanMessages" style="font-size: 12px;display: none;margin-top: 10px;margin-bottom: 10px;width:50%;"></div>
                    </center>   
                    <p>
                        <label>Tarikh Daftar :</label>
                        <s:text name="hakmilik.tarikhDaftar" class="datepicker1" id="tarikhDaftar" 
                                formatPattern="dd/MM/yyyy" formatType="date"
                                value="${actionBean.hakmilik.tarikhDaftar}" size="20"/>
                    </p>
                    <p>
                        <label>Tarikh Daftar Asal :</label>
                        <s:text name="hakmilik.tarikhDaftarAsal" class="datepicker1" id="tarikhDaftarAsal" 
                                formatPattern="dd/MM/yyyy" formatType="date"
                                value="${actionBean.hakmilik.tarikhDaftarAsal}" size="20"/>
                    </p>          
                    <p>
                        <label>Taraf Pegangan :</label>
                        <s:select style="text-transform:uppercase;width:130pt" name="hakmilik.pegangan" 
                                  id="pegangan" value="${actionBean.hakmilik.pegangan}" onchange="filterPegangan();checkPelan(noLot.value);">            
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="P">Pajakan</s:option>
                            <s:option value="S">Selama - lamanya</s:option>                   
                        </s:select>
                    </p>          
                    <div id="p">
                        <p>
                            <label>Tempoh :</label>
                            <s:text name="hakmilik.tempohPegangan" id="tempohPegangan" onchange="doCalcEndDate('tarikhDaftar');" 
                                    value="${actionBean.hakmilik.tempohPegangan}" size="4" maxlength="3" />&nbsp;Tahun 
                        </p>
                        <p>
                            <label>Tarikh Luput :</label>
                            <s:text name="hakmilik.tarikhLuput" class="datepicker1" id="tkhTamat" 
                                    formatPattern="dd/MM/yyyy" formatType="date"
                                    value="${actionBean.hakmilik.tarikhLuput}" size="20"/>
                        </p> 
                    </div>
                    <p>
                        <label>No Pelan Diperakui :</label>
                        <s:text name="hakmilik.noPelan" onblur="this.value=this.value.toUpperCase();" size="20"/>
                    </p>
                    <p>
                        <label>Nombor Lembaran Piawai :</label>
                        <s:text name="hakmilik.noLitho" onblur="this.value=this.value.toUpperCase();" size="20"/>
                    </p>
                    <p>
                        <label>No PU :</label>
                        <s:text name="hakmilik.noPu" id="noPu" onblur="this.value=this.value.toUpperCase();" size="20"/>
                    </p>        
                    <p>
                        <label>Cukai :</label>
                        RM <s:text name="hakmilik.cukai"  id="cukai" formatPattern="#,###.00" size="17"/>
                    </p>
                    <br>
                    <p align="center">
                        <s:submit name="simpanPerinci" id="simpanPerinci" value="Simpan" class="btn"/>&nbsp;
                        <c:if test="${fn:length(actionBean.listSenaraiHakmilik)>1 }">
                            <s:submit name="simpanPerinciKelompok" id="simpanKelompok" value="Simpan Berkelompok" class="longbtn"/>&nbsp;
                            <%--<s:button name="simpanPerinciKelompok" id="simpanKelompok" value="Simpan Berkelompok" class="longbtn" onclick="doSubmit(this.form, this.name, 'page_div')"/>--%>
                        </c:if>
                    </p>
                    <br>
                </fieldset>
                <br>
                <fieldset class="aras1">
                    <legend>Maklumat Syarat Nyata Dan Sekatan Kepentingan</legend>
                    <br>
                    <p>
                        <label>Syarat Nyata :</label>
                        <s:text name="kodSyarat" id="syaratNyata" readonly="true" size="40"
                                onblur="updateSyaratNyata()"/> &nbsp;&nbsp;
                        <s:button name="cariKodSyaratNyata" value="Cari" id="cariKodSyaratNyata" class="btn" 
                                  onclick="popupFormSyaratNyata();" disabled="${disabledbtn}" />
                    </p>          
                    <p>
                        <label>&nbsp;</label>
                        <s:textarea name="syaratnyata" rows="10" style="width:40%;" readonly="true" id="syaratnyatadetails">${actionBean.hakmilik.syaratNyata.syarat}</s:textarea>
                        </p>
                        <p>
                            <label>Sekatan Kepentingan :</label>
                        <s:text name="kodSekatan" id="sekatan" readonly="true" size="40"/> &nbsp;&nbsp;
                        <s:button name="cariKodSekatan" value="Cari" id="cariKodSekatan" class="btn" 
                                  onclick="popupFormKodSekatan();" disabled="${disabledbtn}"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:textarea name="sekatan" rows="10" style="width:40%;" readonly="true" id="sekatandetails" >${actionBean.hakmilik.sekatanKepentingan.sekatan}</s:textarea>
                        </p>               
                        <br>   
                    </fieldset>
                    <!--</div>-->
                    <br>
                    <fieldset class="aras1">
                        <legend>Senarai Hakmilik Asal</legend>
                        <div align="center">
                        <display:table class="tablecloth" style="width:90%;" cellpadding="0" cellspacing="0" 
                                       id="lineAsal" name="${actionBean.listHakmilikAsal}">
                            <display:column title="Bil" sortable="true" style="width:1%;"><p align="right">${lineAsal_rowNum}</p></display:column>
                            <display:column title="ID hakmilik" ><p align="center">${lineAsal.hakmilik.idHakmilik}</p></display:column>
                            <display:column title="ID Hakmilik Asal" >
                                <c:if test="${actionBean.kodNegeri ne '04'}">
                                    <p align="center">
                                        ${lineAsal.hakmilikAsal.kodHakmilik.kod}&nbsp;
                                        <fmt:parseNumber value="${lineAsal.hakmilikAsal.noHakmilik}"/>&nbsp;
                                        ${lineAsal.hakmilikAsal.bandarPekanMukim.nama}
                                        <%--${lineAsal.hakmilikAsal.idHakmilik}--%>
                                    </p>
                                </c:if>
                                <c:if test="${actionBean.kodNegeri eq '04'}">
                                    <c:set var="string1" value="${lineAsal.hakmilikAsal}"/>
                                    <c:set var="string2" value="${fn:substring(string1, 6, 17)}" />
                                    <p align="center">
                                        ${string2}&nbsp;
                                    </p>
                                </c:if>
                            </display:column>
                            <display:column title="Hapus" style="text-align:center">
                                <a href="#">
                                    <img alt='Klik Untuk Hapus' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem${line_rowNum}' onclick="removeChangesAsal('${lineAsal.idAsal}')" 	
                                         title="Klik untuk Hapus" />
                                </a>
                            </display:column>
                        </display:table>
                        <br>
                        <s:button name="hakmilikAsal" id="hakmilikAsal" value="Tambah" class="btn" 
                                  onclick="popupHakmilikAsal('${actionBean.hakmilik.idHakmilik}');" />
                    </div>
                    <br>
                </fieldset>
                <br>
                <fieldset class="aras1">
                    <legend>Senarai Hakmilik Sebelum</legend>
                    <div align="center">
                        <display:table class="tablecloth" style="width:90%;" cellpadding="0" cellspacing="0" 
                                       id="lineSblm" name="${actionBean.listHakmilikSblm}">
                            <display:column title="Bil" sortable="true" style="width:1%;"><p align="right">${lineSblm_rowNum}</p></display:column>
                            <display:column title="ID hakmilik" ><p align="center">${lineSblm.hakmilik.idHakmilik}</p></display:column>
                            <display:column title="ID Hakmilik Sebelum" >
                                <c:if test="${actionBean.kodNegeri ne '04'}">
                                    <p align="center">
                                        ${lineSblm.hakmilikSebelum.kodHakmilik.kod}&nbsp;
                                        <fmt:parseNumber value="${lineSblm.hakmilikSebelum.noHakmilik}"/>&nbsp;
                                        ${lineSblm.hakmilikSebelum.bandarPekanMukim.nama}
                                        <%--${lineSblm.hakmilikSebelum.idHakmilik}--%>
                                    </p>
                                </c:if>
                                <c:if test="${actionBean.kodNegeri eq '04'}">
                                    <p align="center">
                                        ${lineSblm.hakmilikSebelum}&nbsp;
                                    </p>
                                </c:if>
                            </display:column>
                            <display:column title="Hapus" style="text-align:center">
                                <a href="#">
                                    <img alt='Klik Untuk Hapus' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem${line_rowNum}' onclick="removeChangesSblm('${lineSblm.idSebelum}')" 	
                                         title="Klik untuk Hapus" />
                                </a>
                            </display:column>
                        </display:table>
                        <br>
                        <s:button name="hakmilikSblm" id="hakmilikSblm" value="Tambah" class="btn" 
                                  onclick="popupHakmilikSblm('${actionBean.hakmilik.idHakmilik}');" />
                    </div>
                    <br>
                </fieldset>
                <br>
                <!--<div class="subtitle">-->
                <fieldset class="aras1">
                    <legend>Senarai Pemilik</legend>
                    <br>
                    <div class="content" align="center" id="listpihak">
                        <display:table class="tablecloth" style="width:95%;" 
                                       cellpadding="0" cellspacing="0" id="lineHPB"
                                       name="${actionBean.listHakmilikPihak}">
                            <display:column title="<input type='checkbox' name='semua' onclick='javascript:selectAllPihak(this);'>" style="width:1%;">
                                <s:checkbox name="checkboxpihak" id="checkboxpihak${lineHPB_rowNum-1}" 
                                            value="${lineHPB.idHakmilikPihakBerkepentingan}" class="remove2"/>
                            </display:column>
                            <display:column title="Bil" sortable="true" style="width:1%;"><p align="right">${lineHPB_rowNum}</p></display:column>
                            <display:column property="nama" title="Nama" style="text-transform:uppercase;"/>
                            <display:column title="Nombor Pengenalan" style="width:12%;">
                                <div align="center">${lineHPB.noPengenalan}</div>
                            </display:column>
                            <display:column title="Jenis Pihak" style="width:12%;">
                                <div align="center">${lineHPB.jenis.nama}</div>
                            </display:column>
                            <display:column title="Bahagian yang diterima" style="width:20%;">
                                <div align="center">
                                    <s:hidden name="listHakmilikPihak[${lineHPB_rowNum-1}].idHakmilikPihakBerkepentingan"/>
                                    <s:text name="listHakmilikPihak[${lineHPB_rowNum-1}].syerPembilang" size="5" id="syer1${lineHPB_rowNum-1}"
                                            onblur="updateSyer('${lineHPB.idHakmilikPihakBerkepentingan}', '${lineHPB_rowNum-1}')"
                                            onchange="updateSyer('${lineHPB.idHakmilikPihakBerkepentingan}', '${lineHPB_rowNum-1}" class="pembilang"/> /
                                    <s:text name="listHakmilikPihak[${lineHPB_rowNum-1}].syerPenyebut" size="5" id="syer2${lineHPB_rowNum-1}"
                                            onblur="updateSyer('${lineHPB.idHakmilikPihakBerkepentingan}', '${lineHPB_rowNum-1}')"
                                            onchange="updateSyer('${lineHPB.idHakmilikPihakBerkepentingan}', '${lineHPB_rowNum-1}" class="penyebut"/>
                                </div>
                            </display:column>
                            <display:column title="Waris">
                                <c:if test="${lineHPB.jenis.kod eq 'PP' ||lineHPB.jenis.kod eq 'PA' ||lineHPB.jenis.kod eq 'PK'}">
                                    <div align="center">
                                        <img alt='Klik Untuk Tambah Waris' border='0' src='${pageContext.request.contextPath}/pub/images/tambah.png' class='rem'
                                             id='rem_${lineHPB_rowNum}' onclick="cariWaris('${lineHPB.idHakmilikPihakBerkepentingan}')" style="cursor:hand">
                                    </div>
                                </c:if>
                            </display:column>
                            <display:column title="Kemaskini" style="width:5%;">
                                <div align="center">
                                    <a href="#" onclick="popupKemaskiniPihak('${lineHPB.pihak.idPihak}', '${actionBean.hakmilik.idHakmilik}', '${lineHPB.idHakmilikPihakBerkepentingan}')">
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif'/>
                                    </a>
                                </div>
                            </display:column>
                            <display:column title="ID Pihak" property="pihak.idPihak" class="hidden" headerClass="hidden" media="html"/>
                        </display:table>
                    </div>
                    <div align="center">
                        <br>
                        <s:button name="btnpopupPihak"  id="btnpopupPihak" disabled="${disabledbtn}"  value="Tambah" class="btn" 
                                  onclick="popupPihak('${actionBean.hakmilik.idHakmilik}');" />&nbsp;
                        <c:if test="${fn:length(actionBean.listHakmilikPihak) > 1}">  
                            <s:button disabled="${disabledbtn}" class="longbtn" value="Agih Sama Rata" name="agihSamaRata" id="agihSamaRata" 
                                      onclick="samaRata(this.form);"/>&nbsp; 
                        </c:if>
                        <c:if test="${fn:length(actionBean.listHakmilikPihak) > 0}">               
                            <s:submit disabled="${disabledbtn}" class="longbtn" value="Simpan Syer" name="simpanSyer" id="simpanSyer" />&nbsp; 
                            <s:button class="btn" value="Hapus" name="" onclick="removePihak();" disabled="${disabledbtn}"/>&nbsp;      
                        </c:if>
                    </div>  
                    <br>
                </fieldset>
            </c:if>  
        </div>
    </s:form>        
</div>
