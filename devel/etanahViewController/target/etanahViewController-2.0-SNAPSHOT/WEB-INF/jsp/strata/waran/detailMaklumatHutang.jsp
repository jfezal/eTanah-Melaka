<%--
    Document   : detailMaklumatHutang
    Created on : ${date}, ${time}
    Author     : faidzal
    Modified By: Murali
--%>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    function cariLelong(){
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);

        var url = '${pageContext.request.contextPath}/strata/pelelong?cariPelelong';
        window.open(url,'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500, left=" + left + ",top=" + top);
    }
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.get(url,q,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function hapus(idMohonWaranItem){
        var idMh = $("#idMh").val();
        var url = '${pageContext.request.contextPath}/strata/waran?hapusWaranItem&idMohonWaranItem='+idMohonWaranItem+'&idMh='+idMh;
        $.get(url,
        function(data){
            $('#page_div').html(data);
            window.location.reload()
        },'html');
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
    function CurrencyFormatted(elmnt,amount)
    {
        var i = parseFloat(amount);
        if(isNaN(i)) { i = 0.00; }
        var minus = '';
        if(i < 0) { minus = '-'; }
        i = Math.abs(i);
        i = parseInt((i + .005) * 100);
        i = i / 100;
        s = new String(i);
        if(s.indexOf('.') < 0) { s += '.00'; }
        if(s.indexOf('.') == (s.length - 2)) { s += '0'; }
        s = minus + s;
        //            $('#amaun').val(s);
        elmnt.value = s;
    }
    function refreshMain(){
        var idMh = document.getElementById("idMh").value;
        alert(idMh);
        var url = '${pageContext.request.contextPath}/strata/waran?paparSenaraiHutang&idMh='+idMh;
        $.ajax({
            type:"GET",
            url : url,
            success : function(data) {
                $('#page_div').html(data);
            }
        });
    }
    function validation(){
        var perihal = document.getElementById("perihal").value;
        var amaun = document.getElementById("amaun").value;

        if(perihal == ""){
            alert("Sila masukkan perihal hutang.");
            return false;
        }
        if(amaun == "0.00" || amaun==""){
            alert("sila masukkan nilai amaun.");
            return false;
        }
        return true;

    }
    function simpan(event, f){
        var perihal = document.getElementById("perihal").value;
        var amaun = document.getElementById("amaun").value;
        var q = $(f).formSerialize();

        if(perihal == ""){
            alert("Sila masukkan perihal hutang.");
            return false;
        }
        if(amaun == "0.00" || amaun==""){
            alert("sila masukkan nilai amaun.");
            return false;
        }else{


            var url = f.action + '?' + event;
            //        alert(url);
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
            },'html');
            return true;
        }


    }
    $(document).ready(function() {
        //        var url = '${pageContext.request.contextPath}/strata/waran?reload';
        var v = document.getElementById("idMh").value;
        var url = '${pageContext.request.contextPath}/strata/waran?paparSenaraiHutang&idMh='+v; //reload main page.
        $.ajax({
            type:"GET",
            url : url,
            success : function(data) {
                $('#page_div',opener.document).html(data);
            }
        });
    });

    function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
         if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

         return true;
      }
</script>
<s:form beanclass="etanah.view.strata.WaranPenahananActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Jumlah Wang Yang Berhutang</legend>
            <s:hidden name="idMh" id="idMh" />
            <c:if test="${!actionBean.display}">      <p>
                    <s:label name="label"> Perihal Hutang :</s:label><s:textarea name="perihal" id="perihal" readonly="${actionBean.display}" cols="50" />
                </p>
                <p>
                    <s:label name="label"> Jumlah(RM) :</s:label><s:text name="amaun" onkeypress="return isNumberKey(event)" id="amaun" readonly="${actionBean.display}" onkeyup="validateNumber(this,this.value);" onchange="CurrencyFormatted(this,this.value);" formatPattern="#,##0.00"/>
                </p>
            </c:if>
            <p> <c:if test="${!actionBean.display}">
                    <label>&nbsp;</label><s:submit class="btn" name="tambahItemWaran" value="Simpan"/>
                    <s:button class="btn" name="Tutup" value="Tutup" onclick="window.close();"/>
                </p></c:if>
                <br>
                <br>

            </fieldset>
        </div>
</s:form>
