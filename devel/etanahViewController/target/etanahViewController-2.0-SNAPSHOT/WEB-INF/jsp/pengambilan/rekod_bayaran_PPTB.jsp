<%-- 
    Document   : rekod_bayaran_PPTB
    Created on : 13-Mar-2012, 11:05:07
    Author     : nordiyana
--%>

<%@ page language="java" contentType="text/html;" import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="etanah.model.Pengguna"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript">

    $(document).ready( function(){


        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $(".datepicker1").datepicker({dateFormat: 'yy'});
        });
        function showTunai(){
            $("#nodok").attr("disabled",false);
            $("#tarikhDok").attr("disabled",false);
            $("#kodBank").attr("disabled",false);
        }

        function hideTunai(){
            $("#nodok").attr("disabled",true);
            $("#nodok").val("");
            $("#tarikhDok").attr("disabled",false);
            $("#tarikhDok").val("");
            $("#kodBank").attr("disabled",true);
            $("#kodBank").val("");
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
        function popupList(idPihak,idHakmilik){
            var url = '${pageContext.request.contextPath}/pengambilan/akaunTerimaBayaran?showAkuanBayaranList&idPihak='+idPihak+'&idHakmilik='+idHakmilik;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=800,height=400");
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
            $("#selectedIdPP").val(obj.value);
            alert(obj.value);
    <%--document.getElementById("selectedIdHM").value=obj.value;--%>
        }
        function validation() {
            if($("#jumTerimaPampasan").val() == ""){
                alert('Sila pilih " Jumlah pampasan yang diterima (RM) : " terlebih dahulu.');
                $("#jumTerimaPampasan").focus();
                return false;
            }
            if ($("input[name='kodCaraBayaran']:checked").val() != 'CT' &&
                $("input[name='kodCaraBayaran']:checked").val() != 'CL' &&
                $("input[name='kodCaraBayaran']:checked").val() != 'CB' &&
                $("input[name='kodCaraBayaran']:checked").val() != 'DB' &&
                $("input[name='kodCaraBayaran']:checked").val() != 'T' &&
                $("input[name='kodCaraBayaran']:checked").val() != 'XT') {
                alert('Sila pilih " Cara Pembayaran : " terlebih dahulu.');
                $("#kodCaraBayaran1").focus();
                return false;
            }else if($("input[name='kodCaraBayaran']:checked").val() != 'T'){
                $("#nodok").attr("disabled",false);
                $("#tarikhDok").attr("disabled",false);
                $("#kodBank").attr("disabled",false);

            }
                return true;

            }
            function showforTunai() {
                $('#nodok').show();
                $('#datepicker').show();
                $('#kodBank').show();
                $('#no').show();
                $('#tarikh').show();
                $('#tarikhDok').show();
                $('#bank').show();
                $("#noDok").val()="";
                $("#tarikhDok").val()="";
                $('#kodBank').val()=="";
            }
            function HideforTunai() {
                $('#nodok').hide();
                $('#datepicker').hide();
                $('#kodBank').hide();
                $('#no').hide();
                $('#tarikh').hide();
                $('#tarikhDok').hide();
                $('#bank').hide();
                $("#nodok").attr("disabled",true);
                $("#nodok").val("");
                $("#tarikhDok").attr("disabled",true);
                $("#tarikhDok").val("");
                $("#kodBank").attr("disabled",true);
                $("#kodBank").val("");
            }
            function ajaxLink(link, update) {
                $.get(link, function(data) {
                    $(update).html(data);
                    $(update).show();
                });
                return false;
            }
//            function validateAmount(idVar){
//                var str1 = "a,d,k";
//                str1.replace(/\,/g,"");
//                var str2 = str1.replace(/\,/g,"");
//                var amounttuntut = idVar;
//                <%--alert(amounttuntut);--%>
//                var amountsebenar = $('#amountsebenar').val();
//                var aaa= amountsebenar.replace(/\,/g,"");
//               <%-- var am =amountsebenar.indexOf(".00");--%>
//                var am =aaa.indexOf(".00");
//                <%--alert('aaa'+aaa);--%>
//                    
//                <%--var ab = aaa.substring(0, am -1);--%>
//                var ab = aaa.substring(0, am);
//                <%--alert('ab'+ab);--%>
//
//
//                var ad = amounttuntut.replace(/\,/g,"");
//                <%--alert(ad);--%>
//                var amm =ad.indexOf(".00");
//                <%--alert(amm);--%>
//                var ac;
//                <%--alert(amm);--%>
//                if(amm=='-1'){
//                    ac = amounttuntut;
//                }else{
//                    <%--alert(ad);--%>
//                    ac = ad.substring(0, amm);
//                }
//                <%--alert(ac);--%>
//                if(ac != ab){
//                    alert("Pastikan jumlah yang dibayar sama dengan jumlah yang dikehendaki");
//                    $('#jumTerimaPampasan').val("");
//                    $('#jumTerimaPampasan').focus();
//                    return true;
//                }
//            }

</script>
<div class="subtitle" id="caw">
    <s:form name="form1" id="form1" beanclass="etanah.view.stripes.pengambilan.RekodBayaranPPTBActionBean">
        <div  id="hakmilik_details">
            <%--<s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>--%>
            <s:messages/>
            <div class="instr" align="center">
                <s:errors/>
            </div>
            <s:hidden id="selectedIdHM" name="selectedIdHM" />
            <s:hidden id="selectedIdPP" name="selectedIdPP" />
            <s:hidden name="index" id="index" />
            <s:errors/>
            <%--<s:messages/>--%>
            <fieldset class="aras1">
                <legend>
                    Maklumat Hakmilik
                </legend><br/>
                <div align="center">
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/akaunBayaran" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column property="hakmilik.noHakmilik" title="No Hakmilik"/>
                        <%--<display:column property="hakmilik.noLot" title="No Lot/No PT" />--%>
                        <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                        <display:column title="Nama" >
                            <c:forEach items="${actionBean.senaraiPerbicaraanKehadiran}" var="senarai">
                                <s:link beanclass="etanah.view.stripes.pengambilan.RekodBayaranPPTBActionBean"
                                        event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                   <s:param name="idHadir" value="${senarai.idKehadiran}"/>
                                    <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>
                                     <c:if test="${senarai.pihak ne null}"> ${senarai.pihak.pihak.nama} </c:if>
                        <c:if test="${senarai.permohonanPihakTidakBerkepentingan ne null}"> ${senarai.permohonanPihakTidakBerkepentingan.nama} </c:if>
                                </s:link>
                                <br/>
                            </c:forEach>
                        </display:column>
                    </display:table>
                </div>
            </fieldset>
            <c:if test="${showDetails}">
                <div  class="subtitle">
                    <fieldset class="aras1">
                        <legend>Maklumat Pembayaran</legend><br />
                        <div  align="center">
                            <table width="100%">
                                <%--    <tr>
                                       <td><label for="nama">Nama Pengadu/Tuan Tanah :</label></td>
                                       <td>${actionBean.pp.pihak.nama}</td>
                                   </tr>--%>
                                <tr>
                                    <td width="30%"><label >Jumlah Pampasan (RM) :</label></td>
                                    <%--formatPattern="#,##0.00"--%>
                                    <td><s:text name="a" id="amountsebenar" formatPattern="#,##0.00" value="10"  readonly="true"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%"><label >Amaun Diterima  (RM) :</label></td>
                                    <%--formatPattern="#,##0.00"--%>
                                    <td><s:text name="jumTerimaPampasan" formatPattern="#,##0.00" id="jumTerimaPampasan" size="50" onblur="validateAmount(this.value);"/><br/></td>
                                        <%--onblur="validateAmount(this.value);"--%>
                                </tr>
                                <tr>
                                    <td><label >Cara Pembayaran : </label></td>
                                </tr>
                                <tr>
                                    <td><label >&nbsp;&nbsp;</label></td>
                                    <%--onclick="showTunai();"--%>
                                    <td><s:radio name="kodCaraBayaran" id="kodCaraBayaran1" value="CT" onclick="showTunai();"/> Cek Tempatan <br /></td>
                                </tr>
                                <tr>
                                    <td><label >&nbsp;&nbsp;</label></td>
                                    <%--onclick="showTunai();"--%>
                                    <td><s:radio name="kodCaraBayaran" id="kodCaraBayaran2" value="CL" onclick="showTunai();"/> Cek Luar <br /></td>
                                </tr>
                                <tr>
                                    <td><label >&nbsp;&nbsp;</label></td>
                                    <td><s:radio name="kodCaraBayaran" id="kodCaraBayaran3" value="CB" onclick="showTunai();"/> Cek Bank Negara <br /></td>
                                </tr>
                                <tr>
                                    <td><label >&nbsp;&nbsp;</label></td>
                                    <td><s:radio name="kodCaraBayaran" id="kodCaraBayaran4" value="DB" onclick="showTunai();"/> Bank Draf <br /></td>
                                </tr>
                                <tr>
                                    <td><label >&nbsp;&nbsp;</label></td>
                                    <%--onclick="hideTunai();"--%>
                                    <td><s:radio name="kodCaraBayaran" id="kodCaraBayaran5" value="T" onclick="hideTunai();"/> Tunai <br /></td>
                                </tr>
                                <%--<tr>
                                    <td><label >&nbsp;&nbsp;</label></td>
                                    onclick="showTunai();"
                                    <td><s:radio name="kodCaraBayaran" id="kodCaraBayaran6" value="XT" onclick="showTunai();"/> Tiada Pembayaran</td>
                                </tr>--%>
                                <%--<c:if test="${actionBean.ambilPampasan.kodCaraBayaran.kod ne 'T'}">--%>
                                <tr>
                                    <td><label id="no">No.:</label></td>
                                    <td><s:text name="noDok" disabled="${actionBean.tunai}" size="50" id="nodok" onkeyup="validateNumber(this,this.value);"/></td>
                                </tr>
                                <tr>
                                    <td><label id="tarikh" >Tarikh :</label></td>
                                    <%--<td><s:text name="tarikhDok" class="datepicker" id="datepicker" formatPattern="dd/MM/yyyy"/>--%>
                                    <td><s:text name="tarikhDok" disabled="${actionBean.xtunai}" id="tarikhDok" class="datepicker" formatPattern="dd/MM/yyyy"/></td>
                                </tr>
                                <tr>
                                    <td><label id="bank">Bank :</label></td>
                                    <td><s:select name="kodBank" disabled="${actionBean.tunai}" style="width:300px;" id="kodBank" >
                                            <s:option value="">-- Sila Pilih --</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodBank}" value="kod" label="nama"/>
                                        </s:select>
                                    </td>
                                </tr>
                                <%--</c:if>--%>


                            </table>
                            <br/><br/>

                            <div align="center">
                                <s:hidden name="idPermohonanPhkTdkBerptg" value="${actionBean.idPermohonanPhkTdkBerptg}"/>
                                <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
                                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                            </div>

                            <br/>

                        </div>
                    </fieldset>
                </div>
            </c:if>
        </div>
    </s:form>
</div>


