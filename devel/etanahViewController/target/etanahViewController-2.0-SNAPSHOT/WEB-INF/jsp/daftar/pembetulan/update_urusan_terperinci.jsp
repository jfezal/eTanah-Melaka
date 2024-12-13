<%--
    Document   : update_urusan
    Created on : Feb 23, 2010, 2:05:49 PM
    Author     : wan.fairul
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

        $('form').submit(function() {
            doBlockUI();
            var valid = false;
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
            doUnBlockUI();
            return valid;
        });
    });

    function updateSelect(idx) {
        var textKodUrusanKod = document.getElementById('kodUrusanKod' + idx);
        if (textKodUrusanKod.value.length > 0) {
            var selectKodUrusan = document.getElementById('kodUrusan' + idx);
            selectKodUrusan.selectedIndex = 0;
            var kod = textKodUrusanKod.value.toUpperCase();
            for (var i = 0; i < selectKodUrusan.options.length; ++i) {
                if (selectKodUrusan.options[i].value == kod) {
                    selectKodUrusan.selectedIndex = i;
                    updateJabatan(idx, selectKodUrusan.options[i].parentNode.label);
                    break;
                }
            }
            if (selectKodUrusan.selectedIndex == 0) {
                $('#kodUrusanKod' + idx).val('');
                alert('Kod Urusan ' + kod + ' tidak dijumpai.');
                $('#kodUrusanKod' + idx).focus();
            }
        }
    }

    function completeIdPerserahan(id, type) {
        var caw = document.getElementById("caw");
        var res = id.split("/");
        var seq = res[0].length;

        if ((id.length < 16) && (id.length > 0)) {
            var b = '';
            var jenis = '';
            switch (seq) {
                case 1 :
                    b = '00000' + res[0];
                    break;
                case 2 :
                    b = '0000' + res[0];
                    break;
                case 3 :
                    b = '000' + res[0];
                    break;
                case 4 :
                    b = '00' + res[0];
                    break;
                case 5 :
                    b = '0' + res[0];
                    break;
                case 6 :
                    b = res[0];
                    break;
            }


            var idSerah = '04' + caw.value + type + res[1] + b;
            if (type == 'SA') {
//                        jenis = '#SAB';
                if ($('input[name=allowed_2]').is(':checked')) {
                }

                else if ($('input[name=allowed_2]').is(':unchecked')) {
                    $('#SAB').val(idSerah);
                }
            }

            if (type == 'SB') {
//                        jenis = '#SBD';
                if ($('input[name=allowed_3]').is(':checked')) {
                }

                else if ($('input[name=allowed_3]').is(':unchecked')) {
                    $('#SBD').val(idSerah);
                }
            }

            if (type == 'SW') {
//                        jenis = '#SWD';
                if ($('input[name=allowed_1]').is(':checked')) {
                }

                else if ($('input[name=allowed_1]').is(':unchecked')) {
                    $('#SWD').val(idSerah);
                }
            }
        }
    }

    function updateKod(i) {
        var selectKodUrusan = document.getElementById('kodUrusan' + i);
        if (selectKodUrusan.selectedIndex > 0) {
            var textKodUrusanKod = document.getElementById('kodUrusanKod' + i);
            textKodUrusanKod.value = selectKodUrusan.options[selectKodUrusan.selectedIndex].value;
            updateJabatan(i, selectKodUrusan.options[selectKodUrusan.selectedIndex].parentNode.label);
        }
    }

    function updateJabatan(whichItem, namaJabatan) {
        var selectJabatan = document.getElementById('kodJabatan' + whichItem);
        for (i = 0; i < selectJabatan.length; i++) {
            if (selectJabatan.options[i].text == namaJabatan) {
                selectJabatan.selectedIndex = i;
                break;
            }
        }
    }

    function selectUrusanForJabatan(whichItem) {
        var kodJabatan = $("#kodJabatan" + whichItem + " option:selected").text();

        var found = false;
        var selectUrusan = document.getElementById("kodUrusan" + whichItem);
        for (i = 0; i < selectUrusan.length; i++) {
            if (selectUrusan.options[i].parentNode.label == kodJabatan) {
                selectUrusan.selectedIndex = i;
                found = true;
                break;
            }
        }

        if (!found)
            selectUrusan.selectedIndex = 0;
    }
