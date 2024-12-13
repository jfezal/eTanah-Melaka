<%-- 
    Document   : kemaskini_data
    Created on : Jul 24, 2013, 5:02:54 PM
    Author     : afham
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kemaskini Data</title>
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
    function selectRadioBox()
    {
        NoPrompt();
        if(confirm("Adakah anda pasti untuk dikemaskini?")) {
            var url = '${pageContext.request.contextPath}/pelupusan/gisIntegration?updateGISPelanB1' ;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                alert("Rekod telah berjaya di kemaskini") ;
                self.close() ;
                self.opener.refreshTerimaPAB1() ;
            },'html');
        }
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

    <s:form beanclass="etanah.view.stripes.pelupusan.GISIntegrationActionBean" name="frm">
        <s:errors/>
        <s:messages/>
        <div class="subtitle" id="main">
            <fieldset class="aras1">
                <center>
                    <p style="color:red">
                        *Sila klik pada butang kemaskini untuk mengemaskini no lot dan luas pelan B1<br/>
                    </p>
                    <s:button class="btn" value="Kemaskini" name="pilih" id="pilih" onclick="selectRadioBox();"/>
                </center>

            </fieldset>
        </div>
    </s:form>
</body>

