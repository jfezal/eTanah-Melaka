<%--
    Document   :  laporan_tanahGSAKeadaanTanah.jsp
    Created on :  Jan 17, 2012, 10:50:13 AM
    Author     :  Shazwan
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PERIHAL TANAH</title>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">
    var size = 0 ;
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
        },'html');
    }
   
    $(document).ready(function() {
         
        //            maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    <c:if test="${actionBean.laporanTanah.kecerunanTanah ne null}">
        <c:if test="${actionBean.laporanTanah.kecerunanTanah.kod eq 'TG'}">
                $('#tinggi').show();
                $('#cerun').hide();
                $('#dalam').hide();
        </c:if>
        <c:if test="${actionBean.laporanTanah.kecerunanTanah.kod eq 'RD'}">
                    $('#tinggi').hide();
                    $('#cerun').show();
                    $('#dalam').hide();
        </c:if>
        <c:if test="${actionBean.laporanTanah.kecerunanTanah.kod eq 'BK'}">
                $('#tinggi').hide();
                $('#cerun').show();
                $('#dalam').hide();
        </c:if>
        <c:if test="${actionBean.laporanTanah.kecerunanTanah.kod eq 'CR'}">
                $('#tinggi').hide();
                $('#cerun').show();
                $('#dalam').hide();
        </c:if>
        <c:if test="${actionBean.laporanTanah.kecerunanTanah.kod eq 'PY'}">
                $('#tinggi').hide();
                $('#cerun').hide();
                $('#dalam').show();
        </c:if>
        <c:if test="${actionBean.laporanTanah.kecerunanTanah.kod ne 'TG' and actionBean.laporanTanah.kecerunanTanah.kod ne 'CR' and actionBean.laporanTanah.kecerunanTanah.kod ne 'PY' and actionBean.laporanTanah.kecerunanTanah.kod ne 'BK' and actionBean.laporanTanah.kecerunanTanah.kod ne 'RD'}">
                $('#tinggi').hide();
                $('#cerun').hide();
                $('#dalam').hide();
        </c:if>    
    </c:if>
    <c:if test="${actionBean.laporanTanah.kecerunanTanah eq null}">
            $('#tinggi').hide();
            $('#cerun').hide();
            $('#dalam').hide();
    </c:if>
    <c:if test="${actionBean.laporanTanah.kodKeadaanTanah ne null}">
        <c:if test="${actionBean.laporanTanah.kodKeadaanTanah.kod eq 'LL'}">
                $('#keadaantanahlainlain').show();
        </c:if>
        <c:if test="${actionBean.laporanTanah.kodKeadaanTanah.kod ne 'LL'}">
                $('#keadaantanahlainlain').hide();
        </c:if>
    </c:if>
    <c:if test="${actionBean.laporanTanah.kodKeadaanTanah eq null}">
            $('#keadaantanahlainlain').hide();
    </c:if>
        <c:if test="${actionBean.laporanTanah.usaha eq null or actionBean.laporanTanah.usaha eq 'T'}">
            $('#nilaianTanah').hide();
            $('#anggaranPokok ').hide();
            $('#luasTanam').hide();
            $('#jenisTanaman').hide();
            $('#tarikh').hide();
            $('#pengusuka').hide();
        </c:if> 
        <c:if test="${actionBean.laporanTanah.adaBangunan ne null or actionBean.laporanTanah.adaBangunan eq 'Y'}">
            $('#bangunan ').show();
        </c:if>    
        <c:if test="${actionBean.laporanTanah.adaBangunan eq null or actionBean.laporanTanah.adaBangunan eq 'T'}">
            $('#bangunan ').hide();
        </c:if>
        
        }); //END OF READY FUNCTION
         
        function changeKeadaanTanah(text){
            /*
             * LEGEND KOD
             * RATA = RT
             * BERBUKIT = BK
             * TINGGI = TG
             * RENDAH = RD
             * CURAM = CR
             * BERPAYA = PY
             * SEPARAS DENGAN JALAN = SJ
             * TINGGI DARI ARAS JALAN = TJ
             * RENDAH DARI ARAS JALAN = RJ
             */
            if(text == "RT"){
                $('#tinggi').hide();
                $('#cerun').hide();
                $('#dalam').hide();
            }

            else if(text == "BK"){
                $('#tinggi').hide();
                $('#cerun').show();
                $('#dalam').hide();
            }

            else if(text == "TG"){
                $('#tinggi').show();
                $('#cerun').hide();
                $('#dalam').hide();
            }

            else if(text == "RD"){
                $('#tinggi').hide();
                $('#cerun').show();
                $('#dalam').hide();
            }

            else if(text == "CR"){
                $('#tinggi').hide();
                $('#cerun').show();
                $('#dalam').hide();
            }

            else if(text == "PY"){
                $('#tinggi').hide();
                $('#cerun').hide();
                $('#dalam').show();
            }
        
            else{
                $('#tinggi').hide();
                $('#cerun').hide();
                $('#dalam').hide();
            }
        }
    
        function validateNumber(elmnt,content) {
            //if it is character, then remove it..
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }
    
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
    
        function showHidePengusaha(val){
            if(val=='show'){
                $('#pengusuka').show();
                $('#tarikh').show();
                $('#tempoh').show();
                $('#nilaianTanah').show();
                $('#anggaranPokok').show();
                $('#luasTanam').show();
                $('#jenisTanaman').show();
            }
            else if(val =='hide'){
                $('#pengusuka').hide();
                $('#tarikh').hide();
                $('#Bangunantable').hide();
                $('#tempoh').hide();
                $('#nilaianTanah').hide();
                $('#anggaranPokok').hide();
                $('#luasTanam').hide();
                $('#jenisTanaman').hide();
            }
            
        }
        
        function showHideBilBangunan(val){
            if(val == 'show')
                $('#bangunan').show();            
            else if(val =='hide')
                $('#bangunan').hide();
        }
        function tambahBangunan(kategori){
            var idHakmilik = '${actionBean.hakmilik.idHakmilik}';
            window.open("${pageContext.request.contextPath}/pelupusan/laporan_tanahGSA?tambahBangunanPopup&kategori="+kategori+"&idLaporTanah="+${actionBean.laporanTanah.idLaporan}, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }
        function editLaporanBangunan(idLaporBangunan){
        var idHakmilik = '${actionBean.hakmilik.idHakmilik}';
        window.open("${pageContext.request.contextPath}/pelupusan/laporan_tanahGSA?editBangunanPopup&idHakmilik="+idHakmilik+'&idLaporBangunan='+idLaporBangunan+"&idLaporTanah="+${actionBean.laporanTanah.idLaporan}, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }
        
        function refreshpage(){
//        alert('aa');
        NoPrompt();
        opener.refreshGSA('main');
        self.close();
        }
</script>
<body>
    <script>
        // Allow the user to be warned by default.
                var allowPrompt = true;
                
                window.onbeforeunload = WarnUser;
                function WarnUser()
                {   
                    if(allowPrompt)
                        refreshpage();
                    if(allowPrompt)
                    {
                        event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
                    }
                    else
                    {
                        // Reset the flag to its default value.
                        allowPrompt = true;
                    }
                }
                function NoPrompt()
                {
                    allowPrompt = false;
                }

    </script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pelupusan.LaporanTanahGSAPelupusanActionBean" name="form">
    <s:errors/>
    <s:messages/>
    <s:hidden name="idHakmilik"/>
    <div class="subtitle" id="main">
        <fieldset class="aras1">

            <div id="perihaltanah">
                <legend>Keadaan Tanah</legend>
                <div class="content" align="center">
                    <table class="tablecloth" border="0">
                        <tr>
                            <td>
                                Kecerunan Tanah :
                            </td>
                            <td>
                                <s:select name="kecerunanT" id="kecerunanT" onchange="javaScript:changeKeadaanTanah(this.options[selectedIndex].value)">
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodKecerunanTanah}" label="nama" value="kod" />
                                </s:select>
                            </td>
                        </tr>
                        <tr id="tinggi">
                            <td>
                                Ketinggian Dari Paras Jalan (m) :
                            </td>
                            <td>
                                <s:text name="laporanTanah.ketinggianDariJalan" id="ketinggianDariJalan" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                            </td>
                        </tr>  
                        <tr id="cerun">
                            <td>
                                Darjah Kecerunan :
                            </td>
                            <td>
                                <s:text name="laporanTanah.kecerunanBukit" id="kecerunanBukit" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                            </td>
                        </tr>
                        <tr id="dalam">
                            <td>
                                Dalam Paras Air (m) :
                            </td>
                            <td>
                                <s:text name="laporanTanah.parasAir" id="parasAir" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Klasifikasi Tanah :
                            </td>
                            <td>
                                <s:select name="klasifikasiT" id="klasifikasiT" value="${actionBean.laporanTanah.kodKeadaanTanah.kod}">
                                    <s:option value="0">--Sila Pilih--</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodStrukturTanah}" label="nama" value="kod" />
                                </s:select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Jika Lain-lain :
                            </td>
                            <td>
                                <s:text name="laporanTanah.strukturTanahLain" id="strukturTanahLain" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Keadaan Tanah :
                            </td>
                            <td>
                                <s:select name="keadaanTanah" id="keadaanTanah" onchange="javaScript:showkeadaantanahlain(this.value)">
                                    <s:option value="0">--Sila Pilih--</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodKeadaanTanah}" label="nama" value="kod" />
                                </s:select>
                            </td>
                        </tr>
                        <tr id="keadaantanahlainlain">
                            <td>
                                Lain-lain :
                            </td>
                            <td>
                                <s:textarea name="keadaankand" rows="5" cols="50"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Tanah Dipohon Bertumpu :
                            </td>
                            <td>
                                <s:select name="laporanTanah.tanahBertumpu" id="tanahBertumpu" >
                                    <s:option value="0">--Sila Pilih--</s:option>
                                    <s:option value="Y">Ya</s:option>
                                    <s:option value="T">Tidak</s:option>
                                </s:select>
                                &nbsp; Pada :     
                                <s:select name="laporanTanah.keteranganTanahBertumpu" id="keteranganTanahBertumpu" >
                                    <s:option value="0">--Sila Pilih--</s:option>
                                    <s:option value="Jalanraya">Jalanraya</s:option>
                                    <s:option value="Jalan Keretapi">Jalan Keretapi</s:option>
                                    <s:option value="Sungai">Sungai</s:option>
                                    <s:option value="Pantai">Pantai</s:option>
                                    <s:option value="Tiada">Tiada</s:option>
                                    <%--<s:options-collection collection="${listUtil.senaraiKodKecerunanTanah}" label="nama" value="kod" />--%>
                                </s:select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                &nbsp;
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <b>Dilintasi Oleh </b> :
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">
                                Talian Elektrik :
                            </td>
                            <td>
                                <s:checkbox name="laporanTanah.dilintasTiangElektrik" value="Y" id="dilintasTiangElektrik"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right"> 
                                Talian Telefon :
                            </td>
                            <td>
                                <s:checkbox name="laporanTanah.dilintasTiangTelefon" value="Y" id="dilintasTiangTelefon"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">
                                Laluan Gas :
                            </td>
                            <td>
                                <s:checkbox name="laporanTanah.dilintasLaluanGas" value="Y" id="dilintasLaluanGas"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">
                                Paip Air :
                            </td>
                            <td>
                                <s:checkbox name="laporanTanah.dilintasPaip" value="Y" id="dilintasPaip"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">
                                Tali Air :
                            </td>
                            <td>
                                <s:checkbox name="laporanTanah.dilintasTaliar" value="Y" id="dilintasTaliar"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">
                                Sungai :
                            </td>
                            <td>
                                <s:checkbox name="laporanTanah.dilintasSungai" value="Y" id="dilintasSungai"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">
                                Parit :
                            </td>
                            <td>
                                <s:checkbox name="laporanTanah.dilintasParit" value="Y" id="dilintasParit"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                &nbsp;
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Tanah Diusahakan :
                            </td>
                            <td>
                                <s:radio name="laporanTanah.usaha" value="Y" id="usaha" onclick="showHidePengusaha('show');"/>&nbsp;Ya
                                <s:radio name="laporanTanah.usaha" value="T"  id="usaha" onclick="showHidePengusaha('hide');"/>&nbsp;Tidak
                            </td>
                        </tr>
                        <tr id="pengusuka">
                            <td>
                                Nama Pengusaha :
                            </td>
                            <td>
                                <s:text name="laporanTanah.diusaha" size="40" id="diusaha"/>
                            </td>
                        </tr>
                        <tr id="tarikh">
                            <td>
                                Tarikh Mula Usaha :
                            </td>
                            <td>
                                <s:text name="laporanTanah.tarikhMulaUsaha2" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy"/>
                            </td>
                        </tr>
                        <tr id="jenisTanaman">
                            <td>
                                Jenis Tanaman
                            </td>
                            <td>
                                <s:textarea name="laporanTanah.usahaTanam" id="usahaTanam" cols="80" rows="5" />
                            </td>
                        </tr>
                        <tr id="luasTanam">
                            <td>
                                Luas Yang Ditanam (meter persegi)
                            </td>
                            <td>
                                <s:text name="usahaLuas" id="usahaLuas" size="35" onkeyup="validateNumber(this,this.value);"/>
                            </td>
                        </tr>
                        <tr id="anggaranPokok">
                            <td>
                                anggaran Bil.pokok
                            </td>
                            <td>
                                <s:text name="usahaBilanganPokok" id="usahaBilanganPokok" onkeyup="validateNumber(this,this.value);"/>
                            </td>
                        </tr>
                        <tr id="nilaianTanah">
                            <td>
                                Nilaian Tanah Termasuk Tanaman(RM)
                            </td>
                            <td>
                                <s:text name="usahaHarga" id="usahaHarga" size="35" onkeyup="validateNumber(this,this.value);"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                               Bangunan :
                            </td>
                            <td>
                                <s:radio name="laporanTanah.adaBangunan" value="Y" onclick="showHideBilBangunan('show');"/>&nbsp;Ada
                                <s:radio name="laporanTanah.adaBangunan" value="T" onclick="showHideBilBangunan('hide');"/>&nbsp;Tiada
                            </td>
                        </tr>
                        <tr>
                            <td colspan ="2">&nbsp;
                            </td>
                        </tr>
                        <tr id="bangunan">
                            <td colspan="2">
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
                                </display:table><s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBangunan('B');"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Tanah sudah Diperenggan :
                            </td>
                            <td>
                                <s:radio name="laporanTanah.perenggan" value="Y" />&nbsp;Sudah
                                <s:radio name="laporanTanah.perenggan" value="T" />&nbsp;Belum
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Rancangan Kerajaan Atas Tanah (Jika Ada) :
                            </td>
                            <td>
                                <s:text name="laporanTanah.rancanganKerajaan"/>
                            </td>
                        </tr>
                    </table>        
                </div>
                <br/>
            </div>
        </fieldset>
    
    <fieldset class="aras1">
        <table  width="100%" border="0">
            <tr>
                <td align="center">
                    <s:submit name="simpanKeadaanTanah" value="Simpan" class="btn" onclick="NoPrompt();"/>
                    <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                </td>
            </tr>
        </table>   
    </fieldset>
    </div>
</s:form>
</body>

