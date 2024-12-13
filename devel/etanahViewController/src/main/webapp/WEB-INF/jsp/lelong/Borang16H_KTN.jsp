<%-- 
    Document   : Borang16H_KTN
    Created on : Oct 18, 2010, 4:52:26 PM
    Author     : mazurahayati.yusop
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
    
    function showReport(){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        var url = '${pageContext.request.contextPath}/lelong/borang16KTN?genReport';
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

  
        $(document).ready( function(){
            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        });

        function dateValidation(value){
            var now = new Date();
            var vsplit = value.split('/');
            var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
            var date = new Date(fulldate);
    <%--var now1 = (now.getUTCMonth()+1)+'/'+(now.getUTCDate()+31)+'/'+(now.getFullYear());//+1900);
    var now1New = new Date(now1);
    if(now1New > date){
        alert("Tarikh dimasukkan mestilah sama atau melebihi 30 hari dari tarikh sekarang.");
        $('#datepicker').val("");
        $('#datepicker').focus();
    }else{--%>
            var date1 = (date.getUTCMonth()+1)+'/'+(date.getUTCDate()+119)+'/'+(date.getFullYear());//+1900);
            var dateNew = new Date(date1);
            var sdate = dateNew.getUTCDate()+'/'+(dateNew.getUTCMonth()+1)+'/'+(dateNew.getFullYear());//+1900);
            $('#tarikhBayar').val(sdate);
            $('#tarikhBayar1').val(sdate);
        }
    <%--}--%>
    
</script>

<s:form beanclass="etanah.view.stripes.lelong.Borang16KTNActionBean">

    <p>
        <s:messages />
        <s:errors />&nbsp;
    </p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                BORANG 16H
            </legend><br>

            <p align="center"  style="font-size: 11pt">
                <b>Kanun Tanah Negara</b>

            </p><br>

            <p align="center"  style="font-size: 11pt">
                <b>BORANG 16H</b>

            </p><br>
            <p align="center"  style="font-size: 11pt">

                &nbsp;<b>(Seksyen 263)</b><br>
            </p><br>

            <p align="center"  style="font-size: 14pt">
                <b>PERINTAH JUALAN ATAS PERMINTAAN PEMEGANG GADAIAN</b>
            </p><br>
            &nbsp;  &nbsp;

            <p align="left"  style="font-size: 11pt">
                Saya,  *Pendaftar Mahkamah Tinggi/Pentadbir Tanah pada menjalankan kuasa yang diberi oleh seksyen 263 Kanun Tanah Negara, dengan ini memerintahkan
                supaya dijual tanah yang diperihalkan didalam jadual dibawah ini:<br>
                <br>
                Dan saya selanjutnya memerintahkan - <br><br>
                &nbsp; &nbsp;(a)  bahawa jualan itu hendaklah secara lelongan awam, yang akan diadakan pada <c:if test="${actionBean.edit eq 'false'}"><b><fmt:formatDate value="${actionBean.lelong.tarikhLelong}" pattern="dd/MM/yyyy"/></b> jam <b><fmt:formatDate value="${actionBean.lelong.tarikhLelong}" pattern="hh:mm aaa"/></b> di <b>${actionBean.lelong.tempat}</b>; dan<br><br></c:if>
                <c:if test="${actionBean.edit eq 'true'}"><b><s:text name="tarikhLelong" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.value)"/>&nbsp;<s:hidden name="jam"/><s:hidden name="minit"/><s:hidden name="ampm"/></b> di <b>
                        <s:text id="tempat" name="tempat" size="45"/></b>; dan<br><br></c:if>

                    &nbsp; &nbsp;(b)  bahawa harga rizab bagi maksud jualan ialah <b>RM <fmt:formatNumber pattern="#,##0.00" value="${actionBean.lelong.hargaRizab}"/>&nbsp;(${actionBean.lelong.ejaanHarga}).</b><br><br>

                2. Saya dapati bahawa amaun yang kena dibayar kepada pemegang gadaian pada tarikh ini ialah <b>RM <fmt:formatNumber pattern="#,##0.00" value="${actionBean.enkuiri.amaunTunggakan}"/></b> <b></b>

                <c:forEach var="i" items="${actionBean.listPermohonanPihak}" ><c:if test='${i.jenis.kod == "PG"}'>.&nbsp; Nama pemegang gadaian ialah <b>${i.pihak.nama}</c:if></c:forEach></b><br><br>

                3. Jualan ini hendaklah tertakluk kepada syarat-syarat berikut:<br><br>
            </p>
            <div align="center">

                <table width="1165">
                    <tr>
                    <p style="font-size: 11pt">
                        &nbsp;    (a) penawar memiliki, jumlah yang bersamaan dengan sepuluh peratus daripada harga rizab yang dinyatakan dibawah perenggan 1(b) di atas:
                        <b>RM <fmt:formatNumber pattern="#,##0.00" value="${actionBean.lelong.deposit}"/></b>;<br><br>
                    </p>
                    </tr>
                    <tr>
                    <p style="font-size: 11pt">
                        &nbsp; (b) amaun penuh harga jualan boleh dibayar serta-merta selepas tukul dijatuhkan oleh penawar yang berjaya kepada pemegang gadaian;<br><br>
                    </p>
                    </tr>

                    <tr>
                    <p style="font-size: 11pt">
                        &nbsp; (c) di mana amaun penuh harga jualan tidak dibayar selepas tukul dijatuhkan oleh penawar yang berjaya -<br>
                    </p>
                    <table width="1100" style="font-size: 11pt">
                        <tr>
                        <p style="font-size: 11pt">
                            (i)  maka, jumlah wang yang dinyatakan di dalam perenggan (a) hendaklah dibayar kepada pemegang gadaian dan ia hendaklah dipegang sebagai
                            deposit sehingga keseluruhan harga belian telah dibayar; dan <br><br>
                        </p>
                        </tr>
                        <tr>
                        <p style="font-size: 11pt">
                            (ii) sebelum baki harga belian diselesaikan, jumlah wang yang dinyatakan di dalam subperenggan (i) hendaklah dikreditkan kedalam akaun
                            penggadai untuk tujuan mengurangkan jumlah wang yang kena dibayar kepada pemegang gadaian;<br><br>
                        </p>
                        </tr>
                    </table>
                    </tr>

                    <tr>
                    <table width="1160" style="font-size: 11pt">
                        <tr>
                        <p style="font-size: 11pt">
                            &nbsp;   (d)  baki harga belian hendaklah diselesaikan dalam masa tidak lebih daripada satu ratus dua puluh hari daripada tarikh jualan, iaitu tidak
                            lewat dari tarikh <c:if test="${actionBean.edit eq 'true'}"><b><s:text id="tarikhBayar" name="tarikhAkhirBayaran" disabled="true" /></b> dan bahawa tiada ada apa-apa lanjutan masa bagi tempoh yang telah dinyatakan; dan <br><br>
                                <s:hidden name="tarikhAkhirBayaran" id="tarikhBayar1"/></c:if>
                            <c:if test="${actionBean.edit eq 'false'}"><b><fmt:formatDate value="${actionBean.lelong.tarikhAkhirBayaran}" pattern="dd MMMM yyyy"/></b>dan bahawa tiada ada apa-apa lanjutan masa bagi tempoh yang telah dinyatakan; dan</c:if>
                            <br><br>
                        </p>
                        </tr>
                        <tr>
                        <p style="font-size: 11pt">
                            &nbsp;     (e)  di mana harga belian sepenuhnya tidak diselesaikan sebelum atau pada tarikh yang ditentukan dalam perenggan (d), jumlah wang yang dibayar
                            sebagai deposit di bawah perenggan (c) kepada pemegang gadaian hendaklah dilucuthak dan dilupuskan dengan cara yang dinyatakan di bawah seksyen 267.
                        </p>
                        </tr>
                    </table>
                    &nbsp; &nbsp;    <p style="font-size: 11pt" align="left">
                        Bertarikh Pada.........haribulan......,20.....<br>
                    </p>
                </table>

            </div>

            <br>
            <br>

        </fieldset>
        <c:if test="${actionBean.edit eq 'true'}">
            <p align="center">
                <s:button name="simpan16KTN" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                <%--<s:reset name="" value="Isi Semula" class="btn"/>&nbsp;--%>
                <s:button name="genReport" id="report" value="Cetak Borang 16H" class="longbtn" onclick="showReport();"/>&nbsp;
            </p>
        </c:if>

        <c:if test="${actionBean.edit ne 'true'}">
            <p align="center">
                <s:button name="genReport" id="report" value="Jana Borang 16H" class="longbtn" onclick="showReport();"/>&nbsp;
            </p>
        </c:if>

    </div>
</s:form>
