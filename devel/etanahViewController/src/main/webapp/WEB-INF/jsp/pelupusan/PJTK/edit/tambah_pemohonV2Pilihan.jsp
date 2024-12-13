<%-- 
    Document   : tambah_pemohonV2Pilihan
    Created on : Jun 14, 2013, 9:59:36 AM
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
        
        
        
    });
    function selectRadioBox()
    {
        NoPrompt();
        //                alert("test");
        var e= $('#sizeKod').val();
        //        alert(e);
        //                alert(document.frm.radiomate.value);
        var cnt=0;
        var data = new Array() ;

        for(cnt=0;cnt<e;cnt++)
        {
            //                     alert("test1
            if(e=='1'){
                if(document.frm.radiomate.checked) {
                    //                     alert(document.frm.checkmate[cnt].value) ;
                    data[cnt] = document.frm.radiomate.value ;
                    //                     alert(data[cnt])
                }
            }
            else{
                if(document.frm.radiomate[cnt].checked) {
                    //                     alert(document.frm.checkmate[cnt].value) ;
                    data[cnt] = document.frm.radiomate[cnt].value ;
                    //                     alert(data[cnt])
                }
                else{
                    data[cnt] = "T" ;
                }
            }
        }
        if(confirm("Adakah anda pasti?")) {
            var url = '${pageContext.request.contextPath}/pelupusan/lelong_awam?simpanPemilik&idPihak='+data ;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                alert("Rekod telah berjaya di masukkan") ;
                self.openFrame('tPemohon') ;
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
    
    function openFrame(type){
        NoPrompt();
        var url = '${pageContext.request.contextPath}/pelupusan/lelong_awam?openFrame&type='+type;
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

    <s:form beanclass="etanah.view.stripes.pelupusan.LelongAwamActionBean" name="frm">
        <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
        <s:errors/>
        <s:messages/>
        <div class="subtitle" id="main">
            <s:hidden name="${actionBean.sizeKod}" id="sizeKod" value="${actionBean.sizeKod}"/>
            <fieldset class="aras1">
                <div id="maklumatborang" align="center">
                    <legend>Maklumat Tuan Tanah</legend>
                    <br/>
                    <display:table style="width:100%" class="tablecloth" name="${actionBean.senaraiTuanTanah}" cellpadding="0" cellspacing="0" requestURI="/pelupusan/pihak_kepentingan" id="line">
                        <display:column> <s:radio name="radiomate" id="radiomate" value="${line.pihak.idPihak}"/></display:column>
                        <display:column title="Nama" property="pihak.nama"/>
                        <display:column title="No Pengenalan" property="pihak.noPengenalan"/>
                        <display:column title="Syer">
                            ${line.syerPembilang} / ${line.syerPenyebut}
                        </display:column>
                    </display:table>
                    <c:if test="${fn:length(actionBean.senaraiTuanTanah) > 0 }">
                        <s:button class="btn" value="Pilih" name="pilih" id="pilih" onclick="selectRadioBox();"/>
                        <s:button class="btn" value="Kembali" name="kembali" id="kembali" onclick="openFrame('tBorang')"/>
                    </c:if>
                </div>
            </fieldset>
        </div>
    </s:form>
</body>
