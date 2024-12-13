<%--
    Document   : kemasukan_aduan
    Created on : 29-Oct-2010, 16:21:14
    Author     : nordiyana
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">

    function refreshPage(){
        var q = $('#form1').serialize();
        var url = document.form1.action + '?refreshPage&';// + event;
        window.location = url+q;

    }
    function addPopupForm(){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/kemasukan_aduan?oksPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }
    function editPopupForm(id){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/kemasukan_aduan?oksPopup2&id="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }
    function removeOKS(id){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            window.location = "${pageContext.request.contextPath}/penguatkuasaan/kemasukan_aduan?removeOKS&id="+id;
        }
    }
    function validateForm(){

        if(document.form1.cara.value=="")
        {
            alert("Sila Pilih Cara Aduan");
            return false;
        }
        if(document.form1.bpm.value=="")
        {
            alert("Sila Pilih Bandar/Pekan/Mukim");
            return false;
        }

        return true;
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
    function textCounter(field, countfield, maxlimit) {
        if (field.value.length > maxlimit) // if too long...trim it!
            field.value = field.value.substring(0, maxlimit);
        // otherwise, update 'characters left' counter
        else
            countfield.value = maxlimit - field.value.length;
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
     function selectName(val){
            var url = '${pageContext.request.contextPath}/pengambilan/kemasukanaduan?selectName&idHakmilik='+val;
            $.get(url,
            function(data){
                $('#caw').html(data);
            },'html');
        }
        function search(){
             // alert("search:"+index);
             var idH = $("#idHakmilik").val();
             var url = '${pageContext.request.contextPath}/pengambilan/kemasukanaduan?HakmilikPopup&idHakmilik='+idH;
             window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
         }

    function selectRadio(obj){
        $("#selectedPihak").val(obj.value);
    }
</script>
            <div class="subtitle" id="caw">
           <s:form name="form1" id="form1" beanclass="etanah.view.stripes.pengambilan.KemasukanAduanActionBean">
<table width="100%" >
    <tr>
        <td width="100%" height="20" >
            <div  align="center">
                PENGAMBILAN : BORANG LAPORAN ADUAN KEROSAKAN
            </div>
        </td>
    </tr>
</table>

    <%--<s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>--%>
    <s:hidden id="selectedIdHM" name="selectedIdHM" />
    <s:hidden name="hakmilikPermohonan.id" />
    <s:hidden id="selectedPihak" name="selectedPihak" />
     <%--<s:hidden name="jenisPihak"/>
        <s:hidden name="pihak.nama"/>
        <s:hidden name="pihak.idPihak"/>
        <s:hidden name="permohonanPihak.jenis.kod"/>
        <s:hidden name="pihakCawangan.idPihakCawangan"/>
        <s:hidden name="jenisPihak" value="${jenis}"/>
        <s:hidden name="hakmilik" value="${hakmilik}"/>--%>
        <s:errors/>
        <s:messages/>


        <fieldset class="aras1">
            <legend>BORANG ADUAN PENGAMBILAN SEKSYEN 4</legend>
            <br/>
            <div >
                <table>
                    <tr>
                        <td><label for="nama"><font color="red">*</font>Id Hakmilik :</label></td>
                        <td><s:text name="idHakmilik" id="idHakmilik" size="40" onkeyup="this.value=this.value.toUpperCase();"/></td>
                        <td><s:button class="btn" value="Cari" name="add" onclick="javascript:search()"/>
                            </td>
                    </tr>
                    <%--<tr>
                        <td><label>&nbsp;</label></td>
                        <td><s:button class="btn" value="Cari" name="add" onclick="javascript:search()"/>
                            <s:button name="cariHakmilikUrusan" value="Cari" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                            <s:submit name="cariSemulaPemohon" value="Isi Semula" class="btn"/></td>
                    </tr>--%>
                </table>
            </div>
                    <br/><br/>

</fieldset>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Permohonan</legend>
            <br/>
            <table>
                <tr>
                    <td><label for="nama">Nama Permohonan :</label></td>
                    <td>${actionBean.permohonan.permohonanSebelum.idPermohonan}</td>
                </tr>
                <tr>
                    <td><label for="nama">Tarikh Permohonan :</label></td>
                    <td><fmt:formatDate value="${actionBean.permohonan.permohonanSebelum.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy"/></td>
                </tr>
                <tr>
                    <td><label for="nama">Agensi Pemohon :</label></td>
                    <td>${actionBean.pemohon.pihak.nama}</td>
                </tr>
                <tr>
                    <td><label>Alamat :</label></td>
                    <td>${actionBean.pemohon.pihak.alamat1}</td>
                </tr>
                <tr>
                    <td><label >&nbsp;&nbsp;</label></td>
                    <td>${actionBean.pemohon.pihak.alamat2}</td>
                </tr>
                <tr>
                    <td><label >&nbsp;&nbsp;</label></td>
                    <td>${actionBean.pemohon.pihak.alamat3}</td>
                </tr>
                <tr>
                    <td><label >&nbsp;&nbsp;</label></td>
                    <td>${actionBean.pemohon.pihak.alamat4}</td>
                </tr>
                <tr>
                    <td><label>Poskod :</label></td>
                    <td>${actionBean.pemohon.pihak.poskod}</td>
                </tr>
                <tr>
                    <td><label >Negeri :</label></td>
                    <td>${actionBean.pemohon.pihak.negeri.nama}</td>
                </tr>
                <tr>
                    <td><label >No. Telefon :</label></td>
                    <td>${actionBean.pemohon.pihak.noTelefon1}</td>
                </tr>

            </table>
            </fieldset>
        </div>

        <br/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Tanah</legend>
            <br/>
            <table>
                <tr>
                    <td><label for="nama">No Hakmilik :</label></td>
                    <td>${actionBean.hakmilikPermohonan.hakmilik.idHakmilik}</td>
                </tr>
                <tr>
                    <td><label for="nama">No Lot :</label></td>
                    <td>${actionBean.hakmilikPermohonan.hakmilik.noLot}</td>
                </tr>
                <tr>
                    <td><label for="nama">Mukim :</label></td>
                    <td>${actionBean.hakmilikPermohonan.hakmilik.bandarPekanMukim.nama}</td>
                </tr>

            </table>
            </fieldset>
        </div>
        <br/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Aduan</legend>
            <br/>
            <table>
                <tr>
                    <td><label for="nama">Perihal Aduan :</label></td>
                    <td><s:textarea name="perihal" value="" rows="5" cols="50" onkeyup="this.value=this.value.toUpperCase();"/></td>
                </tr>
            </table>
            </fieldset>
        </div>
        <div  class="subtitle">
            <fieldset class="aras1">
              <legend>Maklumat Tuan Tanah/Pengadu</legend><br />
              <div  align="center">
            <display:table style="width:50%" class="tablecloth" name="${actionBean.hakmilikPermohonan.hakmilik.senaraiPihakBerkepentingan}" pagesize="3" cellpadding="1" cellspacing="1" requestURI="/pengambilan/kemasukanaduan" id="line">
                <display:column> 
                    <%--<s:radio name="radio_" id="${line.pihak.idPihak}" value="${line.pihak.idPihak}" style="width:15px" onclick="javascript:selectRadio(this)"/>--%>
                    <s:checkbox name="chkbox" id="chkbox" value="${line.pihak.idPihak}"/>
                </display:column>
                <display:column title="Id Pihak" property="pihak.idPihak" style="vertical-align:top;"/>
                <display:column title="Nama " property="pihak.nama" style="text-transform:uppercase;vertical-align:top;" />
            </display:table>
                  <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
              </div>
            </fieldset>

       </div>






























   <%-- <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Hakmilik</legend><br>
             <div align="center">



                    <p>
                        <label for="nama"><font color="red">*</font>Id Hakmilik :</label>
                        <s:text name="hakmilikUrusan.hakmilik.idHakmilik" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <c:if test="${fn:length(actionBean.hakmilikByList) > 0}">
                        <div align="center">
                            <display:table class="tablecloth" name="${actionBean.hakmilikByList}" cellpadding="0" cellspacing="0" id="line" pagesize="10"
                                           requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                <display:column  title="No Hakmilik" class="nama">
                                        <a href="#" onclick="selectName('${line.hakmilik.idhakmilik}');return false;">${line.hakmilik.idhakmilik}</a>

                                </display:column>
                                <display:column title="Urusan" />
                                <display:column title="Tarikh Permohonan" />
                            </display:table>
                        </div>
                    </c:if>
                    <s:submit name="cariHakmilikUrusan" value="Cari" class="btn"/>
                    <s:submit name="cariSemulaPemohon" value="Isi Semula" class="btn"/>&nbsp;

            </div>
        </fieldset>--%>
      <%-- <div  id="hakmilik_details">
        <fieldset class="aras1"><br/>
            <legend align="left"><b>Maklumat Pengadu</b></legend>
            <div align="center">
             <div class="content" align="center">
                <table border="0" cellspacing="5" width="80%">
                    <tr>
                        <td align="left" width="30%" valign="top"><label style="; clear: right"  ><font color="red">*</font>Maklumat permohonan :</label></td>
                        <td><s:text name="idSblm" size="30" id="idSblm" onkeyup="this.value=this.value.toUpperCase();" value=""/><s:button class="btn" value="Cari" name="add" onclick="javascript:search()"/></td>${actionBean.idPermohonan}
                    </tr>
                    <tr>
                        <td align="right"><label>Nama Projek :</label></td>
                        <td><s:text name="namaProjek" size="30" id="namaProjek" onkeyup="this.value=this.value.toUpperCase();"/><s:button class="btn" value="Cari" name="add" onclick="javascript:search()" /></td>
                        <td>${actionBean.namaProjek}</td>
                    </tr>

                    <tr>
                        <td align="right"><s:button name="checkSebelum" value="Semak" class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');"/></td>
                        <td><s:button name="reset" value="Isi Semula" class="btn" onclick="test();"/></td>
                    </tr>
                </table>
            </div>
        </fieldset>
          <fieldset class="aras1">
              <legend>Maklumat Tuan Tanah/Pengadu</legend><br />
               <div align="center">
                   <table width="100%">
              <tr>
                  <td width="30%"><label>Nama Pengadu :</label></td>
                  <td>${actionBean.permohonanSblm.sebab }<br /></td>
              </tr>
              <tr>
                  <td><label >Jenis Pengenalan :</label></td>
                  <td><s:select name="penyerahJenisPengenalan.kod"  value=""  style="width:139px;" id="pengenalan">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" sort="nama" />
                </s:select>
                &nbsp;</td>
              </tr>
              <tr>
                  <td><label >No.Pengenalan :</label></td>
                  <td><s:text name="penyerahNoPengenalan" maxlength="12" />
                <font color="red" size="1">cth : 850510075342 </font><br /></td>
              </tr>
              <tr>
                  <td><label>Alamat :</label></td>
                  <td><s:text name="penyerahAlamat1"  size="30" onkeyup="this.value=this.value.toUpperCase();"/> <br /></td>
              </tr>
              <tr>
                  <td><label >&nbsp;&nbsp;</label></td>
                  <td><s:text name="penyerahAlamat2"  size="30" onkeyup="this.value=this.value.toUpperCase();"/><br /></td>
              </tr>
              <tr>
                  <td><label >&nbsp;&nbsp;</label></td>
                  <td><s:text name="penyerahAlamat3"  size="30" onkeyup="this.value=this.value.toUpperCase();"/><br /></td>
              </tr>
              <tr>
                  <td><label >&nbsp;&nbsp;</label></td>
                  <td><s:text name="penyerahAlamat4"  size="30" onkeyup="this.value=this.value.toUpperCase();"/><br /></td>
              </tr>
              <tr>
                  <td><label>Poskod :</label></td>
                  <td><s:text name="penyerahPoskod" id="poskod" size="19" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);"/><br /></td>
              </tr>
              <tr>
                  <td><label >Negeri :</label></td>
                  <td><s:select name="penyerahNegeri.kod"  style="width:139px;">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" sort="nama" />
                </s:select></td>
              </tr>

              <tr>
                  <td><label >No. Telefon :</label></td>
                  <td><<s:text name="penyerahNoTelefon1" id="telefon" size="19" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);"/></td>
              </tr>
              <tr>
                  <td><label >Tarikh :</label></td>
                  <td><s:text name="tarikhDok" class="datepicker" id="datepicker" formatPattern="dd/MM/yyyy"/></td>
              </tr>
              <tr>
                  <td><label >Bank :</label></td>
                  <td><s:select name="kodBank.kod" style="width:300px;" id="kodBank" >
                            <s:option value="">-- Sila Pilih --</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodBank}" value="kod" label="nama"/>
                        </s:select>
                  </td>
              </tr>
              <tr>
                  <td><label>Email :</label></td>
                  <td><s:text name="penyerahEmail" id="email" size="40" maxlength="100" onblur="return ValidateEmail()"/></td>
              </tr>

            </table>
              <br/>
              <table align="center">
                  <tr>
                      <td>
                  <s:hidden name="idPihak" value="${actionBean.idPihak}"/>
                  <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                  <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation()) doSubmit(this.form, this.name, 'page_div')"/>
                  </td>
                  </tr>
              </table>
               </div>
          </fieldset>
          <br/>
        <fieldset class="aras1">
            <legend>Maklumat Aduan</legend>
            <div class="instr-fieldset">
                <font color="red">PERINGATAN:</font>Sila Masukan Maklumat Berikut.
            </div>&nbsp;
            <p>
                <label>Tarikh :</label>
                <fmt:formatDate type="time" pattern="dd/MM/yyyy" value="<%=new java.util.Date()%>"/>
                &nbsp;</p>
            <p>
                <label>Projek Pengambilan :</label>
                ${actionBean.sebab}&nbsp;
            </p>
            <p>
                <label>Tarikh Pengambilan :</label>
                <fmt:formatDate type="time" pattern="dd/MM/yyyy" value="<%=new java.util.Date()%>"/>
            </p>
            <p>
                <label>Agensi Pemohon :</label>
                ${kodDaerah} - ${daerah} &nbsp;
            </p>
            <p>
                <label>Cara Aduan :</label>
                <s:select name="caraPermohonan.kod"  style="width:139px;" id="cara">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="" label="nama" value="kod" sort="nama" />
                </s:select>&nbsp;
            </p>
            <p>
                <label>Ringkasan Aduan :</label>
                <s:textarea name="sebab" value="" rows="5" cols="50" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;
            </p>
            <p>
                <label>Peruntukan Seksyen :</label>
                <s:select name="kodUrusan.kod" value=""  style="width:400px;">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="" label="nama" value="kod" sort="kod" />
                </s:select>&nbsp;
                ${actionBean.senaraiUrusan}
                ${actionBean.kodUrusan.kod}
                ${actionBean.senaraiKodCaraPermohonan}
            </p>
        </fieldset >
        <br>
        <fieldset class="aras1">
            <legend>Maklumat Pengadu</legend>
            <p>
                <label>Nama :</label>
                <s:text name="penyerahNama" size="42" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Jenis Pengenalan :</label>
                <s:select name="penyerahJenisPengenalan.kod"  value=""  style="width:139px;" id="pengenalan">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" sort="nama" />
                </s:select>
                &nbsp;
            </p>
            <p>
                <label>No.Pengenalan :</label>
                <s:text name="penyerahNoPengenalan" maxlength="12" />
                <font color="red" size="1">cth : 850510075342 </font>
                &nbsp;
            </p>
            <p>
                <label for="alamat"><font color="red">*</font>Alamat Berdaftar :</label>
                            <s:text name="pihak.alamat1" size="40" id="alamat1" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>

                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.alamat2" size="40" id="alamat2" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>

                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.alamat3" size="40" id="alamat3" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label> Bandar :</label>
                            <s:text name="pihak.alamat4" size="40" id="alamat4" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="pihak.poskod" size="40" maxlength="5" id="poskod" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="pihak.negeri.kod" id="negeri">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <p>
                            <label>No.Telefon :</label>
                            <s:text name="pihak.noTelefon1" maxlength="11" id="notelefon"  /><font color="red">*</font>
                        </p>
                        <p>
                            <label>Email :</label>
                            <s:text name="pihak.email" size="40" maxlength="100" />
                        </p>
        </fieldset>
        <br>
        <fieldset class="aras1">
            <legend>Maklumat Lokasi Aduan</legend>
            <p>
                <label>Bandar/Pekan/Mukim :</label>
                <s:select name="bandarPekanMukim.kod" id="bpm">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${actionBean.listBandarPekanMukim}" label="nama" value="kod" sort="nama" />
                </s:select><em>*</em>
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
                <label>Lokasi :</label>
                <s:textarea name="lokasi" id="message" rows="5" cols="50" onkeydown="textCounter(this.form.message,this.form.remLen,100);" onkeyup="textCounter(this.form.message,this.form.remLen,100);" onkeypress="this.value=this.value.toUpperCase();"/>&nbsp;
            </p>
        </fieldset>

        <p align="right">
             <s:submit class="btn" name="saveMohon" value="Seterusnya" onclick="return validateForm();"/>
        </p>
    </div>--%>
</s:form>
                </div>

