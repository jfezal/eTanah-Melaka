<%-- 
    Document   : bahan_batuan_mlkns
    Created on : Dec 24, 2011, 1:19:28 PM
    Author     : Srinivas
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"/>
<script type="text/javascript">

    $(document).ready( function(){

        var one = document.getElementById('kod1').value;
        var two = document.getElementById('kod2').value;
        var three = document.getElementById('kod3').value;

        if(one == 'KB'){
            //                alert(document.getElementById('mandatori2').value);
            document.getElementById('kodItem1').checked = 'true';
        }
        if(two == 'PB'){
            document.getElementById('kodItem2').checked = 'true';
        }
        if(three == 'MB'){
            document.getElementById('kodItem3').checked = 'true';
        }

        /* if($('#bpm').val() == ""){
                alert("Sila masukkan Bandar/Pekan/Mukim terdahulu di bahagian tanah") ;
            }*/
    });
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function isNumberKey(evt)
    {
        var charCode = (evt.which) ? evt.which : event.keyCode
        if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

        return true;
    }
    function validation() {
        if($('#kodnegeri').val() != "05"){
            if($('#test').val() == ""){
                alert("Sila Masukkan Jenis Bahan Batuan") ;
                return false ;
            }
            if($('#tempoh').val() == ""){
                alert("Sila Masukkan Jangka Masa") ;
                return false ;
            }
            if($('#tempohUOM').val() == ""){
                alert("Sila Masukkan Unit Jangka Masa") ;
                return false ;
            }
            if($('#isipadu').val() == ""){
                alert("Sila Masukkan Isipadu Dipohon") ;
                return false ;
            }
            //    alert(document.batuan.urusan.value) ;
            if(document.batuan.urusan.value == "PBPTG"){
                if($('#isipadu').val() <= 1000 || $('#isipadu').val() > 3000){
                    alert("Sila Masukkan Isipadu Dipohon antara 1000 hingga 3000 untuk urusan ini") ;
                    return false ;
                }
            }
            //     if(document.batuan.urusan.value == "PBPTD"){
            //         if($('#isipadu').val() > 1000){
            //        alert("Sila Masukkan Isipadu Dipohon kurang dari 1000 untuk urusan ini") ;
            //        return false ;
            //    }
            //     }

            if($('#isipaduUOM').val() == ""){
                alert("Sila Masukkan Unit Isipadu Dipohon") ;
                return false ;
            }

            if($('#sbb').val() == ""){
                $('#sbb').val("Tiada") ;
                return true ;
            }
        }
        return true ;
    }

    function insertValue() {
        alert(document.getElementById('testing').checked);

    }

    function resetSendiri() {

        document.getElementById('kodItem1').checked = '';
        document.getElementById('kodItem2').checked = '';
        document.getElementById('kodItem3').checked = '';
        $('#test').val("") ;
        $('#sbb').val("") ;
        $('#ambil').val("") ;
        $('#pindah').val("") ;
        $('#sbb').val("") ;
        $('#jalan').val("") ;
        //$('#lbr').val("") ;
        //$('#pnjg').val("") ;
        $('#datepicker').val("") ;
        $('#datepicker2').val("") ;
        $('#tempoh').val("") ;
        $('#isipadu').val("") ;
        //$('#pekerja').val("") ;
        $('#jenisJentera').val("") ;
        $('#kapasiti').val("") ;


    }


    jQuery.fn.ForceNumericOnly = function() {
        return this.each(function()     {
            $(this).keydown(function(e)         {
                var key = e.charCode || e.keyCode || 0;             // allow backspace, tab, delete, arrows, numbers and keypad numbers ONLY
                return (
                key == 8 ||
                    key == 9 ||
                    key == 46 ||
                    (key >= 37 && key <= 40) ||
                    (key >= 48 && key <= 57) ||
                    (key >= 96 && key <= 105));
            });
        });
    };
    jQuery('.numbersOnly').ForceNumericOnly();

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"  />

