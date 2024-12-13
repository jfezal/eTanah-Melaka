<%-- 
    Document   : kertas_pertimbangan_PTG_rayuanTolak
    Created on : Feb 18, 2014, 10:38:44 AM
    Author     : khairul.hazwan
--%>


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%--<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />--%>
<% pageContext.setAttribute("newline", "\n");%>

<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:right;
        width:30em;
        vertical-align:top;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:50em;
    }

</style>
<script type="text/javascript">
    function showReport(){
    <%--window.open("${pageContext.request.contextPath}/pembangunan/melaka/kertasPertimbanganPTG?genReport", "eTanah",
    "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");--%>
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            var url = '${pageContext.request.contextPath}/pembangunan/melaka/kertasPertimbanganPTG_RayuanTolak?genReport';
            $.ajax({
                type:"GET",
                url : url,
                dataType : 'html',
                error : function(xhr, ajaxOptions, thrownError) {
                    alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
    <%--$("#folder_div").html('<div class=errors>Terdapat Masalah Teknikal. Sila Hubungi Pentadbir Sistem.</div><div id=t><a href="#">[ Lihat Terperinci ]</a></div><br/><div id=error class=b>' + xhr.responseText + '</div>');--%>
                    $.unblockUI();
                },
                success : function(data) {
                    if(data.indexOf('Laporan telah dijana')==0){
                        alert(data);
                        $('#folder').click();
                    }else {
                        alert(data);
                        $('#urusan').click();
                    }
                    $.unblockUI();
                }
            });
        }
        function addRow(tableid){
            var table = document.getElementById(tableid);
            var rowcount = table.rows.length;

            if(rowcount < 9){
                var row = table.insertRow(rowcount);

                var leftcell = row.insertCell(0);
                var bil = document.createTextNode(rowcount);
                leftcell.appendChild(bil);

                var middlecell = row.insertCell(1);
                var sel = document.createElement('select');
                sel.name = 'jabatan' + rowcount;
                sel.style.width = '400px';
                sel.options[0] = new Option('--Sila Pilih--','');
                sel.options[1] = new Option('Jabatan Kebajikan Masyarakat, Melaka','Jabatan Kebajikan Masyarakat');
                sel.options[2] = new Option('Jabatan Kerja Raya, Melaka','Jabatan Kerja Raya, Melaka');
                sel.options[3] = new Option('Jabatan Pengairan dan Saliran, Melaka','Jabatan Pengairan dan Saliran, Melaka');
                sel.options[4] = new Option('Jabatan Perancang Bandar dan Desa, Melaka','Jabatan Perancang Bandar dan Desa, Melaka');
                sel.options[5] = new Option('Jabatan Tenaga Kerja, Melaka','Jabatan Tenaga Kerja, Melaka');
                sel.options[6] = new Option('Jabatan Kesihatan, Melaka','Jabatan Kesihatan, Melaka');
                sel.options[7] = new Option('Jabatan Pertanian, Melaka','Jabatan Pertanian, Melaka');
                sel.options[8] = new Option('Jabatan Alam Sekitar, Melaka','Jabatan Alam Sekitar, Melaka');
                sel.options[9] = new Option('Tenaga Nasional Berhad (TNB)','Tenaga Nasional Berhad (TNB)');
                sel.options[10] = new Option('Syarikat Air Melaka Berhad','Syarikat Air Melaka Berhad');
                sel.options[11] = new Option('Jabatan Perkhidmatan Pembentungan, Melaka','Jabatan Perkhidmatan Pembentungan, Melaka');
                sel.options[12] = new Option('Majlis Bandaraya Melaka Bersejarah','Majlis Bandaraya Melaka Bersejarah');
                sel.options[13] = new Option('Majlis Perbandaran Jasin','Majlis Perbandaran Jasin');
                sel.options[14] = new Option('Majlis Perbandaran Alor Gajah','Majlis Perbandaran Alor Gajah');
                sel.options[15] = new Option('Perbadanan Hang Tuah Jaya', 'Perbadanan Hang Tuah Jaya');
                middlecell.appendChild(sel);

                var rightcell = row.insertCell(2);
                var el = document.createElement('textarea');
                el.name = 'ulasanTeknikal' + rowcount;
                el.rows = 3;
                el.cols = 80;
                el.style.textTransform = 'uppercase';
                rightcell.appendChild(el);
            } else{
                alert('Semua Jabatan Teknikal telah membuat ulasan.');
    <%--$("#huraianptd").focus();--%>
                return true;
            }

        }
    
        function showReport(){
    <%--window.open("${pageContext.request.contextPath}/pembangunan/melaka/kertasPertimbanganPTG?genReport", "eTanah",
    "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");--%>
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            var url = '${pageContext.request.contextPath}/pembangunan/melaka/kertasPertimbanganPTG_RayuanTolak?genReport';
            $.ajax({
                type:"GET",
                url : url,
                dataType : 'html',
                error : function(xhr, ajaxOptions, thrownError) {
                    alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
    <%--$("#folder_div").html('<div class=errors>Terdapat Masalah Teknikal. Sila Hubungi Pentadbir Sistem.</div><div id=t><a href="#">[ Lihat Terperinci ]</a></div><br/><div id=error class=b>' + xhr.responseText + '</div>');--%>
                    $.unblockUI();
                },
                success : function(data) {
                    if(data.indexOf('Laporan telah dijana')==0){
                        alert(data);
                        $('#folder').click();
                    }else {
                        alert(data);
                        $('#urusan').click();
                    }
                    $.unblockUI();
                }
            });
        }

        function myconfirm(ele)
        {
            if(confirm('Adakah anda pasti untuk perakuan '+ele.value+" ?"))
                return true;
            return false;
        }

        function validation(event, f){
            $('#dialog-confirm').dialog('open')
            $(function() {
                $( "#dialog-confirm" ).dialog({
                    resizable: false,
                    height:140,
                    modal: true,
                    buttons: {
                        "Tidak": function() {
                            //  alert('tidak selected');
                            $( this ).dialog( "close" );

                            return false;
                        },
                        "Ya": function() {
                            // alert('ya selected');
                            $( this ).dialog( "close" );

                            var q = $(f).formSerialize();
                            var url = f.action + '?' + event;
                            $.post(url,q,
                            function(data){
                                $('#page_div',this.document).html(data);
                            },'html');

                            return true;
                        }
                    }
                });
            });

        }

