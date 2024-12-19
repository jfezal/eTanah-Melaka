
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Portal eTanah Melaka</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.js"></script>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/
        ➥3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/
        ➥respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <style>
            .header{}

            .footer {
                left: 0;
                bottom: 0;
                width: 100%;
                height: 100pt;
                background-color: #604B89;
                color: white;
                text-align: left;
            }

            input[type=text] {
                padding: 12px 20px;
                display: inline-block;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }
            button{
                background-color: #4CAF50;
                border: none;
                color: white;
                padding: 12px 30px;
                text-decoration: none;
                margin: 4px 2px;
                cursor: pointer;
            }
            canvas{
                /*prevent interaction with the canvas*/
                pointer-events:none;
            }
        </style>

    </head>
    <body>
        <s:messages/>
        <s:errors/>
        <s:form beanclass="com.theta.portal.stripes.portal.SemakanAkaunActionBean" data-toggle="validator"> 
            <div class="container">
                <div class="row">
                    <div class="col-sm-7 col-xs-12">
                        <div class="panel-group">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4><b>CARIAN SEMAKAN CUKAI</b></h4>
                                </div>
                                <div class="panel-body">
                                    <div class="form-group">
                                        <label for="formCarianStatusSelect">Sila pilih kategori ID untuk carian status</label>

                                        <s:select name="katgCarian"  class="form-control" data-error="Sila Pilih Jenis Carian" onchange="checkIc(this.value)" required="true">
                                            <s:option value="a">No Akaun</s:option>
                                            <s:option value="b">No Kad Pengenalan</s:option>  
                                            <s:option value="c">ID Hakmilik</s:option>                                             
                                        </s:select>
                                    </div>
                                    <div class="form-group">                                       
                                        <s:text name="id" class="form-control" placeholder="Masukkan id anda"/>
                                        <p id="info">
                                            <font color="red">  Sila Masukan No Kad Penganalan Anda Tanpa ' - ' Atau Dengan ' - '.</font>
                                        </p>

                                    </div>
                                    <p align="right">
                                    <p id="infoCaptcha">
                                        <font color="red">  Sila Masukan Perkataan Captcha Di Bawah untuk Meneruskan Carian.</font>
                                    </p>
                                    <div id="captcha">
                                    </div>

                                    <input type="text" placeholder="Captcha" id="cpatchaTextBox"/>
                                    <button type="submit" class="btn btn-primary mb-2" onclick="validateCaptcha(this.form)">Submit</button>&nbsp;&nbsp;&nbsp;
                                    <button type="reset" class="btn btn-primary mb-2">Isi Semula</button>
                                    </p>

                                </div>
                            </div>
                        </div>
                        <div class="panel-group">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4><b>SEMAKAN CUKAI</b></h4>
                                </div>
                                <div class="panel-body">
                                    <div class="table-responsive" class="table table-hover">
                                        <display:table class="table table-hover table table-striped table-bordered" name="${actionBean.listStatusSemakanAkaun}" style="width:90;" requestURI="/saveDocuments" id="line">
                                            <c:if test="${line.noAkaun ne null}">
                                                <display:column title="No.">${line_rowNum}</display:column>
                                                <display:column title="No Akaun">${line.noAkaun}</display:column>
                                                <display:column title="Status">${line.status}</display:column>
                                                <display:column title="Baki">${line.baki}</display:column>
                                                <display:column title="Papar Bil">
                                                    <button type="button" class="btn btn-info" onclick="paparBil('${line.idHakmilik}')" data-toggle="modal" data-target="#myModal">Papar</button>
                                                </display:column>
                                            </c:if>
                                            <c:if test="${line.noAkaun eq null}">
                                                <display:column title="Tiada Rekod Di Jumpai">
                                                    <font color="red">  Sila Hubungi Unit Hasil atau Unit IT 06-3333333  Atau email ke ptg@melaka.gov.my unutk maklumat lanjut.</font>
                                                </display:column>

                                            </c:if>
                                        </display:table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div><p align="right">
                                <s:button class="btn btn-primary mb-2" value="Penafian/Disclaimer" name="new" id="new" onclick="penafian();"/>
                                &nbsp;&nbsp;&nbsp;
                            </p>
                        </div>


                    </div>

                </div>
            </div>



        </s:form>   

        <script>
            $(document).ready(function() {
                $('#info').hide();
                $('#tiadaData').hide();
                createCaptcha();

            });



            function cari(frm) {
                var url = '${pageContext.request.contextPath}/semakan_akaun?cari';
                if (confirm("Saya bersetuju bahawa maklumat yang diberikan adalah untuk kegunaan bayaran cukai tanah Negeri Melaka sahaja.\nSaya bersetuju bahawa tindakan boleh diambil ke atas saya sekiranya didapati menyalahgunakan maklumat.")) {
                    frm.action = url;
                    frm.submit();
                }
//                if (confirm('Adakah anda pasti untuk teruskan?')) {
//                    var url = '$//{pageContext.request.contextPath}/semakan_akaun?cari';
//                    $.get(url,
//                            function(data) {
//                                 $('#page_div').html(data);
//                                frm.action = url;
//                                frm.submit();
//                            }, 'html');
//                }
            }

            function penafian() {

                window.open("${pageContext.request.contextPath}/semakan_akaun?penafian", "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=800,height=800,scrollbars=yes,fullscreen=yes");
            }
            function paparBil1(idHakmilik) {

                var report = null;
                report = 'HSLBilCukaiMLK_Frame.rdf';

                var url = "reportName=" + report + "%26report_p_id_hakmilik=" + idHakmilik;

                window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");


            }

            function paparBil(idHakmilik) {
                // alert("papapr bil");
                var url = '${pageContext.request.contextPath}/semakan_akaun?viewBil&idHakmilik=' + idHakmilik;
                window.open(url, 'eTanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
            }

            function checkIc(value) {
                //alert("b:" + value);
                // var plength = value.length;
                if (value === 'b') {
                    $('#info').show();

                } else {
                    $('#info').hide();
                }
            }

            var code;
            function createCaptcha() {
                //clear the contents of captcha div first 
                document.getElementById('captcha').innerHTML = "";
                var charsArray =
                        "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
                var lengthOtp = 6;
                var captcha = [];
                for (var i = 0; i < lengthOtp; i++) {
                    //below code will not allow Repetition of Characters
                    var index = Math.floor(Math.random() * charsArray.length + 1); //get the next character from the array
                    if (captcha.indexOf(charsArray[index]) == -1)
                        captcha.push(charsArray[index]);
                    else
                        i--;
                }
                var canv = document.createElement("canvas");
                canv.id = "captcha";
                canv.width = 100;
                canv.height = 50;
                var ctx = canv.getContext("2d");
                ctx.font = "25px Georgia";
                ctx.strokeText(captcha.join(""), 0, 30);
                //storing captcha so that can validate you can save it somewhere else according to your specific requirements
                code = captcha.join("");
                document.getElementById("captcha").appendChild(canv); // adds the canvas to the body element
            }
            function validateCaptcha(frm) {
                event.preventDefault();
                debugger

                if ($('#cpatchaTextBox').val() === code) {
                    alert("Captcha Sah");
                    cari(frm);
                } else {
                    alert("Captcha Tidak Sah. Sila Cuba Lagi");
                    createCaptcha();
                }
            }
        </script>

    </body>
</html>
