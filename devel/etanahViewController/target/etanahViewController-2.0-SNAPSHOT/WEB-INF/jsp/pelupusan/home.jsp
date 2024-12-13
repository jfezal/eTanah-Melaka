<%--
    Document   : home
    Created on : 15-Sep-2009, 09:42:20
    Author     : md.nurfikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.: e-Tanah :.</title>
        <link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <script type="text/javascript">
            
            String.prototype.capitalize = function(){
                return this.replace( /(^|\s)([a-z])/g , function(m,p1,p2){ return p1+p2.toUpperCase(); } );
            };
            var $dialog;
            $(document).ready(function(){
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                dialogInit('Carian Hakmilik');
            });
            function doPrintViaApplet (docId) {
                //alert('tsttt');
                var a = document.getElementById('applet');
                a.doPrint(docId.toString());
            }
            function doCetakViaApplet (kewid) {                
                var a = document.getElementById('applet');
                a.printOnTheFly('HSLResitBayaran2Frame.rdf','p_id_kew_dok',kewid.toString());
            }

            function showDialog(){
                $("#divId").dialog("open");
                $("#modalIframeId").attr("src","${pageContext.request.contextPath}/upload?muatNaikForm");
                return false;
            }

            function capitalise(id)
            {
                var v = $('#'+id).val();
                $('#'+id).val(v.capitalize());
            }

    
            function muatNaikForm(folderId, dokumenId, idPermohonan) {
                //var left = (screen.width/2)-(1000/2);
                //var top = (screen.height/2)-(150/2);
                var url = '${pageContext.request.contextPath}/upload?muatNaikForm';
                //window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=150, left=" + left + ",top=" + top);

                $.modal('<iframe src="' + url + '" height="150" width="500" style="border:0">', {
                    closeHTML:"",
                    containerCss:{
                        backgroundColor:"#fff",                        
                        height:150,
                        padding:0,
                        width:500
                    },
                    overlayClose:true
                });

                $.extend($.modal.defaults, {
                    closeClass: "modalClose",
                    closeHTML: "<a href='#'>Close</a>"
                });
            }
            function popupWin(id){
                var position = $('#'+id).offset();
                cnTop = position.top;                
                cnLeft = position.left;
                offsetHeight = document.getElementById(id).offsetHeight;
                cnTop +=offsetHeight;                
                cnTop += 95;//FIXME
                var url = '${pageContext.request.contextPath}/upload?muatNaikForm';
                $.modal('<iframe src="' + url + '" height="150" width="500" style="border:0" top='+cnTop+' left='+cnLeft+'>', {
                    closeHTML:"",
                    containerCss:{
                        backgroundColor:"#fff",
                        height:150,
                        padding:0,
                        width:500
                    },
                    overlayClose:false
                });
                
                winCal=window.open("<%=request.getContextPath()%>/common/carian_hakmilik","DateTimePicker","toolbar=0,status=0,menubar=0,fullscreen=no,location=0,menubar=0,titlebar=0,width=500,height=130,resizable=0,top="+cnTop+",left="+cnLeft);
            }

            function doPrintResit(id){
                var a = document.getElementById('applet');
                a.cetakCukai(id);
            }
        </script>
        <style type="text/css">
            #simplemodal-overlay {background-color:#000;}
            #simplemodal-container {background-color:#333; border:8px solid #444; padding:12px;}
        </style>
    </head>
    <body>
<!--        <h2>HOME sweet Home</h2>        
        <input type="text" name="test" id="test" onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');" onblur="capitalise(this.id);"/>
        <%--<input type="button" name="cetak" value="Cetak" onclick="doPrintResit('1012200902130');" class="btn"/>--%>

        <input type="text" name="test" id="test2" onclick="dialog(this.id,'${pageContext.request.contextPath}/common/carian_hakmilik');" onblur="capitalise(this.id);"/>
        <input type="text" name="test" id="test3" onclick="dialog(this.id,'${pageContext.request.contextPath}/common/carian_hakmilik');" onblur="capitalise(this.id);"/>
        <applet code="HasilPrinterReceipt.class" ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
                ${pageContext.request.contextPath}/commons-httpclient-2.0.2.jar,
                ${pageContext.request.contextPath}/commons-logging.jar,
                ${pageContext.request.contextPath}/swingx-1.6.jar,
                ${pageContext.request.contextPath}/jpedal_demo.jar"
                codebase = "${pageContext.request.contextPath}/"
                name     = "applet"
                id       = "applet"
                width    = "1000"
                height   = "1000"
                align    = "middle">
            <param name="uploadAction" value="<%=request.getContextPath()%>/PrintServlet"/>
            <param name ="method" value="pdfp">
            <%
                        Cookie[] cookies = request.getCookies();
                        StringBuffer sb = new StringBuffer();
                        for (int i = 0; i < cookies.length; i++) {
                            sb.setLength(0);
                            sb.append(cookies[i].getName());
                            sb.append("=");
                            sb.append(cookies[i].getValue());
            %>
            <param name="Cookie<%= i%>" value="<%= sb.toString()%>"><%
                        }
            %>
        </applet>-->
<a href="workflow/kernal_action?stageId=01Kemasukan&idPermohonan=0505ACQ2013000023">Maklumat Pihak Tidak Berkepentingan</a><br>
       <a href="workflow/kernal_action?stageId=46CetakNotaSiasatan&idPermohonan=0505ACQ2013000107">Nota Siasatan</a><br>
       <a href="workflow/kernal_action?stageId=3trhj&idPermohonan=0505ACQ2013000074">Nota Siasatan 2</a><br>
       <a href="workflow/kernal_action?stageId=22SemakanTandatangan&idPermohonan=0505ACQ2013000035">Semakan Tandatangan</a><br>
       <a href="workflow/kernal_action?stageId=05LaporanTanah&idPermohonan=0505ACQ2013000035">LaporanTanah</a><br>
       <a href="workflow/kernal_action?stageId=g_penyediaan_pu&idPermohonan=0505ACQ2013000112">Validator for Penyediaan PU</a><br>
       <a href="workflow/kernal_action?stageId=09SemakanKertasMB&idPermohonan=0505ACQ2013000153">Fixed Syor Ulasan</a><br>
       <a href="workflow/kernal_action?stageId=10SemakanKertasMB&idPermohonan=0505ACQ2013000153">Fixed tajuk</a><br>
       <a href="workflow/kernal_action?stageId=68RekodBayaran&idPermohonan=0505ACQ2013000149">Skrin baru</a><br>
       <a href="workflow/kernal_action?stageId=71RekodKeputusanJanaSurat&idPermohonan=0505ACQ2013000165">Jana Surat</a><br>
       <a href="workflow/kernal_action?stageId=47SemakanSuratBrgGH&idPermohonan=0505ACQ2013000222">Penambahan pada Permohonan Jika ada Kedesakan</a><br>


    </body>
</html>
