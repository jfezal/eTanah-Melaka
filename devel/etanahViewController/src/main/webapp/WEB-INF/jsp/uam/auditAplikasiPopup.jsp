<%-- 
    Document   : auditAplikasiPopup
    Created on : Dec 19, 2011, 4:23:29 PM
    Author     : faidzal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });
    function viewPopup(a){
   // alert(a);
        var s = $('#idPengguna').val();
        var sa = $('#tarikhDari').val();
        var ss = $('#tarikhHingga').val();
        
        var url = '${pageContext.request.contextPath}/reportGeneratorServlet?reportName='+a+'&report_p_id_pguna='+s+'&report_p_trh_dari='+sa+'&report_p_trh_hingga='+ss;
        //        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=850,height=600,scrollbars=yes");
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=850,height=600,scrollbars=yes");

    }
    function ReplaceAll(Source,stringToFind,stringToReplace){
        var temp = Source;
        var index = temp.indexOf(stringToFind);
        while(index != -1){
            temp = temp.replace(stringToFind,stringToReplace);
            index = temp.indexOf(stringToFind);

        }
        return temp;
    }

      

    function dateValidation(id, value){
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var today = new Date();
        var sdate = new Date(fulldate);
        if (sdate > today){
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#'+id).val("");
        }
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
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

    function validateYearLength(value){
        var plength = value.length;
        if(plength != 4){
            alert('"Tahun" yang dimasukkan salah.');
            $('#tahun').val("");
            $('#tahun').focus();
        }
    }
</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>
<s:form beanclass="etanah.view.uam.AuditAplikasiBean">
    <div class ="subtitle displaytag">
        <fieldset class ="aras1">
            <legend>Carian</legend>
            <p><label><font color="red">*</font>ID Pengguna :</label>
                <s:text name="idPengguna" id="idPengguna" value = "${actionBean.idPengguna}"></s:text>
            </p>

            <p><label><font color="red">*</font>Tarikh Dari :</label>
                <s:text name="tarikhDari" id="tarikhDari" class="datepicker"  formatPattern="dd/MM/yyyy" value = ""></s:text>
            </p>
            <p><label><font color="red">*</font>Tarikh Hingga :</label>
                <s:text name="tarikhHingga" id="tarikhHingga" class = "datepicker" formatType="date" formatPattern="dd/MM/yyyy" value = ""></s:text>
            </p>


            </p>
            <div align ="center">
                <p><s:button name="viewReport" value="Papar" class="btn" onclick="viewPopup('${actionBean.reportName}')"/>&nbsp;<s:button name = "reset" class="btn" value ="Isi Semula" onclick= "clearText('auditAplikasi')" /></p>
            </div>
        </fieldset>
    </div>

</s:form>