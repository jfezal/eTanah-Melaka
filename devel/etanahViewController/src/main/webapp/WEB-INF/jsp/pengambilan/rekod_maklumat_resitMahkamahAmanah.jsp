<%-- 
    Document   : rekod_maklumat_resitMahkamahAmanah
    Created on : Apr 23, 2013, 12:48:25 PM
    Author     : Admin
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
    <%--
        if($("#datepicker").val() == ""){
            alert('Sila pilih " Tarikh : " terlebih dahulu.');
            $("#datepicker").focus();
            return false;
        }
        if($("#kodBank").val() == ""){
            alert('Sila pilih " Bank : " terlebih dahulu.');
            $("#kodBank").focus();
            return false;
        }--%>
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
            function validateAmount(idVar){
                var str1 = "a,d,k";
                str1.replace(/\,/g,"");
                var str2 = str1.replace(/\,/g,"");
                var amounttuntut = idVar;
    <%--alert(amounttuntut);--%>
            var amountsebenar = $('#amountsebenar').val();
            var aaa= amountsebenar.replace(/\,/g,"");
    <%-- var am =amountsebenar.indexOf(".00");--%>
            var am =aaa.indexOf(".00");
    <%--alert('aaa'+aaa);--%>

    <%--var ab = aaa.substring(0, am -1);--%>
            var ab = aaa.substring(0, am);
    <%--alert('ab'+ab);--%>


            var ad = amounttuntut.replace(/\,/g,"");
    <%--alert(ad);--%>
            var amm =ad.indexOf(".00");
    <%--alert(amm);--%>
            var ac;
    <%--alert(amm);--%>
            if(amm=='-1'){
                ac = amounttuntut;
            }else{
    <%--alert(ad);--%>
                ac = ad.substring(0, amm);
            }
    <%--alert(ac);--%>
            if(ac != ab){
                alert("Pastikan jumlah yang dibayar sama dengan jumlah yang dikehendaki");
                $('#jumTerimaPampasan').val("");
                $('#jumTerimaPampasan').focus();
                return true;
            }
        }

</script>
<div class="subtitle" id="caw">
    <s:form name="form1" id="form1" beanclass="etanah.view.stripes.pengambilan.rekod_maklumat_resitMahkamahAmanahActionBean">
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
            
            <fieldset class="aras1"><br/> 
                <legend align="left"><b>Maklumat Hakmilik Permohonan ${actionBean.hakmilik.idHakmilik}</b></legend>
                <div align="center">

                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/maklumatPampasan" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column title="ID Hakmilik">
                            <s:link beanclass="etanah.view.stripes.pengambilan.rekod_maklumat_resitMahkamahAmanahActionBean"
                                    event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}</s:link>
                        </display:column>
                        <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    </display:table>
                </div>
            </fieldset>
                
            <%--<c:if test="${actionBean.hakmilik ne null}">
                <s:hidden name="idHakmilik" value="${actionBean.hakmilik.idHakmilik}"/>
                <fieldset class="aras1">
                    <legend>Tuan Tanah</legend><br />
                    <div align="center">
                        <display:table class="tablecloth" name="${actionBean.hakmilik}" cellpadding="0" cellspacing="0" id="tbl1">
                            <display:column title="No" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                            <display:column property="idHakmilik" title="ID Hakmilik" />
                            <display:column property="noLot" title="Nombor Lot/PT" />
                            <display:column title="Daerah" property="daerah.nama" class="daerah" />
                            <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                            <display:column title="Orang Berkepentingan" style="vertical-align:baseline">
                                <c:forEach items="${actionBean.senaraiPerbicaraanKehadiran}" var="senarai">
                                    <c:if test="${line.hakmilik.idHakmilik eq tbl1.idHakmilik}">
                                        <s:link beanclass="etanah.view.stripes.pengambilan.rekod_maklumat_resitMahkamahAmanahActionBean"
                                                event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                            <s:param name="idPermohonanPihak" value="${senarai.pihak.pihak.idPihak}"/>${senarai.pihak.pihak.nama}
                                            <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>
                                        </s:link>
                                        <br/>

                                        <br/>
                                    </c:if>
                                </c:forEach>
                            </display:column>
                            <display:column title="Luas Diambil" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${actionBean.hakmilikPermohonan.luasTerlibat}"/>&nbsp;${actionBean.hakmilik.kodUnitLuas.nama}</display:column>
                        </display:table>
                    </div>
                    <br /><br />
                </fieldset><br />
            </c:if>--%>

            <c:if test="${actionBean.hakmilik ne null}">
            <%--<s:hidden name="idHakmilik" value="${actionBean.hakmilik.idHakmilik}"/>--%>
            <fieldset class="aras1">
                <legend>Tuan Tanah ${actionBean.hakmilik.idHakmilik}</legend><br />
                <div align="center">
                    <display:table class="tablecloth" name="${actionBean.hakmilik}" cellpadding="0" cellspacing="0" id="tbl1">
                        <display:column title="No" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                        <display:column property="idHakmilik" title="ID Hakmilik" />
                        <display:column property="noLot" title="Nombor Lot/PT" />
                        <display:column title="Daerah" property="daerah.nama" class="daerah" />
                        <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                        <display:column title="Orang Berkepentingan" style="vertical-align:baseline">
                            <c:forEach items="${actionBean.senaraiPerbicaraanKehadiran}" var="senarai">
                                    <s:link beanclass="etanah.view.stripes.pengambilan.rekod_maklumat_resitMahkamahAmanahActionBean"
                                            event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                        <s:param name="idPihak" value="${senarai.pihak.pihak.idPihak}"/>${senarai.pihak.pihak.nama}
                                        <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>
                                    </s:link>
                                    <br/>

                                <br/>
                            </c:forEach>
                        </display:column>
                        <display:column title="Luas Diambil" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${actionBean.hakmilikPermohonan.luasTerlibat}"/>&nbsp;${actionBean.hakmilik.kodUnitLuas.nama}</display:column>
                    </display:table>
                </div>
                <br /><br />
            </fieldset><br />
        </c:if>

            <c:if test="${showDetails}">
                <div  class="subtitle">
                    <s:hidden name="idPihak" />
                    <fieldset class="aras1">
                        <legend>Maklumat Resit Pembayaran ${actionBean.pMahkamah} ${actionBean.hakmilik.idHakmilik}</legend><br />
                        <div  align="center">
                            <table width="100%">
                                <tr>
                                    <td><label for="nama">Nama Waris :</label></td>
                                    <%--<td>${actionBean.pMahkamah.pihak.pihak.nama}</td>--%>
                                    <td>${actionBean.pMahkamah.permohonanPihak.pihak.nama}</td>
                                </tr>
                                <tr>
                                    <%--disabled="${actionBean.tunai}"--%>
                                    <td><label id="no">No. Resit:</label></td>
                                    <td><s:text name="noDok"size="50" id="nodok" onkeyup="validateNumber(this,this.value);"/></td>
                                </tr>
                                <tr>
                                    <%--disabled="${actionBean.xtunai}"--%>
                                    <td><label id="tarikh">Tarikh :</label></td>
                                    <td><s:text name="tarikhDok" id="tarikhDok" class="datepicker" formatPattern="dd/MM/yyyy"/></td>
                                </tr>
                                <%--</c:if>--%>


                            </table>
                            <br/><br/>

                            <div align="center">
                                <s:hidden name="idPihak" value="${actionBean.idPihak}"/>
                                <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
                                <%--<s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>--%>
                                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                            </div>

                            <br/>

                        </div>
                    </fieldset>
                </div>
            </c:if>
        </div>
    </s:form>
</div>


