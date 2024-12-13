<%--
    Document   : maklumat_Bangunan
    Created on : Sep 21, 2010, 11:57:33 AM
    Author     : Muralikrishna
--%>
<%@ page import="java.io.*,etanah.Configuration" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
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
            var url = '${pageContext.request.contextPath}/strata/permohonanStrata?uploadDoc&idPermohonan=' + idPermohonan+'&lokasi=' + lokasi;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
        }

        function refreshPage(){
            var url = '${pageContext.request.contextPath}/strata/maklumat_bangunan?reload';
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
        function muatNaikimej() {

            var left = (screen.width/2)-(1000/2);
            var top = (screen.height/2)-(150/2);
            var url = '${pageContext.request.contextPath}/strata/maklumat_bangunan?uploadDoc';
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=300, left=" + left + ",top=" + top);
        }
        function hapusimej(idDokumen){
            if((confirm("Anda pasti ingin menghapuskan imej ini?"))){
                var url = '${pageContext.request.contextPath}/strata/maklumat_bangunan?hapusImej&idDokumen='+idDokumen;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
          
            }

        }


        $(document).ready(function(){

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



</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form name="myform" beanclass="etanah.view.strata.MaklumatBangunanActionBean">
    <s:hidden name="idPermohonan" id="idmohon"/>
    <s:hidden name="imageFolderPath" id="lokasi"/>
    <s:messages/>
    <s:errors/>
    
    <div class="subtitle">
        <c:set var="filePath"  value="${actionBean.imageFolderPath}" />       
        <fieldset class="aras1">
            <legend>Muat Naik Imej Laporan</legend>
            <c:if test="${edit}">
            <p>
                <font color="red">*</font> Sila klik 'Muat Naik Imej' untuk muat naik imej <br>
                <font color="red">*</font> Klik 'Hapus Imej' untuk hapus imej
            </p><br>            
            <p>
                <s:button name="muatnaikimej" value="Muat Naik Imej" class="longbtn"  onclick="muatNaikimej();" /></p>
            </c:if>
            <br>
            <%
                        String file = "C:/" + (String) pageContext.getAttribute("filePath");
                        File f = new File(file);
                        String[] fileNames = f.list();
                        File[] fileObjects = f.listFiles();
            %>
            <table cellspacing="20" align="center">
                <c:choose>
                    <c:when test="${fn:length(actionBean.senaraiImejLaporan) > 0}">
                        <c:forEach items="${actionBean.senaraiImejLaporan}" var="item" varStatus="loop">
                            <%--<font color="black" size="3">${loop.count} . ${item.dokumen.kodDokumen.nama} </font><br/>--%>
                            <c:if test="${loop.count mod 8 eq 0}">
                                <tr >
                                </c:if>
                                    <td>
                                    <c:if test="${edit}">
                                    <s:button name="hapusImej" value="Hapus Imej" class="btn"onclick="hapusimej('${item.dokumen.idDokumen}');"/><br/>
                                    </c:if>
                                    <img alt="Imej" src="${pageContext.request.contextPath}/dokumen/view/${item.dokumen.idDokumen}" height="110" width="109" data-plusimage="${pageContext.request.contextPath}/dokumen/view/${item.dokumen.idDokumen}" />
                                    <br>
                                    ${item.catatan}
                                </td>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                        <tr>
                            <td><font color="red">Tiada Imej Imbasan.</font></td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </table>
        </fieldset>
    </div>
</s:form>
