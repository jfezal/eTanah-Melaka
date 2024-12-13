<%--
    Document   : kertas_pertimbangan_PTG
    Created on : Jun 29, 2010, 9:47:53 AM
    Author     : NageswaraRao
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
    function showReport() {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });
        var url = '${pageContext.request.contextPath}/pembangunan/melaka/kertasPertimbanganPTG?genReport';
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                $.unblockUI();
            },
            success: function(data) {
                if (data.indexOf('Laporan telah dijana') == 0) {
                    alert(data);
                    $('#folder').click();
                } else {
                    alert(data);
                    $('#urusan').click();
                }
                $.unblockUI();
            }
        });
    }
    function addRow(tableid) {
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;

        if (rowcount < 9) {
            var row = table.insertRow(rowcount);

            var leftcell = row.insertCell(0);
            var bil = document.createTextNode(rowcount);
            leftcell.appendChild(bil);

            var middlecell = row.insertCell(1);
            var sel = document.createElement('select');
            sel.name = 'jabatan' + rowcount;
            sel.style.width = '400px';
            sel.options[0] = new Option('--Sila Pilih--', '');
            sel.options[1] = new Option('Jabatan Kebajikan Masyarakat, Melaka', 'Jabatan Kebajikan Masyarakat');
            sel.options[2] = new Option('Jabatan Kerja Raya, Melaka', 'Jabatan Kerja Raya, Melaka');
            sel.options[3] = new Option('Jabatan Pengairan dan Saliran, Melaka', 'Jabatan Pengairan dan Saliran, Melaka');
            sel.options[4] = new Option('Jabatan Perancang Bandar dan Desa, Melaka', 'Jabatan Perancang Bandar dan Desa, Melaka');
            sel.options[5] = new Option('Jabatan Tenaga Kerja, Melaka', 'Jabatan Tenaga Kerja, Melaka');
            sel.options[6] = new Option('Jabatan Kesihatan, Melaka', 'Jabatan Kesihatan, Melaka');
            sel.options[7] = new Option('Jabatan Pertanian, Melaka', 'Jabatan Pertanian, Melaka');
            sel.options[8] = new Option('Jabatan Alam Sekitar, Melaka', 'Jabatan Alam Sekitar, Melaka');
            sel.options[9] = new Option('Tenaga Nasional Berhad (TNB)', 'Tenaga Nasional Berhad (TNB)');
            sel.options[10] = new Option('Syarikat Air Melaka Berhad', 'Syarikat Air Melaka Berhad');
            sel.options[11] = new Option('Jabatan Perkhidmatan Pembentungan, Melaka', 'Jabatan Perkhidmatan Pembentungan, Melaka');
            sel.options[12] = new Option('Majlis Bandaraya Melaka Bersejarah', 'Majlis Bandaraya Melaka Bersejarah');
            sel.options[13] = new Option('Majlis Perbandaran Jasin', 'Majlis Perbandaran Jasin');
            sel.options[14] = new Option('Majlis Perbandaran Alor Gajah', 'Majlis Perbandaran Alor Gajah');
            sel.options[15] = new Option('Perbadanan Hang Tuah Jaya', 'Perbadanan Hang Tuah Jaya');
            middlecell.appendChild(sel);

            var rightcell = row.insertCell(2);
            var el = document.createElement('textarea');
            el.name = 'ulasanTeknikal' + rowcount;
            el.rows = 3;
            el.cols = 80;
            el.style.textTransform = 'uppercase';
            rightcell.appendChild(el);
        } else {
            alert('Semua Jabatan Teknikal telah membuat ulasan.');
    <%--$("#huraianptd").focus();--%>
            return true;
        }

    }

    function showReport() {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });
        var url = '${pageContext.request.contextPath}/pembangunan/melaka/kertasPertimbanganPTG?genReport';
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                $.unblockUI();
            },
            success: function(data) {
                if (data.indexOf('Laporan telah dijana') == 0) {
                    alert(data);
                    $('#folder').click();
                } else {
                    alert(data);
                    $('#urusan').click();
                }
                $.unblockUI();
            }
        });
    }

    function myconfirm(ele)
    {
        if (confirm('Adakah anda pasti untuk perakuan ' + ele.value + " ?"))
            return true;
        return false;
    }

    function validation(event, f) {
        $('#dialog-confirm').dialog('open')
        $(function() {
            $("#dialog-confirm").dialog({
                resizable: false,
                height: 140,
                modal: true,
                buttons: {
                    "Tidak": function() {
                        $(this).dialog("close");

                        return false;
                    },
                    "Ya": function() {
                        $(this).dialog("close");

                        var q = $(f).formSerialize();
                        var url = f.action + '?' + event;
                        $.post(url, q,
                                function(data) {
                                    $('#page_div', this.document).html(data);
                                }, 'html');

                        return true;
                    }
                }
            });
        });

    }

