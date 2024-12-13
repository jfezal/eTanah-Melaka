

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>       
        <script type="text/javascript">

        </script>
    </head>

    <body>  
        <s:messages/>
        <s:errors/>
        <div class="row">
            <div class="col-md-12">
                <s:form beanclass="com.theta.portal1speks.stripes.LoginActionBean" data-toggle="validator" id="form1">
                    <s:useActionBean beanclass="com.theta.portal1speks.stripes.util.ListUtil" var="list"/>
                    <s:hidden name="pengguna.idPengguna"/> 
                    <s:hidden name="pengguna.kataLaluan" value="${actionBean.kataLaluan}"/>
                    <s:hidden name="pengguna.question1" value="${actionBean.question1}"/>
                    <s:hidden name="pengguna.question2" value="${actionBean.question2}"/>
                    <s:hidden name="pengguna.question3" value="${actionBean.question3}"/>
                    <s:hidden name="pengguna.answer1" value="${actionBean.answer1}"/>
                    <s:hidden name="pengguna.answer2" value="${actionBean.answer2}"/>
                    <s:hidden name="pengguna.answer3" value="${actionBean.answer3}"/>
                    <s:hidden name="pengguna.imageOnline" value="${actionBean.imageOnline}"/>
                    <s:hidden name="pengguna.frasaRahsia" value="${actionBean.frasaRahsia}"/>

                    <div class="welcome-text-2">
                        <br>                 
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
                                                <label class="col-sm-2 control-label">Kod Pembekal : </label>
                                                <div class="col-sm-10">
                                                    <p class="form-control-static">${actionBean.pengguna.idPengguna}</p>
                                                </div>
                                                <label class="col-sm-2 control-label">Nama Syarikat : </label>
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
                                            Maklumat Syarikat
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

                                                <label class="col-sm-2 control-label">Jenis Syarikat</label>
                                                <div class="col-sm-6">
                                                    <s:select name="pengguna.kodTarafPerkahwinan" style="width:300px" class="form-control" data-error="Sila Pilih Taraf Perkahwninan!" required="true">
                                                        <s:option value="">Sila Pilih</s:option>
                                                        <s:options-collection collection="${list.kategoriSyarikat}" label="perihal" value="id"/>
                                                    </s:select>
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Kelas Kontraktor</label>
                                                <div class="col-sm-6">
                                                    <s:select name="pengguna.kodTarafPerkahwinan" style="width:300px" class="form-control" data-error="Sila Pilih Taraf Perkahwninan!" required="true">
                                                        <s:option value="">Sila Pilih</s:option>
                                                        <s:options-collection collection="${list.kelasKontraktor}" label="perihal" value="id"/>
                                                    </s:select>
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
