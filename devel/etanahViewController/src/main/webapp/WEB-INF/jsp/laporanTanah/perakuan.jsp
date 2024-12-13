<%-- 
    Document   : laporan_tanahV3
    Created on : Oct 14, 2020, 1:38:23 PM
    Author     : zipzap
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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<%-- carian hakmilik end--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">


    $(document).ready(function() {

    });

    function lotSempadan1(idMohonHakmilik) {
//        alert("idMohonHakmilik = " + idMohonHakmilik);
        doBlockUI();
        var url = "${pageContext.request.contextPath}/laporanTanah?lotSempadan&idMohonHakmilik=" + idMohonHakmilik;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }

    function keadaanTanah1(idMohonHakmilik) {
//        alert("idMohonHakmilik = " + idMohonHakmilik);
        doBlockUI();
        var url = "${pageContext.request.contextPath}/laporanTanah?keadaanTanah&idMohonHakmilik=" + idMohonHakmilik;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }
    function simpanLampiranD(idMohonHakmilik) {
//        alert("idMohonHakmilik = " + idMohonHakmilik);
        doBlockUI();
        var url = "${pageContext.request.contextPath}/laporanTanah?lampiranD&idMohonHakmilik=" + idMohonHakmilik;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }

    function doSetDokumenHakmilik() {
        var idDokumen = document.getElementById("hmImej").options[document.getElementById("hmImej").selectedIndex].value;
        alert("idDokumen = " + idDokumen)
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function viewDokumen(idDokumen) {
//        var idDokumen = document.getElementById("hmImej").options[document.getElementById("hmImej").selectedIndex].value;
//        alert("idDokumen = " + idDokumen)
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }


    function myFunction1(value) {
        var x = document.getElementById("myDIV");
        var value = value;
        if (value == "ada") {
            x.style.display = "block";
        } else {
            x.style.display = "none";
        }
    }


    function uploadForm(pandanganImej, idlaporTnhSmpdn, idMohonHakmilik) {

        window.open("${pageContext.request.contextPath}/laporanTanah?uploadDocUTBS&pandanganImej=" + pandanganImej + "&idlaporTnhSmpdn=" + idlaporTnhSmpdn + '&idMohonHakmilik=' + idMohonHakmilik, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }


    function delLampiranD(idlaporTnhSmpdn, idMohonHakmilik) {
        doBlockUI();
        var url = "${pageContext.request.contextPath}/laporanTanah?deleteLampiranD&idlaporTnhSmpdn=" + idlaporTnhSmpdn + '&idMohonHakmilik=' + idMohonHakmilik;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }

    function cariPopup(idMohonHakmilik) {
//        alert("idMohonHakmilik = " + idMohonHakmilik);
        doBlockUI();
        var url = "${pageContext.request.contextPath}/laporanTanah?cariKodSekatan&idMohonHakmilik=" + idMohonHakmilik;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }

    function cariPopup(idMohonHakmilik, idLaporTanah) {
        window.open("${pageContext.request.contextPath}/laporanTanah?cariKodSekatan&idMohonHakmilik=" + idMohonHakmilik + "&idLaporTanah=" + idLaporTanah, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
    }
    function cariPopupSekatan(idMohonHakmilik, idLaporTanah) {
        window.open("${pageContext.request.contextPath}/laporanTanah?searchSyaratNyata&idMohonHakmilik=" + idMohonHakmilik + "&idLaporTanah=" + idLaporTanah, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
    }
    function kemaskini(idMohonHakmilik, idLaporTanah) {
        window.open("${pageContext.request.contextPath}/laporanTanah?kemaskiniPerakuan&idMohonHakmilik=" + idMohonHakmilik + "&idLaporTanah=" + idLaporTanah, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
    }
    function kemaskiniSOKanan(idMohonHakmilik, idLaporTanah) {
        window.open("${pageContext.request.contextPath}/laporanTanah?kemaskiniPerakuan&idMohonHakmilik=" + idMohonHakmilik + "&idLaporTanah=" + idLaporTanah, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
    }

    function showFormMain(idMohonHakmilik) {
//        alert("idMohonHakmilik = " + idMohonHakmilik);
        doBlockUI();
        var url = "${pageContext.request.contextPath}/laporanTanah?showFormMain&idMohonHakmilik=" + idMohonHakmilik;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }

    function deleteJTK(idJtek, idPermohonan) {
        alert("test")
        doBlockUI();
        var url = "${pageContext.request.contextPath}/laporanTanah?deleteJTEK&idJtek=" + idJtek + '&idPermohonan=' + idPermohonan;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }

</script>
<s:form beanclass="etanah.view.laporanTanah.laporanTanahV3" name="form">

    <s:errors/>
    <s:messages/>
    <s:hidden name="idHakmilik" id="idHakmilik"/>
    <s:hidden name="noPtSementara" id="noPtSementara"/>
    <%--<s:hidden name="idMohonHakmilik" id="idMohonHakmilik"/>--%>
    <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
    <s:hidden name="idMohonHakmilik" id="idMohonHakmilik" value="${actionBean.hakmilikPermohonan.id}"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                <tfoot>
                    <tr>    
                        <td colspan="4"><center><b>Jabatan Teknikal</b></center></td>  
                </tr>
                </tfoot>
            </table>
            <br>
            <center>
                <fieldset class="aras1">
                    <legend>
                        Kod Agensi
                    </legend>

                    <p>
                    <display title="Kod JTK">
                        <s:select  name="kodJTK" value="${line.kod}">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${actionBean.senaraiJTK}" label="nama" value="kod" sort="nama" />
                        </s:select>
                    </display>
                    </p>
                    <br>
                    <CENTER>
                        <s:button name="simpanJTK" id="" value="simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </CENTER>
                </fieldset>
            </center>
            <br>

            <br>

            <display:table class="tablecloth" style="width:90%;" name="${actionBean.senaraiJTKSimpan}" cellpadding="0" cellspacing="0" id="line">

                <display:column title="Permohonan">
                    <center>   ${line.permohonan.idPermohonan}</center>
                    </display:column>
                    <display:column title="Jabatan">
                    <center>   ${line.kodAgensi.nama}</center>
                    </display:column>

                <display:column title="Hapus">
                    <a onclick="deleteJTK(${line.id})" onmouseover="this.style.cursor = 'pointer';">
                        <center> <img alt="Hapus"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/delete-button.png'/></center>
                    </a>
                </display:column>
            </display:table>
        </fieldset>
        <br>
    </div>


    <fieldset class="aras1">
        <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
            <tfoot>
                <tr>    
                    <td colspan="4"><center><b>Perakuan ${actionBean.hakmilikPermohonan.permohonan.kodUrusan.nama}</b></center></td>  
            </tr>
            </tfoot>



        </table>
        <div class="content" align="center">

            <display:table class="tablecloth" style="width:90%;" name="${actionBean.mohonSyorLaporanTanahList}" cellpadding="0" cellspacing="0" id="line">

                <display:column title="Perihal Item">
                    <c:if test="${line.item ne null}">
                        <%--<c:if test="${actionBean.idAliran eq 'semak_laporanTanah'}">--%>
                            <center>    ${line.item}</center>
                            <%--</c:if>--%>
                        </c:if>
                    </display:column>


                <display:column title=" ">
                    <center>   ${line.nilaiBaru}</center>
                    </display:column>
                    <display:column title="Kemaskini">
                        <c:if test="${line.item eq 'Syarat Nyata'}">
                        <center>  <s:button name="cariKodSekatan" id="" value="Cari" class="btn" onclick="cariPopup('${actionBean.hakmilikPermohonan.id}','${line.id}')"/>   </center>
                        </c:if>
                        <c:if test="${line.item eq 'Sekatan Lama'}">
                        <center>  <s:button name="cariKodSekatan" id="" value="Cari" class="btn" onclick="cariPopupSekatan('${actionBean.hakmilikPermohonan.id}','${line.id}')"/>   </center>
                        </c:if>
                        <c:if test="${line.item ne 'Syarat Nyata'}">
                            <c:if test="${line.item ne 'Syarat Lama'}">
                                <c:if test="${line.item ne 'Sekatan Lama'}">
                                <center>  <s:button name="kemaskiniPerakuan" id="" value="kemaskini" class="btn" onclick="kemaskini('${actionBean.hakmilikPermohonan.id}','${line.id}')"/>   </center>
                                </c:if>
                            </c:if>
                        </c:if>
                    </display:column>
                </display:table>
        </div>


        <s:hidden id="kod" name="disSyaratSekatan.kodSktn" />
        <s:hidden id="kodNyata" name="disSyaratSekatan.kod"/>
        <s:hidden id="idMohonHakmilik" name="idMohonHakmilik" value="${actionBean.hakmilikPermohonan.id}" />

    </fieldset>
    <fieldset class="aras1">
        <c:if test="${fn:length(actionBean.disSyaratSekatan.listKodSekatan) > 0}" >
            <legend></legend>
            <p>
                <display:table style="width:100%" class="tablecloth" name="${actionBean.disSyaratSekatan.listKodSekatan}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/laporanTanah/laporanTanahV3" id="line">
                    <display:column> <s:radio name="radio_" id="${line.kod}" value="${line.sekatan}"   onclick="javascript:selectRadio(this)"/></display:column>
                    <display:column title="Kod Sekatan" property="kod"/>
                    <display:column title="Nama Sekatan" property="sekatan"/>
                </display:table>
            </p>
            <c:if test="${fn:length(actionBean.disSyaratSekatan.listKodSekatan) > 0}" >
                <p><label>&nbsp;</label>
                    <%--<s:hidden name="idLaporTanah" id="idLaporTanah" value="${actionBean.laporanTanah.idLaporan}"/>--%>
                    <s:hidden name="idMohonHakmilik" id="idMohonHakmilik" value="${actionBean.hakmilikPermohonan.id}"/>
                    <s:submit name="simpanKodSekatan" value="Simpan" class="btn" onclick="NoPrompt();"/>
                </p>
            </c:if>
        </c:if>
        <c:if test="${fn:length(actionBean.disSyaratSekatan.listKodSyaratNyata) > 0}" >
            <fieldset class="aras1">
                <legend>
                    Carian Syarat Nyata
                </legend>
                <s:hidden id="kod" name="disSyaratSekatan.kod" />
                <s:hidden id="kodSekatan" name="disSyaratSekatan.kodSktn" />
                <s:hidden id="idMohonHakmilik" name="idMohonHakmilik" value="${actionBean.hakmilikPermohonan.id}" />
                <s:hidden name="idHakmilik" id="idHakmilik"/>
                <p>
                    <s:hidden name="index" id="index" />
                </p>
                <p>
                    <label>Kod Syarat Nyata :</label>
                    <s:text name="disSyaratSekatan.kodSyaratNyata" id="kodSyaratNyata"/>
                </p>
                <p>

                    <label>Syarat Nyata :</label>
                    <s:text name="disSyaratSekatan.syaratNyata2" id="syaratNyata2"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="cariKodSyaratNyata" id="" value="Cari" class="btn" onclick="cariPopup('nyata','${actionBean.hakmilikPermohonan.id}')"/>
                    <%--<s:submit name="cariKodSyaratNyata" value="Cari" class="btn" onclick="NoPrompt();"/>--%>
                </p>
            </fieldset>
        </div>
        <br>

        <div class="subtitle">

            <%-- <c:if test="${actionBean.kodSyaratNyata != null}">--%>

            <fieldset class="aras1">
                <legend></legend>
                <p>
                    <display:table style="width:100%" class="tablecloth" name="${actionBean.disSyaratSekatan.listKodSyaratNyata}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/common/laporan/tanah/laporantanahNewActionBean" id="line">
                        <display:column> <s:radio name="radio_" id="${line.kod}" value="${line.syarat}" onclick="javascript:selectRadio(this)"/></display:column>
                        <display:column title="Kod Syarat Nyata" property="kod"/>
                        <display:column title="Nama Kod Syarat Nyata" property="syarat"/>
                    </display:table>
                </p>
                <c:if test="${fn:length(actionBean.disSyaratSekatan.listKodSyaratNyata) > 0}" >
                    <p><label>&nbsp;</label>
                        <%--<s:hidden name="idLaporTanah" id="idLaporTanah" value="${actionBean.laporanTanah.idLaporan}"/>--%>
                        <s:hidden name="idMohonHakmilik" id="idMohonHakmilik" value="${actionBean.hakmilikPermohonan.id}"/>
                        <s:submit name="simpanKodSyaratNyata" value="Simpan" class="btn" onclick="NoPrompt();"/>
                        <%--<s:button name="simpanKodSyaratNyata" value="Simpan" id="simpanKodSyaratNyata" class="btn" onclick="javascript:masuk();"/>--%>
                    </p>
                </c:if>
            </fieldset>
        </div>
    </c:if>
    <center> 
        <br>
        &nbsp;
        <s:button name="lotSempadan" id="" value="Kembali" class="btn" onclick="lotSempadan1('${actionBean.hakmilikPermohonan.id}')"/>
        &nbsp;
        <s:button name="keadaanTanah" id="" value="Refresh" class="btn" onclick="nextPerakuan('${actionBean.hakmilikPermohonan.id}')"/>
        &nbsp;&nbsp;
        <s:button name="homePage" id="" value="Home" class="btn" onclick="showFormMain('${actionBean.hakmilikPermohonan.id}')"/>
        <br>

        <br>

        <br>
    </center> 
</fieldset>


</s:form>


