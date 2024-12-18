<%--
    Document   : popup_editAsas
    Created on : Feb 23, 2010, 2:05:49 PM
    Author     : mohd.fairul
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<style type="text/css">
    td.s{
        font-weight:normal;
        color:black;
        text-align: left;
    }
    input , select {
        width: 95%;
    }
    td{
        font-size: 100% !important;
    }
    .arrow { background:transparent url(${pageContext.request.contextPath}/pub/images/arrows.png) no-repeat scroll 0px -16px; width:100%; height:16px; display:block;}
    .up { background-position:0px 0px;}

</style>

<script language="javascript">
    $(document).ready(function () {
        $('.alphanumeric').alphanumeric();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

        $('input').focus(function () {
            $(this).addClass("focus");
        });

        $('input').blur(function () {
            $(this).removeClass("focus");
        });

        $('select').focus(function () {
            $(this).addClass("focus");
        });

        $('select').blur(function () {
            $(this).removeClass("focus");
        });


    <%--        $('#mlot').toggle();
            $('#l1lot').toggle();
            $('#salinanH').toggle();--%>


    });

    <%--   function popupCarian(pathCall)
       {
           var url ="${pageContext.request.contextPath}/daftar/nota/nota_daftar?"+pathCall;
           window.open(url,"eTanah10","status=0,toolbar=0,location=0,menubar=0,width=1000,height=550,scrollbars=yes");
       }--%>

    function popupFormSyaratNyata() {
        var url = "${pageContext.request.contextPath}/pendaftaran/maklumat_asas?showFormCariKodSyaratNyata";
        window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
    }

    function popupFormKodSekatan() {
        var url = "${pageContext.request.contextPath}/pendaftaran/maklumat_asas?showFormCariKodSekatan";
        window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
    }


    function save(event, f, idH)
    {



        var q = $(f).formSerialize();
        var url = f.action + '?' + event + '&idH=' + idH;

        $.post(url, q,
                function (data) {
                    $('#page_div').html(data);
                }, 'html');

    }

    function showMe(thID) {

        $('#' + thID).toggle();
        $('.' + thID).find(".arrow").toggleClass("up");
    }

    function filterKodGunaTanah() {
        var katTanah = $("#katTanah").val();
        $.post('${pageContext.request.contextPath}/daftar/pembetulan/betul?senaraiKodGunaTanahByKatTanah&kodKatTanah=' + katTanah,
                function (data) {
                    if (data != '') {
                        $('#partialKodGunaTanah').html(data);
                        $.unblockUI();
                    }
                }, 'html');

    }

    function filterTempoh(idH) {
        var tempoh = $("#tempoh").val();
        if (tempoh > 99)
        {
            alert('Tempoh Pegangan Hakmilik : ' + [idH] + ' yang dimasukkan lebih dari 99 tahun');
            $('#tempoh').val("");
            return false;
        } else
        {
            $.post('${pageContext.request.contextPath}/daftar/pembetulan/betul?kiraTempoh&idHakmilik=' + idH + '&tempoh=' + tempoh,
                    function (data) {
                        if (data != '') {
                            $('#tarikhLuput').val(data);
                        }
                    }, 'html');
        }
    }

    function zeroPad(num, count)
    {
        if (num != '') {
            var numZeropad = num + '';
            while (numZeropad.length < count) {
                numZeropad = "0" + numZeropad;
            }

            $("#noLot").val(numZeropad);
        }
        return numZeropad;
    }

    function filterDate(idH) {
        var tarikh = $("#tarikhLuput").val();
        $.post('${pageContext.request.contextPath}/daftar/pembetulan/betul?kiraDate&idHakmilik=' + idH + '&tarikh=' + tarikh,
                function (data) {
                    if (data != '') {
                        $('#tempoh').val(data);
                    }
                }, 'html');

    }
    function validateNumber(elmnt, content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }
    String.prototype.lpad = function (padString, length) {
        var str = this;
        while (str.length < length)
            str = padString + str;
        return str;
    }
    function convert() {
        var v1 = document.getElementById('val1').value;
        var luas = document.getElementById('input1').value;
        var ekar = luas.split(".");
        var hektar;
        var i;
        var total;
        if (v1 == 'P') {
            var v10 = (pad(ekar[0], 4));
            var v11 = "0." + ekar[1];
            var v12 = v11 * 160;
            var v16 = v12 + "";
            var ekar1 = v16.split(".");
            var v13 = (pad(ekar1[0], 3));
            var v14 = (pad1(ekar1[1], 4));
            var v15 = v10 + "" + v13 + "." + v14
            var v16 = parseFloat(v15).toFixed(4);
        }
        if (v1 == 'H') {
            var hektarekar = luas * 0.404646
            var v10 = (pad(hektarekar[0], 4));
            var v11 = "0." + hektarekar[1];
            var v12 = v11 * 160;
            var v16 = v12 + "";
            var hektarekar1 = v16.split(".");
            var v13 = (pad(hektarekar1[0], 3));
            var v14 = (pad1(hektarekar1[1], 4));
            var v15 = v10 + "" + v13 + "." + v14
            var v16 = parseFloat(v15).toFixed(4);
        }
        if (v1 == 'kaki') {
            var kakiekar = luas * 0.404646
            var v10 = (pad(kakiekar[0], 4));
            var v11 = "0." + kakiekar[1];
            var v12 = v11 * 160;
            var v16 = v12 + "";
            var kakiekar1 = v16.split(".");
            var v13 = (pad(kakiekar1[0], 3));
            var v14 = (pad1(kakiekar1[1], 4));
            var v15 = v10 + "" + v13 + "." + v14
            var v16 = parseFloat(v15).toFixed(4);
        }
        if (v1 == 'meterpersegi') {
            var MPekar = luas * 0.404646
            var v10 = (pad(MPekar[0], 4));
            var v11 = "0." + MPekar[1];
            var v12 = v11 * 160;
            var v16 = v12 + "";
            var MPekar1 = v16.split(".");
            var v13 = (pad(MPekar1[0], 3));
            var v14 = (pad1(MPekar1[1], 4));
            var v15 = v10 + "" + v13 + "." + v14
            var v16 = parseFloat(v15).toFixed(4);
        }
        $('#input2').val(pad(v16, 12));
    }
    function pad(value, length) {
        return (value.toString().length < length) ? pad("0" + value, length) : value;
    }
    function pad1(value, length) {
        return (value.toString().length > length) ? pad("0" + value, length) : value;
    }






