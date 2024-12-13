<%-- 
    Document   : edit_pemohonIbubapa
    Created on : Aug 20, 2010, 1:17:02 PM
    Author     : sitifariza.hanim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>

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
<script type="text/javascript">
    $(document).ready( function(){
        <c:if test="${flag}">
            self.close();
            opener.refreshPageMaklumatPemohon();

        </c:if>
        $('.alphanumeric').alphanumeric();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $('#jenisPengenalan').change(function(){
            dodacheck($("#kp").val());
        });
        var v = '${actionBean.pemohonHubungan.jenisPengenalan.kod}';

        $('form').submit(function(){
            var val = $('#kp').val();
            var jenis = '${jenis}';
            return doCheckUmur(val, 'kp',jenis);
        });

        if(v == "B"){alert
            var icNo = '${actionBean.pemohonHubungan.noPengenalan}';<%--alert(icNo);alert(icNo.substring(4,6)+'/'+icNo.substring(2,4)+'/'+icNo.substring(0,2));--%>
            var age = calculateAge(icNo.substring(4,6), icNo.substring(2,4), 19 + icNo.substring(0,2));<%--alert(age);--%>
            $('#umur').val(age);
            $('#datepicker').val(icNo.substring(4,6)+"/"+icNo.substring(2,4)+"/"+19 + icNo.substring(0,2));
        }

        $('#kod_warganegara').val('MAL');

        });

         function calAgeByDOB(value){
            var age = calculateAge(value.substring(0,2), value.substring(3,5), 20 + value.substring(8,10));
            $('#umur').val(age);
        }

    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
        },'html');
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
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



     function saveMaklumatIbubapa(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        alert("saveMaklumatIbubapa:"+url);
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            alert("self");
            self.close();
        },'html');
    }

</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<div class="subtitle" id="caw">
<s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohonActionBean">
    <s:errors/>
    <s:messages/>
        <fieldset class="aras1">
            <legend>
                Maklumat IbuBapa Pemohon
            </legend>
            <s:hidden name="ibubapa" value="edit-ibubapa"></s:hidden>
            <p>
                <label><font color="red">*</font>Nama Ibubapa  :</label>
                <s:text name="pemohonHubungan.nama" value=" ${actionBean.pemohonHubungan.nama}" onkeyup="this.value=this.value.toUpperCase();"></s:text>
                 <s:hidden name="pemohonHubungan.idHubungan" />
            </p>
            <p>
                <label><font color="red">*</font>Jenis Pengenalan :</label>
                <s:hidden name="pihak.jenisPengenalan.nama" />
                <%--<s:text name="pemohonHubungan.jenisPengenalan.kod" />--%>
                ${actionBean.pihak.jenisPengenalan.nama}&nbsp;
            </p>
            <p>
                <label>Nombor Pengenalan :</label>
                <s:hidden name="pemohonHubungan.noPengenalan" />
                ${actionBean.pemohonHubungan.noPengenalan}&nbsp;
            </p>
             <%-- <p>
                <label><font color="red">*</font>Warna Nombor Kad Pengenalan :</label>
                <s:select name="pihak.warnaKP" id="warnaKP" style="width:100px">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:option value="B">Biru</s:option>
                                        <s:option value="C">Coklat</s:option>
                                        <s:option value="H">Hijau</s:option>
                                        <s:option value="M">Merah</s:option>
                                        <s:option value="L">Lain-lain</s:option>
                                </s:select>
              </p>--%>
         <%--<p>
            <label>Bangsa  :</label>
            <s:hidden name="pemohonHubungan.bangsa.kod" />
            ${actionBean.pemohonHubungan.bangsa.nama}&nbsp;
        </p>--%>
               <p>
                            <label><font color="red">*</font>Bangsa  :</label>
                            <s:select name="pemohonHubungan.bangsa.kod" value="${actionBean.pemohonHubungan.bangsa.kod}" id="bangsa" style="width:275px">
                                <s:option value=""> Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodBangsa}" label="nama" value="kod" />
                            </s:select>
                </p>
               <p>
                            <label><font color="red">*</font>Kewarganegaraan :</label>
                            <s:select name="pihak.wargaNegara.kod" style="width:150px" value="${actionBean.pihak.wargaNegara.kod}" id="kod_warganegara">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                            </s:select>
                        </p>
           <p>
                                <label><font color="red">*</font>Tarikh Lahir :</label>
                                <s:text name="pemohonHubungan.tarikhLahir" size="40" id="datepicker" value="${actionBean.pemohonHubungan.tarikhLahir}" class="datepicker" onchange="calAgeByDOB(this.value);"/>
                            </p>


