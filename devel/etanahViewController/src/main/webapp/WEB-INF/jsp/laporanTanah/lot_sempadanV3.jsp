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
    function nextPerakuan(idMohonHakmilik) {
//        alert("idMohonHakmilik = " + idMohonHakmilik);
        doBlockUI();
        var url = "${pageContext.request.contextPath}/laporanTanah?Perakuan&idMohonHakmilik=" + idMohonHakmilik;
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

</script>
<s:form beanclass="etanah.view.laporanTanah.laporanTanahV3" name="form">

    <s:errors/>
    <s:messages/>
    <s:hidden name="idHakmilik" id="idHakmilik"/>
    <s:hidden name="noPtSementara" id="noPtSementara"/>
    <%--<s:hidden name="idMohonHakmilik" id="idMohonHakmilik"/>--%>
    <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
    <%--<s:hidden name="idMohonHakmilik" id="idMohonHakmilik" value="${actionBean.hakmilikPermohonan.id}"/>--%>

    <div class="subtitle">
        <fieldset class="aras1">
            <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                <tfoot>
                    <tr>    
                        <td colspan="4"><center><b>Lot-lot Bersempadan</b></center></td>  
                </tr>
                </tfoot>
            </table>
            <br>

            <br>
        </fieldset>
        <br>
    </div>


    <fieldset class="aras1">
        <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
            <tfoot>
                <tr>    
                    <td colspan="4"><center><b>Perihal Lot-Lot Bersempadan</b></center></td>  
            </tr>
            </tfoot>



        </table>
        <div class="content" align="center">

            <display:table class="tablecloth" style="width:90%;" name="${actionBean.mohonLotSepadanList}" cellpadding="0" cellspacing="0" id="line">
                <display:column title="jenis sempadan">
                    <center>    ${line.jenisSempadan} </center>
                    </display:column>
                    <display:column title="Jenis Tanah" >
                    <center>  ${line.milikKerajaan} </center>
                    </display:column>
                    <display:column title="Catatan Atas Tanah ">
                    <center> ${line.catatanAtsTnh} </center>
                    </display:column>
                    <display:column title="No Lot ">
                    <center> ${line.noLot} </center>
                    </display:column>
                    <display:column title="Imej">

                    <%--<s:option value="">Sila pilih imej untuk dipaparkan</s:option>--%>

                    <c:if test="${line.jenisSempadan eq 'Utara'}">
                        <c:if test="${line.dokumen ne null}">
                            <a onclick="viewDokumen('${line.dokumen.idDokumen}')" onmouseover="this.style.cursor = 'pointer';">
                                <center><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/pictures.png'/></center>
                            </a>
                        </c:if>
                    </c:if>
                    <c:if test="${line.jenisSempadan eq 'Selatan'}">
                        <c:if test="${line.dokumen ne null}">
                            <a onclick="viewDokumen('${line.dokumen.idDokumen}')" onmouseover="this.style.cursor = 'pointer';">
                                <center><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/pictures.png'/></center>
                            </a>

                        </c:if>
                    </c:if>
                    <c:if test="${line.jenisSempadan eq 'Timur'}">
                        <c:if test="${line.dokumen ne null}">
                            <a onclick="viewDokumen('${line.dokumen.idDokumen}')" onmouseover="this.style.cursor = 'pointer';">
                                <center><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/pictures.png'/></center>
                            </a>
                        </c:if>
                    </c:if>
                    <c:if test="${line.jenisSempadan eq 'Barat'}">
                        <c:if test="${line.dokumen ne null}">
                            <a onclick="viewDokumen('${line.dokumen.idDokumen}')" onmouseover="this.style.cursor = 'pointer';">
                                <center><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/pictures.png'/></center>
                            </a>
                        </c:if>
                    </c:if>
                    <c:if test="${line.jenisSempadan eq 'Lokasi'}">
                        <c:if test="${line.dokumen ne null}">
                            <a onclick="viewDokumen('${line.dokumen.idDokumen}')" onmouseover="this.style.cursor = 'pointer';">
                                <center><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/pictures.png'/></center>
                            </a>
                        </c:if>
                    </c:if>
                    <c:if test="${line.jenisSempadan eq 'Panorama'}">
                        <c:if test="${line.dokumen ne null}">
                            <a onclick="viewDokumen('${line.dokumen.idDokumen}')" onmouseover="this.style.cursor = 'pointer';">
                                <center><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/pictures.png'/></center>
                            </a>
                        </c:if>
                    </c:if>
                    
                    <c:if test="${line.jenisSempadan eq 'Google'}">
                        <c:if test="${line.dokumen ne null}">
                            <a onclick="viewDokumen('${line.dokumen.idDokumen}')" onmouseover="this.style.cursor = 'pointer';">
                                <center><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/pictures.png'/></center>
                            </a>
                        </c:if>
                    </c:if>


                </display:column> 
                <display:column title="Tindakan">
                    <c:if test="${line.jenisSempadan eq 'Utara'}">
                        <a onclick="uploadForm('Utara',${line.id},${line.hakmilikPermohonan.id})" onmouseover="this.style.cursor = 'pointer';">
                            <center> <img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/pages.png'/></center>
                        </a>
                    </c:if>
                    <c:if test="${line.jenisSempadan eq 'Selatan'}">
                        <a onclick="uploadForm('Selatan',${line.id},${line.hakmilikPermohonan.id})" onmouseover="this.style.cursor = 'pointer';">
                            <center> <img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/pages.png'/></center>
                        </a>
                    </c:if>
                    <c:if test="${line.jenisSempadan eq 'Timur'}">
                        <a onclick="uploadForm('Timur',${line.id},${line.hakmilikPermohonan.id})" onmouseover="this.style.cursor = 'pointer';">
                            <center> <img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/pages.png'/></center>
                        </a>
                    </c:if>
                    <c:if test="${line.jenisSempadan eq 'Barat'}">
                        <a onclick="uploadForm('Barat',${line.id},${line.hakmilikPermohonan.id})" onmouseover="this.style.cursor = 'pointer';">
                            <center> <img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/pages.png'/></center>
                        </a>
                    </c:if>
                    <c:if test="${line.jenisSempadan eq 'Lokasi'}">
                        <a onclick="uploadForm('Lokasi',${line.id},${line.hakmilikPermohonan.id})" onmouseover="this.style.cursor = 'pointer';">
                            <center> <img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/pages.png'/></center>
                        </a>
                    </c:if>
                    <c:if test="${line.jenisSempadan eq 'Panorama'}">
                        <a onclick="uploadForm('Panorama',${line.id},${line.hakmilikPermohonan.id})" onmouseover="this.style.cursor = 'pointer';">
                            <center> <img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/pages.png'/></center>
                        </a>
                    </c:if>
                    <c:if test="${line.jenisSempadan eq 'Google'}">
                        <a onclick="uploadForm('Google',${line.id},${line.hakmilikPermohonan.id})" onmouseover="this.style.cursor = 'pointer';">
                            <center> <img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/pages.png'/></center>
                        </a>
                    </c:if>
                </display:column> 
            </display:table>

        </div>
        <br>


        <c:if test="${fn:length(actionBean.mohonLotSepadanLisLampiranD) > 0}">
            <center>
                LAMPIRAN D 
            </center>
            <div class="content" align="center">

                <display:table class="tablecloth" style="width:90%;" name="${actionBean.mohonLotSepadanLisLampiranD}" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="jenis sempadan">
                        <center>    ${line.jenisSempadan} </center>
                        </display:column>
                        <display:column title="No Lot">
                            <c:if test="${line.hakmilikPermohonan.noLot ne null}">
                            <center>   ${line.hakmilikPermohonan.noLot} &nbsp; ${line.hakmilikPermohonan.kodUnitLuas.nama}</center>
                            </c:if>
                        </display:column>
                        <display:column title="Jenis Lot">
                            <c:if test="${line.hakmilikPermohonan.kodUnitLuas ne null}">
                            <center>   ${line.hakmilikPermohonan.kodUnitLuas.nama}</center>
                            </c:if>
                        </display:column>
                        <display:column title="Catatan Gambar">
                        <center> ${line.catatanImg} </center>
                        </display:column>
                        <display:column title="Imej">
                            <c:if test="${line.dokumen ne null}">
                            <a onclick="viewDokumen('${line.dokumen.idDokumen}')" onmouseover="this.style.cursor = 'pointer';">
                                <center><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/pictures.png'/></center>
                            </a>
                        </c:if>
                    </display:column>
                    <display:column title="Kemaskini">
                        <a onclick="uploadForm('LampiranD',${line.id},${line.hakmilikPermohonan.id})" onmouseover="this.style.cursor = 'pointer';">
                            <center> <img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/pages.png'/></center>
                        </a>
                    </display:column>
                    <display:column title="Hapus">
                        <a onclick="delLampiranD(${line.id},${line.hakmilikPermohonan.id})" onmouseover="this.style.cursor = 'pointer';">
                            <center> <img alt="Hapus"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/delete-button.png'/></center>
                        </a>
                    </display:column>

                </display:table>

            </div>
        </c:if>
        <br>
        <center>

            <s:button name="keadaanTanah" id="" value="Kembali" class="btn" onclick="keadaanTanah1('${actionBean.hakmilikPermohonan.id}')"/>
            &nbsp;
            <s:button name="lotSempadan" id="" value="Refresh" class="btn" onclick="lotSempadan1('${actionBean.hakmilikPermohonan.id}')"/>
            &nbsp;
            <s:button name="keadaanTanah" id="" value="Lampiran D" class="btn" onclick="simpanLampiranD('${actionBean.hakmilikPermohonan.id}')"/>
            &nbsp;
            <s:button name="keadaanTanah" id="" value="Seterusnya" class="btn" onclick="nextPerakuan('${actionBean.hakmilikPermohonan.id}')"/>
            &nbsp;&nbsp;
            <s:button name="homePage" id="" value="Home" class="btn" onclick="showFormMain('${actionBean.hakmilikPermohonan.id}')"/>
        </center>
        <br>

        <br>
    </fieldset>













</s:form>
