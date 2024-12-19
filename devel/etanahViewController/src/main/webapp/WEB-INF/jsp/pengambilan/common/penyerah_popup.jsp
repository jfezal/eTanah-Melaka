<%-- 
    Document   : penyerah_popup
    Created on : 14-Apr-2015, 12:07:36
    Author     : Erra Zyra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ageCalculator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $('.alphanumeric').alphanumeric();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $('#jenisPengenalan').change(function(){
            dodacheck($("#kp").val());
        });
        var v = '${actionBean.pihak.jenisPengenalan.kod}';

        if(v == "B" || v == "L" || v == "P" || v == "T" || v == "I" || v == "F"){
            $('#kodBangsa').hide();
            $('#kodBangsaOrang').show();
            $('#kodBangsaSyarikat').hide();
            $('#tableCaw').hide();
            $('#btnCaw').hide();
            $('#btnAhli').hide();
        }

        else{
            $('#kodBangsa').hide();
            $('#kodBangsaOrang').hide();
            $('#kodBangsaSyarikat').show();
            $('#tableCaw').show();
            $('#btnCaw').show();
            $('#btnAhli').show();
        }

        if(v == ''){
            $('#kodBangsa').show();
            $('#kodBangsaOrang').hide();
            $('#kodBangsaSyarikat').hide();
            $('#tableCaw').hide();
            $('#btnCaw').hide();
            $('#btnAhli').hide();
        }

        if(v == "S"){
            $('#suku').hide();
            $('#perut').hide();
            $('#luak').hide();
        }

        else{
            $('#suku').show();
            $('#perut').show();
            $('#luak').show();
        }

        $('form').submit(function(){
            var val = $('#kp').val();
            var jenis = '${jenis}';
            return doCheckUmur(val, 'kp',jenis);
        });

        if(v == "B"){
            var icNo = '${actionBean.pihak.noPengenalan}';

            var year = icNo.substring(0,2);

            if(year > 25 && year < 99){//fixme :
                year = "19" + year;
            }else {
                year = "20" + year;
            }

            var age = calculateAge(icNo.substring(4,6), icNo.substring(2,4), year);
            $('#umur').val(age);
            $('#datepicker').val(icNo.substring(4,6)+"/"+icNo.substring(2,4)+"/"+year);
        }

        $('#kod_warganegara').val('MAL');
        $('#kod_warga_pengarah').val('MAL');

        var jenisPihak = $('#jenis_pihak').val();

        if(jenisPihak == "Pemegang Amanah" || jenisPihak == "PA"){
            $('#pgAmanah').show();
        }else{
            $('#pgAmanah').hide();
        }

        var hubungan = $('#hubungan').val();
        hubungan = hubungan.toUpperCase();
        if(hubungan == "LAIN-LAIN"){
            $('#hubunganLain').show();
        }else{
            $('#hubunganLain').hide();
        }

        var tempatLahir = $('#tempatLahir').val();
        tempatLahir = tempatLahir.toUpperCase();
        if(tempatLahir == "LAIN-LAIN"){
            $('#tempatLahirLain').show();
        }else{
            $('#tempatLahirLain').hide();
        }

        var amanah2 = $('#namaAmanah2').val();
        var amanah3 = $('#namaAmanah3').val();
        var amanah4 = $('#namaAmanah4').val();
        var amanah5 = $('#namaAmanah5').val();
        var amanah6 = $('#namaAmanah6').val();
        var amanah7 = $('#namaAmanah7').val();
        var amanah8 = $('#namaAmanah8').val();
        var amanah9 = $('#namaAmanah9').val();
        var amanah10 = $('#namaAmanah10').val();

        if(amanah2 == ''){
            $('#divAmanah2').hide();
        }
        if(amanah3 == ''){
            $('#divAmanah3').hide();
        }
        if(amanah4 == ''){
            $('#divAmanah4').hide();
        }
        if(amanah5 == ''){
            $('#divAmanah5').hide();
        }
        if(amanah6 == ''){
            $('#divAmanah6').hide();
        }
        if(amanah7 == ''){
            $('#divAmanah7').hide();
        }
        if(amanah8 == ''){
            $('#divAmanah8').hide();
        }
        if(amanah9 == ''){
            $('#divAmanah9').hide();
        }
        if(amanah10 == ''){
            $('#divAmanah10').hide();
        }

    });

    function saveUlasan(event, f){
        //alert("data");

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
        },'html');
        return true;
    }

    function refreshPagePemohon(){
        var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function tutup() {
        alert("1");
        self.close();
        $.unblockUI();
    }

</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<div class="subtitle" id="caw">
    <s:form beanclass="etanah.view.stripes.pengambilan.pActionBean" >

        <s:hidden name="hakmilik" value="${hakmilik}"/>
        <s:errors/>
        <s:messages/>
        <fieldset class="aras1">
            <div>
                <legend>Maklumat Penyerah</legend>

                <c:if test="${!empty SSHMA}">
                    <p>
                        <label><em>*</em>Nama Pemohon / Jabatan / Unit</label><s:text name="penyerahEmail" id="penyerahEmail" size="42" onblur="doUpperCase(this.id)"/>
                    </p>
                </c:if>
                <p>
                    <label>Nama</label><s:text name="penyerahNama" id="penyerahNama" size="42" onblur="doUpperCase(this.id)"/>
                </p>
                <p>
                    <label><em>*</em>No. Pengenalan :</label>
                    <c:choose>
                        <c:when test="${actionBean.penyerahKod eq '09' or actionBean.penyerahKod eq '10' or actionBean.penyerahKod eq '02' or actionBean.penyerahKod eq '04' or actionBean.penyerahKod eq '03' or actionBean.penyerahKod eq '01' or actionBean.penyerahKod eq '07' or actionBean.penyerahKod eq '00' or actionBean.penyerahKod eq '06' or actionBean.penyerahKod eq null}">
                            <s:hidden name="kod1" value="Z">Badan Kerajaan  </s:hidden>
                            <s:select name="kod" value="${actionBean.permohonan.penyerahJenisPengenalan.kod}" disabled="true">
                                <s:option value="">Pilih Jenis...</s:option>
                                <s:option value="B">No K/P Baru  </s:option>
                                <s:option value="S">No Syarikat  </s:option>
                                <s:option value="L">No K/P Lama  </s:option>
                                <s:option value="P">No Pasport  </s:option>
                                <s:option value="T">No Tentera  </s:option>
                                <s:option value="I">No Polis  </s:option>
                                <s:option value="0">Tidak Berkenaan  </s:option>
                                <s:option value="N">No Bank  </s:option>
                                <s:option value="F">No Paksa  </s:option>
                                <s:option value="U">No Pertubuhan  </s:option>
                                <s:option value="D">No Pendaftaran  </s:option>
                                <s:option value="Z">Badan Kerajaan  </s:option>
                                <s:option value="X">Tiada  </s:option>
                            </s:select>
                        </c:when>
                        <c:otherwise>
                            <s:select name="kod" value="${actionBean.permohonan.penyerahJenisPengenalan.kod}">
                                <s:option value="">Pilih Jenis...</s:option>
                                <s:option value="B">No K/P Baru  </s:option>
                                <s:option value="S">No Syarikat  </s:option>
                                <s:option value="L">No K/P Lama  </s:option>
                                <s:option value="P">No Pasport  </s:option>
                                <s:option value="T">No Tentera  </s:option>
                                <s:option value="I">No Polis  </s:option>
                                <s:option value="0">Tidak Berkenaan  </s:option>
                                <s:option value="N">No Bank  </s:option>
                                <s:option value="F">No Paksa  </s:option>
                                <s:option value="U">No Pertubuhan  </s:option>
                                <s:option value="D">No Pendaftaran  </s:option>
                                <s:option value="Z">Badan Kerajaan  </s:option>
                                <s:option value="X">Tiada  </s:option>
                            </s:select>
                        </c:otherwise>
                    </c:choose>

                    <s:hidden name="penyerahNoPengenalan" id="penyerahNoPengenalan" value="${actionBean.penyerahNoPengenalan}"/>
                    <s:text name="pNoPengenalan" id="penyerahNoPengenalan" size="10" value="${actionBean.permohonan.penyerahNoPengenalan}"/>
                    <em>[cth: 780901057893]</em>
                    <%--<input type=button name="searchPenyerah" value="Cari" class="btn" id="carianPihak"
                           onclick="javascript:populatePenyerah(this);" />--%>
                </p>
                <p>
                    <label><em>*</em>Alamat</label>
                    <s:text name="penyerahAlamat1" id="penyerahAlamat1" size="30" onblur="doUpperCase(this.id)"/>
                </p>

                <p>
                    <label>&nbsp;</label>
                    <s:text name="penyerahAlamat2" id="penyerahAlamat2" size="30" onblur="doUpperCase(this.id)"/>
                </p>

                <p>
                    <label>&nbsp;</label>
                    <s:text name="penyerahAlamat3" id="penyerahAlamat3" size="30" onblur="doUpperCase(this.id)"/>
                </p>

                <p>
                    <label>Bandar</label>
                    <s:text name="penyerahAlamat4" id="penyerahAlamat4" size="30" onblur="doUpperCase(this.id)"/>
                </p>

                <p>
                    <label>Poskod</label>
                    <s:text name="penyerahPoskod" maxlength="5" id="penyerahPoskod" size="17" onkeyup="validateNumber(this,this.value);" />
                    <em>5 Digit [cth : 12000]</em>
                </p>

                <p>
                    <label><em>*</em>Negeri</label>
                    <%--penyerahNegeri.kod--%>
                    <s:select name="penyerahNegeri1" id="penyerahNegeri1" >
                        <s:option value="0">Pilih 1 ...</s:option>
                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" />
                    </s:select>
                    <%--<s:submit  name="updatePenyerah" value="Kemaskini Penyerah" class="longbtn"/>--%>
                </p>
                <p>
                    <label>No.Telefon</label>
                    <s:text name="penyerahNoTelefon" id="penyerahNoTelefon" size="15"/>
                </p>
                <p>
                    <label>Email</label>
                    <s:text name="penyerahEmail" id="penyerahEmail" size="50"/>
                </p>
                <br/>
                <p align="center">
                    <s:button name="simpanPenyerah" value="Simpan" class="btn" onclick="saveUlasan(this.name, this.form)"/>
                    <%--<s:button name="ttp" value="Tutup" class="btn" onclick="tutup();"/>--%>
                </p>
            </div>

        </fieldset>

    </s:form>
</div>