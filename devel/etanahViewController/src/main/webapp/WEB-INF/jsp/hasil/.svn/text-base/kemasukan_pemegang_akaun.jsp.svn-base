<%-- 
    Document   : kemasukan_pemegang_akaun
    Created on : 4 Jan 2010
    Author     : Mohd Hairudin Mansur
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/popup.css"/>

<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>

<script type="text/javascript">
    $(document).ready( function(){
        $('.alphanumeric').alphanumeric();

        $('#selectJenisPengenalan').change(function () {
            $("#selectJenisPengenalan option:selected").each(function () {
                $('#kodPengenalan').val($(this).val());
                $('#jenisPengenalan').val($(this).text());
            });
            
        });
        
    });

    function addNew(){
        window.open("${pageContext.request.contextPath}/hasil/kemasukan_maklumat_akaun?showPemegangAkaunPopup", "eTanah",
        "status=0,toolbar=0,scrollbars=1,location=0,menubar=0,width=910,height=800");
    }
	    
    function save(event, f){
        $('#idPemegang',opener.document).val($('#idPemegang').val());
        $('#nama',opener.document).val($('#nama').val());
        $('#noPengenalan',opener.document).val($('#noPengenalan').val());
        $('#jenisPengenalan',opener.document).val($('#jenisPengenalan').val());
        $('#kodPengenalan',opener.document).val($('#kodPengenalan').val());
		
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            
        },'html');

        $.prompt('Maklumat Berjaya Disimpan.',{
            buttons:{Ok:true},
            prefix:'jqismooth',
            submit:function(v,m,f){
                self.close();
            }
        });
    }

    function dodacheck(val) {
        //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;
        var v = $('#jenisPengenalan').val();
        if(v == 'B') {
            var strPass = val;
            var strLength = strPass.length;
            if(strLength > 12) {
                var tst = val.substring(0, (strLength) - 1);
                $('#kp').val(tst);
            }
            var lchar = val.charAt((strLength) - 1);
            if(isNaN(lchar)) {
                //return false;
                var tst = val.substring(0, (strLength) - 1);
                $('#kp').val(tst);
            }
        }
    }

    function dodacheck1(val) {
        //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;
        var v = $('#selectJenisPengenalan').val();
        if(v == 'B') {
            var strPass = val;
            var strLength = strPass.length;
            if(strLength > 12) {
                var tst = val.substring(0, (strLength) - 1);
                $('#noPengenalan').val(tst);
            }
            var lchar = val.charAt((strLength) - 1);
            if(isNaN(lchar)) {
                //return false;
                var tst = val.substring(0, (strLength) - 1);
                $('#noPengenalan').val(tst);
            }
        }
    }

    function clearNoPengenalan(){
        $('#kp').val('');
    }

    function copyPAalamat(){
        if($('input[name=copy1]').is(':checked')){
            $('#paAlamat1').val('${actionBean.pemegangAkaun.alamat1}');
            $('#paAlamat2').val('${actionBean.pemegangAkaun.alamat2}');
            $('#paAlamat3').val('${actionBean.pemegangAkaun.alamat3}');
            $('#paBandar').val('${actionBean.pemegangAkaun.alamat4}');
            $('#paPoskod').val('${actionBean.pemegangAkaun.poskod}');
            $('#paNegeri').val('${actionBean.pemegangAkaun.negeri.kod}');
        }
    }

    function copyDFalamat(){
        if($('input[name=copy2]').is(':checked')){
            $('#stAlamat1').val($('#dfAlamat1').val());
            $('#stAlamat2').val($('#dfAlamat2').val());
            $('#stAlamat3').val($('#dfAlamat3').val());
            $('#stBandar').val($('#dfBandar').val());
            $('#stPoskod').val($('#dfPoskod').val());
            $('#stNegeri').val($('#dfNegeri').val());
        }
    }

    function copyPCalamat(){
        if($('input[name=copy2]').is(':checked')){
            $('#pcsAlamat1').val($('#pcdAlamat1').val());
            $('#pcsAlamat2').val($('#pcdAlamat2').val());
            $('#pcsAlamat3').val($('#pcdAlamat3').val());
            $('#pcsBandar').val($('#pcdBandar').val());
            $('#pcsPoskod').val($('#pcdPoskod').val());
            $('#pcsNegeri').val($('#pcdNegeri').val());
        }
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric( strString )  {
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
</script>

<style type="text/css">        
    input.error { background-color: green; }
</style>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>

<div class="subtitle">
    <s:form beanclass="etanah.view.stripes.hasil.KemasukanMaklumatAkaunActionBean" >

        <s:errors/>
        <s:messages/>

        <fieldset class="aras1">
            <c:if test="${!actionBean.showTambahCawangan}">
                <legend>Kemasukan Maklumat Pemegang Akaun</legend>
            </c:if>
            <c:if test="${actionBean.showTambahCawangan}">
                <legend>Kemasukan Cawangan Pemegang Akaun</legend>
            </c:if>

            <c:if test="${actionBean.showSearchForm}">
                <p>
                    <label for="Jenis Pengenalan">Jenis Pengenalan :</label>
                    <s:select name="jenisPengenalan" id="jenisPengenalan" onchange="clearNoPengenalan();">
                        <s:option value="0">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p>
                    <label for="No Pengenalan">No Pengenalan :</label>
                    <s:text name="noPengenalan" id="kp" onblur="this.value=this.value.toUpperCase();" onkeyup="dodacheck(this.value);" size="40"/>
                </p>
                <p>
                    <label for="Nama Pemegang">Nama Pemegang :</label>
                    <s:text name="namaPemegang" id="nama" onkeyup="this.value=this.value.toUpperCase();" size="40"/>
                </p>

                <p>
                    <label>&nbsp;</label>
                    <s:submit name="searchPemegangAkaun" value="Cari" class="btn"/>
                    <s:submit name="searchPemegangAkaun1" value="Tambah Baru" class="btn"/>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
            </c:if>

            <c:if test="${!actionBean.showSearchForm}">

                <!-- papar maklumat pemegang akaun -->
                <c:if test="${actionBean.showCarianPemegangAkaun}">
                    <s:hidden name="pemegangAkaun.idPihak" id="idPemegang" />
                    <p>
                        <label for="nama">Nama :</label>
                        <s:hidden name="pemegangAkaun.nama" id="nama"/>
                        ${actionBean.pemegangAkaun.nama}&nbsp;
                    </p>
                    <p>
                        <label for="Jenis Pengenalan">Jenis Pengenalan :</label>
                        <s:hidden name="pemegangAkaun.jenisPengenalan.nama" id="jenisPengenalan"/>
                        <s:hidden name="pemegangAkaun.jenisPengenalan.kod" id="kodPengenalan"/>
                        ${actionBean.pemegangAkaun.jenisPengenalan.nama}&nbsp;
                    </p>
                    <p>
                        <label for="No Pengenalan">No Pengenalan :</label>
                        <s:hidden name="pemegangAkaun.noPengenalan" id="noPengenalan"/>
                        ${actionBean.pemegangAkaun.noPengenalan}&nbsp;
                    </p>
                    <c:if test="${actionBean.pemegangAkaun.bangsa.kod ne null}">
                        <p>
                            <label>Kategori :</label>
                            ${actionBean.pemegangAkaun.bangsa.nama}&nbsp;
                        </p>
                    </c:if>

                    <p>
                        <label for="alamat">Alamat Berdaftar :</label>
                        ${actionBean.pemegangAkaun.alamat1}&nbsp;
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        ${actionBean.pemegangAkaun.alamat2}&nbsp;
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        ${actionBean.pemegangAkaun.alamat3}&nbsp;
                    </p>
                    <p>
                        <label>Bandar :</label>
                        ${actionBean.pemegangAkaun.alamat4}&nbsp;
                    </p>
                    <p>
                        <label>Poskod :</label>
                        ${actionBean.pemegangAkaun.poskod}&nbsp;
                    </p>

                    <p>
                        <label for="Negeri">Negeri :</label>
                        ${actionBean.pemegangAkaun.negeri.nama}&nbsp;
                    </p>
                    <p>
                        <label>No. Tel. Pejabat :</label>
                        ${actionBean.pemegangAkaun.noTelefon1}&nbsp;
                    </p>
                    <p>
                        <label>No. Tel. Bimbit :</label>
                        ${actionBean.pemegangAkaun.noTelefonBimbit}&nbsp;
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <input type="checkbox" id="copy1" name="copy1" value="" onclick="copyPAalamat();"/>
                        <font color="red">Alamat surat-menyurat sama seperti alamat berdaftar.</font>
                    </p>
                    <p>
                        <label for="alamat">Alamat Surat-Menyurat :</label>
                        <s:text id="paAlamat1" name="pemegangAkaun.suratAlamat1" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text id="paAlamat2" name="pemegangAkaun.suratAlamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text id="paAlamat3" name="pemegangAkaun.suratAlamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Bandar :</label>
                        <s:text id="paBandar" name="pemegangAkaun.suratAlamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Poskod :</label>
                        <s:text id="paPoskod" name="pemegangAkaun.suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                    </p>

                    <p>
                        <label for="Negeri">Negeri :</label>
                        <s:select id="paNegeri" name="pemegangAkaun.suratNegeri.kod">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <p>
                        <label>No. Tel. Pejabat :</label>
                        <s:text name="pemegangAkaun.noTelefon1" size="40" maxlength="12"/>
                    </p>
                    <p>
                        <label>No. Tel. Bimbit :</label>
                        <s:text name="pemegangAkaun.noTelefonBimbit" size="40" maxlength="12"/>
                    </p>

                    <!-- maklumat cawangan -->
                    <div class="content" align="center">
                        <br>
							Maklumat Cawangan
                        <display:table name="${actionBean.cawanganList}" id="line" class="tablecloth" >

                            <display:column title="No">${line_rowNum}</display:column>
                            <display:column property="namaCawangan" title="Nama"/>
                            <display:column title="Alamat" >${line.suratAlamat1}
                                <c:if test="${line.suratAlamat2 ne null}"> , </c:if>
                                ${line.suratAlamat2}
                                <c:if test="${line.suratAlamat3 ne null}"> , </c:if>
                                ${line.suratAlamat3}
                                <c:if test="${line.suratAlamat4 ne null}"> , </c:if>
                                ${line.suratAlamat4}
                            </display:column>
                            <display:column property="suratPoskod" title="Poskod" />
                            <display:column property="suratNegeri.nama" title="Negeri" />

                        </display:table>
                    </div>

                </c:if>

                <c:if test="${!actionBean.showTambahCawangan}">
                    <!-- carian pemegang akaun - tiada rekod; papar cipta rekod baru -->
                    <c:if test="${!actionBean.showCarianPemegangAkaun}">
                        <s:hidden name="pemegangAkaun.idPihak" id="idPemegang" />
                        <p>
                            <label for="nama"><font color="red">*</font>Nama :</label>
                            <s:text name="pemegangAkaun.nama" id="nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>

                        <p>
                            <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                            <s:hidden name="pemegangAkaun.jenisPengenalan.nama" id="jenisPengenalan" />
                            <s:hidden name="pemegangAkaun.jenisPengenalan.kod" id="kodPengenalan" />
                            <s:select name="pemegangAkaun.jenisPengenalan.kod" id="selectJenisPengenalan" onchange="clearNoPengenalan();">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <p>
                            <label for="No Pengenalan"><font color="red">*</font>No Pengenalan :</label>
                            <s:text name="pemegangAkaun.noPengenalan" id="noPengenalan" onblur="this.value=this.value.toUpperCase();" onkeyup="dodacheck1(this.value);" size="40"/>
                        </p>

                        <p>
                            <label>Kategori :</label>
                            <s:select name="pemegangAkaun.bangsa.kod">
                                <s:option>Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodBangsa}" label="nama" value="kod" />
                            </s:select>
                        </p>

                        <p>
                            <label for="alamat">Alamat Berdaftar :</label>
                            <s:text id="dfAlamat1" name="pemegangAkaun.alamat1" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>

                        <p>
                            <label> &nbsp;</label>
                            <s:text id="dfAlamat2" name="pemegangAkaun.alamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>

                        <p>
                            <label> &nbsp;</label>
                            <s:text id="dfAlamat3" name="pemegangAkaun.alamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Bandar :</label>
                            <s:text id="dfBandar" name="pemegangAkaun.alamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Poskod :</label>
                            <s:text id="dfPoskod" name="pemegangAkaun.poskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select id="dfNegeri" name="pemegangAkaun.negeri.kod">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <p>
                            <label>No. Tel. Pejabat :</label>
                            <s:text name="pemegangAkaun.noTelefon1" size="40" maxlength="12"/>
                        </p>
                        <p>
                            <label>No. Tel. Bimbit :</label>
                            <s:text name="pemegangAkaun.noTelefonBimbit" size="40" maxlength="12"/>
                        </p>
                        <p>
                            <label>&nbsp;</label>
                            <input type="checkbox" id="copy2" name="copy2" value="" onclick="copyDFalamat();"/>
                            <font color="red">Alamat surat-menyurat sama seperti alamat berdaftar.</font>
                        </p>
                        <p>
                            <label for="alamat">Alamat Surat-Menyurat :</label>
                            <s:text id="stAlamat1" name="pemegangAkaun.suratAlamat1" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text id="stAlamat2" name="pemegangAkaun.suratAlamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text id="stAlamat3" name="pemegangAkaun.suratAlamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Bandar :</label>
                            <s:text id="stBandar" name="pemegangAkaun.suratAlamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Poskod :</label>
                            <s:text id="stPoskod" name="pemegangAkaun.suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select id="stNegeri" name="pemegangAkaun.suratNegeri.kod">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                    </c:if>

                    <br/>

                    <p>
                        <label>&nbsp;</label>
                        <s:button name="simpanPemegangAkaunPopup" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                        <s:button name="new" value="Kembali" class="btn" id="new" onclick="addNew();"/>
                        <%--<s:submit name="tambahCawangan" value="Tambah Cawangan" class="btn"/>--%>
                    </p>
                </c:if>

                <c:if test="${actionBean.showTambahCawangan}">
                    <!-- Tambah cawangan baru -->
                    <s:hidden name="pemegangAkaun.idPihak" id="idPemegang" />
                    <p>
                        <label for="nama"><font color="red">*</font>Nama :</label>
                        <s:text name="pihakCawangan.namaCawangan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label for="alamat">Alamat Berdaftar :</label>
                        <s:text id="pcdAlamat1" name="pihakCawangan.alamat1" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text id="pcdAlamat2" name="pihakCawangan.alamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        <s:text id="pcdAlamat3" name="pihakCawangan.alamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Bandar :</label>
                        <s:text id="pcdBandar" name="pihakCawangan.alamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Poskod :</label>
                        <s:text id="pcdPoskod" name="pihakCawangan.poskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                    </p>

                    <p>
                        <label for="Negeri">Negeri :</label>
                        <s:select id="pcdNegeri" name="pihakCawangan.negeri.kod">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                        </s:select>

                    </p>
                    <p>
                        <label>No. Tel. Pejabat :</label>
                        <s:text name="pihakCawangan.noTelefon1" size="40" maxlength="12"/>
                    </p>
                    <p>
                        <label>No. Tel. Bimbit :</label>
                        <s:text name="pihakCawangan.noTelefonBimbit" size="40" maxlength="12"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <input type="checkbox" id="copy3" name="copy3" value="" onclick="copyPCalamat();"/>
                        <font color="red">Alamat surat-menyurat sama seperti alamat berdaftar.</font>
                    </p>
                    <p>
                        <label for="alamat">Alamat Surat-Menyurat :</label>
                        <s:text id="pcsAlamat1" name="pihakCawangan.suratAlamat1" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text id="pcsAlamat2" name="pihakCawangan.suratAlamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text id="pcsAlamat3" name="pihakCawangan.suratAlamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Bandar :</label>
                        <s:text id="pcsBandar" name="pihakCawangan.suratAlamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Poskod :</label>
                        <s:text id="pcsPoskod" name="pihakCawangan.suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                    </p>

                    <p>
                        <label for="Negeri">Negeri :</label>
                        <s:select id="pcsNegeri" name="pihakCawangan.suratNegeri.kod">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                        </s:select>
                    </p>

                    <p>
                        <label>&nbsp;</label>

                        <s:button name="simpanCawangan" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </p>
                </c:if>

            </c:if>
            <br>

        </fieldset>

    </s:form>
</div>