<s:form beanclass="etanah.view.stripes.pelupusan.ButiranBahanBatuanActionBean" name="batuan" id="batuan">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Tujuan Permohonan</legend>
            <s:hidden name="test1" id="kod1"/>
            <s:hidden name="test12" id="kod2"/>
            <s:hidden name="test13" id="kod3"/>
            <p>
                Sila isikan maklumat yang bertanda <font color="red">*</font>
            </p><br/>
            <table class="tablecloth" border="2" align="center">
                <tr>
                    <td><font color="red">*</font>Sila pilih sekurang-kurangnya satu daripada tujuan permohonan.</td>
                </tr>
                <tr>
                    <td><s:checkbox name="kodItem1" id="kodItem1" />Mengeluarkan bahan batuan</td>
                </tr>
                <tr>
                    <td><s:checkbox name="kodItem2" id="kodItem2"/>Memproses bahan batuan</td>
                </tr>
                <tr>
                    <td><s:checkbox name="kodItem3" id="kodItem3" />Memindahkan bahan batuan</td>
                </tr>
            </table>
            </p>
        </fieldset>
    </div><br/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Bahan Batuan Dipohon</legend>
            <s:hidden name="permohonan.cawangan.daerah.nama" value="${actionBean.permohonan.cawangan.daerah.nama}"/>
            <s:hidden name="permohonan.kodUrusan.kod" value="${actionBean.permohonan.kodUrusan.kod}" id="urusan"/>
            <s:hidden name="hakmilikPermohonan.bandarPekanMukimBaru.nama" value="${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama}" id="bpm"/>
            <%-- <p>
                 <label>Daerah :</label>
                 ${actionBean.permohonan.cawangan.daerah.nama}
             </p>
             <p>
                     <label>Mukim/Pekan/Bandar :</label>
                     ${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama}
              </p>--%>
            <%--<p>
                   <label><font color="red">*</font>Mukim/Pekan/Bandar :</label>
                   <s:select name="bandarPekanMukimBaru.kod" value="${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.kod}" id="mpb">
                       <s:option value="">Sila Pilih</s:option>
                       <s:options-collection collection="${actionBean.senaraiKodBPM}" label="nama" value="kod" />
                   </s:select>
            </p>--%>
            <c:if test="${actionBean.kodNegeri eq '05'}">
                <c:if test="${actionBean.stageId eq 'kemasukan'}" >

                    <p>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTG'}">
                            <label><font color="red">*</font>Jenis :</label>
                                <%--<s:text name="permohonanBahanBatuan.jenisBahanBatu" size="50"/>--%>
                                <s:select name="jenisBahanBatu.kod" value="${actionBean.permohonanBahanBatuan.jenisBahanBatu.kod}" id="test">
                                    <s:option value="">Sila Pilih</s:option>
                                <s:option value="PS">Pasir</s:option>
                                <s:option value="BG">Batu, Batuan, Batu Granit</s:option>
                                <%--<s:option value="LN">Lain-lain</s:option>--%>
                            </s:select>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMMK'}">
                            <label><font color="red">*</font>Jenis :</label>
                                <%--<s:text name="permohonanBahanBatuan.jenisBahanBatu" size="50"/>--%>
                                <s:select name="jenisBahanBatu.kod" value="${actionBean.permohonanBahanBatuan.jenisBahanBatu.kod}" id="test">
                                    <s:option value="">Sila Pilih</s:option>
                                <s:option value="PS">Pasir</s:option>
                                <s:option value="PU">Pasir Sungai</s:option>
                                <s:option value="PD">Pasir Daratan</s:option>
                                <s:option value="BG">Batu, Batuan, Batu Granit</s:option>
                                <s:option value="BK">Batu Kapur</s:option>
                                <s:option value="TL">Tanah Liat</s:option>
                                <%--<s:option value="LN">Lain-lain</s:option>--%>
                            </s:select>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                            <label><font color="red">*</font>Jenis :</label>
                                <%--<s:text name="permohonanBahanBatuan.jenisBahanBatu" size="50"/>--%>
                                <s:select name="jenisBahanBatu.kod" value="${actionBean.permohonanBahanBatuan.jenisBahanBatu.kod}" id="test">
                                    <s:option value="">Sila Pilih</s:option>
                                <s:option value="PS">Pasir</s:option>
                                <s:option value="PU">Pasir Sungai</s:option>
                                <s:option value="PD">Pasir Daratan</s:option>
                                <s:option value="BG">Batu, Batuan, Batu Granit</s:option>
                                <s:option value="BK">Batu Kapur</s:option>
                                <s:option value="TL">Tanah Liat</s:option>
                                <%--<s:option value="LN">Lain-lain</s:option>--%>
                            </s:select>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTD'}">
                            <label><font color="red">*</font>Jenis :</label>
                                <%--<s:text name="permohonanBahanBatuan.jenisBahanBatu" size="50"/>--%>
                                <s:select name="jenisBahanBatu.kod" value="${actionBean.permohonanBahanBatuan.jenisBahanBatu.kod}" id="test">
                                    <s:option value="">Sila Pilih</s:option>
                                <s:option value="BT">Batu Bata</s:option>
                                <s:option value="RP">Rumput</s:option>
                                <s:option value="LT">Tanah Laterit</s:option>
                                <s:option value="TN">Tanah</s:option>
                                <s:option value="TG">Tanah Gambut</s:option>
                                <s:option value="TM">Tanah Merah</s:option>
                                <s:option value="TL">Tanah Liat</s:option>
                                <%--<s:option value="LN">Lain-lain</s:option>--%>
                            </s:select>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB'}">
                            <label><font color="red">*</font>Jenis :</label>
                                <%--<s:text name="permohonanBahanBatuan.jenisBahanBatu" size="50"/>--%>
                                <s:select name="jenisBahanBatu.kod" value="${actionBean.permohonanBahanBatuan.jenisBahanBatu.kod}" id="test">
                                    <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodItemPermitBatuan}" label="nama" value="kod" />
                            </s:select>
                        </c:if>
                        <s:hidden name="permohonan.cawangan.kod" id="kodnegeri"/>
                    </p>
                    <p>
                        <label>Tujuan Pengeluaran :</label>
                        <s:textarea name="permohonan.sebab" rows="5" cols="50" class="normal_text" id="sbb"/>
                    </p>
                    <p>
                        <label>Kawasan pengambilan :</label>
                        <s:text name="permohonanBahanBatuan.kawasanAmbilBatuan" id="ambil" size="20" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;(No. Lot (Jika ada))
                    </p>
                    <p>
                        <label>Kawasan pemindahan :</label>
                        <s:text name="permohonanBahanBatuan.kawasanPindahBatuan" id="pindah" size="20" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;(No.Lot (Jika ada))
                    </p>
                    <p>
                        <label>Jalan yang dilalui :</label>
                        <s:text name="permohonanBahanBatuan.jalanDilalui" id="jalan" size="50" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>

                    <p>
                        <label>Tempoh pengeluaran :</label>
                        <s:text name="permohonanBahanBatuan.tarikhMula"  id="datepicker" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy"/>
                        &nbsp;<font color="#003194"><b>hingga</b></font>&nbsp;
                        <s:text name="permohonanBahanBatuan.tarikhTamat" id= "datepicker2" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy"/>
                    </p>
                    <p>
                        <label><font color="red">*</font>Jangka Masa :</label>
                            <s:text name="permohonanBahanBatuan.tempohKeluar" size="20" id="tempoh" maxlength="3" onkeyup="validateNumber(this,this.value);" value="${actionBean.permohonanBahanBatuan.tempohKeluar}"/>
                            <s:select name="unitTempohKeluarUOM" id="tempohUOM" value="${actionBean.permohonanBahanBatuan.unitTempohKeluar.kod}">
                                <s:option value="">Sila Pilih</s:option>
                            <s:option value="HR">Hari</s:option>
                            <s:option value="B">Bulan</s:option>
                        </s:select>
                    </p>
                    <p>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB' or actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                            <label><font color="red">*</font>Jumlah isipadu :</label>
                                <s:text name="permohonanBahanBatuan.jumlahIsipaduDipohon" id="isipadu" size="20" maxlength="6" onkeypress="return isNumberKey(event)"/>
                                <s:select name="jumlahIsipaduDipohonUom.kod" id="isipaduUOM" value="${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.kod}">
                                    <s:option value="">Sila Pilih</s:option>
                                <s:option value="MP">Meterpadu</s:option>
                                <s:option value="KB">Ketul Batu</s:option>
                            </s:select>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTG'}">
                            <label><font color="red">*</font>Jumlah isipadu :</label>
                                <s:text name="permohonanBahanBatuan.jumlahIsipaduDipohon" id="isipadu" size="20" maxlength="6" onkeypress="return isNumberKey(event)"/>
                                <s:select name="jumlahIsipaduDipohonUom.kod" id="isipaduUOM" value="${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.kod}">
                                    <s:option value="">Sila Pilih</s:option>
                                <s:option value="MP">Meterpadu</s:option>
                            </s:select>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTD'}">
                            <label><font color="red">*</font>Jumlah isipadu :</label>
                                <s:text name="permohonanBahanBatuan.jumlahIsipaduDipohon" id="isipadu" size="20" maxlength="6" onkeypress="return isNumberKey(event)"/>
                                <s:select name="jumlahIsipaduDipohonUom.kod" id="isipaduUOM" value="${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.kod}">
                                    <s:option value="">Sila Pilih</s:option>
                                <s:option value="MP">Meterpadu</s:option>
                            </s:select>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMMK'}">
                            <label><font color="red">*</font>Jumlah isipadu :</label>
                                <s:text name="permohonanBahanBatuan.jumlahIsipaduDipohon" id="isipadu" size="20" maxlength="6" onkeypress="return isNumberKey(event)"/>
                                <s:select name="jumlahIsipaduDipohonUom.kod" id="isipaduUOM" value="${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.kod}">
                                    <s:option value="">Sila Pilih</s:option>
                                <s:option value="MP">Meterpadu</s:option>
                            </s:select>
                        </c:if>
                    </p>
                    <br/>
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="testing33" value="Isi Semula" class="btn" onclick="resetSendiri();"/>
                        <s:button name="simpanNS" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                    </p>

                </c:if>

                <c:if test="${actionBean.stageId eq '01Kemasukan' || actionBean.stageId eq '01kemasukan'}" >

                    <p>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTG'}">
                            <label><font color="red">*</font>Jenis :</label>
                                <%--<s:text name="permohonanBahanBatuan.jenisBahanBatu" size="50"/>--%>
                                <s:select name="jenisBahanBatu.kod" value="${actionBean.permohonanBahanBatuan.jenisBahanBatu.kod}" id="test">
                                    <s:option value="">Sila Pilih</s:option>
                                <s:option value="PS">Pasir</s:option>
                                <s:option value="BG">Batu, Batuan, Batu Granit</s:option>
                                <%--<s:option value="LN">Lain-lain</s:option>--%>
                            </s:select>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMMK'}">
                            <label><font color="red">*</font>Jenis :</label>
                                <%--<s:text name="permohonanBahanBatuan.jenisBahanBatu" size="50"/>--%>
                                <s:select name="jenisBahanBatu.kod" value="${actionBean.permohonanBahanBatuan.jenisBahanBatu.kod}" id="test">
                                    <s:option value="">Sila Pilih</s:option>
                                <s:option value="PS">Pasir</s:option>
                                <s:option value="PU">Pasir Sungai</s:option>
                                <s:option value="PD">Pasir Daratan</s:option>
                                <s:option value="BG">Batu, Batuan, Batu Granit</s:option>
                                <s:option value="BK">Batu Kapur</s:option>
                                <s:option value="TL">Tanah Liat</s:option>
                                <%--<s:option value="LN">Lain-lain</s:option>--%>
                            </s:select>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTD'}">
                            <label><font color="red">*</font>Jenis :</label>
                                <%--<s:text name="permohonanBahanBatuan.jenisBahanBatu" size="50"/>--%>
                                <s:select name="jenisBahanBatu.kod" value="${actionBean.permohonanBahanBatuan.jenisBahanBatu.kod}" id="test">
                                    <s:option value="">Sila Pilih</s:option>
                                <s:option value="BT">Batu Bata</s:option>
                                <s:option value="RP">Rumput</s:option>
                                <s:option value="LT">Tanah Laterit</s:option>
                                <s:option value="TN">Tanah</s:option>
                                <s:option value="TG">Tanah Gambut</s:option>
                                <s:option value="TM">Tanah Merah</s:option>
                                <s:option value="TL">Tanah Liat</s:option>
                                <%--<s:option value="LN">Lain-lain</s:option>--%>
                            </s:select>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                            <label><font color="red">*</font>Jenis :</label>
                                <s:select name="jenisBahanBatu.kod" value="${actionBean.permohonanBahanBatuan.jenisBahanBatu.kod}" id="test">
                                    <s:option value="">Sila Pilih</s:option>
                                <s:option value="PS">Pasir</s:option>
                                <s:option value="PU">Pasir Sungai</s:option>
                                <s:option value="PD">Pasir Daratan</s:option>
                                <s:option value="BG">Batu, Batuan, Batu Granit</s:option>
                                <s:option value="BK">Batu Kapur</s:option>
                                <s:option value="TL">Tanah Liat</s:option>
                                <%--<s:option value="LN">Lain-lain</s:option>--%>
                            </s:select>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB'}">
                            <label><font color="red">*</font>Jenis :</label>
                                <%--<s:text name="permohonanBahanBatuan.jenisBahanBatu" size="50"/>--%>
                                <s:select name="jenisBahanBatu.kod" value="${actionBean.permohonanBahanBatuan.jenisBahanBatu.kod}" id="test">
                                    <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodItemPermitBatuan}" label="nama" value="kod" />
                            </s:select>
                        </c:if>
                        <s:hidden name="permohonan.cawangan.kod" id="kodnegeri"/>
                    </p>
                    <p>
                        <label><font color="red">*</font>Tujuan Pengeluaran :</label>
                            <s:textarea name="permohonan.sebab" rows="5" cols="50" class="normal_text" id="sbb"/>
                    </p>
                    <p>
                        <label><font color="red">*</font>Kawasan pengambilan :</label>
                        <s:text name="permohonanBahanBatuan.kawasanAmbilBatuan" id="ambil" size="20" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;(No. Lot (Jika ada))
                    </p>
                    <p>
                        <label>Kawasan pemindahan :</label>
                        <s:text name="permohonanBahanBatuan.kawasanPindahBatuan" id="pindah" size="20" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;(No.Lot (Jika ada))
                    </p>
                    <p>
                        <label><font color="red">*</font>Jalan yang dilalui :</label>
                            <s:text name="permohonanBahanBatuan.jalanDilalui" id="jalan" size="50" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>

                    <p>
                        <label><font color="red">*</font>Tempoh pengeluaran :</label>
                            <s:text name="permohonanBahanBatuan.tarikhMula"  id="datepicker" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy"/>
                        &nbsp;<font color="#003194"><b>hingga</b></font>&nbsp;
                        <s:text name="permohonanBahanBatuan.tarikhTamat" id= "datepicker2" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy"/>
                    </p>
                    <p>
                        <label><font color="red">*</font>Jangka Masa :</label>
                            <s:text name="permohonanBahanBatuan.tempohKeluar" size="20" id="tempoh" maxlength="3" onkeyup="validateNumber(this,this.value);" value="${actionBean.permohonanBahanBatuan.tempohKeluar}"/>
                            <s:select name="unitTempohKeluarUOM" id="tempohUOM" value="${actionBean.permohonanBahanBatuan.unitTempohKeluar.kod}">
                                <s:option value="">Sila Pilih</s:option>
                            <s:option value="HR">Hari</s:option>
                            <s:option value="B">Bulan</s:option>
                        </s:select>
                    </p>
                    <p>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB' or actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                            <label><font color="red">*</font>Jumlah isipadu:</label>
                                <s:text name="permohonanBahanBatuan.jumlahIsipaduDipohon" id="isipadu" size="20" maxlength="6" onkeypress="return isNumberKey(event)"/>
                                <s:select name="jumlahIsipaduDipohonUom.kod" id="isipaduUOM" value="${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.kod}">
                                    <s:option value="">Sila Pilih</s:option>
                                <s:option value="MP">Meterpadu</s:option>
                                <s:option value="KB">Ketul Batu</s:option>
                            </s:select>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTG'}">
                            <label><font color="red">*</font>Jumlah isipadu :</label>
                                <s:text name="permohonanBahanBatuan.jumlahIsipaduDipohon" id="isipadu" size="20" maxlength="6" onkeypress="return isNumberKey(event)"/>
                                <s:select name="jumlahIsipaduDipohonUom.kod" id="isipaduUOM" value="${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.kod}">
                                    <s:option value="">Sila Pilih</s:option>
                                <s:option value="MP">Meterpadu</s:option>
                            </s:select>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTD'}">
                            <label><font color="red">*</font>Jumlah isipadu :</label>
                                <s:text name="permohonanBahanBatuan.jumlahIsipaduDipohon" id="isipadu" size="20" maxlength="6" onkeypress="return isNumberKey(event)"/>
                                <s:select name="jumlahIsipaduDipohonUom.kod" id="isipaduUOM" value="${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.kod}">
                                    <s:option value="">Sila Pilih</s:option>
                                <s:option value="MP">Meterpadu</s:option>
                            </s:select>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMMK'}">
                            <label><font color="red">*</font>Jumlah isipadu :</label>
                                <s:text name="permohonanBahanBatuan.jumlahIsipaduDipohon" id="isipadu" size="20" maxlength="6" onkeypress="return isNumberKey(event)"/>
                                <s:select name="jumlahIsipaduDipohonUom.kod" id="isipaduUOM" value="${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.kod}">
                                    <s:option value="">Sila Pilih</s:option>
                                <s:option value="MP">Meterpadu</s:option>
                            </s:select>
                        </c:if>
                    </p>
                    <br/>
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="testing33" value="Isi Semula" class="btn" onclick="resetSendiri();"/>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMMK' or actionBean.permohonan.kodUrusan.kod eq 'PBPTD' or actionBean.permohonan.kodUrusan.kod eq 'PBPTG' or actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                            <s:button name="simpanNSBahanBatuan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PBMMK' and actionBean.permohonan.kodUrusan.kod ne 'PBPTD' and actionBean.permohonan.kodUrusan.kod ne 'PBPTG' and actionBean.permohonan.kodUrusan.kod ne 'LPSP'}">
                            <s:button name="simpanNS" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                        </c:if>

                    </p>

                </c:if>

                <c:if test="${actionBean.stageId ne 'kemasukan' && actionBean.stageId ne '01Kemasukan' && actionBean.stageId ne '01kemasukan'}">

                    <p>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTG'}">
                            <label><font color="red">*</font>Jenis :</label>
                                <%--<s:text name="permohonanBahanBatuan.jenisBahanBatu" size="50"/>--%>
                                <s:select name="jenisBahanBatu.kod" value="${actionBean.permohonanBahanBatuan.jenisBahanBatu.kod}" id="test" disabled="true">
                                    <s:option value="">Sila Pilih</s:option>
                                <s:option value="PS">Pasir</s:option>
                                <s:option value="BG">Batu, Batuan, Batu Granit</s:option>
                                <%--<s:option value="LN">Lain-lain</s:option>--%>
                            </s:select>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMMK'}">
                            <label><font color="red">*</font>Jenis :</label>
                                <%--<s:text name="permohonanBahanBatuan.jenisBahanBatu" size="50"/>--%>
                                <s:select name="jenisBahanBatu.kod" value="${actionBean.permohonanBahanBatuan.jenisBahanBatu.kod}" id="test" disabled="true">
                                    <s:option value="">Sila Pilih</s:option>
                                <s:option value="PS">Pasir</s:option>
                                <s:option value="PU">Pasir Sungai</s:option>
                                <s:option value="PD">Pasir Daratan</s:option>
                                <s:option value="BG">Batu, Batuan, Batu Granit</s:option>
                                <s:option value="BK">Batu Kapur</s:option>
                                <s:option value="TL">Tanah Liat</s:option>
                                <%--<s:option value="LN">Lain-lain</s:option>--%>
                            </s:select>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTD'}">
                            <label><font color="red">*</font>Jenis :</label>
                                <%--<s:text name="permohonanBahanBatuan.jenisBahanBatu" size="50"/>--%>
                                <s:select name="jenisBahanBatu.kod" value="${actionBean.permohonanBahanBatuan.jenisBahanBatu.kod}" id="test" disabled="true">
                                    <s:option value="">Sila Pilih</s:option>
                                <s:option value="BT">Batu Bata</s:option>
                                <s:option value="RP">Rumput</s:option>
                                <s:option value="LT">Tanah Laterit</s:option>
                                <s:option value="TN">Tanah</s:option>
                                <s:option value="TG">Tanah Gambut</s:option>
                                <s:option value="TM">Tanah Merah</s:option>
                                <s:option value="TL">Tanah Liat</s:option>
                                <%--<s:option value="LN">Lain-lain</s:option>--%>
                            </s:select>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB'}">
                            <label><font color="red">*</font>Jenis :</label>
                                <%--<s:text name="permohonanBahanBatuan.jenisBahanBatu" size="50"/>--%>
                                <s:select name="jenisBahanBatu.kod" value="${actionBean.permohonanBahanBatuan.jenisBahanBatu.kod}" id="test" disabled="true">
                                    <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodItemPermitBatuan}" label="nama" value="kod" />
                            </s:select>
                        </c:if>

                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                        <p>
                            <label>Jenis : </label>
                            ${actionBean.permohonanBahanBatuan.jenisBahanBatu.nama}
                        </p>

                    </c:if>
                    <s:hidden name="permohonan.cawangan.kod" id="kodnegeri"/>


                    <p>
                        <label><font color="red">*</font>Tujuan Pengeluaran :</label>
                            ${actionBean.permohonan.sebab}
                        &nbsp;
                    </p>
                    <p>
                        <label><font color="red">*</font>Kawasan pengambilan :</label>
                            ${actionBean.permohonanBahanBatuan.kawasanAmbilBatuan}
                        &nbsp;
                    </p>
                    <p>
                        <label>Kawasan pemindahan :</label>
                        ${actionBean.permohonanBahanBatuan.kawasanPindahBatuan}
                        &nbsp;
                    </p>
                    <p>
                        <label><font color="red">*</font>Jalan yang dilalui :</label>
                            ${actionBean.permohonanBahanBatuan.jalanDilalui}
                        &nbsp;
                    </p>

                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'LPSP'}">
                        <p>
                            <label><font color="red">*</font>Tempoh pengeluaran :</label>
                                <s:text name="permohonanBahanBatuan.tarikhMula"  id="datepicker" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" readonly="true" disabled="true"/>
                            &nbsp;<font color="#003194"><b>hingga</b></font>&nbsp;
                            <s:text name="permohonanBahanBatuan.tarikhTamat" id= "datepicker2" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" readonly="true" disabled="true"/>
                        </p>
                        <p>
                            <label><font color="red">*</font>Jangka Masa :</label>
                                ${actionBean.permohonanBahanBatuan.tempohKeluar}

                            <s:select name="unitTempohKeluarUOM" id="tempohUOM" value="${actionBean.permohonanBahanBatuan.unitTempohKeluar.kod}" disabled="true">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="HR">Hari</s:option>
                                <s:option value="B">Bulan</s:option>
                            </s:select>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                        <p>
                            <label><font color="red">*</font>Tempoh pengeluaran :</label>
                                <s:text name="permohonanBahanBatuan.tarikhMula"  id="datepicker" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" readonly="true" disabled="true"/>
                            &nbsp;<font color="#003194"><b>hingga</b></font>&nbsp;
                            <s:text name="permohonanBahanBatuan.tarikhTamat" id= "datepicker2" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" readonly="true" disabled="true"/>
                        </p>
                        <p>
                            <label><font color="red">*</font>Jangka Masa :</label>
                                ${actionBean.permohonanBahanBatuan.tempohKeluar} ${actionBean.permohonanBahanBatuan.unitTempohKeluar.nama}
                        </p>
                    </c:if>
                    <p>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB'}">
                            <label><font color="red">*</font>Jumlah isipadu :</label>
                                ${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohon}
                                <s:select name="jumlahIsipaduDipohonUom.kod" id="isipaduUOM" value="${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.kod}" disabled="true">
                                    <s:option value="">Sila Pilih</s:option>
                                <s:option value="MP">Meterpadu</s:option>
                                <s:option value="KB">Ketul Batu</s:option>
                            </s:select>
                        </c:if>

                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                            <label><font color="red">*</font>Jumlah isipadu :</label>
                                ${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohon} ${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.nama}
                            </c:if>

                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTG'}">
                            <label><font color="red">*</font>Jumlah isipadu :</label>
                                ${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohon}
                                <s:select name="jumlahIsipaduDipohonUom.kod" id="isipaduUOM" value="${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.kod}" disabled="true">
                                    <s:option value="">Sila Pilih</s:option>
                                <s:option value="MP">Meterpadu</s:option>
                            </s:select>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTD'}">
                            <label><font color="red">*</font>Jumlah isipadu :</label>
                                ${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohon}
                                <s:select name="jumlahIsipaduDipohonUom.kod" id="isipaduUOM" value="${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.kod}" disabled="true">
                                    <s:option value="">Sila Pilih</s:option>
                                <s:option value="MP">Meterpadu</s:option>
                            </s:select>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMMK'}">
                            <label><font color="red">*</font>Jumlah isipadu :</label>
                                ${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohon}
                                <s:select name="jumlahIsipaduDipohonUom.kod" id="isipaduUOM" value="${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.kod}" disabled="true">
                                    <s:option value="">Sila Pilih</s:option>
                                <s:option value="MP">Meterpadu</s:option>
                            </s:select>
                        </c:if>
                    </p>
                    <%--
                    <br/>
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="testing33" value="Isi Semula" class="btn" onclick="resetSendiri();"/>
                        <s:button name="simpanNS" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                    --%>
                </c:if>
            </c:if>

            <c:if test="${actionBean.kodNegeri ne '05'}">

                <p>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTG'}">
                        <label><font color="red">*</font>Jenis :</label>
                            <%--<s:text name="permohonanBahanBatuan.jenisBahanBatu" size="50"/>--%>
                            <s:select name="jenisBahanBatu.kod" value="${actionBean.permohonanBahanBatuan.jenisBahanBatu.kod}" id="test">
                                <s:option value="">Sila Pilih</s:option>
                            <s:option value="PS">Pasir</s:option>
                            <s:option value="BG">Batu, Batuan, Batu Granit</s:option>
                            <%--<s:option value="LN">Lain-lain</s:option>--%>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMMK'}">
                        <label><font color="red">*</font>Jenis :</label>
                            <%--<s:text name="permohonanBahanBatuan.jenisBahanBatu" size="50"/>--%>
                            <s:select name="jenisBahanBatu.kod" value="${actionBean.permohonanBahanBatuan.jenisBahanBatu.kod}" id="test">
                                <s:option value="">Sila Pilih</s:option>
                            <s:option value="PS">Pasir</s:option>
                            <s:option value="PU">Pasir Sungai</s:option>
                            <s:option value="PD">Pasir Daratan</s:option>
                            <s:option value="BG">Batu, Batuan, Batu Granit</s:option>
                            <s:option value="BK">Batu Kapur</s:option>
                            <s:option value="TL">Tanah Liat</s:option>
                            <%--<s:option value="LN">Lain-lain</s:option>--%>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTD'}">
                        <label><font color="red">*</font>Jenis :</label>
                            <s:text name="permohonanBahanBatuan.jenisBahanBatu" size="50"/>
                            <s:select name="jenisBahanBatu.kod" value="${actionBean.permohonanBahanBatuan.jenisBahanBatu.kod}" id="test">
                                <s:option value="">Sila Pilih</s:option>
                            <s:option value="BT">Batu Bata</s:option>
                            <s:option value="RP">Rumput</s:option>
                            <s:option value="LT">Tanah Laterit</s:option>
                            <s:option value="TN">Tanah</s:option>
                            <s:option value="TG">Tanah Gambut</s:option>
                            <s:option value="TM">Tanah Merah</s:option>
                            <s:option value="TL">Tanah Liat</s:option>
                            <s:option value="LN">Lain-lain</s:option>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB' or actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                        <label><font color="red">*</font>Jenis :</label>
                            <s:text name="permohonanBahanBatuan.jenisBahanBatu" size="50"/>
                            <s:select name="jenisBahanBatu.kod" value="${actionBean.permohonanBahanBatuan.jenisBahanBatu.kod}" id="test">
                                <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodItemPermitBatuan}" label="nama" value="kod" />
                        </s:select>
                    </c:if>
                    <s:hidden name="permohonan.cawangan.kod" id="kodnegeri"/>
                </p>
                <p>
                    <label>Tujuan Pengeluaran :</label>
                    <s:textarea name="permohonan.sebab" rows="5" cols="50" class="normal_text" id="sbb"/>
                </p>
                <p>
                    <label>Kawasan pengambilan :</label>
                    <s:text name="permohonanBahanBatuan.kawasanAmbilBatuan" id="ambil" size="20" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;(No. Lot (Jika ada))
                </p>
                <p>
                    <label>Kawasan pemindahan :</label>
                    <s:text name="permohonanBahanBatuan.kawasanPindahBatuan" id="pindah" size="20" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;(No.Lot (Jika ada))
                </p>
                <p>
                    <label>Jalan yang dilalui :</label>
                    <s:text name="permohonanBahanBatuan.jalanDilalui" id="jalan" size="50" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>

                <p>
                    <label>Tempoh pengeluaran :</label>
                    <s:text name="permohonanBahanBatuan.tarikhMula"  id="datepicker" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy"/>
                    &nbsp;<font color="#003194"><b>hingga</b></font>&nbsp;
                    <s:text name="permohonanBahanBatuan.tarikhTamat" id= "datepicker2" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy"/>
                </p>
                <p>
                    <label><font color="red">*</font>Jangka Masa :</label>
                        <s:text name="permohonanBahanBatuan.tempohKeluar" size="20" id="tempoh" maxlength="3" onkeyup="validateNumber(this,this.value);" value="${actionBean.permohonanBahanBatuan.tempohKeluar}"/>
                        <s:select name="unitTempohKeluarUOM" id="tempohUOM" value="${actionBean.permohonanBahanBatuan.unitTempohKeluar.kod}">
                            <s:option value="">Sila Pilih</s:option>
                        <s:option value="HR">Hari</s:option>
                        <s:option value="B">Bulan</s:option>
                    </s:select>
                </p>
                <p>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB' or actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                        <label><font color="red">*</font>Jumlah isipadu :</label>
                            <s:text name="permohonanBahanBatuan.jumlahIsipaduDipohon" id="isipadu" size="20" maxlength="6" onkeypress="return isNumberKey(event)"/>
                            <s:select name="jumlahIsipaduDipohonUom.kod" id="isipaduUOM" value="${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.kod}">
                                <s:option value="">Sila Pilih</s:option>
                            <s:option value="MP">Meterpadu</s:option>
                            <s:option value="KB">Ketul Batu</s:option>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTG'}">
                        <label><font color="red">*</font>Jumlah isipadu :</label>
                            <s:text name="permohonanBahanBatuan.jumlahIsipaduDipohon" id="isipadu" size="20" maxlength="6" onkeypress="return isNumberKey(event)"/>
                            <s:select name="jumlahIsipaduDipohonUom.kod" id="isipaduUOM" value="${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.kod}">
                                <s:option value="">Sila Pilih</s:option>
                            <s:option value="MP">Meterpadu</s:option>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTD'}">
                        <label><font color="red">*</font>Jumlah isipadu :</label>
                            <s:text name="permohonanBahanBatuan.jumlahIsipaduDipohon" id="isipadu" size="20" maxlength="6" onkeypress="return isNumberKey(event)"/>
                            <s:select name="jumlahIsipaduDipohonUom.kod" id="isipaduUOM" value="${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.kod}">
                                <s:option value="">Sila Pilih</s:option>
                            <s:option value="MP">Meterpadu</s:option>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMMK'}">
                        <label><font color="red">*</font>Jumlah isipadu :</label>
                            <s:text name="permohonanBahanBatuan.jumlahIsipaduDipohon" id="isipadu" size="20" maxlength="6" onkeypress="return isNumberKey(event)"/>
                            <s:select name="jumlahIsipaduDipohonUom.kod" id="isipaduUOM" value="${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.kod}">
                                <s:option value="">Sila Pilih</s:option>
                            <s:option value="MP">Meterpadu</s:option>
                        </s:select>
                    </c:if>
                </p>
                <br/>

                <p>
                    <label>&nbsp;</label>
                    <s:button name="testing33" value="Isi Semula" class="btn" onclick="resetSendiri();"/>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMMK' or actionBean.permohonan.kodUrusan.kod eq 'PBPTD' or actionBean.permohonan.kodUrusan.kod eq 'PBPTG'}">
                        <s:button name="simpanNSBahanBatuan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PBMMK' and actionBean.permohonan.kodUrusan.kod ne 'PBPTD' and actionBean.permohonan.kodUrusan.kod ne 'PBPTG'}">
                        <s:button name="simpanNS" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                    </c:if>
                </p>

            </c:if>

            <%--            <p>
                            <label>Bangunan Sementara :</label>
                             <s:select name="bangunanSementara" id="bangunanSementara" onchange="javaScript:changeBangunanSementara(this.options[selectedIndex].value)">&nbsp; Ya/Tidak
                                   <s:option value="">Sila Pilih</s:option>
                                    <s:option value="y">Ya</s:option>
                                    <s:option value="t">Tidak</s:option>
                            </s:select>
                        </p>
                        <p>
                                <label>Lebar Bangunan Sementara :</label>
                                <s:text name="permohonanBahanBatuan.lebarBangunanSementara" onkeypress="return isNumberKey(event)" id="lbr"/>
                                <s:select name="lebarBangunanSementaraUom.kod" value="${actionBean.permohonanBahanBatuan.lebarBangunanSementaraUom.kod}">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option value="JM">Meter</s:option>
                                    <s:option value="JK">Kaki</s:option>
                                </s:select>
                        </p>
                        <p>
                                <label>Panjang Bangunan Sementara :</label>
                                <s:text name="permohonanBahanBatuan.panjangBangunanSementara" onkeypress="return isNumberKey(event)" id="pnjg"/>
                                <s:select name="panjangBangunanSementaraUom.kod" value="${actionBean.permohonanBahanBatuan.panjangBangunanSementaraUom.kod}">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option value="JM">Meter</s:option>
                                    <s:option value="JK">Kaki</s:option>
                                </s:select>
                        </p>--%>



            <%--<p>--Melaka--
                <label>Bina Bangunan Sementara:</label>
                <s:select name="permohonanBahanBatuan.bangunanSementara">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="">Akan membina</s:option>
                    <s:option value="">Tidak akan menbina</s:option>
                </s:select>&nbsp;(Jika ada sila nyatakan ukuran bangunan tersebut)
            </p>
            <p>
                <label>Lebar:</label>
                <s:text name="permohonanBahanBatuan.lebarBangunanSementara" id="lebar" size="20" maxlength="20" onkeyup="validateNumber(this,this.value);"/>
                <s:select name="lebarBangunanSementara">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="KK">Kaki</s:option>
                    <s:option value="MT">Meter</s:option>
                </s:select>
            </p>
            <p>
                <label>Panjang:</label>
                <s:text name="permohonanBahanBatuan.panjangBangunanSementara" id="panjang" size="20" maxlength="20" onkeyup="validateNumber(this,this.value);"/>
                <s:select name="panjangBangunanSementara">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="KK">Kaki</s:option>
                    <s:option value="MT">Meter</s:option>
                </s:select>
            </p>--%>


            <%--<p>
            <label> Jenis Mesin :</label>

            <s:text name="permohonanJentera.jenisJentera"  id="jenisJentera" onkeyup="this.value=this.value.toUpperCase();"  maxlength="100"/>
            </p>
             <p>
                <label> Jenis Mesin :</label>
                 <s:select name="permohonanJentera.jenisJentera" id="jenisJentera" value="${actionBean.permohonanJentera.jenisJentera}">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="A">A</s:option><s:option value="B">B</s:option><s:option value="C">C</s:option><s:option value="D">D</s:option><s:option value="E">E</s:option>
                    <s:option value="F">F</s:option><s:option value="G">G</s:option><s:option value="H">H</s:option><s:option value="I">I</s:option><s:option value="J">J</s:option>
                    <s:option value="K">K</s:option><s:option value="L">L</s:option><s:option value="M">M</s:option><s:option value="N">N</s:option><s:option value="O">O</s:option>
                    <s:option value="P">P</s:option><s:option value="Q">Q</s:option><s:option value="R">R</s:option><s:option value="S">S</s:option><s:option value="T">T</s:option>
                    <s:option value="U">U</s:option><s:option value="V">V</s:option><s:option value="W">W</s:option><s:option value="X">X</s:option><s:option value="Y">Y</s:option>
                    <s:option value="Z">Z</s:option><s:option value="0">0</s:option><s:option value="1">1</s:option><s:option value="2">2</s:option><s:option value="3">3</s:option>
                    <s:option value="4">4</s:option><s:option value="5">5</s:option><s:option value="6">6</s:option><s:option value="7">7</s:option><s:option value="8">8</s:option>
                    <s:option value="9">9</s:option><s:option value="~">~</s:option><s:option value="`">`</s:option><s:option value="!">!</s:option><s:option value="@">@</s:option>
                    <s:option value="#">#</s:option><s:option value="$">$</s:option><s:option value="%">%</s:option><s:option value="^">^</s:option><s:option value="&">&</s:option>
                    <s:option value="*">*</s:option><s:option value="(">(</s:option><s:option value=")">)</s:option><s:option value="-">-</s:option><s:option value="_">_</s:option>
                    <s:option value="+">+</s:option><s:option value="=">=</s:option><s:option value="{">{</s:option><s:option value="}">}</s:option><s:option value="[">[</s:option>
                    <s:option value="]">]</s:option><s:option value="|">|</s:option><s:option value="\\">\</s:option><s:option value=":">:</s:option><s:option value=";">;</s:option>
                    <s:option value="\"">"</s:option><s:option value="'">'</s:option><s:option value="<"><</s:option><s:option value=">">></s:option><s:option value=",">,</s:option>
                    <s:option value=".">.</s:option><s:option value="?">?</s:option><s:option value="/">/</s:option>
                 </s:select>
            </p>
            <p>
                <label> Muatan :</label>
                
                    <s:text name="permohonanJentera.kapasiti"  id="kapasiti" class="numbersOnly"  maxlength="20" />
            </p>--%>


        </fieldset>
    </div>
</s:form>


