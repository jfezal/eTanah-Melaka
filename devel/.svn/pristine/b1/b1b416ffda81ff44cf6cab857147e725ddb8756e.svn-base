<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://www.google.com/recaptcha/api.js"></script>
        <script type="text/javascript">

            $(document).ready(function () {
                $('#pelanggan').hide();
                $('#pembekal').hide();
                
                var kod = $('#kodKategoriPengguna').val();
                
                if(kod)
                {
                    getJenisPengguna(kod);
                }
            });


            function getJenisPengguna(val)
            {
                if (val === 'VN')
                {
                    $('#pelanggan').hide();
                    $('#pembekal').show();
                } else if (val === 'CS')
                {
                    $('#pelanggan').show();
                    $('#pembekal').hide();
                }
            }
            function removeNonNumeric(strString)
            {
                var strValidCharacters = "1234567890+-";
                var strReturn = "";
                var strBuffer = "";
                var intIndex = 0;
                // Loop through the string
                for (intIndex = 0; intIndex < strString.length; intIndex++)
                {
                    strBuffer = strString.substr(intIndex, 1);
                    // Is this a number
                    if (strValidCharacters.indexOf(strBuffer) > -1)
                    {
                        strReturn += strBuffer;
                    }
                }
                return strReturn;
            }

            function validateSpecialChtr(elmnt, content) {
                //if it is character, then remove it..
                if (isNaN(content)) {
                    elmnt.value = removeSpecialChtr(content);
                    return;
                }
            }


            function removeSpecialChtr(strString)
            {
                return strString.replace(/[^a-z\d\s]+/gi, "");
            }
        </script>
    </head>

    <body>  
        <s:messages/>
        <s:errors/>
        <div class="row">
            <div class="col-md-12">
                <s:form beanclass="com.theta.portal.stripes.LoginActionBean" data-toggle="validator">
                    <s:useActionBean beanclass="com.theta.portal.stripes.util.ListUtil" var="list"/>
                    <div class="welcome-text-2">
                        <br>
                        <div class="panel-group" id="accordion">
                            <div class="panel panel-default">
                                <!-- Toggle Heading -->
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse-4">
                                            <i class="fa fa-angle-up control-icon"></i>
                                            Daftar Pengguna Baru
                                        </a>
                                    </h4>
                                </div>

                                <!-- Toggle Content -->
                                <div id="collapse-4" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <div class="col-sm-7">
                                                    <div class="latest-news">
                                                        <h3 class="section-title">Makluman</h3>
                                                        <div class="latest-post">
                                                            <p>No Kad Pengenalan akan digunakan sebagai ID Pengguna.</p>
                                                            <p>Contoh : 810512067890</p>
                                                            <p>Bagi Pembekal Kod Pembekal akan digunakan sebagai ID Pengguna.</p>
                                                            <p>Kata Laluan akan dihantar kepada e-mel pengguna untuk proses pengaktifan</p>
                                                        </div>                                         
                                                    </div>
                                                </div>
                                            </div>
                                            <br>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label" >Kategori Pengguna</label>
                                                <div class="col-sm-6">
                                                    <s:select name="pengguna.kodKategoriPengguna" id="kodKategoriPengguna" class="form-control" data-error="Sila Pilih Kategori Pengguna!" required="true" onchange="getJenisPengguna(this.value);">
                                                        <s:option value="">Sila Pilih</s:option>
                                                        <s:options-collection collection="${list.kategoriPengguna}" label="perihal" value="kod"/>
                                                    </s:select>
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                            <div id="pelanggan">
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Jenis Pengenalan</label>
                                                    <div class="col-sm-6">
                                                        <s:select name="pengguna.kodJenisPengenalan" class="form-control" data-error="Sila Pilih Jenis Pengenalan!" required="true">
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${list.jenisPengenalan}" label="perihal" value="kod"/>
                                                        </s:select>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">ID Pengguna</label>
                                                    <div class="col-sm-6">
                                                        <input type="text" name="pengguna.idPengguna" class="form-control" id="idPengguna" maxlength="12" onkeyup="validateSpecialChtr(this, this.value);" data-error="Sila Masukkan ID Pengguna!" required>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Nama</label>
                                                    <div class="col-sm-6">
                                                        <input type="text" name="pengguna.nama" class="form-control" id="nama" data-error="Sila Masukkan Nama!" required>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Emel</label>
                                                    <div class="col-sm-6">
                                                        <input type="email" name="pengguna.emel" class="form-control" id="emel" data-error="Alamat e-mel tidak sah!" required>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div> 
                                            </div>
                                            <div id="pembekal">
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Kod Pembekal</label>
                                                    <div class="col-sm-6">
                                                        <input type="text" name="pengguna.idPengguna" class="form-control" id="idPengguna" maxlength="12" onkeyup="validateSpecialChtr(this, this.value);" data-error="Sila Masukkan Kod Pembekal!" required>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">No Daftar Syarikat</label>
                                                    <div class="col-sm-6">
                                                        <input type="text" name="noDaftarniaga" class="form-control" id="noDaftarniaga" data-error="Sila Masukkan No Daftar Syarikat!" required>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Nama Syarikat</label>
                                                    <div class="col-sm-6">
                                                        <input type="text" name="pengguna.nama" class="form-control" id="nama" data-error="Sila Masukkan Nama Syarikat!" required>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Emel</label>
                                                    <div class="col-sm-6">
                                                        <input type="email" name="pengguna.emel" class="form-control" id="emel" data-error="Alamat e-mel tidak sah!" required>
                                                        <div class="help-block with-errors"></div>
                                                    </div>
                                                </div> 
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">&nbsp;</label>
                                                <div class="col-sm-6">
                                                    <div class="g-recaptcha"
                                                         data-sitekey="6LfO4hgUAAAAAMMM5LnTszFgi_fy6EHYpjRvtpkF" required></div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-offset-2 col-sm-10">
                                                    <s:submit name="daftarBaruUser" value="Hantar" class="btn btn-primary" tabindex="1"/>&nbsp;  
                                                    <a href="${pageContext.request.contextPath}/main?register" class="btn btn-primary">Isi Semula</a>&nbsp;
                                                    <a href="${pageContext.request.contextPath}/welcome" class="btn btn-primary">Keluar</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </s:form>
            </div>
        </div>
    </body>
</html>
