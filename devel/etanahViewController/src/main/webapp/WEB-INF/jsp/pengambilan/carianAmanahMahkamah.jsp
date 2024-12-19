<%-- 
    Document   : carianAmanahMahkamah
    Created on : 27-Oct-2011, 11:54:51
    Author     : nordiyana
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
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
     function selectName(val){
            var url = '${pageContext.request.contextPath}/pengambilan/kemasukanaduan1?selectName&idHakmilik='+val;
            $.get(url,
            function(data){
                $('#caw').html(data);
            },'html');
        }
    function search(){
         // alert("search:"+index);
         var idH = $("#idHakmilik").val();
         var url = '${pageContext.request.contextPath}/pengambilan/kemasukanaduan1?HakmilikPopup&idHakmilik='+idH;
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
         //alert(idH);
         var url = '${pageContext.request.contextPath}/pengambilan/kemasukanaduan1?searchHakmilik&idHakmilik='+idH;
         $.get(url,
         function(data){
             $('#page_div').html(data);
         },'html');
         }
     }

    function selectMH(obj){
        $("#selectedIdHM").val(obj);
        alert($("#selectedIdHM").val(obj.value));
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
    function validation() {

    <%--if($("#selectedPihak").val() == ""){
            alert('Sila pilih " Tuan Tanah Terlibat " terlebih dahulu.');
            $("#datepicker").focus();
            return false;
        }--%>

        if($("#selectedIdHM").val() == ""){
            alert('Sila pilih " Permohonan Terdahulu " terlebih dahulu.');
            $("#selectedIdHM").focus();
            return false;
        }
        return true;
    }

</script>
<s:form name="form1" id="form1" beanclass="etanah.view.stripes.pengambilan.KemasukanAduan1ActionBean">
    <div  id="hakmilik_details">
    <div class="subtitle displaytag">
        <table width="100%" >
        <tr>
            <td width="100%" height="20" >
                <div  align="center">
                    PENGAMBILAN : BORANG LAPORAN ADUAN KEROSAKAN
                </div>
            </td>
        </tr>
    </table>
    <s:messages/>
    <s:errors/>
    <s:hidden name="hakmilikPermohonan.id" />
    <s:hidden id="selectedIdHM" name="selectedIdHM" />
    <s:hidden id="selectedPihak" name="selectedPihak" />
    <fieldset class="aras1">
        <legend>BORANG ADUAN PENGAMBILAN SEKSYEN 4</legend>
        <br/>
        <%--<div >
            <table>
                <tr>
                    <td><label for="nama"><font color="red">*</font>Id Hakmilik :</label></td>
                    <td><s:text name="idHakmilik" id="idHakmilik" size="40" onkeyup="this.value=this.value.toUpperCase();"/></td>
                    <td>
                        <s:button class="btn" value="Cari" name="add" onclick="javascript:search()"/>
                        <s:button class="btn" value="Cari" name="add" onclick="javascript:searchHakmilik()"/>
                        <stripes:submit name="searchHakmilik" value="Cari" class="btn" onclick="" />
                        <s:submit class="btn" value="Cari" name="add" onclick="javascript:searchHakmilik()"/>

                    </td>
                </tr>
            </table>
        </div>--%>
            <br/><br/>
    </fieldset>
            </div>

    <c:if test="${fn:length(actionBean.senaraiHakmilikPermohonan) > 0}" >
        <div class="subtitle">
            <fieldset class="aras1">
                <%--pagesize="3"--%>
                <legend>Senarai Permohonan Beserta Nama Projek</legend><br />
                <div  align="center">
                    <display:table style="width:70%" class="tablecloth" name="${actionBean.senaraiHakmilikPermohonan}" cellpadding="1" cellspacing="1" requestURI="/pengambilan/kemasukanaduan1" id="line">
                        <display:column title="ID Permohonan">
                        <s:link beanclass="etanah.view.stripes.pengambilan.KemasukanAduan1ActionBean"
                        event="cariHakmilikUrusan" onclick="javascript:selectMH('${line.id}');return ajaxLink(this, '#hakmilik_details');" >
                        <s:param name="idHakmilik" value="${actionBean.idHakmilik}"/>
                        <s:param name="selectedIdHM" value="${line.id}"/>${line.permohonan.idPermohonan}</s:link>
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
                    <td><label for="nama">Nama Rujukan Permohonan :</label></td>
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
                    <td><s:textarea name="perihal" value="" rows="5" cols="50"/></td>
                </tr>
            </table>
            </fieldset>
        </div>
       <br/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pengadu</legend>
            <br/>
            <table>
               <tr>
                    <td><label for="nama">Nama Pengadu :</label></td>
                    <td>${actionBean.permohonan.penyerahNama}</td>
                </tr>
                <tr>
                    <td><label>Alamat :</label></td>
                    <td>${actionBean.permohonan.penyerahAlamat1}</td>
                </tr>
                <tr>
                    <td><label >&nbsp;&nbsp;</label></td>
                    <td>${actionBean.permohonan.penyerahAlamat2}</td>
                </tr>
                <tr>
                    <td><label >&nbsp;&nbsp;</label></td>
                    <td>${actionBean.permohonan.penyerahAlamat3}</td>
                </tr>
                <tr>
                    <td><label >&nbsp;&nbsp;</label></td>
                    <td>${actionBean.permohonan.penyerahAlamat4}</td>
                </tr>
                <tr>
                    <td><label>Poskod :</label></td>
                    <td>${actionBean.permohonan.penyerahPoskod}</td>
                </tr>
                <tr>
                    <td><label >Negeri :</label></td>
                    <td>${actionBean.permohonan.penyerahNegeri.nama}</td>
                </tr>
                <tr>
                    <td><label >No. Telefon :</label></td>
                    <td>${actionBean.permohonan.penyerahNoTelefon1}</td>
                </tr>
            </table>
            </fieldset>
        </div>
                    <c:if test="${actionBean.showHP eq 'true'}">
        <div  class="subtitle">
            <fieldset class="aras1">
              <legend>Maklumat Tuan Tanah Terlibat</legend><br />
              <div  align="center">
            <display:table style="width:50%" class="tablecloth" name="${actionBean.senaraiPihak}" cellpadding="1" cellspacing="1" requestURI="/pengambilan/kemasukanaduan1" id="line">
                <display:column>
                    <s:radio name="radio_" id="${line.pihak.idPihak}" value="${line.pihak.idPihak}" style="width:15px" onclick="javascript:selectRadio(this)"/>
                    <s:checkbox name="chkbox" checked="true" id="chkbox" value="${line.pihak.idPihak}"/>
                </display:column>
                <display:column title="Id Pihak" property="idPihak" style="vertical-align:top;"/>
                <display:column title="Nama " property="nama" style="text-transform:uppercase;vertical-align:top;" />
            </display:table>
                  <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
              </div>
            </fieldset>

       </div>
              </c:if>
      </div>
</s:form>

