<%-- 
    Document   : BorangJ
    Created on : 22-Jul-2010, 10:06:41
    Author     : nordiyana
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
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

    <%--    function refreshPage(){
            var q = $('#form1').serialize();
            var url = document.form1.action + '?refreshPage&';// + event;
            window.location = url+q;

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

            if(document.form1.tempohKeteranganBertulis.value=="")
            {
                alert("Sila Masukkan Tempoh Pengosongan");
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
            $("#selectedIdPP").val(obj.value);
            alert(obj.value);
    <%--document.getElementById("selectedIdHM").value=obj.value;--%>
        }
</script>
<script type="text/javascript">


    function showReport(){
        window.open("${pageContext.request.contextPath}/pengambilan/borangJ?genReport567", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
    }
</script>
<s:form beanclass="etanah.view.stripes.pengambilan.BorangJActionbean" name="form1" id="form1">
    <s:messages/>
    <s:errors/>
    <c:if test="${actionBean.mesej eq null}"/>
  <%--  <c:if test="${actionBean.borangJ eq 'false'}">--%>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Sedia Borang 5 / 6 & 7
                </legend>

                <div class="content" align="left">
                    
                   
                    <br>
                    <p>
                        <label>&nbsp;</label>
                        
                        <s:button name="genReport" id="report" value="Jana Borang 5 / 6&7" class="longbtn" onclick="showReport();"/>&nbsp;
                    </p>

                </div>
            </fieldset>
        </div>
        <p align="center">

        </p>
        <%--</c:if>--%>
</s:form>
