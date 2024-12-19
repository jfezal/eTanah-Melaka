<%--
    Document   : second_tab_jquery
    Created on : 17-Sep-2009, 10:06:30
    Author     : md.nurfikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet"
      href="pub/styles/tabNavList.css"/>
<style type="text/css">
    .cursor_pointer {
        cursor:pointer;
    }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript">

    $(document).ready(function() {        
        $("#second_tabs").tabs();        
        
        //$('#second_tabs a[title]').tooltip();        
        $('#second_tabs a[title]').tooltip({
            // tweak the position
               offset: [10, 2],

               // use the "slide" effect
               effect: 'slide'
            }).dynamic({ bottom: { direction: 'down', bounce: true } });

          $('#suratKuasa').click (function(){
              doHalfTitle();
              $("#page_div").html("");
              $(this).text('Tentukan Surat');
                    $.blockUI({
                        message: $('#displayBox'),
                        css: {
                            top:  ($(window).height() - 50) /2 + 'px',
                            left: ($(window).width() - 50) /2 + 'px',
                            width: '50px'
                        }
              });
              var url = '${pageContext.request.contextPath}/daftar/tentukanHakmilik?tentukanHakmilik';

              $.ajax({
                   type:"POST",
                    url : url,
                    dataType : 'html',
                    error : function(xhr, ajaxOptions, thrownError) {
                        //alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                        $("#page_div").html('<div class=errors>Terdapat Masalah Teknikal. Sila Hubungi Pentadbir Sistem.</div><div id=t><a href="#">[ Lihat Terperinci ]</a></div><br/><div id=error class=b>' + xhr.responseText + '</div>');
                        $.unblockUI();
                    },
                    success : function(data) {
                        $("#page_div").html(data);
                            $('.alphanumeric').alphanumeric();
                            <c:if test="${viewOnly}">
                                                $('input:button').hide();
                                                $('input:text').attr('disabled','true');
                                                $('textarea').attr('disabled','true');
                                                $('input:checkbox').attr('disabled','true');
                                                $('input:radio').attr('disabled','true');
                                                $('.rem').remove();
                                                $('.popup_edit').remove();
                            </c:if>
                            $('.popup').each(function(){
                                $(this).html('<u>' + $(this).text() + '</u>');
                                $(this).mouseover(function(){
                                    $(this).addClass("cursor_pointer");
                                });
                            });
                            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy',
                                   changeMonth: true,
                                   changeYear: true});
                            //$(".date_picker").datepicker({dateFormat: 'dd/mm/yy'});
                            $('table.tablecloth thead a').each( function() {
                                var url = $(this).attr('href');
                                $(this).attr("href", "javascript:doSortPaging('"+url+"');");

                            });
                            $('.pagelinks a').each( function() {
                                var url = $(this).attr('href');
                                $(this).attr("href", "javascript:doSortPaging('"+url+"');");
                            });

                            $("#page_div input:text").each( function(el) {
                                if(!$(this).hasClass('normal_text')){
                                    $(this).focus(function () {
                                        $(this).addClass("focus");
                                    } );
                                    $(this).blur( function() {
                                        $(this).removeClass("focus");
                                        $(this).val( $(this).val().toUpperCase());
                                    });
                                }
                            });
                            $("#page_div textarea").each( function(el) {


                                if(!$(this).hasClass('normal_text')){
                                    $(this).focus(function () {
                                        $(this).addClass("focus");
                                        });
                                     $(this).blur( function() {
                                         $(this).removeClass("focus");
                                        $(this).val( $(this).val().toUpperCase());
                                    });
                                }
                            });
                            $.unblockUI();

                    } // close for success

              }); //close for ajax
          });
    <c:choose>
        <c:when test="${fn:length(actionBean.tabBean.defaultPage) > 0}">
                var url = '${pageContext.request.contextPath}/${actionBean.tabBean.currURL}';
                $.ajax({
                    type:"POST",
                    url : url,
                    dataType : 'html',
                    error : function(xhr, ajaxOptions, thrownError) {
                        //alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                        $("#page_div").html('<div class=errors>Terdapat Masalah Teknikal. Sila Hubungi Pentadbir Sistem.</div><div id=t><a href="#">[ Lihat Terperinci ]</a></div><br/><div id=error class=b>' + xhr.responseText + '</div>');
                        $.unblockUI();
                    },
                    success : function(data) {
                        $('#default').html(data);
                        $('.alphanumeric').alphanumeric();

                        <c:if test="${viewOnly}">
                            $('input:button').hide();
                            $('input:text').attr('disabled','true');
                            $('textarea').attr('disabled','true');
                            $('input:checkbox').attr('disabled','true');
                            $('input:radio').attr('disabled','true');
                            $('.rem').remove();
                            $('.popup_edit').remove();
                        </c:if>
                            $('.popup').each(function(){
                                $(this).html('<u>' + $(this).text() + '</u>');
                                $(this).mouseover(function(){
                                    $(this).addClass("cursor_pointer");
                                });
                            });
                            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy',
                                   changeMonth: true,
                                   changeYear: true});
                            //$(".date_picker").datepicker({dateFormat: 'dd/mm/yy'});
                            $('table.tablecloth thead a').each( function() {
                                var url = $(this).attr('href');
                                $(this).attr("href", "javascript:doSortPaging('"+url+"');");

                            });
                            $('.pagelinks a').each( function() {
                                var url = $(this).attr('href');
                                $(this).attr("href", "javascript:doSortPaging('"+url+"');");
                            });
                            $("#default input:text").each( function(el) {
                                $(this).blur( function() {
                                    $(this).val( $(this).val().toUpperCase());
                                });
                            });
                            $("#default textarea").each( function(el) {
                                $(this).blur( function() {
                                    $(this).val( $(this).val().toUpperCase());
                                });
                            });
                        }
                    });               
        </c:when>
        <c:otherwise>
                doBlockUI();
                var url = '${pageContext.request.contextPath}/${actionBean.tabBean.currURL}';                
                $.ajax({
                    type:"POST",
                    url : url,
                    dataType : 'html',
                    error : function(xhr, ajaxOptions, thrownError) {
                        //alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                        $("#page_div").html('<div class=errors>Terdapat Masalah Teknikal. Sila Hubungi Pentadbir Sistem.</div><div id=t><a href="#">[ Lihat Terperinci ]</a></div><br/><div id=error class=b>' + xhr.responseText + '</div>');
                        doUnBlockUI();
                    },
                    success : function(data) {
                        //alert($('#page_id_1').attr('rel'));
                        $('#page_id_1').text($('#page_id_1').attr('rel'));
                        $('#page_div').html(data);            
                            $('.alphanumeric').alphanumeric();
                        <c:if test="${viewOnly}">
                            $('input:button').hide();
                            $('input:text').attr('disabled','true');
                            $('textarea').attr('disabled','true');
                            $('input:checkbox').attr('disabled','true');
                            $('input:radio').attr('disabled','true');
                            $('.rem').remove();
                            $('.popup_edit').remove();
                        </c:if>
                            $('.popup').each(function(){
                                $(this).html('<u>' + $(this).text() + '</u>');
                                $(this).mouseover(function(){
                                    $(this).addClass("cursor_pointer");
                                });
                            });
                            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy',
                               changeMonth: true,
                               changeYear: true});
                            //$(".date_picker").datepicker({dateFormat: 'dd/mm/yy'});
                            $('table.tablecloth thead a').each( function() {
                                var url = $(this).attr('href');
                                $(this).attr("href", "javascript:doSortPaging('"+url+"');");

                            });
                            $('.pagelinks a').each( function() {
                                var url = $(this).attr('href');
                                $(this).attr("href", "javascript:doSortPaging('"+url+"');");
                            });
                            $("#page_div input:text").each( function(el) {
                                $(this).focus(function () {
                                    $(this).addClass("focus");
                                });
                                $(this).blur( function() {
                                   $(this).removeClass("focus");
                                });
                                if(!$(this).hasClass('normal_text')){                                    
                                    $(this).blur( function() {                                        
                                        $(this).val( $(this).val().toUpperCase());
                                    });
                                }
                            });
                            $("#page_div textarea").each( function(el) {
                                $(this).focus(function () {
                                    $(this).addClass("focus");
                                });
                                $(this).blur( function() {
                                     $(this).removeClass("focus");
                                });
                                if(!$(this).hasClass('normal_text')){                                   
                                $(this).blur( function() {                                   
                                    $(this).val( $(this).val().toUpperCase());
                                });
                                }
                            });
                            doUnBlockUI();
                        }
                    });               
        </c:otherwise>
    </c:choose>

    <c:forEach var="page" items="${actionBean.tabBean.page}">
        <c:set var="counter4" value="${counter4+1}"/>
                $("#page_id_${counter4}").click(function() {
                    doHalfTitle();
                    $(this).text('${page.title}');
                    $("#page_div").html("");
                    $.blockUI({
                        message: $('#displayBox'),
                        css: {
                            top:  ($(window).height() - 50) /2 + 'px',
                            left: ($(window).width() - 50) /2 + 'px',
                            width: '50px'
                        }
                    });
                    var url = '${pageContext.request.contextPath}/${page.url}';                    
                    var c = url + (url.indexOf('?')>0? '&' : '?');
                    $.ajax({
                        type:"POST",
                        url : c + 'idPermohonan=${actionBean.idPermohonan}',
                        dataType : 'html',
                        error : function(xhr, ajaxOptions, thrownError) {
                            $("#page_div").html('<div class=errors>Terdapat Masalah Teknikal. Sila Hubungi Pentadbir Sistem.</div><div id=t><a href="#">[ Lihat Terperinci ]</a></div><br/><div id=error class=b>' + xhr.responseText + '</div>');
                            $('#error').hide();
                            $('#t').click(function() {
                                if($('#error').hasClass('b')) {
                                    $('#error').slideDown('normal');
                                    $('#error').removeClass('b');
                                    $('#error').addClass('a');
                                } else if($('#error').hasClass('a')) {
                                    $('#error').slideUp('normal');
                                    $('#error').removeClass('a');
                                    $('#error').addClass('b');
                                }
                            });
                            $.unblockUI();
                        },
                        success : function(data) {
                            $("#page_div").html(data);
                            $('.alphanumeric').alphanumeric();
                            <c:if test="${viewOnly}">
                                                $('input:button').hide();
                                                $('input:text').attr('disabled','true');
                                                $('textarea').attr('disabled','true');
                                                $('input:checkbox').attr('disabled','true');
                                                $('input:radio').attr('disabled','true');
                                                $('.rem').remove();
                                                $('.popup_edit').remove();
                            </c:if>
                            $('.popup').each(function(){
                                $(this).html('<u>' + $(this).text() + '</u>');
                                $(this).mouseover(function(){
                                    $(this).addClass("cursor_pointer");
                                });
                            });
                            $(".datepicker").datepicker(
                                {
                                    dateFormat: 'dd/mm/yy',
                                    changeMonth: true,
                                    changeYear: true, 
                                    beforeShow: function(input, inst)
                                        {
                                            inst.dpDiv.css({marginTop: -input.offsetHeight + 'px', marginLeft: input.offsetWidth + 'px'});
                                        }
                                });
                            $('.datepicker').blur(function() {
                                   editDateBlur(this,'DD/MM/YYYY');                                   
                            });
                            $('.datepicker').keypress(function() {
                                return editDatePre(this, 'DD/MM/YYYY', event);
                            });
                            $('.datepicker').keyup(function () {
                                return editDate(this, 'DD/MM/YYYY', event);
                            });

                            //$(".date_picker").datepicker({dateFormat: 'dd/mm/yy'});
                            $('table.tablecloth thead a').each( function() {
                                var url = $(this).attr('href');
                                $(this).attr("href", "javascript:doSortPaging('"+url+"');");

                            });
                            $('.pagelinks a').each( function() {
                                var url = $(this).attr('href');
                                $(this).attr("href", "javascript:doSortPaging('"+url+"');");
                            });
                            
                            $("#page_div input:text").each( function(el) {
                                if(!$(this).hasClass('normal_text')){
                                    $(this).focus(function () {
                                        $(this).addClass("focus");
                                    } );
                                    $(this).blur( function() {
                                        $(this).removeClass("focus");
                                        $(this).val( $(this).val().toUpperCase());
                                    });
                                }
                            });
                            $("#page_div textarea").each( function(el) {
                               

                                if(!$(this).hasClass('normal_text')){
                                    $(this).focus(function () {
                                        $(this).addClass("focus");
                                        });
                                     $(this).blur( function() {
                                         $(this).removeClass("focus");
                                        $(this).val( $(this).val().toUpperCase());
                                    });
                                }
                            });
                            $.unblockUI();
                        }
                    });
                });
    </c:forEach>
    <c:forEach var="default" items="${actionBean.tabBean.defaultPage}">
        <c:set var="counter6" value="${counter6+1}"/>
                $("#default_id_${counter6}").click(function() {
                    $("#default").html("");
                    $.blockUI({
                        message: $('#displayBox'),
                        css: {
                            top:  ($(window).height() - 50) /2 + 'px',
                            left: ($(window).width() - 50) /2 + 'px',
                            width: '50px'
                        }
                    });
                    var url = '${pageContext.request.contextPath}/${page.url}';
                    var c = url + (url.indexOf('?')>0? '&' : '?');

                    $.ajax({
                        type:"POST",
                        url : c + 'idPermohonan=${actionBean.idPermohonan}',
                        dataType : 'html',
                        error : function(xhr, ajaxOptions, thrownError) {
                            //alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                            $("#page_div").html('<div class=errors>Terdapat Masalah Teknikal. Sila Hubungi Pentadbir Sistem.</div><div id=t><a href="#">[ Lihat Terperinci ]</a></div><br/><div id=error class=b>' + xhr.responseText + '</div>');
                            $.unblockUI();
                        },
                        success : function(data) {
                            $("#default").html(data);

                            $('.alphanumeric').alphanumeric();
                            <c:if test="${viewOnly}">
                                                $('input:button').hide();
                                                $('input:text').attr('disabled','true');
                                                $('textarea').attr('disabled','true');
                                                $('input:checkbox').attr('disabled','true');
                                                $('input:radio').attr('disabled','true');
                                                $('.rem').remove();
                                                $('.popup_edit').remove();
                            </c:if>
                            $('.popup').each(function(){
                                $(this).html('<u>' + $(this).text() + '</u>');
                                $(this).mouseover(function(){
                                    $(this).addClass("cursor_pointer");
                                });
                            });
                            $('#default table.tablecloth thead a').each( function() {
                                var url = $(this).attr('href');
                                $(this).attr("href", "javascript:doSortPaging('"+url+"');");

                            });
                            $('#default.pagelinks a').each( function() {
                                var url = $(this).attr('href');
                                $(this).attr("href", "javascript:doSortPaging('"+url+"');");
                            });
                            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy',
                               changeMonth: true,
                               changeYear: true});
                            //$(".date_picker").datepicker({dateFormat: 'dd/mm/yy'});
                            $("#page_div input:text").each( function(el) {
                                $(this).focus(function () {
                                   $(this).addClass("focus");
                                });
                                $(this).blur( function() {
                                   $(this).removeClass('focus');
                                });
                                if(!$(this).hasClass('normal_text')){                                    
                                    $(this).blur( function() {                                        
                                        $(this).val( $(this).val().toUpperCase());
                                    });
                                }
                            });
                            $("#page_div textarea").each( function(el) {
                                if(!$(this).hasClass('normal_text')){
                                    $(this).focus(function () {
                                        $(this).addClass("focus");
                                        });

                                     $(this).blur( function() {
                                        $(this).removeClass('focus');
                                        $(this).val( $(this).val().toUpperCase());
                                    });
                                }
                            });
                            $.unblockUI();
                        }
                    });
                });
    </c:forEach>
        });

        //function for displaytag function sorting and paging.
        // overwrite displaytag href. change to get data via ajax.

        function doSortPaging(url){                     
            $.get(url ,
            function(data){
                $('.displaytag').html(data);    
                $('.popup').each(function(){
                    $(this).html('<u>' + $(this).text() + '</u>');
                });
                $('table.tablecloth thead a').each( function() {
                    var url = $(this).attr('href');
                    $(this).attr("href", "javascript:doSortPaging('"+url+"');");

                });
                $('.pagelinks a').each( function() {
                    var url = $(this).attr('href');
                    $(this).attr("href", "javascript:doSortPaging('"+url+"');");
                });
            }
            ,'html');
        }

        function doHalfTitle(){
            <c:forEach var="p" items="${actionBean.tabBean.page}">                    
                <c:set value="${c+1}" var="c"/>
                        $('#page_id_${c}').text('${p.halfTitle}');
            </c:forEach>
            <c:if test="${actionBean.isSWexist}">
                $('#suratKuasa').text('Tentukan Surat..');
            </c:if>
        }

