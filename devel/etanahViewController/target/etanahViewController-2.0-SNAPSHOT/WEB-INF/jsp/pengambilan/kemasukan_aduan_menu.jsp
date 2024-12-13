<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
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
            var url = '${pageContext.request.contextPath}/pengambilan/kemasukanaduanMenu?selectName&idHakmilik='+val;
            $.get(url,
            function(data){
                $('#caw').html(data);
            },'html');
        }
    function search(){
         // alert("search:"+index);
         var idH = $("#idHakmilik").val();
         var url = '${pageContext.request.contextPath}/pengambilan/kemasukanaduanMenu?HakmilikPopup&idHakmilik='+idH;
         window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
     }

     function searchHakmilik(){
         // alert("search:"+index);
         var idH = $("#idHakmilik").val();
         if(idH == ""){
             alert('Sila masukkan Id Hakmilik');
             $("#idHakmilik").focus();
         }
         if(idH != null){
         alert(idH);
         var url = '${pageContext.request.contextPath}/pengambilan/kemasukanaduanMenu?searchHakmilik&idHakmilik='+idH;
         $.get(url,
         function(data){
             $('#page_div').html(data);
         },'html');
         }
     }

    function selectMH(obj){
        $("#selectedIdHM").val(obj);
    }
    function selectRadio(obj){
        $("#selectedPihak").val(obj.value);
    }

    function ajaxLink(link, update) {
        $.get(link, function(data) {
        $(update).html(data);
        $(update).show();
        });
        return false;
    }
</script>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form name="form1" id="form1" beanclass="etanah.view.stripes.pengambilan.KemasukanAduanMenuActionBean">
            <stripes:messages />
            <stripes:errors />

            <p class=instr>Pastikan maklumat di bawah adalah betul dan masukkan butir-butir pembayaran. PERINGATAN: Tidak dibenarkan menggunakan cara pembayaran yang lain
                bersama dengan Cek dan hanya satu Cek dibenarkan.</p>

            <stripes:form action="/pengambilan/kemasukanaduan2" >

                <stripes:hidden name="selectedItem" id="selectedItem" />
                <div  id="hakmilik_details">
    <div class="subtitle">
        <table width="100%" >
        <tr>
            <td width="100%" height="20" >
                <div  align="center">
                    PENGAMBILAN : BORANG LAPORAN ADUAN KEROSAKAN
                </div>
            </td>
        </tr>
    </table>
           <fieldset class="aras1">
        <legend>BORANG ADUAN PENGAMBILAN SEKSYEN 4</legend>
        <br/>
        <div >
            <table>
                <tr>
                    <td><label for="nama"><font color="red">*</font>Id Hakmilik :</label></td>
                    <td><s:text name="idHakmilik" id="idHakmilik" size="40" onkeyup="this.value=this.value.toUpperCase();"/></td>
                    <td>
                        <%--<s:button class="btn" value="Cari" name="add" onclick="javascript:search()"/>--%>
                        <%--<s:button class="btn" value="Cari" name="add" onclick="javascript:searchHakmilik()"/>--%>
                        <stripes:submit name="searchHakmilik" value="Cari" class="btn" onclick="" />
                        <%--javascript:searchHakmilik()--%>

                    </td>
                </tr>
            </table>
        </div>
            <br/><br/>
    </fieldset>
            </div>
                        <c:if test="${fn:length(actionBean.senaraiHakmilikPermohonan) > 0}" >
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Senarai Permohonan Beserta Nama Projek</legend><br />
                <div  align="center">
                    <display:table style="width:70%" class="tablecloth" name="${actionBean.senaraiHakmilikPermohonan}" pagesize="5" cellpadding="1" cellspacing="1" requestURI="/pengambilan/kemasukanaduan2" id="line">
                        <%--<display:column> <s:radio name="radio_" id="${line.id}" value="${line.id}" style="width:15px" onclick="javascript:selectMH(this)"/></display:column>--%>
                        <display:column title="Id Permohonan" style="vertical-align:top;">
                            <s:link beanclass="etanah.view.stripes.pengambilan.KemasukanAduanMenuActionBean"
                                    event="cariHakmilikUrusan" onclick="javascript:selectMH('${line.id}');return ajaxLink(this, '#hakmilik_details');" >
                                <s:param name="selectedIdHM" value="${line.id}"/>
                                <s:param name="idHakmilik" value="${actionBean.idHakmilik}"/>
                                ${line.permohonan.idPermohonan}
                            </s:link>
                        </display:column>
                        <display:column title="Nama Projek" property="permohonan.sebab" style="text-transform:uppercase;vertical-align:top;" />
                        <display:column title="Tarikh Masuk" style="vertical-align:top;">
                            <fmt:formatDate value="${line.permohonan.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy"/>
                        </display:column>
                    </display:table>
                </div>
            </fieldset>
        </div>
    </c:if>
            <br/>
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
                    <td><label for="nama">Nama Permohonan :</label></td>
                    <td><font style="text-transform:uppercase;">${actionBean.permohonan.permohonanSebelum.kodUrusan.nama}</font></td>
                </tr>

                <tr>
                    <td><label for="nama">Tarikh Permohonan :</label></td>
                    <td><fmt:formatDate value="${actionBean.permohonan.permohonanSebelum.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy"/></td>
                </tr>
                <tr>
                    <td><label for="nama">Agensi Pemohon :</label></td>
                    <td>${actionBean.permohonan.permohonanSebelum.senaraiPemohon[0].pihak.nama}</td>
                </tr>
                <tr>
                    <td><label>Alamat :</label></td>
                    <td>${actionBean.permohonan.permohonanSebelum.senaraiPemohon[0].pihak.alamat1}</td>
                </tr>
                <tr>
                    <td><label >&nbsp;&nbsp;</label></td>
                    <td>${actionBean.permohonan.permohonanSebelum.senaraiPemohon[0].pihak.alamat2}</td>
                </tr>
                <tr>
                    <td><label >&nbsp;&nbsp;</label></td>
                    <td>${actionBean.permohonan.permohonanSebelum.senaraiPemohon[0].pihak.alamat3}</td>
                </tr>
                <tr>
                    <td><label >&nbsp;&nbsp;</label></td>
                    <td>${actionBean.permohonan.permohonanSebelum.senaraiPemohon[0].pihak.alamat4}</td>
                </tr>
                <tr>
                    <td><label>Poskod :</label></td>
                    <td>${actionBean.permohonan.permohonanSebelum.senaraiPemohon[0].pihak.poskod}</td>
                </tr>
                <tr>
                    <td><label >Negeri :</label></td>
                    <td>${actionBean.permohonan.permohonanSebelum.senaraiPemohon[0].pihak.negeri.nama}</td>
                </tr>
                <tr>
                    <td><label >No. Telefon :</label></td>
                    <td>${actionBean.permohonan.permohonanSebelum.senaraiPemohon[0].pihak.noTelefon1}</td>
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
            <display:table style="width:50%" class="tablecloth" name="${actionBean.hakmilikPermohonan.hakmilik.senaraiPihakBerkepentingan}" pagesize="3" cellpadding="1" cellspacing="1" requestURI="/pengambilan/kemasukanaduan2" id="line">
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
        </div>




        </stripes:form>


</s:form>







































