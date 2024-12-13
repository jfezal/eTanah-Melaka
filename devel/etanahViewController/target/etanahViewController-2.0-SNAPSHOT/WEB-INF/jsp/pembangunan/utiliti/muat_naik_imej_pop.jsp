<%-- 
    Document   : muat_naik_imej_pop
    Created on : Nov 4, 2011, 3:38:46 PM
    Author     : syaiful
--%>
<%@ page import="java.io.*,etanah.Configuration" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
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
            var url = '${pageContext.request.contextPath}/utiliti/laporanTanah?uploadDoc&idPermohonan=' + idPermohonan+'&lokasi=' + lokasi;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
        }

        function refreshPage(){
            var url = '${pageContext.request.contextPath}/utiliti/laporanTanah?reload';
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
            var url = '${pageContext.request.contextPath}/utiliti/laporanTanah?uploadDoc';
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=300, left=" + left + ",top=" + top);
        }
        function hapusimej(idDokumen){
            if((confirm("Anda pasti ingin menghapuskan imej ini?"))){
                var url = '${pageContext.request.contextPath}/utiliti/laporanTanah?hapusImej&idDokumen='+idDokumen;
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

<s:form name="myform" beanclass="etanah.view.stripes.pembangunan.utiliti.UtilitiLtActionBean">
    <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.idPermohonan}"/>
    <s:hidden name="imageFolderPath" id="lokasi"/>
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <c:set var="filePath"  value="${actionBean.imageFolderPath}" />
        <fieldset class="aras1">
            <legend>Paparan Imej Laporan</legend><br>
            <%
                        String file = "C:/" + (String) pageContext.getAttribute("filePath");
                        File f = new File(file);
                        String[] fileNames = f.list();
                        File[] fileObjects = f.listFiles();

            %>
            <table border="0" align="center" class="tablecloth">
                <tr>
                    <%--</c:if>--%>
                    <td valign="top" colspan="2" align="center">
                        <br>
                        <%--<s:button name="close" value="Hapus Imej" onclick="hapusimej(this.name, this.form,'${item.idDokumen}');"/><br/>--%>
                        <img align="center" alt="${actionBean.imejLaporanUP.dokumen.namaFizikal}" src="${pageContext.request.contextPath}/dokumen/view/ ${actionBean.imejLaporanUP.dokumen.idDokumen}" height="500" width="505" data-plusimage="${pageContext.request.contextPath}/dokumen/view/${actionBean.imejLaporanUP.dokumen.idDokumen}" data-plussize="400,300"/>
                        <br>
                    </td>
                </tr>
                <tr>
                    <th>
                        Catatan :
                    </th>
                </tr>
                <tr>
                    <td>${actionBean.imejLaporanUP.catatan}</td>
                </tr>
                <tr>  <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close().fadeOut(2000)"/></tr>
            </table>

        </fieldset>
    </div>
</s:form>
