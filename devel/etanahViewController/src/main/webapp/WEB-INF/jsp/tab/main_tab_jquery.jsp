<%--
    Document   : main_tab_jquery
    Created on : 17-Sep-2009, 09:52:13
    Author     : md.nurfikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<%--<link rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/jquery.tooltip.css" />--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.bgiframe.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.delegate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.dimensions.js"></script>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.7.3.custom.min.js"></script>--%>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.tooltip.js"></script>--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.tabs.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/formatCurrency.js"></script>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>--%>
<script type="text/javascript">    
    $(document).ready(function() {       
       $(".datepicker").datepicker({dateFormat: 'dd/mm/yy',
           changeMonth: true,
	   changeYear: true});
        var $tabs  = $("div.tabs").tabs();       

        $("#tabs a[title]").tooltip({

           // tweak the position
           offset: [10, 2],

           // use the "slide" effect
           effect: 'slide'

        // add dynamic plugin with optional configuration for bottom edge
        }).dynamic({ bottom: { direction: 'down', bounce: true } });
        $('font[title]').tooltip();
        //$('#main').tooltip({delay: 0});
        //$('#folder_div').tooltip({delay: 0});
        //$('#status_div').tooltip({delay: 0});       
      
        $('#displayBox').hide();
           
        $tabs.tabs('select',${actionBean.selectedMainTab});
        var selected;

            $("#urusan").click(function() {
                selected = $tabs.tabs("option", "selected");
                $("#folder_div").hide();
                $("#status_div").hide();
                $("#main").show();

                <c:if test="${viewOnly}">
                            $("#page_div input:button").hide();
                            $("#page_div input:submit").hide();
                            $("#page_div input:text").attr('readonly','true');
                            $("#page_div textarea").attr('readonly','true');
                            $("#page_div input:checkbox").attr('disabled','true');
                </c:if>
                $('#page_id_1').click();//by default page @ 1 will be click                
            });

            $("#folder").click(function() {                
                $("#folder_div").html('');
                 $.blockUI({
                message: $('#displayBox'),
                    css: {
                        top:  ($(window).height() - 50) /2 + 'px',
                        left: ($(window).width() - 50) /2 + 'px',
                        width: '50px'
                    }
                });
                selected = $tabs.tabs("option", "selected");
                $("#folder_div").show();
                $("#main").hide();
                $("#status_div").hide();                
                var url = '?mainTab&selectedTab=0&mainId=2&txnCode=${actionBean.txnCode}&stageId=${actionBean.stageId}&idPermohonan=${actionBean.idPermohonan}';
                $.ajax({
                    type:"GET",
                       url : url,
                       dataType : 'html',
                       error : function(xhr, ajaxOptions, thrownError) {
                            //alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');                            
                            $("#folder_div").html('<div class=errors>Terdapat Masalah Teknikal. Sila Hubungi Pentadbir Sistem.</div><div id=t><a href="#">[ Lihat Terperinci ]</a></div><br/><div id=error class=b>' + xhr.responseText + '</div>');
                            $.unblockUI();
                        },
                       success : function(data) {
                            $("#folder_div").html(data);
                            $("#folder_div input:text").each( function(el) {
                                $(this).blur( function() {
                                    $(this).val( $(this).val().toUpperCase());
                                });
                            });
                            <c:if test="${viewOnly}">
                                $("#folder_div input:button").hide();
                                $("#folder_div input:submit").hide();
                                $("#folder_div input:text").attr('readonly','true');
                                $("#folder_div textarea").attr('readonly','true');
                                $("#folder_div input:checkbox").attr('disabled','true');
                            </c:if>
                            $.unblockUI();
                       }
                });

            });
            $("#status").click(function() {
                $("#status_div").html('');
              $.blockUI({
                message: $('#displayBox'),
                    css: {
                        top:  ($(window).height() - 50) /2 + 'px',
                        left: ($(window).width() - 50) /2 + 'px',
                        width: '50px'
                    }
                });
                selected = $tabs.tabs("option", "selected");
                $("#status_div").show();
                $("#folder_div").hide();
                $("#main").hide();               
                  var url = '${pageContext.request.contextPath}/keputusan?stageId=${actionBean.stageId}&berangkai=${berangkai}&generateReport=${actionBean.report}&size_berangkai=${size_berangkai}';
                  $.ajax({
                       type:"POST",
                       url : url,
                       dataType : 'html',
                       error : function(xhr, ajaxOptions, thrownError) {
                            //alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                            <%--$("#status_div").html(xhr.responseText);--%>
                            $("#status_div").html('<div class=errors>Terdapat Masalah Teknikal. Sila Hubungi Pentadbir Sistem.</div><div id=t><a href="#">[ Lihat Terperinci ]</a></div><br/><div id=error class=b>' + xhr.responseText + '</div>');
                            $.unblockUI();
                        },
                       success : function(data) {
                            $("#status_div").html(data);
                            $("#status_div textarea").each( function(el) {
                                $(this).focus(function(){
                                    $(this).addClass('focus');
                                });
                                $(this).blur( function() {
                                    $(this).removeClass('focus');
                                    $(this).val( $(this).val().toUpperCase());
                                });
                            });
                            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy',
                                   changeMonth: true,
                                   changeYear: true});
                            <c:if test="${viewOnly}">
                                            $("#status_div input:button").hide();
                                            $("#status_div input:text").attr('readonly','true');
                                            $("#status_div textarea").attr('readonly','true');
                                            $("#status_div input:radio").attr('disabled','true');
                            </c:if>
                            $.unblockUI();
                       }
                  });
            });

    <c:if test="${actionBean.selectedMainTab eq 0}">
            $("#folder_div").hide();
            $("#status_div").hide();
    </c:if>
    <c:if test="${actionBean.selectedMainTab eq 1}">
            $("#main").hide();
            $("#status_div").hide();
    </c:if>
    <c:if test="${actionBean.selectedMainTab eq 2}">
            $("#main").hide();
            $("#folder_div").hide();
    </c:if>
        });

            function doSubmit(frm, event, id){
                doBlockUI();
                var queryString = $(frm).formSerialize();                
                  var url = frm.action + '?' + event;
                  $.ajax({
                       type:"POST",
                       url : url,
                       dataType : 'html',
                       data : queryString,
                       error : function(xhr, ajaxOptions, thrownError) {
                            //alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                            $("#" + id).html('<div class=errors>Terdapat Masalah Teknikal. Sila Hubungi Pentadbir Sistem.</div><div id=t></div><br/><div id=error class=b>' + xhr.responseText + '</div>');
                            doUnBlockUI();
                        },
                       success : function(data) {
                           $('#'+id).html(data);
                           $("#" + id +" textarea").each( function(el) {
                                if(!$(this).hasClass('normal_text')){
                                    $(this).blur( function() {
                                        $(this).val( $(this).val().toUpperCase());
                                    });
                                }
                            });
                            $('.popup').each(function(){
                                 $(this).html('<u>' + $(this).text() + '</u>');
                                 $(this).mouseover(function(){
                                    $(this).addClass("cursor_pointer");
                                 });
                            });
                            $('#'+id+' table.tablecloth thead a').each( function() {
                                 var url = $(this).attr('href');
                                 $(this).attr("href", "javascript:doSortPaging('"+url+"');");
                            });
                            $('#'+id+'.pagelinks a').each( function() {
                                 var url = $(this).attr('href');
                                  $(this).attr("href", "javascript:doSortPaging('"+url+"');");
                             });                           
                            $("#"+id+" input:text").each( function(el) {
                                  if(!$(this).hasClass('normal_text')){
                                      $(this).focus(function(){
                                          $(this).addClass('focus');
                                      });
                                    $(this).blur( function() {
                                        $(this).removeClass('focus');
                                        $(this).val( $(this).val().toUpperCase());
                                    });
                                }
                           });
                           
                           $('table.tablecloth thead a').each( function() {
                                var url = $(this).attr('href');
                                $(this).attr("href", "javascript:doSortPaging('"+url+"');");

                            });
                            $('.pagelinks a').each( function() {
                                var url = $(this).attr('href');
                                $(this).attr("href", "javascript:doSortPaging('"+url+"');");
                            });

                           $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                           doUnBlockUI();
                       }
                  });
                //setTimeout($.unblockUI, 1000);
            }

            function convert(val, id){            
                var amaun = CurrencyFormatted(val);               
                amaun = CommaFormatted(amaun);                
                $('#'+id).val(amaun);
            }

            function doBlockUI () {
                $.blockUI({
                    message: $('#displayBox'),
                    css: {
                        top:  ($(window).height() - 50) /2 + 'px',
                        left: ($(window).width() - 50) /2 + 'px',
                        width: '50px'
                    }
                });
            }

            function doUnBlockUI (){
                $.unblockUI();
            }

