

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>       
        <script type="text/javascript">

            $(document).ready(function () {
                hideAll();
                $('#pengaktifanid').hide();
                $('#panelkatalaluansementara').hide();
                      
                $('#load').on('click', function () {
                    var id = document.getElementById('idPengguna').value;
                    if (id == '') {
                        return false;
                    } else {
                        var $this = $(this);
                        $this.button('loading');
                        setTimeout(function () {
                            $this.button('reset');
                        }, 8000);
                    }
                });
                    
                //minimum 8 characters
                var bad = /(?=.{8,}).*/;
                //Alpha Numeric plus minimum 8
                var good = /^(?=\S*?[a-z])(?=\S*?[0-9])\S{8,}$/;
                //Must contain at least one upper case letter, one lower case letter and (one number OR one special char).
                var better = /^(?=\S*?[A-Z])(?=\S*?[a-z])((?=\S*?[0-9])|(?=\S*?[^\w\*]))\S{8,}$/;
                //Must contain at least one upper case letter, one lower case letter and (one number AND one special char).
                var best = /^(?=\S*?[A-Z])(?=\S*?[a-z])(?=\S*?[0-9])(?=\S*?[^\w\*])\S{8,}$/;

                $('#inputPassword').on('keyup', function () {
                    var password = $(this);
                    var pass = password.val();
                    var passLabel = $('[for="password"]');
                    var stength = 'Lemah';
                    var pclass = 'danger';
                    if (best.test(pass) == true) {
                        stength = 'Sangat Bagus';
                        pclass = 'success';
                    } else if (better.test(pass) == true) {
                        stength = 'Bagus';
                        pclass = 'warning';
                    } else if (good.test(pass) == true) {
                        stength = 'Hampir Bagus';
                        pclass = 'warning';
                    } else if (bad.test(pass) == true) {
                        stength = 'Lemah';
                    } else {
                        stength = 'Sangat Lemah';
                    }

                    var popover = password.attr('data-content', stength).data('bs.popover');
                    popover.setContent();
                    popover.$tip.addClass(popover.options.placement).removeClass('danger success info warning primary').addClass(pclass);

                });

                $('input[data-toggle="popover"]').popover({
                    placement: 'top',
                    trigger: 'focus'
                });

            });


            function getPilihan(pilihan) {

              
              var option1 = pilihan.trim();
            
                    if (option1 === 'aktifkanid'){
  
                  //     alert ("You choose aktifkan ID");
                       $('#panelpilihan').show(); 
                       $('#pengaktifanid').show();
                       $('#panelkatalaluansementara').hide();
                       
                       
                    } else if (option1 === 'katalaluansementara'){
                        
                    //    alert ("You choose Kata laluan sementara");
                    
                        $('#panelkatalaluansementara').show();
                        $('#panelpilihan').show();
                        $('#pengaktifanid').hide();   
                    }
                    
                }
                
                
            function hideAll() {
                $('#kakitangan').hide();
                $('#notKakitangan').hide();               
            }
            function getKakitangan(val)
            {
                var url = "${pageContext.request.contextPath}/auto_complete?getPengguna&kod="
                        + val;

                $.get(url,
                        function (data) {
                            console.log(data);
                            if (data.idPengguna)
                            {
                                if(data.login == '0'){
                                    $('#emel').val(data.emel);
                                    $('#kakitangan').show();
                                    $('#notKakitangan').hide();
                                    $('#kataLaluan').removeAttr('required');
                                    
                                }
                                if (data.login == '1'){
                                    $('#kakitangan').hide();
                                    $('#notKakitangan').show();
                                                                   }
                            } 
                            $('#send').removeAttr('disabled');
                        });
            }

            function test() {
                $('#send').click();
            }
            

        </script>
        <style>
            .method {
                display: inline-block;
                vertical-align: middle;
            }
            .payment-methods input[type=radio] {
                margin: 0px 0px 0px 60px;
            }
            .popover.primary {
                border-color:#337ab7;
            }
            .popover.primary>.arrow {
                border-top-color:#337ab7;
            }
            .popover.primary>.popover-title {
                color:#fff;
                background-color:#337ab7;
                border-color:#337ab7;
            }
            .popover.success {
                border-color:#d6e9c6;
            }
            .popover.success>.arrow {
                border-top-color:#d6e9c6;
            }
            .popover.success>.popover-title {
                color:#3c763d;
                background-color:#dff0d8;
                border-color:#d6e9c6;
            }
            .popover.info {
                border-color:#bce8f1;
            }
            .popover.info>.arrow {
                border-top-color:#bce8f1;
            }
            .popover.info>.popover-title {
                color:#31708f;
                background-color:#d9edf7;
                border-color:#bce8f1;
            }
            .popover.warning {
                border-color:#faebcc;
            }
            .popover.warning>.arrow {
                border-top-color:#faebcc;
            }
            .popover.warning>.popover-title {
                color:#8a6d3b;
                background-color:#fcf8e3;
                border-color:#faebcc;
            }
            .popover.danger {
                border-color:#ebccd1;
            }
            .popover.danger>.arrow {
                border-top-color:#ebccd1;
            }
            .popover.danger>.popover-title {
                color:#a94442;
                background-color:#f2dede;
                border-color:#ebccd1;
            }
        </style>
    </head>

    <body>  
        <s:messages/>
        <s:errors/>
        <div class="row">
            <div class="col-md-12">
                <s:form beanclass="com.theta.portal.stripes.LoginActionBean" data-toggle="validator" id="form1">                    
                    <s:hidden name="pengguna.idPengguna"/>                        
                    <div class="welcome-text-2">
                        <br>
                        <c:if test="${empty actionBean.pengguna.idPengguna}"> 
                        <div class="panel-group" id="accordion"> 
                            <div class="panel panel-default" id="panelpilihan">
                                <div class="panel-heading" >
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse-1a">
                                                <i class="fa fa-angle-up control-icon"></i>
                                                Pilihan Pengguna
                                            </a>
                                        </h4>
                                </div>
                                <div id="collapse-1a" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <div class="form-horizontal">                  
                                            <div class="panel-group" id="accordion">
                                                <div class="col-sm-7">
                                                    <s:select name="pilihan" id="pilih" class="form-control" required="true" onchange="getPilihan(options[this.selectedIndex].value)">
                                                        <s:option value="">Sila Pilih</s:option>
                                                        <s:option value="aktifkanid"> Aktifkan ID Pengguna</s:option>
                                                        <s:option value="katalaluansementara"> Kata Laluan Sementara</s:option>
                                                    </s:select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>                                          
                                </div>                              
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${empty actionBean.pengguna.idPengguna}"> 
                            <div class="panel-group" id="accordion">
                                <div class="panel panel-default" id="pengaktifanid">
                                    <!-- Toggle Heading -->
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse-1">
                                                <i class="fa fa-angle-up control-icon"></i>
                                                Pengaktifan ID Pengguna
                                            </a>
                                        </h4>
                                    </div>
                                
                                    <!-- Toggle Content -->
                                    <div id="collapse-1" class="panel-collapse collapse in">
                                        <div class="panel-body">
                                            <div class="form-horizontal">
                                               <div class="form-group">
                                                    <div class="col-sm-7">
                                                        <div class="latest-news">
                                                            <h3 class="section-title">Maklumat ID Pengguna</h3>
                                                            <div class="latest-post">
                                                                <p>No Kad Pengenalan akan digunakan sebagai ID Pengguna.</p>
                                                                <p>Contoh : 810512067890</p>
                                                            </div>   
                                                        </div>
                                                    </div>
                                                </div>
                                                <br>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">No Pengenalan</label>
                                                    <div class="col-sm-6">
                                                        <s:text name="pengguna.idPengguna" class="form-control" id="idPengguna" onkeyup="getKakitangan(this.value);" onblur="getKakitangan(this.value);" 
                                                                data-error="Sila Masukkan No Pengenalan!" required="true" oninvalid="setCustomValidity('Sila Masukkan No Pengenalan!')" oninput="setCustomValidity('')"/> &nbsp;                                                 
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="form-group" id="kakitangan">
                                                    <label class="col-sm-2 control-label">Emel</label>
                                                    <div class="col-sm-6">
                                                        <s:text name="pengguna.emel" class="form-control" id="emel"  readonly="true"/>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="form-group" id="notKakitangan">
                                                    <label class="col-sm-2 control-label">Kata Laluan</label>
                                                    <div class="col-sm-6">
                                                        <input type="password" name="pengguna.kataLaluan" class="form-control" id="kataLaluan" data-error="Sila Masukkan Kata Laluan!" 
                                                               placeholder="Kata Laluan dalam Emel" required="true" oninvalid="setCustomValidity('Sila Masukkan Kata Laluan!')" oninput="setCustomValidity('')">
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-sm-offset-2 col-sm-10">
                                                        <s:submit name="activeLogin" value="Hantar" class="btn btn-primary" tabindex="2" id="send" style="display:none;"/>&nbsp; 
                                                        <button type="button" name="activeLogin" class="btn btn-primary btn-lg" id="load" data-loading-text="<i class='fa fa-spinner fa-spin '></i> Sedang di proses" onclick="test();">Hantar</button>&nbsp; 
                                                        <a href="${pageContext.request.contextPath}/main?firstTimeLogin" class="btn btn-primary" id="btnisisemula">Isi Semula</a>&nbsp;
                                                        <a href="${pageContext.request.contextPath}/welcome" class="btn btn-primary" id="btnkeluar">Keluar</a>                                                       
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${empty actionBean.pengguna.idPengguna}">
                            <div class="panel-group" id="accordion"> 
                            <div class="panel panel-default" id="panelkatalaluansementara">
                                <!-- Toggle Heading -->
                                <div class="panel-heading" >
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse-2a">
                                                <i class="fa fa-angle-up control-icon"></i>
                                                Kata Laluan Sementara
                                            </a>
                                        </h4>
                                </div>
                                <!-- Toggle Content -->
                                <div id="collapse-2a" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <div class="form-horizontal">                  
                                            
                                            <div class="form-group">
                                                    <div class="col-sm-7">
                                                        <div class="latest-news">
                                                            <h3 class="section-title">Maklumat ID Pengguna</h3>
                                                            <div class="latest-post">
                                                                <p>No Kad Pengenalan akan digunakan sebagai ID Pengguna.</p>
                                                                <p>Contoh : 810512067890</p>
                                                            </div>   
                                                        </div>
                                                    </div>
                                                </div>
                                            <br> 
                                            <div class="form-group" id="kakitangan">
                                                    <label class="col-sm-2 control-label">No Pengenalan</label>
                                                    <div class="col-sm-6">
                                                         <s:text name="pengguna.idPengguna" class="form-control" id="idPengguna" onkeyup="getKakitangan(this.value);" onblur="getKakitangan(this.value);" 
                                                                 data-error="Sila Masukkan No Pengenalan!" required="true" oninvalid="setCustomValidity('Sila Masukkan No Pengenalan!')" oninput="setCustomValidity('')"/> 
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                            </div>
                                            <div class="form-group">           
                                                 <div class="col-sm-offset-2 col-sm-10">       
                                                        <a href="${pageContext.request.contextPath}/main?firstTimeLogin" class="btn btn-primary" id="btnisisemula">Isi Semula</a>&nbsp;
                                                        <a href="${pageContext.request.contextPath}/welcome" class="btn btn-primary" id="btnkeluar">Keluar</a>&nbsp;
                                                        
                                                        <s:submit value="Hantar Semula Kata Laluan" name="kataLaluanSementara" class="btn btn-primary btn-lg" id="button2"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>                                          
                                </div>                              
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${!empty actionBean.pengguna.idPengguna}">

                            <div class="panel panel-default">
                                <!-- Toggle Heading -->
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse-2">
                                            <i class="fa fa-angle-up control-icon"></i>
                                            Maklumat Pendaftaran Baru
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapse-2" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">ID Pengguna : </label>
                                                <div class="col-sm-10">
                                                    <p class="form-control-static">${actionBean.pengguna.idPengguna}</p>
                                                </div>
                                                <label class="col-sm-2 control-label">Nama : </label>
                                                <div class="col-sm-10">
                                                    <p class="form-control-static">${actionBean.pengguna.nama}</p>
                                                </div>
                                                <label class="col-sm-2 control-label">Emel : </label>
                                                <div class="col-sm-10">
                                                    <p class="form-control-static">${actionBean.pengguna.emel}</p>
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
                                            Maklumat Kata Laluan
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapse-3" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <div class="col-sm-7">
                                                    <p>* Medan Wajib Diisi</p>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Kata Laluan</label>
                                                <div class="col-sm-6"> 
                                                    <s:password  class="form-control" name="pengguna.kataLaluan" data-minlength="6" id="inputPassword" placeholder="Kata Laluan" required="true" data-toggle="popover" title="Password Strength" data-content="Masukkan Kata Laluan..."/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Sah Kata Laluan</label>
                                                <div class="col-sm-6">
                                                    <input type="password" class="form-control" id="inputPasswordConfirm" data-minlength="6" data-match="#inputPassword" data-match-error="Kata Laluan tidak Sama" placeholder="Sah Kata Laluan" required>
                                                    <div class="help-block with-errors"></div>
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
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse-4">
                                            <i class="fa fa-angle-up control-icon"></i>
                                            Soalan dan Jawapan Rahsia
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapse-4" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">1. Q</label>
                                                <div class="col-sm-6">
                                                    <s:select class="form-control required" name="pengguna.question1">
                                                        <s:option value="">Sila Pilih</s:option>
                                                        <s:option value="1">Sekolah pertama anda?</s:option>
                                                        <s:option value="2">Bandar pertama anda berkerja?</s:option>
                                                        <s:option value="3">Siapakah rakan baik anda di pejabat?</s:option>
                                                        <s:option value="4">Menu kegemaran anda?</s:option>
                                                        <s:option value="5">Apakah warna kesukaan anda?</s:option>
                                                        <s:option value="6">Siapakah nama nenek sebelah ayah anda?</s:option>
                                                    </s:select>
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">A</label>
                                                <div class="col-sm-6">
                                                    <input type="text" name="pengguna.answer1" class="form-control" id="answer1" data-error="Sila Masukkan Jawapan 1!" required>
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">2. Q</label>
                                                <div class="col-sm-6">
                                                    <s:select class="form-control required" name="pengguna.question2">
                                                        <s:option value="">Sila Pilih</s:option>
                                                        <s:option value="1">Sekolah pertama anda?</s:option>
                                                        <s:option value="2">Bandar pertama anda berkerja?</s:option>
                                                        <s:option value="3">Siapakah rakan baik anda di pejabat?</s:option>
                                                        <s:option value="4">Menu kegemaran anda?</s:option>
                                                        <s:option value="5">Apakah warna kesukaan anda?</s:option>
                                                        <s:option value="6">Siapakah nama nenek sebelah ayah anda?</s:option>
                                                    </s:select><div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">A</label>
                                                <div class="col-sm-6">
                                                    <input type="text" name="pengguna.answer2" class="form-control" id="answer2" data-error="Sila Masukkan Jawapan 2!" required>
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">3. Q</label>
                                                <div class="col-sm-6">
                                                    <s:select class="form-control required" name="pengguna.question3">
                                                        <s:option value="">Sila Pilih</s:option>
                                                        <s:option value="1">Sekolah pertama anda?</s:option>
                                                        <s:option value="2">Bandar pertama anda berkerja?</s:option>
                                                        <s:option value="3">Siapakah rakan baik anda di pejabat?</s:option>
                                                        <s:option value="4">Menu kegemaran anda?</s:option>
                                                        <s:option value="5">Apakah warna kesukaan anda?</s:option>
                                                        <s:option value="6">Siapakah nama nenek sebelah ayah anda?</s:option>
                                                    </s:select><div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">A</label>
                                                <div class="col-sm-6">
                                                    <input type="text" name="pengguna.answer3" class="form-control" id="answer3" data-error="Sila Masukkan Jawapan 3!" required>
                                                    <div class="help-block with-errors"></div>
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
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse-5">
                                            <i class="fa fa-angle-up control-icon"></i>
                                            Imej Online
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapse-5" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <div class="payment-methods">
                                            <div class="method">
                                                <label class="radio">
                                                    <img  src="${pageContext.request.contextPath}/images/validate/beg.jpg"  style="vertical-align: middle" class="img-thumbnail" alt="Beg" width="150" height="200">
                                                </label>
                                                <input name="pengguna.imageOnline" value="beg" type="radio" style="vertical-align: middle">
                                            </div>
                                            <div class="method">

                                                <label class="radio">
                                                    <img  src="${pageContext.request.contextPath}/images/validate/bskl.jpg"  style="vertical-align: middle" class="img-thumbnail" alt="Beg" width="150" height="200">
                                                </label>
                                                <input name="pengguna.imageOnline" value="bskl" type="radio" style="vertical-align: middle">
                                            </div>
                                            <div class="method">

                                                <label class="radio">
                                                    <img  src="${pageContext.request.contextPath}/images/validate/peti.jpg"  style="vertical-align: middle" class="img-thumbnail" alt="Beg" width="150" height="200">
                                                </label>
                                                <input name="pengguna.imageOnline" value="peti" type="radio" style="vertical-align: middle">
                                            </div>
                                            <div class="method">
                                                <label class="radio">
                                                    <img  src="${pageContext.request.contextPath}/images/validate/kasut.jpg"  style="vertical-align: middle" class="img-thumbnail" alt="Beg" width="150" height="200">
                                                </label>
                                                <input name="pengguna.imageOnline" value="kasut" type="radio" style="vertical-align: middle">
                                            </div>
                                            <div class="method">
                                                <label class="radio">
                                                    <img  src="${pageContext.request.contextPath}/images/validate/laptops.jpg"  style="vertical-align: middle" class="img-thumbnail" alt="Beg" width="150" height="200">
                                                </label>
                                                <input name="pengguna.imageOnline" value="laptops" type="radio" style="vertical-align: middle">
                                            </div>
                                        </div>  
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <!-- Toggle Heading -->
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse-6">
                                                <i class="fa fa-angle-up control-icon"></i>
                                                Frasa Rahsia
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapse-6" class="panel-collapse collapse in">
                                        <div class="panel-body">
                                            <div class="form-horizontal">
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Frasa Rahsia</label>
                                                    <div class="col-sm-6">
                                                        <input type="text" name="pengguna.frasaRahsia" class="form-control" id="frasaRahsia" data-error="Sila Masukkan Frasa Rahsia!" required>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="panel-body">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <div class="col-sm-offset-2 col-sm-10">
                                                <c:choose>
                                                    <c:when test="${actionBean.pengguna.kodKategoriPengguna eq 'VN'}">
                                                        <s:submit name="nextPembekal" value="Seterusnya" class="btn btn-primary" tabindex="1"/>&nbsp;
                                                    </c:when>
                                                    <c:otherwise>
                                                        <s:submit name="activeLoginPengguna" value="Aktif" class="btn btn-primary" tabindex="1"/>&nbsp;
                                                    </c:otherwise>
                                                </c:choose>  

                                                <s:button onclick="clearText('form1')" name="clear" value="Kosongkan"  class="btn btn-danger" />&nbsp;
                                                <a href="${pageContext.request.contextPath}/welcome" class="btn btn-primary">Keluar</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </c:if>                          
                    </div>
                </s:form>
            </div>        
        </div>
    </body>
</html>
