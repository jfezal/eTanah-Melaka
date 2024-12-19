<%-- 
    Document   : popup_edit_waris
    Created on : Aug 7, 2011, 4:00:21 PM
    Author     : siti.zainal
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
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
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        jenisPengenalanWaris();
    });

    function validateForm(){
        self.opener.refreshPageWaris();
        self.close();
    }
   <%-- function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }--%>
    function validateNumber(elmnt,content) {

        //if it is fullstop (.) , then remove it..
        for( var i = 0; i < content.length; i++ )
        {
            var str = "";
            str = content.substr( i, 1 );
            if(str == "."){
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }

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
    function test(f) {
        $(f).clearForm();
    }

     function jenisPengenalanWaris(){
        if($('#pengenalanWaris').val() == 'B'){
            document.getElementById("noPengenalanBaruWaris").style.visibility = 'visible';
            document.getElementById("noPengenalanBaruWaris").style.display = '';
            $('#noPengenalanLainWaris').hide();
            $('#penyerahNoPengenalanLainWaris').attr("disabled", true);
            $('#penyerahNoPengenalanBaruWaris').attr("disabled", false);
        }else{
            $('#noPengenalanLainWaris').show();
            document.getElementById("noPengenalanBaruWaris").style.visibility = 'hidden';
            document.getElementById("noPengenalanBaruWaris").style.display = 'none';
            $('#penyerahNoPengenalanBaruWaris').attr("disabled", true);
            $('#penyerahNoPengenalanLainWaris').attr("disabled", false);
        }
    }

    function findDOBW(){
        var noPengenalanWaris = $('#penyerahNoPengenalanBaruWaris').val();
        if(noPengenalanWaris.length =="12"){

            //tahun
            var year = noPengenalanWaris.substr(0, 2);
            var tahun = "19"+year;
            var selectKodUrusan = document.getElementById('tahun');
               selectKodUrusan.selectedIndex = 0;
              for (var i = 0; i < selectKodUrusan.options.length; ++i){
                if (selectKodUrusan.options[i].value == tahun){
                    selectKodUrusan.selectedIndex = i;
                    break;
                }
              }

            //month
            var month = noPengenalanWaris.substr(2, 1);
            var bulan = "";
            if( month == "0"){
                bulan = noPengenalanWaris.substr(3, 1);
            }
            else{
                bulan= noPengenalanWaris.substr(2, 2);
                if(bulan >12){
                    alert("Sila masukkan bulan yang betul.");
                }
            }
            var selectKodUrusan = document.getElementById('bulan');
               selectKodUrusan.selectedIndex = 0;
                 for (var i = 0; i < selectKodUrusan.options.length; ++i){
                                   if (selectKodUrusan.options[i].value == bulan){
                                           selectKodUrusan.selectedIndex = i;
                                           break;
                                   }
                           }
            //day
            var hari = noPengenalanWaris.substr(4,1);
            var day ="";
            if(hari == "0"){

                day=noPengenalanWaris.substr(5,1);
            }
            else{
                day=noPengenalanWaris.substr(4,2);
                if(day>31){
                    alert("Sila masukkan hari yang betul.");
                }

            }

            var selectKodUrusan = document.getElementById('hari');
                           selectKodUrusan.selectedIndex = 0;
                           for (var i = 0; i < selectKodUrusan.options.length; ++i){
                                   if (selectKodUrusan.options[i].value == day){
                                           selectKodUrusan.selectedIndex = i;
                                           break;
                                   }
                           }
        }else{
            alert("Sila masukkan 12 digit nombor pengenalan");
        }
               }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>

<s:form  beanclass="etanah.view.penguatkuasaan.MaklumatWarisActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Waris
            </legend>

            <div class="content">

                <p>

                    <label>Nama Waris:</label>
                    <s:text name="nama" size="30" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                </p>

                <p>
                    <label>Jenis Pengenalan Waris:</label>
                    <s:select name="jenispengenalanedit"  style="width:139px;" id="pengenalanWaris" onchange="jenisPengenalanWaris(this.value);">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" sort="nama" />
                    </s:select>
                    &nbsp;
                </p>

               <%-- <p>
                    <label>No.Pengenalan :</label>
                    <s:text name="noPengenalan" maxlength="12" />
                    <font color="red" size="1">cth : 850510075342 </font>&nbsp;
                </p>--%>
               <p id="noPengenalanLainWaris">
                <label>No.Pengenalan :</label>
                <s:text name="noPengenalan" id="penyerahNoPengenalanLainWaris" maxlength="12" onkeyup="this.value=this.value.toUpperCase();" />
                <%--<font color="red" size="1">cth : 850510075342 </font>--%>
                &nbsp;
            </p>

            <p id="noPengenalanBaruWaris" style="visibility: hidden; display: none">
                <label>No.Pengenalan :</label>
                <s:text name="noPengenalan" id="penyerahNoPengenalanBaruWaris" maxlength="12" onkeyup="validateNumber(this,this.value);" onblur="javascript:findDOBW(this.value)"/>
                <font color="red" size="1">cth : 850510071213 </font>
                &nbsp;
            </p>

                <p>
                    <label>Alamat Waris:</label>
                    <s:text name="alamat1" size="30" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                </p>

                <p>
                    <label>&nbsp;</label>
                    <s:text name="alamat2" size="30" onchange="this.value=this.value.toUpperCase();"/>&nbsp;</p>
                <p>
                    <label> &nbsp; </label>
                    <s:text name="alamat3" size="30" onchange="this.value=this.value.toUpperCase();"/>&nbsp;</p>
                <p>
                    <label> &nbsp; </label>
                    <s:text name="alamat4" size="30" onchange="this.value=this.value.toUpperCase();"/>&nbsp; </p>

                <p>
                    <label>Poskod :</label>
                    <s:text name="poskod"  size="19" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);"/>&nbsp;
                </p>
                <p>
                    <label>Negeri :</label>
                    <s:select name="negeriedit">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                    </s:select>&nbsp;
                </p>
                <p>
                    <label>Bangsa :</label>
                    <s:select name="bangsaedit">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.senaraiBangsa}" label="nama" value="kod" />
                    </s:select>&nbsp;
                </p>

                <p>
                    <label>Jantina :</label>
                    <s:select name="jantinaedit">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodJantina}" label="nama" value="kod" />
                    </s:select>&nbsp;
                </p>

                <p>
                    <label>Jenis Warganegara :</label>
                    <s:select name="warganegaraedit"  style="width:139px;">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiWarganegara}" label="nama" value="kod" sort="nama" />
                    </s:select>
                    &nbsp;
                </p>

                <p>
                    <label>No Telefon Rumah:</label>
                    <s:text name="noTelWaris" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);"/>&nbsp;
                </p>

                <p>
                    <label>No Telefon Bimbit:</label>
                    <s:text name="noTelBimbitWaris"  maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);"/>&nbsp;
                </p>

                <p>

                    <label>Hubungan:</label>
                    <s:text name="hubungan"  size="30" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                </p>




                <p><label>&nbsp;</label>
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                    <s:submit name="editWaris" id="simpan" class="btn" value="Simpan" onclick="return validateForm();"/>
                    <s:hidden name="idWaris" value="${actionBean.warisOrangKenaSyak.idWarisOks}"/>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>

                <br>
            </div>
        </fieldset>
    </div>
</s:form>
