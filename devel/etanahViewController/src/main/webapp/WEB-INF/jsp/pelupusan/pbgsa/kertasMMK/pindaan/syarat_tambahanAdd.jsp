<%-- 
    Document   : syarat_tambahanAdd
    Created on : Jul 10, 2013, 2:34:08 PM
    Author     : afham
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tambah Kod</title>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">
    $(document).ready( function() {
        var e= $('#sizeKod').val();
        var a = 0 ;
        for(a=0;a<e;a++){
            $('#harga'+a).attr("disabled", "true");
        }
        
        
        
        
    });
    function selectCheckBox()
    {
//        NoPrompt();
        var e= $('#sizeKod').val();
        var cnt=0;
        var item = new Array() ;
        var harga = new Array() ;
        for(cnt=0;cnt<e;cnt++)
        {
            if(e=='1'){
                if(document.frm.checkmate.checked) {
                    //                     alert(document.frm.checkmate[cnt].value) ;
                    item[cnt] = document.frm.checkmate.value ;
//                    alert($('#harga'+cnt).val());
                    harga[cnt] = $('#harga'+cnt).val() ;
                }

            }
            else {
                if(document.frm.checkmate[cnt].checked) {
                    //                     alert(document.frm.checkmate[cnt].value) ;
                    item[cnt] = document.frm.checkmate[cnt].value ;
//                    alert($('#harga'+cnt).val());
                    harga[cnt] = $('#harga'+cnt).val() ;
                }
                else{
                    item[cnt] = "T" ;
                    harga[cnt] = "T";
                }
            }
        }
        //                    alert(data) ;
                if(confirm("Adakah anda pasti?")) {
                    var url = '${pageContext.request.contextPath}/pelupusan/kertas_pindaan_MMKV2?simpanKodTuntut&item='+item+'&harga='+harga ;
                    $.get(url,
                    function(data){
                        $('#page_div').html(data);
                        alert("Rekod telah berjaya di masukkan") ;
                        self.openFrame('tBorang') ;
                    },'html');
                }
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
    
    function isChecked(val){
    
        //    alert(val);
        $('#harga'+val).removeAttr('disabled');
    }
    
    function openFrame(type){
        NoPrompt();
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_pindaan_MMKV2?openFrame&type='+type;
        window.open(url, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
</script>
<body>
    <script>
        // Allow the user to be warned by default.
        var allowPrompt = true;
                
        window.onbeforeunload = WarnUser;
        function WarnUser()
        {   
            if(allowPrompt)
                refreshpage();
            if(allowPrompt)
            {
                event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
            }
            else
            {
                // Reset the flag to its default value.
                allowPrompt = true;
            }
        }
        function NoPrompt()
        {
            allowPrompt = false;
        }

    </script>

    <s:form beanclass="etanah.view.stripes.pelupusan.KertasPindaanMMKV2ActionBean" name="frm">
        <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
        <s:errors/>
        <s:messages/>
        <div class="subtitle" id="main">
            <s:hidden name="${actionBean.sizeKod}" id="sizeKod" value="${actionBean.sizeKod}"/>
            <fieldset class="aras1">
                <div id="maklumatborang" align="center">
                    <legend>Maklumat Borang</legend>
                    <c:set var="i" value="0" />
                    <display:table style="width:100%" class="tablecloth" name="${actionBean.listKodTuntut}" pagesize="100" cellpadding="0" cellspacing="0" requestURI="/pelupusan/pihak_kepentingan" id="line">
                        <display:column> <s:checkbox name="checkmate" id="checkmate" value="${line.kod}" onclick="isChecked(${i});"/></display:column>
                        <display:column title="Nama" property="nama"/>
                        <display:column title="Nilai">
                            <s:text name="harga${i}" id="harga${i}" formatPattern="#,###,##0.00" maxlength="15" onkeyup="validateNumber(this,this.value);"/>
                        </display:column>
                        <c:set var="i" value="${i+1}" />
                    </display:table>
                    <c:if test="${fn:length(actionBean.listKodTuntut) > 0 }">
                        <s:button class="btn" value="Pilih" name="pilih" id="pilih" onclick="selectCheckBox();"/>
                        <s:button class="btn" value="Kembali" name="kembali" id="kembali" onclick="openFrame('tBorang')"/>
                    </c:if>
                </div>
            </fieldset>
        </div>
    </s:form>
</body>

