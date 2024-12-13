<%-- 
    Document   : imejTerdahulu
    Created on : December 13, 2011, 12:56:29 PM
    Author     : massita
--%>

<%@ page import="java.io.*,etanah.Configuration" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">


    function validateForm()
    {
        if((document.myform.simpanjln.checked == true)&&(document.myform.simpanlaluan.checked == true))
        {

            simpanjln.checked = true;
            simpanlaluan.checked =true;

        }
        return true;
    }

    function refreshPage(){
        var url = '${pageContext.request.contextPath}/pengambilan/ImejTerdahulu?reload';
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
<s:form name="myform" beanclass="etanah.view.stripes.pengambilan.ImejPengambilanTerdahuluActionBean">
    <s:hidden name="idPermohonan" id="idmohon"/>
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Imej Laporan Tanah Terdahulu</legend><br>
            <br>
            <p>
            <br>

            <table border="0" cellspacing="20" align="center">
                <c:choose>
                    <c:when test="${fn:length(actionBean.senaraiImejSiasatan) > 0}">
                        <c:forEach items="${actionBean.senaraiImejSiasatan}" var="item" varStatus="loop">
                            <c:if test="${loop.count mod 8 eq 0}">
                                <tr>
                                </c:if>
                                <td valign="top">
                                    <img alt="Imej" src="${pageContext.request.contextPath}/dokumen/view/${item.dokumen.idDokumen}" height="100" width="105" data-plusimage="${pageContext.request.contextPath}/dokumen/view/${item.dokumen.idDokumen}" data-plussize="400,300"/>
                                    <br>
                                    ${item.catatan}
                                </td>

                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                        <tr>
                            <td><font color="red">Tiada Imej</font></td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </table>

        </fieldset>
    </div>

</s:form>