</script>

<s:form beanclass="etanah.view.stripes.pembangunan.KertasPertimbanganPTGMelakaRayuanActionBean">
    <s:messages/>
    <s:errors/>
    <%--<s:text name="kertasK.kertas.idKertas"/>--%>
    <div id="dialog-confirm" title="Kertas JKBB" style="display:none">
        <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
            <font size="3"> Adakah anda pasti untuk menjana Kertas JKBB baru?</font>
        </p>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>

            <c:set scope="request" value="${actionBean.uniquePemohonList2}" var="senaraiPihak"/>
            <c:set scope="request" value="${actionBean.hakmilikPermohonanList}" var="senaraiHakmilik"/>
            <c:set scope="request" value="${actionBean.listPengarah}" var="senaraiPengarah"/>

            <div class="content" align="center">
                <table border="0" width="80%">
                    <tr><td align="center"><b>MESYUARAT JAWATANKUASA PECAH SEMPADAN DAN BELAH BAHAGI TANAH NEGERI MELAKA</b></td></tr>                   
                </table><br><br>
                <table border="0" width="80%" cellspacing="5">
                    <tr>
                        <c:if test="${edit}">                        
                            <td>
                                <table width="100%" border="0">
                                    <tr>                                     
                                        <td width="75%">&nbsp;&nbsp;&nbsp; </td>
                                        <td><b>KERTAS BIL. </b><s:text name="kertasBil" size="20" class="normal_text" maxlength="14"/></td>
                                    </tr>
                                </table>
                            </td>
                        </c:if>


                        <c:if test="${!edit}">
                            <td>
                                <table width="100%" border="0">
                                    <tr>
                                        <td width="75%">&nbsp;&nbsp;&nbsp; </td>                                      
                                        <td>  <b>KERTAS BIL. </b>${actionBean.kertasBil}</td>
                                    </tr>
                                </table>
                            </td>
                        </c:if>
                    </tr>
                    <tr><td>
                            <table border="0" width="50%">
                                <tr>
                                    <c:if test="${edit}">
                                        <td><b id="idLabel">PERSIDANGAN</b></td>  
                                        <td><b id="idLabel"> : </b></td>
                                        <td><s:text name="persidangan" size="20"/></td>
                                    </c:if>
                                    <c:if test="${!edit}">
                                        <td><b id="idLabel">PERSIDANGAN</b></td>   
                                        <td><b id="idLabel"> : </b></td>                                            
                                        <td id="idDisplay">${actionBean.persidangan} &nbsp;</td>
                                    </c:if>
                                </tr>
                                <tr>
                                    <c:if test="${edit}">
                                        <td id="idLabel"><b>MASA</b></td>
                                        <td><b id="idLabel"> : </b></td>
                                        <td>
                                            <s:select name="jam" style="width:60px;" id="jam">
                                                <s:option value="0">Jam</s:option>
                                                <c:forEach var="i" begin="1" end="12" step="1" varStatus ="status">
                                                    <c:if test="${i lt 10}">
                                                        <s:option value="0${i}">0${i}</s:option>
                                                    </c:if>
                                                    <c:if test="${i gt 9}">
                                                        <s:option value="${i}">${i}</s:option>
                                                    </c:if>
                                                </c:forEach>
                                            </s:select>
                                            <s:select name="minit" style="width:70px;" id="minit">
                                                <s:option value="0">Minit</s:option>
                                                <c:forEach var="i" begin="0" end="59" step="5" varStatus ="status">
                                                    <c:if test="${i lt 10}">
                                                        <s:option value="0${i}">0${i}</s:option>
                                                    </c:if>
                                                    <c:if test="${i gt 9}">
                                                        <s:option value="${i}">${i}</s:option>
                                                    </c:if>
                                                </c:forEach>
                                            </s:select>
                                            <s:select name="pagiPetang" style="width:80px;" id="masa">
                                                <s:option value="0">Pilih</s:option>
                                                <s:option value="AM">Pagi</s:option>
                                                <s:option value="PM">Petang</s:option>
                                            </s:select>
                                        </td>
                                    </c:if>
                                    <c:if test="${!edit}">
                                        <td id="idLabel"><b>MASA</b></td>
                                        <td><b id="idLabel"> : </b></td>                                        
                                        <td id="idDisplay">${actionBean.masasidang} &nbsp;</td>
                                    </c:if>
                                </tr>
                                <tr>
                                    <c:if test="${edit}">
                                        <td id="idLabel"><b>TARIKH</b></td>
                                        <td><b id="idLabel"> : </b></td>
                                        <td><s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" size="12" /></td>
                                    </c:if>
                                    <c:if test="${!edit}">
                                        <td id="idLabel"><b>TARIKH</b></td>
                                        <td><b id="idLabel"> : </b></td>                                        
                                        <td id="idDisplay">${actionBean.tarikhMesyuarat} &nbsp;</td>
                                    </c:if>
                                </tr>
                                <tr>
                                    <c:if test="${edit}">
                                        <td id="idLabel"><b>TEMPAT</b></td>
                                        <td><b id="idLabel"> : </b></td>
                                        <td><s:textarea name="tempatsidang" cols="50"/></td>
                                    </c:if>
                                    <c:if test="${!edit}">
                                        <td id="idLabel"><b>TEMPAT</b></td>
                                        <td><b id="idLabel"> : </b></td>                                       
                                        <td id="idDisplay">${actionBean.tempatsidang} &nbsp;</td>
                                    </c:if>
                                </tr>
                            </table>
                        </td></tr>
                    <tr><td><b>TAJUK : </b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><s:textarea name="tajuk" rows="5" cols="150" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>${actionBean.tajuk} &nbsp;</td></tr>
                    </c:if>


                    <tr><td><b>1. PERIHAL PEMOHON</b></td></tr>
                    <tr><td>
                            <table border="0" width="60%">
                                <tr>
                                    <td  id="tdLabel"><font color="black">Pemohon :</font></td>
                                    <td  id="tdDisplay">
                                        <table border="0" width="80%">
                                            <c:set value="1" var="i"/>
                                            <c:forEach items="${senaraiPihak}" var="pihak" end="0">
                                                <tr><td><font size="-1">                                                                
                                                        <c:out value="${pihak.nama}"/> 
                                                        <c:if test="${fn:length(senaraiPihak) > 1}">
                                                            dan ${fn:length(senaraiPihak)-1} yang lain
                                                        </c:if>                                                               
                                                        </font></td>
                                                </tr>
                                                <c:set value="${i + 1}" var="i"/>
                                            </c:forEach>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td  id="tdLabel"><font color="black">Alamat :</font></td>
                                    <td  id="tdDisplay">
                                        <table border="0" width="80%">
                                            <c:set value="1" var="i"/>
                                            <c:forEach items="${senaraiPihak}" var="pihak" end="0" >
                                                <tr><td><font size="-1">                                                            
                                                        <c:out value="${pihak.alamat1}"/>&nbsp;
                                                        </font></td>
                                                </tr>
                                                <c:if test="${pemohon.pihak.suratAlamat2 ne null}">
                                                    <tr><td><font size="-1">&nbsp; &nbsp;<c:out value="${pihak.alamat2}"/>&nbsp;</font></td></tr>
                                                        </c:if>
                                                        <c:if test="${pemohon.pihak.suratAlamat3 ne null}">
                                                    <tr><td><font size="-1">&nbsp; &nbsp;<c:out value="${pihak.alamat3}"/>&nbsp;</font></td></tr>
                                                        </c:if>
                                                        <c:if test="${pemohon.pihak.suratAlamat4 ne null}">
                                                    <tr><td><font size="-1">&nbsp; &nbsp;<c:out value="${pihak.alamat4}"/>&nbsp;</font></td></tr>
                                                        </c:if>
                                                <tr><td><font size="-1">&nbsp; &nbsp;<c:out value="${pihak.poskod}"/>&nbsp;</font></td></tr>
                                                <tr><td><font size="-1">&nbsp; &nbsp;<c:out value="${pihak.negeri.nama}"/>&nbsp;</font></td></tr>
                                                        <c:set value="${i + 1}" var="i"/>
                                                    </c:forEach>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td  id="tdLabel"><font color="black">Tarikh Permohonan :</font></td>
                                    <td  id="tdDisplay">${actionBean.tarikhPermohonan}&nbsp;</td>
                                </tr>
                                <tr>
                                    <td id="tdLabel"><font color="black">Ahli Lembaga Pengarah :</font></td>
                                    <td  id="tdDisplay">
                                        <table border="0" width="80%">
                                            <c:set value="1" var="count"/>
                                            <c:forEach items="${senaraiPihak}" var="pihak" >
                                                <c:forEach items="${pihak.senaraiPengarah}" var="pengarah" >
                                                    <tr><td><font size="-1">

                                                            <c:out value="${pengarah.nama}"/>
                                                            </font></td>
                                                    </tr>
                                                    <c:set value="${count + 1}" var="count"/>
                                                </c:forEach>
                                            </c:forEach>    
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td></tr>

                    <tr><td><b>2. TUJUAN PERMOHONAN</b></td></tr>
                    <%--
                    <c:if test="${edit}">                      
                        <tr><td><s:textarea rows="5" cols="150" name="tujuan" class="normal_text"/></td></tr>                       
                    </c:if>
                    <c:if test="${!edit}">                      
                        <tr><td>${actionBean.tujuan} &nbsp;</td></tr>                        
                    </c:if>
                    --%>


                    <tr><td>
                            <display:table cellpadding="0" cellspacing="0" class="tablecloth" name="${actionBean.mppList}" id="line1">
                                <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line1_rowNum}</display:column>
                                <display:column title="Butir-butir Pembangunan" style="vertical-align:baseline">
                                    <c:if test="${line1.pemilikan.kod == 'H'}">
                                        ${line1.kategoriTanah.nama}
                                    </c:if>
                                    <c:if test="${line1.pemilikan.kod == 'R'}">
                                        -
                                    </c:if>
                                    <c:if test="${line1.pemilikan.kod == 'K'}">
                                        ${line1.kegunaanTanahLain}
                                    </c:if>
                                </display:column>
                                <display:column title="Bil Unit" style="vertical-align:baseline">                                
                                    <c:if test="${line1.pemilikan.kod == 'H'}">
                                        ${line1.bilanganPlot}
                                    </c:if>
                                    <c:if test="${line1.pemilikan.kod == 'R'}">
                                        -
                                    </c:if>
                                    <c:if test="${line1.pemilikan.kod == 'K'}">
                                        -
                                    </c:if>
                                </display:column>
                                <display:column title="Keluasan Lot" style="vertical-align:baseline">${line1.noPlot} - <fmt:formatNumber pattern="#,##0.0000" value="${line1.luas}"/>&nbsp;${line1.kodUnitLuas.nama}</display:column>
                            </display:table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table width="45%" cellpadding="1" border="0">
                                <tr>
                                    <td width="5%">&nbsp;&nbsp; </td>
                                    <td width="35%">&nbsp;<b>Jumlah</b> </td>
                                    <td width="10%">&nbsp;<b>${actionBean.bilUnitTotal}</b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td width="39%">&nbsp;<b><fmt:formatNumber pattern="#,##0.0000" value="${actionBean.keluasanLotTotal}"/> &nbsp;${actionBean.kodUnitLuas}</b> </td>     
                                </tr>    
                            </table>                            
                        </td>
                        <td> <br><br></td>
                    </tr>


                    <tr><td><b>3. LOKASI TANAH</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><s:textarea rows="5" cols="150" name="lokasiTanah" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>${actionBean.lokasiTanah} &nbsp;</td></tr>
                    </c:if>


                    <tr><td><b>4. BUTIR-BUTIR HAKMILIK </b></td></tr>
                    <tr><td>
                            <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="tbl1">
                                <display:column title="Bil" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                                <display:column title="No. Lot/PT" style="vertical-align:baseline">
                                    ${tbl1.hakmilik.lot.nama} <fmt:formatNumber  pattern="00" value="${tbl1.hakmilik.noLot}"/> 
                                </display:column>
                                <display:column title="Jenis & No Hakmilik" style="vertical-align:baseline">
                                    ${tbl1.hakmilik.kodHakmilik.kod} <fmt:formatNumber  pattern="00" value="${tbl1.hakmilik.noHakmilik}"/>
                                </display:column>
                                <display:column title="Tempoh Pegangan" property="hakmilik.tempohPegangan" style="vertical-align:baseline"/>
                                <display:column title="Taraf Pegangan" property="hakmilik.pegangan" style="vertical-align:baseline"/>
                                <c:if test="${tbl1.hakmilik.pegangan eq 'P'}">                                    
                                    <display:column title="Tarikh Luput Pajakan" style="vertical-align:baseline">
                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${tabl1.hakmilik.tarikhLuput}"/>
                                    </display:column>    
                                </c:if>
                                <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="vertical-align:baseline"/>
                                <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${tbl1.hakmilik.luas}"/>&nbsp;${tbl1.hakmilik.kodUnitLuas.nama}</display:column>
                                <display:column title="Tuan Tanah" style="vertical-align:baseline">
                                    <display:table class="tablecloth" name="${tbl1.hakmilik.senaraiPihakBerkepentingan}" cellpadding="0" cellspacing="0" id="senarai">

                                        <display:column title="Nama" style="vertical-align:baseline">
                                            <c:if test="${senarai.jenis.kod eq 'PM'}">
                                                <c:out value="${senarai.pihak.nama}"/>
                                            </c:if>
                                        </display:column>
                                        <display:column title="Bahagian Dimiliki" style="vertical-align:baseline">
                                            <c:if test="${senarai.jenis.kod eq 'PM'}">
                                                ${senarai.syerPembilang}/${senarai.syerPenyebut}
                                            </c:if>
                                        </display:column>
                                        <display:column title="No. KP/Syarikat" style="vertical-align:baseline">
                                            <c:if test="${senarai.jenis.kod eq 'PM'}">
                                                ${senarai.pihak.noPengenalan}
                                            </c:if>
                                        </display:column>
                                        <%--</c:forEach>--%>
                                    </display:table>            
                                </display:column>
                                <display:column title="Jenis Kegunaan Tanah" property="hakmilik.kategoriTanah.nama" style="vertical-align:baseline"/>
                                <display:column title="Syarat Nyata" property="hakmilik.syaratNyata.syarat" style="vertical-align:baseline"/>
                                <display:column title="Sekatan Kepentingan" property="hakmilik.sekatanKepentingan.sekatan" style="vertical-align:baseline"/>

                            </display:table>
                        </td></tr>

                    <c:if test="${fn:length(actionBean.hakUrusan) ne 0}">
                        <tr><td><b>Bebanan Berdaftar</b></td></tr>
                        <c:if test="${edit}">
                            <tr><td><s:textarea rows="10" cols="150" name="bebananBerdafter" class="normal_text"/></td></tr>
                        </c:if>
                        <c:if test="${!edit}">
                            <tr><td>${actionBean.bebananBerdafter} &nbsp;</td></tr>
                        </c:if>
                        <tr><td><b>Surat Persetujuan daripada Pihak-Pihak Yang Berkepentingan</b></td></tr>
                        <c:if test="${edit}">
                            <tr><td><s:textarea rows="5" cols="150" name="suratPersetujuan" class="normal_text"/></td></tr>
                        </c:if>
                        <c:if test="${!edit}">
                            <tr><td>${actionBean.suratPersetujuan} &nbsp;</td></tr>
                        </c:if>
                    </c:if>     


                    <tr><td><b>5. KEADAAN TANAH</b></td></tr>
                    <tr><td>
                            <table border="0" width="75%" cellspacing="5">
                                <tr>
                                    <td id="tdLabel"><font color="black">Keadaan Tanah :</font></td>
                                    <td id="tdDisplay">                                        
                                        <c:if test="${fn:length(actionBean.findListlaporCerun) == 0}">
                                            Tiada
                                        </c:if>
                                        <c:forEach items="${actionBean.findListlaporCerun}" var="line">
                                            ${line.kodCerunanTanah.nama},
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr>
                                    <td><br></td>
                                </tr>
                                <tr>
                                    <td id="tdLabel"><font color="black">Jenis Tanaman :</font></td>
                                    <td id="tdDisplay">                                        
                                        <c:if test="${fn:length(actionBean.permohonanLaporanTanamanList) == 0}">
                                            Tiada
                                        </c:if>
                                        <display:table class="tablecloth" name="${actionBean.permohonanLaporanTanamanList}" pagesize="10" cellpadding="0" cellspacing="0"
                                                       requestURI="/pembangunan/melaka/kertasPertimbanganPTG"  id="line">
                                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                            <display:column property="namaPemunya" title="Pemilik" />
                                            <display:column property="namaPenyewa" title="Penyewa" />
                                            <display:column property="namaKetua" title="Jenis Tanaman" />
                                            <display:column title="Keadaan Tanaman">
                                                <c:if test="${line.jenisBangunan eq 'KK'}">Kekal</c:if>
                                                <c:if test="${line.jenisBangunan eq 'SM'}">Sementara</c:if>
                                            </display:column>
                                            <display:column property="keteranganTahunBinaan" title="Tahun Dibina" />
                                            <display:column  title="Kadar Sewa" >RM <fmt:formatNumber pattern="#,##0.00" value="${line.nilai}"/></display:column>
                                        </display:table>
                                    </td>
                                </tr>
                                <tr>
                                    <td><br></td>
                                </tr>
                                <tr>
                                    <td id="tdLabel"><font color="black">Bilangan Bangunan di atas lot ini :</font></td>
                                    <td id="tdDisplay">                                        
                                        <c:if test="${fn:length(actionBean.permohonanLaporanBangunanList) == 0}">
                                            Tiada
                                        </c:if>
                                        <display:table class="tablecloth" name="${actionBean.permohonanLaporanBangunanList}" pagesize="10" cellpadding="0" cellspacing="0"
                                                       requestURI="/pembangunan/melaka/kertasPertimbanganPTG"  id="line">
                                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                            <display:column property="namaPemunya" title="Pemilik" />
                                            <display:column property="namaPenyewa" title="Penyewa" />
                                            <display:column property="namaKetua" title="Kegunaan Bangunan" />
                                            <display:column title="Keadaan Bangunan">
                                                <c:if test="${line.jenisBangunan eq 'KK'}">Kekal</c:if>
                                                <c:if test="${line.jenisBangunan eq 'SK'}">Separuh Kekal</c:if>
                                                <c:if test="${line.jenisBangunan eq 'SM'}">Sementara</c:if>
                                            </display:column>
                                            <display:column property="keteranganTahunBinaan" title="Tahun Dibina" />
                                            <display:column title="Kadar Sewa"><c:if test="${line.nilai ne ''}">RM <fmt:formatNumber pattern="#,##0.00" value="${line.nilai}"/></c:if></display:column>
                                        </display:table>
                                    </td>
                                </tr>
                                <tr>
                                    <td><br></td>
                                </tr>
                                <tr>
                                    <td id="tdLabel"><font color="black">Perihal lot-lot bersempadan : </font></td>
                                    <td>
                                        <table class="tablecloth">
                                            <tr>
                                                <th>&nbsp;</th><th>Jenis Tanah</th><th>Nombor Lot</th><th>Kategori</th><th>Catatan</th>
                                            </tr>
                                            <tr>
                                                <th>
                                                    Utara
                                                </th>
                                                <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraMilikKerajaan eq 'T'}">Milik</c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot ne null}"> ${actionBean.laporanTanah.sempadanUtaraNoLot}&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot eq null}"> Tiada Data </c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraJenis eq '1'}">Industri&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraJenis eq '2'}">Bangunan&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraJenis eq '3'}">Pertanian&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraJenis eq '4'}">Rizab&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraJenis eq null}"> Tiada Data </c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraKegunaan ne null}"> ${actionBean.laporanTanah.sempadanUtaraKegunaan}&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraKegunaan eq null}"> Tiada Data </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    Selatan
                                                </th>
                                                <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanSelatanMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanSelatanMilikKerajaan eq 'T'}">Milik</c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanSelatanNoLot ne null}"> ${actionBean.laporanTanah.sempadanSelatanNoLot}&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanSelatanNoLot eq null}"> Tiada Data </c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanSelatanJenis eq '1'}">Industri&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanSelatanJenis eq '2'}">Bangunan&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanSelatanJenis eq '3'}">Pertanian&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanSelatanJenis eq '4'}">Rizab&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanSelatanJenis eq null}"> Tiada Data </c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanSelatanKegunaan ne null}"> ${actionBean.laporanTanah.sempadanSelatanKegunaan}&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanSelatanKegunaan eq null}"> Tiada Data </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    Timur
                                                </th>
                                                <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurMilikKerajaan eq 'T'}">Milik</c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurNoLot ne null}"> ${actionBean.laporanTanah.sempadanTimurNoLot}&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurNoLot eq null}"> Tiada Data </c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurJenis eq '1'}">Industri&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurJenis eq '2'}">Bangunan&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurJenis eq '3'}">Pertanian&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurJenis eq '4'}">Rizab&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurJenis eq null}"> Tiada Data </c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurKegunaan ne null}"> ${actionBean.laporanTanah.sempadanTimurKegunaan}&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurKegunaan eq null}"> Tiada Data </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    Barat
                                                </th>
                                                <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratMilikKerajaan eq 'T'}">Milik</c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratNoLot ne null}"> ${actionBean.laporanTanah.sempadanBaratNoLot}&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratNoLot eq null}"> Tiada Data </c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratJenis eq '1'}">Industri&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratJenis eq '2'}">Bangunan&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratJenis eq '3'}">Pertanian&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratJenis eq '4'}">Rizab&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratJenis eq null}"> Tiada Data </c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratKegunaan ne null}"> ${actionBean.laporanTanah.sempadanBaratKegunaan}&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratKegunaan eq null}"> Tiada Data </c:if>
                                                </td>
                                            </tr>
                                        </table>
                                    </td></tr>

                                <c:if test="${edit}">
                                    <tr>
                                        <td id="tdLabel" valign="top"><font color="black">Maklumat Lain :</font></td>
                                        <td><s:textarea name="maklumat" rows="10" cols="50" class="normal_text"/></td>
                                    </tr>
                                </c:if>
                                <c:if test="${!edit}">
                                    <tr>
                                        <td id="tdLabel"><font color="black">Maklumat Lain :</font></td>
                                        <td id="tdDisplay">${actionBean.maklumat} &nbsp;</td>
                                    </tr>
                                </c:if>

                            </table>
                        </td></tr>

                    <tr><td><b>6. ULASAN YB. ADUN KAWASAN </b></td></tr>                       
                    <tr><td>                              
                            <display:table name="${actionBean.ulasanAdun}" id="line1" class="tablecloth" size="100">
                                <display:column title="Bil" sortable="true">${line1_rowNum}</display:column>
                                <display:column title="Adun" style="width:400px" property="agensi.nama"></display:column>
                                <display:column title="Syor" style="width:150px">
                                    <c:choose>
                                        <c:when test="${line1.diSokong eq '1'}">
                                            Boleh Diluluskan                                                
                                        </c:when>
                                        <c:when test="${line1.diSokong eq '2'}">
                                            Tidak Diluluskan                                                
                                        </c:when>
                                        <c:when test="${line1.diSokong eq '3'}">
                                            Tiada Halangan                                                
                                        </c:when>
                                        <c:when test="${line1.diSokong eq '4'}">
                                            Sesuai                                               
                                        </c:when>
                                        <c:when test="${line1.diSokong eq '5'}">
                                            Tidak Sesuai                                               
                                        </c:when>
                                        <c:when test="${line1.diSokong eq '6'}">
                                            Disokong                                              
                                        </c:when>
                                        <c:when test="${line1.diSokong eq '7'}">
                                            Tidak Disokong                                                 
                                        </c:when>
                                        <c:when test="${line1.diSokong eq '8'}">
                                            Tiada Ulasan                                             
                                        </c:when>
                                        <c:when test="${line1.diSokong eq '9'}">
                                            Sokong Bersyarat                                           
                                        </c:when>
                                        <c:when test="${line1.diSokong eq 'A'}">
                                            Tangguh                                           
                                        </c:when>
                                        <c:when test="${line1.diSokong eq 'B'}">
                                            Tiada Pengesyoran                                        
                                        </c:when>
                                        <c:otherwise>
                                            Tiada Ulasan
                                        </c:otherwise> 
                                    </c:choose>
                                </display:column>
                                <display:column title="Ulasan" style="width:600px" >${fn:replace(line1.ulasan,newline,"<br/>")}</display:column>
                            </display:table>

                        </td></tr>

                    <tr><td><b>7. ULASAN JABATAN-JABATAN TEKNIKAL</b></td></tr>
                    <tr><td>                            
                            <display:table name="${actionBean.ulasanList}" id="line1" class="tablecloth" size="100">
                                <display:column title="Bil" sortable="true">${line1_rowNum}</display:column>
                                <display:column title="Jabatan" style="width:400px" property="agensi.nama"></display:column>
                                 <display:column title="Syor" style="width:150px">
                                    <c:choose>
                                        <c:when test="${line1.diSokong eq '1'}">
                                            Boleh Diluluskan                                                
                                        </c:when>
                                        <c:when test="${line1.diSokong eq '2'}">
                                            Tidak Diluluskan                                                
                                        </c:when>
                                        <c:when test="${line1.diSokong eq '3'}">
                                            Tiada Halangan                                                
                                        </c:when>
                                        <c:when test="${line1.diSokong eq '4'}">
                                            Sesuai                                               
                                        </c:when>
                                        <c:when test="${line1.diSokong eq '5'}">
                                            Tidak Sesuai                                               
                                        </c:when>
                                        <c:when test="${line1.diSokong eq '6'}">
                                            Disokong                                              
                                        </c:when>
                                        <c:when test="${line1.diSokong eq '7'}">
                                            Tidak Disokong                                                 
                                        </c:when>
                                        <c:when test="${line1.diSokong eq '8'}">
                                            Tiada Ulasan                                             
                                        </c:when>
                                        <c:when test="${line1.diSokong eq '9'}">
                                            Sokong Bersyarat                                           
                                        </c:when>
                                        <c:when test="${line1.diSokong eq 'A'}">
                                            Tangguh                                           
                                        </c:when>
                                        <c:when test="${line1.diSokong eq 'B'}">
                                            Tiada Pengesyoran                                        
                                        </c:when>
                                        <c:otherwise>
                                            Tiada Ulasan
                                        </c:otherwise> 
                                    </c:choose>
                                </display:column>
                                <display:column title="Ulasan" style="width:600px" >${fn:replace(line1.ulasan,newline,"<br/>")}</display:column>
                            </display:table>

                        </td></tr>


                    <tr><td><b><font style="text-transform:uppercase">8. PERAKUAN PENTADBIR TANAH ${actionBean.pejTanah}&nbsp;</font></b></td></tr>                                  
                    <tr><td>${actionBean.perakuan}.</td></tr>

                </table>

                <br>
                <c:if test="${edit}">
                    <p align="center">
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </c:if>

            </div>
        </fieldset>
    </div>
</s:form>