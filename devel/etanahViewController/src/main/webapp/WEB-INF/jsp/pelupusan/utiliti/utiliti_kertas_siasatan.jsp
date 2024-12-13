<%-- 
    Document   : utiliti_kertas_siasatan
    Created on : Apr 14, 2010, 5:34:55 PM
    Author     : Erra Zyra
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.tools.min.js?select=full&debug=true"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">

    $(document).ready(function(){
    <%-- $("img[title]").tooltip({
         // tweak the position
         offset: [10, 2],

            // use the "slide" effect
            effect: 'slide'
        }).dynamic({ bottom: { direction: 'down', bounce: true } });--%>
                dialogInit('Carian Hakmilik');
                $('#idHakmilik').keyup(function(){
                    closeDialog();
                });
            });

            function doSearch(e,f) {
                var a = $('#idPermohonan').val();
                if(a == ''){
                    alert('Sila masukan id Perserahan');
                    return;
                }
                f.action = f.action + '?' + e;
                f.submit();
            }

            function simpan (a) {
                var a = $('#idPermohonan').val();
                var url = '${pageContext.request.contextPath}/pelupusan/utiliti_kertasSiasatan?kertasStasatanSimpan&a=' + a;
                f = document.form1;
                f.action = url;
                f.submit();
            }

            function doBlockUI (){
                $.blockUI({
                    message: $('#displayBox'),
                    css: {
                        top:  ($(window).height() - 50) /2 + 'px',
                        left: ($(window).width() - 50) /2 + 'px',
                        width: '50px'
                    }
                });
            }

            function doSearch2 (idPermohonan) {
                var url = '${pageContext.request.contextPath}/pelupusan/utiliti_kertasSiasatan?search&idPermohonan=' + idPermohonan;
                f = document.form1;
                f.action = url;
                f.submit();
            }

            function carianSemula () {
                $('#idPermohonan').val('');
                var url = '${pageContext.request.contextPath}/pelupusan/utiliti_kertasSiasatan';
                f = document.form1;
                f.action = url;
                f.submit();
            }
    
            function doPrintViaApplet (docId) {
                //alert('tsttt');
                var a = document.getElementById('applet');
                a.doPrint(docId.toString());
                //a.printDocument(docId.toString());
            }

            function doViewReport(v) {
                var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
                window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
            }

            function doSaveCapaian(v){
                var sbb = $('#sbb_cetakan_semula').val();
                if(sbb == '') {
                    alert('Sila masukan Sebab');
                    return;
                }
                var url = '${pageContext.request.contextPath}/pelupusan/utiliti_kertasSiasatan?cetakSemula&sbb_cetakan_semula=' + sbb + '&id_dokumen=' + v;
                $.ajax({
                    type:"GET",
                    url : url,
                    success : function(data) {
                        if(data == '1'){
                            doPrintViaApplet(v);
                        }
                    }
                });
        
            }

            function toUppercase(id){
                $('#' + id).val($('#' + id).val().toUpperCase());
            }
            //    Azmi
            function sejarahCetakan(id){
                window.open("${pageContext.request.contextPath}/pelupusan/utiliti_kertasSiasatan?viewSejarahCetakan&idDokumen="+id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
            }
            //    Azmi
</script>
<style type="text/css">
    .tooltip {
        display:none;
        background:transparent url(${pageContext.request.contextPath}/pub/images/black_arrow.png);
        font-size:12px;
        height:70px;
        width:160px;
        padding:25px;
        color:#fff;
    }

    <%--.tooltip {
        background-color:#000;
        border:1px solid #fff;
        padding:10px 15px;
        width:200px;
        display:none;
        color:#fff;
        text-align:left;
        font-size:12px;

               /* outline radius for mozilla/firefox only */
               -moz-box-shadow:0 0 10px #000;
               -webkit-box-shadow:0 0 10px #000;
           }--%>
</style>
<s:messages/>
<s:errors/>
<s:form beanclass="etanah.view.stripes.pelupusan.utility.UtilitiKertasSiasatan" name="form1">
    <div class="subtitle">
        <!--        Menu: Utility Pendaftaran > Cetakan Semula-->
        <fieldset class="aras1">
            <legend>
                Carian Dokumen
            </legend>
            <p>
                <label>ID Perserahan :</label>
                <s:text name="idPermohonan" id="idPermohonan" onblur="toUppercase(this.id);"/>
            </p>
            <br/>
            <p>
                <label>&nbsp;</label>
                <s:button name="searchPartial" value="Cari" class="btn" onclick="doSearch(this.name, this.form);"/>
                <s:button name="" value="Isi Semula" class="btn" onclick="carianSemula();"/>
            </p>
        </fieldset>
    </div>
    <br/>
    <c:if test="${fn:length(actionBean.senaraiPermohonan) > 0}">
        <div class="subtitle">
            <s:messages/>
            <fieldset class="aras1">
                <table width="100%" border="0">
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="4" align="left">
                            <b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">Butiran mengenai pemohon dan tuan tanah adalah seperti </b> </td>
                    </tr>
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <tr>
                        <td align="left" colspan="4"><label>Pemohon:</label>${actionBean.pemohon.pihak.nama}</td>

                    </tr>
                    <tr>
                        <td align="left" colspan="4"><label>Alamat:</label>
                            ${actionBean.pemohon.pihak.alamat1}<br>
                            <label>&nbsp;</label>${actionBean.pemohon.pihak.alamat2}<br>
                            <label>&nbsp;</label>${actionBean.pemohon.pihak.alamat3}<br>
                            <label>&nbsp;</label>${actionBean.pemohon.pihak.alamat4}<br>
                            <label>&nbsp;</label>${actionBean.pemohon.pihak.poskod}&nbsp;${actionBean.pemohon.pihak.negeri.nama}<br>


                        </td>
                    </tr>
                    <tr>

                        <td align="left" colspan="4"><label>No. Tel:</label>
                            ${actionBean.pemohon.pihak.noTelefon1}<c:if test="${actionBean.pemohon.pihak.noTelefon1 ne null}">/</c:if>${actionBean.pemohon.pihak.noTelefon2}<c:if test="${actionBean.pemohon.pihak.noTelefon2 ne null}">/</c:if>${actionBean.pemohon.pihak.noTelefonBimbit}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <fieldset class="aras1">
                                <div class="content" align="center">
                                    <display:table class="tablecloth" name="${actionBean.mohanPihakList}" pagesize="5"
                                                   requestURI="/pelupusan/Kertas_stasatan"  id="line">
                                        <display:column title="Tuan Tanah" style="width:300px" property="pihak.nama"></display:column>
                                        <display:column title="Alamat" style="width:300px"  >${line.pihak.alamat1}&nbsp;${line.pihak.alamat1}&nbsp;${line.pihak.alamat2}&nbsp;${line.pihak.alamat3}&nbsp;${line.pihak.alamat4}&nbsp;${line.pihak.poskod}&nbsp;${line.pihak.negeri.nama}</display:column>
                                        <display:column title="No. Tel"  style="width:300px"  >${line.pihak.noTelefon1}&nbsp;${line.pihak.noTelefon2}&nbsp;${line.pihak.noTelefonBimbit}</display:column>
                                    </display:table>
                                </div>
                            </fieldset>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="4" align="left">
                            <b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">Butiran mengenai tanah</b>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="4"><label>Hakmilik:</label>
                            ${actionBean.hakmilikPermohonan.hakmilik.kodHakmilik.kod}&nbsp;${actionBean.hakmilikPermohonan.hakmilik.noHakmilik}
                        </td>
                    </tr>
                    <tr>
                        <td align="left" colspan="4" ><label>Mukim/Daerah:</label>
                            ${actionBean.hakmilikPermohonan.hakmilik.bandarPekanMukim.nama}&nbsp; ${actionBean.hakmilikPermohonan.hakmilik.daerah.nama}
                        </td>
                    </tr>
                    <tr>
                        <td align="left" colspan="4"><label>Keluasan:</label>
                            ${actionBean.hakmilikPermohonan.hakmilik.luas}&nbsp;${actionBean.hakmilikPermohonan.kodUnitLuas.kod}
                        </td>
                    </tr>
                    <tr>

                        <td align="left" colspan="4">
                            <label>Luas terlibat Hak Lalu Lalang:</label>
                            <s:text name="luasT" id="luasT" formatPattern="#,###,##0.0000"/>
                            <s:select name="kodUnitLuas" id="kodUnitLuas">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="H">Hektar</s:option>
                                <s:option value="M">Meter Persegi</s:option>
                            </s:select>
                        </td>

                    </tr>
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="4" align="left">
                            <b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">Berikut ini adalah merupakan soalan-soalan yang akan ditanyakan </b>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="4" align="left" ><label>Bila dan bagaimana caranya tuan tanah memiliki tanah ini?</label>
                            <s:textarea name="kand0" id="kand0"  rows="7" cols="69" class="normal_text"></s:textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" align="left"><label>Apakah jenis bangunan / tanaman yang ada di atas tanah ini?</label>
                            <s:textarea name="kand1" id="kand1"  rows="7" cols="69" class="normal_text"></s:textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" align="left"><label>Pernah memohon untuk pecah sempadan / tukar syarat / kedua-duanya serentak?</label>
                            <s:textarea name="kand2" id="kand2"  rows="7" cols="69" class="normal_text"></s:textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" align="left"><label>Apakah terdapat apa-apa bebanan seperti gadaian, pajakan, atau perjanjian jualbeli dengan pihak ketiga?</label>
                            <s:textarea name="kand3" id="kand3"  rows="7" cols="69" class="normal_text"></s:textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" align="left"><label>Berapakah pampasan yang dituntut dan apakah alasan-alasannya?</label>
                            <s:textarea name="kand4" id="kand4"  rows="7" cols="69" class="normal_text"></s:textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" align="left"><label>Laporan Nilaian Tanah/Kerosakan(pagar/Bagunan)oleh Jabatan Penilaian dan Perkhimatan Harta Melaka melalui suratnya bertarikh :</label>
                            <s:format formatPattern="MM/dd/yyyy" value="${actionBean.permohonanRujukanLuar.tarikhRujukan}"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="left" colspan="4" ><label>Nilaian Tanah:</label>
                            RM <s:format formatPattern="###,###,##0" value="${actionBean.permohonanRujukanLuar.nilai}"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="left" colspan="4"><label>Kerosakan:</label>
                            <s:text name="kand5" id="kand5" size="20" />
                        </td>
                    </tr>
                    <tr>
                        <td align="left" colspan="4"><label>Lain-lain:</label>
                            <s:text name="kand6" id="kand6" size="20" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" align="left">
                            <label>Keterangan Pemohon:</label>
                            <s:textarea name="kand7" id="kand7"  rows="7" cols="69" class="normal_text"></s:textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" align="left">
                            <label>Keterangan Tuan Tanah:</label>
                            <s:textarea name="kand8" id="kand8"  rows="7" cols="69" class="normal_text"></s:textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" align="left">
                            <label> Pertimbangan PTD:</label>
                            <s:textarea name="kand9" id="kand9"  rows="7" cols="69" class="normal_text"></s:textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" align="left">
                            <label>Keputusan / Perintah PTD:</label>
                            <s:textarea name="kand10" id="kand10"  rows="7" cols="69" class="normal_text"></s:textarea>
                        </td>
                    </tr>

                    <tr>

                        <td colspan="4" align="center">
                            <s:button name="idPermohonan" id="idPermohonan" value="Simpan" class="btn" onclick="simpan(this.name, this.form);"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>
    </c:if>

</s:form>