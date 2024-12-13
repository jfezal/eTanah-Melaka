<%-- 
    Document   : rekodBayaranPampasanToTuanTanah
    Created on : 14-Apr-2011, 15:07:32
    Author     : massita
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html;charset=windows-1252" import="java.util.*" import="java.text.SimpleDateFormat"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $(".datepicker1").datepicker({dateFormat: 'yy'});
    });

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
            if ($("input[name='kodCaraBayaran.kod']:checked").val() != 'CT' &&
                $("input[name='kodCaraBayaran.kod']:checked").val() != 'CL' &&
                $("input[name='kodCaraBayaran.kod']:checked").val() != 'CB' &&
                $("input[name='kodCaraBayaran.kod']:checked").val() != 'DB' &&
                $("input[name='kodCaraBayaran.kod']:checked").val() != 'T' &&
                $("input[name='kodCaraBayaran.kod']:checked").val() != 'XT') {
                alert('Sila pilih " Cara Pembayaran : " terlebih dahulu.');
                $("#kodCaraBayaran1").focus();
                return false;
            }
            return true;

        }
        function HideforTunai() {
            $("#nodok").attr("disabled",true);
            $("#nodok").val("");
            $("#tarikhDok").attr("disabled",false);
            $("#tarikhDok").val("");
            $("#kodBank").attr("disabled",true);
            $("#kodBank").val("");
            //               $('#nodok').hide();
            //               $('#kodBank').hide();
            //               $('#no').hide();
            //               $('#bank').hide();      
        }
        function Show(){
            $("#nodok").attr("disabled",false);
            $("#tarikhDok").attr("disabled",false);
            $("#kodBank").attr("disabled",false);
            //                $('#nodok').show();
            //                $('#datepicker').show();
            //                $('#kodBank').show();
            //                $('#no').show();
            //                $('#tarikh').show();
            //                $('#bank').show();
        }
        function ajaxLink(link, update) {
            $.get(link, function(data) {
                $(update).html(data);
                $(update).show();
            });
            return false;
        }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div class="subtitle" id="caw">
    <s:form name="form1" id="form1" beanclass="etanah.view.stripes.pengambilan.RekodBayaranPampasanToTuanTanahActionBean">
        <div  id="hakmilik_details">
            <div class="instr" align="center">
                <s:errors/>
            </div>
            <s:hidden id="selectedIdHM" name="selectedIdHM" />
            <s:hidden id="selectedIdPP" name="selectedIdPP" />
            <s:hidden name="index" id="index" />
            <s:errors/>
            <s:messages/>
            <fieldset class="aras1"><br/>
                <c:if test="${actionBean.hakmilik ne null || actionBean.permohonanPihak ne null}" >
                    <div class="content" align="center">
                        <table class="tablecloth">
                            <tr>
                                <th>Id Hakmilik</th><th>No Lot/No PT</th><th>Daerah</th><th>Bandar/Pekan/Mukim</th><th>Tuan Tanah</th>
                            </tr>
                            <tr>
                                <td>
                                    ${actionBean.hakmilik.idHakmilik}
                                </td>
                                <td>
                                    ${actionBean.hakmilik.lot.nama}${actionBean.hakmilik.noLot}
                                </td>
                                <td>
                                    ${actionBean.hakmilik.daerah.nama}
                                </td>
                                <td>
                                    ${actionBean.hakmilik.bandarPekanMukim.nama}
                                </td>
                                <td>
                                    <c:forEach items="${actionBean.permohonanPihakPendepositList}" varStatus="loop" var="pendeposit">
                                        <s:link beanclass="etanah.view.stripes.pengambilan.RekodBayaranPampasanToTuanTanahActionBean"
                                                event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                            <s:param name="idPihak" value="${pendeposit.pihak.idPihak}"/>
                                            <s:param name="nama" value="${pendeposit.pihak.nama}"/>
                                            <s:param name="idHakmilik" value="${actionBean.hakmilik.idHakmilik}"/>
                                            ${pendeposit.pihak.nama}
                                        </s:link>
                                        <BR>
                                    </c:forEach>
                                </td>
                            </tr>
                        </table>
                    </div>
                </c:if>
            </fieldset>
            <c:if test="${showDetails}">
                <div  class="subtitle">
                    <fieldset class="aras1">
                        <legend>Maklumat Pembayaran</legend><br />
                        <div  align="center">
                            ${actionBean.nama}
                            <table width="100%">
                                <tr>
                                    <td width="30%"><label >Amaun Diterima  (RM) :</label></td>
                                    <td><s:text name="sum" id="sum" size="50" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00"/><br /></td>
                                </tr>
                                <tr>
                                    <td><label >Cara Pembayaran : </label></td>
                                    <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran1" value="CT" onclick="Show();"/> Cek Tempatan <br /></td>
                                </tr>
                                <tr>
                                    <td><label >&nbsp;&nbsp;</label></td>
                                    <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran2" value="CL" onclick="Show();"/> Cek Luar <br /></td>
                                </tr>
                                <tr>
                                    <td><label >&nbsp;&nbsp;</label></td>
                                    <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran3" value="CB" onclick="Show();"/> Cek Bank Negara <br /></td>
                                </tr>
                                <tr>
                                    <td><label >&nbsp;&nbsp;</label></td>
                                    <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran4" value="DB" onclick="Show();"/> Bank Draf <br /></td>
                                </tr>
                                <tr>
                                    <td><label >&nbsp;&nbsp;</label></td>
                                    <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran5" value="T" onclick="HideforTunai();"/> Tunai <br /></td>
                                </tr>
                                <!--                                <tr>
                                                                    <td><label >&nbsp;&nbsp;</label></td>
                                                                    <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran6" value="XT"/> Tiada Pembayaran</td>
                                                                </tr>-->
                                <%--<c:if test="${actionBean.ambilPampasan.kodCaraBayaran.kod ne 'T'}">--%>
                                <tr>
                                    <td><label id="no" >No. :</label></td>
                                    <td><s:text name="noDok" disabled="${actionBean.tunai}" size="50" id="nodok"/></td>
                                </tr>
                                <tr>
                                    <td><label id="tarikh" >Tarikh :</label></td>
                                    <td><s:text name="tarikhDok" disabled="${actionBean.xtunai}" class="datepicker" id="datepicker" formatPattern="dd/MM/yyyy"/></td>
                                </tr>
                                <tr>
                                    <td><label id="bank" >Bank :</label></td>
                                    <td><s:select name="kodBank.kod" disabled="${actionBean.tunai}" style="width:300px;" id="kodBank" >
                                            <%--<s:select name="kodBank.kod" style="width:300px;" id="kodBank" >--%>
                                            <s:option value="">-- Sila Pilih --</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodBank}" value="kod" label="nama"/>
                                        </s:select>
                                    </td>
                                </tr>
                                <%--</c:if>--%>


                            </table>
                            <br/><br/>

                            <div align="center">
                                <s:hidden name="idPihak" value="${actionBean.idPihak}"/>
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


