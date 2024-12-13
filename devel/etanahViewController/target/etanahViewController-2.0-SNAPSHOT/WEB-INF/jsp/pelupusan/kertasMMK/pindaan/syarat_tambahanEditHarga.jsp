<%-- 
    Document   : syarat_tambahanEditHarga
    Created on : Jul 10, 2013, 2:45:51 PM
    Author     : afham
--%>

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
        <s:hidden name="idTuntutanKos" id="idTuntutanKos"/>
        <div class="subtitle" id="main">
            <s:hidden name="${actionBean.sizeKod}" id="sizeKod" value="${actionBean.sizeKod}"/>
            <fieldset class="aras1">
                <div id="maklumatborang" align="center">
                    <legend>Kemaskini Amaun</legend>
                        <table class="tablecloth" border="0">
                            <tr>
                                <td>Item :</td>
                                <td>${actionBean.permohonanTuntutanKos.item}</td>
                            </tr>
                            <tr>
                                <td>Amaun :</td>
                                <td><s:text name="harga" id="harga" value="${actionBean.permohonanTuntutanKos.amaunTuntutan}" formatPattern="#,###,##0.00" maxlength="15" onkeyup="validateNumber(this,this.value);"/></td>
                            </tr>
                        </table>
                        <s:submit class="btn" value="Kemaskini" name="kemaskiniAmaun" id="kemaskini" onclick="NoPrompt();"/>
                        <s:button class="btn" value="Kembali" name="kembali" id="kembali" onclick="openFrame('tBorang')"/>
                </div>
            </fieldset>
        </div>
    </s:form>
</body>
