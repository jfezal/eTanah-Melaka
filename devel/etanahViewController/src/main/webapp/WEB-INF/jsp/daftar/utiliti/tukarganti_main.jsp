<%-- 
    Document   : tukarganti_main
    Created on : Jul 13, 2015, 10:25:46 AM
    Author     : haqqiem
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
        <title>Tukar Ganti | Utama</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
        <script language="javascript">

            $(document).ready(function() {
                $('input:text').each(function() {
                    $(this).focus(function() {
                        $(this).addClass('focus')
                    });
                    $(this).blur(function() {
                        $(this).removeClass('focus')
                    });
                });
                $('select').each(function() {
                    $(this).focus(function() {
                        $(this).addClass('focus')
                    });
                    $(this).blur(function() {
                        $(this).removeClass('focus')
                    });
                });

                $('form').submit(function() {
                    doBlockUI();
                    var valid = false;
                    var id = $('#idPermohonan').val();
                    if (id === '') {
                        $('.kodUrusan').each(function(index) {
                            updateSelect(index);
                            var val = $('#kodUrusanKod' + index).val();
                            if (val != '') {
                                valid = true;
                            }
                            if (val == '') {
                                valid = false;
                            }
                        });
                    } else {
                        valid = true;
                    }

                    if (!valid)
                        doUnBlockUI();

                    return valid;
                });
                $('#b1').hide();
                $('#b2').hide();
            });
            
            function updateSelect() {
                var textKodUrusanKod = document.getElementById('kodUrusanKod');
                if (textKodUrusanKod.value.length > 0) {
                    var selectKodUrusan = document.getElementById('kodUrusan');
                    selectKodUrusan.selectedIndex = 0;
                    var kod = textKodUrusanKod.value.toUpperCase();
                    for (var i = 0; i < selectKodUrusan.options.length; ++i) {
                        if (selectKodUrusan.options[i].value == kod) {
                            selectKodUrusan.selectedIndex = i;
                            updateJabatan(selectKodUrusan.options[i].parentNode.label);
                            break;
                        }
                    }
                    if (selectKodUrusan.selectedIndex == 0) {
                        $('#kodUrusanKod').val('');
                        alert('Kod Urusan ' + kod + ' tidak dijumpai.');
                        $('#kodUrusanKod').focus();
                    }
                }
            }

            function updateKod(kod) {
                var selectKodUrusan = document.getElementById('kodUrusan');
                $('#kodUrusanKod').val(kod);
                if(kod == 'HKTHK'){
                    $('#b1').show();
                    $('#b2').hide();
                }else if (kod == 'HSTHK'){
                    $('#b1').hide();
                    $('#b2').show();
                }else{
                    $('#b1').hide();
                    $('#b2').hide();
                }
                if (selectKodUrusan.selectedIndex > 0) {
                    var textKodUrusanKod = document.getElementById('kodUrusanKod');
                    textKodUrusanKod.value = selectKodUrusan.options[selectKodUrusan.selectedIndex].value;
                    updateJabatan(selectKodUrusan.options[selectKodUrusan.selectedIndex].parentNode.label);
                }
            }

            function updateJabatan(namaJabatan) {
                var selectJabatan = document.getElementById('kodJabatan');
                for (i = 0; i < selectJabatan.length; i++) {
                    if (selectJabatan.options[i].text == namaJabatan) {
                        selectJabatan.selectedIndex = i;
                        break;
                    }
                }
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

            function doUnBlockUI() {
                $.unblockUI();
            }
            
            function checkingValue(id){
                var inpt;
                if(id == 'urusan'){
                    inpt = document.getElementById('kodUrusan');
                    if(inpt.value == '0'){
                        alert('Sila Pilih Urusan.');
                        $('#kodUrusan').focus();
                        return false;
                    }
                }
                if(id == 'idMohon'){
                    inpt = document.getElementById('idMohon');
                    if(inpt.value == ''){
                        alert('Sila masukkan ID Perserahan untuk membuat carian.');
                        $('#idMohon').focus();
                        return false;
                    }
                }
                if(id == 'idHakmilik'){
                    inpt = document.getElementById('idHakmilik');
                    if(inpt.value == ''){
                        alert('Sila masukkan ID Hakmilik untuk membuat carian.');
                        $('#idHakmilik').focus();
                        return false;
                    }
                }
                if(id == 'serah'){
                    inpt = document.getElementById('idSerah');
                    if(inpt.value == ''){
                        alert('Sila masukkan ID Perserahan untuk dibatalkan.');
                        $('#idSerah').focus();
                        return false;
                    }
                }
                return true;
            }
            
            function choose(idMohon){
//                var l = idMohon.substring(6);
//                alert(l);
                $('#idMohon').val(idMohon);
                $('#step7').click();                
            }
        </script>
    </head>
    <body>      
        <stripes:messages />
        <stripes:errors />
        <div align="center"><table style="width:100%" bgcolor="#00FFFF">
                <tr>
                    <td width="100%" height="20" >
                        <div style="background-color: 00FFFF;" align="center">
                            <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">PENDAFTARAN: Utiliti Tukar Ganti</font>
                        </div>
                    </td>
                </tr>
            </table></div>
        <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>



        <!--  PERMOHOHONAN/PERSERAHAN-->

        <stripes:form action="/daftar/utiliti_tukarganti" id="main_kaunter">

            <fieldset class="aras1">
                <p class=title>Langkah 1: Pendaftaran Urusan</p>
                <span style="font-weight:normal;color: black" class=instr>Medan bertanda <em>*</em> adalah mandatori. Sila pilih urusan pada ruang yang disediakan.</span>


                <c:set scope="request" var="senaraiUrusanPendaftaran" value="${actionBean.senaraiUrusanTukarganti}" />

                <p><label><em>*</em>Urusan : </label><nobr>

                    <stripes:text name="kodUrusan" id="kodUrusanKod" onblur="javascript:updateSelect();" size="6" class="kodUrusan" onkeyup="this.value=this.value.toUpperCase();" style="display:none;"/>

                    <c:set scope="request" var="jabatanNama" value="${pilihanKodUrusan[0].jabatanNama}" />

                    <stripes:select name="senaraiUrusan[0].kodUrusanPilih" id="kodUrusan" onchange="javascript:updateKod(this.value);"
                                    style="width:450px;" >

                        <stripes:option label="Pilih Urusan..."  value="0" />
                        <c:forEach items="${senaraiUrusanPendaftaran}" var="i" >
                            <stripes:option value="${i.kod}" >${i.kod} - ${i.nama}</stripes:option>
                        </c:forEach>
                    </stripes:select>
                </nobr>
            </p>

            <p>
                <label>&nbsp;</label>
                <stripes:submit name="Step2" value="Seterusnya" class="btn" id="step2" onclick="return checkingValue('urusan')"/>
            </p>
            <span style="font-weight:normal;color: black" class=instr>
                Senarai Dokumen yang perlu disertakan. <br>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1. &nbsp;&nbsp;&nbsp;<em>*</em>DHDK - Dokumen Hakmilik (Komputer) <br>		
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2. &nbsp;&nbsp;&nbsp;<em>*</em>SCR - Sijil Carian Rasmi Yang Terkini
                <div id="b1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3. &nbsp;&nbsp;&nbsp;<em>*</em>PB1 - Pelan B1 yang Disahkan JUPEM <br>	</div>
                <div id="b2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3. &nbsp;&nbsp;&nbsp;<em>*</em>PB2 - Pelan B2 <br>		</div>
            </span>
            <br>&nbsp;
        </fieldset>
    <br>
        <fieldset class="aras1">
            <p class=title>Carian Perserahan</p>
            <span style="font-weight:normal;color: black" class=instr>Medan bertanda <em>*</em> adalah mandatori. Sila masukkan ID Perserahan pada ruang yang disediakan.</span>


            <c:set scope="request" var="senaraiUrusanPendaftaran" value="${actionBean.senaraiUrusanTukarganti}" />

            <p><label><em>*</em>ID Perserahan : </label>
                <stripes:text name="idMohon" id="idMohon" size="30" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label>&nbsp;</label>
                <stripes:submit name="Step7a" value="Cari" class="btn" id="step7" onclick="return checkingValue('idMohon')"/>
                <stripes:button name="" class='btn' value="Isi Semula" onclick="clearText('main_kaunter');"/>&nbsp;
            </p>
            
            
            <fieldset class="aras1">
                <legend>
                    Hasil Carian
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiPermohonan}"
                                   pagesize="10" cellpadding="0" cellspacing="0"
                                   requestURI="/daftar/utiliti_tukarganti" id="line">
                        <c:set var="row_num" value="${row_num+1}"/>
                        <display:column title="Bil" sortable="true" > <div align="center">${row_num}</div></display:column>
                        <display:column property="idPermohonan" title="ID Perserahan" style="width:25%"/>
                        <display:column title="Senarai Hakmilik" style="width:25%">
                            <ol>                               
                                <c:forEach items="${line.senaraiHakmilik}" var="senarai" varStatus="a">
                                    <li>
                                        <c:out value="${senarai.hakmilik.idHakmilik}" />
                                    </li>
                                </c:forEach>
                            </ol>
                        </display:column>
                        <display:column property="infoAudit.dimasukOleh.nama" title="Dimasuk Oleh"/>
                        <display:column title="Tarikh Masuk">
                            <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm aa"/>
                        </display:column>
                        <display:column title="Status">
                            <c:if test="${line.status eq 'SL'}">Selesai</c:if>
                            <c:if test="${line.status eq 'TA'|| line.status eq 'AK'}">Belum Selesai</c:if>
                            <c:if test="${line.status eq 'TK' || line.status eq 'BP' }">Telah Dibatalkan</c:if>
                        </display:column>
                        <display:column title="Pilih">
                            <c:choose>
                                <c:when test="${line.status eq 'TA'|| line.status eq 'AK'}">
                                    <stripes:button name="" class='btn' value="Pilih" onclick="choose('${line.idPermohonan}');"/>&nbsp;
                                </c:when>
                                <c:otherwise>
                                    <stripes:button name="" class='btn' value="Pilih" disabled="true"/>&nbsp;
                                </c:otherwise>
                            </c:choose>
                        </display:column>
                    </display:table>
                </div>
            </fieldset>

        </fieldset>
    <br>
        <fieldset class="aras1">
            <p class=title>Carian ID Hakmilik</p>
            <span style="font-weight:normal;color: black" class=instr>Medan bertanda <em>*</em> adalah mandatori. Sila masukkan ID Hakmilik pada ruang yang disediakan.</span>

            <p><label><em>*</em>ID Hakmilik : </label>
                <stripes:text name="idHakmilik" id="idHakmilik" size="30" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label>&nbsp;</label>
                <stripes:submit name="Step10" value="Cari" class="btn" id="step10" onclick="return checkingValue('idHakmilik')"/>
                <stripes:button name="" class='btn' value="Isi Semula" onclick="clearText('main_kaunter');"/>&nbsp;
            </p>

        </fieldset>
    <br>
        <fieldset class="aras1">
            <p class=title>Batal Perserahan</p>
            <span style="font-weight:normal;color: black" class=instr>Medan bertanda <em>*</em> adalah mandatori. Sila masukkan ID Perserahan pada ruang yang disediakan.</span>

            <p><label><em>*</em>ID Perserahan : </label>
                <stripes:text name="idSerah" id="idSerah" size="30" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label>&nbsp;</label>
                <stripes:submit name="Step11" value="Cari" class="btn" id="step11" onclick="return checkingValue('serah')"/>
                <stripes:button name="" class='btn' value="Isi Semula" onclick="clearText('main_kaunter');"/>&nbsp;
            </p>
            <br>
            <c:if test="${actionBean.flag}">
                <p>
                    <label>ID Perserahan : </label>
                    ${actionBean.perserahan.idPermohonan}&nbsp;
                </p>
                <p>
                    <label>Dimasuk Oleh : </label>
                    ${actionBean.perserahan.infoAudit.dimasukOleh.nama}&nbsp;
                </p>
                <p>
                    <label>Tarikh Masuk : </label>
                    <fmt:formatDate type="date" pattern="dd/MM/yyyy HH:mm:ss a" value="${actionBean.perserahan.infoAudit.tarikhMasuk}"/>&nbsp;      
                </p>
                <p>
                    <label>&nbsp;</label>
                    <c:if test="${actionBean.perserahan.status eq 'SL'}">
                    <em>* ID Perserahan ${actionBean.perserahan.idPermohonan} telah didaftarkan.
                    Tidak dibenarkn untuk hapus</em>
                    </c:if>
                    <c:if test="${actionBean.perserahan.status eq 'BP' 
                                  or actionBean.perserahan.status eq 'TK'}">
                    <em>* ID Perserahan ${actionBean.perserahan.idPermohonan} telah dibatalkan.</em>
                    </c:if>
                    <c:if test="${actionBean.perserahan.status eq 'TA'}">
                        <stripes:submit name="Step12" value="Hapus" class="btn" id="step12"/>
                    </c:if>
                </p>
            </c:if>

        </fieldset>

        <stripes:submit name="updateUrusanJabatan" style="display:none;" />

    </stripes:form>

    <br/>
</body>
</html>