</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form name="form1" beanclass="etanah.view.stripes.nota.pembetulanActionBean">
    <div id="page_div">
        <s:messages />
        <s:errors />

        <div class="subtitle">

            <fieldset class="aras1">
                <legend>
                    Maklumat Asas Hakmilik
                </legend>
                <p style="color:red">
                    *Isi ruang pembetulan kemudian tekan butang simpan.<br/>

                </p>
                <div align="center">
                    <table class="tablecloth" width="70%">
                        <tr><th colspan="3">ID Hakmilik - ${actionBean.hakmilik.idHakmilik}</th></tr>
                        <tr><th colspan="3">Butiran Hakmilik</th></tr>
                        <tr><td colspan="3"><table class="tablecloth">
                                    <tr><th>Perkara</th><th>Maklumat Lama</th><th>Maklumat Baru</th></tr>
                                    <tr><td>Nombor Fail:</td><td class="s">${actionBean.hakmilik.noFail}</td><td><s:text name="permohonanPembetulanHakmilik.noFail" title="Nombor Fail" onkeyup="this.value=this.value.toUpperCase();"/></td></tr>

                        </tr>
                        <tr><td>Tarikh Daftar :</td><td class="s"><s:format formatPattern="dd/MM/yyyy" value="${actionBean.hakmilik.tarikhDaftar}"/></td>
                            <td class="s">                                
                                <s:text name="tarikhDaftar" id="tarikhDaftar" class="datepicker"/>
                            </td></tr>
                         <tr><td>Tarikh Daftar Asal:</td><td class="s"><s:format formatPattern="dd/MM/yyyy" value="${actionBean.hakmilik.tarikhDaftarAsal}"/></td>
                            <td class="s">
                                <s:text name="tarikhDaftarAsal" id="tarikhDaftarAsal" class="datepicker"/>
                            </td> 
                        </tr>
                        <tr><td>Taraf Pemilikan :</td><td class="s">
                                <c:if test="${actionBean.hakmilik.pegangan eq 'S'}">
                                    S : SELAMA-LAMANYA
                                </c:if>
                                <c:if test="${actionBean.hakmilik.pegangan eq 'P'}">
                                    P : PAJAKAN
                                </c:if>
                            </td>
                            <c:if test="${actionBean.hakmilik.kodHakmilik.kod ne 'GRN'
                                          or actionBean.hakmilik.kodHakmilik.kod ne 'GM'
                                          or actionBean.hakmilik.kodHakmilik.kod ne 'GMM'
                                          or actionBean.hakmilik.kodHakmilik.kod ne 'PN'
                                          or actionBean.hakmilik.kodHakmilik.kod ne 'PM'
                                          or actionBean.hakmilik.kodHakmilik.kod ne 'HMM'}">
                                  <td class="s">
                                      <c:if test="${actionBean.hakmilik.pegangan eq 'S'}">                                          
                                          <s:select name="permohonanPembetulanHakmilik.pegangan" value="">
                                              <c:if test="${actionBean.permohonanPembetulanHakmilik.pegangan == null}">
                                                  <s:option value="">Sila Pilih</s:option>
                                              </c:if>
                                              <c:if test="${actionBean.permohonanPembetulanHakmilik.pegangan != null}">
                                                  <c:if test="${actionBean.permohonanPembetulanHakmilik.pegangan eq 'S'}">
                                                      <s:option value="S">[S] SELAMA-LAMANYA </s:option>
                                                  </c:if>
                                                  <c:if test="${actionBean.permohonanPembetulanHakmilik.pegangan eq 'P'}">
                                                      <s:option value="P">[P] PAJAKAN</s:option>
                                                  </c:if>
                                              </c:if>                                              
                                              <c:if test="${actionBean.hakmilik.pegangan eq 'S'}">
                                                  <s:option value="S">[S] SELAMA-LAMANYA </s:option>
                                                  <s:option value="P">[P] PAJAKAN</s:option></c:if>                                              
                                          </s:select>
                                      </c:if>
                                      <c:if test="${actionBean.hakmilik.pegangan eq 'P'}">
                                          <s:select name="permohonanPembetulanHakmilik.pegangan" value="">

                                              <c:if test="${actionBean.permohonanPembetulanHakmilik.pegangan == null}">
                                                  <s:option value="">Sila Pilih</s:option>
                                              </c:if>
                                              <c:if test="${actionBean.permohonanPembetulanHakmilik.pegangan != null}">
                                                  <c:if test="${actionBean.permohonanPembetulanHakmilik.pegangan eq 'S'}">
                                                      <s:option value="S">[S] SELAMA-LAMANYA </s:option>
                                                  </c:if>
                                                  <c:if test="${actionBean.permohonanPembetulanHakmilik.pegangan eq 'P'}">
                                                      <s:option value="P">[P] PAJAKAN</s:option>
                                                  </c:if>
                                              </c:if>

                                              <c:if test="${actionBean.hakmilik.pegangan eq 'P'}">
                                                  <s:option value="S">[S] SELAMA-LAMANYA </s:option>
                                                  <s:option value="P">[P] PAJAKAN</s:option></c:if>
                                          </s:select>
                                      </c:if>
                                  </c:if>
                            </td></tr>
                            <%--<c:if test="${actionBean.hakmilik.pegangan eq 'P'}">
                            <tr><td>Tempoh Pajakan :</td><td class="s">${actionBean.hakmilik.tempohPegangan}</td><td><s:text name="permohonanPembetulanHakmilik.tempohPengangan"  id="tempoh" onchange="filterTempoh('${actionBean.hakmilik.idHakmilik}');"/></td></tr>
                        </c:if>--%>
                        <tr><td>Tempoh Pajakan :</td><td class="s">${actionBean.hakmilik.tempohPegangan}</td><td><s:text name="permohonanPembetulanHakmilik.tempohPengangan"  id="tempoh" onchange="filterTempoh('${actionBean.hakmilik.idHakmilik}');"/></td></tr>                      
                        <tr><td>Tarikh Luput :</td><td class="s"><s:format formatPattern="dd/MM/yyyy" value="${actionBean.hakmilik.tarikhLuput}"/></td>

                            <c:if test="${actionBean.hakmilik.kodHakmilik.kod ne 'GRN'
                                          or actionBean.hakmilik.kodHakmilik.kod ne 'GM'
                                          or actionBean.hakmilik.kodHakmilik.kod ne 'GMM'
                                          or actionBean.hakmilik.kodHakmilik.kod ne 'PN'
                                          or actionBean.hakmilik.kodHakmilik.kod ne 'PM'
                                          or actionBean.hakmilik.kodHakmilik.kod ne 'HMM'}">

                                  <td class="s">
                                      <%--<c:if test="${actionBean.hakmilik.pegangan ne 'S'}">
                                          <s:text name="tarikhLuput" id="tarikhLuput" class="datepicker"/>
                                      </c:if>--%>                                  
                                      <s:text name="tarikhLuput" id="tarikhLuput" class="datepicker"/>

                                  </td></tr>

                        </c:if>
                        <tr><td>Kategori Tanah :</td><td class="s">${actionBean.hakmilik.kategoriTanah.nama}</td>
                            <td>
                                <%--<s:select name="permohonanPembetulanHakmilik.kategoriTanah.kod" value="${actionBean.permohonanPembetulanHakmilik.kategoriTanah.kod}">
                                    <c:if test="${actionBean.permohonanPembetulanHakmilik.kategoriTanah.kod == null}">
                                        <s:option value="">Sila Pilih</s:option>
                                    </c:if>
                                    <c:if test="${actionBean.permohonanPembetulanHakmilik.kategoriTanah.kod != null}">
                                        <s:option value="">${actionBean.permohonanPembetulanHakmilik.kategoriTanah.nama}</s:option>
                                    </c:if>
                                    <s:options-collection collection="${list.senaraiKodKategoriTanah}" label="nama" value="kod"/>
                                </s:select>--%>
                                <s:select style="text-transform:uppercase" name="kategoriTanah" id="katTanah" value="${actionBean.permohonanPembetulanHakmilik.kategoriTanah.kod}">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodKategoriTanah}" label="nama" value="kod" />
                                </s:select>
                            </td></tr>
                        <tr><td>Kegunaan Tanah :</td><td class="s">${actionBean.hakmilik.kegunaanTanah.nama}</td>
                            <td>                               
                                <s:select style="text-transform:uppercase" name="gunatanah" id="gunatanah" value="${actionBean.permohonanPembetulanHakmilik.kegunaanTanah.kod}">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKegunaanTanah}" label="nama" value="kod" />
                                </s:select>
                            </td></tr>
                        <tr><td>Syarat Nyata :</td><td class="s">${actionBean.hakmilik.syaratNyata.kod}</td><td><s:text name="kod_syarat" id="syaratNyata"  onclick="popupFormSyaratNyata();"/></td></tr>
                        <tr><td>Sekatan Kepentingan :</td><td class="s">${actionBean.hakmilik.sekatanKepentingan.kod}</td><td><s:text name="kod_sekatan"  id="sekatan" onclick="popupFormKodSekatan();"/></td></tr>
                        <tr><td>Jenis Rizab :</td>
                            <td class="s">${actionBean.hakmilik.rizab.nama}</td>
                            <td>
                                <s:select name="rizab_kod" value="${actionBean.permohonanPembetulanHakmilik.rizab.kod}">
                                    <c:if test="${actionBean.permohonanPembetulanHakmilik.rizab.kod == null}">
                                        <s:option value="">Sila Pilih</s:option>
                                    </c:if>
                                    <c:if test="${actionBean.permohonanPembetulanHakmilik.rizab.kod != null}">
                                        <s:option value="">${actionBean.permohonanPembetulanHakmilik.rizab.nama}</s:option>
                                    </c:if>
                                    <s:options-collection collection="${list.senaraiKodRizab}" label="nama" value="kod"/>
                                </s:select>
                            </td></tr>
                            <%-- <tr><td>Amaun Cukai (RM) :</td><td class="s">${actionBean.hakmilik.cukai}</td><td><s:text name="permohonanPembetulanHakmilik.cukai" title="Cukai"/></td></tr>--%>
                        <tr><td>No. Permohonan Ukur :</td><td class="s">${actionBean.hakmilik.noPu}</td><td><s:text name="permohonanPembetulanHakmilik.noPU"/></td></tr>
                    </table> </td></tr>
                    <%--maklumat lot--%>
                    <tr onclick="showMe('mlot')" onmouseover="this.style.cursor = 'pointer';
                            this.style.text" class="mlot"><th colspan="3"><span class="arrow">Maklumat Lot</span></th></tr>
                    <tr id="mlot"><td><table>
                                <tr><th>Perkara</th><th>Maklumat Lama</th><th>Maklumat Baru</th></tr>
                                <tr>
                                    <td>Kod Lot :</td><td class="s">${actionBean.hakmilik.lot.nama}</td><td>
                                        <s:select name="kodLot" value="${actionBean.permohonanPembetulanHakmilik.kodLot.kod}">
                                            <c:if test="${actionBean.permohonanPembetulanHakmilik.kodLot.kod == null}">
                                                <s:option value="">Sila Pilih</s:option>
                                            </c:if>
                                            <c:if test="${actionBean.permohonanPembetulanHakmilik.kodLot.kod != null}">
                                                <s:option value="">${actionBean.permohonanPembetulanHakmilik.kodLot.nama}</s:option>
                                            </c:if>
                                            <s:options-collection collection="${list.senaraiKodLot}" label="nama" value="kod"/>
                                        </s:select></td>
                                </tr>
                                <tr><td>Nombor Lot :</td><td class="s">${actionBean.hakmilik.noLot}</td><td><s:text id="noLot" maxlength="7" name="permohonanPembetulanHakmilik.noLot" onblur="zeroPad(this.value,7);"/></td></tr>
                                        <%--<tr><td>Seksyen :</td><td  class="s">${actionBean.hakmilik.seksyen.nama}</td>
                                            <td>
                                                <s:select name="kodseksyen" value="${actionBean.permohonanPembetulanHakmilik.seksyen.kod}">
                                                    <s:option value="">Sila Pilih</s:option>
                                                    <s:options-collection collection="${actionBean.listKodSeksyenByBpm}" label="nama" value="kod"/>
                                                </s:select>
                                            </td></tr>--%>
                                <tr><td>No. Lembaran Piawai :</td><td class="s">${actionBean.hakmilik.noLitho}</td><td><s:text name="permohonanPembetulanHakmilik.noLitho" /></td></tr>
                                <tr><td>Nombor Pelan :</td><td class="s">${actionBean.hakmilik.noPelan}</td><td><s:text name="permohonanPembetulanHakmilik.noPelan" /></td></tr>
                                <tr>
                                    <td>Unit Luas :</td><td class="s">${actionBean.hakmilik.kodUnitLuas.nama}</td><td>
                                        <s:select id="val1" name="kodUOM" value="${actionBean.permohonanPembetulanHakmilik.uom.kod}">
                                            <c:if test="${actionBean.permohonanPembetulanHakmilik.uom.kod == null}">
                                                <s:option value="">Sila Pilih</s:option>
                                            </c:if>
                                            <c:if test="${actionBean.permohonanPembetulanHakmilik.uom.kod != null}">
                                                <s:option value="">${actionBean.permohonanPembetulanHakmilik.uom.nama}</s:option>
                                            </c:if>
                                            <s:options-collection id="a1" collection="${list.senaraiKodUOMByLuas}" label="nama"  value="kod"/>
                                        </s:select></td>  
                                </tr>
                                <tr><td>Luas :</td><td class="s">${actionBean.hakmilik.luas}</td><td><s:text name="permohonanPembetulanHakmilik.luas" id="input1"/></td></tr>

                                <tr>
                                    <td>Unit Luas (Luas Lama) :</td><td class="s">${actionBean.hakmilik.kodUnitLuasLama.nama}</td><td>
                                        <s:select id="val1" name="kodUOMLama" value="${actionBean.permohonanPembetulanHakmilik.uomLama.kod}">
                                            <c:if test="${actionBean.permohonanPembetulanHakmilik.uomLama.kod == null}">
                                                <s:option value="">Sila Pilih</s:option>
                                            </c:if>
                                            <c:if test="${actionBean.permohonanPembetulanHakmilik.uomLama.kod != null}">
                                                <s:option value="">${actionBean.permohonanPembetulanHakmilik.uomLama.nama}</s:option>
                                            </c:if>
                                            <s:options-collection id="a1" collection="${list.senaraiKodUOMByLuas}" label="nama"  value="kod"/>
                                        </s:select></td>  
                                </tr>
                                
                                <tr><td>Luas Lama:</td><td class="s">${actionBean.hakmilik.luasLama}</td><td><s:text name="permohonanPembetulanHakmilik.luasLama"/></td></tr>
                                <tr ><td>Lokasi :</td><td  class="s">${actionBean.hakmilik.lokasi}</td><td><s:text name="permohonanPembetulanHakmilik.lokasi"/></td></tr>
                            </table>
                        </td></tr>
                    <tr><td>
                            <div align="center">
                                <s:button name="saveBetul" value="Simpan" class="btn" onclick="save(this.name, this.form, '${actionBean.hakmilik.idHakmilik}')"/>
                                <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:self.close()"/>
                            </div>
                        </td></tr>
                    </table>
                </div>
            </fieldset>
        </div>
    </div>
</s:form>