</script>
        
    <c:choose>        
        <c:when test="${(actionBean.permohonan.kodUrusan.jabatan.kod eq '6') && 
                        (actionBean.stageId eq 'pindaanagihan' || actionBean.stageId eq 'pindaanrencanajkbb' || actionBean.stageId eq 'semakpindaan')}">
            <center><div class="warning2" style="width:90%;text-align: left;margin-top: 10px;margin-bottom: 10px;color:#D8000C">Arahan : ${actionBean.tabBean.instruction}</div></center>
        </c:when>
        <c:otherwise>
        <center><div class="warning" style="width:90%;text-align: left;margin-top: 10px;margin-bottom: 10px;color: black"><b>Arahan : ${actionBean.tabBean.instruction}</b>
                <c:if test="${!empty actionBean.notifikasi && actionBean.notifikasi eq 'true'}">
            <br/><b>Urusan ini ada urusan integrasi. Jika tidak lengkap, sila gunakan hantar notifikasi untuk pemberitahuan kepada modul sebelum.</b>
        </c:if>
            </div></center>        
        </c:otherwise>
    </c:choose>
            
    
<div id="second_tabs">
    <ul>
        <c:forEach var="default" items="${actionBean.tabBean.defaultPage}">
            <c:set var="counter" value="${counter+1}"/>
            <li><a href="#default" id="default_id_${counter}">${default.title}</a></li>
        </c:forEach>        
        <c:forEach var="page" items="${actionBean.tabBean.page}">
            <c:set var="counter2" value="${counter2+1}"/>
            <li><a href="#page_div" id="page_id_${counter2}" title="${page.title}" rel="${page.title}">${page.halfTitle}</a></li>
        </c:forEach>
            
        <c:if test="${actionBean.isSWexist}">
            <li>
                <a href="#page_div" id="suratKuasa" title="Tentukan Surat">Tentukan Surat</a>
            </li>
        </c:if> 
            
        
    </ul>
    <div id="default"></div>
    <div id="page_div"></div>
</div>