<%--
    Document   : maklumat_Bangunan
    Created on : Sep 21, 2010, 11:57:33 AM
    Author     : User
--%>
<%@ page import="java.io.*,etanah.Configuration" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/thumb.css" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    function doSetDokumenUtara(v){
        var idDokumen = document.getElementById("fotoutara").options[document.getElementById("fotoutara").selectedIndex].value;
    <%--alert(idDokumen);--%>
            document.getElementById("idDokumenU").value = idDokumen;
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }

        function doSetDokumenTimur(v){
            var idDokumen = document.getElementById("fototimur").options[document.getElementById("fototimur").selectedIndex].value;
    <%--alert(idDokumen);--%>
            document.getElementById("idDokumenT").value = idDokumen;
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        function doSetDokumenBarat(v){
            var idDokumen = document.getElementById("fotobarat").options[document.getElementById("fotobarat").selectedIndex].value;
    <%--alert(idDokumen);--%>
            document.getElementById("idDokumenB").value = idDokumen;
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        function doSetDokumenSelatan(v){
            var idDokumen = document.getElementById("fotoselatan").options[document.getElementById("fotoselatan").selectedIndex].value;
    <%--alert(idDokumen);--%>
            document.getElementById("idDokumenS").value = idDokumen;
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }


        function doViewReportUtara(v) {

            var idDokumen = document.getElementById("fotoutara").options[document.getElementById("fotoutara").selectedIndex].value;
            alert(idDokumen);
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }

        function doViewReportTimur(v) {

            var idDokumen = document.getElementById("fototimur").options[document.getElementById("fototimur").selectedIndex].value;
            alert(idDokumen);
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        function doViewReportSelatan(v) {

            var idDokumen = document.getElementById("fotoselatan").options[document.getElementById("fotoselatan").selectedIndex].value;
            alert(idDokumen);
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        function doViewReportBarat(v) {

            var idDokumen = document.getElementById("fotobarat").options[document.getElementById("fotobarat").selectedIndex].value;
            alert(idDokumen);
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }

        function validateForm()
        {
            if((document.myform.simpanjln.checked == true)&&(document.myform.simpanlaluan.checked == true))
            {
    <%--alert("'dan'");
    return false;--%>
                simpanjln.checked = true;
                simpanlaluan.checked =true;

            }
            return true;
        }
        function muatNaikForm(idPermohonan,lokasi) {
            var left = (screen.width/2)-(1000/2);
            var top = (screen.height/2)-(150/2);
            var url = '${pageContext.request.contextPath}/pembangunan/muat_naik_imej?uploadDoc&idPermohonan=' + idPermohonan+'&lokasi=' + lokasi;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
        }

        function refreshPage(){
            var url = '${pageContext.request.contextPath}/pembangunan/muat_naik_imej?reload';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
        function validateNumber(elmnt,content) {
            //if it is character, then remove it..

            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }

        function removeNonNumeric( strString )
        {
            var strValidCharacters = "1234567890";
            var strReturn = "";
            var strBuffer = "";
            var intIndex = 0;
            // Loop through the string
            for( intIndex = 0; intIndex < strString.length; intIndex++ )
            {
                strBuffer = strString.substr( intIndex, 1 );
                // Is this a number
                if( strValidCharacters.indexOf( strBuffer ) > -1 )
                {
                    strReturn += strBuffer;
                }
            }
            return strReturn;
        }
        function muatNaikimej(f) {
            //        var url = '${pageContext.request.contextPath}/pembangunan/muat_naik_imej?uploadDoc';
//            $.blockUI({
//                message: $('#muatnaik'),
//                css: {
//                    //                    top:  ($(window).height() - 50) /2 + 'px',
//                    //                    left: ($(window).width() - 50) /2 + 'px',
//                    top:  ($(window).height() - 400) /2 + 'px',
//                    left: ($(window).width() - 400) /2 + 'px',
//                    width: '50px'
//                }
//            });

//            var url = '${pageContext.request.contextPath}/pembangunan/muat_naik_imej?reload';
//            $.get(url,
//            function(data){
//                $('#page_div').html(data);
//            },'html');

            var left = (screen.width/2)-(1000/2);
            var top = (screen.height/2)-(150/2);
            var url = '${pageContext.request.contextPath}/pembangunan/muat_naik_imej?uploadDoc';
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=300, left=" + left + ",top=" + top);
        }

        function simpanImej(event,f){

            //            var id = '${actionBean.permohonan.idPermohonan}';
            //            var f = '${pageContext.request.contextPath}/pembangunan/muat_naik_imej';
            //            alert("simpan imej"+f);
            //            var q = $(f).formSerialize();
            //            var url = f.action + '?muatNaik';
            //            $.post(url,q,
            //            function(data){
            //                $('#page_div',document).html(data);
            //                //            self.close();
            //                $.unblockUI();
            //            },'html');
            alert(event);
            alert("f:"+f);
            try{
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                alert(url);
                $.post(url,q,
                function(data){
                    $('#page_div',this.document).html(data);
                    $.unblockUI();
                    //                    $('.blockUI').click($.unblockUI).attr('title','Click to close');
                },'html');
            }catch(e){
                alert(e);
            }
        }

        function tutupImej(){
            $.unblockUI();
        }

        function hapusimej(idDokumen){
            if((confirm("Anda pasti ingin menghapuskan imej ini?"))){
                var url = '${pageContext.request.contextPath}/pembangunan/muat_naik_imej?hapusImej&idDokumen='+idDokumen;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
          
            }

        }


        $(document).ready(function(){

            $('#testForm').load('ajax/test.html', function() {
                var id = '${actionBean.msg}';
                var r = '${actionBean.laporanTanah}';
                //                alert(r);
                if(${actionBean.laporanTanah eq null}){
                    alert(id);
                    //                    $.blockUI({ message:$('#testForm'+${actionBean.msg})
                    //                        .animate({
                    //                            marginTop: '0px', /* The next 4 lines will vertically align this image */
                    //                            marginLeft: '0px',
                    //                            top: '50%',
                    //                            left: '80%',
                    //                            width: '600px', /* Set new width 174 */
                    //                            height: '400px', /* Set new height */
                    //                            padding: '0px'
                    //                        }, 2000),
                    //                        css: {
                    //                            top:  ($(window).height() - 400) /2 + 'px',
                    //                            left: ($(window).width() - 600) /2 + 'px',
                    //                            width: '600px',
                    //                            height: '400px'
                    //                        }
                    //                    });
                    //                    $.blockUI({ message:$(${actionBean.msg})
                    //                        .animate({
                    //                            marginTop: '0px', /* The next 4 lines will vertically align this image */
                    //                            marginLeft: '0px',
                    //                            top: '50%',
                    //                            left: '80%',
                    //                            width: '600px', /* Set new width 174 */
                    //                            height: '400px', /* Set new height */
                    //                            padding: '0px'
                    //                        }, 2000),
                    //                        css: {
                    //                            top:  ($(window).height() - 400) /2 + 'px',
                    //                            left: ($(window).width() - 600) /2 + 'px',
                    //                            width: '600px',
                    //                            height: '400px'
                    //                        }
                    //                    });
                    //                    $('.blockUI').click($.unblockUI).attr('title','Click to close');
                }

            });

            $('#p').click( function(){
                $('#folder').click();
            });
            $("img[title]").tooltip({
                // tweak the position
                //offset: [10, 2],

                // use the "slide" effect
                position:'left',
                effect: 'slide'
            }).dynamic({ bottom: { direction: 'left' } });

            $("#t[title]").tooltip({
                position:'left',
                effect: 'slide'
            }).dynamic({ bottom: { direction: 'left' } });


            $('.editable').each(function(i) {
                $(this).dblclick(function() {
                    $(this).html('<input id="'+i+'" type=text onblur=\'saveInput(this.value, this);\'/>');
                });
            });

            $('#f1').click(function(){
                if($('#f1').hasClass('open')){
                    $('#row1').slideUp('normal');
                    $('#f11').attr('src', '${pageContext.request.contextPath}/pub/images/folder.gif');
                    $('#f1').removeClass('open');
                    $('#f1').addClass('close');
                }else if($('#f1').hasClass('close')){
                    $('#row1').slideDown('normal');
                    $('#f11').attr('src', '${pageContext.request.contextPath}/pub/images/folderopen.gif');
                    $('#f1').removeClass('close');
                    $('#f1').addClass('open');
                }
            });
            $('#f11').click(function(){
                if($('#f11').hasClass('open')){
                    $('#row1').slideUp('normal');
                    $('#f11').attr('src', '${pageContext.request.contextPath}/pub/images/folder.gif');
                    $('#f11').removeClass('open');
                    $('#f11').addClass('close');
                }else if($('#f11').hasClass('close')){
                    $('#row1').slideDown('normal');
                    $('#f11').attr('src', '${pageContext.request.contextPath}/pub/images/folderopen.gif');
                    $('#f11').removeClass('close');
                    $('#f11').addClass('open');
                }
            });

            $('#f2').click(function(){
                if($('#f2').hasClass('open')){
                    $('#row2').slideUp('normal');
                    $('#f2').attr('src', '${pageContext.request.contextPath}/pub/images/folder.gif');
                    $('#f2').removeClass('open');
                    $('#f2').addClass('close');
                }else if($('#f2').hasClass('close')){
                    $('#row2').slideDown('normal');
                    $('#f2').attr('src', '${pageContext.request.contextPath}/pub/images/folderopen.gif');
                    $('#f2').removeClass('close');
                    $('#f2').addClass('open');
                }
            });

        });

        function popImej(idDok){
            var id = $("#idmohon").val();
            //            alert("idDOk : " +idDok+ "idPermohonan : "+id);
            var url = '${pageContext.request.contextPath}/pembangunan/muat_naik_imej?viewImejPopup&idPermohonan='+id+'&idDokumen='+idDok;
            //            $("#img"+idDok).click(function(){
            //                $.blockUI({
            //                    message: $('#img'+idDok),
            //                    css: {
            //                        top:  ($(window).height() - 400) /2 + 'px',
            //                        left: ($(window).width() - 400) /2 + 'px',
            //                        width: '400px'
            //                    }
            //                });
            //
            //                setTimeout($.unblockUI, 2000);
            //            });
            //
            $.post(url,                     
            function(data){               
                $.blockUI({ message:$('#img2'+idDok)
                    .animate({
                        marginTop: '0px', /* The next 4 lines will vertically align this image */
                        marginLeft: '0px',
                        top: '50%',
                        left: '80%',
                        width: '600px', /* Set new width 174 */
                        height: '400px', /* Set new height */
                        padding: '0px'
                    }, 2000),
                    css: {
                        top:  ($(window).height() - 400) /2 + 'px',
                        left: ($(window).width() - 600) /2 + 'px',
                        width: '600px',
                        height: '400px'
                    }
                });
                $('.blockUI').click($.unblockUI).attr('title','Click to close');
                       });
            //            $("#tutup").click(function(){
            //                $.blockUI();
            //                $('.blockUI').click($.unblockUI).attr('title','Click to close');
            //
            //            });

            //            window.open("${pageContext.request.contextPath}/pembangunan/muat_naik_imej?viewImejPopup&idPermohonan="+id+"&idDokumen="+idDok, "eTanah",
            //            "status=0,toolbar=0,location=center,menubar=0,width=600,height=805");
        }

        $("ul.thumb li").hover(function() {
            $(this).css({'z-index' : '10'}); /*Add a higher z-index value so this image stays on top*/
            $(this).find('img').addClass("hover").stop() /* Add class of "hover", then stop animation queue buildup*/
            .animate({
                marginTop: '-110px', /* The next 4 lines will vertically align this image */
                marginLeft: '-110px',
                top: '50%',
                left: '50%',
                width: '374px', /* Set new width 174 */
                height: '374px', /* Set new height */
                padding: '20px'
            }, 200); /* this value of "200" is the speed of how fast/slow this hover animates */

        } , function() {
            $(this).css({'z-index' : '0'}); /* Set z-index back to 0 */
            $(this).find('img').removeClass("hover").stop()  /* Remove the "hover" class , then stop animation queue buildup*/
            .animate({
                marginTop: '0', /* Set alignment back to default */
                marginLeft: '0',
                top: '0',
                left: '0',
                width: '100px', /* Set width back to default */
                height: '100px', /* Set height back to default */
                padding: '5px'
            }, 400);
        });

</script>


<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form id="testForm" name="myform" beanclass="etanah.view.stripes.pembangunan.MuatNaikImejActionBean">
    <s:hidden name="permohonan.idPermohonan" id="idmohon"/>
    <s:hidden name="imageFolderPath" id="lokasi"/>
    <s:messages/>
    <s:errors/>
    <c:if test="${cheackingLT}">
        <div class="subtitle">
            <c:set var="filePath"  value="${actionBean.imageFolderPath}" />
            <fieldset class="aras1">
                <legend>Muat naik Imej Laporan</legend><br>
                <%
                            String file = "C:/" + (String) pageContext.getAttribute("filePath");
                            File f = new File(file);
                            String[] fileNames = f.list();
                            File[] fileObjects = f.listFiles();

                %>
                <table border="1" align="center" class="tablecloth">
                    <tr>
                        <th>Bil.</th><th>Imej</th><th>Catatan</th><th>Hapus Imej</th>
                    </tr>
                    <c:choose>
                        <c:when test="${fn:length(actionBean.senaraiImejLaporan) > 0}">
                            <c:forEach items="${actionBean.senaraiImejLaporan}" var="item" varStatus="loop">
                                <%--<font color="black" size="3">${loop.count} . ${item.dokumen.kodDokumen.nama} </font><br/>
                                <c:if test="${loop.count mod 8 eq 0}">--%>
                                <tr>
                                    <td>
                                        ${loop.count})
                                    </td>
                                    <%--</c:if>--%>
                                    <td valign="top">
                                        <div style="display: none">
                                            <img  id="img2${item.dokumen.idDokumen}" alt="${item.dokumen.namaFizikal}" align="center" src="${pageContext.request.contextPath}/dokumen/view/${item.dokumen.idDokumen}" height="600" width="400" data-plusimage="${pageContext.request.contextPath}/dokumen/view/${item.dokumen.idDokumen}" data-plussize="400,300" />
                                        </div>
                                        <br>
                                        <ul class="thumb">
                                            <li>
                                                <img id="img${item.dokumen.idDokumen}" alt="${item.dokumen.namaFizikal}" align="center" src="${pageContext.request.contextPath}/dokumen/view/${item.dokumen.idDokumen}" height="100" width="100" data-plusimage="${pageContext.request.contextPath}/dokumen/view/${item.dokumen.idDokumen}" data-plussize="400,300" onclick="popImej('${item.dokumen.idDokumen}')" onmouseover="this.style.cursor='pointer';"/><%-- onclick="doSubmit(this.form);" --%>
                                            </li>
                                        </ul>
                                        <br>
                                    </td>
                                    <td valign="top" width="600">
                                        <br>
                                        ${item.catatan}
                                    </td>
                                    <td valign="top">
                                        <br>
                                        <%--<s:button name="close" value="Hapus Imej" onclick="hapusimej(this.name, this.form,'${item.idDokumen}');"/><br/>--%>
                                        <s:button name="hapusImej" value="Hapus" class="btn"onclick="hapusimej('${item.dokumen.idDokumen}');"/><br/>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="4"><font color="red">Tiada Imej Imbasan.</font></td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    <tr>
                        <td colspan="4">
                            <s:button name="muatnaikimej" value="Muat naik imej" class="longbtn"  onclick="muatNaikimej(this.form);return false;" />
                        </td>
                    </tr>
                </table>

            </fieldset>
        </div>
    </c:if>
</s:form>
<div id="muatnaik" style="display: none">
    <s:form name="myform1" beanclass="etanah.view.stripes.pembangunan.MuatNaikImejActionBean">
        <fieldset class="aras1">
            <div id="error"/>
            <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>

            <s:hidden name="folderId" value="${folderId}"/>
            <s:hidden name="dokumenId" value="${dokumenId}"/>
            <s:hidden name="idPermohonan" value="${idPermohonan}"/>
            <s:hidden name="lokasi" value="${lokasi}"/>
            <legend>Muat Naik</legend>
            <table class="tablecloth">
                <tr>
                    <th>
                        Catatan :
                    </th>
                    <td>:</td>
                    <td>
                        <s:textarea name="catatan" cols="50" rows="5"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <s:file name="fileToBeUpload"/>
                        <c:if test="${actionBean.fileToBeUpload ne null}">
                            ${actionBean.fileToBeUpload.fileName}
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <s:button name="muatNaik" id="muatNaik" value="Simpan" class="longbtn" onclick="simpanImej(this.name, this.form);"/>
                        <s:button name="close" value="Tutup" onclick="tutupImej();" class="btn"/>
                    </td>
                </tr>

            </table>
        </fieldset>
    </s:form>
</div>

