<%-- 
    Document   : AduanNoTab
    Created on : 18-Apr-2011, 15:02:19
    Author     : nordiyana
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">


<%--function refreshPage(){
var url = '${pageContext.request.contextPath}/pengambilan/kemasukanaduan?refreshpage';
$.get(url,
function(data){
$('#page_div').html(data);
},'html');
}--%>
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
              <%--alert("search:"+index);--%>
             var idH = $("#idHakmilik").val();
             var url = '${pageContext.request.contextPath}/pengambilan/kemasukanaduan?HakmilikPopup&idHakmilik='+idH;
             window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
         }

    function selectRadio(obj){
        $("#selectedIdPP").val(obj.value);
    }
</script>

           <s:form name="form1" id="form1" beanclass="etanah.view.stripes.pengambilan.AduanNoTabActionBean">
<table width="100%" >
    <tr>
        <td width="100%" height="20" >
            <div  align="center">

            </div>
        </td>
    </tr>
</table>

    <%--<s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>--%>
    <s:messages/>
    <div class="instr" align="center">
        <s:errors/>
    </div>
    <s:hidden id="selectedIdHM" name="selectedIdHM" />
    <s:hidden id="selectedIdPP" name="selectedIdPP" />
    <%--<s:hidden name="index" id="index" />--%>
     <%--<s:hidden name="jenisPihak"/>
        <s:hidden name="pihak.nama"/>
        <s:hidden name="pihak.idPihak"/>
        <s:hidden name="permohonanPihak.jenis.kod"/>
        <s:hidden name="pihakCawangan.idPihakCawangan"/>
        <s:hidden name="jenisPihak" value="${jenis}"/>
        <s:hidden name="hakmilik" value="${hakmilik}"/>--%>
        <s:errors/>
        <fieldset class="aras1">
            <legend>CARIAN HAKMILIK</legend>
            <br/>
            <div >
                <table>
                    <tr>
                        <td><label for="nama"><font color="red">*</font>Id Hakmilik :</label></td>
                        <td><s:text name="idHakmilik" id="idHakmilik" size="25" onkeyup="this.value=this.value.toUpperCase();"/></td>
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
                    <%--<c:if test="${showDetails}">--%>
                    <table width="100%" >
    <tr>
        <td width="100%" height="20" >
            <div  align="center">
               PENGAMBILAN : BORANG LAPORAN ADUAN KEROSAKAN
            </div>
        </td>
    </tr>
</table>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Permohonan</legend>
            <br/>
            <table>
                <tr>
                    <td><label for="nama">Nama Permohonan :</label></td>
                    <td>${actionBean.hakmilikPermohonan.permohonan.idPermohonan}</td>
                </tr>
                <tr>
                    <td><label for="nama">Tarikh Permohonan :</label></td>
                    <td><fmt:formatDate value="${actionBean.hakmilikPermohonan.permohonan.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy"/></td>
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
                <%--<tr>
                    <td><label for="nama">Peruntukan Seksyen :</label></td>
                    <td><s:select name="kodUrusan.kod" value="${actionBean.kodUrusan.kod}"  style="width:400px;">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${actionBean.senaraiUrusan}" label="nama" value="kod" sort="kod" />
                </s:select>&nbsp;</td>
                </tr>--%>

                <tr>
                    <td><label for="nama">Perihal Aduan :</label></td>
                    <td><s:textarea name="perihal" value="" rows="5" cols="50" onkeyup="this.value=this.value.toUpperCase();">
                            <%--${actionBean.permohonanAduan.perihal}--%>
                        </s:textarea></td>
                </tr>
            </table>
            </fieldset>
        </div>
                <br/>
        <div  class="subtitle">
            <fieldset class="aras1">
              <legend>Maklumat Tuan Tanah/Pengadu</legend><br />
              <div  align="center">
            <display:table style="width:50%" class="tablecloth" name="${actionBean.hakmilikPermohonan.hakmilik.senaraiPihakBerkepentingan}" pagesize="3" cellpadding="1" cellspacing="1" requestURI="/pengambilan/kemasukanaduan" id="line">
                <display:column> <s:radio name="radio_" id="${line.pihak.idPihak}" value="${line.pihak.idPihak}" style="width:15px" onclick="javascript:selectRadio(this)"/></display:column>
                <display:column title="Id Pihak" property="pihak.idPihak" style="vertical-align:top;"/>
                <display:column title="Nama " property="pihak.nama" style="text-transform:uppercase;vertical-align:top;" />
            </display:table>
                  <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>


              </div>
            </fieldset>
                  <%--</c:if>--%>
                   <p align="right">
                <s:submit class="btn" name="selesai" value="Selesai"/></p>

       </div>
</s:form>



