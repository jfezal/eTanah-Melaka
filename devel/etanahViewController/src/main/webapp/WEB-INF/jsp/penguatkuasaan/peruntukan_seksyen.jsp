<%--
    Document   : kemasukan_aduan
    Created on : Apr 7, 2010, 11:36:04 AM
    Author     : aminah.abdmutalib
    modify by sitifarizahanim (11042011)
--%>
<%@ page contentType="text/html" import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="etanah.model.Pengguna"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript">
    function checkKodUrusan(){
         if($('#cara').val() == '')
        <%--if(document.form1.cara.value=="")--%>
        {
            alert("Sila Pilih Cara Aduan");
            return false;
        }
         if($('#sebab').val() == '')
        <%--if(document.form1.sebab.value=="")--%>
        {
            alert("Sila isi Ringkasan Aduan");
            return false;
        }
         if($('#penyerahNama').val() == '')
        <%--if(document.form1.penyerahNama.value=="")--%>
        {
            alert("Sila isi Nama Pengadu");
            return false;
        }
       <%-- if($('#kodUrusan').val() == '')
        {
            alert("Sila pilih Kod Urusan");
            return false;
        }--%>
        if($('#bpm').val() == '')
         <%--if(document.form1.bpm.value=="")--%>
        {
                alert("Sila Pilih Bandar/Pekan/Mukim");
                return false;
        }
        if($('#message').val() == '')
        <%--if(document.form1.message.value=="")--%>
        {
                alert("Sila isi Lokasi");
                return false;
        }
        else
            return true;
    }

    function refreshPage(){
        var q = $('#form1').serialize();
        var url = document.form1.action + '?refreshPage&';// + event;
        window.location = url+q;
    }
    function addPopupOKS(){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/senarai_aduan?oksPopup&idPermohonan=${actionBean.idPermohonan}", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }
    function editPopupOKS(id){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/senarai_aduan?oksPopup2&idOrangKenaSyak="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }
    function removeOKS(id){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            window.location = "${pageContext.request.contextPath}/penguatkuasaan/senarai_aduan?removeOKS&idOrangKenaSyak="+id+"&idPermohonan=${actionBean.idPermohonan}";
        }
    }
    function addPopupLokasi(){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/senarai_aduan?lokasiPopup&idPermohonan=${actionBean.idPermohonan}", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }
    function removeLokasi(id){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            window.location = "${pageContext.request.contextPath}/penguatkuasaan/senarai_aduan?removeLokasi&idAduanLokasi="+id+"&idPermohonan=${actionBean.idPermohonan}";
        }
    }
    function editPopupLokasi(id){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/senarai_aduan?lokasiPopup2&idAduanLokasi="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }
    <%--function validateNumber(elmnt,content) {
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
    function validateTelLength(value){
        var plength = value.length;
        if(plength < 7){
            alert('No. Telefon yang dimasukkan salah.');
            $('#telefon').val("");
            $('#telefon').focus();
        }
    }

    function validatePoskodLength(value){
        var plength = value.length;
        if(plength != 5){
            alert('Poskod yang dimasukkan salah.');
            $('#poskod').val("");
            $('#poskod').focus();
        }
    }

    $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });

    function ValidateEmail(){
        var emailID= $("#email").val();

        if ((emailID==null)||(emailID=="")){
            return true;
        }
        if ((emailID!=null)||(emailID!="")){
            if(emailcheck(emailID)==false){
                $("#email").val("");
                $("#email").focus();
                return false;
            }
        }
        return true;
    }

    function emailcheck(str) {

        var at="@";
        var dot=".";
        var lat=str.indexOf(at);
        var lstr=str.length;
        var ldot=str.indexOf(dot);
        if (str.indexOf(at)==-1){
            alert('"Alamat Email" salah');
            return false;
        }

        if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
            alert('"Alamat Email" salah');
            return false;
        }

        if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
            alert('"Alamat Email" salah');
            return false;
        }

        if (str.indexOf(at,(lat+1))!=-1){
            alert('"Alamat Email" salah');
            return false;
        }

        if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
            alert('"Alamat Email" salah');
            return false;
        }

        if (str.indexOf(dot,(lat+2))==-1){
            alert('"Alamat Email" salah');
            return false;
        }

        if (str.indexOf(" ")!=-1){
            alert('"Alamat Email" salah');
            return false;
        }

        return true;
    }
    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function jenisPengenalanPengadu(){
            if($('#pengenalanPengadu').val() == 'B'){
                document.getElementById("noPengenalanBaruPengadu").style.visibility = 'visible';
                document.getElementById("noPengenalanBaruPengadu").style.display = '';
                $('#noPengenalanLainPengadu').hide();
                 $('#penyerahNoPengenalanLainPengadu').attr("disabled", true);
                 $('#penyerahNoPengenalanBaruPengadu').attr("disabled", false);
            }else{
                $('#noPengenalanLainPengadu').show();
                document.getElementById("noPengenalanBaruPengadu").style.visibility = 'hidden';
                document.getElementById("noPengenalanBaruPengadu").style.display = 'none';
                $('#penyerahNoPengenalanBaruPengadu').attr("disabled", true);
                 $('#penyerahNoPengenalanLainPengadu').attr("disabled", false);
            }
        }

        function findDOBW(){
            var noPengenalanPengadu = $('#penyerahNoPengenalanBaruPengadu').val();
            if(noPengenalanPengadu.length =="12"){

                //tahun
                var year = noPengenalanPengadu.substr(0, 2);
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
                var month = noPengenalanPengadu.substr(2, 1);
                var bulan = "";
                if( month == "0"){
                    bulan = noPengenalanPengadu.substr(3, 1);
                }
                else{
                    bulan= noPengenalanPengadu.substr(2, 2);
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
                var hari = noPengenalanPengadu.substr(4,1);
                var day ="";
                if(hari == "0"){

                    day=noPengenalanPengadu.substr(5,1);
                }
                else{
                    day=noPengenalanPengadu.substr(4,2);
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
<table width="100%" bgcolor="yellow">
    <tr>
        <td width="100%" height="20" >
            <div style="background-color: yellow;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">${actionBean.permohonan.idPermohonan} : Pendaftaran Urusan</font>
            </div>
        </td>
    </tr>
</table>
<s:form  name="form1" id="form1"  beanclass="etanah.view.penguatkuasaan.SenaraiAduanActionBean">
    <s:hidden name="idPermohonan" value="actionBean.idPermohonan" />
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <br><span class=instr>Medan bertanda <em>*</em> adalah mandatori.</span><br>
        <p>&nbsp;</p>

        <fieldset class="aras1">
            <legend>Maklumat Aduan</legend>
            <div class="instr-fieldset">
                Sila Kemaskini Maklumat Berikut.
            </div>&nbsp;
            <p>
                <label>Tarikh :</label>
                <fmt:formatDate type="time" pattern="dd/MM/yyyy" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>
                &nbsp;
            </p>
            <p>
                <label>Daerah :</label>
                ${kodDaerah} - ${daerah}
                &nbsp;
            </p>
            <p>
                <label><em>*</em>Cara Aduan :</label>
                <s:select name="caraPermohonan.kod" value="${actionBean.permohonan.caraPermohonan.kod}" id="cara" style="width:139px;">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${actionBean.senaraiKodCaraPermohonan}" label="nama" value="kod" sort="nama" />
                </s:select>&nbsp;
            </p>
            <p>
                <label><em>*</em>Ringkasan Aduan :</label>
                <s:textarea name="sebab" rows="5" cols="50"/>&nbsp;
            </p>
            <p>
                <label>Peruntukan Seksyen :</label>
                <s:select name="kodUrusan.kod"  id="kodUrusan" value=""  style="width:400px;">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${actionBean.senaraiUrusan}" label="nama" value="kod" sort="kod" />
                </s:select>&nbsp;
            </p>
        </fieldset>
        <br>
        <fieldset class="aras1">
            <legend>Maklumat Lampiran</legend>
            <p>
            <div class="content" align="center">
                <display:table name="${actionBean.senaraiKandungan}" class="tablecloth" id="row" style="width:70%">
                   <c:if test="${row.dokumen.namaFizikal != null}">
                    <display:column title="Kod Dokumen" property="dokumen.kodDokumen.kod" />
                    <display:column title="Nama Dokumen" property="dokumen.kodDokumen.nama" />
                    <display:column title="Klasifikasi" property="dokumen.klasifikasi.nama" />
                    <display:column title="Catatan" property="catatan" />
                    <display:column title="Papar">
                        <c:if test="${row.dokumen.namaFizikal != null}">
                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                 onclick="doViewReport('${row.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                 onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${row.dokumen.kodDokumen.nama}"/>
                            <c:if test="${row.dokumen.baru eq 'Y' || row.dokumen.baru eq ''}">
                                <img src="${pageContext.request.contextPath}/pub/images/baharu.gif" alt="baru"/>
                            </c:if>
                        </c:if>
                    </display:column>
                   </c:if>
                   <c:if test="${row.dokumen.namaFizikal == null}">
                       <display:column title="Kod Dokumen"/>
                       <display:column title="Nama Dokumen"/>
                       <display:column title="Klasifikasi"/>
                       <display:column title="Catatan"/>
                   </c:if>
                </display:table>
            </div>
            
        </fieldset>
        <br>
        <fieldset class="aras1">
            <legend>Maklumat Pengadu</legend>
            <p>
                <label><em>*</em>Nama :</label>
                <s:text name="penyerahNama" value="${actionBean.permohonan.penyerahNama}" size="42"/>
                <%--${actionBean.permohonan.penyerahNama}--%>
                &nbsp;
            </p>
            <p>
                <label>Jenis Pengenalan :</label>
                <s:select name="penyerahJenisPengenalan.kod"  value="${actionBean.permohonan.penyerahJenisPengenalan.kod}"  style="width:139px;" id="pengenalanPengadu" onchange="jenisPengenalanPengadu()">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" sort="nama" />
                </s:select>
                &nbsp;
            </p>
            <%--<p>
                <label>No.Pengenalan :</label>
                <s:text name="penyerahNoPengenalan" maxlength="12"/>
                <font color="red" size="1">cth : 850510075342 </font>
                &nbsp;
            </p>--%>
             <p id="noPengenalanLainPengadu">
                    <label>No.Pengenalan :</label>
                    <s:text name="penyerahNoPengenalan" id="penyerahNoPengenalanLainPengadu" maxlength="12" onkeyup="this.value=this.value.toUpperCase();" />
                    <%--<font color="red" size="1">cth : 850510075342 </font>--%>
                    &nbsp;
                </p>

                <p id="noPengenalanBaruPengadu" style="visibility: hidden; display: none">
                    <label>No.Pengenalan :</label>
                    <s:text name="penyerahNoPengenalan" id="penyerahNoPengenalanBaruPengadu" maxlength="12" onkeyup="validateNumber(this,this.value);" onblur="javascript:findDOBW(this.value)"/>
                    <font color="red" size="1">cth : 850510071213 </font>
                    &nbsp;
                </p>
            <p>
                <label>Alamat :</label>
                <s:text name="penyerahAlamat1"  size="30"/>
                <%--${actionBean.permohonan.penyerahAlamat1}--%>
                &nbsp;
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="penyerahAlamat2"  size="30"/>
                <%--${actionBean.permohonan.penyerahAlamat2}--%>
                &nbsp;
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="penyerahAlamat3"  size="30"/>
                <%--${actionBean.permohonan.penyerahAlamat3}--%>
                &nbsp;
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="penyerahAlamat4"  size="30"/>
                <%--${actionBean.permohonan.penyerahAlamat4}--%>
                &nbsp;
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="penyerahPoskod" id="poskod" size="19" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);"/>
                <%--${actionBean.permohonan.penyerahPoskod}--%>
                &nbsp;
            </p>
            <p>
                <label>Negeri :</label>
                <s:select name="penyerahNegeri.kod"  value="${actionBean.permohonan.penyerahNegeri.kod}"  style="width:139px;">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" sort="nama" />
                </s:select>
                <%--${actionBean.permohonan.penyerahNegeri.nama}--%>
                &nbsp;
            </p>
            <p>
                <label>No.Telefon :</label>
                <s:text name="penyerahNoTelefon1" id="telefon" size="19" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);"/>
                <%--${actionBean.permohonan.penyerahNoTelefon1}--%>
                &nbsp;
            </p>
            <p>
                <label>Email :</label>
                <s:text name="penyerahEmail" id="email" size="40" maxlength="100" onblur="return ValidateEmail()"/>
                <%--${actionBean.permohonan.penyerahEmail}--%>
                &nbsp;
            </p>
        </fieldset>
        <br>
        <fieldset class="aras1">
            <legend>Lokasi Kejadian</legend>
                <div class="instr-fieldset">
                      Sila masukkan maklumat lokasi kejadian.
                </div>
              <p>
                    <label><em>*</em>Bandar/Pekan/Mukim :</label>
                    <s:select name="bandarPekanMukim.kod" id="bpm">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.listBandarPekanMukim}" label="nama" value="kod" sort="nama" />
                    </s:select>
                    &nbsp;
            </p>
                <p>
                    <label>Jenis Tanah :</label>
                    <s:select name="pemilikan.kod" id="milik">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodPemilikan}" label="nama" value="kod" sort="nama" />
                    </s:select>
                    &nbsp;
                </p>
                <p>
                    <label>Nombor Lot :</label>
                    <s:text name="noLot" onkeyup="validateNumber(this,this.value);"/> &nbsp;
                </p>
                <p>
                    <label><em>*</em> Lokasi :</label>
                    <s:textarea name="lokasi" id="message" rows="5" cols="50" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;
                </p>
           <%-- <legend>
                Lokasi Pencerobohan
            </legend>
            <div class="instr-fieldset">
                Klik butang tambah untuk masukkan maklumat lokasi pencerobohan
            </div>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiAduanLokasi}" pagesize="10" cellpadding="0" cellspacing="0" id="line1" >
                    <display:column title="Bil" sortable="true">${line1_rowNum}</display:column>
                    <display:column property="idAduanLokasi" title="Id Aduan"></display:column>
                    <display:column property="cawangan.name" title="Cawangan"></display:column>
                    <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim"></display:column>
                    <display:column property="pemilikan.nama" title="Jenis Tanah"></display:column>
                    <display:column property="noLot" title="No.Lot"></display:column>
                    <display:column property="lokasi" title="Lokasi"></display:column>
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line1_rowNum}' onmouseover="this.style.cursor='pointer';"  onclick="removeLokasi('${line1.idAduanLokasi}',this.form);"/>
                        </div>
                    </display:column>
                    <display:column title="Kemaskini">
                        <div align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                 id='rem_${line1_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editPopupLokasi('${line1.idAduanLokasi}');"/>
                        </div>
                    </display:column>
                </display:table>
                <br>
                <s:button class="btn" value="Tambah" name="new" id="new" onclick="addPopupLokasi();"/>

            </div>--%>
        </fieldset>

        <fieldset class="aras1">
            <legend>Maklumat Orang Yang Disyaki</legend>
            <div class="content" align="center">
                <div class="instr-fieldset">
                    Klik butang tambah untuk masukkan maklumat orang yang disyaki
                </div>
                <display:table class="tablecloth" name="${actionBean.senaraiOrangKenaSyak}" pagesize="10" cellpadding="0" cellspacing="0" id="line2" >
                    <display:column title="Bil" sortable="true">${line2_rowNum}</display:column>
                    <display:column property="noPengenalan" title="No Pengenalan"></display:column>
                    <%--<display:column property="idOrangKenaSyak" title="Nama"></display:column>--%>
                    <display:column property="nama" title="Nama"></display:column>
                    <display:column title="Alamat">${line2.alamat.alamat1}
                        <c:if test="${line2.alamat.alamat2 ne null}"> , </c:if>
                        ${line2.alamat.alamat2}
                        <c:if test="${line2.alamat.alamat3 ne null}"> , </c:if>
                        ${line2.alamat.alamat3}
                        <c:if test="${line2.alamat.alamat4 ne null}"> , </c:if>
                        ${line2.alamat.alamat4}
                        <c:if test="${line2.alamat.poskod ne null}"> , </c:if>
                        ${line2.alamat.poskod}
                        <c:if test="${line2.alamat.negeri.nama ne null}"> , </c:if>
                        ${line2.alamat.negeri.nama}</display:column>
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem2_${line2_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeOKS('${line2.idOrangKenaSyak}');"/>
                        </div>
                    </display:column>
                    <display:column title="Kemaskini">
                        <div align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                 id='rem2_${line2_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editPopupOKS('${line2.idOrangKenaSyak}');"/>
                        </div>
                    </display:column>
                </display:table>
                <s:button class="btn" value="Tambah" name="new" id="new" onclick="addPopupOKS();"/>
            </div>

        </fieldset>
        <p align="right">
            <s:submit name="updateAduan" id="updateAduan" value="Simpan" class="btn" onclick="return checkKodUrusan()"/>
        </p>
    </div>
</s:form>