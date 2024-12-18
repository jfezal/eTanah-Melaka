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


    function simpanKeadaanTnh() {
        var jalanMasuk = document.getElementById('jalan1').value;
        var jarakKeadaan = document.getElementById('jarakKeadaan').value;

//        var valueJarak = document.getElementById('valueJarak').value;
        var idMohonHakmilik = document.getElementById('idMohonHakmilik').value;
        var len = $('.tanah').length;
        var param1 = '';
        for (var i = 1; i <= len; i++) {
            var ckd = $('#keadaanTanahNew' + i).is(":checked");
            if (ckd) {
                param1 = param1 + '&keadaanTanah123=' + $('#keadaanTanahNew' + i).val();
            }
        }

        var len2 = $('.tanahLintas').length;
        var param2 = '';
        for (var i = 1; i <= len2; i++) {
            var ckd2 = $('#keadaanTanahLintasNew' + i).is(":checked");
            if (ckd2) {
                param2 = param2 + '&keadaanTanahLintas=' + $('#keadaanTanahLintasNew' + i).val();
            }
        }

        var len3 = $('.tanahBekalan').length;
        var param3 = '';
        for (var i = 1; i <= len3; i++) {
            var ckd3 = $('#keadaanTanahBekalanNew' + i).is(":checked");
            if (ckd3) {
                param3 = param3 + '&keadaanTanahBekalan=' + $('#keadaanTanahBekalanNew' + i).val();
            }
        }

        var len4 = $('.jalanMasuk2').length;
        var param4 = '';
        for (var i = 1; i <= len4; i++) {
            var ckd4 = $('#simpanJalan' + i).is(":checked");
            if (ckd4) {
                param4 = param4 + '&jalan=' + $('#simpanJalan' + i).val();
            }
        }

        var len5 = $('.keizinan1').length;
        var param5 = '';
        for (var i = 1; i <= len5; i++) {
            var ckd5 = $('#rizab' + i).is(":checked");
            if (ckd5) {
                param5 = param5 + '&jalanRizab=' + $('#rizab' + i).val();
            }
        }
        var len6 = $('.bgn').length;
        var param6 = '';
        for (var i = 1; i <= len6; i++) {
            var bngn = document.getElementById('bangunanNew' + i).value;
            var ckd6 = bngn;
            if (ckd6) {
                param6 = param6 + '&bangunan=' + bngn;
            }
        }

        var len7 = $('.tnm').length;
        var param7 = '';
        for (var i = 1; i <= len7; i++) {
            var tnmn = document.getElementById('tanamanNew' + i).value;
            var ckd7 = tnmn;
            if (ckd7) {
                param7 = param7 + '&tanaman=' + tnmn;
            }
        }
       
        var len8 = $('.pb').length;
        var param8 = '';
        for (var i = 1; i <= len8; i++) {
//            alert(len8);
            var phkpb = document.getElementById('pihakBNew' + i).value;
              
            var ckd8 = phkpb;
            if (ckd8) {
                param8 = param8 + '&pihakB=' + phkpb;
            }
        }
         
        var len9 = $('.nilaiT').length;
        var param9 = '';
        for (var i = 1; i <= len9; i++) {
            var nlaiT = document.getElementById('nilaianNew' + i).value;
            var ckd9 = nlaiT;
            if (ckd9) {
                param9 = param9 + '&nilaian=' + nlaiT;
            }
        }
        doBlockUI();
        var url = '${pageContext.request.contextPath}/laporanTanah?simpanKeadaanTanah&param1=' + param1 + '&param2=' + param2 + '&param3=' + param3
                + '&param4=' + param4 + '&param5=' + param5 + '&param4=' + param4 + '&jarakKeadaan=' + jarakKeadaan
                + '&param7=' + param7 + '&param6=' + param6 + '&idMohonHakmilik=' + idMohonHakmilik
                + '&param8=' + param8 + '&param9=' + param9;

        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
            },
            success: function(data) {
                $('#page_div').html(data);
            }
        });
        doUnBlockUI();

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

    function kembali(idMohonHakmilik) {
//        alert("idMohonHakmilik = " + idMohonHakmilik);
        doBlockUI();
        var url = "${pageContext.request.contextPath}/laporanTanah?statusPerihalTanah&idMohonHakmilik=" + idMohonHakmilik;
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
    function homePage(idMohonHakmilik) {
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
                        <td colspan="4"><center><b>Keadaan Semasa</b></center></td>  
                </tr>
                </tfoot>
            </table>
            <br>

            <br>
        </fieldset>
        <br>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">


            <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                <tfoot>
                    <tr>    
                        <td colspan="4"><center><b>Perihal Permohonan</b></center></td>  
                </tr>
                </tfoot>

                <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2"> 
                    <tr>
                        <th>Tanah</th>
                        <td>

                            <c:set value="1" var="count"/>
                            <c:set value="1" var="count2"/>
                            <c:set value="1" var="count3"/>
                            <c:set value="1" var="count4"/>
                            <c:set value="1" var="count5"/>
                            <c:set value="1" var="count6"/>
                            <c:set value="1" var="count7"/>
                            <c:set value="1" var="count8"/>
                            <c:set value="1" var="count9"/>
                            <c:forEach items="${actionBean.kodKeadaanTanahList}" var="item" varStatus="line">
                                <c:set var="checked" value=""/>
                                <c:forEach items="${actionBean.listMohonKeadaanTanah}" var="item2" >
                                    <c:if test="${item2.kodKeadaanTanah.kod eq item.kod}">
                                        <c:set var="checked" value="checked"/>
                                    </c:if>
                                </c:forEach>
                                <input type="checkbox" name="keadaanTanah${count}" ${checked} id="keadaanTanahNew${count}" value="${item.kod}" class="tanah"/>&nbsp; ${item.nama}&nbsp; / &nbsp;  

                                <c:set value="${count +1}" var="count"/>

                            </c:forEach>
                            <br>
                            <br><br>
                            <c:forEach items="${actionBean.kodKeadaanTanahList2}" var="item" varStatus="line">
                                <c:set var="checked" value=""/>
                                <c:forEach items="${actionBean.listMohonKeadaanTanah2}" var="item2" >
                                    <c:if test="${item2.kodKeadaanTanah.kod eq item.kod}">
                                        <c:set var="checked" value="checked"/>
                                    </c:if>
                                </c:forEach>

                                <input type="checkbox" name="keadaanTanahLintas${count2}" ${checked} id="keadaanTanahLintasNew${count2}" value="${item.kod}" class="tanahLintas"/>&nbsp; ${item.nama}&nbsp; / &nbsp;
                                <%--<s:hidden name="kodKeadaan" id="kodKeadaan" value="${item.kod}"/>--%>
                                <c:set value="${count2 +1}" var="count2"/>
                            </c:forEach>
                            <br><br><br>
                            <c:forEach items="${actionBean.kodKeadaanTanahList3}" var="item" varStatus="line">

                                <c:set var="checked" value=""/>
                                <c:forEach items="${actionBean.listMohonKeadaanTanah3}" var="item2" >
                                    <c:if test="${item2.kodKeadaanTanah.kod eq item.kod}">
                                        <c:set var="checked" value="checked"/>
                                    </c:if>
                                </c:forEach>

                                <input type="checkbox" name="keadaanTanahBekalan${count3}" ${checked} id="keadaanTanahBekalanNew${count3}" value="${item.kod}" class="tanahBekalan"/>&nbsp; ${item.nama}&nbsp; / &nbsp;
                                <%--<s:hidden name="kodKeadaan" id="kodKeadaan" value="${item.kod}"/>--%>
                                <c:set value="${count3 +1}" var="count3"/>

                            </c:forEach>  
                        </td>
                    </tr>
                    <tr>
                        <th>Tanaman</th>
                        <td>
                            <c:forEach items="${actionBean.kodKeadaanTanahList4}" var="item" varStatus="line">
                                <c:if test="${fn:length(actionBean.listMohonKeadaanTanah4) > 0}">
                                    <c:forEach items="${actionBean.listMohonKeadaanTanah4}" var="item2" >
                                        <c:if test="${item2.keterangan ne null}">
                                            <s:textarea name="tanaman${count4}" id="tanamanNew${count4}" value="${item2.keterangan}" class="tnm"  rows="3" cols="100"/>
                                        </c:if>
                                        <c:if test="${item2.keterangan eq null}">
                                            <s:textarea name="tanaman${count4}" id="tanamanNew${count4}" value="" class="tnm" rows="3" cols="100"/>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${fn:length(actionBean.listMohonKeadaanTanah4) <= 0}">
                                    <s:textarea name="tanaman${count4}" id="tanamanNew${count4}" value="" class="tnm" rows="3" cols="100"/>
                                </c:if>

                                <c:set value="${count4 +1}" var="count4"/>
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <th>Bangunan</th>
                        <td> 
                            <c:forEach items="${actionBean.kodKeadaanTanahList5}" var="item" varStatus="line">
                                <c:if test="${fn:length(actionBean.listMohonKeadaanTanah5) > 0}">
                                    <c:forEach items="${actionBean.listMohonKeadaanTanah5}" var="item2" >
                                        <c:if test="${item2.keterangan ne null}">
                                            <s:textarea name="bangunan${count5}" id="bangunanNew${count5}" value="${item2.keterangan}" class="bgn"  rows="3" cols="100"/>
                                        </c:if>
                                        <c:if test="${item2.keterangan eq null}">
                                            <s:textarea name="bangunan${count5}" id="bangunanNew${count5}" value="" class="bgn"  rows="3" cols="100"/>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${fn:length(actionBean.listMohonKeadaanTanah5) <= 0}">
                                    <s:textarea name="bangunan${count5}" id="bangunanNew${count5}" value="" class="bgn"  rows="3" cols="100"/>
                                </c:if>
                                <c:set value="${count5 +1}" var="count5"/>
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <th>Jalan Keluar/Masuk</th>
                        <td> 
                            <input type="radio" name="pilih" id="jalan1" value="Ada" onclick="myFunction1('ada')"/>Ada &nbsp; /
                            <input type="radio" name="pilih" id="jalan1" value="Tiada" onclick="myFunction1('tiada')"/>Tiada &nbsp; : &nbsp;
                            <!--<div id="myDIV" id="jalanMsk"  style="display: none;" class="jalanMasuk1">-->
                            <c:forEach items="${actionBean.kodKeadaanTanahList6}" var="item" varStatus="line">

                                <c:set var="checked" value=""/>
                                <c:forEach items="${actionBean.listMohonKeadaanTanah6}" var="item2" >
                                    <c:if test="${item2.kodKeadaanTanah.kod eq item.kod}">
                                        <c:set var="checked" value="checked"/>
                                    </c:if>
                                </c:forEach>

                                <input type="checkbox" name="jalan${count6}" ${checked} id="simpanJalan${count6}" value="${item.kod}" class="jalanMasuk2"/>&nbsp; ${item.nama}&nbsp; / &nbsp;
                                <c:set value="${count6 +1}" var="count6"/>

                            </c:forEach> 

                            <!--</div>-->

                            <br><br><br>

                            <c:forEach items="${actionBean.kodKeadaanTanahList7}" var="item" varStatus="line">
                                <c:set var="checked" value=""/>
                                <c:forEach items="${actionBean.listMohonKeadaanTanah7}" var="item2" >
                                    <c:if test="${item2.kodKeadaanTanah.kod eq item.kod}">
                                        <c:set var="checked" value="checked"/>
                                    </c:if>
                                </c:forEach>
                                <input type="checkbox" name="jalanRizab${count7}" ${checked} id="rizab${count7}" value="${item.kod}" class="keizinan1"/>&nbsp; ${item.nama}&nbsp; / &nbsp;
                                <c:set value="${count7 +1}" var="count7"/>

                            </c:forEach>

                            <br><br><br>

                            <c:if test="${fn:length(actionBean.listMohonKeadaanTanah8) > 0}">
                                <c:forEach items="${actionBean.listMohonKeadaanTanah8}" var="item" >
                                    <c:if test="${item.keterangan ne null}">
                                        <s:textarea name="${item.keterangan}" id="jarakKeadaan" value="${item.keterangan}" class=""  rows="3" cols="100"/>
                                    </c:if>
                                    <c:if test="${item.keterangan eq null}">
                                        <s:textarea name="jarakKeadaan" id="jarakKeadaan" value="" class=""  rows="3" cols="100"/>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                            <c:if test="${fn:length(actionBean.listMohonKeadaanTanah8) <= 0}">
                                <s:textarea name="jarakKeadaan" id="jarakKeadaan" value="" class=""  rows="3" cols="100"/>
                            </c:if>


                        </td>
                    </tr>
                    <tr>
                        <th>Pihak Berkepentingan</th>
                        <td> 
                            <c:forEach items="${actionBean.kodKeadaanTanahList9}" var="item" varStatus="line">
                                <c:if test="${fn:length(actionBean.listMohonKeadaanTanah9) > 0}">
                                    <c:forEach items="${actionBean.listMohonKeadaanTanah9}" var="item2" >
                                        <c:if test="${item2.keterangan ne null}">
                                            <s:textarea name="pihakB${count8}" id="pihakBNew${count8}" value="${item2.keterangan}" class="pb"  rows="3" cols="100"/>
                                        </c:if>
                                        <c:if test="${item2.keterangan eq null}">
                                            <s:textarea name="pihakB${count8}" id="pihakBNew${count8}" value="" class="pb"  rows="3" cols="100"/>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${fn:length(actionBean.listMohonKeadaanTanah9) <= 0}">
                                    <s:textarea name="pihakB${count8}" id="pihakBNew${count8}" value="" class="pb"  rows="3" cols="100"/>
                                </c:if>
                                <c:set value="${count8 +1}" var="count8"/>
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <th>Nilaian Tanah</th>
                        <td> 
                            <c:forEach items="${actionBean.kodKeadaanTanahList10}" var="item" varStatus="line">
                                <c:if test="${fn:length(actionBean.listMohonKeadaanTanah10) > 0}">
                                    <c:forEach items="${actionBean.listMohonKeadaanTanah10}" var="item2" >
                                        <c:if test="${item2.keterangan ne null}">
                                            <s:textarea name="nilaian${count9}" id="nilaianNew${count9}" value="${item2.keterangan}" class="nilaiT"  rows="3" cols="100"/>
                                        </c:if>
                                        <c:if test="${item2.keterangan eq null}">
                                            <s:textarea name="nilaian${count9}" id="nilaianNew${count9}" value="" class="nilaiT"  rows="3" cols="100"/>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${fn:length(actionBean.listMohonKeadaanTanah10) <= 0}">
                                    <s:textarea name="nilaian${count9}" id="nilaianNew${count9}" value="" class="nilaiT"  rows="3" cols="100"/>
                                </c:if>
                                <c:set value="${count9 +1}" var="count9"/>
                            </c:forEach>
                        </td>
                    </tr>
                    <s:hidden name="idMohonHakmilik" id="idMohonHakmilik" value="${actionBean.hakmilikPermohonan.id}"/>

                </table>
                <BR>
                <CENTER>
                    <s:button name="keadaanTanah" id="" value="Kembali" class="btn" onclick="kembali('${actionBean.hakmilikPermohonan.id}')"/>
                    &nbsp;&nbsp;
                    <s:button name="simpanKeadaanTanah" id="" value="simpan" class="btn" onclick="simpanKeadaanTnh(this.form, this.name, 'page_div', 'this','${actionBean.hakmilikPermohonan.id}')"/>
                    &nbsp;&nbsp;
                    <s:button name="lotSempadan" id="" value="Seterusnya" class="btn" onclick="lotSempadan1('${actionBean.hakmilikPermohonan.id}')"/>
                    &nbsp;&nbsp;
                    <s:button name="homePage" id="" value="Home" class="btn" onclick="showFormMain('${actionBean.hakmilikPermohonan.id}')"/>


                </CENTER>
                <br>
            </table>
            <br>

        </fieldset>
    </div>





</s:form>