</script>
<style type="text/css">
           <%-- .tooltip {
                display:none;
                background:transparent url(${pageContext.request.contextPath}/pub/images/black_arrow.png);
                font-size:12px;
                height:70px;
                width:160px;
                padding:25px;
                color:#fff;
                }--%>

           .tooltip {
               background-color:#000;
               border:1px solid #fff;
               padding:10px 15px;
               width:200px;
               display:none;
               color:#fff;
               text-align:left;
               font-size:12px;

               /* outline radius for mozilla/firefox only */
               -moz-box-shadow:0 0 10px #000;
               -webkit-box-shadow:0 0 10px #000;
           }
</style>
<s:messages/>
<div id="dialog_msg" title=".: e-Tanah :." style="display: none">
    <p id="msg"/>
</div>
<div style="background-color:${actionBean.warnaModul}" align="center">
    <c:set var="font" value="grey"/>
    <c:if test="${actionBean.warnaModul eq '#00FFFF' || actionBean.warnaModul eq 'green' || actionBean.warnaModul eq 'pink'}">
        <c:set var="font" value="black"/>
    </c:if>
     <c:if test="${actionBean.warnaModul eq 'purple' || actionBean.warnaModul eq 'magenta' || actionBean.warnaModul eq 'maroon' || actionBean.warnaModul eq 'blue'}">
        <c:set var="font" value="white"/>
    </c:if>  
    <font style="font-family: Tahoma;font-size:16px;
          font-weight: bold" color="${font}" title="${actionBean.idPermohonan} - <br/>${actionBean.namaUrusan} <br/> Tarikh Perserahan: ${actionBean.trhPerserahan}">
        ${actionBean.idPermohonan} : ${actionBean.namaUrusan}
    </font>