<%--         <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
--%>
                <p>
                    <label><font color="red">*</font>Umur :</label>
                    <s:text name="pemohonHubungan.umur" size="10" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                <label><font color="red">*</font>Tempat Lahir : </label>
                <s:text name="pemohonHubungan.tempatLahir" size="40" value="${actionBean.pemohonHubungan.tempatLahir}" onkeyup="this.value=this.value.toUpperCase();"/>

            </p>

          <p>
                        <label><font color="red">*</font>Kaitan :</label>
                        <s:select name="pemohonHubungan.kaitan" id="kaitan" >
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="BAPA">BAPA</s:option>
                            <s:option value="IBU">IBU</s:option>
                        </s:select>
                    </p>

                <p>
                    <label>Pekerjaan :</label>
                    <s:text name="pemohonHubungan.pekerjaan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label><font color="red">*</font>Pendapatan Bulanan (RM) :</label>
                    <s:text name="pemohonHubungan.gaji" size="40" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                <label>Nama/Majikan </label>
                <s:text name="pemohonHubungan.institusi" value="${actionBean.pemohonHubungan.institusi}"size="40" onkeyup="this.value=this.value.toUpperCase();"/>
             </p>
              <p id="majikanAlamat1">
                            <label for="alamat">Alamat Majikan: </label>
                            <s:text name="pemohonHubungan.institusiAlamat1" id="majikanAlamat1" size="40" onkeyup="this.value=this.value.toUpperCase();" />
                    </p>
                    <p id="majikanAlamat2">
                        <label> &nbsp;</label>
                        <s:text name="pemohonHubungan.institusiAlamat2" id="majikanAlamat2" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p id="majikanAlamat3">
                        <label> &nbsp;</label>
                        <s:text name="pemohonHubungan.institusiAlamat3" id="majikanAlamat3" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p id="majikanAlamat4">
                        <label> &nbsp;</label>
                        <s:text name="pemohonHubungan.institusiAlamat4" id="majikanAlamat4" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>

            <p>
                <label>Poskod :</label>
                <s:text name="pemohonHubungan.institusiPoskod" value="${actionBean.pemohonHubungan.institusiPoskod}"size="40" id="poskodCaw" maxlength="5" onkeyup="validateNumber(this,this.value);"/>              </p>
            <p>
                                    <label for="Negeri">Negeri :</label>
                                    <s:select name="pemohonHubungan.institusiNegeri.kod" id="negeri">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                                    </s:select>
                                </p>

             <legend>(Jika telah meninggal dunia)</legend>
                    <p>
                        <label> Tarikh meninggal dunia</label>
                        <s:text name="pemohonHubungan.tarikhMati" size="10" value="${actionBean.pemohonHubungan.tarikhMati}" class="datepicker" id="tarikhMati"/>
                    </p>
                    <p>
                        <label for="alamat">Alamat Terakhir :</label>
                        <s:text name="pemohonHubungan.alamat1" size="40" id="alamat1" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        <s:text name="pemohonHubungan.alamat2" size="40" id="alamat2" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        <s:text name="pemohonHubungan.alamat3" size="40" id="alamat3" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="pemohonHubungan.alamat4" size="40" id="alamat4" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>



<%--
           <p>
                <label><font color="red">*</font>Nama/Majikan </label>
                <s:text name="pemohon.pendapatan" value="${actionBean.pemohon.pendapatan}"size="40" onkeyup="validateNumber(this,this.value);"/>
             </p>
              <p>
                <label><font color="red">*</font>Alamat Majikan:</label>
                <s:text name="pemohon.pendapatan" size="40" onkeyup="validateNumber(this,this.value);"/>
             </p>--%>

             <p>
                    <label>Poskod :</label>
                    <s:text name="pemohonHubungan.poskod" value="{actionBean.pemohonHubungan.poskod}"id="poskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                </p>

                <p>
                <label>Negeri :</label>
                <s:select name="pemohonHubungan.negeri.kod" >
                    <s:option value="">Pilih </s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>


            <p>
               <label>&nbsp;</label>
                <%--<s:button name="simpanIbubapa" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>--%>
                <%--<s:button name="simpanMaklumatIbubapa" id="simpan" value="Simpan" class="btn" onclick="saveMaklumatIbubapa(this.name, this.form);"/>--%>
                <s:submit name="simpanMaklumatIbubapa" id="simpan" value="Simpan" class="btn"/>
                <label>&nbsp;</label>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>

        </fieldset>
   </div>
 </s:form>