</script>

<s:form beanclass="etanah.view.stripes.pembangunan.KertasPertimbanganPTGMelakaActionBean">
    <s:messages/>
    <s:errors/>

    <div id="dialog-confirm" title="Kertas JKBB" style="display:none">
        <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
            <font size="3"> Adakah anda pasti untuk menjana Kertas JKBB baru?</font>
        </p>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>

            <c:set scope="request" value="${actionBean.uniquePemohonList2}" var="senaraiPihak"/>
            <c:set scope="request" value="${actionBean.selectedPemohon}" var="selectedPemohon"/>
            <c:set scope="request" value="${actionBean.hakmilikPermohonanList}" var="senaraiHakmilik"/>
            <c:set scope="request" value="${actionBean.listPengarah}" var="senaraiPengarah"/>

            <div class="content" align="center">
                <table border="0" width="80%">
                    <tr><td align="center"><b>MESYUARAT JAWATANKUASA PECAH SEMPADAN DAN BELAH BAHAGI TANAH NEGERI MELAKA</b></td></tr>
                </table><br><br>
                <table border="0" width="80%" cellspacing="5">
                    <tr>
                        <c:if test="${actionBean.ptg && actionBean.stageId ne 'rekodkedjkbbtangguh'}">                            
                            <td>
                                <table width="100%" border="0">
                                    <tr>                                     
                                        <td width="75%">&nbsp;&nbsp;&nbsp; </td>
                                        <td><b>KERTAS BIL. </b><s:text name="kertasBil" size="20" class="normal_text" maxlength="14"/></td>
                                    </tr>
                                </table>
                            </td>
                        </c:if>

                        <c:if test="${!edit && !actionBean.ptg || actionBean.stageId eq 'rekodkedjkbbtangguh'}">
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
                                    <c:if test="${actionBean.ptg && actionBean.stageId ne 'rekodkedjkbbtangguh'}">
                                        <td><b id="idLabel">PERSIDANGAN</b></td>  
                                        <td><b id="idLabel"> : </b></td>
                                        <td><s:text name="persidangan" size="20"/></td>
                                    </c:if>
                                    <c:if test="${!edit && !actionBean.ptg || actionBean.stageId eq 'rekodkedjkbbtangguh'}">
                                        <td><b id="idLabel">PERSIDANGAN</b></td>   
                                        <td><b id="idLabel"> : </b></td>                                            
                                        <td id="idDisplay">${actionBean.persidangan} &nbsp;</td>
                                    </c:if>
                                </tr>
                                <tr>
                                    <c:if test="${actionBean.ptg && actionBean.stageId ne 'rekodkedjkbbtangguh'}">
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
                                    <c:if test="${!edit && !actionBean.ptg || actionBean.stageId eq 'rekodkedjkbbtangguh'}">
                                        <td id="idLabel"><b>MASA</b></td>
                                        <td><b id="idLabel"> : </b></td>                                        
                                        <td id="idDisplay">${actionBean.masasidang} &nbsp;</td>
                                    </c:if>
                                </tr>
                                <tr>
                                    <c:if test="${actionBean.ptg && actionBean.stageId ne 'rekodkedjkbbtangguh'}">
                                        <td id="idLabel"><b>TARIKH</b></td>
                                        <td><b id="idLabel"> : </b></td>
                                        <td><s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" size="12" /></td>
                                    </c:if>
                                    <c:if test="${!edit && !actionBean.ptg || actionBean.stageId eq 'rekodkedjkbbtangguh'}">
                                        <td id="idLabel"><b>TARIKH</b></td>
                                        <td><b id="idLabel"> : </b></td>                                        
                                        <td id="idDisplay">${actionBean.tarikhMesyuarat} &nbsp;</td>
                                    </c:if>
                                </tr>
                                <tr>
                                    <c:if test="${actionBean.ptg && actionBean.stageId ne 'rekodkedjkbbtangguh'}">
                                        <td id="idLabel"><b>TEMPAT</b></td>
                                        <td><b id="idLabel"> : </b></td>
                                        <td><s:textarea name="tempatsidang" cols="50"/></td>
                                    </c:if>
                                    <c:if test="${!edit && !actionBean.ptg || actionBean.stageId eq 'rekodkedjkbbtangguh'}">
                                        <td id="idLabel"><b>TEMPAT</b></td>
                                        <td><b id="idLabel"> : </b></td>                                       
                                        <td id="idDisplay">${actionBean.tempatsidang} &nbsp;</td>
                                    </c:if>
                                </tr>
                            </table>
                        </td></tr>
                    <tr><td><b>TAJUK : </b></td></tr>
                    <c:if test="${edit && !actionBean.ptg}">
                        <tr><td><s:textarea name="tajuk" rows="5" cols="150" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>${actionBean.tajuk} &nbsp;</td></tr>
                    </c:if>
                    <c:if test="${edit && actionBean.ptg}">                            
                        <tr><td><s:textarea name="tajuk" rows="5" cols="150" class="normal_text"/></td></tr>
                    </c:if>                        
                    <tr><td><b>1. PERIHAL PEMOHON</b></td></tr>
                    <tr><td>
                            <table border="0" width="60%">
                                <tr>
                                    <td  id="tdLabel"><font color="black">Pemohon :</font></td>
                                    <td  id="tdDisplay">
                                        <table border="0" width="80%">
                                            <c:set value="1" var="i"/>
                                            <c:forEach items="${selectedPemohon}" var="pemohon" end="0">
                                                <tr><td><font size="-1">                                                                
                                                        <c:out value="${pemohon.nama}"/> 

                                                        </font></td>
                                                </tr>
                                                <c:set value="${i + 1}" var="i"/>
                                            </c:forEach>                                                                                            
                                        </table>
                                        <c:if test="${fn:length(senaraiPihak) > 1}">
                                            dan ${fn:length(senaraiPihak)-1} yang lain
                                        </c:if>  
                                    </td>
                                </tr>
                                <tr>
                                    <td  id="tdLabel"><font color="black">Alamat :</font></td>
                                    <td  id="tdDisplay">
                                        <table border="0" width="80%">
                                            <c:set value="1" var="i"/>
                                            <c:forEach items="${selectedPemohon}" var="pemohon" end="0" >
                                                <c:if test="${pemohon.alamat.alamat1 ne null}">
                                                    <tr>
                                                        <td>
                                                            <font size="-1"><c:out value="${pemohon.alamat.alamat1}"/>&nbsp;</font>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                                <c:if test="${pemohon.alamat.alamat1 eq null}">
                                                    <tr>
                                                        <td>
                                                            <font size="-1"><c:out value="${pemohon.pihak.alamat1}"/>&nbsp;</font>
                                                        </td>
                                                    </tr>
                                                </c:if>

                                                <c:if test="${pemohon.alamat.alamat2 ne null}">
                                                    <tr>
                                                        <td>
                                                            <font size="-1"><c:out value="${pemohon.alamat.alamat2}"/>&nbsp;</font>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                                <c:if test="${pemohon.alamat.alamat2 eq null}">
                                                    <tr>
                                                        <td>
                                                            <font size="-1"><c:out value="${pemohon.pihak.alamat2}"/>&nbsp;</font>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                                <c:if test="${pemohon.alamat.alamat3 ne null}">
                                                    <tr>
                                                        <td>
                                                            <font size="-1"><c:out value="${pemohon.alamat.alamat3}"/>&nbsp;</font>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                                <c:if test="${pemohon.alamat.alamat3 eq null}">
                                                    <tr>
                                                        <td>
                                                            <font size="-1"><c:out value="${pemohon.pihak.alamat3}"/>&nbsp;</font>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                                <c:if test="${pemohon.alamat.alamat4 ne null}">
                                                    <tr>
                                                        <td>
                                                            <font size="-1"><c:out value="${pemohon.alamat.alamat4}"/>&nbsp;</font>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                                <c:if test="${pemohon.alamat.alamat4 eq null}">
                                                    <tr>
                                                        <td>
                                                            <font size="-1"><c:out value="${pemohon.pihak.alamat4}"/>&nbsp;</font>
                                                        </td>
                                                    </tr>
                                                </c:if>

                                                <c:if test="${pemohon.alamat.poskod ne null}">
                                                    <tr>
                                                        <td>
                                                            <font size="-1"><c:out value="${pemohon.alamat.poskod}"/></font>
                                                            <font size="-1"><c:out value="${pemohon.alamat.negeri.nama}"/>&nbsp;</font>
                                                        </td>
                                                    </c:if>
                                                    <c:if test="${pemohon.alamat.poskod eq null}">
                                                    <tr>
                                                        <td>
                                                            <font size="-1"><c:out value="${pemohon.pihak.poskod}"/></font>
                                                            <font size="-1"><c:out value="${pemohon.pihak.negeri.nama}"/>&nbsp;</font>
                                                        </td>
                                                    </c:if>


                                                </tr>
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
                    <c:if test="${edit && !actionBean.ptg}">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSKKT' || actionBean.permohonan.kodUrusan.kod eq 'TSBSN' || actionBean.permohonan.kodUrusan.kod eq 'TSPSN' || actionBean.permohonan.kodUrusan.kod eq 'TSKSN' || actionBean.permohonan.kodUrusan.kod eq 'PPT' || actionBean.permohonan.kodUrusan.kod eq 'PPK' || actionBean.permohonan.kodUrusan.kod eq 'PSMT'}">
                            <tr><td><s:textarea rows="5" cols="150" name="tujuan" class="normal_text"/></td></tr>
                        </c:if>
                    </c:if>
                    <c:if test="${edit && actionBean.ptg}">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSKKT' || actionBean.permohonan.kodUrusan.kod eq 'TSBSN' || actionBean.permohonan.kodUrusan.kod eq 'TSPSN' || actionBean.permohonan.kodUrusan.kod eq 'TSKSN' || actionBean.permohonan.kodUrusan.kod eq 'PPT' || actionBean.permohonan.kodUrusan.kod eq 'PPK' || actionBean.permohonan.kodUrusan.kod eq 'PSMT'}">
                            <tr><td><s:textarea rows="5" cols="150" name="tujuan" class="normal_text"/></td></tr>
                        </c:if>
                    </c:if>
                    <c:if test="${!edit}">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSKKT' || actionBean.permohonan.kodUrusan.kod eq 'TSBSN' || actionBean.permohonan.kodUrusan.kod eq 'TSPSN' || actionBean.permohonan.kodUrusan.kod eq 'TSKSN' || actionBean.permohonan.kodUrusan.kod eq 'PPT' || actionBean.permohonan.kodUrusan.kod eq 'PPK' || actionBean.permohonan.kodUrusan.kod eq 'PSMT'}">
                            <tr><td>${actionBean.tujuan} &nbsp;</td></tr>
                        </c:if>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPCS' || actionBean.permohonan.kodUrusan.kod eq 'PPCB' || actionBean.permohonan.kodUrusan.kod eq 'PYTN' || actionBean.permohonan.kodUrusan.kod eq 'TSPSS' || actionBean.permohonan.kodUrusan.kod eq 'SBMS' || actionBean.permohonan.kodUrusan.kod eq 'PSBT'}">

                        <tr><td>
                                <display:table cellpadding="0" cellspacing="0" class="tablecloth" name="${actionBean.mppList}" id="line1">
                                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line1_rowNum}</display:column>
                                    <display:column title="Butir-butir Pembangunan" style="vertical-align:baseline">
                                        <c:if test="${line1.pemilikan.kod == 'H'}">
                                            ${line1.catatan}
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
                                    <display:column title="Keluasan Lot" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${line1.luas}"/>&nbsp;${line1.kodUnitLuas.nama}</display:column>

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
                                        <td width="70%">&nbsp;<b><fmt:formatNumber pattern="#,##0.0000" value="${actionBean.keluasanLotTotal}"/> &nbsp;${actionBean.kodUnitLuas}</b> </td>     
                                    </tr>    
                                </table>                            
                            </td>
                            <td> <br><br></td>
                        </tr>
                    </c:if>

                    <tr><td><b>3. LOKASI TANAH</b></td></tr>
                    <c:if test="${edit && !actionBean.ptg}">
                        <tr><td><s:textarea rows="5" cols="150" name="lokasiTanah" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>${actionBean.lokasiTanah} &nbsp;</td></tr>
                    </c:if>
                    <c:if test="${edit && actionBean.ptg}">
                        <tr><td><s:textarea rows="5" cols="150" name="lokasiTanah" class="normal_text"/></td></tr>
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
                                        <%--<c:forEach items="${tbl1.hakmilik.senaraiPihakBerkepentingan}" var="senarai">--%>
                                        <display:column title="Nama" style="vertical-align:baseline">
                                            <c:if test="${(senarai.jenis.kod eq 'PM' || senarai.jenis.kod eq 'PA') && senarai.aktif eq 'Y'}">
                                                <c:out value="${senarai.pihak.nama}"/>
                                            </c:if>
                                        </display:column>
                                        <display:column title="Bahagian Dimiliki" style="vertical-align:baseline">
                                            <c:if test="${(senarai.jenis.kod eq 'PM' || senarai.jenis.kod eq 'PA') && senarai.aktif eq 'Y'}">
                                                ${senarai.syerPembilang}/${senarai.syerPenyebut}
                                            </c:if>
                                        </display:column>
                                        <display:column title="No. KP/Syarikat" style="vertical-align:baseline">
                                            <c:if test="${(senarai.jenis.kod eq 'PM' || senarai.jenis.kod eq 'PA') && senarai.aktif eq 'Y'}">
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
                            <tr><td> - ${actionBean.bebananBerdafter} &nbsp;</td></tr>
                        </c:if>
                        <tr><td><b>Surat Persetujuan daripada Pihak-Pihak Yang Berkepentingan</b></td></tr>
                        <c:if test="${edit}">
                            <tr><td><s:textarea rows="5" cols="150" name="suratPersetujuan" class="normal_text"/></td></tr>
                        </c:if>
                        <c:if test="${!edit}">
                            <tr><td> - ${actionBean.suratPersetujuan} &nbsp;</td></tr>
                        </c:if>
                    </c:if>     
                    <tr><td><b>5. KEADAAN TANAH</b></td></tr>
                    <tr><td>
                            <table border="0" width="75%" cellspacing="5">
                                <tr>
                                    <td id="tdLabel"><font color="black">Keadaan Tanah :</font></td>
                                    <td id="tdDisplay">    
                                        <c:if test = "${actionBean.laporanTanah.catatanSempadanUtara != null}">
                                            <b>Keadaan semasa tanah</b> adalah ${actionBean.laporanTanah.catatanSempadanUtara}
                                        </c:if>

                                        <c:if test = "${actionBean.laporanTanah.strukturTanah != null}">
                                            &nbsp; <b>Klasifikasi tanah </b> ialah ${actionBean.laporanTanah.strukturTanah.nama} 
                                        </c:if>

                                        <c:if test = "${actionBean.laporanTanah.dilintasTiangElektrik != null || actionBean.laporanTanah.dilintasTiangTelefon != null
                                                        || actionBean.laporanTanah.dilintasPaip != null ||  actionBean.laporanTanah.dilintasTaliar != null
                                                        || actionBean.laporanTanah.dilintasSungai != null || actionBean.laporanTanah.dilintasParit != null
                                                        || actionBean.laporanTanah.dilintasiLain != null || actionBean.laporanTanah.dilintasLaluanGas != null}">
                                              &nbsp; <b>Dilintasi</b> oleh  
                                        </c:if>

                                        <c:if test  = "${actionBean.laporanTanah.dilintasTiangElektrik == 'Y'}">
                                            dilintasi tiang elektrik,
                                        </c:if>
                                        <c:if test  = "${actionBean.laporanTanah.dilintasTiangTelefon == 'Y'}">
                                            dilintasi tiang telefon,
                                        </c:if>
                                        <c:if test  = "${actionBean.laporanTanah.dilintasLaluanGas == 'Y'}">
                                            dilintasi laluan gas,
                                        </c:if>
                                        <c:if test  = "${actionBean.laporanTanah.dilintasPaip == 'Y'}">
                                            dilintasi paip,
                                        </c:if>
                                        <c:if test  = "${actionBean.laporanTanah.dilintasTaliar == 'Y'}">
                                            dilintasi tali air,
                                        </c:if>
                                        <c:if test  = "${actionBean.laporanTanah.dilintasSungai == 'Y'}">
                                            sungai,
                                        </c:if>
                                        <c:if test  = "${actionBean.laporanTanah.dilintasParit == 'Y'}">
                                            parit,
                                        </c:if>
                                        <c:if test  = "${actionBean.laporanTanah.dilintasiLain != null}">
                                            ${actionBean.laporanTanah.dilintasiLain}
                                        </c:if>
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
                                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot eq null}"> - </c:if>
                                                    </td>
                                                    <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraJenis eq '1'}">Industri&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraJenis eq '2'}">Bangunan&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraJenis eq '3'}">Pertanian&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraJenis eq '4'}">Rizab&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraJenis eq null}"> - </c:if>
                                                    </td>
                                                    <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraKegunaan ne null}"> ${actionBean.laporanTanah.sempadanUtaraKegunaan}&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraKegunaan eq null}"> - </c:if>
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
                                                    <c:if test="${actionBean.laporanTanah.sempadanSelatanNoLot eq null}"> - </c:if>
                                                    </td>
                                                    <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanSelatanJenis eq '1'}">Industri&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanSelatanJenis eq '2'}">Bangunan&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanSelatanJenis eq '3'}">Pertanian&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanSelatanJenis eq '4'}">Rizab&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanSelatanJenis eq null}"> - </c:if>
                                                    </td>
                                                    <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanSelatanKegunaan ne null}"> ${actionBean.laporanTanah.sempadanSelatanKegunaan}&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanSelatanKegunaan eq null}"> - </c:if>
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
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurNoLot eq null}"> - </c:if>
                                                    </td>
                                                    <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurJenis eq '1'}">Industri&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurJenis eq '2'}">Bangunan&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurJenis eq '3'}">Pertanian&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurJenis eq '4'}">Rizab&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurJenis eq null}"> - </c:if>
                                                    </td>
                                                    <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurKegunaan ne null}"> ${actionBean.laporanTanah.sempadanTimurKegunaan}&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurKegunaan eq null}"> - </c:if>
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
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratNoLot eq null}"> - </c:if>
                                                    </td>
                                                    <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratJenis eq '1'}">Industri&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratJenis eq '2'}">Bangunan&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratJenis eq '3'}">Pertanian&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratJenis eq '4'}">Rizab&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratJenis eq null}"> - </c:if>
                                                    </td>
                                                    <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratKegunaan ne null}"> ${actionBean.laporanTanah.sempadanBaratKegunaan}&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratKegunaan eq null}"> - </c:if>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th>
                                                        Timur Laut
                                                    </th>
                                                    <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurLautMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurLautMilikKerajaan eq 'T'}">Milik</c:if>
                                                    </td>
                                                    <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurLautNoLot ne null}"> ${actionBean.laporanTanah.sempadanTimurLautNoLot}&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurLautNoLot eq null}"> - </c:if>
                                                    </td>
                                                    <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurlautJenis eq '1'}">Industri&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurlautJenis eq '2'}">Bangunan&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurlautJenis eq '3'}">Pertanian&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurlautJenis eq '4'}">Rizab&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurlautJenis eq null}"> - </c:if>
                                                    </td>
                                                    <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurlautKegunaan ne null}"> ${actionBean.laporanTanah.sempadanTimurlautKegunaan}&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTimurlautKegunaan eq null}"> - </c:if>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th>
                                                        Tenggara
                                                    </th>
                                                    <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTenggaraMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTenggaraMilikKerajaan eq 'T'}">Milik</c:if>
                                                    </td>
                                                    <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTenggaraNoLot ne null}"> ${actionBean.laporanTanah.sempadanTenggaraNoLot}&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTenggaraNoLot eq null}"> - </c:if>
                                                    </td>
                                                    <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTenggaraJenis eq '1'}">Industri&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTenggaraJenis eq '2'}">Bangunan&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTenggaraJenis eq '3'}">Pertanian&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTenggaraJenis eq '4'}">Rizab&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTenggaraJenis eq null}"> - </c:if>
                                                    </td>
                                                    <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTenggaraKegunaan ne null}"> ${actionBean.laporanTanah.sempadanTenggaraKegunaan}&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanTenggaraKegunaan eq null}"> - </c:if>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th>
                                                        Barat Laut
                                                    </th>
                                                    <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratLautMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratLautMilikKerajaan eq 'T'}">Milik</c:if>
                                                    </td>
                                                    <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratlautNoLot ne null}"> ${actionBean.laporanTanah.sempadanBaratlautNoLot}&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratlautNoLot eq null}"> - </c:if>
                                                    </td>
                                                    <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratlautJenis eq '1'}">Industri&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratlautJenis eq '2'}">Bangunan&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratlautJenis eq '3'}">Pertanian&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratlautJenis eq '4'}">Rizab&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratlautJenis eq null}"> - </c:if>
                                                    </td>
                                                    <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratlautKegunaan ne null}"> ${actionBean.laporanTanah.sempadanBaratlautKegunaan}&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratlautKegunaan eq null}"> - </c:if>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th>
                                                        Barat Daya
                                                    </th>
                                                    <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratdayaMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratdayaMilikKerajaan eq 'T'}">Milik</c:if>
                                                    </td>
                                                    <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratdayaNoLot ne null}"> ${actionBean.laporanTanah.sempadanBaratdayaNoLot}&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratdayaNoLot eq null}"> - </c:if>
                                                    </td>
                                                    <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratdayaJenis eq '1'}">Industri&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratdayaJenis eq '2'}">Bangunan&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratdayaJenis eq '3'}">Pertanian&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratdayaJenis eq '4'}">Rizab&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratdayaJenis eq null}"> - </c:if>
                                                    </td>
                                                    <td>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratdayaKegunaan ne null}"> ${actionBean.laporanTanah.sempadanBaratdayaKegunaan}&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanBaratdayaKegunaan eq null}"> - </c:if>
                                                    </td>
                                                </tr>
                                            </table>
                                        </td></tr>
                                    <c:if test="${edit && !actionBean.ptg}">
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
                                <c:if test="${edit && actionBean.ptg}">
                                    <tr>
                                        <td id="tdLabel"><font color="black">Maklumat Lain :</font></td>                                        
                                        <td><s:textarea name="maklumat" rows="5" cols="50" class="normal_text"/></td>
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
                                <display:column title="Ulasan" style="width:600px" >${fn:replace(line1.ulasan,newline,"")}</display:column>
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
                                <display:column title="Ulasan" style="width:600px" >${fn:replace(line1.ulasan,newline,"")}</display:column>
                            </display:table>

                        </td></tr>

                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPT' && actionBean.permohonan.kodUrusan.kod ne 'PPK'}">
                        <c:if test="${actionBean.stageId ne 'ulasanadunteksediajkbb'}">
                            <tr><td><b><font style="text-transform:uppercase">8. PERAKUAN PENTADBIR TANAH ${actionBean.pejTanah}&nbsp;</font></b></td></tr>
                                        <c:if test="${edit && !actionBean.ptg}">
                                <tr><td><s:textarea name="perakuan" rows="5" cols="150" class="normal_text"/></td></tr>
                            </c:if>
                            <c:if test="${actionBean.stageId ne 'derafperakuanjkbbptd'}">
                                <c:if test="${!(actionBean.pengguna.perananUtama.kod eq '80') && !actionBean.ptg && edit}">
                                    <tr><td><s:radio name="perakuan2" value="Disokong" onclick="return myconfirm(this);"/>&nbsp; DISOKONG &nbsp; &nbsp;
                                            <s:radio name="perakuan2" value="Tidak Disokong" onclick="return myconfirm(this);"/>&nbsp; TIDAK DISOKONG</td>
                                    </tr>                                    
                                </c:if>
                            </c:if>
                        </c:if>
                        <c:if test="${!edit}">
                            <tr><td>${actionBean.perakuan}</td></tr>
                        </c:if>
                        <c:if test="${edit && actionBean.ptg}">
                            <tr><td>${actionBean.perakuan}</td></tr>
                        </c:if>
                    </c:if>
                </table>
                <br>
                <p align="center">
                    <c:if test="${actionBean.stageId ne 'rekodkedjkbbtangguh' && !showBtn}">
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </c:if>
                    <c:if test="${actionBean.stageId eq 'rekodkedjkbbtangguh'}">
                        <s:button name="kertasBaru" id="kertasBaru" value="Kertas Baru" class="longbtn" onclick="validation(this.name, this.form);"/>
                    </c:if>                   
                </p>

            </div>
        </fieldset>
    </div>
</s:form>