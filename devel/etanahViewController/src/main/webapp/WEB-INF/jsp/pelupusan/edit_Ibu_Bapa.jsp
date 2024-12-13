<%-- 
    Document   : edit_Ibu_Bapa
    Created on : Nov 15, 2010, 2:34:05 PM
    Author     : Rohan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
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
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>

<script type="text/javascript">

    $(document).ready( function(){
        maximizeWindow();
    <c:if test="${!flag}">
            opener.refreshMaklumatPemohon();
            self.close();
    </c:if>

            $('.alphanumeric').alphanumeric();
            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
            $('#jenisPengenalan').change(function(){
                dodacheck($("#kp").val());
            });
            var v = '${actionBean.pihak.jenisPengenalan.kod}';

            $('form').submit(function(){
                var val = $('#kp').val();
                var jenis = '${jenis}';
                return doCheckUmur(val, 'kp',jenis);
            });

            if(v == "B"){
                var icNo = '${actionBean.pemohonHbgn.noPengenalan}';
                if(icNo != ""){
                    $('#datepicker').val(icNo.substring(4,6)+"/"+icNo.substring(2,4)+"/"+19 + icNo.substring(0,2));
                    var age = calculateAge(icNo.substring(4,6), icNo.substring(2,4), 19 + icNo.substring(0,2));
                    $('#umur').val(age);
                }

            }
            $('#kod_warganegara').val('MAL');
        });
        function calculateUmar(){
            if($("#tarikhLahir").val() != ""){
                var value = $("#tarikhLahir").val();
                var yearStartVal = value.substring(6,8);
                if(yearStartVal == "19"){
                    var age = calculateAge(value.substring(0,2), value.substring(3,5), 19 + value.substring(8,10));
                }else{
                    var age = calculateAge(value.substring(0,2), value.substring(3,5), 20 + value.substring(8,10));
                }
                $('#umr').val(age);
            }
        }
        function doCheckUmur(v,id,type){
            var va = $('#jenisPengenalan').val();
            if(va == 'B'){
                return doValidateAge(v, id, 'jenisPengenalan',type);
            }else {
                return true;
            }
        }


        function calAgeByDOB(value){
            var age = calculateAge(value.substring(0,2), value.substring(3,5), 20 + value.substring(8,10));
            $('#umur').val(age);
        }



        function validateNumber(elmnt,content) {
            //if it is character, then remove it..
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }

   function Hide1(){
      // alert($('input[value=check]').is(':checked'));
        if($('input[value=check]').is(':checked')){
            $('#alamat').hide();
        } else {
             $('#alamat').show();
        }
   }

        function removeNonNumeric( strString )
        {
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

<div class="subtitle" id="caw">
    <s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohon1ActionBean">
        <s:useActionBean beanclass="etanah.view.ListUtil" var="listUtil"/>
        <div align="center">
            <s:errors/>
            <s:messages/>
        </div>
        <fieldset class="aras1">
            <s:hidden name="pemohonHbgn.idHubungan"/>

            <legend>
               Kemasukan Maklumat Ibu/Bapa Pemohon
            </legend>

            <p>
                <label><font color="red">*</font>Nama :</label>
                <s:text name="pemohonHbgn.nama" id="nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Jenis Pengenalan :</label>
                <s:hidden name="pemohonHbgn.jenisPengenalan.nama" id="nama"  />
                <font color="black">${actionBean.pemohonHbgn.jenisPengenalan.nama}&nbsp;</font>
            </p>
            <p>
                <label>Nombor Pengenalan :</label>
                <s:hidden name="pemohonHbgn.noPengenalan" id="nama"/>
                <font color="black">${actionBean.pemohonHbgn.noPengenalan}&nbsp;</font>
            </p>

              <%-- <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B'}">
                <p>
                    <label><font color="red">*</font>Warna Nombor Kad Pengenalan :</label>
                        ${actionBean.pihak.warnaKP}
                    <s:select name="pihak.warnaKP" id="warnaKP" style="width:100px"> value="${actionBean.pihak.warnaKP}"
                        <s:option value="0">SILA PILIH</s:option>
                        <s:option value="B">Biru</s:option>
                        <s:option value="C">Coklat</s:option>
                        <s:option value="H">Hijau</s:option>
                        <s:option value="M">Merah</s:option>
                        <s:option value="L">Lain-lain</s:option>
                    </s:select>
                </p>
            </c:if>--%>

            <p>
                <label>Kaitan :</label>
                       ${actionBean.pemohonHbgn.kaitan}&nbsp;

                <%--<s:select name="pemohonHbgn.kaitan" id="kaitan" >--%>
                    <%--<s:option value="0">Sila Pilih</s:option>
                    <s:option value="SUAMI">SUAMI</s:option>
                    <s:option value="ISTERI">ISTERI</s:option>
                </s:select>--%>
            </p>

            <p>
                <label><font color="red">*</font>Tarikh Lahir :</label>
                <s:text name="pemohonHbgn.tarikhLahir" size="40" id="tarikhLahir" class="datepicker" onchange="calculateUmar();" formatPattern="dd/MM/yyyy" />
            </p>
            <p>
                <label><font color="red">*</font>Umur :</label>
                <s:text name="pemohonHbgn.umur" size="10" maxlength="3"  id="umr" value="${actionBean.pemohonHbgn.umur}" onkeyup="validateNumber(this,this.value);"/>
            </p>

            <p>
                <label><font color="red">*</font>Telah Meninggal :</label>
                <s:radio name="pemohonHbgn.telahMeninggal" id="meninggal" value="Y"/> Ya
                <s:radio name="pemohonHbgn.telahMeninggal" id="meninggal" value="T"/> Tidak
                <br/>

            </p>

            <p>
                <label>Tarikh Meninggal :</label>
                <s:text name="pemohonHbgn.tarikhMati" size="40"  class="datepicker"  formatPattern="dd/MM/yyyy"/>
            </p>

            <p>
                <label>Tempat Lahir :</label>
                <s:text name="pemohonHbgn.tempatLahir" size="40"  value="${actionBean.pemohonHbgn.tempatLahir}" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label><font color="red">*</font>Kewarganegaraan :</label>
                <s:select name="pemohonHbgn.warganegara.kod" style="width:200px">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiWarganegara}" label="nama" value="kod"/>
                </s:select>
            </p>

            <p>
                <label>Pekerjaan :</label>
                <s:text name="pemohonHbgn.pekerjaan" size="40"  value="${actionBean.pemohonHbgn.pekerjaan}"onkeyup="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label>Pendapatan Bulanan(RM) :</label>
                <s:text name="pemohonHbgn.gaji" size="40" value="${actionBean.pemohonHbgn.gaji}" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);"/>
            </p>

               <p>
                <label>&nbsp;</label>
                <input type="checkbox" id="add1" name="add1" value="check" onclick="Hide1();"/>
                <font color="red">Alamat surat-menyurat sama dengan alamat pemohon.</font>
            </p>
            <div id="alamat">

            <p>
                <label><font color="red">*</font>Alamat Surat-Menyurat :</label>
                <s:text name="pemohonHbgn.alamat1"  value="pemohonHbgn.alamat1"size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pemohonHbgn.alamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pemohonHbgn.alamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pemohonHbgn.alamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label><font color="red">*</font>Poskod :</label>
                <s:text name="pemohonHbgn.poskod" id="majikanPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
            </p>

            <p>
                <label><font color="red">*</font>Negeri :</label>
                <s:select name="pemohonHbgn.negeri.kod" id="negeri" value="${actionBean.pemohonHbgn.negeri.kod}">
                    <s:option value="">Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
            </p>
            </div>

            <p>
                <label>&nbsp;</label>
                <s:submit name="simpanEditIbuBapa" id="simpan" value="Simpan" class="btn" />
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>

        </fieldset>

    </s:form>
</div>
