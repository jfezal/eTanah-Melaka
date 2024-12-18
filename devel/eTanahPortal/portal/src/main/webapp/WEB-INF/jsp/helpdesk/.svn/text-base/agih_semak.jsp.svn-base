
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-select.min.css">
        <script src="${pageContext.request.contextPath}/js/util.js" type="text/javascript"></script> 
        <script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>
        <script type="text/javascript">

            function pulangsemulaAgih() {

//                alert('d');
                var notaTeknikal = $('#notaAgih').val();
                if ((notaKontraktor == '')) {


                } else {
                    var notaAgih = $('[name=notaAgih]').val();
                    var reportId = $('[name=reportId]').val();
                    var url = "${pageContext.request.contextPath}/helpdesk/kontraktor?pulangSemulaAgih&reportId=" + reportId + "&notaAgih=" + notaAgih;
                    $.post(url,
                            function (data) {
//                                alert('s');
                                window.location = "${pageContext.request.contextPath}/welcome";
                            }, 'html');

                }
            }

//            function senaraitugasan(a) {
//                var url = "${pageContext.request.contextPath}/helpdesk/agih_semak?tugasanBelumSelesai&userId=" + a;
//                alert(url);
//                $.get(url,
//                        function (data) {
//                            alert(data);
//                            var parent = document.getElementById('parent');
//                            var elem = strToElem("Papar", "#", data);
//                    alert(elem);
//                            parent.appendChild(elem);
//                            document.getElementById("test").innerHTML = "Jumlah tugasan belum selesai "+ data  + elem.;
//                        }, 'html');
//
//
//            }
//            function strToElem(text, link, tugasan) {
//                var temp = '<p> Jumlah tugasan belum selesai: ' + tugasan + ' <a href="' + link + '">' + text + '</a></p>';
//                var a = document.createElement("p");
//                a.innerHTML = temp;
//                return a.childNodes[0];
//            }



        </script>
        <title>.: eTanah : Daftar Aduan :.</title> 
    </head>
    <body>

        <div class="row">
            <div class="col-md-12">
                <s:messages/>
                <s:errors/>
                <s:form beanclass="com.theta.portal.stripes.helpdesk.HelpDeskActionBean" data-toggle="validator"> 
                    <s:useActionBean beanclass="com.theta.portal.stripes.util.ListUtil" var="list"/>
                    <s:hidden name="idPengguna"/>
                    <s:hidden name="reportId"/>
                    <div class="welcome-text-2">
                        <br>
                        <div class="panel panel-default">
                            <!-- Toggle Heading -->
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse-3">
                                        <i class="fa fa-angle-up control-icon"></i>
                                        Maklumat Aduan
                                    </a>
                                </h4>
                            </div>
                            <div id="collapse-3" class="panel-collapse collapse in">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="panel-body">
                                            <div class="form-horizontal">
                                                <br>
                                                <display:table class="table table-hover table table-striped table-bordered" name="${actionBean.listAduan}" style="width:90;" requestURI="/saveDocuments" id="line">
                                                    <display:column title="No.">${line_rowNum}</display:column>
                                                    <display:column title="Maklumat Pelapor" ><div class="col-sm-12">
                                                            <div class="row">
                                                                <div class="col-sm-2 "><span class="pull-left" ><p class="text">Nama</p></span></div><div class="col-sm-10"><span class="pull-left"><p class="text-info" >${line.namaPelapor}</p></span></div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-sm-2 "><span class="pull-left" ><p class="text">Unit</p></span></div><div class="col-sm-10"><span class="pull-left"><p class="text-info" >${line.unit}</p></span></div>
                                                            </div>
                                                        </div>
                                                    </display:column>

                                                    <display:column title="Keterangan Aduan" style="width:50%;">
                                                        <table style="width:100%;">
                                                            <tr>
                                                                <td align="left" style="width:20%;"><p class="text" >Perkara</p></td><td align="left"><p class="text-info" >${line.perkara}</p></td>
                                                            </tr>
                                                            <tr>
                                                                <td align="left" style="width:20%;"><p class="text" >Keterangan</p></td><td align="left"><p class="text-info" >${line.keterangan}</p></td>
                                                            </tr>
                                                            </table>
                                                        
                                                    </display:column>

                                                    <display:column title="Dokumen">

                                                        <c:set value="0" var="count"/>
                                                        <c:forEach items=" ${line.listOfDocument}" var="i" >
                                                            <p>  <a href="${pageContext.request.contextPath}${line.listOfDocument[count].urlDownload}" target="">  ${line.listOfDocument[count].fileName}</a> </p>
                                                            <c:set value="${count +1}" var="count"/>
                                                        </c:forEach>

                                                    </display:column>
                                                </display:table>  
                                            </div>
                                        </div>

                                    </div>

                                </div>
                            </div>



                        </div>
                        <div class="panel panel-default">
                            <!-- Toggle Heading -->
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse-3">
                                        <i class="fa fa-angle-up control-icon"></i>
                                        Agihan Aduan
                                    </a>
                                </h4>
                            </div>
                            <div id="collapse-3" class="panel-collapse collapse in">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="panel-body">
                                            <div class="form-horizontal">
                                                <br>
                                                <div class="container">
                                                    <label class="col-sm-2 control-label">Keterangan Masalah :</label>
                                                    <div class="col-sm-6 form-group">
                                                        <s:hidden name="assignId"/>
                                                        <s:textarea name="keterangan" class="form-control" data-error="Sila Masukkan Keterangan Masalah" required="true"/>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput">Pegawai Teknikal :</label>  
                                                    <div class="col-md-3 form-group">
                                                        <s:select name="userId" style="width:300px" class="form-control" data-error="Sila Pilih Pegawai" required="true" onchange="senaraitugasan(this.value)">
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${actionBean.listAgihanUser}" label="name" value="userId"/>
                                                        </s:select>
                                                    </div>
                                                </div>
                                                <div class="container">
                                                    <label class="col-md-2 control-label" for="textinput"></label>  
                                                    <div class="col-md-3 form-group">
                                                        <div id="parent">
                                                        </div>
                                                        <p id="test"></p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <jsp:include page="/WEB-INF/jsp/include/action.jsp" />
                    </div>

                </s:form>               
            </div>

        </div>


    </body>
</html>