</div>

<c:if test="${!empty actionBean.mesej}">
<br/>
    <div class="errors">${actionBean.mesej}</div>
<br/>
</c:if>

<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<div class="tabs" id="tabs">
    <ul>
        <li><a href="#main" id="urusan" title="Kemasukan Data untuk Permohonan/Perserahan ini.">
                Urusan</a></li>
        <li>
            <a href="#folder_div" id="folder" title="Senarai Dokumen yang terlibat untuk Permohonan/Perserahan ini.">

               <%-- <img src="${pageContext.request.contextPath}/pub/images/icons/attachment-icon.png"
                                   onclick="doViewReport('${row2.idDokumen}');" height="15" width="15" alt="papar"
                                   onmouseover="this.style.cursor='pointer';"/>--%>Dokumen
            </a></li>
         <c:if test="${actionBean.tabBean.isOutcomeView}">
            <li><a href="#status_div" id="status" title="${actionBean.tabBean.keputusanTitle}">${actionBean.tabBean.keputusanTitle}</a></li>
         </c:if>
    </ul>
    <div id="folder_div" class="pane"></div>
    <div id="status_div" class="pane"></div>
</div>
<div id="main">
    <c:if test="${actionBean.secondTab}">
        <%@include file="/WEB-INF/jsp/tab/second_tab_jquery.jsp" %>
    </c:if>
</div>
<div class="tooltip">aaa</div>