</script>
<script language="javascript">
    function save(event, f, idPembetulan, idPerserahan) {
        doBlockUI();
        var q = $(f).formSerialize();
        var url = f.action + '?' + event + '&idPembetulan=' + idPembetulan + '&idPerserahan=' + idPerserahan;
        $.post(url, q,
                function(data) {
                    $('#page_div', opener.document).html(data);
                    self.close();
                }, 'html');
    }

    function save2(event, f, idPembetulan, idPerserahan, dokumenSave) {
        doBlockUI();
        var q = $(f).formSerialize();
        var url = f.action + '?' + event + '&idPembetulan=' + idPembetulan + '&idPerserahan=' + idPerserahan + '&dokumenSave=' + dokumenSave;
        $.post(url, q,
                function(data) {
                    $('#page_div', opener.document).html(data);
                    self.close();
                }, 'html');
    }

    function removeChanges2(event, f, idDokumen, idPerserahan) {
        var answer = confirm("adakah anda pasti untuk Hapus?");
        if (answer) {
            var url = '${pageContext.request.contextPath}/daftar/pembetulan/betul?deleteSASBSW&idDokumen=' + idDokumen + '&idPerserahan=' + idPerserahan;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    });
        }
    }
    function kemaskini(idDokumen, idUrusan, idPembetulan, idPerserahan, idPermohonanLama) {
//        alert(idDokumen);
        alert(idUrusan);
        alert(idPembetulan);
        window.open("${pageContext.request.contextPath}/daftar/pembetulan/betul?kemaskini&idDokumen=" + idDokumen + '&idUrusan =' + idUrusan + '&idPembetulan =' + idPembetulan + '&idPerserahan =' + idPerserahan
                + '&idPermohonanLama =' + idPermohonanLama, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1300,height=700,scrollbars=yes");
    }
    function Delete(idDokumen, idUrusan, idPembetulan, idPerserahan, idPermohonanLama) {
//        alert(idDokumen);
        alert("DELETE");
//        alert(idPembetulan);
        window.open("${pageContext.request.contextPath}/daftar/pembetulan/betul?deleteSASBSW&idDokumen=" + idDokumen + '&idUrusan =' + idUrusan + '&idPembetulan =' + idPembetulan + '&idPerserahan =' + idPerserahan
                + '&idPermohonanLama =' + idPermohonanLama, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1300,height=700,scrollbars=yes");
                  self.close();
    }
    
    
    function tambahSurat(idDokumen, idPerserahan, idPermohonanLama) {
//        alert(idDokumen);
//        alert(idPerserahan);
        window.open("${pageContext.request.contextPath}/daftar/pembetulan/betul?updateUrusanTerperinci&idDokumen=" + idDokumen + '&idPerserahan =' + idPerserahan
                + '&idPermohonanLama =' + idPermohonanLama, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1300,height=700,scrollbars=yes");
    }
//    function kemaskini(idDokumen, idPerserahan) {
////        var answer = confirm("adakah anda pasti untuk Hapus?");
////        alert(idDokumen);
////        alert(idPerserahan);
////        if (answer) {
//        var url = '${pageContext.request.contextPath}/daftar/pembetulan/betul?kemaskini&idDokumen=' + idDokumen + '&idPerserahan =' + idPerserahan;
//        $.get(url,
//                function(data) {
//                    $('#page_div').html(data);
//                });
////        }
//    }

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
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none" alt=""/>
<s:form name="form1" beanclass="etanah.view.stripes.nota.pembetulanActionBean">
    <s:hidden name="idPembetulan" value="${actionBean.idPembetulan}"/>
    <s:hidden name="caw" id="caw" value="${actionBean.caw}"/>
    <s:hidden name="idUrusan" id="idUrusan" value="${actionBean.idUrusan}"/>



    <s:messages />
    <s:errors />

    <div class="subtitle">

        <fieldset class="aras1">
            <legend>
                Kemaskini Maklumat Tambah Urusan
            </legend>
            <p style="color:red">
                *Isi ruang pembetulan kemudian tekan butang simpan.<br/>

            </p>
            <br>
            <c:set scope="request" var="senaraiJabatan" value="${listUtil.senaraiKodJabatan}" />
            <c:set scope="request" var="senaraiUrusanPendaftaran" value="${actionBean.senaraiUrusanPendaftaran}" />

            <p><label for="kodUrusankod"><em>*</em>Urusan</label><nobr>
                ${actionBean.permohonanLama.kodUrusan.nama}

            </nobr>
            </p>
            <p>
                <label><em>*</em>ID Perserahan :</label> 
                ${actionBean.permohonanLama.idPermohonan}
                &nbsp;
            </p>
            <p>
                <label><em>*</em>ID Hakmilik :</label> <s:select name="idHakmilik">
                    <c:forEach items="${actionBean.hakmilikPermohonanList}" var="item" varStatus="line">
                        <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                            ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                        </s:option>
                    </c:forEach>
                </s:select>
            </p>

            <br/>
        </fieldset>
    </div>


    <c:if test="${actionBean.kemaskini ne true}">
        <c:if test="${actionBean.tambah ne true}">
            <fieldset class="aras1">
                <legend>
                    Maklumat Tambahan 
                </legend>
                <br>
                <p>
                    <label for="noFail">No. Rujukan Fail:</label>                    
                    <s:text name="noFail" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
                </p>

                <c:forEach items="${actionBean.listSWD}" var="item" varStatus="line">
                    <p>
                        <label>Nombor Surat Kuasa Wakil :</label>
                        ${item.dokumen.noRujukan}
                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif'
                             onclick="Delete('${item.dokumen.idDokumen}', '${actionBean.permohonanLama.idPermohonan}', '${actionBean.permohonanLama.idPermohonan}')" onmouseover="this.style.cursor = 'pointer';">

                    </p>
                </c:forEach>

                <c:forEach items="${actionBean.listSBD}" var="item" varStatus="line">
                    <p>
                        <label>Nombor Surat Kebenaran:</label>
                         ${item.dokumen.noRujukan}
                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif'
                             onclick="Delete('${item.dokumen.idDokumen}', '${actionBean.idUrusan}', '${actionBean.idPembetulan}', '${actionBean.permohonanLama.idPermohonan}')" onmouseover="this.style.cursor = 'pointer';">      
                   
                    </p>
                </c:forEach>

                <c:forEach items="${actionBean.listSAB}" var="item" varStatus="line">
                    <p>
                        <label>Nombor Surat Amanah :</label>
                         ${item.dokumen.noRujukan}
                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif'
                             onclick="Delete('${item.dokumen.idDokumen}', '${actionBean.permohonanLama.idPermohonan}', '${actionBean.permohonanLama.idPermohonan}')" onmouseover="this.style.cursor = 'pointer';">    
                    </p>
                </c:forEach>


                <c:if test="${actionBean.kodUrusan ne 'HLTE'}">
                    <p>
                        <label>Nombor Warta :</label>                        
                        <s:text name="noRujukan" id="noRujukan" onkeyup="this.value=this.value.toUpperCase();" size="35"/> (Lama : ${actionBean.pkl.noRujukan})
                    </p>
                    <p>
                        <label>Tarikh Warta :</label>
                        <s:text name="tarikhRujukan" id="tarikhRujukan" class="datepicker" size="35"/>(Lama : ${actionBean.pkl.tarikhRujukan})
                    </p>
                    <p>
                        <label>Tarikh Sidang :</label>
                        <s:text name="tarikhSidang" id="tarikhSidang" class="datepicker" size="35"/>(Lama : ${actionBean.pkl.tarikhSidang})
                    </p>
                    <p>
                        <label>Tarikh Kuatkuasa :</label>
                        <s:text name="tarikhKuatkuasa" id="tarikhKuatkuasa" class="datepicker" size="35"/>(Lama : ${actionBean.pkl.tarikhKuatkuasa})
                    </p>
                    <p>
                        <label>Tarikh Lulus :</label>
                        <s:text name="tarikhLulus" id="tarikhLulus" class="datepicker" size="35"/>(Lama : ${actionBean.pkl.tarikhLulus})
                    </p>                    
                    <p>
                        <label>Kawasan :</label>
                        <s:text name="kawasan" id="kawasan" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
                    </p>
                </c:if>
                    <br>
                    <center>
                         <s:submit name="tambahSuratWakil" value="Tambah" class="btn"/>
                    </center>
               
                <br>



                <legend>
                    <c:if test="${actionBean.kodUrusan eq 'HLTE'}">Maklumat Berkaitan Hakmilik</c:if>
                    <c:if test="${actionBean.kodUrusan ne 'HLTE'}">Maklumat Berkaitan Pajakan</c:if>

                    </legend>
                    <div class="content" align="center">
                    <s:hidden name="sizeHakmilikPermohonan" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="sizeHakmilikPermohonan"/>
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" style="width:80%;"  cellpadding="0" cellspacing="0" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column title="ID Hakmilik">${line.hakmilik.idHakmilik}
                            <s:hidden name="hiddenIdHakmilik" id="hiddenIdHakmilik${line_rowNum-1}" value="${line.hakmilik.idHakmilik}"/>
                        </display:column>
                        <display:column title="Luas / Unit Asal">
                            ${line.hakmilik.luas}
                            ${line.hakmilik.kodUnitLuas.nama}
                        </display:column>
                        <display:column title="Luas / Unit Terlibat" ><%--strKodUOM[${line_rowNum-1}]"--%>
                            <s:text name="luasTerlibat" formatPattern="###0.0000" size="10"/>
                            <s:select  style="text-transform:uppercase"  name="strKodUOM">
                                <s:option value=" ${line.kodUnitLuas.kod}"> 
                                    <c:if test="${line.kodUnitLuas.kod ne null}">
                                        ${line.kodUnitLuas.nama}
                                    </c:if>
                                    <c:if test="${line.kodUnitLuas.kod eq null}">
                                        -- SILA PILIH --
                                    </c:if>
                                </s:option>
                                <s:options-collection collection="${list.senaraiKodUOMByJarak}" label="nama" value="kod"/>
                            </s:select>
                        </display:column>

                        <display:column title="Tempoh Pajakan">
                            <s:text name="tempohTahun"  size="2"/> Tahun            
                            <s:text name="tempohBulan" size="2"/> Bulan    
                            <s:text name="tempohHari" size="2"/> Hari 
                        </display:column>  
                        <display:column title="Tarikh Pajakan">
                            Tarikh Kuat Kuasa : <s:text name="trhKuasa" id="trKuasa" class="datepicker" size="10"/><br><br> 
                            Tarikh Tamat &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: <s:text name="trhTamat" id="trhTamat" class="datepicker" size="10"/>                 
                        </display:column>          

                    </display:table>
                </div>

                <div class="content" align="center">
                    <c:if test="${actionBean.kodUrusan eq 'ABT-D'}">
                        <legend>
                            Maklumat Berkaitan Hakmilik
                        </legend>
                        <s:hidden name="sizeHakmilikPermohonan" value="${fn:length(actionBean.hakmilikPermohonanList)}" />
                        <display:table class="tablecloth"  name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="line">
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Luas / Unit Asal">
                                ${line.hakmilik.luas}
                                ${line.hakmilik.kodUnitLuas.nama}
                            </display:column>
                            <display:column title="Luas / Unit Terlibat" ><%--strKodUOM[${line_rowNum-1}]"--%>
                                <s:text name="luasTerlibat" formatPattern="###0.0000" size="10"/>
                                <s:select  style="text-transform:uppercase"  name="strKodUOM">
                                    <s:option value=" ${line.kodUnitLuas.kod}"> ${line.kodUnitLuas.nama}</s:option>
                                    <s:options-collection collection="${list.senaraiKodUOMByJarak}" label="nama" value="kod"/>
                                </s:select>
                            </display:column>
                            <display:column title="Cukai">
                                RM ${line.hakmilik.cukai}
                            </display:column>
                        </display:table>
                    </c:if>
                    <c:if test="${actionBean.kodUrusan eq 'ABTKB'
                                  or actionBean.kodUrusan eq 'ABTBH'
                                  or actionBean.kodUrusan eq 'ABT'
                                  or actionBean.kodUrusan eq 'ABT-A'}">
                          <legend>
                              Maklumat Berkaitan Hakmilik
                          </legend>
                          <s:hidden name="sizeHakmilikPermohonan" value="${fn:length(actionBean.hakmilikPermohonanList)}" />
                          <display:table class="tablecloth"  name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="line">
                              <display:column title="No" sortable="true">${line_rowNum}</display:column>
                              <display:column title="Luas / Unit Asal">
                                  ${line.hakmilik.luas}
                                  ${line.hakmilik.kodUnitLuas.nama}
                              </display:column>
                              <display:column title="Luas / Unit Terlibat" ><%--strKodUOM[${line_rowNum-1}]"--%>
                                  <s:text name="luasTerlibat" formatPattern="###0.0000" size="10"/>
                                  <s:select  style="text-transform:uppercase"  name="strKodUOM">
                                      <s:option value=" ${line.kodUnitLuas.kod}"> ${line.kodUnitLuas.nama}</s:option>
                                      <s:options-collection collection="${list.senaraiKodUOMByJarak}" label="nama" value="kod"/>
                                  </s:select>
                              </display:column>
                              <display:column title="Cukai">
                                  RM ${line.hakmilik.cukai}
                              </display:column>
                              <display:column title="Cukai Baru">
                                  RM <s:text name="cukaiBaru" formatPattern="###0"/>
                              </display:column>
                          </display:table>
                    </c:if>
                    <c:if test="${actionBean.kodUrusan eq 'GD'}">
                    </c:if>


                    <legend>
                        Maklumat SA/SB/SW
                    </legend>

                    <display:table class="tablecloth" name="${actionBean.senaraiDokumen}" style="width:80%;"  cellpadding="0" cellspacing="0" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column title="ID Permohonan">${line.permohonan.idPermohonan}

                        </display:column>
                        <display:column title="Id Permohonan">
                            ${line.noRujukan}
                        </display:column>
                        <display:column title="Id hakmilik">
                            ${line.hakmilik}
                        </display:column>
                        <display:column title="Jenis SA/SB/SW">
                            ${line.kodDokumen.nama}
                        </display:column>

                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem${line_rowNum}' onclick="removeChanges2(this.name, this.form, '${line.idDokumen}', '${actionBean.permohonanLama.idPermohonan}')" onmouseover="this.style.cursor = 'pointer';" >
                            </div>
                        </display:column>


                    </display:table>

                    <br>
                    
                    <s:hidden name="sizeHakmilikPermohonan" value="${fn:length(actionBean.hakmilikPermohonanList)}" />
                    <p>
                        <s:button name="updateTambahUrusanTerperinci" value="Simpan" class="btn" onclick="save(this.name, this.form, '${actionBean.idPembetulan}', '${actionBean.permohonanLama.idPermohonan}')"/>
                        <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:self.close()"/>
                        <s:submit name="updateUrusanTerperinci" value="Refresh" class="btn"/>
                        
                    </p>
                </div>
            </fieldset>
        </c:if>
    </c:if>
    <fieldset class="aras1">
        <c:if test="${actionBean.tambah eq true}">
            <p>
                <label>Nombor Surat Kuasa Wakil :</label>
                <s:text name="SWD" onchange="this.value=this.value.toUpperCase();"  onblur="completeIdPerserahan(this.value,'SW')" size="35" id="SWD"/>
                <font size ="1" color="red"> [Contoh Format:  12/2014]</font>    
                <font color="black">/</font>
                    <s:checkbox name="allowed_1" id="allowed_1" onclick="allow();"/>
                <font color="black">Guna Format SPTB</font><font size ="1" color="red"> [Contoh:  Jil 33 Fol 68]</font>        
            </p>
            <p>
                <label>Nombor Surat Amanah 33:</label>
                <s:text name="SAB" onchange="this.value=this.value.toUpperCase();"  onblur="completeIdPerserahan(this.value,'SA')" size="35" id="SAB"/>
                <font size ="1" color="red"> [Contoh Format:  12/2014]</font>    
                <font color="black">/</font>
                    <s:checkbox name="allowed_2" id="allowed_2" onclick="allow();"/>
                <font color="black">Guna Format SPTB</font><font size ="1" color="red"> [Contoh:  Jil 33 Fol 68]</font> 
            </p>
            <p>
                <label>Nombor Surat Kebenaran :</label>
                <s:text name="SBD" onchange="this.value=this.value.toUpperCase();"  onblur="completeIdPerserahan(this.value,'SB')" size="35" id="SBD"/>
                <font size ="1" color="red"> [Contoh Format:  12/2014]</font>    
                <font color="black">/</font>
                    <s:checkbox name="allowed_3" id="allowed_3" onclick="allow();"/>
                <font color="black">Guna Format SPTB</font><font size ="1" color="red"> [Contoh:  Jil 33 Fol 68]</font> 
            </p>
            <center>


                <s:submit name="updateUrusanTerperinci" value="Kembali" class="btn"/>
                <s:button name="updateTambahUrusanTerperinci" value="Simpan" class="btn" onclick="save(this.name, this.form, '${actionBean.idPembetulan}', '${actionBean.permohonanLama.idPermohonan}')"/>
            </center>

            <br>
        </c:if>
        <br>

    </fieldset>


    <c:if test="${actionBean.kemaskini eq true}">
        <fieldset class="aras1">

            <c:if test="${actionBean.SWD ne null}">
                <p>
                    <label>Nombor Surat Kuasa Wakil :</label>
                    <s:text name="SWD" onchange="this.value=this.value.toUpperCase();"  onblur="completeIdPerserahan(this.value,'SW')" size="35" id="SWD"/>
                    <font size ="1" color="red"> [Contoh Format:  12/2014]</font>    
                    <font color="black">/</font>
                        <s:checkbox name="allowed_1" id="allowed_1" onclick="allow();"/>
                    <font color="black">Guna Format SPTB</font><font size ="1" color="red"> [Contoh:  Jil 33 Fol 68]</font>        
                </p>
            </c:if>
            <c:if test="${actionBean.SAB ne null}">
                <p>
                    <label>Nombor Surat Amanah 11:</label>
                    <s:text name="SAB" onchange="this.value=this.value.toUpperCase();"  onblur="completeIdPerserahan(this.value,'SA')" size="35" id="SAB"/>
                    <font size ="1" color="red"> [Contoh Format:  12/2014]</font>    
                    <font color="black">/</font>
                        <s:checkbox name="allowed_2" id="allowed_2" onclick="allow();"/>
                    <font color="black">Guna Format SPTB</font><font size ="1" color="red"> [Contoh:  Jil 33 Fol 68]</font> 
                </p>
            </c:if>
            <c:if test="${actionBean.SBD ne null}">
                <p>
                    <label>Nombor Surat Kebenaran :</label>
                    <s:text name="SBD" onchange="this.value=this.value.toUpperCase();"  onblur="completeIdPerserahan(this.value,'SB')" size="35" id="SBD"/>
                    <font size ="1" color="red"> [Contoh Format:  12/2014]</font>    
                    <font color="black">/</font>
                        <s:checkbox name="allowed_3" id="allowed_3" onclick="allow();"/>
                    <font color="black">Guna Format SPTB</font><font size ="1" color="red"> [Contoh:  Jil 33 Fol 68]</font> 
                </p> 
            </c:if>
            <br>
            <center>
               
             
                <s:button name="simpanSuratKemaskini" value="Simpan" class="btn" onclick="save2(this.name, this.form, '${actionBean.idPembetulan}', '${actionBean.permohonanLama.idPermohonan}', '${actionBean.dokumenSave.idDokumen}')"/>
            </center>
        </fieldset>
    </c:if>
</s:form>


